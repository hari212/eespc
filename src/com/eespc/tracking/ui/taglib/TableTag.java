// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableTag.java

package com.eespc.tracking.ui.taglib;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TableBodyCellDef

public class TableTag extends BodyTagSupport {

	public TableTag() {
		log = LogFactory.getLog((com.eespc.tracking.ui.taglib.TableTag.class)
				.getName());
	}

	public int doStartTag() throws JspException {
		Collection ds = (Collection) pageContext.getSession().getAttribute(
				dataSource);
		if (null == ds || ds.isEmpty()) {
			displayNoDataMsg();
			return 0;
		} else {
			pageContext.setAttribute("dataSource", dataSource);
			pageContext.setAttribute("formName", formName);
			ResponseUtils
					.write(pageContext,
							"<TABLE BORDER=0 CELLPADDING=1 CELLSPACING=1 BGCOLOR=\"#006699\" WIDTH=100%>");
			ResponseUtils
					.write(pageContext,
							"<TR><TD><TABLE BORDER=0 CELLPADDING=1 CELLSPACING=1 BGCOLOR=\"#006699\" WIDTH=100%>");
			ResponseUtils.write(pageContext,
					"<INPUT TYPE=HIDDEN NAME='TABLETAG_SORTKEY'>");
			return 1;
		}
	}

	public int doEndTag() throws JspException {
		Collection ds = (Collection) pageContext.getSession().getAttribute(
				dataSource);
		if (null == ds || ds.isEmpty()) {
			return 0;
		} else {
			displayTable();
			return 6;
		}
	}

	public String getNoDataFoundMessage() {
		return noDataFoundMessage;
	}

