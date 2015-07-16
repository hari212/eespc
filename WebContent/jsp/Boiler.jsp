<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.BoilerPermitInfoVo,com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.bo.BoilerAnnualTuneUpVo,com.eespc.tracking.bo.BoilerValveTestVo" %>
<%@ page import="com.eespc.tracking.bo.BoilerFuelConsumptionVo" %> 
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New Building</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Boiler.js" ></script>
<script src="/eespc/js/FuelConsumptionCalculation.js" ></script>
<script src="/eespc/js/o_FuelConsumptionCalculation.js" ></script>
<script src="/eespc/help/boilerhelp.js" ></script>

<script>

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
	var url = 'Boiler.do?methodToCall=fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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
	var url = 'Boiler.do?methodToCall=o_fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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


function enablefuelcapping()
{

document.forms[0].B_oilfuelgapping.disabled=false;
document.forms[0].B_oilemmisionfactor.disabled=false;
document.forms[0].B_gasfuelgapping.disabled=false;
document.forms[0].B_gasemmisionfactor.disabled=false;

}

function disConnect()
{

if(document.forms[0].ModifiedInPast.value==-1 || document.forms[0].ModifiedInPast.value==2 || document.forms[0].ModifiedInPast.value==3 || document.forms[0].ModifiedInPast.value==4)
{
document.forms[0].DisconnectedYear.disabled=false;
}
else
{
document.forms[0].DisconnectedYear.value="";
document.forms[0].DisconnectedYear.disabled=true;
}
}

