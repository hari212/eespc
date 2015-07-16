<%@ page language="Java" %>
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.PermitInfoVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.bo.FacilityVo" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>New rpz</title>
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<script src="/eespc/js/CommonScript.js"></script>
<script src="/eespc/js/Rpz.js" ></script>
<script src="/eespc/help/rpzhelp.js" ></script>
<script>
function del()
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
 return true;

}
else
{
return false;
}
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
	var url = "/eespc/RPZListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}

function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
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
<SPAN CLASS=page_title>RPZ (Back Flow Preventor) </SPAN> <br>		
	<br>			
<html:form action="/RpzAction" enctype="multipart/form-data">
<input type="hidden" name="id">
<input type="hidden" name="methodToCall" value="reset"> 
<input name="hdnPermitId" type="hidden" value="<%=request.getAttribute("hdnPermitId")%>">
<input name="hdnContext" type="hidden" value="<%=request.getAttribute("isItForEdit")%>">
<table width="100%" border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="#CCCCCC">
  <tr> 
    <td align="right"><eespc:requiredField />ID #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="ID();"  onmouseout="UnTip()" />: </td>
    <td>&nbsp; 
    <eespc:displayControl paramName="rpzId" formName="rpzForm"> 
    <html:text property="rpzId" styleClass="normal"/>
    <input type="button" name="Submit23" value="Copy From Exist" onClick="searchExist();">
    </eespc:displayControl>	
    (Facility Designated)</td>
  </tr>
  <tr> 
    <td align="right">Year Installed<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="YearInstalledhelp();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp; 
    <eespc:displayControl paramName="rpzYear" formName="rpzForm"> 
        <html:text property="rpzYear" styleClass="normal"  maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/>
    </eespc:displayControl>			
  </tr>
  <tr> 
    <td align="right">Serial #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Serialhelp();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp; 
    <eespc:displayControl paramName="rpzSerialNo" formName="rpzForm"> 
     <html:text property="rpzSerialNo" styleClass="normal"/></td>
    </eespc:displayControl>			
  </tr>
  <tr> 
    <td align="right">Make<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Make();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp;
    <eespc:displayControl paramName="rpzMake" formName="rpzForm"> 
    <html:text property="rpzMake" styleClass="normal"/>
    </eespc:displayControl>			
    </td>
  </tr>
  <tr> 
    <td align="right">Model<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Model();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp;
    <eespc:displayControl paramName="rpzModel" formName="rpzForm"> 
    <html:text property="rpzModel" styleClass="normal"/>	
    </eespc:displayControl>		</td>
  </tr>
  <tr> 
    <td align="right">Size<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Size();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp; 
    <eespc:displayControl paramName="rpzSize" formName="rpzForm"> 
    <html:text property="rpzSize" styleClass="normal"  maxlength="4" 
		 onkeyup="this.value=Check(this.value)" 
    		onchange="this.value=Check(this.value)"/>
    </eespc:displayControl>
    (in.)</td>
  </tr>
  <tr>
    <td align="right">Location<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Location();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp; 
     <eespc:displayControl paramName="psLocation" formName="rpzForm"> 
		<html:select property="psLocation" styleClass="normal">
			<html:optionsCollection name="AGENCY_LOCATION" property="dropDownValues" value="value" label="name" /> 
        	</html:select>		
     </eespc:displayControl> 
    </td>
  </tr>
 
