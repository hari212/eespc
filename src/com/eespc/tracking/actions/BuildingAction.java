package com.eespc.tracking.actions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingInspectionReportVo;
import com.eespc.tracking.bo.BuildingManager;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.myenum.BoroughsEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.StateEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.AddressEntity;
import com.eespc.tracking.entity.BuildingEntity;
import com.eespc.tracking.entity.ViolationEntity;
import com.eespc.tracking.exceptions.BuildingException;
import com.eespc.tracking.util.UtilityObject;

public class BuildingAction extends Action {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.BuildingAction.class);

	public BuildingAction() {

	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("methodToCall");
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		log.debug((new StringBuilder()).append("Building=")
				.append(dynaactionform.toString()).toString());
		log.debug((new StringBuilder()).append("Mapping=")
				.append(actionmapping.toString()).toString());

		int b_count = BuildingEntity.getBuildingsCount(facilityvo.getId());
		if ((Integer.parseInt(checkNullAndFill(facilityvo.getNumofbldg(), "0")) <= b_count)
				&& (s.equalsIgnoreCase("initial") || s.equalsIgnoreCase("ADD"))) {

			httpservletrequest.setAttribute("MESSAGE", "Error in Building");
			httpservletrequest
					.setAttribute("MESSAGE_TYPE",
							"You Need to Get Permission from EES to add more buildings");
			setDropDown(httpservletrequest);
			dynaactionform.set("bldgName", "");
			dynaactionform.set("bldgId", "");
			dynaactionform.set("dobBin", "");
			dynaactionform.set("lot", "");
			dynaactionform.set("block", "");
			dynaactionform.set("address", "");
			dynaactionform.set("bldgfootnote", "");
			dynaactionform.set("bldgcomment", "");
			dynaactionform.set("bldgTall", "");
			dynaactionform.set("cityname", "");
			dynaactionform.set("cofobtaining", "");
			dynaactionform.set("cofno", "");

			dynaactionform.set("methodToCall", "initial");

			httpservletrequest.removeAttribute("isComponentEditable");
			httpservletrequest.setAttribute("showAddBtn", "N");
			log.debug((new StringBuilder())
					.append("BuildingAction - In Initial. methodToCall=")
					.append(s).toString());

			return actionmapping.findForward("success");

		} else {

			if (s != null && s.equalsIgnoreCase("ADD")) {
				return add(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			}

			if (s != null && s.equalsIgnoreCase("UPDATE")) {
				return update(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			}

			if (s != null && s.equalsIgnoreCase("VIEW")) {
				return view(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			}

			if (s != null && s.equalsIgnoreCase("EDIT")) {
				return edit(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			}

			if (s != null && s.equalsIgnoreCase("delete")) {
				return delete(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			}
			if (s != null && s.equalsIgnoreCase("search")) {
				return search(actionmapping, actionform, httpservletrequest,
						httpservletresponse);
			}
			if (s != null && s.equalsIgnoreCase("addBuildingInspection")) {
				return addBuildingInspection(actionmapping, actionform,
						httpservletrequest, httpservletresponse);
			}
			if (s != null && s.equalsIgnoreCase("editBuildingInspection")) {
				return editBuildingInspection(actionmapping, actionform,
						httpservletrequest, httpservletresponse);
			}
			if (s != null && s.equalsIgnoreCase("deleteBuildingInspection")) {
				return deleteBuildingInspection(actionmapping, actionform,
						httpservletrequest, httpservletresponse);
			}
			if (s != null && s.equalsIgnoreCase("updateBuildingInspection")) {
				return updateBuildingInspection(actionmapping, actionform,
						httpservletrequest, httpservletresponse);
			}

			/*
			 * if(s != null && s.equalsIgnoreCase("addnewfolder")) { return
			 * addnewfolder(actionmapping, actionform, httpservletrequest,
			 * httpservletresponse); }
			 * 
			 * if(s != null && s.equalsIgnoreCase("deletefile")) { return
			 * deletefile(actionmapping, actionform, httpservletrequest,
			 * httpservletresponse); }
			 * 
			 * if(s != null && s.equalsIgnoreCase("editfile")) { return
			 * editfile(actionmapping, actionform, httpservletrequest,
			 * httpservletresponse); }
			 * 
			 * if(s != null && s.equalsIgnoreCase("viewfile")) { return
			 * viewfile(actionmapping, actionform, httpservletrequest,
			 * httpservletresponse); } if(s != null &&
			 * s.equalsIgnoreCase("fileupload")) { return
			 * fileupload(actionmapping, actionform, httpservletrequest,
			 * httpservletresponse); }
			 * 
			 * if(s != null && s.equalsIgnoreCase("back")) { return
			 * back(actionmapping, actionform, httpservletrequest,
			 * httpservletresponse); }
			 */

			else {
				setDropDown(httpservletrequest);
				dynaactionform.set("bldgName", "");
				dynaactionform.set("bldgId", "");
				dynaactionform.set("dobBin", "");
				dynaactionform.set("lot", "");
				dynaactionform.set("block", "");
				dynaactionform.set("address", "");
				dynaactionform.set("bldgfootnote", "");
				dynaactionform.set("bldgcomment", "");
				dynaactionform.set("bldgTall", "");
				dynaactionform.set("cityname", "");
				dynaactionform.set("cofobtaining", "");
				dynaactionform.set("cofno", "");

				httpservletrequest.removeAttribute("isComponentEditable");
				httpservletrequest.setAttribute("showAddBtn", "N");
				log.debug((new StringBuilder())
						.append("BuildingAction - In Initial. methodToCall=")
						.append(s).toString());
				return actionmapping.findForward("success");
			}
		}
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return s;
		} else {
			return s1;
		}
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public void setformvariable(String path, ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		String s = (String) dynaactionform.get("methodToCall");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + path);
			if (f.exists() == false) {
				f.mkdirs();
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		ArrayList folderlist = new ArrayList();
		try {

			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + path + "/";
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {
					System.out.println("1a");
					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("1b");
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
				}
				folderlist.add(arr);
				System.out.println("2");

			}

		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", path);
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String returnType = "success";
		log.debug("BuildingAction - In Add");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = "";
		String s1 = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		BuildingManager buildingmanager = new BuildingManager(facilityvo,
				new BuildingVo(), dynaactionform);

		try {
			int i = buildingmanager.addBuilding();
			BuildingVo buildingvo = buildingmanager.getBuilding(i);
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo);
			httpservletrequest.setAttribute("isComponentEditable", "N");
			s = "Added Building.";
			s1 = "CONFIRMATION";
		} catch (BuildingException buildingexception) {
			s = buildingexception.getMessage();
			s1 = "ERROR";
			if (log.isErrorEnabled()) {
				log.error(buildingexception);
			}
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deleteBuildingInspection(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BuildingAction - In updateBuildingInspection");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s)) {
			i = Integer.parseInt(s);
		} else {
			throw new Exception(
					"Building Inspection Id is null while updating the Building Inspection info");
		}
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		BuildingManager buildingmanager = new BuildingManager(facilityvo,
				buildingvo, dynaactionform);
		BuildingInspectionReportVo buildinginspectionreportvo = buildingmanager
				.findReportByPrimaryKey(i);
		buildingmanager.deleteBuildingInspection(buildinginspectionreportvo);
		BuildingVo buildingvo1 = buildingmanager
				.getBuilding(buildingvo.getId());
		if (buildingvo1 != null) {
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo1);
		}

		dynaactionform.set("dobPermitObtained", "");
		dynaactionform.set("facadeInspected", "");
		dynaactionform.set("facRepairRequired", "");
		dynaactionform.set("nextInspDate", "");
		dynaactionform.set("actualInspDate", "");
		dynaactionform.set("repairedYear", "");
		dynaactionform.set("submitedBy", "");
		dynaactionform.set("jobNumberRepairWork", "");
		dynaactionform.set("facadePeriod", "");
		dynaactionform.set("cycle", "");

		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BuildingAction - In Update");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("bId");
		int i = -99;
		if (s != null && s.trim().length() > 0) {
			i = Integer.parseInt(s);
			BuildingManager buildingmanager = new BuildingManager();
			BuildingVo buildingvo1 = buildingmanager.getBuilding(i);
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo1);
		}
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		i = buildingvo.getId();
		AddressEntity.delete(buildingvo);
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		return actionmapping.findForward("successbldg");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String fcity = "";
		String fstate = "";
		String fzip = "";
		log.debug("BuildingAction - In Update");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		AddressVo addressvo1 = facilityvo.getFacilityAddress();
		if (addressvo1 != null) {
			fcity = UtilityObject.checkNull(addressvo1.getCity());
			fstate = UtilityObject.checkNull(addressvo1.getState());
			fzip = UtilityObject.checkNull(addressvo1.getZipCode());
		}
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		AddressVo addressvo = buildingvo.getBuildingAddress();
		buildingvo.setBuildingName((String) dynaactionform.get("bldgName"));
		buildingvo.setBuildingId((String) dynaactionform.get("bldgId"));
		buildingvo.setDobBinNumber((String) dynaactionform.get("dobBin"));
		buildingvo.setLotNumber((String) dynaactionform.get("lot"));
		buildingvo.setBlockNumber((String) dynaactionform.get("block"));
		buildingvo.setbldtall((String) dynaactionform.get("bldgTall"));
		buildingvo.setBldgfootnote((String) dynaactionform.get("bldgfootnote"));
		buildingvo.setBldgcomment((String) dynaactionform.get("bldgcomment"));
		buildingvo.setCityname((String) dynaactionform.get("cityname"));
		buildingvo.setCofObtaining((String) dynaactionform.get("cofobtaining"));
		buildingvo.setCofNo((String) dynaactionform.get("cofno"));

		String s2 = (String) dynaactionform.get("borough");

		if (s2 != null && s2.trim().length() > 0)
			buildingvo.setBorough(Integer.parseInt(s2));
		BuildingManager buildingmanager = new BuildingManager();
		buildingmanager.updateBuilding(buildingvo);
		addressvo.setAddress1((String) dynaactionform.get("address"));
		addressvo.setCity(fcity);
		addressvo.setState(fstate);
		addressvo.setZipCode(fzip);
		AddressEntity.update(addressvo);
		BuildingVo buildingvo1 = buildingmanager
				.getBuilding(buildingvo.getId());
		httpsession.setAttribute("BUILDING_OBJECT", buildingvo1);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BuildingAction - In View");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("bId");
		int i = -99;
		if (s != null && s.trim().length() > 0) {
			i = Integer.parseInt(s);
		}
		if (i < 0) {
			BuildingVo buildingvo = (BuildingVo) httpsession
					.getAttribute("BUILDING_OBJECT");
			i = buildingvo.getId();
			 
		}
		BuildingManager buildingmanager = new BuildingManager();
		BuildingVo buildingvo1 = buildingmanager.getBuilding(i);
		if (buildingvo1 != null) {
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo1);
		}
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward search(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String returnType = "success";
		log.debug("BuildingAction - In Search");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = "";
		String s1 = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		BuildingManager buildingmanager = new BuildingManager(facilityvo,
				dynaactionform);
		List bList = buildingmanager.getBuildingsInFacility(facilityvo.getId());
		List resList = new ArrayList();
		String srch = httpservletrequest.getParameter("srch");
		if (srch == null)
			srch = "";
		for (int i = 0; i < bList.size(); i++) {
			resList.addAll(buildingmanager.getResources(
					((BuildingVo) bList.get(i)).getId(), srch));
		}
		if (bList.size() == 0) {
			httpsession.setAttribute("MESSAGE", "NO RESOURCE");
		} else {
			httpsession.setAttribute("ALL_RESOURSE_LIST", resList);
		}
		setFormInfo(true, actionform, httpservletrequest);
		return actionmapping.findForward("srchsuccess");

	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BuildingAction - In Edit");
		httpservletrequest.removeAttribute("isComponentEditable");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("bId");
		int i = -99;
		if (s != null && s.trim().length() > 0) {
			i = Integer.parseInt(s);
		}
		BuildingManager buildingmanager = new BuildingManager();
		BuildingVo buildingvo = buildingmanager.getBuilding(i);
		if (buildingvo != null) {
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo);
		}
		httpservletrequest.setAttribute("isItForEdit", "Y");
		setDropDown(httpservletrequest);
		setFormInfo(false, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward addBuildingInspection(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		BuildingManager buildingmanager = new BuildingManager(facilityvo,
				buildingvo, dynaactionform);
		buildingmanager.addBuildingInspection();
		dynaactionform.set("dobPermitObtained", "");
		dynaactionform.set("facadeInspected", "");
		dynaactionform.set("facRepairRequired", "");
		dynaactionform.set("nextInspDate", "");
		dynaactionform.set("actualInspDate", "");
		dynaactionform.set("repairedYear", "");
		dynaactionform.set("submitedBy", "");
		dynaactionform.set("jobNumberRepairWork", "");
		dynaactionform.set("facadePeriod", "");
		dynaactionform.set("cycle", "");
		dynaactionform.set("filingDateNotes", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		log.debug("BuildingAction - In addBuildingInspection");
		BuildingVo buildingvo1 = buildingmanager
				.getBuilding(buildingvo.getId());
		if (buildingvo1 != null) {
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo1);
		}
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editBuildingInspection(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BuildingAction - In editBuildingInspection");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_BUILDING_INSPECTION", "Y");
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward updateBuildingInspection(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BuildingAction - In updateBuildingInspection");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s)) {
			i = Integer.parseInt(s);
		} else {
			throw new Exception(
					"Building Inspection Id is null while updating the Building Inspection info");
		}
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		BuildingManager buildingmanager = new BuildingManager(facilityvo,
				buildingvo, dynaactionform);
		BuildingInspectionReportVo buildinginspectionreportvo = buildingmanager
				.findReportByPrimaryKey(i);

		String s1 = (String) dynaactionform.get("dobPermitObtained");
		buildinginspectionreportvo.setDobPermitObtained(UtilityObject
				.convertBoolean(s1));

		String sde = (String) dynaactionform.get("facadeInspected");
		buildinginspectionreportvo.setFacadeDobInspected(sde);

		/*
		 * s1 = (String)dynaactionform.get("facadeInspected");
		 * buildinginspectionreportvo
		 * .setFacadeDobInspected(UtilityObject.convertBoolean(s1));
		 */
		s1 = (String) dynaactionform.get("facRepairRequired");
		buildinginspectionreportvo.setFacadeRepaired(UtilityObject
				.convertBoolean(s1));
		buildinginspectionreportvo
				.setNextInspectionDate((String) dynaactionform
						.get("nextInspDate"));
		buildinginspectionreportvo
				.setActualInspectionDate((String) dynaactionform
						.get("actualInspDate"));
		buildinginspectionreportvo.setRepairedYear((String) dynaactionform
				.get("repairedYear"));
		buildinginspectionreportvo.setSubmitedBy((String) dynaactionform
				.get("submitedBy"));
		buildinginspectionreportvo
				.setJobNumberRepairWork((String) dynaactionform
						.get("jobNumberRepairWork"));
		buildinginspectionreportvo.setFacadePeriod((String) dynaactionform
				.get("facadePeriod"));
		buildinginspectionreportvo.setCycle((String) dynaactionform
				.get("cycle"));
		buildinginspectionreportvo.setFilingDateNotes((String) dynaactionform
				.get("filingDateNotes"));

		buildingmanager.updateBuildingInspection(buildinginspectionreportvo);
		BuildingVo buildingvo1 = buildingmanager
				.getBuilding(buildingvo.getId());
		if (buildingvo1 != null) {
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo1);
		}

		dynaactionform.set("dobPermitObtained", "");
		dynaactionform.set("facadeInspected", "");
		dynaactionform.set("facRepairRequired", "");
		dynaactionform.set("nextInspDate", "");
		dynaactionform.set("actualInspDate", "");
		dynaactionform.set("repairedYear", "");
		dynaactionform.set("submitedBy", "");
		dynaactionform.set("jobNumberRepairWork", "");
		dynaactionform.set("facadePeriod", "");
		dynaactionform.set("cycle", "");
		dynaactionform.set("filingDateNotes", "");

		httpservletrequest.setAttribute("isComponentEditable", "N");
		setDropDown(httpservletrequest);
		setFormInfo(true, actionform, httpservletrequest);
		reset(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		try {
			com.eespc.tracking.bo.DropDown dropdown = StateEnum
					.getDropDownObj();
			com.eespc.tracking.bo.DropDown dropdown1 = YearEnum
					.getDropDownObj();
			com.eespc.tracking.bo.DropDown dropdown2 = ResourcesTypeEnum
					.getDropDownObj(httpservletrequest);
			com.eespc.tracking.bo.DropDown dropdown3 = BoroughsEnum
					.getDropDownObj();
			httpservletrequest.setAttribute("YEARS", dropdown1);
			httpservletrequest.setAttribute("STATES", dropdown);
			httpservletrequest.setAttribute("RESOURCES_TYPE_DROP", dropdown2);
			httpservletrequest.setAttribute("BOROUGHS", dropdown3);

		} catch (Exception exception) {
		}
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/building/"
					+ buildingvo.getId() + "-"
					+ buildingvo.getBuildingName().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/building/"
					+ buildingvo.getId() + "-"
					+ buildingvo.getBuildingName().trim();
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {

					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {

					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
				}
				folderlist.add(arr);
				System.out.println("2");
			}
			httpservletrequest.setAttribute("FILE_LIST", folderlist);
			httpservletrequest.setAttribute("FILE_PATH", facilityvo.getDecId()
					+ "/building/" + buildingvo.getId() + "-"
					+ buildingvo.getBuildingName().trim());

		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}

	}

	private void setFormInfo(boolean flag, ActionForm actionform,
			HttpServletRequest httpservletrequest) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		if (buildingvo != null) {
			httpservletrequest.setAttribute("showAddBtn", "Y");
			dynaactionform.set("id",
					(new StringBuilder()).append(buildingvo.getId()).append("")
							.toString());
			dynaactionform.set("bldgName", buildingvo.getBuildingName());
			dynaactionform.set("bldgId", buildingvo.getBuildingId());
			dynaactionform.set("dobBin", buildingvo.getDobBinNumber());
			dynaactionform.set("lot", buildingvo.getLotNumber());
			dynaactionform.set("block", buildingvo.getBlockNumber());
			dynaactionform.set("bldgTall", buildingvo.getbldtall());
			dynaactionform.set("bldgfootnote", buildingvo.getBldgfootnote());
			dynaactionform.set("bldgcomment", buildingvo.getBldgcomment());

			dynaactionform.set("cityname", buildingvo.getCityname());
			dynaactionform.set("cofobtaining", buildingvo.getCofObtaining());
			dynaactionform.set("cofno", buildingvo.getCofNo());

			int j = buildingvo.getBorough();
			if (flag) {
				BoroughsEnum boroughsenum = BoroughsEnum.get(j);
				dynaactionform.set("borough", boroughsenum == null ? ""
						: ((Object) (boroughsenum.getName())));
			} else {
				dynaactionform.set("borough", (new StringBuilder()).append(j)
						.append("").toString());
			}
			AddressVo addressvo = buildingvo.getBuildingAddress();
			if (addressvo != null) {
				dynaactionform.set("address", addressvo.getAddress1());
				// dynaactionform.set("city", addressvo.getCity());
				// dynaactionform.set("state", addressvo.getState());
				// dynaactionform.set("zipCode", addressvo.getZipCode());
			}
			java.util.List list = buildingvo.getResourcesInBldg();
			if (list != null) {
				httpsession.setAttribute("RESOURCE_LIST", list);
			}
			java.util.List list1 = buildingvo.getInspectionInfo();
			if (list1 != null) {
				httpsession.setAttribute("INSPECTION_LIST", list1);
			}
			java.util.List list2 = buildingvo.getResourcesInBldg();
			if (list2 != null) {
				httpsession.setAttribute("RESOURCE_LIST", list2);
			}

			ViolationEntity violationentity = new ViolationEntity();

			java.util.List dobvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 1);
			httpservletrequest.setAttribute("VIOLATION_DOB_LIST", dobvoilation);

			java.util.List depvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 2);
			httpservletrequest.setAttribute("VIOLATION_DEP_LIST", depvoilation);

			java.util.List ecbvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 3);
			httpservletrequest.setAttribute("VIOLATION_ECB_LIST", ecbvoilation);

			java.util.List decvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 4);
			httpservletrequest.setAttribute("VIOLATION_DEC_LIST", decvoilation);

			java.util.List fdvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 5);
			httpservletrequest.setAttribute("VIOLATION_FD_LIST", fdvoilation);

			java.util.List epavoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 6);
			httpservletrequest.setAttribute("VIOLATION_EPA_LIST", epavoilation);

			java.util.List cityvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 7);
			httpservletrequest.setAttribute("VIOLATION_CITY_LIST",
					cityvoilation);

			java.util.List othvoilation = violationentity
					.getViolationInBuilding(buildingvo.getId(), 8);
			httpservletrequest.setAttribute("VIOLATION_OTHER_LIST",
					othvoilation);

		} else {
			httpservletrequest.setAttribute("showAddBtn", "N");
		}
	}

}
