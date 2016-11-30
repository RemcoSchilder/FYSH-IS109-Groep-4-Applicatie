package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        String css = this.getClass().getResource("style.css").toExternalForm();

        thestage = primaryStage;
        primaryStage.setTitle("FYS Lost & Found");
        primaryStage.getIcons().add(new Image("https://www.corendon.be/apple-touch-icon-152x152.png"));

        Scene scene = new Scene(Start.getScreen(), 1400, 800);
        scene.getStylesheets().add(css);

        thestage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();

//Startpagina+buttons
//        Start startScherm = new Start();
//        GridPane Scherm = startScherm.getScreen();
//        Scene scene = new Scene(Scherm, 1400, 800);
//        scene.getStylesheets().add(css);
//        
//        Button btnBalie = new Button("Baliemedewerker");
//        HBox hbBtnR = new HBox(10);
//        hbBtnR.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtnR.getChildren().add(btnBalie);
//        Scherm.add(hbBtnR, 50, 20);
//        
//        
//        Button btnSysteem = new Button("Systeembeheerder");
//        HBox hbBtnt = new HBox(10);
//        hbBtnt.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtnt.getChildren().add(btnSysteem);
//        Scherm.add(hbBtnt, 50, 25);
//        
//      
//        Button btnManager = new Button("Manager");
//        HBox hbBtne = new HBox(10);
//        hbBtne.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtne.getChildren().add(btnManager);
//        Scherm.add(hbBtne, 50, 30);
//        
//      
//        Button sqlTest = new Button("Get gebruikers");
//        HBox sqlTestH = new HBox(10);
//        sqlTestH.setAlignment(Pos.BOTTOM_RIGHT);
//        sqlTestH.getChildren().add(sqlTest);
//        Scherm.add(sqlTest, 50, 35);
//
//        //LoginScherm+buttons
//        Login loginScherm = new Login();
//        GridPane Scherm2 = loginScherm.maakHetScherm();
//        Scene scene2 = new Scene(Scherm2, 1400, 800);
//        scene2.getStylesheets().add(css);
//        
//        Button loginBtn = new Button("log in");
//        HBox hbBtn = new HBox();
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().add(loginBtn);
//        Scherm2.add(hbBtn, 50, 27);
//
//
//        Button balieBtn = new Button("Baliemedewerker");
//        HBox hbBtn2 = new HBox(10);
//        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn2.getChildren().add(balieBtn);
//        Scherm2.add(hbBtn2, 50, 20);
//
//        Button balieMederwerkerBtn = new Button("Baliemedewerker");
//        HBox hbBtn3 = new HBox();
//        hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn3.getChildren().add(balieMederwerkerBtn);
//        Scherm2.add(hbBtn3, 51, 20);
//
//        Button systeemBeheerderBtn = new Button("Systeem Beheerder");
//        HBox hbBtn4 = new HBox();
//        hbBtn4.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn4.getChildren().add(systeemBeheerderBtn);
//        Scherm2.add(hbBtn4, 52, 20);
        //homescreen baliemedewerker
//        Scene scene3 = new Scene(Homepage_Baliemedewerker.getScreen(), 1400, 800);
//        scene3.getStylesheets().add(css);
//
//
//        homescreen systeembeheerder+buttons
//        Homepage_Systeembeheer homeScherm_Systeembeheer = new Homepage_Systeembeheer();
//        GridPane Scherm4 = homeScherm_Systeembeheer.maakHetScherm();
//        Scene scene4 = new Scene(Scherm4, 1400, 800);
//        scene4.getStylesheets().add(css);
//
//        homescreen manager+buttons
//        Homepage_Manager homeScherm_Manager = new Homepage_Manager();
//        GridPane Scherm5 = homeScherm_Manager.maakHetScherm();
//        Scene scene5 = new Scene(Scherm5, 1400, 800);
//        scene5.getStylesheets().add(css);
        //eventhandlers voor button functies
//        btnBalie.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(scene3);
//            }
//        });
//
//        btnSysteem.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(scene4);
//
//            }
//        });
//        btnManager.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                primaryStage.setScene(scene5);
//
//            }
//        });
    }

}

