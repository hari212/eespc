package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.IncineratorEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.bo:
//            StackVo

public class IncineratorVo implements Serializable {

	public IncineratorVo() {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		location = -99;
		stackVo = null;
		incType = -99;
		make = null;
		model = null;
		floor = null;
		serialNo = null;
		yearInstalled = null;
		status = -99;
		disconnecteddate = null;
		burnerCapacity = null;
		burnerMake = null;
		burnerModel = null;
		mea = null;
		wasteBurned = -99;
		stackFrom = -99;
		capacity = null;
		isDOBApproved = false;
		isScrubberInst = false;
		isCOMonitor = null;
		isOpacityInstalled = null;
		isO2Installed = null;
		isOpacityChartAvailable = false;
		isCGARequired = false;
		permitInfoList = null;
		stackTestList = null;
		chemicalsUsedList = null;
		consumptionList = null;
		typeofunit = null;
		dobno = null;
		teperaturerequired = null;
		facilitysecondary = null;
		facilityprimary = null;
		primarytemp = null;
		sectemp = null;
		deprequired = null;
		depno = null;
		decno = null;
		incStackTested = null;
		i_decPermitObtained = null;
		protocolSubmitted = null;
		testConductedBy = null;
		testReportSubmited = null;
		testReportSubmitedDate = null;
		retestPlanned = null;
		i_testPassed = null;
		i_StackTestDate = null;
		nextStackTest = null;
		nextStackTestNote = null;
		B_parameters1 = null;
		B_parameters2 = null;
		B_parameters3 = null;
		B_parameters4 = null;
		B_parameters5 = null;
		B_parameters6 = null;
		B_parameters7 = null;
		B_parameters8 = null;
		B_parameters9 = null;
		iwastequantity = null;
		isolidwaste = null;
		i_SolidIssuedDate = null;
		i_SolidExpirationDate = null;
		icomments = null;
		iwastelimit = null;
		fuelConsumptionList = null;
		dobsignoff = null;

	}

	public IncineratorVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		location = -99;
		stackVo = null;
		incType = -99;
		make = null;
		model = null;
		floor = null;
		serialNo = null;
		yearInstalled = null;
		status = -99;
		disconnecteddate = null;
		burnerCapacity = null;
		burnerMake = null;
		burnerModel = null;
		mea = null;
		wasteBurned = -99;
		stackFrom = -99;
		capacity = null;
		isDOBApproved = false;
		isScrubberInst = false;
		isCOMonitor = null;
		isOpacityInstalled = null;
		isO2Installed = null;
		isOpacityChartAvailable = false;
		isCGARequired = false;
		permitInfoList = null;
		stackTestList = null;
		chemicalsUsedList = null;
		consumptionList = null;
		typeofunit = null;
		dobno = null;
		teperaturerequired = null;
		facilitysecondary = null;
		facilityprimary = null;
		primarytemp = null;
		sectemp = null;
		deprequired = null;
		depno = null;
		decno = null;
		incStackTested = null;
		i_decPermitObtained = null;
		protocolSubmitted = null;
		testConductedBy = null;
		testReportSubmited = null;
		testReportSubmitedDate = null;
		retestPlanned = null;
		i_testPassed = null;
		i_StackTestDate = null;
		nextStackTest = null;
		nextStackTestNote = null;
		B_parameters1 = null;
		B_parameters2 = null;
		B_parameters3 = null;
		B_parameters4 = null;
		B_parameters5 = null;
		B_parameters6 = null;
		B_parameters7 = null;
		B_parameters8 = null;
		B_parameters9 = null;
		iwastequantity = null;
		isolidwaste = null;
		i_SolidIssuedDate = null;
		i_SolidExpirationDate = null;
		icomments = null;
		iwastelimit = null;
		fuelConsumptionList = null;
		dobsignoff = null;

