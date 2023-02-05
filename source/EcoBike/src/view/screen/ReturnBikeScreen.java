package view.screen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.ReturnBikeController;
import entity.CreditCard;
import entity.Dock;
import entity.RentBikeInvoice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Pair;
import util.Constants;
import util.HandleException;
import view.screen.box.ConfirmBox;
import view.screen.box.NotificationBox;

/**
 * @author datcn
 *
 */
public class ReturnBikeScreen implements Initializable {
	private ArrayList<Dock> docks;
	private String rentalCode;

	@FXML
	private Button backButton;

	@FXML
	private ListView<String> docksView;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("Initialize ReturnBikeScreen");
	}

	/**
	 * double click on dock list
	 */
	private void handleDoubleClickOnDockList() {
		System.out.println("User double click on a dock");
		String dockInfo = docksView.getSelectionModel().getSelectedItem();
		Dock dock = getDockFromString(dockInfo);
		assert dock != null;

		if (!dock.checkSparePoints()) {
			NotificationBox.display("ERROR", "Current dock has no available spot");
			HandleException.getException(Constants.NOT_AVAILABLE);
		}
		else {
			boolean confirmReturnBike = ConfirmBox.display("ConfirmBox", "Do you confirm to return bike to this dock?");
			if (confirmReturnBike) {
				CreditCard card = getCardInfoForReturnBike();
				if (card != null) {
					processReturnBike(rentalCode, card, dock);
				}
			}
		}
	}

	/**
	 * @return Card: card for return bike
	 */
	private CreditCard getCardInfoForReturnBike() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.CARD_INFO_FOR_RETURN));
			Parent root = loader.load();

			CardInfoScreenForReturnBike cardInfoScreenForReturnBike = loader.getController();

			Stage stageCard = new Stage();
			stageCard.setTitle("CardInfoForReturnBike");
			stageCard.setScene(new Scene(root));
			cardInfoScreenForReturnBike.confirmCard(stageCard);
			stageCard.showAndWait();

			CreditCard card = cardInfoScreenForReturnBike.getCardInfo();

			return card;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * process return bike
	 * @param rentalCode
	 * @param card
	 * @param d: dock
	 */
	private void processReturnBike(String rentalCode, CreditCard card, Dock d) {
		try {
			Pair<String, RentBikeInvoice> result = ReturnBikeController.processReturnBike(rentalCode, card,
					d.getDockID());
			@SuppressWarnings("unused")
			String respondCode = result.getKey();
			RentBikeInvoice rentBikeInvoice = result.getValue();

			NotificationBox.display("Refund", "Successful transaction, your bike is returned!");
			showRentBikeInvoiceInfo(rentBikeInvoice);
			Stage stage = (Stage) docksView.getScene().getWindow();
			stage.close();
		} catch (RuntimeException e) {
			NotificationBox.display("Return bike", "Invalid transaction: " + e.getMessage());
		}

	}

	/**
	 * rent bike invoice information
	 * @param rentBikeInvoice
	 */
	private void showRentBikeInvoiceInfo(RentBikeInvoice rentBikeInvoice) {
		System.out.println("showRentBikeInvoiceInfo");
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.RENT_BIKE_INVOICE));
			Parent root = loader.load();

			RentBikeInvoiceScreen rentBikeInvoiceScreenController = loader.getController();
			rentBikeInvoiceScreenController.setInvoiceInfo(rentBikeInvoice);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("RentBikeInvoiceInfo");
			stage.show();

			Stage oldStage = (Stage) docksView.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * set dock information
	 * @param d: Dock
	 * @param rentalCode
	 */
	public void setDockInfo(ArrayList<Dock> d, String rentalCode) {
		docks = d;
		this.rentalCode = rentalCode;

		for (Dock dock : docks) {
			docksView.getItems().add(dock.toString());
		}

		docksView.setOnMouseClicked(click -> {
			if (click.getClickCount() == 2) {
				handleDoubleClickOnDockList();
			}
		});
	}

	/**
	 * @param dockInfo
	 * @return Dock: dock want to get from string
	 */
	private Dock getDockFromString(String dockInfo) {

		for (Dock dock : docks) {
			String s = dock.toString();
			if (dockInfo.equals(s)) {
				return dock;
			}
		}
		return null;
	}

	/**
	 * click back
	 */
	public void handleBackButtonClick() {
		try {
			System.out.println("--Click back button--");
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
