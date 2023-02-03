package util;

import exception.*;

/**
 * @author longld
 *
 */
public class HandleException {
	/**
	 * parser on exception and throw the appropriate message
	 * @param code
	 * @throws RuntimeException
	 */
	public static void getException(String code) throws RuntimeException {
		switch (code) {
		case "01":
			throw new InvalidCardException();
		case "02":
			throw new NotEnoughBalanceException();
		case "03":
			throw new InternalServerErrorException();
		case "04":
			throw new SuspiciousTransactionException();
		case "05":
			throw new NotEnoughTransactionInfoException();
		case "06":
			throw new InvalidVersionException();
		case "07":
			throw new InvalidTransactionAmountException();
		case Constants.IS_USE:
			throw new CardInUseException();
		case Constants.NOT_AVAILABLE:
			throw new NoAvailableSpotException();
		case Constants.INVALID_CARD_INFO:
			throw new InvalidCardInfoException();
		}
	}
}
