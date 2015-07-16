package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.eespc.tracking.bo.PlaceofAssemblyPermitVo;
import com.eespc.tracking.bo.PlaceofAssemblyVo;
import com.eespc.tracking.bo.myenum.Patype;
import com.eespc.tracking.bo.myenum.ViolationTypeEnum;
import com.eespc.tracking.entity.PlaceofAssemblyEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class PlaceofAssemblyAction extends LookupDispatchAction {

	public PlaceofAssemblyAction() {
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
		// System.out.println("SENTHILLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
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
			else if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("delete"))
				return delete(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("Reset"))
				return reset(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("displaycontrol"))
				return displaycontrol(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("viewexist"))
				return viewexist(mapping, form, request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("dobPermitInfo"))
				return dobPermitInfo(mapping, form, request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("editDobPermit"))
				return editDobPermit(mapping, form, request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("updateDobPermitInfo"))
				return updateDobPermitInfo(mapping, form, request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("deleteDobPermit"))
				return deleteDobPermit(mapping, form, request, response);

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
			System.out.println("PlaceofAssembly Execute Exception" + e);
		}

		dynavalidatorform.set("facilitydesignatedid", "");
		dynavalidatorform.set("paType", "");
		dynavalidatorform.set("location", "");
		dynavalidatorform.set("seatingCapacity", "");
		dynavalidatorform.set("dobfiling", "");
		dynavalidatorform.set("dobpermit", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("dobPermitnumber", "");
		dynavalidatorform.set("dobPlan", "");
		dynavalidatorform.set("fdPermitObtained", "");
		dynavalidatorform.set("openViolation", "");
		dynavalidatorform.set("violationType", "");
		dynavalidatorform.set("violationNum", "");
		dynavalidatorform.set("note", "");
		request.setAttribute("SHOW_BUTTONS", "inline");
		request.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		// request.setAttribute("PA_DOB_PERMIT", null);
		return mapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
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
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editfile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);

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
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward viewfile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		String foldername = (String) dynaactionform.get("foldername");
		setformvariable((path + "/" + foldername), actionmapping, actionform,
				httpservletrequest, httpservletresponse);
		// refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deletefile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
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
		refreshPermitInfo(httpservletrequest);
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
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);

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
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");

	}

	public ActionForward back(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/placeofassembly/"
				+ placeofassemblyvo.getId() + "-"
				+ placeofassemblyvo.getFacilitydesignatedId().trim())) {
			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		System.out.println("back:" + back);
		String foldername = (String) dynaactionform.get("foldername");
		setformvariable(back, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		refreshPermitInfo(httpservletrequest);
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

		log.debug("PlaceofAssembly - ADD");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		PlaceofAssemblyVo placeofassemblyvo = new PlaceofAssemblyVo();
		placeofassemblyvo.setBuildingId(buildingvo.getId());
		placeofassemblyvo.setFacilitydesignatedId((String) dynaactionform
				.get("facilitydesignatedid"));
		placeofassemblyvo.setPaType(Integer.parseInt((String) dynaactionform
				.get("paType")));
		placeofassemblyvo.setLocation((String) dynaactionform.get("location"));
		placeofassemblyvo.setSeatingCapacity((String) dynaactionform
				.get("seatingCapacity"));
		placeofassemblyvo
				.setDobfiling((String) dynaactionform.get("dobfiling"));
		placeofassemblyvo
				.setDobpermit((String) dynaactionform.get("dobpermit"));
		placeofassemblyvo.setDobsignoff((String) dynaactionform
				.get("dobsignoff"));
		placeofassemblyvo.setDobPermitnumber((String) dynaactionform
				.get("dobPermitnumber"));
		placeofassemblyvo.setDobPlan((String) dynaactionform.get("dobPlan"));
		placeofassemblyvo.setFdPermitObtained((String) dynaactionform
				.get("fdPermitObtained"));
		placeofassemblyvo.setOpenViolation((String) dynaactionform
				.get("openViolation"));
		placeofassemblyvo.setViolationType(Integer
				.parseInt((String) dynaactionform.get("violationType")));
		placeofassemblyvo.setViolationNum((String) dynaactionform
				.get("violationNum"));
		placeofassemblyvo.setNote((String) dynaactionform.get("note"));

		byte byte0 = -99;
		try {

			int i = PlaceofAssemblyEntity.add(placeofassemblyvo);

			placeofassemblyvo = PlaceofAssemblyEntity.findByPrimaryKey(i);

			if (placeofassemblyvo != null) {

				httpsession.setAttribute("PLACEOFASSEMBLY_OBJECT",
						placeofassemblyvo);

				setFormVariable(dynaactionform, placeofassemblyvo,
						httpservletrequest);

			}

			s = "Added PlaceofAssembly Successfully.";
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
		log.debug("PlaceofAssembly - Update");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");

		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		String facilityDesignatedid = placeofassemblyvo
				.getFacilitydesignatedId();
		placeofassemblyvo.setBuildingId(buildingvo.getId());
		placeofassemblyvo.setFacilitydesignatedId((String) dynaactionform
				.get("facilitydesignatedid"));
		placeofassemblyvo.setPaType(Integer.parseInt((String) dynaactionform
				.get("paType")));
		placeofassemblyvo.setLocation((String) dynaactionform.get("location"));
		placeofassemblyvo.setSeatingCapacity((String) dynaactionform
				.get("seatingCapacity"));
		placeofassemblyvo
				.setDobfiling((String) dynaactionform.get("dobfiling"));
		placeofassemblyvo
				.setDobpermit((String) dynaactionform.get("dobpermit"));
		placeofassemblyvo.setDobsignoff((String) dynaactionform
				.get("dobsignoff"));
		placeofassemblyvo.setDobPermitnumber((String) dynaactionform
				.get("dobPermitnumber"));
		placeofassemblyvo.setDobPlan((String) dynaactionform.get("dobPlan"));
		placeofassemblyvo.setFdPermitObtained((String) dynaactionform
				.get("fdPermitObtained"));
		placeofassemblyvo.setOpenViolation((String) dynaactionform
				.get("openViolation"));
		placeofassemblyvo.setViolationType(Integer
				.parseInt((String) dynaactionform.get("violationType")));
		placeofassemblyvo.setViolationNum((String) dynaactionform
				.get("violationNum"));
		placeofassemblyvo.setNote((String) dynaactionform.get("note"));

		byte byte0 = -99;
		try {
			PlaceofAssemblyEntity.update(placeofassemblyvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/placeofassembly/" + placeofassemblyvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/placeofassembly/"
									+ placeofassemblyvo.getId()
									+ "-"
									+ placeofassemblyvo
											.getFacilitydesignatedId().trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			s = "Updated PlaceofAssembly Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, placeofassemblyvo,
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
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		try {
			log.debug("PlaceofAssembly - In Delete");
			DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynaactionform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			PlaceofAssemblyVo placeofassemblyvo = PlaceofAssemblyEntity
					.findByPrimaryKey(i);
			if (placeofassemblyvo != null)
				httpsession.setAttribute("PLACEOFASSEMBLY_OBJECT",
						placeofassemblyvo);
			PlaceofAssemblyVo placeofassemblyvox = (PlaceofAssemblyVo) httpsession
					.getAttribute("PLACEOFASSEMBLY_OBJECT");
			PlaceofAssemblyEntity.delete(placeofassemblyvox);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/placeofassembly/" + placeofassemblyvox.getId()
						+ "-"
						+ placeofassemblyvox.getFacilitydesignatedId().trim());
				if (f.isDirectory()) {
					// delete(f);
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

	// *********************************************DOB Permit
	// Start**************************************//

	public ActionForward dobPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		log.debug("PlaceofAssemblyAction - In dobPermitInfo");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		s = (String) dynaactionform.get("dobIssueDate");
		s1 = (String) dynaactionform.get("dobExpirationDate");
		s2 = (String) dynaactionform.get("dobExpirationNote");

		PlaceofAssemblyPermitVo placeofassemblypermitvo = new PlaceofAssemblyPermitVo();
		placeofassemblypermitvo.setPlaceofassemblyId(placeofassemblyvo.getId());
		placeofassemblypermitvo.setDobIssueDate(UtilityObject.convertToDate(s));
		placeofassemblypermitvo.setDobExpirationDate(UtilityObject
				.convertToDate(s1));
		placeofassemblypermitvo.setDobExpirationNote(s2);

		PlaceofAssemblyEntity.addPermit(placeofassemblypermitvo);
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
		dynaactionform.set("dobIssueDate", "");
		dynaactionform.set("dobExpirationDate", "");
		dynaactionform.set("dobExpirationNote", "");

		refreshShowInfo(httpservletrequest, placeofassemblyvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");

	}

	public ActionForward updateDobPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		System.out.println("updateDobPermitInfo.....");
		log.debug("PlaceofAssembly - In updateDobPermitInfo");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		String ss = (String) dynaactionform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("PlaceofAssembly - In updateDobPermitInfo - ID = ")
				.append(ss).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(ss))
			i = Integer.parseInt(ss);
		else
			throw new Exception(
					"PlaceofAssembly Permit Id is null while updating the PlaceofAssembly Permit");
		String s = "";
		String s1 = "";
		String s2 = "";
		s = (String) dynaactionform.get("dobIssueDate");
		s1 = (String) dynaactionform.get("dobExpirationDate");
		s2 = (String) dynaactionform.get("dobExpirationNote");

		PlaceofAssemblyPermitVo placeofassemblypermitvo = new PlaceofAssemblyPermitVo();
		placeofassemblypermitvo.setPlaceofassemblyId(placeofassemblyvo.getId());
		System.out.println("ID:" + placeofassemblyvo.getId());
		placeofassemblypermitvo.setDobIssueDate(UtilityObject.convertToDate(s));
		placeofassemblypermitvo.setDobExpirationDate(UtilityObject
				.convertToDate(s1));
		placeofassemblypermitvo.setDobExpirationNote(s2);
		System.out.println("Before Update.....");
		PlaceofAssemblyEntity.updatePermit(placeofassemblypermitvo);
		System.out.println("After Update.....");

		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
		dynaactionform.set("dobIssueDate", "");
		dynaactionform.set("dobExpirationDate", "");
		dynaactionform.set("dobExpirationNote", "");
		System.out.println("After Update  Ends.....");

		refreshShowInfo(httpservletrequest, placeofassemblyvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE....");
		return actionmapping.findForward("success");
	}

	public ActionForward editDobPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PlaceofAssembly - In editDobPermit");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOB_PERMIT", "Y");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");

	}

	public ActionForward deleteDobPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		log.debug("PlaceofAssemblyAction - In deleteDobPermit");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		String ss = (String) dynaactionform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("PlaceofAssemblyAction - In deleteDobPermit - ID = ")
				.append(ss).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(ss))
			i = Integer.parseInt(ss);
		else
			throw new Exception(
					"PlaceofAssembly Permit Id is null while updating the PlaceofAssembly Permit");

		PlaceofAssemblyPermitVo placeofassemblypermitvo = PlaceofAssemblyEntity
				.findPermit(i);
		PlaceofAssemblyEntity.deletePermit(placeofassemblypermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFormVariable(dynaactionform, placeofassemblyvo, httpservletrequest);
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");

	}

	// *********************************************DOB Permit
	// End**************************************//
	private void refreshShowInfo(HttpServletRequest httpservletrequest,
			PlaceofAssemblyVo placeofassemblyvo) {
		httpservletrequest.setAttribute("PA_DOB_PERMIT", null);
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		ArrayList arraylist = new ArrayList();
		try {
			if (placeofassemblyvo == null) {
				log.debug("Place of Assembly Object is null");
				return;
			}
			placeofassemblyvo.getId();
			placeofassemblyvo.setPermitList(null);

			List list = placeofassemblyvo.getPermitList();
			int i = list != null ? list.size() : -1;
			byte byte0 = -1;
			for (int j = 0; j < i; j++) {
				PlaceofAssemblyPermitVo placeofassemblypermitvo = (PlaceofAssemblyPermitVo) list
						.get(j);
				arraylist.add(placeofassemblypermitvo);
			}

			log.debug((new StringBuilder()).append("PA_DOB_PERMIT size=")
					.append(arraylist.size()).toString());
		} catch (Exception e) {
			System.out.println("refreshPermitInfo Dob:" + e);
		}

		httpservletrequest.setAttribute("PA_DOB_PERMIT", arraylist);
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			PlaceofAssemblyVo placeofassemblyvo,
			HttpServletRequest httpservletrequest) {
		System.out.println(placeofassemblyvo.getFacilitydesignatedId());

		HttpSession httpsession = httpservletrequest.getSession();
		// log.debug("placeofassembly setForm variable");

		try {

			Patype patype = Patype.get(placeofassemblyvo.getPaType());

			if (patype != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"paType",
						(new StringBuilder())
								.append(String.valueOf(placeofassemblyvo
										.getPaType())).append("").toString());
			else if (patype != null && userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"paType",
						(new StringBuilder())
								.append(String.valueOf(placeofassemblyvo
										.getPaType())).append("").toString());

			else if (patype != null && !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("paType", patype.getName());
			else
				dynavalidatorform.set("paType", "");

			ViolationTypeEnum violationtypeenum = ViolationTypeEnum
					.get(placeofassemblyvo.getViolationType());

			if (violationtypeenum != null
					&& userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"violationType",
						(new StringBuilder())
								.append(String.valueOf(placeofassemblyvo
										.getViolationType())).append("")
								.toString());
			else if (violationtypeenum != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"violationType",
						(new StringBuilder())
								.append(String.valueOf(placeofassemblyvo
										.getViolationType())).append("")
								.toString());

			else if (violationtypeenum != null
					&& !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("violationType",
						violationtypeenum.getName());
			else
				dynavalidatorform.set("violationType", "");

			dynavalidatorform.set("facilitydesignatedid",
					placeofassemblyvo.getFacilitydesignatedId());
			dynavalidatorform.set("location", placeofassemblyvo.getLocation());
			dynavalidatorform.set("seatingCapacity",
					placeofassemblyvo.getSeatingCapacity());
			dynavalidatorform
					.set("dobfiling", placeofassemblyvo.getDobfiling());
			dynavalidatorform
					.set("dobpermit", placeofassemblyvo.getDobpermit());
			dynavalidatorform.set("dobsignoff",
					placeofassemblyvo.getDobsignoff());
			dynavalidatorform.set("dobPermitnumber",
					placeofassemblyvo.getDobPermitnumber());
			dynavalidatorform.set("dobPlan", placeofassemblyvo.getDobPlan());
			dynavalidatorform.set("fdPermitObtained",
					placeofassemblyvo.getFdPermitObtained());
			dynavalidatorform.set("openViolation",
					placeofassemblyvo.getOpenViolation());
			dynavalidatorform.set("violationNum",
					placeofassemblyvo.getViolationNum());
			dynavalidatorform.set("note", placeofassemblyvo.getNote());

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
		// log.debug("placeofassembly - In View");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PlaceofAssemblyVo placeofassemblyvo = PlaceofAssemblyEntity
				.findByPrimaryKey(i);
		if (placeofassemblyvo != null) {
			httpsession.setAttribute("PLACEOFASSEMBLY_OBJECT",
					placeofassemblyvo);
			setFormVariable(dynaactionform, placeofassemblyvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get placeofassemblyvo for id(")
					.append(s).append(")").toString());
		}
		refreshPermitInfo(httpservletrequest);
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
		PlaceofAssemblyVo placeofassemblyvo = PlaceofAssemblyEntity
				.findByPrimaryKey(i);
		if (placeofassemblyvo != null) {
			httpsession.setAttribute("PLACEOFASSEMBLY_OBJECT",
					placeofassemblyvo);
			setFormVariable(dynavalidatorform, placeofassemblyvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get placeofassemblyvo for id(")
					.append(s).append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("placeofassembly - In Edit");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PlaceofAssemblyVo placeofassemblyvo = PlaceofAssemblyEntity
				.findByPrimaryKey(i);
		if (placeofassemblyvo != null) {
			httpsession.setAttribute("PLACEOFASSEMBLY_OBJECT",
					placeofassemblyvo);
			setFormVariable(dynaactionform, placeofassemblyvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get placeofassemblyvo for id(")
					.append(s).append(")").toString());
		}
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("isComponentEditable", "Y");

		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		// log.debug("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("facilitydesignatedid", "");
		dynavalidatorform.set("paType", "");
		dynavalidatorform.set("location", "");
		dynavalidatorform.set("seatingCapacity", "");
		dynavalidatorform.set("dobfiling", "");
		dynavalidatorform.set("dobpermit", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("dobPermitnumber", "");
		dynavalidatorform.set("dobPlan", "");
		dynavalidatorform.set("fdPermitObtained", "");
		dynavalidatorform.set("openViolation", "");
		dynavalidatorform.set("violationType", "");
		dynavalidatorform.set("violationNum", "");
		dynavalidatorform.set("note", "");

		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("PA_DOB_PERMIT", null);
		return actionmapping.findForward("success");

	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = Patype.getDropDownObj();
		httpservletrequest.setAttribute("PA_TYPE", dropdown);
		com.eespc.tracking.bo.DropDown dropdown0 = ViolationTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("VIOLATION_TYPE", dropdown0);
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PlaceofAssemblyVo placeofassemblyvo = (PlaceofAssemblyVo) httpsession
				.getAttribute("PLACEOFASSEMBLY_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/placeofAssembly/" + placeofassemblyvo.getId() + "-"
					+ placeofassemblyvo.getFacilitydesignatedId().trim());
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
					+ "/placeofAssembly/" + placeofassemblyvo.getId() + "-"
					+ placeofassemblyvo.getFacilitydesignatedId().trim();
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
				+ "/placeofassembly/" + placeofassemblyvo.getId() + "-"
				+ placeofassemblyvo.getFacilitydesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.PlaceofAssemblyAction.class);
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