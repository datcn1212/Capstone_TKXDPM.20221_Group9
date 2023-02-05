package view.screen;

import java.io.IOException;
import java.util.ArrayList;

import controller.DockController;
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
public class DockListScreen {
	public static boolean reset = false;
	ArrayList<Dock> docks;

	@FXML
	private ListView<String> docksView;

	@FXML
	private Button backButton;

	/**
	 * set Dock information
	 * @param docks
	 */
	public void setDockInfo(ArrayList<Dock> docks) {
		this.docks = docks;
		// show list of docks
		for (Dock dock : docks) {
			docksView.getItems().add(dock.toString());
		}

		// listen when user double click on the dock in listview => show ViewDockScreen
		docksView.setOnMouseClicked(click -> {
			if (click.getClickCount() == 2) {
				handleDoubleClickOnDockList();
			}
		});
	}

	/**
	 * double click on dock list
	 */
	private void handleDoubleClickOnDockList() {
		System.out.println("User double on a dock");
		String dockInfo = docksView.getSelectionModel().getSelectedItem();
		Dock dock = DockController.getDockFromString(dockInfo);

		showViewDockScreen(dock);
	}

	/**
	 * dock screen
	 * @param dock
	 */
	public void showViewDockScreen(Dock dock) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.DOCK));
			Parent root = loader.load();
			DockScreen viewDockController = loader.getController();
			viewDockController.setDockInfo(dock);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("ViewDockScreen");
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
}
