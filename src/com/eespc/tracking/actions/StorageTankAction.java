package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.struts.upload.FormFile;

import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.SpillProtectionVo;
import com.eespc.tracking.bo.StorageTankVo;
import com.eespc.tracking.bo.TankTightnessVo;
import com.eespc.tracking.bo.TrienialCathodicVo;
import com.eespc.tracking.bo.myenum.AgencyLocationEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.DispenserEnum;
import com.eespc.tracking.bo.myenum.ExternalProtectionEnum;
import com.eespc.tracking.bo.myenum.InternalProtectionEnum;
import com.eespc.tracking.bo.myenum.LeakDetectionEnum;
import com.eespc.tracking.bo.myenum.OverfillPreventionEnum;
import com.eespc.tracking.bo.myenum.PipingSecContainmentEnum;
import com.eespc.tracking.bo.myenum.PipingTypeEnum;
import com.eespc.tracking.bo.myenum.ProductStorageEnum;
import com.eespc.tracking.bo.myenum.SecondaryContainmentEnum;
import com.eespc.tracking.bo.myenum.TankCorrosionEnum;
import com.eespc.tracking.bo.myenum.TankExternalEnum;
import com.eespc.tracking.bo.myenum.TankLocationEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.TankSpillEnum;
import com.eespc.tracking.bo.myenum.TankTypeEnum;
import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class StorageTankAction extends Action {

	public StorageTankAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		setDropDown(httpservletrequest);
		setScreenInfo(httpservletrequest);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("methodToCall");
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
		if (s != null && s.equalsIgnoreCase("VIEWEXIST"))
			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("EDIT"))
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addTankTightness"))
			return addTankTightness(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editTankTightness"))
			return editTankTightness(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteTankTightness"))
			return deleteTankTightness(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateTankTightness"))
			return updateTankTightness(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addTrienialCathodicProtection"))
			return addTrienialCathodicProtection(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editTrienialCathodicProtection"))
			return editTrienialCathodicProtection(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteTrienialCathodicProtection"))
			return deleteTrienialCathodicProtection(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateTrienialCathodicProtection"))
			return updateTrienialCathodicProtection(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addSpillCompliance"))
			return addSpillCompliance(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addStatePermit"))
			return addStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addLocationPermit"))
			return addLocationPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editSpillCompliance"))
			return editSpillCompliance(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateSpillCompliance"))
			return updateSpillCompliance(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteSpillCompliance"))
			return deleteSpillCompliance(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editStatePermit"))
			return editStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateStatePermit"))
			return updateStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteStatePermit"))
			return deleteStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editLocationPermit"))
			return editLocationPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteLocationPermit"))
			return deleteLocationPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateLocationPermit")) {
			return updateLocationPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
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
			dynaactionform.set("sTankId", "");
			dynaactionform.set("sPbs", "");
			dynaactionform.set("sPbsexpirationdate", "");
			dynaactionform.set("sPbsexpirationdateNote", "");
			dynaactionform.set("sFuelType", "");
			dynaactionform.set("sCapacity", "");
			dynaactionform.set("sYearInstalled", "");
			dynaactionform.set("s_disconnecteddate", "");
			dynaactionform.set("stestcompany", "");
			dynaactionform.set("sProductStored", "");
			dynaactionform.set("sDayTank", "");
			dynaactionform.set("sTankType", "");
			dynaactionform.set("regionalLocation", "");
			dynaactionform.set("sTankCorrosionProtection", "");
			dynaactionform.set("cathodicInstallationDate", "");
			dynaactionform.set("tankTightness", "");
			dynaactionform.set("sTankLocation", "");
			dynaactionform.set("sTankOpStatus", "");
			dynaactionform.set("sPipeType", "");
			dynaactionform.set("sInternalProt", "");
			dynaactionform.set("sTankExternalProt", "");
			dynaactionform.set("sExternalProt", "");
			dynaactionform.set("sOverFillProt", "");
			dynaactionform.set("sSpillPrevent", "");
			dynaactionform.set("sDispenser", "");
			dynaactionform.set("sLeakDetection", "");
			dynaactionform.set("sPipingSecContainment", "");
			dynaactionform.set("sSecContainment", "");
			dynaactionform.set("sDobApp", "");
			dynaactionform.set("certificateofapproval", "");
			dynaactionform.set("sFireDepApp", "");
			dynaactionform.set("sTankReg", "");
			dynaactionform.set("sLocation", "");
			dynaactionform.set("sComments", "");
			dynaactionform.set("dobsignoff", "");
			dynaactionform.set("dobfiling", "");
			dynaactionform.set("sFuelsuppliedto", "");
			log.debug((new StringBuilder())
					.append("StorageTankAction - In Execute (").append(s)
					.append(")").toString());
			httpservletrequest.getSession().removeAttribute(
					"STROAGE_TANK_OBJECT");
			permitBlockControl(httpservletrequest);
			return actionmapping.findForward("success");
		}
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		try {
			displayControl(actionmapping, actionform, httpservletrequest,
					httpservletresponse, false);
		} catch (Exception e) {
		}

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
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, storagetankvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		try {
			displayControl(actionmapping, actionform, httpservletrequest,
					httpservletresponse, false);
		} catch (Exception e) {
		}

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
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, storagetankvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		try {
			displayControl(actionmapping, actionform, httpservletrequest,
					httpservletresponse, false);
		} catch (Exception e) {
		}

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
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, storagetankvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		try {
			displayControl(actionmapping, actionform, httpservletrequest,
					httpservletresponse, false);
		} catch (Exception e) {
		}

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
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, storagetankvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		try {
			displayControl(actionmapping, actionform, httpservletrequest,
					httpservletresponse, false);
		} catch (Exception e) {
		}

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
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		try {
			displayControl(actionmapping, actionform, httpservletrequest,
					httpservletresponse, false);
		} catch (Exception e) {
		}
		// setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/storagetank/"
				+ storagetankvo.getId() + "-"
				+ storagetankvo.getFacilityDesignatedId().trim())) {
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

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("StorageTankAction - In Add");
		String s = "";
		String s3 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		StorageTankVo storagetankvo = new StorageTankVo();
		storagetankvo.setBuildingId(buildingvo.getId());
		storagetankvo.setDayTank(UtilityObject
				.convertBoolean((String) dynaactionform.get("sDayTank")));
		storagetankvo.setPbsNumber((String) dynaactionform.get("sPbs"));

		String sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("sPbsexpirationdate")), "yyyy-MM-dd");
		storagetankvo.setPbsExpirationDate(sd);

		storagetankvo.setPbsExpirationDateNote((String) dynaactionform
				.get("sPbsexpirationdateNote"));

		String sd1 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("cathodicInstallationDate")), "yyyy-MM-dd");
		storagetankvo.setCathodicInstallationDate(sd1);

		storagetankvo.setTankTightness((String) dynaactionform
				.get("tankTightness"));

		storagetankvo.setFuelType(1);

		storagetankvo.setFuelsuppliedto((String) dynaactionform
				.get("sFuelsuppliedto"));
		storagetankvo.setScomments((String) dynaactionform.get("sComments"));
		storagetankvo.setRegionalLocation((String) dynaactionform
				.get("regionalLocation"));
		storagetankvo.setCapacity((String) dynaactionform.get("sCapacity"));

		String s6 = (String) dynaactionform.get("sTankType");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setTankType(Integer.parseInt(s6));

		s6 = (String) dynaactionform.get("sTankLocation");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setTankLocation(Integer.parseInt(s6));

		s6 = (String) dynaactionform.get("sTankCorrosionProtection");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setCorrosionProtectionType(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sTankOpStatus");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setTankStatus(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sPipeType");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setPipeType(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sInternalProt");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setPipeInternalProtection(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sTankExternalProt");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setTankExternalProtection(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sExternalProt");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setExternalProtection(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sOverFillProt");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setOverfillProtection(Integer.parseInt(s6));
		s6 = (String) dynaactionform.get("sSpillPrevent");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setSpillPreventionType(Integer.parseInt(s6));

		s6 = (String) dynaactionform.get("sDispenser");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setDispenser(Integer.parseInt(s6));

		s6 = (String) dynaactionform.get("sLeakDetection");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setLeakDetection(Integer.parseInt(s6));

		s6 = (String) dynaactionform.get("sPipingSecContainment");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setPipingSecContainment(Integer.parseInt(s6));

		s6 = (String) dynaactionform.get("sSecContainment");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setSecondaryContainment(Integer.parseInt(s6));
		storagetankvo.setDobApproval((String) dynaactionform.get("sDobApp"));
		storagetankvo.setCertificateofapproval((String) dynaactionform
				.get("certificateofapproval"));
		storagetankvo.setFireDeptCertificate((String) dynaactionform
				.get("sFireDepApp"));
		s6 = (String) dynaactionform.get("sLocation");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setLocation(Integer.parseInt(s6));
		storagetankvo.setYearInstalled((String) dynaactionform
				.get("sYearInstalled"));
		storagetankvo.setDisconnecteddate((String) dynaactionform
				.get("s_disconnecteddate"));
		storagetankvo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		storagetankvo.setDobFiling((String) dynaactionform.get("dobfiling"));
		storagetankvo.settestcompany((String) dynaactionform
				.get("stestcompany"));
		s6 = (String) dynaactionform.get("sProductStored");
		if (UtilityObject.isNotEmpty(s6))
			storagetankvo.setProductStored(Integer.parseInt(s6));
		storagetankvo.setRegWithStateAgency(UtilityObject
				.convertBoolean((String) dynaactionform.get("sTankReg")));
		storagetankvo.setFacilityDesignatedId((String) dynaactionform
				.get("sTankId"));
		try {
			int i = StorageTankEntity.add(storagetankvo);
			StorageTankVo storagetankvo1 = StorageTankEntity
					.findByPrimaryKey(i);
			if (storagetankvo1 != null) {
				httpsession.setAttribute("STROAGE_TANK_OBJECT", storagetankvo1);
				setFieldDetails(httpservletrequest, dynaactionform,
						storagetankvo1);
			}
			httpservletrequest.setAttribute("isComponentEditable", "N");
			String s1 = "Added Oxygen Storage Tank.";
			String s4 = "CONFIRMATION";
		} catch (TrackingException trackingexception) {
			String s2 = trackingexception.getMessage();
			String s5 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("StorageTankAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		StorageTankVo storagetankvo = StorageTankEntity.findByPrimaryKey(i);
		if (storagetankvo != null) {
			httpsession.setAttribute("STROAGE_TANK_OBJECT", storagetankvo);
			setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		}

		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In Edit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.removeAttribute("STROAGE_TANK_OBJECT");
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		httpservletrequest.setAttribute("isItForEdit", "Y");
		StorageTankVo storagetankvo = StorageTankEntity.findByPrimaryKey(i);
		if (storagetankvo != null) {
			httpsession.setAttribute("STROAGE_TANK_OBJECT", storagetankvo);
			setFieldDetailsedit(httpservletrequest, dynaactionform,
					storagetankvo);
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		setFieldDetialsForEdit(httpservletrequest, dynaactionform,
				storagetankvo);
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In Edit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.removeAttribute("STROAGE_TANK_OBJECT");
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		httpservletrequest.setAttribute("isItForEdit", "N");
		StorageTankVo storagetankvo = StorageTankEntity.findByPrimaryKey(i);
		if (storagetankvo != null) {
			httpsession.setAttribute("STROAGE_TANK_OBJECT", storagetankvo);
			setFieldDetailsedit(httpservletrequest, dynaactionform,
					storagetankvo);
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		setFieldDetialsForEdit(httpservletrequest, dynaactionform,
				storagetankvo);
		return displayControlforsearch(actionmapping, actionform,
				httpservletrequest, httpservletresponse);
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In update");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String facilityDesignatedid = storagetankvo.getFacilityDesignatedId();

		HttpSession httpsession = httpservletrequest.getSession();
		storagetankvo.setDayTank(UtilityObject
				.convertBoolean((String) dynaactionform.get("sDayTank")));
		storagetankvo.setPbsNumber((String) dynaactionform.get("sPbs"));

		String sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("sPbsexpirationdate")), "yyyy-MM-dd");
		storagetankvo.setPbsExpirationDate(sd);

		storagetankvo.setPbsExpirationDateNote((String) dynaactionform
				.get("sPbsexpirationdateNote"));

		String sd1 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("cathodicInstallationDate")), "yyyy-MM-dd");
		storagetankvo.setCathodicInstallationDate(sd1);

		storagetankvo.setTankTightness((String) dynaactionform
				.get("tankTightness"));

		storagetankvo.setFuelType(1);

		storagetankvo.setFuelsuppliedto((String) dynaactionform
				.get("sFuelsuppliedto"));
		storagetankvo.setScomments((String) dynaactionform.get("sComments"));
		storagetankvo.setRegionalLocation((String) dynaactionform
				.get("regionalLocation"));
		storagetankvo.setCapacity((String) dynaactionform.get("sCapacity"));

		String s = (String) dynaactionform.get("sTankType");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setTankType(Integer.parseInt(s));
		// System.out.println((new
		// StringBuilder()).append("sigma").append((String)dynaactionform.get("sTankLocation")).toString());
		s = (String) dynaactionform.get("sTankLocation");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setTankLocation(Integer.parseInt(s));

		s = (String) dynaactionform.get("sTankCorrosionProtection");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setCorrosionProtectionType(Integer.parseInt(s));
		s = (String) dynaactionform.get("sTankOpStatus");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setTankStatus(Integer.parseInt(s));
		s = (String) dynaactionform.get("sPipeType");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setPipeType(Integer.parseInt(s));
		s = (String) dynaactionform.get("sInternalProt");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setPipeInternalProtection(Integer.parseInt(s));
		s = (String) dynaactionform.get("sTankExternalProt");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setTankExternalProtection(Integer.parseInt(s));
		s = (String) dynaactionform.get("sExternalProt");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setExternalProtection(Integer.parseInt(s));
		s = (String) dynaactionform.get("sOverFillProt");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setOverfillProtection(Integer.parseInt(s));

		s = (String) dynaactionform.get("sSpillPrevent");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setSpillPreventionType(Integer.parseInt(s));

		s = (String) dynaactionform.get("sDispenser");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setDispenser(Integer.parseInt(s));

		s = (String) dynaactionform.get("sLeakDetection");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setLeakDetection(Integer.parseInt(s));

		s = (String) dynaactionform.get("sPipingSecContainment");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setPipingSecContainment(Integer.parseInt(s));

		s = (String) dynaactionform.get("sSecContainment");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setSecondaryContainment(Integer.parseInt(s));

		storagetankvo.setDobApproval((String) dynaactionform.get("sDobApp"));
		storagetankvo.setCertificateofapproval((String) dynaactionform
				.get("certificateofapproval"));
		storagetankvo.setFireDeptCertificate((String) dynaactionform
				.get("sFireDepApp"));

		s = (String) dynaactionform.get("sLocation");
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setLocation(Integer.parseInt(s));
		storagetankvo.setYearInstalled((String) dynaactionform
				.get("sYearInstalled"));
		storagetankvo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		storagetankvo.setDobFiling((String) dynaactionform.get("dobfiling"));
		storagetankvo.setDisconnecteddate((String) dynaactionform
				.get("s_disconnecteddate"));
		s = (String) dynaactionform.get("sProductStored");
		storagetankvo.settestcompany((String) dynaactionform
				.get("stestcompany"));
		if (UtilityObject.isNotEmpty(s))
			storagetankvo.setProductStored(Integer.parseInt(s));
		storagetankvo.setRegWithStateAgency(UtilityObject
				.convertBoolean((String) dynaactionform.get("sTankReg")));
		storagetankvo.setFacilityDesignatedId((String) dynaactionform
				.get("sTankId"));
		StorageTankEntity.update(storagetankvo);

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/storagetank/" + storagetankvo.getId() + "-"
					+ facilityDesignatedid.trim());

			if (f.isDirectory()) {

				File newFileName = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/storagetank/" + storagetankvo.getId() + "-"
						+ storagetankvo.getFacilityDesignatedId().trim());
				f.renameTo(newFileName);

			} else {

			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		StorageTankVo storagetankvo1 = StorageTankEntity
				.findByPrimaryKey(storagetankvo.getId());
		if (storagetankvo1 != null) {
			httpsession.setAttribute("STROAGE_TANK_OBJECT", storagetankvo1);
			setFieldDetails(httpservletrequest, dynaactionform, storagetankvo1);
		}
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse, true);
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("StorageTankAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		StorageTankVo storagetankvo = StorageTankEntity.findByPrimaryKey(i);
		if (storagetankvo != null)
			httpsession.setAttribute("STROAGE_TANK_OBJECT", storagetankvo);
		StorageTankVo storagetankvo1 = getStorageTankVo(httpservletrequest);
		StorageTankEntity.delete(storagetankvo1);

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/storagetank/" + storagetankvo1.getId() + "-"
					+ storagetankvo1.getFacilityDesignatedId().trim());
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

		return actionmapping.findForward("successstorage");
	}

	public ActionForward addSpillCompliance(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In addSpillCompliance");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		SpillProtectionVo spillprotectionvo = new SpillProtectionVo();
		spillprotectionvo.setTankId(storagetankvo.getId());
		spillprotectionvo
				.setCompliant(UtilityObject
						.convertBoolean((String) dynaactionform
								.get("spillCompliance")));
		spillprotectionvo.setDate(UtilityObject
				.convertToDate((String) dynaactionform.get("spillDate")));
		spillprotectionvo.setSpillNumber((String) dynaactionform
				.get("spillNumber"));
		spillprotectionvo.setThereAnySpill(UtilityObject
				.convertBoolean((String) dynaactionform.get("anySpill")));
		int i = StorageTankEntity.addSpillControl(spillprotectionvo);
		dynaactionform.set("spillCompliance", "");
		dynaactionform.set("spillDate", "");
		dynaactionform.set("spillNumber", "");
		dynaactionform.set("anySpill", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		log.debug((new StringBuilder()).append("Created Spill Control, id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward addStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In addStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(DepartmentEnum.STATE_AGENCY.getCode());
		permitinfovo.setObjectId(storagetankvo.getId());
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("statePermitIssueDate")));
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("statePermitExpireDate")));
		permitinfovo.setNote((String) dynaactionform
				.get("statePermitExpireDateNote"));
		permitinfovo.setPermitNumber("dummy123");
		int i = StorageTankEntity.addPermit(permitinfovo);
		dynaactionform.set("statePermitIssueDate", "");
		dynaactionform.set("statePermitExpireDate", "");
		dynaactionform.set("statePermitExpireDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		log.debug((new StringBuilder()).append("State Permit Created, id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward addLocationPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In addLocationPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		byte byte0 = -1;
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(byte0);
		permitinfovo.setObjectId(storagetankvo.getId());
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("locationPermitExpireDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("locationPermitIssueDate")));
		permitinfovo.setNote((String) dynaactionform
				.get("locationPermitExpireDateNote"));
		permitinfovo.setPermitNumber("dummy4location");
		int i = StorageTankEntity.addPermit(permitinfovo);
		dynaactionform.set("locationPermitExpireDate", "");
		dynaactionform.set("locationPermitIssueDate", "");
		dynaactionform.set("locationPermitExpireDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		log.debug((new StringBuilder()).append("Location Permit Created, id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse, false);
		/*
		 * log.debug("StorageTankAction - In displayControl"); String s =
		 * httpservletrequest.getParameter("hdnContext");
		 * if(UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
		 * httpservletrequest.setAttribute("showAddBtn", "Y");
		 * httpservletrequest.setAttribute("isItForEdit", "Y");
		 * httpservletrequest.setAttribute("isComponentEditable", "Y"); } else {
		 * httpservletrequest.setAttribute("showAddBtn", "N"); }
		 * 
		 * 
		 * try { reset(httpservletrequest); } catch(Exception exception) {
		 * System.out.println(exception); } return
		 * actionmapping.findForward("success");
		 */
	}

	public ActionForward displayControlforsearch(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		return displayControlforsearch(actionmapping, actionform,
				httpservletrequest, httpservletresponse, false);
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, boolean flag)
			throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("sDayTank");
		log.debug((new StringBuilder()).append("displayControl sDayTank=")
				.append(s).toString());
		if (s != null && s.equalsIgnoreCase("Y"))
			dynaactionform.set("dayTankFlag", "N");
		else
			dynaactionform.set("dayTankFlag", "Y");
		String s1 = (String) dynaactionform.get("sLocation");
		AgencyLocationEnum agencylocationenum = null;
		if (UtilityObject.isNotEmpty(s1) && !s1.equalsIgnoreCase("-1"))
			try {
				agencylocationenum = AgencyLocationEnum.get(Integer
						.parseInt(s1));
			} catch (NumberFormatException numberformatexception) {
				agencylocationenum = AgencyLocationEnum.parse(s1);
			}
		if (agencylocationenum != null) {
			httpservletrequest.setAttribute("showLocationPermit", "Y");
			String agen = "";
			if (agencylocationenum.getName().equals("Others"))
				agen = "DEP/DOH";
			else
				agen = agencylocationenum.getName();
			httpservletrequest.setAttribute("agencyLabel", agen);
		} else {
			httpservletrequest.setAttribute("showLocationPermit", "N");
			httpservletrequest.setAttribute("agencyLabel", "");
		}
		permitBlockControl(httpservletrequest);
		if (!flag) {
			String s2 = httpservletrequest.getParameter("hdnContext");
			if (UtilityObject.isNotEmpty(s2) && s2.equalsIgnoreCase("Y")) {
				httpservletrequest.setAttribute("isItForEdit", "Y");
				httpservletrequest.setAttribute("isComponentEditable", "Y");
				HttpSession httpsession = httpservletrequest.getSession();
				StorageTankVo storagetankvo = (StorageTankVo) httpsession
						.getAttribute("STROAGE_TANK_OBJECT");
				setFieldDetialsForEdit(httpservletrequest, dynaactionform,
						storagetankvo);
			}
		} else {
			httpservletrequest.removeAttribute("isItForEdit");
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		log.debug("StorageTankAction - In displayControl");
		return actionmapping.findForward("success");
	}

	public ActionForward displayControlforsearch(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, boolean flag)
			throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("sDayTank");
		log.debug((new StringBuilder()).append("displayControl sDayTank=")
				.append(s).toString());
		if (s != null && s.equalsIgnoreCase("Y"))
			dynaactionform.set("dayTankFlag", "N");
		else
			dynaactionform.set("dayTankFlag", "Y");
		String s1 = (String) dynaactionform.get("sLocation");
		AgencyLocationEnum agencylocationenum = null;
		if (UtilityObject.isNotEmpty(s1) && !s1.equalsIgnoreCase("-1"))
			try {
				agencylocationenum = AgencyLocationEnum.get(Integer
						.parseInt(s1));
			} catch (NumberFormatException numberformatexception) {
				agencylocationenum = AgencyLocationEnum.parse(s1);
			}
		if (agencylocationenum != null) {
			if (agencylocationenum != null) {
				httpservletrequest.setAttribute("showLocationPermit", "Y");
				String agen = "";
				if (agencylocationenum.getName().equals("Others"))
					agen = "DEP/DOH";
				else
					agen = agencylocationenum.getName();
				httpservletrequest.setAttribute("agencyLabel", agen);
			}
		} else {
			httpservletrequest.setAttribute("showLocationPermit", "N");
			httpservletrequest.setAttribute("agencyLabel", "");
		}
		if (!flag) {
			String s2 = httpservletrequest.getParameter("hdnContext");
			if (UtilityObject.isNotEmpty(s2) && s2.equalsIgnoreCase("Y")) {
				httpservletrequest.setAttribute("isComponentEditable", "Y");
				HttpSession httpsession = httpservletrequest.getSession();
				StorageTankVo storagetankvo = (StorageTankVo) httpsession
						.getAttribute("STROAGE_TANK_OBJECT");
				setFieldDetialsForEdit(httpservletrequest, dynaactionform,
						storagetankvo);
			}
		} else {
			httpservletrequest.removeAttribute("isItForEdit");
		}
		log.debug("StorageTankAction - In displayControl");
		return actionmapping.findForward("success");
	}

	// *****************************************************************Tank
	// Tightness
	// Starts*****************************************************************////
	public ActionForward addTankTightness(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In addTankTightness");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		TankTightnessVo tanktightnessvo = new TankTightnessVo();
		tanktightnessvo.setTankId(storagetankvo.getId());
		tanktightnessvo.setTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tTestDate")));
		tanktightnessvo.setNextTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tNextTestDate")));
		tanktightnessvo.setNextTestDateNote((String) dynaactionform
				.get("tNextTestDateNote"));
		int i = StorageTankEntity.addTankTightness(tanktightnessvo);
		dynaactionform.set("tTestDate", "");
		dynaactionform.set("tNextTestDate", "");
		dynaactionform.set("tNextTestDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		log.debug((new StringBuilder()).append("Tank Test data created. id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward editTankTightness(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In editTankTightness");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STORAGE_TANK_TIGHTNESS", "Y");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward updateTankTightness(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateTankTightness");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Tank Tightness Id is null while updating the Tank Tightness info");
		TankTightnessVo tanktightnessvo = StorageTankEntity.getTankTightness(i);
		tanktightnessvo.setTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tTestDate")));
		tanktightnessvo.setNextTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tNextTestDate")));
		tanktightnessvo.setNextTestDateNote((String) dynaactionform
				.get("tNextTestDateNote"));
		StorageTankEntity.updateTankTightness(tanktightnessvo);
		dynaactionform.set("tTestDate", "");
		dynaactionform.set("tNextTestDate", "");
		dynaactionform.set("tNextTestDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward deleteTankTightness(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateTankTightness");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Tank Tightness Id is null while updating the Tank Tightness info");
		TankTightnessVo tanktightnessvo = StorageTankEntity.getTankTightness(i);
		StorageTankEntity.deleteTankTightness(tanktightnessvo);
		dynaactionform.set("tTestDate", "");
		dynaactionform.set("tNextTestDate", "");
		dynaactionform.set("tNextTestDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	// *****************************************************************Tank
	// Tightness
	// End*****************************************************************////

	// ************************************************Trienial Cathodic
	// start**************************************************************************/

	public ActionForward addTrienialCathodicProtection(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In addTrienialCathodicProtection");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		TrienialCathodicVo trienialcathodicvo = new TrienialCathodicVo();
		trienialcathodicvo.setTankId(storagetankvo.getId());
		trienialcathodicvo.setLastTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tLastTestDate")));
		trienialcathodicvo
				.setNextTestDueDate(UtilityObject
						.convertToDate((String) dynaactionform
								.get("tNextTestDueDate")));
		trienialcathodicvo.setNote((String) dynaactionform
				.get("tNextTestDueDateNote"));
		trienialcathodicvo.setActualTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tActualTestDate")));
		int i = StorageTankEntity
				.addTrienialCathodicProtection(trienialcathodicvo);
		dynaactionform.set("tLastTestDate", "");
		dynaactionform.set("tNextTestDueDate", "");
		dynaactionform.set("tNextTestDueDateNote", "");
		dynaactionform.set("tActualTestDate", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		log.debug((new StringBuilder())
				.append("Tank Trienial Test data created. id=").append(i)
				.toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward editTrienialCathodicProtection(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In editTrienialCathodicProtection");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STORAGE_TANK_TRIENIALTEST", "Y");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward updateTrienialCathodicProtection(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateTrienialCathodicProtection");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Tank TrienialCathodicProtection Id is null while updating the Tank TrienialCathodicProtection info");
		TrienialCathodicVo trienialcathodicvo = StorageTankEntity
				.getTrienialCathodicProtection(i);
		trienialcathodicvo.setLastTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tLastTestDate")));
		trienialcathodicvo
				.setNextTestDueDate(UtilityObject
						.convertToDate((String) dynaactionform
								.get("tNextTestDueDate")));
		trienialcathodicvo.setNote((String) dynaactionform
				.get("tNextTestDueDateNote"));
		trienialcathodicvo.setActualTestDate(UtilityObject
				.convertToDate((String) dynaactionform.get("tActualTestDate")));
		StorageTankEntity.updateTrienialCathodicProtection(trienialcathodicvo);
		dynaactionform.set("tLastTestDate", "");
		dynaactionform.set("tNextTestDueDate", "");
		dynaactionform.set("tNextTestDueDateNote", "");
		dynaactionform.set("tActualTestDate", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward deleteTrienialCathodicProtection(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateTrienialCathodicProtection");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Tank TrienialCathodicProtection Id is null while updating the Tank TrienialCathodicProtection info");

		TrienialCathodicVo trienialcathodicvo = StorageTankEntity
				.getTrienialCathodicProtection(i);
		StorageTankEntity.deleteTrienialCathodicProtection(trienialcathodicvo);
		dynaactionform.set("tLastTestDate", "");
		dynaactionform.set("tNextTestDueDate", "");
		dynaactionform.set("tNextTestDueDateNote", "");
		dynaactionform.set("tActualTestDate", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	// **************************************Trienial
	// End****************************************************************************************/

	public ActionForward editSpillCompliance(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In editSpillCompliance");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_SPILL_COMPLIANCE", "Y");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward updateSpillCompliance(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateSpillCompliance");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Spill Compliance Id is null while updating the Spill Compliance info");
		SpillProtectionVo spillprotectionvo = StorageTankEntity
				.getSpillProtection(i);
		spillprotectionvo
				.setCompliant(UtilityObject
						.convertBoolean((String) dynaactionform
								.get("spillCompliance")));
		spillprotectionvo.setDate(UtilityObject
				.convertToDate((String) dynaactionform.get("spillDate")));
		spillprotectionvo.setSpillNumber((String) dynaactionform
				.get("spillNumber"));
		spillprotectionvo.setThereAnySpill(UtilityObject
				.convertBoolean((String) dynaactionform.get("anySpill")));
		StorageTankEntity.updateSpillControl(spillprotectionvo);
		dynaactionform.set("spillCompliance", "");
		dynaactionform.set("spillDate", "");
		dynaactionform.set("spillNumber", "");
		dynaactionform.set("anySpill", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward deleteSpillCompliance(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateSpillCompliance");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Spill Compliance Id is null while updating the Spill Compliance info");
		SpillProtectionVo spillprotectionvo = StorageTankEntity
				.getSpillProtection(i);
		StorageTankEntity.deleteSpillControl(spillprotectionvo);
		dynaactionform.set("spillCompliance", "");
		dynaactionform.set("spillDate", "");
		dynaactionform.set("spillNumber", "");
		dynaactionform.set("anySpill", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward editStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In editStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STATE_PERMIT", "Y");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward updateStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit info");
		PermitInfoVo permitinfovo = StorageTankEntity.getPermit(i);
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("statePermitIssueDate")));
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("statePermitExpireDate")));
		permitinfovo.setNote((String) dynaactionform
				.get("statePermitExpireDateNote"));
		StorageTankEntity.updatePermit(permitinfovo);
		dynaactionform.set("statePermitIssueDate", "");
		dynaactionform.set("statePermitExpireDate", "");
		dynaactionform.set("statePermitExpireDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward deleteStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit info");
		PermitInfoVo permitinfovo = StorageTankEntity.getPermit(i);
		StorageTankEntity.deletePermit(permitinfovo);
		dynaactionform.set("statePermitIssueDate", "");
		dynaactionform.set("statePermitExpireDate", "");
		dynaactionform.set("statePermitExpireDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward editLocationPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In editLocationPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_LOCATION_PERMIT", "Y");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward updateLocationPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateLocationPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Location Permit Id is null while updating the Location Permit info");
		PermitInfoVo permitinfovo = StorageTankEntity.getPermit(i);
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("locationPermitExpireDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform
						.get("locationPermitIssueDate")));
		permitinfovo.setNote((String) dynaactionform
				.get("locationPermitExpireDateNote"));
		StorageTankEntity.updatePermit(permitinfovo);
		dynaactionform.set("locationPermitExpireDate", "");
		dynaactionform.set("locationPermitIssueDate", "");
		dynaactionform.set("locationPermitExpireDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	public ActionForward deleteLocationPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StorageTankAction - In updateLocationPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		StorageTankVo storagetankvo = getStorageTankVo(httpservletrequest);
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Location Permit Id is null while updating the Location Permit info");
		PermitInfoVo permitinfovo = StorageTankEntity.getPermit(i);
		StorageTankEntity.deletePermit(permitinfovo);
		dynaactionform.set("locationPermitExpireDate", "");
		dynaactionform.set("locationPermitIssueDate", "");
		dynaactionform.set("locationPermitExpireDateNote", "");
		setFieldDetails(httpservletrequest, dynaactionform, storagetankvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return displayControl(actionmapping, actionform, httpservletrequest,
				httpservletresponse);
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest) {
		httpservletrequest.setAttribute("showLocationPermit", "Y");
		httpservletrequest.setAttribute("agencyLabel", "");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = ProductStorageEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("PRODUCT_STORED", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_TYPE", dropdown1);
		com.eespc.tracking.bo.DropDown dropdown2 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown2);
		com.eespc.tracking.bo.DropDown dropdown3 = PipingTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("PIPE_TYPE", dropdown3);
		com.eespc.tracking.bo.DropDown dropdown4 = InternalProtectionEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("PIPE_INTERNAL_PROTECT", dropdown4);
		com.eespc.tracking.bo.DropDown dropdown5 = ExternalProtectionEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("PIPE_EXTERNAL_PROTECT", dropdown5);
		com.eespc.tracking.bo.DropDown dropdown6 = OverfillPreventionEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("OVERFILL_PROTECT", dropdown6);
		com.eespc.tracking.bo.DropDown dropdown7 = SecondaryContainmentEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("SECONDARY_CONTAIN", dropdown7);
		com.eespc.tracking.bo.DropDown dropdown8 = AgencyLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("AGENCY_LOCATION", dropdown8);
		com.eespc.tracking.bo.DropDown dropdown9 = TankLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_LOCATION", dropdown9);
		com.eespc.tracking.bo.DropDown dropdown10 = TankCorrosionEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_CORROSIONPROTECTION", dropdown10);
		com.eespc.tracking.bo.DropDown dropdown11 = TankSpillEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("SPILL_PREVENT_METHOD", dropdown11);
		com.eespc.tracking.bo.DropDown dropdown12 = TankExternalEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_EXTERNAL_PROTECT", dropdown12);
		com.eespc.tracking.bo.DropDown dropdown13 = DispenserEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_DISPENSER", dropdown13);
		com.eespc.tracking.bo.DropDown dropdown14 = LeakDetectionEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_LEAK_DETECTION", dropdown14);
		com.eespc.tracking.bo.DropDown dropdown15 = PipingSecContainmentEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("PIPE_SECONDARY_CONTAINMENT",
				dropdown15);
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, StorageTankVo storagetankvo) {
		dynaactionform.set("sTankId", storagetankvo.getFacilityDesignatedId());
		dynaactionform.set("sPbs", storagetankvo.getPbsNumber());
		dynaactionform.set(
				"sPbsexpirationdate",
				(new StringBuilder())
						.append(UtilityObject
								.convertYyyyMmDd2MmDdYyyy(storagetankvo
										.getPbsExpirationDate())).append("")
						.toString());
		dynaactionform.set("sPbsexpirationdateNote",
				storagetankvo.getPbsExpirationDateNote());
		dynaactionform.set(
				"cathodicInstallationDate",
				(new StringBuilder())
						.append(UtilityObject
								.convertYyyyMmDd2MmDdYyyy(storagetankvo
										.getCathodicInstallationDate()))
						.append("").toString());
		// dynaactionform.set("cathodicInstallationDate", (new
		// StringBuilder()).append(checkNullAndFill(storagetankvo.getCathodicInstallationDate(),
		// "")).append("").toString());
		dynaactionform.set("tankTightness", storagetankvo.getTankTightness());
		dynaactionform.set("regionalLocation",
				storagetankvo.getRegionalLocation());
		dynaactionform.set("sFuelType",
				(new StringBuilder()).append(storagetankvo.getFuelType())
						.append("").toString());
		dynaactionform.set("sCapacity", storagetankvo.getCapacity());
		dynaactionform.set("sYearInstalled", storagetankvo.getYearInstalled());
		dynaactionform.set(
				"s_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								storagetankvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynaactionform.set("stestcompany", storagetankvo.gettestcompany());

		ProductStorageEnum productstorageenum = ProductStorageEnum
				.get(storagetankvo.getProductStored());
		if (productstorageenum != null)
			dynaactionform.set("sProductStored", productstorageenum.getName());
		else
			dynaactionform.set("sProductStored", "");
		if (storagetankvo.isDayTank())
			dynaactionform.set("sDayTank", "Y");
		else
			dynaactionform.set("sDayTank", "N");
		TankTypeEnum tanktypeenum = TankTypeEnum.get(storagetankvo
				.getTankType());
		if (tanktypeenum != null)
			dynaactionform.set("sTankType", tanktypeenum.getName());
		else
			dynaactionform.set("sTankType", "");
		TankCorrosionEnum tankcorrosionenum = TankCorrosionEnum
				.get(storagetankvo.getCorrosionProtectionType());
		if (tankcorrosionenum != null)
			dynaactionform.set("sTankCorrosionProtection",
					tankcorrosionenum.getName());
		else
			dynaactionform.set("sTankCorrosionProtection", "");

		TankLocationEnum tanklocationenum = TankLocationEnum.get(storagetankvo
				.getTankLocation());
		if (tanklocationenum != null)
			dynaactionform.set("sTankLocation", tanklocationenum.getName());
		else
			dynaactionform.set("sTankLocation", "");
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(storagetankvo.getTankStatus());
		if (tankoperatingstatusenum != null)
			dynaactionform.set("sTankOpStatus",
					tankoperatingstatusenum.getName());
		else
			dynaactionform.set("sTankOpStatus", "");
		PipingTypeEnum pipingtypeenum = PipingTypeEnum.get(storagetankvo
				.getPipeType());
		if (pipingtypeenum != null)
			dynaactionform.set("sPipeType", pipingtypeenum.getName());
		else
			dynaactionform.set("sPipeType", "");

		InternalProtectionEnum internalprotectionenum = InternalProtectionEnum
				.get(storagetankvo.getPipeInternalProtection());
		if (internalprotectionenum != null)
			dynaactionform.set("sInternalProt",
					internalprotectionenum.getName());
		else
			dynaactionform.set("sInternalProt", "");

		TankExternalEnum tankexternalenum = TankExternalEnum.get(storagetankvo
				.getTankExternalProtection());
		if (tankexternalenum != null)
			dynaactionform.set("sTankExternalProt", tankexternalenum.getName());
		else
			dynaactionform.set("sTankExternalProt", "");

		ExternalProtectionEnum externalprotectionenum = ExternalProtectionEnum
				.get(storagetankvo.getExternalProtection());
		if (externalprotectionenum != null)
			dynaactionform.set("sExternalProt",
					externalprotectionenum.getName());
		else
			dynaactionform.set("sExternalProt", "");

		OverfillPreventionEnum overfillpreventionenum = OverfillPreventionEnum
				.get(storagetankvo.getOverfillProtection());
		if (overfillpreventionenum != null)
			dynaactionform.set("sOverFillProt",
					overfillpreventionenum.getName());
		else
			dynaactionform.set("sOverFillProt", "");

		TankSpillEnum tankspillenum = TankSpillEnum.get(storagetankvo
				.getSpillPreventionType());
		if (tankspillenum != null)
			dynaactionform.set("sSpillPrevent", tankspillenum.getName());
		else
			dynaactionform.set("sSpillPrevent", "");

		DispenserEnum dispenserenum = DispenserEnum.get(storagetankvo
				.getDispenser());
		if (dispenserenum != null)
			dynaactionform.set("sDispenser", dispenserenum.getName());
		else
			dynaactionform.set("sDispenser", "");

		LeakDetectionEnum leakdetectionenum = LeakDetectionEnum
				.get(storagetankvo.getLeakDetection());
		if (leakdetectionenum != null)
			dynaactionform.set("sLeakDetection", leakdetectionenum.getName());
		else
			dynaactionform.set("sLeakDetection", "");

		PipingSecContainmentEnum pipingseccontainmentenum = PipingSecContainmentEnum
				.get(storagetankvo.getPipingSecContainment());
		if (pipingseccontainmentenum != null)
			dynaactionform.set("sPipingSecContainment",
					pipingseccontainmentenum.getName());
		else
			dynaactionform.set("sPipingSecContainment", "");

		SecondaryContainmentEnum secondarycontainmentenum = SecondaryContainmentEnum
				.get(storagetankvo.getSecondaryContainment());
		if (secondarycontainmentenum != null)
			dynaactionform.set("sSecContainment",
					secondarycontainmentenum.getName());
		else
			dynaactionform.set("sSecContainment", "");

		dynaactionform.set("sDobApp", storagetankvo.getDobApproval());
		dynaactionform.set("certificateofapproval",
				storagetankvo.getCertificateofapproval());
		dynaactionform.set("sFireDepApp",
				storagetankvo.getFireDeptCertificate());
		dynaactionform
				.set("sFuelsuppliedto", storagetankvo.getFuelsuppliedto());
		dynaactionform.set("dobsignoff", storagetankvo.getDobsignoff());
		dynaactionform.set("dobfiling", storagetankvo.getDobFiling());
		dynaactionform.set("sComments", storagetankvo.getScomments());
		if (storagetankvo.isRegWithStateAgency())
			dynaactionform.set("sTankReg", "Y");
		else
			dynaactionform.set("sTankReg", "N");
		AgencyLocationEnum agencylocationenum = AgencyLocationEnum
				.get(storagetankvo.getLocation());
		if (agencylocationenum != null)
			dynaactionform.set("sLocation", agencylocationenum.getName());
		else
			dynaactionform.set("sLocation", "");
		setPermitDetails(httpservletrequest, storagetankvo);

		try {
			reset(httpservletrequest);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	private void setFieldDetailsedit(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, StorageTankVo storagetankvo) {
		dynaactionform.set("sTankId", storagetankvo.getFacilityDesignatedId());
		dynaactionform.set("sPbs", storagetankvo.getPbsNumber());
		dynaactionform.set(
				"sPbsexpirationdate",
				(new StringBuilder())
						.append(UtilityObject
								.convertYyyyMmDd2MmDdYyyy(storagetankvo
										.getPbsExpirationDate())).append("")
						.toString());
		dynaactionform.set("sPbsexpirationdateNote",
				storagetankvo.getPbsExpirationDateNote());
		dynaactionform.set(
				"cathodicInstallationDate",
				(new StringBuilder())
						.append(UtilityObject
								.convertYyyyMmDd2MmDdYyyy(storagetankvo
										.getCathodicInstallationDate()))
						.append("").toString());
		// dynaactionform.set("cathodicInstallationDate", (new
		// StringBuilder()).append(checkNullAndFill(storagetankvo.getCathodicInstallationDate(),
		// "")).append("").toString());
		dynaactionform.set("tankTightness", storagetankvo.getTankTightness());
		dynaactionform.set("regionalLocation",
				storagetankvo.getRegionalLocation());
		dynaactionform.set("sFuelType",
				(new StringBuilder()).append(storagetankvo.getFuelType())
						.append("").toString());
		dynaactionform.set("sCapacity", storagetankvo.getCapacity());
		dynaactionform.set("sYearInstalled", storagetankvo.getYearInstalled());
		dynaactionform.set(
				"s_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								storagetankvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynaactionform.set("stestcompany", storagetankvo.gettestcompany());
		ProductStorageEnum productstorageenum = ProductStorageEnum
				.get(storagetankvo.getProductStored());
		if (productstorageenum != null)
			dynaactionform.set("sProductStored", productstorageenum.getName());
		if (storagetankvo.isDayTank())
			dynaactionform.set("sDayTank", "Y");
		else
			dynaactionform.set("sDayTank", "N");

		TankCorrosionEnum tankcorrosionenum = TankCorrosionEnum
				.get(storagetankvo.getCorrosionProtectionType());
		if (tankcorrosionenum != null)
			dynaactionform.set("sTankCorrosionProtection",
					tankcorrosionenum.getName());
		TankTypeEnum tanktypeenum = TankTypeEnum.get(storagetankvo
				.getTankType());
		if (tanktypeenum != null)
			dynaactionform.set("sTankType", tanktypeenum.getName());
		TankLocationEnum tanklocationenum = TankLocationEnum.get(storagetankvo
				.getTankLocation());
		if (tanklocationenum != null)
			dynaactionform.set("sTankLocation", tanklocationenum.getName());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(storagetankvo.getTankStatus());
		if (tankoperatingstatusenum != null)
			dynaactionform.set("sTankOpStatus",
					tankoperatingstatusenum.getName());
		PipingTypeEnum pipingtypeenum = PipingTypeEnum.get(storagetankvo
				.getPipeType());
		if (pipingtypeenum != null)
			dynaactionform.set("sPipeType", pipingtypeenum.getName());
		InternalProtectionEnum internalprotectionenum = InternalProtectionEnum
				.get(storagetankvo.getPipeInternalProtection());
		if (internalprotectionenum != null)
			dynaactionform.set("sInternalProt",
					internalprotectionenum.getName());

		TankExternalEnum tankexternalenum = TankExternalEnum.get(storagetankvo
				.getTankExternalProtection());
		if (tankexternalenum != null)
			dynaactionform.set("sTankExternalProt", tankexternalenum.getName());

		ExternalProtectionEnum externalprotectionenum = ExternalProtectionEnum
				.get(storagetankvo.getExternalProtection());
		if (externalprotectionenum != null)
			dynaactionform.set("sExternalProt",
					externalprotectionenum.getName());
		OverfillPreventionEnum overfillpreventionenum = OverfillPreventionEnum
				.get(storagetankvo.getOverfillProtection());
		if (overfillpreventionenum != null)
			dynaactionform.set("sOverFillProt",
					overfillpreventionenum.getName());

		TankSpillEnum tankspillenum = TankSpillEnum.get(storagetankvo
				.getSpillPreventionType());
		if (tankspillenum != null)
			dynaactionform.set("sSpillPrevent", tankspillenum.getName());

		DispenserEnum dispenserenum = DispenserEnum.get(storagetankvo
				.getDispenser());
		if (dispenserenum != null)
			dynaactionform.set("sDispenser", dispenserenum.getName());

		LeakDetectionEnum leakdetectionenum = LeakDetectionEnum
				.get(storagetankvo.getLeakDetection());
		if (leakdetectionenum != null)
			dynaactionform.set("sLeakDetection", leakdetectionenum.getName());

		PipingSecContainmentEnum pipingseccontainmentenum = PipingSecContainmentEnum
				.get(storagetankvo.getPipingSecContainment());
		if (pipingseccontainmentenum != null)
			dynaactionform.set("sPipingSecContainment",
					pipingseccontainmentenum.getName());

		SecondaryContainmentEnum secondarycontainmentenum = SecondaryContainmentEnum
				.get(storagetankvo.getSecondaryContainment());
		if (secondarycontainmentenum != null)
			dynaactionform.set("sSecContainment",
					secondarycontainmentenum.getName());
		dynaactionform.set("sDobApp", storagetankvo.getDobApproval());
		dynaactionform.set("certificateofapproval",
				storagetankvo.getCertificateofapproval());
		dynaactionform.set("sFireDepApp",
				storagetankvo.getFireDeptCertificate());
		dynaactionform
				.set("sFuelsuppliedto", storagetankvo.getFuelsuppliedto());
		dynaactionform.set("dobsignoff", storagetankvo.getDobsignoff());
		dynaactionform.set("dobfiling", storagetankvo.getDobFiling());
		dynaactionform.set("sComments", storagetankvo.getScomments());
		if (storagetankvo.isRegWithStateAgency())
			dynaactionform.set("sTankReg", "Y");
		else
			dynaactionform.set("sTankReg", "N");
		AgencyLocationEnum agencylocationenum = AgencyLocationEnum
				.get(storagetankvo.getLocation());
		if (agencylocationenum != null)
			dynaactionform.set("sLocation", agencylocationenum.getName());
		try {
			reset(httpservletrequest);
		} catch (Exception e) {
			System.out.println(e);
		}

		setPermitDetails(httpservletrequest, storagetankvo);
	}

	private void setPermitDetails(HttpServletRequest httpservletrequest,
			StorageTankVo storagetankvo) {
		storagetankvo.setPermitInfo(null);
		storagetankvo.setSpillControlList(null);
		storagetankvo.setTightnessTestList(null);
		storagetankvo.setTrienialCathodicTestList(null);
		List list = storagetankvo.getTightnessTestList();
		if (list != null && list.size() > 0) {
			httpservletrequest.setAttribute("STORAGE_TANK_TIGHTNESS", list);
		} else {
			httpservletrequest.setAttribute("STORAGE_TANK_TIGHTNESS",
					new ArrayList());
			log.debug("No Tank Tightness Info");
		}
		List list3 = storagetankvo.getTrienialCathodicTestList();
		if (list3 != null && list3.size() > 0) {
			httpservletrequest.setAttribute("STORAGE_TANK_TRIENIALTEST", list3);
		} else {
			httpservletrequest.setAttribute("STORAGE_TANK_TRIENIALTEST",
					new ArrayList());
			log.debug("No Trienial Test Info");
		}
		List list1 = storagetankvo.getSpillControlList();
		if (list1 != null && list1.size() > 0) {
			httpservletrequest.setAttribute("STORAGE_TANK_SPILLCONTROL", list1);
		} else {
			httpservletrequest.setAttribute("STORAGE_TANK_SPILLCONTROL",
					new ArrayList());
			log.debug("No Spill Control Info");
		}
		List list2 = storagetankvo.getPermitInfo();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int i = list2.size();
		if (list2 != null && i > 0) {
			for (int j = 0; j < i; j++) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list2.get(j);
				int k = permitinfovo.getDepId();
				if (k == DepartmentEnum.STATE_AGENCY.getCode())
					arraylist.add(permitinfovo);
				else
					arraylist1.add(permitinfovo);
			}

		}
		httpservletrequest.setAttribute("STORAGE_TANK_STATE_PERMIT", arraylist);
		httpservletrequest.setAttribute("STORAGE_TANK_LOCATION_PERMIT",
				arraylist1);

		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
	}

	private void permitBlockControl(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		StorageTankVo storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		if (storagetankvo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
	}

	private StorageTankVo getStorageTankVo(HttpServletRequest httpservletrequest)
			throws Exception {
		return (StorageTankVo) httpservletrequest.getSession().getAttribute(
				"STROAGE_TANK_OBJECT");
	}

	private void setFieldDetialsForEdit(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, StorageTankVo storagetankvo) {
		dynaactionform.set("sProductStored",
				(new StringBuilder()).append(storagetankvo.getProductStored())
						.append("").toString());
		dynaactionform.set("sTankCorrosionProtection", (new StringBuilder())
				.append(storagetankvo.getCorrosionProtectionType()).append("")
				.toString());
		dynaactionform.set("sTankType",
				(new StringBuilder()).append(storagetankvo.getTankType())
						.append("").toString());
		dynaactionform.set("sTankLocation",
				(new StringBuilder()).append(storagetankvo.getTankLocation())
						.append("").toString());
		dynaactionform.set("sTankOpStatus",
				(new StringBuilder()).append(storagetankvo.getTankStatus())
						.append("").toString());
		dynaactionform.set("sPipeType",
				(new StringBuilder()).append(storagetankvo.getPipeType())
						.append("").toString());
		dynaactionform.set(
				"sInternalProt",
				(new StringBuilder())
						.append(storagetankvo.getPipeInternalProtection())
						.append("").toString());
		dynaactionform.set(
				"sTankExternalProt",
				(new StringBuilder())
						.append(storagetankvo.getTankExternalProtection())
						.append("").toString());
		dynaactionform.set(
				"sExternalProt",
				(new StringBuilder())
						.append(storagetankvo.getExternalProtection())
						.append("").toString());
		dynaactionform.set(
				"sOverFillProt",
				(new StringBuilder())
						.append(storagetankvo.getOverfillProtection())
						.append("").toString());
		dynaactionform.set(
				"sSpillPrevent",
				(new StringBuilder())
						.append(storagetankvo.getSpillPreventionType())
						.append("").toString());
		dynaactionform.set("sDispenser",
				(new StringBuilder()).append(storagetankvo.getDispenser())
						.append("").toString());
		dynaactionform.set("sLeakDetection",
				(new StringBuilder()).append(storagetankvo.getLeakDetection())
						.append("").toString());
		dynaactionform.set("sPipingSecContainment", (new StringBuilder())
				.append(storagetankvo.getPipingSecContainment()).append("")
				.toString());
		dynaactionform.set(
				"sSecContainment",
				(new StringBuilder())
						.append(storagetankvo.getSecondaryContainment())
						.append("").toString());
		dynaactionform.set("sLocation",
				(new StringBuilder()).append(storagetankvo.getLocation())
						.append("").toString());

		try {
			reset(httpservletrequest);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		StorageTankVo storagetankvo = (StorageTankVo) httpsession
				.getAttribute("STROAGE_TANK_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/storagetank/" + storagetankvo.getId() + "-"
					+ storagetankvo.getFacilityDesignatedId().trim());
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
					+ "/storagetank/" + storagetankvo.getId() + "-"
					+ storagetankvo.getFacilityDesignatedId().trim();
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
				+ "/storagetank/" + storagetankvo.getId() + "-"
				+ storagetankvo.getFacilityDesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.StorageTankAction.class);

}