package exception;

/**
 * @author baonn
 *
 */
public class InvalidTransactionAmountException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7891797622499220697L;

	/**
	 * construction
	 */
	public InvalidTransactionAmountException() {
		super("ERROR: Invalid Transaction Amount!");
	}
}
