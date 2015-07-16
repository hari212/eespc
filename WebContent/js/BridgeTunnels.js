// JavaScript Document

function addBridgeTunnel()
{
    if ( isFieldEntered(document.forms[0].BT_facilityDesignatedId)==false )
    {
        alert("Please Enter Facility Designated Id");
        document.forms[0].BT_facilityDesignatedId.focus();
        //printMandatoryAlert();
        return false;
    }
	else if(isyearFieldEnteredyear(document.forms[0].YearInstalled)==false)
	{
	document.forms[0].YearInstalled.focus();
	return false;

	}
    else if(document.forms[0].ModifiedInPast.selectedIndex==0)
    {
	  alert("Please Select Status Of Source");
        document.forms[0].ModifiedInPast.focus();
        return false;
    }
    else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear)==false)
	{
	document.forms[0].DisconnectedYear.focus();
	return false;
	}
  else if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate) ==false)
	{   
        	document.forms[0].dobIssueDate.focus();
        	return false;      	
	}

    document.forms[0].methodToCall.value='add';
    document.forms[0].submit();
    return true;
}

function updateBridgeTunnel()
{
    if ( isFieldEntered(document.forms[0].BT_facilityDesignatedId)==false )
    {
        alert("Please Enter Facility Designated Id");
        document.forms[0].BT_facilityDesignatedId.focus();
        //printMandatoryAlert();
        return false;
    }
	else if(isyearFieldEnteredyear(document.forms[0].YearInstalled)==false)
	{
	document.forms[0].YearInstalled.focus();
	return false;

	}

    else if(document.forms[0].ModifiedInPast.selectedIndex==0)
    {
	  alert("Please Select Status Of Source");
        document.forms[0].ModifiedInPast.focus();
        return false;
    }
    else if(isyearFieldEnteredyear(document.forms[0].DisconnectedYear)==false)
	{
	document.forms[0].DisconnectedYear.focus();
	return false;

	}
  else if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate) ==false)
	{   
        	document.forms[0].dobIssueDate.focus();
        	return false;      	
	}

    document.forms[0].methodToCall.value='update';
    document.forms[0].submit();
    return true;
}


function BridgeTunnelInfo()
{
alert("hai");
}

function addDot(updateFlag)
{
 /* 
    if(isDropDownSelected(document.forms[0].dotYear)==false){
        printMandatoryAlert();
        return false;    
    }        
    if(isFieldEntered(document.forms[0].dotPermitNumber)==false || 
        isFieldEntered(document.forms[0].dotByWho)==false)
    {
        printMandatoryAlert();
        return false;
    }
    if ( validatePermitDate(document.forms[0].dotIssueDate, document.forms[0].dotExpDate)==false ){
        return false;       
    }
    if (isDate(document.forms[0].dotFillingDate.value)==false){
            issueDate.focus();
        return false;       
    }        
    var tempMessage = "Enter all mandatory dates, Last Inspection Date should be earlier than Next Inspection date";
    if( checkDates(document.forms[0].dotLastInspDate.value, document.forms[0].dotNextInspDate.value, tempMessage ) == false )
    {
        return false;           
    }
    var tempMessage2 = "Enter all mandatory dates, Insurance Issue Date should be earlier than Insurance Expiration date";
    if( checkDates(document.forms[0].dotInsIssueDate.value, document.forms[0].dotInsExpDate.value, tempMessage2 ) == false )
    {
        return false;           
    }
*/

if(isdateFieldEnteredcheckdate(document.forms[0].dotIssueDate) ==false)
	{   
        	document.forms[0].dotIssueDate.focus();
        	return false;      	
	}

else if(isdateFieldEnteredcheckdate(document.forms[0].dotExpDate) ==false)
	{   
        	document.forms[0].dotExpDate.focus();
        	return false;      	
	}
else if(isdateFieldEnteredcheckdate(document.forms[0].dotFillingDate) ==false)
	{   
        	document.forms[0].dotFillingDate.focus();
        	return false;      	
	}
else if(isdateFieldEnteredcheckdate(document.forms[0].dotLastInspDate) ==false)
	{   
        	document.forms[0].dotLastInspDate.focus();
        	return false;      	
	}


else if(isdateFieldEnteredcheckdate(document.forms[0].dotNextInspDate) ==false)
	{   
        	document.forms[0].dotNextInspDate.focus();
        	return false;      	
	}
else if(isdateFieldEnteredcheckdate(document.forms[0].dotInsIssueDate) ==false)
	{   
        	document.forms[0].dotInsIssueDate.focus();
        	return false;      	
	}
else if(isdateFieldEnteredcheckdate(document.forms[0].dotInsExpDate) ==false)
	{   
        	document.forms[0].dotInsExpDate.focus();
        	return false;      	
	}





    if(updateFlag)
    {
    	document.forms[0].methodToCall.value='updateDotPermitInfo';    
    }
    else
    {
    	document.forms[0].methodToCall.value='dotPermitInfo';
    }
    document.forms[0].submit();
    return true;
}

function addDob(updateFlag)
{
    /*var isCheckOk = true;
    if(isDropDownSelected(document.forms[0].dobYear)==false){
        printMandatoryAlert();
        isCheckOk = false;
        return false;    
    } 

       
    if ( validatePermitDate(document.forms[0].dobIssueDate, document.forms[0].dobExpDate)==false ){
       isCheckOk = false;
    }
    if(isFieldEntered(document.forms[0].dobPermitNumber)==false){
        printMandatoryAlert();
        isCheckOk = false;
    }
    if(isCheckOk ){
*/

	if(isdateFieldEnteredcheckdate(document.forms[0].dobExpDate) ==false)
	{   
        	document.forms[0].dobExpDate.focus();
        	return false;      	
	}

       if(updateFlag){
	  document.forms[0].methodToCall.value='updateDob';    
       }else{
	  document.forms[0].methodToCall.value='dobPermitInfo';
       }
	document.forms[0].submit();
            
    return true;
}
function editDotPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editPermitInfo';
	document.forms[0].submit();
}
function deleteDotPermit(id)
{

var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deletePermitInfo';
	document.forms[0].submit();
 }
 else
 {
 
  }
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
			tempVal1=isDate(formObject.value);
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
