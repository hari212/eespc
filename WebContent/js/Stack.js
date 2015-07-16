function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

function addStack(updateFlag){
	if (document.forms[0].facilityStackId.value.length ==0)    
       
         {    
            document.forms[0].facilityStackId.focus();
		alert("Please enter Facility Stack Id");
		return false;		
	    }
      else if(isyearFieldEnteredyear(document.forms[0].YearInstalled)==false)
	{
		return false;
	}
	if(updateFlag){
		document.forms[0].methodToCall.value='update';	
	}else{
		document.forms[0].methodToCall.value='add';
	}
	document.forms[0].submit();
	return true;

}


function addPermit(id, updateFlag){
	if (id==1){
            if(isFieldEntered(document.forms[0].staPermitNumber)==false){
                printMandatoryAlert();
                return false;    
            }
            if ( validatePermitDate(document.forms[0].staIssueDate, document.forms[0].staExpirationDate)==false ){
                return false;
            }
            if(updateFlag)
            {
            	document.forms[0].methodToCall.value='updateStatePermitInfo';	
            }else{
            	document.forms[0].methodToCall.value='statePermitInfo';	
            }
        }else if (id==2){
            if(isFieldEntered(document.forms[0].nyDobPermitNumber)==false){
                printMandatoryAlert();
                return false;    
            }
            if ( validatePermitDate(document.forms[0].nyDobIssueDate, document.forms[0].nyDobExpirationDate )==false ){
                return false;
            }
            if(updateFlag)
            {
            	document.forms[0].methodToCall.value='updateNyDobPermitInfo';	
            }else{
            	document.forms[0].methodToCall.value='nyDobPermitInfo';	
            }
        }else if (id==3){
            if(isFieldEntered(document.forms[0].diffStatePermitNumber)==false)
            {
                printMandatoryAlert();
                return false;    
            }
            if ( validatePermitDate(document.forms[0].diffStateIssueDate, document.forms[0].diffStateExpirationDate )==false )
            {
                return false;
            }
            if(updateFlag)
            {
            	document.forms[0].methodToCall.value='updateCommonPermitInfo';	
            }else{
            	document.forms[0].methodToCall.value='commonPermitInfo';	
            }
	}
	document.forms[0].submit();
	return true;	
}

function editStatePermitInfo(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editStatePermitInfo';
	document.forms[0].submit();
}

function deleteStatePermitInfo(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteStatePermitInfo';
	document.forms[0].submit();
  }
 else
 {
 
  }
}

function editNyDobPermitInfo(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editNyDobPermitInfo';
	document.forms[0].submit();
}

function deleteNyDobPermitInfo(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteNyDobPermitInfo';
	document.forms[0].submit();
}
 else
 {
 
  }

}

function editCommonPermitInfo(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editCommonPermitInfo';
	document.forms[0].submit();
}

function deleteCommonPermitInfo(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteCommonPermitInfo';
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
