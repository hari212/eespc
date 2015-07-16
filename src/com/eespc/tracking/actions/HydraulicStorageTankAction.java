package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

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
import org.apache.struts.upload.FormFile;

import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.HydraulicElevatorAndOtherTankVo;
import com.eespc.tracking.bo.myenum.HydraulicStorageUsageEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.entity.HydraulicElevatorTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class HydraulicStorageTankAction extends Action {

	public HydraulicStorageTankAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		setDropDown(httpservletrequest);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("methodToCall");
		log.debug((new StringBuilder()).append("Hydraulic Tank ").append(s)
				.toString());
		if (s != null && s.equalsIgnoreCase("ADD"))
			return add(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("UPDATE"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("DELETE"))
			return delete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("VIEW"))
			return view(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("VIEWEXIST"))
			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("EDIT")) {
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		}
		if (s != null && s.equalsIgnoreCase("fileupload"))
			return fileupload(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("back"))
			return back(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addnewfolder"))
			return addnewfolder(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deletefile"))
			return deletefile(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editfile"))
			return editfile(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("viewfile"))
			return viewfile(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		else {
			log.debug((new StringBuilder())
					.append("HydraulicStorageTankAction userAction is ")
					.append(s).toString());
			dynaactionform.set("facilityDesignatedId", "");
			dynaactionform.set("capacity", "");
			dynaactionform.set("usage", "");
			dynaactionform.set("secondaryContain", "");
			dynaactionform.set("YearInstalled", "");
			dynaactionform.set("DisconnectedYear", "");
			dynaactionform.set("ModifiedInPast", "");
			dynaactionform.set("decapproval", "");
			dynaactionform.set("monthlyinspection", "");
			dynaactionform.set("spillkit", "");
			dynaactionform.set("pbsno", "");
			dynaactionform.set("hcomments", "");
			dynaactionform.set("dobsignoff", "");
			dynaactionform.set("dobnumber", "");

			log.debug("HydraulicStorageTankAction - In Execute");
			return actionmapping.findForward("success");
		}
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform,
				hydraulicelevatorandothertankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");

		String Pathname = httpservletrequest.getRealPath("/")
				+ "/clientfolder/" + path + "/" + foldername;

		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}

		try {
			File f = new File(Pathname);
			if (f.exists() == false) {
				f.mkdirs();
			} else {
				System.out.println("Error:Already Exist");
				httpservletrequest.setAttribute("MESSAGE", "Already Exist");
				httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward editfile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform,
				hydraulicelevatorandothertankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}
		String foldername = (String) dynaactionform.get("foldername");
		String refoldername = (String) dynaactionform.get("renamefoldername");
		System.out.println(" Old: " + foldername + " New: " + refoldername);
		String Pathname = httpservletrequest.getRealPath("/")
				+ "/clientfolder/" + path + "/" + foldername;
		String rePathname = httpservletrequest.getRealPath("/")
				+ "/clientfolder/" + path + "/" + refoldername;
		System.out.println(" OldPath: " + Pathname + " NewPath: " + rePathname);
		try {

			File f = new File(Pathname);
			if (f.isDirectory()) {
				File newFileName = new File(rePathname);
				if (newFileName.exists() == false) {
					System.out.println("Error:GOODDDDDDDDDDDDDD");
					f.renameTo(newFileName);
				} else {
					System.out.println("Error:Already Exist");
					httpservletrequest.setAttribute("MESSAGE", "Already Exist");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			} else {

				File newFileName = new File(rePathname);
				if (newFileName.exists() == false) {
					System.out.println("Error:GOODDDDDDDDDDDDDD");
					f.renameTo(newFileName);
				} else {
					System.out.println("Error:Already Exist");
					httpservletrequest.setAttribute("MESSAGE", "Already Exist");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward viewfile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform,
				hydraulicelevatorandothertankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");

		setformvariable((path + "/" + foldername), actionmapping, actionform,
				httpservletrequest, httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward deletefile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform,
				hydraulicelevatorandothertankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");

		String Pathname = httpservletrequest.getRealPath("/")
				+ "/clientfolder/" + path + "/" + foldername;

		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}

		try {

			File f = new File(Pathname);
			if (f.isDirectory()) {
				delete(f);
				if (f.exists() == true) {
					f.delete();
				} else {
					System.out.println("Error:No File or Folder");
					httpservletrequest.setAttribute("MESSAGE",
							"No File or Folder");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			} else {
				if (f.exists() == true) {
					f.delete();
				} else {
					System.out.println("Error:No File or Folder");
					httpservletrequest.setAttribute("MESSAGE",
							"No File or Folder");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public static void delete(File folder) {
		if (folder.exists()) {
			File[] files = folder.listFiles();
			for (int nFileCur = 0; nFileCur < files.length; nFileCur++) {
				File oFileCur = files[nFileCur];
				if (oFileCur.isDirectory()) {
					// call itself to delete the contents of the current folder
					delete(oFileCur);
				}
				oFileCur.delete();
			}
		}
	}

	public ActionForward fileupload(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform,
				hydraulicelevatorandothertankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		// httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()
		try {

			FormFile uploadFile = (FormFile) dynaactionform.get("filename");
			String uploadingFileName = uploadFile.getFileName();
			// String
			// uploadPath=getServlet().getServletContext().getRealPath("/")
			// +"/upload";
			String uploadPath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + path;
			if (!uploadingFileName.equals("")) {

				File uploadFileObject = new File(uploadPath, uploadingFileName);
				if (uploadFileObject.exists() == false) {
					FileOutputStream fileOutStream = new FileOutputStream(
							uploadFileObject);
					fileOutStream.write(uploadFile.getFileData());
					fileOutStream.flush();
					fileOutStream.close();
				} else {
					System.out.println("Error:Already Exist");
					httpservletrequest.setAttribute("MESSAGE", "Already Exist");
					httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				}

			} else {
				httpservletrequest.setAttribute("MESSAGE", "No File To upload");
				httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
				System.out.println("Error:No File");
			}

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		// httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()
		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");

	}

	public ActionForward back(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform,
				hydraulicelevatorandothertankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId()
				+ "/hydraulicelevatorandothertank/"
				+ hydraulicelevatorandothertankvo.getId()
				+ "-"
				+ hydraulicelevatorandothertankvo.getFacilityDesignatedId()
						.trim())) {
			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		System.out.println("back:" + back);
		String foldername = (String) dynaactionform.get("foldername");
		setformvariable(back, actionmapping, actionform, httpservletrequest,
				httpservletresponse);

		return actionmapping.findForward("success");

	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			httpservletrequest.setAttribute("showAddBtn", "Y");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		} else {
			httpservletrequest.setAttribute("showAddBtn", "N");
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In Add");
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s2 = (String) dynaactionform.get("facilityDesignatedId");
		String s3 = (String) dynaactionform.get("capacity");
		String s4 = (String) dynaactionform.get("usage");
		String s5 = (String) dynaactionform.get("secondaryContain");
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = new HydraulicElevatorAndOtherTankVo();
		hydraulicelevatorandothertankvo.setBldgId(buildingvo.getId());
		if (UtilityObject.isNotEmpty(s3))
			hydraulicelevatorandothertankvo.setCapacity(Integer.parseInt(s3));
		hydraulicelevatorandothertankvo.setFacilityDesignatedId(s2);
		hydraulicelevatorandothertankvo.setSecondaryContainment(UtilityObject
				.convertBoolean(s5));
		hydraulicelevatorandothertankvo.setUsage(s4);
		String s6 = (String) dynaactionform.get("YearInstalled");
		hydraulicelevatorandothertankvo.setYearInstalled(s6);
		String s7 = (String) dynaactionform.get("ModifiedInPast");
		String s8 = (String) dynaactionform.get("DisconnectedYear");

		hydraulicelevatorandothertankvo.setDisconnectedYear(s8);
		hydraulicelevatorandothertankvo.setModifiedInPast(Integer.parseInt(s7));
		hydraulicelevatorandothertankvo.setDecapproval((String) dynaactionform
				.get("decapproval"));
		hydraulicelevatorandothertankvo
				.setMonthlyinspection((String) dynaactionform
						.get("monthlyinspection"));
		hydraulicelevatorandothertankvo.setSpillkit((String) dynaactionform
				.get("spillkit"));
		hydraulicelevatorandothertankvo.setPbsno((String) dynaactionform
				.get("pbsno"));
		hydraulicelevatorandothertankvo.setHcomments((String) dynaactionform
				.get("hcomments"));
		hydraulicelevatorandothertankvo.setDobsignoff((String) dynaactionform
				.get("dobsignoff"));
		hydraulicelevatorandothertankvo.setDobnumber((String) dynaactionform
				.get("dobnumber"));
		try {
			HydraulicElevatorTankEntity.add(hydraulicelevatorandothertankvo);
			setFieldDetails(httpservletrequest, dynaactionform,
					hydraulicelevatorandothertankvo);
			httpservletrequest.setAttribute("isComponentEditable", "N");
			s = "Added Hydraulic Storage Tank.";
			s1 = "CONFIRMATION";
		} catch (TrackingException trackingexception) {
			s = trackingexception.getMessage();
			s1 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In Edit");
		httpservletrequest.removeAttribute("isComponentEditable");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = HydraulicElevatorTankEntity
				.findByPrimaryKey(i);
		if (hydraulicelevatorandothertankvo != null) {
			httpsession.setAttribute("HYDRAULIC_STROAGE_OBJECT",
					hydraulicelevatorandothertankvo);
			dynaactionform.set("facilityDesignatedId",
					hydraulicelevatorandothertankvo.getFacilityDesignatedId());
			dynaactionform.set(
					"capacity",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getCapacity()).append("").toString());
			if (UtilityObject.isNotEmpty(hydraulicelevatorandothertankvo
					.getUsage()))
				dynaactionform.set(
						"usage",
						(new StringBuilder())
								.append(hydraulicelevatorandothertankvo
										.getUsage()).append("").toString());
			dynaactionform.set(
					"DisconnectedYear",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getDisconnectedYear()).append("")
							.toString());
			dynaactionform.set(
					"YearInstalled",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getYearInstalled()).append("").toString());
			dynaactionform
					.set("ModifiedInPast",
							(new StringBuilder())
									.append(hydraulicelevatorandothertankvo
											.getModifiedInPast()).append("")
									.toString());
			dynaactionform.set("secondaryContain", UtilityObject
					.booleanToString(hydraulicelevatorandothertankvo
							.isSecondaryContainment()));
			dynaactionform.set("decapproval",
					hydraulicelevatorandothertankvo.getDecapproval());
			dynaactionform.set("monthlyinspection",
					hydraulicelevatorandothertankvo.getMonthlyinspection());
			dynaactionform.set("spillkit",
					hydraulicelevatorandothertankvo.getSpillkit());
			dynaactionform.set("pbsno",
					hydraulicelevatorandothertankvo.getPbsno());
			dynaactionform.set("hcomments",
					hydraulicelevatorandothertankvo.getHcomments());
			dynaactionform.set("dobsignoff",
					hydraulicelevatorandothertankvo.getDobsignoff());
			dynaactionform.set("dobnumber",
					hydraulicelevatorandothertankvo.getDobnumber());

		}
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In View");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = HydraulicElevatorTankEntity
				.findByPrimaryKey(i);
		if (hydraulicelevatorandothertankvo != null) {
			httpsession.setAttribute("HYDRAULIC_STROAGE_OBJECT",
					hydraulicelevatorandothertankvo);
			dynaactionform.set("facilityDesignatedId",
					hydraulicelevatorandothertankvo.getFacilityDesignatedId());
			dynaactionform.set(
					"capacity",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getCapacity()).append("").toString());
			if (UtilityObject.isNotEmpty(hydraulicelevatorandothertankvo
					.getUsage())
					&& !(hydraulicelevatorandothertankvo.getUsage())
							.equals("-1")) {
				HydraulicStorageUsageEnum hydraulicstorageusageenum = HydraulicStorageUsageEnum
						.get(Integer.parseInt(hydraulicelevatorandothertankvo
								.getUsage()));
				dynaactionform
						.set("usage", hydraulicstorageusageenum.getName());
			}
			dynaactionform.set(
					"YearInstalled",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getYearInstalled()).append("").toString());
			dynaactionform.set(
					"DisconnectedYear",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getDisconnectedYear()).append("")
							.toString());
			TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
					.get(hydraulicelevatorandothertankvo.getModifiedInPast());
			if (tankoperatingstatusenum != null)
				dynaactionform.set("ModifiedInPast",
						tankoperatingstatusenum.getName());
			dynaactionform.set("secondaryContain", UtilityObject
					.booleanToString(hydraulicelevatorandothertankvo
							.isSecondaryContainment()));

			dynaactionform.set("decapproval",
					hydraulicelevatorandothertankvo.getDecapproval());
			dynaactionform.set("monthlyinspection",
					hydraulicelevatorandothertankvo.getMonthlyinspection());
			dynaactionform.set("spillkit",
					hydraulicelevatorandothertankvo.getSpillkit());
			dynaactionform.set("pbsno",
					hydraulicelevatorandothertankvo.getPbsno());
			dynaactionform.set("hcomments",
					hydraulicelevatorandothertankvo.getHcomments());
			dynaactionform.set("dobsignoff",
					hydraulicelevatorandothertankvo.getDobsignoff());
			dynaactionform.set("dobnumber",
					hydraulicelevatorandothertankvo.getDobnumber());

		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In Edit");
		httpservletrequest.removeAttribute("isComponentEditable");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = HydraulicElevatorTankEntity
				.findByPrimaryKey(i);
		if (hydraulicelevatorandothertankvo != null) {
			httpsession.setAttribute("HYDRAULIC_STROAGE_OBJECT",
					hydraulicelevatorandothertankvo);
			dynaactionform.set("facilityDesignatedId",
					hydraulicelevatorandothertankvo.getFacilityDesignatedId());
			dynaactionform.set(
					"capacity",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getCapacity()).append("").toString());
			if (UtilityObject.isNotEmpty(hydraulicelevatorandothertankvo
					.getUsage()))
				dynaactionform.set(
						"usage",
						(new StringBuilder())
								.append(hydraulicelevatorandothertankvo
										.getUsage()).append("").toString());
			dynaactionform.set(
					"DisconnectedYear",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getDisconnectedYear()).append("")
							.toString());
			dynaactionform.set(
					"YearInstalled",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo
									.getYearInstalled()).append("").toString());
			dynaactionform
					.set("ModifiedInPast",
							(new StringBuilder())
									.append(hydraulicelevatorandothertankvo
											.getModifiedInPast()).append("")
									.toString());
			dynaactionform.set("secondaryContain", UtilityObject
					.booleanToString(hydraulicelevatorandothertankvo
							.isSecondaryContainment()));
			dynaactionform.set("decapproval",
					hydraulicelevatorandothertankvo.getDecapproval());
			dynaactionform.set("monthlyinspection",
					hydraulicelevatorandothertankvo.getMonthlyinspection());
			dynaactionform.set("spillkit",
					hydraulicelevatorandothertankvo.getSpillkit());
			dynaactionform.set("pbsno",
					hydraulicelevatorandothertankvo.getPbsno());
			dynaactionform.set("hcomments",
					hydraulicelevatorandothertankvo.getHcomments());
			dynaactionform.set("dobsignoff",
					hydraulicelevatorandothertankvo.getDobsignoff());
			dynaactionform.set("dobnumber",
					hydraulicelevatorandothertankvo.getDobnumber());

		}
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In View");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = HydraulicElevatorTankEntity
				.findByPrimaryKey(i);
		if (hydraulicelevatorandothertankvo != null)
			httpsession.setAttribute("HYDRAULIC_STROAGE_OBJECT",
					hydraulicelevatorandothertankvo);
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo1 = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		try {
			HydraulicElevatorTankEntity
					.delete(hydraulicelevatorandothertankvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/"
						+ facilityvo.getDecId()
						+ "/hydraulicelevatorandothertank/"
						+ hydraulicelevatorandothertankvo1.getId()
						+ "-"
						+ hydraulicelevatorandothertankvo1
								.getFacilityDesignatedId().trim());
				if (f.isDirectory()) {
					delete(f);
					if (f.exists() == true) {
						f.delete();
					} else {
						System.out.println("Error:No File or Folder");
					}
				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

		} catch (TrackingException trackingexception) {
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		return actionmapping.findForward("successhydraulic");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("HydraulicStorageTankAction - In Update");
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s2 = (String) dynaactionform.get("facilityDesignatedId");
		String s3 = (String) dynaactionform.get("capacity");
		String s4 = (String) dynaactionform.get("usage");
		String s5 = (String) dynaactionform.get("secondaryContain");
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		String facilityDesignatedid = hydraulicelevatorandothertankvo
				.getFacilityDesignatedId();
		if (UtilityObject.isNotEmpty(s3))
			hydraulicelevatorandothertankvo.setCapacity(Integer.parseInt(s3));
		hydraulicelevatorandothertankvo.setFacilityDesignatedId(s2);
		String s6 = (String) dynaactionform.get("YearInstalled");
		String s7 = (String) dynaactionform.get("DisconnectedYear");
		String s8 = (String) dynaactionform.get("ModifiedInPast");
		hydraulicelevatorandothertankvo.setDisconnectedYear(s7);
		hydraulicelevatorandothertankvo.setYearInstalled(s6);
		hydraulicelevatorandothertankvo.setModifiedInPast(Integer.parseInt(s8));
		hydraulicelevatorandothertankvo.setSecondaryContainment(UtilityObject
				.convertBoolean(s5));
		hydraulicelevatorandothertankvo.setUsage(s4);
		hydraulicelevatorandothertankvo.setDecapproval((String) dynaactionform
				.get("decapproval"));
		hydraulicelevatorandothertankvo
				.setMonthlyinspection((String) dynaactionform
						.get("monthlyinspection"));
		hydraulicelevatorandothertankvo.setSpillkit((String) dynaactionform
				.get("spillkit"));
		hydraulicelevatorandothertankvo.setPbsno((String) dynaactionform
				.get("pbsno"));
		hydraulicelevatorandothertankvo.setHcomments((String) dynaactionform
				.get("hcomments"));
		hydraulicelevatorandothertankvo.setDobsignoff((String) dynaactionform
				.get("dobsignoff"));
		hydraulicelevatorandothertankvo.setDobnumber((String) dynaactionform
				.get("dobnumber"));
		try {
			HydraulicElevatorTankEntity.update(hydraulicelevatorandothertankvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/hydraulicelevatorandothertank/"
						+ hydraulicelevatorandothertankvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/hydraulicelevatorandothertank/"
									+ hydraulicelevatorandothertankvo.getId()
									+ "-"
									+ hydraulicelevatorandothertankvo
											.getFacilityDesignatedId().trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			setFieldDetails(httpservletrequest, dynaactionform,
					hydraulicelevatorandothertankvo);
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo1 = HydraulicElevatorTankEntity
					.findByPrimaryKey(hydraulicelevatorandothertankvo.getId());
			if (hydraulicelevatorandothertankvo1 != null) {
				httpsession.setAttribute("HYDRAULIC_STROAGE_OBJECT",
						hydraulicelevatorandothertankvo1);
				dynaactionform.set("facilityDesignatedId",
						hydraulicelevatorandothertankvo1
								.getFacilityDesignatedId());
				dynaactionform.set(
						"capacity",
						(new StringBuilder())
								.append(hydraulicelevatorandothertankvo1
										.getCapacity()).append("").toString());
				if (UtilityObject.isNotEmpty(hydraulicelevatorandothertankvo1
						.getUsage())
						&& !(hydraulicelevatorandothertankvo.getUsage())
								.equals("-1")) {
					HydraulicStorageUsageEnum hydraulicstorageusageenum = HydraulicStorageUsageEnum
							.get(Integer
									.parseInt(hydraulicelevatorandothertankvo1
											.getUsage()));
					dynaactionform.set("usage",
							hydraulicstorageusageenum.getName());
				} else {
					dynaactionform.set("usage", "");
				}
				dynaactionform.set("secondaryContain", UtilityObject
						.booleanToString(hydraulicelevatorandothertankvo1
								.isSecondaryContainment()));
			}
			httpservletrequest.setAttribute("isComponentEditable", "N");
			s = "Updated Hydraulic Storage Tank.";
			s1 = "CONFIRMATION";
		} catch (TrackingException trackingexception) {
			s = trackingexception.getMessage();
			s1 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = HydraulicStorageUsageEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("HYDRAULIC_USAGE", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown1);

	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform,
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo) {
		HttpSession httpsession = httpservletrequest.getSession();
		if (hydraulicelevatorandothertankvo == null) {
			httpsession.removeAttribute("HYDRAULIC_STROAGE_OBJECT");
			httpsession.removeAttribute("HYDRAULIC_STROAGE_OBJECT");
			return;
		}
		dynaactionform.set("facilityDesignatedId",
				hydraulicelevatorandothertankvo.getFacilityDesignatedId());
		dynaactionform.set(
				"capacity",
				(new StringBuilder())
						.append(hydraulicelevatorandothertankvo.getCapacity())
						.append("").toString());

		if (UtilityObject
				.isNotEmpty(hydraulicelevatorandothertankvo.getUsage())
				&& !(hydraulicelevatorandothertankvo.getUsage()).equals("-1")) {
			HydraulicStorageUsageEnum hydraulicstorageusageenum = HydraulicStorageUsageEnum
					.get(Integer.parseInt(hydraulicelevatorandothertankvo
							.getUsage()));
			dynaactionform.set("usage", hydraulicstorageusageenum.getName());
		} else
			dynaactionform.set("usage", "");
		dynaactionform.set("YearInstalled",
				hydraulicelevatorandothertankvo.getYearInstalled());
		dynaactionform.set("DisconnectedYear",
				hydraulicelevatorandothertankvo.getDisconnectedYear());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(hydraulicelevatorandothertankvo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			dynaactionform.set("ModifiedInPast",
					tankoperatingstatusenum.getName());

		dynaactionform.set("decapproval",
				hydraulicelevatorandothertankvo.getDecapproval());
		dynaactionform.set("monthlyinspection",
				hydraulicelevatorandothertankvo.getMonthlyinspection());
		dynaactionform.set("spillkit",
				hydraulicelevatorandothertankvo.getSpillkit());
		dynaactionform.set("pbsno", hydraulicelevatorandothertankvo.getPbsno());
		dynaactionform.set("hcomments",
				hydraulicelevatorandothertankvo.getHcomments());
		dynaactionform.set("dobsignoff",
				hydraulicelevatorandothertankvo.getDobsignoff());
		dynaactionform.set("dobnumber",
				hydraulicelevatorandothertankvo.getDobnumber());

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) httpsession
				.getAttribute("HYDRAULIC_STROAGE_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/"
					+ facilityvo.getDecId()
					+ "/hydraulicelevatorandothertank/"
					+ hydraulicelevatorandothertankvo.getId()
					+ "-"
					+ hydraulicelevatorandothertankvo.getFacilityDesignatedId()
							.trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/"
					+ facilityvo.getDecId()
					+ "/hydraulicelevatorandothertank/"
					+ hydraulicelevatorandothertankvo.getId()
					+ "-"
					+ hydraulicelevatorandothertankvo.getFacilityDesignatedId()
							.trim();
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {

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

			}
		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", facilityvo.getDecId()
				+ "/hydraulicelevatorandothertank/"
				+ hydraulicelevatorandothertankvo.getId()
				+ "-"
				+ hydraulicelevatorandothertankvo.getFacilityDesignatedId()
						.trim());

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

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.HydraulicStorageTankAction.class);

}