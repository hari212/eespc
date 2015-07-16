
function addPress(updateFlag)
{
	if(isFieldEntered(document.forms[0].facilitydesinatedId)==false)
	{
       	alert("Please Enter the Fasility Designated Id:");
	 	document.forms[0].facilitydesinatedId.focus();
	 	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].depissuedate)==false)
	{
       	
        	document.forms[0].depissuedate.focus();
        	return false;
    		  
        	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].depexpirationdate) ==false)
	{
       
        	document.forms[0].depexpirationdate.focus();
        	return false;
    		   
        	
	}


	else if(document.forms[0].ModifiedInPast.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].ModifiedInPast.focus();
	 	return false;
	}

	else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear) ==false)
	{
		document.forms[0].DisconnectedYear.focus();
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






