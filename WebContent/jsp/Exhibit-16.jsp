<%@ page language ="java" import = "com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.sql.*,Test.TestFacility,java.text.*" %> 
<html>
<head>
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>New Page 1</title>
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

SqlUtil utilObj = new SqlUtil();
String ss[]={"In service","Temporarily Out-of Service","Closed-Removed","Closed-In place","Converted to Non-Regulated Use"};

%>

<body>
<%String na="-";

java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber1" cellpadding="7" height="50">
  <tr>
    <td width="100%" height="53">
    <img border="0" src="images/pdf.gif" width="18" height="16">&nbsp;
    <a href="jsp/xls16.jsp">
    <img border="0" src="images/xls.gif" width="19" height="16"></a>
    <img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"><hr></td>
  </tr>
  <tr>
    <td width="100%" height="1">
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    <%
    String str="";
    try
{
Connection conn;
conn =utilObj.getConnection();
 
			PreparedStatement st=conn.prepareStatement("select clientname from facility where facilityid=?");
            st.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString(1);
            	
            	
            }
            }
            catch(Exception e)
            {
            }
            out.println(str);
    %></span></a><br>
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    Environmental Compliance Assessment Manual</span></a></td>
  </tr>
  <tr>
    <td width="100%" height="19">
    <p align="center"><b><font color="#01688A">EXHIBIT 16:LIST OF DISCONNECTED SOURCES FOR '<%=str.toUpperCase()%>' </font></b></td>
  </tr>
