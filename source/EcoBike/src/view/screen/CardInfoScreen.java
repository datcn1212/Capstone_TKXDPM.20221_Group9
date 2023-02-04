package view.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.CardController;
import controller.RentBikeController;
import entity.Bike;
import entity.CreditCard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;
import util.HandleException;
import view.screen.box.NotificationBox;

/**
 * @author baonn
 *
 */
public class CardInfoScreen implements Initializable {
	/**
	 * card
	 */
	public static CreditCard card = CreditCard.getCard();

	/**
	 * some text field on screen
	 */
	@FXML
	private TextField cardCode, owner, ccvCode, expDate;

	/**
	 * some buttons
	 */
	@FXML
	private Button confirmButton, cancelButton;

	/**
	 * init
	 */
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		cardCode.setText(card.getCardCode());
		owner.setText(card.getOwner());
		ccvCode.setText(card.getCVV());
		expDate.setText(card.getExpiredDate());
	}

	/**
	 * process rent bike
	 * @param bike
	 * @param cardInfoStage
	 */
	public void processRentBike(Bike bike, Stage cardInfoStage) {
		confirmButton.setOnAction(e -> {
			if (cardCode.getText().isEmpty() || owner.getText().isEmpty() || ccvCode.getText().isEmpty()
					|| expDate.getText().isEmpty()) {
				NotificationBox.display("Notification", "Please fill in all information!");
			} else {
				if (!CardController.validateCardInfo(cardCode.getText(), owner.getText(), ccvCode.getText(),
						expDate.getText())) {
					NotificationBox.display("Notification", "Information entered isn't in right format!");
					HandleException.getException(Constants.INVALID_CARD_INFO);
				} else {
					card = new CreditCard(cardCode.getText(), owner.getText(), ccvCode.getText(), expDate.getText());
					try {
						@SuppressWarnings("unused")
						String respondCode = RentBikeController.processRentBike(card, bike);
						NotificationBox.display("RentalCode",
								"Successful transaction, your rental code is: " + RentBikeController.rentalCode);
						cardInfoStage.close();
						// Back to main screen
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.MAIN));
							Parent root = (Parent) loader.load();
							MainScreen mainScreenController = loader.getController();
							mainScreenController.resetDockInfo();
							Stage stage = new Stage();
							stage.initModality(Modality.APPLICATION_MODAL);
							stage.setScene(new Scene(root));
							stage.setTitle("MainScreen");
							stage.show();
						} catch (IOException ioe) {
							ioe.printStackTrace();
						}
					} catch (RuntimeException paymentException) {
						NotificationBox.display("RentalCode", "Invalid transaction: " + paymentException.getMessage());
					}
				}
			}
		});
		cancelButton.setOnAction(e -> {
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RENT_BIKE_INFO));
				Parent root = loader.load();
				RentBikeInfoScreen rentBikeInfoScreen = loader.getController();
				Stage stage = new Stage();
				stage.setTitle("ViewRentBikeInfoScreen");
				rentBikeInfoScreen.setBikeInfo(bike, stage);
				stage.setScene(new Scene(root));
				stage.show();

				Stage oldStage = (Stage) cancelButton.getScene().getWindow();
				oldStage.close();
			} catch (IOException f) {
				f.printStackTrace();
			}
		});
	}
}
