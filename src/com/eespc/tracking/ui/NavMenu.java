// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NavMenu.java

package com.eespc.tracking.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.ui:
//            MenuItem, NavMenuService

public class NavMenu {

	public NavMenu(UserVo uservo, HttpServletRequest httpservletrequest) {
		menu = new ArrayList();
		userObj = null;
		applicationContextPath = null;
		session = null;
		NavMenuService navmenuservice = NavMenuService
				.getInstance(httpservletrequest);
		menu = navmenuservice.getMenuList();
		System.out.println((new StringBuilder()).append("menu=").append(menu)
				.toString());
		System.out.println((new StringBuilder()).append("userob=")
				.append(uservo).toString());
		System.out.println((new StringBuilder()).append("menu.size()=")
				.append(menu.size()).toString());
		if (menu == null || menu.size() == 0 || uservo == null) {
			throw new IllegalArgumentException(
					uservo != null ? "Menu list is not available."
							: "UserVo Object is not passed.");
		} else {
			userObj = uservo;
			applicationContextPath = httpservletrequest.getContextPath();
			session = httpservletrequest.getSession();
			return;
		}
	}

	public String getMenuItems(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int i = 0;
		ArrayList arraylist = new ArrayList();
		Object obj = session.getAttribute("FACILITY_OBJECT");
		if (obj != null)
			arraylist.add("FacilitySelected");
		for (Iterator iterator = menu.iterator(); iterator.hasNext();) {
			MenuItem menuitem = (MenuItem) iterator.next();
			menuitem.setContextPath(applicationContextPath);
			if (menuitem.isValidateByProfile()) {
				if (userObj.isAdminUser())
					stringbuffer.append(menuitem.toHtml(s, i, arraylist));
			} else {
				stringbuffer.append(menuitem.toHtml(s, i, arraylist));
			}
			i++;
		}

		return stringbuffer.toString();
	}

	public String getActionMapping(String s) {
		String s1 = null;
		if (!UtilityObject.isNotEmpty(s))
			return s1;
		Iterator iterator = menu.iterator();
		do {
			if (!iterator.hasNext())
				break;
			MenuItem menuitem = (MenuItem) iterator.next();
			if (!menuitem.getId().equals(s))
				continue;
			s1 = menuitem.getDefaultAction();
			break;
		} while (true);
		return s1;
	}

	private List menu;
	private UserVo userObj;
	private String applicationContextPath;
	private HttpSession session;
}
