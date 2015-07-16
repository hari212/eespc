<%@ page import="java.util.Enumeration,org.apache.struts.Globals,java.io.StringWriter,java.io.PrintWriter" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Error Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<h3> Error Page</h3>
<hr>
<%
/*
paramNames = session.getAttributeNames();
while (paramNames.hasMoreElements())
{
	String name = (String) paramNames.nextElement();
	Object values = session.getAttribute(name);
	out.println("<br>" + name + ":" + values);
}
out.println("<hr>");
*/
Object o = request.getAttribute(Globals.EXCEPTION_KEY);
if (o != null) {
	Throwable thr = (Throwable)o;
	out.println("<h3>An Exception was thrown: </h3>" + thr);
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	thr.printStackTrace(pw);
	pw.flush();
	String trace = sw.toString();
	out.println("<pre>" + trace + "</pre>");
}
%>
<p><b>All Attributes in request scope:</b></p>
<%
Enumeration paramNames = request.getAttributeNames();
while (paramNames.hasMoreElements())
{
	String name = (String) paramNames.nextElement();
	Object values = request.getAttribute(name);
	out.println("<br>" + name + "==>" + values);
}
out.println("<hr>");
%>

</body>
</html>
