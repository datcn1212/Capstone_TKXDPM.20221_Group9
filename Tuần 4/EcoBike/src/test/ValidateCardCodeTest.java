package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import controller.CardController;

class ValidateCardCodeTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	 * String, Boolean code of the credit card, expected validation result
	 */
	@ParameterizedTest
	@CsvSource({ "kstn_group2_2021, true", "Kstn_group2_2021, false", "Kstn_group2/2021, false",
			"kstn group2_2021, false", ", false",

	})

	void validateCardCode(String cardCode, boolean expected) {
		boolean isValid = CardController.validateCardCode(cardCode);
		assertEquals(expected, isValid);
	}

}
