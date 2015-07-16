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
import com.eespc.tracking.bo.FumehoodVo;
import com.eespc.tracking.bo.LandfillVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.myenum.AgencyLocationEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.FumehoodChemicalsEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.entity.FumehoodEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class FumehoodAction extends LookupDispatchAction {

	public FumehoodAction() {
		userAction = null;
	}

	protected Map getKeyMethodMap() {
		HashMap hashmap = new HashMap();
		hashmap.put("FumehoodForm.return", "returnTo");
		hashmap.put("FumehoodForm.save", "save");
		hashmap.put("FumehoodForm.reset", "reset");
		hashmap.put("FumehoodForm.delete", "delete");
		hashmap.put("FumehoodForm.dep", "dep");
		hashmap.put("FumehoodForm.loc", "location");
		return hashmap;
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		setDropDown(httpservletrequest);
		userAction = (String) dynavalidatorform.get("methodToCall");
		log.debug((new StringBuilder()).append("FumuhoodAction:execute() ")
				.append(userAction).toString());
		if (userAction != null && userAction.equalsIgnoreCase("Save"))
			return save(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("view"))
			return view(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("viewexist"))
			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("edit"))
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("delete"))
			return delete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Return"))
			return returnTo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("reset"))
			return reset(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Update"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("dep"))
			return dep(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depEdit"))
			return depEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depUpdate"))
			return depUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("depDelete"))
			return depDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("loc"))
			return location(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locEdit"))
			return locEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locUpdate"))
			return locUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("locDelete")) {
			return locDelete(actionmapping, actionform, httpservletrequest,
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
			HttpSession httpsession = httpservletrequest.getSession();
			dynavalidatorform.set("FumehoodId", "");
			dynavalidatorform.set("FumehoodFloor", "");
			dynavalidatorform.set("Fumehood_yearInstalled", "");
			dynavalidatorform.set("Fumehood_status", "");
			dynavalidatorform.set("Fumehood_disconnecteddate", "");
			dynavalidatorform.set("FumehoodMake", "");
			dynavalidatorform.set("FumehoodModel", "");
			dynavalidatorform.set("Fumehood_Chemical1", "");
			dynavalidatorform.set("Fumehood_Chemicalname1", "");
			dynavalidatorform.set("Fumehood_volume1", "");
			dynavalidatorform.set("Fumehood_density1", "");
			dynavalidatorform.set("Fumehood_voc1", "");
			dynavalidatorform.set("Fumehood_voct1", "");
			dynavalidatorform.set("Fumehood_Chemical2", "");
			dynavalidatorform.set("Fumehood_Chemicalname2", "");
			dynavalidatorform.set("Fumehood_volume2", "");
			dynavalidatorform.set("Fumehood_density2", "");
			dynavalidatorform.set("Fumehood_voc2", "");
			dynavalidatorform.set("Fumehood_voct2", "");
			dynavalidatorform.set("Fumehood_Chemical3", "");
			dynavalidatorform.set("Fumehood_Chemicalname3", "");
			dynavalidatorform.set("Fumehood_volume3", "");
			dynavalidatorform.set("Fumehood_density3", "");
			dynavalidatorform.set("Fumehood_voc3", "");
			dynavalidatorform.set("Fumehood_voct3", "");
			dynavalidatorform.set("Fumehood_Chemical4", "");
			dynavalidatorform.set("Fumehood_Chemicalname4", "");
			dynavalidatorform.set("Fumehood_volume4", "");
			dynavalidatorform.set("Fumehood_density4", "");
			dynavalidatorform.set("Fumehood_voc4", "");
			dynavalidatorform.set("Fumehood_voct4", "");
			dynavalidatorform.set("Fumehood_Chemical5", "");
			dynavalidatorform.set("Fumehood_Chemicalname5", "");
			dynavalidatorform.set("Fumehood_volume5", "");
			dynavalidatorform.set("Fumehood_density5", "");
			dynavalidatorform.set("Fumehood_voc5", "");
			dynavalidatorform.set("Fumehood_voct5", "");
			dynavalidatorform.set("Fumehood_voc", "");
			dynavalidatorform.set("Fumehood_dob", "");
			dynavalidatorform.set("FumehoodHoursOfOperation", "");
			dynavalidatorform.set("FumehoodTypesOfChemical", null);
			dynavalidatorform.set("FumehoodIncludeByDec", "");
			dynavalidatorform.set("FumehoodLocation", "");
			dynavalidatorform.set("FumehoodExemptedbyDec", "");
			dynavalidatorform.set("FumehoodDEPNumber", "");
			dynavalidatorform.set("FumehoodDEPIssueDate", "");
			dynavalidatorform.set("FumehoodDEPExpirationDate", "");
			dynavalidatorform.set("FumehoodDEPExpirationDateNote", "");
			dynavalidatorform.set("FumehoodLocIssueDate", "");
			dynavalidatorform.set("FumehoodLocExpirationDate", "");
			dynavalidatorform.set("FumehoodLocNumber", "");
			dynavalidatorform.set("FumehoodHoursOfOperation1", "");
			dynavalidatorform.set("FumehoodDEPNumber", "");
			dynavalidatorform.set("fcomments", "");
			dynavalidatorform.set("dobsignoff", "");
			dynavalidatorform.set("depPermitStatus", "");
			dynavalidatorform.set("dobPermitStatus", "");
			dynavalidatorform.set("annualPermitStatus", "");
			dynavalidatorform.set("lastInspectionDate", "");
			dynavalidatorform.set("nextInspectionDate", "");
			httpservletrequest.setAttribute("FUMEHOOD_DEP", "none");
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "none");
			httpservletrequest.setAttribute("FUMEHOOD_DEP_LST", null);
			httpservletrequest.setAttribute("FUMEHOOD_LOC_LST", null);
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
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, fumehoodvo);
		setScreenInfo(httpservletrequest);
		setFormVariable(dynaactionform, fumehoodvo, httpservletrequest);

		// refreshShowInfo(httpservletrequest, fumehoodvo);
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
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, fumehoodvo);
		setFormVariable(dynaactionform, fumehoodvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, fumehoodvo);
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
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, fumehoodvo);
		setFormVariable(dynaactionform, fumehoodvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, fumehoodvo);
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
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, fumehoodvo);
		setFormVariable(dynaactionform, fumehoodvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, fumehoodvo);
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
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, fumehoodvo);
		setFormVariable(dynaactionform, fumehoodvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, fumehoodvo);
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
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, fumehoodvo);
		setFormVariable(dynaactionform, fumehoodvo, httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/fumehood/"
				+ fumehoodvo.getId() + "-"
				+ fumehoodvo.getFacilityDesignatedId().trim())) {
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
		log.debug("FumehoodAction - In Add");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		FumehoodVo fumehoodvo = new FumehoodVo();
		fumehoodvo.setBuildingId(buildingvo.getId());
		fumehoodvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("FumehoodId"));
		fumehoodvo.setFloor((String) dynavalidatorform.get("FumehoodFloor"));
		fumehoodvo.setYearinstalled((String) dynavalidatorform
				.get("Fumehood_yearInstalled"));
		fumehoodvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("Fumehood_status")));
		fumehoodvo.setDisconnecteddate((String) dynavalidatorform
				.get("Fumehood_disconnecteddate"));
		fumehoodvo.setHrsOfOperation((String) dynavalidatorform
				.get("FumehoodHoursOfOperation"));
		fumehoodvo.setMake((String) dynavalidatorform.get("FumehoodMake"));
		fumehoodvo.setModel((String) dynavalidatorform.get("FumehoodModel"));
		fumehoodvo.setdob((String) dynavalidatorform.get("Fumehood_dob"));
		fumehoodvo.setChemical1((String) dynavalidatorform
				.get("Fumehood_Chemical1"));
		fumehoodvo.setChemicalname1((String) dynavalidatorform
				.get("Fumehood_Chemicalname1"));
		fumehoodvo.setVolume1((String) dynavalidatorform
				.get("Fumehood_volume1"));
		fumehoodvo.setDensity1((String) dynavalidatorform
				.get("Fumehood_density1"));
		fumehoodvo.setVoc1((String) dynavalidatorform.get("Fumehood_voc1"));
		fumehoodvo.setVoctot1((String) dynavalidatorform.get("Fumehood_voct1"));
		fumehoodvo.setChemical2((String) dynavalidatorform
				.get("Fumehood_Chemical2"));
		fumehoodvo.setChemicalname2((String) dynavalidatorform
				.get("Fumehood_Chemicalname2"));
		fumehoodvo.setVolume2((String) dynavalidatorform
				.get("Fumehood_volume2"));
		fumehoodvo.setDensity2((String) dynavalidatorform
				.get("Fumehood_density2"));
		fumehoodvo.setVoc2((String) dynavalidatorform.get("Fumehood_voc2"));
		fumehoodvo.setVoctot2((String) dynavalidatorform.get("Fumehood_voct2"));
		fumehoodvo.setChemical3((String) dynavalidatorform
				.get("Fumehood_Chemical3"));
		fumehoodvo.setChemicalname3((String) dynavalidatorform
				.get("Fumehood_Chemicalname3"));
		fumehoodvo.setVolume3((String) dynavalidatorform
				.get("Fumehood_volume3"));
		fumehoodvo.setDensity3((String) dynavalidatorform
				.get("Fumehood_density3"));
		fumehoodvo.setVoc3((String) dynavalidatorform.get("Fumehood_voc3"));
		fumehoodvo.setVoctot3((String) dynavalidatorform.get("Fumehood_voct3"));
		fumehoodvo.setChemical4((String) dynavalidatorform
				.get("Fumehood_Chemical4"));
		fumehoodvo.setChemicalname4((String) dynavalidatorform
				.get("Fumehood_Chemicalname4"));
		fumehoodvo.setVolume4((String) dynavalidatorform
				.get("Fumehood_volume4"));
		fumehoodvo.setDensity4((String) dynavalidatorform
				.get("Fumehood_density4"));
		fumehoodvo.setVoc4((String) dynavalidatorform.get("Fumehood_voc4"));
		fumehoodvo.setVoctot4((String) dynavalidatorform.get("Fumehood_voct4"));
		fumehoodvo.setChemical5((String) dynavalidatorform
				.get("Fumehood_Chemical5"));
		fumehoodvo.setChemicalname5((String) dynavalidatorform
				.get("Fumehood_Chemicalname5"));
		fumehoodvo.setVolume5((String) dynavalidatorform
				.get("Fumehood_volume5"));
		fumehoodvo.setDensity5((String) dynavalidatorform
				.get("Fumehood_density5"));
		fumehoodvo.setVoc5((String) dynavalidatorform.get("Fumehood_voc5"));
		fumehoodvo.setVoctot5((String) dynavalidatorform.get("Fumehood_voct5"));
		fumehoodvo.setvoc((String) dynavalidatorform.get("Fumehood_voc"));
		String s2 = (String) dynavalidatorform.get("Fumehood_fuelFrom");
		if (UtilityObject.isNotEmpty(s2))
			fumehoodvo.setStackId(Integer.parseInt(s2));
		String as[] = (String[]) (String[]) dynavalidatorform
				.get("FumehoodTypesOfChemical");
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < as.length; i++)
			arraylist.add(as[i]);

		fumehoodvo.setChemicalsUsedList(arraylist);
		s2 = (String) dynavalidatorform.get("FumehoodExemptedbyDec");
		if (s2.equals("Y"))
			fumehoodvo.setExemptedByDec(true);
		else
			fumehoodvo.setExemptedByDec(false);
		s2 = (String) dynavalidatorform.get("FumehoodIncludeByDec");
		if (s2.equals("Y"))
			fumehoodvo.setUnitIncludedInDecPermit(true);
		else
			fumehoodvo.setUnitIncludedInDecPermit(false);
		fumehoodvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("FumehoodLocation")));

		fumehoodvo.setFumehoodHoursOfOperation1((String) dynavalidatorform
				.get("FumehoodHoursOfOperation1"));
		fumehoodvo.setFumehoodDEPNumber((String) dynavalidatorform
				.get("FumehoodDEPNumber"));
		fumehoodvo.setFcomments((String) dynavalidatorform.get("fcomments"));
		fumehoodvo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));
		fumehoodvo.setDepPermitStatus((String) dynavalidatorform
				.get("depPermitStatus"));
		fumehoodvo.setDobPermitStatus((String) dynavalidatorform
				.get("dobPermitStatus"));
		fumehoodvo.setAnnualPermitStatus((String) dynavalidatorform
				.get("annualPermitStatus"));

		String s3 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("lastInspectionDate")), "yyyy-MM-dd");
		fumehoodvo.setLastInspectionDate(s3);

		s3 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("nextInspectionDate")), "yyyy-MM-dd");
		fumehoodvo.setNextInspectionDate(s3);

		byte byte0 = -99;
		try {
			int j = FumehoodEntity.add(fumehoodvo);
			fumehoodvo = FumehoodEntity.findByPrimaryKey(j);
			if (fumehoodvo != null) {
				FumehoodEntity.addChemical(fumehoodvo.getId(), arraylist);
				httpsession.setAttribute("FUMEHOOD_OBJECT", fumehoodvo);
				dynavalidatorform = setFormVariable(dynavalidatorform,
						fumehoodvo, httpservletrequest);
			}
			s = "Added Fumehood Successfully.";
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
				AgencyLocationEnum.get(fumehoodvo.getLocation()));
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("FUMEHOOD_DEP_LST", null);
		httpservletrequest.setAttribute("FUMEHOOD_LOC_LST", null);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		log.debug((new StringBuilder()).append("Fumehod The Chemical list = ")
				.append(fumehoodvo.getChemicalsUsedList().size()).toString());
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSprayAction - In Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Paint Spray ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FumehoodVo fumehoodvo = FumehoodEntity.findByPrimaryKey(i);
		if (fumehoodvo != null) {
			httpsession.setAttribute("FUMEHOOD_OBJECT", fumehoodvo);
			dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
					httpservletrequest);
		}
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		fumehoodvo = (FumehoodVo) httpsession.getAttribute("FUMEHOOD_OBJECT");
		try {
			FumehoodEntity.delete(fumehoodvo);
			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/fumehood/" + fumehoodvo.getId() + "-"
						+ fumehoodvo.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successfume");
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
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		String facilityDesignatedid = fumehoodvo.getFacilityDesignatedId();
		fumehoodvo.setBuildingId(buildingvo.getId());
		fumehoodvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("FumehoodId"));
		fumehoodvo.setFloor((String) dynavalidatorform.get("FumehoodFloor"));
		fumehoodvo.setHrsOfOperation((String) dynavalidatorform
				.get("FumehoodHoursOfOperation"));
		fumehoodvo.setYearinstalled((String) dynavalidatorform
				.get("Fumehood_yearInstalled"));
		fumehoodvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("Fumehood_status")));
		fumehoodvo.setDisconnecteddate((String) dynavalidatorform
				.get("Fumehood_disconnecteddate"));
		fumehoodvo.setMake((String) dynavalidatorform.get("FumehoodMake"));
		fumehoodvo.setModel((String) dynavalidatorform.get("FumehoodModel"));
		fumehoodvo.setdob((String) dynavalidatorform.get("Fumehood_dob"));
		fumehoodvo.setChemical1((String) dynavalidatorform
				.get("Fumehood_Chemical1"));
		fumehoodvo.setChemicalname1((String) dynavalidatorform
				.get("Fumehood_Chemicalname1"));
		fumehoodvo.setVolume1((String) dynavalidatorform
				.get("Fumehood_volume1"));
		fumehoodvo.setDensity1((String) dynavalidatorform
				.get("Fumehood_density1"));
		fumehoodvo.setVoc1((String) dynavalidatorform.get("Fumehood_voc1"));
		fumehoodvo.setVoctot1((String) dynavalidatorform.get("Fumehood_voct1"));
		fumehoodvo.setChemical2((String) dynavalidatorform
				.get("Fumehood_Chemical2"));
		fumehoodvo.setChemicalname2((String) dynavalidatorform
				.get("Fumehood_Chemicalname2"));
		fumehoodvo.setVolume2((String) dynavalidatorform
				.get("Fumehood_volume2"));
		fumehoodvo.setDensity2((String) dynavalidatorform
				.get("Fumehood_density2"));
		fumehoodvo.setVoc2((String) dynavalidatorform.get("Fumehood_voc2"));
		fumehoodvo.setVoctot2((String) dynavalidatorform.get("Fumehood_voct2"));
		fumehoodvo.setChemical3((String) dynavalidatorform
				.get("Fumehood_Chemical3"));
		fumehoodvo.setChemicalname3((String) dynavalidatorform
				.get("Fumehood_Chemicalname3"));
		fumehoodvo.setVolume3((String) dynavalidatorform
				.get("Fumehood_volume3"));
		fumehoodvo.setDensity3((String) dynavalidatorform
				.get("Fumehood_density3"));
		fumehoodvo.setVoc3((String) dynavalidatorform.get("Fumehood_voc3"));
		fumehoodvo.setVoctot3((String) dynavalidatorform.get("Fumehood_voct3"));
		fumehoodvo.setChemical4((String) dynavalidatorform
				.get("Fumehood_Chemical4"));
		fumehoodvo.setChemicalname4((String) dynavalidatorform
				.get("Fumehood_Chemicalname4"));
		fumehoodvo.setVolume4((String) dynavalidatorform
				.get("Fumehood_volume4"));
		fumehoodvo.setDensity4((String) dynavalidatorform
				.get("Fumehood_density4"));
		fumehoodvo.setVoc4((String) dynavalidatorform.get("Fumehood_voc4"));
		fumehoodvo.setVoctot4((String) dynavalidatorform.get("Fumehood_voct4"));
		fumehoodvo.setChemical5((String) dynavalidatorform
				.get("Fumehood_Chemical5"));
		fumehoodvo.setChemicalname5((String) dynavalidatorform
				.get("Fumehood_Chemicalname5"));
		fumehoodvo.setVolume5((String) dynavalidatorform
				.get("Fumehood_volume5"));
		fumehoodvo.setDensity5((String) dynavalidatorform
				.get("Fumehood_density5"));
		fumehoodvo.setVoc5((String) dynavalidatorform.get("Fumehood_voc5"));
		fumehoodvo.setVoctot5((String) dynavalidatorform.get("Fumehood_voct5"));
		fumehoodvo.setvoc((String) dynavalidatorform.get("Fumehood_voc"));
		String s2 = (String) dynavalidatorform.get("Fumehood_fuelFrom");
		if (UtilityObject.isNotEmpty(s2))
			fumehoodvo.setStackId(Integer.parseInt(s2));
		String as[] = (String[]) (String[]) dynavalidatorform
				.get("FumehoodTypesOfChemical");
		ArrayList arraylist = new ArrayList();
		for (int i = 0; i < as.length; i++)
			arraylist.add(as[i]);

		fumehoodvo.setChemicalsUsedList(arraylist);
		s2 = (String) dynavalidatorform.get("FumehoodExemptedbyDec");
		if (s2.equals("Y"))
			fumehoodvo.setExemptedByDec(true);
		else
			fumehoodvo.setExemptedByDec(false);
		s2 = (String) dynavalidatorform.get("FumehoodIncludeByDec");
		if (s2.equals("Y"))
			fumehoodvo.setUnitIncludedInDecPermit(true);
		else
			fumehoodvo.setUnitIncludedInDecPermit(false);
		fumehoodvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("FumehoodLocation")));
		fumehoodvo.setFumehoodHoursOfOperation1((String) dynavalidatorform
				.get("FumehoodHoursOfOperation1"));
		fumehoodvo.setFumehoodDEPNumber((String) dynavalidatorform
				.get("FumehoodDEPNumber"));
		fumehoodvo.setFcomments((String) dynavalidatorform.get("fcomments"));
		fumehoodvo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));
		fumehoodvo.setDepPermitStatus((String) dynavalidatorform
				.get("depPermitStatus"));
		fumehoodvo.setDobPermitStatus((String) dynavalidatorform
				.get("dobPermitStatus"));
		fumehoodvo.setAnnualPermitStatus((String) dynavalidatorform
				.get("annualPermitStatus"));

		String s3 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("lastInspectionDate")), "yyyy-MM-dd");
		fumehoodvo.setLastInspectionDate(s3);

		s3 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("nextInspectionDate")), "yyyy-MM-dd");
		fumehoodvo.setNextInspectionDate(s3);
		FumehoodVo fumehoodvo1 = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		try {
			FumehoodEntity.update(fumehoodvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/fumehood/" + fumehoodvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {
					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/fumehood/"
									+ fumehoodvo.getId()
									+ "-"
									+ fumehoodvo.getFacilityDesignatedId()
											.trim());
					f.renameTo(newFileName);
				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			if (arraylist != null)
				FumehoodEntity.updateChemical(fumehoodvo.getId(), arraylist);
			fumehoodvo = FumehoodEntity.findByPrimaryKey(fumehoodvo1.getId());
			refreshBuildingObject(httpservletrequest);
			s = "Updated Fumehood Successfully.";
			s1 = "CONFIRMATION";
			dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
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
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(fumehoodvo.getLocation()));
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
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
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FumehoodVo fumehoodvo = FumehoodEntity.findByPrimaryKey(i);
		if (fumehoodvo != null) {
			httpsession.setAttribute("FUMEHOOD_OBJECT", fumehoodvo);
			dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get Fumehood for id(").append(s)
					.append(")").toString());
		}
		try {
			dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
					httpservletrequest);
		} catch (Exception exception) {
			log.debug((new StringBuilder()).append("Error : ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshPermitInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("FumehoodForm - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Fume hood ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FumehoodVo fumehoodvo = FumehoodEntity.findByPrimaryKey(i);
		if (fumehoodvo != null) {
			httpsession.setAttribute("FUMEHOOD_OBJECT", fumehoodvo);
			dynavalidatorform = setFormVariableforedit(dynavalidatorform,
					fumehoodvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get FumehoodVo for id(").append(s)
					.append(")").toString());
		}
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
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
		log.debug("FumehoodForm - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		log.debug((new StringBuilder()).append("Fume hood ID = ").append(s)
				.toString());
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		FumehoodVo fumehoodvo = FumehoodEntity.findByPrimaryKey(i);
		if (fumehoodvo != null) {
			httpsession.setAttribute("FUMEHOOD_OBJECT", fumehoodvo);
			dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
					fumehoodvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get FumehoodVo for id(").append(s)
					.append(")").toString());
		}
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
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

	public ActionForward dep(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("FumehoodAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(fumehoodvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Fumehood")
				.getCode());
		s = (String) dynavalidatorform.get("FumehoodDEPNumber");
		s1 = (String) dynavalidatorform.get("FumehoodDEPIssueDate");
		s2 = (String) dynavalidatorform.get("FumehoodDEPExpirationDate");
		s3 = (String) dynavalidatorform.get("FumehoodDEPExpirationDateNote");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		FumehoodEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		dynavalidatorform.set("FumehoodDEPIssueDate", "");
		dynavalidatorform.set("FumehoodDEPExpirationDate", "");
		dynavalidatorform.set("FumehoodDEPExpirationDateNote", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward depEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("Fumehood Dep Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("FumehoodDEPNumber");
		String s1 = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		// if(s != null)
		// dynavalidatorform.set("FumehoodDEPNumber", s);

		if (s1 != null && s1.trim().equalsIgnoreCase("")) {
			String s2 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s2);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s1);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward depUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("FumehoodAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
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
		permitinfovo.setObjectId(fumehoodvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Fumehood")
				.getCode());
		s1 = (String) dynavalidatorform.get("FumehoodDEPNumber");
		System.out.println((new StringBuilder()).append("good").append(s1)
				.toString());
		s2 = (String) dynavalidatorform.get("FumehoodDEPIssueDate");
		s3 = (String) dynavalidatorform.get("FumehoodDEPExpirationDate");
		s4 = (String) dynavalidatorform.get("FumehoodDEPExpirationDateNote");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		FumehoodEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		dynavalidatorform.set("FumehoodDEPIssueDate", "");
		dynavalidatorform.set("FumehoodDEPExpirationDate", "");
		dynavalidatorform.set("FumehoodDEPExpirationDateNote", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward depDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("FumehoodAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
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
		permitinfovo.setId(Integer.parseInt(s));
		FumehoodEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		dynavalidatorform.set("FumehoodDEPIssueDate", "");
		dynavalidatorform.set("FumehoodDEPExpirationDate", "");
		dynavalidatorform.set("FumehoodDEPExpirationDateNote", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward location(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("PaintSpray Loc");
		log.debug("PaintSprayAction - In LOC");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
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
		permitinfovo.setObjectId(fumehoodvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Fumehood")
				.getCode());
		s = (String) dynavalidatorform.get("FumehoodLocNumber");
		s1 = (String) dynavalidatorform.get("FumehoodLocIssueDate");
		s2 = (String) dynavalidatorform.get("FumehoodLocExpirationDate");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		FumehoodEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		dynavalidatorform.set("FumehoodLocIssueDate", "");
		dynavalidatorform.set("FumehoodLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
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
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_LOC_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		return actionmapping.findForward("success");
	}

	public ActionForward locUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Fumehood Loc");
		log.debug("FumehoodAction - In LOC - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
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
		permitinfovo.setObjectId(fumehoodvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Fumehood")
				.getCode());
		permitinfovo.setId(Integer.parseInt(s));
		s1 = (String) dynavalidatorform.get("FumehoodLocNumber");
		s2 = (String) dynavalidatorform.get("FumehoodLocIssueDate");
		s3 = (String) dynavalidatorform.get("FumehoodLocExpirationDate");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		FumehoodEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		dynavalidatorform.set("FumehoodLocIssueDate", "");
		dynavalidatorform.set("FumehoodLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward locDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Fumehood Loc");
		log.debug("FumehoodAction - In LOC - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
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
		FumehoodEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, fumehoodvo,
				httpservletrequest);
		dynavalidatorform.set("FumehoodLocIssueDate", "");
		dynavalidatorform.set("FumehoodLocExpirationDate", "");
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward inspectionEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		return actionmapping.findForward("success");
	}

	public ActionForward inspectionUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		return actionmapping.findForward("success");
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
		/*
		 * log.debug("FumehoodAction:DisplayControl"); return
		 * actionmapping.findForward("success");
		 */
		log.debug("FumehoodAction:DisplayControl");
		System.out.println("Test:"
				+ httpservletrequest.getParameter("hdnContext"));
		String s = httpservletrequest.getParameter("hdnContext");
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			System.out.println("Test Good:");

			httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
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
		dynavalidatorform.set("FumehoodId", "");
		dynavalidatorform.set("FumehoodFloor", "");
		dynavalidatorform.set("Fumehood_yearInstalled", "");
		dynavalidatorform.set("Fumehood_status", "");
		dynavalidatorform.set("Fumehood_disconnecteddate", "");
		dynavalidatorform.set("FumehoodMake", "");
		dynavalidatorform.set("FumehoodModel", "");
		dynavalidatorform.set("Fumehood_Chemical1", "");
		dynavalidatorform.set("Fumehood_Chemicalname1", "");
		dynavalidatorform.set("Fumehood_volume1", "");
		dynavalidatorform.set("Fumehood_density1", "");
		dynavalidatorform.set("Fumehood_voc1", "");
		dynavalidatorform.set("Fumehood_voct1", "");
		dynavalidatorform.set("Fumehood_Chemical2", "");
		dynavalidatorform.set("Fumehood_Chemicalname2", "");
		dynavalidatorform.set("Fumehood_volume2", "");
		dynavalidatorform.set("Fumehood_density2", "");
		dynavalidatorform.set("Fumehood_voc2", "");
		dynavalidatorform.set("Fumehood_voct2", "");
		dynavalidatorform.set("Fumehood_Chemical3", "");
		dynavalidatorform.set("Fumehood_Chemicalname3", "");
		dynavalidatorform.set("Fumehood_volume3", "");
		dynavalidatorform.set("Fumehood_density3", "");
		dynavalidatorform.set("Fumehood_voc3", "");
		dynavalidatorform.set("Fumehood_voct3", "");
		dynavalidatorform.set("Fumehood_Chemical4", "");
		dynavalidatorform.set("Fumehood_Chemicalname4", "");
		dynavalidatorform.set("Fumehood_volume4", "");
		dynavalidatorform.set("Fumehood_density4", "");
		dynavalidatorform.set("Fumehood_voc4", "");
		dynavalidatorform.set("Fumehood_voct4", "");
		dynavalidatorform.set("Fumehood_Chemical5", "");
		dynavalidatorform.set("Fumehood_Chemicalname5", "");
		dynavalidatorform.set("Fumehood_volume5", "");
		dynavalidatorform.set("Fumehood_density5", "");
		dynavalidatorform.set("Fumehood_voc5", "");
		dynavalidatorform.set("Fumehood_voct5", "");
		dynavalidatorform.set("Fumehood_voc", "");
		dynavalidatorform.set("FumehoodHoursOfOperation", "");
		dynavalidatorform.set("FumehoodTypesOfChemical", null);
		dynavalidatorform.set("FumehoodExemptedbyDec", "");
		dynavalidatorform.set("FumehoodIncludeByDec", "");
		dynavalidatorform.set("FumehoodLocation", "");
		dynavalidatorform.set("Fumehood_dob", "");
		dynavalidatorform.set("FumehoodHoursOfOperation1", "");
		dynavalidatorform.set("FumehoodDEPNumber", "");
		dynavalidatorform.set("fcomments", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("depPermitStatus", "");
		dynavalidatorform.set("dobPermitStatus", "");
		dynavalidatorform.set("annualPermitStatus", "");
		dynavalidatorform.set("lastInspectionDate", "");
		dynavalidatorform.set("nextInspectionDate", "");
		httpservletrequest.setAttribute("FumehoodLocation", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		setDropDown(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public DynaValidatorForm setFormVariableforedit(
			DynaValidatorForm dynavalidatorform, FumehoodVo fumehoodvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Set Form Variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"FumehoodId",
				(new StringBuilder())
						.append(fumehoodvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("FumehoodFloor",
				(new StringBuilder()).append(fumehoodvo.getFloor()).append("")
						.toString());
		dynavalidatorform1.set("Fumehood_yearInstalled", (new StringBuilder())
				.append(checkNullAndFill(fumehoodvo.getYearinstalled(), ""))
				.append("").toString());
		dynavalidatorform1.set("Fumehood_status",
				(new StringBuilder()).append(fumehoodvo.getStatus()).append("")
						.toString());
		dynavalidatorform1.set(
				"Fumehood_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								fumehoodvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("FumehoodMake",
				(new StringBuilder()).append(fumehoodvo.getMake()).append("")
						.toString());
		dynavalidatorform1.set("FumehoodModel",
				(new StringBuilder()).append(fumehoodvo.getModel()).append("")
						.toString());
		dynavalidatorform1.set("Fumehood_Chemical1",
				checkNullAndFill(fumehoodvo.getChemical1(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname1",
				checkNullAndFill(fumehoodvo.getChemicalname1(), ""));
		dynavalidatorform1.set("Fumehood_volume1",
				checkNullAndFill(fumehoodvo.getVolume1(), ""));
		dynavalidatorform1.set("Fumehood_density1",
				checkNullAndFill(fumehoodvo.getDensity1(), ""));
		dynavalidatorform1.set("Fumehood_voc1",
				checkNullAndFill(fumehoodvo.getVoc1(), ""));
		dynavalidatorform1.set("Fumehood_voct1",
				checkNullAndFill(fumehoodvo.getVoctot1(), ""));
		dynavalidatorform1.set("Fumehood_Chemical2",
				checkNullAndFill(fumehoodvo.getChemical2(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname2",
				checkNullAndFill(fumehoodvo.getChemicalname2(), ""));
		dynavalidatorform1.set("Fumehood_volume2",
				checkNullAndFill(fumehoodvo.getVolume2(), ""));
		dynavalidatorform1.set("Fumehood_density2",
				checkNullAndFill(fumehoodvo.getDensity2(), ""));
		dynavalidatorform1.set("Fumehood_voc2",
				checkNullAndFill(fumehoodvo.getVoc2(), ""));
		dynavalidatorform1.set("Fumehood_voct2",
				checkNullAndFill(fumehoodvo.getVoctot2(), ""));
		dynavalidatorform1.set("Fumehood_Chemical3",
				checkNullAndFill(fumehoodvo.getChemical3(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname3",
				checkNullAndFill(fumehoodvo.getChemicalname3(), ""));
		dynavalidatorform1.set("Fumehood_volume3",
				checkNullAndFill(fumehoodvo.getVolume3(), ""));
		dynavalidatorform1.set("Fumehood_density3",
				checkNullAndFill(fumehoodvo.getDensity3(), ""));
		dynavalidatorform1.set("Fumehood_voc3",
				checkNullAndFill(fumehoodvo.getVoc3(), ""));
		dynavalidatorform1.set("Fumehood_voct3",
				checkNullAndFill(fumehoodvo.getVoctot3(), ""));
		dynavalidatorform1.set("Fumehood_Chemical4",
				checkNullAndFill(fumehoodvo.getChemical4(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname4",
				checkNullAndFill(fumehoodvo.getChemicalname4(), ""));
		dynavalidatorform1.set("Fumehood_volume4",
				checkNullAndFill(fumehoodvo.getVolume4(), ""));
		dynavalidatorform1.set("Fumehood_density4",
				checkNullAndFill(fumehoodvo.getDensity4(), ""));
		dynavalidatorform1.set("Fumehood_voc4",
				checkNullAndFill(fumehoodvo.getVoc4(), ""));
		dynavalidatorform1.set("Fumehood_voct4",
				checkNullAndFill(fumehoodvo.getVoctot4(), ""));
		dynavalidatorform1.set("Fumehood_Chemical5",
				checkNullAndFill(fumehoodvo.getChemical5(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname5",
				checkNullAndFill(fumehoodvo.getChemicalname5(), ""));
		dynavalidatorform1.set("Fumehood_volume5",
				checkNullAndFill(fumehoodvo.getVolume5(), ""));
		dynavalidatorform1.set("Fumehood_density5",
				checkNullAndFill(fumehoodvo.getDensity5(), ""));
		dynavalidatorform1.set("Fumehood_voc5",
				checkNullAndFill(fumehoodvo.getVoc5(), ""));
		dynavalidatorform1.set("Fumehood_voct5",
				checkNullAndFill(fumehoodvo.getVoctot5(), ""));
		dynavalidatorform1.set("Fumehood_voc",
				(new StringBuilder()).append(fumehoodvo.getvoc()).append("")
						.toString());
		dynavalidatorform1.set("FumehoodHoursOfOperation",
				(new StringBuilder()).append(fumehoodvo.getHrsOfOperation())
						.append("").toString());
		com.eespc.tracking.bo.DropDown dropdown = FumehoodChemicalsEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("FUMEHOOD_CHEMICAL", dropdown);
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		dynavalidatorform1.set("FumehoodIncludeByDec", "Y");
		dynavalidatorform1.set("FumehoodHoursOfOperation1",
				fumehoodvo.getFumehoodHoursOfOperation1());
		dynavalidatorform1.set("FumehoodDEPNumber",
				fumehoodvo.getFumehoodDEPNumber());
		dynavalidatorform1.set("fcomments", fumehoodvo.getFcomments());
		dynavalidatorform1.set("dobsignoff", fumehoodvo.getDobsignoff());
		dynavalidatorform1.set("depPermitStatus",
				fumehoodvo.isDepPermitStatus());
		dynavalidatorform1.set("dobPermitStatus",
				fumehoodvo.isDobPermitStatus());
		dynavalidatorform1.set("annualPermitStatus",
				fumehoodvo.isAnnualPermitStatus());

		String s = fumehoodvo.getLastInspectionDate();
		dynavalidatorform1.set("lastInspectionDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(s));

		String s1 = fumehoodvo.getNextInspectionDate();
		dynavalidatorform1.set("nextInspectionDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));

		dynavalidatorform1.set("Fumehood_dob",
				(new StringBuilder()).append(fumehoodvo.getdob()).append("")
						.toString());
		if (!userAction.equalsIgnoreCase("edit"))
			dynavalidatorform1.set("FumehoodLocation", (new StringBuilder())
					.append(AgencyLocationEnum.get(fumehoodvo.getLocation()))
					.append("").toString());
		else
			dynavalidatorform1.set("FumehoodLocation", (new StringBuilder())
					.append(fumehoodvo.getLocation()).append("").toString());
		StackVo stackvo = fumehoodvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set("Fumehood_fuelFrom", (new StringBuilder())
					.append(stackvo.getStackId()).append("").toString());
			dynavalidatorform1.set("Fumehood_fuelFromName",
					stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("Fumehood_fuelFrom", "");
			dynavalidatorform1.set("Fumehood_fuelFromName", "");
		}
		dynavalidatorform1.set("FumehoodExemptedbyDec", "N");
		boolean flag = fumehoodvo.isExemptedByDec();
		if (flag)
			dynavalidatorform1.set("FumehoodExemptedbyDec", "Y");
		else
			dynavalidatorform1.set("FumehoodExemptedbyDec", "N");
		flag = fumehoodvo.isUnitIncludedInDecPermit();
		if (flag)
			dynavalidatorform1.set("FumehoodIncludeByDec", "Y");
		else
			dynavalidatorform1.set("FumehoodIncludeByDec", "N");
		int i = fumehoodvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		else
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "none");
		try {
			List list = fumehoodvo.getChemicalsUsedList();
			String as[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				FumehoodChemicalsEnum fumehoodchemicalsenum = FumehoodChemicalsEnum
						.parse(String.valueOf(list.get(j)));
				as[j] = String.valueOf(fumehoodchemicalsenum.getCode());
				log.debug(as[j].toString());
			}

			dynavalidatorform1.set("FumehoodTypesOfChemical", as);
		} catch (Exception exception) {
			log.debug((new StringBuilder())
					.append("FumehoodAction.setFormVariable() ")
					.append(exception.toString()).toString());
		}

		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(i));
		refreshPermitInfo(httpservletrequest);
		List list1 = (List) httpservletrequest.getAttribute("FUMEHOOD_DEP_LST");
		if (list1 != null && list1.size() > 0) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(0);
			log.debug(permitinfovo.getPermitNumber());
			// dynavalidatorform1.set("FumehoodDEPNumber",
			// permitinfovo.getPermitNumber());
		}
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		setScreenInfo(httpservletrequest);
		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, FumehoodVo fumehoodvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Set Form Variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"FumehoodId",
				(new StringBuilder())
						.append(fumehoodvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("FumehoodFloor",
				(new StringBuilder()).append(fumehoodvo.getFloor()).append("")
						.toString());
		dynavalidatorform1.set("Fumehood_yearInstalled", (new StringBuilder())
				.append(checkNullAndFill(fumehoodvo.getYearinstalled(), ""))
				.append("").toString());
		dynavalidatorform1.set("Fumehood_status",
				(new StringBuilder()).append(fumehoodvo.getStatus()).append("")
						.toString());
		dynavalidatorform1.set(
				"Fumehood_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								fumehoodvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("FumehoodMake",
				(new StringBuilder()).append(fumehoodvo.getMake()).append("")
						.toString());
		dynavalidatorform1.set("FumehoodModel",
				(new StringBuilder()).append(fumehoodvo.getModel()).append("")
						.toString());
		dynavalidatorform1.set("Fumehood_Chemical1",
				checkNullAndFill(fumehoodvo.getChemical1(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname1",
				checkNullAndFill(fumehoodvo.getChemicalname1(), ""));
		dynavalidatorform1.set("Fumehood_volume1",
				checkNullAndFill(fumehoodvo.getVolume1(), ""));
		dynavalidatorform1.set("Fumehood_density1",
				checkNullAndFill(fumehoodvo.getDensity1(), ""));
		dynavalidatorform1.set("Fumehood_voc1",
				checkNullAndFill(fumehoodvo.getVoc1(), ""));
		dynavalidatorform1.set("Fumehood_voct1",
				checkNullAndFill(fumehoodvo.getVoctot1(), ""));
		dynavalidatorform1.set("Fumehood_Chemical2",
				checkNullAndFill(fumehoodvo.getChemical2(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname2",
				checkNullAndFill(fumehoodvo.getChemicalname2(), ""));
		dynavalidatorform1.set("Fumehood_volume2",
				checkNullAndFill(fumehoodvo.getVolume2(), ""));
		dynavalidatorform1.set("Fumehood_density2",
				checkNullAndFill(fumehoodvo.getDensity2(), ""));
		dynavalidatorform1.set("Fumehood_voc2",
				checkNullAndFill(fumehoodvo.getVoc2(), ""));
		dynavalidatorform1.set("Fumehood_voct2",
				checkNullAndFill(fumehoodvo.getVoctot2(), ""));
		dynavalidatorform1.set("Fumehood_Chemical3",
				checkNullAndFill(fumehoodvo.getChemical3(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname3",
				checkNullAndFill(fumehoodvo.getChemicalname3(), ""));
		dynavalidatorform1.set("Fumehood_volume3",
				checkNullAndFill(fumehoodvo.getVolume3(), ""));
		dynavalidatorform1.set("Fumehood_density3",
				checkNullAndFill(fumehoodvo.getDensity3(), ""));
		dynavalidatorform1.set("Fumehood_voc3",
				checkNullAndFill(fumehoodvo.getVoc3(), ""));
		dynavalidatorform1.set("Fumehood_voct3",
				checkNullAndFill(fumehoodvo.getVoctot3(), ""));
		dynavalidatorform1.set("Fumehood_Chemical4",
				checkNullAndFill(fumehoodvo.getChemical4(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname4",
				checkNullAndFill(fumehoodvo.getChemicalname4(), ""));
		dynavalidatorform1.set("Fumehood_volume4",
				checkNullAndFill(fumehoodvo.getVolume4(), ""));
		dynavalidatorform1.set("Fumehood_density4",
				checkNullAndFill(fumehoodvo.getDensity4(), ""));
		dynavalidatorform1.set("Fumehood_voc4",
				checkNullAndFill(fumehoodvo.getVoc4(), ""));
		dynavalidatorform1.set("Fumehood_voct4",
				checkNullAndFill(fumehoodvo.getVoctot4(), ""));
		dynavalidatorform1.set("Fumehood_Chemical5",
				checkNullAndFill(fumehoodvo.getChemical5(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname5",
				checkNullAndFill(fumehoodvo.getChemicalname5(), ""));
		dynavalidatorform1.set("Fumehood_volume5",
				checkNullAndFill(fumehoodvo.getVolume5(), ""));
		dynavalidatorform1.set("Fumehood_density5",
				checkNullAndFill(fumehoodvo.getDensity5(), ""));
		dynavalidatorform1.set("Fumehood_voc5",
				checkNullAndFill(fumehoodvo.getVoc5(), ""));
		dynavalidatorform1.set("Fumehood_voct5",
				checkNullAndFill(fumehoodvo.getVoctot5(), ""));
		dynavalidatorform1.set("Fumehood_voc",
				(new StringBuilder()).append(fumehoodvo.getvoc()).append("")
						.toString());
		dynavalidatorform1.set("FumehoodHoursOfOperation",
				(new StringBuilder()).append(fumehoodvo.getHrsOfOperation())
						.append("").toString());
		com.eespc.tracking.bo.DropDown dropdown = FumehoodChemicalsEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("FUMEHOOD_CHEMICAL", dropdown);
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		dynavalidatorform1.set("FumehoodIncludeByDec", "Y");
		dynavalidatorform1.set("FumehoodHoursOfOperation1",
				fumehoodvo.getFumehoodHoursOfOperation1());
		dynavalidatorform1.set("FumehoodDEPNumber",
				fumehoodvo.getFumehoodDEPNumber());
		dynavalidatorform1.set("fcomments", fumehoodvo.getFcomments());
		dynavalidatorform1.set("dobsignoff", fumehoodvo.getDobsignoff());
		dynavalidatorform1.set("depPermitStatus",
				fumehoodvo.isDepPermitStatus());
		dynavalidatorform1.set("dobPermitStatus",
				fumehoodvo.isDobPermitStatus());
		dynavalidatorform1.set("annualPermitStatus",
				fumehoodvo.isAnnualPermitStatus());

		String s = fumehoodvo.getLastInspectionDate();
		dynavalidatorform1.set("lastInspectionDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(s));

		String s1 = fumehoodvo.getNextInspectionDate();
		dynavalidatorform1.set("nextInspectionDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
		dynavalidatorform1.set("Fumehood_dob",
				(new StringBuilder()).append(fumehoodvo.getdob()).append("")
						.toString());
		if (!userAction.equalsIgnoreCase("viewexist"))
			dynavalidatorform1.set("FumehoodLocation", (new StringBuilder())
					.append(AgencyLocationEnum.get(fumehoodvo.getLocation()))
					.append("").toString());
		else
			dynavalidatorform1.set("FumehoodLocation", (new StringBuilder())
					.append(fumehoodvo.getLocation()).append("").toString());
		StackVo stackvo = fumehoodvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set("Fumehood_fuelFrom", (new StringBuilder())
					.append(stackvo.getStackId()).append("").toString());
			dynavalidatorform1.set("Fumehood_fuelFromName",
					stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("Fumehood_fuelFrom", "");
			dynavalidatorform1.set("Fumehood_fuelFromName", "");
		}
		dynavalidatorform1.set("FumehoodExemptedbyDec", "N");
		boolean flag = fumehoodvo.isExemptedByDec();
		if (flag)
			dynavalidatorform1.set("FumehoodExemptedbyDec", "Y");
		else
			dynavalidatorform1.set("FumehoodExemptedbyDec", "N");
		flag = fumehoodvo.isUnitIncludedInDecPermit();
		if (flag)
			dynavalidatorform1.set("FumehoodIncludeByDec", "Y");
		else
			dynavalidatorform1.set("FumehoodIncludeByDec", "N");
		int i = fumehoodvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		else
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "none");
		try {
			List list = fumehoodvo.getChemicalsUsedList();
			String as[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				FumehoodChemicalsEnum fumehoodchemicalsenum = FumehoodChemicalsEnum
						.parse(String.valueOf(list.get(j)));
				as[j] = String.valueOf(fumehoodchemicalsenum.getCode());
				log.debug(as[j].toString());
			}

			dynavalidatorform1.set("FumehoodTypesOfChemical", as);
		} catch (Exception exception) {
			log.debug((new StringBuilder())
					.append("FumehoodAction.setFormVariable() ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(i));
		refreshPermitInfo(httpservletrequest);
		List list1 = (List) httpservletrequest.getAttribute("FUMEHOOD_DEP_LST");
		if (list1 != null && list1.size() > 0) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(0);
			log.debug(permitinfovo.getPermitNumber());
			// dynavalidatorform1.set("FumehoodDEPNumber",
			// permitinfovo.getPermitNumber());
		}
		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariable(
			DynaValidatorForm dynavalidatorform, FumehoodVo fumehoodvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Set Form Variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"FumehoodId",
				(new StringBuilder())
						.append(fumehoodvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("FumehoodFloor",
				(new StringBuilder()).append(fumehoodvo.getFloor()).append("")
						.toString());
		dynavalidatorform1.set("Fumehood_yearInstalled", (new StringBuilder())
				.append(checkNullAndFill(fumehoodvo.getYearinstalled(), ""))
				.append("").toString());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(fumehoodvo.getStatus());
		if (tankoperatingstatusenum != null)
			dynavalidatorform1.set("Fumehood_status",
					tankoperatingstatusenum.getName());
		dynavalidatorform1.set(
				"Fumehood_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								fumehoodvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("FumehoodMake",
				(new StringBuilder()).append(fumehoodvo.getMake()).append("")
						.toString());
		dynavalidatorform1.set("FumehoodModel",
				(new StringBuilder()).append(fumehoodvo.getModel()).append("")
						.toString());
		dynavalidatorform1.set("Fumehood_Chemical1",
				checkNullAndFill(fumehoodvo.getChemical1(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname1",
				checkNullAndFill(fumehoodvo.getChemicalname1(), ""));
		dynavalidatorform1.set("Fumehood_volume1",
				checkNullAndFill(fumehoodvo.getVolume1(), ""));
		dynavalidatorform1.set("Fumehood_density1",
				checkNullAndFill(fumehoodvo.getDensity1(), ""));
		dynavalidatorform1.set("Fumehood_voc1",
				checkNullAndFill(fumehoodvo.getVoc1(), ""));
		dynavalidatorform1.set("Fumehood_voct1",
				checkNullAndFill(fumehoodvo.getVoctot1(), ""));
		dynavalidatorform1.set("Fumehood_Chemical2",
				checkNullAndFill(fumehoodvo.getChemical2(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname2",
				checkNullAndFill(fumehoodvo.getChemicalname2(), ""));
		dynavalidatorform1.set("Fumehood_volume2",
				checkNullAndFill(fumehoodvo.getVolume2(), ""));
		dynavalidatorform1.set("Fumehood_density2",
				checkNullAndFill(fumehoodvo.getDensity2(), ""));
		dynavalidatorform1.set("Fumehood_voc2",
				checkNullAndFill(fumehoodvo.getVoc2(), ""));
		dynavalidatorform1.set("Fumehood_voct2",
				checkNullAndFill(fumehoodvo.getVoctot2(), ""));
		dynavalidatorform1.set("Fumehood_Chemical3",
				checkNullAndFill(fumehoodvo.getChemical3(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname3",
				checkNullAndFill(fumehoodvo.getChemicalname3(), ""));
		dynavalidatorform1.set("Fumehood_volume3",
				checkNullAndFill(fumehoodvo.getVolume3(), ""));
		dynavalidatorform1.set("Fumehood_density3",
				checkNullAndFill(fumehoodvo.getDensity3(), ""));
		dynavalidatorform1.set("Fumehood_voc3",
				checkNullAndFill(fumehoodvo.getVoc3(), ""));
		dynavalidatorform1.set("Fumehood_voct3",
				checkNullAndFill(fumehoodvo.getVoctot3(), ""));
		dynavalidatorform1.set("Fumehood_Chemical4",
				checkNullAndFill(fumehoodvo.getChemical4(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname4",
				checkNullAndFill(fumehoodvo.getChemicalname4(), ""));
		dynavalidatorform1.set("Fumehood_volume4",
				checkNullAndFill(fumehoodvo.getVolume4(), ""));
		dynavalidatorform1.set("Fumehood_density4",
				checkNullAndFill(fumehoodvo.getDensity4(), ""));
		dynavalidatorform1.set("Fumehood_voc4",
				checkNullAndFill(fumehoodvo.getVoc4(), ""));
		dynavalidatorform1.set("Fumehood_voct4",
				checkNullAndFill(fumehoodvo.getVoctot4(), ""));
		dynavalidatorform1.set("Fumehood_Chemical5",
				checkNullAndFill(fumehoodvo.getChemical5(), ""));
		dynavalidatorform1.set("Fumehood_Chemicalname5",
				checkNullAndFill(fumehoodvo.getChemicalname5(), ""));
		dynavalidatorform1.set("Fumehood_volume5",
				checkNullAndFill(fumehoodvo.getVolume5(), ""));
		dynavalidatorform1.set("Fumehood_density5",
				checkNullAndFill(fumehoodvo.getDensity5(), ""));
		dynavalidatorform1.set("Fumehood_voc5",
				checkNullAndFill(fumehoodvo.getVoc5(), ""));
		dynavalidatorform1.set("Fumehood_voct5",
				checkNullAndFill(fumehoodvo.getVoctot5(), ""));
		dynavalidatorform1.set("Fumehood_voc",
				(new StringBuilder()).append(fumehoodvo.getvoc()).append("")
						.toString());
		dynavalidatorform1.set("FumehoodHoursOfOperation",
				(new StringBuilder()).append(fumehoodvo.getHrsOfOperation())
						.append("").toString());
		com.eespc.tracking.bo.DropDown dropdown = FumehoodChemicalsEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("FUMEHOOD_CHEMICAL", dropdown);
		httpservletrequest.setAttribute("SELECTED_CHEMICAL",
				fumehoodvo.getChemicalsUsedList());
		dynavalidatorform1.set("FumehoodIncludeByDec", "Y");
		dynavalidatorform1.set("FumehoodHoursOfOperation1",
				fumehoodvo.getFumehoodHoursOfOperation1());
		dynavalidatorform1.set("FumehoodDEPNumber",
				fumehoodvo.getFumehoodDEPNumber());
		dynavalidatorform1.set("fcomments", fumehoodvo.getFcomments());
		dynavalidatorform1.set("dobsignoff", fumehoodvo.getDobsignoff());
		dynavalidatorform1.set("depPermitStatus",
				fumehoodvo.isDepPermitStatus());
		dynavalidatorform1.set("dobPermitStatus",
				fumehoodvo.isDobPermitStatus());
		dynavalidatorform1.set("annualPermitStatus",
				fumehoodvo.isAnnualPermitStatus());

		String s = fumehoodvo.getLastInspectionDate();
		dynavalidatorform1.set("lastInspectionDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(s));

		String s1 = fumehoodvo.getNextInspectionDate();
		dynavalidatorform1.set("nextInspectionDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));

		dynavalidatorform1.set("Fumehood_dob",
				(new StringBuilder()).append(fumehoodvo.getdob()).append("")
						.toString());
		if (!userAction.equalsIgnoreCase("edit")) {
			if (UtilityObject.isNotEmpty((new StringBuilder())
					.append(AgencyLocationEnum.get(fumehoodvo.getLocation()))
					.append("").toString()))
				dynavalidatorform1.set(
						"FumehoodLocation",
						checkNullAndFill(
								(new StringBuilder())
										.append(AgencyLocationEnum
												.get(fumehoodvo.getLocation()))
										.append("").toString(), ""));
			else
				dynavalidatorform1.set("FumehoodLocation", "");

		} else
			dynavalidatorform1.set("FumehoodLocation", (new StringBuilder())
					.append(fumehoodvo.getLocation()).append("").toString());

		StackVo stackvo = fumehoodvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set("Fumehood_fuelFrom", (new StringBuilder())
					.append(stackvo.getStackId()).append("").toString());
			dynavalidatorform1.set("Fumehood_fuelFromName",
					stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("Fumehood_fuelFrom", "");
			dynavalidatorform1.set("Fumehood_fuelFromName", "");
		}
		dynavalidatorform1.set("FumehoodExemptedbyDec", "N");
		boolean flag = fumehoodvo.isExemptedByDec();
		if (flag)
			dynavalidatorform1.set("FumehoodExemptedbyDec", "Y");
		else
			dynavalidatorform1.set("FumehoodExemptedbyDec", "N");
		flag = fumehoodvo.isUnitIncludedInDecPermit();
		if (flag)
			dynavalidatorform1.set("FumehoodIncludeByDec", "Y");
		else
			dynavalidatorform1.set("FumehoodIncludeByDec", "N");
		int i = fumehoodvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "inline");
		else
			httpservletrequest.setAttribute("FUMEHOOD_LOC", "none");
		try {
			List list = fumehoodvo.getChemicalsUsedList();
			String as[] = new String[list.size()];
			for (int j = 0; j < list.size(); j++) {
				FumehoodChemicalsEnum fumehoodchemicalsenum = FumehoodChemicalsEnum
						.parse(String.valueOf(list.get(j)));
				as[j] = String.valueOf(fumehoodchemicalsenum.getCode());
				log.debug(as[j].toString());
			}

			dynavalidatorform1.set("FumehoodTypesOfChemical", as);
		} catch (Exception exception) {
			log.debug((new StringBuilder())
					.append("FumehoodAction.setFormVariable() ")
					.append(exception.toString()).toString());
		}
		httpservletrequest.setAttribute("LOCATION", AgencyLocationEnum.get(i));
		refreshPermitInfo(httpservletrequest);
		List list1 = (List) httpservletrequest.getAttribute("FUMEHOOD_DEP_LST");
		if (list1 != null && list1.size() > 0) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(0);
			log.debug(permitinfovo.getPermitNumber());
			// dynavalidatorform1.set("FumehoodDEPNumber",
			// permitinfovo.getPermitNumber());
		}
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("FUMEHOOD_DEP", "inline");
		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}
		return dynavalidatorform1;
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = AgencyLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("AGENCY_LOCATION", dropdown);
		dropdown = FumehoodChemicalsEnum.getDropDownObj();
		httpservletrequest.setAttribute("FUMEHOOD_CHEMICAL", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown1);
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		if (fumehoodvo == null) {
			log.debug("Fumehood Object is null");
			return;
		}
		fumehoodvo.setPermitInfoList(null);
		List list = fumehoodvo.getPermitInfoList();
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

		log.debug((new StringBuilder()).append("FUMEHOOD_STATE_LST size=")
				.append(arraylist.size()).append(", FUMEHOOD_DEP_LST size=")
				.append(arraylist1.size()).append(", FUMEHOOD_LOC_LST size=")
				.append(arraylist2.size()).toString());
		httpservletrequest.setAttribute("FUMEHOOD_STATE_LST", arraylist);
		httpservletrequest.setAttribute("FUMEHOOD_DEP_LST", arraylist1);
		httpservletrequest.setAttribute("FUMEHOOD_LOC_LST", arraylist2);
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
			FumehoodVo fumehoodvo = (FumehoodVo) httpsession
					.getAttribute("FUMEHOOD_OBJECT");
			if (fumehoodvo != null)
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
		FumehoodVo fumehoodvo = (FumehoodVo) httpsession
				.getAttribute("FUMEHOOD_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/fumehood/"
					+ fumehoodvo.getId() + "-"
					+ fumehoodvo.getFacilityDesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/fumehood/"
					+ fumehoodvo.getId() + "-"
					+ fumehoodvo.getFacilityDesignatedId().trim();
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
				+ "/fumehood/" + fumehoodvo.getId() + "-"
				+ fumehoodvo.getFacilityDesignatedId().trim());

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

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
			return s;
		else
			return s1;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public String userAction;
	public static HashMap VALID_STATES;

	static {
		log = LogFactory
				.getLog(com.eespc.tracking.actions.FumehoodAction.class);
		VALID_STATES = new HashMap();
		VALID_STATES.put("NY", "NYCDEP");
		VALID_STATES.put("NJ", "NJDEP");
		VALID_STATES.put("PA", "PADEP");
		VALID_STATES.put("NC", "NCDENR");
		VALID_STATES.put("SC", "SCDEH");
		VALID_STATES.put("MD", "MDE");
	}
}