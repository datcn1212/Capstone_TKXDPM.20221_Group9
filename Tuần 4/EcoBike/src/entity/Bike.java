package entity;

import java.util.ArrayList;

import entity.data.BikeDAO;

/**
 * @author longld
 *
 */
public abstract class Bike {
	/**
	 * id of bike
	 */
	private int bikeID;
	/**
	 * status of bike
	 */
	private boolean inUse;
	/**
	 * value of bike
	 */
	private int value;
	/**
	 * number of pedals
	 */
	private int numPedal;
	/**
	 * number of saddles
	 */
	private int numSaddle;
	/**
	 * number of seats
	 */
	private int numSeat;
	/**
	 * id of dock
	 */
	private String dockID;
	/**
	 * license plate
	 */
	private String licensePlate;
	/**
	 * type of bike
	 */
	private String type;

	/**
	 * construction
	 */
	public Bike() {
	}

	/**
	 * construction from array list string from SQL
	 * @param bike
	 */
	public Bike(ArrayList<String> bike) {
		this.setBikeID(Integer.parseInt(bike.get(0)));
		this.setNumPedal(Integer.parseInt(bike.get(2)));
		this.setNumSaddle(Integer.parseInt(bike.get(3)));
		this.setNumSeat(Integer.parseInt(bike.get(4)));
		this.setInUse((Integer.parseInt(bike.get(7)) == 1) ? true : false);
		this.setValue(Integer.parseInt(bike.get(8)));
		this.setLicensePlate(bike.get(9));
		this.setDockID(bike.get(10));
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public void setNumPedal(int numPedal) {
		this.numPedal = numPedal;
	}

	public void setNumSaddle(int numSaddle) {
		this.numSaddle = numSaddle;
	}

	public void setNumSeat(int numSeat) {
		this.numSeat = numSeat;
	}

	public void setBikeID(int bikeID) {
		this.bikeID = bikeID;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setDockID(String dockID) {
		this.dockID = dockID;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getType() {
		return type;
	}

	public int getBikeID() {
		return bikeID;
	}

	public int getValue() {
		return value;
	}

	public int getNumPedal() {
		return numPedal;
	}

	public int getNumSaddle() {
		return numSaddle;
	}

	public int getNumSeat() {
		return numSeat;
	}

	public String getDockID() {
		return dockID;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * Update dock status
	 * 
	 * @param inUse
	 * @param dockID
	 */
	public void updateStatus(boolean inUse, String dockID) {
		this.inUse = inUse;
		BikeDAO.updateStatus(this.bikeID, inUse, dockID);
	}

	/**
	 * calculate deposit of bike
	 * @return int: deposit
	 */
	public int calculateDeposit() {
		return 4 * getValue() / 10;
	}

	/**
	 * @return boolean: status of bike
	 */
	public boolean getInUse() {
		return inUse;
	}

	/**
	 * @return String: general information of bike
	 */
	public String getGeneralInfo() {
		return bikeID + " - " + getType();
	}

	/**
	 * @return result as ArrayList String: general information of bike
	 */
	public ArrayList<String> getBikeInfo() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("ID: " + String.valueOf(bikeID));
		result.add("TYPE: " + this.getType());
		result.add("VALUE: " + String.valueOf(value));
		result.add("NUMBER OF PEDALS: " + String.valueOf(numPedal));
		result.add("NUMBER OF SADDLE: " + String.valueOf(numSaddle));
		result.add("NUMBER OF BICYCLE SEATS: " + String.valueOf(numSeat));
		result.add("DOCK ID: " + dockID);
		result.add("LISCENSE PLATE: " + licensePlate);
		result.add("IN USE: " + (this.inUse ? "YES" : "NO"));
		result.add("DEPOSIT: " + this.calculateDeposit());
		return result;
	}

	/**
	 * Function to calculate rent cost
	 * 
	 * @param duration: rent time duration (caculated in minutes)
	 * @return rent cost
	 */
	public abstract int caculateRentCost(int duration);

	/**
	 * Get Bike (similarly to a bike parser)
	 * 
	 * @param bike
	 * @return bike with exact type 
	 */
	public static final Bike getBike(ArrayList<String> bike) {
		switch (bike.get(1)) {
		case "single-normal":
			return new SingleNormalBike(bike);
	
		case "double-normal":
			return new DoubleNormalBike(bike);
	
		case "single-electric":
			return new SingleElectricBike(bike);
		default:
			throw new IllegalArgumentException("This type of bike is not supported");
		}
	}
}
