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
 * @author Thijs Timmermans
 */
public class HB_CreateFound {
    // Create gridpane
    private static GridPane screen = new GridPane();

    // Create Buttons
    private static Button cancel = new Button("Cancel");
    private static Button next = new Button("Next");
    private static Button addFoundLuggage = new Button("Add Found luggage");
    
    /* Get date and time */
    private static DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date date = new Date();
    
    /* Inputs */
    private static TextField dateT = new TextField(dateFormat.format(date));
    private static TextField airportT = new TextField();
    private static TextField labelNumberT = new TextField();
    private static TextField flightNumberT = new TextField();
    private static TextField destinationT = new TextField();
    private static TextField brandT = new TextField();
    private static TextField colorT = new TextField();
    private static TextField typeT = new TextField();
    private static TextArea characteristicsT = new TextArea();
    

    public static GridPane getScreen() {
        /* Pane properties */
        screen.setAlignment(Pos.CENTER);
        screen.setHgap(10);
        screen.setVgap(10);
        screen.setPadding(new Insets(25, 25, 25, 25));
        
        screenOne();

        return screen;
    }
    
    
    
    public static void screenOne() {
        /* Clear screen */
        screen.getChildren().clear();
        
        // Aanmaken van de subheadings
        Text lostInfo = new Text("Lost information:");
        lostInfo.getStyleClass().add("subheading");

        Text labelInfo = new Text("Luggage label information:");
        labelInfo.getStyleClass().add("subheading");

        Text luggageInfo = new Text("Luggage information:");
        luggageInfo.getStyleClass().add("subheading");

        // Aanmaken van de labels en textfields
        Label dateL = new Label("Date:");
        dateT.setDisable(true);

        Label airportL = new Label("Airport:");
        Label labelNumberL = new Label("Label number:");
        Label flightNumberL = new Label("Flight number:");
        Label destinationL = new Label("Destination:");
        Label brandL = new Label("Brand:");
        Label colorL = new Label("Color:");
        Label typeL = new Label("Type:");

        Label characteristicsL = new Label("Characteristics:");
        characteristicsT.setPrefWidth(250);
        characteristicsT.setPrefHeight(100);

        // Alles toevoegen aan de gridpane
        screen.add(lostInfo, 0, 0);
        
        screen.add(dateL, 0, 1);
        screen.add(dateT, 1, 1, 10, 1);

        screen.add(airportL, 0, 3);
        screen.add(airportT, 1, 3, 10, 1);

        screen.add(labelInfo, 0, 5);

        screen.add(labelNumberL, 0, 6);
        screen.add(labelNumberT, 1, 6, 10, 1);

        screen.add(flightNumberL, 0, 7);
        screen.add(flightNumberT, 1, 7, 10, 1);

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

        // Eventhandler voor toevoegen aan database
        addFoundLuggage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database DB = new Database();
                DB.setConn();
                DB.setQuery("INSERT INTO found (date, time, airport, labelNumber, flightNumber, destination, brand, color, type, characteristics) "
                        + "VALUES "
                        + "('" + dateFormat.format(date) + "',"
                        + " '" + timeFormat.format(date) + "',"
                        + " '" + airportT.getText() + "',"
                        + " '" + labelNumberT.getText() + "',"
                        + " '" + flightNumberT.getText() + "',"
                        + " '" + destinationT.getText() + "',"
                        + " '" + brandT.getText() + "',"
                        + " '" + colorT.getText() + "',"
                        + " '" + typeT.getText() + "',"
                        + " '" + characteristicsT.getText() + "',"
                        + " 'found',"
                        + " 'open')");
            }
        });
    }

}
