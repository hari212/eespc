package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ViolationVo implements Serializable {

	public ViolationVo() {
		id = -99;
		violationWhich = -99;
		violationno = null;
		violationType = null;
		description = null;
		issueDate = null;
		remedialMeassures = null;
		contractor = null;
		stepsTaken = null;
		removalDate = null;
		interMediateStatus = null;
		finalComplianceDate = null;
		jDate = null;
		otherAgency = null;
		feePaid = null;

	}

	public ViolationVo(ResultSet resultset) throws SQLException {
		id = -99;
		violationWhich = -99;
		violationno = null;
		violationType = null;
		description = null;
		issueDate = null;
		remedialMeassures = null;
		contractor = null;
		stepsTaken = null;
		removalDate = null;
		interMediateStatus = null;
		finalComplianceDate = null;
		jDate = null;
		otherAgency = null;
		feePaid = null;

		id = resultset.getInt("violationid");
		violationWhich = resultset.getInt("violationWhich");
		violationno = resultset.getString("violationno");
		violationType = resultset.getString("violationType");
		description = resultset.getString("description");
		issueDate = resultset.getString("issueDate");
		remedialMeassures = resultset.getString("remedialMeassures");
		contractor = resultset.getString("contractor");
		stepsTaken = resultset.getString("stepsTaken");
		removalDate = resultset.getString("removalDate");
		interMediateStatus = resultset.getString("interMediateStatus");
		finalComplianceDate = resultset.getString("finalComplianceDate");
		jDate = resultset.getString("jDate");
		otherAgency = resultset.getString("OTHERAGENCY");
		feePaid = resultset.getString("FEEPAID");
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public void setFeePaid(String data) {
		feePaid = data;
	}

	public String getFeePaid() {
		return feePaid;
	}

	public void setViolationWhich(int data) {
		violationWhich = data;
	}

	public int getViolationWhich() {
		return violationWhich;
	}

	public void setViolationno(String data) {
		violationno = data;
	}

	public String getViolationno() {
		return violationno;
	}

	public void setViolationType(String data) {
		violationType = data;
	}

	public String getViolationType() {
		return violationType;
	}

	public void setDescription(String data) {
		description = data;
	}

	public String getDescription() {
		return description;
	}

	public void setIssueDate(String data) {
		issueDate = data;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setRemedialMeassures(String data) {
		remedialMeassures = data;
	}

	public String getRemedialMeassures() {
		return remedialMeassures;
	}

	public void setContractor(String data) {
		contractor = data;
	}

	public String getContractor() {
		return contractor;
	}

	public void setStepsTaken(String data) {
		stepsTaken = data;
	}

	public String getStepsTaken() {
		return stepsTaken;
	}

	public void setRemovalDate(String data) {
		removalDate = data;
	}

	public String getRemovalDate() {
		return removalDate;
	}

	public void setInterMediateStatus(String data) {
		interMediateStatus = data;
	}

	public String getInterMediateStatus() {
		return interMediateStatus;
	}

	public void setFinalComplianceDate(String data) {
		finalComplianceDate = data;
	}

	public String getFinalComplianceDate() {
		return finalComplianceDate;
	}

	public void setJDate(String data) {
		jDate = data;
	}

	public String getJDate() {
		return jDate;
	}

	public void setOtherAgency(String data) {
		otherAgency = data;
	}

	public String getOtherAgency() {
		return otherAgency;
	}

	protected int id;
	protected int violationWhich;
	protected String violationno;
	protected String violationType;
	protected String description;
	protected String issueDate;
	protected String remedialMeassures;
	protected String contractor;
	protected String stepsTaken;
	protected String removalDate;
	protected String interMediateStatus;
	protected String finalComplianceDate;
	protected String jDate;
	protected String otherAgency;
	protected String feePaid;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.ViolationVo.class);

}