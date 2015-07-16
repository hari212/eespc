package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class FireAlarmListVo extends FireAlarmVo {

	public FireAlarmListVo() {
		buildingName = null;
	}

	public FireAlarmListVo(ResultSet resultset) throws SQLException {
		id = UtilityObject.getIntFromString(resultset.getString("FIREALARMID"));
		buildingName = resultset.getString("BUILDINGNAME");
		facilitydesignatedid = resultset.getString("FACILITYDESIGNATEDID");

	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	protected String buildingName;
}