//        //lost invulscherm
//        GridPane grid3 = new GridPane();
//        grid3.setMinSize(300, 300);
//        grid3.setHgap(10);
//        grid3.setVgap(10);
//        grid3.setPadding(new Insets(25, 25, 25, 25));
//        Rectangle2D screenBounds3 = Screen.getPrimary().getVisualBounds();
//        Scene scene3 = new Scene(grid3, screenBounds3.getWidth(), screenBounds3.getHeight());
//
//        Text scene3title = new Text("LOST");
//        grid3.add(scene3title, 60, 23);
//
//        Label klantNaam = new Label("Name:");
//        grid3.add(klantNaam, 50, 25);
//        TextField naamTextfield = new TextField();
//        grid3.add(naamTextfield, 51, 25);
//
//        Label klantAdress = new Label("Adress:");
//        grid3.add(klantAdress, 50, 26);
//        TextField adressTextfield = new TextField();
//        grid3.add(adressTextfield, 51, 26);
//
//        Label klantStad = new Label("City/State:");
//        grid3.add(klantStad, 50, 27);
//        TextField stadTextfield = new TextField();
//        grid3.add(stadTextfield, 51, 27);
//
//        Label klantTelefoon = new Label("Telephone:");
//        grid3.add(klantTelefoon, 50, 28);
//        TextField telefoonTextfield = new TextField();
//        grid3.add(telefoonTextfield, 51, 28);
//
//        Label klantVliegveld = new Label("Airport:");
//        grid3.add(klantVliegveld, 50, 29);
//        TextField vliegveldTextfield = new TextField();
//        grid3.add(vliegveldTextfield, 51, 29);
//
//        Label klantDate = new Label("Date:");
//        grid3.add(klantDate, 50, 30);
//        TextField dateTextfield = new TextField();
//        grid3.add(dateTextfield, 51, 30);
//
//        Button addBtn = new Button("\u2713");
//        HBox hbBtn7 = new HBox(10);
//        hbBtn7.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn7.getChildren().add(addBtn);
//        grid3.add(hbBtn7, 51, 31);
//
//found scherm
//        GridPane grid5 = new GridPane();
//        grid5.setMinSize(300, 300);
//        grid5.setHgap(10);
//        grid5.setVgap(10);
//        grid5.setPadding(new Insets(25, 25, 25, 25));
//        Rectangle2D screenBounds5 = Screen.getPrimary().getVisualBounds();
//        Scene scene5 = new Scene(grid5, screenBounds5.getWidth(), screenBounds5.getHeight());
//
//        Label found = new Label("FOUND");
//        grid5.add(found, 60, 23);
//
//        Label klantNaamFound = new Label("Name:");
//        grid5.add(klantNaamFound, 60, 25);
//        TextField naamFoundTextfield = new TextField();
//        grid5.add(naamFoundTextfield, 61, 25);
//
//        Label klantAdressFound = new Label("Adress:");
//        grid5.add(klantAdressFound, 60, 26);
//        TextField adressFoundTextfield = new TextField();
//        grid5.add(adressFoundTextfield, 61, 26);
//
//        Label klantStadFound = new Label("City/State:");
//        grid5.add(klantStadFound, 60, 27);
//        TextField stadFoundTextfield = new TextField();
//        grid5.add(stadFoundTextfield, 61, 27);
//
//        Label klantTelefoonFound = new Label("Telephone:");
//        grid5.add(klantTelefoonFound, 60, 28);
//        TextField telefoonFoundTextfield = new TextField();
//        grid5.add(telefoonFoundTextfield, 61, 28);
//
//        Label klantVliegveldFound = new Label("Airport:");
//        grid5.add(klantVliegveldFound, 60, 29);
//        TextField vliegveldFoundTextfield = new TextField();
//        grid5.add(vliegveldFoundTextfield, 61, 29);
//
//        Label klantDateFound = new Label("Date:");
//        grid5.add(klantDateFound, 60, 30);
//        TextField dateFoundTextfield = new TextField();
//        grid5.add(dateFoundTextfield, 61, 30);
//
//        Button searchBtn = new Button("search");
//        HBox hbBtn9 = new HBox(10);
//        hbBtn9.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn9.getChildren().add(searchBtn);
//        grid5.add(hbBtn9, 60, 31);
//
//        /* TEST*/
////       sqlTest.setOnAction(new EventHandler<ActionEvent>() {
////            @Override
////            public void handle(ActionEvent event) {
////                Database db = new Database();
////                Database.setConn();
////                ResultSet gebruikers = db.doQuery("SELECT * FROM gebruikers");
////                
////                try {
////                    while(gebruikers.next()) {
////                        //Retrieve by column name
////                        int id  = gebruikers.getInt("id");
////                        String naam = gebruikers.getString("naam");
////                        String wachtwoord = gebruikers.getString("wachtwoord");
////
////                        //Display values
////                        System.out.println("ID: " + id);
////                        System.out.println("Naam: " + naam);
////                        System.out.println("Wachtwoord: " + wachtwoord);
////                    }
////                } catch(SQLException se) {
////                   //Handle errors for JDBC
////                   se.printStackTrace();
////                }
////            }
////        });
//        /* EIND TEST */
////        

