// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 2:28:09 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ElevatorListVo.java

package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            ElevatorVo

public class ElevatorListVo extends ElevatorVo {

	public ElevatorListVo() {
		buildingName = null;
	}

	public ElevatorListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		bridge = null;
		buildingName = resultset.getString("BUILDINGNAME");
		elevatorId = resultset.getInt("ELEVATORID");
		facilityDesignatedId = resultset.getString("FACILITYDesignatedID");
		eleType = resultset.getString("ELETYPE");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	public String getBridge() {
		return bridge;
	}

	public void setBridge(String s) {
		bridge = s;
	}

	protected String buildingName;
	protected String bridge;
}