<tr> 
    <td align="right">Type<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Type();"  onmouseout="UnTip()" />: </td>
    <td>&nbsp;
    <eespc:displayControl paramName="rpzType" formName="rpzForm"> 
    <html:text property="rpzType" styleClass="normal"/>	
    </eespc:displayControl>		</td>
  </tr>
 <tr> 
    <td align="right"><%=dep%> Permit Status<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="PermitStatus();"  onmouseout="UnTip()" />: </td>
    <td>&nbsp; 
    <eespc:displayControl paramName="Permit" formName="rpzForm"> 
      <html:radio property="Permit" value="Y" onclick="displayControl();"/>Yes
      <html:radio property="Permit" value="N" onclick="displayControl();"/>No 
      <html:radio property="Permit" value="NA" onclick="displayControl();"/>N/A
      </eespc:displayControl> </td>
  </tr>
  <logic:equal name="rpzForm" property="Permit" value="Y"> 	
   <tr> 
    <td align="right"><%=dep%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPPermitInformation();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp;
    <eespc:displayControl paramName="rpzDEP" formName="rpzForm"> 
    <html:text property="rpzDEP" styleClass="normal"/> 
    </eespc:displayControl>		
    </td>
  </tr>
 </logic:equal>
  <tr> 
    <td align="right"><%=dob%> #<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOB();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp; 
    <eespc:displayControl paramName="rpzDOB" formName="rpzForm"> 
    <html:text property="rpzDOB" styleClass="normal"/>
    </eespc:displayControl></td>
  </tr>
    <tr> 
    <td align="right"><%=dob%> Sign Off<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DOBsignoff();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp; 				
		<eespc:displayControl paramName="dobsignoff" formName="rpzForm"> 		
			<html:radio property="dobsignoff" value="Y"  />Yes <html:radio property="dobsignoff" value="N"/>No 
		</eespc:displayControl>	 	
	</td>
  </tr>
 
  
  <tr> 
    <td align="right"><eespc:requiredField />Status of Source<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="StatusofSource();"  onmouseout="UnTip()" />:</td>
    <td>&nbsp;
			<eespc:displayControl paramName="ModifiedInPast" formName="rpzForm"> 
			<html:select property="ModifiedInPast" styleClass="normal" onchange="status();">
				<html:optionsCollection name="TANK_OPERATION_STATUS" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
		</eespc:displayControl>	  
									  
	  &nbsp; <strong>Disconnected Year :</strong>
		<eespc:displayControl paramName="DisconnectedYear" formName="rpzForm"> 
			<html:text property="DisconnectedYear" styleClass="normal"  maxlength="4" 
			onkeyup="this.value=intCheck(this.value)" 
    		onchange="this.value=intCheck(this.value)"/> 
		</eespc:displayControl>
	   </td>
  </tr>
<tr> 
    <td align="right">Comments <input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="Comments();"  onmouseout="UnTip()" />: </td>
    <td>&nbsp; 
    <eespc:displayControl paramName="rpzComment" formName="rpzForm"> 
    <html:textarea property="rpzComment" cols="30" rows="2" ></html:textarea>
    </eespc:displayControl>	  
    </td>
  </tr>
  
