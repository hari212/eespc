<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.BoilerPermitInfoVo,com.eespc.tracking.bo.GeneratorCfrPermitInfoVo" %>
<%@ page import="com.eespc.tracking.bo.BoilerFuelConsumptionVo" %>
<%@ page import="com.eespc.tracking.bo.GeneratorEngineRunningHrsVo" %>  
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.bo.GeneratorVo,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<html>&nbsp;<head><title>New Generator</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" >
</script>
<script src="/eespc/js/Generator.js" >
</script>
<script src="/eespc/help/Generator.js" >
</script>
<script src="/eespc/js/FuelConsumptionCalculation.js" >
</script>
<script src="/eespc/js/o_FuelConsumptionCalculation.js" ></script>
<script src="/eespc/js/EngineRunningHrsCalculation.js" ></script>
<script src="/eespc/js/MonthlyRunningHrsCalculationOil.js" ></script>
<script src="/eespc/js/MonthlyRunningHrsCalculationGas.js" ></script>
<script src="/eespc/js/AnnualSparkRunningHrsCalculation.js" ></script>
<script src="/eespc/help/generatorhelp.js" ></script>
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
	var url = 'Generator.do?methodToCall=fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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
	var url = 'Generator.do?methodToCall=o_fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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

  
  function  oil_fetchConsumption()
  {

  var serviceNameValue=document.forms[0].oil_fcyear.value;
      if(serviceNameValue=="Please Select")	{
         	alert("Please Choose Year:");
  	 	document.forms[0].oil_fcyear.focus();
  	 	return false;
  	}
  	var url = 'Generator.do?methodToCall=o_pressureTestHrsInfoyearconsumtion&oil_fcyear='+serviceNameValue;
  	oil_retrieveURL(url);
  }

    function oil_retrieveURL(url) {

      if (window.XMLHttpRequest) { // Non-IE browsers
        req = new XMLHttpRequest();
        req.onreadystatechange = oil_processStateChange;
        try {
          req.open("GET", url, true);
        } catch (e) {
          alert(e);
        }
        req.send(null);
      } else if (window.ActiveXObject) { // IE
        req = new ActiveXObject("Microsoft.XMLHTTP");
        if (req) {
          req.onreadystatechange = oil_processStateChange;
          req.open("GET", url, true);
          req.send();
        }
      }
    }

    function oil_processStateChange() {
      if (req.readyState == 4) { // Complete
        if (req.status == 200) {
         // OK response
          //alert(req.responseText+"");
          document.forms[0].oil_hdnConsumption.value=req.responseText;
          oil_validateEngineRunningHrs();
        } else {
          alert("Problem: " + req.statusText);
        }
      }
    }


    function  gas_fetchConsumption()
    {

    var serviceNameValue=document.forms[0].gas_fcyear.value;
        if(serviceNameValue=="Please Select")	{
           	alert("Please Choose Year:");
    	 	document.forms[0].gas_fcyear.focus();
    	 	return false;
    	}
    	var url = 'Generator.do?methodToCall=g_pressureTestHrsInfoyearconsumtion&gas_fcyear='+serviceNameValue;
    	gas_retrieveURL(url);
    }

      function gas_retrieveURL(url) {

        if (window.XMLHttpRequest) { // Non-IE browsers
          req = new XMLHttpRequest();
          req.onreadystatechange = gas_processStateChange;
          try {
            req.open("GET", url, true);
          } catch (e) {
            alert(e);
          }
          req.send(null);
        } else if (window.ActiveXObject) { // IE
          req = new ActiveXObject("Microsoft.XMLHTTP");
          if (req) {
            req.onreadystatechange = gas_processStateChange;
            req.open("GET", url, true);
            req.send();
          }
        }
      }

      function gas_processStateChange() {
        if (req.readyState == 4) { // Complete
          if (req.status == 200) {
           // OK response
            //alert(req.responseText+"");
            document.forms[0].gas_hdnConsumption.value=req.responseText;
            gas_validateEngineRunningHrs();
          } else {
            alert("Problem: " + req.statusText);
          }
        }
      }


      
      function  anu_fetchConsumption()
      {

      var serviceNameValue=document.forms[0].anu_fcyear.value;
          if(serviceNameValue=="Please Select")	{
             	alert("Please Choose Year:");
      	 	document.forms[0].anu_fcyear.focus();
      	 	return false;
      	}
      	var url = 'Generator.do?methodToCall=anu_pressureTestHrsInfoyearconsumtion&anu_fcyear='+serviceNameValue;
      	anu_retrieveURL(url);
      }

        function anu_retrieveURL(url) {

          if (window.XMLHttpRequest) { // Non-IE browsers
            req = new XMLHttpRequest();
            req.onreadystatechange = anu_processStateChange;
            try {
              req.open("GET", url, true);
            } catch (e) {
              alert(e);
            }
            req.send(null);
          } else if (window.ActiveXObject) { // IE
            req = new ActiveXObject("Microsoft.XMLHTTP");
            if (req) {
              req.onreadystatechange = anu_processStateChange;
              req.open("GET", url, true);
              req.send();
            }
          }
        }

        function anu_processStateChange() {
          if (req.readyState == 4) { // Complete
            if (req.status == 200) {
             // OK response
              //alert(req.responseText+"");
              document.forms[0].anu_hdnConsumption.value=req.responseText;
              anu_validateEngineRunningHrs();
            } else {
              alert("Problem: " + req.statusText);
            }
          }
        }


