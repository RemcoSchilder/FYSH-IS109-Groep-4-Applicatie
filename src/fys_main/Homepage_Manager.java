package fys_main;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
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
    private static Button bagageList = new Button("Bagage List");
    private static Button travellerList = new Button("Travellers List");
    private static Label graph = new Label("Graph");
    private static Button lost = new Button("Month overview");
    private static Button found = new Button("Status Graph");
    private static Button edit = new Button("Edit");
    private static Button logout = new Button("Log out");
    private static HBox hbox = new HBox();

   //methode voor het scherm
    public static VBox vbox() {
        VBox vbox = new VBox();
        

        //buttons
        bagageList.setMinSize(230, 48);
        travellerList.setMinSize(230, 48);
        lost.setMinSize(230, 48);
        found.setMinSize(230, 48);

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(see, bagageList, travellerList, graph, lost, found);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");
       

        return vbox;
    }
    
    public static HBox hboxBottom() {
        
        //buttons
        logout.setMinSize(230, 48);
        edit.setMinSize(230, 48);
        
        //alles wordt in de vbox gestopt
        hbox.getChildren().clear();
        hbox.getChildren().addAll(logout);

        //style voor de vbox
        hbox.getStyleClass().add("vbox");
       // VBox.setMargin(logout, new Insets(566, 0, 0, 0));
       
       
        return hbox;
    }
    
    //methode voor het scherm
    public static BorderPane getScreen() {

        pane.setLeft(vbox());
        pane.setBottom(hboxBottom());
        
        
        //eventhandler knop bagagelijst opent bagagelijst info
        bagageList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HM_luggageList.getScreen());
            }
        });
        
        travellerList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HM_TravellersList.getScreen());
            }
        });
        
        //eventhandler terug naar start
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                pane.getChildren().clear();
                pane.getScene().setRoot(Start.getScreen());
            }
        });
        
        //eventhandler naar lost pie chart
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                pane.setCenter(HM_Graphlost.getScreen());
            }
        });
        
        //eventhandler naar found pie chart
        found.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                pane.setCenter(HM_Graphfound.getScreen());
            }
        });

        return pane;
    }

}