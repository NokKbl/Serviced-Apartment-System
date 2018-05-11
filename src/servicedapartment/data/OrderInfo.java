package servicedapartment.data;

import java.time.LocalDate;

/**
 * OrderInfo class is a class for create an object which contains the information of an order.
 * @author Kunyaruk Katebunlu
 */
public class OrderInfo {
	private int roomId;
	private int customerId;
	private int paymentId;
	private int pTotal;
	private int daysStay;
	private int people;
	private LocalDate dayIn;
	private LocalDate dayOut;
	private String roomStatus;
	
	/**
	 * Initialize the values in a OrderInfo object.
	 * @param roomId is an ID of a room that customer choose in this order.
	 * @param customerId is an ID of a customer of this order.
	 * @param paymentId is an ID of payment information of this order.
	 * @param pTotal is a total payment value.
	 * @param daysStay is the amount of days that customer will stay.
	 * @param people is the amount of people that will stay.
	 * @param dayIn is a day that customer will be in.
	 * @param dayOut is a day that customer will be out.
	 * @param roomStatus is the status of a room.
	 */
	public OrderInfo(int roomId, int customerId, int paymentId, int pTotal, int daysStay, int people,
			LocalDate dayIn, LocalDate dayOut, String roomStatus) {
		this.roomId = roomId;
		this.customerId = customerId;
		this.paymentId = paymentId;
		this.pTotal = pTotal;
		this.daysStay = daysStay;
		this.people = people;
		this.dayIn = dayIn;
		this.dayOut = dayOut;
		this.roomStatus = roomStatus;
	}

	/**
	 * Get room's ID.
	 * @return A room's ID.
	 */
	public int getRoomId() {
		return roomId;
	}

	/**
	 * Get customer's ID.
	 * @return A customer's ID.
	 */
	public int getCustomerId() {
		return customerId;
	}
	
	/**
	 * Get payment's ID.
	 * @return A payment's ID.
	 */
	public int getPaymentId() {
		return paymentId;
	}

	/**
	 * Get total payment value.
	 * @return A total payment value.
	 */
	public int getpTotal() {
		return pTotal;
	}

	/**
	 * Get the amount of days that customer will stay.
	 * @return An amount of days that customer will stay.
	 */
	public int getDaysStay() {
		return daysStay;
	}

	/**
	 * Get the amount of people that will stay.
	 * @return An the amount of people that will stay.
	 */
	public int getPeople() {
		return people;
	}

	/**
	 * Get day that customer will be in.
	 * @return A day that customer will be in.
	 */
	public LocalDate getDayIn() {
		return dayIn;
	}

	/**
	 * Get day that customer will be out.
	 * @return A day that customer will be out.
	 */
	public LocalDate getDayOut() {
		return dayOut;
	}
	
	/**
	 * Get the status of a room.
	 * @return A status of a room.
	 */
	public String getRoomStatus() {
		return roomStatus;
	}
	
}
