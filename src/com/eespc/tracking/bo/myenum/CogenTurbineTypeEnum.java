package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class CogenTurbineTypeEnum implements Serializable {

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.myenum.CogenTurbineTypeEnum.class);
	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final CogenTurbineTypeEnum COGEN = new CogenTurbineTypeEnum(
			1, "Co Gen Engines", "Co Gen Engines");
	public static final CogenTurbineTypeEnum TURBINES = new CogenTurbineTypeEnum(
			2, "Turbines", "Turbines");

	private CogenTurbineTypeEnum(int i, String s, String s1) {
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

	public static CogenTurbineTypeEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			CogenTurbineTypeEnum cogenturbinetypeenum = (CogenTurbineTypeEnum) list
					.get(k);
			if (cogenturbinetypeenum.getCode() == i) {
				return cogenturbinetypeenum;
			}
		}

		return null;
	}

	public static CogenTurbineTypeEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			CogenTurbineTypeEnum cogenturbinetypeenum = (CogenTurbineTypeEnum) list
					.get(j);
			if (cogenturbinetypeenum.getName().equals(s)) {
				return cogenturbinetypeenum;
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

	public static DropDown getDropDownObj(HttpServletRequest httpservletrequest) {
		int i = list.size();
		DropDown dropdown = null;
		ArrayList arraylist = new ArrayList();
		arraylist.add(NameValueBean.getPleaseSelect());
		for (int j = 0; j < i; j++) {
			CogenTurbineTypeEnum cogenturbinetypeenum = (CogenTurbineTypeEnum) list
					.get(j);
			String s = "";
			String s2 = "";
			if (cogenturbinetypeenum != null) {
				String s1 = (new StringBuffer(
						String.valueOf(cogenturbinetypeenum.getCode())))
						.toString();
				String s3 = cogenturbinetypeenum.getName();
				arraylist.add(new NameValueBean(s3, s1));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

}
