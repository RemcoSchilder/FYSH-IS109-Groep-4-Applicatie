package fys_main;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {
    
    
    //private TableView table = new TableView();
    
    
    public BorderPane maakHetScherm(){
        

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10, 20, 10, 20));
        
        
       
        TextField create = new TextField("Create");
        Button lost = new Button("lost bagage");
        VBox menu = new VBox();
        menu.getChildren().add(create);
        menu.getChildren().add(lost);
        menu.setAlignment(Pos.CENTER);

        
        

       
        
      



//    TableView table = new TableView();
//    table.setEditable(true);
//
//    TableColumn lostFound = new TableColumn("Lost/found");
//    TableColumn labelNumber = new TableColumn("Label nuber");
//    TableColumn type = new TableColumn("Type");
//    TableColumn brand= new TableColumn("Brand");
//    TableColumn color= new TableColumn("Color");
//    TableColumn match= new TableColumn("Match(%)");
//    TableColumn status= new TableColumn("Status");
//    
//    table.getColumns().addAll( lostFound, labelNumber, type, brand, color, match, status);
//    
//
//    GridPane grid = new GridPane();
//    grid.getChildren().add(table);
//   
//    Label label = new Label("Baliemedewerker");
//    grid.add(label, 50,50);

        
        return border;
    }
}
