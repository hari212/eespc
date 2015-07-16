package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class BuildingInspectionReportVo {

	public BuildingInspectionReportVo() {
		id = -99;
		isFacadeDobInspected = null;
		cycle = null;
		facadePeriod = null;
		submitedBy = null;
		isFacadeRepaired = false;
		isDobPermitObtained = false;
		jobNumberRepairWork = null;
		repairedYear = null;
		actualInspectionDate = null;
		nextInspectionDate = null;
		filingDateNotes = null;
		departmentId = -99;
	}

	public BuildingInspectionReportVo(ResultSet resultset) throws SQLException {
		id = -99;
		isFacadeDobInspected = null;
		cycle = null;
		facadePeriod = null;
		submitedBy = null;
		isFacadeRepaired = false;
		isDobPermitObtained = false;
		jobNumberRepairWork = null;
		repairedYear = null;
		actualInspectionDate = null;
		nextInspectionDate = null;
		filingDateNotes = null;
		departmentId = -99;
		if (resultset != null) {
			id = resultset.getInt("bldginsprepid");
			departmentId = resultset.getInt("departmentid");
			cycle = resultset.getString("cycle");
			facadePeriod = resultset.getString("facadeperiod");
			submitedBy = resultset.getString("submitedby");

			String s1 = resultset.getString("facadeinspfordob");
			isFacadeDobInspected = s1;

			/*
			 * String s = resultset.getString("facadeinspfordob"); if(s != null
			 * && s.trim().equalsIgnoreCase("Y")) isFacadeDobInspected = true;
			 */
			String s = resultset.getString("facaderepairrqd");
			if (s != null && s.trim().equalsIgnoreCase("Y"))
				isFacadeRepaired = true;
			s = resultset.getString("dobpermitobtained");
			if (s != null && s.trim().equalsIgnoreCase("Y"))
				isDobPermitObtained = true;

			jobNumberRepairWork = resultset.getString("jobnumberrepairwork");
			repairedYear = resultset.getString("repairedyear");
			actualInspectionDate = resultset.getString("actualinspdate");
			nextInspectionDate = resultset.getString("nextinspdate");
			filingDateNotes = resultset.getString("filingdatenotes");
		}
	}

	public boolean isDobPermitObtained() {
		return isDobPermitObtained;
	}

	/*
	 * public boolean isFacadeDobInspected() { return isFacadeDobInspected; }
	 */

	public void setFilingDateNotes(String s) {
		filingDateNotes = s;
	}

	public String getFilingDateNotes() {
		return filingDateNotes;
	}

	public String isFacadeDobInspected() {
		return isFacadeDobInspected;
	}

	public void setFacadeDobInspected(String flag) {
		isFacadeDobInspected = flag;
	}

	public boolean isFacadeRepaired() {
		return isFacadeRepaired;
	}

	public String getNextInspectionDate() {
		return nextInspectionDate;
	}

	public String getNextInspectionDate(String format) {

		try {
			return new SimpleDateFormat(format).format(new SimpleDateFormat(
					"yyyy-MM-dd").parse(nextInspectionDate));
		} catch (Exception ex) {
			return "";
		}
	}

	public String getSubmitedBy() {
		return submitedBy;
	}

	public String getFacadePeriod() {
		return facadePeriod;
	}

	public String getCycle() {
		return cycle;
	}

	public String getRepairedYear() {
		return repairedYear;
	}

	public String getActualInspectionDate() {
		return actualInspectionDate;
	}

	public String getActualInspectionDate(String format) {

		try {
			return new SimpleDateFormat(format).format(new SimpleDateFormat(
					"yyyy-MM-dd").parse(actualInspectionDate));
		} catch (Exception ex) {
			return "";
		}
	}

	public String getJobNumberRepairWork() {
		return jobNumberRepairWork;
	}

	public void setDobPermitObtained(boolean flag) {
		isDobPermitObtained = flag;
	}

	/*
	 * public void setFacadeDobInspected(boolean flag) { isFacadeDobInspected =
	 * flag; }
	 */

	public void setFacadeRepaired(boolean flag) {
		isFacadeRepaired = flag;
	}

	public void setNextInspectionDate(String s) {
		nextInspectionDate = s;
	}

	public void setSubmitedBy(String s) {
		submitedBy = s;
	}

	public void setFacadePeriod(String s) {
		facadePeriod = s;
	}

	public void setCycle(String s) {
		cycle = s;
	}

	public void setRepairedYear(String s) {
		repairedYear = s;
	}

	public void setActualInspectionDate(String s) {
		actualInspectionDate = s;
	}

	public void setJobNumberRepairWork(String s) {
		jobNumberRepairWork = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int i) {
		departmentId = i;
	}

	public static void main(String[] arg) {
		BuildingInspectionReportVo bls = new BuildingInspectionReportVo();
		System.out.println(bls.getActualInspectionDate("MM/dd/yyyy"));
		System.out.println(bls.getNextInspectionDate("MM/dd/yyyy"));
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();

		stringbuffer.append("isFacadeDobInspected =")
				.append(isFacadeDobInspected).append(" ");
		stringbuffer.append("cycle =").append(cycle).append(" ");
		stringbuffer.append("facadePeriod =").append(facadePeriod).append(" ");
		stringbuffer.append("submitedBy =").append(submitedBy).append(" ");
		stringbuffer.append("isFacadeRepaired =").append(isFacadeRepaired)
				.append(" ");
		stringbuffer.append("jobNumberRepairWork =")
				.append(jobNumberRepairWork).append(" ");
		stringbuffer.append("repairedYear =").append(repairedYear).append(" ");
		stringbuffer.append("isDobPermitObtained =")
				.append(isDobPermitObtained).append(" ");
		stringbuffer.append("actualInspectionDate =")
				.append(actualInspectionDate).append(" ");
		stringbuffer.append("nextInspectionDate =").append(nextInspectionDate)
				.append(" ");
		stringbuffer.append("filingDateNotes  =").append(filingDateNotes);
		return super.toString();
	}

	/*
	 * public String getInpectionDate4Display() { return
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(getNextInspectionDate()); }
	 */

	/*
	 * public String getActualInpectionDate4Display() { return
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(getActualInspectionDate()); }
	 */

	protected int id;
	protected String facadePeriod;
	protected String cycle;
	protected String isFacadeDobInspected;
	protected String submitedBy;
	protected boolean isFacadeRepaired;
	protected boolean isDobPermitObtained;
	protected String nextInspectionDate;
	protected String repairedYear;
	protected String actualInspectionDate;
	protected String jobNumberRepairWork;
	protected String filingDateNotes;
	protected int departmentId;
}