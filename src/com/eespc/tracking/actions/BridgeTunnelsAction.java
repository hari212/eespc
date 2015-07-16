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
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.DynaValidatorForm;

import com.eespc.tracking.bo.BridgeTunnelPermitVo;
import com.eespc.tracking.bo.BridgeTunnelsVo;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.BridgeTunnelsEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class BridgeTunnelsAction extends LookupDispatchAction {

	public BridgeTunnelsAction() {
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

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown);
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		setDropDown(httpservletrequest);
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		String s = (String) DynaValidatorForm.get("methodToCall");
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
		if (s != null && s.equalsIgnoreCase("dobPermitInfo"))
			return dobPermitInfo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("dotPermitInfo"))
			return dotPermitInfo(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("EDIT"))
			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editPermitInfo"))
			return editDotPermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deletePermitInfo"))
			return deleteDotPermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("deleteDobPermit"))
			return deleteDobPermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateDotPermitInfo"))
			return updateDotPermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("editDobPermit"))
			return editDobPermit(actionmapping, actionform, httpservletrequest,
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

		if (s != null && s.equalsIgnoreCase("updateDob")) {
			return updateDob(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		} else {
			HttpSession httpsession = httpservletrequest.getSession();
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOT_PERMIT");
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOB_PERMIT");
			httpsession.removeAttribute("BRIDGE_TUNNEL_OBJECT");
			log.debug("BridgeTunnelsAction - In Execute");
			setScreenInfo(httpservletrequest);
			return actionmapping.findForward("success");
		}
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		// setFormVariable(DynaValidatorForm, bridgetunnelsvo,
		// httpservletrequest);

		// refreshShowInfo(httpservletrequest, bridgetunnelsvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) DynaValidatorForm.get("pathname");
		String foldername = (String) DynaValidatorForm.get("foldername");

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
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bridgetunnelsvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) DynaValidatorForm.get("pathname");
		if (httpsession.getAttribute("DELETE_PERMISSION") == null) {
			httpservletrequest.setAttribute("MESSAGE", "No Permission");
			httpservletrequest.setAttribute("MESSAGE_TYPE", "Error");
			setformvariable(path, actionmapping, actionform,
					httpservletrequest, httpservletresponse);
			return actionmapping.findForward("success");
		}
		String foldername = (String) DynaValidatorForm.get("foldername");
		String refoldername = (String) DynaValidatorForm
				.get("renamefoldername");
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

		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bridgetunnelsvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) DynaValidatorForm.get("pathname");
		String foldername = (String) DynaValidatorForm.get("foldername");
		setformvariable((path + "/" + foldername), actionmapping, actionform,
				httpservletrequest, httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward deletefile(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bridgetunnelsvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) DynaValidatorForm.get("pathname");
		String foldername = (String) DynaValidatorForm.get("foldername");

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
		// DynaValidatorForm DynaValidatorForm = (DynaValidatorForm)actionform;

		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, bridgetunnelsvo);
		// refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) DynaValidatorForm.get("pathname");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		// httpservletrequest.getRealPath("/")+"/clientfolder/"+facilityvo.getDecId()
		try {

			FormFile uploadFile = (FormFile) DynaValidatorForm.get("filename");
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

		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) DynaValidatorForm.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/bridgetunnels/"
				+ bridgetunnelsvo.getId() + "-"
				+ bridgetunnelsvo.getFacilityDesignatedId().trim())) {
			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		System.out.println("back:" + back);
		String foldername = (String) DynaValidatorForm.get("foldername");
		setformvariable(back, actionmapping, actionform, httpservletrequest,
				httpservletresponse);

		return actionmapping.findForward("success");

	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		String s = "";
		String s3 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		BridgeTunnelsVo bridgetunnelsvo = new BridgeTunnelsVo();
		bridgetunnelsvo.setBuildingId(buildingvo.getId());
		bridgetunnelsvo.setFacilityDesignatedId((String) DynaValidatorForm
				.get("BT_facilityDesignatedId"));
		bridgetunnelsvo.setActionTaken((String) DynaValidatorForm
				.get("BT_actiontaken"));
		String s6 = (String) DynaValidatorForm.get("YearInstalled");
		bridgetunnelsvo.setYearInstalled(s6);
		String s7 = (String) DynaValidatorForm.get("ModifiedInPast");
		String s8 = (String) DynaValidatorForm.get("DisconnectedYear");
		bridgetunnelsvo.setDisconnectedYear(s8);
		bridgetunnelsvo.setModifiedInPast(Integer.parseInt(s7));
		bridgetunnelsvo.setBcomments((String) DynaValidatorForm
				.get("B_comments"));
		bridgetunnelsvo.setIssueDate((String) DynaValidatorForm
				.get("dobIssueDate"));
		bridgetunnelsvo.setPermitNumber((String) DynaValidatorForm
				.get("dobPermitNumber"));
		bridgetunnelsvo.setDobsignoff((String) DynaValidatorForm
				.get("dobsignoff"));
		String s9 = (String) DynaValidatorForm.get("type");
		if (s9 != null && s9.equalsIgnoreCase("1"))
			bridgetunnelsvo.setBridge(true);
		else
			bridgetunnelsvo.setBridge(false);
		try {
			int i = BridgeTunnelsEntity.add(bridgetunnelsvo);
			BridgeTunnelsVo bridgetunnelsvo1 = BridgeTunnelsEntity
					.findByPrimaryKey(i);
			if (bridgetunnelsvo1 != null) {
				log.debug("Going to set in session");
				httpsession.setAttribute("BRIDGE_TUNNEL_OBJECT",
						bridgetunnelsvo1);
				setFieldDetails(httpservletrequest, DynaValidatorForm,
						bridgetunnelsvo1);
			} else {
				log.debug("OOPS! WHY >>>>>>>");
			}
			httpservletrequest.setAttribute("isComponentEditable", "N");
			String s1 = "Added Bridge/Tunnel.";
			String s4 = "CONFIRMATION";
		} catch (TrackingException trackingexception) {
			String s2 = trackingexception.getMessage();
			String s5 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		log.debug("BridgeTunnelsAction - In Add");
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = "";
		String s3 = "";
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		String facilityDesignatedid = bridgetunnelsvo.getFacilityDesignatedId();
		bridgetunnelsvo.setFacilityDesignatedId((String) DynaValidatorForm
				.get("BT_facilityDesignatedId"));
		bridgetunnelsvo.setActionTaken((String) DynaValidatorForm
				.get("BT_actiontaken"));
		String s6 = (String) DynaValidatorForm.get("YearInstalled");
		String s7 = (String) DynaValidatorForm.get("DisconnectedYear");
		String s8 = (String) DynaValidatorForm.get("ModifiedInPast");
		bridgetunnelsvo.setDisconnectedYear(s7);
		bridgetunnelsvo.setYearInstalled(s6);
		bridgetunnelsvo.setBcomments((String) DynaValidatorForm
				.get("B_comments"));
		bridgetunnelsvo.setIssueDate((String) DynaValidatorForm
				.get("dobIssueDate"));
		bridgetunnelsvo.setPermitNumber((String) DynaValidatorForm
				.get("dobPermitNumber"));
		bridgetunnelsvo.setDobsignoff((String) DynaValidatorForm
				.get("dobsignoff"));
		bridgetunnelsvo.setModifiedInPast(Integer.parseInt(s8));
		try {
			BridgeTunnelsEntity.update(bridgetunnelsvo);
			bridgetunnelsvo = BridgeTunnelsEntity
					.findByPrimaryKey(bridgetunnelsvo.getId());
			if (bridgetunnelsvo != null) {

				FacilityVo facilityvo = (FacilityVo) httpsession
						.getAttribute("FACILITY_OBJECT");
				try {

					File f = new File(httpservletrequest.getRealPath("/")
							+ "/clientfolder/" + facilityvo.getDecId()
							+ "/bridgetunnels/" + bridgetunnelsvo.getId() + "-"
							+ facilityDesignatedid.trim());

					if (f.isDirectory()) {

						File newFileName = new File(
								httpservletrequest.getRealPath("/")
										+ "/clientfolder/"
										+ facilityvo.getDecId()
										+ "/bridgetunnels/"
										+ bridgetunnelsvo.getId()
										+ "-"
										+ bridgetunnelsvo
												.getFacilityDesignatedId()
												.trim());
						f.renameTo(newFileName);

					} else {

					}
				} catch (Exception e) {
					System.out.println("Exception: " + e);
				}

				httpsession.setAttribute("BRIDGE_TUNNEL_OBJECT",
						bridgetunnelsvo);
				setFieldDetails(httpservletrequest, DynaValidatorForm,
						bridgetunnelsvo);
			}
			String s1 = "Updated Bridge/Tunnel.";
			String s4 = "CONFIRMATION";
		} catch (TrackingException trackingexception) {
			String s2 = trackingexception.getMessage();
			String s5 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		log.debug("BridgeTunnelsAction - In update");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		log.debug("BridgeTunnelsAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) DynaValidatorForm.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BridgeTunnelsVo bridgetunnelsvo = BridgeTunnelsEntity
				.findByPrimaryKey(i);
		if (bridgetunnelsvo != null)
			httpsession.setAttribute("BRIDGE_TUNNEL_OBJECT", bridgetunnelsvo);
		BridgeTunnelsVo bridgetunnelsvo1 = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		try {
			BridgeTunnelsEntity.delete(bridgetunnelsvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/bridgetunnels/" + bridgetunnelsvo1.getId() + "-"
						+ bridgetunnelsvo1.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successbridge");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		log.debug("BridgeTunnelsAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) DynaValidatorForm.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BridgeTunnelsVo bridgetunnelsvo = BridgeTunnelsEntity
				.findByPrimaryKey(i);
		if (bridgetunnelsvo != null) {
			httpsession.setAttribute("BRIDGE_TUNNEL_OBJECT", bridgetunnelsvo);
			setFieldDetails(httpservletrequest, DynaValidatorForm,
					bridgetunnelsvo);
		}
		setScreenInfo(httpservletrequest);
		log.debug("BridgeTunnelsAction - In view");
		System.out.println("BridgeTunnelsAction - In view");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.removeAttribute("BRIDGE_TUNNEL_OBJECT");
		String s = (String) DynaValidatorForm.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BridgeTunnelsVo bridgetunnelsvo = BridgeTunnelsEntity
				.findByPrimaryKey(i);
		if (bridgetunnelsvo != null) {
			httpsession.setAttribute("BRIDGE_TUNNEL_OBJECT", bridgetunnelsvo);
			setFieldDetailsforedit(httpservletrequest, DynaValidatorForm,
					bridgetunnelsvo);
		}
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("showPermit", "Y");
		log.debug("BridgeTunnelsAction - In edit");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		httpsession.removeAttribute("BRIDGE_TUNNEL_OBJECT");
		String s = (String) DynaValidatorForm.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		BridgeTunnelsVo bridgetunnelsvo = BridgeTunnelsEntity
				.findByPrimaryKey(i);
		if (bridgetunnelsvo != null) {
			httpsession.setAttribute("BRIDGE_TUNNEL_OBJECT", bridgetunnelsvo);
			setFieldDetailsforsearch(httpservletrequest, DynaValidatorForm,
					bridgetunnelsvo);
		}
		setScreenInfoforexist(httpservletrequest);
		log.debug("BridgeTunnelsAction - In edit");
		return actionmapping.findForward("success");
	}

	public ActionForward dobPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In dobPermitInfo");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		BridgeTunnelPermitVo bridgetunnelpermitvo = new BridgeTunnelPermitVo();
		bridgetunnelpermitvo.setBridgeTunnelId(bridgetunnelsvo.getId());
		bridgetunnelpermitvo.setDepId(DepartmentEnum.DOB.getCode());
		bridgetunnelpermitvo.setExpirationDate((String) DynaValidatorForm
				.get("dobExpDate"));
		bridgetunnelpermitvo.setIssueDate((String) DynaValidatorForm
				.get("dobIssueDate"));
		bridgetunnelpermitvo.setPermitNumber((String) DynaValidatorForm
				.get("dobPermitNumber"));
		bridgetunnelpermitvo.setYear((String) DynaValidatorForm.get("dobYear"));
		int i = BridgeTunnelsEntity.addPermitdob(bridgetunnelpermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward dotPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In dobPermitInfo");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		BridgeTunnelPermitVo bridgetunnelpermitvo = new BridgeTunnelPermitVo();
		bridgetunnelpermitvo.setBridgeTunnelId(bridgetunnelsvo.getId());
		bridgetunnelpermitvo.setDepId(DepartmentEnum.DOT.getCode());
		bridgetunnelpermitvo.setByWho((String) DynaValidatorForm
				.get("dotByWho"));
		bridgetunnelpermitvo.setExpirationDate((String) DynaValidatorForm
				.get("dotExpDate"));
		bridgetunnelpermitvo.setFillingDate((String) DynaValidatorForm
				.get("dotFillingDate"));
		bridgetunnelpermitvo.setInsExpDate((String) DynaValidatorForm
				.get("dotInsExpDate"));
		bridgetunnelpermitvo.setInsIssueDate((String) DynaValidatorForm
				.get("dotInsIssueDate"));
		bridgetunnelpermitvo.setIssueDate((String) DynaValidatorForm
				.get("dotIssueDate"));
		bridgetunnelpermitvo.setLastInspDate((String) DynaValidatorForm
				.get("dotLastInspDate"));
		bridgetunnelpermitvo.setNextInspDate((String) DynaValidatorForm
				.get("dotNextInspDate"));
		bridgetunnelpermitvo.setPermitNumber((String) DynaValidatorForm
				.get("dotPermitNumber"));
		bridgetunnelpermitvo.setFileNo((String) DynaValidatorForm
				.get("dotFileNo"));
		bridgetunnelpermitvo.setYear((String) DynaValidatorForm.get("dotYear"));
		bridgetunnelpermitvo.setDotExpDateNote((String) DynaValidatorForm
				.get("dotExpDateNote"));
		bridgetunnelpermitvo.setDotNextInspDateNote((String) DynaValidatorForm
				.get("dotNextInspDateNote"));
		bridgetunnelpermitvo.setDotInsExpDateNote((String) DynaValidatorForm
				.get("dotInsExpDateNote"));
		int i = BridgeTunnelsEntity.addPermit(bridgetunnelpermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editDotPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In editDotPermitInfo");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOT_PERMIT", "Y");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		// System.out.println("ALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL"+httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");
	}

	public ActionForward deleteDotPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In editDotPermitInfo");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		BridgeTunnelPermitVo bridgetunnelpermitvo = new BridgeTunnelPermitVo();
		bridgetunnelpermitvo.setBridgeTunnelId(bridgetunnelsvo.getId());
		String s = httpservletrequest.getParameter("hdnPermitId");
		if (UtilityObject.isNotEmpty(s))
			bridgetunnelpermitvo.setId(Integer.parseInt(s));
		else
			throw new Exception(
					"Permit Id is null for updating the permit info");
		BridgeTunnelsEntity.deletePermit(bridgetunnelpermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOT_PERMIT", "N");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deleteDobPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In editDotPermitInfo");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		BridgeTunnelPermitVo bridgetunnelpermitvo = new BridgeTunnelPermitVo();
		bridgetunnelpermitvo.setBridgeTunnelId(bridgetunnelsvo.getId());
		String s = httpservletrequest.getParameter("hdnPermitId");
		if (UtilityObject.isNotEmpty(s))
			bridgetunnelpermitvo.setId(Integer.parseInt(s));
		else
			throw new Exception(
					"Permit Id is null for updating the permit info");
		BridgeTunnelsEntity.deletePermit(bridgetunnelpermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOT_PERMIT", "N");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward updateDotPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In editDotPermitInfo");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		BridgeTunnelPermitVo bridgetunnelpermitvo = new BridgeTunnelPermitVo();
		bridgetunnelpermitvo.setBridgeTunnelId(bridgetunnelsvo.getId());
		bridgetunnelpermitvo.setDepId(DepartmentEnum.DOT.getCode());
		bridgetunnelpermitvo.setByWho((String) DynaValidatorForm
				.get("dotByWho"));
		bridgetunnelpermitvo.setExpirationDate((String) DynaValidatorForm
				.get("dotExpDate"));
		bridgetunnelpermitvo.setFillingDate((String) DynaValidatorForm
				.get("dotFillingDate"));
		bridgetunnelpermitvo.setInsExpDate((String) DynaValidatorForm
				.get("dotInsExpDate"));
		bridgetunnelpermitvo.setInsIssueDate((String) DynaValidatorForm
				.get("dotInsIssueDate"));
		bridgetunnelpermitvo.setIssueDate((String) DynaValidatorForm
				.get("dotIssueDate"));
		bridgetunnelpermitvo.setLastInspDate((String) DynaValidatorForm
				.get("dotLastInspDate"));
		bridgetunnelpermitvo.setNextInspDate((String) DynaValidatorForm
				.get("dotNextInspDate"));
		bridgetunnelpermitvo.setFileNo((String) DynaValidatorForm
				.get("dotFileNo"));
		bridgetunnelpermitvo.setPermitNumber((String) DynaValidatorForm
				.get("dotPermitNumber"));
		bridgetunnelpermitvo.setYear((String) DynaValidatorForm.get("dotYear"));
		bridgetunnelpermitvo.setDotExpDateNote((String) DynaValidatorForm
				.get("dotExpDateNote"));
		bridgetunnelpermitvo.setDotNextInspDateNote((String) DynaValidatorForm
				.get("dotNextInspDateNote"));
		bridgetunnelpermitvo.setDotInsExpDateNote((String) DynaValidatorForm
				.get("dotInsExpDateNote"));
		String s = httpservletrequest.getParameter("hdnPermitId");
		if (UtilityObject.isNotEmpty(s))
			bridgetunnelpermitvo.setId(Integer.parseInt(s));
		else
			throw new Exception(
					"Permit Id is null for updating the permit info");
		BridgeTunnelsEntity.updatePermit(bridgetunnelpermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOT_PERMIT", "N");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editDobPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In editDobPermit");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOB_PERMIT", "Y");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward updateDob(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("BridgeTunnelsAction - In updateDob");
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		BridgeTunnelPermitVo bridgetunnelpermitvo = new BridgeTunnelPermitVo();
		bridgetunnelpermitvo.setBridgeTunnelId(bridgetunnelsvo.getId());
		bridgetunnelpermitvo.setDepId(DepartmentEnum.DOB.getCode());
		bridgetunnelpermitvo.setExpirationDate((String) DynaValidatorForm
				.get("dobExpDate"));
		bridgetunnelpermitvo.setIssueDate((String) DynaValidatorForm
				.get("dobIssueDate"));
		bridgetunnelpermitvo.setPermitNumber((String) DynaValidatorForm
				.get("dobPermitNumber"));
		bridgetunnelpermitvo.setYear((String) DynaValidatorForm.get("dobYear"));
		String s = httpservletrequest.getParameter("hdnPermitId");
		if (UtilityObject.isNotEmpty(s))
			bridgetunnelpermitvo.setId(Integer.parseInt(s));
		else
			throw new Exception(
					"Permit Id is null while updating DOT permit info");
		BridgeTunnelsEntity.updatePermitDob(bridgetunnelpermitvo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DOB_PERMIT", "N");
		setFieldDetails(httpservletrequest, DynaValidatorForm, bridgetunnelsvo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		String s = httpservletrequest.getParameter("type");
		log.debug((new StringBuilder()).append("tempStr=").append(s).toString());
		if (bridgetunnelsvo != null) {
			httpservletrequest.setAttribute("showPermit", "Y");
			log.debug("showPermit =Y");
			if (bridgetunnelsvo.isBridge())
				httpservletrequest
						.setAttribute("BRIDGE_TUNNEL_LABEL", "Bridge");
			else
				httpservletrequest
						.setAttribute("BRIDGE_TUNNEL_LABEL", "Tunnel");
		} else {
			httpservletrequest.setAttribute("showPermit", "N");
			log.debug("showPermit =N");
			if (s != null)
				if (s.equalsIgnoreCase("1"))
					httpservletrequest.setAttribute("BRIDGE_TUNNEL_LABEL",
							"Bridge");
				else if (s.equalsIgnoreCase("2"))
					httpservletrequest.setAttribute("BRIDGE_TUNNEL_LABEL",
							"Tunnel");
		}
		com.eespc.tracking.bo.DropDown dropdown = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEARS", dropdown);
	}

	private void setScreenInfoforexist(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		String s = httpservletrequest.getParameter("type");
		log.debug((new StringBuilder()).append("tempStr=").append(s).toString());
		if (bridgetunnelsvo != null) {
			log.debug("showPermit =Y");
			if (bridgetunnelsvo.isBridge())
				httpservletrequest
						.setAttribute("BRIDGE_TUNNEL_LABEL", "Bridge");
			else
				httpservletrequest
						.setAttribute("BRIDGE_TUNNEL_LABEL", "Tunnel");
		} else {
			httpservletrequest.setAttribute("showPermit", "N");
			log.debug("showPermit =N");
			if (s != null)
				if (s.equalsIgnoreCase("1"))
					httpservletrequest.setAttribute("BRIDGE_TUNNEL_LABEL",
							"Bridge");
				else if (s.equalsIgnoreCase("2"))
					httpservletrequest.setAttribute("BRIDGE_TUNNEL_LABEL",
							"Tunnel");
		}
		com.eespc.tracking.bo.DropDown dropdown = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEARS", dropdown);
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaValidatorForm DynaValidatorForm, BridgeTunnelsVo bridgetunnelsvo) {
		HttpSession httpsession = httpservletrequest.getSession();
		if (bridgetunnelsvo == null) {
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOT_PERMIT");
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOB_PERMIT");
			return;
		}
		DynaValidatorForm.set("BT_facilityDesignatedId",
				bridgetunnelsvo.getFacilityDesignatedId());
		DynaValidatorForm.set("BT_actiontaken",
				bridgetunnelsvo.getActionTaken());
		DynaValidatorForm.set("YearInstalled",
				bridgetunnelsvo.getYearInstalled());
		DynaValidatorForm.set("DisconnectedYear",
				bridgetunnelsvo.getDisconnectedYear());
		DynaValidatorForm.set("B_comments", bridgetunnelsvo.getBcomments());
		DynaValidatorForm.set("dobPermitNumber",
				bridgetunnelsvo.getPermitNumber());
		DynaValidatorForm.set("dobIssueDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bridgetunnelsvo.getIssueDate()));
		DynaValidatorForm.set("dobsignoff", bridgetunnelsvo.getDobsignoff());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(bridgetunnelsvo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			DynaValidatorForm.set("ModifiedInPast",
					tankoperatingstatusenum.getName());
		if (bridgetunnelsvo.isBridge())
			DynaValidatorForm.set("type", "1");
		else
			DynaValidatorForm.set("type", "2");
		bridgetunnelsvo.setPermitInfo(null);
		List list = bridgetunnelsvo.getPermitInfo();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int i = list.size();
		if (list != null && i > 0) {
			for (int j = 0; j < i; j++) {
				BridgeTunnelPermitVo bridgetunnelpermitvo = (BridgeTunnelPermitVo) list
						.get(j);
				int k = bridgetunnelpermitvo.getDepId();
				if (k == DepartmentEnum.DOB.getCode()) {
					arraylist.add(bridgetunnelpermitvo);
					continue;
				}
				if (k == DepartmentEnum.DOT.getCode())
					arraylist1.add(bridgetunnelpermitvo);
			}

		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		httpsession.setAttribute("BRIDGE_TUNNEL_DOT_PERMIT", arraylist1);
		httpsession.setAttribute("BRIDGE_TUNNEL_DOB_PERMIT", arraylist);
	}

	private void setFieldDetailsforedit(HttpServletRequest httpservletrequest,
			DynaValidatorForm DynaValidatorForm, BridgeTunnelsVo bridgetunnelsvo) {
		HttpSession httpsession = httpservletrequest.getSession();
		if (bridgetunnelsvo == null) {
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOT_PERMIT");
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOB_PERMIT");
			return;
		}
		DynaValidatorForm.set("BT_facilityDesignatedId",
				bridgetunnelsvo.getFacilityDesignatedId());
		DynaValidatorForm.set("BT_actiontaken",
				bridgetunnelsvo.getActionTaken());
		DynaValidatorForm.set("DisconnectedYear",
				bridgetunnelsvo.getDisconnectedYear());
		DynaValidatorForm.set("YearInstalled",
				bridgetunnelsvo.getYearInstalled());
		DynaValidatorForm.set("B_comments", bridgetunnelsvo.getBcomments());

		DynaValidatorForm.set("dobPermitNumber",
				bridgetunnelsvo.getPermitNumber());
		DynaValidatorForm.set("dobIssueDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bridgetunnelsvo.getIssueDate()));
		DynaValidatorForm.set("dobsignoff", bridgetunnelsvo.getDobsignoff());
		DynaValidatorForm.set(
				"ModifiedInPast",
				(new StringBuilder())
						.append(bridgetunnelsvo.getModifiedInPast()).append("")
						.toString());
		if (bridgetunnelsvo.isBridge())
			DynaValidatorForm.set("type", "1");
		else
			DynaValidatorForm.set("type", "2");
		bridgetunnelsvo.setPermitInfo(null);
		List list = bridgetunnelsvo.getPermitInfo();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int i = list.size();
		if (list != null && i > 0) {
			for (int j = 0; j < i; j++) {
				BridgeTunnelPermitVo bridgetunnelpermitvo = (BridgeTunnelPermitVo) list
						.get(j);
				int k = bridgetunnelpermitvo.getDepId();
				if (k == DepartmentEnum.DOB.getCode()) {
					arraylist.add(bridgetunnelpermitvo);
					continue;
				}
				if (k == DepartmentEnum.DOT.getCode())
					arraylist1.add(bridgetunnelpermitvo);
			}

		}
		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}
		httpsession.setAttribute("BRIDGE_TUNNEL_DOT_PERMIT", arraylist1);
		httpsession.setAttribute("BRIDGE_TUNNEL_DOB_PERMIT", arraylist);
	}

	private void setFieldDetailsforsearch(
			HttpServletRequest httpservletrequest,
			DynaValidatorForm DynaValidatorForm, BridgeTunnelsVo bridgetunnelsvo) {
		HttpSession httpsession = httpservletrequest.getSession();
		if (bridgetunnelsvo == null) {
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOT_PERMIT");
			httpsession.removeAttribute("BRIDGE_TUNNEL_DOB_PERMIT");
			return;
		}
		DynaValidatorForm.set("BT_facilityDesignatedId",
				bridgetunnelsvo.getFacilityDesignatedId());
		DynaValidatorForm.set("BT_actiontaken",
				bridgetunnelsvo.getActionTaken());
		DynaValidatorForm.set("DisconnectedYear",
				bridgetunnelsvo.getDisconnectedYear());
		DynaValidatorForm.set("YearInstalled",
				bridgetunnelsvo.getYearInstalled());
		DynaValidatorForm.set("B_comments", bridgetunnelsvo.getBcomments());
		DynaValidatorForm.set("dobPermitNumber",
				bridgetunnelsvo.getPermitNumber());
		DynaValidatorForm.set("dobsignoff", bridgetunnelsvo.getDobsignoff());
		DynaValidatorForm.set("dobIssueDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(bridgetunnelsvo.getIssueDate()));
		DynaValidatorForm.set(
				"ModifiedInPast",
				(new StringBuilder())
						.append(bridgetunnelsvo.getModifiedInPast()).append("")
						.toString());
		if (bridgetunnelsvo.isBridge())
			DynaValidatorForm.set("type", "1");
		else
			DynaValidatorForm.set("type", "2");
		bridgetunnelsvo.setPermitInfo(null);
		List list = bridgetunnelsvo.getPermitInfo();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int i = list.size();
		if (list != null && i > 0) {
			for (int j = 0; j < i; j++) {
				BridgeTunnelPermitVo bridgetunnelpermitvo = (BridgeTunnelPermitVo) list
						.get(j);
				int k = bridgetunnelpermitvo.getDepId();
				if (k == DepartmentEnum.DOB.getCode()) {
					arraylist.add(bridgetunnelpermitvo);
					continue;
				}
				if (k == DepartmentEnum.DOT.getCode())
					arraylist1.add(bridgetunnelpermitvo);
			}

		}
		httpsession.setAttribute("BRIDGE_TUNNEL_DOT_PERMIT", arraylist1);
		httpsession.setAttribute("BRIDGE_TUNNEL_DOB_PERMIT", arraylist);
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaValidatorForm DynaValidatorForm = (DynaValidatorForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BridgeTunnelsVo bridgetunnelsvo = (BridgeTunnelsVo) httpsession
				.getAttribute("BRIDGE_TUNNEL_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		// System.out.println("Facility Id: "+facilityvo.getDecId());
		// System.out.println("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest.getRealPath("/"));
		// String s = (String)DynaValidatorForm.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/bridgetunnels/" + bridgetunnelsvo.getId() + "-"
					+ bridgetunnelsvo.getFacilityDesignatedId().trim());
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
					+ "/bridgetunnels/" + bridgetunnelsvo.getId() + "-"
					+ bridgetunnelsvo.getFacilityDesignatedId().trim();
			File folder = new File(contextpath);
			File[] listOfFiles = folder.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				// System.out.println("1");
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
				// System.out.println("2");
			}
		} catch (Exception e) {
			System.out.println("Find List Exception: " + e);
		}
		httpservletrequest.setAttribute("FILE_LIST", folderlist);
		httpservletrequest.setAttribute("FILE_PATH", facilityvo.getDecId()
				+ "/bridgetunnels/" + bridgetunnelsvo.getId() + "-"
				+ bridgetunnelsvo.getFacilityDesignatedId().trim());

	}

	public void setformvariable(String path, ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm DynaValidatorForm = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		// System.out.println("Facility Id: "+facilityvo.getDecId());
		// System.out.println("Path="+httpservletrequest.getContextPath()+":"+httpservletrequest.getRealPath("/"));
		String s = (String) DynaValidatorForm.get("methodToCall");
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
				// System.out.println("1");
				String arr[] = new String[5];
				if (listOfFiles[i].isFile()) {
					// System.out.println("1a");
					arr[0] = "file";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					arr[4] = String.valueOf(listOfFiles[i].length()) + " Bytes";
					System.out.println("File " + listOfFiles[i].getName());
				} else if (listOfFiles[i].isDirectory()) {
					// System.out.println("1b");
					arr[0] = "folder";
					arr[1] = contextpath;
					arr[2] = listOfFiles[i].getName();
					Date lastModified = new Date(listOfFiles[i].lastModified());
					arr[3] = String.valueOf(lastModified);
					System.out.println("Directory " + listOfFiles[i].getName());
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

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.BridgeTunnelsAction.class);

}