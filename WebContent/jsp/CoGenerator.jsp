<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eespc.tracking.bo.PermitInfoVo" %> 
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Co-Generator</title>
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
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/CogenTurbine.js" ></script>
<script src="/eespc/js/FuelConsumptionCalculation.js" ></script>
<script src="/eespc/js/o_FuelConsumptionCalculation.js" ></script>
<script src="/eespc/help/cogenhelp.js" ></script>
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
	var url = 'CoGenTurbine.do?methodToCall=fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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
	var url = 'CoGenTurbine.do?methodToCall=o_fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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
       // alert(req.responseText+"");
        document.forms[0].o_hdnPreviousConsumption.value=req.responseText;
        o_validateFuelConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }


function disConnect()
{

if(document.forms[0].co_status.value==-1 || document.forms[0].co_status.value==2 || document.forms[0].co_status.value==3 || document.forms[0].co_status.value==4)
{
document.forms[0].co_disconnecteddate.disabled=false;
}
else
{
document.forms[0].co_disconnecteddate.disabled=true;
}
}
function searchExist(){
	var url = "/eespc/CogenTListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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

<body onload="init();">
<script src="tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="tooltip/tip_centerwindow.js" type="text/javascript"></script>
<html:form action="/CoGenTurbine"  enctype="multipart/form-data"> 
<html:hidden property="methodToCall" />
<html:hidden property="type" />
<html:hidden property="id" />
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<html:hidden property="fueldeleteConsumptionId" />
<input name="bctype" type="hidden">
<SPAN CLASS=page_title>Cogen Turbine-Engine<!--bean:write name='RESOURCE_NAME' scope='request' filter='false' /--></SPAN> <br>		
	<br>			
<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#EEEEEE" style="border-collapse: collapse" height="1336">
  <tr> 
    <td class="label_right" height="26"><eespc:requiredField />ID # 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td width="56%"  nowrap class="fieldleft" height="26">&nbsp; 
		<eespc:displayControl paramName="facilityDesignatedId" formName="cogenTurbineInfo"> 
			<html:text property="facilityDesignatedId" styleClass="normal" />
			       			<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();"> 
		</eespc:displayControl>	
      (Facility Designated)</td>
  </tr>
  <tr> 
    <td width="44%" nowrap class="label_right" height="15">Floor 			 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="floor" formName="cogenTurbineInfo"> 
			<html:text property="floor" styleClass="normal" /> 
		</eespc:displayControl>	
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Primary Use 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PrimaryUse();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="primaryUse" formName="cogenTurbineInfo"> 
			<html:select property="primaryUse" styleClass="normal" >
				<html:optionsCollection name="COGENTURBINE_PRIMARY_USE" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  	
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Does the Engine have a Control System 					
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Doestheenginehaveacontrolsystem();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="controlSystem" formName="cogenTurbineInfo"> 		
			<html:radio property="controlSystem" value="Y" onclick="document.forms[0].controlEfficiency.disabled=false;" />Yes 
			<html:radio property="controlSystem" value="N" onclick="document.forms[0].controlEfficiency.selectedIndex=0;document.forms[0].controlEfficiency.disabled=true;" />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Type and Control Efficiency  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Typeandcontrolefficiency();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="controlEfficiency" formName="cogenTurbineInfo"> 
			<html:select property="controlEfficiency" styleClass="normal"  >
				<html:optionsCollection name="CONTROL_EFFICIENCY" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  	
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Year Installed 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="yearInstalled" formName="cogenTurbineInfo"> 
			<html:text property="yearInstalled" styleClass="normal" size="4" maxlength="4" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" height="15"><eespc:requiredField />Status Of Source  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusOfSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_status" formName="cogenTurbineInfo"> 	
			<html:select property="co_status" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="co_disconnecteddate" formName="cogenTurbineInfo"> 
			<html:text property="co_disconnecteddate" styleClass="normal" size="4" maxlength="4" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	  </td>
  </tr>

  <tr> 
    <td class="label_right" nowrap bgcolor="#FFFFCC" colspan="2" height="15">
   <p style="text-align: left">Co Generator:		
    </td>
  </tr>

  <tr> 
    <td class="label_right" nowrap height="15">Manufacturer 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Manufacturer();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="manufacturer" formName="cogenTurbineInfo"> 
			<html:text property="manufacturer" styleClass="normal" /> 
		</eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Make  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Make();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="make" formName="cogenTurbineInfo"> 
			<html:text property="make" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Model  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Model();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="model" formName="cogenTurbineInfo"> 
			<html:text property="model" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Serial #  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serial();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="serial" formName="cogenTurbineInfo"> 
			<html:text property="serial" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Burner Make 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerMake();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="burnerMake" formName="cogenTurbineInfo"> 
			<html:text property="burnerMake" styleClass="normal" /> 
		</eespc:displayControl>
	
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Burner Model  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerModel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="burnerModel" formName="cogenTurbineInfo"> 
			<html:text property="burnerModel" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="45" valign="top">
    <eespc:requiredField />Capacity  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacity();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="45">&nbsp; 
    <eespc:displayControl paramName="capacity" formName="cogenTurbineInfo"> 
      <html:text property="capacity" styleClass="normal"  onblur="calcOtherUnit();" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
    </eespc:displayControl> 
      (KWH) 
	  <br>&nbsp; <html:text property="hpText"  readonly="true" styleClass="normal" /> (BHP) 
	  <br>&nbsp; <html:text property="mmbtuText" readonly="true" styleClass="normal" /> (MM BTU/Hr) 	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Burner Type 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerType();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="burnerType" formName="cogenTurbineInfo"> 		
			<html:radio property="burnerType" value="Y"  onclick="burnerTypeSetting();" />Dual Fuel 
			<html:radio property="burnerType" value="N"  onclick="burnerTypeSetting();"/>Single Fuel 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Primary Fuel  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PrimaryFuel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="primaryFuel" formName="cogenTurbineInfo"> 
			<html:select property="primaryFuel" styleClass="normal" onchange="fuelChanged();">
				<html:optionsCollection name="COGEN_TURBINE_PRIMARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Secondary Fuel  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SecondaryFuel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="secondaryFuel" formName="cogenTurbineInfo"> 
			<html:select property="secondaryFuel" styleClass="normal" onchange="fuelChanged();" >
				<html:optionsCollection name="COGEN_TURBINE_SECONDARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Oil 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateOil();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="oilFiringRate" formName="cogenTurbineInfo"> 
			<html:text property="oilFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (GPH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Natural Gas 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateNaturalGas();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="naturalGasFiringRate" formName="cogenTurbineInfo"> 
			<html:text property="naturalGasFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (CFH) </td>
  </tr>
    <tr> 
    <td class="label_right" nowrap bgcolor="#F2F2F2" height="15">
    <eespc:requiredField />Is There a Turbine with This Unit  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereaTurbinewiththisunit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap bgcolor="#F2F2F2" height="15">&nbsp;
		<eespc:displayControl paramName="isturbine" formName="cogenTurbineInfo"> 		
			<html:radio property="isturbine" value="Y" onclick="displayControl()" />Yes 
			<html:radio property="isturbine" value="N" onclick="displayControl()" />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <logic:equal name="cogenTurbineInfo" property="isturbine" value="Y">  
   <tr> 
    <td class="label_right" nowrap bgcolor="#FFFFCC" colspan="2" height="15">
   <p style="text-align: left">Turbine:		
    </td>
  </tr>
  <tr>
   <td class="label_right" nowrap height="15">Manufacturer  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Manufacturert();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="tmanufacturer" formName="cogenTurbineInfo"> 
			<html:text property="tmanufacturer" styleClass="normal" /> 
		</eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Make 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Maket();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="tmake" formName="cogenTurbineInfo"> 
			<html:text property="tmake" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Model 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Modelt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="tmodel" formName="cogenTurbineInfo"> 
			<html:text property="tmodel" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Serial # 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serialt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="tserial" formName="cogenTurbineInfo"> 
			<html:text property="tserial" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Burner Make 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerMaket();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="tburnerMake" formName="cogenTurbineInfo"> 
			<html:text property="tburnerMake" styleClass="normal" /> 
		</eespc:displayControl>
	
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Burner Model  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerModelt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="tburnerModel" formName="cogenTurbineInfo"> 
			<html:text property="tburnerModel" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="45" valign="top">
    <eespc:requiredField />Capacity  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacityt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="45">&nbsp; 
    <eespc:displayControl paramName="tcapacity" formName="cogenTurbineInfo"> 
      <html:text property="tcapacity" styleClass="normal"  onblur="tcalcOtherUnit()" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 
    </eespc:displayControl> 
      (KWH) 
	  <br>&nbsp; <html:text property="thpText"  readonly="true" styleClass="normal" /> (BHP) 
	  <br>&nbsp; <html:text property="tmmbtuText" readonly="true" styleClass="normal" /> (MM BTU/Hr) 	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Burner Type  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerTypet();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="tburnerType" formName="cogenTurbineInfo"> 		
			<html:radio property="tburnerType" value="Y"  onclick="tburnerTypeSetting();" />Dual Fuel 
			<html:radio property="tburnerType" value="N"  onclick="tburnerTypeSetting();"/>Single Fuel 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Primary Fuel  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PrimaryFuelt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="tprimaryFuel" formName="cogenTurbineInfo"> 
			<html:select property="tprimaryFuel" styleClass="normal" onchange="tfuelChanged();">
				<html:optionsCollection name="COGEN_TURBINE_PRIMARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Secondary Fuel 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SecondaryFuelt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="tsecondaryFuel" formName="cogenTurbineInfo"> 
			<html:select property="tsecondaryFuel" styleClass="normal" onchange="tfuelChanged();" >
				<html:optionsCollection name="COGEN_TURBINE_SECONDARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Oil 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateOilt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="toilFiringRate" formName="cogenTurbineInfo"> 
			<html:text property="toilFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (GPH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Natural Gas  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateNaturalGast();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="tnaturalGasFiringRate" formName="cogenTurbineInfo"> 
			<html:text property="tnaturalGasFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (CFH) </td>
  </tr>
  </logic:equal>
 <tr> 
    <td class="label_right" nowrap bgcolor="#EEEEEE" height="15">
    <eespc:requiredField />Is There a Waste HRB 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereaWasteHRBt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap bgcolor="#EEEEEE" height="15">&nbsp;
		<eespc:displayControl paramName="iswhrb" formName="cogenTurbineInfo"> 		
			<html:radio property="iswhrb" value="Y" onclick="displayControl()" />Yes 
			<html:radio property="iswhrb" value="N" onclick="displayControl()" />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
<logic:equal name="cogenTurbineInfo" property="iswhrb" value="Y">   
   <tr> 
    <td class="label_right" nowrap bgcolor="#FFFFCC" colspan="2" height="15">
   <p style="text-align: left">Waste HRB:		
    </td>
  </tr>
  <tr>
   <td class="label_right" nowrap height="15">Manufacturer
   <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Manufacturerw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="wmanufacturer" formName="cogenTurbineInfo"> 
			<html:text property="wmanufacturer" styleClass="normal" /> 
		</eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Make  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Makew();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="wmake" formName="cogenTurbineInfo"> 
			<html:text property="wmake" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Model  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Modelw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="wmodel" formName="cogenTurbineInfo"> 
			<html:text property="wmodel" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Serial #  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serialw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="wserial" formName="cogenTurbineInfo"> 
			<html:text property="wserial" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Burner Make 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerMakew();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="wburnerMake" formName="cogenTurbineInfo"> 
			<html:text property="wburnerMake" styleClass="normal" /> 
		</eespc:displayControl>
	
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Burner Model  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerModelw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="wburnerModel" formName="cogenTurbineInfo"> 
			<html:text property="wburnerModel" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="45" valign="top">
    <eespc:requiredField />Capacity 	 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacityw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="45">&nbsp; 
    <eespc:displayControl paramName="wcapacity" formName="cogenTurbineInfo"> 
      <html:text property="wcapacity" styleClass="normal"  onblur="wcalcOtherUnit()" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
    </eespc:displayControl> 
      (KWH) 
	  <br>&nbsp; <html:text property="whpText"  readonly="true" styleClass="normal" /> (BHP) 
	  <br>&nbsp; <html:text property="wmmbtuText" readonly="true" styleClass="normal" /> (MM BTU/Hr) 	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Burner Type  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerTypew();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="wburnerType" formName="cogenTurbineInfo"> 		
			<html:radio property="wburnerType" value="Y"  onclick="wburnerTypeSetting();" />Dual Fuel 
			<html:radio property="wburnerType" value="N"  onclick="wburnerTypeSetting();"/>Single Fuel 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Primary Fuel 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PrimaryFuelw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="wprimaryFuel" formName="cogenTurbineInfo"> 
			<html:select property="wprimaryFuel" styleClass="normal" onchange="wfuelChanged();">
				<html:optionsCollection name="COGEN_TURBINE_PRIMARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Secondary Fuel  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SecondaryFuelw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="wsecondaryFuel" formName="cogenTurbineInfo"> 
			<html:select property="wsecondaryFuel" styleClass="normal" onchange="wfuelChanged();" >
				<html:optionsCollection name="COGEN_TURBINE_SECONDARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Oil 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateOilw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="woilFiringRate" formName="cogenTurbineInfo"> 
			<html:text property="woilFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (GPH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Natural Gas 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateNaturalGasw();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="wnaturalGasFiringRate" formName="cogenTurbineInfo"> 
			<html:text property="wnaturalGasFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (CFH) </td>
  </tr>
  </logic:equal>
   <tr> 
    <td class="label_right" nowrap bgcolor="#FFFFCC" colspan="2" height="15">
   <p style="text-align: left">Common:		
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="26">Fuel from tank  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Fuelfromtank();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="26">&nbsp;
		<eespc:displayControl paramName="fuelFrom" formName="cogenTurbineInfo"> 
			<html:hidden property="fuelFrom" /> 
			<html:text property="fuelFromName" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchFuel();">
		</eespc:displayControl>
      </td>
  </tr>
  
  
  <tr> 
    <td class="label_right" nowrap height="15"><%=dob%> Filing
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="dobfiling" formName="cogenTurbineInfo"> 
        	<html:radio property="dobfiling"  value="Y" styleClass="normal" onclick="displayControl();"/>Yes 
        	<html:radio property="dobfiling"  value="N" styleClass="normal" onclick="displayControl();"/>No
        	<html:radio property="dobfiling"  value="N/A" styleClass="normal" onclick="displayControl();"/>N/A
        </eespc:displayControl>
	</td>
 </tr>

<logic:equal name="cogenTurbineInfo" property="dobfiling" value="Y">	  
  <tr> 
    <td class="label_right" nowrap height="15"><%=dob%> Filing/Job #  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="nycDob" formName="cogenTurbineInfo"> 
			<html:text property="nycDob" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
 
  <tr> 
    <td class="label_right" nowrap height="15"><%=dob%> Sign Off
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="dobsignoff" formName="cogenTurbineInfo"> 
        	<html:radio property="dobsignoff"  value="Y" styleClass="normal"/>Yes 
        	<html:radio property="dobsignoff"  value="N" styleClass="normal"/>No
        </eespc:displayControl>
	</td>
  </tr>
    
  </logic:equal>
  
  <tr> 
    <td class="label_right" nowrap height="1">MEA #
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="MEA();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="1">&nbsp;
		<eespc:displayControl paramName="mea" formName="cogenTurbineInfo"> 
			<html:text property="mea" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>

  <tr> 
    <td class="label_right" nowrap height="15"><%=dep%> # 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEP();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="dep" formName="cogenTurbineInfo"> 
			<html:text property="dep" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
 
  <tr> 
    <td class="label_right" nowrap height="15">Schedule C 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ScheduleC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="sechduelC" formName="cogenTurbineInfo"> 		
			<html:radio property="sechduelC" value="Y"  />Yes 
			<html:radio property="sechduelC" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Plan Approval  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PlanApproval();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="planApproval" formName="cogenTurbineInfo"> 		
			<html:radio property="planApproval" value="Y"  />Yes 
			<html:radio property="planApproval" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="30">Are Weekly and Monthly Test Records in Permanent Bound Book ?
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Areweeklyandmonthlytestrecordsinpermanentboundbook();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="recordsInBook" formName="cogenTurbineInfo"> 		
			<html:radio property="recordsInBook" value="Y"  />Yes 
			<html:radio property="recordsInBook" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15">DEC Permits Obtained ? 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DECpermitsobtained();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_decPermitObtained" formName="cogenTurbineInfo"> 		
			<html:radio property="co_decPermitObtained" value="Y"  />Yes 
			<html:radio property="co_decPermitObtained" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>

  <tr> 
    <td class="label_right" height="15"><%=dep%> Permits Obtained ? 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPpermitsobtained();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_depPermitObtained" formName="cogenTurbineInfo"> 		
			<html:radio property="co_depPermitObtained" value="Y"  />Yes 
			<html:radio property="co_depPermitObtained" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>


  <tr> 
    <td class="label_right" nowrap height="15">Was a Stack Test Protocol Submitted to DEC 
      ?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WasastacktestprotocolsubmittedtoDEC();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="protocolSubmitted" formName="cogenTurbineInfo"> 		
			<html:radio property="protocolSubmitted" value="Y"  />Yes 
			<html:radio property="protocolSubmitted" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Test Conducted by What Firm
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Testconductedbywhatfirm();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="testConductedBy" formName="cogenTurbineInfo"> 
			<html:text property="testConductedBy" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Was a Test Report Submitted to DEC ?
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WasatestreportsubmittedtoDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="testReportSubmited" formName="cogenTurbineInfo"> 		
			<html:radio property="testReportSubmited" value="Y"/>Yes 
			<html:radio property="testReportSubmited" value="N"/>No 
		</eespc:displayControl>														  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Stack Test Date Required by the Permit (<font color="#006699">mm/dd/yyyy</font>)
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StacktestdaterequiredbythePermit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="testReportSubmitedDate" formName="cogenTurbineInfo"> 
			<html:text property="testReportSubmitedDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Did the Unit Comply with the NOX RACT plan?
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DidtheunitcompywiththeNOXRACTplan();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="isNoxRactPlan" formName="cogenTurbineInfo"> 		
			<html:radio property="isNoxRactPlan" value="Y"  />Yes 
			<html:radio property="isNoxRactPlan" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Was a Retest Planned ?
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Wasaretestplanned();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="retestPlanned" formName="cogenTurbineInfo"> 		
			<html:radio property="retestPlanned" value="Y"  />Yes 
			<html:radio property="retestPlanned" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
    <tr> 
    <td class="label_right" height="15">Unit Comply with SO2 RACT Plan 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UnitcomplywithSO2RACTplan();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_compyWithSO2RactPlan" formName="cogenTurbineInfo"> 		
			<html:radio property="co_compyWithSO2RactPlan" value="Y"  />Yes 
			<html:radio property="co_compyWithSO2RactPlan" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
 

  <tr> 
    <td class="label_right" height="15">Unit Comply with PM RACT Plan  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UnitcomplywithPMRACTplan();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_compyWithPMRactPlan" formName="cogenTurbineInfo"> 		
			<html:radio property="co_compyWithPMRactPlan" value="Y"  />Yes 
			<html:radio property="co_compyWithPMRactPlan" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
    <tr> 
    <td class="label_right" height="15">Test Passed/Compliance 	 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestPassedCompliance();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_testPassed" formName="cogenTurbineInfo"> 		
			<html:radio property="co_testPassed" value="Y"  />Yes 
			<html:radio property="co_testPassed" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15">Stack Test Date(<font color="#006699">mm/dd/yyyy</font>)  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackTestDate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_StackTestDate" formName="cogenTurbineInfo"> 
			<html:text property="co_StackTestDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr>
    <td class="label_right" nowrap height="15">When is the Next Stack Test (<font color="#006699">mm/dd/yyyy</font>) 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Whenisthenextstacktest();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="nextStackTest" formName="cogenTurbineInfo"> 
			<html:text property="nextStackTest" size="10" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
   <tr>
    <td class="label_right" nowrap height="15">Note
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="note();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="nextStackTestNote" formName="cogenTurbineInfo"> 
			<html:text property="nextStackTestNote" size="10" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="26">Stack Exhausting 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" />: </td>
    <td  nowrap class="fieldleft" height="26">&nbsp;
		<eespc:requiredField />
    <eespc:displayControl paramName="stackFromName" formName="cogenTurbineInfo"> 
			<html:hidden property="stackFrom" /> 		
			<html:text property="stackFromName" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchStack();">
		</eespc:displayControl>
	  </td>
  </tr>
  <tr>
      <td class="label_right" height="15">Fuel Capping 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="co_fuelgapping" formName="cogenTurbineInfo"> 		
			<html:radio property="co_fuelgapping" value="Y" onclick="enablefuelcapping()"  />Yes 
			<html:radio property="co_fuelgapping" onclick="document.forms[0].G_gasfuelgapping.value='';document.forms[0].G_gasfuelgapping.disabled=true;document.forms[0].G_gasemmisionfactor.value='';document.forms[0].G_gasemmisionfactor.disabled=true;document.forms[0].G_oilfuelgapping.value='';document.forms[0].G_oilfuelgapping.disabled=true;document.forms[0].G_oilemmisionfactor='';document.forms[0].G_oilemmisionfactor.disabled=true;" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="18">Gas Fuel Capping 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="GasFuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="18">
	
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" height="19">
      <tr>
        <td width="33%" height="19">&nbsp;<eespc:displayControl paramName="co_gasfuelgapping" formName="cogenTurbineInfo"> 
			<html:text property="co_gasfuelgapping" size="4" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;CFY</td>
        <td width="33%" align="right" height="19">Gas NOx Emission Factor
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="GasNOxEmmisionfactor();"  onmouseout="UnTip()" />:</td>
        <td width="34%" height="19">&nbsp;<eespc:displayControl paramName="co_gasemmisionfactor" formName="cogenTurbineInfo"> 
			<html:text property="co_gasemmisionfactor" size="4" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/million cu.ft.</td>
      </tr>
    </table>
	
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="30">Oil Fuel Capping  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OilFuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">
		
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="33%">&nbsp;<eespc:displayControl paramName="co_oilfuelgapping" formName="cogenTurbineInfo"> 
			<html:text property="co_oilfuelgapping" size="4" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;GPY</td>
        <td width="33%" align="right">&nbsp;Oil NOx Emission Factor
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="OilNOxEmmisionfactor();"  onmouseout="UnTip()" />:</td>
        <td width="34%">&nbsp;<eespc:displayControl paramName="co_oilemmisionfactor" formName="cogenTurbineInfo"> 
			<html:text property="co_oilemmisionfactor" size="4" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/1000 gal</td>
      </tr>
    </table>
		
	</td>
  </tr>
 <tr> 
    <td class="label_right">SO2 Emission Factor, Oil <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="SO2EmissionFactorOil();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15"  width="433">&nbsp;
		<eespc:displayControl paramName="co_oilso2" formName="cogenTurbineInfo"> 
			<html:text property="co_oilso2" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp; &nbsp; lb/1000 gal &nbsp;<b>Gas
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Gas();"  onmouseout="UnTip()" /></b>:<eespc:displayControl paramName="co_gasso2" formName="cogenTurbineInfo"> 
			<html:text property="co_gasso2" styleClass="normal"  size="4"			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp; lb/million cu.ft.
	</td>
  </tr>
   <tr> 
    <td class="label_right" >SO2 Allowable<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="SO2Allowable();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" >&nbsp;
		<eespc:displayControl paramName="co_so2allowable" formName="cogenTurbineInfo"> 
			<html:text property="co_so2allowable" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;Tons/Yr &nbsp;&nbsp;<b>NOX Allowable<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="NOXAllowable();"  onmouseout="UnTip()" /></b>:<eespc:displayControl paramName="co_noxallowable" formName="cogenTurbineInfo"> 
			<html:text property="co_noxallowable" styleClass="normal"  size="4"			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;Tons/Yr
	</td>
  </tr>
  

 <tr> 
    <td class="label_right" height="30">Is There Fuel Capping or the Hours of Operation 
    Limit 
      Under DEC for this Unit ?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IstherefuelcappingorthehoursofoperationlimitunderDECforthisunit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="isFuleCapping" formName="cogenTurbineInfo"> 		
			<html:radio property="isFuleCapping" value="Y"  />Yes 
			<html:radio property="isFuleCapping" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
   <tr> 
    <td class="label_right" height="30">Comments<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="co_Comments" formName="cogenTurbineInfo"> 
			<html:textarea property="co_Comments" cols="30" rows="2"/>			
		</eespc:displayControl>	  											  
	</td>
  </tr>
</table>		
	<logic:equal name="showAddBtn" value="Y">			
       <br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dep%> Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Year </td>			  
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>) </td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>) </td>
                <td   class=columnhead>Note</td>
                <td   class=columnhead>Edit</td>                
               <td   class=columnhead>Delete</td>                
              </tr>
