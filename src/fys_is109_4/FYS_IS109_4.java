/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_is109_4;

import javafx.application.Application; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Menno
 */
public class FYS_IS109_4 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Inloggen");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                /* Knopje */
            }
        });
        
        
        Label persnrLabel = new Label("Personeelnummer:");
        persnrLabel.setStyle("font-size: 16px;");
        TextField persnrInput = new TextField();
        
        Label passwordLabel = new Label("Wachtwoord:");
        passwordLabel.setStyle("font-size: 16px;");
        PasswordField passwordInput = new PasswordField();
        
        ImageView logoView = new ImageView(new Image(getClass().getResource("images/logo.jpg").toExternalForm(), 500, 222, false, false));
        
        
        BorderPane frame = new BorderPane();
        
        HBox menu = new HBox();
        menu.setSpacing(10);
        
        frame.setAlignment(logoView, Pos.TOP_CENTER);
        frame.setTop(logoView);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        grid.add(persnrLabel, 0, 0);
        grid.add(persnrInput, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordInput, 1, 1);
        grid.add(btn, 1, 2);
        grid.setAlignment(Pos.CENTER);
        frame.setCenter(grid);
        
        Scene scene = new Scene(frame, 800, 600);
        
        primaryStage.setTitle("Inloggen!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
