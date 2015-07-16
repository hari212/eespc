<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eespc.tracking.bo.PermitInfoVo,com.eespc.tracking.bo.SpillProtectionVo,com.eespc.tracking.bo.TankTightnessVo,com.eespc.tracking.bo.TrienialCathodicVo,com.eespc.tracking.bo.FacilityVo" %> 
<%@ page import="com.eespc.tracking.util.UtilityObject" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New Storage Tank</title>
<html:base />
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/StorageTank.js" ></script>
<script src="/eespc/help/storagetankhelp.js" ></script>
<script>

function test() 
{ 
alert(document.forms[0].sTankLocation.value);
}



function status()

{
             if(document.forms[0].sTankOpStatus.value ==-1 ||
                        document.forms[0].sTankOpStatus.value ==2 ||
                        document.forms[0].sTankOpStatus.value ==3 ||
                               document.forms[0].sTankOpStatus.value ==4)
                        {
                         document.forms[0].s_disconnecteddate.disabled =false;

                       
			        
		           }
                 else
                       document.forms[0].s_disconnecteddate.disabled =true;
                       document.forms[0].s_disconnecteddate.value="";

}




function cathodicStatus()
{

             if(document.forms[0].sTankCorrosionProtection.value ==3)             	                       
                        {
                         document.forms[0].cathodicInstallationDate.disabled =false;        
			        
		           		}
                 else
                       document.forms[0].cathodicInstallationDate.disabled =true;
                       document.forms[0].cathodicInstallationDate.value="";

}


