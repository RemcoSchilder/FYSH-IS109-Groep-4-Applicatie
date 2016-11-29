
package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Manager {
    
    private TableView table = new TableView();
    
    public GridPane maakHetScherm(){
        
    TableView table = new TableView();
    table.setEditable(true);

    TableColumn lostFound = new TableColumn("Lost/found");
    TableColumn labelNumber = new TableColumn("Label nuber");
    TableColumn type = new TableColumn("Type");
    TableColumn brand= new TableColumn("Brand");
    TableColumn color= new TableColumn("Color");
    TableColumn match= new TableColumn("Match(%)");
    TableColumn status= new TableColumn("Status");
    
    table.getColumns().addAll( lostFound, labelNumber, type, brand, color, match, status);
    
    Database hoi = new Database();
        
        ResultSet result = hoi.doQuery("SELECT * FROM lostLuggage");
        try {
            while(result.next()) {
                String Date  = result.getString("date");
                int Labelnumber = result.getInt("label_number");
                String Type = result.getString("type");
                String Brand = result.getString("brand");
                String Color = result.getString("color");
                String Status = result.getString("status");
                
                System.out.println("ID: " + Date);
                System.out.println("Datum: " + Labelnumber);
                System.out.println("Datum: " + Type);
                System.out.println("Datum: " + Brand);
                System.out.println("Datum: " + Color);
                System.out.println("Cijfer: " + Status);
                
            }
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        }

    GridPane grid = new GridPane();
    grid.getChildren().add(table);
   
    
        
        return grid;
}
}
