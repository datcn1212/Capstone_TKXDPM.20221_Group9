package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import entity.SingleNormalBike;
/**
 * 
 * @author baonn
 *
 */
class SNBikeCalculateRentCostTest {
	private SingleNormalBike singleNormalBike;

	@BeforeEach
	void setUp() throws Exception {
		singleNormalBike = new SingleNormalBike();
	}

	/*
	 * Int, Int Rent duration (in minutes), expected rent cost
	 */
	@ParameterizedTest
	@CsvSource({ "1, 10000", "30, 10000", "31, 13000", "45, 13000", "46, 16000", "60, 16000", "70, 19000" })

	void testSNBikeCalculateRentCost(int duration, int expected) {
		assertEquals(singleNormalBike.caculateRentCost(duration), expected);
	}
}
