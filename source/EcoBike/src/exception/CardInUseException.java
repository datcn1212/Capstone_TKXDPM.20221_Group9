package exception;

/**
 * @author anvd
 *
 */
public class CardInUseException extends RuntimeException {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8566169724044154463L;

	/**
	 * construction
	 */
	public CardInUseException() {
		super("ERROR: Card is currently in used!");
	}
}
