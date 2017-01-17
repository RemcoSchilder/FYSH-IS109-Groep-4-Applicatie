package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Remco Schilder
 */
public class HM_luggageList {

    private static BorderPane screen = new BorderPane();
    private static TableView<TableLuggage> table = new TableView<>();
    private static TableLuggage editTraveller;
    private static TableLuggage detailsBaggage;
    private static ObservableList<TableLuggage> data = FXCollections.observableArrayList();

    private static Button search = new Button("Search");
    private static Button details = new Button("Details");

    private static Button delete = new Button("Delete");

    private static Button edit = new Button("Edit");

    private static TextField searchLabelNr = new TextField();
    private static TextField searchBrand = new TextField();
    private static TextField searchType = new TextField();
    private static TextField searchColor = new TextField();
    private static Label LabelNumber = new Label("Label Number: ");
    private static Label Brand = new Label("Brand: ");
    private static Label Type = new Label("Type: ");
    private static Label Color = new Label("Color: ");
    private static Label error = new Label();
    
    /* Buttons */
    private static Button cancel = new Button("Cancel");
    private static Button save = new Button("Save");
    private static ButtonType yesButton, cancelButton;

    public static BorderPane getScreen() {
        getScreenOne();
        vboxRight();

        return screen;
    }

    public static void vboxRight() {
        VBox vbox = new VBox();
        //image

        //buttons
        search.setMinSize(230, 48);
        searchLabelNr.setMinSize(230, 48);
        searchBrand.setMinSize(230, 48);
        searchType.setMinSize(230, 48);
        searchColor.setMinSize(230, 48);
        details.setMinSize(230, 48);

        delete.setMinSize(230,48);

        edit.setMinSize(230, 48);


        LabelNumber.getStyleClass().add("labels");
        Brand.getStyleClass().add("labels");
        Type.getStyleClass().add("labels");
        Color.getStyleClass().add("labels");

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(LabelNumber, searchLabelNr, Brand,

                searchBrand, Color, searchColor, Type, searchType, search, details, edit,delete);

        


        //style voor de vbox
        vbox.getStyleClass().add("vbox");
        
        screen.setRight(vbox);
    }

