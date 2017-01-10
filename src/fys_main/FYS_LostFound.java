package fys_main;

import javafx.scene.Scene;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author Thijs Timmermans
 */
public class FYS_LostFound extends Application {

    Stage thestage;
    static BorderPane pane = new BorderPane();
    static GridPane grid = new GridPane();
        
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        String css = this.getClass().getResource("style.css").toExternalForm();

        pane = new BorderPane();
        grid = new GridPane();
        
        //aanmaken primary stage
        thestage = primaryStage;
        primaryStage.setTitle("FYS Lost & Found");
        primaryStage.getIcons().add(new Image("logo.jpg"));

        //scene aangemaakt
        Scene scene = new Scene(Start.getScreen(), 1400, 800);
        scene.getStylesheets().add(css);

        //begin van de eerste scene
        thestage = primaryStage;
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}