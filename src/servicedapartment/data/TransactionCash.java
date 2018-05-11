package servicedapartment.data;

import java.util.Random;

/**
 * TransactionCash class is class that use for generate code for a payment that pay by cash.
 * @author Thanaphon Keawjam
 */
public class TransactionCash {
	private String transacCash = "";
	private final String ALPHABET = "0123456789" + "abcdefghijklmnopqrstuvwxyz" +
									"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * Random alphabet and number then get first 4 digits.
	 * @return First 4 digits.
	 */
	public String createTransactionCash() {
		Random random = new Random();
		int index = 0;
		
		for(int i = 0; i < 4; i++) {
			index = random.nextInt(ALPHABET.length());
			char ch = ALPHABET.charAt(index);
			transacCash = transacCash + ch;
		}
		return transacCash;
	}
	
}
