<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PlaceofAssemblyPermitVo,com.eespc.tracking.bo.FacilityVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Place of Assembly</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/PlaceofAssembly.js" ></script>
<script src="/eespc/help/PlaceofAssemblyhelp.js" ></script>
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

function displayControl()

{
document.forms[0].methodToCall.value='displaycontrol';
document.forms[0].submit();	
}

function searchExist(){
	var url = "/eespc/PlaceofAssemblyListInfo.do?formId=id";
	window.open(url, "_blank");
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


<body>
<script src="tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="tooltip/tip_centerwindow.js" type="text/javascript"></script>
<html:form action="/PlaceofAssemblyAction" enctype="multipart/form-data">
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<br>
<SPAN CLASS="page_title">Place of Assembly (PA)</SPAN>
<br>
<br>

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="344">
 <tr> 
    <td width="26%" nowrap class="label_right" height="27"><p align="right"><b>
    <eespc:requiredField /> ID</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" /><b>:</b></td>    
    <td width="80%"  nowrap class="fieldleft" colspan="4" height="27">&nbsp; 
     <eespc:displayControl paramName="facilitydesignatedid" formName="PlaceofAssemblyForm">  
    	<html:text property="facilitydesignatedid" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>(Facility Designated)
    </td>
  </tr>
  
 <tr>
    <td width="43%" height="19" align="right"><b><eespc:requiredField />PA Type</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="paType();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
     <eespc:displayControl paramName="paType" formName="PlaceofAssemblyForm"> 		
		<html:select property="paType" styleClass="normal" >
			<html:optionsCollection name="PA_TYPE" property="dropDownValues" value="value" label="name" /> 
		</html:select>
	</eespc:displayControl>
	</td>
  </tr>
  
  
  <tr>
    <td width="43%" height="19" align="right"><b>Location</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="location();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="location" formName="PlaceofAssemblyForm"> 		
			<html:text property="location"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr>
  
   <tr>
    <td width="43%" height="19" align="right"><b>Seating Capacity</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="seatingCap();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="seatingCapacity" formName="PlaceofAssemblyForm"> 		
			<html:text property="seatingCapacity"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr> 
  
  <tr> 
    <td width="43%" height="19" align="right"><b><%=dob%> Filing Status
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobFiling();"  onmouseout="UnTip()" />:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;				
	<eespc:displayControl paramName="dobfiling" formName="PlaceofAssemblyForm"> 		
			<html:radio property="dobfiling" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="dobfiling" value="N" onclick="displayControl();"/>No 
			<html:radio property="dobfiling" value="N/A" onclick="displayControl();"/>N/A
	</eespc:displayControl>	 	
	</td>
  </tr>
  
   <logic:equal name="PlaceofAssemblyForm" property="dobfiling" value="Y"> 
  <tr>
    <td width="43%" height="19" align="right"><b><%=dob%> Filing/Job No.
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="crdobpermit();"  onmouseout="UnTip()"/>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="dobpermit" formName="PlaceofAssemblyForm"> 		
			<html:text property="dobpermit"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr>


  <tr> 
    <td width="43%" height="19" align="right"><b><%=dob%> Signed Off
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobsignoff();"  onmouseout="UnTip()" />:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;				
	<eespc:displayControl paramName="dobsignoff" formName="PlaceofAssemblyForm"> 		
			<html:radio property="dobsignoff" value="Y" onclick="displayControl();"/>Yes
			<html:radio property="dobsignoff" value="N" onclick="displayControl();"/>No 
	</eespc:displayControl>	 	
	</td>
  </tr>

  
   	
 <tr>
    <td width="43%" height="19" align="right"><b><%=dob%> Permit No.</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobPermitnum();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="dobPermitnumber" formName="PlaceofAssemblyForm"> 		
		<html:text property="dobPermitnumber"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr>  
  
  
  <tr>
    <td width="43%" height="19" align="right"><b><%=dob%> Approved Plan Available?</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobPlanApproval();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="dobPlan" formName="PlaceofAssemblyForm"> 		
			<html:radio property="dobPlan" value="Y"/>Yes
			<html:radio property="dobPlan" value="N"/>No 	
		</eespc:displayControl>
	</td>
  </tr>
    </logic:equal>
    
    
  <tr>
    <td width="43%" height="19" align="right"><b>FD Permit Obtained?</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="fdPermit();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="fdPermitObtained" formName="PlaceofAssemblyForm"> 		
			<html:radio property="fdPermitObtained" value="Y"/>Yes
			<html:radio property="fdPermitObtained" value="N"/>No 	
	</eespc:displayControl>
	</td>
  </tr>

  <tr>
    <td width="43%" height="19" align="right"><b>Any Open Violation?</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="openViolation();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="openViolation" formName="PlaceofAssemblyForm"> 
		<html:radio property="openViolation" value="Y"/>Yes 	
		<html:radio property="openViolation" value="N"/>No
	</eespc:displayControl>
	</td>
  </tr>
  
  <tr>
    <td width="43%" height="19" align="right"><b>Violation Type</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="violationType();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="violationType" formName="PlaceofAssemblyForm"> 		
		<html:select property="violationType" styleClass="normal" >
			<html:optionsCollection name="VIOLATION_TYPE" property="dropDownValues" value="value" label="name" /> 
		</html:select>
	</eespc:displayControl>
	</td>
  </tr>

  <tr>
    <td width="43%" height="19" align="right"><b>Violation No.</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="violationNo();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="57%" colspan="4" height="19">&nbsp;
    <eespc:displayControl paramName="violationNum" formName="PlaceofAssemblyForm"> 		
			<html:text property="violationNum"  styleClass="normal" />	
	</eespc:displayControl>
	</td>
  </tr>
	
  <tr>
    <td width="43%" height="19" align="right"><b>Note</b>
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="note();"  onmouseout="UnTip()" /><b>:</b>
    </td>
    <td width="57%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="note" formName="PlaceofAssemblyForm"> 
     	<html:textarea property="note" cols="28" rows="2"/>     	
    </eespc:displayControl>     
  	</td>
  </tr>
  </table>
&nbsp;<p>
<br>
</p>
<br>
<logic:equal name="isComponentEditable" value="N">
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dob%> Permit Information 
      </FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
             <tr class="oddRowStyle"> 
                <td   align="center" class=columnhead>Issue Date<br>(<b><font color="#006699">mm/dd/yyyy</font></b>)</td>                
                <td   align="center" class=columnhead>Expiration Date<br>(<b><font color="#006699">mm/dd/yyyy</font></b>)</td>          
                <td   align="center" class=columnhead>Note</td>               
                <td   align="center" class=columnhead>Edit</td>
                <td   align="center" class=columnhead>Delete</td>
              </tr>
              
   <%
			List dobInspectionList = (List)request.getAttribute("PA_DOB_PERMIT");
			boolean toEditDobPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DOB_PERMIT"));
			
			if (dobInspectionList != null){
				int size = dobInspectionList.size();
				for (int i=0; i < size; i++)
				{
					PlaceofAssemblyPermitVo paPermitVo= (PlaceofAssemblyPermitVo)dobInspectionList.get(i);
					boolean isDobPermitEditable = false;
					int permitId = paPermitVo.getId();
					String permitIdStr = permitId +"";
					String tempStr = (String)request.getAttribute("hdnPermitId");
		//					out.println(tempStr + "==" + permitIdStr + ", " + toEditDobPermit);
		
					if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDobPermit)
					{
						isDobPermitEditable = true;
					}
					
					
					
					
					out.println("<tr class=\"evenRowStyle\">");
									    
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobIssueDate", paPermitVo.getIssueDateStr(), null));
					out.println("</td>");
								    
					out.println("<td  nowrap class=\"fieldleft\">");
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobExpirationDate", paPermitVo.getExpirationDateStr(), null));
					out.println("</td>");
					
					out.println("<td nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDobPermitEditable, "dobExpirationNote", paPermitVo.getDobExpirationNote(),null));
					out.println("</td>");
					
					
					out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
					if (!isDobPermitEditable){
					
						out.println("<a href=\"javascript:editDobPermit('");
						out.println(permitId);
						out.println("');\">");
						out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
					}
					else{
					
						out.println("&nbsp;");
					}
					out.println("</td>");

					out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
					if (!isDobPermitEditable){
					
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deleteDobPermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
							}
						else{				
							out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\">");
							}
							}
						else{
							out.println("&nbsp;");
						}
					out.println("</td>");
					out.println("</tr>");	
						
		}
		}


