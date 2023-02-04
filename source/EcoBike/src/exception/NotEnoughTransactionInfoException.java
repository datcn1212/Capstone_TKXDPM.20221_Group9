package exception;

/**
 * @author baonn
 *
 */
public class NotEnoughTransactionInfoException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4393741254059584973L;

	/**
	 * construction
	 */
	public NotEnoughTransactionInfoException() {
		super("ERROR: Not Enough Transcation Information");
	}
}
