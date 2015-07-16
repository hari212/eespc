package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class DepartmentEnum implements Serializable {

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final DepartmentEnum STATE_AGENCY = new DepartmentEnum(1,
			"State Agency", "State Agency");
	public static final DepartmentEnum DOB = new DepartmentEnum(2, "DOB",
			"Department of Building");
	public static final DepartmentEnum NJDEP = new DepartmentEnum(3, "NJDEP",
			"NJDEP");
	public static final DepartmentEnum NYCDEP = new DepartmentEnum(4, "NYCDEP",
			"NYCDEP");
	public static final DepartmentEnum PADEP = new DepartmentEnum(5, "PADEP",
			"PADEP");
	public static final DepartmentEnum NCDENR = new DepartmentEnum(6, "NCDENR",
			"NCDENR");
	public static final DepartmentEnum SCDEH = new DepartmentEnum(7, "SCDEH",
			"SCDEH");
	public static final DepartmentEnum MDE = new DepartmentEnum(8, "MDE", "MDE");
	public static final DepartmentEnum DOT = new DepartmentEnum(9, "DOT", "DOT");

	private DepartmentEnum(int i, String s, String s1) {
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

	public static DepartmentEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			DepartmentEnum departmentenum = (DepartmentEnum) list.get(k);
			if (departmentenum.getCode() == i) {
				return departmentenum;
			}
		}

		return null;
	}

	public static DepartmentEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			DepartmentEnum departmentenum = (DepartmentEnum) list.get(j);
			if (departmentenum.getName().equals(s)) {
				return departmentenum;
			}
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
			DepartmentEnum departmentenum = (DepartmentEnum) list.get(j);
			String s = "";
			String s2 = "";
			if (departmentenum != null) {
				String s1 = (new StringBuffer(String.valueOf(departmentenum
						.getCode()))).toString();
				String s3 = departmentenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

}
