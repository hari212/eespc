<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.StatepermitVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.bo.LsfVo,com.eespc.tracking.bo.HwasteVo,com.eespc.tracking.util.UtilityObject" %>

<html>
<head>
<title>New Facility</title>
<html:base />
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Facility.js" ></script>
<script src="/eespc/js/FuelConsumptionCalculation.js" ></script>
<script src="/eespc/js/o_FuelConsumptionCalculation.js" ></script>
<script src="/eespc/help/facilityinfohelp.js" ></script>
<Script>

var req;  
var which;
function fetchConsumption()
{
var serviceName = document.getElementById("fcyear");
var serviceNameValue=serviceName.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].fcyear.focus();
	 	return false;
	}
	var url = 'AddFacility.do?methodToCall=fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
	retrieveURL(url);
}

  function retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].hdnPreviousConsumption.value=req.responseText;
        validateFuelConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }


function o_fetchConsumption()
{

var serviceNameValue=document.forms[0].o_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].o_fcyear.focus();
	 	return false;
	}
	var url = 'AddFacility.do?methodToCall=o_fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
	o_retrieveURL(url);
}

  function o_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = o_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = o_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function o_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].o_hdnPreviousConsumption.value=req.responseText;
        o_validateFuelConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }



function isdateFieldEnteredcheckdate(formObject)
{
var tempVal1=true;	
if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0)
			{
			tempVal1=isDate(formObject.value)
			}
		else  {
			tempVal1= true;
			}
	}	
	
	return tempVal1;
}

function addpermit(updateFlag)
{ 

if(isdateFieldEnteredcheckdate(document.forms[0].fexpirationdate)==false || isdateFieldEnteredcheckdate(document.forms[0].flastproposal)==false  || isdateFieldEnteredcheckdate(document.forms[0].fnextproposal)==false)
{

return false;
} 

    
    	if (updateFlag){
    		document.forms[0].methodToCall.value='updatestatepermit';
    	}else{
    		document.forms[0].methodToCall.value='addstatepermit';
	}
	document.forms[0].submit();
           
    return true;

}

function editstatePermit(id)
{
	document.forms[0].fpermitid.value=id;
	document.forms[0].methodToCall.value='editstatepermit';
	document.forms[0].submit();
}

function deletesatatePermit(id)
{var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].fpermitid.value=id;
	document.forms[0].methodToCall.value='deletestatepermit';
	document.forms[0].submit();
}
 else
 {
 
  }
}

function NaturalGasConsumption()
{
Tip('Enter the natural gas consumption (if applicable) in cubic feet under each month box as indicated.  Upon entering the number for all applicable months, click the radio button under the latest month, then click validate button.  This will give the latest 12-month rolling figure and the consumption for the current year so far.', TITLE, 'EES HELP',WIDTH,'25' )
}

function FuelOilConsumption()
{
Tip('Enter the fuel oil consumption (if applicable) in gallons under each month box as indicated.  Upon entering the number for all applicable months, click the radio button under the latest month, then click validate button.  This will give the latest 12-month rolling figure and the consumption for the current year so far. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function addLsf()
{
alert('Lsf description Page');
document.forms[0].action="/eespc/LsfAction.do?methodToCall=reset";
alert('Lsf description Page1');
document.forms[0].submit();
alert('Lsf description Page2');
}

function srchResource()
{
var srchValue=document.forms[0].srch.value;
    if(srchValue=="")	{
       	alert("Please enter Resource name or Resource Type");
		return;
		}
document.forms[0].action="/eespc/BuildingInfo.do?methodToCall=search&srch="+srchValue;
document.forms[0].submit();
}

function deletehwaste(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
    document.forms[0].action='/eespc/HwasteAction.do?methodToCall=delete&id=' + id;
	document.forms[0].submit();
}
 else
 {
 
  }
}


function addHwaste()
{
document.forms[0].action="/eespc/HwasteAction.do?methodToCall=reset";
document.forms[0].submit();
}


function deletelsf(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].action='/eespc/LsfAction.do?methodToCall=delete&id=' + id;
	document.forms[0].submit();
}
 else
 {
 
  }

}

 

