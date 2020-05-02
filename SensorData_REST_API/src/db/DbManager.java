package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbManager {


	public Connection getConnection() {

		String dbURL = "jdbc:mysql://localhost:3306/sensorDB";
		String username = "root";
		String password = "sliitpc16$97";

		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, username, password);
			
			if (conn == null) {
				System.out.println("Failed connection");
			} else {
				System.out.println("Successfull connection");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return conn;

	}

	

}
