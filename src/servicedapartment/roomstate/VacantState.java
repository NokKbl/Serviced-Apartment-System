package servicedapartment.roomstate;

public class VacantState implements RoomState{

	@Override
	public void checkState(Room room) {
		room.setState(this);
	}
	
	@Override
	public String toString() {
		return "Vacant";
	}

}
