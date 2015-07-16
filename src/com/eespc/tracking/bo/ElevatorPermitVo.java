package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class ElevatorPermitVo {

	public ElevatorPermitVo() {
		permitId = -99;
		elevatorId = -99;
		permitNumber = null;
		lastInspDate = null;
		nextInspDate = null;
		reportSubmitDate = null;
		reportSubmitDateNote = null;
		nextInspDateNote = null;
		firmInspected = null;

	}

	public ElevatorPermitVo(ResultSet resultset) throws SQLException {
		permitId = -99;
		elevatorId = -99;
		permitNumber = null;
		lastInspDate = null;
		nextInspDate = null;
		reportSubmitDate = null;
		firmInspected = null;
		reportSubmitDateNote = null;
		nextInspDateNote = null;
		permitId = resultset.getInt("ELEVATORPERMITID");
		elevatorId = resultset.getInt("ELEVATORID");
		permitNumber = resultset.getString("PERMITNUMBER");
		String s = resultset.getString("LASTINSPECTIONDATE");
		if (UtilityObject.isNotEmpty(s))
			lastInspDate = UtilityObject.convertToString(resultset
					.getDate("LASTINSPECTIONDATE"));
		s = resultset.getString("NEXTINSPECTIONDATE");
		if (UtilityObject.isNotEmpty(s))
			nextInspDate = UtilityObject.convertToString(resultset
					.getDate("NEXTINSPECTIONDATE"));
		s = resultset.getString("REPORTSUBMITTALDATE");
		if (UtilityObject.isNotEmpty(s))
			reportSubmitDate = UtilityObject.convertToString(resultset
					.getDate("REPORTSUBMITTALDATE"));
		reportSubmitDateNote = resultset.getString("REPORTSUBMITDATENOTE");
		nextInspDateNote = resultset.getString("NEXTINSPDATENOTE");
		firmInspected = resultset.getString("FIRMINSPECTED");
	}

	public int getElevatorId() {
		return elevatorId;
	}

	public void setElevatorId(int i) {
		elevatorId = i;
	}

	public String getReportSubmitDateNote() {
		return reportSubmitDateNote;
	}

	public void setReportSubmitDateNote(String s) {
		reportSubmitDateNote = s;
	}

	public String getNextInspDateNote() {
		return nextInspDateNote;
	}

	public void setNextInspDateNote(String s) {
		nextInspDateNote = s;
	}

	public String getFirmInspected() {
		return firmInspected;
	}

	public void setFirmInspected(String s) {
		firmInspected = s;
	}

	public String getLastInspDate() {
		return lastInspDate;
	}

	public void setLastInspDate(String s) {
		lastInspDate = s;
	}

	public String getNextInspDate() {
		return nextInspDate;
	}

	public void setNextInspDate(String s) {
		nextInspDate = s;
	}

	public int getPermitId() {
		return permitId;
	}

	public void setPermitId(int i) {
		permitId = i;
	}

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String s) {
		permitNumber = s;
	}

	public String getReportSubmitDate() {
		return reportSubmitDate;
	}

	public void setReportSubmitDate(String s) {
		reportSubmitDate = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ",";
		stringbuffer.append("permitId =").append(permitId).append(s);
		stringbuffer.append("elevatorId =").append(elevatorId).append(s);
		stringbuffer.append("permitNumber =").append(permitNumber).append(s);
		stringbuffer.append("lastInspDate =").append(lastInspDate).append(s);
		stringbuffer.append("nextInspDate =").append(nextInspDate).append(s);
		stringbuffer.append("reportSubmitDate =").append(reportSubmitDate)
				.append(s);
		stringbuffer.append("nextInspDateNote =").append(nextInspDateNote);
		stringbuffer.append("reportSubmitDateNote =").append(
				reportSubmitDateNote);
		stringbuffer.append("firmInspected =").append(firmInspected);
		return stringbuffer.toString();
	}

	protected int permitId;
	protected int elevatorId;
	protected String permitNumber;
	protected String lastInspDate;
	protected String nextInspDate;
	protected String reportSubmitDate;
	protected String firmInspected;
	protected String nextInspDateNote;
	protected String reportSubmitDateNote;
}