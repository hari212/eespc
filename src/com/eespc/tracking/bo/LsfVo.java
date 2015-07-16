package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.LsfEntity;

public class LsfVo implements Serializable {

	public LsfVo() {

		id = -99;
		firstname = null;
		lastname = null;
		certificatenumber = null;
		phonenumber = null;
		department = null;
		issuedate = null;
		expirationdate = null;
		operatingequipments = null;
		equipmentoperated = null;

		htypeofcoffrequired = null;
		Htypeofcoffrequiredobject = null;
		operatingequipmentsobject = null;

	}

	public LsfVo(ResultSet resultset) throws SQLException {

		id = -99;
		firstname = null;
		lastname = null;
		certificatenumber = null;
		phonenumber = null;
		department = null;
		operatingequipments = null;
		equipmentoperated = null;

		htypeofcoffrequired = null;
		issuedate = null;
		expirationdate = null;

		Htypeofcoffrequiredobject = null;
		operatingequipmentsobject = null;

		id = resultset.getInt("lsfid");
		firstname = resultset.getString("firstname");
		lastname = resultset.getString("lastname");
		certificatenumber = resultset.getString("certificatenumber");
		phonenumber = resultset.getString("phonenumber");
		department = resultset.getString("department");
		issuedate = resultset.getString("issuedate");
		expirationdate = resultset.getString("expirationdate");
		// operatingequipments=resultset.getString("operatingequipments");
		equipmentoperated = resultset.getString("equipmentoperated");
		// htypeofcoffrequired=resultset.getString("htypeofcoffrequired");
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public void setFirstname(String data) {
		firstname = data;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setLastname(String data) {
		lastname = data;
	}

	public String getLastname() {
		return lastname;
	}

	public void setCertificatenumber(String data) {
		certificatenumber = data;
	}

	public String getCertificatenumber() {
		return certificatenumber;
	}

	public void setPhonenumber(String data) {
		phonenumber = data;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setDepartment(String data) {
		department = data;
	}

	public String getDepartment() {
		return department;
	}

	public void setOperatingequipments(String data[]) {
		operatingequipments = data;
	}

	public String[] getOperatingequipments() {
		return operatingequipments;
	}

	public void setEquipmentoperated(String data) {
		equipmentoperated = data;
	}

	public String getEquipmentoperated() {
		return equipmentoperated;
	}

	public void setHtypeofcoffrequired(String[] data) {
		htypeofcoffrequired = data;
	}

	public String[] getHtypeofcoffrequired() {
		return htypeofcoffrequired;
	}

	private List getoperatingequipmentsobject() {
		// if(operatingequipmentsobject == null)
		// {
		LsfEntity lsfentity = new LsfEntity();
		operatingequipmentsobject = lsfentity.getcoffrequiredList(getId(), 1);
		// }
		return operatingequipmentsobject;
	}

	private List getHtypeofcoffrequiredobject() {
		// if(Htypeofcoffrequiredobject == null)
		// {
		LsfEntity lsfentity = new LsfEntity();
		Htypeofcoffrequiredobject = lsfentity.getcoffrequiredList(getId(), 2);
		// }
		return Htypeofcoffrequiredobject;
	}

	public String[] get_operatingequipments() {
		List list = getoperatingequipmentsobject();

		int i = list == null ? 0 : list.size();
		if (i > 0) {
			String as[] = new String[i];
			for (int j = 0; j < i; j++) {
				as[j] = (new StringBuffer(String.valueOf(list.get(j))))
						.toString();

			}

			operatingequipments = as;
		}

		return operatingequipments;

	}

	public String[] get_Htypeofcoffrequired() {
		List list = getHtypeofcoffrequiredobject();
		int i = list == null ? 0 : list.size();
		if (i > 0) {
			String as[] = new String[i];
			for (int j = 0; j < i; j++) {

				as[j] = (new StringBuffer(String.valueOf(list.get(j))))
						.toString();
			}

			htypeofcoffrequired = as;
		}
		return htypeofcoffrequired;
	}

	public void setIssuedate(String data) {
		issuedate = data;
	}

	public String getIssuedate() {
		return issuedate;
	}

	public String getIssuedate(String format) {

		try {
			return new SimpleDateFormat(format).format(new SimpleDateFormat(
					"yyyy-MM-dd").parse(issuedate));
		} catch (Exception ex) {
			return "";
		}
	}

	public void setExpirationdate(String data) {
		expirationdate = data;
	}

	public String getExpirationdate() {
		return expirationdate;
	}

	public String getExpirationdate(String format) {

		try {
			return new SimpleDateFormat(format).format(new SimpleDateFormat(
					"yyyy-MM-dd").parse(expirationdate));
		} catch (Exception ex) {
			return "";
		}
	}

	public static void main(String[] arg) {
		LsfVo ls = new LsfVo();
		System.out.println(ls.getIssuedate("MM/dd/yyyy"));
		System.out.println(ls.getExpirationdate("MM/dd/yyyy"));
	}

	protected int id;
	protected String staffname;
	protected String firstname;
	protected String lastname;
	protected String certificatenumber;
	protected String phonenumber;
	protected String department;
	protected String issuedate;
	protected String expirationdate;
	protected String operatingequipments[];
	protected String equipmentoperated;
	protected String htypeofcoffrequired[];
	protected List Htypeofcoffrequiredobject;
	protected List operatingequipmentsobject;
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.LsfVo.class);

}