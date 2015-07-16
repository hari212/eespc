<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.LoginUserVo" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
		<head>
			<title>User Adminstration</title>
			<html:base />
			<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<script src="/eespc/js/CommonScript.js" ></script>
			<script src="/eespc/js/AdminScreen.js" ></script>
			<script>
function setInfo()
{
var formObj = document.forms[0].stackRsltRbtn;
	if (radioChecked(formObj) == false){
		alert("Please select a User.");
		return false;
	}else{
		var id ="";

		if (document.forms[0].stackRsltRbtn && document.forms[0].stackRsltRbtn.length){
			for (var i=0; i<document.forms[0].stackRsltRbtn.length;i++){
				if(document.forms[0].stackRsltRbtn[i].checked){
					id = document.forms[0].stackRsltRbtn[i].value;
					break;
				}
			}
		}else{
			if(document.forms[0].stackRsltRbtn){		
				if(document.forms[0].stackRsltRbtn.checked){
					id = document.forms[0].stackRsltRbtn.value;
				}else{
					alert("Please select a User.");
				}
			}else{
				alert("No User Available.");
			}
			
		}
		document.forms[0].userId.value=id;
		document.forms[0].methodToCall.value='view';
		document.forms[0].submit();
				
	}
	
}

function addnewuser()
{
document.forms[0].methodToCall.value="initial";
document.forms[0].submit();
}
            </script>
		</head>
      <body onLoad="init();">
<html:form action="/UserAdministration" > 
<html:hidden property="methodToCall" />

        <SPAN CLASS=page_title>User Administration</SPAN><br>		
		<br>
        <table width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#CCCCCC" style="border-collapse: collapse">

          <tr> 

            <td width="47%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="35%" nowrap class="label_right" style="border-right-style: solid; border-right-width: 1; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />First Name :</td>
            <td width="65%"  nowrap class="fieldleft">&nbsp; <html:text property="firstName" styleClass="normal" /> 
              <input name="userId" type="hidden" value=""> &nbsp; <input type="button" name="Submit22" value="Search" onClick="goToUserSearch();"></td>
          </tr>
          <tr> 
            <td nowrap class="label_right" style="border-right-style: solid; border-right-width: 1; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />Last Name :</td>
            <td  nowrap class="fieldleft">&nbsp; <html:text property="lastName" styleClass="normal" /> 
              &nbsp; </td>
          </tr>
          <tr> 
            <td nowrap class="label_right" style="border-right-style: solid; border-right-width: 1; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">Initial :</td>
            <td  nowrap class="fieldleft">&nbsp; <html:text property="initial" styleClass="normal" />
              &nbsp; </td>
          </tr>
          <tr> 
            <td nowrap class="label_right" style="border-right-style: solid; border-right-width: 1; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">Title :</td>
            <td  nowrap class="fieldleft">&nbsp; <html:text property="title" styleClass="normal" />
              &nbsp; </td>
          </tr>
          <tr> 
            <td colspan="2" nowrap class="label_right"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="35%" rowspan="3" class="label_right" valign="top" style="border-right: 1px solid #CCCCCC">
                  <eespc:requiredField />Address :</td>
                  <td width="65%"   nowrap class="fieldleft">&nbsp; <html:text property="address1" styleClass="normal" />
                    </td>
                </tr>
                <tr> 
                  <td   nowrap class="fieldleft">&nbsp; <html:text property="address2" styleClass="normal" />
                </tr>
                <tr> 
                  <td   nowrap class="fieldleft">&nbsp; <html:text property="address3" styleClass="normal" />
                </tr>
              </table></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />City :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="city" styleClass="normal" /></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />State :</td>
            <td class="fieldleft"  nowrap>&nbsp; 
			<html:select property="state" styleClass="normal" >
				<html:optionsCollection name="STATES" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
			  </td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />Zip Code :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="zipCode" styleClass="normal" /></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />Phone :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="phone" styleClass="normal" maxlength="12" onblur="formatPhoneNumber(document.forms[0].phone, 'Phone');"/></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">Alternate Phone :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="alternatePhone" styleClass="normal" maxlength="12"  onblur="formatPhoneNumber(document.forms[0].alternatePhone, 'Alternate Phone');"/></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">
            <eespc:requiredField />Email :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="email" styleClass="normal" /></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-right: 1px solid #CCCCCC; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1">Alternate Email :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="alternateEmail" styleClass="normal" /></td>
          </tr>
        </table></td>

            <td width="53%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="29%" nowrap class="label_right" style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">
            <eespc:requiredField />Role :</td>
            <td width="71%"  nowrap class="fieldleft">&nbsp; 
			<html:select property="role" styleClass="normal" onchange="setClientAccess();">
				<html:optionsCollection name="ROLES" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
			  </td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">
            <eespc:requiredField />DeletePermission:</td>
            <td class="fieldleft"  nowrap>
            <html:radio property="delete" value="Y"  />Yes 
			<html:radio property="delete" value="N"  />No 
			</td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">
            <eespc:requiredField />Access Active :</td>
            <td class="fieldleft"  nowrap>
			<html:radio property="active" value="Y"  />Yes 
			<html:radio property="active" value="N"  />No 
			</td>
          </tr>
          <tr> 
            <td nowrap class="label_right" valign="top" style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">
            <eespc:requiredField />Client Access :</td>
            <td  nowrap class="fieldleft">&nbsp; 
			<html:select property="clientAccess" multiple="true" size="10">
				<html:optionsCollection name="CLIENT_LIST" property="dropDownValues" value="value" label="name" /> 
			</html:select>								
			</td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">
            <eespc:requiredField />Login Id :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="loginId" styleClass="normal" /></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">
            <eespc:requiredField />Password :</td>
            <td class="fieldleft"  nowrap>&nbsp; <html:text property="userPassword" styleClass="normal" /></td>
          </tr>
          <tr> 
            <td style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1">&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table></td>

          </tr>

          <tr align="center"> 

            <td colspan="2"><input type="button" name="Button2" value="<bean:write name="adminUserForm" property="btnLabel" />" onclick="validateAdminScreen();"> &nbsp;&nbsp;

              <input type="reset" name="Submit23" value="Reset">&nbsp;&nbsp;&nbsp; <input type="button" name="Submit23" onclick="addnewuser()" value="Add New User"></td>

          </tr>

        </table>

