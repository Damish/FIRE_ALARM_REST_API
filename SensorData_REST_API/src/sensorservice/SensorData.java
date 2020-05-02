package sensorservice;

public class SensorData {

	private String sid;
	private int co2Level;
	private int smokeLevel;
	private int floorNo;
	private int roomNo;
	
	public SensorData() {
		
	}

	public SensorData(String sid ,int co2Level, int smokeLevel, int floorNo, int roomNo) {
		super();
		this.sid =sid;
		this.co2Level = co2Level;
		this.smokeLevel = smokeLevel;
		this.floorNo = floorNo;
		this.roomNo = roomNo;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public int getCo2Level() {
		return co2Level;
	}

	public void setCo2Level(int co2Level) {
		this.co2Level = co2Level;
	}

	public int getSmokeLevel() {
		return smokeLevel;
	}

	public void setSmokeLevel(int smokeLevel) {
		this.smokeLevel = smokeLevel;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	
	
}
