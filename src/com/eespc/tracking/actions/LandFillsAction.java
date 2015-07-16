package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
import org.apache.struts.util.LabelValueBean;
import org.apache.struts.validator.DynaValidatorForm;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.CogenTurbineVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.LandfillVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.myenum.AgencyLocationEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.entity.CogenTurbineEntity;
import com.eespc.tracking.entity.LandfillEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class LandFillsAction extends LookupDispatchAction {

	public LandFillsAction() {
		userAction = null;
	}

	protected Map getKeyMethodMap() {
		HashMap hashmap = new HashMap();
		hashmap.put("landFillsform.add", "add");
		hashmap.put("landFillsform.save", "save");
		hashmap.put("landFillsform.reset", "reset");
		hashmap.put("landFillsform.dep", "dep");
		hashmap.put("landFillsform.update", "update");
		hashmap.put("landFillsform.delete", "delete");
		hashmap.put("landFillsform.state", "state");
		hashmap.put("landFillsform.loc", "loc");
		hashmap.put("landFillsform.addcogen", "addcogen");
		return hashmap;
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		setDropDown(httpservletrequest);
		userAction = (String) dynavalidatorform.get("methodToCall");
		log.debug((new StringBuilder()).append("LandFillsAction:execute() ")
				.append(userAction).toString());
		if (userAction != null && userAction.equalsIgnoreCase("Save"))
			return save(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Return"))
			return add(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("reset"))
			return reset(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("state"))
			return state(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("dep"))
			return dep(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("edit"))
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("view"))
			return view(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("viewexist"))
			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Update"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Delete"))
			return delete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("loc"))
			return commonPermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locEdit"))
			return commonPermitEdit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depEdit"))
			return depEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depDelete"))
			return depDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateEdit"))
			return stateEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locUpdate"))
			return commonPermitUpdate(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depUpdate"))
			return depUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateDelete"))
			return stateDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("stateUpdate")) {
			return stateUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
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
			dynavalidatorform.set("lfId", "");
			dynavalidatorform.set("psLocation", "");
			// dynavalidatorform.set("lfDEPNumber", "");
			dynavalidatorform.set("lfDEPIssueDate", "");
			dynavalidatorform.set("lfDEPExpirationDate", "");
			dynavalidatorform.set("lfStateIssueDate", "");
			dynavalidatorform.set("lfStateNumber", "");
			dynavalidatorform.set("lfStateExpirationDate", "");
			dynavalidatorform.set("lfLocIssueDate", "");
			dynavalidatorform.set("lfLocExpirationDate", "");
			dynavalidatorform.set("lfLocNumber", "");
			dynavalidatorform.set("lfDEPNumber", "");
			dynavalidatorform.set("lfDOBNumber", "");
			dynavalidatorform.set("lfDOBIssuedate", "");
			dynavalidatorform.set("lfLFGQuantity", "");
			dynavalidatorform.set("LFGQuantity", "");
			dynavalidatorform.set("lfLFGCombustion", "");
			dynavalidatorform.set("LFGCombustion", "");
			dynavalidatorform.set("lfSCH4", "");
			dynavalidatorform.set("svalue", "");
			dynavalidatorform.set("sallowablevalue", "");
			dynavalidatorform.set("ch4value", "");
			dynavalidatorform.set("ch4allowablevalue", "");
			dynavalidatorform.set("istemperaturelimit", "");
			dynavalidatorform.set("temperaturevalue", "");
			dynavalidatorform.set("temperatureallowablevalue", "");
			dynavalidatorform.set("noxvalue", "");
			dynavalidatorform.set("noxallowablevalue", "");
			dynavalidatorform.set("covalue", "");
			dynavalidatorform.set("coallowablevalue", "");
			dynavalidatorform.set("ovalue", "");
			dynavalidatorform.set("oallowablevalue", "");
			dynavalidatorform.set("yearlyoperating", "");
			dynavalidatorform.set("yearlyvalue", "");
			dynavalidatorform.set("yearlyallowablevalue", "");
			dynavalidatorform.set("lcomments", "");
			dynavalidatorform.set("aesdate", "");
			dynavalidatorform.set("dobsignoff", "");
			log.debug("StorageTankAction - In Execute");
			httpservletrequest.setAttribute("LFILLS_DEP", "none");
			httpservletrequest.setAttribute("LFILLS_STATE", "none");
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
			httpservletrequest.setAttribute("LFILL_DEP_LST", null);
			httpservletrequest.setAttribute("LFILL_LOC_LST", null);
			httpservletrequest.setAttribute("LFILL_STATE_LST", null);
			httpservletrequest.setAttribute("TURBINE", null);
			httpservletrequest.setAttribute("SELECTED_TURBINE", null);
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
		LandfillVo landfillvo = new LandfillVo();
		landfillvo = (LandfillVo) httpsession.getAttribute("LANDFILL_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, landfillvo);

		setScreenInfo(httpservletrequest);
		setFormVariable(dynaactionform, landfillvo, httpservletrequest,
				httpsession);

		// httpservletrequest.setAttribute("SELECTED_TURBINE",httpservletrequest.getAttribute("SELECTED_TURBINE"));
		// httpservletrequest.setAttribute("COGENLIST",httpservletrequest.getAttribute("COGENLIST"));

		// refreshShowInfo(httpservletrequest, landfillvo);
		refreshPermitInfo(httpservletrequest);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		httpservletrequest.setAttribute("SELECTED_TURBINE",
				httpsession.getAttribute("SELECTED_TURBINE"));
		httpservletrequest.setAttribute("COGENLIST",
				httpsession.getAttribute("COGENLIST"));
		setCogenList(httpservletrequest, httpsession);

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
		LandfillVo landfillvo = new LandfillVo();
		landfillvo = (LandfillVo) httpsession.getAttribute("LANDFILL_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, landfillvo);
		setFormVariable(dynaactionform, landfillvo, httpservletrequest,
				httpsession);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, landfillvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		httpservletrequest.setAttribute("SELECTED_TURBINE",
				httpsession.getAttribute("SELECTED_TURBINE"));
		httpservletrequest.setAttribute("COGENLIST",
				httpsession.getAttribute("COGENLIST"));
		setCogenList(httpservletrequest, httpsession);

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
		LandfillVo landfillvo = new LandfillVo();
		landfillvo = (LandfillVo) httpsession.getAttribute("LANDFILL_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, landfillvo);
		setFormVariable(dynaactionform, landfillvo, httpservletrequest,
				httpsession);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, landfillvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		httpservletrequest.setAttribute("SELECTED_TURBINE",
				httpsession.getAttribute("SELECTED_TURBINE"));
		httpservletrequest.setAttribute("COGENLIST",
				httpsession.getAttribute("COGENLIST"));
		setCogenList(httpservletrequest, httpsession);

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
		LandfillVo landfillvo = new LandfillVo();
		landfillvo = (LandfillVo) httpsession.getAttribute("LANDFILL_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, landfillvo);
		setFormVariable(dynaactionform, landfillvo, httpservletrequest,
				httpsession);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, landfillvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		httpservletrequest.setAttribute("SELECTED_TURBINE",
				httpsession.getAttribute("SELECTED_TURBINE"));
		httpservletrequest.setAttribute("COGENLIST",
				httpsession.getAttribute("COGENLIST"));
		setCogenList(httpservletrequest, httpsession);

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
		// DynaValidatorForm dynaactionform = (DynaValidatorForm)actionform;

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = new LandfillVo();
		landfillvo = (LandfillVo) httpsession.getAttribute("LANDFILL_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, landfillvo);
		setFormVariable(dynaactionform, landfillvo, httpservletrequest,
				httpsession);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, landfillvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		httpservletrequest.setAttribute("SELECTED_TURBINE",
				httpsession.getAttribute("SELECTED_TURBINE"));
		httpservletrequest.setAttribute("COGENLIST",
				httpsession.getAttribute("COGENLIST"));
		setCogenList(httpservletrequest, httpsession);

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
		LandfillVo landfillvo = new LandfillVo();
		landfillvo = (LandfillVo) httpsession.getAttribute("LANDFILL_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, landfillvo);
		refreshPermitInfo(httpservletrequest);
		setFormVariable(dynaactionform, landfillvo, httpservletrequest,
				httpsession);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		httpservletrequest.setAttribute("SELECTED_TURBINE",
				httpsession.getAttribute("SELECTED_TURBINE"));
		httpservletrequest.setAttribute("COGENLIST",
				httpsession.getAttribute("COGENLIST"));
		setCogenList(httpservletrequest, httpsession);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/landfill/"
				+ landfillvo.getId() + "-"
				+ landfillvo.getFacilityDesignatedId().trim())) {
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
		HttpSession httpsession = httpservletrequest.getSession();
		System.out.println("Landfill Action - In displayControl1 ");
		String s = httpservletrequest.getParameter("hdnContext");
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {

			httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
			httpservletrequest.setAttribute("showAddBtn", "Y");
			httpservletrequest.setAttribute("SELECTED_TURBINE",
					httpservletrequest.getAttribute("SELECTED_TURBINE"));
			httpservletrequest.setAttribute("COGENLIST",
					httpservletrequest.getAttribute("COGENLIST"));
			httpservletrequest.setAttribute("SHOW_BUTTONS",
					httpservletrequest.getAttribute("SHOW_BUTTONS"));
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS",
					httpservletrequest.getAttribute("SHOW_UPDATE_BUTTONS"));
			setCogenList(httpservletrequest, httpsession);
		} else {
			httpservletrequest.setAttribute("isItForEdit", "N");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
			httpservletrequest.setAttribute("showAddBtn", "N");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
			httpservletrequest.setAttribute("SELECTED_TURBINE",
					httpservletrequest.getAttribute("SELECTED_TURBINE"));
			httpservletrequest.setAttribute("COGENLIST",
					httpservletrequest.getAttribute("COGENLIST"));
			httpservletrequest.setAttribute("SHOW_BUTTONS",
					httpservletrequest.getAttribute("SHOW_BUTTONS"));
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS",
					httpservletrequest.getAttribute("SHOW_UPDATE_BUTTONS"));
			setCogenList(httpservletrequest, httpsession);
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		System.out
				.println("Landfill Action - In displayControl1wertwe4rwerwer");
		return actionmapping.findForward("success");
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("save");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		LandfillVo landfillvo = new LandfillVo();
		landfillvo.setBuildingId(buildingvo.getId());
		landfillvo.setCommencedDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform.get("lfDate")),
				"yyyy-MM-dd"));
		landfillvo.setYearinstalled((String) dynavalidatorform
				.get("lf_yearInstalled"));
		landfillvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("lf_status")));
		landfillvo.setDisconnecteddate((String) dynavalidatorform
				.get("lf_disconnecteddate"));
		String as[] = (String[]) (String[]) dynavalidatorform
				.get("lfCoTurbines");
		ArrayList arraylist = new ArrayList();
		try {
			arraylist = new ArrayList();
			for (int i = 0; i < as.length; i++) {
				arraylist.add(as[i]);
				log.debug((new StringBuilder()).append("lanfill add ")
						.append(as[i]).toString());
			}

			landfillvo.setCogenOrTurbineList(arraylist);
		} catch (Exception exception) {
			log.error((new StringBuilder()).append("landfill cogen error ")
					.append(exception.toString()).toString(), exception);
		}
		String s2 = (String) dynavalidatorform.get("psLocation");
		if (UtilityObject.isNotEmpty(s2))
			landfillvo.setLocation(Integer.parseInt((String) dynavalidatorform
					.get("psLocation")));
		if (s2.equals(String.valueOf(AgencyLocationEnum.RCDOH.getCode()))
				|| s2.equals(String.valueOf(AgencyLocationEnum.RCDOH.getCode())))
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		s2 = (String) dynavalidatorform.get("lfFacility");
		if (s2.equals("Y"))
			landfillvo.setListedAsTitleV(true);
		else
			landfillvo.setListedAsTitleV(false);
		s2 = (String) dynavalidatorform.get("lfTurbines");
		if (s2.equals("Y"))
			landfillvo.setLinkedToCogenOrTurbine(true);
		else
			landfillvo.setLinkedToCogenOrTurbine(false);
		s2 = (String) dynavalidatorform.get("lfEmission");
		if (s2.equals("Y"))
			landfillvo.setAnnualStmtSubmited(true);
		else
			landfillvo.setAnnualStmtSubmited(false);
		landfillvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("lfId"));

		landfillvo
				.setLfDEPNumber((String) dynavalidatorform.get("lfDEPNumber"));
		landfillvo
				.setLfDOBNumber((String) dynavalidatorform.get("lfDOBNumber"));
		landfillvo.setLfDOBIssuedate(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynavalidatorform
						.get("lfDOBIssuedate")), "yyyy-MM-dd"));
		landfillvo.setLfLFGQuantity((String) dynavalidatorform
				.get("lfLFGQuantity"));
		landfillvo
				.setLFGQuantity((String) dynavalidatorform.get("LFGQuantity"));
		landfillvo.setLfLFGCombustion((String) dynavalidatorform
				.get("lfLFGCombustion"));
		landfillvo.setLFGCombustion((String) dynavalidatorform
				.get("LFGCombustion"));
		landfillvo.setLfSCH4((String) dynavalidatorform.get("lfSCH4"));
		landfillvo.setSvalue((String) dynavalidatorform.get("svalue"));
		landfillvo.setSallowablevalue((String) dynavalidatorform
				.get("sallowablevalue"));
		landfillvo.setCh4value((String) dynavalidatorform.get("ch4value"));
		landfillvo.setCh4allowablevalue((String) dynavalidatorform
				.get("ch4allowablevalue"));
		landfillvo.setIstemperaturelimit((String) dynavalidatorform
				.get("istemperaturelimit"));
		landfillvo.setTemperaturevalue((String) dynavalidatorform
				.get("temperaturevalue"));
		landfillvo.setTemperatureallowablevalue((String) dynavalidatorform
				.get("temperatureallowablevalue"));
		landfillvo.setNoxvalue((String) dynavalidatorform.get("noxvalue"));
		landfillvo.setNoxallowablevalue((String) dynavalidatorform
				.get("noxallowablevalue"));
		landfillvo.setCovalue((String) dynavalidatorform.get("covalue"));
		landfillvo.setCoallowablevalue((String) dynavalidatorform
				.get("coallowablevalue"));
		landfillvo.setOvalue((String) dynavalidatorform.get("ovalue"));
		landfillvo.setOallowablevalue((String) dynavalidatorform
				.get("oallowablevalue"));
		landfillvo.setYearlyoperating((String) dynavalidatorform
				.get("yearlyoperating"));
		landfillvo
				.setYearlyvalue((String) dynavalidatorform.get("yearlyvalue"));
		landfillvo.setYearlyallowablevalue((String) dynavalidatorform
				.get("yearlyallowablevalue"));
		landfillvo.setLcomments((String) dynavalidatorform.get("lcomments"));
		landfillvo.setAesdate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform.get("aesdate")),
				"yyyy-MM-dd"));
		landfillvo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));

		byte byte0 = -99;
		setCogenList(httpservletrequest, httpsession);
		try {
			int j = LandfillEntity.add(landfillvo);
			landfillvo = LandfillEntity.findByPrimaryKey(j);
			if (landfillvo != null) {
				LandfillEntity.addCogenOrTurbine(landfillvo.getId(), arraylist);
				httpsession.setAttribute("LANDFILL_OBJECT", landfillvo);
				dynavalidatorform = setFormVariable(dynavalidatorform,
						landfillvo, httpservletrequest, httpsession);
			}
			s = "Added LandFill Successfully.";
			s1 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "N");
			httpservletrequest.setAttribute("isItForEdit", "Y");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		int k = landfillvo.getLocation();
		if (k == AgencyLocationEnum.RCDOH.getCode()
				|| k == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(k));
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("LFILL_STATE_LST", null);
		httpservletrequest.setAttribute("LFILL_LOC_LST", null);
		httpservletrequest.setAttribute("LFILL_DEP_LST", null);
		httpservletrequest.setAttribute("LFILLS_TURBINE", "inline");
		httpservletrequest.setAttribute("TURBINE", null);
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillAction - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		LandfillVo landfillvo = LandfillEntity.findByPrimaryKey(i);
		if (landfillvo != null)
			httpsession.setAttribute("LANDFILL_OBJECT", landfillvo);
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		LandfillVo landfillvo1 = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		try {
			LandfillEntity.delete(landfillvo1);
			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/landfill/" + landfillvo1.getId() + "-"
						+ landfillvo1.getFacilityDesignatedId().trim());
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
		}
		return actionmapping.findForward("successland");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandfillAction - In Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String facilityDesignatedid = landfillvo.getFacilityDesignatedId();
		log.debug((new StringBuilder())
				.append("LandfillsAction.update() ID = ")
				.append(landfillvo.getId()).toString());
		landfillvo.setBuildingId(buildingvo.getId());
		landfillvo.setCommencedDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform.get("lfDate")),
				"yyyy-MM-dd"));
		landfillvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("lfId"));
		landfillvo.setYearinstalled((String) dynavalidatorform
				.get("lf_yearInstalled"));
		landfillvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("lf_status")));
		landfillvo.setDisconnecteddate((String) dynavalidatorform
				.get("lf_disconnecteddate"));
		String as[] = (String[]) (String[]) dynavalidatorform
				.get("lfCoTurbines");
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < as.length; i++)
			arraylist.add(as[i]);

		landfillvo.setCogenOrTurbineList(arraylist);
		String s2 = (String) dynavalidatorform.get("psLocation");
		if (UtilityObject.isNotEmpty(s2))
			landfillvo.setLocation(Integer.parseInt((String) dynavalidatorform
					.get("psLocation")));
		if (s2.equals(String.valueOf(AgencyLocationEnum.RCDOH.getCode()))
				|| s2.equals(String.valueOf(AgencyLocationEnum.RCDOH.getCode())))
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		s2 = (String) dynavalidatorform.get("lfFacility");
		if (s2.equals("Y"))
			landfillvo.setListedAsTitleV(true);
		else
			landfillvo.setListedAsTitleV(false);
		s2 = (String) dynavalidatorform.get("lfTurbines");
		if (s2.equals("Y"))
			landfillvo.setLinkedToCogenOrTurbine(true);
		else
			landfillvo.setLinkedToCogenOrTurbine(false);
		s2 = (String) dynavalidatorform.get("lfEmission");
		if (s2.equals("Y"))
			landfillvo.setAnnualStmtSubmited(true);
		else
			landfillvo.setAnnualStmtSubmited(false);

		landfillvo
				.setLfDEPNumber((String) dynavalidatorform.get("lfDEPNumber"));
		landfillvo
				.setLfDOBNumber((String) dynavalidatorform.get("lfDOBNumber"));
		landfillvo.setLfDOBIssuedate(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynavalidatorform
						.get("lfDOBIssuedate")), "yyyy-MM-dd"));
		landfillvo.setLfLFGQuantity((String) dynavalidatorform
				.get("lfLFGQuantity"));
		landfillvo
				.setLFGQuantity((String) dynavalidatorform.get("LFGQuantity"));
		landfillvo.setLfLFGCombustion((String) dynavalidatorform
				.get("lfLFGCombustion"));
		landfillvo.setLFGCombustion((String) dynavalidatorform
				.get("LFGCombustion"));
		landfillvo.setLfSCH4((String) dynavalidatorform.get("lfSCH4"));
		landfillvo.setSvalue((String) dynavalidatorform.get("svalue"));
		landfillvo.setSallowablevalue((String) dynavalidatorform
				.get("sallowablevalue"));
		landfillvo.setCh4value((String) dynavalidatorform.get("ch4value"));
		landfillvo.setCh4allowablevalue((String) dynavalidatorform
				.get("ch4allowablevalue"));
		landfillvo.setIstemperaturelimit((String) dynavalidatorform
				.get("istemperaturelimit"));
		landfillvo.setTemperaturevalue((String) dynavalidatorform
				.get("temperaturevalue"));
		landfillvo.setTemperatureallowablevalue((String) dynavalidatorform
				.get("temperatureallowablevalue"));
		landfillvo.setNoxvalue((String) dynavalidatorform.get("noxvalue"));
		landfillvo.setNoxallowablevalue((String) dynavalidatorform
				.get("noxallowablevalue"));
		landfillvo.setCovalue((String) dynavalidatorform.get("covalue"));
		landfillvo.setCoallowablevalue((String) dynavalidatorform
				.get("coallowablevalue"));
		landfillvo.setOvalue((String) dynavalidatorform.get("ovalue"));
		landfillvo.setOallowablevalue((String) dynavalidatorform
				.get("oallowablevalue"));
		landfillvo.setYearlyoperating((String) dynavalidatorform
				.get("yearlyoperating"));
		landfillvo
				.setYearlyvalue((String) dynavalidatorform.get("yearlyvalue"));
		landfillvo.setYearlyallowablevalue((String) dynavalidatorform
				.get("yearlyallowablevalue"));
		landfillvo.setLcomments((String) dynavalidatorform.get("lcomments"));
		landfillvo.setAesdate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform.get("aesdate")),
				"yyyy-MM-dd"));
		landfillvo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));

		byte byte0 = -99;
		setCogenList(httpservletrequest, httpsession);
		try {
			LandfillEntity.update(landfillvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/landfill/" + landfillvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/landfill/"
									+ landfillvo.getId()
									+ "-"
									+ landfillvo.getFacilityDesignatedId()
											.trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			int j = LandfillEntity.updateCogenOrTurbine(landfillvo.getId(),
					arraylist);
			httpsession.setAttribute("LANDFILL_OBJECT", landfillvo);
			dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
					httpservletrequest, httpsession);
			s = "Updated LandFills Successfully.";
			s1 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "N");
			httpservletrequest.setAttribute("isItForEdit", "Y");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
			s = "Error updating the LandFills.";
			s1 = "ERROR";
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		} else {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
			return actionmapping.findForward("success");
		}
		int k = landfillvo.getLocation();
		if (k == AgencyLocationEnum.RCDOH.getCode()
				|| k == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(k));
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("LFILL_STATE_LST", null);
		httpservletrequest.setAttribute("LFILL_LOC_LST", null);
		httpservletrequest.setAttribute("LFILL_DEP_LST", null);
		httpservletrequest.setAttribute("TURBINE", null);
		refreshPermitInfo(httpservletrequest);
		List list = (List) httpservletrequest.getAttribute("LFILL_DEP_LST");
		if (list != null && list.size() > 0) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list.get(0);
			// dynavalidatorform.set("lfDEPNumber",
			// permitinfovo.getPermitNumber());
		}
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public DynaValidatorForm setFormVariable(
			DynaValidatorForm dynavalidatorform, LandfillVo landfillvo,
			HttpServletRequest httpservletrequest, HttpSession httpsession) {

		try {
			dynavalidatorform.set(
					"lfId",
					(new StringBuilder())
							.append(landfillvo.getFacilityDesignatedId())
							.append("").toString());
			dynavalidatorform.set(
					"lfDate",
					(new StringBuilder())
							.append(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(landfillvo
											.getCommencedDate())).append("")
							.toString());
			dynavalidatorform.set(
					"lf_yearInstalled",
					(new StringBuilder())
							.append(checkNullAndFill(
									landfillvo.getYearinstalled(), ""))
							.append("").toString());
			TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
					.get(landfillvo.getStatus());
			if (tankoperatingstatusenum != null)
				dynavalidatorform.set("lf_status",
						tankoperatingstatusenum.getName());
			dynavalidatorform.set(
					"lf_disconnecteddate",
					(new StringBuilder())
							.append(checkNullAndFill(
									landfillvo.getDisconnecteddate(), ""))
							.append("").toString());

			if (userAction.equalsIgnoreCase("edit"))
				dynavalidatorform
						.set("psLocation",
								(new StringBuilder())
										.append(landfillvo.getLocation())
										.append("").toString());
			else if (landfillvo.getLocation() != -1)
				dynavalidatorform.set(
						"psLocation",
						(new StringBuilder())
								.append(AgencyLocationEnum.get(landfillvo
										.getLocation())).append("").toString());
			else
				dynavalidatorform.set("psLocation", "");

			boolean flag = landfillvo.isListedAsTitleV();
			if (flag)
				dynavalidatorform.set("lfFacility", "Y");
			else
				dynavalidatorform.set("lfFacility", "N");
			flag = landfillvo.isLinkedToCogenOrTurbine();
			if (flag) {
				dynavalidatorform.set("lfTurbines", "Y");
				httpservletrequest.setAttribute("LFILLS_TURBINE", "inline");
			} else {
				dynavalidatorform.set("lfTurbines", "N");
				httpservletrequest.setAttribute("LFILLS_TURBINE", "none");
			}
			flag = landfillvo.isAnnualStmtSubmited();
			if (flag)
				dynavalidatorform.set("lfEmission", "Y");
			else
				dynavalidatorform.set("lfEmission", "N");

			dynavalidatorform.set("lfDEPNumber", landfillvo.getLfDEPNumber());
			dynavalidatorform.set("lfDOBNumber", landfillvo.getLfDOBNumber());
			dynavalidatorform.set("lfDOBIssuedate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(landfillvo.getLfDOBIssuedate()));
			dynavalidatorform.set("lfLFGQuantity",
					landfillvo.getLfLFGQuantity());
			dynavalidatorform.set("LFGQuantity", landfillvo.getLFGQuantity());
			dynavalidatorform.set("lfLFGCombustion",
					landfillvo.getLfLFGCombustion());
			dynavalidatorform.set("LFGCombustion",
					landfillvo.getLFGCombustion());
			dynavalidatorform.set("lfSCH4", landfillvo.getLfSCH4());
			dynavalidatorform.set("svalue", landfillvo.getSvalue());
			dynavalidatorform.set("sallowablevalue",
					landfillvo.getSallowablevalue());
			dynavalidatorform.set("ch4value", landfillvo.getCh4value());
			dynavalidatorform.set("ch4allowablevalue",
					landfillvo.getCh4allowablevalue());
			dynavalidatorform.set("istemperaturelimit",
					landfillvo.getIstemperaturelimit());
			dynavalidatorform.set("temperaturevalue",
					landfillvo.getTemperaturevalue());
			dynavalidatorform.set("temperatureallowablevalue",
					landfillvo.getTemperatureallowablevalue());
			dynavalidatorform.set("noxvalue", landfillvo.getNoxvalue());
			dynavalidatorform.set("noxallowablevalue",
					landfillvo.getNoxallowablevalue());
			dynavalidatorform.set("covalue", landfillvo.getCovalue());
			dynavalidatorform.set("coallowablevalue",
					landfillvo.getCoallowablevalue());
			dynavalidatorform.set("ovalue", landfillvo.getOvalue());
			dynavalidatorform.set("oallowablevalue",
					landfillvo.getOallowablevalue());
			dynavalidatorform.set("yearlyoperating",
					landfillvo.getYearlyoperating());
			dynavalidatorform.set("yearlyvalue", landfillvo.getYearlyvalue());
			dynavalidatorform.set("yearlyallowablevalue",
					landfillvo.getYearlyallowablevalue());
			dynavalidatorform.set("lcomments", landfillvo.getLcomments());
			dynavalidatorform.set("aesdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(landfillvo.getAesdate()));
			dynavalidatorform.set("dobsignoff", landfillvo.getDobsignoff());
			System.out.println("cogenstart:1");
			try {
				ArrayList arraylist = (ArrayList) httpservletrequest
						.getAttribute("COGENLIST");
				List list1 = landfillvo.getCogenOrTurbineList();
				ArrayList arraylist1 = new ArrayList();
				log.debug((new StringBuilder()).append("Size = ")
						.append(list1.size()).toString());
				String as[] = new String[list1.size()];

				if (userAction.equalsIgnoreCase("edit")) {
					for (int i = 0; i < list1.size(); i++) {
						as[i] = (String) list1.get(i);
					}

				} else {

					for (int j = 0; j < list1.size(); j++) {

						String s1 = (String) list1.get(j);

						LabelValueBean labelvaluebean = null;

						int k = 0;
						do {

							if (k >= arraylist.size())
								break;

							labelvaluebean = (LabelValueBean) arraylist.get(k);

							if (labelvaluebean.getValue().trim()
									.equals(s1.trim()))
								break;
							k++;
						} while (true);

						as[j] = labelvaluebean.getLabel();
						arraylist1.add(labelvaluebean.getLabel());

					}

				}

				dynavalidatorform.set("lfCoTurbines", as);
				httpservletrequest.setAttribute("SELECTED_TURBINE", arraylist1);
			} catch (Exception exception1) {
				System.out.println("setFormVariable" + exception1);
				log.debug((new StringBuilder())
						.append("Error. setFormVariable()")
						.append(exception1.toString()).toString());
			}

			setScreenInfo(httpservletrequest);

			String s;
			Object obj;
			for (Enumeration enumeration = httpservletrequest
					.getAttributeNames(); enumeration.hasMoreElements(); log
					.debug((new StringBuilder()).append(s).append(":")
							.append(obj).toString())) {
				s = (String) enumeration.nextElement();
				obj = httpservletrequest.getAttribute(s);
			}

		} catch (Exception exception) {
			System.out.println("Error Landfills.setFormVariable:" + exception);
			log.error(
					(new StringBuilder())
							.append("Error Landfills.setFormVariable.")
							.append(exception.toString()).toString(), exception);
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");

		return dynavalidatorform;
	}

	public DynaValidatorForm setFormVariableforedit(
			DynaValidatorForm dynavalidatorform, LandfillVo landfillvo,
			HttpServletRequest httpservletrequest, HttpSession httpsession) {
		log.debug("Inside setFormVariable");
		try {
			setCogenList(httpservletrequest, httpsession);
			dynavalidatorform.set(
					"lfId",
					(new StringBuilder())
							.append(landfillvo.getFacilityDesignatedId())
							.append("").toString());
			dynavalidatorform.set(
					"lfDate",
					(new StringBuilder())
							.append(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(landfillvo
											.getCommencedDate())).append("")
							.toString());
			dynavalidatorform.set(
					"lf_yearInstalled",
					(new StringBuilder())
							.append(checkNullAndFill(
									landfillvo.getYearinstalled(), ""))
							.append("").toString());
			dynavalidatorform.set("lf_status",
					(new StringBuilder()).append(landfillvo.getStatus())
							.append("").toString());
			dynavalidatorform.set(
					"lf_disconnecteddate",
					(new StringBuilder())
							.append(checkNullAndFill(
									landfillvo.getDisconnecteddate(), ""))
							.append("").toString());
			if (userAction.equalsIgnoreCase("edit"))
				dynavalidatorform
						.set("psLocation",
								(new StringBuilder())
										.append(landfillvo.getLocation())
										.append("").toString());
			else
				dynavalidatorform.set(
						"psLocation",
						(new StringBuilder())
								.append(AgencyLocationEnum.get(landfillvo
										.getLocation())).append("").toString());
			log.debug("Inside setFormVariable 1");
			boolean flag = landfillvo.isListedAsTitleV();
			if (flag)
				dynavalidatorform.set("lfFacility", "Y");
			else
				dynavalidatorform.set("lfFacility", "N");
			flag = landfillvo.isLinkedToCogenOrTurbine();
			if (flag) {
				dynavalidatorform.set("lfTurbines", "Y");
				httpservletrequest.setAttribute("LFILLS_TURBINE", "inline");
			} else {
				dynavalidatorform.set("lfTurbines", "N");
				httpservletrequest.setAttribute("LFILLS_TURBINE", "none");
			}
			flag = landfillvo.isAnnualStmtSubmited();
			if (flag)
				dynavalidatorform.set("lfEmission", "Y");
			else
				dynavalidatorform.set("lfEmission", "N");

			dynavalidatorform.set("lfDEPNumber", landfillvo.getLfDEPNumber());
			dynavalidatorform.set("lfDOBNumber", landfillvo.getLfDOBNumber());
			dynavalidatorform.set("lfDOBIssuedate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(landfillvo.getLfDOBIssuedate()));
			dynavalidatorform.set("lfLFGQuantity",
					landfillvo.getLfLFGQuantity());
			dynavalidatorform.set("LFGQuantity", landfillvo.getLFGQuantity());
			dynavalidatorform.set("lfLFGCombustion",
					landfillvo.getLfLFGCombustion());
			dynavalidatorform.set("LFGCombustion",
					landfillvo.getLFGCombustion());
			dynavalidatorform.set("lfSCH4", landfillvo.getLfSCH4());
			dynavalidatorform.set("svalue", landfillvo.getSvalue());
			dynavalidatorform.set("sallowablevalue",
					landfillvo.getSallowablevalue());
			dynavalidatorform.set("ch4value", landfillvo.getCh4value());
			dynavalidatorform.set("ch4allowablevalue",
					landfillvo.getCh4allowablevalue());
			dynavalidatorform.set("istemperaturelimit",
					landfillvo.getIstemperaturelimit());
			dynavalidatorform.set("temperaturevalue",
					landfillvo.getTemperaturevalue());
			dynavalidatorform.set("temperatureallowablevalue",
					landfillvo.getTemperatureallowablevalue());
			dynavalidatorform.set("noxvalue", landfillvo.getNoxvalue());
			dynavalidatorform.set("noxallowablevalue",
					landfillvo.getNoxallowablevalue());
			dynavalidatorform.set("covalue", landfillvo.getCovalue());
			dynavalidatorform.set("coallowablevalue",
					landfillvo.getCoallowablevalue());
			dynavalidatorform.set("ovalue", landfillvo.getOvalue());
			dynavalidatorform.set("oallowablevalue",
					landfillvo.getOallowablevalue());
			dynavalidatorform.set("yearlyoperating",
					landfillvo.getYearlyoperating());
			dynavalidatorform.set("yearlyvalue", landfillvo.getYearlyvalue());
			dynavalidatorform.set("yearlyallowablevalue",
					landfillvo.getYearlyallowablevalue());
			dynavalidatorform.set("lcomments", landfillvo.getLcomments());
			dynavalidatorform.set("aesdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(landfillvo.getAesdate()));
			dynavalidatorform.set("dobsignoff", landfillvo.getDobsignoff());

			/*
			 * log.debug("Inside setFormVariable 2"); List list =
			 * (List)httpservletrequest.getAttribute("LFILL_DEP_LST"); if(list
			 * != null && list.size() > 0) { PermitInfoVo permitinfovo =
			 * (PermitInfoVo)list.get(0); //dynavalidatorform.set("lfDEPNumber",
			 * permitinfovo.getPermitNumber()); }
			 */
			try {
				ArrayList arraylist = (ArrayList) httpservletrequest
						.getAttribute("COGENLIST");
				List list1 = landfillvo.getCogenOrTurbineList();
				ArrayList arraylist1 = new ArrayList();
				log.debug((new StringBuilder()).append("Size = ")
						.append(list1.size()).toString());
				String as[] = new String[list1.size()];
				if (userAction.equalsIgnoreCase("edit")) {
					for (int i = 0; i < list1.size(); i++) {
						as[i] = (String) list1.get(i);
						log.debug((new StringBuilder())
								.append("cogen items = ").append(as[i])
								.toString());
					}

				} else {
					for (int j = 0; j < list1.size(); j++) {
						String s1 = (String) list1.get(j);
						log.debug((new StringBuilder()).append("Str = ")
								.append(s1).toString());
						LabelValueBean labelvaluebean = null;
						log.debug((new StringBuilder())
								.append("List Co sieze ")
								.append(arraylist.size()).append("").toString());
						int k = 0;
						do {
							if (k >= arraylist.size())
								break;
							log.debug(arraylist.get(k).toString());
							labelvaluebean = (LabelValueBean) arraylist.get(k);
							if (labelvaluebean.getValue().trim()
									.equals(s1.trim()))
								break;
							k++;
						} while (true);
						as[j] = labelvaluebean.getLabel();
						arraylist1.add(labelvaluebean.getLabel());
						log.debug((new StringBuilder())
								.append("cogen items = ").append(as[j])
								.toString());
					}

				}
				log.debug("Inside setFormVariable 3");
				dynavalidatorform.set("lfCoTurbines", as);

				httpservletrequest.setAttribute("SELECTED_TURBINE", arraylist1);
			} catch (Exception exception1) {
				log.debug((new StringBuilder())
						.append("Error. setFormVariable()")
						.append(exception1.toString()).toString());
			}

			setScreenInfo(httpservletrequest);

			String s;
			Object obj;
			for (Enumeration enumeration = httpservletrequest
					.getAttributeNames(); enumeration.hasMoreElements(); log
					.debug((new StringBuilder()).append(s).append(":")
							.append(obj).toString())) {
				s = (String) enumeration.nextElement();
				obj = httpservletrequest.getAttribute(s);
			}

		} catch (Exception exception) {
			log.error(
					(new StringBuilder())
							.append("Error Landfills.setFormVariable.")
							.append(exception.toString()).toString(), exception);
		}
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		try {
			reset(httpservletrequest);
		}

		catch (Exception exception) {
		}
		return dynavalidatorform;
	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, LandfillVo landfillvo,
			HttpServletRequest httpservletrequest, HttpSession httpsession) {
		log.debug("Inside setFormVariable");
		try {
			setCogenList(httpservletrequest, httpsession);
			dynavalidatorform.set(
					"lfId",
					(new StringBuilder())
							.append(landfillvo.getFacilityDesignatedId())
							.append("").toString());
			dynavalidatorform.set(
					"lfDate",
					(new StringBuilder())
							.append(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(landfillvo
											.getCommencedDate())).append("")
							.toString());
			dynavalidatorform.set(
					"lf_yearInstalled",
					(new StringBuilder())
							.append(checkNullAndFill(
									landfillvo.getYearinstalled(), ""))
							.append("").toString());
			dynavalidatorform.set("lf_status",
					(new StringBuilder()).append(landfillvo.getStatus())
							.append("").toString());
			dynavalidatorform.set(
					"lf_disconnecteddate",
					(new StringBuilder())
							.append(checkNullAndFill(
									landfillvo.getDisconnecteddate(), ""))
							.append("").toString());
			if (userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform
						.set("psLocation",
								(new StringBuilder())
										.append(landfillvo.getLocation())
										.append("").toString());
			else
				dynavalidatorform.set(
						"psLocation",
						(new StringBuilder())
								.append(AgencyLocationEnum.get(landfillvo
										.getLocation())).append("").toString());
			log.debug("Inside setFormVariable 1");
			boolean flag = landfillvo.isListedAsTitleV();
			if (flag)
				dynavalidatorform.set("lfFacility", "Y");
			else
				dynavalidatorform.set("lfFacility", "N");
			flag = landfillvo.isLinkedToCogenOrTurbine();
			if (flag) {
				dynavalidatorform.set("lfTurbines", "Y");
				httpservletrequest.setAttribute("LFILLS_TURBINE", "inline");
			} else {
				dynavalidatorform.set("lfTurbines", "N");
				httpservletrequest.setAttribute("LFILLS_TURBINE", "none");
			}
			flag = landfillvo.isAnnualStmtSubmited();
			if (flag)
				dynavalidatorform.set("lfEmission", "Y");
			else
				dynavalidatorform.set("lfEmission", "N");
			dynavalidatorform.set("lfDEPNumber", landfillvo.getLfDEPNumber());
			dynavalidatorform.set("lfDOBNumber", landfillvo.getLfDOBNumber());
			dynavalidatorform.set("lfDOBIssuedate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(landfillvo.getLfDOBIssuedate()));
			dynavalidatorform.set("lfLFGQuantity",
					landfillvo.getLfLFGQuantity());
			dynavalidatorform.set("LFGQuantity", landfillvo.getLFGQuantity());
			dynavalidatorform.set("lfLFGCombustion",
					landfillvo.getLfLFGCombustion());
			dynavalidatorform.set("LFGCombustion",
					landfillvo.getLFGCombustion());
			dynavalidatorform.set("lfSCH4", landfillvo.getLfSCH4());
			dynavalidatorform.set("svalue", landfillvo.getSvalue());
			dynavalidatorform.set("sallowablevalue",
					landfillvo.getSallowablevalue());
			dynavalidatorform.set("ch4value", landfillvo.getCh4value());
			dynavalidatorform.set("ch4allowablevalue",
					landfillvo.getCh4allowablevalue());
			dynavalidatorform.set("istemperaturelimit",
					landfillvo.getIstemperaturelimit());
			dynavalidatorform.set("temperaturevalue",
					landfillvo.getTemperaturevalue());
			dynavalidatorform.set("temperatureallowablevalue",
					landfillvo.getTemperatureallowablevalue());
			dynavalidatorform.set("noxvalue", landfillvo.getNoxvalue());
			dynavalidatorform.set("noxallowablevalue",
					landfillvo.getNoxallowablevalue());
			dynavalidatorform.set("covalue", landfillvo.getCovalue());
			dynavalidatorform.set("coallowablevalue",
					landfillvo.getCoallowablevalue());
			dynavalidatorform.set("ovalue", landfillvo.getOvalue());
			dynavalidatorform.set("oallowablevalue",
					landfillvo.getOallowablevalue());
			dynavalidatorform.set("yearlyoperating",
					landfillvo.getYearlyoperating());
			dynavalidatorform.set("yearlyvalue", landfillvo.getYearlyvalue());
			dynavalidatorform.set("yearlyallowablevalue",
					landfillvo.getYearlyallowablevalue());
			dynavalidatorform.set("lcomments", landfillvo.getLcomments());
			dynavalidatorform.set("aesdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(landfillvo.getAesdate()));
			dynavalidatorform.set("dobsignoff", landfillvo.getDobsignoff());
			log.debug("Inside setFormVariable 2");
			List list = (List) httpservletrequest.getAttribute("LFILL_DEP_LST");
			if (list != null && list.size() > 0) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list.get(0);
				// dynavalidatorform.set("lfDEPNumber",
				// permitinfovo.getPermitNumber());
			}
			try {
				ArrayList arraylist = (ArrayList) httpservletrequest
						.getAttribute("COGENLIST");
				List list1 = landfillvo.getCogenOrTurbineList();
				ArrayList arraylist1 = new ArrayList();
				log.debug((new StringBuilder()).append("Size = ")
						.append(list1.size()).toString());
				String as[] = new String[list1.size()];
				if (userAction.equalsIgnoreCase("viewexist")) {
					for (int i = 0; i < list1.size(); i++) {
						as[i] = (String) list1.get(i);
						log.debug((new StringBuilder())
								.append("cogen items = ").append(as[i])
								.toString());
					}

				} else {
					for (int j = 0; j < list1.size(); j++) {
						String s1 = (String) list1.get(j);
						log.debug((new StringBuilder()).append("Str = ")
								.append(s1).toString());
						LabelValueBean labelvaluebean = null;
						log.debug((new StringBuilder())
								.append("List Co sieze ")
								.append(arraylist.size()).append("").toString());
						int k = 0;
						do {
							if (k >= arraylist.size())
								break;
							log.debug(arraylist.get(k).toString());
							labelvaluebean = (LabelValueBean) arraylist.get(k);
							if (labelvaluebean.getValue().trim()
									.equals(s1.trim()))
								break;
							k++;
						} while (true);
						as[j] = labelvaluebean.getLabel();
						arraylist1.add(labelvaluebean.getLabel());
						log.debug((new StringBuilder())
								.append("cogen items = ").append(as[j])
								.toString());
					}

				}
				log.debug("Inside setFormVariable 3");
				dynavalidatorform.set("lfCoTurbines", as);
				httpservletrequest.setAttribute("SELECTED_TURBINE", arraylist1);
			} catch (Exception exception1) {
				log.debug((new StringBuilder())
						.append("Error. setFormVariable()")
						.append(exception1.toString()).toString());
			}
			log.debug("Inside setFormVariable 4");
			setScreenInfoforsearch(httpservletrequest);
			log.debug("Inside setFormVariable 5");
			String s;
			Object obj;
			for (Enumeration enumeration = httpservletrequest
					.getAttributeNames(); enumeration.hasMoreElements(); log
					.debug((new StringBuilder()).append(s).append(":")
							.append(obj).toString())) {
				s = (String) enumeration.nextElement();
				obj = httpservletrequest.getAttribute(s);
			}

		} catch (Exception exception) {
			log.error(
					(new StringBuilder())
							.append("Error Landfills.setFormVariable.")
							.append(exception.toString()).toString(), exception);
		}
		return dynavalidatorform;
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("DEP Edit");
		return actionmapping.findForward("return");
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest) {
		System.out.println("setScreenInfo:test3");
		try {
			/*
			 * boolean flag =
			 * UtilityObject.isBoroughValidInNyc(httpservletrequest); if(flag)
			 * httpservletrequest.setAttribute("is5Borough", "Y"); boolean flag1
			 * = UtilityObject.isFacilityInNy(httpservletrequest); if(flag1)
			 * httpservletrequest.setAttribute("isInNy", "Y");
			 */
			HttpSession httpsession = httpservletrequest.getSession();
			LandfillVo landfillvo = (LandfillVo) httpsession
					.getAttribute("LANDFILL_OBJECT");
			System.out.println("showAddBtn" + landfillvo);
			if (landfillvo != null)
				httpservletrequest.setAttribute("showAddBtn", "Y");
			else
				httpservletrequest.setAttribute("showAddBtn", "N");
		} catch (Exception exception) {
			System.out.println("setScreenInfo" + exception);
		}
	}

	private void setScreenInfoforsearch(HttpServletRequest httpservletrequest) {
		try {
			boolean flag = UtilityObject
					.isBoroughValidInNyc(httpservletrequest);
			if (flag)
				httpservletrequest.setAttribute("is5Borough", "Y");
			boolean flag1 = UtilityObject.isFacilityInNy(httpservletrequest);
			if (flag1)
				httpservletrequest.setAttribute("isInNy", "Y");
		} catch (Exception exception) {
		}
	}

	public ActionForward loc(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("LOCATION");
		return actionmapping.findForward("return");
	}

	public ActionForward addcogen(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("Add Cogen");
		return actionmapping.findForward("return");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("lfId", "");
		dynavalidatorform.set("lfDate", "");
		dynavalidatorform.set("lf_yearInstalled", "");
		dynavalidatorform.set("lf_status", "");
		dynavalidatorform.set("lf_disconnecteddate", "");
		// dynavalidatorform.set("lfDEPNumber", "");
		dynavalidatorform.set("lfDEPIssueDate", "");
		dynavalidatorform.set("lfDEPExpirationDate", "");
		dynavalidatorform.set("lfStateIssueDate", "");
		dynavalidatorform.set("lfStateNumber", "");
		dynavalidatorform.set("lfStateExpirationDate", "");
		dynavalidatorform.set("lfLocIssueDate", "");
		dynavalidatorform.set("lfLocExpirationDate", "");
		dynavalidatorform.set("lfLocNumber", "");
		dynavalidatorform.set("lfDEPNumber", "");
		dynavalidatorform.set("lfDOBNumber", "");
		dynavalidatorform.set("lfDOBIssuedate", "");
		dynavalidatorform.set("lfLFGQuantity", "");
		dynavalidatorform.set("LFGQuantity", "");
		dynavalidatorform.set("lfLFGCombustion", "");
		dynavalidatorform.set("LFGCombustion", "");
		dynavalidatorform.set("lfSCH4", "");
		dynavalidatorform.set("svalue", "");
		dynavalidatorform.set("sallowablevalue", "");
		dynavalidatorform.set("ch4value", "");
		dynavalidatorform.set("ch4allowablevalue", "");
		dynavalidatorform.set("istemperaturelimit", "");
		dynavalidatorform.set("temperaturevalue", "");
		dynavalidatorform.set("temperatureallowablevalue", "");
		dynavalidatorform.set("noxvalue", "");
		dynavalidatorform.set("noxallowablevalue", "");
		dynavalidatorform.set("covalue", "");
		dynavalidatorform.set("coallowablevalue", "");
		dynavalidatorform.set("ovalue", "");
		dynavalidatorform.set("oallowablevalue", "");
		dynavalidatorform.set("yearlyoperating", "");
		dynavalidatorform.set("yearlyvalue", "");
		dynavalidatorform.set("yearlyallowablevalue", "");
		dynavalidatorform.set("lcomments", "");
		dynavalidatorform.set("aesdate", "");
		dynavalidatorform.set("dobsignoff", "");
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.setAttribute("LANDFILL_OBJECT", "");
		httpservletrequest.setAttribute("LFILL_STATE_LST", null);
		httpservletrequest.setAttribute("LFILL_LOC_LST", null);
		httpservletrequest.setAttribute("LFILL_DEP_LST", null);
		httpservletrequest.setAttribute("LFILLS_TURBINE", "none");
		httpservletrequest.setAttribute("LFILLS_DEP", "none");
		httpservletrequest.setAttribute("LFILLS_STATE", "none");
		httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("TURBINE", null);
		httpservletrequest.setAttribute("SELECTED_TURBINE", null);

		setDropDown(httpservletrequest);
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	/*
	 * public String datetoMMDDYYYY(String s) { String s1 = null; String s2 =
	 * null; String s3 = null; if(s.length() == 10) { int i = s.indexOf("-"); s3
	 * = s.substring(0, i); s1 = s.substring(i + 1, s.lastIndexOf("-")); s2 =
	 * s.substring(s.lastIndexOf("-") + 1); } return (new
	 * StringBuilder()).append
	 * (s1).append("-").append(s2).append("-").append(s3).toString(); }
	 */
	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = AgencyLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("AGENCY_LOCATION", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown1);
	}

	private void setCogenList(HttpServletRequest httpservletrequest,
			HttpSession httpsession) {
		Object obj = null;
		byte byte0 = -99;
		Object obj1 = null;
		httpservletrequest.setAttribute("COGENLIST", null);
		try {
			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			if (facilityvo == null) {
				httpservletrequest.setAttribute("COGENLIST", null);
			} else {
				List list = CogenTurbineEntity.getCogenTurbineList(facilityvo
						.getId());
				ArrayList arraylist = new ArrayList(list.size());
				for (int j = 0; j < list.size(); j++) {
					CogenTurbineVo cogenturbinevo = (CogenTurbineVo) list
							.get(j);
					int i = cogenturbinevo.getId();
					String s = cogenturbinevo.getFacilityDesignatedId();
					arraylist.add(new LabelValueBean(s, (new StringBuilder())
							.append(i).append("").toString()));
				}

				httpservletrequest.setAttribute("COGENLIST", arraylist);
			}
		} catch (Exception exception) {
			System.out.println("GetCoGenList Error:" + exception);
			log.error((new StringBuilder()).append("GetCoGenList Error: ")
					.append(exception.toString()).toString(), exception);
			httpservletrequest.setAttribute("COGENLIST", null);
		}
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillAction - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		LandfillVo landfillvo = LandfillEntity.findByPrimaryKey(i);
		if (landfillvo != null)
			httpsession.setAttribute("LANDFILL_OBJECT", landfillvo);
		else
			log.debug((new StringBuilder())
					.append("Unable to get LandFillVo for id(").append(s)
					.append(")").toString());
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("TURBINE", null);
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillAction - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		LandfillVo landfillvo = LandfillEntity.findByPrimaryKey(i);
		if (landfillvo != null)
			httpsession.setAttribute("LANDFILL_OBJECT", landfillvo);
		else
			log.debug((new StringBuilder())
					.append("Unable to get LandFillVo for id(").append(s)
					.append(")").toString());
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariableforedit(dynavalidatorform,
				landfillvo, httpservletrequest, httpsession);
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		int j = landfillvo.getLocation();
		if (j == AgencyLocationEnum.RCDOH.getCode()
				|| j == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("TURBINE", null);
		httpservletrequest.setAttribute("SELECTED_TURBINE",
				landfillvo.getCogenOrTurbineList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		refreshPermitInfo(httpservletrequest);
		try {
			List list = (List) httpservletrequest.getAttribute("LFILL_DEP_LST");
			if (list != null && list.size() > 0) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list.get(0);
				// dynavalidatorform.set("lfDEPNumber",
				// permitinfovo.getPermitNumber());
			}
		} catch (Exception exception) {
			log.error((new StringBuilder()).append("Landfills Dep List Error ")
					.append(exception.toString()).toString(), exception);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillAction - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		LandfillVo landfillvo = LandfillEntity.findByPrimaryKey(i);
		if (landfillvo != null)
			httpsession.setAttribute("LANDFILL_OBJECT", landfillvo);
		else
			log.debug((new StringBuilder())
					.append("Unable to get LandFillVo for id(").append(s)
					.append(")").toString());
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
				landfillvo, httpservletrequest, httpsession);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("TURBINE", null);
		httpservletrequest.setAttribute("SELECTED_TURBINE",
				landfillvo.getCogenOrTurbineList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward state(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillAction - In statePermitInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = -99;
		i = DepartmentEnum.STATE_AGENCY.getCode();
		s = (String) dynavalidatorform.get("lfStateNumber");
		s1 = (String) dynavalidatorform.get("lfStateIssueDate");
		s2 = (String) dynavalidatorform.get("lfStateExpirationDate");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		LandfillEntity.addPermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfStateIssueDate", "");
		dynavalidatorform.set("lfStateExpirationDate", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("LANDFILL_OBJECT", landfillvo);
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int j = landfillvo.getLocation();
		if (j == AgencyLocationEnum.RCDOH.getCode()
				|| j == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		refreshPermitInfo(httpservletrequest);
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward stateEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("State Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("lfStateNumber");
		String s1 = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STATE_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		if (s != null)
			dynavalidatorform.set("lfStateNumber", s);
		if (s1 != null && s1.trim().equalsIgnoreCase("")) {
			String s2 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s2);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s1);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int i = landfillvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward stateDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("State Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		j = DepartmentEnum.STATE_AGENCY.getCode();
		s1 = (String) dynavalidatorform.get("lfStateNumber");
		s2 = (String) dynavalidatorform.get("lfStateIssueDate");
		s3 = (String) dynavalidatorform.get("lfStateExpirationDate");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setId(Integer.parseInt(s));
		LandfillEntity.deletePermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfStateIssueDate", "");
		dynavalidatorform.set("lfStateExpirationDate", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("LANDFILL_OBJECT", landfillvo);
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int k = landfillvo.getLocation();
		if (k == AgencyLocationEnum.RCDOH.getCode()
				|| k == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		refreshPermitInfo(httpservletrequest);
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward stateUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("State Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		j = DepartmentEnum.STATE_AGENCY.getCode();
		s1 = (String) dynavalidatorform.get("lfStateNumber");
		s2 = (String) dynavalidatorform.get("lfStateIssueDate");
		s3 = (String) dynavalidatorform.get("lfStateExpirationDate");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setId(Integer.parseInt(s));
		LandfillEntity.updatePermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfStateIssueDate", "");
		dynavalidatorform.set("lfStateExpirationDate", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("LANDFILL_OBJECT", landfillvo);
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int k = landfillvo.getLocation();
		if (k == AgencyLocationEnum.RCDOH.getCode()
				|| k == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		refreshPermitInfo(httpservletrequest);
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward commonPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillAction - In commonPermitInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = -99;
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		AddressVo addressvo = facilityvo.getFacilityAddress();
		String s3 = addressvo.getState();
		String s4 = (String) VALID_STATES.get(s3);
		log.debug((new StringBuilder()).append(s3).append(" enum =")
				.append(DepartmentEnum.parse(s4)).toString());
		if (UtilityObject.isNotEmpty(s4)) {
			DepartmentEnum departmentenum = DepartmentEnum.parse(s4);
			i = departmentenum != null ? departmentenum.getCode() : -1;
		}
		s = (String) dynavalidatorform.get("lfLocNumber");
		s1 = (String) dynavalidatorform.get("lfLocIssueDate");
		s2 = (String) dynavalidatorform.get("lfLocExpirationDate");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		LandfillEntity.addPermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfLocIssueDate", "");
		dynavalidatorform.set("lfLocExpirationDate", "");
		dynavalidatorform.set("lfLocNumber", "");
		List list = LandfillEntity.getPermitList(landfillvo.getId());
		httpservletrequest.setAttribute("LFILL_LOC_LST", list);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward commonPermitEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("CommonPermitEdit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("lfLocNumber");
		String s1 = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_LOC_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		if (s != null)
			dynavalidatorform.set("lfLocNumber", s);
		if (s1 != null && s1.trim().equalsIgnoreCase("")) {
			String s2 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s2);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s1);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		int i = landfillvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward commonPermitUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CommonPermitUpdate");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Common Permit Id is null while updating the LOC Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		AddressVo addressvo = facilityvo.getFacilityAddress();
		String s4 = addressvo.getState();
		String s5 = (String) VALID_STATES.get(s4);
		log.debug((new StringBuilder()).append(s4).append(" enum =")
				.append(DepartmentEnum.parse(s5)).toString());
		if (UtilityObject.isNotEmpty(s5)) {
			DepartmentEnum departmentenum = DepartmentEnum.parse(s5);
			j = departmentenum != null ? departmentenum.getCode() : -1;
		}
		s1 = (String) dynavalidatorform.get("lfLocNumber");
		s2 = (String) dynavalidatorform.get("lfLocIssueDate");
		s3 = (String) dynavalidatorform.get("lfLocExpirationDate");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setId(Integer.parseInt(s));
		LandfillEntity.updatePermit(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		dynavalidatorform.set("lfLocIssueDate", "");
		dynavalidatorform.set("lfLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));
		setCogenList(httpservletrequest, httpsession);
		return actionmapping.findForward("success");
	}

	public ActionForward dep(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("LandFillsAction - In Dep");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.DOB.getCode();
		s = (String) dynavalidatorform.get("lfDEPNumber");
		s1 = (String) dynavalidatorform.get("lfDEPIssueDate");
		s2 = (String) dynavalidatorform.get("lfDEPExpirationDate");
		s3 = (String) dynavalidatorform.get("lfDEPExpirationDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		LandfillEntity.addPermit(permitinfovo);
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfDEPIssueDate", "");
		dynavalidatorform.set("lfDEPExpirationDate", "");
		dynavalidatorform.set("lfDEPExpirationDateNote", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		int j = landfillvo.getLocation();
		if (j == AgencyLocationEnum.RCDOH.getCode()
				|| j == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));

		return actionmapping.findForward("success");
	}

	public ActionForward depEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("DEP Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("lfDEPNumber");
		String s1 = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		if (s != null)
			dynavalidatorform.set("lfDEPNumber", s);
		if (s1 != null && s1.trim().equalsIgnoreCase("")) {
			String s2 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s2);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s1);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		int i = landfillvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));

		return actionmapping.findForward("success");
	}

	public ActionForward depDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("DEP Update");
		log.debug("LandfillAction - In updateDepPermitInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("LandfillAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the DEP Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		s1 = (String) dynavalidatorform.get("lfDEPNumber");
		s2 = (String) dynavalidatorform.get("lfDEPIssueDate");
		s3 = (String) dynavalidatorform.get("lfDEPExpirationDate");
		s4 = (String) dynavalidatorform.get("lfDEPExpirationDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setId(Integer.parseInt(s));
		LandfillEntity.deletePermit(permitinfovo);
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfDEPIssueDate", "");
		dynavalidatorform.set("lfDEPExpirationDate", "");
		dynavalidatorform.set("lfDEPExpirationDateNote", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		int k = landfillvo.getLocation();
		if (k == AgencyLocationEnum.RCDOH.getCode()
				|| k == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));

		return actionmapping.findForward("success");
	}

	public ActionForward depUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("DEP Update");
		log.debug("LandfillAction - In updateDepPermitInfo");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("LandfillAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the DEP Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		s1 = (String) dynavalidatorform.get("lfDEPNumber");
		s2 = (String) dynavalidatorform.get("lfDEPIssueDate");
		s3 = (String) dynavalidatorform.get("lfDEPExpirationDate");
		s4 = (String) dynavalidatorform.get("lfDEPExpirationDateNote");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(landfillvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.LANDFILL.getCode());
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		LandfillEntity.updatePermit(permitinfovo);
		setCogenList(httpservletrequest, httpsession);
		dynavalidatorform = setFormVariable(dynavalidatorform, landfillvo,
				httpservletrequest, httpsession);
		dynavalidatorform.set("lfDEPIssueDate", "");
		dynavalidatorform.set("lfDEPExpirationDate", "");
		dynavalidatorform.set("lfDEPExpirationDateNote", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("LFILLS_DEP", "inline");
		httpservletrequest.setAttribute("LFILLS_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		int k = landfillvo.getLocation();
		if (k == AgencyLocationEnum.RCDOH.getCode()
				|| k == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("LFILLS_LOC", "inline");
		else
			httpservletrequest.setAttribute("LFILLS_LOC", "none");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(landfillvo.getLocation()));

		return actionmapping.findForward("success");
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {

		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		if (landfillvo == null) {
			log.debug("LandFill Object is null");
			return;
		}
		landfillvo.setPermitInfoList(null);
		List list = landfillvo.getPermitInfoList();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		ArrayList arraylist2 = new ArrayList();
		int i = list != null ? list.size() : -1;
		byte byte0 = -1;
		for (int k = 0; k < i; k++) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list.get(k);
			int j = permitinfovo.getDepId();
			if (j == DepartmentEnum.STATE_AGENCY.getCode()) {
				arraylist.add(permitinfovo);
				continue;
			}
			if (j == DepartmentEnum.DOB.getCode())
				arraylist1.add(permitinfovo);
			else
				arraylist2.add(permitinfovo);
		}

		log.debug((new StringBuilder()).append("LFILL_STATE_LST size=")
				.append(arraylist.size()).append(", LFILL_DEP_LST size=")
				.append(arraylist1.size()).append(", LFILL_LOC_LST size=")
				.append(arraylist2.size()).toString());
		httpservletrequest.setAttribute("LFILL_STATE_LST", arraylist);
		httpservletrequest.setAttribute("LFILL_DEP_LST", arraylist1);
		httpservletrequest.setAttribute("LFILL_LOC_LST", arraylist2);
		httpservletrequest.setAttribute("isItForEdit", "Y");
		System.out.println("my 4");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/landfill/"
					+ landfillvo.getId() + "-"
					+ landfillvo.getFacilityDesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/landfill/"
					+ landfillvo.getId() + "-"
					+ landfillvo.getFacilityDesignatedId().trim();
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
				+ "/landfill/" + landfillvo.getId() + "-"
				+ landfillvo.getFacilityDesignatedId().trim());

	}

	public void setformvariable(String path, ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		// System.out.println("Facility Id: "+facilityvo.getDecId());
		// System.out.println("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest.getRealPath("/"));
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
					// System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					System.out.println("1b");
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					// System.out.println("Directory " +
					// listOfFiles[i].getName());
				}
				folderlist.add(arr);
				// System.out.println("2");

			}

		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", path);
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

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.LandFillsAction.class);
	public static HashMap VALID_STATES;
	public String userAction;

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