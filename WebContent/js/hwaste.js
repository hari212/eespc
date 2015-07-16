


function addHwaste(updateFlag)
{
	if(isFieldEntered(document.forms[0].manifastno)==false)
	{
       	alert("Please Enter the Manifest No:");
	 	document.forms[0].manifastno.focus();
	 	return false;
	}      
	else if (document.forms[0].dateOftheManifest.value.length ==0)
      {	
		alert("Please Enter Manifest Date.");
		document.forms[0].dateOftheManifest.focus();
		return false;	
      }
	else if(document.forms[0].hazardousWasteCategory.selectedIndex==0)
      {
	  alert("Please Select Waste Category");
        document.forms[0].hazardousWasteCategory.focus();
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






