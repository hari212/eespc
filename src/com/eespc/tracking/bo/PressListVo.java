package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class PressListVo extends PrintingPressVo {

	public PressListVo() {
		buildingName = null;
	}

	public PressListVo(ResultSet resultset) throws SQLException {
		id = UtilityObject.getIntFromString(resultset.getString("PRESSID"));
		buildingName = resultset.getString("BUILDINGNAME");
		facilitydesinatedId = resultset.getString("FACILITYDESIGNATEDID");

	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	protected String buildingName;
}