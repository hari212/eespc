// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CofF.java

package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class CofF implements Serializable, Comparable {

	private CofF(int i, String s, String s1) {
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

	public static CofF get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			CofF coff = (CofF) list.get(k);
			if (coff.getCode() == i)
				return coff;
		}

		return null;
	}

	public static CofF parse(String s) {
		if (s == null)
			return a;
		int i = size();
		for (int j = 0; j < i; j++) {
			CofF coff = (CofF) list.get(j);
			if (coff.getName().equals(s))
				return coff;
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
			CofF coff = (CofF) list.get(j);
			String s = "";
			String s1 = "";
			if (coff != null) {
				String s2 = (new StringBuffer(String.valueOf(coff.getCode())))
						.toString();
				String s3 = coff.getName();
				arraylist.add(new NameValueBean(s3, s2));
			}
		}

		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	public int compareTo(Object obj) {
		if (obj instanceof CofF) {
			CofF coff = (CofF) obj;
			return getName().compareToIgnoreCase(coff.getName());
		} else {
			return -1;
		}
	}

	private final int code;
	private final String name;
	private final String descriptor;
	private static final List list = new ArrayList();
	public static final CofF a = new CofF(-1, "", "-");
	public static final CofF a1 = new CofF(1,
			"LNG(liquefied Natural Gas)  c10",
			"LNG(liquefied Natural Gas)  c10");
	public static final CofF a2 = new CofF(2,
			"Supervisor of Chemical Lab  c14",
			"Supervisor of Chemical Lab  c14");
	public static final CofF a3 = new CofF(3, "Spray Painting    c22",
			"Spray Painting    c22");
	public static final CofF a4 = new CofF(4, "Bulk acid storage   c24",
			"Bulk acid storage   c24");
	public static final CofF a5 = new CofF(5,
			"Hazardous material Storage  c91",
			"Hazardous material Storage  c91");
	public static final CofF a6 = new CofF(6, "Flammable Material   c12 ",
			"Flammable Material   c12");
	public static final CofF a7 = new CofF(7,
			"Fire Safety Coordinator    (F-24)",
			"Fire Safety Coordinator    (F-24)");
	public static final CofF a8 = new CofF(8,
			"Servicing Portable Fire Extinguishers  F-62/W-62)",
			"Servicing Portable Fire Extinguishers  F-62/W-62)");
	public static final CofF a9 = new CofF(9,
			"Maintenance of Smoke Detectors  F-57) ",
			"Maintenance of Smoke Detectors  F-57) ");
	public static final CofF a10 = new CofF(10,
			"Standpipe Systems With Pressure Tanks F-15",
			"Standpipe Systems With Pressure Tanks F-15");
	public static final CofF a11 = new CofF(11,
			"Sprinkler Systems With Pressure Tanks  F-23",
			"Sprinkler Systems With Pressure Tanks  F-23");
	public static final CofF a12 = new CofF(12,
			"Sprinkler Systems With Pressure Tanks Complex-wide F-48",
			"Sprinkler Systems With Pressure Tanks Complex-wide F-48");
	public static final CofF a13 = new CofF(13,
			"Storage and Use of Chlorine Liquefied Gas G-13    ",
			"Storage and Use of Chlorine Liquefied Gas G-13");
	public static final CofF a14 = new CofF(14,
			"Supervision of Medical Gases Bulk O2/NO2 G-17",
			"Supervision of Medical Gases Bulk O2/NO2 G-17");
	public static final CofF a15 = new CofF(15,
			"Compress Flammable Gases at a Pressure Greater than 15 PSI  G-29",
			"Compress Flammable Gases at a Pressure Greater than 15 PSI  G-29");
	public static final CofF a16 = new CofF(16,
			"Operate Air Compressors G-35 ", "Operate Air Compressors G-35 ");
	public static final CofF a17 = new CofF(17,
			"Roof-TopEthylene Oxide/Sterilizers-ETO G-55Roof-Top",
			"Ethylene Oxide/Sterilizers-ETO G-55");
	public static final CofF a18 = new CofF(18,
			"Supervise Fuel-Oil Piping and Storage in Buildings P98",
			"Supervise Fuel-Oil Piping and Storage in Buildings P98");
	public static final CofF a19 = new CofF(19,
			"Citywide Maintenance of Smoke Detectors W-26",
			"Citywide Maintenance of Smoke Detectors W-26");

}
