<%@ page language="Java"%>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc"%>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo,com.eespc.tracking.bo.ChConsumptionVo"%>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.util.UtilityObject"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Chiller</title>
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
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScripts.js"></script>
<script src="/eespc/js/CommonScript.js"></script>
<script src="/eespc/js/ChiAbs.js"></script>
<script src="/eespc/help/chillerabsorberhelp.js"></script>
<script>
function validateMe(){

    var a=document.forms[0].chFuelAbility[0].checked;
    var b=document.forms[0].chFuelAbility[1].checked;
	

	if (document.forms[0].chFacId.value.length ==0)
	{
	alert("Please Enter facility Designated Id.");
	document.forms[0].chFacId.focus();
	return false;
	}	
	else if(document.forms[0].chCapacity.value.length==0 )
	{
       	alert("Please Enter Capacity:");
	 	document.forms[0].chCapacity.focus();
		return false;		
	}
	else if(isyearFieldEnteredyear(document.forms[0].YearInstalled) ==false)
	{
		document.forms[0].YearInstalled.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].chDEPIssueDate) ==false)
	{      
        	document.forms[0].chDEPIssueDate.focus();
        	return false; 	       	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].chDEPExpirationDate) ==false)
	{      
        	document.forms[0].chDEPExpirationDate.focus();
        	return false; 	       	
	}

	else if(document.forms[0].ModifiedInPast.selectedIndex ==0) 
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].ModifiedInPast.focus();
	 	return false;
	}

	else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear) ==false)
	{

		document.forms[0].DisconnectedYear.focus();
		return false;	
	}
	
	
	if (a==false && b==false)
	{
	alert("Please select FuelAbility.");
	return false;
	}
	
	


return true;

}

function calculatecapacity()
{
var ton=document.forms[0].chCapacityton.value;
var ton2=(ton*12)/1000;
document.forms[0].chCapacity.value=ton2;
}


function calculatecapacitybtu()
{
var btu=document.forms[0].chCapacity.value;
var btu2=(btu*1000)/12;
document.forms[0].chCapacityton.value=btu2;
}



function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
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
	var url = "/eespc/ChillerSearchInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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
<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Chiller/Absorber</SPAN><br>		
<br>
<html:form action="/ChillerAction" enctype="multipart/form-data">

