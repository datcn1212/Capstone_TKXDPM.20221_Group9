package subsystem.barcodeconverter.api;

import com.google.gson.JsonObject;

import util.HTTPBinder;

/**
 * @author baonn
 *
 */
public class BarcodeConvertion implements Message {
	/**
	 * barcode
	 */
	private int barcode;
	/**
	 * url to barcode API
	 */
	public final static String URL = "https://barcodeservicebykv2.herokuapp.com/barcode";

	/**
	 * construction
	 * @param barcode
	 */
	public BarcodeConvertion(int barcode) {
		this.barcode = barcode;
	}

	/**
	 * pack a package
	 */
	@Override
	public String pack() {
		JsonObject sentJson = new JsonObject();
		sentJson.addProperty("barcode", String.valueOf(this.barcode));
		return sentJson.toString();
	}

	/**
	 * post a package
	 */
	@Override
	public String post() {
		return String.valueOf(this.barcode);
	}
}
