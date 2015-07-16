package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.LandfillEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class LandfillVo implements Serializable {

	public LandfillVo() {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		location = -99;
		commencedDate = null;
		yearinstalled = null;
		status = -99;
		disconnecteddate = null;
		isListedAsTitleV = false;
		isLinkedToCogenOrTurbine = false;
		isAnnualStmtSubmited = false;
		permitInfoList = null;
		cogenOrTurbineList = null;
		lfDEPNumber = null;
		lfDOBNumber = null;
		lfDOBIssuedate = null;
		lfLFGQuantity = null;
		LFGQuantity = null;
		lfLFGCombustion = null;
		LFGCombustion = null;
		lfSCH4 = null;
		svalue = null;
		sallowablevalue = null;
		ch4value = null;
		ch4allowablevalue = null;
		istemperaturelimit = null;
		temperaturevalue = null;
		temperatureallowablevalue = null;
		noxvalue = null;
		noxallowablevalue = null;
		covalue = null;
		coallowablevalue = null;
		ovalue = null;
		oallowablevalue = null;
		yearlyoperating = null;
		yearlyvalue = null;
		yearlyallowablevalue = null;
		lcomments = null;
		aesdate = null;
		dobsignoff = null;
	}

	public LandfillVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilityDesignatedId = null;
		location = -99;
		commencedDate = null;
		yearinstalled = null;
		status = -99;
		disconnecteddate = null;
		isListedAsTitleV = false;
		isLinkedToCogenOrTurbine = false;
		isAnnualStmtSubmited = false;
		permitInfoList = null;
		cogenOrTurbineList = null;
		lfDEPNumber = null;
		lfDOBNumber = null;
		lfDOBIssuedate = null;
		lfLFGQuantity = null;
		LFGQuantity = null;
		lfLFGCombustion = null;
		LFGCombustion = null;
		lfSCH4 = null;
		svalue = null;
		sallowablevalue = null;
		ch4value = null;
		ch4allowablevalue = null;
		istemperaturelimit = null;
		temperaturevalue = null;
		temperatureallowablevalue = null;
		noxvalue = null;
		noxallowablevalue = null;
		covalue = null;
		coallowablevalue = null;
		ovalue = null;
		oallowablevalue = null;
		yearlyoperating = null;
		yearlyvalue = null;
		yearlyallowablevalue = null;
		lcomments = null;
		aesdate = null;
		dobsignoff = null;

		id = resultset.getInt("LANDFILLSID");
		buildingId = resultset.getInt("BUILDINGID");
		facilityDesignatedId = resultset.getString("FACILITYDESIGNATEDID");
		String s = resultset.getString("LOCATION");
		if (UtilityObject.isNotEmpty(s))
			location = resultset.getInt("LOCATION");
		commencedDate = resultset.getString("COMMENCEDDATE");
		yearinstalled = resultset.getString("YEARINSTALLED");
		status = resultset.getInt("STATUS");
		disconnecteddate = resultset.getString("DISCONNECTEDDATE");
		s = resultset.getString("ISLISTEDASTITLEV");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isListedAsTitleV = true;
		s = resultset.getString("ISLINKEDTOCOGEN");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isLinkedToCogenOrTurbine = true;
		s = resultset.getString("ISANNUALSTATEMENTSUBMITTED");
		if (s != null && s.trim().equalsIgnoreCase("Y"))
			isAnnualStmtSubmited = true;

		lfDEPNumber = resultset.getString("LFDEPNUMBER");
		lfDOBNumber = resultset.getString("LFDOBNUMBER");
		lfDOBIssuedate = resultset.getString("LFDOBISSUEDATE");
		lfLFGQuantity = resultset.getString("LFLFGQUANTITY");
		LFGQuantity = resultset.getString("LFGQUANTITY");
		lfLFGCombustion = resultset.getString("LFLFGCOMBUSTION");
		LFGCombustion = resultset.getString("LFGCOMBUSTION");
		lfSCH4 = resultset.getString("LFSCH4");
		svalue = resultset.getString("SVALUE");
		sallowablevalue = resultset.getString("SALLOWABLEVALUE");
		ch4value = resultset.getString("CH4VALUE");
		ch4allowablevalue = resultset.getString("CH4ALLOWABLEVALUE");
		istemperaturelimit = resultset.getString("ISTEMPERATURELIMIT");
		temperaturevalue = resultset.getString("TEMPERATUREVALUE");
		temperatureallowablevalue = resultset
				.getString("TEMPERATUREALLOWABLEVALUE");
		noxvalue = resultset.getString("NOXVALUE");
		noxallowablevalue = resultset.getString("NOXALLOWABLEVALUE");
		covalue = resultset.getString("COVALUE");
		coallowablevalue = resultset.getString("COALLOWABLEVALUE");
		ovalue = resultset.getString("OVALUE");
		oallowablevalue = resultset.getString("OALLOWABLEVALUE");
		yearlyoperating = resultset.getString("YEARLYOPERATING");
		yearlyvalue = resultset.getString("YEARLYVALUE");
		yearlyallowablevalue = resultset.getString("YEARLYALLOWABLEVALUE");
		lcomments = resultset.getString("LCOMMENTS");
		aesdate = resultset.getString("AESDATE");
		dobsignoff = resultset.getString("DOBSIGNOFF");

	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getLfDEPNumber() {
		return lfDEPNumber;
	}

	public void setLfDEPNumber(String s) {
		lfDEPNumber = s;
	}

	public String getLfDOBNumber() {
		return lfDOBNumber;
	}

	public void setLfDOBNumber(String s) {
		lfDOBNumber = s;
	}

	public String getLfDOBIssuedate() {
		return lfDOBIssuedate;
	}

	public void setLfDOBIssuedate(String s) {
		lfDOBIssuedate = s;
	}

	public String getLfLFGQuantity() {
		return lfLFGQuantity;
	}

	public void setLfLFGQuantity(String s) {
		lfLFGQuantity = s;
	}

	public String getLFGQuantity() {
		return LFGQuantity;
	}

	public void setLFGQuantity(String s) {
		LFGQuantity = s;
	}

	public String getLfLFGCombustion() {
		return lfLFGCombustion;
	}

	public void setLfLFGCombustion(String s) {
		lfLFGCombustion = s;
	}

	public String getLFGCombustion() {
		return LFGCombustion;
	}

	public void setLFGCombustion(String s) {
		LFGCombustion = s;
	}

	public String getLfSCH4() {
		return lfSCH4;
	}

	public void setLfSCH4(String s) {
		lfSCH4 = s;
	}

	public String getSvalue() {
		return svalue;
	}

	public void setSvalue(String s) {
		svalue = s;
	}

	public String getSallowablevalue() {
		return sallowablevalue;
	}

	public void setSallowablevalue(String s) {
		sallowablevalue = s;
	}

	public String getCh4value() {
		return ch4value;
	}

	public void setCh4value(String s) {
		ch4value = s;
	}

	public String getCh4allowablevalue() {
		return ch4allowablevalue;
	}

	public void setCh4allowablevalue(String s) {
		ch4allowablevalue = s;
	}

	public String getIstemperaturelimit() {
		return istemperaturelimit;
	}

	public void setIstemperaturelimit(String s) {
		istemperaturelimit = s;
	}

	public String getTemperaturevalue() {
		return temperaturevalue;
	}

	public void setTemperaturevalue(String s) {
		temperaturevalue = s;
	}

	public String getTemperatureallowablevalue() {
		return temperatureallowablevalue;
	}

	public void setTemperatureallowablevalue(String s) {
		temperatureallowablevalue = s;
	}

	public String getNoxvalue() {
		return noxvalue;
	}

	public void setNoxvalue(String s) {
		noxvalue = s;
	}

	public String getNoxallowablevalue() {
		return noxallowablevalue;
	}

	public void setNoxallowablevalue(String s) {
		noxallowablevalue = s;
	}

	public String getCovalue() {
		return covalue;
	}

	public void setCovalue(String s) {
		covalue = s;
	}

	public String getCoallowablevalue() {
		return coallowablevalue;
	}

	public void setCoallowablevalue(String s) {
		coallowablevalue = s;
	}

	public String getOvalue() {
		return ovalue;
	}

	public void setOvalue(String s) {
		ovalue = s;
	}

	public String getOallowablevalue() {
		return oallowablevalue;
	}

	public void setOallowablevalue(String s) {
		oallowablevalue = s;
	}

	public String getYearlyoperating() {
		return yearlyoperating;
	}

	public void setYearlyoperating(String s) {
		yearlyoperating = s;
	}

	public String getYearlyvalue() {
		return yearlyvalue;
	}

	public void setYearlyvalue(String s) {
		yearlyvalue = s;
	}

	public String getYearlyallowablevalue() {
		return yearlyallowablevalue;
	}

	public void setYearlyallowablevalue(String s) {
		yearlyallowablevalue = s;
	}

	public String getLcomments() {
		return lcomments;
	}

	public void setLcomments(String s) {
		lcomments = s;
	}

	public String getAesdate() {
		return aesdate;
	}

	public void setAesdate(String s) {
		aesdate = s;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public String getCommencedDate() {
		return commencedDate;
	}

	public String getCommencedDateStr() {
		if (commencedDate == null)
			return "";
		else
			return UtilityObject.convertToString(UtilityObject
					.convertToDate(commencedDate));
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

	public void setCommencedDate(String s) {
		commencedDate = s;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isAnnualStmtSubmited() {
		return isAnnualStmtSubmited;
	}

	public void setAnnualStmtSubmited(boolean flag) {
		isAnnualStmtSubmited = flag;
	}

	public boolean isLinkedToCogenOrTurbine() {
		return isLinkedToCogenOrTurbine;
	}

	public void setLinkedToCogenOrTurbine(boolean flag) {
		isLinkedToCogenOrTurbine = flag;
	}

	public boolean isListedAsTitleV() {
		return isListedAsTitleV;
	}

	public void setListedAsTitleV(boolean flag) {
		isListedAsTitleV = flag;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int i) {
		location = i;
	}

	public List getPermitInfoList() {
		if (permitInfoList == null)
			try {
				permitInfoList = LandfillEntity.getPermitList(getId());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		return permitInfoList;
	}

	public void setPermitInfoList(List list) {
		permitInfoList = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id);
		stringbuffer.append(",buildingId =").append(buildingId);
		stringbuffer.append(",facilityDesignatedId =").append(
				facilityDesignatedId);
		stringbuffer.append(",location =").append(location);
		stringbuffer.append(",commencedDate =").append(commencedDate);
		stringbuffer.append(",yearinstalled=").append(yearinstalled);
		stringbuffer.append(",status=").append(status);
		stringbuffer.append(",disconnecteddate=").append(disconnecteddate);
		stringbuffer.append(",isListedAsTitleV =").append(isListedAsTitleV);
		stringbuffer.append(",isLinkedToCogenOrTurbine =").append(
				isLinkedToCogenOrTurbine);
		stringbuffer.append(",isAnnualStmtSubmited =").append(
				isAnnualStmtSubmited);
		return stringbuffer.toString();
	}

	public List getCogenOrTurbineList() {
		if (cogenOrTurbineList == null)
			try {
				cogenOrTurbineList = LandfillEntity.getCogenOrTurbine(getId());
			} catch (TrackingException trackingexception) {
				if (log.isErrorEnabled())
					log.error(trackingexception);
			}
		return cogenOrTurbineList;
	}

	public void setCogenOrTurbineList(List list) {
		cogenOrTurbineList = list;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.LandfillVo.class);
	protected int id;
	protected int buildingId;
	protected String facilityDesignatedId;
	protected int location;
	protected String commencedDate;
	protected String yearinstalled;
	protected int status;
	protected String disconnecteddate;
	protected boolean isListedAsTitleV;
	protected boolean isLinkedToCogenOrTurbine;
	protected boolean isAnnualStmtSubmited;
	protected List permitInfoList;
	protected List cogenOrTurbineList;
	protected String lfDEPNumber;
	protected String lfDOBNumber;
	protected String lfDOBIssuedate;
	protected String lfLFGQuantity;
	protected String LFGQuantity;
	protected String lfLFGCombustion;
	protected String LFGCombustion;
	protected String lfSCH4;
	protected String svalue;
	protected String sallowablevalue;
	protected String ch4value;
	protected String ch4allowablevalue;
	protected String istemperaturelimit;
	protected String temperaturevalue;
	protected String temperatureallowablevalue;
	protected String noxvalue;
	protected String noxallowablevalue;
	protected String covalue;
	protected String coallowablevalue;
	protected String ovalue;
	protected String oallowablevalue;
	protected String yearlyoperating;
	protected String yearlyvalue;
	protected String yearlyallowablevalue;
	protected String lcomments;
	protected String aesdate;
	protected String dobsignoff;

}