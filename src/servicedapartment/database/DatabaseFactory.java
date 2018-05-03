package servicedapartment.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
										+ "(CUSTOMER_ID		INTEGER	PRIMARY KEY,"
										+ "CUSTOMER_NAME	VARCHAR(30),"
										+ "PHONE_NUMBER		VARCHAR(15),"
										+ "EMAIL			VARCHAR(30));";
				String sqlRoom = "CREATE TABLE Rooms "
										+ "(ROOM_ID			INTEGER PRIMARY KEY,"
										+ "ROOM_NUMBER		VARCHAR(5),"
										+ "TYPE_ID			INT,"
										+ "CUSTOMER_ID		INT);";
				String sqlType = "CREATE TABLE Room_Types "
										+ "(TYPE_ID			INTEGER PRIMARY KEY,"
										+ "ROOM_TYPE		VARCHAR(10),"
										+ "PRICE_DAY		INT,"
										+ "PRICE_WEEK		INT,"
										+ "PRICE_MONTH		INT);";
				String sqlOrder = "CREATE TABLE Orders "
										+ "(ORDER_ID		INTEGER PRIMARY KEY,"
										+ "ROOM_ID			INT,"
										+ "CUSTOMER_ID		INT,"
										+ "TOTAL_PRICE		INT,"
										+ "IS_PAID			BOOLEAN	NOT NULL CHECK (IS_PAID IN (0,1)),"
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
								+ "VALUES ('" + customer.getName() + "', '" + customer.getPhone() + "', '" + customer.getEmail() + "');";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			//System.err.println("can't insert data");
			System.out.println(e.getMessage());
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
								+ "VALUES ('" + room.getRoomNumb() + "', " + room.getTypeId() + ", " + room.getCustomerId() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			//System.err.println("can't insert data");
			System.out.println(e.getMessage());
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
								+ "VALUES ( '" + type.getRoomType() + "', " + type.getpDays() + ", " + type.getpWeeks() + ", " + type.getpMonths() + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
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
								+ order.isPaid() + ", " + order.getDaysStay() + ", " + order.getPeople() + ", " + Date.valueOf(order.getDayIn()) + ", "
								+ Date.valueOf(order.getDayOut()) + ");";
				stm.executeUpdate(data);
				stm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) {
			//System.err.println("can't insert data");
			System.out.println(e.getMessage());
		}
	}
	
<<<<<<< Updated upstream
	public List<RoomInfo> readDataFromRoom() {
		String url = "jdbc:sqlite:CustomerLog.db";
		List<RoomInfo> allRoomInfo = new ArrayList<>();
		RoomInfo roomInfo = null;
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM Rooms;");
				
				while(rs.next()) {
					String roomN = rs.getString("ROOM_NUMBER");
					int typeID = rs.getInt("TYPE_ID");
					int customerID = rs.getInt("CUSTOMER_ID");
					roomInfo = new RoomInfo(roomN, typeID, customerID);
					allRoomInfo.add(roomInfo);
				}
				
				rs.close();
				stm.close();
				connect.close();
			}
		} catch(SQLException e) {
			//System.err.println("can't insert data");
			System.out.println(e.getMessage());
		}
		return allRoomInfo;
	}
=======
	public List<TypeInfo> readDataFromRoomType() {
		String url = "jdbc:sqlite:CustomerLog.db";
		List<TypeInfo> list = new ArrayList<>();
		try (Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm =connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM Room_Types;");
				
				while(rs.next()) {
					String roomType = rs.getString("room_type");
					int pDays = rs.getInt("price_day");
					int pWeeks = rs.getInt("price_week");
					int pMonths = rs.getInt("price_month");
					
					TypeInfo type = new TypeInfo(roomType, pDays, pWeeks, pMonths);
					
					list.add(type);
				}
				
				rs.close();
				connect.commit();
				connect.close();
				stm.close();
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return list;
	}
	
	
>>>>>>> Stashed changes
	
}