</Script>
</head>

<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
				
	<html:form action="/AddFacility">
	<html:hidden property="methodToCall" />
	<html:hidden property="id" />	
	<input type="hidden" name="fpermitid" value="<%=request.getAttribute("stateeditid")%>" >
	<html:hidden property="bctype" />
	<html:hidden property="fueldeleteConsumptionId" />
	<html:hidden property="id" />
	<table width=100%><tr><td nowrap >
	<SPAN CLASS=page_title>Facility Information</SPAN> 
	</td>
	<td width=80% align=right nowrap class="label_right">
	Source
	Search<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="searchFunc();"  onmouseout="UnTip()" />:</td>
	<td width="20%"  nowrap class="fieldleft">
	<input name="srch" size="20"><input type=button value=go onclick="srchResource();"></td></tr>
</table>
	<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#F2F2F2">
  <tr> 
    <td width="221" nowrap class="label_right"><eespc:requiredField />Client Name <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="clientname();"  onmouseout="UnTip()" />:</td>
    <td width="228"  nowrap class="fieldleft">&nbsp; 
		<eespc:displayControl paramName="clName"  formName="addFacility"> 
			<html:text property="clName" styleClass="normal" /> 
		</eespc:displayControl>
    </td>
    <td nowrap class="label_right" width="212"><eespc:requiredField />DEC ID# 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="DECID();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="226" colspan="2">&nbsp; 
		<eespc:displayControl paramName="decId" formName="addFacility"> 
			<html:text property="decId" styleClass="normal" /> 
		</eespc:displayControl>			
	</td>
			
  </tr>
  <tr> 
    <td nowrap class="label_right" width="221" rowspan="3" valign="top">
    <eespc:requiredField />Vice President 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="viceprisedent();"  onmouseout="UnTip()" />:</td>
    <td  nowrap class="fieldleft" width="228" rowspan="3">&nbsp; 
		<eespc:displayControl paramName="vPresident"  formName="addFacility"> 	
			<html:text property="vPresident" styleClass="normal" />
		</eespc:displayControl>						
	</td>
    <td class="label_right" nowrap width="212" rowspan="3" valign="top">
    <eespc:requiredField />Borough 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="brough();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="226" colspan="2">&nbsp; 
		<eespc:displayControl paramName="borough"  formName="addFacility"> 
			<html:select property="borough" styleClass="normal" >
				<html:optionsCollection name="BOROUGHS" property="dropDownValues" value="value" label="name" /> 
			</html:select>	
										
		</eespc:displayControl>&nbsp;								
	</td>
  </tr>

  <tr> 
    <% 			
			if(request.getAttribute("other")==null  || ((String)request.getAttribute("other")).equals("yes"))
			{
			%>
    <td class="fieldleft"  nowrap width="113"><b>If Other&nbsp; </b> 
		County :	&nbsp;</td>
    <td class="fieldleft"  nowrap width="113">
    <eespc:displayControl paramName="county"  formName="addFacility"> 
			<html:text property="county" styleClass="normal" /> 								
		</eespc:displayControl></td>
		  <%}%>
  </tr>
  <tr> 
      		<% 			
			if(request.getAttribute("other")==null  || ((String)request.getAttribute("other")).equals("yes"))
			{
			%>
    <td class="fieldleft"  nowrap width="113">Town :</td>
    <td class="fieldleft"  nowrap width="113">
    <eespc:displayControl paramName="town"  formName="addFacility"> 
			<html:text property="town" styleClass="normal"/> 								
		</eespc:displayControl></td>
		 <%
		 }
		 %>
  </tr>

  <tr> 
    <td class="label_right" nowrap width="221"><eespc:requiredField />Address 1 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="address1();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="addr1"  formName="addFacility"> 			
			<html:text property="addr1" styleClass="normal" />
		</eespc:displayControl>			
	</td>
    <td class="label_right" nowrap width="212"><eespc:requiredField />City <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="cityhelp();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="226" colspan="2">&nbsp; 
		<eespc:displayControl paramName="city"  formName="addFacility"> 			
			<html:text property="city" styleClass="normal" />
		</eespc:displayControl>									
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="221">Address 2 <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="address2();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="addr2"  formName="addFacility"> 				
			<html:text property="addr2" styleClass="normal" />
		</eespc:displayControl>										
	</td>
    <td class="label_right" nowrap width="212"><eespc:requiredField />State <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="statehelp();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="226" colspan="2">&nbsp; 
		<eespc:displayControl paramName="state"  formName="addFacility"> 
			<html:select property="state" styleClass="normal" >
				<html:optionsCollection name="STATES" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="221">Address 3 <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="address3();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="addr3"  formName="addFacility"> 				
			<html:text property="addr3" styleClass="normal" />
		</eespc:displayControl>		
	</td>
    <td class="label_right" nowrap width="212"><eespc:requiredField />Zip Code <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="zipcodehelp();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="226" colspan="2">&nbsp; 
		<eespc:displayControl paramName="zip" formName="addFacility"> 					
			<html:text property="zip" styleClass="normal" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" />
		</eespc:displayControl>					
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="221"><eespc:requiredField />Phone <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="phonehelp();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="phone" formName="addFacility"> 						
			<html:text property="phone" styleClass="normal"  size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].phone, 'Phone');"/>
		</eespc:displayControl>						
	</td>
    <td class="label_right" nowrap width="212"><eespc:requiredField />Fax <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="faxhelp();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="226" colspan="2">&nbsp; 
		<eespc:displayControl paramName="fax" formName="addFacility"> 							
			<html:text property="fax" styleClass="normal"  size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].fax, 'Fax');"/>
		</eespc:displayControl>							
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="221"><eespc:requiredField />Type Of Facility 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="typeoffacility();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="facilityType" formName="addFacility"> 
			<html:select property="facilityType" styleClass="normal" >
				<html:optionsCollection name="FACILITY_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>									
	</td>
    <td width="212">
    <p align="right"><b>Fuel Capping (Boiler) 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="fuelcappingboiler();"  onmouseout="UnTip()" />:</b></td>
    <td width="226" colspan="2">
    <eespc:displayControl paramName="fuelcaping" formName="addFacility"> 			
			<html:radio property="fuelcaping" value="Y"/>Yes 
			<html:radio property="fuelcaping" value="N"/>No										
	</eespc:displayControl>
			</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="221">Oil Cap 
    (Allowable Limit)	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="oilcaps();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="oilcap" formName="addFacility"> 			
				<html:text property="oilcap" styleClass="normal"/> 										
		</eespc:displayControl>									
	</td>
    <td width="212">
    <p align="right"><b>Gas Cap (Allowable Limit)	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="gascapping();"  onmouseout="UnTip()" />:</b></td>
    <td width="226" colspan="2">
    <eespc:displayControl paramName="gascap" formName="addFacility"> 			
			<html:text property="gascap" styleClass="normal"/>										
	</eespc:displayControl>
			</td>
  </tr>
  

   <tr> 
    <td class="label_right" nowrap width="221">Number Of Buildings:
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="bldgnum();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="228">&nbsp; 
		<eespc:displayControl paramName="numofbldg" formName="addFacility"> 			
				<html:text property="numofbldg" styleClass="normal"/> 										
		</eespc:displayControl>									
	</td>  
  </tr>

