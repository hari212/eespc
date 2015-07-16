package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.util.UtilityObject;

public class OtherSourceVo implements Serializable {

	public OtherSourceVo() {
		id = -99;
		buildingId = -99;
		facilitydesinatedId = null;
		ModifiedInPast = -99;
		DisconnectedYear = null;
		Ocomments = null;
		misname = null;
		mismake = null;
		mismodel = null;
		miscapacity = null;
		miscapacityunit = null;
		mispollutant1 = null;
		mispollutant2 = null;
		mispollutant3 = null;
		mispollutant1max = null;
		mispollutant2max = null;
		mispollutant3max = null;
		misdec = null;
		misdep = null;
		misdob = null;
		misdoh = null;
		misfd = null;
		misother = null;
		misissuedate = null;
		misexpirationdate = null;
		misexpirationdateNote = null;
		mismonthly = null;
		misquartly = null;
		missemiannualy = null;
		misannualy = null;
		misanniversary = null;
		mistestingrequired = null;
		complianceissue = null;
		mistestsubmitted = null;
		dobsignoff = null;

	}

	public OtherSourceVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		facilitydesinatedId = null;
		ModifiedInPast = -99;
		DisconnectedYear = null;
		Ocomments = null;
		misname = null;
		mismake = null;
		mismodel = null;
		miscapacity = null;
		miscapacityunit = null;
		mispollutant1 = null;
		mispollutant2 = null;
		mispollutant3 = null;
		mispollutant1max = null;
		mispollutant2max = null;
		mispollutant3max = null;
		misdec = null;
		misdep = null;
		misdob = null;
		misdoh = null;
		misfd = null;
		misother = null;
		misissuedate = null;
		misexpirationdate = null;
		misexpirationdateNote = null;
		mismonthly = null;
		misquartly = null;
		missemiannualy = null;
		misannualy = null;
		misanniversary = null;
		mistestingrequired = null;
		complianceissue = null;
		mistestsubmitted = null;
		dobsignoff = null;

		id = UtilityObject.getIntFromString(resultset.getString("OTHERSID"));
		buildingId = UtilityObject.getIntFromString(resultset
				.getString("BUILDINGID"));
		facilitydesinatedId = resultset.getString("FACILITYDESIGNATEDID");
		ModifiedInPast = resultset.getInt("MODIFIEDINPAST");
		DisconnectedYear = resultset.getString("DISCONNECTEDYEAR");
		Ocomments = resultset.getString("OCOMMENTS");
		misname = resultset.getString("MISNAME");
		mismake = resultset.getString("MISMAKE");
		mismodel = resultset.getString("MISMODEL");
		miscapacity = resultset.getString("MISCAPACITY");
		miscapacityunit = resultset.getString("MISCAPACITYUNIT");
		mispollutant1 = resultset.getString("MISPOLLUTANT1");
		mispollutant2 = resultset.getString("MISPOLLUTANT2");
		mispollutant3 = resultset.getString("MISPOLLUTANT3");
		mispollutant1max = resultset.getString("MISPOLLUTANT1MAX");
		mispollutant2max = resultset.getString("MISPOLLUTANT2MAX");
		mispollutant3max = resultset.getString("MISPOLLUTANT3MAX");
		misdec = resultset.getString("MISDEC");
		misdep = resultset.getString("MISDEP");
		misdob = resultset.getString("MISDOB");
		misdoh = resultset.getString("MISDOH");
		misfd = resultset.getString("MISFD");
		misother = resultset.getString("MISOTHER");
		misissuedate = resultset.getString("MISISSUEDATE");
		misexpirationdate = resultset.getString("MISEXPIRATIONDATE");
		misexpirationdateNote = resultset.getString("MISEXPIRATIONDATENOTE");
		mismonthly = resultset.getString("MISMONTHLY");
		misquartly = resultset.getString("MISQUARTLY");
		missemiannualy = resultset.getString("MISSEMIANNUALY");
		misannualy = resultset.getString("MISANNUALY");
		misanniversary = resultset.getString("MISANNIVERSARY");
		mistestingrequired = resultset.getString("MISTESTINGREQUIRED");
		complianceissue = resultset.getString("COMPLIANCEISSUE");
		mistestsubmitted = resultset.getString("MISTESTSUBMITTED");
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

	public String getMisexpirationdateNote() {
		return misexpirationdateNote;
	}

