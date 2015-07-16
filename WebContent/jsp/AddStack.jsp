<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>NewStack</title>
<html:base />
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script src="/eespc/js/CommonScript.js" ></script>
<script src="/eespc/js/Stack.js" ></script>
<script>
function getfuelrate()
{
if(document.forms[0].typeoffuel[0].checked)
{
var florate=document.forms[0].totalcapacity.value*450;
document.forms[0].flowRate.value=parseFloat(florate).toFixed(2);
var velo=((document.forms[0].flowRate.value)*3.05)/((document.forms[0].diameter.value) * (document.forms[0].diameter.value))
if(velo==Infinity)
{
	document.forms[0].velocity.value=null;
}
else
document.forms[0].velocity.value=parseFloat(velo).toFixed(2);
}
else if(document.forms[0].typeoffuel[1].checked )
{
var florate=document.forms[0].totalcapacity.value*225;
document.forms[0].flowRate.value=parseFloat(florate).toFixed(2);
var velo=((document.forms[0].flowRate.value)*3.05)/((document.forms[0].diameter.value) * (document.forms[0].diameter.value))
if(velo==Infinity)
{
	document.forms[0].velocity.value=null;
}
else
document.forms[0].velocity.value=parseFloat(velo).toFixed(2);
}
}

function getvelocity()
{
var velo=((document.forms[0].flowRate.value)*3.05)/((document.forms[0].diameter.value) * (document.forms[0].diameter.value))
if(velo==Infinity)
{
	document.forms[0].velocity.value=null;
}
else
document.forms[0].velocity.value=parseFloat(velo).toFixed(2);
}
function disConnect()
{
}

