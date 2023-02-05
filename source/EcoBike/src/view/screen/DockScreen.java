package view.screen;

import java.io.IOException;
import java.util.ArrayList;

import entity.Bike;
import entity.Dock;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;

/**
 * @author baonn
 *
 */
public class DockScreen {
	private Dock dock;

	@FXML
	private ListView<String> bikesView;

	@FXML
	private ListView<String> dockInfo;

	@FXML
	private Button backButton;

	/**
	 * click back
	 */
	public void handleBackButtonClick() {
		try {
			System.out.println("Click BackButton");
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

	/**
	 * double click on bike list
	 */
	private void handleDoubleClickOnBikeList() {
		System.out.println("User double on a bike");
		String bikeInfo = bikesView.getSelectionModel().getSelectedItem();
		Bike bike = dock.getBikeFromString(bikeInfo);

		showViewBikeScreen(bike, dock);
	}

	/**
	 * set dock info
	 * @param dock
	 */
	public void setDockInfo(Dock dock) {
		System.out.println("initialize dockInfo and bikesInfo ");
		this.dock = dock;

		for (String s : dock.getDockInfo())
			dockInfo.getItems().add(s);
		ArrayList<Bike> bikes = dock.getBikes();

		for (Bike bike : bikes)
			if (!bike.getInUse())
				bikesView.getItems().add(bike.getGeneralInfo());

		System.out.println("default initialize ViewDockScreen");

		bikesView.setOnMouseClicked(click -> {
			if (click.getClickCount() == 2) {
				handleDoubleClickOnBikeList();
			}
		});
	}

	/**
	 * bike screen
	 * @param bike
	 * @param dock
	 */
	private void showViewBikeScreen(Bike bike, Dock dock) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.BIKE_DETAIL));
			Parent root = loader.load();
			BikeDetailScreen viewBikeController = loader.getController();
			viewBikeController.setBikeInfo(bike, dock);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("ViewBikeScreen");
			stage.show();

			Stage oldStage = (Stage) dockInfo.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
