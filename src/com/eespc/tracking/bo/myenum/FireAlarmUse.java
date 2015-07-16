// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FireAlarmUse.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class FireAlarmUse implements Serializable, Comparable {

	private FireAlarmUse(int i, String s, String s1) {
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

	public static FireAlarmUse get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			FireAlarmUse firealarmuse = (FireAlarmUse) list.get(k);
			if (firealarmuse.getCode() == i)
				return firealarmuse;
		}

		return null;
	}

	public static FireAlarmUse parse(String s) {
		if (s == null)
			return null;
		int i = size();
		for (int j = 0; j < i; j++) {
			FireAlarmUse firealarmuse = (FireAlarmUse) list.get(j);
			if (firealarmuse.getName().equals(s))
				return firealarmuse;
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
			FireAlarmUse firealarmuse = (FireAlarmUse) list.get(j);
			String s = "";
			String s1 = "";
			if (firealarmuse != null) {
				String s2 = (new StringBuffer(String.valueOf(firealarmuse
						.getCode()))).toString();
				String s3 = firealarmuse.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof FireAlarmUse) {
			FireAlarmUse firealarmuse = (FireAlarmUse) obj;
			return getName().compareToIgnoreCase(firealarmuse.getName());
		} else {
			return -1;
		}
	}

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final FireAlarmUse Residential = new FireAlarmUse(1,
			"Residential", "Residential");
	public static final FireAlarmUse Office = new FireAlarmUse(2, "Office",
			"Office");
	public static final FireAlarmUse Commercial = new FireAlarmUse(3,
			"Commercial", "Commercial");
	public static final FireAlarmUse Others = new FireAlarmUse(4, "Others",
			"Others");

}
