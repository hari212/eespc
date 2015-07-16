package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.PlaceofAssemblyEntity;
import com.eespc.tracking.util.UtilityObject;

public class PlaceofAssemblyVo implements Serializable {

	public PlaceofAssemblyVo() {
		id = -99;
		buildingId = -99;
		facilitydesignatedid = null;
		paType = -99;
		location = null;
		seatingCapacity = null;
		dobfiling = null;
		dobpermit = null;
		dobsignoff = null;
		dobPermitnumber = null;
		dobPlan = null;
		fdPermitObtained = null;
		openViolation = null;
		violationType = -99;
		violationNum = null;
		note = null;
		permitList = null;

	}

	public PlaceofAssemblyVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilitydesignatedid = null;
		paType = -99;
		location = null;
		seatingCapacity = null;
		dobfiling = null;
		dobpermit = null;
		dobsignoff = null;
		dobPermitnumber = null;
		dobPlan = null;
		fdPermitObtained = null;
		openViolation = null;
		violationType = -99;
		violationNum = null;
		note = null;
		permitList = null;

		id = UtilityObject.getIntFromString(resultset
				.getString("PLACEOFASSEMBLYID"));
		buildingId = UtilityObject.getIntFromString(resultset
				.getString("BUILDINGID"));
		facilitydesignatedid = resultset.getString("FACILITYDESIGNATEDID");
		paType = resultset.getInt("PATYPE");
		location = resultset.getString("LOCATION");
		seatingCapacity = resultset.getString("SEATINGCAPACITY");
		dobfiling = resultset.getString("DOBFILING");
		dobpermit = resultset.getString("DOBPERMIT");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		dobPermitnumber = resultset.getString("DOBPERMITNUMBER");
		dobPlan = resultset.getString("DOBPLAN");
		fdPermitObtained = resultset.getString("FDPERMITOBTAINED");
		openViolation = resultset.getString("OPENVIOLATION");
		violationType = resultset.getInt("VIOLATIONTYPE");
		violationNum = resultset.getString("VIOLATIONNUM");
		note = resultset.getString("NOTE");

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

	public int getPaType() {
		return paType;
	}

	public void setPaType(int i) {
		paType = i;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String s) {
		location = s;
	}

	public String getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(String s) {
		seatingCapacity = s;
	}

	public String getDobfiling() {
		return dobfiling;
	}

	public void setDobfiling(String s) {
		dobfiling = s;
	}

	public String getDobpermit() {
		return dobpermit;
	}

	public void setDobpermit(String s) {
		dobpermit = s;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getDobPermitnumber() {
		return dobPermitnumber;
	}

	public void setDobPermitnumber(String s) {
		dobPermitnumber = s;
	}

	public String getDobPlan() {
		return dobPlan;
	}

	public void setDobPlan(String s) {
		dobPlan = s;
	}

	public String getFdPermitObtained() {
		return fdPermitObtained;
	}

	public void setFdPermitObtained(String s) {
		fdPermitObtained = s;
	}

	public String getOpenViolation() {
		return openViolation;
	}

	public void setOpenViolation(String s) {
		openViolation = s;
	}

	public int getViolationType() {
		return violationType;
	}

	public void setViolationType(int i) {
		violationType = i;
	}

	public String getViolationNum() {
		return violationNum;
	}

	public void setViolationNum(String s) {
		violationNum = s;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String s) {
		note = s;
	}

	public List getPermitList() {
		if (permitList == null)
			try {
				permitList = PlaceofAssemblyEntity.getPermitList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return permitList;
	}

	public void setPermitList(List list) {
		permitList = list;
	}

	protected int id;
	protected int buildingId;
	protected String facilitydesignatedid;
	protected int paType;
	protected String location;
	protected String seatingCapacity;
	protected String dobfiling;
	protected String dobpermit;
	protected String dobsignoff;
	protected String dobPermitnumber;
	protected String dobPlan;
	protected String fdPermitObtained;
	protected String openViolation;
	protected int violationType;
	protected String violationNum;
	protected String note;
	protected List permitList;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.PlaceofAssemblyVo.class);

}