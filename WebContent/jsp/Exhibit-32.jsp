<%@ page import="java.util.List,com.eespc.tracking.bo.BoilerPermitInfoVo,com.eespc.tracking.bo.FacilityVo" %>
<html:form action="/documentAction" enctype="multipart/form-data"> 
<html:hidden property="methodToCall" />
<html:hidden property="foldername" />
<html:hidden property="renamefoldername" />

<input type="hidden" name="pathname" value="<%=(String)request.getAttribute("FILE_PATH")%>">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#006699">
  <tr>
    <td width="33%"><font color="#FFFFFF">
    <img border="0" src="images/folderopen.gif" width="16" height="16"><b>Agency Inspection Documents (AID):</b></font></td>
    <td width="33%"><b>&nbsp;<font color="#FFFFFF"><%=(String)request.getAttribute("FILE_PATH")%> </font></b></td>
    <td width="34%">
    <p align="center"><font color="#FFFFFF"><a href="<%out.println("documentAction.do?methodToCall=backforexhibit32&separateFile=1&pathname="+(String)request.getAttribute("FILE_PATH"));%>">
    <b><font size="2" color="#FFFFFF">BACK</font></b></a></font></td>
  </tr>
</table>

<p>

<br>
</p>
<p></p>
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
    <a href="<%if(arr[0].equals("folder"))    {    out.println("documentAction.do?methodToCall=viewexhibit&separateFile=1&foldername="+arr[2]+"&pathname="+(String)request.getAttribute("FILE_PATH"));    }    else    {    out.println("clientfolder_global/"+(String)request.getAttribute("FILE_PATH")+"/"+arr[2]);    }    %>" ><img border="0" src="images/<%=arr[0]%>.gif" width="16" height="16"><%=arr[2]%>&nbsp;</a></font></b></td>
    <td width="20%" align="center"><span style="font-variant: small-caps"><%=arr[0]%>&nbsp;</span></td>
    <td width="20%" align="center"><%
    if(arr[4]!=null)
    {
    out.println(arr[4]);
    }    
    %>&nbsp;</td>
    <td width="20%" align="center"><%=arr[3]%>&nbsp;</td>    
  </tr>
<%
}
}
%>
</table>

<p align="center">

<br> 


<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>
</html:form>		
</p>
<p align="center">		
</p>