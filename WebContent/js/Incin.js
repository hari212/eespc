
function validateDep(){
	

       if(isdateFieldEnteredcheckdate(document.forms[0].incDEPIssueDate)==false)
	{	
        	document.forms[0].incDEPIssueDate.focus();
        	return false;	  
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].incDEPExpirationDate) ==false)
	{      
        	document.forms[0].incDEPExpirationDate.focus();
        	return false;  
        	
	}
	return true;
}


function validateCGA(){
	if (    document.forms[0].incCGADate.value.length ==0 || document.forms[0].incCGA.value.length ==0 || document.forms[0].incCGANext.value.length ==0)
	{
		alert("Please enter all the mandatory information for CGA.");
		return false;		
	}
	        if (!isDate(document.forms[0].incCGADate.value))
	        {   
	            alert("Invalid CGA issue Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].incCGANext.value))
	        {   
	            alert("Invalid CGA Expiration Date");
	            return false;
	        }


	dateFrom = chkdate(document.forms[0].incCGADate);
	dateTo   = chkdate(document.forms[0].incCGANext);

        if(Date.parse(dateFrom) < Date.parse(dateTo))
	        {
	        	return true;
				
		}
		else
		{
		      alert("Expiration date must be Greater than Issue Date");
		      return false; 
		}

	return true;
}

function validateLoc(){
	if (document.forms[0].incLocIssueDate.value.length ==0 || document.forms[0].incLocExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory building information.");
		return false;		
	}
	        if (!isDate(document.forms[0].incLocIssueDate.value))
	        {   
	            alert("Invalid Location issue Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].incLocExpirationDate.value))
	        {   
	            alert("Invalid Location Expiration Date");
	            return false;
	        }
	
	dateFrom = chkdate(document.forms[0].incLocIssueDate);
	dateTo   = chkdate(document.forms[0].incLocExpirationDate);

        if(Date.parse(dateFrom) <= Date.parse(dateTo))
	        {
	        	return true;
				
		}
		else
		{
		      alert("Expiration date must be Greater than Issue Date");
		      return false; 
		}
	
	return true;
}


function validateCons(){
	if (document.forms[0].incAnnualYear.value.length ==0 ||document.forms[0].incAnnualConsumption.value.length ==0 )
        {
		alert("Please enter all the mandatory Combustion information.");
		return false;		
	}
	if (document.forms[0].incAnnualConsumption.value.length > 0)
	 {
	    var cons = document.forms[0].incAnnualConsumption.value;
	    return isNumeric(cons," Annual Consumption ");
	 }

	return true;
}



function validateMe(){

	if(isFieldEntered(document.forms[0].incId)==false)
	{
       	alert("Please Enter the Fasility Designated Id:");
	 	document.forms[0].incId.focus();
	 	return false;
	}
	
	else if(document.forms[0].inc_status.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
	 	document.forms[0].inc_status.focus();
	 	return false;
	}

	

	else if(isRadioChecked(document.forms[0].incStackTested) ==false)
	{
       	alert("Please Select the Stack Test Required or Not:");	 	
		return false;
	}  


	else if(isdateFieldEnteredcheckdate(document.forms[0].testReportSubmitedDate)==false)
	{	
        	document.forms[0].testReportSubmitedDate.focus();
        	return false;     	
	} 

	else if(isdateFieldEnteredcheckdate(document.forms[0].i_StackTestDate)==false)
	{	
        	document.forms[0].i_StackTestDate.focus();
        	return false;     	
	} 

	else if(isdateFieldEnteredcheckdate(document.forms[0].nextStackTest)==false)
	{	
        	document.forms[0].nextStackTest.focus();
        	return false;     	
	} 

	else if(isdateFieldEnteredcheckdate(document.forms[0].i_SolidIssuedDate)==false)
	{	
        	document.forms[0].i_SolidIssuedDate.focus();
        	return false;     	
	} 

	else if(isdateFieldEnteredcheckdate(document.forms[0].i_SolidExpirationDate)==false)
	{	
        	document.forms[0].i_SolidExpirationDate.focus();
        	return false;     	
	} 


	if(isyearFieldEnteredyear(document.forms[0].incYearIns)==false)
	{
	document.forms[0].incYearIns.focus();
	return false;
	}

	if(isyearFieldEnteredyear(document.forms[0].inc_disconnecteddate) ==false)
	{
		document.forms[0].inc_disconnecteddate.focus();
		return false;	
	}

	
	return true;
}


function validateStack(){
        
	if (	document.forms[0].incStackYear.selectedIndex == -1 ||
		document.forms[0].incStackBoilers.selectedIndex == -1 ||
		document.forms[0].incStackTested.selectedIndex == -1 ||
		document.forms[0].incStackOilType.selectedIndex ==-1 ||
		document.forms[0].incStackProtocol.selectedIndex ==-1 ||
		document.forms[0].incStackReport.selectedIndex ==-1 ||
		document.forms[0].incStackConductedBy.value.length ==0 ||
		document.forms[0].incStackSubmittedDate.value.length ==0 ||
                document.forms[0].incStackBoiler.selectedIndex ==-1 ||
                document.forms[0].incStackReTest.selectedIndex ==-1 ||
                document.forms[0].incStackTestDate.value.length ==0 )

		{
			alert("Please enter all the mandatory Stack Test Information.");
		        return false;		
	        }
	        if (!isDate(document.forms[0].incStackSubmittedDate.value))
	        {   
	            alert("Invalid Stack Submission Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].incStackTestDate.value))
	        {   
	            alert("Invalid Stack Test Date");
	            return false;
	        }
	
	return true;
}







function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function isNumeric(s , b){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
        { 
           alert( b + " Should be Numeric."); 
           return false;
        }
    }
    // All characters are numbers.
    return true;
}



function showCGA()
{
   a = document.getElementById("CGADiv");
   a.style.display = "inline"; 
   alert("Inside CGA");
}

function hideCGA()
{
   a1 = document.getElementById("CGADiv");
   a1.style.display = "none"; 
   alert("Inside CGA Hide");
}


function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=incStack&formIdName=incStackFrom";
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
			else  {
			tempVal1= true;
			}
	}	
	
	return tempVal1;
}
