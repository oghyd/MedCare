/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

/**
 *
 * @author idber
 */
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
	// constructeur vide
	Connection(){		
	}
	
	public static java.sql.Connection connect() throws SQLException{
		java.sql.Connection conn = null;
		try{
                    conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tp7?" +
                            "user=root&password=");
		} 
		catch (SQLException e) {
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		return conn;
	}
}