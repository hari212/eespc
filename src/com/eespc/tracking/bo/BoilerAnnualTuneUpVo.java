package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class BoilerAnnualTuneUpVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoilerAnnualTuneUpVo() {
		id = -99;
		boilerId = -99;
		year = null;
		date = null;
		performedBy = null;
		isRecordsKept = false;
		dateForDisplay = null;
	}

	public BoilerAnnualTuneUpVo(ResultSet resultset) throws SQLException {
		id = -99;
		boilerId = -99;
		year = null;
		date = null;
		performedBy = null;
		isRecordsKept = false;
		dateForDisplay = null;
		id = UtilityObject.getIntFromString(resultset
				.getString("ANNUALTUNEUPID"));
		boilerId = UtilityObject.getIntFromString(resultset
				.getString("BOILERID"));
		year = resultset.getString("YEAR");
		date = resultset.getDate("DATEPERFORMED");
		dateForDisplay = resultset.getDate("DATEPERFORMED");
		performedBy = resultset.getString("PERFORMEDBY");
		isRecordsKept = UtilityObject.convertBoolean(resultset
				.getString("ISRECORDSKEPT"));
	}

	public int getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(int i) {
		boilerId = i;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date s) {
		date = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isRecordsKept() {
		return isRecordsKept;
	}

	public void setRecordsKept(boolean flag) {
		isRecordsKept = flag;
	}

	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String s) {
		performedBy = s;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String s) {
		year = s;
	}

	public String getDateForDisplay() {
		if (dateForDisplay == null)
			return "";
		else
			return UtilityObject.convertToString(dateForDisplay);
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ", ";
		stringbuffer.append("id =").append(id).append(s);
		stringbuffer.append("boilerId =").append(boilerId).append(s);
		stringbuffer.append("year     =").append(year).append(s);
		stringbuffer.append("date     =").append(date).append(s);
		stringbuffer.append("date     =").append(getDateForDisplay()).append(s);
		stringbuffer.append("performedBy =").append(performedBy).append(s);
		stringbuffer.append("isRecordsKept =").append(isRecordsKept);
		return stringbuffer.toString();
	}

	protected int id;
	protected int boilerId;
	protected String year;
	protected Date date;
	protected String performedBy;
	protected boolean isRecordsKept;
	protected Date dateForDisplay;
}