function searchExist(){
	var url = "/eespc/BoilerSearchInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}
</script>
<script>
 String.prototype.replaceAll = function(pcFrom, pcTo){  
      var i = this.indexOf(pcFrom);   
      var c = this;
      while (i > -1){  
      c = c.replace(pcFrom, pcTo); 
      i = c.indexOf(pcFrom);  
      }  
      return c; 
      }

function fileupload()
{
document.forms[0].methodToCall.value='fileupload';
document.forms[0].submit();	
return true;
}

function editfile(a,b)
{
var name = prompt("Please Enter name.If File, Please Enter Name With Extension(Like sample.pdf)", "");
if(name.length==0)
{
alert("Invalid Folder Name.");
return false;
}
var name1=name.replace(/&/g, " and ");
var name2=name1.replace(/'/g, "-");
document.forms[0].foldername.value=a.replaceAll("x***x","'");
document.forms[0].renamefoldername.value=name2;
document.forms[0].methodToCall.value='editfile';
document.forms[0].submit();	
return true;
}
function deletefile(a,b)
{
var where_to= confirm("Do you want delete this?");
 if (where_to== true)
 {
	document.forms[0].foldername.value=a.replaceAll("x***x","'");
	document.forms[0].methodToCall.value='deletefile';
	document.forms[0].submit();
	return true;
}
 else
 { 
  }
 }


function addnewfolder()
{
var name = prompt("Folder name", "");
if(name.length==0)
{
alert("Invalid Folder Name.");
return false;
}
var name1=name.replace(/&/g, " and ");
var name2=name1.replace(/'/g, "-");
document.forms[0].foldername.value=name2;
document.forms[0].methodToCall.value='addnewfolder';
document.forms[0].submit();	
return true;
}

 </script>
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


<body onload="init();">
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<html:form action="/Boiler" enctype="multipart/form-data" > 
<html:hidden property="methodToCall" />
<html:hidden property="bctype" />
<html:hidden property="fueldeleteConsumptionId" />
<html:hidden property="id" />

<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<SPAN CLASS=page_title>Boiler</SPAN><br>		
	<br>			
<table width="100%" border="1" cellspacing="0" cellpadding="0" height="945" bordercolor="#F2F2F2" style="border-collapse: collapse">
  <tr> 
    <td class="label_right" height="24" width="293"><eespc:requiredField/>ID #<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td width="699"  nowrap class="fieldleft" height="24" colspan="3">&nbsp; 
		<eespc:displayControl paramName="B_facilityDesignatedId" formName="boiler"> 
			<html:text property="B_facilityDesignatedId" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
		</eespc:displayControl>
		(Facility Designated)
  </tr>
   <tr> 
    <td class="label_right" height="26" width="293"><eespc:requiredField/>Stack Exhausting <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StackExhausting();"  onmouseout="UnTip()" />: </td>
    <td  nowrap class="fieldleft" height="26" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_stackFromName" formName="boiler"> 
			<html:hidden property="B_stackFrom" /> 		
			<html:text property="B_stackFromName" readonly="true" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchStack();">
		</eespc:displayControl>
	  </td>
  </tr>
  
  <tr> 
    <td width="293" nowrap class="label_right" height="15">Floor<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Floor();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_floor" formName="boiler"> 
			<html:text property="B_floor" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" nowrap height="15" width="293">DEC Source ID # <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="DECSourceID();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_stateId" formName="boiler"> 
			<html:text property="B_stateId" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Primary Use <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="PrimaryUse();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_primaryUse" formName="boiler"> 
			<html:select property="B_primaryUse" onchange="displayControl()" styleClass="normal" >
				<html:optionsCollection name="BOILER_PRIMARY_USE" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	 
		 <logic:equal name="boiler" property="B_primaryUse" value="4"> 
		
		<b>if other</b>,<eespc:displayControl paramName="B_primaryuseother" formName="boiler"> 
			<html:text property="B_primaryuseother" styleClass="normal" /> 
		</eespc:displayControl>
 </logic:equal>
  <logic:equal name="boiler" property="B_primaryUse" value="Other"> 
		
		<b>if other</b>,<eespc:displayControl paramName="B_primaryuseother" formName="boiler"> 
			<html:text property="B_primaryuseother" styleClass="normal" /> 
		</eespc:displayControl>
 </logic:equal>

	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Year Installed<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="YearInstalled();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_yearInstalled" formName="boiler"> 
			<html:text property="B_yearInstalled" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Manufacturer<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Manufacturer();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_manufacturer" formName="boiler"> 
			<html:text property="B_manufacturer" styleClass="normal" /> 
		</eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Make<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Make();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_make" formName="boiler"> 
			<html:text property="B_make" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Model<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Model();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_model" formName="boiler"> 
			<html:text property="B_model" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Serial #<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Serial();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_serial" formName="boiler"> 
			<html:text property="B_serial" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Burner Make <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="BurnerMake();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_burnerMake" formName="boiler"> 
			<html:text property="B_burnerMake" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Burner Model <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="BurnerModel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_burnerModel" formName="boiler"> 
			<html:text property="B_burnerModel" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">
    <eespc:requiredField />Capacity <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Capacity();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp; 
		<eespc:displayControl paramName="B_capacity" formName="boiler"> 
			<html:text property="B_capacity" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 
		</eespc:displayControl>
      (MM BTU / Hr)</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">
    <eespc:requiredField />Burner Type <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="BurnerType();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_burnerType" formName="boiler"> 		
			<html:radio property="B_burnerType" value="Y"  onclick="burnerTypeSetting();"/>Dual Fuel 
			<html:radio property="B_burnerType" value="N"  onclick="burnerTypeSetting();"/>Single Fuel 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">
    <eespc:requiredField />Primary Fuel <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="PrimaryFuel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_primaryFuel" formName="boiler"> 
			<html:select property="B_primaryFuel" styleClass="normal" onchange="fuelChanged();">
				<html:optionsCollection name="BOILER_PRIMARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">
    <eespc:requiredField />Secondary Fuel <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="SecondaryFuel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_secondaryFuel" formName="boiler"> 
			<html:select property="B_secondaryFuel" styleClass="normal" onchange="fuelChanged();" >
				<html:optionsCollection name="BOILER_SECONDARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Firing Rate - Oil <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="FiringRateOil();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_oilFiringRate" formName="boiler"> 
			<html:text property="B_oilFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (GPH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Firing Rate - Natural Gas <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="FiringRateNaturalGas();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_naturalGasFiringRate" formName="boiler"> 
			<html:text property="B_naturalGasFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (CFH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="26" width="293">Fuel From Tank <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Fuelfromtank();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="26" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_fuelFromName" formName="boiler"> 
			<html:hidden property="B_fuelFrom" /> 
			<html:text property="B_fuelFromName" readonly="true" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchFuel();">
		</eespc:displayControl>
      </td>
  </tr>
  
  
	<tr> 
    <td class="label_right" nowrap width="293"><%=dob%> Filing?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Dobfiling();"  onmouseout="UnTip()"/>:</td>
    <td  height="20" class="fieldleft"  nowrap width="672">&nbsp; 				
		<eespc:displayControl paramName="dobfiling" formName="boiler"> 		
			<html:radio property="dobfiling" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="dobfiling" value="N" onclick="displayControl();"/>No
			<html:radio property="dobfiling" value="N/A" onclick="displayControl();"/>N/A			
		</eespc:displayControl>	 	
	</td>
  </tr>
  
  
  <logic:equal name="boiler" property="dobfiling" value="Y">   
    <tr> 
    <td class="label_right" nowrap height="15" width="293"><%=dob%> Filing/Job #<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="DOBPERMIT();"  onmouseout="UnTip()"/>:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_nycDob" formName="boiler"> 
			<html:text property="B_nycDob" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
    </tr>  
 
    <tr> 
    <td class="label_right" nowrap width="293"><%=dob%> Sign Off<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="doboff();"  onmouseout="UnTip()"/>:</td>
    <td  height="20" class="fieldleft"  nowrap width="672">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="boiler"> 		
			<html:radio property="dobsignoff" value="Y"/>Yes
			<html:radio property="dobsignoff" value="N"/>No							 
		</eespc:displayControl>	 	
	 </td>
     </tr>
  </logic:equal>
     
 
    <tr> 
    <td class="label_right" nowrap width="293">Fire department Approval<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="FireDeptcertificateofapproval();"  onmouseout="UnTip()"/>:</td>
    <td  height="20" class="fieldleft"  nowrap width="672">&nbsp; 				
		<eespc:displayControl paramName="firedepartmentapproval" formName="boiler"> 		
			<html:radio property="firedepartmentapproval" value="Y"/>Yes 
			<html:radio property="firedepartmentapproval" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">MEA #<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="MEA();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_mea" formName="boiler"> 
			<html:text property="B_mea" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>

  <tr> 
    <td class="label_right" height="15" width="293" valign="top"><b><%=dep%> Permit Status?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitStatus();"  onmouseout="UnTip()" />: </td>
    <td> 
    <eespc:displayControl paramName="depPermit" formName="boiler">&nbsp; 
      <html:radio property="depPermit" value="Y" onclick="displayControl();"/>Yes
      <html:radio property="depPermit" value="N" onclick="displayControl();"/>No 
      <html:radio property="depPermit" value="N/A" onclick="displayControl();"/>N/A
    </eespc:displayControl>
    </td>
  </tr>
  
   <logic:equal name="boiler" property="depPermit" value="Y"> 
  <tr> 
    <td class="label_right" nowrap height="15" width="293"><%=dep%> #<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="DEP();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_dep" formName="boiler"> 
			<html:text property="B_dep" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>  
    
    <tr> 
    <td class="label_right" height="15" width="293" valign="top"><b>Does the Permit Expire?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitStatus();"  onmouseout="UnTip()" />: </td>
    <td> 
    <eespc:displayControl paramName="depPermitExpire" formName="boiler">&nbsp; 
      <html:radio property="depPermitExpire" value="Y" onclick="displayControl();"/>Yes
      <html:radio property="depPermitExpire" value="N" onclick="displayControl();"/>No       
      </eespc:displayControl> </td>
  </tr> 
   </logic:equal> 
   
   
   <tr> 
    <td class="label_right" height="15" width="293" valign="top"><b>Is Annual tune-up done for the boiler?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AnnualTuneUp();"  onmouseout="UnTip()" />: </td>
    <td> 
    <eespc:displayControl paramName="boilerTuneup" formName="boiler">&nbsp; 
      <html:radio property="boilerTuneup" value="Y" onclick="displayControl();"/>Yes
      <html:radio property="boilerTuneup" value="N" onclick="displayControl();"/>No 
      <html:radio property="boilerTuneup" value="N/A" onclick="displayControl();"/>N/A
      </eespc:displayControl> </td>
  </tr>
  
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Schedule C <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="ScheduleC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_sechduelC" formName="boiler"> 		
			<html:radio property="B_sechduelC" value="Y"  />Yes 
			<html:radio property="B_sechduelC" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15" width="293">Plan Approval <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="PlanApproval();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_planApproval" formName="boiler"> 		
			<html:radio property="B_planApproval" value="Y"  />Yes 
			<html:radio property="B_planApproval" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
 
  <tr> 
    <td class="label_right" height="15" width="293">Stack Test required by State Agency <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StackTestrequiredbyStateAgency();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_stackTestRequired" formName="boiler"> 		
			<html:radio property="B_stackTestRequired" value="Y" onclick="displayControl();" />Yes 
			<html:radio property="B_stackTestRequired" value="N" onclick="displayControl();" />No
			<html:radio property="B_stackTestRequired" value="N/A" onclick="displayControl();"/>N/A 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
<logic:equal name="boiler" property="B_stackTestRequired" value="Y">    
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Other Boilers Combined with the Test 
      <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="OtherBoilersCombinedwiththetest();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_otherBoilersCombined" formName="boiler"> 		
			<html:radio property="B_otherBoilersCombined" value="Y"  />Yes 
			<html:radio property="B_otherBoilersCombined" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Parameters
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Parameters();"  onmouseout="UnTip()" />:</strong> &nbsp; 
		<eespc:displayControl paramName="B_parameters1" formName="boiler"> 		
			<html:checkbox property="B_parameters1" value="Y"/>	
		</eespc:displayControl>	  											  
      <b>NOX</b> &nbsp;	
		<eespc:displayControl paramName="B_parameters2" formName="boiler"> 		
			<html:checkbox property="B_parameters2" value="Y"/>	
		</eespc:displayControl>	  											  
      <b>SO2</b> &nbsp;  	
		<eespc:displayControl paramName="B_parameters3" formName="boiler"> 		
			<html:checkbox property="B_parameters3" value="Y"/>	
		</eespc:displayControl>	  											  
      <b>PM</b> 	
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Test On Fuel <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="TestOnFuel();"  onmouseout="UnTip()" />: </strong> &nbsp; 
		<eespc:displayControl paramName="B_testOnFuel" formName="boiler"> 		
			<html:checkbox property="B_testOnFuel" value="Y"/>	
		</eespc:displayControl>	  											  
      <b>NG</b> &nbsp;<strong> Oil Type : </strong> 
		<eespc:displayControl paramName="B_testOnFuelOilType" formName="boiler"> 
			<html:select property="B_testOnFuelOilType" styleClass="normal" >
				<html:optionsCollection name="BOILER_OIL_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Stack Test Protocol Submitted to State Agency
     <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StacktestprotocolsubmittedtoStateAgency();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_stackTestProtSubmited" formName="boiler"> 		
			<html:radio property="B_stackTestProtSubmited" value="Y"  />Yes 
			<html:radio property="B_stackTestProtSubmited" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" height="14" width="293"></td>
    <td class="fieldleft"  nowrap height="14" colspan="3" width="699"><strong>Test Conducted by <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="TestConductedby();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_testConductedBy" formName="boiler"> 
			<html:text property="B_testConductedBy" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Test Report 
    Submitted to State Agency 
      <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="TestReportsubmittedtoStateAgency();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_testReportSubmited" formName="boiler"> 		
			<html:radio property="B_testReportSubmited" value="Y"  />Yes 
			<html:radio property="B_testReportSubmited" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>
    Stack Test Date Required by the Permit (<b><font color="#006699">mm/dd/yyyy</font></b>) <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StackTestDaterequiredbythepermit();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_protocolSubmitDate" formName="boiler"> 
			<html:text property="B_protocolSubmitDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Test Passed/Compliance <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="TestPassedCompliance();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_boilerTestPassed" formName="boiler"> 		
			<html:radio property="B_boilerTestPassed" value="Y" onclick="displayControl();"  />Yes 
			<html:radio property="B_boilerTestPassed" value="N" onclick="displayControl();"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
<logic:equal name="boiler" property="B_boilerTestPassed" value="N">      
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>
    Retest Planned <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="ReTestPlanned();"  onmouseout="UnTip()" />: </strong>
		<eespc:displayControl paramName="B_retestPlanned" formName="boiler"> 		
			<html:radio property="B_retestPlanned" value="Y"  />Yes 
			<html:radio property="B_retestPlanned" value="N"  />No 
		</eespc:displayControl>	  											  

	</td>
  </tr>
</logic:equal>   
 <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Stack Test Date (<b><font color="#006699">mm/dd/yyyy</font></b>) <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StackTestDate();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_StackTestDate" formName="boiler"> 
			<html:text property="B_StackTestDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>
	</td>
  </tr>
   
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Next Stack Test Date (<b><font color="#006699">mm/dd/yyyy</font></b>) <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="NextStackTestDate();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_nextStackTestDate" formName="boiler"> 
			<html:text property="B_nextStackTestDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" height="15" width="293"></td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699"><strong>Note<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="NextStackTestDateNote();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="B_footnote" formName="boiler"> 
			<html:text property="B_footnote" styleClass="normal" size="20"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
</logic:equal>  
  <tr> 
    <td class="label_right" height="15" width="293">Is the Boiler Subject to NSPS ?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="IstheboilersubjecttoNSPS();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_isBoilerSubjectToNspc" formName="boiler"> 		
			<html:radio property="B_isBoilerSubjectToNspc" value="Y" onclick="displayControl();"  />Yes 
			<html:radio property="B_isBoilerSubjectToNspc" value="N" onclick="displayControl();"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293">Was Boiler or Burner Modified in the 
    Past ?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Wasboilerorburnermodifiedinthepast();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_blrModifiedInPast" formName="boiler"> 		
			<html:radio property="B_blrModifiedInPast" value="Y"  onclick="document.forms[0].B_blrModifiedOn.disabled=false;"/>Yes 
			<html:radio property="B_blrModifiedInPast" value="N"  onclick="document.forms[0].B_blrModifiedOn.value='';document.forms[0].B_blrModifiedOn.disabled=true;"/>No 
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Modified on (<b><font color="#006699">mm/dd/yyyy</font></b>) :</strong>
		<eespc:displayControl paramName="B_blrModifiedOn" formName="boiler"> 
			<html:text property="B_blrModifiedOn" styleClass="normal" /> 
		</eespc:displayControl>

	   </td>
  </tr>
  <tr> 
    <td class="label_right" height="30" width="293">Permit to Construct or Modify 
    Submitted to 
      DEC,&nbsp;<%=dep%> &amp; &nbsp;<%=dob%> <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="PermitforconstructormodifysubmitedtoDECDEPDOB();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_isModifyPermitSub" formName="boiler"> 		
			<html:radio property="B_isModifyPermitSub" value="Y"  />Yes 
			<html:radio property="B_isModifyPermitSub" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="30" width="293">Was There any Emission Credit 
    Required as Part of 
      the 6 NYCRR Part 231 ?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Wasthereanyemissioncreditrequiredaspartofthe6NYCRRPart231();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_anyEmission" formName="boiler"> 		
			<html:radio property="B_anyEmission" value="Y"  />Yes 
			<html:radio property="B_anyEmission" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="30" width="293">Was the Boiler Subject to Federal PSD (40 CFR Part 
      52)<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="WastheboilersubjecttoFederalPSD40CFRPart52();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_blrSubjectToFederal" formName="boiler"> 		
			<html:radio property="B_blrSubjectToFederal" value="Y"  />Yes 
			<html:radio property="B_blrSubjectToFederal" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="293">Fuel Capping <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="FuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
    <eespc:displayControl paramName="B_fuelCaping" formName="boiler"> 		
			<html:radio property="B_fuelCaping" value="Y" onclick="displayControl();" />Yes 
			<html:radio property="B_fuelCaping" value="N" onclick="displayControl();" />No 
		</eespc:displayControl>			
	</td>
  </tr>
  
  
  <logic:equal name="boiler" property="B_fuelCaping" value="Y">  
  <tr> 
  
    <td class="label_right" height="15" width="293">If Fuel Capping Yes, Gas Fuel Capping <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="IfFuelCappingYesGasFuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_gasfuelgapping" formName="boiler"> 
			<html:text property="B_gasfuelgapping" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;CFY&nbsp;&nbsp;<b>Gas NOx Emission Factor:<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="GasNOxEmmisionfactor();"  onmouseout="UnTip()" /> 
    </b>:<eespc:displayControl paramName="B_gasemmisionfactor" formName="boiler"> 
			<html:text property="B_gasemmisionfactor" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/million cu.ft.
	</td>
	
  </tr>
  
  
  
  <tr> 
    <td class="label_right" height="15" width="293">If Fuel Capping Yes, Oil Fuel Capping <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="IfFuelCappingYesOilFuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_oilfuelgapping" formName="boiler"> 
			<html:text property="B_oilfuelgapping" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;GPY&nbsp;&nbsp;<b>Oil NOx Emission Factor<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="OilNOxEmmisionfactor();"  onmouseout="UnTip()" />:
    </b>:<eespc:displayControl paramName="B_oilemmisionfactor" formName="boiler"> 
			<html:text property="B_oilemmisionfactor" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/1000 gal
	</td>
  </tr>
   <tr> 
    <td class="label_right" height="15" width="293">SO2 Emission Factor, Oil <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="SO2EmissionFactorOil();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_oilso2" formName="boiler"> 
			<html:text property="B_oilso2" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp; lb/1000 gal &nbsp;&nbsp;<b>Gas
    </b><input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Gas();"  onmouseout="UnTip()" />:<eespc:displayControl paramName="B_gasso2" formName="boiler"> 
			<html:text property="B_gasso2" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/million cu.ft.
	</td>
  </tr>
   <tr> 
    <td class="label_right" height="15" width="293">SO2 Allowable<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="SO2Allowable();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_so2allowable" formName="boiler"> 
			<html:text property="B_so2allowable" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;Tons/Yr &nbsp;&nbsp;<b>NOX Allowable<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="NOXAllowable();"  onmouseout="UnTip()" /></b>:<eespc:displayControl paramName="B_noxallowable" formName="boiler"> 
			<html:text property="B_noxallowable" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;Tons/Yr
	</td>
  </tr>
</logic:equal>
<logic:equal name="boiler" property="B_isBoilerSubjectToNspc" value="Y">  
  <tr> 
    <td class="label_right" height="30" width="293">Is an Opacity Monitor Installed as per 40 CFR Part 60?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Isanopacitymonitorinstalledasper40CFRPart60();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_isOpacityMonInst" formName="boiler"> 		
			<html:radio property="B_isOpacityMonInst" value="Y"  />Yes 
			<html:radio property="B_isOpacityMonInst" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="30" width="293">Was Performance Test Protocol 
    Submitted to DEC ?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="WasperformancetestprotocolsubmittedtoDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_prfmTestProtSub" formName="boiler"> 		
			<html:radio property="B_prfmTestProtSub" value="Y" onclick="document.forms[0].B_prfmTestProtSubDate.disabled=false;"  />Yes 
			<html:radio property="B_prfmTestProtSub" value="N" onclick="document.forms[0].B_prfmTestProtSubDate.value='';document.forms[0].B_prfmTestProtSubDate.disabled=true;"  />No 
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Date (<b><font color="#006699">mm/dd/yyyy</font></b>) :</strong> 
	  		<eespc:displayControl paramName="B_prfmTestProtSubDate" formName="boiler"> 
			<html:text property="B_prfmTestProtSubDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>

	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="30" width="293">Was Performance Test Report 
    Submitted to DEC ?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="WasperformancetestreportsubmittedtoDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_prfmTestRptSub" formName="boiler"> 		
			<html:radio property="B_prfmTestRptSub" value="Y" onclick="document.forms[0].B_prfmTestRptSubDate.disabled=false;" />Yes 
			<html:radio property="B_prfmTestRptSub" value="N" onclick="document.forms[0].B_prfmTestRptSubDate.value='';document.forms[0].B_prfmTestRptSubDate.disabled=true;"  />No 
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Date (<b><font color="#006699">mm/dd/yyyy</font></b>) :</strong> 
	  		<eespc:displayControl paramName="B_prfmTestRptSubDate" formName="boiler"> 
			<html:text property="B_prfmTestRptSubDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>

	 </td>
  </tr>
</logic:equal>  
  <tr> 
    <td class="label_right" height="45" width="293">Is the Sulfur Content of the 
    Fuel Sampled, Analyzed 
      and Records Kept for Each of the Fuel Delivery <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="IstheSulfurcontentofthefuelsampledanalyzedandrecordskeptforeachofthefueldelivery();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="45" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_isSulpContentAnaly" formName="boiler"> 		
			<html:radio property="B_isSulpContentAnaly" value="Y" onclick="document.forms[0].B_sulphurContent.disabled=false;" />Yes 
			<html:radio property="B_isSulpContentAnaly" value="N" onclick="document.forms[0].B_sulphurContent.value='';document.forms[0].B_sulphurContent.disabled=true;" />No 
		</eespc:displayControl>	  											  
		&nbsp; <strong>Sulfur Content :</strong> 
	  		<eespc:displayControl paramName="B_sulphurContent" formName="boiler"> 
				<html:text property="B_sulphurContent" styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 
			</eespc:displayControl>
      % </td>
  </tr>
 
  <tr> 
    <td class="label_right" height="30" width="293">Is There a Nitrogen Content of the 
    Fuel Data Requirement 
      for this Boiler ?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="IsthereaNitrogencontentofthefueldatarequirementforthisboiler();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30" colspan="3" width="699">&nbsp;
		<eespc:displayControl paramName="B_nitrogenContent" formName="boiler"> 		
			<html:radio property="B_nitrogenContent" value="Y" onclick="document.forms[0].B_nitrogenContentQnty.disabled=false;" />Yes 
			<html:radio property="B_nitrogenContent" value="N" onclick="document.forms[0].B_nitrogenContentQnty.value='';document.forms[0].B_nitrogenContentQnty.disabled=true;" />No 
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Nitrogen Content :</strong> 
		<eespc:displayControl paramName="B_nitrogenContentQnty" formName="boiler"> 
			<html:text property="B_nitrogenContentQnty" styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 
		</eespc:displayControl>
      % </td>
  </tr>
  
    <tr> 
    <td class="label_right" height="15" width="293"><eespc:requiredField />Status of Source<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="StatusofSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="699">&nbsp;
		 <eespc:displayControl paramName="ModifiedInPast" formName="boiler"> 	
			<html:select property="ModifiedInPast" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>		  											  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="boiler"> 
			<html:text property="DisconnectedYear" styleClass="normal"  maxlength="4" 
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/> 
		</eespc:displayControl>
	   </td>
  </tr>
 <tr> 
    <td class="label_right" height="15" width="293" valign="top"><b>Comments</b> <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Comments();"  onmouseout="UnTip()" />:
        </td>
    <td class="label_right" height="15" width="672" style="text-align: left">
    <eespc:displayControl paramName="B_comments" formName="boiler">
    <html:textarea property="B_comments" cols="30" rows="2"/>
	</eespc:displayControl>
        </td>
        </tr>  
        

   
</table>

<logic:equal name="showAddBtn" value="Y">	
	 <logic:equal name="boiler" property="depPermitExpire" value="Y"> 		
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dep%> Permit Information <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="DEPPermitInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle" height="91">
              <tr class="oddRowStyle" colspan=2> 
                <td   class=columnhead>Issued Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Expiration Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>               	
                <td   class=columnhead>Renewal Submittal Date<br>(<b><font color="#006699">mm/dd/yyyy</font></b>)</td>	
                <td   class=columnhead>Note</td>		
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
<%
			List inspectionList = (List)request.getAttribute("BLR_DEP_PERMIT");
			boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DEP_PERMIT"));
			if (inspectionList != null){
				int size = inspectionList.size();
				for (int i=0; i < size; i++)
				{
					BoilerPermitInfoVo inspVo = (BoilerPermitInfoVo)inspectionList.get(i);
					boolean isDepPermitEditable = false;
					int permitId = inspVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
					{
						isDepPermitEditable = true;
					}
					
					  out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(inspVo.getIssueDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depIssueDate", inspVo.getIssueDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(inspVo.getExpirationDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpDate", inspVo.getExpirationDateStr(),null));
						out.println("</td>");												
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(	inspVo.getInspecSubmittedDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depInspSubDate", inspVo.getInspecSubmittedDateStr(),null));
						out.println("</td>");						
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(	inspVo.getDepExpirationNote());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpirationNote", inspVo.getDepExpirationNote(),null));
						out.println("</td>");
										
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isDepPermitEditable){
							out.println("<a href=\"javascript:editDepPermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");								
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isDepPermitEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteDepPermit('");
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
			if (!toEditDepPermit){ //show the following only if it is not edit									
%>				  
              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft" height="35"><input name="depIssueDate" type="text" class="normal" id="depIssueDate" size="10" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft" height="35"><input name="depExpDate" type="text" class="normal" id="depExpDate" size="10" maxlength="10"></td>                
                <td width="26%" align="center"  nowrap class="fieldleft" height="35"><input name="depInspSubDate" type="text" class="normal" id="depInspSubDate" size="10" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft" height="35"><input name="depExpirationNote" type="text" class="normal" id="depExpirationNote"  size="20"></td>				
                <td class="fieldleft" colspan=2 height="35">&nbsp;</td>
              </tr>
<%
			}// if !toEditDepPermit loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=7>
                <input type="button" name="Button22" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDepPermit)? "addDep(true)" : "addDep(false)") %>;">
                </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
</logic:equal>
</logic:equal>
	
	
	
		
	<logic:equal name="showAddBtn" value="Y">			
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dob%> Permit Information 
      </FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
              <td   class=columnhead>Boiler Inspection Type</td>      
                <td   class=columnhead>Last Inspection Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>                
                <td   class=columnhead>Next Inspection Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>              
                <td   class=columnhead>Submittal Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Comments</td>
                <td   class=columnhead>Date of Approval by NYCDOB<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
<%
			List dobInspectionList = (List)request.getAttribute("BLR_DOB_PERMIT");
			boolean toEditDobPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DOB_PERMIT"));
			if (dobInspectionList != null){       //IF THE LIST HAS VALUES
				int size = dobInspectionList.size();
				for (int i=0; i < size; i++)
				{
					BoilerPermitInfoVo dobInspVo = (BoilerPermitInfoVo)dobInspectionList.get(i);
					boolean isDobPermitEditable = false;
					int permitId = dobInspVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + permitIdStr + ", " + toEditDobPermit);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(permitIdStr) && toEditDobPermit)
					{
						isDobPermitEditable = true;
					}
					
				     out.println("<tr class=\"evenRowStyle\">");					
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	(dobInspVo.isInspectionType()? "Internal" : "External") );
					out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTIONS, isDobPermitEditable, "dobInspectionType", (dobInspVo.isInspectionType()? "Internal" : "External"),TagUtil.HTML_OPTION_Internal_External));
					out.println("</td>");

			    
					out.println("<td  nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getLastinspectionDateStr());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobLastinspectionDate", dobInspVo.getLastinspectionDateStr(),null));
					out.println("</td>");	
								
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getExpirationDateStr());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobExpDate", dobInspVo.getExpirationDateStr(),null));
					out.println("</td>");	
													
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getInspecSubmittedDateStr());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobInspSubDate", dobInspVo.getInspecSubmittedDateStr(),null));
					out.println("</td>");
					
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getDobExpirationNote());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobExpirationNote", dobInspVo.getDobExpirationNote(),null));
					out.println("</td>");	

					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getIssueDateStr());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobIssueDate", dobInspVo.getIssueDateStr(),null));
					out.println("</td>");
					
					
					
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isDobPermitEditable){
							out.println("<a href=\"javascript:editDobPermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");	
													
					out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isDobPermitEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteDobPermit('");
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
				}                       //for loop ends
			}                          // if condition ends
			if (!toEditDobPermit){ //show the following only if it is not edit	
					
%>				  
			  
              <tr class="evenRowStyle">           

				<td width="26%" align="center"  nowrap class="fieldleft">
				<select name="dobInspectionType" id="dobInspectionType">
                    <option selected value="Internal">Internal</option>
                    <option selected value="External">External</option>  
                  </select></td>                                          
				<td  width="10%" align="center"  nowrap class="fieldleft"><input name="dobLastinspectionDate" type="text" class="normal" id="dobLastinspectionDate" size="10" maxlength="10"></td>               
                <td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobExpDate" type="text" class="normal" id="dobExpDate" size="10" maxlength="10"></td>               
                <td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobInspSubDate" type="text" class="normal" id="dobInspSubDate" size="10" maxlength="10"></td>
                <td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobExpirationNote" type="text" class="normal" id="dobExpirationNote" size="20"></td>
                <td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobIssueDate" type="text" class="normal" id="dobIssueDate" size="10" maxlength="10"></td>
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditDobPermit loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=9>
                <input type="button" name="Button22" value="<%= ((toEditDobPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDobPermit)? "addDob(true)" : "addDob(false)") %>;">
                </td>
              </tr>
              
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>

</logic:equal>



	<logic:equal name="showAddBtn" value="Y">
	<logic:equal name="boiler" property="boilerTuneup" value="Y"> 		
			
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Annual Tune-up Information <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="AnnualTuneupInformation();"  onmouseout="UnTip()" />
      </FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Year</td>
                <td   class=columnhead>Date Performed<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Performed By</td>
                <td   class=columnhead>Records Kept ?</td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
<%
			List tuneUpList = (List)request.getAttribute("BLR_ANNUAL_TUNE_UP");
			boolean toEditAnnualTuneUp = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_ANNUAL_TUNE_UP"));
			if (tuneUpList != null){
				int size = tuneUpList.size();
				for (int i=0; i < size; i++)
				{
					BoilerAnnualTuneUpVo tuneUpVo = (BoilerAnnualTuneUpVo)tuneUpList.get(i);
					boolean isAnnualTuenUpEditable = false;
					int permitId = tuneUpVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + permitIdStr + ", " + toEditAnnualTuneUp);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(permitIdStr) && toEditAnnualTuneUp)
					{
						isAnnualTuenUpEditable = true;
					}
					
				    out.println("<tr class=\"evenRowStyle\">");
					out.println("<td  nowrap class=\"fieldleft\">");
					//out.println(	tuneUpVo.getYear());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isAnnualTuenUpEditable, "tuneUpYear", tuneUpVo.getYear(),"YEARS"));
					out.println("</td>");
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	tuneUpVo.getDateForDisplay());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isAnnualTuenUpEditable, "tuneUpDate", tuneUpVo.getDateForDisplay(),null));
					out.println("</td>");
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	tuneUpVo.getPerformedBy());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isAnnualTuenUpEditable, "tuneUpPerfBy", tuneUpVo.getPerformedBy(),null));
					out.println("</td>");				
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	(tuneUpVo.isRecordsKept()? "Yes" : "No") );
					out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isAnnualTuenUpEditable, "tuneUpRecKept", (tuneUpVo.isRecordsKept()? "Y" : "N"),TagUtil.HTML_OPTION_YES_NO));
					out.println("</td>");	
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isAnnualTuenUpEditable){
							out.println("<a href=\"javascript:editAnnualTuneUp('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");	
						
                        out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isAnnualTuenUpEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteAnnualTuneUp('");
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
			if (!toEditAnnualTuneUp){ //show the following only if it is not edit						
%>				  
              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
				<html:select property="tuneUpYear" styleClass="normal" >
					<html:optionsCollection name="YEARS" property="dropDownValues" value="value" label="name" /> 
				</html:select>								
				 </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="tuneUpDate" type="text" class="normal" id="tuneUpDate" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft">
                <input name="tuneUpPerfBy" type="text" class="normal" id="tuneUpPerfBy" size="20"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><select name="tuneUpRecKept" id="tuneUpRecKept">
                    <option selected value="Y">Yes</option>
                    <option selected value="N">No</option>
                  </select></td>
                  <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditAnnualTuneUp loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=6>
                <input type="button" name="Button22" value="<%= ((toEditAnnualTuneUp)? "Update" : "Add") %>" onClick="<%= ((toEditAnnualTuneUp)? "addTuneUp(true)" : "addTuneUp(false)") %>;">
                </td>
              </tr>
              
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>

</logic:equal>
	<br>
	<logic:equal name="showAddBtn" value="Y">
				
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Pressure Valve Test Information<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Pressurevalve();"  onmouseout="UnTip()" />
      </FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Year</td>
                <td   class=columnhead>Date Performed<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Performed By</td>                
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
<%
			List valveTestList = (List)request.getAttribute("BLR_VALVE_TEST");
			boolean toEditValveTest = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_VALVE_TEST"));
			if (valveTestList != null){
				int size = valveTestList.size();
				for (int i=0; i < size; i++)
				{
					BoilerValveTestVo valveTestVo = (BoilerValveTestVo)valveTestList.get(i);
					boolean isValveTestEditable = false;
					int permitId = valveTestVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + permitIdStr + ", " + toEditValveTest);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(permitIdStr) && toEditValveTest)
					{
						isValveTestEditable = true;
					}
					
				    out.println("<tr class=\"evenRowStyle\">");
					out.println("<td  nowrap class=\"fieldleft\">");
					//out.println(	tuneUpVo.getYear());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isValveTestEditable, "valveTestYear", valveTestVo.getYear(),"YEARS"));
					out.println("</td>");
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	tuneUpVo.getDateForDisplay());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isValveTestEditable, "valveTestDate", valveTestVo.getDateForDisplay(),null));
					out.println("</td>");
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	tuneUpVo.getPerformedBy());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isValveTestEditable, "valveTestPerfBy", valveTestVo.getPerformedBy(),null));
					out.println("</td>");				
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isValveTestEditable){
							out.println("<a href=\"javascript:editValveTest('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");	
						
                        out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isValveTestEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteValveTest('");
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
			if (!toEditValveTest){ //show the following only if it is not edit						
%>				  
              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
				<html:select property="valveTestYear" styleClass="normal" >
					<html:optionsCollection name="YEARS" property="dropDownValues" value="value" label="name" /> 
				</html:select>								
				 </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="valveTestDate" type="text" class="normal" id="valveTestDate" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="valveTestPerfBy" type="text" class="normal" id="valveTestPerfBy" size="20">
                </td>
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditValveTestloop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=6>
                <input type="button" name="Button22" value="<%= ((toEditValveTest)? "Update" : "Add") %>" onClick="<%= ((toEditValveTest)? "addValveTest(true)" : "addValveTest(false)") %>;">
                </td>
              </tr>
              
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>

</logic:equal>
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
<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("Boiler.do?methodToCall=back&pathname="+file_path);        %>">
    <b><font size="2" color="#FFFFFF">BACK</font></b></a></font></td>
  </tr>
</table>

<br>
</p>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#006699" width="100%" id="AutoNumber1">
  <tr>
    <td width="20%" bgcolor="#FFFFCC" align="left"><b>
    <font size="2" face="Verdana">File/Folder Name</font></b></td>
    <td width="20%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Type</font></b></td>
    <td width="20%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">File Size</font></b></td>
    <td width="20%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Date Modified</font></b></td>
    <td width="12%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Rename</font></b></td>
    <td width="28%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Delete</font></b></td>
  </tr>
  <% 
  List folderList = (List)request.getAttribute("FILE_LIST");

		if (folderList != null)
		{
				int size = folderList .size();
				for (int i=0; i < size; i++)
				{
				String arr[]=(String[])folderList .get(i);
  %>
  <tr>
    <td width="20%" align="left"><b><font color="#FF3399" size="2">
    <a href="<%if(arr[0].equals("folder"))    {    out.println("Boiler.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
    <td width="20%" align="center"><span style="font-variant: small-caps"><%=arr[0]%>&nbsp;</span></td>
    <td width="20%" align="center"><%
    if(arr[4]!=null)
    {
    out.println(arr[4]);
    }
    
    
    %>&nbsp;</td>
    <td width="20%" align="center"><%=arr[3]%>&nbsp;</td>    
    <td width="12%" align="center"><a href="javascript:editfile('<%=arr[2].replaceAll("'","x***x")%>','<%=(String)request.getAttribute("FILE_PATH")%>');">
    <img border="0" src="images/edit.jpg" width="42" height="13" ></a></td>
    <td width="28%" align="center"><a href="javascript:deletefile('<%=arr[2].replaceAll("'","x***x")%>','<%=(String)request.getAttribute("FILE_PATH")%>');">
    <img border="0" src="images/delete.jpg" width="56" height="16"></a></td>
  </tr>
<%
}
}
%>
</table>

<p align="center">

<br> 
<html:file property="filename"/>

<input type="button" value="Upload" name="B1" onClick="fileupload();" >

<hr>

	
</logic:equal>
<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Building" onClick="returnToBuilding();">
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
			<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addBoiler(true)" : "addBoiler(false)") %>;">						
      		</td>
            <td >&nbsp;
              <input type="reset" name="Submit22" value="Reset">
			</td>
			</logic:notEqual>			
          </tr>
    </table> 
		
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>
</html:form>
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and Verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>		
</body>
</html>