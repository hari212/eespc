// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 2:27:19 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   BuildingVo.java

package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.AddressEntity;
import com.eespc.tracking.entity.BuildingEntity;

// Referenced classes of package com.eespc.tracking.bo:
//            AddressVo

public class BuildingVo {

	public BuildingVo() {
		log = LogFactory.getLog(com.eespc.tracking.bo.BuildingVo.class);
		id = -99;
		buildingName = null;
		buildingId = null;
		dobBinNumber = null;
		blockNumber = null;
		lotNumber = null;
		bldgcomment = null;
		bldgfootnote = null;
		isBldgOver65Stories = false;
		inspectionInfo = null;
		resourcesInBldg = null;
		buildingAddressId = -99;
		buildingAddress = null;
		borough = -99;
		cityname = null;
		cofObtaining = null;
		cofNo = null;
	}

	public BuildingVo(ResultSet resultset) throws SQLException {
		log = LogFactory.getLog(com.eespc.tracking.bo.BuildingVo.class);
		id = -99;
		buildingName = null;
		buildingId = null;
		dobBinNumber = null;
		blockNumber = null;
		lotNumber = null;
		bldgcomment = null;
		bldgfootnote = null;
		isBldgOver65Stories = false;
		inspectionInfo = null;
		resourcesInBldg = null;
		buildingAddressId = -99;
		buildingAddress = null;
		cityname = null;
		cofObtaining = null;
		cofNo = null;

		if (resultset != null) {
			boolean flag = false;
			String s = "";
			id = resultset.getInt("buildingid");
			buildingName = resultset.getString("buildingname");
			buildingId = resultset.getString("buildingrefid");
			dobBinNumber = resultset.getString("dobBinNumber");
			blockNumber = resultset.getString("blocknumber");
			lotNumber = resultset.getString("lotnumber");
			bldgcomment = resultset.getString("COMMENTS");
			bldgfootnote = resultset.getString("FOOTNOTE");
			bldtall = resultset.getString("bldgsixstories");
			buildingAddressId = resultset.getInt("addressid");
			s = resultset.getString("BOROUGH");
			if (s != null && s.trim().length() > 0)
				borough = Integer.parseInt(s);
			cityname = resultset.getString("cityname");
			cofObtaining = resultset.getString("cofobtaining");
			cofNo = resultset.getString("cofno");
		}
	}

	public String getBlockNumber() {
		return blockNumber;
	}

	public String getDobBinNumber() {
		return dobBinNumber;
	}

	public String getBldgfootnote() {
		return bldgfootnote;
	}

	public String getBldgcomment() {
		return bldgcomment;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public int getId() {
		return id;
	}

	public List getInspectionInfo() {
		if (inspectionInfo == null)
			inspectionInfo = BuildingEntity.getInspectionReportObject(getId());
		return inspectionInfo;
	}

	public boolean isBldgOver65Stories() {
		return isBldgOver65Stories;
	}

	public String getbldtall() {
		return bldtall;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public String getCityname() {
		return cityname;
	}

	public void setCityname(String s) {
		cityname = s;
	}

	public String getCofObtaining() {
		return cofObtaining;
	}

	public void setCofObtaining(String s) {
		cofObtaining = s;
	}

	public String getCofNo() {
		return cofNo;
	}

	public void setCofNo(String s) {
		cofNo = s;
	}

	public int getBorough() {
		return borough;
	}

	public void setBorough(int i) {
		borough = i;
	}

	public List getResourcesInBldg() {
		if (resourcesInBldg == null)
			resourcesInBldg = BuildingEntity.getResources(getId());
		return resourcesInBldg;
	}

	public void setBlockNumber(String s) {
		blockNumber = s;
	}

	public void setDobBinNumber(String s) {
		dobBinNumber = s;
	}

	public void setBldgfootnote(String s) {
		bldgfootnote = s;
	}

	public void setBldgcomment(String s) {
		bldgcomment = s;
	}

	public void setBuildingId(String s) {
		buildingId = s;
	}

	public void setBuildingName(String s) {
		buildingName = s;
	}

	public void setId(int i) {
		id = i;
	}

	public void setInspectionInfo(List list) {
		inspectionInfo = list;
	}

	public void setBldgOver65Stories(boolean flag) {
		isBldgOver65Stories = flag;
	}

	public void setbldtall(String s) {
		bldtall = s;
	}

	public void setLotNumber(String s) {
		lotNumber = s;
	}

	public void setResourcesInBldg(List list) {
		resourcesInBldg = list;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id).append(" ");
		stringbuffer.append("buildingName =").append(buildingName).append(" ");
		stringbuffer.append("buildingId =").append(buildingId).append(" ");
		stringbuffer.append("dobBinNumber =").append(dobBinNumber).append(" ");
		stringbuffer.append("blockNumber =").append(blockNumber).append(" ");
		stringbuffer.append("lotNumber =").append(lotNumber).append(" ");
		stringbuffer.append("isBldgOver65Stories =")
				.append(isBldgOver65Stories).append(" ");
		return stringbuffer.toString();
	}

	public AddressVo getBuildingAddress() {
		if (buildingAddress == null && buildingAddressId > 0)
			try {
				buildingAddress = AddressEntity
						.getAddressById(buildingAddressId);
			} catch (Exception exception) {
				log.error(exception);
			}
		return buildingAddress;
	}

	public void setBuildingAddress(AddressVo addressvo) {
		buildingAddress = addressvo;
	}

	private Log log;
	protected int id;
	protected String buildingName;
	protected String buildingId;
	protected String dobBinNumber;
	protected String blockNumber;
	protected String lotNumber;
	protected String bldtall;
	protected String bldgcomment;
	protected String bldgfootnote;
	protected boolean isBldgOver65Stories;
	protected List inspectionInfo;
	protected List resourcesInBldg;
	protected int buildingAddressId;
	protected AddressVo buildingAddress;
	protected int borough;
	protected String cityname;
	protected String cofObtaining;
	protected String cofNo;

}