    private static BorderPane getScreenOne() {

        table = new TableView<>();
        data.removeAll(data);

        /* Create columns and assign them the right values */
        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        id.prefWidthProperty().bind(table.widthProperty().divide(26));
        
        TableColumn date = new TableColumn("Date");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn time = new TableColumn("Time");
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        time.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn airport = new TableColumn("Airport");
        airport.setCellValueFactory(new PropertyValueFactory<>("airport"));
        airport.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn labelNumber = new TableColumn("Label number");
        labelNumber.setCellValueFactory(new PropertyValueFactory<>("label_number"));
        labelNumber.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn flightNumber = new TableColumn("Flight number");
        flightNumber.setCellValueFactory(new PropertyValueFactory<>("flight_number"));
        flightNumber.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn destination = new TableColumn("Destination");
        destination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        destination.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn brand = new TableColumn("Brand");
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        brand.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn color = new TableColumn("Color");
        color.setCellValueFactory(new PropertyValueFactory<>("color"));
        color.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn characteristics = new TableColumn("characteristics");
        characteristics.setCellValueFactory(new PropertyValueFactory<>("characteristics"));
        characteristics.prefWidthProperty().bind(table.widthProperty().divide(26).multiply(3));

        TableColumn lost_found = new TableColumn("lost_found");
        lost_found.setCellValueFactory(new PropertyValueFactory<>("lost_found"));
        lost_found.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn status = new TableColumn("status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.prefWidthProperty().bind(table.widthProperty().divide(13));

        /* Initialize Database */
        Database db = new Database();
        db.setConn();

        /* Get all the lost luggage */
        ResultSet result = db.getQuery("SELECT * FROM lost UNION SELECT * FROM found");
        try {
            /* For each row insert them into the data from the table */
            while (result.next()) {
                data.add(new TableLuggage(
                        result.getString("id"),
                        result.getString("date"),
                        result.getString("time"),
                        result.getString("airport"),
                        result.getString("labelNumber"),
                        result.getString("flightNumber"),
                        result.getString("destination"),
                        result.getString("brand"),
                        result.getString("color"),
                        result.getString("type"),
                        result.getString("characteristics"),
                        result.getString("lost_found"),
                        result.getString("status"))
                );
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(id, date, time, airport, labelNumber, flightNumber, destination, brand, color, type, characteristics, lost_found, status);

        

        /* Create fields with labels */
        screen.setCenter(table);

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database DB = new Database();
                DB.setConn();

                data.clear();
                table.getItems().clear();

                boolean where = false;

                String query = "SELECT * "
                        + " FROM ( SELECT *"
                        + " FROM lost "
                        + " UNION "
                        + " SELECT *"
                        + " FROM found "
                        + ") AS search";

                if (!searchLabelNr.getText().equals("")) {
                    if (where) {
                        query += " AND labelNumber = '" + searchLabelNr.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE labelNumber = '" + searchLabelNr.getText() + "'";
                    }
                }
                if (!searchBrand.getText().equals("")) {
                    if (where) {
                        query += " AND brand = '" + searchBrand.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE brand = '" + searchBrand.getText() + "'";
                    }
                }
                if (!searchColor.getText().equals("")) {
                    if (where) {
                        query += " AND color = '" + searchColor.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE color = '" + searchColor.getText() + "'";
                    }
                }
                if (!searchType.getText().equals("")) {
                    if (where) {
                        query += " AND type = '" + searchType.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE type = '" + searchType.getText() + "'";
                    }
                }

                ResultSet searchResult = DB.getQuery(query);

                /* Get all the lost luggage */
                try {
                    /* For each row insert them into the data from the table */
                    while (searchResult.next()) {
                        data.add(new TableLuggage(
                                searchResult.getString("id"),
                                searchResult.getString("date"),
                                searchResult.getString("time"),
                                searchResult.getString("airport"),
                                searchResult.getString("labelNumber"),
                                searchResult.getString("flightNumber"),
                                searchResult.getString("destination"),
                                searchResult.getString("brand"),
                                searchResult.getString("color"),
                                searchResult.getString("type"),
                                searchResult.getString("characteristics"),
                                searchResult.getString("lost_found"),
                                searchResult.getString("status"))
                        );
                    }
                } catch (SQLException se) {
                    //Handle errors for JDBC
                    se.printStackTrace();
                }

                /* Set table colums and rows */
                table.setItems(data);

            }
        });
        
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                editTraveller = table.getSelectionModel().getSelectedItem();

                if (editTraveller != null) {
                    getScreenTwo();
                }
            }
        });

        details.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                detailsBaggage = table.getSelectionModel().getSelectedItem();
                
                if (detailsBaggage != null) {
                    getScreenDetails();
                }
                
            }
        });
        
        delete.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {
            
            TableLuggage labelnumber = table.getSelectionModel().getSelectedItem();
                      
                Optional<ButtonType> result = alertPopup().showAndWait();
                
                if(result.get() == yesButton)
                {                       
                    Database deleteluggage = new Database();
                    Database.setConn();
                    deleteluggage.setQuery("DELETE FROM " + labelnumber.getLost_found() + " WHERE id='" + labelnumber.getId() + "'" );
                    getScreenOne();
                }
                else if(result.get() == cancelButton)
                {
                    alertPopup().close();
                }
            }
        });
        return screen;
    
    }

    public static void getScreenTwo() {

        /* GridPane with properties */
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        /* Add style */
        error.getStyleClass().add("error");
        
       /* Create all subheadings */
        Text lostInfo = new Text("Lost information:");
        lostInfo.getStyleClass().add("subheading");
        
        Text labelInfo = new Text("Luggage label information:");
        labelInfo.getStyleClass().add("subheading");
        
        Text luggageInfo = new Text("Luggage information:");
        luggageInfo.getStyleClass().add("subheading");
        
        /* Textfields */
        TextField airportT = new TextField(editTraveller.getAirport());
        TextField labelNumberT = new TextField(editTraveller.getLabel_number());
        TextField flightNumberT = new TextField(editTraveller.getFlight_number());
        TextField destinationT = new TextField(editTraveller.getDestination());
        TextField brandT = new TextField(editTraveller.getBrand());
        TextField colorT = new TextField(editTraveller.getColor());
        TextField typeT = new TextField(editTraveller.getType());
        TextArea characteristicsT = new TextArea(editTraveller.getCharacteristics());
        
        /* Create all labels & inputs */
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
        grid.add(lostInfo, 0, 0);
        
        grid.add(airportL, 0, 3);
        grid.add(airportT, 1, 3, 10, 1);
        
        grid.add(labelInfo, 0, 5);
        
        grid.add(labelNumberL, 0, 6);
        grid.add(labelNumberT, 1, 6, 10, 1);
        
        grid.add(flightNumberL, 0, 7);
        grid.add(flightNumberT, 1, 7, 10, 1);
        
        grid.add(destinationL, 0, 8);
        grid.add(destinationT, 1, 8, 10, 1);
        
        grid.add(luggageInfo, 0, 10);
        
        grid.add(brandL, 0, 11);
        grid.add(brandT, 1, 11, 10, 1);
        
        grid.add(colorL, 0, 12);
        grid.add(colorT, 1, 12, 10, 1);
        
        grid.add(typeL, 0, 13);
        grid.add(typeT, 1, 13, 10, 1);
        
        grid.add(characteristicsL, 0, 14);
        grid.add(characteristicsT, 1, 14, 10, 1);
        
        grid.add(cancel, 0, 15);
        grid.add(save, 1, 15, 10, 1);
        grid.add(error, 0, 16, 10, 1);
        
        // All event handlers from screen two
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if (
                        airportT.getText() == null || 
                        airportT.getText().trim().isEmpty() ||
                        labelNumberT.getText() == null || 
                        labelNumberT.getText().trim().isEmpty() ||
                        flightNumberT.getText() == null || 
                        flightNumberT.getText().trim().isEmpty() ||
                        destinationT.getText() == null || 
                        destinationT.getText().trim().isEmpty() ||
                        brandT.getText() == null || 
                        brandT.getText().trim().isEmpty() ||
                        colorL.getText() == null || 
                        colorL.getText().trim().isEmpty() ||
                        typeT.getText() == null || 
                        typeT.getText().trim().isEmpty() ||
                        characteristicsT.getText() == null || 
                        characteristicsT.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the fields");
                    
                    return;
                }
                
                Database DB = new Database();
                DB.setConn();
                DB.setQuery("UPDATE " + editTraveller.getLost_found() + " "
                        + "SET "
                        + "airport='" + airportT.getText() + "', "
                        + "labelNumber='" + labelNumberT.getText() + "', "
                        + "flightNumber='" + flightNumberT.getText() + "', "
                        + "destination='" + destinationT.getText() + "', "
                        + "brand='" + brandT.getText() + "', "
                        + "color='" + colorT.getText() + "', "
                        + "type='" + typeT.getText() + "', "
                        + "characteristics='" + characteristicsT.getText() + "' "
                        + "WHERE id='" + editTraveller.getId() + "'");
               
                getScreenOne();
            }
        });
        
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getScreenOne();
            }
        });
        
        screen.setCenter(grid);
    }
    
    public static void getScreenDetails(){
        /* Initialize Database */
        Database db = new Database();
        db.setConn();

        /* Get info from the traveller */
        ResultSet traveller = db.getQuery("SELECT * FROM travellers WHERE lost_id='" + detailsBaggage.getId() + "'");
        
        /* Make GridPane with properties */
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(75);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        
        /* Create headings */
        Text lostDetails = new Text("Lost");
        lostDetails.getStyleClass().add("heading");

        Text travellerDetails = new Text("Traveller");
        travellerDetails.getStyleClass().add("heading");
        
        /*Create subheadings */
        Text baggageDetails = new Text("Baggage details:");
        baggageDetails.getStyleClass().add("subheading");
        
        Text labelInfo = new Text("Baggage label information:");
        labelInfo.getStyleClass().add("subheading");
        
        Text baggageInfo = new Text("Baggage information:");
        baggageInfo.getStyleClass().add("subheading");

        Text name = new Text("Name:");
        name.getStyleClass().add("subheading");

        Text mainAddress = new Text("Main address:");
        mainAddress.getStyleClass().add("subheading");

        Text mainAddress2 = new Text("2nd address (optional):");
        mainAddress2.getStyleClass().add("subheading");

        Text contact = new Text("Contact:");
        contact.getStyleClass().add("subheading");
        
        
        /*Create labels */
        Label date = new Label("Date (Y-D-M):");
        Label time = new Label("Time:");   
        Label airport = new Label("Airport");
        Label labelnumber = new Label("Label number:");
        Label flightnumber = new Label("Flight number:");
        Label lostfound = new Label("Lost/Found:");
        Label brand = new Label("Brand:");
        Label color = new Label("Color:");
        Label type = new Label("Type:");
        Label characteristics = new Label("Characteristics:");
        
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
        
        try {
            traveller.next();
            
            /*Create labels with information from the table */
            Label date2 = new Label(detailsBaggage.getDate());
            Label time2 = new Label(detailsBaggage.getTime());
            Label airport2 = new Label(detailsBaggage.getAirport());
            Label labelnumber2 = new Label(detailsBaggage.getLabel_number());
            Label flightnumber2 = new Label(detailsBaggage.getFlight_number());
            Label lostfound2 = new Label(detailsBaggage.getLost_found());
            Label brand2 = new Label(detailsBaggage.getBrand());
            Label color2 = new Label(detailsBaggage.getColor());
            Label type2 = new Label(detailsBaggage.getType());
            Label characteristics2 = new Label(detailsBaggage.getCharacteristics());

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
            pane.add(date, 0, 3);
            pane.add(date2, 1 , 3);
            pane.add(time, 0, 4);
            pane.add(time2,1, 4);
            pane.add(airport, 0, 5);
            pane.add(airport2, 1, 5);
            
            pane.add(labelInfo, 0, 7);
            pane.add(labelnumber, 0, 9);
            pane.add(labelnumber2, 1, 9);
            pane.add(flightnumber, 0, 10);
            pane.add(flightnumber2, 1, 10);
            pane.add(lostfound, 0, 11);
            pane.add(lostfound2, 1, 11);

            pane.add(baggageInfo, 0, 13);
            pane.add(brand, 0, 15);
            pane.add(brand2, 1, 15);
            pane.add(color, 0, 16);
            pane.add(color2, 1, 16);
            pane.add(type, 0, 17);
            pane.add(type2, 1, 17);
            pane.add(characteristics, 0, 18);
            pane.add(characteristics2, 1, 18);
            
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

            screen.setCenter(pane);
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }
    
    public static Alert alertPopup() {
        yesButton = new ButtonType("DELETE");
        cancelButton = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Luggage");
        alert.setContentText("Are u sure you want to delete this Luggage?");
        alert.getButtonTypes().setAll(cancelButton, yesButton);
        
        return alert;
    }
    
    public static class TableLuggage {

        private final SimpleStringProperty id;
        private final SimpleStringProperty date;
        private final SimpleStringProperty time;
        private final SimpleStringProperty airport;
        private final SimpleStringProperty label_number;
        private final SimpleStringProperty flight_number;
        private final SimpleStringProperty destination;
        private final SimpleStringProperty brand;
        private final SimpleStringProperty color;
        private final SimpleStringProperty type;
        private final SimpleStringProperty characteristics;
        private final SimpleStringProperty lost_found;
        private final SimpleStringProperty status;

        private TableLuggage(String id, String date, String time, String airport, String label_number, String flight_number, String destination, String brand, String color, String type, String characteristics, String lost_found, String status) {

            this.id = new SimpleStringProperty(id);
            this.date = new SimpleStringProperty(date);
            this.time = new SimpleStringProperty(time);
            this.airport = new SimpleStringProperty(airport);
            this.label_number = new SimpleStringProperty(label_number);
            this.flight_number = new SimpleStringProperty(flight_number);
            this.destination = new SimpleStringProperty(destination);
            this.brand = new SimpleStringProperty(brand);
            this.color = new SimpleStringProperty(color);
            this.type = new SimpleStringProperty(type);
            this.characteristics = new SimpleStringProperty(characteristics);
            this.lost_found = new SimpleStringProperty(lost_found);
            this.status = new SimpleStringProperty(status);

        }

        public String getId() {
            return id.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }
        
        public String getDate() {
            return date.get();
        }

        public void setDate(String date) {
            this.date.set(date);
        }

        public String getTime() {
            return time.get();
        }

        public void setTime(String time) {
            this.time.set(time);
        }

        public String getAirport() {
            return airport.get();
        }

        public void setAirport(String airport) {
            this.airport.set(airport);
        }

        public String getLabel_number() {
            return label_number.get();
        }

        public void setLabel_number(String label_number) {
            this.label_number.set(label_number);
        }

        public String getFlight_number() {
            return flight_number.get();
        }

        public void setFlight_number(String flight_number) {
            this.flight_number.set(flight_number);
        }

        public String getDestination() {
            return destination.get();
        }

        public void setDestination(String destination) {
            this.destination.set(destination);
        }

        public String getBrand() {
            return brand.get();
        }

        public void setBrand(String brand) {
            this.brand.set(brand);
        }

        public String getColor() {
            return color.get();
        }

        public void setColor(String color) {
            this.color.set(color);
        }

        public String getType() {
            return type.get();
        }

        public void setType(String type) {
            this.type.set(type);
        }

        public String getCharacteristics() {
            return characteristics.get();
        }

        public void setCharacteristics(String characteristics) {
            this.characteristics.set(characteristics);
        }

        public String getLost_found() {
            return lost_found.get();
        }

        public void setLost_found(String lost_found) {
            this.lost_found.set(lost_found);
        }

        public String getStatus() {
            return status.get();
        }

        public void setStatus(String status) {
            this.status.set(status);
        }

    }
}
