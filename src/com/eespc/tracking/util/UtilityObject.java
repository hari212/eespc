package com.eespc.tracking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.EngineRunningHrsVo;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.ui.filters.ResourceTypeValidatorIntf;

public class UtilityObject
{

    public UtilityObject()
    {
    }

    public static void cleanSessionObject(HttpServletRequest httpservletrequest)
    {
        HttpSession httpsession = httpservletrequest.getSession();
        try
        {
            Runtime runtime = Runtime.getRuntime();
            long l = runtime.freeMemory();
            Enumeration enumeration = httpsession.getAttributeNames();
            do
            {
                if(!enumeration.hasMoreElements())
                    break;
                String s = (String)enumeration.nextElement();
                if(s != null && (s.trim().equalsIgnoreCase("SESSION_USER") || s.trim().equalsIgnoreCase("MENUS_STRING")))
                {
                    if(log.isDebugEnabled())
                        log.debug("NOT Removing session attribute '" + s + "'");
                } else
                {
                    httpsession.removeAttribute(s);
                }
            } while(true);
            long l1 = runtime.freeMemory();
            if(log.isDebugEnabled())
                log.debug("Memory Total=" + runtime.totalMemory() + ", Before=" + l + ", After=" + runtime.freeMemory() + ", Cleaned=" + (l - l1));
        }
        catch(Exception exception)
        {
            if(log.isErrorEnabled())
                log.error("Unable to clean session object.", exception);
        }
    }

    public static void logOut(HttpServletRequest httpservletrequest)
    {
        HttpSession httpsession = httpservletrequest.getSession();
        try
        {
            Runtime runtime = Runtime.getRuntime();
            long l = runtime.freeMemory();
            String s;
            for(Enumeration enumeration = httpsession.getAttributeNames(); enumeration.hasMoreElements(); httpsession.removeAttribute(s))
                s = (String)enumeration.nextElement();

            long l1 = runtime.freeMemory();
            if(log.isDebugEnabled())
                log.debug("Memory Total=" + runtime.totalMemory() + ", Before=" + l + ", After=" + runtime.freeMemory() + ", Cleaned=" + (l - l1));
        }
        catch(Exception exception)
        {
            if(log.isErrorEnabled())
                log.error("Error in logout", exception);
        }
    }

    public static String checkNull(String s)
    {
        if(s == null)
            return "";
        else
            return s;
    }

    public static boolean convertBoolean(String s)
    {
        return s != null && (s.trim().equalsIgnoreCase("Y") || s.trim().equalsIgnoreCase("Yes") || s.trim().equalsIgnoreCase("true"));
    }

    public static String booleanToString(boolean flag)
    {
        if(flag)
            return "Y";
        else
            return "N";
    }

    public static boolean isNotEmpty(String s)
    {
        return s != null && s.trim().length() > 0;
    }

    public static Date convertToDate(String s)
    {
        if(s != null)
        {
            Date date = null;
            try
            {
                date = sdfDate.parse(s);
            }
            catch(ParseException parseexception)
            {
                log.error("Unable to convertToDate:", parseexception);
            }
            return date;
        } else
        {
            return new Date();
        }
    }

    public static String convertToString(Date date)
    {
        if(date == null)
        {
            return "";
        } else
        {
            String s = "";
            s = sdfOutput.format(date);
            return s;
        }
    }

    public static String convertToString(Date date, String s)
    {
        if(date == null)
        {
            return "";
        } else
        {
            String s1 = "";
            SimpleDateFormat simpledateformat = new SimpleDateFormat(s);
            s1 = simpledateformat.format(date);
            return s1;
        }
    }

    public static ResourceTypeValidatorIntf getValidator(String s)
        throws Exception
    {
        if(s == null)
            return null;
        else
            return (ResourceTypeValidatorIntf)Class.forName(s).newInstance();
    }

