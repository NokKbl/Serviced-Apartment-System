package servicedapartment.data;

import java.time.LocalDate;

public class PaymentInfo {
	private LocalDate dayPaid;
	private int amountPaid;
	private String pmType;
	private String trsCode;
	
	public PaymentInfo(LocalDate dayPaid, int amountPaid, String pmType, String trsCode) {
		this.dayPaid = dayPaid;
		this.amountPaid = amountPaid;
		this.pmType = pmType;
		this.trsCode = trsCode;
	}

	public LocalDate getDayPaid() {
		return dayPaid;
	}

	public int getAmountPaid() {
		return amountPaid;
	}

	public String getPaymentType() {
		return pmType;
	}

	public String getTransactionID() {
		return trsCode;
	}
	
}
