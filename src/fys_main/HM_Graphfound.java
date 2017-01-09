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
            
            double MAX_yAxis = 0;
            
            while(getTotalLost.next()) {
                totalLost[totalNumberLost] = getTotalLost.getInt("totalLost");
                totalNumberLost++;
                
                if(MAX_yAxis < totalLost[0]){
                    MAX_yAxis = Math.round(totalLost[0] * 1.20);
                } else if(MAX_yAxis < totalLost[1]){
                    MAX_yAxis = Math.round(totalLost[1] * 1.20);
                } else if(MAX_yAxis < totalLost[2]){
                    MAX_yAxis = Math.round(totalLost[2] * 1.20);
                } else if(MAX_yAxis < totalLost[3]){
                    MAX_yAxis = Math.round(totalLost[3] * 1.20);
                }
            }
            
            while(getTotalFound.next()) {
                totalFound[totalNumberFound] = getTotalFound.getInt("totalFound");
                totalNumberFound++;
                
                if(MAX_yAxis < totalFound[0]){
                    MAX_yAxis = Math.round(totalFound[0] * 1.20);
                } else if(MAX_yAxis < totalFound[1]){
                    MAX_yAxis = Math.round(totalFound[1] * 1.20);
                } else if(MAX_yAxis < totalFound[2]){
                    MAX_yAxis = Math.round(totalFound[2] * 1.20);
                } else if(MAX_yAxis < totalFound[3]){
                
                    MAX_yAxis = Math.round(totalFound[3] * 1.20);
                }
            }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, MAX_yAxis, 1);
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        
        bc.setTitle("Status Graph " + dateFormat.format(date));
        xAxis.setLabel("Status");
        yAxis.setLabel("Number of bagagge");

        XYChart.Series lostColumn = new XYChart.Series();
        lostColumn.setName("Lost");
        lostColumn.getData().add(new XYChart.Data(open, totalLost[0]));
        lostColumn.getData().add(new XYChart.Data(matched, totalLost[1]));
        lostColumn.getData().add(new XYChart.Data(send, totalLost[2]));
        lostColumn.getData().add(new XYChart.Data(returned, totalLost[3]));

        XYChart.Series foundColumn = new XYChart.Series();
        foundColumn.setName("Found");
        foundColumn.getData().add(new XYChart.Data(open, totalFound[0]));
        foundColumn.getData().add(new XYChart.Data(matched, totalFound[1]));
        foundColumn.getData().add(new XYChart.Data(send, totalFound[2]));
        foundColumn.getData().add(new XYChart.Data(returned, totalFound[3]));

            bc.setMinSize(1200, 800);  //grote aanpassen

            pane.setAlignment(Pos.CENTER);
            pane.getChildren().addAll(bc);
            bc.getData().addAll(lostColumn, foundColumn);
            
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        return pane;
    }

}