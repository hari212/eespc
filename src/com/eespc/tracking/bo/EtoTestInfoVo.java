package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class EtoTestInfoVo implements Serializable {

	public EtoTestInfoVo() {
	}

	public EtoTestInfoVo(ResultSet rst) throws SQLException {
		etoTestId = rst.getInt("ETOTESTID");
		etoId = rst.getInt("ETOID");
		year = rst.getString("YEAR");
		testDate = UtilityObject.convertToString(rst.getDate("TESTDATE"));
		nextDate = UtilityObject.convertToString(rst.getDate("NEXTDATE"));
		nextDateNote = rst.getString("NEXTDATENOTE");
	}

	public int getEtoId() {
		return etoId;
	}

	public void setEtoId(int etoId) {
		this.etoId = etoId;
	}

	public int getEtoTestId() {
		return etoTestId;
	}

	public void setEtoTestId(int etoTestId) {
		this.etoTestId = etoTestId;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public String getNextDateNote() {
		return nextDateNote;
	}

	public void setNextDateNote(String nextDateNote) {
		this.nextDateNote = nextDateNote;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	protected int etoTestId;
	protected int etoId;
	protected String year;
	protected String testDate;
	protected String nextDate;
	protected String nextDateNote;
}