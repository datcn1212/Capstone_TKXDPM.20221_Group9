package view.screen;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;

/**
 * @author baonn
 *
 */
public class Lobby {
	public static boolean reset = false;

	@FXML
	public Button closeButton;

	/**
	 * proceed to main screen
	 */
	public void handleMainScreenClick() {
		try {
			System.out.println("Click on getting started!");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.MAIN));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("MAIN SCREEN");
			stage.show();
			Stage oldStage = (Stage) closeButton.getScene().getWindow();
			oldStage.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
