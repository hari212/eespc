package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class AgencyLocationEnum implements Serializable, Comparable {

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final AgencyLocationEnum NEW_YORK = new AgencyLocationEnum(1,
			"New York City", "New York City");
	public static final AgencyLocationEnum WCDOH = new AgencyLocationEnum(2,
			"WCDOH", "WCDOH");
	public static final AgencyLocationEnum RCDOH = new AgencyLocationEnum(3,
			"RCDOH", "RCDOH");
	public static final AgencyLocationEnum NJDEP = new AgencyLocationEnum(4,
			"NJDEP", "NJDEP");
	public static final AgencyLocationEnum OTHERS = new AgencyLocationEnum(5,
			"Others", "Others");

	private AgencyLocationEnum(int i, String s, String s1) {
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

	public static AgencyLocationEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			AgencyLocationEnum agencylocationenum = (AgencyLocationEnum) list
					.get(k);
			if (agencylocationenum.getCode() == i) {
				return agencylocationenum;
			}
		}

		return null;
	}

	public static AgencyLocationEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			AgencyLocationEnum agencylocationenum = (AgencyLocationEnum) list
					.get(j);
			if (agencylocationenum.getName().equals(s)) {
				return agencylocationenum;
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
			AgencyLocationEnum agencylocationenum = (AgencyLocationEnum) list
					.get(j);
			String s = "";
			String s1 = "";
			if (agencylocationenum != null) {
				String s2 = (new StringBuffer(String.valueOf(agencylocationenum
						.getCode()))).toString();
				String s3 = agencylocationenum.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof AgencyLocationEnum) {
			AgencyLocationEnum agencylocationenum = (AgencyLocationEnum) obj;
			return getName().compareToIgnoreCase(agencylocationenum.getName());
		} else {
			return -1;
		}
	}

}
