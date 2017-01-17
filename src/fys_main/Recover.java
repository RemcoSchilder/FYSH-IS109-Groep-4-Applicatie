/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import static fys_main.FYS_LostFound.pane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author freek
 */
public class Recover {

    private static Text title, error;
    protected static TextField usernameT = new TextField();
    private static Button cancel = new Button("Cancel");

    public static GridPane getScreen() {
        title = new Text("Recover Password");
        title.getStyleClass().add("title");

        error = new Text("");
        error.getStyleClass().add("error");

        Label usernameL = new Label("Username");
        Button nextPassword = new Button("Go to password");
        Button nextEmail = new Button("Go to email");

        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(title, 0, 0);
        grid.add(usernameL, 0, 1);
        grid.add(usernameT, 1, 1, 5, 1);
        grid.add(cancel, 0, 2);
        grid.add(nextPassword, 1, 2);
        grid.add(nextEmail, 2, 2);
        grid.add(error, 0, 3);
        grid.setAlignment(Pos.CENTER);
        
        GridPane.setColumnSpan(title, 3);
        GridPane.setColumnSpan(error, 3);

        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());
            }
        });
        
        nextPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (usernameT.getText() == null
                        || usernameT.getText().trim().isEmpty()) {

                    error.setText("You have not filled the field");

                    return;
                }

                Database DB = new Database();
                Database.setConn();
                ResultSet controlFields = DB.getQuery("SELECT username "
                        + "FROM users "
                        + "WHERE username='" + usernameT.getText() + "'");

                try {
                    if (!controlFields.next()) {
                        error.setText("Username does not exist");
                    } else {
                        pane.setCenter(passwordScreen());
                    }
                } catch (SQLException se) {
                    //Handle errors for JDBC
                }
            }
        });
        
        nextEmail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (usernameT.getText() == null
                        || usernameT.getText().trim().isEmpty()) {

                    error.setText("You have not filled the field");

                    return;
                }

                Database DB = new Database();
                Database.setConn();
                ResultSet controlFields = DB.getQuery("SELECT username "
                        + "FROM users "
                        + "WHERE username='" + usernameT.getText() + "'");

                try {
                    if (!controlFields.next()) {
                        error.setText("Username does not exist");
                    } else {
                        pane.setCenter(emailScreen());
                    }
                } catch (SQLException se) {
                    //Handle errors for JDBC
                }
            }
        });

        return grid;
    }

    public static GridPane passwordScreen() throws SQLException {
        Label questionL = new Label("Question:");
        Label questionT = new Label("");
        Label answerL = new Label("Answer:");

        Label password = new Label("Email:");
        password.getStyleClass().add("subheading");

        Label passwordL1 = new Label("New Email");
        Label passwordL2 = new Label("Confirm password:");

        PasswordField answerT = new PasswordField();
        PasswordField passwordT1 = new PasswordField();
        PasswordField passwordT2 = new PasswordField();

        Button resetPassword = new Button("Reset Password");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        error.setText("");

        grid.add(title, 0, 0);
        grid.add(questionL, 0, 1);
        grid.add(questionT, 1, 1, 1, 1);
        grid.add(answerL, 0, 2);
        grid.add(answerT, 1, 2, 1, 1);
        grid.add(password, 0, 3);
        grid.add(passwordL1, 0, 4);
        grid.add(passwordT1, 1, 4, 1, 1);
        grid.add(passwordL2, 0, 5);
        grid.add(passwordT2, 1, 5, 1, 1);
        grid.add(resetPassword, 0, 6);
        grid.add(error, 0, 7);
        grid.setAlignment(Pos.CENTER);

        Database DB = new Database();
        Database.setConn();
        ResultSet controlFields = DB.getQuery("SELECT email, question, answer FROM users WHERE username='" + usernameT.getText() + "'");

        if (controlFields.next()) {
            questionT.setText(controlFields.getString("question"));
        }
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());
            }
        });
        
        resetPassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (answerT.getText() == null
                        || answerT.getText().trim().isEmpty()
                        || passwordT1.getText() == null
                        || passwordT1.getText().trim().isEmpty()
                        || passwordT2.getText() == null
                        || passwordT2.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the fields");
                    return;
                } else if (!passwordT1.getText().equals(passwordT2.getText())) {
                    error.setText("passwords do not match");
                    return;
                }

                try {
                    if (!controlFields.getString("answer").equals(answerT.getText())) {
                        error.setText("Answer is incorrect");
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
                }

                Database test = new Database();
                Database.setConn();
                test.setQuery("Update users SET "
                        + "password='" + passwordT1.getText() + "'"
                        + "WHERE username='" + usernameT.getText() + "'");

                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());

            }
        });

        return grid;
    }
    
    public static GridPane emailScreen() throws SQLException {
        Label questionL = new Label("Question:");
        Label questionT = new Label("");
        Label answerL = new Label("Answer:");

        Label email = new Label("Email:");
        email.getStyleClass().add("subheading");

        Label emailL1 = new Label("Old email");
        Label emailT1 = new Label("");
        
        Label emailL2 = new Label("New email:");
        TextField emailT2 = new TextField("");

        PasswordField answerT = new PasswordField();

        Button resetEmail = new Button("Reset Email");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        error.setText("");

        grid.add(title, 0, 0);
        grid.add(questionL, 0, 1);
        grid.add(questionT, 1, 1, 1, 1);
        grid.add(answerL, 0, 2);
        grid.add(answerT, 1, 2, 1, 1);
        grid.add(email, 0, 3);
        grid.add(emailL1, 0, 4);
        grid.add(emailT1, 1, 4, 1, 1);
        grid.add(emailL2, 0, 5);
        grid.add(emailT2, 1, 5, 1, 1);
        grid.add(resetEmail, 0, 5);
        grid.add(error, 0, 6);
        grid.setAlignment(Pos.CENTER);

        Database DB = new Database();
        Database.setConn();
        ResultSet controlFields = DB.getQuery("SELECT question, answer, email FROM users WHERE username='" + usernameT.getText() + "'");

        if (controlFields.next()) {
            questionT.setText(controlFields.getString("question"));
            emailT1.setText(controlFields.getString("email"));
        }
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());
            }
        });
        
        resetEmail.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (answerT.getText() == null
                        || answerT.getText().trim().isEmpty()
                        || emailT2.getText() == null
                        || emailT2.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the fields");
                    return;
                }

                try {
                    if (!controlFields.getString("answer").equals(answerT.getText())) {
                        error.setText("Answer is incorrect");
                        return;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Recover.class.getName()).log(Level.SEVERE, null, ex);
                }

                Database test = new Database();
                Database.setConn();
                test.setQuery("Update users SET "
                        + "email='" + emailT2.getText() + "'"
                        + "WHERE username='" + usernameT.getText() + "'");

                pane.getChildren().clear();
                pane.getScene().setRoot(Login.getScreen());

            }
        });

        return grid;
    }
}
