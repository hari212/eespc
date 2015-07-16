package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.PrintingPressEntity;
import com.eespc.tracking.util.UtilityObject;

public class PrintingPressVo implements Serializable {

	public PrintingPressVo() {
		id = -99;
		buildingId = -99;

		facilitydesinatedId = null;
		ModifiedInPast = -99;
		DisconnectedYear = null;
		Pcomments = null;
		decpermit = null;
		stacktestrequired = null;
		isvoc = null;
		ispm = null;
		isother = null;
		depnumber = null;
		depissuedate = null;
		depexpirationdate = null;
		stackexhastlocation = null;
		height = null;
		diameter = null;
		velocity = null;
		filterused = null;
		effiviancy = null;
		solvantcap = null;
		annualconsumption = null;
		Ink1 = null;
		Ink2 = null;
		Ink3 = null;
		Ink4 = null;
		FountainSolution = null;
		CleaningAgent = null;
		Ink1name = null;
		Ink2name = null;
		Ink3name = null;
		Ink4name = null;
		FountainSolutionname = null;
		CleaningAgentname = null;
		Ink1volume = null;
		Ink2volume = null;
		Ink3volume = null;
		Ink4volume = null;
		FountainSolutionvolume = null;
		CleaningAgentvolume = null;
		Ink1density = null;
		Ink2density = null;
		Ink3density = null;
		Ink4density = null;
		FountainSolutiondensity = null;
		CleaningAgentdensity = null;
		Ink1vocpercent = null;
		Ink2vocpercent = null;
		Ink3vocpercent = null;
		Ink4vocpercent = null;
		FountainSolutionvocpercent = null;
		CleaningAgentvocpercent = null;
		Ink1voc = null;
		Ink2voc = null;
		Ink3voc = null;
		Ink4voc = null;
		FountainSolutionvoc = null;
		CleaningAgentvoc = null;
		dobpermit = null;
		totalvoc = null;
		dobsignoff = null;
		inkConsumptionList = null;
		to_inkConsumptionList = null;
		th_inkConsumptionList = null;
		fr_inkConsumptionList = null;
		fi_inkConsumptionList = null;
		si_inkConsumptionList = null;
		se_inkConsumptionList = null;
	}

	public PrintingPressVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;

		facilitydesinatedId = null;
		ModifiedInPast = -99;
		DisconnectedYear = null;
		Pcomments = null;
		decpermit = null;
		stacktestrequired = null;
		isvoc = null;
		ispm = null;
		isother = null;
		depnumber = null;
		depissuedate = null;
		depexpirationdate = null;
		stackexhastlocation = null;
		height = null;
		diameter = null;
		velocity = null;
		filterused = null;
		effiviancy = null;
		solvantcap = null;
		annualconsumption = null;
		Ink1 = null;
		Ink2 = null;
		Ink3 = null;
		Ink4 = null;
		FountainSolution = null;
		CleaningAgent = null;
		Ink1name = null;
		Ink2name = null;
		Ink3name = null;
		Ink4name = null;
		FountainSolutionname = null;
		CleaningAgentname = null;
		Ink1volume = null;
		Ink2volume = null;
		Ink3volume = null;
		Ink4volume = null;
		FountainSolutionvolume = null;
		CleaningAgentvolume = null;
		Ink1density = null;
		Ink2density = null;
		Ink3density = null;
		Ink4density = null;
		FountainSolutiondensity = null;
		CleaningAgentdensity = null;
		Ink1vocpercent = null;
		Ink2vocpercent = null;
		Ink3vocpercent = null;
		Ink4vocpercent = null;
		FountainSolutionvocpercent = null;
		CleaningAgentvocpercent = null;
		Ink1voc = null;
		Ink2voc = null;
		Ink3voc = null;
		Ink4voc = null;
		FountainSolutionvoc = null;
		CleaningAgentvoc = null;
		dobpermit = null;
		totalvoc = null;
		dobsignoff = null;
		inkConsumptionList = null;
		to_inkConsumptionList = null;
		th_inkConsumptionList = null;
		fr_inkConsumptionList = null;
		fi_inkConsumptionList = null;
		si_inkConsumptionList = null;
		se_inkConsumptionList = null;