	public void setNoDataFoundMessage(String noDataFoundMessage) {
		this.noDataFoundMessage = noDataFoundMessage;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	private void displayNoDataMsg() {
		try {
			pageContext.getOut().print(noDataFoundMessage);
		} catch (Exception ex) {
			log.error("displayNoDataMsg():", ex);
		}
	}

	private void displayTable() {
		try {
			displayHeader();
			displayBody();
			pageContext.getOut().println("</TABLE></TD></TR></TABLE>");
		} catch (Exception ex) {
			log.error("displayTable():", ex);
		}
	}

	private void displayHeader() {
		try {
			pageContext.getOut().println("<TR>");
			List headerColumns = (List) pageContext
					.getAttribute("headerColumns");
			for (int i = 0; i < headerColumns.size(); i++) {
				pageContext.getOut().print("<TH CLASS=\"header\">");
				pageContext.getOut().print(headerColumns.get(i));
				pageContext.getOut().println("</TH>");
			}

			pageContext.getOut().println("</TR>");
		} catch (Exception ex) {
			log.error("displayHeader():", ex);
		}
	}

	private void displayBody() {
		try {
			List data = buildData();
			List sortedData = sortData(data);
			for (int i = 0; i < sortedData.size(); i++) {
				if ((double) (i / 2) == (double) i / 2D)
					pageContext
							.getOut()
							.println(
									"<TR bgcolor='#FFFFFF' onMouseOver=\"this.bgColor='#CCCCFF';\" onMouseOut=\"this.bgColor='#FFFFFF';\">");
				else
					pageContext
							.getOut()
							.println(
									"<TR bgcolor='#EEEEEE' onMouseOver=\"this.bgColor='#CCCCFF';\" onMouseOut=\"this.bgColor='#EEEEEE';\">");
				pageContext.getOut().println(sortedData.get(i));
				pageContext.getOut().println("</TR>");
			}

		} catch (Exception ex) {
			log.error("displayBody():", ex);
		}
	}

	private List buildData() {
		List data = new ArrayList();
		String sortKey = pageContext.getRequest().getParameter(
				"TABLETAG_SORTKEY");
		if (null == sortKey)
			sortKey = "";
		Collection dataSource = (Collection) pageContext.getSession()
				.getAttribute((String) pageContext.getAttribute("dataSource"));
		String recordTemplate = ((StringBuffer) pageContext
				.getAttribute("recordTemplate")).toString();
		try {
			Iterator i = dataSource.iterator();
			Object vo = i.next();
			Map methods = reflectMethods(vo);
			for (i = dataSource.iterator(); i.hasNext(); data
					.add(((Object) (new Object[] {
							getData(vo, (Method) methods.get(sortKey)),
							translateRecord(recordTemplate, vo, methods) }))))
				vo = i.next();

		} catch (Exception ex) {
			log.error("buildData():", ex);
		}
		return data;
	}

	private String translateRecord(String recordTemplate, Object vo,
			Map reflectMethodMap) {
		String result = recordTemplate;
		Map bodyCellDefs = (Map) pageContext.getAttribute("bodyCellDefs");
		Iterator i = bodyCellDefs.keySet().iterator();
		do {
			if (!i.hasNext())
				break;
			String bodyCellId = (String) i.next();
			String mark = "$$" + bodyCellId + "$$";
			int ind = result.indexOf(mark);
			if (ind >= 0)
				result = result.substring(0, ind)
						+ getData(vo, (Method) reflectMethodMap.get(bodyCellId))
						+ result.substring(ind + mark.length());
		} while (true);
		return result;
	}

	private String getData(Object vo, Method getter) {
		String value = "";
		try {
			if (getter != null) {
				Object obj = getter.invoke(vo, null);
				if (obj != null)
					value = obj.toString();
			}
		} catch (Exception ex) {
			log.error("getData():", ex);
		}
		return value;
	}

	private Map reflectMethods(Object vo) {
		Map reflectMethodMap = new HashMap();
		Map bodyCellDefs = (Map) pageContext.getAttribute("bodyCellDefs");
		try {
			TableBodyCellDef bodyCellDef;
			Method getter;
			for (Iterator i = bodyCellDefs.values().iterator(); i.hasNext(); reflectMethodMap
					.put(bodyCellDef.getId(), getter)) {
				bodyCellDef = (TableBodyCellDef) i.next();
				getter = vo.getClass().getMethod(bodyCellDef.getMethod(),
						new Class[0]);
			}

		} catch (SecurityException ex) {
			log.error("reflectMethods():", ex);
		} catch (NoSuchMethodException ex) {
			log.error("reflectMethods():", ex);
		}
		return reflectMethodMap;
	}

	private List sortData(List data) {
		List sortedData = new ArrayList();
		String lastSortKey = (String) pageContext.getSession().getAttribute(
				"TABLETAG_SORTKEY");
		String lastSortOrder = (String) pageContext.getSession().getAttribute(
				"TABLETAG_SORTORDER");
		String currentSortKey = pageContext.getRequest().getParameter(
				"TABLETAG_SORTKEY");
		String currentSortOrder = null;
		if (null == currentSortKey || currentSortKey.equals("")) {
			for (int i = 0; i < data.size(); i++) {
				Object objPair[] = (Object[]) data.get(i);
				sortedData.add(objPair[1]);
			}

			System.out.println("No Sorting.");
		} else {
			if (null == lastSortOrder || !currentSortKey.equals(lastSortKey))
				currentSortOrder = "ascend";
			else if (lastSortOrder.equals("ascend"))
				currentSortOrder = "descend";
			else
				currentSortOrder = "ascend";
			pageContext.getSession().setAttribute("TABLETAG_SORTKEY",
					currentSortKey);
			pageContext.getSession().setAttribute("TABLETAG_SORTORDER",
					currentSortOrder);
			for (int i = 0; i < data.size() - 1; i++) {
				for (int j = i + 1; j < data.size(); j++) {
					Object oi[] = (Object[]) data.get(i);
					Object oj[] = (Object[]) data.get(j);
					String keyI = (String) oi[0];
					String keyJ = (String) oj[0];
					if (currentSortOrder.equals("ascend")
							&& keyI.compareTo(keyJ) > 0
							|| currentSortOrder.equals("descend")
							&& keyI.compareTo(keyJ) < 0) {
						Object o[] = oi;
						data.set(i, ((Object) (oj)));
						data.set(j, ((Object) (o)));
					}
				}

			}

			for (int i = 0; i < data.size(); i++) {
				Object objPair[] = (Object[]) data.get(i);
				sortedData.add(objPair[1]);
			}

		}
		return sortedData;
	}

	private String noDataFoundMessage;
	private String dataSource;
	private String formName;
	Log log;
}
