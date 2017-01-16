/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import static fys_main.Login.usernameT;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author freek
 */
public class FirstLogin {

    private static Label questionL, answerL1, answerL2;
    private static TextField questionT = new TextField();
    private static PasswordField answerT1 = new PasswordField();
    private static PasswordField answerT2 = new PasswordField();
    private static Button finish = new Button("Finish");
    private static ButtonType yesButton, cancelButton;

    public static Alert alertPopup() {
        yesButton = new ButtonType("Yes");
        cancelButton = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setTitle("Extra Security");
        alert.setHeaderText("Answer & Question");
        alert.setContentText("Are u sure you want to use:\n\n"
                + "Question: " + questionT.getText() + "\n"
                + "Answer: " + answerT1.getText().toString());
        alert.getButtonTypes().setAll(cancelButton, yesButton);

        return alert;
    }

    public static GridPane getScreen() {
        questionL = new Label("Question:");
        answerL1 = new Label("Answer:");
        answerL2 = new Label("Confirm Answer:");

        Text error = new Text("");
        error.getStyleClass().add("error");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Extra security");
        sceneTitle.getStyleClass().add("title");

        Text explanation = new Text();
        explanation.setText("Write a question where only you know the answer!");

        Text example = new Text();
        example.setText("Example: "
                + "\n\t Question: What was the name of my first pet?"
                + "\n\t Answer:   Max");

        grid.add(sceneTitle, 0, 0);
        grid.add(explanation, 0, 1);
        grid.add(example, 0, 2);
        grid.add(questionL, 0, 3);
        grid.add(questionT, 1, 3, 1, 1);
        grid.add(answerL1, 0, 4);
        grid.add(answerT1, 1, 4, 1, 1);
        grid.add(answerL2, 0, 5);
        grid.add(answerT2, 1, 5, 1, 1);
        grid.add(finish, 0, 6);
        grid.add(error, 0, 7);
        grid.setAlignment(Pos.CENTER);

        finish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (questionT.getText() == null
                        || questionT.getText().trim().isEmpty()
                        || answerT1.getText() == null
                        || answerT1.getText().trim().isEmpty()
                        || answerT2.getText() == null
                        || answerT2.getText().trim().isEmpty()) {

                    error.setText("You have not filled all the fields");

                    return;
                } else if (!answerT1.getText().equals(answerT2.getText())) {
                    error.setText("Answers are different");

                    return;
                }

                Optional<ButtonType> result = alertPopup().showAndWait();

                if (result.get() == yesButton) {

                    Database test = new Database();
                    Database.setConn();
                    test.setQuery("Update users SET "
                            + "question='" + questionT.getText() + "', "
                            + "answer='" + answerT1.getText() + "' "
                            + "WHERE username ='" + usernameT.getText() + "'");

                    try {
                        Login.functionControl();
                    } catch (SQLException ex) {
                        Logger.getLogger(FirstLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (result.get() == cancelButton) {

                    alertPopup().close();
                }
            }
        });
        return grid;
    }
}
