package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class PlaceofAssemblyListVo extends PlaceofAssemblyVo {

	public PlaceofAssemblyListVo() {
		buildingName = null;
	}

	public PlaceofAssemblyListVo(ResultSet resultset) throws SQLException {
		id = UtilityObject.getIntFromString(resultset
				.getString("PLACEOFASSEMBLYID"));
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