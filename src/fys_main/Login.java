package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Login {

    //aanmaken allover borderpane
    private static BorderPane pane = new BorderPane();
    
    //aanmaken buttons, labels, en fields voor inlogscherm
    private static Button btnLogin= new Button("Log in");
    private static Text scenetitle = new Text("Welcome");
    private static Label userName = new Label("User Name:");
    private static TextField userTextField = new TextField();
    private static Label pw = new Label("Password:");
    private static PasswordField pwBox = new PasswordField();
    
    //methode voor het scherm van login naar baliemedewerker homepage
    public static BorderPane getScreenOne() {

        //gridpane voor center van de borderpane, buttons, labels, en fields voor inlogscherm
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(200, 200, 200, 200));
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 50, 20, 40, 20);
        grid.add(userName, 50, 25, 40, 25);
        grid.add(userTextField, 70, 25, 40, 30);
        grid.add(pw, 50, 30, 40, 35);
        grid.add(pwBox, 70, 30, 40, 40);
        grid.add(btnLogin, 50, 35, 40, 45);
        
        pane.setCenter(grid);
        
        //eventhandler login naar baliemedewerker
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getChildren().add(Homepage_Baliemedewerker.getScreen());
             
            }
        });
        
        return pane;
    }

    //methode voor het scherm van login naar systeembeheerder homepage
    public static BorderPane getScreenTwo() {
        
        //gridpane voor center van de borderpane, buttons, labels, en fields voor inlogscherm
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(200, 200, 200, 200));
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 50, 20, 40, 20);
        grid.add(userName, 50, 25, 40, 25);
        grid.add(userTextField, 70, 25, 40, 30);
        grid.add(pw, 50, 30, 40, 35);
        grid.add(pwBox, 70, 30, 40, 40);
        grid.add(btnLogin, 50, 35, 40, 45);
        
        pane.setCenter(grid);
        
        //eventhandler login naar systeembeheerder
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getChildren().add(Homepage_Systeem.getScreen());
             
            }
        });
        
        return pane;
    }
    
    //methode voor het scherm van login naar manager homepage
    public static BorderPane getScreenThree() {
        
        //gridpane voor center van de borderpane, buttons, labels, en fields voor inlogscherm
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(200, 200, 200, 200));
        
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 50, 20, 40, 20);
        grid.add(userName, 50, 25, 40, 25);
        grid.add(userTextField, 70, 25, 40, 30);
        grid.add(pw, 50, 30, 40, 35);
        grid.add(pwBox, 70, 30, 40, 40);
        grid.add(btnLogin, 50, 35, 40, 45);
        
        pane.setCenter(grid);
        
        //eventhandler login naar manager
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getChildren().add(Homepage_Manager.getScreen());
             
            }
        });
        
        return pane;
    }
}
