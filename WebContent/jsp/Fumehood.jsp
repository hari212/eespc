
<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo,com.eespc.tracking.bo.myenum.FumehoodChemicalsEnum" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New Fumehood</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/help/fumehoodhelp.js" ></script>
<script>

function disConnect()
{

if(document.forms[0].Fumehood_status.value==-1 || document.forms[0].Fumehood_status.value==2 || document.forms[0].Fumehood_status.value==3 || document.forms[0].Fumehood_status.value==4)
{
document.forms[0].Fumehood_disconnecteddate.disabled=false;
}
else
{
document.forms[0].Fumehood_disconnecteddate.value="";
document.forms[0].Fumehood_disconnecteddate.disabled=true;
}
}

function radioen()
{
if(document.forms[0].Fumehood_Chemical1.checked==false)
{
document.forms[0].Fumehood_Chemicalname1.disabled=true;
document.forms[0].Fumehood_volume1.disabled=true;
document.forms[0].Fumehood_density1.disabled=true;
document.forms[0].Fumehood_voc1.disabled=true;
document.forms[0].Fumehood_voct1.disabled=true;

document.forms[0].Fumehood_Chemicalname1.value="";
document.forms[0].Fumehood_volume1.value="";
document.forms[0].Fumehood_density1.value="";
document.forms[0].Fumehood_voc1.value="";
document.forms[0].Fumehood_voct1.value="";
}
else
{
document.forms[0].Fumehood_Chemicalname1.disabled=false;
document.forms[0].Fumehood_volume1.disabled=false;
document.forms[0].Fumehood_density1.disabled=false;
document.forms[0].Fumehood_voc1.disabled=false;
document.forms[0].Fumehood_voct1.disabled=false;

}
}

function radioen1()
{
if(document.forms[0].Fumehood_Chemical2.checked==false)
{
document.forms[0].Fumehood_Chemicalname2.disabled=true;
document.forms[0].Fumehood_volume2.disabled=true;
document.forms[0].Fumehood_density2.disabled=true;
document.forms[0].Fumehood_voc2.disabled=true;
document.forms[0].Fumehood_voct2.disabled=true;

document.forms[0].Fumehood_Chemicalname2.value="";
document.forms[0].Fumehood_volume2.value="";
document.forms[0].Fumehood_density2.value="";
document.forms[0].Fumehood_voc2.value="";
document.forms[0].Fumehood_voct2.value="";
}
else
{
document.forms[0].Fumehood_Chemicalname2.disabled=false;
document.forms[0].Fumehood_volume2.disabled=false;
document.forms[0].Fumehood_density2.disabled=false;
document.forms[0].Fumehood_voc2.disabled=false;
document.forms[0].Fumehood_voct2.disabled=false;

}
}

function radioen2()
{
if(document.forms[0].Fumehood_Chemical3.checked==false)
{
document.forms[0].Fumehood_Chemicalname3.disabled=true;
document.forms[0].Fumehood_volume3.disabled=true;
document.forms[0].Fumehood_density3.disabled=true;
document.forms[0].Fumehood_voc3.disabled=true;
document.forms[0].Fumehood_voct3.disabled=true;

document.forms[0].Fumehood_Chemicalname3.value="";
document.forms[0].Fumehood_volume3.value="";
document.forms[0].Fumehood_density3.value="";
document.forms[0].Fumehood_voc3.value="";
document.forms[0].Fumehood_voct3.value="";
}
else
{
document.forms[0].Fumehood_Chemicalname3.disabled=false;
document.forms[0].Fumehood_volume3.disabled=false;
document.forms[0].Fumehood_density3.disabled=false;
document.forms[0].Fumehood_voc3.disabled=false;
document.forms[0].Fumehood_voct3.disabled=false;

}
}

