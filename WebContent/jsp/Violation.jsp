
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
<title>Violation</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/help/violationhelp.js" ></script>
<script src="/eespc/js/violation.js" ></script>

<script>
function searchExist(){
	var url = "/eespc/ViolationListInfo.do?formId=id&formIdName=violationno";
	window.open(url, "_blank");
}
</script>
</head>
<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title><%=request.getAttribute("vtype")%><b> Violation:</b></SPAN> 		
	<br>			
<html:form action="/ViolationAction" enctype="multipart/form-data">		

<input type="hidden" name="methodToCall" value="reset">
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">

<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="344">
 <tr> 
    <td width="33%" nowrap class="label_right" height="27">
    <p align="right">
    <b>
    <eespc:requiredField /> Violation Number	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="violationnumber();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" colspan="4" height="27">&nbsp; 
     <eespc:displayControl paramName="violationno" formName="ViolationForm">  
    	<html:text property="violationno" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    	&nbsp;</eespc:displayControl></td>
  </tr>
  
 <tr>
    <td width="50%" height="19" align="right" valign="top"><b>
    <eespc:requiredField />Issuing Agency<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="violationissuingagency();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
     <eespc:displayControl paramName="violationWhich" formName="ViolationForm"> 	
			<html:select property="violationWhich" styleClass="normal">
				<html:optionsCollection name="VIOLATION_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>		
		</eespc:displayControl>	

 <tr>
    <td width="50%" height="19" align="right"><b>If Other, Agency Name<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="agencyname();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
   <eespc:displayControl paramName="otherAgency" formName="ViolationForm">  
    	<html:text property="otherAgency" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr>
  

  
   <tr>
    <td width="50%" height="19" align="right" valign="top"><b>
    <eespc:requiredField />Violation Type<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="violationtype();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
     <eespc:displayControl paramName="violationType" formName="ViolationForm">  
    	<html:text property="violationType" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>			</td>
  </tr>

  <tr>
    <td width="50%" height="19" align="right"><b>Description<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="descrip_tion();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
   <eespc:displayControl paramName="description" formName="ViolationForm">  
    	<html:text property="description" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr>
  
   <tr>
    <td width="50%" height="19" align="right"><b>Issue Date (<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="issuedate();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="issueDate" formName="ViolationForm">  
    	<html:text property="issueDate" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr> 
<tr>
    <td width="50%" height="19" align="right"><b>Remedial Measures<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="remedialmeasures();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
     <eespc:displayControl paramName="remedialMeassures" formName="ViolationForm">  
    	<html:text property="remedialMeassures" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="50%" height="19" align="right" valign="top"><b>Contractor<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="contractor();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="contractor" formName="ViolationForm">  
    	<html:text property="contractor" styleClass="normal"/>&nbsp;
    </eespc:displayControl>		
	</td>
  </tr>

<tr>
    <td width="50%" height="19" align="right"><b>Corrective Action Taken<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="stepstaken();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
     <eespc:displayControl paramName="stepsTaken" formName="ViolationForm">  
    	<html:text property="stepsTaken" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr>

	
 <tr>
    <td width="50%" height="19" align="right"><b>Violation Removal Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="removaldate();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
     <eespc:displayControl paramName="removalDate" formName="ViolationForm">  
    	<html:text property="removalDate" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr>	
  <tr> 
    <td class="label_right" nowrap width="50%">
    <p align="right"><b>Intermediate Status <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="intermediatestatus();"  onmouseout="UnTip()" />:</b></td>
   <td width="50%" colspan="4" height="19">	 &nbsp;			
		<eespc:displayControl paramName="interMediateStatus" formName="ViolationForm">  
    	<html:text property="interMediateStatus" styleClass="normal"/>
    	&nbsp;</eespc:displayControl> 	
	</td>
  </tr>
  
  <tr>
    <td width="50%" height="19" align="right"><b>Final Compliance Date (<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="finalcompliancedate();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
   	<eespc:displayControl paramName="finalComplianceDate" formName="ViolationForm">  
    	<html:text property="finalComplianceDate" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>

  <tr>
    <td width="50%" height="19" align="right"><b>Violation Fee<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="feePaid();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
   <eespc:displayControl paramName="feePaid" formName="ViolationForm">  
    	<html:text property="feePaid" styleClass="normal"/>&nbsp;
    	</eespc:displayControl></td>
  </tr>
  <tr>
    <td width="50%" height="19" align="right"><b>Court Hearing Date (<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="courthearingdate();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
   	<eespc:displayControl paramName="jDate" formName="ViolationForm">  
    	<html:text property="jDate" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>


</table>
<br>

<br><br>
</p>
<table width="100%" border="0" cellspacing="0" cellpadding="0">

          <tr> 
            <td  align="center"><input type="button" name="Button22" value="Return To Building" onClick="returnToBuilding();">
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addViolation(true)" : "addViolation(false)") %>;">			            
     	    </td>
            <td ><input type="reset" name="Submit22" value="Reset"></td>
			</logic:notEqual>
								  			  
          </tr>
    </table> 
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />","<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>
</html:form>
</body>
</html>