function searchExist(){
	var url = "/eespc/tankSearchListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=X";
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
<SPAN CLASS=page_title>Storage Tank</SPAN><br>		
	<br>
<html:form action="/StorageTankInfo" enctype="multipart/form-data"> 
<html:hidden property="methodToCall" />
<html:hidden property="id" />
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<table cellspacing="0" cellpadding="0" border="1" width="100%" style="border-collapse: collapse" bordercolor="#F2F2F2" height="723" >
  <tr> 
    <td align="right" height="33" ><b><eespc:requiredField/>ID #</b> 
     <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" /><b>: </b> </td>
    <td height="33" >&nbsp;
		<eespc:displayControl paramName="sTankId" formName="storageTankInfo"> 			
	 		<html:text property="sTankId" styleClass="normal" />
	 		<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
		</eespc:displayControl>				 
      (Facility Designated)</td>
  </tr>
  <tr>   
    <td align="right" height="27" ><b><eespc:requiredField />PBS # </b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PBS();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="27" >&nbsp; 
		<eespc:displayControl paramName="sPbs" formName="storageTankInfo"> 				
			<html:text property="sPbs" styleClass="normal" />
		</eespc:displayControl>				 			
	</td>	
  </tr>
   
   <tr>    
    <td align="right" height="27"><b><eespc:requiredField />PBS Expiration Date </b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PBSEXPIRATION();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="27" >&nbsp;  
		<eespc:displayControl paramName="sPbsexpirationdate" formName="storageTankInfo"> 			
				<html:text property="sPbsexpirationdate" styleClass="normal" />				
		</eespc:displayControl>			
	</td>	
  </tr>
  
  <tr>    
    <td align="right" height="27"><b>Note</b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PBSEXPIRATIONNOTE();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="27" >&nbsp;  
		<eespc:displayControl paramName="sPbsexpirationdateNote" formName="storageTankInfo"> 			
				<html:text property="sPbsexpirationdateNote" styleClass="normal" />				
		</eespc:displayControl>			
	</td>	
  </tr>
  
    
  <tr> 
    <td align="right" height="33"><b>Tank Installation Year</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Installed();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33"  >&nbsp; 
		<eespc:displayControl paramName="sYearInstalled" formName="storageTankInfo"> 		
			<html:text property="sYearInstalled" styleClass="normal"  size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
		</eespc:displayControl>			
	</td>
  </tr>
  
   
  
  <tr> 
    <td align="right" height="33"><b>Tank Type </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankType();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sTankLocation" formName="storageTankInfo"> 	
			<html:select property="sTankLocation" styleClass="normal">
				<html:optionsCollection name="TANK_LOCATION" property="dropDownValues" value="value" label="name" /> 
			</html:select>	
		</eespc:displayControl>	  										 
	   </td>
  </tr>
  
  <tr> 
    <td align="right" height="33" > <b>Capacity </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacity();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sCapacity" formName="storageTankInfo"> 					
			<html:text property="sCapacity" styleClass="normal" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
		</eespc:displayControl>				 				
      Gallons</td>
  </tr>
  
    <tr> 
    <td align="right" height="33"><b>Tank Material </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankMaterial();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sTankType" formName="storageTankInfo" > 	
			<html:select property="sTankType" styleClass="normal" >
				<html:optionsCollection name="TANK_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										

	   </td>
  </tr>
    

    <tr> 
    
    <td align="right" height="33"><b><eespc:requiredField />Tank Operating Status </b> 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankOperatingStatus();"  onmouseout="UnTip()" /><b>: </b> </td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sTankOpStatus" formName="storageTankInfo"> 	
			<html:select property="sTankOpStatus" styleClass="normal"  onchange="status();">
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
</eespc:displayControl>	  											  
	  &nbsp; <strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="s_disconnecteddate" formName="storageTankInfo"> 
			<html:text property="s_disconnecteddate" styleClass="normal" size="6" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
  										
	  </td>  
  </tr>
  
  
  
  
    <tr> 
    <td align="right" height="33"><b>Product Stored </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ProductStored();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sProductStored" formName="storageTankInfo"> 	
			<html:select property="sProductStored" styleClass="normal" >
				<html:optionsCollection name="PRODUCT_STORED" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	</td>
  </tr>
  
      <tr> 
    <td align="right" height="33"><b><eespc:requiredField />Corrosion Protection Type </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="CorresionProtectionType();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sTankCorrosionProtection" formName="storageTankInfo"> 	
			<html:select property="sTankCorrosionProtection" styleClass="normal" onchange="cathodicStatus();">
				<html:optionsCollection name="TANK_CORROSIONPROTECTION" property="dropDownValues" value="value" label="name"/> 
			</html:select>	
		</eespc:displayControl>	  										
	   </td>
  </tr>
  

   
   <tr>    
    <td align="right" height="27"><b>Date of Installation</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="CathodicInstallationDate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="27" >&nbsp;  
		<eespc:displayControl paramName="cathodicInstallationDate" formName="storageTankInfo"> 			
				<html:text property="cathodicInstallationDate" styleClass="normal" />				
		</eespc:displayControl>			
	</td>		
  </tr> 
  
  
  <tr> 
    <td align="right" height="33"><b>Is Tank Tightness Test Required?</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankTightness();"  onmouseout="UnTip()" /></td>
    <td height="33">
		<eespc:displayControl paramName="tankTightness" formName="storageTankInfo"> 		
			<html:radio property="tankTightness" value="Y" onclick="displayControl();"/>Yes 
			  <html:radio property="tankTightness" value="N" onclick="displayControl();"/>No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  

   <tr> 
    <td align="right" height="33"><b>Testing Company</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestingCompany();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 	
    <eespc:displayControl paramName="stestcompany" formName="storageTankInfo"> 		
			
			<html:text property="stestcompany" styleClass="normal" />
			</eespc:displayControl>
		</td>
  </tr>
  
  
  <tr> 
    <td align="right" height="15"><b>Fuel Supplied To</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FuelType();"  onmouseout="UnTip()"/><b>:</b></td>
    <td height="15">&nbsp; 
		<eespc:displayControl paramName="sFuelsuppliedto" formName="storageTankInfo"> 		
			<html:text property="sFuelsuppliedto" styleClass="normal" />
		</eespc:displayControl>			
	</td>
  </tr>
   


  <tr> 
    <td align="right" height="33"><b>Is It a Day Tank ?</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsitaDayTank();"  onmouseout="UnTip()" /></td>
    <td height="33">
		<eespc:displayControl paramName="sDayTank" formName="storageTankInfo"> 		
			<html:radio property="sDayTank" value="Y"/>Yes 
			<html:radio property="sDayTank" value="N"/>No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  
  
  
  <tr> 
    <td align="right" height="33"><b>Tank Secondary Containment </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SecondaryContainment();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sSecContainment" formName="storageTankInfo"> 	
			<html:select property="sSecContainment" styleClass="normal" >
				<html:optionsCollection name="SECONDARY_CONTAIN" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	</td>
  </tr>
  
  
    <tr> 
    <td align="right" height="33"><b>Tank Leak Detection</b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankLeakDetection();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sLeakDetection" formName="storageTankInfo"> 	
			<html:select property="sLeakDetection" styleClass="normal" >
				<html:optionsCollection name="TANK_LEAK_DETECTION" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	</td>
  </tr>


<tr> 
    <td align="right" height="33"><b>Tank Internal Protection </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PipeInternalProtection();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp;
		<eespc:displayControl paramName="sInternalProt" formName="storageTankInfo"> 	
			<html:select property="sInternalProt" styleClass="normal" >
				<html:optionsCollection name="PIPE_INTERNAL_PROTECT" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	  </td>
  </tr>
  
  
   <tr> 
    <td align="right" height="33"><b>Tank External Protection</b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankExternalProtection();"  onmouseout="UnTip()"/><b>:</b></td>
    <td height="33">&nbsp;
		<eespc:displayControl paramName="sTankExternalProt" formName="storageTankInfo"> 	
			<html:select property="sTankExternalProt" styleClass="normal" >
				<html:optionsCollection name="TANK_EXTERNAL_PROTECT" property="dropDownValues" value="value" label="name"/> 
			</html:select>		
		</eespc:displayControl>	  										
	  </td>
  </tr>
  
 <tr> 
    <td align="right" height="33"><b>Pipe Type</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PipeType();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sPipeType" formName="storageTankInfo"> 	
			<html:select property="sPipeType" styleClass="normal" >
				<html:optionsCollection name="PIPE_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	  </td>
  </tr>
  


  <tr> 
    <td align="right" height="33"><b>Pipe External Protection</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PipeExternalProtection();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33"> &nbsp; 
		<eespc:displayControl paramName="sExternalProt" formName="storageTankInfo"> 	
			<html:select property="sExternalProt" styleClass="normal" >
				<html:optionsCollection name="PIPE_EXTERNAL_PROTECT" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	</td>
  </tr>
  
    <tr> 
    <td align="right" height="33"><b>Piping Secondary Containment</b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PipesecondaryContainment();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33"> &nbsp; 
		<eespc:displayControl paramName="sPipingSecContainment" formName="storageTankInfo"> 	
			<html:select property="sPipingSecContainment" styleClass="normal" >
				<html:optionsCollection name="PIPE_SECONDARY_CONTAINMENT" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	</td>
  </tr>
  
  <tr> 
    <td align="right" height="33"><b>Overfill Protection </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OverfillProtection();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sOverFillProt" formName="storageTankInfo"> 	
			<html:select property="sOverFillProt" styleClass="normal" >
				<html:optionsCollection name="OVERFILL_PROTECT" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
     </td>
  </tr>
  
  
  <tr>     
    <td align="right" height="33"><b>Spill Prevention Method</b> 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SpillPrevention();"  onmouseout="UnTip()" /><b>: </b> </td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sSpillPrevent" formName="storageTankInfo"> 	
			<html:select property="sSpillPrevent" styleClass="normal">
				<html:optionsCollection name="SPILL_PREVENT_METHOD" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  											  	  										
	</td>  
  </tr>
  
  
     <tr> 
    <td align="right" height="33"><b>Dispenser</b> 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankDispenser();"  onmouseout="UnTip()"/><b>:</b></td>
    <td height="33">&nbsp;
		<eespc:displayControl paramName="sDispenser" formName="storageTankInfo"> 	
			<html:select property="sDispenser" styleClass="normal" >
				<html:optionsCollection name="TANK_DISPENSER" property="dropDownValues" value="value" label="name"/> 
			</html:select>		
		</eespc:displayControl>	  										
	  </td> 
  </tr>
  
  <tr> 
    <td align="right" height="33"><b><%=dob%> Filing?</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobfiling();"  onmouseout="UnTip()" />:</b></td>
    <td height="33">&nbsp; 				
		<eespc:displayControl paramName="dobfiling" formName="storageTankInfo"> 		
			<html:radio property="dobfiling" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="dobfiling" value="N" onclick="displayControl();"/>No
			<html:radio property="dobfiling" value="N/A" onclick="displayControl();"/>N/A 
		</eespc:displayControl>	 	
	</td>
  </tr>
  
  <logic:equal name="storageTankInfo" property="dobfiling" value="Y">	
  <tr> 
    <td align="right" height="33"><b><%=dob%> Filing/Job #</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBApproval();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sDobApp" formName="storageTankInfo"> 		
			<html:text property="sDobApp" styleClass="normal" />
		</eespc:displayControl>			
	</td>
  </tr>
   <tr> 
    <td align="right" height="33"><b><%=dob%></b><b> Sign Off <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</b></td>
    <td height="33">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="storageTankInfo"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes
			<html:radio property="dobsignoff" value="N"/>No
			
		</eespc:displayControl>	 	
	</td>
  </tr>
  
   <tr> 
    <td align="right" height="33"><b>Certificate of Approval(C of A)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="cofa();"  onmouseout="UnTip()" />:</b></td>
    <td height="33">&nbsp; 				
		<eespc:displayControl paramName="certificateofapproval" formName="storageTankInfo"> 		
			<html:radio property="certificateofapproval" value="Y"  />Yes
			<html:radio property="certificateofapproval" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>

</logic:equal>


  <tr> 
    <td align="right" height="33"><b>Fire Dept. Approval </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FireDeptcertificateofapproval();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sFireDepApp" formName="storageTankInfo"> 		
			<html:text property="sFireDepApp" styleClass="normal" />
		</eespc:displayControl>			
	</td>
  </tr>
  <tr> 
    <td align="right" height="33"><b>Are Tanks Registered With the Agency? <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Aretanksregisteredwiththeagency();"  onmouseout="UnTip()"/></td>
    <td height="33">
		<eespc:displayControl paramName="sTankReg" formName="storageTankInfo"> 		
		  <html:radio property="sTankReg" value="Y" />Yes 
		  <html:radio property="sTankReg" value="N" />No 
		</eespc:displayControl>			
	  </td>
  </tr>
  <tr>
    <td align="right" height="33"><b><eespc:requiredField />Regional Location </b> 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Location();"  onmouseout="UnTip()" /><b>: </b> </td>
    <td height="33">&nbsp;
		<eespc:displayControl paramName="sLocation" formName="storageTankInfo"> 	
			<html:select property="sLocation" styleClass="normal" >
				<html:optionsCollection name="AGENCY_LOCATION" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	  										
	</td>
  </tr>
  
  
      <tr> 
   
    <td align="right" height="27"><b>Site Location</b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="RegionalLocation();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="27" >&nbsp;  
		<eespc:displayControl paramName="regionalLocation" formName="storageTankInfo"> 			
				<html:text property="regionalLocation" styleClass="normal" />				
		</eespc:displayControl>			
	</td>		
	
  </tr>
  
 <tr> 
    <td align="right" valign="top" height="33"><b>Comments </b>
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" /><b>:</b></td>
    <td height="33">&nbsp; 
		<eespc:displayControl paramName="sComments" formName="storageTankInfo"> 		
			<html:textarea property="sComments" cols="30" rows="2" />
		</eespc:displayControl>			
	</td>
  </tr>
</table>	
<br>	

<logic:equal name="showAddBtn" value="Y">			
 <script>
		alert("If test is conducted recently or application for renewal submitted recently,import the estimated date for compliance assessment.");
</script>
<logic:equal name="storageTankInfo" property="tankTightness" value="Y">
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Tank Tightness Test Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TankTightnessTestInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD><TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Test Date <br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Next Test Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>              
                <td   class=columnhead>Note</td>
                <td   class=columnhead>Edit</td>				
                <td   class=columnhead>Delete</td>				
              </tr>
<%
			List tankTightnessList = (List)request.getAttribute("STORAGE_TANK_TIGHTNESS");
			boolean toEditTankTightness = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_STORAGE_TANK_TIGHTNESS"));							
			if (tankTightnessList != null){
				int size = tankTightnessList.size();
				for (int i=0; i < size; i++)
				{
					TankTightnessVo tankTightnessVo = (TankTightnessVo)tankTightnessList.get(i);
					boolean isTankTightnessEditable = false;
					int tankTightnessId = tankTightnessVo.getId();
					String tankTightnessIdStr = tankTightnessId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
//					out.println(tempStr + "==" + tankTightnessIdStr + ", " + toEditTankTightness);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(tankTightnessIdStr) && toEditTankTightness)
					{
						isTankTightnessEditable = true;
					}
					
						out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
//						out.println(tankTightnessVo.getTestDate4Display());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTightnessEditable,
						 "tTestDate", tankTightnessVo.getTestDate4Display(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTightnessEditable, 
						"tNextTestDate", tankTightnessVo.getNextTestDate4Display(),null));						
//						out.println(tankTightnessVo.getNextTestDate4Display());
						out.println("</td>");
						
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTightnessEditable, 
						"tNextTestDateNote", tankTightnessVo.getNextTestDateNote(),null));						
