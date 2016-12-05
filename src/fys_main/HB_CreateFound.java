package fys_main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HB_CreateFound {
    
    /* Create grid pane */
    private static GridPane screen = new GridPane();
    
    /* Buttons */
    private static Button cancel = new Button("Cancel");
    private static Button next = new Button("Next");
    private static Button addFoundLuggage = new Button("Add Found luggage");
    
    public static GridPane getScreen() {
        /* GridPane properties */
        screen.setAlignment(Pos.CENTER);
        screen.setHgap(10);
        screen.setVgap(10);
        screen.setPadding(new Insets(25, 25, 25, 25));
        
      
        
        /* Event handlers */
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
            }
        });
        
        
        
        
    
    
    
        /* Clear Grid */
        screen.getChildren().clear();
        
        /* Create all subheadings */
        Text lostInfo = new Text("Lost information:");
        lostInfo.getStyleClass().add("subheading");
        
        Text labelInfo = new Text("Luggage label information:");
        labelInfo.getStyleClass().add("subheading");
        
        Text luggageInfo = new Text("Luggage information:");
        luggageInfo.getStyleClass().add("subheading");
        
        Text NameInfo = new Text("Name traveller:");
        NameInfo.getStyleClass().add("subheading");
        
        /* Create all labels & inputs */
        Label dateL = new Label("Date:");
        TextField dateT = new TextField();
        
        Label timeL = new Label("Time:");
        TextField timeT = new TextField();
        
        Label airportL = new Label("Airport:");
        TextField airportT = new TextField();
        
        Label labelnrL = new Label("Lable number:");
        TextField labelnrT = new TextField();
        
        Label flightnrL = new Label("Flight number:");
        TextField flightnrT = new TextField();
        
        Label destinationL = new Label("Destination:");
        TextField destinationT = new TextField();
        
        Label brandL = new Label("Brand:");
        TextField brandT = new TextField();
        
        Label colorL = new Label("Color:");
        TextField colorT = new TextField();
        
        Label typeL = new Label("Type:");
        TextField typeT = new TextField();
        
        Label characteristicsL = new Label("Characteristics:");
        TextArea characteristicsT = new TextArea();
        characteristicsT.setPrefWidth(250);
        characteristicsT.setPrefHeight(100);
        
        /* Get date */
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        Date date = new Date();
        
        /* Add everything to the grid */
        screen.add(NameInfo, 10, 5);
        
        screen.add(lostInfo, 0, 0);
        
        screen.add(dateL, 0, 1);
        screen.add(dateT, 1, 1, 10, 1);
        
        screen.add(timeL, 0, 2);
        screen.add(timeT, 1, 2, 10, 1);
        
        screen.add(airportL, 0, 3);
        screen.add(airportT, 1, 3, 10, 1);
        
        screen.add(labelInfo, 0, 5);
        
        screen.add(labelnrL, 0, 6);
        screen.add(labelnrT, 1, 6, 10, 1);
        
        screen.add(flightnrL, 0, 7);
        screen.add(flightnrT, 1, 7, 10, 1);
        
        screen.add(destinationL, 0, 8);
        screen.add(destinationT, 1, 8, 10, 1);
        
        screen.add(luggageInfo, 0, 10);
        
        screen.add(brandL, 0, 11);
        screen.add(brandT, 1, 11, 10, 1);
        
        screen.add(colorL, 0, 12);
        screen.add(colorT, 1, 12, 10, 1);
        
        screen.add(typeL, 0, 13);
        screen.add(typeT, 1, 13, 10, 1);
        
        screen.add(characteristicsL, 0, 14);
        screen.add(characteristicsT, 1, 14, 10, 1);
        
        
        screen.add(addFoundLuggage, 1, 15, 10, 1);
        
        // all event handlers from screen two
        addFoundLuggage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String status = "Open";
                
                Database test = new Database();
                test.setConn();
                test.setQuery("INSERT INTO lostLuggage (date, label_number, type, brand, color, status) VALUES "
                        + "('" + dateT.getText() + "',"
                        + " '" + labelnrT.getText() + "',"
                        + " '" + typeT.getText() + "',"
                        + " '" + brandT.getText() + "',"
                        + " '" + colorT.getText() + "',"
                        + " '" + status + "')");
            }
        });
   return screen;
    }
    
}