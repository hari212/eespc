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

public class EngineRunningHrsVo
implements Serializable
{

public EngineRunningHrsVo()
{
    id = -99;
    entityId = -99;
    year = null;
    isLocked = false;
    yearToDate = -99f;
  //rollingConsumption = -99f;
  //isCompliant = false;
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
    ebctype=null;
    runningHrsList = null;
}

public EngineRunningHrsVo(ResultSet resultset)
    throws SQLException
{
    id = -99;
    entityId = -99;
    year = null;
    isLocked = false;
    yearToDate = -99f;
  //rollingConsumption = -99f;
  //  isCompliant = false;
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
    ebctype=null;
    runningHrsList = null;
    
    id = resultset.getInt("ID");
    entityId = resultset.getInt("ENTITY_ID");
    year = resultset.getString("YEAR");    
  //rollingConsumption = resultset.getFloat("ROLLINGCONSUMPTION");
  //isCompliant = UtilityObject.convertBoolean(resultset.getString("COMPLIANT"));
    jan = UtilityObject.isNotEmpty(resultset.getString("JAN")) ? resultset.getFloat("JAN") : -99f;
    feb = UtilityObject.isNotEmpty(resultset.getString("FEB")) ? resultset.getFloat("FEB") : -99f;
    mar = UtilityObject.isNotEmpty(resultset.getString("MAR")) ? resultset.getFloat("MAR") : -99f;
    apr = UtilityObject.isNotEmpty(resultset.getString("APR")) ? resultset.getFloat("APR") : -99f;
    may = UtilityObject.isNotEmpty(resultset.getString("MAY")) ? resultset.getFloat("MAY") : -99f;
    jun = UtilityObject.isNotEmpty(resultset.getString("JUN")) ? resultset.getFloat("JUN") : -99f;
    jul = UtilityObject.isNotEmpty(resultset.getString("JUL")) ? resultset.getFloat("JUL") : -99f;
    aug = UtilityObject.isNotEmpty(resultset.getString("AUG")) ? resultset.getFloat("AUG") : -99f;
    sep = UtilityObject.isNotEmpty(resultset.getString("SEP")) ? resultset.getFloat("SEP") : -99f;
    oct = UtilityObject.isNotEmpty(resultset.getString("OCT")) ? resultset.getFloat("OCT") : -99f;
    nov = UtilityObject.isNotEmpty(resultset.getString("NOV")) ? resultset.getFloat("NOV") : -99f;
    dec = UtilityObject.isNotEmpty(resultset.getString("DEC")) ? resultset.getFloat("DEC") : -99f;    
    yearToDate = resultset.getFloat("YEARTODATE");
    isLocked = UtilityObject.convertBoolean(resultset.getString("LOCK"));
    index = UtilityObject.isNotEmpty(resultset.getString("index")) ? resultset.getInt("index") : 0;
	ebctype=resultset.getString("ebctype");
}


public String getEbctype()
{
    return ebctype;
}

public void setEbctype(String l)
{
    ebctype = l;
}


public List getRunningHrsList()
{
    runningHrsList = new ArrayList();
    runningHrsList.add(convertToObject(getJan()));
    runningHrsList.add(convertToObject(getFeb()));
    runningHrsList.add(convertToObject(getMar()));
    runningHrsList.add(convertToObject(getApr()));
    runningHrsList.add(convertToObject(getMay()));
    runningHrsList.add(convertToObject(getJun()));
    runningHrsList.add(convertToObject(getJul()));
    runningHrsList.add(convertToObject(getAug()));
    runningHrsList.add(convertToObject(getSep()));
    runningHrsList.add(convertToObject(getOct()));
    runningHrsList.add(convertToObject(getNov()));
    runningHrsList.add(convertToObject(getDec()));
    return runningHrsList;
}


public void setRunningHrsList(List list)
{
    runningHrsList = list;
}    

public int getEntityId()
{
    return entityId;
}

public void setEntityId(int i)
{
    entityId = i;
}



public int getId()
{
    return id;
}

public void setId(int i)
{
    id = i;
}

/*public boolean isCompliant()
{
    return isCompliant;
}

public void setCompliant(boolean flag)
{
    isCompliant = flag;
}*/

public boolean isLocked()
{
    return isLocked;
}

public void setLocked(boolean flag)
{
    isLocked = flag;
}

public float getJan()
{
    return jan;
}

public void setJan(float l)
{
    jan = l;
}

public float getFeb()
{
    return feb;
}

public void setFeb(float l)
{
    feb = l;
}

public float getMar()
{
    return mar;
}

public void setMar(float l)
{
    mar = l;
}

public float getApr()
{
    return apr;
}

public void setApr(float l)
{
    apr = l;
}




public float getMay()
{
    return may;
}

public void setMay(float l)
{
    may = l;
}
public float getJun()
{
    return jun;
}

public void setJun(float l)
{
    jun = l;
}

public float getJul()
{
    return jul;
}

public void setJul(float l)
{
    jul = l;
}

public float getAug()
{
    return aug;
}

public void setAug(float l)
{
    aug = l;
}

public float getSep()
{
    return sep;
}

public void setSep(float l)
{
    sep = l;
}
public float getOct()
{
    return oct;
}

public void setOct(float l)
{
    oct = l;
}
public float getNov()
{
    return nov;
}

public void setNov(float l)
{
    nov = l;
}
public float getDec()
{
    return dec;
}

public void setDec(float l)
{
    dec = l;
}


public String getYear()
{
    return year;
}

public void setYear(String s)
{
    year = s;
}

public float getYearToDate()
{
    float l = 0f;
    if(getRunningHrsList() != null)
    {
        int i = runningHrsList.size();
        if(i > 0)
        {
            for(int j = 0; j < i; j++)
            {
                Float long1 = (Float)runningHrsList.get(j);
                l += long1.floatValue();
            }

        }
        yearToDate = l;
    }
    return yearToDate;
}


public void setYearToDate(long l)
{
    yearToDate = l;
}

public int getIndex()
{
    return index;
}

public void setIndex(int i)
{
    index = i;
}


public int getCurrentIndex()
{
    return getIndex();
}


/*public float getRollingConsumption()
{
    return rollingConsumption;
}

public void setRollingConsumption(float l)
{
    rollingConsumption = l;
}*/


private Float convertToObject(float l)
{
    if(l < 0f)
        return new Float(-99f);
    else
        return new Float(l);
}

private boolean toStripZero(int i)
{
    return i >= getCurrentIndex() && i != getCurrentIndex();
}


private String convertText(float l, String s, boolean flag, boolean flag1, int i)
{
    if(l < 0f)
        return convertText("", s, flag, flag1, i);
    else
        return convertText((new StringBuffer(String.format("%.2f",l))).toString(), s, flag, flag1, i);
}

private String convertText(String s, String s1, boolean flag, boolean flag1, int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(s);
    } else
    {
        stringbuffer.append("<input name=\"");
        stringbuffer.append(s1);
        stringbuffer.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
        if(flag1)
        {
            if(s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
                stringbuffer.append("");
            else
                stringbuffer.append(s);
        } else
        {
            stringbuffer.append(s);
        }
        stringbuffer.append("\" >");
        if(flag)
            stringbuffer.append("<br><input name=\"e_rdbIndex\" type=\"radio\" value=\"").append(i).append("\">");
    }
    stringbuffer.append("</td>");
    return stringbuffer.toString();
}

public String getHtml(int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    String s = "";
    if((double)(i / 2) == (double)i / 2D)
        s = "evenRowStyle";
    else
        s = "oddRowStyle";
    stringbuffer.append("<tr class=\"").append(s).append("\"> ");
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(getYear());
    } else
    {
        stringbuffer.append("<select name=\"e_rhyear\" id=\"e_rhyear\">");
        String s1 = (new StringBuffer(String.valueOf(getYear()))).toString();
        DropDown dropdown = YearEnum.getDropDownObj();
        Collection collection = dropdown.getDropDownValues();
        String s2;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer.append(">").append(s2).append("</option>"))
        {
            NameValueBean namevaluebean = (NameValueBean)iterator.next();
            s2 = namevaluebean.getName();
            stringbuffer.append("<option value=\"").append(s2).append("\"");
            if(s2.equalsIgnoreCase(s1))
                stringbuffer.append(" selected ");
        }

        stringbuffer.append("</select>");
        stringbuffer.append("<input type=\"hidden\" name=\"engineRunningHrsId\" value=\"").append(getId()).append("\">");
        System.out.println(" New Year: "+s1);
    }
    stringbuffer.append("</td>");
    stringbuffer.append(convertText(getJan(), "e_jan", true, toStripZero(0), 0));
    stringbuffer.append(convertText(getFeb(), "e_feb", true, toStripZero(1), 1));
    stringbuffer.append(convertText(getMar(), "e_mar", true, toStripZero(2), 2));
    stringbuffer.append(convertText(getApr(), "e_apr", true, toStripZero(3), 3));
    stringbuffer.append(convertText(getMay(), "e_may", true, toStripZero(4), 4));
    stringbuffer.append(convertText(getJun(), "e_jun", true, toStripZero(5), 5));
    stringbuffer.append(convertText(getJul(), "e_jul", true, toStripZero(6), 6));
    stringbuffer.append(convertText(getAug(), "e_aug", true, toStripZero(7), 7));
    stringbuffer.append(convertText(getSep(), "e_sep", true, toStripZero(8), 8));
    stringbuffer.append(convertText(getOct(), "e_oct", true, toStripZero(9), 9));
    stringbuffer.append(convertText(getNov(), "e_nov", true, toStripZero(10), 10));
    stringbuffer.append(convertText(getDec(), "e_dec", true, toStripZero(11), 11));
    stringbuffer.append(convertText(getYearToDate(), "e_yearToDate", false, false, -1));
   // stringbuffer.append(convertText(getRollingConsumption(), "consumption", false, false, -1));
    
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
        stringbuffer.append("Locked");
    else
        stringbuffer.append("<input type=\"checkbox\" name=\"e_lock\" value=\"Y\">");
    stringbuffer.append("</td>");
    stringbuffer.append("<td align=\"center\"  nowrap class=\"fieldleft\">");					
	stringbuffer.append("<a href=\"javascript:deleteEningeRunningHrs('");
	stringbuffer.append(getId());
	stringbuffer.append("');\">");
	stringbuffer.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
	stringbuffer.append("</td>");
    stringbuffer.append("</tr>");
    return stringbuffer.toString();
}


  private String oil_convertText(String s, String s1, boolean flag, boolean flag1, int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(s);
    } else
    {
        stringbuffer.append("<input name=\"");
        stringbuffer.append(s1);
        stringbuffer.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
        if(flag1)
        {
            if(s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
                stringbuffer.append("");
            else
                stringbuffer.append(s);
        } else
        {
            stringbuffer.append(s);
        }
        stringbuffer.append("\" >");
        if(flag)
            stringbuffer.append("<br><input name=\"oil_rdbIndex\" type=\"radio\" value=\"").append(i).append("\">");
    }
    stringbuffer.append("</td>");
    return stringbuffer.toString();
}

