package fys_main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class CreateLost {
    
    public CreateLost() {
        
    }
    
    public GridPane getScreen() {
        /* Create grid pane */
        GridPane screen = new GridPane();
        screen.setAlignment(Pos.CENTER);
        screen.setHgap(10);
        screen.setVgap(10);
        screen.setPadding(new Insets(25, 25, 25, 25));
        
        
        /* Create all subheadings */
        Text scenetitle = new Text("Name");
        scenetitle.setFont(Font.font("Calibri", FontWeight.BOLD, 16));
        
        
        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField();
        
        
        return screen;
    }
    
}
