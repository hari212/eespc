package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.util.UtilityObject;

public class FireAlarmVo implements Serializable {

	public FireAlarmVo() {
		id = -99;
		buildingId = -99;
		facilitydesignatedid = null;
		yearinstalled = null;
		statusOfSource = -99;
		disconnectedYear = null;

		typeofFAInstalled = -99;
		dobApproval = null;
		dobFiling = null;
		approvalDate = null;
		dobSignoff = null;

		fdApproval = null;
		fdApprovalDate = null;
		agencyApproval = null;
		agencyApprovalDate = null;
		agencyApprovalNo = null;
		comments = null;
		agencyName = null;

	}

	public FireAlarmVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilitydesignatedid = null;
		yearinstalled = null;
		statusOfSource = -99;
		disconnectedYear = null;

		typeofFAInstalled = -99;
		dobApproval = null;
		dobFiling = null;
		approvalDate = null;
		dobSignoff = null;
		fdApproval = null;
		fdApprovalDate = null;
		agencyApproval = null;
		agencyApprovalDate = null;
		agencyApprovalNo = null;
		comments = null;
		agencyName = null;

		id = UtilityObject.getIntFromString(resultset.getString("FIREALARMID"));
		buildingId = UtilityObject.getIntFromString(resultset
				.getString("BUILDINGID"));
		facilitydesignatedid = resultset.getString("facilitydesignatedid");
		yearinstalled = resultset.getString("yearinstalled");
		statusOfSource = UtilityObject.getIntFromString(resultset
				.getString("statusOfSource"));
		disconnectedYear = resultset.getString("disconnectedYear");

		typeofFAInstalled = UtilityObject.getIntFromString(resultset
				.getString("typeofFAInstalled"));
		dobApproval = resultset.getString("dobApproval");
		approvalDate = resultset.getString("approvalDate");
		fdApproval = resultset.getString("fdApproval");
		fdApprovalDate = resultset.getString("fdApprovalDate");
		agencyApproval = resultset.getString("agencyApproval");
		agencyApprovalDate = resultset.getString("agencyApprovalDate");
		agencyApprovalNo = resultset.getString("agencyApprovalNo");
		comments = resultset.getString("fcomments");
		agencyName = resultset.getString("agencyName");
		dobSignoff = resultset.getString("DOBSIGNOFF");
		dobFiling = resultset.getString("DOBFILING");
	}

	public String getDobSignoff() {
		return dobSignoff;
	}

	public void setDobSignoff(String data) {
		dobSignoff = data;
	}

	public String getDobFiling() {
		return dobFiling;
	}

	public void setDobFiling(String data) {
		dobFiling = data;
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

	public void setFacilitydesignatedid(String data) {
		facilitydesignatedid = data;
	}

	public String getFacilitydesignatedid() {
		return facilitydesignatedid;
	}

	public void setYearinstalled(String data) {
		yearinstalled = data;
	}

	public String getYearinstalled() {
		return yearinstalled;
	}

	public void setStatusOfSource(int data) {
		statusOfSource = data;
	}

	public int getStatusOfSource() {
		return statusOfSource;
	}

	public void setDisconnectedYear(String data) {
		disconnectedYear = data;
	}

	public String getDisconnectedYear() {
		return disconnectedYear;
	}

	public void setTypeofFAInstalled(int data) {
		typeofFAInstalled = data;
	}

	public int getTypeofFAInstalled() {
		return typeofFAInstalled;
	}

	public void setDobApproval(String data) {
		dobApproval = data;
	}

	public String getDobApproval() {
		return dobApproval;
	}

	public void setApprovalDate(String data) {
		approvalDate = data;
	}

	public String getApprovalDate() {
		return approvalDate;
	}

	public void setFdApproval(String data) {
		fdApproval = data;
	}

	public String getFdApproval() {
		return fdApproval;
	}

	public void setFdApprovalDate(String data) {
		fdApprovalDate = data;
	}

	public String getFdApprovalDate() {
		return fdApprovalDate;
	}

	public void setAgencyApproval(String data) {
		agencyApproval = data;
	}

	public String getAgencyApproval() {
		return agencyApproval;
	}

	public void setAgencyName(String data) {
		agencyName = data;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyApprovalDate(String data) {
		agencyApprovalDate = data;
	}

	public String getAgencyApprovalDate() {
		return agencyApprovalDate;
	}

	public void setAgencyApprovalNo(String data) {
		agencyApprovalNo = data;
	}

	public String getAgencyApprovalNo() {
		return agencyApprovalNo;
	}

	public void setComments(String data) {
		comments = data;
	}

	public String getComments() {
		return comments;
	}

	protected int id;
	protected int buildingId;
	protected String facilitydesignatedid;
	protected String yearinstalled;
	protected int statusOfSource;
	protected String disconnectedYear;

	protected int typeofFAInstalled;
	protected String dobApproval;
	protected String dobFiling;
	protected String approvalDate;
	protected String dobSignoff;
	protected String fdApproval;
	protected String fdApprovalDate;
	protected String agencyApproval;
	protected String agencyApprovalDate;
	protected String agencyApprovalNo;
	protected String comments;

	protected String agencyName;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.FireAlarmVo.class);

}