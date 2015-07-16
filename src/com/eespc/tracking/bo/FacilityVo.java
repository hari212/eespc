package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.eespc.tracking.bo.myenum.FacilityTypeEnum;
import com.eespc.tracking.entity.BuildingEntity;
import com.eespc.tracking.entity.ContactEntity;
import com.eespc.tracking.entity.FacilityEntity;

public class FacilityVo implements Serializable {

	public FacilityVo() {
		id = -99;
		decId = null;
		clientName = null;
		facilityType = -99;
		vicePresident = null;
		facilityAddress = null;
		contactList = null;
		borough = -99;
		buildingList = null;
		phone = null;
		fax = null;
		county = null;
		town = null;
		fuelConsumptionList = null;
		o_fuelConsumptionList = null;
		fuelcaping = null;
		oilcap = null;
		gascap = null;
		numofbldg = null;
	}

	public FacilityVo(ResultSet resultset) throws SQLException {
		id = -99;
		decId = null;
		clientName = null;
		facilityType = -99;
		vicePresident = null;
		facilityAddress = null;
		contactList = null;
		borough = -99;
		buildingList = null;
		phone = null;
		fax = null;
		county = null;
		town = null;
		fuelConsumptionList = null;
		o_fuelConsumptionList = null;
		fuelcaping = null;
		oilcap = null;
		gascap = null;
		numofbldg = null;

		String s = "";
		id = resultset.getInt("FACILITYID");
		decId = resultset.getString("DECID");
		clientName = resultset.getString("CLIENTNAME");
		s = resultset.getString("FACILITYTYPE");
		vicePresident = resultset.getString("VICEPRESIDENT");
		if (s != null && s.trim().length() > 0)
			facilityType = Integer.parseInt(s);
		s = resultset.getString("BOROUGH");
		if (s != null && s.trim().length() > 0)
			borough = Integer.parseInt(s);
		phone = resultset.getString("PHONE");
		fax = resultset.getString("FAX");
		facilityAddress = new AddressVo(resultset);
		county = resultset.getString("COUNTY");
		town = resultset.getString("TOWN");
		fuelcaping = resultset.getString("FUELCAPING");
		oilcap = resultset.getString("OILCAP");
		gascap = resultset.getString("GASCAP");
		numofbldg = resultset.getString("NUMOFBLDG");
	}

	public String getNumofbldg() {
		return numofbldg;
	}

	public void setNumofbldg(String s) {
		numofbldg = s;
	}

	public String getFuelcaping() {
		return fuelcaping;
	}

	public void setFuelcaping(String s) {
		fuelcaping = s;
	}

	public String getOilcap() {
		return oilcap;
	}

	public void setOilcap(String s) {
		oilcap = s;
	}

	public String getGascap() {
		return gascap;
	}

	public void setGascap(String s) {
		gascap = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getClientName() {
		return clientName;
	}

	public String getDecId() {
		return decId;
	}

	public AddressVo getFacilityAddress() {
		return facilityAddress;
	}

	public int getFacilityType() {
		return facilityType;
	}

	public void setClientName(String s) {
		clientName = s;
	}

	public void setDecId(String s) {
		if (s != null)
			decId = s.trim();
	}

	public void setFacilityAddress(AddressVo addressvo) {
		facilityAddress = addressvo;
	}

	public void setFacilityType(int i) {
		facilityType = i;
	}

	public List getContactListForInsert() {
		return contactList;
	}

	public List getContactList() {
		if (contactList == null)
			contactList = ContactEntity.getContactList(getId());
		return contactList;
	}

	public void setContactList(List list) {
		contactList = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer("FacilityVo : ");
		stringbuffer.append("id=").append(id).append(" ");
		stringbuffer.append("decId=").append(decId).append(" ");
		stringbuffer.append("clientName=").append(clientName).append(" ");
		stringbuffer.append("facilityType=").append(facilityType).append(" ");
		stringbuffer.append("ADDRESS :");
		if (facilityAddress != null)
			stringbuffer.append(facilityAddress.toString());
		return stringbuffer.toString();
	}

	public int getBorough() {
		return borough;
	}

	public void setBorough(int i) {
		borough = i;
	}

	public String getVicePresident() {
		return vicePresident;
	}

	public void setVicePresident(String s) {
		vicePresident = s;
	}

	public List getBuildingList() {
		if (buildingList == null) {
			buildingList = BuildingEntity.getBuildingsInFacility(getId());
			return buildingList;
		} else {
			return buildingList;
		}
	}

	public void setBuildingList(List list) {
		buildingList = list;
	}

	public String getFacilityTypeName() {
		if (getFacilityType() >= 0)
			return FacilityTypeEnum.get(getFacilityType()).getName();
		else
			return "";
	}

	public String getFacilityCity() {
		if (facilityAddress != null) {
			String s = facilityAddress.getCity();
			if (null != s)
				return s;
		}
		return "";
	}

	public String getFacilityState() {
		if (facilityAddress != null) {
			String s = facilityAddress.getState();
			if (null != s)
				return s;
		}
		return "";
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String s) {
		fax = s;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String s) {
		town = s;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String s) {
		county = s;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String s) {
		phone = s;
	}

	public List getFuelConsumptionList() {
		if (fuelConsumptionList == null)
			try {
				fuelConsumptionList = FacilityEntity
						.getFuelConsumptionList(getId());
			} catch (Exception exception) {
				System.out.println("Exception:" + exception);
			}
		return fuelConsumptionList;
	}

	public List geto_FuelConsumptionList() {

		if (o_fuelConsumptionList == null)
			try {
				o_fuelConsumptionList = FacilityEntity
						.geto_FuelConsumptionList(getId());
			} catch (Exception exception) {
				System.out.println("Exception:" + exception);
			}
		return o_fuelConsumptionList;
	}

	public void setFuelConsumptionList(List list) {
		fuelConsumptionList = list;
	}

	public void seto_FuelConsumptionList(List list) {
		o_fuelConsumptionList = list;
	}

	protected int id;
	protected String decId;
	protected String clientName;
	protected int facilityType;
	protected String vicePresident;
	protected AddressVo facilityAddress;
	protected List contactList;
	protected int borough;
	protected List buildingList;
	protected String phone;
	protected String fax;
	protected String county;
	protected String town;
	protected List fuelConsumptionList;
	protected List o_fuelConsumptionList;
	protected String fuelcaping;
	protected String oilcap;
	protected String gascap;
	protected String numofbldg;
}