function e_fetchRunningHrs()
{

var serviceNameValue=document.forms[0].e_rhyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].e_rhyear.focus();
	 	return false;
	}
	var url = 'Generator.do?methodToCall=engineRunningHrsInfoyearconsumtion&e_rhyear='+serviceNameValue;
	e_retrieveURL(url);
}

  function e_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = e_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = e_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function e_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].e_hdnConsumption.value=req.responseText;
        validateEngineRunningHrs();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }



function enablefuelcapping()
{
document.forms[0].G_oilfuelgapping.disabled=false;
document.forms[0].G_oilemmisionfactor.disabled=false;
document.forms[0].G_gasfuelgapping.disabled=false;
document.forms[0].G_gasemmisionfactor.disabled=false;
}

function del()
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
 return true;

}
else
{
return false;
}
}

function disConnect()
{

if(document.forms[0].G_status.value==-1 || document.forms[0].G_status.value==2 || document.forms[0].G_status.value==3 || document.forms[0].G_status.value==4)
{
document.forms[0].G_disconnecteddate.disabled=false;
}
else
{
document.forms[0].G_disconnecteddate.value="";
document.forms[0].G_disconnecteddate.disabled=true;
}
}

function searchExist(){
	var url = "/eespc/GeneratorListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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

function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

 </script>

</head>
<body>

<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<html:form action="/Generator" enctype="multipart/form-data">

<html:hidden property="methodToCall"  /><html:hidden property="id" />
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<html:hidden property="fueldeleteConsumptionId" />
<input name="bctype" type="hidden">
<html:hidden property="enginedeleteRunningHrsId" />
<input name="ebctype" type="hidden">

<SPAN CLASS=page_title>Generator</SPAN> <br>		
	<br>			
<table width="100%" border="1" cellspacing="0" cellpadding="0" height="778" style="border-collapse: collapse" bordercolor="#F2F2F2">
  <tr> 
    <td class="label_right" height="25"><eespc:requiredField />ID # 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td width="69%"  nowrap class="fieldleft" height="25">&nbsp; 
		<eespc:displayControl paramName="G_facilityDesignatedId" formName="generator"> 
			<html:text property="G_facilityDesignatedId" styleClass="normal" /> 
			          <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
		</eespc:displayControl>
		(Facility Designated)
  </tr>
  <tr> 
    <td width="31%" nowrap class="label_right" height="15">Floor 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_floor" formName="generator"> 
			<html:text property="G_floor" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Permit ID #		
    	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitID();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_stateId" formName="generator"> 
			<html:text property="G_stateId" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Primary Use  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PrimaryUse();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_primaryUse" formName="generator"> 
			<html:select property="G_primaryUse" styleClass="normal" onchange="displayControl();" >
				<html:optionsCollection name="GENERATOR_PRIMARY_USE" property="dropDownValues" value="value" label="name"/> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Year Installed 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_yearInstalled" formName="generator"> 
			<html:text property="G_yearInstalled" styleClass="normal"  size="4" maxlength="4" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
   <tr> 
    <td class="label_right" height="15"><eespc:requiredField />Status Of Source 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusOfSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_status" formName="generator"> 	
			<html:select property="G_status" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>	   											  
	  &nbsp; <strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="G_disconnecteddate" formName="generator"> 
			<html:text property="G_disconnecteddate" styleClass="normal" size="10" maxlength="4" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	  </td>
  </tr>


  <tr> 
    <td class="label_right" nowrap height="15">Manufacturer 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Manufacturer();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_manufacturer" formName="generator"> 
			<html:text property="G_manufacturer" styleClass="normal"  /> 
		</eespc:displayControl>
    </td>
  </tr>

  <tr> 
    <td class="label_right" nowrap height="15">Model 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Model();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_model" formName="generator"> 
			<html:text property="G_model" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Serial # 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serial();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_serial" formName="generator"> 
			<html:text property="G_serial" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>

  <tr> 
    <td class="label_right" nowrap valign="top" height="60">
    <eespc:requiredField />Capacity 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacity();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="60">&nbsp; 
    <eespc:displayControl paramName="G_capacity" formName="generator"> 
      <html:text property="G_capacity" styleClass="normal"  onblur="calcOtherUnit();" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> </eespc:displayControl> 
      (KWH) <br>&nbsp; <html:text property="G_kvaText" readonly="true" styleClass="normal" /> (KVA) 
	  <br>&nbsp; <html:text property="G_hpText"  readonly="true" styleClass="normal" /> (HP) 
	  <br>&nbsp; <html:text property="G_mmbtuText" readonly="true" styleClass="normal" /> (MMBTU/Hr) 	  
	  </td>
  </tr>

  <tr> 
    <td class="label_right" nowrap height="15"><eespc:requiredField />Fuel 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Fuel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_primaryFuel" formName="generator"> 
			<html:select property="G_primaryFuel" styleClass="normal" onchange="fuelChanged();">
				<html:optionsCollection name="GENERATOR_PRIMARY_FUEL" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Oil 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateOil();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_oilFiringRate" formName="generator"> 
			<html:text property="G_oilFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (GPH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Firing Rate - Natural Gas 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateNaturalGas();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_naturalGasFiringRate" formName="generator"> 
			<html:text property="G_naturalGasFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (CFH) </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="26">Fuel from Tank 
	

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Fuelfromtank();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="26">&nbsp;
		<eespc:displayControl paramName="G_fuelFromName" formName="generator"> 
			<html:hidden property="G_fuelFrom" /> 
			<html:text property="G_fuelFromName" readonly="true" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchFuel();">
		</eespc:displayControl>
      </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Has Day Tank
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="HasDayTank();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_hasDayTank" formName="generator"> 		
			<html:radio property="G_hasDayTank" value="Y" onclick="useChanged();" />Yes 
			<html:radio property="G_hasDayTank" value="N" onclick="useChanged();" />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
<logic:equal name="generator" property="G_hasDayTank" value="Y">      
  <tr> 
    <td class="label_right" nowrap height="26">Day Tank Detail 
	

 
 
    : </td>
    <td class="fieldleft"  nowrap height="26">&nbsp;
		<eespc:displayControl paramName="G_dayTankFromName" formName="generator"> 
			<html:hidden property="G_dayTankFrom" /> 
			<html:text property="G_dayTankFromName" readonly="true" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchDayTankFuel();">
		</eespc:displayControl>
      </td>
  </tr>
</logic:equal>
      
      
      
  <tr> 
    <td class="label_right" nowrap height="26">Is the Generator capable of dual fuel?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="capableOfDualfuel();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="673">&nbsp; 				
		<eespc:displayControl paramName="capablefuel" formName="generator"> 		
			<html:radio property="capablefuel" value="Y" onclick="displayControl();GUI_CHANGE();"/>Yes
			<html:radio property="capablefuel" value="N" onclick="displayControl();GUI_CHANGE();"/>No			
		</eespc:displayControl>	 	
	</td>
  </tr>
  
<logic:equal name="generator" property="capablefuel" value="N">	  
    <tr> 
    <td class="label_right" nowrap height="26">Is the Generator is oil only firing?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="oilfiring();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="673">&nbsp; 				
		<eespc:displayControl paramName="oilfiring" formName="generator"> 		
			<html:radio property="oilfiring" value="Yes" onclick="displayControl();GUI_CHANGE();"/>Yes
			<html:radio property="oilfiring" value="No" onclick="displayControl();GUI_CHANGE();"/>No			
		</eespc:displayControl>	 	
	</td>
  </tr>
  </logic:equal>  
  
 
  <tr> 
    <td class="label_right" nowrap height="26"><%=dob%> Filing?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Dobfiling();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="673">&nbsp; 				
		<eespc:displayControl paramName="dobfiling" formName="generator"> 		
			<html:radio property="dobfiling" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="dobfiling" value="N" onclick="displayControl();"/>No
			<html:radio property="dobfiling" value="N/A" onclick="displayControl();"/>N/A 
		</eespc:displayControl>	 	
	</td>
  </tr>
  
<logic:equal name="generator" property="dobfiling" value="Y">		  
  <tr> 
    <td class="label_right" nowrap height="15"><%=dob%> Filing/Job #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_nycDob" formName="generator"> 
			<html:text property="G_nycDob" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
    <tr> 
    <td class="label_right" nowrap height="26"><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="673">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="generator"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes
			<html:radio property="dobsignoff" value="N"/>No			
		</eespc:displayControl>	 	
	</td>
  </tr>
  </logic:equal>
  
    <tr> 
    <td class="label_right" nowrap height="26">Fire Department Approval<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FireDeptcertificateofapproval();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="673">&nbsp; 				
		<eespc:displayControl paramName="firedepartmentapproval" formName="generator"> 		
			<html:radio property="firedepartmentapproval" value="Y"/>Yes
			<html:radio property="firedepartmentapproval" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  <tr>
      <td class="label_right" nowrap height="15">MEA #      
      <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="MEA();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_mea" formName="generator"> 
			<html:text property="G_mea" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr> 
    <td class="label_right"  nowrap height="15"><b><%=dep%> Permit Status?</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitStatus();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
     <eespc:displayControl paramName="depPermitStatus" formName="generator"> 
       <html:radio property="depPermitStatus" value="Y" onclick="displayControl();"/>Yes
       <html:radio property="depPermitStatus" value="N" onclick="displayControl();"/>No 
       <html:radio property="depPermitStatus" value="N/A" onclick="displayControl();"/>N/A
    </eespc:displayControl>
    </td>
  </tr>
  
   <logic:equal name="generator" property="depPermitStatus" value="Y">
  <tr> 
    <td class="label_right" nowrap height="15"><%=dep%> # 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEP();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_dep" formName="generator"> 
			<html:text property="G_dep" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>	
   


	<tr> 
    <td class="label_right"  nowrap height="15"><b>Does the Permit Expire?</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitExpire();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
     <eespc:displayControl paramName="depPermitExpire" formName="generator"> 
       <html:radio property="depPermitExpire" value="Y" onclick="displayControl();"/>Yes
       <html:radio property="depPermitExpire" value="N" onclick="displayControl();"/>No 
     </eespc:displayControl>
    </td>
  </tr>
  </logic:equal>	
  
  <tr> 
    <td class="label_right" nowrap height="15">Schedule C  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ScheduleC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_sechduelC" formName="generator"> 		
			<html:radio property="G_sechduelC" value="Y"  />Yes 
			<html:radio property="G_sechduelC" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Plan Approval  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PlanApproval();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_planApproval" formName="generator"> 		
			<html:radio property="G_planApproval" value="Y"  />Yes 
			<html:radio property="G_planApproval" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="26">Stack Exhausting  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" />: </td>
    <td  nowrap class="fieldleft" height="26">&nbsp;
		<eespc:displayControl paramName="G_stackFromName" formName="generator"> 
			<html:hidden property="G_stackFrom" /> 		
			<html:text property="G_stackFromName" readonly="true" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Search" onClick="searchStack();">
		</eespc:displayControl>
	  </td>
  </tr>

  <tr> 
    <td class="label_right" nowrap height="15">Is Records in Permanent Bound Book  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Isrecordsinpermanentboundbook();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_isRecordInBook" formName="generator"> 		
			<html:radio property="G_isRecordInBook" value="Y"  />Yes 
			<html:radio property="G_isRecordInBook" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
<logic:equal name="isPlm" value="Y"> 

   <tr> 
    <td class="label_right" height="30">Is Stack Test Required?  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Stacktest();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="G_stackTest" formName="generator"> 		
			<html:radio property="G_stackTest" value="Y"  onclick="displayControl();"/>Yes 
			<html:radio property="G_stackTest" value="N"  onclick="displayControl();"/>No
			 
		</eespc:displayControl>	  											  
	</td>
  </tr>
   
   
  <logic:equal name="generator" property="G_stackTest" value="Y"> 
  <tr> 
    <td class="label_right" height="15">DEC Permits Obtained? 
    
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DECpermitsobtained();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_decPermitObtained" formName="generator"> 		
			<html:radio property="G_decPermitObtained" value="Y"/>Yes 
			<html:radio property="G_decPermitObtained" value="N"/>No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>

  <tr> 
    <td class="label_right" height="15"><%=dep%> Permits Obtained ?
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPpermitsobtained();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_depPermitObtained" formName="generator"> 		
			<html:radio property="G_depPermitObtained" value="Y"  />Yes 
			<html:radio property="G_depPermitObtained" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  




  <tr> 
    <td class="label_right" height="30">Stack Test Protocol Submitted to DEC  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StacktestprotocolsubmittedtoDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="G_stackTestProtSubmited" formName="generator"> 		
			<html:radio property="G_stackTestProtSubmited" value="Y"/>Yes 
			<html:radio property="G_stackTestProtSubmited" value="N"/>No
			 	
 
		</eespc:displayControl>	  											  
	</td>
  </tr>
   <tr> 
    <td class="label_right" height="30">Other Generators Combined with the Test  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OtherGeneratorscombinedwiththetest();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="G_retestPlanned" formName="generator"> 		
			<html:radio property="G_retestPlanned" value="Y"  />Yes 
			<html:radio property="G_retestPlanned" value="N"  />No 
		</eespc:displayControl>	  											  

	</td>
  </tr>

<tr>
    <td class="label_right" height="15">Test Conducted by  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestConductedby();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15"> &nbsp;
		<eespc:displayControl paramName="G_testConductedBy" formName="generator"> 
			<html:text property="G_testConductedBy" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15">Test Report Submitted to DEC 
     
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestReportsubmittedtoDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15"> &nbsp;
		<eespc:displayControl paramName="G_testReportSubmited" formName="generator"> 		
			<html:radio property="G_testReportSubmited" value="Y"  />Yes 
			<html:radio property="G_testReportSubmited" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
   <tr> 
    <td class="label_right" height="30">Stack Test Date Required by the Permit</strong>(<font color="#006699">mm/dd/yyyy</font>)  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackTestDaterequiredbythepermit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="G_ProtocalSubmittalDate" formName="generator"> 
			<html:text property="G_ProtocalSubmittalDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
  
  <tr> 
    <td class="label_right" height="15">Unit Comply with NOx RACT plan  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UnitcomplywithNOxRACTplan();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_compyWithNoxRactPlan" formName="generator"> 		
			<html:radio property="G_compyWithNoxRactPlan" value="Y"  />Yes 
			<html:radio property="G_compyWithNoxRactPlan" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
 

  <tr> 
    <td class="label_right" height="15">Unit Comply with PM RACT plan  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UnitcomplywithPMRACTplan();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_compyWithPMRactPlan" formName="generator"> 		
			<html:radio property="G_compyWithPMRactPlan" value="Y"  />Yes 
			<html:radio property="G_compyWithPMRactPlan" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
    <tr> 
    <td class="label_right" height="15">Test Passed/Compliance  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestPassedCompliance();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_testPassed" formName="generator"> 		
			<html:radio property="G_testPassed" value="Y"  />Yes 
			<html:radio property="G_testPassed" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  
   	

  <tr> 
    <td class="label_right" height="15">Stack Test Date(<font color="#006699">mm/dd/yyyy</font>) 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackTestDate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_StackTestDate" formName="generator"> 
			<html:text property="G_StackTestDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>

  <tr> 
    <td class="label_right" height="15">Next Stack Test Date(<font color="#006699">mm/dd/yyyy</font>)  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="NextStackTestDate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_nextStackTestDate" formName="generator"> 
			<html:text property="G_nextStackTestDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>
  	

    <tr> 
    <td class="label_right" height="15">Note  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="NextStackTestDateNote();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_nextStackTestDateNote" formName="generator"> 
			<html:text property="G_nextStackTestDateNote" styleClass="normal" size="20"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
  </logic:equal> 
  </logic:equal> 
    <tr> 
    <td class="label_right" height="15">Fuel Capping  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="G_fuelgapping" formName="generator"> 		
			<html:radio property="G_fuelgapping" value="Y" onclick="enablefuelcapping()"  />Yes 
			<html:radio property="G_fuelgapping" onclick="document.forms[0].G_gasfuelgapping.value='';document.forms[0].G_gasfuelgapping.disabled=true;document.forms[0].G_gasemmisionfactor.value='';document.forms[0].G_gasemmisionfactor.disabled=true;document.forms[0].G_oilfuelgapping.value='';document.forms[0].G_oilfuelgapping.disabled=true;document.forms[0].G_oilemmisionfactor='';document.forms[0].G_oilemmisionfactor.disabled=true;" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15">Gas Fuel Capping  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="GasFuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">
	
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
      <tr>
        <td width="33%">&nbsp;<eespc:displayControl paramName="G_gasfuelgapping" formName="generator"> 
			<html:text property="G_gasfuelgapping" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;CFY</td>
        <td width="33%" align="right">Gas NOx Emission Factor 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="GasNOxEmissionfactor();"  onmouseout="UnTip()" />:</td>
        <td width="34%">&nbsp;<eespc:displayControl paramName="G_gasemmisionfactor" formName="generator"> 
			<html:text property="G_gasemmisionfactor" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/million cu.ft.</td>
      </tr>
    </table>
	
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15">Oil Fuel Capping  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OilFuelCapping();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">
		
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2">
      <tr>
        <td width="33%">&nbsp;<eespc:displayControl paramName="G_oilfuelgapping" formName="generator"> 
			<html:text property="G_oilfuelgapping" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;GPY</td>
        <td width="33%" align="right">&nbsp;Oil NOx Emission Factor
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OilNOxEmissionfactor();"  onmouseout="UnTip()" />:</td>
        <td width="34%">&nbsp;<eespc:displayControl paramName="G_oilemmisionfactor" formName="generator"> 
			<html:text property="G_oilemmisionfactor" styleClass="normal"  size="7"			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/1000 gal</td>
      </tr>
    </table>
		
	</td>
  </tr>
<tr> 
    <td class="label_right" >SO2 Emission Factor, Oil  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SO2EmissionfactorOil();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15"  width="433">&nbsp;
		<eespc:displayControl paramName="g_oilso2" formName="generator"> 
			<html:text property="g_oilso2" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/1000 gal  &nbsp;&nbsp;<b>Gas
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="so2Emissionfactorgas();"  onmouseout="UnTip()" /></b>:<eespc:displayControl paramName="g_gasso2" formName="generator"> 
			<html:text property="g_gasso2" styleClass="normal" size="7"			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;lb/million cu.ft.
	</td>
  </tr>
   <tr> 
    <td class="label_right" >SO2 Allowable<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SO2Allowable();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15"  width="433">&nbsp;
		<eespc:displayControl paramName="g_so2allowable" formName="generator"> 
			<html:text property="g_so2allowable" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;Tons/Yr &nbsp;&nbsp;<b>NOX Allowable<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="NOXAllowable();"  onmouseout="UnTip()" /></b>:<eespc:displayControl paramName="g_noxallowable" formName="generator"> 
			<html:text property="g_noxallowable" styleClass="normal" 	size="7"		
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"  /> 
		</eespc:displayControl>&nbsp;Tons/Yr
	</td>
  </tr>
 
  <tr> 
    <td class="label_right" height="45">Is There Fuel Capping or the Hours of Operation Limit under DEC for this Unit?  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IstherefuelcappingorthehoursofoperationlimitunderDECforthisunit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="45">&nbsp;
		<eespc:displayControl paramName="G_fuelCaping" formName="generator"> 
			<html:radio property="G_fuelCaping" value="Y"  />Yes 
			<html:radio property="G_fuelCaping" value="N"  />No 		
		</eespc:displayControl>
	</td>
  </tr>
   <tr> 
    <td width="31%" nowrap class="label_right" height="15">Comments  
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="G_actiontaken" formName="generator"> 
		   <html:textarea property="G_actiontaken" cols="30" rows="2"/>			
		</eespc:displayControl>
	</td>
  </tr>
</table>		

	<logic:equal name="showAddBtn" value="Y">
	
	 <logic:equal name="generator" property="depPermitExpire" value="Y">
	 <hr>        
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dep%> Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note</td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
<%
			List inspectionList = (List)session.getAttribute("GNR_DEP_PERMIT");
			boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("GNR_EDIT_DEP_PERMIT"));			
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
						//out.println(inspVo.getIssueDate());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depIssueDate", inspVo.getIssueDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(inspVo.getExpirationDate());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpDate", inspVo.getExpirationDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(inspVo.getDepCompliancecomments());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpDateNote", inspVo.getDepCompliancecomments(),null));
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
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="depIssueDate" type="text" class="normal" id="depIssueDate" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="depExpDate" type="text" class="normal" id="depExpDate" size="10" maxlength="10"> 
                </td>
                 <td width="26%" align="center"  nowrap class="fieldleft"><input name="depExpDateNote" type="text" class="normal" id="depExpDateNote" size="20"> 
                </td>
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditDepPermit loop
%>


              <tr class="evenRowStyle"> 
                <td    colspan=5>
		<input type="button" name="Button22" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDepPermit)? "addDep(true)" : "addDep(false)") %>;" style="float: right">                
                </td>
              </tr>

            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
