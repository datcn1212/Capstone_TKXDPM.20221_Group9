package exception;

/**
 * @author anvd
 *
 */
public class InvalidVersionException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 6545671771519226710L;

	/**
	 * construction
	 */
	public InvalidVersionException() {
		super("ERROR: Invalid Version Information!");
	}
}
