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
<script src="/eespc/js/Elev1.js" ></script>
<script src="/eespc/help/elevator.js" ></script>
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
function displaycontrol()

{
document.forms[0].methodToCall.value='displaycontrol';
document.forms[0].submit();	
}

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
	var url = "/eespc/ElevatorListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
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
<SPAN CLASS=page_title>Elevators</SPAN> <br>		
	<br>			
<html:form action="/ElevatorAction" onsubmit="return validateEleForm(this)" enctype="multipart/form-data">	
<html:javascript formName="eleForm"/>	
<input type="hidden" name="methodToCall" value="reset"> 
<input type="hidden" name="id"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">

<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
  <tr> 
    <td width="27%" nowrap class="label_right"><eespc:requiredField /> ID # <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();" onmouseout="UnTip()">:</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
     <eespc:displayControl paramName="eleId" formName="eleForm">  
    	<html:text property="eleId" styleClass="normal"/>
    	<input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>        
    (Facility Designated)</td>
  </tr>
  

		
		
<tr> 
    <td nowrap class="label_right"><eespc:requiredField />Type <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Type();" onmouseout="UnTip()">:</td>
    <td width="73%"  nowrap class="fieldleft"> 
    		  <eespc:displayControl paramName="eleType" formName="eleForm"> 		
			<html:radio property="eleType" value="Elevator" onclick="displaycontrol()" />Elevator 
			<html:radio property="eleType" value="Escalator" onclick="displaycontrol()" />Escalator
			<html:radio property="eleType" value="Electric" onclick="displaycontrol()" />Electric  
		</eespc:displayControl>
    
  </tr>		
  <logic:equal name="eleForm" property="eleType" value="Elevator">
  <tr> 
    <td nowrap class="label_right">Hydraulic Tank <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="HydraulicTank();" onmouseout="UnTip()">:</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
    		<eespc:displayControl paramName="eleHydTankFrom" formName="eleForm"> 
    			<html:hidden property="eleHydTank" /> 		
    			<html:text property="eleHydTankFrom" styleClass="normal" /> 
    			<input type="button" name="Submit23" value="Search" onClick="searchFuel();">
    		</eespc:displayControl>
    
  </tr>
  </logic:equal>
   

 
		
		
   <tr> 
    <td class="label_right"><eespc:requiredField />Status of Source <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();" onmouseout="UnTip()">:</td>
    <td class="fieldleft"  nowrap>&nbsp;
			<eespc:displayControl paramName="ModifiedInPast" formName="eleForm"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="ELE_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
 											  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="eleForm"> 
			<html:text property="DisconnectedYear" onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)" styleClass="normal" /> 
		</eespc:displayControl>
	   </td>
  </tr>


  <tr> 
    <td width="27%" height="20" nowrap class="label_right">
    Comments <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />:</td>
    <td width="73%"  nowrap class="fieldleft">&nbsp; 
     <eespc:displayControl paramName="elecomment" formName="eleForm"> 
     <html:textarea property="elecomment" cols="28	" rows="2"/> 
    	
    </eespc:displayControl>        
    </td>
  </tr>

 
  <tr>
<br>
<br>
</tr>
  </table>		
