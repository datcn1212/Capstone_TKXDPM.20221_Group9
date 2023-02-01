
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.Constants;

/**
 * @author hdb
 * Main app
 */
public class App extends Application {
	/**
	 * Start the application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource(Constants.LOBBY));
		primaryStage.setTitle("EcoBike");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	/**
	 * Main program
	 * @param args: arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}