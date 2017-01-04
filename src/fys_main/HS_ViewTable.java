package fys_main;

import static fys_main.Homepage_Systeem.searchResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author freek
 */
public class HS_ViewTable {
    
    private static TableView<TableUsers> table = new TableView<>();
    private static ObservableList<TableUsers> data = FXCollections.observableArrayList();
    
    public static TableView<TableUsers> users() {
        
        //vernieuw gegevens
        table = new TableView<>();
        data.removeAll(data);
        
        //create colums voor table
        TableColumn firstname = new TableColumn("Firstname");
        firstname.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        firstname.prefWidthProperty().bind(table.widthProperty().divide(6));
        
        TableColumn lastname = new TableColumn("Lastname");
        lastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        lastname.prefWidthProperty().bind(table.widthProperty().divide(6));
        
        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        username.prefWidthProperty().bind(table.widthProperty().divide(6));
        
        TableColumn password = new TableColumn("Password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        password.prefWidthProperty().bind(table.widthProperty().divide(6));
        
        TableColumn email = new TableColumn("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.prefWidthProperty().bind(table.widthProperty().divide(6));
        
        TableColumn function = new TableColumn("Function");
        function.setCellValueFactory(new PropertyValueFactory<>("function"));
        function.prefWidthProperty().bind(table.widthProperty().divide(6));
        
        //ophalen van gegevens uit database
        Database db = new Database();
        db.setConn();

        if (searchResult == null) {
            ResultSet result = db.getQuery("SELECT firstname, lastname"
                + ", username, password, email, function FROM users");
            
            try {
                while (result.next()) {
                    data.add(new TableUsers(
                            result.getString("firstname"),
                            result.getString("lastname"),
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
           
        } else {
            try {
                while (searchResult.next()) {
                    data.add(new TableUsers(
                            searchResult.getString("firstname"),
                            searchResult.getString("lastname"),
                            searchResult.getString("username"),
                            searchResult.getString("password"),
                            searchResult.getString("email"),
                            searchResult.getString("function")
                    ));
                }
            } catch (SQLException se) {
            
            //Handle errors for JDBC
            se.printStackTrace();
            }
        }

        /* Set table colums and rows */
        table.setItems(data);
        table.getColumns().addAll(firstname, lastname, username, password, email, function);

        
        return table;
    }

    public static class TableUsers {
        
        /* haalt gegevens uit database */
        private final SimpleStringProperty firstname;
        private final SimpleStringProperty lastname;
        private final SimpleStringProperty username;
        private final SimpleStringProperty password;
        private final SimpleStringProperty email;
        private final SimpleStringProperty function;

        private TableUsers(String firstname, String lastname, String username, String password, String email, String function) {
            this.firstname = new SimpleStringProperty(firstname);
            this.lastname = new SimpleStringProperty(lastname);
            this.username = new SimpleStringProperty(username);
            this.password = new SimpleStringProperty(password);
            this.email = new SimpleStringProperty(email);
            this.function = new SimpleStringProperty(function);
        }
        
        public String getFirstname() {
            return firstname.get();
        }

        public void setFirstname(String firstname) {
            this.firstname.set(firstname);
        }
        
        public String getLastname() {
            return lastname.get();
        }

        public void setLastname(String lastname) {
            this.lastname.set(lastname);
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

        public String getFunction() {
            return function.get();
        }

        public void setFunction(String function) {
            this.function.set(function);
        }

    }
}
