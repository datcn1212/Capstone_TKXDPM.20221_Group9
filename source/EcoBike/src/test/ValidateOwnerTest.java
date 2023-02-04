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
class ValidateOwnerTest {
	@BeforeEach
	void setUp() throws Exception {
	}

	/*
	 * String, Boolean name of the owner of the credit card, expected validation
	 * result
	 */
	@ParameterizedTest
	@CsvSource({ "Group 2, true", "Group2, true", "group@2 3, false", ", false"

	})

	void validateCardCode(String owner, boolean expected) {
		boolean isValid = CardController.validateOwner(owner);
		assertEquals(expected, isValid);
	}
}
