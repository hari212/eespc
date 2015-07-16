<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.ElevatorPermitVo,com.eespc.tracking.bo.FacilityVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New Elevators</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/FireAlarm.js" ></script>
<script src="/eespc/help/FireAlarmhelp.js" ></script>
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
	var url = "/eespc/FireAlarmListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, "_blank");
}

function crwstatus()
{
    if(document.forms[0].status.value ==-1 ||
                        document.forms[0].status.value ==2 ||
                        document.forms[0].status.value ==3 ||
                        document.forms[0].status.value ==4)
                        {
                        document.forms[0].disconnectedyear.disabled =false; 
			            }
             else
             {
                        document.forms[0].disconnectedyear.disabled =true;
                        document.forms[0].disconnectedyear.value="";
              }
  
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
<SPAN CLASS=page_title>Fire Alarm</SPAN> <br>		
	<br>			
<html:form action="/FireAlarmAction" enctype="multipart/form-data">
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="344">
 <tr> 
    <td width="16%" nowrap class="label_right" height="27">
    <p align="right">
    <b>
    <eespc:requiredField /> ID # </b>	
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" /><b>:</b></td>
    
    <td width="90%"  nowrap class="fieldleft" colspan="4" height="27">&nbsp; 
     <eespc:displayControl paramName="facilitydesignatedid" formName="FireAlarmForm">  
    	<html:text property="facilitydesignatedid" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>        
    (Facility Designated)</td>
  </tr>
   <tr>
    <td width="33%" height="19" align="right"><b>Year Installed</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="year_installed();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="yearinstalled" formName="FireAlarmForm"> 		
			<html:text property="yearinstalled"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  
  
   <tr>
    <td width="33%" height="19" align="right" valign="top"><b>
    <eespc:requiredField />Status 
    of Source</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="statusofsource();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="26%" height="19">&nbsp;
    <eespc:displayControl paramName="statusOfSource" formName="FireAlarmForm"> 
			<html:select property="statusOfSource" styleClass="normal" onchange="crwstatus()">
				<html:optionsCollection name="FIREALARM_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>
			</eespc:displayControl>
			</td>
    <td width="15%" colspan="2" height="19">
    <p align="right"><b>Disconnected Year:</b></td>
    <td width="26%" height="19"> 
    <eespc:displayControl paramName="disconnectedYear" formName="FireAlarmForm"> 		
			<html:text property="disconnectedYear"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
 
<tr>
    <td width="33%" height="19" align="right"><b>Fire Alarm Type</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="typeoffa();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="typeofFAInstalled" formName="FireAlarmForm"> 		
			<html:select property="typeofFAInstalled" styleClass="normal" >
				<html:optionsCollection name="FIREALARM_TYPE" property="dropDownValues" value="value" label="name" /> 
			</html:select>
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="33%" height="19" align="right"><b><%=dob%> Approval Required</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobapprovalrequired();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="dobApproval" formName="FireAlarmForm"> 		
				<html:radio property="dobApproval" value="Y" onclick="displayControl();"/>Yes
				<html:radio property="dobApproval" value="N" onclick="displayControl();"/>No 	
				<html:radio property="dobApproval" value="N/A" onclick="displayControl();"/>N/A
		</eespc:displayControl>	</td>
  </tr>
  
  
<logic:equal name="FireAlarmForm" property="dobApproval" value="Y"> 
  <tr>
    <td width="33%" height="19" align="right"><b><%=dob%> Filing/Job #</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobfilingNum();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
   <eespc:displayControl paramName="dobFiling" formName="FireAlarmForm"> 		
			<html:text property="dobFiling"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>  
<tr>
    <td width="33%" height="19" align="right"><b>Job Filing Date (<font color="#006699">mm/dd/yyyy</font>) </b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="approvaldate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="approvalDate" formName="FireAlarmForm"> 		
			<html:text property="approvalDate"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
   <tr>
    <td width="33%" height="19" align="right"><b><%=dob%> Signoff</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="dobfilingNum();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="dobSignoff" formName="FireAlarmForm"> 		
				<html:radio property="dobSignoff" value="Y" onclick="displayControl();"/>Yes
				<html:radio property="dobSignoff" value="N" onclick="displayControl();"/>No 	
				<html:radio property="dobSignoff" value="N/A" onclick="displayControl();"/>N/A
		</eespc:displayControl></td>
  </tr>
 </logic:equal>
	  <tr>
    <td width="33%" height="19" align="right"><b>FD Approval Required</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="fdapproval_required();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="fdApproval" formName="FireAlarmForm"> 		
				<html:radio property="fdApproval" value="Y" onclick="displayControl();" />Yes <html:radio property="fdApproval" value="N" onclick="displayControl();" />No 	
		</eespc:displayControl></td>
  </tr>
<logic:equal name="FireAlarmForm" property="fdApproval" value="Y"> 
<tr>
    <td width="33%" height="19" align="right"><b>FD Approval Date (<font color="#006699">mm/dd/yyyy</font>) </b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="fdapprovaldate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="fdApprovalDate" formName="FireAlarmForm"> 		
			<html:text property="fdApprovalDate"  styleClass="normal" />	
		</eespc:displayControl></td>
  </tr>
   </logic:equal>
    <tr>
    <td width="33%" height="19" align="right"><b>Any Other Agency Approval Required</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="otheragency();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="agencyApproval" formName="FireAlarmForm"> 		
				<html:radio property="agencyApproval" value="Y" onclick="displayControl();"/>Yes
				<html:radio property="agencyApproval" value="N" onclick="displayControl();"/>No 	
		</eespc:displayControl></td>
  </tr>
<logic:equal name="FireAlarmForm" property="agencyApproval" value="Y"> 
<tr>
    <td width="33%" height="19" align="right"><b>Agency Name </b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="agencyname();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
   <eespc:displayControl paramName="agencyName" formName="FireAlarmForm"> 		
			<html:text property="agencyName"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
<tr>
    <td width="33%" height="19" align="right"><b>Agency Approval Date (<font color="#006699">mm/dd/yyyy</font>) </b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="agencyapprovaldate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19">&nbsp; 
    <eespc:displayControl paramName="agencyApprovalDate" formName="FireAlarmForm"> 		
			<html:text property="agencyApprovalDate"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
 <tr>
    <td width="33%" height="19" align="right"><b>Agency Approval No </b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="agencyapprovalno();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19"> &nbsp;
    <eespc:displayControl paramName="agencyApprovalNo" formName="FireAlarmForm"> 		
			<html:text property="agencyApprovalNo"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>	
    
 </logic:equal>

  <tr>
    <td width="33%" height="19" align="right"><b>Comments</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="frcomments();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="67%" colspan="4" height="19"> &nbsp;
      <eespc:displayControl paramName="comments" formName="FireAlarmForm"> 
     <html:textarea property="comments" cols="28" rows="2"/>     	
    </eespc:displayControl>   	</td>
  </tr>


  </table>
<br>
<logic:equal name="isComponentEditable" value="N">


<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />
<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("FireAlarmAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("FireAlarmAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" 
		onClick="<%= ((isEdit)? "addFireAlarm(true)" : "addFireAlarm(false)") %>;">			            
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
<b><font size="2" face="Verdana" color="#006699">Action: When all Sources are Completed, Facility Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>        
</body>
</html>