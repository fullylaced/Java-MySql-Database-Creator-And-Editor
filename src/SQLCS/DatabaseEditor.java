/**
 * DatabaseEditor - a Java program for connecting to a MySQL server
 * and creating a new database.
 *
 * Author: Avram Parra
 */

package SQLCS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseEditor {
    // Constants for default values
    static String NO_LINK = "\"N/A\"";
    static String LOCALHOST_STRING = "localhost:3306/?useSSL=false";

    // Method to establish a database connection
    public static Connection connect() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the MySQL Server Link (or type " + NO_LINK + " or leave entry blank to reach localhost):");
        String linkEntry = scanner.nextLine();
        Connection con = null;
        if (linkEntry.equals("N/A") || linkEntry.isEmpty()) {
            // Connect to localhost if no link is provided
            con = DriverManager.getConnection(("jdbc:mysql://" + LOCALHOST_STRING), "root", "");
        } else {
            // Connect to the provided link
            con = DriverManager.getConnection(("jdbc:mysql://" + linkEntry), "root", "");
        }
        return con;
    }

    // Method to create a new database
    public static int createDatabase(Statement stmt) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like the name of the Database to be?: ");
        String nameEntry = scanner.nextLine();

        return stmt.executeUpdate("CREATE DATABASE " + nameEntry);
    }

    public static void main(String[] args) {
        // Initialize variables for database connection and statement
        Connection con = null;
        Statement stmt = null;

        try {
            int status = 0;
            con = connect();
            System.out.println("Connected to MySQL Server!");

            // Create a statement object to execute SQL commands
            stmt = con.createStatement();
            System.out.println("What would you like to do within the SQL Database?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter 1 to Create a Database");
            String choiceEntry = scanner.nextLine();

            if (choiceEntry.equals("1")) {
                // Call the createDatabase method if the user chooses to create a database
                status = createDatabase(stmt);
            }

            // Check if the database creation was successful
            if (status > 0) {
                System.out.println("Database is created successfully !!!");
            }
            scanner.close();
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