
package fys_main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Wessel
 */
public class Manager_Details {
    
    //moet allemaal nog gefixt worden
    
        private static GridPane screen = new GridPane();
       
        public static GridPane getScreen() {
        screenDetails();
        
        return screen;
        }
       
     
        public static void screenDetails(){
             
            
        Text scenetitle = new Text("Luggage information");       
        screen.add(scenetitle, 22, 8);

        Label datum = new Label("Date(D-M-Y)");
        screen.add(datum, 20, 10);
       
        Text tijd = new Text("Time");       
        screen.add(tijd, 20, 11);
        

        Label airport = new Label("Airport");
        screen.add(airport, 20, 12);

        Text luggageinfo = new Text("Bagage label information");
        screen.add(luggageinfo, 20, 13);

        Label labelnumber = new Label("Label number");
        screen.add(labelnumber, 20, 14);

        Label traveler = new Label("Name Traveler");
        screen.add(traveler, 23, 14);
        
        Label flightnumber = new Label("Flight number");
        screen.add(flightnumber, 20, 15);
        
        Label lostfound = new Label("Lost/Found");
        screen.add(lostfound, 23 , 15);
        
        Text bagageinfo = new Text("Luggage Information");
        screen.add(bagageinfo, 20 , 16);
        
        Label Type = new Label("Type");
        screen.add(Type, 20 , 17);
        
        Label Brand = new Label("Brand");
        screen.add(Brand, 20 , 18);
        
        Label Color = new Label("Color");
        screen.add(Color, 20, 19);
        
        Label charac = new Label("Characteristics");
        screen.add(charac, 20, 20);        
        }
}
