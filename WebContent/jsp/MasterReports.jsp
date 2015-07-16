<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,java.util.*,com.eespc.tracking.bo.FacilityVo" %>
<html>
<head>
<title>MasterReport</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
</head>

<body>


<form method="post" action="/eespc/MasterReports.do">
<input type="hidden" name="rptType" value="" />
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead>Status</td>
                <td   class=columnhead>Client</td>
                <td   class=columnhead>Permit</td>
                <td   class=columnhead>Expire</td>
                <td   class=columnhead>Due</td>
                <td   class=columnhead>Jan</td>
                <td   class=columnhead>Feb</td>
                <td   class=columnhead>Mar</td>
                <td   class=columnhead>Apr</td>
                <td   class=columnhead>May</td>
                <td   class=columnhead>Jun</td>
                <td   class=columnhead>Jul</td>
                <td   class=columnhead>Aug</td>
                <td   class=columnhead>Sep</td>
                <td   class=columnhead>Oct</td>
                <td   class=columnhead>Nov</td>
                <td   class=columnhead>Dec</td>
                <td   class=columnhead>Last <br>Proposal</td>
                <td   class=columnhead>Next <br>Proposal</td>
              </tr>
<%
			List reportList = (List)request.getAttribute("MASTER_REPORT");
			session.setAttribute("MASTER_REPORT",reportList);
			if (reportList != null){
				int size = reportList.size();
				for (int i=0; i < size; i++)
				{
					
					Hashtable ht=(Hashtable)reportList.get(i);
					//FacilityVo reportVo = (FacilityVo)reportList.get(i);
					if ( (i/2)==(i/2.0) )
						out.println("<tr bgcolor='#FFFFFF' onMouseOver=\"this.bgColor='#CCCCFF';\" onMouseOut=\"this.bgColor='#FFFFFF';\">");
					else
						out.println("<tr bgcolor='#EEEEEE' onMouseOver=\"this.bgColor='#CCCCFF';\" onMouseOut=\"this.bgColor='#EEEEEE';\">");
					
					out.println("<td  nowrap class=\"fieldleft\">");
					out.println(ht.get("fstatus"));
					out.println("</td>");
					out.println("<td  nowrap class=\"fieldleft\">");
					out.println(ht.get("clientname"));
					out.println("</td>");
					out.println("<td  nowrap class=\"fieldleft\">");
					out.println(ht.get("decid"));
					out.println("</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fexpirationdate")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fdue")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fjan")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("ffeb")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fmar")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fapril")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fmay")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fjune")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fjuly")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("faug")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fsep")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("foct")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fnov")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fdec")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("flastproposal")+"</td>");
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fnextproposal")+"</td>");
				  out.println("</tr>");
				}
			}
%>					
			  </table>
		  </TD>
		 </TR>
		 </TABLE>
	</TD>
	</TR>
</TABLE	>
</form>
</body>
</html>