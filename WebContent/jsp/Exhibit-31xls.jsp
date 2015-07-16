<%@ page contentType="application/vnd.ms-excel;
    charset=UTF-8" %>

<html>
<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="com.eespc.tracking.reports.datasources.factories.*,sevenb.fromseventh,java.sql.*,java.text.*,java.util.*,com.eespc.tracking.bo.ElevatorPermitVo,com.eespc.tracking.bo.FacilityVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.SqlUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.entity.*" %>
<%
String dep="";
String dob="";
FacilityVo facilityvo = (FacilityVo)session.getAttribute("REPORT_FACILITY_VO");
try
{


int borough=facilityvo.getBorough();
if(borough==1)
{
dep="DEP/DOH";
dob="DOB/TOWN/CITY";
}
else
{
dep="DEP";
dob="DOB";
}

}
catch(Exception e)
{
out.println("Eyxception:"+e);
}
%>

<%
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");



%>


<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>REPORT 31</title>
</head>
<p align="left"><img border="0" src="images/pdf.gif" width="16" height="16">
<a href="jsp/Exhibit-31xls.jsp" target="_blank"><img border="0" src="images/xls.gif" width="16" height="16"></a>&nbsp;&nbsp;
<img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"></p>

<span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">	
<%

    String str="";
    Connection conn= null;            
    PreparedStatement st= null;
    ResultSet rs = null;
    try {
			conn = utilObj.getConnection(); 
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
    %>	
    </span> <br>
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    EESPC Compliance Tracking Software (EESCTS)</span>
    <br>

<body>
<p align="center"><font color="#01688A"><b>EXHIBIT 31: MASTER REPORT &nbsp; FOR '<%=str.toUpperCase()%>'</b></font><hr>

  <%
  Exhibit1DataSourceFactory ex1=new Exhibit1DataSourceFactory();  
  List exhibit1=(List)ex1.getlist(facilityvo.getId()); 
  if (exhibit1 != null && exhibit1.size()>0)
    {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
  <tr>
    <td width="100%" colspan="11">
    <p align="center"><b><font face="Verdana" color="#006699" size="2">REPORT 
    1:LIST OF BUILDINGS OPERATED BY '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="3%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">No.</font></b></td>
    <td width="19%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Building Name</font></b></td>
    <td width="14%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Address</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">BIN</font></b></td>
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Block No.</font></b></td>
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">LOT No.</font></b></td>
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">No. of&nbsp;Stories</font></b></td>
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Facade Info</font></b></td>    
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">C Of O Availability Status</font></b></td>
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">(C OF O) No</font></b></td>
    <td width="12%" align="center" bgcolor="#006699"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit1.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit1.get(i);
								
  %>
  <tr>
    <td width="3%" align="center"><%=(i+1)%>&nbsp;</td>
    <td width="19%" align="left"><%=hashtable.get("BUILDINGNAME")%>&nbsp;</td>
    <td width="14%" align="left"><%=hashtable.get("bldgAddress")%>&nbsp;</td>
    <td width="8%" align="center"><%=hashtable.get("DOBBINNUMBER")%>&nbsp;</td>
    <td width="11%" align="center"><%=hashtable.get("BLOCKNUMBER")%>&nbsp;</td>
    <td width="11%" align="center"><%=hashtable.get("LOTNUMBER")%>&nbsp;</td>
    <td width="11%" align="center"><%=hashtable.get("BLDGSIXSTORIES")%>&nbsp;</td>
    <td width="11%" align="center"><%=hashtable.get("FACADEINFO")%>&nbsp;</td>    
    <td width="11%" align="center"><%=hashtable.get("COFO")%>&nbsp;</td>
    <td width="11%" align="center"><%=hashtable.get("COFONO")%>&nbsp;</td>
    <td width="12%" align="center"><%=hashtable.get("COMPLIANCESTATUS")%>&nbsp;</td>
  </tr>
  <%
  	}
  		}
 %>
</table>
<p> </p>
<%
  Exhibit2DataSourceFactory ex2=new Exhibit2DataSourceFactory();  
  List exhibit2=(List)ex2.getData(facilityvo.getId()); 
  if (exhibit2 != null && exhibit2.size()>0)
					{
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
  <tr>
    <td width="1154" colspan="21">
    <p align="center"><b><font color="#006699" face="Verdana" size="2">REPORT 2: LIST OF EQUIPMENT ASSOCIATED WITH EACH BUILDING 
    OPERATED BY '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="39" rowspan="2" bgcolor="#006699">
    <b>
    <font face="Verdana" color="#FFFFFF" size="2">No.</font></b></td>
    <td width="154" rowspan="2" bgcolor="#006699" align="center">
    <b>
    <font face="Verdana" color="#FFFFFF" size="2">Building Name</font></b></td>
    <td width="67" rowspan="2" bgcolor="#006699" align="center">
    <b>
    <font face="Verdana" color="#FFFFFF" size="2">Address</font></b></td>
    <td width="1008" colspan="18" bgcolor="#006699" align="center">
    <p align="center"><b><font face="Verdana" color="#FFFFFF" size="2">List of 
    Sources In Respective Building</font></b></td>
  </tr>
  <tr>
    <td width="50" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Elevator</font></b></td>
    <td width="53" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Boiler</font></b></td>
    <td width="101" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Chiller/Absorber</font></b></td>
    <td width="79" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Generator</font></b></td>
    <td width="56" bgcolor="#006699" align="center">
    <p align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">RPZ</font></b></td>
    <td width="146" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Storage Tanks</font></b></td>
    <td width="54" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Stacks</font></b></td>
    <td width="126" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Paint Spray</font></b></td>
    <td width="134" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Bulk Oxygen</font></b></td>
    <td width="121" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Fume Hoods</font></b></td>
    <td width="215" bgcolor="#006699" align="center">
    <p align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Hydraulic Storage&nbsp; Tanks</font></b></p>
    </td>
    <td width="96" bgcolor="#006699"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Bridges/Tunnels</font></b></td>
    <td width="149" bgcolor="#006699"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Co Gen&nbsp; Engines</font></b></td>
    <td width="142" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Printing Press</font></b></td>
    <td width="38" bgcolor="#006699" align="center"><b>
    <font face="Verdana" color="#FFFFFF" size="2">Landfill </font></b></td>
    <td width="25" bgcolor="#006699" align="center"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Incinerator/Crematories</font></b></td>
    <td width="42" bgcolor="#006699" align="center"><b>
    <font face="Verdana" size="2" color="#FFFFFF">ETO</font></b></td>
    <td width="43" bgcolor="#006699" align="center"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Miscellaneous</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit2.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit2.get(i);
								
  %>   
  <tr>
    <td width="39"><%=hashtable.get("NO")%>&nbsp;</td>
    <td width="154"><%=hashtable.get("BUILDING_NAME")%>&nbsp;</td>
    <td width="67"><%=hashtable.get("BUILDING_ADDRESS")%>&nbsp;</td>
    <td width="50"><%=hashtable.get("ELEVATOR")%>&nbsp;</td>
    <td width="53"><%=hashtable.get("BOILER")%>&nbsp;</td>
    <td width="101"><%=hashtable.get("CHILLER")%>&nbsp;</td>
    <td width="79"><%=hashtable.get("GENERATOR")%>&nbsp;</td>
    <td width="56"><%=hashtable.get("RPZ")%>&nbsp;</td>
    <td width="146"><%=hashtable.get("STORAGE_TANK")%>&nbsp;</td>
    <td width="54"><%=hashtable.get("STACK")%>&nbsp;</td>
    <td width="126"><%=hashtable.get("PAINT_SPRAY")%>&nbsp;</td>
    <td width="134"><%=hashtable.get("BULK_OXYGEN_STORAGE_TANK")%>&nbsp;</td>
    <td width="121"><%=hashtable.get("FUMEHOOD")%>&nbsp;</td>
    <td width="215"><%=hashtable.get("HYDRAULIC_STORAGE_TANK")%>&nbsp;</td>
    <td width="96"><%=hashtable.get("BRIDGE")%>&nbsp;</td>
    <td width="149"><%=hashtable.get("COGEN")%>&nbsp;</td>
    <td width="142" align="center"><%=hashtable.get("PRESS")%>&nbsp;</td>
    <td width="38" align="center"><%=hashtable.get("LANDFILL")%>&nbsp;</td>
    <td width="25" align="center"><%=hashtable.get("INCINERATOR")%>&nbsp;</td>
    <td width="42" align="center"><%=hashtable.get("ETO")%>&nbsp;</td>
    <td width="43" align="center"><%=hashtable.get("OTHERS")%>&nbsp;</td>
  </tr>
   <%
  	}
  		}
  		  %>
