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

import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.PrintingPressVo;
import com.eespc.tracking.bo.myenum.ModifiedIn;
import com.eespc.tracking.entity.PrintingPressEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class PrintingPressAction extends LookupDispatchAction {

	public PrintingPressAction() {
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

			log.debug("PrintingPressAction - MethodToCall " + userAction);

			if (userAction != null && userAction.equalsIgnoreCase("Save"))
				return save(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("Update"))
				return update(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(mapping, form, request, response);
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

			else if (userAction != null
					&& userAction.equalsIgnoreCase("on_inkConsumptionInfo"))
				return on_inkConsumptionInfo(mapping, form, request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("on_inkConsumptionInfoyearconsumtion"))
				return on_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("to_inkConsumptionInfoyearconsumtion"))
				return to_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("th_inkConsumptionInfoyearconsumtion"))
				return th_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("fr_inkConsumptionInfoyearconsumtion"))
				return fr_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("fi_inkConsumptionInfoyearconsumtion"))
				return fi_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("si_inkConsumptionInfoyearconsumtion"))
				return si_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction
							.equalsIgnoreCase("se_inkConsumptionInfoyearconsumtion"))
				return se_inkConsumptionInfoyearconsumtion(mapping, form,
						request, response);

			else if (userAction != null
					&& userAction.equalsIgnoreCase("deleteinkConsumptionInfo"))
				return deleteinkConsumptionInfo(mapping, form, request,
						response);

		} catch (Exception e) {
			System.out.println("Printing Press Execute Exception" + e);
		}

		dynavalidatorform.set("facilitydesinatedId", "");
		dynavalidatorform.set("ModifiedInPast", "");
		dynavalidatorform.set("DisconnectedYear", "");
		dynavalidatorform.set("Pcomments", "");
		dynavalidatorform.set("decpermit", "");
		dynavalidatorform.set("stacktestrequired", "");
		dynavalidatorform.set("isvoc", "");
		dynavalidatorform.set("ispm", "");
		dynavalidatorform.set("isother", "");
		dynavalidatorform.set("depnumber", "");
		dynavalidatorform.set("depissuedate", "");
		dynavalidatorform.set("depexpirationdate", "");
		dynavalidatorform.set("stackexhastlocation", "");
		dynavalidatorform.set("height", "");
		dynavalidatorform.set("diameter", "");
		dynavalidatorform.set("velocity", "");
		dynavalidatorform.set("filterused", "");
		dynavalidatorform.set("effiviancy", "");
		dynavalidatorform.set("solvantcap", "");
		dynavalidatorform.set("annualconsumption", "");
		dynavalidatorform.set("Ink1", "");
		dynavalidatorform.set("Ink2", "");
		dynavalidatorform.set("Ink3", "");
		dynavalidatorform.set("Ink4", "");
		dynavalidatorform.set("FountainSolution", "");
		dynavalidatorform.set("CleaningAgent", "");
		dynavalidatorform.set("Ink1name", "");
		dynavalidatorform.set("Ink2name", "");
		dynavalidatorform.set("Ink3name", "");
		dynavalidatorform.set("Ink4name", "");
		dynavalidatorform.set("FountainSolutionname", "");
		dynavalidatorform.set("CleaningAgentname", "");
		dynavalidatorform.set("Ink1volume", "");
		dynavalidatorform.set("Ink2volume", "");
		dynavalidatorform.set("Ink3volume", "");
		dynavalidatorform.set("Ink4volume", "");
		dynavalidatorform.set("FountainSolutionvolume", "");
		dynavalidatorform.set("CleaningAgentvolume", "");
		dynavalidatorform.set("Ink1density", "");
		dynavalidatorform.set("Ink2density", "");
		dynavalidatorform.set("Ink3density", "");
		dynavalidatorform.set("Ink4density", "");
		dynavalidatorform.set("FountainSolutiondensity", "");
		dynavalidatorform.set("CleaningAgentdensity", "");
		dynavalidatorform.set("Ink1vocpercent", "");
		dynavalidatorform.set("Ink2vocpercent", "");
		dynavalidatorform.set("Ink3vocpercent", "");
		dynavalidatorform.set("Ink4vocpercent", "");
		dynavalidatorform.set("FountainSolutionvocpercent", "");
		dynavalidatorform.set("CleaningAgentvocpercent", "");
		dynavalidatorform.set("Ink1voc", "");
		dynavalidatorform.set("Ink2voc", "");
		dynavalidatorform.set("Ink3voc", "");
		dynavalidatorform.set("Ink4voc", "");
		dynavalidatorform.set("dobpermit", "");
		dynavalidatorform.set("totalvoc", "");
		dynavalidatorform.set("FountainSolutionvoc", "");
		dynavalidatorform.set("CleaningAgentvoc", "");
		dynavalidatorform.set("dobsignoff", "");
		request.setAttribute("SHOW_BUTTONS", "inline");
		request.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		return mapping.findForward("success");
	}

	public ActionForward on_inkConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();

		PrintingPressVo printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(printingpressvo.getId());
		String ttype = (String) dynaactionform.get("bctype");
		if (ttype.equals("1")) {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("on_fcyear"));
			fuelconsumptionvo.setJan(getValue("on_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("on_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("on_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("on_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("on_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("on_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("on_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("on_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("on_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("on_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("on_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("on_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("on_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("on_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("on_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkOneId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkOneId")));
				// PrintingPressEntity.updateInkConsumption(fuelconsumptionvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());
			}

		} else if (ttype.equals("2")) {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("to_fcyear"));
			fuelconsumptionvo.setJan(getValue("to_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("to_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("to_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("to_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("to_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("to_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("to_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("to_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("to_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("to_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("to_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("to_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("to_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("to_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("to_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkTwoId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkTwoId")));
				// PrintingPressEntity.updateInkConsumption(pressinkvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());
			}

		} else if (ttype.equals("3")) {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("th_fcyear"));
			fuelconsumptionvo.setJan(getValue("th_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("th_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("th_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("th_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("th_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("th_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("th_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("th_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("th_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("th_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("th_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("th_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("th_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("th_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("th_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkThreeId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkThreeId")));
				// PrintingPressEntity.updateInkConsumption(pressinkvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());
			}

		}

		else if (ttype.equals("4")) {
			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("fr_fcyear"));
			fuelconsumptionvo.setJan(getValue("fr_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("fr_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("fr_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("fr_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("fr_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("fr_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("fr_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("fr_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("fr_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("fr_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("fr_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("fr_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("fr_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("fr_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("fr_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkFourId"))) {
				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkFourId")));
				// PrintingPressEntity.updateInkConsumption(pressinkvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());

			}

		}

		else if (ttype.equals("5")) {

			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("fi_fcyear"));
			fuelconsumptionvo.setJan(getValue("fi_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("fi_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("fi_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("fi_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("fi_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("fi_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("fi_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("fi_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("fi_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("fi_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("fi_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("fi_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("fi_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("fi_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("fi_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkFiveId"))) {

				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkFiveId")));
				// PrintingPressEntity.updateInkConsumption(pressinkvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());
			}

		} else if (ttype.equals("6")) {

			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("si_fcyear"));
			fuelconsumptionvo.setJan(getValue("si_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("si_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("si_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("si_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("si_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("si_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("si_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("si_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("si_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("si_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("si_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("si_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("si_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("si_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("si_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkSixId"))) {

				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkSixId")));
				// PrintingPressEntity.updateInkConsumption(pressinkvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());
			}

		}

		else if (ttype.equals("7")) {

			fuelconsumptionvo.setBctype(ttype);
			fuelconsumptionvo.setYear((String) dynaactionform.get("se_fcyear"));
			fuelconsumptionvo.setJan(getValue("se_jan", dynaactionform));
			fuelconsumptionvo.setFeb(getValue("se_feb", dynaactionform));
			fuelconsumptionvo.setMar(getValue("se_mar", dynaactionform));
			fuelconsumptionvo.setApr(getValue("se_apr", dynaactionform));
			fuelconsumptionvo.setMay(getValue("se_may", dynaactionform));
			fuelconsumptionvo.setJun(getValue("se_jun", dynaactionform));
			fuelconsumptionvo.setJul(getValue("se_jul", dynaactionform));
			fuelconsumptionvo.setAug(getValue("se_aug", dynaactionform));
			fuelconsumptionvo.setSep(getValue("se_sep", dynaactionform));
			fuelconsumptionvo.setOct(getValue("se_oct", dynaactionform));
			fuelconsumptionvo.setNov(getValue("se_nov", dynaactionform));
			fuelconsumptionvo.setDec(getValue("se_dec", dynaactionform));
			fuelconsumptionvo.setRollingConsumption(getValue("se_consumption",
					dynaactionform));
			fuelconsumptionvo.setCompliant(UtilityObject
					.convertBoolean((String) dynaactionform
							.get("se_compliance")));
			fuelconsumptionvo.setLocked(UtilityObject
					.convertBoolean((String) dynaactionform.get("se_lock")));
			if (UtilityObject.isNotEmpty((String) dynaactionform
					.get("inkSevenId"))) {

				fuelconsumptionvo.setId(UtilityObject
						.getIntFromString((String) dynaactionform
								.get("inkSevenId")));
				// PrintingPressEntity.updateInkConsumption(pressinkvo);
				log.debug("Updated ink object");
			} else {
				int i = PrintingPressEntity
						.addInkConsumption(fuelconsumptionvo);
				log.debug((new StringBuilder()).append("Added ink id=")
						.append(i).toString());
			}

		}
		printingpressvo.setInkConsumptionList(null);
		printingpressvo.setto_InkConsumptionList(null);
		printingpressvo.setth_InkConsumptionList(null);
		printingpressvo.setfr_InkConsumptionList(null);
		printingpressvo.setfi_InkConsumptionList(null);
		printingpressvo.setsi_InkConsumptionList(null);
		printingpressvo.setse_InkConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);

		return actionmapping.findForward("success");
	}

	public ActionForward on_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();

		List list = printingpressentity.geton_InkConsumptionyearList(
				printingpressvo.getId(), year);

		System.out.println("eeeeeeeeee" + list.size());
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

	public ActionForward to_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();

		List list = printingpressentity.getto_InkConsumptionyearList(
				printingpressvo.getId(), year);

		System.out.println("ffffff" + list.size());
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

	public ActionForward th_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();

		List list = printingpressentity.getth_InkConsumptionyearList(
				printingpressvo.getId(), year, 3);

		System.out.println("ffffff" + list.size());
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

	public ActionForward fr_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();

		List list = printingpressentity.getfr_InkConsumptionyearList(
				printingpressvo.getId(), year, 4);

		System.out.println("gggggg" + list.size());
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

	public ActionForward fi_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();
		List list = printingpressentity.getfi_InkConsumptionyearList(
				printingpressvo.getId(), year, 5);

		System.out.println("CCCCCCCCC" + list.size());
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

	public ActionForward si_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();

		List list = printingpressentity.getsi_InkConsumptionyearList(
				printingpressvo.getId(), year, 6);

		System.out.println("ffffff" + list.size());
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

	public ActionForward se_inkConsumptionInfoyearconsumtion(
			ActionMapping actionmapping, ActionForm actionform,
			HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = (String) dynaactionform.get("on_fcyear");
		System.out.println("Current Year" + s);
		String year = String.valueOf((Integer.parseInt(s) - 1));
		System.out.println("Prev Year" + year);
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		PrintingPressEntity printingpressentity = new PrintingPressEntity();

		List list = printingpressentity.getse_InkConsumptionyearList(
				printingpressvo.getId(), year, 7);

		System.out.println("ffffff" + list.size());
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

	public ActionForward deleteinkConsumptionInfo(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		log.debug("PressAction - In inkConsumptionInfo");

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		FuelConsumptionVo fuelconsumptionvo = new FuelConsumptionVo();
		fuelconsumptionvo.setEntityId(printingpressvo.getId());
		int tid = Integer.parseInt((String) dynaactionform
				.get("inkdeleteConsumptionId"));
		PrintingPressEntity.deleteinkconsumption(tid);
		printingpressvo.setInkConsumptionList(null);
		printingpressvo.setto_InkConsumptionList(null);
		httpservletrequest.setAttribute("isComponentEditable", "N");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);
		// setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward addnewfolder(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);
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
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);

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
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);
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
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);
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
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);

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
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		setFormVariable(dynaactionform, printingpressvo, httpservletrequest);
		httpservletrequest.setAttribute("SHOW_BUTTONS", "none");
		httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		httpservletrequest.setAttribute("isComponentEditable", "N");

		String back = "";

		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		String path = (String) dynaactionform.get("pathname");
		System.out.println("path:" + path);
		if (path.equals(facilityvo.getDecId() + "/printingpress/"
				+ printingpressvo.getId() + "-"
				+ printingpressvo.getFacilitydesinatedId().trim())) {
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
		log.debug("Printing Press - In displayControl");
		String s = httpservletrequest.getParameter("hdnContext");
		System.out.println("Printing Press display:" + s);
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

		log.debug("Press - ADD");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		PrintingPressVo printingpressvo = new PrintingPressVo();
		printingpressvo.setBuildingId(buildingvo.getId());

		printingpressvo.setFacilitydesinatedId((String) dynaactionform
				.get("facilitydesinatedId"));
		printingpressvo.setModifiedInPast(Integer
				.parseInt((String) dynaactionform.get("ModifiedInPast")));
		printingpressvo.setDisconnectedYear((String) dynaactionform
				.get("DisconnectedYear"));
		printingpressvo.setPcomments((String) dynaactionform.get("Pcomments"));
		printingpressvo.setDecpermit((String) dynaactionform.get("decpermit"));
		printingpressvo.setStacktestrequired((String) dynaactionform
				.get("stacktestrequired"));
		printingpressvo.setIsvoc((String) dynaactionform.get("isvoc"));
		printingpressvo.setIspm((String) dynaactionform.get("ispm"));
		printingpressvo.setIsother((String) dynaactionform.get("isother"));
		printingpressvo.setDepnumber((String) dynaactionform.get("depnumber"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("depissuedate")),
				"yyyy-MM-dd");
		printingpressvo.setDepissuedate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("depexpirationdate")), "yyyy-MM-dd");
		printingpressvo.setDepexpirationdate(sd);
		printingpressvo.setStackexhastlocation((String) dynaactionform
				.get("stackexhastlocation"));
		printingpressvo.setHeight((String) dynaactionform.get("height"));
		printingpressvo.setDiameter((String) dynaactionform.get("diameter"));
		printingpressvo.setVelocity((String) dynaactionform.get("velocity"));
		printingpressvo
				.setFilterused((String) dynaactionform.get("filterused"));
		printingpressvo
				.setEffiviancy((String) dynaactionform.get("effiviancy"));
		printingpressvo
				.setSolvantcap((String) dynaactionform.get("solvantcap"));
		printingpressvo.setAnnualconsumption((String) dynaactionform
				.get("annualconsumption"));
		printingpressvo.setInk1((String) dynaactionform.get("Ink1"));
		printingpressvo.setInk2((String) dynaactionform.get("Ink2"));
		printingpressvo.setInk3((String) dynaactionform.get("Ink3"));
		printingpressvo.setInk4((String) dynaactionform.get("Ink4"));
		printingpressvo.setFountainSolution((String) dynaactionform
				.get("FountainSolution"));
		printingpressvo.setCleaningAgent((String) dynaactionform
				.get("CleaningAgent"));
		printingpressvo.setInk1name((String) dynaactionform.get("Ink1name"));
		printingpressvo.setInk2name((String) dynaactionform.get("Ink2name"));
		printingpressvo.setInk3name((String) dynaactionform.get("Ink3name"));
		printingpressvo.setInk4name((String) dynaactionform.get("Ink4name"));
		printingpressvo.setFountainSolutionname((String) dynaactionform
				.get("FountainSolutionname"));
		printingpressvo.setCleaningAgentname((String) dynaactionform
				.get("CleaningAgentname"));
		printingpressvo
				.setInk1volume((String) dynaactionform.get("Ink1volume"));
		printingpressvo
				.setInk2volume((String) dynaactionform.get("Ink2volume"));
		printingpressvo
				.setInk3volume((String) dynaactionform.get("Ink3volume"));
		printingpressvo
				.setInk4volume((String) dynaactionform.get("Ink4volume"));
		printingpressvo.setFountainSolutionvolume((String) dynaactionform
				.get("FountainSolutionvolume"));
		printingpressvo.setCleaningAgentvolume((String) dynaactionform
				.get("CleaningAgentvolume"));
		printingpressvo.setInk1density((String) dynaactionform
				.get("Ink1density"));
		printingpressvo.setInk2density((String) dynaactionform
				.get("Ink2density"));
		printingpressvo.setInk3density((String) dynaactionform
				.get("Ink3density"));
		printingpressvo.setInk4density((String) dynaactionform
				.get("Ink4density"));
		printingpressvo.setFountainSolutiondensity((String) dynaactionform
				.get("FountainSolutiondensity"));
		printingpressvo.setCleaningAgentdensity((String) dynaactionform
				.get("CleaningAgentdensity"));
		printingpressvo.setInk1vocpercent((String) dynaactionform
				.get("Ink1vocpercent"));
		printingpressvo.setInk2vocpercent((String) dynaactionform
				.get("Ink2vocpercent"));
		printingpressvo.setInk3vocpercent((String) dynaactionform
				.get("Ink3vocpercent"));
		printingpressvo.setInk4vocpercent((String) dynaactionform
				.get("Ink4vocpercent"));
		printingpressvo.setFountainSolutionvocpercent((String) dynaactionform
				.get("FountainSolutionvocpercent"));
		printingpressvo.setCleaningAgentvocpercent((String) dynaactionform
				.get("CleaningAgentvocpercent"));
		printingpressvo.setInk1voc((String) dynaactionform.get("Ink1voc"));
		printingpressvo.setInk2voc((String) dynaactionform.get("Ink2voc"));
		printingpressvo.setInk3voc((String) dynaactionform.get("Ink3voc"));
		printingpressvo.setInk4voc((String) dynaactionform.get("Ink4voc"));
		printingpressvo.setFountainSolutionvoc((String) dynaactionform
				.get("FountainSolutionvoc"));
		printingpressvo.setCleaningAgentvoc((String) dynaactionform
				.get("CleaningAgentvoc"));
		printingpressvo.setDobpermit((String) dynaactionform.get("dobpermit"));
		printingpressvo.setTotalvoc((String) dynaactionform.get("totalvoc"));
		printingpressvo
				.setDobsignoff((String) dynaactionform.get("dobsignoff"));

		byte byte0 = -99;
		try {

			int i = PrintingPressEntity.add(printingpressvo);

			printingpressvo = PrintingPressEntity.findByPrimaryKey(i);

			if (printingpressvo != null) {
				httpsession.setAttribute("PRESS_OBJECT", printingpressvo);

				setFormVariable(dynaactionform, printingpressvo,
						httpservletrequest);

			}
			s = "Added Printing Press Successfully.";
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
		log.debug("Press - Update");
		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();

		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		PrintingPressVo printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");

		String facilityDesignatedid = printingpressvo.getFacilitydesinatedId();
		printingpressvo.setBuildingId(buildingvo.getId());
		printingpressvo.setFacilitydesinatedId((String) dynaactionform
				.get("facilitydesinatedId"));
		printingpressvo.setModifiedInPast(Integer
				.parseInt((String) dynaactionform.get("ModifiedInPast")));
		printingpressvo.setDisconnectedYear((String) dynaactionform
				.get("DisconnectedYear"));
		// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
		printingpressvo.setPcomments((String) dynaactionform.get("Pcomments"));
		printingpressvo.setDecpermit((String) dynaactionform.get("decpermit"));
		printingpressvo.setStacktestrequired((String) dynaactionform
				.get("stacktestrequired"));
		printingpressvo.setIsvoc((String) dynaactionform.get("isvoc"));
		printingpressvo.setIspm((String) dynaactionform.get("ispm"));
		printingpressvo.setIsother((String) dynaactionform.get("isother"));
		printingpressvo.setDepnumber((String) dynaactionform.get("depnumber"));
		String sd = "";
		sd = UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("depissuedate")),
				"yyyy-MM-dd");
		printingpressvo.setDepissuedate(sd);
		sd = UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("depexpirationdate")), "yyyy-MM-dd");
		printingpressvo.setDepexpirationdate(sd);

		printingpressvo.setStackexhastlocation((String) dynaactionform
				.get("stackexhastlocation"));
		printingpressvo.setHeight((String) dynaactionform.get("height"));
		printingpressvo.setDiameter((String) dynaactionform.get("diameter"));
		printingpressvo.setVelocity((String) dynaactionform.get("velocity"));
		printingpressvo
				.setFilterused((String) dynaactionform.get("filterused"));
		printingpressvo
				.setEffiviancy((String) dynaactionform.get("effiviancy"));
		printingpressvo
				.setSolvantcap((String) dynaactionform.get("solvantcap"));
		printingpressvo.setAnnualconsumption((String) dynaactionform
				.get("annualconsumption"));
		printingpressvo.setInk1((String) dynaactionform.get("Ink1"));
		printingpressvo.setInk2((String) dynaactionform.get("Ink2"));
		printingpressvo.setInk3((String) dynaactionform.get("Ink3"));
		printingpressvo.setInk4((String) dynaactionform.get("Ink4"));
		printingpressvo.setFountainSolution((String) dynaactionform
				.get("FountainSolution"));
		printingpressvo.setCleaningAgent((String) dynaactionform
				.get("CleaningAgent"));
		printingpressvo.setInk1name((String) dynaactionform.get("Ink1name"));
		printingpressvo.setInk2name((String) dynaactionform.get("Ink2name"));
		printingpressvo.setInk3name((String) dynaactionform.get("Ink3name"));
		printingpressvo.setInk4name((String) dynaactionform.get("Ink4name"));
		printingpressvo.setFountainSolutionname((String) dynaactionform
				.get("FountainSolutionname"));
		printingpressvo.setCleaningAgentname((String) dynaactionform
				.get("CleaningAgentname"));
		printingpressvo
				.setInk1volume((String) dynaactionform.get("Ink1volume"));
		printingpressvo
				.setInk2volume((String) dynaactionform.get("Ink2volume"));
		printingpressvo
				.setInk3volume((String) dynaactionform.get("Ink3volume"));
		printingpressvo
				.setInk4volume((String) dynaactionform.get("Ink4volume"));
		printingpressvo.setFountainSolutionvolume((String) dynaactionform
				.get("FountainSolutionvolume"));
		printingpressvo.setCleaningAgentvolume((String) dynaactionform
				.get("CleaningAgentvolume"));
		printingpressvo.setInk1density((String) dynaactionform
				.get("Ink1density"));
		printingpressvo.setInk2density((String) dynaactionform
				.get("Ink2density"));
		printingpressvo.setInk3density((String) dynaactionform
				.get("Ink3density"));
		printingpressvo.setInk4density((String) dynaactionform
				.get("Ink4density"));
		printingpressvo.setFountainSolutiondensity((String) dynaactionform
				.get("FountainSolutiondensity"));
		printingpressvo.setCleaningAgentdensity((String) dynaactionform
				.get("CleaningAgentdensity"));
		printingpressvo.setInk1vocpercent((String) dynaactionform
				.get("Ink1vocpercent"));
		printingpressvo.setInk2vocpercent((String) dynaactionform
				.get("Ink2vocpercent"));
		printingpressvo.setInk3vocpercent((String) dynaactionform
				.get("Ink3vocpercent"));
		printingpressvo.setInk4vocpercent((String) dynaactionform
				.get("Ink4vocpercent"));
		printingpressvo.setFountainSolutionvocpercent((String) dynaactionform
				.get("FountainSolutionvocpercent"));
		printingpressvo.setCleaningAgentvocpercent((String) dynaactionform
				.get("CleaningAgentvocpercent"));
		printingpressvo.setInk1voc((String) dynaactionform.get("Ink1voc"));
		printingpressvo.setInk2voc((String) dynaactionform.get("Ink2voc"));
		printingpressvo.setInk3voc((String) dynaactionform.get("Ink3voc"));
		printingpressvo.setInk4voc((String) dynaactionform.get("Ink4voc"));
		printingpressvo.setFountainSolutionvoc((String) dynaactionform
				.get("FountainSolutionvoc"));
		printingpressvo.setCleaningAgentvoc((String) dynaactionform
				.get("CleaningAgentvoc"));
		printingpressvo.setDobpermit((String) dynaactionform.get("dobpermit"));
		printingpressvo.setTotalvoc((String) dynaactionform.get("totalvoc"));
		printingpressvo
				.setDobsignoff((String) dynaactionform.get("dobsignoff"));

		byte byte0 = -99;
		try {

			PrintingPressEntity.update(printingpressvo);
			s = "Updated Printing Press Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, printingpressvo, httpservletrequest);
			httpservletrequest.setAttribute("isComponentEditable", "N");

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/printingpress/" + printingpressvo.getId() + "-"
						+ facilityDesignatedid.trim());

				if (f.isDirectory()) {

					File newFileName = new File(
							httpservletrequest.getRealPath("/")
									+ "/clientfolder/"
									+ facilityvo.getDecId()
									+ "/printingpress/"
									+ printingpressvo.getId()
									+ "-"
									+ printingpressvo.getFacilitydesinatedId()
											.trim());
					f.renameTo(newFileName);
				} else {

				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			}

		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
			// System.out.println("Exception "+trackingexception);
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
			// log.debug("Press - In Delete");

			DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynaactionform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			PrintingPressVo printingpressvo = PrintingPressEntity
					.findByPrimaryKey(i);
			if (printingpressvo != null)
				httpsession.setAttribute("PRESS_OBJECT", printingpressvo);
			PrintingPressVo printingpressvox = (PrintingPressVo) httpsession
					.getAttribute("PRESS_OBJECT");
			PrintingPressEntity.delete(printingpressvox);

			FacilityVo facilityvo = (FacilityVo) httpsession
					.getAttribute("FACILITY_OBJECT");
			try {

				File f = new File(httpservletrequest.getRealPath("/")
						+ "/clientfolder/" + facilityvo.getDecId()
						+ "/printingpress/" + printingpressvox.getId() + "-"
						+ printingpressvox.getFacilitydesinatedId().trim());
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
		return actionmapping.findForward("successpress");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			PrintingPressVo printingpressvo,
			HttpServletRequest httpservletrequest) {
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("PRINTINGPRESS setForm variable");
		if (printingpressvo == null)
			return;
		dynavalidatorform.set("facilitydesinatedId",
				printingpressvo.getFacilitydesinatedId());
		ModifiedIn modifiedin = ModifiedIn.get(printingpressvo
				.getModifiedInPast());

		if (modifiedin != null && userAction.equalsIgnoreCase("edit"))
			dynavalidatorform.set("ModifiedInPast", (new StringBuilder())
					.append(printingpressvo.getModifiedInPast()).append("")
					.toString());
		else if (modifiedin != null && userAction.equalsIgnoreCase("viewexist"))
			dynavalidatorform.set("ModifiedInPast", (new StringBuilder())
					.append(printingpressvo.getModifiedInPast()).append("")
					.toString());

		else if (modifiedin != null && !userAction.equalsIgnoreCase("edit"))
			dynavalidatorform.set("ModifiedInPast", modifiedin.getName());
		else
			dynavalidatorform.set("ModifiedInPast", "");

		dynavalidatorform.set("DisconnectedYear",
				printingpressvo.getDisconnectedYear());
		dynavalidatorform.set("Pcomments", printingpressvo.getPcomments());
		dynavalidatorform.set("decpermit", printingpressvo.getDecpermit());
		dynavalidatorform.set("stacktestrequired",
				printingpressvo.getStacktestrequired());
		dynavalidatorform.set("isvoc", printingpressvo.getIsvoc());
		dynavalidatorform.set("ispm", printingpressvo.getIspm());
		dynavalidatorform.set("isother", printingpressvo.getIsother());
		dynavalidatorform.set("depnumber", printingpressvo.getDepnumber());
		dynavalidatorform.set("depissuedate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(printingpressvo.getDepissuedate()));
		dynavalidatorform.set("depexpirationdate", UtilityObject
				.convertYyyyMmDd2MmDdYyyy(printingpressvo
						.getDepexpirationdate()));
		dynavalidatorform.set("stackexhastlocation",
				printingpressvo.getStackexhastlocation());
		dynavalidatorform.set("height", printingpressvo.getHeight());
		dynavalidatorform.set("diameter", printingpressvo.getDiameter());
		dynavalidatorform.set("velocity", printingpressvo.getVelocity());
		dynavalidatorform.set("filterused", printingpressvo.getFilterused());
		dynavalidatorform.set("effiviancy", printingpressvo.getEffiviancy());
		dynavalidatorform.set("solvantcap", printingpressvo.getSolvantcap());
		dynavalidatorform.set("annualconsumption",
				printingpressvo.getAnnualconsumption());
		dynavalidatorform.set("Ink1", printingpressvo.getInk1());
		dynavalidatorform.set("Ink2", printingpressvo.getInk2());
		dynavalidatorform.set("Ink3", printingpressvo.getInk3());
		dynavalidatorform.set("Ink4", printingpressvo.getInk4());
		dynavalidatorform.set("FountainSolution",
				printingpressvo.getFountainSolution());
		dynavalidatorform.set("CleaningAgent",
				printingpressvo.getCleaningAgent());
		dynavalidatorform.set("Ink1name", printingpressvo.getInk1name());
		dynavalidatorform.set("Ink2name", printingpressvo.getInk2name());
		dynavalidatorform.set("Ink3name", printingpressvo.getInk3name());
		dynavalidatorform.set("Ink4name", printingpressvo.getInk4name());
		dynavalidatorform.set("FountainSolutionname",
				printingpressvo.getFountainSolutionname());
		dynavalidatorform.set("CleaningAgentname",
				printingpressvo.getCleaningAgentname());
		dynavalidatorform.set("Ink1volume", printingpressvo.getInk1volume());
		dynavalidatorform.set("Ink2volume", printingpressvo.getInk2volume());
		dynavalidatorform.set("Ink3volume", printingpressvo.getInk3volume());
		dynavalidatorform.set("Ink4volume", printingpressvo.getInk4volume());
		dynavalidatorform.set("FountainSolutionvolume",
				printingpressvo.getFountainSolutionvolume());
		dynavalidatorform.set("CleaningAgentvolume",
				printingpressvo.getCleaningAgentvolume());
		dynavalidatorform.set("Ink1density", printingpressvo.getInk1density());
		dynavalidatorform.set("Ink2density", printingpressvo.getInk2density());
		dynavalidatorform.set("Ink3density", printingpressvo.getInk3density());
		dynavalidatorform.set("Ink4density", printingpressvo.getInk4density());
		dynavalidatorform.set("FountainSolutiondensity",
				printingpressvo.getFountainSolutiondensity());
		dynavalidatorform.set("CleaningAgentdensity",
				printingpressvo.getCleaningAgentdensity());
		dynavalidatorform.set("Ink1vocpercent",
				printingpressvo.getInk1vocpercent());
		dynavalidatorform.set("Ink2vocpercent",
				printingpressvo.getInk2vocpercent());
		dynavalidatorform.set("Ink3vocpercent",
				printingpressvo.getInk3vocpercent());
		dynavalidatorform.set("Ink4vocpercent",
				printingpressvo.getInk4vocpercent());
		dynavalidatorform.set("FountainSolutionvocpercent",
				printingpressvo.getFountainSolutionvocpercent());
		dynavalidatorform.set("CleaningAgentvocpercent",
				printingpressvo.getCleaningAgentvocpercent());
		dynavalidatorform.set("Ink1voc", printingpressvo.getInk1voc());
		dynavalidatorform.set("Ink2voc", printingpressvo.getInk2voc());
		dynavalidatorform.set("Ink3voc", printingpressvo.getInk3voc());
		dynavalidatorform.set("Ink4voc", printingpressvo.getInk4voc());
		dynavalidatorform.set("FountainSolutionvoc",
				printingpressvo.getFountainSolutionvoc());
		dynavalidatorform.set("CleaningAgentvoc",
				printingpressvo.getCleaningAgentvoc());
		dynavalidatorform.set("dobpermit", printingpressvo.getDobpermit());
		dynavalidatorform.set("totalvoc", printingpressvo.getTotalvoc());
		dynavalidatorform.set("dobsignoff", printingpressvo.getDobsignoff());

		printingpressvo.setInkConsumptionList(null);
		printingpressvo.setto_InkConsumptionList(null);
		printingpressvo.setth_InkConsumptionList(null);
		printingpressvo.setfr_InkConsumptionList(null);
		printingpressvo.setfi_InkConsumptionList(null);
		printingpressvo.setsi_InkConsumptionList(null);
		printingpressvo.setse_InkConsumptionList(null);
		List list1 = printingpressvo.getInkConsumptionList();
		List to_list1 = printingpressvo.getto_InkConsumptionList();
		List th_list1 = printingpressvo.getth_InkConsumptionList();
		List fr_list1 = printingpressvo.getfr_InkConsumptionList();
		List fi_list1 = printingpressvo.getfi_InkConsumptionList();
		List si_list1 = printingpressvo.getsi_InkConsumptionList();
		List se_list1 = printingpressvo.getse_InkConsumptionList();
		System.out.println("In Pressaction A:" + list1.size());
		System.out.println("In Pressaction B:" + to_list1.size());
		System.out.println("In Pressaction C:" + th_list1.size());
		System.out.println("In Pressaction D:" + fr_list1.size());
		System.out.println("In Pressaction E:" + fi_list1.size());
		System.out.println("In Pressaction F:" + si_list1.size());
		System.out.println("In Pressaction G:" + se_list1.size());
		if (list1 != null && list1.size() > 0) {
			Properties properties = UtilityObject.getRollingAverageInfo(list1);
			httpservletrequest.setAttribute("on_hdnConsumption",
					properties.get("TOTAL"));
			httpservletrequest.setAttribute("on_hdnPreviousConsumption",
					properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("on_hdnCurrentMonthIndex",
					properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("PRESS_INK", list1);

		} else {
			httpservletrequest.setAttribute("PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("on_hdnConsumption", "");
			httpservletrequest.setAttribute("on_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("on_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		if (to_list1 != null && to_list1.size() > 0) {
			Properties to_properties = UtilityObject
					.getRollingAverageInfo(to_list1);
			httpservletrequest.setAttribute("to_hdnConsumption",
					to_properties.get("TOTAL"));
			httpservletrequest.setAttribute("to_hdnPreviousConsumption",
					to_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("to_hdnCurrentMonthIndex",
					to_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("TO_PRESS_INK", to_list1);
		} else {
			httpservletrequest.setAttribute("TO_PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("to_hdnConsumption", "");
			httpservletrequest.setAttribute("to_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("to_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		if (th_list1 != null && th_list1.size() > 0) {
			Properties th_properties = UtilityObject
					.getRollingAverageInfo(th_list1);
			httpservletrequest.setAttribute("th_hdnConsumption",
					th_properties.get("TOTAL"));
			httpservletrequest.setAttribute("th_hdnPreviousConsumption",
					th_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("th_hdnCurrentMonthIndex",
					th_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("TH_PRESS_INK", th_list1);
		} else {
			httpservletrequest.setAttribute("TH_PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("th_hdnConsumption", "");
			httpservletrequest.setAttribute("th_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("th_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		if (fr_list1 != null && fr_list1.size() > 0) {
			Properties fr_properties = UtilityObject
					.getRollingAverageInfo(fr_list1);
			httpservletrequest.setAttribute("fr_hdnConsumption",
					fr_properties.get("TOTAL"));
			httpservletrequest.setAttribute("fr_hdnPreviousConsumption",
					fr_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("fr_hdnCurrentMonthIndex",
					fr_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FR_PRESS_INK", fr_list1);
		} else {
			httpservletrequest.setAttribute("FR_PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("fr_hdnConsumption", "");
			httpservletrequest.setAttribute("fr_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("fr_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}

		if (fi_list1 != null && fi_list1.size() > 0) {
			Properties fi_properties = UtilityObject
					.getRollingAverageInfo(fi_list1);
			httpservletrequest.setAttribute("fi_hdnConsumption",
					fi_properties.get("TOTAL"));
			httpservletrequest.setAttribute("fi_hdnPreviousConsumption",
					fi_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("fi_hdnCurrentMonthIndex",
					fi_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("FI_PRESS_INK", fi_list1);
		} else {
			httpservletrequest.setAttribute("FI_PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("fi_hdnConsumption", "");
			httpservletrequest.setAttribute("fi_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("fi_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}
		if (si_list1 != null && si_list1.size() > 0) {
			Properties si_properties = UtilityObject
					.getRollingAverageInfo(si_list1);
			httpservletrequest.setAttribute("si_hdnConsumption",
					si_properties.get("TOTAL"));
			httpservletrequest.setAttribute("si_hdnPreviousConsumption",
					si_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("si_hdnCurrentMonthIndex",
					si_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("SI_PRESS_INK", si_list1);
		} else {
			httpservletrequest.setAttribute("SI_PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("si_hdnConsumption", "");
			httpservletrequest.setAttribute("si_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("si_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}
		if (se_list1 != null && se_list1.size() > 0) {
			Properties fi_properties = UtilityObject
					.getRollingAverageInfo(se_list1);
			httpservletrequest.setAttribute("se_hdnConsumption",
					fi_properties.get("TOTAL"));
			httpservletrequest.setAttribute("se_hdnPreviousConsumption",
					fi_properties.get("PREVIOUS_CONSUMPTION"));
			httpservletrequest.setAttribute("se_hdnCurrentMonthIndex",
					fi_properties.get("CURRENT_MONTH_INDEX"));
			httpservletrequest.setAttribute("SE_PRESS_INK", se_list1);
		} else {
			httpservletrequest.setAttribute("SE_PRESS_INK", new ArrayList());
			httpservletrequest.setAttribute("se_hdnConsumption", "");
			httpservletrequest.setAttribute("se_hdnPreviousConsumption",
					"0|0|0|0|0|0|0|0|0|0|0|0");
			httpservletrequest.setAttribute("se_hdnCurrentMonthIndex", "");
			log.debug("No Consumption info");
		}
		try {
			reset(httpservletrequest);
		} catch (Exception exception) {
			System.out.println(exception);
		}
	}

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
		PrintingPressVo printingpressvo = PrintingPressEntity
				.findByPrimaryKey(i);
		if (printingpressvo != null) {
			httpsession.setAttribute("PRESS_OBJECT", printingpressvo);
			setFormVariable(dynavalidatorform, printingpressvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get PrintingpressVo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("Press Edit");
		log.debug("Press - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PrintingPressVo printingpressvo = PrintingPressEntity
				.findByPrimaryKey(i);
		if (printingpressvo != null) {
			httpsession.setAttribute("PRESS_OBJECT", printingpressvo);
			setFormVariable(dynavalidatorform, printingpressvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get printingpressvo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("printingpressvo - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		PrintingPressVo printingpressvo = PrintingPressEntity
				.findByPrimaryKey(i);
		if (printingpressvo != null) {
			httpsession.setAttribute("PRESS_OBJECT", printingpressvo);
			setFormVariable(dynavalidatorform, printingpressvo,
					httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get printingpressvo for id(").append(s)
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
		dynavalidatorform.set("pressId", "");
		dynavalidatorform.set("ModifiedInPast", "");
		dynavalidatorform.set("DisconnectedYear", "");
		dynavalidatorform.set("Pcomments", "");
		dynavalidatorform.set("decpermit", "");
		dynavalidatorform.set("stacktestrequired", "");
		dynavalidatorform.set("isvoc", "");
		dynavalidatorform.set("ispm", "");
		dynavalidatorform.set("isother", "");
		dynavalidatorform.set("depnumber", "");
		dynavalidatorform.set("depissuedate", "");
		dynavalidatorform.set("depexpirationdate", "");
		dynavalidatorform.set("stackexhastlocation", "");
		dynavalidatorform.set("height", "");
		dynavalidatorform.set("diameter", "");
		dynavalidatorform.set("velocity", "");
		dynavalidatorform.set("filterused", "");
		dynavalidatorform.set("effiviancy", "");
		dynavalidatorform.set("solvantcap", "");
		dynavalidatorform.set("annualconsumption", "");
		dynavalidatorform.set("Ink1", "");
		dynavalidatorform.set("Ink2", "");
		dynavalidatorform.set("Ink3", "");
		dynavalidatorform.set("Ink4", "");
		dynavalidatorform.set("FountainSolution", "");
		dynavalidatorform.set("CleaningAgent", "");
		dynavalidatorform.set("Ink1name", "");
		dynavalidatorform.set("Ink2name", "");
		dynavalidatorform.set("Ink3name", "");
		dynavalidatorform.set("Ink4name", "");
		dynavalidatorform.set("FountainSolutionname", "");
		dynavalidatorform.set("CleaningAgentname", "");
		dynavalidatorform.set("Ink1volume", "");
		dynavalidatorform.set("Ink2volume", "");
		dynavalidatorform.set("Ink3volume", "");
		dynavalidatorform.set("Ink4volume", "");
		dynavalidatorform.set("FountainSolutionvolume", "");
		dynavalidatorform.set("CleaningAgentvolume", "");
		dynavalidatorform.set("Ink1density", "");
		dynavalidatorform.set("Ink2density", "");
		dynavalidatorform.set("Ink3density", "");
		dynavalidatorform.set("Ink4density", "");
		dynavalidatorform.set("FountainSolutiondensity", "");
		dynavalidatorform.set("CleaningAgentdensity", "");
		dynavalidatorform.set("Ink1vocpercent", "");
		dynavalidatorform.set("Ink2vocpercent", "");
		dynavalidatorform.set("Ink3vocpercent", "");
		dynavalidatorform.set("Ink4vocpercent", "");
		dynavalidatorform.set("FountainSolutionvocpercent", "");
		dynavalidatorform.set("CleaningAgentvocpercent", "");
		dynavalidatorform.set("Ink1voc", "");
		dynavalidatorform.set("Ink2voc", "");
		dynavalidatorform.set("Ink3voc", "");
		dynavalidatorform.set("Ink4voc", "");
		dynavalidatorform.set("FountainSolutionvoc", "");
		dynavalidatorform.set("CleaningAgentvoc", "");
		dynavalidatorform.set("dobpermit", "");
		dynavalidatorform.set("totalvoc", "");
		dynavalidatorform.set("dobsignoff", "");
		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	public void reset(HttpServletRequest httpservletrequest)
			throws IOException, ServletException {
		// DynaActionForm dynaactionform = (DynaActionForm)actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		PrintingPressVo printingpressvo = (PrintingPressVo) httpsession
				.getAttribute("PRESS_OBJECT");
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		System.out.println("Facility Id: " + facilityvo.getDecId());
		System.out.println("Path=" + httpservletrequest.getContextPath() + ":"
				+ httpservletrequest.getRealPath("/"));
		// String s = (String)dynaactionform.get("methodToCall");
		try {
			File f = new File(httpservletrequest.getRealPath("/")
					+ "/clientfolder/" + facilityvo.getDecId()
					+ "/printingpress/" + printingpressvo.getId() + "-"
					+ printingpressvo.getFacilitydesinatedId().trim());
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
					+ "/printingpress/" + printingpressvo.getId() + "-"
					+ printingpressvo.getFacilitydesinatedId().trim();
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
				+ "/printingpress/" + printingpressvo.getId() + "-"
				+ printingpressvo.getFacilitydesinatedId().trim());

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
		httpservletrequest.setAttribute("PRESS_STATUS", dropdown);
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private float getValue(String s, DynaActionForm dynaactionform)
			throws NumberFormatException {
		String s1 = "0";
		s1 = (String) dynaactionform.get(s);
		if (s1.equals(""))
			s1 = "0";
		if (UtilityObject.isNotEmpty(s1))
			return Float.parseFloat(s1);
		else
			return 0f;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.PrintingPressAction.class);
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