</table>		

  
  




</table>		
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
<TR BGCOLOR=#006699>
            <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Contact Information</FONT></TD>
          </TR>
<TR><TD >
    <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
                <TR BGCOLOR=white>
                  <TD> 
                  <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle" height="299">
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="15"><eespc:requiredField /> Contact Type 	
<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="contacttype();"  onmouseout="UnTip()" />:</td>
                <td   class="fieldleft" height="15">
				<eespc:displayControl paramName="pContact" formName="addFacility"> 
					<html:select property="pContact" styleClass="normal" >
						<html:optionsCollection name="CONTACT_TYPE" property="dropDownValues" value="value" label="name" /> 
					</html:select>								
				</eespc:displayControl>													  
				  </td>
                <td   class="fieldleft" height="15">
				<eespc:displayControl paramName="sContact" formName="addFacility">
					<html:select property="sContact" styleClass="normal" >
						<html:optionsCollection name="CONTACT_TYPE" property="dropDownValues" value="value" label="name" /> 
					</html:select>								
				</eespc:displayControl>													  				  
				  </td>
                <td   class="fieldleft" height="15">
				<eespc:displayControl paramName="tContact" formName="addFacility">
					<html:select property="tContact" styleClass="normal" >
						<html:optionsCollection name="CONTACT_TYPE" property="dropDownValues" value="value" label="name" /> 
					</html:select>								
				</eespc:displayControl>				  
				  </td>
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15"> <eespc:requiredField />First Name 	
				<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="firstname();"  onmouseout="UnTip()" />:</td>
                <td width="26%"  nowrap class="fieldleft" height="15"> 
				<eespc:displayControl paramName="pFirstName" formName="addFacility">								
					<html:text property="pFirstName" styleClass="normal" tabindex="14"/>
				</eespc:displayControl>
				</td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sFirstName" formName="addFacility"><html:text property="sFirstName" styleClass="normal" tabindex="27"/> 
                </eespc:displayControl>
                </td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tFirstName" formName="addFacility"><html:text property="tFirstName" styleClass="normal" tabindex="40"/>
                </eespc:displayControl> </td>
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="15"> <eespc:requiredField />Last Name <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="lastname();"  onmouseout="UnTip()" />:</td>
                <td width="26%"  nowrap class="fieldleft" height="15"> 
                <eespc:displayControl paramName="pLastName" formName="addFacility"><html:text property="pLastName" styleClass="normal"  tabindex="15"/>
                </eespc:displayControl></td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sLastName" formName="addFacility"><html:text property="sLastName" styleClass="normal" tabindex="28"/>
                </eespc:displayControl> 
                </td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tLastName" formName="addFacility"> <html:text property="tLastName" styleClass="normal" tabindex="41"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15">Title <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="factitle();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pTitle" formName="addFacility"><html:text property="pTitle" styleClass="normal"  tabindex="16"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sTitle" formName="addFacility"><html:text property="sTitle" styleClass="normal" tabindex="29"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tTitle" formName="addFacility"><html:text property="tTitle" styleClass="normal" tabindex="42"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="15"><eespc:requiredField />Address 1 <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="address1();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15"> 
                <eespc:displayControl paramName="pAddr1" formName="addFacility"><html:text property="pAddr1" styleClass="normal"  tabindex="17"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sAddr1" formName="addFacility"><html:text property="sAddr1" styleClass="normal" tabindex="30"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tAddr1" formName="addFacility"><html:text property="tAddr1" styleClass="normal" tabindex="43"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15">Address 2 <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="address2();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pAddr2" formName="addFacility"><html:text property="pAddr2" styleClass="normal"  tabindex="18"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sAddr2" formName="addFacility"><html:text property="sAddr2" styleClass="normal" tabindex="31"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tAddr2" formName="addFacility"><html:text property="tAddr2" styleClass="normal" tabindex="44"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="15">Address 3 <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="address3();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pAddr3" formName="addFacility"><html:text property="pAddr3" styleClass="normal"  tabindex="19"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sAddr3" formName="addFacility"><html:text property="sAddr3" styleClass="normal" tabindex="32"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tAddr3" formName="addFacility"><html:text property="tAddr3" styleClass="normal" tabindex="45"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15"><eespc:requiredField />City <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="cityhelp();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pCity" formName="addFacility"><html:text property="pCity" styleClass="normal"  tabindex="20"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sCity" formName="addFacility"><html:text property="sCity" styleClass="normal" tabindex="33"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tCity" formName="addFacility"><html:text property="tCity" styleClass="normal" tabindex="46"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="14"><eespc:requiredField />State <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="statehelp();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="14">
				<eespc:displayControl paramName="pState" formName="addFacility">
					<html:select property="pState" styleClass="normal" tabindex="21" >
						<html:optionsCollection name="STATES" property="dropDownValues" value="value" label="name" /> 
					</html:select>								
				</eespc:displayControl>				  
				  </td>
                <td  nowrap class="fieldleft" height="14">
				<eespc:displayControl paramName="sState" formName="addFacility">				
					<html:select property="sState" styleClass="normal" tabindex="34">
						<html:optionsCollection name="STATES" property="dropDownValues" value="value" label="name" /> 
					</html:select>								
				  </eespc:displayControl>
				  </td>
                <td  nowrap class="fieldleft" height="14">
				  <eespc:displayControl paramName="tState" formName="addFacility">
					<html:select property="tState" styleClass="normal" tabindex="47">
						<html:optionsCollection name="STATES" property="dropDownValues" value="value" label="name" /> 
					</html:select>								
				  </eespc:displayControl>
				  </td>
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15"><eespc:requiredField />Zip Code <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="zipcodehelp();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pZip" formName="addFacility"><html:text property="pZip" styleClass="normal"  tabindex="22" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/></eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sZip" formName="addFacility"><html:text property="sZip" styleClass="normal" tabindex="35" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/></eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tZip" formName="addFacility"><html:text property="tZip" styleClass="normal" tabindex="48" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/></eespc:displayControl></td>
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="15"><eespc:requiredField />Phone <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="phonehelp();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pPhone" formName="addFacility"><html:text property="pPhone" styleClass="normal" tabindex="23" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].pPhone, 'Primary Phone');"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sPhone" formName="addFacility"><html:text property="sPhone" styleClass="normal" tabindex="36" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].sPhone, 'Secondary Phone');"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tPhone" formName="addFacility"><html:text property="tPhone" styleClass="normal" tabindex="49" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].tPhone, 'Teritary Phone');"/>
                </eespc:displayControl></td>
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15">Mobile Phone <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="mobilephonehelp();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pMobilePhone" formName="addFacility"><html:text property="pMobilePhone" styleClass="normal" tabindex="24" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].pMobilePhone, 'Primary Mobile Phone');"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sMobilePhone" formName="addFacility"><html:text property="sMobilePhone" styleClass="normal" tabindex="37" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].sMobilePhone, 'Secondary Mobile Phone');"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tMobilePhone" formName="addFacility"><html:text property="tMobilePhone" styleClass="normal" tabindex="50" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].tMobilePhone, 'Teritary Mobile Phone');"/>
                </eespc:displayControl></td>								
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnheadright height="15">Fax <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="faxhelp();"  onmouseout="UnTip()" />:</td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pFax" formName="addFacility"><html:text property="pFax" styleClass="normal" tabindex="25" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].pFax, 'Primary Fax');"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sFax" formName="addFacility"><html:text property="sFax" styleClass="normal" tabindex="38" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].sFax, 'Secondary Fax');"/>
                </eespc:displayControl></td>
                <td  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tFax" formName="addFacility"><html:text property="tFax" styleClass="normal" tabindex="51" size="12" maxlength="12" onblur="formatPhoneNumber(document.forms[0].tFax, 'Teritary Fax');"/>
                </eespc:displayControl></td>								
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnheadright height="15">Email <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="emailhelp();"  onmouseout="UnTip()" />:</td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="pEmail" formName="addFacility"><html:text property="pEmail" styleClass="normal" tabindex="26"/>
                </eespc:displayControl></td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="sEmail" formName="addFacility"><html:text property="sEmail" styleClass="normal" tabindex="39"/>
                </eespc:displayControl></td>
                <td width="26%"  nowrap class="fieldleft" height="15">
                <eespc:displayControl paramName="tEmail" formName="addFacility"><html:text property="tEmail" styleClass="normal" tabindex="52"/>
                </eespc:displayControl></td>								
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=data_left_nocolor colspan=10 height="15" ></td>
              </tr>
            </table></TD>
                </TR>
              </TABLE>
