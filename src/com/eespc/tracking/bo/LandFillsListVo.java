package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            LandfillVo

public class LandFillsListVo extends LandfillVo {

	protected String buildingName;

	public LandFillsListVo() {
		buildingName = null;
	}

	public LandFillsListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("LANDFILLSID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}
}
