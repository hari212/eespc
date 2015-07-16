package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class BridgeTunnelPermitVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BridgeTunnelPermitVo() {
		id = -99;
		bridgeTunnelId = -99;
		permitNumber = null;
		FileNo = null;
		issueDate = null;
		expirationDate = null;
		dotExpDateNote = null;
		depId = -99;
		lastInspDate = null;
		nextInspDate = null;
		fillingDate = null;
		byWho = null;
		insIssueDate = null;
		insExpDate = null;
		year = null;
		dotNextInspDateNote = null;
		dotInsExpDateNote = null;

	}

	public BridgeTunnelPermitVo(ResultSet resultset) throws SQLException {
		id = -99;
		bridgeTunnelId = -99;
		permitNumber = null;
		FileNo = null;
		issueDate = null;
		expirationDate = null;
		dotExpDateNote = null;
		depId = -99;
		lastInspDate = null;
		nextInspDate = null;
		fillingDate = null;
		byWho = null;
		insIssueDate = null;
		insExpDate = null;
		year = null;
		dotNextInspDateNote = null;
		dotInsExpDateNote = null;
		id = resultset.getInt("BRIDGETUNNELPERMITID");
		bridgeTunnelId = resultset.getInt("BRIDGETUNNELID");
		permitNumber = resultset.getString("PERMITNUMBER");
		dotExpDateNote = resultset.getString("DOTEXPDATENOTE");
		FileNo = resultset.getString("FILENO");
		String s = resultset.getString("ISSUEDATE");
		if (UtilityObject.isNotEmpty(s))
			issueDate = UtilityObject.convertToString(resultset
					.getDate("ISSUEDATE"));
		s = resultset.getString("EXPIRATIONDATE");
		if (UtilityObject.isNotEmpty(s))
			expirationDate = UtilityObject.convertToString(resultset
					.getDate("EXPIRATIONDATE"));
		depId = UtilityObject.getIntFromString(resultset.getString("DEPID"));
		s = resultset.getString("LASTINSPDATE");
		if (UtilityObject.isNotEmpty(s))
			lastInspDate = UtilityObject.convertToString(resultset
					.getDate("LASTINSPDATE"));
		s = resultset.getString("NEXTINSPDATE");
		if (UtilityObject.isNotEmpty(s))
			nextInspDate = UtilityObject.convertToString(resultset
					.getDate("NEXTINSPDATE"));
		s = resultset.getString("FILLINGDATE");
		if (UtilityObject.isNotEmpty(s))
			fillingDate = UtilityObject.convertToString(resultset
					.getDate("FILLINGDATE"));
		byWho = resultset.getString("BYWHO");
		s = resultset.getString("INSISSUEDATE");
		if (UtilityObject.isNotEmpty(s))
			insIssueDate = UtilityObject.convertToString(resultset
					.getDate("INSISSUEDATE"));
		s = resultset.getString("INSEXPDATE");
		if (UtilityObject.isNotEmpty(s))
			insExpDate = UtilityObject.convertToString(resultset
					.getDate("INSEXPDATE"));
		year = resultset.getString("YEAR");
		dotNextInspDateNote = resultset.getString("DOTNEXTINSPDATENOTE");
		dotInsExpDateNote = resultset.getString("DOTINSEXPDATENOTE");
	}

	public int getBridgeTunnelId() {
		return bridgeTunnelId;
	}

	public void setBridgeTunnelId(int i) {
		bridgeTunnelId = i;
	}

	public String getByWho() {
		return byWho;
	}

	public void setByWho(String s) {
		byWho = s;
	}

	public String getDotNextInspDateNote() {
		return dotNextInspDateNote;
	}

	public void setDotNextInspDateNote(String s) {
		dotNextInspDateNote = s;
	}

	public String getDotInsExpDateNote() {
		return dotInsExpDateNote;
	}

	public void setDotInsExpDateNote(String s) {
		dotInsExpDateNote = s;
	}

	public String getDotExpDateNote() {
		return dotExpDateNote;
	}

	public void setDotExpDateNote(String s) {
		dotExpDateNote = s;
	}

	public String getFileNo() {
		return FileNo;
	}

	public void setFileNo(String s) {
		FileNo = s;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int i) {
		depId = i;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String s) {
		expirationDate = s;
	}

	public String getFillingDate() {
		return fillingDate;
	}

	public void setFillingDate(String s) {
		fillingDate = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getInsExpDate() {
		return insExpDate;
	}

	public void setInsExpDate(String s) {
		insExpDate = s;
	}

	public String getInsIssueDate() {
		return insIssueDate;
	}

	public void setInsIssueDate(String s) {
		insIssueDate = s;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String s) {
		issueDate = s;
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

	public String getPermitNumber() {
		return permitNumber;
	}

	public void setPermitNumber(String s) {
		permitNumber = s;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String s) {
		year = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ",";
		stringbuffer.append("id =").append(id).append(s);
		stringbuffer.append("bridgeTunnelId =").append(bridgeTunnelId)
				.append(s);
		stringbuffer.append("permitNumber =").append(permitNumber).append(s);
		stringbuffer.append("FileNo =").append(FileNo).append(s);
		stringbuffer.append("issueDate =").append(issueDate).append(s);
		stringbuffer.append("expirationDate =").append(expirationDate)
				.append(s);
		stringbuffer.append("depId =").append(depId).append(s);
		stringbuffer.append("lastInspDate =").append(lastInspDate).append(s);
		stringbuffer.append("nextInspDate =").append(nextInspDate).append(s);
		stringbuffer.append("fillingDate =").append(fillingDate).append(s);
		stringbuffer.append("byWho =").append(byWho).append(s);
		stringbuffer.append("insIssueDate =").append(insIssueDate).append(s);
		stringbuffer.append("insExpDate =").append(insExpDate);
		stringbuffer.append("dotExpDateNote =").append(dotExpDateNote);
		stringbuffer.append("dotNextInspDateNote =")
				.append(dotNextInspDateNote);
		stringbuffer.append("dotInsExpDateNote =").append(dotInsExpDateNote);
		return stringbuffer.toString();
	}

	protected int id;
	protected int bridgeTunnelId;
	protected String permitNumber;
	protected String FileNo;
	protected String issueDate;
	protected String expirationDate;
	protected int depId;
	protected String lastInspDate;
	protected String nextInspDate;
	protected String fillingDate;
	protected String byWho;
	protected String insIssueDate;
	protected String insExpDate;
	protected String year;
	protected String dotExpDateNote;
	protected String dotNextInspDateNote;
	protected String dotInsExpDateNote;
}