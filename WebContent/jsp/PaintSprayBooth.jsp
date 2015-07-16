<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo,com.eespc.tracking.bo.myenum.SprayBoothChemicalsEnum" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>	
<title>New Paint Spray Booths</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js"></script>
<script src="/eespc/js/PSpray.js"></script>
<script src="/eespc/help/paintsprayboothhelp.js" ></script>
<script>
function cal()
{
	document.forms[0].spraypaintvoc.value=parseFloat(((document.forms[0].spraypaintvolume.value*document.forms[0].spraypaintdensity.value))*(document.forms[0].spraypaintvocpercent.value/100)).toFixed(2);
}

function cal1()
{
	document.forms[0].spraysolvantvoc.value=parseFloat(((document.forms[0].spraysolvantvolume.value*document.forms[0].spraysolvantdensity.value))*(document.forms[0].spraysolvantvocpercent.value/100)).toFixed(2);
}

function cal2()
{
	document.forms[0].sprayinkvoc.value=parseFloat(((document.forms[0].sprayinkvolume.value*document.forms[0].sprayinkdensity.value))*(document.forms[0].sprayinkvocpercent.value/100)).toFixed(2);
}

function cal3()
{
	document.forms[0].sprayothervoc.value=parseFloat(((document.forms[0].sprayothervolume.value*document.forms[0].sprayotherdensity.value))*(document.forms[0].sprayothervocpercent.value/100)).toFixed(2);
}


function disConnect()
{

if(document.forms[0].ps_status.value==-1 || document.forms[0].ps_status.value==2 || document.forms[0].ps_status.value==3 || document.forms[0].ps_status.value==4)
{
document.forms[0].ps_disconnecteddate.disabled=false;
}
else
{
document.forms[0].ps_disconnecteddate.value="";
document.forms[0].ps_disconnecteddate.disabled=true;
}
}

