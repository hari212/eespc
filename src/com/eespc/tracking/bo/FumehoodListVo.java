package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            FumehoodVo

public class FumehoodListVo extends FumehoodVo {

	protected String buildingName;

	public FumehoodListVo() {
		buildingName = null;
	}

	public FumehoodListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("FUMEHOODID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		floor = resultset.getString("FLOOR");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}
}
