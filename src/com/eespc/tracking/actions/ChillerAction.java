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
import com.eespc.tracking.bo.ChConsumptionVo;
import com.eespc.tracking.bo.ChillerVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.StorageTankVo;
import com.eespc.tracking.bo.myenum.BoilerUseEnum;
import com.eespc.tracking.bo.myenum.ChillerOperatingEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.FurnaceOilTypeEnum;
import com.eespc.tracking.bo.myenum.ProductStorageEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.bo.myenum.YesNoEnum;
import com.eespc.tracking.entity.ChillerEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class ChillerAction extends LookupDispatchAction {

	public ChillerAction() {
		userAction = null;
	}

	protected Map getKeyMethodMap() {
		HashMap hashmap = new HashMap();
		hashmap.put("paintsprayform.add", "add");
		hashmap.put("paintsprayform.save", "save");
		hashmap.put("paintsprayform.save", "update");
		hashmap.put("paintsprayform.reset", "reset");
		hashmap.put("paintsprayform.dep", "dep");
		hashmap.put("paintsprayform.state", "state");
		return hashmap;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		DynaValidatorForm chForm = (DynaValidatorForm) form;
		userAction = (String) chForm.get("methodToCall");

		log.debug("chillerAction - MethodToCall " + userAction);
		setDropDown(request);
		try {
			if (userAction != null && userAction.equalsIgnoreCase("Save"))
				return save(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("Return"))
				return add(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("Update"))
				return update(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("delete"))
				return delete(mapping, form, request, response);
			if (userAction != null && userAction.equalsIgnoreCase("Reset"))
				return reset(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("Edit"))
				return edit(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("viewexist"))
				return viewexist(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("dep"))
				return dep(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("cons"))
				return consumption(mapping, form, request, response);

			if (userAction != null
					&& userAction.equalsIgnoreCase("consumptionEdit"))
				return consumptionEdit(mapping, form, request, response);

			if (userAction != null
					&& userAction.equalsIgnoreCase("consumptionDelete"))
				return consumptionDelete(mapping, form, request, response);

			if (userAction != null
					&& userAction.equalsIgnoreCase("consumptionUpdate"))
				return consumptionUpdate(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("depEdit"))
				return depEdit(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("depDelete"))
				return depDelete(mapping, form, request, response);

			if (userAction != null && userAction.equalsIgnoreCase("depUpdate"))
				return depUpdate(mapping, form, request, response);

			if (userAction != null
					&& userAction.equalsIgnoreCase("displayControl"))
				return displayControl(mapping, form, request, response);

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

			setDropDown(request);

			reset(mapping, form, request, response);
		}
		// Misplaced declaration of an exception variable
		catch (Exception e) {
			log.error("ChillerAction.execute() + reset exception", e);
		}
		return mapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = new ChillerVo();
		chillervo = (ChillerVo) httpsession.getAttribute("CHILLER_OBJECT");
		setFormVariable(dynaactionform, chillervo, httpservletrequest);

		refreshShowInfo(httpservletrequest, chillervo);
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
		ChillerVo chillervo = new ChillerVo();
		chillervo = (ChillerVo) httpsession.getAttribute("CHILLER_OBJECT");
		setFormVariable(dynaactionform, chillervo, httpservletrequest);

		refreshShowInfo(httpservletrequest, chillervo);
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
		ChillerVo chillervo = new ChillerVo();
		chillervo = (ChillerVo) httpsession.getAttribute("CHILLER_OBJECT");
		setFormVariable(dynaactionform, chillervo, httpservletrequest);

		refreshShowInfo(httpservletrequest, chillervo);
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
		ChillerVo chillervo = new ChillerVo();
		chillervo = (ChillerVo) httpsession.getAttribute("CHILLER_OBJECT");
		setFormVariable(dynaactionform, chillervo, httpservletrequest);

		refreshShowInfo(httpservletrequest, chillervo);
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
		ChillerVo chillervo = new ChillerVo();
		chillervo = (ChillerVo) httpsession.getAttribute("CHILLER_OBJECT");
		setFormVariable(dynaactionform, chillervo, httpservletrequest);

		refreshShowInfo(httpservletrequest, chillervo);
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
		ChillerVo chillervo = new ChillerVo();
		chillervo = (ChillerVo) httpsession.getAttribute("CHILLER_OBJECT");
		setFormVariable(dynaactionform, chillervo, httpservletrequest);

		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/chillerabsorber/"
				+ chillervo.getId() + "-"
				+ chillervo.getFacilityDesignatedId().trim())) {
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

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = YearEnum.getDropDownObj();
		httpservletrequest.setAttribute("YEAR", dropdown);
		dropdown = YesNoEnum.getDropDownObj();
		httpservletrequest.setAttribute("YESNO", dropdown);
		dropdown = ProductStorageEnum.getDropDownObj();
		httpservletrequest.setAttribute("PRODUCT", dropdown);
		com.eespc.tracking.bo.DropDown dropdown1 = BoilerUseEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("BOILER_PRIMARY_USE", dropdown1);
		com.eespc.tracking.bo.DropDown dropdown2 = FurnaceOilTypeEnum
				.getDropDownObj(false);
		httpservletrequest.setAttribute("BOILER_PRIMARY_FUEL", dropdown2);
		com.eespc.tracking.bo.DropDown dropdown3 = FurnaceOilTypeEnum
				.getDropDownObj(false);
		httpservletrequest.setAttribute("BOILER_SECONDARY_FUEL", dropdown3);
		com.eespc.tracking.bo.DropDown dropdown4 = FurnaceOilTypeEnum
				.getDropDownObj(true);
		httpservletrequest.setAttribute("BOILER_OIL_TYPE", dropdown4);
		com.eespc.tracking.bo.DropDown dropdown5 = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown5);
		com.eespc.tracking.bo.DropDown dropdown6 = ChillerOperatingEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("CHILLER_OPERATED_BY", dropdown6);
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

	public DynaValidatorForm setFormVariable(
			DynaValidatorForm dynavalidatorform, ChillerVo chillervo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("chiller setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"chFacId",
				(new StringBuilder())
						.append(chillervo.getFacilityDesignatedId()).append("")
						.toString());
		dynavalidatorform1.set("chFloor",
				(new StringBuilder()).append(chillervo.getFloor()).append("")
						.toString());
		dynavalidatorform1.set("YearInstalled", chillervo.getYearInstalled());
		dynavalidatorform1.set("chModel",
				(new StringBuilder()).append(chillervo.getModel()).append("")
						.toString());
		dynavalidatorform1.set("chMake", chillervo.getMake());
		dynavalidatorform1.set("chSerialNo", chillervo.getSerialNo());
		dynavalidatorform1.set("chCapacity", chillervo.getCapacity());

		dynavalidatorform1.set("chCapacityton", chillervo.getChCapacityton());
		dynavalidatorform1.set("chDEPExpirationDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(chillervo.getChDEPExpirationDate()));
		dynavalidatorform1.set("dobjobnumber", chillervo.getDobjobnumber());
		dynavalidatorform1.set("chFootnote", chillervo.getChFootnote());
		dynavalidatorform1.set("chComments", chillervo.getChComments());
		StackVo stackvo = chillervo.getStackFromObj();
		if (stackvo != null) {
			dynavalidatorform1.set(
					"chStack",
					(new StringBuilder()).append(stackvo.getStackId())
							.append("").toString());
			dynavalidatorform1.set("chStackFrom", stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("chStack", "");
			dynavalidatorform1.set("chStackFrom", "");
		}

		boolean testflag = chillervo.isChillerFuel();
		if (testflag) {

			int i = chillervo.getPrimFuel();
			int j = chillervo.getsecFuel();

			if (chillervo.getBurnerType() == 1)
				dynavalidatorform1.set("chBurnerType", "Y");
			else if (chillervo.getBurnerType() == 2)
				dynavalidatorform1.set("chBurnerType", "N");

			int k = chillervo.getPrimFuel();
			if (k > 0) {
				FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum
						.get(k);
				if (!userAction.equals("viewexist"))
					dynavalidatorform1
							.set("chPrimFuel",
									furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum
											.getName())) : "");
				else
					dynavalidatorform1.set("chPrimFuel", (new StringBuilder())
							.append(k).append("").toString());
			}
			double d = Double.parseDouble(chillervo.getCapacity());
			double d1 = -99D;
			if (d > 0.0D) {
				double d2 = d * 1000D;
				dynavalidatorform1.set("B_naturalGasFiringRate",
						(new StringBuilder()).append(d2).append("").toString());
			}
			k = chillervo.getsecFuel();
			if (k > 0) {
				FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(k);
				if (furnaceoiltypeenum1 != null) {
					if (!userAction.equals("viewexist"))
						dynavalidatorform1.set("chSecFuel",
								furnaceoiltypeenum1.getName());
					else
						dynavalidatorform1.set("chSecFuel",
								(new StringBuilder()).append(k).append("")
										.toString());
					byte byte0 = -99;
					int l = furnaceoiltypeenum1.getCode();
					int i1 = 0;
					if (l == 2)
						i1 = 140;
					else if (l == 3)
						i1 = 145;
					else if (l == 4)
						i1 = 150;
					if (i1 > 0)
						dynavalidatorform1.set(
								"B_oilFiringRate",
								(new StringBuilder())
										.append(round(
												(d * 1000D) / (double) i1, 2))
										.append("").toString());
				}
			}

			StorageTankVo storagetankvo = chillervo.getFuelFromObj();
			if (storagetankvo != null) {
				dynavalidatorform1.set("chFuel",
						(new StringBuilder()).append(storagetankvo.getId())
								.append("").toString());
				dynavalidatorform1.set("chFuelFrom",
						storagetankvo.getFacilityDesignatedId());
			} else {
				dynavalidatorform1.set("chFuel", "");
				dynavalidatorform1.set("chFuelFrom", "");
			}

		}

		else {
			dynavalidatorform1.set("chFuel", "");
			dynavalidatorform1.set("chFuelFrom", "");
			dynavalidatorform1.set("chBurnerType", "");
			dynavalidatorform1.set("B_oilFiringRate", "");
			dynavalidatorform1.set("chPrimFuel", "");
			dynavalidatorform1.set("chSecFuel", "");
		}
		boolean flag = chillervo.isChillerFuel();
		if (flag)
			dynavalidatorform1.set("chFuelAbility", "Y");
		else
			dynavalidatorform1.set("chFuelAbility", "N");
		// dynavalidatorform.set("chDOB",
		// checkNullAndFill(chillervo.isDOBApproved(), ""));

		dynavalidatorform1.set("chDOB", chillervo.isDOBApproved());

		TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
				.get(chillervo.getModifiedInPast());
		if (tankoperatingstatusenum != null)
			dynavalidatorform1.set("ModifiedInPast",
					tankoperatingstatusenum.getName());

		dynavalidatorform1.set("chdepapproval", chillervo.isDEPApproved());

		flag = chillervo.isTitleV();
		if (flag)
			dynavalidatorform1.set("chTitleV", "Y");
		else
			dynavalidatorform1.set("chTitleV", "N");
		dynavalidatorform1.set("chMea", chillervo.getMEA());
		dynavalidatorform1.set("DisconnectedYear",
				chillervo.getDisconnectedYear());
		dynavalidatorform1.set("chactiontaken", chillervo.getActionTaken());
		dynavalidatorform1.set("dobsignoff", chillervo.getDobsignoff());

		ChillerOperatingEnum chilleroperatingenum = ChillerOperatingEnum
				.get(chillervo.getChillerOperated());
		if (chilleroperatingenum != null)
			dynavalidatorform1.set("chillerOperated",
					chilleroperatingenum.getName());

		dynavalidatorform1.set("eupAvailable", chillervo.getEupAvailable());
		setDropDown(httpservletrequest);
		refreshShowInfo(httpservletrequest, chillervo);
		log.debug((new StringBuilder()).append("Chiller VO = ")
				.append(chillervo.toString()).toString());
		log.debug((new StringBuilder())
				.append("Chiller setFormVariable : chForm= ")
				.append(dynavalidatorform1.toString()).toString());
		setScreenInfo(httpservletrequest);
		try {
			reset(httpservletrequest);
		} catch (Exception e) {
			System.out.println(e);
		}

		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforedit(
			DynaValidatorForm dynavalidatorform, ChillerVo chillervo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("chiller setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"chFacId",
				(new StringBuilder())
						.append(chillervo.getFacilityDesignatedId()).append("")
						.toString());
		dynavalidatorform1.set("chFloor",
				(new StringBuilder()).append(chillervo.getFloor()).append("")
						.toString());
		dynavalidatorform1.set("YearInstalled", chillervo.getYearInstalled());
		dynavalidatorform1.set("chModel",
				(new StringBuilder()).append(chillervo.getModel()).append("")
						.toString());
		dynavalidatorform1.set("chMake", chillervo.getMake());
		dynavalidatorform1.set("chSerialNo", chillervo.getSerialNo());
		dynavalidatorform1.set("chCapacity", chillervo.getCapacity());
		dynavalidatorform1.set("chCapacityton", chillervo.getChCapacityton());
		dynavalidatorform1.set("chDEPExpirationDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(chillervo.getChDEPExpirationDate()));
		dynavalidatorform1.set("dobjobnumber", chillervo.getDobjobnumber());
		dynavalidatorform1.set("chComments", chillervo.getChComments());
		dynavalidatorform1.set("chFootnote", chillervo.getChFootnote());

		StackVo stackvo = chillervo.getStackFromObj();
		if (stackvo != null) {
			dynavalidatorform1.set(
					"chStack",
					(new StringBuilder()).append(stackvo.getStackId())
							.append("").toString());
			dynavalidatorform1.set("chStackFrom", stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("chStack", "");
			dynavalidatorform1.set("chStackFrom", "");
		}

		boolean testflag = chillervo.isChillerFuel();
		if (testflag) {

			if (chillervo.getBurnerType() == 1)
				dynavalidatorform1.set("chBurnerType", "Y");
			else if (chillervo.getBurnerType() == 2)
				dynavalidatorform1.set("chBurnerType", "N");
			int k = chillervo.getPrimFuel();
			if (k > 0) {
				FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum
						.get(k);
				if (!userAction.equals("edit"))
					dynavalidatorform1
							.set("chPrimFuel",
									furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum
											.getName())) : "");
				else
					dynavalidatorform1.set("chPrimFuel", (new StringBuilder())
							.append(k).append("").toString());
			}
			double d = Double.parseDouble(chillervo.getCapacity());
			double d1 = -99D;
			if (d > 0.0D) {
				double d2 = d * 1000D;
				dynavalidatorform1.set("B_naturalGasFiringRate",
						(new StringBuilder()).append(d2).append("").toString());
			}
			k = chillervo.getsecFuel();
			if (k > 0) {
				FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(k);
				if (furnaceoiltypeenum1 != null) {
					if (!userAction.equals("edit"))
						dynavalidatorform1.set("chSecFuel",
								furnaceoiltypeenum1.getName());
					else
						dynavalidatorform1.set("chSecFuel",
								(new StringBuilder()).append(k).append("")
										.toString());
					byte byte0 = -99;
					int l = furnaceoiltypeenum1.getCode();
					int i1 = 0;
					if (l == 2)
						i1 = 140;
					else if (l == 3)
						i1 = 145;
					else if (l == 4)
						i1 = 150;
					if (i1 > 0)
						dynavalidatorform1.set(
								"B_oilFiringRate",
								(new StringBuilder())
										.append(round(
												(d * 1000D) / (double) i1, 2))
										.append("").toString());
				}
			}

			StorageTankVo storagetankvo = chillervo.getFuelFromObj();
			if (storagetankvo != null) {
				dynavalidatorform1.set("chFuel",
						(new StringBuilder()).append(storagetankvo.getId())
								.append("").toString());
				dynavalidatorform1.set("chFuelFrom",
						storagetankvo.getFacilityDesignatedId());
			} else {
				dynavalidatorform1.set("chFuel", "");
				dynavalidatorform1.set("chFuelFrom", "");
			}

		}

		else {
			dynavalidatorform1.set("chFuel", "");
			dynavalidatorform1.set("chFuelFrom", "");
			dynavalidatorform1.set("chBurnerType", "");
			dynavalidatorform1.set("B_oilFiringRate", "");
			dynavalidatorform1.set("chPrimFuel", "");
			dynavalidatorform1.set("chSecFuel", "");
		}

		boolean flag = chillervo.isChillerFuel();
		if (flag)
			dynavalidatorform1.set("chFuelAbility", "Y");
		else
			dynavalidatorform1.set("chFuelAbility", "N");

		dynavalidatorform1.set("chDOB", chillervo.isDOBApproved());
		dynavalidatorform1.set("ModifiedInPast",
				(new StringBuilder()).append(chillervo.getModifiedInPast())
						.append("").toString());
		dynavalidatorform1.set("chdepapproval", chillervo.isDEPApproved());

		flag = chillervo.isTitleV();
		if (flag)
			dynavalidatorform1.set("chTitleV", "Y");
		else
			dynavalidatorform1.set("chTitleV", "N");
		dynavalidatorform1.set("chMea", chillervo.getMEA());
		dynavalidatorform1.set("DisconnectedYear",
				chillervo.getDisconnectedYear());
		dynavalidatorform1.set("chactiontaken", chillervo.getActionTaken());
		dynavalidatorform1.set("dobsignoff", chillervo.getDobsignoff());
		dynavalidatorform1.set("chillerOperated",
				(new StringBuilder()).append(chillervo.getChillerOperated())
						.append("").toString());
		dynavalidatorform1.set("eupAvailable", chillervo.getEupAvailable());
		setDropDown(httpservletrequest);
		refreshShowInfo(httpservletrequest, chillervo);
		log.debug((new StringBuilder()).append("Chiller VO = ")
				.append(chillervo.toString()).toString());
		log.debug((new StringBuilder())
				.append("Chiller setFormVariable : chForm= ")
				.append(dynavalidatorform1.toString()).toString());
		setScreenInfo(httpservletrequest);
		try {
			reset(httpservletrequest);
		} catch (Exception e) {
			System.out.println(e);
		}

		return dynavalidatorform1;
	}

	public DynaValidatorForm setFormVariableforsearch(
			DynaValidatorForm dynavalidatorform, ChillerVo chillervo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("chiller setForm variable");
		DynaValidatorForm dynavalidatorform1 = dynavalidatorform;
		dynavalidatorform1.set(
				"chFacId",
				(new StringBuilder())
						.append(chillervo.getFacilityDesignatedId()).append("")
						.toString());
		dynavalidatorform1.set("chFloor",
				(new StringBuilder()).append(chillervo.getFloor()).append("")
						.toString());
		dynavalidatorform1.set("YearInstalled", chillervo.getYearInstalled());
		dynavalidatorform1.set("chModel",
				(new StringBuilder()).append(chillervo.getModel()).append("")
						.toString());
		dynavalidatorform1.set("chMake", chillervo.getMake());
		dynavalidatorform1.set("chSerialNo", chillervo.getSerialNo());
		dynavalidatorform1.set("chCapacity", chillervo.getCapacity());
		dynavalidatorform1.set("chCapacityton", chillervo.getChCapacityton());
		dynavalidatorform1.set("chDEPExpirationDate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(chillervo.getChDEPExpirationDate()));
		dynavalidatorform1.set("dobjobnumber", chillervo.getDobjobnumber());
		dynavalidatorform1.set("chComments", chillervo.getChComments());
		dynavalidatorform1.set("chFootnote", chillervo.getChFootnote());

		StackVo stackvo = chillervo.getStackFromObj();
		if (stackvo != null) {
			dynavalidatorform1.set(
					"chStack",
					(new StringBuilder()).append(stackvo.getStackId())
							.append("").toString());
			dynavalidatorform1.set("chStackFrom", stackvo.getFacilityStackId());
		} else {
			dynavalidatorform1.set("chStack", "");
			dynavalidatorform1.set("chStackFrom", "");
		}

		boolean testflag = chillervo.isChillerFuel();
		if (testflag) {

			int i = chillervo.getPrimFuel();
			int j = chillervo.getsecFuel();

			if (chillervo.getBurnerType() == 1)
				dynavalidatorform1.set("chBurnerType", "Y");
			else if (chillervo.getBurnerType() == 2)
				dynavalidatorform1.set("chBurnerType", "N");

			int k = chillervo.getPrimFuel();
			if (k > 0) {
				FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum
						.get(k);
				if (!userAction.equals("viewexist"))
					dynavalidatorform1
							.set("chPrimFuel",
									furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum
											.getName())) : "");
				else
					dynavalidatorform1.set("chPrimFuel", (new StringBuilder())
							.append(k).append("").toString());
			}
			double d = Double.parseDouble(chillervo.getCapacity());
			double d1 = -99D;
			if (d > 0.0D) {
				double d2 = d * 1000D;
				dynavalidatorform1.set("B_naturalGasFiringRate",
						(new StringBuilder()).append(d2).append("").toString());
			}
			k = chillervo.getsecFuel();
			if (k > 0) {
				FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(k);
				if (furnaceoiltypeenum1 != null) {
					if (!userAction.equals("viewexist"))
						dynavalidatorform1.set("chSecFuel",
								furnaceoiltypeenum1.getName());
					else
						dynavalidatorform1.set("chSecFuel",
								(new StringBuilder()).append(k).append("")
										.toString());
					byte byte0 = -99;
					int l = furnaceoiltypeenum1.getCode();
					int i1 = 0;
					if (l == 2)
						i1 = 140;
					else if (l == 3)
						i1 = 145;
					else if (l == 4)
						i1 = 150;
					if (i1 > 0)
						dynavalidatorform1.set(
								"B_oilFiringRate",
								(new StringBuilder())
										.append(round(
												(d * 1000D) / (double) i1, 2))
										.append("").toString());
				}
			}

			StorageTankVo storagetankvo = chillervo.getFuelFromObj();
			if (storagetankvo != null) {
				dynavalidatorform1.set("chFuel",
						(new StringBuilder()).append(storagetankvo.getId())
								.append("").toString());
				dynavalidatorform1.set("chFuelFrom",
						storagetankvo.getFacilityDesignatedId());
			} else {
				dynavalidatorform1.set("chFuel", "");
				dynavalidatorform1.set("chFuelFrom", "");
			}
		}

		else {
			dynavalidatorform1.set("chFuel", "");
			dynavalidatorform1.set("chFuelFrom", "");
			dynavalidatorform1.set("chBurnerType", "");
			dynavalidatorform1.set("B_oilFiringRate", "");
			dynavalidatorform1.set("chPrimFuel", "");
			dynavalidatorform1.set("chSecFuel", "");
		}
		boolean flag = chillervo.isChillerFuel();
		if (flag)
			dynavalidatorform1.set("chFuelAbility", "Y");
		else
			dynavalidatorform1.set("chFuelAbility", "N");

		dynavalidatorform1.set("chDOB", chillervo.isDOBApproved());
		dynavalidatorform1.set("ModifiedInPast",
				(new StringBuilder()).append(chillervo.getModifiedInPast())
						.append("").toString());
		dynavalidatorform1.set("chdepapproval", chillervo.isDEPApproved());

		flag = chillervo.isTitleV();
		if (flag)
			dynavalidatorform1.set("chTitleV", "Y");
		else
			dynavalidatorform1.set("chTitleV", "N");
		dynavalidatorform1.set("chMea", chillervo.getMEA());
		dynavalidatorform1.set("DisconnectedYear",
				chillervo.getDisconnectedYear());
		dynavalidatorform1.set("chactiontaken", chillervo.getActionTaken());
		dynavalidatorform1.set("dobsignoff", chillervo.getDobsignoff());
		dynavalidatorform1.set("chillerOperated",
				(new StringBuilder()).append(chillervo.getChillerOperated())
						.append("").toString());
		dynavalidatorform1.set("eupAvailable", chillervo.getEupAvailable());
		setDropDown(httpservletrequest);
		refreshShowInfo(httpservletrequest, chillervo);
		log.debug((new StringBuilder()).append("Chiller VO = ")
				.append(chillervo.toString()).toString());
		log.debug((new StringBuilder())
				.append("Chiller setFormVariable : chForm= ")
				.append(dynavalidatorform1.toString()).toString());
		return dynavalidatorform1;
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("chillerAction - In Add");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		ChillerVo chillervo = new ChillerVo();
		chillervo.setBuildingId(buildingvo.getId());
		chillervo.setFacilityDesignatedId((String) dynavalidatorform
				.get("chFacId"));
		chillervo.setChType(ResourcesTypeEnum.CHILLER.getCode());
		chillervo.setFloor((String) dynavalidatorform.get("chFloor"));
		chillervo.setYearInstalled((String) dynavalidatorform
				.get("YearInstalled"));
		chillervo.setMake((String) dynavalidatorform.get("chMake"));
		chillervo.setModel((String) dynavalidatorform.get("chModel"));
		chillervo.setSerialNo((String) dynavalidatorform.get("chSerialNo"));
		chillervo.setCapacity((String) dynavalidatorform.get("chCapacity"));
		String s2 = (String) dynavalidatorform.get("chBurnerType");
		if (s2.equals("Y"))
			chillervo.setBurnerType(1);
		else
			chillervo.setBurnerType(2);
		s2 = (String) dynavalidatorform.get("chPrimFuel");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setPrimFuel(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("chSecFuel");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setSecFuel(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("chFuel");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setfuelFrom(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("chStack");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setStackFrom(Integer.parseInt(s2));

		s2 = (String) dynavalidatorform.get("chFuelAbility");
		if (s2.equalsIgnoreCase("Y"))
			chillervo.setChillerFuel(true);
		else
			chillervo.setChillerFuel(false);
		s2 = (String) dynavalidatorform.get("chTitleV");
		if (s2.equals("Y"))
			chillervo.setTitleV(true);
		else
			chillervo.setTitleV(false);
		chillervo.setDOBApproved((String) dynavalidatorform.get("chDOB"));
		chillervo.setDEPApproved((String) dynavalidatorform
				.get("chdepapproval"));
		chillervo.setActionTaken((String) dynavalidatorform
				.get("chactiontaken"));
		chillervo.setMEA((String) dynavalidatorform.get("chMea"));
		chillervo.setModifiedInPast(Integer.parseInt((String) dynavalidatorform
				.get("ModifiedInPast")));
		chillervo.setDisconnectedYear((String) dynavalidatorform
				.get("DisconnectedYear"));
		chillervo.setChCapacityton((String) dynavalidatorform
				.get("chCapacityton"));
		chillervo.setChDEPExpirationDate(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynavalidatorform
						.get("chDEPExpirationDate")), "yyyy-MM-dd"));
		chillervo.setDobjobnumber((String) dynavalidatorform
				.get("dobjobnumber"));
		chillervo.setChFootnote((String) dynavalidatorform.get("chFootnote"));
		chillervo.setChComments((String) dynavalidatorform.get("chComments"));
		chillervo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));

		String ss = (String) dynavalidatorform.get("chillerOperated");
		if (UtilityObject.isNotEmpty(ss))
			chillervo.setChillerOperated(Integer.parseInt(ss));

		// chillervo.setChillerOperated(Integer.parseInt((String)dynavalidatorform.get("chillerOperated")));
		chillervo.setEupAvailable((String) dynavalidatorform
				.get("eupAvailable"));
		byte byte0 = -99;
		try {
			int i = ChillerEntity.add(chillervo);
			chillervo = ChillerEntity.findByPrimaryKey(i);
			if (chillervo != null) {
				httpsession.setAttribute("CHILLER_OBJECT", chillervo);
				dynavalidatorform = setFormVariable(dynavalidatorform,
						chillervo, httpservletrequest);
				s = "Added Chiller Successfully.";
				s1 = "CONFIRMATION";
			}
		} catch (TrackingException trackingexception) {
			log.debug(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		httpservletrequest.setAttribute("isComponentEditable", "N");
		refreshShowInfo(httpservletrequest, chillervo);
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Hi Display");
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
		// setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("chillerAction - In Update");
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String facilityDesignatedid = chillervo.getFacilityDesignatedId();

		chillervo.setBuildingId(buildingvo.getId());
		chillervo.setFacilityDesignatedId((String) dynavalidatorform
				.get("chFacId"));
		chillervo.setChType(ResourcesTypeEnum.CHILLER.getCode());
		chillervo.setFloor((String) dynavalidatorform.get("chFloor"));
		chillervo.setYearInstalled((String) dynavalidatorform
				.get("YearInstalled"));
		chillervo.setMake((String) dynavalidatorform.get("chMake"));
		chillervo.setModel((String) dynavalidatorform.get("chModel"));
		chillervo.setSerialNo((String) dynavalidatorform.get("chSerialNo"));
		chillervo.setCapacity((String) dynavalidatorform.get("chCapacity"));
		String s2 = (String) dynavalidatorform.get("chBurnerType");
		if (s2.equals("Y"))
			chillervo.setBurnerType(1);
		else
			chillervo.setBurnerType(2);

		s2 = (String) dynavalidatorform.get("chPrimFuel");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setPrimFuel(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("chSecFuel");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setSecFuel(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("chFuel");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setfuelFrom(Integer.parseInt(s2));
		s2 = (String) dynavalidatorform.get("chStack");
		if (UtilityObject.isNotEmpty(s2))
			chillervo.setStackFrom(Integer.parseInt(s2));

		s2 = (String) dynavalidatorform.get("chFuelAbility");
		if (s2.equals("Y"))
			chillervo.setChillerFuel(true);
		else
			chillervo.setChillerFuel(false);
		s2 = (String) dynavalidatorform.get("chTitleV");
		if (s2.equals("Y"))
			chillervo.setTitleV(true);
		else
			chillervo.setTitleV(false);

		chillervo.setDOBApproved((String) dynavalidatorform.get("chDOB"));
		chillervo.setDEPApproved((String) dynavalidatorform
				.get("chdepapproval"));
		chillervo.setActionTaken((String) dynavalidatorform
				.get("chactiontaken"));
		chillervo.setMEA((String) dynavalidatorform.get("chMea"));
		String s3 = (String) dynavalidatorform.get("ModifiedInPast");
		chillervo.setModifiedInPast(Integer.parseInt(s3));
		chillervo.setDisconnectedYear((String) dynavalidatorform
				.get("DisconnectedYear"));
		chillervo.setChCapacityton((String) dynavalidatorform
				.get("chCapacityton"));
		chillervo.setChDEPExpirationDate(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynavalidatorform
						.get("chDEPExpirationDate")), "yyyy-MM-dd"));
		chillervo.setDobjobnumber((String) dynavalidatorform
				.get("dobjobnumber"));
		chillervo.setChFootnote((String) dynavalidatorform.get("chFootnote"));
		chillervo.setChComments((String) dynavalidatorform.get("chComments"));
		chillervo.setDobsignoff((String) dynavalidatorform.get("dobsignoff"));

		String ss = (String) dynavalidatorform.get("chillerOperated");
		if (UtilityObject.isNotEmpty(ss))
			chillervo.setChillerOperated(Integer.parseInt(ss));

		/*
		 * String s4 = (String)dynavalidatorform.get("chillerOperated");
		 * chillervo.setChillerOperated(Integer.parseInt(s4));
		 */

		chillervo.setEupAvailable((String) dynavalidatorform
				.get("eupAvailable"));

		byte byte0 = -99;
		try {
			ChillerEntity.update(chillervo);
			if (chillervo != null) {

				FacilityVo facilityvo = (FacilityVo) httpsession
						.getAttribute("FACILITY_OBJECT");
				try {

					File f = new File(httpservletrequest.getRealPath("/")
							+ "/clientfolder/" + facilityvo.getDecId()
							+ "/chillerabsorber/" + chillervo.getId() + "-"
							+ facilityDesignatedid.trim());

					if (f.isDirectory()) {

						File newFileName = new File(
								httpservletrequest.getRealPath("/")
										+ "/clientfolder/"
										+ facilityvo.getDecId()
										+ "/chillerabsorber/"
										+ chillervo.getId()
										+ "-"
										+ chillervo.getFacilityDesignatedId()
												.trim());
						f.renameTo(newFileName);

					} else {
						System.out.println("ha ha ha");
					}
				} catch (Exception e) {
					System.out.println("Exception: " + e);
				}

				httpsession.setAttribute("CHILLER_OBJECT", chillervo);
				dynavalidatorform = setFormVariable(dynavalidatorform,
						chillervo, httpservletrequest);
			}
			s = "Updated Chiller Successfully.";
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
		refreshShowInfo(httpservletrequest, chillervo);
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		try {
			log.debug("chiller - In View");
			DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynavalidatorform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			ChillerVo chillervo = ChillerEntity.findByPrimaryKey(i);
			if (chillervo != null)
				httpsession.setAttribute("CHILLER_OBJECT", chillervo);
			ChillerVo chillervo1 = (ChillerVo) httpsession
					.getAttribute("CHILLER_OBJECT");
			ChillerEntity.delete(chillervo1);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/chillerabsorber/" + chillervo.getId() + "-"
						+ chillervo.getFacilityDesignatedId().trim());
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
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("successss");
	}

	public ActionForward dep(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller DEP");
		log.debug("chillerAction - In DEP");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String s = "";
		String s1 = "";
		String s2 = "";
		int i = -99;
		i = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(i);
		permitinfovo.setObjectId(chillervo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.CHILLER.getCode());
		s1 = (String) dynavalidatorform.get("chDEPIssueDate");
		s2 = (String) dynavalidatorform.get("chDEPExpirationDate");
		permitinfovo.setPermitNumber(s);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s1));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s2));
		ChillerEntity.addPermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		dynavalidatorform.set("chDEPIssueDate", "");
		dynavalidatorform.set("chDEPExpirationDate", "");
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depEdit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("Dep Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		String s = (String) dynavalidatorform.get("hdnPermitId");
		Object obj = null;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_DEP_PERMIT", "Y");
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller DEP Update");
		log.debug("chillerAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("ChillerAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Dep Permit Id is null while updating the DEP Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setId(Integer.parseInt(s));
		ChillerEntity.deletePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		dynavalidatorform.set("chDEPIssueDate", "");
		dynavalidatorform.set("chDEPExpirationDate", "");
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward depUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller DEP Update");
		log.debug("chillerAction - In DEP Update");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("ChillerAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Dep Permit Id is null while updating the DEP Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		int j = -99;
		j = DepartmentEnum.DOB.getCode();
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setDepId(j);
		permitinfovo.setObjectId(chillervo.getId());
		permitinfovo.setObjectType(ResourcesTypeEnum.CHILLER.getCode());
		s2 = (String) dynavalidatorform.get("chDEPIssueDate");
		s3 = (String) dynavalidatorform.get("chDEPExpirationDate");
		permitinfovo.setPermitNumber(s1);
		permitinfovo.setIssueDate(UtilityObject.convertToDate(s2));
		permitinfovo.setExpirationDate(UtilityObject.convertToDate(s3));
		permitinfovo.setId(Integer.parseInt(s));
		ChillerEntity.updatePermitInfo(permitinfovo);
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		dynavalidatorform.set("chDEPIssueDate", "");
		dynavalidatorform.set("chDEPExpirationDate", "");
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumption(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller Consumption");
		log.debug("chillerAction - In Consumption");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String s = "";
		String s1 = "";
		String s3 = "";
		ChConsumptionVo chconsumptionvo = new ChConsumptionVo();
		s = (String) dynavalidatorform.get("chAnnualYear");
		s1 = (String) dynavalidatorform.get("chAnnualConsumption");
		s3 = (String) dynavalidatorform.get("chAnnualConsumptioncfy");
		chconsumptionvo.setObjectId(chillervo.getId());
		chconsumptionvo.setYear(s);
		chconsumptionvo.setConsumption(s1);
		chconsumptionvo.setConsumptioncfy(s3);
		ChillerEntity.addFuelConsumption(chconsumptionvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		dynavalidatorform.set("chAnnualYear", "");
		dynavalidatorform.set("chAnnualConsumption", "");
		dynavalidatorform.set("chAnnualConsumptioncfy", "");
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
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
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("EDIT_CONS_PERMIT", "Y");
		if (s != null && s.trim().equalsIgnoreCase("")) {
			String s1 = (String) httpservletrequest.getAttribute("hdnPermitId");
			httpservletrequest.setAttribute("hdnPermitId", s1);
		} else {
			httpservletrequest.setAttribute("hdnPermitId", s);
		}
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumptionUpdate(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller Update Consumption");
		log.debug("chillerAction - In Update Consumption");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("ChillerAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Inspection Permit Id is null while updating the Inspection Permit");
		String s1 = "";
		String s2 = "";
		String s3 = "";
		ChConsumptionVo chconsumptionvo = new ChConsumptionVo();
		s1 = (String) dynavalidatorform.get("chAnnualYear");
		s2 = (String) dynavalidatorform.get("chAnnualConsumption");
		s3 = (String) dynavalidatorform.get("chAnnualConsumptioncfy");
		chconsumptionvo.setObjectId(chillervo.getId());
		chconsumptionvo.setYear(s1);
		chconsumptionvo.setConsumption(s2);
		chconsumptionvo.setConsumptioncfy(s3);
		chconsumptionvo.setId(Integer.parseInt(s));
		ChillerEntity.updateFuelConsumption(chconsumptionvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		dynavalidatorform.set("chAnnualYear", "");
		dynavalidatorform.set("chAnnualConsumption", "");
		dynavalidatorform.set("chAnnualConsumptioncfy", "");
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward consumptionDelete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller Update Consumption");
		log.debug("chillerAction - In Update Consumption");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		String s = (String) dynavalidatorform.get("hdnPermitId");
		log.debug((new StringBuilder())
				.append("ChillerAction - In updateDepPermitInfo - ID = ")
				.append(s).toString());
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"Inspection Permit Id is null while updating the Inspection Permit");
		String s1 = "";
		String s2 = "";
		ChConsumptionVo chconsumptionvo = new ChConsumptionVo();
		s1 = (String) dynavalidatorform.get("chAnnualYear");
		s2 = (String) dynavalidatorform.get("chAnnualConsumption");
		chconsumptionvo.setObjectId(chillervo.getId());
		chconsumptionvo.setId(Integer.parseInt(s));
		ChillerEntity.deleteFuelConsumption(chconsumptionvo);
		dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
				httpservletrequest);
		dynavalidatorform.set("chAnnualYear", "");
		dynavalidatorform.set("chAnnualConsumption", "");
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "inline");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ChillerVo chillervo = ChillerEntity.findByPrimaryKey(i);
		if (chillervo != null) {
			httpsession.setAttribute("CHILLER_OBJECT", chillervo);
			dynavalidatorform = setFormVariable(dynavalidatorform, chillervo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get chillerVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, chillervo);
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("chiller - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ChillerVo chillervo = ChillerEntity.findByPrimaryKey(i);
		if (chillervo != null) {
			httpsession.setAttribute("CHILLER_OBJECT", chillervo);
			dynavalidatorform = setFormVariableforedit(dynavalidatorform,
					chillervo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get chillerVo for id(").append(s)
					.append(")").toString());
		}
		refreshShowInfo(httpservletrequest, chillervo);
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
		log.debug("chiller - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ChillerVo chillervo = ChillerEntity.findByPrimaryKey(i);
		if (chillervo != null) {
			httpsession.setAttribute("CHILLER_OBJECT", chillervo);
			dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
					chillervo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get chillerVo for id(").append(s)
					.append(")").toString());
		}
		refreshPermitInfo(httpservletrequest);
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	private void refreshShowInfo(HttpServletRequest httpservletrequest,
			ChillerVo chillervo) {
		httpservletrequest.setAttribute("CH_DEP", "inline");
		httpservletrequest.setAttribute("CH_CONS", "inline");
		httpservletrequest.setAttribute("CH_DEP_LST", null);
		httpservletrequest.setAttribute("CH_CONS_LST", null);
	}

	private void refreshPermitInfo(HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		if (chillervo == null) {
			log.debug("chiller Object is null");
			return;
		}
		chillervo.getId();
		chillervo.setPermitInfoList(null);
		chillervo.setConsumptionList(null);
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		ArrayList arraylist2 = new ArrayList();
		Object obj = null;
		ArrayList arraylist3 = new ArrayList();
		List list = chillervo.getConsumptionList();
		ArrayList arraylist4 = new ArrayList();
		int i = list.size();
		for (int j = 0; j < i; j++) {
			ChConsumptionVo chconsumptionvo = (ChConsumptionVo) list.get(j);
			arraylist4.add(chconsumptionvo);
		}

		List list1 = chillervo.getPermitInfoList();
		int k = list1 != null ? list1.size() : -1;
		byte byte0 = -1;
		for (int i1 = 0; i1 < k; i1++) {
			PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(i1);
			int l = permitinfovo.getDepId();
			if (l == DepartmentEnum.DOB.getCode())
				arraylist1.add(permitinfovo);
		}

		log.debug((new StringBuilder()).append("CH_DEP_LST size=")
				.append(arraylist1.size()).append(", CH_CONS_LST size=")
				.append(arraylist4.size()).toString());
		httpservletrequest.setAttribute("CH_DEP_LST", arraylist1);
		httpservletrequest.setAttribute("CH_CONS_LST", arraylist4);
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("RETURN");
		return actionmapping.findForward("return");
	}

	public ActionForward search(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		log.debug("SEARCH");
		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("chFacId", "");
		dynavalidatorform.set("chFloor", "");
		dynavalidatorform.set("YearInstalled", "");
		dynavalidatorform.set("chMake", "");
		dynavalidatorform.set("chModel", "");
		dynavalidatorform.set("chSerialNo", "");
		dynavalidatorform.set("chCapacity", "");
		dynavalidatorform.set("chBurnerType", "");
		dynavalidatorform.set("chPrimFuel", "");
		dynavalidatorform.set("chSecFuel", "");
		dynavalidatorform.set("chFuel", "");
		dynavalidatorform.set("chStack", "");
		dynavalidatorform.set("chFuelAbility", "");
		dynavalidatorform.set("chTitleV", "");
		dynavalidatorform.set("chDOB", "");
		dynavalidatorform.set("chdepapproval", "");
		dynavalidatorform.set("chactiontaken", "");
		dynavalidatorform.set("chMea", "");
		dynavalidatorform.set("ModifiedInPast", "");
		dynavalidatorform.set("DisconnectedYear", "");
		dynavalidatorform.set("chCapacityton", "");
		dynavalidatorform.set("chDEPExpirationDate", "");
		dynavalidatorform.set("dobjobnumber", "");
		dynavalidatorform.set("chFootnote", "");
		dynavalidatorform.set("chComments", "");
		dynavalidatorform.set("dobsignoff", "");
		dynavalidatorform.set("chillerOperated", "");
		dynavalidatorform.set("eupAvailable", "");

		httpservletrequest.setAttribute("CH_DEP", "none");
		httpservletrequest.setAttribute("CH_CONS", "none");
		httpservletrequest.setAttribute("CH_DEP_LST", null);
		httpservletrequest.setAttribute("CH_CONS_LST", null);
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		log.debug((new StringBuilder()).append("BOILER_PRIMARY_FUEL=")
				.append(httpservletrequest.getAttribute("BOILER_PRIMARY_FUEL"))
				.toString());
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
			ChillerVo chillervo = (ChillerVo) httpsession
					.getAttribute("CHILLER_OBJECT");
			if (chillervo != null) {

				httpservletrequest.setAttribute("showAddBtn", "Y");

			} else {

				httpservletrequest.setAttribute("showAddBtn", "N");

			}
		} catch (Exception exception) {
		}
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		ChillerVo chillervo = (ChillerVo) httpsession
				.getAttribute("CHILLER_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/chillerabsorber/" + chillervo.getId() + "-"
					+ chillervo.getFacilityDesignatedId().trim());
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
					+ "/chillerabsorber/" + chillervo.getId() + "-"
					+ chillervo.getFacilityDesignatedId().trim();
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
				+ "/chillerabsorber/" + chillervo.getId() + "-"
				+ chillervo.getFacilityDesignatedId().trim());

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

	public static double round(double d, int i) {
		double d1;
		for (d1 = 1.0D; i-- > 0; d1 *= 10D)
			;
		return (double) Math.round(d * d1) / d1;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.ChillerAction.class);
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