<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="Microsoft FrontPage 5.0">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<TITLE>Master Report(s)</TITLE>
<script src="/eespc/help/commonhelp.js" ></script>
<Script>
function changeType()
{
	if(document.forms[0].rptType.selectedIndex !=0)
	{
		document.getElementById('mastFieldSet').innerHTML=document.forms[0].rptType[document.forms[0].rptType.selectedIndex].text+ " Report";	
		window.frames['ireports'].document.forms[0].rptType.value=document.forms[0].rptType[document.forms[0].rptType.selectedIndex].value;
		window.frames['ireports'].document.forms[0].submit();
		
	}
}
</Script>
</HEAD>
<BODY>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
	<html:form action="/MasterReportBase">			
<CENTER>
<TABLE border="0" width="100%" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0">
	<TBODY>
		<TR>
		<td width="6%" nowrap class="label_right">
        <p align="left"><a href="jsp/MasterReportsxls.jsp" target="_blank" onmouseover="masterexcelreport();"  onmouseout="UnTip()" >
        <img border="0" src="images/xls.gif" width="23" height="17"></a></td>
		<td width="6%" nowrap class="label_right">&nbsp;Type Of Report <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="typeofreport();"  onmouseout="UnTip()" />:</td>
		<td width="37%"  nowrap class="fieldleft">&nbsp; <html:select property="rptType" styleClass="normal" onchange="changeType();"> 
		  <html:optionsCollection name="FACILITY_TYPE" property="dropDownValues" value="value" label="name" /> 
		  </html:select> 
		  </td>
		</TR>
	</TBODY>
</TABLE>
</CENTER>
<br>
<FIELDSET ><LEGEND><SPAN id="mastFieldSet" class="label_right">Select Report Type</SPAN></LEGEND>
<iframe name="ireports" src="/eespc/MasterReports.do" width="100%" height="440"></iframe>
</FIELDSET>
</html:form>
</BODY>
</HTML>