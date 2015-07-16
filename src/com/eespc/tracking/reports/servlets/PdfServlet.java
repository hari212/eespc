// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfServlet.java

package com.eespc.tracking.reports.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eespc.tracking.reports.beans.ReportBean;

public class PdfServlet extends HttpServlet {

	public PdfServlet() {
	}

	public void service(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		byte abyte0[] = (byte[]) null;
		ReportBean reportbean = (ReportBean) httpservletrequest.getSession()
				.getAttribute("reportBean");
		if (reportbean != null)
			abyte0 = reportbean.getPdf();
		if (abyte0 != null && abyte0.length > 0) {
			httpservletresponse.setContentType("application/pdf");
			httpservletresponse.setContentLength(abyte0.length);
			ServletOutputStream servletoutputstream = httpservletresponse
					.getOutputStream();
			try {
				servletoutputstream.write(abyte0, 0, abyte0.length);
				servletoutputstream.flush();
			} catch (IOException ioexception) {
				if (servletoutputstream != null)
					try {
						servletoutputstream.close();
					} catch (IOException ioexception1) {
					}
			}
		} else {
			httpservletresponse.setContentType("text/html");
			PrintWriter printwriter = httpservletresponse.getWriter();
			printwriter.println("<html>");
			printwriter.println("<body bgcolor=\"white\">");
			printwriter.println("<span class=\"bold\">Empty response.</span>");
			printwriter.println("</body>");
			printwriter.println("</html>");
		}
	}
}
