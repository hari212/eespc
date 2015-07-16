package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class TrienialCathodicVo {

	protected int tankId;
	protected int id;
	protected Date lastTestDate;
	protected Date nextTestDueDate;
	protected Date actualTestDate;
	protected String note;

	protected String lastTestDate4Display;
	protected String nextTestDueDate4Display;
	protected String actualTestDate4Display;

	public TrienialCathodicVo() {
		tankId = -99;
		id = -99;
		lastTestDate = null;
		nextTestDueDate = null;
		actualTestDate = null;
		note = null;
		lastTestDate4Display = null;
		nextTestDueDate4Display = null;
		actualTestDate4Display = null;
	}

	public TrienialCathodicVo(ResultSet resultset) throws SQLException {
		tankId = -99;
		id = -99;
		lastTestDate = null;
		nextTestDueDate = null;
		actualTestDate = null;
		note = null;
		lastTestDate4Display = null;
		nextTestDueDate4Display = null;
		actualTestDate4Display = null;
		id = resultset.getInt("TRIENIALTESTID");
		tankId = resultset.getInt("STORAGETANKID");
		lastTestDate = resultset.getDate("LASTTESTDATE");
		nextTestDueDate = resultset.getDate("NEXTTESTDUEDATE");
		actualTestDate = resultset.getDate("ACTUALTESTDATE");
		note = resultset.getString("NOTE");
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

	public String getNote() {
		return note;
	}

	public void setNote(String s) {
		note = s;
	}

	public Date getLastTestDate() {
		return lastTestDate;
	}

	public void setLastTestDate(Date date) {
		lastTestDate = date;
	}

	public Date getNextTestDueDate() {
		return nextTestDueDate;
	}

	public void setNextTestDueDate(Date date) {
		nextTestDueDate = date;
	}

	public Date getActualTestDate() {
		return actualTestDate;
	}

	public void setActualTestDate(Date date) {
		actualTestDate = date;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id).append("|");
		stringbuffer.append("tankId =").append(tankId).append("|");
		stringbuffer.append("lastTestDate =").append(lastTestDate).append("|");
		stringbuffer.append("nextTestDueDate =").append(nextTestDueDate)
				.append("|");
		stringbuffer.append("note =").append(note).append("|");
		stringbuffer.append("actualTestDate =").append(actualTestDate);
		return stringbuffer.toString();
	}

	public String getLastTestDate4Display() {
		return UtilityObject.convertToString(lastTestDate);
	}

	public void setLastTestDate4Display(String s) {
		lastTestDate4Display = s;
	}

	public String getNextTestDueDate4Display() {
		return UtilityObject.convertToString(nextTestDueDate);
	}

	public void setNextTestDueDate4Display(String s) {
		nextTestDueDate4Display = s;
	}

	public String getActualTestDate4Display() {
		return UtilityObject.convertToString(actualTestDate);
	}

	public void setActualTestDate4Display(String s) {
		actualTestDate4Display = s;
	}
}
