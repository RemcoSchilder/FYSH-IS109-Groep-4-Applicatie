package fys_main;

import static fys_main.FYS_LostFound.grid;
import static fys_main.FYS_LostFound.pane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Login {

    //aanmaken allover borderpane
    
    //aanmaken buttons, labels, en fields voor inlogscherm
    private static Button btnLogin = new Button("Log in");
    private static Text sceneTitle = new Text("Welcome");
    private static Text sceneError = new Text();
    private static Label userName = new Label("Username:");
    private static TextField userTextField = new TextField();
    private static Label pw = new Label("Password:");
    private static PasswordField pwTextField = new PasswordField();
    
    
    //methode voor het scherm van login naar baliemedewerker homepage
    public static BorderPane getScreen() {
        //gridpane voor center van de borderpane, buttons, labels, en fields voor inlogscherm
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(200, 200, 200, 200));
        
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        sceneError.setFill(Color.RED);
        grid.add(sceneTitle, 50, 20, 40, 20);
        grid.add(userName, 50, 25, 40, 25);
        grid.add(userTextField, 70, 25, 40, 30);
        grid.add(pw, 50, 30, 40, 35);
        grid.add(pwTextField, 70, 30, 40, 40);
        grid.add(btnLogin, 50, 35, 40, 45);
        grid.add(sceneError, 70, 35, 40, 45);
        
        pane.setCenter(grid);
        
        // Eventhandler login
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sceneError.setText(""); // Empty previous error
                if(userTextField.getText().equals("") && pwTextField.getText().equals("")){
                    sceneError.setText("Username and Password can not be empty");
                }
                else if (userTextField.getText().equals("")) {
                    sceneError.setText("Username can not be empty");
                } else if (pwTextField.getText().equals("")) {
                    sceneError.setText("Password can not be empty");
                } else {
                    Database DB = new Database();
                    DB.setConn();
                
                    ResultSet checkLogin = DB.getQuery("SELECT * FROM users WHERE BINARY username='" + userTextField.getText() + "' AND BINARY password='" + pwTextField.getText() + "'");
                
                    try {
                        if (checkLogin.next()) {
                            pane.getChildren().clear();
                            
                            if (checkLogin.getString("function").equals("system_manager")) {
                                pane.getScene().setRoot(Homepage_Systeem.getScreen());
                            } else if (checkLogin.getString("function").equals("manager")) {
                                pane.getScene().setRoot(Homepage_Manager.getScreen());
                            } else if (checkLogin.getString("function").equals("baliemedewerker")) {
                                pane.getScene().setRoot(Homepage_Baliemedewerker.getScreen());
                            } else {
                                sceneError.setText("This account does not have a function, please contact the system manager");
                            }
                        } else {
                            sceneError.setText("Wrong username or password");
                        }
                    } catch(SQLException se) {
                        //Handle errors for JDBC
                        se.printStackTrace();
                    }
                }

            }
        });
        
        return pane;
    }
}