</table>		
<br>
<logic:equal name="showAddBtn" value="Y">			
<DIV id= "InspDiv" style= "{display:<% 
           String str = (String)request.getAttribute("RPZ_INSPEC"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %>} "> 

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section>Annual Inspection Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="AnnualInspectionInformation();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle">                
                <td   class=columnhead>Last Annual Inspection Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Next Annual Inspection Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note<br></td>

		<td   class=columnhead>Edit</td>
				<td   class=columnhead>Delete</td>
              </tr>
	<%
	List inspectionList = (List)request.getAttribute("RPZ_INSTALLATION_LST");
	boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_DEP_PERMIT"));
	int size1 =0;
	if (inspectionList != null){
		size1 = inspectionList.size();
		for (int i=0; i < size1; i++)
		{
			PermitInfoVo statePermitInfo= (PermitInfoVo)inspectionList.get(i);
			boolean isDepPermitEditable = false;
			int permitId = statePermitInfo.getId();
			String permitIdStr = permitId +"";
			String tempStr = (String)request.getAttribute("hdnPermitId");
			//out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
			if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
			{
				isDepPermitEditable = true;
			}              		
              		out.println("<tr class=\"evenRowStyle\">");
              		
              		
				
                	out.println("<td nowrap width=\"26%\" class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "rpzLastInspDate", statePermitInfo.getIssueDateStr(),null));
					//out.println(statePermitInfo.getIssueDateStr());
					out.print("</td>");
					
                	out.println("<td nowrap width=\"26%\" class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "rpzNextInspDate", statePermitInfo.getExpirationDateStr(),null));
					//out.println(statePermitInfo.getExpirationDateStr());
					out.print("</td>");	
					
					out.println("<td nowrap width=\"26%\" class=\"fieldleft\">");
                	out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "rpzNextInspDateNote", statePermitInfo.getNote(),null));
					//out.println(statePermitInfo.getNote());
					out.print("</td>");	
							
			out.println("<td align=\"center\" width=\"6%\" nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/RpzAction.do?methodToCall=inspectionEdit&hdnPermitId=");out.print(permitId);
				out.print("\">");

				out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
			}else{
				out.println("&nbsp;");
			}
			out.println("</td>");		
			
	out.println("<td align=\"center\" width=\"6%\" nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/RpzAction.do?methodToCall=inspectionDelete&hdnPermitId=");out.print(permitId);
				out.print("\"  onclick=\"return del()\">");

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
	    method = "inspection";    
%>				                

              <tr class="evenRowStyle">              		
                <td width="23%" align="center"  nowrap class="fieldleft"><html:text property="rpzLastInspDate" styleClass="normal"/></td>
                <td width="27%" align="center"  nowrap class="fieldleft"><html:text property="rpzNextInspDate" styleClass="normal"/></td>
                <td width="27%" align="center"  nowrap class="fieldleft"><html:text property="rpzNextInspDateNote" styleClass="normal"/></td>
			  	<td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
              </tr>
<%
	}
	else // if !toEditDepPermit loop
	  method = "inspectionUpdate";
%>
              
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=5>              
                  <input type="button" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="<%= ((toEditDepPermit)? "addDep(true)" : "addDep(false)") %>;">
              </td>
              </tr>

            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>
</DIV>
</logic:equal>

<!--<div><font color='red'><html:errors property="rpzLastInspDate" /></font></td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <td><font color='red'><html:errors property="rpzNextInspDate" /></font></td> </div>-->
<logic:equal name="showAddBtn" value="Y">
 <logic:equal name="rpzForm" property="Permit" value="Y"> 			
<DIV id= "LocDiv" style= "{display:<% 
           String str = (String)request.getAttribute("RPZ_LOC"); 
        if (str != null)
             out.println(str);
        else
             out.println("none"); %>} ">              
            
