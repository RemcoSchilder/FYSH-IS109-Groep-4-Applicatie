package fys_main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thijs Timmermans
 */
public class Start {

    private static BorderPane pane = new BorderPane();

    private static Button btnBalie = new Button("Baliemedewerker");
    private static Button btnSysteem = new Button("Systemeebeheer");
    private static Button btnManager = new Button("Manager");
    private static HBox start = new HBox(10);
    
            

    public static BorderPane getScreen() {

//       pane.getChildren().setAlignment(Pos.CENTER);
//       pane.setHgap(10);
//        pane.setVgap(10);


        //       dit moet er nog staan na de pull

        
        GridPane grid = new GridPane();
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(200, 200, 200, 200));
        
        grid.add(btnBalie, 30, 20);
        grid.add(btnSysteem, 30, 25);
        grid.add(btnManager, 30, 30);
       
        
        
        GridPane grid2 = new GridPane();
        grid2.setMinSize(150, 150);
        grid2.setHgap(5);
        grid2.setVgap(5);
        grid2.setPadding(new Insets(100, 100, 100, 100));
        
        Image image = new Image("http://www.corendonairlines.nl/corendon_logo.jpg", 600, 200, false, false);

        ImageView iv1 = new ImageView(image);
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(iv1);
        grid2.getChildren().add(box);
        
        pane.setCenter(grid);
        pane.setTop(grid2);
       
         
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
                pane.getChildren().add(SysM_Homepage.getScreen());
            }
        });
        
        btnManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               pane.getChildren().clear();
               pane.getChildren().add( Homepage_Manager.getScreen());
            }
        });
        


        

     
        return pane;
    }
}
