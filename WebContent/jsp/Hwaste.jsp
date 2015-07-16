<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.FacilityVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>HWaste</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/help/hwastehelp.js" ></script>
<script src="/eespc/js/hwaste.js"></script>
</head>  

<script>
function cal()
{
	document.forms[0].wasteQuantity.value=parseFloat((document.forms[0].wasteVolume.value*document.forms[0].wasteDensity.value)).toFixed(2);
}

function cal1()
{
	document.forms[0].wasteQuantity1.value=parseFloat(document.forms[0].wasteVolume1.value*document.forms[0].wasteDensity1.value).toFixed(2);
}

function cal2()
{
	document.forms[0].wasteQuantity2.value=parseFloat((document.forms[0].wasteVolume2.value*document.forms[0].wasteDensity2.value)).toFixed(2);
}

function cal3()
{
	document.forms[0].wasteQuantity3.value=parseFloat((document.forms[0].wasteVolume3.value*document.forms[0].wasteDensity3.value)).toFixed(2);
}

function cal4()
{
	document.forms[0].wasteQuantity4.value=parseFloat((document.forms[0].wasteVolume4.value*document.forms[0].wasteDensity4.value)).toFixed(2);
}



function cal5()
{


	var a=parseFloat(document.forms[0].wasteQuantity.value);
	var b=parseFloat(document.forms[0].wasteQuantity1.value);
	var c=parseFloat(document.forms[0].wasteQuantity2.value);
	var d=parseFloat(document.forms[0].wasteQuantity3.value);
	var e=parseFloat(document.forms[0].wasteQuantity4.value);
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
	document.forms[0].hazardousTotalWaste.value=(a+b+c+d+e).toFixed(2);
}



function lbs()
	{
			if(document.forms[0].wasteUnit.value=="Lbs")
                        {
                        document.forms[0].wasteVolume.disabled =false;
                        document.forms[0].wasteDensity.disabled =false;
                        }
                        else
                        document.forms[0].wasteVolume.disabled =true;
                        document.forms[0].wasteDensity.disabled =true; 
                        document.forms[0].wasteVolume.value=""; 
                        document.forms[0].wasteDensity.value="";  
     }       	
function gallons()
	{
	
		   if(document.forms[0].wasteUnit.value=="Gallons/Liters")
                        {
                        document.forms[0].wasteVolume.disabled =true;
                        document.forms[0].wasteDensity.disabled =true; 
                        document.forms[0].wasteVolume.value=""; 
                        document.forms[0].wasteDensity.value="";
                        }
                        else                         
                        document.forms[0].wasteVolume.disabled =false;
                        document.forms[0].wasteDensity.disabled =false; 
	
	}
	
	function lbs1()
	{
			if(document.forms[0].wasteUnit1.value=="Lbs1")
                        {
                        document.forms[0].wasteVolume1.disabled =false;
                        document.forms[0].wasteDensity1.disabled =false;
                        }
                        else
                        document.forms[0].wasteVolume1.disabled =true;
                        document.forms[0].wasteDensity1.disabled =true; 
                        document.forms[0].wasteVolume1.value=""; 
                        document.forms[0].wasteDensity1.value="";  
     }       	
function gallons1()
	{
	
		   if(document.forms[0].wasteUnit1.value=="Gallons/Liters1")
                        {
                        document.forms[0].wasteVolume1.disabled =true;
                        document.forms[0].wasteDensity1.disabled =true; 
                        document.forms[0].wasteVolume1.value=""; 
                        document.forms[0].wasteDensity1.value="";
                        }
                        else                         
                        document.forms[0].wasteVolume1.disabled =false;
                        document.forms[0].wasteDensity1.disabled =false; 
	
	}
function lbs2()
	{
			if(document.forms[0].wasteUnit2.value=="Lbs2")
                        {
                        document.forms[0].wasteVolume2.disabled =false;
                        document.forms[0].wasteDensity2.disabled =false;
                        }
                        else
                        document.forms[0].wasteVolume2.disabled =true;
                        document.forms[0].wasteDensity2.disabled =true; 
                        document.forms[0].wasteVolume2.value=""; 
                        document.forms[0].wasteDensity2.value="";  
     }       	
function gallons2()
	{
	
		   if(document.forms[0].wasteUnit2.value=="Gallons/Liters2")
                        {
                        document.forms[0].wasteVolume2.disabled =true;
                        document.forms[0].wasteDensity2.disabled =true; 
                        document.forms[0].wasteVolume2.value=""; 
                        document.forms[0].wasteDensity2.value="";
                        }
                        else                                                
                        document.forms[0].wasteVolume2.disabled =false;
                        document.forms[0].wasteDensity2.disabled =false;
                        
	
	}
