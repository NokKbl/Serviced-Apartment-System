package servicedapartment.checkin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import servicedapartment.SwitchScene;
import servicedapartment.customer.BasicInfo;
import servicedapartment.customer.Customer;
import servicedapartment.customer.RoomInfo;
import servicedapartment.database.DataFactory;

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
	private BasicInfo basicInfo;
	private RoomInfo roomInfo;
	private SwitchScene newScene = new SwitchScene();
	private DataFactory factory = DataFactory.getInstance();
	
	public void initialize(BasicInfo basicInfo, RoomInfo roomInfo) {
		this.basicInfo = basicInfo;
		this.roomInfo = roomInfo;
		//สร้าง Customer
		//ใช้ method insert in WriteData
		//เปลี่ยนสถานะห้องใน Database
	}
	
	public BasicInfo getBasicInfo() {
		return this.basicInfo;
	}
	
	public RoomInfo getRoomInfo() {
		return this.roomInfo;
	}
	
	public void handleCheckin(ActionEvent event) throws IOException {
		Customer customer = new Customer(this.getBasicInfo(), this.getRoomInfo());
		factory.insertDataToCustomerLog(customer);
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
