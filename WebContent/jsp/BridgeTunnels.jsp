<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.BridgeTunnelPermitVo,com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Bridge Tunnels</title>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/BridgeTunnels.js" ></script>
<script src="/eespc/help/tunnelbridgeshelp.js" ></script>
<script>

function disConnect()
{

if(document.forms[0].ModifiedInPast.value==-1 || document.forms[0].ModifiedInPast.value==2 || document.forms[0].ModifiedInPast.value==3 || document.forms[0].ModifiedInPast.value==4)
{
document.forms[0].DisconnectedYear.disabled=false;
}
else
{
document.forms[0].DisconnectedYear.value="";
document.forms[0].DisconnectedYear.disabled=true;
}
}

function searchExist(){
	var url = "/eespc/BridgeListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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
<body>
<script src="tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="tooltip/tip_centerwindow.js" type="text/javascript"></script>

<html:form action="/BridgeTunnelInfo" enctype="multipart/form-data" > 
<html:hidden property="methodToCall" />
<html:hidden property="id" />
<html:hidden property="type" />
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<SPAN CLASS=page_title> <bean:write name="BRIDGE_TUNNEL_LABEL" /></SPAN> <br>		
	<br>			
<table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#F2F2F2" style="border-collapse: collapse">
  <tr> 
    <td width="25%" class="label_right"><eespc:requiredField />ID # 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();" onmouseout="UnTip()">: </td>
    <td class="fieldleft"  nowrap>&nbsp;
		<eespc:displayControl paramName="BT_facilityDesignatedId" formName="bridgeTunnel"> 
			<html:text property="BT_facilityDesignatedId" styleClass="normal" /> 
			       <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
		</eespc:displayControl>
	 (Facility Designated)
	 </td>
  </tr>
  
      <tr> 
    <td class="label_right" nowrap>Year Installed 
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();" onmouseout="UnTip()">: </td>
    <td class="fieldleft"  nowrap>&nbsp; 
		<eespc:displayControl paramName="YearInstalled" formName="bridgeTunnel"> 
			<html:text property="YearInstalled" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
  
 
  
     <tr> 
    <td class="label_right"><eespc:requiredField />Status of Source<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap>&nbsp;
		 <eespc:displayControl paramName="ModifiedInPast" formName="bridgeTunnel"> 	
			<html:select property="ModifiedInPast" styleClass="normal" onchange="disConnect();" >
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name"  /> 
			</html:select>		
		</eespc:displayControl>	  											  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="bridgeTunnel"> 
			<html:text property="DisconnectedYear" styleClass="normal" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	   </td>
  </tr>
  <tr> 
    <td width="25%" class="label_right"><%=dob%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap>&nbsp;
		<eespc:displayControl paramName="dobPermitNumber" formName="bridgeTunnel"> 
		<html:text property="dobPermitNumber" styleClass="normal" /> 
		</eespc:displayControl>
	
	 </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="376"><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="568">&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="bridgeTunnel"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes <html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  <tr> 
    <td width="25%" class="label_right"><%=dob%> Issue Date (<font color="#006699">mm/dd/yyyy</font>)<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBIssueDate();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap>&nbsp;
		<eespc:displayControl paramName="dobIssueDate" formName="bridgeTunnel"> 
		<html:text property="dobIssueDate" styleClass="normal" /> 
		
		</eespc:displayControl>
	
	 </td>
  </tr>
  <tr> 
    <td width="25%" class="label_right">Comments<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap>&nbsp;
		<eespc:displayControl paramName="B_comments" formName="bridgeTunnel"> 
			<html:textarea property="B_comments" cols="40" rows="3"/> 
		</eespc:displayControl>
	
	 </td>
  </tr>





</table>	
<logic:equal name="showPermit" value="Y">				
<br>
<table border="1" cellpadding="0" cellspacing="0"  style="width: 100%; table-layout: fixed; border-collapse: collapse" bordercolor="#111111" id="AutoNumber1">
  <tr>

    <td width="100%">
   <div style="width: auto; overflow: auto;">
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>DOT Permit Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOTpermitInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>&nbsp;</td>
                <td colspan="5"   class=columnhead>Permit </td>
                <td colspan="5"   class=columnhead>Inspection Details</td>
                <td colspan="3"   class=columnhead>Insurance</td>
                <td colspan="0"   class=columnhead>&nbsp;</td>
                <td colspan="0"   class=columnhead>&nbsp;</td>

              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Year</td>
                <td   class=columnhead> Number</td> <td   class=columnhead> File No</td>
                <td   class=columnhead>Issued Date<br>(<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Last Inspection Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Filing Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Inspected By</td>
                <td   class=columnhead>Next Inspection Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead> Effective Date<br> (<font color="#006699">mm/dd/yyyy</font>) </td>
                <td   class=columnhead>Expiration Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
              </tr>
              <%
			List dotPermitList = (List)session.getAttribute("BRIDGE_TUNNEL_DOT_PERMIT");
			boolean toEditDotPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DOT_PERMIT"));
			if (dotPermitList != null){
				int size = dotPermitList.size();
				for (int i=0; i < size; i++)
				{
					BridgeTunnelPermitVo inspVo = (BridgeTunnelPermitVo)dotPermitList.get(i);
					boolean isEditable = false;
					//TODO for checking the hdnPermitInfo && toEditDotPermit -> set the isEditable to true
						int dotPermitId = inspVo.getId();
						String dotPermitIdStr = dotPermitId +"";
						String tempStr = (String)request.getAttribute("hdnPermitId");
						
						
						//out.println(""+toEditDotPermit +"/"+tempStr);
						//null != tempStr &&
						if (null != tempStr && tempStr.equalsIgnoreCase(dotPermitIdStr) && toEditDotPermit)
						{
							isEditable = true;
						}
					  out.println("<tr class=\"evenRowStyle\">");
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_OPTION, isEditable, "dotYear", UtilityObject.checkNullAndFill(inspVo.getYear(),""), "YEARS"));
//						out.println(inspVo.getYear());
						out.println("</td>");
						
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotPermitNumber", UtilityObject.checkNullAndFill(inspVo.getPermitNumber(),""),null));						
//						out.println(inspVo.getPermitNumber());
						out.println("</td>");	
								
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotFileNo", UtilityObject.checkNullAndFill(inspVo.getFileNo(),""),null));						
//						out.println(inspVo.getFileNo());
						out.println("</td>");	
								
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotIssueDate", UtilityObject.checkNullAndFill(inspVo.getIssueDate(),""),null));												
//						out.println(inspVo.getIssueDate());
						out.println("</td>");	
									
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotExpDate", UtilityObject.checkNullAndFill(inspVo.getExpirationDate(),""),null));
//						out.println(inspVo.getExpirationDate());
						out.println("</td>");
						
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotExpDateNote", UtilityObject.checkNullAndFill(inspVo.getDotExpDateNote(),""),null));
//						out.println(inspVo.getDotExpDateNote());
						out.println("</td>");	
									
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotLastInspDate", UtilityObject.checkNullAndFill(inspVo.getLastInspDate(),""),null));						
//						out.println(inspVo.getLastInspDate());
						out.println("</td>");				
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotFillingDate", UtilityObject.checkNullAndFill(inspVo.getFillingDate(),""),null));												
//						out.println(inspVo.getFillingDate());
						out.println("</td>");				
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotByWho", UtilityObject.checkNullAndFill(inspVo.getByWho(),""),null));												
//						out.println(inspVo.getByWho());
						out.println("</td>");				
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotNextInspDate", UtilityObject.checkNullAndFill(inspVo.getNextInspDate(),""),null));												
//						out.println(inspVo.getNextInspDate());
						out.println("</td>");	
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotNextInspDateNote", UtilityObject.checkNullAndFill(inspVo.getDotNextInspDateNote(),""),null));												
//						out.println(inspVo.getDotNextInspDateNote());
						out.println("</td>");			
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotInsIssueDate", UtilityObject.checkNullAndFill(inspVo.getInsIssueDate(),""),null));												
//						out.println(inspVo.getInsIssueDate());
						out.println("</td>");				
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotInsExpDate", UtilityObject.checkNullAndFill(inspVo.getInsExpDate(),""),null));												
//						out.println(inspVo.getInsExpDate());
						out.println("</td>");
						out.println("<td nowrap class=\"fieldleft\">");
						out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isEditable, "dotInsExpDateNote", UtilityObject.checkNullAndFill(inspVo.getDotInsExpDateNote(),""),null));												
