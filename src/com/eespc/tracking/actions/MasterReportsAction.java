package com.eespc.tracking.actions;

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

import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.entity.FacilityEntity;
import com.eespc.tracking.util.UtilityObject;

public class MasterReportsAction extends Action {

	public MasterReportsAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug((new StringBuilder())
				.append("MasterReportsAction - In Execute")
				.append(dynaactionform.get("rptType")).toString());
		String s = (String) dynaactionform.get("rptType");
		if (UtilityObject.isNotEmpty(s)) {
			HttpSession httpsession = httpservletrequest.getSession();
			UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");
			System.out.println(s + "-" + uservo.getUserId());
			// FacilityManager facilitymanager = new FacilityManager();
			java.util.List list = FacilityEntity.getMasterReport(
					Integer.parseInt(s), uservo.getUserId());
			System.out.println("MASTER_REPORT+SIZE:" + list.size());
			if (list != null)
				httpservletrequest.setAttribute("MASTER_REPORT", list);
		}
		return actionmapping.findForward("success");
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.MasterReportsAction.class);

}