<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.BulkOxygenPermitVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Bulk Oxygen Storage Tank</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/BulkOxygenTankScript.js" ></script>
<script src="/eespc/help/Bulkoxygenhelp.js" ></script>
<script>

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
	var url = "/eespc/BulkListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
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
</head>

<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<html:form action="/BulkOxygenStorageTankInfo" enctype="multipart/form-data"> 
<html:hidden property="methodToCall" />
<html:hidden property="id" />
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<SPAN CLASS=page_title>Bulk Oxygen Storage Tank</SPAN> <br>		
	<br>			
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#F2F2F2">
  <tr> 
    <td width="25%" class="label_right"><eespc:requiredField />ID # 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="facilityDesignatedId" formName="oxygenStorageTank"> 
			<html:text property="facilityDesignatedId" styleClass="normal" /> 
			<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
		</eespc:displayControl>
      (Facility Designated)</td>
  </tr>
  
   <tr> 
    <td class="label_right" nowrap>Year Installed 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="YearInstalled" formName="oxygenStorageTank"> 
			<html:text property="YearInstalled" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" nowrap>Capacity <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Capacity();"  onmouseout="UnTip()" />:</td>
    <td height="15" class="fieldleft">&nbsp; 
		<eespc:displayControl paramName="capacity" formName="oxygenStorageTank"> 
			<html:text property="capacity" styleClass="normal"/> 
		</eespc:displayControl>
		(Gallons)
    </td>
  </tr>
  

  <tr> 
    <td class="label_right" nowrap> <%=dob%> / Fire Department Approval Obtained 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBNYCFireDepartmentApproval();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="fireDeptApproval" formName="oxygenStorageTank"> 	
	 		<html:radio property="fireDeptApproval" styleClass="normal" value="Y" onclick="displayControl()" />Yes 
      		<html:radio property="fireDeptApproval" styleClass="normal" value="N" onclick="displayControl()" />No 
      		<html:radio property="fireDeptApproval" styleClass="normal" value="NA" onclick="displayControl()" />N/A 
		</eespc:displayControl>	  
	</td>
  </tr>
 <logic:equal name="oxygenStorageTank" property="fireDeptApproval" value="Y">  
<tr> 
    <td class="label_right" nowrap> Fire Department Approval No 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FiredepartmentapprovalNo();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="fireDeptApprovalno" formName="oxygenStorageTank"> 	
		<html:text property="fireDeptApprovalno" styleClass="normal" /> 
	 				</eespc:displayControl>	  
	</td>
</tr>
 </logic:equal>
<tr> 
    <td class="label_right" nowrap> 5 Yrs Pressure Test Performed? 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YrsPressuretestPerformed();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="pressuretest" formName="oxygenStorageTank"> 	
	 		<html:radio property="pressuretest" styleClass="normal" value="Y" onclick="displayControl()" />Yes 
      		<html:radio property="pressuretest" styleClass="normal" value="N" onclick="displayControl()" />No
      		<html:radio property="pressuretest" styleClass="normal" value="N/A" onclick="displayControl()" />N/A 
		</eespc:displayControl>	  
	</td>
  </tr>
  
  <logic:equal name="oxygenStorageTank" property="pressuretest" value="Y">  
<tr> 
    <td class="label_right" nowrap> Last Test Date (<font color="#006699">mm/dd/yyyy</font>) 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Lasttestdate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="lasttestdate" formName="oxygenStorageTank"> 	
		<html:text property="lasttestdate" styleClass="normal" /> 
	 				</eespc:displayControl>	  
	</td>
</tr>
 
<tr> 
    <td class="label_right" nowrap> Next Test Date (<font color="#006699">mm/dd/yyyy</font>) 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Nexttestdate();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="nexttestdate" formName="oxygenStorageTank"> 	
		<html:text property="nexttestdate" styleClass="normal" /> 
	 				</eespc:displayControl>	  
	</td>
</tr>

<tr> 
    <td class="label_right" nowrap>Note 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Nexttestdatenote();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="nexttestdatenote" formName="oxygenStorageTank"> 	
		<html:text property="nexttestdatenote" styleClass="normal" /> 
	 	</eespc:displayControl>	  
	</td>
</tr>

 </logic:equal>
  
