package entity;

import java.util.ArrayList;

/**
 * @author baonn
 *
 */
public class DoubleNormalBike extends Bike {
	/**
	 * construction
	 */
	public DoubleNormalBike() {
		super();
	}

	/**
	 * construction
	 * @param bike: as Array List String
	 */
	public DoubleNormalBike(ArrayList<String> bike) {
		super(bike);
		this.setType("double-normal");
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