function searchExist(){
	var url = "/eespc/PaintListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}

function radioen()
{

if(document.forms[0].spraypaint.checked==false)
{
document.forms[0].spraypaintname.disabled=true;
document.forms[0].spraypaintvocpercent.disabled=true;
document.forms[0].spraypaintvolume.disabled=true;
document.forms[0].spraypaintdensity.disabled=true;
document.forms[0].spraypaintvoc.disabled=true;

document.forms[0].spraypaintname.value="";
document.forms[0].spraypaintvocpercent.value="";
document.forms[0].spraypaintvolume.value="";
document.forms[0].spraypaintdensity.value="";
document.forms[0].spraypaintvoc.value="";
}
else
{
document.forms[0].spraypaintname.disabled=false;
document.forms[0].spraypaintvocpercent.disabled=false;
document.forms[0].spraypaintvolume.disabled=false;
document.forms[0].spraypaintdensity.disabled=false;
document.forms[0].spraypaintvoc.disabled=false;

}
}

function radioen1()
{
if(document.forms[0].spraysolvant.checked==false)
{
document.forms[0].spraysolvantname.disabled=true;
document.forms[0].spraysolvantvocpercent.disabled=true;
document.forms[0].spraysolvantvolume.disabled=true;
document.forms[0].spraysolvantdensity.disabled=true;
document.forms[0].spraysolvantvoc.disabled=true;

document.forms[0].spraysolvantname.value="";
document.forms[0].spraysolvantvocpercent.value="";
document.forms[0].spraysolvantvolume.value="";
document.forms[0].spraysolvantdensity.value="";
document.forms[0].spraysolvantvoc.value="";
}
else
{
document.forms[0].spraysolvantname.disabled=false;
document.forms[0].spraysolvantvocpercent.disabled=false;
document.forms[0].spraysolvantvolume.disabled=false;
document.forms[0].spraysolvantdensity.disabled=false;
document.forms[0].spraysolvantvoc.disabled=false;

}
}

function radioen2()
{
if(document.forms[0].sprayink.checked==false)
{
document.forms[0].sprayinkname.disabled=true;
document.forms[0].sprayinkvocpercent.disabled=true;
document.forms[0].sprayinkvolume.disabled=true;
document.forms[0].sprayinkdensity.disabled=true;
document.forms[0].sprayinkvoc.disabled=true;

document.forms[0].sprayinkname.value="";
document.forms[0].sprayinkvocpercent.value="";
document.forms[0].sprayinkvolume.value="";
document.forms[0].sprayinkdensity.value="";
document.forms[0].sprayinkvoc.value="";
}
else
{
document.forms[0].sprayinkname.disabled=false;
document.forms[0].sprayinkvocpercent.disabled=false;
document.forms[0].sprayinkvolume.disabled=false;
document.forms[0].sprayinkdensity.disabled=false;
document.forms[0].sprayinkvoc.disabled=false;

}
}


function radioen3()
{
if(document.forms[0].sprayother.checked==false)
{
document.forms[0].sprayothername.disabled=true;
document.forms[0].sprayothervocpercent.disabled=true;
document.forms[0].sprayothervolume.disabled=true;
document.forms[0].sprayotherdensity.disabled=true;
document.forms[0].sprayothervoc.disabled=true;

document.forms[0].sprayothername.value="";
document.forms[0].sprayothervocpercent.value="";
document.forms[0].sprayothervolume.value="";
document.forms[0].sprayotherdensity.value="";
document.forms[0].sprayothervoc.value="";
}
else
{
document.forms[0].sprayothername.disabled=false;
document.forms[0].sprayothervocpercent.disabled=false;
document.forms[0].sprayothervolume.disabled=false;
document.forms[0].sprayotherdensity.disabled=false;
document.forms[0].sprayothervoc.disabled=false;

}
}


function cal5()
{


	var a=parseFloat(document.forms[0].spraypaintvoc.value);
	var b=parseFloat(document.forms[0].spraysolvantvoc.value);
	var c=parseFloat(document.forms[0].sprayinkvoc.value);
	var d=parseFloat(document.forms[0].sprayothervoc.value);

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

	
	document.forms[0].vocmonth.value=(a+b+c+d).toFixed(2);
}
function cal6()
{


	var a=parseFloat(document.forms[0].spraypaintvoc.value);
	var b=parseFloat(document.forms[0].spraysolvantvoc.value);
	var c=parseFloat(document.forms[0].sprayinkvoc.value);
	var d=parseFloat(document.forms[0].sprayothervoc.value);

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

	
	document.forms[0].vocyear.value=((a+b+c+d)*12).toFixed(2);
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


%>
</head>
<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Paint Spray Booth/Print Shop</SPAN> <br>		
	<br>
<html:form action="/PaintSprayAction" enctype="multipart/form-data">	
<input type="hidden" name="methodToCall" value="reset"> 

<input type="hidden" name="id">

<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
 <tr> 
    <td  class="label_right" nowrap> <eespc:requiredField />ID # 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
    <eespc:displayControl paramName="psId" formName="paintSprayForm"> 
        <html:text property="psId" styleClass="normal"/>
            <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>			
    (Facility Designated)</td>
  </tr>
  <tr> 
    <td class="label_right">Stack Exhausting 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" />: </td>
    <td width="57%"  nowrap class="fieldleft">&nbsp; 
    <eespc:displayControl paramName="P_fuelFromName" formName="paintSprayForm"> 
    	<html:hidden property="P_fuelFrom" /> 
    	<html:text property="P_fuelFromName" styleClass="normal" /> 
        <input type="button" name="Submit2" value="Search"  onClick="searchStack();"> 
    </eespc:displayControl>         
    </td>
  </tr>
 
  <tr> 
    <td class="label_right" nowrap> Floor 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()"/>:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
            <eespc:displayControl paramName="psFloor" formName="paintSprayForm">
                 <html:text property="psFloor" styleClass="normal"/>
            </eespc:displayControl>			
    </td>
  </tr>
  
  <tr> 
    <td class="label_right" nowrap>Year Installed 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()"/>: </td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="ps_yearInstalled" formName="paintSprayForm"> 
			<html:text property="ps_yearInstalled" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
   <tr> 
    <td class="label_right"><eespc:requiredField />Status of Source 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusOfSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
		<eespc:displayControl paramName="ps_status" formName="paintSprayForm"> 	
			<html:select property="ps_status" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="ps_disconnecteddate" formName="paintSprayForm"> 
			<html:text property="ps_disconnecteddate" styleClass="normal" size="10" maxlength="10" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	  </td>
  </tr>
   <tr> 
    <td class="label_right" nowrap> Make 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Make();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psMake" formName="paintSprayForm"> <html:text property="psMake" styleClass="normal"/>
    </eespc:displayControl></td>
  </tr>
  <tr> 
    <td class="label_right" nowrap> Model 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Model();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psModel" formName="paintSprayForm"> <html:text property="psModel" styleClass="normal"/>
    </eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap> Hours of Operation 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="HoursofOperation();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psHoursOfOperation" formName="paintSprayForm"><html:text property="psHoursOfOperation" styleClass="normal"/>
    </eespc:displayControl>
      <font color="#006699">Hrs/ Week</font> &nbsp;  
    <eespc:displayControl paramName="psHoursOfOperationyear" formName="paintSprayForm"><html:text property="psHoursOfOperationyear" styleClass="normal"/>
    </eespc:displayControl>
      <font color="#006699">Days/Year</font></td>
  </tr>
</table>
<font face="Arial"><b>Process Method:</b></font><br>
<table border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" style="border-collapse: collapse" width="100%" >
  <tr>
    <td width="17%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">Types</font></b></td>
    <td width="16%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF">Name<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Name();"  onmouseout="UnTip()" /></font></b></td>
    <td width="16%" align="center" bgcolor="#006699"><b>
    <font size="2" color="#FFFFFF">Volume gal/month<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Volumegalmonth();"  onmouseout="UnTip()" /> </font></b>

       
    </td>
    <td width="18%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">Density lb/gal<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Densitylbgal();"  onmouseout="UnTip()" /> </font></b>

       
    </td>
    <td width="16%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">VOC (wt %)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOCwt();"  onmouseout="UnTip()" /></font></b></td>
    <td width="37%" align="center" bgcolor="#006699">
	
	<b><font size="2" color="#FFFFFF">VOC(lb/month)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="VOClbmonth();"  onmouseout="UnTip()" /></font></b></td>
  </tr>

  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="spraypaint" formName="paintSprayForm"> 
       <html:checkbox property="spraypaint" value="Paint" onclick="radioen();" styleClass="normal"/>Paint</eespc:displayControl>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Paint();"  onmouseout="UnTip()" /></td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="spraypaintname" formName="paintSprayForm"> <html:text property="spraypaintname" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="spraypaintvolume" formName="paintSprayForm"> <html:text property="spraypaintvolume" styleClass="normal"  onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" 
			/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="spraypaintdensity" formName="paintSprayForm"> <html:text property="spraypaintdensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="spraypaintvocpercent" formName="paintSprayForm"> <html:text property="spraypaintvocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="spraypaintvoc" formName="paintSprayForm"> <html:text property="spraypaintvoc" onfocus="cal()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
  </tr>
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="spraysolvant" formName="paintSprayForm"> 
       <html:checkbox property="spraysolvant" value="Solvent" onclick="radioen1();" styleClass="normal"/>Solvent
       </eespc:displayControl><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Solvent();"  onmouseout="UnTip()" />
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="spraysolvantname" formName="paintSprayForm"> <html:text property="spraysolvantname" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="spraysolvantvolume" formName="paintSprayForm"> <html:text property="spraysolvantvolume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="spraysolvantdensity" formName="paintSprayForm"> <html:text property="spraysolvantdensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="spraysolvantvocpercent" formName="paintSprayForm"> <html:text property="spraysolvantvocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="spraysolvantvoc" formName="paintSprayForm"> <html:text property="spraysolvantvoc" onfocus="cal1()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
  </tr>
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="sprayink" formName="paintSprayForm"> 
       <html:checkbox property="sprayink" value="Ink "  onclick="radioen2();" styleClass="normal"/>Ink 
       </eespc:displayControl><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ink();"  onmouseout="UnTip()" />
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="sprayinkname" formName="paintSprayForm"> <html:text property="sprayinkname" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayinkvolume" formName="paintSprayForm"> <html:text property="sprayinkvolume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayinkdensity" formName="paintSprayForm"> <html:text property="sprayinkdensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayinkvocpercent" formName="paintSprayForm"> <html:text property="sprayinkvocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayinkvoc" formName="paintSprayForm"> <html:text property="sprayinkvoc" onfocus="cal2()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
  </tr>
  <tr>
    <td width="17%"> 
       <eespc:displayControl paramName="sprayother" formName="paintSprayForm"> 
       <html:checkbox property="sprayother" value="Other"  onclick="radioen3();" styleClass="normal"/>Other
       </eespc:displayControl><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Other();"  onmouseout="UnTip()" />
    </td>
    
    <td width="16%" align="center"> 
    <eespc:displayControl paramName="sprayothername" formName="paintSprayForm"> <html:text property="sprayothername" styleClass="normal"/>
    </eespc:displayControl>
    </td>
    
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayothervolume" formName="paintSprayForm"> <html:text property="sprayothervolume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="18%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayotherdensity" formName="paintSprayForm"> <html:text property="sprayotherdensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="16%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayothervocpercent" formName="paintSprayForm"> <html:text property="sprayothervocpercent" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
    <td width="37%" align="center">&nbsp;
     <eespc:displayControl paramName="sprayothervoc" formName="paintSprayForm"> <html:text property="sprayothervoc" onfocus="cal3()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl></td>
    </td>
  </tr>


</table>
<br>
  <table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">

 
  <tr> 
    <td width="25%" height="20" class="label_right">Total VOC Content/Month<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TotalVOCcontentMonth();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="vocmonth" formName="paintSprayForm"> 
    <html:text property="vocmonth" styleClass="normal" onfocus="cal5()" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
      </eespc:displayControl>&nbsp;lb/month
    </td>
  </tr>
   <tr> 
    <td width="25%" height="20" class="label_right">Total Annual VOC Content<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TotalannualVOCContent();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="vocyear" formName="paintSprayForm"> 
    <html:text property="vocyear" styleClass="normal" onfocus="cal6()" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
      </eespc:displayControl>&nbsp;lb/year
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap> Exempted by DEC 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ExemptedbyDEC();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
      <eespc:displayControl paramName="psExemptedbyDec" formName="paintSprayForm"> 
      <html:radio property="psExemptedbyDec"  value="Y"  styleClass="normal"/> Yes 
      <html:radio property="psExemptedbyDec"  value="N"  styleClass="normal"/> No
      </eespc:displayControl>
   </td>
  </tr>
  <tr> 
    <td height="35" class="label_right"> Was this Unit Included in the DEC Permit 
      Application as Exempt Source 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WasthisUnitincludedintheDECpermitapplicationasexemptsource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psIncludeByDec" formName="paintSprayForm"> 
       <html:radio property="psIncludeByDec" value="Y"  styleClass="normal"/> Yes 
       <html:radio property="psIncludeByDec" value="N"  styleClass="normal"/> No
    </eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap>Location 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Location();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psLocation" formName="paintSprayForm"> 
		<html:select property="psLocation" styleClass="normal">
			<html:optionsCollection name="AGENCY_LOCATION" property="dropDownValues" value="value" label="name"/> 
        	</html:select>		
    </eespc:displayControl>
      </td>
  </tr>
  <tr> 
    <td width="25%" height="20" class="label_right"><%=dep%> # 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPPermitInformation();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psDEPNumber" formName="paintSprayForm"> 
    <html:text property="psDEPNumber" styleClass="normal"/>
      </eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td width="25%" height="20" class="label_right"><%=dob%> # 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="dobnumber" formName="paintSprayForm"> 
    <html:text property="dobnumber" styleClass="normal"/>
      </eespc:displayControl>
    </td>
  </tr>
   <tr> 
    <td width="25%" height="20" class="label_right"><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="673">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="paintSprayForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes <html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
    <tr> 
    <td class="label_right" nowrap> Is There a Cap on the VOC Content 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereacapontheVOCContent();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
      <eespc:displayControl paramName="voccap" formName="paintSprayForm"> 
      <html:radio property="voccap"  value="Y"  styleClass="normal" onclick="displayControl();"/> Yes 
      <html:radio property="voccap"  value="N"  styleClass="normal" onclick="displayControl();"/> No
      </eespc:displayControl>
   </td>
  </tr>
    <logic:equal name="paintSprayForm" property="voccap" value="Y">
   <tr> 
    <td width="25%" height="20" class="label_right">If Yes (Rule Part 228 Limit),&nbsp; 
    Paint 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfYesRulePart228LimitPaint();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="spraypaintcap" formName="paintSprayForm"> 
    <html:text property="spraypaintcap" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
      </eespc:displayControl>&nbsp;lb/gal
    </td>
  </tr>
   <tr> 
    <td width="25%" height="20" class="label_right">Solvent<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Solventa();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="spraysolvantcap" formName="paintSprayForm"> 
    <html:text property="spraysolvantcap" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
      </eespc:displayControl>&nbsp;lb/gal
    </td>
  </tr>
   <tr> 
    <td width="25%" height="20" class="label_right">Ink  			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Inka();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="sprayinkcap" formName="paintSprayForm"> 
    <html:text property="sprayinkcap" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
      </eespc:displayControl>&nbsp;lb/gal
    </td>
  </tr>
   </logic:equal>  
   <tr> 
    <td class="label_right" nowrap> Is There a Total Monthly Quantity Limit 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Isthereatotalmonthlyquantitylimit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
      <eespc:displayControl paramName="vocmonthlyquantity" formName="paintSprayForm"> 
      <html:radio property="vocmonthlyquantity"  value="Y"  styleClass="normal" onclick="displayControl();"/> Yes 
      <html:radio property="vocmonthlyquantity"  value="N"  styleClass="normal" onclick="displayControl();"/> No
      </eespc:displayControl>
   </td>
  </tr>
   <logic:equal name="paintSprayForm" property="vocmonthlyquantity" value="Y">
   <tr> 
    <td width="25%" height="20" class="label_right">If Yes ,&nbsp; 
    Limit<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfLimit();"  onmouseout="UnTip()" />: </td>
     
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="vocmonthlylimit" formName="paintSprayForm"> 
    <html:text property="vocmonthlylimit" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
      </eespc:displayControl>&nbsp;gal/month
    </td>
  </tr>
   </logic:equal> 
 <tr> 
    <td class="label_right" nowrap valign="top"> Comments 			

 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
    <eespc:displayControl paramName="psActionTaken" formName="paintSprayForm"> 
			<html:textarea property="psActionTaken" cols="40" rows="3"/> 
		</eespc:displayControl>
   </td>
  </tr>
  

</table>
<logic:equal name="showAddBtn" value="Y">			
<DIV id= "PaintDiv" style= "{display:<%   
        String str = (String)request.getAttribute("PSPRAY_DEP"); 
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
             <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Issued Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Expiration Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Note</td>
                <td   class=columnhead>Edit</td> <td   class=columnhead>Delete</td>

              </tr>
		<%
		List inspectionList1	 = (List)request.getAttribute("PSPRAY_DEP_LST");
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
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "psDEPIssueDate", statePermitInfo.getIssueDateStr(),null));
			//out.println(statePermitInfo.getIssueDateStr());
			out.print("</td>");
                	out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "psDEPExpirationDate", statePermitInfo.getExpirationDateStr(),null));
			//out.println(statePermitInfo.getExpirationDateStr());
			out.print("</td>");	
			out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "psDEPExpirationDateNote", statePermitInfo.getNote(),null));
			//out.println(statePermitInfo.getNote());
			out.print("</td>");				
			out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/PaintSprayAction.do?methodToCall=depEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");

				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");		
			
	out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/PaintSprayAction.do?methodToCall=depDelete&hdnPermitId=");out.print(permitId);
				out.print("\"  onclick=\"return confirm('Do you want delete this??')\">");

				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\" ></a>");
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
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="psDEPIssueDate"  styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="psDEPExpirationDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="psDEPExpirationDateNote" styleClass="normal"/></td>
                <td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
              </tr>
	<%
		}
		else // if !toEditDepPermit loop
		  method = "depUpdate";
	%>

              <tr align="right" class="evenRowStyle"> 
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
<logic:equal name="showAddBtn" value="Y">			
<DIV id= "StateDiv" style= "{display:<%   
        String str = (String)request.getAttribute("PSPRAY_STATE"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %> }">