<br>
<logic:present name="MESSAGE" scope="request">
	<script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	</script>
</logic:present>
	

<eespc:table dataSource="MANAGE_USER_LIST"
		  formName="form1"
		  noDataFoundMessage="<CENTER><SPAN CLASS=error>No User List available.</SPAN></CENTER>" >
		  
		  <eespc:tableHeaderCellDef id="h_firstName" label="First Name" bodyCellId="b_firstName"/>		
		  <eespc:tableHeaderCellDef id="h_lastName" label="Last Name" bodyCellId="b_lastName"/>
		  <eespc:tableHeaderCellDef id="h_initial" label="Initial" bodyCellId="b_initial"/>		  
		  <eespc:tableHeaderCellDef id="h_title" label="Title" bodyCellId="b_title"/>		  		  
		  <eespc:tableHeaderCellDef id="h_role" label="Role" bodyCellId="b_role"/>
		  <eespc:tableHeaderCellDef id="h_clientAccess" label="Client Access List" bodyCellId="b_clientAccess"/>		  
		  <eespc:tableHeaderCellDef id="h_disabled" label="Disabled" bodyCellId="b_disabled"/>		  		  
		  
		  <eespc:tableBodyCellDef id="b_id" method="getUserId"/>
		  <eespc:tableBodyCellDef id="b_eid" method="getUserId"/>
		  <eespc:tableBodyCellDef id="b_firstName" method="getFirstName"/>
		  <eespc:tableBodyCellDef id="b_lastName" method="getLastName"/>
		  <eespc:tableBodyCellDef id="b_initial" method="getMiddleName"/>
		  <eespc:tableBodyCellDef id="b_title" method="getTitle"/>
		  <eespc:tableBodyCellDef id="b_role" method="getUserRoleString"/>
		  <eespc:tableBodyCellDef id="b_clientAccess" method="getClientAccessString"/>		  
		  <eespc:tableBodyCellDef id="b_disabled" method="getDisabledIndicator"/>		  		  
		  		
		  <eespc:tr>
			  <eespc:th>Edit/View</eespc:th>  
			  <eespc:th>$$h_firstName$$</eespc:th>
			  <eespc:th>$$h_lastName$$</eespc:th>
			  <eespc:th>$$h_initial$$</eespc:th>
			  <eespc:th>$$h_title$$</eespc:th>
			  <eespc:th>$$h_role$$</eespc:th>	  	  	  
			  <eespc:th>$$h_clientAccess$$</eespc:th>	  	  	  			  
			  <eespc:th>$$h_disabled$$</eespc:th>
			  <eespc:th>Delete</eespc:th>	  	  	  			  			  
		  </eespc:tr>
		
		  <eespc:tr>
			  <eespc:tb>
			  <input type="radio" onclick="setInfo()" name="stackRsltRbtn" value="$$b_id$$" />
			  </eespc:tb>		  
			  <eespc:tb>$$b_firstName$$</eespc:tb>
			  <eespc:tb>$$b_lastName$$</eespc:tb>
			  <eespc:tb>$$b_initial$$</eespc:tb>
			  <eespc:tb>$$b_title$$</eespc:tb>	  	  	  
			  <eespc:tb>$$b_role$$</eespc:tb>	  	  	  			  
			  <eespc:tb><textarea name="dummy" cols="35" rows="3" readonly="true">$$b_clientAccess$$</textarea></eespc:tb>	  
			  <eespc:tb>$$b_disabled$$</eespc:tb>
			  <eespc:tb>
			  <a href="/eespc/UserAdministration.do?methodToCall=delete&userId=$$b_eid$$" ><img src='/eespc/images/delete.jpg' border=0></a>			  

			  </eespc:tb>	  	  	  			  			  			  	  	  			  			  
		  </eespc:tr>
		</eespc:table>
