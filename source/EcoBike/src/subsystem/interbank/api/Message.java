package subsystem.interbank.api;

/**
 * @author baonn
 * Message for Interbank API
 */
public interface Message {
	/**
	 * pack a messgae
	 * @return
	 */
	public String pack();

	/**
	 * patch a package to URL
	 * @return
	 */
	public String patch();
}