//						out.println(tankTightnessVo.getNextTestDateNote());
						out.println("</td>");
						

						
						

						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isTankTightnessEditable){
							out.println("<a href=\"javascript:editTankTightness('");
							out.println(tankTightnessId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");								
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isTankTightnessEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				             {
							out.println("<a href=\"javascript:deleteTankTightness('");
							out.println(tankTightnessId);
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
			if (!toEditTankTightness){ //show the following only if it is not edit				
%>						  
              <tr class="oddRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tTestDate" styleClass="normal"  size="10" maxlength="10"/>
				</td>
                <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tNextTestDate" styleClass="normal"  size="10" maxlength="10"/>
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tNextTestDateNote" styleClass="normal"  size="20"/>
                </td>
                
               
                <td   class="oddRowStyle" colspan=2 >&nbsp;</td>								
              </tr>
<%
			}// if !toEditTankTightness loop
%>
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=5>
			<input type="button" name="Button22" value="<%= ((toEditTankTightness)? "Update" : "Add") %>" 
			onClick="<%= ((toEditTankTightness)? "tankTightness(true)" : "tankTightness(false)") %>;">				
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
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Annual Cathodic Protection Test Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TrienialCathodicTestInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Last Test Date <br>(<font color="#006699">mm/dd/yyyy</font>)<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="LastTestDate();"  onmouseout="UnTip()" /></td>
                <td   class=columnhead>Next Test Due Date<br>(<font color="#006699">mm/dd/yyyy</font>)<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="NextTestDueDate();"  onmouseout="UnTip()" /></td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Actual Test Date<br>(<font color="#006699">mm/dd/yyyy</font>)<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="ActualTestDate();"  onmouseout="UnTip()" /></td>
                <td   class=columnhead>Edit</td>				
                <td   class=columnhead>Delete</td>				
                </tr>
<%
			List trienialCathodicProtectionTestList = (List)request.getAttribute("STORAGE_TANK_TRIENIALTEST");
			boolean toEditTankTrienial = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_STORAGE_TANK_TRIENIALTEST"));							
			if (trienialCathodicProtectionTestList != null){
				int size = trienialCathodicProtectionTestList.size();
				for (int i=0; i < size; i++)
				{
					TrienialCathodicVo trienialCathodicVo= (TrienialCathodicVo)trienialCathodicProtectionTestList.get(i);
					boolean isTankTrienialEditable = false;
					int trienialTestId = trienialCathodicVo.getId();
					String trienialTestIdStr = trienialTestId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
//					out.println(tempStr + "==" + trienialTestIdStr + ", " + toEditTankTrienial);
					if (null != tempStr && tempStr.equalsIgnoreCase(trienialTestIdStr) && toEditTankTrienial)
					{
						isTankTrienialEditable = true;
					}
					
						out.println("<tr class=\"evenRowStyle\">");
						
						out.println("<td nowrap class=\"fieldleft\">");											
//						out.println(trienialCathodicVo.getLastTestDate4Display());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTrienialEditable,
						 "tLastTestDate", trienialCathodicVo.getLastTestDate4Display(),null));
						out.println("</td>");
						
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTrienialEditable, 
						"tNextTestDueDate", trienialCathodicVo.getNextTestDueDate4Display(),null));						
//						out.println(trienialCathodicVo.getNextTestDueDate4Display());
						out.println("</td>");
						
						
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTrienialEditable, 
						"tNextTestDueDateNote", trienialCathodicVo.getNote(),null));						
