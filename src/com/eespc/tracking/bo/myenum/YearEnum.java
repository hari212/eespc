package com.eespc.tracking.bo.myenum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;

public class YearEnum {

	private static Collection years;

	public YearEnum() {
	}

	public static Collection getYears() {
		return years;
	}

	public static DropDown getDropDownObj() {
		return new DropDown(years);
	}

	static {
		years = new ArrayList();
		Calendar calendar = Calendar.getInstance();
		int i = calendar.get(1);
		int j = i - 5;
		int k = j + 10;
		years.add(NameValueBean.getPleaseSelect());
		for (int l = j; l < k; l++) {
			System.out.println(l);
			years.add(new NameValueBean(String.valueOf(l), String.valueOf(l)));
		}

	}
}
