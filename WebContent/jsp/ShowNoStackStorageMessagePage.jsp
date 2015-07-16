<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META http-equiv="Content-Style-Type" content="text/css">

<TITLE>Stack Storage Tank Validator - Message(s)</TITLE>
<link rel="stylesheet" type="text/css" href="../css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

</HEAD>
<BODY>
<form action="/eespc/BuildingInfo.do">
<input type="hidden" name="methodToCall" value="view" />

<br>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		  <tr>
		  <td class="error">
<%
				out.println(request.getAttribute("STACK_STORAGE_VALIDATION_MESSAGE"));
				out.println("Please add the corresponding Resource(s)");
%>		  
		  </td>
		  </tr>
          <tr> 
            <td  align="center"><input type="button" name="Button22" value="<< Return To Building" onClick="document.forms[0].submit();">
     	    </td>		  
     	  </tr>
     	</table>
</form>
</BODY>
</HTML>