<%
			List inspectionList = (List)request.getAttribute("COGENTURBINE_DEP_PERMIT");
			boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("CGNTRBN_EDIT_DEP_PERMIT"));			
			if (inspectionList != null){
				int size = inspectionList.size();
				for (int i=0; i < size; i++)
				{
					PermitInfoVo inspVo = (PermitInfoVo)inspectionList.get(i);
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
						out.println("<td  nowrap class=\"fieldleft\">");
						//out.println(inspVo.getYear());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isDepPermitEditable, "depYear", inspVo.getYear(),"YEARS"));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(inspVo.getIssueDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depIssueDate", inspVo.getIssueDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(inspVo.getExpirationDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpDate", inspVo.getExpirationDateStr(),null));
						out.println("</td>");
						out.println("<td  nowrap class=\"fieldleft\">");
						//out.println(inspVo.getNote());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "note", inspVo.getNote(),null));
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
                <td width="26%" align="center"  nowrap class="fieldleft"> 
				<html:select property="depYear" styleClass="normal" >
					<html:optionsCollection name="YEARS" property="dropDownValues" value="value" label="name" /> 
				</html:select>								
				</td>			  
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="depIssueDate" type="text" class="normal" id="depIssueDate" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="depExpDate" type="text" class="normal" id="depExpDate" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="note" type="text" class="normal" id="note" size="10" maxlength="10"> 
                </td>
                
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditDepPermit loop
%>
              
              <tr class="evenRowStyle"> 
                <td   colspan=6>
                <input type="button" name="Button22" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDepPermit)? "cogenAddDep(true)" : "cogenAddDep(false)") %>;">
                </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
