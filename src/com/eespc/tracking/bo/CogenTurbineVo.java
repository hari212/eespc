package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.myenum.CogenTurbineTypeEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.entity.CogenTurbineEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;

public class CogenTurbineVo implements Serializable {

	public CogenTurbineVo() {
		id = -99;
		buildingId = -99;
		buildingObj = null;
		facilityDesignatedId = null;
		location = -99;
		decId = null;
		resourceType = -99;
		floor = null;
		primaryUse = -99;
		hasControlSystem = false;
		controlEfficiency = -99;
		yearInstalled = null;
		status = -99;
		disconnecteddate = null;
		manufacturer = null;
		make = null;
		model = null;
		serialNumber = null;
		burnerMake = null;
		burnerModel = null;
		burnerType = -99;
		capacity = null;
		primaryFuel = -99;
		secondaryFuel = -99;
		fuelFromTank = -99;
		fuelFromTankObj = null;

		mea = null;
		depNumber = null;
		sechedule = false;
		planApproval = false;
		isRecordsMaintained = false;
		isTestProtocolSubmitted = false;
		testContductedBy = null;
		isTestReportSubmitted = false;
		testReportSubmittedDate = null;
		isComplyWithNoxPlan = false;
		isRetestPlanned = false;
		nextStackTestDate = null;
		nextStackTestNote = null;
		stackExhaustId = -99;
		stackObj = null;
		isFuleCappingUnderLimit = false;
		resourceName = null;
		permitList = null;
		fuelConsumptionList = null;
		o_fuelConsumptionList = null;
		isturbine = null;
		tmanufacturer = null;
		tmake = null;
		tmodel = null;
		tserial = null;
		tburnerMake = null;
		tburnerModel = null;
		tcapacity = null;
		// thpText=null;
		// tmmbtuText=null;
		tburnerType = null;
		tprimaryFuel = -99;
		tsecondaryFuel = -99;
		// toilFiringRate=null;
		// tnaturalGasFiringRate=null;
		iswhrb = null;
		wmanufacturer = null;
		wmake = null;
		wmodel = null;
		wserial = null;
		wburnerMake = null;
		wburnerModel = null;
		wcapacity = null;
		// whpText=null;
		// wmmbtuText=null;
		wburnerType = null;
		wprimaryFuel = -99;
		wsecondaryFuel = -99;
		// woilFiringRate=null;
		// wnaturalGasFiringRate=null;
		co_decPermitObtained = null;
		co_depPermitObtained = null;
		co_compyWithSO2RactPlan = null;
		co_compyWithPMRactPlan = null;
		co_testPassed = null;
		co_StackTestDate = null;
		co_fuelgapping = null;
		co_gasfuelgapping = null;
		co_gasemmisionfactor = null;
		co_oilfuelgapping = null;
		co_oilemmisionfactor = null;
		co_Comments = null;
		co_oilso2 = null;
		co_gasso2 = null;
		co_so2allowable = null;
		co_noxallowable = null;
		nycdob = null;
		dobsignoff = null;
		dobfiling = null;

	}

	public CogenTurbineVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		buildingObj = null;
		facilityDesignatedId = null;
		location = -99;
		decId = null;
		resourceType = -99;
		floor = null;
		primaryUse = -99;
		hasControlSystem = false;
		controlEfficiency = -99;
		yearInstalled = null;
		status = -99;
		disconnecteddate = null;
		manufacturer = null;
		make = null;
		model = null;
		serialNumber = null;
		burnerMake = null;
		burnerModel = null;
		burnerType = -99;
		capacity = null;
		primaryFuel = -99;
		secondaryFuel = -99;
		fuelFromTank = -99;
		fuelFromTankObj = null;
		nycdob = null;
		mea = null;
		depNumber = null;
		sechedule = false;
		planApproval = false;
		isRecordsMaintained = false;
		isTestProtocolSubmitted = false;
		testContductedBy = null;
		isTestReportSubmitted = false;
		testReportSubmittedDate = null;
		isComplyWithNoxPlan = false;
		isRetestPlanned = false;
		nextStackTestDate = null;
		nextStackTestNote = null;
		stackExhaustId = -99;
		stackObj = null;
		isFuleCappingUnderLimit = false;
		resourceName = null;
		permitList = null;
		fuelConsumptionList = null;
		o_fuelConsumptionList = null;

