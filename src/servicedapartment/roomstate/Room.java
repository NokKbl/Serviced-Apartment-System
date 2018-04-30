package servicedapartment.roomstate;

public class Room {
	private int roomNo;
	private RoomState state;

	public Room(int roomNo) {
		this.roomNo = roomNo;
		state = new OccupiedState();
	}
	
	public RoomState getState() {
		return this.state;
	}

	public void setState(RoomState state) {
		this.state = state;
	}

	public int getRoomNo() {
		return this.roomNo;
	}
}
