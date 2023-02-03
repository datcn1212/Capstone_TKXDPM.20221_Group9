package view.screen;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import controller.DockController;
import controller.RentBikeController;
import entity.Bike;
import entity.RentBikeInvoice;
import entity.SingleElectricBike;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;

/**
 * @author hdb
 *
 */
public class RentalBikeScreen {
	@FXML
	private Label bikeCode, type, remainBattery, rentDuration, rentCost;

	@FXML
	private Button OK;

	/**
	 * @param rentalCode: query current bike by rental code
	 */
	public void setRentalBikeInfo(String rentalCode) {
		System.out.println("display rental bike info");

		// Query invoice and rental bike from rental code
		RentBikeInvoice rentBikeInvoice = RentBikeController.getRentBikeInvoice(rentalCode);
		Bike bike = DockController.getBikeFromID(rentBikeInvoice.getBikeCode());
		String bikeType = bike.getType();

		// Initialize for time
		Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();
		DateFormat daytime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date rentTime = null;
		try {
			rentTime = daytime.parse(rentBikeInvoice.getRentTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Caculate rent duration
		long diff = currentTime.getTime() - rentTime.getTime();
		int duration = (int) Math.ceil((double) diff / 60000);

		// Calculate rent cost and refund amount
		int rentBikeCost = bike.caculateRentCost(duration);
		System.out.println("Rent cost amount: " + rentBikeCost);

		// Set up label for showing screen
		bikeCode.setText(Integer.toString(rentBikeInvoice.getBikeCode()));
		type.setText(bikeType);
		if (bikeType.equals("single-electric")) {
			remainBattery.setText(String.valueOf(((SingleElectricBike) bike).getRemainBattery()));
		} else
			remainBattery.setText("Not have battery");
		rentDuration.setText(String.valueOf(duration) + " minutes");
		rentCost.setText(String.valueOf(rentBikeCost));
	}

	/**
	 * ok click
	 */
	@FXML
	public void processOKClick() {
		try {
			System.out.println("user click OKButton");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.MAIN));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("MainScreen");
			stage.show();
			Stage oldStage = (Stage) OK.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
