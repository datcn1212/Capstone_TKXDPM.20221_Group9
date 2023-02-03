package entity;

import java.util.ArrayList;

import entity.data.DockDAO;

/**
 * @author hdb
 *
 */
public class Dock {
	/**
	 * id of dock
	 */
	private String dockID;
	/**
	 * name
	 */
	private String name;
	/**
	 * address
	 */
	private String address;
	/**
	 * area
	 */
	private String area;
	/**
	 * number of docking points
	 */
	private int numberOfDockingPoints;
	/**
	 * bikes containing
	 */
	private ArrayList<Bike> bikes;

	/**
	 * construction
	 * @param dockID
	 * @param name
	 * @param address
	 * @param area
	 * @param numberOfDockingPoints
	 * @param bikes: bikes containing as Array list Bike
	 */
	public Dock(String dockID, String name, String address, String area, int numberOfDockingPoints,
			ArrayList<Bike> bikes) {
		this.dockID = dockID;
		this.name = name;
		this.address = address;
		this.area = area;
		this.numberOfDockingPoints = numberOfDockingPoints;
		this.bikes = bikes;
	}

	public ArrayList<Bike> getBikes() {
		return bikes;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getArea() {
		return area;
	}

	public String getDockID() {
		return dockID;
	}

	public void setDockID(String dockID) {
		this.dockID = dockID;
	}

	public int getNumberOfDockingPoints() {
		return numberOfDockingPoints;
	}

	public int getNumberOfBikes() {
		return this.bikes.size();
	}

	/**
	 * @return int: number of bikes not in use
	 */
	public int getNumberOfBikesNotInUse() {
		int count = 0;

		for (Bike bike : this.getBikes()) {
			if (!bike.getInUse())
				count += 1;
		}

		return count;
	}

	/**
	 * @param bikeInfo: as a String
	 * @return Bike in this dock
	 */
	public Bike getBikeFromString(String bikeInfo) {
		for (Bike bike : this.getBikes()) {
			String s = bike.getGeneralInfo();
			if (bikeInfo.equals(s)) {
				return bike;
			}
		}
		return null;
	}

	/**
	 * string convertion
	 */
	@Override
	public String toString() {
		return String.format("Name: %s, available spots: %s, address: %s", name,
				Integer.toString(this.getNumberOfDockingPoints() - this.getNumberOfBikesNotInUse()), address);
	}

	/**
	 * @return table of all docks
	 */
	public static ArrayList<ArrayList<String>> getDockTable() {
		return DockDAO.getAllDocks();
	}

	/**
	 * @return boolean: if this dock has spare points
	 */
	public boolean checkSparePoints() {
		return this.getNumberOfDockingPoints() - this.getBikes().size() > 0;
	}

	/**
	 * @return general information of dock
	 */
	public ArrayList<String> getDockInfo() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("DOCK ID: " + dockID);
		result.add("NAME: " + name);
		result.add("ADDRESS: " + address);
		result.add("NUMBER OF DOCKING POINTS: " + Integer.toString(getNumberOfDockingPoints()));
		result.add("NUMBER OF BIKES: " + Integer.toString(getNumberOfBikesNotInUse()));
		return result;
	}

	/**
	 * @param dockID: dock id want to change
	 * @param difference: difference, e.g. +1, -1, ...
	 */
	public static void updateRemainCapacity(String dockID, String difference) {
		DockDAO.updateRemainCapacity(dockID, difference);
	}
}
