package controller.calculator;

import entity.Bike;
/**
 * Interface calculator to implement Strategy design pattern
 * @author baonn
 *
 */
public interface Calculator {
	public int calculateRentBike(Bike bike, int time);
}
