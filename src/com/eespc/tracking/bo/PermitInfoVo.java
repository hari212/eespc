package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class PermitInfoVo {

	protected int id;
	protected int objectId;
	protected int objectType;
	protected String permitNumber;
	protected Date issueDate;
	protected Date expirationDate;
	protected String note;
	// protected String isDepPermitInspected;
	protected int depId;
	protected String issueDateStr;
	protected String expirationDateStr;
	protected String year;

	public PermitInfoVo() {
		id = -99;
		objectId = -99;
		objectType = -99;
		permitNumber = null;
		issueDate = null;
		expirationDate = null;
		note = null;
		// isDepPermitInspected = null;
		depId = -99;
		issueDateStr = null;
		expirationDateStr = null;
		year = null;
	}

	public PermitInfoVo(ResultSet resultset) throws SQLException {
		id = -99;
		objectId = -99;
		objectType = -99;
		permitNumber = null;
		issueDate = null;
		expirationDate = null;
		note = null;
		// isDepPermitInspected = null;
		depId = -99;
		issueDateStr = null;
		expirationDateStr = null;
		year = null;
		id = resultset.getInt("PERMIT_ID");
		objectId = resultset.getInt("OBJECT_ID");
		objectType = resultset.getInt("OBJECT_TYPE");
		permitNumber = resultset.getString("PERMIT_NUMBER");
		issueDate = resultset.getDate("PERMIT_ISSUE_DATE");
		expirationDate = resultset.getDate("PERMIT_EXP_DATE");
		note = resultset.getString("NOTE");
		depId = resultset.getInt("DEPT_ID");
		try {

			/*
			 * String s1 = resultset.getString("deppermitinspected");
			 * isDepPermitInspected = s1;
			 */

			year = resultset.getString("YEAR");
			// note = resultset.getString("NOTE");
		} catch (SQLException sqlexception) {
		}
	}

	/*
	 * public String isDepPermitInspected() { return isDepPermitInspected; }
	 * 
	 * public void setDepPermitInspected(String flag) { isDepPermitInspected =
	 * flag; }
	 */

	public int getDepId() {
		return depId;
	}

	public void setDepId(int i) {
		depId = i;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date date) {
		expirationDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date date) {
		issueDate = date;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int i) {
		objectId = i;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int i) {
		objectType = i;
	}

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String s) {
		permitNumber = s;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String s) {
		note = s;
	}

	public String getExpirationDateStr() {
		if (expirationDate == null) {
			return "";
		} else {
			return UtilityObject.convertToString(expirationDate);
		}
	}

	public void setExpirationDateStr(String s) {
		expirationDateStr = s;
	}

	public String getIssueDateStr() {
		if (issueDate == null) {
			return "";
		} else {
			return UtilityObject.convertToString(issueDate);
		}
	}

	public void setIssueDateStr(String s) {
		issueDateStr = s;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String s) {
		year = s;
	}
}
