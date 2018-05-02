package servicedapartment.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import servicedapartment.data.CustomerInfo;
import servicedapartment.data.OrderInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.TypeInfo;

public class DatabaseFactory {
	private static DatabaseFactory factory;
	
	protected DatabaseFactory() { }
	
	public static DatabaseFactory getInstance() {
		if(factory == null) factory = new DatabaseFactory();
		return factory;
	}
	
	public void createDatabase(String filename) {
		String url = "jdbc:sqlite:" + filename;
		try (Connection connect = DriverManager.getConnection(url)){
			if(connect != null) {
				Statement stm = connect.createStatement();
				String sqlCustomer = "CREATE TABLE Customers "
										+ "(CUSTOMER_ID		INT	PRIMARY KEY,"
										+ "CUSTOMER_NAME	VARCHAR(30),"
										+ "PHONE_NUMBER		VARCHAR(15),"
										+ "EMAIL			VARCHAR(30));";
				String sqlRoom = "CREATE TABLE Rooms "
										+ "(ROOM_ID			INT PRIMARY KEY,"
										+ "ROOM_NUMBER		VARCHAR(5),"
										+ "TYPE_ID			INT,"
										+ "CUSTOMER_ID		INT);";
				String sqlType = "CREATE TABLE Room_Types "
										+ "(TYPE_ID			INT PRIMARY KEY,"
										+ "ROOM_TYPE		VARCHAR(10),"
										+ "PRICE_DAY		INT,"
										+ "PRICE_WEEK		INT,"
										+ "PRICE_MONTH		INT);";
				String sqlOrder = "CREATE TABLE Orders "
										+ "(ORDER_ID		INT PRIMARY KEY,"
										+ "ROOM_ID			INT,"
										+ "CUSTOMER_ID		INT,"
										+ "TOTAL_PRICE		INT,"
										+ "IS_PAID			BOOLEAN,"
										+ "DAYS_STAY		INT,"
										+ "PEOPLE			INT,"
										+ "DAY_IN			DATE,"
										+ "DAY_OUT			DATE);";
				stm.executeUpdate(sqlCustomer);
				stm.executeUpdate(sqlRoom);
				stm.executeUpdate(sqlType);
				stm.executeUpdate(sqlOrder);
				stm.close();
				connect.close();
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDataToCustomers(CustomerInfo customer) {
		String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				String data = "INSERT INTO Customers (CUSTOMER_NAME, PHONE_NUMBER, EMAIL) "
								+ "VALUES (" + customer.getName() + ", " + customer.getPhone() + ", " + customer.getEmail() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			System.err.println("can't insert data");
		}
	}
	
	public void insertDataToRooms(RoomInfo room) {
		String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				String data = "INSERT INTO Rooms (ROOM_NUMBER, TYPE_ID, CUSTOMER_ID) "
								+ "VALUES (" + room.getRoomNumb() + ", " + room.getTypeId() + ", " + room.getCustomerId() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			System.err.println("can't insert data");
		}
	}
	
	public void insertDataToTypes(TypeInfo type) {
		String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				String data = "INSERT INTO Room_Types (ROOM_TYPE, PRICE_DAY, PRICE_WEEK, PRICE_MONTH) "
								+ "VALUES (" + type.getRoomType() + ", " + type.getpDays() + ", " + type.getpWeeks() + ", " + type.getpMonths() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
				System.out.println("types ss");
			}
		} catch(SQLException e) {
			//System.err.println("can't insert data");
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDataToOrders(OrderInfo order) {
		String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				String data = "INSERT INTO Orders (ROOM_ID, CUSTOMER_ID, TOTAL_PRICE, IS_PAID, DAYS_STAY, PEOPLE, DAY_IN, DAY_OUT) "
								+ "VALUES (" + order.getRoomId() + ", " + order.getCustomerId() + ", " + order.getpTotal() + ", "
								+ order.isPaid() + ", " + order.getDaysStay() + ", " + Date.valueOf(order.getDayIn()) + ", "
								+ Date.valueOf(order.getDayOut()) + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			System.err.println("can't insert data");
		}
	}
	
	
	
}
