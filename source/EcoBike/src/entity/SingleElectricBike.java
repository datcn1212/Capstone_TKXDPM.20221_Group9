package entity;

import java.util.ArrayList;

/**
 * @author haonq
 *
 */
public class SingleElectricBike extends Bike {
	/**
	 * remain battery
	 */
	private Double remainBattery;
	/**
	 * maximum time
	 */
	private Double maxTime;

	/**
	 * construction
	 */
	public SingleElectricBike() {
		super();
	}

	/**
	 * construction
	 * @param bike info as Array List String
	 */
	public SingleElectricBike(ArrayList<String> bike) {
		super(bike);
		this.setRemainBattery(Double.parseDouble(bike.get(5)));
		this.setMaxTime(Double.parseDouble(bike.get(6)));
		this.setType("single-electric");
	}

	public Double getRemainBattery() {
		return remainBattery;
	}

	public void setRemainBattery(Double remainBattery) {
		this.remainBattery = remainBattery;
	}

	public Double getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Double maxTime) {
		this.maxTime = maxTime;
	}

	@Override
	public ArrayList<String> getBikeInfo() {
		ArrayList<String> result = super.getBikeInfo();
		result.add("MAX TIME AVAILABLE: " + String.valueOf(maxTime));
		result.add("REMAIN BATTERY: " + String.valueOf(remainBattery));
		return result;
	}

	/**
	 * Function to calculate rent cost
	 * 
	 * @param duration: rent time duration (caculated in minutes)
	 * @return rent cost
	 */
	@Override
	public int caculateRentCost(int duration) {
		int cost = 10000;
		if (duration > 30) {
			cost += (int) Math.ceil((float) (duration - 30) / 15) * 3000;
		}
		cost = cost * 3 / 2;
		return cost;
	}
}
