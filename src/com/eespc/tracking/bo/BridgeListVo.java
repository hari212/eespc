package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            BridgeTunnelsVo

public class BridgeListVo extends BridgeTunnelsVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String buildingName;
	protected String bridge;

	public BridgeListVo() {
		buildingName = null;
	}

	public BridgeListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		bridge = null;
		buildingName = resultset.getString("BUILDINGNAME");
		id = resultset.getInt("BRIDGETUNNELID");
		facilityDesignatedId = resultset.getString("FACILITYDesignatedID");
		yearinstalled = resultset.getString("YEARINSTALLED");
		bridge = resultset.getString("isbridge");
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
}
