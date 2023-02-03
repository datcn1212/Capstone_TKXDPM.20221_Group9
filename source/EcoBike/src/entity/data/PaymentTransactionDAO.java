package entity.data;

import util.Constants;

/**
 * @author hdb
 *
 */
public class PaymentTransactionDAO {
	/**
	 * save a transaction
	 * @param rentalCode
	 * @param cardCode
	 * @param owner
	 * @param transactionContent
	 * @param amount
	 * @param daytime
	 */
	public static void save(String rentalCode, String cardCode, String owner, String transactionContent, int amount,
			String daytime) {
		String key, sign;
		if (transactionContent.equals(Constants.RENT_MESSAGE)) {
			key = "0";
			sign = "-";
		} else {
			key = "1";
			sign = "+";
		}
		String command = "INSERT INTO paymenttransaction " + "VALUES " + '(' + '\'' + rentalCode + key + '\'' + ", "
				+ '\'' + owner + '\'' + ", " + '\'' + transactionContent + '\'' + ", " + '\'' + rentalCode + '\'' + ", "
				+ '\'' + cardCode + '\'' + ", " + sign + amount + ", " + '\'' + daytime + '\'' + ')';
		DBBinder.execute(command);
	}

	/**
	 * This function for checking card if users is currently renting a bike
	 * 
	 * @param cardCode: cardCode of user card for transaction
	 * @return whether if users is currently renting a bike
	 */
	public static boolean checkCardInUse(String cardCode) {
		String command = "SELECT * FROM paymenttransaction " + "WHERE cardCode = '" + cardCode
				+ "' AND transactionContent = '" + Constants.RENT_MESSAGE + "';";
		if (DBBinder.query(command).size() > 0) {
			command = "SELECT * FROM paymenttransaction, \r\n"
					+ "(SELECT rentalCode as c FROM paymenttransaction, (select max(str_to_date(time, '%Y-%m-%d %T')) as max_time FROM paymenttransaction WHERE transactionContent = \"Rent bike\") as T\r\n"
					+ "WHERE str_to_date(time, '%Y-%m-%d %T') = T.max_time) as newest_rent\r\n"
					+ "WHERE rentalCode = newest_rent.c AND transactionContent = \"Return bike\"";
			if (DBBinder.query(command).size() == 0) {
				return true;
			} else
				return false;
		} else
			return false;
	}
}
