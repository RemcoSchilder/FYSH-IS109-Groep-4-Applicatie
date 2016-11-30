package fys_main;

import javafx.scene.layout.GridPane;

/**private static GridPane pane = new GridPane();
    
    public static GridPane getScreen() {
        pane.add(HM_luggageList.getScreen(), 0, 0);
        
        return pane;
    }
 *
 * @author Thijs Timmermans
 */

public class SysM_Homepage {

    private static GridPane pane = new GridPane();
    
    public static GridPane getScreen() {
        pane.add(SysM_ViewTable.getScreen(), 0, 0);
        
        return pane;
    }
}
