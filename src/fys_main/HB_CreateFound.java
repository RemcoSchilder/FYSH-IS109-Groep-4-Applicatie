package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javax.mail.MessagingException;

/**
 *
 * @author Thijs Timmermans
 */
public class HB_CreateFound {

    // Create gridpane
    private static GridPane screen = new GridPane();

    /* Table */
    private static TableView<HB_SearchBaggage.TableBaggage> table = new TableView<>();
    private static ObservableList<HB_SearchBaggage.TableBaggage> data = FXCollections.observableArrayList();

    /* Store last lost ID */
    private static int lostId;
    private static String travellerLostId;

    // Create Buttons
    private static Button cancel = new Button("Cancel");
    private static Button next = new Button("Next");
    private static Button addFoundLuggage = new Button("Add Found luggage");
    private static Label error = new Label();

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
        /* Clear all textfields when screen is refreshing */
        for (Node node : screen.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setText("");
            }
        }

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

        /* Add style */
        error.getStyleClass().add("error");

        Label verplichtL = new Label("* is required");
        Label airportL = new Label("Airport: *");
        Label labelNumberL = new Label("Label number:");
        Label flightNumberL = new Label("Flight number: *");
        Label destinationL = new Label("Destination: *");
        Label brandL = new Label("Brand:");
        Label colorL = new Label("Color:");
        Label typeL = new Label("Type:");

        Label characteristicsL = new Label("Characteristics:");
        characteristicsT.setPrefWidth(250);
        characteristicsT.setPrefHeight(100);

        // Alles toevoegen aan de gridpane
        screen.add(lostInfo, 0, 0);

        screen.add(verplichtL, 0, 1);

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
        screen.add(error, 0, 16, 10, 1);

        // Eventhandler voor toevoegen aan database
        addFoundLuggage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ( //Check if all the textfields are filled in
                        airportT.getText() == null
                        || airportT.getText().trim().isEmpty()
                        || flightNumberT.getText() == null
                        || flightNumberT.getText().trim().isEmpty()
                        || destinationT.getText() == null
                        || destinationT.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the required fields");

                    return;
                }

                Database DB = new Database();
                DB.setConn();
                DB.setQuery("INSERT INTO found (date, time, airport, labelNumber, flightNumber, destination, brand, color, type, characteristics, lost_found, status) "
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

                /* Get generated ID from the baggage */
                ResultSet getId = DB.getQuery("SELECT id FROM found WHERE "
                        + "date='" + dateFormat.format(date) + "' AND "
                        + "time='" + timeFormat.format(date) + "' AND "
                        + "labelNumber='" + labelNumberT.getText() + "'");

                /* Insert the traveller into the database with the ID from the baggage */
                try {
                    getId.next();
                    lostId = getId.getInt("id");
                } catch (SQLException se) {
                    //Handle errors for JDBC
                    se.printStackTrace();
                }

                screenThree();
            }
        });
    }

    private static void screenThree() {
        /* Clear grid */
        screen.getChildren().clear();

        /* Get matched results */
        Database DB = new Database();
        DB.setConn();
        ResultSet labelMatch = DB.getQuery("SELECT * FROM lost WHERE labelNumber='" + labelNumberT.getText() + "' AND labelNumber <> ''");

        try {
            if (labelMatch.next()) {
                /* Create success message */
                Text successTitle = new Text("There is a match with the label number");
                successTitle.getStyleClass().add("subheading");
                Text successText = new Text("Label numer '" + labelNumberT.getText() + "' has been found at airport " + labelMatch.getString("airport") + " on " + labelMatch.getString("date"));

                /* Create buttons */
                HBox buttons = new HBox();
                buttons.setPadding(new Insets(15, 12, 15, 12));
                buttons.setSpacing(10);
                Button ignore = new Button("Ignore");
                Button match = new Button("Match");
                buttons.getChildren().addAll(ignore, match);

                /* Add all elements to the grid */
                screen.add(successTitle, 0, 0);
                screen.add(successText, 0, 1);
                screen.add(buttons, 0, 3);

                // If ignore then go straight to the baggage list
                ignore.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Homepage_Baliemedewerker.pane.setCenter(HB_SearchBaggage.getScreen());
                    }
                });

                // If match then update the status to matched and go to success screen
                match.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        /* Update status to matched */
                        DB.setQuery("UPDATE lost SET status='matched' WHERE labelNumber='" + labelNumberT.getText() + "'");
                        DB.setQuery("UPDATE found SET status='matched' WHERE labelNumber='" + labelNumberT.getText() + "'");

                        /* Insert into matches table */
                        ResultSet getIds = DB.getQuery("SELECT lost.id AS lost_id, found.id AS found_id FROM lost, found WHERE lost.labelNumber='" + labelNumberT.getText() + "' AND found.labelNumber='" + labelNumberT.getText() + "'");

                        try {
                            getIds.next();
                            DB.setQuery("INSERT INTO matches(lost_id, found_id) VALUES ('" + getIds.getString("lost_id") + "', '" + getIds.getString("found_id") + "')");
                            travellerLostId = getIds.getString("lost_id");
                        } catch (SQLException se) {
                            //Handle errors for JDBC
                            se.printStackTrace();
                        }

                        /* Empty screen */
                        screen.getChildren().clear();

                        /* Create success message */
                        Text successTitle = new Text("The baggage has been matched!");
                        successTitle.getStyleClass().add("subheading");
                        Text successText = new Text("The traveller will be informed by email when the baggage will be send to their house");

                        /* Add all elements to the grid */
                        screen.add(successTitle, 0, 0);
                        screen.add(successText, 0, 1);

                        /* Sends email */
                        try {
                            ResultSet travelEmail = DB.getQuery("SELECT email FROM travellers WHERE lost_id='" + getIds.getString("lost_id") + "'");
                            travelEmail.next();
                            Email.sendMessage(travelEmail.getString("email"));
                        } catch (MessagingException ex) {
                            Logger.getLogger(HB_CreateLost.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(HB_CreateFound.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        /* makes label */
                        try {
                            ResultSet information = DB.getQuery("SELECT CONCAT ( firstName, ' ', lastName ) AS name, street, city, country, zipcode FROM travellers WHERE lost_id='" + travellerLostId + "'");
                            information.next();
                            MakePDF.createShippingLabel(information.getString("name"), information.getString("street"), information.getString("city"), information.getString("country"), information.getString("zipcode"), labelNumberT.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(HB_CreateFound.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
            } else {
                ResultSet otherMatch = DB.getQuery("SELECT * FROM lost WHERE (brand LIKE '%" + brandT.getText() + "%' "
                        + "OR color LIKE '%" + colorT.getText() + "%' "
                        + "OR type LIKE '%" + typeT.getText() + "%') "
                        + "AND status='open'");

                Text error = new Text("Label number not found, check if the following results have a match:");

                /* Initialize table */
                table = new TableView<>();
                data.removeAll(data);

                /* Create columns and assign them the right values */
                TableColumn id = new TableColumn("ID");
                id.setCellValueFactory(new PropertyValueFactory<>("id"));

                TableColumn brand = new TableColumn("Brand");
                brand.setCellValueFactory(new PropertyValueFactory<>("brand"));

                TableColumn color = new TableColumn("Color");
                color.setCellValueFactory(new PropertyValueFactory<>("color"));

                TableColumn type = new TableColumn("Type");
                type.setCellValueFactory(new PropertyValueFactory<>("type"));

                TableColumn characteristics = new TableColumn("characteristics");
                characteristics.setCellValueFactory(new PropertyValueFactory<>("characteristics"));

                /* For each row insert them into the data from the table */
                while (otherMatch.next()) {
                    data.add(new HB_SearchBaggage.TableBaggage(
                            otherMatch.getString("id"),
                            otherMatch.getString("brand"),
                            otherMatch.getString("color"),
                            otherMatch.getString("type"),
                            otherMatch.getString("characteristics"))
                    );
                }

                if (data.size() > 0) {
                    HBox buttons = new HBox();
                    buttons.setPadding(new Insets(15, 12, 15, 12));
                    buttons.setSpacing(10);
                    Button ignore = new Button("Ignore");
                    Button match = new Button("Match");
                    buttons.getChildren().addAll(ignore, match);

                    /* Set table colums and rows */
                    table.setItems(data);
                    table.getColumns().addAll(id, brand, color, type, characteristics);

                    /* Add all to screen */
                    screen.add(error, 0, 0);
                    screen.add(table, 0, 1);
                    screen.add(buttons, 0, 3);

                    // If ignore then go straight to the baggage list
                    ignore.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Homepage_Baliemedewerker.pane.setCenter(HB_SearchBaggage.getScreen());
                        }
                    });

                    // If match then update the status to matched and go to success screen
                    match.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            /* Get selected baggage */
                            HB_SearchBaggage.TableBaggage baggage = table.getSelectionModel().getSelectedItem();

                            /* Update status to matched */
                            DB.setQuery("UPDATE found SET status='matched' WHERE id='" + lostId + "'");
                            DB.setQuery("UPDATE lost SET status='matched' WHERE id='" + baggage.getId() + "'");

                            /* Insert into matches table */
                            DB.setQuery("INSERT INTO matches(lost_id, found_id) VALUES ('" + baggage.getId() + "', '" + lostId + "')");

                            /* makes label */
                            try {
                                ResultSet information = DB.getQuery("SELECT CONCAT ( firstName, ' ', lastName ) AS name, street, city, country, zipcode FROM travellers WHERE lost_id='" + travellerLostId + "'");
                                information.next();
                                MakePDF.createShippingLabel(information.getString("name"), information.getString("street"), information.getString("city"), information.getString("country"), information.getString("zipcode"), labelNumberT.getText());
                            } catch (SQLException ex) {
                                Logger.getLogger(HB_CreateFound.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            /* Empty screen */
                            screen.getChildren().clear();

                            /* Create success message */
                            Text successTitle = new Text("The baggage has been matched!");
                            successTitle.getStyleClass().add("subheading");
                            Text successText = new Text("The traveller will be informed by email when the baggage will be send to their house");

                            /* Add all elements to the grid */
                            screen.add(successTitle, 0, 0);
                            screen.add(successText, 0, 1);
                        }
                    });
                } else {
                    /* Create error message */
                    Text errorTitle = new Text("There are no matches");
                    errorTitle.getStyleClass().add("subheading");
                    Text errorText = new Text("Go to the baggage list");

                    /* Create button */
                    Button go = new Button("Go");

                    /* Add all elements to the grid */
                    screen.add(errorTitle, 0, 0);
                    screen.add(errorText, 0, 1);
                    screen.add(go, 0, 3);

                    // If ignore then go straight to the baggage list
                    go.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Homepage_Baliemedewerker.pane.setCenter(HB_SearchBaggage.getScreen());
                        }
                    });
                }
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

}
