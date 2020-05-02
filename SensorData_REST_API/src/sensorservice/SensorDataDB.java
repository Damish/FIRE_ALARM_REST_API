package sensorservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DbManager;

public class SensorDataDB {

	private static ArrayList<SensorData> SensorDB = new ArrayList<>();

	// static {
	// SensorDB.add(new SensorData("sensor1", 0, 0, 1, 1));
	// SensorDB.add(new SensorData("sensor2", 0, 0, 1, 2));
	// SensorDB.add(new SensorData("sensor3", 0, 0, 2, 1));
	// SensorDB.add(new SensorData("sensor4", 0, 0, 2, 2));
	// SensorDB.add(new SensorData("sensor5", 0, 0, 2, 3));
	// SensorDB.add(new SensorData("sensor6", 0, 0, 3, 1));
	// SensorDB.add(new SensorData("sensor7", 0, 0, 3, 2));
	// SensorDB.add(new SensorData("sensor8", 0, 0, 3, 3));
	// }

	static DbManager objDbM = new DbManager();

	static Connection conn = objDbM.getConnection();

	public static ArrayList<SensorData> getSensorData() {

		ArrayList<SensorData> SensorDB_temp = new ArrayList<>(); // this arraylist is only for use in this method.

		try {

			// System.out.println("Http GET action called!!! ");
			// System.out.println("Fetching data from Database...");

			String sql = "SELECT * FROM sdata";

			Statement statement;
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				String sid = result.getString(1);
				String co2 = result.getString(2);
				String smoke = result.getString(3);
				String floor = result.getString(4);
				String room = result.getString(5);

				SensorDB_temp.add(new SensorData(sid, Integer.parseInt(co2), Integer.parseInt(smoke),
						Integer.parseInt(floor), Integer.parseInt(room)));
				// System.out.println("Data from database >> sid:" + sid + "
				// co2: " + co2 + " smoke: " + smoke
				// + " floor: " + floor + " room: " + room);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SensorDB_temp;

	}

	public static void addSensorData(SensorData sData) {

		try {

			String sql = "INSERT INTO sdata (sid, co2Level, smokeLevel, floorNo,roomNo) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement statement;

			statement = conn.prepareStatement(sql);

			statement.setString(1, sData.getSid());
			statement.setInt(2, sData.getCo2Level());
			statement.setInt(3, sData.getSmokeLevel());
			statement.setInt(4, sData.getFloorNo());
			statement.setInt(5, sData.getRoomNo());

			int rowsInserted = statement.executeUpdate();

			if (rowsInserted > 0) {
				System.out.println("A new sensor was inserted successfully! Added sensor id : "+  sData.getSid() );
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void updateSensorData(String usid, SensorData sData) {

		// SensorDB.set(index, sData);

		try {

			String sql = "UPDATE sdata SET co2Level=?, smokeLevel=?, floorNo=?,roomNo=? WHERE sid=?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, sData.getCo2Level());
			statement.setInt(2, sData.getSmokeLevel());
			statement.setInt(3, sData.getFloorNo());
			statement.setInt(4, sData.getRoomNo());
			statement.setString(5, usid);

			// System.out.println("statement : " + statement);

			int rowsUpdated = statement.executeUpdate();

			if (rowsUpdated > 0) {
//				System.out.println("An existing sensor was updated successfully!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void removeSensorData(String dsid) {

		try {

			String sql = "DELETE FROM sdata WHERE sid=?";

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, dsid);

			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("A sensor was deleted successfully! Deleted sensor id : "+  dsid);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