if (!toEditDobPermit){ //show the following only if it is not edit							
%>	
              
              <tr class="evenRowStyle">                              
				<td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobIssueDate" type="text" class="normal" id="dobIssueDate" size="10" maxlength="10"></td>
                <td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobExpirationDate" type="text" class="normal" id="dobExpirationDate" size="10" maxlength="10"></td>                
                <td  width="26%" align="center"  nowrap class="fieldleft"><input name="dobExpirationNote" type="text" class="normal" id="dobExpirationNote" size="20"></td>                
                <td  class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
<%

	}// if !toEditDobPermit loop

%>
			  <tr align="right" class="evenRowStyle"> 
                <td colspan=5>
                <input type="button" name="Button22"  value="<%= ((toEditDobPermit)? "Update" : "Add") %>" onClick="<%= ((toEditDobPermit)? "addDob(true)" : "addDob(false)") %>;">
                </td>
              </tr>


			</table>
			</TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>

<br>

<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b>Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("PlaceofAssemblyAction.do?methodToCall=back&pathname="+file_path);        %>">
    <b><font size="2" color="#FFFFFF">BACK</font></b></a></font></td>
  </tr>
</table>

<br>
</p>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#006699" width="100%" id="AutoNumber1">
  <tr>
    <td width="20%" bgcolor="#FFFFCC" align="left"><b>
    <font size="2" face="Verdana">File/Folder Name</font></b></td>
    <td width="20%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Type</font></b></td>
    <td width="20%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">File Size</font></b></td>
    <td width="20%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Date Modified</font></b></td>
    <td width="12%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Rename</font></b></td>
    <td width="28%" bgcolor="#FFFFCC" align="center"><b>
    <font size="2" face="Verdana">Delete</font></b></td>
  </tr>
  <% 
  List folderList = (List)request.getAttribute("FILE_LIST");

		if (folderList != null)
		{
				int size = folderList .size();
				for (int i=0; i < size; i++)
				{
				String arr[]=(String[])folderList .get(i);
  %>
  <tr>
    <td width="20%" align="left"><b><font color="#FF3399" size="2">
    <a href="<%if(arr[0].equals("folder"))    {    out.println("PlaceofAssemblyAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
    <td width="20%" align="center"><span style="font-variant: small-caps"><%=arr[0]%>&nbsp;</span></td>
    <td width="20%" align="center"><%
    if(arr[4]!=null)
    {
    out.println(arr[4]);
    }
    
    
    %>&nbsp;</td>
    <td width="20%" align="center"><%=arr[3]%>&nbsp;</td>    
    <td width="12%" align="center"><a href="javascript:editfile('<%=arr[2].replaceAll("'","x***x")%>','<%=(String)request.getAttribute("FILE_PATH")%>');">
    <img border="0" src="images/edit.jpg" width="42" height="13" ></a></td>
    <td width="28%" align="center"><a href="javascript:deletefile('<%=arr[2].replaceAll("'","x***x")%>','<%=(String)request.getAttribute("FILE_PATH")%>');">
    <img border="0" src="images/delete.jpg" width="56" height="16"></a></td>
  </tr>
<%
}
}
%>
</table>

<p align="center">

<br> 
<html:file property="filename"/>

<input type="button" value="Upload" name="B1" onClick="fileupload();" >

<hr>

</logic:equal>	

<br>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Building" 
            onClick="returnToBuilding();">
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addPlaceofAssembly(true)" : "addPlaceofAssembly(false)") %>;">			            
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
</logic:present>

</html:form>
<b><font size="2" face="Verdana" color="#006699">Action: When all Sources are 
Completed, Facility Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>        
</body>
</html>