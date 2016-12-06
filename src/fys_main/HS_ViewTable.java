package fys_main;

import static fys_main.FYS_LostFound.grid;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

/**
 *
 * @author freek
 */
public class HS_ViewTable {
    
    private static TableView<TableUsers> table = new TableView<>();
    private static ObservableList<TableUsers> data = FXCollections.observableArrayList();
    
    public static GridPane Datatable() {
        
        //vernieuw gegevens
        grid = new GridPane();
        table = new TableView<>();
        data.removeAll(data);
        
        //create colums voor table
        TableColumn name = new TableColumn("Name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        name.prefWidthProperty().bind(table.widthProperty().divide(5));
        
        TableColumn username = new TableColumn("Username");
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        username.prefWidthProperty().bind(table.widthProperty().divide(5));
        
        TableColumn password = new TableColumn("Password");
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        password.prefWidthProperty().bind(table.widthProperty().divide(5));
        
        TableColumn email = new TableColumn("Email");
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        email.prefWidthProperty().bind(table.widthProperty().divide(5));
        
        TableColumn function = new TableColumn("Function");
        function.setCellValueFactory(new PropertyValueFactory<>("function"));
        function.prefWidthProperty().bind(table.widthProperty().divide(5));
        
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
        
        /* voeg table toe aan grid */
        grid.add(table, 0, 0);

        
        return grid;
    }

    public static class TableUsers {
        
        /* haalt gegevens uit database */
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

        public String getFunction() {
            return function.get();
        }

        public void setFunction(String function) {
            this.function.set(function);
        }

    }
}