private String oil_convertText(float l, String s, boolean flag, boolean flag1, int i)
{
    if(l < 0f)
        return oil_convertText("", s, flag, flag1, i);
    else
        return oil_convertText((new StringBuffer(String.format("%.2f",l))).toString(), s, flag, flag1, i);
}

public String oil_getHtml(int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    String s = "";
    if((double)(i / 2) == (double)i / 2D)
        s = "evenRowStyle";
    else
        s = "oddRowStyle";
    stringbuffer.append("<tr class=\"").append(s).append("\"> ");
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(getYear());
    } else
    {
        stringbuffer.append("<select name=\"oil_fcyear\" id=\"oil_fcyear\">");
        String s1 = (new StringBuffer(String.valueOf(getYear()))).toString();
        DropDown dropdown = YearEnum.getDropDownObj();
        Collection collection = dropdown.getDropDownValues();
        String s2;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer.append(">").append(s2).append("</option>"))
        {
            NameValueBean namevaluebean = (NameValueBean)iterator.next();
            s2 = namevaluebean.getName();
            stringbuffer.append("<option value=\"").append(s2).append("\"");
            if(s2.equalsIgnoreCase(s1))
                stringbuffer.append(" selected ");
        }

        stringbuffer.append("</select>");
        stringbuffer.append("<input type=\"hidden\" name=\"oil_monthlyPressureId\" value=\"").append(getId()).append("\">");
    }
    stringbuffer.append("</td>");
    stringbuffer.append(oil_convertText(getJan(), "oil_jan", true, toStripZero(0), 0));
    stringbuffer.append(oil_convertText(getFeb(), "oil_feb", true, toStripZero(1), 1));
    stringbuffer.append(oil_convertText(getMar(), "oil_mar", true, toStripZero(2), 2));
    stringbuffer.append(oil_convertText(getApr(), "oil_apr", true, toStripZero(3), 3));
    stringbuffer.append(oil_convertText(getMay(), "oil_may", true, toStripZero(4), 4));
    stringbuffer.append(oil_convertText(getJun(), "oil_jun", true, toStripZero(5), 5));
    stringbuffer.append(oil_convertText(getJul(), "oil_jul", true, toStripZero(6), 6));
    stringbuffer.append(oil_convertText(getAug(), "oil_aug", true, toStripZero(7), 7));
    stringbuffer.append(oil_convertText(getSep(), "oil_sep", true, toStripZero(8), 8));
    stringbuffer.append(oil_convertText(getOct(), "oil_oct", true, toStripZero(9), 9));
    stringbuffer.append(oil_convertText(getNov(), "oil_nov", true, toStripZero(10), 10));
    stringbuffer.append(oil_convertText(getDec(), "oil_dec", true, toStripZero(11), 11));
    stringbuffer.append(oil_convertText(getYearToDate(), "oil_yearToDate", false, false, -1));
    
   
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
        stringbuffer.append("Locked");
    else
        stringbuffer.append("<input type=\"checkbox\" name=\"oil_lock\" value=\"Y\">");
    stringbuffer.append("</td>");       
    
    stringbuffer.append("<td align=\"center\"  nowrap class=\"fieldleft\">");					
	stringbuffer.append("<a href=\"javascript:deleteEningeRunningHrs('");
	stringbuffer.append(getId());
	stringbuffer.append("');\">");
	stringbuffer.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
	stringbuffer.append("</td>");					

    stringbuffer.append("</tr>");
    return stringbuffer.toString();
}



