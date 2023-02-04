package exception;

/**
 * @author baonn
 *
 */
public class InternalServerErrorException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6243857608638709207L;

	/**
	 * construction
	 */
	public InternalServerErrorException() {
		super("ERROR: Internal Server Error!");
	}

}
