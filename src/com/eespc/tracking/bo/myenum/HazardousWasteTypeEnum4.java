// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HazardousWasteTypeEnum4.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.eespc.tracking.bo.Const2Name;
import com.eespc.tracking.bo.Const2Names;
import com.eespc.tracking.bo.ConstantGroup;
import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class HazardousWasteTypeEnum4 implements Serializable, Comparable {

	private HazardousWasteTypeEnum4(Const2Name const2name) {
		code = const2name.getValue();
		name = const2name.getName();
		descriptor = const2name.getDescription();
	}

	private HazardousWasteTypeEnum4(int i, String s, String s1) {
		code = i;
		name = s;
		descriptor = s1;
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

	public static HazardousWasteTypeEnum4 get(int i) {
		Const2Name const2name = constants.get(i);
		return const2name != null ? new HazardousWasteTypeEnum4(const2name)
				: null;
	}

	public static HazardousWasteTypeEnum4 parse(String s) {
		Const2Name const2name = constants.parse(s);
		return const2name != null ? new HazardousWasteTypeEnum4(const2name)
				: null;
	}

	public static boolean contains(String s) {
		return constants.contains(s);
	}

	public static int size() {
		return constants.size();
	}

	public String toString() {
		return getName();
	}

	public static DropDown getDropDownObj() {
		List list = constants.getConstants();
		Collections.sort(list);
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			HazardousWasteTypeEnum4 hazardouswastetypeenum4 = new HazardousWasteTypeEnum4(
					(Const2Name) list.get(j));
			String s = "";
			String s1 = "";
			if (hazardouswastetypeenum4 != null) {
				String s2 = (new StringBuffer(
						String.valueOf(hazardouswastetypeenum4.getCode())))
						.toString();
				String s3 = hazardouswastetypeenum4.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof HazardousWasteTypeEnum4) {
			HazardousWasteTypeEnum4 hazardouswastetypeenum4 = (HazardousWasteTypeEnum4) obj;
			return getName().compareToIgnoreCase(
					hazardouswastetypeenum4.getName());
		} else {
			return -1;
		}
	}

	private static Const2Names constants;
	private final int code;
	private final String name;
	private final String descriptor;

	static {
		constants = Const2Names.getInstance(ConstantGroup.WASTE_TYPE4);
	}
}
