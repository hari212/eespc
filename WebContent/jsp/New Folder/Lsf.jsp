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
<title>Printing Press</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/help/lsfhelp.js" ></script>
<script src="/eespc/js/lsf.js" ></script>
</head>  

<script>

</script>

<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Staff and their Fire Department (FD) Certificate of 
Fitness (C of F) Status :</SPAN> 		
	<br>			
<html:form action="/LsfAction">		
<input type="hidden" name="methodToCall" value="reset">



<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="344">
 <tr> 
    <td width="33%" nowrap class="label_right" height="27">
    <p align="right">
    <b>
    <eespc:requiredField /> Staff First Name	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="first_name();"  onmouseout="UnTip()" />:</b></td>
    
    <td width="73%"  nowrap class="fieldleft" colspan="4" height="27">&nbsp; 
     <eespc:displayControl paramName="firstname" formName="LsfForm">  
    	<html:text property="firstname" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>
 
  
   <tr>
    <td width="50%" height="19" align="right" valign="top"><b>
    Last Name<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="last_name();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
     <eespc:displayControl paramName="lastname" formName="LsfForm">  
    	<html:text property="lastname" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
  </tr>
  
    <tr>
    <td width="50%" height="19" align="right" valign="top"><b>
    Certification Number<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="certificateNumber();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19">&nbsp; 
     <eespc:displayControl paramName="certificatenumber" formName="LsfForm">  
    	<html:text property="certificatenumber" styleClass="normal"/>
    	&nbsp;</eespc:displayControl></td>
    </tr>
  


  
   <tr>
    <td width="50%" height="19" align="right"><b>Phone Number<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="phone_number();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="phonenumber" formName="LsfForm">  
    	<html:text property="phonenumber" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr> 
<tr>
    <td width="50%" height="19" align="right"><b>Department<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="depart_ment();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
     <eespc:displayControl paramName="department" formName="LsfForm">  
    	<html:text property="department" styleClass="normal"/>
    	&nbsp;</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="50%" height="19" align="right" valign="top"><b>
    <eespc:requiredField />
    Operating Equipment &amp; Type of C of F <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="coff();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
    
			<html:select property="operatingequipments"   multiple="true" size="10">
				<html:optionsCollection name="COFF_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
			</td>

  </tr>


   
  <tr> 
    <td class="label_right" nowrap width="50%">
    <p align="right"><b>Certification Issue Date(<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="issue_date();"  onmouseout="UnTip()" />:</b></td>
   <td width="50%" colspan="4" height="19">	 &nbsp;			
		<eespc:displayControl paramName="issuedate" formName="LsfForm"> 		
			<html:text property="issuedate"  styleClass="normal" />	
		</eespc:displayControl>	
	</td>
  </tr>

  <tr>
    <td width="50%" height="19" align="right"><b>Expiration Date(<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="expiration_date();"  onmouseout="UnTip()" />:</b></td>
    <td width="50%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="expirationdate" formName="LsfForm"> 		
			<html:text property="expirationdate"  styleClass="normal" />	
		</eespc:displayControl>		</td>
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
		onClick="<%= ((isEdit)? "addLsf(true)" : "addLsf(false)") %>;">			            
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