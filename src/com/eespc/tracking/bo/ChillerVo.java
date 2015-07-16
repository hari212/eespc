package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.ChillerEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.bo:
//            StackVo, StorageTankVo

public class ChillerVo implements Serializable {

	public ChillerVo() {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		stackFromObj = null;
		fuelFromObj = null;
		make = null;
		model = null;
		floor = null;
		chDisconnectedOn = null;
		serialNo = null;
		yearInstalled = null;
		chType = -99;
		burnerType = -99;
		primFuel = -99;
		secFuel = -99;
		mea = null;
		actiontaken = null;
		fuelFrom = -99;
		stackFrom = -99;
		capacity = null;
		isDOBApproved = null;
		isDEPApproved = null;
		modifiedinpast = -99;
		disconnectedyear = null;
		isChillerFuel = false;
		isTitleV = false;
		permitInfoList = null;
		consumptionList = null;
		chCapacityton = null;
		chDEPExpirationDate = null;
		dobjobnumber = null;
		chFootnote = null;
		chComments = null;
		dobsignoff = null;
		eupAvailable = null;
		chillerOperated = -99;
	}

	public ChillerVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		stackFromObj = null;
		fuelFromObj = null;
		make = null;
		model = null;
		floor = null;
		serialNo = null;
		yearInstalled = null;
		chType = -99;
		burnerType = -99;
		primFuel = -99;
		secFuel = -99;
		mea = null;
		actiontaken = null;
		fuelFrom = -99;
		stackFrom = -99;
		capacity = null;
		modifiedinpast = -99;
		disconnectedyear = null;
		isDOBApproved = null;
		isDEPApproved = null;
		isChillerFuel = false;
		isTitleV = false;
		permitInfoList = null;
		consumptionList = null;
		chCapacityton = null;
		chDEPExpirationDate = null;
		dobjobnumber = null;
		chFootnote = null;
		chComments = null;
		dobsignoff = null;
		eupAvailable = null;
		chillerOperated = -99;

		id = resultset.getInt("CHILLERABSORBERID");
		buildingId = resultset.getInt("BUILDINGID");
		String s = resultset.getString("TYPE");
		if (UtilityObject.isNotEmpty(s))
			chType = resultset.getInt("TYPE");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		make = resultset.getString("MAKE");
		yearInstalled = resultset.getString("YEARINSTALLED");
		model = resultset.getString("MODEL");
		floor = resultset.getString("FLOOR");
		serialNo = resultset.getString("SERIAL");
		capacity = resultset.getString("CAPACITY");
		burnerType = resultset.getInt("BURNERTYPE");
		primFuel = resultset.getInt("PRIMARYFUEL");
		secFuel = resultset.getInt("SECONDARYFUEL");
		fuelFrom = resultset.getInt("FUELFROM");
		stackFrom = resultset.getInt("STACKFROM");
		mea = resultset.getString("MEA");

		chCapacityton = resultset.getString("CAPACITYTON");
		chDEPExpirationDate = resultset.getString("EXPIRATIONDATE");
		dobjobnumber = resultset.getString("DOBJOBNUMBER");
		chFootnote = resultset.getString("CHFOOTNOTE");
		chComments = resultset.getString("CHCOMMENTS");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		eupAvailable = resultset.getString("EUPAVAILABLE");
		actiontaken = resultset.getString("ACTIONTAKEN");
		s = resultset.getString("CHILLERHASFUELFIRINGABILITY");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isChillerFuel = true;
		else if (s != null && s.trim().equalsIgnoreCase("N"))
			isChillerFuel = false;

		isDOBApproved = resultset.getString("DOBAPPROVAL");
		modifiedinpast = resultset.getInt("MODIFIEDINPAST");
		chillerOperated = resultset.getInt("CHILLEROPERTAEDBY");
		yearInstalled = resultset.getString("YEARINSTALLED");
		disconnectedyear = resultset.getString("DISCONNECTEDYEAR");
		String ss = resultset.getString("DEPAPPROVAL");
		isDEPApproved = ss;

