package servicedapartment.checkin;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import servicedapartment.SwitchScene;
import servicedapartment.data.TypeInfo;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.OrderInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.database.DatabaseFactory;

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
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private SwitchScene newScene = new SwitchScene();
	private RoomInfo roomInfo;
	private CustomerInfo customerInfo;
	private int totalP, stayD, people;
	private LocalDate dayIn, dayOut;
	
	
	public void initialize(TypeInfo typeInfo, RoomInfo roomInfo, CustomerInfo customerInfo, String unit,
							int totalP, int stayD, int people, LocalDate dayIn, LocalDate dayOut) {
		this.roomInfo = roomInfo;
		this.customerInfo = customerInfo;
		this.totalP = totalP;
		this.stayD = stayD;
		this.people = people;
		this.dayIn = dayIn;
		this.dayOut = dayOut;
		
		name.setText(customerInfo.getName());
		phone.setText(customerInfo.getPhone());
		email.setText(customerInfo.getEmail());
		amount.setText(String.valueOf(people));
		checkinDate.setText(dayIn.toString());
		checkoutDate.setText(dayOut.toString());
		roomNumb.setText(roomInfo.getRoomNumb());
		roomType.setText(typeInfo.getRoomType());
		stay.setText(String.valueOf(stayD));
		total.setText(String.valueOf(totalP));
		if(unit.equalsIgnoreCase("days")) rentalRate.setText(String.valueOf(typeInfo.getpDays()));
		else if (unit.equalsIgnoreCase("weeks")) rentalRate.setText(String.valueOf(typeInfo.getpWeeks()));
		else rentalRate.setText(String.valueOf(typeInfo.getpMonths()));
	}
	
	public void handleCheckin(ActionEvent event) throws IOException {
		factory.insertDataToCustomers(customerInfo);
		int custmId = factory.getCustomerID(customerInfo.getName());
		//factory.updateCustomerIDInRoom(roomInfo.getRoomNumb(), custmId);
		int roomID = factory.getRoomID(roomInfo.getRoomNumb());
		System.out.println(dayIn);
		OrderInfo orderInfo = new OrderInfo(roomID, custmId, totalP, stayD, people, dayIn, dayOut);
		factory.insertDataToOrders(orderInfo);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Check in successful");
		alert.setContentText("This order has successfully recorded.");
		alert.showAndWait();
		
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "HomeUI.fxml");
	}
	
}
