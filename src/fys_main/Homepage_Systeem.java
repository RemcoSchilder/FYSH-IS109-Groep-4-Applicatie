package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Systeem {
    
    private static BorderPane screen = new BorderPane();
    private static Button logout = new Button("logout");
    
    
    public static VBox vbox() {
        VBox vbox = new VBox();
        //image
        
        
        //buttons
        Text title = new Text("Create");
        Button user = new Button("user");
        user.setMinSize(230, 48);
        
        logout.setMinSize(230, 48);

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(title, user, logout);
        
        //style voor de vbox
        vbox.getStyleClass().add("vbox");
        VBox.setMargin(logout, new Insets(608, 0, 0, 0));
 
        return vbox;
    }
    
    public static HBox hbox() {
        HBox hbox = new HBox();
        Image image = new Image("https://www.corendon.be/apple-touch-icon-152x152.png", 1400, 80, false, false);
        ImageView v1 = new ImageView();
        v1.setImage(image);
         hbox.getChildren().add(v1);
         
         return hbox;
    }
    
    public static GridPane table() {
        GridPane table = new GridPane();
        table.getChildren().add(HS_ViewTable.Datatable());
        
        return table;
    }

    public static BorderPane getScreen() {
        BorderPane homepage = new BorderPane();
        homepage.setTop(hbox());
        homepage.setLeft(vbox());
        homepage.setCenter(table());
        
       //eventhandler terug naar start
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              homepage.getChildren().clear();
              homepage.getChildren().add(Start.getScreen());
               
            }
        });
        
        return homepage;
    }
}


    
        
        
        
        
        
