// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReportServlet.java

package com.eespc.tracking.reports.servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eespc.tracking.reports.beans.ReportBean;

public class ReportServlet extends HttpServlet {

	public ReportServlet() {
	}

	public void doGet(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
		doPost(httpservletrequest, httpservletresponse);
	}

	public void doPost(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
		try {
			String s = httpservletrequest.getParameter("reportType");
			ServletContext servletcontext = getServletContext();
			ReportBean reportbean = new ReportBean();
			reportbean.setServletContext(servletcontext);
			reportbean.setReportType(s);
			reportbean.fillReport(httpservletrequest);
			httpservletrequest.getSession().setAttribute("reportBean",
					reportbean);
		} catch (Exception exception) {
			httpservletrequest.setAttribute("exception", exception);
			getServletContext()
					.getRequestDispatcher("/jsp/ReportException.jsp").forward(
							httpservletrequest, httpservletresponse);
		}
		getServletContext().getRequestDispatcher("/jsp/ReportViewer.jsp")
				.include(httpservletrequest, httpservletresponse);
	}

	public void init() throws ServletException {
		super.init();
		ServletContext servletcontext = getServletContext();
		System.setProperty(
				"jasper.reports.compile.class.path",
				(new StringBuilder())
						.append(servletcontext
								.getRealPath("/WEB-INF/lib/jasperreports-0.6.7.jar"))
						.append(System.getProperty("path.separator"))
						.append(servletcontext.getRealPath("/WEB-INF/classes/"))
						.toString());
		System.setProperty("jasper.reports.compile.temp",
				servletcontext.getRealPath("/reports/"));
	}
}
