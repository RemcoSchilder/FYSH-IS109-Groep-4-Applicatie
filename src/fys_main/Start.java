package fys_main;

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

    public GridPane maakHetScherm() {

        GridPane grid = new GridPane();
        grid.setMinSize(300, 300);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Button btnAdmin = new Button("Administrator");
        HBox hbBtnR = new HBox(10);
        hbBtnR.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnR.getChildren().add(btnAdmin);
        grid.add(hbBtnR, 50, 20);

        Button btnSysteem = new Button("Systeembeheerder");
        HBox hbBtnt = new HBox(10);
        hbBtnt.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnt.getChildren().add(btnSysteem);
        grid.add(hbBtnt, 50, 25);

        Button btnManager = new Button("Manager");
        HBox hbBtne = new HBox(10);
        hbBtne.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtne.getChildren().add(btnManager);
        grid.add(hbBtne, 50, 30);

        Button sqlTest = new Button("Get gebruikers");
        HBox sqlTestH = new HBox(10);
        sqlTestH.setAlignment(Pos.BOTTOM_RIGHT);
        sqlTestH.getChildren().add(sqlTest);
        grid.add(sqlTest, 50, 35);

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
        grid.getChildren().add(box);

        //primaryStage.getIcons().add(new Image("https://www.corendon.be/apple-touch-icon-152x152.png"));
        return grid;
    }
}
