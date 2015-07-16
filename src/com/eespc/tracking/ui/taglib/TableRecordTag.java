// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TableRecordTag.java

package com.eespc.tracking.ui.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TableRecordTag extends BodyTagSupport {

	public TableRecordTag() {
	}

	public int doStartTag() throws JspException {
		return 1;
	}

	public int doEndTag() {
		return 6;
	}
}
