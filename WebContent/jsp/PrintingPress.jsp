<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.FacilityVo"%>
<%@ page import="com.eespc.tracking.bo.PrintingInkConsumptionVo"%> 
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Printing Press</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js"></script>
<script src="/eespc/js/Press.js"></script>
<script src="/eespc/js/on_InkConsumptionCalculation.js"></script>
<script src="/eespc/js/to_InkConsumptionCalculation.js"></script>
<script src="/eespc/js/th_InkConsumptionCalculation.js"></script>
<script src="/eespc/js/fr_InkConsumptionCalculation.js"></script>
<script src="/eespc/js/fi_InkConsumptionCalculation.js"></script>
<script src="/eespc/js/si_InkConsumptionCalculation.js"></script>
<script src="/eespc/js/se_InkConsumptionCalculation.js"></script>
<script src="/eespc/help/printingpresshelp.js"></script>
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

 var req;
 var which;
function on_fetchConsumption()
{
var serviceName = document.getElementById("on_fcyear");
var serviceNameValue=serviceName.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].on_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=on_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	on_retrieveURL(url);
}

  function on_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = on_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = on_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function on_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].onx_hdnPreviousConsumption.value=req.responseText;
        on_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }


function to_fetchConsumption()
{

var serviceNameValue=document.forms[0].to_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].to_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=to_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	to_retrieveURL(url);
}

  function to_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = to_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = to_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function to_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].tox_hdnPreviousConsumption.value=req.responseText;
        to_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }
  
  
  function th_fetchConsumption()
{

var serviceNameValue=document.forms[0].th_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].th_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=th_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	th_retrieveURL(url);
}

  function th_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = th_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = th_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function th_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].thx_hdnPreviousConsumption.value=req.responseText;
        th_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

 function fr_fetchConsumption()
{

var serviceNameValue=document.forms[0].fr_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].fr_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=fr_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	fr_retrieveURL(url);
}

  function fr_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = fr_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = fr_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function fr_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].frx_hdnPreviousConsumption.value=req.responseText;
        fr_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

 function fi_fetchConsumption()
{

var serviceNameValue=document.forms[0].fi_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].fi_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=fi_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	fi_retrieveURL(url);
}

  function fi_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = fi_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = fi_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function fi_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].fir_hdnPreviousConsumption.value=req.responseText;
        fi_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

function si_fetchConsumption()
{

var serviceNameValue=document.forms[0].si_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].si_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=si_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	si_retrieveURL(url);
}

  function si_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = si_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = si_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function si_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].six_hdnPreviousConsumption.value=req.responseText;
        si_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }
