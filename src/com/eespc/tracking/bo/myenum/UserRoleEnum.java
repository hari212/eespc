package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class UserRoleEnum implements Serializable {

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final UserRoleEnum CLIENT_USER = new UserRoleEnum(1,
			"Client User", "Client User");
	public static final UserRoleEnum EMPLOYEE = new UserRoleEnum(2, "Employee",
			"Employee");
	public static final UserRoleEnum SUPER_USER = new UserRoleEnum(3,
			"Super User", "Super User");

	private UserRoleEnum(int i, String s, String s1) {
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

	public static UserRoleEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			UserRoleEnum userroleenum = (UserRoleEnum) list.get(k);
			if (userroleenum.getCode() == i) {
				return userroleenum;
			}
		}

		return null;
	}

	public static UserRoleEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			UserRoleEnum userroleenum = (UserRoleEnum) list.get(j);
			if (userroleenum.getName().equals(s)) {
				return userroleenum;
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
			UserRoleEnum userroleenum = (UserRoleEnum) list.get(j);
			String s = "";
			String s2 = "";
			if (userroleenum != null) {
				String s1 = (new StringBuffer(String.valueOf(userroleenum
						.getCode()))).toString();
				String s3 = userroleenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

}
