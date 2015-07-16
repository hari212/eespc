<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>

<HTML><HEAD><TITLE>Stack Search</TITLE>
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
<script src="/eespc/js/CommonScript.js" ></script>
<script>
function setInfo(){
	var formObj = document.forms[0].stackRsltRbtn;
	if (radioChecked(formObj) == "false"){
		alert("Please select a Elevator .");
	}else{
		var id ="";
		var idName="" ;
		if (document.forms[0].stackRsltRbtn && document.forms[0].stackRsltRbtn.length){
			for (var i=0; i<document.forms[0].stackRsltRbtn.length;i++){
				if(document.forms[0].stackRsltRbtn[i].checked){
					id = document.forms[0].stackRsltRbtn[i].value;
					idName = document.forms[0].stackRsltName[i].value;
					break;
				}
			}
		}else{
			if(document.forms[0].stackRsltRbtn){		
				if(document.forms[0].stackRsltRbtn.checked){
					id = document.forms[0].stackRsltRbtn.value;
					idName = document.forms[0].stackRsltName.value;
				}else{
					alert("Please select a Elevator ");
				}
			}else{
				alert("No Elevator Available.");
			}
			
		}
		
		opener.document.forms[0].id.value=id;
		opener.document.forms[0].methodToCall.value='VIEWEXIST';
		opener.document.forms[0].submit();
		
		self.close();
			
	}
}
</script>
</HEAD>

<BODY>
	<html:form action="/ElevatorListInfo">			
<BR><BR>
		<eespc:table dataSource="ELEVATOR_SEARCH_LIST"
		  formName="form1"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No Elevator available in this facility.</SPAN></CENTER>" >
		  
		  <eespc:tableHeaderCellDef id="h_Type" label="TYPE" bodyCellId="b_Type"/>		
		  <eespc:tableHeaderCellDef id="h_HId" label="(Facility Designated ID)" bodyCellId="b_HId"/>			  		  
		  <eespc:tableHeaderCellDef id="h_buildingName" label="Building Name" bodyCellId="b_buildingName"/>
		  
		  <eespc:tableBodyCellDef id="b_editId" method="getElevatorId"/>
		  
		  
		  <eespc:tableBodyCellDef id="b_HId" method="getFacilityDesignatedId"/>
		  <eespc:tableBodyCellDef id="b_Type" method="getEleType"/>		  
		  <eespc:tableBodyCellDef id="b_buildingName" method="getBuildingName"/>	 	  
		  <eespc:tableBodyCellDef id="b_HId2" method="getFacilityDesignatedId"/>			
		  <eespc:tr>
			  <eespc:th>Select</eespc:th>	  
			  <eespc:th>$$h_Type$$ </eespc:th>
			  <eespc:th>$$h_HId$$</eespc:th>
			  <eespc:th>$$h_buildingName$$</eespc:th>	  	  	  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb>
			  <input type="radio" name="stackRsltRbtn" value="$$b_editId$$" />
			  <input type="hidden" name="stackRsltName" value="$$b_HId2$$" />
			  </eespc:tb>		  
			  <eespc:tb>$$b_Type$$</eespc:tb>			 
			  <eespc:tb>$$b_HId$$</eespc:tb>			  	  	  
			  <eespc:tb>$$b_buildingName$$</eespc:tb>	  	  	  			  
		  </eespc:tr>
		</eespc:table>
<table width="100%" border="0">
  <tr>
    <td align="right">&nbsp;<input type="button" name="Ok" value="Ok" onClick="setInfo();" /></td>
    <td>&nbsp;&nbsp;&nbsp;<input type="button" name="Cancel" value="Cancel" onClick="self.close();" /></td>
  </tr>
</table>

</FORM></BODY>
</html:form>