package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.GeneratorEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class GeneratorVo
implements Serializable
{

public GeneratorVo()
{
    id = -99;
    buildingId = -99;
    facilityDesignatedId = null;
    floor = null;
    stateId = null;
    primaryUse = -99;
    yearInstalled = null;
    status = -99;
    disconnecteddate = null;
    manufacturer = null;
   
    model = null;
    serial = null;
    burnerMake = null;
    burnerModel = null;
    capacity = null;
    burnerType = -99;
    primaryFuel = -99;
    secondaryFuel = -99;
    fuelFrom = -99;
    hasDayTank = false;
    dayTankFrom = -99;
    nycDob = null;
    mea = null;
    dep = null;
    sechduelC = false;
    planApproval = false;
    stackFrom = -99;
    isRecordsInBoundBook = false;
    decPermitObtained = false;
    depPermitObtained = false;
    isStackProtSubmitted = null;
    isStackTest = null;
    testConductedBy = null;
    testRptSubmited = false;
    complyWithNoxPlan = false;
    retestPlanned = false;
    nextStackTestDate = null;
    nextStackTestDateNote = null;
    fuelCappingUnderLimit = false;        
    fuelFromObj = null;
    stackFromObj = null;
    dayTankObj = null;
    actiontaken = null;		
    compyWithPMRactPlan = null;
    gasfuelgapping = null;
    gasemmisionfactor = null;
    oilfuelgapping = null;
    oilemmisionfactor = null;
    StackTestDate = null;
    ProtocalSubmittalDate = null;
    testPassed = null;
    fuelgapping = null;
    g_oilso2=null;
	g_gasso2=null;
	g_so2allowable=null;
	g_noxallowable=null;
	dobfiling=null;
	dobsignoff=null;
	firedepartmentapproval=null;
	permitStatus=null;
	permitExpire=null;
	
	capablefuel=null;
	//oilfiring=null;
	isOilFiring=true;
	
	fuelConsumptionList = null;
    o_fuelConsumptionList = null;
    permitList = null;
    cfrPermitList = null;
	engineRunningHrsList = null;
	o_pressureTestList = null;
    g_pressureTestList = null;
    anu_pressureTestList = null;

}

public GeneratorVo(ResultSet resultset)
    throws SQLException
{
    id = -99;
    buildingId = -99;
    facilityDesignatedId = null;
    floor = null;
    stateId = null;
    primaryUse = -99;
    yearInstalled = null;
    status = -99;
    disconnecteddate = null;
    manufacturer = null;
  
    model = null;
    serial = null;
    burnerMake = null;
    burnerModel = null;
    capacity = null;
    burnerType = -99;
    primaryFuel = -99;
    secondaryFuel = -99;
    fuelFrom = -99;
    hasDayTank = false;
    dayTankFrom = -99;
    nycDob = null;
    mea = null;
    dep = null;
    sechduelC = false;
    planApproval = false;
    stackFrom = -99;
    isRecordsInBoundBook = false;
    decPermitObtained = false;
    depPermitObtained = false;
    isStackProtSubmitted = null;
    testConductedBy = null;
    testRptSubmited = false;
    complyWithNoxPlan = false;
    retestPlanned = false;
    nextStackTestDate = null;
    nextStackTestDateNote = null;
    fuelCappingUnderLimit = false;       
    fuelFromObj = null;
    stackFromObj = null;
    dayTankObj = null;
    actiontaken = null;
    permitStatus=null;
	permitExpire=null;
	dobfiling=null;
    compyWithPMRactPlan = null;
    gasfuelgapping = null;
    gasemmisionfactor = null;
    oilfuelgapping = null;
    oilemmisionfactor = null;
    StackTestDate = null;
    ProtocalSubmittalDate = null;
    testPassed = null;
    fuelgapping = null;
    g_oilso2=null;
	g_gasso2=null;
	g_so2allowable=null;
	g_noxallowable=null;
	dobsignoff=null;
	firedepartmentapproval=null;
	isStackTest = null;
	
	capablefuel=null;
	//oilfiring=null;
	isOilFiring=true;
	
	fuelConsumptionList = null;
    o_fuelConsumptionList = null;
    permitList = null;
    cfrPermitList = null;
    engineRunningHrsList = null;
    o_pressureTestList = null;
    g_pressureTestList = null;
    anu_pressureTestList = null;


    id = UtilityObject.getIntFromString(resultset.getString("GENERATORID"));
    buildingId = UtilityObject.getIntFromString(resultset.getString("BUILDINGID"));
    facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
    floor = resultset.getString("FLOOR");
    stateId = resultset.getString("STATEID");
    primaryUse = UtilityObject.getIntFromString(resultset.getString("PRIMARYUSE"));
    yearInstalled = resultset.getString("YEARINSTALLED");
    status = resultset.getInt("STATUS");
    disconnecteddate = resultset.getString("DISCONNECTEDDATE");
    manufacturer = resultset.getString("MANUFACTURER");
    
    model = resultset.getString("MODEL");
    actiontaken = resultset.getString("ACTIONTAKEN");
    serial = resultset.getString("SERIAL");
    burnerMake = resultset.getString("BURNERMAKE");
    burnerModel = resultset.getString("BURNERMODEL");
    capacity = resultset.getString("CAPACITY");
    burnerType = UtilityObject.getIntFromString(resultset.getString("BURNERTYPE"));
    primaryFuel = UtilityObject.getIntFromString(resultset.getString("PRIMARYFUEL"));
    secondaryFuel = UtilityObject.getIntFromString(resultset.getString("SECONDARYFUEL"));
    fuelFrom = UtilityObject.getIntFromString(resultset.getString("FUELFROM"));
    hasDayTank = UtilityObject.convertBoolean(resultset.getString("HASDAYTANK"));
    dayTankFrom = UtilityObject.getIntFromString(resultset.getString("DAYTANKFROM"));
    nycDob = resultset.getString("NYCDOB");
    mea = resultset.getString("MEA");
    dep = resultset.getString("DEP");
    sechduelC = UtilityObject.convertBoolean(resultset.getString("SECHDUELC"));
    planApproval = UtilityObject.convertBoolean(resultset.getString("PLANAPPROVAL"));
    stackFrom = UtilityObject.getIntFromString(resultset.getString("STACKFROM"));
    isRecordsInBoundBook = UtilityObject.convertBoolean(resultset.getString("ISRECORDSINBOUNDBOOK"));
    decPermitObtained = UtilityObject.convertBoolean(resultset.getString("DECDEPPERMITOBTAINED"));
    depPermitObtained = UtilityObject.convertBoolean(resultset.getString("DEPPERMITOBTAINED"));
    isStackProtSubmitted = resultset.getString("ISSTACKPROTSUBMITTED");
    testConductedBy = resultset.getString("TESTCONDUCTEDBY");
    testRptSubmited = UtilityObject.convertBoolean(resultset.getString("TESTRPTSUBMITED"));
    complyWithNoxPlan = UtilityObject.convertBoolean(resultset.getString("COMPLYWITHNOXPLAN"));
    retestPlanned = UtilityObject.convertBoolean(resultset.getString("RETESTPLANNED"));
    nextStackTestDate = resultset.getString("NEXTSTACKTESTDATE");
    nextStackTestDateNote = resultset.getString("NEXTSTACKTESTDATENOTE");
    fuelCappingUnderLimit = UtilityObject.convertBoolean(resultset.getString("FUELCAPPINGUNDERLIMIT"));
    compyWithPMRactPlan = resultset.getString("compyWithPMRactPlan");
    gasfuelgapping = resultset.getString("gasfuelgapping");
    gasemmisionfactor = resultset.getString("gasemmisionfactor");
    oilfuelgapping = resultset.getString("oilfuelgapping");
    oilemmisionfactor = resultset.getString("oilemmisionfactor");
    StackTestDate = resultset.getString("StackTestDate");
    ProtocalSubmittalDate = resultset.getString("ProtocalSubmittalDate");
    testPassed = resultset.getString("testPassed");
    fuelgapping = resultset.getString("fuelgapping");
    g_oilso2=resultset.getString("G_OILSO2");
	g_gasso2=resultset.getString("G_GASSO2");
	g_so2allowable=resultset.getString("G_SO2ALLOWABLE");
	g_noxallowable=resultset.getString("G_NOXALLOWABLE");
	dobsignoff=resultset.getString("DOBSIGNOFF");	
	firedepartmentapproval=resultset.getString("FIREDEPARTMENTAPPROVAL");
	isStackTest=resultset.getString("ISSTACKTEST");
	permitStatus=resultset.getString("PERMITSTATUS");
	permitExpire=resultset.getString("PERMITEXPIRE");
	dobfiling=resultset.getString("DOBFILING");
	capablefuel=resultset.getString("CAPABLEFUEL");
	//oilfiring=resultset.getString("OILFIRING");
	isOilFiring = UtilityObject.convertBoolean(resultset.getString("OILFIRING"));
	}



public String getDobFiling()
{
    return dobfiling;
}

public void setDobFiling(String s)
{
    dobfiling = s;
}



public String getCapablefuel()
{
    return capablefuel;
}

public void setCapablefuel(String s)
{
	capablefuel = s;
}


/*public String getOilFiring()
{
    return oilfiring;
}

public void setOilFiring(String s)
{
	oilfiring = s;
}*/


public boolean isOilFiring()
{
    return isOilFiring;
}

public void setOilFiring(boolean flag)
{
	isOilFiring = flag;
}

public String getPermitStatus()
{
return permitStatus;
}
public void setPermitStatus(String s)
{
permitStatus= s;
}

public String getPermitExpire()
{
return permitExpire;
}
public void setPermitExpire(String s)
{
permitExpire= s;
}


public String getDobsignoff()
{
return dobsignoff;
}
public void setDobsignoff(String s)
{
dobsignoff= s;
}

public String isStackTest()
{
return isStackTest;
}
public void setStackTest(String s)
{
isStackTest= s;
}

public String getFiredepartmentapproval()
{
return firedepartmentapproval;
}
public void setFiredepartmentapproval(String s)
{
firedepartmentapproval= s;
}

public String getNextStackTestDateNote()
{
return nextStackTestDateNote;
}
public void setNextStackTestDateNote(String s)
{
nextStackTestDateNote= s;
}


public String getG_oilso2()
{
return g_oilso2;
}
public void setG_oilso2(String s)
{
g_oilso2= s;
}

public String getG_gasso2()
{
return g_gasso2;
}
public void setG_gasso2(String s)
{
g_gasso2= s;
}

public String getG_so2allowable()
{
return g_so2allowable;
}
public void setG_so2allowable(String s)
{
g_so2allowable= s;
}

public String getG_noxallowable()
{
return g_noxallowable;
}
public void setG_noxallowable(String s)
{
g_noxallowable= s;
}

public int getBuildingId()
{
    return buildingId;
}

public void setBuildingId(int i)
{
    buildingId = i;
}

public String getBurnerMake()
{
    return burnerMake;
}

public void setBurnerMake(String s)
{
    burnerMake = s;
}

public String getBurnerModel()
{
    return burnerModel;
}

public void setActionTaken(String s)
{
    actiontaken = s;
}

public String getActionTaken()
{
    return actiontaken;
}

public void setBurnerModel(String s)
{
    burnerModel = s;
}

public int getBurnerType()
{
    return burnerType;
}

public void setBurnerType(int i)
{
    burnerType = i;
}

public String getCapacity()
{
    return capacity;
}

public void setCapacity(String s)
{
    capacity = s;
}

public boolean isComplyWithNoxPlan()
{
    return complyWithNoxPlan;
}

public void setComplyWithNoxPlan(boolean flag)
{
    complyWithNoxPlan = flag;
}

public int getDayTankFrom()
{
    return dayTankFrom;
}

public void setDayTankFrom(int i)
{
    dayTankFrom = i;
}

public boolean isDecPermitObtained()
{
    return decPermitObtained;
}

public void setDecPermitObtained(boolean flag)
{
    decPermitObtained = flag;
}

public String getDep()
{
    return dep;
}

public void setDep(String s)
{
    dep = s;
}

public String getFacilityDesignatedId()
{
    return facilityDesignatedId;
}

public void setFacilityDesignatedId(String s)
{
    facilityDesignatedId = s;
}

public String getFloor()
{
    return floor;
}

public void setFloor(String s)
{
    floor = s;
}

public boolean isFuelCappingUnderLimit()
{
    return fuelCappingUnderLimit;
}

public void setFuelCappingUnderLimit(boolean flag)
{
    fuelCappingUnderLimit = flag;
}

public int getFuelFrom()
{
    return fuelFrom;
}

public void setFuelFrom(int i)
{
    fuelFrom = i;
}

public boolean isHasDayTank()
{
    return hasDayTank;
}

public void setHasDayTank(boolean flag)
{
    hasDayTank = flag;
}

public int getId()
{
    return id;
}

public void setId(int i)
{
    id = i;
}

public boolean isRecordsInBoundBook()
{
    return isRecordsInBoundBook;
}

public void setRecordsInBoundBook(boolean flag)
{
    isRecordsInBoundBook = flag;
}

public String isStackProtSubmitted()
{
    return isStackProtSubmitted;
}

public void setStackProtSubmitted(String s)
{
    isStackProtSubmitted = s;
}

/*public String getMake()
{
    return make;
}

public void setMake(String s)
{
    make = s;
}*/

public String getManufacturer()
{
    return manufacturer;
}

public void setManufacturer(String s)
{
    manufacturer = s;
}

public String getMea()
{
    return mea;
}

public void setMea(String s)
{
    mea = s;
}



public String getFuelgapping()
{
    return fuelgapping;
}

public void setFuelgapping(String s)
{
    fuelgapping = s;
}


public String getTestPassed()
{
    return testPassed;
}

public void setTestPassed(String s)
{
    testPassed = s;
}


public String getProtocalSubmittalDate()
{
    return ProtocalSubmittalDate;
}

public void setProtocalSubmittalDate(String s)
{
    ProtocalSubmittalDate = s;
}


public String getStackTestDate()
{
    return StackTestDate;
}

public void setStackTestDate(String s)
{
    StackTestDate = s;
}


public String getOilemmisionfactor()
{
    return oilemmisionfactor;
}

public void setOilemmisionfactor(String s)
{
    oilemmisionfactor = s;
}


public String getOilfuelgapping()
{
    return oilfuelgapping;
}

public void setOilfuelgapping(String s)
{
    oilfuelgapping = s;
}


public String getGasemmisionfactor()
{
    return gasemmisionfactor;
}

public void setGasemmisionfactor(String s)
{
    gasemmisionfactor = s;
}

 public String getGasfuelgapping()
{
    return gasfuelgapping;
}

public void setGasfuelgapping(String s)
{
    gasfuelgapping = s;
}

public String getCompyWithPMRactPlan()
{
    return compyWithPMRactPlan;
}

public void setCompyWithPMRactPlan(String s)
{
    compyWithPMRactPlan = s;
}



public String getModel()
{
    return model;
}

public void setModel(String s)
{
    model = s;
}

public String getNextStackTestDate()
{
    return nextStackTestDate;
}

public void setNextStackTestDate(String s)
{
    nextStackTestDate = s;
}

public String getNycDob()
{
    return nycDob;
}

public void setNycDob(String s)
{
    nycDob = s;
}

public boolean isPlanApproval()
{
    return planApproval;
}

public void setPlanApproval(boolean flag)
{
    planApproval = flag;
}

public int getPrimaryFuel()
{
    return primaryFuel;
}

public void setPrimaryFuel(int i)
{
    primaryFuel = i;
}

public int getPrimaryUse()
{
    return primaryUse;
}

public void setPrimaryUse(int i)
{
    primaryUse = i;
}

public boolean isRetestPlanned()
{
    return retestPlanned;
}

public void setRetestPlanned(boolean flag)
{
    retestPlanned = flag;
}

public boolean isSechduelC()
{
    return sechduelC;
}

public void setSechduelC(boolean flag)
{
    sechduelC = flag;
}

public int getSecondaryFuel()
{
    return secondaryFuel;
}

public void setSecondaryFuel(int i)
{
    secondaryFuel = i;
}

public String getSerial()
{
    return serial;
}

public void setSerial(String s)
{
    serial = s;
}

public int getStackFrom()
{
    return stackFrom;
}

public void setStackFrom(int i)
{
    stackFrom = i;
}

public String getStateId()
{
    return stateId;
}

public void setStateId(String s)
{
    stateId = s;
}

public String getTestConductedBy()
{
    return testConductedBy;
}

public void setTestConductedBy(String s)
{
    testConductedBy = s;
}

public boolean isTestRptSubmited()
{
    return testRptSubmited;
}

public void setTestRptSubmited(boolean flag)
{
    testRptSubmited = flag;
}

public String getYearInstalled()
{
    return yearInstalled;
}

public void setYearInstalled(String s)
{
    yearInstalled = s;
}

public int getStatus()
{
    return status;
}

public void setStatus(int i)
{
    status = i;
}

public String getDisconnecteddate()
{
    return disconnecteddate;
}

public void setDisconnecteddate(String s)
{
    disconnecteddate = s;
}

public List getFuelConsumptionList()
{
    if(fuelConsumptionList == null)
        try
        {
            fuelConsumptionList = GeneratorEntity.getFuelConsumptionList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return fuelConsumptionList;
}

public void setFuelConsumptionList(List list)
{
    fuelConsumptionList = list;
}

public List geto_FuelConsumptionList()
{
    if(o_fuelConsumptionList == null)
        try
        {
            o_fuelConsumptionList = GeneratorEntity.geto_FuelConsumptionList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return o_fuelConsumptionList;
}

public void seto_FuelConsumptionList(List list)
{
    o_fuelConsumptionList = list;
}

public List getPermitList()
{
    if(permitList == null)
        try
        {
            permitList = GeneratorEntity.getPermitList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return permitList;
}

public void setPermitList(List list)
{
    permitList = list;
}



public List getCfrPermitList()
{
    if(cfrPermitList == null)
        try
        {
            cfrPermitList = GeneratorEntity.getCfrPermitList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return cfrPermitList;
}

public void setCfrPermitList(List list)
{
    cfrPermitList = list;
}


public List getEngineRunningHrsList()
{
    if(engineRunningHrsList == null)
        try
        {
            engineRunningHrsList = GeneratorEntity.getEngineRunningHrsList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return engineRunningHrsList;
}

public void setEngineRunningHrsList(List list)
{
    engineRunningHrsList = list;
}




public List geto_PressureTestHrsList()
{
    if(o_pressureTestList == null)
        try
        {
        	o_pressureTestList = GeneratorEntity.geto_PressureTestHrsList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return o_pressureTestList;
}

public void seto_PressureTestHrsList(List list)
{
	o_pressureTestList = list;
}


public List getg_PressureTestHrsList()
{
    if(g_pressureTestList == null)
        try
        {
        	g_pressureTestList = GeneratorEntity.getg_PressureTestHrsList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return g_pressureTestList;
}
public void setg_PressureTestHrsList(List list)
{
	g_pressureTestList = list;
}



public List getanu_PressureTestHrsList()
{
    if(anu_pressureTestList == null)
        try
        {
        	anu_pressureTestList = GeneratorEntity.getanu_PressureTestHrsList(getId());
        }
        catch(Exception exception)
        {
            log.error(exception);
        }
    return anu_pressureTestList;
}

public void setanu_PressureTestHrsList(List list)
{
	anu_pressureTestList = list;
}

public boolean isDepPermitObtained()
{
    return depPermitObtained;
}

public void setDepPermitObtained(boolean flag)
{
    depPermitObtained = flag;
}

public String toString()
{
    StringBuffer stringbuffer = new StringBuffer();
    stringbuffer.append("id =").append(id).append(", ");
    stringbuffer.append("buildingId =").append(buildingId).append(", ");
    stringbuffer.append("facilityDesignatedId =").append(facilityDesignatedId).append(", ");
    stringbuffer.append("floor =").append(floor).append(", ");
    stringbuffer.append("stateId =").append(stateId).append(", ");
    stringbuffer.append("primaryUse =").append(primaryUse).append(", ");
    stringbuffer.append("yearInstalled =").append(yearInstalled).append(", ");
    stringbuffer.append("status=").append(status).append(", ");
    stringbuffer.append("disconnecteddate=").append(disconnecteddate).append(", ");
    stringbuffer.append("manufacturer =").append(manufacturer).append(", ");
    
    stringbuffer.append("model =").append(model).append(", ");
    stringbuffer.append("serial =").append(serial).append(", ");
    stringbuffer.append("burnerMake =").append(burnerMake).append(", ");
    stringbuffer.append("burnerModel =").append(burnerModel).append(", ");
    stringbuffer.append("capacity =").append(capacity).append(", ");
    stringbuffer.append("burnerType =").append(burnerType).append(", ");
    stringbuffer.append("primaryFuel =").append(primaryFuel).append(", ");
    stringbuffer.append("secondaryFuel =").append(secondaryFuel).append(", ");
    stringbuffer.append("fuelFrom =").append(fuelFrom).append(", ");
    stringbuffer.append("hasDayTank =").append(hasDayTank).append(", ");
    stringbuffer.append("dayTankFrom =").append(dayTankFrom).append(", ");
    stringbuffer.append("nycDob =").append(nycDob).append(", ");
    stringbuffer.append("mea =").append(mea).append(", ");
    stringbuffer.append("dep =").append(dep).append(", ");
    stringbuffer.append("sechduelC =").append(sechduelC).append(", ");
    stringbuffer.append("planApproval =").append(planApproval).append(", ");
    stringbuffer.append("stackFrom =").append(stackFrom).append(", ");
    stringbuffer.append("isRecordsInBoundBook =").append(isRecordsInBoundBook).append(", ");
    stringbuffer.append("decPermitObtained =").append(decPermitObtained).append(", ");
    stringbuffer.append("depPermitObtained =").append(depPermitObtained).append(", ");
    stringbuffer.append("isStackTest =").append(isStackTest).append(", ");
    stringbuffer.append("isStackProtSubmitted =").append(isStackProtSubmitted).append(", ");
    stringbuffer.append("testConductedBy =").append(testConductedBy).append(", ");
    stringbuffer.append("testRptSubmited =").append(testRptSubmited).append(", ");
    stringbuffer.append("complyWithNoxPlan =").append(complyWithNoxPlan).append(", ");
    stringbuffer.append("retestPlanned =").append(retestPlanned).append(", ");
    stringbuffer.append("nextStackTestDate =").append(nextStackTestDate).append(", ");
    stringbuffer.append("nextStackTestDateNote =").append(nextStackTestDateNote).append(", ");
    stringbuffer.append("fuelCappingUnderLimit =").append(fuelCappingUnderLimit).append(", ");
    stringbuffer.append("actiontaken =").append(actiontaken).append(", ");
    stringbuffer.append("dobsignoff =").append(dobsignoff).append(", ");
    stringbuffer.append("firedepartmentapproval =").append(firedepartmentapproval).append(", ");
    stringbuffer.append("permitStatus =").append(permitStatus).append(", ");
    stringbuffer.append("dobfiling =").append(dobfiling).append(", ");
    stringbuffer.append("capablefuel").append(capablefuel).append(", ");
   // stringbuffer.append("oilfiring").append(oilfiring).append(", ");
    stringbuffer.append("isOilFiring").append(isOilFiring).append(", ");
    stringbuffer.append("permitExpire =").append(permitExpire);
    return stringbuffer.toString();
}

public StorageTankVo getFuelFromObj()
{
    if(fuelFromObj == null)
        try
        {
            fuelFromObj = StorageTankEntity.findByPrimaryKey(getFuelFrom());
        }
        catch(TrackingException trackingexception)
        {
            log.error(trackingexception);
        }
    return fuelFromObj;
}

public void setFuelFromObj(StorageTankVo storagetankvo)
{
    fuelFromObj = storagetankvo;
}

public StackVo getStackFromObj()
{
    if(stackFromObj == null)
        try
        {
            stackFromObj = StackEntity.findByPrimaryKey(getStackFrom());
        }
        catch(TrackingException trackingexception)
        {
            log.error(trackingexception);
        }
    return stackFromObj;
}

public void setStackFromObj(StackVo stackvo)
{
    stackFromObj = stackvo;
}

public StorageTankVo getDayTankObj()
{
    if(dayTankObj == null)
        try
        {
            dayTankObj = StorageTankEntity.findByPrimaryKey(getDayTankFrom());
        }
        catch(TrackingException trackingexception)
        {
            log.error(trackingexception);
        }
    return dayTankObj;
}

private static Log log = LogFactory.getLog(com.eespc.tracking.bo.GeneratorVo.class);
protected int id;
protected int buildingId;
protected String facilityDesignatedId;
protected String floor;
protected String stateId;
protected int primaryUse;
protected String yearInstalled;
protected int status;
protected String disconnecteddate;
protected String manufacturer;

protected String model;
protected String serial;
protected String actiontaken;
protected String burnerMake; 
protected String burnerModel;
protected String capacity;
protected int burnerType;
protected int primaryFuel;
protected int secondaryFuel;
protected int fuelFrom;
protected boolean hasDayTank;
protected int dayTankFrom;
protected String nycDob;
protected String mea;
protected String dep;
protected boolean sechduelC;
protected boolean planApproval;
protected int stackFrom;
protected boolean isRecordsInBoundBook;
protected boolean decPermitObtained;
protected boolean depPermitObtained;
protected String isStackProtSubmitted;
protected String testConductedBy;
protected boolean testRptSubmited;
protected boolean complyWithNoxPlan;
protected boolean retestPlanned;
protected String nextStackTestDate;
protected String nextStackTestDateNote;
protected boolean fuelCappingUnderLimit;
protected List fuelConsumptionList;
protected List o_fuelConsumptionList;
protected List permitList;
protected List cfrPermitList;
protected List engineRunningHrsList;
protected List o_pressureTestList;
protected List g_pressureTestList;
protected List anu_pressureTestList;
protected StorageTankVo fuelFromObj;
protected StackVo stackFromObj;
protected StorageTankVo dayTankObj;
protected String isStackTest;
protected String compyWithPMRactPlan;
protected String gasfuelgapping;
protected String gasemmisionfactor;
protected String oilfuelgapping;
protected String oilemmisionfactor;
protected String StackTestDate;
protected String ProtocalSubmittalDate;
protected String testPassed;
protected String fuelgapping;
protected String g_oilso2;
protected String g_gasso2;
protected String g_so2allowable;
protected String g_noxallowable;
protected String dobsignoff;
protected String firedepartmentapproval;
protected String permitExpire;
protected String permitStatus;
protected String dobfiling;
protected String capablefuel;
//protected String oilfiring;
protected boolean isOilFiring;

}