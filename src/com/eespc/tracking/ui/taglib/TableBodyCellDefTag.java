// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableBodyCellDefTag.java

package com.eespc.tracking.ui.taglib;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TableBodyCellDef

public class TableBodyCellDefTag extends TagSupport {

	public TableBodyCellDefTag() {
	}

	public int doStartTag() throws JspException {
		Object obj = (Map) pageContext.getAttribute("bodyCellDefs");
		if (obj == null) {
			obj = new HashMap();
			pageContext.setAttribute("bodyCellDefs", obj);
		}
		TableBodyCellDef tablebodycelldef = new TableBodyCellDef();
		tablebodycelldef.setId(id);
		tablebodycelldef.setMethod(method);
		((Map) (obj)).put(id, tablebodycelldef);
		return 0;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String s) {
		method = s;
	}

	public String getId() {
		return id;
	}

	public void setId(String s) {
		id = s;
	}

	private String id;
	private String method;
}
