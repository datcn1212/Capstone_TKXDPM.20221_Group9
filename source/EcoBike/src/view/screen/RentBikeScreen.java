package view.screen;

import java.io.IOException;

import controller.BarcodeController;
import controller.RentBikeController;
import entity.Bike;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import util.Constants;
import view.screen.box.NotificationBox;

/**
 * @author hdb
 *
 */
public class RentBikeScreen {
	@FXML
	private TextField barcode;

	@FXML
	private Button backButton;

	/**
	 * rent bike
	 * @param event
	 */
	@FXML
	void handleButtonRentBike(ActionEvent event) {
		String message = barcode.getText();
		if (message.isEmpty()) {
			NotificationBox.display("NotificationBox", "Please enter your barcode");
		} else {
			if (RentBikeController.rentalCode == null) {
				Pair<Boolean, Bike> p = BarcodeController.getBikeFromBarcode(message);
				if (p.getKey()) {
					System.out.println(p.getValue());
					display(p.getValue());

				} else {
					NotificationBox.display("NotificationBox", "Invalid Barcode");
				}
			} else {
				NotificationBox.display("NotificationBox", "Please return your bike before renting a new one!");
			}
		}
	}

	/**
	 * display a bike
	 * @param bike
	 */
	public void display(Bike bike) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RENT_BIKE_INFO));
			Parent root = loader.load();
			RentBikeInfoScreen rentBikeInfoScreen = loader.getController();
			Stage stage = new Stage();
			stage.setTitle("ViewRentBikeInfoScreen");
			rentBikeInfoScreen.setBikeInfo(bike, stage);
			stage.setScene(new Scene(root));
			stage.show();

			Stage oldStage = (Stage) barcode.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * back button
	 */
	public void handleBackButtonClick() {
		try {
			System.out.println("user click Back Button");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.MAIN));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("MainScreen");
			stage.show();

			Stage oldStage = (Stage) backButton.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
