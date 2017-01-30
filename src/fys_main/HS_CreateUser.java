package fys_main;

import static fys_main.Homepage_Systeem.createTable;
import static fys_main.Homepage_Systeem.pane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class HS_CreateUser {

    private static Label error;
    private static Button cancel, addUser;

    //create screen
    public static GridPane getScreen() {
        //creates gridpane
        GridPane grid = new GridPane();

        //creates combobox options
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Counter Assistant",
                        "Manager",
                        "System Manager"
                );

        //creates text with style
        Text name = new Text("Name:");
        name.getStyleClass().add("subheading");

        Text account = new Text("Account:");
        account.getStyleClass().add("subheading");

        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField();

        Label lastNameL = new Label("Last name:");
        TextField lastNameT = new TextField();

        Label usernameL = new Label("Username:");
        TextField usernameT = new TextField();

        Label passwordL = new Label("password:");
        TextField passwordT = new TextField();

        Label emailL = new Label("Email:");
        TextField emailT = new TextField();

        Label functionL = new Label("Type:");
        ComboBox functionC = new ComboBox(options);

        //creates label and buttons
        error = new Label();
        cancel = new Button("Cancel");
        addUser = new Button("Add User");

        //adds components to gridpane
        grid.add(name, 0, 0);

        grid.add(firstNameL, 0, 1);
        grid.add(firstNameT, 1, 1, 5, 1);

        grid.add(lastNameL, 0, 2);
        grid.add(lastNameT, 1, 2, 5, 1);

        grid.add(account, 0, 4);

        grid.add(usernameL, 0, 5);
        grid.add(usernameT, 1, 5, 5, 1);

        grid.add(passwordL, 0, 6);
        grid.add(passwordT, 1, 6, 5, 1);

        grid.add(emailL, 0, 7);
        grid.add(emailT, 1, 7, 5, 1);

        grid.add(functionL, 0, 8);
        grid.add(functionC, 1, 8, 5, 1);

        grid.add(error, 0, 12, 10, 1);

        grid.add(cancel, 0, 10);
        grid.add(addUser, 1, 10, 10, 1);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //cancel button pressed
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //sets table to center
                pane.setCenter(createTable());
            }
        });

        //adduser button pressed
        addUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //checks fields
                if (firstNameT.getText() == null
                        || firstNameT.getText().trim().isEmpty()
                        || lastNameT.getText() == null
                        || lastNameT.getText().trim().isEmpty()
                        || usernameT.getText() == null
                        || usernameT.getText().trim().isEmpty()
                        || passwordT.getText() == null
                        || passwordT.getText().trim().isEmpty()
                        || emailT.getText() == null
                        || emailT.getText().trim().isEmpty()
                        || functionC.getValue() == null) {

                    //sets error text
                    error.setText("You have not filled all the fields");

                    return;
                }

                //conncets to database
                Database test = new Database();
                Database.setConn();
                
                //sets query
                test.setQuery("INSERT INTO users (firstname, lastname, username"
                        + ", password, email, function, question, answer) "
                        + "VALUES ('"
                        + firstNameT.getText() + "', '"
                        + lastNameT.getText() + "', '"
                        + usernameT.getText() + "', "
                        + "MD5('" + passwordT.getText() + "'), '"
                        + emailT.getText() + "', '"
                        + functionC.getValue() + "', '"
                        + "" + "', '"
                        + "" + "')");

                //sets table to center
                pane.setCenter(createTable());
            }
        });

        return grid;
    }
}
