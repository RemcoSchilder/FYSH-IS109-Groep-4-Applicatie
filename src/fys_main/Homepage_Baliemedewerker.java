package fys_main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {

    private static GridPane pane = new GridPane();
    private static VBox menu = new VBox(10);
    private static Button lost = new Button("Lost bagage");

    public static GridPane getScreen() {

       
        menu.getChildren().addAll(lost);
        pane.getChildren().add(menu);
        
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getChildren().add(HB_CreateLost.getScreen());

            }
        });

        return pane;
    }

    

   
}
