package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import subsystem.barcodeconverter.BarcodeConverter;

/**
 * NOTE: This test is currently not available since there is no API barcode
 * documentation Will be edit soon
 * 
 * @author anvd
 *
 */
class BarcodeConverterTest {
	private BarcodeConverter barcodeConverter;

	@BeforeEach
	void setUp() throws Exception {
		barcodeConverter = new BarcodeConverter();
	}

	/*
	 * String, String barcode, expected bike code
	 */
	@ParameterizedTest
	@CsvSource({ "20210000, 20210000",
		         "20210001, 20210001",
		         "20210002, 20210002",
		         "20210003, 20210003",
		         "20210004, 20210004",
		         "20210005, 20210005",
		         "20210006, 20210006",
		         "20210007, 20210007"     
	})
	public void testBarcodeConverter(int barcode, int expected) {
//		assertEquals(barcodeConverter.convertBarcodeToBikeCode(barcode), expected);
	}

}
