package servicedapartment;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import servicedapartment.data.ReadFile;
import servicedapartment.data.RoomInfo;
import servicedapartment.data.TypeInfo;
import servicedapartment.database.DatabaseFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

/**
 * Main class for Serviced Apartment System application.
 * @author Kunyaruk Katebunlu
 * @author Thanaphon Keawjam
 */
public class Main extends Application {
	/**
	 * The main entry point for all JavaFX applications. The start method is called after the init method has returned.
	 * @param primaryStage is the primary stage for this application, onto which the application scene can be set. 
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = (Parent)FXMLLoader.load(getClass().getResource("/servicedapartment/home/HomeUI.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Serviced Apartment System");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create database file, table and insert default informations and rooms into database.
	 */
	public void createDatabaseandDefaultRooms() {
		DatabaseFactory factory = DatabaseFactory.getInstance();
		factory.createDatabase();
		
		List<RoomInfo> roomInfoData = new ArrayList<>();
		List<TypeInfo> typeInfoData = new ArrayList<>();
		roomInfoData = factory.readDataFromRoom();
		typeInfoData = factory.readDataFromRoomType();
		
		if(roomInfoData.size() == 0 && typeInfoData.size() == 0) {
			TypeInfo typeStd = new TypeInfo("Studio", 2000, 12000, 45000);
			TypeInfo typeOne = new TypeInfo("1-Bedroom", 2800, 16800, 59000);
			TypeInfo typeTwo = new TypeInfo("2-Bedroom", 4300, 25800, 92000);
			TypeInfo typeThr = new TypeInfo("3-Bedroom", 5800, 34800, 122000);
			factory.insertDataToTypes(typeStd);
			factory.insertDataToTypes(typeOne);
			factory.insertDataToTypes(typeTwo);
			factory.insertDataToTypes(typeThr);
		
			RoomInfo rooms1 = new RoomInfo("101", 1);
			RoomInfo rooms2 = new RoomInfo("102", 1);
			RoomInfo rooms3 = new RoomInfo("103", 1);
			RoomInfo roomo1 = new RoomInfo("201", 2);
			RoomInfo roomo2 = new RoomInfo("202", 2);
			RoomInfo roomo3 = new RoomInfo("203", 2);
			RoomInfo roomw1 = new RoomInfo("301", 3);
			RoomInfo roomw2 = new RoomInfo("302", 3);
			RoomInfo roomw3 = new RoomInfo("303", 3);
			RoomInfo roomh1 = new RoomInfo("401", 4);
			RoomInfo roomh2 = new RoomInfo("402", 4);
			RoomInfo roomh3 = new RoomInfo("403", 4);
			factory.insertDataToRooms(rooms1);
			factory.insertDataToRooms(rooms2);
			factory.insertDataToRooms(rooms3);
			factory.insertDataToRooms(roomo1);
			factory.insertDataToRooms(roomo2);
			factory.insertDataToRooms(roomo3);
			factory.insertDataToRooms(roomw1);
			factory.insertDataToRooms(roomw2);
			factory.insertDataToRooms(roomw3);
			factory.insertDataToRooms(roomh1);
			factory.insertDataToRooms(roomh2);
			factory.insertDataToRooms(roomh3);
		}
		
		String[] admin = ReadFile.readFile();
		factory.setAdmin(admin);
	}
	
	/**
	 * Main method to running the application.
	 */
	public static void main(String[] args) {
		Main main = new Main();
		main.createDatabaseandDefaultRooms();
		launch(args);
	}
}
