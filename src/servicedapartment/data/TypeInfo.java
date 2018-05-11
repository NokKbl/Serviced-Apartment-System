package servicedapartment.data;

/**
 * TypeInfo class is a class for create an object which contains the information of a type of room.
 * @author Kunyaruk Katebunlu
 */
public class TypeInfo {
	private String roomType;
	private int pDays;
	private int pWeeks;
	private int pMonths;
	
	/**
	 * Initialize the values in a TypeInfo object.
	 * @param roomType is a type of a room.
	 * @param pDays is a payment rate per day.
	 * @param pWeeks is a payment rate per week.
	 * @param pMonths is a payment rate per month.
	 */
	public TypeInfo(String roomType, int pDays, int pWeeks, int pMonths) {
		this.roomType = roomType;
		this.pDays = pDays;
		this.pWeeks = pWeeks;
		this.pMonths = pMonths;
	}

	/**
	 * Get a type of a room.
	 * @return A type of a room.
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * Get a payment rate per day.
	 * @return A payment rate per day.
	 */
	public int getpDays() {
		return pDays;
	}

	/**
	 * Get a payment rate per week.
	 * @return A payment rate per week.
	 */
	public int getpWeeks() {
		return pWeeks;
	}

	/**
	 * Get a payment rate per month.
	 * @return A payment rate per month.
	 */
	public int getpMonths() {
		return pMonths;
	}
	
}
