package fys_main;

import static fys_main.FYS_LostFound.grid;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Menno
 */
public class HM_luggageList {
    
    private static GridPane screen = new GridPane();
    
    public static GridPane getScreen() {
        screen.add(HB_SearchBaggage.getScreen(), 0, 0);
        
        return screen;
    }
    
}