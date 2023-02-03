package subsystem.barcodeconverter.api;

/**
 * @author hdb
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
