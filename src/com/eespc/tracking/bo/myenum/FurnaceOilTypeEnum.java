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

public class FurnaceOilTypeEnum implements Serializable, Comparable {

	private static Const2Names constants;
	private final int code;
	private final String name;
	private final String descriptor;

	private FurnaceOilTypeEnum(Const2Name const2name) {
		code = const2name.getValue();
		name = const2name.getName();
		descriptor = const2name.getDescription();
	}

	private FurnaceOilTypeEnum(int i, String s, String s1) {
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

	public static FurnaceOilTypeEnum get(int i) {
		Const2Name const2name = constants.get(i);
		return const2name != null ? new FurnaceOilTypeEnum(const2name) : null;
	}

	public static FurnaceOilTypeEnum parse(String s) {
		Const2Name const2name = constants.parse(s);
		return const2name != null ? new FurnaceOilTypeEnum(const2name) : null;
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

	public static DropDown getDropDownObj(boolean flag) {
		String s = "Natural Gas";
		List list = constants.getConstants();
		Collections.sort(list);
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			FurnaceOilTypeEnum furnaceoiltypeenum = new FurnaceOilTypeEnum(
					(Const2Name) list.get(j));
			String s1 = "";
			String s2 = "";
			if (furnaceoiltypeenum == null) {
				continue;
			}
			s1 = (new StringBuffer(String.valueOf(furnaceoiltypeenum.getCode())))
					.toString();
			s2 = furnaceoiltypeenum.getName();
			if (!flag || s2 == null || !s.equalsIgnoreCase(s2)) {
				arraylist.add(new NameValueBean(s2, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public static DropDown getDropDownObjOther() {
		List list = constants.getConstants();
		Collections.sort(list);
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			FurnaceOilTypeEnum furnaceoiltypeenum = new FurnaceOilTypeEnum(
					(Const2Name) list.get(j));
			String s = "";
			String s1 = "";
			if (furnaceoiltypeenum == null) {
				continue;
			}
			s = (new StringBuffer(String.valueOf(furnaceoiltypeenum.getCode())))
					.toString();
			s1 = furnaceoiltypeenum.getName();
			if (furnaceoiltypeenum.getCode() == 1
					|| furnaceoiltypeenum.getCode() == 5) {
				arraylist.add(new NameValueBean(s1, s));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof FurnaceOilTypeEnum) {
			FurnaceOilTypeEnum furnaceoiltypeenum = (FurnaceOilTypeEnum) obj;
			return getName().compareToIgnoreCase(furnaceoiltypeenum.getName());
		} else {
			return -1;
		}
	}

	static {
		constants = Const2Names.getInstance(ConstantGroup.FUNACE_OIL_TYPE);
	}
}
