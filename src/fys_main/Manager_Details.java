/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Wessel
 */
public class Manager_Details {
    
    //moet allemaal nog gefixt worden
    
        GridPane grid = new GridPane();
        private Stage primaryStage;
        
        
        public void VerzinEenNaam() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 1400, 800);

        primaryStage.setTitle("Manager Details");
        primaryStage.setScene(scene);

        
        
        Text scenetitle = new Text("Luggage information");       
        grid.add(scenetitle, 22, 8);

        Label datum = new Label("Date(D-M-Y)");
        grid.add(datum, 20, 10);
        TextField datumTextfield = new TextField();
        grid.add(datumTextfield, 21, 10);

        Text tijd = new Text("Time");       
        grid.add(tijd, 20, 11);
        
        TextField tijdTextfield = new TextField();
        grid.add(tijdTextfield, 21, 11);

        Label airport = new Label("Airport");
        grid.add(airport, 20, 12);
        TextField airportTextfield = new TextField();
        grid.add(airportTextfield, 21, 12);

        Text luggageinfo = new Text("Bagage label information");
        grid.add(luggageinfo, 20, 13);

        Label labelnumber = new Label("Label number");
        grid.add(labelnumber, 20, 14);
        TextField lablenumberTextfield = new TextField();
        grid.add(lablenumberTextfield, 21, 14);

        Label traveler = new Label("Name Traveler");
        grid.add(traveler, 23, 14);
        TextField travelerTextfield = new TextField();
        grid.add(travelerTextfield, 24, 14);
        
        Label flightnumber = new Label("Flight number");
        grid.add(flightnumber, 20, 15);
        TextField flightTextfield = new TextField();
        grid.add(flightTextfield, 21 , 15);
        
        Label lostfound = new Label("Lost/Found");
        grid.add(lostfound, 23 , 15);
        TextField lfTextField = new TextField();
        grid.add(lfTextField, 24 , 15);
        
        Text bagageinfo = new Text("Luggage Information");
        grid.add(bagageinfo, 20 , 16);
        
        Label Type = new Label("Type");
        grid.add(Type, 20 , 17);
        TextField typeTextField = new TextField();
        grid.add(typeTextField, 21 , 17);
        
        Label Brand = new Label("Brand");
        grid.add(Brand, 20 , 18);
        TextField BrandTextField = new TextField();
        grid.add(BrandTextField, 21, 18);
        
        Label Color = new Label("Color");
        grid.add(Color, 20, 19);
        TextField ColorTextField = new TextField();
        grid.add(ColorTextField, 21, 19);
        
        Label charac = new Label("Characteristics");
        grid.add(charac, 20, 20);
        TextField characTextField = new TextField();
        grid.add(characTextField, 21, 20);
        
        
        Button addBtn = new Button("Return");
        HBox hbBtn7 = new HBox(10);
        hbBtn7.setAlignment(Pos.CENTER);
        hbBtn7.getChildren().add(addBtn);
        grid.add(hbBtn7, 22, 21);

        primaryStage.show();
        }
    
}