		id = UtilityObject.getIntFromString(resultset.getString("PRESSID"));
		buildingId = UtilityObject.getIntFromString(resultset
				.getString("BUILDINGID"));
		facilitydesinatedId = resultset.getString("FACILITYDESIGNATEDID");
		ModifiedInPast = UtilityObject.getIntFromString(resultset
				.getString("MODIFIEDINPAST"));
		DisconnectedYear = resultset.getString("DISCONNECTEDYEAR");
		Pcomments = resultset.getString("PCOMMENTS");
		decpermit = resultset.getString("DECPERMIT");
		stacktestrequired = resultset.getString("STACKTESTREQUIRED");
		isvoc = resultset.getString("ISVOC");
		ispm = resultset.getString("ISPM");
		isother = resultset.getString("ISOTHER");
		depnumber = resultset.getString("DEPNUMBER");
		depissuedate = resultset.getString("DEPISSUEDATE");
		depexpirationdate = resultset.getString("DEPEXPIRATIONDATE");
		stackexhastlocation = resultset.getString("STACKEXHASTLOCATION");
		height = resultset.getString("HEIGHT");
		diameter = resultset.getString("DIAMETER");
		velocity = resultset.getString("VELOCITY");
		filterused = resultset.getString("FILTERUSED");
		effiviancy = resultset.getString("EFFIVIANCY");
		solvantcap = resultset.getString("SOLVANTCAP");
		annualconsumption = resultset.getString("ANNUALCONSUMPTION");
		Ink1 = resultset.getString("INK1");
		Ink2 = resultset.getString("INK2");
		Ink3 = resultset.getString("INK3");
		Ink4 = resultset.getString("INK4");
		FountainSolution = resultset.getString("FOUNTAINSOLUTION");
		CleaningAgent = resultset.getString("CLEANINGAGENT");
		Ink1name = resultset.getString("INK1NAME");
		Ink2name = resultset.getString("INK2NAME");
		Ink3name = resultset.getString("INK3NAME");
		Ink4name = resultset.getString("INK4NAME");
		FountainSolutionname = resultset.getString("FOUNTAINSOLUTIONNAME");
		CleaningAgentname = resultset.getString("CLEANINGAGENTNAME");
		Ink1volume = resultset.getString("INK1VOLUME");
		Ink2volume = resultset.getString("INK2VOLUME");
		Ink3volume = resultset.getString("INK3VOLUME");
		Ink4volume = resultset.getString("INK4VOLUME");
		FountainSolutionvolume = resultset.getString("FOUNTAINSOLUTIONVOLUME");
		CleaningAgentvolume = resultset.getString("CLEANINGAGENTVOLUME");
		Ink1density = resultset.getString("INK1DENSITY");
		Ink2density = resultset.getString("INK2DENSITY");
		Ink3density = resultset.getString("INK3DENSITY");
		Ink4density = resultset.getString("INK4DENSITY");
		FountainSolutiondensity = resultset
				.getString("FOUNTAINSOLUTIONDENSITY");
		CleaningAgentdensity = resultset.getString("CLEANINGAGENTDENSITY");
		Ink1vocpercent = resultset.getString("INK1VOCPERCENT");
		Ink2vocpercent = resultset.getString("INK2VOCPERCENT");
		Ink3vocpercent = resultset.getString("INK3VOCPERCENT");
		Ink4vocpercent = resultset.getString("INK4VOCPERCENT");
		FountainSolutionvocpercent = resultset
				.getString("FOUNTAINSOLUTIONVOCPERCENT");
		CleaningAgentvocpercent = resultset
				.getString("CLEANINGAGENTVOCPERCENT");
		Ink1voc = resultset.getString("INK1VOC");
		Ink2voc = resultset.getString("INK2VOC");
		Ink3voc = resultset.getString("INK3VOC");
		Ink4voc = resultset.getString("INK4VOC");
		FountainSolutionvoc = resultset.getString("FOUNTAINSOLUTIONVOC");
		CleaningAgentvoc = resultset.getString("CLEANINGAGENTVOC");
		dobpermit = resultset.getString("DOBPERMIT");
		totalvoc = resultset.getString("TOTALVOC");
		dobsignoff = resultset.getString("DOBSIGNOFF");
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getFacilitydesinatedId() {
		return facilitydesinatedId;
	}

