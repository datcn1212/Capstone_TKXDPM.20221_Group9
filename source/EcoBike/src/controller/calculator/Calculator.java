package controller.calculator;

import entity.Bike;
/**
 * Interface calculator to implement Strategy design pattern
 * @author hdb
 *
 */
public interface Calculator {
	public int calculateRentBike(Bike bike, int time);
}
