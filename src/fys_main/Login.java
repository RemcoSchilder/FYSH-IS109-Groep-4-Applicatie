package fys_main;

import static fys_main.FYS_LostFound.pane;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Login {

    protected static TextField usernameT = new TextField();
    protected static ResultSet checkLogin;

    public static BorderPane getScreen() {

        Label error = new Label("");
        error.getStyleClass().add("error");

        Label usernameL = new Label("Username:");
        Label passwordL = new Label("Password:");

        PasswordField passwordT = new PasswordField();

        Hyperlink resetPassword = new Hyperlink("Reset Password/Email");

        Button login = new Button("Log in");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setAlignment(Pos.CENTER);

        grid.add(usernameL, 0, 1);
        grid.add(usernameT, 1, 1, 1, 1);

        grid.add(passwordL, 0, 2);
        grid.add(passwordT, 1, 2, 1, 1);

        grid.add(login, 0, 3);
        grid.add(resetPassword, 0, 4);
        grid.add(error, 0, 5);

        Image image = new Image("Logo-OBV-Nieuw.png", 700, 200, false, false);

        ImageView iv1 = new ImageView(image);
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        GridPane grid2 = new GridPane();
        grid2.add(iv1, 0, 1);
        grid2.setAlignment(Pos.TOP_CENTER);
        GridPane.setColumnSpan(error, 3);

        pane.setCenter(grid);
        pane.setTop(grid2);
        pane.getStyleClass().add("background");

        // Eventhandler login
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameT.getText().equals("")
                        || passwordT.getText().equals("")) {

                    error.setText("Username and/or Password can not be empty");
                    return;
                }

                Database DB = new Database();
                Database.setConn();

                checkLogin = DB.getQuery("SELECT function, question "
                        + "FROM users "
                        + "WHERE username='" + usernameT.getText() + "' "
                        + "AND password=MD5('" + passwordT.getText() + "')");

                try {
                    if (checkLogin.next()) {
                        if (checkLogin.getString("question").equals("")) {
                            pane.setCenter(FirstLogin.getScreen());
                        } else {
                            functionControl();
                        }
                    } else {
                        error.setText("Wrong username or password");
                    }
                } catch (SQLException se) {
                    //Handle errors for JDBC

                }
                
                usernameT.clear();
                passwordT.clear();
            }
        });

        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                pane.setCenter(Recover.getScreen());
            }
        });

        return pane;
    }

    public static void functionControl() throws SQLException {

        switch (checkLogin.getString("function")) {
            case "Counter Assistant":
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Baliemedewerker.getScreen());
                break;
            case "Manager":
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Manager.getScreen());
                break;
            case "System Manager":
                pane.getChildren().clear();
                pane.getScene().setRoot(Homepage_Systeem.getScreen());
                break;
            default:
                break;
        }

    }
}
