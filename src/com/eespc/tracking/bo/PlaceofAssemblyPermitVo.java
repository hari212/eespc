package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class PlaceofAssemblyPermitVo {

	public PlaceofAssemblyPermitVo() {
		id = -99;
		placeofassemblyId = -99;
		dobIssueDate = null;
		dobExpirationDate = null;
		dobExpirationNote = null;
		expirationDateStr = null;
		issueDateStr = null;

	}

	public PlaceofAssemblyPermitVo(ResultSet resultset) throws SQLException {
		id = -99;
		placeofassemblyId = -99;
		dobIssueDate = null;
		dobExpirationDate = null;
		dobExpirationNote = null;
		expirationDateStr = null;
		issueDateStr = null;

		id = resultset.getInt("PLACEOFASSEMBLYPERMITID");
		placeofassemblyId = resultset.getInt("PLACEOFASSEMBLYID");
		dobIssueDate = resultset.getDate("ISSUEDDATE");
		dobExpirationDate = resultset.getDate("EXPIRATIONDATE");
		dobExpirationNote = resultset.getString("DOBEXPNOTE");

	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getPlaceofassemblyId() {
		return placeofassemblyId;
	}

	public void setPlaceofassemblyId(int i) {
		placeofassemblyId = i;
	}

	public Date getDobIssueDate() {
		return dobIssueDate;
	}

	public void setDobIssueDate(Date date) {
		dobIssueDate = date;
	}

	public String getIssueDateStr() {
		if (dobIssueDate == null)
			return "";
		else
			return UtilityObject.convertToString(dobIssueDate);
	}

	public void setIssueDateStr(String s) {
		issueDateStr = s;
	}

	public Date getDobExpirationDate() {
		return dobExpirationDate;
	}

	public void setDobExpirationDate(Date date) {
		dobExpirationDate = date;
	}

	public String getExpirationDateStr() {
		if (dobExpirationDate == null)
			return "";
		else
			return UtilityObject.convertToString(dobExpirationDate);
	}

	public void setExpirationDateStr(String s) {
		expirationDateStr = s;
	}

	public String getDobExpirationNote() {
		return dobExpirationNote;
	}

	public void setDobExpirationNote(String s) {
		dobExpirationNote = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ", ";
		stringbuffer.append("id  =").append(id).append(s);
		stringbuffer.append("placeofassemblyId  =").append(placeofassemblyId)
				.append(s);
		stringbuffer.append("dobIssueDate  =").append(dobIssueDate).append(s);
		stringbuffer.append("dobExpirationDate  =").append(dobExpirationDate)
				.append(s);
		stringbuffer.append("dobExpirationNote  =").append(dobExpirationNote);
		return stringbuffer.toString();
	}

	protected int id;
	protected int placeofassemblyId;
	protected Date dobIssueDate;
	protected Date dobExpirationDate;
	protected String dobExpirationNote;
	protected String expirationDateStr;
	protected String issueDateStr;

}