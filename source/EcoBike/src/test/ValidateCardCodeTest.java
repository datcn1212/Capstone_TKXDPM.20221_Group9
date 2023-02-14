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
class ValidateCardCodeTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	 * String, Boolean code of the credit card, expected validation result
	 */
	@ParameterizedTest
	@CsvSource({ "kstn_group9_2023, true", "Kstn_group9_2023, false", "Kstn_group9/2023, false",
			"kstn group9_2023, false", ", false",

	})

	void validateCardCode(String cardCode, boolean expected) {
		boolean isValid = CardController.validateCardCode(cardCode);
		assertEquals(expected, isValid);
	}

}
