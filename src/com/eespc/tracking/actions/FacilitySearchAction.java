package com.eespc.tracking.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.eespc.tracking.bo.FacilityManager;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.bo.myenum.FacilityTypeEnum;

public class FacilitySearchAction extends Action {

	public FacilitySearchAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("methodToCall");
		if (s != null && s.equalsIgnoreCase("SEARCH"))
			return search(actionmapping, actionform, httpservletrequest,
					httpservletresponse);
		if (s != null && !s.equalsIgnoreCase("SORT")) {
			dynaactionform.set("clName", "");
			dynaactionform.set("clDecId", "");
			dynaactionform.set("clFacilityType", "");
			dynaactionform.set("methodToCall", "");
		}
		setDropDown(httpservletrequest);
		return actionmapping.findForward("success");
	}

	public ActionForward search(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("clName");
		String s1 = (String) dynaactionform.get("clDecId");
		String s2 = (String) dynaactionform.get("clFacilityType");
		int i = -99;
		if (s2 != null && s2.trim().length() > 0)
			try {
				i = Integer.parseInt(s2);
			} catch (NumberFormatException numberformatexception) {
			}
		HttpSession httpsession = httpservletrequest.getSession();
		UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");
		FacilityManager facilitymanager = new FacilityManager(new FacilityVo());
		java.util.List list = facilitymanager.searchBy(s, s1, i,
				uservo.getUserId());
		if (list != null)
			httpsession.setAttribute("FACILITY_SEARCH_LIST", list);
		setDropDown(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = FacilityTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("FACILITY_TYPE", dropdown);
	}
}