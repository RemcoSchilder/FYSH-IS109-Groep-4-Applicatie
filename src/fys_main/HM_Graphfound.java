/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fys_main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author wessel
 */
public class HM_Graphfound {

    public static GridPane getScreen() {
        GridPane pane = new GridPane();

        /* Get date */
        DateFormat dateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();
        
        Database DB = new Database();
        DB.setConn();
        ResultSet getTotal = DB.getQuery("SELECT COUNT(*) AS total, MONTH(date) AS month FROM found GROUP BY MONTH(date) ORDER BY MONTH(date)");
        
        try {
            double total[] = new double[12];
            int totalNumber = 0;
            
            while(getTotal.next()) {
                total[getTotal.getInt("month")] = getTotal.getDouble("total");
                totalNumber++;
            }
            
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                            new PieChart.Data("January", total[0]),
                            new PieChart.Data("February", total[1]),
                            new PieChart.Data("March", total[2]),
                            new PieChart.Data("April", total[3]),
                            new PieChart.Data("May", total[4]),
                            new PieChart.Data("June", total[5]),
                            new PieChart.Data("July", total[6]),
                            new PieChart.Data("August", total[7]),
                            new PieChart.Data("September", total[8]),
                            new PieChart.Data("October", total[9]),
                            new PieChart.Data("November", total[10]),
                            new PieChart.Data("December", total[11]));
        
            PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Found Lugagge " + dateFormat.format(date));

            chart.setMinSize(600, 600);  //grote aanpassen

            pane.setAlignment(Pos.CENTER);
            pane.add(chart, 0, 0);
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        return pane;
    }

}