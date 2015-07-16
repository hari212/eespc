package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class OtherListVo extends OtherSourceVo {

	public OtherListVo() {
		buildingName = null;
	}

	public OtherListVo(ResultSet resultset) throws SQLException {
		id = UtilityObject.getIntFromString(resultset.getString("OTHERSID"));
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