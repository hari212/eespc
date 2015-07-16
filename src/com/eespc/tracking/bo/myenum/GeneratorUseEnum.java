package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class GeneratorUseEnum implements Serializable {

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final GeneratorUseEnum EMERGENCY = new GeneratorUseEnum(1,
			"Emergency", "Emergency");
	public static final GeneratorUseEnum PLM = new GeneratorUseEnum(2,
			"PLM TIER 2", "PLM TIER 2");
	public static final GeneratorUseEnum PLMTIER = new GeneratorUseEnum(3,
			"PLM TIER 4", "PLM TIER 4");

	private GeneratorUseEnum(int i, String s, String s1) {
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

	public static GeneratorUseEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			GeneratorUseEnum generatoruseenum = (GeneratorUseEnum) list.get(k);
			if (generatoruseenum.getCode() == i) {
				return generatoruseenum;
			}
		}

		return null;
	}

	public static GeneratorUseEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			GeneratorUseEnum generatoruseenum = (GeneratorUseEnum) list.get(j);
			if (generatoruseenum.getName().equals(s)) {
				return generatoruseenum;
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
			GeneratorUseEnum generatoruseenum = (GeneratorUseEnum) list.get(j);
			String s = "";
			String s2 = "";
			if (generatoruseenum != null) {
				String s1 = (new StringBuffer(String.valueOf(generatoruseenum
						.getCode()))).toString();
				String s3 = generatoruseenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

}
