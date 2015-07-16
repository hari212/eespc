package com.eespc.tracking.actions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.apache.struts.validator.DynaValidatorForm;

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.HwasteVo;
import com.eespc.tracking.bo.myenum.HazardousWasteCategoryEnum;
import com.eespc.tracking.bo.myenum.HazardousWasteTypeEnum;
import com.eespc.tracking.bo.myenum.HazardousWasteTypeEnum1;
import com.eespc.tracking.bo.myenum.HazardousWasteTypeEnum2;
import com.eespc.tracking.bo.myenum.HazardousWasteTypeEnum3;
import com.eespc.tracking.bo.myenum.HazardousWasteTypeEnum4;
import com.eespc.tracking.entity.HwasteEntity;
import com.eespc.tracking.util.UtilityObject;

public class HwasteAction extends Action {

	public HwasteAction() {
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
			else if (userAction != null && userAction.equalsIgnoreCase("edit"))
				return edit(mapping, form, request, response);
		} catch (Exception e) {
			System.out.println("Hwaste Execute Exception" + e);
		}

		dynavalidatorform.set("manifastno", "");
		dynavalidatorform.set("dateOftheManifest", "");
		dynavalidatorform.set("waste", "");
		dynavalidatorform.set("typeOfWaste", "");
		dynavalidatorform.set("wasteName", "");
		dynavalidatorform.set("wasteVolume", "");
		dynavalidatorform.set("wasteDensity", "");
		dynavalidatorform.set("wasteUnit", "");
		dynavalidatorform.set("wasteQuantity", "");
		dynavalidatorform.set("waste1", "");
		dynavalidatorform.set("typeOfWaste1", "");
		dynavalidatorform.set("wasteName1", "");
		dynavalidatorform.set("wasteVolume1", "");
		dynavalidatorform.set("wasteDensity1", "");
		dynavalidatorform.set("wasteUnit1", "");
		dynavalidatorform.set("wasteQuantity1", "");
		dynavalidatorform.set("waste2", "");
		dynavalidatorform.set("typeOfWaste2", "");
		dynavalidatorform.set("wasteName2", "");
		dynavalidatorform.set("wasteVolume2", "");
		dynavalidatorform.set("wasteDensity2", "");
		dynavalidatorform.set("wasteUnit2", "");
		dynavalidatorform.set("wasteQuantity2", "");
		dynavalidatorform.set("waste3", "");
		dynavalidatorform.set("typeOfWaste3", "");
		dynavalidatorform.set("wasteName3", "");
		dynavalidatorform.set("wasteVolume3", "");
		dynavalidatorform.set("wasteDensity3", "");
		dynavalidatorform.set("wasteUnit3", "");
		dynavalidatorform.set("wasteQuantity3", "");
		dynavalidatorform.set("waste4", "");
		dynavalidatorform.set("typeOfWaste4", "");
		dynavalidatorform.set("wasteName4", "");
		dynavalidatorform.set("wasteVolume4", "");
		dynavalidatorform.set("wasteDensity4", "");
		dynavalidatorform.set("wasteUnit4", "");
		dynavalidatorform.set("wasteQuantity4", "");
		dynavalidatorform.set("hazardousTotalWaste", "");
		dynavalidatorform.set("divisioncontactname", "");
		dynavalidatorform.set("telephonenumber", "");
		dynavalidatorform.set("companyName", "");
		dynavalidatorform.set("epaidWaste", "");
		dynavalidatorform.set("epaId", "");
		dynavalidatorform.set("hazardousWasteCategory", "");
		dynavalidatorform.set("epaidFinaldes", "");

