<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.FacilityVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Printing Press</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/help/otherhelp.js" ></script>
</head>  
<%
String dep="";
String dob="";
FacilityVo obj=(FacilityVo)session.getAttribute("FACILITY_OBJECT");
int boro=obj.getBorough();
if(boro==1)
{
dep="DEP/DOH";
dob="DOB/TOWN/CITY";
}
else
{
dep="DEP";
dob="DOB";
}
%>
<script>
function addOther(updateFlag)
{
        
	if (updateFlag)
	{
	document.forms[0].methodToCall.value='update';					
	}else
	{
	document.forms[0].methodToCall.value='save';
	}
	document.forms[0].submit();	
	return true;

	
}
function ww()
{
alert("t54")
}
</script>

<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Other Regulatory Compliance&nbsp; Evaluations</SPAN> 		
				
<html:form action="/OtherEvalutionAction">		
<input type="hidden" name="methodToCall" value="reset">
<br>&nbsp;<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#336699" width="100%" id="AutoNumber1" height="479">
  <tr>
    <td width="4%" height="38" rowspan="2" bgcolor="#FFFFCC">
    <p align="center"><b><font size="2" face="Verdana">No.</font></b></td>
    <td width="60%" height="38" rowspan="2" bgcolor="#FFFFCC">
    <p align="center"><b><font face="Verdana" size="2">Description of Compliance 
    Items</font></b></td>
    <td width="18%" height="19" colspan="3" align="center" bgcolor="#FFFFCC">
    <font face="Verdana" size="2"><b>Required or Not</b></font></td>
    <td width="18%" height="19" colspan="3" align="center" bgcolor="#FFFFCC">
    <font face="Verdana" size="2"><b>Available</b></font></td>
  </tr>
  <tr>
    <td width="5%" height="19" align="center" bgcolor="#FFFFCC"><b><font size="2" face="Verdana">Y</font></b></td>
    <td width="5%" height="19" align="center" bgcolor="#FFFFCC"><b><font size="2" face="Verdana">N</font></b></td>
    <td width="8%" height="19" align="center" bgcolor="#FFFFCC"><b><font size="2" face="Verdana">
    UNKNOWN</font></b></td>
    <td width="5%" height="19" align="center" bgcolor="#FFFFCC"><b><font size="2" face="Verdana">Y</font></b></td>
    <td width="5%" height="19" align="center" bgcolor="#FFFFCC"><b><font size="2" face="Verdana">N</font></b></td>
    <td width="10%" height="19" bgcolor="#FFFFCC"><b><font size="2" face="Verdana">UNKNOWN</font></b></td>
  </tr>

     
      <tr>
    <td width="4%" height="19" align="center">1</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="spillpreventioncontroll();"  onmouseout="UnTip()" />Spill Prevention Control &amp; Counter Measure Plan 
    (SPCC).</td>
     <eespc:displayControl paramName="spcc_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="spcc_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="spcc_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="spcc_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="spcc_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="spcc_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="spcc_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="spcc_avi" value="UN" /></td>
    </eespc:displayControl>	
  </tr>
  <tr>
    <td width="4%" height="19" align="center">2</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="stromwaterdischage();"  onmouseout="UnTip()" />Storm Water Discharge Pollution Prevention Plan 
    (SWDPPP).</td>
    <eespc:displayControl paramName="stormprevention_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="stormprevention_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="stormprevention_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="stormprevention_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="stormprevention_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="stormprevention_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="stormprevention_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="stormprevention_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">3</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="epaaudit();"  onmouseout="UnTip()" />EPA Audit Under Self Audit Program.</td>
    <eespc:displayControl paramName="epaaudit_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="epaaudit_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="epaaudit_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="epaaudit_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="epaaudit_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="epaaudit_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="epaaudit_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="epaaudit_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="21" align="center">4</td>
    <td width="60%" height="21"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="allfindings();"  onmouseout="UnTip()" />All Findings Corrected Under the Self Audit?</td>
    <eespc:displayControl paramName="accfinding_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="accfinding_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="accfinding_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="accfinding_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="accfinding_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="accfinding_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="accfinding_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="accfinding_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">5</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="hardouswastemanagement();"  onmouseout="UnTip()" />Hazardous Waste Management&nbsp; Plan (HWMP).</td>
    <eespc:displayControl paramName="hazwaste_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="hazwaste_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="hazwaste_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="hazwaste_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="hazwaste_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="hazwaste_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="hazwaste_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="hazwaste_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="17" align="center">6</td>
    <td width="60%" height="17"> 		
		
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="hazordouswastereduction();"  onmouseout="UnTip()" />Hazardous Waste Reduction Plan (HWRP).</td>
    <eespc:displayControl paramName="hazr_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="hazr_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="hazr_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="hazr_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="hazr_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="hazr_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="hazr_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="hazr_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">7</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="tierIIreportsubmitted();"  onmouseout="UnTip()" />Tier II Report Submitted by March 01?</td>
    <eespc:displayControl paramName="tier2_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="tier2_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="tier2_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="tier2_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="tier2_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="tier2_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="tier2_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="tier2_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">8</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ifinnyccommunityright();"  onmouseout="UnTip()" />If in NYC, Community Right To Know (CRTK) 
    Submitted by March 01?</td>
    <eespc:displayControl paramName="nyccom_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="nyccom_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="nyccom_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="nyccom_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="nyccom_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="nyccom_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="nyccom_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="nyccom_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">9</td>
    <td width="60%" height="19"> 		
				
		
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ifinnycanydob();"  onmouseout="UnTip()" />Any DOB Violations Exist?</td>
    <eespc:displayControl paramName="dobvio_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="dobvio_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="dobvio_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="dobvio_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="dobvio_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="dobvio_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="dobvio_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="dobvio_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">10</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="anyfdviolatiionexist();"  onmouseout="UnTip()" />Any FD Violations Exist?</td>
    <eespc:displayControl paramName="fd_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="fd_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="fd_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="fd_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="fd_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="fd_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="fd_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="fd_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">11</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="anyecbviolationexist();"  onmouseout="UnTip()" />If 
    in NYC, Any ECB Violations Exist?</td>
    <eespc:displayControl paramName="ecp_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="ecp_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="ecp_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="ecp_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="ecp_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="ecp_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="ecp_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="ecp_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">12</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="anydepviolationexist();"  onmouseout="UnTip()" />If 
    in NYC, Any DEP Violations Exist?</td>
    <eespc:displayControl paramName="dep_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="dep_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="dep_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="dep_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="dep_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="dep_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="dep_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="dep_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">13</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="anystateviolationexist();"  onmouseout="UnTip()" />Any State Violations exist?</td>
    <eespc:displayControl paramName="anystate_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="anystate_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="anystate_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="anystate_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="anystate_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="anystate_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="anystate_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="anystate_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">14</td>
    <td width="60%" height="19"> 		
				
		
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="anydohcountyviolationexist();"  onmouseout="UnTip()" />Any DOH/County Violations exist?</td>
    <eespc:displayControl paramName="doh_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="doh_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="doh_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="doh_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="doh_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="doh_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="doh_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="doh_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">15</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="areasbuiltplans();"  onmouseout="UnTip()" />Are As-Built Plans Available for the Tanks?</td>
    <eespc:displayControl paramName="builtinplan_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="builtinplan_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="builtinplan_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="builtinplan_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="builtinplan_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="builtinplan_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="builtinplan_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="builtinplan_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">16</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dailyfuelinventoryrecords();"  onmouseout="UnTip()" />Daily Fuel Inventory Records Available?</td>
    <eespc:displayControl paramName="fuelinventory_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="fuelinventory_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="fuelinventory_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="fuelinventory_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="fuelinventory_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="fuelinventory_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="fuelinventory_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="fuelinventory_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">17</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="method9measurementforopacitydone();"  onmouseout="UnTip()" />Method 9 Measurement for Opacity Done 
    (If Required by the Permit)?</td>
    <eespc:displayControl paramName="opacity_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="opacity_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="opacity_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="opacity_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="opacity_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="opacity_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="opacity_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="opacity_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">18</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="refrigrentrecoveryunitregisteredornot();"  onmouseout="UnTip()" />If Refrigerant Recovery 
    Operated, EPA Registration Available or Not?</td>
    <eespc:displayControl paramName="refri_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="refri_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="refri_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="refri_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="refri_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="refri_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="refri_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="refri_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">19</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="riskmanagementplanforchemicals();"  onmouseout="UnTip()" />Risk Management Plan for Chemicals?</td>
    <eespc:displayControl paramName="risk_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="risk_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="risk_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="risk_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="risk_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="risk_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="risk_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="risk_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  <tr>
    <td width="4%" height="19" align="center">20</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="leadbasedpainttestingperformance();"  onmouseout="UnTip()" />Lead based Paint 
    Notification (HUD) Provided to the Tenant or Not?</td>
    <eespc:displayControl paramName="other_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="other_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="other_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="other_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="other_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="other_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="other_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="other_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  
    <tr>
    <td width="4%" height="19" align="center">21</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="LaundryDischargeTestingPHtobeTestedReportSubmitted();"  onmouseout="UnTip()" />Laundry 
    Discharge Testing/P<sup>H </sup> to be Tested (If Permitted) / Report Submitted?</td>
    <eespc:displayControl paramName="laundry_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="laundry_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="laundry_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="laundry_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="laundry_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="laundry_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="laundry_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="laundry_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>
  
  <tr>
    <td width="4%" height="19" align="center">22</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ComplianceReportSubmitted();"  onmouseout="UnTip()" />
    Compliance Reports are Submitted?</td>
    <eespc:displayControl paramName="compliancereport_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="compliancereport_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="compliancereport_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="compliancereport_req" value="UN" /></td>
    </eespc:displayControl>	
     <eespc:displayControl paramName="compliancereport_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="compliancereport_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="compliancereport_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="compliancereport_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>

  <tr>
    <td width="4%" height="19" align="center">23</td>
    <td width="60%" height="19"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DepartmentofEnergy();"  onmouseout="UnTip()" />
    The Plant is registered with Department of Energy (DOE)?</td>
    <eespc:displayControl paramName="depofenergy_req" formName="OtherEvalutionForm"> 
    <td width="5%" height="19" align="center"><html:radio property="depofenergy_req" value="Y" /></td>
    <td width="5%" height="19" align="center"><html:radio property="depofenergy_req" value="N" /></td>
    <td width="8%" height="19" align="center"><html:radio property="depofenergy_req" value="UN" /></td>
    </eespc:displayControl>	
    <eespc:displayControl paramName="depofenergy_avi" formName="OtherEvalutionForm"> 
    <td width="4%" height="19" align="center"><html:radio property="depofenergy_avi" value="Y" /></td>
    <td width="4%" height="19" align="center"><html:radio property="depofenergy_avi" value="N" /></td>
    <td width="10%" height="19" align="center"><html:radio property="depofenergy_avi" value="UN" /></td>
    </eespc:displayControl>
  </tr>

  </table>
<p>			
	
<br><br>
</p>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
	FacilityVo facilityVo = (FacilityVo)session.getAttribute("FACILITY_OBJECT");
	StringBuffer tempBuffer = new StringBuffer();	
	tempBuffer.append("document.forms[0].action='/eespc/AddFacility.do?methodToCall=view&id=").append(facilityVo.getId()).append("';");
	tempBuffer.append("document.forms[0].submit();");
	
%>
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Facility" 
            onClick="<%= tempBuffer%>">
      		</td>		  
			<logic:notEqual name="isComponentEditable" value="N">						
<%
			String tempStr = (String)request.getAttribute("isItForEdit");
			boolean  isEdit = false;
			if (null != tempStr && tempStr.equalsIgnoreCase("Y")){
				isEdit = true;
			}
%>						

            <td  align="right">
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" 
		onClick="<%= ((isEdit)? "addOther(true)" : "addOther(false)") %>;">			            
     	    </td>
            <td >&nbsp;
              <input type="reset" name="Submit22" value="Reset"></td>
			</logic:notEqual>						  			  
          </tr>
    </table> 
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present></html:form>        
</body>
</html>