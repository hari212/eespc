
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="Microsoft FrontPage 6.0">
<META http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" type="text/css" href="./css/eepsc_style.css" />
<TITLE>Change Password</TITLE>
<script src="/eespc/js/CommonScript.js" ></script>
<script>
function changepassword()
{
   if(isFieldEntered(document.forms[0].oldpassword)==false)
	{
       	alert("Please Enter the Old password:");
	 	document.forms[0].oldpassword.focus();
	 	return false;
	}
	else if(isFieldEntered(document.forms[0].newpassword)==false)
	{
       	alert("Please Enter the New password:");
	 	document.forms[0].newpassword.focus();
	 	return false;
	}
	else if(isFieldEntered(document.forms[0].confirmpassword)==false)
	{
       	alert("Please Enter the Confirm password:");
	 	document.forms[0].confirmpassword.focus();
	 	return false;
	}
	else if(document.forms[0].newpassword.value.length < 4)
	{
	    alert("Password length should have at least 4.");
	 	document.forms[0].newpassword.focus();
	 	return false;
	}

	else if(document.forms[0].newpassword.value != document.forms[0].confirmpassword.value)
	{
	   alert("Newpassword should be equal to Confirmpassword");
	   document.forms[0].confirmpassword.focus();
	   return false;
	}
	
	document.forms[0].methodToCall.value='passwordchange';
	document.forms[0].submit();
	return true;
}
</script>
<script src="/eespc/help/commonhelp.js" ></script>
</HEAD>
<BODY>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>

<html:form action="/UserAdministration" > 
<html:hidden property="methodToCall" />
<br><br><br><br><br><br><br><br><br>
<center>
 <table border="1" cellpadding="0" cellspacing="0"  bgcolor="#FFFFFF" height="119" width="436" bordercolor="#0066CC" style="border-collapse: collapse">
  <tr>
      <td width="430" align="right" height="14" colspan="2" bgcolor="#FFFFCC">      
      <p align="center"><b><font size="2">Change Password</font></b></td>
    </tr>
      
		
    <tr>
      <td width="212" align="right" height="14"><strong>Old Password <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="oldpass();"  onmouseout="UnTip()" />:</strong></td>
      <td width="218" align="left" height="14">
      <eespc:displayControl paramName="oldpassword" formName="adminUserForm"> 
			<html:text property="oldpassword" styleClass="normal" /> 
		</eespc:displayControl></td>
    </tr>
    
    <tr>
      <td width="212" align="right" height="13"><strong>New Password <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="newpass();"  onmouseout="UnTip()" />:</strong></td>
      <td width="218" align="left" height="13">
      <eespc:displayControl paramName="newpassword" formName="adminUserForm"> 
			<html:text property="newpassword" styleClass="normal" /> 
		</eespc:displayControl></td>
    </tr>
    
	<tr>
      <td width="212" align="right" height="11"><strong>Confirm Password <input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="confirmpass();"  onmouseout="UnTip()" />:</strong></td>
      <td width="218" align="left" height="11">
      <eespc:displayControl paramName="confirmpassword" formName="adminUserForm"> 
			<html:text property="confirmpassword" styleClass="normal" /> 
		</eespc:displayControl></td>
    </tr>
	
    <tr>
      <td width="432" colspan="2" align="left" height="29">
      <p align="center">   
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    
      <input type="button" value="Submit" name="B1" onclick="changepassword()"><input type="reset" value="Reset" name="B2"></td>
    </tr>
        </table>  

<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present> 
</html:form>
</BODY>
</HTML>