private String gas_convertText(String s, String s1, boolean flag, boolean flag1, int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(s);
    } else
    {
        stringbuffer.append("<input name=\"");
        stringbuffer.append(s1);
        stringbuffer.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
        if(flag1)
        {
            if(s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
                stringbuffer.append("");
            else
                stringbuffer.append(s);
        } else
        {
            stringbuffer.append(s);
        }
        stringbuffer.append("\" >");
        if(flag)
            stringbuffer.append("<br><input name=\"gas_rdbIndex\" type=\"radio\" value=\"").append(i).append("\">");
    }
    stringbuffer.append("</td>");
    return stringbuffer.toString();
}

private String gas_convertText(float l, String s, boolean flag, boolean flag1, int i)
{
    if(l < 0f)
        return gas_convertText("", s, flag, flag1, i);
    else
        return gas_convertText((new StringBuffer(String.format("%.2f",l))).toString(), s, flag, flag1, i);
}

public String gas_getHtml(int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    String s = "";
    if((double)(i / 2) == (double)i / 2D)
        s = "evenRowStyle";
    else
        s = "oddRowStyle";
    stringbuffer.append("<tr class=\"").append(s).append("\"> ");
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(getYear());
    } else
    {
        stringbuffer.append("<select name=\"gas_fcyear\" id=\"gas_fcyear\">");
        String s1 = (new StringBuffer(String.valueOf(getYear()))).toString();
        DropDown dropdown = YearEnum.getDropDownObj();
        Collection collection = dropdown.getDropDownValues();
        String s2;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer.append(">").append(s2).append("</option>"))
        {
            NameValueBean namevaluebean = (NameValueBean)iterator.next();
            s2 = namevaluebean.getName();
            stringbuffer.append("<option value=\"").append(s2).append("\"");
            if(s2.equalsIgnoreCase(s1))
                stringbuffer.append(" selected ");
        }

        stringbuffer.append("</select>");
        stringbuffer.append("<input type=\"hidden\" name=\"gas_monthlyPressureId\" value=\"").append(getId()).append("\">");
    }
    stringbuffer.append("</td>");
    stringbuffer.append(gas_convertText(getJan(), "gas_jan", true, toStripZero(0), 0));
    stringbuffer.append(gas_convertText(getFeb(), "gas_feb", true, toStripZero(1), 1));
    stringbuffer.append(gas_convertText(getMar(), "gas_mar", true, toStripZero(2), 2));
    stringbuffer.append(gas_convertText(getApr(), "gas_apr", true, toStripZero(3), 3));
    stringbuffer.append(gas_convertText(getMay(), "gas_may", true, toStripZero(4), 4));
    stringbuffer.append(gas_convertText(getJun(), "gas_jun", true, toStripZero(5), 5));
    stringbuffer.append(gas_convertText(getJul(), "gas_jul", true, toStripZero(6), 6));
    stringbuffer.append(gas_convertText(getAug(), "gas_aug", true, toStripZero(7), 7));
    stringbuffer.append(gas_convertText(getSep(), "gas_sep", true, toStripZero(8), 8));
    stringbuffer.append(gas_convertText(getOct(), "gas_oct", true, toStripZero(9), 9));
    stringbuffer.append(gas_convertText(getNov(), "gas_nov", true, toStripZero(10), 10));
    stringbuffer.append(gas_convertText(getDec(), "gas_dec", true, toStripZero(11), 11));
    stringbuffer.append(gas_convertText(getYearToDate(), "gas_yearToDate", false, false, -1));
       
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
        stringbuffer.append("Locked");
    else
        stringbuffer.append("<input type=\"checkbox\" name=\"gas_lock\" value=\"Y\">");
    stringbuffer.append("</td>");       
    
    stringbuffer.append("<td align=\"center\"  nowrap class=\"fieldleft\">");					
	stringbuffer.append("<a href=\"javascript:deleteEningeRunningHrs('");
	stringbuffer.append(getId());
	stringbuffer.append("');\">");
	stringbuffer.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
	stringbuffer.append("</td>");					

    stringbuffer.append("</tr>");
    return stringbuffer.toString();
}



