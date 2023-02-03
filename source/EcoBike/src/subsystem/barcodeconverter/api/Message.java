package subsystem.barcodeconverter.api;

/**
 * @author duydc
 *
 */
public interface Message {
	/**
	 * pack a message
	 * @return
	 */
	public String pack();

	/**
	 * post a message
	 * @return
	 */
	public String post();
}
