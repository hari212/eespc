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

public class PipingSecContainmentEnum implements Serializable, Comparable {

	private static Const2Names constants;
	private final int code;
	private final String name;
	private final String descriptor;

	private PipingSecContainmentEnum(Const2Name const2name) {
		code = const2name.getValue();
		name = const2name.getName();
		descriptor = const2name.getDescription();
	}

	private PipingSecContainmentEnum(int i, String s, String s1) {
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

	public static PipingSecContainmentEnum get(int i) {
		Const2Name const2name = constants.get(i);
		return const2name == null ? null : new PipingSecContainmentEnum(
				const2name);
	}

	public static PipingSecContainmentEnum parse(String s) {
		Const2Name const2name = constants.parse(s);
		return const2name == null ? null : new PipingSecContainmentEnum(
				const2name);
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
			PipingSecContainmentEnum pipingseccontainmentenum = new PipingSecContainmentEnum(
					(Const2Name) list.get(j));
			String s = "";
			String s1 = "";
			if (pipingseccontainmentenum != null) {
				String s2 = (new StringBuffer(
						String.valueOf(pipingseccontainmentenum.getCode())))
						.toString();
				String s3 = pipingseccontainmentenum.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof PipingSecContainmentEnum) {
			PipingSecContainmentEnum pipingseccontainmentenum = (PipingSecContainmentEnum) obj;
			return getName().compareToIgnoreCase(
					pipingseccontainmentenum.getName());
		} else {
			return -1;
		}
	}

	static {
		constants = Const2Names
				.getInstance(ConstantGroup.PIPE_SECONDARY_CONTAINMENT);
	}
}