function radioen3()
{
if(document.forms[0].Fumehood_Chemical4.checked==false)
{
document.forms[0].Fumehood_Chemicalname4.disabled=true;
document.forms[0].Fumehood_volume4.disabled=true;
document.forms[0].Fumehood_density4.disabled=true;
document.forms[0].Fumehood_voc4.disabled=true;
document.forms[0].Fumehood_voct4.disabled=true;

document.forms[0].Fumehood_Chemicalname4.value="";
document.forms[0].Fumehood_volume4.value="";
document.forms[0].Fumehood_density4.value="";
document.forms[0].Fumehood_voc4.value="";
document.forms[0].Fumehood_voct4.value="";
}
else
{
document.forms[0].Fumehood_Chemicalname4.disabled=false;
document.forms[0].Fumehood_volume4.disabled=false;
document.forms[0].Fumehood_density4.disabled=false;
document.forms[0].Fumehood_voc4.disabled=false;
document.forms[0].Fumehood_voct4.disabled=false;

}
}

function radioen4()
{
if(document.forms[0].Fumehood_Chemical5.checked==false)
{
document.forms[0].Fumehood_Chemicalname5.disabled=true;
document.forms[0].Fumehood_volume5.disabled=true;
document.forms[0].Fumehood_density5.disabled=true;
document.forms[0].Fumehood_voc5.disabled=true;
document.forms[0].Fumehood_voct5.disabled=true;

document.forms[0].Fumehood_Chemicalname5.value="";
document.forms[0].Fumehood_volume5.value="";
document.forms[0].Fumehood_density5.value="";
document.forms[0].Fumehood_voc5.value="";
document.forms[0].Fumehood_voct5.value="";
}
else
{
document.forms[0].Fumehood_Chemicalname5.disabled=false;
document.forms[0].Fumehood_volume5.disabled=false;
document.forms[0].Fumehood_density5.disabled=false;
document.forms[0].Fumehood_voc5.disabled=false;
document.forms[0].Fumehood_voct5.disabled=false;

}
}

