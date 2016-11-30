
package fys_main;

import javafx.scene.layout.BorderPane;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Manager {
    
    private static BorderPane pane = new BorderPane();
    
    public static BorderPane getScreen() {
        pane.setCenter(HM_luggageList.getScreen());
        
        return pane;
    }
    
}
