
function validateDep(){
	if (    document.forms[0].lfDEPNumber.value.length ==0 || document.forms[0].lfDEPIssueDate.value.length ==0 || document.forms[0].lfDEPExpirationDate.value.length ==0)
	{
		alert("Please enter all the mandatory information for DEP.");
		return false;		
	}
	return true;
}


function validateLoc(){
	if (document.forms[0].lfLocNumber.value.length ==0 || document.forms[0].lfLocIssueDate.value.length ==0 || document.forms[0].lfLocExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory building information.");
		return false;		
	}
	return true;
}


function validateState(){
	if (document.forms[0].lfStateIssueDate.value.length ==0 ||document.forms[0].lfStateExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory State information.");
		return false;		
	}
	return true;
}
