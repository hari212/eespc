package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoilerListVo extends BoilerVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BoilerListVo() {
		buildingName = null;
	}

	public BoilerListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("BOILERID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		floor = resultset.getString("FLOOR");
		serial = resultset.getString("SERIAL");
		capacity = resultset.getString("CAPACITY");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	protected String buildingName;
}