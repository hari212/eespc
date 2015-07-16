// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MenuItem.java

package com.eespc.tracking.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.eespc.tracking.util.XmlHelper;
import com.eespc.tracking.util.XmlHelperException;

public class MenuItem {

	public MenuItem() {
		actionTarget = "_self";
		isValidateByProfile = false;
		isValidateByFunction = false;
		activity = null;
		contextPath = null;
		enableEventList = new HashMap();
	}

	public void setId(String s) {
		id = s;
	}

	public String getId() {
		return id;
	}

	public void setName(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public void setLevel(int i) {
		level = i;
	}

	public int getLevel() {
		return level;
	}

	public void setDefaultAction(String s) {
		defaultAction = s;
	}

	public String getDefaultAction() {
		return defaultAction;
	}

	public boolean isValidateByProfile() {
		return isValidateByProfile;
	}

	public void setValidateByProfile(boolean flag) {
		isValidateByProfile = flag;
	}

	public boolean isValidateByFunction() {
		return isValidateByFunction;
	}

	public void setValidateByFunction(boolean flag) {
		isValidateByFunction = flag;
	}

	public void setActionTarget(String s) {
		actionTarget = s;
	}

	public String getActionTarget() {
		return actionTarget;
	}

	public void setActivity(String s) {
		activity = s;
	}

	public String getActivity() {
		return activity;
	}

	public void setContextPath(String s) {
		contextPath = s;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void addEnableEvent(String s) {
		enableEventList.put(s, s);
	}

	public boolean isEnabled(List list) {
		System.out.println((new StringBuilder())
				.append(">>>>>>>>>>>>>eventList.size()=").append(list.size())
				.append(", enableEventList.size()=")
				.append(enableEventList.size()).append(", string=")
				.append(list.size() > 0 ? list.get(0) : "NULL").toString());
		if (enableEventList.isEmpty())
			return true;
		for (Iterator iterator = enableEventList.keySet().iterator(); iterator
				.hasNext(); System.out.println("Doesn't exist"))
			if (list.contains(iterator.next()))
				return true;

		return false;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer("<<MenuItem: ");
		stringbuffer.append(id).append("|");
		stringbuffer.append(name).append("|");
		stringbuffer.append(level).append("|");
		stringbuffer.append(defaultAction).append("|");
		stringbuffer.append(actionTarget).append("|");
		stringbuffer.append(isValidateByProfile).append("|");
		stringbuffer.append(isValidateByFunction).append("|");
		stringbuffer.append(activity).append("|");
		stringbuffer.append(">>");
		return stringbuffer.toString();
	}

	public String toHtml(String s, int i, List list) {
		System.out.println((new StringBuilder()).append("Selectedid:")
				.append(s).append("@pos").append(i).append("@enableEventList")
				.append(list).toString());
		String s1 = "<a CLASS=LN1 onMouseOver=\"'this.style.cursor=hand';\" href=\"javascript:winOpen('";
		String s2 = "')";
		StringBuffer stringbuffer = new StringBuffer();
		String s3 = (new StringBuilder()).append(getContextPath())
				.append("/images/").toString();
		if (name.equals("Facility"))
			stringbuffer
					.append("<li style=\"border-left: 1px solid #202020;\" ><A CLASS=LN1 ")
					.append("\">").append(name).append("</A><ul>");
		else if (name.equals("Reports") || name.equals("Accounts"))
			stringbuffer.append("</ul></li><li><A CLASS=LN1 ")
					.append("\">").append(name).append("</A><ul>");
		else if (name.equals("Administrative Function"))
			stringbuffer.append("</ul></li><li><A CLASS=LN1 ")
					.append("\">").append(name).append("</A><ul>");
		else if (name.equals("Manage User"))
			stringbuffer.append("<li><A CLASS=LN1 HREF=")
					.append(getContextPath())
					.append("/ControllerAction.do?id=").append(id)
					.append(" TARGET=\"").append(actionTarget).append("\">")
					.append(name).append("</A></li></ul</li>");
		else
			stringbuffer.append("<li><A CLASS=LN1 HREF=")
					.append(getContextPath())
					.append("/ControllerAction.do?id=").append(id)
					.append(" TARGET=\"").append(actionTarget).append("\">")
					.append(name).append("</A></li>");
		return stringbuffer.toString();
	}

	public void parse(Node node) {
		if (node == null)
			return;
		try {
			setId(XmlHelper.getStringNodeValue(node, "./Id", null));
			setName(XmlHelper.getStringNodeValue(node, "./Name", null));
			setLevel(XmlHelper.getIntNodeValue(node, "./Level", 0));
			setDefaultAction(XmlHelper.getStringNodeValue(node,
					"./DefaultAction", null));
			setActionTarget(XmlHelper.getStringNodeValue(node,
					"./ActionTarget", "_self"));
			setValidateByFunction(XmlHelper.getBooleanNodeValue(node,
					"./ValidateByFunction", false));
			setValidateByProfile(XmlHelper.getBooleanNodeValue(node,
					"./ValidateByProfile", false));
			setActivity(XmlHelper.getStringNodeValue(node, "./Activity", null));
			NodeList nodelist = XmlHelper.getNodeList(node,
					"./EnableEvents/Event");
			for (int i = 0; i < nodelist.getLength(); i++) {
				String s = XmlHelper.getNodeValue(nodelist.item(i), null);
				if (s != null)
					addEnableEvent(s);
			}

		} catch (XmlHelperException xmlhelperexception) {
		}
	}

	private static final String DELIMITER = "|";
	private static final String TAG_MENU_ITEM = "./MenuItem";
	private static final String TAG_ID = "./Id";
	private static final String TAG_LEVEL = "./Level";
	private static final String TAG_NAME = "./Name";
	private static final String TAG_DEFAULT_ACTION = "./DefaultAction";
	private static final String TAG_ACTION_TARGET = "./ActionTarget";
	private static final String TAG_PROFILE_VALIDATION = "./ValidateByProfile";
	private static final String TAG_FUNCTION_VALIDATION = "./ValidateByFunction";
	private static final String TAG_ACTIVITY = "./Activity";
	private static final String URI = "/ControllerAction.do?id=";
	private static final String TAG_ENABLE_EVENT = "./EnableEvents/Event";
	private String id;
	private String name;
	private int level;
	private String defaultAction;
	private String actionTarget;
	private boolean isValidateByProfile;
	private boolean isValidateByFunction;
	private String activity;
	private String contextPath;
	private HashMap enableEventList;
}
