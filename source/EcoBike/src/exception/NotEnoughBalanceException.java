package exception;

/**
 * @author datcn
 *
 */
public class NotEnoughBalanceException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6163521882277888525L;

	/**
	 * construction
	 */
	public NotEnoughBalanceException() {
		super("ERROR: Not enough balance in card!");
	}

}
