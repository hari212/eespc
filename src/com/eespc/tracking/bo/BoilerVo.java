package com.eespc.tracking.bo;

import com.eespc.tracking.entity.*;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// Referenced classes of package com.eespc.tracking.bo:
//            StorageTankVo, StackVo

public class BoilerVo
    implements Serializable
{

    public BoilerVo()
    {
        id = -99;
        buildingId = -99;
        facilityDesignatedId = null;
        floor = null;
        stateId = null;
        primaryUse = -99;
        yearInstalled = null;
        manufacturer = null;
        make = null;
        model = null;
        serial = null;
        burnerMake = null;
        burnerModel = null;
        capacity = null;
        burnerType = -99;
        primaryFuel = -99;
        secondaryFuel = -99;
        fuelFrom = -99;
        fuelFromObj = null;
        nycDob = null;
        mea = null;
        dep = null;
        sechduelC = false;
        planApproval = false;
        stackFrom = -99;
        stackFromObj = null;
        
        otherBoilersCombined = false;
        parameters = -99;
        testOnFuel = -99;
        stackTestProtSubmited = false;
        testConductedBy = null;
        testReportSubmited = null;
        protocolSubmitDate = null;
        boilerTestPassed = false;
        retestPlanned = false;
        StackTestDate = null;
        nextStackTestDate = null;
        isBoilerInCompliance = false;
        isBoilerSubjectToNspc = false;
        blrModifiedInPast = false;
        blrModifiedOn = null;
        isModifyPermitSub = false;
        anyEmission = false;
        blrSubjectToFederal = false;
        fuelCaping = null;
        isRollingAvg = false;
        isOpacityMonInst = false;
        prfmTestProtSub = false;
        prfmTestProtSubDate = null;
        prfmTestRptSub = false;
        prfmTestRptSubDate = null;
        isSulpContentAnaly = false;
        sulphurContent = null;
        modifiedinpast = -99;
        disconnectedyear = null;
        fuelLimitsForBlr = false;
        fuelLimitQnty = null;
        nitrogenContent = false;
        nitrogenContentQnty = null;
        annualTuneUpList = null;
        valveTestList = null;
        fuelConsumptionList = null;
        o_fuelConsumptionList = null;
        permitList = null;
        comments = null;
        footnote = null;
        oilemmisionfactor = null;
        gasemmisionfactor = null;
        gasfuelgapping = null;
        oilfuelgapping = null;
        primaryuseother = null;
        
        
        B_oilso2 = null;
        B_gasso2 = null;
        B_so2allowable=null;
		B_noxallowable=null;
		
		dobfiling=null;
		dobsignoff=null;
		firedepartmentapproval=null;
		isDepPermit=null;
		isDepPermitExpire=null;
		boilerTuneup=null;
		
		stackTestRequired = null;

    }

    public BoilerVo(ResultSet resultset)
        throws SQLException
    {
        id = -99;
        buildingId = -99;
        facilityDesignatedId = null;
        floor = null;
        stateId = null;
        primaryUse = -99;
        yearInstalled = null;
        manufacturer = null;
        make = null;
        model = null;
        serial = null;
        burnerMake = null;
        burnerModel = null;
        capacity = null;
        burnerType = -99;
        primaryFuel = -99;
        secondaryFuel = -99;
        fuelFrom = -99;
        fuelFromObj = null;
        nycDob = null;
        mea = null;
        dep = null;
        sechduelC = false;
        planApproval = false;
        stackFrom = -99;
        stackFromObj = null;
        stackTestRequired = null;
        otherBoilersCombined = false;
        parameters = -99;
        testOnFuel = -99;
        stackTestProtSubmited = false;
        testConductedBy = null;
        testReportSubmited = null;
        protocolSubmitDate = null;
        boilerTestPassed = false;
        retestPlanned = false;
        StackTestDate = null;
        nextStackTestDate = null;
        isBoilerInCompliance = false;
        isBoilerSubjectToNspc = false;
        blrModifiedInPast = false;
        blrModifiedOn = null;
        isModifyPermitSub = false;
        anyEmission = false;
        blrSubjectToFederal = false;
        fuelCaping = null;
        isRollingAvg = false;
        isOpacityMonInst = false;
        prfmTestProtSub = false;
        prfmTestProtSubDate = null;
        prfmTestRptSub = false;
        prfmTestRptSubDate = null;
        isSulpContentAnaly = false;
        sulphurContent = null;
        modifiedinpast = -99;
        disconnectedyear = null;
        fuelLimitsForBlr = false;
        fuelLimitQnty = null;
        nitrogenContent = false;
        nitrogenContentQnty = null;
        annualTuneUpList = null;
        fuelConsumptionList = null;
        o_fuelConsumptionList = null;
        permitList = null;
        comments = null;
        footnote = null;
        oilemmisionfactor = null;
        gasemmisionfactor = null;
        gasfuelgapping = null;
        oilfuelgapping = null;
        primaryuseother = null;
        B_oilso2 = null;
        B_gasso2 = null;
        B_so2allowable=null;
		B_noxallowable=null;
		dobsignoff=null;
		firedepartmentapproval=null;
		isDepPermit=null;
		isDepPermitExpire=null;
		boilerTuneup=null;
		dobfiling=null;
		valveTestList = null;

        id = UtilityObject.getIntFromString(resultset.getString("BOILERID"));
        buildingId = UtilityObject.getIntFromString(resultset.getString("BUILDINGID"));
        facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
        floor = resultset.getString("FLOOR");
        stateId = resultset.getString("STATEID");
        primaryUse = UtilityObject.getIntFromString(resultset.getString("PRIMARYUSE"));
        yearInstalled = resultset.getString("YEARINSTALLED");
        manufacturer = resultset.getString("MANUFACTURER");
        make = resultset.getString("MAKE");
        model = resultset.getString("MODEL");
        comments = resultset.getString("BCOMMENTS");
        serial = resultset.getString("SERIAL");
        burnerMake = resultset.getString("BURNERMAKE");
        burnerModel = resultset.getString("BURNERMODEL");
        capacity = resultset.getString("CAPACITY");
        burnerType = UtilityObject.getIntFromString(resultset.getString("BURNERTYPE"));
        primaryFuel = UtilityObject.getIntFromString(resultset.getString("PRIMARYFUEL"));
        secondaryFuel = UtilityObject.getIntFromString(resultset.getString("SECONDARYFUEL"));
        fuelFrom = UtilityObject.getIntFromString(resultset.getString("FUELFROM"));
        nycDob = resultset.getString("NYCDOB");
        mea = resultset.getString("MEA");
        dep = resultset.getString("DEP");
        sechduelC = UtilityObject.convertBoolean(resultset.getString("SECHDUELC"));
        planApproval = UtilityObject.convertBoolean(resultset.getString("PLANAPPROVAL"));
        stackFrom = UtilityObject.getIntFromString(resultset.getString("STACKFROM"));
        stackTestRequired = resultset.getString("STACKTESTREQUIRED");
        otherBoilersCombined = UtilityObject.convertBoolean(resultset.getString("OTHERBOILERSCOMBINED"));
        parameters = UtilityObject.getIntFromString(resultset.getString("PARAMETERS"));
        testOnFuel = UtilityObject.getIntFromString(resultset.getString("TESTONFUEL"));
        stackTestProtSubmited = UtilityObject.convertBoolean(resultset.getString("STACKTESTPROTSUBMITED"));
        testConductedBy = resultset.getString("TESTCONDUCTEDBY");
        testReportSubmited = resultset.getString("TESTRPTSUBMITED");
        protocolSubmitDate = resultset.getString("PROTOCOLSUBMITDATE");
        boilerTestPassed = UtilityObject.convertBoolean(resultset.getString("BOILERTESTPASSED"));
        retestPlanned = UtilityObject.convertBoolean(resultset.getString("RETESTPLANNED"));
        StackTestDate = resultset.getString("STACKTESTDATE");
        nextStackTestDate = resultset.getString("NEXTSTACKTESTDATE");
        isBoilerInCompliance = UtilityObject.convertBoolean(resultset.getString("ISBLRCOMPLIANCE"));
        modifiedinpast = resultset.getInt("MODIFIEDINPAST");
        disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
        isBoilerSubjectToNspc = UtilityObject.convertBoolean(resultset.getString("ISBLRSUBJECTTONSPC"));
        blrModifiedInPast = UtilityObject.convertBoolean(resultset.getString("BLRMODIFIEDINPAST"));
        blrModifiedOn = resultset.getString("BLRMODIFIEDON");
        isModifyPermitSub = UtilityObject.convertBoolean(resultset.getString("ISMODIFYPERMITSUB"));
        anyEmission = UtilityObject.convertBoolean(resultset.getString("ANYEMISSION"));
        blrSubjectToFederal = UtilityObject.convertBoolean(resultset.getString("BLRSUBJECTTOFEDERAL"));	
        fuelCaping = resultset.getString("FUELCAPING");
        isRollingAvg = UtilityObject.convertBoolean(resultset.getString("ISROLLINGAVG"));
        isOpacityMonInst = UtilityObject.convertBoolean(resultset.getString("ISOPACITYMONINST"));
        prfmTestProtSub = UtilityObject.convertBoolean(resultset.getString("PRFMTESTPROTSUB"));
        prfmTestProtSubDate = resultset.getString("PRFMTESTPROTSUBDATE");
        prfmTestRptSub = UtilityObject.convertBoolean(resultset.getString("PRFMTESTRPTSUB"));
        prfmTestRptSubDate = resultset.getString("PRFMTESTRPTSUBDATE");
        isSulpContentAnaly = UtilityObject.convertBoolean(resultset.getString("ISSULPCONANLY"));
        sulphurContent = resultset.getString("SULPCONTENT");
        fuelLimitsForBlr = UtilityObject.convertBoolean(resultset.getString("FUELLIMITSFORBLR"));
        fuelLimitQnty = resultset.getString("FUELLIMITQNTY");
        nitrogenContent = UtilityObject.convertBoolean(resultset.getString("NITGNCONTNT"));
        nitrogenContentQnty = resultset.getString("NITGNCONTENT");

        footnote = resultset.getString("BFOOTNOTE");
        oilemmisionfactor = resultset.getString("OILEMMISSIONFACTOR");
        gasemmisionfactor = resultset.getString("GASEMMISSIONFACTOR");
        gasfuelgapping = resultset.getString("GASFUELGAPPING");
        oilfuelgapping = resultset.getString("OILFUELGAPPING");
        primaryuseother = resultset.getString("PRIMARYUSEOTHER");

        B_oilso2 = resultset.getString("B_OILSO2");
        B_gasso2 = resultset.getString("B_GASSO2");

        B_so2allowable=resultset.getString("B_SO2ALLOWABLE");
		B_noxallowable=resultset.getString("B_NOXALLOWABLE");        
		firedepartmentapproval=resultset.getString("FIREDEPARTMENTAPPROVAL");
		
		
		dobsignoff=resultset.getString("DOBSIGNOFF");
		
		String ss = resultset.getString("DEPPERMITSTATUS");
        isDepPermit = ss;

        isDepPermitExpire = resultset.getString("DEPPERMITSTATUSEXPIRE");
		boilerTuneup = resultset.getString("BOILERTUNEUP");
		dobfiling=resultset.getString("DOBFILING");
    }

	public String getDobFiling()
    {
        return dobfiling;
    }

    public void setDobFiling(String s)
    {
        dobfiling = s;
    }

	public String getBoilerTuneup()
    {
        return boilerTuneup;
    }

    public void setBoilerTuneup(String flag)
    {
        boilerTuneup = flag;
    }


	public String isDepPermitExpire()
    {
        return isDepPermitExpire;
    }

    public void setDepPermitExpire(String flag)
    {
        isDepPermitExpire = flag;
    }


 	public String isDepPermit()
    {
        return isDepPermit;
    }

    public void setDepPermit(String flag)
    {
        isDepPermit = flag;
    }


    public String getDobsignoff()
	{
	return dobsignoff;
	}
	public void setDobsignoff(String s)
	{
	dobsignoff= s;
	}

	public String getFiredepartmentapproval()
	{
	return firedepartmentapproval;
	}
	public void setFiredepartmentapproval(String s)
	{
	firedepartmentapproval= s;
	}

    public String getB_so2allowable()
	{
	return B_so2allowable;
	}
	public void setB_so2allowable(String s)
	{
	B_so2allowable= s;
	}

	public String getB_noxallowable()
	{
	return B_noxallowable;
	}
	public void setB_noxallowable(String s)
	{
	B_noxallowable= s;
	}


    public boolean isAnyEmission()
    {
        return anyEmission;
    }

    public void setAnyEmission(boolean flag)
    {
        anyEmission = flag;
    }

    public String getDisconnectedYear()
    {
        return disconnectedyear;
    }

    public void setDisconnectedYear(String s)
    {
        disconnectedyear = s;
    }

    public int getModifiedInPast()
    {
        return modifiedinpast;
    }

    public void setModifiedInPast(int i)
    {
        modifiedinpast = i;
    }

    public boolean isBlrModifiedInPast()
    {
        return blrModifiedInPast;
    }

    public void setBlrModifiedInPast(boolean flag)
    {
        blrModifiedInPast = flag;
    }

    public String getBlrModifiedOn()
    {
        return blrModifiedOn;
    }

    public void setBlrModifiedOn(String s)
    {
        blrModifiedOn = s;
    }

    public boolean isBlrSubjectToFederal()
    {
        return blrSubjectToFederal;
    }

    public void setBlrSubjectToFederal(boolean flag)
    {
        blrSubjectToFederal = flag;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int i)
    {
        id = i;
    }

    public boolean isBoilerTestPassed()
    {
        return boilerTestPassed;
    }

    public void setBoilerTestPassed(boolean flag)
    {
        boilerTestPassed = flag;
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




    public String getComments()
    {
        return comments;
    }

    public void setComments(String s)
    {
        comments = s;
    }



    public void setFootnote(String s)
    {
        footnote = s;
    }
    public String getFootnote()
    {
        return footnote;
    }

    public void setOilemmisionfactor(String s)
    {
        oilemmisionfactor = s;
    }
    public String getOilemmisionfactor()
    {
        return oilemmisionfactor;
    }
    public void setGasemmisionfactor(String s)
    {
        gasemmisionfactor = s;
    }

    public String getGasemmisionfactor()
    {
        return gasemmisionfactor;
    }

    public void setGasfuelgapping(String s)
    {
        gasfuelgapping = s;
    }
	public String getGasfuelgapping()
    {
        return gasfuelgapping;
    }


    public String getOilfuelgapping()
    {
        return oilfuelgapping;
    }

    public void setOilfuelgapping(String s)
    {
        oilfuelgapping = s;
    }

    public String getPrimaryuseother()
    {
        return primaryuseother;
    }

    public void setPrimaryuseother(String s)
    {
        primaryuseother = s;
    }



    public String getBurnerModel()
    {
        return burnerModel;
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

    public String getFuelCaping()
    {
        return fuelCaping;
    }

    public void setFuelCaping(String s)
    {
        fuelCaping = s;
    }

    public int getFuelFrom()
    {
        return fuelFrom;
    }

    public void setFuelFrom(int i)
    {
        fuelFrom = i;
    }

    public String getFuelLimitQnty()
    {
        return fuelLimitQnty;
    }

    public void setFuelLimitQnty(String s)
    {
        fuelLimitQnty = s;
    }

    public boolean isFuelLimitsForBlr()
    {
        return fuelLimitsForBlr;
    }

    public void setFuelLimitsForBlr(boolean flag)
    {
        fuelLimitsForBlr = flag;
    }

    public boolean isBoilerInCompliance()
    {
        return isBoilerInCompliance;
    }

    public void setBoilerInCompliance(boolean flag)
    {
        isBoilerInCompliance = flag;
    }

    public boolean isBoilerSubjectToNspc()
    {
        return isBoilerSubjectToNspc;
    }

    public void setBoilerSubjectToNspc(boolean flag)
    {
        isBoilerSubjectToNspc = flag;
    }

    public boolean isModifyPermitSub()
    {
        return isModifyPermitSub;
    }

    public void setModifyPermitSub(boolean flag)
    {
        isModifyPermitSub = flag;
    }

    public boolean isOpacityMonInst()
    {
        return isOpacityMonInst;
    }

    public void setOpacityMonInst(boolean flag)
    {
        isOpacityMonInst = flag;
    }

    public boolean isRollingAvg()
    {
        return isRollingAvg;
    }

    public void setRollingAvg(boolean flag)
    {
        isRollingAvg = flag;
    }

    public boolean isSulpContentAnaly()
    {
        return isSulpContentAnaly;
    }

    public void setSulpContentAnaly(boolean flag)
    {
        isSulpContentAnaly = flag;
    }

    public String getMake()
    {
        return make;
    }

    public void setMake(String s)
    {
        make = s;
    }

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

    public String getStackTestDate()
    {
        return StackTestDate;
    }

    public void setStackTestDate(String s)
    {
        StackTestDate = s;
    }

    public boolean isNitrogenContent()
    {
        return nitrogenContent;
    }

    public void setNitrogenContent(boolean flag)
    {
        nitrogenContent = flag;
    }

    public String getNitrogenContentQnty()
    {
        return nitrogenContentQnty;
    }

    public void setNitrogenContentQnty(String s)
    {
        nitrogenContentQnty = s;
    }

    public String getNycDob()
    {
        return nycDob;
    }

    public void setNycDob(String s)
    {
        nycDob = s;
    }

    public boolean isOtherBoilersCombined()
    {
        return otherBoilersCombined;
    }

    public void setOtherBoilersCombined(boolean flag)
    {
        otherBoilersCombined = flag;
    }

    public int getParameters()
    {
        return parameters;
    }

    public void setParameters(int i)
    {
        parameters = i;
    }

    public boolean isPlanApproval()
    {
        return planApproval;
    }

    public void setPlanApproval(boolean flag)
    {
        planApproval = flag;
    }

    public boolean isPrfmTestProtSub()
    {
        return prfmTestProtSub;
    }

    public void setPrfmTestProtSub(boolean flag)
    {
        prfmTestProtSub = flag;
    }

    public String getPrfmTestProtSubDate()
    {
        return prfmTestProtSubDate;
    }

    public void setPrfmTestProtSubDate(String s)
    {
        prfmTestProtSubDate = s;
    }

    public boolean isPrfmTestRptSub()
    {
        return prfmTestRptSub;
    }

    public void setPrfmTestRptSub(boolean flag)
    {
        prfmTestRptSub = flag;
    }

    public String getPrfmTestRptSubDate()
    {
        return prfmTestRptSubDate;
    }

    public void setPrfmTestRptSubDate(String s)
    {
        prfmTestRptSubDate = s;
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

    public String getProtocolSubmitDate()
    {
        return protocolSubmitDate;
    }

    public void setProtocolSubmitDate(String s)
    {
        protocolSubmitDate = s;
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

    public boolean isStackTestProtSubmited()
    {
        return stackTestProtSubmited;
    }

    public void setStackTestProtSubmited(boolean flag)
    {
        stackTestProtSubmited = flag;
    }

    public String getStackTestRequired()
    {
        return stackTestRequired;
    }

    public void setStackTestRequired(String s)
    {
        stackTestRequired = s;
    }

    public String getStateId()
    {
        return stateId;
    }

    public void setStateId(String s)
    {
        stateId = s;
    }

    public String getSulphurContent()
    {
        return sulphurContent;
    }

    public void setSulphurContent(String s)
    {
        sulphurContent = s;
    }


    public String getB_oilso2()
    {
        return B_oilso2;
    }

    public void setB_oilso2(String s)
    {
        B_oilso2 = s;
    }


	public String getB_gasso2()
    {
        return B_gasso2;
    }

    public void setB_gasso2(String s)
    {
        B_gasso2 = s;
    }


    public String getTestConductedBy()
    {
        return testConductedBy;
    }

    public void setTestConductedBy(String s)
    {
        testConductedBy = s;
    }

    public int getTestOnFuel()
    {
        return testOnFuel;
    }

    public void setTestOnFuel(int i)
    {
        testOnFuel = i;
    }

    public String getTestReportSubmited()
    {
        return testReportSubmited;
    }

    public void setTestReportSubmited(String s)
    {
        testReportSubmited = s;
    }

    public String getYearInstalled()
    {
        return yearInstalled;
    }

    public void setYearInstalled(String s)
    {
        yearInstalled = s;
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

    public List getAnnualTuneUpList()
    {
        if(annualTuneUpList == null)
            try
            {
                annualTuneUpList = BoilerEntity.getAnnaulTuneUpList(getId());
            }
            catch(Exception exception)
            {
                log.error(exception);
            }
        return annualTuneUpList;
    }

    public void setAnnualTuneUpList(List list)
    {
        annualTuneUpList = list;
    }
    
    
    public List getValveTestList()
    {
        if(valveTestList == null)
            try
            {
                valveTestList = BoilerEntity.getValveTestList(getId());
            }
            catch(Exception exception)
            {
                log.error(exception);
            }
        return valveTestList;
    }

    public void setValveTestList(List list)
    {
        valveTestList = list;
    }

    public List getFuelConsumptionList()
    {
       if(fuelConsumptionList == null)
            try
            {
                fuelConsumptionList = BoilerEntity.getFuelConsumptionList(getId());
            }
            catch(Exception exception)
            {
                log.error(exception);
            }
        return fuelConsumptionList;
    }

    public List geto_FuelConsumptionList()
    {

        if(o_fuelConsumptionList == null)
            try
            {
                o_fuelConsumptionList = BoilerEntity.geto_FuelConsumptionList(getId());
            }
            catch(Exception exception)
            {
                log.error(exception);
            }
        return o_fuelConsumptionList;
    }

    public void setFuelConsumptionList(List list)
    {
        fuelConsumptionList = list;
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
                permitList = BoilerEntity.getPermitList(getId());
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

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = ", ";
        stringbuffer.append("id   =").append(id).append(s);
        stringbuffer.append("buildingId   =").append(buildingId).append(s);
        stringbuffer.append("facilityDesignatedId     =").append(facilityDesignatedId).append(s);
        stringbuffer.append("floor     =").append(floor).append(s);
        stringbuffer.append("stateId   =").append(stateId).append(s);
        stringbuffer.append("primaryUse   =").append(primaryUse).append(s);
        stringbuffer.append("yearInstalled=").append(yearInstalled).append(s);
        stringbuffer.append("manufacturer =").append(manufacturer).append(s);
        stringbuffer.append("make=").append(make).append(s);
        stringbuffer.append("model     =").append(model).append(s);
        stringbuffer.append("comments     =").append(comments).append(s);
        stringbuffer.append("serial    =").append(serial).append(s);
        stringbuffer.append("burnerMake   =").append(burnerMake).append(s);
        stringbuffer.append("burnerModel  =").append(burnerModel).append(s);
        stringbuffer.append("capacity  =").append(capacity).append(s);
        stringbuffer.append("burnerType   =").append(burnerType).append(s);
        stringbuffer.append("primaryFuel  =").append(primaryFuel).append(s);
        stringbuffer.append("secondaryFuel   =").append(secondaryFuel).append(s);
        stringbuffer.append("fuelFrom     =").append(fuelFrom).append(s);
        stringbuffer.append("nycDob    =").append(nycDob).append(s);
        stringbuffer.append("mea =").append(mea).append(s);
        stringbuffer.append("dep =").append(dep).append(s);
        stringbuffer.append("sechduelC   =").append(sechduelC).append(s);
        stringbuffer.append("planApproval=").append(planApproval).append(s);
        stringbuffer.append("stackFrom    =").append(stackFrom).append(s);
        stringbuffer.append("stackTestRequired =").append(stackTestRequired).append(s);
        stringbuffer.append("otherBoilersCombined    =").append(otherBoilersCombined).append(s);
        stringbuffer.append("parameters   =").append(parameters).append(s);
        stringbuffer.append("testOnFuel   =").append(testOnFuel).append(s);
        stringbuffer.append("stackTestProtSubmited   =").append(stackTestProtSubmited).append(s);
        stringbuffer.append("testConductedBy    =").append(testConductedBy).append(s);
        stringbuffer.append("testReportSubmited=").append(testReportSubmited).append(s);
        stringbuffer.append("protocolSubmitDate =").append(protocolSubmitDate).append(s);
        stringbuffer.append("boilerTestPassed  =").append(boilerTestPassed).append(s);
        stringbuffer.append("retestPlanned     =").append(retestPlanned).append(s);
        stringbuffer.append("StackTestDate  =").append(StackTestDate).append(s);
        stringbuffer.append("nextStackTestDate  =").append(nextStackTestDate).append(s);
        stringbuffer.append("isBoilerInCompliance    =").append(isBoilerInCompliance).append(s);
        stringbuffer.append("isBoilerSubjectToNspc   =").append(isBoilerSubjectToNspc).append(s);
        stringbuffer.append("blrModifiedInPast =").append(blrModifiedInPast).append(s);
        stringbuffer.append("blrModifiedOn=").append(blrModifiedOn).append(s);
        stringbuffer.append("isModifyPermitSub =").append(isModifyPermitSub).append(s);
        stringbuffer.append("anyEmission =").append(anyEmission).append(s);
        stringbuffer.append("blrSubjectToFederal     =").append(blrSubjectToFederal).append(s);
        stringbuffer.append("fuelCaping   =").append(fuelCaping).append(s);
        stringbuffer.append("isRollingAvg=").append(isRollingAvg).append(s);
        stringbuffer.append("isOpacityMonInst  =").append(isOpacityMonInst).append(s);
        stringbuffer.append("prfmTestProtSub   =").append(prfmTestProtSub).append(s);
        stringbuffer.append("prfmTestProtSubDate=").append(prfmTestProtSubDate).append(s);
        stringbuffer.append("prfmTestRptSub    =").append(prfmTestRptSub).append(s);
        stringbuffer.append("prfmTestRptSubDate =").append(prfmTestRptSubDate).append(s);
        stringbuffer.append("isSulpContentAnaly=").append(isSulpContentAnaly).append(s);
        stringbuffer.append("sulphurContent     =").append(sulphurContent).append(s);
        stringbuffer.append("fuelLimitsForBlr  =").append(fuelLimitsForBlr).append(s);
        stringbuffer.append("fuelLimitQnty=").append(fuelLimitQnty).append(s);
        stringbuffer.append("disconnectedyear=").append(disconnectedyear).append(s);
        stringbuffer.append("modifiedinpast=").append(modifiedinpast).append(s);
        stringbuffer.append("nitrogenContent   =").append(nitrogenContent).append(s);
        stringbuffer.append("footnote   =").append(footnote).append(s);
        stringbuffer.append("oilemmisionfactor   =").append(oilemmisionfactor).append(s);
        stringbuffer.append("gasemmisionfactor   =").append(gasemmisionfactor).append(s);
        stringbuffer.append("gasfuelgapping   =").append(gasfuelgapping).append(s);
        stringbuffer.append("oilfuelgapping   =").append(oilfuelgapping).append(s);
        stringbuffer.append("primaryuseother   =").append(primaryuseother).append(s);
        stringbuffer.append("nitrogenContentQnty=").append(nitrogenContentQnty).append(s);
        stringbuffer.append("isDepPermit=").append(isDepPermit).append(s);
        stringbuffer.append("isDepPermitExpire=").append(isDepPermitExpire).append(s);
        stringbuffer.append("dobfiling=").append(dobfiling).append(s);
        stringbuffer.append("boilerTuneup=").append(boilerTuneup);
        return stringbuffer.toString();
    }

    private static Log log = LogFactory.getLog(com.eespc.tracking.bo.BoilerVo.class);
    protected int id;
    protected int buildingId;
    protected String facilityDesignatedId;
    protected String floor;
    protected String stateId;
    protected int primaryUse;
    protected String yearInstalled;
    protected String manufacturer;
    protected String make;
    protected String model;
    protected String comments;
    protected String serial;
    protected String burnerMake;
    protected String burnerModel;
    protected String capacity;
    protected int burnerType;
    protected int primaryFuel;
    protected int secondaryFuel;
    protected int fuelFrom;
    protected StorageTankVo fuelFromObj;
    protected String nycDob;
    protected String mea;
    protected String dep;
    protected boolean sechduelC;
    protected boolean planApproval;
    protected int stackFrom;
    protected StackVo stackFromObj;
    protected String stackTestRequired;
    protected boolean otherBoilersCombined;
    protected int parameters;
    protected int testOnFuel;
    protected boolean stackTestProtSubmited;
    protected String testConductedBy;
    protected String testReportSubmited;
    protected String protocolSubmitDate;
    protected boolean boilerTestPassed;
    protected boolean retestPlanned;
    protected String StackTestDate;
    protected String nextStackTestDate;
    protected boolean isBoilerInCompliance;
    protected String disconnectedyear;
    protected int modifiedinpast;
    protected boolean isBoilerSubjectToNspc;
    protected boolean blrModifiedInPast;
    protected String blrModifiedOn;
    protected boolean isModifyPermitSub;
    protected boolean anyEmission;
    protected boolean blrSubjectToFederal;
    protected String fuelCaping;
    protected boolean isRollingAvg;
    protected boolean isOpacityMonInst;
    protected boolean prfmTestProtSub;
    protected String prfmTestProtSubDate;
    protected boolean prfmTestRptSub;
    protected String prfmTestRptSubDate;
    protected boolean isSulpContentAnaly;
    protected String sulphurContent;
    protected boolean fuelLimitsForBlr;
    protected String fuelLimitQnty;
    protected boolean nitrogenContent;
    protected String nitrogenContentQnty;
    protected List annualTuneUpList;
    protected List fuelConsumptionList;
    protected List o_fuelConsumptionList;
    protected List permitList;
    protected List valveTestList;
	protected String footnote;
    protected String oilemmisionfactor ;
    protected String gasemmisionfactor;
    protected String gasfuelgapping ;
    protected String oilfuelgapping ;
    protected String primaryuseother ;
    protected String B_oilso2 ;
    protected String B_gasso2 ;
    protected String B_so2allowable;
	protected String B_noxallowable;
	protected String dobsignoff;
	protected String firedepartmentapproval;
	protected String isDepPermit;
	protected String isDepPermitExpire;
	protected String dobfiling;
	protected String boilerTuneup;
}