		isturbine = null;
		tmanufacturer = null;
		tmake = null;
		tmodel = null;
		tserial = null;
		tburnerMake = null;
		tburnerModel = null;
		tcapacity = null;
		// thpText=null;
		// tmmbtuText=null;
		tburnerType = null;
		tprimaryFuel = -99;
		tsecondaryFuel = -99;
		// toilFiringRate=null;
		// tnaturalGasFiringRate=null;
		iswhrb = null;
		wmanufacturer = null;
		wmake = null;
		wmodel = null;
		wserial = null;
		wburnerMake = null;
		wburnerModel = null;
		wcapacity = null;
		// whpText=null;
		// wmmbtuText=null;
		wburnerType = null;
		wprimaryFuel = -99;
		wsecondaryFuel = -99;
		// woilFiringRate=null;
		// wnaturalGasFiringRate=null;
		co_decPermitObtained = null;
		co_depPermitObtained = null;
		co_compyWithSO2RactPlan = null;
		co_compyWithPMRactPlan = null;
		co_testPassed = null;
		co_StackTestDate = null;
		co_fuelgapping = null;
		co_gasfuelgapping = null;
		co_gasemmisionfactor = null;
		co_oilfuelgapping = null;
		co_oilemmisionfactor = null;
		co_Comments = null;
		co_oilso2 = null;
		co_gasso2 = null;
		co_so2allowable = null;
		co_noxallowable = null;
		dobsignoff = null;
		dobfiling = null;

