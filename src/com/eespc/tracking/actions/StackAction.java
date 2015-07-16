package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingManager;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class StackAction extends Action {

	public StackAction() {
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
		setScreenDetails(httpservletrequest, dynaactionform);
		String s = (String) dynaactionform.get("methodToCall");
		if (s != null && s.equalsIgnoreCase("ADD"))
			return add(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("UPDATE"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("Delete"))
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
		if (s != null && s.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		/*
		 * if(s != null && s.equalsIgnoreCase("statePermitInfo")) return
		 * statePermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("nyDobPermitInfo")) return
		 * nyDobPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("commonPermitInfo")) return
		 * commonPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("editStatePermitInfo")) return
		 * editStatePermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("deleteStatePermitInfo")) return
		 * deleteStatePermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("updateStatePermitInfo")) return
		 * updateStatePermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("editNyDobPermitInfo")) return
		 * editNyDobPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("updateNyDobPermitInfo")) return
		 * updateNyDobPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("deleteNyDobPermitInfo")) return
		 * deleteNyDobPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("editCommonPermitInfo")) return
		 * editCommonPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("deleteCommonPermitInfo")) return
		 * deleteCommonPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); if(s != null &&
		 * s.equalsIgnoreCase("updateCommonPermitInfo")) { return
		 * updateCommonPermitInfo(actionmapping, actionform, httpservletrequest,
		 * httpservletresponse); }
		 */

		else {

			dynaactionform.set("floor", "");
			dynaactionform.set("height", "");
			dynaactionform.set("diameter", "");
			dynaactionform.set("flowRate", "");
			dynaactionform.set("exhaustTemp", "");
			dynaactionform.set("velocity", "");
			dynaactionform.set("emissionPointId", "");
			dynaactionform.set("YearInstalled", "");
			dynaactionform.set("facilityStackId", "");
			dynaactionform.set("noofsource", "");
			dynaactionform.set("totalcapacity", "");
			dynaactionform.set("typeoffuel", "");
			/*
			 * dynaactionform.set("ModifiedInPast", "");
			 * dynaactionform.set("DisconnectedYear", "");
			 */
			dynaactionform.set("methodNineTest", "");
			dynaactionform.set("methodNineLastDate", "");
			dynaactionform.set("methodNineNextTestDate", "");
			dynaactionform.set("opacityLimit", "");
			log.debug("StackAction - In Execute");
			httpservletrequest.getSession().removeAttribute("STACK_OBJECT");
			setScreenInfo(httpservletrequest);
			return actionmapping.findForward("success");
		}
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StackAction - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			httpservletrequest.setAttribute("showAddBtn", "Y");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		} else {
			httpservletrequest.setAttribute("showAddBtn", "N");
		}
		// setScreenInfo(httpservletrequest);4543

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return actionmapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		StackVo stackvo = new StackVo();
		stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		setScreenFieldDetails(dynaactionform, stackvo);

		setScreenInfo(httpservletrequest);
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
		StackVo stackvo = new StackVo();
		stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		setScreenFieldDetails(dynaactionform, stackvo);
		setScreenInfo(httpservletrequest);

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

	/*
	 * public ActionForward viewfile(ActionMapping actionmapping, ActionForm
	 * actionform, HttpServletRequest httpservletrequest, HttpServletResponse
	 * httpservletresponse) throws IOException, ServletException {
	 * 
	 * 
	 * 
	 * DynaActionForm dynaactionform = (DynaActionForm)actionform;
	 * 
	 * HttpSession httpsession = httpservletrequest.getSession(); StackVo
	 * stackvo =new StackVo();
	 * stackvo=(StackVo)httpsession.getAttribute("STACK_OBJECT");
	 * 
	 * setScreenFieldDetails(dynaactionform,stackvo);
	 * setScreenInfo(httpservletrequest); reset(httpservletrequest);
	 * //setFieldDetails(httpservletrequest, dynaactionform, stackvo);
	 * 
	 * httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
	 * httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * 
	 * String path= (String)dynaactionform.get("pathname"); String foldername=
	 * (String)dynaactionform.get("foldername");
	 * setformvariable((path+"/"+foldername
	 * ),actionmapping,actionform,httpservletrequest,httpservletresponse);
	 * return actionmapping.findForward("success"); }
	 */

	public ActionForward viewfile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		StackVo stackvo = new StackVo();
		stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		setScreenFieldDetails(dynaactionform, stackvo);
		setScreenInfo(httpservletrequest);

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
		StackVo stackvo = new StackVo();
		stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		setScreenFieldDetails(dynaactionform, stackvo);
		setScreenInfo(httpservletrequest);

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
		StackVo stackvo = new StackVo();
		stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		setScreenFieldDetails(dynaactionform, stackvo);
		setScreenInfo(httpservletrequest);

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
		StackVo stackvo = new StackVo();
		stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		setScreenFieldDetails(dynaactionform, stackvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");

		if (path.equals(facilityvo.getDecId() + "/stack/"
				+ stackvo.getStackId() + "-"
				+ stackvo.getFacilityStackId().trim())) {
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

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StackAction - In View");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		StackVo stackvo = StackEntity.findByPrimaryKey(i);
		if (stackvo != null)
			httpsession.setAttribute("STACK_OBJECT", stackvo);
		else
			log.debug((new StringBuilder())
					.append("Unable to get StackVo for id(").append(s)
					.append(")").toString());
		setScreenDetails(httpservletrequest, dynaactionform);
		setScreenFieldDetails(dynaactionform, stackvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

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
		log.debug("StackAction - In Add");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		StackVo stackvo = new StackVo();
		stackvo.setBuildingId(buildingvo.getId());
		String s1 = (String) dynaactionform.get("floor");
		stackvo.setFloor(s1);
		stackvo.setHeight(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("height")));
		stackvo.setDiameter(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("diameter")));
		stackvo.setFlowRate(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("flowRate")));
		stackvo.setExhaustTemp(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("exhaustTemp")));
		stackvo.setVelocity(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("velocity")));
		String s2 = (String) dynaactionform.get("emissionPointId");
		stackvo.setDecEmissionPointId(s2);
		String s3 = (String) dynaactionform.get("YearInstalled");
		stackvo.setYearInstalled(s3);
		String s = (String) dynaactionform.get("facilityStackId");
		stackvo.setFacilityStackId(s);
		stackvo.setNoofsource(UtilityObject.checkNullAndFill(
				(String) dynaactionform.get("noofsource"), ""));
		stackvo.setTotalcapacity(UtilityObject.checkNullAndFill(
				(String) dynaactionform.get("totalcapacity"), ""));
		stackvo.setTypeoffuel(UtilityObject.checkNullAndFill(
				(String) dynaactionform.get("typeoffuel"), ""));
		String sde = (String) dynaactionform.get("methodNineTest");
		stackvo.setMethodNineTest(sde);

		String sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("methodNineLastDate")), "yyyy-MM-dd");
		stackvo.setMethodNineLastDate(sd);

		String sd1 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("methodNineNextTestDate")), "yyyy-MM-dd");
		stackvo.setMethodNineNextTestDate(sd1);

		String sde1 = (String) dynaactionform.get("opacityLimit");
		stackvo.setOpacityLimit(sde1);
		// String s4 ="1";
		// stackvo.setModifiedInPast(Integer.parseInt(s4));
		// String s5 = "";
		// stackvo.setDisconnectedYear(s5);
		// stackvo.setFacilityStackId((String)dynaactionform.get("facilityStackId"));

		byte byte0 = -99;
		String s6 = "";
		String s7 = "";
		try {
			int i = StackEntity.add(stackvo);
			stackvo = StackEntity.findByPrimaryKey(i);
			if (stackvo != null)
				httpsession.setAttribute("STACK_OBJECT", stackvo);
			refreshBuildingObject(httpservletrequest);
			setScreenFieldDetails(dynaactionform, stackvo);
			s6 = "Added Stack Successfully.";
			s7 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s6 != null && s6.trim().length() > 0 && s7 != null
				&& s7.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s6);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s7);
		}
		setScreenInfo(httpservletrequest);

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StackAction - In Update");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		StackVo stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		StackVo stackvo1 = new StackVo();
		stackvo1.setStackId(stackvo.getStackId());
		String s1 = (String) dynaactionform.get("floor");
		stackvo1.setFloor(s1);
		stackvo1.setHeight(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("height")));
		stackvo1.setDiameter(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("diameter")));
		stackvo1.setFlowRate(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("flowRate")));
		stackvo1.setExhaustTemp(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("exhaustTemp")));
		stackvo1.setVelocity(UtilityObject
				.getDoubleFromString((String) dynaactionform.get("velocity")));
		String s2 = (String) dynaactionform.get("emissionPointId");
		stackvo1.setDecEmissionPointId(s2);
		String s3 = (String) dynaactionform.get("YearInstalled");
		stackvo1.setYearInstalled(s3);
		String s = (String) dynaactionform.get("facilityStackId");
		stackvo1.setFacilityStackId(s);
		stackvo1.setNoofsource(UtilityObject.checkNullAndFill(
				(String) dynaactionform.get("noofsource"), ""));
		stackvo1.setTotalcapacity(UtilityObject.checkNullAndFill(
				(String) dynaactionform.get("totalcapacity"), ""));
		stackvo1.setTypeoffuel(UtilityObject.checkNullAndFill(
				(String) dynaactionform.get("typeoffuel"), ""));
		String sde = (String) dynaactionform.get("methodNineTest");
		stackvo1.setMethodNineTest(sde);

		String sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("methodNineLastDate")), "yyyy-MM-dd");
		stackvo1.setMethodNineLastDate(sd);

		String sd1 = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform
						.get("methodNineNextTestDate")), "yyyy-MM-dd");
		stackvo1.setMethodNineNextTestDate(sd1);

		String sde1 = (String) dynaactionform.get("opacityLimit");
		stackvo1.setOpacityLimit(sde1);
		// String s4 ="1";
		// stackvo.setModifiedInPast(Integer.parseInt(s4));
		// String s5 = "";
		// stackvo.setDisconnectedYear(s5);
		// stackvo.setFacilityStackId((String)dynaactionform.get("facilityStackId"));
		String s6 = "";
		String s7 = "";
		try {
			StackEntity.update(stackvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId() + "/stack/"
						+ stackvo1.getStackId() + "-" + s.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/" + facilityvo.getDecId()
									+ "/stack/" + stackvo1.getStackId() + "-"
									+ stackvo1.getFacilityStackId().trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			stackvo1 = StackEntity.findByPrimaryKey(stackvo.getStackId());
			setScreenFieldDetails(dynaactionform, stackvo1);
			if (stackvo1 != null)
				httpsession.setAttribute("STACK_OBJECT", stackvo1);
			refreshBuildingObject(httpservletrequest);
			s6 = "Updated Stack Successfully.";
			s7 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s6 != null && s6.trim().length() > 0 && s7 != null
				&& s7.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s6);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s7);
		}
		setScreenInfo(httpservletrequest);

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		StackVo stackvo = StackEntity.findByPrimaryKey(i);
		if (stackvo != null)
			httpsession.setAttribute("STACK_OBJECT", stackvo);
		try {
			StackEntity.delete(stackvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId() + "/stack/"
						+ stackvo.getStackId() + "-"
						+ stackvo.getFacilityStackId().trim());
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
		return actionmapping.findForward("successstack");
	}

	private void setScreenFieldDetails(DynaActionForm dynaactionform,
			StackVo stackvo) {
		dynaactionform.set("bId",
				(new StringBuffer(String.valueOf(stackvo.getBuildingId())))
						.toString());
		dynaactionform.set("floor", stackvo.getFloor());
		dynaactionform.set("height",
				(new StringBuffer(String.valueOf(stackvo.getHeight())))
						.toString());
		dynaactionform.set("diameter",
				(new StringBuffer(String.valueOf(stackvo.getDiameter())))
						.toString());
		dynaactionform.set("flowRate",
				(new StringBuffer(String.valueOf(stackvo.getFlowRate())))
						.toString());
		dynaactionform.set("exhaustTemp",
				(new StringBuffer(String.valueOf(stackvo.getExhaustTemp())))
						.toString());
		dynaactionform.set("velocity",
				(new StringBuffer(String.valueOf(stackvo.getVelocity())))
						.toString());
		dynaactionform.set("emissionPointId", stackvo.getDecEmissionPointId());
		dynaactionform.set("YearInstalled",
				(new StringBuffer(String.valueOf(stackvo.getYearInstalled())))
						.toString());
		dynaactionform.set("facilityStackId", stackvo.getFacilityStackId());
		dynaactionform.set("noofsource",
				UtilityObject.checkNullAndFill(stackvo.getNoofsource(), ""));
		dynaactionform.set("totalcapacity",
				UtilityObject.checkNullAndFill(stackvo.getTotalcapacity(), ""));
		dynaactionform.set("typeoffuel",
				UtilityObject.checkNullAndFill(stackvo.getTypeoffuel(), ""));
		dynaactionform.set("methodNineTest",
				checkNullAndFill(stackvo.isMethodNineTest(), ""));
		String sd = stackvo.getMethodNineLastDate();
		dynaactionform.set("methodNineLastDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(sd));
		String sd1 = stackvo.getMethodNineNextTestDate();
		dynaactionform.set("methodNineNextTestDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(sd1));
		dynaactionform.set("opacityLimit",
				checkNullAndFill(stackvo.isOpacityLimit(), ""));
		// dynaactionform.set("facilityStackId",
		// stackvo.getFacilityDesignatedId());
	}

	private void setScreenFieldDetailsforedit(DynaActionForm dynaactionform,
			StackVo stackvo) {
		dynaactionform.set("bId",
				(new StringBuffer(String.valueOf(stackvo.getBuildingId())))
						.toString());
		dynaactionform.set("floor", stackvo.getFloor());
		dynaactionform.set("height",
				(new StringBuffer(String.valueOf(stackvo.getHeight())))
						.toString());
		dynaactionform.set("diameter",
				(new StringBuffer(String.valueOf(stackvo.getDiameter())))
						.toString());
		dynaactionform.set("flowRate",
				(new StringBuffer(String.valueOf(stackvo.getFlowRate())))
						.toString());
		dynaactionform.set("exhaustTemp",
				(new StringBuffer(String.valueOf(stackvo.getExhaustTemp())))
						.toString());
		dynaactionform.set("velocity",
				(new StringBuffer(String.valueOf(stackvo.getVelocity())))
						.toString());
		dynaactionform.set("emissionPointId", stackvo.getDecEmissionPointId());
		dynaactionform.set("YearInstalled",
				(new StringBuffer(String.valueOf(stackvo.getYearInstalled())))
						.toString());
		dynaactionform.set("facilityStackId", stackvo.getFacilityStackId());
		dynaactionform.set("noofsource",
				UtilityObject.checkNullAndFill(stackvo.getNoofsource(), ""));
		dynaactionform.set("totalcapacity",
				UtilityObject.checkNullAndFill(stackvo.getTotalcapacity(), ""));
		dynaactionform.set("typeoffuel",
				UtilityObject.checkNullAndFill(stackvo.getTypeoffuel(), ""));
		dynaactionform.set("methodNineTest",
				checkNullAndFill(stackvo.isMethodNineTest(), ""));
		String sd = stackvo.getMethodNineLastDate();
		dynaactionform.set("methodNineLastDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(sd));
		String sd1 = stackvo.getMethodNineNextTestDate();
		dynaactionform.set("methodNineNextTestDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(sd1));
		dynaactionform.set("opacityLimit",
				checkNullAndFill(stackvo.isOpacityLimit(), ""));
		// dynaactionform.set("facilityStackId",
		// stackvo.getFacilityDesignatedId());
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

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StackAction - In Edit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		httpservletrequest.setAttribute("isItForEdit", "Y");
		StackVo stackvo = StackEntity.findByPrimaryKey(i);
		if (stackvo != null) {
			httpsession.setAttribute("STACK_OBJECT", stackvo);
			setScreenFieldDetailsforedit(dynaactionform, stackvo);
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		setScreenInfo(httpservletrequest);
		setScreenDetails(httpservletrequest, dynaactionform);

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("StackAction - In Edit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		httpservletrequest.setAttribute("isItForEdit", "N");
		StackVo stackvo = StackEntity.findByPrimaryKey(i);
		if (stackvo != null) {
			httpsession.setAttribute("STACK_OBJECT", stackvo);
			setScreenFieldDetailsforedit(dynaactionform, stackvo);
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		setScreenDetails(httpservletrequest, dynaactionform);
		return actionmapping.findForward("success");
	}

	/*
	 * public ActionForward statePermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In statePermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; HttpSession httpsession =
	 * httpservletrequest.getSession(); StackVo stackvo =
	 * (StackVo)httpsession.getAttribute("STACK_OBJECT"); String s = ""; String
	 * s1 = ""; String s2 = ""; int i = -99; i =
	 * DepartmentEnum.STATE_AGENCY.getCode(); s =
	 * (String)dynaactionform.get("staPermitNumber"); s1 =
	 * (String)dynaactionform.get("staIssueDate"); s2 =
	 * (String)dynaactionform.get("staExpirationDate"); PermitInfoVo
	 * permitinfovo = new PermitInfoVo(); permitinfovo.setDepId(i);
	 * permitinfovo.setObjectId(stackvo.getStackId());
	 * permitinfovo.setObjectType(ResourcesTypeEnum.parse("Stack").getCode());
	 * permitinfovo.setPermitNumber(s);
	 * permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
	 * permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
	 * StackEntity.addPermit(permitinfovo); dynaactionform.set("bId", (new
	 * StringBuffer(String.valueOf(stackvo.getBuildingId()))).toString());
	 * dynaactionform.set("facilityStackId", stackvo.getFacilityStackId());
	 * dynaactionform.set("YearInstalled", (new
	 * StringBuffer(String.valueOf(stackvo.getYearInstalled()))).toString());
	 * dynaactionform.set("floor", stackvo.getFloor());
	 * dynaactionform.set("height", (new
	 * StringBuffer(String.valueOf(stackvo.getHeight()))).toString());
	 * dynaactionform.set("diameter", (new
	 * StringBuffer(String.valueOf(stackvo.getDiameter()))).toString());
	 * dynaactionform.set("noofsource",
	 * UtilityObject.checkNullAndFill(stackvo.getNoofsource(),""));
	 * dynaactionform.set("totalcapacity",
	 * UtilityObject.checkNullAndFill(stackvo.getTotalcapacity(),""));
	 * dynaactionform.set("typeoffuel",
	 * UtilityObject.checkNullAndFill(stackvo.getTypeoffuel(),""));
	 * dynaactionform.set("flowRate", (new
	 * StringBuffer(String.valueOf(stackvo.getFlowRate()))).toString());
	 * dynaactionform.set("exhaustTemp", (new
	 * StringBuffer(String.valueOf(stackvo.getExhaustTemp()))).toString());
	 * dynaactionform.set("velocity", (new
	 * StringBuffer(String.valueOf(stackvo.getVelocity()))).toString());
	 * //dynaactionform.set("ModifiedInPast", (new
	 * StringBuffer(String.valueOf(stackvo.getModifiedInPast()))).toString());
	 * //dynaactionform.set("DisconnectedYear", (new
	 * StringBuffer(String.valueOf(stackvo.getDisconnectedYear()))).toString());
	 * dynaactionform.set("emissionPointId", stackvo.getDecEmissionPointId());
	 * dynaactionform.set("methodNineTest",
	 * checkNullAndFill(stackvo.isMethodNineTest(), "")); String sd =
	 * stackvo.getMethodNineLastDate(); dynaactionform.set("methodNineLastDate",
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(sd)); String sd1 =
	 * stackvo.getMethodNineNextTestDate();
	 * dynaactionform.set("methodNineNextTestDate",
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(sd1));
	 * dynaactionform.set("opacityLimit",
	 * checkNullAndFill(stackvo.isOpacityLimit(), ""));
	 * dynaactionform.set("staPermitNumber", "");
	 * dynaactionform.set("staIssueDate", "");
	 * dynaactionform.set("staExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward editStatePermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In editStatePermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo);
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * httpservletrequest.setAttribute("EDIT_STATE_PERMIT", "Y");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward updateStatePermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In updateStatePermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo); String s =
	 * httpservletrequest.getParameter("hdnPermitId"); int i = -99;
	 * if(UtilityObject.isNotEmpty(s)) i = Integer.parseInt(s); else throw new
	 * Exception
	 * ("State Permit Id is null while updating the State Permit info"); String
	 * s1 = (String)dynaactionform.get("staPermitNumber"); String s2 =
	 * (String)dynaactionform.get("staIssueDate"); String s3 =
	 * (String)dynaactionform.get("staExpirationDate"); PermitInfoVo
	 * permitinfovo = StackEntity.getPermit(i);
	 * permitinfovo.setPermitNumber(s1);
	 * permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
	 * permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
	 * StackEntity.updatePermit(permitinfovo);
	 * dynaactionform.set("staPermitNumber", "");
	 * dynaactionform.set("staIssueDate", "");
	 * dynaactionform.set("staExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward deleteStatePermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In deleteStatePermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo); String s =
	 * httpservletrequest.getParameter("hdnPermitId"); int i = -99;
	 * if(UtilityObject.isNotEmpty(s)) i = Integer.parseInt(s); else throw new
	 * Exception
	 * ("State Permit Id is null while updating the State Permit info"); String
	 * s1 = (String)dynaactionform.get("staPermitNumber"); String s2 =
	 * (String)dynaactionform.get("staIssueDate"); String s3 =
	 * (String)dynaactionform.get("staExpirationDate"); PermitInfoVo
	 * permitinfovo = StackEntity.getPermit(i);
	 * StackEntity.deletePermit(permitinfovo);
	 * dynaactionform.set("staPermitNumber", "");
	 * dynaactionform.set("staIssueDate", "");
	 * dynaactionform.set("staExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 */

	private void setScreenFields(DynaActionForm dynaactionform, StackVo stackvo) {
		dynaactionform.set("bId",
				(new StringBuffer(String.valueOf(stackvo.getBuildingId())))
						.toString());
		dynaactionform.set("facilityStackId", stackvo.getFacilityStackId());
		dynaactionform.set("YearInstalled",
				(new StringBuffer(String.valueOf(stackvo.getYearInstalled())))
						.toString());
		dynaactionform.set("floor", stackvo.getFloor());
		dynaactionform.set("height",
				(new StringBuffer(String.valueOf(stackvo.getHeight())))
						.toString());
		dynaactionform.set("diameter",
				(new StringBuffer(String.valueOf(stackvo.getDiameter())))
						.toString());
		dynaactionform.set("noofsource",
				UtilityObject.checkNullAndFill(stackvo.getNoofsource(), ""));
		dynaactionform.set("totalcapacity",
				UtilityObject.checkNullAndFill(stackvo.getTotalcapacity(), ""));
		dynaactionform.set("typeoffuel",
				UtilityObject.checkNullAndFill(stackvo.getTypeoffuel(), ""));
		dynaactionform.set("flowRate",
				(new StringBuffer(String.valueOf(stackvo.getFlowRate())))
						.toString());
		dynaactionform.set("exhaustTemp",
				(new StringBuffer(String.valueOf(stackvo.getExhaustTemp())))
						.toString());
		dynaactionform.set("velocity",
				(new StringBuffer(String.valueOf(stackvo.getVelocity())))
						.toString());
		dynaactionform.set("emissionPointId", stackvo.getDecEmissionPointId());
		dynaactionform.set("methodNineTest",
				checkNullAndFill(stackvo.isMethodNineTest(), ""));
		String sd = stackvo.getMethodNineLastDate();
		dynaactionform.set("methodNineLastDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(sd));
		String sd1 = stackvo.getMethodNineNextTestDate();
		dynaactionform.set("methodNineNextTestDate",
				UtilityObject.convertYyyyMmDd2MmDdYyyy(sd1));
		dynaactionform.set("opacityLimit",
				checkNullAndFill(stackvo.isOpacityLimit(), ""));
		// dynaactionform.set("ModifiedInPast", (new
		// StringBuffer(String.valueOf(stackvo.getModifiedInPast()))).toString());
		// dynaactionform.set("DisconnectedYear", (new
		// StringBuffer(String.valueOf(stackvo.getDisconnectedYear()))).toString());
	}

	/*
	 * public ActionForward nyDobPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In nyDobPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; HttpSession httpsession =
	 * httpservletrequest.getSession(); StackVo stackvo =
	 * (StackVo)httpsession.getAttribute("STACK_OBJECT"); String s = ""; String
	 * s1 = ""; String s2 = ""; int i = -99; i = DepartmentEnum.DOB.getCode(); s
	 * = (String)dynaactionform.get("nyDobPermitNumber"); s1 =
	 * (String)dynaactionform.get("nyDobIssueDate"); s2 =
	 * (String)dynaactionform.get("nyDobExpirationDate"); PermitInfoVo
	 * permitinfovo = new PermitInfoVo(); permitinfovo.setDepId(i);
	 * permitinfovo.setObjectId(stackvo.getStackId());
	 * permitinfovo.setObjectType(ResourcesTypeEnum.parse("Stack").getCode());
	 * permitinfovo.setPermitNumber(s);
	 * permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
	 * permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
	 * StackEntity.addPermit(permitinfovo); dynaactionform.set("bId", (new
	 * StringBuffer(String.valueOf(stackvo.getBuildingId()))).toString());
	 * dynaactionform.set("facilityStackId", stackvo.getFacilityStackId());
	 * dynaactionform.set("YearInstalled", (new
	 * StringBuffer(String.valueOf(stackvo.getYearInstalled()))).toString());
	 * dynaactionform.set("floor", stackvo.getFloor());
	 * dynaactionform.set("height", (new
	 * StringBuffer(String.valueOf(stackvo.getHeight()))).toString());
	 * dynaactionform.set("diameter", (new
	 * StringBuffer(String.valueOf(stackvo.getDiameter()))).toString());
	 * dynaactionform.set("noofsource",
	 * UtilityObject.checkNullAndFill(stackvo.getNoofsource(),""));
	 * dynaactionform.set("totalcapacity",
	 * UtilityObject.checkNullAndFill(stackvo.getTotalcapacity(),""));
	 * dynaactionform.set("typeoffuel",
	 * UtilityObject.checkNullAndFill(stackvo.getTypeoffuel(),""));
	 * dynaactionform.set("flowRate", (new
	 * StringBuffer(String.valueOf(stackvo.getFlowRate()))).toString());
	 * dynaactionform.set("exhaustTemp", (new
	 * StringBuffer(String.valueOf(stackvo.getExhaustTemp()))).toString());
	 * dynaactionform.set("velocity", (new
	 * StringBuffer(String.valueOf(stackvo.getVelocity()))).toString());
	 * dynaactionform.set("emissionPointId", stackvo.getDecEmissionPointId());
	 * dynaactionform.set("methodNineTest",
	 * checkNullAndFill(stackvo.isMethodNineTest(), "")); String sd =
	 * stackvo.getMethodNineLastDate(); dynaactionform.set("methodNineLastDate",
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(sd)); String sd1 =
	 * stackvo.getMethodNineNextTestDate();
	 * dynaactionform.set("methodNineNextTestDate",
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(sd1));
	 * dynaactionform.set("opacityLimit",
	 * checkNullAndFill(stackvo.isOpacityLimit(), ""));
	 * dynaactionform.set("nyDobPermitNumber", "");
	 * dynaactionform.set("nyDobIssueDate", "");
	 * dynaactionform.set("nyDobExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward editNyDobPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In editNyDobPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo);
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * httpservletrequest.setAttribute("EDIT_NYDOB_PERMIT", "Y");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward deleteNyDobPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In updateNyDobPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo); String s =
	 * httpservletrequest.getParameter("hdnPermitId"); int i = -99;
	 * if(UtilityObject.isNotEmpty(s)) i = Integer.parseInt(s); else throw new
	 * Exception
	 * ("NYDOB Permit Id is null while updating the NYDOB Permit info");
	 * PermitInfoVo permitinfovo = StackEntity.getPermit(i);
	 * StackEntity.deletePermit(permitinfovo);
	 * dynaactionform.set("nyDobPermitNumber", "");
	 * dynaactionform.set("nyDobIssueDate", "");
	 * dynaactionform.set("nyDobExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward updateNyDobPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In updateNyDobPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo); String s =
	 * httpservletrequest.getParameter("hdnPermitId"); int i = -99;
	 * if(UtilityObject.isNotEmpty(s)) i = Integer.parseInt(s); else throw new
	 * Exception
	 * ("NYDOB Permit Id is null while updating the NYDOB Permit info"); String
	 * s1 = (String)dynaactionform.get("nyDobPermitNumber"); String s2 =
	 * (String)dynaactionform.get("nyDobIssueDate"); String s3 =
	 * (String)dynaactionform.get("nyDobExpirationDate"); PermitInfoVo
	 * permitinfovo = StackEntity.getPermit(i);
	 * permitinfovo.setPermitNumber(s1);
	 * permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
	 * permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
	 * StackEntity.updatePermit(permitinfovo);
	 * dynaactionform.set("nyDobPermitNumber", "");
	 * dynaactionform.set("nyDobIssueDate", "");
	 * dynaactionform.set("nyDobExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 */

	/*
	 * public ActionForward commonPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In commonPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; HttpSession httpsession =
	 * httpservletrequest.getSession(); StackVo stackvo =
	 * (StackVo)httpsession.getAttribute("STACK_OBJECT"); String s = ""; String
	 * s1 = ""; String s2 = ""; int i = -99; FacilityVo facilityvo =
	 * (FacilityVo)httpsession.getAttribute("FACILITY_OBJECT"); AddressVo
	 * addressvo = facilityvo.getFacilityAddress(); String s3 =
	 * addressvo.getState(); String s4 = (String)VALID_STATES.get(s3);
	 * log.debug((new
	 * StringBuilder()).append(s3).append(" enum =").append(DepartmentEnum
	 * .parse(s4)).toString()); if(UtilityObject.isNotEmpty(s4)) {
	 * DepartmentEnum departmentenum = DepartmentEnum.parse(s4); i =
	 * departmentenum != null ? departmentenum.getCode() : -1; } s =
	 * (String)dynaactionform.get("diffStatePermitNumber"); s1 =
	 * (String)dynaactionform.get("diffStateIssueDate"); s2 =
	 * (String)dynaactionform.get("diffStateExpirationDate"); PermitInfoVo
	 * permitinfovo = new PermitInfoVo(); permitinfovo.setDepId(i);
	 * permitinfovo.setObjectId(stackvo.getStackId());
	 * permitinfovo.setObjectType(ResourcesTypeEnum.parse("Stack").getCode());
	 * permitinfovo.setPermitNumber(s);
	 * permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
	 * permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
	 * StackEntity.addPermit(permitinfovo); dynaactionform.set("bId", (new
	 * StringBuffer(String.valueOf(stackvo.getBuildingId()))).toString());
	 * dynaactionform.set("facilityStackId", stackvo.getFacilityStackId());
	 * dynaactionform.set("YearInstalled", (new
	 * StringBuffer(String.valueOf(stackvo.getYearInstalled()))).toString());
	 * dynaactionform.set("floor", stackvo.getFloor());
	 * dynaactionform.set("height", (new
	 * StringBuffer(String.valueOf(stackvo.getHeight()))).toString());
	 * dynaactionform.set("diameter", (new
	 * StringBuffer(String.valueOf(stackvo.getDiameter()))).toString());
	 * dynaactionform.set("noofsource",
	 * UtilityObject.checkNullAndFill(stackvo.getNoofsource(),""));
	 * dynaactionform.set("totalcapacity",
	 * UtilityObject.checkNullAndFill(stackvo.getTotalcapacity(),""));
	 * dynaactionform.set("typeoffuel",
	 * UtilityObject.checkNullAndFill(stackvo.getTypeoffuel(),""));
	 * dynaactionform.set("flowRate", (new
	 * StringBuffer(String.valueOf(stackvo.getFlowRate()))).toString());
	 * dynaactionform.set("exhaustTemp", (new
	 * StringBuffer(String.valueOf(stackvo.getExhaustTemp()))).toString());
	 * dynaactionform.set("velocity", (new
	 * StringBuffer(String.valueOf(stackvo.getVelocity()))).toString());
	 * dynaactionform.set("emissionPointId", stackvo.getDecEmissionPointId());
	 * dynaactionform.set("methodNineTest",
	 * checkNullAndFill(stackvo.isMethodNineTest(), "")); String sd =
	 * stackvo.getMethodNineLastDate(); dynaactionform.set("methodNineLastDate",
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(sd)); String sd1 =
	 * stackvo.getMethodNineNextTestDate();
	 * dynaactionform.set("methodNineNextTestDate",
	 * UtilityObject.convertYyyyMmDd2MmDdYyyy(sd1));
	 * dynaactionform.set("opacityLimit",
	 * checkNullAndFill(stackvo.isOpacityLimit(), ""));
	 * dynaactionform.set("diffStatePermitNumber", "");
	 * dynaactionform.set("diffStateIssueDate", "");
	 * dynaactionform.set("diffStateExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward editCommonPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In editCommonPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo);
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * httpservletrequest.setAttribute("EDIT_COMMON_PERMIT", "Y");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward updateCommonPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * log.debug("StackAction - In updateCommonPermitInfo"); DynaActionForm
	 * dynaactionform = (DynaActionForm)actionform; StackVo stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo); String s =
	 * httpservletrequest.getParameter("hdnPermitId"); int i = -99;
	 * if(UtilityObject.isNotEmpty(s)) i = Integer.parseInt(s); else throw new
	 * Exception
	 * ("Common Permit Id is null while updating the Common Permit info");
	 * String s1 = (String)dynaactionform.get("diffStatePermitNumber"); String
	 * s2 = (String)dynaactionform.get("diffStateIssueDate"); String s3 =
	 * (String)dynaactionform.get("diffStateExpirationDate"); PermitInfoVo
	 * permitinfovo = StackEntity.getPermit(i);
	 * permitinfovo.setPermitNumber(s1);
	 * permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
	 * permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
	 * StackEntity.updatePermit(permitinfovo);
	 * dynaactionform.set("diffStatePermitNumber", "");
	 * dynaactionform.set("diffStateIssueDate", "");
	 * dynaactionform.set("diffStateExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 * 
	 * public ActionForward deleteCommonPermitInfo(ActionMapping actionmapping,
	 * ActionForm actionform, HttpServletRequest httpservletrequest,
	 * HttpServletResponse httpservletresponse) throws Exception {
	 * DynaActionForm dynaactionform = (DynaActionForm)actionform; StackVo
	 * stackvo =
	 * (StackVo)httpservletrequest.getSession().getAttribute("STACK_OBJECT");
	 * setScreenFields(dynaactionform, stackvo); String s =
	 * httpservletrequest.getParameter("hdnPermitId"); int i = -99;
	 * if(UtilityObject.isNotEmpty(s)) i = Integer.parseInt(s); else throw new
	 * Exception
	 * ("Common Permit Id is null while updating the Common Permit info");
	 * String s1 = (String)dynaactionform.get("diffStatePermitNumber");
	 * PermitInfoVo permitinfovo = StackEntity.getPermit(i);
	 * StackEntity.deletePermit(permitinfovo);
	 * dynaactionform.set("diffStatePermitNumber", "");
	 * dynaactionform.set("diffStateIssueDate", "");
	 * dynaactionform.set("diffStateExpirationDate", "");
	 * httpservletrequest.setAttribute("isComponentEditable", "N");
	 * refreshPermitInfo(httpservletrequest); setScreenInfo(httpservletrequest);
	 * return actionmapping.findForward("success"); }
	 */

	private void setScreenDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform) {
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		AddressVo addressvo = facilityvo.getFacilityAddress();
		String s = addressvo.getState();
		if (UtilityObject.isNotEmpty(s)) {
			if (s.equalsIgnoreCase("NY"))
				httpservletrequest.setAttribute("isFacilityInNy", "Y");
			if (VALID_STATES.containsKey(s)) {
				httpservletrequest.setAttribute("isValidState", "Y");
				httpservletrequest.setAttribute("validStateLabel",
						VALID_STATES.get(s));
			}
		}
		// refreshPermitInfo(httpservletrequest);
	}

	/*
	 * private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
	 * HttpSession httpsession = httpservletrequest.getSession(); StackVo
	 * stackvo = (StackVo)httpsession.getAttribute("STACK_OBJECT"); if(stackvo
	 * == null) { log.debug("Stack Object is null"); return; }
	 * stackvo.setPermitInfo(null); List list = stackvo.getPermitInfo();
	 * ArrayList arraylist = new ArrayList(); ArrayList arraylist1 = new
	 * ArrayList(); ArrayList arraylist2 = new ArrayList(); int i = list != null
	 * ? list.size() : -1; byte byte0 = -1; for(int k = 0; k < i; k++) {
	 * PermitInfoVo permitinfovo = (PermitInfoVo)list.get(k); int j =
	 * permitinfovo.getDepId(); if(j == DepartmentEnum.STATE_AGENCY.getCode()) {
	 * arraylist.add(permitinfovo); continue; } if(j ==
	 * DepartmentEnum.DOB.getCode()) arraylist1.add(permitinfovo); else
	 * arraylist2.add(permitinfovo); }
	 * 
	 * log.debug((new
	 * StringBuilder()).append("STATE_AGENCY_LST size=").append(arraylist
	 * .size())
	 * .append(", NYDOB_PERMIT_LST size=").append(arraylist1.size()).append
	 * (", COMMON_PERMIT_LST size=").append(arraylist2.size()).toString());
	 * httpsession.setAttribute("STATE_AGENCY_LST", arraylist);
	 * httpsession.setAttribute("NYDOB_PERMIT_LST", arraylist1);
	 * httpsession.setAttribute("COMMON_PERMIT_LST", arraylist2); }
	 */

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

	private void setScreenInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		StackVo stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		if (stackvo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		StackVo stackvo = (StackVo) httpsession.getAttribute("STACK_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/stack/"
					+ stackvo.getStackId() + "-"
					+ stackvo.getFacilityStackId().trim());
			if (f.exists() == false) {
				f.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		ArrayList folderlist = new ArrayList();
		try {
			String contextpath = httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId() + "/stack/"
					+ stackvo.getStackId() + "-"
					+ stackvo.getFacilityStackId().trim();
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
				+ "/stack/" + stackvo.getStackId() + "-"
				+ stackvo.getFacilityStackId().trim());

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
			.getLog(com.eespc.tracking.actions.StackAction.class);
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