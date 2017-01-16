package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {

    //aanmaken allover borderpane
    public static BorderPane pane = new BorderPane();

    //sidebar Menu Buttons
    private static Label createL = new Label("Create");
    private static Button lost = new Button("Lost baggage");
    private static Button found = new Button("Found baggage");
    private static Label searchL = new Label("Search");
    private static Button baggage = new Button("Baggage");
    private static Button matches = new Button("Matches");
    private static Button logout = new Button("Log out");
    private static HBox hbox = new HBox();

    //methode voor het scherm
    public static VBox vboxLeft() {
        VBox vbox = new VBox();
        
        //image
        Image image = new Image("logo.jpg", 230, 48, false, false);
        ImageView v1 = new ImageView(image);
        
        //buttons
        lost.setMinSize(230, 48);
        found.setMinSize(230, 48);
        baggage.setMinSize(230, 48);
        matches.setMinSize(230, 48);
        logout.setMinSize(230, 48);

        createL.getStyleClass().add("labels");
        searchL.getStyleClass().add("labels");

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(v1, createL, lost, found, searchL, baggage, matches, logout);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");

        return vbox;
    }

    public static HBox HboxBottom() {

        //buttons
        logout.setMinSize(230, 48);
        //edit.setMinSize(230, 48);
        //alles wordt in de vbox gestopt
        hbox.getChildren().addAll(logout);

        //style voor de vbox
        hbox.getStyleClass().add("vbox");
        

        return hbox;
    }

    

    public static BorderPane getScreen() {

        pane.setLeft(vboxLeft());
        pane.setBottom(HboxBottom());
       

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

        matches.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_MatchBaggage.getScreen());
            }
        });
        
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
