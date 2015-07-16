<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<%@ page isErrorPage="true" %>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<TITLE>Sample web application for JasperReports</TITLE>
</HEAD>
<BODY bgcolor="white">

<P>Errors occurred! See below for error/exception information.</P>
<PRE>
<%Exception e = (Exception) request.getAttribute("exception");
e.printStackTrace(new java.io.PrintWriter(out));
%>
</PRE>

</BODY>
</HTML>

