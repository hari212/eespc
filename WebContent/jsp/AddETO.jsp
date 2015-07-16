<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.EtoTestInfoVo,com.eespc.tracking.bo.PermitInfoVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>ETO</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Eto.js" ></script>
<script src="/eespc/help/Etohelp.js" ></script>
<script>
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
	var url = "/eespc/EtoListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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
<html:form action="/Eto" enctype="multipart/form-data"> 
<html:hidden property="methodToCall" />
<html:hidden property="id" />
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

<SPAN CLASS=page_title>ETO (Ethylene Oxide Sterilizer)</SPAN> <br>		
	<br>			
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#EEEEEE" height="487">
  <tr> 
    <td nowrap class="label_right" height="26"><eespc:requiredField />ID 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="26">&nbsp; 
    <eespc:displayControl paramName="facilityDesignatedId" formName="etoInfo"> 
      <html:text property="facilityDesignatedId" styleClass="normal" /> 
      <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl> 
      (Facility Designated)</td>
  </tr>
  <tr> 
    <td class="label_right" height="26">Stack Exhausting 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackExhausting();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="26">&nbsp; 
    <eespc:displayControl paramName="stackName" formName="etoInfo"> 
      <html:hidden property="stackId" /> <html:text property="stackName" styleClass="normal" /> 
      <input type="button" name="Submit23" value="Search" onClick="searchStack();">
      </eespc:displayControl> </td>
  </tr>
  <tr> 
    <td width="25%" nowrap class="label_right" height="15">Manufacturer 
      
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Manufacturer();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="manufacturer" formName="etoInfo"> 
      <html:text property="manufacturer" styleClass="normal" /> 
    </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Model 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Model();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="model" formName="etoInfo"> 
      <html:text property="model" styleClass="normal" /> </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15"> Serial # 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serial();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="serial" formName="etoInfo"> 
      <html:text property="serial" styleClass="normal" /> 
    </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Volume 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Volume();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="volume" formName="etoInfo"> 
      <html:text property="volume" styleClass="normal" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/> 
    </eespc:displayControl> 
      (cubic ft.)</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Gas Mixture Type 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="GasMixtureType();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
		<eespc:displayControl paramName="mixtureType" formName="etoInfo"> 
			<html:select property="mixtureType" styleClass="normal" onchange="mixTypeChanged();" >
				<html:optionsCollection name="ETO_GAS_MIXTURE_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Weight of the Container 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="WeightoftheContainer();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="containerWeight" formName="etoInfo" > 
      <html:text property="containerWeight" styleClass="normal" disabled="true" 			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/> 
    </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Gas Mixture 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="GasMixture();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="gasMixture" formName="etoInfo"> 
			<html:select property="gasMixture" styleClass="normal" onchange="mixTypeChanged();" disabled="true">
				<html:optionsCollection name="ETO_CYLINDER_GAS_MIXTURE" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	  </td>
  </tr>
  
  <tr> 
    <td class="label_right" nowrap height="15">Installation Date(<font color="#006699">mm/dd/yyyy</font>) <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="InstallationDate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="installationDate" formName="etoInfo"> 
      <html:text property="installationDate" styleClass="normal" size="10" maxlength="10"  /> 
    </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Average Use (Hrs/Day) 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AverageUse();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="hrs" formName="etoInfo"> 
      <html:text property="hrs" styleClass="normal" size="10" maxlength="10"  onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
    </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap height="15">Average Use (Days/Week)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AverageUse();"  onmouseout="UnTip()" /> :</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="days" formName="etoInfo"> 
      <html:text property="days" styleClass="normal" size="10" maxlength="10"  onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
    </eespc:displayControl> 
    </td>
  </tr>
  
  <tr>
    <td class="label_right" nowrap height="15">Does it require the stack test?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="stackTest();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="stackTestRequire"  formName="etoInfo"> 
      <html:radio property="stackTestRequire" value="Y" onclick="displaycontrol();"/>Yes 
      <html:radio property="stackTestRequire" value="N" onclick="displaycontrol();"/>No 
      <html:radio property="stackTestRequire" value="N/A" onclick="displaycontrol();"/>N/A
      </eespc:displayControl>
    </td>
  </tr>
  
   <logic:equal name="etoInfo" property="stackTestRequire" value="Y"> 
   <tr> 
    <td class="label_right" height="30">Stack Test Date Required by the Permit</strong>(<font color="#006699">mm/dd/yyyy</font>)
        
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StackTestDaterequiredbythepermit();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="30">&nbsp;
		<eespc:displayControl paramName="A_permitdate" formName="etoInfo"> 
			<html:text property="A_permitdate" styleClass="normal" size="10" maxlength="10" /> 
		</eespc:displayControl>
	</td>
  </tr>
  </logic:equal> 
  
  
   <tr> <td class="label_right" nowrap height="15"><eespc:requiredField />Abator?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Abetor();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="isabator"  formName="etoInfo"> 
      <html:radio property="isabator" value="Y" onclick="displaycontrol()"/>Yes 
      <html:radio property="isabator" value="N" onclick="displaycontrol()"/>No 
      </eespc:displayControl> </td>
  </tr>
  <logic:equal name="etoInfo" property="isabator" value="Y">   
   <tr> 
    <td width="25%" nowrap class="label_right" height="15">
    Abator Manufacturer 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AbetorManufacturer();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="amanufacturer" formName="etoInfo"> 
      <html:text property="amanufacturer" styleClass="normal" /> 
    </eespc:displayControl> 
    </td>
  </tr>
 <tr> 
    <td class="label_right" nowrap height="15">
 
    Abator
	
    Model 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AbetorModel();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="amodel" formName="etoInfo"> 
      <html:text property="amodel" styleClass="normal" /> 
    </eespc:displayControl> 
    </td>
  </tr>
  <tr> 
    <td class="label_right" height="15">DEC permit obtained ? 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DECpermitsobtained();"  onmouseout="UnTip()" /></logic:equal>: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="A_decPermitObtained" formName="etoInfo"> 		
			<html:radio property="A_decPermitObtained" value="Y"  />Yes 
			<html:radio property="A_decPermitObtained" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  <tr> 
    <td class="label_right" height="29">Stack Test Protocol Submitted to DEC 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StacktestprotocolsubmittedtoDEC();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="29">&nbsp;
		<eespc:displayControl paramName="A_stackTestProtSubmited" formName="etoInfo"> 		
			<html:radio property="A_stackTestProtSubmited" value="Y"  />Yes 
			<html:radio property="A_stackTestProtSubmited" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr>
    <td class="label_right" height="15">Test Conducted by 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestConductedby();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="15"> &nbsp;
		<eespc:displayControl paramName="A_testConductedBy" formName="etoInfo"> 
			<html:text property="A_testConductedBy" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" height="15">Test Report Submitted to DEC<logic:equal name="etoInfo" property="isabator" value="Y"><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestReportsubmittedtoDEC();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="15"> &nbsp;
		<eespc:displayControl paramName="A_testReportSubmited" formName="etoInfo"> 		
			<html:radio property="A_testReportSubmited" value="Y"  />Yes 
			<html:radio property="A_testReportSubmited" value="N"  />No 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  
  <tr> 
    <td class="label_right" height="15">Unit Complies with ETO RACT Plan 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UnitcomplywithETORACTplan();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
		<eespc:displayControl paramName="A_compyWithETO" formName="etoInfo"> 		
			<html:radio property="A_compyWithETO" value="Y"  />Yes 
			<html:radio property="A_compyWithETO" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
  <tr> 
    <td class="label_right" height="12">Test Passed/Compliance 

 
  <logic:equal name="etoInfo" property="isabator" value="Y">   
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestPassedCompliance();"  onmouseout="UnTip()" /></logic:equal>:</td>
    <td class="fieldleft"  nowrap height="12">&nbsp;
		<eespc:displayControl paramName="A_testPassed" formName="etoInfo"> 		
			<html:radio property="A_testPassed" value="Y"  />Yes 
			<html:radio property="A_testPassed" value="N"  />No 
		</eespc:displayControl>	  											  
	</td>
  </tr>
   </logic:equal>
  <tr> 
    <td class="label_right" nowrap height="15">Are Daily Records Available?
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsDailyrecordsavailable();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="recordsAvailable" formName="etoInfo"> 
      <html:radio property="recordsAvailable" value="Y"/>Yes
      <html:radio property="recordsAvailable" value="N"/>No 
      </eespc:displayControl> </td>
  </tr>
  <tr> 
    <td height="19" class="label_right"><%=dep%> #  
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEP();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="19">&nbsp;<eespc:displayControl paramName="dep" formName="etoInfo"> 
      <html:text property="dep" styleClass="normal" /> </eespc:displayControl> </td>
  </tr>
  
   <tr> 
    <td class="label_right" nowrap height="15"><%=dob%> Filing
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBFiling();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="etoDOB" formName="etoInfo"> 
       <html:radio property="etoDOB" value="Y" onclick="displaycontrol()"/>Yes
       <html:radio property="etoDOB" value="N" onclick="displaycontrol()"/>No
       <html:radio property="etoDOB" value="N/A" onclick="displaycontrol()"/>N/A 
     </eespc:displayControl>
     </td>
  </tr>
 
   
  <logic:equal name="etoInfo" property="etoDOB" value="Y"> 
  <tr> 
    <td height="19" class="label_right"><%=dob%> Filing/Job # 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="19">&nbsp; 
    <eespc:displayControl paramName="dob" formName="etoInfo"> 
      <html:text property="dob" styleClass="normal" />
    </eespc:displayControl> </td>
  </tr>
   <tr> 
    <td class="label_right" nowrap><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap >&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="etoInfo"> 		
			<html:radio property="dobsignoff" value="Y"/>Yes
			<html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  </logic:equal>
  
 <tr> 
    <td class="label_right" height="15"><eespc:requiredField />Status of Source<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp;
			<eespc:displayControl paramName="ModifiedInPast" formName="etoInfo"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="ETO_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
	   &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="etoInfo"> 
			<html:text property="DisconnectedYear" styleClass="normal"  onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
		</eespc:displayControl>
	   </td>
  </tr>
