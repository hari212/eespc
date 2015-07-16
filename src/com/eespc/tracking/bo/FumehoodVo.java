package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.FumehoodEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class FumehoodVo implements Serializable {

	public FumehoodVo() {
		id = -99;
		buildingId = -99;
		stackId = -99;
		stackVo = null;
		facilityDesignatedId = null;
		floor = null;
		yearinstalled = null;
		status = -99;
		disconnecteddate = null;
		chemical1 = null;
		chemicalname1 = null;
		volume1 = null;
		density1 = null;
		voc1 = null;
		voctot1 = null;
		chemical2 = null;
		chemicalname2 = null;
		volume2 = null;
		density2 = null;
		voc2 = null;
		voctot2 = null;
		chemical3 = null;
		chemicalname3 = null;
		volume3 = null;
		density3 = null;
		voc3 = null;
		voctot3 = null;
		chemical4 = null;
		chemicalname4 = null;
		volume4 = null;
		density4 = null;
		voc4 = null;
		voctot4 = null;
		chemical5 = null;
		chemicalname5 = null;
		volume5 = null;
		density5 = null;
		voc5 = null;
		voctot5 = null;
		make = null;
		model = null;
		voc = null;
		dob = null;
		hrsOfOperation = null;
		isExemptedByDec = false;
		isUnitIncludedInDecPermit = false;
		location = -99;
		permitInfoList = null;
		chemicalsUsedList = null;
		FumehoodHoursOfOperation1 = null;
		FumehoodDEPNumber = null;
		fcomments = null;
		dobsignoff = null;
		isDepPermitStatus = null;
		isDobPermitStatus = null;
		isAnnualPermitStatus = null;
		lastInspectionDate = null;
		nextInspectionDate = null;

	}

	public FumehoodVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		stackId = -99;
		stackVo = null;
		facilityDesignatedId = null;
		floor = null;
		yearinstalled = null;
		status = -99;
		disconnecteddate = null;
		chemical1 = null;
		chemicalname1 = null;
		volume1 = null;
		density1 = null;
		voc1 = null;
		voctot1 = null;
		chemical2 = null;
		chemicalname2 = null;
		volume2 = null;
		density2 = null;
		voc2 = null;
		voctot2 = null;
		chemical3 = null;
		chemicalname3 = null;
		volume3 = null;
		density3 = null;
		voc3 = null;
		voctot3 = null;
		chemical4 = null;
		chemicalname4 = null;
		volume4 = null;
		density4 = null;
		voc4 = null;
		voctot4 = null;
		chemical5 = null;
		chemicalname5 = null;
		volume5 = null;
		density5 = null;
		voc5 = null;
		voctot5 = null;
		make = null;
		model = null;
		voc = null;
		dob = null;
		FumehoodHoursOfOperation1 = null;
		FumehoodDEPNumber = null;
		fcomments = null;
		dobsignoff = null;
		isDepPermitStatus = null;
		isDobPermitStatus = null;
		isAnnualPermitStatus = null;
		lastInspectionDate = null;
		nextInspectionDate = null;

		hrsOfOperation = null;
		isExemptedByDec = false;
		isUnitIncludedInDecPermit = false;
		location = -99;
		permitInfoList = null;
		chemicalsUsedList = null;
		id = resultset.getInt("FUMEHOODID");
		buildingId = resultset.getInt("BUILDINGID");
		stackId = resultset.getInt("STACKEXHAUSTID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		floor = resultset.getString("FLOOR");
		yearinstalled = resultset.getString("YEARINSTALLED");
		status = resultset.getInt("STATUS");
		disconnecteddate = resultset.getString("DISCONNECTEDDATE");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		chemical1 = resultset.getString("chemical1");
		chemicalname1 = resultset.getString("chemicalname1");
		volume1 = resultset.getString("volume1");
		density1 = resultset.getString("density1");
		voc1 = resultset.getString("voc1");
		voctot1 = resultset.getString("voctot1");
		chemical2 = resultset.getString("chemical2");
		chemicalname2 = resultset.getString("chemicalname2");
		volume2 = resultset.getString("volume2");
		density2 = resultset.getString("density2");
		voc2 = resultset.getString("voc2");
		voctot2 = resultset.getString("voctot2");
		chemical3 = resultset.getString("chemical3");
		chemicalname3 = resultset.getString("chemicalname3");
		volume3 = resultset.getString("volume3");
		density3 = resultset.getString("density3");
		voc3 = resultset.getString("voc3");
		voctot3 = resultset.getString("voctot3");
		chemical4 = resultset.getString("chemical4");
		chemicalname4 = resultset.getString("chemicalname4");
		volume4 = resultset.getString("volume4");
		density4 = resultset.getString("density4");
		voc4 = resultset.getString("voc4");
		voctot4 = resultset.getString("voctot4");
		chemical5 = resultset.getString("chemical5");
		chemicalname5 = resultset.getString("chemicalname5");
		volume5 = resultset.getString("volume5");
		density5 = resultset.getString("density5");
		voc5 = resultset.getString("voc5");
		voctot5 = resultset.getString("voctot5");
		voc = resultset.getString("VOC");
		dob = resultset.getString("DOB");
		hrsOfOperation = resultset.getString("HROFOPERATION");
		String s = resultset.getString("EXEMPTEDBYDEC");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isExemptedByDec = true;
		s = resultset.getString("INCLUDEDINDECPERMIT");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isUnitIncludedInDecPermit = true;
		s = resultset.getString("LOCATION");
		if (UtilityObject.isNotEmpty(s))
			location = resultset.getInt("LOCATION");

		FumehoodHoursOfOperation1 = resultset
				.getString("FUMEHOODHOURSOFOPERATION1");
		FumehoodDEPNumber = resultset.getString("FUMEHOODDEPNUMBER");
		fcomments = resultset.getString("FCOMMENTS");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		isDepPermitStatus = resultset.getString("DEPPERMITSTATUS");
		isDobPermitStatus = resultset.getString("DOBPERMITSTATUS");
		isAnnualPermitStatus = resultset.getString("ANNUALPERMITSTATUS");
		lastInspectionDate = resultset.getString("LASTINSPECTIONDATE");
		nextInspectionDate = resultset.getString("NEXTINSPECTIONDATE");

	}

	public String getLastInspectionDate() {
		return lastInspectionDate;
	}

	public void setLastInspectionDate(String s) {
		lastInspectionDate = s;
	}

	public String getNextInspectionDate() {
		return nextInspectionDate;
	}

	public void setNextInspectionDate(String s) {
		nextInspectionDate = s;
	}

	public String isAnnualPermitStatus() {
		return isAnnualPermitStatus;
	}

	public void setAnnualPermitStatus(String flag) {
		isAnnualPermitStatus = flag;
	}

	public String isDepPermitStatus() {
		return isDepPermitStatus;
	}

	public void setDepPermitStatus(String flag) {
		isDepPermitStatus = flag;
	}

	public String isDobPermitStatus() {
		return isDobPermitStatus;
	}

	public void setDobPermitStatus(String flag) {
		isDobPermitStatus = flag;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getFumehoodHoursOfOperation1() {
		return FumehoodHoursOfOperation1;
	}

	public void setFumehoodHoursOfOperation1(String s) {
		FumehoodHoursOfOperation1 = s;
	}

	public String getFumehoodDEPNumber() {
		return FumehoodDEPNumber;
	}

	public void setFumehoodDEPNumber(String s) {
		FumehoodDEPNumber = s;
	}

	public String getFcomments() {
		return fcomments;
	}

	public void setFcomments(String s) {
		fcomments = s;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public List getChemicalsUsedList() {
		if (chemicalsUsedList == null)
			try {
				chemicalsUsedList = FumehoodEntity.getChemicalList(getId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return chemicalsUsedList;
	}

	public void setChemicalsUsedList(List list) {
		chemicalsUsedList = list;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String s) {
		floor = s;
	}

	public String getYearinstalled() {
		return yearinstalled;
	}

	public void setYearinstalled(String s) {
		yearinstalled = s;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int i) {
		status = i;
	}

	public String getDisconnecteddate() {
		return disconnecteddate;
	}

	public void setDisconnecteddate(String s) {
		disconnecteddate = s;
	}

	public String getHrsOfOperation() {
		return hrsOfOperation;
	}

	public void setHrsOfOperation(String s) {
		hrsOfOperation = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isExemptedByDec() {
		return isExemptedByDec;
	}

	public void setExemptedByDec(boolean flag) {
		isExemptedByDec = flag;
	}

	public boolean isUnitIncludedInDecPermit() {
		return isUnitIncludedInDecPermit;
	}

	public void setUnitIncludedInDecPermit(boolean flag) {
		isUnitIncludedInDecPermit = flag;
	}

	public int getLocation() {
		return location;
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

	public String getModel() {
		return model;
	}

	public void setChemical1(String s) {
		chemical1 = s;
	}

	public String getChemical1() {
		return chemical1;
	}

	public void setChemicalname1(String s) {
		chemicalname1 = s;
	}

	public String getChemicalname1() {
		return chemicalname1;
	}

	public void setVolume1(String s) {
		volume1 = s;
	}

	public String getVolume1() {
		return volume1;
	}

	public void setDensity1(String s) {
		density1 = s;
	}

	public String getDensity1() {
		return density1;
	}

	public void setVoc1(String s) {
		voc1 = s;
	}

	public String getVoc1() {
		return voc1;
	}

	public void setVoctot1(String s) {
		voctot1 = s;
	}

	public String getVoctot1() {
		return voctot1;
	}

	public void setChemical2(String s) {
		chemical2 = s;
	}

	public String getChemical2() {
		return chemical2;
	}

	public void setChemicalname2(String s) {
		chemicalname2 = s;
	}

	public String getChemicalname2() {
		return chemicalname2;
	}

	public void setVolume2(String s) {
		volume2 = s;
	}

	public String getVolume2() {
		return volume2;
	}

	public void setDensity2(String s) {
		density2 = s;
	}

	public String getDensity2() {
		return density2;
	}

	public void setVoc2(String s) {
		voc2 = s;
	}

	public String getVoc2() {
		return voc2;
	}

	public void setVoctot2(String s) {
		voctot2 = s;
	}

	public String getVoctot2() {
		return voctot2;
	}

	public void setChemical3(String s) {
		chemical3 = s;
	}

	public String getChemical3() {
		return chemical3;
	}

	public void setChemicalname3(String s) {
		chemicalname3 = s;
	}

	public String getChemicalname3() {
		return chemicalname3;
	}

	public void setVolume3(String s) {
		volume3 = s;
	}

	public String getVolume3() {
		return volume3;
	}

	public void setDensity3(String s) {
		density3 = s;
	}

	public String getDensity3() {
		return density3;
	}

	public void setVoc3(String s) {
		voc3 = s;
	}

	public String getVoc3() {
		return voc3;
	}

	public void setVoctot3(String s) {
		voctot3 = s;
	}

	public String getVoctot3() {
		return voctot3;
	}

	public void setChemical4(String s) {
		chemical4 = s;
	}

	public String getChemical4() {
		return chemical4;
	}

	public void setChemicalname4(String s) {
		chemicalname4 = s;
	}

	public String getChemicalname4() {
		return chemicalname4;
	}

	public void setVolume4(String s) {
		volume4 = s;
	}

	public String getVolume4() {
		return volume4;
	}

	public void setDensity4(String s) {
		density4 = s;
	}

	public String getDensity4() {
		return density4;
	}

	public void setVoc4(String s) {
		voc4 = s;
	}

	public String getVoc4() {
		return voc4;
	}

	public void setVoctot4(String s) {
		voctot4 = s;
	}

	public String getVoctot4() {
		return voctot4;
	}

	public void setChemical5(String s) {
		chemical5 = s;
	}

	public String getChemical5() {
		return chemical5;
	}

	public void setChemicalname5(String s) {
		chemicalname5 = s;
	}

	public String getChemicalname5() {
		return chemicalname5;
	}

	public void setVolume5(String s) {
		volume5 = s;
	}

	public String getVolume5() {
		return volume5;
	}

	public void setDensity5(String s) {
		density5 = s;
	}

	public String getDensity5() {
		return density5;
	}

	public void setVoc5(String s) {
		voc5 = s;
	}

	public String getVoc5() {
		return voc5;
	}

	public void setVoctot5(String s) {
		voctot5 = s;
	}

	public String getVoctot5() {
		return voctot5;
	}

	public void setvoc(String s) {
		voc = s;
	}

	public String getvoc() {
		return voc;
	}

	public void setdob(String s) {
		dob = s;
	}

	public String getdob() {
		return dob;
	}

	public void setModel(String s) {
		model = s;
	}

	public List getPermitInfoList() {
		if (permitInfoList == null)
			try {
				permitInfoList = FumehoodEntity.getPermitInfo(getId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return permitInfoList;
	}

	public void setPermitInfoList(List list) {
		permitInfoList = list;
	}

	public int getStackId() {
		return stackId;
	}

	public void setStackId(int i) {
		stackId = i;
	}

	public StackVo getStackVo() {
		if (getStackId() > 0)
			try {
				stackVo = StackEntity.findByPrimaryKey(getStackId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return stackVo;
	}

	public void setStackVo(StackVo stackvo) {
		stackVo = stackvo;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id=").append(id).append(", ");
		stringbuffer.append("buildingId=").append(buildingId).append(", ");
		stringbuffer.append("stackId=").append(stackId).append(", ");
		stringbuffer.append("facilityDesignatedId=")
				.append(facilityDesignatedId).append(", ");
		stringbuffer.append("floor=").append(floor).append(", ");
		stringbuffer.append("yearinstalled=").append(yearinstalled)
				.append(", ");
		stringbuffer.append("status=").append(status).append(", ");
		stringbuffer.append("disconnecteddate=").append(disconnecteddate)
				.append(", ");
		stringbuffer.append("make=").append(make).append(", ");
		stringbuffer.append("model=").append(model).append(", ");
		stringbuffer.append("chemical1=").append(chemical1).append(", ");
		stringbuffer.append("chemicalname1=").append(chemicalname1)
				.append(", ");
		stringbuffer.append("volume1=").append(volume1).append(", ");
		stringbuffer.append("density1=").append(density1).append(", ");
		stringbuffer.append("voc1=").append(voc1).append(", ");
		stringbuffer.append("voctot1=").append(voctot1).append(", ");
		stringbuffer.append("chemical2=").append(chemical2).append(", ");
		stringbuffer.append("chemicalname2=").append(chemicalname2)
				.append(", ");
		stringbuffer.append("volume2=").append(volume2).append(", ");
		stringbuffer.append("density2=").append(density2).append(", ");
		stringbuffer.append("voc2=").append(voc2).append(", ");
		stringbuffer.append("voctot2=").append(voctot2).append(", ");
		stringbuffer.append("chemical3=").append(chemical3).append(", ");
		stringbuffer.append("chemicalname3=").append(chemicalname3)
				.append(", ");
		stringbuffer.append("volume3=").append(volume3).append(", ");
		stringbuffer.append("density3=").append(density3).append(", ");
		stringbuffer.append("voc3=").append(voc3).append(", ");
		stringbuffer.append("voctot3=").append(voctot3).append(", ");
		stringbuffer.append("chemical4=").append(chemical4).append(", ");
		stringbuffer.append("chemicalname4=").append(chemicalname4)
				.append(", ");
		stringbuffer.append("volume4=").append(volume4).append(", ");
		stringbuffer.append("density4=").append(density4).append(", ");
		stringbuffer.append("voc4=").append(voc4).append(", ");
		stringbuffer.append("voctot4=").append(voctot4).append(", ");
		stringbuffer.append("chemical5=").append(chemical5).append(", ");
		stringbuffer.append("chemicalname5=").append(chemicalname5)
				.append(", ");
		stringbuffer.append("volume5=").append(volume5).append(", ");
		stringbuffer.append("density5=").append(density5).append(", ");
		stringbuffer.append("voc5=").append(voc5).append(", ");
		stringbuffer.append("voctot5=").append(voctot5).append(", ");
		stringbuffer.append("voc=").append(voc).append(", ");
		stringbuffer.append("dob=").append(dob).append(", ");
		stringbuffer.append("hrsOfOperation=").append(hrsOfOperation)
				.append(", ");
		stringbuffer.append("isExemptedByDec=").append(isExemptedByDec)
				.append(", ");
		stringbuffer.append("isUnitIncludedInDecPermit=")
				.append(isUnitIncludedInDecPermit).append(", ");
		stringbuffer.append("location=").append(location).append(", ");
		stringbuffer.append("isDepPermitStatus=").append(isDepPermitStatus)
				.append(", ");
		stringbuffer.append("isDobPermitStatus=").append(isDobPermitStatus)
				.append(", ");
		stringbuffer.append("isAnnualPermitStatus=")
				.append(isAnnualPermitStatus).append(", ");
		stringbuffer.append("lastInspectionDate=").append(lastInspectionDate)
				.append(", ");
		stringbuffer.append("nextInspectionDate=").append(nextInspectionDate);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.FumehoodVo.class);
	protected int id;
	protected int buildingId;
	protected int stackId;
	protected StackVo stackVo;
	protected String facilityDesignatedId;
	protected String floor;
	protected String yearinstalled;
	protected int status;
	protected String disconnecteddate;
	protected String make;
	protected String model;
	protected String chemical1;
	protected String chemicalname1;
	protected String volume1;
	protected String density1;
	protected String voc1;
	protected String voctot1;
	protected String chemical2;
	protected String chemicalname2;
	protected String volume2;
	protected String density2;
	protected String voc2;
	protected String voctot2;
	protected String chemical3;
	protected String chemicalname3;
	protected String volume3;
	protected String density3;
	protected String voc3;
	protected String voctot3;
	protected String chemical4;
	protected String chemicalname4;
	protected String volume4;
	protected String density4;
	protected String voc4;
	protected String voctot4;
	protected String chemical5;
	protected String chemicalname5;
	protected String volume5;
	protected String density5;
	protected String voc5;
	protected String voctot5;
	protected String voc;
	protected String dob;
	protected String hrsOfOperation;
	protected boolean isExemptedByDec;
	protected boolean isUnitIncludedInDecPermit;
	protected int location;
	protected List permitInfoList;
	protected List chemicalsUsedList;
	protected String FumehoodHoursOfOperation1;
	protected String FumehoodDEPNumber;
	protected String fcomments;
	protected String dobsignoff;
	protected String isDepPermitStatus;
	protected String isDobPermitStatus;
	protected String isAnnualPermitStatus;
	protected String lastInspectionDate;
	protected String nextInspectionDate;
}