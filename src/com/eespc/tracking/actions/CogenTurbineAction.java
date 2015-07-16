package com.eespc.tracking.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

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
import com.eespc.tracking.bo.CogenTurbineVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.bo.StorageTankVo;
import com.eespc.tracking.bo.myenum.BurnerTypeEnum;
import com.eespc.tracking.bo.myenum.CogenTurbineTypeEnum;
import com.eespc.tracking.bo.myenum.CogenTurbineUseEnum;
import com.eespc.tracking.bo.myenum.ControlSystemTypeEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.FurnaceOilTypeEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.bo.myenum.TankOperatingStatusEnum;
import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.entity.CogenTurbineEntity;
import com.eespc.tracking.entity.StackStorageChecker;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class CogenTurbineAction extends Action {

	public CogenTurbineAction() {
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		// setFormVariable(dynaactionform, cogenturbinevo, httpservletrequest);

		// refreshShowInfo(httpservletrequest, cogenturbinevo);
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
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, cogenturbinevo);
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
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, cogenturbinevo);
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
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, cogenturbinevo);
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
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		// refreshShowInfo(httpservletrequest, cogenturbinevo);
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
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);

		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setScreenInfo(httpservletrequest);

		String back = "";
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/cogenengine/"
				+ cogenturbinevo.getId() + "-"
				+ cogenturbinevo.getFacilityDesignatedId().trim())) {
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
		httpservletrequest.setAttribute("YEARS", YearEnum.getDropDownObj());
		httpservletrequest.setAttribute("COGENTURBINE_PRIMARY_USE",
				CogenTurbineUseEnum.getDropDownObj());
		httpservletrequest.setAttribute("CONTROL_EFFICIENCY",
				ControlSystemTypeEnum.getDropDownObj());
		httpservletrequest.setAttribute("COGEN_TURBINE_PRIMARY_FUEL",
				FurnaceOilTypeEnum.getDropDownObj(false));
		httpservletrequest.setAttribute("COGEN_TURBINE_SECONDARY_FUEL",
				FurnaceOilTypeEnum.getDropDownObj(false));
		com.eespc.tracking.bo.DropDown dropdown = TankOperatingStatusEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("TANK_OPERATION_STATUS", dropdown);
	}

	private void setScreenInfo(HttpServletRequest httpservletrequest)

	{
		try {

			boolean flag = UtilityObject
					.isBoroughValidInNyc(httpservletrequest);

			if (flag)
				httpservletrequest.setAttribute("is5Borough", "Y");
		} catch (Exception e) {
			System.out.println(e);
		}

		HttpSession httpsession = httpservletrequest.getSession();

		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		if (cogenturbinevo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
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
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		CogenTurbineEntity cogenturbineentity = new CogenTurbineEntity();

		List list = cogenturbineentity.getFuelConsumptionyearList(
				cogenturbinevo.getId(), year);

		System.out.println("hai senthil" + list.size());
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

	public ActionForward o_fuelConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		CogenTurbineEntity cogenturbineentity = new CogenTurbineEntity();

		List list = cogenturbineentity.geto_FuelConsumptionyearList(
				cogenturbinevo.getId(), year);

		System.out.println("hai senthil" + list.size());
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

	private void setFieldDetailsforedit(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, CogenTurbineVo cogenturbinevo) {
		setFieldDetailsforedit(httpservletrequest, dynaactionform,
				cogenturbinevo, false);
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, CogenTurbineVo cogenturbinevo) {
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo,
				false);
	}

	private void setFieldDetailsforedit(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, CogenTurbineVo cogenturbinevo,
			boolean flag) {
		if (cogenturbinevo == null) {
			log.debug("Object is null so returning..");
			return;
		}
		try {
			dynaactionform.set("facilityDesignatedId",
					cogenturbinevo.getFacilityDesignatedId());
			dynaactionform.set(
					"type",
					(new StringBuffer(String.valueOf(cogenturbinevo
							.getResourceType()))).toString());
			dynaactionform.set("floor", cogenturbinevo.getFloor());
			CogenTurbineUseEnum cogenturbineuseenum = CogenTurbineUseEnum
					.get(cogenturbinevo.getPrimaryUse());
			if (flag)
				dynaactionform
						.set("primaryUse",
								cogenturbineuseenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(cogenturbineuseenum
												.getCode()))).toString())) : "");
			else
				dynaactionform
						.set("primaryUse",
								cogenturbineuseenum != null ? ((Object) (cogenturbineuseenum
										.getName())) : "");
			dynaactionform.set("controlSystem", UtilityObject
					.booleanToString(cogenturbinevo.isHasControlSystem()));
			ControlSystemTypeEnum controlsystemtypeenum = ControlSystemTypeEnum
					.get(cogenturbinevo.getControlEfficiency());
			if (flag)
				dynaactionform
						.set("controlEfficiency",
								controlsystemtypeenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(controlsystemtypeenum
												.getCode()))).toString())) : "");
			else
				dynaactionform
						.set("controlEfficiency",
								controlsystemtypeenum != null ? ((Object) (controlsystemtypeenum
										.getName())) : "");
			dynaactionform.set("yearInstalled",
					cogenturbinevo.getYearInstalled());
			dynaactionform.set("co_status",
					(new StringBuilder()).append(cogenturbinevo.getStatus())
							.append("").toString());
			dynaactionform.set(
					"co_disconnecteddate",
					(new StringBuilder())
							.append(checkNullAndFill(
									cogenturbinevo.getDisconnecteddate(), ""))
							.append("").toString());
			dynaactionform
					.set("manufacturer", cogenturbinevo.getManufacturer());
			dynaactionform.set("make", cogenturbinevo.getMake());
			dynaactionform.set("model", cogenturbinevo.getModel());
			dynaactionform.set("serial", cogenturbinevo.getSerialNumber());
			dynaactionform.set("burnerMake", cogenturbinevo.getBurnerMake());
			dynaactionform.set("burnerModel", cogenturbinevo.getBurnerModel());
			BurnerTypeEnum burnertypeenum = BurnerTypeEnum.get(cogenturbinevo
					.getBurnerType());
			if (flag) {
				int i = cogenturbinevo.getBurnerType();
				if (i == BurnerTypeEnum.DUAL_FUEL.getCode())
					dynaactionform.set("burnerType", "Y");
				if (i == BurnerTypeEnum.SINGLE_FUEL.getCode())
					dynaactionform.set("burnerType", "N");
			} else {
				dynaactionform.set(
						"burnerType",
						burnertypeenum != null ? ((Object) (burnertypeenum
								.getName())) : "");
			}
			dynaactionform.set("capacity", cogenturbinevo.getCapacity());

			FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum
					.get(cogenturbinevo.getPrimaryFuel());
			FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum
					.get(cogenturbinevo.getSecondaryFuel());

			if (flag) {
				dynaactionform
						.set("primaryFuel",
								furnaceoiltypeenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(furnaceoiltypeenum
												.getCode()))).toString())) : "");
				dynaactionform
						.set("secondaryFuel",
								furnaceoiltypeenum1 != null ? ((Object) ((new StringBuffer(
										String.valueOf(furnaceoiltypeenum1
												.getCode()))).toString())) : "");
			} else {
				dynaactionform
						.set("primaryFuel",
								furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum
										.getName())) : "");
				dynaactionform
						.set("secondaryFuel",
								furnaceoiltypeenum1 != null ? ((Object) (furnaceoiltypeenum1
										.getName())) : "");
			}
			double d = Double.parseDouble(cogenturbinevo.getCapacity());

			// int npf=cogenturbinevo.getSecondaryFuel();

			double d1 = -99D;
			if (d > 0.0D) {
				double d2 = round(d * 0.01D * 1000D, 2);
				DecimalFormat decimalformat = new DecimalFormat("###.##");
				String s = decimalformat.format(d * 1.3400000000000001D);
				dynaactionform.set("hpText", s);
				s = decimalformat.format(d * 0.01D);
				dynaactionform.set("mmbtuText", s);

				if (furnaceoiltypeenum.getCode() == 1)
					dynaactionform.set("naturalGasFiringRate",
							(new StringBuffer(String.valueOf(d2))).toString());
				else {
					byte nbyte0 = -99;
					int nj1 = furnaceoiltypeenum.getCode();
					int nk1 = 0;
					if (nj1 == 2)
						nk1 = 140;
					else if (nj1 == 3)
						nk1 = 145;
					else if (nj1 == 4)
						nk1 = 150;
					if (nk1 > 0)
						dynaactionform
								.set("oilFiringRate",
										(new StringBuffer(String.valueOf(round(
												(d * 0.01D * 1000D)
														/ (double) nk1, 2))))
												.toString());

				}

			}

			int i1 = cogenturbinevo.getSecondaryFuel();
			if (i1 > 0 && furnaceoiltypeenum1 != null) {
				byte byte0 = -99;
				int j1 = furnaceoiltypeenum1.getCode();
				int k1 = 0;
				if (j1 == 2)
					k1 = 140;
				else if (j1 == 3)
					k1 = 145;
				else if (j1 == 4)
					k1 = 150;
				if (k1 > 0)
					dynaactionform.set(
							"oilFiringRate",
							(new StringBuffer(String.valueOf(round(
									(d * 0.01D * 1000D) / (double) k1, 2))))
									.toString());
			}

			StorageTankVo storagetankvo = cogenturbinevo.getFuelFromTankObj();
			if (storagetankvo != null) {
				dynaactionform
						.set("fuelFrom",
								(new StringBuffer(String.valueOf(storagetankvo
										.getId()))).toString());
				dynaactionform.set("fuelFromName",
						storagetankvo.getFacilityDesignatedId());
			}
			dynaactionform.set("nycDob", cogenturbinevo.getNycdob());
			dynaactionform.set("dobsignoff", cogenturbinevo.getDobsignoff());
			dynaactionform.set("dobfiling", cogenturbinevo.getDobfiling());
			dynaactionform.set("mea", cogenturbinevo.getMea());
			dynaactionform.set("dep", cogenturbinevo.getDepNumber());
			dynaactionform
					.set("sechduelC", UtilityObject
							.booleanToString(cogenturbinevo.isSechedule()));
			dynaactionform.set("planApproval", UtilityObject
					.booleanToString(cogenturbinevo.isPlanApproval()));
			dynaactionform.set("recordsInBook", UtilityObject
					.booleanToString(cogenturbinevo.isRecordsMaintained()));
			dynaactionform.set("protocolSubmitted", UtilityObject
					.booleanToString(cogenturbinevo.isTestProtocolSubmitted()));
			dynaactionform.set("testConductedBy",
					cogenturbinevo.getTestContductedBy());
			dynaactionform.set("testReportSubmited", UtilityObject
					.booleanToString(cogenturbinevo.isTestReportSubmitted()));
			dynaactionform.set("isNoxRactPlan", UtilityObject
					.booleanToString(cogenturbinevo.isComplyWithNoxPlan()));
			dynaactionform.set("retestPlanned", UtilityObject
					.booleanToString(cogenturbinevo.isRetestPlanned()));
			Object obj = null;
			String s1 = cogenturbinevo.getTestReportSubmittedDate();
			dynaactionform.set("testReportSubmitedDate",
					UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
			s1 = cogenturbinevo.getNextStackTestDate();
			dynaactionform.set("nextStackTest",
					UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
			dynaactionform.set("nextStackTestNote",
					cogenturbinevo.getNextStackTestNote());
			StackVo stackvo = cogenturbinevo.getStackObj();
			if (stackvo != null) {
				dynaactionform
						.set("stackFrom",
								(new StringBuffer(String.valueOf(stackvo
										.getStackId()))).toString());
				dynaactionform.set("stackFromName",
						stackvo.getFacilityStackId());
			}
			dynaactionform.set("isFuleCapping", UtilityObject
					.booleanToString(cogenturbinevo.isFuleCappingUnderLimit()));

			dynaactionform.set("isturbine", cogenturbinevo.getIsturbine());
			if (cogenturbinevo.getIsturbine().equalsIgnoreCase("Y")) {
				dynaactionform.set("tmanufacturer",
						cogenturbinevo.getTmanufacturer());
				dynaactionform.set("tmake", cogenturbinevo.getTmake());
				dynaactionform.set("tmodel", cogenturbinevo.getTmodel());
				dynaactionform.set("tserial", cogenturbinevo.getTserial());
				dynaactionform.set("tburnerMake",
						cogenturbinevo.getTburnerMake());
				dynaactionform.set("tburnerModel",
						cogenturbinevo.getTburnerModel());

				/*
				 * dynaactionform.set("tcapacity",cogenturbinevo.getTcapacity());
				 * dynaactionform
				 * .set("tburnerType",cogenturbinevo.getTburnerType());
				 * dynaactionform
				 * .set("tprimaryFuel",cogenturbinevo.getTprimaryFuel());
				 * dynaactionform
				 * .set("tsecondaryFuel",cogenturbinevo.getTsecondaryFuel());
				 */

				if (flag) {
					String btype = cogenturbinevo.getTburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("tburnerType", "Y");
					else if (btype.equals("N"))
						dynaactionform.set("tburnerType", "N");
					else
						dynaactionform.set("tburnerType", "");
				} else {
					String btype = cogenturbinevo.getTburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("tburnerType", "Dual fuel");
					else if (btype.equals("N"))
						dynaactionform.set("tburnerType", "Single fuel");
					else
						dynaactionform.set("tburnerType", "");
				}

				dynaactionform.set("tcapacity", cogenturbinevo.getTcapacity());
				// ************************************************************************
				System.out.println(cogenturbinevo.getTprimaryFuel()
						+ "@@@@@@@@@@@@@" + cogenturbinevo.getTsecondaryFuel());
				FurnaceOilTypeEnum tfurnaceoiltypeenum = FurnaceOilTypeEnum
						.get(cogenturbinevo.getTprimaryFuel());
				FurnaceOilTypeEnum tfurnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(cogenturbinevo.getTsecondaryFuel());

				if (flag) {
					dynaactionform
							.set("tprimaryFuel",
									tfurnaceoiltypeenum != null ? ((Object) ((new StringBuffer(
											String.valueOf(tfurnaceoiltypeenum
													.getCode()))).toString()))
											: "");
					dynaactionform
							.set("tsecondaryFuel",
									tfurnaceoiltypeenum1 != null ? ((Object) ((new StringBuffer(
											String.valueOf(tfurnaceoiltypeenum1
													.getCode()))).toString()))
											: "");
				} else {
					dynaactionform
							.set("tprimaryFuel",
									tfurnaceoiltypeenum != null ? ((Object) (tfurnaceoiltypeenum
											.getName())) : "");
					dynaactionform
							.set("tsecondaryFuel",
									tfurnaceoiltypeenum1 != null ? ((Object) (tfurnaceoiltypeenum1
											.getName())) : "");
				}

				if (UtilityObject.isNotEmpty(cogenturbinevo.getTcapacity())) {
					double td = Double.parseDouble(cogenturbinevo
							.getTcapacity());
					double d2 = round(td * 0.01D * 1000D, 2);
					DecimalFormat decimalformat = new DecimalFormat("###.##");
					String s = decimalformat.format(td * 1.3400000000000001D);
					dynaactionform.set("thpText", s);
					s = decimalformat.format(td * 0.01D);
					dynaactionform.set("tmmbtuText", s);
					if (tfurnaceoiltypeenum != null) {

						double td1 = -99D;
						if (td > 0.0D) {

							if (tfurnaceoiltypeenum.getCode() == 1)
								dynaactionform.set("tnaturalGasFiringRate",
										(new StringBuffer(String.valueOf(d2)))
												.toString());
							else {
								byte byte0 = -99;
								int j1 = tfurnaceoiltypeenum.getCode();
								int k1 = 0;
								if (j1 == 2)
									k1 = 140;
								else if (j1 == 3)
									k1 = 145;
								else if (j1 == 4)
									k1 = 150;
								if (k1 > 0)
									dynaactionform
											.set("toilFiringRate",
													(new StringBuffer(
															String.valueOf(round(
																	(td * 0.01D * 1000D)
																			/ (double) k1,
																	2))))
															.toString());

							}

						}
					}

					else {
						dynaactionform.set("tnaturalGasFiringRate", "");
						dynaactionform.set("toilFiringRate", "");

					}

					if (tfurnaceoiltypeenum1 != null) {
						int ti1 = cogenturbinevo.getTsecondaryFuel();
						if (i1 > 0 && tfurnaceoiltypeenum1 != null) {
							byte byte0 = -99;
							int j1 = tfurnaceoiltypeenum1.getCode();
							int k1 = 0;
							if (j1 == 2)
								k1 = 140;
							else if (j1 == 3)
								k1 = 145;
							else if (j1 == 4)
								k1 = 150;
							if (k1 > 0)
								dynaactionform.set(
										"toilFiringRate",
										(new StringBuffer(String.valueOf(round(
												(td * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
						}
					}
				} else {
					dynaactionform.set("tnaturalGasFiringRate", "");
					dynaactionform.set("toilFiringRate", "");
					dynaactionform.set("thpText", "");
					dynaactionform.set("tmmbtuText", "");
				}

			} else {
			}
			// ************************************************************************
			dynaactionform.set("iswhrb", cogenturbinevo.getIswhrb());
			if (cogenturbinevo.getIswhrb().equalsIgnoreCase("Y")) {
				dynaactionform.set("wmanufacturer",
						cogenturbinevo.getWmanufacturer());
				dynaactionform.set("wmake", cogenturbinevo.getWmake());
				dynaactionform.set("wmodel", cogenturbinevo.getWmodel());
				dynaactionform.set("wserial", cogenturbinevo.getWserial());
				dynaactionform.set("wburnerMake",
						cogenturbinevo.getWburnerMake());
				dynaactionform.set("wburnerModel",
						cogenturbinevo.getWburnerModel());

				/*
				 * dynaactionform.set("wcapacity",cogenturbinevo.getWcapacity());
				 * dynaactionform
				 * .set("wburnerType",cogenturbinevo.getWburnerType());
				 * dynaactionform
				 * .set("wprimaryFuel",cogenturbinevo.getWprimaryFuel());
				 * dynaactionform
				 * .set("wsecondaryFuel",cogenturbinevo.getWsecondaryFuel());
				 */

				if (flag) {
					String btype = cogenturbinevo.getWburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("wburnerType", "Y");
					else if (btype.equals("N"))
						dynaactionform.set("wburnerType", "N");
					else
						dynaactionform.set("wburnerType", "");
				} else {
					String btype = cogenturbinevo.getWburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("wburnerType", "Dual fuel");
					else if (btype.equals("N"))
						dynaactionform.set("wburnerType", "Single fuel");
					else
						dynaactionform.set("wburnerType", "");
				}

				dynaactionform.set("wcapacity", cogenturbinevo.getWcapacity());

				FurnaceOilTypeEnum wfurnaceoiltypeenum = FurnaceOilTypeEnum
						.get(cogenturbinevo.getWprimaryFuel());
				FurnaceOilTypeEnum wfurnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(cogenturbinevo.getWsecondaryFuel());

				if (flag) {
					dynaactionform
							.set("wprimaryFuel",
									wfurnaceoiltypeenum != null ? ((Object) ((new StringBuffer(
											String.valueOf(wfurnaceoiltypeenum
													.getCode()))).toString()))
											: "");
					dynaactionform
							.set("wsecondaryFuel",
									wfurnaceoiltypeenum1 != null ? ((Object) ((new StringBuffer(
											String.valueOf(wfurnaceoiltypeenum1
													.getCode()))).toString()))
											: "");
				} else {
					dynaactionform
							.set("wprimaryFuel",
									wfurnaceoiltypeenum != null ? ((Object) (wfurnaceoiltypeenum
											.getName())) : "");
					dynaactionform
							.set("wsecondaryFuel",
									wfurnaceoiltypeenum1 != null ? ((Object) (wfurnaceoiltypeenum1
											.getName())) : "");
				}

				if (UtilityObject.isNotEmpty(cogenturbinevo.getWcapacity())) {

					double wd = Double.parseDouble(cogenturbinevo
							.getWcapacity());
					double d2 = round(wd * 0.01D * 1000D, 2);
					DecimalFormat decimalformat = new DecimalFormat("###.##");
					String s = decimalformat.format(wd * 1.3400000000000001D);
					dynaactionform.set("whpText", s);
					s = decimalformat.format(wd * 0.01D);
					dynaactionform.set("wmmbtuText", s);
					if (wfurnaceoiltypeenum != null) {

						double wd1 = -99D;
						if (wd > 0.0D) {

							if (wfurnaceoiltypeenum.getCode() == 1)
								dynaactionform.set("wnaturalGasFiringRate",
										(new StringBuffer(String.valueOf(d2)))
												.toString());
							else {
								byte byte0 = -99;
								int j1 = wfurnaceoiltypeenum.getCode();
								int k1 = 0;
								if (j1 == 2)
									k1 = 140;
								else if (j1 == 3)
									k1 = 145;
								else if (j1 == 4)
									k1 = 150;
								if (k1 > 0)
									dynaactionform
											.set("woilFiringRate",
													(new StringBuffer(
															String.valueOf(round(
																	(wd * 0.01D * 1000D)
																			/ (double) k1,
																	2))))
															.toString());

							}
						}
					} else {
						dynaactionform.set("wnaturalGasFiringRate", "");
						dynaactionform.set("woilFiringRate", "");

					}

					if (wfurnaceoiltypeenum1 != null) {

						int wi1 = cogenturbinevo.getWsecondaryFuel();
						if (i1 > 0 && wfurnaceoiltypeenum1 != null) {
							byte byte0 = -99;
							int j1 = wfurnaceoiltypeenum1.getCode();
							int k1 = 0;
							if (j1 == 2)
								k1 = 140;
							else if (j1 == 3)
								k1 = 145;
							else if (j1 == 4)
								k1 = 150;
							if (k1 > 0)
								dynaactionform.set(
										"woilFiringRate",
										(new StringBuffer(String.valueOf(round(
												(wd * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
						}
					}
				} else {
					dynaactionform.set("wnaturalGasFiringRate", "");
					dynaactionform.set("woilFiringRate", "");
					dynaactionform.set("whpText", "");
					dynaactionform.set("wmmbtuText", "");

				}
			} else {
			}
			dynaactionform.set("co_decPermitObtained",
					cogenturbinevo.getCo_decPermitObtained());
			dynaactionform.set("co_depPermitObtained",
					cogenturbinevo.getCo_depPermitObtained());
			dynaactionform.set("co_compyWithSO2RactPlan",
					cogenturbinevo.getCo_compyWithSO2RactPlan());
			dynaactionform.set("co_compyWithPMRactPlan",
					cogenturbinevo.getCo_compyWithPMRactPlan());
			dynaactionform.set("co_testPassed",
					cogenturbinevo.getCo_testPassed());

			dynaactionform.set("co_StackTestDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(cogenturbinevo
							.getCo_StackTestDate()));

			dynaactionform.set("co_fuelgapping",
					cogenturbinevo.getCo_fuelgapping());
			dynaactionform.set("co_gasfuelgapping",
					cogenturbinevo.getCo_gasfuelgapping());
			dynaactionform.set("co_gasemmisionfactor",
					cogenturbinevo.getCo_gasemmisionfactor());
			dynaactionform.set("co_oilfuelgapping",
					cogenturbinevo.getCo_oilfuelgapping());
			dynaactionform.set("co_oilemmisionfactor",
					cogenturbinevo.getCo_oilemmisionfactor());
			dynaactionform.set("co_oilemmisionfactor",
					cogenturbinevo.getCo_oilemmisionfactor());
			dynaactionform.set("co_Comments", cogenturbinevo.getCo_Comments());
			dynaactionform.set("co_oilso2", cogenturbinevo.getCo_oilso2());
			dynaactionform.set("co_gasso2", cogenturbinevo.getCo_gasso2());
			dynaactionform.set("co_so2allowable",
					cogenturbinevo.getCo_so2allowable());
			dynaactionform.set("co_noxallowable",
					cogenturbinevo.getCo_noxallowable());

		}

		catch (Exception exception) {
			System.out.println("View Exception" + exception);
			log.error(exception);
		}

		List list = cogenturbinevo.getFuelConsumptionList();
		if (list != null && list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list);
			httpservletrequest.setAttribute("hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FUEL_CONSUMPTION", list);
		} else {
			httpservletrequest
					.setAttribute("FUEL_CONSUMPTION", new ArrayList());
			httpservletrequest.setAttribute("hdnConsumption", "");
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		List o_list = cogenturbinevo.geto_FuelConsumptionList();
		if (o_list != null && o_list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(o_list);
			httpservletrequest.setAttribute("o_hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("o_hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("o_hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list);
		} else {
			httpservletrequest.setAttribute("O_FUEL_CONSUMPTION",
					new ArrayList());
			httpservletrequest.setAttribute("o_hdnConsumption", "");
			httpservletrequest.setAttribute("o_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		cogenturbinevo.setPermitList(null);
		List list1 = cogenturbinevo.getPermitList();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int j = list1 == null ? 0 : list1.size();
		if (list1 != null && j > 0) {
			for (int k = 0; k < j; k++) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(k);
				int l = permitinfovo.getDepId();
				if (l == DepartmentEnum.NYCDEP.getCode()) {
					arraylist.add(permitinfovo);
					continue;
				}
				if (l == DepartmentEnum.STATE_AGENCY.getCode())
					arraylist1.add(permitinfovo);
			}

		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		httpservletrequest
				.setAttribute("COGENTURBINE_STATE_PERMIT", arraylist1);
		httpservletrequest.setAttribute("COGENTURBINE_DEP_PERMIT", arraylist);
		UtilityObject.setPermitNumber("displayStatePermitNumber",
				httpservletrequest, arraylist1);
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, CogenTurbineVo cogenturbinevo,
			boolean flag) {
		if (cogenturbinevo == null) {
			log.debug("Object is null so returning..");
			return;
		}
		try {
			dynaactionform.set("facilityDesignatedId",
					cogenturbinevo.getFacilityDesignatedId());
			dynaactionform.set(
					"type",
					(new StringBuffer(String.valueOf(cogenturbinevo
							.getResourceType()))).toString());
			dynaactionform.set("floor", cogenturbinevo.getFloor());
			CogenTurbineUseEnum cogenturbineuseenum = CogenTurbineUseEnum
					.get(cogenturbinevo.getPrimaryUse());
			if (flag)
				dynaactionform
						.set("primaryUse",
								cogenturbineuseenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(cogenturbineuseenum
												.getCode()))).toString())) : "");
			else
				dynaactionform
						.set("primaryUse",
								cogenturbineuseenum != null ? ((Object) (cogenturbineuseenum
										.getName())) : "");
			dynaactionform.set("controlSystem", UtilityObject
					.booleanToString(cogenturbinevo.isHasControlSystem()));

			ControlSystemTypeEnum controlsystemtypeenum = ControlSystemTypeEnum
					.get(cogenturbinevo.getControlEfficiency());
			if (flag)
				dynaactionform
						.set("controlEfficiency",
								controlsystemtypeenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(controlsystemtypeenum
												.getCode()))).toString())) : "");
			else
				dynaactionform
						.set("controlEfficiency",
								controlsystemtypeenum != null ? ((Object) (controlsystemtypeenum
										.getName())) : "");

			dynaactionform.set("yearInstalled",
					cogenturbinevo.getYearInstalled());
			TankOperatingStatusEnum tankoperatingstatusenum = TankOperatingStatusEnum
					.get(cogenturbinevo.getStatus());
			if (tankoperatingstatusenum != null)
				dynaactionform.set("co_status",
						tankoperatingstatusenum.getName());
			dynaactionform.set(
					"co_disconnecteddate",
					(new StringBuilder())
							.append(checkNullAndFill(
									cogenturbinevo.getDisconnecteddate(), ""))
							.append("").toString());
			dynaactionform
					.set("manufacturer", cogenturbinevo.getManufacturer());
			dynaactionform.set("make", cogenturbinevo.getMake());
			dynaactionform.set("model", cogenturbinevo.getModel());
			dynaactionform.set("serial", cogenturbinevo.getSerialNumber());
			dynaactionform.set("burnerMake", cogenturbinevo.getBurnerMake());
			dynaactionform.set("burnerModel", cogenturbinevo.getBurnerModel());
			BurnerTypeEnum burnertypeenum = BurnerTypeEnum.get(cogenturbinevo
					.getBurnerType());
			if (flag) {
				int i = cogenturbinevo.getBurnerType();
				if (i == BurnerTypeEnum.DUAL_FUEL.getCode())
					dynaactionform.set("burnerType", "Y");
				if (i == BurnerTypeEnum.SINGLE_FUEL.getCode())
					dynaactionform.set("burnerType", "N");
			} else {
				dynaactionform.set(
						"burnerType",
						burnertypeenum != null ? ((Object) (burnertypeenum
								.getName())) : "");
			}
			dynaactionform.set("capacity", cogenturbinevo.getCapacity());
			FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum
					.get(cogenturbinevo.getPrimaryFuel());
			FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum
					.get(cogenturbinevo.getSecondaryFuel());
			if (flag) {
				dynaactionform
						.set("primaryFuel",
								furnaceoiltypeenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(furnaceoiltypeenum
												.getCode()))).toString())) : "");
				dynaactionform
						.set("secondaryFuel",
								furnaceoiltypeenum1 != null ? ((Object) ((new StringBuffer(
										String.valueOf(furnaceoiltypeenum1
												.getCode()))).toString())) : "");
			} else {
				dynaactionform
						.set("primaryFuel",
								furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum
										.getName())) : "");
				dynaactionform
						.set("secondaryFuel",
								furnaceoiltypeenum1 != null ? ((Object) (furnaceoiltypeenum1
										.getName())) : "");
			}
			double d = Double.parseDouble(cogenturbinevo.getCapacity());

			double d1 = -99D;
			if (d > 0.0D) {
				double d2 = round(d * 0.01D * 1000D, 2);
				DecimalFormat decimalformat = new DecimalFormat("###.##");
				String s = decimalformat.format(d * 1.3400000000000001D);
				dynaactionform.set("hpText", s);
				s = decimalformat.format(d * 0.01D);
				dynaactionform.set("mmbtuText", s);

				if (furnaceoiltypeenum.getCode() == 1)
					dynaactionform.set("naturalGasFiringRate",
							(new StringBuffer(String.valueOf(d2))).toString());
				else {
					byte nbyte0 = -99;
					int nj1 = furnaceoiltypeenum.getCode();
					int nk1 = 0;
					if (nj1 == 2)
						nk1 = 140;
					else if (nj1 == 3)
						nk1 = 145;
					else if (nj1 == 4)
						nk1 = 150;
					if (nk1 > 0)
						dynaactionform
								.set("oilFiringRate",
										(new StringBuffer(String.valueOf(round(
												(d * 0.01D * 1000D)
														/ (double) nk1, 2))))
												.toString());

				}

			}

			int i1 = cogenturbinevo.getSecondaryFuel();
			if (i1 > 0 && furnaceoiltypeenum1 != null) {
				byte byte0 = -99;
				int j1 = furnaceoiltypeenum1.getCode();
				int k1 = 0;
				if (j1 == 2)
					k1 = 140;
				else if (j1 == 3)
					k1 = 145;
				else if (j1 == 4)
					k1 = 150;
				if (k1 > 0)
					dynaactionform.set(
							"oilFiringRate",
							(new StringBuffer(String.valueOf(round(
									(d * 0.01D * 1000D) / (double) k1, 2))))
									.toString());
			}
			StorageTankVo storagetankvo = cogenturbinevo.getFuelFromTankObj();
			if (storagetankvo != null) {
				dynaactionform
						.set("fuelFrom",
								(new StringBuffer(String.valueOf(storagetankvo
										.getId()))).toString());
				dynaactionform.set("fuelFromName",
						storagetankvo.getFacilityDesignatedId());
			}
			dynaactionform.set("nycDob", cogenturbinevo.getNycdob());
			dynaactionform.set("dobsignoff", cogenturbinevo.getDobsignoff());
			dynaactionform.set("dobfiling", cogenturbinevo.getDobfiling());
			dynaactionform.set("mea", cogenturbinevo.getMea());
			dynaactionform.set("dep", cogenturbinevo.getDepNumber());
			dynaactionform
					.set("sechduelC", UtilityObject
							.booleanToString(cogenturbinevo.isSechedule()));
			dynaactionform.set("planApproval", UtilityObject
					.booleanToString(cogenturbinevo.isPlanApproval()));
			dynaactionform.set("recordsInBook", UtilityObject
					.booleanToString(cogenturbinevo.isRecordsMaintained()));
			dynaactionform.set("protocolSubmitted", UtilityObject
					.booleanToString(cogenturbinevo.isTestProtocolSubmitted()));
			dynaactionform.set("testConductedBy",
					cogenturbinevo.getTestContductedBy());
			dynaactionform.set("testReportSubmited", UtilityObject
					.booleanToString(cogenturbinevo.isTestReportSubmitted()));
			dynaactionform.set("isNoxRactPlan", UtilityObject
					.booleanToString(cogenturbinevo.isComplyWithNoxPlan()));
			dynaactionform.set("retestPlanned", UtilityObject
					.booleanToString(cogenturbinevo.isRetestPlanned()));
			Object obj = null;
			String s1 = cogenturbinevo.getTestReportSubmittedDate();
			dynaactionform.set("testReportSubmitedDate",
					UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
			s1 = cogenturbinevo.getNextStackTestDate();
			dynaactionform.set("nextStackTest",
					UtilityObject.convertYyyyMmDd2MmDdYyyy(s1));
			dynaactionform.set("nextStackTestNote",
					cogenturbinevo.getNextStackTestNote());
			StackVo stackvo = cogenturbinevo.getStackObj();
			if (stackvo != null) {
				dynaactionform
						.set("stackFrom",
								(new StringBuffer(String.valueOf(stackvo
										.getStackId()))).toString());
				dynaactionform.set("stackFromName",
						stackvo.getFacilityStackId());
			}
			dynaactionform.set("isFuleCapping", UtilityObject
					.booleanToString(cogenturbinevo.isFuleCappingUnderLimit()));

			dynaactionform.set("isturbine", cogenturbinevo.getIsturbine());
			if (cogenturbinevo.getIsturbine().equalsIgnoreCase("Y")) {
				dynaactionform.set("tmanufacturer",
						cogenturbinevo.getTmanufacturer());
				dynaactionform.set("tmake", cogenturbinevo.getTmake());
				dynaactionform.set("tmodel", cogenturbinevo.getTmodel());
				dynaactionform.set("tserial", cogenturbinevo.getTserial());
				dynaactionform.set("tburnerMake",
						cogenturbinevo.getTburnerMake());
				dynaactionform.set("tburnerModel",
						cogenturbinevo.getTburnerModel());

				/*
				 * dynaactionform.set("tcapacity",cogenturbinevo.getTcapacity());
				 * dynaactionform
				 * .set("tburnerType",cogenturbinevo.getTburnerType());
				 * dynaactionform
				 * .set("tprimaryFuel",cogenturbinevo.getTprimaryFuel());
				 * dynaactionform
				 * .set("tsecondaryFuel",cogenturbinevo.getTsecondaryFuel());
				 */

				if (flag) {
					String btype = cogenturbinevo.getTburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("tburnerType", "Y");
					else if (btype.equals("N"))
						dynaactionform.set("tburnerType", "N");
					else
						dynaactionform.set("tburnerType", "");
				} else {
					String btype = cogenturbinevo.getTburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("tburnerType", "Dual fuel");
					else if (btype.equals("N"))
						dynaactionform.set("tburnerType", "Single fuel");
					else
						dynaactionform.set("tburnerType", "");
				}

				dynaactionform.set("tcapacity", cogenturbinevo.getTcapacity());
				// ************************************************************************
				System.out.println(cogenturbinevo.getTprimaryFuel()
						+ "@@@@@@@@@@@@@" + cogenturbinevo.getTsecondaryFuel());
				FurnaceOilTypeEnum tfurnaceoiltypeenum = FurnaceOilTypeEnum
						.get(cogenturbinevo.getTprimaryFuel());
				FurnaceOilTypeEnum tfurnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(cogenturbinevo.getTsecondaryFuel());

				if (flag) {
					dynaactionform
							.set("tprimaryFuel",
									tfurnaceoiltypeenum != null ? ((Object) ((new StringBuffer(
											String.valueOf(tfurnaceoiltypeenum
													.getCode()))).toString()))
											: "");
					dynaactionform
							.set("tsecondaryFuel",
									tfurnaceoiltypeenum1 != null ? ((Object) ((new StringBuffer(
											String.valueOf(tfurnaceoiltypeenum1
													.getCode()))).toString()))
											: "");
				} else {
					dynaactionform
							.set("tprimaryFuel",
									tfurnaceoiltypeenum != null ? ((Object) (tfurnaceoiltypeenum
											.getName())) : "");
					dynaactionform
							.set("tsecondaryFuel",
									tfurnaceoiltypeenum1 != null ? ((Object) (tfurnaceoiltypeenum1
											.getName())) : "");
				}

				if (UtilityObject.isNotEmpty(cogenturbinevo.getTcapacity())) {
					double td = Double.parseDouble(cogenturbinevo
							.getTcapacity());
					double d2 = round(td * 0.01D * 1000D, 2);
					DecimalFormat decimalformat = new DecimalFormat("###.##");
					String s = decimalformat.format(td * 1.3400000000000001D);
					dynaactionform.set("thpText", s);
					s = decimalformat.format(td * 0.01D);
					dynaactionform.set("tmmbtuText", s);
					if (tfurnaceoiltypeenum != null) {

						double td1 = -99D;
						if (td > 0.0D) {

							if (tfurnaceoiltypeenum.getCode() == 1)
								dynaactionform.set("tnaturalGasFiringRate",
										(new StringBuffer(String.valueOf(d2)))
												.toString());
							else {
								byte byte0 = -99;
								int j1 = tfurnaceoiltypeenum.getCode();
								int k1 = 0;
								if (j1 == 2)
									k1 = 140;
								else if (j1 == 3)
									k1 = 145;
								else if (j1 == 4)
									k1 = 150;
								if (k1 > 0)
									dynaactionform
											.set("toilFiringRate",
													(new StringBuffer(
															String.valueOf(round(
																	(td * 0.01D * 1000D)
																			/ (double) k1,
																	2))))
															.toString());

							}

						}
					}

					else {
						dynaactionform.set("tnaturalGasFiringRate", "");
						dynaactionform.set("toilFiringRate", "");

					}

					if (tfurnaceoiltypeenum1 != null) {
						int ti1 = cogenturbinevo.getTsecondaryFuel();
						if (i1 > 0 && tfurnaceoiltypeenum1 != null) {
							byte byte0 = -99;
							int j1 = tfurnaceoiltypeenum1.getCode();
							int k1 = 0;
							if (j1 == 2)
								k1 = 140;
							else if (j1 == 3)
								k1 = 145;
							else if (j1 == 4)
								k1 = 150;
							if (k1 > 0)
								dynaactionform.set(
										"toilFiringRate",
										(new StringBuffer(String.valueOf(round(
												(td * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
						}
					}
				} else {
					dynaactionform.set("tnaturalGasFiringRate", "");
					dynaactionform.set("toilFiringRate", "");
					dynaactionform.set("thpText", "");
					dynaactionform.set("tmmbtuText", "");
				}

			} else {
			}
			// ************************************************************************
			dynaactionform.set("iswhrb", cogenturbinevo.getIswhrb());
			if (cogenturbinevo.getIswhrb().equalsIgnoreCase("Y")) {
				dynaactionform.set("wmanufacturer",
						cogenturbinevo.getWmanufacturer());
				dynaactionform.set("wmake", cogenturbinevo.getWmake());
				dynaactionform.set("wmodel", cogenturbinevo.getWmodel());
				dynaactionform.set("wserial", cogenturbinevo.getWserial());
				dynaactionform.set("wburnerMake",
						cogenturbinevo.getWburnerMake());
				dynaactionform.set("wburnerModel",
						cogenturbinevo.getWburnerModel());

				/*
				 * dynaactionform.set("wcapacity",cogenturbinevo.getWcapacity());
				 * dynaactionform
				 * .set("wburnerType",cogenturbinevo.getWburnerType());
				 * dynaactionform
				 * .set("wprimaryFuel",cogenturbinevo.getWprimaryFuel());
				 * dynaactionform
				 * .set("wsecondaryFuel",cogenturbinevo.getWsecondaryFuel());
				 */

				if (flag) {
					String btype = cogenturbinevo.getWburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("wburnerType", "Y");
					else if (btype.equals("N"))
						dynaactionform.set("wburnerType", "N");
					else
						dynaactionform.set("wburnerType", "");
				} else {
					String btype = cogenturbinevo.getWburnerType();
					if (btype.equals("Y"))
						dynaactionform.set("wburnerType", "Dual fuel");
					else if (btype.equals("N"))
						dynaactionform.set("wburnerType", "Single fuel");
					else
						dynaactionform.set("wburnerType", "");
				}

				dynaactionform.set("wcapacity", cogenturbinevo.getWcapacity());

				FurnaceOilTypeEnum wfurnaceoiltypeenum = FurnaceOilTypeEnum
						.get(cogenturbinevo.getWprimaryFuel());
				FurnaceOilTypeEnum wfurnaceoiltypeenum1 = FurnaceOilTypeEnum
						.get(cogenturbinevo.getWsecondaryFuel());

				if (flag) {
					dynaactionform
							.set("wprimaryFuel",
									wfurnaceoiltypeenum != null ? ((Object) ((new StringBuffer(
											String.valueOf(wfurnaceoiltypeenum
													.getCode()))).toString()))
											: "");
					dynaactionform
							.set("wsecondaryFuel",
									wfurnaceoiltypeenum1 != null ? ((Object) ((new StringBuffer(
											String.valueOf(wfurnaceoiltypeenum1
													.getCode()))).toString()))
											: "");
				} else {
					dynaactionform
							.set("wprimaryFuel",
									wfurnaceoiltypeenum != null ? ((Object) (wfurnaceoiltypeenum
											.getName())) : "");
					dynaactionform
							.set("wsecondaryFuel",
									wfurnaceoiltypeenum1 != null ? ((Object) (wfurnaceoiltypeenum1
											.getName())) : "");
				}

				if (UtilityObject.isNotEmpty(cogenturbinevo.getWcapacity())) {

					double wd = Double.parseDouble(cogenturbinevo
							.getWcapacity());
					double d2 = round(wd * 0.01D * 1000D, 2);
					DecimalFormat decimalformat = new DecimalFormat("###.##");
					String s = decimalformat.format(wd * 1.3400000000000001D);
					dynaactionform.set("whpText", s);
					s = decimalformat.format(wd * 0.01D);
					dynaactionform.set("wmmbtuText", s);
					if (wfurnaceoiltypeenum != null) {

						double wd1 = -99D;
						if (wd > 0.0D) {

							if (wfurnaceoiltypeenum.getCode() == 1)
								dynaactionform.set("wnaturalGasFiringRate",
										(new StringBuffer(String.valueOf(d2)))
												.toString());
							else {
								byte byte0 = -99;
								int j1 = wfurnaceoiltypeenum.getCode();
								int k1 = 0;
								if (j1 == 2)
									k1 = 140;
								else if (j1 == 3)
									k1 = 145;
								else if (j1 == 4)
									k1 = 150;
								if (k1 > 0)
									dynaactionform
											.set("woilFiringRate",
													(new StringBuffer(
															String.valueOf(round(
																	(wd * 0.01D * 1000D)
																			/ (double) k1,
																	2))))
															.toString());

							}
						}
					} else {
						dynaactionform.set("wnaturalGasFiringRate", "");
						dynaactionform.set("woilFiringRate", "");

					}

					if (wfurnaceoiltypeenum1 != null) {

						int wi1 = cogenturbinevo.getWsecondaryFuel();
						if (i1 > 0 && wfurnaceoiltypeenum1 != null) {
							byte byte0 = -99;
							int j1 = wfurnaceoiltypeenum1.getCode();
							int k1 = 0;
							if (j1 == 2)
								k1 = 140;
							else if (j1 == 3)
								k1 = 145;
							else if (j1 == 4)
								k1 = 150;
							if (k1 > 0)
								dynaactionform.set(
										"woilFiringRate",
										(new StringBuffer(String.valueOf(round(
												(wd * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
						}
					}
				} else {
					dynaactionform.set("wnaturalGasFiringRate", "");
					dynaactionform.set("woilFiringRate", "");
					dynaactionform.set("whpText", "");
					dynaactionform.set("wmmbtuText", "");

				}
			} else {
			}

			dynaactionform.set("co_decPermitObtained",
					cogenturbinevo.getCo_decPermitObtained());
			dynaactionform.set("co_depPermitObtained",
					cogenturbinevo.getCo_depPermitObtained());
			dynaactionform.set("co_compyWithSO2RactPlan",
					cogenturbinevo.getCo_compyWithSO2RactPlan());
			dynaactionform.set("co_compyWithPMRactPlan",
					cogenturbinevo.getCo_compyWithPMRactPlan());
			dynaactionform.set("co_testPassed",
					cogenturbinevo.getCo_testPassed());

			dynaactionform.set("co_StackTestDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(cogenturbinevo
							.getCo_StackTestDate()));

			dynaactionform.set("co_fuelgapping",
					cogenturbinevo.getCo_fuelgapping());
			dynaactionform.set("co_gasfuelgapping",
					cogenturbinevo.getCo_gasfuelgapping());
			dynaactionform.set("co_gasemmisionfactor",
					cogenturbinevo.getCo_gasemmisionfactor());
			dynaactionform.set("co_oilfuelgapping",
					cogenturbinevo.getCo_oilfuelgapping());
			dynaactionform.set("co_oilemmisionfactor",
					cogenturbinevo.getCo_oilemmisionfactor());
			dynaactionform.set("co_oilemmisionfactor",
					cogenturbinevo.getCo_oilemmisionfactor());
			dynaactionform.set("co_Comments", cogenturbinevo.getCo_Comments());
			dynaactionform.set("co_oilso2", cogenturbinevo.getCo_oilso2());
			dynaactionform.set("co_gasso2", cogenturbinevo.getCo_gasso2());
			dynaactionform.set("co_so2allowable",
					cogenturbinevo.getCo_so2allowable());
			dynaactionform.set("co_noxallowable",
					cogenturbinevo.getCo_noxallowable());

		} catch (Exception exception) {
			log.error(exception);
		}
		List list = cogenturbinevo.getFuelConsumptionList();
		if (list != null && list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list);
			httpservletrequest.setAttribute("hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FUEL_CONSUMPTION", list);
		} else {
			httpservletrequest
					.setAttribute("FUEL_CONSUMPTION", new ArrayList());
			httpservletrequest.setAttribute("hdnConsumption", "");
			httpservletrequest.setAttribute("hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		List o_list = cogenturbinevo.geto_FuelConsumptionList();
		if (o_list != null && o_list.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(o_list);
			httpservletrequest.setAttribute("o_hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("o_hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("o_hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("O_FUEL_CONSUMPTION", o_list);
		} else {
			httpservletrequest.setAttribute("O_FUEL_CONSUMPTION",
					new ArrayList());
			httpservletrequest.setAttribute("o_hdnConsumption", "");
			httpservletrequest.setAttribute("o_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("o_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		cogenturbinevo.setPermitList(null);
		List list1 = cogenturbinevo.getPermitList();
		ArrayList arraylist = new ArrayList();
		ArrayList arraylist1 = new ArrayList();
		int j = list1 == null ? 0 : list1.size();
		if (list1 != null && j > 0) {
			for (int k = 0; k < j; k++) {
				PermitInfoVo permitinfovo = (PermitInfoVo) list1.get(k);
				int l = permitinfovo.getDepId();
				if (l == DepartmentEnum.NYCDEP.getCode()) {
					arraylist.add(permitinfovo);
					continue;
				}
				if (l == DepartmentEnum.STATE_AGENCY.getCode())
					arraylist1.add(permitinfovo);
			}

		}

		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
		}

		httpservletrequest
				.setAttribute("COGENTURBINE_STATE_PERMIT", arraylist1);
		httpservletrequest.setAttribute("COGENTURBINE_DEP_PERMIT", arraylist);
		UtilityObject.setPermitNumber("displayStatePermitNumber",
				httpservletrequest, arraylist1);

	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		setDropDown(httpservletrequest);
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		CogenTurbineTypeEnum cogenturbinetypeenum = CogenTurbineTypeEnum
				.get(UtilityObject.getIntFromString((String) dynaactionform
						.get("type")));
		httpservletrequest.setAttribute(
				"RESOURCE_NAME",
				cogenturbinetypeenum != null ? ((Object) (cogenturbinetypeenum
						.getName())) : "");
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
		if (s != null && s.equalsIgnoreCase("deleteDepPermit"))
			return deleteDepPermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("updateDepPermitInfo"))
			return updateDepPermitInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("fuelConsumptionInfo"))
			return fuelConsumptionInfo(actionmapping, actionform,
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
		if (s != null && s.equalsIgnoreCase("updateStatePermit"))
			return updateStatePermit(actionmapping, actionform,
					httpservletrequest, httpservletresponse);
		if (s != null && s.equalsIgnoreCase("displayControl"))
			return displayControl(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (s != null && s.equalsIgnoreCase("deletefuelConsumptionInfo"))
			return deletefuelConsumptionInfo(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (s != null
				&& s.equalsIgnoreCase("fuelConsumptionInfoyearconsumtion"))
			return fuelConsumptionInfoyearconsumtion(actionmapping, actionform,
					httpservletrequest, httpservletresponse);

		if (s != null
				&& s.equalsIgnoreCase("o_fuelConsumptionInfoyearconsumtion"))
			return o_fuelConsumptionInfoyearconsumtion(actionmapping,
					actionform, httpservletrequest, httpservletresponse);

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

		log.debug((new StringBuilder())
				.append("CogenTurbineAction userAction is ").append(s)
				.toString());
		StackStorageChecker stackstoragechecker = new StackStorageChecker();
		FacilityVo facilityvo = (FacilityVo) httpservletrequest.getSession()
				.getAttribute("FACILITY_OBJECT");
		if (!stackstoragechecker.isAvailable(facilityvo.getId())) {
			if (stackstoragechecker.getMessage() != null)
				httpservletrequest.setAttribute(
						"STACK_STORAGE_VALIDATION_MESSAGE",
						stackstoragechecker.getMessage());
			return actionmapping.findForward("showNoStackStorageMessagePage");
		} else {
			log.debug((new StringBuilder())
					.append("CogenTurbineAction - Stack / Storage Tank validated for Facility Id =")
					.append(facilityvo.getId()).append(".").toString());
			httpservletrequest.getSession().removeAttribute(
					"COGEN_TURBINE_OBJECT");
			setScreenInfo(httpservletrequest);
			log.debug("CogenTurbineAction - In Execute");
			return actionmapping.findForward("success");
		}
	}

	public ActionForward displayControl(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("coGetturbine - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		System.out.println("Display:::::::::::::::" + s);
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
		// setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		System.out.println("CogenTurbineAction - In Add");
		log.debug("CogenTurbineAction - In Add");
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		CogenTurbineVo cogenturbinevo = new CogenTurbineVo();
		cogenturbinevo.setBuildingId(buildingvo.getId());
		addUpdateManipulation(dynaactionform, cogenturbinevo);
		try {

			int i = CogenTurbineEntity.add(cogenturbinevo);

			CogenTurbineVo cogenturbinevo1 = CogenTurbineEntity
					.findByPrimaryKey(i);

			if (cogenturbinevo1 != null) {
				httpsession.setAttribute("COGEN_TURBINE_OBJECT",
						cogenturbinevo1);
				setFieldDetails(httpservletrequest, dynaactionform,
						cogenturbinevo1);
			}
			httpservletrequest.setAttribute("isComponentEditable", "N");

			CogenTurbineTypeEnum cogenturbinetypeenum = CogenTurbineTypeEnum
					.get(UtilityObject.getIntFromString((String) dynaactionform
							.get("type")));
			if (cogenturbinetypeenum != null) {
				s = (new StringBuilder()).append("Added ")
						.append(cogenturbinetypeenum.getName()).append(".")
						.toString();
				s1 = "CONFIRMATION";
			}
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

	private void addUpdateManipulation(DynaActionForm dynaactionform,
			CogenTurbineVo cogenturbinevo) {
		cogenturbinevo.setFacilityDesignatedId((String) dynaactionform
				.get("facilityDesignatedId"));
		// cogenturbinevo.setResourceType(UtilityObject.getIntFromString((String)dynaactionform.get("type")));
		cogenturbinevo.setResourceType(1);
		cogenturbinevo.setFloor((String) dynaactionform.get("floor"));
		cogenturbinevo.setPrimaryUse(UtilityObject
				.getIntFromString((String) dynaactionform.get("primaryUse")));
		cogenturbinevo.setHasControlSystem(UtilityObject
				.convertBoolean((String) dynaactionform.get("controlSystem")));
		cogenturbinevo.setControlEfficiency(UtilityObject
				.getIntFromString((String) dynaactionform
						.get("controlEfficiency")));
		cogenturbinevo.setYearInstalled((String) dynaactionform
				.get("yearInstalled"));
		cogenturbinevo.setStatus(Integer.parseInt((String) dynaactionform
				.get("co_status")));
		cogenturbinevo.setDisconnecteddate((String) dynaactionform
				.get("co_disconnecteddate"));
		cogenturbinevo.setManufacturer((String) dynaactionform
				.get("manufacturer"));
		cogenturbinevo.setMake((String) dynaactionform.get("make"));
		cogenturbinevo.setModel((String) dynaactionform.get("model"));
		cogenturbinevo.setSerialNumber((String) dynaactionform.get("serial"));
		cogenturbinevo.setBurnerMake((String) dynaactionform.get("burnerMake"));
		cogenturbinevo.setBurnerModel((String) dynaactionform
				.get("burnerModel"));
		String s = (String) dynaactionform.get("burnerType");

		if (UtilityObject.isNotEmpty(s) && s.equalsIgnoreCase("Y"))
			cogenturbinevo.setBurnerType(BurnerTypeEnum.DUAL_FUEL.getCode());
		else
			cogenturbinevo.setBurnerType(BurnerTypeEnum.SINGLE_FUEL.getCode());

		cogenturbinevo.setCapacity((String) dynaactionform.get("capacity"));
		cogenturbinevo.setPrimaryFuel(UtilityObject
				.getIntFromString((String) dynaactionform.get("primaryFuel")));
		cogenturbinevo
				.setSecondaryFuel(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("secondaryFuel")));
		cogenturbinevo.setFuelFromTank(UtilityObject
				.getIntFromString((String) dynaactionform.get("fuelFrom")));
		cogenturbinevo.setNycdob((String) dynaactionform.get("nycDob"));
		cogenturbinevo.setDobsignoff((String) dynaactionform.get("dobsignoff"));
		cogenturbinevo.setDobfiling((String) dynaactionform.get("dobfiling"));
		cogenturbinevo.setMea((String) dynaactionform.get("mea"));
		cogenturbinevo.setDepNumber((String) dynaactionform.get("dep"));
		cogenturbinevo.setSechedule(UtilityObject
				.convertBoolean((String) dynaactionform.get("sechduelC")));
		cogenturbinevo.setPlanApproval(UtilityObject
				.convertBoolean((String) dynaactionform.get("planApproval")));
		cogenturbinevo.setRecordsMaintained(UtilityObject
				.convertBoolean((String) dynaactionform.get("recordsInBook")));
		cogenturbinevo.setTestProtocolSubmitted(UtilityObject
				.convertBoolean((String) dynaactionform
						.get("protocolSubmitted")));
		cogenturbinevo.setTestContductedBy((String) dynaactionform
				.get("testConductedBy"));
		cogenturbinevo.setTestReportSubmitted(UtilityObject
				.convertBoolean((String) dynaactionform
						.get("testReportSubmited")));
		cogenturbinevo.setTestReportSubmittedDate((String) dynaactionform
				.get("testReportSubmitedDate"));
		cogenturbinevo.setComplyWithNoxPlan(UtilityObject
				.convertBoolean((String) dynaactionform.get("isNoxRactPlan")));
		cogenturbinevo.setRetestPlanned(UtilityObject
				.convertBoolean((String) dynaactionform.get("retestPlanned")));
		cogenturbinevo.setNextStackTestDate((String) dynaactionform
				.get("nextStackTest"));
		cogenturbinevo.setNextStackTestNote((String) dynaactionform
				.get("nextStackTestNote"));
		cogenturbinevo.setStackExhaustId(UtilityObject
				.getIntFromString((String) dynaactionform.get("stackFrom")));
		cogenturbinevo.setFuleCappingUnderLimit(UtilityObject
				.convertBoolean((String) dynaactionform.get("isFuleCapping")));
		// -----
		cogenturbinevo.setIsturbine((String) dynaactionform.get("isturbine"));
		cogenturbinevo.setTmanufacturer((String) dynaactionform
				.get("tmanufacturer"));
		cogenturbinevo.setTmake((String) dynaactionform.get("tmake"));
		cogenturbinevo.setTmodel((String) dynaactionform.get("tmodel"));
		cogenturbinevo.setTserial((String) dynaactionform.get("tserial"));
		cogenturbinevo.setTburnerMake((String) dynaactionform
				.get("tburnerMake"));
		cogenturbinevo.setTburnerModel((String) dynaactionform
				.get("tburnerModel"));
		cogenturbinevo.setTcapacity((String) dynaactionform.get("tcapacity"));
		cogenturbinevo.setTburnerType((String) dynaactionform
				.get("tburnerType"));

		System.out.println(UtilityObject.isNotEmpty((String) dynaactionform
				.get("tprimaryFuel"))
				+ "CCCCCCCC"
				+ (String) dynaactionform.get("tprimaryFuel"));
		if (UtilityObject.isNotEmpty((String) dynaactionform
				.get("tprimaryFuel")))
			cogenturbinevo.setTprimaryFuel(Integer
					.parseInt((String) dynaactionform.get("tprimaryFuel")));
		else
			cogenturbinevo.setTprimaryFuel(-1);

		if (UtilityObject.isNotEmpty((String) dynaactionform
				.get("tsecondaryFuel")))
			cogenturbinevo.setTsecondaryFuel(Integer
					.parseInt((String) dynaactionform.get("tsecondaryFuel")));
		else
			cogenturbinevo.setTsecondaryFuel(-1);

		cogenturbinevo.setIswhrb((String) dynaactionform.get("iswhrb"));
		cogenturbinevo.setWmanufacturer((String) dynaactionform
				.get("wmanufacturer"));
		cogenturbinevo.setWmake((String) dynaactionform.get("wmake"));
		cogenturbinevo.setWmodel((String) dynaactionform.get("wmodel"));
		cogenturbinevo.setWserial((String) dynaactionform.get("wserial"));
		cogenturbinevo.setWburnerMake((String) dynaactionform
				.get("wburnerMake"));
		cogenturbinevo.setWburnerModel((String) dynaactionform
				.get("wburnerModel"));
		cogenturbinevo.setWcapacity((String) dynaactionform.get("wcapacity"));
		cogenturbinevo.setWburnerType((String) dynaactionform
				.get("wburnerType"));

		if (UtilityObject.isNotEmpty((String) dynaactionform
				.get("wprimaryFuel")) == true)
			cogenturbinevo.setWprimaryFuel(Integer
					.parseInt((String) dynaactionform.get("wprimaryFuel")));
		else
			cogenturbinevo.setWprimaryFuel(-1);

		if (UtilityObject.isNotEmpty((String) dynaactionform
				.get("wsecondaryFuel")) == true)
			cogenturbinevo.setWsecondaryFuel(Integer
					.parseInt((String) dynaactionform.get("wsecondaryFuel")));
		else
			cogenturbinevo.setWsecondaryFuel(-1);

		cogenturbinevo.setCo_decPermitObtained((String) dynaactionform
				.get("co_decPermitObtained"));
		cogenturbinevo.setCo_depPermitObtained((String) dynaactionform
				.get("co_depPermitObtained"));
		cogenturbinevo.setCo_compyWithSO2RactPlan((String) dynaactionform
				.get("co_compyWithSO2RactPlan"));
		cogenturbinevo.setCo_compyWithPMRactPlan((String) dynaactionform
				.get("co_compyWithPMRactPlan"));
		cogenturbinevo.setCo_testPassed((String) dynaactionform
				.get("co_testPassed"));
		cogenturbinevo.setCo_StackTestDate((String) dynaactionform
				.get("co_StackTestDate"));
		cogenturbinevo.setCo_fuelgapping((String) dynaactionform
				.get("co_fuelgapping"));
		cogenturbinevo.setCo_gasfuelgapping((String) dynaactionform
				.get("co_gasfuelgapping"));
		cogenturbinevo.setCo_gasemmisionfactor((String) dynaactionform
				.get("co_gasemmisionfactor"));
		cogenturbinevo.setCo_oilfuelgapping((String) dynaactionform
				.get("co_oilfuelgapping"));
		cogenturbinevo.setCo_oilemmisionfactor((String) dynaactionform
				.get("co_oilemmisionfactor"));
		cogenturbinevo.setCo_Comments((String) dynaactionform
				.get("co_Comments"));
		cogenturbinevo.setCo_oilso2((String) dynaactionform.get("co_oilso2"));
		cogenturbinevo.setCo_gasso2((String) dynaactionform.get("co_gasso2"));
		cogenturbinevo.setCo_so2allowable((String) dynaactionform
				.get("co_so2allowable"));
		cogenturbinevo.setCo_noxallowable((String) dynaactionform
				.get("co_noxallowable"));

	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("CogenTurbineAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		CogenTurbineVo cogenturbinevo = CogenTurbineEntity.findByPrimaryKey(i);
		if (cogenturbinevo != null) {
			httpsession.setAttribute("COGEN_TURBINE_OBJECT", cogenturbinevo);
			setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		}
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("CogenTurbineAction - In edit");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		CogenTurbineVo cogenturbinevo = CogenTurbineEntity.findByPrimaryKey(i);
		if (cogenturbinevo != null) {
			httpsession.setAttribute("COGEN_TURBINE_OBJECT", cogenturbinevo);
			setFieldDetailsforedit(httpservletrequest, dynaactionform,
					cogenturbinevo, true);
		}
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("CogenTurbineAction - In edit");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		CogenTurbineVo cogenturbinevo = CogenTurbineEntity.findByPrimaryKey(i);
		if (cogenturbinevo != null) {
			httpsession.setAttribute("COGEN_TURBINE_OBJECT", cogenturbinevo);
			setFieldDetailsforedit(httpservletrequest, dynaactionform,
					cogenturbinevo, true);
		}
		httpservletrequest.setAttribute("isItForEdit", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = "";
		String s3 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("CogenTurbineAction - In update");
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		String facilityDesignatedid = cogenturbinevo.getFacilityDesignatedId();
		addUpdateManipulation(dynaactionform, cogenturbinevo);
		try {
			CogenTurbineEntity.update(cogenturbinevo);
			cogenturbinevo = CogenTurbineEntity.findByPrimaryKey(cogenturbinevo
					.getId());
			if (cogenturbinevo != null) {

				FacilityVo facilityvo = (FacilityVo) httpsession
						.getAttribute("FACILITY_OBJECT");
				try {

					File f = new File(httpservletrequest.getRealPath("/")
							+ "/clientfolder/" + facilityvo.getDecId()
							+ "/cogenengine/" + cogenturbinevo.getId() + "-"
							+ facilityDesignatedid.trim());

					if (f.isDirectory()) {

						File newFileName = new File(
								httpservletrequest.getRealPath("/")
										+ "/clientfolder/"
										+ facilityvo.getDecId()
										+ "/cogenengine/"
										+ cogenturbinevo.getId()
										+ "-"
										+ cogenturbinevo
												.getFacilityDesignatedId()
												.trim());
						f.renameTo(newFileName);

					} else {

					}
				} catch (Exception e) {
					System.out.println("Exception: " + e);
				}

				httpsession
						.setAttribute("COGEN_TURBINE_OBJECT", cogenturbinevo);
				setFieldDetails(httpservletrequest, dynaactionform,
						cogenturbinevo);
			}
			CogenTurbineTypeEnum cogenturbinetypeenum = CogenTurbineTypeEnum
					.get(UtilityObject.getIntFromString((String) dynaactionform
							.get("type")));
			if (cogenturbinetypeenum != null) {
				String s1 = (new StringBuilder()).append("Updated ")
						.append(cogenturbinetypeenum.getName()).append(".")
						.toString();
				String s4 = "CONFIRMATION";
			}
		} catch (TrackingException trackingexception) {
			String s2 = trackingexception.getMessage();
			String s5 = "ERROR";
			if (log.isErrorEnabled())
				log.error(trackingexception);
		}
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("CogenTurbineAction - In view");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynaactionform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		CogenTurbineVo cogenturbinevo = CogenTurbineEntity.findByPrimaryKey(i);
		if (cogenturbinevo != null)
			httpsession.setAttribute("COGEN_TURBINE_OBJECT", cogenturbinevo);
		CogenTurbineVo cogenturbinevo1 = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		try {
			CogenTurbineEntity.delete(cogenturbinevo1);
			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/cogenengine/" + cogenturbinevo1.getId() + "-"
						+ cogenturbinevo1.getFacilityDesignatedId().trim());
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
		return actionmapping.findForward("successs2");
	}

	public ActionForward depPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In depPermitInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setPermitNumber("");
		permitinfovo.setObjectId(cogenturbinevo.getId());
		permitinfovo.setNote((String) dynaactionform.get("note"));
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depIssueDate")));
		permitinfovo.setDepId(DepartmentEnum.NYCDEP.getCode());
		permitinfovo.setYear((String) dynaactionform.get("depYear"));
		int i = CogenTurbineEntity.addPermit(permitinfovo);
		log.debug((new StringBuilder()).append("Added dep permit id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editDepPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In editDepPermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("CGNTRBN_EDIT_DEP_PERMIT", "Y");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");
	}

	public ActionForward deleteDepPermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("GeneratorAction - In updateDepPermitInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the DEP Permit");
		PermitInfoVo permitinfovo = CogenTurbineEntity
				.findPermitByPrimaryKey(i);
		CogenTurbineEntity.deletePermit(permitinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward updateDepPermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("GeneratorAction - In updateDepPermitInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"DEP Permit Id is null while updating the DEP Permit");
		PermitInfoVo permitinfovo = CogenTurbineEntity
				.findPermitByPrimaryKey(i);
		permitinfovo.setNote((String) dynaactionform.get("note"));
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("depIssueDate")));
		permitinfovo.setYear((String) dynaactionform.get("depYear"));
		CogenTurbineEntity.updatePermit(permitinfovo);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward statePermitInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In statePermitInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		PermitInfoVo permitinfovo = new PermitInfoVo();
		permitinfovo.setPermitNumber((String) dynaactionform
				.get("statePermitNumber"));
		permitinfovo.setObjectId(cogenturbinevo.getId());
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateIssueDate")));
		permitinfovo.setDepId(DepartmentEnum.STATE_AGENCY.getCode());
		permitinfovo.setYear((String) dynaactionform.get("stateYear"));
		int i = CogenTurbineEntity.addPermit(permitinfovo);
		log.debug((new StringBuilder()).append("Added dep permit id=")
				.append(i).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward editStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In editStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		httpservletrequest.setAttribute("isComponentEditable", "N");
		httpservletrequest.setAttribute("CGNTRBN_EDIT_STATE_PERMIT", "Y");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		httpservletrequest.setAttribute("hdnPermitId",
				httpservletrequest.getParameter("hdnPermitId"));
		return actionmapping.findForward("success");
	}

	public ActionForward deleteStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In updateStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit");
		PermitInfoVo permitinfovo = CogenTurbineEntity
				.findPermitByPrimaryKey(i);
		CogenTurbineEntity.deletePermit(permitinfovo);
		log.debug((new StringBuilder()).append("Updated dep permit id=")
				.append(permitinfovo.getId()).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward updateStatePermit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In updateStatePermit");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		String s = httpservletrequest.getParameter("hdnPermitId");
		int i = -99;
		if (UtilityObject.isNotEmpty(s))
			i = Integer.parseInt(s);
		else
			throw new Exception(
					"State Permit Id is null while updating the State Permit");
		PermitInfoVo permitinfovo = CogenTurbineEntity
				.findPermitByPrimaryKey(i);
		permitinfovo.setPermitNumber((String) dynaactionform
				.get("statePermitNumber"));
		permitinfovo.setExpirationDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateExpDate")));
		permitinfovo.setIssueDate(UtilityObject
				.convertToDate((String) dynaactionform.get("stateIssueDate")));
		permitinfovo.setYear((String) dynaactionform.get("stateYear"));
		CogenTurbineEntity.updatePermit(permitinfovo);
		log.debug((new StringBuilder()).append("Updated dep permit id=")
				.append(permitinfovo.getId()).toString());
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward deletefuelConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Co-GeneratorAction - In fuelConsumptionInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(cogenturbinevo.getId());
		int tid = Integer.parseInt((String) dynaactionform
				.get("fueldeleteConsumptionId"));
		CogenTurbineEntity.deletefuelconsumption(tid);
		cogenturbinevo.seto_FuelConsumptionList(null);
		cogenturbinevo.setFuelConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward fuelConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("CogenTurbineAction - In fuelConsumptionInfo");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		int i = cogenturbinevo.getResourceType();
		ResourcesTypeEnum resourcestypeenum = ResourcesTypeEnum.get(i);
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(cogenturbinevo.getId());
		String ttype = (String) dynaactionform.get("bctype");
		if (ttype.equals("1")) {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("fcyear"));
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
				CogenTurbineEntity.updateFuelConsumption(fuelconsumptionvo,
						ResourcesTypeEnum.TURBINES);
				log.debug("Updated fuel object");
			} else {
				int j = CogenTurbineEntity.addFuelConsumption(
						fuelconsumptionvo, ResourcesTypeEnum.TURBINES);
				log.debug((new StringBuilder()).append("Added fuel id=")
						.append(j).toString());
			}
		} else {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("o_fcyear"));
			fuelconsumptionvo.setJan(getValue("o_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("o_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("o_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("o_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("o_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("o_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("o_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("o_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("o_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("o_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("o_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("o_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("o_consumption",
					dynaactionform));
			fuelconsumptionvo
					.setCompliant(UtilityObject
							.convertBoolean((String) dynaactionform
									.get("o_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("o_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("o_fuelConsumptionId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("o_fuelConsumptionId")));
				CogenTurbineEntity.updateFuelConsumption(fuelconsumptionvo,
						ResourcesTypeEnum.TURBINES);
				log.debug("Updated fuel object");
			} else {
				int j = CogenTurbineEntity.addFuelConsumption(
						fuelconsumptionvo, ResourcesTypeEnum.TURBINES);
				log.debug((new StringBuilder()).append("Added fuel id=")
						.append(j).toString());
			}
		}
		cogenturbinevo.setFuelConsumptionList(null);
		cogenturbinevo.seto_FuelConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFieldDetails(httpservletrequest, dynaactionform, cogenturbinevo);
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		CogenTurbineVo cogenturbinevo = (CogenTurbineVo) httpsession
				.getAttribute("COGEN_TURBINE_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/cogenengine/" + cogenturbinevo.getId() + "-"
					+ cogenturbinevo.getFacilityDesignatedId().trim());
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
					+ "/cogenengine/" + cogenturbinevo.getId() + "-"
					+ cogenturbinevo.getFacilityDesignatedId().trim();
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
				+ "/cogenengine/" + cogenturbinevo.getId() + "-"
				+ cogenturbinevo.getFacilityDesignatedId().trim());

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

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
			return s;
		else
			return s1;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static double round(double d, int i) {
		double d1;
		for (d1 = 1.0D; i-- > 0; d1 *= 10D)
			;
		return (double) Math.round(d * d1) / d1;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.CogenTurbineAction.class);

}