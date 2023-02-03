package exception;

/**
 * @author duydc
 *
 */

public class InvalidCardInfoException extends RuntimeException {
	/**
	 * construction
	 */
	private static final long serialVersionUID = -3631642295432960723L;

	public InvalidCardInfoException() {
		super("ERROR: Invalid card info!");
	}
}
