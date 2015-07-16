package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RPZListVo extends RpzVo {

	public RPZListVo() {
		buildingName = null;
	}

	public RPZListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("RPZID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		serialNumber = resultset.getString("SERIALNUMBER");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	protected String buildingName;
}