</logic:equal>	

		
<hr>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dob%> Permit Information 
      </FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note</td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
               
              </tr>
<%
			List dobInspectionList = (List)session.getAttribute("GNR_DOB_PERMIT");
//out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK:" + dobInspectionList);
			boolean toEditDobPermit = UtilityObject.convertBoolean((String)request.getAttribute("GNR_EDIT_DOB_PERMIT"));			
			if (dobInspectionList != null){
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
					//out.println(	dobInspVo.getIssueDate());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobIssueDate", dobInspVo.getIssueDateStr(),null));
					out.println("</td>");
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getExpirationDate());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobExpDate", dobInspVo.getExpirationDateStr(),null));
					out.println("</td>");
					out.println("<td nowrap class=\"fieldleft\">");
					//out.println(	dobInspVo.getDepCompliancecomments());
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobExpDateNote", dobInspVo.getDepCompliancecomments(),null));
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
				}
			}
			if (!toEditDobPermit){ //show the following only if it is not edit						
%>				  
			  
              <tr class="evenRowStyle"> 
                
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dobIssueDate" type="text" class="normal" id="dobIssueDate"  size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dobExpDate" type="text" class="normal" id="dobExpDate"  size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dobExpDateNote" type="text" class="normal" id="dobExpDateNote"  size="20"> 
                </td>
                <td   class="fieldleft" colspan=2 >&nbsp;</td>
              </tr>
<%
			}// if !toEditDobPermit loop
