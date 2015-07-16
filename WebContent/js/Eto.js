// JavaScript Document
function addEto(updateFlag)
{

if (document.forms[0].facilityDesignatedId.value.length ==0)
	{
		alert("Please enter the Id.");
		document.forms[0].facilityDesignatedId.focus();
		return false;		
	}
	else if(isRadioChecked(document.forms[0].isabator) ==false)
	{
       	alert("Please Select IsAbator:");	 
		return false;
	}
	else if(document.forms[0].ModifiedInPast.selectedIndex==0)
	{
	alert("Please Select Status.");
	document.forms[0].ModifiedInPast.focus();
	return false;
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].installationDate) ==false)
	{
		document.forms[0].installationDate.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].A_permitdate) ==false)
	{
		document.forms[0].A_permitdate.focus();
		return false;	
	}
	else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear) ==false)
	{
		document.forms[0].DisconnectedYear.focus();
		return false;	
	}


    if (updateFlag){
    	document.forms[0].methodToCall.value='update';
    }else{
    	document.forms[0].methodToCall.value='add';
    }
    document.forms[0].submit();
    return true;
}

function displaycontrol()
{
document.forms[0].methodToCall.value='displayControl';
    
    document.forms[0].submit();
    return true;
}

function checkGasMixType()
{
    if (isDropDownSelected(document.forms[0].mixtureType) ==false)
    {
        return false;
    }
    
    var tmp = document.forms[0].mixtureType[document.forms[0].mixtureType.selectedIndex].value;
    if (tmp == 1){//canister
        return isFieldEntered(document.forms[0].containerWeight);
    }else if (tmp == 2){//cylider
        return isFieldEntered(document.forms[0].gasMixture);    
    }
    
    return true;
}

function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=stackId&formIdName=stackName";
	window.open(url, 'StackSearch', 500, 500);
}
function mixTypeChanged()
{
	var tmp = document.forms[0].mixtureType[document.forms[0].mixtureType.selectedIndex].value;
	if (tmp == 1){//canister
		document.forms[0].containerWeight.disabled = false;
		document.forms[0].gasMixture.disabled = true;
	}else if (tmp == 2){//cylider
		document.forms[0].containerWeight.disabled = true;
		document.forms[0].gasMixture.disabled = false;
	}else{
		document.forms[0].containerWeight.disabled = true;
		document.forms[0].gasMixture.disabled = true;
	}
}
function addDep(updateFlag)
{
    
if(isdateFieldEnteredcheckdate(document.forms[0].depIssueDate) ==false)
	{
		document.forms[0].depIssueDate.focus();
		return false;	
	}
else if(isdateFieldEnteredcheckdate(document.forms[0].depExpDate) ==false)
	{
		document.forms[0].depExpDate.focus();
		return false;	
	}

    if(updateFlag){
    	document.forms[0].methodToCall.value='updateDepPermit';    
    }else{
    	document.forms[0].methodToCall.value='addDepPermit';
    }
    document.forms[0].submit();
    return true;

}
function addState(updateFlag)
{
    if(isDropDownSelected(document.forms[0].stateYear)==false){
        printMandatoryAlert();
        return false;    
    }        
    if ( validatePermitDate(document.forms[0].stateIssueDate, document.forms[0].stateExpDate)==false ){
       return false;
    }
    if(updateFlag){
    	document.forms[0].methodToCall.value='updateStatePermit';    
    }else{
    	document.forms[0].methodToCall.value='addStatePermit';
    }
    document.forms[0].submit();
    return true;
}
function addTest(updateFlag)
{
 if(isdateFieldEnteredcheckdate(document.forms[0].testLastDate) ==false)
	{
		document.forms[0].testLastDate.focus();
		return false;	
	}
 else if(isdateFieldEnteredcheckdate(document.forms[0].testNextDate) ==false)
	{
		document.forms[0].testNextDate.focus();
		return false;	
	}



    if(updateFlag){
    	document.forms[0].methodToCall.value='updateTestInfo';    
    }else{
    	document.forms[0].methodToCall.value='addTestInfo';
    }
    document.forms[0].submit();
    return true;
}

function editTest(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editTestInfo';
	document.forms[0].submit();

}

function deleteTest(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteTestInfo';
	document.forms[0].submit();
}
 else
 {
 
  }
}

function editDepPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editDepPermit';
	document.forms[0].submit();

}


function deleteDepPermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteDepPermit';
	document.forms[0].submit();
}
 else
 {
 
  }
}

function editStatePermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editStatePermit';
	document.forms[0].submit();

}


function deleteStatePermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteStatePermit';
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
