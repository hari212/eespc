<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New landfills</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Lfills1.js" ></script>
<script src="/eespc/help/landfillhelp.js" ></script>
<script>

function disConnect()
{

if(document.forms[0].lf_status.value==-1 || document.forms[0].lf_status.value==2 || document.forms[0].lf_status.value==3 || document.forms[0].lf_status.value==4)
{
document.forms[0].lf_disconnecteddate.disabled=false;
}
else
{
document.forms[0].lf_disconnecteddate.disabled=true;
}
}
function searchExist(){
	var url = "/eespc/LandfillsListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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


%>
</head>
<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Landfill</SPAN><br>		
<br>			
<html:form action="/LandFillsAction" enctype="multipart/form-data">	
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id">     
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

<table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" width="100%">
  <tr> 
    <td nowrap class="label_right" width="50%"> <eespc:requiredField />ID # 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td  nowrap class="fieldleft" width="279" >&nbsp; 
    <eespc:displayControl paramName="lfId" formName="landFillsForm"> 
       <html:text property="lfId" styleClass="normal"/>
       <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>	
    (Facility Designated)</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="50%" >Operation Commenced Date(<font color="#006699">mm/dd/yyyy</font>) 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OperationCommencedDate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
      <eespc:displayControl paramName="lfDate" formName="landFillsForm"> 
    	<html:text property="lfDate" styleClass="normal"/>
      </eespc:displayControl>			
    </td>
  </tr>
  
  <tr> 
    <td class="label_right" width="50%" ><eespc:requiredField />Status of Source 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusOfSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="279" >&nbsp;
			<eespc:displayControl paramName="lf_status" formName="landFillsForm"> 	
			<html:select property="lf_status" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>	  	  											  
	  &nbsp; <strong>Disconnected Year :</strong> 
	  		<eespc:displayControl paramName="lf_disconnecteddate" formName="landFillsForm"> 
			<html:text property="lf_disconnecteddate" styleClass="normal" size="10" maxlength="10" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	  </td>
  </tr>



  
  <tr> 
    <td class="label_right" width="50%" >Is Landfill Listed as Title V facility 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsLandfilllistedasTitleVfacility();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="279" >
   
       <eespc:displayControl paramName="lfFacility" formName="landFillsForm"> 
        <html:radio property="lfFacility"  styleClass="normal" value="Y"/>Yes 
        <html:radio property="lfFacility"  styleClass="normal" value="N"/>No
       </eespc:displayControl>
   
    </td>
  </tr>
  
  
  
  
 
 
 
 <tr> 
    <td class="label_right" nowrap width="50%" >Linked to Co-Gen or Turbines 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="LinkedtoCoGenorTurbines();"  onmouseout="UnTip()" />: </td>	
    <td class="fieldleft"  nowrap width="279" >
  
      <eespc:displayControl paramName="lfTurbines" formName="landFillsForm"> 
      <html:radio property="lfTurbines"  value="Y" styleClass="normal" onclick="showTable('CoGenDiv')"/>Yes 
      <html:radio property="lfTurbines"  value="N" styleClass="normal" onclick="hideTable('CoGenDiv')"/>No
      </eespc:displayControl>			
  
    </td>
  </tr>
 <%
  request.setAttribute("SELECTED_TURBINE",request.getAttribute("SELECTED_TURBINE"));
  request.setAttribute("COGENLIST",request.getAttribute("COGENLIST"));
  
  session.setAttribute("SELECTED_TURBINE",request.getAttribute("SELECTED_TURBINE"));
  session.setAttribute("COGENLIST",request.getAttribute("COGENLIST"));

  
  request.setAttribute("SHOW_BUTTONS",request.getAttribute("SHOW_BUTTONS"));
  request.setAttribute("SHOW_UPDATE_BUTTONS",request.getAttribute("SHOW_UPDATE_BUTTONS"));
  
  
  %>
  
    <tr> 
    <td class="label_right" nowrap  valign="top" width="50%">Add Cogen to the List of Source 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AddCogentotheListofSource();"  onmouseout="UnTip()" />: </td>	
    <td align="left" width="279" >&nbsp;
    <DIV id= "CoGenDiv" class="label_right" style= "{display:<%   
        String strs = (String)request.getAttribute("SHOW_BUTTONS"); 
        String strs1 = (String)request.getAttribute("SHOW_UPDATE_BUTTONS"); 
        
        if (strs != null && strs1 != null)
        {
           if ( strs.equals("none") && strs1.equals("none"))
              out.println(strs);
           else if (strs.trim().equals("inline")) 	
                  out.println(strs);
           else if (strs1.trim().equals("inline"))       
                  out.println(strs1);
        }
        else
             out.println("none"); %> }">

      	  <html:select size="5" property="lfCoTurbines"  multiple="true" styleClass="normal;height:70px" >
	     <html:options collection="COGENLIST" property="value" labelProperty="label"/> 
          </html:select> 

 			<input type="button" name="Submit23" value="cogenlist" onClick="getCoGenList()">
   </DIV>
   <%
        if (strs != null && strs.equals("none") && strs1 != null && strs1.equals("none"))
        {
        
        
     	List chemList1	 = (List)request.getAttribute("SELECTED_TURBINE");
    	int size4 =0;
    	
    	if (chemList1 != null){
    		size4 = chemList1.size();
    		for (int i=0; i < size4; i++)
    		{
    			String info = (String)chemList1.get(i);
                  	out.println("<tr>");
                  	out.println("<td class=\"label_right\" nowrap>");
                    	out.println("<td nowrap class=\"fieldright\">");
    			out.println(info);
    			out.println("</td>");
    			out.println("</td>");
    		        out.println("</tr>");
    		}
         }
    	}
    	%>
   </td>       
      
  </tr>   
 
 
 
 
 
 
  <tr> 
    <td width="50%"  class="label_right"><%=dep%> # 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEP();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="lfDEPNumber" formName="landFillsForm"> 
    <html:text property="lfDEPNumber" styleClass="normal"/>
    </eespc:displayControl>
    </td>
  </tr>
   <tr> 
    <td width="50%"  class="label_right"><%=dob%> # 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="lfDOBNumber" formName="landFillsForm"> 
    <html:text property="lfDOBNumber" styleClass="normal"/>
    </eespc:displayControl>
    </td>
  </tr>
    <tr> 
    <td   class="label_right" width="50%"><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="279" >&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="landFillsForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes <html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  <tr> 
    <td width="50%"  class="label_right"><%=dob%> Issue Date(<font color="#006699">mm/dd/yyyy</font>) 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBIssuedate();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="lfDOBIssuedate" formName="landFillsForm"> 
    <html:text property="lfDOBIssuedate" styleClass="normal"/>
    </eespc:displayControl>
    </td>
  </tr>
  <tr> 
    <td   class="label_right" width="50%">Is There a Cap on the LFG Monthly Production 
    Quantity? 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereacapontheLFGmonthlyproductionquantity();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >
    <eespc:displayControl paramName="lfLFGQuantity" formName="landFillsForm"> 
    	<html:radio property="lfLFGQuantity"  value="Y" styleClass="normal" onclick="displayControl();"/> Yes
    	<html:radio property="lfLFGQuantity"  value="N" styleClass="normal" onclick="displayControl();"/>No
    </eespc:displayControl>
    </td>
  </tr>
  <logic:equal name="landFillsForm" property="lfLFGQuantity" value="Y"> 
  <tr> 
    <td   class="label_right" width="50%">If yes <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ifyes();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="LFGQuantity" formName="landFillsForm"> 
    <html:text property="LFGQuantity" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>&nbsp;CF
    </eespc:displayControl>
    </td>
  </tr>
  </logic:equal>  
  <tr> 
    <td   class="label_right" width="50%">Is There a Cap on the LFG Combustion by the Cogen/Turbine unit? 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereacapontheLFGCombustionbytheCogenturbineunit();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >
    <eespc:displayControl paramName="lfLFGCombustion" formName="landFillsForm"> 
    	<html:radio property="lfLFGCombustion"  value="Y" styleClass="normal" onclick="displayControl();"/> Yes
    	<html:radio property="lfLFGCombustion"  value="N" styleClass="normal" onclick="displayControl();"/>No
    </eespc:displayControl>
    </td>
  </tr>
  <logic:equal name="landFillsForm" property="lfLFGCombustion" value="Y"> 
  <tr> 
    <td   class="label_right" width="50%">If yes 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Ifyes();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="LFGCombustion" formName="landFillsForm"> 
    <html:text property="LFGCombustion" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>&nbsp;CF/H
    </eespc:displayControl>
    </td>
  </tr>
  </logic:equal>  
    <tr> 
    <td   class="label_right" width="50%">Is Monitoring Required for S% and CH<sub>4</sub> % ? 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsmonitoringrequiredforSandCH4();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" > 
    <eespc:displayControl paramName="lfSCH4" formName="landFillsForm"> 
    	<html:radio property="lfSCH4"  value="Y" styleClass="normal" onclick="displayControl();"/> Yes
    	<html:radio property="lfSCH4"  value="N" styleClass="normal" onclick="displayControl();"/>No
    </eespc:displayControl>
    </td>
  </tr>
    <logic:equal name="landFillsForm" property="lfSCH4" value="Y"> 
  <tr> 
    <td   class="label_right" width="50%">If Yes S% Measured/Allowable Value<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfyesSMeasuredAllowablevalue();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="svalue" formName="landFillsForm"> 
    <html:text property="svalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>/
    <eespc:displayControl paramName="sallowablevalue" formName="landFillsForm"> 
    <html:text property="sallowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>    
    </td>
  </tr>
  
  <tr> 
    <td   class="label_right" width="50%">If Yes, CH<sub>4</sub>% Measured/Allowable 
    Value<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfyesCH4MeasuredAllowablevalue();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="ch4value" formName="landFillsForm"> 
    <html:text property="ch4value" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>/
    <eespc:displayControl paramName="ch4allowablevalue" formName="landFillsForm"> 
    <html:text property="ch4allowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>    
    </td>
  </tr>
  </logic:equal>  
 <tr> 
    <td   class="label_right" width="50%">Is There a Flue Gas Temperature Limit?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsthereaFLUEgastemperaturelimit();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" > 
    <eespc:displayControl paramName="istemperaturelimit" formName="landFillsForm"> 
    	<html:radio property="istemperaturelimit"  value="Y" styleClass="normal" onclick="displayControl();"/> Yes
    	<html:radio property="istemperaturelimit"  value="N" styleClass="normal" onclick="displayControl();"/>No
    </eespc:displayControl>
    </td>
  </tr>
   <logic:equal name="landFillsForm" property="istemperaturelimit" value="Y"> 
  <tr> 
    <td   class="label_right" width="50%">If Yes, 			
	
    Flue Gas        
    Temperature Limit 
    Tested/Allowable value<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IfyesFLUEgastemperaturelimitTestedAllowablevalue();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="temperaturevalue" formName="landFillsForm"> 
    <html:text property="temperaturevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>/
    <eespc:displayControl paramName="temperatureallowablevalue" formName="landFillsForm"> 
    <html:text property="temperatureallowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl> &nbsp;F<sup>o</sup>   
    </td>
  </tr>

  </logic:equal>  
  <tr> 
    <td   class="label_right" width="50%">NOX Tested/Allowable Emission<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="NOXTestedAllowableEmmissions();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="noxvalue" formName="landFillsForm"> 
    <html:text property="noxvalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>/
    <eespc:displayControl paramName="noxallowablevalue" formName="landFillsForm"> 
    <html:text property="noxallowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>&nbsp;PPM
    
    </td>
  </tr>
  
  <tr> 
    <td   class="label_right" width="50%">CO 			
	
    Tested/Allowable Emission<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="COTestedAllowableEmmissions();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="covalue" formName="landFillsForm"> 
    <html:text property="covalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" />
    </eespc:displayControl>/
    <eespc:displayControl paramName="coallowablevalue" formName="landFillsForm"> 
    <html:text property="coallowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" />
    </eespc:displayControl>   &nbsp;PPM 
    </td>
  </tr>
  <tr> 
    <td   class="label_right" width="50%">OTHERS 			
	
    Tested/Allowable Emission<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OTHERSTestedAllowableEmmissions();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="ovalue" formName="landFillsForm"> 
    <html:text property="ovalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>/
    <eespc:displayControl paramName="oallowablevalue" formName="landFillsForm"> 
    <html:text property="oallowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>    &nbsp;PPM
    </td>
  </tr>
 <tr> 
    <td   class="label_right" width="50%">Is There Limit on Yearly Operating 
    
     <logic:equal name="landFillsForm" property="yearlyoperating" value="Y">
    Hours</logic:equal>?<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Istherelimitonyearlyoperatingitems();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >
    <eespc:displayControl paramName="yearlyoperating" formName="landFillsForm"> 
    	<html:radio property="yearlyoperating"  value="Y" styleClass="normal" onclick="displayControl();"/> Yes
    	<html:radio property="yearlyoperating"  value="N" styleClass="normal" onclick="displayControl();"/>No
    </eespc:displayControl>
    </td>
  </tr>
     <logic:equal name="landFillsForm" property="yearlyoperating" value="Y">
   <tr> 
    <td   class="label_right" width="50%">Operated/Allowable Hours<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="OperatedAllowableHours();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
    <eespc:displayControl paramName="yearlyvalue" formName="landFillsForm"> 
    <html:text property="yearlyvalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>/
    <eespc:displayControl paramName="yearlyallowablevalue" formName="landFillsForm"> 
    <html:text property="yearlyallowablevalue" styleClass="normal" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)"/>
    </eespc:displayControl>    &nbsp;Hours
    </td>
  </tr>
  </logic:equal>  
  <tr> 
    <td height="35" class="label_right" width="50%" >Is Annual Emission Statement 
    Submitted 
      to State Agency 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="IsAnnualemissionstatementsubmittedtostateagencies();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >
                <eespc:displayControl paramName="lfEmission" formName="landFillsForm"> 
                 <html:radio property="lfEmission"  value="Y" styleClass="normal" onclick="displayControl();"/> Yes
                 <html:radio property="lfEmission"  value="N" styleClass="normal" onclick="displayControl();"/>No
                 </eespc:displayControl>			
    </td>
  </tr>
  <logic:equal name="landFillsForm" property="lfEmission" value="Y"> 
    <tr> 
    <td class="label_right" nowrap width="50%" >AES Submitted Date(<font color="#006699">mm/dd/yyyy</font>) 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AESSubmiteddate();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp; 
		<eespc:displayControl paramName="aesdate" formName="landFillsForm"> 
			<html:text property="aesdate" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  </logic:equal>  
  <tr> 
    <td class="label_right" nowrap width="50%" >Location 
 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Location();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="279" >&nbsp;
    <eespc:displayControl paramName="psLocation" formName="landFillsForm"> 
		<html:select property="psLocation" styleClass="normal">
			<html:optionsCollection name="AGENCY_LOCATION" property="dropDownValues" value="value" label="name"/> 
        	</html:select>		
     </eespc:displayControl></td>
  </tr>
   <tr> 
    <td  class="label_right" valign="top" width="50%">Comments<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="comments();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="279" >&nbsp;
		<eespc:displayControl paramName="lcomments" formName="landFillsForm"> 
			<html:textarea property="lcomments" cols="40" rows="3"/> 
		</eespc:displayControl>	
	 </td>
  </tr>
  </table>

