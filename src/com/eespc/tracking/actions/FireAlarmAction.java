package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;

import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FireAlarmVo;
import com.eespc.tracking.bo.myenum.FireAlarmType;
import com.eespc.tracking.bo.myenum.FireAlarmUse;
import com.eespc.tracking.bo.myenum.ModifiedIn;
import com.eespc.tracking.entity.FireAlarmEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class FireAlarmAction extends LookupDispatchAction {

	public FireAlarmAction() {
		userAction = null;
	}

	protected Map getKeyMethodMap() {
		HashMap hashmap = new HashMap();
		hashmap.put("app.add", "add");
		hashmap.put("app.save", "save");
		hashmap.put("app.reset", "reset");
		hashmap.put("app.search", "search");
		hashmap.put("app.delete", "delete");
		return hashmap;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) form;
		try {
			setDropDown(request);
			HttpSession session = request.getSession();

			userAction = (String) dynavalidatorform.get("methodToCall");
			log.debug("Miscellaneous - MethodToCall " + userAction);
			if (userAction != null && userAction.equalsIgnoreCase("Save"))
				return save(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("Update"))
				return update(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("delete"))
				return delete(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("Reset"))
				return reset(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("displaycontrol"))
				return displaycontrol(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("viewexist"))
				return viewexist(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("fileupload"))
				return fileupload(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("back"))
				return back(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("addnewfolder"))
				return addnewfolder(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("deletefile"))
				return deletefile(mapping, form, request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("editfile"))
				return editfile(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("viewfile"))
				return viewfile(mapping, form, request, response);

		} catch (Exception e) {
			System.out.println("FireAlarmExecute Execute Exception" + e);
		}

		dynavalidatorform.set("facilitydesignatedid", "");
		dynavalidatorform.set("yearinstalled", "");
		dynavalidatorform.set("statusOfSource", "");
		dynavalidatorform.set("disconnectedYear", "");
		dynavalidatorform.set("typeofFAInstalled", "");
		dynavalidatorform.set("dobApproval", "");
		dynavalidatorform.set("dobFiling", "");
		dynavalidatorform.set("approvalDate", "");
		dynavalidatorform.set("dobSignoff", "");
		dynavalidatorform.set("fdApproval", "");
		dynavalidatorform.set("fdApprovalDate", "");
		dynavalidatorform.set("agencyApproval", "");
		dynavalidatorform.set("agencyApprovalDate", "");
		dynavalidatorform.set("agencyApprovalNo", "");
		dynavalidatorform.set("comments", "");
		dynavalidatorform.set("agencyName", "");

		request.setAttribute("SHOW_BUTTONS", "inline");
		request.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		return mapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = new FireAlarmVo();
		firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		setFormVariable(dynaactionform, firealarmvo, httpservletrequest);
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
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = new FireAlarmVo();
		firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		setFormVariable(dynaactionform, firealarmvo, httpservletrequest);

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

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = new FireAlarmVo();
		firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		setFormVariable(dynaactionform, firealarmvo, httpservletrequest);
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
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = new FireAlarmVo();
		firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		setFormVariable(dynaactionform, firealarmvo, httpservletrequest);
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

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = new FireAlarmVo();
		firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		setFormVariable(dynaactionform, firealarmvo, httpservletrequest);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");

		try {

			FormFile uploadFile = (FormFile) dynaactionform.get("filename");
			String uploadingFileName = uploadFile.getFileName();
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

		setformvariable(path, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");

	}

	public ActionForward back(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = new FireAlarmVo();
		firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		setFormVariable(dynaactionform, firealarmvo, httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/firealarm/"
				+ firealarmvo.getId() + "-"
				+ firealarmvo.getFacilitydesignatedid().trim())) {
			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		String foldername = (String) dynaactionform.get("foldername");
		setformvariable(back, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward displaycontrol(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("FireAlarm - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		System.out.println("FireAlarm display:" + s);
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
			httpservletrequest.setAttribute("showAddBtn", "Y");

		} else {
			httpservletrequest.setAttribute("isItForEdit", "N");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
			httpservletrequest.setAttribute("showAddBtn", "N");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		}

		return actionmapping.findForward("success");
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		log.debug("FireAlarm - ADD");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");

		FireAlarmVo firealarmvo = new FireAlarmVo();

		firealarmvo.setBuildingId(buildingvo.getId());
		firealarmvo.setFacilitydesignatedid((String) dynaactionform
				.get("facilitydesignatedid"));
		firealarmvo.setYearinstalled((String) dynaactionform
				.get("yearinstalled"));
		firealarmvo.setStatusOfSource(Integer.parseInt((String) dynaactionform
				.get("statusOfSource")));
		firealarmvo.setDisconnectedYear((String) dynaactionform
				.get("disconnectedYear"));

		firealarmvo.setTypeofFAInstalled(Integer
				.parseInt((String) dynaactionform.get("typeofFAInstalled")));
		firealarmvo.setDobApproval((String) dynaactionform.get("dobApproval"));
		firealarmvo.setDobFiling((String) dynaactionform.get("dobFiling"));
		firealarmvo.setDobSignoff((String) dynaactionform.get("dobSignoff"));
		firealarmvo.setFdApproval((String) dynaactionform.get("fdApproval"));
		firealarmvo.setAgencyApproval((String) dynaactionform
				.get("agencyApproval"));
		firealarmvo.setAgencyApprovalNo((String) dynaactionform
				.get("agencyApprovalNo"));
		firealarmvo.setComments((String) dynaactionform.get("comments"));
		firealarmvo.setAgencyName((String) dynaactionform.get("agencyName"));

		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("approvalDate")),
				"yyyy-MM-dd");
		firealarmvo.setApprovalDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("fdApprovalDate")),
				"yyyy-MM-dd");
		firealarmvo.setFdApprovalDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("agencyApprovalDate")), "yyyy-MM-dd");
		firealarmvo.setAgencyApprovalDate(sd);

		byte byte0 = -99;
		try {

			int i = FireAlarmEntity.add(firealarmvo);

			firealarmvo = FireAlarmEntity.findByPrimaryKey(i);

			if (firealarmvo != null) {

				httpsession.setAttribute("FIREALARM_OBJECT", firealarmvo);

				setFormVariable(dynaactionform, firealarmvo, httpservletrequest);

			}

			s = "Added FireAlarm Successfully.";
			s1 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, Exception {
		log.debug("FireAlarm - Update");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		FireAlarmVo firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		String facilityDesignatedid = firealarmvo.getFacilitydesignatedid();

		firealarmvo.setBuildingId(buildingvo.getId());
		firealarmvo.setFacilitydesignatedid((String) dynaactionform
				.get("facilitydesignatedid"));
		firealarmvo.setYearinstalled((String) dynaactionform
				.get("yearinstalled"));
		firealarmvo.setStatusOfSource(Integer.parseInt((String) dynaactionform
				.get("statusOfSource")));
		firealarmvo.setDisconnectedYear((String) dynaactionform
				.get("disconnectedYear"));

		firealarmvo.setTypeofFAInstalled(Integer
				.parseInt((String) dynaactionform.get("typeofFAInstalled")));
		firealarmvo.setDobApproval((String) dynaactionform.get("dobApproval"));
		firealarmvo.setDobFiling((String) dynaactionform.get("dobFiling"));
		firealarmvo.setDobSignoff((String) dynaactionform.get("dobSignoff"));
		firealarmvo.setFdApproval((String) dynaactionform.get("fdApproval"));
		firealarmvo.setAgencyApproval((String) dynaactionform
				.get("agencyApproval"));
		firealarmvo.setAgencyApprovalNo((String) dynaactionform
				.get("agencyApprovalNo"));
		firealarmvo.setComments((String) dynaactionform.get("comments"));
		firealarmvo.setAgencyName((String) dynaactionform.get("agencyName"));

		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("approvalDate")),
				"yyyy-MM-dd");
		firealarmvo.setApprovalDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("fdApprovalDate")),
				"yyyy-MM-dd");
		firealarmvo.setFdApprovalDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("agencyApprovalDate")), "yyyy-MM-dd");
		firealarmvo.setAgencyApprovalDate(sd);

		byte byte0 = -99;
		try {
			FireAlarmEntity.update(firealarmvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/firealarm/" + firealarmvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/firealarm/"
									+ firealarmvo.getId()
									+ "-"
									+ firealarmvo.getFacilitydesignatedid()
											.trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			s = "Updated FireAlarm Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, firealarmvo, httpservletrequest);
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		try {
			log.debug("FIREALARM - In Delete");
			DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynaactionform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			FireAlarmVo firealarmvo = FireAlarmEntity.findByPrimaryKey(i);
			if (firealarmvo != null)
				httpsession.setAttribute("FIREALARM_OBJECT", firealarmvo);
			FireAlarmVo firealarmvox = (FireAlarmVo) httpsession
					.getAttribute("FIREALARM_OBJECT");
			FireAlarmEntity.delete(firealarmvox);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/firealarm/" + firealarmvox.getId() + "-"
						+ firealarmvox.getFacilitydesignatedid().trim());
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
			log.error(trackingexception);
		}
		return actionmapping.findForward("successcr");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			FireAlarmVo firealarmvo, HttpServletRequest httpservletrequest) {

		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("FireAlarm setForm variable");

		try {

			dynavalidatorform.set("facilitydesignatedid",
					firealarmvo.getFacilitydesignatedid());
			dynavalidatorform.set("yearinstalled",
					firealarmvo.getYearinstalled());
			// dynavalidatorform.set("statusOfSource",firealarmvo.getStatusOfSource());

			ModifiedIn modifiedin = ModifiedIn.get(firealarmvo
					.getStatusOfSource());

			if (modifiedin != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"statusOfSource",
						(new StringBuilder())
								.append(String.valueOf(firealarmvo
										.getStatusOfSource())).append("")
								.toString());
			else if (modifiedin != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"statusOfSource",
						(new StringBuilder())
								.append(String.valueOf(firealarmvo
										.getStatusOfSource())).append("")
								.toString());

			else if (modifiedin != null && !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("statusOfSource", modifiedin.getName());
			else
				dynavalidatorform.set("statusOfSource", "");

			FireAlarmUse firealarmuse = FireAlarmUse.get(firealarmvo
					.getStatusOfSource());

			if (firealarmuse != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"use",
						(new StringBuilder())
								.append(String.valueOf(firealarmvo
										.getStatusOfSource())).append("")
								.toString());
			else if (firealarmuse != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"use",
						(new StringBuilder())
								.append(String.valueOf(firealarmvo
										.getStatusOfSource())).append("")
								.toString());

			else if (firealarmuse != null
					&& !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("use", firealarmuse.getName());
			else
				dynavalidatorform.set("use", "");

			FireAlarmType firealarmtype = FireAlarmType.get(firealarmvo
					.getTypeofFAInstalled());

			if (firealarmtype != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"typeofFAInstalled",
						(new StringBuilder())
								.append(String.valueOf(firealarmvo
										.getTypeofFAInstalled())).append("")
								.toString());
			else if (firealarmtype != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"typeofFAInstalled",
						(new StringBuilder())
								.append(String.valueOf(firealarmvo
										.getTypeofFAInstalled())).append("")
								.toString());

			else if (firealarmtype != null
					&& !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("typeofFAInstalled",
						firealarmtype.getName());
			else
				dynavalidatorform.set("typeofFAInstalled", "");

			dynavalidatorform.set("disconnectedYear",
					firealarmvo.getDisconnectedYear());
			// dynavalidatorform.set("use",firealarmvo.getUse());

			// dynavalidatorform.set("typeofFAInstalled",firealarmvo.getTypeofFAInstalled());
			dynavalidatorform.set("dobApproval", firealarmvo.getDobApproval());
			dynavalidatorform.set("dobFiling", firealarmvo.getDobFiling());
			dynavalidatorform.set("approvalDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(firealarmvo.getApprovalDate()));
			dynavalidatorform.set("dobSignoff", firealarmvo.getDobSignoff());
			dynavalidatorform.set("fdApproval", firealarmvo.getFdApproval());
			dynavalidatorform.set("fdApprovalDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(firealarmvo.getFdApprovalDate()));
			dynavalidatorform.set("agencyApproval",
					firealarmvo.getAgencyApproval());
			dynavalidatorform.set("agencyApprovalDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(firealarmvo
							.getAgencyApprovalDate()));
			dynavalidatorform.set("agencyApprovalNo",
					firealarmvo.getAgencyApprovalNo());
			dynavalidatorform.set("comments", firealarmvo.getComments());
			dynavalidatorform.set("agencyName", firealarmvo.getAgencyName());

		} catch (Exception exception) {
			System.out.println("Exception :" + exception);
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("FireAlarm - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FireAlarmVo firealarmvo = FireAlarmEntity.findByPrimaryKey(i);
		if (firealarmvo != null) {
			httpsession.setAttribute("FIREALARM_OBJECT", firealarmvo);
			setFormVariable(dynavalidatorform, firealarmvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get firealarmvo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("firealarm... Edit");
		log.debug("firealarm... Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FireAlarmVo firealarmvo = FireAlarmEntity.findByPrimaryKey(i);
		if (firealarmvo != null) {
			httpsession.setAttribute("FIREALARM_OBJECT", firealarmvo);
			setFormVariable(dynavalidatorform, firealarmvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get firealarmvo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("firealarm - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FireAlarmVo firealarmvo = FireAlarmEntity.findByPrimaryKey(i);
		if (firealarmvo != null) {
			httpsession.setAttribute("FIREALARM_OBJECT", firealarmvo);
			setFormVariable(dynavalidatorform, firealarmvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get firealarmvo for id(").append(s)
					.append(")").toString());
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("facilitydesignatedid", "");
		dynavalidatorform.set("yearinstalled", "");
		dynavalidatorform.set("statusOfSource", "");
		dynavalidatorform.set("disconnectedYear", "");

		dynavalidatorform.set("typeofFAInstalled", "");
		dynavalidatorform.set("dobApproval", "");
		dynavalidatorform.set("dobFiling", "");
		dynavalidatorform.set("approvalDate", "");
		dynavalidatorform.set("dobSignoff", "");
		dynavalidatorform.set("fdApproval", "");
		dynavalidatorform.set("fdApprovalDate", "");
		dynavalidatorform.set("agencyApproval", "");
		dynavalidatorform.set("agencyApprovalDate", "");
		dynavalidatorform.set("agencyApprovalNo", "");
		dynavalidatorform.set("comments", "");
		dynavalidatorform.set("agencyName", "");

		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = ModifiedIn.getDropDownObj();
		httpservletrequest.setAttribute("FIREALARM_STATUS", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = FireAlarmUse
				.getDropDownObj();
		httpservletrequest.setAttribute("FIREALARM_USE", dropdown1);
		com.eespc.tracking.bo.DropDown dropdown2 = FireAlarmType
				.getDropDownObj();
		httpservletrequest.setAttribute("FIREALARM_TYPE", dropdown2);

	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FireAlarmVo firealarmvo = (FireAlarmVo) httpsession
				.getAttribute("FIREALARM_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/firealarm/"
					+ firealarmvo.getId() + "-"
					+ firealarmvo.getFacilitydesignatedid().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/firealarm/"
					+ firealarmvo.getId() + "-"
					+ firealarmvo.getFacilitydesignatedid().trim();
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
		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", facilityvo.getDecId()
				+ "/firealarm/" + firealarmvo.getId() + "-"
				+ firealarmvo.getFacilitydesignatedid().trim());

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

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.FireAlarmAction.class);
	public String userAction;
	public static HashMap VALID_STATES;

	static {
		VALID_STATES = new HashMap();
		VALID_STATES.put("NY", "NYCDEP");
		VALID_STATES.put("NJ", "NJDEP");
		VALID_STATES.put("PA", "PADEP");
		VALID_STATES.put("NC", "NCDENR");
		VALID_STATES.put("SC", "SCDEH");
		VALID_STATES.put("MD", "MDE");
	}
}