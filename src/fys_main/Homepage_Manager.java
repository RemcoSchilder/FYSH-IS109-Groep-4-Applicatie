
package fys_main;

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
    

    GridPane grid = new GridPane();
    grid.getChildren().add(table);
   
    Label label = new Label("Manager");
    grid.add(label, 50,50);
        
        return grid;
}
}
