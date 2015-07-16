// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableHeaderColumnTag.java

package com.eespc.tracking.ui.taglib;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TableHeaderCellDef

public class TableHeaderColumnTag extends BodyTagSupport {

	public TableHeaderColumnTag() {
	}

	public int doStartTag() throws JspException {
		return 2;
	}

	public int doEndTag() {
		List headerColumns = (List) pageContext.getAttribute("headerColumns");
		if (headerColumns == null) {
			headerColumns = new ArrayList();
			pageContext.setAttribute("headerColumns", headerColumns);
		}
		String bc = bodyContent.getString();
		Map headerCellDefs = (Map) pageContext.getAttribute("headerCellDefs");
		for (Iterator i = headerCellDefs.keySet().iterator(); i.hasNext();) {
			String headerCellId = (String) i.next();
			String mark = "$$" + headerCellId + "$$";
			int ind = bc.indexOf(mark);
			if (ind >= 0) {
				TableHeaderCellDef headerCellDef = (TableHeaderCellDef) headerCellDefs
						.get(headerCellId);
				bc = bc.substring(0, ind) + getLink(headerCellDef)
						+ bc.substring(ind + mark.length());
			}
		}

		headerColumns.add(bc);
		return 6;
	}

	private String getLink(TableHeaderCellDef headerCellDef) {
		String link = "<A CLASS=\"header_link\" HREF=\"javascript:void(0)\"  onclick=\"sort('"
				+ headerCellDef.getBodyCellId()
				+ "'); return false;\">"
				+ headerCellDef.getLabel() + "</A>";
		return link;
	}
}