function lbs3()
	{
			if(document.forms[0].wasteUnit3.value=="Lbs3")
                        {
                        document.forms[0].wasteVolume3.disabled =false;
                        document.forms[0].wasteDensity3.disabled =false;
                        }
                        else
                        document.forms[0].wasteVolume3.disabled =true;
                        document.forms[0].wasteDensity3.disabled =true; 
                        document.forms[0].wasteVolume3.value=""; 
                        document.forms[0].wasteDensity3.value="";  
     }       	
function gallons3()
	{
	
		   if(document.forms[0].wasteUnit3.value=="Gallons/Liters3")
                        {
                        document.forms[0].wasteVolume3.disabled =true;
                        document.forms[0].wasteDensity3.disabled =true; 
                        document.forms[0].wasteVolume3.value=""; 
                        document.forms[0].wasteDensity3.value="";
                        }
                        else                         
                        document.forms[0].wasteVolume3.disabled =false;
                        document.forms[0].wasteDensity3.disabled =false; 
	
	}

function lbs4()
{
			if(document.forms[0].wasteUnit4.value=="Lbs4")
                        {
                        document.forms[0].wasteVolume4.disabled =false;
                        document.forms[0].wasteDensity4.disabled =false;
                        }
                        else
                        document.forms[0].wasteVolume4.disabled =true;
                        document.forms[0].wasteDensity4.disabled =true; 
                        document.forms[0].wasteVolume4.value=""; 
                        document.forms[0].wasteDensity4.value="";  
}    
   	
	function gallons4()
	{
	
		   if(document.forms[0].wasteUnit4.value=="Gallons/Liters4")
                        {
                        document.forms[0].wasteVolume4.disabled =true;
                        document.forms[0].wasteDensity4.disabled =true; 
                        document.forms[0].wasteVolume4.value=""; 
                        document.forms[0].wasteDensity4.value="";
                        }
                        else                         
                        document.forms[0].wasteVolume4.disabled =false;
                        document.forms[0].wasteDensity4.disabled =false; 	
	}

		   	   
	
</script>

<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Hazardous  Waste (HW) Generation and Disposal Summary:</SPAN> 		
<br>			
<html:form action="/HwasteAction">		
<input type="hidden" name="methodToCall" value="reset">


