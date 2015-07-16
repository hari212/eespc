package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.RpzEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class RpzVo implements Serializable {

	public RpzVo() {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		yearInstalled = null;
		modifiedinpast = -99;
		disconnectedyear = null;
		serialNumber = null;
		make = null;
		model = null;
		type = null;
		location = -99;
		size = null;
		comments = null;
		dobPermitNumber = null;
		depPermitNumber = null;
		inspectionList = null;
		permitInfo = null;
		dobsignoff = null;
	}

	public RpzVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		yearInstalled = null;
		modifiedinpast = -99;
		disconnectedyear = null;
		serialNumber = null;
		make = null;
		model = null;
		type = null;
		location = -99;
		size = null;
		comments = null;
		dobPermitNumber = null;
		depPermitNumber = null;
		inspectionList = null;
		permitInfo = null;
		dobsignoff = null;

		id = resultset.getInt("RPZID");
		buildingId = resultset.getInt("BUILDINGID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		yearInstalled = resultset.getString("YEARINSTALLED");
		serialNumber = resultset.getString("SERIALNUMBER");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		type = resultset.getString("TYPE");

		// isPermit =
		// UtilityObject.convertBoolean(resultset.getString("PERMITSTATUS"));

		String ss = resultset.getString("PERMITSTATUS");

		isPermit = ss;

		size = resultset.getString("SIZE");
		String s = resultset.getString("LOCATION");
		if (UtilityObject.isNotEmpty(s))
			location = resultset.getInt("LOCATION");
		comments = resultset.getString("RCOMMENTS");
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		yearInstalled = resultset.getString("YEARINSTALLED");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		dobPermitNumber = resultset.getString("DOBPERMITNUMBER");
		depPermitNumber = resultset.getString("DEPPERMITNUMBER");
		dobsignoff = resultset.getString("DOBSIGNOFF");
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String s) {
		comments = s;
	}

	public String isPermit() {
		return isPermit;
	}

	public void setPermit(String flag) {
		isPermit = flag;
	}

	/*
	 * public boolean isPermit() { return isPermit; } public void
	 * setPermit(boolean flag) { isPermit = flag; }
	 */
	public String getDepPermitNumber() {
		return depPermitNumber;
	}

	public void setDepPermitNumber(String s) {
		depPermitNumber = s;
	}

	public String getDobPermitNumber() {
		return dobPermitNumber;
	}

	public void setDobPermitNumber(String s) {
		dobPermitNumber = s;
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

	public List getInspectionList() {
		if (inspectionList == null)
			try {
				inspectionList = RpzEntity.getInstallationInfo(getId());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		return inspectionList;
	}

	public void setInspectionList(List list) {
		inspectionList = list;
	}

	public int getLocation() {
		return location;
	}

	public List getPermitInfo() {
		if (permitInfo == null)
			try {
				permitInfo = RpzEntity.getPermitList(id);
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return permitInfo;
	}

	public void setPermitInfo(List list) {
		permitInfo = list;
	}

	public void setLocation(int i) {
		location = i;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String s) {
		make = s;
	}

	public String getType() {
		return type;
	}

	public void setType(String s) {
		type = s;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String s) {
		serialNumber = s;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String s) {
		size = s;
	}

	public String getYearInstalled() {
		return yearInstalled;
	}

	public void setYearInstalled(String s) {
		yearInstalled = s;
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
		stringbuffer.append("id =").append(id);
		stringbuffer.append(",buildingId =").append(buildingId);
		stringbuffer.append(",facilityDesignatedId =").append(
				facilityDesignatedId);
		stringbuffer.append(",yearInstalled =").append(yearInstalled);
		stringbuffer.append(",serialNumber =").append(serialNumber);
		stringbuffer.append(",make =").append(make);
		stringbuffer.append(",model =").append(model);
		stringbuffer.append(",type =").append(type);
		stringbuffer.append(",permitstatus =").append(isPermit);
		stringbuffer.append(",size =").append(size);
		stringbuffer.append(",location =").append(location);
		stringbuffer.append(",comments =").append(comments);
		stringbuffer.append(",disconnectedyear =").append(disconnectedyear);
		stringbuffer.append(",modifiedinpast=").append(modifiedinpast);
		stringbuffer.append(",dobPermitNumber =").append(dobPermitNumber);
		stringbuffer.append(",depPermitNumber =").append(depPermitNumber);
		return stringbuffer.toString();
	}

	protected int id;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected String yearInstalled;
	protected String serialNumber;
	protected String make;
	protected String model;
	protected String type;
	protected String isPermit;
	protected int location;
	protected String size;
	protected String comments;
	protected String dobPermitNumber;
	protected String depPermitNumber;
	protected List inspectionList;
	protected List permitInfo;
	protected String disconnectedyear;
	protected int modifiedinpast;
	protected String dobsignoff;
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.RpzVo.class);

}