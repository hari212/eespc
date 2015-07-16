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
import com.eespc.tracking.bo.EtoTestInfoVo;
import com.eespc.tracking.bo.EtoVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.EtoCylinderGasMixtureEnum;
import com.eespc.tracking.bo.myenum.EtoGasMixtureTypeEnum;
import com.eespc.tracking.bo.myenum.ModifiedIn;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.EtoEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class EtoAction extends Action {

	public EtoAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		setDropDown(httpservletrequest);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		httpservletrequest.getSession().removeAttribute("BLK_O2_PERMIT_LIST");
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
		if (s != null && s.equalsIgnoreCase("addDepPermit"))
			return depPermitInfo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editDepPermit"))
			return editDepPermit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateDepPermit"))
			return updateDepPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteDepPermit"))
			return deleteDepPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addTestInfo"))
			return addTestInfo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editTestInfo"))
			return editTestInfo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateTestInfo"))
			return updateTestInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteTestInfo"))
			return deleteTestInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("addStatePermit"))
			return statePermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editStatePermit"))
			return editStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteStatePermit"))
			return deleteStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

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

		if (s != null && s.equalsIgnoreCase("updateStatePermit")) {
			return updateStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		} else {
			log.debug((new StringBuilder()).append("EtoAction userAction is ")
					.append(s).toString());
			httpservletrequest.getSession().removeAttribute("ETO_OBJECT");
			setScreenInfo(httpservletrequest);
			log.debug("EtoAction - In Execute");
			return actionmapping.findForward("success");
		}
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = new EtoVo();
		etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		// setFormVariable(dynaactionform, etovo, httpservletrequest);

		// refreshShowInfo(httpservletrequest, etovo);
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
		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = new EtoVo();
		etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, etovo);
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

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = new EtoVo();
		etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, etovo);
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
		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = new EtoVo();
		etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, etovo);
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
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = new EtoVo();
		etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, etovo);
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

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = new EtoVo();
		etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/eto/" + etovo.getEtoId()
				+ "-" + etovo.getFacilityDesignatedId().trim())) {
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
		log.debug("EtoAction - In displayControl");
		System.out.println("" + "EtoAction - In displayControl");
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
		log.debug("EtoAction - In Add");
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		EtoVo etovo = new EtoVo();
		etovo.setBuildingId(buildingvo.getId());
		etovo.setFacilityDesignatedId((String) dynaactionform
				.get("facilityDesignatedId"));
		etovo.setComments((String) dynaactionform.get("comments"));
		etovo.setDep((String) dynaactionform.get("dep"));
		etovo.setDob((String) dynaactionform.get("dob"));
		etovo.setActiontaken((String) dynaactionform.get("actiontaken"));
		etovo.setContainerWeight(UtilityObject
				.getIntFromString((String) dynaactionform
						.get("containerWeight")));
		etovo.setGasMixture(UtilityObject
				.getIntFromString((String) dynaactionform.get("gasMixture")));
		String s2 = (String) dynaactionform.get("installationDate");
		if (UtilityObject.isNotEmpty(s2)) {
			java.util.Date date = UtilityObject.convertToDate(s2);
			etovo.setInstallationDate(UtilityObject.convertToString(date));
		}
		etovo.setManufacturer((String) dynaactionform.get("manufacturer"));
		etovo.setMixtureType(UtilityObject
				.getIntFromString((String) dynaactionform.get("mixtureType")));
		etovo.setModel((String) dynaactionform.get("model"));
		etovo.setRecordsAvailable(UtilityObject
				.convertBoolean((String) dynaactionform.get("recordsAvailable")));
		etovo.setSerial((String) dynaactionform.get("serial"));
		etovo.setVolume((String) dynaactionform.get("volume"));
		etovo.setStackId(UtilityObject.getIntFromString((String) dynaactionform
				.get("stackId")));
		String s3 = (String) dynaactionform.get("ModifiedInPast");
		if (UtilityObject.isNotEmpty(s3))
			etovo.setModifiedInPast(Integer.parseInt(s3));
		String s4 = (String) dynaactionform.get("DisconnectedYear");
		etovo.setDisconnectedYear(s4);
		etovo.setIsabator((String) dynaactionform.get("isabator"));
		etovo.setHrs((String) dynaactionform.get("hrs"));
		etovo.setDays((String) dynaactionform.get("days"));
		etovo.setA_decPermitObtained((String) dynaactionform
				.get("A_decPermitObtained"));
		etovo.setA_stackTestProtSubmited((String) dynaactionform
				.get("A_stackTestProtSubmited"));
		etovo.setA_testConductedBy((String) dynaactionform
				.get("A_testConductedBy"));
		etovo.setA_testReportSubmited((String) dynaactionform
				.get("A_testReportSubmited"));
		etovo.setA_permitdate((String) dynaactionform.get("A_permitdate"));
		etovo.setA_compyWithETO((String) dynaactionform.get("A_compyWithETO"));
		etovo.setAmanufacturer((String) dynaactionform.get("amanufacturer"));
		etovo.setAmodel((String) dynaactionform.get("amodel"));
		etovo.setA_testPassed((String) dynaactionform.get("A_testPassed"));
		etovo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		etovo.setEtoDob((String) dynaactionform.get("etoDOB"));
		etovo.setStackTestRequire((String) dynaactionform
				.get("stackTestRequire"));
		try {
			int i = EtoEntity.add(etovo);
			EtoVo etovo1 = EtoEntity.findByPrimaryKey(i);
			if (etovo1 != null) {
				httpsession.setAttribute("ETO_OBJECT", etovo1);
				setFieldDetails(httpservletrequest, dynaactionform, etovo1);
			}
			httpservletrequest.setAttribute("isComponentEditable", "N");
			s = "Added ETO.";
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
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("EtoAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		EtoVo etovo = EtoEntity.findByPrimaryKey(i);
		System.out.println((new StringBuilder()).append("Eto")
				.append(etovo.getDisconnectedYear()).toString());
		if (etovo != null) {
			httpsession.setAttribute("ETO_OBJECT", etovo);
			setFieldDetails(httpservletrequest, dynaactionform, etovo);
		}
		setScreenInfo(httpservletrequest);
		// return displayControl(actionmapping, actionform, httpservletrequest,
		// httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In Edit");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		EtoVo etovo = EtoEntity.findByPrimaryKey(i);
		if (etovo != null) {
			httpsession.setAttribute("ETO_OBJECT", etovo);
			setFieldDetailsforedit(httpservletrequest, dynaactionform, etovo,
					true);
		}
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In Edit");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		EtoVo etovo = EtoEntity.findByPrimaryKey(i);
		if (etovo != null) {
			httpsession.setAttribute("ETO_OBJECT", etovo);
			setFieldDetailsforedit(httpservletrequest, dynaactionform, etovo,
					true);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In Edit");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		EtoVo etovo = EtoEntity.findByPrimaryKey(i);
		if (etovo != null)
			httpsession.setAttribute("ETO_OBJECT", etovo);
		EtoVo etovo1 = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		EtoEntity.delete(etovo1);

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/eto/"
					+ etovo1.getEtoId() + "-"
					+ etovo1.getFacilityDesignatedId().trim());
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

		return actionmapping.findForward("successeto");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In Update");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String facilityDesignatedid = etovo.getFacilityDesignatedId();
		etovo.setFacilityDesignatedId((String) dynaactionform
				.get("facilityDesignatedId"));
		etovo.setComments((String) dynaactionform.get("comments"));
		etovo.setDep((String) dynaactionform.get("dep"));
		etovo.setDob((String) dynaactionform.get("dob"));
		etovo.setActiontaken((String) dynaactionform.get("actiontaken"));
		etovo.setContainerWeight(UtilityObject
				.getIntFromString((String) dynaactionform
						.get("containerWeight")));
		etovo.setGasMixture(UtilityObject
				.getIntFromString((String) dynaactionform.get("gasMixture")));
		String s = (String) dynaactionform.get("installationDate");
		if (UtilityObject.isNotEmpty(s)) {
			java.util.Date date = UtilityObject.convertToDate(s);
			etovo.setInstallationDate(UtilityObject.convertToString(date));
		}
		etovo.setManufacturer((String) dynaactionform.get("manufacturer"));
		etovo.setMixtureType(UtilityObject
				.getIntFromString((String) dynaactionform.get("mixtureType")));
		etovo.setModel((String) dynaactionform.get("model"));
		etovo.setRecordsAvailable(UtilityObject
				.convertBoolean((String) dynaactionform.get("recordsAvailable")));
		etovo.setSerial((String) dynaactionform.get("serial"));
		etovo.setVolume((String) dynaactionform.get("volume"));
		etovo.setStackId(UtilityObject.getIntFromString((String) dynaactionform
				.get("stackId")));
		String s1 = (String) dynaactionform.get("DisconnectedYear");
		etovo.setDisconnectedYear(s1);
		String s2 = (String) dynaactionform.get("ModifiedInPast");
		if (UtilityObject.isNotEmpty(s2))
			etovo.setModifiedInPast(Integer.parseInt(s2));
		etovo.setIsabator((String) dynaactionform.get("isabator"));
		etovo.setHrs((String) dynaactionform.get("hrs"));
		etovo.setDays((String) dynaactionform.get("days"));
		etovo.setA_decPermitObtained((String) dynaactionform
				.get("A_decPermitObtained"));
		etovo.setA_stackTestProtSubmited((String) dynaactionform
				.get("A_stackTestProtSubmited"));
		etovo.setA_testConductedBy((String) dynaactionform
				.get("A_testConductedBy"));
		etovo.setA_testReportSubmited((String) dynaactionform
				.get("A_testReportSubmited"));
		etovo.setA_permitdate((String) dynaactionform.get("A_permitdate"));
		etovo.setA_compyWithETO((String) dynaactionform.get("A_compyWithETO"));
		etovo.setAmanufacturer((String) dynaactionform.get("amanufacturer"));
		etovo.setAmodel((String) dynaactionform.get("amodel"));
		etovo.setA_testPassed((String) dynaactionform.get("A_testPassed"));
		etovo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		etovo.setEtoDob((String) dynaactionform.get("etoDOB"));
		etovo.setStackTestRequire((String) dynaactionform
				.get("stackTestRequire"));
		EtoEntity.update(etovo);
		etovo = EtoEntity.findByPrimaryKey(etovo.getEtoId());

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		try {

			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/eto/"
					+ etovo.getEtoId() + "-" + facilityDesignatedid.trim());

			if (f.isDirectory()) {

				File newFileName = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId() + "/eto/"
						+ etovo.getEtoId() + "-"
						+ etovo.getFacilityDesignatedId().trim());
				f.renameTo(newFileName);

			} else {

			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}

		if (etovo != null) {
			httpsession.setAttribute("ETO_OBJECT", etovo);
			setFieldDetails(httpservletrequest, dynaactionform, etovo);
		}
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward addTestInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In addTestInfo");
		Object obj = null;
		String s = null;
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		EtoTestInfoVo etotestinfovo = new EtoTestInfoVo();
		etotestinfovo.setEtoId(etovo.getEtoId());
		etotestinfovo.setYear((String) dynaactionform.get("testYear"));
		s = (String) dynaactionform.get("testNextDate");
		if (UtilityObject.isNotEmpty(s)) {
			java.util.Date date = UtilityObject.convertToDate(s);
			etotestinfovo.setNextDate(UtilityObject.convertToString(date,
					"yyyy-MM-dd"));
		}
		s = (String) dynaactionform.get("testLastDate");
		if (UtilityObject.isNotEmpty(s)) {
			java.util.Date date1 = UtilityObject.convertToDate(s);
			etotestinfovo.setTestDate(UtilityObject.convertToString(date1,
					"yyyy-MM-dd"));
		}
		etotestinfovo.setNextDateNote((String) dynaactionform
				.get("testNextDateNote"));
		int i = EtoEntity.addTest(etotestinfovo);
		log.debug((new StringBuilder()).append("Added ETO Test Info id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editTestInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In editTestInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_TEST_INFO", "Y");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");
	}

	public ActionForward updateTestInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In updateTestInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Test Info Id is null while updating the Test info");
		EtoTestInfoVo etotestinfovo = EtoEntity.findTest(i);
		etotestinfovo.setYear((String) dynaactionform.get("testYear"));
		String s1 = (String) dynaactionform.get("testNextDate");
		Object obj = null;
		if (UtilityObject.isNotEmpty(s1)) {
			java.util.Date date = UtilityObject.convertToDate(s1);
			etotestinfovo.setNextDate(UtilityObject.convertToString(date,
					"yyyy-MM-dd"));
		}
		s1 = (String) dynaactionform.get("testLastDate");
		if (UtilityObject.isNotEmpty(s1)) {
			java.util.Date date1 = UtilityObject.convertToDate(s1);
			etotestinfovo.setTestDate(UtilityObject.convertToString(date1,
					"yyyy-MM-dd"));
		}
		etotestinfovo.setNextDateNote((String) dynaactionform
				.get("testNextDateNote"));
		EtoEntity.updateTest(etotestinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deleteTestInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In updateTestInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Test Info Id is null while updating the Test info");
		EtoTestInfoVo etotestinfovo = EtoEntity.findTest(i);
		EtoEntity.deleteTest(etotestinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward depPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In depPermitInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setObjectId(etovo.getEtoId());
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depIssueDate")));
		permitinfovo.setNote((String) dynaactionform.get("depExpDateNote"));
		permitinfovo.setDepId(DepartmentEnum.NYCDEP.getCode());
		permitinfovo.setYear((String) dynaactionform.get("depYear"));
		int i = EtoEntity.addPermit(permitinfovo);
		log.debug((new StringBuilder()).append("Added dep permit id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editDepPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In editDepPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");
	}

	public ActionForward updateDepPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In updateDepPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the DEP Permit");
		PermitInfoVo permitinfovo = EtoEntity.findPermit(i);
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depIssueDate")));
		permitinfovo.setNote((String) dynaactionform.get("depExpDateNote"));
		permitinfovo.setYear((String) dynaactionform.get("depYear"));
		EtoEntity.updatePermit(permitinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deleteDepPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In updateDepPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the DEP Permit");
		PermitInfoVo permitinfovo = EtoEntity.findPermit(i);
		EtoEntity.deletePermit(permitinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward statePermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In statePermitInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setObjectId(etovo.getEtoId());
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateIssueDate")));
		permitinfovo.setDepId(DepartmentEnum.STATE_AGENCY.getCode());
		permitinfovo.setYear((String) dynaactionform.get("stateYear"));
		int i = EtoEntity.addPermit(permitinfovo);
		log.debug((new StringBuilder()).append("Added State permit id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In editStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STATE_PERMIT", "Y");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");
	}

	public ActionForward updateStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In editStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit");
		PermitInfoVo permitinfovo = EtoEntity.findPermit(i);
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateIssueDate")));
		permitinfovo.setYear((String) dynaactionform.get("stateYear"));
		EtoEntity.updatePermit(permitinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deleteStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("EtoAction - In editStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit");
		PermitInfoVo permitinfovo = EtoEntity.findPermit(i);
		EtoEntity.deletePermit(permitinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, etovo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest)

	{
		HttpSession httpsession = httpservletrequest.getSession();
		try {
			boolean flag = UtilityObject
					.isBoroughValidInNyc(httpservletrequest);
			if (flag)
				httpservletrequest.setAttribute("is5Borough", "Y");

		} catch (Exception e) {
			System.out.println(e);
		}

		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");

		if (etovo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEARS", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = EtoCylinderGasMixtureEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("ETO_CYLINDER_GAS_MIXTURE", dropdown1);
		com.eespc.tracking.bo.DropDown dropdown2 = EtoGasMixtureTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("ETO_GAS_MIXTURE_TYPE", dropdown2);
		com.eespc.tracking.bo.DropDown dropdown3 = ModifiedIn.getDropDownObj();
		httpservletrequest.setAttribute("ETO_STATUS", dropdown3);
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, EtoVo etovo) {
		setFieldDetails(httpservletrequest, dynaactionform, etovo, false);
	}

	private void setFieldDetailsforedit(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, EtoVo etovo) {
		setFieldDetailsforedit(httpservletrequest, dynaactionform, etovo, false);
	}

	private void setFieldDetailsforedit(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, EtoVo etovo, boolean flag) {
		if (etovo == null)
			return;
		dynaactionform.set("facilityDesignatedId",
				etovo.getFacilityDesignatedId());
		dynaactionform.set("comments", etovo.getComments());
		dynaactionform.set("dep", etovo.getDep());
		dynaactionform.set("dob", etovo.getDob());
		dynaactionform.set("actiontaken", etovo.getActiontaken());
		dynaactionform.set(
				"containerWeight",
				etovo.getContainerWeight() < 0 ? "" : ((Object) (String
						.valueOf(etovo.getContainerWeight()))));
		EtoGasMixtureTypeEnum etogasmixturetypeenum = EtoGasMixtureTypeEnum
				.get(etovo.getMixtureType());
		EtoCylinderGasMixtureEnum etocylindergasmixtureenum = EtoCylinderGasMixtureEnum
				.get(etovo.getGasMixture());
		if (flag) {
			dynaactionform
					.set("gasMixture",
							etocylindergasmixtureenum != null ? ((Object) ((new StringBuffer(
									String.valueOf(etocylindergasmixtureenum
											.getCode()))).toString())) : "");
			dynaactionform
					.set("mixtureType",
							etogasmixturetypeenum != null ? ((Object) ((new StringBuffer(
									String.valueOf(etogasmixturetypeenum
											.getCode()))).toString())) : "");
			dynaactionform.set("recordsAvailable",
					etovo.isRecordsAvailable() ? "Y" : "N");
		} else {
			dynaactionform
					.set("gasMixture",
							etocylindergasmixtureenum != null ? ((Object) (etocylindergasmixtureenum
									.getName())) : "");
			dynaactionform
					.set("mixtureType",
							etogasmixturetypeenum != null ? ((Object) (etogasmixturetypeenum
									.getName())) : "");
			dynaactionform.set("recordsAvailable",
					etovo.isRecordsAvailable() ? "Yes" : "No");
		}

		dynaactionform.set("installationDate", etovo.getInstallationDate());
		dynaactionform.set("manufacturer", etovo.getManufacturer());
		dynaactionform.set("model", etovo.getModel());
		dynaactionform.set("serial", etovo.getSerial());
		dynaactionform.set("statePermit", etovo.getStatePermit());
		dynaactionform.set("volume", etovo.getVolume());
		dynaactionform.set("DisconnectedYear", etovo.getDisconnectedYear());
		dynaactionform.set(
				"ModifiedInPast",
				(new StringBuilder()).append(etovo.getModifiedInPast())
						.append("").toString());
		StackVo stackvo = etovo.getStackObject();
		dynaactionform.set(
				"stackId",
				stackvo != null ? ((Object) ((new StringBuffer(String
						.valueOf(stackvo.getStackId()))).toString())) : "");
		dynaactionform.set("stackName",
				stackvo != null ? ((Object) (stackvo.getFacilityStackId()))
						: "");
		dynaactionform.set("isabator", etovo.getIsabator());
		dynaactionform.set("hrs", etovo.getHrs());
		dynaactionform.set("days", etovo.getDays());
		dynaactionform.set("A_decPermitObtained",
				etovo.getA_decPermitObtained());
		dynaactionform.set("A_stackTestProtSubmited",
				etovo.getA_stackTestProtSubmited());
		dynaactionform.set("A_testConductedBy", etovo.getA_testConductedBy());
		dynaactionform.set("A_testReportSubmited",
				etovo.getA_testReportSubmited());
		dynaactionform
				.set("A_permitdate", UtilityObject
						.convertYyyyMmDd2MmDdYyyy(etovo.getA_permitdate()));
		dynaactionform.set("A_compyWithETO", etovo.getA_compyWithETO());
		dynaactionform.set("amanufacturer", etovo.getAmanufacturer());
		dynaactionform.set("amodel", etovo.getAmodel());
		dynaactionform.set("A_testPassed", etovo.getA_testPassed());
		dynaactionform.set("dobsignoff", etovo.getDobsignoff());
		dynaactionform.set("etoDOB", etovo.getEtoDob());
		dynaactionform.set("stackTestRequire", etovo.getStackTestRequire());

		etovo.setTestList(null);
		etovo.setPermitList(null);
		List list = etovo.getTestList();
		if (list != null && list.size() > 0)
			httpservletrequest.setAttribute("ETO_TEST_INFO", list);
		List list1 = etovo.getPermitList();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int i = list1 != null ? list1.size() : 0;
		if (i > 0) {
			for (int j = 0; j < i; j++) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(j);
				int k = permitinfovo.getDepId();
				if (k == DepartmentEnum.STATE_AGENCY.getCode()) {
					arraylist1.add(permitinfovo);
					continue;
				}
				if (k == DepartmentEnum.NYCDEP.getCode())
					arraylist.add(permitinfovo);
			}

		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		httpservletrequest.setAttribute("ETO_STATE_PERMIT", arraylist1);
		httpservletrequest.setAttribute("ETO_DEP_PERMIT", arraylist);
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, EtoVo etovo, boolean flag) {
		if (etovo == null)
			return;
		dynaactionform.set("facilityDesignatedId",
				etovo.getFacilityDesignatedId());
		dynaactionform.set("comments", etovo.getComments());
		dynaactionform.set("dep", etovo.getDep());
		dynaactionform.set("dob", etovo.getDob());
		dynaactionform.set("actiontaken", etovo.getActiontaken());
		dynaactionform.set(
				"containerWeight",
				etovo.getContainerWeight() < 0 ? "" : ((Object) (String
						.valueOf(etovo.getContainerWeight()))));
		EtoGasMixtureTypeEnum etogasmixturetypeenum = EtoGasMixtureTypeEnum
				.get(etovo.getMixtureType());
		EtoCylinderGasMixtureEnum etocylindergasmixtureenum = EtoCylinderGasMixtureEnum
				.get(etovo.getGasMixture());
		if (flag) {
			dynaactionform
					.set("gasMixture",
							etocylindergasmixtureenum != null ? ((Object) ((new StringBuffer(
									String.valueOf(etocylindergasmixtureenum
											.getCode()))).toString())) : "");
			dynaactionform
					.set("mixtureType",
							etogasmixturetypeenum != null ? ((Object) ((new StringBuffer(
									String.valueOf(etogasmixturetypeenum
											.getCode()))).toString())) : "");
			dynaactionform.set("recordsAvailable",
					etovo.isRecordsAvailable() ? "Y" : "N");
		} else {
			dynaactionform
					.set("gasMixture",
							etocylindergasmixtureenum != null ? ((Object) (etocylindergasmixtureenum
									.getName())) : "");
			dynaactionform
					.set("mixtureType",
							etogasmixturetypeenum != null ? ((Object) (etogasmixturetypeenum
									.getName())) : "");
			dynaactionform.set("recordsAvailable",
					etovo.isRecordsAvailable() ? "Yes" : "No");
		}
		dynaactionform.set("installationDate", etovo.getInstallationDate());
		dynaactionform.set("manufacturer", etovo.getManufacturer());
		dynaactionform.set("model", etovo.getModel());
		dynaactionform.set("serial", etovo.getSerial());
		dynaactionform.set("statePermit", etovo.getStatePermit());
		dynaactionform.set("volume", etovo.getVolume());
		dynaactionform.set("DisconnectedYear", etovo.getDisconnectedYear());
		ModifiedIn modifiedin = ModifiedIn.get(etovo.getModifiedInPast());
		if (modifiedin != null)
			dynaactionform.set("ModifiedInPast", modifiedin.getName());
		StackVo stackvo = etovo.getStackObject();
		dynaactionform.set(
				"stackId",
				stackvo != null ? ((Object) ((new StringBuffer(String
						.valueOf(stackvo.getStackId()))).toString())) : "");
		dynaactionform.set("stackName",
				stackvo != null ? ((Object) (stackvo.getFacilityStackId()))
						: "");
		dynaactionform.set("isabator", etovo.getIsabator());
		dynaactionform.set("hrs", etovo.getHrs());
		dynaactionform.set("days", etovo.getDays());
		dynaactionform.set("A_decPermitObtained",
				etovo.getA_decPermitObtained());
		dynaactionform.set("A_stackTestProtSubmited",
				etovo.getA_stackTestProtSubmited());
		dynaactionform.set("A_testConductedBy", etovo.getA_testConductedBy());
		dynaactionform.set("A_testReportSubmited",
				etovo.getA_testReportSubmited());
		dynaactionform
				.set("A_permitdate", UtilityObject
						.convertYyyyMmDd2MmDdYyyy(etovo.getA_permitdate()));
		dynaactionform.set("A_compyWithETO", etovo.getA_compyWithETO());
		dynaactionform.set("amanufacturer", etovo.getAmanufacturer());
		dynaactionform.set("amodel", etovo.getAmodel());
		dynaactionform.set("A_testPassed", etovo.getA_testPassed());
		dynaactionform.set("dobsignoff", etovo.getDobsignoff());
		dynaactionform.set("etoDOB", etovo.getEtoDob());
		dynaactionform.set("stackTestRequire", etovo.getStackTestRequire());

		etovo.setTestList(null);
		etovo.setPermitList(null);
		List list = etovo.getTestList();
		if (list != null && list.size() > 0)
			httpservletrequest.setAttribute("ETO_TEST_INFO", list);
		List list1 = etovo.getPermitList();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int i = list1 != null ? list1.size() : 0;
		if (i > 0) {
			for (int j = 0; j < i; j++) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(j);
				int k = permitinfovo.getDepId();
				if (k == DepartmentEnum.STATE_AGENCY.getCode()) {
					arraylist1.add(permitinfovo);
					continue;
				}
				if (k == DepartmentEnum.NYCDEP.getCode())
					arraylist.add(permitinfovo);
			}

		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		httpservletrequest.setAttribute("ETO_STATE_PERMIT", arraylist1);
		httpservletrequest.setAttribute("ETO_DEP_PERMIT", arraylist);
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		EtoVo etovo = (EtoVo) httpsession.getAttribute("ETO_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/eto/"
					+ etovo.getEtoId() + "-"
					+ etovo.getFacilityDesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/eto/"
					+ etovo.getEtoId() + "-"
					+ etovo.getFacilityDesignatedId().trim();
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
				+ "/eto/" + etovo.getEtoId() + "-"
				+ etovo.getFacilityDesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.EtoAction.class);

}