package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.eespc.tracking.bo.myenum.YearEnum;
import com.eespc.tracking.util.UtilityObject;

public class FuelConsumptionVo implements Serializable {

	public FuelConsumptionVo() {
		id = -99;
		entityId = -99;
		year = null;
		isLocked = false;
		yearToDate = -99f;
		rollingConsumption = -99f;
		isCompliant = false;
		jan = -99f;
		feb = -99f;
		mar = -99f;
		apr = -99f;
		may = -99f;
		jun = -99f;
		jul = -99f;
		aug = -99f;
		sep = -99f;
		oct = -99f;
		nov = -99f;
		dec = -99f;
		index = 0;
		bctype = null;
		consumptionList = null;
	}

	public FuelConsumptionVo(ResultSet resultset) throws SQLException {
		id = -99;
		entityId = -99;
		year = null;
		isLocked = false;
		yearToDate = -99f;
		rollingConsumption = -99f;
		isCompliant = false;
		jan = -99f;
		feb = -99f;
		mar = -99f;
		apr = -99f;
		may = -99f;
		jun = -99f;
		jul = -99f;
		aug = -99f;
		sep = -99f;
		oct = -99f;
		nov = -99f;
		dec = -99f;
		index = 0;
		bctype = null;
		consumptionList = null;

		id = resultset.getInt("ID");
		entityId = resultset.getInt("ENTITY_ID");
		year = resultset.getString("YEAR");
		isLocked = UtilityObject.convertBoolean(resultset.getString("LOCK"));
		yearToDate = resultset.getFloat("YEARTODATE");
		rollingConsumption = resultset.getFloat("ROLLINGCONSUMPTION");
		isCompliant = UtilityObject.convertBoolean(resultset
				.getString("COMPLIANT"));
		jan = UtilityObject.isNotEmpty(resultset.getString("JAN")) ? resultset
				.getFloat("JAN") : -99f;
		feb = UtilityObject.isNotEmpty(resultset.getString("FEB")) ? resultset
				.getFloat("FEB") : -99f;
		mar = UtilityObject.isNotEmpty(resultset.getString("MAR")) ? resultset
				.getFloat("MAR") : -99f;
		apr = UtilityObject.isNotEmpty(resultset.getString("APR")) ? resultset
				.getFloat("APR") : -99f;
		may = UtilityObject.isNotEmpty(resultset.getString("MAY")) ? resultset
				.getFloat("MAY") : -99f;
		jun = UtilityObject.isNotEmpty(resultset.getString("JUN")) ? resultset
				.getFloat("JUN") : -99f;
		jul = UtilityObject.isNotEmpty(resultset.getString("JUL")) ? resultset
				.getFloat("JUL") : -99f;
		aug = UtilityObject.isNotEmpty(resultset.getString("AUG")) ? resultset
				.getFloat("AUG") : -99f;
		sep = UtilityObject.isNotEmpty(resultset.getString("SEP")) ? resultset
				.getFloat("SEP") : -99f;
		oct = UtilityObject.isNotEmpty(resultset.getString("OCT")) ? resultset
				.getFloat("OCT") : -99f;
		nov = UtilityObject.isNotEmpty(resultset.getString("NOV")) ? resultset
				.getFloat("NOV") : -99f;
		dec = UtilityObject.isNotEmpty(resultset.getString("DEC")) ? resultset
				.getFloat("DEC") : -99f;
		index = UtilityObject.isNotEmpty(resultset.getString("index")) ? resultset
				.getInt("index") : 0;
		bctype = resultset.getString("bctype");
	}

	public String getBctype() {
		return bctype;
	}

	public void setBctype(String l) {
		bctype = l;
	}

	public List getConsumptionList() {
		consumptionList = new ArrayList();
		consumptionList.add(convertToObject(getJan()));
		consumptionList.add(convertToObject(getFeb()));
		consumptionList.add(convertToObject(getMar()));
		consumptionList.add(convertToObject(getApr()));
		consumptionList.add(convertToObject(getMay()));
		consumptionList.add(convertToObject(getJun()));
		consumptionList.add(convertToObject(getJul()));
		consumptionList.add(convertToObject(getAug()));
		consumptionList.add(convertToObject(getSep()));
		consumptionList.add(convertToObject(getOct()));
		consumptionList.add(convertToObject(getNov()));
		consumptionList.add(convertToObject(getDec()));
		return consumptionList;
	}

