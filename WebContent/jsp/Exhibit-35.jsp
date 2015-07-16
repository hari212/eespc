<%@ page language ="java" import = "sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*,com.eespc.tracking.bo.FacilityVo,java.sql.*,java.text.*" %> 
<html>
<head>
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 35</title>
</head>
<%!
public static String checkNullAndFill(String _input, String _default)
    {
        if(isNotEmpty(_input) && !"null".equalsIgnoreCase(_input.trim()))
            return _input;
        else
            return _default;
    }
    public static boolean isNotEmpty(String _input)
    {
        return _input != null && _input.trim().length() > 0;
    }
    
    public static String checkNullAndFilldate(String _input, String _default)
    {
        if(isNotEmpty(_input) && !"null".equalsIgnoreCase(_input.trim()))
        {
        	
        String dd[]=_input.split("-");
    	String dat=dd[1]+"/"+dd[2]+"/"+dd[0];

            return dat;
        }
        else
            return _default;
    }
%>

<%
FacilityVo facilityvo= (FacilityVo)session.getAttribute("REPORT_FACILITY_VO");
SqlUtil utilObj = new SqlUtil();
String ss[]={"In service","Temporarily Out-of Service","Closed-Removed","Closed-In place","Converted to Non-Regulated Use"};

%>

<body>
<%String na="N/A";

java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" cellpadding="7" height="166">
  <tr>
    <td width="100%" height="43">
    <img border="0" src="images/pdf.gif" width="18" height="16">&nbsp;
    <a href="jsp/Exhibit-35xls.jsp">
    <img border="0" src="images/xls.gif" width="19" height="16"></a>
    <img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"><hr></td>
  </tr>
  <tr>
    <td width="100%" height="31">
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    <%
    String str="";
    Connection conn= null;            
    PreparedStatement st= null;
    ResultSet rs = null;
    try
{
			conn =utilObj.getConnection(); 
			st=conn.prepareStatement("select clientname from facility where facilityid=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString(1);          	
            }
            }
            catch(Exception e)
            {
            out.println("Facility Exception :"+e);
            } finally
        	{
        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}

            out.println(str);
    %></span></a><br>
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    EESPC Compliance Tracking Software (C)</span></a></td>
  </tr>
  <tr>
    <td width="100%" height="30">
    <p align="center"><font color="#01688A"><b>REPORT</b></font><b><font color="#01688A"> 
    35:LIST OF VIOLATION AND FINAL COMPLIANCE STATUS FOR '<%=str.toUpperCase()%>' </font></b></td>
  </tr>
  
   <tr>
    <td width="100%" height="2">
     <hr color="#000000"></td>
  </tr>

 

</table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
  <tr>
    <td width="9%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">S.No</font></b></td>
    <td width="6%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Violation Number</font></b></td>
    <td width="6%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Issuing Agency</font></b></td>    
    <td width="9%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Violation Type</font></b></td>
    <td width="13%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Building Name</font></b></td>
    <td width="7%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Issue Date</font></b></td>
    <td width="9%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Corrective Action Taken</font></b></td>
    <td width="8%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Violation RemovalDate</font></b></td>
    <td width="9%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Intermediate Status</font></b></td>
   <td width="11%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Violation Hearing Date</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Final Compliance Date</font></b></td>
    <td width="12%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  
  <%
  					List violationList=fromseventh.getNycdobStatusReport35(facilityvo.getId());
                    int psize =0;
					Hashtable hashtable= new Hashtable();
					if (violationList!= null)
					{
						psize = violationList.size();
						for (int i=0; i < psize; i++)
						{
						hashtable=(Hashtable)violationList.get(i);
						
						
				
  %>
  
  <tr>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(i+1)%>&nbsp;</font></td>
    <td width="6%" align="center" valign="top"><font face="Verdana" size="2"><a href="<%=request.getContextPath()%>/clientfolder_global/<%=facilityvo.getDecId()%>/Violation/<%=(String)hashtable.get("VIOLATIONNO")%>.pdf"><%=(String)hashtable.get("VIOLATIONNO")%>&nbsp;</a></font></td>
    <td width="6%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("VIOLATIONWHICH")%>&nbsp;</font></td>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("VIOLATIONTYPE")%>&nbsp;</font></td>
    <td width="13%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("BUILDINGNAME")%>&nbsp;</font></td>
    <td width="7%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("ISSUEDATE")%>&nbsp;</font></td>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("STEPSTAKEN")%>&nbsp;</font></td>
    <td width="8%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("REMOVALDATE")%>&nbsp;</font></td>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("INTERMEDIATESTATUS")%>&nbsp;</font></td>

<td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("JDATE")%>&nbsp;</font></td>

     <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("FINALCOMPLIANCEDATE")%>&nbsp;</font></td>
    <td width="12%" align="center" valign="top"><font face="Verdana" size="2"><% String comp=(String)hashtable.get("COMPLIANCESTATUS");
    if(comp.equals("Non Compliance"))
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}
else
{
out.println(comp);
}
%>&nbsp;</font></td>
  </tr>
  <%
  	}
					}

  %>

    
</table>

<br>
<hr color="#000000">
<table width="100%" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0" height="16">
<tr><td width="602" height="16">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
 </td>
 <td width="441">
  <p align="center"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  <img border="0" src="images/ees.JPG" width="20" height="18" align="right"></SPAN>
 </td>
 
 <td width="199">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  Environmental 
Engineering Solutions, P.C.</SPAN></td>
 
 </tr>
</table>