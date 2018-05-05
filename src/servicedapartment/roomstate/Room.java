package servicedapartment.roomstate;

public class Room {
	//private String roomNo;
	private RoomState state;

	public Room(String roomNo) {
		//this.roomNo = roomNo;
		state = null;
	}
	
	public void setState(RoomState state) {
		this.state = state;
	}
	
	public RoomState getState() {
		return this.state;
	}

//	public String getRoomNo() {
//		return this.roomNo;
//	}
}
