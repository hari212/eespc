// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 2:28:18 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EtoListVo.java

package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            EtoVo

public class EtoListVo extends EtoVo {

	public EtoListVo() {
		buildingName = null;
	}

	public EtoListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		etoId = resultset.getInt("ETOID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		model = resultset.getString("MODEL");
		serial = resultset.getString("SERIAL");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	protected String buildingName;
}