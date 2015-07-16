package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BurnerTypeEnum implements Serializable {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.myenum.BurnerTypeEnum.class);
	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final BurnerTypeEnum SINGLE_FUEL = new BurnerTypeEnum(1,
			"Single Fuel", "Single Fuel");
	public static final BurnerTypeEnum DUAL_FUEL = new BurnerTypeEnum(2,
			"Dual Fuel", "Dual Fuel");

	private BurnerTypeEnum(int i, String s, String s1) {
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

	public static BurnerTypeEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			BurnerTypeEnum burnertypeenum = (BurnerTypeEnum) list.get(k);
			if (burnertypeenum.getCode() == i) {
				return burnertypeenum;
			}
		}

		return null;
	}

	public static BurnerTypeEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			BurnerTypeEnum burnertypeenum = (BurnerTypeEnum) list.get(j);
			if (burnertypeenum.getName().equals(s)) {
				return burnertypeenum;
			}
		}

		return null;
	}

	public static int size() {
		return list.size();
	}

	public String toString() {
		return (new StringBuilder()).append(getCode()).append(" | ")
				.append(getName()).append(" | ").append(getDescriptor())
				.toString();
	}

}
