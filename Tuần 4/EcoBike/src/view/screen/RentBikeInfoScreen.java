package view.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Bike;
import controller.DockController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;
import view.screen.box.NotificationBox;

/**
 * @author duydc
 *
 */
public class RentBikeInfoScreen implements Initializable {
	@FXML
	private ListView<String> bikeInfo;

	@FXML
	private TextArea textArea;

	@FXML
	private Button confirmButton, backButton;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}

	/**
	 * set bike information
	 * @param bike
	 * @param stage
	 */
	public void setBikeInfo(Bike bike, Stage stage) {
		for (String info : bike.getBikeInfo()) {
			bikeInfo.getItems().add(info);
		}

		confirmButton.setOnAction(e -> {
			if (!DockController.checkBikeIsInUse(bike.getBikeID()))
				displayCardScreen(bike);
			else
				NotificationBox.display("NotificationBox", "This bike is currently in served!");

		});
	}

	/**
	 * display card screen
	 * @param bike
	 */
	public void displayCardScreen(Bike bike) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.CARD_INFO));
			Parent root = loader.load();
			CardInfoScreen cardInfoScreen = loader.getController();
			Stage stage = new Stage();
			stage.setTitle("EcoBike");
			stage.setScene(new Scene(root));
			cardInfoScreen.processRentBike(bike, stage);
			stage.show();

			Stage oldStage = (Stage) backButton.getScene().getWindow();
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
			System.out.println("user click BackButton");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RENT_BIKE));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("RentBikeScreen");
			stage.show();

			Stage oldStage = (Stage) backButton.getScene().getWindow();
			oldStage.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