	public void setConsumptionList(List list) {
		consumptionList = list;
	}

	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int i) {
		entityId = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isCompliant() {
		return isCompliant;
	}

	public void setCompliant(boolean flag) {
		isCompliant = flag;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean flag) {
		isLocked = flag;
	}

	public float getJan() {
		return jan;
	}

	public void setJan(float l) {
		jan = l;
	}

	public float getFeb() {
		return feb;
	}

	public void setFeb(float l) {
		feb = l;
	}

	public float getMar() {
		return mar;
	}

	public void setMar(float l) {
		mar = l;
	}

	public float getApr() {
		return apr;
	}

	public void setApr(float l) {
		apr = l;
	}

	public float getMay() {
		return may;
	}

	public void setMay(float l) {
		may = l;
	}

	public float getJun() {
		return jun;
	}

	public void setJun(float l) {
		jun = l;
	}

	public float getJul() {
		return jul;
	}

	public void setJul(float l) {
		jul = l;
	}

	public float getAug() {
		return aug;
	}

	public void setAug(float l) {
		aug = l;
	}

	public float getSep() {
		return sep;
	}

	public void setSep(float l) {
		sep = l;
	}

	public float getOct() {
		return oct;
	}

	public void setOct(float l) {
		oct = l;
	}

	public float getNov() {
		return nov;
	}

	public void setNov(float l) {
		nov = l;
	}

	public float getDec() {
		return dec;
	}

	public void setDec(float l) {
		dec = l;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String s) {
		year = s;
	}

	public float getYearToDate() {
		float l = 0f;
		if (getConsumptionList() != null) {
			int i = consumptionList.size();
			if (i > 0) {
				for (int j = 0; j < i; j++) {
					Float long1 = (Float) consumptionList.get(j);
					l += long1.floatValue();
				}

			}
			yearToDate = l;
		}
		return yearToDate;
	}

	public void setYearToDate(long l) {
		yearToDate = l;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int i) {
		index = i;
	}

	public int getCurrentIndex() {
		return getIndex();
	}

	public float getRollingConsumption() {
		return rollingConsumption;
	}

	public void setRollingConsumption(float l) {
		rollingConsumption = l;
	}

	private Float convertToObject(float l) {
		if (l < 0f)
			return new Float(-99f);
		else
			return new Float(l);
	}

	private boolean toStripZero(int i) {
		return i >= getCurrentIndex() && i != getCurrentIndex();
	}

	private String convertText(float l, String s, boolean flag, boolean flag1,
			int i) {
		if (l < 0f)
			return convertText("", s, flag, flag1, i);
		else
			return convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	private String convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	public String getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"fcyear\" id=\"fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"fuelConsumptionId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(convertText(getJan(), "jan", true, toStripZero(0),
				0));
		stringbuffer.append(convertText(getFeb(), "feb", true, toStripZero(1),
				1));
		stringbuffer.append(convertText(getMar(), "mar", true, toStripZero(2),
				2));
		stringbuffer.append(convertText(getApr(), "apr", true, toStripZero(3),
				3));
		stringbuffer.append(convertText(getMay(), "may", true, toStripZero(4),
				4));
		stringbuffer.append(convertText(getJun(), "jun", true, toStripZero(5),
				5));
		stringbuffer.append(convertText(getJul(), "jul", true, toStripZero(6),
				6));
		stringbuffer.append(convertText(getAug(), "aug", true, toStripZero(7),
				7));
		stringbuffer.append(convertText(getSep(), "sep", true, toStripZero(8),
				8));
		stringbuffer.append(convertText(getOct(), "oct", true, toStripZero(9),
				9));
		stringbuffer.append(convertText(getNov(), "nov", true, toStripZero(10),
				10));
		stringbuffer.append(convertText(getDec(), "dec", true, toStripZero(11),
				11));
		stringbuffer.append(convertText(getYearToDate(), "yearToDate", false,
				false, -1));
		stringbuffer.append(convertText(getRollingConsumption(), "consumption",
				false, false, -1));
		/*
		 * stringbuffer.append(
		 * "<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		 * if(isLocked()) { if(isCompliant()) stringbuffer.append("Yes"); else
		 * stringbuffer.append("No"); } else if(isCompliant()) {
		 * stringbuffer.append
		 * ("<input type=\"radio\" name=\"compliance\" value=\"Y\" checked>");
		 * stringbuffer.append("Yes"); stringbuffer.append(
		 * "<input type=\"radio\" name=\"compliance\" value=\"N\">");
		 * stringbuffer.append("No"); } else { stringbuffer.append(
		 * "<input type=\"radio\" name=\"compliance\" value=\"Y\">");
		 * stringbuffer.append("Yes"); stringbuffer.append(
		 * "<input type=\"radio\" name=\"compliance\" value=\"N\" checked>");
		 * stringbuffer.append("No"); } stringbuffer.append("</td>");
		 */
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteoilconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String o_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"o_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String o_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return o_convertText("", s, flag, flag1, i);
		else
			return o_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String o_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"o_fcyear\" id=\"fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"o_fuelConsumptionId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(o_convertText(getJan(), "o_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(o_convertText(getFeb(), "o_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(o_convertText(getMar(), "o_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(o_convertText(getApr(), "o_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(o_convertText(getMay(), "o_may", true,
				toStripZero(4), 4));
		stringbuffer.append(o_convertText(getJun(), "o_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(o_convertText(getJul(), "o_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(o_convertText(getAug(), "o_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(o_convertText(getSep(), "o_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(o_convertText(getOct(), "o_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(o_convertText(getNov(), "o_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(o_convertText(getDec(), "o_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(o_convertText(getYearToDate(), "o_yearToDate",
				false, false, -1));
		stringbuffer.append(o_convertText(getRollingConsumption(),
				"o_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"o_lock\" value=\"Y\">");
		stringbuffer.append("</td>");

		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteoilconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");

		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String on_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"on_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String on_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return on_convertText("", s, flag, flag1, i);
		else
			return on_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String on_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"on_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkOneId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(on_convertText(getJan(), "on_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(on_convertText(getFeb(), "on_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(on_convertText(getMar(), "on_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(on_convertText(getApr(), "on_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(on_convertText(getMay(), "on_may", true,
				toStripZero(4), 4));
		stringbuffer.append(on_convertText(getJun(), "on_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(on_convertText(getJul(), "on_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(on_convertText(getAug(), "on_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(on_convertText(getSep(), "on_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(on_convertText(getOct(), "on_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(on_convertText(getNov(), "on_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(on_convertText(getDec(), "on_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(on_convertText(getYearToDate(), "on_yearToDate",
				false, false, -1));
		stringbuffer.append(on_convertText(getRollingConsumption(),
				"on_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"on_lock\" value=\"Y\">");
		stringbuffer.append("</td>");

		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");

		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String to_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"to_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String to_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return to_convertText("", s, flag, flag1, i);
		else
			return to_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String to_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"to_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkTwoId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(to_convertText(getJan(), "to_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(to_convertText(getFeb(), "to_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(to_convertText(getMar(), "to_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(to_convertText(getApr(), "to_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(to_convertText(getMay(), "to_may", true,
				toStripZero(4), 4));
		stringbuffer.append(to_convertText(getJun(), "to_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(to_convertText(getJul(), "to_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(to_convertText(getAug(), "to_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(to_convertText(getSep(), "to_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(to_convertText(getOct(), "to_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(to_convertText(getNov(), "to_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(to_convertText(getDec(), "to_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(to_convertText(getYearToDate(), "to_yearToDate",
				false, false, -1));
		stringbuffer.append(to_convertText(getRollingConsumption(),
				"to_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"to_lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String th_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"th_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String th_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return th_convertText("", s, flag, flag1, i);
		else
			return th_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String th_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"th_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkThreeId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(th_convertText(getJan(), "th_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(th_convertText(getFeb(), "th_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(th_convertText(getMar(), "th_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(th_convertText(getApr(), "th_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(th_convertText(getMay(), "th_may", true,
				toStripZero(4), 4));
		stringbuffer.append(th_convertText(getJun(), "th_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(th_convertText(getJul(), "th_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(th_convertText(getAug(), "th_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(th_convertText(getSep(), "th_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(th_convertText(getOct(), "th_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(th_convertText(getNov(), "th_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(th_convertText(getDec(), "th_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(th_convertText(getYearToDate(), "th_yearToDate",
				false, false, -1));
		stringbuffer.append(th_convertText(getRollingConsumption(),
				"th_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"th_lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String fr_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"fr_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String fr_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return fr_convertText("", s, flag, flag1, i);
		else
			return fr_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String fr_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"fr_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkFourId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(fr_convertText(getJan(), "fr_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(fr_convertText(getFeb(), "fr_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(fr_convertText(getMar(), "fr_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(fr_convertText(getApr(), "fr_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(fr_convertText(getMay(), "fr_may", true,
				toStripZero(4), 4));
		stringbuffer.append(fr_convertText(getJun(), "fr_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(fr_convertText(getJul(), "fr_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(fr_convertText(getAug(), "fr_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(fr_convertText(getSep(), "fr_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(fr_convertText(getOct(), "fr_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(fr_convertText(getNov(), "fr_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(fr_convertText(getDec(), "fr_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(fr_convertText(getYearToDate(), "fr_yearToDate",
				false, false, -1));
		stringbuffer.append(fr_convertText(getRollingConsumption(),
				"fr_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"fr_lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String fi_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"fi_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String fi_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return fi_convertText("", s, flag, flag1, i);
		else
			return fi_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String fi_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"fi_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkFiveId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(fi_convertText(getJan(), "fi_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(fi_convertText(getFeb(), "fi_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(fi_convertText(getMar(), "fi_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(fi_convertText(getApr(), "fi_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(fi_convertText(getMay(), "fi_may", true,
				toStripZero(4), 4));
		stringbuffer.append(fi_convertText(getJun(), "fi_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(fi_convertText(getJul(), "fi_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(fi_convertText(getAug(), "fi_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(fi_convertText(getSep(), "fi_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(fi_convertText(getOct(), "fi_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(fi_convertText(getNov(), "fi_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(fi_convertText(getDec(), "fi_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(fi_convertText(getYearToDate(), "fi_yearToDate",
				false, false, -1));
		stringbuffer.append(fi_convertText(getRollingConsumption(),
				"fi_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"fi_lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String si_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"si_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String si_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return si_convertText("", s, flag, flag1, i);
		else
			return si_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String si_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"si_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkSixId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(si_convertText(getJan(), "si_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(si_convertText(getFeb(), "si_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(si_convertText(getMar(), "si_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(si_convertText(getApr(), "si_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(si_convertText(getMay(), "si_may", true,
				toStripZero(4), 4));
		stringbuffer.append(si_convertText(getJun(), "si_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(si_convertText(getJul(), "si_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(si_convertText(getAug(), "si_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(si_convertText(getSep(), "si_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(si_convertText(getOct(), "si_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(si_convertText(getNov(), "si_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(si_convertText(getDec(), "si_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(si_convertText(getYearToDate(), "si_yearToDate",
				false, false, -1));
		stringbuffer.append(si_convertText(getRollingConsumption(),
				"si_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"si_lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	private String se_convertText(String s, String s1, boolean flag,
			boolean flag1, int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(s);
		} else {
			stringbuffer.append("<input name=\"");
			stringbuffer.append(s1);
			stringbuffer
					.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
			if (flag1) {
				if (s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
					stringbuffer.append("");
				else
					stringbuffer.append(s);
			} else {
				stringbuffer.append(s);
			}
			stringbuffer.append("\" >");
			if (flag)
				stringbuffer
						.append("<br><input name=\"se_rdbIndex\" type=\"radio\" value=\"")
						.append(i).append("\">");
		}
		stringbuffer.append("</td>");
		return stringbuffer.toString();
	}

	private String se_convertText(float l, String s, boolean flag,
			boolean flag1, int i) {
		if (l < 0f)
			return se_convertText("", s, flag, flag1, i);
		else
			return se_convertText(
					(new StringBuffer(String.format("%.2f", l))).toString(), s,
					flag, flag1, i);
	}

	public String se_getHtml(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		String s = "";
		if ((double) (i / 2) == (double) i / 2D)
			s = "evenRowStyle";
		else
			s = "oddRowStyle";
		stringbuffer.append("<tr class=\"").append(s).append("\"> ");
		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked()) {
			stringbuffer.append(getYear());
		} else {
			stringbuffer.append("<select name=\"se_fcyear\" id=\"on_fcyear\">");
			String s1 = (new StringBuffer(String.valueOf(getYear())))
					.toString();
			DropDown dropdown = YearEnum.getDropDownObj();
			Collection collection = dropdown.getDropDownValues();
			String s2;
			for (Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer
					.append(">").append(s2).append("</option>")) {
				NameValueBean namevaluebean = (NameValueBean) iterator.next();
				s2 = namevaluebean.getName();
				stringbuffer.append("<option value=\"").append(s2).append("\"");
				if (s2.equalsIgnoreCase(s1))
					stringbuffer.append(" selected ");
			}

			stringbuffer.append("</select>");
			stringbuffer
					.append("<input type=\"hidden\" name=\"inkSevenId\" value=\"")
					.append(getId()).append("\">");
		}
		stringbuffer.append("</td>");
		stringbuffer.append(se_convertText(getJan(), "se_jan", true,
				toStripZero(0), 0));
		stringbuffer.append(se_convertText(getFeb(), "se_feb", true,
				toStripZero(1), 1));
		stringbuffer.append(se_convertText(getMar(), "se_mar", true,
				toStripZero(2), 2));
		stringbuffer.append(se_convertText(getApr(), "se_apr", true,
				toStripZero(3), 3));
		stringbuffer.append(se_convertText(getMay(), "se_may", true,
				toStripZero(4), 4));
		stringbuffer.append(se_convertText(getJun(), "se_jun", true,
				toStripZero(5), 5));
		stringbuffer.append(se_convertText(getJul(), "se_jul", true,
				toStripZero(6), 6));
		stringbuffer.append(se_convertText(getAug(), "se_aug", true,
				toStripZero(7), 7));
		stringbuffer.append(se_convertText(getSep(), "se_sep", true,
				toStripZero(8), 8));
		stringbuffer.append(se_convertText(getOct(), "se_oct", true,
				toStripZero(9), 9));
		stringbuffer.append(se_convertText(getNov(), "se_nov", true,
				toStripZero(10), 10));
		stringbuffer.append(se_convertText(getDec(), "se_dec", true,
				toStripZero(11), 11));
		stringbuffer.append(se_convertText(getYearToDate(), "se_yearToDate",
				false, false, -1));
		stringbuffer.append(se_convertText(getRollingConsumption(),
				"se_consumption", false, false, -1));

		stringbuffer
				.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
		if (isLocked())
			stringbuffer.append("Locked");
		else
			stringbuffer
					.append("<input type=\"checkbox\" name=\"se_lock\" value=\"Y\">");
		stringbuffer.append("</td>");
		stringbuffer
				.append("<td align=\"center\"  nowrap class=\"fieldleft\">");
		stringbuffer.append("<a href=\"javascript:deleteinkconsumption('");
		stringbuffer.append(getId());
		stringbuffer.append("');\">");
		stringbuffer
				.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
		stringbuffer.append("</td>");
		stringbuffer.append("</tr>");
		return stringbuffer.toString();
	}

	int id;
	int entityId;
	String year;
	boolean isLocked;
	float yearToDate;
	float rollingConsumption;
	boolean isCompliant;
	float jan;
	float feb;
	float mar;
	float apr;
	float may;
	float jun;
	float jul;
	float aug;
	float sep;
	float oct;
	float nov;
	float dec;
	int index;
	String bctype;
	List consumptionList;

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ",";
		stringbuffer.append("id =").append(id).append(s);
		stringbuffer.append("entityId =").append(entityId).append(s);
		stringbuffer.append("year =").append(year).append(s);
		stringbuffer.append("isLocked =").append(isLocked).append(s);
		stringbuffer.append("yearToDate =").append(yearToDate).append(s);
		stringbuffer.append("rollingConsumption =").append(rollingConsumption)
				.append(s);
		stringbuffer.append("isCompliant =").append(isCompliant).append(s);
		stringbuffer.append("jan =").append(jan).append(s);
		stringbuffer.append("feb =").append(feb).append(s);
		stringbuffer.append("mar =").append(mar).append(s);
		stringbuffer.append("apr =").append(apr).append(s);
		stringbuffer.append("may =").append(may).append(s);
		stringbuffer.append("jun =").append(jun).append(s);
		stringbuffer.append("jul =").append(jul).append(s);
		stringbuffer.append("aug =").append(aug).append(s);
		stringbuffer.append("sep =").append(sep).append(s);
		stringbuffer.append("oct =").append(oct).append(s);
		stringbuffer.append("nov =").append(nov).append(s);
		stringbuffer.append("dec =").append(dec);
		return stringbuffer.toString();
	}
}