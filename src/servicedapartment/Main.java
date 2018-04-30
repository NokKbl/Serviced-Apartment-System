package servicedapartment;

import java.sql.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("HomeUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Serviced Apartment System");
			primaryStage.setResizable(false);;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createDatabase(String filename) {
		String url = "jdbc:sqlite:" + filename;
		try (Connection connect = DriverManager.getConnection(url)){
			if(connect != null) {
				Statement stm = connect.createStatement();
				String sqlTable = "CREATE TABLE Customer_log "
										+ "(ROOM_NUMBER		CHAR(5)			NOT NULL,"
										+ "ROOM_TYPE		VARCHAR(10)		NOT NULL,"
										+ "CUSTOMER_NAME	VARCHAR(30)		NOT NULL,"
										+ "PHONE_NUMBER		VARCHAR(15)		NOT NULL,"
										+ "EMAIL			VARCHAR(30),"
										+ "STAY_FOR			VARCHAR(20)		NOT NULL,"
										+ "CLIENTS_AMOUNT	INT				NOT NULL,"
										+ "CHECKIN_DATE		DATE			NOT NULL,"
										+ "CHECKOUT_DATE	DATE			NOT NULL,"
										+ "STATUS			VARCHAR(10)		NOT NULL,"
										+ "TOTAL			INT(15)			NOT NULL)";
				stm.executeUpdate(sqlTable);
				stm.close();
				connect.close();
			}
		} catch(SQLException e) {
			System.err.println("table already exists");
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.createDatabase("CustomerLog.db");
		launch(args);
	}
}
