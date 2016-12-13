package fys_main;

import java.sql.*;

/**
 *
 * @author Menno
 */
public class Database {
    
    /* Static connection variable */
    private static Connection conn = null;
    
    public static void setConn() {
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://149.210.233.73:3306/menno_fys", "menno_fys", "FYSapp123");
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Check connection
            try {if(conn==null) {
                     conn.close();
                 }
            } catch(SQLException se) {
                 se.printStackTrace();
            }
        }
    }
        
    public ResultSet getQuery(String query) {
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Create query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch(Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        } finally {
            // Check statement
            try {
               if (stmt==null) {
                   stmt.close();
               }
            } catch(SQLException se2) {
                // Kapot
            }
        }
        
        return rs;
    }
    
    public void setQuery(String query) {
        Statement stmt = null;
        
        try {
            // Create query
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch(Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        } finally {
            // Check statement
            try {
               if (stmt==null) {
                   stmt.close();
               }
            } catch(SQLException se2) {
                // Kapot
            }
        }     
    }
    
    /*
    Zo kun je de resultaten verwerken van doQuery("QUERY");
    
    while(rs.next()){
        //Retrieve by column name
        int id  = rs.getInt("id");
        int cijfer = rs.getInt("cijfer");
        int datum = rs.getInt("datum");

        //Display values
        System.out.println("ID: " + id);
        System.out.println("Cijfer: " + cijfer);
        System.out.println("Datum: " + datum);
     }
    
    
    Database hoi = new Database();
    ResultSet result = hoi.doQuery("SELECT * FROM iets");
    try {
        while(result.next()) {

        }
    } catch(SQLException se) {
       //Handle errors for JDBC
       se.printStackTrace();
    }
    */
    
    
    
    /*
    Database test = new Database();
    test.setConn();
    test.setQuery("QUERY");
    */
    
}