		request.setAttribute("SHOW_BUTTONS", "inline");
		request.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		return mapping.findForward("success");
	}

	public ActionForward displaycontrol(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

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
			httpservletrequest.setAttribute("isComponentEditable", "Y");

		}

		return actionmapping.findForward("success");
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");

		HwasteVo hwastevo = new HwasteVo();

		hwastevo.setManifastno((String) dynaactionform.get("manifastno"));
		hwastevo.setDateOftheManifest(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("dateOftheManifest")), "yyyy-MM-dd"));
		hwastevo.setWaste((String) dynaactionform.get("waste"));
		hwastevo.setWaste1((String) dynaactionform.get("waste1"));
		hwastevo.setWaste2((String) dynaactionform.get("waste2"));
		hwastevo.setWaste3((String) dynaactionform.get("waste3"));
		hwastevo.setWaste4((String) dynaactionform.get("waste4"));
		hwastevo.setTypeOfWaste(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste")));
		hwastevo.setTypeOfWaste1(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste1")));
		hwastevo.setTypeOfWaste2(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste2")));
		hwastevo.setTypeOfWaste3(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste3")));
		hwastevo.setTypeOfWaste4(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste4")));
		hwastevo.setWasteName((String) dynaactionform.get("wasteName"));
		hwastevo.setWasteVolume((String) dynaactionform.get("wasteVolume"));
		hwastevo.setWasteVolume1((String) dynaactionform.get("wasteVolume1"));
		hwastevo.setWasteVolume2((String) dynaactionform.get("wasteVolume2"));
		hwastevo.setWasteVolume3((String) dynaactionform.get("wasteVolume3"));
		hwastevo.setWasteVolume4((String) dynaactionform.get("wasteVolume4"));
		hwastevo.setWasteDensity((String) dynaactionform.get("wasteDensity"));
		hwastevo.setWasteDensity1((String) dynaactionform.get("wasteDensity1"));
		hwastevo.setWasteDensity2((String) dynaactionform.get("wasteDensity2"));
		hwastevo.setWasteDensity3((String) dynaactionform.get("wasteDensity3"));
		hwastevo.setWasteDensity4((String) dynaactionform.get("wasteDensity4"));
		hwastevo.setWasteUnit((String) dynaactionform.get("wasteUnit"));
		hwastevo.setWasteUnit1((String) dynaactionform.get("wasteUnit1"));
		hwastevo.setWasteUnit2((String) dynaactionform.get("wasteUnit2"));
		hwastevo.setWasteUnit3((String) dynaactionform.get("wasteUnit3"));
		hwastevo.setWasteUnit4((String) dynaactionform.get("wasteUnit4"));
		hwastevo.setWasteQuantity((String) dynaactionform.get("wasteQuantity"));
		hwastevo.setWasteName1((String) dynaactionform.get("wasteName1"));
		hwastevo.setWasteQuantity1((String) dynaactionform
				.get("wasteQuantity1"));
		hwastevo.setWasteName2((String) dynaactionform.get("wasteName2"));
		hwastevo.setWasteQuantity2((String) dynaactionform
				.get("wasteQuantity2"));
		hwastevo.setWasteName3((String) dynaactionform.get("wasteName3"));
		hwastevo.setWasteQuantity3((String) dynaactionform
				.get("wasteQuantity3"));
		hwastevo.setWasteName4((String) dynaactionform.get("wasteName4"));
		hwastevo.setWasteQuantity4((String) dynaactionform
				.get("wasteQuantity4"));
		hwastevo.setHazardousTotalWaste((String) dynaactionform
				.get("hazardousTotalWaste"));
		hwastevo.setDivisioncontactname((String) dynaactionform
				.get("divisioncontactname"));
		hwastevo.setTelephonenumber((String) dynaactionform
				.get("telephonenumber"));
		hwastevo.setCompanyName((String) dynaactionform.get("companyName"));
		hwastevo.setEpaidWaste((String) dynaactionform.get("epaidWaste"));
		hwastevo.setEpaId((String) dynaactionform.get("epaId"));
		hwastevo.setHazardousWasteCategory(UtilityObject
				.getIntFromString((String) dynaactionform
						.get("hazardousWasteCategory")));
		hwastevo.setEpaidFinaldes((String) dynaactionform.get("epaidFinaldes"));

		byte byte0 = -99;
		try {

			int i = HwasteEntity.add(facilityvo.getId(), hwastevo);

			hwastevo = HwasteEntity.findByPrimaryKey(i);

			if (hwastevo != null) {

				httpsession.setAttribute("HWASTE_OBJECT", hwastevo);

				setFormVariable(dynaactionform, hwastevo, httpservletrequest);

			}

			s = "Added Hwaste Successfully.";
			s1 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (Exception exception) {
			System.out.println("Exception " + exception);
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

		HwasteVo hwastevo = (HwasteVo) httpsession
				.getAttribute("HWASTE_OBJECT");
		hwastevo.setId(hwastevo.getId());
		hwastevo.setManifastno((String) dynaactionform.get("manifastno"));
		hwastevo.setDateOftheManifest(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("dateOftheManifest")), "yyyy-MM-dd"));
		hwastevo.setWaste((String) dynaactionform.get("waste"));
		hwastevo.setTypeOfWaste(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste")));
		hwastevo.setTypeOfWaste1(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste1")));
		hwastevo.setTypeOfWaste2(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste2")));
		hwastevo.setTypeOfWaste3(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste3")));
		hwastevo.setTypeOfWaste4(UtilityObject
				.getIntFromString((String) dynaactionform.get("typeOfWaste4")));
		hwastevo.setWasteName((String) dynaactionform.get("wasteName"));
		hwastevo.setWasteVolume((String) dynaactionform.get("wasteVolume"));
		hwastevo.setWasteDensity((String) dynaactionform.get("wasteDensity"));
		hwastevo.setWasteUnit((String) dynaactionform.get("wasteUnit"));
		hwastevo.setWasteQuantity((String) dynaactionform.get("wasteQuantity"));
		hwastevo.setWaste1((String) dynaactionform.get("waste1"));
		hwastevo.setWasteName1((String) dynaactionform.get("wasteName1"));
		hwastevo.setWasteVolume1((String) dynaactionform.get("wasteVolume1"));
		hwastevo.setWasteDensity1((String) dynaactionform.get("wasteDensity1"));
		hwastevo.setWasteUnit1((String) dynaactionform.get("wasteUnit1"));
		hwastevo.setWasteQuantity1((String) dynaactionform
				.get("wasteQuantity1"));
		hwastevo.setWaste2((String) dynaactionform.get("waste2"));
		hwastevo.setWasteName2((String) dynaactionform.get("wasteName2"));
		hwastevo.setWasteVolume2((String) dynaactionform.get("wasteVolume2"));
		hwastevo.setWasteDensity2((String) dynaactionform.get("wasteDensity2"));
		hwastevo.setWasteUnit2((String) dynaactionform.get("wasteUnit2"));
		hwastevo.setWasteQuantity2((String) dynaactionform
				.get("wasteQuantity2"));
		hwastevo.setWaste3((String) dynaactionform.get("waste3"));
		hwastevo.setWasteName3((String) dynaactionform.get("wasteName3"));
		hwastevo.setWasteVolume3((String) dynaactionform.get("wasteVolume3"));
		hwastevo.setWasteDensity3((String) dynaactionform.get("wasteDensity3"));
		hwastevo.setWasteUnit3((String) dynaactionform.get("wasteUnit3"));
		hwastevo.setWasteQuantity3((String) dynaactionform
				.get("wasteQuantity3"));
		hwastevo.setWaste4((String) dynaactionform.get("waste4"));
		hwastevo.setWasteName4((String) dynaactionform.get("wasteName4"));
		hwastevo.setWasteVolume4((String) dynaactionform.get("wasteVolume4"));
		hwastevo.setWasteDensity4((String) dynaactionform.get("wasteDensity4"));
		hwastevo.setWasteUnit4((String) dynaactionform.get("wasteUnit4"));
		hwastevo.setWasteQuantity4((String) dynaactionform
				.get("wasteQuantity4"));
		hwastevo.setHazardousTotalWaste((String) dynaactionform
				.get("hazardousTotalWaste"));
		hwastevo.setDivisioncontactname((String) dynaactionform
				.get("divisioncontactname"));
		hwastevo.setTelephonenumber((String) dynaactionform
				.get("telephonenumber"));
		hwastevo.setCompanyName((String) dynaactionform.get("companyName"));
		hwastevo.setEpaidWaste((String) dynaactionform.get("epaidWaste"));
		hwastevo.setEpaId((String) dynaactionform.get("epaId"));
		hwastevo.setHazardousWasteCategory(UtilityObject
				.getIntFromString((String) dynaactionform
						.get("hazardousWasteCategory")));
		hwastevo.setEpaidFinaldes((String) dynaactionform.get("epaidFinaldes"));

		byte byte0 = -99;
		try {
			HwasteEntity.update(hwastevo);
			s = "Updated Hwaste Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, hwastevo, httpservletrequest);
			httpservletrequest.setAttribute("isComponentEditable", "N");
		} catch (Exception exception) {
			System.out.println("Exception " + exception);
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

			DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
			HttpSession httpsession = httpservletrequest.getSession();
			String s = (String) dynaactionform.get("id");
			int i = -99;
			if (s != null)
				i = Integer.parseInt(s);
			HwasteVo hwastevo = HwasteEntity.findByPrimaryKey(i);
			if (hwastevo != null)
				httpsession.setAttribute("HWASTE_OBJECT", hwastevo);
			hwastevo = (HwasteVo) httpsession.getAttribute("HWASTE_OBJECT");
			HwasteEntity.delete(hwastevo);
		} catch (Exception exception) {
			System.out.println("Exception " + exception);
		}
		return actionmapping.findForward("successfc");
	}

	private void setFieldDetailsforedit(HttpServletRequest httpservletrequest,
			DynaValidatorForm dynavalidatorform, HwasteVo hwastevo, boolean flag) {
		HttpSession httpsession = httpservletrequest.getSession();
		if (hwastevo == null) {
			log.debug("Object is null so returning..");
			return;
		}
		try {
			dynavalidatorform.set("manifastno", hwastevo.getManifastno());
			dynavalidatorform.set("dateOftheManifest", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(hwastevo.getDateOftheManifest()));
			dynavalidatorform.set("waste", hwastevo.getWaste());
			dynavalidatorform.set("waste1", hwastevo.getWaste1());
			dynavalidatorform.set("waste2", hwastevo.getWaste2());
			dynavalidatorform.set("waste3", hwastevo.getWaste3());
			dynavalidatorform.set("waste4", hwastevo.getWaste4());
			dynavalidatorform.set("wasteName", hwastevo.getWasteName());
			dynavalidatorform.set("wasteQuantity", hwastevo.getWasteQuantity());
			dynavalidatorform.set("wasteVolume", hwastevo.getWasteVolume());
			dynavalidatorform.set("wasteVolume1", hwastevo.getWasteVolume1());
			dynavalidatorform.set("wasteVolume2", hwastevo.getWasteVolume2());
			dynavalidatorform.set("wasteVolume3", hwastevo.getWasteVolume3());
			dynavalidatorform.set("wasteVolume4", hwastevo.getWasteVolume4());
			dynavalidatorform.set("wasteDensity", hwastevo.getWasteDensity());
			dynavalidatorform.set("wasteDensity1", hwastevo.getWasteDensity1());
			dynavalidatorform.set("wasteDensity2", hwastevo.getWasteDensity2());
			dynavalidatorform.set("wasteDensity3", hwastevo.getWasteDensity3());
			dynavalidatorform.set("wasteDensity4", hwastevo.getWasteDensity4());
			dynavalidatorform.set("wasteUnit", hwastevo.getWasteUnit());
			dynavalidatorform.set("wasteUnit1", hwastevo.getWasteUnit1());
			dynavalidatorform.set("wasteUnit2", hwastevo.getWasteUnit2());
			dynavalidatorform.set("wasteUnit3", hwastevo.getWasteUnit3());
			dynavalidatorform.set("wasteUnit4", hwastevo.getWasteUnit4());
			dynavalidatorform.set("wasteName1", hwastevo.getWasteName1());
			dynavalidatorform.set("wasteQuantity1",
					hwastevo.getWasteQuantity1());
			dynavalidatorform.set("wasteName2", hwastevo.getWasteName2());
			dynavalidatorform.set("wasteQuantity2",
					hwastevo.getWasteQuantity2());
			dynavalidatorform.set("wasteName3", hwastevo.getWasteName3());
			dynavalidatorform.set("wasteQuantity3",
					hwastevo.getWasteQuantity3());
			dynavalidatorform.set("wasteName4", hwastevo.getWasteName4());
			dynavalidatorform.set("wasteQuantity4",
					hwastevo.getWasteQuantity4());
			dynavalidatorform.set("hazardousTotalWaste",
					hwastevo.getHazardousTotalWaste());
			dynavalidatorform.set("divisioncontactname",
					hwastevo.getDivisioncontactname());
			dynavalidatorform.set("telephonenumber",
					hwastevo.getTelephonenumber());
			dynavalidatorform.set("companyName", hwastevo.getCompanyName());
			dynavalidatorform.set("epaidWaste", hwastevo.getEpaidWaste());
			dynavalidatorform.set("epaId", hwastevo.getEpaId());
			dynavalidatorform.set("epaidFinaldes", hwastevo.getEpaidFinaldes());

			HazardousWasteCategoryEnum hazardouswastecategoryenum = HazardousWasteCategoryEnum
					.get(hwastevo.getHazardousWasteCategory());
			if (flag)
				dynavalidatorform
						.set("hazardousWasteCategory",
								hazardouswastecategoryenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastecategoryenum
												.getCode()))).toString()))
										: "");
			else
				dynavalidatorform
						.set("hazardousWasteCategory",
								hazardouswastecategoryenum != null ? ((Object) (hazardouswastecategoryenum
										.getName())) : "");

			HazardousWasteTypeEnum hazardouswastetypeenum = HazardousWasteTypeEnum
					.get(hwastevo.getTypeOfWaste());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste",
								hazardouswastetypeenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste",
								hazardouswastetypeenum != null ? ((Object) (hazardouswastetypeenum
										.getName())) : "");

			HazardousWasteTypeEnum1 hazardouswastetypeenum1 = HazardousWasteTypeEnum1
					.get(hwastevo.getTypeOfWaste1());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste1",
								hazardouswastetypeenum1 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum1
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste1",
								hazardouswastetypeenum1 != null ? ((Object) (hazardouswastetypeenum1
										.getName())) : "");

			HazardousWasteTypeEnum2 hazardouswastetypeenum2 = HazardousWasteTypeEnum2
					.get(hwastevo.getTypeOfWaste2());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste2",
								hazardouswastetypeenum2 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum2
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste2",
								hazardouswastetypeenum2 != null ? ((Object) (hazardouswastetypeenum2
										.getName())) : "");

			HazardousWasteTypeEnum3 hazardouswastetypeenum3 = HazardousWasteTypeEnum3
					.get(hwastevo.getTypeOfWaste3());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste3",
								hazardouswastetypeenum3 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum3
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste3",
								hazardouswastetypeenum3 != null ? ((Object) (hazardouswastetypeenum3
										.getName())) : "");

			HazardousWasteTypeEnum4 hazardouswastetypeenum4 = HazardousWasteTypeEnum4
					.get(hwastevo.getTypeOfWaste4());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste4",
								hazardouswastetypeenum4 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum4
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste4",
								hazardouswastetypeenum4 != null ? ((Object) (hazardouswastetypeenum4
										.getName())) : "");

		}

		catch (Exception exception) {
			System.out.println("Exception :" + exception);
		}

	}

	private void setFormVariable(DynaValidatorForm dynavalidatorform,
			HwasteVo hwastevo, HttpServletRequest httpservletrequest) {
		setFormVariable(dynavalidatorform, hwastevo, httpservletrequest, false);
	}

	private void setFormVariable(DynaValidatorForm dynavalidatorform,
			HwasteVo hwastevo, HttpServletRequest httpservletrequest,
			boolean flag) {
		HttpSession httpsession = httpservletrequest.getSession();
		if (hwastevo == null) {
			log.debug("Object is null so returning..");
			return;
		}
		try {
			dynavalidatorform.set("manifastno", hwastevo.getManifastno());
			dynavalidatorform.set("dateOftheManifest", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(hwastevo.getDateOftheManifest()));
			dynavalidatorform.set("waste", hwastevo.getWaste());
			dynavalidatorform.set("waste1", hwastevo.getWaste1());
			dynavalidatorform.set("waste2", hwastevo.getWaste2());
			dynavalidatorform.set("waste3", hwastevo.getWaste3());
			dynavalidatorform.set("waste4", hwastevo.getWaste4());
			dynavalidatorform.set("wasteName", hwastevo.getWasteName());
			dynavalidatorform.set("wasteQuantity", hwastevo.getWasteQuantity());
			dynavalidatorform.set("wasteVolume", hwastevo.getWasteVolume());
			dynavalidatorform.set("wasteVolume1", hwastevo.getWasteVolume1());
			dynavalidatorform.set("wasteVolume2", hwastevo.getWasteVolume2());
			dynavalidatorform.set("wasteVolume3", hwastevo.getWasteVolume3());
			dynavalidatorform.set("wasteVolume4", hwastevo.getWasteVolume4());
			dynavalidatorform.set("wasteDensity", hwastevo.getWasteDensity());
			dynavalidatorform.set("wasteDensity1", hwastevo.getWasteDensity1());
			dynavalidatorform.set("wasteDensity2", hwastevo.getWasteDensity2());
			dynavalidatorform.set("wasteDensity3", hwastevo.getWasteDensity3());
			dynavalidatorform.set("wasteDensity4", hwastevo.getWasteDensity4());
			dynavalidatorform.set("wasteUnit", hwastevo.getWasteUnit());
			dynavalidatorform.set("wasteUnit1", hwastevo.getWasteUnit1());
			dynavalidatorform.set("wasteUnit2", hwastevo.getWasteUnit2());
			dynavalidatorform.set("wasteUnit3", hwastevo.getWasteUnit3());
			dynavalidatorform.set("wasteUnit4", hwastevo.getWasteUnit4());
			dynavalidatorform.set("wasteName1", hwastevo.getWasteName1());
			dynavalidatorform.set("wasteQuantity1",
					hwastevo.getWasteQuantity1());
			dynavalidatorform.set("wasteName2", hwastevo.getWasteName2());
			dynavalidatorform.set("wasteQuantity2",
					hwastevo.getWasteQuantity2());
			dynavalidatorform.set("wasteName3", hwastevo.getWasteName3());
			dynavalidatorform.set("wasteQuantity3",
					hwastevo.getWasteQuantity3());
			dynavalidatorform.set("wasteName4", hwastevo.getWasteName4());
			dynavalidatorform.set("wasteQuantity4",
					hwastevo.getWasteQuantity4());
			dynavalidatorform.set("hazardousTotalWaste",
					hwastevo.getHazardousTotalWaste());
			dynavalidatorform.set("divisioncontactname",
					hwastevo.getDivisioncontactname());
			dynavalidatorform.set("telephonenumber",
					hwastevo.getTelephonenumber());
			dynavalidatorform.set("companyName", hwastevo.getCompanyName());
			dynavalidatorform.set("epaidWaste", hwastevo.getEpaidWaste());
			dynavalidatorform.set("epaId", hwastevo.getEpaId());
			dynavalidatorform.set("epaidFinaldes", hwastevo.getEpaidFinaldes());

			HazardousWasteCategoryEnum hazardouswastecategoryenum = HazardousWasteCategoryEnum
					.get(hwastevo.getHazardousWasteCategory());
			if (flag)
				dynavalidatorform
						.set("hazardousWasteCategory",
								hazardouswastecategoryenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastecategoryenum
												.getCode()))).toString()))
										: "");
			else
				dynavalidatorform
						.set("hazardousWasteCategory",
								hazardouswastecategoryenum != null ? ((Object) (hazardouswastecategoryenum
										.getName())) : "");

			HazardousWasteTypeEnum hazardouswastetypeenum = HazardousWasteTypeEnum
					.get(hwastevo.getTypeOfWaste());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste",
								hazardouswastetypeenum != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste",
								hazardouswastetypeenum != null ? ((Object) (hazardouswastetypeenum
										.getName())) : "");

			HazardousWasteTypeEnum1 hazardouswastetypeenum1 = HazardousWasteTypeEnum1
					.get(hwastevo.getTypeOfWaste1());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste1",
								hazardouswastetypeenum1 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum1
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste1",
								hazardouswastetypeenum1 != null ? ((Object) (hazardouswastetypeenum1
										.getName())) : "");

			HazardousWasteTypeEnum2 hazardouswastetypeenum2 = HazardousWasteTypeEnum2
					.get(hwastevo.getTypeOfWaste2());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste2",
								hazardouswastetypeenum2 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum2
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste2",
								hazardouswastetypeenum2 != null ? ((Object) (hazardouswastetypeenum2
										.getName())) : "");

			HazardousWasteTypeEnum3 hazardouswastetypeenum3 = HazardousWasteTypeEnum3
					.get(hwastevo.getTypeOfWaste3());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste3",
								hazardouswastetypeenum3 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum3
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste3",
								hazardouswastetypeenum3 != null ? ((Object) (hazardouswastetypeenum3
										.getName())) : "");

			HazardousWasteTypeEnum4 hazardouswastetypeenum4 = HazardousWasteTypeEnum4
					.get(hwastevo.getTypeOfWaste4());
			if (flag)
				dynavalidatorform
						.set("typeOfWaste4",
								hazardouswastetypeenum4 != null ? ((Object) ((new StringBuffer(
										String.valueOf(hazardouswastetypeenum4
												.getCode()))).toString())) : "");
			else
				dynavalidatorform
						.set("typeOfWaste4",
								hazardouswastetypeenum4 != null ? ((Object) (hazardouswastetypeenum4
										.getName())) : "");

		} catch (Exception exception) {
			System.out.println("Exception :" + exception);
		}

	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);

		HwasteVo hwastevo = HwasteEntity.findByPrimaryKey(i);
		if (hwastevo != null) {
			httpsession.setAttribute("HWASTE_OBJECT", hwastevo);
			setFormVariable(dynavalidatorform, hwastevo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get hwastevo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		log.debug("HwasteAction - In Edit");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null && s.trim().length() > 0)
			i = Integer.parseInt(s);
		HwasteVo hwastevo = HwasteEntity.findByPrimaryKey(i);
		if (hwastevo != null) {
			httpsession.setAttribute("HWASTE_OBJECT", hwastevo);
			setFieldDetailsforedit(httpservletrequest, dynavalidatorform,
					hwastevo, true);
		}
		setScreenInfo(httpservletrequest);
		return actionmapping.findForward("success");

	}

	private void setScreenInfo(HttpServletRequest httpservletrequest)

	{

		HttpSession httpsession = httpservletrequest.getSession();
		HwasteVo hwastevo = (HwasteVo) httpsession
				.getAttribute("HWASTE_OBJECT");
		if (hwastevo != null)
			httpservletrequest.setAttribute("showAddBtn", "Y");
		else
			httpservletrequest.setAttribute("showAddBtn", "N");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("manifastno", "");
		dynavalidatorform.set("dateOftheManifest", "");
		dynavalidatorform.set("waste", "");
		dynavalidatorform.set("typeOfWaste", "");
		dynavalidatorform.set("wasteName", "");
		dynavalidatorform.set("wasteVolume", "");
		dynavalidatorform.set("wasteDensity", "");
		dynavalidatorform.set("wasteUnit", "");
		dynavalidatorform.set("wasteQuantity", "");
		dynavalidatorform.set("waste1", "");
		dynavalidatorform.set("typeOfWaste1", "");
		dynavalidatorform.set("wasteName1", "");
		dynavalidatorform.set("wasteVolume1", "");
		dynavalidatorform.set("wasteDensity1", "");
		dynavalidatorform.set("wasteUnit1", "");
		dynavalidatorform.set("wasteQuantity1", "");
		dynavalidatorform.set("waste2", "");
		dynavalidatorform.set("typeOfWaste2", "");
		dynavalidatorform.set("wasteName2", "");
		dynavalidatorform.set("wasteVolume2", "");
		dynavalidatorform.set("wasteDensity2", "");
		dynavalidatorform.set("wasteUnit2", "");
		dynavalidatorform.set("wasteQuantity2", "");
		dynavalidatorform.set("waste3", "");
		dynavalidatorform.set("typeOfWaste3", "");
		dynavalidatorform.set("wasteName3", "");
		dynavalidatorform.set("wasteVolume3", "");
		dynavalidatorform.set("wasteDensity3", "");
		dynavalidatorform.set("wasteUnit3", "");
		dynavalidatorform.set("wasteQuantity3", "");
		dynavalidatorform.set("waste4", "");
		dynavalidatorform.set("typeOfWaste4", "");
		dynavalidatorform.set("wasteName4", "");
		dynavalidatorform.set("wasteVolume4", "");
		dynavalidatorform.set("wasteDensity4", "");
		dynavalidatorform.set("wasteUnit4", "");
		dynavalidatorform.set("wasteQuantity4", "");
		dynavalidatorform.set("hazardousTotalWaste", "");
		dynavalidatorform.set("divisioncontactname", "");
		dynavalidatorform.set("telephonenumber", "");
		dynavalidatorform.set("companyName", "");
		dynavalidatorform.set("epaidWaste", "");
		dynavalidatorform.set("epaId", "");
		dynavalidatorform.set("hazardousWasteCategory", "");
		dynavalidatorform.set("epaidFinaldes", "");

		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		httpservletrequest.setAttribute("HAZARDOUS_WASTE_CATEGORY",
				HazardousWasteCategoryEnum.getDropDownObj());
		httpservletrequest.setAttribute("WASTE_TYPE",
				HazardousWasteTypeEnum.getDropDownObj());
		httpservletrequest.setAttribute("WASTE_TYPE1",
				HazardousWasteTypeEnum1.getDropDownObj());
		httpservletrequest.setAttribute("WASTE_TYPE2",
				HazardousWasteTypeEnum2.getDropDownObj());
		httpservletrequest.setAttribute("WASTE_TYPE3",
				HazardousWasteTypeEnum3.getDropDownObj());
		httpservletrequest.setAttribute("WASTE_TYPE4",
				HazardousWasteTypeEnum4.getDropDownObj());

	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.HwasteAction.class);
	public String userAction;
}