package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            CogenTurbineVo

public class CogenTurbineListVo extends CogenTurbineVo {

	protected String buildingName;

	public CogenTurbineListVo() {
		buildingName = null;
	}

	public CogenTurbineListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("COGENTURBINEID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		floor = resultset.getString("FLOOR");
		serialNumber = resultset.getString("SERIALNUMBER");
		capacity = resultset.getString("CAPACITY");
		resourceType = resultset.getInt("RESOURCETYPE");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}
}
