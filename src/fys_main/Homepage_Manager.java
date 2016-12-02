package fys_main;

//import static fys_main.Homepage_Systeem.pane;
import static fys_main.Homepage_Baliemedewerker.vbox;
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
    public static VBox vbox() {
        VBox vbox = new VBox();
        //image

        //buttons
        bagageLijst.setMinSize(230, 48);
        lost.setMinSize(230, 48);
        found.setMinSize(230, 48);
        logout.setMinSize(230, 48);

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(see, bagageLijst, graph, lost, found, logout);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");
        VBox.setMargin(logout, new Insets(566, 0, 0, 0));

        return vbox;
    }
    
//methode voor het scherm
    public static BorderPane getScreen() {

        pane.setLeft(vbox());

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