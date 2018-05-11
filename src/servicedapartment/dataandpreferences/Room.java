package servicedapartment.dataandpreferences;

import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;

/**
 * Room class is a class that extend Observable class.
 * This class use to set the values and notify the observers.
 * @author Thanaphon Keawjam
 */
public class Room extends Observable{
	private SimpleStringProperty roomType;
	private SimpleStringProperty roomNumber;
	
	/**
	 * Create Room object without any values.
	 */
	public Room() { }
	
	/**
	 * Create Room object with the values, set the changed and notify the observers.
	 * @param roomType is a type of rooms.
	 * @param roomNumber is a number of the room.
	 */
	public Room(String roomType, String roomNumber) {
		this.roomType = new SimpleStringProperty(roomType);
		this.roomNumber = new SimpleStringProperty(roomNumber);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Set a type of room.
	 * @param type is a type of rooms.
	 */
	public void setRoomType(String type) {
		this.roomType.set(type);
	}
	
	/**
	 * Get room's type.
	 * @return A room type
	 */
	public String getRoomType() {
		return this.roomType.get();
	}
	
	/**
	 * Set a room number.
	 * @param number is a room number.
	 */
	public void setRoomNumber(String number) {
		this.roomNumber.set(number);
	}
	
	/**
	 * Get a room number.
	 * @return A room number
	 */
	public String getRoomNumber() {
		return this.roomNumber.get();
	}
	
}
