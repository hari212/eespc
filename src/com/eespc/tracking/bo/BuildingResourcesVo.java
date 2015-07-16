package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;

public class BuildingResourcesVo implements Serializable {

	public BuildingResourcesVo() {
		entityId = -99;
		entityType = -99;
		entityTypeName = null;
		name = null;
		bname = null;
	}

	public BuildingResourcesVo(ResultSet resultset) throws SQLException {
		entityId = -99;
		entityType = -99;
		entityTypeName = null;
		name = null;
		entityId = resultset.getInt("entityid");
		entityType = resultset.getInt("entitytype");
		name = resultset.getString("name");
		bname = resultset.getString("bname");
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int i) {
		entityId = i;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int i) {
		entityType = i;
	}

	public String getEntityTypeName() {
		ResourcesTypeEnum resourcestypeenum = ResourcesTypeEnum
				.get(getEntityType());
		return resourcestypeenum != null ? resourcestypeenum.getName() : "";
	}

	public void setEntityTypeName(String s) {
		entityTypeName = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name = s;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String s) {
		bname = s;
	}

	protected int entityId;
	protected int entityType;
	protected String entityTypeName;
	protected String name;
	protected String bname;
}