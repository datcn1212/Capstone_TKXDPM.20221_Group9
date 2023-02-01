package util;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import entity.InterbankTransaction;

/**
 * @author longld
 *
 */
public class HashFunction {
	/**
	 * hash a transaction
	 * @param key
	 * @param transaction
	 * @return
	 */
	public static String hashTransaction(String key, InterbankTransaction transaction) {
		try {
			String transact = new ObjectMapper().writeValueAsString(transaction);
			@SuppressWarnings("deprecation")
			JsonObject transactionBody = new JsonParser().parse(transact).getAsJsonObject();

			// convert to request transaction
			JsonObject transToHash = new JsonObject();
			transToHash.addProperty("secretKey", key);
			transToHash.add("transaction", transactionBody);
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				e.printStackTrace();
			}
			md.update(transToHash.toString().getBytes());
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (Exception e) {
			System.out.println("Exception at util.HashFunction" + e);
			return null;
		}
	}

	/**
	 * hash a rental code
	 * @param rentalCode
	 * @return
	 */
	public static String hashRentalCode(String rentalCode) {
		try {
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				e.printStackTrace();
			}
			md.update(rentalCode.getBytes());
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase().substring(0, 8);
		} catch (Exception e) {
			System.out.println("Exception at util.HashFunction");
			return null;
		}
	}
}
