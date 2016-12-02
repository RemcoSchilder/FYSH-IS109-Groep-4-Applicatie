/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author wessel
 */
public class HM_Graphlost {

    //stage en gridpane maken
    private Stage loststage;
    GridPane lostgraph = new GridPane();

    public void foundstatistieken() {

        Scene scene = new Scene(new Group());
        loststage.setTitle("Lost Graph");

        /*grafiek maken en informatie geven. ik heb dit nu met getallen gedaan, deze 
        getallen moeten eigenlijk informatie vanuit de database zijn.
         */
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("January", 10),
                        new PieChart.Data("February", 15),
                        new PieChart.Data("March", 10),
                        new PieChart.Data("April", 5),
                        new PieChart.Data("May", 5),
                        new PieChart.Data("June", 10),
                        new PieChart.Data("July", 10),
                        new PieChart.Data("August", 5),
                        new PieChart.Data("September", 8),
                        new PieChart.Data("October", 5),
                        new PieChart.Data("November", 7),
                        new PieChart.Data("December", 5));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Lost Lugagge");

        chart.setMinSize(600, 600);  //grote aanpassen

        ((Group) scene.getRoot()).getChildren().add(chart);
        loststage.setScene(scene);
        loststage.show();

    }

}