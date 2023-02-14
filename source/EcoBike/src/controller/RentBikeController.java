package controller;


import subsystem.interbank.IInterbank;
import subsystem.interbank.Interbank;
import util.*;
import java.util.Random;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import entity.Bike;
import entity.CreditCard;
import entity.Dock;
import entity.PaymentTransaction;
import entity.RentBikeInvoice;
import entity.data.RentBikeInvoiceDAO;

/**
 * @author baonn
 *
 */
public class RentBikeController {
	/**
	 * rentalCode: rental code for each renting invoice
	 */
	public static String rentalCode = null;

	/**
	 * static counter for rental code
	 */
	public static int rentalCounter = (new Random()).nextInt(100);

	/**
	 * Process a rent by passing card and bike arguments
	 * 
	 * @param bike : bike wish to rent
	 * @return error code
	 */
	public static String processRentBike(CreditCard card, Bike bike) throws RuntimeException {
		System.out.println("Processing your transaction");
		// Check if card is used
		if (CardController.checkCardInUse(card.getCardCode())) {
			HandleException.getException(Constants.IS_USE);
			return Constants.IS_USE;
		}

		// Initialize for time
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		DateFormat daytime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		int deposit = bike.calculateDeposit();
		IInterbank interbank = new Interbank();
		String code = interbank.processTransaction(card, deposit, "Transaction for a rent", daytime.format(date),
				Constants.PAY);

		if (code.equals(Constants.SUCCESS)) {
			// updateRentBikeStatus(card, bike, deposit, daytime.format(date),
			// daytime.format(returnDate));
			boolean flag = true;
			while(flag) {
				try {
					rentalCode = convertBikeCodeToRentalCode(bike.getBikeID());
					updateRentBikeStatus(card, bike, deposit, daytime.format(date), null);
					flag = false;
				}
				catch(Exception e) {
					continue;
				}
			}
			
		} else {
			HandleException.getException(code);
		}
		return code;
	}

	/**
	 * Update rent bike status
	 * 
	 * @param card
	 * @param bike
	 * @param amount
	 * @param rentStamp
	 * @param returnStamp
	 */
	private static void updateRentBikeStatus(CreditCard card, Bike bike, int amount, String rentStamp, String returnStamp) {
		// Update rent bike screen status
		RentBikeInvoice rentBikeInvoice = new RentBikeInvoice(rentalCode, bike.getBikeID(), bike.getType(),
				card.getOwner(), rentStamp, amount);

		rentBikeInvoice.saveRentBikeInvoice();

		// Save payment by Interbank
		PaymentTransaction paymentTransaction = new PaymentTransaction(rentalCode, card.getCardCode(), card.getOwner(),
				Constants.RENT_MESSAGE, amount, rentStamp);
		paymentTransaction.savePaymentTransaction();

		// Update bike used status
		bike.updateStatus(true, bike.getDockID());

		// Update dock capacity
		Dock.updateRemainCapacity(bike.getDockID(), "1");
	}

	/**
	 * Convert bike code to rental code
	 * 
	 * @param bikeID: id of renting bike Generate rental code
	 * @return rental code
	 */
	public static String convertBikeCodeToRentalCode(int bikeID) {
		return bikeID + String.valueOf(rentalCounter++);
	}

	/**
	 * Get rent bike invoice from rental code
	 * 
	 * @param rentalCode
	 * @return rent bike invoice
	 */
	public static RentBikeInvoice getRentBikeInvoice(String rentalCode) {
		ArrayList<ArrayList<String>> rentBikeInvoice = RentBikeInvoiceDAO.queryByRentalCode(rentalCode);
		assert rentBikeInvoice.size() == 1;

		ArrayList<String> s = rentBikeInvoice.get(0);
		int bikeCode = Integer.parseInt(s.get(1));
		String bikeType = s.get(2);
		String owner = s.get(4);
		String rentTime = s.get(5);
		int deposit = Integer.parseInt(s.get(7));

		return new RentBikeInvoice(rentalCode, bikeCode, bikeType, owner, rentTime, deposit);
	}
}