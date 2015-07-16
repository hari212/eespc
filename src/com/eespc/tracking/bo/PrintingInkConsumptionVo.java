package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class PrintingInkConsumptionVo implements Serializable {

	protected int id;
	protected int pressId;
	protected String year;
	protected String annualInkConsump;
	protected String dailyInkConsump;
	protected String monthlyInkConsump;

	public PrintingInkConsumptionVo() {
		id = -99;
		pressId = -99;
		year = null;
		annualInkConsump = null;
		dailyInkConsump = null;
		monthlyInkConsump = null;
	}

	public PrintingInkConsumptionVo(ResultSet resultset) throws SQLException {
		id = -99;
		pressId = -99;
		year = null;
		annualInkConsump = null;
		dailyInkConsump = null;
		monthlyInkConsump = null;
		id = UtilityObject.getIntFromString(resultset
				.getString("PRTINKONECONSID"));
		pressId = UtilityObject
				.getIntFromString(resultset.getString("PRESSID"));
		year = resultset.getString("YEAR");
		annualInkConsump = resultset.getString("ANNUALINKCONSUMP");
		dailyInkConsump = resultset.getString("DAILYINKCONSUMP");
		monthlyInkConsump = resultset.getString("MONTHLYINKCONSUMP");
	}

	public String getAnnualInkConsump() {
		return annualInkConsump;
	}

	public void setAnnualInkConsump(String s) {
		annualInkConsump = s;
	}

	public int getPressId() {
		return pressId;
	}

	public void setPressId(int i) {
		pressId = i;
	}

	public String getDailyInkConsump() {
		return dailyInkConsump;
	}

	public void setDailyInkConsump(String s) {
		dailyInkConsump = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getMonthlyInkConsump() {
		return monthlyInkConsump;
	}

	public void setMonthlyInkConsump(String s) {
		monthlyInkConsump = s;
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
		stringbuffer.append("pressId=").append(pressId).append(s);
		stringbuffer.append("year=").append(year).append(s);
		stringbuffer.append("annualInkConsump=").append(annualInkConsump)
				.append(s);
		stringbuffer.append("dailyInkConsump=").append(dailyInkConsump)
				.append(s);
		stringbuffer.append("monthlyInkConsump=").append(monthlyInkConsump);
		return stringbuffer.toString();
	}
}
