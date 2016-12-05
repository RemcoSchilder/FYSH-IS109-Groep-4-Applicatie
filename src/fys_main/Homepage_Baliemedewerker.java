package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {

    //aanmaken allover borderpane
    private static BorderPane pane = new BorderPane();

    //sidebar Menu Buttons
    private static Label create = new Label("Create");
    private static Button lost = new Button("Lost bagage");
    private static Button found = new Button("Found bagage");
    private static Label search = new Label("Search");
    private static Button baggage = new Button("Baggage");
    private static Button logout = new Button("Log out");

    //methode voor het scherm
    public static VBox vbox() {
        VBox vbox = new VBox();
        //image

        //buttons
        lost.setMinSize(230, 48);
        found.setMinSize(230, 48);
        baggage.setMinSize(230, 48);
        logout.setMinSize(230, 48);

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(create, lost, found, search, baggage, logout);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");
        VBox.setMargin(logout, new Insets(566, 0, 0, 0));

        return vbox;
    }

    public static BorderPane getScreen() {

          pane.setLeft(vbox());
          
          
          
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
                pane.getScene().setRoot(Start.getScreen());

            }
        });

        return pane;
    }
}
