package servicedapartment.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import servicedapartment.data.CustomerInfo;
import servicedapartment.data.OrderInfo;
import servicedapartment.data.PaymentInfo;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.TypeInfo;

public class Database {
	private static Database factory;
	private final String url = "jdbc:sqlite:CustomerLog.db";
	
	protected Database() { }
	
	public static Database getInstance() {
		if(factory == null) factory = new Database();
		return factory;
	}
	
	public void createDatabase() {
		//String url = "jdbc:sqlite:" + filename;
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
										+ "TYPE_ID			INT);";
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
										+ "PAYMENT_ID		INT,"
										+ "TOTAL_PRICE		INT,"
										+ "DAYS_STAY		INT,"
										+ "PEOPLE			INT,"
										+ "DAY_IN			TEXT,"
										+ "DAY_OUT			TEXT);";
				String sqlPayment = "CREATE TABLE Payments "
										+ "(PAYMENT_ID		INTEGER PRIMARY KEY,"
										+ "DAY_PAID			TEXT,"
										+ "AMOUNT_PAID		INT,"
										+ "PAYMENT_TYPE		TEXT,"
										+ "TRANSACTION_ID	TEXT);";
				stm.executeUpdate(sqlCustomer);
				stm.executeUpdate(sqlRoom);
				stm.executeUpdate(sqlType);
				stm.executeUpdate(sqlOrder);
				stm.executeUpdate(sqlPayment);
				stm.close();
				connect.close();
			}
		} catch(SQLException e) { }
	}
	
	public void insertDataToCustomers(CustomerInfo customer) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
