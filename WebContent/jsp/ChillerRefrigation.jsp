<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.ElevatorPermitVo,com.eespc.tracking.bo.FacilityVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New Elevators</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/ChillerRefrigation.js" ></script>
<script src="/eespc/help/ChillerRefrigationhelp.js" ></script>
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

function displayControl()

{
document.forms[0].methodToCall.value='displaycontrol';
document.forms[0].submit();	
}

function searchExist(){
	var url = "/eespc/ChillerRefrigationListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, "_blank");
}

function crwstatus()
{
    if(document.forms[0].status.value ==-1 ||
                        document.forms[0].status.value ==2 ||
                        document.forms[0].status.value ==3 ||
                        document.forms[0].status.value ==4)
                        {
                        document.forms[0].disconnectedyear.disabled =false; 
			            }
             else
             {
                        document.forms[0].disconnectedyear.disabled =true;
                        document.forms[0].disconnectedyear.value="";
              }
  
}

function calcCapacity()
{
if (document.forms[0].capacitytons.value.length >0)
{

document.forms[0].capacitybtu.value=document.forms[0].capacitytons.value/1000;
 }
}



function calculateCapacity()
{
var ton=document.forms[0].capacitytons.value;
var ton2=(ton*12000);
document.forms[0].capacitybtu.value=ton2;
}