function se_fetchConsumption()
{

var serviceNameValue=document.forms[0].se_fcyear.value;
    if(serviceNameValue=="Please Select")	{
       	alert("Please Choose Year:");
	 	document.forms[0].se_fcyear.focus();
	 	return false;
	}
	var url = 'PressAction.do?methodToCall=se_inkConsumptionInfoyearconsumtion&on_fcyear='+serviceNameValue;
	se_retrieveURL(url);
}

  function se_retrieveURL(url) {

    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = se_processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = se_processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function se_processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) {
       // OK response
        //alert(req.responseText+"");
        document.forms[0].ser_hdnPreviousConsumption.value=req.responseText;
        se_validateInkConsumption();
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

 

function displayControl()
{

document.forms[0].methodToCall.value='displaycontrol';
document.forms[0].submit();	
}
function status()
{
             if(document.forms[0].ModifiedInPast.value ==-1 ||
                        document.forms[0].ModifiedInPast.value ==2 ||
                        document.forms[0].ModifiedInPast.value ==3 ||
                        document.forms[0].ModifiedInPast.value ==4)
                        {
                        document.forms[0].DisconnectedYear.disabled =false; 
			            }
             else
                        document.forms[0].DisconnectedYear.disabled =true;
                        document.forms[0].DisconnectedYear.value="";

}
function searchExist(){
	var url = "/eespc/PressListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, "_blank");
}

function cal()
{
	document.forms[0].Ink1voc.value=parseFloat(((document.forms[0].Ink1volume.value*document.forms[0].Ink1density.value))*(document.forms[0].Ink1vocpercent.value/100)).toFixed(2);
}

function cal1()
{
	document.forms[0].Ink2voc.value=parseFloat(((document.forms[0].Ink2volume.value*document.forms[0].Ink2density.value))*(document.forms[0].Ink2vocpercent.value/100)).toFixed(2);
}

function cal2()
{
	document.forms[0].Ink3voc.value=parseFloat(((document.forms[0].Ink3volume.value*document.forms[0].Ink3density.value))*(document.forms[0].Ink3vocpercent.value/100)).toFixed(2);
}

function cal3()
{
	document.forms[0].Ink4voc.value=parseFloat(((document.forms[0].Ink4volume.value*document.forms[0].Ink4density.value))*(document.forms[0].Ink4vocpercent.value/100)).toFixed(2);
}

function cal4()
{
	document.forms[0].FountainSolutionvoc.value=parseFloat(((document.forms[0].FountainSolutionvolume.value*document.forms[0].FountainSolutiondensity.value))*(document.forms[0].FountainSolutionvocpercent.value/100)).toFixed(2);
}
function cal5()
{
	document.forms[0].CleaningAgentvoc.value=parseFloat(((document.forms[0].CleaningAgentvolume.value*document.forms[0].CleaningAgentdensity.value))*(document.forms[0].CleaningAgentvocpercent.value/100)).toFixed(2);
}

function cal6()
{


	var a=parseFloat(document.forms[0].Ink1voc.value);
	var b=parseFloat(document.forms[0].Ink2voc.value);
	var c=parseFloat(document.forms[0].Ink3voc.value);
	var d=parseFloat(document.forms[0].Ink4voc.value);
	var e=parseFloat(document.forms[0].FountainSolutionvoc.value);
	var f=parseFloat(document.forms[0].CleaningAgentvoc.value);

	if(isNaN(a))
	{
		a=0;
	}
	if(isNaN(b))
	{
		b=0;
	}

	if(isNaN(c))
	{
		c=0;
	}

	if(isNaN(d))
	{
		d=0;
	}
	
	if(isNaN(e))
	{
		e=0;
	}

	if(isNaN(f))
	{
		f=0;
	}
	
	document.forms[0].totalvoc.value=((a+b+c+d+e+f)*12).toFixed(2);
}


function radioen()
{

	if(document.forms[0].Ink1.checked==false)
	{
		document.forms[0].Ink1name.disabled=true;
		document.forms[0].Ink1volume.disabled=true;
		document.forms[0].Ink1density.disabled=true;
		document.forms[0].Ink1vocpercent.disabled=true;
		document.forms[0].Ink1voc.disabled=true;
		
		document.forms[0].Ink1name.value="";
		document.forms[0].Ink1volume.value="";
		document.forms[0].Ink1density.value="";
		document.forms[0].Ink1vocpercent.value="";
		document.forms[0].Ink1voc.value="";
	}
	else
	{
		document.forms[0].Ink1name.disabled=false;
		document.forms[0].Ink1volume.disabled=false;
		document.forms[0].Ink1density.disabled=false;
		document.forms[0].Ink1vocpercent.disabled=false;
		document.forms[0].Ink1voc.disabled=false;
	}
}

function radioen1()
{

	if(document.forms[0].Ink2.checked==false)
	{
		document.forms[0].Ink2name.disabled=true;
		document.forms[0].Ink2volume.disabled=true;
		document.forms[0].Ink2density.disabled=true;
		document.forms[0].Ink2vocpercent.disabled=true;
		document.forms[0].Ink2voc.disabled=true;
		
		document.forms[0].Ink2name.value="";
		document.forms[0].Ink2volume.value="";
		document.forms[0].Ink2density.value="";
		document.forms[0].Ink2vocpercent.value="";
		document.forms[0].Ink2voc.value="";
	}
	else
	{
		document.forms[0].Ink2name.disabled=false;
		document.forms[0].Ink2volume.disabled=false;
		document.forms[0].Ink2density.disabled=false;
		document.forms[0].Ink2vocpercent.disabled=false;
		document.forms[0].Ink2voc.disabled=false;
	}
}


function radioen2()
{

	if(document.forms[0].Ink3.checked==false)
	{
		document.forms[0].Ink3name.disabled=true;
		document.forms[0].Ink3volume.disabled=true;
		document.forms[0].Ink3density.disabled=true;
		document.forms[0].Ink3vocpercent.disabled=true;
		document.forms[0].Ink3voc.disabled=true;
		
		document.forms[0].Ink3name.value="";
		document.forms[0].Ink3volume.value="";
		document.forms[0].Ink3density.value="";
		document.forms[0].Ink3vocpercent.value="";
		document.forms[0].Ink3voc.value="";
	}
	else
	{
		document.forms[0].Ink3name.disabled=false;
		document.forms[0].Ink3volume.disabled=false;
		document.forms[0].Ink3density.disabled=false;
		document.forms[0].Ink3vocpercent.disabled=false;
		document.forms[0].Ink3voc.disabled=false;
	}
}


function radioen3()
{

	if(document.forms[0].Ink4.checked==false)
	{
		document.forms[0].Ink4name.disabled=true;
		document.forms[0].Ink4volume.disabled=true;
		document.forms[0].Ink4density.disabled=true;
		document.forms[0].Ink4vocpercent.disabled=true;
		document.forms[0].Ink4voc.disabled=true;
		
		document.forms[0].Ink4name.value="";
		document.forms[0].Ink4volume.value="";
		document.forms[0].Ink4density.value="";
		document.forms[0].Ink4vocpercent.value="";
		document.forms[0].Ink4voc.value="";
	}
	else
	{
		document.forms[0].Ink4name.disabled=false;
		document.forms[0].Ink4volume.disabled=false;
		document.forms[0].Ink4density.disabled=false;
		document.forms[0].Ink4vocpercent.disabled=false;
		document.forms[0].Ink4voc.disabled=false;
	}
}


function radioen4()
{

	if(document.forms[0].FountainSolution.checked==false)
	{
		document.forms[0].FountainSolutionname.disabled=true;
		document.forms[0].FountainSolutionvolume.disabled=true;
		document.forms[0].FountainSolutiondensity.disabled=true;
		document.forms[0].FountainSolutionvocpercent.disabled=true;
		document.forms[0].FountainSolutionvoc.disabled=true;
		
		document.forms[0].FountainSolutionname.value="";
		document.forms[0].FountainSolutionvolume.value="";
		document.forms[0].FountainSolutiondensity.value="";
		document.forms[0].FountainSolutionvocpercent.value="";
		document.forms[0].FountainSolutionvoc.value="";
	}
	else
	{
		document.forms[0].FountainSolutionname.disabled=false;
		document.forms[0].FountainSolutionvolume.disabled=false;
		document.forms[0].FountainSolutiondensity.disabled=false;
		document.forms[0].FountainSolutionvocpercent.disabled=false;
		document.forms[0].FountainSolutionvoc.disabled=false;
	}
}


function radioen5()
{

	if(document.forms[0].CleaningAgent.checked==false)
	{
		document.forms[0].CleaningAgentname.disabled=true;
		document.forms[0].CleaningAgentvolume.disabled=true;
		document.forms[0].CleaningAgentdensity.disabled=true;
		document.forms[0].CleaningAgentvocpercent.disabled=true;
		document.forms[0].CleaningAgentvoc.disabled=true;
		
		document.forms[0].CleaningAgentname.value="";
		document.forms[0].CleaningAgentvolume.value="";
		document.forms[0].CleaningAgentdensity.value="";
		document.forms[0].CleaningAgentvocpercent.value="";
		document.forms[0].CleaningAgentvoc.value="";
	}
	else
	{
		document.forms[0].CleaningAgentname.disabled=false;
		document.forms[0].CleaningAgentvolume.disabled=false;
		document.forms[0].CleaningAgentdensity.disabled=false;
		document.forms[0].CleaningAgentvocpercent.disabled=false;
		document.forms[0].CleaningAgentvoc.disabled=false;
	}
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


<body>
<script src="tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Printing Press</SPAN> <br>		
	<br>			
<html:form action="/PressAction" enctype="multipart/form-data">
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input type="hidden" name="bctype"> 
<input type="hidden" name="inkdeleteConsumptionId"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
  <tr> 
    <td width="27%" nowrap class="label_right"><eespc:requiredField /> ID #  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />:</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
     <eespc:displayControl paramName="facilitydesinatedId" formName="PrintingPressForm">  
    	<html:text property="facilitydesinatedId" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>        
    (Facility Designated)</td>
  </tr> 	
  <tr> 
    <td class="label_right"><eespc:requiredField />Status of Source
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusOfSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
			<eespc:displayControl paramName="ModifiedInPast" formName="PrintingPressForm"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="PRESS_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
 											  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="PrintingPressForm"> 
			<html:text property="DisconnectedYear" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" styleClass="normal" /> 
		</eespc:displayControl>
	   </td>
  </tr>

 
  </table>
  <br>
  
  Process Method:</b></font><br>
<table border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" style="border-collapse: collapse" width="100%" >
  <tr>
    <td width="17%" align="center" bgcolor="#006699"><b><font size="2" color="#FFFFFF">Types</font></b></td>
    <td width="16%" align="center" bgcolor="#006699"><b><font size="2" color="#FFFFFF">Name</font></b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Name();"  onmouseout="UnTip()" /></td>
    <td width="16%" align="center" bgcolor="#006699"><b><font size="2" color="#FFFFFF">Volume gal/month</font></b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Volumegalmonth();"  onmouseout="UnTip()" /><b><font size="2" color="#FFFFFF"> </font></b></td>
    <td width="18%" align="center" bgcolor="#006699"><b><font size="2" color="#FFFFFF">Density lb/gal</font></b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Densitylbgal();"  onmouseout="UnTip()" /><b><font size="2" color="#FFFFFF"> </font></b></td>
    <td width="16%" align="center" bgcolor="#006699"><b><font size="2" color="#FFFFFF">VOC (wt %)</font></b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOCwt();"  onmouseout="UnTip()" /></td>
    <td width="37%" align="center" bgcolor="#006699"><b><font size="2" color="#FFFFFF">VOC (lb/month)</font></b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOClbmonth();"  onmouseout="UnTip()" /></td>
  </tr>

  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Ink1" formName="PrintingPressForm"> 
       <html:checkbox property="Ink1" value="Ink1" onclick="radioen();" styleClass="normal"/>Ink1
       </eespc:displayControl>
       <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ink1();"  onmouseout="UnTip()"/>
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Ink1name" formName="PrintingPressForm">
     <html:text property="Ink1name" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink1volume" formName="PrintingPressForm">
      <html:text property="Ink1volume" styleClass="normal"  onkeyup="this.value=numberWithDotCheck(this.value)"	onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink1density" formName="PrintingPressForm">
      <html:text property="Ink1density" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)"	onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink1vocpercent" formName="PrintingPressForm">
      <html:text property="Ink1vocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink1voc" formName="PrintingPressForm">
      <html:text property="Ink1voc" onfocus="cal()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
  
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Ink2" formName="PrintingPressForm"> 
       <html:checkbox property="Ink2" value="Ink2" onclick="radioen1();" styleClass="normal"/>Ink2
       </eespc:displayControl><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ink2();"  onmouseout="UnTip()" />    
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Ink2name" formName="PrintingPressForm">
     <html:text property="Ink2name" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink2volume" formName="PrintingPressForm">
      <html:text property="Ink2volume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>    
    </td>
    
    
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink2density" formName="PrintingPressForm">
      <html:text property="Ink2density" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)"	onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink2vocpercent" formName="PrintingPressForm">
      <html:text property="Ink2vocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink2voc" formName="PrintingPressForm">
      <html:text property="Ink2voc" onfocus="cal1()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
  
  
   <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Ink3" formName="PrintingPressForm"> 
       <html:checkbox property="Ink3" value="Ink3"  onclick="radioen2();" styleClass="normal"/>Ink3
       </eespc:displayControl>
       <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ink3();"  onmouseout="UnTip()" />
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Ink3name" formName="PrintingPressForm">
    <html:text property="Ink3name" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink3volume" formName="PrintingPressForm">
      <html:text property="Ink3volume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink3density" formName="PrintingPressForm">
      <html:text property="Ink3density" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink3vocpercent" formName="PrintingPressForm">
      <html:text property="Ink3vocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)"	onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink3voc" formName="PrintingPressForm">
      <html:text property="Ink3voc" onfocus="cal2()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
  
  
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Ink4" formName="PrintingPressForm"> 
       <html:checkbox property="Ink4" value="Ink4"  onclick="radioen3();" styleClass="normal"/>Ink4
       </eespc:displayControl><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ink4();"  onmouseout="UnTip()" />
   </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Ink4name" formName="PrintingPressForm">
    <html:text property="Ink4name" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink4volume" formName="PrintingPressForm">
     <html:text property="Ink4volume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink4density" formName="PrintingPressForm">
     <html:text property="Ink4density" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)"	onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink4vocpercent" formName="PrintingPressForm">
     <html:text property="Ink4vocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Ink4voc" formName="PrintingPressForm">
     <html:text property="Ink4voc" onfocus="cal3()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
  
  
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="FountainSolution" formName="PrintingPressForm"> 
       <html:checkbox property="FountainSolution" value="Other"  onclick="radioen4();" styleClass="normal"/>Fountain Solution
       </eespc:displayControl>
       <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FountainSolution();"  onmouseout="UnTip()" />
     </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="FountainSolutionname" formName="PrintingPressForm">
    <html:text property="FountainSolutionname" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="FountainSolutionvolume" formName="PrintingPressForm">
     <html:text property="FountainSolutionvolume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    

    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="FountainSolutiondensity" formName="PrintingPressForm">
     <html:text property="FountainSolutiondensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="FountainSolutionvocpercent" formName="PrintingPressForm">
     <html:text property="FountainSolutionvocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="FountainSolutionvoc" formName="PrintingPressForm">
      <html:text property="FountainSolutionvoc" onfocus="cal4()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
  
