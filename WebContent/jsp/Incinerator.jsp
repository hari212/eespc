<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo,com.eespc.tracking.bo.StackTestVo,com.eespc.tracking.bo.IncConsumptionVo,com.eespc.tracking.bo.ChConsumptionVo ,com.eespc.tracking.bo.myenum.YesNoEnum,com.eespc.tracking.bo.IncConsumptionVo,com.eespc.tracking.bo.myenum.ProductStorageEnum" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Incinerator</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScripts.js" ></script>
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Incin.js" ></script>
<script src="/eespc/js/FuelConsumptionCalculation.js" ></script>
<script src="/eespc/help/incineratorcrematorieshelp.js" ></script>
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
	var url = 'IncineratorAction.do?methodToCall=fuelConsumptionInfoyearconsumtion&fcyear='+serviceNameValue;
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
       // alert(req.responseText+"");
        document.forms[0].hdnPreviousConsumption.value=req.responseText;
        validateFuelConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }
  
function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

function disConnect()
{

if(document.forms[0].inc_status.value==-1 || document.forms[0].inc_status.value==2 || document.forms[0].inc_status.value==3 || document.forms[0].inc_status.value==4)
{
document.forms[0].inc_disconnecteddate.disabled=false;
}
else
{
document.forms[0].inc_disconnecteddate.disabled=true;
}
}
function searchExist(){
	var url = "/eespc/IncineratorListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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


String file_path=String.valueOf(request.getAttribute("FILE_PATH")).replaceAll("&", "%26");  



%>

</head>
<body>
<script src="tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Incinerator/Crematories</SPAN> <br>		
<br>
<html:form action="/IncineratorAction" enctype="multipart/form-data" >

<html:hidden property="id" />
<html:hidden property="bctype" />
<html:hidden property="fueldeleteConsumptionId" />
<input type="hidden" name="methodToCall" value="reset"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">


	<br>			
<p>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#CCCCCC" width="100%" id="AutoNumber1" height="913">
  <tr>
    <td width="50%" align="right" height="27"><b><eespc:requiredField />ID # </b>
		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="27">
    <eespc:displayControl paramName="incId" formName="incForm"> 
       <html:text property="incId" styleClass="normal"/>
       			<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>			   
    (Facility Designated)</td>
  </tr>
 <tr>
    <td width="50%" align="right" height="38"><b>Type of Unit</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TypeOfUnit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="38">
    <eespc:displayControl paramName="typeofunit" formName="incForm"> 
        <html:radio property="typeofunit"  value="Crematory" styleClass="normal"/>
    Crematory 
        <html:radio property="typeofunit"  value="Municipalincinerator" styleClass="normal"/>Municipal Incinerator
        <html:radio property="typeofunit"  value="Medicalwasteincinerator" styleClass="normal"/>Medical Waste Incinerator
        </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="27"><b>Stack Exhausting </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="27">
    <eespc:displayControl paramName="incStackFrom" formName="incForm"> 
    			<html:hidden property="incStack" /> 		
    			<html:text property="incStackFrom" styleClass="normal" /> 
    			<input type="button" name="Submit23" value="Search" onClick="searchStack();">
    		</eespc:displayControl></td>
  </tr>
 
  <tr>
    <td width="50%" align="right" height="19"><b>Floor # </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incFloor" formName="incForm"> 
       <html:text property="incFloor" styleClass="normal"/>
    </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Make / Model </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="MakeModel();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incMake" formName="incForm"> 
       <html:text property="incMake" styleClass="normal"/>
      </eespc:displayControl>			   
      / 
      <eespc:displayControl paramName="incModel" formName="incForm"> 
      <html:text property="incModel" styleClass="normal"/>
      </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Serial # </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serial();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incSerialNo" formName="incForm"> 
      <html:text property="incSerialNo" styleClass="normal"/>
        </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Year Installed </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incYearIns" formName="incForm"> 
           <html:text property="incYearIns" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
      </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Burner Capacity </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerCapacity();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incBurner" formName="incForm"> 
        <html:text property="incBurner" styleClass="normal"/>
      </eespc:displayControl>			   
      (MM BTU/Hr)</td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b><eespc:requiredField />Status 
    of Source </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusOfSource();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="inc_status" formName="incForm"> 	
			<html:select property="inc_status" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>&nbsp;<strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="inc_disconnecteddate" formName="incForm"> 
			<html:text property="inc_disconnecteddate" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Burner Make / Model </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerMakeModel();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incBurnMake" formName="incForm"> 
       <html:text property="incBurnMake" styleClass="normal"/>
     </eespc:displayControl>			      
      / 
      <eespc:displayControl paramName="incBurnModel" formName="incForm"> 
      <html:text property="incBurnModel" styleClass="normal"/>
      </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Type of Waste Burned </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TypeofWasteBurned();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incWasteBurnType" formName="incForm"> 
      <html:select property="incWasteBurnType" styleClass="normal">
        	<html:optionsCollection name="INCINERATOR_WASTE_TYPE" property="dropDownValues" value="value" label="name"/> 
      </html:select>
      </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Capacity </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacity();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19"> 
    <eespc:displayControl paramName="incCapacity" formName="incForm"> 
       <html:text property="incCapacity" styleClass="normal"/>
      </eespc:displayControl>
     (lb / hr)</td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Is There a Scrubber Installed </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereaScrubberInstalled();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incScrubber" formName="incForm"> 
        <html:radio property="incScrubber"  value="Y" styleClass="normal"/>Yes 
        <html:radio property="incScrubber"  value="N" styleClass="normal"/>No
        </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"> <b>Location </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Location();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incLocation" formName="incForm"> 
		<html:select property="incLocation" styleClass="normal">
			<html:optionsCollection name="AGENCY_LOCATION" property="dropDownValues" value="value" label="name"/> 
        	</html:select>		
    </eespc:displayControl></td>
  </tr>
 <tr>
    <td width="50%" align="right" height="19"><b><%=dob%> Sign Off    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="dobsignoff" formName="incForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes <html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	  </td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b><%=dob%> Approval Required 

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBApprovalRequired();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incDOB" formName="incForm"> 
      <html:radio property="incDOB"  value="Y" onclick="displayControl();" styleClass="normal"/>Yes 
      <html:radio property="incDOB"  value="N" onclick="displayControl();" styleClass="normal"/>No
    </eespc:displayControl> </td>
  </tr>
  
   <logic:equal name="incForm" property="incDOB" value="Y"> 
   <tr>
    <td width="50%" align="right" height="19"><b><%=dob%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="dobno" formName="incForm"> 
    <html:text property="dobno" styleClass="normal"/>
    </eespc:displayControl> </td>
  </tr>
</logic:equal>
  <tr>
    <td width="50%" align="right" height="19"><b>Is CO Monitor Installed </b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsCOmonitorinstalled();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incCOMonitor" formName="incForm">  
      <html:radio property="incCOMonitor"  value="Y" styleClass="normal"/>Yes 
      <html:radio property="incCOMonitor"  value="N" styleClass="normal"/>No
      <html:radio property="incCOMonitor"  value="NR" styleClass="normal"/>Not Required
     </eespc:displayControl> </td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Is Opacity Monitor Installed </b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsOpacitymonitorinstalled();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incOpacity" formName="incForm">  
    <html:radio property="incOpacity"  value="Y" styleClass="normal"/>Yes 
      <html:radio property="incOpacity"  value="N" styleClass="normal"/>No
      <html:radio property="incOpacity"  value="NR" styleClass="normal"/>Not Required
    </eespc:displayControl> </td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Is O2 Monitor Installed </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsO2monitorinstalled();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incO2Monitor" formName="incForm">  
    <html:radio property="incO2Monitor"  value="Y" styleClass="normal"/>Yes 
      <html:radio property="incO2Monitor"  value="N" styleClass="normal"/>No
      <html:radio property="incO2Monitor"  value="NR" styleClass="normal"/>Not Required
    </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Opacity Chart Recorder 
    Available </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OpacityChartRecorderAvailable();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incOpacityChart" formName="incForm">  
    <html:radio property="incOpacityChart"  value="Y" styleClass="normal"/>Yes 
      <html:radio property="incOpacityChart"  value="N" styleClass="normal"/>No
  
    </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" align="right" height="19"><b>Quarterly CGA required </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="QuarterlyCGArequired();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="incQuarterlyCGA" formName="incForm">  
    	<html:radio property="incQuarterlyCGA"  value="Y"  styleClass="normal"/>Yes 
    	<html:radio property="incQuarterlyCGA"  value="N"  styleClass="normal"/>No
    	
    </eespc:displayControl>  
  </td>
  </tr>
   <tr>
    <td width="50%" align="right" height="19"><b>Temperature measurement&nbsp; 
    Required</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TemperatureRequired();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19"> 
    <eespc:displayControl paramName="teperaturerequired" formName="incForm">  
    	<html:radio property="teperaturerequired"  value="Y"  styleClass="normal"/>Yes 
    	<html:radio property="teperaturerequired"  value="N"  styleClass="normal"/>No    	
    </eespc:displayControl> 
  </td>
  </tr>

 <tr>
    <td width="100%" align="center" colspan="2" bgcolor="#F0F0FF" height="19">
    <font face="Verdana" size="2"><b>Minimum Temperature</b></font></td>
  </tr>

 <tr>
    <td width="50%" align="right" height="19"><b>Primary</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Primary();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="primarytemp" formName="incForm">  
       <html:text property="primarytemp" styleClass="normal" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
     </eespc:displayControl>F<sup>o</sup>
  </td>
  </tr>

 <tr>
    <td width="50%" align="right" height="19"><b>Secondary</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Secondary();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="sectemp" formName="incForm">  
       <html:text property="sectemp" styleClass="normal" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
     </eespc:displayControl>F<sup>o</sup>
  </td>
  </tr>

 <tr>
    <td width="100%" align="center" colspan="2" bgcolor="#F0F0FF" height="18">
    <font face="Verdana" size="2"><b>Average Facility temperature</b></font></td>
  </tr>

 <tr>
    <td width="50%" align="right" height="19"><b>Primary</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Primaryx();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="facilityprimary" formName="incForm">  
       <html:text property="facilityprimary" styleClass="normal" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
     </eespc:displayControl> F<sup>o</sup>
  </td>
  </tr>

 <tr>
    <td width="50%" align="right" height="19"><b>Secondary</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Secondaryx();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="facilitysecondary" formName="incForm">  
       <html:text property="facilitysecondary" styleClass="normal" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
     </eespc:displayControl>F<sup>o</sup>
  </td>
   </tr> 

  <tr>
    <td width="50%" align="right" height="19"><b><%=dep%> Permit Required<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPRequired();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" height="19">
    <eespc:displayControl paramName="deprequired" formName="incForm">  
    	<html:radio property="deprequired"  value="Y" onclick="displayControl();"  styleClass="normal"/>Yes 
    	<html:radio property="deprequired"  value="N" onclick="displayControl();"  styleClass="normal"/>No
    	<html:radio property="deprequired"  value="NR" onclick="displayControl();"  styleClass="normal"/>N/A     	
    </eespc:displayControl> 
  </td>
    </tr>
    
   <logic:equal name="incForm" property="deprequired" value="Y">  
  <tr>
    <td width="50%" align="right" height="19"><b><%=dep%>#<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEP();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" height="19"> 
    <eespc:displayControl paramName="depno" formName="incForm">  
       <html:text property="depno" styleClass="normal"/>
     </eespc:displayControl>
  </td>
  </tr>
</logic:equal>
  <tr>
    <td width="50%" align="right" height="19"><b>DEC#</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEC();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19"> 
    <eespc:displayControl paramName="decno" formName="incForm">  
       <html:text property="decno" styleClass="normal"/>
     </eespc:displayControl>
  </td>
  </tr>

    
  <tr>
    <td width="50%" align="right" height="19"><b><eespc:requiredField />Stack Test 
    Required?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Stacktestrequired();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="50%" height="19"> 
    <eespc:displayControl paramName="incStackTested" formName="incForm">  
    	<html:radio property="incStackTested"  value="Y" onclick="displayControl();"  styleClass="normal"/>Yes 
    	<html:radio property="incStackTested"  value="N" onclick="displayControl();"  styleClass="normal"/>No    	
    </eespc:displayControl> 
  </td>
    </tr>
      <logic:equal name="incForm" property="incStackTested" value="Y"> 
    <tr> <td class="label_right" height="19" align="right"><b>DEC Permits 
      Obtained?
      </b> 

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DECpermitsobtained();"  onmouseout="UnTip()" /><b>:
      </b> </td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="i_decPermitObtained" formName="incForm"> 		
			<html:radio property="i_decPermitObtained" value="Y"  />Yes 
			<html:radio property="i_decPermitObtained" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>

 


  <tr> 
    <td class="label_right" nowrap height="19" align="right"><b>Was a Stack Test 
    Protocol Submitted to DEC ?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WasastacktestprotocolsubmittedtoDEC();"  onmouseout="UnTip()" /><b>: </b> </td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="protocolSubmitted" formName="incForm"> 		
			<html:radio property="protocolSubmitted" value="Y"  />Yes 
			<html:radio property="protocolSubmitted" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="19" align="right"><b>Test Conducted 
    by What Firm </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Testconductedbywhatfirm();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="testConductedBy" formName="incForm"> 
			<html:text property="testConductedBy" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="33" align="right" valign="top"><b>
    Parameters </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Parameters();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="33">
	<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#CCCCCC" width="100%" id="AutoNumber2">
      <tr>
        <td width="11%" align="center"><b><font size="2">NO</font><sub><font size="2">X</font></sub></b></td>
        <td width="11%" align="center"><b><font size="2">SO</font><sub><font size="2">2</font></sub></b></td>
        <td width="11%" align="center"><b><font size="2">PM</font></b></td>
        <td width="11%" align="center"><b><font size="2">Trace Metals</font></b></td>
        <td width="11%" align="center"><b><font size="2">PAH</font></b></td>
        <td width="11%" align="center"><b><font size="2">CO</font></b></td>
        <td width="11%" align="center"><b><font size="2">HCL</font></b></td>
        <td width="11%" align="center"><b><font size="2">DIOXIN</font></b></td>
        <td width="12%" align="center"><b><font size="2">Other</font></b></td>
      </tr>
      <tr>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters1" formName="incForm"> 		
			<html:checkbox property="B_parameters1" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters2" formName="incForm"> 		
			<html:checkbox property="B_parameters2" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters3" formName="incForm"> 		
			<html:checkbox property="B_parameters3" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters4" formName="incForm"> 		
			<html:checkbox property="B_parameters4" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters5" formName="incForm"> 		
			<html:checkbox property="B_parameters5" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters6" formName="incForm"> 		
			<html:checkbox property="B_parameters6" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters7" formName="incForm"> 		
			<html:checkbox property="B_parameters7" value="Y"/>	
		</eespc:displayControl></td>
        <td width="11%" align="center">
        <eespc:displayControl paramName="B_parameters8" formName="incForm"> 		
			<html:checkbox property="B_parameters8" value="Y"/>	
		</eespc:displayControl></td>
        <td width="12%" align="center">
        <eespc:displayControl paramName="B_parameters9" formName="incForm"> 
			<html:text property="B_parameters9" styleClass="normal" /> 
		</eespc:displayControl></td>
      </tr>
    </table>
	</td>
  </tr>
 <tr> 
    <td class="label_right" nowrap height="19" align="right"><b>Was a Test 
    Report Submitted to DEC ?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WasatestreportsubmittedtoDEC();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="testReportSubmited" formName="incForm"> 		
			<html:radio property="testReportSubmited" value="Y"/>Yes 
			<html:radio property="testReportSubmited" value="N"/>No 
		</eespc:displayControl>	  
		
													  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="19" align="right"><b>Stack Test Date 
    Required by the Permit(<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StacktestdaterequiredbythePermit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="testReportSubmitedDate" formName="incForm"> 
			<html:text property="testReportSubmitedDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="19" align="right"><b>Was a Retest 
    Planned ?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Wasaretestplanned();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="retestPlanned" formName="incForm"> 		
			<html:radio property="retestPlanned" value="Y"  />Yes 
			<html:radio property="retestPlanned" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  
    <tr> 
    <td class="label_right" height="19" align="right"><b>Test Passed/Compliance </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestPassedCompliance();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="i_testPassed" formName="incForm"> 		
			<html:radio property="i_testPassed" value="Y"  />Yes 
			<html:radio property="i_testPassed" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="19" align="right"><b>Stack Test Date(<font color="#006699">mm/dd/yyyy</font>) </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackTestDate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="i_StackTestDate" formName="incForm"> 
			<html:text property="i_StackTestDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr>
    <td class="label_right" nowrap height="19" align="right"><b>When is the Next 
    Stack Test Date(<font color="#006699">mm/dd/yyyy</font>)
    </b>  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Whenisthenextstacktest();"  onmouseout="UnTip()" /><b>:
    </b> </td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="nextStackTest" formName="incForm"> 
			<html:text property="nextStackTest" size="10" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr>
    <td class="label_right" nowrap height="19" align="right"><b>Note</b>  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="nextstacktestnote();"  onmouseout="UnTip()" /><b>:
    </b> </td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="nextStackTestNote" formName="incForm"> 
			<html:text property="nextStackTestNote" size="10" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>

    </logic:equal>
   <tr> 
    <td class="label_right" height="19" align="right"><b>Is There a Waste 
    Quantity Cap </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereaWasteQuantityCap();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="iwastequantity" formName="incForm"> 		
			<html:radio property="iwastequantity" value="Y" onclick="displayControl();"  />Yes 
			<html:radio property="iwastequantity" value="N"  onclick="displayControl();" />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <logic:equal name="incForm" property="iwastequantity" value="Y"> 
  <tr> 
    <td class="label_right" height="19" align="right"><b>If Yes,Waste Limit </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfYesWasteLimit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="iwastelimit" formName="incForm"> 	
		<html:text property="iwastelimit" size="10" styleClass="normal" /> 	
			
		</eespc:displayControl>	  											  
	</td>
  </tr>
  </logic:equal>
 <tr> 
    <td class="label_right" height="19" align="right"><b>Solid Waste Permit 
    Required by DEC </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SolidWastePermitRequiredbyDEC();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="isolidwaste" formName="incForm"> 		
			<html:radio property="isolidwaste" value="Y" onclick="displayControl();"  />Yes 
			<html:radio property="isolidwaste" value="N" onclick="displayControl();"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <logic:equal name="incForm" property="isolidwaste" value="Y"> 
 <tr> 
    <td class="label_right" height="19" align="right"><b>Issue Date(<font color="#006699">mm/dd/yyyy</font>) </b>

		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IssueDate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="i_SolidIssuedDate" formName="incForm"> 
			<html:text property="i_SolidIssuedDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
 <tr> 
    <td class="label_right" height="19" align="right"><b>Expiration Date(<font color="#006699">mm/dd/yyyy</font>) </b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ExpirationDate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="i_SolidExpirationDate" formName="incForm"> 
			<html:text property="i_SolidExpirationDate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr> 
  
   <tr>
    <td class="label_right" nowrap height="19" align="right"><b>Note</b>  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="nextstacktestnote();"  onmouseout="UnTip()" /><b>:
    </b> </td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="solidWasteExpirationNote" formName="incForm"> 
			<html:text property="solidWasteExpirationNote" size="10" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
  </logic:equal> 
   <tr> 
    <td class="label_right" height="19" align="right"><b>Comments </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" /><b>:</b></td>
    <td class="fieldleft"  nowrap height="19">
		<eespc:displayControl paramName="icomments" formName="incForm">
    <html:textarea property="icomments" cols="30" rows="2"/>
	</eespc:displayControl>
	</td>
  </tr>  

</table>

<br>
<logic:equal name="showAddBtn" value="Y">	
<logic:equal name="incForm" property="deprequired" value="Y">
<table border="1" width="100%" cellspacing="0" cellpadding="2" class="globalTableStyle" bgcolor="#006699" style="border-collapse: collapse" bordercolor="#006699">
 <tr > 
                      <td   colspan="4" bgcolor="#006699">
                      <b>
                      <font color="#FFFFFF"><%=dep%> Permit</font></b></td>
                    </tr>
    <DIV id= "DepDiv " style= "{display:<%   
        String str = (String)request.getAttribute("INC_DEP"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %> }; ">
                  

                    <tr class="oddRowStyle"> 
                      <td   class=columnhead>Issued Date</td>
                      <td   class=columnhead>Expiration Date</td>
                      <td   class=columnhead>Note</td>
                      <td   class=columnhead>Edit</td>
                      <td   class=columnhead>Delete</td>
                    </tr>
			<%
			List inspectionList1	 = (List)request.getAttribute("INC_DEP_LST");
		boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DEP_PERMIT"));
		int size1 =0;
		if (inspectionList1 != null){
			size1 = inspectionList1.size();
			for (int i=0; i < size1; i++)
			{
			PermitInfoVo statePermitInfo = (PermitInfoVo)inspectionList1.get(i);
			boolean isDepPermitEditable = false;
			int permitId = statePermitInfo.getId();
			String permitIdStr = permitId +"";
			String tempStr = (String)request.getAttribute("hdnPermitId");
				// out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
				if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
				{
					isDepPermitEditable = true;
				}              		
				
              		out.println("<tr class=\"evenRowStyle\">");
                	out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "incDEPIssueDate", statePermitInfo.getIssueDateStr(),null));
					//out.println(statePermitInfo.getIssueDateStr());
					out.print("</td>");
					
                	out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "incDEPExpirationDate", statePermitInfo.getExpirationDateStr(),null));
					//out.println(statePermitInfo.getExpirationDateStr());
					out.print("</td>");	
					
					out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "incDEPExpirationDateNote", statePermitInfo.getNote(),null));
					//out.println(statePermitInfo.getNote());
					out.print("</td>");						
			
			out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/IncineratorAction.do?methodToCall=depEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");
				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");		
				out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/IncineratorAction.do?methodToCall=depDelete&hdnPermitId=");out.print(permitId);
				out.print("\"  onclick=\"return confirm('Do you want delete this??')\">");
				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");							
		        out.println("</tr>");
			}
		}
		String method = null;
		if (!toEditDepPermit){ //show the following only if it is not edit									
		    method = "dep";    
		
		%>              
                    
                    <tr class="evenRowStyle"> 
                      <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="incDEPIssueDate" styleClass="normal"/></td>
                      <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="incDEPExpirationDate" styleClass="normal"/></td>
                      <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="incDEPExpirationDateNote" styleClass="normal"/></td>
                      <td width="6%" align="center"  nowrap class="fieldleft"></td>
                      <td></td>
                    </tr>
		<%
			}
			else // if !toEditDepPermit loop
			  method = "depUpdate";
		%>

              	    <tr align="right" class="evenRowStyle"> 
                     <td    colspan=5 >
		        <input type="submit" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="setmethod('<%= method %>');return validateDep();">
		    		</td>   

                    </tr>
                  </table>

 </DIV>             
      
