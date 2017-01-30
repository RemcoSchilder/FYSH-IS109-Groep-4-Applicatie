package fys_main;

import fys_main.HS_ViewTable.TableUsers;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Freek Kuijpers
 */
public class Homepage_Systeem {

    private static TableView<TableUsers> table;
    protected static ResultSet searchResult;
    protected static TableUsers editUser;
    private static TextField searchFirstName, searchLastName, searchEmail;
    private static ComboBox boxFunction;
    private static Button search, addUser, edit, delete, logout;
    private static ButtonType yesButton, cancelButton;
    protected static BorderPane pane;

    //creates hbox for the top of the borderpane
    public static HBox hboxTop() {
        //creates image
        Image image = new Image("logo.jpg", 230, 48, false, false);
        ImageView v1 = new ImageView(image);

        //create hbox and adds image to it
        HBox hbox = new HBox();
        hbox.getChildren().add(v1);
        hbox.getStyleClass().add("hbox");

        return hbox;
    }

    //creates hbox for the bottom of the borderpane
    public static HBox hboxBottom() {
        //gives the logout button its characteristics
        logout = new Button("logout");
        logout.setMinSize(230, 48);

        //creates hbox with characteristics and adds the logout button
        HBox hbox = new HBox();
        hbox.getChildren().add(logout);
        hbox.getStyleClass().add("hbox");

        return hbox;
    }

    //create left vbox
    public static VBox vboxLeft() {
        //create vbox and add style
        VBox vbox = new VBox();
        vbox.getStyleClass().add("vbox");

        return vbox;
    }

    //create right vbox
    public static VBox vboxRight() {
        VBox vbox = new VBox();

        //create/refresh textfields
        searchFirstName = new TextField();
        searchLastName = new TextField();
        searchEmail = new TextField();

        //gives buttons text and size
        search = new Button("Search");
        addUser = new Button("Add User");
        edit = new Button("edit");
        delete = new Button("delete");
        search.setMinSize(230, 48);
        addUser.setMinSize(230, 48);
        edit.setMinSize(230, 48);
        delete.setMinSize(230, 48);

        //gives labels text and adds styles
        Label labelFirstName = new Label("First Name: ");
        Label labelLastName = new Label("Last Name: ");
        Label labelEmail = new Label("Email: ");
        Label labelFunction = new Label("Function: ");
        labelFirstName.getStyleClass().add("labels");
        labelLastName.getStyleClass().add("labels");
        labelEmail.getStyleClass().add("labels");
        labelFunction.getStyleClass().add("labels");

        //options for combobox
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "",
                        "Counter Assistant",
                        "Manager",
                        "System Manager"
                );

        //creates combobox and adds width
        boxFunction = new ComboBox(options);
        boxFunction.getSelectionModel().selectFirst();
        boxFunction.setMaxWidth(Double.MAX_VALUE);
        
        //adds children and style to vbox
        vbox.getChildren().addAll(labelFirstName, searchFirstName,
                labelLastName, searchLastName, labelEmail, searchEmail,
                labelFunction, boxFunction, search, addUser, edit, delete);
        vbox.getStyleClass().add("vbox");

        return vbox;
    }
    
    //creates table
    public static TableView createTable() {
        //set HS_ViewTable.users() method in table
        table = HS_ViewTable.users();

        return table;
    }

    //creates popup
    public static Alert alertPopup() {
        //creates button
        yesButton = new ButtonType("DELETE");
        cancelButton = new ButtonType("No");
        
        //create popup and adds text + buttons
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are u sure you want to delete this user?");
        alert.getButtonTypes().setAll(cancelButton, yesButton);

        return alert;
    }

    //creates borderpane
    public static BorderPane getScreen() {
        pane = new BorderPane();
        //sets hbox's and vbox's in borderpane
        pane.setTop(hboxTop());
        pane.setLeft(vboxLeft());
        pane.setCenter(createTable());
        pane.setRight(vboxRight());
        pane.setBottom(hboxBottom());
        pane.getStyleClass().add("background");
        
        //search button action
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //connect to database
                Database DB = new Database();
                Database.setConn();
                
                //creates boolean
                boolean where = false;
                
                //creates query
                String query = "SELECT firstname, lastname"
                        + ", username, password, email, function FROM users";
                
                //adds the search textfield text to query
                if (!searchFirstName.getText().equals("")) {
                    if (where) {
                        query += " AND firstname = '" + searchFirstName.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE firstname LIKE '%" + searchFirstName.getText() + "%'";
                    }
                }
                if (!searchLastName.getText().equals("")) {
                    if (where) {
                        query += " AND lastname = '" + searchLastName.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE lastname LIKE '%" + searchLastName.getText() + "%'";
                    }
                }
                if (!searchEmail.getText().equals("")) {
                    if (where) {
                        query += " AND email = '" + searchEmail.getText() + "'";
                    } else {
                        where = true;
                        query += " WHERE email LIKE '%" + searchEmail.getText() + "%'";
                    }
                }
                if (!boxFunction.getValue().equals("")) {
                    if (where) {
                        query += " AND function = '" + boxFunction.getValue() + "'";
                    } else {
                        where = true;
                        query += " WHERE function = '" + boxFunction.getValue() + "'";
                    }
                }
                
                //sets query in searchresult
                searchResult = DB.getQuery(query);
                
                //sets table in center
                pane.setCenter(createTable());

            }
        });
        
        //adduser button action
        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //sets HS_CreateUser.getScreen() in center
                pane.setCenter(HS_CreateUser.getScreen());

            }
        });

        //edit button action
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //takes info from selected tablerow
                editUser = table.getSelectionModel().getSelectedItem();

                //checks info
                if (editUser != null) {
                    //sets HS_EditUser.getScreen() as center
                    pane.setCenter(HS_EditUser.getScreen());
                }
            }
        });

        //delete button action
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //gets username from tablerow
                String username = table.getSelectionModel().getSelectedItem().getUsername();

                //shows popup
                Optional<ButtonType> result = alertPopup().showAndWait();
                
                //yesbutton pressed
                if (result.get() == yesButton) {
                    //connects to database
                    Database test = new Database();
                    Database.setConn();
                    
                    //sets query
                    test.setQuery("DELETE "
                            + "FROM users "
                            + "WHERE username = '" + username + "'");
                    
                    //sets createTable() as center
                    pane.setCenter(createTable());
                } 
                //cancel button pressed
                else if (result.get() == cancelButton) {
                    //closes popup
                    alertPopup().close();
                }

            }
        });

        //logout button pressed
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //clears pane and gets login screen
                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());

            }
        });

        return pane;
    }

}
