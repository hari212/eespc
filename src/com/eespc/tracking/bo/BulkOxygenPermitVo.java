package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class BulkOxygenPermitVo {

	protected int id;
	protected int objectId;
	protected int objectType;
	protected String permitNumber;
	protected Date issueDate;
	protected int depId;
	protected String issueDateStr;

	public BulkOxygenPermitVo() {
		id = -99;
		objectId = -99;
		objectType = -99;
		permitNumber = null;
		issueDate = null;
		depId = -99;
		issueDateStr = null;

	}

	public BulkOxygenPermitVo(ResultSet resultset) throws SQLException {
		id = -99;
		objectId = -99;
		objectType = -99;
		permitNumber = null;
		issueDate = null;
		depId = -99;
		issueDateStr = null;

		id = resultset.getInt("PERMIT_ID");
		objectId = resultset.getInt("OBJECT_ID");
		objectType = resultset.getInt("OBJECT_TYPE");
		permitNumber = resultset.getString("PERMIT_NUMBER");
		issueDate = resultset.getDate("PERMIT_ISSUE_DATE");

		depId = resultset.getInt("DEPT_ID");
		/*
		 * try {
		 * 
		 * year = resultset.getString("YEAR");
		 * 
		 * } catch(SQLException sqlexception) { }
		 */
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int i) {
		depId = i;
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

}
