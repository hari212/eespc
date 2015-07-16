package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class BoilerFuelConsumptionVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoilerFuelConsumptionVo() {
		id = -99;
		boilerId = -99;
		year = null;
		annualConsump = null;
		dailyConsump = null;
		monthlyConsump = null;
	}

	public BoilerFuelConsumptionVo(ResultSet resultset) throws SQLException {
		id = -99;
		boilerId = -99;
		year = null;
		annualConsump = null;
		dailyConsump = null;
		monthlyConsump = null;
		id = UtilityObject.getIntFromString(resultset
				.getString("BLRANNFUELCONSID"));
		boilerId = UtilityObject.getIntFromString(resultset
				.getString("BOILERID"));
		year = resultset.getString("YEAR");
		annualConsump = resultset.getString("ANNUALCONSUMP");
		dailyConsump = resultset.getString("DAILYCONSUMP");
		monthlyConsump = resultset.getString("MONTHLYCONSUMP");
	}

	public String getAnnualConsump() {
		return annualConsump;
	}

	public void setAnnualConsump(String s) {
		annualConsump = s;
	}

	public int getBoilerId() {
		return boilerId;
	}

	public void setBoilerId(int i) {
		boilerId = i;
	}

	public String getDailyConsump() {
		return dailyConsump;
	}

	public void setDailyConsump(String s) {
		dailyConsump = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getMonthlyConsump() {
		return monthlyConsump;
	}

	public void setMonthlyConsump(String s) {
		monthlyConsump = s;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String s) {
		year = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ", ";
		stringbuffer.append("id=").append(id).append(s);
		stringbuffer.append("boilerId=").append(boilerId).append(s);
		stringbuffer.append("year=").append(year).append(s);
		stringbuffer.append("annualConsump=").append(annualConsump).append(s);
		stringbuffer.append("dailyConsump=").append(dailyConsump).append(s);
		stringbuffer.append("monthlyConsump=").append(monthlyConsump);
		return stringbuffer.toString();
	}

	protected int id;
	protected int boilerId;
	protected String year;
	protected String annualConsump;
	protected String dailyConsump;
	protected String monthlyConsump;
}