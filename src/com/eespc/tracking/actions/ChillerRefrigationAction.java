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
import com.eespc.tracking.bo.ChillerRefrigationVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.myenum.ChillerLocation;
import com.eespc.tracking.bo.myenum.ModifiedIn;
import com.eespc.tracking.entity.ChillerRefrigationEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class ChillerRefrigationAction extends LookupDispatchAction {

	public ChillerRefrigationAction() {
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

		}

		catch (Exception e) {
			System.out.println("ChillerRefrigationExecute Execute Exception"
					+ e);
		}

		dynavalidatorform.set("facilitydesignatedid", "");
		dynavalidatorform.set("yearinstalled", "");
		dynavalidatorform.set("make", "");
		dynavalidatorform.set("model", "");
		dynavalidatorform.set("status", "");
		dynavalidatorform.set("disconnectedyear", "");
		dynavalidatorform.set("location", "");
		dynavalidatorform.set("capacitytons", "");
		dynavalidatorform.set("capacitybtu", "");
		dynavalidatorform.set("equipmentusepermit", "");
		dynavalidatorform.set("dobpermit", "");
		dynavalidatorform.set("dobissuedate", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("epastatus", "");
		dynavalidatorform.set("epasubmittaldate", "");
		// dynavalidatorform.set("submittaldatenote","");
		dynavalidatorform.set("epaapprovaldate", "");
		dynavalidatorform.set("fdaapproval", "");
		// dynavalidatorform.set("fdaapprovaldate","");
		dynavalidatorform.set("comments", "");
		dynavalidatorform.set("serialnum", "");
		dynavalidatorform.set("areaServed", "");
		dynavalidatorform.set("fuelFiring", "");
		dynavalidatorform.set("fuelUsed", "");
		dynavalidatorform.set("depStatus", "");
		dynavalidatorform.set("deppermit", "");
		dynavalidatorform.set("depexpirationdate", "");
		dynavalidatorform.set("refrigerationrecovery", "");
		dynavalidatorform.set("epamaintained", "");
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
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		setFormVariable(dynaactionform, chillerrefrigationvo,
				httpservletrequest);
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
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		setFormVariable(dynaactionform, chillerrefrigationvo,
				httpservletrequest);

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
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		setFormVariable(dynaactionform, chillerrefrigationvo,
				httpservletrequest);
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
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		setFormVariable(dynaactionform, chillerrefrigationvo,
				httpservletrequest);
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
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		setFormVariable(dynaactionform, chillerrefrigationvo,
				httpservletrequest);

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
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		setFormVariable(dynaactionform, chillerrefrigationvo,
				httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/chillerrefrigation/"
				+ chillerrefrigationvo.getId() + "-"
				+ chillerrefrigationvo.getFacilitydesignatedId().trim())) {
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

	public ActionForward displaycontrol(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Miscellaneous - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		System.out.println("Miscellaneous display:" + s);
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

		log.debug("ChillerRefrigation - ADD");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		ChillerRefrigationVo chillerrefrigationvo = new ChillerRefrigationVo();
		chillerrefrigationvo.setBuildingId(buildingvo.getId());
		chillerrefrigationvo.setFacilitydesignatedId((String) dynaactionform
				.get("facilitydesignatedid"));
		chillerrefrigationvo.setYearInstalled((String) dynaactionform
				.get("yearinstalled"));
		chillerrefrigationvo.setMake((String) dynaactionform.get("make"));
		chillerrefrigationvo.setModel((String) dynaactionform.get("model"));
		chillerrefrigationvo.setStatus(Integer.parseInt((String) dynaactionform
				.get("status")));
		chillerrefrigationvo.setDisconnectedyear((String) dynaactionform
				.get("disconnectedyear"));
		chillerrefrigationvo.setLocation(Integer
				.parseInt((String) dynaactionform.get("location")));
		chillerrefrigationvo.setCapacityTons((String) dynaactionform
				.get("capacitytons"));
		chillerrefrigationvo.setCapacityBtu((String) dynaactionform
				.get("capacitybtu"));
		chillerrefrigationvo.setEquipmentusepermit((String) dynaactionform
				.get("equipmentusepermit"));
		chillerrefrigationvo.setDobpermit((String) dynaactionform
				.get("dobpermit"));
		chillerrefrigationvo.setDobsignoff((String) dynaactionform
				.get("dobsignoff"));
		chillerrefrigationvo.setEpaStatus((String) dynaactionform
				.get("epastatus"));
		chillerrefrigationvo.setFdaApproval((String) dynaactionform
				.get("fdaapproval"));
		chillerrefrigationvo.setComments((String) dynaactionform
				.get("comments"));
		chillerrefrigationvo.setSerialnum((String) dynaactionform
				.get("serialnum"));
		chillerrefrigationvo.setAreaServed((String) dynaactionform
				.get("areaServed"));
		chillerrefrigationvo.setDobfiling((String) dynaactionform
				.get("dobfiling"));
		chillerrefrigationvo.setFuelFiring((String) dynaactionform
				.get("fuelFiring"));
		chillerrefrigationvo.setFuelUsed((String) dynaactionform
				.get("fuelUsed"));
		chillerrefrigationvo.setDepStatus((String) dynaactionform
				.get("depStatus"));
		chillerrefrigationvo.setDeppermit((String) dynaactionform
				.get("deppermit"));
		chillerrefrigationvo.setRefrigerationrecovery((String) dynaactionform
				.get("refrigerationrecovery"));
		chillerrefrigationvo.setEpamaintained((String) dynaactionform
				.get("epamaintained"));
		// chillerrefrigationvo.setSubmittalDateNote((String)dynaactionform.get("submittaldatenote"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("dobissuedate")),
				"yyyy-MM-dd");
		chillerrefrigationvo.setDobissuedate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("epasubmittaldate")), "yyyy-MM-dd");
		chillerrefrigationvo.setEpaSubmittalDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("epaapprovaldate")),
				"yyyy-MM-dd");
		chillerrefrigationvo.setEpaApprovalDate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("depexpirationdate")), "yyyy-MM-dd");
		chillerrefrigationvo.setDepexpirationdate(sd);

		byte byte0 = -99;
		try {

			int i = ChillerRefrigationEntity.add(chillerrefrigationvo);

			chillerrefrigationvo = ChillerRefrigationEntity.findByPrimaryKey(i);

			if (chillerrefrigationvo != null) {

				httpsession.setAttribute("CHILLERREFRIGATION_OBJECT",
						chillerrefrigationvo);

				setFormVariable(dynaactionform, chillerrefrigationvo,
						httpservletrequest);

			}

			s = "Added ChillerRefrigation Successfully.";
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
		log.debug("Miscellaneous - Update");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");

		ChillerRefrigationVo chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		String facilityDesignatedid = chillerrefrigationvo
				.getFacilitydesignatedId();
		chillerrefrigationvo.setBuildingId(buildingvo.getId());
		chillerrefrigationvo.setFacilitydesignatedId((String) dynaactionform
				.get("facilitydesignatedid"));
		chillerrefrigationvo.setYearInstalled((String) dynaactionform
				.get("yearinstalled"));
		chillerrefrigationvo.setMake((String) dynaactionform.get("make"));
		chillerrefrigationvo.setModel((String) dynaactionform.get("model"));
		chillerrefrigationvo.setStatus(Integer.parseInt((String) dynaactionform
				.get("status")));
		chillerrefrigationvo.setDisconnectedyear((String) dynaactionform
				.get("disconnectedyear"));
		chillerrefrigationvo.setLocation(Integer
				.parseInt((String) dynaactionform.get("location")));
		chillerrefrigationvo.setCapacityTons((String) dynaactionform
				.get("capacitytons"));
		chillerrefrigationvo.setCapacityBtu((String) dynaactionform
				.get("capacitybtu"));
		chillerrefrigationvo.setEquipmentusepermit((String) dynaactionform
				.get("equipmentusepermit"));
		chillerrefrigationvo.setDobpermit((String) dynaactionform
				.get("dobpermit"));
		chillerrefrigationvo.setDobsignoff((String) dynaactionform
				.get("dobsignoff"));
		chillerrefrigationvo.setEpaStatus((String) dynaactionform
				.get("epastatus"));
		chillerrefrigationvo.setFdaApproval((String) dynaactionform
				.get("fdaapproval"));
		chillerrefrigationvo.setComments((String) dynaactionform
				.get("comments"));
		chillerrefrigationvo.setSerialnum((String) dynaactionform
				.get("serialnum"));
		chillerrefrigationvo.setAreaServed((String) dynaactionform
				.get("areaServed"));
		chillerrefrigationvo.setDobfiling((String) dynaactionform
				.get("dobfiling"));
		chillerrefrigationvo.setFuelFiring((String) dynaactionform
				.get("fuelFiring"));
		chillerrefrigationvo.setFuelUsed((String) dynaactionform
				.get("fuelUsed"));
		chillerrefrigationvo.setDepStatus((String) dynaactionform
				.get("depStatus"));
		chillerrefrigationvo.setDeppermit((String) dynaactionform
				.get("deppermit"));

		// chillerrefrigationvo.setSubmittalDateNote((String)dynaactionform.get("submittaldatenote"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("dobissuedate")),
				"yyyy-MM-dd");
		chillerrefrigationvo.setDobissuedate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("epasubmittaldate")), "yyyy-MM-dd");
		chillerrefrigationvo.setEpaSubmittalDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("epaapprovaldate")),
				"yyyy-MM-dd");
		chillerrefrigationvo.setEpaApprovalDate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("depexpirationdate")), "yyyy-MM-dd");
		chillerrefrigationvo.setDepexpirationdate(sd);

		chillerrefrigationvo.setRefrigerationrecovery((String) dynaactionform
				.get("refrigerationrecovery"));
		chillerrefrigationvo.setEpamaintained((String) dynaactionform
				.get("epamaintained"));

		byte byte0 = -99;
		try {
			ChillerRefrigationEntity.update(chillerrefrigationvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/chillerrefrigation/" + chillerrefrigationvo.getId()
						+ "-" + facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/chillerrefrigation/"
									+ chillerrefrigationvo.getId()
									+ "-"
									+ chillerrefrigationvo
											.getFacilitydesignatedId().trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			s = "Updated ChillerRefrigation Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, chillerrefrigationvo,
					httpservletrequest);
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
			log.debug("ChillerRefrigation - In Delete");
			DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynaactionform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			ChillerRefrigationVo chillerrefrigationvo = ChillerRefrigationEntity
					.findByPrimaryKey(i);
			if (chillerrefrigationvo != null)
				httpsession.setAttribute("CHILLERREFRIGATION_OBJECT",
						chillerrefrigationvo);
			ChillerRefrigationVo chillerrefrigationvox = (ChillerRefrigationVo) httpsession
					.getAttribute("CHILLERREFRIGATION_OBJECT");
			ChillerRefrigationEntity.delete(chillerrefrigationvox);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/"
						+ facilityvo.getDecId()
						+ "/chillerrefrigation/"
						+ chillerrefrigationvox.getId()
						+ "-"
						+ chillerrefrigationvox.getFacilitydesignatedId()
								.trim());
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
			ChillerRefrigationVo chillerrefrigationvo,
			HttpServletRequest httpservletrequest) {
		// System.out.println(chillerrefrigationvo.getFacilitydesignatedId());

		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("ChillerRefrigation setForm variable");

		try {

			ModifiedIn modifiedin = ModifiedIn.get(chillerrefrigationvo
					.getStatus());

			if (modifiedin != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"status",
						(new StringBuilder())
								.append(String.valueOf(chillerrefrigationvo
										.getStatus())).append("").toString());
			else if (modifiedin != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"status",
						(new StringBuilder())
								.append(String.valueOf(chillerrefrigationvo
										.getStatus())).append("").toString());

			else if (modifiedin != null && !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("status", modifiedin.getName());
			else
				dynavalidatorform.set("status", "");

			ChillerLocation chillerlocation = ChillerLocation
					.get(chillerrefrigationvo.getLocation());

			if (chillerlocation != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"location",
						(new StringBuilder())
								.append(String.valueOf(chillerrefrigationvo
										.getLocation())).append("").toString());
			else if (chillerlocation != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"location",
						(new StringBuilder())
								.append(String.valueOf(chillerrefrigationvo
										.getLocation())).append("").toString());

			else if (chillerlocation != null
					&& !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("location", chillerlocation.getName());
			else
				dynavalidatorform.set("location", "");

			// com.eespc.tracking.bo.DropDown dropdown0 =
			// ChillerLocation.getDropDownObj();

			dynavalidatorform.set("facilitydesignatedid",
					chillerrefrigationvo.getFacilitydesignatedId());
			dynavalidatorform.set("yearinstalled",
					chillerrefrigationvo.getYearInstalled());
			dynavalidatorform.set("make", chillerrefrigationvo.getMake());
			dynavalidatorform.set("model", chillerrefrigationvo.getModel());

			// dynavalidatorform.set("status",String.valueOf(chillerrefrigationvo.getStatus()));
			dynavalidatorform.set("disconnectedyear",
					chillerrefrigationvo.getDisconnectedyear());

			// dynavalidatorform.set("location",String.valueOf(chillerrefrigationvo.getLocation()));
			dynavalidatorform.set("capacitytons",
					chillerrefrigationvo.getCapacityTons());
			dynavalidatorform.set("capacitybtu",
					chillerrefrigationvo.getCapacityBtu());
			dynavalidatorform.set("equipmentusepermit",
					chillerrefrigationvo.getEquipmentusepermit());
			dynavalidatorform.set("dobpermit",
					chillerrefrigationvo.getDobpermit());
			dynavalidatorform.set("dobissuedate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(chillerrefrigationvo
							.getDobissuedate()));
			dynavalidatorform.set("dobsignoff",
					chillerrefrigationvo.getDobsignoff());
			dynavalidatorform.set("epastatus",
					chillerrefrigationvo.getEpaStatus());
			dynavalidatorform.set("epasubmittaldate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(chillerrefrigationvo
							.getEpaSubmittalDate()));
			// dynavalidatorform.set("submittaldatenote",chillerrefrigationvo.getSubmittalDateNote());
			dynavalidatorform.set("epaapprovaldate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(chillerrefrigationvo
							.getEpaApprovalDate()));
			dynavalidatorform.set("fdaapproval",
					chillerrefrigationvo.getFdaApproval());
			// dynavalidatorform.set("fdaapprovaldate",UtilityObject.convertYyyyMmDd2MmDdYyyy(chillerrefrigationvo.getFdaApprovalDate()));
			dynavalidatorform.set("comments",
					chillerrefrigationvo.getComments());
			dynavalidatorform.set("serialnum",
					chillerrefrigationvo.getSerialnum());
			dynavalidatorform.set("areaServed",
					chillerrefrigationvo.getAreaServed());
			dynavalidatorform.set("dobfiling",
					chillerrefrigationvo.getDobfiling());
			dynavalidatorform.set("fuelFiring",
					chillerrefrigationvo.getFuelFiring());
			dynavalidatorform.set("fuelUsed",
					chillerrefrigationvo.getFuelUsed());
			dynavalidatorform.set("depStatus",
					chillerrefrigationvo.getDepStatus());
			dynavalidatorform.set("deppermit",
					chillerrefrigationvo.getDeppermit());
			dynavalidatorform.set("depexpirationdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(chillerrefrigationvo
							.getDepexpirationdate()));
			dynavalidatorform.set("refrigerationrecovery",
					chillerrefrigationvo.getRefrigerationrecovery());
			dynavalidatorform.set("epamaintained",
					chillerrefrigationvo.getEpamaintained());

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
		log.debug("chillerrefrigation - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ChillerRefrigationVo chillerrefrigationvo = ChillerRefrigationEntity
				.findByPrimaryKey(i);
		if (chillerrefrigationvo != null) {
			httpsession.setAttribute("CHILLERREFRIGATION_OBJECT",
					chillerrefrigationvo);
			setFormVariable(dynavalidatorform, chillerrefrigationvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get chillerrefrigationvo for id(")
					.append(s).append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("MIS... Edit");
		log.debug("MIS... Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ChillerRefrigationVo chillerrefrigationvo = ChillerRefrigationEntity
				.findByPrimaryKey(i);
		if (chillerrefrigationvo != null) {
			httpsession.setAttribute("CHILLERREFRIGATION_OBJECT",
					chillerrefrigationvo);
			setFormVariable(dynavalidatorform, chillerrefrigationvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get chillerrefrigationvo for id(")
					.append(s).append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("ChillerRefrigation - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ChillerRefrigationVo chillerrefrigationvo = ChillerRefrigationEntity
				.findByPrimaryKey(i);
		if (chillerrefrigationvo != null) {
			httpsession.setAttribute("CHILLERREFRIGATION_OBJECT",
					chillerrefrigationvo);
			setFormVariable(dynavalidatorform, chillerrefrigationvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get chillerrefrigationvo for id(")
					.append(s).append(")").toString());
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
		dynavalidatorform.set("make", "");
		dynavalidatorform.set("model", "");
		dynavalidatorform.set("status", "");
		dynavalidatorform.set("disconnectedyear", "");
		dynavalidatorform.set("location", "");
		dynavalidatorform.set("capacitytons", "");
		dynavalidatorform.set("capacitybtu", "");
		dynavalidatorform.set("equipmentusepermit", "");
		dynavalidatorform.set("dobpermit", "");
		dynavalidatorform.set("dobissuedate", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("epastatus", "");
		dynavalidatorform.set("epasubmittaldate", "");
		dynavalidatorform.set("submittaldatenote", "");
		dynavalidatorform.set("epaapprovaldate", "");
		dynavalidatorform.set("fdaapproval", "");
		dynavalidatorform.set("fdaapprovaldate", "");
		dynavalidatorform.set("comments", "");
		dynavalidatorform.set("serialnum", "");
		dynavalidatorform.set("areaServed", "");
		dynavalidatorform.set("dobfiling", "");
		dynavalidatorform.set("fuelFiring", "");
		dynavalidatorform.set("fuelUsed", "");
		dynavalidatorform.set("depStatus", "");
		dynavalidatorform.set("deppermit", "");
		dynavalidatorform.set("depexpirationdate", "");
		dynavalidatorform.set("refrigerationrecovery", "");
		dynavalidatorform.set("epamaintained", "");

		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");

	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = ModifiedIn.getDropDownObj();
		httpservletrequest.setAttribute("CHILLERREFRIGATION_STATUS", dropdown);
		com.eespc.tracking.bo.DropDown dropdown0 = ChillerLocation
				.getDropDownObj();
		httpservletrequest.setAttribute("CHILLERREFRIGATION_LOCATION",
				dropdown0);
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerRefrigationVo chillerrefrigationvo = (ChillerRefrigationVo) httpsession
				.getAttribute("CHILLERREFRIGATION_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/chillerrefrigation/" + chillerrefrigationvo.getId()
					+ "-"
					+ chillerrefrigationvo.getFacilitydesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/chillerrefrigation/" + chillerrefrigationvo.getId()
					+ "-"
					+ chillerrefrigationvo.getFacilitydesignatedId().trim();
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
				+ "/chillerrefrigation/" + chillerrefrigationvo.getId() + "-"
				+ chillerrefrigationvo.getFacilitydesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.ChillerRefrigationAction.class);
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