<br>

<logic:equal name="showAddBtn" value="Y">
<DIV id= "DepDiv " style= "{display:<%   
        String str = (String)request.getAttribute("LFILLS_DEP"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %> }">


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
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>

              </tr>
	<%
	List inspectionList1	 = (List)request.getAttribute("LFILL_DEP_LST");
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
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "lfDEPIssueDate", statePermitInfo.getIssueDateStr(),null));
			//out.println(statePermitInfo.getIssueDateStr());
			out.print("</td>");
                	out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "lfDEPExpirationDate", statePermitInfo.getExpirationDateStr(),null));
			//out.println(statePermitInfo.getExpirationDateStr());
			out.print("</td>");				
					out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "lfDEPExpirationDateNote", statePermitInfo.getNote(),null));
			//out.println(statePermitInfo.getNote());
			out.print("</td>");				
			out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/LandFillsAction.do?methodToCall=depEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");

				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");		
			out.println("<td width=\"6%\" align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/LandFillsAction.do?methodToCall=depDelete&hdnPermitId=");out.print(permitId);
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
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="lfDEPIssueDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="lfDEPExpirationDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="lfDEPExpirationDateNote" styleClass="normal"/></td>                
                <td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
              </tr>
<%
	}
	else // if !toEditDepPermit loop
	  method = "depUpdate";
%>

              <tr align="right" class="evenRowStyle"> 
                <td    colspan=5>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("LandFillsAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("LandFillsAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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



</p>

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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addlandfill(true);" : "addlandfill(false);") %>">			
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
Input and Verified by the Facility, Then the  Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>     
</body>
</html>