		s = resultset.getString("TITLEVPERMITAPPLICATION");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isTitleV = true;
		else if (s != null && s.trim().equalsIgnoreCase("N"))
			isTitleV = false;
	}

	public String getEupAvailable() {
		return eupAvailable;
	}

	public void setEupAvailable(String s) {
		eupAvailable = s;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getChCapacityton() {
		return chCapacityton;
	}

	public void setChCapacityton(String s) {
		chCapacityton = s;
	}

	public String getChDEPExpirationDate() {
		return chDEPExpirationDate;
	}

	public void setChDEPExpirationDate(String s) {
		chDEPExpirationDate = s;
	}

	public void setDobjobnumber(String s) {
		dobjobnumber = s;
	}

	public String getDobjobnumber() {
		return dobjobnumber;
	}

	public String getChFootnote() {
		return chFootnote;
	}

	public void setChFootnote(String s) {
		chFootnote = s;
	}

	public String getChComments() {
		return chComments;
	}

	public void setChComments(String s) {
		chComments = s;
	}

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

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
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

	public void setchDisconnectedOn(String s) {
		chDisconnectedOn = s;
	}

	public String getchDisconnectedOn() {
		return chDisconnectedOn;
	}

	public int getChType() {
		return chType;
	}

	public void setChType(int i) {
		chType = i;
	}

	public int getBurnerType() {
		return burnerType;
	}

	public void setBurnerType(int i) {
		burnerType = i;
	}

	public int getPrimFuel() {
		return primFuel;
	}

	public void setPrimFuel(int i) {
		primFuel = i;
	}

	public int getsecFuel() {
		return secFuel;
	}

	public void setSecFuel(int i) {
		secFuel = i;
	}

	public int getStackFrom() {
		return stackFrom;
	}

	public void setStackFrom(int i) {
		stackFrom = i;
	}

	public int getfuelFrom() {
		return fuelFrom;
	}

	public void setfuelFrom(int i) {
		fuelFrom = i;
	}

	public String getMEA() {
		return mea;
	}

	public void setMEA(String s) {
		mea = s;
	}

	public String getActionTaken() {
		return actiontaken;
	}

	public void setActionTaken(String s) {
		actiontaken = s;
	}

	public boolean isChillerFuel() {
		return isChillerFuel;
	}

	public void setChillerFuel(boolean flag) {
		isChillerFuel = flag;
	}

	public boolean isTitleV() {
		return isTitleV;
	}

	public void setTitleV(boolean flag) {
		isTitleV = flag;
	}

	public String isDOBApproved() {
		return isDOBApproved;
	}

	public void setDOBApproved(String flag) {
		isDOBApproved = flag;
	}

	public String isDEPApproved() {
		return isDEPApproved;
	}

	public void setDEPApproved(String flag) {
		isDEPApproved = flag;
	}

	public StorageTankVo getFuelFromObj() {
		if (fuelFromObj == null)
			try {
				fuelFromObj = StorageTankEntity.findByPrimaryKey(getfuelFrom());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return fuelFromObj;
	}

	public void setFuelFromObj(StorageTankVo storagetankvo) {
		fuelFromObj = storagetankvo;
	}

	public StackVo getStackFromObj() {
		if (stackFromObj == null)
			try {
				stackFromObj = StackEntity.findByPrimaryKey(getStackFrom());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return stackFromObj;
	}

	public void setStackFromObj(StackVo stackvo) {
		stackFromObj = stackvo;
	}

	public List getConsumptionList() {
		if (consumptionList == null) {
			log.debug((new StringBuilder())
					.append("Consum Info List is NULL and ID = ")
					.append(getId()).toString());
			try {
				consumptionList = ChillerEntity.getFuelConsumption(getId());
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
				permitInfoList = ChillerEntity.getPermitInfo(getId());
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

	public int getChillerOperated() {
		return chillerOperated;
	}

	public void setChillerOperated(int i) {
		chillerOperated = i;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id);
		stringbuffer.append(",buildingId =").append(buildingId);
		stringbuffer.append(",type =").append(chType);
		stringbuffer.append(",facilityDesignatedId =").append(
				facilityDesignatedId);
		stringbuffer.append(",make =").append(make);
		stringbuffer.append(",model =").append(model);
		stringbuffer.append(",capacity =").append(capacity);
		stringbuffer.append(",floor =").append(floor);
		stringbuffer.append(",serialNo=").append(serialNo);
		stringbuffer.append(",yearInstalled=").append(yearInstalled);
		stringbuffer.append(",burnerType=").append(burnerType);
		stringbuffer.append(",primFuel=").append(primFuel);
		stringbuffer.append(",secFuel=").append(secFuel);
		stringbuffer.append(",fuelFrom=").append(fuelFrom);
		stringbuffer.append(",stackFrom=").append(stackFrom);
		stringbuffer.append(",isChillerFuel=").append(isChillerFuel);
		stringbuffer.append(",isTitleV=").append(isTitleV);
		stringbuffer.append(",isDOBApproved=").append(isDOBApproved);
		stringbuffer.append(",isDEPApproved=").append(isDEPApproved);
		stringbuffer.append(",mea=").append(mea);
		stringbuffer.append(",disconnectedyear=").append(disconnectedyear);
		stringbuffer.append(",modifiedinpast=").append(modifiedinpast);
		stringbuffer.append(",chillerOperated=").append(chillerOperated);
		stringbuffer.append(",yearInstalled=").append(yearInstalled);
		stringbuffer.append(",actiontaken=").append(actiontaken);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.ChillerVo.class);
	protected int id;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected StackVo stackFromObj;
	protected StorageTankVo fuelFromObj;
	protected String make;
	protected String model;
	protected String floor;
	protected String serialNo;
	protected String yearInstalled;
	protected int chType;
	protected int burnerType;
	protected int primFuel;
	protected int secFuel;
	protected String mea;
	protected String actiontaken;
	protected int fuelFrom;
	protected int stackFrom;
	protected String capacity;
	protected String chDisconnectedOn;
	protected String isDOBApproved;
	protected boolean ModifiedInPast;
	protected String isDEPApproved;
	protected boolean isChillerFuel;
	protected boolean isTitleV;
	protected List permitInfoList;
	protected List consumptionList;
	protected String disconnectedyear;
	protected int modifiedinpast;
	protected String chCapacityton;
	protected String chDEPExpirationDate;
	protected String dobjobnumber;
	protected String chFootnote;
	protected String chComments;
	protected String dobsignoff;
	protected String eupAvailable;
	protected int chillerOperated;

}