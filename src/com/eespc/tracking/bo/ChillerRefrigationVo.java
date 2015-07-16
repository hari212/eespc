package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.util.UtilityObject;

public class ChillerRefrigationVo implements Serializable {

	public ChillerRefrigationVo() {
		id = -99;
		buildingId = -99;
		facilitydesignatedid = null;
		YearInstalled = null;
		make = null;
		model = null;
		status = -99;
		disconnectedyear = null;
		location = -99;
		capacitytons = null;
		capacitybtu = null;
		equipmentusepermit = null;
		dobpermit = null;
		dobissuedate = null;
		dobsignoff = null;
		epastatus = null;
		epasubmittaldate = null;
		// submittaldatenote = null;
		epaapprovaldate = null;
		fdapproval = null;
		// fdapprovaldate = null;
		comments = null;
		refrigerationrecovery = null;
		epamaintained = null;
		serialnum = null;
		areaServed = null;
		dobfiling = null;
		fuelFiring = null;
		fuelUsed = null;
		depStatus = null;
		deppermit = null;
		depexpirationdate = null;

	}

	public ChillerRefrigationVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilitydesignatedid = null;
		YearInstalled = null;
		make = null;
		model = null;
		status = -99;
		disconnectedyear = null;
		location = -99;
		capacitytons = null;
		capacitybtu = null;
		equipmentusepermit = null;
		dobpermit = null;
		dobissuedate = null;
		dobsignoff = null;
		epastatus = null;
		epasubmittaldate = null;
		// submittaldatenote = null;
		epaapprovaldate = null;
		fdapproval = null;
		// fdapprovaldate = null;
		comments = null;
		refrigerationrecovery = null;
		epamaintained = null;
		serialnum = null;
		areaServed = null;
		dobfiling = null;
		fuelFiring = null;
		fuelUsed = null;
		depStatus = null;
		deppermit = null;
		depexpirationdate = null;

