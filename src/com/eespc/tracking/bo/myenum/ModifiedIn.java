// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ModifiedIn.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class ModifiedIn implements Serializable, Comparable {

	private ModifiedIn(int i, String s, String s1) {
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

	public static ModifiedIn get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			ModifiedIn modifiedin = (ModifiedIn) list.get(k);
			if (modifiedin.getCode() == i)
				return modifiedin;
		}

		return null;
	}

	public static ModifiedIn parse(String s) {
		if (s == null)
			return null;
		int i = size();
		for (int j = 0; j < i; j++) {
			ModifiedIn modifiedin = (ModifiedIn) list.get(j);
			if (modifiedin.getName().equals(s))
				return modifiedin;
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
			ModifiedIn modifiedin = (ModifiedIn) list.get(j);
			String s = "";
			String s1 = "";
			if (modifiedin != null) {
				String s2 = (new StringBuffer(String.valueOf(modifiedin
						.getCode()))).toString();
				String s3 = modifiedin.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof ModifiedIn) {
			ModifiedIn modifiedin = (ModifiedIn) obj;
			return getName().compareToIgnoreCase(modifiedin.getName());
		} else {
			return -1;
		}
	}

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final ModifiedIn ClosedInplace = new ModifiedIn(4,
			"Closed-In place", "Closed-In place");
	public static final ModifiedIn ClosedRemoved = new ModifiedIn(3,
			"Closed-Removed", "Closed-Removed");
	public static final ModifiedIn Inservice = new ModifiedIn(1, "In service",
			"In service");
	public static final ModifiedIn ConvertedtoNonRegulatedUse = new ModifiedIn(
			5, "Converted to Non-Regulated Use",
			"Converted to Non-Regulated Use");
	public static final ModifiedIn TemporarilyOutofService = new ModifiedIn(2,
			"Temporarily Out-of Service", "Temporarily Out-of Service");

}