<tr>
    <td width="17%">     
       <eespc:displayControl paramName="CleaningAgent" formName="PrintingPressForm">
       <html:checkbox property="CleaningAgent" value="CleaningAgent"  onclick="radioen5();" styleClass="normal"/>Cleaning Agent
       </eespc:displayControl>
       <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="CleaningAgent();"  onmouseout="UnTip()" />		

    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="CleaningAgentname" formName="PrintingPressForm">
     <html:text property="CleaningAgentname" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="CleaningAgentvolume" formName="PrintingPressForm">
      <html:text property="CleaningAgentvolume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="CleaningAgentdensity" formName="PrintingPressForm">
     <html:text property="CleaningAgentdensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="CleaningAgentvocpercent" formName="PrintingPressForm">
     <html:text property="CleaningAgentvocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
    
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="CleaningAgentvoc" formName="PrintingPressForm">
     <html:text property="CleaningAgentvoc" onfocus="cal5()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    
    
  </tr>  
</table>


  <br>
  <table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
 <tr> 
    <td class="label_right" height="15" width="244" align="right">Total VOC  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TotalVOC();"  onmouseout="UnTip()" />:</td>
    
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="totalvoc" formName="PrintingPressForm"> 		
	 <html:text property="totalvoc" onfocus="cal6()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>		
	</eespc:displayControl>	lb/Yr		
	</td>
  </tr>

    <tr> 
    <td class="label_right" height="15" width="244" align="right">Is There a DEC Permit 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereaDECPermit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="decpermit" formName="PrintingPressForm"> 		
			<html:radio property="decpermit" value="Y" />Yes 
			<html:radio property="decpermit" value="N" />No 
		</eespc:displayControl>			
	</td>
  </tr>
  

   <tr> 
    <td class="label_right" height="15" width="244" align="right">Stack Exhaust Location<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhaustLocation();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="stackexhastlocation" formName="PrintingPressForm"> 		
			<html:text property="stackexhastlocation"  styleClass="normal" />	
		</eespc:displayControl>			
	</td>
  </tr>
    <tr> 
    <td class="label_right" height="15" width="244" align="right">Stack Exhaust Height<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhaustHeight();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="height" formName="PrintingPressForm"> 		
			<html:text property="height"  styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>	
		</eespc:displayControl>&nbsp;Ft			
	</td>
  </tr>
    <tr> 
    <td class="label_right" height="15" width="244" align="right">Stack Exhaust Diameter<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhaustDiameter();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="diameter" formName="PrintingPressForm"> 		
			<html:text property="diameter"  styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>	
		</eespc:displayControl>&nbsp;INCH			
	</td>
  </tr>
  
    <tr> 
    <td class="label_right" height="15" width="244" align="right">Stack Exhaust Velocity
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhaustVelocity();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="velocity" formName="PrintingPressForm"> 		
			<html:text property="velocity"  styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>	
		</eespc:displayControl>&nbsp;ACFM			
	</td>
  </tr>
  
   <tr> 
    <td class="label_right" height="15" width="244" align="right">Stack Test Required 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Stacktestrequired();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="stacktestrequired" formName="PrintingPressForm"> 		
			<html:radio property="stacktestrequired" value="Y" onclick="displayControl();" styleClass="normal" />Yes 
			<html:radio property="stacktestrequired" value="N" onclick="displayControl();" styleClass="normal" />No 
		</eespc:displayControl>			
	</td>
  </tr>
  
  <logic:equal name="PrintingPressForm" property="stacktestrequired" value="Y">
  <tr> 
    <td class="label_right" height="15" width="244" align="right">If Yes, Parameters : VOC</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="isvoc" formName="PrintingPressForm"> 		
			<html:checkbox property="isvoc" value="Y"/>	
		</eespc:displayControl>			
	</td>
  </tr>
    <tr> 
    <td class="label_right" height="15" width="244" align="right">&nbsp;PM</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="ispm" formName="PrintingPressForm"> 		
			<html:checkbox property="ispm" value="Y"/>	
		</eespc:displayControl>			
	</td>
  </tr>
   <tr> 
    <td class="label_right" height="15" width="244" align="right">Other</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="isother" formName="PrintingPressForm"> 		
			<html:text property="isother"  styleClass="normal" />	
	</eespc:displayControl>			
	</td>
  </tr>
     </logic:equal> 
     
  <tr> 
    <td class="label_right" height="15" width="244" align="right"><%=dep%> #
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEP();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="depnumber" formName="PrintingPressForm"> 		
			<html:text property="depnumber"  styleClass="normal" />	
		</eespc:displayControl>			
	</td>
  </tr>
  
   <tr> 
    <td class="label_right" height="15" width="244" align="right">Issue Date (<font color="#006699">mm/dd/yyyy</font>) 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Issuedate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="depissuedate" formName="PrintingPressForm"> 		
			<html:text property="depissuedate"  styleClass="normal" />	
		</eespc:displayControl>			
	</td>
  </tr>
  
   <tr> 
    <td class="label_right" height="15" width="244" align="right">Expiration Date(<font color="#006699">mm/dd/yyyy</font>) 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Expirationdate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="depexpirationdate" formName="PrintingPressForm"> 		
			<html:text property="depexpirationdate"  styleClass="normal" />	
		</eespc:displayControl>			
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="15" width="244" align="right"><%=dob%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="dobpermit" formName="PrintingPressForm"> 		
			<html:text property="dobpermit"  styleClass="normal" />	
		</eespc:displayControl>			
	</td>
  </tr>
  
   <tr> 
    <td class="label_right" height="15" width="244" align="right"><%=dob%> Sign Off
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td  class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="PrintingPressForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes
			<html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>	
  </tr>
  
    <tr> 
    <td class="label_right" height="15" width="244" align="right">Filter Used (Type)
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FilterusedType();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="filterused" formName="PrintingPressForm"> 		
			<html:text property="filterused"  styleClass="normal" />	
		</eespc:displayControl>			
	</td>
  </tr>
  
    <tr> 
    <td class="label_right" height="15" width="244" align="right">% Efficiency
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Efficiency();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="effiviancy" formName="PrintingPressForm"> 		
			<html:text property="effiviancy"  styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>	
		</eespc:displayControl>			
	</td>
  </tr>
  
    <tr> 
    <td class="label_right" height="15" width="244" align="right">Is There a Ink or Solvent Usage Cap  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Isthereainkorsolventusagecap();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="solvantcap" formName="PrintingPressForm"> 		
			<html:radio property="solvantcap" value="Y" onclick="displayControl();" styleClass="normal"/>Yes 
			<html:radio property="solvantcap" value="N" onclick="displayControl();" styleClass="normal"/>No 
		</eespc:displayControl>			
	</td>
  </tr>
  
   <logic:equal name="PrintingPressForm" property="solvantcap" value="Y">
  <tr> 
    <td class="label_right" height="15" width="244" align="right">If Yes, Enter Permitted Maximum VOC(12 Month Total)
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfyesannualVoc12monthtotal();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15" colspan="3" width="654">&nbsp;
    <eespc:displayControl paramName="annualconsumption" formName="PrintingPressForm"> 		
			<html:text property="annualconsumption"  styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" />	
		</eespc:displayControl>&nbsp;lb/yr		
	</td>
  </tr>  
  </logic:equal>
  
    <tr> 
    <td width="244" height="20" nowrap class="label_right" align="right">Comments  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td width="654"  nowrap colspan="3" class="fieldleft">&nbsp; 
     <eespc:displayControl paramName="Pcomments" formName="PrintingPressForm"> 
     <html:textarea property="Pcomments" cols="28" rows="2"/>     	
    </eespc:displayControl>        
    </td>
  </tr> 
  </table>
  