    public static int getIntFromString(String s)
    {
        int i = -99;
        if(isNotEmpty(s))
            try
            {
                i = Integer.parseInt(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                log.error("Error in getIntFromString", numberformatexception);
            }
        return i;
    }

    public static long getLongFromString(String s)
    {
        long l = -99L;
        if(isNotEmpty(s))
            try
            {
                l = Long.parseLong(s.trim());
            }
            catch(NumberFormatException numberformatexception)
            {
                numberformatexception.printStackTrace();
                log.error("Error in getLongFromString", numberformatexception);
            }
        return l;
    }

    public static boolean isBoroughValidInNyc(HttpServletRequest httpservletrequest)
        throws Exception
    {
        boolean flag = false;
        ResourceTypeValidatorIntf resourcetypevalidatorintf = getValidator("com.eespc.tracking.ui.filters.BridgeTunnelValidator");
        if(resourcetypevalidatorintf != null)
            try
            {
                flag = resourcetypevalidatorintf.isValidToShow(httpservletrequest);
            }
            catch(TrackingException trackingexception)
            {
                log.error("Error in isBoroughValidInNyc", trackingexception);
                flag = false;
            }
        else
            log.debug("Validator Interface is empty");
        return flag;
    }

    public static boolean isFacilityInNy(HttpServletRequest httpservletrequest)
    {
        boolean flag = false;
        FacilityVo facilityvo = (FacilityVo)httpservletrequest.getSession().getAttribute("FACILITY_OBJECT");
        if(facilityvo != null)
        {
            String s = facilityvo.getFacilityState();
            if(s != null && s.equalsIgnoreCase("NY"))
                flag = true;
        }
        return flag;
    }

    public static String getDateStringForDB(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Object obj = null;
        if(isNotEmpty(s))
        {
            Date date = convertToDate(s);
            stringbuffer.append("'").append(convertToString(date, "yyyy-MM-dd")).append("',");
        } else
        {
            stringbuffer.append("'").append("',");
        }
        return stringbuffer.toString();
    }

    public static String getDateStringForDB(String s, boolean flag)
    {
        StringBuffer stringbuffer = new StringBuffer();
        Object obj = null;
        if(isNotEmpty(s))
        {
            Date date = convertToDate(s);
            stringbuffer.append(convertToString(date, "yyyy-MM-dd"));
        }
        return stringbuffer.toString();
    }
//****************************************fuel****************//
    public static Properties getRollingAverageInfo(List list)
    {
        if(list == null || list.size() == 0)
        {
            log.debug("Fuel Consumption list is null/empty so returning without processing");
            return new Properties();
        }
        Object obj = new ArrayList();
        ArrayList arraylist = new ArrayList();
        FuelConsumptionVo fuelconsumptionvo = null;
        Object obj1 = null;
        int i = list.size();
        if(i == 1)
        {
            fuelconsumptionvo = (FuelConsumptionVo)list.get(0);
            List list1;
            if(fuelconsumptionvo.isLocked())
                obj = fuelconsumptionvo.getConsumptionList();
            else
                list1 = fuelconsumptionvo.getConsumptionList();
        }
        else
        {
            fuelconsumptionvo = (FuelConsumptionVo)list.get(i - 1);
            FuelConsumptionVo fuelconsumptionvo1 = (FuelConsumptionVo)list.get(i - 2);
            obj = fuelconsumptionvo1.getConsumptionList();
            List list2 = fuelconsumptionvo.getConsumptionList();
        }
        int j = fuelconsumptionvo.getCurrentIndex();
        System.out.println("currentIndex=" + j);
        return getPreviousConsumption(((List) (obj)));
    }

    public static Properties getPreviousConsumption(List list)
    {
        String s = "";
        if(list == null || list.size() == 0)
            s = "0|0|0|0|0|0|0|0|0|0|0|0";
        if(list != null)
        {
            for(int i = 0; i < list.size(); i++)
                s = s + list.get(i) + "|";

        }
        Properties properties = new Properties();
        properties.put("PREVIOUS_CONSUMPTION", s);
        return properties;
    }
//*************************************************************************************//


 public static Properties getEngRollingAverageInfo(List list)
    {
        if(list == null || list.size() == 0)
        {
            log.debug("Engine Running Hrs list is null/empty so returning without processing");
            return new Properties();
        }
        Object obj = new ArrayList();
        ArrayList arraylist = new ArrayList();
        EngineRunningHrsVo enginerunninghrsvo = null;
        Object obj1 = null;
        int i = list.size();
        if(i == 1)
        {
            enginerunninghrsvo = (EngineRunningHrsVo)list.get(0);
            List list1;
            if(enginerunninghrsvo.isLocked())
                obj = enginerunninghrsvo.getRunningHrsList();
            else
                list1 = enginerunninghrsvo.getRunningHrsList();
        }
        else
        {
            enginerunninghrsvo = (EngineRunningHrsVo)list.get(i - 1);
            EngineRunningHrsVo enginerunninghrsvo1 = (EngineRunningHrsVo)list.get(i - 2);
            obj = enginerunninghrsvo1.getRunningHrsList();
            List list2 = enginerunninghrsvo.getRunningHrsList();
        }
        int j = enginerunninghrsvo.getCurrentIndex();
        System.out.println("currentIndex=" + j);
        return getPreviousRunningHrs(((List) (obj)));
    }
    
    
    
     public static Properties getPreviousRunningHrs(List list)
    {
        String s = "";
        if(list == null || list.size() == 0)
            s = "0|0|0|0|0|0|0|0|0|0|0|0";
        if(list != null)
        {
            for(int i = 0; i < list.size(); i++)
                s = s + list.get(i) + "|";

        }
        Properties properties = new Properties();
        properties.put("TOTAL", s);
        return properties;
    }
    //*************************************************Engine Ends*****************************//
    public static void setPermitNumber(String s, HttpServletRequest httpservletrequest, List list)
    {
        if(!isNotEmpty(s))
        {
            log.debug("CRITICAL: setPermitNumber name is empty");
            return;
        }
        if(list != null && list.size() > 0)
        {
            PermitInfoVo permitinfovo = (PermitInfoVo)list.get(0);
            httpservletrequest.setAttribute(s, permitinfovo.getPermitNumber());
        } else
        {
            httpservletrequest.setAttribute(s, "");
        }
    }

    public static String checkDateString(String s)
    {
        if(s == null)
            return "";
        if(s != null && "0000-00-00".equalsIgnoreCase(s))
            return "";
        else
            return s;
    }

    public static String convertYyyyMmDd2MmDdYyyy(String s)
    {
        Date date = null;
        String s1 = "";
        try
        {
            String s2 = checkDateString(s);
            if(isNotEmpty(s2))
                if(isDateFormat_mm_dd_yyyy(s2))
                    date = sdfDate.parse(s2);
                else
                    date = yyyyMmDd.parse(s2);
        }
        catch(ParseException parseexception)
        {
            log.error("Error while parsing the date :", parseexception);
        }
        return convertToString(date);
    }
    
    public static Date convert_YyyyMmDd2MmDdYyyy(String s)
    {
        Date date = null;
        String s1 = "";
        try
        {
            String s2 = checkDateString(s);
            if(isNotEmpty(s2))
                if(isDateFormat_mm_dd_yyyy(s2))
                    date = sdfDate.parse(s2);
                else
                    date = yyyyMmDd.parse(s2);
        }
        catch(ParseException parseexception)
        {
            log.error("Error while parsing the date :", parseexception);
        }
        return date;
    }

    public static boolean isDateFormat_mm_dd_yyyy(String s)
    {
        String s1 = "[0-9][0-9]-[0-9][0-9]-[0-9][0-9][0-9][0-9]";
        Pattern pattern = Pattern.compile(s1);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public static double getDoubleFromString(String s)
    {
        double d = 00D;
        if(isNotEmpty(s))
            try
            {
                d = Double.parseDouble(s);
            }
            catch(NumberFormatException numberformatexception)
            {
                log.error("Error in getDoubleFromString", numberformatexception);
            }
        return d;
    }

    public static String checkNullAndFill(String s, String s1)
    {
        if(isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
            return s;
        else
            return s1;
    }
    public static String convertDate(String strDate,String fromFormat,String toFormat){
	 
	 try{
	 return	new SimpleDateFormat(toFormat).format (new SimpleDateFormat(fromFormat).parse(strDate));
		}catch(Exception ex){ return ""; }
	}
	public static final String DB_DATE_FORMAT="yyyy-MM-dd";
	public static final String VIEW_DATE_FORMAT="MM/dd/yyyy";
	public static final String ONLY_YEAR="yyyy";
	public static final String ONLY_MONTH="MM";
	public static final String ONLY_MONTH_FULL="MMMMM";
	
	
    public static void main(String args[])
    {
    	System.out.println(convertDate("2005-12-23","yyyy-MM-dd","yyyy"));
        System.out.println(convertYyyyMmDd2MmDdYyyy("2005-12-23"));
        System.out.println(convertYyyyMmDd2MmDdYyyy(""));
        System.out.println(convertYyyyMmDd2MmDdYyyy("0000-00-00"));
        System.out.println(convertYyyyMmDd2MmDdYyyy("12-12-2005"));
        System.out.println("isDateFormat_mm_dd_yyyy=" + isDateFormat_mm_dd_yyyy("12-12-2005"));
        System.out.println("isDateFormat_mm_dd_yyyy=" + isDateFormat_mm_dd_yyyy("2005-12-12"));
    }

    

    private static Log log;
    private static SimpleDateFormat sdfOutput = new SimpleDateFormat("MM/dd/yyyy");
    private static SimpleDateFormat sdfDate = new SimpleDateFormat("MM/dd/yyyy");
    private static SimpleDateFormat yyyyMmDd = new SimpleDateFormat("yyyy-MM-dd");
    public static final String EMPTY_ROLOVER = "0|0|0|0|0|0|0|0|0|0|0|0";
    public static final String EMPTY_DATE_STR = "0000-00-00";

    static 
    {
        log = LogFactory.getLog(com.eespc.tracking.util.UtilityObject.class);
    }
}