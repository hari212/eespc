<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>

<HTML><HEAD><TITLE>Admin User Search</TITLE>
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
<script src="/eespc/js/CommonScript.js" ></script>
<script>
function setInfo(){
	var formObj = document.forms[0].stackRsltRbtn;
	if (radioChecked(formObj) == false){
		alert("Please select a User.");
		return false;
	}
	else{
		var id ="";

		if (document.forms[0].stackRsltRbtn && document.forms[0].stackRsltRbtn.length){
			for (var i=0; i<document.forms[0].stackRsltRbtn.length;i++){
				if(document.forms[0].stackRsltRbtn[i].checked){
					id = document.forms[0].stackRsltRbtn[i].value;
					break;
				}
			}
		}
		else{
			if(document.forms[0].stackRsltRbtn){		
				if(document.forms[0].stackRsltRbtn.checked){
					id = document.forms[0].stackRsltRbtn.value;
				}else{
					alert("Please select a User.");
				}
			}else{
				alert("No User Available.");
			}
			
		}
		opener.document.forms[0].userId.value=id;
		opener.document.forms[0].methodToCall.value='view';
		opener.document.forms[0].submit();
		self.close();		
	}
}
</script>

</HEAD>
<BODY>
	<html:form action="/UserListInfo">			
<BR><BR>
		<eespc:table dataSource="MANAGE_USER_LIST"
		  formName="form1"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No User List available.</SPAN></CENTER>" >
		  
		  <eespc:tableHeaderCellDef id="h_firstName" label="First Name" bodyCellId="b_firstName"/>		
		  <eespc:tableHeaderCellDef id="h_lastName" label="Last Name" bodyCellId="b_lastName"/>
		  <eespc:tableHeaderCellDef id="h_initial" label="Initial" bodyCellId="b_initial"/>		  
		  <eespc:tableHeaderCellDef id="h_title" label="Title" bodyCellId="b_title"/>		  		  
		  <eespc:tableHeaderCellDef id="h_role" label="Role" bodyCellId="b_role"/>
		  <eespc:tableHeaderCellDef id="h_clientAccess" label="Client Access List" bodyCellId="b_clientAccess"/>		  
		  <eespc:tableHeaderCellDef id="h_disabled" label="Disabled" bodyCellId="b_disabled"/>		  		  
		  
		  <eespc:tableBodyCellDef id="b_id" method="getUserId"/>
		  <eespc:tableBodyCellDef id="b_firstName" method="getFirstName"/>
		  <eespc:tableBodyCellDef id="b_lastName" method="getLastName"/>
		  <eespc:tableBodyCellDef id="b_initial" method="getMiddleName"/>
		  <eespc:tableBodyCellDef id="b_title" method="getTitle"/>
		  <eespc:tableBodyCellDef id="b_role" method="getUserRoleString"/>
		  <eespc:tableBodyCellDef id="b_clientAccess" method="getClientAccessString"/>		  
		  <eespc:tableBodyCellDef id="b_disabled" method="getDisabledIndicator"/>		  		  
		  		
		  <eespc:tr>
			  <eespc:th>Select</eespc:th>	  
			  <eespc:th>$$h_firstName$$</eespc:th>
			  <eespc:th>$$h_lastName$$</eespc:th>
			  <eespc:th>$$h_initial$$</eespc:th>
			  <eespc:th>$$h_title$$</eespc:th>
			  <eespc:th>$$h_role$$</eespc:th>	  	  	  
			  <eespc:th>$$h_clientAccess$$</eespc:th>	  	  	  			  
			  <eespc:th>$$h_disabled$$</eespc:th>	  	  	  			  			  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb>
			  <input type="radio" name="stackRsltRbtn" value="$$b_id$$" />
			  </eespc:tb>		  
			  <eespc:tb>$$b_firstName$$</eespc:tb>
			  <eespc:tb>$$b_lastName$$</eespc:tb>
			  <eespc:tb>$$b_initial$$</eespc:tb>
			  <eespc:tb>$$b_title$$</eespc:tb>	  	  	  
			  <eespc:tb>$$b_role$$</eespc:tb>	  	  	  			  
			  <eespc:tb><textarea name="dummy" cols="35" rows="3" readonly="true">$$b_clientAccess$$</textarea></eespc:tb>	  
			  <eespc:tb>$$b_disabled$$</eespc:tb>	  	  	  			  			  			  	  	  			  			  
		  </eespc:tr>
		</eespc:table>
<table width="100%" border="0">
  <tr>
    <td align="right">&nbsp;<input type="button" name="Ok" value="Ok" onClick="setInfo();" /></td>
    <td>&nbsp;&nbsp;&nbsp;<input type="button" name="Cancel" value="Cancel" onClick="self.close();" /></td>
  </tr>
</table>
</html:form>
</BODY>
</HTML>

