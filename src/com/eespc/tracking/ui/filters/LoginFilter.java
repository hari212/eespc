// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoginFilter.java

package com.eespc.tracking.ui.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public LoginFilter() {
		filterConfig = null;
	}

	public void init(FilterConfig filterconfig) throws ServletException {
		filterConfig = filterconfig;
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		System.out.println("Filter file");
		HttpServletRequest httpservletrequest = (HttpServletRequest) servletrequest;
		HttpServletResponse httpservletresponse = (HttpServletResponse) servletresponse;
		System.out.println(httpservletresponse);
		javax.servlet.ServletContext servletcontext = filterConfig
				.getServletContext();
		System.out.println(httpservletrequest.getServletPath());
		if ("/loginFail.do".equals(httpservletrequest.getServletPath())
				|| "/login.do".equals(httpservletrequest.getServletPath())) {
			System.out.println("chain above");
			filterchain.doFilter(httpservletrequest, httpservletresponse);
			System.out.println("Inside");
			return;
		}
		System.out.println("After");
		HttpSession httpsession = httpservletrequest.getSession();
		System.out.println("session" + httpsession);
		String s = null;
		System.out.println(httpsession.isNew() + " "
				+ httpsession.getLastAccessedTime() + " "
				+ httpsession.getMaxInactiveInterval());
		if (httpsession.isNew()) {
			System.out.println("Session is New ");
			httpsession = httpservletrequest.getSession(true);
			s = getUserId(httpservletrequest);
			System.out.println("userId=|" + s + "|");
			if (s == null)
				login(httpservletrequest, httpservletresponse);
			return;
		}
		System.out.println("UserId=" + s);
		if (httpsession.getAttribute("SESSION_USER") == null) {
			login(httpservletrequest, httpservletresponse);
			return;
		} else {
			filterchain.doFilter(httpservletrequest, httpservletresponse);
			return;
		}
	}

	public void destroy() {
	}

	private String getUserId(HttpServletRequest httpservletrequest) {
		String s = null;
		Cookie acookie[] = httpservletrequest.getCookies();
		if (acookie == null) {
			System.out.println("***** COOKIES IS NULL *******");
			return null;
		}
		int i = acookie.length;
		for (int j = 0; j < i; j++) {
			if ("EESPC_USERID".equals(acookie[j].getName()))
				s = acookie[j].getValue();
			System.out.println(acookie[j].getName() + "="
					+ acookie[j].getValue());
		}

		System.out
				.println("Constants.COOKIE_NAMEEESPC_USERID getUserId()=" + s);
		return s;
	}

	private void login(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) {
		try {
			httpservletresponse.sendRedirect(httpservletrequest
					.getContextPath() + "/login.do" + "?methodToCall=initial");
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	private void deleteCookie(HttpServletResponse httpservletresponse) {
		Cookie cookie = new Cookie("EESPC_USERID", null);
		cookie.setMaxAge(0);
		httpservletresponse.addCookie(cookie);
	}

	private FilterConfig filterConfig;
	private static final String LOGIN_FAIL_URL = "/loginFail.do";
	public static final String LOGIN_URL = "/login.do";
}