	public void setMisexpirationdateNote(String s) {
		misexpirationdateNote = s;
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

	public String getOcomments() {
		return Ocomments;
	}

	public void setOcomments(String s) {
		Ocomments = s;
	}

	public String getMisname() {
		return misname;
	}

	public void setMisname(String s) {
		misname = s;
	}

	public String getMismake() {
		return mismake;
	}

	public void setMismake(String s) {
		mismake = s;
	}

	public String getMismodel() {
		return mismodel;
	}

	public void setMismodel(String s) {
		mismodel = s;
	}

	public String getMiscapacity() {
		return miscapacity;
	}

	public void setMiscapacity(String s) {
		miscapacity = s;
	}

	public String getMiscapacityunit() {
		return miscapacityunit;
	}

	public void setMiscapacityunit(String s) {
		miscapacityunit = s;
	}

	public String getMispollutant1() {
		return mispollutant1;
	}

	public void setMispollutant1(String s) {
		mispollutant1 = s;
	}

	public String getMispollutant2() {
		return mispollutant2;
	}

	public void setMispollutant2(String s) {
		mispollutant2 = s;
	}

	public String getMispollutant3() {
		return mispollutant3;
	}

	public void setMispollutant3(String s) {
		mispollutant3 = s;
	}

	public String getMispollutant1max() {
		return mispollutant1max;
	}

	public void setMispollutant1max(String s) {
		mispollutant1max = s;
	}

	public String getMispollutant2max() {
		return mispollutant2max;
	}

	public void setMispollutant2max(String s) {
		mispollutant2max = s;
	}

	public String getMispollutant3max() {
		return mispollutant3max;
	}

	public void setMispollutant3max(String s) {
		mispollutant3max = s;
	}

	public String getMisdec() {
		return misdec;
	}

	public void setMisdec(String s) {
		misdec = s;
	}

	public String getMisdep() {
		return misdep;
	}

	public void setMisdep(String s) {
		misdep = s;
	}

	public String getMisdob() {
		return misdob;
	}

	public void setMisdob(String s) {
		misdob = s;
	}

	public String getMisdoh() {
		return misdoh;
	}

	public void setMisdoh(String s) {
		misdoh = s;
	}

	public String getMisfd() {
		return misfd;
	}

	public void setMisfd(String s) {
		misfd = s;
	}

	public String getMisother() {
		return misother;
	}

	public void setMisother(String s) {
		misother = s;
	}

	public String getMisissuedate() {
		return misissuedate;
	}

	public void setMisissuedate(String s) {
		misissuedate = s;
	}

	public String getMisexpirationdate() {
		return misexpirationdate;
	}

	public void setMisexpirationdate(String s) {
		misexpirationdate = s;
	}

	public String getMismonthly() {
		return mismonthly;
	}

	public void setMismonthly(String s) {
		mismonthly = s;
	}

	public String getMisquartly() {
		return misquartly;
	}

	public void setMisquartly(String s) {
		misquartly = s;
	}

	public String getMissemiannualy() {
		return missemiannualy;
	}

	public void setMissemiannualy(String s) {
		missemiannualy = s;
	}

	public String getMisannualy() {
		return misannualy;
	}

	public void setMisannualy(String s) {
		misannualy = s;
	}

	public String getMisanniversary() {
		return misanniversary;
	}

	public void setMisanniversary(String s) {
		misanniversary = s;
	}

	public String getMistestingrequired() {
		return mistestingrequired;
	}

	public void setMistestingrequired(String s) {
		mistestingrequired = s;
	}

	public String getComplianceissue() {
		return complianceissue;
	}

	public void setComplianceissue(String s) {
		complianceissue = s;
	}

	public String getMistestsubmitted() {
		return mistestsubmitted;
	}

	public void setMistestsubmitted(String s) {
		mistestsubmitted = s;
	}

	protected int id;
	protected int buildingId;
	protected String facilitydesinatedId;
	protected int ModifiedInPast;
	protected String DisconnectedYear;
	protected String Ocomments;
	protected String misname;
	protected String mismake;
	protected String mismodel;
	protected String miscapacity;
	protected String miscapacityunit;
	protected String mispollutant1;
	protected String mispollutant2;
	protected String mispollutant3;
	protected String mispollutant1max;
	protected String mispollutant2max;
	protected String mispollutant3max;
	protected String misdec;
	protected String misdep;
	protected String misdob;
	protected String misdoh;
	protected String misfd;
	protected String misother;
	protected String misissuedate;
	protected String misexpirationdate;
	protected String misexpirationdateNote;
	protected String mismonthly;
	protected String misquartly;
	protected String missemiannualy;
	protected String misannualy;
	protected String misanniversary;
	protected String mistestingrequired;
	protected String complianceissue;
	protected String mistestsubmitted;
	protected String dobsignoff;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.OtherSourceVo.class);

}