<%@ page contentType="application/vnd.ms-excel;
    charset=UTF-8" %>
    
<%@ page language ="java" import = "com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*,com.eespc.tracking.bo.FacilityVo,java.sql.*,java.text.*" %> 
<html>
<head>
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 30</title>
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
Connection conn= null;            
PreparedStatement st= null;
ResultSet rs = null;
%>
<%String na="N/A";

java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber1" cellpadding="7" height="50">
 
  <tr>
    <td width="100%" height="1">
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    <%
    String str="";
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
    <td width="100%" height="19">
    <p align="center"><font color="#01688A"><b>REPORT</b></font><b><font color="#01688A"> 
    30:LIST OF DISCONNECTED SOURCES FOR '<%=str.toUpperCase()%>' </font></b></td>
  </tr>
</table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>

  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
 
  </tr>
  <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <b><font size="2">BOILER</font></b></td>
  </tr>
  <%
try
{
// get a database connection
	conn =utilObj.getConnection();
 	st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.boilerid,d.buildingname,a.yearinstalled,a.DISCONNECTEDYEAR,a.make,a.model,a.capacity,a.stateid,a.dep,a.nycdob FROM boiler a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
    st.setString(1,String.valueOf(facilityvo.getId()));
    rs=st.executeQuery();
    int rssize=0;
          
	while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("boilerid"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("DISCONNECTEDYEAR"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("capacity"),"-")%><%out.println("/MM BTU/HR");%></font></td>
  </tr>
  	<%            	
            }
   
          
 if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 }           
 }
