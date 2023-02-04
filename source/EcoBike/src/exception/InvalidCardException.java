package exception;


/**
 * @author baonn
 *
 */
public class InvalidCardException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3631642295432960723L;

	/**
	 * construction
	 */
	public InvalidCardException() {
		super("ERROR: Invalid card!");
	}
}
