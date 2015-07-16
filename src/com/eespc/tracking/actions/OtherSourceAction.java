package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.OtherSourceVo;
import com.eespc.tracking.bo.myenum.ModifiedIn;
import com.eespc.tracking.entity.OtherSourceEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class OtherSourceAction extends LookupDispatchAction {

	public OtherSourceAction() {
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
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) form;
		try {
			setDropDown(request);
			HttpSession session = request.getSession();

			userAction = (String) dynavalidatorform.get("methodToCall");
			log.debug("Miscellaneous - MethodToCall " + userAction);
			if (userAction != null && userAction.equalsIgnoreCase("Save"))
				return save(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("Update"))
				return update(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("delete"))
				return delete(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("Reset"))
				return reset(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("displaycontrol"))
				return displaycontrol(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("viewexist"))
				return viewexist(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("fileupload"))
				return fileupload(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("back"))
				return back(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("addnewfolder"))
				return addnewfolder(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("deletefile"))
				return deletefile(mapping, form, request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("editfile"))
				return editfile(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("viewfile"))
				return viewfile(mapping, form, request, response);
		} catch (Exception e) {
			System.out.println("MiscellaneousExecute Execute Exception" + e);
		}

		dynavalidatorform.set("facilitydesinatedId", "");
		dynavalidatorform.set("ModifiedInPast", "");
		dynavalidatorform.set("DisconnectedYear", "");
		dynavalidatorform.set("Ocomments", "");
		dynavalidatorform.set("misname", "");
		dynavalidatorform.set("mismake", "");
		dynavalidatorform.set("mismodel", "");
		dynavalidatorform.set("miscapacity", "");
		dynavalidatorform.set("miscapacityunit", "");
		dynavalidatorform.set("mispollutant1", "");
		dynavalidatorform.set("mispollutant2", "");
		dynavalidatorform.set("mispollutant3", "");
		dynavalidatorform.set("mispollutant1max", "");
		dynavalidatorform.set("mispollutant2max", "");
		dynavalidatorform.set("mispollutant3max", "");
		dynavalidatorform.set("misdec", "");
		dynavalidatorform.set("misdep", "");
		dynavalidatorform.set("misdob", "");
		dynavalidatorform.set("misdoh", "");
		dynavalidatorform.set("misfd", "");
		dynavalidatorform.set("misother", "");
		dynavalidatorform.set("misissuedate", "");
		dynavalidatorform.set("misexpirationdate", "");
		dynavalidatorform.set("misexpirationdateNote", "");
		dynavalidatorform.set("mismonthly", "");
		dynavalidatorform.set("misquartly", "");
		dynavalidatorform.set("missemiannualy", "");
		dynavalidatorform.set("misannualy", "");
		dynavalidatorform.set("misanniversary", "");
		dynavalidatorform.set("mistestingrequired", "");
		dynavalidatorform.set("mistestsubmitted", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("complianceissue", "");
		request.setAttribute("SHOW_BUTTONS", "inline");
		request.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		return mapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		setFormVariable(dynaactionform, othersourcevo, httpservletrequest);
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
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		setFormVariable(dynaactionform, othersourcevo, httpservletrequest);

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
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		setFormVariable(dynaactionform, othersourcevo, httpservletrequest);
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
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		setFormVariable(dynaactionform, othersourcevo, httpservletrequest);
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

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		setFormVariable(dynaactionform, othersourcevo, httpservletrequest);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String path = (String) dynaactionform.get("pathname");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");

		try {

			FormFile uploadFile = (FormFile) dynaactionform.get("filename");
			String uploadingFileName = uploadFile.getFileName();
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

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		setFormVariable(dynaactionform, othersourcevo, httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path1:" + path.length());
		String path2 = facilityvo.getDecId() + "/miscellaneous/"
				+ othersourcevo.getId() + "-"
				+ othersourcevo.getFacilitydesinatedId().trim();
		System.out.println("path2:" + path.length());
		if (path.equals(facilityvo.getDecId() + "/miscellaneous/"
				+ othersourcevo.getId() + "-"
				+ othersourcevo.getFacilitydesinatedId().trim())) {

			back = path;
		} else {
			back = path.substring(0, path.lastIndexOf("/"));
		}

		String foldername = (String) dynaactionform.get("foldername");
		setformvariable(back, actionmapping, actionform, httpservletrequest,
				httpservletresponse);
		return actionmapping.findForward("success");
	}

	public ActionForward displaycontrol(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Miscellaneous - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		System.out.println("Miscellaneous display:" + s);
		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y")) {
			httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpservletrequest.setAttribute("isComponentEditable", "Y");
			httpservletrequest.setAttribute("showAddBtn", "Y");

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
		log.debug("Miscellaneous - ADD");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		OtherSourceVo othersourcevo = new OtherSourceVo();
		othersourcevo.setBuildingId(buildingvo.getId());
		othersourcevo.setFacilitydesinatedId((String) dynaactionform
				.get("facilitydesinatedId"));
		othersourcevo.setModifiedInPast(Integer
				.parseInt((String) dynaactionform.get("ModifiedInPast")));
		othersourcevo.setDisconnectedYear((String) dynaactionform
				.get("DisconnectedYear"));
		othersourcevo.setOcomments((String) dynaactionform.get("Ocomments"));
		othersourcevo.setMisname((String) dynaactionform.get("misname"));
		othersourcevo.setMismake((String) dynaactionform.get("mismake"));
		othersourcevo.setMismodel((String) dynaactionform.get("mismodel"));
		othersourcevo
				.setMiscapacity((String) dynaactionform.get("miscapacity"));
		othersourcevo.setMiscapacityunit((String) dynaactionform
				.get("miscapacityunit"));
		othersourcevo.setMispollutant1((String) dynaactionform
				.get("mispollutant1"));
		othersourcevo.setMispollutant2((String) dynaactionform
				.get("mispollutant2"));
		othersourcevo.setMispollutant3((String) dynaactionform
				.get("mispollutant3"));
		othersourcevo.setMispollutant1max((String) dynaactionform
				.get("mispollutant1max"));
		othersourcevo.setMispollutant2max((String) dynaactionform
				.get("mispollutant2max"));
		othersourcevo.setMispollutant3max((String) dynaactionform
				.get("mispollutant3max"));
		othersourcevo.setMisdec((String) dynaactionform.get("misdec"));
		othersourcevo.setMisdep((String) dynaactionform.get("misdep"));
		othersourcevo.setMisdob((String) dynaactionform.get("misdob"));
		othersourcevo.setMisdoh((String) dynaactionform.get("misdoh"));
		othersourcevo.setMisfd((String) dynaactionform.get("misfd"));
		othersourcevo.setMisother((String) dynaactionform.get("misother"));
		othersourcevo.setMistestsubmitted((String) dynaactionform
				.get("mistestsubmitted"));
		othersourcevo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("misissuedate")),
				"yyyy-MM-dd");
		othersourcevo.setMisissuedate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("misexpirationdate")), "yyyy-MM-dd");
		othersourcevo.setMisexpirationdate(sd);
		othersourcevo.setMisexpirationdateNote((String) dynaactionform
				.get("misexpirationdateNote"));
		othersourcevo.setMismonthly((String) dynaactionform.get("mismonthly"));
		othersourcevo.setMisquartly((String) dynaactionform.get("misquartly"));
		othersourcevo.setMissemiannualy((String) dynaactionform
				.get("missemiannualy"));
		othersourcevo.setMisannualy((String) dynaactionform.get("misannualy"));
		othersourcevo.setMisanniversary((String) dynaactionform
				.get("misanniversary"));
		othersourcevo.setMistestingrequired((String) dynaactionform
				.get("mistestingrequired"));
		othersourcevo.setComplianceissue((String) dynaactionform
				.get("complianceissue"));

		byte byte0 = -99;
		try {
			int i = OtherSourceEntity.add(othersourcevo);
			othersourcevo = OtherSourceEntity.findByPrimaryKey(i);
			if (othersourcevo != null) {
				httpsession.setAttribute("OTHERS_OBJECT", othersourcevo);
				setFormVariable(dynaactionform, othersourcevo,
						httpservletrequest);
			}
			s = "Added Miscellaneous Successfully.";
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

		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, Exception {
		log.debug("Miscellaneous - Update");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		OtherSourceVo othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		String facilityDesignatedid = othersourcevo.getFacilitydesinatedId();
		othersourcevo.setBuildingId(buildingvo.getId());
		othersourcevo.setFacilitydesinatedId((String) dynaactionform
				.get("facilitydesinatedId"));
		othersourcevo.setModifiedInPast(Integer
				.parseInt((String) dynaactionform.get("ModifiedInPast")));
		othersourcevo.setDisconnectedYear((String) dynaactionform
				.get("DisconnectedYear"));
		othersourcevo.setOcomments((String) dynaactionform.get("Ocomments"));
		othersourcevo.setMisname((String) dynaactionform.get("misname"));
		othersourcevo.setMismake((String) dynaactionform.get("mismake"));
		othersourcevo.setMismodel((String) dynaactionform.get("mismodel"));
		othersourcevo
				.setMiscapacity((String) dynaactionform.get("miscapacity"));
		othersourcevo.setMiscapacityunit((String) dynaactionform
				.get("miscapacityunit"));
		othersourcevo.setMispollutant1((String) dynaactionform
				.get("mispollutant1"));
		othersourcevo.setMispollutant2((String) dynaactionform
				.get("mispollutant2"));
		othersourcevo.setMispollutant3((String) dynaactionform
				.get("mispollutant3"));
		othersourcevo.setMispollutant1max((String) dynaactionform
				.get("mispollutant1max"));
		othersourcevo.setMispollutant2max((String) dynaactionform
				.get("mispollutant2max"));
		othersourcevo.setMispollutant3max((String) dynaactionform
				.get("mispollutant3max"));
		othersourcevo.setMisdec((String) dynaactionform.get("misdec"));
		othersourcevo.setMisdep((String) dynaactionform.get("misdep"));
		othersourcevo.setMisdob((String) dynaactionform.get("misdob"));
		othersourcevo.setMisdoh((String) dynaactionform.get("misdoh"));
		othersourcevo.setMisfd((String) dynaactionform.get("misfd"));
		othersourcevo.setMisother((String) dynaactionform.get("misother"));
		othersourcevo.setMistestsubmitted((String) dynaactionform
				.get("mistestsubmitted"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("misissuedate")),
				"yyyy-MM-dd");
		othersourcevo.setMisissuedate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("misexpirationdate")), "yyyy-MM-dd");
		othersourcevo.setMisexpirationdate(sd);
		othersourcevo.setMisexpirationdateNote((String) dynaactionform
				.get("misexpirationdateNote"));
		othersourcevo.setMismonthly((String) dynaactionform.get("mismonthly"));
		othersourcevo.setMisquartly((String) dynaactionform.get("misquartly"));
		othersourcevo.setMissemiannualy((String) dynaactionform
				.get("missemiannualy"));
		othersourcevo.setMisannualy((String) dynaactionform.get("misannualy"));
		othersourcevo.setMisanniversary((String) dynaactionform
				.get("misanniversary"));
		othersourcevo.setMistestingrequired((String) dynaactionform
				.get("mistestingrequired"));
		othersourcevo.setComplianceissue((String) dynaactionform
				.get("complianceissue"));
		othersourcevo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		byte byte0 = -99;
		try {
			OtherSourceEntity.update(othersourcevo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/miscellaneous/" + othersourcevo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/miscellaneous/"
									+ othersourcevo.getId()
									+ "-"
									+ othersourcevo.getFacilitydesinatedId()
											.trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			s = "Updated Miscellaneous Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, othersourcevo, httpservletrequest);
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

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		try {
			log.debug("Miscellaneous - In Delete");
			DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynaactionform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			OtherSourceVo othersourcevo = OtherSourceEntity.findByPrimaryKey(i);
			if (othersourcevo != null)
				httpsession.setAttribute("OTHERS_OBJECT", othersourcevo);
			OtherSourceVo othersourcevox = (OtherSourceVo) httpsession
					.getAttribute("OTHERS_OBJECT");
			OtherSourceEntity.delete(othersourcevox);
			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/miscellaneous/" + othersourcevox.getId() + "-"
						+ othersourcevox.getFacilitydesinatedId().trim());
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
		return actionmapping.findForward("successothers");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			OtherSourceVo othersourcevo, HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Miscellaneous setForm variable");
		dynavalidatorform.set("facilitydesinatedId",
				othersourcevo.getFacilitydesinatedId());
		try {
			ModifiedIn modifiedin = ModifiedIn.get(othersourcevo
					.getModifiedInPast());

			if (modifiedin != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"ModifiedInPast",
						(new StringBuilder())
								.append(String.valueOf(othersourcevo
										.getModifiedInPast())).append("")
								.toString());
			else if (modifiedin != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"ModifiedInPast",
						(new StringBuilder())
								.append(String.valueOf(othersourcevo
										.getModifiedInPast())).append("")
								.toString());

			else if (modifiedin != null && !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set("ModifiedInPast", modifiedin.getName());
			else
				dynavalidatorform.set("ModifiedInPast", "");

			dynavalidatorform.set("DisconnectedYear",
					othersourcevo.getDisconnectedYear());
			dynavalidatorform.set("Ocomments", othersourcevo.getOcomments());
			dynavalidatorform.set("misname", othersourcevo.getMisname());
			dynavalidatorform.set("mismake", othersourcevo.getMismake());
			dynavalidatorform.set("mismodel", othersourcevo.getMismodel());
			dynavalidatorform
					.set("miscapacity", othersourcevo.getMiscapacity());
			dynavalidatorform.set("miscapacityunit",
					othersourcevo.getMiscapacityunit());
			dynavalidatorform.set("mispollutant1",
					othersourcevo.getMispollutant1());
			dynavalidatorform.set("mispollutant2",
					othersourcevo.getMispollutant2());
			dynavalidatorform.set("mispollutant3",
					othersourcevo.getMispollutant3());
			dynavalidatorform.set("mispollutant1max",
					othersourcevo.getMispollutant1max());
			dynavalidatorform.set("mispollutant2max",
					othersourcevo.getMispollutant2max());
			dynavalidatorform.set("mispollutant3max",
					othersourcevo.getMispollutant3max());
			dynavalidatorform.set("misdec", othersourcevo.getMisdec());
			dynavalidatorform.set("misdep", othersourcevo.getMisdep());
			dynavalidatorform.set("misdob", othersourcevo.getMisdob());
			dynavalidatorform.set("misdoh", othersourcevo.getMisdoh());
			dynavalidatorform.set("misfd", othersourcevo.getMisfd());
			dynavalidatorform.set("misother", othersourcevo.getMisother());
			dynavalidatorform.set("misissuedate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(othersourcevo.getMisissuedate()));
			dynavalidatorform.set("misexpirationdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(othersourcevo
							.getMisexpirationdate()));
			dynavalidatorform.set("misexpirationdateNote",
					othersourcevo.getMisexpirationdateNote());
			dynavalidatorform.set("mismonthly", othersourcevo.getMismonthly());
			dynavalidatorform.set("misquartly", othersourcevo.getMisquartly());
			dynavalidatorform.set("missemiannualy",
					othersourcevo.getMissemiannualy());
			dynavalidatorform.set("misannualy", othersourcevo.getMisannualy());
			dynavalidatorform.set("misanniversary",
					othersourcevo.getMisanniversary());
			dynavalidatorform.set("mistestingrequired",
					othersourcevo.getMistestingrequired());
			dynavalidatorform.set("complianceissue",
					othersourcevo.getComplianceissue());
			// dynavalidatorform.set("mistestsubmitted",othersourcevo.getComplianceissue());
			dynavalidatorform.set("mistestsubmitted",
					othersourcevo.getMistestsubmitted());
			dynavalidatorform.set("dobsignoff", othersourcevo.getDobsignoff());
		} catch (Exception exception) {
			System.out.println("Exception :" + exception);
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

	}

	/*
	 * public void setFormVariableforedit(DynaValidatorForm dynavalidatorform1,
	 * ElevatorVo elevatorvo, HttpServletRequest httpservletrequest) { try {
	 * HttpSession httpsession = httpservletrequest.getSession();
	 * log.debug("Elevator setForm variable");
	 * 
	 * dynavalidatorform1.set("eleId", (new
	 * StringBuilder()).append(elevatorvo.getFacilityDesignatedId
	 * ()).append("").toString()); dynavalidatorform1.set("eleType", (new
	 * StringBuilder()).append(elevatorvo.getEleType()).append("").toString());
	 * //dynavalidatorform1.set("elecomment", (new
	 * StringBuilder()).append(elevatorvo.getComment()).append("").toString());
	 * 
	 * dynavalidatorform1.set("eleHydTank", (new
	 * StringBuilder()).append(elevatorvo
	 * .getHydraulicTankId()).append("").toString());
	 * HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo =
	 * elevatorvo.getFuelFromObj(); if(hydraulicelevatorandothertankvo != null)
	 * { dynavalidatorform1.set("eleHydTank", (new
	 * StringBuilder()).append(hydraulicelevatorandothertankvo
	 * .getId()).append("").toString());
	 * dynavalidatorform1.set("eleHydTankFrom",
	 * hydraulicelevatorandothertankvo.getFacilityDesignatedId());
	 * 
	 * } else { dynavalidatorform1.set("eleHydTank", "");
	 * dynavalidatorform1.set("eleHydTankFrom", ""); }
	 * dynavalidatorform1.set("YearInstalled", elevatorvo.getYearInstalled());
	 * dynavalidatorform1.set("DisconnectedYear",
	 * elevatorvo.getDisconnectedYear());
	 * dynavalidatorform1.set("ModifiedInPast",
	 * String.valueOf(elevatorvo.getModifiedInPast()));
	 * System.out.println("ViewComment:"+elevatorvo.getComment());
	 * dynavalidatorform1.set("elecomment",
	 * String.valueOf(elevatorvo.getComment()));
	 * 
	 * } catch(Exception exception) {
	 * 
	 * System.out.println("View For Edit elvator:"+exception);
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Press - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		OtherSourceVo othersourcevo = OtherSourceEntity.findByPrimaryKey(i);
		if (othersourcevo != null) {
			httpsession.setAttribute("OTHERS_OBJECT", othersourcevo);
			setFormVariable(dynavalidatorform, othersourcevo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get othersourcevo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("MIS... Edit");
		log.debug("MIS... Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		OtherSourceVo othersourcevo = OtherSourceEntity.findByPrimaryKey(i);
		if (othersourcevo != null) {
			httpsession.setAttribute("OTHERS_OBJECT", othersourcevo);
			setFormVariable(dynavalidatorform, othersourcevo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get othersourcevo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("OtherSource - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		OtherSourceVo othersourcevo = OtherSourceEntity.findByPrimaryKey(i);
		if (othersourcevo != null) {
			httpsession.setAttribute("PRESS_OBJECT", othersourcevo);
			setFormVariable(dynavalidatorform, othersourcevo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get othersourcevo for id(").append(s)
					.append(")").toString());
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("facilitydesinatedId", "");
		dynavalidatorform.set("ModifiedInPast", "");
		dynavalidatorform.set("DisconnectedYear", "");
		dynavalidatorform.set("Ocomments", "");
		dynavalidatorform.set("misname", "");
		dynavalidatorform.set("mismake", "");
		dynavalidatorform.set("mismodel", "");
		dynavalidatorform.set("miscapacity", "");
		dynavalidatorform.set("miscapacityunit", "");
		dynavalidatorform.set("mispollutant1", "");
		dynavalidatorform.set("mispollutant2", "");
		dynavalidatorform.set("mispollutant3", "");
		dynavalidatorform.set("mispollutant1max", "");
		dynavalidatorform.set("mispollutant2max", "");
		dynavalidatorform.set("mispollutant3max", "");
		dynavalidatorform.set("misdec", "");
		dynavalidatorform.set("misdep", "");
		dynavalidatorform.set("misdob", "");
		dynavalidatorform.set("misdoh", "");
		dynavalidatorform.set("misfd", "");
		dynavalidatorform.set("misother", "");
		dynavalidatorform.set("misissuedate", "");
		dynavalidatorform.set("misexpirationdate", "");
		dynavalidatorform.set("misexpirationdateNote", "");
		dynavalidatorform.set("mismonthly", "");
		dynavalidatorform.set("misquartly", "");
		dynavalidatorform.set("missemiannualy", "");
		dynavalidatorform.set("misannualy", "");
		dynavalidatorform.set("misanniversary", "");
		dynavalidatorform.set("mistestingrequired", "");
		dynavalidatorform.set("complianceissue", "");
		dynavalidatorform.set("mistestsubmitted", "");
		dynavalidatorform.set("dobsignoff", "");
		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		OtherSourceVo othersourcevo = (OtherSourceVo) httpsession
				.getAttribute("OTHERS_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/miscellaneous/" + othersourcevo.getId() + "-"
					+ othersourcevo.getFacilitydesinatedId().trim());
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
					+ "/miscellaneous/" + othersourcevo.getId() + "-"
					+ othersourcevo.getFacilitydesinatedId().trim();
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
				+ "/miscellaneous/" + othersourcevo.getId() + "-"
				+ othersourcevo.getFacilitydesinatedId().trim());

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
		httpservletrequest.setAttribute("OTHERS_STATUS", dropdown);
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.OtherSourceAction.class);
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