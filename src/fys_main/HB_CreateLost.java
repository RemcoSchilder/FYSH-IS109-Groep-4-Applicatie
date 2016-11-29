package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
        GridPane screenOne = screenOne();
        
        return screenOne;
    }
    
    public static GridPane screenOne() {
        /* Create grid pane */
        GridPane screen = new GridPane();
        screen.setAlignment(Pos.CENTER);
        screen.setHgap(10);
        screen.setVgap(10);
        screen.setPadding(new Insets(25, 25, 25, 25));
        
        
        /* Create all subheadings */
        Text name = new Text("Name:");
        name.getStyleClass().add("subheading");
        
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
        
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                next.setText("Yeah");

            }
        });
        
        
        
        /* Create all labels & inputs */
        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField();
        
        Label lastNameL = new Label("Last name:");
        TextField lastNameT = new TextField();
        
        Label streetL = new Label("Street:");
        Label street2L = new Label("Street:");
        TextField streetT = new TextField();
        TextField street2T = new TextField();
        
        Label cityL = new Label("City:");
        Label city2L = new Label("City:");
        TextField cityT = new TextField();
        TextField city2T = new TextField();
        
        Label zipCodeL = new Label("Zip code:");
        Label zipCode2L = new Label("Zip code:");
        TextField zipCodeT = new TextField();
        TextField zipCode2T = new TextField();
        
        Label countryL = new Label("Country:");
        Label country2L = new Label("Country:");
        TextField countryT = new TextField();
        TextField country2T = new TextField();
        
        Label emailL = new Label("Email:");
        TextField emailT = new TextField();
        
        Label telephoneL = new Label("Telephone:");
        TextField telephoneT = new TextField();
        
        
        
        /* Add everything to the grid */
        screen.add(name, 0, 0);
        
        screen.add(firstNameL, 0, 1);
        screen.add(firstNameT, 1, 1);
        
        screen.add(lastNameL, 0, 2);
        screen.add(lastNameT, 1, 2);
        
        screen.add(mainAddress, 0, 4);
        screen.add(mainAddress2, 3, 4);
        
        screen.add(streetL, 0, 5);
        screen.add(streetT, 1, 5);
        screen.add(street2L, 3, 5);
        screen.add(street2T, 4, 5);
        
        screen.add(cityL, 0, 6);
        screen.add(cityT, 1, 6);
        screen.add(city2L, 3, 6);
        screen.add(city2T, 4, 6);
        
        screen.add(zipCodeL, 0, 7);
        screen.add(zipCodeT, 1, 7);
        screen.add(zipCode2L, 3, 7);
        screen.add(zipCode2T, 4, 7);
        
        screen.add(countryL, 0, 8);
        screen.add(countryT, 1, 8);
        screen.add(country2L, 3, 8);
        screen.add(country2T, 4, 8);
        
        screen.add(contact, 0, 10);
        
        screen.add(emailL, 0, 11);
        screen.add(emailT, 1, 11);
        
        screen.add(telephoneL, 0, 12);
        screen.add(telephoneT, 1, 12);
        
        screen.add(next, 4, 14);
        
        
        
        return screen;
    }
    
}
