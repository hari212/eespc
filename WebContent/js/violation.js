function addViolation(updateFlag)
{

    if(isFieldEntered(document.forms[0].violationno)==false)
	{
       	alert("Please Type  the Violation Number:");
	 	document.forms[0].violationno.focus();
	 	return false;
	}
	else if(isFieldEntered(document.forms[0].violationType)==false)
	{
       	alert("Please Type  the Violation Type:");
	 	document.forms[0].violationType.focus();
	 	return false;
	}

	else if(is_dateFieldEnteredcheckdate(document.forms[0].issueDate)==false)
	{
       	
        	document.forms[0].issueDate.focus();
        	return false;    		  
        	
	}
	
	else if(is_dateFieldEnteredcheckdate(document.forms[0].removalDate)==false)
	{
       	
        	document.forms[0].removalDate.focus();
        	return false;    		  
        	
	}

	else if(is_dateFieldEnteredcheckdate(document.forms[0].finalComplianceDate)==false)
	{
       	
        	document.forms[0].finalComplianceDate.focus();
        	return false;    		  
        	
	}
	else if(is_dateFieldEnteredcheckdate(document.forms[0].jDate)==false)
	{
       	
        	document.forms[0].jDate.focus();
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

function is_dateFieldEnteredcheckdate(formObject)
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
