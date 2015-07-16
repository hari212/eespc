// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BuildingManager.java

package com.eespc.tracking.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.DynaActionForm;

import com.eespc.tracking.entity.AddressEntity;
import com.eespc.tracking.entity.BuildingEntity;
import com.eespc.tracking.exceptions.AddressException;
import com.eespc.tracking.exceptions.BuildingException;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.bo:
//            AddressVo, BuildingVo, BuildingInspectionReportVo, FacilityVo

public class BuildingManager {

	public BuildingManager() {
		fcity = "";
		fstate = "";
		fzip = "";
		facilityVo = null;
		buildingVo = null;
		formObj = null;
	}

	public BuildingManager(FacilityVo facilityvo, DynaActionForm dynaactionform) {
		fcity = "";
		fstate = "";
		fzip = "";
		AddressVo addressvo = facilityvo.getFacilityAddress();
		if (addressvo != null) {
			fcity = UtilityObject.checkNull(addressvo.getCity());
			fstate = UtilityObject.checkNull(addressvo.getState());
			fzip = UtilityObject.checkNull(addressvo.getZipCode());
		}
		if (facilityvo == null || dynaactionform == null) {
			throw new IllegalArgumentException("Mandatory object not passed.");
		} else {
			facilityVo = facilityvo;
			formObj = dynaactionform;
			return;
		}
	}

	public BuildingManager(FacilityVo facilityvo, BuildingVo buildingvo,
			DynaActionForm dynaactionform) {
		fcity = "";
		fstate = "";
		fzip = "";
		AddressVo addressvo = facilityvo.getFacilityAddress();
		if (addressvo != null) {
			fcity = UtilityObject.checkNull(addressvo.getCity());
			fstate = UtilityObject.checkNull(addressvo.getState());
			fzip = UtilityObject.checkNull(addressvo.getZipCode());
		}
		facilityVo = null;
		buildingVo = null;
		formObj = null;
		if (facilityvo == null || buildingvo == null || dynaactionform == null) {
			throw new IllegalArgumentException("Mandatory object not passed.");
		} else {
			facilityVo = facilityvo;
			buildingVo = buildingvo;
			formObj = dynaactionform;
			return;
		}
	}

	public void deleteBuildingInspection(
			BuildingInspectionReportVo buildinginspectionreportvo)
			throws BuildingException {
		BuildingEntity.deleteBuildingInspectionInfo(buildinginspectionreportvo);
	}

	public List getBuildingsInFacility(int i) {
		Object obj = new ArrayList();
		obj = BuildingEntity.getBuildingsInFacility(i);
		return (List) (List) obj;
	}

	public BuildingVo getBuilding(int i) {
		BuildingVo buildingvo = null;
		buildingvo = BuildingEntity.getBuilding(i);
		return buildingvo;
	}

	public List getInspectionReportObject(int i) {
		Object obj = new ArrayList();
		obj = BuildingEntity.getInspectionReportObject(i);
		return (List) (List) obj;
	}

	public List getResources(int i) {
		Object obj = new ArrayList();
		obj = BuildingEntity.getResources(i);
		return (List) (List) obj;
	}

	public List getResources(int i, String s) {
		Object obj = new ArrayList();
		obj = BuildingEntity.getResources(i, s);
		return (List) (List) obj;
	}

	public int addBuilding() throws BuildingException {
		AddressVo addressvo = new AddressVo();
		addressvo.setAddress1((String) formObj.get("address"));
		addressvo.setCity(fcity);
		addressvo.setState(fstate);
		addressvo.setZipCode(fzip);
		BuildingVo buildingvo = new BuildingVo();
		buildingvo.setBuildingName((String) formObj.get("bldgName"));
		buildingvo.setBuildingId((String) formObj.get("bldgId"));
		buildingvo.setDobBinNumber((String) formObj.get("dobBin"));
		buildingvo.setLotNumber((String) formObj.get("lot"));
		buildingvo.setBlockNumber((String) formObj.get("block"));
		buildingvo.setbldtall((String) formObj.get("bldgTall"));
		buildingvo.setBldgfootnote((String) formObj.get("bldgfootnote"));
		buildingvo.setBldgcomment((String) formObj.get("bldgcomment"));
		buildingvo.setCityname((String) formObj.get("cityname"));
		buildingvo.setCofObtaining((String) formObj.get("cofobtaining"));
		buildingvo.setCofNo((String) formObj.get("cofno"));
		String s = (String) formObj.get("borough");
		if (s != null && s.trim().length() > 0)
			buildingvo.setBorough(Integer.parseInt(s));
		int i = -99;
		try {
			i = AddressEntity.addAddress(addressvo);
		} catch (AddressException addressexception) {
			System.out.println((new StringBuilder()).append("Exception:")
					.append(addressexception).toString());
			throw new BuildingException(addressexception);
		}
		int j = BuildingEntity.addBuilding(facilityVo.getId(), i, buildingvo);
		return j;
	}

	public void updateBuilding() throws BuildingException {
		BuildingEntity.updateBuilding(buildingVo);
	}

	public void updateBuilding(BuildingVo buildingvo) throws BuildingException {
		BuildingEntity.updateBuilding(buildingvo);
	}

	public void updateBuildingInspection(
			BuildingInspectionReportVo buildinginspectionreportvo)
			throws BuildingException {
		BuildingEntity.updateBuildingInspectionInfo(buildinginspectionreportvo);
	}

	public void addBuildingInspection() throws BuildingException {
		BuildingInspectionReportVo buildinginspectionreportvo = new BuildingInspectionReportVo();
		String s = (String) formObj.get("dobPermitObtained");
		buildinginspectionreportvo.setDobPermitObtained(UtilityObject
				.convertBoolean(s));
		s = (String) formObj.get("facRepairRequired");
		buildinginspectionreportvo.setFacadeRepaired(UtilityObject
				.convertBoolean(s));
		buildinginspectionreportvo.setFilingDateNotes((String) formObj
				.get("filingDateNotes"));
		buildinginspectionreportvo.setNextInspectionDate((String) formObj
				.get("nextInspDate"));
		buildinginspectionreportvo.setActualInspectionDate((String) formObj
				.get("actualInspDate"));
		buildinginspectionreportvo.setRepairedYear((String) formObj
				.get("repairedYear"));
		buildinginspectionreportvo.setSubmitedBy((String) formObj
				.get("submitedBy"));
		buildinginspectionreportvo.setJobNumberRepairWork((String) formObj
				.get("jobNumberRepairWork"));
		buildinginspectionreportvo.setFacadePeriod((String) formObj
				.get("facadePeriod"));
		buildinginspectionreportvo.setCycle((String) formObj.get("cycle"));
		buildinginspectionreportvo.setFacadeDobInspected((String) formObj
				.get("facadeInspected"));
		BuildingEntity.addBuildingInspRpt(buildingVo.getId(),
				buildinginspectionreportvo);
	}

	public BuildingInspectionReportVo findReportByPrimaryKey(int i)
			throws BuildingException {
		return BuildingEntity.findReportByPrimaryKey(i);
	}

	FacilityVo facilityVo;
	BuildingVo buildingVo;
	List buildingList;
	DynaActionForm formObj;
	String fcity;
	String fstate;
	String fzip;
}