	public void setFacilitydesinatedId(String s) {
		facilitydesinatedId = s;
	}

	public int getModifiedInPast() {
		return ModifiedInPast;
	}

	public void setModifiedInPast(int s) {
		ModifiedInPast = s;
	}

	public String getDisconnectedYear() {
		return DisconnectedYear;
	}

	public void setDisconnectedYear(String s) {
		DisconnectedYear = s;
	}

	public String getPcomments() {
		return Pcomments;
	}

	public void setPcomments(String s) {
		Pcomments = s;
	}

	public String getDecpermit() {
		return decpermit;
	}

	public void setDecpermit(String s) {
		decpermit = s;
	}

	public String getStacktestrequired() {
		return stacktestrequired;
	}

	public void setStacktestrequired(String s) {
		stacktestrequired = s;
	}

	public String getIsvoc() {
		return isvoc;
	}

	public void setIsvoc(String s) {
		isvoc = s;
	}

	public String getIspm() {
		return ispm;
	}

	public void setIspm(String s) {
		ispm = s;
	}

	public String getIsother() {
		return isother;
	}

	public void setIsother(String s) {
		isother = s;
	}

	public String getDepnumber() {
		return depnumber;
	}

	public void setDepnumber(String s) {
		depnumber = s;
	}

	public String getDepissuedate() {
		return depissuedate;
	}

	public void setDepissuedate(String s) {
		depissuedate = s;
	}

	public String getDepexpirationdate() {
		return depexpirationdate;
	}

	public void setDepexpirationdate(String s) {
		depexpirationdate = s;
	}

	public String getStackexhastlocation() {
		return stackexhastlocation;
	}

	public void setStackexhastlocation(String s) {
		stackexhastlocation = s;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String s) {
		height = s;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String s) {
		diameter = s;
	}

	public String getVelocity() {
		return velocity;
	}

	public void setVelocity(String s) {
		velocity = s;
	}

	public String getFilterused() {
		return filterused;
	}

	public void setFilterused(String s) {
		filterused = s;
	}

	public String getEffiviancy() {
		return effiviancy;
	}

	public void setEffiviancy(String s) {
		effiviancy = s;
	}

	public String getSolvantcap() {
		return solvantcap;
	}

	public void setSolvantcap(String s) {
		solvantcap = s;
	}

	public String getAnnualconsumption() {
		return annualconsumption;
	}

	public void setAnnualconsumption(String s) {
		annualconsumption = s;
	}

	public String getInk1() {
		return Ink1;
	}

	public void setInk1(String s) {
		Ink1 = s;
	}

	public String getInk2() {
		return Ink2;
	}

	public void setInk2(String s) {
		Ink2 = s;
	}

	public String getInk3() {
		return Ink3;
	}

	public void setInk3(String s) {
		Ink3 = s;
	}

	public String getInk4() {
		return Ink4;
	}

	public void setInk4(String s) {
		Ink4 = s;
	}

	public String getFountainSolution() {
		return FountainSolution;
	}

	public void setFountainSolution(String s) {
		FountainSolution = s;
	}

	public String getCleaningAgent() {
		return CleaningAgent;
	}

	public void setCleaningAgent(String s) {
		CleaningAgent = s;
	}

	public String getInk1name() {
		return Ink1name;
	}

	public void setInk1name(String s) {
		Ink1name = s;
	}

	public String getInk2name() {
		return Ink2name;
	}

	public void setInk2name(String s) {
		Ink2name = s;
	}

	public String getInk3name() {
		return Ink3name;
	}

	public void setInk3name(String s) {
		Ink3name = s;
	}

	public String getInk4name() {
		return Ink4name;
	}

	public void setInk4name(String s) {
		Ink4name = s;
	}

	public String getFountainSolutionname() {
		return FountainSolutionname;
	}

	public void setFountainSolutionname(String s) {
		FountainSolutionname = s;
	}

	public String getCleaningAgentname() {
		return CleaningAgentname;
	}

	public void setCleaningAgentname(String s) {
		CleaningAgentname = s;
	}

	public String getInk1volume() {
		return Ink1volume;
	}