</TD></TR>
</TABLE>
<logic:equal name="isComponentEditable" value="N" >
<br>
<b>State Permit -Reporting Deadlines<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StatePermitReportingDeadlines();"  onmouseout="UnTip()" />:</b>
 
<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;"> 
 <table BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border:3px solid #006699; border-collapse:" bordercolor="#111111" height="53">
  <tr>

   
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>(Title V)Expiration date<br>(<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>)</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Jan</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Feb</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>March</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>April</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>May</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>June</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>July</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Aug</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Sept</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Oct</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Nov</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Dec</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Last Proposal</b><br>(<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>)</td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Next Proposal</b><br>(<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>)</td>
    <td width="6%" bgcolor="#FFFFCC" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Comments</b></td>
    <td width="6%" bgcolor="#FFFFCC" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Edit</b></td>
	<td width="6%" bgcolor="#FFFFCC" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Delete</b></td>

  </tr>
  
  <%
				List permitlist	 = (List)request.getAttribute("PERMIT_LIST");
				boolean toEditPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_PERMIT"));
				int size1 =0;
				if (permitlist != null){
				
				size1 = permitlist.size();
					for (int i=0; i < size1; i++)
					{
						StatepermitVo statePermitInfo = (StatepermitVo)permitlist.get(i);
						boolean isPermitEditable = false;
						int permitId = statePermitInfo.getId();
						String permitIdStr = permitId +"";
						String tempStr = String.valueOf(request.getAttribute("editid"));
						
						if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditPermit)
						{
							isPermitEditable = true;
						} 
						
						
					out.println("<tr class=\"evenRowStyle\">");
					
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fexpirationdate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(statePermitInfo.getFexpirationdate()),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fjan", UtilityObject.checkNullAndFill(statePermitInfo.getFjan(),""),null));
					out.println("</td>");
					
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "ffeb", UtilityObject.checkNullAndFill(statePermitInfo.getFfeb(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fmar", UtilityObject.checkNullAndFill(statePermitInfo.getFmar(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fapril", UtilityObject.checkNullAndFill(statePermitInfo.getFapril(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fmay", UtilityObject.checkNullAndFill(statePermitInfo.getFmay(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fjune", UtilityObject.checkNullAndFill(statePermitInfo.getFjune(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fjuly", UtilityObject.checkNullAndFill(statePermitInfo.getFjuly(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "faug", UtilityObject.checkNullAndFill(statePermitInfo.getFaug(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fsep", UtilityObject.checkNullAndFill(statePermitInfo.getFsep(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "foct", UtilityObject.checkNullAndFill(statePermitInfo.getFoct(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fnov", UtilityObject.checkNullAndFill(statePermitInfo.getFnov(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fdec", UtilityObject.checkNullAndFill(statePermitInfo.getFdec(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "flastproposal", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(statePermitInfo.getFlastproposal()),""),null));
					out.println("</td>");


					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fnextproposal", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(statePermitInfo.getFnextproposal()),""),"DATES"));
					out.println("</td>");

					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fcomments", UtilityObject.checkNullAndFill(statePermitInfo.getFcomments(),""),null));
					out.println("</td>");

					
					
					
					
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isPermitEditable){
							out.println("<a href=\"javascript:editstatePermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");	
													
					out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isPermitEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deletesatatePermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
							}
				else
				{
				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\">");
				}
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");			
				  out.println("</tr>");
					
					
				}
					
			}
			
			
			if (!toEditPermit){
  
  
  %>
  <tr>
 
    
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fexpirationdate" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fjan" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="ffeb" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fmar" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fapril" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fmay" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fjune" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fjuly" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="faug" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fsep" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="foct" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fnov" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fdec" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="flastproposal" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fnextproposal" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fcomments" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    &nbsp;</td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    &nbsp;</td> 
  </tr>
  <%}%>
  <tr>
    <td width="95%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" colspan="18">
    <p align="right"><input type="button" name="Button22" value="<%= ((toEditPermit)? "Update" : "Add") %>" onClick="<%= ((toEditPermit)? "addpermit(true)" : "addpermit(false)") %>;"></td>
  </tr> 
</table>
</div>
    </td>

  </tr>
</table>


<p>
<br>
<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
    
	<jsp:include page="AnnualFuelConsumption.jsp" />
	
	</div>
    </td>

  </tr>
</table>
	<br>
	<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="AnnualFuelConsumptionoil.jsp" />
	</div>
    </td>

  </tr>
</table>
<br>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#006699" width="100%" id="AutoNumber2" bgcolor="#FFFFCC">
  <tr>
    <td width="100%"><b><a href="/eespc/OtherEvalutionAction.do?methodToCall=view">Other 
    Compliance Evaluation:-</a><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="OtherComplianceEvaluation();"  onmouseout="UnTip()" /></b></td>
  </tr>
  <tr>
    <td width="100%"><b><a href="/eespc/documentAction.do?methodToCall=reset&&separateFile=1">Agency Inspection Documents (AID):-</a><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="DocumentsAndReports();"  onmouseout="UnTip()" /></b></td>
  </tr>
</table>

<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="6" bgcolor="#006699">
    <font color="#FFFFFF"><b>List of Staff and their Fire Department (FD) 
    Certificate of Fitness (C of F) Status<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="cofo();"  onmouseout="UnTip()" /> :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Staff Name</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Certification Number</b></td>
    <td width="10%" height="18" bgcolor="#FFFFCC" align="center"><b>Certification Issue Date</b></td>
    <td width="10%" height="18" bgcolor="#FFFFCC" align="center"><b>Expiration Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List lsfList = (List)request.getAttribute("LSF_LIST");

  	if(lsfList!=null)
  	{
		int lsf_size = lsfList .size();
		
		for (int p=0; p < lsf_size; p++)
		{
			LsfVo lsfvo = (LsfVo )lsfList .get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><a href="/eespc/LsfAction.do?methodToCall=view&id=<%=lsfvo.getId()%>"><%=lsfvo.getFirstname()%> <%=lsfvo.getLastname()%> </a></td>
    <td width="20%" height="15" align="center"><%=lsfvo.getCertificatenumber()%></td>
    <td width="10%" height="15" align="center"><%=lsfvo.getIssuedate("MM/dd/yyyy")%></td>
    <td width="10%" height="15" align="center"><%=lsfvo.getExpirationdate("MM/dd/yyyy")%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/LsfAction.do?methodToCall=edit&id=<%=lsfvo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deletelsf('<%=lsfvo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="6">
    <p align="right"><input type="button" name="addf" onclick="addLsf();" value="Add New"></td>
  </tr>
</table>
<p>&nbsp;


	 

<br>

<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="6" bgcolor="#006699">
    <font color="#FFFFFF"><b>Hazardous  Waste (HW) Generation and Disposal Summary<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="hwaste();"  onmouseout="UnTip()" />:</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Manifest Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Date of the Manifest</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Total Waste(lbs)</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>EPA ID</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List hwasteList = (List)request.getAttribute("HWASTE_LIST");

  	if(hwasteList!=null)
  	{
		int hwaste_size = hwasteList .size();
		//out.println(""+hwaste_size);
		for (int p=0; p < hwaste_size; p++)
		{
			HwasteVo hwastevo = (HwasteVo)hwasteList .get(p);
  %>
  <tr>
  
    <td width="20%" height="15" align="center"><a href="/eespc/HwasteAction.do?methodToCall=view&id=<%=hwastevo.getId()%>"><%=hwastevo.getManifastno()%></a></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(hwastevo.getDateOftheManifest())%></td>
    <td width="20%" height="15" align="center"><%=hwastevo.getHazardousTotalWaste()%></td>
    <td width="20%" height="15" align="center"><%=hwastevo.getEpaId()%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/HwasteAction.do?methodToCall=edit&id=<%=hwastevo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deletehwaste('<%=hwastevo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>
  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">
<%
}
%>	    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
 
  <tr>
    <td width="100%" height="15" colspan="6">
    <p align="right"><input type="button" name="addf" onclick="addHwaste();" value="Add New"></td>
  </tr>
</table> 



<br>
</logic:equal>
<logic:notEmpty name="BUILDING_LIST">
</p>
</p>

<eespc:table dataSource="BUILDING_LIST"
  formName="form1"
  noDataFoundMessage="<CENTER><SPAN CLASS=error>There is no building to this facility.</SPAN></CENTER>" >

  <eespc:tableHeaderCellDef id="h_buildingName" label="Building Name" bodyCellId="b_buildingName"/>
  <eespc:tableHeaderCellDef id="h_buildingId" label="Building Id" bodyCellId="b_buildingId"/>
  <eespc:tableHeaderCellDef id="h_dobBin" label="DOB Bin Number" bodyCellId="b_dobBin"/>
  <eespc:tableHeaderCellDef id="h_blockNumber" label="Block Number" bodyCellId="b_blockNumber"/>
  <eespc:tableHeaderCellDef id="h_lotNumber" label="Lot Number" bodyCellId="b_lotNumber"/>  
  
  <eespc:tableBodyCellDef id="b_crId" method="getId"/>
  <eespc:tableBodyCellDef id="b_crId1" method="getId"/>
  <eespc:tableBodyCellDef id="b_crIdd" method="getId"/>
  <eespc:tableBodyCellDef id="b_buildingName" method="getBuildingName"/>
  <eespc:tableBodyCellDef id="b_buildingId" method="getBuildingId"/>
  <eespc:tableBodyCellDef id="b_dobBin" method="getDobBinNumber"/>
  <eespc:tableBodyCellDef id="b_blockNumber" method="getBlockNumber"/>  
  <eespc:tableBodyCellDef id="b_lotNumber" method="getLotNumber"/>    

  <eespc:tr>
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="buildingname();"  onmouseout="UnTip()" />$$h_buildingName$$</eespc:th>
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="buildingid();"  onmouseout="UnTip()" />$$h_buildingId$$</eespc:th>
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="dobbinnumber();"  onmouseout="UnTip()" />$$h_dobBin$$</eespc:th>
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="blocknumber();"  onmouseout="UnTip()" />$$h_blockNumber$$</eespc:th>
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="lotnumber();"  onmouseout="UnTip()" />$$h_lotNumber$$</eespc:th>	  	  	  
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="edit();"  onmouseout="UnTip()" />Edit</eespc:th>	  
      <eespc:th><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="delet();"  onmouseout="UnTip()" />Delete</eespc:th>
  </eespc:tr>

  <eespc:tr>
      <eespc:tb><a href="javascript:viewBuilding('$$b_crIdd$$');">$$b_buildingName$$</a></eespc:tb>
      <eespc:tb>$$b_buildingId$$</eespc:tb>
      <eespc:tb>$$b_dobBin$$</eespc:tb>
      <eespc:tb>$$b_blockNumber$$</eespc:tb>
      <eespc:tb>$$b_lotNumber$$</eespc:tb>	  	  	  
      <eespc:tb><a href="javascript:editBuilding('$$b_crId$$');">
<img src="../images/edit.jpg" border="0" width="42" height="13"></a></eespc:tb>	
    

	<%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>

  <eespc:tb><a href="javascript:deleteBuilding('$$b_crId1$$');">
<img src="../images/delete.jpg" border="0" width="42" height="13"></a></eespc:tb>		
<%	

}
else
{
%>

  <eespc:tb>
<img src="../images/delete.jpg" border="0" width="42" height="13"></eespc:tb>
<%
}

%>	 
	  
  
  </eespc:tr>
</eespc:table>
</logic:notEmpty>
		<br>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
				<c:choose>
					<c:when test="${addFacility.map.methodToCall == 'edit'}">
						<td width="47%" align="right"><input type="reset" name="Submit22" value="Reset"></td>
						<td width="53%">&nbsp; 					

						<html:button property="button" onclick="update();">Update</html:button>
					</c:when>
					<c:when test="${addFacility.map.methodToCall == 'initial' || addFacility.map.methodToCall == 'add' }">
						<td width="47%" align="right"><input type="reset" name="Submit22" value="Reset"></td>
						<td width="53%">&nbsp; 					
						<html:button property="button" onclick="goToResult();" >Save &amp; Continue</html:button>   	
					</c:when>	
				</c:choose>	

				<logic:equal name="isComponentEditable" value="N" >
					<td align="center">&nbsp; 
						<html:submit onclick="addBuilding();">Add Building</html:submit>
						
				</logic:equal>
			</td>
          </tr>
        </table> 
<br>
<logic:equal name="isComponentEditable" value="N" >
<b><font size="2" face="Verdana" color="#006699">Action:If You Want To Add New Building, Please Click Add Building Button.</font></b>		
</logic:equal>
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>

</html:form>		
</p>

</body>

</html>