package fys_main;

import fys_main.HB_SearchBaggage.TableBaggage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {

    //aanmaken allover borderpane
    private static BorderPane pane = new BorderPane();
    private static GridPane grid = new GridPane();

    private static TableView<TableBaggage> table = new TableView<>();
    private static ObservableList<TableBaggage> data = FXCollections.observableArrayList();

    //sidebar Menu Buttons
    private static Label createL = new Label("Create");
    private static Button lost = new Button("Lost baggage");
    private static Button found = new Button("Found baggage");
    private static Button edit = new Button("Edit");
    private static Label searchL = new Label("Search");
    private static Button baggage = new Button("Baggage");
    private static Button logout = new Button("Log out");
    private static HBox hbox = new HBox();
    private static VBox vbox = new VBox();

    //methode voor het scherm
    public static VBox vboxLeft() {
        VBox vbox = new VBox();
        //image

        //buttons
        lost.setMinSize(230, 48);
        found.setMinSize(230, 48);
        baggage.setMinSize(230, 48);
        logout.setMinSize(230, 48);

        createL.getStyleClass().add("labels");
        searchL.getStyleClass().add("labels");

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(createL, lost, found, searchL, baggage, logout);

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
//                hbox.getChildren().remove(edit);
            }
        });

        found.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_CreateFound.getScreen());
//                hbox.getChildren().remove(edit);
            }
        });

        baggage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_SearchBaggage.getScreen());
//                hbox.getChildren().add(edit);

            }
        });

        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Start.getScreen());
//                hbox.getChildren().remove(edit);
            }
        });

//        edit.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                pane.setCenter(HB_SearchBaggage.getScreenTwo());
//            }
//        });

        return pane;
    }

}