		id = UtilityObject.getIntFromString(resultset
				.getString("CHILLERREFRIGATIONID"));
		buildingId = UtilityObject.getIntFromString(resultset
				.getString("BUILDINGID"));
		facilitydesignatedid = resultset.getString("FACILITYDESIGNATEDID");
		YearInstalled = resultset.getString("YEARINSTALLED");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		status = resultset.getInt("STATUS");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		location = resultset.getInt("LOCATION");
		capacitytons = resultset.getString("CAPACITYTONS");
		capacitybtu = resultset.getString("CAPACITYBTU");
		equipmentusepermit = resultset.getString("EQUIPMENTUSEPERMIT");
		dobpermit = resultset.getString("DOBPERMIT");
		dobissuedate = resultset.getString("DOBISSUEDATE");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		epastatus = resultset.getString("EPASTATUS");
		epasubmittaldate = resultset.getString("EPASUBMITTALDATE");
		// submittaldatenote=resultset.getString("SUBMITTALDATENOTE");
		epaapprovaldate = resultset.getString("EPAAPPROVALDATE");
		fdapproval = resultset.getString("FDAPPROVAL");
		// fdapprovaldate=resultset.getString("FDAPPROVALDATE");
		comments = resultset.getString("CRCOMMENTS");
		refrigerationrecovery = resultset.getString("REFRIGERATIONRECOVERY");
		epamaintained = resultset.getString("EPAMAINTAINED");
		serialnum = resultset.getString("SERIALNUM");
		areaServed = resultset.getString("AREASERVED");
		dobfiling = resultset.getString("DOBFILING");
		fuelFiring = resultset.getString("FUELFIRING");
		fuelUsed = resultset.getString("FUELUSED");
		depStatus = resultset.getString("DEPSTATUS");
		deppermit = resultset.getString("DEPPERMIT");
		depexpirationdate = resultset.getString("DEPEXPIRATIONDATE");
	}

	public String getFuelFiring() {
		return fuelFiring;
	}

	public void setFuelFiring(String s) {
		fuelFiring = s;
	}

	public String getFuelUsed() {
		return fuelUsed;
	}

	public void setFuelUsed(String s) {
		fuelUsed = s;
	}

	public String getDepStatus() {
		return depStatus;
	}

	public void setDepStatus(String s) {
		depStatus = s;
	}

	public String getDeppermit() {
		return deppermit;
	}

	public void setDeppermit(String s) {
		deppermit = s;
	}

	public String getDepexpirationdate() {
		return depexpirationdate;
	}

	public void setDepexpirationdate(String s) {
		depexpirationdate = s;
	}

	public String getSerialnum() {
		return serialnum;
	}

	public void setSerialnum(String s) {
		serialnum = s;
	}

	public String getDobfiling() {
		return dobfiling;
	}

	public void setDobfiling(String s) {
		dobfiling = s;
	}

	public String getAreaServed() {
		return areaServed;
	}

	public void setAreaServed(String s) {
		areaServed = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getFacilitydesignatedId() {
		return facilitydesignatedid;
	}

	public void setFacilitydesignatedId(String s) {
		facilitydesignatedid = s;
	}

	public String getYearInstalled() {
		return YearInstalled;
	}

	public void setYearInstalled(String s) {
		YearInstalled = s;
	}

	/*
	 * public String getSubmittalDateNote() { return submittaldatenote; } public
	 * void setSubmittalDateNote(String s) { submittaldatenote = s; }
	 */

	public String getMake() {
		return make;
	}

	public void setMake(String s) {
		make = s;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		status = i;
	}

	public String getDisconnectedyear() {
		return disconnectedyear;
	}

	public void setDisconnectedyear(String s) {
		disconnectedyear = s;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int i) {
		location = i;
	}

	public String getCapacityTons() {
		return capacitytons;
	}

	public void setCapacityTons(String s) {
		capacitytons = s;
	}

	public String getCapacityBtu() {
		return capacitybtu;
	}

	public void setCapacityBtu(String s) {
		capacitybtu = s;
	}

	public String getEquipmentusepermit() {
		return equipmentusepermit;
	}

	public void setEquipmentusepermit(String s) {
		equipmentusepermit = s;
	}

	public String getDobpermit() {
		return dobpermit;
	}

	public void setDobpermit(String s) {
		dobpermit = s;
	}

	public String getDobissuedate() {
		return dobissuedate;
	}

	public void setDobissuedate(String s) {
		dobissuedate = s;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getEpaStatus() {
		return epastatus;
	}

	public void setEpaStatus(String s) {
		epastatus = s;
	}

	public String getEpaSubmittalDate() {
		return epasubmittaldate;
	}

	public void setEpaSubmittalDate(String s) {
		epasubmittaldate = s;
	}

	public String getEpaApprovalDate() {
		return epaapprovaldate;
	}

	public void setEpaApprovalDate(String s) {
		epaapprovaldate = s;
	}

	public String getFdaApproval() {
		return fdapproval;
	}

	public void setFdaApproval(String s) {
		fdapproval = s;
	}

	/*
	 * public String getFdaApprovalDate() { return fdapprovaldate; } public void
	 * setFdaApprovalDate(String s) { fdapprovaldate = s; }
	 */

	public String getComments() {
		return comments;
	}

	public void setComments(String s) {
		comments = s;
	}

	public String getEpamaintained() {
		return epamaintained;
	}

	public void setEpamaintained(String s) {
		epamaintained = s;
	}

	public String getRefrigerationrecovery() {
		return refrigerationrecovery;
	}

	public void setRefrigerationrecovery(String s) {
		refrigerationrecovery = s;
	}

	protected int id;
	protected int buildingId;
	protected String facilitydesignatedid;
	protected String YearInstalled;
	protected String make;
	protected String model;
	protected int status;
	protected String disconnectedyear;
	protected int location;
	protected String capacitytons;
	protected String capacitybtu;
	protected String equipmentusepermit;
	protected String dobpermit;
	protected String dobissuedate;
	protected String dobsignoff;
	protected String epastatus;
	protected String epasubmittaldate;
	// protected String submittaldatenote;
	protected String epaapprovaldate;
	protected String fdapproval;
	// protected String fdapprovaldate;
	protected String comments;
	protected String refrigerationrecovery;
	protected String epamaintained;
	protected String serialnum;
	protected String areaServed;
	protected String dobfiling;
	protected String fuelFiring;
	protected String fuelUsed;
	protected String depStatus;
	protected String deppermit;
	protected String depexpirationdate;
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.ChillerRefrigationVo.class);

}