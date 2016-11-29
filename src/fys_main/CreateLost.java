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
    
    public static GridPane getScreen() {
        /* Create grid pane */
        GridPane screen = new GridPane();
        screen.setAlignment(Pos.CENTER);
        screen.setHgap(10);
        screen.setVgap(10);
        screen.setPadding(new Insets(25, 25, 25, 25));
        
        
        /* Create all subheadings */
        Text nameText = new Text("Name:");
        nameText.getStyleClass().add("subheading");
        
        Text mainAddress = new Text("Main address:");
        mainAddress.getStyleClass().add("subheading");
        
        Text mainAddress2 = new Text("2nd address (optional):");
        mainAddress2.getStyleClass().add("subheading");
        
        Text contact = new Text("Contact:");
        contact.getStyleClass().add("subheading");
        
        
        
        /* Create all labels & inputs */
        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField();
        
        Label lastNameL = new Label("Last name:");
        TextField lastNameT = new TextField();
        
        
        
        /* Add everything to the grid */
        screen.add(nameText, 0, 0);
        
        screen.add(firstNameL, 0, 1);
        screen.add(firstNameT, 1, 1);
        
        screen.add(lastNameL, 0, 2);
        screen.add(lastNameT, 1, 2);
        
        
        
        return screen;
    }
    
}
