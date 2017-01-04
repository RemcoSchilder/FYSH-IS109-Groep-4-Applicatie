/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import static fys_main.FYS_LostFound.grid;
import static fys_main.FYS_LostFound.pane;
import static fys_main.Homepage_Systeem.editUser;
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
    
    private static Button cancel = new Button("Cancel");
    private static Button save = new Button("Save");   
    
    public static GridPane getScreen() {
        grid = new GridPane();
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Counter Assistant",
            "Manager",
            "System Manager"
        );
        final ComboBox function = new ComboBox(options);
        
        System.out.println(editUser.getFunction());
        function.getSelectionModel().selectFirst(); 
        if (!"Counter Assistant".equals(editUser.getFunction())) {
            function.getSelectionModel().selectNext();   
            if (!"Manager".equals(editUser.getFunction())) {
                function.getSelectionModel().selectNext();
            }
        }  
        
            
        /* GridPane properties */
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
         /* Create all subheadings */
        Text name = new Text("Name:");
        name.getStyleClass().add("subheading");
        
        Text account = new Text("Account:");
        account.getStyleClass().add("subheading");
        
        /* Create all labels & inputs */
        Label firstNameL = new Label("First name:");
        TextField firstNameT = new TextField(editUser.getFirstname());
        
        Label lastNameL = new Label("Last name:");
        TextField lastNameT = new TextField(editUser.getLastname());
        
        Label usernameL = new Label("Username:");
        Label usernameT = new Label(editUser.getUsername());
        
        Label passwordL = new Label("password:");
        TextField passwordT = new TextField(editUser.getPassword());
        
        Label emailL = new Label("Email:");
        TextField emailT = new TextField(editUser.getEmail());
        
        Label functionL = new Label("Type:");
        ComboBox functionT = function;
        
        /* Add everything to the grid */
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
        grid.add(functionT, 1, 8, 5, 1);
        
        grid.add(cancel, 0, 10);
        grid.add(save, 1, 10, 10, 1);
        
        /* Event handlers */
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Systeem.getScreen());
            }
        });
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                               
                Database test = new Database();
                test.setConn();
                test.setQuery("Update users SET "
                        + "firstname='" + firstNameT.getText() + "', " 
                        + "lastname='" + lastNameT.getText() + "', "
                        + "password='" + passwordT.getText() + "', "
                        + "email='" + emailT.getText() + "', "
                        + "function='" + functionT.getValue() + "' "
                        + "WHERE username = '" + editUser.getUsername() + "'");
                       
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Systeem.getScreen());
            }
        });
        
        return grid;
    }
}
