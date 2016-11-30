package fys_main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Baliemedewerker {

    private static BorderPane pane = new BorderPane();
    private static VBox menu = new VBox(10);
    private static Button lost = new Button("Lost bagage");

    public static BorderPane getScreen() {

       
        menu.getChildren().addAll(lost);
        pane.setLeft(menu);
        
        lost.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.setCenter(HB_CreateLost.getScreen());

            }
        });

        return pane;
    }

    

   
}
