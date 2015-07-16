package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChConsumptionVo {

	public ChConsumptionVo() {
		id = -99;
		objectId = -99;
		year = null;
		consumption = null;
		consumptioncfy = null;
	}

	public ChConsumptionVo(ResultSet resultset) throws SQLException {
		id = -99;
		objectId = -99;
		year = null;
		consumption = null;
		consumptioncfy = null;
		id = resultset.getInt("CONS_ID");
		objectId = resultset.getInt("object_id");
		year = resultset.getString("YEAR");
		consumption = resultset.getString("CONSUMPTION");
		consumptioncfy = resultset.getString("CONSUMPTIONCFY");
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

	public void setConsumptioncfy(String s) {
		consumptioncfy = s;
	}

	public String getConsumptioncfy() {
		return consumptioncfy;
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

	protected int id;
	protected int objectId;
	protected String year;
	protected String consumption;
	protected String consumptioncfy;
}