<tr> 
    <td class="label_right" nowrap height="15">Comments 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap height="15">&nbsp; 
    <eespc:displayControl paramName="comments" formName="etoInfo"> 
      <html:textarea property="comments" cols="25" rows="1"/>
    </eespc:displayControl> 
    </td>
  </tr>

</table>		
        

<br>
<logic:equal name="showAddBtn" value="Y">
<logic:equal name="etoInfo" property="stackTestRequire" value="Y"> 
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Stack Test Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle">
                <td   class=columnhead>Year</td>
                <td   class=columnhead>Last Test Date<br></strong>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Next Test Date<br></strong>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
<%
			List etoTestList = (List)request.getAttribute("ETO_TEST_INFO");
			boolean toEditTest = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_TEST_INFO"));
			if (etoTestList != null){
				int size = etoTestList.size();
				for (int i=0; i < size; i++)
				{
					EtoTestInfoVo etoTestVo = (EtoTestInfoVo)etoTestList.get(i);
					boolean isTestEditable = false;
					int testId = etoTestVo.getEtoTestId();
					String testIdStr = testId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + testIdStr + ", " + toEditStatePermit);
					if (null != tempStr && 
							tempStr.equalsIgnoreCase(testIdStr) && toEditTest)
					{
						isTestEditable = true;
					}
					
					  out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(etoTestVo.getYear());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isTestEditable, "testYear", etoTestVo.getYear(),"YEARS"));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(etoTestVo.getTestDate());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTestEditable, "testLastDate", etoTestVo.getTestDate(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(	etoTestVo.getNextDate());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTestEditable, "testNextDate", etoTestVo.getNextDate(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(	etoTestVo.getNextDateNote());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isTestEditable, "testNextDateNote", etoTestVo.getNextDateNote(),null));
						out.println("</td>");
									
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isTestEditable){
							out.println("<a href=\"javascript:editTest('");
							out.println(testId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");				
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isTestEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteTest('");
							out.println(testId);
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
			if (!toEditTest){ //show the following only if it is not edit			
%>				  
              <tr class="evenRowStyle">
                <td width="23%" align="center"  nowrap class="fieldleft">
				<html:select property="testYear" styleClass="normal" >
					<html:optionsCollection name="YEARS" property="dropDownValues" value="value" label="name" /> 
				</html:select>								
				  </td>
                <td width="23%" align="center"  nowrap class="fieldleft"><input name="testLastDate" type="text" class="normal" id="testLastDate" size="10" maxlength="10"></td>
                <td width="23%" align="center"  nowrap class="fieldleft"><input name="testNextDate" type="text" class="normal" id="testNextDate" size="10" maxlength="10"></td>
                <td width="23%" align="center"  nowrap class="fieldleft"><input name="testNextDateNote" type="text" class="normal" id="testNextDateNote" size="10"></td>

                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditTest loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=6>
                <input type="button" name="Button22" value="<%= ((toEditTest)? "Update" : "Add") %>" onClick="<%= ((toEditTest)? "addTest(true)" : "addTest(false)") %>;">
                </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
<br>
</logic:equal>
</logic:equal> 

	<logic:equal name="showAddBtn" value="Y">
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
                <td   class=columnhead>Issued Date<br></strong>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br></strong>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Edit</td><td   class=columnhead>Delete</td>

              </tr>
<%
			List etoDepList = (List)request.getAttribute("ETO_DEP_PERMIT");
			boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DEP_PERMIT"));
			if (etoDepList != null){
				int size = etoDepList.size();
				for (int i=0; i < size; i++)
				{
					PermitInfoVo depPermitVo = (PermitInfoVo)etoDepList.get(i);
					boolean isDepPermitEditable = false;
					int permitId = depPermitVo.getId();
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
						//out.println(depPermitVo.getYear());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isDepPermitEditable, "depYear", depPermitVo.getYear(),"YEARS"));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(depPermitVo.getIssueDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depIssueDate", depPermitVo.getIssueDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(	depPermitVo.getExpirationDateStr());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpDate", depPermitVo.getExpirationDateStr(),null));
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						//out.println(	depPermitVo.getNote());
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "depExpDateNote", depPermitVo.getNote(),null));
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
                <td width="26%" align="center"  nowrap class="fieldleft"><input type="text" name="depIssueDate" class="normal" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input type="text" name="depExpDate" class="normal" size="10" maxlength="10"> 
                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input type="text" name="depExpDateNote" class="normal" size="20"> 
                </td>
                
                <td   class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%
			}// if !toEditDepPermit loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=6>
                <input type="button" name="Button22" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDepPermit)? "addDep(true)" : "addDep(false)") %>;">
                </td>
              </tr>
              
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("Eto.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("Eto.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
            <td  align="right"><input type="button" name="Button22" value="<< Return To Building" onClick="returnToBuilding();">
      		</td>		  
			<logic:notEqual name="isComponentEditable" value="N">
<%
			String tempStr = (String)request.getAttribute("isItForEdit");
			boolean  isEdit = false;
			if (null != tempStr && tempStr.equalsIgnoreCase("Y")){
				isEdit = true;
			}
			if (isEdit){
%>						
			<script>mixTypeChanged();</script>
<%
			}
%>
            <td  align="right">
            	<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addEto(true)" : "addEto(false)") %>;">
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
Input and Verified by the Facility, Then the  Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>	
</body>
</html>