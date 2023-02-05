package view.screen;

import java.io.IOException;

import entity.RentBikeInvoice;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Constants;

/**
 * @author datcn
 *
 */
public class RentBikeInvoiceScreen {
	@FXML
	private Label rentalCode, bikeCode, type, owner, rentTime, returnTime, deposit, rentCost;

	@FXML
	private Button OK;

	/**
	 * set invoice information
	 * @param rentBikeInvoice
	 */
	public void setInvoiceInfo(RentBikeInvoice rentBikeInvoice) {
		System.out.println("display rentbikeinvoice info");
		System.out.println(rentBikeInvoice.getDetail());
		rentalCode.setText(rentBikeInvoice.getRentalCode());
		bikeCode.setText(Integer.toString(rentBikeInvoice.getBikeCode()));
		type.setText(rentBikeInvoice.getType());
		rentCost.setText(Integer.toString(rentBikeInvoice.getRentCost()));
		owner.setText(rentBikeInvoice.getOwner());
		rentTime.setText(rentBikeInvoice.getRentTime());
		returnTime.setText(rentBikeInvoice.getReturnTime());
		deposit.setText(Integer.toString(rentBikeInvoice.getDeposit()));
	}

	/**
	 * process ok
	 */
	@FXML
	public void processOKClick() {
		try {
			System.out.println("Click OKButton");
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.MAIN));
			Parent root = (Parent) loader.load();
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.setTitle("MainScreen");
			stage.show();
			Stage oldStage = (Stage) OK.getScene().getWindow();
			oldStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
