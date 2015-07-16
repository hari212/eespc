// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableBodyColumnTag.java

package com.eespc.tracking.ui.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TableBodyColumnTag extends BodyTagSupport {

	public TableBodyColumnTag() {
	}

	public int doStartTag() throws JspException {
		return 2;
	}

	public int doEndTag() {
		StringBuffer recordTemplate = (StringBuffer) pageContext
				.getAttribute("recordTemplate");
		if (recordTemplate == null)
			recordTemplate = new StringBuffer();
		String bc = bodyContent.getString();
		recordTemplate.append("<TD CLASS=data1_nocolor>").append(bc)
				.append("</TD>");
		pageContext.setAttribute("recordTemplate", recordTemplate);
		return 6;
	}
}
