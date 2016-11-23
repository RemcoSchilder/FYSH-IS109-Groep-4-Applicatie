package fys_main;

import java.sql.*;

/**
 *
 * @author Menno
 */
public class Database {
    
    /* Static connection variable */
    static Connection conn = null;
    
    public void Database() {     
        try {
           // Register JDBC driver
           Class.forName("com.mysql.jdbc.Driver");

           // Open a connection
           conn = DriverManager.getConnection("jdbc:mysql://localhost/sys", "root", "menno");
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch(Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        } finally {
           // Check connection
           try {
                if(conn!=null) {
                    conn.close();
                }
           } catch(SQLException se) {
                se.printStackTrace();
           }
        }
    }
        
    public ResultSet doQuery(String query) {
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Create query
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            // Close statement
            rs.close();
            stmt.close();
        } catch(SQLException se) {
           //Handle errors for JDBC
           se.printStackTrace();
        } catch(Exception e) {
           //Handle errors for Class.forName
           e.printStackTrace();
        } finally {
            // Check statement
            try {
               if (stmt!=null) {
                   stmt.close();
               }
            } catch(SQLException se2) {
                // Kapot
            }
        }
        
        return rs;
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
    */
    
}