<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

	<br>			
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#CCCCCC" width="100%" id="AutoNumber1" height="365">
  <tr>
    <td width="45%" align="right" height="27"><b><eespc:requiredField />ID # <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="27">&nbsp;<eespc:displayControl paramName="chFacId" formName="chForm"> 
       <html:text property="chFacId" maxlength="50" styleClass="normal"/>
       <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>			   
    (Facility Designated)</td>
  </tr>
 
  <tr>
    <td width="45%" align="right" height="19"><b>Floor # <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="chFloor" formName="chForm"> 
       <html:text property="chFloor" maxlength="50" styleClass="normal"/>
    </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b>Make / Model <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="MakeModel();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="chMake" formName="chForm"> 
       <html:text property="chMake" styleClass="normal"/>
      </eespc:displayControl>			   
      / 
      <eespc:displayControl paramName="chModel" formName="chForm"> 
      <html:text property="chModel" styleClass="normal"/>
      </eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b>Serial # <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serial();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">
      <eespc:displayControl  paramName="chSerialNo" formName="chForm"> 
      &nbsp;<html:text property="chSerialNo" maxlength="50"  styleClass="normal"/></eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b>Year Installed <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;
      <eespc:displayControl paramName="YearInstalled" formName="chForm"> 
	        <html:text property="YearInstalled" styleClass="normal" size="4" maxlength="4" 
				onkeyup="this.value=intCheck(this.value)" 
    			onchange="this.value=intCheck(this.value)"	/>
      </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b><eespc:requiredField />Capacity <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacity();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;
    <eespc:displayControl paramName="chCapacityton" formName="chForm"> 
        <html:text property="chCapacityton"  maxlength="25"  styleClass="normal"
        onchange="calculatecapacity()"  onkeyup="calculatecapacity()" onfocus="calculatecapacitybtu()"/>
      </eespc:displayControl>			   
      (Tons)
 	&nbsp;	
      <eespc:displayControl paramName="chCapacity" formName="chForm"> 
        <html:text property="chCapacity" onchange="calculatecapacitybtu()" onkeyup="calculatecapacitybtu()" onfocus="calculatecapacity()"  styleClass="normal"/>
      </eespc:displayControl>			   
      (MM BTU/Hr)</td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b><eespc:requiredField />Does this Source Have Fuel Firing Ability <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DoesthechillerAbsorberhavefuelfiringability();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">
    <eespc:displayControl paramName="chFuelAbility" formName="chForm"> 
        <html:radio property="chFuelAbility"  value="Y" onclick="displayControl();" styleClass="normal"/>Yes 
        <html:radio property="chFuelAbility"  value="N" onclick="displayControl();" styleClass="normal"/>No
        </eespc:displayControl></td>
  </tr>
  
  <logic:equal name="chForm" property="chFuelAbility" value="Y">
   <tr>
    <td width="45%" align="right" height="27"><b>Stack Exhausting <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="27">&nbsp;<eespc:displayControl paramName="chStackFrom" formName="chForm"> 
    			<html:hidden property="chStack" /> 		
    			<html:text property="chStackFrom" readonly="true" styleClass="normal" /> 
    			<input type="button" name="Submit23" value="Search" onClick="searchStack();">
    		</eespc:displayControl></td>
  </tr>
  
  <tr>
    <td width="45%" align="right" height="19"><b>Burner Type <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="BurnerType();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="chBurnerType" formName="chForm"> 		
			<html:radio property="chBurnerType" value="Y"  onclick="burnerTypeSetting();" />Dual Fuel 
			<html:radio property="chBurnerType" value="N"  onclick="burnerTypeSetting();"/>Single Fuel 
		</eespc:displayControl>	  </td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b>Primary Fuel <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PrimaryFuel();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="chPrimFuel" formName="chForm"> 
    		  <html:select property="chPrimFuel" styleClass="normal" onchange="fuelChanged();">
                      <html:optionsCollection name="BOILER_PRIMARY_FUEL" property="dropDownValues" value="value" label="name"/>                  
                  </html:select>
    </eespc:displayControl></td>
  </tr> 
  
  <tr>
    <td width="45%" align="right" height="19"><b>Secondary Fuel <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SecondaryFuel();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="chSecFuel" formName="chForm"> 
    <html:select property="chSecFuel" styleClass="normal" onchange="fuelChanged();">
                      <html:optionsCollection name="BOILER_SECONDARY_FUEL" property="dropDownValues" value="value" label="name"/>                  
                  </html:select>
      </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b>Firing Rate - Oil <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateOil();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="B_oilFiringRate" formName="chForm"> 
			<html:text property="B_oilFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (GPH)</td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19"><b>Firing Rate - Natural Gas <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiringRateNaturalGas();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="B_naturalGasFiringRate" formName="chForm"> 
			<html:text property="B_naturalGasFiringRate" styleClass="normal" readonly="true" /> 
		</eespc:displayControl>
      (CFH)</td>
  </tr>
  <tr>
    <td width="45%" align="right" height="27"><b>Fuel from tank <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Fuelfromtank();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="27">&nbsp;	
    <eespc:displayControl paramName="chFuelFrom" formName="chForm"> 
    			<html:hidden property="chFuel" /> 		
    			<html:text property="chFuelFrom" readonly="true" styleClass="normal" /> 
    			<input type="button" name="Submit23" value="Search" onClick="searchFuel();">
    		</eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="7"><b>Is it Included in the DEC Title V Permit Application &amp; Annual Emissions Statement <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBApproval();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="7">
    <eespc:displayControl paramName="chTitleV" formName="chForm"> 
        <html:radio property="chTitleV"  value="Y" styleClass="normal"/>Yes 
        <html:radio property="chTitleV"  value="N" styleClass="normal"/>No
        </eespc:displayControl></td>
  </tr>   
   </logic:equal>
  
   <logic:equal name="chForm" property="chFuelAbility" value="N">
   <tr>
    <td width="45%" align="right" height="1"><b>Chiller operated by<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="chillerOperated();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="1"> 
    <eespc:displayControl paramName="chillerOperated"  formName="chForm"> 	
		<html:select property="chillerOperated" styleClass="normal">
				<html:optionsCollection name="CHILLER_OPERATED_BY" property="dropDownValues" value="value" label="name"/> 
		</html:select></eespc:displayControl></td>
	</tr> 
  </logic:equal>
  
    <tr>
    <td width="45%" align="right" height="12"><b> </b><b><%=dob%> Filing<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBFiling();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="12">
    <eespc:displayControl paramName="chDOB" formName="chForm"> 
        <html:radio property="chDOB"  value="Y" styleClass="normal" onclick="displayControl();"/>Yes 
        <html:radio property="chDOB"  value="N" styleClass="normal" onclick="displayControl();"/>No
        <html:radio property="chDOB"  value="N/A" styleClass="normal" onclick="displayControl();"/>N/A
    </eespc:displayControl></td>
  </tr>
   
   <logic:equal name="chForm" property="chDOB" value="Y"> 
   <tr>
    <td width="30%" align="right" height="11"><b><%=dob%> Filing/Job #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DobjobNum();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="11">
    <eespc:displayControl paramName="dobjobnumber" formName="chForm"> 
		<html:text property="dobjobnumber" styleClass="normal"/>			
	</eespc:displayControl></td>
  </tr>
  
  <tr>
    <td width="45%" align="right" height="12"><b><%=dob%> Signoff<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="12">
    <eespc:displayControl paramName="dobsignoff" formName="chForm"> 
        <html:radio property="dobsignoff"  value="Y" styleClass="normal"/>Yes 
        <html:radio property="dobsignoff"  value="N" styleClass="normal"/>No
        </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="12"><b>EUP Available?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="eupAvailable();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="12">
    <eespc:displayControl paramName="eupAvailable" formName="chForm"> 
        <html:radio property="eupAvailable"  value="Y" styleClass="normal"/>Yes 
        <html:radio property="eupAvailable"  value="N" styleClass="normal"/>No
        </eespc:displayControl></td>
  </tr>

  </logic:equal> 
  
  <tr>
    <td width="45%" align="right" height="12"><b> </b><b><%=dep%> Approval <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPApproval();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="12">
    <eespc:displayControl paramName="chdepapproval" formName="chForm"> 
        <html:radio property="chdepapproval"  value="Y" styleClass="normal" onclick="displayControl();"/>Yes 
        <html:radio property="chdepapproval"  value="N" styleClass="normal" onclick="displayControl();"/>No
        <html:radio property="chdepapproval"  value="N/A" styleClass="normal" onclick="displayControl();"/>N/A

    </eespc:displayControl></td>
  </tr>
  
  <logic:equal name="chForm" property="chdepapproval" value="Y"> 
  <tr>
    <td width="45%" align="right" height="11"><b>Permit Expiration Date(<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBApproval();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="11">
    <eespc:displayControl paramName="chDEPExpirationDate" formName="chForm"> 
			<html:text property="chDEPExpirationDate" styleClass="normal"/> 
	</eespc:displayControl></td>
</tr>

<tr>
    <td width="30%" align="right" height="11"><b>Note<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="11">
    <eespc:displayControl paramName="chFootnote" formName="chForm"> 
		<html:textarea property="chFootnote" cols="30" rows="2"/>			
	</eespc:displayControl></td>
  </tr>
</logic:equal> 


   
  <tr>
    <td width="45%" align="right" height="1"><b>MEA #
<b> <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="MEA();"  onmouseout="UnTip()" /></b>:</b></td>
    <td width="55%" colspan="3" height="1">&nbsp;<eespc:displayControl paramName="chMea" formName="chForm"> 
       <html:text property="chMea" maxlength="50" styleClass="normal"/>
    </eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="1"><b><eespc:requiredField />Status of Source<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();"  onmouseout="UnTip()" />:</b></td>
    <td width="13%" height="1"> 
    <eespc:displayControl paramName="ModifiedInPast"  formName="chForm"> 	
			<html:select property="ModifiedInPast" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			&nbsp;</html:select></eespc:displayControl></td>
    <td width="18%" align="right" height="1"><strong>Disconnected Year:</strong></td>
    <td width="24%" height="1">
    <eespc:displayControl paramName="DisconnectedYear" formName="chForm"> 
			<html:text property="DisconnectedYear" styleClass="normal"
			onkeyup="this.value=intCheck(this.value)" 
    			onchange="this.value=intCheck(this.value)" /> 
		</eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="1"><b>Action Taken <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ActionTaken();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="1">&nbsp;	
    <eespc:displayControl paramName="chactiontaken" formName="chForm"> 
			<html:text property="chactiontaken" maxlength="50" styleClass="normal"/> 
		</eespc:displayControl></td>
  </tr>
  <tr>
    <td width="45%" align="right" height="19" valign="top"><b>Comments <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</b></td>
    <td width="55%" colspan="3" height="19">&nbsp;<eespc:displayControl paramName="chComments" formName="chForm"> 
		<html:textarea property="chComments" cols="30" rows="2"/>
			
		</eespc:displayControl></td>
  </tr>
</table>
<p>


&nbsp;<br>
<logic:equal name="showAddBtn" value="Y">
<logic:equal name="chForm" property="chFuelAbility" value="Y"> 

	
		
    </p>
		
    <DIV id= "ConsDiv" style= "{display:<%   
       String str = (String)request.getAttribute("CH_CONS"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %> }">
	
	<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
 
 <TR BGCOLOR=#006699> 
	    <TD width="870">&#160&#160&#160 <FONT COLOR=white CLASS=section>Annual 
        Fuel Consumption</FONT></TD>
	  </TR>
	  <TR> 

		  <TD> 


<table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
		      <tr class="oddRowStyle"> 
			<td   class=columnhead>Year</td>
			<td   class=columnhead>GPY</td>
			<td   class=columnhead>CFY</td>
			<td   class=columnhead>Edit</td>
			<td   class=columnhead>Delete</td>
		      </tr>
				<%
				List inspectionList1	 = (List)request.getAttribute("CH_CONS_LST");
				boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_CONS_PERMIT"));
				int size1 =0;
				if (inspectionList1 != null){
					size1 = inspectionList1.size();
					for (int i=0; i < size1; i++)
					{
						ChConsumptionVo statePermitInfo = (ChConsumptionVo)inspectionList1.get(i);
						boolean isDepPermitEditable = false;
						int permitId = statePermitInfo.getId();
						String permitIdStr = permitId +"";
						String tempStr = (String)request.getAttribute("hdnPermitId");
						//out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
						if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
						{
							isDepPermitEditable = true;
						}              		

						out.println("<tr class=\"evenRowStyle\">");
						out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
						out.print(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isDepPermitEditable, "chAnnualYear", statePermitInfo.getYear(),"YEAR"));
						//out.println(statePermitInfo.getIssueDateStr());
						out.print("</td>");
						out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
						out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "chAnnualConsumption", statePermitInfo.getConsumption(),null));
						//out.println(statePermitInfo.getExpirationDateStr());
						out.print("</td>");	
						out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
						out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "chAnnualConsumptioncfy", statePermitInfo.getConsumptioncfy(),null));
						//out.println(statePermitInfo.getExpirationDateStr());
						out.print("</td>");			
						out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isDepPermitEditable){
							out.print("<a href=\"/eespc/ChillerAction.do?methodToCall=consumptionEdit&hdnPermitId=");out.print(permitId);
							out.print("\">");

							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");	
						
                       out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isDepPermitEditable){
							out.print("<a href=\"/eespc/ChillerAction.do?methodToCall=consumptionDelete&hdnPermitId=");out.print(permitId);
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
				    method = "cons";    
			%>				                

		      <tr class="evenRowStyle"> 
			<td width="26%" align="center"  nowrap class="fieldleft">
			  <html:select property="chAnnualYear"  styleClass="normal">
			      <html:optionsCollection name="YEAR" property="dropDownValues" value="value" label="name"/> 
			  </html:select> </td>
			<td width="26%" align="center"  nowrap class="fieldleft"><html:text property="chAnnualConsumption" styleClass="normal"/>
			</td>
			<td width="26%" align="center"  nowrap class="fieldleft"><html:text property="chAnnualConsumptioncfy" styleClass="normal"/>
			</td>

		      <td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
		      </tr>
			<%
				}
				else // if !toEditDepPermit loop
				  method = "consumptionUpdate";
			%>
		      
		      <tr align="right" class="evenRowStyle"> 
			<td    colspan=5 >
		             <input type="submit" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="setmethod('<%= method %>');return validateCons();">
                        </td>   
		      </tr>
		    </table></TD>
		</TR>
	      </TABLE></TD>
	  
</DIV>
</logic:equal>

<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%     String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");                      out.println("ChillerAction.do?methodToCall=back&pathname="+file_path);                %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("ChillerAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
  
    <td width="53%" align="right">
      <html:submit property="method"  onclick="bCancel=true;returnToBuilding();">
	 <bean:message key="landFillsform.add"/>
      </html:submit>   
  </td>  
    <td width="47%">&nbsp;
      <div id="buttondiv" style= "{display:<% 
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
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>      
</body>
</html>