package servicedapartment.data;

import javafx.beans.property.SimpleStringProperty;

/**
 * StatusTableRow class is a class which contains the information (room number and status)
 *  that use to show in a TableView in check-in part.
 * @author Kunyaruk Katebunlu
 */
public class StatusTableRow{
	private SimpleStringProperty roomNb;
	private SimpleStringProperty roomSt;
	
	/**
	 * Initialize values in StatusTableRow object.
	 * @param roomNb is a room number.
	 * @param roomSt is a room's status.
	 */
	public StatusTableRow(String roomNb, String roomSt) {
		this.roomNb = new SimpleStringProperty(roomNb);
		this.roomSt = new SimpleStringProperty(roomSt);
	}

	/**
	 * Get a room number.
	 * @return A room number.
	 */
	public String getRoomNumber() {
		return roomNb.get();
	}

	/**
	 * Set a room number.
	 * @param roomNumber is a room number.
	 */
	public void setRoomNumber(SimpleStringProperty roomNb) {
		this.roomNb = roomNb;
	}

	/**
	 * Get a room's status.
	 * @return A room's status.
	 */
	public String getRoomStatus() {
		return roomSt.get();
	}

	/**
	 * Set a room's status.
	 * @param roomNumber is a room's status.
	 */
	public void setRoomStatus(SimpleStringProperty roomSt) {
		this.roomSt = roomSt;
	}
	
}
