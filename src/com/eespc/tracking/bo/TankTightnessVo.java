package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class TankTightnessVo {

	protected int tankId;
	protected int id;
	protected Date testDate;
	protected Date nextTestDate;
	protected String nextTestDateNote;
	protected String testDate4Display;
	protected String nextTestDate4Display;

	public TankTightnessVo() {
		tankId = -99;
		id = -99;
		testDate = null;
		nextTestDate = null;
		nextTestDateNote = null;
		testDate4Display = null;
		nextTestDate4Display = null;
	}

	public TankTightnessVo(ResultSet resultset) throws SQLException {
		tankId = -99;
		id = -99;
		testDate = null;
		nextTestDate = null;
		nextTestDateNote = null;
		testDate4Display = null;
		nextTestDate4Display = null;
		id = resultset.getInt("TANKTIGHTNESSID");
		tankId = resultset.getInt("STORAGETANKID");
		testDate = resultset.getDate("TESTDATE");
		nextTestDate = resultset.getDate("NEXTTESTDATE");
		nextTestDateNote = resultset.getString("NEXTTESTDATENOTE");
	}

	public int getTankId() {
		return tankId;
	}

	public void setTankId(int i) {
		tankId = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getNextTestDateNote() {
		return nextTestDateNote;
	}

	public void setNextTestDateNote(String s) {
		nextTestDateNote = s;
	}

	public Date getNextTestDate() {
		return nextTestDate;
	}

	public void setNextTestDate(Date date) {
		nextTestDate = date;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date date) {
		testDate = date;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id).append("|");
		stringbuffer.append("tankId =").append(tankId).append("|");
		stringbuffer.append("testDate =").append(testDate).append("|");
		stringbuffer.append("nextTestDate =").append(nextTestDate).append("|");
		stringbuffer.append("nextTestDateNote =").append(nextTestDateNote);
		return stringbuffer.toString();
	}

	public String getNextTestDate4Display() {
		return UtilityObject.convertToString(nextTestDate);
	}

	public void setNextTestDate4Display(String s) {
		nextTestDate4Display = s;
	}

	public String getTestDate4Display() {
		return UtilityObject.convertToString(testDate);
	}

	public void setTestDate4Display(String s) {
		testDate4Display = s;
	}
}
