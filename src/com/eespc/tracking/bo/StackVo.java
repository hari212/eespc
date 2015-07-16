package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StackVo {

	public StackVo() {
		stackId = -99;
		buildingId = -99;
		floor = null;
		height = -99D;
		diameter = -99D;
		flowRate = -99D;
		exhaustTemp = -99D;
		velocity = -99D;
		decEmissionPointId = null;
		yearinstalled = null;
		facilityStackId = null;
		noofsource = null;
		totalcapacity = null;
		typeoffuel = null;
		// permitInfo = null;
		isMethodNineTest = null;
		methodNineLastDate = null;
		methodNineNextTestDate = null;
		isOpacityLimit = null;
		// facilityDesignatedId = null;
	}

	public StackVo(ResultSet resultset) throws SQLException {
		stackId = -99;
		buildingId = -99;
		floor = null;
		height = -99D;
		diameter = -99D;
		flowRate = -99D;
		exhaustTemp = -99D;
		velocity = -99D;
		decEmissionPointId = null;
		yearinstalled = null;
		facilityStackId = null;
		noofsource = null;
		totalcapacity = null;
		typeoffuel = null;
		// permitInfo = null;
		isMethodNineTest = null;
		methodNineLastDate = null;
		methodNineNextTestDate = null;
		isOpacityLimit = null;
		// facilityDesignatedId = null;

		stackId = resultset.getInt("STACKID");
		buildingId = resultset.getInt("BUILDINGID");
		floor = resultset.getString("FLOOR");
		height = resultset.getDouble("HEIGHT");
		diameter = resultset.getDouble("DIAMETER");
		flowRate = resultset.getDouble("FLOWRATE");
		exhaustTemp = resultset.getDouble("EXHAUSTTEMP");
		velocity = resultset.getDouble("VELOCITY");
		decEmissionPointId = resultset.getString("DECEMISSIONPOINTID");
		yearinstalled = resultset.getString("YEARINSTALLED");
		facilityStackId = resultset.getString("FACILITYSTACKID");
		noofsource = resultset.getString("NOOFSOURCE");
		totalcapacity = resultset.getString("CAPACITY");
		typeoffuel = resultset.getString("TYPEOFFUEL");
		/*
		 * modifiedinpast = resultset.getInt("MODIFIEDINPAST"); disconnectedyear
		 * = resultset.getString("DISCONNECTEDYEAR");
		 */
		String s = resultset.getString("methodNineTest");
		isMethodNineTest = s;
		methodNineLastDate = resultset.getString("methodNineLastDate");
		methodNineNextTestDate = resultset.getString("methodNineNextTestDate");
		s = resultset.getString("opacityLimit");
		isOpacityLimit = s;
		// facilityDesignatedId = resultset.getString("facilitydesignatedid");
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	/*
	 * public String getFacilityDesignatedId() { return facilityDesignatedId; }
	 * 
	 * public void setFacilityDesignatedId(String s) { facilityDesignatedId = s;
	 * }
	 */
	public String isMethodNineTest() {
		return isMethodNineTest;
	}

	public void setMethodNineTest(String flag) {
		isMethodNineTest = flag;
	}

	public String isOpacityLimit() {
		return isOpacityLimit;
	}

	public void setOpacityLimit(String flag) {
		isOpacityLimit = flag;
	}

	public String getMethodNineLastDate() {
		return methodNineLastDate;
	}

	public void setMethodNineLastDate(String s) {
		methodNineLastDate = s;
	}

	public String getMethodNineNextTestDate() {
		return methodNineNextTestDate;
	}

	public void setMethodNineNextTestDate(String s) {
		methodNineNextTestDate = s;
	}

	public String getNoofsource() {
		return noofsource;
	}

	public void setNoofsource(String s) {
		noofsource = s;
	}

	public String getTotalcapacity() {
		return totalcapacity;
	}

	public void setTotalcapacity(String s) {
		totalcapacity = s;
	}

	public String getTypeoffuel() {
		return typeoffuel;
	}

	public void setTypeoffuel(String s) {
		typeoffuel = s;
	}

	public String getDecEmissionPointId() {
		return decEmissionPointId;
	}

	public void setDecEmissionPointId(String s) {
		decEmissionPointId = s;
	}

	public double getDiameter() {
		return diameter;
	}

	public void setDiameter(double d) {
		diameter = d;
	}

	public double getExhaustTemp() {
		return exhaustTemp;
	}

	public void setExhaustTemp(double d) {
		exhaustTemp = d;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String s) {
		floor = s;
	}

	public double getFlowRate() {
		return flowRate;
	}

	public void setFlowRate(double d) {
		flowRate = d;
	}

	public String getYearInstalled() {
		return yearinstalled;
	}

	public void setYearInstalled(String s) {
		yearinstalled = s;
	}

	/*
	 * public String getDisconnectedYear() { return disconnectedyear; }
	 * 
	 * public void setDisconnectedYear(String s) { disconnectedyear = s; }
	 * 
	 * public int getModifiedInPast() { return modifiedinpast; }
	 * 
	 * public void setModifiedInPast(int i) { modifiedinpast = i; }
	 */

	public double getHeight() {
		return height;
	}

	public void setHeight(double d) {
		height = d;
	}

	/*
	 * public List getPermitInfo() { if(permitInfo == null) try { permitInfo =
	 * StackEntity.getPermitInfo(stackId); } catch(TrackingException
	 * trackingexception) { log.error(trackingexception); } return permitInfo; }
	 * 
	 * public void setPermitInfo(List list) { permitInfo = list; }
	 */

	public int getStackId() {
		return stackId;
	}

	public void setStackId(int i) {
		stackId = i;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double d) {
		velocity = d;
	}

	public String getFacilityStackId() {
		return facilityStackId;
	}

	public void setFacilityStackId(String s) {
		facilityStackId = s;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.StackVo.class);
	protected int stackId;
	protected int buildingId;
	protected String floor;
	protected double height;
	protected double diameter;
	protected double flowRate;
	protected double exhaustTemp;
	protected double velocity;
	protected String decEmissionPointId;
	protected String yearinstalled;
	protected String facilityStackId;
	protected String noofsource;
	protected String totalcapacity;
	protected String typeoffuel;
	protected String isMethodNineTest;
	protected String methodNineLastDate;
	protected String methodNineNextTestDate;
	protected String isOpacityLimit;
	/*
	 * protected List permitInfo; protected String disconnectedyear; protected
	 * int modifiedinpast; protected String facilityDesignatedId;
	 */
}