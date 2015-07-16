package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IncConsumptionVo {

	protected int id;
	protected int objectId;
	protected String year;
	protected String consumption;

	public IncConsumptionVo() {
		id = -99;
		objectId = -99;
		year = null;
		consumption = null;
	}

	public IncConsumptionVo(ResultSet resultset) throws SQLException {
		id = -99;
		objectId = -99;
		year = null;
		consumption = null;
		id = resultset.getInt("CONS_ID");
		objectId = resultset.getInt("OBJECT_ID");
		year = resultset.getString("YEAR");
		consumption = resultset.getString("CONSUMPTION");
	}

	public String getYear() {
		return year;
	}

	public void setYear(String s) {
		year = s;
	}

	public void setConsumption(String s) {
		consumption = s;
	}

	public String getConsumption() {
		return consumption;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int i) {
		objectId = i;
	}
}