<br>	
    <DIV id= "DepDiv " style= "{display:<%   
        String str = (String)request.getAttribute("ELE_DEP"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %> }">

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dob%> Permit Information</FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="1" width="100%" cellspacing="0" cellpadding="2" class="globalTableStyle" style="border-collapse: collapse" bordercolor="#006699">
              <tr class="oddRowStyle"> 
                <td   class=columnhead> Permit #</td>
                <td   class=columnhead>Last Inspection Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Report Submittal Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Firm Inspected</td>
                <td   class=columnhead>Next Inspection Date<br> (<b><font color="#006699">mm/dd/yyyy</font></b>)</td>
                <td   class=columnhead>Note<br></td>
                <td   class=columnhead>Edit</td>
                <td   class=columnhead>Delete</td>
                
              </tr>
		<%
		List inspectionList1	 = (List)request.getAttribute("ELE_DEP_LST");
		boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DEP_PERMIT"));		
		int size1 =0;
		if (inspectionList1 != null){
			size1 = inspectionList1.size();
			for (int i=0; i < size1; i++)
			{
				ElevatorPermitVo statePermitInfo = (ElevatorPermitVo)inspectionList1.get(i);
				boolean isDepPermitEditable = false;
				int permitId = statePermitInfo.getPermitId();
				String permitIdStr = permitId +"";
				String tempStr = (String)request.getAttribute("hdnPermitId");
				// out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
				if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
				{
					isDepPermitEditable = true;
				}              		

              		out.println("<tr class=\"evenRowStyle\">");
                	out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "elePermit", UtilityObject.checkNullAndFill(statePermitInfo.getPermitNumber(),""),null));
			//out.println(statePermitInfo.getPermitNumber());
			out.print("</td>");
                	out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "eleLastInsDate", UtilityObject.checkNullAndFill(statePermitInfo.getLastInspDate(),""),null));
			//out.println(statePermitInfo.getLastInspDate());
			out.print("</td>");				
                	out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "eleRepSubmDate",  UtilityObject.checkNullAndFill(statePermitInfo.getReportSubmitDate(),""),null));
			//out.println(statePermitInfo.getReportSubmitDate());
			out.print("</td>");		
			out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "eleRepSubmDateNote",  UtilityObject.checkNullAndFill(statePermitInfo.getReportSubmitDateNote(),""),null));
			//out.println(statePermitInfo.getReportSubmitDateNote());
			out.print("</td>");			
                	out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "eleFirm",  UtilityObject.checkNullAndFill(statePermitInfo.getFirmInspected(),""),null));
			//out.println(statePermitInfo.getFirmInspected());
			out.print("</td>");				
                	out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "eleNextInspDate",  UtilityObject.checkNullAndFill(statePermitInfo.getNextInspDate(),""),null));
			//out.println(statePermitInfo.getNextInspDate());
			out.print("</td>");	
			out.println("<td nowrap class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "eleNextInspDateNote",  UtilityObject.checkNullAndFill(statePermitInfo.getNextInspDateNote(),""),null));
			//out.println(statePermitInfo.getNextInspDateNote());
			out.print("</td>");				

			out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/ElevatorAction.do?methodToCall=depEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");
				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}

			out.println("</td>");		
			
out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/ElevatorAction.do?methodToCall=depDelete&hdnPermitId=");out.print(permitId);
				out.print("\"  onclick=\"return confirm('Do you want delete this??')\">");
				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}

			out.println("</td>");			
			out.println("</tr>");
			}
		}
		
		String method = null;
		if (!toEditDepPermit){ //show the following only if it is not edit									
		    method = "dep";    
		
		
		%>              

              
              <tr class="evenRowStyle"> 
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="elePermit" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="eleLastInsDate" styleClass="normal"/></td>
                 </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="eleRepSubmDate" styleClass="normal"/></td>
                 </td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="eleRepSubmDateNote" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="eleFirm" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="eleNextInspDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="eleNextInspDateNote" styleClass="normal"/></td>
                <td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
              </tr>
		<%
			}
			else // if !toEditDepPermit loop
			  method = "depUpdate";
		%>
              
            	  <tr align="right" class="evenRowStyle"> 
           	      <td    colspan=10 >
			<input type="button" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="validateDep('<%= method %>');">		      
		     </td>   
                 </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>

   
  <br> 		

<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>


<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF"><img border="0" src="images/folderopen.gif" width="16" height="16"><b> 
    Documents (D):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%                String file_path=String.valueOf(request.getAttribute("FILE_PATH")).replaceAll("&", "%26");                           out.println("ElevatorAction.do?methodToCall=back&pathname="+file_path);                   %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("ElevatorAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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
</DIV>
<br>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
  
    <td width="53%" align="right">
      <input type="button" name="Button22" value="<< Return To Building" onClick="returnToBuilding();">
  </td>  
    <td width="47%">
    
    <logic:notEqual name="isComponentEditable" value="N">
<%
			String tempStr = (String)request.getAttribute("isItForEdit");
			boolean  isEdit = false;
			if (null != tempStr && tempStr.equalsIgnoreCase("Y")){
				isEdit = true;
			}
%>						
						
            <td  align="right">
		<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "saveElevator(true);" : "saveElevator(false);") %>;">			
      		</td>
            <td > <input type="reset" name="Submit22" value="Reset"></td>
			</logic:notEqual>
    
    </td>
  </tr>
</table>  

</html:form>
<b><font size="2" face="Verdana" color="#006699">Action: When Data for all Sources are 
Input and Verified by the Facility, Then the Compliance Status can be Viewed in the </font>
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>        
</body>
</html>