%>

              <tr class="evenRowStyle"> 
                <td    colspan=5>
                <input type="button" name="Button22" value="<%= ((toEditDobPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDobPermit)? "addDob(true)" : "addDob(false)") %>;" style="float: right">                
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
	<jsp:include page="AnnualFuelConsumptionoil.jsp"/>
	</div>
    </td>

  </tr>
</table>

<br>

	<logic:equal name="isPlm" value="Y"> 

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>40 CFR Part 63, Subpart ZZZZ Compliance Status</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100% >
        <TR BGCOLOR=white> 
          <TD> <table id="hiddingTable" border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle">  
                <td   class=columnhead>Initial Compliance Test Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td> 
                <td style="display:block;"  class=columnhead>Initial Pressure Drop Test (in. w.c.)<br>Oil-Firing</td>                          
                <td style="display:block;"   class=columnhead>Initial Pressure Drop Test (in. w.c.)<br>Gas-Firing</td>                
                <td   class=columnhead>Last Subsequent Test Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
				<td style="display:block;"   class=columnhead>Subsequent Pressure Drop (in. w.c.)<br>Oil-Firing</td> 
				<td style="display:block;"   class=columnhead>Subsequent Pressure Drop (in. w.c.)<br>Gas-Firing</td>          
                <td   class=columnhead>Edit</td>                
               	<td   class=columnhead>Delete</td>                
              </tr>
              
              
                <%
			List cfrInspectionList = (List)request.getAttribute("GNR_CFR_PERMIT");
              //  out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK:" + cfrInspectionList);
			boolean toEditCfrPermit = UtilityObject.convertBoolean((String)request.getAttribute("GNR_EDIT_CFR_PERMIT"));			
			if (cfrInspectionList != null){
				int size = cfrInspectionList.size();
				for (int i=0; i < size; i++)
				{
					GeneratorCfrPermitInfoVo cfrVo = (GeneratorCfrPermitInfoVo)cfrInspectionList.get(i);
					boolean isCfrPermitEditable = false;
					int permitId = cfrVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
						//	out.println(tempStr + "==" + permitIdStr + ", " + toEditCfrPermit);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(permitIdStr) && toEditCfrPermit)
					{
						isCfrPermitEditable = true;
					}
					
					  out.println("<tr class=\"evenRowStyle\">");				  
						out.println("<td nowrap align='center' class=\"fieldleft\">");
						//out.println(cfrVo.getInitialTestDate());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isCfrPermitEditable, "initialTestDate", cfrVo.getInitialTestDateStr(),null));
						out.println("</td>");
						
						out.println("<td nowrap style='display:block;' align='center' class=\"fieldleft\">");
						//out.println(cfrVo.getInitialPressureOil());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isCfrPermitEditable, "initialPressureOil", cfrVo.getInitialPressureOil(),null));
						out.println("</td>");
						
						
			 			out.println("<td nowrap style='display:block;' align='center' class=\"fieldleft\">");
						//out.println(cfrVo.getInitialPressureGas());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isCfrPermitEditable, "initialPressureGas", cfrVo.getInitialPressureGas(),null));
						out.println("</td>");
						
						
						out.println("<td nowrap align='center' class=\"fieldleft\">");
						//out.println(cfrVo.getLastSubsequentTestDate());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isCfrPermitEditable, "lastSubsequentTestDate", cfrVo.getLastSubsequentTestDateStr(),null));
						out.println("</td>");
						
						out.println("<td nowrap style='display:block;'  align='center' class=\"fieldleft\">");
						//out.println(cfrVo.getSubsequentPressureOil());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isCfrPermitEditable, "subsequentPressureOil", cfrVo.getSubsequentPressureOil(),null));
						out.println("</td>");
						
						out.println("<td nowrap style='display:block;'  align='center' class=\"fieldleft\">");
						//out.println(cfrVo.getSubsequentPressureGas());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isCfrPermitEditable, "subsequentPressureGas", cfrVo.getSubsequentPressureGas(),null));
						out.println("</td>");
								
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isCfrPermitEditable){
							out.println("<a href=\"javascript:editCfrPermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");		
						
                       out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isCfrPermitEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteCfrPermit('");
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
			if (!toEditCfrPermit){ //show the following only if it is not edit			
%>				  
              
                <tr class="evenRowStyle"> 
                <td  align="center"  nowrap class="fieldleft"><input name="initialTestDate" type="text" class="normal" id="initialTestDate" size="10" maxlength="10">												
				</td>			  
                <td  align="center"  style='display:block;' nowrap class="fieldleft" ><input name="initialPressureOil" type="text" class="normal" id="initialPressureOil" size="10" maxlength="10"> 
                </td>                                
                <td  align="center" style='display:block;' nowrap class="fieldleft"><input name="initialPressureGas" type="text" class="normal" id="initialPressureGas" size="10" maxlength="10"> 
                </td>                
                <td  align="center"  nowrap class="fieldleft"><input name="lastSubsequentTestDate" type="text" class="normal" id="lastSubsequentTestDate" size="10" maxlength="10"> 
                </td>
                <td  align="center" style='display:block;' nowrap class="fieldleft"><input name="subsequentPressureOil" type="text" class="normal" id="subsequentPressureOil" size="10" maxlength="10"> 
                </td>                   
                <td  align="center" style='display:block;' nowrap class="fieldleft"><input name="subsequentPressureGas" type="text" class="normal" id="subsequentPressureGas" size="10" maxlength="10"> 
                </td>                
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditCfrPermit loop
%>


 <tr class="evenRowStyle"> 
                <td    colspan=4> 
                <html:file property="filename" />
                <input type="button" value="Upload" name="B1" onClick="fileupload();" > </td>              
               <td colspan=4> <input type="button" name="Button22" value="<%= ((toEditCfrPermit)? "Update" : "Add") %>" onClick="<%= ((toEditCfrPermit)? "addCfr(true)" : "addCfr(false)") %>;" style="float: right">
                </td>
             

                 
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>


<br>




<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
    
	<jsp:include page="MonthlyEngineRunningHrs.jsp" />
	
	</div>
    </td>

  </tr>
</table>
	<br>

<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="MonthlyPressureTestOil.jsp" />
	</div>
    </td>

  </tr>
</table>

<br>
<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="MonthlyPressureTestGas.jsp" />
	</div>
    </td>

  </tr>
</table>
<br>

<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="AnnualMaintainanceSparkPlug.jsp" />
	</div>
    </td>

  </tr>
</table>


</logic:equal>
</logic:equal>

<br>
<logic:equal name="showAddBtn" value="Y">

<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />
<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("Generator.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("Generator.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addGenerator(true)" : "addGenerator(false)") %>;">
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