
package fys_main;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Manager {
    
    private static GridPane pane = new GridPane();
    
    public static GridPane getScreen() {
        pane.add(HM_luggageList.getScreen(), 0, 0);
        
        return pane;
    }
    
}