function searchExist(){
	var url = "/eespc/FumehoodListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}

</script>
<script src="/eespc/js/CommonScript.js"></script>
<script src="/eespc/js/Fumehood.js" ></script>
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
<SPAN CLASS=page_title>Fume Hood</SPAN><br>		
	<br>
	<html:form action="/FumehoodAction" enctype="multipart/form-data">
	<input type="hidden" name="id">
	<input type="hidden" name="methodToCall" value="reset">
	<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
    <input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>"> 
    
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="174">


 <tr> 
 
    <td width="25%" height="27" class="label_right"><eespc:requiredField />ID # 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="27">&nbsp; 
    <eespc:displayControl paramName="FumehoodId" formName="FumehoodForm"> 
    <html:text property="FumehoodId" styleClass="normal"/>
          <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>	
    (Facility Designated)</td>
  </tr>
 <tr> 
    <td class="label_right" height="27">Stack Exhausting 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" />: </td>
    <td width="57%"  nowrap class="fieldleft" height="27">&nbsp; 
    <eespc:displayControl paramName="Fumehood_fuelFromName" formName="FumehoodForm"> 
    	<html:hidden property="Fumehood_fuelFrom" /> 
    	<html:text property="Fumehood_fuelFromName" styleClass="normal" /> 
        <input type="button" name="Submit2" value="Search"  onClick="searchStack();"> 
    </eespc:displayControl>         
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="19"> Floor 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="19">&nbsp; 
            <eespc:displayControl paramName="FumehoodFloor" formName="FumehoodForm">
                 <html:text property="FumehoodFloor" styleClass="normal"/>
            </eespc:displayControl>			
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="19">Year Installed 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="19">&nbsp; 
		<eespc:displayControl paramName="Fumehood_yearInstalled" formName="FumehoodForm"> 
			<html:text property="Fumehood_yearInstalled" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	</td>
  </tr>

  <tr> 
    <td class="label_right" nowrap height="19"> Type 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Type();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="19">&nbsp; 
    <eespc:displayControl paramName="FumehoodMake" formName="FumehoodForm"> <html:text property="FumehoodMake" styleClass="normal"/>
    </eespc:displayControl></td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="19"> Used for /At 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UsedforAt();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="19">&nbsp; 
    <eespc:displayControl paramName="FumehoodModel" formName="FumehoodForm"> <html:text property="FumehoodModel" styleClass="normal"/>
    </eespc:displayControl>
    </td>
  </tr>
  
  
  <tr> 
    <td class="label_right" nowrap height="19"><eespc:requiredField />Operating Status 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OperatingStatus();"  onmouseout="UnTip()" />: </td>
    <td  height="19" class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="Fumehood_status" formName="FumehoodForm"> 	
			<html:select property="Fumehood_status" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>	 
		
 &nbsp; <strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="Fumehood_disconnecteddate" formName="FumehoodForm"> 
			<html:text property="Fumehood_disconnecteddate" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl> 										
	  </td>
  </tr>

  
  
  <tr> 
    <td class="label_right" nowrap height="18"> Hours of Operation 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="HoursofOperation();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="18">&nbsp; 
    <eespc:displayControl paramName="FumehoodHoursOfOperation" formName="FumehoodForm"><html:text property="FumehoodHoursOfOperation" styleClass="normal"/>
    </eespc:displayControl>
      / day
     &nbsp; 
	  		<eespc:displayControl paramName="FumehoodHoursOfOperation1" formName="FumehoodForm"> 
			<html:text property="FumehoodHoursOfOperation1" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>  days/week
      
      </td>
  </tr>
  
 </table>
&nbsp;<table border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" style="border-collapse: collapse" width="100%" >
  <tr>
    <td width="17%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">Types of chemicals Used</font></b><b><font size="2" color="#FFFFFF"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TypesofchemicalsUsed();"  onmouseout="UnTip()" /></font></b></td>
    <td width="16%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF">Chemical Name</font></b><b><font size="2" color="#FFFFFF"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ChemicalName();"  onmouseout="UnTip()" /></font></b></td>
    <td width="16%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF">Volume
	 gal/yr</font></b><b><font size="2" color="#FFFFFF"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Volumegalyr();"  onmouseout="UnTip()" /></font></b><b><font size="2" color="#FFFFFF"> </font></b>

       
    </td>
    <td width="18%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">Density lb/gal</font></b><b><font size="2" color="#FFFFFF"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Densitylbgal();"  onmouseout="UnTip()" /></font></b></td>
    <td width="16%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">VOC (wt %)</font></b><b><font size="2" color="#FFFFFF"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOCwt();"  onmouseout="UnTip()" /></font></b></td>
    <td width="37%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">VOC</font></b><b><font size="2" color="#FFFFFF"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOC();"  onmouseout="UnTip()" /></font></b></td>
  </tr>

  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Fumehood_Chemical1" formName="FumehoodForm"> 
       <html:checkbox property="Fumehood_Chemical1" value="Chemical1 " onclick="radioen();" styleClass="normal"/>Chemical1 
       </eespc:displayControl>
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Fumehood_Chemicalname1" formName="FumehoodForm"> <html:text property="Fumehood_Chemicalname1" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_volume1" formName="FumehoodForm"> <html:text property="Fumehood_volume1" styleClass="normal"   
			/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_density1" formName="FumehoodForm"> <html:text property="Fumehood_density1" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voc1" formName="FumehoodForm"> <html:text property="Fumehood_voc1" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voct1" formName="FumehoodForm"> <html:text property="Fumehood_voct1" onfocus="cal()" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
  </tr>
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Fumehood_Chemical2" formName="FumehoodForm"> 
       <html:checkbox property="Fumehood_Chemical2" value="Chemical2 " onclick="radioen1();" styleClass="normal"/>Chemical2 
       </eespc:displayControl>
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Fumehood_Chemicalname2" formName="FumehoodForm"> <html:text property="Fumehood_Chemicalname2" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_volume2" formName="FumehoodForm"> <html:text property="Fumehood_volume2" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_density2" formName="FumehoodForm"> <html:text property="Fumehood_density2" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voc2" formName="FumehoodForm"> <html:text property="Fumehood_voc2" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voct2" formName="FumehoodForm"> <html:text property="Fumehood_voct2" onfocus="cal1()" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
  </tr>
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Fumehood_Chemical3" formName="FumehoodForm"> 
       <html:checkbox property="Fumehood_Chemical3" value="Chemical3 "  onclick="radioen2();" styleClass="normal"/>Chemical3 
       </eespc:displayControl>
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Fumehood_Chemicalname3" formName="FumehoodForm"> <html:text property="Fumehood_Chemicalname3" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_volume3" formName="FumehoodForm"> <html:text property="Fumehood_volume3" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_density3" formName="FumehoodForm"> <html:text property="Fumehood_density3" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voc3" formName="FumehoodForm"> <html:text property="Fumehood_voc3" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voct3" formName="FumehoodForm"> <html:text property="Fumehood_voct3" onfocus="cal2()" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
  </tr>
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Fumehood_Chemical4" formName="FumehoodForm"> 
       <html:checkbox property="Fumehood_Chemical4" value="Chemical4 "  onclick="radioen3();" styleClass="normal"/>Chemical4 
       </eespc:displayControl>
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Fumehood_Chemicalname4" formName="FumehoodForm"> <html:text property="Fumehood_Chemicalname4" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_volume4" formName="FumehoodForm"> <html:text property="Fumehood_volume4" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_density4" formName="FumehoodForm"> <html:text property="Fumehood_density4" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voc4" formName="FumehoodForm"> <html:text property="Fumehood_voc4" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voct4" formName="FumehoodForm"> <html:text property="Fumehood_voct4" onfocus="cal3()" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
  </tr>
 <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="Fumehood_Chemical5" formName="FumehoodForm"> 
       <html:checkbox property="Fumehood_Chemical5" value="Chemical5 "  onclick="radioen4();" styleClass="normal"/>Chemical5 
       </eespc:displayControl>
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="Fumehood_Chemicalname5" formName="FumehoodForm"> <html:text property="Fumehood_Chemicalname5" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_volume5" formName="FumehoodForm"> <html:text property="Fumehood_volume5" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_density5" formName="FumehoodForm"> <html:text property="Fumehood_density5" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voc5" formName="FumehoodForm"> <html:text property="Fumehood_voc5" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="Fumehood_voct5" formName="FumehoodForm"> <html:text property="Fumehood_voct5" onfocus="cal4()" styleClass="normal"/>
    </eespc:displayControl></td>
    </td>
  </tr>

</table>

<br>

<table width="100%" style="border-collapse: collapse" bordercolor="#CCCCCC" cellpadding="0" cellspacing="0" border="1" >
   
     <tr> 
    <td class="label_right" width="292" align="right" > VOC 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666" >&nbsp; 
    <eespc:displayControl paramName="Fumehood_voc" formName="FumehoodForm">
     <html:text property="Fumehood_voc" styleClass="normal" onfocus="cal5()" size="4"  
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
    tons/yr</eespc:displayControl>
    </td>
  </tr>

 <tr> 
    <td class="label_right" width="292" align="right" > Exempted by DEC 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ExemptedbyDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666"  >&nbsp;
      <eespc:displayControl paramName="FumehoodExemptedbyDec" formName="FumehoodForm"> 
      <html:radio property="FumehoodExemptedbyDec"  value="Y"  styleClass="normal"/> Yes 
      <html:radio property="FumehoodExemptedbyDec"  value="N"  styleClass="normal"/> No
      </eespc:displayControl>
   </td>
  </tr>
  <tr> 
    <td height="35" class="label_right" width="292" align="right" > Was This unit Included in the DEC 
    Permit 
      Application as Exempt Source 
	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WasthisUnitincludedintheDECpermitapplicationasexemptsource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666"  >&nbsp; 
    <eespc:displayControl paramName="FumehoodIncludeByDec" formName="FumehoodForm"> 
       <html:radio property="FumehoodIncludeByDec" value="Y"  styleClass="normal"/> Yes 
       <html:radio property="FumehoodIncludeByDec" value="N"  styleClass="normal"/> No
    </eespc:displayControl>
    </td>
  </tr>
  
   <tr> 
    <td class="label_right" width="292" align="right"><%=dob%> Permit Status
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DobPermitStatus();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666">&nbsp; 				
		<eespc:displayControl paramName="dobPermitStatus" formName="FumehoodForm"> 		
			<html:radio property="dobPermitStatus" value="Y" onclick="displayControl();"/>Yes
      		<html:radio property="dobPermitStatus" value="N" onclick="displayControl();"/>No 
      		<html:radio property="dobPermitStatus" value="N/A" onclick="displayControl();"/>N/A
		</eespc:displayControl>	 	
	</td>
  </tr>
  
<logic:equal name="FumehoodForm" property="dobPermitStatus" value="Y"> 
  <tr> 
    <td class="label_right" width="292" align="right" ><%=dob%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666" >&nbsp; 
    <eespc:displayControl paramName="Fumehood_dob" formName="FumehoodForm">
    	<html:text property="Fumehood_dob" styleClass="normal"/>
    </eespc:displayControl>
     </td>
  </tr>

   <tr> 
    <td class="label_right" width="292" align="right"><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="FumehoodForm"> 		
			<html:radio property="dobsignoff" value="Y"/>Yes
			<html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  </logic:equal> 
  
     <tr> 
    <td class="label_right" width="292" align="right"><%=dep%> Permit Status
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DepPermitStatus();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666">&nbsp; 				
		<eespc:displayControl paramName="depPermitStatus" formName="FumehoodForm"> 		
			<html:radio property="depPermitStatus" value="Y" onclick="displayControl();"/>Yes
      		<html:radio property="depPermitStatus" value="N" onclick="displayControl();"/>No 
      		<html:radio property="depPermitStatus" value="N/A" onclick="displayControl();"/>N/A
		</eespc:displayControl>	 	
	</td>
  </tr>
   

  <logic:equal name="FumehoodForm" property="depPermitStatus" value="Y"> 	
   <tr> 
    <td class="label_right" width="292" align="right"><%=dep%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPStatus();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666">&nbsp;
    <eespc:displayControl paramName="FumehoodDEPNumber" formName="FumehoodForm"> 
    <html:text property="FumehoodDEPNumber" styleClass="normal"/> 
    </eespc:displayControl>		
    </td>
  </tr>
 </logic:equal>
 



   <tr> 
    <td class="label_right" width="292" align="right">Is Annual Inspection Conducted?
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DepPermitStatus();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666">&nbsp; 				
		<eespc:displayControl paramName="annualPermitStatus" formName="FumehoodForm"> 		
			<html:radio property="annualPermitStatus" value="Y" onclick="displayControl();"/>Yes
      		<html:radio property="annualPermitStatus" value="N" onclick="displayControl();"/>No 
      		<html:radio property="annualPermitStatus" value="N/A" onclick="displayControl();"/>N/A
		</eespc:displayControl>	 	
	</td>
  </tr>
  
 <logic:equal name="FumehoodForm" property="annualPermitStatus" value="Y">  
 <tr> 
    <td class="label_right" width="292" align="right">Last Inspection Date (<b><font color="#006699">mm/dd/yyyy</font></b>)
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="lastTestDate();"  onmouseout="UnTip()" /></td>    
    <td class="fieldleft"  width="666">&nbsp;
    	<eespc:displayControl paramName="lastInspectionDate" formName="FumehoodForm"> 
			<html:text property="lastInspectionDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>
	</td>
  </tr>
   
 <tr> 
    <td class="label_right" width="292" align="right">Next Inspection Date (<b><font color="#006699">mm/dd/yyyy</font></b>)
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="nextTestDate();"  onmouseout="UnTip()" /></td>    
    <td class="fieldleft"  width="666">&nbsp;
    	<eespc:displayControl paramName="nextInspectionDate" formName="FumehoodForm"> 
			<html:text property="nextInspectionDate" styleClass="normal" size="10" maxlength="10"/> 
		</eespc:displayControl>
	</td>
  </tr>
  </logic:equal> 	

   
  <tr> 
    <td class="label_right" width="292" align="right">Location<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Location();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft" width="666">&nbsp; 
    <eespc:displayControl paramName="FumehoodLocation" formName="FumehoodForm"> 
		<html:select property="FumehoodLocation" styleClass="normal">
			<html:optionsCollection name="AGENCY_LOCATION" property="dropDownValues" value="value" label="name"/> 
        </html:select>		
    </eespc:displayControl>
 	</td> 
 </tr>
 
 <tr> 
    <td width="292" class="label_right" valign="top" align="right">Comments<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft" width="666">&nbsp;
	<eespc:displayControl paramName="fcomments" formName="FumehoodForm"> 
		<html:textarea property="fcomments" cols="40" rows="3"/> 
	</eespc:displayControl>	
	</td>
 </tr>
 
 </table>


<logic:equal name="showAddBtn" value="Y">
 <logic:equal name="FumehoodForm" property="depPermitStatus" value="Y"> 			
<DIV id= "PaintDiv" style= "{display:<%   
        String str = (String)request.getAttribute("FUMEHOOD_DEP"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %> }">
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dep%> Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > 
    
      <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
             <table border="1" width="100%" cellspacing="0" cellpadding="2" class="globalTableStyle" style="border-collapse: collapse" bordercolor="#006699" height="60">
              <tr class="oddRowStyle">               
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note</td>                
                <td   class=columnhead width="24" height="19">Edit</td>
                <td   class=columnhead width="342" height="19">Delete</td>
              </tr>
		<%
		List inspectionList1	 = (List)request.getAttribute("FUMEHOOD_DEP_LST");
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
			//out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
			if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
			{
				isDepPermitEditable = true;
			}              		
              		
              		out.println("<tr class=\"evenRowStyle\">");
		           	out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "FumehoodDEPIssueDate", statePermitInfo.getIssueDateStr(),null));
			//out.println(statePermitInfo.getIssueDateStr());
			out.print("</td>");
                	out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "FumehoodDEPExpirationDate", statePermitInfo.getExpirationDateStr(),null));
			//out.println(statePermitInfo.getExpirationDateStr());
			out.print("</td>");	
			
					out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "FumehoodDEPExpirationDateNote", statePermitInfo.getNote(),null));
			//out.println(statePermitInfo.getNote());
			out.print("</td>");				
			out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/FumehoodAction.do?methodToCall=depEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");

				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");
						out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/FumehoodAction.do?methodToCall=depDelete&hdnPermitId=");out.print(permitId);
				out.print("\">");

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
               
                <td width="26%" align="center"  nowrap class="fieldleft" height="1"><html:text property="FumehoodDEPIssueDate"  styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft" height="1"><html:text property="FumehoodDEPExpirationDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft" height="1"><html:text property="FumehoodDEPExpirationDateNote" styleClass="normal"/></td>                
                <td width="6%" align="center"  nowrap class="fieldleft" colspan=2 height="1"></td>
              </tr>
	<%
		}
		else // if !toEditDepPermit loop
		  method = "depUpdate";
	%>

              <tr align="right" class="evenRowStyle" > 
                <td    colspan=5 >
              <input type="button" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="<%= ((toEditDepPermit)? "addDep(true)" : "addDep(false)") %>;">
              </td>   
              </tr>                    
              </table>
            </TD>
        </TR>
      </TABLE>
    </TD>
  </TR>
</TABLE>
</DIV>
</logic:equal>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("FumehoodAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("FumehoodAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addfumehood(true);" : "addfumehood(false);") %>">			
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
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and Verified by the Facility, The the  Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b></body></html>