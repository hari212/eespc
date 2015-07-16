package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.ElevatorEntity;
import com.eespc.tracking.entity.HydraulicElevatorTankEntity;
import com.eespc.tracking.exceptions.TrackingException;

public class ElevatorVo implements Serializable {

	public ElevatorVo() {
		elevatorId = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		comment = null;
		hydraulicTankId = -99;
		fuelFromObj = null;
		hydraulicTankVo = null;
		permitInfo = null;
		eleType = null;
		disconnectedyear = null;
		modifiedinpast = -99;
		yearinstalled = null;
	}

	public ElevatorVo(ResultSet resultset) throws SQLException {
		elevatorId = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		hydraulicTankId = -99;
		fuelFromObj = null;
		hydraulicTankVo = null;
		modifiedinpast = -99;
		yearinstalled = null;
		disconnectedyear = null;
		comment = null;
		permitInfo = null;
		eleType = null;
		buildingId = resultset.getInt("BUILDINGID");
		elevatorId = resultset.getInt("ELEVATORID");
		hydraulicTankId = resultset.getInt("HYDRAULICTANK");
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		yearinstalled = resultset.getString("YEARINSTALLED");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		comment = resultset.getString("ECOMMENTS");
		eleType = resultset.getString("ELETYPE");
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String s) {
		comment = s;
	}

	public String getEleType() {
		return eleType;
	}

	public void setEleType(String s) {
		eleType = s;
	}

	public int getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(int i) {
		elevatorId = i;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public int getModifiedInPast() {
		return modifiedinpast;
	}

	public void setModifiedInPast(int i) {
		modifiedinpast = i;
	}

	public int getHydraulicTankId() {
		return hydraulicTankId;
	}

	public void setHydraulicTankId(int i) {
		hydraulicTankId = i;
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

	public HydraulicElevatorAndOtherTankVo getHydraulicTankVo() {
		return hydraulicTankVo;
	}

	public void setHydraulicTankVo(
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo) {
		hydraulicTankVo = hydraulicelevatorandothertankvo;
	}

	public HydraulicElevatorAndOtherTankVo getFuelFromObj() {
		if (fuelFromObj == null)
			try {
				fuelFromObj = HydraulicElevatorTankEntity
						.findByPrimaryKey(getHydraulicTankId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return fuelFromObj;
	}

	public void setFuelFromObj(
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo) {
		fuelFromObj = hydraulicelevatorandothertankvo;
	}

	public List getPermitInfo() {
		if (permitInfo == null)
			try {
				permitInfo = ElevatorEntity.getPermitList(getElevatorId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return permitInfo;
	}

	public void setPermitInfo(List list) {
		permitInfo = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ",";
		stringbuffer.append("elevatorId =").append(elevatorId).append(s);
		stringbuffer.append("buildingId =").append(buildingId).append(s);
		stringbuffer.append("comment =").append(comment).append(s);
		stringbuffer.append("disconnectedyear=").append(disconnectedyear)
				.append(s);
		stringbuffer.append("modifiedinpast =").append(modifiedinpast)
				.append(s);
		stringbuffer.append("yearinstalled=").append(yearinstalled).append(s);
		stringbuffer.append("facilityDesignatedId =")
				.append(facilityDesignatedId).append(s);
		stringbuffer.append("hydraulicTankId =").append(hydraulicTankId);
		return stringbuffer.toString();
	}

	protected int elevatorId;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected String comment;
	protected int hydraulicTankId;
	protected HydraulicElevatorAndOtherTankVo fuelFromObj;
	protected HydraulicElevatorAndOtherTankVo hydraulicTankVo;
	protected String disconnectedyear;
	protected String yearinstalled;
	protected String eleType;
	protected int modifiedinpast;
	protected List permitInfo;
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.ElevatorVo.class);

}