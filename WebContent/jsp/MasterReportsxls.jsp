<%@ page contentType="application/vnd.ms-excel;
    charset=UTF-8" %>
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

</head>
<script>
function view()
{
document.forms[0].rptType.value=opener.document.forms[0].rptType[opener.document.forms[0].rptType.selectedIndex].value;
document.forms[0].submit();
}
</script>
<body>
<form method="post" action="/eespc/MasterReports.do">
<input type="hidden" name="rptType" value="" />
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR> 
    <TD > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="1" width="100%" cellspacing="0" cellpadding="2" class="globalTableStyle" style="border-collapse: collapse" bordercolor="#111111">
              <tr > 
                <td bgcolor="#006699"   ><font color="#FFFFFF">Status</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Client</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Permit</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Expire</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Due</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Jan</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Feb</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Mar</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Apr</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">May</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Jun</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Jul</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Aug</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Sep</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Oct</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Nov</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Dec</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Last <br>Proposal</font></td>
                <td bgcolor="#006699"   ><font color="#FFFFFF">Next <br>Proposal</font></td>
              </tr>
<%
			List reportList = (List)session.getAttribute("MASTER_REPORT");
			if (reportList != null){
				int size = reportList.size();
				for (int i=0; i < size; i++)
				{
					
					Hashtable ht=(Hashtable)reportList.get(i);
					
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
					out.println("<td  nowrap class=\"fieldleft\">"+ht.get("fexpirationdate")+"</td>");
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