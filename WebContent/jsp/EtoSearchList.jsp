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
		alert("Please select a Storage Tank.");
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
					alert("Please select a Stack.");
				}
			}else{
				alert("No Stack Available.");
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
	<html:form action="/EtoListInfo">			
<BR><BR>
		<eespc:table dataSource="ETO_SEARCH_LIST"
		  formName="form1"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No stack available in this facility.</SPAN></CENTER>" >
		  
		  		
		  <eespc:tableHeaderCellDef id="h_CId" label="(Facility Designated ID)" bodyCellId="b_CId"/>			  		  
		  <eespc:tableHeaderCellDef id="h_buildingName" label="Building Name" bodyCellId="b_buildingName"/>
		   			  		  
		  <eespc:tableHeaderCellDef id="h_Model" label="MODEL" bodyCellId="b_Model"/>
		  <eespc:tableHeaderCellDef id="h_Serial" label="SERIAL" bodyCellId="b_Serial"/>
		  
		  <eespc:tableBodyCellDef id="b_editId" method="getEtoId"/>		  
		  <eespc:tableBodyCellDef id="b_CId" method="getFacilityDesignatedId"/>		  
		 		  
		  <eespc:tableBodyCellDef id="b_buildingName" method="getBuildingName"/>		  
		   		  
		   <eespc:tableBodyCellDef id="b_Model" method="getModel"/>		  
		  <eespc:tableBodyCellDef id="b_Serial" method="getSerial"/>		  
	 	  
		  <eespc:tableBodyCellDef id="b_CId2" method="getFacilityDesignatedId"/>			
		  <eespc:tr>
			  <eespc:th>Select</eespc:th>	  
			 
			  <eespc:th>$$h_CId$$</eespc:th>
			  <eespc:th>$$h_buildingName$$</eespc:th>	
			
			  <eespc:th>$$h_Model$$</eespc:th>
			  <eespc:th>$$h_Serial$$</eespc:th>  	  	  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb>
			  <input type="radio" name="stackRsltRbtn" value="$$b_editId$$" />
			  <input type="hidden" name="stackRsltName" value="$$b_CId2$$" />
			  </eespc:tb>		  
						 
			  <eespc:tb>$$b_CId$$</eespc:tb>			  	  	  
			  <eespc:tb>$$b_buildingName$$</eespc:tb>	
					  	  	  
			  <eespc:tb>$$b_Model$$</eespc:tb>
			  <eespc:tb>$$b_Serial$$</eespc:tb>  	  	  			  
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