package servicedapartment.dataandpreferences;

import java.util.List;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import servicedapartment.data.RoomInfo;
import servicedapartment.database.Database;

public class RoomView implements java.util.Observer{
	
	private Room room;
	private Stage stage;
	private TableView<Room> table;
	private Database factory = Database.getInstance();

	public RoomView(Room room) {
		this.room = room;
		initComponents();
	}
	
	public void initComponents() {
		stage = new Stage();
		stage.setTitle("Table Room");
		BorderPane root = new BorderPane();
		root.setPrefSize(627.0, 640.0);
		
		table = new TableView<>();
		table.setPrefHeight(640.0);
		TableColumn<Room, String> typeColumn = new TableColumn<Room, String>("Room Type");
		typeColumn.setPrefWidth(192.0);
		typeColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomType"));
		
		TableColumn<Room, String> numberColumn = new TableColumn<Room, String>("Room Number");
		numberColumn.setPrefWidth(192.0);
		numberColumn.setCellValueFactory(new PropertyValueFactory<Room, String>("roomNumber"));
		
		table.getColumns().addAll(typeColumn, numberColumn);
		
		root.getChildren().add(table);
		root.setAlignment(table, Pos.CENTER);
		
		Scene scene = new Scene(new Group());
		((Group) scene.getRoot()).getChildren().add(table);
		
		stage.setScene(scene);
		stage.setResizable(false);
	}
	
	public void run() {
		displayRoom();
		stage.show();
	}
	
	public void exit() {
		stage.close();
	}
	
	public void displayRoom() {
		table.setItems(getRoomData());
	}
	
	public ObservableList<Room> getRoomData(){
		ObservableList<Room> data = FXCollections.observableArrayList();
		
		List<RoomInfo> roomList = factory.readDataFromRoom();
		Room room;
		for(RoomInfo x : roomList) {
			String type = "";
			String number = x.getRoomNumb();
			if(x.getTypeId() == 1) type = "Studio Room";
			else if(x.getTypeId() == 2) type = "1-Bed Room";
			else if(x.getTypeId() == 3) type = "2-Bed Room";
			else if(x.getTypeId() == 4) type = "3-Bed Room";
			
			room = new Room(type, number);
			
			data.add(room);
		}
		
		return data;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		displayRoom();
	}
	
}
