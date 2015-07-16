package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.BulkOxygenTankEntity;
import com.eespc.tracking.exceptions.TrackingException;

public class BulkOxygenTankVo {

	public BulkOxygenTankVo() {
		id = -99;
		buildingId = -99;
		fireDeptApproval = null;
		facilityDesignatedId = null;
		dobPermitList = null;
		disconnectedyear = null;
		modifiedinpast = -99;
		yearinstalled = null;
		fireDeptApprovalno = null;
		pressuretest = null;
		lasttestdate = null;
		nexttestdate = null;
		nexttestdatenote = null;
		bcomments = null;
		// dobsignoff=null;
		capacity = null;

	}

	public BulkOxygenTankVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		fireDeptApproval = null;
		facilityDesignatedId = null;
		modifiedinpast = -99;
		yearinstalled = null;
		disconnectedyear = null;
		dobPermitList = null;
		fireDeptApprovalno = null;
		pressuretest = null;
		lasttestdate = null;
		nexttestdate = null;
		nexttestdatenote = null;
		bcomments = null;
		// dobsignoff=null;
		capacity = null;

		id = resultset.getInt("bulkoxygentankid");
		buildingId = resultset.getInt("buildingid");
		/*
		 * String s = resultset.getString("firedeptapproval");
		 * if(UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y"))
		 * fireDeptApproval = true;
		 */
		fireDeptApproval = resultset.getString("firedeptapproval");
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		yearinstalled = resultset.getString("YEARINSTALLED");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		fireDeptApprovalno = resultset.getString("FIREDEPTAPPROVALNO");
		pressuretest = resultset.getString("PRESSURETEST");
		lasttestdate = resultset.getString("LASTTESTDATE");
		nexttestdate = resultset.getString("NEXTTESTDATE");
		nexttestdatenote = resultset.getString("NEXTTESTDATENOTE");
		bcomments = resultset.getString("BCOMMENTS");
		// dobsignoff=resultset.getString("DOBSIGNOFF");
		capacity = resultset.getString("CAPACITY");

	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String s) {
		capacity = s;
	}

	/*
	 * public String getDobsignoff() { return dobsignoff; } public void
	 * setDobsignoff(String s) { dobsignoff= s; }
	 */

	public String getNexttestdatenote() {
		return nexttestdatenote;
	}

	public void setNexttestdatenote(String s) {
		nexttestdatenote = s;
	}

	public String getFireDeptApprovalno() {
		return fireDeptApprovalno;
	}

	public void setFireDeptApprovalno(String s) {
		fireDeptApprovalno = s;
	}

	public String getPressuretest() {
		return pressuretest;
	}

	public void setPressuretest(String s) {
		pressuretest = s;
	}

	public String getLasttestdate() {
		return lasttestdate;
	}

	public void setLasttestdate(String s) {
		lasttestdate = s;
	}

	public String getNexttestdate() {
		return nexttestdate;
	}

	public void setNexttestdate(String s) {
		nexttestdate = s;
	}

	public String getBcomments() {
		return bcomments;
	}

	public void setBcomments(String s) {
		bcomments = s;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String isFireDeptApproval() {
		return fireDeptApproval;
	}

	public void setFireDeptApproval(String flag) {
		fireDeptApproval = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id=").append(id).append("|");
		stringbuffer.append("buildingId=").append(buildingId).append("|");
		stringbuffer.append("fireDeptApproval=").append(fireDeptApproval)
				.append("|");
		stringbuffer.append("disconnectedyear=").append(disconnectedyear)
				.append("|");
		stringbuffer.append("modifiedinpast=").append(modifiedinpast)
				.append("|");
		stringbuffer.append("yearinstalled=").append(yearinstalled).append("|");
		stringbuffer.append("capacity=").append(capacity).append("|");
		stringbuffer.append("FacilityDesignatedId=").append(
				facilityDesignatedId);
		return stringbuffer.toString();
	}

	public List getDobPermitList() {
		if (dobPermitList == null)
			try {
				dobPermitList = BulkOxygenTankEntity.getPermitInfo(getId());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		return dobPermitList;
	}

	public void setDobPermitList(List list) {
		dobPermitList = list;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
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

	protected int id;
	protected int buildingId;
	protected String fireDeptApproval;
	protected String facilityDesignatedId;
	protected String disconnectedyear;
	protected String yearinstalled;
	protected int modifiedinpast;
	protected List dobPermitList;
	protected String fireDeptApprovalno;
	protected String pressuretest;
	protected String lasttestdate;
	protected String nexttestdate;
	protected String nexttestdatenote;
	protected String bcomments;
	// protected String dobsignoff;
	protected String capacity;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.BulkOxygenTankVo.class);

}