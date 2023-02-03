package subsystem.interbank;

import entity.CreditCard;


/**
 * @author hdb
 *
 */
public interface IInterbank {
	/**
	 * process transaction
	 * @param card
	 * @param amount
	 * @param content
	 * @param createdAt
	 * @param command
	 * @return
	 */
	String processTransaction(CreditCard card, int amount, String content, String createdAt, String command);

	/**
	 * reset card
	 * @param card
	 * @return
	 */
	String reset(CreditCard card);
}
