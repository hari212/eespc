<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ page language="java" import="com.eespc.tracking.bo.Constants,com.eespc.tracking.bo.FacilityVo" %>
<%
FacilityVo  facVo = (FacilityVo)session.getAttribute("FACILITY_OBJECT");
StringBuffer facBuffer = new StringBuffer();
if (facVo != null){
	facBuffer.append("<span class=\"act_lnk\">Client Name/DEC Id :</span> &nbsp;");
	facBuffer.append(facVo.getClientName());
	facBuffer.append("/").append(facVo.getDecId());
}
%>
<script>
function logOut()
{
	var test = top.location.href;
	if (test.indexOf(".do") == -1)
	{
		document.forms[0].action=test + '/Logout.do';
	}else{
		document.forms[0].action=test.substring(0, test.lastIndexOf("/")) + '/Logout.do';
	}
	document.forms[0].submit();	
}
function goToHome()
{
	var urlToGo = top.location.protocol + "//" + top.location.host + '<%= request.getContextPath() %>' ;
	top.location.href=urlToGo;
}
</script>
<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 width="100%" style="border-collapse: collapse" bordercolor="#111111" height="59">
    <TR> 
      <TD bgcolor="#0066CC" width="9" height="31">&nbsp;&nbsp;</TD>
      <TD width="75" height="31" bgcolor="#0066CC"> 
      <p align="center"> 
      <img border="0" src="images/logo.gif" ><br><img border="0" src="images/earth.gif" ></TD>
      <TD COLSPAN=3 WIDTH=621 BGCOLOR=#0066CC ALIGN=CENTER CLASS=BANNER height="31">EESPC 
        - Compliance Tracking Software</TD>
      <TD BGCOLOR=#CCCCFF ALIGN=center NOWRAP width="193" height="31">
      
      
   
      
      <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="36%" id="AutoNumber1">
  
  <tr>
 
    <td width="50%"> <A CLASS=LN HREF="javascript:goToHome();" target="_self"><img border="0" src="images/house.png" width="16" height="16"></A></td>
    <td width="50%"><A CLASS=LN HREF="javascript:goToHome();" target="_self">&nbsp;Home</A></td>

      
      
      
     
    <td width="50%"><A CLASS=LN HREF="javascript:logOut();"><img border="0" src="images/exclamation.png" width="16" height="16"></A></td>
    <td width="50%"><A CLASS=LN HREF="javascript:logOut();">&nbsp;Logout&nbsp;</A></td>
        <td width="50%"><A CLASS=LN HREF="/eespc/sitemap.html" target="_blank"><img border="0" src="images/sitemap.png" width="16" height="16"></A></td>
    <td width="50%"><A CLASS=LN HREF="/eespc/sitemap.html" target="_blank">&nbsp;SiteMap</A></td>
    

  </tr>
</table>       
      
      
      
      
      
      </TD>
    </TR>
    <TR> 
      <TD COLSPAN=7 CLASS=MENU width="892" height="28"> <TABLE WIDTH="100%">
          <TR> 

            <TD CLASS=menu WIDTH="33%" ALIGN=center><img border="0" src="images/connect.png" width="16" height="16">&nbsp;<%=facBuffer.toString()%></TD>
            <TD CLASS=menu ALIGN=center WIDTH="33%"> <span class="act_lnk"><img border="0" src="images/user.png" width="16" height="16">&nbsp;<bean:write name="<%= Constants.SESSION_USER %>" property="firstName" /> &nbsp; <bean:write name="<%= Constants.SESSION_USER %>" property="lastName" /> </TD>
            <TD CLASS=menu ALIGN=center>&nbsp; <img border="0" src="images/clock.png" width="16" height="16">&nbsp;<script>document.write(new Date());</script></TD>
          </TR>
        </TABLE></TD>
    </TR>
  </TABLE>