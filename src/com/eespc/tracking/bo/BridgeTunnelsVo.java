package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.BridgeTunnelsEntity;
import com.eespc.tracking.util.UtilityObject;

public class BridgeTunnelsVo implements Serializable {

	public BridgeTunnelsVo() {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		actiontaken = null;
		isBridge = false;
		permitInfo = null;
		disconnectedyear = null;
		modifiedinpast = -99;
		yearinstalled = null;
		bcomments = null;
		issueDate = null;
		permitNumber = null;
		dobsignoff = null;
	}

	public BridgeTunnelsVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		isBridge = false;
		permitInfo = null;
		actiontaken = null;
		modifiedinpast = -99;
		yearinstalled = null;
		disconnectedyear = null;
		bcomments = null;
		issueDate = null;
		permitNumber = null;
		dobsignoff = null;

		id = UtilityObject.getIntFromString(resultset
				.getString("BRIDGETUNNELID"));
		buildingId = UtilityObject.getIntFromString(resultset
				.getString("BUILDINGID"));
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		actiontaken = resultset.getString("ACTIONTAKEN");
		isBridge = UtilityObject
				.convertBoolean(resultset.getString("ISBRIDGE"));
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		yearinstalled = resultset.getString("YEARINSTALLED");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		bcomments = resultset.getString("BCOMMENTS");
		issueDate = resultset.getString("ISSUEDATE");
		permitNumber = resultset.getString("PERMITNUMBER");
		dobsignoff = resultset.getString("DOBSIGNOFF");
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String i) {
		issueDate = i;
	}

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String i) {
		permitNumber = i;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getBcomments() {
		return bcomments;
	}

	public void setBcomments(String i) {
		bcomments = i;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setActionTaken(String s) {
		actiontaken = s;
	}

	public String getActionTaken() {
		return actiontaken;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getYearInstalled() {
		return yearinstalled;
	}

	public void setYearInstalled(String s) {
		yearinstalled = s;
	}

	public String getDisconnectedYear() {
		return disconnectedyear;
	}

	public void setDisconnectedYear(String s) {
		disconnectedyear = s;
	}

	public int getModifiedInPast() {
		return modifiedinpast;
	}

	public void setModifiedInPast(int i) {
		modifiedinpast = i;
	}

	public boolean isBridge() {
		return isBridge;
	}

	public void setBridge(boolean flag) {
		isBridge = flag;
	}

	public List getPermitInfo() {
		if (permitInfo == null)
			try {
				permitInfo = BridgeTunnelsEntity.getPermitList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return permitInfo;
	}

	public void setPermitInfo(List list) {
		permitInfo = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ", ";
		stringbuffer.append("id   =").append(id).append(s);
		stringbuffer.append("buildingId   =").append(buildingId).append(s);
		stringbuffer.append("facilityDesignatedId     =")
				.append(facilityDesignatedId).append(s);
		stringbuffer.append("actiontaken=").append(actiontaken).append(s);
		stringbuffer.append("disconnectedyear=").append(disconnectedyear)
				.append(s);
		stringbuffer.append("modifiedinpast=").append(modifiedinpast).append(s);
		stringbuffer.append("yearinstalled=").append(yearinstalled).append(s);
		stringbuffer.append("isBridge     =").append(isBridge);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.BridgeTunnelsVo.class);
	protected int id;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected String actiontaken;
	protected boolean isBridge;
	protected List permitInfo;
	protected String disconnectedyear;
	protected String yearinstalled;
	protected String bcomments;
	protected String issueDate;
	protected String permitNumber;
	protected int modifiedinpast;
	protected String dobsignoff;

}