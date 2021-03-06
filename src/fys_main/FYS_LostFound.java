package fys_main;

import javafx.scene.Scene;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Thijs Timmermans
 */
public class FYS_LostFound extends Application {

    Stage thestage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String css = this.getClass().getResource("style.css").toExternalForm();

        //aanmaken primary stage
        thestage = primaryStage;
        primaryStage.setTitle("FYS Lost & Found");
        primaryStage.getIcons().add(new Image("SterLogo.png"));

        //scene aangemaakt
        Scene scene = new Scene(Login.getScreen(), 1400, 800);
        scene.getStylesheets().add(css);

        //begin van de eerste scene
        thestage = primaryStage;
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
