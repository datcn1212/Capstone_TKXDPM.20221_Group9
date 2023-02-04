package entity;

import entity.data.PaymentTransactionDAO;

/**
 * @author baonn
 *
 */
public class PaymentTransaction {
	/**
	 * rental code
	 */
	private String rentalCode;
	/**
	 * card code
	 */
	private String cardCode;
	/**
	 * owner
	 */
	private String owner;
	/**
	 * transaction content
	 */
	private String transactionContent;
	/**
	 * amount
	 */
	private int amount;
	/**
	 * date
	 */
	private String daytime;

	/**
	 * construction
	 * @param rentalCode
	 * @param cardCode
	 * @param owner
	 * @param transactionContent
	 * @param amount
	 * @param daytime
	 */
	public PaymentTransaction(String rentalCode, String cardCode, String owner, String transactionContent, int amount,
			String daytime) {
		this.rentalCode = rentalCode;
		this.cardCode = cardCode;
		this.owner = owner;
		this.transactionContent = transactionContent;
		this.amount = amount;
		this.daytime = daytime;
	}

	/**
	 * save payment transaction in database
	 */
	public void savePaymentTransaction() {
		PaymentTransactionDAO.save(rentalCode, cardCode, owner, transactionContent, amount, daytime);
	}
}
