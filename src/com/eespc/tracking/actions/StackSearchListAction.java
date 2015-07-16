package com.eespc.tracking.actions;

import java.util.ArrayList;

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

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.entity.StackEntity;
import com.eespc.tracking.exceptions.TrackingException;

// Referenced classes of package com.eespc.tracking.actions:
//            StackListAction

public class StackSearchListAction extends Action {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.StackListAction.class);

	public StackSearchListAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		if (dynaactionform.get("formId") == null
				|| dynaactionform.get("formIdName") == null) {
			throw new TrackingException(
					"Parameter formId and formIdName are mandatory.");
		} else {
			getStackListInfo(httpservletrequest);
			log.debug("StackListAction - In Execute");
			return actionmapping.findForward("success");
		}
	}

	private void getStackListInfo(HttpServletRequest httpservletrequest)
			throws Exception {
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo == null) {
			throw new TrackingException("Unable to get the Facility Info.");
		}
		java.util.List list = StackEntity.getFacilityStackList(facilityvo
				.getId());
		if (list != null) {
			httpsession.setAttribute("STACK_SEARCH_LIST", list);
		} else {
			httpsession.setAttribute("STACK_SEARCH_LIST", new ArrayList());
		}
	}

}
