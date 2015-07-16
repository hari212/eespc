function dayTankCheck()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

function addLocationPermit(updateFlag)
{
   
if(isdateFieldEnteredcheckdate(document.forms[0].locationPermitIssueDate)==false)
{
document.forms[0].locationPermitIssueDate.focus();
return false;
}
else if(isdateFieldEnteredcheckdate(document.forms[0].locationPermitExpireDate)==false)
{
document.forms[0].locationPermitExpireDate.focus();
return false;
}


    if(updateFlag){
    document.forms[0].methodToCall.value='updateLocationPermit';
    }else{
    document.forms[0].methodToCall.value='addLocationPermit';
    }
    document.forms[0].submit();
    return true;
}

function addSatePermit(updateFlag)
{
    
if(isdateFieldEnteredcheckdate(document.forms[0].statePermitIssueDate)==false)
{
document.forms[0].statePermitIssueDate.focus();
return false;
}
else if(isdateFieldEnteredcheckdate(document.forms[0].statePermitExpireDate)==false)
{
document.forms[0].statePermitExpireDate.focus();
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

function addSpillCompliance(updateFlag)
{
    
if(isdateFieldEnteredcheckdate(document.forms[0].spillDate)==false)
{
document.forms[0].spillDate.focus();
return false;
}



    if(updateFlag){
    document.forms[0].methodToCall.value='updateSpillCompliance';
    }else{
    document.forms[0].methodToCall.value='addSpillCompliance';
    }
    document.forms[0].submit();
    return true;
}

function tankTightness(updateFlag)
{
 
if(isdateFieldEnteredcheckdate(document.forms[0].tTestDate)==false)
{
document.forms[0].tTestDate.focus();
return false;
}
else if(isdateFieldEnteredcheckdate(document.forms[0].tNextTestDate)==false)
{
document.forms[0].tNextTestDate.focus();
return false;
}

    if (updateFlag){
    	document.forms[0].methodToCall.value='updateTankTightness';
	}else{
		document.forms[0].methodToCall.value='addTankTightness';
	}
    document.forms[0].submit();
    return true;
}


function tankTrienial(updateFlag)
{
 
if(isdateFieldEnteredcheckdate(document.forms[0].tLastTestDate)==false)
{
document.forms[0].tLastTestDate.focus();
return false;
}
else if(isdateFieldEnteredcheckdate(document.forms[0].tNextTestDueDate)==false)
{
document.forms[0].tNextTestDueDate.focus();
return false;
}
else if(isdateFieldEnteredcheckdate(document.forms[0].tActualTestDate)==false)
{
document.forms[0].tActualTestDate.focus();
return false;
}

    if (updateFlag){
    	document.forms[0].methodToCall.value='updateTrienialCathodicProtection';
	}else{
		document.forms[0].methodToCall.value='addTrienialCathodicProtection';
	}
    document.forms[0].submit();
    return true;
}


function isYear(s){
    var i;
	var message = "Please enter valid year between 1900 & current year";
    if(s.length < 4 || s.length > 4)
    {
       alert(message); 
       return false;
    }
    for (i = 0; i < 4; i++)
    {   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
           alert(message); 
           return false;
        }
    }
    var yr = s;
 	dteDate=new Date();
	var currYear = dteDate.getFullYear();
	
    if(s < 1900 || s > currYear) 
    {
       alert(message); 
       return false;
    }
    
    return true;
}
function addStorage(editFlag)
{

	if(document.forms[0].sTankId.value.length ==0)
	{
	alert("Please enter Tank id.");
	document.forms[0].sTankId.focus();
	return false;
	}
	else if(document.forms[0].sPbs.value.length ==0)
	{
	alert("Please PBS.");
	document.forms[0].sPbs.focus();
	return false;
	}
	else if(document.forms[0].sTankOpStatus.selectedIndex ==0)
	{
	alert("Please Select tank status.");
	document.forms[0].sTankOpStatus.focus();
	return false;
	}

	else if(document.forms[0].sLocation.selectedIndex ==0)
	{
	alert("Please Select Tank Location.");
	document.forms[0].sLocation.focus();
	return false;
	}

	else if(document.forms[0].sTankCorrosionProtection.selectedIndex ==0)
	{
	alert("Please Select corrosion protection type.");
	document.forms[0].sTankCorrosionProtection.focus();
	return false;
	}

	if(isyearFieldEnteredyear(document.forms[0].sYearInstalled)==false)
	{
		document.forms[0].sYearInstalled.focus();
		return false;
	}


      if(isyearFieldEnteredyear(document.forms[0].s_disconnecteddate)==false)
	{
		document.forms[0].s_disconnecteddate.focus();
		return false;
	}


	
	if(editFlag){
	   document.forms[0].methodToCall.value='update';
	}else{
	   document.forms[0].methodToCall.value='add';
	}
	document.forms[0].submit();
	return true;
}
function deleteTankTightness(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteTankTightness';
	document.forms[0].submit();
}
 else
 {
 
  }
}
function editTankTightness(id)
{
     
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editTankTightness';
	document.forms[0].submit();
}


function deleteTrienialCathodicProtection(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteTrienialCathodicProtection';
	document.forms[0].submit();
}
 else
 {
 
  }
}
function editTrienialCathodicProtection(id)
{
     
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editTrienialCathodicProtection';
	document.forms[0].submit();
}



function editSpillCompliance(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editSpillCompliance';
	document.forms[0].submit();

}

function deleteSpillCompliance(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteSpillCompliance';
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

function editLocationPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editLocationPermit';
	document.forms[0].submit();
}

function deleteLocationPermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteLocationPermit';
	document.forms[0].submit();
}
 else
 {
 
  }
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


