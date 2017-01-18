package fys_main;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
public class Homepage_Manager {

    //Create BorderPane
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

        
        //Method to create the left sidebar    
        public static VBox vbox() {
        
        VBox vbox = new VBox();
        
        //Image
        Image image = new Image("logo.jpg", 230, 48, false, false);
        ImageView v1 = new ImageView(image);

        //Set buttonsize
        bagageList.setMinSize(230, 48);
        travellerList.setMinSize(230, 48);
        lost.setMinSize(230, 48);
        found.setMinSize(230, 48);
        
        //Label styles
        see.getStyleClass().add("labels");
        graph.getStyleClass().add("labels");

        //Add everything to the vbox
        vbox.getChildren().addAll(v1, see, bagageList, travellerList, graph, lost, found);

        //Vbox style
        vbox.getStyleClass().add("vbox");
       

        return vbox;
    }
    
    //Method to create the bottom sidebar    
    public static HBox hboxBottom() {
        
        //Buttonsize
        logout.setMinSize(230, 48);
        edit.setMinSize(230, 48);
        
        //Add everything to the vbox
        hbox.getChildren().clear();
        hbox.getChildren().addAll(logout);

        //Set vbox style
        hbox.getStyleClass().add("vbox");
       
       
        return hbox;
    }
    
   //Method to create the screen
    public static BorderPane getScreen() {

        pane.setLeft(vbox());
        pane.setBottom(hboxBottom());
        
        
        /* Event handlers for the buttons */
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
        
       
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());
            }
        });
        
       
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                pane.setCenter(HM_Graphlost.getScreen());
            }
        });
        
      
        found.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                pane.setCenter(HM_Graphfound.getScreen());
            }
        });

        return pane;
    }

}
