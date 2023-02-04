package entity;

/**
 * @author baonn
 *
 */
public class CreditCard extends GenericCard{
	/**
	 * static variable holds the card
	 */
	private static CreditCard card = new CreditCard("kstn_group2_2021", "Group 2", "494", "1125");
	/**
	 * code of card
	 */
	private String cardCode;
	/**
	 * owner
	 */
	private String owner;
	/**
	 * cvv
	 */
	private String CVV;
	/**
	 * expired date
	 */
	private String expiredDate;

	/**
	 * construction
	 * @param cardCode
	 * @param owner
	 * @param cvv
	 * @param expiredDate
	 */
	public CreditCard(String cardCode, String owner, String cvv, String expiredDate) {
		this.cardCode = cardCode;
		this.owner = owner;
		this.CVV = cvv;
		this.expiredDate = expiredDate;
	}

	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCVV() {
		return CVV;
	}

	public void setCVV(String CVV) {
		this.CVV = CVV;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public static CreditCard getCard() {
		return card;
	}
}
