package servicedapartment.data;

public class RoomInfo {
	private String roomNumb;
	private int typeId;
	private int customerId;
	
	public RoomInfo(String roomNumb, int typeId, int customerId) {
		this.roomNumb = roomNumb;
		this.typeId = typeId;
		this.customerId = customerId;
	}

	public String getRoomNumb() {
		return roomNumb;
	}

	public int getTypeId() {
		return typeId;
	}

	public int getCustomerId() {
		return customerId;
	}
	
}
