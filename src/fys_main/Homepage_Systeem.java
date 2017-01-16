package fys_main;

import static fys_main.FYS_LostFound.pane;
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

    public static HBox hboxTop() {
        HBox hbox = new HBox();

        Image image = new Image("logo.jpg", 230, 48, false, false);
        ImageView v1 = new ImageView(image);

        hbox.getChildren().add(v1);
        hbox.getStyleClass().add("hbox");

        return hbox;
    }

    public static HBox hboxBottom() {
        HBox hbox = new HBox();

        logout = new Button("logout");
        logout.setMinSize(230, 48);

        hbox.getChildren().add(logout);
        hbox.getStyleClass().add("hbox");

        return hbox;
    }

    public static VBox vboxLeft() {
        VBox vbox = new VBox();

        vbox.getStyleClass().add("vbox");

        return vbox;
    }

    public static VBox vboxRight() {
        VBox vbox = new VBox();

        searchFirstName = new TextField();
        searchLastName = new TextField();
        searchEmail = new TextField();

        search = new Button("Search");
        addUser = new Button("Add User");
        edit = new Button("edit");
        delete = new Button("delete");

        Label labelFirstName = new Label("First Name: ");
        Label labelLastName = new Label("Last Name: ");
        Label labelEmail = new Label("Email: ");
        Label labelFunction = new Label("Function: ");

        labelFirstName.getStyleClass().add("labels");
        labelLastName.getStyleClass().add("labels");
        labelEmail.getStyleClass().add("labels");
        labelFunction.getStyleClass().add("labels");

        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "",
                        "Counter Assistant",
                        "Manager",
                        "System Manager"
                );
        boxFunction = new ComboBox(options);
        boxFunction.getSelectionModel().selectFirst();
        boxFunction.setMaxWidth(Double.MAX_VALUE);

        search.setMinSize(230, 48);
        addUser.setMinSize(230, 48);
        edit.setMinSize(230, 48);
        delete.setMinSize(230, 48);

        vbox.getChildren().addAll(labelFirstName, searchFirstName,
                labelLastName, searchLastName, labelEmail, searchEmail,
                labelFunction, boxFunction, search, addUser, edit, delete);

        vbox.getStyleClass().add("vbox");

        return vbox;
    }

    public static TableView createTable() {
        table = HS_ViewTable.users();

        return table;
    }

    public static Alert alertPopup() {
        yesButton = new ButtonType("DELETE");
        cancelButton = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Delete");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are u sure you want to delete this user?");
        alert.getButtonTypes().setAll(cancelButton, yesButton);

        return alert;
    }

    public static BorderPane getScreen() {
        pane.setTop(hboxTop());
        pane.setLeft(vboxLeft());
        pane.setCenter(createTable());
        pane.setRight(vboxRight());
        pane.setBottom(hboxBottom());

        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Database DB = new Database();
                Database.setConn();

                boolean where = false;

                String query = "SELECT firstname, lastname"
                        + ", username, password, email, function FROM users";

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

                searchResult = DB.getQuery(query);
                pane.setCenter(createTable());

            }
        });

        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HS_CreateUser.getScreen());

            }
        });

        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                editUser = table.getSelectionModel().getSelectedItem();

                if (editUser != null) {
                    pane.setCenter(HS_EditUser.getScreen());
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = table.getSelectionModel().getSelectedItem().getUsername();

                Optional<ButtonType> result = alertPopup().showAndWait();

                if (result.get() == yesButton) {
                    Database test = new Database();
                    Database.setConn();
                    test.setQuery("DELETE "
                            + "FROM users "
                            + "WHERE username = '" + username + "'");

                    pane.setCenter(createTable());
                } else if (result.get() == cancelButton) {
                    alertPopup().close();
                }

            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Start.getScreen());

            }
        });

        return pane;
    }
}
