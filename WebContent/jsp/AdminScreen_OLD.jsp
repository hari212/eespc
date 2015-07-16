<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
		<head>
			<title>User Adminstration</title>
			<html:base />
			<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<script src="/eespc/js/CommonScript.js" ></script>
			<script src="/eespc/js/AdminScreen.js" ></script>
		</head>
      <body onLoad="init();">
	  <form name="form1">
        <SPAN CLASS=page_title>User Administration</SPAN><br>		
		<br>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">

          <tr> 

            <td width="47%"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="35%" nowrap class="label_right">First Name :*</td>
            <td width="65%"  nowrap class="fieldleft">&nbsp; 
            <input name="firstName" type="text" class="normal" id="firstName" size="20"> 
              <input name="hiddenField" type="hidden" value="userId"> &nbsp; <input type="button" name="Submit22" value="Search" onClick="goToUserSearch();"></td>
          </tr>
          <tr> 
            <td nowrap class="label_right">Last Name :*</td>
            <td  nowrap class="fieldleft">&nbsp; 
            <input name="lastName" type="text" class="normal" id="lastName" size="20"> 
              &nbsp; </td>
          </tr>
          <tr> 
            <td nowrap class="label_right">Initial :</td>
            <td  nowrap class="fieldleft">&nbsp; 
            <input name="initial" type="text" class="normal" id="initial" maxlength="15" size="20"> 
              &nbsp; </td>
          </tr>
          <tr> 
            <td nowrap class="label_right">Title :</td>
            <td  nowrap class="fieldleft">&nbsp; 
            <input name="title" type="text" class="normal" id="title" maxlength="15" size="20"> 
              &nbsp; </td>
          </tr>
          <tr> 
            <td colspan="2" nowrap class="label_right"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr> 
                  <td width="35%" rowspan="3">Address :</td>
                  <td width="65%">&nbsp; 
                  <input name="address1" type="text" class="normal" id="address1" size="20">
                    *</td>
                </tr>
                <tr> 
                  <td>&nbsp; 
                  <input name="address2" type="text" class="normal" id="address2" size="20"></td>
                </tr>
                <tr> 
                  <td>&nbsp; 
                  <input name="address3" type="text" class="normal" id="address3" size="20"></td>
                </tr>
              </table></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>City :*</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="city" type="text" class="normal" id="city" size="20"></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>State :*</td>
            <td class="fieldleft"  nowrap>&nbsp; <select name="state" class="normal" id="state">
                <option value="-1" selected>Please select</option>
                <option value="AK">AK</option>
                <option value="AL">AL</option>
                <option value="AR">AR</option>
                <option value="AZ">AZ</option>
                <option value="CA">CA</option>
                <option value="CO">CO</option>
                <option value="CT">CT</option>
                <option value="DC">DC</option>
                <option value="DE">DE</option>
                <option value="FL">FL</option>
                <option value="FM">FM</option>
                <option value="GA">GA</option>
                <option value="GU">GU</option>
                <option value="HI">HI</option>
                <option value="IA">IA</option>
                <option value="ID">ID</option>
                <option value="IL">IL</option>
                <option value="IN">IN</option>
                <option value="KS">KS</option>
                <option value="KY">KY</option>
                <option value="LA">LA</option>
                <option value="MA">MA</option>
                <option value="MD">MD</option>
                <option value="ME">ME</option>
                <option value="MI">MI</option>
                <option value="MN">MN</option>
                <option value="MO">MO</option>
                <option value="MS">MS</option>
                <option value="MT">MT</option>
                <option value="NC">NC</option>
                <option value="ND">ND</option>
                <option value="NE">NE</option>
                <option value="NH">NH</option>
                <option value="NJ">NJ</option>
                <option value="NM">NM</option>
                <option value="NV">NV</option>
                <option value="NY">NY</option>
                <option value="OH">OH</option>
                <option value="OK">OK</option>
                <option value="OR">OR</option>
                <option value="PA">PA</option>
                <option value="PR">PR</option>
                <option value="RI">RI</option>
                <option value="SC">SC</option>
                <option value="SD">SD</option>
                <option value="TN">TN</option>
                <option value="TX">TX</option>
                <option value="UT">UT</option>
                <option value="VA">VA</option>
                <option value="VI">VI</option>
                <option value="VT">VT</option>
                <option value="WA">WA</option>
                <option value="WI">WI</option>
                <option value="WV">WV</option>
                <option value="WY">WY</option>
                <option value="AB">AB</option>
                <option value="BC">BC</option>
                <option value="MB">MB</option>
                <option value="NB">NB</option>
                <option value="NF">NF</option>
                <option value="NT">NT</option>
                <option value="NS">NS</option>
                <option value="NU">NU</option>
                <option value="ON">ON</option>
                <option value="PE">PE</option>
                <option value="QC">QC</option>
                <option value="SK">SK</option>
                <option value="YT">YT</option>
              </select> </td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Zip Code :*</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="zipCode" type="text" class="normal" id="zipCode" size="20"></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Phone :*</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="phone" type="text" class="normal" id="phone" maxlength="12" size="20"></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Alternate Phone :</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="alternatePhone" type="text" class="normal" id="alternatePhone" maxlength="12" size="20"></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Email :*</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="email" type="text" class="normal" id="email" size="20"></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Alternate Email :</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="alternateEmail" type="text" class="normal" id="alternateEmail" size="20"></td>
          </tr>
        </table></td>

            <td width="53%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr> 
            <td width="29%" nowrap class="label_right">Role :*</td>
            <td width="71%"  nowrap class="fieldleft">&nbsp; <select name="role" class="normal" id="role" onChange="setClientAccess();">
                <option value="-1" selected>Please select</option>
                <option value="1">Employee</option>
                <option value="2">Client User</option>
                <option value="3">Super User</option>
              </select> </td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Disable Access :*</td>
            <td class="fieldleft"  nowrap><input type="radio" name="disableAccess" value="Y">
              Yes 
              <input name="disableAccess" type="radio" value="N" checked>
              No </td>
          </tr>
          <tr> 
            <td nowrap class="label_right" valign="top">Client Access :*</td>
            <td  nowrap class="fieldleft">&nbsp; <select name="clientAccess" size="10" multiple id="clientAccess" >
                <option value="100">All Clients </option>
                <option value="1">Client 1</option>
                <option value="2">Client 2</option>
              </select> </td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Login Id :*</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="loginId" type="text" class="normal" id="loginId" size="20"></td>
          </tr>
          <tr> 
            <td class="label_right" nowrap>Password :*</td>
            <td class="fieldleft"  nowrap>&nbsp; 
            <input name="userPassword" type="password" class="normal" id="userPassword" size="20"></td>
          </tr>
          <tr> 
            <td>&nbsp;</td>
            <td>&nbsp;</td>
          </tr>
        </table></td>

          </tr>

          <tr align="center"> 

            <td colspan="2"><input type="button" name="Button2" value="Save" onclick="validateAdminScreen();"> &nbsp;&nbsp;

              <input type="reset" name="Submit23" value="Reset"></td>

          </tr>

        </table>

        <hr>
</form>
</body>
</html>