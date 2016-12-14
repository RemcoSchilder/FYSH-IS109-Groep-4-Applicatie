package fys_main;

import static fys_main.FYS_LostFound.pane;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Remco Schilder
 */
public class HM_luggageList {
    
    private static GridPane screen = new GridPane();
    
    private static Button details = new Button("Details");
    
    public static GridPane getScreen() {
        screen.getChildren().clear();
        screen.setAlignment(Pos.CENTER);
        screen.add(HB_SearchBaggage.getScreen(), 0, 0);
        screen.add(details, 4, 6);
        
        details.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              
                pane.setCenter(Manager_Details.getScreen());
            }
        });
        
        return screen;
    }
}