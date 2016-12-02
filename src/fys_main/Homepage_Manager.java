package fys_main;

import static fys_main.Homepage_Systeem.pane;
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
public class Homepage_Manager {

    //aanmaken allover borderpane
    private static BorderPane pane = new BorderPane();

    //buttons en label voor sidebar menu
    private static Label see = new Label("See");
    private static Button bagageLijst = new Button("Bagage List");
    private static Label graph = new Label("Graph");
    private static Button lost = new Button("Lost");
    private static Button found = new Button("Found");
    private static Button logout = new Button("Log out");

    //methode voor het scherm
    public static BorderPane getScreen() {

        //gridpane voor left side van de borderpane
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(50, 50, 50, 50));

        bagageLijst.setPrefWidth(200);
        bagageLijst.setPrefHeight(50);
        lost.setPrefWidth(200);
        lost.setPrefHeight(50);
        found.setPrefWidth(200);
        found.setPrefHeight(50);
        logout.setPrefWidth(200);
        logout.setPrefHeight(50);
        
        grid.add(see, 6, 1, 40, 20);
        grid.add(bagageLijst, 5, 10, 40, 20);
        grid.add(graph, 6, 40, 40, 20);
        grid.add(lost, 6, 50, 40, 20);
        grid.add(found, 6, 60, 40, 20);
        grid.add(logout, 5, 120, 40, 20);
        
        pane.setLeft(grid);

        //eventhandler knop bagagelijst opent bagagelijst info
        bagageLijst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                pane.setCenter(HM_luggageList.getScreen());
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