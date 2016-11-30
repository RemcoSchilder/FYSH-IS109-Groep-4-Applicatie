package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Login {

    
    private static GridPane pane = new GridPane();
    private static Button btnLogin= new Button("Log in");
    private static HBox start = new HBox(10);
    
    public static GridPane getScreen() {

         pane.setAlignment(Pos.CENTER);
         pane.setMinSize(300, 300);
         pane.setHgap(10);
         pane.setVgap(10);
         pane.setPadding(new Insets(25, 25, 25, 25));
        
        start.getChildren().addAll(btnLogin);
        pane.add(start, 50, 50);
        
        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        pane.add(scenetitle, 50, 24, 40, 20);

        Label userName = new Label("User Name:");
        pane.add(userName, 50, 21, 40, 20);
        TextField userTextField = new TextField();
        pane.add(userTextField, 55, 21, 40, 20);

        Label pw = new Label("Password:");
        pane.add(pw, 50, 22, 40, 20);
        PasswordField pwBox = new PasswordField();
        pane.add(pwBox, 55, 22, 40, 20);
        
        pane.add(btnLogin, 50, 27, 40, 20);
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
          pane.getChildren().add(Homepage_Baliemedewerker.getScreen());
             
            }
        });
        
        
        
        
        
        
        
        


      

//        Button loginBtn = new Button("log in");
//        HBox hbBtn = new HBox();
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().add(loginBtn);
//        pane.add(hbBtn, 50, 27);




//        final Text actiontarget = new Text();
//        pane.add(actiontarget, 1, 6);

        return pane;
    }

}
