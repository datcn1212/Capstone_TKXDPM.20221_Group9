package subsystem.barcodeconverter;

/**
 * @author baonn
 *
 */
public interface IBarcodeConverter {
	/**
	 * Convert barcode into bike code
	 * 
	 * @param barcode : input barcode
	 * @return bikeCode
	 */
	int convertBarcodeToBikeCode(int barcode);
}