//						out.println(trienialCathodicVo.getNote());
						out.println("</td>");
						
						
						out.println("<td nowrap class=\"fieldleft\">");						
//						out.println(trienialCathodicVo.getActualTestDate4Display());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTankTrienialEditable,
						 "tActualTestDate", trienialCathodicVo.getActualTestDate4Display(),null));
						out.println("</td>");

						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isTankTrienialEditable){
							out.println("<a href=\"javascript:editTrienialCathodicProtection('");
							out.println(trienialTestId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");								
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isTankTrienialEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				             {
							out.println("<a href=\"javascript:deleteTrienialCathodicProtection('"); 
							out.println(trienialTestId);
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
			if (!toEditTankTrienial){ //show the following only if it is not edit				
%>						  
              <tr class="oddRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tLastTestDate" styleClass="normal"  size="10" maxlength="10"/>
				</td>
                <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tNextTestDueDate" styleClass="normal"  size="10" maxlength="10"/>
                </td>
                 <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tNextTestDueDateNote" styleClass="normal"  size="20"/>
                </td>
                 <td width="26%" align="center"  nowrap class="fieldleft">
						<html:text property="tActualTestDate" styleClass="normal"  size="10" maxlength="10"/>
                </td>
                <td   class="oddRowStyle" colspan=2 >&nbsp;</td>								
              </tr>
<%
			}// if !toEditTankTrienial loop
%>
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=6 >
			<input type="button" name="Button22" value="<%= ((toEditTankTrienial)? "Update" : "Add")%>" 
			onClick="<%= ((toEditTankTrienial)? "tankTrienial(true)" : "tankTrienial(false)") %>;">				
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

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Spill Compliance Information    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="SpillComplianceInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead> Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Any Spill</td>
                <td   class=columnhead>Spill #</td>
                <td   class=columnhead>Compliance Status</td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>

              </tr>
<%
			List spillControlList = (List)request.getAttribute("STORAGE_TANK_SPILLCONTROL");
			boolean toEditSpillControl = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_SPILL_COMPLIANCE"));
			if (spillControlList != null){
				int size = spillControlList.size();
				for (int i=0; i < size; i++)
				{
					SpillProtectionVo spillControlVo = (SpillProtectionVo)spillControlList.get(i);
					boolean isSpillControlEditable = false;
					int spillControlId = spillControlVo.getId();
					String spillControlIdStr = spillControlId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
//					out.println(tempStr + "==" + spillControlIdStr + ", " + toEditSpillControl);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(spillControlIdStr) && toEditSpillControl)
					{
						isSpillControlEditable = true;
					}
					
						out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(spillControlVo.getDate4Display());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isSpillControlEditable, 
						"spillDate", spillControlVo.getDate4Display(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_RADIO, isSpillControlEditable,
						 "anySpill", UtilityObject.booleanToString(spillControlVo.isThereAnySpill()),null));						
						//out.println(UtilityObject.booleanToString(spillControlVo.isThereAnySpill()));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(spillControlVo.getSpillNumber());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isSpillControlEditable, 
						"spillNumber", spillControlVo.getSpillNumber(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_RADIO, isSpillControlEditable, 
						"spillCompliance", UtilityObject.booleanToString(spillControlVo.isCompliant()),null));						
						//out.println(UtilityObject.booleanToString(spillControlVo.isCompliant()));
						out.println("</td>");
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isSpillControlEditable){
							out.println("<a href=\"javascript:editSpillCompliance('");
							out.println(spillControlId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");								
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isSpillControlEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				{
							out.println("<a href=\"javascript:deleteSpillCompliance('");
							out.println(spillControlId);
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
			if (!toEditSpillControl){ //show the following only if it is not edit			
%>						  
              <tr class="oddRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="spillDate" styleClass="normal"  size="10" maxlength="10"/>
				</td>
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:radio property="anySpill" value="Y"  />Yes 
					<html:radio property="anySpill" value="N"  />No 
				  </td>
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="spillNumber" styleClass="normal" />
				</td>
                <td width="26%" align="center"  nowrap class="fieldleft">
				<html:radio property="spillCompliance" value="Y"  /> Yes 
			  	<html:radio property="spillCompliance" value="N"  /> No 
				</td>
                <td   class="oddRowStyle" colspan=2>&nbsp;</td>												
              </tr>
<%
			}// if !toEditSpillControl loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=6 >
<input type="button" name="Button22" value="<%= ((toEditSpillControl)? "Update" : "Add") %>" 
onClick="<%= ((toEditSpillControl)? "addSpillCompliance(true)" : "addSpillCompliance(false)") %>;">                
               
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
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>State Agency Permit Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StateAgencyPermitInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Edit</td>
                 <td   class=columnhead>Delete</td>
              </tr>
<%
			List statePermitList = (List)request.getAttribute("STORAGE_TANK_STATE_PERMIT");
			boolean toEditStatePermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_STATE_PERMIT"));
			if (statePermitList != null){
				int size = statePermitList.size();
				for (int i=0; i < size; i++)
				{
					PermitInfoVo statePermitVo = (PermitInfoVo)statePermitList.get(i);
					boolean isStatePermitEditable = false;
					int statePermitId = statePermitVo.getId();
					String statePermitIdStr = statePermitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
//					out.println(tempStr + "==" + statePermitIdStr + ", " + toEditStatePermit);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(statePermitIdStr) && toEditStatePermit)
					{
						isStatePermitEditable = true;
					}
					
						out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
//						out.println(statePermitVo.getIssueDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isStatePermitEditable, "statePermitIssueDate", 
						statePermitVo.getIssueDateStr(),null));						
						out.println("</td>");
						
						out.println("<td nowrap class=\"fieldleft\">");
//						out.println(statePermitVo.getExpirationDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isStatePermitEditable, "statePermitExpireDate", 
						statePermitVo.getExpirationDateStr(),null));						
						out.println("</td>");
						
						out.println("<td nowrap class=\"fieldleft\">");						
//						out.println(statePermitVo.getNote());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isStatePermitEditable, "statePermitExpireDateNote",						
						statePermitVo.getNote(),null));						
						out.println("</td>");

						
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isStatePermitEditable){
							out.println("<a href=\"javascript:editStatePermit('");
							out.println(statePermitId);
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
							out.println(statePermitId);
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
			  
              <tr class="oddRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="statePermitIssueDate" styleClass="normal"  size="10" maxlength="10"/>
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="statePermitExpireDate" styleClass="normal"  size="10" maxlength="10"/>
                </td>
                 <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="statePermitExpireDateNote" styleClass="normal"  size="20"/>
                </td>
                
                <td   class="oddRowStyle" colspan=2>&nbsp;</td>	
              </tr>
<%
			}// if !toEditStatePermit loop
