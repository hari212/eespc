// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableHeaderCellDefTag.java

package com.eespc.tracking.ui.taglib;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TableHeaderCellDef

public class TableHeaderCellDefTag extends TagSupport {

	public TableHeaderCellDefTag() {
	}

	public int doStartTag() throws JspException {
		Object obj = (Map) pageContext.getAttribute("headerCellDefs");
		if (obj == null) {
			obj = new HashMap();
			pageContext.setAttribute("headerCellDefs", obj);
		}
		TableHeaderCellDef tableheadercelldef = new TableHeaderCellDef();
		tableheadercelldef.setId(id);
		tableheadercelldef.setLabel(label);
		tableheadercelldef.setBodyCellId(bodyCellId);
		((Map) (obj)).put(id, tableheadercelldef);
		return 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String s) {
		id = s;
	}

	public String getBodyCellId() {
		return bodyCellId;
	}

	public void setBodyCellId(String s) {
		bodyCellId = s;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String s) {
		label = s;
	}

	private String id;
	private String bodyCellId;
	private String label;
}
