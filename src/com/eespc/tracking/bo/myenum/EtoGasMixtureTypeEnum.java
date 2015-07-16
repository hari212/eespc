package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class EtoGasMixtureTypeEnum implements Serializable {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.myenum.EtoGasMixtureTypeEnum.class);
	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final EtoGasMixtureTypeEnum CANISTER = new EtoGasMixtureTypeEnum(
			1, "Canister", "Canister");
	public static final EtoGasMixtureTypeEnum CYLINDER = new EtoGasMixtureTypeEnum(
			2, "Cylinder", "Cylinder");

	private EtoGasMixtureTypeEnum(int i, String s, String s1) {
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

	public static EtoGasMixtureTypeEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			EtoGasMixtureTypeEnum etogasmixturetypeenum = (EtoGasMixtureTypeEnum) list
					.get(k);
			if (etogasmixturetypeenum.getCode() == i) {
				return etogasmixturetypeenum;
			}
		}

		return null;
	}

	public static EtoGasMixtureTypeEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			EtoGasMixtureTypeEnum etogasmixturetypeenum = (EtoGasMixtureTypeEnum) list
					.get(j);
			if (etogasmixturetypeenum.getName().equals(s)) {
				return etogasmixturetypeenum;
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

	public static DropDown getDropDownObj() {
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			EtoGasMixtureTypeEnum etogasmixturetypeenum = (EtoGasMixtureTypeEnum) list
					.get(j);
			String s = "";
			String s2 = "";
			if (etogasmixturetypeenum != null) {
				String s1 = (new StringBuffer(
						String.valueOf(etogasmixturetypeenum.getCode())))
						.toString();
				String s3 = etogasmixturetypeenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

}
