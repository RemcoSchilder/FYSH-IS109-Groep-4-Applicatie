package fys_main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HB_CreateLost {
    
    public HB_CreateLost() {
        
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
        
        
        
        /* Create buttons */
        Button cancel = new Button("Cancel");
        Button next = new Button("Next");
        Button addLostLuggage = new Button("Add lost luggage");
        
        
        
        /* Create all labels & inputs */
        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField();
        
        Label lastNameL = new Label("Last name:");
        TextField lastNameT = new TextField();
        
        Label streetL = new Label("Street:");
        TextField streetT = new TextField();
        
        Label cityL = new Label("City:");
        TextField cityT = new TextField();
        
        Label zipCodeL = new Label("Zip code:");
        TextField zipCodeT = new TextField();
        
        Label countryL = new Label("Country:");
        TextField countryT = new TextField();
        
        
        
        /* Add everything to the grid */
        screen.add(nameText, 0, 0);
        
        screen.add(firstNameL, 0, 1);
        screen.add(firstNameT, 1, 1);
        
        screen.add(lastNameL, 0, 2);
        screen.add(lastNameT, 1, 2);
        
        screen.add(mainAddress, 0, 4);
        
        screen.add(streetL, 0, 5);
        screen.add(streetT, 1, 5);
        
        screen.add(cityL, 0, 6);
        screen.add(cityT, 1, 6);
        
        screen.add(zipCodeL, 0, 7);
        screen.add(zipCodeT, 1, 7);
        
        screen.add(countryL, 0, 8);
        screen.add(countryT, 1, 8);
        
        
        
        return screen;
    }
    
}
