// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FireAlarmType.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class FireAlarmType implements Serializable, Comparable {

	private FireAlarmType(int i, String s, String s1) {
		code = i;
		name = s;
		descriptor = s1;
		list.add(this);
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public static FireAlarmType get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			FireAlarmType firealarmtype = (FireAlarmType) list.get(k);
			if (firealarmtype.getCode() == i)
				return firealarmtype;
		}

		return null;
	}

	public static FireAlarmType parse(String s) {
		if (s == null)
			return null;
		int i = size();
		for (int j = 0; j < i; j++) {
			FireAlarmType firealarmtype = (FireAlarmType) list.get(j);
			if (firealarmtype.getName().equals(s))
				return firealarmtype;
		}

		return null;
	}

	public static int size() {
		return list.size();
	}

	public String toString() {
		return getName();
	}

	public static DropDown getDropDownObj() {
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			FireAlarmType firealarmtype = (FireAlarmType) list.get(j);
			String s = "";
			String s1 = "";
			if (firealarmtype != null) {
				String s2 = (new StringBuffer(String.valueOf(firealarmtype
						.getCode()))).toString();
				String s3 = firealarmtype.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof FireAlarmType) {
			FireAlarmType firealarmtype = (FireAlarmType) obj;
			return getName().compareToIgnoreCase(firealarmtype.getName());
		} else {
			return -1;
		}
	}

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final FireAlarmType MiniClassE = new FireAlarmType(1,
			"Mini Class E", "Mini Class E");
	public static final FireAlarmType Modified = new FireAlarmType(2,
			"Modified", "Modified");
	public static final FireAlarmType Other = new FireAlarmType(3, "Other",
			"Other");

}
