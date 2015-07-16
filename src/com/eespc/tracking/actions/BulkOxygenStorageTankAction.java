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
import com.eespc.tracking.bo.BulkOxygenPermitVo;
import com.eespc.tracking.bo.BulkOxygenTankVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.entity.BulkOxygenTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class BulkOxygenStorageTankAction extends Action {

	public BulkOxygenStorageTankAction() {
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown);
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

		if (s != null && s.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (s != null && s.equalsIgnoreCase("VIEW"))
			return view(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("VIEWEXIST"))
			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("EDIT"))
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		/*
		 * if(s != null && s.equalsIgnoreCase("addPermit")) {
		 * addPermit(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); }
		 */
		if (s != null && s.equalsIgnoreCase("addPermit"))
			return addPermit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

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

		if (s != null && s.equalsIgnoreCase("editDobPermit"))
			return editDobPermit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteDobPermit"))
			return deleteDobPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateDob"))
			return updateDob(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		else {

			dynaactionform.set("facilityDesignatedId", "");
			dynaactionform.set("fireDeptApproval", "");
		}
		log.debug((new StringBuilder())
				.append("BulkOxygenStorageTankAction userAction is ").append(s)
				.toString());
		log.debug("BulkOxygenStorageTankAction - In Execute");
		return actionmapping.findForward("success");
	}

	public void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, BulkOxygenTankVo bulkoxygentankvo)

	{
		HttpSession httpsession = httpservletrequest.getSession();
		dynaactionform.set("facilityDesignatedId",
				bulkoxygentankvo.getFacilityDesignatedId());
		dynaactionform.set("fireDeptApproval",
				bulkoxygentankvo.isFireDeptApproval());
		dynaactionform
				.set("YearInstalled", bulkoxygentankvo.getYearInstalled());
		dynaactionform.set("DisconnectedYear",
				bulkoxygentankvo.getDisconnectedYear());
		dynaactionform.set("fireDeptApprovalno",
				bulkoxygentankvo.getFireDeptApprovalno());
		dynaactionform.set("pressuretest", bulkoxygentankvo.getPressuretest());
		dynaactionform.set("lasttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getLasttestdate()));
		dynaactionform.set("nexttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getNexttestdate()));
		dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
		// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
		dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
		dynaactionform.set(
				"ModifiedInPast",
				(new StringBuilder())
						.append(bulkoxygentankvo.getModifiedInPast())
						.append("").toString());
		java.util.List list = bulkoxygentankvo.getDobPermitList();
		if (list != null)
			httpsession.setAttribute("BLK_O2_PERMIT_LIST", list);

		httpservletrequest.removeAttribute("isComponentEditable");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("showPermit", "Y");

	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, bulkoxygentankvo);
		// setScreenInfo(httpservletrequest);
		// setFormVariable(dynaactionform, bulkoxygentankvo,
		// httpservletrequest);

		// refreshShowInfo(httpservletrequest, bulkoxygentankvo);
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
		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, bulkoxygentankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bulkoxygentankvo);
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
		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, bulkoxygentankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bulkoxygentankvo);
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
		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, bulkoxygentankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bulkoxygentankvo);
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
		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, bulkoxygentankvo);
		// setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bulkoxygentankvo);
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
		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, bulkoxygentankvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		// setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/bulkoxygentank/"
				+ bulkoxygentankvo.getId() + "-"
				+ bulkoxygentankvo.getFacilityDesignatedId().trim())) {
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
		log.debug("BulkOxygenStorageTankAction - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			httpservletrequest.setAttribute("showAddBtn", "Y");
			httpservletrequest.setAttribute("showPermit", "Y");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		} else {
			httpservletrequest.setAttribute("showAddBtn", "N");
		}
		return actionmapping.findForward("success");
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In Add");
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s2 = (String) dynaactionform.get("facilityDesignatedId");
		String s3 = (String) dynaactionform.get("YearInstalled");
		String s4 = (String) dynaactionform.get("ModifiedInPast");
		String s5 = (String) dynaactionform.get("DisconnectedYear");
		String s6 = (String) dynaactionform.get("fireDeptApproval");
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");

		BulkOxygenTankVo bulkoxygentankvo = new BulkOxygenTankVo();
		bulkoxygentankvo.setBuildingId(buildingvo.getId());
		bulkoxygentankvo.setFacilityDesignatedId(s2);
		bulkoxygentankvo.setYearInstalled(s3);
		bulkoxygentankvo.setDisconnectedYear(s5);
		bulkoxygentankvo.setModifiedInPast(Integer.parseInt(s4));
		bulkoxygentankvo.setFireDeptApproval(s6);
		bulkoxygentankvo.setFireDeptApprovalno((String) dynaactionform
				.get("fireDeptApprovalno"));
		bulkoxygentankvo.setPressuretest((String) dynaactionform
				.get("pressuretest"));
		bulkoxygentankvo.setLasttestdate((String) dynaactionform
				.get("lasttestdate"));
		bulkoxygentankvo.setNexttestdate((String) dynaactionform
				.get("nexttestdate"));
		bulkoxygentankvo.setNexttestdatenote((String) dynaactionform
				.get("nexttestdatenote"));
		bulkoxygentankvo.setBcomments((String) dynaactionform.get("bcomments"));
		// bulkoxygentankvo.setDobsignoff((String)dynaactionform.get("dobsignoff"));
		bulkoxygentankvo.setCapacity((String) dynaactionform.get("capacity"));

		try {
			int i = BulkOxygenTankEntity.add(bulkoxygentankvo);
			BulkOxygenTankVo bulkoxygentankvo1 = BulkOxygenTankEntity
					.findByPrimaryKey(i);
			if (bulkoxygentankvo1 != null)
				httpsession.setAttribute("BULK_OXYGEN_STROAGE_OBJECT",
						bulkoxygentankvo1);
			httpservletrequest.setAttribute("isComponentEditable", "N");
			httpservletrequest.setAttribute("showPermit", "Y");
			s = "Added Oxygen Storage Tank.";
			s1 = "CONFIRMATION";
			TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
					.get(bulkoxygentankvo1.getModifiedInPast());
			if (tankoperatingstatusenum != null)
				dynaactionform.set("ModifiedInPast",
						tankoperatingstatusenum.getName());
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

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In Edit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BulkOxygenTankVo bulkoxygentankvo = BulkOxygenTankEntity
				.findByPrimaryKey(i);
		if (bulkoxygentankvo != null) {
			httpsession.setAttribute("BULK_OXYGEN_STROAGE_OBJECT",
					bulkoxygentankvo);
			dynaactionform.set("facilityDesignatedId",
					bulkoxygentankvo.getFacilityDesignatedId());
			dynaactionform.set("fireDeptApproval",
					bulkoxygentankvo.isFireDeptApproval());
			dynaactionform.set("YearInstalled",
					bulkoxygentankvo.getYearInstalled());
			dynaactionform.set("DisconnectedYear",
					bulkoxygentankvo.getDisconnectedYear());
			dynaactionform.set("fireDeptApprovalno",
					bulkoxygentankvo.getFireDeptApprovalno());
			dynaactionform.set("pressuretest",
					bulkoxygentankvo.getPressuretest());
			dynaactionform.set("lasttestdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo
							.getLasttestdate()));
			dynaactionform.set("nexttestdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo
							.getNexttestdate()));
			dynaactionform.set("nexttestdatenote",
					bulkoxygentankvo.getNexttestdatenote());
			dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
			// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
			dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
			dynaactionform.set(
					"ModifiedInPast",
					(new StringBuilder())
							.append(bulkoxygentankvo.getModifiedInPast())
							.append("").toString());
			java.util.List list = bulkoxygentankvo.getDobPermitList();
			if (list != null)
				httpsession.setAttribute("BLK_O2_PERMIT_LIST", list);
		}
		httpservletrequest.removeAttribute("isComponentEditable");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		httpservletrequest.setAttribute("showPermit", "Y");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In View Exist");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BulkOxygenTankVo bulkoxygentankvo = BulkOxygenTankEntity
				.findByPrimaryKey(i);
		if (bulkoxygentankvo != null) {
			httpsession.setAttribute("BULK_OXYGEN_STROAGE_OBJECT",
					bulkoxygentankvo);
			dynaactionform.set("facilityDesignatedId",
					bulkoxygentankvo.getFacilityDesignatedId());
			dynaactionform.set("fireDeptApproval",
					bulkoxygentankvo.isFireDeptApproval());
			dynaactionform.set("YearInstalled",
					bulkoxygentankvo.getYearInstalled());
			dynaactionform.set("DisconnectedYear",
					bulkoxygentankvo.getDisconnectedYear());
			dynaactionform.set("fireDeptApprovalno",
					bulkoxygentankvo.getFireDeptApprovalno());
			dynaactionform.set("pressuretest",
					bulkoxygentankvo.getPressuretest());
			dynaactionform.set("lasttestdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo
							.getLasttestdate()));
			dynaactionform.set("nexttestdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo
							.getNexttestdate()));
			dynaactionform.set("nexttestdatenote",
					bulkoxygentankvo.getNexttestdatenote());
			dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
			// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
			dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
			dynaactionform.set(
					"ModifiedInPast",
					(new StringBuilder())
							.append(bulkoxygentankvo.getModifiedInPast())
							.append("").toString());
			java.util.List list = bulkoxygentankvo.getDobPermitList();
			if (list != null)
				httpsession.setAttribute("BLK_O2_PERMIT_LIST", list);
		}
		httpservletrequest.removeAttribute("isComponentEditable");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In View");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BulkOxygenTankVo bulkoxygentankvo = BulkOxygenTankEntity
				.findByPrimaryKey(i);
		if (bulkoxygentankvo != null) {
			httpsession.setAttribute("BULK_OXYGEN_STROAGE_OBJECT",
					bulkoxygentankvo);
			dynaactionform.set("facilityDesignatedId",
					bulkoxygentankvo.getFacilityDesignatedId());
			dynaactionform.set("fireDeptApproval",
					bulkoxygentankvo.isFireDeptApproval());
			dynaactionform.set("YearInstalled",
					bulkoxygentankvo.getYearInstalled());
			dynaactionform.set("DisconnectedYear",
					bulkoxygentankvo.getDisconnectedYear());
			dynaactionform.set("fireDeptApprovalno",
					bulkoxygentankvo.getFireDeptApprovalno());
			dynaactionform.set("pressuretest",
					bulkoxygentankvo.getPressuretest());
			dynaactionform.set("lasttestdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo
							.getLasttestdate()));
			dynaactionform.set("nexttestdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo
							.getNexttestdate()));
			dynaactionform.set("nexttestdatenote",
					bulkoxygentankvo.getNexttestdatenote());
			dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
			// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
			dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
			TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
					.get(bulkoxygentankvo.getModifiedInPast());
			if (tankoperatingstatusenum != null)
				dynaactionform.set("ModifiedInPast",
						tankoperatingstatusenum.getName());
			java.util.List list = bulkoxygentankvo.getDobPermitList();
			if (list != null)
				httpsession.setAttribute("BLK_O2_PERMIT_LIST", list);
		}
		httpservletrequest.setAttribute("showPermit", "Y");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In View");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BulkOxygenTankVo bulkoxygentankvo = BulkOxygenTankEntity
				.findByPrimaryKey(i);
		if (bulkoxygentankvo != null)
			httpsession.setAttribute("BULK_OXYGEN_STROAGE_OBJECT",
					bulkoxygentankvo);
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		BulkOxygenTankVo bulkoxygentankvo1 = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		try {
			BulkOxygenTankEntity.delete(bulkoxygentankvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/bulkoxygentank/" + bulkoxygentankvo1.getId() + "-"
						+ bulkoxygentankvo1.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successbulk");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In update");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("facilityDesignatedId");
		String s1 = (String) dynaactionform.get("fireDeptApproval");
		String s2 = (String) dynaactionform.get("YearInstalled");
		String s3 = (String) dynaactionform.get("DisconnectedYear");
		String s4 = (String) dynaactionform.get("ModifiedInPast");
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		BulkOxygenTankVo bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		String facilityDesignatedid = bulkoxygentankvo
				.getFacilityDesignatedId();

		bulkoxygentankvo.setFacilityDesignatedId(s);
		bulkoxygentankvo.setFireDeptApproval(s1);
		bulkoxygentankvo.setDisconnectedYear(s3);
		bulkoxygentankvo.setYearInstalled(s2);
		bulkoxygentankvo.setModifiedInPast(Integer.parseInt(s4));
		bulkoxygentankvo.setFireDeptApprovalno((String) dynaactionform
				.get("fireDeptApprovalno"));
		bulkoxygentankvo.setPressuretest((String) dynaactionform
				.get("pressuretest"));
		bulkoxygentankvo.setLasttestdate((String) dynaactionform
				.get("lasttestdate"));
		bulkoxygentankvo.setNexttestdate((String) dynaactionform
				.get("nexttestdate"));
		bulkoxygentankvo.setNexttestdatenote((String) dynaactionform
				.get("nexttestdatenote"));
		bulkoxygentankvo.setBcomments((String) dynaactionform.get("bcomments"));
		// bulkoxygentankvo.setDobsignoff((String)dynaactionform.get("dobsignoff"));
		bulkoxygentankvo.setCapacity((String) dynaactionform.get("capacity"));
		try {
			BulkOxygenTankEntity.update(bulkoxygentankvo);
			BulkOxygenTankVo bulkoxygentankvo1 = BulkOxygenTankEntity
					.findByPrimaryKey(bulkoxygentankvo.getId());
			if (bulkoxygentankvo1 != null) {
				httpsession.setAttribute("BULK_OXYGEN_STROAGE_OBJECT",
						bulkoxygentankvo1);

				FacilityVo facilityvo = (FacilityVo) httpsession
						.getAttribute("FACILITY_OBJECT");
				try {

					File f = new File(httpservletrequest.getRealPath("/")
							+ "/clientfolder/" + facilityvo.getDecId()
							+ "/bulkoxygentank/" + bulkoxygentankvo1.getId()
							+ "-" + facilityDesignatedid.trim());

					if (f.isDirectory()) {

						File newFileName = new File(
								httpservletrequest.getRealPath("/")
										+ "/clientfolder/"
										+ facilityvo.getDecId()
										+ "/bulkoxygentank/"
										+ bulkoxygentankvo1.getId()
										+ "-"
										+ bulkoxygentankvo1
												.getFacilityDesignatedId()
												.trim());
						f.renameTo(newFileName);

					} else {

					}
				} catch (Exception e) {
					System.out.println("Exception: " + e);
				}

				TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
						.get(bulkoxygentankvo1.getModifiedInPast());
				if (tankoperatingstatusenum != null)
					dynaactionform.set("ModifiedInPast",
							tankoperatingstatusenum.getName());
				java.util.List list = bulkoxygentankvo1.getDobPermitList();
				if (list != null)
					httpsession.setAttribute("BLK_O2_PERMIT_LIST", list);
			}
			httpservletrequest.setAttribute("showPermit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "N");
			httpservletrequest.setAttribute("showPermit", "Y");
		} catch (TrackingException trackingexception) {
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return actionmapping.findForward("success");
	}

	public ActionForward addPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		// log.debug("BulkOxygenStorageTankAction - In addPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		// BulkOxygenTankVo bulkoxygentankvo =
		// (BulkOxygenTankVo)httpservletrequest.getSession().getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		HttpSession httpsession = httpservletrequest.getSession();
		BulkOxygenTankVo bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		String s = (String) dynaactionform.get("dobPermit");
		String s1 = (String) dynaactionform.get("dobIssueDate");
		String s3 = "";
		String s4 = "";
		BulkOxygenPermitVo bulkoxygenpermitvo = new BulkOxygenPermitVo();
		bulkoxygenpermitvo.setObjectId(bulkoxygentankvo.getId());
		bulkoxygenpermitvo.setPermitNumber(s);
		bulkoxygenpermitvo.setIssueDate(UtilityObject.convertToDate(s1));

		try {
			BulkOxygenTankEntity.addPermit(bulkoxygenpermitvo);
			java.util.List list = BulkOxygenTankEntity
					.getPermitInfo(bulkoxygentankvo.getId());
			if (list != null)
				httpservletrequest.getSession().setAttribute(
						"BLK_O2_PERMIT_LIST", list);
			dynaactionform.set("dobPermit", "");
			dynaactionform.set("dobIssueDate", "");
			httpservletrequest.setAttribute("showPermit", "Y");
		} catch (TrackingException trackingexception) {
			s3 = trackingexception.getMessage();
			s4 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		/*
		 * if(s3 != null && s3.trim().length() > 0 && s4 != null &&
		 * s4.trim().length() > 0) { httpservletrequest.setAttribute("MESSAGE",
		 * s3); httpservletrequest.setAttribute("MESSAGE_TYPE", s4); }
		 */
		httpservletrequest.setAttribute("isComponentEditable", "N");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println("Throwing error while save the permit info:"
					+ exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward editDobPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In editDobPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BulkOxygenTankVo bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		dynaactionform.set("facilityDesignatedId",
				bulkoxygentankvo.getFacilityDesignatedId());
		dynaactionform.set("fireDeptApproval",
				bulkoxygentankvo.isFireDeptApproval());
		dynaactionform
				.set("YearInstalled", bulkoxygentankvo.getYearInstalled());
		dynaactionform.set("DisconnectedYear",
				bulkoxygentankvo.getDisconnectedYear());
		dynaactionform.set("fireDeptApprovalno",
				bulkoxygentankvo.getFireDeptApprovalno());
		dynaactionform.set("pressuretest", bulkoxygentankvo.getPressuretest());
		dynaactionform.set("lasttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getLasttestdate()));
		dynaactionform.set("nexttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getNexttestdate()));
		dynaactionform.set("nexttestdatenote",
				bulkoxygentankvo.getNexttestdatenote());
		dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
		// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
		dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(bulkoxygentankvo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			dynaactionform.set("ModifiedInPast",
					tankoperatingstatusenum.getName());

		java.util.List list = BulkOxygenTankEntity
				.getPermitInfo(bulkoxygentankvo.getId());
		if (list != null)
			httpservletrequest.getSession().setAttribute("BLK_O2_PERMIT_LIST",
					list);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_BLK_O2_PERMIT_LIST", "Y");
		httpservletrequest.setAttribute("showPermit", "Y");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));

		return actionmapping.findForward("success");
	}

	public ActionForward deleteDobPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In updateDob");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BulkOxygenTankVo bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		dynaactionform.set("facilityDesignatedId",
				bulkoxygentankvo.getFacilityDesignatedId());
		dynaactionform.set("fireDeptApproval",
				bulkoxygentankvo.isFireDeptApproval());
		dynaactionform
				.set("YearInstalled", bulkoxygentankvo.getYearInstalled());
		dynaactionform.set("DisconnectedYear",
				bulkoxygentankvo.getDisconnectedYear());
		dynaactionform.set("fireDeptApprovalno",
				bulkoxygentankvo.getFireDeptApprovalno());
		dynaactionform.set("pressuretest", bulkoxygentankvo.getPressuretest());
		dynaactionform.set("lasttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getLasttestdate()));
		dynaactionform.set("nexttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getNexttestdate()));
		dynaactionform.set("nexttestdatenote",
				bulkoxygentankvo.getNexttestdatenote());
		dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
		// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
		dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(bulkoxygentankvo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			dynaactionform.set("ModifiedInPast",
					tankoperatingstatusenum.getName());
		String s = (String) dynaactionform.get("dobPermit");
		String s1 = (String) dynaactionform.get("dobIssueDate");

		BulkOxygenPermitVo bulkoxygenpermitvo = new BulkOxygenPermitVo();
		String s3 = httpservletrequest.getParameter("hdnPermitId");
		if (UtilityObject.isNotEmpty(s3))
			bulkoxygenpermitvo.setId(Integer.parseInt(s3));
		else
			throw new Exception(
					"Permit Id is null while updating DOB permit info");
		BulkOxygenTankEntity.deletePermit(bulkoxygenpermitvo);
		java.util.List list = BulkOxygenTankEntity
				.getPermitInfo(bulkoxygentankvo.getId());
		if (list != null)
			httpservletrequest.getSession().setAttribute("BLK_O2_PERMIT_LIST",
					list);
		dynaactionform.set("dobPermit", "");
		dynaactionform.set("dobIssueDate", "");

		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_BLK_O2_PERMIT_LIST", "N");
		httpservletrequest.setAttribute("showPermit", "Y");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return actionmapping.findForward("success");
	}

	public ActionForward updateDob(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BulkOxygenStorageTankAction - In updateDob");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BulkOxygenTankVo bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		dynaactionform.set("facilityDesignatedId",
				bulkoxygentankvo.getFacilityDesignatedId());
		dynaactionform.set("fireDeptApproval",
				bulkoxygentankvo.isFireDeptApproval());
		dynaactionform
				.set("YearInstalled", bulkoxygentankvo.getYearInstalled());
		dynaactionform.set("DisconnectedYear",
				bulkoxygentankvo.getDisconnectedYear());
		dynaactionform.set("fireDeptApprovalno",
				bulkoxygentankvo.getFireDeptApprovalno());
		dynaactionform.set("pressuretest", bulkoxygentankvo.getPressuretest());
		dynaactionform.set("lasttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getLasttestdate()));
		dynaactionform.set("nexttestdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bulkoxygentankvo.getNexttestdate()));
		dynaactionform.set("nexttestdatenote",
				bulkoxygentankvo.getNexttestdatenote());
		dynaactionform.set("bcomments", bulkoxygentankvo.getBcomments());
		// dynaactionform.set("dobsignoff",bulkoxygentankvo.getDobsignoff());
		dynaactionform.set("capacity", bulkoxygentankvo.getCapacity());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(bulkoxygentankvo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			dynaactionform.set("ModifiedInPast",
					tankoperatingstatusenum.getName());
		String s = (String) dynaactionform.get("dobPermit");
		String s1 = (String) dynaactionform.get("dobIssueDate");

		BulkOxygenPermitVo bulkoxygenpermitvo = new BulkOxygenPermitVo();
		bulkoxygenpermitvo.setObjectId(bulkoxygentankvo.getId());
		bulkoxygenpermitvo.setIssueDate(UtilityObject.convertToDate(s1));
		bulkoxygenpermitvo.setPermitNumber(s);
		String s3 = httpservletrequest.getParameter("hdnPermitId");
		if (UtilityObject.isNotEmpty(s3))
			bulkoxygenpermitvo.setId(Integer.parseInt(s3));
		else
			throw new Exception(
					"Permit Id is null while updating DOB permit info");
		BulkOxygenTankEntity.updatePermit(bulkoxygenpermitvo);
		java.util.List list = BulkOxygenTankEntity
				.getPermitInfo(bulkoxygentankvo.getId());
		if (list != null)
			httpservletrequest.getSession().setAttribute("BLK_O2_PERMIT_LIST",
					list);
		dynaactionform.set("dobPermit", "");
		dynaactionform.set("dobIssueDate", "");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_BLK_O2_PERMIT_LIST", "N");
		httpservletrequest.setAttribute("showPermit", "Y");

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return actionmapping.findForward("success");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BulkOxygenTankVo bulkoxygentankvo = (BulkOxygenTankVo) httpsession
				.getAttribute("BULK_OXYGEN_STROAGE_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/bulkoxygentank/" + bulkoxygentankvo.getId() + "-"
					+ bulkoxygentankvo.getFacilityDesignatedId().trim());
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
					+ "/bulkoxygentank/" + bulkoxygentankvo.getId() + "-"
					+ bulkoxygentankvo.getFacilityDesignatedId().trim();
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
				+ "/bulkoxygentank/" + bulkoxygentankvo.getId() + "-"
				+ bulkoxygentankvo.getFacilityDesignatedId().trim());

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
			.getLog(com.eespc.tracking.actions.BulkOxygenStorageTankAction.class);

}