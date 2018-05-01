package servicedapartment.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import servicedapartment.customer.Customer;
import servicedapartment.roomstate.Room;

public class DataFactory {
	private static DataFactory factory;
	
	protected DataFactory() { }
	
	public static DataFactory getInstance() {
		if(factory == null) factory = new DataFactory();
		return factory;
	}
	
	public void insertDataToCustomerLog(Customer customer) {
		String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				String data = "INSERT INTO Customer_log(ROOM_NUMBER, ROOM_TYPE, CUSTOMER_NAME, PHONE_NUMBER, EMAIL, STAY_FOR, CLIENTS_AMOUNT, CHECKIN_DATE, CHECKOUT_DATE, STATUS, TOTAL) "
								+ "VALUES ( " + customer.getRoomInfo().getRoomNumb() + ", " + customer.getRoomInfo().getRoomType() + ", "
								+ customer.getBasicInfo().getName() + ", " + customer.getBasicInfo().getPhone() + ", " + customer.getBasicInfo().getEmail() + ", "
								+ Integer.toString(customer.getBasicInfo().getStay()) + customer.getBasicInfo().getStayUnit() + ", " + customer.getBasicInfo().getAmount() + ", "
								+ Date.valueOf(customer.getBasicInfo().getCheckin()) + ", " + Date.valueOf(customer.getBasicInfo().getCheckout()) + ", "
								+ customer.getRoomInfo().getStatus() + ", " + customer.getRoomInfo().getTotal() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			System.err.println("can't insert data");
		}
	}
	
	public void updateStatus(Room room) {
		//if(ห้องว่าง) --> ไม่ว่าง แล้วอัพเดตใน database
		//else vice versa
	}
	
}
