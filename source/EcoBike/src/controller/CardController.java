package controller;

import entity.data.PaymentTransactionDAO;

/**
 * @author datcn
 *
 */
public class CardController {
	/**
	 * NOTE: this method can only check if the card is used for RENTING, other
	 * checks will not work
	 * 
	 * @param cardCode
	 * @return
	 */
	public static boolean checkCardInUse(String cardCode) {
		return PaymentTransactionDAO.checkCardInUse(cardCode);
	}

	/**
	 * Validate format of infor entered for create card
	 * 
	 * @param carCode:     code of the credit card
	 * @param owner:       name of the owner of the credit card
	 * @param ccvCode:     card security code
	 * @param expiredDate: expiration date of the credit card
	 * @return right format (true) and otherwise (false)
	 */
	public static boolean validateCardInfo(String carCode, String owner, String ccvCode, String expiredDate) {
		if (validateCardCode(carCode) && validateOwner(owner) && validateCcvCode(ccvCode)
				&& validateExpiredDate(expiredDate)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Validate cardcode
	 * 
	 * @param cardCode
	 * @return right format (true) and otherwise (false)
	 */
	public static boolean validateCardCode(String cardCode) {
		if ((cardCode == null) || (!cardCode.matches("[a-z0-9_]+")))
			return false;
		return true;
	}

	/**
	 * Validate Owner
	 * 
	 * @param owner
	 * @return  right format (true) and otherwise (false)
	 */
	public static boolean validateOwner(String owner) {
		if ((owner == null) || (!owner.matches("[A-Za-z0-9\\s]+")))
			return false;
		return true;
	}

	/**
	 * Validate cvv code
	 * 
	 * @param cvvCode
	 * @return right format (true) and otherwise (false)
	 */
	public static boolean validateCcvCode(String cvvCode) {
		if ((cvvCode == null) || (!cvvCode.matches("[0-9]+"))) {
			return false;
		}
		if (cvvCode.length() == 3)
			return true;
		else 
			return false;
	}

	/**
	 * Validate Expired Date
	 * 
	 * @param expiredDate
	 * @return right format (true) and otherwise (false)
	 */
	public static boolean validateExpiredDate(String expiredDate) {
		if ((expiredDate == null) || (!expiredDate.matches("[0-9]+"))) {
			return false;
		}
		return true;
	}
}
