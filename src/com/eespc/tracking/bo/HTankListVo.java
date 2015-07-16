package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            HydraulicElevatorAndOtherTankVo

public class HTankListVo extends HydraulicElevatorAndOtherTankVo {

	protected String buildingName;

	public HTankListVo() {
		buildingName = null;
	}

	public HTankListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("otherTankId");
		facilityDesignatedId = resultset.getString("FACILITYDesignatedID");
		capacity = resultset.getInt("capacity");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}
}
