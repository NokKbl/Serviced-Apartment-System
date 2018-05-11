package servicedapartment.data;

/**
 * RoomInfo class is a class for create an object which contains the information of a room.
 * @author Kunyaruk Katebunlu
 */
public class RoomInfo {
	private String roomNumb;
	private int typeId;
	
	/**
	 * Initialize the values in a RoomInfo object.
	 * @param roomNumb is a number of a room.
	 * @param typeId is a type's ID of a room.
	 */
	public RoomInfo(String roomNumb, int typeId) {
		this.roomNumb = roomNumb;
		this.typeId = typeId;
	}

	/**
	 * Get number of a room.
	 * @return A room's number.
	 */
	public String getRoomNumb() {
		return this.roomNumb;
	}

	/**
	 * Get type's ID of a room.
	 * @return A type's ID.
	 */
	public int getTypeId() {
		return this.typeId;
	}
	
}
