package servicedapartment.dataandpreferences;

import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;

public class Room extends Observable{
	
	private SimpleStringProperty roomType;
	private SimpleStringProperty roomNumber;
	
	public Room() {}
	
	public Room(String roomType, String roomNumber) {
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNumber = new SimpleStringProperty(roomNumber);
		setChanged();
		notifyObservers();
	}
	
	public void setRoomType(String type) {
		this.roomType.set(type);
	//	setChanged();
	//	notifyObservers();
	}
	
	public String getRoomType() {
		return this.roomType.get();
	}
	
	public void setRoomNumber(String number) {
		this.roomNumber.set(number);
	//	setChanged();
	//	notifyObservers();
	}
	
	public String getRoomNumber() {
		return this.roomNumber.get();
	}
}