function searchExist(){
	var url = "/eespc/stackSearchListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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



<script src="/eespc/help/stackhelp.js" ></script>
</head>

<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<SPAN CLASS=page_title>Stack</SPAN> <br>		
	&nbsp;<br>			
<html:form action="/StackInfo" enctype="multipart/form-data"> 
<html:hidden property="methodToCall" value="" />
<html:hidden property="bId" />
<html:hidden property="id" />

<input name="hdnPermitId" type="hidden" value="<%=request.getParameter("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">		
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
  <tr> 
    <td class="label_right" width="323" align="right"><eespc:requiredField />ID # <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td height="20"  nowrap class="fieldleft" width="566">&nbsp; 
		<eespc:displayControl paramName="facilityStackId" formName="stackInfo"> 
			<html:text property="facilityStackId"  styleClass="normal" />
			<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
		</eespc:displayControl>			
      (Facility Designated)</td>
  </tr>
  
    <tr> 
    <td class="label_right" nowrap width="323" align="right">Year Installed 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Installed();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="YearInstalled" formName="stackInfo"> 
			<html:text property="YearInstalled" styleClass="normal" size="4" maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	</td>
  </tr>



  <tr> 
    <td width="323" nowrap class="label_right" align="right"> Floor 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Floor();"  onmouseout="UnTip()" />:</td>
    <td height="20"  class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="floor" formName="stackInfo"> 
		<html:text property="floor"  styleClass="normal" />
		</eespc:displayControl>			
	</td>
  </tr>
  
  <tr> 
    <td class="label_right" nowrap width="323" align="right">Height 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Height();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="height" formName="stackInfo"> 
			<html:text property="height"  styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 
		</eespc:displayControl>	FEET		
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="323" align="right"> Diameter 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Diameter();"  onmouseout="UnTip()" />:</td>
    <td height="20"  class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="diameter" formName="stackInfo"> 
		<html:text property="diameter"  styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" />
		</eespc:displayControl>INCHES			
	</td>
  </tr>
  
      <tr> 
    <td class="label_right" width="323" align="right">No. of Sources Connected to 
    this Stack<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="NoofSourcesConnectedtothisStack();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="566">&nbsp;
		 <eespc:displayControl paramName="noofsource" formName="stackInfo"> 	
		<html:text property="noofsource"  styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 	
		</eespc:displayControl>	  											  
	  
	   </td>
  </tr>
   <tr> 
    <td class="label_right" width="323" align="right">Total Capacity of the Source<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TotalCapacityoftheSource();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="566">&nbsp;
		 <eespc:displayControl paramName="totalcapacity" formName="stackInfo"> 	
			<html:text property="totalcapacity" onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" styleClass="normal"/> 		
		</eespc:displayControl>	 MMBTU/HR 											  
	  
	   </td>
  </tr>
   <tr> 
    <td class="label_right" width="323" align="right">Type of Fuel<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="TypeofFuel();"  onmouseout="UnTip()" />:</td>
    <td class="fieldleft"  nowrap width="566">&nbsp;
		 <eespc:displayControl paramName="typeoffuel" formName="stackInfo"> 
        <html:radio property="typeoffuel" onclick="getfuelrate();"  value="Oil" styleClass="normal"/>Oil 
        <html:radio property="typeoffuel" onclick="getfuelrate();"  value="Gas" styleClass="normal"/>Gas
        </eespc:displayControl>											  
	  
	   </td>
  </tr>
  
  
  <tr> 
    <td class="label_right" nowrap width="323" align="right">Flow Rate 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="FlowRate();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="flowRate" formName="stackInfo"> 
		<html:text property="flowRate"  styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" /> 
		</eespc:displayControl>	CFM		
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="323" align="right">Exhaust Temperature 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ExhaustTemperature();"  onmouseout="UnTip()" />: </td>
    <td  height="20" class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="exhaustTemp" formName="stackInfo"> 
		<html:text property="exhaustTemp"  styleClass="normal"/>
		</eespc:displayControl>			
	</td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="323" align="right">Velocity 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Velocity();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="566">&nbsp;
		<eespc:displayControl paramName="velocity" formName="stackInfo"> 
	 	<html:text property="velocity"  styleClass="normal"  			
			onkeyup="this.value=numberWithDotCheck(this.value)" 
    		onchange="this.value=numberWithDotCheck(this.value)" onfocus="getvelocity()" /> 
		</eespc:displayControl>	FPS		
    </td>
  </tr>
  <tr> 
    <td class="label_right" nowrap width="323" align="right">DEC Emission Point ID # 			
 
    <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DECEmissionsPointID();"  onmouseout="UnTip()" />:</td>
    <td  height="20" class="fieldleft"  nowrap width="566">&nbsp; 
		<eespc:displayControl paramName="emissionPointId" formName="stackInfo"> 
		<html:text property="emissionPointId"  styleClass="normal" /> 
		</eespc:displayControl>			
    </td>
  </tr>
  <tr>
    <td class="label_right" nowrap width="323" align="right">Is method 9 Test Conducted for this Stack?
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="MethodTest();"  onmouseout="UnTip()" />: </td>
    <td class="fieldleft"  nowrap  width="566" height="20">&nbsp;
		<eespc:displayControl paramName="methodNineTest" formName="stackInfo"> 		
			<html:radio property="methodNineTest" value="Y" onclick="displayControl();" />Yes 
			<html:radio property="methodNineTest" value="N" onclick="displayControl();" />No
			<html:radio property="methodNineTest" value="NA" onclick="displayControl();" />N/A
 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  
<logic:equal name="stackInfo" property="methodNineTest" value="Y">     
  
  <tr> 
    <td class="label_right" nowrap width="323"></td>
    <td class="fieldleft"  nowrap height="20" width="566"><strong>
    Last Test Date (<b><font color="#006699">mm/dd/yyyy</font></b>) <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="lastTestDate();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="methodNineLastDate" formName="stackInfo"> 
			<html:text property="methodNineLastDate" styleClass="normal"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
    <tr> 
    <td class="label_right" nowrap width="323"></td>
    <td class="fieldleft"  nowrap height="20" width="566"><strong>
    Next Test Due Date (<b><font color="#006699">mm/dd/yyyy</font></b>) <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="nextTestDueDate();"  onmouseout="UnTip()" />:</strong> 
		<eespc:displayControl paramName="methodNineNextTestDate" formName="stackInfo"> 
			<html:text property="methodNineNextTestDate" styleClass="normal"/> 
		</eespc:displayControl>
	</td>
  </tr>
  
  <tr>
    <td class="label_right" nowrap width="323"></td>    
    <td class="fieldleft"  nowrap  width="566" height="20"><strong>Whether the Opacity is under the permissible limit?<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="OpacityLimit();"  onmouseout="UnTip()" />:</strong>
		<eespc:displayControl paramName="opacityLimit" formName="stackInfo"> 		
			<html:radio property="opacityLimit" value="Y"/>Yes 
			<html:radio property="opacityLimit" value="N"/>No
			<html:radio property="opacityLimit" value="NA"/>N/A 
		</eespc:displayControl>	  											  
	  </td>
  </tr>
  
</logic:equal>  

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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");        out.println("StackInfo.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("StackInfo.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("/eespc/clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
						
            <td  align="right">
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "addStack(true)" : "addStack(false)") %>;">			
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