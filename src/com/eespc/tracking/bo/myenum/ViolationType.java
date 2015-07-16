// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ViolationType.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class ViolationType implements Serializable, Comparable {

	private ViolationType(int i, String s, String s1) {
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

	public static ViolationType get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			ViolationType violationtype = (ViolationType) list.get(k);
			if (violationtype.getCode() == i)
				return violationtype;
		}

		return null;
	}

	public static ViolationType parse(String s) {
		if (s == null)
			return null;
		int i = size();
		for (int j = 0; j < i; j++) {
			ViolationType violationtype = (ViolationType) list.get(j);
			if (violationtype.getName().equals(s))
				return violationtype;
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
			ViolationType violationtype = (ViolationType) list.get(j);
			String s = "";
			String s1 = "";
			if (violationtype != null) {
				String s2 = (new StringBuffer(String.valueOf(violationtype
						.getCode()))).toString();
				String s3 = violationtype.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof ViolationType) {
			ViolationType violationtype = (ViolationType) obj;
			return getName().compareToIgnoreCase(violationtype.getName());
		} else {
			return -1;
		}
	}

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final ViolationType dob = new ViolationType(1, "DOB", "DOB");
	public static final ViolationType dep = new ViolationType(2, "DEP", "DEP");
	public static final ViolationType ecb = new ViolationType(3, "ECB", "ECB");
	public static final ViolationType dec = new ViolationType(4, "DEC", "DEC");
	public static final ViolationType fd = new ViolationType(5, "FD", "FD");
	public static final ViolationType epa = new ViolationType(6, "EPA", "EPA");
	public static final ViolationType state = new ViolationType(7,
			"STATE/CITY", "STATE/CITY");
	public static final ViolationType other = new ViolationType(8, "OTHERS",
			"OTHERS");

}
