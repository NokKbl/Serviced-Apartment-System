package servicedapartment.customer;

public class Customer {
	private BasicInfo basicInfo;
	private RoomInfo roomInfo;
	
	public Customer(BasicInfo basicInfo, RoomInfo roomInfo) {
		this.basicInfo = basicInfo;
		this.roomInfo = roomInfo;
	}

	public BasicInfo getBasicInfo() {
		return basicInfo;
	}

	public RoomInfo getRoomInfo() {
		return roomInfo;
	}
}