//						out.println(inspVo.getDotInsExpDateNote());
						out.println("</td>"); 
						
                		out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isEditable){
							out.println("<a href=\"javascript:editDotPermit('");
							out.println(dotPermitId);
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
							out.println("<a href=\"javascript:deleteDotPermit('");
							out.println(dotPermitId);
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
			if (!toEditDotPermit){ //show the following only if it is not edit
%>
              <tr class="oddRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft"> <html:select property="dotYear" styleClass="normal" > 
                  <html:optionsCollection name="YEARS" property="dropDownValues" value="value" label="name" /> 
                  </html:select> </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotPermitNumber" type="text" class="normal" id="dotPermitNumber" size="20"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotFileNo" type="text" class="normal" id="dotFileNo" size="20"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotIssueDate" type="text" class="normal" id="dotIssueDate" size="11" maxlength="10">                 </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotExpDate" type="text" class="normal" id="dotExpDate" size="11" maxlength="10">                </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotExpDateNote" type="text" class="normal" id="dotExpDateNote" size="11"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotLastInspDate" type="text" class="normal" id="dotLastInspDate" size="11" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotFillingDate" type="text" class="normal" id="dotFillingDate" size="11" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotByWho" type="text" class="normal" id="dotByWho" size="20"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotNextInspDate" type="text" class="normal" id="dotNextInspDate" size="11" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotNextInspDateNote" type="text" class="normal" id="dotNextInspDateNote" size="11"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotInsIssueDate" type="text" class="normal" id="dotInsIssueDate" size="11" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotInsExpDate" type="text" class="normal" id="dotInsExpDate" size="11" maxlength="10"></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><input name="dotInsExpDateNote" type="text" class="normal" id="dotInsExpDateNote" size="11"></td>
                <td colspan=2 >

              </tr>
             
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=16>
				<input type="button" name="Button23" value="Add " onClick="addDot(false);">
				</td>
              </tr>
<%
			}else{ // if !toEditDotPermit loop
%>
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=16 >
				<input type="button" name="Button23" value="Update " onClick="addDot(true);">
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
</div>
    </td>
     </tr>
</table>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=String.valueOf(request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("BridgeTunnelInfo.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("BridgeTunnelInfo.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
</logic:equal>
<br>




   <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Building" onClick="returnToBuilding();">
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "updateBridgeTunnel();" : "addBridgeTunnel();") %>;">			
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
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and Verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>
</body>
</html>