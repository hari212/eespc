<html>
<%@ page language ="java" import = "sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.sql.*,java.text.*,com.eespc.tracking.bo.FacilityVo" %> 

<head>
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 28</title>
</head>
<%
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
FacilityVo facilityvo = (FacilityVo)session.getAttribute("REPORT_FACILITY_VO");
%>
<p align="left"><img border="0" src="images/pdf.gif" width="16" height="16">
<a href="jsp/Exhibit-28xls.jsp" target="_blank"><img border="0" src="images/xls.gif" width="16" height="16"></a>&nbsp;&nbsp;
<img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"></p>

<span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">	
<%
    String str="";
    Connection conn= null;            
    PreparedStatement st= null;
    ResultSet rs = null;
    try
{

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
            }finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}

            out.println(str);
    %>	
    </span> <br>
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    EESPC Compliance Tracking Software (C)</span>
    <br>
<body>
<p align="center"><font color="#01688A"><b>REPORT 28:<span style="text-transform: uppercase"> 
OTHER REGULATORY COMPLIANCE EVALUATIONS TASKS</span><%//out.println(t[Integer.parseInt(str)-1]);%> FOR '<%=str.toUpperCase()%>'</b></font><hr>
<%
					List printlist=fromseventh.getNycdobStatusReport28(facilityvo.getId());
                    int psize =0;
					Hashtable hashtable= new Hashtable();
					if (printlist != null)
					{
						psize = printlist.size();
						for (int i=0; i < psize; i++)
						{
						hashtable=(Hashtable)printlist.get(i);
						
%>

   
    
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; font-family:Verdana; font-size:10pt" bordercolor="#111111" width="100%" id="AutoNumber1" height="385">
  <tr>
    <td width="4%" align="center" bgcolor="#01688A" height="32"><b>
    <font face="Verdana" size="2" color="#FFFFFF">No.</font></b></td>
    <td width="60%" align="center" bgcolor="#01688A" height="32"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Description of Compliance 
    Items</font></b></td>
    <td width="10%" align="center" bgcolor="#01688A" height="32"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Required Or Not</font></b></td>
    <td width="9%" align="center" bgcolor="#01688A" height="32"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Available </font></b></td>
    <td width="17%" align="center" bgcolor="#01688A" height="32"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">1</font></td>
				
	
    <td width="60%" height="19">Spill Prevention Control &amp; Counter Measure Plan 
    (SPCC).</td>
             
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("SPCC_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("SPCC_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("SPCC_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">2</font></td>
				
	
    <td width="60%" height="19">Storm Water Discharge Pollution Prevention Plan 
    (SWDPPP).</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("STORMPREVENTION_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("STORMPREVENTION_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("STORMPREVENTION_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">3</font></td>
				
	
    <td width="60%" height="19">EPA Audit Under Self Audit Program.</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("EPAAUDIT_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("EPAAUDIT_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("EPAAUDIT_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">4</font></td>
				
	
    <td width="60%" height="21">All Findings Corrected Under the Self Audit?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("ACCFINDING_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("ACCFINDING_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("ACCFINDING_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">5</font></td>
				
	
    <td width="60%" height="19">Hazardous Waste Management Plan (HWMP).</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("HAZWASTE_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("HAZWASTE_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("HAZWASTE_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">6</font></td>
				
	
    <td width="60%" height="17"> 		
		
    Hazardous Waste Reduction Plan (HWRP).</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("HAZR_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("HAZR_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("HAZR_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">7</font></td>
				
	
    <td width="60%" height="19">Tier II Report Submitted by March 01?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("TIER2_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("TIER2_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("TIER2_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">8</font></td>
				
	
    <td width="60%" height="19">If in NYC, Community Right To Know (CRTK) 
    Submitted by March 01?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("NYCCOM_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("NYCCOM_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("NYCCOM_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">9</font></td>
				
	
    <td width="60%" height="19"> 		
				
		
    Any DOB Violations Exist?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("DOBVIO_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("DOBVIO_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("DOBVIO_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="12"><font size="2" face="Verdana">10</font></td>
				
	
    <td width="60%" height="19">Any FD Violations Exist?</td>
            
    <td width="10%" align="center" height="12"><%=(String)hashtable.get("FD_REQ")%></td>
    <td width="9%" align="center" height="12"><%=(String)hashtable.get("FD_AVI")%></td>
    <td width="17%" align="center" height="12"><%=(String)hashtable.get("FD_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">11</font></td>
				
	
    <td width="60%" height="19">If in NYC, Any ECB Violations Exist?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("ECP_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("ECP_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("ECP_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">12</font></td>
				
	
    <td width="60%" height="19">If in NYC, Any DEP Violations Exist?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("DEP_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("DEP_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("DEP_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">13</font></td>
				
	
    <td width="60%" height="19">Any State Violations exist?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("ANYSTATE_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("ANYSTATE_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("ANYSTATE_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">14</font></td>
				
	
    <td width="60%" height="19"> 		
				
		
    Any DOH/County Violations exist?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("DOH_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("DOH_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("DOH_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">15</font></td>
				
	
    <td width="60%" height="19">Are As-Built Plans Available for the Tanks?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("BUILTINPLAN_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("BUILTINPLAN_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("BUILTINPLAN_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">16</font></td>
				
	
    <td width="60%" height="19">Daily Fuel Inventory Records Available?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("FUELINVENTORY_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("FUELINVENTORY_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("FUELINVENTORY_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">17</font></td>
				
	
    <td width="60%" height="19">Method 9 Measurement for Opacity Done (If 
    Required by the Permit)?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("OPACITY_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("OPACITY_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("OPACITY_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">18</font></td>
				
	
    <td width="60%" height="19">If Refrigerant Recovery Operated, EPA 
    Registration Available or Not?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("REFRI_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("REFRI_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("REFRI_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">19</font></td>
				
	
    <td width="60%" height="19">Risk Management Plan for Chemicals?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("RISK_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("RISK_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("RISK_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">20</font></td>
				
	
    <td width="60%" height="19">Lead based Paint Notification (HUD) Provided to 
    the Tenant or Not?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("OTHER_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("OTHER_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("OTHER_REQ_COMPLIANCE")%></td>
  </tr>
  <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">21</font></td>
				
	
    <td width="60%" height="19">Laundry Discharge Testing/PH to be Tested (If 
    Permitted) / Report Submitted?</td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("LAUNDRY_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("LAUNDRY_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("LAUNDRY_AVI_COMPLIANCE")%></td>
  </tr>
   <tr>
    <td width="4%" align="center" height="16"><font size="2" face="Verdana">22</font></td>
				
	
    <td width="60%" height="19"><html:form action="/OtherEvalutionAction">Compliance Reports are Submitted?</html:form></td>
            
    <td width="10%" align="center" height="16"><%=(String)hashtable.get("COMPLIANCEREPORT_REQ")%></td>
    <td width="9%" align="center" height="16"><%=(String)hashtable.get("COMPLIANCEREPORT_AVI")%></td>
    <td width="17%" align="center" height="16"><%=(String)hashtable.get("COMPLIANCEREPORT_AVI_COMPLIANCE")%></td>
  </tr>

  
  <%
  }
						}
						else
						{
  %>
  <tr>
    <td width="100%" align="center" colspan="5" height="16">No Results</td>
  </tr>
<%}%>
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