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
public class HM_TravellersList {

    /* Create Borderpane */
    private static BorderPane screen = new BorderPane();
    
     /* Table */
    private static TableView<TableLuggage> table = new TableView<>();
    private static TableLuggage editTraveller;
    private static TableLuggage detailsTraveller;
    private static ObservableList<TableLuggage> data = FXCollections.observableArrayList();

    /* Textfields */
    private static TextField searchTravellerID = new TextField();
    private static TextField searchFirstname = new TextField();
    private static TextField searchLastname = new TextField();
    private static TextField searchTelephone = new TextField();
   
    /* Labels */
    private static Label TravellerID = new Label("Traveller id: ");
    private static Label Firstname = new Label("First name: ");
    private static Label Lastname = new Label("Last name: ");
    private static Label Telephone = new Label("Telephone: ");
    private static Label error = new Label();
    
    /* Buttons */
    private static Button search = new Button("Search");
    private static Button details = new Button("Details");
    private static Button edit = new Button("Edit");
    private static Button cancel = new Button("Cancel");
    private static Button save = new Button("Save");

    //Method to open the screen with the travellerslist
    public static BorderPane getScreen() {
        getScreenOne();
        vboxRight();

        return screen;
    }

    //Method to create the sidebar at the right
    public static void vboxRight() {
        VBox vbox = new VBox();
        

        //Buttonsize and style
        search.setMinSize(230, 48);
        searchTravellerID.setMinSize(230, 48);
        searchFirstname.setMinSize(230, 48);
        searchLastname.setMinSize(230, 48);
        searchTelephone.setMinSize(230, 48);
        details.setMinSize(230, 48);
        edit.setMinSize(230, 48);

        TravellerID.getStyleClass().add("labels");
        Firstname.getStyleClass().add("labels");
        Lastname.getStyleClass().add("labels");
        Telephone.getStyleClass().add("labels");

        //Add everything to the vbox
        vbox.getChildren().addAll(TravellerID, searchTravellerID, Firstname,
                searchFirstname, Lastname, searchLastname, Telephone, searchTelephone, search, details, edit);

        //Set vbox style
        vbox.getStyleClass().add("vbox");
        
        screen.setRight(vbox);
    }
    