//				Statement stm = connect.createStatement();
//				String data = "INSERT INTO Customers (CUSTOMER_NAME, PHONE_NUMBER, EMAIL) "
//								+ "VALUES ('" + customer.getName() + "', '" + customer.getPhone() + "', '" + customer.getEmail() + "');";
//				stm.executeUpdate(data);
//				stm.close();
				String data = "INSERT INTO Customers (CUSTOMER_NAME, PHONE_NUMBER, EMAIL) VALUES (?, ?, ?);";
				PreparedStatement pstm = connect.prepareStatement(data);
				
				pstm.setString(1, customer.getName());
				pstm.setString(2, customer.getPhone());
				pstm.setString(3, customer.getEmail());
				pstm.executeUpdate();
				pstm.close();
				connect.commit();
				connect.close();
				System.out.println("write customer success");
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertDataToRooms(RoomInfo room) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
//				Statement stm = connect.createStatement();
//				String data = "INSERT INTO Rooms (ROOM_NUMBER, TYPE_ID) "
//								+ "VALUES ('" + room.getRoomNumb() + "', " + room.getTypeId() + ");";
//				stm.executeUpdate(data);
//				stm.close();
				String data = "INSERT INTO Rooms (ROOM_NUMBER, TYPE_ID) VALUES (?, ?);";
				PreparedStatement pstm = connect.prepareStatement(data);
				
				pstm.setString(1, room.getRoomNumb());
				pstm.setInt(2, room.getTypeId());
				pstm.executeUpdate();
				pstm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) { System.out.println(e.getMessage());}
	}
	
	public void insertDataToTypes(TypeInfo type) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open db success");
			if(connect != null) {
//				Statement stm = connect.createStatement();
//				String data = "INSERT INTO Room_Types (ROOM_TYPE, PRICE_DAY, PRICE_WEEK, PRICE_MONTH) "
//								+ "VALUES ( '" + type.getRoomType() + "', " + type.getpDays() + ", " + type.getpWeeks() + ", " + type.getpMonths() + ");";
//				stm.executeUpdate(data);
//				stm.close();
				String data = "INSERT INTO Room_Types (ROOM_TYPE, PRICE_DAY, PRICE_WEEK, PRICE_MONTH) VALUES (?, ?, ?, ?);";
				PreparedStatement pstm = connect.prepareStatement(data);
				
				pstm.setString(1, type.getRoomType());
				pstm.setInt(2, type.getpDays());
				pstm.setInt(3, type.getpWeeks());
				pstm.setInt(4, type.getpMonths());
				pstm.executeUpdate();
				pstm.close();
				connect.commit();
				connect.close();
			}
		} catch(SQLException e) { System.out.println(e.getMessage());}
	}
	
	public void insertDataToOrders(OrderInfo order) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open order success");
			if(connect != null) {
//				Statement stm = connect.createStatement();
//				String data = "INSERT INTO Orders (ROOM_ID, CUSTOMER_ID, PAYMENT_ID, TOTAL_PRICE, DAYS_STAY, PEOPLE, DAY_IN, DAY_OUT) "
//								+ "VALUES (" + order.getRoomId() + ", " + order.getCustomerId() + ", " + order.getPaymentId() + ", "
//								+ order.getpTotal() + ", " + order.getDaysStay() + ", " + order.getPeople() + ", '"
//								+ order.getDayIn().toString() + "', '" + order.getDayOut().toString() + "');";
//				stm.executeUpdate(data);
//				stm.close();
				String data = "INSERT INTO Orders (ROOM_ID, CUSTOMER_ID, PAYMENT_ID, TOTAL_PRICE, DAYS_STAY, PEOPLE, DAY_IN, DAY_OUT) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
				PreparedStatement pstm = connect.prepareStatement(data);
				
				pstm.setInt(1, order.getRoomId());
				pstm.setInt(2, order.getCustomerId());
				pstm.setInt(3, order.getPaymentId());
				pstm.setInt(4, order.getpTotal());
				pstm.setInt(5, order.getDaysStay());
				pstm.setInt(6, order.getPeople());
				pstm.setString(7, order.getDayIn().toString());
				pstm.setString(8, order.getDayOut().toString());
				pstm.executeUpdate();
				pstm.close();
				connect.commit();
				connect.close();
				System.out.println("write order success");
			}
		} catch(SQLException e) { System.out.println(e.getMessage());}
	}
	
	public void insertDataToPayment(PaymentInfo payment) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open order success");
			if(connect != null) {
//				Statement stm = connect.createStatement();
//				String data = "INSERT INTO Payments (DAY_PAID, AMOUNT_PAID, PAYMENT_TYPE, TRANSACTION_ID) "
//								+ "VALUES ('" + payment.getDayPaid().toString() + "', " + payment.getAmountPaid() + ", '" 
//								+ payment.getPmType() + "', '" + payment.getTrsCode() + "');";
//				stm.executeUpdate(data);
//				stm.close();
				String data = "INSERT INTO Payments (DAY_PAID, AMOUNT_PAID, PAYMENT_TYPE, TRANSACTION_ID) VALUES (?, ?, ?, ?);";
				PreparedStatement pstm = connect.prepareStatement(data);
				
				pstm.setString(1, payment.getDayPaid().toString());
				pstm.setInt(2, payment.getAmountPaid());
				pstm.setString(3, payment.getPaymentType());
				pstm.setString(4, payment.getTransactionID());
				pstm.executeUpdate();
				pstm.close();
				connect.commit();
				connect.close();
				System.out.println("write order success");
			}
		} catch(SQLException e) { System.out.println(e.getMessage());}
	}
	
	public List<RoomInfo> readDataFromRoom() {
		//String url = "jdbc:sqlite:CustomerLog.db";
		List<RoomInfo> allRoomInfo = new ArrayList<>();
		RoomInfo roomInfo = null;
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open room table success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM Rooms;");
				
				while(rs.next()) {
					String roomN = rs.getString("ROOM_NUMBER");
					int typeID = rs.getInt("TYPE_ID");
					//int customerID = rs.getInt("CUSTOMER_ID");
					roomInfo = new RoomInfo(roomN, typeID);
					allRoomInfo.add(roomInfo);
				}
				
				rs.close();
				stm.close();
				connect.close();
				System.out.println("read room success");
			}
		} catch(SQLException e) { System.out.println(e.getMessage());}
		return allRoomInfo;
	}

	public List<TypeInfo> readDataFromRoomType() {
		//String url = "jdbc:sqlite:CustomerLog.db";
		List<TypeInfo> list = new ArrayList<>();
		TypeInfo type = null;
		try (Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open type table success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM Room_Types;");
				
				while(rs.next()) {
					String roomType = rs.getString("ROOM_TYPE");
					int pDays = rs.getInt("PRICE_DAY");
					int pWeeks = rs.getInt("PRICE_WEEK");
					int pMonths = rs.getInt("PRICE_MONTH");
					type = new TypeInfo(roomType, pDays, pWeeks, pMonths);
					list.add(type);
				}
				
				rs.close();
				stm.close();
				connect.close();
				System.out.println("read type success");
			}
		}catch(SQLException e) { System.out.println(e.getMessage());}
		return list;
	}
	
	public List<OrderInfo> readDataFromOrder(){
		//String url = "jdbc:sqlite:CustomerLog.db";
		List<OrderInfo> allOrders = new ArrayList<>();
		OrderInfo orderInfo = null;
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			System.out.println("open orders table success");
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT * FROM Orders;");
				
				while(rs.next()) {
					int rId = rs.getInt("ROOM_ID");
					int ctmId = rs.getInt("CUSTOMER_ID");
					int pmId = rs.getInt("PAYMENT_ID");
					int totalP = rs.getInt("TOTAL_PRICE");
					int dStay = rs.getInt("DAYS_STAY");
					int people = rs.getInt("PEOPLE");
					String dIn = rs.getString("DAY_IN");
					String dOut = rs.getString("DAY_OUT");
					orderInfo = new OrderInfo(rId, ctmId, pmId, totalP, dStay, people, LocalDate.parse(dIn), LocalDate.parse(dOut));
					allOrders.add(orderInfo);
				}
				
				rs.close();
				stm.close();
				connect.close();
				System.out.println("read orders success");
			}
		} catch(SQLException e) {System.out.println(e.getMessage()); }
		return allOrders;
	}

	public int getCustomerID(String customerName) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		int id = 0;
		try (Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm =connect.createStatement();
				String data = "SELECT CUSTOMER_ID FROM Customers WHERE CUSTOMER_NAME = '" + customerName + "';"; 
				ResultSet rs = stm.executeQuery(data);
				id = rs.getInt("CUSTOMER_ID");
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) { System.out.println(e.getMessage());}
		return id;
	}
	
	public int getRoomID(String roomNumber) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		int id = 0;
		try (Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm =connect.createStatement();
				String data = "SELECT ROOM_ID FROM Rooms WHERE ROOM_NUMBER = '" + roomNumber + "';"; 
				ResultSet rs = stm.executeQuery(data);
				id = rs.getInt("ROOM_ID");
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) { System.out.println(e.getMessage());}
		return id;
	}
	
	public int getPaymentID(String transactionID) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		int id = 0;
		try (Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm =connect.createStatement();
				String data = "SELECT PAYMENT_ID FROM Payments WHERE TRANSACTION_ID = '" + transactionID + "';"; 
				ResultSet rs = stm.executeQuery(data);
				id = rs.getInt("PAYMENT_ID");
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) {System.out.println(e.getMessage()); }
		return id;
	}
	
