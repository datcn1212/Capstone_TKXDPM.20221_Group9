package entity.data;

import java.util.ArrayList;

/**
 * @author longld
 *
 */
public class DockDAO {
	/**
	 * @return all information of docks
	 */
	public static ArrayList<ArrayList<String>> getAllDocks() {
		String command = "SELECT * FROM dock";
		return DBBinder.query(command);
	}

	/**
	 * update remain capacity of a dock
	 * @param dockID
	 * @param difference
	 */
	public static void updateRemainCapacity(String dockID, String difference) {
		String command = "UPDATE dock SET remainCapacity = remainCapacity + " + difference + " WHERE dockID=\"" + dockID
				+ "\"";
		DBBinder.execute(command);
	}
}
