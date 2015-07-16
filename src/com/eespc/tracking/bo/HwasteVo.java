package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HwasteVo implements Serializable {

	public HwasteVo() {
		id = -99;
		manifastno = null;
		dateOftheManifest = null;
		waste = null;
		waste1 = null;
		waste2 = null;
		waste3 = null;
		waste4 = null;
		typeOfWaste1 = -99;
		typeOfWaste2 = -99;
		typeOfWaste3 = -99;
		typeOfWaste4 = -99;
		wasteName = null;
		wasteName1 = null;
		wasteName2 = null;
		wasteName3 = null;
		wasteName4 = null;
		wasteVolume = null;
		wasteVolume1 = null;
		wasteVolume2 = null;
		wasteVolume3 = null;
		wasteVolume4 = null;
		wasteDensity = null;
		wasteDensity1 = null;
		wasteDensity2 = null;
		wasteDensity3 = null;
		wasteDensity4 = null;
		wasteUnit = null;
		wasteUnit1 = null;
		wasteUnit2 = null;
		wasteUnit3 = null;
		wasteUnit4 = null;
		wasteQuantity = null;
		wasteQuantity1 = null;
		wasteQuantity2 = null;
		wasteQuantity3 = null;
		wasteQuantity4 = null;
		hazardousTotalWaste = null;
		divisioncontactname = null;
		telephonenumber = null;
		epaidWaste = null;
		companyName = null;
		epaId = null;
		hazardousWasteCategory = -99;
		typeOfWaste = -99;
		epaidFinaldes = null;
	}

	public HwasteVo(ResultSet resultset) throws SQLException {
		id = -99;
		manifastno = null;
		dateOftheManifest = null;
		waste = null;
		waste1 = null;
		waste2 = null;
		waste3 = null;
		waste4 = null;
		typeOfWaste = -99;
		typeOfWaste1 = -99;
		typeOfWaste2 = -99;
		typeOfWaste3 = -99;
		typeOfWaste4 = -99;
		wasteName = null;
		wasteName1 = null;
		wasteName2 = null;
		wasteName3 = null;
		wasteName4 = null;
		wasteVolume = null;
		wasteVolume1 = null;
		wasteVolume2 = null;
		wasteVolume3 = null;
		wasteVolume4 = null;
		wasteDensity = null;
		wasteDensity1 = null;
		wasteDensity2 = null;
		wasteDensity3 = null;
		wasteDensity4 = null;
		wasteUnit = null;
		wasteUnit1 = null;
		wasteUnit2 = null;
		wasteUnit3 = null;
		wasteUnit4 = null;
		wasteQuantity = null;
		wasteQuantity1 = null;
		wasteQuantity2 = null;
		wasteQuantity3 = null;
		wasteQuantity4 = null;
		hazardousTotalWaste = null;
		divisioncontactname = null;
		telephonenumber = null;
		epaidWaste = null;
		companyName = null;
		epaId = null;
		hazardousWasteCategory = -99;
		epaidFinaldes = null;

		id = resultset.getInt("hwasteid");
		manifastno = resultset.getString("manifastno");
		dateOftheManifest = resultset.getString("dateOftheManifest");
		waste = resultset.getString("waste");
		waste1 = resultset.getString("waste1");
		waste2 = resultset.getString("waste2");
		waste3 = resultset.getString("waste3");
		waste4 = resultset.getString("waste4");

		if (resultset.getString("typeOfWaste") != null)
			typeOfWaste = resultset.getInt("typeOfWaste");

		if (resultset.getString("typeOfWaste1") != null)
			typeOfWaste1 = resultset.getInt("typeOfWaste1");

		if (resultset.getString("typeOfWaste2") != null)
			typeOfWaste2 = resultset.getInt("typeOfWaste2");

		if (resultset.getString("typeOfWaste3") != null)
			typeOfWaste3 = resultset.getInt("typeOfWaste3");

		if (resultset.getString("typeOfWaste4") != null)
			typeOfWaste4 = resultset.getInt("typeOfWaste4");

		wasteName = resultset.getString("wasteName");
		wasteQuantity = resultset.getString("wasteQuantity");
		wasteName1 = resultset.getString("wasteName1");
		wasteQuantity1 = resultset.getString("wasteQuantity1");
		wasteName2 = resultset.getString("wasteName2");
		wasteQuantity2 = resultset.getString("wasteQuantity2");
		wasteName3 = resultset.getString("wasteName3");
		wasteQuantity3 = resultset.getString("wasteQuantity3");
		wasteVolume = resultset.getString("wasteVolume");
		wasteVolume1 = resultset.getString("wasteVolume1");
		wasteVolume2 = resultset.getString("wasteVolume2");
		wasteVolume3 = resultset.getString("wasteVolume3");
		wasteVolume4 = resultset.getString("wasteVolume4");
		wasteDensity = resultset.getString("wasteDensity");
		wasteDensity1 = resultset.getString("wasteDensity1");
		wasteDensity2 = resultset.getString("wasteDensity2");
		wasteDensity3 = resultset.getString("wasteDensity3");
		wasteDensity4 = resultset.getString("wasteDensity4");
		wasteUnit = resultset.getString("wasteUnit");
		wasteUnit1 = resultset.getString("wasteUnit1");
		wasteUnit2 = resultset.getString("wasteUnit2");
		wasteUnit3 = resultset.getString("wasteUnit3");
		wasteUnit4 = resultset.getString("wasteUnit4");
		wasteName4 = resultset.getString("wasteName4");
		wasteQuantity4 = resultset.getString("wasteQuantity4");
		hazardousTotalWaste = resultset.getString("hazardousTotalWaste");
		divisioncontactname = resultset.getString("divisioncontactname");
		telephonenumber = resultset.getString("telephonenumber");
		epaidWaste = resultset.getString("epaidWaste");
		companyName = resultset.getString("companyName");
		epaId = resultset.getString("epaId");
		epaidFinaldes = resultset.getString("epaidFinaldes");

		if (resultset.getString("hazardousWasteCategory") != null)
			hazardousWasteCategory = resultset.getInt("hazardousWasteCategory");

	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getHazardousWasteCategory() {
		return hazardousWasteCategory;
	}

	public void setHazardousWasteCategory(int i) {
		hazardousWasteCategory = i;
	}

	public void setHazardousTotalWaste(String data) {
		hazardousTotalWaste = data;
	}

	public String getHazardousTotalWaste() {
		return hazardousTotalWaste;
	}

	public void setManifastno(String data) {
		manifastno = data;
	}

	public String getManifastno() {
		return manifastno;
	}

	public void setDateOftheManifest(String data) {
		dateOftheManifest = data;
	}

	public String getDateOftheManifest() {
		return dateOftheManifest;
	}

	public void setEpaId(String data) {
		epaId = data;
	}

	public String getEpaId() {
		return epaId;
	}

	public void setWaste(String data) {
		waste = data;
	}

	public String getWaste() {
		return waste;
	}

	public void setWaste1(String data) {
		waste1 = data;
	}

	public String getWaste1() {
		return waste1;
	}

	public void setWaste2(String data) {
		waste2 = data;
	}

	public String getWaste2() {
		return waste2;
	}

	public void setWaste3(String data) {
		waste3 = data;
	}

	public String getWaste3() {
		return waste3;
	}

	public void setWaste4(String data) {
		waste4 = data;
	}

	public String getWaste4() {
		return waste4;
	}

	public void setTypeOfWaste(int i) {
		typeOfWaste = i;
	}

	public int getTypeOfWaste() {
		return typeOfWaste;
	}

	public void setTypeOfWaste1(int i) {
		typeOfWaste1 = i;
	}

	public int getTypeOfWaste1() {
		return typeOfWaste1;
	}

	public void setTypeOfWaste2(int i) {
		typeOfWaste2 = i;
	}

	public int getTypeOfWaste2() {
		return typeOfWaste2;
	}

	public void setTypeOfWaste3(int i) {
		typeOfWaste3 = i;
	}

	public int getTypeOfWaste3() {
		return typeOfWaste3;
	}

	public void setTypeOfWaste4(int i) {
		typeOfWaste4 = i;
	}

	public int getTypeOfWaste4() {
		return typeOfWaste4;
	}

	public void setWasteName(String data) {
		wasteName = data;
	}

	public String getWasteName() {
		return wasteName;
	}

	public void setWasteName1(String data) {
		wasteName1 = data;
	}

	public String getWasteName1() {
		return wasteName1;
	}

	public void setWasteName2(String data) {
		wasteName2 = data;
	}

	public String getWasteName2() {
		return wasteName2;
	}

	public void setWasteName3(String data) {
		wasteName3 = data;
	}

	public String getWasteName3() {
		return wasteName3;
	}

	public void setWasteName4(String data) {
		wasteName4 = data;
	}

	public String getWasteName4() {
		return wasteName4;
	}

	public void setWasteVolume(String data) {
		wasteVolume = data;
	}

	public String getWasteVolume() {
		return wasteVolume;
	}

	public void setWasteVolume1(String data) {
		wasteVolume1 = data;
	}

	public String getWasteVolume1() {
		return wasteVolume1;
	}

	public void setWasteVolume2(String data) {
		wasteVolume2 = data;
	}

	public String getWasteVolume2() {
		return wasteVolume2;
	}

	public void setWasteVolume3(String data) {
		wasteVolume3 = data;
	}

	public String getWasteVolume3() {
		return wasteVolume3;
	}

	public void setWasteVolume4(String data) {
		wasteVolume4 = data;
	}

	public String getWasteVolume4() {
		return wasteVolume4;
	}

	public void setWasteDensity(String data) {
		wasteDensity = data;
	}

	public String getWasteDensity() {
		return wasteDensity;
	}

	public void setWasteDensity1(String data) {
		wasteDensity1 = data;
	}

	public String getWasteDensity1() {
		return wasteDensity1;
	}

	public void setWasteDensity2(String data) {
		wasteDensity2 = data;
	}

	public String getWasteDensity2() {
		return wasteDensity2;
	}

	public void setWasteDensity3(String data) {
		wasteDensity3 = data;
	}

	public String getWasteDensity3() {
		return wasteDensity3;
	}

	public void setWasteDensity4(String data) {
		wasteDensity4 = data;
	}

	public String getWasteDensity4() {
		return wasteDensity4;
	}

	public void setWasteUnit(String data) {
		wasteUnit = data;
	}

	public String getWasteUnit() {
		return wasteUnit;
	}

	public void setWasteUnit1(String data) {
		wasteUnit1 = data;
	}

	public String getWasteUnit1() {
		return wasteUnit1;
	}

	public void setWasteUnit2(String data) {
		wasteUnit2 = data;
	}

	public String getWasteUnit2() {
		return wasteUnit2;
	}

	public void setWasteUnit3(String data) {
		wasteUnit3 = data;
	}

	public String getWasteUnit3() {
		return wasteUnit3;
	}

	public void setWasteUnit4(String data) {
		wasteUnit4 = data;
	}

	public String getWasteUnit4() {
		return wasteUnit4;
	}

	public void setWasteQuantity(String data) {
		wasteQuantity = data;
	}

	public String getWasteQuantity() {
		return wasteQuantity;
	}

	public void setWasteQuantity1(String data) {
		wasteQuantity1 = data;
	}

	public String getWasteQuantity1() {
		return wasteQuantity1;
	}

	public void setWasteQuantity2(String data) {
		wasteQuantity2 = data;
	}

	public String getWasteQuantity2() {
		return wasteQuantity2;
	}

	public void setWasteQuantity3(String data) {
		wasteQuantity3 = data;
	}

	public String getWasteQuantity3() {
		return wasteQuantity3;
	}

	public void setWasteQuantity4(String data) {
		wasteQuantity4 = data;
	}

	public String getWasteQuantity4() {
		return wasteQuantity4;
	}

	public void setDivisioncontactname(String data) {
		divisioncontactname = data;
	}

	public String getDivisioncontactname() {
		return divisioncontactname;
	}

	public void setTelephonenumber(String data) {
		telephonenumber = data;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setEpaidWaste(String data) {
		epaidWaste = data;
	}

	public String getEpaidWaste() {
		return epaidWaste;
	}

	public void setCompanyName(String data) {
		companyName = data;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setEpaidFinaldes(String data) {
		epaidFinaldes = data;
	}

	public String getEpaidFinaldes() {
		return epaidFinaldes;
	}

	protected int id;
	protected String manifastno;
	protected String dateOftheManifest;
	protected String waste;
	protected String waste1;
	protected String waste2;
	protected String waste3;
	protected String waste4;
	protected int typeOfWaste;
	protected String wasteName;
	protected String wasteQuantity;
	protected int typeOfWaste1;
	protected String wasteName1;
	protected String wasteQuantity1;
	protected int typeOfWaste2;
	protected String wasteName2;
	protected String wasteQuantity2;
	protected int typeOfWaste3;
	protected String wasteName3;
	protected String wasteQuantity3;
	protected int typeOfWaste4;
	protected String wasteName4;
	protected String wasteVolume;
	protected String wasteVolume1;
	protected String wasteVolume2;
	protected String wasteVolume3;
	protected String wasteVolume4;
	protected String wasteDensity;
	protected String wasteDensity1;
	protected String wasteDensity2;
	protected String wasteDensity3;
	protected String wasteDensity4;
	protected String wasteUnit;
	protected String wasteUnit1;
	protected String wasteUnit2;
	protected String wasteUnit3;
	protected String wasteUnit4;
	protected String wasteQuantity4;
	protected String divisioncontactname;
	protected String telephonenumber;
	protected String epaidWaste;
	protected String companyName;
	protected String epaId;
	protected String hazardousTotalWaste;
	protected int hazardousWasteCategory;
	protected String epaidFinaldes;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.HwasteVo.class);

}