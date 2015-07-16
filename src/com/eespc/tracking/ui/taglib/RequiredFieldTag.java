// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RequiredFieldTag.java

package com.eespc.tracking.ui.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TagUtil

public class RequiredFieldTag extends TagSupport {

	public RequiredFieldTag() {
	}

	public int doStartTag() throws JspException {
		javax.servlet.ServletRequest request = pageContext.getRequest();
		if (TagUtil.isViewOnly(request))
			ResponseUtils.write(pageContext, "");
		else
			ResponseUtils.write(pageContext,
					"<font size=\"3\" color=\"#FF0000\">*</font>");
		return 0;
	}

	public static final String REQUIRED_FIELD = "<font size=\"3\" color=\"#FF0000\">*</font>";
}
