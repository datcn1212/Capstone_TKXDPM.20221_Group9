// Config arguments
//--module-path .\lib\javafx-sdk-11.0.2\lib --add-modules
//javafx.controls,javafx.fxml

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

/**
 * @author baonn
 * Main app
 */
public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.LOBBY));
		primaryStage.setTitle("ECOBIKE");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}