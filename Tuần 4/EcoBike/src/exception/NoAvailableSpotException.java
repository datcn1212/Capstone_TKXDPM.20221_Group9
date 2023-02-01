package exception;

/**
 * @author duydc
 *
 */

public class NoAvailableSpotException extends RuntimeException {
	/**
	 * construction
	 */
	private static final long serialVersionUID = 5025357111420354748L;

	public NoAvailableSpotException() {
		super("ERROR: No available spot in this dock. Please choose other dock to return bike!");
	}
}
