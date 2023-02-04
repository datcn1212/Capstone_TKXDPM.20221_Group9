package subsystem.interbank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entity.CreditCard;
import entity.InterbankTransaction;
import util.Constants;
import util.HTTPBinder;
import util.HashFunction;

/**
 * @author hdb
 *
 */
public class ProcessTransaction implements Message {
	/**
	 * card
	 */
	private CreditCard card;
	/**
	 * amount
	 */
	private int amount;
	/**
	 * content
	 */
	private String content;
	/**
	 * created at 
	 */
	private String createdAt;
	/**
	 * command: pay/ refund
	 */
	private String command;
	/**
	 * InterbankTransaction to abstract real life transaction
	 */
	private InterbankTransaction interbankTransaction;

	/**
	 * URL for API
	 */
	private static final String URL = "https://ecopark-system-api.herokuapp.com/api/card/processTransaction";
	/**
	 * Version for API
	 */
	@SuppressWarnings("unused")
	private static final String VERSION = "1.0.1";

	/**
	 * construction
	 * @param card
	 * @param amount
	 * @param content
	 * @param createdAt
	 * @param command
	 */
	public ProcessTransaction(CreditCard card, int amount, String content, String createdAt, String command) {
		this.card = card;
		this.amount = amount;
		this.content = content;
		this.createdAt = createdAt;
		this.command = command;
		this.interbankTransaction = new InterbankTransaction(this.card, this.command, this.content, this.amount,
				this.createdAt);
	}

	/**
	 * pack message
	 */
	@Override
	public String pack() {
		JsonObject message = new JsonObject();
		try {
			String transact = new ObjectMapper().writeValueAsString(interbankTransaction);
			@SuppressWarnings("deprecation")
			JsonObject transaction = new JsonParser().parse(transact).getAsJsonObject();
			String hashCode = HashFunction.hashTransaction(Constants.SECRET_KEY, interbankTransaction).toString();

			message.addProperty("version", "1.0.1");
			message.add("transaction", transaction);
			message.addProperty("appCode", Constants.APP_CODE);
			message.addProperty("hashCode", hashCode);
		} catch (Exception e) {
			System.out.println("Exception at utils.api.ProcessTransaction");
		}
		return message.toString();

	}

	/**
	 * patch to URL
	 */
	@Override
	public String patch() {
		HTTPBinder connector = new HTTPBinder();
		System.out.println(this.pack());
		return connector.patch(URL, this.pack());
	}
}
