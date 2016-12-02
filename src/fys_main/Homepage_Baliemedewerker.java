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
public class Homepage_Baliemedewerker {

    //aanmaken allover borderpane
    private static BorderPane pane = new BorderPane();
    
    //sidebar Menu Buttons
    private static Button lost = new Button("Lost bagage");
    private static Button found = new Button("Found bagage");
    private static Label create = new Label("Create");
    private static Label search = new Label("Search");
    private static Button baggage = new Button("Baggage");
    private static Button logout = new Button("Log out");
    
    //methode voor het scherm
    public static BorderPane getScreen() {
     
       //gridpane voor left side van de borderpane
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(50, 50, 50, 50));
        
        lost.setPrefWidth(200);
        lost.setPrefHeight(50);
        found.setPrefWidth(200);
        found.setPrefHeight(50);
        baggage.setPrefWidth(200);
        baggage.setPrefHeight(50);
        logout.setPrefWidth(200);
        logout.setPrefHeight(50);
        
        grid.add(create, 5, 1, 40, 20);
        grid.add(lost, 0, 10, 40, 20); 
        grid.add(found, 0, 20, 40, 20); 
        grid.add(search, 0, 40, 40, 20);
        grid.add(baggage,0, 50, 40, 20);
        grid.add(logout, 0, 120, 40, 20);
        
        pane.setLeft(grid);
        
        //eventhander knop lost openen lost invoer
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_CreateLost.getScreen());
                
            }
        });
        
        found.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_CreateFound.getScreen());
                
            }
        });
        
        baggage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_SearchBaggage.getScreen());
                
            }
        });
        
        //eventhandler van logout knop naar start
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