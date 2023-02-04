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
 * @author baonn
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
	@CsvSource({ "20230000, 20230000",
		         "20230001, 20230001",
		         "20230002, 20230002",
		         "20230003, 20230003",
		         "20230004, 20230004",
		         "20230005, 20230005",
		         "20230006, 20230006",
		         "20230007, 20230007"     
	})
	public void testBarcodeConverter(int barcode, int expected) {
//		assertEquals(barcodeConverter.convertBarcodeToBikeCode(barcode), expected);
	}

}
