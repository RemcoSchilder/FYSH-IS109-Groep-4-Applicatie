package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HB_CreateLost {
    
    /* Create grid pane */
    private static GridPane screen = new GridPane();
    
    /* Buttons */
    private static Button cancel = new Button("Cancel");
    private static Button previous = new Button("Previous");
    private static Button next = new Button("Next");
    private static Button addLostLuggage = new Button("Add lost luggage");
    
    /* Get date and time */
    private static DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
    private static DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private static Date date = new Date();
    
    /* Name info data */
    private static Text nameData = new Text();
    
    /* Inputs */
    private static TextField firstNameT = new TextField();
    private static TextField lastNameT = new TextField();
    private static TextField streetT = new TextField();
    private static TextField street2T = new TextField();
    private static TextField cityT = new TextField();
    private static TextField city2T = new TextField();
    private static TextField zipCodeT = new TextField();
    private static TextField zipCode2T = new TextField();
    private static TextField countryT = new TextField();
    private static TextField country2T = new TextField();
    private static TextField emailT = new TextField();
    private static TextField telephoneT = new TextField();
    
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
        /* GridPane properties */
        screen.setAlignment(Pos.CENTER);
        screen.setHgap(10);
        screen.setVgap(10);
        screen.setPadding(new Insets(25, 25, 25, 25));
        
        /* Initialize screen one */
        screenOne();
        
        /* Event handlers */
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nameData.setText(firstNameT.getText() + " " + lastNameT.getText());
                screenTwo();
            }
        });
        
        previous.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                screenOne();
            }
        });
        
        return screen;
    }
    
    private static void screenOne() {
        /* Clear Grid */
        screen.getChildren().clear();
        
        /* Create all subheadings */
        Text name = new Text("Name:");
        name.getStyleClass().add("subheading");
        
        Text mainAddress = new Text("Main address:");
        mainAddress.getStyleClass().add("subheading");
        
        Text mainAddress2 = new Text("2nd address (optional):");
        mainAddress2.getStyleClass().add("subheading");
        
        Text contact = new Text("Contact:");
        contact.getStyleClass().add("subheading");
        
        /* Create all labels */
        Label firstNameL = new Label("First name:");
        Label lastNameL = new Label("Last name:");
        Label streetL = new Label("Street + nr:");
        Label street2L = new Label("Street + nr:");
        Label cityL = new Label("City:");
        Label city2L = new Label("City:");
        Label zipCodeL = new Label("Zip code:");
        Label zipCode2L = new Label("Zip code:");
        Label countryL = new Label("Country:");
        Label country2L = new Label("Country:");
        Label emailL = new Label("Email:");
        Label telephoneL = new Label("Telephone:");
        
        /* Add everything to the grid */
        screen.add(name, 0, 0);
        
        screen.add(firstNameL, 0, 1);
        screen.add(firstNameT, 1, 1, 5, 1);
        
        screen.add(lastNameL, 0, 2);
        screen.add(lastNameT, 1, 2, 5, 1);
        
        screen.add(mainAddress, 0, 4);
        screen.add(mainAddress2, 6, 4, 5, 1);
        
        screen.add(streetL, 0, 5);
        screen.add(streetT, 1, 5, 5, 1);
        
        screen.add(street2L, 6, 5);
        screen.add(street2T, 8, 5, 5, 1);
        
        screen.add(cityL, 0, 6);
        screen.add(cityT, 1, 6, 5, 1);
        screen.add(city2L, 6, 6);
        screen.add(city2T, 8, 6, 5, 1);
        
        screen.add(zipCodeL, 0, 7);
        screen.add(zipCodeT, 1, 7, 5, 1);
        screen.add(zipCode2L, 6, 7);
        screen.add(zipCode2T, 8, 7, 5, 1);
        
        screen.add(countryL, 0, 8);
        screen.add(countryT, 1, 8, 5, 1);
        screen.add(country2L, 6, 8);
        screen.add(country2T, 8, 8, 5, 1);
        
        screen.add(contact, 0, 10);
        
        screen.add(emailL, 0, 11);
        screen.add(emailT, 1, 11, 5, 1);
        
        screen.add(telephoneL, 0, 12);
        screen.add(telephoneT, 1, 12, 5, 1);
        
        screen.add(next, 4, 14, 5, 1);
    }
    
    private static void screenTwo() {
        /* Clear Grid */
        screen.getChildren().clear();
        
        /* Create all subheadings */
        Text lostInfo = new Text("Lost information:");
        lostInfo.getStyleClass().add("subheading");
        
        Text labelInfo = new Text("Luggage label information:");
        labelInfo.getStyleClass().add("subheading");
        
        Text luggageInfo = new Text("Luggage information:");
        luggageInfo.getStyleClass().add("subheading");
        
        Text nameInfo = new Text("Name traveller:");
        nameInfo.getStyleClass().add("subheading");
        
        /* Create all labels & inputs */
        Label dateL = new Label("Date:");
        dateT.setDisable(true);
        
        Label airportL = new Label("Airport:");
        Label labelNumberL = new Label("Lable number:");
        Label flightNumberL = new Label("Flight number:");
        Label destinationL = new Label("Destination:");
        Label brandL = new Label("Brand:");
        Label colorL = new Label("Color:");
        Label typeL = new Label("Type:");
        
        Label characteristicsL = new Label("Characteristics:");
        characteristicsT.setPrefWidth(250);
        characteristicsT.setPrefHeight(100);
        
        /* Add everything to the grid */
        screen.add(nameInfo, 15, 5);
        screen.add(nameData, 15, 6);
        
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
        
        screen.add(previous, 0, 15);
        screen.add(addLostLuggage, 1, 15, 10, 1);
        
        // All event handlers from screen two
        addLostLuggage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database DB = new Database();
                DB.setConn();
                DB.setQuery("INSERT INTO travellers (firstName, lastName, street, city, zipCode, country, street2, city2, zipCode2, country2, email, telephone) "
                        + "VALUES "
                        + "('" + firstNameT.getText() + "',"
                        + " '" + lastNameT.getText() + "',"
                        + " '" + streetT.getText() + "',"
                        + " '" + cityT.getText() + "',"
                        + " '" + zipCodeT.getText() + "',"
                        + " '" + countryT.getText() + "',"
                        + " '" + street2T.getText() + "',"
                        + " '" + city2T.getText() + "',"
                        + " '" + zipCode2T.getText() + "',"
                        + " '" + country2T.getText() + "',"
                        + " '" + emailT.getText() + "',"
                        + " '" + telephoneT.getText() + "')");
                
                DB.setQuery("INSERT INTO lost (date, time, airport, labelNumber, flightNumber, destination, brand, color, type, characteristics, lost_found, status) "
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
                        + " 'lost',"
                        + " 'open')");
               
                screenThree();
                /*FYS_LostFound.pane.setCenter(HB_SearchBaggage.getScreen());*/
            }
        });
    }
    
    
    private static void screenThree() {
        /* Clear grid */
        screen.getChildren().clear();
        
        /* Get matched results */
        Database DB = new Database();
        DB.setConn();
        ResultSet labelMatch = DB.getQuery("SELECT * FROM found WHERE labelNumber='" + labelNumberT.getText() + "'");
        
        try {
            if (labelMatch.next()) {
                /* Create success message */
                Text successTitle = new Text("There is a match with the label number");
                successTitle.getStyleClass().add("subheading");
                Text successText = new Text("Label numer '" + labelNumberT.getText() + "' has been found at airport " + labelMatch.getString("airport") + " on " + labelMatch.getString("date"));
                
                /* Create buttons */
                HBox buttons = new HBox();
                buttons.setMinWidth(200);
                Button cancel = new Button("Cancel");
                Button match = new Button("Match & send");
                buttons.getChildren().addAll(cancel, match);
                
                /* Add all elements to the grid */
                screen.add(successTitle, 0, 0);
                screen.add(successText, 0, 1);
                
                screen.add(buttons, 0, 3);
            } else {
                ResultSet otherMatch = DB.getQuery("SELECT * FROM found WHERE (brand='" + brandT.getText() + "' "
                        + "OR color='" + colorT.getText() + "' "
                        + "OR type='" + typeT.getText() + "') "
                        + "AND status='open'");
                
                Text error = new Text("Label number not found, check if the following results have a match:");
                screen.add(error, 0, 0);
            }
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
    
}
