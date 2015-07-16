<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.BuildingInspectionReportVo" %>
<%@ page import="com.eespc.tracking.bo.myenum.ResourcesTypeEnum" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>
<%@ page import="com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.bo.ViolationVo" %>
<html:base />
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
  <style type="text/css">
.mybutton { width: 1.9em;
height: 1.7em; }
  </style>
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Building.js" ></script>
<script src="/eespc/help/buildinghelp.js" ></script> 
<script>
function sa()
{
return false;
}

function deleteviolation(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].action='/eespc/ViolationAction.do?methodToCall=delete&id=' + id;
	document.forms[0].submit();
 }
 else
 {
 
 }

}
function addViolation(vw)
{
document.forms[0].action="/eespc/ViolationAction.do?methodToCall=reset&violationWhich="+vw;
document.forms[0].submit();
}



</script>

<%
	out.println(ResourcesTypeEnum.getJavaScript());
	out.println(ResourcesTypeEnum.getJavaScript(true));	
%>
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
<head>

<%
	FacilityVo facilityVo = (FacilityVo)session.getAttribute("FACILITY_OBJECT");
	StringBuffer tempBuffer = new StringBuffer();	
	tempBuffer.append("document.forms[0].action='/eespc/AddFacility.do?methodToCall=view&id=").append(facilityVo.getId()).append("';");
	tempBuffer.append("document.forms[0].submit();");
	
%>

<title>Resource</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Facility.js" ></script>
<script src="/eespc/help/facilitysearchhelp.js" ></script>
</head>

<body >

<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>

<SPAN CLASS=page_title>Resource</SPAN>
	<br>		
	<br>
	<html:form action="/BuildingInfo"> 
<html:hidden property="methodToCall" value="add" />	
<html:hidden property="id" />	
<logic:notEmpty name="ALL_RESOURSE_LIST">
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>List of Source in Buildings of This 
      Facility</FONT></TD>
  </TR>
</TABLE>
	
<eespc:table dataSource="ALL_RESOURSE_LIST"
  formName="form1"
  noDataFoundMessage="<CENTER><SPAN CLASS=error>There is no resources in this building.</SPAN></CENTER>" >

  <eespc:tableHeaderCellDef id="h_resourceType" label="Resource Type" bodyCellId="b_resourceType"/>
  <eespc:tableHeaderCellDef id="h_bname" label="Building Name" bodyCellId="b_bname"/>
  <eespc:tableHeaderCellDef id="h_name" label="Name" bodyCellId="b_name"/>
  
  <eespc:tableBodyCellDef id="b_entityId" method="getEntityId"/>
  <eespc:tableBodyCellDef id="b_entityType" method="getEntityType"/>  
  <eespc:tableBodyCellDef id="b_entityId2" method="getEntityId"/>
  <eespc:tableBodyCellDef id="b_entityType2" method="getEntityType"/> 
  <eespc:tableBodyCellDef id="b_entityId1" method="getEntityId"/>
  <eespc:tableBodyCellDef id="b_entityType1" method="getEntityType"/>    
  <eespc:tableBodyCellDef id="b_resourceType" method="getEntityTypeName"/>
  <eespc:tableBodyCellDef id="b_name" method="getName"/>
  <eespc:tableBodyCellDef id="b_bname" method="getBname"/>

  <eespc:tr>
      <eespc:th>$$h_resourceType$$</eespc:th>
      <eespc:th>$$h_name$$</eespc:th>
	  <eespc:th>$$h_bname$$</eespc:th>
      <eespc:th>Edit</eespc:th>	 
      <eespc:th>Delete</eespc:th> 
  </eespc:tr>

  <eespc:tr>
      <eespc:tb>$$b_resourceType$$</eespc:tb>  
      <eespc:tb><a href="javascript:viewSource('$$b_entityId$$', '$$b_entityType$$');">$$b_name$$</a></eespc:tb>
	  <eespc:tb>$$b_bname$$</eespc:tb>  
     
      <eespc:tb><a href="javascript:editSource('$$b_entityId2$$', '$$b_entityType2$$');"><img src="/eespc/images/edit.jpg" border="0"></a></eespc:tb>	  

			<%	
		
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
   <eespc:tb><a href="javascript:deleteSource('$$b_entityId1$$', '$$b_entityType1$$');"><img src="/eespc/images/delete.jpg" border="0"></a></eespc:tb>	  	
<%
}
else
{
%>
  <eespc:tb><img src="/eespc/images/delete.jpg" border="0"></eespc:tb>	 
	
<%
}

%></eespc:tr></eespc:table></logic:notEmpty>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>		
			  <td align="center" width="26%"  nowrap class="fieldleft"><input type="button" name="Button2" value="<< Return To Facility" onClick="<%= tempBuffer%>">
			</td>
		  </tr>
		</table>

</html:form>