    //Method to open the screen with the travellerslist
    private static void getScreenOne() {

        table = new TableView<>();
        data.removeAll(data);

        /* Create columns and assign them the right values */
        TableColumn ID = new TableColumn("ID");
        ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        ID.prefWidthProperty().bind(table.widthProperty().divide(26));
                
        TableColumn firstName = new TableColumn("First name");
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        firstName.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn lastName = new TableColumn("Last name");
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        lastName.prefWidthProperty().bind(table.widthProperty().divide(13));
        
        TableColumn street = new TableColumn("Street");
        street.setCellValueFactory(new PropertyValueFactory<>("street"));
        street.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn city = new TableColumn("City");
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        city.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn zipCode = new TableColumn("Zip code");
        zipCode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        zipCode.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn country = new TableColumn("Country");
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        country.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn street2 = new TableColumn("Street (2nd)");
        street2.setCellValueFactory(new PropertyValueFactory<>("street2"));
        street2.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn city2 = new TableColumn("City (2nd)");
        city2.setCellValueFactory(new PropertyValueFactory<>("city2"));
        city2.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn zipCode2 = new TableColumn("Zip code (2nd)");
        zipCode2.setCellValueFactory(new PropertyValueFactory<>("zipcode2"));
        zipCode2.prefWidthProperty().bind(table.widthProperty().divide(13));

        TableColumn country2 = new TableColumn("Country (2nd)");
        country2.setCellValueFactory(new PropertyValueFactory<>("country2"));
        country2.prefWidthProperty().bind(table.widthProperty().divide(13));
        
        TableColumn email = new TableColumn("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.prefWidthProperty().bind(table.widthProperty().divide(26).multiply(3));

        TableColumn telephone = new TableColumn("Telephone");
        telephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephone.prefWidthProperty().bind(table.widthProperty().divide(13));

        /* Initialize Database */
        Database db = new Database();
        db.setConn();

        /* Get all the lost luggage */
        ResultSet result = db.getQuery("SELECT * FROM travellers");
        try {
            /* For each row insert them into the data from the table */
            while (result.next()) {
                data.add(new TableLuggage(
                        result.getString("id"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("street"),
                        result.getString("city"),
                        result.getString("zipCode"),
                        result.getString("country"),
                        result.getString("street2"),
                        result.getString("city2"),
                        result.getString("zipCode2"),
                        result.getString("country2"),
                        result.getString("email"),
                        result.getString("telephone"))
                );
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(ID, firstName, lastName, street, city, zipCode, country, street2, city2, zipCode2, country2, email, telephone);

        /* Create fields with labels */
        screen.setCenter(table);

        /* Event handlers */
        
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database DB = new Database();
                DB.setConn();

                data.clear();
                table.getItems().clear();

                boolean where = false;

                /* Create query */
                String query = "SELECT * "
                        + " FROM ( SELECT *"
                        + " FROM travellers "
                        + ") AS search";

                /* Check if the filled in textfields are equal to 
                data in the database */
                if (!searchTravellerID.getText().equals("")) {
                    if (where) {
                        query += " AND id = '" + searchTravellerID.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE id = '" + searchTravellerID.getText() + "'";
                    }
                }
                if (!searchFirstname.getText().equals("")) {
                    if (where) {
                        query += " AND firstName = '" + searchFirstname.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE firstName = '" + searchFirstname.getText() + "'";
                    }
                }
                if (!searchLastname.getText().equals("")) {
                    if (where) {
                        query += " AND lastName = '" + searchLastname.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE lastName = '" + searchLastname.getText() + "'";
                    }
                }
                if (!searchTelephone.getText().equals("")) {
                    if (where) {
                        query += " AND telephone = '" + searchTelephone.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE telephone = '" + searchTelephone.getText() + "'";
                    }
                }

                /* Execute query */
                ResultSet searchResult = DB.getQuery(query);

                /* Get all the lost luggage */
                try {
                    /* For each row insert them into the data from the table */
                    while (searchResult.next()) {
                        data.add(new TableLuggage(
                                searchResult.getString("id"),
                                searchResult.getString("firstName"),
                                searchResult.getString("lastName"),
                                searchResult.getString("street"),
                                searchResult.getString("city"),
                                searchResult.getString("zipCode"),
                                searchResult.getString("country"),
                                searchResult.getString("street2"),
                                searchResult.getString("city2"),
                                searchResult.getString("zipCode2"),
                                searchResult.getString("country2"),
                                searchResult.getString("email"),
                                searchResult.getString("telephone"))
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

        details.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                detailsTraveller = table.getSelectionModel().getSelectedItem();
                
                if (detailsTraveller != null) {
                    getScreenDetails();
                }
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
    }

    /*Method to open the edit screen */
    public static void getScreenTwo() {
        
        /* GridPane with properties */
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
       /* Create all subheadings */
        Text nameInfo = new Text("Name traveller:");
        nameInfo.getStyleClass().add("subheading");
        
        Text adressInfo = new Text("Adress traveller:");
        adressInfo.getStyleClass().add("subheading");
        
        Text contactInfo = new Text("Contact information:");
        contactInfo.getStyleClass().add("subheading");
        
        /* Textfields */
        TextField firstnameT = new TextField(editTraveller.getFirstname());
        TextField lastnameT = new TextField(editTraveller.getLastname());
        TextField streetT = new TextField(editTraveller.getStreet());
        TextField cityT = new TextField(editTraveller.getCity());
        TextField zipcodeT = new TextField(editTraveller.getZipcode());
        TextField countryT = new TextField(editTraveller.getCountry());
        TextField street2T = new TextField(editTraveller.getStreet2());
        TextField city2T = new TextField(editTraveller.getCity2());
        TextField zipcode2T = new TextField(editTraveller.getZipcode2());
        TextField country2T = new TextField(editTraveller.getCountry2());
        TextField emailT = new TextField(editTraveller.getEmail());
        TextField telephoneT = new TextField(editTraveller.getTelephone());
        
        /* Create all labels & inputs */
        Label firstnameL = new Label("First name:");
        Label lastnameL = new Label("Last name:");
        Label streetL = new Label("Street:");
        Label cityL = new Label("City:");
        Label zipcodeL = new Label("Zip code:");
        Label countryL = new Label("Country:");
        Label street2L = new Label("Street (2nd):");
        Label city2L = new Label("City (2nd):");
        Label zipcode2L = new Label("Zip code (2nd):");
        Label country2L = new Label("Country (2nd):");
        Label emailL = new Label("Email:");
        Label telephoneL = new Label("Telephone:");
        
        
        /* Add everything to the grid */
        grid.add(nameInfo, 0, 0);
        
        grid.add(firstnameL, 0, 3);
        grid.add(firstnameT, 1, 3, 10, 1);
        
        grid.add(lastnameL, 0, 4);
        grid.add(lastnameT, 1, 4, 10, 1);
        
        grid.add(adressInfo, 0, 6);
        
        grid.add(streetL, 0, 7);
        grid.add(streetT, 1, 7, 10, 1);
        
        grid.add(cityL, 0, 8);
        grid.add(cityT, 1, 8, 10, 1);
        
        grid.add(zipcodeL, 0, 9);
        grid.add(zipcodeT, 1, 9, 10, 1);
        
        grid.add(countryL, 0, 10);
        grid.add(countryT, 1, 10, 10, 1);
        
        grid.add(street2L, 0, 11);
        grid.add(street2T, 1, 11, 10, 1);
        
        grid.add(city2L, 0, 12);
        grid.add(city2T, 1, 12, 10, 1);
        
        grid.add(zipcode2L, 0, 13);
        grid.add(zipcode2T, 1, 13, 10, 1);
        
        grid.add(country2L, 0, 14);
        grid.add(country2T, 1, 14, 10, 1);
        
        grid.add(contactInfo, 0, 16);
        
        grid.add(emailL, 0, 17);
        grid.add(emailT, 1, 17, 10, 1);
        
        grid.add(telephoneL, 0, 18);
        grid.add(telephoneT, 1, 18, 10, 1);
        
        
        grid.add(cancel, 0, 19);
        grid.add(save, 1, 19, 10, 1);
        grid.add(error, 0, 20, 10, 1);
        
        /* Event handlers */
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            
                if (
                        //Check if all the textfields are filled in
                        
                        firstnameT.getText() == null || 
                        firstnameT.getText().trim().isEmpty() ||
                        lastnameT.getText() == null || 
                        lastnameT.getText().trim().isEmpty() ||
                        streetT.getText() == null || 
                        streetT.getText().trim().isEmpty() ||
                        cityT.getText() == null || 
                        cityT.getText().trim().isEmpty() ||
                        zipcodeT.getText() == null || 
                        zipcodeT.getText().trim().isEmpty() ||
                        countryT.getText() == null || 
                        countryT.getText().trim().isEmpty() ||
                        street2T.getText() == null || 
                        street2T.getText().trim().isEmpty() ||
                        city2T.getText() == null || 
                        city2T.getText().trim().isEmpty() ||
                        zipcode2T.getText() == null || 
                        zipcode2T.getText().trim().isEmpty() ||
                        country2T.getText() == null || 
                        country2T.getText().trim().isEmpty() ||
                        emailT.getText() == null || 
                        emailT.getText().trim().isEmpty() ||
                        telephoneT.getText() == null || 
                        telephoneT.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the fields");
                    
                    return;
                }
                
                //Update the data in the database with the filled in data
                Database DB = new Database();
                DB.setConn();
                DB.setQuery("UPDATE travellers SET "
                        + "firstName='" + firstnameT.getText() + "', "
                        + "lastName='" + lastnameT.getText() + "', "
                        + "street='" + streetT.getText() + "', "
                        + "city='" + cityT.getText() + "', "
                        + "zipCode='" + zipcodeT.getText() + "', "
                        + "country='" + countryT.getText() + "', "
                        + "street2='" + street2T.getText() + "', "
                        + "city2='" + city2T.getText() + "', "
                        + "zipCode2='" + zipcode2T.getText() + "', "
                        + "country2='" + country2T.getText() + "', "
                        + "email='" + emailT.getText() + "', "
                        + "telephone='" + telephoneT.getText() + "' "
                        + "WHERE id='" + editTraveller.getId() + "'");
               
                //Return to the travellerlist
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
    
    /* Method to open the details screen*/
    public static void getScreenDetails(){
    
     /* Make GridPane with properties */
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
    
        /*Create subheadings */
        Text travellerdetails = new Text("Traveller details:");
        travellerdetails.getStyleClass().add("subheading");
        
        Text adressinfo = new Text("Bagage label information:");
        adressinfo.getStyleClass().add("subheading");
        
        Text travellerinfo = new Text("Bagage Information:");
        travellerinfo.getStyleClass().add("subheading");
        
        /*Create labels */
        Label firstname = new Label("First name:");
        Label lastname = new Label("Last name:");   
        Label street = new Label("Street");
        Label city = new Label("City:");
        Label zipcode = new Label("Zip code:");
        Label country = new Label("Country:");
        Label streetTWO = new Label("Street (2nd):");
        Label cityTWO = new Label("City (2nd):");
        Label zipcodeTWO = new Label("Zip code (2nd):");
        Label countryTWO = new Label("Country  (2nd):");
        Label email = new Label("Email:");
        Label telephone = new Label("Telephone:");
        
        /*Create labels with information from the table */
        Label firstname2 = new Label(detailsTraveller.getFirstname());
        Label lastname2 = new Label(detailsTraveller.getLastname());
        Label street2 = new Label(detailsTraveller.getStreet());
        Label city2 = new Label(detailsTraveller.getCity());
        Label zipcode2 = new Label(detailsTraveller.getZipcode());
        Label country2 = new Label(detailsTraveller.getCountry());
        Label streetTWO2 = new Label(detailsTraveller.getStreet2());
        Label cityTWO2 = new Label(detailsTraveller.getCity2());
        Label zipcodeTWO2 = new Label(detailsTraveller.getZipcode2());
        Label countryTWO2 = new Label(detailsTraveller.getCountry2());
        Label email2 = new Label(detailsTraveller.getEmail());
        Label telephone2 = new Label(detailsTraveller.getTelephone());
        
       
        
        /*Add everything to the grid */
        pane.add(travellerdetails, 0 , 0);
        
        pane.add(firstname, 0, 2);
        pane.add(firstname2, 1 ,2);
        
        pane.add(lastname, 0, 3);
        pane.add(lastname2,1, 3);
        
        pane.add(street, 0, 4);
        pane.add(street2, 1, 4);
        
        pane.add(adressinfo, 0, 6);
        
        pane.add(city, 0, 8);
        pane.add(city2, 1, 8);
        
        pane.add(zipcode, 0, 9);
        pane.add(zipcode2, 1, 9);
        
        pane.add(country, 0,10);
        pane.add(country2, 1, 10);
        
        pane.add(travellerinfo, 0, 12);
        
        pane.add(streetTWO, 0, 14);
        pane.add(streetTWO2, 1, 14);
        
        pane.add(cityTWO, 0, 15);
        pane.add(cityTWO2, 1, 15);
        
        pane.add(zipcodeTWO, 0, 16);
        pane.add(zipcodeTWO2, 1, 16);
        
        pane.add(countryTWO, 0, 17);
        pane.add(countryTWO2, 1, 17);
        
        pane.add(email, 0, 18);
        pane.add(email2, 1, 18);
        
        pane.add(telephone, 0, 19);
        pane.add(telephone2, 1, 19);
        
        screen.setCenter(pane);
    }
    
  /* Class to create a table with the correct colums for the travellerslist */
    public static class TableLuggage {

        private final SimpleStringProperty id;
        private final SimpleStringProperty first_name;
        private final SimpleStringProperty last_name;
        private final SimpleStringProperty street;
        private final SimpleStringProperty city;
        private final SimpleStringProperty zip_code;
        private final SimpleStringProperty country;
        private final SimpleStringProperty street_2;
        private final SimpleStringProperty city_2;
        private final SimpleStringProperty zip_code_2;
        private final SimpleStringProperty country_2;
        private final SimpleStringProperty email;
        private final SimpleStringProperty telephone;

        private TableLuggage(String id, String first_name, String last_name, String street, String city, String zip_code, String country, String street_2, String city_2, String zip_code_2, String country_2, String email, String telephone) {
        
            this.id = new SimpleStringProperty(id);
            this.first_name = new SimpleStringProperty(first_name);
            this.last_name = new SimpleStringProperty(last_name);
            this.street = new SimpleStringProperty(street);
            this.city = new SimpleStringProperty(city);
            this.zip_code = new SimpleStringProperty(zip_code);
            this.country = new SimpleStringProperty(country);
            this.street_2 = new SimpleStringProperty(street_2);
            this.city_2 = new SimpleStringProperty(city_2);
            this.zip_code_2 = new SimpleStringProperty(zip_code_2);
            this.country_2 = new SimpleStringProperty(country_2);
            this.email = new SimpleStringProperty(email);
            this.telephone = new SimpleStringProperty(telephone);
        }

        /* Getters & Setters */
        
        public String getId() {
            return id.get();
        }

        public void setId(String id) {
            this.id.set(id);
        }

        public String getFirstname() {
            return first_name.get();
        }

        public void setFirstname(String first_name) {
            this.first_name.set(first_name);
        }

        public String getLastname() {
            return last_name.get();
        }

        public void setLastname(String last_name) {
            this.last_name.set(last_name);
        }

        public String getStreet() {
            return street.get();
        }

        public void setStreet(String street) {
            this.street.set(street);
        }

        public String getCity() {
            return city.get();
        }

        public void setCity(String city) {
            this.city.set(city);
        }

        public String getZipcode() {
            return zip_code.get();
        }

        public void setZipcode(String zip_code) {
            this.zip_code.set(zip_code);
        }

        public String getCountry() {
            return country.get();
        }

        public void setCountry(String country) {
            this.country.set(country);
        }

        public String getStreet2() {
            return street_2.get();
        }

        public void setStreet2(String street_2) {
            this.street_2.set(street_2);
        }

        public String getCity2() {
            return city_2.get();
        }

        public void setCity2(String city_2) {
            this.city_2.set(city_2);
        }

        public String getZipcode2() {
            return zip_code_2.get();
        }

        public void setZipcode2(String zip_code_2) {
            this.zip_code_2.set(zip_code_2);
        }

        public String getCountry2() {
            return country_2.get();
        }

        public void setCountry2(String country_2) {
            this.country_2.set(country_2);
        }
        
        public String getEmail() {
            return email.get();
        }

        public void setEmail(String email) {
            this.email.set(email);
        }

        public String getTelephone() {
            return telephone.get();
        }

        public void setTelephone(String telephone) {
            this.telephone.set(telephone);
        }

    }
}
