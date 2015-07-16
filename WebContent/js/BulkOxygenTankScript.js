function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
	return true;
}
function add()
{
	if(isFieldEntered(document.forms[0].facilityDesignatedId)==false)
	{
       	alert("Please Enter the Fasility Designated Id:");
	 	document.forms[0].facilityDesignatedId.focus();
	 	return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].YearInstalled)==false)
	{
	document.forms[0].YearInstalled.focus();
	return false;

	}
	else if(document.forms[0].ModifiedInPast.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].ModifiedInPast.focus();
	 	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].lasttestdate) ==false)
	{       
        	document.forms[0].lasttestdate.focus();
        	return false;      	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].nexttestdate) ==false)
	{       
        	document.forms[0].nexttestdate.focus();
        	return false;      	
	}


	else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear) ==false)
	{
		document.forms[0].DisconnectedYear.focus();
		return false;	
	}


		document.forms[0].methodToCall.value='add';
		document.forms[0].submit();
		return true;
}

function update()
{

		if(isFieldEntered(document.forms[0].facilityDesignatedId)==false)
	{
       	alert("Please Enter the Fasility Designated Id:");
	 	document.forms[0].facilityDesignatedId.focus();
	 	return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].YearInstalled)==false)
	{
	document.forms[0].YearInstalled.focus();
	return false;

	}
	else if(document.forms[0].ModifiedInPast.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].ModifiedInPast.focus();
	 	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].lasttestdate) ==false)
	{       
        	document.forms[0].lasttestdate.focus();
        	return false;      	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].nexttestdate) ==false)
	{       
        	document.forms[0].nexttestdate.focus();
        	return false;      	
	}


	else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear) ==false)
	{
		document.forms[0].DisconnectedYear.focus();
		return false;	
	}

		document.forms[0].methodToCall.value='update';
		document.forms[0].submit();
		return true;
}

function addPermit()
{   
	if(isFieldEntered(document.forms[0].dobPermit)==false)
	{
       	alert("Please Enter the DOB Permit No:");
	 	document.forms[0].dobPermit.focus();
	 	return false;
	}
	
	else if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate) ==false)
	{       
        	document.forms[0].dobIssueDate.focus();
        	return false;      	
	}


	document.forms[0].methodToCall.value='addPermit';
	document.forms[0].submit();
      return true;
	
}
function editDobPermit(dobPermitId)
{
	document.forms[0].hdnPermitId.value=dobPermitId;
	document.forms[0].methodToCall.value='editDobPermit';
	document.forms[0].submit();
}

function deleteDobPermit(dobPermitId)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=dobPermitId;
	document.forms[0].methodToCall.value='deleteDobPermit';
	document.forms[0].submit();
}
 else
 { 
 }
}

function updateDob()
{
	if(isFieldEntered(document.forms[0].dobPermit)==false)
	{
       	alert("Please Enter the DOB Permit No:");
	 	document.forms[0].dobPermit.focus();
	 	return false;
	}
	
	else if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate) ==false)
	{       
        	document.forms[0].dobIssueDate.focus();
        	return false;      	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].dobExpirationDate) ==false)
	{       
        	document.forms[0].dobExpirationDate.focus();
        	return false;      	
	}

		document.forms[0].methodToCall.value='updateDob';
		document.forms[0].submit();
		return true;
}

function isdateFieldEnteredcheckdate(formObject)
{
var tempVal1=true;	
if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0)
			{
			tempVal1=isDate(formObject.value);
			}
		else  {
			tempVal1= true;
			}
	}	
	
	return tempVal1;
}

function isyearFieldEnteredyear(formObject)
{
var tempVal1=true;	
if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0)
			{
				var year = parseInt(formObject.value);
				if(year<1900)
				{
				alert("Please Enter Year Between 1900-2100 :");
      	 			tempVal1= false;
				}
      				else if(year>2100)
				{
				alert("Please Enter Year Between 1900-2100 :");			
				tempVal1= false;
				}	
			}
			else  {
			tempVal1= true;
			}
	}	
	return tempVal1;
}