</table>
<p> </p>
<%
  BoilerEntity boilerentity=new BoilerEntity(); 
  List exhibit3=(List)boilerentity.getResultForReport(facilityvo.getId()); 
  if (exhibit3 != null && exhibit3.size()>0)
					{
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="30">
  <tr>
    <td width="100%" colspan="20" height="18">
    <p align="center"><b><font color="#006699" face="Verdana" size="2">REPORT 3: LIST OF 
    BOILERS AND <%=dep%> PERMIT STATUS FOR '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="5%" align="center" height="44" bgcolor="#006699" rowspan="2"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id</font></b></td>
    <td width="11%" align="center" height="44" bgcolor="#006699" rowspan="2"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Manufacturer</font></b></td>
    <td width="4%" align="center" height="44" bgcolor="#006699" rowspan="2">
    <font color="#FFFFFF" face="Verdana"><b><font size="2">&nbsp;</font></b><span style="font-family: Times-Roman; font-size: 13px; font-weight: bold; background-color: rgb(1, 104, 138)">Year<br>
    Installed</span></font></td>
    <td width="4%" align="center" height="44" bgcolor="#006699" rowspan="2">
    <font color="#FFFFFF" face="Verdana">
    <span style="font-family: Times-Roman; font-size: 13px; font-weight: bold; background-color: rgb(1, 104, 138)">
    Make</span></font></td>
    <td width="6%" align="center" height="44" bgcolor="#006699" rowspan="2">
    <font color="#FFFFFF" face="Verdana">
    <span style="font-family: Times-Roman; font-size: 13px; font-weight: bold; background-color: rgb(1, 104, 138)">
    Model</span></font></td>
    <td width="7%" align="center" height="44" bgcolor="#006699" rowspan="2">
    <font color="#FFFFFF" face="Verdana">
    <span style="font-family: Times-Roman; font-size: 13px; font-weight: bold; background-color: rgb(1, 104, 138)">
    Serial No </span></font></td>
    <td width="10%" colspan="2" height="19" bgcolor="#006699">
    <p align="center"><font color="#FFFFFF" size="2" face="Verdana">
    <span style="font-family: Times-Roman; font-weight: bold; background-color: rgb(1, 104, 138)">
    Burner</span></font></td>
    <td width="4%" height="44" bgcolor="#006699" rowspan="2" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="18%" height="19" bgcolor="#006699" colspan="3" align="center">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Maximum 
    Capacity</font></b></td>
    <td width="12%" height="19" bgcolor="#006699" colspan="2" align="center">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Fuel</font></b></td>    
    <td width="18%" height="19" bgcolor="#006699" colspan="3" align="center">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Compliance with NYC <%=dob%></font></b></td>
    <td width="12%" height="19" bgcolor="#006699" colspan="2" align="center">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF"><%=dep%></font></b></td>    
    <td width="3%" height="44" bgcolor="#006699" rowspan="2" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance<br>Status</font></b></td>
    
  </tr>
  <tr>
    <td width="6%" height="25" align="center" bgcolor="#006699">
    <font color="#FFFFFF" face="Verdana">
    <span style="font-family: Times-Roman; font-size: 13px; font-weight: bold; background-color: rgb(1, 104, 138)">
    Make</span></font></td>
    <td width="6%" height="25" align="center" bgcolor="#006699">
    <font color="#FFFFFF" face="Verdana">
    <span style="font-family: Times-Roman; font-size: 13px; font-weight: bold; background-color: rgb(1, 104, 138)">
    Model</span></font></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">MM BTU/HR</font></b></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">OIL<br>GPH</font></b></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">NG CFH</font></b></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Primary</font></b></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Secondary</font></b></td>    
     <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Filing/Job No.</font></b></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Sign Off(Y/N)</font></b></td>
	<td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>    
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Permit No</font></b></td>
    <td width="6%" height="25" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Expiration Date</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit3.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit3.get(i);
								
  %>

  <tr>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("facilitydesinatedid")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("manufacturer")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("yearinstalled")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("make")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("model")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("serial")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("burnermake")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("burnermodel")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">
    <p align="left">&nbsp;<%=hashtable.get("buildingname")%></td>
    <td width="6%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("capacity")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("oilFiringRate")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("naturalGasFiringRate")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("primaryFuel")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("secondaryFuel")%></td>    
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("NYCDOB")%></td>    
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("dobcompliancestatus")%></td>    
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("dep")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("expirationdate")%></td>
    <td width="5%" height="3" bgcolor="#FFFFFF" align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>

  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </p>
<%

  List exhibit4=(List)boilerentity.getNycdobStatusReport(facilityvo.getId()); 
  if (exhibit4 != null && exhibit4.size()>0)
					{
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber4">
  <tr>
    <td width="100%" colspan="9" align="center"><b>
    <font face="Verdana" color="#006699" size="2">REPORT 4: LIST OF BOILERS AND <%=dob%> 
    ANNUAL INSPECTION STATUS FOR '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="9%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id</font></b></td>
    <td width="9%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Manufacturer</font></b></td>
    <td width="9%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location </font></b></td>    
    <td width="9%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">MEA No</font></b></td>
    <td width="18%" colspan="2" bgcolor="#006699">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Annual 
    Inspection Date</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#006699">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Report 
    Submitted (Y/N)</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Last</font></b></td>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Next</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit4.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit4.get(i);
								
  %>

  <tr>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("facilitydesinatedid")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("manufacturer")%></td>
    <td width="9%" align="center">
    <p align="left">&nbsp;<%=hashtable.get("location")%></td>  
    <td width="9%" align="center">&nbsp;<%=hashtable.get("internalId")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("annualLast")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("annualNext")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("testingSubmitted")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>
  </tr>
  
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%

  List exhibit5=(List)boilerentity.getAnnualTuneUpReport(facilityvo.getId()); 
  if (exhibit5 != null && exhibit5.size()>0)
					{
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="101%" id="AutoNumber5">
  <tr>
    <td width="100%" colspan="10">
    <p align="center"><b><font face="Verdana" color="#006699" size="2">REPORT 5: LIST OF 
    BOILERS ANNUAL TUNE-UP AND STACK TEST REPORTING SUMMARY FOR '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">No.</font></b></td>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Boilers</font></b></td>
    <td width="13%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td>
    <td width="12%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Annual Tune-up Performed</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Date Performed</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Tune-Up Company</font></b></td>    
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DEC Stack Test Required</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Test Report Submitted to State Agency</font></b></td>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Next Stack Test Date</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance 
    Status</font></b></td>
  </tr>
				<%	  
                     	int psize =0; 
						psize = exhibit5.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit5.get(i);
								
  %>

  <tr>
    <td width="6%">&nbsp;<%=(i+1)%></td>
    <td width="9%">&nbsp;<%=hashtable.get("manufacturer")%></td>
    <td width="13%">&nbsp;<%=hashtable.get("location")%></td>
    <td width="12%">&nbsp;<%=hashtable.get("BOILERTUNEUP")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("DATEPERFORMED")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("performedby")%></td>   
    <td width="11%">&nbsp;<%=hashtable.get("stacktestdate")%></td>    
    <td width="10%">&nbsp;<%=hashtable.get("reportSubmitted")%></td>     
    <td width="9%">&nbsp;<%=hashtable.get("expirationdate")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("compliancestatus")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  GeneratorEntity generatorentity=new GeneratorEntity();  
  List exhibit6=(List)generatorentity.getNycdepReport(facilityvo.getId()); 
  if (exhibit6 != null && exhibit6.size()>0)
					{
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="115%" id="AutoNumber6">
  <tr>
    <td colspan="22">
    <p align="center"><b><font face="Verdana" color="#006699" size="2">REPORT 6: LIST OF 
    EMERGENCY GENERATOR&nbsp; AND AGENCY PERMIT STATUS '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="2%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id</font></b></td>
    <td width="9%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Manufacturer</font></b></td>
    <td width="4%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Model</font></b></td>
    <td width="4%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Serial No</font></b></td>
    <td width="6%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Year Installed&nbsp;</font></b></td>
    <td colspan="4" bgcolor="#006699">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Maximum 
    Capacity</font></b></td>
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Fuel Type</font></b></td>    
    <td colspan="2" bgcolor="#006699">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF"><%=dep%></font></b></td>    
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">DRP Participation</font></b></td>
    <td colspan="4" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">COMPLIANCE WITH THE STATE</font></b></td> 
    
     <td colspan="3" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance With NYC <%=dob%></font></b></td>    
    
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%> Compliance Status</font></b></td>

  </tr>
  <tr>
    <td width="6%" bgcolor="#006699">
    <p align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">MM BTU/HR</font></b></td>
    <td width="2%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">HP</font></b></td>
    <td width="2%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">KW</font></b></td>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">GPH</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Permit No</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Expiration Date</font></b></td>    
    <td width="4%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Stack Test</font></b></td>
    <td width="4%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Last Test Date</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Next Test Date</font></b></td>    
    <td width="9%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
    
    <td width="4%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">DOB Filing/Job No.</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Sign Off(Y/N)</font></b></td>    
    <td width="9%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit6.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit6.get(i);
								
  %>
  <tr>
    <td width="2%" align="center">&nbsp;<%=hashtable.get("generatorid")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("manufacturer")%></td>
    <td width="4%" align="center">&nbsp;<%=hashtable.get("model")%></td>
    <td width="4%" align="center">&nbsp;<%=hashtable.get("serial")%></td>
    <td width="6%" align="center">
    <p align="left">&nbsp;<%=hashtable.get("buildingname")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("yearinstalled")%></td>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("mmbtu")%></td>
    <td width="2%" align="center">&nbsp;<%=hashtable.get("hp")%></td>
    <td width="2%" align="center">&nbsp;<%=hashtable.get("kw")%></td>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("gph")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("fuel")%></td>    
    <td width="5%" align="center">&nbsp;<%=hashtable.get("dep")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("expirationdate")%></td>    
    <td width="5%" align="center">&nbsp;<%=hashtable.get("stackpermit")%></td>   
    <td width="4%" align="center">&nbsp;<%=hashtable.get("stacktest")%></td>
    <td width="4%" align="center">&nbsp;<%=hashtable.get("lasttest")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("nexttest")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("compliancestatus2")%></td>    
    <td width="4%" align="center">&nbsp;<%=hashtable.get("NYCDOB")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("dobcompliancestatus")%></td>    
    <td width="5%" align="center">&nbsp;<%=hashtable.get("compliancestatus1")%></td>

    
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
   fromseventh fseven=new fromseventh();    
   List exhibit7=(List)fseven.getNycdobStatusReport7(facilityvo.getId()); 
  if (exhibit7 != null && exhibit7.size()>0)
					{
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="116%" id="AutoNumber7">
  <tr>
    <td colspan="18">
    <p align="center"><b><font face="Verdana" color="#006699" size="2">REPORT 7: LIST OF 
    PETROLEUM BULK STORAGE TANKS AND  DEC REGISTRATION STATUS FOR '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="6%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Tank ID</font></b></td>
    <td width="12%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Installation Year</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Capacity<br>(Gallons)</font></b></td>
    <td width="11%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Product Stored</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Tank Type</font></b></td>
    <td colspan="2" bgcolor="#006699"><p align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Tightness Test Date</font></b></td>
    
     <td colspan="2" bgcolor="#006699"><p align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Corrosion Protection Test Date</font></b></td>

    <td width="8%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="8%" rowspan="2" bgcolor="#006699" align="center">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">Fuel<br>Supplied To</font></b></td>
    
    <td colspan="4" bgcolor="#006699"><p align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance With NYC <%=dob%>/Certificate of Approval</font></b></td>
    
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">PBS Number</font></b></td>
    
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">PBS Exp. Date</font></b></td>
    <td width="16%" rowspan="2" bgcolor="#006699">
    <p align="center"><b><font size="2" face="Verdana" color="#FFFFFF">DEC Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Last</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Next</font></b></td>
     <td width="7%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Last</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Next</font></b></td>
    
    
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dob%> Job No.</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Sign Off(Y/N)</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">C of A(Y/N)</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit7.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit7.get(i);
  
  %>
  <tr>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("facilitydesignatedid")%></td>
    <td width="12%" align="center">&nbsp;<%=hashtable.get("yearinstalled")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("capacity")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("tankstored")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("tanktype")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("TESTDATE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("NEXTTESTDATE")%></td>    
    <td width="7%" align="center">&nbsp;<%=hashtable.get("LASTTESTDATE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("NEXTTESTDUEDATE")%></td>    
    <td width="8%" align="center">
    <p align="left">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("boilerid")%></td>    
    
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DOBAPPROVAL")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>    
    <td width="7%" align="center">&nbsp;<%=hashtable.get("CERTIFICATEOFAPPROVAL")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("dobcompliancestatus")%></td> 
    <td width="10%" align="center">&nbsp;<%=hashtable.get("PBSNUMBER")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("EXPIRATIONDATE")%></td>
    <td width="16%" align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
  <%
  List exhibit8=(List)fseven.getNycdobStatusReport8(facilityvo.getId()); 
   if (exhibit8 != null && exhibit8.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="119%" id="AutoNumber8">
  <tr>
    <td colspan="16">
    <p align="center"><b><font face="Verdana" color="#006699" size="2">REPORT 8: LIST OF 
    ETHYLENE OXIDE STERILIZER SYSTEM AND PERMIT STATUS FOR'<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="2%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id</font></b></td>
    
    <td colspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Sterilizer</font></b></td>
    
    <td colspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Abator </font></b> </td>
    <td width="8%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b> </td>
    <td colspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Avg. Use</font></b></td>
    <td colspan="3" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%></font></b>&nbsp;</td>
    
    <td width="10%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%> <br> Compliance Status</font></b></td>
    
    <td colspan="3" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance with NYC <%=dob%></font></b>&nbsp;</td>

   
  </tr>
  <tr>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Make</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Model</font></b></td>
    <td width="5%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Make</font></b></td>
    <td width="5%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Model</font></b></td>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Hrs/Day</font></b></td>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Days/Week</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Permit No.</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Issue Date</font></b></td>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Expiration Date</font></b></td>    
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Filing/Job No.</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Sign Off(Y/N)</font></b></td>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit8.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit8.get(i);
  
  %>

  <tr>
    <td width="2%" align="center">&nbsp;<%=hashtable.get("eid")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("MANUFACTURER")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MODEL")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("amake")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("amodel")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("days")%></td>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("hrs")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("dep")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("ISSUEDATE")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("EXPIRATIONDATE")%></td>    
    <td width="10%" align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DOB")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("dobcompliancestatus")%></td>  
  </tr>
    <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit9=(List)fseven.getNycdobStatusReport9(facilityvo.getId()); 
   if (exhibit9 != null  && exhibit9.size()>0)
   {
%>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber9">
  <tr>
    <td colspan="15">
    <p align="center"><b><font face="Verdana" color="#006699" size="2">REPORT 9: LIST OF CHILLERS, AGENCY APPROVALS AND INSPECTION REPORTS FOR '<%=str.toUpperCase()%>'</font></b></td>
  </tr>
  <tr>
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id.</font></b></td>  
      
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Capacity (Tons)</font></b></td>
    
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location </font></b></td>
    
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Floor</font></b></td>
    
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Make</font></b></td>
    
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Model</font></b></td>
    
    <td width="5%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Serial No.</font></b></td>  
          
    <td width="40%" colspan="4" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance with NYC <%=dob%></font></b></td>    
    
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%> Approval</font></b></td>   
     
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%> <br> Compliance Status</font></b></td>
  </tr>
  <tr>
   <td width="10%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Filing/Job No.</font></b></td>    
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Sign Off(Y/N)</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">EUP Available?</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit9.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit9.get(i);
  
  %>
  <tr>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("facilitydesinatedid")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("capacity")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("buildingname")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("floor")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("make")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("model")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("serial")%></td>    
    <td width="10%" align="center">&nbsp;<%=hashtable.get("DOBJOBNUMBER")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("EUPAVAILABLE")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("dobcompliancestatus")%></td>    
    <td width="10%" align="center">&nbsp;<%=hashtable.get("depapproval")%></td>   
    <td width="10%" align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit10=(List)fseven.getNycdobStatusReport10(facilityvo.getId()); 
   if (exhibit10 != null  && exhibit10.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber10">
  <tr>
    <td width="100%" colspan="9">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 10: LIST OF ELEVATORS AND <%=dob%> INSPECTION STATUS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="11%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="11%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Address</font></b></td>
    <td width="11%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Elevator Id</font></b></td>
    <td width="11%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dob%> Permit</font></b></td>
    <td width="33%" colspan="3" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Annual Inspection Date</font></b></td>
    <td width="11%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Inspected By</font></b></td>
    <td width="12%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="11%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Last</font></b></td>
    <td width="11%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Submittal Date</font></b></td>
    <td width="11%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Next</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit10.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit10.get(i);
  
  %>

  <tr>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("ADDRESS1")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("ELEVATORID")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("PERMITNUMBER")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("LASTINSPECTIONDATE")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("SUBMITTALDATE")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("NEXTINSPECTIONDATE")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("FIRMINSPECTED")%></td>
    <td width="12%" align="center">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit11=(List)fseven.getNycdobStatusReport11(facilityvo.getId()); 
   if (exhibit11 != null  && exhibit11.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber11">
  <tr>
    <td width="100%" colspan="13">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 11: LIST OF </font> </span>
    <span style="font-family: Verdana; font-weight: 700; color: #01688A; background-color: #FFFFFF">
    <font size="2">RPZs AND AGENCY PERMIT STATUS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="5%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">No.</font></b></td>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Id.</font></b></td>
    <td width="10%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Location</font></b></td>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Serial No.</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Model</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Size Inches</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Type</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana"><%=dob%></font></b>&nbsp;</td>
    <td width="9%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana"><%=dep%> Permit#</font></b>&nbsp;</td>    
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Last Inspection Date</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Next Inspection Date</font></b></td>
    <td width="11%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit11.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit11.get(i);
  
  %>
  <tr>
    <td width="5%" align="center">&nbsp;<%=(i+1)%></td>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="10%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("SERIALNUMBER")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MODEL")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("SIZE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("TYPE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DOBPERMITNUMBER")%></td>
    <td width="9%" align="center">&nbsp;<%=hashtable.get("DEPPERMITNUMBER")%></td>    
    <td width="8%" align="center">&nbsp;<%=hashtable.get("LASTINSPECTIONDATE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("NEXTINSPECTIONDATE")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>
  </tr>
   <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit12=(List)fseven.getNycdobStatusReport12(facilityvo.getId()); 
   if (exhibit12 != null   && exhibit12.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="121%" id="AutoNumber12" height="118">
  <tr>
    <td width="100%" colspan="27" height="15">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 12: LIST OF </font> </span>
    <font size="2">
    <span style="font-family: Verdana; font-weight: 700; color: #01688A">COGEN 
    SYSTEMS AND THEIR DESIGN AND OPERATING PARAMETERS FOR</span></font><span style="font-family: Verdana; font-weight: 700; color: #01688A; background-color: #FFFFFF"><font size="2"> '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="4%" rowspan="3" bgcolor="#006699" align="center" height="82"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id.</font></b></td>
    <td width="12%" rowspan="2" colspan="3" bgcolor="#006699" align="center" height="49">
    <b><font size="2" face="Verdana" color="#FFFFFF">Manufacturer</font></b></td>
    <td width="11%" rowspan="2" colspan="3" bgcolor="#006699" align="center" height="49">
    <b><font size="2" face="Verdana" color="#FFFFFF">Model</font></b></td>
    <td width="12%" rowspan="2" colspan="3" bgcolor="#006699" align="center" height="49">
    <b><font size="2" face="Verdana" color="#FFFFFF">Serial No.</font></b></td>
    <td width="6%" rowspan="3" bgcolor="#006699" align="center" height="82"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="4%" rowspan="3" bgcolor="#006699" align="center" height="82"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Year Installed</font></b></td>
    <td width="12%" rowspan="2" colspan="3" bgcolor="#006699" align="center" height="49">
    <b><font size="2" face="Verdana" color="#FFFFFF">Maximum Capacity (MMBTU/Hr)</font></b></td>
    <td width="11%" rowspan="2" colspan="3" bgcolor="#006699" align="center" height="49">
    <b><font size="2" face="Verdana" color="#FFFFFF">Primary Fuel</font></b></td>
    <td width="10%" colspan="3" rowspan="2" bgcolor="#006699" align="center" height="49">
    <b><font size="2" face="Verdana" color="#FFFFFF">Secondary Fuel</font></b></td>
    <td width="18%" colspan="6" bgcolor="#006699" align="center" height="16"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Fuel Capacity</font></b></td>
  </tr>
  <tr>
    <td width="4%" colspan="2" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Engine</font></b></td>
    <td width="7%" colspan="2" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Turbine</font></b></td>
    <td width="7%" colspan="2" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Waste HRB</font></b></td>
  </tr>
  <tr>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Engine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Turbine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Waste HRB</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Engine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Turbine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Waste HRB</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Engine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Turbine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Waste HRB</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Engine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Turbine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Waste HRB</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Engine</font></b></td>
    <td width="4%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Turbine</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Waste HRB</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32">&nbsp;</td>
    <td width="4%" bgcolor="#006699" align="center" height="32">&nbsp;</td>
    <td width="3%" bgcolor="#006699" align="center" height="32">&nbsp;</td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">GPH</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">CFH</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">GPH</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">CFH</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">GHP</font></b></td>
    <td width="3%" bgcolor="#006699" align="center" height="32"><b>
    <font size="2" face="Verdana" color="#FFFFFF">CFH</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit12.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit12.get(i);
  
  %>
  <tr>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("cid")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("enginemanufacturer")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("turbinemanufacturer")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("whrbmanufacturer")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("enginemodel")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("turbinemodel")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("whrbmodel")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("engineserial")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("turbineserial")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("whrbserial")%></td>
    <td width="6%" height="19" align="center">&nbsp;<%=hashtable.get("location")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("year")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("enginecapacity")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("turbinecapacity")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("whrbcapacity")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("engineprimary")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("turbineprimary")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("whrbprimary")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("enginesecondary")%></td>
    <td width="4%" height="19" align="center">&nbsp;<%=hashtable.get("turbinesecondary")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("whrbsecondary")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("enginegph")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("enginecfh")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("turbinegph")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("turbinecfh")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("whrbgph")%></td>
    <td width="3%" height="19" align="center">&nbsp;<%=hashtable.get("whrbcfh")%></td>
  </tr>
  
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit13=(List)fseven.getNycdobStatusReport13(facilityvo.getId()); 
   if (exhibit13 != null   && exhibit13.size()>0)
   {
%>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber13">
  <tr>
    <td width="100%" colspan="11">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 13: LIST OF </font> </span>
    <font size="2">
    <span style="font-family: Verdana; font-weight: 700; color: #01688A">COGEN 
    SYSTEMS AND AGENCY PERMIT STATUS FOR </span></font><span style="font-family: Verdana; font-weight: 700; color: #01688A; background-color: #FFFFFF"><font size="2">'<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="4%" align="center" rowspan="2" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id.</font></b></td>
    <td width="8%" align="center" rowspan="2" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td> 
    
	<td colspan="3" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance With NYC <%=dob%></font></b></td>   
    
   
    <td colspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dep%></font></b>&nbsp;</td>    
    <td colspan="3" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance With NY State DEC</font></b></td>    
    <td width="8%" align="center" rowspan="2" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dob%> Filing/Job No.</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Sign Off(Y/N)</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Permit No.</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Expiration Date</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Stack Test</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Last Test Date</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Next Test Date</font></b></td>
  </tr>
<%
					  	int psize =0; 
						psize = exhibit13.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit13.get(i);
  
  %>
  <tr>
    <td width="4%"  align="center">&nbsp;<%=hashtable.get("facilitydesinatedid")%></td>
    <td width="8%"  align="center">&nbsp;<%=hashtable.get("location")%></td>     
    <td width="8%"  align="center">&nbsp;<%=hashtable.get("NYCDOB")%></td>
    <td width="5%"  align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>
    <td width="5%"  align="center">&nbsp;<%=hashtable.get("dobcompliancestatus")%></td>     
    <td width="8%"  align="center">&nbsp;<%=hashtable.get("deppermit")%></td>
    <td width="8%"  align="center">&nbsp;<%=hashtable.get("expirationdate")%></td>
    <td width="5%"  align="center">&nbsp;<%=hashtable.get("stacktest")%></td>
    <td width="5%"  align="center">&nbsp;<%=hashtable.get("lasttestdate")%></td>
    <td width="5%"  align="center">&nbsp;<%=hashtable.get("nexttestdate")%></td>
    <td width="8%"  align="center">&nbsp;<%=hashtable.get("compliancestatus")%></td>
  </tr>
  
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit14=(List)fseven.getNycdobStatusReport14(facilityvo.getId()); 
  //out.println(exhibit14+"");
   if (exhibit14 != null   && exhibit14.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber14">
  <tr>
    <td width="100%" colspan="10">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 14: LIST OF PAINT SPRAY BOOTHS OR PAINT SHOPS FOR </font></span><span style="font-family: Verdana; font-weight: 700; color: #01688A; background-color: #FFFFFF"><font size="2">'<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Make</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Model</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td>
    <td width="20%" colspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Avg.Use</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dep%> Permit</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Expiration Date</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Included In DEC Permit</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="10%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Hrs/Week</font></b></td>
    <td width="10%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Days/Year</font></b></td>
  </tr>
<%
					  	int psize =0; 
						psize = exhibit14.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit14.get(i);
  
  %>

  <tr>
    <td width="10%">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("MAKE")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("MODEL")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("HROFOPERATION")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("PSHOURSOFOPERATIONYEAR")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("PSDEPNUMBER")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("EXPIRATIONDATE")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("INCLUDEDINDECPERMIT")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
  
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
  List exhibit15=(List)fseven.getNycdobStatusReport15(facilityvo.getId()); 
   if (exhibit15 != null   && exhibit15.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber15">
  <tr>
    <td colspan="13">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 15: LIST OF FUMEHOODS AND THEIR COMPLIANCE STATUS FOR </font></span><span style="font-family: Verdana; font-weight: 700; color: #01688A; background-color: #FFFFFF"><font size="2">'<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="6%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id.</font></b></td>
    <td width="8%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td>
    <td width="6%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Type</font></b></td>
    <td width="6%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Used For</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Operation- Hrs/Day</font></b></td>
    
    <td width="14%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Chemicals Used</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Included in DEC</font></b></td>
    <td colspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dep%> Permit#</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dob%> Permit# </font></b></td>
    
    <td colspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Annual Inspection</font></b></td>
    
    <td width="14%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    
    <td width="9%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">ID</font></b></td>
    <td width="9%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Expiration Date</font></b></td>
    
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Last Date</font></b></td>
    <td width="5%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Next Date</font></b></td>

  </tr>

<%
					  	int psize =0; 
						psize = exhibit15.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit15.get(i);
  
  %>

  <tr>
    <td width="6%">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("LOCATION")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("MAKE")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("MODEL")%></td>
    <td width="9%">&nbsp;<%=hashtable.get("HROFOPERATION")%></td>    
    <td width="14%">&nbsp;<%=hashtable.get("CHEMICAL")%></td>
    <td width="10%">&nbsp;<%=hashtable.get("INCLUDEDINDECPERMIT")%></td>
    <td width="9%">&nbsp;<%=hashtable.get("DEP")%></td>
    <td width="9%">&nbsp;<%=hashtable.get("EXPIRATIONDATE")%></td>
    <td width="9%">&nbsp;<%=hashtable.get("DOB")%></td>    
    <td width="5%">&nbsp;<%=hashtable.get("LASTINSPECTIONDATE")%></td>
    <td width="5%">&nbsp;<%=hashtable.get("NEXTINSPECTIONDATE")%></td>    
    <td width="14%">&nbsp;<%=hashtable.get("compliance")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
    
   List exhibit16=(List)fseven.getNycdobStatusReport16(facilityvo.getId()); 
   if (exhibit16!= null   && exhibit16.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber16">
  <tr>
    <td width="100%" colspan="13">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 16: LIST OF TUNNELS AND BRIDGES AND AGENCY PERMIT STATUS&nbsp; FOR </font></span><span style="font-family: Verdana; font-weight: 700; color: #01688A; background-color: #FFFFFF"><font size="2">'<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id.</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Type</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td>
    <td width="23%" colspan="3" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DOT</font></b></td>
    <td width="16%" colspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Insurance Company</font></b></td>
    <td width="16%" colspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DOT-Inspection Test Date</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dob%> Permit#</font></b></td>
    <td width="16%" colspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Permit No.</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">File No.</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Expiration Date</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Effective Date</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Expiration Date</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Last</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Next</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DOT</font></b></td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dob%></font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit16.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit16.get(i);
  
  %>
  <tr>
    <td width="7%">&nbsp;<%=hashtable.get("bid")%></td>
    <td width="7%">&nbsp;<%=hashtable.get("type")%></td>
    <td width="7%">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="7%">&nbsp;<%=hashtable.get("PERMITNUMBER")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("FILENO")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("EXPIRATIONDATE")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("INSISSUEDATE")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("INSEXPDATE")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("LASTINSPDATE")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("NEXTINSPDATE")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("dobnumber")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("dotcompliance")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("dobcompliance")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
    
   List exhibit17=(List)fseven.getNycdobStatusReport17(facilityvo.getId()); 
   if (exhibit17!= null   && exhibit17.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="112%" id="AutoNumber17">
  <tr>
    <td width="100%" colspan="15">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 17: LIST OF INCINERATOR/CREMATORIES AND THEIR COMPLIANCE STATUS 
    FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="4%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id.</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td>
    <td width="6%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Throughput (lb/hr)</font></b></td>
    <td width="6%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dob%> Permit#</font></b></td>
    <td width="6%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dep%> Permit#</font></b></td>
    <td width="5%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DEC#</font></b></td>
    <td width="14%" colspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Permit Expiration Date</font></b></td>
    <td width="13%" colspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Temp.Compliance</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Waste Quantity</font></b></td>
    <td width="6%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Stack Test Required</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Stack Test Date</font></b></td>
    <td width="6%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Next Stack Test Date</font></b></td>
    <td width="11%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana"><%=dep%></font></b>&nbsp;</td>
    <td width="8%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DEC</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Primary</font></b></td>
    <td width="6%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Secondary</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit17.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit17.get(i);
  
  %>

  <tr>
    <td width="4%">&nbsp;<%=hashtable.get("IID")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("LOCATION")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("THROUGHPUT")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("DOB")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("DEP")%></td>
    <td width="5%">&nbsp;<%=hashtable.get("DEC")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("DEPEXPIRATIONDATE")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("DECEXPIRATIONDATE")%></td>
    <td width="7%">&nbsp;<%=hashtable.get("PRIMARY")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("SECONDARY")%></td>
    <td width="8%">&nbsp;<%=hashtable.get("WASTEQUANTITY")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("STACKTESTREQUIRED")%></td>
    <td width="7%">&nbsp;<%=hashtable.get("STACKTESTDATE")%></td>
    <td width="6%">&nbsp;<%=hashtable.get("NEXTSTACKTESTDATE")%></td>
    <td width="11%">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
    
   List exhibit18=(List)fseven.getNycdobStatusReport18(facilityvo.getId()); 
   if (exhibit18!= null   && exhibit18.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber18">
  <tr>
    <td colspan="9">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 18: LIST OF HYDRAULIC ELEVATOR TANKS&nbsp; AND&nbsp; COMPLIANCE 
    STATUS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="6%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id.</font></b></td>
    <td width="18%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="11%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Capacity</font></b></td>
    <td width="15%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Supplied to:<br> Elevator#</font></b></td>
    <td width="14%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Secondary Containment </font></b></td>    
    <td width="11%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly Inspection</font></b></td>
    <td width="8%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Spill Kits</font></b></td>
    <td width="17%" bgcolor="#006699" align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
<%
					  	int psize =0; 
						psize = exhibit18.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit18.get(i);
  
  %>

  <tr>
    <td width="6%" align="center">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="18%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="11%" align="center">&nbsp;<%=hashtable.get("CAPACITY")%></td>
    <td width="15%" align="center">&nbsp;<%=hashtable.get("LISTOFELEVATOR")%></td>
    <td width="14%" align="center">&nbsp;<%=hashtable.get("ISSECONDARYCONTAINMENT")%></td>    
    <td width="11%" align="center">&nbsp;<%=hashtable.get("MONTHLYINSPECTION")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("SPILLKIT")%></td>
    <td width="17%" align="center">&nbsp;<%=hashtable.get("compliance")%></td>
  </tr>
   <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
    
   List exhibit19=(List)fseven.getNycdobStatusReport19(facilityvo.getId()); 
   if (exhibit19!= null   && exhibit19.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber19" height="70">
  <tr>
    <td width="100%" colspan="8" height="18">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 19: LIST OF BULK OXYGEN STORAGE TANKS&nbsp; AND&nbsp; COMPLIANCE 
    STATUS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="9%" rowspan="2" bgcolor="#006699" align="center" height="33"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Id.</font></b></td>
    <td width="10%" rowspan="2" bgcolor="#006699" align="center" height="33"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Location</font></b></td>
    <td width="5%" rowspan="2" bgcolor="#006699" align="center" height="33"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Capacity<br>(Gallons)</font></b></td>

    <td width="5%" rowspan="2" bgcolor="#006699" align="center" height="33"><b>
    <font size="2" color="#FFFFFF" face="Verdana"><%=dob%> /FD Approval</font></b></td>
    <td width="5%" rowspan="2" bgcolor="#006699" align="center" height="33"><b>
    <font size="2" color="#FFFFFF" face="Verdana">FD Approval#</font></b></td>
    <td  colspan="2" bgcolor="#006699" align="center" height="16"><b>
    <font size="2" color="#FFFFFF" face="Verdana">5Yr.Pressure Test</font></b></td>
    <td width="8%" rowspan="2" bgcolor="#006699" align="center" height="33"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="5%" bgcolor="#006699" align="center" height="16"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Last Test Date</font></b></td>
    <td width="5%" bgcolor="#006699" align="center" height="16"><b>
    <font size="2" color="#FFFFFF" face="Verdana">Next Test Date</font></b></td>
  </tr>
   
<%
					  	int psize =0; 
						psize = exhibit19.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit19.get(i);
  
  %>

  <tr>
    <td width="9%" height="17" align="center"><%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="10%" height="17" align="center"><%=hashtable.get("BUILDINGNAME")%></td>
    <td width="5%" height="17" align="center"><%=hashtable.get("CAPACITY")%></td>
    <td width="5%" height="17" align="center"><%=hashtable.get("FIREDEPTAPPROVAL")%></td>
    <td width="5%" height="17" align="center"><%=hashtable.get("FIREDEPTAPPROVALNO")%></td>
    <td width="5%" height="17" align="center"><%=hashtable.get("LASTTESTDATE")%></td>
    <td width="5%" height="17" align="center"><%=hashtable.get("NEXTTESTDATE")%></td>
    <td width="8%" height="17" align="center"><%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
    <%
  	}
  		}
  		  %>
</table>
<p> </P>
  <%    
   List exhibit20=(List)fseven.getNycdobStatusReport20(facilityvo.getId()); 
   if (exhibit20!= null   && exhibit20.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber20">
  <tr>
    <td width="100%" colspan="15">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 20: COMPLIANCE EVALUATION OF&nbsp; THE PRINTING PRESS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="6%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id</font></b></td>
    <td width="6%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location </font></b></td>
    <td width="6%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Material Type</font></b></td>
    <td width="6%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Material Name</font></b></td>
    <td width="6%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Volume gal/month</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Density lb/gal</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">VOC (Wt)%</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">VOC lb/month</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Total VOC lb/Yr</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Allowable VOC lb/Yr</font></b></td>
    
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit20.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit20.get(i);
  
  %>

  <tr>
    <td width="6%" rowspan="6" valign="top">
    <p align="center"><%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="6%" rowspan="6" valign="top"><%=hashtable.get("BUILDINGNAME")%>&nbsp;</td>
    <td width="6%" align="center"><font face="Verdana" size="2"><b>Ink 1</b></font></td>
    <td width="6%"><%=hashtable.get("INK1NAME")%>&nbsp;</td>

    <td width="6%"><%=hashtable.get("INK1VOLUME")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK1DENSITY")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK1VOCPERCENT")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK1VOC")%>&nbsp;</td>
    <td width="7%" rowspan="6" valign="top" align="center"><%=hashtable.get("TOTALVOC")%>&nbsp;</td>
    <td width="7%" rowspan="6" valign="top" align="center"><%=hashtable.get("ANNUALCONSUMPTION")%>&nbsp;</td>
    
    <td width="7%" rowspan="6" valign="top" align="center"><%=hashtable.get("COMPLIANCESTATUS")%>&nbsp;</td>
  </tr>
  <tr>
    <td width="6%" align="center"><font face="Verdana" size="2"><b>Ink 2</b></font></td>
    <td width="6%"><%=hashtable.get("INK2NAME")%>&nbsp;</td>
    <td width="6%"><%=hashtable.get("INK2VOLUME")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK2DENSITY")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK2VOCPERCENT")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK2VOC")%>&nbsp;</td>
  </tr>
  <tr>
    <td width="6%" align="center"><font face="Verdana" size="2"><b>Ink 3</b></font></td>
    <td width="6%"><%=hashtable.get("INK3NAME")%>&nbsp;</td>
    <td width="6%"><%=hashtable.get("INK3VOLUME")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK3DENSITY")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK3VOCPERCENT")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK3VOC")%>&nbsp;</td>
  </tr>
  <tr>
    <td width="6%" align="center"><font face="Verdana" size="2"><b>Ink 4</b></font></td>
    <td width="6%"><%=hashtable.get("INK4NAME")%>&nbsp;</td>
    <td width="6%"><%=hashtable.get("INK4VOLUME")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK4DENSITY")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK4VOCPERCENT")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("INK4VOC")%>&nbsp;</td>
  </tr>
  <tr>
    <td width="6%" align="center"><font face="Verdana" size="2"><b>Fountain 
    Solution</b></font></td>
    <td width="6%"><%=hashtable.get("FOUNTAINSOLUTIONNAME")%>&nbsp;</td>
    <td width="6%"><%=hashtable.get("FOUNTAINSOLUTIONVOLUME")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("FOUNTAINSOLUTIONDENSITY")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("FOUNTAINSOLUTIONVOCPERCENT")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("FOUNTAINSOLUTIONVOC")%>&nbsp;</td>
  </tr>
  <tr>
    <td width="6%" align="center"><font face="Verdana" size="2"><b>Cleaning 
    Agent</b></font></td>
    <td width="6%"><%=hashtable.get("CLEANINGAGENTNAME")%>&nbsp;</td>
    <td width="6%"><%=hashtable.get("CLEANINGAGENTVOLUME")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("CLEANINGAGENTDENSITY")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("CLEANINGAGENTVOCPERCENT")%>&nbsp;</td>
    <td width="7%"><%=hashtable.get("CLEANINGAGENTVOC")%>&nbsp;</td>
  </tr>
    <%
  	}
  		}
  		  %>
</table>
<p> </P>
  <%
    
   List exhibit21=(List)fseven.getNycdobStatusReport21(facilityvo.getId()); 
   if (exhibit21!= null   && exhibit21.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber21">
  <tr>
    <td width="100%" colspan="14">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 21: LIST OF LANDFILLS AND COMPLIANCE STATUS&nbsp; FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id.</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Year Operation Began</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">LFG Capacity CFY</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Annual Production CFY</font></b></td>
    <td width="14%" colspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">LFG Quality Measure/Allowable
    </font></b></td>
    <td width="28%" colspan="4" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Fuel Gas Quality</font></b></td>
    <td width="7%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Operating Hours</font></b></td>
    <td width="8%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">AES Submittal Date</font></b></td>
    <td width="8%" rowspan="2" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">S</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">CH4</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Temp</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">NOX</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">CO</font></b></td>
    <td width="7%" bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Other</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit21.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit21.get(i);
  
  %>

  <tr>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("COMMENCEDDATE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("LFGQUANTITY")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("LFGCOMBUSTION")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("SVALUE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("CH4VALUE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("TEMPERATUREVALUE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("NOXVALUE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("COVALUE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("OVALUE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("YEARLYVALUE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("AESDATE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
  <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
    
   List exhibit22=(List)fseven.getNycdobStatusReport22(facilityvo.getId()); 
   if (exhibit22!= null   && exhibit22.size()>0)
   {
%>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber22">
  <tr>
    <td width="100%" colspan="14">
    <p align="center">
    <span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 22:&nbsp; COMPLIANCE EVALUATION OF 'MISCELLANEOUS' SOURCES&nbsp; 
    FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Id.</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Location </font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Name </font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Make</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Model</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Capacity</font></b></td>
    <td width="35%" colspan="5" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Permit Status</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Expiration Date</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Stack Test Done</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DEP</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DOB </font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DOH</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">DEC</font></b></td>
    <td width="7%" align="center" bgcolor="#006699"><b>
    <font color="#FFFFFF" size="2" face="Verdana">FD</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit22.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit22.get(i);
  
  %>
  <tr>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISNAME")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISMAKE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISMODEL")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISCAPACITY")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISDEP")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISDOB")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISDOH")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISDEC")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISFD")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MISEXPIRATIONDATE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("MISTESTSUBMITTED")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
   <%
  	}
  		}
  		  %>
</table>
<p> </P>
  <%
   List exhibit23=(List)fseven.getNycdobStatusReport23(facilityvo.getId()); 
   if (exhibit23!= null   && exhibit23.size()>0)
   {
%>
<table border="1" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber23">
  <tr>
    <td width="100%" colspan="15">
    <p align="center"><span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 23:&nbsp; LIST OF CHILLER WITH AIR-CONDITIONING REFRIGERATION AND COMPLIANCE STATUS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">No.</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id.</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Area Served</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Make</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Model</font></b></td>
    
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Serial No.</font></b></td>
    
    <td  colspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Capacity</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dob%> Job/Filing No.</font></b></td>
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dob%> Sign Off(Y/N)</font></b></td>
    <td width="5%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">EUP Card(Y/N)</font></b></td>    
    <td width="7%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%> Permit No.</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF"><%=dep%> Expiration Date</font></b></td>
    <td width="8%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  <tr>
  <td width="5%" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Tons</font></b></td>
    <td width="5%" align="center" bgcolor="#01688A"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Btu/hr</font></b></td>


   
  </tr>

<%
					  	int psize =0; 
						psize = exhibit23.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit23.get(i);
  
  %>

  <tr>
    <td width="7%" align="center">&nbsp;<%=i+1%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("AREASERVED")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MAKE")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("MODEL")%></td>    
    <td width="7%" align="center">&nbsp;<%=hashtable.get("SERIALNUM")%></td>        
    <td width="5%" align="center">&nbsp;<%=hashtable.get("CAPACITYTONS")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("CAPACITYBTU")%></td>        
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DOBPERMIT")%></td>
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DOBSIGNOFF")%></td>
    <td width="5%" align="center">&nbsp;<%=hashtable.get("EQUIPMENTUSEPERMIT")%></td>   
    <td width="7%" align="center">&nbsp;<%=hashtable.get("DEPPERMIT")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("DEPEXPIRATIONDATE")%></td>
    <td width="8%" align="center">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
  
   <%
  	}
  		}
  		  %>
</table>
<p> </P>
<%
   List exhibit24=(List)fseven.getNycdobStatusReport24(facilityvo.getId()); 
   if (exhibit24!= null   && exhibit24.size()>0)
   {
%>
<table border="1" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber24" height="92">
  <tr>
    <td width="100%" colspan="12" height="11"><p align="center"><span style="font-family: Verdana; color: rgb(1, 104, 138); font-weight: 700; background-color: rgb(255, 255, 255)">
    <font size="2">REPORT 24: LIST OF FIRE ALARM AND COMPLIANCE STATUS FOR '<%=str.toUpperCase()%>'</font></span></td>
  </tr>
  <tr>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">No.</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Id.</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Location</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Type</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b><font size="2" face="Verdana" color="#FFFFFF"><%=dob%>
    </font></b><b><font size="2" face="Verdana" color="#FFFFFF">Approval</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b><font size="2" face="Verdana" color="#FFFFFF"><%=dob%>
    </font></b><b><font size="2" face="Verdana" color="#FFFFFF">Approval Date</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">FD Approval Required</font></b></td>
    <td width="8%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">FD Approval Date</font></b></td>
    <td width="27%" bgcolor="#01688A" colspan="3" align="center" height="16"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Other Agency</font></b></td>
    <td width="9%" bgcolor="#01688A" rowspan="2" align="center" height="35"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="9%" bgcolor="#01688A" align="center" height="16"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Agency Name</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center" height="16"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Approval No</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center" height="16"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Approval Date</font></b></td>
  </tr>

<%
					  	int psize =0; 
						psize = exhibit24.size();
						for (int i=0; i < psize; i++)
						{
						Hashtable hashtable=(Hashtable)exhibit24.get(i);
  
  %>

  <tr>
    <td width="8%" height="19" align="center">&nbsp;<%=i+1%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("FACILITYDESIGNATEDID")%></td>
     <td width="9%" height="19" align="center">&nbsp;<%=hashtable.get("BUILDINGNAME")%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("TYPEOFFAINSTALLED")%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("DOBAPPROVAL")%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("APPROVALDATE")%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("FDAPPROVAL")%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("FDAPPROVALDATE")%></td>
    <td width="8%" height="19" align="center">&nbsp;<%=hashtable.get("AGENCYNAME")%></td>
    <td width="9%" height="19" align="center">&nbsp;<%=hashtable.get("AGENCYAPPROVALNO")%></td>
    <td width="9%" height="19" align="center">&nbsp;<%=hashtable.get("AGENCYAPPROVALDATE")%></td>   
    <td width="9%" height="19" align="center">&nbsp;<%=hashtable.get("COMPLIANCESTATUS")%></td>
  </tr>
   <%
  	}
  		}
  		  %>
</table>
<hr color="#000000">
<table width="100%" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0">
<tr><td width="602">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
 </td>
 <td width="93">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  <img border="0" src="images/ees.JPG" width="20" height="18"></SPAN>
 </td>
 
 <td width="197">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  Environmental 
Engineering Solutions, P.C.</SPAN></td>
 
 </tr>
</table>

</body>

</html>