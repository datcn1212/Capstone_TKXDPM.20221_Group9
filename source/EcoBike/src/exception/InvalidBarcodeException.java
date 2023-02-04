package exception;

/**
 * @author baonn
 *
 */
public class InvalidBarcodeException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -81852587092128207L;

	/**
	 * construction
	 * @param message
	 */
	public InvalidBarcodeException(String message) {
		super(message);
	}
}