		id = resultset.getInt("COGENTURBINEID");
		buildingId = resultset.getInt("BUILDINGID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		decId = resultset.getString("DECID");
		if (resultset.getString("RESOURCETYPE") != null)
			resourceType = resultset.getInt("RESOURCETYPE");
		floor = resultset.getString("FLOOR");
		if (resultset.getString("PRIMARYUSE") != null)
			primaryUse = resultset.getInt("PRIMARYUSE");
		String s = resultset.getString("HASCONTROLSYSTEM");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			hasControlSystem = true;

		if (resultset.getString("CONTROLEFFICIENCY") != null)
			controlEfficiency = resultset.getInt("CONTROLEFFICIENCY");
		yearInstalled = resultset.getString("YEARINSTALLED");
		status = resultset.getInt("STATUS");
		disconnecteddate = resultset.getString("DISCONNECTEDDATE");
		manufacturer = resultset.getString("MANUFACTURER");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		serialNumber = resultset.getString("SERIALNUMBER");
		burnerMake = resultset.getString("BURNERMAKE");
		burnerModel = resultset.getString("BURNERMODEL");
		if (resultset.getString("BURNERTYPE") != null)
			burnerType = resultset.getInt("BURNERTYPE");
		capacity = resultset.getString("CAPACITY");
		if (resultset.getString("PRIMARYFUEL") != null)
			primaryFuel = resultset.getInt("PRIMARYFUEL");
		if (resultset.getString("SECONDARYFUEL") != null)
			secondaryFuel = resultset.getInt("SECONDARYFUEL");
		if (resultset.getString("FUELFROMTANK") != null)
			fuelFromTank = resultset.getInt("FUELFROMTANK");
		nycdob = resultset.getString("NYCDOB");
		mea = resultset.getString("MEA");
		depNumber = resultset.getString("DEPNUMBER");
		s = resultset.getString("SCHEDULEC");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			sechedule = true;
		s = resultset.getString("PLANAPPROVAL");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			planApproval = true;
		s = resultset.getString("RECORDSMAINTAINED");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isRecordsMaintained = true;
		s = resultset.getString("STACTTESTPROTOCOLSUBMITTED");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isTestProtocolSubmitted = true;
		testContductedBy = resultset.getString("TESTCONDUCTBY");
		s = resultset.getString("TESTREPORTSUBMITTED");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isTestReportSubmitted = true;
		testReportSubmittedDate = resultset
				.getString("TESTREPORTSUBMITTEDDATE");
		s = resultset.getString("COMPYWITHNOX");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isComplyWithNoxPlan = true;
		s = resultset.getString("RETESTPLANNED");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isRetestPlanned = true;
		nextStackTestDate = resultset.getString("NEXTSTACKTESTDATE");
		nextStackTestNote = resultset.getString("NEXTSTACKTESTNOTE");
		if (resultset.getString("STACKEXHAUSTID") != null)
			stackExhaustId = resultset.getInt("STACKEXHAUSTID");
		s = resultset.getString("ISFUELCAPPING");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isFuleCappingUnderLimit = true;

		isturbine = resultset.getString("ISTURBINE");
		tmanufacturer = resultset.getString("TMANUFACTURER");
		tmake = resultset.getString("TMAKE");
		tmodel = resultset.getString("TMODEL");
		tserial = resultset.getString("TSERIAL");
		tburnerMake = resultset.getString("TBURNERMAKE");
		tburnerModel = resultset.getString("TBURNERMODEL");
		tcapacity = resultset.getString("TCAPACITY");
		// thpText=resultset.getString("THPTEXT");
		// tmmbtuText=resultset.getString("TMMBTUTEXT");
		tburnerType = resultset.getString("TBURNERTYPE");
		tprimaryFuel = resultset.getInt("TPRIMARYFUEL");
		tsecondaryFuel = resultset.getInt("TSECONDARYFUEL");
		// toilFiringRate=resultset.getString("TOILFIRINGRATE");
		// tnaturalGasFiringRate=resultset.getString("TNATURALGASFIRINGRATE");
		iswhrb = resultset.getString("ISWHRB");
		wmanufacturer = resultset.getString("WMANUFACTURER");
		wmake = resultset.getString("WMAKE");
		wmodel = resultset.getString("WMODEL");
		wserial = resultset.getString("WSERIAL");
		wburnerMake = resultset.getString("WBURNERMAKE");
		wburnerModel = resultset.getString("WBURNERMODEL");
		wcapacity = resultset.getString("WCAPACITY");
		// whpText=resultset.getString("WHPTEXT");
		// wmmbtuText=resultset.getString("WMMBTUTEXT");
		wburnerType = resultset.getString("WBURNERTYPE");
		wprimaryFuel = resultset.getInt("WPRIMARYFUEL");
		wsecondaryFuel = resultset.getInt("WSECONDARYFUEL");
		// woilFiringRate=resultset.getString("WOILFIRINGRATE");
		// wnaturalGasFiringRate=resultset.getString("WNATURALGASFIRINGRATE");
		co_decPermitObtained = resultset.getString("CO_DECPERMITOBTAINED");
		co_depPermitObtained = resultset.getString("CO_DEPPERMITOBTAINED");
		co_compyWithSO2RactPlan = resultset
				.getString("CO_COMPYWITHSO2RACTPLAN");
		co_compyWithPMRactPlan = resultset.getString("CO_COMPYWITHPMRACTPLAN");
		co_testPassed = resultset.getString("CO_TESTPASSED");
		co_StackTestDate = resultset.getString("CO_STACKTESTDATE");
		co_fuelgapping = resultset.getString("CO_FUELGAPPING");
		co_gasfuelgapping = resultset.getString("CO_GASFUELGAPPING");
		co_gasemmisionfactor = resultset.getString("CO_GASEMMISIONFACTOR");
		co_oilfuelgapping = resultset.getString("CO_OILFUELGAPPING");
		co_oilemmisionfactor = resultset.getString("CO_OILEMMISIONFACTOR");
		co_Comments = resultset.getString("CO_COMMENTS");
		co_oilso2 = resultset.getString("CO_OILSO2");
		co_gasso2 = resultset.getString("CO_GASSO2");
		co_so2allowable = resultset.getString("CO_SO2ALLOWABLE");
		co_noxallowable = resultset.getString("CO_NOXALLOWABLE");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		dobfiling = resultset.getString("DOBFILING");
	}

	public String getDobfiling() {
		return dobfiling;
	}

