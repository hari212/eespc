package com.eespc.tracking.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.eespc.tracking.bo.myenum.FacilityTypeEnum;

public class MasterReportBaseAction extends Action {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.MasterReportBaseAction.class);

	public MasterReportBaseAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("MasterReportBaseAction - In Execute");
		setDropDown(httpservletrequest);
		return actionmapping.findForward("success");
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = FacilityTypeEnum
				.getDropDownObj();
		httpservletrequest.setAttribute("FACILITY_TYPE", dropdown);
	}

}