</html:form>	
<br>	
<font face="Verdana" size="2"><b>Login User Detail:</b></font><table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#336699" width="100%" id="AutoNumber1">
  <tr>
    <td width="6%" align="center" bgcolor="#FFFF99"><b>
    <font face="Verdana" size="2">No</font></b></td>
    <td width="25%" align="center" bgcolor="#FFFF99"><b>
    <font face="Verdana" size="2">Name</font></b></td>
    <td width="29%" align="center" bgcolor="#FFFF99"><b>
    <font face="Verdana" size="2">User id</font></b></td>
    <td width="20%" align="center" bgcolor="#FFFF99"><b>
    <font face="Verdana" size="2">Login time</font></b></td>
  </tr>
  <%
  List inspectionList = (List)session.getAttribute("LOGIN_USER_LIST");
			if (inspectionList != null){
				int size = inspectionList.size();
				for (int i=0; i < size; i++)
				{
					LoginUserVo inspVo = (LoginUserVo)inspectionList.get(i);
  
  %>
  <tr>
    <td width="6%" align="center"><%=(i+1)%>&nbsp;</td>
    <td width="25%" align="center"><%=inspVo.getUsername()%>&nbsp;</td>
    <td width="29%" align="center"><%=inspVo.getUserid()%>&nbsp;</td>
    <td width="20%" align="center"><%=inspVo.getLogintime()%>&nbsp;</td>
  </tr>
  <%
  }
  }
  %>
   <tr>
    <td width="80%" colspan="4">&nbsp;</td>
  </tr>
</table>
</body>
</html>