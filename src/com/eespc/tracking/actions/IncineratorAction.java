package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.IncConsumptionVo;
import com.eespc.tracking.bo.IncineratorVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackTestVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.myenum.AgencyLocationEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.IncineratorWasteTypeEnum;
import com.eespc.tracking.bo.myenum.ProductStorageEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.bo.myenum.YesNoEnum;
import com.eespc.tracking.entity.IncineratorEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class IncineratorAction extends LookupDispatchAction {

	public IncineratorAction() {
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
		HttpSession httpsession = httpservletrequest.getSession();
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		userAction = (String) dynavalidatorform.get("methodToCall");
		System.out.println("Incinerator Action - In displayControl "
				+ userAction);
		log.debug((new StringBuilder())
				.append("IncineratorAction - MethodToCall ").append(userAction)
				.toString());
		setDropDown(httpservletrequest);
		if (userAction != null && userAction.equalsIgnoreCase("Save"))
			return save(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Update"))
			return update(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("delete"))
			return delete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Return"))
			return add(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (userAction != null && userAction.equalsIgnoreCase("Reset"))
			return reset(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null
				&& userAction.equalsIgnoreCase("fuelConsumptionInfo"))
			return fuelConsumptionInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null
				&& userAction.equalsIgnoreCase("deletefuelConsumptionInfo"))
			return deletefuelConsumptionInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("stack"))
			return stackTest(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("stackEdit"))
			return stackEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("stackDelete"))

			return stackTestDelete(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("stackUpdate"))

			return stackTestUpdate(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("view"))

			return view(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("viewexist"))

			return viewexist(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("edit"))

			return edit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("dep"))

			return dep(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("depEdit"))
			return depEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("depDelete"))

			return depDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("depUpdate"))

			return depUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("cga"))

			return cga(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("cgaEdit"))

			return cgaEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("cgaDelete"))

			return cgaDelete(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("cgaUpdate"))

			return cgaUpdate(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("cons"))

			System.out.println("IncineratorAction Inside cons");

		if (userAction != null && userAction.equalsIgnoreCase("consEdit"))

			return consumptionEdit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("consDelete"))
			return consumptionDelete(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("consUpdate"))
			return consumptionUpdate(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("loc"))

			return location(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("locEdit"))
			return locationEdit(actionmapping, actionform, httpservletrequest,
					httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("locDelete"))
			return locationDelete(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (userAction != null && userAction.equalsIgnoreCase("locUpdate"))
			return locationUpdate(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (userAction != null
				&& userAction
						.equalsIgnoreCase("fuelConsumptionInfoyearconsumtion"))
			return fuelConsumptionInfoyearconsumtion(actionmapping, actionform,
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

		else {

			setDropDown(httpservletrequest);
			dynavalidatorform.set("incId", "");
			dynavalidatorform.set("incFloor", "");
			dynavalidatorform.set("incModel", "");
			dynavalidatorform.set("incMake", "");
			dynavalidatorform.set("incSerialNo", "");
			dynavalidatorform.set("incYearIns", "");
			dynavalidatorform.set("inc_status", "");
			dynavalidatorform.set("inc_disconnecteddate", "");
			dynavalidatorform.set("incBurner", "");
			dynavalidatorform.set("incBurnMake", "");
			dynavalidatorform.set("incBurnModel", "");
			dynavalidatorform.set("incWasteBurnType", "");
			dynavalidatorform.set("incCapacity", "");
			dynavalidatorform.set("incScrubber", "");
			dynavalidatorform.set("incDOB", "");
			dynavalidatorform.set("incMea", "");
			dynavalidatorform.set("incCOMonitor", "");
			dynavalidatorform.set("incOpacity", "");
			dynavalidatorform.set("incO2Monitor", "");
			dynavalidatorform.set("incOpacityChart", "");
			dynavalidatorform.set("incQuarterlyCGA", "");
			dynavalidatorform.set("incStackFrom", "");
			dynavalidatorform.set("incStack", "");
			dynavalidatorform.set("typeofunit", "");
			dynavalidatorform.set("dobno", "");
			dynavalidatorform.set("teperaturerequired", "");
			dynavalidatorform.set("facilitysecondary", "");
			dynavalidatorform.set("facilityprimary", "");
			dynavalidatorform.set("primarytemp", "");
			dynavalidatorform.set("sectemp", "");
			dynavalidatorform.set("deprequired", "");
			dynavalidatorform.set("depno", "");
			dynavalidatorform.set("decno", "");
			dynavalidatorform.set("incStackTested", "");
			dynavalidatorform.set("i_decPermitObtained", "");
			dynavalidatorform.set("protocolSubmitted", "");
			dynavalidatorform.set("testConductedBy", "");
			dynavalidatorform.set("testReportSubmited", "");
			dynavalidatorform.set("testReportSubmitedDate", "");
			dynavalidatorform.set("retestPlanned", "");
			dynavalidatorform.set("i_testPassed", "");
			dynavalidatorform.set("i_StackTestDate", "");
			dynavalidatorform.set("nextStackTest", "");
			dynavalidatorform.set("nextStackTestNote", "");
			dynavalidatorform.set("B_parameters1", "");
			dynavalidatorform.set("B_parameters2", "");
			dynavalidatorform.set("B_parameters3", "");
			dynavalidatorform.set("B_parameters4", "");
			dynavalidatorform.set("B_parameters5", "");
			dynavalidatorform.set("B_parameters6", "");
			dynavalidatorform.set("B_parameters7", "");
			dynavalidatorform.set("B_parameters8", "");
			dynavalidatorform.set("B_parameters9", "");
			dynavalidatorform.set("iwastequantity", "");
			dynavalidatorform.set("isolidwaste", "");
			dynavalidatorform.set("i_SolidIssuedDate", "");
			dynavalidatorform.set("i_SolidExpirationDate", "");
			dynavalidatorform.set("solidWasteExpirationNote", "");
			dynavalidatorform.set("icomments", "");
			dynavalidatorform.set("iwastelimit", "");
			dynavalidatorform.set("dobsignoff", "");
			httpservletrequest.setAttribute("INC_DEP", "none");
			httpservletrequest.setAttribute("INC_LOC", "none");
			httpservletrequest.setAttribute("INC_CGA", "none");
			httpservletrequest.setAttribute("INC_CONS", "none");
			httpservletrequest.setAttribute("INC_CGA_LST", null);
			httpservletrequest.setAttribute("INC_DEP_LST", null);
			httpservletrequest.setAttribute("INC_LOC_LST", null);
			httpservletrequest.setAttribute("INC_CONS_LST", null);
			httpservletrequest.setAttribute("LOCATION", "");
			httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
			httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		}
		return actionmapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		// setFieldDetails(httpservletrequest, dynaactionform, incineratorvo);
		setScreenInfo(httpservletrequest);
		setFormVariable(dynaactionform, incineratorvo, httpservletrequest);

		// refreshShowInfo(httpservletrequest, incineratorvo);
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
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		setFormVariable(dynaactionform, incineratorvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, incineratorvo);
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
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		setFormVariable(dynaactionform, incineratorvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, incineratorvo);
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
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		setFormVariable(dynaactionform, incineratorvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, incineratorvo);
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
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		setFormVariable(dynaactionform, incineratorvo, httpservletrequest);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, incineratorvo);
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
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		setFormVariable(dynaactionform, incineratorvo, httpservletrequest);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/incinerator/"
				+ incineratorvo.getId() + "-"
				+ incineratorvo.getFacilityDesignatedId().trim())) {
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

	public ActionForward fuelConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		IncineratorEntity incineratorentity = new IncineratorEntity();

		List list = incineratorentity.getFuelConsumptionyearList(
				incineratorvo.getId(), year);

		httpservletresponse.setContentType("text/html");
		PrintWriter printwriter = httpservletresponse.getWriter();

		if (list != null && list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list);
			printwriter.println(properties.get("PREVIOUS_CONSUMPTION"));
		} else {
			printwriter.println("0|0|0|0|0|0|0|0|0|0|0|0");
		}

		printwriter.flush();
		return null;

	}

	public ActionForward fuelConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Incinerator Action - In fuelConsumptionInfo");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(incvo.getId());
		String ttype = (String) dynaactionform.get("bctype");

		fuelconsumptionvo.setBctype(ttype);
		fuelconsumptionvo.setYear((String) dynaactionform.get("fcyear"));
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
				+ getValue("jan", dynaactionform));
		fuelconsumptionvo.setJan(getValue("jan", dynaactionform));
		fuelconsumptionvo.setFeb(getValue("feb", dynaactionform));
		fuelconsumptionvo.setMar(getValue("mar", dynaactionform));
		fuelconsumptionvo.setApr(getValue("apr", dynaactionform));
		fuelconsumptionvo.setMay(getValue("may", dynaactionform));
		fuelconsumptionvo.setJun(getValue("jun", dynaactionform));
		fuelconsumptionvo.setJul(getValue("jul", dynaactionform));
		fuelconsumptionvo.setAug(getValue("aug", dynaactionform));
		fuelconsumptionvo.setSep(getValue("sep", dynaactionform));
		fuelconsumptionvo.setOct(getValue("oct", dynaactionform));
		fuelconsumptionvo.setNov(getValue("nov", dynaactionform));
		fuelconsumptionvo.setDec(getValue("dec", dynaactionform));
		fuelconsumptionvo.setRollingConsumption(getValue("consumption",
				dynaactionform));
		fuelconsumptionvo.setCompliant(UtilityObject
				.convertBoolean((String) dynaactionform.get("compliance")));
		fuelconsumptionvo.setLocked(UtilityObject
				.convertBoolean((String) dynaactionform.get("lock")));
		if (UtilityObject.isNotEmpty((String) dynaactionform
				.get("fuelConsumptionId"))) {
			fuelconsumptionvo.setId(UtilityObject
					.getIntFromString((String) dynaactionform
							.get("fuelConsumptionId")));
			IncineratorEntity.updateFuelConsumption(fuelconsumptionvo);
			log.debug("Updated fuel object");
		} else {
			int i = IncineratorEntity.addFuelConsumption(fuelconsumptionvo);
			log.debug((new StringBuilder()).append("Added fuel id=").append(i)
					.toString());
		}
		incvo.setFuelConsumptionList(null);
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		if (incineratorvo != null) {
			httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
			actionform = setFormVariable(dynaactionform, incineratorvo,
					httpservletrequest);
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward deletefuelConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("IncinatorAction - In fuelConsumptionInfo");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(incineratorvo.getId());
		int tid = Integer.parseInt((String) dynaactionform
				.get("fueldeleteConsumptionId"));
		IncineratorEntity.deletefuelconsumption(tid);
		incineratorvo.setFuelConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		dynaactionform = setFormVariable(dynaactionform, incineratorvo,
				httpservletrequest);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Incinerator Action - In displayControl");
		System.out.println("Incinerator Action - In displayControl ");
		String s = httpservletrequest.getParameter("hdnContext");
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
		}
		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}

		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = AgencyLocationEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("AGENCY_LOCATION", dropdown);
		dropdown = IncineratorWasteTypeEnum.getDropDownObj();
		httpservletrequest.setAttribute("INCINERATOR_WASTE_TYPE", dropdown);
		dropdown = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEAR", dropdown);
		dropdown = YesNoEnum.getDropDownObj();
		httpservletrequest.setAttribute("YESNO", dropdown);
		dropdown = ProductStorageEnum.getDropDownObj();
		httpservletrequest.setAttribute("PRODUCT", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown1);
	}

	public DynaValidatorForm setFormVariable(
			DynaValidatorForm dynavalidatorform, IncineratorVo incineratorvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		System.out.println("Incinerator setForm variable");
		log.debug("Incinerator setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"incId",
				(new StringBuffer(String.valueOf(incineratorvo
						.getFacilityDesignatedId()))).toString());
		dynavalidatorform1.set("incFloor",
				(new StringBuffer(String.valueOf(incineratorvo.getFloor())))
						.toString());
		dynavalidatorform1.set("incModel",
				(new StringBuffer(String.valueOf(incineratorvo.getModel())))
						.toString());
		dynavalidatorform1.set("incMake", incineratorvo.getMake());
		dynavalidatorform1.set("incSerialNo", incineratorvo.getSerialNo());
		dynavalidatorform1.set("incYearIns", incineratorvo.getYearInstalled());
		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(incineratorvo.getStatus());
		if (tankoperatingstatusenum != null)
			dynavalidatorform1.set("inc_status",
					tankoperatingstatusenum.getName());
		dynavalidatorform1.set(
				"inc_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								incineratorvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("incBurner", incineratorvo.getBurnerCapacity());
		dynavalidatorform1.set("incBurnMake", incineratorvo.getBurnerMake());
		dynavalidatorform1.set("incBurnModel", incineratorvo.getBurnerModel());
		dynavalidatorform1.set("incCapacity", incineratorvo.getCapacity());
		boolean flag = incineratorvo.isScrubberInst();
		if (flag)
			dynavalidatorform1.set("incScrubber", "Y");
		else
			dynavalidatorform1.set("incScrubber", "N");
		flag = incineratorvo.isDOBApproved();
		if (flag)
			dynavalidatorform1.set("incDOB", "Y");
		else
			dynavalidatorform1.set("incDOB", "N");

		dynavalidatorform1.set("incCOMonitor",
				checkNullAndFill(incineratorvo.isCOMonitor(), ""));
		dynavalidatorform1.set("incO2Monitor",
				checkNullAndFill(incineratorvo.isO2Installed(), ""));
		dynavalidatorform1.set("incOpacity",
				checkNullAndFill(incineratorvo.isOpacityInstalled(), ""));

		flag = incineratorvo.isOpacityChartAvailable();
		if (flag)
			dynavalidatorform1.set("incOpacityChart", "Y");
		else
			dynavalidatorform1.set("incOpacityChart", "N");
		flag = incineratorvo.isCGARequired();
		if (flag)
			dynavalidatorform1.set("incQuarterlyCGA", "Y");
		else
			dynavalidatorform1.set("incQuarterlyCGA", "N");
		dynavalidatorform1.set("incMea", incineratorvo.getMEA());
		dynavalidatorform1
				.set("incStack",
						(new StringBuffer(String.valueOf(incineratorvo
								.getStackFrom()))).toString());
		StackVo stackvo = incineratorvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set("incStack",
					(new StringBuffer(String.valueOf(stackvo.getStackId())))
							.toString());
			dynavalidatorform1
					.set("incStackFrom", stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("incStack", "");
			dynavalidatorform1.set("incStackFrom", "");
		}

		dynavalidatorform1.set("typeofunit", incineratorvo.getTypeofunit());
		dynavalidatorform1.set("dobno", incineratorvo.getDobno());
		dynavalidatorform1.set("teperaturerequired",
				incineratorvo.getTeperaturerequired());
		dynavalidatorform1.set("facilitysecondary",
				incineratorvo.getFacilitysecondary());
		dynavalidatorform1.set("facilityprimary",
				incineratorvo.getFacilityprimary());
		dynavalidatorform1.set("primarytemp", incineratorvo.getPrimarytemp());
		dynavalidatorform1.set("sectemp", incineratorvo.getSectemp());
		dynavalidatorform1.set("deprequired", incineratorvo.getDeprequired());
		dynavalidatorform1.set("depno", incineratorvo.getDepno());
		dynavalidatorform1.set("decno", incineratorvo.getDecno());
		dynavalidatorform1.set("incStackTested",
				incineratorvo.getIncStackTested());
		dynavalidatorform1.set("i_decPermitObtained",
				incineratorvo.getI_decPermitObtained());
		dynavalidatorform1.set("protocolSubmitted",
				incineratorvo.getProtocolSubmitted());
		dynavalidatorform1.set("testConductedBy",
				incineratorvo.getTestConductedBy());
		dynavalidatorform1.set("testReportSubmited",
				incineratorvo.getTestReportSubmited());
		dynavalidatorform1.set("testReportSubmitedDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo
						.getTestReportSubmitedDate()));
		dynavalidatorform1.set("retestPlanned",
				incineratorvo.getRetestPlanned());
		dynavalidatorform1.set("i_testPassed", incineratorvo.getI_testPassed());
		dynavalidatorform1.set("i_StackTestDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo.getI_StackTestDate()));
		dynavalidatorform1.set("nextStackTest", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo.getNextStackTest()));
		dynavalidatorform1.set("B_parameters1",
				incineratorvo.getB_parameters1());
		dynavalidatorform1.set("B_parameters2",
				incineratorvo.getB_parameters2());
		dynavalidatorform1.set("B_parameters3",
				incineratorvo.getB_parameters3());
		dynavalidatorform1.set("B_parameters4",
				incineratorvo.getB_parameters4());
		dynavalidatorform1.set("B_parameters5",
				incineratorvo.getB_parameters5());
		dynavalidatorform1.set("B_parameters6",
				incineratorvo.getB_parameters6());
		dynavalidatorform1.set("B_parameters7",
				incineratorvo.getB_parameters7());
		dynavalidatorform1.set("B_parameters8",
				incineratorvo.getB_parameters8());
		dynavalidatorform1.set("B_parameters9",
				incineratorvo.getB_parameters9());
		dynavalidatorform1.set("iwastequantity",
				incineratorvo.getIwastequantity());
		dynavalidatorform1.set("isolidwaste", incineratorvo.getIsolidwaste());
		System.out.println("Incinerator setForm variable1"
				+ incineratorvo.getI_SolidIssuedDate());
		dynavalidatorform1
				.set("i_SolidIssuedDate", UtilityObject
						.convertYyyyMmDd2MmDdYyyy(incineratorvo
								.getI_SolidIssuedDate()));
		dynavalidatorform1.set("i_SolidExpirationDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo
						.getI_SolidExpirationDate()));
		System.out.println("Incinerator setForm variable2"
				+ incineratorvo.getI_SolidExpirationDate());
		dynavalidatorform1.set("icomments", incineratorvo.getIcomments());
		dynavalidatorform1.set("iwastelimit", incineratorvo.getIwastelimit());
		dynavalidatorform1.set("dobsignoff", incineratorvo.getDobsignoff());
		try {
			int i = -99;
			int j = -99;
			i = incineratorvo.getLocation();
			j = incineratorvo.getWasteBurned();
			System.out.println(j + "ww" + i);
			if (!userAction.equalsIgnoreCase("edit")) {
				if (i != -1)
					dynavalidatorform1.set("incLocation", AgencyLocationEnum
							.get(i).toString());

				if (j != -1)
					dynavalidatorform1.set("incWasteBurnType",
							IncineratorWasteTypeEnum.get(j).toString());
			} else {
				dynavalidatorform1.set("incLocation", String.valueOf(i));
				dynavalidatorform1.set("incWasteBurnType", String.valueOf(j));
			}

		} catch (Exception e) {
			System.out.println("Exception In incLocation:" + e);
		}
		try {

			List list1 = incineratorvo.getFuelConsumptionList();
			// System.out.println("Consumtion Length:"+list1.size());
			if (list1 != null && list1.size() > 0) {
				Properties properties = UtilityObject
						.getRollingAverageInfo(list1);
				httpservletrequest.setAttribute("hdnConsumption",
						properties.get("TOTAL"));
				httpservletrequest.setAttribute("hdnPreviousConsumption",
						properties.get("PREVIOUS_CONSUMPTION"));
				httpservletrequest.setAttribute("hdnCurrentMonthIndex",
						properties.get("CURRENT_MONTH_INDEX"));
				httpservletrequest.setAttribute("FUEL_CONSUMPTION", list1);
			} else {
				httpservletrequest.setAttribute("FUEL_CONSUMPTION",
						new ArrayList());
				httpservletrequest.setAttribute("hdnConsumption", "");
				httpservletrequest.setAttribute("hdnPreviousConsumption",
						"0|0|0|0|0|0|0|0|0|0|0|0");
				httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
				log.debug("No Consumption info");
			}

			setDropDown(httpservletrequest);
			setScreenInfo(httpservletrequest);
			refreshShowInfo(httpservletrequest, incineratorvo);
		} catch (Exception e) {
			System.out.println("Exception in view:" + e);
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforedit(
			DynaValidatorForm dynavalidatorform, IncineratorVo incineratorvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Incinerator setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"incId",
				(new StringBuffer(String.valueOf(incineratorvo
						.getFacilityDesignatedId()))).toString());
		dynavalidatorform1.set("incFloor",
				(new StringBuffer(String.valueOf(incineratorvo.getFloor())))
						.toString());
		dynavalidatorform1.set("incModel",
				(new StringBuffer(String.valueOf(incineratorvo.getModel())))
						.toString());
		dynavalidatorform1.set("incMake", incineratorvo.getMake());
		dynavalidatorform1.set("incSerialNo", incineratorvo.getSerialNo());
		dynavalidatorform1.set("incYearIns", incineratorvo.getYearInstalled());
		dynavalidatorform1.set(
				"inc_status",
				(new StringBuilder()).append(incineratorvo.getStatus())
						.append("").toString());
		dynavalidatorform1.set(
				"inc_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								incineratorvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("incBurner", incineratorvo.getBurnerCapacity());
		dynavalidatorform1.set("incBurnMake", incineratorvo.getBurnerMake());
		dynavalidatorform1.set("incBurnModel", incineratorvo.getBurnerModel());
		dynavalidatorform1.set("incCapacity", incineratorvo.getCapacity());
		boolean flag = incineratorvo.isScrubberInst();
		if (flag)
			dynavalidatorform1.set("incScrubber", "Y");
		else
			dynavalidatorform1.set("incScrubber", "N");
		flag = incineratorvo.isDOBApproved();
		if (flag)
			dynavalidatorform1.set("incDOB", "Y");
		else
			dynavalidatorform1.set("incDOB", "N");

		dynavalidatorform1.set("incCOMonitor",
				checkNullAndFill(incineratorvo.isCOMonitor(), ""));
		dynavalidatorform1.set("incO2Monitor",
				checkNullAndFill(incineratorvo.isO2Installed(), ""));
		dynavalidatorform1.set("incOpacity",
				checkNullAndFill(incineratorvo.isOpacityInstalled(), ""));

		flag = incineratorvo.isOpacityChartAvailable();
		if (flag)
			dynavalidatorform1.set("incOpacityChart", "Y");
		else
			dynavalidatorform1.set("incOpacityChart", "N");
		flag = incineratorvo.isCGARequired();
		if (flag)
			dynavalidatorform1.set("incQuarterlyCGA", "Y");
		else
			dynavalidatorform1.set("incQuarterlyCGA", "N");
		dynavalidatorform1.set("incMea", incineratorvo.getMEA());
		dynavalidatorform1
				.set("incStack",
						(new StringBuffer(String.valueOf(incineratorvo
								.getStackFrom()))).toString());
		StackVo stackvo = incineratorvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set("incStack",
					(new StringBuffer(String.valueOf(stackvo.getStackId())))
							.toString());
			dynavalidatorform1
					.set("incStackFrom", stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("incStack", "");
			dynavalidatorform1.set("incStackFrom", "");
		}

		dynavalidatorform1.set("typeofunit", incineratorvo.getTypeofunit());
		dynavalidatorform1.set("dobno", incineratorvo.getDobno());
		dynavalidatorform1.set("teperaturerequired",
				incineratorvo.getTeperaturerequired());
		dynavalidatorform1.set("facilitysecondary",
				incineratorvo.getFacilitysecondary());
		dynavalidatorform1.set("facilityprimary",
				incineratorvo.getFacilityprimary());
		dynavalidatorform1.set("primarytemp", incineratorvo.getPrimarytemp());
		dynavalidatorform1.set("sectemp", incineratorvo.getSectemp());
		dynavalidatorform1.set("deprequired", incineratorvo.getDeprequired());
		dynavalidatorform1.set("depno", incineratorvo.getDepno());
		dynavalidatorform1.set("decno", incineratorvo.getDecno());
		dynavalidatorform1.set("incStackTested",
				incineratorvo.getIncStackTested());
		dynavalidatorform1.set("i_decPermitObtained",
				incineratorvo.getI_decPermitObtained());
		dynavalidatorform1.set("protocolSubmitted",
				incineratorvo.getProtocolSubmitted());
		dynavalidatorform1.set("testConductedBy",
				incineratorvo.getTestConductedBy());
		dynavalidatorform1.set("testReportSubmited",
				incineratorvo.getTestReportSubmited());
		dynavalidatorform1.set("testReportSubmitedDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo
						.getTestReportSubmitedDate()));
		dynavalidatorform1.set("retestPlanned",
				incineratorvo.getRetestPlanned());
		dynavalidatorform1.set("i_testPassed", incineratorvo.getI_testPassed());
		dynavalidatorform1.set("i_StackTestDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo.getI_StackTestDate()));
		dynavalidatorform1.set("nextStackTest", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo.getNextStackTest()));
		dynavalidatorform1.set("B_parameters1",
				incineratorvo.getB_parameters1());
		dynavalidatorform1.set("B_parameters2",
				incineratorvo.getB_parameters2());
		dynavalidatorform1.set("B_parameters3",
				incineratorvo.getB_parameters3());
		dynavalidatorform1.set("B_parameters4",
				incineratorvo.getB_parameters4());
		dynavalidatorform1.set("B_parameters5",
				incineratorvo.getB_parameters5());
		dynavalidatorform1.set("B_parameters6",
				incineratorvo.getB_parameters6());
		dynavalidatorform1.set("B_parameters7",
				incineratorvo.getB_parameters7());
		dynavalidatorform1.set("B_parameters8",
				incineratorvo.getB_parameters8());
		dynavalidatorform1.set("B_parameters9",
				incineratorvo.getB_parameters9());
		dynavalidatorform1.set("iwastequantity",
				incineratorvo.getIwastequantity());
		dynavalidatorform1.set("isolidwaste", incineratorvo.getIsolidwaste());
		dynavalidatorform1
				.set("i_SolidIssuedDate", UtilityObject
						.convertYyyyMmDd2MmDdYyyy(incineratorvo
								.getI_SolidIssuedDate()));
		dynavalidatorform1.set("i_SolidExpirationDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo
						.getI_SolidExpirationDate()));
		dynavalidatorform1.set("icomments", incineratorvo.getIcomments());
		dynavalidatorform1.set("iwastelimit", incineratorvo.getIwastelimit());
		dynavalidatorform1.set("dobsignoff", incineratorvo.getDobsignoff());

		try {
			int i = -99;
			int j = -99;
			i = incineratorvo.getLocation();
			j = incineratorvo.getWasteBurned();
			System.out.println(j + "ww" + i);
			if (!userAction.equalsIgnoreCase("edit")) {
				if (i != -1)
					dynavalidatorform1.set("incLocation", AgencyLocationEnum
							.get(i).toString());

				if (j != -1)
					dynavalidatorform1.set("incWasteBurnType",
							IncineratorWasteTypeEnum.get(j).toString());
			} else {
				dynavalidatorform1.set("incLocation", String.valueOf(i));
				dynavalidatorform1.set("incWasteBurnType", String.valueOf(j));
			}

		} catch (Exception e) {
			System.out.println("Exception In incLocation:" + e);
		}

		List list1 = incineratorvo.getFuelConsumptionList();

		if (list1 != null && list1.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list1);
			httpservletrequest.setAttribute("hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FUEL_CONSUMPTION", list1);
		} else {
			httpservletrequest
					.setAttribute("FUEL_CONSUMPTION", new ArrayList());
			httpservletrequest.setAttribute("hdnConsumption", "");
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		setDropDown(httpservletrequest);
		setScreenInfo(httpservletrequest);
		refreshShowInfo(httpservletrequest, incineratorvo);
		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, IncineratorVo incineratorvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("Incinerator setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"incId",
				(new StringBuffer(String.valueOf(incineratorvo
						.getFacilityDesignatedId()))).toString());
		dynavalidatorform1.set("incFloor",
				(new StringBuffer(String.valueOf(incineratorvo.getFloor())))
						.toString());
		dynavalidatorform1.set("incModel",
				(new StringBuffer(String.valueOf(incineratorvo.getModel())))
						.toString());
		dynavalidatorform1.set("incMake", incineratorvo.getMake());
		dynavalidatorform1.set("incSerialNo", incineratorvo.getSerialNo());
		dynavalidatorform1.set("incYearIns", incineratorvo.getYearInstalled());
		dynavalidatorform1.set(
				"inc_status",
				(new StringBuilder()).append(incineratorvo.getStatus())
						.append("").toString());
		dynavalidatorform1.set(
				"inc_disconnecteddate",
				(new StringBuilder())
						.append(checkNullAndFill(
								incineratorvo.getDisconnecteddate(), ""))
						.append("").toString());
		dynavalidatorform1.set("incBurner", incineratorvo.getBurnerCapacity());
		dynavalidatorform1.set("incBurnMake", incineratorvo.getBurnerMake());
		dynavalidatorform1.set("incBurnModel", incineratorvo.getBurnerModel());
		dynavalidatorform1.set("incCapacity", incineratorvo.getCapacity());
		boolean flag = incineratorvo.isScrubberInst();
		if (flag)
			dynavalidatorform1.set("incScrubber", "Y");
		else
			dynavalidatorform1.set("incScrubber", "N");
		flag = incineratorvo.isDOBApproved();
		if (flag)
			dynavalidatorform1.set("incDOB", "Y");
		else
			dynavalidatorform1.set("incDOB", "N");

		dynavalidatorform1.set("incCOMonitor",
				checkNullAndFill(incineratorvo.isCOMonitor(), ""));
		dynavalidatorform1.set("incO2Monitor",
				checkNullAndFill(incineratorvo.isO2Installed(), ""));
		dynavalidatorform1.set("incOpacity",
				checkNullAndFill(incineratorvo.isOpacityInstalled(), ""));

		flag = incineratorvo.isOpacityChartAvailable();
		if (flag)
			dynavalidatorform1.set("incOpacityChart", "Y");
		else
			dynavalidatorform1.set("incOpacityChart", "N");
		flag = incineratorvo.isCGARequired();
		if (flag)
			dynavalidatorform1.set("incQuarterlyCGA", "Y");
		else
			dynavalidatorform1.set("incQuarterlyCGA", "N");
		dynavalidatorform1.set("incMea", incineratorvo.getMEA());
		dynavalidatorform1
				.set("incStack",
						(new StringBuffer(String.valueOf(incineratorvo
								.getStackFrom()))).toString());
		StackVo stackvo = incineratorvo.getStackVo();
		if (stackvo != null) {
			dynavalidatorform1.set("incStack",
					(new StringBuffer(String.valueOf(stackvo.getStackId())))
							.toString());
			dynavalidatorform1
					.set("incStackFrom", stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("incStack", "");
			dynavalidatorform1.set("incStackFrom", "");
		}

		dynavalidatorform1.set("typeofunit", incineratorvo.getTypeofunit());
		dynavalidatorform1.set("dobno", incineratorvo.getDobno());
		dynavalidatorform1.set("teperaturerequired",
				incineratorvo.getTeperaturerequired());
		dynavalidatorform1.set("facilitysecondary",
				incineratorvo.getFacilitysecondary());
		dynavalidatorform1.set("facilityprimary",
				incineratorvo.getFacilityprimary());
		dynavalidatorform1.set("primarytemp", incineratorvo.getPrimarytemp());
		dynavalidatorform1.set("sectemp", incineratorvo.getSectemp());
		dynavalidatorform1.set("deprequired", incineratorvo.getDeprequired());
		dynavalidatorform1.set("depno", incineratorvo.getDepno());
		dynavalidatorform1.set("decno", incineratorvo.getDecno());
		dynavalidatorform1.set("incStackTested",
				incineratorvo.getIncStackTested());
		dynavalidatorform1.set("i_decPermitObtained",
				incineratorvo.getI_decPermitObtained());
		dynavalidatorform1.set("protocolSubmitted",
				incineratorvo.getProtocolSubmitted());
		dynavalidatorform1.set("testConductedBy",
				incineratorvo.getTestConductedBy());
		dynavalidatorform1.set("testReportSubmited",
				incineratorvo.getTestReportSubmited());
		dynavalidatorform1.set("testReportSubmitedDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo
						.getTestReportSubmitedDate()));
		dynavalidatorform1.set("retestPlanned",
				incineratorvo.getRetestPlanned());
		dynavalidatorform1.set("i_testPassed", incineratorvo.getI_testPassed());
		dynavalidatorform1.set("i_StackTestDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo.getI_StackTestDate()));
		dynavalidatorform1.set("nextStackTest", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo.getNextStackTest()));
		dynavalidatorform1.set("B_parameters1",
				incineratorvo.getB_parameters1());
		dynavalidatorform1.set("B_parameters2",
				incineratorvo.getB_parameters2());
		dynavalidatorform1.set("B_parameters3",
				incineratorvo.getB_parameters3());
		dynavalidatorform1.set("B_parameters4",
				incineratorvo.getB_parameters4());
		dynavalidatorform1.set("B_parameters5",
				incineratorvo.getB_parameters5());
		dynavalidatorform1.set("B_parameters6",
				incineratorvo.getB_parameters6());
		dynavalidatorform1.set("B_parameters7",
				incineratorvo.getB_parameters7());
		dynavalidatorform1.set("B_parameters8",
				incineratorvo.getB_parameters8());
		dynavalidatorform1.set("B_parameters9",
				incineratorvo.getB_parameters9());
		dynavalidatorform1.set("iwastequantity",
				incineratorvo.getIwastequantity());
		dynavalidatorform1.set("isolidwaste", incineratorvo.getIsolidwaste());
		dynavalidatorform1
				.set("i_SolidIssuedDate", UtilityObject
						.convertYyyyMmDd2MmDdYyyy(incineratorvo
								.getI_SolidIssuedDate()));
		dynavalidatorform1.set("i_SolidExpirationDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(incineratorvo
						.getI_SolidExpirationDate()));
		dynavalidatorform1.set("icomments", incineratorvo.getIcomments());
		dynavalidatorform1.set("iwastelimit", incineratorvo.getIwastelimit());
		dynavalidatorform1.set("dobsignoff", incineratorvo.getDobsignoff());
		try {
			int i = -99;
			int j = -99;
			i = incineratorvo.getLocation();
			j = incineratorvo.getWasteBurned();
			System.out.println(j + "ww" + i);
			if (!userAction.equalsIgnoreCase("edit")) {
				if (i != -1)
					dynavalidatorform1.set("incLocation", AgencyLocationEnum
							.get(i).toString());

				if (j != -1)
					dynavalidatorform1.set("incWasteBurnType",
							IncineratorWasteTypeEnum.get(j).toString());
			} else {
				dynavalidatorform1.set("incLocation", String.valueOf(i));
				dynavalidatorform1.set("incWasteBurnType", String.valueOf(j));
			}

		} catch (Exception e) {
			System.out.println("Exception In incLocation:" + e);
		}

		List list1 = incineratorvo.getFuelConsumptionList();

		if (list1 != null && list1.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list1);
			httpservletrequest.setAttribute("hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FUEL_CONSUMPTION", list1);
		} else {
			httpservletrequest
					.setAttribute("FUEL_CONSUMPTION", new ArrayList());
			httpservletrequest.setAttribute("hdnConsumption", "");
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		setDropDown(httpservletrequest);
		refreshShowInfo(httpservletrequest, incineratorvo);
		return dynavalidatorform1;
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("IncineratorAction - In Add");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		IncineratorVo incineratorvo = new IncineratorVo();
		incineratorvo.setBuildingId(buildingvo.getId());
		incineratorvo.setType(ResourcesTypeEnum.INCINERATOR.getCode());
		incineratorvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("incId"));
		incineratorvo.setFloor((String) dynavalidatorform.get("incFloor"));
		incineratorvo.setModel((String) dynavalidatorform.get("incModel"));
		incineratorvo.setMake((String) dynavalidatorform.get("incMake"));
		incineratorvo
				.setSerialNo((String) dynavalidatorform.get("incSerialNo"));
		incineratorvo.setYearInstalled((String) dynavalidatorform
				.get("incYearIns"));
		incineratorvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("inc_status")));
		incineratorvo.setDisconnecteddate((String) dynavalidatorform
				.get("inc_disconnecteddate"));
		incineratorvo.setBurnerCapacity((String) dynavalidatorform
				.get("incBurner"));
		incineratorvo.setBurnerMake((String) dynavalidatorform
				.get("incBurnMake"));
		incineratorvo.setBurnerModel((String) dynavalidatorform
				.get("incBurnModel"));
		incineratorvo
				.setCapacity((String) dynavalidatorform.get("incCapacity"));
		String s2 = (String) dynavalidatorform.get("incStack");
		if (UtilityObject.isNotEmpty(s2))
			incineratorvo.setStackFrom(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("incScrubber");
		if (s2.equals("Y"))
			incineratorvo.setScrubberInst(true);
		else
			incineratorvo.setScrubberInst(false);
		s2 = (String) dynavalidatorform.get("incDOB");
		if (s2.equals("Y"))
			incineratorvo.setDOBApproved(true);
		else
			incineratorvo.setDOBApproved(false);
		s2 = (String) dynavalidatorform.get("incCOMonitor");

		incineratorvo.setCOMonitor(s2);

		s2 = (String) dynavalidatorform.get("incOpacity");

		incineratorvo.setOpacityInstalled(s2);

		s2 = (String) dynavalidatorform.get("incO2Monitor");

		incineratorvo.setO2Installed(s2);

		s2 = (String) dynavalidatorform.get("incOpacityChart");
		if (s2.equals("Y"))
			incineratorvo.setOpacityChartAvailable(true);
		else
			incineratorvo.setOpacityChartAvailable(false);
		s2 = (String) dynavalidatorform.get("incQuarterlyCGA");
		if (s2.equals("Y"))
			incineratorvo.setCGARequired(true);
		else
			incineratorvo.setCGARequired(false);
		incineratorvo.setMEA((String) dynavalidatorform.get("incMea"));
		incineratorvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("incLocation")));
		incineratorvo.setWasteBurned(Integer
				.parseInt((String) dynavalidatorform.get("incWasteBurnType")));

		incineratorvo.setTypeofunit((String) dynavalidatorform
				.get("typeofunit"));
		incineratorvo.setDobno((String) dynavalidatorform.get("dobno"));
		incineratorvo.setTeperaturerequired((String) dynavalidatorform
				.get("teperaturerequired"));
		incineratorvo.setFacilitysecondary((String) dynavalidatorform
				.get("facilitysecondary"));
		incineratorvo.setFacilityprimary((String) dynavalidatorform
				.get("facilityprimary"));
		incineratorvo.setPrimarytemp((String) dynavalidatorform
				.get("primarytemp"));
		incineratorvo.setSectemp((String) dynavalidatorform.get("sectemp"));
		incineratorvo.setDeprequired((String) dynavalidatorform
				.get("deprequired"));
		incineratorvo.setDepno((String) dynavalidatorform.get("depno"));
		incineratorvo.setDecno((String) dynavalidatorform.get("decno"));
		incineratorvo.setIncStackTested((String) dynavalidatorform
				.get("incStackTested"));
		incineratorvo.setI_decPermitObtained((String) dynavalidatorform
				.get("i_decPermitObtained"));
		incineratorvo.setProtocolSubmitted((String) dynavalidatorform
				.get("protocolSubmitted"));
		incineratorvo.setTestConductedBy((String) dynavalidatorform
				.get("testConductedBy"));
		incineratorvo.setTestReportSubmited((String) dynavalidatorform
				.get("testReportSubmited"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("testReportSubmitedDate")), "yyyy-MM-dd");

		incineratorvo.setTestReportSubmitedDate(sd);
		incineratorvo.setRetestPlanned((String) dynavalidatorform
				.get("retestPlanned"));
		incineratorvo.setI_testPassed((String) dynavalidatorform
				.get("i_testPassed"));
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("i_StackTestDate")), "yyyy-MM-dd");
		incineratorvo.setI_StackTestDate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynavalidatorform
						.get("nextStackTest")), "yyyy-MM-dd");
		incineratorvo.setNextStackTest(sd);
		incineratorvo.setNextStackTestNote((String) dynavalidatorform
				.get("nextStackTestNote"));
		incineratorvo.setB_parameters1((String) dynavalidatorform
				.get("B_parameters1"));
		incineratorvo.setB_parameters2((String) dynavalidatorform
				.get("B_parameters2"));
		incineratorvo.setB_parameters3((String) dynavalidatorform
				.get("B_parameters3"));
		incineratorvo.setB_parameters4((String) dynavalidatorform
				.get("B_parameters4"));
		incineratorvo.setB_parameters5((String) dynavalidatorform
				.get("B_parameters5"));
		incineratorvo.setB_parameters6((String) dynavalidatorform
				.get("B_parameters6"));
		incineratorvo.setB_parameters7((String) dynavalidatorform
				.get("B_parameters7"));
		incineratorvo.setB_parameters8((String) dynavalidatorform
				.get("B_parameters8"));
		incineratorvo.setB_parameters9((String) dynavalidatorform
				.get("B_parameters9"));
		incineratorvo.setIwastequantity((String) dynavalidatorform
				.get("iwastequantity"));
		incineratorvo.setIsolidwaste((String) dynavalidatorform
				.get("isolidwaste"));

		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("i_SolidIssuedDate")), "yyyy-MM-dd");
		incineratorvo.setI_SolidIssuedDate(sd);

		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("i_SolidExpirationDate")), "yyyy-MM-dd");
		incineratorvo.setI_SolidExpirationDate(sd);
		incineratorvo.setSolidWasteExpirationNote((String) dynavalidatorform
				.get("solidWasteExpirationNote"));
		incineratorvo.setIcomments((String) dynavalidatorform.get("icomments"));
		incineratorvo.setIwastelimit((String) dynavalidatorform
				.get("iwastelimit"));
		incineratorvo.setDobsignoff((String) dynavalidatorform
				.get("dobsignoff"));

		byte byte0 = -99;
		try {
			int i = IncineratorEntity.add(incineratorvo);
			incineratorvo = IncineratorEntity.findByPrimaryKey(i);
			if (incineratorvo != null) {
				httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
				dynavalidatorform = setFormVariable(dynavalidatorform,
						incineratorvo, httpservletrequest);
			}
			s = "Added Incinerator Successfully.";
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
		refreshShowInfo(httpservletrequest, incineratorvo);
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("IncineratorAction - In Update");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String facilityDesignatedid = incineratorvo.getFacilityDesignatedId();

		incineratorvo.setBuildingId(buildingvo.getId());
		incineratorvo.setType(ResourcesTypeEnum.INCINERATOR.getCode());
		incineratorvo.setFacilityDesignatedId((String) dynavalidatorform
				.get("incId"));
		incineratorvo.setFloor((String) dynavalidatorform.get("incFloor"));
		incineratorvo.setModel((String) dynavalidatorform.get("incModel"));
		incineratorvo.setMake((String) dynavalidatorform.get("incMake"));
		incineratorvo
				.setSerialNo((String) dynavalidatorform.get("incSerialNo"));
		incineratorvo.setYearInstalled((String) dynavalidatorform
				.get("incYearIns"));
		incineratorvo.setStatus(Integer.parseInt((String) dynavalidatorform
				.get("inc_status")));
		incineratorvo.setDisconnecteddate((String) dynavalidatorform
				.get("inc_disconnecteddate"));
		incineratorvo.setBurnerCapacity((String) dynavalidatorform
				.get("incBurner"));
		incineratorvo.setBurnerMake((String) dynavalidatorform
				.get("incBurnMake"));
		incineratorvo.setBurnerModel((String) dynavalidatorform
				.get("incBurnModel"));
		incineratorvo
				.setCapacity((String) dynavalidatorform.get("incCapacity"));
		String s2 = (String) dynavalidatorform.get("incStack");
		if (UtilityObject.isNotEmpty(s2))
			incineratorvo.setStackFrom(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("incScrubber");
		if (s2.equals("Y"))
			incineratorvo.setScrubberInst(true);
		else
			incineratorvo.setScrubberInst(false);
		s2 = (String) dynavalidatorform.get("incDOB");
		if (s2.equals("Y"))
			incineratorvo.setDOBApproved(true);
		else
			incineratorvo.setDOBApproved(false);

		s2 = (String) dynavalidatorform.get("incCOMonitor");

		incineratorvo.setCOMonitor(s2);

		s2 = (String) dynavalidatorform.get("incOpacity");

		incineratorvo.setOpacityInstalled(s2);

		s2 = (String) dynavalidatorform.get("incO2Monitor");

		incineratorvo.setO2Installed(s2);

		s2 = (String) dynavalidatorform.get("incOpacityChart");
		if (s2.equals("Y"))
			incineratorvo.setOpacityChartAvailable(true);
		else
			incineratorvo.setOpacityChartAvailable(false);
		s2 = (String) dynavalidatorform.get("incQuarterlyCGA");
		if (s2.equals("Y"))
			incineratorvo.setCGARequired(true);
		else
			incineratorvo.setCGARequired(false);
		incineratorvo.setMEA((String) dynavalidatorform.get("incMea"));
		incineratorvo.setLocation(Integer.parseInt((String) dynavalidatorform
				.get("incLocation")));
		incineratorvo.setWasteBurned(Integer
				.parseInt((String) dynavalidatorform.get("incWasteBurnType")));

		incineratorvo.setTypeofunit((String) dynavalidatorform
				.get("typeofunit"));
		incineratorvo.setDobno((String) dynavalidatorform.get("dobno"));
		incineratorvo.setTeperaturerequired((String) dynavalidatorform
				.get("teperaturerequired"));
		incineratorvo.setFacilitysecondary((String) dynavalidatorform
				.get("facilitysecondary"));
		incineratorvo.setFacilityprimary((String) dynavalidatorform
				.get("facilityprimary"));
		incineratorvo.setPrimarytemp((String) dynavalidatorform
				.get("primarytemp"));
		incineratorvo.setSectemp((String) dynavalidatorform.get("sectemp"));
		incineratorvo.setDeprequired((String) dynavalidatorform
				.get("deprequired"));
		incineratorvo.setDepno((String) dynavalidatorform.get("depno"));
		incineratorvo.setDecno((String) dynavalidatorform.get("decno"));
		incineratorvo.setIncStackTested((String) dynavalidatorform
				.get("incStackTested"));
		incineratorvo.setI_decPermitObtained((String) dynavalidatorform
				.get("i_decPermitObtained"));
		incineratorvo.setProtocolSubmitted((String) dynavalidatorform
				.get("protocolSubmitted"));
		incineratorvo.setTestConductedBy((String) dynavalidatorform
				.get("testConductedBy"));
		incineratorvo.setTestReportSubmited((String) dynavalidatorform
				.get("testReportSubmited"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("testReportSubmitedDate")), "yyyy-MM-dd");

		incineratorvo.setTestReportSubmitedDate(sd);
		incineratorvo.setRetestPlanned((String) dynavalidatorform
				.get("retestPlanned"));
		incineratorvo.setI_testPassed((String) dynavalidatorform
				.get("i_testPassed"));
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("i_StackTestDate")), "yyyy-MM-dd");
		incineratorvo.setI_StackTestDate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynavalidatorform
						.get("nextStackTest")), "yyyy-MM-dd");
		incineratorvo.setNextStackTest(sd);
		incineratorvo.setNextStackTestNote((String) dynavalidatorform
				.get("nextStackTestNote"));
		incineratorvo.setB_parameters1((String) dynavalidatorform
				.get("B_parameters1"));
		incineratorvo.setB_parameters2((String) dynavalidatorform
				.get("B_parameters2"));
		incineratorvo.setB_parameters3((String) dynavalidatorform
				.get("B_parameters3"));
		incineratorvo.setB_parameters4((String) dynavalidatorform
				.get("B_parameters4"));
		incineratorvo.setB_parameters5((String) dynavalidatorform
				.get("B_parameters5"));
		incineratorvo.setB_parameters6((String) dynavalidatorform
				.get("B_parameters6"));
		incineratorvo.setB_parameters7((String) dynavalidatorform
				.get("B_parameters7"));
		incineratorvo.setB_parameters8((String) dynavalidatorform
				.get("B_parameters8"));
		incineratorvo.setB_parameters9((String) dynavalidatorform
				.get("B_parameters9"));
		incineratorvo.setIwastequantity((String) dynavalidatorform
				.get("iwastequantity"));
		incineratorvo.setIsolidwaste((String) dynavalidatorform
				.get("isolidwaste"));
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("i_SolidIssuedDate")), "yyyy-MM-dd");
		incineratorvo.setI_SolidIssuedDate(sd);
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("i_SolidExpirationDate")), "yyyy-MM-dd");
		incineratorvo.setI_SolidExpirationDate(sd);
		incineratorvo.setSolidWasteExpirationNote((String) dynavalidatorform
				.get("solidWasteExpirationNote"));
		incineratorvo.setIcomments((String) dynavalidatorform.get("icomments"));
		incineratorvo.setIwastelimit((String) dynavalidatorform
				.get("iwastelimit"));
		incineratorvo.setDobsignoff((String) dynavalidatorform
				.get("dobsignoff"));
		try {
			IncineratorEntity.update(incineratorvo);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/incinerator/" + incineratorvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/incinerator/"
									+ incineratorvo.getId()
									+ "-"
									+ incineratorvo.getFacilityDesignatedId()
											.trim());
					f.renameTo(newFileName);

				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

			httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
			dynavalidatorform = setFormVariable(dynavalidatorform,
					incineratorvo, httpservletrequest);
			s = "Updated Incinerator Successfully.";
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
		refreshShowInfo(httpservletrequest, incineratorvo);
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		try {
			log.debug("Incinerator - In View");
			DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynavalidatorform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			IncineratorVo incineratorvo = IncineratorEntity.findByPrimaryKey(i);
			if (incineratorvo != null)
				httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
			BuildingVo buildingvo = (BuildingVo) httpsession
					.getAttribute("BUILDING_OBJECT");
			IncineratorVo incineratorvo1 = (IncineratorVo) httpsession
					.getAttribute("INCINERATOR_OBJECT");
			IncineratorEntity.delete(incineratorvo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/incinerator/" + incineratorvo1.getId() + "-"
						+ incineratorvo1.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successs3");
	}

	public ActionForward dep(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator DEP");
		log.debug("IncineratorAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int i = -99;
		i = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Incinerator")
				.getCode());
		s1 = (String) dynavalidatorform.get("incDEPIssueDate");
		s2 = (String) dynavalidatorform.get("incDEPExpirationDate");
		s3 = (String) dynavalidatorform.get("incDEPExpirationDateNote");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		permitinfovo.setNote(s3);
		IncineratorEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incDEPIssueDate", "");
		dynavalidatorform.set("incDEPExpirationDate", "");
		dynavalidatorform.set("incDEPExpirationDateNote", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator DEP Update");
		log.debug("IncineratorAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
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
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Incinerator")
				.getCode());
		s2 = (String) dynavalidatorform.get("incDEPIssueDate");
		s3 = (String) dynavalidatorform.get("incDEPExpirationDate");
		s4 = (String) dynavalidatorform.get("incDEPExpirationDateNote");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setNote(s4);
		permitinfovo.setId(Integer.parseInt(s));
		IncineratorEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incDEPIssueDate", "");
		dynavalidatorform.set("incDEPExpirationDate", "");
		dynavalidatorform.set("incDEPExpirationDateNote", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator DEP delete");
		log.debug("IncineratorAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
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
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setId(Integer.parseInt(s));
		IncineratorEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incDEPIssueDate", "");
		dynavalidatorform.set("incDEPExpirationDate", "");
		dynavalidatorform.set("incDEPExpirationDateNote", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
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
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumption(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator Consumption");
		log.debug("IncineratorAction - In Consumption");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = "";
		String s1 = "";
		IncConsumptionVo incconsumptionvo = new IncConsumptionVo();
		incconsumptionvo.setObjectId(incineratorvo.getId());
		s = (String) dynavalidatorform.get("incAnnualYear");
		s1 = (String) dynavalidatorform.get("incAnnualConsumption");
		incconsumptionvo.setYear(s);
		incconsumptionvo.setConsumption(s1);
		IncineratorEntity.addWasteConsum(incconsumptionvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incAnnualYear", "");
		dynavalidatorform.set("incAnnualConsumption", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumptionUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator Consumption Update");
		log.debug("IncineratorAction - In Consumption");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("LandfillAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Consumption Permit Id is null while updating the Consumption Permit");
		String s1 = "";
		String s2 = "";
		IncConsumptionVo incconsumptionvo = new IncConsumptionVo();
		incconsumptionvo.setObjectId(incineratorvo.getId());
		s1 = (String) dynavalidatorform.get("incAnnualYear");
		s2 = (String) dynavalidatorform.get("incAnnualConsumption");
		incconsumptionvo.setYear(s1);
		incconsumptionvo.setConsumption(s2);
		incconsumptionvo.setId(Integer.parseInt(s));
		IncineratorEntity.updateWasteConsum(incconsumptionvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incAnnualYear", "");
		dynavalidatorform.set("incAnnualConsumption", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumptionDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator Consumption Delete");
		log.debug("IncineratorAction - In Consumption");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("LandfillAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Consumption Permit Id is null while updating the Consumption Permit");
		String s1 = "";
		String s2 = "";
		IncConsumptionVo incconsumptionvo = new IncConsumptionVo();
		incconsumptionvo.setObjectId(incineratorvo.getId());
		incconsumptionvo.setId(Integer.parseInt(s));
		IncineratorEntity.deleteWasteConsum(incconsumptionvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incAnnualYear", "");
		dynavalidatorform.set("incAnnualConsumption", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumptionEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("Consumption Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_CONS_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward cga(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator CGA");
		log.debug("IncineratorAction - In CGA");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = -99;
		i = DepartmentEnum.STATE_AGENCY.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Incinerator")
				.getCode());
		s = (String) dynavalidatorform.get("incCGA");
		s1 = (String) dynavalidatorform.get("incCGADate");
		s2 = (String) dynavalidatorform.get("incCGANext");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		IncineratorEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incCGADate", "");
		dynavalidatorform.set("incCGA", "");
		dynavalidatorform.set("incCGANext", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward cgaEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("CGA Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_CGA_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward cgaDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator CGA Update");
		log.debug("IncineratorAction - In CGA Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("Incinerator - In updateCGAPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"CGA Permit Id is null while updating the CGA Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		j = DepartmentEnum.STATE_AGENCY.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setId(Integer.parseInt(s));
		IncineratorEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incCGADate", "");
		dynavalidatorform.set("incCGA", "");
		dynavalidatorform.set("incCGANext", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward cgaUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator CGA Update");
		log.debug("IncineratorAction - In CGA Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("Incinerator - In updateCGAPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"CGA Permit Id is null while updating the CGA Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		j = DepartmentEnum.STATE_AGENCY.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Incinerator")
				.getCode());
		s1 = (String) dynavalidatorform.get("incCGA");
		s2 = (String) dynavalidatorform.get("incCGADate");
		s3 = (String) dynavalidatorform.get("incCGANext");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setId(Integer.parseInt(s));
		IncineratorEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incCGADate", "");
		dynavalidatorform.set("incCGA", "");
		dynavalidatorform.set("incCGANext", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward stackTestDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator StackTest");
		log.debug("IncineratorAction - In StackTest");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("IncineratorAction - In stackTest - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Stack Permit Id is null while updating the Stack Permit");
		StackTestVo stacktestvo = new StackTestVo();
		stacktestvo.setIncineratorId(incineratorvo.getId());
		log.debug((new StringBuilder())
				.append("IncineratorAction - In StackTest IncinID = ")
				.append(incineratorvo.getId()).toString());
		stacktestvo.setId(Integer.parseInt(s));
		IncineratorEntity.deleteStackTestInfo(stacktestvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		resetStackTestVars(dynavalidatorform);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward stackTestUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator StackTest");
		log.debug("IncineratorAction - In StackTest");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("IncineratorAction - In stackTest - ID = ").append(s)
				.toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Stack Permit Id is null while updating the Stack Permit");
		StackTestVo stacktestvo = new StackTestVo();
		stacktestvo.setIncineratorId(incineratorvo.getId());
		log.debug((new StringBuilder())
				.append("IncineratorAction - In StackTest IncinID = ")
				.append(incineratorvo.getId()).toString());
		stacktestvo.setYear((String) dynavalidatorform.get("incStackYear"));
		stacktestvo.setTestAgency((String) dynavalidatorform
				.get("incStackTested"));
		stacktestvo.setBoilerTest((String) dynavalidatorform
				.get("incStackBoilers"));
		stacktestvo.setParam1((String) dynavalidatorform.get("incStackParam1"));
		stacktestvo.setParam2((String) dynavalidatorform.get("incStackParam2"));
		stacktestvo.setParam3((String) dynavalidatorform.get("incStackParam3"));
		stacktestvo.setTestOnNaturalGas((String) dynavalidatorform
				.get("incStackTest"));
		String s1 = (String) dynavalidatorform.get("incStackOilType");
		stacktestvo.setTestFuel(Integer.parseInt(s1));
		stacktestvo.setProtocolSubmitted((String) dynavalidatorform
				.get("incStackProtocol"));
		stacktestvo.setConductedBy((String) dynavalidatorform
				.get("incStackConductedBy"));
		stacktestvo.setReportSubmitted((String) dynavalidatorform
				.get("incStackReport"));
		String s2 = (String) dynavalidatorform.get("incStackSubmittedDate");
		stacktestvo.setProSubmissionDate(UtilityObject.convertToDate(s2));
		stacktestvo.setTestPassed((String) dynavalidatorform
				.get("incStackBoiler"));
		stacktestvo.setReTest((String) dynavalidatorform.get("incStackReTest"));
		stacktestvo.setNextTestDate(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("incStackTestDate")));
		stacktestvo.setId(Integer.parseInt(s));
		IncineratorEntity.updateStackTestInfo(stacktestvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		resetStackTestVars(dynavalidatorform);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward stackTest(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator StackTest Update");
		log.debug("IncineratorAction - In StackTest Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		StackTestVo stacktestvo = new StackTestVo();
		stacktestvo.setIncineratorId(incineratorvo.getId());
		log.debug((new StringBuilder())
				.append("IncineratorAction - In StackTest IncinID = ")
				.append(incineratorvo.getId()).toString());
		stacktestvo.setYear((String) dynavalidatorform.get("incStackYear"));
		stacktestvo.setTestAgency(((String) dynavalidatorform
				.get("incStackTested")).equals("1") ? "Y" : "N");
		stacktestvo.setBoilerTest(((String) dynavalidatorform
				.get("incStackBoilers")).equals("1") ? "Y" : "N");
		stacktestvo
				.setParam1(((String) dynavalidatorform.get("incStackParam1"))
						.equals("on") ? "Y" : "N");
		stacktestvo
				.setParam2(((String) dynavalidatorform.get("incStackParam2"))
						.equals("on") ? "Y" : "N");
		stacktestvo
				.setParam3(((String) dynavalidatorform.get("incStackParam3"))
						.equals("on") ? "Y" : "N");
		stacktestvo.setTestOnNaturalGas(((String) dynavalidatorform
				.get("incStackTest")).equals("1") ? "Y" : "N");
		String s = (String) dynavalidatorform.get("incStackOilType");
		System.out.println((new StringBuilder()).append("fdgfdgdf").append(s)
				.toString());
		stacktestvo.setTestFuel(Integer.parseInt(s));
		stacktestvo.setProtocolSubmitted(((String) dynavalidatorform
				.get("incStackProtocol")).equals("1") ? "Y" : "N");
		stacktestvo.setConductedBy((String) dynavalidatorform
				.get("incStackConductedBy"));
		stacktestvo.setReportSubmitted(((String) dynavalidatorform
				.get("incStackReport")).equals("1") ? "Y" : "N");
		String s1 = (String) dynavalidatorform.get("incStackSubmittedDate");
		stacktestvo.setProSubmissionDate(UtilityObject.convertToDate(s1));
		stacktestvo.setTestPassed(((String) dynavalidatorform
				.get("incStackBoiler")).equals("1") ? "Y" : "N");
		stacktestvo
				.setReTest(((String) dynavalidatorform.get("incStackReTest"))
						.equals("1") ? "Y" : "N");
		stacktestvo.setNextTestDate(UtilityObject
				.convertToDate((String) dynavalidatorform
						.get("incStackTestDate")));
		IncineratorEntity.addStackTestInfo(stacktestvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		resetStackTestVars(dynavalidatorform);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward stackEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("Stack Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_STACK_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public void resetStackTestVars(DynaValidatorForm dynavalidatorform) {
		dynavalidatorform.set("incId", "");
		dynavalidatorform.set("incStackYear", "");
		dynavalidatorform.set("incStackTested", "");
		dynavalidatorform.set("incStackBoilers", "");
		dynavalidatorform.set("incStackParam1", "");
		dynavalidatorform.set("incStackParam2", "");
		dynavalidatorform.set("incStackParam3", "");
		dynavalidatorform.set("incStackTest", "");
		dynavalidatorform.set("incStackOilType", "");
		dynavalidatorform.set("incStackProtocol", "");
		dynavalidatorform.set("incStackConductedBy", "");
		dynavalidatorform.set("incStackReport", "");
		dynavalidatorform.set("incStackSubmittedDate", "");
		dynavalidatorform.set("incStackBoiler", "");
		dynavalidatorform.set("incStackReTest", "");
		dynavalidatorform.set("incStackTestDate", "");
	}

	public ActionForward location(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator Loc");
		log.debug("IncineratorAction - In LOC");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
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
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Incinerator")
				.getCode());
		s1 = (String) dynavalidatorform.get("incLocIssueDate");
		s2 = (String) dynavalidatorform.get("incLocExpirationDate");
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		IncineratorEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incLocIssueDate", "");
		dynavalidatorform.set("incLocExpirationDate", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward locationEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("Incinerator Loc Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_LOC_PERMIT", "Y");
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward locationDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator Loc Update");
		log.debug("IncineratorAction - In LOC Update ");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
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
		permitinfovo.setId(Integer.parseInt(s));
		IncineratorEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incLocIssueDate", "");
		dynavalidatorform.set("incLocExpirationDate", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward locationUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Incinerator Loc Update");
		log.debug("IncineratorAction - In LOC Update ");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
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
		permitinfovo.setObjectId(incineratorvo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.parse("Incinerator")
				.getCode());
		s2 = (String) dynavalidatorform.get("incLocIssueDate");
		s3 = (String) dynavalidatorform.get("incLocExpirationDate");
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setId(Integer.parseInt(s));
		IncineratorEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, incineratorvo,
				httpservletrequest);
		dynavalidatorform.set("incLocIssueDate", "");
		dynavalidatorform.set("incLocExpirationDate", "");
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Incinerator - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		IncineratorVo incineratorvo = IncineratorEntity.findByPrimaryKey(i);
		if (incineratorvo != null) {
			httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
			dynavalidatorform = setFormVariable(dynavalidatorform,
					incineratorvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get IncineratorVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Incinerator - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		IncineratorVo incineratorvo = IncineratorEntity.findByPrimaryKey(i);
		if (incineratorvo != null) {
			httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
			dynavalidatorform = setFormVariableforedit(dynavalidatorform,
					incineratorvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get IncineratorVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
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
		log.debug("Incinerator - In viewexist");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		IncineratorVo incineratorvo = IncineratorEntity.findByPrimaryKey(i);
		if (incineratorvo != null) {
			httpsession.setAttribute("INCINERATOR_OBJECT", incineratorvo);
			dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
					incineratorvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get IncineratorVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, incineratorvo);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	private void refreshShowInfo(HttpServletRequest httpservletrequest,
			IncineratorVo incineratorvo) {
		httpservletrequest.setAttribute("INC_DEP", "inline");
		int i = incineratorvo.getLocation();
		if (i == AgencyLocationEnum.RCDOH.getCode()
				|| i == AgencyLocationEnum.WCDOH.getCode())
			httpservletrequest.setAttribute("INC_LOC", "inline");
		else
			httpservletrequest.setAttribute("INC_LOC", "none");
		if (incineratorvo.isCGARequired())
			httpservletrequest.setAttribute("INC_CGA", "inline");
		else
			httpservletrequest.setAttribute("INC_CGA", "none");
		httpservletrequest.setAttribute("INC_CONS", "inline");
		httpservletrequest.setAttribute("INC_STACK", "inline");
		httpservletrequest.setAttribute("LOCATION",
				AgencyLocationEnum.get(incineratorvo.getLocation()));
		httpservletrequest.setAttribute("INC_CGA_LST", null);
		httpservletrequest.setAttribute("INC_DEP_LST", null);
		httpservletrequest.setAttribute("INC_LOC_LST", null);
		httpservletrequest.setAttribute("INC_CONS_LST", null);
		httpservletrequest.setAttribute("INC_STACK_LST", null);
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		if (incineratorvo == null) {
			log.debug("Incinerator Object is null");
			return;
		}
		incineratorvo.getId();
		incineratorvo.setPermitInfoList(null);
		// incineratorvo.setStackTestList(null);
		// incineratorvo.setConsumptionList(null);
		// ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		// ArrayList arraylist2 = new ArrayList();
		/*
		 * List list = incineratorvo.getStackTestList(); ArrayList arraylist3 =
		 * new ArrayList(); int i = list != null ? list.size() : -1; for(int j =
		 * 0; j < i; j++) { StackTestVo stacktestvo = (StackTestVo)list.get(j);
		 * arraylist3.add(stacktestvo); }
		 * 
		 * List list1 = incineratorvo.getConsumptionList(); ArrayList arraylist4
		 * = new ArrayList(); int k = list != null ? list1.size() : -1; for(int
		 * l = 0; l < k; l++) { IncConsumptionVo incconsumptionvo =
		 * (IncConsumptionVo)list1.get(l); arraylist4.add(incconsumptionvo); }
		 */
		List list2 = incineratorvo.getPermitInfoList();
		int i1 = list2 != null ? list2.size() : -1;
		byte byte0 = -1;
		for (int k1 = 0; k1 < i1; k1++) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list2.get(k1);
			int j1 = permitinfovo.getDepId();
			if (j1 == DepartmentEnum.STATE_AGENCY.getCode()) {
				// arraylist.add(permitinfovo);
				continue;
			}
			if (j1 == DepartmentEnum.DOB.getCode())
				arraylist1.add(permitinfovo);
			// else
			// arraylist2.add(permitinfovo);
		}

		// log.debug((new
		// StringBuilder()).append("INC_DEP_LST size=").append(arraylist1.size()).append(", INC_CGA_LST size=").append(arraylist.size()).append(", INC_LOC_LST size=").append(arraylist2.size()).append(", INC_CONS_LST size=").append(arraylist4.size()).append(", INC_STACK_LST size=").append(arraylist3.size()).toString());
		httpservletrequest.setAttribute("INC_DEP_LST", arraylist1);
		// httpservletrequest.setAttribute("INC_CGA_LST", arraylist);
		// httpservletrequest.setAttribute("INC_LOC_LST", arraylist2);
		// httpservletrequest.setAttribute("INC_CONS_LST", arraylist4);
		// httpservletrequest.setAttribute("INC_STACK_LST", arraylist3);
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		System.out.println("RETURN");
		return actionmapping.findForward("return");
	}

	public ActionForward search(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		System.out.println("SEARCH");
		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		System.out.println("RESET");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("incId", "");
		dynavalidatorform.set("incFloor", "");
		dynavalidatorform.set("incModel", "");
		dynavalidatorform.set("incMake", "");
		dynavalidatorform.set("incSerialNo", "");
		dynavalidatorform.set("incYearIns", "");
		dynavalidatorform.set("inc_status", "");
		dynavalidatorform.set("inc_disconnecteddate", "");
		dynavalidatorform.set("incBurner", "");
		dynavalidatorform.set("incBurnMake", "");
		dynavalidatorform.set("incBurnModel", "");
		dynavalidatorform.set("incWasteBurnType", "");
		dynavalidatorform.set("incCapacity", "");
		dynavalidatorform.set("incScrubber", "");
		dynavalidatorform.set("incDOB", "");
		dynavalidatorform.set("incMea", "");
		dynavalidatorform.set("incLocation", "");
		dynavalidatorform.set("incCOMonitor", "");
		dynavalidatorform.set("incOpacity", "");
		dynavalidatorform.set("incO2Monitor", "");
		dynavalidatorform.set("incOpacityChart", "");
		dynavalidatorform.set("incQuarterlyCGA", "");
		dynavalidatorform.set("incStackFrom", "");
		dynavalidatorform.set("incStack", "");
		httpservletrequest.setAttribute("INC_DEP", "none");
		httpservletrequest.setAttribute("INC_LOC", "none");
		httpservletrequest.setAttribute("INC_CGA", "none");
		httpservletrequest.setAttribute("INC_CONS", "none");
		httpservletrequest.setAttribute("INC_STACK", "none");
		httpservletrequest.setAttribute("INC_CGA_LST", null);
		httpservletrequest.setAttribute("INC_DEP_LST", null);
		httpservletrequest.setAttribute("INC_LOC_LST", null);
		httpservletrequest.setAttribute("INC_CONS_LST", null);
		httpservletrequest.setAttribute("INC_STACK_LST", null);
		httpservletrequest.setAttribute("LOCATION", "");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
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
			IncineratorVo incineratorvo = (IncineratorVo) httpsession
					.getAttribute("INCINERATOR_OBJECT");
			if (incineratorvo != null)
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
		IncineratorVo incineratorvo = (IncineratorVo) httpsession
				.getAttribute("INCINERATOR_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/incinerator/" + incineratorvo.getId() + "-"
					+ incineratorvo.getFacilityDesignatedId().trim());
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
					+ "/incinerator/" + incineratorvo.getId() + "-"
					+ incineratorvo.getFacilityDesignatedId().trim();
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
				+ "/incinerator/" + incineratorvo.getId() + "-"
				+ incineratorvo.getFacilityDesignatedId().trim());

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

	private float getValue(String s, DynaActionForm dynaactionform) {
		String s1 = (String) dynaactionform.get(s);
		if (UtilityObject.isNotEmpty(s1))
			try {
				return Float.parseFloat(s1);
			} catch (NumberFormatException numberformatexception) {
				log.error(numberformatexception);
			}
		return 0f;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.IncineratorAction.class);
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