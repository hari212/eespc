// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SummaryLinkTag.java

package com.eespc.tracking.ui.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts.util.ResponseUtils;

// Referenced classes of package com.eespc.tracking.ui.taglib:
//            TagUtil

public class SummaryLinkTag extends TagSupport {

	public SummaryLinkTag() {
	}

	public int doStartTag() throws JspException {
		HttpServletRequest request = (HttpServletRequest) pageContext
				.getRequest();
		key = TagUtil.eval(request, key).toString();
		label = (String) TagUtil.eval(request, label);
		StringBuffer link = new StringBuffer();
		try {
			if (Integer.parseInt(key) <= 0)
				link.append(label);
		} catch (Exception exception) {
		}
		if (link.length() == 0) {
			link.append("<A HREF='#' onClick=\"return ").append(script)
					.append("('").append(key).append("');\">");
			link.append(label).append("</A>");
		}
		ResponseUtils.write(pageContext, link.toString());
		return 0;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	private String label;
	private String key;
	private String script;
}
