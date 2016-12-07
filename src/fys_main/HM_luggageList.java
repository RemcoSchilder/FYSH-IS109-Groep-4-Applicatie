package fys_main;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;


/**
 *
 * @author Menno
 */
public class HM_luggageList {
    
    private static GridPane screen = new GridPane();
    
    public static GridPane getScreen() {
        screen.getChildren().clear();
        screen.setAlignment(Pos.CENTER);
        screen.add(HB_SearchBaggage.getScreen(), 0, 0);
        
        return screen;
    }
    
}