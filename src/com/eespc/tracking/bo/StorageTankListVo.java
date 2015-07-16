package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageTankListVo extends StorageTankVo {

	public StorageTankListVo() {
		buildingName = null;
	}

	public StorageTankListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		id = resultset.getInt("storagetankid");
		pbsNumber = resultset.getString("pbsnumber");
		facilityDesignatedId = resultset.getString("facilitydesignatedid");
		capacity = resultset.getString("capacity");
		buildingName = resultset.getString("BUILDINGNAME");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	protected String buildingName;
}