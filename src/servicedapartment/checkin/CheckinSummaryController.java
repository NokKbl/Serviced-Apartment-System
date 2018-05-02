package servicedapartment.checkin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.database.DatabaseFactory;
import servicedapartment.roomstate.Room;

public class CheckinSummaryController {
	@FXML Label name;
	@FXML Label phone;
	@FXML Label email;
	@FXML Label amount;
	@FXML Label checkinDate;
	@FXML Label checkoutDate;
	@FXML Label roomNumb;
	@FXML Label roomType;
	@FXML Label rentalRate;
	@FXML Label stay;
	@FXML Label total;
	@FXML Button checkin;
	@FXML Button cancel;
	private TypeInfo basicInfo;
	private RoomInfo roomInfo;
	private Room room;
	private SwitchScene newScene = new SwitchScene();
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	
	public void initialize(TypeInfo basicInfo, RoomInfo roomInfo, Room room) {
		this.basicInfo = basicInfo;
		this.roomInfo = roomInfo;
		this.room = room;
	}
	
	public TypeInfo getBasicInfo() {
		return this.basicInfo;
	}
	
	public RoomInfo getRoomInfo() {
		return this.roomInfo;
	}
	
	public Room getRoom() {
		return this.room;
	}
	
	public void handleCheckin(ActionEvent event) throws IOException {
		//สร้าง Customer
		//ใช้ method insert in WriteData
		//เปลี่ยนสถานะห้องใน Database
		
		//CustomerInfo customer = new CustomerInfo(this.getBasicInfo(), this.getRoomInfo());
		//factory.insertDataToCustomer(customer);
		//factory.updateCheckin(this.getRoom(), customer.getBasicInfo().getName());
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
