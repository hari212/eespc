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
import com.eespc.tracking.bo.LsfVo;
import com.eespc.tracking.bo.myenum.CofF;
import com.eespc.tracking.entity.LsfEntity;
import com.eespc.tracking.util.UtilityObject;

public class LsfAction extends Action {

	public LsfAction() {
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
			System.out.println("LFS Execute Exception" + e);
		}

		dynavalidatorform.set("firstname", "");
		dynavalidatorform.set("lastname", "");
		dynavalidatorform.set("certificatenumber", "");
		dynavalidatorform.set("phonenumber", "");
		dynavalidatorform.set("department", "");
		dynavalidatorform.set("issuedate", "");
		dynavalidatorform.set("expirationdate", "");
		dynavalidatorform.set("operatingequipments", "");
		dynavalidatorform.set("equipmentoperated", null);
		/*
		 * dynavalidatorform.set("htypeofcoffrequired",null);
		 * dynavalidatorform.set("coffobtained","");
		 */
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
		System.out.println("Lsf Save");

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");

		LsfVo lsfvo = new LsfVo();
		// lsfvo.setFcilityId(facilityvo.getId());
		lsfvo.setFirstname((String) dynaactionform.get("firstname"));
		lsfvo.setLastname((String) dynaactionform.get("lastname"));
		lsfvo.setCertificatenumber((String) dynaactionform
				.get("certificatenumber"));
		lsfvo.setPhonenumber((String) dynaactionform.get("phonenumber"));
		lsfvo.setDepartment((String) dynaactionform.get("department"));
		lsfvo.setIssuedate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("issuedate")),
				"yyyy-MM-dd"));
		lsfvo.setExpirationdate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("expirationdate")),
				"yyyy-MM-dd"));
		lsfvo.setOperatingequipments((String[]) dynaactionform
				.get("operatingequipments"));
		lsfvo.setEquipmentoperated((String) dynaactionform
				.get("equipmentoperated"));

		// lsfvo.setHtypeofcoffrequired((String[])dynaactionform.get("htypeofcoffrequired"));
		// lsfvo.setCoffobtained((String)dynaactionform.get("coffobtained"));

		System.out.println("Lsf Save1");
		byte byte0 = -99;
		try {

			int i = LsfEntity.add(facilityvo.getId(), lsfvo);

			lsfvo = LsfEntity.findByPrimaryKey(i);

			if (lsfvo != null) {

				httpsession.setAttribute("LSF_OBJECT", lsfvo);

				setFormVariable(dynaactionform, lsfvo, httpservletrequest);

			}

			s = "Added LSF Successfully.";
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

		LsfVo lsfvo = (LsfVo) httpsession.getAttribute("LSF_OBJECT");
		lsfvo.setId(lsfvo.getId());
		lsfvo.setFirstname((String) dynaactionform.get("firstname"));
		lsfvo.setLastname((String) dynaactionform.get("lastname"));
		lsfvo.setCertificatenumber((String) dynaactionform
				.get("certificatenumber"));
		lsfvo.setPhonenumber((String) dynaactionform.get("phonenumber"));
		lsfvo.setDepartment((String) dynaactionform.get("department"));
		lsfvo.setIssuedate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("issuedate")),
				"yyyy-MM-dd"));
		lsfvo.setExpirationdate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("expirationdate")),
				"yyyy-MM-dd"));
		lsfvo.setOperatingequipments((String[]) dynaactionform
				.get("operatingequipments"));
		lsfvo.setEquipmentoperated((String) dynaactionform
				.get("equipmentoperated"));
		// lsfvo.setHtypeofcoffrequired((String[])dynaactionform.get("htypeofcoffrequired"));
		// lsfvo.setCoffobtained((String)dynaactionform.get("coffobtained"));

		byte byte0 = -99;
		try {
			LsfEntity.update(lsfvo);
			s = "Updated LSF Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, lsfvo, httpservletrequest);
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

	/*
	 * public ActionForward edit(ActionMapping actionmapping, ActionForm
	 * actionform, HttpServletRequest httpservletrequest, HttpServletResponse
	 * httpservletresponse) throws Exception { DynaActionForm dynaactionform =
	 * (DynaActionForm)actionform; log.debug("LsfAction - In Edit");
	 * httpservletrequest.setAttribute("isComponentEditable", "Y");
	 * httpservletrequest.setAttribute("isItForEdit", "Y"); HttpSession
	 * httpsession = httpservletrequest.getSession(); String s =
	 * (String)dynaactionform.get("id"); int i = -99; if(s != null &&
	 * s.trim().length() > 0) i = Integer.parseInt(s); LsfVo lsfvo =
	 * LsfEntity.findByPrimaryKey(i); if(lsfvo != null) {
	 * httpsession.setAttribute("LSF_OBJECT", lsfvo);
	 * setFormVariable(dynaactionform, lsfvo, httpservletrequest); } //
	 * setScreenInfo(httpservletrequest); return
	 * actionmapping.findForward("success"); }
	 */

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
			LsfVo lsfvo = LsfEntity.findByPrimaryKey(i);
			if (lsfvo != null)
				httpsession.setAttribute("LSF_OBJECT", lsfvo);
			lsfvo = (LsfVo) httpsession.getAttribute("LSF_OBJECT");
			LsfEntity.delete(lsfvo);
		} catch (Exception exception) {
			System.out.println("Exception " + exception);
		}
		return actionmapping.findForward("successfc");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			LsfVo lsfvo, HttpServletRequest httpservletrequest) {

		HttpSession httpsession = httpservletrequest.getSession();
		try {

			dynavalidatorform.set("firstname", lsfvo.getFirstname());
			dynavalidatorform.set("lastname", lsfvo.getLastname());
			dynavalidatorform.set("certificatenumber",
					lsfvo.getCertificatenumber());
			dynavalidatorform.set("phonenumber", lsfvo.getPhonenumber());
			dynavalidatorform.set("department", lsfvo.getDepartment());
			dynavalidatorform.set("issuedate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(lsfvo.getIssuedate()));
			dynavalidatorform.set("expirationdate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(lsfvo.getExpirationdate()));
			dynavalidatorform.set("operatingequipments",
					lsfvo.get_operatingequipments());
			System.out.println((lsfvo.get_Htypeofcoffrequired()).length + "/"
					+ (lsfvo.get_operatingequipments().length) + "-"
					+ ((String[]) lsfvo.get_operatingequipments())[0]);
			// dynavalidatorform.set("operatingequipments",null);
			dynavalidatorform.set("equipmentoperated",
					lsfvo.getEquipmentoperated());
			// dynavalidatorform.set("htypeofcoffrequired",(String[])lsfvo.get_Htypeofcoffrequired());
			// dynavalidatorform.set("htypeofcoffrequired",null);

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

		LsfVo lsfvo = LsfEntity.findByPrimaryKey(i);
		if (lsfvo != null) {
			httpsession.setAttribute("LSF_OBJECT", lsfvo);
			setFormVariable(dynavalidatorform, lsfvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get lsfvo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "N");
		return actionmapping.findForward("success");
	}

	public ActionForward edit(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		LsfVo lsfvo = LsfEntity.findByPrimaryKey(i);
		if (lsfvo != null) {
			httpsession.setAttribute("LSF_OBJECT", lsfvo);
			setFormVariable(dynavalidatorform, lsfvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get lsfvo for id(").append(s)
					.append(")").toString());
		}

		httpservletrequest.setAttribute("isComponentEditable", "Y");
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward reset(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		dynavalidatorform.set("firstname", "");
		dynavalidatorform.set("lastname", "");
		dynavalidatorform.set("certificatenumber", "");
		dynavalidatorform.set("phonenumber", "");
		dynavalidatorform.set("department", "");
		dynavalidatorform.set("issuedate", "");
		dynavalidatorform.set("expirationdate", "");
		dynavalidatorform.set("operatingequipments", null);
		dynavalidatorform.set("equipmentoperated", "");
		// dynavalidatorform.set("htypeofcoffrequired",null);
		// dynavalidatorform.set("coffobtained","");
		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = CofF.getDropDownObj();
		httpservletrequest.setAttribute("COFF_STATUS", dropdown);

	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.LsfAction.class);
	public String userAction;

}