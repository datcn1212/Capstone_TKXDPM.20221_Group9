package entity.data;

import java.util.ArrayList;

/**
 * @author hdb
 *
 */
public class RentBikeInvoiceDAO {
	/**
	 * save a rent bike invoice
	 * @param bikeID
	 * @param rentalCode
	 * @param type
	 * @param rentTime
	 * @param returnTime
	 * @param rentBikeCost
	 * @param owner
	 * @param deposit
	 */
	public static void save(int bikeID, String rentalCode, String type, String rentTime, String returnTime,
			int rentBikeCost, String owner, int deposit) {
		String command = "INSERT INTO rentbikeinvoice " + " VALUES " + "(" + '\'' + rentalCode + '\'' + ", " + bikeID
				+ ", " + '\'' + type + '\'' + ", " + rentBikeCost + ", " + '\'' + owner + '\'' + ", " + '\'' + rentTime
				+ '\'' + ", " + '\'' + returnTime + '\'' + ", " + deposit + ")";

		DBBinder.execute(command);
	}

	/**
	 * @param rentalCode
	 * @return rental code
	 */
	public static ArrayList<ArrayList<String>> queryByRentalCode(String rentalCode) {
		String command = "SELECT * FROM rentbikeinvoice WHERE rentalCode=" + '\'' + rentalCode + '\'';
		System.out.println(command);
		return DBBinder.query(command);
	}

	/**
	 * update bike after returning
	 * @param rentalCode
	 * @param rentBikeCost
	 * @param returnTime
	 */
	public static void updateAfterReturnBike(String rentalCode, int rentBikeCost, String returnTime) {
		String command = "UPDATE rentbikeinvoice SET rentBikeCost=" + rentBikeCost + ", returnTime=" + '\'' + returnTime
				+ '\'' + " WHERE rentalCode=" + '\'' + rentalCode + '\'';
		DBBinder.execute(command);
	}
}
