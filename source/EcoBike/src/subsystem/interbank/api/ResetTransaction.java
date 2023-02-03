package subsystem.interbank.api;

import com.google.gson.JsonObject;

import entity.CreditCard;
import util.HTTPBinder;

/**
 * @author hdb
 *
 */
public class ResetTransaction implements Message {
	/**
	 * card
	 */
	private CreditCard card;
	/**
	 * URL for Interbank API
	 */
	public final static String URL = "https://ecopark-system-api.herokuapp.com/api/card/reset-balance";

	/**
	 * construction
	 * @param card
	 */
	public ResetTransaction(CreditCard card) {
		this.card = card;
	}

	/**
	 * pack a message
	 */
	@Override
	public String pack() {
		JsonObject sentJson = new JsonObject();
		sentJson.addProperty("cardCode", card.getCardCode());
		sentJson.addProperty("owner", card.getOwner());
		sentJson.addProperty("cvvCode", card.getCVV());
		sentJson.addProperty("dateExpired", card.getExpiredDate());
		return sentJson.toString();
	}

	/**
	 * patch to URL
	 */
	@Override
	public String patch() {
		HTTPBinder connector = new HTTPBinder();
		return connector.patch(URL, this.pack());
	}
}
