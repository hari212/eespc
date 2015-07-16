function validateDep(){
	if (document.forms[0].psDEPNumber.value.length ==0 || document.forms[0].psDEPIssueDate.value.length ==0 || document.forms[0].psDEPExpirationDate.value.length ==0)
	{
		alert("Please enter all the mandatory information for DEP.");
		return false;		
	}
    return true;
}

function validateLoc(){
	if (document.forms[0].psLocIssueDate.value.length ==0 || document.forms[0].lfLocExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory building information.");
		return false;		
	}
	return true;
}

function validateState(){
	if (document.forms[0].psStateIssueDate.value.length ==0 ||document.forms[0].psStateExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory State information.");
		return false;		
	}
	return true;
}

function validate(){
        alert("Inside validate");
	if (    document.forms[0].psId.value.length ==0 || 
	        document.forms[0].psFloor.value.length ==0 ||
	        document.forms[0].psMake.value.length ==0 ||
	        document.forms[0].psModel.value.length ==0 ||
	        document.forms[0].psHoursOfOperation.value.length ==0 ||
	        document.forms[0].psTypesOfChemical.selectedIndex != 0 ||
	        document.forms[0].psLocation.selectedIndex !=0 )
	{
		alert("Please enter all the mandatory information for Paint Spray Form.");
		return false;		
	}
	return true;
}