<br>
<table width="81%" border="0" cellspacing="0" cellpadding="0">
</table>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>State Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > 
    
    <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
      
        <TR BGCOLOR=white> 
          <TD> 
           <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Issued Date</td>
               
                <td   class=columnhead>Edit</td>   <td   class=columnhead>Delete</td>
              </tr>
		<%
		List inspectionList1 = null;
		inspectionList1	 = (List)request.getAttribute("PSPRAY_STATE_LST");
		boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_STATE_PERMIT"));
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
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "psStateIssueDate", statePermitInfo.getIssueDateStr(),null));
			//out.println(statePermitInfo.getIssueDateStr());
			out.print("</td>");
                			
			out.println("<td width=\"6%\" align=\"center\" nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/PaintSprayAction.do?methodToCall=stateEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");
				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");		
			
	out.println("<td width=\"6%\" align=\"center\" nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
						out.print("<a href=\"/eespc/PaintSprayAction.do?methodToCall=stateDelete&hdnPermitId=");out.print(permitId);
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
		    method = "state";    
		
		%>              
		

              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="psStateIssueDate" onblur="checkdate(this)" styleClass="normal"/></td>              
                <td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
              </tr>
		<%
			}
			else // if !toEditDepPermit loop
			  method = "stateUpdate";
		%>
              
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=3>
		<input type="button" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="<%= ((toEditDepPermit)? "addState(true)" : "addState(false)") %>;">
		</td>   
              </tr>        
              </table></TD>
        </TR>
        
      </TABLE>
      </TD>

  </TR>
</TABLE>
</DIV>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("PaintSprayAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("PaintSprayAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addspraybooth(true);" : "addspraybooth(false);") %>">			
      		</td>
            <td >&nbsp;
              <input type="reset" name="Submit22" value="Reset"></td>
			</logic:notEqual>
</tr>
    </table> 
<br>
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>

   </html:form>	     
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and Verified by Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>
</body>
</html>