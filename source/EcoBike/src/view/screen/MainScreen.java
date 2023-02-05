package view.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.DockController;
import controller.RentBikeController;
import entity.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;
import view.screen.box.NotificationBox;

/**
 * @author baonn, datcn, haonq
 *
 */
public class MainScreen implements Initializable {
	ArrayList<Dock> docks;

	@FXML
	private ListView<String> docksView;

	@FXML
	private Button rentBikeButton, returnBikeButton, viewDockListButton, viewRentalBikeButton;

	/**
	 * init
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		System.out.println("Initialize main screen");
		docks = DockController.getDocks();
	}

	/**
	 * reset dock information
	 */
	public void resetDockInfo() {
		docks = DockController.getDocks();
	}

	/**
	 * rent button click
	 */
	public void handleRentButtonClick() {
		try {
			if (RentBikeController.rentalCode != null) {
				NotificationBox.display("NotificationBox", "Please return your rented bike before renting a new one!");
				return;
			}
			System.out.println("Click RentBike Button");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RENT_BIKE));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("RENT BIKE");
			stage.show();

			Stage oldStage = (Stage) rentBikeButton.getScene().getWindow();
			oldStage.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * return button click
	 */
	public void handleReturnButtonClick() {
		if (RentBikeController.rentalCode == null) {
			NotificationBox.display("NotificationBox", "You don't rent any bike yet!");
			return;
		} else {
			try {
				System.out.println("Click ReturnBike Button");
				FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RETURN_BIKE));
				Parent root = loader.load();
				ReturnBikeScreen returnBikeScreen = loader.getController();
				returnBikeScreen.setDockInfo(this.docks, RentBikeController.rentalCode);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.setTitle("RETURN BIKE");
				stage.show();

				Stage oldStage = (Stage) returnBikeButton.getScene().getWindow();
				oldStage.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * dock list
	 */
	public void handleViewDockListClick() {
		try {
			System.out.println("Click View Dock List");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.DOCK_LIST));
			Parent root = (Parent) loader.load();
			DockListScreen dockListController = loader.getController();
			dockListController.setDockInfo(this.docks);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("DOCK LIST");
			stage.show();

			Stage oldStage = (Stage) viewDockListButton.getScene().getWindow();
			oldStage.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * current rental bike
	 */
	public void handleViewRentalBikeClick() {
		if (RentBikeController.rentalCode == null) {
			NotificationBox.display("NotificationBox", "You don't rent any bike yet!");
			return;
		} else {
			try {
				System.out.println("Click View Rental Bike Info");
				FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RENTAL_BIKE));
				Parent root = (Parent) loader.load();
				RentalBikeScreen rentalBikeController = loader.getController();
				rentalBikeController.setRentalBikeInfo(RentBikeController.rentalCode);
				Stage stage = new Stage();
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.setScene(new Scene(root));
				stage.setTitle("RENTAL BIKE INFORMATION");
				stage.show();

				Stage oldStage = (Stage) viewDockListButton.getScene().getWindow();
				oldStage.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
