<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>

<html>
<head>
<title>Facility Search</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Facility.js" ></script>
<script src="/eespc/help/facilitysearchhelp.js" ></script>
</head>

<body onLoad="document.forms[0].clName.focus();">

<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>

<SPAN CLASS=page_title>Facility Search</SPAN>
	<br>		
	<br>	
	<html:form action="/SearchFacility">			
	<html:hidden property="methodToCall" />	
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse; border: 1px solid #EEEEEE; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" bordercolor="#111111" height="75">
  
 
  <tr> 
    <td align="center" height="36" valign="top">	
				
	<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="72%" id="AutoNumber1" height="33">
      <tr> 
    <td width="17%" nowrap class="label_right" height="33">Client Name <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="clientname();"  onmouseout="UnTip()" />:</td>
    <td width="17%" nowrap class="label_right" height="33" colspan="2">&nbsp;<html:text property="clName" styleClass="normal" /></td>
    <td width="17%" nowrap class="label_right" height="33">&nbsp;Type Of Facility <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="typeoffacility();"  onmouseout="UnTip()" />:</td>
    <td width="17%" nowrap class="label_right" height="33">&nbsp;<html:select property="clFacilityType" styleClass="normal" ><html:optionsCollection name="FACILITY_TYPE" property="dropDownValues" value="value" label="name" /> 
      </html:select> </td>
    <td width="16%" nowrap class="label_right" height="33" colspan="2">&nbsp;DEC Id <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="decid();"  onmouseout="UnTip()" />:</td>
    <td width="16%" nowrap class="label_right" height="33">&nbsp;<html:text property="clDecId" styleClass="normal" /></td>
  </tr>
    </table>
    </td>
  </tr>
  
 
  <tr> 
    <td align="center" height="36" valign="top" style="border-top:1px solid #F2F2F2; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1; border-left-width:1; border-right-width:1; border-bottom-width:1">	
				
	<input type="submit" name="Button" value="Search" onClick="search();"> 
      <input type="reset" name="Submit2" value="Reset"></td>
  </tr>
</table>

<br>
		<c:if test="${searchFacilityForm.map.methodToCall == 'search'}">		
		<eespc:table dataSource="FACILITY_SEARCH_LIST"
		  formName="form1"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No facility available for the search criteria.</SPAN></CENTER>" >
		
		  <eespc:tableHeaderCellDef id="h_ClientName" label="Client Name" bodyCellId="b_ClientName"/>
		  <eespc:tableHeaderCellDef id="h_DecId" label="DEC Id" bodyCellId="b_DecId"/>
		  <eespc:tableHeaderCellDef id="h_TypeOfFacility" label="Type of Facility" bodyCellId="b_TypeOfFacility"/>
		  <eespc:tableHeaderCellDef id="h_City" label="City" bodyCellId="b_City"/>
		  <eespc:tableHeaderCellDef id="h_State" label="State" bodyCellId="b_State"/>  
		  
		  <eespc:tableBodyCellDef id="b_editId" method="getId"/>
		  <eespc:tableBodyCellDef id="b_deleteId" method="getId"/>
		  <eespc:tableBodyCellDef id="b_crId" method="getId"/>
		  <eespc:tableBodyCellDef id="b_ccrId" method="getId"/>
		  <eespc:tableBodyCellDef id="b_ClientName" method="getClientName"/>
		  <eespc:tableBodyCellDef id="b_DecId" method="getDecId"/>
		  <eespc:tableBodyCellDef id="b_TypeOfFacility" method="getFacilityTypeName"/>
		  <eespc:tableBodyCellDef id="b_City" method="getFacilityCity"/>  
		  <eespc:tableBodyCellDef id="b_State" method="getFacilityState"/>    
		
		  <eespc:tr>
			  <eespc:th>$$h_ClientName$$</eespc:th>
			  <eespc:th>$$h_DecId$$</eespc:th>
			  <eespc:th>$$h_TypeOfFacility$$</eespc:th>
			  <eespc:th>$$h_City$$</eespc:th>	  	  	  
			  <eespc:th>$$h_State$$</eespc:th>	  	  	  	  
			  <eespc:th>Edit</eespc:th>	  
			  <eespc:th>Delete</eespc:th>	  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb><eespc:link label="$$b_ClientName$$" key="$$b_ccrId$$" script="facilityDetails" /></eespc:tb>
			  <eespc:tb><eespc:link label="$$b_DecId$$" key="$$b_crId$$" script="facilityDetails" /></eespc:tb>
			  <eespc:tb>$$b_TypeOfFacility$$</eespc:tb>
			  <eespc:tb>$$b_City$$</eespc:tb>
			  <eespc:tb>$$b_State$$</eespc:tb>	  	  	  
			  <eespc:tb><eespc:link label="<img src='/eespc/images/edit.jpg' border=0>" key="$$b_editId$$" script="editFacility" /></eespc:tb>
			  <eespc:tb>	
			  	<%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>

<eespc:link label="<img src='/eespc/images/delete.jpg' border=0>" key="$$b_deleteId$$" script="deleteFacility" />			  
<%	

}
else
{
%>

  
<img src="/eespc/images/delete.jpg" border="0" width="42" height="13">
<%
}

%>	 
	
		      </eespc:tb>
	     </eespc:tr>
	     
		</eespc:table>
		</c:if>
	</html:form>
</body>
</html>