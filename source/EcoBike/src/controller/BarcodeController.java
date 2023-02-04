package controller;

import entity.Bike;
import javafx.util.Pair;
import subsystem.barcodeconverter.*;

/**
 * @author hdb
 *
 */
public class BarcodeController {

	/**
	 * current bike code to use
	 */
	private static int bikeCode;

	/**
	 *
	 * @param barcode : input barcode
	 * @return (boolean, bike code)
	 */
	public static Pair<Boolean, Bike> getBikeFromBarcode(String barcode) {
        IBarcodeConverter bc = new BarcodeConverter();
		boolean flag = false;
		Bike bike = null;
		try {
			int barc = Integer.parseInt(barcode);
            bikeCode = bc.convertBarcodeToBikeCode(barc);
            System.out.println(bikeCode);
			bike = DockController.getBikeFromID(bikeCode);
			flag = (bike == null) ? false : true;

		} catch (NumberFormatException e) {
			System.out.println("Invalid barcode at BarcodeController.checkBarcodeAndGetBikeIfTrue");
		}

		return new Pair<>(flag, bike);
	}
}
