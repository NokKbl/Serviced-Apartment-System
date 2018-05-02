package servicedapartment.data;

public class TypeInfo {
	private String roomType;
	private int pDays;
	private int pWeeks;
	private int pMonths;
	
	public TypeInfo(String roomType, int pDays, int pWeeks, int pMonths) {
		this.roomType = roomType;
		this.pDays = pDays;
		this.pWeeks = pWeeks;
		this.pMonths = pMonths;
	}

	public String getRoomType() {
		return roomType;
	}

	public int getpDays() {
		return pDays;
	}

	public int getpWeeks() {
		return pWeeks;
	}

	public int getpMonths() {
		return pMonths;
	}
	
}
