package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {
    
    
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
   
    Label label = new Label("Baliemedewerker");
    grid.add(label, 50,50);

        
        return grid;
    }
}
