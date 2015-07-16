package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.EtoEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class EtoVo implements Serializable {

	public EtoVo() {
		buildingId = -99;
		testList = null;
		permitList = null;
		stackId = -99;
		stackObject = null;
		disconnectedyear = null;
		modifiedinpast = -99;
		isabator = null;
		hrs = null;
		days = null;
		A_decPermitObtained = null;
		A_stackTestProtSubmited = null;
		A_testConductedBy = null;
		A_testReportSubmited = null;
		A_permitdate = null;
		A_compyWithETO = null;
		amanufacturer = null;
		amodel = null;
		A_testPassed = null;
		dobsignoff = null;
		etoDOB = null;
		stackTestRequire = null;
	}

	public EtoVo(ResultSet resultset) throws SQLException {
		buildingId = -99;
		testList = null;
		permitList = null;
		stackId = -99;
		stackObject = null;
		modifiedinpast = -99;
		disconnectedyear = null;
		isabator = null;
		hrs = null;
		days = null;
		A_decPermitObtained = null;
		A_stackTestProtSubmited = null;
		A_testConductedBy = null;
		A_testReportSubmited = null;
		A_permitdate = null;
		A_compyWithETO = null;
		amanufacturer = null;
		amodel = null;
		A_testPassed = null;
		dobsignoff = null;
		etoDOB = null;
		stackTestRequire = null;

		etoId = resultset.getInt("ETOID");
		buildingId = resultset.getInt("BUILDINGID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		manufacturer = resultset.getString("MANUFACTURER");
		model = resultset.getString("MODEL");
		serial = resultset.getString("SERIAL");
		volume = resultset.getString("VOLUME");
		mixtureType = resultset.getInt("MIXTURETYPE");
		containerWeight = resultset.getInt("CONTAINERWEIGHT");
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		gasMixture = resultset.getInt("GASMIXTURE");
		installationDate = UtilityObject.convertToString(resultset
				.getDate("INSTALLATIONDATE"));
		isRecordsAvailable = UtilityObject.convertBoolean(resultset
				.getString("ISRECORDSAVAILABLE"));
		comments = resultset.getString("ETCOMMENTS");
		dep = resultset.getString("DEP");
		dob = resultset.getString("DOB");
		actiontaken = resultset.getString("ACTIONTAKEN");
		statePermit = resultset.getString("STATEPERMIT");
		stackId = resultset.getInt("STACKFROM");
		isabator = resultset.getString("ISABATOR");
		hrs = resultset.getString("HRS");
		days = resultset.getString("DAYS");
		A_decPermitObtained = resultset.getString("A_DECPERMITOBTAINED");
		A_stackTestProtSubmited = resultset
				.getString("A_STACKTESTPROTSUBMITED");
		A_testConductedBy = resultset.getString("A_TESTCONDUCTEDBY");
		A_testReportSubmited = resultset.getString("A_TESTREPORTSUBMITED");
		A_permitdate = resultset.getString("A_PERMITDATE");
		A_compyWithETO = resultset.getString("A_COMPYWITHETO");
		amanufacturer = resultset.getString("AMANUFACTURER");
		amodel = resultset.getString("AMODEL");
		A_testPassed = resultset.getString("A_TESTPASSED");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		etoDOB = resultset.getString("ETODOB");
		stackTestRequire = resultset.getString("STACKTESTREQUIRE");
	}

	public String getStackTestRequire() {
		return stackTestRequire;
	}

	public void setStackTestRequire(String s) {
		stackTestRequire = s;
	}

	public String getEtoDob() {
		return etoDOB;
	}

	public void setEtoDob(String s) {
		etoDOB = s;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getIsabator() {
		return isabator;
	}

	public void setIsabator(String s) {
		isabator = s;
	}

	public String getHrs() {
		return hrs;
	}

	public void setHrs(String s) {
		hrs = s;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String s) {
		days = s;
	}

	public String getA_decPermitObtained() {
		return A_decPermitObtained;
	}

	public void setA_decPermitObtained(String s) {
		A_decPermitObtained = s;
	}

	public String getA_stackTestProtSubmited() {
		return A_stackTestProtSubmited;
	}

	public void setA_stackTestProtSubmited(String s) {
		A_stackTestProtSubmited = s;
	}

	public String getA_testConductedBy() {
		return A_testConductedBy;
	}

	public void setA_testConductedBy(String s) {
		A_testConductedBy = s;
	}

	public String getA_testReportSubmited() {
		return A_testReportSubmited;
	}

	public void setA_testReportSubmited(String s) {
		A_testReportSubmited = s;
	}

	public String getA_permitdate() {
		return A_permitdate;
	}

	public void setA_permitdate(String s) {
		A_permitdate = s;
	}

	public String getA_compyWithETO() {
		return A_compyWithETO;
	}

	public void setA_compyWithETO(String s) {
		A_compyWithETO = s;
	}

	public String getAmanufacturer() {
		return amanufacturer;
	}

	public void setAmanufacturer(String s) {
		amanufacturer = s;
	}

	public String getAmodel() {
		return amodel;
	}

	public void setAmodel(String s) {
		amodel = s;
	}

	public String getA_testPassed() {
		return A_testPassed;
	}

	public void setA_testPassed(String s) {
		A_testPassed = s;
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

	public int getContainerWeight() {
		return containerWeight;
	}

	public void setContainerWeight(int i) {
		containerWeight = i;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String s) {
		dep = s;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String s) {
		dob = s;
	}

	public String getActiontaken() {
		return actiontaken;
	}

	public void setActiontaken(String s) {
		actiontaken = s;
	}

	public int getEtoId() {
		return etoId;
	}

	public void setEtoId(int i) {
		etoId = i;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public int getGasMixture() {
		return gasMixture;
	}

	public void setGasMixture(int i) {
		gasMixture = i;
	}

	public String getInstallationDate() {
		return installationDate;
	}

	public void setInstallationDate(String s) {
		installationDate = s;
	}

	public boolean isRecordsAvailable() {
		return isRecordsAvailable;
	}

	public void setRecordsAvailable(boolean flag) {
		isRecordsAvailable = flag;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String s) {
		manufacturer = s;
	}

	public int getMixtureType() {
		return mixtureType;
	}

	public void setMixtureType(int i) {
		mixtureType = i;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
	}

	public List getPermitList() {
		if (permitList == null)
			try {
				permitList = EtoEntity.getPermitList(getEtoId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return permitList;
	}

	public void setPermitList(List list) {
		permitList = list;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String s) {
		serial = s;
	}

	public String getStatePermit() {
		return statePermit;
	}

	public void setStatePermit(String s) {
		statePermit = s;
	}

	public List getTestList() {
		if (testList == null)
			try {
				testList = EtoEntity.getTestList(getEtoId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return testList;
	}

	public void setTestList(List list) {
		testList = list;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String s) {
		volume = s;
	}

	public int getStackId() {
		return stackId;
	}

	public void setStackId(int i) {
		stackId = i;
	}

	public StackVo getStackObject() {
		if (stackObject == null && getStackId() > 0)
			try {
				stackObject = StackEntity.findByPrimaryKey(getStackId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return stackObject;
	}

	public void setStackObject(StackVo stackvo) {
		stackObject = stackvo;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ",";
		stringbuffer.append("etoId  =").append(etoId).append(s);
		stringbuffer.append("buildingId  =").append(buildingId).append(s);
		stringbuffer.append("facilityDesignatedId  =")
				.append(facilityDesignatedId).append(s);
		stringbuffer.append("manufacturer  =").append(manufacturer).append(s);
		stringbuffer.append("model  =").append(model).append(s);
		stringbuffer.append("serial  =").append(serial).append(s);
		stringbuffer.append("volume  =").append(volume).append(s);
		stringbuffer.append("mixtureType  =").append(mixtureType).append(s);
		stringbuffer.append("containerWeight  =").append(containerWeight)
				.append(s);
		stringbuffer.append("gasMixture  =").append(gasMixture).append(s);
		stringbuffer.append("installationDate  =").append(installationDate)
				.append(s);
		stringbuffer.append("isRecordsAvailable  =").append(isRecordsAvailable)
				.append(s);
		stringbuffer.append("comments  =").append(comments).append(s);
		stringbuffer.append("dep  =").append(dep).append(s);
		stringbuffer.append("dob  =").append(dob).append(s);
		stringbuffer.append("etoDOB  =").append(etoDOB).append(s);
		stringbuffer.append("stackTestRequire =").append(stackTestRequire)
				.append(s);
		stringbuffer.append("actiontaken  =").append(actiontaken).append(s);
		stringbuffer.append("disconnectedyear =").append(disconnectedyear)
				.append(s);
		stringbuffer.append("modifiedinpast =").append(modifiedinpast)
				.append(s);
		stringbuffer.append("stackId  =").append(stackId).append(s);
		stringbuffer.append("statePermit  =").append(statePermit);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.EtoVo.class);
	protected int etoId;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected String manufacturer;
	protected String model;
	protected String serial;
	protected String volume;
	protected int mixtureType;
	protected int containerWeight;
	protected int gasMixture;
	protected String installationDate;
	protected boolean isRecordsAvailable;
	protected String comments;
	protected String dep;
	protected String dob;
	protected String actiontaken;
	protected String statePermit;
	protected List testList;
	protected List permitList;
	protected int stackId;
	protected StackVo stackObject;
	protected String disconnectedyear;
	protected int modifiedinpast;
	protected String isabator;
	protected String hrs;
	protected String days;
	protected String A_decPermitObtained;
	protected String A_stackTestProtSubmited;
	protected String A_testConductedBy;
	protected String A_testReportSubmited;
	protected String A_permitdate;
	protected String A_compyWithETO;
	protected String amanufacturer;
	protected String amodel;
	protected String A_testPassed;
	protected String dobsignoff;
	protected String etoDOB;
	protected String stackTestRequire;
}