// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NavMenuService.java

package com.eespc.tracking.ui;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.eespc.tracking.util.XmlHelper;

// Referenced classes of package com.eespc.tracking.ui:
//            MenuItem

public class NavMenuService {

	private NavMenuService(ServletContext servletcontext) {
		menuList = null;
		menuList = new ArrayList();
		servletContext = servletcontext;
	}

	public static NavMenuService getInstance(
			HttpServletRequest httpservletrequest) {
		if (soleInstance == null)
			synchronized ("com.eespc.tracking.ui.NavMenuService") {
				if (soleInstance == null) {
					ServletContext servletcontext = httpservletrequest
							.getSession().getServletContext();
					if (servletcontext == null)
						throw new IllegalArgumentException(
								"Servlet Context is null");
					soleInstance = new NavMenuService(servletcontext);
					soleInstance.initialize();
				}
			}
		return soleInstance;
	}

	private void initialize() {
		try {
			java.io.InputStream inputstream = servletContext
					.getResourceAsStream("/WEB-INF/config/menu.xml");
			org.w3c.dom.Document document = XmlHelper.parse(new InputSource(
					inputstream));
			NodeList nodelist = XmlHelper.getNodeList(document, "//MenuItem");
			System.out.println((new StringBuilder()).append("Check1")
					.append(nodelist).toString());
			for (int i = 0; i < nodelist.getLength(); i++) {
				MenuItem menuitem = new MenuItem();
				menuitem.parse(nodelist.item(i));
				log.info((new StringBuilder()).append("Loading menu item: ")
						.append(menuitem).toString());
				menuList.add(menuitem);
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Check2")
					.append(exception).toString());
			exception.printStackTrace();
			log.error((new StringBuilder()).append("NavMenu.init(): ")
					.append(exception.getMessage()).toString());
		}
	}

	public List getMenuList() {
		return menuList;
	}

	List menuList;
	private static NavMenuService soleInstance = null;
	private static final String DATASOURCE_FILE = "/WEB-INF/config/menu.xml";
	private static Logger log = Logger
			.getLogger("com.eespc.tracking.ui.NavMenuService");
	private static ServletContext servletContext = null;

}