		id = resultset.getInt("INCINERATORCREMATORIESID");
		buildingId = resultset.getInt("BUILDINGID");
		String s = resultset.getString("TYPEOFWASTEBURNED");
		if (UtilityObject.isNotEmpty(s))
			wasteBurned = resultset.getInt("TYPEOFWASTEBURNED");
		s = resultset.getString("LOCATION");
		if (UtilityObject.isNotEmpty(s))
			location = resultset.getInt("LOCATION");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		incType = resultset.getInt("TYPE");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		floor = resultset.getString("FLOOR");
		serialNo = resultset.getString("SERIAL");
		capacity = resultset.getString("CAPACITY");
		yearInstalled = resultset.getString("YEARINSTALLED");
		status = resultset.getInt("STATUS");
		disconnecteddate = resultset.getString("DISCONNECTEDDATE");
		stackFrom = resultset.getInt("STACKFROM");
		burnerCapacity = resultset.getString("BURNERCAPACITY");
		burnerMake = resultset.getString("BURNERMAKE");
		burnerModel = resultset.getString("BURNERMODEL");
		mea = resultset.getString("MEA");
		s = resultset.getString("SCRUBBERINSTALLED");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isScrubberInst = true;
		s = resultset.getString("DOBAPPROVAL");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isDOBApproved = true;

		s = resultset.getString("COMONINSTALLED");

		isCOMonitor = s;
		s = resultset.getString("OPACITYMONINSTALLED");

		isOpacityInstalled = s;
		s = resultset.getString("OXYGENMONINSTALLED");

		isO2Installed = s;

		s = resultset.getString("CHARTRECORDERAVAILABLE");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isOpacityChartAvailable = true;
		s = resultset.getString("QUARTERLYCGARQD");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isCGARequired = true;

		typeofunit = resultset.getString("TYPEOFUNIT");
		dobno = resultset.getString("DOBNO");
		teperaturerequired = resultset.getString("TEPERATUREREQUIRED");
		facilitysecondary = resultset.getString("FACILITYSECONDARY");
		facilityprimary = resultset.getString("FACILITYPRIMARY");
		primarytemp = resultset.getString("PRIMARYTEMP");
		sectemp = resultset.getString("SECTEMP");
		deprequired = resultset.getString("DEPREQUIRED");
		depno = resultset.getString("DEPNO");
		decno = resultset.getString("DECNO");
		incStackTested = resultset.getString("INCSTACKTESTED");
		i_decPermitObtained = resultset.getString("I_DECPERMITOBTAINED");
		protocolSubmitted = resultset.getString("PROTOCOLSUBMITTED");
		testConductedBy = resultset.getString("TESTCONDUCTEDBY");
		testReportSubmited = resultset.getString("TESTREPORTSUBMITED");
		testReportSubmitedDate = resultset.getString("TESTREPORTSUBMITEDDATE");
		retestPlanned = resultset.getString("RETESTPLANNED");
		i_testPassed = resultset.getString("I_TESTPASSED");
		i_StackTestDate = resultset.getString("I_STACKTESTDATE");
		nextStackTest = resultset.getString("NEXTSTACKTEST");
		nextStackTestNote = resultset.getString("NEXTSTACKTESTNOTE");
		B_parameters1 = resultset.getString("B_PARAMETERS1");
		B_parameters2 = resultset.getString("B_PARAMETERS2");
		B_parameters3 = resultset.getString("B_PARAMETERS3");
		B_parameters4 = resultset.getString("B_PARAMETERS4");
		B_parameters5 = resultset.getString("B_PARAMETERS5");
		B_parameters6 = resultset.getString("B_PARAMETERS6");
		B_parameters7 = resultset.getString("B_PARAMETERS7");
		B_parameters8 = resultset.getString("B_PARAMETERS8");
		B_parameters9 = resultset.getString("B_PARAMETERS9");
		iwastequantity = resultset.getString("IWASTEQUANTITY");
		isolidwaste = resultset.getString("ISOLIDWASTE");
		i_SolidIssuedDate = resultset.getString("I_SOLIDISSUEDDATE");
		i_SolidExpirationDate = resultset.getString("I_SOLIDEXPIRATIONDATE");
		solidWasteExpirationNote = resultset
				.getString("SOLIDWASTEEXPIRATIONNOTE");
		icomments = resultset.getString("ICOMMENTS");
		iwastelimit = resultset.getString("IWASTELIMIT");
		dobsignoff = resultset.getString("DOBSIGNOFF");
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getTypeofunit() {
		return typeofunit;
	}

	public void setTypeofunit(String s) {
		typeofunit = s;
	}

	public String getDobno() {
		return dobno;
	}

	public void setDobno(String s) {
		dobno = s;
	}

	public String getTeperaturerequired() {
		return teperaturerequired;
	}

	public void setTeperaturerequired(String s) {
		teperaturerequired = s;
	}

	public String getFacilitysecondary() {
		return facilitysecondary;
	}

