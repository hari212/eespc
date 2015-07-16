<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>

<HTML><HEAD><TITLE>Fuel Tank Search</TITLE>
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
<script src="/eespc/js/CommonScript.js" ></script>
<script>
function setInfo(){
	var formObj = document.forms[0].tankRsltRbtn;
	if (radioChecked(formObj) == "false"){
		alert("Please select a Storage Tank.");
	}else{
		var id ="";
		var idName="" ;
		if (document.forms[0].tankRsltRbtn && document.forms[0].tankRsltRbtn.length){
			for (var i=0; i<document.forms[0].tankRsltRbtn.length;i++){
				if(document.forms[0].tankRsltRbtn[i].checked){
					id = document.forms[0].tankRsltRbtn[i].value;
					idName = document.forms[0].tankRsltName[i].value;
					break;
				}
			}
		}else{
			if(document.forms[0].tankRsltRbtn){
				if(document.forms[0].tankRsltRbtn.checked){
					id = document.forms[0].tankRsltRbtn.value;
					idName = document.forms[0].tankRsltName.value;
				}else{
					alert("Please select a Storage Tank.");
				}
			}else{
				alert("No Storage Tank Available.");
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
	<html:form action="/tankSearchListInfo">			
<BR><BR>
		<eespc:table dataSource="FUEL_TANK_SEARCH_LIST"
		  formName="form1"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No Fuel Tank available in this facility.</SPAN></CENTER>" >
		  
		  <eespc:tableHeaderCellDef id="h_pbs" label="PBS #" bodyCellId="b_pbs"/>		
		  <eespc:tableHeaderCellDef id="h_tankId" label="Tank ID (Facility Designated)" bodyCellId="b_tankId"/>
		  <eespc:tableHeaderCellDef id="h_tankCapacity" label="Capacity" bodyCellId="b_tankCapacity"/>		  
		  <eespc:tableHeaderCellDef id="h_buildingName" label="Building Name" bodyCellId="b_buildingName"/>
		  
		  <eespc:tableBodyCellDef id="b_editId" method="getId"/>
		  <eespc:tableBodyCellDef id="b_pbs" method="getPbsNumber"/>
		  <eespc:tableBodyCellDef id="b_tankId" method="getFacilityDesignatedId"/>
		  <eespc:tableBodyCellDef id="b_tankCapacity" method="getCapacity"/>
		  <eespc:tableBodyCellDef id="b_buildingName" method="getBuildingName"/>
		  <eespc:tableBodyCellDef id="b_tankId2" method="getFacilityDesignatedId"/>		  
		  		
		  <eespc:tr>
			  <eespc:th>Select</eespc:th>	  
			  		  
			  <eespc:th>$$h_pbs$$</eespc:th>
			  <eespc:th>$$h_tankId$$</eespc:th>
			  <eespc:th>$$h_tankCapacity$$</eespc:th>
			  <eespc:th>$$h_buildingName$$</eespc:th>	  	  	  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb>
			  <input type="radio" name="tankRsltRbtn" value="$$b_editId$$" />
			  <input type="hidden" name="tankRsltName" value="$$b_tankId2$$" />
			  </eespc:tb>		  
			  <eespc:tb>$$b_pbs$$</eespc:tb>
			  <eespc:tb>$$b_tankId$$</eespc:tb>
			  <eespc:tb>$$b_tankCapacity$$</eespc:tb>
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