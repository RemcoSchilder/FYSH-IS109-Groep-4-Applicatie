package fys_main;

import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author Thijs Timmermans
 */
public class FYS_LostFound extends Application {

    Stage thestage;
    Button btn;
    Scene scene, scene2;

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("FYS Lost & Found");
        thestage = primaryStage;

        GridPane gridR = new GridPane();
        gridR.setMinSize(300, 300);
        gridR.setHgap(10);
        gridR.setVgap(10);
        gridR.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene sceneR = new Scene(gridR, screenBounds.getWidth(), screenBounds.getHeight());
     
        Button btnR = new Button("Administrator");
        HBox hbBtnR = new HBox(10);
        hbBtnR.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnR.getChildren().add(btnR);
        gridR.add(hbBtnR, 50, 20);
        
        
        Button btnt = new Button("Systeembeheerder");
        HBox hbBtnt = new HBox(10);
        hbBtnt.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnt.getChildren().add(btnt);
        gridR.add(hbBtnt, 50, 25);
        
      
        Button btne = new Button("Systeembeheerder");
        HBox hbBtne = new HBox(10);
        hbBtne.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtne.getChildren().add(btne);
        gridR.add(hbBtne, 50, 30);
        
        Image image = new Image("http://www.corendonairlines.nl/corendon_logo.jpg", 600, 200, false, false);
        
        // Corendon afbeelding boven aan de app.
        ImageView iv1 = new ImageView(image);
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        sceneR.setFill(Color.WHITE);
        VBox box = new VBox();
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(iv1);
        gridR.getChildren().add(box);
        
        //logo van de app
        primaryStage.getIcons().add(new Image("https://www.corendon.be/apple-touch-icon-152x152.png"));
        
        
        
        
        
        
        GridPane grid = new GridPane();
        grid.setMinSize(300, 300);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBoundsR = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(grid, screenBoundsR.getWidth(), screenBoundsR.getHeight());

