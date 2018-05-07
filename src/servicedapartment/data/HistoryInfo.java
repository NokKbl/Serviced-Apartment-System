package servicedapartment.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class HistoryInfo {
	
	private SimpleStringProperty room;
	private SimpleStringProperty customerName;
	private SimpleStringProperty phoneNumber;
	private SimpleStringProperty email;
	private SimpleIntegerProperty stay;
	private SimpleIntegerProperty personNumber;
	private SimpleStringProperty dIn;
	private SimpleStringProperty dOut;
	
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
	
	public void setRoom(String room) {
		this.room.set(room);
	}
	
	public String getRoom() {
		return this.room.get();
	}
	
	public void setCustomerName(String customerName) {
		this.customerName.set(customerName);
	}
	
	public String getCustomerName() {
		return this.customerName.get();
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber.get();
	}
	
	public void setEmail(String email) {
		this.email.set(email);
	}
	
	public String getEmail() {
		return this.email.get();
	}
	
	public void setStay(int stay) {
		this.stay.set(stay);
	}
	
	public int getStay() {
		return this.stay.get();
	}
	
	public void setPersonNumber(int personNumber) {
		this.personNumber.set(personNumber);
	}
	
	public int getPersonNumber() {
		return this.personNumber.get();
	}
	
	public void setDIn(String dIn) {
		this.dIn.set(dIn);
	}
	
	public String getDIn() {
		return this.dIn.get();
	}
	
	public void setDOut(String dOut) {
		this.dOut.set(dOut);
	}
	
	public String getDOut() {
		return this.dOut.get();
	}
	
}
