// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XlsServlet.java

package com.eespc.tracking.reports.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eespc.tracking.reports.beans.ReportBean;

public class XlsServlet extends HttpServlet {

	public XlsServlet() {
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		byte bytes[] = (byte[]) null;
		ReportBean reportBean = (ReportBean) request.getSession().getAttribute(
				"reportBean");
		if (reportBean != null)
			bytes = reportBean.getXls();
		if (bytes != null && bytes.length > 0) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition",
					"inline; filename=\"file.xls\"");
			response.setContentLength(bytes.length);
			ServletOutputStream ouputStream = response.getOutputStream();
			try {
				ouputStream.write(bytes, 0, bytes.length);
				ouputStream.flush();
			} catch (IOException e) {
				if (ouputStream != null)
					try {
						ouputStream.close();
					} catch (IOException ioexception) {
					}
			}
		} else {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body bgcolor=\"white\">");
			out.println("<span class=\"bold\">Empty response.</span>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
