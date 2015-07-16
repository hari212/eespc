package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.PaintSprayEntity;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class PaintSprayVo implements Serializable {

	public PaintSprayVo() {
		id = -99;
		buildingId = -99;
		stackId = -99;
		stackVo = null;
		facilityDesignatedId = null;
		floor = null;
		yearinstalled = null;
		status = -99;
		disconnecteddate = null;
		make = null;
		model = null;
		ActionTaken = null;
		hrsOfOperation = null;
		isExemptedByDec = false;
		isUnitIncludedInDecPermit = false;
		location = -99;
		permitInfoList = null;
		chemicalsUsedList = null;
		psDEPNumber = null;
		spraypaint = null;
		spraysolvant = null;
		sprayink = null;
		sprayother = null;
		spraypaintname = null;
		spraysolvantname = null;
		sprayinkname = null;
		sprayothername = null;
		spraypaintvocpercent = null;
		spraysolvantvocpercent = null;
		sprayinkvocpercent = null;
		sprayothervocpercent = null;
		spraypaintvolume = null;
		spraysolvantvolume = null;
		sprayinkvolume = null;
		sprayothervolume = null;
		spraypaintdensity = null;
		spraysolvantdensity = null;
		sprayinkdensity = null;
		sprayotherdensity = null;
		spraypaintvoc = null;
		spraysolvantvoc = null;
		sprayinkvoc = null;
		sprayothervoc = null;
		spraypaintcap = null;
		spraysolvantcap = null;
		sprayinkcap = null;
		vocyear = null;
		vocmonth = null;
		voccap = null;
		vocmonthlyquantity = null;
		vocmonthlylimit = null;
		psHoursOfOperationyear = null;
		dobsignoff = null;
		dobnumber = null;
	}

	public PaintSprayVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		stackId = -99;
		stackVo = null;
		facilityDesignatedId = null;
		floor = null;
		yearinstalled = null;
		status = -99;
		disconnecteddate = null;
		make = null;
		model = null;
		ActionTaken = null;
		hrsOfOperation = null;
		isExemptedByDec = false;
		isUnitIncludedInDecPermit = false;
		location = -99;
		permitInfoList = null;
		chemicalsUsedList = null;
		psDEPNumber = null;
		spraypaint = null;
		spraysolvant = null;
		sprayink = null;
		sprayother = null;
		spraypaintname = null;
		spraysolvantname = null;
		sprayinkname = null;
		sprayothername = null;
		spraypaintvocpercent = null;
		spraysolvantvocpercent = null;
		sprayinkvocpercent = null;
		sprayothervocpercent = null;
		spraypaintvolume = null;
		spraysolvantvolume = null;
		sprayinkvolume = null;
		sprayothervolume = null;
		spraypaintdensity = null;
		spraysolvantdensity = null;
		sprayinkdensity = null;
		sprayotherdensity = null;
		spraypaintvoc = null;
		spraysolvantvoc = null;
		sprayinkvoc = null;
		sprayothervoc = null;
		spraypaintcap = null;
		spraysolvantcap = null;
		sprayinkcap = null;
		vocyear = null;
		vocmonth = null;
		voccap = null;
		vocmonthlyquantity = null;
		vocmonthlylimit = null;
		psHoursOfOperationyear = null;
		dobsignoff = null;
		dobnumber = null;

		id = resultset.getInt("SPRAYBOOTHID");
		buildingId = resultset.getInt("BUILDINGID");
		stackId = resultset.getInt("STACKEXHAUSTID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		floor = resultset.getString("FLOOR");
		yearinstalled = resultset.getString("YEARINSTALLED");
		status = resultset.getInt("STATUS");
		disconnecteddate = resultset.getString("DISCONNECTEDDATE");
		make = resultset.getString("MAKE");
		model = resultset.getString("MODEL");
		ActionTaken = resultset.getString("ACTIONTAKEN");
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

		psDEPNumber = resultset.getString("PSDEPNUMBER");
		spraypaint = resultset.getString("SPRAYPAINT");
		spraysolvant = resultset.getString("SPRAYSOLVANT");
		sprayink = resultset.getString("SPRAYINK");
		sprayother = resultset.getString("SPRAYOTHER");
		spraypaintname = resultset.getString("SPRAYPAINTNAME");
		spraysolvantname = resultset.getString("SPRAYSOLVANTNAME");
		sprayinkname = resultset.getString("SPRAYINKNAME");
		sprayothername = resultset.getString("SPRAYOTHERNAME");
		spraypaintvocpercent = resultset.getString("SPRAYPAINTVOCPERCENT");
		spraysolvantvocpercent = resultset.getString("SPRAYSOLVANTVOCPERCENT");
		sprayinkvocpercent = resultset.getString("SPRAYINKVOCPERCENT");
		sprayothervocpercent = resultset.getString("SPRAYOTHERVOCPERCENT");
		spraypaintvolume = resultset.getString("SPRAYPAINTVOLUME");
		spraysolvantvolume = resultset.getString("SPRAYSOLVANTVOLUME");
		sprayinkvolume = resultset.getString("SPRAYINKVOLUME");
		sprayothervolume = resultset.getString("SPRAYOTHERVOLUME");
		spraypaintdensity = resultset.getString("SPRAYPAINTDENSITY");
		spraysolvantdensity = resultset.getString("SPRAYSOLVANTDENSITY");
		sprayinkdensity = resultset.getString("SPRAYINKDENSITY");
		sprayotherdensity = resultset.getString("SPRAYOTHERDENSITY");
		spraypaintvoc = resultset.getString("SPRAYPAINTVOC");
		spraysolvantvoc = resultset.getString("SPRAYSOLVANTVOC");
		sprayinkvoc = resultset.getString("SPRAYINKVOC");
		sprayothervoc = resultset.getString("SPRAYOTHERVOC");
		spraypaintcap = resultset.getString("SPRAYPAINTCAP");
		spraysolvantcap = resultset.getString("SPRAYSOLVANTCAP");
		sprayinkcap = resultset.getString("SPRAYINKCAP");
		vocyear = resultset.getString("VOCYEAR");
		vocmonth = resultset.getString("VOCMONTH");
		voccap = resultset.getString("VOCCAP");
		vocmonthlyquantity = resultset.getString("VOCMONTHLYQUANTITY");
		vocmonthlylimit = resultset.getString("VOCMONTHLYLIMIT");
		psHoursOfOperationyear = resultset.getString("PSHOURSOFOPERATIONYEAR");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		dobnumber = resultset.getString("DOBNUMBER");

	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getDobnumber() {
		return dobnumber;
	}

	public void setDobnumber(String s) {
		dobnumber = s;
	}

	public String getPsDEPNumber() {
		return psDEPNumber;
	}

	public void setPsDEPNumber(String s) {
		psDEPNumber = s;
	}

	public String getSpraypaint() {
		return spraypaint;
	}

	public void setSpraypaint(String s) {
		spraypaint = s;
	}

	public String getSpraysolvant() {
		return spraysolvant;
	}

	public void setSpraysolvant(String s) {
		spraysolvant = s;
	}

	public String getSprayink() {
		return sprayink;
	}

	public void setSprayink(String s) {
		sprayink = s;
	}

	public String getSprayother() {
		return sprayother;
	}

	public void setSprayother(String s) {
		sprayother = s;
	}

	public String getSpraypaintname() {
		return spraypaintname;
	}

	public void setSpraypaintname(String s) {
		spraypaintname = s;
	}

	public String getSpraysolvantname() {
		return spraysolvantname;
	}

	public void setSpraysolvantname(String s) {
		spraysolvantname = s;
	}

	public String getSprayinkname() {
		return sprayinkname;
	}

	public void setSprayinkname(String s) {
		sprayinkname = s;
	}

	public String getSprayothername() {
		return sprayothername;
	}

	public void setSprayothername(String s) {
		sprayothername = s;
	}

	public String getSpraypaintvocpercent() {
		return spraypaintvocpercent;
	}

	public void setSpraypaintvocpercent(String s) {
		spraypaintvocpercent = s;
	}

	public String getSpraysolvantvocpercent() {
		return spraysolvantvocpercent;
	}

	public void setSpraysolvantvocpercent(String s) {
		spraysolvantvocpercent = s;
	}

	public String getSprayinkvocpercent() {
		return sprayinkvocpercent;
	}

	public void setSprayinkvocpercent(String s) {
		sprayinkvocpercent = s;
	}

	public String getSprayothervocpercent() {
		return sprayothervocpercent;
	}

	public void setSprayothervocpercent(String s) {
		sprayothervocpercent = s;
	}

	public String getSpraypaintvolume() {
		return spraypaintvolume;
	}

	public void setSpraypaintvolume(String s) {
		spraypaintvolume = s;
	}

	public String getSpraysolvantvolume() {
		return spraysolvantvolume;
	}

	public void setSpraysolvantvolume(String s) {
		spraysolvantvolume = s;
	}

	public String getSprayinkvolume() {
		return sprayinkvolume;
	}

	public void setSprayinkvolume(String s) {
		sprayinkvolume = s;
	}

	public String getSprayothervolume() {
		return sprayothervolume;
	}

	public void setSprayothervolume(String s) {
		sprayothervolume = s;
	}

	public String getSpraypaintdensity() {
		return spraypaintdensity;
	}

	public void setSpraypaintdensity(String s) {
		spraypaintdensity = s;
	}

	public String getSpraysolvantdensity() {
		return spraysolvantdensity;
	}

	public void setSpraysolvantdensity(String s) {
		spraysolvantdensity = s;
	}

	public String getSprayinkdensity() {
		return sprayinkdensity;
	}

	public void setSprayinkdensity(String s) {
		sprayinkdensity = s;
	}

	public String getSprayotherdensity() {
		return sprayotherdensity;
	}

	public void setSprayotherdensity(String s) {
		sprayotherdensity = s;
	}

	public String getSpraypaintvoc() {
		return spraypaintvoc;
	}

	public void setSpraypaintvoc(String s) {
		spraypaintvoc = s;
	}

	public String getSpraysolvantvoc() {
		return spraysolvantvoc;
	}

	public void setSpraysolvantvoc(String s) {
		spraysolvantvoc = s;
	}

	public String getSprayinkvoc() {
		return sprayinkvoc;
	}

	public void setSprayinkvoc(String s) {
		sprayinkvoc = s;
	}

	public String getSprayothervoc() {
		return sprayothervoc;
	}

	public void setSprayothervoc(String s) {
		sprayothervoc = s;
	}

	public String getSpraypaintcap() {
		return spraypaintcap;
	}

	public void setSpraypaintcap(String s) {
		spraypaintcap = s;
	}

	public String getSpraysolvantcap() {
		return spraysolvantcap;
	}

	public void setSpraysolvantcap(String s) {
		spraysolvantcap = s;
	}

	public String getSprayinkcap() {
		return sprayinkcap;
	}

	public void setSprayinkcap(String s) {
		sprayinkcap = s;
	}

	public String getVocyear() {
		return vocyear;
	}

	public void setVocyear(String s) {
		vocyear = s;
	}

	public String getVocmonth() {
		return vocmonth;
	}

	public void setVocmonth(String s) {
		vocmonth = s;
	}

	public String getVoccap() {
		return voccap;
	}

	public void setVoccap(String s) {
		voccap = s;
	}

	public String getVocmonthlyquantity() {
		return vocmonthlyquantity;
	}

	public void setVocmonthlyquantity(String s) {
		vocmonthlyquantity = s;
	}

	public String getVocmonthlylimit() {
		return vocmonthlylimit;
	}

	public void setVocmonthlylimit(String s) {
		vocmonthlylimit = s;
	}

	public String getPsHoursOfOperationyear() {
		return psHoursOfOperationyear;
	}

	public void setPsHoursOfOperationyear(String s) {
		psHoursOfOperationyear = s;
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
				chemicalsUsedList = PaintSprayEntity.getChemicalList(getId());
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

	public String getActionTaken() {
		return ActionTaken;
	}

	public void setActionTaken(String s) {
		ActionTaken = s;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String s) {
		model = s;
	}

	public List getPermitInfoList() {
		if (permitInfoList == null)
			try {
				permitInfoList = PaintSprayEntity.getPermitInfo(getId());
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
		stringbuffer.append("ActionTaken=").append(ActionTaken).append(", ");
		stringbuffer.append("hrsOfOperation=").append(hrsOfOperation)
				.append(", ");
		stringbuffer.append("isExemptedByDec=").append(isExemptedByDec)
				.append(", ");
		stringbuffer.append("isUnitIncludedInDecPermit=")
				.append(isUnitIncludedInDecPermit).append(", ");
		stringbuffer.append("location=").append(location);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.PaintSprayVo.class);
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
	protected String ActionTaken;
	protected String hrsOfOperation;
	protected boolean isExemptedByDec;
	protected boolean isUnitIncludedInDecPermit;
	protected int location;
	protected List permitInfoList;
	protected List chemicalsUsedList;
	protected String psDEPNumber;
	protected String spraypaint;
	protected String spraysolvant;
	protected String sprayink;
	protected String sprayother;
	protected String spraypaintname;
	protected String spraysolvantname;
	protected String sprayinkname;
	protected String sprayothername;
	protected String spraypaintvocpercent;
	protected String spraysolvantvocpercent;
	protected String sprayinkvocpercent;
	protected String sprayothervocpercent;
	protected String spraypaintvolume;
	protected String spraysolvantvolume;
	protected String sprayinkvolume;
	protected String sprayothervolume;
	protected String spraypaintdensity;
	protected String spraysolvantdensity;
	protected String sprayinkdensity;
	protected String sprayotherdensity;
	protected String spraypaintvoc;
	protected String spraysolvantvoc;
	protected String sprayinkvoc;
	protected String sprayothervoc;
	protected String spraypaintcap;
	protected String spraysolvantcap;
	protected String sprayinkcap;
	protected String vocyear;
	protected String vocmonth;
	protected String voccap;
	protected String vocmonthlyquantity;
	protected String vocmonthlylimit;
	protected String psHoursOfOperationyear;
	protected String dobsignoff;
	protected String dobnumber;

}