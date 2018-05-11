package servicedapartment.data;

import java.time.LocalDate;

/**
 * PaymentInfo class is a class for create an object which contains the payment information.
 * @author Kunyaruk Katebunlu
 */
public class PaymentInfo {
	private LocalDate dayPaid;
	private int amountPaid;
	private String paymentType;
	private String transactionID;
	
	/**
	 * Initialize values in PaymentInfo object.
	 * @param dayPaid is date that customer paid.
	 * @param amountPaid is an amount that customer paid.
	 * @param paymentType is type of payment.
	 * @param transactionID is transaction ID or order ID.
	 */
	public PaymentInfo(LocalDate dayPaid, int amountPaid, String paymentType, String transactionID) {
		this.dayPaid = dayPaid;
		this.amountPaid = amountPaid;
		this.paymentType = paymentType;
		this.transactionID = transactionID;
	}

	/**
	 * Get date that customer paid.
	 * @return A date that customer paid.
	 */
	public LocalDate getDayPaid() {
		return dayPaid;
	}

	/**
	 * Get amount that customer paid.
	 * @return An amount that customer paid.
	 */
	public int getAmountPaid() {
		return amountPaid;
	}

	/**
	 * Get type of payment.
	 * @return A type of payment.
	 */
	public String getPaymentType() {
		return paymentType;
	}

	/**
	 * Get transaction ID or order ID.
	 * @return A transaction ID or an order ID.
	 */
	public String getTransactionID() {
		return transactionID;
	}
	
}
