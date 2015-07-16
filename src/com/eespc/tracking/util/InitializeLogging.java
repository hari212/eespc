// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   InitializeLogging.java

package com.eespc.tracking.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

public class InitializeLogging extends HttpServlet {

	public InitializeLogging() {
	}

	protected void doGet(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		super.doGet(arg0, arg1);
	}

	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		super.doPost(arg0, arg1);
	}

	public void init() throws ServletException {
		String prefix = getServletContext().getRealPath("/");
		String fileName = getInitParameter("log4j-config-file");
		if (fileName != null)
			PropertyConfigurator.configure(prefix + fileName);
	}
}