</logic:equal>
<logic:equal name="showAddBtn" value="Y">
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#111111" bgcolor="#F2F2F2">
  <tr> 
    <td width="14%" height="20" class="label_right" style="text-align: left">State Permit # : </td>
    <td class="fieldleft"  nowrap width="111%">&nbsp; 
<logic:notEmpty name="displayStatePermitNumber" >	
	<bean:write name='displayStatePermitNumber' scope='request' filter='false' />
</logic:notEmpty>	
<logic:empty name="displayStatePermitNumber">
	<input type="text" name="statePermitNumber" class="normal" value="" size="20">
</logic:empty>
	</td>
	
  </tr>
</table>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>State Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Year </td>
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>) </td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>) </td>
               <td   class=columnhead>Edit</td>                
                <td   class=columnhead>Delete</td>                
              </tr>
<%
			List inspectionList = (List)request.getAttribute("COGENTURBINE_STATE_PERMIT");
			boolean toEditStatePermit = UtilityObject.convertBoolean((String)request.getAttribute("CGNTRBN_EDIT_STATE_PERMIT"));
			if (inspectionList != null){
				int size = inspectionList.size();
				for (int i=0; i < size; i++)
				{
					PermitInfoVo statePermitVo = (PermitInfoVo)inspectionList.get(i);
					boolean isStatePermitEditable = false;
					int permitId = statePermitVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + permitIdStr + ", " + toEditStatePermit);
					if (null != tempStr && 
						tempStr.equalsIgnoreCase(permitIdStr) && toEditStatePermit)
					{
						isStatePermitEditable = true;
					}
					
						out.println("<tr class=\"evenRowStyle\">");
						out.println("<td  nowrap class=\"fieldleft\">");
						//out.println(statePermitVo.getYear());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isStatePermitEditable, "stateYear", statePermitVo.getYear(),"YEARS"));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(statePermitVo.getIssueDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isStatePermitEditable, "stateIssueDate", statePermitVo.getIssueDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(statePermitVo.getExpirationDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isStatePermitEditable, "stateExpDate", statePermitVo.getExpirationDateStr(),null));
						out.println("</td>");
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isStatePermitEditable){
							out.println("<a href=\"javascript:editStatePermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");		
						
                        out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isStatePermitEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteStatePermit('");
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
			if (!toEditStatePermit){ //show the following only if it is not edit						
%>				  
              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft"> 
				<html:select property="stateYear" styleClass="normal" >
					<html:optionsCollection name="YEARS" property="dropDownValues" value="value" label="name" /> 
				</html:select>								
				</td>			  
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="stateIssueDate" type="text" class="normal" id="stateIssueDate" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="stateExpDate" type="text" class="normal" id="stateExpDate" size="10" maxlength="10"> 
                </td>
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditStatePermit loop
%>
              
              <tr class="evenRowStyle"> 
                <td    colspan=5 >
                <input type="button" name="Button22" value="<%= ((toEditStatePermit)? "Update" : "Add") %>" onClick="<%= ((toEditStatePermit)? "cogenAddStatePermit(true)" : "cogenAddStatePermit(false)") %>;">
                </td>
              </tr>
			  </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>

<hr>
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

<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("CoGenTurbine.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("CoGenTurbine.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
<html:file property="filename" />

<input type="button" value="Upload" name="B1" onClick="fileupload();" >

<hr>

</logic:equal>
<hr>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  align="right"><input type="button" name="Button22" value="<< Return To Building" onClick="returnToBuilding();">
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
	    <input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addCogenTurbine(true)" : "addCogenTurbine(false)") %>;">            
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
Input and verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>		
</body>
</html>