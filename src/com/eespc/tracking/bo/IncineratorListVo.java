package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            IncineratorVo

public class IncineratorListVo extends IncineratorVo {

	protected String buildingName;

	public IncineratorListVo() {
		buildingName = null;
	}

	public IncineratorListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("INCINERATORCREMATORIESID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		floor = resultset.getString("FLOOR");
		serialNo = resultset.getString("SERIAL");
		capacity = resultset.getString("CAPACITY");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}
}
