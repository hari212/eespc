// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 2:18:23 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   TagUtil.java

package com.eespc.tracking.ui.taglib;

import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.NameValueBean;
import com.eespc.tracking.util.UtilityObject;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;

public class TagUtil
{

    public TagUtil()
    {
    } 

    public static boolean isViewOnly(ServletRequest servletrequest)
        throws JspException
    {
        boolean flag = false;
        String s = (String)servletrequest.getAttribute("isComponentEditable");
        if(s != null && s.equalsIgnoreCase("N"))
            flag = true;
        return flag;
    }

    public static Object eval(HttpServletRequest httpservletrequest, String s)
    {
        if(s.startsWith("${") && s.endsWith("}"))
        {
            int i = s.indexOf(".");
            String s1 = s.substring(2, i);
            String s2 = s.substring(i + 1, s.length() - 1);
            Object obj = httpservletrequest.getAttribute(s1);
            if(obj == null)
            {
                HttpSession httpsession = httpservletrequest.getSession(false);
                if(httpsession != null)
                    obj = httpsession.getAttribute(s1);
            }
            if(obj == null)
                return null;
            else
                return getBeanProp(obj, s2);
        } else
        {
            return s;
        }
    }

    public static Object getBeanProp(Object bean, String getter)
    {
        try
        {
            Method getterM = bean.getClass().getMethod(getter, new Class[0]);
            return getterM.invoke(bean);
        }
        catch(Exception exception)
        {
            return null;
        }
    }

    public static String toHtml(HttpServletRequest httpservletrequest, int i, boolean flag, String s, String s1, String s2)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if(flag)
            switch(i)
            {
            case 4: // '\004'
                boolean flag1 = UtilityObject.convertBoolean(s1);
                stringbuffer.append("<input type=\"checkbox\" name=\"").append(s).append("\" value=\"Y\" ").append(flag1 ? " checked " : "").append(" > " + s2);
                break;

            case 2: // '\002'
                stringbuffer.append("<select name=\"").append(s).append("\">");
                if(s2.equalsIgnoreCase("YESNO"))
                {
                    String s3 = "";
                    String s4 = "";
                    if(s1.equalsIgnoreCase("Y") || s1.equalsIgnoreCase("Yes"))
                        s3 = " selected ";
                    else
                        s4 = " selected ";
                    stringbuffer.append("<option value=\"Y\"").append(s3).append(">Yes</option>");
                    stringbuffer.append("<option value=\"N\"").append(s4).append(">No</option>");
                } else
                {
                    DropDown dropdown = (DropDown)httpservletrequest.getAttribute(s2);
                    Collection collection = dropdown.getDropDownValues();
                    Iterator iterator = collection.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        NameValueBean namevaluebean = (NameValueBean)iterator.next();
                        if(namevaluebean != null)
                        {
                            String s5 = "";
                            String s6 = namevaluebean.getName();
                            if(s6 != null && s1 != null && s1.equalsIgnoreCase(s6))
                                s5 = " selected ";
                            stringbuffer.append("<option value=\"");
                            stringbuffer.append(namevaluebean.getValue());
                            stringbuffer.append("\"").append(s5).append(" >");
                            stringbuffer.append(s6);
                            stringbuffer.append("</option>");
                        }
                    } while(true);
                }
                stringbuffer.append("</select>");
                break;