//	public void updateCustomerIDInRoom(String roomNb, int customerID) {
//		//gonna delete it
//		String url = "jdbc:sqlite:CustomerLog.db";
//		try(Connection connect = DriverManager.getConnection(url)){
//			connect.setAutoCommit(false);
//			System.out.println("open db success");
//			if(connect != null) {
//				Statement stm = connect.createStatement();
//				String data = "UPDATE Rooms SET CUSTOMER_ID = " + customerID + " WHERE ROOM_NUMBER = '" + roomNb + "';";
//				stm.executeUpdate(data);
//				stm.close();
//				connect.commit();
//				connect.close();
//				System.out.println("update customerID success");
//			}
//		} catch(SQLException e) {
//			//System.err.println("can't insert data");
//		}
//	}
	
	public void updateDataToTypes(TypeInfo type) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				String sql = "UPDATE Room_Types SET PRICE_DAY = " + type.getpDays() + ", PRICE_WEEK = " + type.getpWeeks() + 
								", PRICE_MONTH = " + type.getpMonths() + " WHERE ROOM_TYPE ='" + type.getRoomType() + "';";
				stm.executeUpdate(sql);
				
				connect.commit();
				connect.close();
				stm.close();
			}
		}catch(SQLException e) { System.out.println(e.getMessage());}
	}
	
	public void deleteDataFromRoom(RoomInfo room) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				String sql = "DELETE FROM Rooms WHERE ROOM_NUMBER = " + room.getRoomNumb() + ";";
				stm.executeUpdate(sql);
				
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) { System.out.println(e.getMessage());}
	}
	
	public List<String[]> getDayIO(int roomID){
		//String url = "jdbc:sqlite:CustomerLog.db";
		List<String[]> dateIO = new ArrayList<>();
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT DAY_IN, DAY_OUT FROM Orders WHERE ROOM_ID = " + roomID + ";");
				
				while(rs.next()) {
					String dIn = rs.getString("DAY_IN");
					String dOut = rs.getString("DAY_OUT");
					String[] dates = {dIn, dOut};
					dateIO.add(dates);
				}
				
				rs.close();
				stm.close();
				connect.close();
			}
		} catch(SQLException e) { System.out.println(e.getMessage());}
		return dateIO;
	}
	
	public String getDayIn(String roomNumber) {
		String dIn = "";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT DAY_IN FROM Orders WHERE ROOM_ID = " + getRoomID(roomNumber) + ";");
				
				dIn = rs.getString("DAY_IN");
				
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) {}
		
		return dIn;
	}
	
	public boolean findOrderID(int roomId, int customerId) {
		//String url = "jdbc:sqlite:CustomerLog.db";
		boolean matchOrNot = false;
		int orderID;
		
		try(Connection connect = DriverManager.getConnection(url)) {
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT ORDER_ID FROM Orders WHERE ROOM_ID = " + roomId +
								" AND CUSTOMER_ID = " + customerId + ";");
				
				orderID = rs.getInt("ORDER_ID");
				if(orderID == 0) matchOrNot = false;
				else matchOrNot = true;
				
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) { System.out.println(e.getMessage());}
		return matchOrNot;
	}
	
	public String getRoomNumber(int id){
		String roomId= "";
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT ROOM_NUMBER FROM Rooms WHERE ROOM_ID = " + id + ";");
				roomId = rs.getString("ROOM_NUMBER");
				
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) {}
		
		return roomId;
	}
	
	public CustomerInfo getCustomerInfo(int id){
		CustomerInfo ctm = null;
		try(Connection connect = DriverManager.getConnection(url)){
			connect.setAutoCommit(false);
			if(connect != null) {
				Statement stm = connect.createStatement();
				ResultSet rs = stm.executeQuery("SELECT CUSTOMER_NAME, PHONE_NUMBER, EMAIL FROM Customers WHERE CUSTOMER_ID = " + id + ";");
				
				String name = rs.getString("CUSTOMER_NAME");
				String phone = rs.getString("PHONE_NUMBER");
				String email = rs.getString("EMAIL");
				
				ctm = new CustomerInfo(name, phone, email);
				
				rs.close();
				stm.close();
				connect.commit();
				connect.close();
			}
		}catch(SQLException e) {}
		
		return ctm;
	}
	
}
