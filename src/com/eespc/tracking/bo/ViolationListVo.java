// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 2:28:09 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ElevatorListVo.java

package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            ViolationVo

public class ViolationListVo extends ViolationVo {

	public ViolationListVo() {
		buildingName = null;
	}

	public ViolationListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		// bridge = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("violationid");
		violationno = resultset.getString("violationno");
		violationWhich = resultset.getInt("violationWhich");
		violationType = resultset.getString("violationType");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	/*
	 * public String getBridge() { return bridge; }
	 * 
	 * public void setBridge(String s) { bridge = s; }
	 */
	protected String buildingName;
	// protected String bridge;
}