package SQLCS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class databasedemo{
	   public static void main(String[] args) {
		      Connection con=null;
		      Statement stmt=null;
		      String yourDatabaseName="watt_Database2";
		      try {
		         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/yurr?useSSL=false",
		         "root","");
		         stmt = con.createStatement();
		         int status = stmt.executeUpdate("CREATE DATABASE "+yourDatabaseName);
		         if(status > 0) {
		            System.out.println("Database is created successfully !!!");
		         }
		      }
		      catch(Exception e) {
		         e.printStackTrace();
		      }
	   }
}