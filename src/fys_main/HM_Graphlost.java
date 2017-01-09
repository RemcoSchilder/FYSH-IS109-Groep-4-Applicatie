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
public class HM_Graphlost {

    final static String january = "January";
    final static String february = "February";
    final static String march = "March";
    final static String april = "April";
    final static String may = "May";
    final static String june = "June";
    final static String july = "July";
    final static String august = "August";
    final static String september = "September";
    final static String october = "October";
    final static String november = "November";
    final static String december = "December";
    
    public static GridPane getScreen() {
        GridPane pane = new GridPane();

        /* Get date */
        DateFormat dateFormat = new SimpleDateFormat("YYYY");
        Date date = new Date();
        
        // all database queries
        Database DB = new Database();
        DB.setConn();
        ResultSet getTotalLost = DB.getQuery("SELECT COUNT(*) AS total, MONTH(date) AS month FROM lost GROUP BY MONTH(date) ORDER BY MONTH(date)");
        ResultSet getTotalMatched = DB.getQuery("SELECT lost.status, COUNT(*) AS total, MONTH(date) AS month FROM lost WHERE status = 'matched' GROUP BY MONTH(date) ORDER BY MONTH(date)");
        ResultSet getTotal = DB.getQuery("SELECT COUNT(*) AS total FROM lost GROUP BY MONTH(date) ORDER BY total DESC LIMIT 1");
        
        try {
            double totalLost[] = new double[12];
            double totalMatched[] = new double[12];
            double totalNumber = 0;
            
            while(getTotalLost.next()) {
                totalLost[getTotalLost.getInt("month")-1] = getTotalLost.getDouble("total");
                totalNumber++;
                
            }
            
            while(getTotalMatched.next()) {
                totalMatched[getTotalMatched.getInt("month")-1] = getTotalMatched.getDouble("total");
                totalNumber++;
                
            }
            
        getTotal.next();
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, Math.round(getTotal.getInt("total") * 1.20), 1);
        final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
        
        bc.setTitle("Month overview " + dateFormat.format(date));
        xAxis.setLabel("Status");
        yAxis.setLabel("Value");

        XYChart.Series lostColumn = new XYChart.Series();
        lostColumn.setName("Lost");
        lostColumn.getData().add(new XYChart.Data(january, totalLost[0]));
        lostColumn.getData().add(new XYChart.Data(february, totalLost[1]));
        lostColumn.getData().add(new XYChart.Data(march, totalLost[2]));
        lostColumn.getData().add(new XYChart.Data(april, totalLost[3]));
        lostColumn.getData().add(new XYChart.Data(may, totalLost[4]));
        lostColumn.getData().add(new XYChart.Data(june, totalLost[5]));
        lostColumn.getData().add(new XYChart.Data(july, totalLost[6]));
        lostColumn.getData().add(new XYChart.Data(august, totalLost[7]));
        lostColumn.getData().add(new XYChart.Data(september, totalLost[8]));
        lostColumn.getData().add(new XYChart.Data(october, totalLost[9]));
        lostColumn.getData().add(new XYChart.Data(november, totalLost[10]));
        lostColumn.getData().add(new XYChart.Data(december, totalLost[11]));

        XYChart.Series matchedColumn = new XYChart.Series();
        matchedColumn.setName("Matched");
        matchedColumn.getData().add(new XYChart.Data(january, totalMatched[0]));
        matchedColumn.getData().add(new XYChart.Data(february, totalMatched[1]));
        matchedColumn.getData().add(new XYChart.Data(march, totalMatched[2]));
        matchedColumn.getData().add(new XYChart.Data(april, totalMatched[3]));
        matchedColumn.getData().add(new XYChart.Data(may, totalMatched[4]));
        matchedColumn.getData().add(new XYChart.Data(june, totalMatched[5]));
        matchedColumn.getData().add(new XYChart.Data(july, totalMatched[6]));
        matchedColumn.getData().add(new XYChart.Data(august, totalMatched[7]));
        matchedColumn.getData().add(new XYChart.Data(september, totalMatched[8]));
        matchedColumn.getData().add(new XYChart.Data(october, totalMatched[9]));
        matchedColumn.getData().add(new XYChart.Data(november, totalMatched[10]));
        matchedColumn.getData().add(new XYChart.Data(december, totalMatched[11]));

            bc.setMinSize(1200, 800);  //grote aanpassen

            pane.setAlignment(Pos.CENTER);
            pane.getChildren().addAll(bc);
            bc.getData().addAll(lostColumn, matchedColumn);
            
        }  catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }

        return pane;
    }

}