	public void setInk1volume(String s) {
		Ink1volume = s;
	}

	public String getInk2volume() {
		return Ink2volume;
	}

	public void setInk2volume(String s) {
		Ink2volume = s;
	}

	public String getInk3volume() {
		return Ink3volume;
	}

	public void setInk3volume(String s) {
		Ink3volume = s;
	}

	public String getInk4volume() {
		return Ink4volume;
	}

	public void setInk4volume(String s) {
		Ink4volume = s;
	}

	public String getFountainSolutionvolume() {
		return FountainSolutionvolume;
	}

	public void setFountainSolutionvolume(String s) {
		FountainSolutionvolume = s;
	}

	public String getCleaningAgentvolume() {
		return CleaningAgentvolume;
	}

	public void setCleaningAgentvolume(String s) {
		CleaningAgentvolume = s;
	}

	public String getInk1density() {
		return Ink1density;
	}

	public void setInk1density(String s) {
		Ink1density = s;
	}

	public String getInk2density() {
		return Ink2density;
	}

	public void setInk2density(String s) {
		Ink2density = s;
	}

	public String getInk3density() {
		return Ink3density;
	}

	public void setInk3density(String s) {
		Ink3density = s;
	}

	public String getInk4density() {
		return Ink4density;
	}

	public void setInk4density(String s) {
		Ink4density = s;
	}

	public String getFountainSolutiondensity() {
		return FountainSolutiondensity;
	}

	public void setFountainSolutiondensity(String s) {
		FountainSolutiondensity = s;
	}

	public String getCleaningAgentdensity() {
		return CleaningAgentdensity;
	}

	public void setCleaningAgentdensity(String s) {
		CleaningAgentdensity = s;
	}

	public String getInk1vocpercent() {
		return Ink1vocpercent;
	}

	public void setInk1vocpercent(String s) {
		Ink1vocpercent = s;
	}

	public String getInk2vocpercent() {
		return Ink2vocpercent;
	}

	public void setInk2vocpercent(String s) {
		Ink2vocpercent = s;
	}

	public String getInk3vocpercent() {
		return Ink3vocpercent;
	}

	public void setInk3vocpercent(String s) {
		Ink3vocpercent = s;
	}

	public String getInk4vocpercent() {
		return Ink4vocpercent;
	}

	public void setInk4vocpercent(String s) {
		Ink4vocpercent = s;
	}

	public String getFountainSolutionvocpercent() {
		return FountainSolutionvocpercent;
	}

	public void setFountainSolutionvocpercent(String s) {
		FountainSolutionvocpercent = s;
	}

	public String getCleaningAgentvocpercent() {
		return CleaningAgentvocpercent;
	}

	public void setCleaningAgentvocpercent(String s) {
		CleaningAgentvocpercent = s;
	}

	public String getInk1voc() {
		return Ink1voc;
	}

	public void setInk1voc(String s) {
		Ink1voc = s;
	}

	public String getInk2voc() {
		return Ink2voc;
	}

	public void setInk2voc(String s) {
		Ink2voc = s;
	}

	public String getInk3voc() {
		return Ink3voc;
	}

	public void setInk3voc(String s) {
		Ink3voc = s;
	}

	public String getInk4voc() {
		return Ink4voc;
	}

	public void setInk4voc(String s) {
		Ink4voc = s;
	}

	public String getFountainSolutionvoc() {
		return FountainSolutionvoc;
	}

	public void setFountainSolutionvoc(String s) {
		FountainSolutionvoc = s;
	}

	public String getCleaningAgentvoc() {
		return CleaningAgentvoc;
	}

	public void setCleaningAgentvoc(String s) {
		CleaningAgentvoc = s;
	}

	public String getDobpermit() {
		return dobpermit;
	}

	public void setDobpermit(String s) {
		dobpermit = s;
	}

	public String getTotalvoc() {
		return totalvoc;
	}

	public void setTotalvoc(String s) {
		totalvoc = s;
	}

