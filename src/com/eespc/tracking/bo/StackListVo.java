package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            StackVo

public class StackListVo extends StackVo {

	protected String buildingName;

	public StackListVo() {
		buildingName = null;
	}

	public StackListVo(ResultSet resultset) throws SQLException {
		buildingName = null;
		stackId = resultset.getInt("STACKID");
		decEmissionPointId = resultset.getString("DECEMISSIONPOINTID");
		facilityStackId = resultset.getString("FACILITYSTACKID");
		height = resultset.getDouble("HEIGHT");
		diameter = resultset.getDouble("DIAMETER");
		buildingName = resultset.getString("BUILDINGNAME");
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("stackId=").append(stackId).append(",");
		stringbuffer.append("decEmissionPointId=").append(decEmissionPointId)
				.append(",");
		stringbuffer.append("facilityStackId=").append(facilityStackId)
				.append(",");
		stringbuffer.append("height=").append(height).append(",");
		stringbuffer.append("diameter=").append(diameter).append(",");
		stringbuffer.append("buildingName=").append(buildingName);
		return stringbuffer.toString();
	}
}
