package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            BulkOxygenTankVo

public class BulkOxygenListVo extends BulkOxygenTankVo {

	protected String buildingName;

	public BulkOxygenListVo() {
		buildingName = null;
	}

	public BulkOxygenListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("bulkoxygentankid");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}
}
