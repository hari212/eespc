// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   getpath.java

package sevenb;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class getpath extends HttpServlet {

	public getpath() {
		path = "";
	}

	public void service(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		ServletContext servletcontext = getServletConfig().getServletContext();
		path = servletcontext.getRealPath("/jasperRep/exhi8.jrxml");
	}

	public String path() {
		return path;
	}

	String path;
}
