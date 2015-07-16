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

public class OtherTanksMaterialEnum implements Serializable, Comparable {

	private static Const2Names constants;
	private final int code;
	private final String name;
	private final String descriptor;

	private OtherTanksMaterialEnum(Const2Name const2name) {
		code = const2name.getValue();
		name = const2name.getName();
		descriptor = const2name.getDescription();
	}

	private OtherTanksMaterialEnum(int i, String s, String s1) {
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

	public static OtherTanksMaterialEnum get(int i) {
		Const2Name const2name = constants.get(i);
		return const2name != null ? new OtherTanksMaterialEnum(const2name)
				: null;
	}

	public static OtherTanksMaterialEnum parse(String s) {
		Const2Name const2name = constants.parse(s);
		return const2name != null ? new OtherTanksMaterialEnum(const2name)
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
			OtherTanksMaterialEnum othertanksmaterialenum = new OtherTanksMaterialEnum(
					(Const2Name) list.get(j));
			String s = "";
			String s1 = "";
			if (othertanksmaterialenum != null) {
				String s2 = (new StringBuffer(
						String.valueOf(othertanksmaterialenum.getCode())))
						.toString();
				String s3 = othertanksmaterialenum.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof OtherTanksMaterialEnum) {
			OtherTanksMaterialEnum othertanksmaterialenum = (OtherTanksMaterialEnum) obj;
			return getName().compareToIgnoreCase(
					othertanksmaterialenum.getName());
		} else {
			return -1;
		}
	}

	static {
		constants = Const2Names
				.getInstance(ConstantGroup.HYDRAULIC_TANK_MATERIAL);
	}
}
