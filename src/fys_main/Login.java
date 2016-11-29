package fys_main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Login {

    public GridPane maakHetScherm() {

        GridPane grid = new GridPane();
        grid.setMinSize(300, 300);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

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

        return grid;
    }

}
