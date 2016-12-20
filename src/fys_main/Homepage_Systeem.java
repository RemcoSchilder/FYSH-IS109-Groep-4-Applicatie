package fys_main;

import static fys_main.FYS_LostFound.grid;
import static fys_main.FYS_LostFound.pane;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Freek Kuijpers
 */
public class Homepage_Systeem {
    
    private static StackPane stack = new StackPane();
    private static TableView<HS_ViewTable.TableUsers> table;
    private static ColumnConstraints column = new ColumnConstraints();
    private static Button user = new Button("user");
    private static Button logout = new Button("logout");
    private static Button delete = new Button("delete");
    private static Button edit = new Button("edit");
    
    public static VBox vboxLeft() {
        VBox vbox = new VBox();

        //image
        Image image = new Image("https://www.corendon.be/apple-touch-icon-152x152.png", 230, 80, false, false);
        ImageView v1 = new ImageView();
        v1.setImage(image);

        //tekst & buttons
        

        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(v1);

        //style voor de vbox
        vbox.getStyleClass().add("vbox");

        //logout button onderaan
        

        return vbox;
    }
    
    public static HBox hboxBottom() {
        HBox hbox = new HBox();
        
        //buttons
        logout.setMinSize(230, 48);
        
        //alles wordt in de vbox gestopt
        hbox.getChildren().addAll(logout);

        //style voor de vbox
        hbox.getStyleClass().add("vbox");
       // VBox.setMargin(logout, new Insets(566, 0, 0, 0));
       
       
        return hbox;
    }
    
    
    public static VBox vboxRight() {
        VBox vbox = new VBox();
        VBox buttonVbox = new VBox();
        Button search = new Button("Search");
        TextField searchFirstName = new TextField();
        TextField searchLastName = new TextField();
        TextField searchEmail = new TextField();
        
        ObservableList<String> options = 
        FXCollections.observableArrayList(
            "Counter Assistent",
            "Manager",
            "Admin"
        );
        final ComboBox boxFunction = new ComboBox(options);
        
        Label labelFirstName = new Label("First Name: ");
        Label labelLastName = new Label("Last Name: ");
        Label labelEmail = new Label("Email: ");
        Label labelFunction = new Label("Function: ");
        
        //buttons
        search.setMinSize(230, 48);
       
        labelFirstName.getStyleClass().add("labels");
        labelLastName.getStyleClass().add("labels");
        labelEmail.getStyleClass().add("labels");
        labelFunction.getStyleClass().add("labels");
        
        //Stackpane
        user.setMinSize(160, 48);
        edit.setMinSize(160, 48);
        delete.setMinSize(160, 48);
        
        stack.setMinHeight(200);
        buttonVbox.getChildren().addAll(user, edit, delete);
        stack.getChildren().add(buttonVbox);
        stack.setAlignment(Pos.BOTTOM_CENTER);
        
        //alles wordt in de vbox gestopt
        vbox.getChildren().addAll(labelFirstName, searchFirstName, labelLastName
                , searchLastName, labelEmail, searchEmail, labelFunction
                , boxFunction, stack);
        
        VBox.setVgrow(stack, Priority.ALWAYS);
        VBox.setMargin(stack, new Insets(400, 0, 0, 0));
        

        //style voor de vbox
        vbox.getStyleClass().add("vbox");
        

        return vbox;
    }
    

    public static BorderPane getScreen() {
        pane = new BorderPane();
        ButtonType yesButton = new ButtonType("DELETE");
        ButtonType cancelButton = new ButtonType("No");
        Alert alert = new Alert(Alert.AlertType.WARNING);
        
        table = HS_ViewTable.users();
        
        //delete warning popup
        alert.setTitle("Delete");
        alert.setHeaderText("Delete User");
        alert.setContentText("Are u sure you want to delete this user?");
        alert.getButtonTypes().setAll(cancelButton, yesButton);
       
        //voeg alles toe aan de border
        pane.setLeft(vboxLeft());
        pane.setCenter(table);
        pane.setRight(vboxRight());
        pane.setBottom(hboxBottom());
        
        

        //eventhandeler bekijken van userlist 
        user.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                grid.getChildren().clear();
                column.setPercentWidth(20);
                pane.setCenter(HS_CreateUser.getScreen());

            }
        });
        
        //eventhandeler edit van gegevens
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
