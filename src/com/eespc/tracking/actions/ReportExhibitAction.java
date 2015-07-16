package com.eespc.tracking.actions;

//import Test.TestFacility;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eespc.tracking.bo.FacilityManager;
import com.eespc.tracking.bo.myenum.ReportExhibitsEnum;
import com.eespc.tracking.util.UtilityObject;

public class ReportExhibitAction extends Action {

	public ReportExhibitAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = httpservletrequest.getParameter("id");
		if (!UtilityObject.isNotEmpty(s)) {
			throw new Exception("Unable to get the Client id to process");
		} else {
			httpservletresponse.setContentType("text/xml");
			httpservletresponse.setHeader("Cache-Control", "no-cache");
			PrintWriter printwriter = httpservletresponse.getWriter();
			int i = Integer.parseInt(s);
			printwriter.println(getXml(i, httpservletrequest));
			System.out.println((new StringBuilder()).append("Get Exhibit : ")
					.append(getXml(i, httpservletrequest)).append("Break  : ")
					.toString());
			printwriter.flush();
			System.out.println("------------------------");
			System.out.println((new StringBuilder())
					.append("Get Exhibit Id : ").append(i).toString());
			System.out.println("------------------------");
			// (new TestFacility()).setCurrentFacilityID(i);
			return null;
		}
	}

	private String getXml(int i, HttpServletRequest httpservletrequest)
			throws Exception {
		FacilityManager facilitymanager = new FacilityManager();
		com.eespc.tracking.bo.FacilityVo facilityvo = facilitymanager
				.findById(i);
		if (facilityvo != null)
			httpservletrequest.getSession().setAttribute("REPORT_FACILITY_VO",
					facilityvo);
		return ReportExhibitsEnum.getExhibits(facilityvo);
	}
}