</logic:equal>	
<br>
 <logic:equal name="incForm" property="iwastequantity" value="Y"> 
	<jsp:include page="IncFuelConsumption.jsp" />
</logic:equal>	
<br>


<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%                                                  out.println("IncineratorAction.do?methodToCall=back&pathname="+file_path);                               %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("IncineratorAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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



<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  
    <td  align="right">
      <html:submit property="method"  onclick="bCancel=true;returnToBuilding();">
	 <bean:message key="landFillsform.add"/>
      </html:submit>   
  </td>  
    <td>&nbsp;   
      <div id="buttondiv" style= "{display:
      <%        
        String str = (String)request.getAttribute("SHOW_BUTTONS"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); 
      %> }">
  
       <html:submit property="method" onclick="setmethod('Save');return validateMe();">
  	 <bean:message key="landFillsform.save"/>
        </html:submit>   
        <html:submit property="method"  onclick="setmethod('RESET');bCancel=true">
	 <bean:message key="landFillsform.reset"/>
      </html:submit> 
      </div>

      <div id="buttondiv" style= "{display:
      <%        
        str = (String)request.getAttribute("SHOW_UPDATE_BUTTONS"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); 
      %> }">
  
       <html:submit property="method" onclick="setmethod('Update');return validateMe();">
  	 <bean:message key="landFillsform.update"/>
        </html:submit>   
      </div>      
      
    </td>
  </tr>
</table>  


<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	      </script>
</logic:present>

</html:form> 

<b><font size="2" face="Verdana" color="#006699">Action: When all Sources are 
Completed, Facility Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>       
</body>
</html>