

function addDep(updateFlag){

	if(isdateFieldEnteredcheckdate(document.forms[0].rpzLastInspDate) ==false)
	{
		document.forms[0].rpzLastInspDate.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].rpzNextInspDate) ==false)
	{
		document.forms[0].rpzNextInspDate.focus();
		return false;	
	}	

	
	if (updateFlag){
    		document.forms[0].methodToCall.value='inspectionUpdate';
    	}else
	  {
        	document.forms[0].methodToCall.value='inspection';
        }
       document.forms[0].submit();
        return true;

}


function stateadd(updateFlag){


	if(isdateFieldEnteredcheckdate(document.forms[0].rpzLocIssueDate) ==false)
	{
		document.forms[0].rpzLocIssueDate.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].rpzLocExpirationDate) ==false)
	{
		document.forms[0].rpzLocExpirationDate.focus();
		return false;	
	}	


	if (updateFlag){
    		document.forms[0].methodToCall.value='stateUpdate';
    	}
     else
	  {
        	document.forms[0].methodToCall.value='statePermitInfo';
        }
       document.forms[0].submit();
        return true;

}

function add(updateFlag){

	if (document.forms[0].rpzId.value.length ==0)
	{
		alert("Please enter the Id.");
		document.forms[0].rpzId.focus();
		return false;		
	}
	else if(isyearFieldEnteredyear(document.forms[0].rpzYear)==false)
	{
	document.forms[0].rpzYear.focus();
	return false;
	}
	else if(document.forms[0].ModifiedInPast.selectedIndex==0)
	{
	alert("Please Select Status.");
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
