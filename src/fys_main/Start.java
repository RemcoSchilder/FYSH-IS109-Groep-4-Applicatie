package fys_main;

import static fys_main.FYS_LostFound.grid;
import static fys_main.FYS_LostFound.pane;
import javafx.scene.media.AudioClip;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Thijs Timmermans
 */
public class Start {
    
    //aanmaken allover boderpane
    

    //buttons voor verschillende gebruikers
    private static Button btnBalie = new Button("Baliemedewerker");
    private static Button btnSysteem = new Button("Systeembeheer");
    private static Button btnManager = new Button("Manager");
    private static Button btnLogin = new Button("Inloggen");
    
    //mehtode voor het scherm
    public static BorderPane getScreen() {
        pane = new BorderPane();
        grid = new GridPane();
        
        //gridpane voor center van de borderpane, buttons gebruikers
        grid.setMinSize(150, 150);
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(200, 200, 200, 200));
        
        grid.add(btnBalie, 40, 0);
        grid.add(btnSysteem, 50, 0);
        grid.add(btnManager, 60, 0);
        grid.add(btnLogin, 70, 0);
       
        //gridpane voor top van de borderpane, image corendon
        GridPane grid2 = new GridPane();
        grid2.setMinSize(150, 150);
        grid2.setHgap(5);
        grid2.setVgap(5);
        grid2.setPadding(new Insets(100, 100, 100, 100));
        
        Image image = new Image("logo.jpg", 600, 200, false, false);

        ImageView iv1 = new ImageView(image);
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        grid2.add(iv1, 50, 20);
        
        pane.setCenter(grid);
        pane.setTop(grid2);
        
        //AudioClip sound = new AudioClip("https://ia600501.us.archive.org/33/items/nyannyannyan/NyanCatoriginal.mp3");
   
       
        btnBalie.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               
                //sound.play();
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Baliemedewerker.getScreen());
            }
        });
        
        btnSysteem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Systeem.getScreen());
            }
        });
        
        btnManager.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               pane.getChildren().clear();
               pane.getScene().setRoot(Homepage_Manager.getScreen());
            }
        });
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               pane.getChildren().clear();
               pane.getScene().setRoot(Login.getScreen());
            }
        });
        
        return pane;
    }
}