<br>

<logic:equal name="isComponentEditable" value="N">

<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />
<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("/eespc/PressAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("/eespc/PressAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
<br>


<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
    
	<jsp:include page="PrintingPressInk-1.jsp" />
	
	</div>
    </td>

  </tr>
</table>

	<br>
	
	<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
    
	<jsp:include page="PrintingPressInk-2.jsp" />
	
	</div>
    </td>

  </tr>
</table>
<br>	
	<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
    
	<jsp:include page="PrintingPressInk-3.jsp" />
	
	</div>
    </td>

  </tr>
</table>
<br>
<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
    
	<jsp:include page="PrintingPressInk-4.jsp" />
	
	</div>
    </td>

  </tr>
</table>
<br>

<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="PrintingFountainSolution.jsp" />
	</div>
    </td>
  </tr>
</table>    
    <br>
 <table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="PrintingCleaningAgent.jsp" />
	</div>
    </td>
  </tr>
</table>    
    <br>

<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">	
	<jsp:include page="PrintingVoc.jsp" />
	</div>
    </td>
</tr>
</table>
</logic:equal>

<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Building" 
            onClick="returnToBuilding();">
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
		onClick="<%= ((isEdit)? "addPress(true)" : "addPress(false)") %>;">			            
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
<b><font size="2" face="Verdana" color="#006699">Action: When Data for Sources are 
Input and Verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>     
</body>
</html>