	public List getInkConsumptionList() {
		if (inkConsumptionList == null)
			try {
				inkConsumptionList = PrintingPressEntity
						.getInkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return inkConsumptionList;
	}

	public List getto_InkConsumptionList() {

		if (to_inkConsumptionList == null)
			try {
				to_inkConsumptionList = PrintingPressEntity
						.getto_InkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return to_inkConsumptionList;
	}

	public List getth_InkConsumptionList() {

		if (th_inkConsumptionList == null)
			try {
				th_inkConsumptionList = PrintingPressEntity
						.getth_InkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return th_inkConsumptionList;
	}

	public List getfr_InkConsumptionList() {

		if (fr_inkConsumptionList == null)
			try {
				fr_inkConsumptionList = PrintingPressEntity
						.getfr_InkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return fr_inkConsumptionList;
	}

	public List getfi_InkConsumptionList() {

		if (fi_inkConsumptionList == null)
			try {
				fi_inkConsumptionList = PrintingPressEntity
						.getfi_InkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return fi_inkConsumptionList;
	}

	public List getsi_InkConsumptionList() {

		if (si_inkConsumptionList == null)
			try {
				si_inkConsumptionList = PrintingPressEntity
						.getsi_InkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return si_inkConsumptionList;
	}

	public List getse_InkConsumptionList() {

		if (se_inkConsumptionList == null)
			try {
				se_inkConsumptionList = PrintingPressEntity
						.getse_InkConsumptionList(getId());
			} catch (Exception exception) {
				log.error(exception);
			}
		return se_inkConsumptionList;
	}

	public void setInkConsumptionList(List list) {
		inkConsumptionList = list;
	}

	public void setto_InkConsumptionList(List list) {
		to_inkConsumptionList = list;
	}

	public void setth_InkConsumptionList(List list) {
		th_inkConsumptionList = list;
	}

	public void setfr_InkConsumptionList(List list) {
		fr_inkConsumptionList = list;
	}

	public void setfi_InkConsumptionList(List list) {
		fi_inkConsumptionList = list;
	}

	public void setsi_InkConsumptionList(List list) {
		si_inkConsumptionList = list;
	}

	public void setse_InkConsumptionList(List list) {
		se_inkConsumptionList = list;
	}

	protected int id;
	protected int buildingId;
	protected String facilitydesinatedId;
	protected int ModifiedInPast;
	protected String DisconnectedYear;
	protected String Pcomments;
	protected String decpermit;
	protected String stacktestrequired;
	protected String isvoc;
	protected String ispm;
	protected String isother;
	protected String depnumber;
	protected String depissuedate;
	protected String depexpirationdate;
	protected String stackexhastlocation;
	protected String height;
	protected String diameter;
	protected String velocity;
	protected String filterused;
	protected String effiviancy;
	protected String solvantcap;
	protected String annualconsumption;
	protected String Ink1;
	protected String Ink2;
	protected String Ink3;
	protected String Ink4;
	protected String FountainSolution;
	protected String CleaningAgent;
	protected String Ink1name;
	protected String Ink2name;
	protected String Ink3name;
	protected String Ink4name;
	protected String FountainSolutionname;
	protected String CleaningAgentname;
	protected String Ink1volume;
	protected String Ink2volume;
	protected String Ink3volume;
	protected String Ink4volume;
	protected String FountainSolutionvolume;
	protected String CleaningAgentvolume;
	protected String Ink1density;
	protected String Ink2density;
	protected String Ink3density;
	protected String Ink4density;
	protected String FountainSolutiondensity;
	protected String CleaningAgentdensity;
	protected String Ink1vocpercent;
	protected String Ink2vocpercent;
	protected String Ink3vocpercent;
	protected String Ink4vocpercent;
	protected String FountainSolutionvocpercent;
	protected String CleaningAgentvocpercent;
	protected String Ink1voc;
	protected String Ink2voc;
	protected String Ink3voc;
	protected String Ink4voc;
	protected String FountainSolutionvoc;
	protected String CleaningAgentvoc;
	protected String dobpermit;
	protected String totalvoc;
	protected String dobsignoff;
	protected List inkConsumptionList;
	protected List to_inkConsumptionList;
	protected List th_inkConsumptionList;
	protected List fr_inkConsumptionList;
	protected List fi_inkConsumptionList;
	protected List si_inkConsumptionList;
	protected List se_inkConsumptionList;
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.PrintingPressVo.class);

}