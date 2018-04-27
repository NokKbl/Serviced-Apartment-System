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
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("HomepageUI.fxml"));
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
				DatabaseMetaData meta = connect.getMetaData();
				System.out.println("The driver name " + meta.getDriverName());
				System.out.println("new db create");
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		m.createDatabase("CustomerLog.db");
		launch(args);
	}
}