        primaryStage.setScene(sceneR);

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 50, 24, 40, 20);

        Label userName = new Label("User Name:");
        grid.add(userName, 50, 25);
        TextField userTextField = new TextField();
        grid.add(userTextField, 51, 25);

        Label pw = new Label("Password:");
        grid.add(pw, 50, 26);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 51, 26);

        Button loginBtn = new Button("log in");
        HBox hbBtn = new HBox();
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginBtn);
        grid.add(hbBtn, 50, 27);


        Button adminBtn = new Button("Admin");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(adminBtn);
        grid.add(hbBtn2, 50, 20);

        Button balieMederwerkerBtn = new Button("Baliemedewerker");
        HBox hbBtn3 = new HBox();
        hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn3.getChildren().add(balieMederwerkerBtn);
        grid.add(hbBtn3, 51, 20);

        Button systeemBeheerderBtn = new Button("Systeem Beheerder");
        HBox hbBtn4 = new HBox();
        hbBtn4.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn4.getChildren().add(systeemBeheerderBtn);
        grid.add(hbBtn4, 52, 20);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        GridPane grid2 = new GridPane();
        grid2.setMinSize(300, 300);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBounds2 = Screen.getPrimary().getVisualBounds();
        Scene scene2 = new Scene(grid2, screenBounds2.getWidth(), screenBounds2.getHeight());
        
        Text scene2title = new Text("Lost & Found");
        grid2.add(scene2title, 90, 40);

        Button lostBtn = new Button("LOST");
        HBox hbBtn5 = new HBox();
        hbBtn5.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn5.getChildren().add(lostBtn);
        grid2.add(hbBtn5, 90, 42);

        Button foundBtn = new Button("FOUND");
        HBox hbBtn6 = new HBox();
        hbBtn6.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn6.getChildren().add(foundBtn);
        grid2.add(hbBtn6, 90, 43);

        GridPane grid3 = new GridPane();
        grid3.setMinSize(300, 300);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBounds3 = Screen.getPrimary().getVisualBounds();
        Scene scene3 = new Scene(grid3, screenBounds3.getWidth(), screenBounds3.getHeight());

        Text scene3title = new Text("LOST");
        grid3.add(scene3title, 60, 23);

        Label klantNaam = new Label("Name:");
        grid3.add(klantNaam, 50, 25);
        TextField naamTextfield = new TextField();
        grid3.add(naamTextfield, 51, 25);

        Label klantAdress = new Label("Adress:");
        grid3.add(klantAdress, 50, 26);
        TextField adressTextfield = new TextField();
        grid3.add(adressTextfield, 51, 26);

        Label klantStad = new Label("City/State:");
        grid3.add(klantStad, 50, 27);
        TextField stadTextfield = new TextField();
        grid3.add(stadTextfield, 51, 27);

        Label klantTelefoon = new Label("Telephone:");
        grid3.add(klantTelefoon, 50, 28);
        TextField telefoonTextfield = new TextField();
        grid3.add(telefoonTextfield, 51, 28);

        Label klantVliegveld = new Label("Airport:");
        grid3.add(klantVliegveld, 50, 29);
        TextField vliegveldTextfield = new TextField();
        grid3.add(vliegveldTextfield, 51, 29);

        Label klantDate = new Label("Date:");
        grid3.add(klantDate, 50, 30);
        TextField dateTextfield = new TextField();
        grid3.add(dateTextfield, 51, 30);

        Button addBtn = new Button("\u2713");
        HBox hbBtn7 = new HBox(10);
        hbBtn7.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn7.getChildren().add(addBtn);
        grid3.add(hbBtn7, 51, 31);

        GridPane grid4 = new GridPane();
        grid4.setMinSize(300, 300);
        grid4.setHgap(10);
        grid4.setVgap(10);
        grid4.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBounds4 = Screen.getPrimary().getVisualBounds();
        Scene scene4 = new Scene(grid4, screenBounds4.getWidth(), screenBounds4.getHeight());

        Label toegevoegdeBagage = new Label("De bagage is toegevoegd");
        grid4.add(toegevoegdeBagage, 60, 23);
        
        Random rand = new Random();
        int n = rand.nextInt(1000) + 1;
        Label lostfoundId = new Label("Lost & Found ID = " + n);
        grid4.add(lostfoundId, 60, 25);
        
        Button terugLostBtn = new Button("< Terug naar Lost & Found");
        HBox hbBtn8 = new HBox(10);
        hbBtn8.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn8.getChildren().add(terugLostBtn);
        grid4.add(hbBtn8, 60, 30);
        
        GridPane grid5 = new GridPane();
        grid5.setMinSize(300, 300);
        grid5.setHgap(10);
        grid5.setVgap(10);
        grid5.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBounds5 = Screen.getPrimary().getVisualBounds();
        Scene scene5 = new Scene(grid5, screenBounds5.getWidth(), screenBounds5.getHeight());
        
        Label found = new Label("FOUND");
        grid5.add(found, 60, 23);
        
        Label klantNaamFound = new Label("Name:");
        grid5.add(klantNaamFound, 60, 25);
        TextField naamFoundTextfield = new TextField();
        grid5.add(naamFoundTextfield, 61, 25);

        Label klantAdressFound = new Label("Adress:");
        grid5.add(klantAdressFound, 60, 26);
        TextField adressFoundTextfield = new TextField();
        grid5.add(adressFoundTextfield, 61, 26);

        Label klantStadFound = new Label("City/State:");
        grid5.add(klantStadFound, 60, 27);
        TextField stadFoundTextfield = new TextField();
        grid5.add(stadFoundTextfield, 61, 27);

        Label klantTelefoonFound = new Label("Telephone:");
        grid5.add(klantTelefoonFound, 60, 28);
        TextField telefoonFoundTextfield = new TextField();
        grid5.add(telefoonFoundTextfield, 61, 28);

        Label klantVliegveldFound = new Label("Airport:");
        grid5.add(klantVliegveldFound, 60, 29);
        TextField vliegveldFoundTextfield = new TextField();
        grid5.add(vliegveldFoundTextfield, 61, 29);

        Label klantDateFound = new Label("Date:");
        grid5.add(klantDateFound, 60, 30);
        TextField dateFoundTextfield = new TextField();
        grid5.add(dateFoundTextfield, 61, 30);
        
        Button searchBtn = new Button("search");
        HBox hbBtn9 = new HBox(10);
        hbBtn9.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn9.getChildren().add(searchBtn);
        grid5.add(hbBtn9, 60, 31);
        
        GridPane grid6 = new GridPane();
        grid6.setMinSize(300, 300);
        grid6.setHgap(10);
        grid6.setVgap(10);
        grid6.setPadding(new Insets(25, 25, 25, 25));
        Rectangle2D screenBounds6 = Screen.getPrimary().getVisualBounds();
        Scene scene6 = new Scene(grid6, screenBounds6.getWidth(), screenBounds6.getHeight());
        
        btnR.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);

            }
        });
        
       btnt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);

            }
        });
        
        btne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene);

            }
        });
        
        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);

            }
        });

        lostBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene3);

            }
        });

        foundBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene5);

            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene4);

            }
        });
        
        terugLostBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene2);

            }
        });
        
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(scene6);

            }
        });
        
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

