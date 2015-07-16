<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.BuildingInspectionReportVo" %>
<%@ page import="com.eespc.tracking.bo.myenum.ResourcesTypeEnum" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>
<%@ page import="com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.bo.ViolationVo" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Building</title>
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

<%
	out.println(ResourcesTypeEnum.getJavaScript());
	out.println(ResourcesTypeEnum.getJavaScript(true));	
%>
</head>
<%
	FacilityVo facilityVo = (FacilityVo)session.getAttribute("FACILITY_OBJECT");
	StringBuffer tempBuffer = new StringBuffer();	
	tempBuffer.append("document.forms[0].action='/eespc/AddFacility.do?methodToCall=view&id=").append(facilityVo.getId()).append("';");
	tempBuffer.append("document.forms[0].submit();");
	
%>
<%
try{
%>
<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>

<SPAN CLASS=page_title>&nbsp;&nbsp;&nbsp; Building Information</SPAN><br>		
	<br>
<html:form action="/BuildingInfo"> 
<html:hidden property="methodToCall" value="add" />	
<html:hidden property="id" />	
<input name="hdnPermitId" type="hidden" value="<%=request.getParameter("hdnPermitId")%>">
<center>	
<table width="100%" style="border-collapse: collapse" bordercolor="#CCCCCC" cellpadding="0" cellspacing="0" border="1" height="236" >
  <tr> 
    <td   width="26%" align="right" height="34"><b><eespc:requiredField />  
      Building Name<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="BuildingName();"  onmouseout="UnTip()" />:</b></td>
    <td    width="25%" height="34">&nbsp; 
		<eespc:displayControl paramName="bldgName" formName="buildingInfo"> 
			<html:text property="bldgName" maxlength="100" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
    <td  width="24%" align="right" height="34"  ><b><eespc:requiredField />  Building 
      ID # 
    <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="BuildingID();"  onmouseout="UnTip()" />:</b></td>
    <td class="fieldleft"  nowrap width="25%" align="left" height="34" >&nbsp; 
		<eespc:displayControl paramName="bldgId" formName="buildingInfo"> 
			<html:text property="bldgId" maxlength="50" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td  width="26%" align="right" height="34" > <b>DOB 
      BIN # <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="DOBBIN();"  onmouseout="UnTip()" />:</b></td>
    <td  width="25%" height="34" >&nbsp; 
		<eespc:displayControl paramName="dobBin" formName="buildingInfo"> 
			<html:text property="dobBin" maxlength="50" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
    <td  width="24%" align="right" height="34"  ><b>Lot 
      # <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="Lot();"  onmouseout="UnTip()" />: </b></td>
    <td   width="25%" align="left" height="34" >&nbsp; 
    <eespc:displayControl paramName="lot" formName="buildingInfo"> 
			<html:text property="lot" maxlength="50" styleClass="normal" /> 
		</eespc:displayControl></td>
  </tr>
  <tr> 
    <td width="26%" align="right" height="34"   ><b>Block 
      # <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="Block();"  onmouseout="UnTip()" />:</b> </td>
    <td class="fieldleft"  width="25%" height="34" >&nbsp; 
		
		<eespc:displayControl paramName="block" formName="buildingInfo"> 
			<html:text property="block" maxlength="50" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
    <td  width="24%" align="right" height="34"  ><b>Address 
      <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="Address();"  onmouseout="UnTip()" />:</b> </td>
    <td   width="25%" align="left" height="34">&nbsp; 
		<eespc:displayControl paramName="address" formName="buildingInfo"> 
			<html:text property="address" maxlength="60" styleClass="normal" /> 
		</eespc:displayControl>
	</td>
  </tr>
  <tr> 
    <td width="26%" align="right" height="34" ><b><eespc:requiredField /> Borough <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="Borough();"  onmouseout="UnTip()" />: </b> </td>
    <td   width="25%" height="34" >&nbsp; 
		<eespc:displayControl paramName="borough" formName="buildingInfo"> 
			<html:select property="borough" styleClass="normal" >
				<html:optionsCollection name="BOROUGHS" property="dropDownValues" value="value" label="name" /> 
			</html:select>
		</eespc:displayControl>
	</td>
    <td  width="24%" align="right" height="34"  > <b><eespc:requiredField />No. of Stories <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="NoofStories();"  onmouseout="UnTip()" />:</b></td>
    <td  width="25%" align="left" height="34" >&nbsp;
		<eespc:displayControl paramName="bldgTall" formName="buildingInfo"> 	
	 		<html:text property="bldgTall" maxlength="2" styleClass="normal"
	 		onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
    		</eespc:displayControl>
	</td>
  </tr>
<tr> 
    <td  width="26%" align="right" height="34" ><b>If Other, Please Specify the City</b><input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="city_name();"  onmouseout="UnTip()" />:</td>
    <td  width="25%" height="34" >
    <eespc:displayControl paramName="cityname" formName="buildingInfo"> 
			<html:text property="cityname" maxlength="50" styleClass="normal" /> 
		</eespc:displayControl>
	&nbsp;	</td>
    <td  width="24%" align="right" height="34">&nbsp;</td>
    <td  width="25%" align="left" height="34">&nbsp;</td>
  </tr>
  <tr> 
    <td  width="26%" align="right" height="26" ><b>C of O Availability Status<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="cofo();"  onmouseout="UnTip()" />:</b></td>
    <td  width="25%" height="26" > 
    <eespc:displayControl paramName="cofobtaining" formName="buildingInfo">
    <html:radio property="cofobtaining" styleClass="normal" value="Y" />Yes
	<html:radio property="cofobtaining" styleClass="normal" value="N" />No
	</eespc:displayControl></td>
    <td  width="24%" align="right" height="26"  ><b>If Yes, &nbsp;(C of O) No<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="cofono();"  onmouseout="UnTip()" />:</b></td>
    <td  width="25%" align="left" height="26">&nbsp;<eespc:displayControl paramName="cofno" formName="buildingInfo"> 
			<html:text property="cofno" maxlength="60" styleClass="normal" /> 
		</eespc:displayControl>	  
</td>
  </tr>
    <tr> 
    <td  valign="top" width="26%" align="right" height="34" > <b>Foot Note  <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="FootNote();"  onmouseout="UnTip()" />:</b></td>
    <td width="25%" height="34"  >&nbsp; 
		<eespc:displayControl paramName="bldgfootnote" formName="buildingInfo"> 
			<html:textarea property="bldgfootnote"  cols="18" rows="2"/>								
		</eespc:displayControl>	  
	</td>
    <td width="24%" align="right" height="34"  > <b>Comments <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="Comments();"  onmouseout="UnTip()" />:</b></td>
    <td  width="25%" align="left" height="34" >&nbsp; 
		<eespc:displayControl paramName="bldgcomment"  formName="buildingInfo"> 
			<html:textarea property="bldgcomment"  cols="18" rows="2"/>
		</eespc:displayControl>
	</td>
  </tr>
  
</table>

<br>
<logic:equal name="showAddBtn" value="Y">
<br>
<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
   
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD> <FONT COLOR=white CLASS=section>Facade Inspection Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                 <td   class=columnhead><font color="#FF0000" size="4">*</font>Facade Inspected for DOB<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="FacadeInspected();"  onmouseout="UnTip()" /></td>                
                <td   class=columnhead>Cycle<input type="image" src="/eespc/images/help.png" onClick="return false;"  onmouseover="Cycle();" onmouseout="UnTip()" /></td>                
                <td   class=columnhead>Facade Period <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="FacadePeriod();"  onmouseout="UnTip()" /></td>
                <td   class=columnhead>Submitted By <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="SubmittedBy();"  onmouseout="UnTip()" /></td>
                <td   class=columnhead>Facade Repair Required <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="FacadeRepair();"  onmouseout="UnTip()" /></td>
				<td   class=columnhead>DOB job # for repair work.<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="JobNumber();"  onmouseout="UnTip()" /></td>
				<td   class=columnhead>Is this Repair Work for the Current Cycle/Previous Cycle.<input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="RepairWork();"  onmouseout="UnTip()" /></td>
				<td   class=columnhead>DOB Permit Obtained <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="DOBPermit();"  onmouseout="UnTip()" /></td>                
				<td   class=columnhead>Prior Notification to EES<br>[<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>] <input type="image" src="/eespc/images/help.png" onClick="return false;"   onmouseover="ActualInspection();"   onmouseout="UnTip()" /></td>
				<td   class=columnhead>Filing Due Date<br>[<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>] <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="InspectionDueDate();"  onmouseout="UnTip()" /></td>         
			    <td   class=columnhead>Notes <input type="image" src="/eespc/images/help.png" onClick="return false;"    onmouseover="Note();"  onmouseout="UnTip()" /></td>
				<td   class=columnhead>Edit</td>
				<td   class=columnhead>Delete</td>
              </tr>
<%
	List inspectionList = (List)session.getAttribute("INSPECTION_LIST");
	boolean toEditInspection = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_BUILDING_INSPECTION"));	
	if (inspectionList != null){
		int size = inspectionList.size();
		for (int i=0; i < size; i++)
		{
			BuildingInspectionReportVo inspVo = (BuildingInspectionReportVo)inspectionList.get(i);
			boolean isInspectionEditable = false;
			int reportId = inspVo.getId();
			String reportIdStr = reportId +"";
			String tempStr = (String)request.getParameter("hdnPermitId");
//					out.println(tempStr + "==" + reportIdStr + ", " + toEditInspection);
			if (null != tempStr && 
					tempStr.equalsIgnoreCase(reportIdStr) && toEditInspection)
			{
				isInspectionEditable = true;
			}
			
            out.println("<tr class=\"evenRowStyle\">");
            out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.isFacadeDobInspected());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_RADIONA, isInspectionEditable, "facadeInspected", inspVo.isFacadeDobInspected(),null));
			out.println("</td>");
			
   			 out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.getCycle());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "cycle", inspVo.getCycle(),null));
			out.println("</td>");	
			
			out.println("<td  nowrap class=\"fieldleft\">");
			//out.println(	inspVo.getFacadePeriod());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "facadePeriod", inspVo.getFacadePeriod(),null));			
			out.println("</td>"); 
			
			
            out.println("<td nowrap class=\"fieldleft\">"); 
			//out.println(inspVo.getSubmitedBy());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "submitedBy", inspVo.getSubmitedBy(),null));
			out.println("</td>");	
						
            out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.isFacadeRepaired());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_RADIO, isInspectionEditable, "facRepairRequired", UtilityObject.booleanToString(inspVo.isFacadeRepaired()),null));
			out.println("</td>");	
			
			out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.getJobNumberRepairWork());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "jobNumberRepairWork", inspVo.getJobNumberRepairWork(),null));
			out.println("</td>");
					
			out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.getRepairedYear());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "repairedYear", inspVo.getRepairedYear(),null));
			out.println("</td>");
										
            out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.isDobPermitObtained());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_RADIO, isInspectionEditable, "dobPermitObtained", UtilityObject.booleanToString(inspVo.isDobPermitObtained()),null));
			out.println("</td>");				
						
			out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.getActualInspectionDate());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "actualInspDate", inspVo.getActualInspectionDate("MM/dd/yyyy"),null));
			out.println("</td>");	
			
			out.println("<td nowrap class=\"fieldleft\">");
			//out.println(inspVo.getNextInspectionDate());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "nextInspDate", inspVo.getNextInspectionDate("MM/dd/yyyy"),null));
			out.println("</td>");
			
			out.println("<td nowrap class=\"fieldleft\">"); 
			//out.println(inspVo.getFilingDateNotes());
			out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isInspectionEditable, "filingDateNotes", inspVo.getFilingDateNotes(),null));
			out.println("</td>");
			out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isInspectionEditable){
				out.println("<a href=\"javascript:editBuildingInspection('");
				out.println(reportId);
				out.println("');\">");
				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");								
			out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isInspectionEditable){
			 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
				{
				out.println("<a href=\"javascript:deleteBuildingInspection('");
				out.println(reportId);
				out.println("');\">");
				
				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
				}
				else
				{
				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\">");
				}
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");			
			  out.println("</tr>");
		}
	}
	if (!toEditInspection){ //show the following only if it is not edit						
%>	


		  
              <tr class="evenRowStyle"> 
                             		
                <td width="15%"  nowrap class="fieldleft"> 
					<html:radio property="facadeInspected" styleClass="normal" value="Y"/>Yes
					<html:radio property="facadeInspected" styleClass="normal" value="N"/>No
					<html:radio property="facadeInspected" styleClass="normal" value="NA"/>N/A	
				</td>				  		  
				  		  
				<td width="15%"  nowrap class="fieldleft"> 
					<html:text property="cycle" styleClass="normal"/>																	
				</td>
							  
				<td width="15%"  nowrap class="fieldleft">
					<html:text property="facadePeriod" styleClass="normal"/>																	
				</td>
				  
                <td width="15%"  nowrap class="fieldleft">
					<html:text property="submitedBy" styleClass="normal"/>
                </td>
                
                <td width="15%"  nowrap class="fieldleft">
					<html:radio property="facRepairRequired" styleClass="normal" value="Y"/>Yes
					<html:radio property="facRepairRequired" styleClass="normal" value="N" />No				
				</td>	
				  
				<td width="15%"  nowrap class="fieldleft">
					<html:text property="jobNumberRepairWork" styleClass="normal"/>
                </td>			  
				
                <td width="15%"  nowrap class="fieldleft">
					<html:text property="repairedYear" styleClass="normal"/>
                </td>
                
                <td width="15%"  nowrap class="fieldleft">
					<html:radio property="dobPermitObtained" styleClass="normal" value="Y"/>Yes
					<html:radio property="dobPermitObtained" styleClass="normal" value="N"/>No				
				</td>				
				
                <td width="15%"  nowrap class="fieldleft">
					<html:text property="actualInspDate" styleClass="normal"/> 
				</td>
				
				<td width="15%"  nowrap class="fieldleft">
					<html:text property="nextInspDate" styleClass="normal"/> 
				</td>
				
				<td width="15%" nowrap class="fieldleft">
					<html:textarea property="filingDateNotes" styleClass="normal"/>
                </td>	
				
		<td   class="oddRowStyle" colspan=2 width="70">&nbsp;</td>				
				
              </tr>
<%
	}// if !toEditInspection loop
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td colspan=13 >
                <input type="button" name="Button22" value="<%= ((toEditInspection)? "Update" : "Add") %>" onClick="<%= ((toEditInspection)? "validateBldgInsp(true)" : "validateBldgInsp(false)") %>;">
                </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