</table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="9">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <b><font size="2">BOILER</font></b></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn =utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.boilerid,d.buildingname,a.yearinstalled,a.DISCONNECTEDYEAR,a.make,a.model,a.capacity,a.stateid,a.dep,a.nycdob FROM boiler a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("boilerid"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("DISCONNECTEDYEAR"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%><%out.println("/MM BTU/HR");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("stateid"),"N/A")%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("dep"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("nycdob"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>
<br>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="9">
<tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></sub></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></sub></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></sub></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></sub></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></sub></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></sub></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></sub></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></sub></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></sub></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></sub></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></sub></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></sub></td>
  </tr>




   <tr>
    <td width="100%" height="18" colspan="12"><font size="2"><b>BRIDGE/TUNNEL</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.BRIDGETUNNELID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM bridgetunnels a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("BRIDGETUNNELID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>


</table>
<br>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="9">
<tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>

    <td width="100%" height="12" colspan="12"><b><font size="2">FUEL OIL/STORAGE 
    TANK</font></b></td>
  </tr>
  
<%
try
{
// get a database connection
Connection conn;

conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.TANKSTATUS,a.STORAGETANKID, a.facilitydesignatedid,d.buildingname,a.yearinstalled,a.capacity,a.DISCONNECTEDDATE,a.DOBAPPROVAL FROM storagetank a,building d,facility f WHERE (a.TANKSTATUS='2' or a.TANKSTATUS='3' or a.TANKSTATUS='4') AND d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
            	<tr>
    <td width="10%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("STORAGETANKID"),"N/A")%></font></td>
    <td width="8%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="11%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("DISCONNECTEDDATE"),"N/A")%></font></td>
    <td width="10%" height="9" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("TANKSTATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="9" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="9" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="9" align="right"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%><%out.println("/Gallons");%></font></td>
    <td width="6%" height="9" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="9" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("DOBAPPROVAL"),"N/A")%></font></td>
    <td width="5%" height="9" align="center"><font size="1"><%=na%></font></td>
  </tr>
            	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>


  
</table>


<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>GENERATOR</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.GENERATORID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity,a.dep,a.nycdob FROM generator a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("GENERATORID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%><%out.println("/MM BTU/HR");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("dep"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("nycdob"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>

<br>


<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="11" colspan="12" bordercolor="#000000">
    <font size="2"><b>PAINT SPRAY BOOTHS</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.SPRAYBOOTHID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model FROM spraybooth a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("SPRAYBOOTHID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>


<br>


<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Voc</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Tons/Yr</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>FUME HOODS</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn =utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.FUMEHOODID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.voc FROM fumehoods a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("FUMEHOODID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("voc"),"N/A")%><%out.println("Tons/Yr");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>
<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>LAND FILLS</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.LANDFILLSID,d.buildingname,a.yearinstalled,a.disconnecteddate FROM landfills a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("LANDFILLSID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>


<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>TURBINES</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn =utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.COGENTURBINEID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity FROM cogenturbine a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  a.resourcetype='2' and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("COGENTURBINEID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=(Double.parseDouble(checkNullAndFill(rs1.getString("capacity"),"0"))/100)%><%out.println("(MM BTU/Hr)");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>

<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>CO GEN ENGINES</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.COGENTURBINEID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity FROM cogenturbine a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  a.resourcetype='1' and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("COGENTURBINEID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=(Double.parseDouble(checkNullAndFill(rs1.getString("capacity"),"0"))/100)%><%out.println("(MM BTU/Hr)");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>
<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>INCINERATOR CREMATORIES</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.INCINERATORCREMATORIESID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity FROM incineratorcrematories a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("INCINERATORCREMATORIESID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnecteddate"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%><%out.println("(lb/hr)");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>

<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>ELEVATOR</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn =utilObj.getConnection();
 
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.ELEVATORID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM elevators a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
                        ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("ELEVATORID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>
<br>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <b>CHILLER ABSORBER</b></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.CHILLERABSORBERID,d.buildingname,a.yearinstalled,a.disconnectedyear,a.make,a.model,a.capacity FROM CHILLERABSORBER a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("CHILLERABSORBERID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%><%out.println("(lb/hr)");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>

<br>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Volume</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font color="#FFFFFF">(cubic ft.)</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>ETO</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn =utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.ETOID,d.buildingname,a.INSTALLATIONDATE,a.disconnectedyear,a.model,a.volume,a.dep,a.dob FROM ETO a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("ETOID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("INSTALLATIONDATE"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("volume"),"N/A")%><%out.println("(lb/hr)");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("dep"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("dob"),"N/A")%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>




<br>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
        <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>RPZ</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.RPZID,d.buildingname,a.yearinstalled,a.disconnectedyear,a.make,a.model,a.DOBPERMITNUMBER,a.DEPPERMITNUMBER FROM RPZ a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("RPZID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("model"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("a.DEPPERMITNUMBER"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("a.DOBPERMITNUMBER"),"N/A")%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>


<br>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>STACK</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.FACILITYSTACKID,a.STACKID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM STACK a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("FACILITYSTACKID"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("STACKID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>


<br>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>BULK STORAGE TANK</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;
conn =utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.BULKOXYGENTANKID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM BULKOXYGENTANK a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("BULKOXYGENTANKID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>


<br>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status Of Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    <td width="22%" align="center" bgcolor="#01688A" height="6" colspan="4">
    <font face="Times New Roman" size="2" color="#FFFFFF">Permit No.</font></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    <td width="7%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYSDEC</font></td>
    <td width="6%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDEP</font></td>
    <td width="5%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCDOB</font></td>
    <td width="4%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">NYCGD</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="12" bordercolor="#000000">
    <font size="2"><b>HYDRAULIC STORAGE TANK</b></font></td>
  </tr>
  <%
try
{
// get a database connection
Connection conn;

conn = utilObj.getConnection();
 
PreparedStatement st1=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.OTHERTANKID,d.buildingname,a.yearinstalled,a.disconnectedyear,a.capacity FROM hydraulicelevatorothertank a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("OTHERTANKID"),"N/A")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("yearinstalled"),"N/A")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("disconnectedyear"),"N/A")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs1.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%><%out.println("(lb/hr)");%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="6%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="5%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>





<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>


<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber3">
  <tr>
    <td width="100%" colspan="2"><hr></td>
  </tr>
  <tr>
    <td width="50%"><span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span></td>
    <td width="50%">
    <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">Environmental 
Engineering Solutions, P.C.</SPAN></td>
  </tr>
</table>

</body>

</html>