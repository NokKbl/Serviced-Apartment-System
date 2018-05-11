package servicedapartment.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * HistoryInfo class is a class for create an object which contains the history information.
 * @author Thanaphon Keawjam
 */
public class HistoryInfo {
	private SimpleStringProperty room;
	private SimpleStringProperty customerName;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty email;
	private SimpleIntegerProperty stay;
	private SimpleIntegerProperty personNumber;
	private SimpleStringProperty dIn;
	private SimpleStringProperty dOut;
	
	/**
	 * Initialize the values in a HistoryInfo object.
	 * @param room is a room number.
	 * @param customerName is a customer's name.
	 * @param phoneNumber is a customer's phone number.
	 * @param email is a customer's email address.
	 * @param stay is days that customer will stay.
	 * @param personNumber is amount of customer.
	 * @param dIn is a day that customer will be in.
	 * @param dOut a day that customer will be out.
	 */
	public HistoryInfo(String room, String customerName, String phoneNumber, String email, 
						int stay, int personNumber, String dIn, String dOut) {
		this.room = new SimpleStringProperty(room);
		this.customerName = new SimpleStringProperty(customerName);
		this.phoneNumber = new SimpleStringProperty(phoneNumber);
		this.email = new SimpleStringProperty(email);
		this.stay = new SimpleIntegerProperty(stay);
		this.personNumber = new SimpleIntegerProperty(personNumber);
		this.dIn = new SimpleStringProperty(dIn);
		this.dOut = new SimpleStringProperty(dOut);
	}
	
	/**
	 * Set a room number.
	 * @param room is a room number.
	 */
	public void setRoom(String room) {
		this.room.set(room);
	}
	
	/**
	 * Get a room number.
	 * @return A room number.
	 */
	public String getRoom() {
		return this.room.get();
	}
	
	/**
	 * Set a customer's name.
	 * @param customerName is a customer's name.
	 */
	public void setCustomerName(String customerName) {
		this.customerName.set(customerName);
	}
	
	/**
	 * Get a customer's name.
	 * @return A customer's name.
	 */
	public String getCustomerName() {
		return this.customerName.get();
	}
	
	/**
	 * Set a customer's phone number.
	 * @param phoneNumber is a customer's phone number.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	
	/**
	 * Get a customer's phone number.
	 * @return A customer's phone number.
	 */
	public String getPhoneNumber() {
		return this.phoneNumber.get();
	}
	
	/**
	 * Set a customer's email address.
	 * @param email is a customer's email address.
	 */
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	/**
	 * Get a customer's email address.
	 * @return A customer's email address.
	 */
	public String getEmail() {
		return this.email.get();
	}
	
	/**
	 * Set days that customer will stay.
	 * @param stay is days that customer will stay.
	 */
	public void setStay(int stay) {
		this.stay.set(stay);
	}
	
	/**
	 * Get days that customer will stay.
	 * @return Days that customer will stay.
	 */
	public int getStay() {
		return this.stay.get();
	}
	
	/**
	 * Set amount of customer.
	 * @param personNumber is amount of customer.
	 */
	public void setPersonNumber(int personNumber) {
		this.personNumber.set(personNumber);
	}
	
	/**
	 * Get amount of customer.
	 * @return amount of customer.
	 */
	public int getPersonNumber() {
		return this.personNumber.get();
	}
	
	/**
	 * Set a day that customer will be in.
	 * @param dIn is a day that customer will be in.
	 */
	public void setDIn(String dIn) {
		this.dIn.set(dIn);
	}
	
	/**
	 * Get a day that customer will be in.
	 * @return A day that customer will be in.
	 */
	public String getDIn() {
		return this.dIn.get();
	}
	
	/**
	 * Set a day that customer will be out.
	 * @param dOut is a day that customer will be out.
	 */
	public void setDOut(String dOut) {
		this.dOut.set(dOut);
	}
	
	/**
	 * Get a day that customer will be out.
	 * @return A day that customer will be out.
	 */
	public String getDOut() {
		return this.dOut.get();
	}
	
}
