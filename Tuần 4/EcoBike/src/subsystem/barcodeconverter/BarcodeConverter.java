package subsystem.barcodeconverter;

import subsystem.barcodeconverter.api.BarcodeConvertion;

/**
 * @author duydc
 *
 */
public class BarcodeConverter implements IBarcodeConverter{
	/**
	 * convert barde to bike code
	 */
	public int convertBarcodeToBikeCode(int barcode){
		BarcodeConvertion barcodeConverter = new BarcodeConvertion(barcode);
		return Integer.parseInt(barcodeConverter.post());
	}
}