            case 5: // '\005'
                boolean flag2 = UtilityObject.convertBoolean(s1);
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Y\" ").append(flag2 ? " checked " : "").append(" >Yes");
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"N\" ").append(flag2 ? "" : " checked ").append(" >No");
			//	stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"NA\" ").append(flag2 ? "" : " checked ").append(" >NotApplicable");
                break;

		   case 6: // '\006'
                boolean flag3 = UtilityObject.convertBoolean(s1);
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Y\" ").append(flag3 ? " checked " : "").append(" >Yes");
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"N\" ").append(flag3 ? "" : " checked ").append(" >No");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"NA\" ").append(flag3 ? "" : " checked ").append(" >N/A");
                break;

                case 7: // '\006'
                boolean flag5 = UtilityObject.convertBoolean(s1);
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Waste1\" ").append(flag5 ? " checked " : "").append(" >One");
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Waste2\" ").append(flag5 ? "" : " checked ").append(" >Two");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Waste3\" ").append(flag5 ? "" : " checked ").append(" >Three");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Waste4\" ").append(flag5 ? "" : " checked ").append(" >Four");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"Waste5\" ").append(flag5 ? "" : " checked ").append(" >Five");
                break;


            case 8: // '\006'
                boolean flag6 = UtilityObject.convertBoolean(s1);
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"F Waste\" ").append(flag6 ? " checked " : "").append(" >F");
                stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"O Waste\" ").append(flag6 ? "" : " checked ").append(" >O");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"K Waste\" ").append(flag6 ? "" : " checked ").append(" >K");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"U Waste\" ").append(flag6 ? "" : " checked ").append(" >U");
				stringbuffer.append("<input type=\"radio\" name=\"").append(s).append("\" value=\"P Waste\" ").append(flag6 ? "" : " checked ").append(" >P");
                break;

			case 9:
			
                stringbuffer.append("<select name=\"").append(s).append("\">");
                if(s2.equalsIgnoreCase("InternalExternal"))
                {
                    String s3 = "";
                    String s4 = "";
                    if(s1.equalsIgnoreCase("Internal") || s1.equalsIgnoreCase("Internal"))
                        s3 = " selected ";
                    else
                        s4 = " selected ";
                    stringbuffer.append("<option value=\"Internal\"").append(s3).append(">Internal</option>");
                    stringbuffer.append("<option value=\"External\"").append(s4).append(">External</option>");
                } else
                {
                    DropDown dropdown = (DropDown)httpservletrequest.getAttribute(s2);
                    Collection collection = dropdown.getDropDownValues();
                    Iterator iterator = collection.iterator();
                    do
                    {
                        if(!iterator.hasNext())
                            break;
                        NameValueBean namevaluebean = (NameValueBean)iterator.next();
                        if(namevaluebean != null)
                        {
                            String s5 = "";
                            String s6 = namevaluebean.getName();
                            if(s6 != null && s1 != null && s1.equalsIgnoreCase(s6))
                                s5 = " selected ";
                            stringbuffer.append("<option value=\"");
                            stringbuffer.append(namevaluebean.getValue());
                            stringbuffer.append("\"").append(s5).append(" >");
                            stringbuffer.append(s6);
                            stringbuffer.append("</option>");
                        }
                    } while(true);
                }
                stringbuffer.append("</select>");
                break;

            case 0: // '\0'
                stringbuffer.append("<input name=\"").append(s).append("\" type=\"text\" class=\"normal\" id=\"").append(s).append("\" value=\"").append(s1).append("\">");
                break;
            }
        else
            switch(i) 
            {
            case 4: // '\004'
                boolean flag3 = UtilityObject.convertBoolean(s1);
                s1 = flag3 ? s2 : "&nbsp; &nbsp; &nbsp; &nbsp;";
                // fall through

            default:
                if(HTML_OPTION==2)
                {
                	if(s1.equals("-1"))
                	{
                	stringbuffer.append("");
                	}
                	else
                	{
                	stringbuffer.append(s1);
                	}
                }
                else
                {
                stringbuffer.append(s1);
            	}
                break;
            }
        return stringbuffer.toString(); 
    }

    public static final String CONDITION_VALUE = "N";
    public static final String CONDITION_NAME = "isComponentEditable";
    public static final int HTML_TEXT = 0;
    public static final int HTML_TEXTAREA = 1;
    public static final int HTML_OPTION = 2;
    public static final int HTML_LIST = 3;
    public static final int HTML_CHECKBOX = 4;
    public static final int HTML_RADIO = 5;
    public static final int HTML_RADIONA = 6;
    public static final int HTML_RADIOWASTE = 7;
    public static final int HTML_RADIOWASTYPE = 8;
    public static final String HTML_OPTION_YES_NO = "YESNO";
    
    public static final int HTML_OPTIONS = 9;
    public static final String HTML_OPTION_Internal_External="InternalExternal";
}