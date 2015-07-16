<%@ page import="com.eespc.tracking.reports.beans.ReportBean" %>
<html>
<body>
<form method="post">
<%
	ReportBean reportBean = (ReportBean)session.getAttribute("reportBean");
try{


	if (
		request.getParameter("btnFirst") != null
		|| request.getParameter("btnFirst.x") != null
		) 
	{
		reportBean.setFirstPage();
	} 
	else if (
		request.getParameter("btnPrevious") != null
		|| request.getParameter("btnPrevious.x") != null
		) 
	{
		reportBean.setPreviousPage();
	} 
	else if (
		request.getParameter("btnNext") != null
		|| request.getParameter("btnNext.x") != null
		) 
	{
		reportBean.setNextPage();
	} 
	else if (
		request.getParameter("btnLast") != null
		|| request.getParameter("btnLast.x") != null
		) 
	{
		reportBean.setLastPage();
	} 
	

	if (
		reportBean.getError() != null
		)
	{
%>
<span class="bnew">&nbsp;&nbsp;&nbsp;<%=reportBean.getError()%></span>
<%
	}
	else if (reportBean.getLastPage() == -1)
	{
%>
<span class="bold">&nbsp;&nbsp;&nbsp;This Source is not available in this Facility.</span>
<%
	}
	else if (reportBean.getLastPage() >= 0)
	{
%>
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
  <td width="100%">
    <table width="100%" cellpadding="0" cellspacing="0" border="0">
      <tr>
        <td>&nbsp;</td>
        <td><a href="pdf" target="_blank" title="Save as PDF"><img src="/eespc/images/pdf.gif" border="0"></a></td>

        <td>&nbsp;</td>
        <td><a href="xls" target="_blank" title="Save as Excel"><img src="/eespc/images/xls.gif" border="0"></a></td>
        <td>&nbsp;&nbsp;&nbsp;</td>
<%
		if (reportBean.getPageIndex() <= 0)
		{
%>
        <td><img src="/eespc/images/first_grey.gif" border="0"></td>
        <td><img src="/eespc/images/previous_grey.gif" border="0"></td>
<%
		}
		if (reportBean.getPageIndex() > 0)
		{
%>
        <td><input type="image" name="btnFirst" value="btnFirst" src="/eespc/images/first.gif"></td>
        <td><input type="image" name="btnPrevious" value="btnPrevious" src="/eespc/images/previous.gif"></td>
<%
		}
		if (reportBean.getPageIndex() < reportBean.getLastPage())
		{
%>
        <td><input type="image" name="btnNext" value="btnNext" src="/eespc/images/next.gif"></td>
        <td><input type="image" name="btnLast" value="btnLast" src="/eespc/images/last.gif"></td>
<%
		}
		if (reportBean.getPageIndex() >= reportBean.getLastPage())
		{
%>
        <td><img src="/eespc/images/next_grey.gif" border="0"></td>
        <td><img src="/eespc/images/last_grey.gif" border="0"></td>
<%
		}
%>
        <td width="100%">&nbsp;</td>
      </tr>
    </table>
<!--
    <hr size="1" color="#000000">
-->
  </td>
</tr>
<tr>
  <td align="center">
<%
	out.println(reportBean.getHtml());
%>
  </td>
</tr>
</table>
<%
	}
}catch(Exception e){
	out.println(e);
	e.printStackTrace();
}	

%>
</form>
<p></p>
</body>
</html>