	public void setFacilitysecondary(String s) {
		facilitysecondary = s;
	}

	public String getFacilityprimary() {
		return facilityprimary;
	}

	public void setFacilityprimary(String s) {
		facilityprimary = s;
	}

	public String getPrimarytemp() {
		return primarytemp;
	}

	public void setPrimarytemp(String s) {
		primarytemp = s;
	}

	public String getSectemp() {
		return sectemp;
	}

	public void setSectemp(String s) {
		sectemp = s;
	}

	public String getDeprequired() {
		return deprequired;
	}

	public void setDeprequired(String s) {
		deprequired = s;
	}

	public String getDepno() {
		return depno;
	}

	public void setDepno(String s) {
		depno = s;
	}

	public String getDecno() {
		return decno;
	}

	public void setDecno(String s) {
		decno = s;
	}

	public String getIncStackTested() {
		return incStackTested;
	}

	public void setIncStackTested(String s) {
		incStackTested = s;
	}

	public String getI_decPermitObtained() {
		return i_decPermitObtained;
	}

	public void setI_decPermitObtained(String s) {
		i_decPermitObtained = s;
	}

	public String getProtocolSubmitted() {
		return protocolSubmitted;
	}

	public void setProtocolSubmitted(String s) {
		protocolSubmitted = s;
	}

	public String getTestConductedBy() {
		return testConductedBy;
	}

	public void setTestConductedBy(String s) {
		testConductedBy = s;
	}

	public String getTestReportSubmited() {
		return testReportSubmited;
	}

	public void setTestReportSubmited(String s) {
		testReportSubmited = s;
	}

	public String getTestReportSubmitedDate() {
		return testReportSubmitedDate;
	}

	public void setTestReportSubmitedDate(String s) {
		testReportSubmitedDate = s;
	}

	public String getRetestPlanned() {
		return retestPlanned;
	}

	public void setRetestPlanned(String s) {
		retestPlanned = s;
	}

	public String getI_testPassed() {
		return i_testPassed;
	}

	public void setI_testPassed(String s) {
		i_testPassed = s;
	}

	public String getI_StackTestDate() {
		return i_StackTestDate;
	}

	public void setI_StackTestDate(String s) {
		i_StackTestDate = s;
	}

	public String getNextStackTest() {
		return nextStackTest;
	}

	public void setNextStackTest(String s) {
		nextStackTest = s;
	}

	public String getNextStackTestNote() {
		return nextStackTestNote;
	}

	public void setNextStackTestNote(String s) {
		nextStackTestNote = s;
	}

	public String getB_parameters1() {
		return B_parameters1;
	}

	public void setB_parameters1(String s) {
		B_parameters1 = s;
	}

	public String getB_parameters2() {
		return B_parameters2;
	}

	public void setB_parameters2(String s) {
		B_parameters2 = s;
	}

	public String getB_parameters3() {
		return B_parameters3;
	}

	public void setB_parameters3(String s) {
		B_parameters3 = s;
	}

	public String getB_parameters4() {
		return B_parameters4;
	}

	public void setB_parameters4(String s) {
		B_parameters4 = s;
	}

	public String getB_parameters5() {
		return B_parameters5;
	}

	public void setB_parameters5(String s) {
		B_parameters5 = s;
	}

	public String getB_parameters6() {
		return B_parameters6;
	}

	public void setB_parameters6(String s) {
		B_parameters6 = s;
	}

	public String getB_parameters7() {
		return B_parameters7;
	}

	public void setB_parameters7(String s) {
		B_parameters7 = s;
	}

	public String getB_parameters8() {
		return B_parameters8;
	}

	public void setB_parameters8(String s) {
		B_parameters8 = s;
	}

	public String getB_parameters9() {
		return B_parameters9;
	}

	public void setB_parameters9(String s) {
		B_parameters9 = s;
	}

	public String getIwastequantity() {
		return iwastequantity;
	}

	public void setIwastequantity(String s) {
		iwastequantity = s;
	}

	public String getIsolidwaste() {
		return isolidwaste;
	}

	public void setIsolidwaste(String s) {
		isolidwaste = s;
	}

	public String getI_SolidIssuedDate() {
		return i_SolidIssuedDate;
	}

	public void setI_SolidIssuedDate(String s) {
		i_SolidIssuedDate = s;
	}

	public String getI_SolidExpirationDate() {
		return i_SolidExpirationDate;
	}

