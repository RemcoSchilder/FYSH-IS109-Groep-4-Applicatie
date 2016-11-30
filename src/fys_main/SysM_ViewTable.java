/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author freek
 */
public class SysM_ViewTable {
    
    private static GridPane screen = new GridPane();
    private static TableView<TableUsers> table = new TableView<>();
    private static ObservableList<TableUsers> data = FXCollections.observableArrayList();
    
    public static GridPane getScreen() {
        Datatable();
        
        return screen;
    }
    
    public static GridPane Datatable() {
        
        //create colums voor table
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn password = new TableColumn("Password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn email = new TableColumn("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn function = new TableColumn("Function");
        function.setCellValueFactory(new PropertyValueFactory<>("function"));
        
        //ophalen van gegevens uit database
        Database db = new Database();
        db.setConn();

        ResultSet result = db.getQuery("SELECT * FROM users");
        try {
            while (result.next()) {
                data.add(new TableUsers(
                        result.getString("name"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("function")
                ));
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        
        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(name, username, password, email, function);
        
        /* Create fields with labels */
        screen.add(table, 0, 0);
    
        return screen;
    }

    public static class TableUsers {
        
        private final SimpleStringProperty name;
        private final SimpleStringProperty username;
        private final SimpleStringProperty password;
        private final SimpleStringProperty email;
        private final SimpleStringProperty function;

        private TableUsers(String name, String username, String password, String email, String function) {
            this.name = new SimpleStringProperty(name);
            this.username = new SimpleStringProperty(username);
            this.password = new SimpleStringProperty(password);
            this.email = new SimpleStringProperty(email);
            this.function = new SimpleStringProperty(function);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public String getUsername() {
            return username.get();
        }

        public void setUsername(String username) {
            this.username.set(username);
        }

        public String getPassword() {
            return password.get();
        }

        public void setPassword(String password) {
            this.password.set(password);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String email) {
            this.email.set(email);
        }

        public String get() {
            return function.get();
        }

        public void setFunction(String function) {
            this.function.set(function);
        }

    }
    
    public GridPane setScreen() {
        Datatable();
        GridPane grid = new GridPane();
        grid.getChildren().add(table);

        return grid;
    }

}