catch(Exception e)
{
out.println("Boiler Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);        
			}
	

%>

</table>
<p>
<br>
</p>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="4">
<tr>
    <td width="10%" align="center" bgcolor="#01688A" height="7" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></sub></td>
    <td width="8%" align="center" bgcolor="#01688A" height="7" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></sub></td>
    <td width="11%" align="center" bgcolor="#01688A" height="7" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></sub></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <sub><font face="Times New Roman" size="2" color="#FFFFFF">Year</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="7" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of Source</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="7" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></sub></td>
    <td width="11%" align="center" bgcolor="#01688A" height="7" rowspan="2">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></sub></td>
  
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="1">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></sub></td>
    <td width="9%" align="center" bgcolor="#01688A" height="1">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></sub></td>
    <td width="10%" align="center" bgcolor="#01688A" height="1">
    <sub>
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></sub></td>
  </tr>
  <tr>
    <td width="100%" height="18" colspan="9"><font size="2"><b>BRIDGE/TUNNEL</b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.BRIDGETUNNELID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM bridgetunnels a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
            int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("BRIDGETUNNELID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
  </tr>
  	<%            	
            }
            
if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 }     

}
catch(Exception e)
{
out.println("Bridge Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);        
			}
	
%>


</table>
<p>
<br>
</p>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="9">
<tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
   
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
  </tr>

    <td width="100%" height="12" colspan="9"><b><font size="2">STORAGE TANK</font></b></td>
  </tr>
  
<%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.TANKSTATUS,a.STORAGETANKID, a.facilitydesignatedid,d.buildingname,a.yearinstalled,a.capacity,a.DISCONNECTEDDATE,a.DOBAPPROVAL FROM storagetank a,building d,facility f WHERE (a.TANKSTATUS='2' or a.TANKSTATUS='3' or a.TANKSTATUS='4') AND d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
            int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;

            	
            	%>
            	
            	<tr>
    <td width="10%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs.getString("STORAGETANKID"),"-")%></font></td>
    <td width="8%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="11%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs.getString("DISCONNECTEDDATE"),"-")%></font></td>
    <td width="10%" height="9" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("TANKSTATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="9" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="9" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="9" align="center"><font size="1"><%=checkNullAndFill(rs.getString("capacity"),"-")%><%out.println("/Gallons");%></font></td>
 
  </tr>
            	<%            	
            }
            
 if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 }    

}
catch(Exception e)
{
out.println("Storage tank :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
	
%>


  
</table>


<p>


<br>

</p>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
   
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
  
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>GENERATOR</b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.GENERATORID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity,a.dep,a.nycdob FROM generator a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("GENERATORID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnecteddate"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("capacity"),"-")%><%out.println("/MM BTU/HR");%></font></td>

  </tr>
  	<%            	
            }
            
 
 if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 
}
catch(Exception e)
{
out.println("Generator Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
	
%>

</table>

<p>

<br>


</p>


<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id </font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
   
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    
  </tr>
  <tr>
    <td width="100%" height="11" colspan="9" bordercolor="#000000">
    <font size="2"><b>PAINT SPRAY BOOTH</b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.SPRAYBOOTHID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model FROM spraybooth a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("SPRAYBOOTHID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnecteddate"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
  
  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Spray Booth :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>


<p>


<br>


</p>


<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Voc</font></td>
 
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Tons/Yr</font></td>
  
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>FUME HOOD</b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn =utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.FUMEHOODID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.voc FROM fumehoods a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("FUMEHOODID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnecteddate"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("voc"),"-")%><%out.println("Tons/Yr");%></font></td>

  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Fume Hood Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
	
%>

</table>
<p>
<br>

</p>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
  
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
    
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>LAND FILL</b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.LANDFILLSID,d.buildingname,a.yearinstalled,a.disconnecteddate FROM landfills a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and  d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("LANDFILLSID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnecteddate"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>

  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Land Fill Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>


<p>


<br>

</p>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
 
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
  
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>CO GEN ENGINE</b></font></td>
  </tr>
  <%
try
{
// get a database connection

conn =utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.COGENTURBINEID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity FROM cogenturbine a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and   d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("COGENTURBINEID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnecteddate"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=(Double.parseDouble(checkNullAndFill(rs.getString("capacity"),"0"))/100)%><%out.println("(MM BTU/Hr)");%></font></td>

  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Co gen Engine Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>

<p>

<br>

<br>

</p>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>INCINERATOR CREMATORY</b></font></td>
  </tr>
  <%
try
{
// get a database connection

conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.facilitydesignatedid,a.INCINERATORCREMATORIESID,d.buildingname,a.yearinstalled,a.disconnecteddate,a.make,a.model,a.capacity FROM incineratorcrematories a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("INCINERATORCREMATORIESID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnecteddate"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("capacity"),"-")%><%out.println("(lb/hr)");%></font></td>

  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Incinerator Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>

<p>

<br>

</p>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>

  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
   

  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>ELEVATOR</b></font></td>
  </tr>
  <%
try
{
// get a database connection

conn =utilObj.getConnection();
 
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.ELEVATORID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM elevators a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
                        rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("ELEVATORID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
   
  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Elevator Exception :"+e);
}	 finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}

%>

</table>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>
<p>
<br>

</p>

<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Date</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
   
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <b><font size="2">CHILLER ABSORBER</font></b></td>
  </tr>
  <%
try
{
// get a database connection

conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.CHILLERABSORBERID,d.buildingname,a.yearinstalled,a.disconnectedyear,a.make,a.model,a.capacity FROM CHILLERABSORBER a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("CHILLERABSORBERID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("capacity"),"-")%><%out.println("(lb/hr)");%></font></td>

  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Chiller Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
	
%>

</table>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>

<p>

<br>
</p>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Volume</font></td>
    
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font color="#FFFFFF">(cubic ft.)</font></td>
   
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>ETO</b></font></td>
  </tr>
  <%
try
{
// get a database connection

conn =utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.ETOID,d.buildingname,a.INSTALLATIONDATE,a.disconnectedyear,a.model,a.volume,a.dep,a.dob FROM ETO a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("ETOID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFilldate(rs.getString("INSTALLATIONDATE"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("volume"),"-")%><%out.println("(lb/hr)");%></font></td>
  
  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Eto Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>




<p>




<br>
</p>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
        <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
   
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>

  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>RPZ</b></font></td>
  </tr>
  <%
try
{
// get a database connection

conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.RPZID,d.buildingname,a.yearinstalled,a.disconnectedyear,a.make,a.model,a.DOBPERMITNUMBER,a.DEPPERMITNUMBER FROM RPZ a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	%>
            	
           
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("RPZID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("make"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("model"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>

  </tr>
  	<%            	
            }
            
 
 if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 
}
catch(Exception e)
{
out.println("RPZ Exception :"+e);
}	 finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}

%>

</table>


<p>


<br>
</p>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
    
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>BULK STORAGE TANK</b></font></td>
  </tr>
  <%
try
{
// get a database connection

conn =utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.BULKOXYGENTANKID,d.buildingname,a.yearinstalled,a.disconnectedyear FROM BULKOXYGENTANK a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("BULKOXYGENTANKID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    
  </tr>
  	<%            	
            }
            
 
 if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 
}
catch(Exception e)
{
out.println("Bulk Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>


<p>


<br>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
   
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
   
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>CHILLER WITH AIRCONDITIONING REFRIGERATION </b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.STATUS,a.FACILITYDESIGNATEDID,a.CHILLERREFRIGATIONID,a.MAKE,a.MODEL,d.buildingname,a.YEARINSTALLED,a.disconnectedyear,a.CAPACITYTONS FROM chillerrefrigation a,building d,facility f WHERE (a.STATUS='2' or a.STATUS='3' or a.STATUS='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
            int rssize=0;          
            while(rs.next())
            {            
            rssize=rssize+1;	
          
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("FACILITYDESIGNATEDID"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("CHILLERREFRIGATIONID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("YEARINSTALLED"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("STATUS"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("MAKE"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("MODEL"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("CAPACITYTONS"),"-")%><%out.println("(TONS)");%></font></td>
   
  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("CH REF Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
			}
	
%>

</table>

<br>



<table>
<tr>
    <td width="100%" height="1">
   
    </td>
  </tr>

</table>

<br>

</p>
<table>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="850" id="AutoNumber2" height="1">
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Equipment Id</font></td>
    <td width="8%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">No:</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Location<br>Building:</font></td>
    <td width="18%" align="center" bgcolor="#01688A" height="6" colspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Year</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Status of&nbsp; Source</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Make</font></td>
    <td width="11%" align="center" bgcolor="#01688A" height="12" rowspan="2">
    <font face="Times New Roman" size="2" color="#FFFFFF">Model</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Capacity</font></td>
   
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Install</font></td>
    <td width="9%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Removal</font></td>
    <td width="10%" align="center" bgcolor="#01688A" height="6">
    <font face="Times New Roman" size="2" color="#FFFFFF">Rate/Unit</font></td>
   
  </tr>
  <tr>
    <td width="100%" height="14" colspan="9" bordercolor="#000000">
    <font size="2"><b>HYDRAULIC STORAGE TANK</b></font></td>
  </tr>
  <%
try
{
// get a database connection
conn = utilObj.getConnection();
 
st=conn.prepareStatement("SELECT a.MODIFIEDINPAST,a.facilitydesignatedid,a.OTHERTANKID,d.buildingname,a.yearinstalled,a.disconnectedyear,a.capacity FROM hydraulicelevatorothertank a,building d,facility f WHERE (a.MODIFIEDINPAST='2' or a.MODIFIEDINPAST='3' or a.MODIFIEDINPAST='4') and d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
             int rssize=0;
          
            while(rs.next())
            {
            rssize=rssize+1;	
            	
            	
            	%>
            	
      

  <tr>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("facilitydesignatedid"),"-")%></font></td>
    <td width="8%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("OTHERTANKID"),"-")%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("buildingname"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("yearinstalled"),"-")%></font></td>
    <td width="9%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("disconnectedyear"),"-")%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=ss[(Integer.parseInt(checkNullAndFill(rs.getString("MODIFIEDINPAST"),"2"))-1)]%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="11%" height="33" align="center"><font size="1"><%=na%></font></td>
    <td width="10%" height="33" align="center"><font size="1"><%=checkNullAndFill(rs.getString("capacity"),"-")%><%out.println("(lb/hr)");%></font></td>
   
  </tr>
  	<%            	
            }
            
  if(rssize==0)
     {     
            
     %>
    <tr>
    <td width="100%" height="5" colspan="9" bordercolor="#000000">
    <p align="center">
    <b><font size="2">No Results</font></b></td>
  </tr>   
     
     
<%       
 } 

}
catch(Exception e)
{
out.println("Hyd Exception :"+e);
} finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
	
%>

</table>

<p>

<br>



</p>



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