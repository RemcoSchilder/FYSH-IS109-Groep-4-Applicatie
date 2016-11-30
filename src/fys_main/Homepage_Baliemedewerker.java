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
    
    private static Button lost = new Button("Lost bagage");
    private static Button found = new Button("Found bagage");
    private static Label create = new Label("Create");
    
    public static BorderPane getScreen() {

        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(50, 50, 50, 50));
        
        
        lost.setPrefWidth(200);
        lost.setPrefHeight(50);
        found.setPrefWidth(200);
        found.setPrefHeight(50);

        grid.add(create, 6, 1, 40, 20);
        grid.add(lost, 5, 10, 40, 20); 
        grid.add(found, 5, 20, 40, 20); 
        
        pane.setLeft(grid);
        
       
        
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                pane.setCenter(HB_CreateLost.getScreen());
            }
        });
        return pane;
    }
}
