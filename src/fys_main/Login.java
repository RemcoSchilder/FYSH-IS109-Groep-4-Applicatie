package fys_main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import jxl.write.WriteException;

/**
 *
 * @author Thijs Timmermans
 */
public class Login {

    protected static TextField usernameT = new TextField();
    protected static ResultSet checkLogin;
    protected static BorderPane pane;

    //creates screen
    public static BorderPane getScreen() {
        pane = new BorderPane();
        //creates labels with style
        Label error = new Label("");
        error.getStyleClass().add("error");

        Label usernameL = new Label("Username:");
        Label passwordL = new Label("Password:");

        //creates passwordField
        PasswordField passwordT = new PasswordField();

        //creates hyperlink
        Hyperlink resetPassword = new Hyperlink("Reset Password/Email");

        //creates button
        Button login = new Button("Log in");

        //creates gridpane and adds components
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

        //creates image
        Image image = new Image("Logo-OBV-Nieuw.png", 700, 200, false, false);
        ImageView iv1 = new ImageView(image);
        iv1.setImage(image);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        //creates gridpane
        GridPane grid2 = new GridPane();
        grid2.add(iv1, 0, 1);
        grid2.setAlignment(Pos.TOP_CENTER);
        GridPane.setColumnSpan(error, 3);

        //add gridpane components
        pane.setCenter(grid);
        pane.setTop(grid2);
        pane.getStyleClass().add("background");

        // Eventhandler login
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                try {
                    MakeExcel.createExcel();
                } catch (IOException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } catch (WriteException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //checks fields
                if (usernameT.getText().equals("")
                        || passwordT.getText().equals("")) {

                    //sets error text
                    error.setText("Username and/or Password can not be empty");
                    return;
                }

                //conncecs to database
                Database DB = new Database();
                Database.setConn();

                //gets query
                checkLogin = DB.getQuery("SELECT function, question "
                        + "FROM users "
                        + "WHERE username='" + usernameT.getText() + "' "
                        + "AND password=MD5('" + passwordT.getText() + "')");

                //checks fields
                try {
                    if (checkLogin.next()) {
                        if (checkLogin.getString("question").equals("")) {
                            //sets firstlogin method as center
                            pane.setCenter(FirstLogin.getScreen());
                        } else {
                            //activates method
                            functionControl();
                        }
                    } else {
                        //sets error text
                        error.setText("Wrong username or password");
                    }
                } catch (SQLException se) {
                    //Handle errors for JDBC

                }
                //clears fields
                usernameT.clear();
                passwordT.clear();
            }
        });

        //resetPassword button pressed
        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //sets recover screen as center
                pane.setCenter(Recover.getScreen());
            }
        });

        return pane;
    }

    public static void functionControl() throws SQLException {
        //checks users function
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