<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="125">

 <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right">
    <b>
    <eespc:requiredField/>Manifest Number<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="manifestno();"  onmouseout="UnTip()" />:
    </b>
    </td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp;
     
    <eespc:displayControl paramName="manifastno" formName="HwasteForm"> 
     
    	<html:text property="manifastno" styleClass="normal"/>
    	    	
    	&nbsp;</eespc:displayControl>
    	
    </td>
  </tr>
 
   <tr> 
    <td class="label_right" nowrap width="33%" height="31">    
   <p align="right">
   <b>
   <eespc:requiredField/>Date Of the Manifest (<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dateOfManifest();"  onmouseout="UnTip()" />:
   </b></td>   
   <td width="73%"  nowrap class="fieldleft" height="31">&nbsp;	   		
		<eespc:displayControl paramName="dateOftheManifest" formName="HwasteForm">		 		
			<html:text property="dateOftheManifest"  styleClass="normal"/>	
		&nbsp;</eespc:displayControl>	
	</td>
  </tr>
  
  <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right">
    <b>
   Facility/Generator EPA ID<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="hazardousEpaId();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
     <eespc:displayControl paramName="epaId" formName="HwasteForm">  
    	<html:text property="epaId" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>
  
   <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right">
    <b><eespc:requiredField/>Hazardous Waste Generation Category<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="hazardousCategory();"  onmouseout="UnTip()" />:</b></td>
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp;
		<eespc:displayControl paramName="hazardousWasteCategory" formName="HwasteForm"> 
			<html:select property="hazardousWasteCategory" styleClass="normal">
				<html:optionsCollection name="HAZARDOUS_WASTE_CATEGORY" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  
  </table>
   <br>
    <table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="93">
   <tr>
    <td width="8%" height="6" align="left" bgcolor="#006699">
    <font color="#FFFFFF"><b>Waste<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="wasteTitle();"  onmouseout="UnTip()" /></b></font></td>
    <td width="16%" height="6" align="center" bgcolor="#006699">
    <font color="#FFFFFF"><b>Type Of Waste<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="typeOfWasteTitle();"  onmouseout="UnTip()" /></b></font></td>
    <td width="20%" height="6" align="center" bgcolor="#006699">
    <font color="#FFFFFF"><b>Name Of Waste<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="wasteNameTitle();"  onmouseout="UnTip()" /></b></font></td>
    <td width="8%" height="6" align="center" bgcolor="#006699">
    <font color="#FFFFFF"><b>Waste Unit</b></font><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="wasteUnitTitle();"  onmouseout="UnTip()" /></b></font></td>
    <td width="13%" height="6" align="center" bgcolor="#006699">
    <font color="#FFFFFF"><b>Waste Volume in Gallons</b></font><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="wasteVolumeTitle();"  onmouseout="UnTip()" /></b></font></td>
    <td width="12%" height="6" align="center" bgcolor="#006699">
    <font color="#FFFFFF"><b>Waste Density</b></font><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="wasteDensityTitle();"  onmouseout="UnTip()"/></b></font></td>
    <td width="142%" height="6" align="center" bgcolor="#006699">
    <font color="#FFFFFF"><b>Waste Quantity(LBS)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="wasteQuantityTitle();"  onmouseout="UnTip()" /></b></font></td>
  </tr>
 
   <tr>
    <td width="8%" height="6" align="left">    
     <eespc:displayControl paramName="waste" formName="HwasteForm"> 
     <html:checkbox property="waste" value="waste" onclick="radioen();" styleClass="normal"/><b>Waste</b></eespc:displayControl>
     
     <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="waste();"  onmouseout="UnTip()" />		
    </td>    
    <td width="16%" height="6" align="center"><p align="center"> 
    <eespc:displayControl paramName="typeOfWaste" formName="HwasteForm">     		
			<html:select property="typeOfWaste" styleClass="normal" onchange="useChanged();">
				<html:optionsCollection name="WASTE_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>
    </eespc:displayControl>
    </td>	
    <td width="20%" height="6" align="center">  
    <eespc:displayControl paramName="wasteName" formName="HwasteForm">  
    <html:text property="wasteName" styleClass="normal"/>&nbsp;</eespc:displayControl>
    </td>   
    <td width="20%" height="6" align="center"><p align="center"> 
    <eespc:displayControl paramName="wasteUnit" formName="HwasteForm"> 		
    <html:radio property="wasteUnit" value="Lbs" onclick="lbs();"/>Pounds
    <html:radio property="wasteUnit" value="Gallons/Liters" onclick="gallons();"/>Gallons
    </eespc:displayControl>
    </td>    	        	
    <td width="13%" height="6" align="center">
    <eespc:displayControl paramName="wasteVolume" formName="HwasteForm">
    <html:text property="wasteVolume" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="12%" height="6" align="center">
    <eespc:displayControl paramName="wasteDensity" formName="HwasteForm">
    <html:text property="wasteDensity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>       	
    <td width="142%" height="6" align="center">
    <eespc:displayControl paramName="wasteQuantity" formName="HwasteForm">  
    <html:text property="wasteQuantity" onfocus="cal()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
     </tr>
     
      <tr>
	<td width="8%" height="6" align="left">    
     <eespc:displayControl paramName="waste1" formName="HwasteForm"> 
     <html:checkbox property="waste1" value="waste1" onclick="radioen();" styleClass="normal"/><b>Waste1</b></eespc:displayControl>
     <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="waste1();"  onmouseout="UnTip()" />		
    </td>        
    <td width="16%" height="5" align="center"> 
    <eespc:displayControl paramName="typeOfWaste1" formName="HwasteForm"> 		
	<html:select property="typeOfWaste1" styleClass="normal" onchange="useChanged();">
				<html:optionsCollection name="WASTE_TYPE1" property="dropDownValues" value="value" label="name" /> 
			</html:select>

	</eespc:displayControl>
	</td>	
    <td width="20%" height="5" align="center"> 
    <eespc:displayControl paramName="wasteName1" formName="HwasteForm">  
    <html:text property="wasteName1" styleClass="normal"/>
    </eespc:displayControl></td>    	
    <td width="20%" height="6" align="center"><p align="center"> 
    <eespc:displayControl paramName="wasteUnit1" formName="HwasteForm"> 		
    <html:radio property="wasteUnit1" value="Lbs1" onclick="lbs1();"/>Pounds
    <html:radio property="wasteUnit1" value="Gallons/Liters1" onclick="gallons1();"/>Gallons
    </eespc:displayControl>
    </td>        	
    <td width="13%" height="6" align="center">
    <eespc:displayControl paramName="wasteVolume1" formName="HwasteForm">
    <html:text property="wasteVolume1" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="12%" height="6" align="center">
    <eespc:displayControl paramName="wasteDensity1" formName="HwasteForm">
    <html:text property="wasteDensity1" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="142%" height="5" align="center">    
    <eespc:displayControl paramName="wasteQuantity1" formName="HwasteForm">  
    <html:text property="wasteQuantity1" onfocus="cal1()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
    <tr>
   <td width="8%" height="6" align="left">    
     <eespc:displayControl paramName="waste2" formName="HwasteForm"> 
     <html:checkbox property="waste2" value="waste2" onclick="radioen();" styleClass="normal"/><b>Waste2</b></eespc:displayControl>
     <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="waste2();"  onmouseout="UnTip()" />		
    </td>     
    <td width="16%" height="5" align="center">
    <eespc:displayControl paramName="typeOfWaste2" formName="HwasteForm"> 		
	<html:select property="typeOfWaste2" styleClass="normal" onchange="useChanged();">
				<html:optionsCollection name="WASTE_TYPE2" property="dropDownValues" value="value" label="name" /> 
			</html:select>
	</eespc:displayControl>
	</td>	
    <td width="20%" height="5" align="center"> 
    <eespc:displayControl paramName="wasteName2" formName="HwasteForm">
    <html:text property="wasteName2" styleClass="normal"/>
    </eespc:displayControl>
    </td>    
	<td width="20%" height="6" align="center"><p align="center"> 
    <eespc:displayControl paramName="wasteUnit2" formName="HwasteForm"> 		
    <html:radio property="wasteUnit2" value="Lbs2"  onclick="lbs2();"/>Pounds
    <html:radio property="wasteUnit2" value="Gallons/Liters2" onclick="gallons2();"/>Gallons
    </eespc:displayControl>
    </td>	        	
    <td width="13%" height="6" align="center">
    <eespc:displayControl paramName="wasteVolume2" formName="HwasteForm">
    <html:text property="wasteVolume2" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="12%" height="6" align="center">
    <eespc:displayControl paramName="wasteDensity2" formName="HwasteForm">
    <html:text property="wasteDensity2" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="142%" height="5" align="center">
    <eespc:displayControl paramName="wasteQuantity2" formName="HwasteForm">  
    <html:text property="wasteQuantity2" onfocus="cal2()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
 
   <tr>
  	 <td width="8%" height="6" align="left">    
     <eespc:displayControl paramName="waste3" formName="HwasteForm"> 
     <html:checkbox property="waste3" value="waste3" onclick="radioen();" styleClass="normal"/><b>Waste3</b></eespc:displayControl>
     <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="waste3();"  onmouseout="UnTip()" />		
    </td>    
    <td width="16%" height="1" align="center">
    <eespc:displayControl paramName="typeOfWaste3" formName="HwasteForm"> 		
	<html:select property="typeOfWaste3" styleClass="normal" onchange="useChanged();">
				<html:optionsCollection name="WASTE_TYPE3" property="dropDownValues" value="value" label="name" /> 
			</html:select>
	</eespc:displayControl>
	</td>	
    <td width="20%" height="1" align="center"> 
    <eespc:displayControl paramName="wasteName3" formName="HwasteForm">  
    <html:text property="wasteName3" styleClass="normal"/>
    </eespc:displayControl>
    </td>    
    <td width="20%" height="6" align="center"><p align="center"> 
    <eespc:displayControl paramName="wasteUnit3" formName="HwasteForm"> 		
    <html:radio property="wasteUnit3" value="Lbs3" onclick="lbs3();"/>Pounds
    <html:radio property="wasteUnit3" value="Gallons/Liters3"  onclick="gallons3();"/>Gallons
    </eespc:displayControl>
    </td>	      	
    <td width="13%" height="6" align="center">
    <eespc:displayControl paramName="wasteVolume3" formName="HwasteForm">
    <html:text property="wasteVolume3" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="12%" height="6" align="center">
    <eespc:displayControl paramName="wasteDensity3" formName="HwasteForm">
    <html:text property="wasteDensity3" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="142%" height="1" align="center">    
    <eespc:displayControl paramName="wasteQuantity3" formName="HwasteForm">  
    <html:text property="wasteQuantity3" onfocus="cal3()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>
  
   <tr>
	<td width="8%" height="6" align="left">    
     <eespc:displayControl paramName="waste4" formName="HwasteForm"> 
     <html:checkbox property="waste4" value="waste4" onclick="radioen();" styleClass="normal"/><b>Waste4</b></eespc:displayControl>
     <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="waste4();"  onmouseout="UnTip()" />		
    </td>         
     <td width="16%" height="5" align="center">
    <eespc:displayControl paramName="typeOfWaste4" formName="HwasteForm"> 		
    <html:select property="typeOfWaste4" styleClass="normal" onchange="useChanged();">
				<html:optionsCollection name="WASTE_TYPE4" property="dropDownValues" value="value" label="name" /> 
			</html:select>
		</eespc:displayControl>
	</td>	
    <td width="20%" height="5" align="center"> 
    <eespc:displayControl paramName="wasteName4" formName="HwasteForm">  
    <html:text property="wasteName4" styleClass="normal"/>
    </eespc:displayControl>
    </td>    	
     <td width="20%" height="6" align="center"><p align="center"> 
    <eespc:displayControl paramName="wasteUnit4" formName="HwasteForm"> 		
    <html:radio property="wasteUnit4" value="Lbs4" onclick="lbs4();"/>Pounds
    <html:radio property="wasteUnit4" value="Gallons/Liters4"  onclick="gallons4();"/>Gallons
    </eespc:displayControl>
    </td>
    <td width="13%" height="6" align="center">
    <eespc:displayControl paramName="wasteVolume4" formName="HwasteForm">
    <html:text property="wasteVolume4" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="12%" height="6" align="center">
    <eespc:displayControl paramName="wasteDensity4" formName="HwasteForm">
    <html:text property="wasteDensity4" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td>    	
    <td width="142%" height="5" align="center">
    <eespc:displayControl paramName="wasteQuantity4" formName="HwasteForm">  
   	<html:text property="wasteQuantity4" onfocus="cal4()" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>
    </td> 
  	</tr>    
     </table> 
   <br>
 <table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="125">
    <tr> 
    <td width="33%" nowrap class="label_right" height="31">    
    <p align="right"><b>Total Waste in LBS</b>
 	<b></b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TotalWaste();"  onmouseout="UnTip()" />:</td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
    <eespc:displayControl paramName="hazardousTotalWaste" formName="HwasteForm">    
     <html:text property="hazardousTotalWaste" styleClass="normal" onfocus="cal5()"   
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
    </eespc:displayControl>
    </td>
  </tr>  

<tr> 
    <td width="33%" nowrap class="label_right" height="31">    
    <p align="right"><b>Contact Person</b>
      	<b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="manifestContact();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
     <eespc:displayControl paramName="divisioncontactname" formName="HwasteForm">  
    	<html:text property="divisioncontactname" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>

  <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right"><b>Telephone Number</b>
      	<b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="telephoneNo();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
     <eespc:displayControl paramName="telephonenumber" formName="HwasteForm">  
    	<html:text property="telephonenumber" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>  

  
  <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right"><b>Name of the Company Collecting Waste/TSDF Facility</b><b>
 	<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="manifestcmpyname();"  onmouseout="UnTip()" />:</b></td>
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
     <eespc:displayControl paramName="companyName" formName="HwasteForm">  
    	<html:text property="companyName" styleClass="normal"/>
     </eespc:displayControl>
    </td>
  </tr>

  <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right"><b>EPA ID  of Waste Transporting Company</b><b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="epaWasteTransporting();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
     <eespc:displayControl paramName="epaidWaste" formName="HwasteForm">  
    	<html:text property="epaidWaste" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>
  
   <tr> 
    <td width="33%" nowrap class="label_right" height="31">
    <p align="right"><b>EPA ID  of Final Destination Facility</b> 
   	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="epaidFinaldes();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" height="31">&nbsp; 
     <eespc:displayControl  paramName="epaidFinaldes" formName="HwasteForm">  
    	<html:text property="epaidFinaldes" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>
  
  
</table>
<br>

<br><br>
</p>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<%
	FacilityVo facilityVo = (FacilityVo)session.getAttribute("FACILITY_OBJECT");
	StringBuffer tempBuffer = new StringBuffer();	
	tempBuffer.append("document.forms[0].action='/eespc/AddFacility.do?methodToCall=view&id=").append(facilityVo.getId()).append("';");
	tempBuffer.append("document.forms[0].submit();");
	
%>
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Facility" 
            onClick="<%= tempBuffer%>">
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
		onClick="<%= ((isEdit)? "addHwaste(true)" : "addHwaste(false)") %>;">			            
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