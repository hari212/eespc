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
<script src="/eespc/js/Other.js" ></script>
<script src="/eespc/help/miscellaneoushelp.js" ></script>
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

function displayControl()

{
document.forms[0].methodToCall.value='displaycontrol';
document.forms[0].submit();	
}

function searchExist(){
	var url = "/eespc/OtherListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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
<SPAN CLASS=page_title>Miscellaneous</SPAN> <br>		
	<br>			
<html:form action="/OthersAction" enctype="multipart/form-data">
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<br>
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC" height="344">
  <tr> 
    <td width="9%" nowrap class="label_right" height="27">
    <p align="right">
    <b>
    <eespc:requiredField /> ID # </b>			
		
 

 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="97%"  nowrap class="fieldleft" colspan="6" height="27">&nbsp; 
     <eespc:displayControl paramName="facilitydesinatedId" formName="OtherSourceForm">  
    	<html:text property="facilitydesinatedId" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>        
    (Facility Designated)</td>
  </tr> 
  	  <tr>
    <td width="26%" height="19" align="right"><b>Unit Name</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="UnitName();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19">&nbsp; 
    <eespc:displayControl paramName="misname" formName="OtherSourceForm"> 		
			<html:text property="misname"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b><eespc:requiredField />Status 
    of source</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="25%" colspan="2" height="19">&nbsp;
    <eespc:displayControl paramName="ModifiedInPast" formName="OtherSourceForm"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="OTHERS_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>
			</eespc:displayControl>
			</td>
    <td width="25%" colspan="2" height="19"><b>Disconnected Year:</b></td>
    <td width="24%" colspan="2" height="19"> 
    <eespc:displayControl paramName="DisconnectedYear" formName="OtherSourceForm"> 		
			<html:text property="DisconnectedYear"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Make</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Make();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19"> &nbsp;
    <eespc:displayControl paramName="mismake" formName="OtherSourceForm"> 		
			<html:text property="mismake"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  
  <tr>
    <td width="26%" height="19" align="right"><b>Model</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Model();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19"> &nbsp;
    <eespc:displayControl paramName="mismodel" formName="OtherSourceForm"> 		
			<html:text property="mismodel"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Capacity</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Capacity();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="25%" colspan="2" height="19"> &nbsp;
    <eespc:displayControl paramName="miscapacity" formName="OtherSourceForm"> 		
			<html:text property="miscapacity"  styleClass="normal" />	
		</eespc:displayControl>	</td>
    <td width="25%" colspan="2" height="19"><b>Unit</b><b>:</b></td>
    <td width="24%" colspan="2" height="19"> 
    <eespc:displayControl paramName="miscapacityunit" formName="OtherSourceForm"> 		
			<html:text property="miscapacityunit"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Pollutant #1</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Pollutants1();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="25%" colspan="2" height="19"> &nbsp;
    <eespc:displayControl paramName="mispollutant1" formName="OtherSourceForm"> 		
			<html:text property="mispollutant1"  styleClass="normal" />	
		</eespc:displayControl>	</td>
    <td width="25%" colspan="2" height="19"><b>Maximum limit</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Maximumlimit1();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="24%" colspan="2" height="19"> 
    <eespc:displayControl paramName="mispollutant1max" formName="OtherSourceForm"> 		
			<html:text property="mispollutant1max"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="7" align="right">			

    <b>Pollutant #2</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Pollutants2();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="25%" colspan="2" height="7"> &nbsp;
    <eespc:displayControl paramName="mispollutant2" formName="OtherSourceForm"> 		
			<html:text property="mispollutant2"  styleClass="normal" />	
		</eespc:displayControl>	</td>
    <td width="25%" colspan="2" height="7">			

    <b>Maximum limit</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Maximumlimit2();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="24%" colspan="2" height="7"> 
    <eespc:displayControl paramName="mispollutant2max" formName="OtherSourceForm"> 		
			<html:text property="mispollutant2max"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="7" align="right">			

    <b>Pollutant #3</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Pollutants3();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="25%" colspan="2" height="7"> &nbsp;
    <eespc:displayControl paramName="mispollutant3" formName="OtherSourceForm"> 		
			<html:text property="mispollutant3"  styleClass="normal" />	
		</eespc:displayControl>	</td>
    <td width="25%" colspan="2" height="7">	
    <b>Maximum limit</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Maximumlimit3();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="24%" colspan="2" height="7"> 
    <eespc:displayControl paramName="mispollutant3max" formName="OtherSourceForm"> 		
			<html:text property="mispollutant3max"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Agencies With Jurisdiction</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AgenciesWithJurisdiction();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="13%" align="center" height="19"><b>DEC</b></td>
    <td width="13%" align="center" height="19"><b>DEP</b></td>
    <td width="12%" align="center" height="19"><b>DOB</b></td>
    <td width="12%" align="center" height="19"><b>DOH</b></td>
    <td width="12%" align="center" height="19"><b>FD</b></td>
    <td width="12%" align="center" height="19"><b>Others</b></td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right">&nbsp;</td>
    <td width="13%" height="19" align="center">
    <eespc:displayControl paramName="misdec" formName="OtherSourceForm"> 		
			<html:checkbox property="misdec" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="13%" height="19" align="center">
    <eespc:displayControl paramName="misdep" formName="OtherSourceForm"> 		
			<html:checkbox property="misdep" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="misdob" formName="OtherSourceForm"> 		
			<html:checkbox property="misdob" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="misdoh" formName="OtherSourceForm"> 		
			<html:checkbox property="misdoh" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="misfd" formName="OtherSourceForm"> 		
			<html:checkbox property="misfd" value="Y"/>
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="misother" formName="OtherSourceForm"> 		
			<html:text property="misother"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Permit Issue Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitIssueDate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19"> &nbsp;
    <eespc:displayControl paramName="misissuedate" formName="OtherSourceForm"> 		
			<html:text property="misissuedate"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Permit Expiration Date (<font color="#006699">mm/dd/yyyy</font>)</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitExpirationDate();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19"> &nbsp;
    <eespc:displayControl paramName="misexpirationdate" formName="OtherSourceForm"> 		
			<html:text property="misexpirationdate"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
   <tr>
    <td width="26%" height="19" align="right"><b>Note</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitExpirationDateNote();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19"> &nbsp;
    <eespc:displayControl paramName="misexpirationdateNote" formName="OtherSourceForm"> 		
			<html:text property="misexpirationdateNote"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Reporting Requirements</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ReportingRequirements();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="13%" align="center" height="19"><b>Monthly</b></td>
    <td width="13%" align="center" height="19"><b>Quarterly</b></td>
    <td width="12%" align="center" height="19"><b>Semi-Annually </b> </td>
    <td width="12%" align="center" height="19"><b>Annually</b></td>
    <td width="12%" align="center" height="19"><b>Anniversary</b></td>
    <td width="12%" height="19">&nbsp;</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right">&nbsp;</td>
    <td width="13%" height="19" align="center">
    <eespc:displayControl paramName="mismonthly" formName="OtherSourceForm"> 		
			<html:checkbox property="mismonthly" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="13%" height="19" align="center">
    <eespc:displayControl paramName="misquartly" formName="OtherSourceForm"> 		
			<html:checkbox property="misquartly" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="missemiannualy" formName="OtherSourceForm"> 		
			<html:checkbox property="missemiannualy" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="misannualy" formName="OtherSourceForm"> 		
			<html:checkbox property="misannualy" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">
    <eespc:displayControl paramName="misanniversary" formName="OtherSourceForm"> 		
			<html:checkbox property="misanniversary" value="Y"/>	
		</eespc:displayControl>	</td>
    <td width="12%" height="19" align="center">&nbsp;</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="26%">
    <p align="right"><b><%=dob%> Sign Off <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</b></td>
   <td width="74%" colspan="6" height="19">				
		<eespc:displayControl paramName="dobsignoff" formName="OtherSourceForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes <html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
  <tr>
    <td width="26%" height="19" align="right"><b>Testing Required</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestingRequired();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19">
    <eespc:displayControl paramName="mistestingrequired" formName="OtherSourceForm"> 		
			<html:radio property="mistestingrequired" value="Y" onclick="displayControl();" styleClass="normal"/>Yes 
			<html:radio property="mistestingrequired" value="N" onclick="displayControl();" styleClass="normal"/>No 
		</eespc:displayControl>	</td>
  </tr>
     <logic:equal name="OtherSourceForm" property="mistestingrequired" value="Y">
  <tr>
    <td width="26%" height="19" align="right"><b>Test Report Submitted</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TestReportSubmitted();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19">
    <eespc:displayControl paramName="mistestsubmitted" formName="OtherSourceForm"> 		
			<html:radio property="mistestsubmitted" value="Y"  styleClass="normal"/>Yes 
			<html:radio property="mistestsubmitted" value="N"  styleClass="normal"/>No 
		</eespc:displayControl>	</td>
  </tr>
   </logic:equal>
  <tr>
    <td width="26%" height="20" align="right"><b>Compliance Issue, If any</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ComplianceIssueIfany();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="20"> &nbsp;
    <eespc:displayControl paramName="complianceissue" formName="OtherSourceForm"> 		
			<html:text property="complianceissue"  styleClass="normal" />	
		</eespc:displayControl>	</td>
  </tr>
 <tr>
    <td width="26%" height="19" align="right"><b>Comments</b><input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" /><b>:</b></td>
    <td width="74%" colspan="6" height="19"> &nbsp;
      <eespc:displayControl paramName="Ocomments" formName="OtherSourceForm"> 
     <html:textarea property="Ocomments" cols="28" rows="2"/>     	
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("OthersAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("OthersAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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


<br><table width="100%" border="0" cellspacing="0" cellpadding="0">
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
		onClick="<%= ((isEdit)? "addOther(true)" : "addOther(false)") %>;">			            
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
Input and verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>        
</body>
</html>