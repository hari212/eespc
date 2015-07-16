
function addLsf(updateFlag)
{
	if(isFieldEntered(document.forms[0].firstname)==false)
	{
       	alert("Please Type  the First Name:");
	 	document.forms[0].firstname.focus();
	 	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].issuedate)==false)
	{       	
        	document.forms[0].issuedate.focus();
        	return false;         	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].expirationdate) ==false)
	{       
        	document.forms[0].expirationdate.focus();
        	return false;        	
	}
	else if(isFieldEntered(document.forms[0].operatingequipments) ==false)
	{
       	alert("Please Choose Type of C of F:");
	 	document.forms[0].operatingequipments.focus();
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






