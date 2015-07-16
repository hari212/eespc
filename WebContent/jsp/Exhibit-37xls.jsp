<%@ page contentType="application/vnd.ms-excel;
    charset=UTF-8" %>
<%@ page language ="java" import = "sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*,com.eespc.tracking.bo.FacilityVo,java.sql.*,java.text.*" %> 
<html>
<head>
<meta name="PA" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 37</title>
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
    <a href="jsp/Exhibit-37xls.jsp">
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
    37:LIST OF PLACE OF PUBLIC ASSEMBLY &amp; AGENCY APPROVALS FOR '<%=str.toUpperCase()%>' </font></b></td>
  </tr>
  
</table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
  <tr>
    <td width="9%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">ID</font></b></td>
    <td width="6%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">PA Type</font></b></td>
	<td width="6%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">Building</font></b></td>
    <td width="6%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">Location/Floor</font></b></td>    
    <td width="9%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">Seating Capacity</font></b></td>
    <td width="13%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">DOB Filing/Job No.</font></b></td>
    <td width="7%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">DOB Approved Plan (Y/N)</font></b></td>
    <td width="9%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">PA DOB Permit No.</font></b></td>
    <td width="8%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">FD Approval (Y/N)</font></b></td>
    <td width="9%" align="center" bgcolor="#01688A"><b>
	<font face="Verdana" size="2" color="#FFFFFF">Associated Violations</font></b></td>
    <td width="12%" align="center" bgcolor="#01688A"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  
  <%
  					List paList=fromseventh.getNycdobStatusReport37(facilityvo.getId());
                    int psize =0;
					Hashtable hashtable= new Hashtable();
					if (paList!= null)
					{
						psize = paList.size();
						for (int i=0; i < psize; i++)
						{
						hashtable=(Hashtable)paList.get(i);									
  %>
  
  <tr>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("FACILITYDESIGNATEDID")%>&nbsp;</font></td>
    <td width="6%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("PATYPE")%>&nbsp;</font></td>
    <td width="6%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("BUILDINGNAME")%>&nbsp;</font></td>
    <td width="6%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("LOCATION")%>&nbsp;</font></td>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("SEATINGCAPACITY")%>&nbsp;</font></td>
    <td width="13%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("DOBPERMIT")%>&nbsp;</font></td>
    <td width="7%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("DOBPLAN")%>&nbsp;</font></td>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("DOBPERMITNUMBER")%>&nbsp;</font></td>
    <td width="8%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("FDPERMITOBTAINED")%>&nbsp;</font></td>
    <td width="9%" align="center" valign="top"><font face="Verdana" size="2"><%=(String)hashtable.get("VIOLATIONNUM")%>&nbsp;</font></td>  
    <td width="12%" align="center" valign="top"><font face="Verdana" size="2"><% String comp=(String)hashtable.get("COMPLIANCESTATUS");
    if(comp.equals("Non Compliance"))
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}
else if(comp.equals("TBD"))
{
out.println("<font color=\"#000000\">TBD</font>");
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
<table width="100%"  style="border: 4px solid transparent;"  cellpadding="0" cellspacing="0" height="10">
<tr><td>
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
 </td>
 <td>
  <p align="center"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  <img border="0" src="images/ees.JPG" width="25" height="15" align="right"></SPAN>
 </td>
 
 <td  width="215">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 10px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  Environmental Engineering Solutions, P.C.</SPAN></td>
 
 </tr>
</table>