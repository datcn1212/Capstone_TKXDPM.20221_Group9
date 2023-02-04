package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.CardController;
/**
 * 
 * @author baonn
 *
 */
class ValidateExpiredDateTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	 * String, Boolean expiration date of the credit card, expected validation
	 * result
	 */
	@ParameterizedTest
	@CsvSource({ "0100, true", "0199, true", "1200, true", "1299, true", "0000, false", "1300, false", "00000, false",
			"000, false", "a123, false", ", false"

	})

	void validateCcvCode(String expiredDate, boolean expected) {
		boolean isValid = CardController.validateExpiredDate(expiredDate);
		assertEquals(expected, isValid);
	}

}
