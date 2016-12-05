package fys_main;

import static fys_main.FYS_LostFound.grid;
import static fys_main.FYS_LostFound.pane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Thijs Timmermans
 */
public class Homepage_Systeem {

    private static Button user = new Button("user");
    private static Button logout = new Button("logout");

    public static VBox vbox() {
        VBox vbox = new VBox();

        //image
        Image image = new Image("https://www.corendon.be/apple-touch-icon-152x152.png", 230, 80, false, false);
        ImageView v1 = new ImageView();
        v1.setImage(image);

        //tekst & buttons
        Text title = new Text("Create");
        user.setMinSize(230, 48);
        logout.setMinSize(230, 48);

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(v1, title, user, logout);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");

        //logout button onderaan
        VBox.setMargin(logout, new Insets(608, 0, 0, 0));

        return vbox;
    }

    public static GridPane table() {
        grid = new GridPane();

        //voegt table toe aan gridpane
        grid.getChildren().add(HS_ViewTable.Datatable());

        //column grootte
        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(75);
        grid.getColumnConstraints().add(column);

        return grid;
    }

    public static BorderPane getScreen() {
        pane = new BorderPane();

        //voeg alles toe aan de borderpane
        pane.setLeft(vbox());
        pane.setCenter(table());

        //eventhandeler bekijken van userlist 
        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(getScreen());

            }
        });

        //eventhandler terug naar start
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                pane.getChildren().clear();
                pane.getScene().setRoot(Start.getScreen());

            }
        });

        return pane;
    }
}
