// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ChillerLocation.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class ChillerLocation implements Serializable, Comparable {

	private ChillerLocation(int i, String s, String s1) {
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

	public static ChillerLocation get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			ChillerLocation chillerlocation = (ChillerLocation) list.get(k);
			if (chillerlocation.getCode() == i)
				return chillerlocation;
		}

		return null;
	}

	public static ChillerLocation parse(String s) {
		if (s == null)
			return null;
		int i = size();
		for (int j = 0; j < i; j++) {
			ChillerLocation chillerlocation = (ChillerLocation) list.get(j);
			if (chillerlocation.getName().equals(s))
				return chillerlocation;
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
			ChillerLocation chillerlocation = (ChillerLocation) list.get(j);
			String s = "";
			String s1 = "";
			if (chillerlocation != null) {
				String s2 = (new StringBuffer(String.valueOf(chillerlocation
						.getCode()))).toString();
				String s3 = chillerlocation.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof ChillerLocation) {
			ChillerLocation chillerlocation = (ChillerLocation) obj;
			return getName().compareToIgnoreCase(chillerlocation.getName());
		} else {
			return -1;
		}
	}

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final ChillerLocation Inside = new ChillerLocation(1,
			"Inside", "Inside");
	public static final ChillerLocation RoofTop = new ChillerLocation(2,
			"Roof-Top", "Roof-Top");

}
