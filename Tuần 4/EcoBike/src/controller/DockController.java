package controller;

import entity.Bike;
import entity.Dock;
import entity.data.BikeDAO;

import java.util.ArrayList;

/**
 * @author longld
 *
 */
public class DockController {
	
	/**
	 * Get all Docks info
	 * 
	 * @return docks: information of all docks
	 */
	public static ArrayList<Dock> getDocks() {
		ArrayList<Dock> docks = new ArrayList<>();
		ArrayList<ArrayList<String>> dockTable = Dock.getDockTable();
		for (ArrayList<String> row : dockTable) {
			String dockID = row.get(0);
			String name = row.get(1);
			String area = row.get(2);
			String address = row.get(3);
			int numberOfDockingPoints = Integer.parseInt(row.get(5));
			ArrayList<Bike> bikes = getBikes(dockID);
			Dock dock = new Dock(dockID, name, address, area, numberOfDockingPoints, bikes);
			docks.add(dock);
		}
		return docks;
	}

	/**
	 * Get all bikes info in a dock
	 * 
	 * @param dockID
	 * @return bikes: information of all bikes
	 */
	public static ArrayList<Bike> getBikes(String dockID) {
		ArrayList<ArrayList<String>> bikeTable = BikeDAO.queryWithDockID(dockID);
		ArrayList<Bike> bikes = new ArrayList<>();
		for (ArrayList<String> row : bikeTable) {
			Bike bike = Bike.getBike(row);
			bikes.add(bike);
		}
		return bikes;
	}

	/**
	 * Get Dock from String
	 * 
	 * @param dockInfo
	 * @return dock
	 */
	public static Dock getDockFromString(String dockInfo) {
		for (Dock dock : getDocks()) {
			String s = dock.toString();
			if (dockInfo.equals(s)) {
				return dock;
			}
		}
		return null;
	}

	/**
	 * Get bike from ID
	 * 
	 * @param id
	 * @return bike
	 */
	public static Bike getBikeFromID(int id) {
		for (ArrayList<String> bikeString : BikeDAO.getBikes()) {
			Bike bike = Bike.getBike(bikeString);
			if (bike.getBikeID() == id) {
				return bike;
			}
		}
		return null;
	}

	/**
	 * check whether a bike is being rent
	 * 
	 * @param id
	 * @return boolean, default is true
	 */
	public static boolean checkBikeIsInUse(int id) {
		for (ArrayList<String> bikeString : BikeDAO.getBikes()) {
			Bike bike = Bike.getBike(bikeString);
			if (bike.getBikeID() == id) {
				return bike.getInUse();
			}
		}
		return true;
	}
}
