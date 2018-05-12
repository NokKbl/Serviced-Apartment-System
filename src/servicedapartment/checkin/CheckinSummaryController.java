package servicedapartment.checkin;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import servicedapartment.data.TypeInfo;
import servicedapartment.data.CustomerInfo;
import servicedapartment.data.OrderInfo;
import servicedapartment.data.PaymentInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.SwitchScene;
import servicedapartment.database.DatabaseFactory;

/**
 * Receive and set the values or action of the components in CheckinSummaryUI.fxml file and record into database.
 * @author Kunyaruk Katebunlu
 */
public class CheckinSummaryController {
	@FXML private Label name;
	@FXML private Label phone;
	@FXML private Label email;
	@FXML private Label amount;
	@FXML private Label checkinDate;
	@FXML private Label checkoutDate;
	@FXML private Label roomNumb;
	@FXML private Label roomType;
	@FXML private Label rentalRate;
	@FXML private Label stay;
	@FXML private Label total;
	@FXML private Label dayPaid;
	@FXML private Label paymentType;
	@FXML private Label transactionID;
	@FXML private Label amountPaid;
	@FXML private Button checkin;
	@FXML private Button cancel;
	private DatabaseFactory factory = DatabaseFactory.getInstance();
	private SwitchScene newScene = new SwitchScene();
	private RoomInfo roomInfo;
	private CustomerInfo customerInfo;
	private PaymentInfo paymentInfo;
	private int totalP, stayD, people;
	private LocalDate dayIn, dayOut;
	
	/**
	 * Initialize components and set text in all Labels.
	 * @param typeInfo is the information about type of a room that the customer choose.
	 * @param roomInfo is the information about the room that the customer choose.
	 * @param customerInfo is the information about the customer.
	 * @param paymentInfo is the information about the payment that the customer done.
	 * @param unit is the unit of time that the customer will stay (days/weeks/months/years).
	 * @param totalP is the total payment which the customer should pay.
	 * @param stayD is a number of days that the customer will stay.
	 * @param people is a number of the customer.
	 * @param dayIn is a check-in date.
	 * @param dayOut is a checkout date.
	 */
	public void initialize(TypeInfo typeInfo, RoomInfo roomInfo, CustomerInfo customerInfo, PaymentInfo paymentInfo,
							String unit, int totalP, int stayD, int people, LocalDate dayIn, LocalDate dayOut) {
		this.roomInfo = roomInfo;
		this.customerInfo = customerInfo;
		this.paymentInfo = paymentInfo;
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
		dayPaid.setText(paymentInfo.getDayPaid().toString());
		paymentType.setText(paymentInfo.getPaymentType());
		transactionID.setText(paymentInfo.getTransactionID());
		amountPaid.setText(String.valueOf(paymentInfo.getAmountPaid()));
		if(unit.equalsIgnoreCase("days")) rentalRate.setText(String.valueOf(typeInfo.getpDays()));
		else if (unit.equalsIgnoreCase("weeks")) rentalRate.setText(String.valueOf(typeInfo.getpWeeks()));
		else rentalRate.setText(String.valueOf(typeInfo.getpMonths()));
	}
	
	/**
	 * Record and update all information into the database and switch to the Home scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCheckin(ActionEvent event) throws IOException {
		factory.insertDataToCustomers(customerInfo);
		int custmId = factory.getCustomerID(customerInfo.getName());
		int roomID = factory.getRoomID(roomInfo.getRoomNumb());
		factory.insertDataToPayment(paymentInfo);
		int paymentID = factory.getPaymentID(paymentInfo.getTransactionID());
		String rmStatus = "Occupied";
		OrderInfo orderInfo = new OrderInfo(roomID, custmId, paymentID, totalP, stayD, people, dayIn, dayOut, rmStatus);
		factory.insertDataToOrders(orderInfo);
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Alert");
		alert.setHeaderText("Check in successful");
		alert.setContentText("This order has successfully recorded.");
		alert.showAndWait();
		
		newScene.switchScene(event, "/servicedapartment/home/HomeUI.fxml");
	}
	
	/**
	 * Not record any data and switch to the Home scene.
	 * @throws IOException if FXMLLoader cannot get resource from file.
	 */
	public void handleCancel(ActionEvent event) throws IOException {
		newScene.switchScene(event, "/servicedapartment/home/HomeUI.fxml");
	}
	
}