	public void setI_SolidExpirationDate(String s) {
		i_SolidExpirationDate = s;
	}

	public String getSolidWasteExpirationNote() {
		return solidWasteExpirationNote;
	}

	public void setSolidWasteExpirationNote(String s) {
		solidWasteExpirationNote = s;
	}

	public String getIcomments() {
		return icomments;
	}

	public void setIcomments(String s) {
		icomments = s;
	}

	public String getIwastelimit() {
		return iwastelimit;
	}

	public void setIwastelimit(String s) {
		iwastelimit = s;
	}

	// ///////////
	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String s) {
		make = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getStackFrom() {
		return stackFrom;
	}

	public void setStackFrom(int i) {
		stackFrom = i;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int i) {
		location = i;
	}

	public int getWasteBurned() {
		return wasteBurned;
	}

	public void setWasteBurned(int i) {
		wasteBurned = i;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
	}

	public int getType() {
		return incType;
	}

	public void setType(int i) {
		incType = i;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String s) {
		floor = s;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String s) {
		serialNo = s;
	}

	public void setCapacity(String s) {
		capacity = s;
	}

	public String getCapacity() {
		return capacity;
	}

	public String getYearInstalled() {
		return yearInstalled;
	}

	public void setYearInstalled(String s) {
		yearInstalled = s;
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

	public String getBurnerCapacity() {
		return burnerCapacity;
	}

	public void setBurnerCapacity(String s) {
		burnerCapacity = s;
	}

	public String getBurnerMake() {
		return burnerMake;
	}

	public void setBurnerMake(String s) {
		burnerMake = s;
	}

	public String getMEA() {
		return mea;
	}

	public void setMEA(String s) {
		mea = s;
	}

	public String getBurnerModel() {
		return burnerModel;
	}

	public void setBurnerModel(String s) {
		burnerModel = s;
	}

	public boolean isScrubberInst() {
		return isScrubberInst;
	}

	public void setScrubberInst(boolean flag) {
		isScrubberInst = flag;
	}

	public String isCOMonitor() {
		return isCOMonitor;
	}

	public void setCOMonitor(String flag) {
		isCOMonitor = flag;
	}

	public String isOpacityInstalled() {
		return isOpacityInstalled;
	}

	public void setOpacityInstalled(String flag) {
		isOpacityInstalled = flag;
	}

	public String isO2Installed() {
		return isO2Installed;
	}

	public void setO2Installed(String flag) {
		isO2Installed = flag;
	}

	public boolean isOpacityChartAvailable() {
		return isOpacityChartAvailable;
	}

	public void setOpacityChartAvailable(boolean flag) {
		isOpacityChartAvailable = flag;
	}

	public boolean isDOBApproved() {
		return isDOBApproved;
	}

	public void setDOBApproved(boolean flag) {
		isDOBApproved = flag;
	}

	public boolean isCGARequired() {
		return isCGARequired;
	}

	public void setCGARequired(boolean flag) {
		isCGARequired = flag;
	}

	public StackVo getStackVo() {
		if (getStackFrom() > 0)
			try {
				stackVo = StackEntity.findByPrimaryKey(getStackFrom());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return stackVo;
	}

	public void setStackVo(StackVo stackvo) {
		stackVo = stackvo;
	}

	public List getStackTestList() {
		if (stackTestList == null) {
			log.debug((new StringBuilder())
					.append("Stack Test Info List is NULL and ID = ")
					.append(getId()).toString());
			try {
				stackTestList = IncineratorEntity.getStackTestInfo(getId());
				log.debug((new StringBuilder()).append("Stack size  = ")
						.append(stackTestList.size()).toString());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		}
		return stackTestList;
	}

	public void setStackTestList(List list) {
		stackTestList = list;
	}

	public List getConsumptionList() {
		if (consumptionList == null) {
			log.debug((new StringBuilder())
					.append("Consum Info List is NULL and ID = ")
					.append(getId()).toString());
			try {
				consumptionList = IncineratorEntity.getWasteConsum(getId());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		}
		return consumptionList;
	}

	public void setConsumptionList(List list) {
		consumptionList = list;
	}

	public List getPermitInfoList() {
		if (permitInfoList == null) {
			log.debug((new StringBuilder())
					.append("Permit Info List is NULL and ID = ")
					.append(getId()).toString());
			try {
				permitInfoList = IncineratorEntity.getPermitInfo(getId());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		}
		return permitInfoList;
	}

	public void setPermitInfoList(List list) {
		permitInfoList = list;
	}

	// ////////////////
	public List getFuelConsumptionList() {
		if (fuelConsumptionList == null)
			try {
				fuelConsumptionList = IncineratorEntity
						.getFuelConsumptionList(getId());
			} catch (Exception exception) {
				System.out.println("Incinirator Vo Exception:" + exception);
				log.error(exception);
			}
		return fuelConsumptionList;
	}

	public void setFuelConsumptionList(List list) {
		fuelConsumptionList = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id);
		stringbuffer.append(",buildingId =").append(buildingId);
		stringbuffer.append(",facilityDesignatedId =").append(
				facilityDesignatedId);
		stringbuffer.append(",incType =").append(incType);
		stringbuffer.append(",location =").append(location);
		stringbuffer.append(",make =").append(make);
		stringbuffer.append(",model =").append(model);
		stringbuffer.append(",capacity =").append(capacity);
		stringbuffer.append(",floor =").append(floor);
		stringbuffer.append(",serialNo=").append(serialNo);
		stringbuffer.append(",yearInstalled=").append(yearInstalled);
		stringbuffer.append(",status=").append(status);
		stringbuffer.append(",disconnecteddate=").append(disconnecteddate);
		stringbuffer.append(",burnerCapacity=").append(burnerCapacity);
		stringbuffer.append(",burnerMake=").append(burnerMake);
		stringbuffer.append(",burnerModel=").append(burnerModel);
		stringbuffer.append(",stackFrom=").append(stackFrom);
		stringbuffer.append(",mea=").append(mea);
		stringbuffer.append(",wasteBurned=").append(wasteBurned);
		stringbuffer.append(",capacity=").append(capacity);
		stringbuffer.append(",isDOBApproved=").append(isDOBApproved);
		stringbuffer.append(",isScrubberInst=").append(isScrubberInst);
		stringbuffer.append(",isCOMonitor=").append(isCOMonitor);
		stringbuffer.append(",isOpacityInstalled=").append(isOpacityInstalled);
		stringbuffer.append(",isO2Installed=").append(isO2Installed);
		stringbuffer.append(",isOpacityChartAvailable=").append(
				isOpacityChartAvailable);
		stringbuffer.append(",isCGARequired=").append(isCGARequired);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.IncineratorVo.class);
	protected int id;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected int location;
	protected StackVo stackVo;
	protected int incType;
	protected String make;
	protected String model;
	protected String floor;
	protected String serialNo;
	protected String yearInstalled;
	protected int status;
	protected String disconnecteddate;
	protected String burnerCapacity;
	protected String burnerMake;
	protected String burnerModel;
	protected String mea;
	protected int wasteBurned;
	protected int stackFrom;
	protected String capacity;
	protected boolean isDOBApproved;
	protected boolean isScrubberInst;
	protected String isCOMonitor;
	protected String isOpacityInstalled;
	protected String isO2Installed;
	protected boolean isOpacityChartAvailable;
	protected boolean isCGARequired;
	protected List permitInfoList;
	protected List stackTestList;
	protected List chemicalsUsedList;
	protected List consumptionList;

	protected String typeofunit;
	protected String dobno;
	protected String teperaturerequired;
	protected String facilitysecondary;
	protected String facilityprimary;
	protected String primarytemp;
	protected String sectemp;
	protected String deprequired;
	protected String depno;
	protected String decno;
	protected String incStackTested;
	protected String i_decPermitObtained;
	protected String protocolSubmitted;
	protected String testConductedBy;
	protected String testReportSubmited;
	protected String testReportSubmitedDate;
	protected String retestPlanned;
	protected String i_testPassed;
	protected String i_StackTestDate;
	protected String nextStackTest;
	protected String nextStackTestNote;
	protected String B_parameters1;
	protected String B_parameters2;
	protected String B_parameters3;
	protected String B_parameters4;
	protected String B_parameters5;
	protected String B_parameters6;
	protected String B_parameters7;
	protected String B_parameters8;
	protected String B_parameters9;
	protected String iwastequantity;
	protected String isolidwaste;
	protected String i_SolidIssuedDate;
	protected String i_SolidExpirationDate;
	protected String solidWasteExpirationNote;
	protected String icomments;
	protected String iwastelimit;
	protected List fuelConsumptionList;
	protected String dobsignoff;

}