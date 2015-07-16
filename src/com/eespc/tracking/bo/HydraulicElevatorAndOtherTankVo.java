package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HydraulicElevatorAndOtherTankVo {

	public HydraulicElevatorAndOtherTankVo() {
		bldgId = -99;
		id = -99;
		facilityDesignatedId = null;
		capacity = -99;
		isSecondaryContainment = false;
		usage = null;
		disconnectedyear = null;
		modifiedinpast = -99;
		yearinstalled = null;
		decapproval = null;
		monthlyinspection = null;
		spillkit = null;
		pbsno = null;
		hcomments = null;
		dobsignoff = null;
		dobnumber = null;

	}

	public HydraulicElevatorAndOtherTankVo(ResultSet resultset)
			throws SQLException {
		bldgId = -99;
		id = -99;
		facilityDesignatedId = null;
		capacity = -99;
		isSecondaryContainment = false;
		usage = null;
		modifiedinpast = -99;
		yearinstalled = null;
		disconnectedyear = null;
		decapproval = null;
		monthlyinspection = null;
		spillkit = null;
		pbsno = null;
		hcomments = null;
		dobsignoff = null;
		dobnumber = null;

		bldgId = resultset.getInt("buildingId");
		id = resultset.getInt("otherTankId");
		facilityDesignatedId = resultset.getString("FACILITYDesignatedID");
		capacity = resultset.getInt("capacity");
		usage = resultset.getString("tankusage");
		String s = resultset.getString("isSecondaryContainment");
		if (s != null && s.equalsIgnoreCase("Y"))
			isSecondaryContainment = true;
		else
			isSecondaryContainment = false;
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		yearinstalled = resultset.getString("YEARINSTALLED");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		decapproval = resultset.getString("DECAPPROVAL");
		monthlyinspection = resultset.getString("MONTHLYINSPECTION");
		spillkit = resultset.getString("SPILLKIT");
		pbsno = resultset.getString("PBSNO");
		hcomments = resultset.getString("HCOMMENTS");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		dobnumber = resultset.getString("DOBNUMBER");

	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getDobnumber() {
		return dobnumber;
	}

	public void setDobnumber(String s) {
		dobnumber = s;
	}

	public String getDecapproval() {
		return decapproval;
	}

	public void setDecapproval(String s) {
		decapproval = s;
	}

	public String getMonthlyinspection() {
		return monthlyinspection;
	}

	public void setMonthlyinspection(String s) {
		monthlyinspection = s;
	}

	public String getSpillkit() {
		return spillkit;
	}

	public void setSpillkit(String s) {
		spillkit = s;
	}

	public String getPbsno() {
		return pbsno;
	}

	public void setPbsno(String s) {
		pbsno = s;
	}

	public String getHcomments() {
		return hcomments;
	}

	public void setHcomments(String s) {
		hcomments = s;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int i) {
		capacity = i;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
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

	public boolean isSecondaryContainment() {
		return isSecondaryContainment;
	}

	public void setSecondaryContainment(boolean flag) {
		isSecondaryContainment = flag;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String s) {
		usage = s;
	}

	public int getBldgId() {
		return bldgId;
	}

	public void setBldgId(int i) {
		bldgId = i;
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

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("bldgId =").append(bldgId).append("|");
		stringbuffer.append("id =").append(id).append("|");
		stringbuffer.append("facilityDesignatedId =")
				.append(facilityDesignatedId).append("|");
		stringbuffer.append("capacity =").append(capacity).append("|");
		stringbuffer.append("isSecondaryContainment =")
				.append(isSecondaryContainment).append("|");
		stringbuffer.append("disconnectedyear=").append(disconnectedyear)
				.append("|");
		stringbuffer.append("modifiedinpast=").append(modifiedinpast)
				.append("|");
		stringbuffer.append("yearinstalled=").append(yearinstalled).append("|");
		stringbuffer.append("usage =").append(usage);
		return stringbuffer.toString();
	}

	protected int bldgId;
	protected int id;
	protected String facilityDesignatedId;
	protected int capacity;
	protected boolean isSecondaryContainment;
	protected String usage;
	protected String disconnectedyear;
	protected String yearinstalled;
	protected int modifiedinpast;
	protected String decapproval;
	protected String monthlyinspection;
	protected String spillkit;
	protected String pbsno;
	protected String hcomments;
	protected String dobsignoff;
	protected String dobnumber;

}