<br>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD>&#160&#160&#160 <FONT COLOR=white CLASS=section><%=dep%> Permit Information<input type="image" onClick="return false;" src="/eespc/images/help.png" onmouseover="DEPpermit();"  onmouseout="UnTip()" /></FONT></TD>
  </TR>
  <TR> 
    <TD > 
    
    <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
      
        <TR BGCOLOR=white> 
          <TD> 
           <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle">
                <td   class=columnhead>Issued Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Expiration Date<br> (<font color="#006699">mm/dd/yyyy</font>)</td>
                <td   class=columnhead>Note</td>
		<td   class=columnhead>Edit</td>	
		<td   class=columnhead>Delete</td>	
		
              </tr>
	<%
	        List inspectionList1	 = (List)request.getAttribute("RPZ_LOC_LST");
		int size1 =0;
		boolean toEditDepPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_LOC_PERMIT"));
		if (inspectionList1 != null){
			size1 = inspectionList1.size();
			for (int i=0; i < size1; i++)
			{
				PermitInfoVo statePermitInfo = (PermitInfoVo)inspectionList1.get(i);
				boolean isDepPermitEditable = false;
				int permitId = statePermitInfo.getId();
				String permitIdStr = permitId +"";
				String tempStr = (String)request.getAttribute("hdnPermitId");
				//out.println(tempStr + "==" + permitIdStr + ", " + toEditDepPermit);
				if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditDepPermit)
				{
					isDepPermitEditable = true;
				}              		

				out.println("<tr class=\"evenRowStyle\">");		
				out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
				out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "rpzLocIssueDate", statePermitInfo.getIssueDateStr(),null));
				//out.println(statePermitInfo.getIssueDateStr());
				out.print("</td>");
				
				out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
				out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "rpzLocExpirationDate", statePermitInfo.getExpirationDateStr(),null));
				//out.println(statePermitInfo.getExpirationDateStr());
				out.print("</td>");	
				
				out.println("<td width=\"26%\" nowrap class=\"fieldleft\">");
				out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isDepPermitEditable, "rpzLocExpirationDateNote", statePermitInfo.getNote(),null));
				//out.println(statePermitInfo.getNote());
				out.print("</td>");	
							
				out.println("<td width=\"6%\" align=\"center\"  nowrap >");
				if (!isDepPermitEditable){
					out.print("<a href=\"/eespc/RpzAction.do?methodToCall=stateEdit&hdnPermitId=");out.print(permitId);
					out.print("\">");

					out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
				}else{
					out.println("&nbsp;");
				}
				out.println("</td>");	
					out.println("<td align=\"center\" width=\"6%\" nowrap class=\"fieldleft\">");
			if (!isDepPermitEditable){
				out.print("<a href=\"/eespc/RpzAction.do?methodToCall=stateDelete&hdnPermitId=");out.print(permitId);
				out.print("\"  onclick=\"return del()\">");

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
		    method = "state";    
	%>				                 
              
              <tr class="evenRowStyle">
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="rpzLocIssueDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="rpzLocExpirationDate" styleClass="normal"/></td>
                <td width="26%" align="center"  nowrap class="fieldleft"><html:text property="rpzLocExpirationDateNote" styleClass="normal"/></td>                
		<td width="6%" align="center"  nowrap class="fieldleft" colspan=2></td>
              </tr>
	<%
		}
		else // if !toEditDepPermit loop
		  method = "stateUpdate";
	%>

             
              <tr align="right" class="evenRowStyle"> 
                <td    colspan=5>
                  <input type="button" name="method" value="<%= ((toEditDepPermit)? "Update" : "Add") %>" onclick="<%= ((toEditDepPermit)? "stateadd(true)" : "stateadd(false)") %>;">              	
		</td>
              </tr>
            </table></TD>
        </TR>
      </TABLE>
      </TD>
  </TR>
</TABLE>
</DIV>
</logic:equal>
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
    <p align="center"><font color="#FFFFFF"><input type="button" value="Add New Folder" name="B1" onClick="addnewfolder();" ><a href="<%            String file_path=((String)request.getAttribute("FILE_PATH")).replaceAll("&", "%26");                               out.println("RpzAction.do?methodToCall=back&pathname="+file_path);        %>">
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("RpzAction.do?methodToCall=viewfile&foldername="+arr[2]+"&pathname="+file_path);    }    else    {    out.println("clientfolder/"+file_path+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
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




<!--<div><font color='red'><html:errors property="rpzLastInspDate" /></font></td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <td><font color='red'><html:errors property="rpzNextInspDate" /></font></td> </div>-->
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
				<input type="button" name="Button22" value="<%= ((isEdit)? "Update" : "Save") %>" onClick="<%= ((isEdit)? "add(true)" : "add(false)") %>;">			
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
Input and Verified by the Facility, Then the Compliance Status can be Viewed in the </font>t
<font size="2" face="Verdana" color="#0000FF">Reports</font><font size="2" face="Verdana" color="#006699">. 
Please Go to REPORTS menu, and Choose </font>
<font size="2" face="Verdana" color="#0000FF">General reports</font><font size="2" face="Verdana" color="#006699">.
</font></b>
</body>
</html>