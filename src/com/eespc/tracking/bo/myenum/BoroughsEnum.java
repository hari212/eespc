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

public class BoroughsEnum implements Serializable, Comparable {

	private static Const2Names constants;
	private final int code;
	private final String name;
	private final String descriptor;

	private BoroughsEnum(Const2Name const2name) {
		code = const2name.getValue();
		name = const2name.getName();
		descriptor = const2name.getDescription();
	}

	private BoroughsEnum(int i, String s, String s1) {
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

	public static BoroughsEnum get(int i) {
		Const2Name const2name = constants.get(i);
		return const2name == null ? null : new BoroughsEnum(const2name);
	}

	public static BoroughsEnum parse(String s) {
		Const2Name const2name = constants.parse(s);
		return const2name == null ? null : new BoroughsEnum(const2name);
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
			BoroughsEnum boroughsenum = new BoroughsEnum(
					(Const2Name) list.get(j));
			String s = "";
			String s2 = "";
			if (boroughsenum != null) {
				String s1 = (new StringBuffer(String.valueOf(boroughsenum
						.getCode()))).toString();
				String s3 = boroughsenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof BoroughsEnum) {
			BoroughsEnum boroughsenum = (BoroughsEnum) obj;
			return getName().compareToIgnoreCase(boroughsenum.getName());
		} else {
			return -1;
		}
	}

	public static boolean isValidBoroughInNYC(int i) {
		boolean flag = false;
		BoroughsEnum boroughsenum = get(i);
		if (boroughsenum == null) {
			throw new IllegalArgumentException((new StringBuilder())
					.append("Unable to get the Enumeration for code ")
					.append(i).toString());
		}
		BoroughsEnum boroughsenum1 = get(1);
		if (boroughsenum.getCode() == boroughsenum1.getCode()) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	public static void main(String args[]) {
		System.out.println(isValidBoroughInNYC(2));
		System.out.println(isValidBoroughInNYC(1));
	}

	static {
		constants = Const2Names.getInstance(ConstantGroup.BOROUGHS);
	}
}
