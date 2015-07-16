package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.util.UtilityObject;

public class StackTestVo {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.StackTestVo.class);
	protected int id;
	protected int incremId;
	protected String stYear;
	protected String stTestAgency;
	protected String stBoilerTest;
	protected String stParam1;
	protected String stParam2;
	protected String stParam3;
	protected String stTestonNG;
	protected int stTestFuel;
	protected String stProSub;
	protected String stConductBy;
	protected String stRptSubm;
	protected Date stProSubmDate;
	protected String stTestPassed;
	protected String stReTest;
	protected Date stNextTestDt;

	public StackTestVo() {
		id = -99;
		incremId = -99;
		stYear = null;
		stTestAgency = null;
		stBoilerTest = null;
		stParam1 = null;
		stParam2 = null;
		stParam3 = null;
		stTestonNG = null;
		stTestFuel = -99;
		stProSub = null;
		stConductBy = null;
		stRptSubm = null;
		stProSubmDate = null;
		stTestPassed = null;
		stReTest = null;
		stNextTestDt = null;
	}

	public StackTestVo(ResultSet resultset) throws SQLException {
		id = -99;
		incremId = -99;
		stYear = null;
		stTestAgency = null;
		stBoilerTest = null;
		stParam1 = null;
		stParam2 = null;
		stParam3 = null;
		stTestonNG = null;
		stTestFuel = -99;
		stProSub = null;
		stConductBy = null;
		stRptSubm = null;
		stProSubmDate = null;
		stTestPassed = null;
		stReTest = null;
		stNextTestDt = null;
		id = resultset.getInt("STACKTESTINFO");
		incremId = resultset.getInt("INCINERATORCREMATORIESID");
		stYear = resultset.getString("YEAR");
		stTestAgency = resultset.getString("TESTRQDBYAGENCY");
		stBoilerTest = resultset.getString("OTHERBLRCOMBINED");
		stParam1 = resultset.getString("PARAMETERNOX");
		stParam2 = resultset.getString("PARAMETERSO");
		stParam3 = resultset.getString("PARAMETERPM");
		stTestonNG = resultset.getString("TESTONNATURALGAS");
		stTestFuel = resultset.getInt("TESTONFUEL");
		stProSub = resultset.getString("PROTOCOLSUBMITTED");
		stConductBy = resultset.getString("CONDUCTEDBY");
		stRptSubm = resultset.getString("RPTSUBMITTED");
		stProSubmDate = resultset.getDate("PROTOCOLSUBMITDATE");
		stTestPassed = resultset.getString("TESTPASSED");
		stReTest = resultset.getString("RETESTPLANNED");
		stNextTestDt = resultset.getDate("NEXTTESTDATE");
	}

	public int getIncineratorId() {
		return incremId;
	}

	public void setIncineratorId(int i) {
		incremId = i;
	}

	public Date getProSubmissionDate() {
		return stProSubmDate;
	}

	public String getProSubmissionDateStr() {
		if (stProSubmDate == null) {
			return "";
		} else {
			return UtilityObject.convertToString(stProSubmDate);
		}
	}

	public void setProSubmissionDate(Date date) {
		stProSubmDate = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public Date getNextTestDate() {
		return stNextTestDt;
	}

	public String getNextTestDateStr() {
		if (stNextTestDt == null) {
			return "";
		} else {
			return UtilityObject.convertToString(stNextTestDt);
		}
	}

	public void setNextTestDate(Date date) {
		stNextTestDt = date;
	}

	public int getTestFuel() {
		return stTestFuel;
	}

	public void setTestFuel(int i) {
		stTestFuel = i;
	}

	public String getYear() {
		return stYear;
	}

	public void setYear(String s) {
		stYear = s;
	}

	public String getTestAgency() {
		return stTestAgency;
	}

	public void setTestAgency(String s) {
		stTestAgency = s;
	}

	public String getBoilerTest() {
		return stBoilerTest;
	}

	public void setBoilerTest(String s) {
		stBoilerTest = s;
	}

	public String getParam1() {
		return stParam1;
	}

	public void setParam1(String s) {
		stParam1 = s;
	}

	public String getParam2() {
		return stParam2;
	}

	public void setParam2(String s) {
		stParam2 = s;
	}

	public String getParam3() {
		return stParam3;
	}

	public void setParam3(String s) {
		stParam3 = s;
	}

	public String getTestOnNaturalGas() {
		return stTestonNG;
	}

	public void setTestOnNaturalGas(String s) {
		stTestonNG = s;
	}

	public String getProtocolSubmitted() {
		return stProSub;
	}

	public void setProtocolSubmitted(String s) {
		stProSub = s;
	}

	public String getConductedBy() {
		return stConductBy;
	}

	public void setConductedBy(String s) {
		stConductBy = s;
	}

	public String getReportSubmitted() {
		return stRptSubm;
	}

	public void setReportSubmitted(String s) {
		stRptSubm = s;
	}

	public String getTestPassed() {
		return stTestPassed;
	}

	public void setTestPassed(String s) {
		stTestPassed = s;
	}

	public String getReTest() {
		return stReTest;
	}

	public void setReTest(String s) {
		stReTest = s;
	}

}
