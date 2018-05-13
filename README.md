# Serviced Apartment System
**Table of contents**
- [About the Application](#about-the-application)
- [Technology and Knowledge](#new-technology-and-knowledge-that-we-have-learned)
- [Installation](#installation)
- [Contributors](#contributors)
- [References](#references)

## About the Application
**Serviced Apartment System** application is an application that will be use by clerks and administer/owner of any serviced apartment with four types of room (Studio, 1-Bedroom, 2-Bedroom and 3-Bedroom). This application can be separate in three main parts which are check-in, checkout and administer part.

### Check-in Part
- **In Check-in part**, the clerk will get all required information and put into the fields in first window then ask about room type and payment information from the customer. If press 'Check In' button in order's summary window, all information of an order will be save into the database. 

![checkin](source/chin.png)

### Checkout Part
- **In Checkout part**, the clerk will ask the customer for room number and the customer name and use to search for an order that match with the required information in the database. If it can be identical, the checkout will be successful and room status will be change into vacant state.

![checkout](source/cout.png)

### Administer Part
- **In Administer part**, user need to input the correct username and password before use this part. This part will let the administer(owner) be able to edit room rates, add/remove rooms, view room status in each day and able to view all order's history.

**Demo username and password**

|**Username**  |**Password**      |
|:-------------|-----------------:|
| admin        | 123456admin      |

![admin](source/admn.png)

### Packages
- **checkin** - Contains controller classes and user interface documents for check-in part.
- **checkout** - Contains controller class and user interface document for checkout part.
- **data** - Contains classes that related to received data and other useful classes.
- **dataandpreferences** - Contains controller classes and user interface documents for administer part.
- **database** - Contains class about database.
- **home** - Contains controller class and user interface document for home part.

## New Technology and Knowledge that we have learned
### Database
We've used **Database** which is a better choice to save the data instead of a file because it can query data, handle very large data sets and look up data from a database rapidly.

These are some basic database commands.
- CREATE DATABASE
```sql
CREATE DATABASE database_name;
```
**CREATE DATABASE** creates a new database.

- CREATE TABLE
```sql
CREATE TABLE table_name (column_1 datatype, column_2 datatype, column_3 datatype);
```
**CREATE TABLE** creates a new table in the database. It allows you to specify the name of the table and the name of each column in the table.

- INSERT INTO
```sql
INSERT INTO table_name (column_1, column_2, column_3) VALUES (value_1, 'value_2', value_3);
```
**INSERT INTO** statements are used to add a new row to a table.

- DELETE
```sql
DELETE FROM table_name WHERE some_column = some_value;
```
**DELETE** statements are used to remove rows from a table.

- DROP DATABASE
```sql
DROP DATABASE database_name;
```
**DROP DATABASE** statement is used to drop an existing SQL database.
- **Note:** Be careful before dropping a database. Deleting a database will result in loss of complete information stored in the database!

- SELECT
```sql
SELECT column_name FROM table_name;
```
**SELECT** statements are used to fetch data from a database. Every query will begin with SELECT.

- UPDATE
```sql
UPDATE table_name
SET some_column = some_value
WHERE some_column = some_value;
```
**UPDATE** statements allow you to edit rows in a table.

- WHERE
```sql
SELECT column_name(s)
FROM table_name
WHERE column_name operator value;
```
**WHERE** is a clause that indicates you want to filter the result set to include only rows where the following condition is true.

In this project, we've chosen **SQLite** because it's small, fast, reliable and it's simplicity. Moreover, there's no intermediary server process because SQLite is serverless, so, the process that wants to access the database reads and writes directly from the database files on disk.

You can connect the SQLite database by using SQLite JDBC (Java Database Connectivity) Driver.

This is some example from our project:
```java
import java.sql.*;

private final String url = "jdbc:sqlite:CustomerLog.db";
	
public void createDatabase() {
	// try to open a connection.
	try (Connection connect = DriverManager.getConnection(url)){
		if(connect != null) {
			// create a statement.
			Statement stm = connect.createStatement();
			String sqlRoom = "CREATE TABLE Rooms "
							+ "(ROOM_ID			INTEGER PRIMARY KEY,"
							+ "ROOM_NUMBER		VARCHAR(5),"
							+ "TYPE_ID			INT);";
			// executes the given SQL statement.
			stm.executeUpdate(sqlRoom);
			// close statement and connection.
			stm.close();
			connect.close();
		}
	} catch(SQLException e) { }
}
```
We also used **Prepared Statement** when insert the data into the database because it can accept bind variables, preventing SQL injection and formatting portability.

This is some example from our project:
```java
import java.sql.*;

private final String url = "jdbc:sqlite:CustomerLog.db";

public void insertDataToRooms(RoomInfo room) {
	try(Connection connect = DriverManager.getConnection(url)){
		connect.setAutoCommit(false);
		if(connect != null) {
			// set prepared statement,
			String data = "INSERT INTO Rooms (ROOM_NUMBER, TYPE_ID) VALUES (?, ?);";
			PreparedStatement pstm = connect.prepareStatement(data);
			// set bind variables.
			pstm.setString(1, room.getRoomNumb());
			pstm.setInt(2, room.getTypeId());
			// execute statement in PreparedStatement object.
			pstm.executeUpdate();
			// close statement and connection.
			pstm.close();
			connect.commit();
			connect.close();
		}
	} catch(SQLException e) { }
}
```

- Documentation: https://www.sqlite.org/docs.html
- Website: https://www.sqlite.org/index.html
- See also: https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html

### ThreeTen-Extra
**ThreeTen-Extra** provides additional date-time classes that complement those in JDK 8.

Not every piece of date/time logic is destined for the JDK. Some concepts are too specialized or too bulky to make it in. This project provides some of those additional classes as a well-tested and reliable jar.

In our project, we've used **Interval** class which represents the time on the time-line between two **Instants** (start and end instants) and other useful methods to check that the time is overlap or not to give a state to rooms.
- See also: http://www.threeten.org/threeten-extra/apidocs/org/threeten/extra/Interval.html

This is some code sample from our project:
```java
public boolean checkOverlap(String startA, String endA, String startB, String endB) {
	// create start/end instants and first interval.
	Instant instantStartA = Instant.parse(startA);
	Instant instantEndA = Instant.parse(endA);
	Interval A = Interval.of(instantStartA, instantEndA);
	// create another start/end instants and second interval.
	Instant instantStartB = Instant.parse(startB);
	Instant instantEndB = Instant.parse(endB);
	Interval B = Interval.of(instantStartB, instantEndB);
	// use the intervals to check date/time overlap.
	return A.overlaps(B) || A.encloses(B) || B.encloses(A) || A.equals(B);
}
```

- Documentation: http://www.threeten.org/threeten-extra/apidocs/index.html?org.threeten.extra-summary.html
- Website: http://www.threeten.org/threeten-extra/
- See also: https://github.com/ThreeTen/threeten-extra

### TableView<S>
In our project, we decided to show order's history or room number with room's status by put those information in form of table to make them easy to see.

To do that we need **TableView** which designed to visualize an unlimited number of rows of data, broken out into columns. A TableView is made up of a number of **TableColumn** instances. Each TableColumn in a table is responsible for displaying the contents of that column. 

This is the code sample from JavaFx API for create a TableView:
```java
 ObservableList<Person> teamMembers = ...;
 table.setItems(teamMembers);

 TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("First Name");
 firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
 
 TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("Last Name");
 lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));

 table.getColumns().setAll(firstNameCol, lastNameCol);
```
**Note:** This example and our project used an **ObservableList** because it is the simplest way of showing data in a TableView.

**See also:**
- Documentation for TableView<S>: https://docs.oracle.com/javase/8/javafx/api/toc.htm
- Documentation for TableColumn<S,T>: https://docs.oracle.com/javase/8/javafx/api/toc.htm

### Patterns
In this program, we use...
- **Singleton Pattern**: Use for data manager which will do all activities that related with database.
- **Observer Pattern**: Use to show an update after the administer(owner) add/remove room in Administer part.

## Installation
To run this application, your computer need...
 - Java version 8 or newer
 - Serviced Apartment Application.jar file
 
### How to run
#### **Option 1: Run a JAR file**
 1. Download a runnable JAR file named 'Serviced Apartment Application.jar' or 'Serviced Apartment System.zip' from the links below.
 2. Run 'Serviced Apartment Application.jar' file.
 
#####**Download links:**
 - JAR file: [Serviced Apartment Application.jar](https://github.com/NokKbl/Serviced-Apartment-System/blob/master/Download/Serviced%20Apartment%20Application.jar)
 - ZIP file: [Serviced Apartment System.zip](https://github.com/NokKbl/Serviced-Apartment-System/blob/master/Download/Serviced%20Apartment%20System.zip)
 
#### **Option 2: Compile by IDE(i.e. eclipse)**
 1. Download a ZIP file of this project from a link below then extract the file and open in your IDE.
 2. Add the Referenced Libraries (can be download from the links below).
 3. Run 'Main.java' class.

#####**Download links:**
- Click [here](https://github.com/NokKbl/Serviced-Apartment-System/archive/master.zip) to download ZIP file of this project.
- Click [here](https://github.com/NokKbl/Serviced-Apartment-System/tree/master/source/Library%20JAR%20file) to go to 'source/Library JAR file' folder.
- Click [here](https://bitbucket.org/xerial/sqlite-jdbc/downloads/) to go to download sqlite-jdbc Library website.
- Click [here](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.threeten%22%20AND%20a%3A%22threeten-extra%22) to go to download threeten-extra Library website.

## Contributors
- [**Kunyaruk Katebunlu**](https://github.com/NokKbl) (ID: 6010545692)
- [**Thanaphon Keawjam**](https://github.com/ThanaphonKeawjam) (ID: 6010545781)
	
**Note:** This program is a final project of Object Oriented Programming II Course which created by students in Faculty of Software and Knowledge Engineering, Kasetsart University.

## References
### for Database commands and SQLite
- http://www.sqlitetutorial.net/
- https://www.tutorialspoint.com/sqlite/sqlite_java.htm
- https://www.w3schools.com/sql/sql_where.asp

### for Other useful topics
- http://code.makery.ch/blog/javafx-dialogs-official/
- https://stackoverflow.com/
- https://www.youtube.com/channel/UCLEftf2Nu3I-sUBq9PbtQ9w
- https://docs.oracle.com/javase/8/javafx/api/toc.htm
