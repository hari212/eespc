package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class ContactTypeEnum implements Serializable, Comparable {

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final ContactTypeEnum PRIMARY = new ContactTypeEnum(1,
			"Primary", "Primary Contact");
	public static final ContactTypeEnum SECONDARY = new ContactTypeEnum(2,
			"Secondary", "Secondary Contact");
	public static final ContactTypeEnum TERTIARY = new ContactTypeEnum(3,
			"Tertiary", "Tertiary Contact");

	private ContactTypeEnum(int i, String s, String s1) {
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

	public static ContactTypeEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			ContactTypeEnum contacttypeenum = (ContactTypeEnum) list.get(k);
			if (contacttypeenum.getCode() == i) {
				return contacttypeenum;
			}
		}

		return null;
	}

	public static ContactTypeEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			ContactTypeEnum contacttypeenum = (ContactTypeEnum) list.get(j);
			if (contacttypeenum.getName().equals(s)) {
				return contacttypeenum;
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
			ContactTypeEnum contacttypeenum = (ContactTypeEnum) list.get(j);
			String s = "";
			String s1 = "";
			if (contacttypeenum != null) {
				String s2 = (new StringBuffer(String.valueOf(contacttypeenum
						.getCode()))).toString();
				String s3 = contacttypeenum.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof ContactTypeEnum) {
			ContactTypeEnum contacttypeenum = (ContactTypeEnum) obj;
			return getName().compareToIgnoreCase(contacttypeenum.getName());
		} else {
			return -1;
		}
	}

}
