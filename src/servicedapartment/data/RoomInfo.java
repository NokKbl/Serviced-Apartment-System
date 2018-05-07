package servicedapartment.data;

public class RoomInfo {
	private String roomNumb;
	private int typeId;
	
	public RoomInfo(String roomNumb, int typeId) {
		this.roomNumb = roomNumb;
		this.typeId = typeId;
	}

	public String getRoomNumb() {
		return this.roomNumb;
	}

	public int getTypeId() {
		return this.typeId;
	}
	
}
