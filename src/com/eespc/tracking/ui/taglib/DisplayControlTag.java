// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DisplayControlTag.java

package com.eespc.tracking.ui.taglib;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TagUtil

public class DisplayControlTag extends BodyTagSupport {

	public DisplayControlTag() {
		paramName = null;
		formName = null;
	}

	public int doStartTag() throws JspException {
		ServletRequest request = pageContext.getRequest();
		boolean isViewOnly = TagUtil.isViewOnly(request);
		if (paramName == null || paramName.trim().length() == 0 || !isViewOnly)
			return 1;
		DynaActionForm formObj = (DynaActionForm) request
				.getAttribute(formName);
		String paramValue = (String) formObj.get(paramName);
		if (paramValue == null)
			paramValue = "";
		ResponseUtils.write(pageContext, paramValue);
		return 0;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	private String paramName;
	private String formName;
}