function calculateCapacityBtu()
{
var btu=document.forms[0].capacitybtu.value;
var btu2=(btu/12000);
document.forms[0].capacitytons.value=btu2;
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
<SPAN CLASS=page_title>Air-conditioning Unit</SPAN><br>		
	<br>			
<html:form action="/ChillerRefrigationAction" enctype="multipart/form-data">
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="344">
 <tr> 
    <td width="26%" nowrap class="label_right" height="27">
    <p align="right">
    <b>
    <eespc:requiredField /> ID # </b>	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" /><b>:</b></td>
    
    <td width="80%"  nowrap class="fieldleft" colspan="4" height="27">&nbsp; 
     <eespc:displayControl paramName="facilitydesignatedid" formName="ChillerRefrigationForm">  
    	<html:text property="facilitydesignatedid" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>        
    (Facility Designated)</td>
  </tr>
   <tr>
    <td width="43%" height="19" align="right"><b>Year Installed</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="year_installed();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="yearinstalled" formName="ChillerRefrigationForm"> 		
			<html:text property="yearinstalled"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  
   <tr>
    <td width="43%" height="19" align="right" valign="top"><b>
    <eespc:requiredField />Status 
    of Source</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="statusofsource();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="19%" height="19">&nbsp;
    <eespc:displayControl paramName="status" formName="ChillerRefrigationForm"> 
			<html:select property="status" styleClass="normal" onchange="crwstatus()">
				<html:optionsCollection name="CHILLERREFRIGATION_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>
			</eespc:displayControl>
			</td>
    <td width="16%" colspan="2" height="19">
    <p align="right"><b>Disconnected Year:</b></td>
    <td width="22%" height="19"> 
    <eespc:displayControl paramName="disconnectedyear" formName="ChillerRefrigationForm"> 		
			<html:text property="disconnectedyear"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>

  <tr>
    <td width="43%" height="19" align="right"><b>Make</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crmake();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="make" formName="ChillerRefrigationForm"> 		
			<html:text property="make"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  
   <tr>
    <td width="43%" height="19" align="right"><b>Model</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crmodel();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="model" formName="ChillerRefrigationForm"> 		
			<html:text property="model"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr> 
  
  
  <tr>
    <td width="43%" height="19" align="right"><b>Serial No.</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crserial();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="serialnum" formName="ChillerRefrigationForm"> 		
			<html:text property="serialnum"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr> 


 <tr>
    <td width="43%" height="19" align="right"><b>Area Served</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="areaServed();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="areaServed" formName="ChillerRefrigationForm"> 		
			<html:text property="areaServed"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr> 



<tr>
    <td width="43%" height="19" align="right"><b>Location</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crlocation();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="location" formName="ChillerRefrigationForm"> 		
			<html:select property="location" styleClass="normal" >
				<html:optionsCollection name="CHILLERREFRIGATION_LOCATION" property="dropDownValues" value="value" label="name" /> 
			</html:select>
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="43%" height="19" align="right"><b><eespc:requiredField />Capacity</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crcapacity();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="27%" colspan="2" height="19">&nbsp; 
    <eespc:displayControl paramName="capacitytons" formName="ChillerRefrigationForm"> 		
			<html:text property="capacitytons"  maxlength="25" styleClass="normal" onchange="calculateCapacity()"  onkeyup="calculateCapacity()" onfocus="calculateCapacityBtu()"/>	
		</eespc:displayControl>	<b>TONS</b></td>
		 
		
    <td width="30%" colspan="2" height="19">&nbsp;
    <eespc:displayControl paramName="capacitybtu" formName="ChillerRefrigationForm"> 		
			<html:text property="capacitybtu"  styleClass="normal" onchange="calculateCapacity()" onkeyup="calculateCapacityBtu()" onfocus="calculateCapacity()"/>	
		</eespc:displayControl><b>BTU/Hr</b></td>
  </tr>

  <tr> 
    <td width="43%" height="19" align="right"><b><%=dob%> Filing?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobfiling();"  onmouseout="UnTip()" />:</b></td>
   <td width="57%" colspan="4" height="19">&nbsp;				
		<eespc:displayControl paramName="dobfiling" formName="ChillerRefrigationForm"> 		
			<html:radio property="dobfiling" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="dobfiling" value="N" onclick="displayControl();"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  
 <logic:equal name="ChillerRefrigationForm" property="dobfiling" value="Y">    
<tr>
    <td width="43%" height="19" align="right"><b><%=dob%> Filing #</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobpermit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="dobpermit" formName="ChillerRefrigationForm"> 		
			<html:text property="dobpermit"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>

	
 <tr>
    <td width="43%" height="19" align="right"><b><%=dob%> Approval Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobissuedate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="dobissuedate" formName="ChillerRefrigationForm"> 		
			<html:text property="dobissuedate"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>	
  <tr> 
    <td width="43%" height="19" align="right"><b><%=dob%> Sign Off <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobsignoff();"  onmouseout="UnTip()" />:</b></td>
   <td width="57%" colspan="4" height="19">&nbsp;				
		<eespc:displayControl paramName="dobsignoff" formName="ChillerRefrigationForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes
			<html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  </logic:equal>
  
  
  <tr>
    <td width="43%" height="19" align="right"><b>Equipment Use Permit Card Available?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crequipmentusepermit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="equipmentusepermit" formName="ChillerRefrigationForm"> 		
			<html:radio property="equipmentusepermit" value="Y"/>Yes
			<html:radio property="equipmentusepermit" value="N"/>No 	
		</eespc:displayControl>	</td>
  </tr>
    
  <tr>
    <td width="43%" height="19" align="right"><b>Unit has the Fuel Firing Capability?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="fuelFiring();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="fuelFiring" formName="ChillerRefrigationForm"> 		
			<html:radio property="fuelFiring" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="fuelFiring" value="N" onclick="displayControl();"/>No 	
		</eespc:displayControl>	</td>
  </tr>

<logic:equal name="ChillerRefrigationForm" property="fuelFiring" value="Y">
  <tr>
    <td width="43%" height="19" align="right"><b>Type of Fuel Used</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="fuelFiring();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="fuelUsed" formName="ChillerRefrigationForm"> 
			<html:radio property="fuelUsed" value="Gas"/>Gas 	
			<html:radio property="fuelUsed" value="Oil"/>Oil
	</eespc:displayControl>	</td>
  </tr>
  
  <tr>
    <td width="43%" height="19" align="right"><b>DEP Permit Obtained?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="fuelFiring();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="depStatus" formName="ChillerRefrigationForm"> 		
			<html:radio property="depStatus" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="depStatus" value="N" onclick="displayControl();"/>No
			<html:radio property="depStatus" value="N/A" onclick="displayControl();"/>N/A 	 	
		</eespc:displayControl>	</td>
  </tr>
</logic:equal>

<logic:equal name="ChillerRefrigationForm" property="depStatus" value="Y">
<tr>
    <td width="43%" height="19" align="right"><b><%=dep%> Permit #</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobpermit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="deppermit" formName="ChillerRefrigationForm"> 		
			<html:text property="deppermit"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>

	
 <tr>
    <td width="43%" height="19" align="right"><b>Expiration Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobissuedate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="depexpirationdate" formName="ChillerRefrigationForm"> 		
			<html:text property="depexpirationdate"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>	
  </logic:equal>
  
   <tr>
    <td width="43%" height="19" align="right"><b>Is there a Refrigeration Recovery Involved?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="refrigerationrecovery();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;  
    <eespc:displayControl paramName="refrigerationrecovery" formName="ChillerRefrigationForm">   
	<html:radio property="refrigerationrecovery" value="Y" onclick="displayControl();"/>Yes
    <html:radio property="refrigerationrecovery" value="N" onclick="displayControl();"/>No			
		</eespc:displayControl></td>
  </tr>
  
  
  <logic:equal name="ChillerRefrigationForm" property="refrigerationrecovery" value="Y">
  <tr>
    <td width="43%" height="19" align="right"><b><eespc:requiredField />EPA Refrigeration Recovery Registration Status(Registered Or Not)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crepastatus();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="epastatus" formName="ChillerRefrigationForm">     
		<html:radio property="epastatus" value="Y"/>Yes
		<html:radio property="epastatus" value="N"/>No 			
	</eespc:displayControl>
	</td>
  </tr>
      
  <tr>
    <td width="43%" height="19" align="right"><b>EPA Submittal Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crepasubmittaldate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;
    <eespc:displayControl paramName="epasubmittaldate" formName="ChillerRefrigationForm"> 		
			<html:text property="epasubmittaldate"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr> 
   
   <tr>
    <td width="43%" height="19" align="right"><b>Records As Per EPA Maintained</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="epamaintained();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;
    <eespc:displayControl paramName="epamaintained" formName="ChillerRefrigationForm">    
		<html:radio property="epamaintained" value="Y"  />Yes
		<html:radio property="epamaintained" value="N"/>No 			
	</eespc:displayControl>
    </td>
  </tr>
  
 <tr>
    <td width="43%" height="19" align="right"><b>EPA Approval Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crepaapprovaldate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;
    <eespc:displayControl paramName="epaapprovaldate" formName="ChillerRefrigationForm"> 		
			<html:text property="epaapprovaldate"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr>
   </logic:equal>
   
  <tr> 
    <td class="label_right" nowrap width="43%">
    <p align="right"><b><eespc:requiredField/>FD Approval</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crfdaapproval();"  onmouseout="UnTip()" /><b>:</b></td>
   <td width="57%" colspan="4" height="19">&nbsp;		
		<eespc:displayControl paramName="fdaapproval" formName="ChillerRefrigationForm"> 		
			<html:radio property="fdaapproval" value="Y"/>Yes
			<html:radio property="fdaapproval" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
   
  <tr>
    <td width="43%" height="19" align="right"><b>Comments</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crcomments();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
      <eespc:displayControl paramName="comments" formName="ChillerRefrigationForm"> 
     <html:textarea property="comments" cols="28" rows="2"/>     	
    </eespc:displayControl>   	</td>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("ChillerRefrigationAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("ChillerRefrigationAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
		onClick="<%= ((isEdit)? "addChillerRefrigation(true)" : "addChillerRefrigation(false)") %>;">			            
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