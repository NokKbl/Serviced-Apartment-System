package servicedapartment.database;

import java.sql.*;

import servicedapartment.Customer;

public class WriteData extends DataFactory{
	public void insertDataToCustomerLog(Customer customer) {
		String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				String data = "INSERT INTO Customer_log(ROOM_NUMBER, ROOM_TYPE, CUSTOMER_NAME, PHONE_NUMBER, EMAIL, STAY_FOR, CLIENTS_AMOUNT, CHECKIN_DATE, CHECKOUT_DATE, STATUS, TOTAL) "
								+ "VALUES ( " + customer.getRoomNumb() + ", " + customer.getRoomType() + ", "
								+ customer.getName() + ", " + customer.getPhone() + ", " + customer.getEmail() + ", "
								+ Integer.toString(customer.getStay()) + customer.getStayUnit() + ", " + customer.getAmount() + ", "
								+ Date.valueOf(customer.getCheckin()) + ", " + Date.valueOf(customer.getCheckout()) + ", "
								+ customer.getStatus() + ", " + customer.getTotal() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			System.err.println("can't insert data");
		}
	}
	
	public void updateStatus() {
		
	}
}