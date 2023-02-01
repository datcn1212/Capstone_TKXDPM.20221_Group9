package controller;

import subsystem.interbank.IInterbank;
import subsystem.interbank.Interbank;
import util.*;
import entity.*;
import javafx.util.Pair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author duydc
 *
 */
public class ReturnBikeController {

	/**
	 * Process return bike by passing rental code, card and newDockID
	 * 
	 * @param rentalCode: rental code of renting invoice
	 * @param card:       credit card user uses to process transaction
	 * @param newDockID:  id of the dock which user chooses to return bike
	 * @return respond code and rent bike invoice
	 */
	public static Pair<String, RentBikeInvoice> processReturnBike(String rentalCode, CreditCard card, String newDockID)
			throws RuntimeException {
		// Gent rent bike invoice and rental bike from rental code
		RentBikeInvoice rentBikeInvoice = RentBikeController.getRentBikeInvoice(rentalCode);
		Bike bike = DockController.getBikeFromID(rentBikeInvoice.getBikeCode());

		// Initialize for time and calculate rent duration
		Calendar calendar = Calendar.getInstance();
		DateFormat daytime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date returnTime = calendar.getTime();

		Date rentTime = null;
		try {
			rentTime = daytime.parse(rentBikeInvoice.getRentTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Calculate rent duration
		long diff = returnTime.getTime() - rentTime.getTime();
		int duration = (int) Math.ceil((double) diff / 60000);

		// Calculate rent cost and refund amount
		int rentCost = bike.caculateRentCost(duration);
		int refundAmount = rentBikeInvoice.getDeposit() - rentCost;
		System.out.println("Refund amount: " + refundAmount);
		assert refundAmount > 0;

		// Using interbank to process refund
		IInterbank interbank = new Interbank();
		String respondCode = interbank.processTransaction(card, refundAmount, "Transaction for a refund",
				daytime.format(returnTime), Constants.REFUND);
		System.out.println("respond code: " + respondCode);

		// Update after return bike
		if (!respondCode.equals(Constants.SUCCESS)) {
			HandleException.getException(respondCode);
			return new Pair<>(respondCode, null);
		} else {
			updateReturnBikeStatus(card, bike, newDockID, refundAmount, daytime.format(returnTime), rentBikeInvoice);
		}

		return new Pair<>(respondCode, rentBikeInvoice);
	}

	/**
	 * Update after return bike, including bike status, update invoice and and save
	 * payment transaction
	 * 
	 * @param card:            credit card user uses to process transaction
	 * @param bike:            bike that user returns
	 * @param newDockID:       id of the dock which user chooses to return
	 * @param refundAmount:    refund amount after return bike
	 * @param returnStamp:     time stamp which user returns bike
	 * @param rentBikeInvoice: corresponding rent bike invoice
	 */
	private static void updateReturnBikeStatus(CreditCard card, Bike bike, String newDockID, int refundAmount,
			String returnStamp, RentBikeInvoice rentBikeInvoice) {
		// Update rent bike screen status
		RentBikeController.rentalCode = null;

		// Update rent bike invoice (for our own system)
		int rentCost = rentBikeInvoice.getDeposit() - refundAmount;
		rentBikeInvoice.updateAfterReturnBike(returnStamp, rentCost);

		// Save payment by Interbank
		PaymentTransaction paymentTransaction = new PaymentTransaction(rentBikeInvoice.getRentalCode(),
				card.getCardCode(), card.getOwner(), Constants.RETURN_MESSAGE, refundAmount, returnStamp);
		paymentTransaction.savePaymentTransaction();

		// Update bike used status
		bike.updateStatus(false, newDockID);

		// Update dock capacity
		Dock.updateRemainCapacity(bike.getDockID(), "-1");
	}
}
