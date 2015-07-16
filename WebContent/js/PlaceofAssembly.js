
function addPlaceofAssembly(updateFlag)
{
	if(isFieldEntered(document.forms[0].facilitydesignatedid)==false)
	{
       	alert("Please Enter the Facility Designated Id:");
	 	document.forms[0].facilitydesignatedid.focus();
	 	return false;
	}	
	else if(document.forms[0].paType.selectedIndex ==0)
	{
       	alert("Please Choose PA Type:");
	 	document.forms[0].paType.focus();
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


function addDob(updateFlag)
{
	

	if(isdateFieldEnteredcheckdate(document.forms[0].dobExpirationDate)==false)
	{
	document.forms[0].dobExpirationDate.focus();
	return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate)==false)
	{
	document.forms[0].dobIssueDate.focus();
	return false;
	}

	if (updateFlag)
      	{
    		document.forms[0].methodToCall.value='updateDobPermitInfo';
    	}else
	{
		document.forms[0].methodToCall.value='dobPermitInfo';
	}
	document.forms[0].submit();
            
    return true;

}


function editDobPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editDobPermit';
	document.forms[0].submit();
}
function deleteDobPermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteDobPermit';
	document.forms[0].submit();
}
 else
 {
 
  }
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

