package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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

import com.eespc.tracking.bo.BuildingManager;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.RpzVo;
import com.eespc.tracking.bo.myenum.AgencyLocationEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.RpzEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class RpzAction extends LookupDispatchAction {

	public RpzAction() {
		userAction = null;
	}

	protected Map getKeyMethodMap() {
		HashMap hashmap = new HashMap();
		hashmap.put("rpzForm.return", "returnTo");
		hashmap.put("rpzForm.save", "save");
		hashmap.put("rpzForm.reset", "reset");
		hashmap.put("rpzForm.state", "state");
		hashmap.put("rpzForm.delete", "delete");
		hashmap.put("rpzForm.inspection", "installation");
		return hashmap;
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest)
			throws Exception {
		boolean flag = UtilityObject.isBoroughValidInNyc(httpservletrequest);
		if (flag)
			httpservletrequest.setAttribute("is5Borough", "Y");
		boolean flag1 = UtilityObject.isFacilityInNy(httpservletrequest);
		if (flag1)
			httpservletrequest.setAttribute("isInNy", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		if (rpzvo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
	}

	private void setScreenInfoforsearch(HttpServletRequest httpservletrequest)
			throws Exception {
		boolean flag = UtilityObject.isBoroughValidInNyc(httpservletrequest);
		if (flag)
			httpservletrequest.setAttribute("is5Borough", "Y");
		boolean flag1 = UtilityObject.isFacilityInNy(httpservletrequest);
		if (flag1)
			httpservletrequest.setAttribute("isInNy", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		httpservletrequest.setAttribute("showAddBtn", "N");
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		setDropDown(httpservletrequest);
		userAction = (String) dynavalidatorform.get("methodToCall");
		log.debug((new StringBuilder()).append("RpzAction:execute() ")
				.append(userAction).toString());
		if (userAction != null && userAction.equalsIgnoreCase("Save"))
			return save(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Update"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Delete"))
			return delete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("state"))
			return statePermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("view"))
			return view(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("viewexist"))
			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("edit"))
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("inspectionEdit"))
			return inspectionEdit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateEdit"))
			return stateEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateUpdate"))
			return stateUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateDelete"))
			return stateDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null
				&& userAction.equalsIgnoreCase("inspectionUpdate"))
			return inspectionUpdate(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null
				&& userAction.equalsIgnoreCase("inspectionDelete"))
			return inspectionDelete(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Return"))
			return returnTo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("inspection"))
			return installation(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("reset"))
			return reset(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null
				&& userAction.equalsIgnoreCase("statePermitInfo")) {
			return statePermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		}
		if (userAction != null && userAction.equalsIgnoreCase("fileupload"))
			return fileupload(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("back"))
			return back(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("addnewfolder"))
			return addnewfolder(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("deletefile"))
			return deletefile(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("editfile"))
			return editfile(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("viewfile"))
			return viewfile(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		else {
			HttpSession httpsession = httpservletrequest.getSession();
			dynavalidatorform.set("rpzId", "");
			dynavalidatorform.set("rpzYear", "");
			dynavalidatorform.set("rpzSerialNo", "");
			dynavalidatorform.set("rpzMake", "");
			dynavalidatorform.set("rpzModel", "");
			dynavalidatorform.set("rpzType", "");
			dynavalidatorform.set("Permit", "");
			dynavalidatorform.set("psLocation", "");
			dynavalidatorform.set("rpzDOB", "");
			dynavalidatorform.set("rpzDEP", "");
			// dynavalidatorform.set("rpzDepInspected", "");
			dynavalidatorform.set("rpzLocIssueDate", "");
			dynavalidatorform.set("rpzLocExpirationDate", "");
			dynavalidatorform.set("rpzLocExpirationDateNote", "");
			dynavalidatorform.set("rpzLocNumber", "");
			dynavalidatorform.set("rpzComment", "");
			dynavalidatorform.set("ModifiedInPast", "");
			dynavalidatorform.set("DisconnectedYear", "");
			dynavalidatorform.set("dobsignoff", "");

			log.debug("RpzAction - In Execute");
			httpservletrequest.setAttribute("RPZ_LOC", "none");
			httpservletrequest.setAttribute("RPZ_INSPEC", "none");
			httpservletrequest.removeAttribute("RPZ_LOC_LST");
			httpservletrequest.removeAttribute("RPZ_INSTALLATION_LST");
			httpservletrequest.setAttribute("LOCATION", "");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
			return actionmapping.findForward("success");
		}
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = new RpzVo();
		rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		setFormVariable(dynaactionform, rpzvo, httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// refreshShowInfo(httpservletrequest, rpzvo);
		refreshPermitInfo(httpservletrequest);
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
		RpzVo rpzvo = new RpzVo();
		rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		setFormVariable(dynaactionform, rpzvo, httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");

		// refreshShowInfo(httpservletrequest, rpzvo);
		refreshPermitInfo(httpservletrequest);
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
		RpzVo rpzvo = new RpzVo();
		rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		setFormVariable(dynaactionform, rpzvo, httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");

		// refreshShowInfo(httpservletrequest, rpzvo);
		refreshPermitInfo(httpservletrequest);
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
		RpzVo rpzvo = new RpzVo();
		rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		setFormVariable(dynaactionform, rpzvo, httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");

		// refreshShowInfo(httpservletrequest, rpzvo);
		refreshPermitInfo(httpservletrequest);
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

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = new RpzVo();
		rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		setFormVariable(dynaactionform, rpzvo, httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");

		// refreshShowInfo(httpservletrequest, rpzvo);
		refreshPermitInfo(httpservletrequest);
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

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = new RpzVo();
		rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		setFormVariable(dynaactionform, rpzvo, httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");

		// refreshShowInfo(httpservletrequest, rpzvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/rpz/" + rpzvo.getId() + "-"
				+ rpzvo.getFacilityDesignatedId().trim())) {
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

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("RpzAction - In Add");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		RpzVo rpzvo = new RpzVo();
		rpzvo.setFacilityDesignatedId((String) dynavalidatorform.get("rpzId"));
		rpzvo.setBuildingId(buildingvo.getId());
		rpzvo.setYearInstalled((String) dynavalidatorform.get("rpzYear"));
		rpzvo.setSerialNumber((String) dynavalidatorform.get("rpzSerialNo"));
		rpzvo.setMake((String) dynavalidatorform.get("rpzMake"));
		rpzvo.setModel((String) dynavalidatorform.get("rpzModel"));
		rpzvo.setType((String) dynavalidatorform.get("rpzType"));

		String s2 = (String) dynavalidatorform.get("Permit");
		rpzvo.setPermit(s2);

		// rpzvo.setPermit(UtilityObject.convertBoolean((String)dynavalidatorform.get("Permit")));

		rpzvo.setComments((String) dynavalidatorform.get("rpzComment"));
		rpzvo.setDisconnectedYear((String) dynavalidatorform
				.get("DisconnectedYear"));
		String s3 = (String) dynavalidatorform.get("ModifiedInPast");
		rpzvo.setModifiedInPast(Integer.parseInt(s3));
		rpzvo.setSize((String) dynavalidatorform.get("rpzSize"));
		/*
		 * String s3 = (String)dynavalidatorform.get("rpzSize");
		 * if(UtilityObject.isNotEmpty(s3)) rpzvo.setSize(Integer.parseInt(s3));
		 */
		rpzvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("psLocation")));
		int i = Integer.parseInt((String) dynavalidatorform.get("psLocation"));
		// if(i == AgencyLocationEnum.RCDOH.getCode() || i ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		rpzvo.setDobPermitNumber((String) dynavalidatorform.get("rpzDOB"));
		rpzvo.setDepPermitNumber((String) dynavalidatorform.get("rpzDEP"));
		rpzvo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));
		byte byte0 = -99;
		try {
			int j = RpzEntity.add(rpzvo);
			rpzvo = RpzEntity.findByPrimaryKey(j);
			if (rpzvo != null) {
				httpsession.setAttribute("RPZ_OBJECT", rpzvo);
				dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
						httpservletrequest);
			}
			s = "Added RPZ Successfully.";
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
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("RPZ_LOC_LST", null);
		httpservletrequest.setAttribute("RPZ_INSTALLATION_LST", null);
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StackAction - In Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("RpzAction - In Add");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String facilityDesignatedid = rpzvo.getFacilityDesignatedId();
		rpzvo.setFacilityDesignatedId((String) dynavalidatorform.get("rpzId"));
		rpzvo.setBuildingId(buildingvo.getId());
		rpzvo.setYearInstalled((String) dynavalidatorform.get("rpzYear"));
		rpzvo.setSerialNumber((String) dynavalidatorform.get("rpzSerialNo"));
		rpzvo.setMake((String) dynavalidatorform.get("rpzMake"));
		rpzvo.setModel((String) dynavalidatorform.get("rpzModel"));
		rpzvo.setType((String) dynavalidatorform.get("rpzType"));

		String s2 = (String) dynavalidatorform.get("Permit");
		rpzvo.setPermit(s2);

		// rpzvo.setPermit(UtilityObject.convertBoolean((String)dynavalidatorform.get("Permit")));
		rpzvo.setComments((String) dynavalidatorform.get("rpzComment"));
		String s3 = (String) dynavalidatorform.get("ModifiedInPast");
		rpzvo.setModifiedInPast(Integer.parseInt(s3));
		rpzvo.setDisconnectedYear((String) dynavalidatorform
				.get("DisconnectedYear"));
		rpzvo.setSize((String) dynavalidatorform.get("rpzSize"));
		/*
		 * String s3 = (String)dynavalidatorform.get("rpzSize");
		 * if(UtilityObject.isNotEmpty(s3)) rpzvo.setSize(Integer.parseInt(s3));
		 */
		rpzvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("psLocation")));
		int i = Integer.parseInt((String) dynavalidatorform.get("psLocation"));
		// if(i == AgencyLocationEnum.RCDOH.getCode() || i ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		rpzvo.setDobPermitNumber((String) dynavalidatorform.get("rpzDOB"));
		rpzvo.setDepPermitNumber((String) dynavalidatorform.get("rpzDEP"));
		rpzvo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));
		byte byte0 = -99;
		try {
			RpzEntity.update(rpzvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId() + "/rpz/"
						+ rpzvo.getId() + "-" + facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/" + facilityvo.getDecId()
									+ "/rpz/" + rpzvo.getId() + "-"
									+ rpzvo.getFacilityDesignatedId().trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
					httpservletrequest);
			refreshBuildingObject(httpservletrequest);
			s = "Updated RPZ Successfully.";
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
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		try {
			log.debug("Rpz - In View");
			DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynavalidatorform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			RpzVo rpzvo = RpzEntity.findByPrimaryKey(i);
			if (rpzvo != null)
				httpsession.setAttribute("RPZ_OBJECT", rpzvo);
			BuildingVo buildingvo = (BuildingVo) httpsession
					.getAttribute("BUILDING_OBJECT");
			RpzVo rpzvo1 = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
			RpzEntity.delete(rpzvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId() + "/rpz/"
						+ rpzvo1.getId() + "-"
						+ rpzvo1.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successrpz");
	}

	private void refreshBuildingObject(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		int i = buildingvo.getId();
		BuildingManager buildingmanager = new BuildingManager();
		buildingvo = buildingmanager.getBuilding(i);
		if (buildingvo != null)
			httpsession.setAttribute("BUILDING_OBJECT", buildingvo);
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Rpz - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		RpzVo rpzvo = RpzEntity.findByPrimaryKey(i);
		if (rpzvo != null) {
			httpsession.setAttribute("RPZ_OBJECT", rpzvo);
			dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder()).append("Unable to get Rpz for id(")
					.append(s).append(")").toString());
		}
		try {
			dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
					httpservletrequest);
		} catch (Exception exception) {
			log.debug((new StringBuilder()).append("Error : ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		// if(rpzvo.getLocation() == AgencyLocationEnum.RCDOH.getCode() ||
		// rpzvo.getLocation() == AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Rpz - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		RpzVo rpzvo = RpzEntity.findByPrimaryKey(i);
		if (rpzvo != null) {
			httpsession.setAttribute("RPZ_OBJECT", rpzvo);
			dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder()).append("Unable to get Rpz for id(")
					.append(s).append(")").toString());
		}
		dynavalidatorform = setFormVariableforedit(dynavalidatorform, rpzvo,
				httpservletrequest);
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		// if(rpzvo.getLocation() == AgencyLocationEnum.RCDOH.getCode() ||
		// rpzvo.getLocation() == AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Rpz - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		RpzVo rpzvo = RpzEntity.findByPrimaryKey(i);
		if (rpzvo != null) {
			httpsession.setAttribute("RPZ_OBJECT", rpzvo);
			dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder()).append("Unable to get Rpz for id(")
					.append(s).append(")").toString());
		}
		dynavalidatorform = setFormVariableforsearch(dynavalidatorform, rpzvo,
				httpservletrequest);
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		// if(rpzvo.getLocation() == AgencyLocationEnum.RCDOH.getCode() ||
		// rpzvo.getLocation() == AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
			return s;
		else
			return s1;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public DynaValidatorForm setFormVariable(
			DynaValidatorForm dynavalidatorform, RpzVo rpzvo,
			HttpServletRequest httpservletrequest) {
		dynavalidatorform.set("rpzId",
				(new StringBuilder()).append(rpzvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform.set("rpzSerialNo",
				(new StringBuilder()).append(rpzvo.getSerialNumber())
						.append("").toString());
		dynavalidatorform.set("rpzMake",
				(new StringBuilder()).append(rpzvo.getMake()).append("")
						.toString());
		dynavalidatorform.set("rpzModel",
				(new StringBuilder()).append(rpzvo.getModel()).append("")
						.toString());
		dynavalidatorform.set("Permit", checkNullAndFill(rpzvo.isPermit(), ""));
		// dynavalidatorform.set("Permit", rpzvo.isPermit() ? "Y" : "N");
		/*
		 * if(rpzvo.getSize()!=-99) dynavalidatorform.set("rpzSize", (new
		 * StringBuilder()).append(rpzvo.getSize()).append("").toString()); else
		 * dynavalidatorform.set("rpzSize","");
		 */
		dynavalidatorform.set("rpzSize",
				(new StringBuilder()).append(rpzvo.getSize()).append("")
						.toString());

		dynavalidatorform.set("rpzDOB",
				(new StringBuilder()).append(rpzvo.getDobPermitNumber())
						.append("").toString());
		dynavalidatorform.set("rpzDEP",
				(new StringBuilder()).append(rpzvo.getDepPermitNumber())
						.append("").toString());
		dynavalidatorform.set(
				"rpzYear",
				(new StringBuilder()).append(rpzvo.getYearInstalled())
						.append("").toString());
		dynavalidatorform.set("rpzComment",
				(new StringBuilder()).append(rpzvo.getComments()).append("")
						.toString());
		dynavalidatorform.set("rpzType",
				(new StringBuilder()).append(rpzvo.getType()).append("")
						.toString());
		dynavalidatorform.set("DisconnectedYear",
				(new StringBuilder()).append(rpzvo.getDisconnectedYear())
						.append("").toString());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(rpzvo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			dynavalidatorform.set("ModifiedInPast",
					tankoperatingstatusenum.getName());
		if (userAction.equalsIgnoreCase("edit"))
			dynavalidatorform.set("psLocation",
					(new StringBuilder()).append(rpzvo.getLocation())
							.append("").toString());
		else if (rpzvo.getLocation() != -1)
			dynavalidatorform
					.set("psLocation",
							(new StringBuilder())
									.append(AgencyLocationEnum.get(rpzvo
											.getLocation())).append("")
									.toString());
		else
			dynavalidatorform.set("psLocation", "");

		dynavalidatorform.set("dobsignoff", rpzvo.getDobsignoff());
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		try {
			java.util.List list = RpzEntity.getInstallationInfo(rpzvo.getId());
			httpservletrequest.setAttribute("RPZ_INSTALLATION_LST", list);
			java.util.List list1 = RpzEntity.getPermitList(rpzvo.getId());
			// if(list1 != null)
			httpservletrequest.setAttribute("RPZ_LOC_LST", list1);
			// else
			// httpservletrequest.setAttribute("RPZ_LOC_LST", null);
		} catch (Exception exception) {
			log.error((new StringBuilder()).append("Rpz.setFormVariable : ")
					.append(exception.toString()).toString());
		}
		try {
			setScreenInfo(httpservletrequest);
			reset(httpservletrequest);
		} catch (Exception exception1) {
		}
		String s;
		Object obj;
		for (Enumeration enumeration = httpservletrequest.getAttributeNames(); enumeration
				.hasMoreElements(); log.debug((new StringBuilder()).append(s)
				.append(":").append(obj).toString())) {
			s = (String) enumeration.nextElement();
			obj = httpservletrequest.getAttribute(s);
		}

		return dynavalidatorform;
	}

	public DynaValidatorForm setFormVariableforedit(
			DynaValidatorForm dynavalidatorform, RpzVo rpzvo,
			HttpServletRequest httpservletrequest) {
		dynavalidatorform.set("rpzId",
				(new StringBuilder()).append(rpzvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform.set("rpzSerialNo",
				(new StringBuilder()).append(rpzvo.getSerialNumber())
						.append("").toString());
		dynavalidatorform.set("rpzMake",
				(new StringBuilder()).append(rpzvo.getMake()).append("")
						.toString());
		dynavalidatorform.set("rpzModel",
				(new StringBuilder()).append(rpzvo.getModel()).append("")
						.toString());
		dynavalidatorform.set("Permit", checkNullAndFill(rpzvo.isPermit(), ""));
		// dynavalidatorform.set("Permit", rpzvo.isPermit() ? "Y" : "N");
		/*
		 * if(rpzvo.getSize()!=-99) dynavalidatorform.set("rpzSize", (new
		 * StringBuilder()).append(rpzvo.getSize()).append("").toString()); else
		 * dynavalidatorform.set("rpzSize","");
		 */
		dynavalidatorform.set("rpzSize",
				(new StringBuilder()).append(rpzvo.getSize()).append("")
						.toString());
		dynavalidatorform.set("rpzDOB",
				(new StringBuilder()).append(rpzvo.getDobPermitNumber())
						.append("").toString());
		dynavalidatorform.set("rpzDEP",
				(new StringBuilder()).append(rpzvo.getDepPermitNumber())
						.append("").toString());
		dynavalidatorform.set(
				"rpzYear",
				(new StringBuilder()).append(rpzvo.getYearInstalled())
						.append("").toString());
		dynavalidatorform.set("rpzComment",
				(new StringBuilder()).append(rpzvo.getComments()).append("")
						.toString());
		dynavalidatorform.set("rpzType",
				(new StringBuilder()).append(rpzvo.getType()).append("")
						.toString());
		dynavalidatorform.set("DisconnectedYear",
				(new StringBuilder()).append(rpzvo.getDisconnectedYear())
						.append("").toString());
		dynavalidatorform.set(
				"ModifiedInPast",
				(new StringBuilder()).append(rpzvo.getModifiedInPast())
						.append("").toString());
		if (userAction.equalsIgnoreCase("edit"))
			dynavalidatorform.set("psLocation",
					(new StringBuilder()).append(rpzvo.getLocation())
							.append("").toString());
		else if (rpzvo.getLocation() != -1)
			dynavalidatorform
					.set("psLocation",
							(new StringBuilder())
									.append(AgencyLocationEnum.get(rpzvo
											.getLocation())).append("")
									.toString());
		else
			dynavalidatorform.set("psLocation", "");
		dynavalidatorform.set("dobsignoff", rpzvo.getDobsignoff());
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		try {
			java.util.List list = RpzEntity.getInstallationInfo(rpzvo.getId());
			httpservletrequest.setAttribute("RPZ_INSTALLATION_LST", list);
			java.util.List list1 = RpzEntity.getPermitList(rpzvo.getId());
			// if(list1 != null)
			httpservletrequest.setAttribute("RPZ_LOC_LST", list1);
			// else
			// httpservletrequest.setAttribute("RPZ_LOC_LST", null);
		} catch (Exception exception) {
			log.error((new StringBuilder()).append("Rpz.setFormVariable : ")
					.append(exception.toString()).toString());
		}
		try {
			setScreenInfo(httpservletrequest);
			reset(httpservletrequest);
		} catch (Exception exception1) {
		}
		String s;
		Object obj;
		for (Enumeration enumeration = httpservletrequest.getAttributeNames(); enumeration
				.hasMoreElements(); log.debug((new StringBuilder()).append(s)
				.append(":").append(obj).toString())) {
			s = (String) enumeration.nextElement();
			obj = httpservletrequest.getAttribute(s);
		}

		return dynavalidatorform;
	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, RpzVo rpzvo,
			HttpServletRequest httpservletrequest) {
		dynavalidatorform.set("rpzId",
				(new StringBuilder()).append(rpzvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform.set("rpzSerialNo",
				(new StringBuilder()).append(rpzvo.getSerialNumber())
						.append("").toString());
		dynavalidatorform.set("rpzMake",
				(new StringBuilder()).append(rpzvo.getMake()).append("")
						.toString());
		dynavalidatorform.set("rpzModel",
				(new StringBuilder()).append(rpzvo.getModel()).append("")
						.toString());
		dynavalidatorform.set("Permit", checkNullAndFill(rpzvo.isPermit(), ""));
		// dynavalidatorform.set("Permit", rpzvo.isPermit() ? "Y" : "N");

		/*
		 * if(rpzvo.getSize()!=-99) dynavalidatorform.set("rpzSize", (new
		 * StringBuilder()).append(rpzvo.getSize()).append("").toString()); else
		 * dynavalidatorform.set("rpzSize","");
		 */
		dynavalidatorform.set("rpzSize",
				(new StringBuilder()).append(rpzvo.getSize()).append("")
						.toString());
		dynavalidatorform.set("rpzDOB",
				(new StringBuilder()).append(rpzvo.getDobPermitNumber())
						.append("").toString());
		dynavalidatorform.set("rpzDEP",
				(new StringBuilder()).append(rpzvo.getDepPermitNumber())
						.append("").toString());
		dynavalidatorform.set(
				"rpzYear",
				(new StringBuilder()).append(rpzvo.getYearInstalled())
						.append("").toString());
		dynavalidatorform.set("rpzComment",
				(new StringBuilder()).append(rpzvo.getComments()).append("")
						.toString());
		dynavalidatorform.set("rpzType",
				(new StringBuilder()).append(rpzvo.getType()).append("")
						.toString());
		dynavalidatorform.set("DisconnectedYear",
				(new StringBuilder()).append(rpzvo.getDisconnectedYear())
						.append("").toString());
		dynavalidatorform.set(
				"ModifiedInPast",
				(new StringBuilder()).append(rpzvo.getModifiedInPast())
						.append("").toString());
		if (userAction.equalsIgnoreCase("viewexist"))
			dynavalidatorform.set("psLocation",
					(new StringBuilder()).append(rpzvo.getLocation())
							.append("").toString());
		else if (rpzvo.getLocation() != -1)
			dynavalidatorform
					.set("psLocation",
							(new StringBuilder())
									.append(AgencyLocationEnum.get(rpzvo
											.getLocation())).append("")
									.toString());
		else
			dynavalidatorform.set("psLocation", "");

		dynavalidatorform.set("dobsignoff", rpzvo.getDobsignoff());
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		try {
			java.util.List list = RpzEntity.getInstallationInfo(rpzvo.getId());
			httpservletrequest.setAttribute("RPZ_INSTALLATION_LST", list);
			java.util.List list1 = RpzEntity.getPermitList(rpzvo.getId());
			// if(list1 != null)
			httpservletrequest.setAttribute("RPZ_LOC_LST", list1);
			// else
			// httpservletrequest.setAttribute("RPZ_LOC_LST", null);
		} catch (Exception exception) {
			log.error((new StringBuilder()).append("Rpz.setFormVariable : ")
					.append(exception.toString()).toString());
		}
		try {
			setScreenInfoforsearch(httpservletrequest);
		} catch (Exception exception1) {
		}
		String s;
		Object obj;
		for (Enumeration enumeration = httpservletrequest.getAttributeNames(); enumeration
				.hasMoreElements(); log.debug((new StringBuilder()).append(s)
				.append(":").append(obj).toString())) {
			s = (String) enumeration.nextElement();
			obj = httpservletrequest.getAttribute(s);
		}

		return dynavalidatorform;
	}

	public ActionForward installation(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction: Inside Installation");
		log.debug("RpzAction - In installationInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = "";
		// String ss = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.DOB.getCode();
		// ss = (String)dynavalidatorform.get("rpzDohInspected");
		s1 = (String) dynavalidatorform.get("rpzLastInspDate");
		s2 = (String) dynavalidatorform.get("rpzNextInspDate");
		s3 = (String) dynavalidatorform.get("rpzNextInspDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		// permitinfovo.setDepPermitInspected(ss);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		int j = rpzvo.getLocation();
		// if(j == AgencyLocationEnum.RCDOH.getCode() || j ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(j));
		RpzEntity.addInstallation(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDohInspected", "");
		dynavalidatorform.set("rpzLastInspDate", "");
		dynavalidatorform.set("rpzNextInspDate", "");
		dynavalidatorform.set("rpzNextInspDateNote", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward inspectionEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("DEP Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int i = rpzvo.getLocation();
		// if(i == AgencyLocationEnum.RCDOH.getCode() || i ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		return actionmapping.findForward("success");
	}

	public ActionForward inspectionDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction: Edit  Installation");
		log.debug("RpzAction - In Edit installationInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("RpzAction - In updateDepPermitInfo - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Inspection Permit Id is null while updating the Inspection Permit");
		// String ss = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		// permitinfovo.setDepPermitInspected(ss);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		int k = rpzvo.getLocation();
		// if(k == AgencyLocationEnum.RCDOH.getCode() || k ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(k));
		RpzEntity.deleteInstallation(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDohInspected", "");
		dynavalidatorform.set("rpzLastInspDate", "");
		dynavalidatorform.set("rpzNextInspDate", "");
		dynavalidatorform.set("rpzNextInspDateNote", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward inspectionUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction: Edit  Installation");
		log.debug("RpzAction - In Edit installationInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("RpzAction - In updateDepPermitInfo - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Inspection Permit Id is null while updating the Inspection Permit");
		// String ss = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";

		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		// ss = (String)dynavalidatorform.get("rpzDohInspected");
		s2 = (String) dynavalidatorform.get("rpzLastInspDate");
		s3 = (String) dynavalidatorform.get("rpzNextInspDate");
		s4 = (String) dynavalidatorform.get("rpzNextInspDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		// permitinfovo.setDepPermitInspected(ss);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		int k = rpzvo.getLocation();
		// if(k == AgencyLocationEnum.RCDOH.getCode() || k ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(k));
		RpzEntity.updateInstallation(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDohInspected", "");
		dynavalidatorform.set("rpzLastInspDate", "");
		dynavalidatorform.set("rpzNextInspDate", "");
		dynavalidatorform.set("rpzNextInspDateNote", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward statePermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction - In StateInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = "";
		// String ss= "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.STATE_AGENCY.getCode();
		// ss = (String)dynavalidatorform.get("rpzDepInspected");
		s1 = (String) dynavalidatorform.get("rpzLocIssueDate");
		s2 = (String) dynavalidatorform.get("rpzLocExpirationDate");
		s3 = (String) dynavalidatorform.get("rpzLocExpirationDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		// permitinfovo.setDepPermitInspected(ss);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		int j = rpzvo.getLocation();
		// if(j == AgencyLocationEnum.RCDOH.getCode() || j ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		RpzEntity.addPermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDepInspected", "");
		dynavalidatorform.set("rpzLocIssueDate", "");
		dynavalidatorform.set("rpzLocExpirationDate", "");
		dynavalidatorform.set("rpzLocExpirationDateNote", "");
		dynavalidatorform.set("rpzLocNumber", "");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward stateEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("State Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_LOC_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int i = rpzvo.getLocation();
		// if(i == AgencyLocationEnum.RCDOH.getCode() || i ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(rpzvo.getLocation()));
		return actionmapping.findForward("success");
	}

	public ActionForward stateDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction - In StateDelete");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("RpzAction - In deleteDepPermitInfo - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the Rpz DEP Permit");
		// String ss = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.STATE_AGENCY.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		permitinfovo.setId(Integer.parseInt(s));
		int k = rpzvo.getLocation();
		// if(k == AgencyLocationEnum.RCDOH.getCode() || k ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		RpzEntity.deletePermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDepInspected", "");
		dynavalidatorform.set("rpzLocIssueDate", "");
		dynavalidatorform.set("rpzLocExpirationDate", "");
		dynavalidatorform.set("rpzLocExpirationDateNote", "");
		dynavalidatorform.set("rpzLocNumber", "");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward stateUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction - In StateUpdate");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("RpzAction - In updateDepPermitInfo - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the Rpz DEP Permit");
		// String ss = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.STATE_AGENCY.getCode();
		// ss = (String)dynavalidatorform.get("rpzDepInspected");
		s2 = (String) dynavalidatorform.get("rpzLocIssueDate");
		s3 = (String) dynavalidatorform.get("rpzLocExpirationDate");
		s4 = (String) dynavalidatorform.get("rpzLocExpirationDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		// permitinfovo.setDepPermitInspected(ss);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		int k = rpzvo.getLocation();
		// if(k == AgencyLocationEnum.RCDOH.getCode() || k ==
		// AgencyLocationEnum.WCDOH.getCode())
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		// else
		// httpservletrequest.setAttribute("RPZ_LOC", "none");
		RpzEntity.updatePermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDepInspected", "");
		dynavalidatorform.set("rpzLocIssueDate", "");
		dynavalidatorform.set("rpzLocExpirationDate", "");
		dynavalidatorform.set("rpzLocExpirationDateNote", "");
		dynavalidatorform.set("rpzLocNumber", "");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest)

	{

		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		try {

			java.util.List list = RpzEntity.getPermitList(rpzvo.getId());
			// if(list != null)
			httpservletrequest.setAttribute("RPZ_LOC_LST", list);
			// else
			// httpservletrequest.setAttribute("RPZ_LOC_LST", null);
			java.util.List list1 = RpzEntity.getInstallationInfo(rpzvo.getId());
			if (list1 != null)
				httpservletrequest.setAttribute("RPZ_INSTALLATION_LST", list1);
			else
				httpservletrequest.setAttribute("RPZ_INSTALLATION_LST", null);

		} catch (Exception e) {
		}

		if (rpzvo == null) {
			log.debug("RPZ Object is null");
			return;
		} else {
			return;
		}
	}

	public ActionForward returnTo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RETURN");
		return actionmapping.findForward("goToAddBuilding");
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RpzAction:DisplayControl");
		System.out.println("Test:"
				+ httpservletrequest.getParameter("hdnContext"));
		String s = httpservletrequest.getParameter("hdnContext");
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			System.out.println("Test Good:");

			httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
			httpservletrequest.setAttribute("RPZ_LOC", "inline");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("showAddBtn", "Y");
			refreshPermitInfo(httpservletrequest);
			reset(httpservletrequest);
		} else {
			httpservletrequest.setAttribute("showAddBtn", "N");
		}

		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("rpzId", "");
		dynavalidatorform.set("rpzYear", "");
		dynavalidatorform.set("rpzSerialNo", "");
		dynavalidatorform.set("rpzMake", "");
		dynavalidatorform.set("rpzSize", "");
		dynavalidatorform.set("ModifiedInPast", "");
		dynavalidatorform.set("DisconnectedYear", "");
		dynavalidatorform.set("rpzModel", "");
		dynavalidatorform.set("rpzType", "");
		dynavalidatorform.set("Permit", "");
		dynavalidatorform.set("psLocation", "");
		dynavalidatorform.set("rpzDOB", "");
		dynavalidatorform.set("rpzDEP", "");
		// dynavalidatorform.set("rpzDepInspected", "");
		dynavalidatorform.set("rpzLocIssueDate", "");
		dynavalidatorform.set("rpzLocExpirationDate", "");
		dynavalidatorform.set("rpzLocExpirationDateNote", "");
		dynavalidatorform.set("rpzLocNumber", "");
		// dynavalidatorform.set("rpzDohInspected", "");
		dynavalidatorform.set("rpzLastInspDate", "");
		dynavalidatorform.set("rpzNextInspDate", "");
		dynavalidatorform.set("dobsignoff", "");
		httpservletrequest.setAttribute("RPZ_LOC", "none");
		httpservletrequest.setAttribute("RPZ_INSPEC", "none");
		httpservletrequest.removeAttribute("RPZ_LOC_LST");
		httpservletrequest.removeAttribute("RPZ_INSTALLATION_LST");
		httpservletrequest.setAttribute("LOCATION", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		setDropDown(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward state(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RpzAction - In statePermitInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		String s = "";
		// String ss = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.STATE_AGENCY.getCode();
		// ss = (String)dynavalidatorform.get("rpzDepInspected");
		s1 = (String) dynavalidatorform.get("rpzLocIssueDate");
		s2 = (String) dynavalidatorform.get("rpzLocExpirationDate");
		s3 = (String) dynavalidatorform.get("rpzLocExpirationDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(rpzvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.RPZ.getCode());
		// permitinfovo.setDepPermitInspected(ss);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		dynavalidatorform = setFormVariable(dynavalidatorform, rpzvo,
				httpservletrequest);
		// dynavalidatorform.set("rpzDepInspected", "");
		dynavalidatorform.set("rpzLocIssueDate", "");
		dynavalidatorform.set("rpzLocExpirationDate", "");
		dynavalidatorform.set("rpzLocExpirationDateNote", "");
		httpservletrequest.setAttribute("RPZ_INSPEC", "inline");
		httpservletrequest.setAttribute("RPZ_LOC", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = AgencyLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("AGENCY_LOCATION", dropdown);
		dropdown = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEAR", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown1);
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		RpzVo rpzvo = (RpzVo) httpsession.getAttribute("RPZ_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/rpz/"
					+ rpzvo.getId() + "-"
					+ rpzvo.getFacilityDesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/rpz/"
					+ rpzvo.getId() + "-"
					+ rpzvo.getFacilityDesignatedId().trim();
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
				+ "/rpz/" + rpzvo.getId() + "-"
				+ rpzvo.getFacilityDesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.RpzAction.class);
	public static HashMap AGENCY_LOCATION = new HashMap();
	public static HashMap YEAR = new HashMap();
	public String userAction;

}