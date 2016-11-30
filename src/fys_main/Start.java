package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thijs Timmermans
 */
public class Start {

    private static GridPane pane = new GridPane();

    private static Button btnBalie = new Button("Baliemedewerker");
    private static Button btnSysteem = new Button("Systemeebeheer");
    private static Button btnManager = new Button("Manager");
    private static HBox start = new HBox(10);
    private static Image image = new Image("http://www.corendonairlines.nl/corendon_logo.jpg", 600, 200, false, false);
    private static ImageView iv1 = new ImageView(image);

    public static GridPane getScreen() {

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25, 25, 25, 25));
        pane.add(start, 40, 40);
        
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

       start.getChildren().addAll(btnBalie, btnSysteem, btnManager, iv1);
        
        
        
        btnBalie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().add(Login.getScreen());
            }
        });

        

        return pane;
    }
}
