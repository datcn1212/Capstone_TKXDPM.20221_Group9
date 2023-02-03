package subsystem.interbank;

import entity.CreditCard;
import subsystem.interbank.api.ProcessTransaction;
import subsystem.interbank.api.ResetTransaction;

/**
 * @author anvd
 *
 */
public class Interbank implements IInterbank {
	/**
	 * reset card
	 */
	@Override
	public String reset(CreditCard card) {
		ResetTransaction resetTransaction = new ResetTransaction(card);
		return resetTransaction.patch();
	}

	/**
	 * process transaction
	 */
	@Override
	public String processTransaction(CreditCard card, int amount, String content, String createdAt, String command) {
		ProcessTransaction processTransaction = new ProcessTransaction(card, amount, content, createdAt, command);
		return processTransaction.patch();
	}
}