private String anu_convertText(String s, String s1, boolean flag, boolean flag1, int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(s);
    } else
    {
        stringbuffer.append("<input name=\"");
        stringbuffer.append(s1);
        stringbuffer.append("\" type=\"text\" class=\"normal\" size=\"4\" maxlength=\"11\" value=\"");
        if(flag1)
        {
            if(s.equalsIgnoreCase("0.0") || s.equalsIgnoreCase("0"))
                stringbuffer.append("");
            else
                stringbuffer.append(s);
        } else
        {
            stringbuffer.append(s);
        }
        stringbuffer.append("\" >");
        if(flag)
            stringbuffer.append("<br><input name=\"anu_rdbIndex\" type=\"radio\" value=\"").append(i).append("\">");
    }
    stringbuffer.append("</td>");
    return stringbuffer.toString();
}

    private String anu_convertText(float l, String s, boolean flag, boolean flag1, int i)
{
    if(l < 0f)
        return anu_convertText("", s, flag, flag1, i);
    else
        return anu_convertText((new StringBuffer(String.format("%.2f",l))).toString(), s, flag, flag1, i);
}

public String anu_getHtml(int i)
{
    StringBuffer stringbuffer = new StringBuffer();
    String s = "";
    if((double)(i / 2) == (double)i / 2D)
        s = "evenRowStyle";
    else
        s = "oddRowStyle";
    stringbuffer.append("<tr class=\"").append(s).append("\"> ");
    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
    {
        stringbuffer.append(getYear());
    } else
    {
        stringbuffer.append("<select name=\"anu_fcyear\" id=\"anu_fcyear\">");
        String s1 = (new StringBuffer(String.valueOf(getYear()))).toString();
        DropDown dropdown = YearEnum.getDropDownObj();
        Collection collection = dropdown.getDropDownValues();
        String s2;
        for(Iterator iterator = collection.iterator(); iterator.hasNext(); stringbuffer.append(">").append(s2).append("</option>"))
        {
            NameValueBean namevaluebean = (NameValueBean)iterator.next();
            s2 = namevaluebean.getName();
            stringbuffer.append("<option value=\"").append(s2).append("\"");
            if(s2.equalsIgnoreCase(s1))
                stringbuffer.append(" selected ");
        }

        stringbuffer.append("</select>");
        stringbuffer.append("<input type=\"hidden\" name=\"anu_monthlyPressureId\" value=\"").append(getId()).append("\">");
    }
    stringbuffer.append("</td>");
    stringbuffer.append(anu_convertText(getJan(), "anu_jan", true, toStripZero(0), 0));
    stringbuffer.append(anu_convertText(getFeb(), "anu_feb", true, toStripZero(1), 1));
    stringbuffer.append(anu_convertText(getMar(), "anu_mar", true, toStripZero(2), 2));
    stringbuffer.append(anu_convertText(getApr(), "anu_apr", true, toStripZero(3), 3));
    stringbuffer.append(anu_convertText(getMay(), "anu_may", true, toStripZero(4), 4));
    stringbuffer.append(anu_convertText(getJun(), "anu_jun", true, toStripZero(5), 5));
    stringbuffer.append(anu_convertText(getJul(), "anu_jul", true, toStripZero(6), 6));
    stringbuffer.append(anu_convertText(getAug(), "anu_aug", true, toStripZero(7), 7));
    stringbuffer.append(anu_convertText(getSep(), "anu_sep", true, toStripZero(8), 8));
    stringbuffer.append(anu_convertText(getOct(), "anu_oct", true, toStripZero(9), 9));
    stringbuffer.append(anu_convertText(getNov(), "anu_nov", true, toStripZero(10), 10));
    stringbuffer.append(anu_convertText(getDec(), "anu_dec", true, toStripZero(11), 11));
    stringbuffer.append(anu_convertText(getYearToDate(), "anu_yearToDate", false, false, -1));
    

    stringbuffer.append("<td width=\"26%\" align=\"center\"  nowrap class=\"fieldleft\">");
    if(isLocked())
        stringbuffer.append("Locked");
    else
        stringbuffer.append("<input type=\"checkbox\" name=\"anu_lock\" value=\"Y\">");
    stringbuffer.append("</td>");
    stringbuffer.append("<td align=\"center\"  nowrap class=\"fieldleft\">");					
	stringbuffer.append("<a href=\"javascript:deleteEningeRunningHrs('");
	stringbuffer.append(getId());
	stringbuffer.append("');\">");
	stringbuffer.append("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
	stringbuffer.append("</td>");
    stringbuffer.append("</tr>");
    return stringbuffer.toString();
}


int id;
int entityId;
String year;
boolean isLocked;
float yearToDate;
//float rollingConsumption;
//boolean isCompliant;
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
String ebctype;
List runningHrsList;    
public String toString()
{
    StringBuffer stringbuffer = new StringBuffer();
    String s = ",";
    stringbuffer.append("id =").append(id).append(s);
    stringbuffer.append("entityId =").append(entityId).append(s);
    stringbuffer.append("year =").append(year).append(s);
    stringbuffer.append("isLocked =").append(isLocked).append(s);
    stringbuffer.append("yearToDate =").append(yearToDate).append(s);
  //stringbuffer.append("rollingConsumption =").append(rollingConsumption).append(s);
  //stringbuffer.append("isCompliant =").append(isCompliant).append(s);
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