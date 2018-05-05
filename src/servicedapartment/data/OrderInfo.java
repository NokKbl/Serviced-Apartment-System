package servicedapartment.data;

import java.time.LocalDate;

public class OrderInfo {
	private int roomId;
	private int customerId;
	private int paymentId;
	private int pTotal;
	private int daysStay;
	private int people;
	private LocalDate dayIn;
	private LocalDate dayOut;
	
	public OrderInfo(int roomId, int customerId, int paymentId, int pTotal, int daysStay, int people,
			LocalDate dayIn, LocalDate dayOut) {
		this.roomId = roomId;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.pTotal = pTotal;
		this.daysStay = daysStay;
		this.people = people;
		this.dayIn = dayIn;
		this.dayOut = dayOut;
	}

	public int getRoomId() {
		return roomId;
	}

	public int getCustomerId() {
		return customerId;
	}
	
	public int getPaymentId() {
		return paymentId;
	}

	public int getpTotal() {
		return pTotal;
	}

	public int getDaysStay() {
		return daysStay;
	}

	public int getPeople() {
		return people;
	}

	public LocalDate getDayIn() {
		return dayIn;
	}

	public LocalDate getDayOut() {
		return dayOut;
	}
	
}
