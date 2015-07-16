// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImageServlet.java

package com.eespc.tracking.reports.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eespc.tracking.reports.beans.ReportBean;

public class ImageServlet extends HttpServlet {

	public ImageServlet() {
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Map imagesMap = ((ReportBean) request.getSession().getAttribute(
				"reportBean")).getImagesMap();
		if (imagesMap != null) {
			String imageName = request.getParameter("image");
			if (imageName != null) {
				byte imageData[] = (byte[]) imagesMap.get(imageName);
				response.setContentLength(imageData.length);
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(imageData, 0, imageData.length);
				ouputStream.flush();
				ouputStream.close();
			}
		}
	}
}
