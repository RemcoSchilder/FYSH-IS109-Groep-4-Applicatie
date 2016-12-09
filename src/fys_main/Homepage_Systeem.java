package fys_main;

import static fys_main.FYS_LostFound.grid;
import static fys_main.FYS_LostFound.pane;
import java.util.List;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
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
    
    private static TableView<HS_ViewTable.TableUsers> table;
    private static ColumnConstraints column = new ColumnConstraints();
    private static Button user = new Button("user");
    private static Button logout = new Button("logout");
    private static Button delete = new Button("delete");
    private static Button edit = new Button("edit");
    
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
        table = HS_ViewTable.users();
        
        //button size
        edit.setMinSize(180, 48);
        delete.setMinSize(180, 48);
        
        //voegt table toe aan gridpane
        grid.add(table, 0, 0);
        grid.add(edit, 4, 4);
        grid.add(delete, 4, 6);

        //column grootte
        column.setPercentWidth(75);
        grid.getColumnConstraints().add(column);
        

        return grid;
    }

    public static BorderPane getScreen() {
        pane = new BorderPane();
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        alert.setTitle("Delete");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are u sure you want to delete this user?");
        
        ButtonType yesButton = new ButtonType("DELETE");
        ButtonType cancelButton = new ButtonType("No");
        
        alert.getButtonTypes().setAll(cancelButton, yesButton);
       
        //voeg alles toe aan de borderpane
        pane.setLeft(vbox());
        pane.setCenter(table());

        //eventhandeler bekijken van userlist 
        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                column.setPercentWidth(20);
                pane.setCenter(HS_CreateUser.getScreen());

            }
        });
        
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
        
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                String username = table.getSelectionModel().getSelectedItem().getUsername();
                      
                Optional<ButtonType> result = alert.showAndWait();
                
                if(result.get() == yesButton)
                {                       
                    Database test = new Database();
                    test.setConn();
                    test.setQuery("DELETE FROM users WHERE username = '" + username + "'" );
                    
                    pane.getChildren().clear();
                    pane.getScene().setRoot(Homepage_Systeem.getScreen());
                }
                else if(result.get() == cancelButton)
                {

                }  

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
