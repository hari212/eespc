//************************************************************
var clientText = new Array();
var clientValue = new Array();
function init(){
	var formObj = document.forms[0].clientAccess;
	for(var i=0; i<formObj.length; i++)
	{
		clientText[i] = new Array(formObj[i].text);	
		clientValue[i] = new Array(formObj[i].value);
	}
}

function goToUserSearch(){
	var url = "/eespc/UserListInfo.do";
	window.open(url, 'UserSearch', "scrollbars=1,width=500,height=500");
}
function validateAdminScreen()
{
	if(
		isFieldEntered(document.forms[0].firstName) ==true && 
		isFieldEntered(document.forms[0].lastName) ==true && 		
		isFieldEntered(document.forms[0].address1) ==true && 
		isFieldEntered(document.forms[0].city) ==true && 						
		isDropDownSelected(document.forms[0].state) ==true && 						
		isFieldEntered(document.forms[0].zipCode) ==true && 
		isDropDownSelected(document.forms[0].role) ==true &&						
		isRadioChecked(document.forms[0].disableAccess) ==true &&
		isFieldEntered(document.forms[0].clientAccess) ==true &&
		isFieldEntered(document.forms[0].loginId) ==true && 						
		isFieldEntered(document.forms[0].userPassword) ==true 		
		){
			if(validateUserEmail() == true &&
				validateUserPhone()==true )
			{
				document.forms[0].submit();			
			}else{
				return false;				
			}

		}else{
			printMandatoryAlert();
			return false;
		}
		return true;
}
function validateUserEmail()
{
	var allEmailIdsValid = true;
	if (isFieldEntered(document.forms[0].email) ==true)
	{
		allEmailIdsValid = validateEmail(document.forms[0].email);	
	}else{
		printMandatoryAlert();
		return false;
	}
	if (isFieldEntered(document.forms[0].alternateEmail) ==true)
	{
		allEmailIdsValid = validateEmail(document.forms[0].alternateEmail);
	}
	return allEmailIdsValid;
}
function validateUserPhone()
{
	var allPhoneNumberValid = true;
	if (isFieldEntered(document.forms[0].phone) ==true)
	{
		allPhoneNumberValid = checkPhone(document.forms[0].phone.value);	
	}else{
		printMandatoryAlert();
		return false;
	}
	if (isFieldEntered(document.forms[0].alternatePhone) ==true)
	{
		allPhoneNumberValid = checkPhone(document.forms[0].alternatePhone.value);
	}
	return allPhoneNumberValid;
}
/*
function validateEmail(_formObj)
{
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(_formObj.value)){
		return (true)
	}
	alert("Invalid E-mail Address! Please re-enter.")
	_formObj.focus();
	return (false)
}
*/
function setClientAccess()
{
	var tempRole = parseInt(document.forms[0].role[document.forms[0].role.selectedIndex].value);
	if (tempRole != -1)
	{
		emptyDropDown(document.forms[0].clientAccess);
		var index = parseInt(0);
		for (var j=0; j<clientText.length; j++)
		{
			var tempClientValue = parseInt(clientValue[j]);
			if (tempRole == 3) //Super User
			{
			

				if (tempClientValue==100) 
				{
					continue;
				}
			}else{//Not Super User Role
				if (tempClientValue == 100) 
				{
					continue;
				}
			}
//			alert("adding.." + index + "." + clientText[j] + "  " + clientValue[j]);
			document.forms[0].clientAccess.options[index] = new Option(clientText[j],clientValue[j]);		
			index++;						
		}//for loop
	}else{
		//reset to all the possible values.
		emptyDropDown(document.forms[0].clientAccess);	
		var index = parseInt(0);	
		for (var j=0; j<clientText.length; j++)
		{
			document.forms[0].clientAccess.options[index] = new Option(clientText[j],clientValue[j]);		
			index++;						
		}
	}
			if (tempRole == 3) //Super User
			{
				for(i=0;i<document.forms[0].clientAccess.options.length;i++)
				{
				document.forms[0].clientAccess.options[i].selected=true;
				}
			}
}
function emptyDropDown(controlToPopulate)
{
	 // Empty the second drop down box of any choices
	for (var q=controlToPopulate.options.length;q>=0;q--) controlToPopulate.options[q]=null;
}
