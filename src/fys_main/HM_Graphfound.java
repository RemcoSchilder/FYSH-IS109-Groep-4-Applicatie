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
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;

/**
 *
 * @author wessel
 */
public class HM_Graphfound {
    
    final static String open = "Open";
    final static String matched = "Matched";
    final static String send = "Send";
    final static String returned = "Returned";
    
    public static GridPane getScreen() {
        GridPane pane = new GridPane();

        /* Get date */
        DateFormat dateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();
        
        Database DB = new Database();
        DB.setConn();
        ResultSet getTotalLost = DB.getQuery("SELECT COUNT(*) AS totalLost FROM lost GROUP BY status\n" +
               "ORDER BY CASE WHEN status = 'open' THEN '1'\n" +
               "WHEN status = 'matched' THEN '2'\n" +
               "WHEN status = 'send' THEN '3'\n" +
               "ELSE status END ASC");
        
        ResultSet getTotalFound = DB.getQuery("SELECT COUNT(*) AS totalFound FROM found GROUP BY status\n" +
               "ORDER BY CASE WHEN status = 'open' THEN '1'\n" +
               "WHEN status = 'matched' THEN '2'\n" +
               "WHEN status = 'send' THEN '3'\n" +
               "ELSE status END ASC");
        
        try {
            int totalLost[] = new int[4];
            int totalNumberLost = 0;
            
            int totalFound[] = new int[4];
            int totalNumberFound = 0;
            
            while(getTotalLost.next()) {
                totalLost[totalNumberLost] = getTotalLost.getInt("totalLost");
                totalNumberLost++;
            }
            
            while(getTotalFound.next()) {
                totalFound[totalNumberFound] = getTotalFound.getInt("totalFound");
                totalNumberFound++;
            }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 15, 1);
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        
        bc.setTitle("Status Graph " + dateFormat.format(date));
        xAxis.setLabel("Status");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Lost");
        series1.getData().add(new XYChart.Data(open, totalLost[0]));
        series1.getData().add(new XYChart.Data(matched, totalLost[1]));
        series1.getData().add(new XYChart.Data(send, totalLost[2]));
        series1.getData().add(new XYChart.Data(returned, totalLost[3]));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Found");
        series2.getData().add(new XYChart.Data(open, totalFound[0]));
        series2.getData().add(new XYChart.Data(matched, totalFound[1]));
        series2.getData().add(new XYChart.Data(send, totalFound[2]));
        series2.getData().add(new XYChart.Data(returned, totalFound[3]));

            bc.setMinSize(600, 600);  //grote aanpassen

            pane.setAlignment(Pos.CENTER);
            pane.getChildren().addAll(bc);
            bc.getData().addAll(series1, series2);
            
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        return pane;
    }

}