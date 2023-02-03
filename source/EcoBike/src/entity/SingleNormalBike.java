package entity;

import java.util.ArrayList;

/**
 * @author hdb
 *
 */
public class SingleNormalBike extends Bike {
	/**
	 * construction
	 */
	public SingleNormalBike() {
		super();
	}

	/**
	 * construction
	 * @param bike information as Array List String
	 */
	public SingleNormalBike(ArrayList<String> bike) {
		super(bike);
		this.setType("single-normal");
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
		return cost;
	}
}
