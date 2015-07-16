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
import com.eespc.tracking.entity.CogenTurbineEntity;
import com.eespc.tracking.exceptions.TrackingException;

public class CogenListAction extends Action {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.CogenListAction.class);

	public CogenListAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		getCoGenListInfo(httpservletrequest);
		log.debug("CogenListAction - In Execute");
		return actionmapping.findForward("success");
	}

	private void getCoGenListInfo(HttpServletRequest httpservletrequest)
			throws Exception {
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo == null) {
			throw new TrackingException("Unable to get the Facility Info.");
		}
		java.util.List list = CogenTurbineEntity.getCogenTurbineList(facilityvo
				.getId());
		if (list != null) {
			httpsession.setAttribute("COGEN_LIST", list);
		} else {
			httpsession.setAttribute("COGEN_LIST", new ArrayList());
		}
	}

}
