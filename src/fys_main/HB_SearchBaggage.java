package fys_main;

import static fys_main.FYS_LostFound.grid;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HB_SearchBaggage {
    
    private static GridPane screen = new GridPane();
    private static TableView<TableLuggage> table = new TableView<>();
    private static ObservableList<TableLuggage> data = FXCollections.observableArrayList();
    
    public static GridPane getScreen() {
        getScreenOne();
        
        return screen;
    }
    
    private static GridPane getScreenOne() {
        
        grid = new GridPane();
        table = new TableView<>();
        data.removeAll(data);
        
        /* Create columns and assign them the right values */
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn time = new TableColumn("Time");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        
        TableColumn airport = new TableColumn("Airport");
        airport.setCellValueFactory(new PropertyValueFactory<>("airport"));
        
        TableColumn labelNumber = new TableColumn("Label number");
        labelNumber.setCellValueFactory(new PropertyValueFactory<>("label_number"));
        
        TableColumn flightNumber = new TableColumn("Flight number");
        flightNumber.setCellValueFactory(new PropertyValueFactory<>("flight_number"));
        
        TableColumn destination = new TableColumn("Destination");
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        
        TableColumn brand = new TableColumn("Brand");
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        
        TableColumn color = new TableColumn("Color");
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        
        TableColumn type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        /* Initialize Database */
        Database db = new Database();
        db.setConn();
        
        /* Get all the lost luggage */
        ResultSet result = db.getQuery("SELECT * FROM lostLuggage");
        try {
            /* For each row insert them into the data from the table */
            while(result.next()) {
                data.add(new TableLuggage(
                        result.getString("date"),
                        result.getString("time"),
                        result.getString("airport"),
                        result.getString("label_number"),  
                        result.getString("flight_number"),  
                        result.getString("destination"),  
                        result.getString("brand"), 
                        result.getString("color"), 
                        result.getString("type"))
                );
            }
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        }
        
        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(date, time, airport, labelNumber, flightNumber, destination, brand, color, type);
        
        /* Create subheading */
        Text searchLuggage = new Text("Search luggage:");
        searchLuggage.getStyleClass().add("subheading");
        
        /* Create fields with labels */
        screen.add(table, 0, 0);
        screen.add(searchLuggage, 0, 1);
    
        return screen;
    }
    
    public static class TableLuggage {
        
        private final SimpleStringProperty date;
        private final SimpleStringProperty time;
        private final SimpleStringProperty airport;
        private final SimpleStringProperty label_number;
        private final SimpleStringProperty flight_number;
        private final SimpleStringProperty destination;
        private final SimpleStringProperty brand;
        private final SimpleStringProperty color;
        private final SimpleStringProperty type;
    
        private TableLuggage (String date, String time, String airport, String label_number, String flight_number, String destination, String brand, String color, String type) {
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.airport = new SimpleStringProperty(airport);
            this.label_number = new SimpleStringProperty(label_number);
            this.flight_number = new SimpleStringProperty(flight_number);
            this.destination = new SimpleStringProperty(destination);
            this.brand = new SimpleStringProperty(brand);
            this.color = new SimpleStringProperty(color);
            this.type = new SimpleStringProperty(type);
        }
        
        public String getDate() {
            return date.get();
        }
 
        public void setDate(String date) {
            this.date.set(date);
        }
        
        public String getTime() {
            return time.get();
        }
 
        public void setTime(String time) {
            this.time.set(time);
        }
        
        public String getAirport() {
            return airport.get();
        }
 
        public void setAirport(String airport) {
            this.airport.set(airport);
        }
        
        public String getLabel_number() {
            return label_number.get();
        }
 
        public void setLabel_number(String label_number) {
            this.label_number.set(label_number);
        }
        
        public String getFlight_number() {
            return flight_number.get();
        }
 
        public void setFlight_number(String flight_number) {
            this.flight_number.set(flight_number);
        }
        
        public String getDestination() {
            return destination.get();
        }
 
        public void setDestination(String destination) {
            this.destination.set(destination);
        }
        
        public String getBrand() {
            return brand.get();
        }
 
        public void setBrand(String brand) {
            this.brand.set(brand);
        }
        
        public String getColor() {
            return color.get();
        }
 
        public void setColor(String color) {
            this.color.set(color);
        }
        
        public String getType() {
            return type.get();
        }
 
        public void setType(String type) {
            this.type.set(type);
        }
    
    }
}