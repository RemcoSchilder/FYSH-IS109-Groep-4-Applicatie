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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HB_SearchBaggage {

    private static BorderPane screen = new BorderPane();
    private static TableView<TableBaggage> table = new TableView<>();
    private static ObservableList<TableBaggage> data = FXCollections.observableArrayList();

    private static Button search = new Button("Search");
    private static Button edit = new Button("Edit");
    private static TextField searchLabelNr = new TextField();
    private static TextField searchBrand = new TextField();
    private static TextField searchType = new TextField();
    private static TextField searchColor = new TextField();
    private static Label LabelNumber = new Label("Label Number: ");
    private static Label Brand = new Label("Brand: ");
    private static Label Type = new Label("Type: ");
    private static Label Color = new Label("Color: ");
    
    /* Buttons */
    private static Button cancel = new Button("Cancel");
    private static Button save = new Button("Save");
    private static Label error = new Label();

    
    public static BorderPane getScreen() {
        getScreenOne();
        vboxRight();

        return screen;
    }
    

    public static void vboxRight() {
        VBox vbox = new VBox();
        
        //buttons
        search.setMinSize(230, 48);
        searchLabelNr.setMinSize(230, 48);
        searchBrand.setMinSize(230, 48);
        searchType.setMinSize(230, 48);
        searchColor.setMinSize(230, 48);
        edit.setMinSize(230, 48);
        
        LabelNumber.getStyleClass().add("labels");
        Brand.getStyleClass().add("labels");
        Type.getStyleClass().add("labels");
        Color.getStyleClass().add("labels");

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(LabelNumber, searchLabelNr, Brand, searchBrand, Color, searchColor,Type, searchType, search, edit);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");

        screen.setRight(vbox);
    }

    private static void getScreenOne() {
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

        TableColumn characteristics = new TableColumn("Characteristics");
        characteristics.setCellValueFactory(new PropertyValueFactory<>("characteristics"));
        characteristics.prefWidthProperty().bind(table.widthProperty().divide(26).multiply(3));

        TableColumn lost_found = new TableColumn("Lost/Found");
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
                data.add(new TableBaggage(
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

        /* Create subheading */
        Text searchLuggage = new Text("Search luggage:");
        searchLuggage.getStyleClass().add("subheading");
        
        /* Create fields with labels */
        screen.setCenter(table);
        screen.setTop(searchLuggage);
        

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
                        query += " AND labelNumber LIKE '%" + searchLabelNr.getText() + "%'";
                    } else {
                        where = true;
                        query += " WHERE labelNumber LIKE '%" + searchLabelNr.getText() + "%'";
                    }
                } 
                if (!searchBrand.getText().equals("")) {
                    if (where) {
                        query += " AND brand LIKE '%" + searchBrand.getText() + "%'";
                    } else {
                        where = true;
                        query += " WHERE brand LIKE '%" + searchBrand.getText() + "%'";
                    }
                } 
                if (!searchColor.getText().equals("")) {
                    if (where) {
                        query += " AND color LIKE '%" + searchColor.getText() + "%'";
                    } else {
                        where = true;
                        query += " WHERE color LIKE '%" + searchColor.getText() + "%'";
                    }
                } 
                if (!searchType.getText().equals("")) {
                    if (where) {
                        query += " AND type LIKE '%" + searchType.getText() + "%'";
                    } else {
                        where = true;
                        query += " WHERE type LIKE '%" + searchType.getText() + "%'";
                    }
                }
                
                ResultSet searchResult = DB.getQuery(query);

                /* Get all the lost luggage */
                try {
                    /* For each row insert them into the data from the table */
                    while (searchResult.next()) {
                        data.add(new TableBaggage(
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
                getScreenTwo();
            }
        });
    }
    
    
    public static void getScreenTwo() {
        /* GridPane with properties */
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        TableBaggage baggage = table.getSelectionModel().getSelectedItem();
        
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
        TextField airportT = new TextField(baggage.getAirport());
        TextField labelNumberT = new TextField(baggage.getLabel_number());
        TextField flightNumberT = new TextField(baggage.getFlight_number());
        TextField destinationT = new TextField(baggage.getDestination());
        TextField brandT = new TextField(baggage.getBrand());
        TextField colorT = new TextField(baggage.getColor());
        TextField typeT = new TextField(baggage.getType());
        TextArea characteristicsT = new TextArea(baggage.getCharacteristics());
        
        /* Create all labels & inputs */
        Label verplichtL = new Label("* is required");
        Label airportL = new Label("Airport: *");
        Label labelNumberL = new Label("Lable number:");
        Label flightNumberL = new Label("Flight number: *");
        Label destinationL = new Label("Destination: *");
        Label brandL = new Label("Brand:");
        Label colorL = new Label("Color:");
        Label typeL = new Label("Type:");
        
        Label characteristicsL = new Label("Characteristics:");
        characteristicsT.setPrefWidth(250);
        characteristicsT.setPrefHeight(100);
        
        /* Add everything to the grid */
        grid.add(lostInfo, 0, 0);
        
        grid.add(verplichtL, 0, 1);
        
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
                        //Check if all the textfields are filled in
                        airportT.getText() == null || 
                        airportT.getText().trim().isEmpty() ||
                        flightNumberT.getText() == null || 
                        flightNumberT.getText().trim().isEmpty() ||
                        destinationT.getText() == null || 
                        destinationT.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the required fields");
                    
                    return;
                }
                
                Database DB = new Database();
                DB.setConn();
                DB.setQuery("UPDATE " + baggage.getLost_found() + " "
                        + "SET "
                        + "airport='" + airportT.getText() + "', "
                        + "labelNumber='" + labelNumberT.getText() + "', "
                        + "flightNumber='" + flightNumberT.getText() + "', "
                        + "destination='" + destinationT.getText() + "', "
                        + "brand='" + brandT.getText() + "', "
                        + "color='" + colorT.getText() + "', "
                        + "type='" + typeT.getText() + "', "
                        + "characteristics='" + characteristicsT.getText() + "' "
                        + "WHERE id='" + baggage.getId() + "'");
               
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
    
    
    public static class TableBaggage {

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

        public TableBaggage(String id, String date, String time, String airport, String label_number, String flight_number, String destination, String brand, String color, String type, String characteristics, String lost_found, String status) {
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
        
        public TableBaggage(String id, String brand, String color, String type, String characteristics) {
            this.id = new SimpleStringProperty(id);
            this.date = new SimpleStringProperty();
            this.time = new SimpleStringProperty();
            this.airport = new SimpleStringProperty();
            this.label_number = new SimpleStringProperty();
            this.flight_number = new SimpleStringProperty();
            this.destination = new SimpleStringProperty();
            this.brand = new SimpleStringProperty(brand);
            this.color = new SimpleStringProperty(color);
            this.type = new SimpleStringProperty(type);
            this.characteristics = new SimpleStringProperty(characteristics);
            this.lost_found = new SimpleStringProperty();
            this.status = new SimpleStringProperty();
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
