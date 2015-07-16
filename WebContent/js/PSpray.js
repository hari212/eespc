function addDep(updateFlag)
{

	if(isdateFieldEnteredcheckdate(document.forms[0].psDEPIssueDate) ==false)
	{
		document.forms[0].psDEPIssueDate.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].psDEPExpirationDate) ==false)
	{
		document.forms[0].psDEPExpirationDate.focus();
		return false;	
	}	

	
	if (updateFlag)
	{
    		document.forms[0].methodToCall.value='depUpdate';
    	}
	else
	{
        	document.forms[0].methodToCall.value='dep';
      }
      document.forms[0].submit();
      return true;

}


function addState(updateFlag){
	if(isdateFieldEnteredcheckdate(document.forms[0].psStateIssueDate) ==false)
	{
		document.forms[0].psStateIssueDate.focus();
		return false;	
	}

	
	if (updateFlag)
	{
    		document.forms[0].methodToCall.value='stateUpdate';
    	}
	else
	{
        	document.forms[0].methodToCall.value='state';
      }
      document.forms[0].submit();
      return true;

}

function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}
function addspraybooth(updateFlag)
{
      if (document.forms[0].psId.value.length ==0)
	{
		alert("Please enter the Id.");
		document.forms[0].psId.focus();
		return false;		
	}
	else if(isyearFieldEnteredyear(document.forms[0].ps_yearInstalled)==false)
	{
		document.forms[0].ps_yearInstalled.focus();
		return false;
	}
	else if(document.forms[0].ps_status.selectedIndex==0)
	{
		alert("Please Select Status.");
		document.forms[0].ps_status.focus();
		return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].ps_disconnecteddate) ==false)
	{
		document.forms[0].ps_disconnecteddate.focus();
		return false;	
	}

	if (updateFlag)
	{
		document.forms[0].methodToCall.value='update';					
	}
	else
	{
		document.forms[0].methodToCall.value='save';
	}
		document.forms[0].submit();	
		return true;

	
}




function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=P_fuelFrom&formIdName=P_fuelFromName";
	window.open(url, 'StackSearch', 500, 500);
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
			else  
			{
			tempVal1= true;
			}
	}	
return tempVal1;
}