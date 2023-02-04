package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import subsystem.interbank.Interbank;
import entity.CreditCard;
/**
 * 
 * @author baonn
 *
 */
class InterbankTest {
	private Interbank interbankController;

	/*
	 * String, String, String, String, int, String, Format String, String, String
	 * (error code) cardCode, owner, CVV, expired Date, amount, content, createdAt,
	 * command, expected
	 */
	@ParameterizedTest
	@CsvSource({ "kstn_group9_2023, Group 9, 777, 3112, 400000, Rent bike, 2023-12-14 17:00:36, pay, 00",
			"kstn_group2_0021, Group 9, 777, 3112, 400000, Process for a transaction, 2023-15-12 15:18:30, pay, 01",
			"kstn_group9_2023, Group 9, 777, 3112, 1400000, Process for a transaction, 2023-15-12 15:18:30, pay, 02",
			"kstn_group9_2023, Group 9, 777, 3112, 400000, Rent bike, 2023-15-12 15:18:30, pay, 03",
			"kstn_group9_2023, Group 9, 777, 3112, 400000, , 2023-15-12 15:18:30, pay, 05", })
	public void test(String cardCode, String owner, String cvv, String expiredDate, String amount, String content,
			String createdAt, String command, String expected) {
		interbankController = new Interbank();
		CreditCard card = new CreditCard(cardCode, owner, cvv, expiredDate);
		assertEquals(interbankController.processTransaction(card, (int) Integer.parseInt(amount), content, createdAt,
				command), expected);
		interbankController.reset(card);
	}
}
