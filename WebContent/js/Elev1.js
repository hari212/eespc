function validateDep(updateflag)
{
	if ( document.forms[0].elePermit.value.length ==0)
	{
		alert("Please Permit No.");
		document.forms[0].elePermit.focus();
		return false;		
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].eleLastInsDate)==false)
	{
		document.forms[0].eleLastInsDate.focus();
		return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].eleRepSubmDate)==false)
	{
		document.forms[0].eleRepSubmDate.focus();
		return false;
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].eleNextInspDate)==false)
	{
		document.forms[0].eleNextInspDate.focus();
		return false;
	}

document.forms[0].methodToCall.value=updateflag;
document.forms[0].submit();
return true;

}



function saveElevator(updateFlag){
	if (document.forms[0].eleId.value.length ==0)
	{
		alert("Please enter the Id.");
		document.forms[0].eleId.focus();
		return false;		
	}
	else if(isRadioChecked(document.forms[0].eleType) ==false)
	{
       	alert("Please Select the type:");	 
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
	document.forms[0].methodToCall.value='Update';					
	}else
	{
	document.forms[0].methodToCall.value='Save';
	}
	document.forms[0].submit();	
	return true;

      

}

function searchFuel(){
	var url = "/eespc/HTankListInfo.do?formId=eleHydTank&formIdName=eleHydTankFrom&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
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

