
function addFireAlarm(updateFlag)
{
	if(isFieldEntered(document.forms[0].facilitydesignatedid)==false)
	{
       	alert("Please Enter the Facility Designated Id:");
	 	document.forms[0].facilitydesignatedid.focus();
	 	return false;
	}


	else if(isdateFieldEnteredcheckdate(document.forms[0].approvalDate) ==false)
	{
       
        	document.forms[0].approvalDate.focus();
        	return false;
    		   
        	
	}
else if(isdateFieldEnteredcheckdate(document.forms[0].fdApprovalDate) ==false)
	{
       
        	document.forms[0].fdApprovalDate.focus();
        	return false;
}
else if(isdateFieldEnteredcheckdate(document.forms[0].agencyApprovalDate) ==false)
	{
       
        	document.forms[0].agencyApprovalDate.focus();
        	return false;
}




	else if(document.forms[0].statusOfSource.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].statusOfSource.focus();
	 	return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].yearinstalled) ==false)
	{
		document.forms[0].yearinstalled.focus();
		return false;	
	}
        


	else if(isyearFieldEnteredyear(document.forms[0].disconnectedYear) ==false)
	{
		document.forms[0].disconnectedYear.focus();
		return false;	
	}
        
	if (updateFlag)
	{
	document.forms[0].methodToCall.value='update';					
	}else
	{
	document.forms[0].methodToCall.value='save';
	}
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
			tempVal1=isDate(formObject.value)
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