<tr> 
    <td class="label_right"><eespc:requiredField />Status of Source<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
			<eespc:displayControl paramName="ModifiedInPast" formName="oxygenStorageTank"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
 											  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="oxygenStorageTank"> 
			<html:text property="DisconnectedYear" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" /> 
		</eespc:displayControl>
	   </td>
  </tr>
 <tr> 
    <td class="label_right" valign="top" >Comments<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  >&nbsp;
		<eespc:displayControl paramName="bcomments" formName="oxygenStorageTank"> 
			<html:textarea property="bcomments" cols="30" rows="2"/>			
		</eespc:displayControl>	  											  
	</td>
  </tr> 

</table>

		
<logic:equal name="showPermit" value="Y">            
<br>   
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dob%> Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead> Permit #</td>
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                
                <td   class=columnhead>Edit</td>				
                <td   class=columnhead>Delete</td>
              </tr>
<%
	List inspectionList = (List)session.getAttribute("BLK_O2_PERMIT_LIST");
	boolean toEditDobPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_BLK_O2_PERMIT_LIST"));				
	if (inspectionList != null){
		int size = inspectionList.size();
		for (int i=0; i < size; i++)
		{
			BulkOxygenPermitVo inspVo = (BulkOxygenPermitVo)inspectionList.get(i);
			boolean isEditable = false;
			int dobPermitId = inspVo.getId();
			String dobPermitIdStr = dobPermitId +"";
			String tempStr = (String)request.getAttribute("hdnPermitId");
//			out.println(tempStr + "==" + dobPermitIdStr + ", " + toEditDobPermit);
			if (null != tempStr && 	tempStr.equalsIgnoreCase(dobPermitIdStr) && toEditDobPermit)
			{
				isEditable = true;
			}
			//out.println(isEditable +""	);
              out.println("<tr class=\"evenRowStyle\">");
                out.println("<td  nowrap class=\"fieldleft\">");
				out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dobPermit", inspVo.getPermitNumber(),null));										
//				out.println(	inspVo.getPermitNumber());
				out.println("</td>");
                out.println("<td nowrap class=\"fieldleft\">");
				out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dobIssueDate", inspVo.getIssueDateStr(),null));				
//				out.println(inspVo.getIssueDateStr());
				out.println("</td>");
              
				out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
				if (!isEditable){
					out.println("<a href=\"javascript:editDobPermit('");
					out.println(dobPermitId);
					out.println("');\">");
					out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
				}else{
					out.println("&nbsp;");
				}
				out.println("</td>");				
				
                out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
				if (!isEditable){
				 if(session.getAttribute("DELETE_PERMISSION")!=null)
				{
					out.println("<a href=\"javascript:deleteDobPermit('");
					out.println(dobPermitId);
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
			if (!toEditDobPermit){ //show the following only if it is not edit	
%>				  
              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="dobPermit" styleClass="normal" /> 
				</td>
                <td width="26%" align="center"  nowrap class="fieldleft">
					<html:text property="dobIssueDate" styleClass="normal" size="10" maxlength="10"/> 
                </td>
             
				<td class="fieldleft" colspan=2>&nbsp;</td>
              </tr>
              <tr align="right" class="evenRowStyle"> 
                <td colspan=5 >
					<input type="button" name="Button23" value="Add " onClick="addPermit();">
				</td>
              </tr>
<%
			}else{ // if !toEditDobPermit loop
%>
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=11 >
				<input type="button" name="Button23" value="Update " onClick="updateDob();">
				</td>
              </tr>
<%			
			}
%>			  
			  
            </table></TD>
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
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%                String file_path=String.valueOf(request.getAttribute("FILE_PATH")).replaceAll("&", "%26");                           out.println("BulkOxygenStorageTankInfo.do?methodToCall=back&pathname="+file_path);                   %>">
    <b><font size="2" color="#FFFFFF">BACK</font></b></a></font></td>
  </tr>
</table>
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("BulkOxygenStorageTankInfo.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
<html:file property="filename" />

<input type="button" value="Upload" name="B1" onClick="fileupload();" >

<hr>
<br>

</logic:equal>
<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  align="right"><input type="button" name="Button22" value="<< Return To Building" onClick="returnToBuilding();">
      		</td>		  
			<logic:notEqual name="isComponentEditable" value="N">
<%
			String tempStr = (String)request.getAttribute("isItForEdit");
			boolean  isEdit = false;
			if (null != tempStr && tempStr.equalsIgnoreCase("Y")){
				isEdit = true;
			}
%>						
            <td  align="right"><input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "update()" : "add()") %>;">
      		</td>
            <td >&nbsp;
              <input type="reset" name="Submit22" value="Reset">
			</td>
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
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and Veified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>
</body>
</html>