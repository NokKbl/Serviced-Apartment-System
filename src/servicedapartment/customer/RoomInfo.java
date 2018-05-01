package servicedapartment.customer;

public class RoomInfo {
	private String roomNumb;
	private String roomType;
	private String status;
	private String roomRates;
	private int total;
	
	public RoomInfo(String roomNumb, String roomType, String status, String roomRates, int total) {
		this.roomNumb = roomNumb;
		this.roomType = roomType;
		this.status = status;
		this.roomRates = roomRates;
		this.total = total;
	}

	public String getRoomNumb() {
		return this.roomNumb;
	}

	public String getRoomType() {
		return this.roomType;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public String getRoomRates() {
		return this.roomRates;
	}

	public int getTotal() {
		return this.total;
	}
}