	public void setDobfiling(String s) {
		dobfiling = s;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getCo_oilso2() {
		return co_oilso2;
	}

	public void setCo_oilso2(String s) {
		co_oilso2 = s;
	}

	public String getCo_gasso2() {
		return co_gasso2;
	}

	public void setCo_gasso2(String s) {
		co_gasso2 = s;
	}

	public String getCo_so2allowable() {
		return co_so2allowable;
	}

	public void setCo_so2allowable(String s) {
		co_so2allowable = s;
	}

	public String getCo_noxallowable() {
		return co_noxallowable;
	}

	public void setCo_noxallowable(String s) {
		co_noxallowable = s;
	}

	public String getCo_Comments() {
		return co_Comments;
	}

	public void setCo_Comments(String s) {
		co_Comments = s;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public BuildingVo getBuildingObj() {
		return buildingObj;
	}

	public void setBuildingObj(BuildingVo buildingvo) {
		buildingObj = buildingvo;
	}

	public String getBurnerMake() {
		return burnerMake;
	}

	public void setBurnerMake(String s) {
		burnerMake = s;
	}

	public String getBurnerModel() {
		return burnerModel;
	}

	public void setBurnerModel(String s) {
		burnerModel = s;
	}

	public int getBurnerType() {
		return burnerType;
	}

	public void setBurnerType(int i) {
		burnerType = i;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String s) {
		capacity = s;
	}

	public int getControlEfficiency() {
		return controlEfficiency;
	}

	public void setControlEfficiency(int i) {
		controlEfficiency = i;
	}

	public String getDecId() {
		return decId;
	}

	public void setDecId(String s) {
		decId = s;
	}

	public String getDepNumber() {
		return depNumber;
	}

	public void setDepNumber(String s) {
		depNumber = s;
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

	public int getFuelFromTank() {
		return fuelFromTank;
	}

	public void setFuelFromTank(int i) {
		fuelFromTank = i;
	}

	public StorageTankVo getFuelFromTankObj() {
		if (fuelFromTankObj == null)
			try {
				fuelFromTankObj = StorageTankEntity
						.findByPrimaryKey(getFuelFromTank());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return fuelFromTankObj;
	}

	public void setFuelFromTankObj(StorageTankVo storagetankvo) {
		fuelFromTankObj = storagetankvo;
	}

	public boolean isHasControlSystem() {
		return hasControlSystem;
	}

	public void setHasControlSystem(boolean flag) {
		hasControlSystem = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isComplyWithNoxPlan() {
		return isComplyWithNoxPlan;
	}

	public void setComplyWithNoxPlan(boolean flag) {
		isComplyWithNoxPlan = flag;
	}

	public boolean isFuleCappingUnderLimit() {
		return isFuleCappingUnderLimit;
	}

	public void setFuleCappingUnderLimit(boolean flag) {
		isFuleCappingUnderLimit = flag;
	}

	public boolean isRecordsMaintained() {
		return isRecordsMaintained;
	}

	public void setRecordsMaintained(boolean flag) {
		isRecordsMaintained = flag;
	}

	public boolean isRetestPlanned() {
		return isRetestPlanned;
	}

	public void setRetestPlanned(boolean flag) {
		isRetestPlanned = flag;
	}

	public boolean isTestProtocolSubmitted() {
		return isTestProtocolSubmitted;
	}

	public void setTestProtocolSubmitted(boolean flag) {
		isTestProtocolSubmitted = flag;
	}

	public boolean isTestReportSubmitted() {
		return isTestReportSubmitted;
	}

	public void setTestReportSubmitted(boolean flag) {
		isTestReportSubmitted = flag;
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

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String s) {
		manufacturer = s;
	}

	public String getMea() {
		return mea;
	}

	public void setMea(String s) {
		mea = s;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
	}

	public String getNextStackTestDate() {
		return nextStackTestDate;
	}

	public void setNextStackTestDate(String s) {
		nextStackTestDate = s;
	}

	public String getNextStackTestNote() {
		return nextStackTestNote;
	}

	public void setNextStackTestNote(String s) {
		nextStackTestNote = s;
	}

	public String getNycdob() {
		return nycdob;
	}

	public void setNycdob(String s) {
		nycdob = s;
	}

	public boolean isPlanApproval() {
		return planApproval;
	}

	public void setPlanApproval(boolean flag) {
		planApproval = flag;
	}

	public int getPrimaryFuel() {
		return primaryFuel;
	}

	public void setPrimaryFuel(int i) {
		primaryFuel = i;
	}

	public int getPrimaryUse() {
		return primaryUse;
	}

	public void setPrimaryUse(int i) {
		primaryUse = i;
	}

	public int getResourceType() {
		return resourceType;
	}

	public void setResourceType(int i) {
		resourceType = i;
	}

	public boolean isSechedule() {
		return sechedule;
	}

	public void setSechedule(boolean flag) {
		sechedule = flag;
	}

	public int getSecondaryFuel() {
		return secondaryFuel;
	}

	public void setSecondaryFuel(int i) {
		secondaryFuel = i;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String s) {
		serialNumber = s;
	}

	public int getStackExhaustId() {
		return stackExhaustId;
	}

	public void setStackExhaustId(int i) {
		stackExhaustId = i;
	}

	public StackVo getStackObj() {
		if (stackObj == null)
			try {
				stackObj = StackEntity.findByPrimaryKey(getStackExhaustId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return stackObj;
	}

	public void setStackObj(StackVo stackvo) {
		stackObj = stackvo;
	}

	public String getTestContductedBy() {
		return testContductedBy;
	}

	public void setTestContductedBy(String s) {
		testContductedBy = s;
	}

	public String getTestReportSubmittedDate() {
		return testReportSubmittedDate;
	}

	public void setTestReportSubmittedDate(String s) {
		testReportSubmittedDate = s;
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

	public String getResourceName() {
		if (resourceType > 0) {
			CogenTurbineTypeEnum cogenturbinetypeenum = CogenTurbineTypeEnum
					.get(resourceType);
			if (cogenturbinetypeenum != null)
				resourceName = cogenturbinetypeenum.getName();
		}
		return resourceName;
	}

	public void setResourceName(String s) {
		resourceName = s;
	}

	public List getPermitList() {
		if (permitList == null)
			try {
				permitList = CogenTurbineEntity.getPermitList(getId(),
						getResourceType());
			} catch (Exception exception) {
				log.error(exception);
			}
		return permitList;
	}

	public void setPermitList(List list) {
		permitList = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id).append(", ");
		stringbuffer.append("buildingId =").append(buildingId).append(", ");
		stringbuffer.append("facilityDesignatedId =")
				.append(facilityDesignatedId).append(", ");
		stringbuffer.append("decId =").append(decId).append(", ");
		stringbuffer.append("resourceType =").append(resourceType).append(", ");
		stringbuffer.append("floor =").append(floor).append(", ");
		stringbuffer.append("primaryUse =").append(primaryUse).append(", ");
		stringbuffer.append("hasControlSystem =").append(hasControlSystem)
				.append(", ");
		stringbuffer.append("controlEfficiency =").append(controlEfficiency)
				.append(", ");
		stringbuffer.append("yearInstalled =").append(yearInstalled)
				.append(", ");
		stringbuffer.append("status=").append(status).append(", ");
		stringbuffer.append("disconnecteddate=").append(disconnecteddate)
				.append(", ");
		stringbuffer.append("manufacturer =").append(manufacturer).append(", ");
		stringbuffer.append("make =").append(make).append(", ");
		stringbuffer.append("model =").append(model).append(", ");
		stringbuffer.append("serialNumber =").append(serialNumber).append(", ");
		stringbuffer.append("burnerMake =").append(burnerMake).append(", ");
		stringbuffer.append("burnerModel =").append(burnerModel).append(", ");
		stringbuffer.append("burnerType =").append(burnerType).append(", ");
		stringbuffer.append("capacity =").append(capacity).append(", ");
		stringbuffer.append("primaryFuel =").append(primaryFuel).append(", ");
		stringbuffer.append("secondaryFuel =").append(secondaryFuel)
				.append(", ");
		stringbuffer.append("fuelFromTank =").append(fuelFromTank).append(", ");
		stringbuffer.append("nycdob =").append(nycdob).append(", ");
		stringbuffer.append("mea =").append(mea).append(", ");
		stringbuffer.append("depNumber =").append(depNumber).append(", ");
		stringbuffer.append("sechedule =").append(sechedule).append(", ");
		stringbuffer.append("planApproval =").append(planApproval).append(", ");
		stringbuffer.append("isRecordsMaintained =")
				.append(isRecordsMaintained).append(", ");
		stringbuffer.append("isTestProtocolSubmitted =")
				.append(isTestProtocolSubmitted).append(", ");
		stringbuffer.append("testContductedBy =").append(testContductedBy)
				.append(", ");
		stringbuffer.append("isTestReportSubmitted =")
				.append(isTestReportSubmitted).append(", ");
		stringbuffer.append("testReportSubmittedDate =")
				.append(testReportSubmittedDate).append(", ");
		stringbuffer.append("isComplyWithNoxPlan =")
				.append(isComplyWithNoxPlan).append(", ");
		stringbuffer.append("isRetestPlanned =").append(isRetestPlanned)
				.append(", ");
		stringbuffer.append("nextStackTestDate =").append(nextStackTestDate)
				.append(", ");
		stringbuffer.append("nextStackTestNote =").append(nextStackTestNote)
				.append(", ");
		stringbuffer.append("stackExhaustId =").append(stackExhaustId)
				.append(", ");
		stringbuffer.append("isFuleCappingUnderLimit =")
				.append(isFuleCappingUnderLimit).append(", ");
		stringbuffer.append("resourceName =").append(getResourceName());
		return stringbuffer.toString();
	}

	public List getFuelConsumptionList() {
		if (fuelConsumptionList == null)
			try {
				int i = getResourceType();
				ResourcesTypeEnum resourcestypeenum = null;
				if (i == CogenTurbineTypeEnum.COGEN.getCode())
					resourcestypeenum = ResourcesTypeEnum.COGEN;
				else if (i == CogenTurbineTypeEnum.TURBINES.getCode())
					resourcestypeenum = ResourcesTypeEnum.TURBINES;
				if (resourcestypeenum == null)
					throw new Exception(
							(new StringBuilder())
									.append("Unable to get the ResourceTypeEnum for resource type=")
									.append(i).toString());
				fuelConsumptionList = CogenTurbineEntity
						.getFuelConsumptionList(getId(), resourcestypeenum);
			} catch (Exception exception) {
				log.error(exception);
			}
		return fuelConsumptionList;
	}

	public void setFuelConsumptionList(List list) {
		fuelConsumptionList = list;
	}

	public List geto_FuelConsumptionList() {
		if (o_fuelConsumptionList == null)
			try {
				int i = getResourceType();
				ResourcesTypeEnum resourcestypeenum = null;
				if (i == CogenTurbineTypeEnum.COGEN.getCode())
					resourcestypeenum = ResourcesTypeEnum.COGEN;
				else if (i == CogenTurbineTypeEnum.TURBINES.getCode())
					resourcestypeenum = ResourcesTypeEnum.TURBINES;
				if (resourcestypeenum == null)
					throw new Exception(
							(new StringBuilder())
									.append("Unable to get the ResourceTypeEnum for resource type=")
									.append(i).toString());
				o_fuelConsumptionList = CogenTurbineEntity
						.geto_FuelConsumptionList(getId(), resourcestypeenum);
			} catch (Exception exception) {
				log.error(exception);
			}
		return o_fuelConsumptionList;
	}

	public void seto_FuelConsumptionList(List list) {
		o_fuelConsumptionList = list;
	}

	// ---
	public String getIsturbine() {
		return isturbine;
	}

	public void setIsturbine(String s) {
		isturbine = s;
	}

	public String getTmanufacturer() {
		return tmanufacturer;
	}

	public void setTmanufacturer(String s) {
		tmanufacturer = s;
	}

	public String getTmake() {
		return tmake;
	}

	public void setTmake(String s) {
		tmake = s;
	}

	public String getTmodel() {
		return tmodel;
	}

	public void setTmodel(String s) {
		tmodel = s;
	}

	public String getTserial() {
		return tserial;
	}

	public void setTserial(String s) {
		tserial = s;
	}

	public String getTburnerMake() {
		return tburnerMake;
	}

	public void setTburnerMake(String s) {
		tburnerMake = s;
	}

	public String getTburnerModel() {
		return tburnerModel;
	}

	public void setTburnerModel(String s) {
		tburnerModel = s;
	}

	public String getTcapacity() {
		return tcapacity;
	}

	public void setTcapacity(String s) {
		tcapacity = s;
	}

	/*
	 * public String getThpText() { return thpText; } public void
	 * setThpText(String s) { thpText= s; }
	 * 
	 * public String getTmmbtuText() { return tmmbtuText; } public void
	 * setTmmbtuText(String s) { tmmbtuText= s; }
	 */
	public String getTburnerType() {
		return tburnerType;
	}

	public void setTburnerType(String s) {
		tburnerType = s;
	}

	public int getTprimaryFuel() {
		return tprimaryFuel;
	}

	public void setTprimaryFuel(int s) {
		tprimaryFuel = s;
	}

	public int getTsecondaryFuel() {
		return tsecondaryFuel;
	}

	public void setTsecondaryFuel(int s) {
		tsecondaryFuel = s;
	}

	/*
	 * public String getToilFiringRate() { return toilFiringRate; } public void
	 * setToilFiringRate(String s) { toilFiringRate= s; }
	 * 
	 * public String getTnaturalGasFiringRate() { return tnaturalGasFiringRate;
	 * } public void setTnaturalGasFiringRate(String s) { tnaturalGasFiringRate=
	 * s; }
	 */
	public String getIswhrb() {
		return iswhrb;
	}

	public void setIswhrb(String s) {
		iswhrb = s;
	}

	public String getWmanufacturer() {
		return wmanufacturer;
	}

	public void setWmanufacturer(String s) {
		wmanufacturer = s;
	}

	public String getWmake() {
		return wmake;
	}

	public void setWmake(String s) {
		wmake = s;
	}

	public String getWmodel() {
		return wmodel;
	}

	public void setWmodel(String s) {
		wmodel = s;
	}

	public String getWserial() {
		return wserial;
	}

	public void setWserial(String s) {
		wserial = s;
	}

	public String getWburnerMake() {
		return wburnerMake;
	}

	public void setWburnerMake(String s) {
		wburnerMake = s;
	}

	public String getWburnerModel() {
		return wburnerModel;
	}

	public void setWburnerModel(String s) {
		wburnerModel = s;
	}

	public String getWcapacity() {
		return wcapacity;
	}

	public void setWcapacity(String s) {
		wcapacity = s;
	}

	/*
	 * public String getWhpText() { return whpText; } public void
	 * setWhpText(String s) { whpText= s; }
	 * 
	 * public String getWmmbtuText() { return wmmbtuText; } public void
	 * setWmmbtuText(String s) { wmmbtuText= s; }
	 */
	public String getWburnerType() {
		return wburnerType;
	}

	public void setWburnerType(String s) {
		wburnerType = s;
	}

	public int getWprimaryFuel() {
		return wprimaryFuel;
	}

	public void setWprimaryFuel(int s) {
		wprimaryFuel = s;
	}

	public int getWsecondaryFuel() {
		return wsecondaryFuel;
	}

	public void setWsecondaryFuel(int s) {
		wsecondaryFuel = s;
	}

	/*
	 * public String getWoilFiringRate() { return woilFiringRate; } public void
	 * setWoilFiringRate(String s) { woilFiringRate= s; }
	 * 
	 * public String getWnaturalGasFiringRate() { return wnaturalGasFiringRate;
	 * } public void setWnaturalGasFiringRate(String s) { wnaturalGasFiringRate=
	 * s; }
	 */
	public String getCo_decPermitObtained() {
		return co_decPermitObtained;
	}

	public void setCo_decPermitObtained(String s) {
		co_decPermitObtained = s;
	}

	public String getCo_depPermitObtained() {
		return co_depPermitObtained;
	}

	public void setCo_depPermitObtained(String s) {
		co_depPermitObtained = s;
	}

	public String getCo_compyWithSO2RactPlan() {
		return co_compyWithSO2RactPlan;
	}

	public void setCo_compyWithSO2RactPlan(String s) {
		co_compyWithSO2RactPlan = s;
	}

	public String getCo_compyWithPMRactPlan() {
		return co_compyWithPMRactPlan;
	}

	public void setCo_compyWithPMRactPlan(String s) {
		co_compyWithPMRactPlan = s;
	}

	public String getCo_testPassed() {
		return co_testPassed;
	}

	public void setCo_testPassed(String s) {
		co_testPassed = s;
	}

	public String getCo_StackTestDate() {
		return co_StackTestDate;
	}

	public void setCo_StackTestDate(String s) {
		co_StackTestDate = s;
	}

	public String getCo_fuelgapping() {
		return co_fuelgapping;
	}

	public void setCo_fuelgapping(String s) {
		co_fuelgapping = s;
	}

	public String getCo_gasfuelgapping() {
		return co_gasfuelgapping;
	}

	public void setCo_gasfuelgapping(String s) {
		co_gasfuelgapping = s;
	}

	public String getCo_gasemmisionfactor() {
		return co_gasemmisionfactor;
	}

	public void setCo_gasemmisionfactor(String s) {
		co_gasemmisionfactor = s;
	}

	public String getCo_oilfuelgapping() {
		return co_oilfuelgapping;
	}

	public void setCo_oilfuelgapping(String s) {
		co_oilfuelgapping = s;
	}

	public String getCo_oilemmisionfactor() {
		return co_oilemmisionfactor;
	}

	public void setCo_oilemmisionfactor(String s) {
		co_oilemmisionfactor = s;
	}

	// ///////////
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.LandfillVo.class);
	protected int id;
	protected int buildingId;
	protected BuildingVo buildingObj;
	protected String facilityDesignatedId;
	protected int location;
	protected String decId;
	protected int resourceType;
	protected String floor;
	protected int primaryUse;
	protected boolean hasControlSystem;
	protected int controlEfficiency;
	protected String yearInstalled;
	protected int status;
	protected String disconnecteddate;
	protected String manufacturer;
	protected String make;
	protected String model;
	protected String serialNumber;
	protected String burnerMake;
	protected String burnerModel;
	protected int burnerType;
	protected String capacity;
	protected int primaryFuel;
	protected int secondaryFuel;
	protected int fuelFromTank;
	protected StorageTankVo fuelFromTankObj;
	protected String nycdob;
	protected String mea;
	protected String depNumber;
	protected boolean sechedule;
	protected boolean planApproval;
	protected boolean isRecordsMaintained;
	protected boolean isTestProtocolSubmitted;
	protected String testContductedBy;
	protected boolean isTestReportSubmitted;
	protected String testReportSubmittedDate;
	protected boolean isComplyWithNoxPlan;
	protected boolean isRetestPlanned;
	protected String nextStackTestDate;
	protected String nextStackTestNote;
	protected int stackExhaustId;
	protected StackVo stackObj;
	protected boolean isFuleCappingUnderLimit;
	protected String resourceName;
	protected List permitList;
	protected List fuelConsumptionList;
	protected List o_fuelConsumptionList;

	protected String isturbine;
	protected String tmanufacturer;
	protected String tmake;
	protected String tmodel;
	protected String tserial;
	protected String tburnerMake;
	protected String tburnerModel;
	protected String tcapacity;
	// protected String thpText;
	// protected String tmmbtuText;
	protected String tburnerType;
	protected int tprimaryFuel;
	protected int tsecondaryFuel;
	// protected String toilFiringRate;
	// protected String tnaturalGasFiringRate;
	protected String iswhrb;
	protected String wmanufacturer;
	protected String wmake;
	protected String wmodel;
	protected String wserial;
	protected String wburnerMake;
	protected String wburnerModel;
	protected String wcapacity;
	// protected String whpText;
	// protected String wmmbtuText;
	protected String wburnerType;
	protected int wprimaryFuel;
	protected int wsecondaryFuel;
	// protected String woilFiringRate;
	// protected String wnaturalGasFiringRate;
	protected String co_decPermitObtained;
	protected String co_depPermitObtained;
	protected String co_compyWithSO2RactPlan;
	protected String co_compyWithPMRactPlan;
	protected String co_testPassed;
	protected String co_StackTestDate;
	protected String co_fuelgapping;
	protected String co_gasfuelgapping;
	protected String co_gasemmisionfactor;
	protected String co_oilfuelgapping;
	protected String co_oilemmisionfactor;
	protected String co_Comments;
	protected String co_oilso2;
	protected String co_gasso2;
	protected String co_so2allowable;
	protected String co_noxallowable;
	protected String dobsignoff;
	protected String dobfiling;

}