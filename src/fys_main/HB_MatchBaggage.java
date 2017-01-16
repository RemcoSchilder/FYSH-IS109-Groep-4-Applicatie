package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HB_MatchBaggage {

    private static BorderPane screen = new BorderPane();
    private static VBox vbox = new VBox();
    
    /* Match table */
    private static TableView<TableMatch> table = new TableView<>();
    private static ObservableList<TableMatch> data = FXCollections.observableArrayList();
    
    /* Labels */
    private static Label detailsLabel = new Label("Details");
    
    /* Buttons */
    private static Button details = new Button("Details");
    private static Button setStatus = new Button("Set status");

    
    public static BorderPane getScreen() {
        getScreenOne();

        return screen;
    }
    

    public static void vboxRight() {
        /* Empty VBox */
        vbox.getChildren().clear();
        
        /* Set styles */
        details.setMinSize(230, 48);
        detailsLabel.getStyleClass().add("labels");
        vbox.getStyleClass().add("vbox");

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(detailsLabel, details);

        screen.setRight(vbox);
    }

    private static void getScreenOne() {
        /* Initialize default side bar */
        vboxRight();
        
        table = new TableView<>();
        data.removeAll(data);

        /* Create columns and assign them the right values */
        TableColumn lost_id = new TableColumn("Lost ID");
        lost_id.setCellValueFactory(new PropertyValueFactory<>("lost_id"));
        
        TableColumn lost_date = new TableColumn("Date");
        lost_date.setCellValueFactory(new PropertyValueFactory<>("lost_date"));

        TableColumn lost_labelNumber = new TableColumn("Label number");
        lost_labelNumber.setCellValueFactory(new PropertyValueFactory<>("lost_label_number"));

        TableColumn arrowRight = new TableColumn("");
        arrowRight.setCellValueFactory(new PropertyValueFactory<>("arrowRight"));

        TableColumn found_id = new TableColumn("Found ID");
        found_id.setCellValueFactory(new PropertyValueFactory<>("found_id"));
        
        TableColumn found_date = new TableColumn("Date");
        found_date.setCellValueFactory(new PropertyValueFactory<>("found_date"));

        TableColumn found_labelNumber = new TableColumn("Label number");
        found_labelNumber.setCellValueFactory(new PropertyValueFactory<>("found_label_number"));
        

        /* Initialize Database */
        Database db = new Database();
        db.setConn();

        /* Get all the lost luggage */
        ResultSet result = db.getQuery("SELECT * FROM matches");
        ResultSet lost;
        ResultSet found;
        
        try {
            /* For each row insert them into the data from the table */
            while (result.next()) {
                if(result == null) {
                    break;
                }
                
                lost = db.getQuery("SELECT * FROM lost WHERE id='" + result.getInt("lost_id") + "'");
                found = db.getQuery("SELECT * FROM found WHERE id='" + result.getInt("found_id") + "'");
                lost.next();
                found.next();
                
                data.add(new TableMatch(
                        result.getString("lost_id"),
                        lost.getString("date"),
                        lost.getString("labelNumber"),
                        result.getString("found_id"),
                        found.getString("date"),
                        found.getString("labelNumber"))
                );
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(lost_id, lost_date, lost_labelNumber, arrowRight, found_id, found_date, found_labelNumber);

        /* Create subheading */
        Text matchedLuggage = new Text("Matched luggage:");
        matchedLuggage.getStyleClass().add("subheading");

        /* Create fields with labels */
        screen.setCenter(table);
        screen.setTop(matchedLuggage);
        
        details.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getScreenDetails();
            }
        });
    }
    
    
    public static void getScreenDetails() {
        /* Add extra options to side menu */
        vboxRight();
        
        Label statusReturnedLabel = new Label("Set status to");
        statusReturnedLabel.getStyleClass().add("labels");
        
        Button statusReturned = new Button("Returned");
        statusReturned.setMinSize(230, 48);

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(statusReturnedLabel, statusReturned);
        
        /* Get selected baggage */
        TableMatch match = table.getSelectionModel().getSelectedItem();
        
        /* Initialize Database */
        Database db = new Database();
        db.setConn();

        /* Get all data */
        ResultSet lost = db.getQuery("SELECT * FROM lost WHERE id='" + match.getLost_id() + "'");
        ResultSet traveller = db.getQuery("SELECT * FROM travellers WHERE lost_id='" + match.getLost_id() + "'");
        ResultSet found = db.getQuery("SELECT * FROM found WHERE id='" + match.getFound_id() + "'");
        
        try {
            /* Database results */
            lost.next();
            traveller.next();
            found.next();
            
            /* Make GridPane with properties */
            GridPane pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setHgap(50);
            pane.setVgap(10);
            pane.setPadding(new Insets(5, 5, 5, 5));
            
            /* Create headings */
            Text lostDetails = new Text("Lost");
            lostDetails.getStyleClass().add("heading");

            Text travellerDetails = new Text("Traveller");
            travellerDetails.getStyleClass().add("heading");

            Text foundDetails = new Text("Found");
            foundDetails.getStyleClass().add("heading");
            
            
            /* Create subheadings */
            Text baggageDetails = new Text("Baggage details:");
            baggageDetails.getStyleClass().add("subheading");

            Text labelInfo = new Text("Baggage label info:");
            labelInfo.getStyleClass().add("subheading");

            Text baggageInfo = new Text("Baggage info:");
            baggageInfo.getStyleClass().add("subheading");
            
            Text baggageDetails2 = new Text("Baggage details:");
            baggageDetails2.getStyleClass().add("subheading");

            Text labelInfo2 = new Text("Baggage label info:");
            labelInfo2.getStyleClass().add("subheading");

            Text baggageInfo2 = new Text("Baggage info:");
            baggageInfo2.getStyleClass().add("subheading");
            
            Text name = new Text("Name:");
            name.getStyleClass().add("subheading");

            Text mainAddress = new Text("Main address:");
            mainAddress.getStyleClass().add("subheading");

            Text mainAddress2 = new Text("2nd address (optional):");
            mainAddress2.getStyleClass().add("subheading");

            Text contact = new Text("Contact:");
            contact.getStyleClass().add("subheading");

            
            /* Create labels */
            Label dateL = new Label("Date (Y-D-M):");
            Label timeL = new Label("Time:");   
            Label airportL = new Label("Airport");
            Label labelnumberL = new Label("Label number:");
            Label flightnumberL = new Label("Flight number:");
            Label lostfoundL = new Label("Lost/Found:");
            Label brandL = new Label("Brand:");
            Label colorL = new Label("Color:");
            Label typeL = new Label("Type:");
            Label characteristicsL = new Label("Characteristics:");
            
            Label dateL2 = new Label("Date (Y-D-M):");
            Label timeL2 = new Label("Time:");   
            Label airportL2 = new Label("Airport");
            Label labelnumberL2 = new Label("Label number:");
            Label flightnumberL2 = new Label("Flight number:");
            Label lostfoundL2 = new Label("Lost/Found:");
            Label brandL2 = new Label("Brand:");
            Label colorL2 = new Label("Color:");
            Label typeL2 = new Label("Type:");
            Label characteristicsL2 = new Label("Characteristics:");
            
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

            
            /* Create labels with information from 'lost' */
            Label lostDate = new Label(lost.getString("date"));
            Label lostTime = new Label(lost.getString("time"));
            Label lostAirport = new Label(lost.getString("airport"));
            Label lostLabelnumber = new Label(lost.getString("labelNumber"));
            Label lostFlightnumber = new Label(lost.getString("flightNumber"));
            Label lostLostfound = new Label(lost.getString("lost_found"));
            Label lostBrand = new Label(lost.getString("brand"));
            Label lostColor = new Label(lost.getString("color"));
            Label lostType = new Label(lost.getString("type"));
            Label lostCharacteristics = new Label(lost.getString("characteristics"));
            
            Label foundDate = new Label(found.getString("date"));
            Label foundTime = new Label(found.getString("time"));
            Label foundAirport = new Label(found.getString("airport"));
            Label foundLabelnumber = new Label(found.getString("labelNumber"));
            Label foundFlightnumber = new Label(found.getString("flightNumber"));
            Label foundLostfound = new Label(found.getString("lost_found"));
            Label foundBrand = new Label(found.getString("brand"));
            Label foundColor = new Label(found.getString("color"));
            Label foundType = new Label(found.getString("type"));
            Label foundCharacteristics = new Label(found.getString("characteristics"));
            
            Label firstName = new Label(traveller.getString("firstName"));
            Label lastName = new Label(traveller.getString("lastName"));
            Label street = new Label(traveller.getString("street"));
            Label street2 = new Label(traveller.getString("street2"));
            Label city = new Label(traveller.getString("city"));
            Label city2 = new Label(traveller.getString("city2"));
            Label zipCode = new Label(traveller.getString("zipCode"));
            Label zipCode2 = new Label(traveller.getString("zipCode2"));
            Label country = new Label(traveller.getString("country"));
            Label country2 = new Label(traveller.getString("country2"));
            Label email = new Label(traveller.getString("email"));
            Label telephone = new Label(traveller.getString("telephone"));
            
            
            /* Add 'lost' to the grid */
            pane.add(lostDetails, 0 , 0);
            
            pane.add(baggageDetails, 0 , 2);
            pane.add(dateL, 0, 3);
            pane.add(lostDate, 1 , 3);
            pane.add(timeL, 0, 4);
            pane.add(lostTime,1, 4);
            pane.add(airportL, 0, 5);
            pane.add(lostAirport, 1, 5);
            
            pane.add(labelInfo, 0, 7);
            pane.add(labelnumberL, 0, 9);
            pane.add(lostLabelnumber, 1, 9);
            pane.add(flightnumberL, 0, 10);
            pane.add(lostFlightnumber, 1, 10);
            pane.add(lostfoundL, 0, 11);
            pane.add(lostLostfound, 1, 11);

            pane.add(baggageInfo, 0, 13);
            pane.add(brandL, 0, 15);
            pane.add(lostBrand, 1, 15);
            pane.add(colorL, 0, 16);
            pane.add(lostColor, 1, 16);
            pane.add(typeL, 0, 17);
            pane.add(lostType, 1, 17);
            pane.add(characteristicsL, 0, 18);
            pane.add(lostCharacteristics, 1, 18);
            
            /* Add 'traveller' to the grid */
            pane.add(travellerDetails, 2 , 0);
            
            pane.add(name, 2 , 2);
            pane.add(firstNameL, 2, 3);
            pane.add(firstName, 3, 3);
            pane.add(lastNameL, 2, 4);
            pane.add(lastName, 3, 4);
            
            pane.add(mainAddress, 2, 6);
            pane.add(streetL, 2, 8);
            pane.add(street, 3, 8);
            pane.add(cityL, 2, 9);
            pane.add(city, 3, 9);
            pane.add(zipCodeL, 2, 10);
            pane.add(zipCode, 3, 10);
            pane.add(countryL, 2, 11);
            pane.add(country, 3, 11);
            
            pane.add(mainAddress2, 2, 13);
            pane.add(street2L, 2, 15);
            pane.add(street2, 3, 15);
            pane.add(city2L, 2, 16);
            pane.add(city2, 3, 16);
            pane.add(zipCode2L, 2, 17);
            pane.add(zipCode2, 3, 17);
            pane.add(country2L, 2, 18);
            pane.add(country2, 3, 18);

            pane.add(contact, 2, 20);
            pane.add(emailL, 2, 22);
            pane.add(email, 3, 22);
            pane.add(telephoneL, 2, 23);
            pane.add(telephone, 3, 23);
            
            /* Add 'found' to the grid */
            pane.add(foundDetails, 4 , 0);
            
            pane.add(baggageDetails2, 4 , 2);
            pane.add(dateL2, 4, 3);
            pane.add(foundDate, 5 , 3);
            pane.add(timeL2, 4, 4);
            pane.add(foundTime, 5, 4);
            pane.add(airportL2, 4, 5);
            pane.add(foundAirport, 5, 5);
            
            pane.add(labelInfo2, 4, 7);
            pane.add(labelnumberL2, 4, 9);
            pane.add(foundLabelnumber, 5, 9);
            pane.add(flightnumberL2, 4, 10);
            pane.add(foundFlightnumber, 5, 10);
            pane.add(lostfoundL2, 4, 11);
            pane.add(foundLostfound, 5, 11);

            pane.add(baggageInfo2, 4, 13);
            pane.add(brandL2, 4, 15);
            pane.add(foundBrand, 5, 15);
            pane.add(colorL2, 4, 16);
            pane.add(foundColor, 5, 16);
            pane.add(typeL2, 4, 17);
            pane.add(foundType, 5, 17);
            pane.add(characteristicsL2, 4, 18);
            pane.add(foundCharacteristics, 5, 18);

            screen.setCenter(pane);
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        
        
        statusReturned.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                db.setQuery("UPDATE lost SET status='returned' WHERE id='" + match.getLost_id() + "'");
                db.setQuery("UPDATE found SET status='returned' WHERE id='" + match.getFound_id() + "'");
                db.setQuery("DELETE FROM matches WHERE lost_id='" + match.getLost_id() + "' AND found_id='" + match.getFound_id() + "'");
                
                getScreenOne();
            }
        });
    }
    
    
    public static class TableMatch {

        private final SimpleStringProperty lost_id;
        private final SimpleStringProperty lost_date;
        private final SimpleStringProperty lost_label_number;
        private final SimpleStringProperty arrowRight;
        private final SimpleStringProperty found_id;
        private final SimpleStringProperty found_date;
        private final SimpleStringProperty found_label_number;

        public TableMatch(String lost_id, String lost_date, String lost_label_number, String found_id, String found_date, String found_label_number) {
            this.lost_id = new SimpleStringProperty(lost_id);
            this.lost_date = new SimpleStringProperty(lost_date);
            this.lost_label_number = new SimpleStringProperty(lost_label_number);
            this.arrowRight = new SimpleStringProperty("\u2194");
            this.found_id = new SimpleStringProperty(found_id);
            this.found_date = new SimpleStringProperty(found_date);
            this.found_label_number = new SimpleStringProperty(found_label_number);
        }

        public String getLost_id() {
            return lost_id.get();
        }
        
        public String getLost_date() {
            return lost_date.get();
        }
        
        public String getLost_label_number() {
            return lost_label_number.get();
        }
        
        public String getArrowRight() {
            return arrowRight.get();
        }
        
        public String getFound_id() {
            return found_id.get();
        }
        
        public String getFound_date() {
            return found_date.get();
        }
        
        public String getFound_label_number() {
            return found_label_number.get();
        }
        
    }
}
