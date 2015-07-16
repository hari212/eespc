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
<script src="/eespc/js/Elev1.js" ></script>
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
function displaycontrol()

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
	var url = "/eespc/ElevatorListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, "_blank");
}

</script>

<body>
<SPAN CLASS=page_title>Add Printing Press</SPAN> <br>		
	<br>			
<html:form action="/PrintingPressAction" onsubmit="return validateEleForm(this)">	
<html:javascript formName="eleForm"/>	
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
  <tr> 
    <td width="27%" nowrap class="label_right"><eespc:requiredField /> ID # :</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
     <eespc:displayControl paramName="eleId" formName="eleForm">  
    	<html:text property="eleId" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Search Exist" onClick="searchExist();">
    </eespc:displayControl>        
    (Facility Designated)</td>
  </tr>
  

		
		
<tr> 
    <td nowrap class="label_right"><eespc:requiredField />Type :</td>
    <td width="73%"  nowrap class="fieldleft"> 
    		  <eespc:displayControl paramName="eleType" formName="eleForm"> 		
			<html:radio property="eleType" value="Elevator" onclick="displaycontrol()" />Elevator 
			<html:radio property="eleType" value="Escalator" onclick="displaycontrol()" />Escalator
			<html:radio property="eleType" value="Electric" onclick="displaycontrol()" />Electric  
		</eespc:displayControl>
    
  </tr>		
  <logic:equal name="eleForm" property="eleType" value="Elevator">
  <tr> 
    <td nowrap class="label_right">Hydraulic Tank :</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
    		<eespc:displayControl paramName="eleHydTankFrom" formName="eleForm"> 
    			<html:hidden property="eleHydTank" /> 		
    			<html:text property="eleHydTankFrom" styleClass="normal" /> 
    			<input type="button" name="Submit23" value="Search" onClick="searchFuel();">
    		</eespc:displayControl>
    
  </tr>
  </logic:equal>
   

 
		
		
   <tr> 
    <td class="label_right"><eespc:requiredField />Status of Source</td>
    <td class="fieldleft"  nowrap>&nbsp;
			<eespc:displayControl paramName="ModifiedInPast" formName="eleForm"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="ELE_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
 											  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="eleForm"> 
			<html:text property="DisconnectedYear" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" styleClass="normal" /> 
		</eespc:displayControl>
	   </td>
  </tr>


  <tr> 
    <td width="27%" height="20" nowrap class="label_right">
    Comments :</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
     <eespc:displayControl paramName="elecomment" formName="eleForm"> 
     <html:textarea property="elecomment" cols="28	" rows="2"/> 
    	
    </eespc:displayControl>        
    </td>
  </tr>

 
  <tr>
<br>
<br>
</tr>
  </table>		
<br>	
       		

<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  
    <td width="53%" align="right">
      <html:submit property="method"  onclick="bCancel=true;returnToBuilding();">
	 <bean:message key="landFillsform.add"/>
      </html:submit>   
  </td>  
    <td width="47%">
    <%        
       String str = (String)request.getAttribute("SHOW_BUTTONS"); 
       
        %>
      <div id="buttondiv" style= "{display:
      <%
        if (str != null)
             out.println(str);
        else
             out.println("none"); 
      %> }">
  
       <html:submit property="method" onclick="return validate(false)">
  	 <bean:message key="landFillsform.save"/>
        </html:submit>   
        <html:submit property="method"  onclick="setmethod('RESET');bCancel=true">
	 <bean:message key="landFillsform.reset"/>
      </html:submit> 
      </div>
	      <div id="buttondiv" style= "{display:
	      <%        
		str = (String)request.getAttribute("SHOW_UPDATE_BUTTONS"); 
		if (str != null)
		     out.println(str);
		else
		     out.println("none"); 
	      %> }">

	       <html:submit property="method" onclick="validate(true)">
		 <bean:message key="landFillsform.update"/>
		</html:submit>   
	      </div>

    </td>
  </tr>
</table>  
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>
</html:form>        
</body>
</html>