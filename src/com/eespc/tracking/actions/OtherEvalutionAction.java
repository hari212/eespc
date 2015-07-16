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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.validator.DynaValidatorForm;

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.OtherEvalutionVo;
import com.eespc.tracking.entity.OtherEvalutionEntity;
import com.eespc.tracking.exceptions.TrackingException;

public class OtherEvalutionAction extends LookupDispatchAction {

	public OtherEvalutionAction() {
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
			HttpSession session = request.getSession();
			userAction = (String) dynavalidatorform.get("methodToCall");
			if (userAction != null && userAction.equalsIgnoreCase("Save"))
				return save(mapping, form, request, response);
			else if (userAction != null
					&& userAction.equalsIgnoreCase("Update"))
				return update(mapping, form, request, response);
			else if (userAction != null && userAction.equalsIgnoreCase("view"))
				return view(mapping, form, request, response);
		} catch (Exception e) {
			System.out.println("Execute Exception" + e);
		}
		return mapping.findForward("success");
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
		int a = facilityvo.getId();
		OtherEvalutionVo otherevalutionvo = new OtherEvalutionVo();
		otherevalutionvo.setFacilityId(a);

		otherevalutionvo.setSpcc_req((String) dynaactionform.get("spcc_req"));
		otherevalutionvo.setStormprevention_req((String) dynaactionform
				.get("stormprevention_req"));
		otherevalutionvo.setEpaaudit_req((String) dynaactionform
				.get("epaaudit_req"));
		otherevalutionvo.setAccfinding_req((String) dynaactionform
				.get("accfinding_req"));
		otherevalutionvo.setHazwaste_req((String) dynaactionform
				.get("hazwaste_req"));
		otherevalutionvo.setHazr_req((String) dynaactionform.get("hazr_req"));
		otherevalutionvo.setTier2_req((String) dynaactionform.get("tier2_req"));
		otherevalutionvo.setNyccom_req((String) dynaactionform
				.get("nyccom_req"));
		otherevalutionvo.setDobvio_req((String) dynaactionform
				.get("dobvio_req"));
		otherevalutionvo.setFd_req((String) dynaactionform.get("fd_req"));
		otherevalutionvo.setEcp_req((String) dynaactionform.get("ecp_req"));
		otherevalutionvo.setDep_req((String) dynaactionform.get("dep_req"));
		otherevalutionvo.setAnystate_req((String) dynaactionform
				.get("anystate_req"));
		otherevalutionvo.setDoh_req((String) dynaactionform.get("doh_req"));
		otherevalutionvo.setBuiltinplan_req((String) dynaactionform
				.get("builtinplan_req"));
		otherevalutionvo.setFuelinventory_req((String) dynaactionform
				.get("fuelinventory_req"));
		otherevalutionvo.setOpacity_req((String) dynaactionform
				.get("opacity_req"));
		otherevalutionvo.setRefri_req((String) dynaactionform.get("refri_req"));
		otherevalutionvo.setRisk_req((String) dynaactionform.get("risk_req"));
		otherevalutionvo.setOther_req((String) dynaactionform.get("other_req"));
		otherevalutionvo.setSpcc_avi((String) dynaactionform.get("spcc_avi"));
		otherevalutionvo.setStormprevention_avi((String) dynaactionform
				.get("stormprevention_avi"));
		otherevalutionvo.setEpaaudit_avi((String) dynaactionform
				.get("epaaudit_avi"));
		otherevalutionvo.setAccfinding_avi((String) dynaactionform
				.get("accfinding_avi"));
		otherevalutionvo.setHazwaste_avi((String) dynaactionform
				.get("hazwaste_avi"));
		otherevalutionvo.setHazr_avi((String) dynaactionform.get("hazr_avi"));
		otherevalutionvo.setTier2_avi((String) dynaactionform.get("tier2_avi"));
		otherevalutionvo.setNyccom_avi((String) dynaactionform
				.get("nyccom_avi"));
		otherevalutionvo.setDobvio_avi((String) dynaactionform
				.get("dobvio_avi"));
		otherevalutionvo.setFd_avi((String) dynaactionform.get("fd_avi"));
		otherevalutionvo.setEcp_avi((String) dynaactionform.get("ecp_avi"));
		otherevalutionvo.setDep_avi((String) dynaactionform.get("dep_avi"));
		otherevalutionvo.setAnystate_avi((String) dynaactionform
				.get("anystate_avi"));
		otherevalutionvo.setDoh_avi((String) dynaactionform.get("doh_avi"));
		otherevalutionvo.setBuiltinplan_avi((String) dynaactionform
				.get("builtinplan_avi"));
		otherevalutionvo.setFuelinventory_avi((String) dynaactionform
				.get("fuelinventory_avi"));
		otherevalutionvo.setOpacity_avi((String) dynaactionform
				.get("opacity_avi"));
		otherevalutionvo.setRefri_avi((String) dynaactionform.get("refri_avi"));
		otherevalutionvo.setRisk_avi((String) dynaactionform.get("risk_avi"));
		otherevalutionvo.setOther_avi((String) dynaactionform.get("other_avi"));
		otherevalutionvo.setOther_inp((String) dynaactionform.get("other_inp"));
		otherevalutionvo.setLaundry_avi((String) dynaactionform
				.get("laundry_avi"));
		otherevalutionvo.setLaundry_req((String) dynaactionform
				.get("laundry_req"));
		otherevalutionvo.setCompliancereport_avi((String) dynaactionform
				.get("compliancereport_avi"));
		otherevalutionvo.setCompliancereport_req((String) dynaactionform
				.get("compliancereport_req"));
		otherevalutionvo.setDepofenergy_avi((String) dynaactionform
				.get("depofenergy_avi"));
		otherevalutionvo.setDepofenergy_req((String) dynaactionform
				.get("depofenergy_req"));
		byte byte0 = -99;
		try {
			int i = OtherEvalutionEntity.add(otherevalutionvo);
			otherevalutionvo = OtherEvalutionEntity.findByPrimaryKey(i);
			if (otherevalutionvo != null) {
				httpsession.setAttribute("OTHEREVALUTION_OBJECT",
						otherevalutionvo);
				setFormVariable(dynaactionform, otherevalutionvo,
						httpservletrequest);
			}
			s = "Added Other Compliance Evaluation Successfully.";
			s1 = "CONFIRMATION";
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		} catch (TrackingException trackingexception) {
			System.out.println("Exception :" + trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException, Exception {

		DynaValidatorForm dynaactionform = (DynaValidatorForm) actionform;
		String s = "";
		String s1 = "";
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		int a = facilityvo.getId();

		OtherEvalutionVo otherevalutionvo = (OtherEvalutionVo) httpsession
				.getAttribute("OTHEREVALUTION_OBJECT");
		otherevalutionvo.setFacilityId(a);
		otherevalutionvo.setSpcc_req((String) dynaactionform.get("spcc_req"));
		otherevalutionvo.setStormprevention_req((String) dynaactionform
				.get("stormprevention_req"));
		otherevalutionvo.setEpaaudit_req((String) dynaactionform
				.get("epaaudit_req"));
		otherevalutionvo.setAccfinding_req((String) dynaactionform
				.get("accfinding_req"));
		otherevalutionvo.setHazwaste_req((String) dynaactionform
				.get("hazwaste_req"));
		otherevalutionvo.setHazr_req((String) dynaactionform.get("hazr_req"));
		otherevalutionvo.setTier2_req((String) dynaactionform.get("tier2_req"));
		otherevalutionvo.setNyccom_req((String) dynaactionform
				.get("nyccom_req"));
		otherevalutionvo.setDobvio_req((String) dynaactionform
				.get("dobvio_req"));
		otherevalutionvo.setFd_req((String) dynaactionform.get("fd_req"));
		otherevalutionvo.setEcp_req((String) dynaactionform.get("ecp_req"));
		otherevalutionvo.setDep_req((String) dynaactionform.get("dep_req"));
		otherevalutionvo.setAnystate_req((String) dynaactionform
				.get("anystate_req"));
		otherevalutionvo.setDoh_req((String) dynaactionform.get("doh_req"));
		otherevalutionvo.setBuiltinplan_req((String) dynaactionform
				.get("builtinplan_req"));
		otherevalutionvo.setFuelinventory_req((String) dynaactionform
				.get("fuelinventory_req"));
		otherevalutionvo.setOpacity_req((String) dynaactionform
				.get("opacity_req"));
		otherevalutionvo.setRefri_req((String) dynaactionform.get("refri_req"));
		otherevalutionvo.setRisk_req((String) dynaactionform.get("risk_req"));
		otherevalutionvo.setOther_req((String) dynaactionform.get("other_req"));
		otherevalutionvo.setSpcc_avi((String) dynaactionform.get("spcc_avi"));
		otherevalutionvo.setStormprevention_avi((String) dynaactionform
				.get("stormprevention_avi"));
		otherevalutionvo.setEpaaudit_avi((String) dynaactionform
				.get("epaaudit_avi"));
		otherevalutionvo.setAccfinding_avi((String) dynaactionform
				.get("accfinding_avi"));
		otherevalutionvo.setHazwaste_avi((String) dynaactionform
				.get("hazwaste_avi"));
		otherevalutionvo.setHazr_avi((String) dynaactionform.get("hazr_avi"));
		otherevalutionvo.setTier2_avi((String) dynaactionform.get("tier2_avi"));
		otherevalutionvo.setNyccom_avi((String) dynaactionform
				.get("nyccom_avi"));
		otherevalutionvo.setDobvio_avi((String) dynaactionform
				.get("dobvio_avi"));
		otherevalutionvo.setFd_avi((String) dynaactionform.get("fd_avi"));
		otherevalutionvo.setEcp_avi((String) dynaactionform.get("ecp_avi"));
		otherevalutionvo.setDep_avi((String) dynaactionform.get("dep_avi"));
		otherevalutionvo.setAnystate_avi((String) dynaactionform
				.get("anystate_avi"));
		otherevalutionvo.setDoh_avi((String) dynaactionform.get("doh_avi"));
		otherevalutionvo.setBuiltinplan_avi((String) dynaactionform
				.get("builtinplan_avi"));
		otherevalutionvo.setFuelinventory_avi((String) dynaactionform
				.get("fuelinventory_avi"));
		otherevalutionvo.setOpacity_avi((String) dynaactionform
				.get("opacity_avi"));
		otherevalutionvo.setRefri_avi((String) dynaactionform.get("refri_avi"));
		otherevalutionvo.setRisk_avi((String) dynaactionform.get("risk_avi"));
		otherevalutionvo.setOther_avi((String) dynaactionform.get("other_avi"));
		otherevalutionvo.setOther_inp((String) dynaactionform.get("other_inp"));
		otherevalutionvo.setLaundry_avi((String) dynaactionform
				.get("laundry_avi"));
		otherevalutionvo.setLaundry_req((String) dynaactionform
				.get("laundry_req"));
		otherevalutionvo.setCompliancereport_avi((String) dynaactionform
				.get("compliancereport_avi"));
		otherevalutionvo.setCompliancereport_req((String) dynaactionform
				.get("compliancereport_req"));
		otherevalutionvo.setDepofenergy_avi((String) dynaactionform
				.get("depofenergy_avi"));
		otherevalutionvo.setDepofenergy_req((String) dynaactionform
				.get("depofenergy_req"));

		byte byte0 = -99;
		try {
			OtherEvalutionEntity.update(otherevalutionvo);
			if (otherevalutionvo != null) {
				httpsession.setAttribute("OTHEREVALUTION_OBJECT",
						otherevalutionvo);
			}
			s = "Updated OtherEvaluation Successfully.";
			s1 = "CONFIRMATION";
			setFormVariable(dynaactionform, otherevalutionvo,
					httpservletrequest);
			httpservletrequest.setAttribute("isComponentEditable", "Y");
		} catch (TrackingException trackingexception) {
			log.error(trackingexception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		httpservletrequest.setAttribute("isItForEdit", "Y");
		return actionmapping.findForward("success");
	}

	public void setFormVariable(DynaValidatorForm dynavalidatorform,
			OtherEvalutionVo otherevalutionvo,
			HttpServletRequest httpservletrequest) {
		dynavalidatorform.set("spcc_req", otherevalutionvo.getSpcc_req());
		dynavalidatorform.set("stormprevention_req",
				otherevalutionvo.getStormprevention_req());
		dynavalidatorform.set("epaaudit_req",
				otherevalutionvo.getEpaaudit_req());
		dynavalidatorform.set("accfinding_req",
				otherevalutionvo.getAccfinding_req());
		dynavalidatorform.set("hazwaste_req",
				otherevalutionvo.getHazwaste_req());
		dynavalidatorform.set("hazr_req", otherevalutionvo.getHazr_req());
		dynavalidatorform.set("tier2_req", otherevalutionvo.getTier2_req());
		dynavalidatorform.set("nyccom_req", otherevalutionvo.getNyccom_req());
		dynavalidatorform.set("dobvio_req", otherevalutionvo.getDobvio_req());
		dynavalidatorform.set("fd_req", otherevalutionvo.getFd_req());
		dynavalidatorform.set("ecp_req", otherevalutionvo.getEcp_req());
		dynavalidatorform.set("dep_req", otherevalutionvo.getDep_req());
		dynavalidatorform.set("anystate_req",
				otherevalutionvo.getAnystate_req());
		dynavalidatorform.set("doh_req", otherevalutionvo.getDoh_req());
		dynavalidatorform.set("builtinplan_req",
				otherevalutionvo.getBuiltinplan_req());
		dynavalidatorform.set("fuelinventory_req",
				otherevalutionvo.getFuelinventory_req());
		dynavalidatorform.set("opacity_req", otherevalutionvo.getOpacity_req());
		dynavalidatorform.set("refri_req", otherevalutionvo.getRefri_req());
		dynavalidatorform.set("risk_req", otherevalutionvo.getRisk_req());
		dynavalidatorform.set("other_req", otherevalutionvo.getOther_req());
		dynavalidatorform.set("spcc_avi", otherevalutionvo.getSpcc_avi());
		dynavalidatorform.set("stormprevention_avi",
				otherevalutionvo.getStormprevention_avi());
		dynavalidatorform.set("epaaudit_avi",
				otherevalutionvo.getEpaaudit_avi());
		dynavalidatorform.set("accfinding_avi",
				otherevalutionvo.getAccfinding_avi());
		dynavalidatorform.set("hazwaste_avi",
				otherevalutionvo.getHazwaste_avi());
		dynavalidatorform.set("hazr_avi", otherevalutionvo.getHazr_avi());
		dynavalidatorform.set("tier2_avi", otherevalutionvo.getTier2_avi());
		dynavalidatorform.set("nyccom_avi", otherevalutionvo.getNyccom_avi());
		dynavalidatorform.set("dobvio_avi", otherevalutionvo.getDobvio_avi());
		dynavalidatorform.set("fd_avi", otherevalutionvo.getFd_avi());
		dynavalidatorform.set("ecp_avi", otherevalutionvo.getEcp_avi());
		dynavalidatorform.set("dep_avi", otherevalutionvo.getDep_avi());
		dynavalidatorform.set("anystate_avi",
				otherevalutionvo.getAnystate_avi());
		dynavalidatorform.set("doh_avi", otherevalutionvo.getDoh_avi());
		dynavalidatorform.set("builtinplan_avi",
				otherevalutionvo.getBuiltinplan_avi());
		dynavalidatorform.set("fuelinventory_avi",
				otherevalutionvo.getFuelinventory_avi());
		dynavalidatorform.set("opacity_avi", otherevalutionvo.getOpacity_avi());
		dynavalidatorform.set("refri_avi", otherevalutionvo.getRefri_avi());
		dynavalidatorform.set("risk_avi", otherevalutionvo.getRisk_avi());
		dynavalidatorform.set("other_avi", otherevalutionvo.getOther_avi());
		dynavalidatorform.set("other_inp", otherevalutionvo.getOther_inp());
		dynavalidatorform.set("laundry_avi", otherevalutionvo.getLaundry_avi());
		dynavalidatorform.set("laundry_req", otherevalutionvo.getLaundry_req());
		dynavalidatorform.set("compliancereport_avi",
				otherevalutionvo.getCompliancereport_avi());
		dynavalidatorform.set("compliancereport_req",
				otherevalutionvo.getCompliancereport_req());
		dynavalidatorform.set("depofenergy_avi",
				otherevalutionvo.getDepofenergy_avi());
		dynavalidatorform.set("depofenergy_req",
				otherevalutionvo.getDepofenergy_req());

	}

	public void resetFormVariable(DynaValidatorForm dynavalidatorform,
			OtherEvalutionVo otherevalutionvo,
			HttpServletRequest httpservletrequest) {

		dynavalidatorform.set("spcc_req", "");
		dynavalidatorform.set("stormprevention_req", "");
		dynavalidatorform.set("epaaudit_req", "");
		dynavalidatorform.set("accfinding_req", "");
		dynavalidatorform.set("hazwaste_req", "");
		dynavalidatorform.set("hazr_req", "");
		dynavalidatorform.set("tier2_req", "");
		dynavalidatorform.set("nyccom_req", "");
		dynavalidatorform.set("dobvio_req", "");
		dynavalidatorform.set("fd_req", "");
		dynavalidatorform.set("ecp_req", "");
		dynavalidatorform.set("dep_req", "");
		dynavalidatorform.set("anystate_req", "");
		dynavalidatorform.set("doh_req", "");
		dynavalidatorform.set("builtinplan_req", "");
		dynavalidatorform.set("fuelinventory_req", "");
		dynavalidatorform.set("opacity_req", "");
		dynavalidatorform.set("refri_req", "");
		dynavalidatorform.set("risk_req", "");
		dynavalidatorform.set("other_req", "");
		dynavalidatorform.set("spcc_avi", "");
		dynavalidatorform.set("stormprevention_avi", "");
		dynavalidatorform.set("epaaudit_avi", "");
		dynavalidatorform.set("accfinding_avi", "");
		dynavalidatorform.set("hazwaste_avi", "");
		dynavalidatorform.set("hazr_avi", "");
		dynavalidatorform.set("tier2_avi", "");
		dynavalidatorform.set("nyccom_avi", "");
		dynavalidatorform.set("dobvio_avi", "");
		dynavalidatorform.set("fd_avi", "");
		dynavalidatorform.set("ecp_avi", "");
		dynavalidatorform.set("dep_avi", "");
		dynavalidatorform.set("anystate_avi", "");
		dynavalidatorform.set("doh_avi", "");
		dynavalidatorform.set("builtinplan_avi", "");
		dynavalidatorform.set("fuelinventory_avi", "");
		dynavalidatorform.set("opacity_avi", "");
		dynavalidatorform.set("refri_avi", "");
		dynavalidatorform.set("risk_avi", "");
		dynavalidatorform.set("other_avi", "");
		dynavalidatorform.set("other_inp", "");
		dynavalidatorform.set("laundry_avi", "");
		dynavalidatorform.set("laundry_req", "");
		dynavalidatorform.set("compliancereport_avi", "");
		dynavalidatorform.set("compliancereport_req", "");
		dynavalidatorform.set("depofenergy_avi", "");
		dynavalidatorform.set("depofenergy_req", "");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("OtherEvalution - In View");
		DynaValidatorForm dynavalidatorform = (DynaValidatorForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		int a = facilityvo.getId();
		OtherEvalutionVo otherevalutionvo = OtherEvalutionEntity
				.facilityfindByPrimaryKey(a);
		if (otherevalutionvo != null) {
			httpservletrequest.setAttribute("isItForEdit", "Y");
			httpsession.setAttribute("OTHEREVALUTION_OBJECT", otherevalutionvo);
			setFormVariable(dynavalidatorform, otherevalutionvo,
					httpservletrequest);
		} else {
			httpservletrequest.setAttribute("isItForEdit", "N");
			httpservletrequest.getSession().removeAttribute(
					"OTHEREVALUTION_OBJECT");
			resetFormVariable(dynavalidatorform, otherevalutionvo,
					httpservletrequest);
		}
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		return actionmapping.findForward("success");
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.OtherEvalutionAction.class);
	public String userAction;

}