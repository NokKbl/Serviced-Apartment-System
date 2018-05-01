package servicedapartment.customer;

import java.time.LocalDate;

public class BasicInfo {
	private String name;
	private String phone;
	private String email;
	private int stay;
	private String stayUnit;
	private int amount;
	private LocalDate checkin;
	private LocalDate checkout;
	
	public BasicInfo(String name, String phone, String email, int stay, String stayUnit,
			int amount, LocalDate checkin) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.stay = stay;
		this.stayUnit = stayUnit;
		this.amount = amount;
		this.checkin = checkin;
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
		if(this.stayUnit.equalsIgnoreCase("days")) this.checkout = this.checkin.plusDays(this.stay);
		if(this.stayUnit.equalsIgnoreCase("weeks")) this.checkout = this.checkin.plusWeeks(this.stay);
		if(this.stayUnit.equalsIgnoreCase("months")) this.checkout = this.checkin.plusMonths(this.stay);
		else this.checkout = this.checkin.plusYears(this.stay);
		return this.checkout;
	}
}
