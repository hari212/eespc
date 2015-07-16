// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NameValueBean.java

package com.eespc.tracking.bo;

import java.io.Serializable;

public class NameValueBean implements Serializable {

	public NameValueBean(String s, String s1) {
		name = null;
		value = null;
		name = s;
		value = s1;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public void setName(String s) {
		name = s;
	}

	public void setValue(String s) {
		value = s;
	}

	public static NameValueBean getPleaseSelect() {
		return new NameValueBean("Please Select", "-1");
	}

	protected String name;
	protected String value;
}
