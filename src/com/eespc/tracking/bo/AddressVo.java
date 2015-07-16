package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class AddressVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddressVo() {
		id = -99;
		address1 = null;
		address2 = null;
		address3 = null;
		city = null;
		state = null;
		zipCode = null;
		country = null;
	}

	public AddressVo(ResultSet resultset) throws SQLException {
		id = -99;
		address1 = null;
		address2 = null;
		address3 = null;
		city = null;
		state = null;
		zipCode = null;
		country = null;
		id = resultset.getInt("ADDRESSID");
		address1 = resultset.getString("ADDRESS1");
		address2 = resultset.getString("ADDRESS2");
		address3 = resultset.getString("ADDRESS3");
		city = resultset.getString("CITY");
		state = resultset.getString("STATE");
		zipCode = resultset.getString("ZIPCODE");
		country = resultset.getString("ZIPCODE");
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getAddress3() {
		return address3;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setAddress1(String s) {
		address1 = s;
	}

	public void setAddress2(String s) {
		address2 = s;
	}

	public void setAddress3(String s) {
		address3 = s;
	}

	public void setCity(String s) {
		city = s;
	}

	public void setState(String s) {
		state = s;
	}

	public void setZipCode(String s) {
		zipCode = s;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String s) {
		country = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer("AddressVO :");
		stringbuffer.append("address1=").append(address1).append(" ");
		stringbuffer.append("address2=").append(address2).append(" ");
		stringbuffer.append("address3=").append(address3).append(" ");
		stringbuffer.append("city=").append(city).append(" ");
		stringbuffer.append("state=").append(state).append(" ");
		stringbuffer.append("zipCode=").append(zipCode).append(" ");
		stringbuffer.append("country=").append(country).append(" ");
		return stringbuffer.toString();
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getFormattedAddress() {
		StringBuffer stringbuffer = new StringBuffer();
		if (UtilityObject.isNotEmpty(getAddress1()))
			stringbuffer.append(getAddress1()).append(", ");
		if (UtilityObject.isNotEmpty(getAddress2())
				&& !getAddress2().equalsIgnoreCase("NULL"))
			stringbuffer.append(getAddress2()).append(", ");
		if (UtilityObject.isNotEmpty(getAddress3())
				&& !getAddress3().equalsIgnoreCase("NULL"))
			stringbuffer.append(getAddress3()).append(", ");
		if (UtilityObject.isNotEmpty(getCity()))
			stringbuffer.append(getCity()).append(", ");
		if (UtilityObject.isNotEmpty(getState()))
			stringbuffer.append(getState()).append("-");
		if (UtilityObject.isNotEmpty(getZipCode()))
			stringbuffer.append(getZipCode());
		return stringbuffer.toString();
	}

	public String getFormatted_Address() {
		StringBuffer stringbuffer = new StringBuffer();
		if (UtilityObject.isNotEmpty(getAddress1()))
			stringbuffer.append(getAddress1()).append("");
		else
			stringbuffer.append(getAddress1()).append("-");
		/*
		 * if(UtilityObject.isNotEmpty(getAddress2()) &&
		 * !getAddress2().equalsIgnoreCase("NULL"))
		 * stringbuffer.append(getAddress2()).append(", ");
		 * if(UtilityObject.isNotEmpty(getAddress3()) &&
		 * !getAddress3().equalsIgnoreCase("NULL"))
		 * stringbuffer.append(getAddress3()).append(", ");
		 * if(UtilityObject.isNotEmpty(getCity()))
		 * stringbuffer.append(getCity()).append(", ");
		 * if(UtilityObject.isNotEmpty(getState()))
		 * stringbuffer.append(getState()).append("-");
		 * if(UtilityObject.isNotEmpty(getZipCode()))
		 * stringbuffer.append(getZipCode());
		 */
		return stringbuffer.toString();
	}

	protected int id;
	protected String address1;
	protected String address2;
	protected String address3;
	protected String city;
	protected String state;
	protected String zipCode;
	protected String country;
}