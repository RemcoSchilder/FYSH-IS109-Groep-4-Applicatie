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
    
    
    public BorderPane maakHetScherm(){
        

        BorderPane border = new BorderPane();
        border.setPadding(new Insets(10, 20, 10, 20));
        
        
        TextField create = new TextField("Create");
        Button lost = new Button("lost bagage");
        VBox menu = new VBox();
        menu.getChildren().add(create);
        menu.getChildren().add(lost);
        menu.setAlignment(Pos.CENTER_LEFT);
        
        
        

       
        
    

        
        return border;
    }
}
