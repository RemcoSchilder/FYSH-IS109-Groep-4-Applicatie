package fys_main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {

    private static BorderPane pane = new BorderPane();
    
    //sidebar Menu Buttons
    private static Button lost = new Button("Lost bagage");
    private static Button found = new Button("Found bagage");
    private static Label create = new Label("Create");
    private static Button logout = new Button("Log out");
    //Einde Sidebar Menu Buttons
    
    public static BorderPane getScreen() {
     
        //Sidebar Menu
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(50, 50, 50, 50));
        
        lost.setPrefWidth(200);
        lost.setPrefHeight(50);
        found.setPrefWidth(200);
        found.setPrefHeight(50);
        logout.setPrefWidth(200);
        logout.setPrefHeight(50);
        
        grid.add(create, 6, 1, 40, 20);
        grid.add(lost, 5, 10, 40, 20); 
        grid.add(found, 5, 20, 40, 20); 
        grid.add(logout, 5, 100, 40, 20);
       
        pane.setLeft(grid);
        //Einde Sidebar Menu
       
        
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                pane.setCenter(HB_CreateLost.getScreen());
            }
        });
        
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
