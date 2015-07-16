// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 3/30/2014 6:10:25 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ReportFacilitySearch.java

package com.eespc.tracking.actions;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eespc.tracking.bo.FacilityManager;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.util.UtilityObject;

public class ReportFacilitySearch extends Action {

	public ReportFacilitySearch() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = httpservletrequest.getParameter("clName");
		Object obj = new ArrayList();
		if (UtilityObject.isNotEmpty(s)) {
			HttpSession httpsession = httpservletrequest.getSession();
			UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");
			FacilityManager facilitymanager = new FacilityManager(
					new FacilityVo());
			obj = facilitymanager.searchBy(s, null, -99, uservo.getUserId());
		}
		long l = System.currentTimeMillis();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<TABLE BORDER=0 CELLPADDING=1 CELLSPACING=1 BGCOLOR=\"#006699\" WIDTH=100%>");
		stringbuffer.append("<TR>");
		stringbuffer
				.append("<TD><TABLE BORDER=0 CELLPADDING=1 CELLSPACING=1 BGCOLOR=\"#006699\" WIDTH=100%>");
		stringbuffer.append("        <TR> ");
		stringbuffer.append("          <TH CLASS=\"header\">Select</TH>");
		stringbuffer.append("          <TH CLASS=\"header\">Client Name</TH>");
		stringbuffer.append("          <TH CLASS=\"header\">DEC Id</TH>");
		stringbuffer
				.append("          <TH CLASS=\"header\">Type of Facility</TH>");
		stringbuffer.append("          <TH CLASS=\"header\">City</TH>");
		stringbuffer.append("          <TH CLASS=\"header\">State</TH>");
		stringbuffer.append("        </TR>");
		int i = 0;
		String s1 = "'#FFFFFF'";
		for (Iterator iterator = ((List) (List) obj).iterator(); iterator
				.hasNext();) {
			FacilityVo facilityvo = (FacilityVo) iterator.next();
			String s2;
			if (i % 2 == 0)
				s2 = "'#EEEEEE'";
			else
				s2 = "'#FFFFFF'";
			stringbuffer
					.append("<TR bgcolor=")
					.append(s2)
					.append(" onMouseOver=\"this.bgColor='#CCCCFF';\" onMouseOut=\"this.bgColor=")
					.append(s2).append(";\">");
			stringbuffer.append("    <TD CLASS=data1_nocolor>");
			stringbuffer
					.append("     <input type=\"radio\" name=\"clientId\" value=\"")
					.append(facilityvo.getId())
					.append("\" onClick=\"fetchExhibit('")
					.append(facilityvo.getId()).append("');\"></TD>");
			stringbuffer.append("    <TD CLASS=data1_nocolor>")
					.append(facilityvo.getClientName()).append("</TD>");
			stringbuffer.append("    <TD CLASS=data1_nocolor>")
					.append(facilityvo.getDecId()).append("</TD>");
			stringbuffer.append("    <TD CLASS=data1_nocolor>")
					.append(facilityvo.getFacilityTypeName()).append("</TD>");
			stringbuffer.append("    <TD CLASS=data1_nocolor>")
					.append(facilityvo.getFacilityCity()).append("</TD>");
			stringbuffer.append("    <TD CLASS=data1_nocolor>")
					.append(facilityvo.getFacilityState()).append("</TD>");
			stringbuffer.append("</TR>");
			System.out.println((new StringBuilder()).append("Facility Vo : ")
					.append(facilityvo.getId()).toString());
			System.out.println((new StringBuilder()).append("Client Name : ")
					.append(facilityvo.getClientName()).toString());
			System.out.println((new StringBuilder()).append("Dec Id : ")
					.append(facilityvo.getDecId()).toString());
			System.out.println((new StringBuilder())
					.append("Facility Type name : ")
					.append(facilityvo.getFacilityTypeName()).toString());
			System.out.println((new StringBuilder()).append("Facility City ")
					.append(facilityvo.getFacilityCity()).toString());
			System.out.println((new StringBuilder())
					.append("Facility State : ")
					.append(facilityvo.getFacilityState()).toString());
			i++;
		}

		if (i == 0) {
			stringbuffer.append("<tr bgcolor='#FFFFFF'>");
			stringbuffer
					.append("<td colspan=6 align='center'><CENTER><SPAN CLASS=error>No facility available.</SPAN></CENTER></td>");
			stringbuffer.append("</tr>");
		}
		stringbuffer.append("</TABLE></TD>");
		stringbuffer.append("  </TR>");
		stringbuffer.append("</TABLE>");
		log.debug((new StringBuilder())
				.append("Time take for table construction ")
				.append(System.currentTimeMillis() - l).append(" ms.")
				.toString());
		httpservletresponse.setContentType("text/html");
		PrintWriter printwriter = httpservletresponse.getWriter();
		printwriter.println(stringbuffer.toString());
		printwriter.flush();
		return null;
	}

	private static Log log = LogFactory
			.getLog("com/eespc/tracking/actions/ReportFacilitySearch");

}