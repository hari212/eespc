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
import com.eespc.tracking.bo.ElevatorPermitVo;
import com.eespc.tracking.bo.ElevatorVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.HydraulicElevatorAndOtherTankVo;
import com.eespc.tracking.bo.myenum.ModifiedIn;
import com.eespc.tracking.entity.ElevatorEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class ElevatorAction extends LookupDispatchAction {

	public ElevatorAction() {
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
		try {

			setDropDown(request);
			HttpSession session = request.getSession();
			DynaValidatorForm incForm = (DynaValidatorForm) form;
			userAction = (String) incForm.get("methodToCall");
			log.debug("ElevatorAction - MethodToCall " + userAction);
			if (userAction != null && userAction.equalsIgnoreCase("Save")) {
				System.out
						.println("Welcome------------------------------ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
								+ userAction);
				return save(mapping, form, request, response);
			}
			if (userAction != null && userAction.equalsIgnoreCase("Update"))
				return update(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("delete"))
				return delete(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("Return"))
				return add(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("Reset"))
				return reset(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("fileupload"))
				return fileupload(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("back"))
				return back(mapping, form, request, response);
			if (userAction != null
					&& userAction.equalsIgnoreCase("addnewfolder"))
				return addnewfolder(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("deletefile"))
				return deletefile(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("editfile"))
				return editfile(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("viewfile"))
				return viewfile(mapping, form, request, response);

			if (userAction != null
					&& userAction.equalsIgnoreCase("displaycontrol"))
				return changedisplaycontrol(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("viewexist"))
				return viewexist(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("dep"))
				return dep(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("depEdit"))
				return depEdit(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("depDelete"))
				return depDelete(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("depUpdate"))
				return depUpdate(mapping, form, request, response);
		} catch (Exception e) {
		}
		request.setAttribute("SHOW_BUTTONS", "inline");
		request.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		request.setAttribute("ELE_DEP", "none");
		request.setAttribute("ELE_DEP_LST", null);
		return mapping.findForward("success");
	}

	public ActionForward changedisplaycontrol(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		// httpservletrequest.setAttribute("isComponentEditable", "Y");
		// httpservletrequest.setAttribute("isItForEdit", "Y");
		log.debug("Elavator - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		System.out.println("Elevaterdisplay:" + s);
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
			httpservletrequest.setAttribute("showAddBtn", "Y");
			// httpservletrequest.setAttribute("ELE_DEP", "inline");
		} else {
			httpservletrequest.setAttribute("isItForEdit", "N");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
			httpservletrequest.setAttribute("showAddBtn", "N");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Elevator - ADD");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo.setBuildingId(buildingvo.getId());
		elevatorvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("eleId"));
		elevatorvo.setEleType((String) dynavalidatorform.get("eleType"));
		String s2 = (String) dynavalidatorform.get("YearInstalled");
		String s3 = (String) dynavalidatorform.get("ModifiedInPast");
		String s4 = (String) dynavalidatorform.get("DisconnectedYear");
		elevatorvo.setYearInstalled(s2);
		elevatorvo.setDisconnectedYear(s4);
		String s5 = (String) dynavalidatorform.get("ModifiedInPast");
		if (UtilityObject.isNotEmpty(s5))
			elevatorvo.setModifiedInPast(Integer.parseInt(s5));
		elevatorvo.setComment((String) dynavalidatorform.get("elecomment"));
		String s6 = (String) dynavalidatorform.get("eleHydTank");
		String s7 = (String) dynavalidatorform.get("eleHydTankFrom");
		if (UtilityObject.isNotEmpty(s6) && UtilityObject.isNotEmpty(s7))
			elevatorvo.setHydraulicTankId(Integer.parseInt(s6));
		byte byte0 = -99;
		try {
			int i = ElevatorEntity.add(elevatorvo);

			System.out
					.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"
							+ "Added Elevator Successfully" + i);

			elevatorvo = ElevatorEntity.findByPrimaryKey(i);
			if (elevatorvo != null) {
				httpsession.setAttribute("ELEVATOR_OBJECT", elevatorvo);
				setFormVariable(dynavalidatorform, elevatorvo,
						httpservletrequest);
			}
			s = "Added Elevator Successfully.";
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
		refreshShowInfo(httpservletrequest, elevatorvo);
		if (elevatorvo != null) {
			httpsession.setAttribute("ELEVATOR_OBJECT", elevatorvo);
			setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo = (ElevatorVo) httpsession.getAttribute("ELEVATOR_OBJECT");
		setFormVariable(dynaactionform, elevatorvo, httpservletrequest);

		refreshShowInfo(httpservletrequest, elevatorvo);
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
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo = (ElevatorVo) httpsession.getAttribute("ELEVATOR_OBJECT");
		setFormVariable(dynaactionform, elevatorvo, httpservletrequest);

		refreshShowInfo(httpservletrequest, elevatorvo);
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
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo = (ElevatorVo) httpsession.getAttribute("ELEVATOR_OBJECT");
		setFormVariable(dynaactionform, elevatorvo, httpservletrequest);

		refreshShowInfo(httpservletrequest, elevatorvo);
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
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo = (ElevatorVo) httpsession.getAttribute("ELEVATOR_OBJECT");
		setFormVariable(dynaactionform, elevatorvo, httpservletrequest);

		refreshShowInfo(httpservletrequest, elevatorvo);
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
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo = (ElevatorVo) httpsession.getAttribute("ELEVATOR_OBJECT");
		setFormVariable(dynaactionform, elevatorvo, httpservletrequest);

		refreshShowInfo(httpservletrequest, elevatorvo);
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
		ElevatorVo elevatorvo = new ElevatorVo();
		elevatorvo = (ElevatorVo) httpsession.getAttribute("ELEVATOR_OBJECT");
		setFormVariable(dynaactionform, elevatorvo, httpservletrequest);

		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/elevator/"
				+ elevatorvo.getElevatorId() + "-"
				+ elevatorvo.getFacilityDesignatedId().trim())) {
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

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, Exception {
		log.debug("Elevator - Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		String facilityDesignatedid = elevatorvo.getFacilityDesignatedId();
		elevatorvo.setBuildingId(buildingvo.getId());
		elevatorvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("eleId"));
		elevatorvo.setEleType((String) dynavalidatorform.get("eleType"));
		String s2 = (String) dynavalidatorform.get("YearInstalled");
		String s3 = (String) dynavalidatorform.get("DisconnectedYear");
		String s4 = (String) dynavalidatorform.get("ModifiedInPast");
		elevatorvo.setDisconnectedYear(s3);
		elevatorvo.setYearInstalled(s2);
		String s5 = (String) dynavalidatorform.get("ModifiedInPast");
		if (UtilityObject.isNotEmpty(s5))
			elevatorvo.setModifiedInPast(Integer.parseInt(s5));
		elevatorvo.setComment((String) dynavalidatorform.get("elecomment"));
		String s6 = (String) dynavalidatorform.get("eleHydTank");
		String s7 = (String) dynavalidatorform.get("eleHydTankFrom");
		System.out.println((new StringBuilder()).append("sigma").append(s6)
				.append("-").append(s7).toString());
		if (UtilityObject.isNotEmpty(s6) && UtilityObject.isNotEmpty(s7))
			elevatorvo.setHydraulicTankId(Integer.parseInt(s6));
		byte byte0 = -99;
		try {
			ElevatorEntity.update(elevatorvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/elevator/" + elevatorvo.getElevatorId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/elevator/"
									+ elevatorvo.getElevatorId()
									+ "-"
									+ elevatorvo.getFacilityDesignatedId()
											.trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			s = "Updated Elevator Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
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

	/*
	 * public ActionForward displayControl(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception { return
	 * displayControl(actionmapping, actionform, httpservletrequest,
	 * httpservletresponse, false); }
	 * 
	 * public ActionForward displayControl(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse, boolean flag) throws Exception {
	 * DynaActionForm dynaactionform = (DynaActionForm)actionform; String s =
	 * (String)dynaactionform.get("ModifiedInPast"); ModifiedIn modifiedin =
	 * null; if(UtilityObject.isNotEmpty(s) && !s.equalsIgnoreCase("-1") &&
	 * !s.equalsIgnoreCase("5")) try { modifiedin =
	 * ModifiedIn.get(Integer.parseInt(s)); } catch(NumberFormatException
	 * numberformatexception) { modifiedin = ModifiedIn.parse(s); } return
	 * actionmapping.findForward("success"); }
	 */
	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		try {

			DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;

			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynavalidatorform.get("id");

			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);

			ElevatorVo elevatorvo = ElevatorEntity.findByPrimaryKey(i);

			if (elevatorvo != null)
				httpsession.setAttribute("ELEVATOR_OBJECT", elevatorvo);

			ElevatorVo elevatorvo1 = (ElevatorVo) httpsession
					.getAttribute("ELEVATOR_OBJECT");

			/*
			 * int more = JOptionPane.YES_OPTION; more =
			 * JOptionPane.showConfirmDialog(null,
			 * "Also Delete"+elevatorvo.getFacilityDesignatedId
			 * ()+"(Elevator Name) Document Folder?",
			 * "Input",JOptionPane.YES_NO_OPTION); if(more==0) {
			 * System.out.println(""+more); } else {
			 * System.out.println(""+more); }
			 */

			ElevatorEntity.delete(elevatorvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/elevator/" + elevatorvo1.getElevatorId() + "-"
						+ elevatorvo1.getFacilityDesignatedId().trim());
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

		} catch (Exception trackingexception) {
			System.out.println("" + trackingexception);
			log.error(trackingexception);
		}

		return actionmapping.findForward("successelevator");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform1,
			ElevatorVo elevatorvo, HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Elevator setForm variable");

		dynavalidatorform1.set(
				"eleId",
				(new StringBuilder())
						.append(elevatorvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("eleType",
				(new StringBuilder()).append(elevatorvo.getEleType())
						.append("").toString());
		// dynavalidatorform1.set("elecomment", (new
		// StringBuilder()).append(elevatorvo.getComment()).append("").toString());
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = elevatorvo
				.getFuelFromObj();
		if (hydraulicelevatorandothertankvo != null) {
			dynavalidatorform1.set(
					"eleHydTank",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo.getId())
							.append("").toString());
			dynavalidatorform1.set("eleHydTankFrom",
					hydraulicelevatorandothertankvo.getFacilityDesignatedId());
		} else {
			dynavalidatorform1.set("eleHydTank", "");
			dynavalidatorform1.set("eleHydTankFrom", "");
		}
		dynavalidatorform1.set("YearInstalled", elevatorvo.getYearInstalled());
		dynavalidatorform1.set("DisconnectedYear",
				elevatorvo.getDisconnectedYear());
		ModifiedIn modifiedin = ModifiedIn.get(elevatorvo.getModifiedInPast());
		if (modifiedin != null)
			dynavalidatorform1.set("ModifiedInPast", modifiedin.getName());
		System.out.println("ViewComment:" + elevatorvo.getComment());
		dynavalidatorform1.set("elecomment",
				String.valueOf(elevatorvo.getComment()));
		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		try {
			setScreenInfo(httpservletrequest);
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

	}

	public void setFormVariableforedit(DynaValidatorForm dynavalidatorform1,
			ElevatorVo elevatorvo, HttpServletRequest httpservletrequest) {
		try {
			HttpSession httpsession = httpservletrequest.getSession();
			log.debug("Elevator setForm variable");

			dynavalidatorform1.set(
					"eleId",
					(new StringBuilder())
							.append(elevatorvo.getFacilityDesignatedId())
							.append("").toString());
			dynavalidatorform1.set("eleType",
					(new StringBuilder()).append(elevatorvo.getEleType())
							.append("").toString());
			// dynavalidatorform1.set("elecomment", (new
			// StringBuilder()).append(elevatorvo.getComment()).append("").toString());

			dynavalidatorform1.set(
					"eleHydTank",
					(new StringBuilder())
							.append(elevatorvo.getHydraulicTankId()).append("")
							.toString());
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = elevatorvo
					.getFuelFromObj();
			if (hydraulicelevatorandothertankvo != null) {
				dynavalidatorform1.set("eleHydTank", (new StringBuilder())
						.append(hydraulicelevatorandothertankvo.getId())
						.append("").toString());
				dynavalidatorform1.set("eleHydTankFrom",
						hydraulicelevatorandothertankvo
								.getFacilityDesignatedId());

			} else {
				dynavalidatorform1.set("eleHydTank", "");
				dynavalidatorform1.set("eleHydTankFrom", "");
			}
			dynavalidatorform1.set("YearInstalled",
					elevatorvo.getYearInstalled());
			dynavalidatorform1.set("DisconnectedYear",
					elevatorvo.getDisconnectedYear());
			dynavalidatorform1.set("ModifiedInPast",
					String.valueOf(elevatorvo.getModifiedInPast()));
			System.out.println("ViewComment:" + elevatorvo.getComment());
			dynavalidatorform1.set("elecomment",
					String.valueOf(elevatorvo.getComment()));
			reset(httpservletrequest);

		} catch (Exception exception) {

			System.out.println("View For Edit elvator:" + exception);

		}

		/*
		 * refreshShowInfo(httpservletrequest, elevatorvo);
		 * refreshPermitInfo(httpservletrequest); try {
		 * setScreenInfo(httpservletrequest); } catch(Exception exception) { }
		 */

	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, ElevatorVo elevatorvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Elevator setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"eleId",
				(new StringBuilder())
						.append(elevatorvo.getFacilityDesignatedId())
						.append("").toString());
		dynavalidatorform1.set("eleType",
				(new StringBuilder()).append(elevatorvo.getEleType())
						.append("").toString());
		// dynavalidatorform1.set("elecomment", (new
		// StringBuilder()).append(elevatorvo.getComment()).append("").toString());

		dynavalidatorform1.set("eleHydTank",
				(new StringBuilder()).append(elevatorvo.getHydraulicTankId())
						.append("").toString());
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = elevatorvo
				.getFuelFromObj();
		if (hydraulicelevatorandothertankvo != null) {
			dynavalidatorform1.set(
					"eleHydTank",
					(new StringBuilder())
							.append(hydraulicelevatorandothertankvo.getId())
							.append("").toString());
			dynavalidatorform1.set("eleHydTankFrom",
					hydraulicelevatorandothertankvo.getFacilityDesignatedId());
		} else {
			dynavalidatorform1.set("eleHydTank", "");
			dynavalidatorform1.set("eleHydTankFrom", "");
		}
		dynavalidatorform1.set("YearInstalled", elevatorvo.getYearInstalled());
		dynavalidatorform1.set("DisconnectedYear",
				elevatorvo.getDisconnectedYear());
		dynavalidatorform1.set("ModifiedInPast",
				(new StringBuilder()).append(elevatorvo.getModifiedInPast())
						.append("").toString());

		dynavalidatorform1.set("elecomment",
				String.valueOf(elevatorvo.getComment()));
		try {
			setScreenInfo(httpservletrequest);
		} catch (Exception exception) {
		}
		return dynavalidatorform1;
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("RETURN");
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Elevator - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ElevatorVo elevatorvo = ElevatorEntity.findByPrimaryKey(i);
		if (elevatorvo != null) {
			httpsession.setAttribute("ELEVATOR_OBJECT", elevatorvo);
			setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get ElevatorVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Elevator Edit");
		log.debug("Elevator - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ElevatorVo elevatorvo = ElevatorEntity.findByPrimaryKey(i);
		if (elevatorvo != null) {
			httpsession.setAttribute("ELEVATOR_OBJECT", elevatorvo);
			setFormVariableforedit(dynavalidatorform, elevatorvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get ElevatorVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Elevator - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ElevatorVo elevatorvo = ElevatorEntity.findByPrimaryKey(i);
		if (elevatorvo != null) {
			httpsession.setAttribute("ELEVATOR_OBJECT", elevatorvo);
			dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
					elevatorvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get ElevatorVo for id(").append(s)
					.append(")").toString());
		}
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward search(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("SEARCH");
		return actionmapping.findForward("success");
	}

	public ActionForward dep(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Elevator DEP");
		log.debug("ElevatorAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		String s5 = "";
		String s6 = "";
		s = (String) dynavalidatorform.get("elePermit");
		s1 = (String) dynavalidatorform.get("eleNextInspDate");
		s2 = (String) dynavalidatorform.get("eleLastInsDate");
		s3 = (String) dynavalidatorform.get("eleRepSubmDate");
		s4 = (String) dynavalidatorform.get("eleFirm");
		s5 = (String) dynavalidatorform.get("eleRepSubmDateNote");
		s6 = (String) dynavalidatorform.get("eleNextInspDateNote");
		ElevatorPermitVo elevatorpermitvo = new ElevatorPermitVo();
		elevatorpermitvo.setElevatorId(elevatorvo.getElevatorId());
		elevatorpermitvo.setPermitNumber(s);
		elevatorpermitvo.setLastInspDate(s2);
		elevatorpermitvo.setNextInspDate(s1);
		elevatorpermitvo.setReportSubmitDate(s3);
		elevatorpermitvo.setFirmInspected(s4);
		elevatorpermitvo.setReportSubmitDateNote(s5);
		elevatorpermitvo.setNextInspDateNote(s6);
		ElevatorEntity.add(elevatorpermitvo);
		setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
		dynavalidatorform.set("elePermit", "");
		dynavalidatorform.set("eleNextInspDate", "");
		dynavalidatorform.set("eleLastInsDate", "");
		dynavalidatorform.set("eleRepSubmDate", "");
		dynavalidatorform.set("eleFirm", "");
		dynavalidatorform.set("eleRepSubmDateNote", "");
		dynavalidatorform.set("eleNextInspDateNote", "");
		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("DEP Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		return actionmapping.findForward("success");
	}

	public ActionForward depDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("ElevatorAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("Elevator - In updateDepPermitInfo - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Elevator Permit Id is null while updating the Elevator Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		String s5 = "";
		String s6 = "";
		String s7 = "";
		s1 = (String) dynavalidatorform.get("elePermit");
		s2 = (String) dynavalidatorform.get("eleNextInspDate");
		s3 = (String) dynavalidatorform.get("eleLastInsDate");
		s4 = (String) dynavalidatorform.get("eleRepSubmDate");
		s5 = (String) dynavalidatorform.get("eleFirm");
		s6 = (String) dynavalidatorform.get("eleRepSubmDateNote");
		s7 = (String) dynavalidatorform.get("eleNextInspDateNote");
		ElevatorPermitVo elevatorpermitvo = new ElevatorPermitVo();
		elevatorpermitvo.setElevatorId(elevatorvo.getElevatorId());
		elevatorpermitvo.setPermitNumber(s1);
		elevatorpermitvo.setPermitId(Integer.parseInt(s));
		ElevatorEntity.delete(elevatorpermitvo);
		setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
		dynavalidatorform.set("elePermit", "");
		dynavalidatorform.set("eleNextInspDate", "");
		dynavalidatorform.set("eleLastInsDate", "");
		dynavalidatorform.set("eleRepSubmDate", "");
		dynavalidatorform.set("eleFirm", "");
		dynavalidatorform.set("eleRepSubmDateNote", "");
		dynavalidatorform.set("eleNextInspDateNote", "");
		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("ElevatorAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("Elevator - In updateDepPermitInfo - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Elevator Permit Id is null while updating the Elevator Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		String s4 = "";
		String s5 = "";
		String s6 = "";
		String s7 = "";
		s1 = (String) dynavalidatorform.get("elePermit");
		s2 = (String) dynavalidatorform.get("eleNextInspDate");
		s3 = (String) dynavalidatorform.get("eleLastInsDate");
		s4 = (String) dynavalidatorform.get("eleRepSubmDate");
		s5 = (String) dynavalidatorform.get("eleFirm");
		s6 = (String) dynavalidatorform.get("eleRepSubmDateNote");
		s7 = (String) dynavalidatorform.get("eleNextInspDateNote");
		ElevatorPermitVo elevatorpermitvo = new ElevatorPermitVo();
		elevatorpermitvo.setElevatorId(elevatorvo.getElevatorId());
		elevatorpermitvo.setPermitNumber(s1);
		elevatorpermitvo.setLastInspDate(s3);
		elevatorpermitvo.setNextInspDate(s2);
		elevatorpermitvo.setReportSubmitDate(s4);
		elevatorpermitvo.setFirmInspected(s5);
		elevatorpermitvo.setReportSubmitDateNote(s6);
		elevatorpermitvo.setNextInspDateNote(s7);
		elevatorpermitvo.setPermitId(Integer.parseInt(s));
		ElevatorEntity.update(elevatorpermitvo);
		setFormVariable(dynavalidatorform, elevatorvo, httpservletrequest);
		dynavalidatorform.set("elePermit", "");
		dynavalidatorform.set("eleNextInspDate", "");
		dynavalidatorform.set("eleLastInsDate", "");
		dynavalidatorform.set("eleRepSubmDate", "");
		dynavalidatorform.set("eleFirm", "");
		dynavalidatorform.set("eleRepSubmDateNote", "");
		dynavalidatorform.set("eleNextInspDateNote", "");
		refreshShowInfo(httpservletrequest, elevatorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	private void refreshShowInfo(HttpServletRequest httpservletrequest,
			ElevatorVo elevatorvo) {
		httpservletrequest.setAttribute("ELE_DEP", "inline");
		httpservletrequest.setAttribute("ELE_DEP_LST", null);
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		ArrayList arraylist = new ArrayList();
		try {
			if (elevatorvo == null) {
				log.debug("Elevator Object is null");
				return;
			}
			elevatorvo.getElevatorId();
			elevatorvo.setPermitInfo(null);

			List list = elevatorvo.getPermitInfo();
			int i = list != null ? list.size() : -1;
			byte byte0 = -1;
			for (int j = 0; j < i; j++) {
				ElevatorPermitVo elevatorpermitvo = (ElevatorPermitVo) list
						.get(j);
				arraylist.add(elevatorpermitvo);
			}

			log.debug((new StringBuilder()).append("ELE_DEP_LST size=")
					.append(arraylist.size()).toString());
		} catch (Exception e) {
			System.out.println("refreshPermitInfo Dob:" + e);
		}

		httpservletrequest.setAttribute("ELE_DEP_LST", arraylist);
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("eleId", "");
		dynavalidatorform.set("eleType", "");
		dynavalidatorform.set("eleHydTank", "");
		dynavalidatorform.set("elePermit", "");
		dynavalidatorform.set("eleLastInsDate", "");
		dynavalidatorform.set("eleRepSubmDate", "");
		// dynavalidatorform.set("eleRepSubmDateNote", "");
		dynavalidatorform.set("eleFirm", "");
		dynavalidatorform.set("eleNextInspDate", "");
		dynavalidatorform.set("eleNextInspDateNote", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("ELE_DEP", "none");
		httpservletrequest.setAttribute("ELE_DEP_LST", null);

		return actionmapping.findForward("success");
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
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		if (elevatorvo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ElevatorVo elevatorvo = (ElevatorVo) httpsession
				.getAttribute("ELEVATOR_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/elevator/"
					+ elevatorvo.getElevatorId() + "-"
					+ elevatorvo.getFacilityDesignatedId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/elevator/"
					+ elevatorvo.getElevatorId() + "-"
					+ elevatorvo.getFacilityDesignatedId().trim();
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
				+ "/elevator/" + elevatorvo.getElevatorId() + "-"
				+ elevatorvo.getFacilityDesignatedId().trim());

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

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = ModifiedIn.getDropDownObj();
		httpservletrequest.setAttribute("ELE_STATUS", dropdown);
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.IncineratorAction.class);
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