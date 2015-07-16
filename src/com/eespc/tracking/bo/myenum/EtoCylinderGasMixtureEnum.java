package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class EtoCylinderGasMixtureEnum implements Serializable {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.myenum.EtoCylinderGasMixtureEnum.class);
	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final EtoCylinderGasMixtureEnum TEN_NINTY = new EtoCylinderGasMixtureEnum(
			1, "10/90", "10/90");
	public static final EtoCylinderGasMixtureEnum TWELVE_EIGHTYEIGHT = new EtoCylinderGasMixtureEnum(
			2, "12/88", "12/88");

	private EtoCylinderGasMixtureEnum(int i, String s, String s1) {
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

	public static EtoCylinderGasMixtureEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			EtoCylinderGasMixtureEnum etocylindergasmixtureenum = (EtoCylinderGasMixtureEnum) list
					.get(k);
			if (etocylindergasmixtureenum.getCode() == i) {
				return etocylindergasmixtureenum;
			}
		}

		return null;
	}

	public static EtoCylinderGasMixtureEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			EtoCylinderGasMixtureEnum etocylindergasmixtureenum = (EtoCylinderGasMixtureEnum) list
					.get(j);
			if (etocylindergasmixtureenum.getName().equals(s)) {
				return etocylindergasmixtureenum;
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
			EtoCylinderGasMixtureEnum etocylindergasmixtureenum = (EtoCylinderGasMixtureEnum) list
					.get(j);
			String s = "";
			String s2 = "";
			if (etocylindergasmixtureenum != null) {
				String s1 = (new StringBuffer(
						String.valueOf(etocylindergasmixtureenum.getCode())))
						.toString();
				String s3 = etocylindergasmixtureenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

}
