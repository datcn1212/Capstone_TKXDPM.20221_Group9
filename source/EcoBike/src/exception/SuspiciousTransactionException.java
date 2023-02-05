package exception;

/**
 * @author datcn
 *
 */
public class SuspiciousTransactionException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2139094765520288861L;

	/**
	 * construction
	 */
	public SuspiciousTransactionException() {
		super("ERROR: Suspicious Transaction Report!");
	}
}
