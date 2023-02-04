package entity;

/**
 * @author baonn
 * This class is to use for patching package to API server, NOT TO STORE in database
 */
public class InterbankTransaction {
	/**
	 * code of card
	 */
	private String cardCode;
	/**
	 * owner of card
	 */
	private String owner;
	/**
	 * cvv of card
	 */
	private String cvvCode;
	/**
	 * date expired
	 */
	private String dateExpired;
	/**
	 * command: pay/ refund
	 */
	private String command;
	/**
	 * transaction content
	 */
	private String transactionContent;
	/**
	 * amount of transaction
	 */
	private double amount;
	/**
	 * date: YYYY-MM-DD hh:mm:ss
	 */
	private String createdAt;

	/**
	 * construction
	 * @param card
	 * @param command
	 * @param content
	 * @param amount
	 * @param createdAt
	 */
	public InterbankTransaction(CreditCard card, String command, String content, int amount, String createdAt) {
		this.setCardCode(card.getCardCode());
		this.setOwner(card.getOwner());
		this.setCvvCode(card.getCVV());
		this.setDateExpired(card.getExpiredDate());
		this.setCommand(command);
		this.setTransactionContent(content);
		this.setAmount(amount);
		this.setCreatedAt(createdAt);
	}

	public String getCardCode() {
		return cardCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public void setCvvCode(String cvvCode) {
		this.cvvCode = cvvCode;
	}

	public void setDateExpired(String dateExpired) {
		this.dateExpired = dateExpired;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public void setTransactionContent(String transactionContent) {
		this.transactionContent = transactionContent;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getOwner() {
		return owner;
	}

	public String getCvvCode() {
		return cvvCode;
	}

	public String getDateExpired() {
		return dateExpired;
	}

	public String getCommand() {
		return command;
	}

	public String getTransactionContent() {
		return transactionContent;
	}

	public String getCreatedAt() {
		return createdAt;
	}
}