%>              
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=5>
                <input type="button" name="Button22" value="<%= ((toEditStatePermit)? "Update" : "Add") %>" 
                onClick="<%= ((toEditStatePermit)? "addSatePermit(true)" : "addSatePermit(false)") %>;">
                </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
 </logic:equal>
<logic:equal name="showLocationPermit" value="Y" scope="request">
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><bean:write name="agencyLabel" /> Permit Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="NewYorkCityPermitInformation();"  onmouseout="UnTip()" /> 
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
			List locationPermitList = (List)request.getAttribute("STORAGE_TANK_LOCATION_PERMIT");
			boolean toEditLocationPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_LOCATION_PERMIT"));
			if (locationPermitList != null){
				int size = locationPermitList.size();
				for (int i=0; i < size; i++)
				{
					PermitInfoVo locationPermitVo = (PermitInfoVo)locationPermitList.get(i);
					boolean isLocationPermitEditable = false;
					int locationPermitId = locationPermitVo.getId();
					String locationPermitIdStr = locationPermitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
				//	out.println(tempStr + "==" + locationPermitIdStr + ", " + toEditLocationPermit);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(locationPermitIdStr) && toEditLocationPermit)
					{
						isLocationPermitEditable = true;
					}
					
						out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
//						out.println(locationPermitVo.getIssueDateStr());
out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isLocationPermitEditable, "locationPermitIssueDate",
 locationPermitVo.getIssueDateStr(),null));						
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
//						out.println(locationPermitVo.getExpirationDateStr());
out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isLocationPermitEditable, "locationPermitExpireDate",
 locationPermitVo.getExpirationDateStr(),null));						
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
//						out.println(locationPermitVo.getNote());
out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isLocationPermitEditable, "locationPermitExpireDateNote",
 locationPermitVo.getNote(),null));						
						out.println("</td>");
						
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isLocationPermitEditable){
							out.println("<a href=\"javascript:editLocationPermit('");
							out.println(locationPermitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");								
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isLocationPermitEditable){
						 if(session.getAttribute("DELETE_PERMISSION")!=null)
				{
							out.println("<a href=\"javascript:deleteLocationPermit('");
							out.println(locationPermitId);
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
			if (!toEditLocationPermit){ //show the following only if it is not edit
%>		
		<%//=toEditLocationPermit%> 		  
              <tr class="oddRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="locationPermitIssueDate" styleClass="normal"  size="10" maxlength="10"/>
				</td>
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="locationPermitExpireDate" styleClass="normal"  size="10" maxlength="10"/>
                </td>                 
				<td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="locationPermitExpireDateNote" styleClass="normal"  size="20"/>
                </td>                    
                
                <td   class="oddRowStyle" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditLocationPermit loop
			
%>
              
             <tr align="right" class="evenRowStyle"> 
                <td    colspan=5>
			<input type="button" name="Button22" value="<%= ((toEditLocationPermit)? "Update" : "Add") %>" 
			onClick="<%= ((toEditLocationPermit)? "addLocationPermit(true)" : "addLocationPermit(false)") %>;">                
                </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%String file_path=String.valueOf(request.getAttribute("FILE_PATH")).replaceAll("&", "%26");                           out.println("/eespc/StorageTankInfo.do?methodToCall=back&pathname="+file_path);                   %>">
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
				//out.println("qweqeqwe"+arr[0]+arr[3]);
  %>
  
    <tr>
    <td width="20%" align="left"><b><font color="#FF3399" size="2">
    <a href="<%if(arr[0].equals("folder"))    {    out.println("/eespc/StorageTankInfo.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("/eespc/clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
		onClick="<%= ((isEdit)? "addStorage(true)" : "addStorage(false)") %>;">			            
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
<b><font size="2" face="Verdana" color="#006699">Action:When Data for all Sources are 
Input and Verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>				
</body>
</html>