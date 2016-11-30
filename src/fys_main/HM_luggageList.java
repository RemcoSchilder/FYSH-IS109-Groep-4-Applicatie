package fys_main;

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
public class HM_luggageList {
    
    private static GridPane screen = new GridPane();
    private static TableView<TableLuggage> table = new TableView<>();
    private static ObservableList<TableLuggage> data = FXCollections.observableArrayList();
    
    public static GridPane getScreen() {
        getScreenOne();
        
        return screen;
    }
    
    private static GridPane getScreenOne() {
        /* Create columns and assign them the right values */
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        TableColumn labelNumber = new TableColumn("Label number");
        labelNumber.setCellValueFactory(new PropertyValueFactory<>("label_number"));
        
        TableColumn type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        TableColumn brand= new TableColumn("Brand");
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        
        TableColumn color= new TableColumn("Color");
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        
        TableColumn status= new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        
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
                        result.getInt("label_number"),  
                        result.getString("type"), 
                        result.getString("brand"), 
                        result.getString("color"), 
                        result.getString("status"))
                );
            }
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        }
        
        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(labelNumber, date, type, brand, color, status);
        
        
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
        private final SimpleStringProperty label_number;
        private final SimpleStringProperty type;
        private final SimpleStringProperty brand;
        private final SimpleStringProperty color;
        private final SimpleStringProperty status;
    
        private TableLuggage (String date, int label_number, String type, String brand, String color, String status) {
            this.date = new SimpleStringProperty(date);
            this.label_number = new SimpleStringProperty(String.valueOf(label_number));
            this.type = new SimpleStringProperty(type);
            this.brand = new SimpleStringProperty(brand);
            this.color = new SimpleStringProperty(color);
            this.status = new SimpleStringProperty(status);
        }
        
        public String getLabel_number() {
            return label_number.get();
        }
 
        public void setLabel_number(String label_number) {
            this.label_number.set(label_number);
        }
        
        public String getDate() {
            return date.get();
        }
 
        public void setDate(String date) {
            this.date.set(date);
        }
        
        public String getType() {
            return type.get();
        }
 
        public void setType(String type) {
            this.type.set(type);
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
        
        public String getStatus() {
            return status.get();
        }
 
        public void setStatus(String status) {
            this.status.set(status);
        }
    
    }
}
