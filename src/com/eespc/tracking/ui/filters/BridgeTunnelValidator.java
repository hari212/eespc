package com.eespc.tracking.ui.filters;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.exceptions.TrackingException;

public class BridgeTunnelValidator implements ResourceTypeValidatorIntf {

	public BridgeTunnelValidator() {
	}

	public boolean isValidToShow(HttpServletRequest httpservletrequest)
			throws TrackingException {
		boolean flag = false;
		FacilityVo facilityvo = (FacilityVo) httpservletrequest.getSession()
				.getAttribute("FACILITY_OBJECT");
		if (facilityvo == null)
			throw new TrackingException(
					"Unable to get Facility Object from session");
		int i = facilityvo.getBorough();
		/*
		 * try { flag = BoroughsEnum.isValidBoroughInNYC(i); } catch(Exception
		 * exception) { log.error(exception); } return flag;
		 */
		return true;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.ui.filters.BridgeTunnelValidator.class);

}