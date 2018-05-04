package servicedapartment.data;

import javafx.beans.property.SimpleStringProperty;

public class TableRow {
	private SimpleStringProperty roomNb;
	private SimpleStringProperty roomSt;
	
	public TableRow(String roomNb, String roomSt) {
		this.roomNb = new SimpleStringProperty(roomNb);
		this.roomSt = new SimpleStringProperty(roomSt);
	}

	public String getRoomNb() {
		return roomNb.get();
	}

	public void setRoomNb(SimpleStringProperty roomNb) {
		this.roomNb = roomNb;
	}

	public String getRoomSt() {
		return roomSt.get();
	}

	public void setRoomSt(SimpleStringProperty roomSt) {
		this.roomSt = roomSt;
	}
	
}
