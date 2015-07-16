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

import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.ViolationVo;
import com.eespc.tracking.bo.myenum.ViolationType;
import com.eespc.tracking.entity.ViolationEntity;
import com.eespc.tracking.util.UtilityObject;

public class ViolationAction extends Action {

	public ViolationAction() {
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
		setDropDown(request);
		try {

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
			else if (userAction != null
					&& userAction.equalsIgnoreCase("viewexist"))
				return viewexist(mapping, form, request, response);
		} catch (Exception e) {
			System.out.println("Violation Execute Exception" + e);
		}

		dynavalidatorform.set("violationWhich", "");
		dynavalidatorform.set("violationno", "");
		dynavalidatorform.set("violationType", "");
		dynavalidatorform.set("description", "");
		dynavalidatorform.set("issueDate", "");
		dynavalidatorform.set("remedialMeassures", "");
		dynavalidatorform.set("contractor", "");
		dynavalidatorform.set("stepsTaken", "");
		dynavalidatorform.set("removalDate", "");
		dynavalidatorform.set("interMediateStatus", "");
		dynavalidatorform.set("finalComplianceDate", "");
		dynavalidatorform.set("jDate", "");
		dynavalidatorform.set("otherAgency", "");
		dynavalidatorform.set("feePaid", "");

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

	public ActionForward viewexist(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("Violation - In Edit");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		String s = (String) dynavalidatorform.get("id");
		int i = -99;
		if (s != null)
			i = Integer.parseInt(s);
		ViolationVo violationvo = ViolationEntity.findByPrimaryKey(i);
		if (violationvo != null) {
			/*
			 * httpsession.setAttribute("VIOLATION_OBJECT", violationvo);
			 * dynavalidatorform = setFormVariableforsearch(dynavalidatorform,
			 * violationvo, httpservletrequest);
			 */
			httpsession.setAttribute("VIOLATION_OBJECT", violationvo);
			setFormVariable(dynavalidatorform, violationvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get violationvo for id(").append(s)
					.append(")").toString());
		}
		/*
		 * httpservletrequest.setAttribute("SHOW_BUTTONS", "inline");
		 * httpservletrequest.setAttribute("SHOW_UPDATE_BUTTONS", "none");
		 */
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward save(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		BuildingVo buildingvo = (BuildingVo) httpsession
				.getAttribute("BUILDING_OBJECT");
		ViolationVo violationvo = new ViolationVo();
		violationvo.setViolationWhich(Integer.parseInt((String) dynaactionform
				.get("violationWhich")));
		violationvo.setViolationno((String) dynaactionform.get("violationno"));
		violationvo.setViolationType((String) dynaactionform
				.get("violationType"));
		violationvo.setDescription((String) dynaactionform.get("description"));
		violationvo.setRemedialMeassures((String) dynaactionform
				.get("remedialMeassures"));
		violationvo.setContractor((String) dynaactionform.get("contractor"));
		violationvo.setStepsTaken((String) dynaactionform.get("stepsTaken"));
		violationvo.setInterMediateStatus((String) dynaactionform
				.get("interMediateStatus"));
		violationvo.setIssueDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("issueDate")),
				"yyyy-MM-dd"));
		violationvo.setRemovalDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("removalDate")),
				"yyyy-MM-dd"));
		violationvo.setFinalComplianceDate(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("finalComplianceDate")), "yyyy-MM-dd"));
		violationvo.setJDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("jDate")),
				"yyyy-MM-dd"));
		violationvo.setOtherAgency((String) dynaactionform.get("otherAgency"));
		violationvo.setFeePaid((String) dynaactionform.get("feePaid"));
		byte byte0 = -99;
		try {

			int i = ViolationEntity.add(buildingvo.getId(), violationvo);

			violationvo = ViolationEntity.findByPrimaryKey(i);

			if (violationvo != null) {

				httpsession.setAttribute("VIOLATION_OBJECT", violationvo);

				setFormVariable(dynaactionform, violationvo, httpservletrequest);

			}

			s = "Added Violation Successfully.";
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

		ViolationVo violationvo = (ViolationVo) httpsession
				.getAttribute("VIOLATION_OBJECT");
		violationvo.setId(violationvo.getId());
		violationvo.setViolationWhich(Integer.parseInt((String) dynaactionform
				.get("violationWhich")));
		violationvo.setViolationno((String) dynaactionform.get("violationno"));
		violationvo.setViolationType((String) dynaactionform
				.get("violationType"));
		violationvo.setDescription((String) dynaactionform.get("description"));
		violationvo.setRemedialMeassures((String) dynaactionform
				.get("remedialMeassures"));
		violationvo.setContractor((String) dynaactionform.get("contractor"));
		violationvo.setStepsTaken((String) dynaactionform.get("stepsTaken"));
		violationvo.setInterMediateStatus((String) dynaactionform
				.get("interMediateStatus"));
		violationvo.setIssueDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("issueDate")),
				"yyyy-MM-dd"));
		violationvo.setRemovalDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("removalDate")),
				"yyyy-MM-dd"));
		violationvo.setFinalComplianceDate(UtilityObject.convertToString(
				UtilityObject.convertToDate((String) dynaactionform
						.get("finalComplianceDate")), "yyyy-MM-dd"));
		violationvo.setJDate(UtilityObject.convertToString(UtilityObject
				.convertToDate((String) dynaactionform.get("jDate")),
				"yyyy-MM-dd"));
		violationvo.setOtherAgency((String) dynaactionform.get("otherAgency"));
		violationvo.setFeePaid((String) dynaactionform.get("feePaid"));

		byte byte0 = -99;
		try {
			ViolationEntity.update(violationvo);
			s = "Updated VIOLATION Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, violationvo, httpservletrequest);
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
			ViolationVo violationvo = ViolationEntity.findByPrimaryKey(i);
			if (violationvo != null)
				httpsession.setAttribute("VIOLATION_OBJECT", violationvo);
			violationvo = (ViolationVo) httpsession
					.getAttribute("VIOLATION_OBJECT");
			ViolationEntity.delete(violationvo);
		} catch (Exception exception) {
			System.out.println("Exception " + exception);
		}
		return actionmapping.findForward("successfc");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			ViolationVo violationvo, HttpServletRequest httpservletrequest) {

		HttpSession httpsession = httpservletrequest.getSession();
		try {
			String arr[] = { "", "DOB", "DEP", "ECB", "DEC", "FD", "EPA",
					"STATE/CITY", "OTHER" };
			httpservletrequest.setAttribute("vtype",
					arr[violationvo.getViolationWhich()]);

			ViolationType violationtype = ViolationType.get(violationvo
					.getViolationWhich());

			if (violationtype != null && userAction.equalsIgnoreCase("edit"))
				dynavalidatorform.set(
						"violationWhich",
						(new StringBuilder())
								.append(String.valueOf(violationvo
										.getViolationWhich())).append("")
								.toString());
			else if (violationtype != null
					&& userAction.equalsIgnoreCase("viewexist"))
				dynavalidatorform.set(
						"violationWhich",
						(new StringBuilder())
								.append(String.valueOf(violationvo
										.getViolationWhich())).append("")
								.toString());

			else if (violationtype != null
					&& !userAction.equalsIgnoreCase("edit"))
				dynavalidatorform
						.set("violationWhich", violationtype.getName());
			else
				dynavalidatorform.set("violationWhich", "");

			// dynavalidatorform.set("violationWhich",violationvo.getViolationWhich());
			dynavalidatorform.set("violationno", violationvo.getViolationno());
			dynavalidatorform.set("violationType",
					violationvo.getViolationType());
			dynavalidatorform.set("description", violationvo.getDescription());
			dynavalidatorform.set("issueDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(violationvo.getIssueDate()));
			dynavalidatorform.set("remedialMeassures",
					violationvo.getRemedialMeassures());
			dynavalidatorform.set("contractor", violationvo.getContractor());
			dynavalidatorform.set("stepsTaken", violationvo.getStepsTaken());
			dynavalidatorform.set("removalDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(violationvo.getRemovalDate()));
			dynavalidatorform.set("interMediateStatus",
					violationvo.getInterMediateStatus());
			dynavalidatorform.set("finalComplianceDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(violationvo
							.getFinalComplianceDate()));
			dynavalidatorform.set("jDate", UtilityObject
					.convertYyyyMmDd2MmDdYyyy(violationvo.getJDate()));

			dynavalidatorform.set("otherAgency", violationvo.getOtherAgency());
			dynavalidatorform.set("feePaid", violationvo.getFeePaid());

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

		ViolationVo violationvo = ViolationEntity.findByPrimaryKey(i);
		if (violationvo != null) {
			httpsession.setAttribute("VIOLATION_OBJECT", violationvo);
			setFormVariable(dynavalidatorform, violationvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get violationvo for id(").append(s)
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
		ViolationVo violationvo = ViolationEntity.findByPrimaryKey(i);
		if (violationvo != null) {
			httpsession.setAttribute("VIOLATION_OBJECT", violationvo);
			setFormVariable(dynavalidatorform, violationvo, httpservletrequest);
		} else {
			log.debug((new StringBuilder())
					.append("Unable to get violationvo for id(").append(s)
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
		String arr[] = { "", "DOB", "DEP", "ECB", "DEC", "FD", "EPA",
				"STATE/CITY", "OTHER" };

		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		httpservletrequest.setAttribute("vtype", arr[Integer
				.parseInt((String) dynavalidatorform.get("violationWhich"))]);
		dynavalidatorform.set("violationWhich",
				(String) dynavalidatorform.get("violationWhich"));
		dynavalidatorform.set("violationno", "");
		dynavalidatorform.set("violationType", "");
		dynavalidatorform.set("description", "");
		dynavalidatorform.set("issueDate", "");
		dynavalidatorform.set("remedialMeassures", "");
		dynavalidatorform.set("contractor", "");
		dynavalidatorform.set("stepsTaken", "");
		dynavalidatorform.set("removalDate", "");
		dynavalidatorform.set("interMediateStatus", "");
		dynavalidatorform.set("finalComplianceDate", "");
		dynavalidatorform.set("jDate", "");
		dynavalidatorform.set("otherAgency", "");
		dynavalidatorform.set("feePaid", "");
		httpservletrequest.setAttribute("isItForEdit", "N");
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = ViolationType
				.getDropDownObj();
		httpservletrequest.setAttribute("VIOLATION_TYPE", dropdown);
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.ViolationAction.class);
	public String userAction;

}