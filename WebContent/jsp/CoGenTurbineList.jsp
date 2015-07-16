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
		alert("Please select a CoGen.");
	}else{
	var listB = opener.document.forms[0].lfCoTurbines ;
		var id ="";
		var idName="" ;
		if (document.forms[0].stackRsltRbtn && document.forms[0].stackRsltRbtn.length){
		
		for (var i=0; i<document.forms[0].stackRsltRbtn.length;i++){
		
		listB.options[i].selected  = false;
		}
		       
			for (var i=0; i<document.forms[0].stackRsltRbtn.length;i++){
				if(document.forms[0].stackRsltRbtn[i].checked){
					id = document.forms[0].stackRsltRbtn[i].value;
					idName = document.forms[0].stackRsltName[i].value;
					listB.options[i].selected  = true;
                                        
					
				}
			}
		}else{
			if(document.forms[0].stackRsltRbtn){		
				if(document.forms[0].stackRsltRbtn.checked){
					id = document.forms[0].stackRsltRbtn.value;
					idName = document.forms[0].stackRsltName.value;
				}else{
					alert("Please select a CoGen/Turbine.");
				}
			}else{
				alert("No CoGen Turbine Available.");
			}
			
		}
		
		self.close();		
	}
}
</script>
</HEAD>

<BODY>
	<html:form action="/CogenListAction">			
<BR><BR>
		<eespc:table dataSource="COGEN_LIST"  formName="form2"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No CoGen/Turbine available in this facility.</SPAN></CENTER>" >
		  
		  		
		  <eespc:tableHeaderCellDef id="h_stackId" label="CoGen ID (Facility Designated)" bodyCellId="b_stackId"/>
		  <eespc:tableHeaderCellDef id="h_stackHeight" label="Manufacturer" bodyCellId="b_stackHeight"/>		  
		  <eespc:tableHeaderCellDef id="h_stackDiameter" label="Make" bodyCellId="b_stackDiameter"/>		  		  
		  <eespc:tableHeaderCellDef id="h_buildingName" label="Model" bodyCellId="b_buildingName"/>
		  
		 
		  <eespc:tableBodyCellDef id="b_decEmissionId" method="getDecId"/>
		  <eespc:tableBodyCellDef id="b_stackId" method="getFacilityDesignatedId"/>
		  <eespc:tableBodyCellDef id="b_stackHeight" method="getManufacturer"/>
		  <eespc:tableBodyCellDef id="b_stackDiameter" method="getMake"/>
		  <eespc:tableBodyCellDef id="b_buildingName" method="getModel"/>
		  <eespc:tableBodyCellDef id="b_stackId2" method="getFacilityDesignatedId"/>		  
		  		
		  <eespc:tr>
			  <eespc:th>Select</eespc:th>	  
			  		  
			 
			  <eespc:th>$$h_stackId$$</eespc:th>
			  <eespc:th>$$h_stackHeight$$</eespc:th>
			  <eespc:th>$$h_stackDiameter$$</eespc:th>
			  <eespc:th>$$h_buildingName$$</eespc:th>	  	  	  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb>
			  <input type="checkbox" name="stackRsltRbtn" value="$$b_editId$$" />
			  <input type="hidden" name="stackRsltName" value="$$b_stackId$$" />
			  </eespc:tb>		  
			  
			  <eespc:tb>$$b_stackId2$$</eespc:tb>
			  <eespc:tb>$$b_stackHeight$$</eespc:tb>
			  <eespc:tb>$$b_stackDiameter$$</eespc:tb>	  	  	  
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