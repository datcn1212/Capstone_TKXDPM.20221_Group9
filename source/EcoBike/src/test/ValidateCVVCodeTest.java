package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.CardController;

class ValidateCVVCodeTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	 * String, Boolean sercurity code of the credit card, expected validation result
	 */
	@ParameterizedTest
	@CsvSource({ "000, true", "999, true", "0000, false", "00, false", "a01, false", ", false"

	})

	void validateCcvCode(String ccvCode, boolean expected) {
		boolean isValid = CardController.validateCcvCode(ccvCode);
		assertEquals(expected, isValid);
	}

}
