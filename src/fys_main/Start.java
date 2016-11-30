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
    
            

    public static GridPane getScreen() {

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        
        start.getChildren().addAll(btnBalie, btnSysteem, btnManager);
        pane.add(start, 50, 50);
        
        btnBalie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getChildren().add(Login.getScreen());
            }
        });
        
        btnSysteem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getChildren().add(Login.getScreen());
            }
        });
        
        btnManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               pane.getChildren().clear();
               pane.getChildren().add( Homepage_Manager.getScreen());
            }
        });
        


        Image image = new Image("http://www.corendonairlines.nl/corendon_logo.jpg", 600, 200, false, false);

        ImageView iv1 = new ImageView(image);
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        //sceneR.setFill(Color.WHITE);
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(iv1);
        pane.getChildren().add(box);
//
//        primaryStage.getIcons().add(new Image("https://www.corendon.be/apple-touch-icon-152x152.png"));
        return pane;
    }
}
