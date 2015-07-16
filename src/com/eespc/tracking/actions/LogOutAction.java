package com.eespc.tracking.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eespc.tracking.util.UtilityObject;

public class LogOutAction extends Action {

	Log log;

	public LogOutAction() {
		log = LogFactory.getLog(com.eespc.tracking.actions.LogOutAction.class);
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		UtilityObject.logOut(httpservletrequest);
		log.debug("Removed all the session objects, going to call login screen.");
		return actionmapping.findForward("success");
	}
}
