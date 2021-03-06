/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import static fys_main.Homepage_Systeem.createTable;
import static fys_main.Homepage_Systeem.editUser;
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
 * @author freek
 */
public class HS_EditUser {

    private static Label error;
    private static Button cancel, save;

    //creates screen
    public static GridPane getScreen() {
        //create gridpane
        GridPane grid = new GridPane();

        //creates combobox options
        ObservableList<String> options
                = FXCollections.observableArrayList(
                        "Counter Assistant",
                        "Manager",
                        "System Manager"
                );

        //creates text and with style
        Text name = new Text("Name:");
        name.getStyleClass().add("subheading");

        Text account = new Text("Account:");
        account.getStyleClass().add("subheading");

        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField(editUser.getFirstname());

        Label lastNameL = new Label("Last name:");
        TextField lastNameT = new TextField(editUser.getLastname());

        Label usernameL = new Label("Username:");
        Label usernameT = new Label(editUser.getUsername());

        Label passwordL = new Label("Password:");
        Label passwordT = new Label("XXX");

        Label emailL = new Label("Email:");
        TextField emailT = new TextField(editUser.getEmail());

        Label functionL = new Label("Type:");
        ComboBox functionC = new ComboBox(options);

        //creates labels text
        error = new Label();
        cancel = new Button("Cancel");
        save = new Button("Save");

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
        grid.add(save, 1, 10, 10, 1);

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        //checks and sets correct combobox option
        functionC.getSelectionModel().selectFirst();
        if ("Manager".equals(editUser.getFunction())) {
            functionC.getSelectionModel().selectNext();
        } else if ("System Manager".equals(editUser.getFunction())) {
            functionC.getSelectionModel().selectLast();
        }

        //cancel button pressed
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //sets table as center
                pane.setCenter(createTable());
            }
        });

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //checks fields
                if (firstNameT.getText() == null
                        || firstNameT.getText().trim().isEmpty()
                        || lastNameT.getText() == null
                        || lastNameT.getText().trim().isEmpty()
                        || usernameT.getText() == null
                        || usernameT.getText().trim().isEmpty()
                        || emailT.getText() == null
                        || emailT.getText().trim().isEmpty()
                        || functionC.getValue() == null) {

                    //sets error text
                    error.setText("You have not filled all the fields");

                    return;
                }

                //connects to database
                Database test = new Database();
                Database.setConn();
                
                //sets query
                test.setQuery("UPDATE users SET "
                        + "firstname='" + firstNameT.getText() + "', "
                        + "lastname='" + lastNameT.getText() + "', "
                        + "email='" + emailT.getText() + "', "
                        + "function='" + functionC.getValue() + "' "
                        + "WHERE username = '" + editUser.getUsername() + "'");

                //sets table as center
                pane.setCenter(createTable());
            }
        });

        return grid;
    }
}
