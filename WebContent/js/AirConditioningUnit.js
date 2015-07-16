
function addAirConditioning(updateFlag)
{
	if(isFieldEntered(document.forms[0].facilitydesignatedid)==false)
	{
       	alert("Please Enter the Facility Designated Id:");
	 	document.forms[0].facilitydesignatedid.focus();
	 	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].dobissuedate)==false)
	{
       	document.forms[0].dobissuedate.focus();
        	return false;       	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].epasubmittaldate) ==false)
	{
           	document.forms[0].epasubmittaldate.focus();
        	return false;        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].epaapprovaldate) ==false)
	{
       
        	document.forms[0].epaapprovaldate.focus();
        	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].fdaapprovaldate) ==false)
	{
       
        	document.forms[0].fdaapprovaldate.focus();
        	return false;
	}
	else if(document.forms[0].status.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].status.focus();
	 	return false;
	}
      else if(isFieldEntered(document.forms[0].capacity)==false)
	{
       	alert("Please Enter the capacity:");
	 	document.forms[0].capacity.focus();
	 	return false;
	}


	else if(isyearFieldEnteredyear(document.forms[0].disconnectedyear) ==false)
	{
		document.forms[0].disconnectedyear.focus();
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