</div>
    </td>
     </tr>
</table>

<br>

<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="53">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>DOB Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationDobList = (List)request.getAttribute("VIOLATION_DOB_LIST");

  	if(violationDobList !=null)
  	{
		int violation_size = violationDobList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationdobvo = (ViolationVo)violationDobList.get(p);
  %>
 
  <tr>
    <td width="20%" height="15" align="center"><%=violationdobvo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationdobvo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationdobvo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationdobvo .getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationdobvo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(1);" value="Add New"></td>
  </tr>
   <tr>
    <td width="100%" height="36" align="center" colspan="5">
    <p align="left"><b>Help</b>:-<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <font face="Verdana" size="2">To Search DOB Violations, Go to this Website,
    <a href="http://www.nyc.gov/html/dob/html/bis/bis.shtml">http://www.nyc.gov/html/dob/html/bis/bis.shtml</a>, 
    Then you will get a Bis web Query, fill in this BIS WEB QUERY and click on submit button,
    Where yoy will get a Property Profile Overview. Under the Section, Department of Finance Building Classification 
You will find a Violations records, just click on Violations-DOB in this Section.</font></td>
  </tr>
</table>
<p>&nbsp;

<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>DEP Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">




    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationDepList = (List)request.getAttribute("VIOLATION_DEP_LIST");

  	if(violationDepList !=null)
  	{
		int violation_size = violationDepList.size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationdepvo = (ViolationVo)violationDepList.get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationdepvo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationdepvo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationdepvo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationdepvo .getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationdepvo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(2);" value="Add New"></td>
  </tr>
  
</table>
<p>&nbsp;


<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>ECB Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationEcbList = (List)request.getAttribute("VIOLATION_ECB_LIST");

  	if(violationEcbList !=null)
  	{
		int violation_size = violationEcbList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationecbvo = (ViolationVo)violationEcbList.get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationecbvo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationecbvo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationecbvo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationecbvo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationecbvo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(3);" value="Add New"></td>
  </tr>
    <tr>
    <td width="100%" height="36" align="center" colspan="5">
    <p align="left"><b>Help</b>:-<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <font face="Verdana" size="2">To search ECB Violations, Go to this website, 
    <a href="http://www.nyc.gov/html/dob/html/bis/bis.shtml">http://www.nyc.gov/html/dob/html/bis/bis.shtml</a>, 
    Then you will get a Bis web query, Fill in this BIS WEB QUERY and click on submit button,
    Where u will get a Property Profile Overview. Under the section, Department of Finance Building Classification
just click on the Violations-ECB(DOB), You will find a ECB Query by Location, 
    just click on
 the list of ECB NUM, you will get the details of ECB Violations.</font></td>
  </tr>
</table>
<p>&nbsp;


<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>DEC Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationDecList = (List)request.getAttribute("VIOLATION_DEC_LIST");

  	if(violationDecList !=null)
  	{
		int violation_size = violationDecList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationdecvo = (ViolationVo)violationDecList.get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationdecvo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationdecvo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationdecvo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationdecvo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationdecvo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(4);" value="Add New"></td>
  </tr>
 
</table>
<p>&nbsp;


<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>FD Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationFdList = (List)request.getAttribute("VIOLATION_FD_LIST");

  	if(violationFdList !=null)
  	{
		int violation_size = violationFdList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationfdvo = (ViolationVo)violationFdList.get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationfdvo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationfdvo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationfdvo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationfdvo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationfdvo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(5);" value="Add New"></td>
  </tr>
</table>
<p>&nbsp;
<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>EPA Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationEpaList = (List)request.getAttribute("VIOLATION_EPA_LIST");

  	if(violationEpaList !=null)
  	{
		int violation_size = violationEpaList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationepavo = (ViolationVo)violationEpaList .get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationepavo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationepavo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationepavo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationepavo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationepavo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(6);" value="Add New"></td>
  </tr>
</table>
<p>&nbsp;

<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>STATE/CITY Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationEpaList = (List)request.getAttribute("VIOLATION_CITY_LIST");

  	if(violationEpaList !=null)
  	{
		int violation_size = violationEpaList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationepavo = (ViolationVo)violationEpaList .get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationepavo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationepavo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationepavo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationepavo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationepavo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(7);" value="Add New"></td>
  </tr>
</table>
<p>&nbsp;



<br>
<table border="1" cellpadding="0" cellspacing="0" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="32">
  <tr>
    <td width="100%" height="13" colspan="5" bgcolor="#006699">
    <font color="#FFFFFF"><b>OTHER Violation Status :</b></font></td>
  </tr>
  <tr>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Number</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Violation Type</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center">

    <b>Issue Date</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Edit</b></td>
    <td width="20%" height="18" bgcolor="#FFFFCC" align="center"><b>Delete</b></td>
  </tr>
  <%
  try
  {
  	List violationEpaList = (List)request.getAttribute("VIOLATION_OTHER_LIST");

  	if(violationEpaList !=null)
  	{
		int violation_size = violationEpaList .size();
		
		for (int p=0; p <  violation_size; p++)
		{
			ViolationVo violationepavo = (ViolationVo)violationEpaList .get(p);
  %>
  <tr>
    <td width="20%" height="15" align="center"><%=violationepavo.getViolationno()%></td>
    <td width="20%" height="15" align="center"><%=violationepavo.getViolationType()%></td>
    <td width="20%" height="15" align="center"><%=UtilityObject.convertYyyyMmDd2MmDdYyyy(violationepavo.getIssueDate())%></td>
    <td width="20%" height="15" align="center"><a href="/eespc/ViolationAction.do?methodToCall=edit&id=<%=violationepavo.getId()%>"><img src="/eespc/images/edit.jpg" border="0" width="42" height="13"></a></td>
    
   <td width="20%" height="15" align="center"> 
    <%		 
		 
			 if(session.getAttribute("DELETE_PERMISSION")!=null)
{
%>
 <a href="javascript:deleteviolation('<%=violationepavo.getId()%>');"><img src="/eespc/images/delete.jpg" border="0" width="42" height="13"></a>
<%	

}
else
{
%>

  <img src="/eespc/images/delete.jpg" border="0" width="42" height="13">

<%
}

%>	 

    
    
   </td>
  </tr>
  <%
  }  
  }
  }
  catch(Exception e)
  {
  out.println(""+e);
  }
  %>
  <tr>
    <td width="100%" height="15" colspan="5">
    <p align="right"><input type="button" name="addf" onclick="addViolation(8);" value="Add New"></td>
  </tr>
</table>
<p>&nbsp;


<logic:notEmpty name="RESOURCE_LIST">
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>List of Source in This 
      Building</FONT></TD>
  </TR>
</TABLE>
<eespc:table dataSource="RESOURCE_LIST"
  formName="form1"
  noDataFoundMessage="<CENTER><SPAN CLASS=error>There is no resources in this building.</SPAN></CENTER>" >

  <eespc:tableHeaderCellDef id="h_resourceType" label="Resource Type" bodyCellId="b_resourceType"/>
  <eespc:tableHeaderCellDef id="h_name" label="Name" bodyCellId="b_name"/>
  
  <eespc:tableBodyCellDef id="b_entityId" method="getEntityId"/>
  <eespc:tableBodyCellDef id="b_entityType" method="getEntityType"/>  
  <eespc:tableBodyCellDef id="b_entityId2" method="getEntityId"/>
  <eespc:tableBodyCellDef id="b_entityType2" method="getEntityType"/> 
  <eespc:tableBodyCellDef id="b_entityId1" method="getEntityId"/>
  <eespc:tableBodyCellDef id="b_entityType1" method="getEntityType"/>    
  <eespc:tableBodyCellDef id="b_resourceType" method="getEntityTypeName"/>
  <eespc:tableBodyCellDef id="b_name" method="getName"/>

  <eespc:tr>
      <eespc:th>$$h_resourceType$$</eespc:th>
      <eespc:th>$$h_name$$</eespc:th>
      <eespc:th>Edit</eespc:th>	 
      <eespc:th>Delete</eespc:th> 
  </eespc:tr>

  <eespc:tr>
      <eespc:tb>$$b_resourceType$$</eespc:tb>  
      <eespc:tb><a href="javascript:viewSource('$$b_entityId$$', '$$b_entityType$$');">$$b_name$$</a></eespc:tb>
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

%>	
   


  </eespc:tr>
</eespc:table>
</logic:notEmpty>
</logic:equal>
<br>
<%
	String tempStr = (String)request.getAttribute("isItForEdit");
	boolean  isEdit = false;	
	if (null != tempStr && tempStr.equalsIgnoreCase("Y")){
		isEdit = true;
	}
%>						
<c:choose> 
	<c:when test="${buildingInfo.map.methodToCall == 'initial'}"> 

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr> 
			<td align="center"><input type="button" name="Button22" value="Save" onClick="validate(false);"> 
			  &nbsp; <input type="reset" name="Submit22" value="Reset">&nbsp; </td>
			  <td width="26%"  nowrap class="fieldleft"><input type="button" name="Button2" value="<< Return To Facility" onClick="<%= tempBuffer%>"></td>
		  </tr>
		</table>
	</c:when> 
	<c:otherwise> 
		<table width="81%" border="0" cellspacing="0" cellpadding="0">
<%
		if (isEdit) {
%>
		  <tr> 
			<td align="center"><input type="button" name="Button22" value="Update" onClick="validate(true);"> 
			  &nbsp; <input type="reset" name="Submit22" value="Reset"> </td>
		  </tr>

<%
		}else{
%>
		  <tr> 
			<td width="25%" nowrap class="label_right">Add New Source :&nbsp;</td>
			<td width="26%"  nowrap class="fieldleft">
			<html:select property="resourceType" styleClass="normal" >
				<html:optionsCollection name="RESOURCES_TYPE_DROP" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
			  &nbsp; 
            <input type="button" name="Button2" value="Add New Source" onClick="addResource();"></td>
			  
			<td width="26%"  nowrap class="fieldleft"><input type="button" name="Button2" value="<< Return To Facility" onClick="<%= tempBuffer%>"></td>
		  </tr>
		  <tr> 
			<td class="label_right" nowrap>&nbsp;</td>
			<td class="fieldleft"  nowrap>&nbsp;</td>
			<td class="fieldleft"  nowrap>&nbsp;</td>			
		  </tr>
<%
		}
%>
		</table>
	<b><font face="Verdana" size="2" color="#006699">Action:&nbsp;&nbsp; If You Want Add New 
Source, Click Add New Source Button. If Not Click Return to Facility Button.</font></b>	
	</c:otherwise> 
</c:choose> 
</html:form> 
<%
}catch (Exception e){
e.printStackTrace();
}
%>
</body></html>