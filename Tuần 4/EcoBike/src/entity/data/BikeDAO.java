package entity.data;

import java.util.ArrayList;

/**
 * @author longld
 *
 */
public class BikeDAO {
	/**
	 * update status of bike
	 * @param bikeID
	 * @param inUse
	 * @param dockID
	 */
	public static void updateStatus(int bikeID, boolean inUse, String dockID) {
		int status = inUse ? 1 : 0;
		String command = "UPDATE bike SET inUse=" + status + ", dockID=" + '\'' + dockID + '\'' + " WHERE bikeID="
				+ bikeID;
		DBBinder.execute(command);
	}

	/**
	 * get all information of bikes in a dock
	 * @param dockID
	 * @return
	 */
	public static ArrayList<ArrayList<String>> queryWithDockID(String dockID) {
		ArrayList<ArrayList<String>> s = new ArrayList<>();
		String command = "SELECT * from bike WHERE dockID=" + '\'' + dockID + '\'';
		s = DBBinder.query(command);
		return s;
	}

	/**
	 * get information of a bike knowing bike code
	 * @param bikeCode
	 * @return
	 */
	public static ArrayList<ArrayList<String>> queryWithBikeCode(int bikeCode) {
		ArrayList<ArrayList<String>> s = new ArrayList<>();
		String command = "SELECT * from bike WHERE bikeID=" + '\'' + bikeCode + '\'';
		s = DBBinder.query(command);
		System.out.println(s);
		return s;
	}

	/**
	 * @return All bikes
	 */
	public static ArrayList<ArrayList<String>> getBikes() {
		ArrayList<ArrayList<String>> s = new ArrayList<>();
		String command = "SELECT * from bike";
		s = DBBinder.query(command);
		return s;
	}

}
