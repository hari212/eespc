// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 1:48:40 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TankListAction.java

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
import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class TankListAction extends Action {

	public TankListAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		if (dynaactionform.get("formId") == null
				|| dynaactionform.get("formIdName") == null
				|| dynaactionform.get("tankType") == null) {
			throw new TrackingException(
					"Parameter formId,formIdName & tankType are mandatory.");
		} else {
			if (((String) dynaactionform.get("tankType")).equals("X")) {
				getTankListInfo(httpservletrequest);
			} else {
				boolean flag = UtilityObject
						.convertBoolean((String) dynaactionform.get("tankType"));
				getTankListInfo(httpservletrequest, flag);
			}
			log.debug("StackListAction - In Execute");
			return actionmapping.findForward("success");
		}
	}

	private void getTankListInfo(HttpServletRequest httpservletrequest,
			boolean flag) throws Exception {
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo == null)
			throw new TrackingException("Unable to get the Facility Info.");
		java.util.List list = StorageTankEntity.getFacilityTankList(
				facilityvo.getId(), flag);
		if (list != null)
			httpsession.setAttribute("FUEL_TANK_SEARCH_LIST", list);
		else
			httpsession.setAttribute("FUEL_TANK_SEARCH_LIST", new ArrayList());
	}

	private void getTankListInfo(HttpServletRequest httpservletrequest)
			throws Exception {
		HttpSession httpsession = httpservletrequest.getSession();
		FacilityVo facilityvo = (FacilityVo) httpsession
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo == null)
			throw new TrackingException("Unable to get the Facility Info.");
		java.util.List list = StorageTankEntity.getFacilityTankList(facilityvo
				.getId());
		if (list != null)
			httpsession.setAttribute("FUEL_TANK_SEARCH_LIST", list);
		else
			httpsession.setAttribute("FUEL_TANK_SEARCH_LIST", new ArrayList());
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.TankListAction.class);

}