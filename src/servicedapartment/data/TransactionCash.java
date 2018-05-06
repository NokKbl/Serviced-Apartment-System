package servicedapartment.data;

import java.util.Random;

public class TransactionCash {

	private final String ALPHABET = "0123456789" + "abcdefghijklmnopqrstuvwxyz" +
									"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	private String transacCash = "";
	
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
