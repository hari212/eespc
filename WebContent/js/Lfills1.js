function addlandfill(updateFlag)
{
      if (document.forms[0].lfId.value.length ==0)
	{
		alert("Please enter the Id.");
		document.forms[0].lfId.focus();
		return false;		
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].lfDate)==false)
	{
		document.forms[0].lfDate.focus();
		return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].lfDOBIssuedate)==false)
	{
		document.forms[0].lfDOBIssuedate.focus();
		return false;
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].aesdate)==false)
	{
		document.forms[0].aesdate.focus();
		return false;
	}

	else if(document.forms[0].lf_status.selectedIndex==0)
	{
		alert("Please Select Status.");
		document.forms[0].lf_status.focus();
		return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].lf_disconnecteddate) ==false)
	{
		document.forms[0].lf_disconnecteddate.focus();
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

function addDep(updateFlag)
{

	if(isdateFieldEnteredcheckdate(document.forms[0].lfDEPIssueDate) ==false)
	{
		document.forms[0].lfDEPIssueDate.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].lfDEPExpirationDate) ==false)
	{
		document.forms[0].lfDEPExpirationDate.focus();
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
function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

function getCoGenList(){     
	var url = "/eespc/CogenListAction.do";
	window.open(url, 'CoGenTurbineList', 1800, 1600);
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





