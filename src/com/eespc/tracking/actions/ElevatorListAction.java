// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 1:45:12 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ElevatorListAction.java

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
import com.eespc.tracking.entity.ElevatorEntity;
import com.eespc.tracking.exceptions.TrackingException;

public class ElevatorListAction extends Action {

	public ElevatorListAction() {
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
			getHydraulicTankListInfo(httpservletrequest);
			log.debug("ElevatorListAction - In Execute");
			System.out.println("sss1");
			return actionmapping.findForward("success");
		}
	}

	private void getHydraulicTankListInfo(HttpServletRequest httpservletrequest)
			throws Exception {
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo == null)
			throw new TrackingException("Unable to get the Facility Info.");
		java.util.List list = ElevatorEntity.getFacilityStackList(facilityvo
				.getId());
		System.out.println((new StringBuilder()).append("List=").append(list)
				.toString());
		if (list != null)
			httpsession.setAttribute("ELEVATOR_SEARCH_LIST", list);
		else
			httpsession.setAttribute("ELEVATOR_SEARCH_LIST", new ArrayList());
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.ElevatorListAction.class);

}