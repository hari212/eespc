package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            PaintSprayVo

public class PaintListVo extends PaintSprayVo {

	protected String buildingName;

	public PaintListVo() {
		buildingName = null;
	}

	public PaintListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("SPRAYBOOTHID");
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
