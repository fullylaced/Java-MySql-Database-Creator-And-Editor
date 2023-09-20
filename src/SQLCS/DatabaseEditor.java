/**
 * DatabaseEditor - a Java program for connecting to a MySQL server
 * and creating a new database.
 *
 * Author: Avram Parra
 */

package SQLCS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseEditor {
	static String NO_LINK = "\"N/A\"";
	static String LOCALHOST_STRING = "localhost:3306/?useSSL=false";
	
    public static void main(String[] args) {
        // Initialize variables for database connection and statement
        Connection con = null;
        Statement stmt = null;
        
        // Specify the name of the database you want to create
        String yourDatabaseName = "Test_Database";
        
        try {
        	Scanner scanner = new Scanner(System.in);
        	System.out.println("Enter the MySQL Server Link (or type " + NO_LINK + " or leave entry blank to reach localhost):");
        	String linkEntry = scanner.nextLine();
        	if (linkEntry.equals("N/A") || linkEntry.isEmpty()) {
        		con = DriverManager.getConnection(("jdbc:mysql://" + LOCALHOST_STRING), "root", "");
        	}else {
        		con = DriverManager.getConnection(("jdbc:mysql://" + linkEntry), "root", "");
        	}
        	scanner.close();
            // Establish a connection to the MySQL server
            
            // Create a statement object to execute SQL commands
            stmt = con.createStatement();
            
            // Execute an SQL command to create a new database with the specified name
            int status = stmt.executeUpdate("CREATE DATABASE " + yourDatabaseName);
            
            // Check if the database creation was successful
            if (status > 0) {
                System.out.println("Database is created successfully !!!");
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during database creation
            e.printStackTrace();
        } finally {
            try {
                // Close the statement and the database connection
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                // Handle any exceptions that may occur while closing resources
                e.printStackTrace();
            }
        }
    }
}