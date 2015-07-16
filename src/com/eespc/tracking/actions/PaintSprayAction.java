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
import org.apache.struts.validator.DynaValidatorForm;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingManager;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.LandfillVo;
import com.eespc.tracking.bo.PaintSprayVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.myenum.AgencyLocationEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.SprayBoothChemicalsEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.entity.PaintSprayEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class PaintSprayAction extends LookupDispatchAction {

	public PaintSprayAction() {
		userAction = null;
	}

	protected Map getKeyMethodMap() {
		HashMap hashmap = new HashMap();
		hashmap.put("paintsprayform.add", "add");
		hashmap.put("paintsprayform.save", "save");
		hashmap.put("paintsprayform.reset", "reset");
		hashmap.put("paintsprayform.dep", "dep");
		hashmap.put("paintsprayform.loc", "location");
		hashmap.put("paintsprayform.state", "state");
		hashmap.put("paintsprayform.delete", "delete");
		return hashmap;
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, Exception {
		DynaValidatorForm dynavalidatorform;
		HttpSession httpsession = httpservletrequest.getSession();
		dynavalidatorform = (DynaValidatorForm) actionform;
		userAction = (String) dynavalidatorform.get("methodToCall");
		setDropDown(httpservletrequest);
		if (userAction != null && userAction.equalsIgnoreCase("Save"))
			return save(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Update"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Delete"))
			return delete(actionmapping, actionform, httpservletrequest,
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
		if (userAction != null && userAction.equalsIgnoreCase("dep"))
			return dep(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("state"))
			return state(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("loc"))
			return location(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Return"))
			return add(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Reset"))
			return reset(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locEdit"))
			return locEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locDelete"))
			return locDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depEdit"))
			return depEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateEdit"))
			return stateEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depDelete"))
			return depDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("stateDelete"))
			return stateDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depUpdate"))
			return depUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locUpdate"))
			return locUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

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

		try {
			if (userAction != null
					&& userAction.equalsIgnoreCase("stateUpdate"))
				return stateUpdate(actionmapping, actionform,
						httpservletrequest, httpservletresponse);
		} catch (Exception exception) {
		}
		dynavalidatorform.set("psId", "");
		dynavalidatorform.set("psFloor", "");
		dynavalidatorform.set("ps_yearInstalled", "");
		dynavalidatorform.set("ps_status", "");
		dynavalidatorform.set("ps_disconnecteddate", "");
		dynavalidatorform.set("psMake", "");
		dynavalidatorform.set("psModel", "");
		dynavalidatorform.set("psActionTaken", "");
		dynavalidatorform.set("psHoursOfOperation", "");
		dynavalidatorform.set("psTypesOfChemical", null);
		dynavalidatorform.set("psIncludeByDec", "");
		dynavalidatorform.set("psLocation", "");
		dynavalidatorform.set("psExemptedbyDec", "");
		dynavalidatorform.set("psDEPNumber", "");
		dynavalidatorform.set("psDEPIssueDate", "");
		dynavalidatorform.set("psDEPExpirationDate", "");
		dynavalidatorform.set("psStateIssueDate", "");
		dynavalidatorform.set("psStateNumber", "");
		dynavalidatorform.set("psStateNumber", "");
		dynavalidatorform.set("psLocIssueDate", "");
		dynavalidatorform.set("psLocExpirationDate", "");
		dynavalidatorform.set("psLocNumber", "");
		dynavalidatorform.set("psDEPNumber", "");
		dynavalidatorform.set("spraypaint", "");
		dynavalidatorform.set("spraysolvant", "");
		dynavalidatorform.set("sprayink", "");
		dynavalidatorform.set("sprayother", "");
		dynavalidatorform.set("spraypaintname", "");
		dynavalidatorform.set("spraysolvantname", "");
		dynavalidatorform.set("sprayinkname", "");
		dynavalidatorform.set("sprayothername", "");
		dynavalidatorform.set("spraypaintvocpercent", "");
		dynavalidatorform.set("spraysolvantvocpercent", "");
		dynavalidatorform.set("sprayinkvocpercent", "");
		dynavalidatorform.set("sprayothervocpercent", "");
		dynavalidatorform.set("spraypaintvolume", "");
		dynavalidatorform.set("spraysolvantvolume", "");
		dynavalidatorform.set("sprayinkvolume", "");
		dynavalidatorform.set("sprayothervolume", "");
		dynavalidatorform.set("spraypaintdensity", "");
		dynavalidatorform.set("spraysolvantdensity", "");
		dynavalidatorform.set("sprayinkdensity", "");
		dynavalidatorform.set("sprayotherdensity", "");
		dynavalidatorform.set("spraypaintvoc", "");
		dynavalidatorform.set("spraysolvantvoc", "");
		dynavalidatorform.set("sprayinkvoc", "");
		dynavalidatorform.set("sprayothervoc", "");
		dynavalidatorform.set("spraypaintcap", "");
		dynavalidatorform.set("spraysolvantcap", "");
		dynavalidatorform.set("sprayinkcap", "");
		dynavalidatorform.set("vocyear", "");
		dynavalidatorform.set("vocmonth", "");
		dynavalidatorform.set("voccap", "");
		dynavalidatorform.set("vocmonthlyquantity", "");
		dynavalidatorform.set("vocmonthlylimit", "");
		dynavalidatorform.set("psHoursOfOperationyear", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("dobnumber", "");
		httpservletrequest.setAttribute("PSPRAY_DEP", "none");
		httpservletrequest.setAttribute("PSPRAY_STATE", "none");
		httpservletrequest.setAttribute("PSPRAY_LOC", "none");
		httpservletrequest.setAttribute("PSPRAY_DEP_LST", null);
		httpservletrequest.setAttribute("PSPRAY_LOC_LST", null);
		httpservletrequest.setAttribute("PSPRAY_STATE_LST", null);
		httpservletrequest.setAttribute("LOCATION", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		return actionmapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, paintsprayvo);
		setScreenInfo(httpservletrequest);
		setFormVariable(dynaactionform, paintsprayvo, httpservletrequest);

		// refreshShowInfo(httpservletrequest, paintsprayvo);
		// refreshPermitInfo(httpservletrequest);
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
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		setFormVariable(dynaactionform, paintsprayvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, paintsprayvo);
		// refreshPermitInfo(httpservletrequest);
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
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		setFormVariable(dynaactionform, paintsprayvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, paintsprayvo);
		// refreshPermitInfo(httpservletrequest);
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
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		setFormVariable(dynaactionform, paintsprayvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, paintsprayvo);
		// refreshPermitInfo(httpservletrequest);
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
		// DynaValidatorForm dynaactionform = (DynaValidatorForm)actionform;

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		setFormVariable(dynaactionform, paintsprayvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, paintsprayvo);
		// refreshPermitInfo(httpservletrequest);
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
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		setFormVariable(dynaactionform, paintsprayvo, httpservletrequest);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/paintspray/"
				+ paintsprayvo.getId() + "-"
				+ paintsprayvo.getFacilityDesignatedId().trim())) {
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
			httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
			httpservletrequest.setAttribute("PSPRAY_STATE", "inline");

		} else {
			httpservletrequest.setAttribute("isItForEdit", "N");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
			httpservletrequest.setAttribute("showAddBtn", "N");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		}
		// System.out.println("Landfill Action - In displayControl1wertwe4rwerwer");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("PaintSprayAction - In Add");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		PaintSprayVo paintsprayvo = new PaintSprayVo();
		paintsprayvo.setBuildingId(buildingvo.getId());
		paintsprayvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("psId"));
		paintsprayvo.setFloor((String) dynavalidatorform.get("psFloor"));
		paintsprayvo.setYearinstalled((String) dynavalidatorform
				.get("ps_yearInstalled"));
		paintsprayvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("ps_status")));
		paintsprayvo.setDisconnecteddate((String) dynavalidatorform
				.get("ps_disconnecteddate"));
		paintsprayvo.setHrsOfOperation((String) dynavalidatorform
				.get("psHoursOfOperation"));

		paintsprayvo.setPsDEPNumber((String) dynavalidatorform
				.get("psDEPNumber"));
		paintsprayvo
				.setSpraypaint((String) dynavalidatorform.get("spraypaint"));
		paintsprayvo.setSpraysolvant((String) dynavalidatorform
				.get("spraysolvant"));
		paintsprayvo.setSprayink((String) dynavalidatorform.get("sprayink"));
		paintsprayvo
				.setSprayother((String) dynavalidatorform.get("sprayother"));
		paintsprayvo.setSpraypaintname((String) dynavalidatorform
				.get("spraypaintname"));
		paintsprayvo.setSpraysolvantname((String) dynavalidatorform
				.get("spraysolvantname"));
		paintsprayvo.setSprayinkname((String) dynavalidatorform
				.get("sprayinkname"));
		paintsprayvo.setSprayothername((String) dynavalidatorform
				.get("sprayothername"));
		paintsprayvo.setSpraypaintvocpercent((String) dynavalidatorform
				.get("spraypaintvocpercent"));
		paintsprayvo.setSpraysolvantvocpercent((String) dynavalidatorform
				.get("spraysolvantvocpercent"));
		paintsprayvo.setSprayinkvocpercent((String) dynavalidatorform
				.get("sprayinkvocpercent"));
		paintsprayvo.setSprayothervocpercent((String) dynavalidatorform
				.get("sprayothervocpercent"));
		paintsprayvo.setSpraypaintvolume((String) dynavalidatorform
				.get("spraypaintvolume"));
		paintsprayvo.setSpraysolvantvolume((String) dynavalidatorform
				.get("spraysolvantvolume"));
		paintsprayvo.setSprayinkvolume((String) dynavalidatorform
				.get("sprayinkvolume"));
		paintsprayvo.setSprayothervolume((String) dynavalidatorform
				.get("sprayothervolume"));
		paintsprayvo.setSpraypaintdensity((String) dynavalidatorform
				.get("spraypaintdensity"));
		paintsprayvo.setSpraysolvantdensity((String) dynavalidatorform
				.get("spraysolvantdensity"));
		paintsprayvo.setSprayinkdensity((String) dynavalidatorform
				.get("sprayinkdensity"));
		paintsprayvo.setSprayotherdensity((String) dynavalidatorform
				.get("sprayotherdensity"));
		paintsprayvo.setSpraypaintvoc((String) dynavalidatorform
				.get("spraypaintvoc"));
		paintsprayvo.setSpraysolvantvoc((String) dynavalidatorform
				.get("spraysolvantvoc"));
		paintsprayvo.setSprayinkvoc((String) dynavalidatorform
				.get("sprayinkvoc"));
		paintsprayvo.setSprayothervoc((String) dynavalidatorform
				.get("sprayothervoc"));
		paintsprayvo.setSpraypaintcap((String) dynavalidatorform
				.get("spraypaintcap"));
		paintsprayvo.setSpraysolvantcap((String) dynavalidatorform
				.get("spraysolvantcap"));
		paintsprayvo.setSprayinkcap((String) dynavalidatorform
				.get("sprayinkcap"));
		paintsprayvo.setVocyear((String) dynavalidatorform.get("vocyear"));
		paintsprayvo.setVocmonth((String) dynavalidatorform.get("vocmonth"));
		paintsprayvo.setVoccap((String) dynavalidatorform.get("voccap"));
		paintsprayvo.setVocmonthlyquantity((String) dynavalidatorform
				.get("vocmonthlyquantity"));
		paintsprayvo.setVocmonthlylimit((String) dynavalidatorform
				.get("vocmonthlylimit"));
		paintsprayvo.setPsHoursOfOperationyear((String) dynavalidatorform
				.get("psHoursOfOperationyear"));

		paintsprayvo
				.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));
		paintsprayvo.setDobnumber((String) dynavalidatorform.get("dobnumber"));

		paintsprayvo.setMake((String) dynavalidatorform.get("psMake"));
		System.out.println("1p");
		paintsprayvo.setActionTaken((String) dynavalidatorform
				.get("psActionTaken"));
		System.out.println("2p");
		paintsprayvo.setModel((String) dynavalidatorform.get("psModel"));
		String s2 = (String) dynavalidatorform.get("P_fuelFrom");
		if (UtilityObject.isNotEmpty(s2))
			paintsprayvo.setStackId(Integer.parseInt(s2));
		String as[] = (String[]) (String[]) dynavalidatorform
				.get("psTypesOfChemical");
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < as.length; i++)
			arraylist.add(as[i]);
		paintsprayvo.setChemicalsUsedList(arraylist);
		s2 = (String) dynavalidatorform.get("psExemptedbyDec");
		if (s2.equals("Y"))
			paintsprayvo.setExemptedByDec(true);
		else
			paintsprayvo.setExemptedByDec(false);
		s2 = (String) dynavalidatorform.get("psIncludeByDec");
		if (s2.equals("Y"))
			paintsprayvo.setUnitIncludedInDecPermit(true);
		else
			paintsprayvo.setUnitIncludedInDecPermit(false);
		paintsprayvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("psLocation")));
		byte byte0 = -99;
		try {
			int j = PaintSprayEntity.add(paintsprayvo);
			paintsprayvo = PaintSprayEntity.findByPrimaryKey(j);
			if (paintsprayvo != null) {
				PaintSprayEntity.addChemical(paintsprayvo.getId(), arraylist);
				httpsession.setAttribute("PAINTSPRAY_OBJECT", paintsprayvo);
				dynavalidatorform = setFormVariable(dynavalidatorform,
						paintsprayvo, httpservletrequest);
			}
			s = "Added PaintSpray Successfully.";
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
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(paintsprayvo.getLocation()));
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_DEP_LST", null);
		httpservletrequest.setAttribute("PSPRAY_LOC_LST", null);
		httpservletrequest.setAttribute("PSPRAY_STATE_LST", null);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		log.debug((new StringBuilder())
				.append("paintSpray The Chemical list = ")
				.append(paintsprayvo.getChemicalsUsedList().size()).toString());
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayForm - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Paint Spray ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PaintSprayVo paintsprayvo = PaintSprayEntity.findByPrimaryKey(i);
		if (paintsprayvo != null) {
			httpsession.setAttribute("PAINTSPRAY_OBJECT", paintsprayvo);
			dynavalidatorform = setFormVariable(dynavalidatorform,
					paintsprayvo, httpservletrequest);
		}
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		PaintSprayVo paintsprayvo1 = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		try {
			PaintSprayEntity.delete(paintsprayvo1);
			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/paintspray/" + paintsprayvo1.getId() + "-"
						+ paintsprayvo1.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successpaint");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayAction - In Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		String facilityDesignatedid = paintsprayvo.getFacilityDesignatedId();
		paintsprayvo.setBuildingId(buildingvo.getId());
		paintsprayvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("psId"));
		paintsprayvo.setFloor((String) dynavalidatorform.get("psFloor"));
		paintsprayvo.setYearinstalled((String) dynavalidatorform
				.get("ps_yearInstalled"));
		paintsprayvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("ps_status")));
		paintsprayvo.setDisconnecteddate((String) dynavalidatorform
				.get("ps_disconnecteddate"));
		paintsprayvo.setHrsOfOperation((String) dynavalidatorform
				.get("psHoursOfOperation"));
		paintsprayvo.setMake((String) dynavalidatorform.get("psMake"));
		paintsprayvo.setModel((String) dynavalidatorform.get("psModel"));
		paintsprayvo.setActionTaken((String) dynavalidatorform
				.get("psActionTaken"));

		paintsprayvo.setPsDEPNumber((String) dynavalidatorform
				.get("psDEPNumber"));
		paintsprayvo
				.setSpraypaint((String) dynavalidatorform.get("spraypaint"));
		paintsprayvo.setSpraysolvant((String) dynavalidatorform
				.get("spraysolvant"));
		paintsprayvo.setSprayink((String) dynavalidatorform.get("sprayink"));
		paintsprayvo
				.setSprayother((String) dynavalidatorform.get("sprayother"));
		paintsprayvo.setSpraypaintname((String) dynavalidatorform
				.get("spraypaintname"));
		paintsprayvo.setSpraysolvantname((String) dynavalidatorform
				.get("spraysolvantname"));
		paintsprayvo.setSprayinkname((String) dynavalidatorform
				.get("sprayinkname"));
		paintsprayvo.setSprayothername((String) dynavalidatorform
				.get("sprayothername"));
		paintsprayvo.setSpraypaintvocpercent((String) dynavalidatorform
				.get("spraypaintvocpercent"));
		paintsprayvo.setSpraysolvantvocpercent((String) dynavalidatorform
				.get("spraysolvantvocpercent"));
		paintsprayvo.setSprayinkvocpercent((String) dynavalidatorform
				.get("sprayinkvocpercent"));
		paintsprayvo.setSprayothervocpercent((String) dynavalidatorform
				.get("sprayothervocpercent"));
		paintsprayvo.setSpraypaintvolume((String) dynavalidatorform
				.get("spraypaintvolume"));
		paintsprayvo.setSpraysolvantvolume((String) dynavalidatorform
				.get("spraysolvantvolume"));
		paintsprayvo.setSprayinkvolume((String) dynavalidatorform
				.get("sprayinkvolume"));
		paintsprayvo.setSprayothervolume((String) dynavalidatorform
				.get("sprayothervolume"));
		paintsprayvo.setSpraypaintdensity((String) dynavalidatorform
				.get("spraypaintdensity"));
		paintsprayvo.setSpraysolvantdensity((String) dynavalidatorform
				.get("spraysolvantdensity"));
		paintsprayvo.setSprayinkdensity((String) dynavalidatorform
				.get("sprayinkdensity"));
		paintsprayvo.setSprayotherdensity((String) dynavalidatorform
				.get("sprayotherdensity"));
		paintsprayvo.setSpraypaintvoc((String) dynavalidatorform
				.get("spraypaintvoc"));
		paintsprayvo.setSpraysolvantvoc((String) dynavalidatorform
				.get("spraysolvantvoc"));
		paintsprayvo.setSprayinkvoc((String) dynavalidatorform
				.get("sprayinkvoc"));
		paintsprayvo.setSprayothervoc((String) dynavalidatorform
				.get("sprayothervoc"));
		paintsprayvo.setSpraypaintcap((String) dynavalidatorform
				.get("spraypaintcap"));
		paintsprayvo.setSpraysolvantcap((String) dynavalidatorform
				.get("spraysolvantcap"));
		paintsprayvo.setSprayinkcap((String) dynavalidatorform
				.get("sprayinkcap"));
		paintsprayvo.setVocyear((String) dynavalidatorform.get("vocyear"));
		paintsprayvo.setVocmonth((String) dynavalidatorform.get("vocmonth"));
		paintsprayvo.setVoccap((String) dynavalidatorform.get("voccap"));
		paintsprayvo.setVocmonthlyquantity((String) dynavalidatorform
				.get("vocmonthlyquantity"));
		paintsprayvo.setVocmonthlylimit((String) dynavalidatorform
				.get("vocmonthlylimit"));
		paintsprayvo.setPsHoursOfOperationyear((String) dynavalidatorform
				.get("psHoursOfOperationyear"));
		paintsprayvo
				.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));
		paintsprayvo.setDobnumber((String) dynavalidatorform.get("dobnumber"));

		String s2 = (String) dynavalidatorform.get("P_fuelFrom");
		if (UtilityObject.isNotEmpty(s2))
			paintsprayvo.setStackId(Integer.parseInt(s2));
		String as[] = (String[]) (String[]) dynavalidatorform
				.get("psTypesOfChemical");
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < as.length; i++)
			arraylist.add(as[i]);

		paintsprayvo.setChemicalsUsedList(arraylist);
		s2 = (String) dynavalidatorform.get("psExemptedbyDec");
		if (s2.equals("Y"))
			paintsprayvo.setExemptedByDec(true);
		else
			paintsprayvo.setExemptedByDec(false);
		s2 = (String) dynavalidatorform.get("psIncludeByDec");
		if (s2.equals("Y"))
			paintsprayvo.setUnitIncludedInDecPermit(true);
		else
			paintsprayvo.setUnitIncludedInDecPermit(false);
		paintsprayvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("psLocation")));
		PaintSprayVo paintsprayvo1 = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		try {
			PaintSprayEntity.update(paintsprayvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/paintspray/" + paintsprayvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/paintspray/"
									+ paintsprayvo.getId()
									+ "-"
									+ paintsprayvo.getFacilityDesignatedId()
											.trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			if (arraylist != null)
				PaintSprayEntity
						.updateChemical(paintsprayvo.getId(), arraylist);
			paintsprayvo = PaintSprayEntity.findByPrimaryKey(paintsprayvo1
					.getId());
			refreshBuildingObject(httpservletrequest);
			s = "Updated PaintSpray Successfully.";
			s1 = "CONFIRMATION";
			dynavalidatorform = setFormVariable(dynavalidatorform,
					paintsprayvo, httpservletrequest);
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(paintsprayvo.getLocation()));
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
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
		log.debug("PaintSprayForm - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Paint Spray ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PaintSprayVo paintsprayvo = PaintSprayEntity.findByPrimaryKey(i);
		if (paintsprayvo != null) {
			httpsession.setAttribute("PAINTSPRAY_OBJECT", paintsprayvo);
			dynavalidatorform = setFormVariable(dynavalidatorform,
					paintsprayvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get PaintSpraylVo for id(").append(s)
					.append(")").toString());
		}
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayForm - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Paint Spray ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PaintSprayVo paintsprayvo = PaintSprayEntity.findByPrimaryKey(i);
		if (paintsprayvo != null) {
			httpsession.setAttribute("PAINTSPRAY_OBJECT", paintsprayvo);
			dynavalidatorform = setFormVariableforedit(dynavalidatorform,
					paintsprayvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get PaintSpraylVo for id(").append(s)
					.append(")").toString());
		}
		refreshPermitInfo(httpservletrequest);
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		String s1;
		Object obj;
		for (Enumeration enumeration = httpservletrequest.getAttributeNames(); enumeration
				.hasMoreElements(); log.debug((new StringBuilder()).append(s1)
				.append(":").append(obj).toString())) {
			s1 = (String) enumeration.nextElement();
			obj = httpservletrequest.getAttribute(s1);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayForm - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Paint Spray ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PaintSprayVo paintsprayvo = PaintSprayEntity.findByPrimaryKey(i);
		if (paintsprayvo != null) {
			httpsession.setAttribute("PAINTSPRAY_OBJECT", paintsprayvo);
			dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
					paintsprayvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get PaintSpraylVo for id(").append(s)
					.append(")").toString());
		}
		refreshPermitInfo(httpservletrequest);
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		String s1;
		Object obj;
		for (Enumeration enumeration = httpservletrequest.getAttributeNames(); enumeration
				.hasMoreElements(); log.debug((new StringBuilder()).append(s1)
				.append(":").append(obj).toString())) {
			s1 = (String) enumeration.nextElement();
			obj = httpservletrequest.getAttribute(s1);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RETURN");
		return actionmapping.findForward("return");
	}

	public ActionForward dep(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(paintsprayvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Paint Spray Booth")
				.getCode());
		s = (String) dynavalidatorform.get("psDEPNumber");
		s1 = (String) dynavalidatorform.get("psDEPIssueDate");
		s2 = (String) dynavalidatorform.get("psDEPExpirationDate");
		s3 = (String) dynavalidatorform.get("psDEPExpirationDateNote");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		PaintSprayEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psDEPIssueDate", "");
		dynavalidatorform.set("psDEPExpirationDate", "");
		dynavalidatorform.set("psDEPExpirationDateNote", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward depEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("PaintSpray Dep Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("psDEPNumber");
		String s1 = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		if (s != null)
			dynavalidatorform.set("psDEPNumber", s);
		if (s1 != null && s1.trim().equalsIgnoreCase("")) {
			String s2 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s2);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s1);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward depDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Dep Permit Id is null while updating the Dep Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setId(Integer.parseInt(s));
		PaintSprayEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psDEPIssueDate", "");
		dynavalidatorform.set("psDEPExpirationDate", "");
		dynavalidatorform.set("psDEPExpirationDateNote", "");

		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward depUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Dep Permit Id is null while updating the Dep Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(paintsprayvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Paint Spray Booth")
				.getCode());
		s1 = (String) dynavalidatorform.get("psDEPNumber");
		s2 = (String) dynavalidatorform.get("psDEPIssueDate");
		s3 = (String) dynavalidatorform.get("psDEPExpirationDate");
		s4 = (String) dynavalidatorform.get("psDEPExpirationDateNote");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		PaintSprayEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psDEPIssueDate", "");
		dynavalidatorform.set("psDEPExpirationDate", "");
		dynavalidatorform.set("psDEPExpirationDateNote", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward state(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray State");
		log.debug("PaintSprayAction - In State");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = -99;
		i = DepartmentEnum.STATE_AGENCY.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(paintsprayvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Paint Spray Booth")
				.getCode());
		s = (String) dynavalidatorform.get("psStateNumber");
		s1 = (String) dynavalidatorform.get("psStateIssueDate");
		s2 = (String) dynavalidatorform.get("psStateExpirationDate");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		PaintSprayEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psStateIssueDate", "");
		dynavalidatorform.set("psStateExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward stateEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("State Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STATE_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward stateDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray State - Update");
		log.debug("PaintSprayAction - In State - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
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
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setId(Integer.parseInt(s));
		PaintSprayEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psStateIssueDate", "");
		dynavalidatorform.set("psStateExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward stateUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray State - Update");
		log.debug("PaintSprayAction - In State - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
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
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(paintsprayvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Paint Spray Booth")
				.getCode());
		s1 = (String) dynavalidatorform.get("psStateNumber");
		s2 = (String) dynavalidatorform.get("psStateIssueDate");
		s3 = (String) dynavalidatorform.get("psStateExpirationDate");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setId(Integer.parseInt(s));
		PaintSprayEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psStateIssueDate", "");
		dynavalidatorform.set("psStateExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward location(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray Loc");
		log.debug("PaintSprayAction - In LOC");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = -99;
		PermitInfoVo permitinfovo = new PermitInfoVo();
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
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(paintsprayvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Paint Spray Booth")
				.getCode());
		s = (String) dynavalidatorform.get("psLocNumber");
		s1 = (String) dynavalidatorform.get("psLocIssueDate");
		s2 = (String) dynavalidatorform.get("psLocExpirationDate");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		PaintSprayEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psLocIssueDate", "");
		dynavalidatorform.set("psLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward locEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("State Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_LOC_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward locUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray Loc");
		log.debug("PaintSprayAction - In LOC - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"location Permit Id is null while updating the Loc Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		PermitInfoVo permitinfovo = new PermitInfoVo();
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
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(paintsprayvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Paint Spray Booth")
				.getCode());
		permitinfovo.setId(Integer.parseInt(s));
		s1 = (String) dynavalidatorform.get("psLocNumber");
		s2 = (String) dynavalidatorform.get("psLocIssueDate");
		s3 = (String) dynavalidatorform.get("psLocExpirationDate");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		PaintSprayEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psLocIssueDate", "");
		dynavalidatorform.set("psLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward locDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray Loc");
		log.debug("PaintSprayAction - In LOC - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		LandfillVo landfillvo = (LandfillVo) httpsession
				.getAttribute("LANDFILL_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"location Permit Id is null while updating the Loc Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		byte byte0 = -99;
		PermitInfoVo permitinfovo = new PermitInfoVo();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		AddressVo addressvo = facilityvo.getFacilityAddress();
		String s4 = addressvo.getState();
		String s5 = (String) VALID_STATES.get(s4);
		log.debug((new StringBuilder()).append(s4).append(" enum =")
				.append(DepartmentEnum.parse(s5)).toString());
		if (UtilityObject.isNotEmpty(s5)) {
			DepartmentEnum departmentenum = DepartmentEnum.parse(s5);
			int j = departmentenum != null ? departmentenum.getCode() : -1;
		}
		permitinfovo.setId(Integer.parseInt(s));
		PaintSprayEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, paintsprayvo,
				httpservletrequest);
		dynavalidatorform.set("psLocIssueDate", "");
		dynavalidatorform.set("psLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");
		httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = AgencyLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("AGENCY_LOCATION", dropdown);
		dropdown = SprayBoothChemicalsEnum.getDropDownObj();
		httpservletrequest.setAttribute("SPRAY_CHEMICAL", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown1);
	}

	public DynaValidatorForm setFormVariable(
			DynaValidatorForm dynavalidatorform, PaintSprayVo paintsprayvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Set Form Variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"psId",
				(new StringBuilder())
						.append(paintsprayvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("psFloor",
				(new StringBuilder()).append(paintsprayvo.getFloor())
						.append("").toString());
		dynavalidatorform1.set("ps_yearInstalled", (new StringBuilder())
				.append(checkNullAndFill(paintsprayvo.getYearinstalled(), ""))
				.append("").toString());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(paintsprayvo.getStatus());
		if (tankoperatingstatusenum != null)
			dynavalidatorform1.set("ps_status",
					tankoperatingstatusenum.getName());
		dynavalidatorform1.set(
				"ps_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								paintsprayvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("psMake",
				(new StringBuilder()).append(paintsprayvo.getMake()).append("")
						.toString());
		dynavalidatorform1.set("psActionTaken",
				(new StringBuilder()).append(paintsprayvo.getActionTaken())
						.append("").toString());
		dynavalidatorform1.set("psDEPNumber", paintsprayvo.getPsDEPNumber());
		dynavalidatorform1.set("spraypaint", paintsprayvo.getSpraypaint());
		dynavalidatorform1.set("spraysolvant", paintsprayvo.getSpraysolvant());
		dynavalidatorform1.set("sprayink", paintsprayvo.getSprayink());
		dynavalidatorform1.set("sprayother", paintsprayvo.getSprayother());
		dynavalidatorform1.set("spraypaintname",
				paintsprayvo.getSpraypaintname());
		dynavalidatorform1.set("spraysolvantname",
				paintsprayvo.getSpraysolvantname());
		dynavalidatorform1.set("sprayinkname", paintsprayvo.getSprayinkname());
		dynavalidatorform1.set("sprayothername",
				paintsprayvo.getSprayothername());
		dynavalidatorform1.set("spraypaintvocpercent",
				paintsprayvo.getSpraypaintvocpercent());
		dynavalidatorform1.set("spraysolvantvocpercent",
				paintsprayvo.getSpraysolvantvocpercent());
		dynavalidatorform1.set("sprayinkvocpercent",
				paintsprayvo.getSprayinkvocpercent());
		dynavalidatorform1.set("sprayothervocpercent",
				paintsprayvo.getSprayothervocpercent());
		dynavalidatorform1.set("spraypaintvolume",
				paintsprayvo.getSpraypaintvolume());
		dynavalidatorform1.set("spraysolvantvolume",
				paintsprayvo.getSpraysolvantvolume());
		dynavalidatorform1.set("sprayinkvolume",
				paintsprayvo.getSprayinkvolume());
		dynavalidatorform1.set("sprayothervolume",
				paintsprayvo.getSprayothervolume());
		dynavalidatorform1.set("spraypaintdensity",
				paintsprayvo.getSpraypaintdensity());
		dynavalidatorform1.set("spraysolvantdensity",
				paintsprayvo.getSpraysolvantdensity());
		dynavalidatorform1.set("sprayinkdensity",
				paintsprayvo.getSprayinkdensity());
		dynavalidatorform1.set("sprayotherdensity",
				paintsprayvo.getSprayotherdensity());
		dynavalidatorform1
				.set("spraypaintvoc", paintsprayvo.getSpraypaintvoc());
		dynavalidatorform1.set("spraysolvantvoc",
				paintsprayvo.getSpraysolvantvoc());
		dynavalidatorform1.set("sprayinkvoc", paintsprayvo.getSprayinkvoc());
		dynavalidatorform1
				.set("sprayothervoc", paintsprayvo.getSprayothervoc());
		dynavalidatorform1
				.set("spraypaintcap", paintsprayvo.getSpraypaintcap());
		dynavalidatorform1.set("spraysolvantcap",
				paintsprayvo.getSpraysolvantcap());
		dynavalidatorform1.set("sprayinkcap", paintsprayvo.getSprayinkcap());
		dynavalidatorform1.set("vocyear", paintsprayvo.getVocyear());
		dynavalidatorform1.set("vocmonth", paintsprayvo.getVocmonth());
		dynavalidatorform1.set("voccap", paintsprayvo.getVoccap());
		dynavalidatorform1.set("vocmonthlyquantity",
				paintsprayvo.getVocmonthlyquantity());
		dynavalidatorform1.set("vocmonthlylimit",
				paintsprayvo.getVocmonthlylimit());
		dynavalidatorform1.set("psHoursOfOperationyear",
				paintsprayvo.getPsHoursOfOperationyear());
		dynavalidatorform1.set("dobsignoff", paintsprayvo.getDobsignoff());
		dynavalidatorform1.set("dobnumber", paintsprayvo.getDobnumber());

		dynavalidatorform1.set("psModel",
				(new StringBuilder()).append(paintsprayvo.getModel())
						.append("").toString());
		dynavalidatorform1
				.set("psHoursOfOperation",
						(new StringBuilder())
								.append(paintsprayvo.getHrsOfOperation())
								.append("").toString());
		com.eespc.tracking.bo.DropDown dropdown = SprayBoothChemicalsEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("SPRAY_CHEMICAL", dropdown);
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		dynavalidatorform1.set("psIncludeByDec", "Y");

		if (userAction.equalsIgnoreCase("edit"))
			dynavalidatorform1.set("psLocation",
					(new StringBuilder()).append(paintsprayvo.getLocation())
							.append("").toString());
		else if (paintsprayvo.getLocation() != -1)
			dynavalidatorform1.set(
					"psLocation",
					(new StringBuilder())
							.append(AgencyLocationEnum.get(paintsprayvo
									.getLocation())).append("").toString());

		else
			dynavalidatorform1.set("psLocation", "");

		StackVo stackvo = paintsprayvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set(
					"P_fuelFrom",
					(new StringBuilder()).append(stackvo.getStackId())
							.append("").toString());
			dynavalidatorform1.set("P_fuelFromName",
					stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("P_fuelFrom", "");
			dynavalidatorform1.set("P_fuelFromName", "");
		}
		dynavalidatorform1.set("psExemptedbyDec", "N");
		boolean flag = paintsprayvo.isExemptedByDec();
		if (flag)
			dynavalidatorform1.set("psExemptedbyDec", "Y");
		else
			dynavalidatorform1.set("psExemptedbyDec", "N");
		flag = paintsprayvo.isUnitIncludedInDecPermit();
		if (flag)
			dynavalidatorform1.set("psIncludeByDec", "Y");
		else
			dynavalidatorform1.set("psIncludeByDec", "N");
		int i = paintsprayvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		else
			httpservletrequest.setAttribute("PSPRAY_LOC", "none");
		try {
			List list = paintsprayvo.getChemicalsUsedList();
			String as[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				SprayBoothChemicalsEnum sprayboothchemicalsenum = SprayBoothChemicalsEnum
						.parse(String.valueOf(list.get(j)));
				as[j] = String.valueOf(sprayboothchemicalsenum.getCode());
				log.debug(as[j].toString());
			}

			dynavalidatorform1.set("psTypesOfChemical", as);
		} catch (Exception exception) {
			log.debug((new StringBuilder())
					.append("paintSprayAction.setFormVariable() ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(i));
		refreshPermitInfo(httpservletrequest);
		/*
		 * List list1 = (List)httpservletrequest.getAttribute("PSPRAY_DEP_LST");
		 * if(list1 != null && list1.size() > 0) { PermitInfoVo permitinfovo =
		 * (PermitInfoVo)list1.get(0);
		 * log.debug(permitinfovo.getPermitNumber());
		 * dynavalidatorform1.set("psDEPNumber",
		 * permitinfovo.getPermitNumber()); }
		 */
		setScreenInfo(httpservletrequest);

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		httpservletrequest.setAttribute("PSPRAY_DEP", "inline");
		httpservletrequest.setAttribute("PSPRAY_STATE", "inline");

		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforedit(
			DynaValidatorForm dynavalidatorform, PaintSprayVo paintsprayvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Set Form Variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"psId",
				(new StringBuilder())
						.append(paintsprayvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("psFloor",
				(new StringBuilder()).append(paintsprayvo.getFloor())
						.append("").toString());
		dynavalidatorform1.set("ps_yearInstalled", (new StringBuilder())
				.append(checkNullAndFill(paintsprayvo.getYearinstalled(), ""))
				.append("").toString());
		dynavalidatorform1.set(
				"ps_status",
				(new StringBuilder()).append(paintsprayvo.getStatus())
						.append("").toString());
		dynavalidatorform1.set(
				"ps_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								paintsprayvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("psMake",
				(new StringBuilder()).append(paintsprayvo.getMake()).append("")
						.toString());
		dynavalidatorform1.set("psActionTaken",
				(new StringBuilder()).append(paintsprayvo.getActionTaken())
						.append("").toString());

		dynavalidatorform1.set("psDEPNumber", paintsprayvo.getPsDEPNumber());
		dynavalidatorform1.set("spraypaint", paintsprayvo.getSpraypaint());
		dynavalidatorform1.set("spraysolvant", paintsprayvo.getSpraysolvant());
		dynavalidatorform1.set("sprayink", paintsprayvo.getSprayink());
		dynavalidatorform1.set("sprayother", paintsprayvo.getSprayother());
		dynavalidatorform1.set("spraypaintname",
				paintsprayvo.getSpraypaintname());
		dynavalidatorform1.set("spraysolvantname",
				paintsprayvo.getSpraysolvantname());
		dynavalidatorform1.set("sprayinkname", paintsprayvo.getSprayinkname());
		dynavalidatorform1.set("sprayothername",
				paintsprayvo.getSprayothername());
		dynavalidatorform1.set("spraypaintvocpercent",
				paintsprayvo.getSpraypaintvocpercent());
		dynavalidatorform1.set("spraysolvantvocpercent",
				paintsprayvo.getSpraysolvantvocpercent());
		dynavalidatorform1.set("sprayinkvocpercent",
				paintsprayvo.getSprayinkvocpercent());
		dynavalidatorform1.set("sprayothervocpercent",
				paintsprayvo.getSprayothervocpercent());
		dynavalidatorform1.set("spraypaintvolume",
				paintsprayvo.getSpraypaintvolume());
		dynavalidatorform1.set("spraysolvantvolume",
				paintsprayvo.getSpraysolvantvolume());
		dynavalidatorform1.set("sprayinkvolume",
				paintsprayvo.getSprayinkvolume());
		dynavalidatorform1.set("sprayothervolume",
				paintsprayvo.getSprayothervolume());
		dynavalidatorform1.set("spraypaintdensity",
				paintsprayvo.getSpraypaintdensity());
		dynavalidatorform1.set("spraysolvantdensity",
				paintsprayvo.getSpraysolvantdensity());
		dynavalidatorform1.set("sprayinkdensity",
				paintsprayvo.getSprayinkdensity());
		dynavalidatorform1.set("sprayotherdensity",
				paintsprayvo.getSprayotherdensity());
		dynavalidatorform1
				.set("spraypaintvoc", paintsprayvo.getSpraypaintvoc());
		dynavalidatorform1.set("spraysolvantvoc",
				paintsprayvo.getSpraysolvantvoc());
		dynavalidatorform1.set("sprayinkvoc", paintsprayvo.getSprayinkvoc());
		dynavalidatorform1
				.set("sprayothervoc", paintsprayvo.getSprayothervoc());
		dynavalidatorform1
				.set("spraypaintcap", paintsprayvo.getSpraypaintcap());
		dynavalidatorform1.set("spraysolvantcap",
				paintsprayvo.getSpraysolvantcap());
		dynavalidatorform1.set("sprayinkcap", paintsprayvo.getSprayinkcap());
		dynavalidatorform1.set("vocyear", paintsprayvo.getVocyear());
		dynavalidatorform1.set("vocmonth", paintsprayvo.getVocmonth());
		dynavalidatorform1.set("voccap", paintsprayvo.getVoccap());
		dynavalidatorform1.set("vocmonthlyquantity",
				paintsprayvo.getVocmonthlyquantity());
		dynavalidatorform1.set("vocmonthlylimit",
				paintsprayvo.getVocmonthlylimit());
		dynavalidatorform1.set("psHoursOfOperationyear",
				paintsprayvo.getPsHoursOfOperationyear());
		dynavalidatorform1.set("dobsignoff", paintsprayvo.getDobsignoff());
		dynavalidatorform1.set("dobnumber", paintsprayvo.getDobnumber());

		dynavalidatorform1.set("psModel",
				(new StringBuilder()).append(paintsprayvo.getModel())
						.append("").toString());
		dynavalidatorform1
				.set("psHoursOfOperation",
						(new StringBuilder())
								.append(paintsprayvo.getHrsOfOperation())
								.append("").toString());
		com.eespc.tracking.bo.DropDown dropdown = SprayBoothChemicalsEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("SPRAY_CHEMICAL", dropdown);
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				paintsprayvo.getChemicalsUsedList());
		dynavalidatorform1.set("psIncludeByDec", "Y");
		if (!userAction.equalsIgnoreCase("edit"))
			dynavalidatorform1.set(
					"psLocation",
					(new StringBuilder())
							.append(AgencyLocationEnum.get(paintsprayvo
									.getLocation())).append("").toString());
		else
			dynavalidatorform1.set("psLocation",
					(new StringBuilder()).append(paintsprayvo.getLocation())
							.append("").toString());
		StackVo stackvo = paintsprayvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set(
					"P_fuelFrom",
					(new StringBuilder()).append(stackvo.getStackId())
							.append("").toString());
			dynavalidatorform1.set("P_fuelFromName",
					stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("P_fuelFrom", "");
			dynavalidatorform1.set("P_fuelFromName", "");
		}
		dynavalidatorform1.set("psExemptedbyDec", "N");
		boolean flag = paintsprayvo.isExemptedByDec();
		if (flag)
			dynavalidatorform1.set("psExemptedbyDec", "Y");
		else
			dynavalidatorform1.set("psExemptedbyDec", "N");
		flag = paintsprayvo.isUnitIncludedInDecPermit();
		if (flag)
			dynavalidatorform1.set("psIncludeByDec", "Y");
		else
			dynavalidatorform1.set("psIncludeByDec", "N");
		int i = paintsprayvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		else
			httpservletrequest.setAttribute("PSPRAY_LOC", "none");
		try {
			List list = paintsprayvo.getChemicalsUsedList();
			String as[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				SprayBoothChemicalsEnum sprayboothchemicalsenum = SprayBoothChemicalsEnum
						.parse(String.valueOf(list.get(j)));
				as[j] = String.valueOf(sprayboothchemicalsenum.getCode());
				log.debug(as[j].toString());
			}

			dynavalidatorform1.set("psTypesOfChemical", as);
		} catch (Exception exception) {
			log.debug((new StringBuilder())
					.append("paintSprayAction.setFormVariable() ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(i));
		refreshPermitInfo(httpservletrequest);
		/*
		 * List list1 = (List)httpservletrequest.getAttribute("PSPRAY_DEP_LST");
		 * if(list1 != null && list1.size() > 0) { PermitInfoVo permitinfovo =
		 * (PermitInfoVo)list1.get(0);
		 * log.debug(permitinfovo.getPermitNumber());
		 * dynavalidatorform1.set("psDEPNumber",
		 * permitinfovo.getPermitNumber()); }
		 */
		setScreenInfo(httpservletrequest);

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, PaintSprayVo paintsprayvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Set Form Variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"psId",
				(new StringBuilder())
						.append(paintsprayvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("psFloor",
				(new StringBuilder()).append(paintsprayvo.getFloor())
						.append("").toString());
		dynavalidatorform1.set("ps_yearInstalled", (new StringBuilder())
				.append(checkNullAndFill(paintsprayvo.getYearinstalled(), ""))
				.append("").toString());
		dynavalidatorform1.set(
				"ps_status",
				(new StringBuilder()).append(paintsprayvo.getStatus())
						.append("").toString());
		dynavalidatorform1.set(
				"ps_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								paintsprayvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("psMake",
				(new StringBuilder()).append(paintsprayvo.getMake()).append("")
						.toString());
		dynavalidatorform1.set("psActionTaken",
				(new StringBuilder()).append(paintsprayvo.getActionTaken())
						.append("").toString());
		dynavalidatorform1.set("dobsignoff", paintsprayvo.getDobsignoff());
		dynavalidatorform1.set("dobnumber", paintsprayvo.getDobnumber());

		dynavalidatorform1.set("psDEPNumber", paintsprayvo.getPsDEPNumber());
		dynavalidatorform1.set("spraypaint", paintsprayvo.getSpraypaint());
		dynavalidatorform1.set("spraysolvant", paintsprayvo.getSpraysolvant());
		dynavalidatorform1.set("sprayink", paintsprayvo.getSprayink());
		dynavalidatorform1.set("sprayother", paintsprayvo.getSprayother());
		dynavalidatorform1.set("spraypaintname",
				paintsprayvo.getSpraypaintname());
		dynavalidatorform1.set("spraysolvantname",
				paintsprayvo.getSpraysolvantname());
		dynavalidatorform1.set("sprayinkname", paintsprayvo.getSprayinkname());
		dynavalidatorform1.set("sprayothername",
				paintsprayvo.getSprayothername());
		dynavalidatorform1.set("spraypaintvocpercent",
				paintsprayvo.getSpraypaintvocpercent());
		dynavalidatorform1.set("spraysolvantvocpercent",
				paintsprayvo.getSpraysolvantvocpercent());
		dynavalidatorform1.set("sprayinkvocpercent",
				paintsprayvo.getSprayinkvocpercent());
		dynavalidatorform1.set("sprayothervocpercent",
				paintsprayvo.getSprayothervocpercent());
		dynavalidatorform1.set("spraypaintvolume",
				paintsprayvo.getSpraypaintvolume());
		dynavalidatorform1.set("spraysolvantvolume",
				paintsprayvo.getSpraysolvantvolume());
		dynavalidatorform1.set("sprayinkvolume",
				paintsprayvo.getSprayinkvolume());
		dynavalidatorform1.set("sprayothervolume",
				paintsprayvo.getSprayothervolume());
		dynavalidatorform1.set("spraypaintdensity",
				paintsprayvo.getSpraypaintdensity());
		dynavalidatorform1.set("spraysolvantdensity",
				paintsprayvo.getSpraysolvantdensity());
		dynavalidatorform1.set("sprayinkdensity",
				paintsprayvo.getSprayinkdensity());
		dynavalidatorform1.set("sprayotherdensity",
				paintsprayvo.getSprayotherdensity());
		dynavalidatorform1
				.set("spraypaintvoc", paintsprayvo.getSpraypaintvoc());
		dynavalidatorform1.set("spraysolvantvoc",
				paintsprayvo.getSpraysolvantvoc());
		dynavalidatorform1.set("sprayinkvoc", paintsprayvo.getSprayinkvoc());
		dynavalidatorform1
				.set("sprayothervoc", paintsprayvo.getSprayothervoc());
		dynavalidatorform1
				.set("spraypaintcap", paintsprayvo.getSpraypaintcap());
		dynavalidatorform1.set("spraysolvantcap",
				paintsprayvo.getSpraysolvantcap());
		dynavalidatorform1.set("sprayinkcap", paintsprayvo.getSprayinkcap());
		dynavalidatorform1.set("vocyear", paintsprayvo.getVocyear());
		dynavalidatorform1.set("vocmonth", paintsprayvo.getVocmonth());
		dynavalidatorform1.set("voccap", paintsprayvo.getVoccap());
		dynavalidatorform1.set("vocmonthlyquantity",
				paintsprayvo.getVocmonthlyquantity());
		dynavalidatorform1.set("vocmonthlylimit",
				paintsprayvo.getVocmonthlylimit());
		dynavalidatorform1.set("psHoursOfOperationyear",
				paintsprayvo.getPsHoursOfOperationyear());

		dynavalidatorform1.set("psModel",
				(new StringBuilder()).append(paintsprayvo.getModel())
						.append("").toString());
		dynavalidatorform1
				.set("psHoursOfOperation",
						(new StringBuilder())
								.append(paintsprayvo.getHrsOfOperation())
								.append("").toString());
		com.eespc.tracking.bo.DropDown dropdown = SprayBoothChemicalsEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("SPRAY_CHEMICAL", dropdown);
		dynavalidatorform1.set("psIncludeByDec", "Y");
		if (!userAction.equalsIgnoreCase("viewexist"))
			dynavalidatorform1.set(
					"psLocation",
					(new StringBuilder())
							.append(AgencyLocationEnum.get(paintsprayvo
									.getLocation())).append("").toString());
		else
			dynavalidatorform1.set("psLocation",
					(new StringBuilder()).append(paintsprayvo.getLocation())
							.append("").toString());
		StackVo stackvo = paintsprayvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set(
					"P_fuelFrom",
					(new StringBuilder()).append(stackvo.getStackId())
							.append("").toString());
			dynavalidatorform1.set("P_fuelFromName",
					stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("P_fuelFrom", "");
			dynavalidatorform1.set("P_fuelFromName", "");
		}
		dynavalidatorform1.set("psExemptedbyDec", "N");
		boolean flag = paintsprayvo.isExemptedByDec();
		if (flag)
			dynavalidatorform1.set("psExemptedbyDec", "Y");
		else
			dynavalidatorform1.set("psExemptedbyDec", "N");
		flag = paintsprayvo.isUnitIncludedInDecPermit();
		if (flag)
			dynavalidatorform1.set("psIncludeByDec", "Y");
		else
			dynavalidatorform1.set("psIncludeByDec", "N");
		int i = paintsprayvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("PSPRAY_LOC", "inline");
		else
			httpservletrequest.setAttribute("PSPRAY_LOC", "none");
		try {
			List list = paintsprayvo.getChemicalsUsedList();
			String as[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				SprayBoothChemicalsEnum sprayboothchemicalsenum = SprayBoothChemicalsEnum
						.parse(String.valueOf(list.get(j)));
				as[j] = String.valueOf(sprayboothchemicalsenum.getCode());
				log.debug(as[j].toString());
			}

			dynavalidatorform1.set("psTypesOfChemical", as);
		} catch (Exception exception) {
			log.debug((new StringBuilder())
					.append("paintSprayAction.setFormVariable() ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(i));
		refreshPermitInfo(httpservletrequest);
		/*
		 * List list1 = (List)httpservletrequest.getAttribute("PSPRAY_DEP_LST");
		 * if(list1 != null && list1.size() > 0) { PermitInfoVo permitinfovo =
		 * (PermitInfoVo)list1.get(0);
		 * log.debug(permitinfovo.getPermitNumber());
		 * dynavalidatorform1.set("psDEPNumber",
		 * permitinfovo.getPermitNumber()); }
		 */
		setScreenInfoforsearch(httpservletrequest);
		return dynavalidatorform1;
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		if (paintsprayvo == null) {
			log.debug("PaintSpray Object is null");
			return;
		}
		paintsprayvo.setPermitInfoList(null);
		List list = paintsprayvo.getPermitInfoList();
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

		log.debug((new StringBuilder()).append("PSPRAY_STATE_LST size=")
				.append(arraylist.size()).append(", PSPRAY_DEP_LST size=")
				.append(arraylist1.size()).append(", PSPRAY_LOC_LST size=")
				.append(arraylist2.size()).toString());
		httpservletrequest.setAttribute("PSPRAY_STATE_LST", arraylist);
		httpservletrequest.setAttribute("PSPRAY_DEP_LST", arraylist1);
		httpservletrequest.setAttribute("PSPRAY_LOC_LST", arraylist2);
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("RESET");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		dynaactionform.set("psId", "");
		dynaactionform.set("psFloor", "");
		dynaactionform.set("ps_yearInstalled", "");
		dynaactionform.set("ps_status", "");
		dynaactionform.set("ps_disconnecteddate", "");
		dynaactionform.set("psMake", "");
		dynaactionform.set("psModel", "");
		dynaactionform.set("psActionTaken", "");
		dynaactionform.set("psHoursOfOperation", "");
		dynaactionform.set("psTypesOfChemical", null);
		dynaactionform.set("psIncludeByDec", "");
		dynaactionform.set("psLocation", "");
		dynaactionform.set("psExemptedbyDec", "");
		dynaactionform.set("psDEPNumber", "");
		dynaactionform.set("psDEPIssueDate", "");
		dynaactionform.set("psDEPExpirationDate", "");
		dynaactionform.set("psStateIssueDate", "");
		dynaactionform.set("psStateNumber", "");
		dynaactionform.set("psStateNumber", "");
		dynaactionform.set("psLocIssueDate", "");
		dynaactionform.set("psLocExpirationDate", "");
		dynaactionform.set("psLocNumber", "");
		dynaactionform.set("psDEPNumber", "");
		dynaactionform.set("spraypaint", "");
		dynaactionform.set("spraysolvant", "");
		dynaactionform.set("sprayink", "");
		dynaactionform.set("sprayother", "");
		dynaactionform.set("spraypaintname", "");
		dynaactionform.set("spraysolvantname", "");
		dynaactionform.set("sprayinkname", "");
		dynaactionform.set("sprayothername", "");
		dynaactionform.set("spraypaintvocpercent", "");
		dynaactionform.set("spraysolvantvocpercent", "");
		dynaactionform.set("sprayinkvocpercent", "");
		dynaactionform.set("sprayothervocpercent", "");
		dynaactionform.set("spraypaintvolume", "");
		dynaactionform.set("spraysolvantvolume", "");
		dynaactionform.set("sprayinkvolume", "");
		dynaactionform.set("sprayothervolume", "");
		dynaactionform.set("spraypaintdensity", "");
		dynaactionform.set("spraysolvantdensity", "");
		dynaactionform.set("sprayinkdensity", "");
		dynaactionform.set("sprayotherdensity", "");
		dynaactionform.set("spraypaintvoc", "");
		dynaactionform.set("spraysolvantvoc", "");
		dynaactionform.set("sprayinkvoc", "");
		dynaactionform.set("sprayothervoc", "");
		dynaactionform.set("spraypaintcap", "");
		dynaactionform.set("spraysolvantcap", "");
		dynaactionform.set("sprayinkcap", "");
		dynaactionform.set("vocyear", "");
		dynaactionform.set("vocmonth", "");
		dynaactionform.set("voccap", "");
		dynaactionform.set("vocmonthlyquantity", "");
		dynaactionform.set("vocmonthlylimit", "");
		dynaactionform.set("psHoursOfOperationyear", "");
		dynaactionform.set("dobsignoff", "");
		dynaactionform.set("dobnumber", "");
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("PSPRAY_DEP", "none");
		httpservletrequest.setAttribute("PSPRAY_STATE", "none");
		httpservletrequest.setAttribute("PSPRAY_LOC", "none");
		httpservletrequest.setAttribute("PSPRAY_DEP_LST", null);
		httpservletrequest.setAttribute("PSPRAY_LOC_LST", null);
		httpservletrequest.setAttribute("PSPRAY_STATE_LST", null);
		httpservletrequest.setAttribute("LOCATION", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL", null);
		return actionmapping.findForward("success");
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest) {
		try {
			boolean flag = UtilityObject
					.isBoroughValidInNyc(httpservletrequest);
			if (flag)
				httpservletrequest.setAttribute("is5Borough", "Y");
			boolean flag1 = UtilityObject.isFacilityInNy(httpservletrequest);
			if (flag1)
				httpservletrequest.setAttribute("isInNy", "Y");
			HttpSession httpsession = httpservletrequest.getSession();
			PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
					.getAttribute("PAINTSPRAY_OBJECT");
			if (paintsprayvo != null)
				httpservletrequest.setAttribute("showAddBtn", "Y");
			else
				httpservletrequest.setAttribute("showAddBtn", "N");
		} catch (Exception exception) {
		}
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PaintSprayVo paintsprayvo = (PaintSprayVo) httpsession
				.getAttribute("PAINTSPRAY_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/paintspray/"
					+ paintsprayvo.getId() + "-"
					+ paintsprayvo.getFacilityDesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/paintspray/"
					+ paintsprayvo.getId() + "-"
					+ paintsprayvo.getFacilityDesignatedId().trim();
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
				+ "/paintspray/" + paintsprayvo.getId() + "-"
				+ paintsprayvo.getFacilityDesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.PaintSprayAction.class);
	public static HashMap VALID_STATES;
	private String userAction;

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