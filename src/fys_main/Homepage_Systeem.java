package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Systeem {
    
    //aanmaken allover borderpane
    public static BorderPane pane = new BorderPane();
    
    //aanmaken sidebar menu, buttons en label
    private static Button user = new Button("User");
    private static Label create = new Label("Create");
    private static Button logout = new Button("Log out");
    
    //methode voor scherm
    public static BorderPane getScreen() {
        
        //gridpane voor left side van de borderpane
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(50, 50, 50, 50));
        
        user.setPrefWidth(200);
        user.setPrefHeight(50);
        logout.setPrefWidth(200);
        logout.setPrefHeight(50);
        
        grid.add(create, 6, 1, 40, 20);
        grid.add(user, 5, 10, 40, 20);
        grid.add(logout, 5, 100, 40, 20);
       
        pane.setLeft(grid);
        
        //gridpane voor center van de borderpane
        pane.setCenter(HS_ViewTable.getScreen());
        
        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
               pane.getChildren().add(HS_CreateUser.getScreen());
               
            }
        });
        
        //eventhandler terug naar start
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
               pane.getChildren().clear();
               pane.getChildren().add(Start.getScreen());
               
            }
        });
        
        
        return pane;
    }
}