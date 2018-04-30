package servicedapartment;

import java.time.LocalDate;

public class Customer {
	private String roomNumb;
	private String roomType;
	private String name;
	private String phone;
	private String email;
	private int stay;
	private String stayUnit;
	private int amount;
	private LocalDate checkin;
	private LocalDate checkout;
	private String status;
	private int total;
	
	public Customer(String roomNumb, String roomType, String name, String phone, String email, int stay, 
					String stayUnit, int amount, LocalDate checkin, LocalDate checkout, String status, int total) {
		this.roomNumb = roomNumb;
		this.roomType = roomType;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.stay = stay;
		this.stayUnit = stayUnit;
		this.amount = amount;
		this.checkin = checkin;
		this.checkout = checkout;
		this.status = status;
		this.total = total;
	}

	public String getRoomNumb() {
		return this.roomNumb;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public String getName() {
		return this.name;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getEmail() {
		return this.email;
	}

	public int getStay() {
		return this.stay;
	}

	public String getStayUnit() {
		return this.stayUnit;
	}

	public int getAmount() {
		return this.amount;
	}

	public LocalDate getCheckin() {
		return this.checkin;
	}

	public LocalDate getCheckout() {
		return this.checkout;
	}

	public String getStatus() {
		return this.status;
	}

	public int getTotal() {
		return this.total;
	}
}
