// JavaScript Document
var fuelText = new Array();
var fuelValue = new Array();
function init(){
	var formObj = document.forms[0].primaryFuel;
	for(var i=0; i<formObj.length; i++)
	{
		fuelText[i] = new Array(formObj[i].text);	
		fuelValue[i] = new Array(formObj[i].value);			
	}
}
function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

function addCogenTurbine(updateFlag)
{

if(document.forms[0].facilityDesignatedId.value.length==0)
{
alert("Please Enter the Facility designatedId.");
document.forms[0].facilityDesignatedId.focus();
return false;
}
else if(document.forms[0].primaryUse.selectedIndex==0)
{
alert("Please Select Primary Use.");
document.forms[0].primaryUse.focus();
return false;
}
else if(document.forms[0].co_status.selectedIndex==0)
{
alert("Please Select Tank Status.");
document.forms[0].co_status.focus();
return false;
}
else if(document.forms[0].capacity.value.length==0)
{
alert("Please enter the Capacity (Co Gen).");
document.forms[0].capacity.focus();
return false;
}

else if(isRadioChecked(document.forms[0].burnerType) ==false)
{
       	alert("Please Select the Burner type(Co Gen):");
	 	document.forms[0].burnerType.focus();
		return false;
}
else if(isDropDownSelected(document.forms[0].primaryFuel) ==false)
{
       	alert("Please Select the Primary Fuel type(Co Gen):");
	 	document.forms[0].primaryFuel.focus();
		return false;
}
else if(document.forms[0].burnerType[0].checked && (isDropDownSelected(document.forms[0].secondaryFuel) ==false))
{
       	alert("Please Select the Secondary Fuel type(Co Gen):");
	 	document.forms[0].secondaryFuel.focus();
		return false;
		
}
else if(isRadioChecked(document.forms[0].isturbine) ==false )
{
       	alert("Please Select IsTirbine:");
	 	document.forms[0].isturbine.focus();
		return false;
}
else if(document.forms[0].isturbine[0].checked && document.forms[0].tcapacity.value.length==0)
{
       	alert("Please Enter Capacity for Turbine:");
	 	document.forms[0].tcapacity.focus();
		return false;
}
else if(document.forms[0].isturbine[0].checked && isRadioChecked(document.forms[0].tburnerType) ==false)
{
       	alert("Please Select Burnertype for Turbine:");
	 	document.forms[0].tburnerType.focus();
		return false;
}
else if(document.forms[0].isturbine[0].checked && isDropDownSelected(document.forms[0].tprimaryFuel) ==false)
{
       	alert("Please Select Primary fuel for Turbine:");
	 	document.forms[0].tprimaryFuel.focus();
		return false;
}
else if(document.forms[0].isturbine[0].checked && document.forms[0].tburnerType[0].checked && isDropDownSelected(document.forms[0].tsecondaryFuel) ==false)
{
       	alert("Please Select Secondary fuel for Turbine:");
	 	document.forms[0].tsecondaryFuel.focus();
		return false;
}





else if(isRadioChecked(document.forms[0].iswhrb) ==false)
{
       	alert("Please Select IsWHRB:");
	 	document.forms[0].iswhrb.focus();
		return false;
}
else if(document.forms[0].iswhrb[0].checked && document.forms[0].wcapacity.value.length==0)
{
       	alert("Please Enter Capacity for WHRB:");
	 	document.forms[0].wcapacity.focus();
		return false;
}
else if(document.forms[0].iswhrb[0].checked && isRadioChecked(document.forms[0].wburnerType) ==false)
{
       	alert("Please Select Burnertype for WHRB:");
	 	document.forms[0].wburnerType.focus();
		return false;
}
else if(document.forms[0].iswhrb[0].checked && isDropDownSelected(document.forms[0].wprimaryFuel) ==false)
{
       	alert("Please Select Primary fuel for WHRB:");
	 	document.forms[0].wprimaryFuel.focus();
		return false;
}
else if(document.forms[0].iswhrb[0].checked && document.forms[0].wburnerType[0].checked && isDropDownSelected(document.forms[0].wsecondaryFuel) ==false)
{
       	alert("Please Select Secondary fuel for WHRB:");
	 	document.forms[0].wsecondaryFuel.focus();
		return false;
}



else if(isyearFieldEnteredyear(document.forms[0].yearInstalled) ==false)
	{
		document.forms[0].yearInstalled.focus();
		return false;	
	}
else if(isyearFieldEnteredyear(document.forms[0].co_disconnecteddate) ==false)
	{
		document.forms[0].co_disconnecteddate.focus();
		return false;	
	}


else if(isdateFieldEnteredcheckdate(document.forms[0].testReportSubmitedDate)==false)
	{

		
        	document.forms[0].testReportSubmitedDate.focus();
        	return false;
    		  
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].co_StackTestDate) ==false)
	{
       
        	document.forms[0].co_StackTestDate.focus();
        	return false;
    		  
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].nextStackTest) ==false)
	{
       
        	document.forms[0].nextStackTest.focus();
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

function isControlEfficiencySelected()
{
	if (radioValueTrue(document.forms[0].controlSystem))
	{
		return isDropDownSelected (document.forms[0].controlEfficiency);
	}
	return true;
}

function dateCheck(_formObj){
	if(!_formObj){
		return true;
	}
    if (isDate(_formObj.value)==false){
        _formObj.focus();
        return false;
    }        
    return true;
}

function dualBurnerCheck()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].burnerType.length;k++)
	{
		if (document.forms[0].burnerType[k].checked && document.forms[0].burnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
    if(isDualBurner){
      	return isDropDownSelected(document.forms[0].secondaryFuel);
    }
   return true;
}

function burnerTypeSetting()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].burnerType.length;k++)
	{
		if (document.forms[0].burnerType[k].checked && document.forms[0].burnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}

	if (isDualBurner){
		emptyDropDown(document.forms[0].primaryFuel);
		var tt =0;
		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr = fuelValue[j];
			if (tempStr =="-1" || tempStr =="1"){
				document.forms[0].primaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
			document.forms[0].secondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr == "1") {
				tt =j;
			}
		}
		if(tt>0){
			document.forms[0].secondaryFuel.options[tt] = null;
		}
	}else{
		emptyDropDown(document.forms[0].primaryFuel);
		emptyDropDown(document.forms[0].secondaryFuel);

		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr2 = fuelValue[j];
			document.forms[0].primaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr2 ==-1) {
				document.forms[0].secondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
		}
	}
}

function tburnerTypeSetting()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].tburnerType.length;k++)
	{
		if (document.forms[0].tburnerType[k].checked && document.forms[0].tburnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}

	if (isDualBurner){
		emptyDropDown(document.forms[0].tprimaryFuel);
		var tt =0;
		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr = fuelValue[j];
			if (tempStr =="-1" || tempStr =="1"){
				document.forms[0].tprimaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
			document.forms[0].tsecondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr == "1") {
				tt =j;
			}
		}
		if(tt>0){
			document.forms[0].tsecondaryFuel.options[tt] = null;
		}
	}else{
		emptyDropDown(document.forms[0].tprimaryFuel);
		emptyDropDown(document.forms[0].tsecondaryFuel);

		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr2 = fuelValue[j];
			document.forms[0].tprimaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr2 ==-1) {
				document.forms[0].tsecondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
		}
	}
}



function wburnerTypeSetting()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].wburnerType.length;k++)
	{
		if (document.forms[0].wburnerType[k].checked && document.forms[0].wburnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}

	if (isDualBurner){
		emptyDropDown(document.forms[0].wprimaryFuel);
		var tt =0;
		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr = fuelValue[j];
			if (tempStr =="-1" || tempStr =="1"){
				document.forms[0].wprimaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
			document.forms[0].wsecondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr == "1") {
				tt =j;
			}
		}
		if(tt>0){
			document.forms[0].wsecondaryFuel.options[tt] = null;
		}
	}else{
		emptyDropDown(document.forms[0].wprimaryFuel);
		emptyDropDown(document.forms[0].wsecondaryFuel);

		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr2 = fuelValue[j];
			document.forms[0].wprimaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr2 ==-1) {
				document.forms[0].wsecondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
		}
	}
}




function emptyDropDown(controlToPopulate)
{
		 // Empty the second drop down box of any choices
        for (var q=controlToPopulate.options.length;q>=0;q--) controlToPopulate.options[q]=null;

}

function tfuelChanged(){
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].tburnerType.length;k++)
	{
		if (document.forms[0].tburnerType[k].checked && document.forms[0].tburnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
	var factor =0;
	if (isDualBurner){
		if(document.forms[0].tprimaryFuel.selectedIndex !=0 && 
			document.forms[0].tcapacity.value.length >0){
			document.forms[0].tnaturalGasFiringRate.value = (document.forms[0].tcapacity.value *0.01* 1000);
		}else{
			document.forms[0].tnaturalGasFiringRate.value = "";
		}
		if(document.forms[0].tsecondaryFuel.selectedIndex !=0 && 
			document.forms[0].tcapacity.value.length >0){
			var tmp = document.forms[0].tsecondaryFuel[document.forms[0].tsecondaryFuel.selectedIndex].value;
			if (tmp==2){
				factor = 140;
			}else if(tmp==3){
				factor = 145;
			}else if(tmp==4){
				factor = 150;
			}

			document.forms[0].toilFiringRate.value = parseFloat((document.forms[0].tcapacity.value *0.01* 1000)/factor).toFixed(2);
		}else{
			document.forms[0].toilFiringRate.value = "";		
		}
	}else{
		if(document.forms[0].tprimaryFuel.selectedIndex !=0 && 
			document.forms[0].tcapacity.value.length >0){
			var tmp = document.forms[0].tprimaryFuel[document.forms[0].tprimaryFuel.selectedIndex].value;
			if (tmp ==1){
				document.forms[0].tnaturalGasFiringRate.value = (document.forms[0].tcapacity.value *0.01* 1000);
				document.forms[0].toilFiringRate.value = "";			
			}else if (tmp==2){
				factor = 140;
				document.forms[0].tnaturalGasFiringRate.value = "";
			}else if(tmp==3){
				factor = 145;
				document.forms[0].tnaturalGasFiringRate.value = "";
			}else if(tmp==4){
				factor = 150;
				document.forms[0].tnaturalGasFiringRate.value = "";
			}
			
			if (tmp !=1){
				document.forms[0].toilFiringRate.value = parseFloat((document.forms[0].tcapacity.value *0.01* 1000)/factor).toFixed(2);			
			}
		}
	}
}



function wfuelChanged(){
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].wburnerType.length;k++)
	{
		if (document.forms[0].wburnerType[k].checked && document.forms[0].wburnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
	var factor =0;
	if (isDualBurner){
		if(document.forms[0].wprimaryFuel.selectedIndex !=0 && 
			document.forms[0].wcapacity.value.length >0){
			document.forms[0].wnaturalGasFiringRate.value = (document.forms[0].wcapacity.value *0.01* 1000);
		}else{
			document.forms[0].wnaturalGasFiringRate.value = "";
		}
		if(document.forms[0].wsecondaryFuel.selectedIndex !=0 && 
			document.forms[0].wcapacity.value.length >0){
			var tmp = document.forms[0].wsecondaryFuel[document.forms[0].wsecondaryFuel.selectedIndex].value;
			if (tmp==2){
				factor = 140;
			}else if(tmp==3){
				factor = 145;
			}else if(tmp==4){
				factor = 150;
			}

			document.forms[0].woilFiringRate.value = parseFloat((document.forms[0].wcapacity.value *0.01* 1000)/factor).toFixed(2);
		}else{
			document.forms[0].woilFiringRate.value = "";		
		}
	}else{
		if(document.forms[0].wprimaryFuel.selectedIndex !=0 && 
			document.forms[0].wcapacity.value.length >0){
			var tmp = document.forms[0].wprimaryFuel[document.forms[0].wprimaryFuel.selectedIndex].value;
			if (tmp ==1){
				document.forms[0].wnaturalGasFiringRate.value = (document.forms[0].wcapacity.value *0.01* 1000);
				document.forms[0].woilFiringRate.value = "";			
			}else if (tmp==2){
				factor = 140;
				document.forms[0].wnaturalGasFiringRate.value = "";
			}else if(tmp==3){
				factor = 145;
				document.forms[0].wnaturalGasFiringRate.value = "";
			}else if(tmp==4){
				factor = 150;
				document.forms[0].wnaturalGasFiringRate.value = "";
			}
			
			if (tmp !=1){
				document.forms[0].woilFiringRate.value = parseFloat((document.forms[0].wcapacity.value *0.01* 1000)/factor).toFixed(2);			
			}
		}
	}
}



//@TODO - to get the calorific value dynamically 
function fuelChanged(){
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].burnerType.length;k++)
	{
		if (document.forms[0].burnerType[k].checked && document.forms[0].burnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
	var factor =0;
	if (isDualBurner){
		if(document.forms[0].primaryFuel.selectedIndex !=0 && 
			document.forms[0].capacity.value.length >0){
			document.forms[0].naturalGasFiringRate.value = (document.forms[0].capacity.value *0.01* 1000);
		}else{
			document.forms[0].naturalGasFiringRate.value = "";
		}
		if(document.forms[0].secondaryFuel.selectedIndex !=0 && 
			document.forms[0].capacity.value.length >0){
			var tmp = document.forms[0].secondaryFuel[document.forms[0].secondaryFuel.selectedIndex].value;
			if (tmp==2){
				factor = 140;
			}else if(tmp==3){
				factor = 145;
			}else if(tmp==4){
				factor = 150;
			}

			document.forms[0].oilFiringRate.value = parseFloat((document.forms[0].capacity.value *0.01* 1000)/factor).toFixed(2);
		}else{
			document.forms[0].oilFiringRate.value = "";		
		}
	}else{
		if(document.forms[0].primaryFuel.selectedIndex !=0 && 
			document.forms[0].capacity.value.length >0){
			var tmp = document.forms[0].primaryFuel[document.forms[0].primaryFuel.selectedIndex].value;
			if (tmp ==1){
				document.forms[0].naturalGasFiringRate.value = (document.forms[0].capacity.value *0.01* 1000);
				document.forms[0].oilFiringRate.value = "";			
			}else if (tmp==2){
				factor = 140;
				document.forms[0].naturalGasFiringRate.value = "";
			}else if(tmp==3){
				factor = 145;
				document.forms[0].naturalGasFiringRate.value = "";
			}else if(tmp==4){
				factor = 150;
				document.forms[0].naturalGasFiringRate.value = "";
			}
			
			if (tmp !=1){
				document.forms[0].oilFiringRate.value = parseFloat((document.forms[0].capacity.value *0.01* 1000)/factor).toFixed(2);			
			}
		}
	}
}
function cogenAddDep(updateFlag)
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
		document.forms[0].methodToCall.value='updateDepPermitInfo';    	
    	}else{
		document.forms[0].methodToCall.value='addDepPermit';
	}
		document.forms[0].submit();
    
    return true;
}

function cogenAddStatePermit(updateFlag)
{
if(isdateFieldEnteredcheckdate(document.forms[0].stateIssueDate) ==false)
	{
       
        	document.forms[0].stateIssueDate.focus();
        	return false;
    		 
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].stateExpDate) ==false)
	{
       
        	document.forms[0].stateExpDate.focus();
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

function addFuelConsumption()
{
	validateFuelConsumption();
	document.forms[0].methodToCall.value='fuelConsumptionInfo';
	document.forms[0].submit();
	return true;
}
function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=stackFrom&formIdName=stackFromName";
	window.open(url, 'StackSearch', 500, 500);
}
function searchFuel(){
	var url = "/eespc/FuelTankListInfo.do?formId=fuelFrom&formIdName=fuelFromName&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}
function calcOtherUnit(){
	if (document.forms[0].capacity.value.length >0){
		var tempInt =0;
		tempInt = document.forms[0].capacity.value;
		document.forms[0].hpText.value = (tempInt * 1.34).toFixed(2);		
		document.forms[0].mmbtuText.value = (tempInt * 0.01).toFixed(2);
		fuelChanged();				
	}
}
function tcalcOtherUnit(){
	if (document.forms[0].tcapacity.value.length >0){
		var tempInt =0;
		tempInt = document.forms[0].tcapacity.value;
		document.forms[0].thpText.value = (tempInt * 1.34).toFixed(2);		
		document.forms[0].tmmbtuText.value = (tempInt * 0.01).toFixed(2);
		tfuelChanged();				
	}
}
function wcalcOtherUnit(){
	if (document.forms[0].wcapacity.value.length >0){
		var tempInt =0;
		tempInt = document.forms[0].wcapacity.value;
		document.forms[0].whpText.value = (tempInt * 1.34).toFixed(2);		
		document.forms[0].wmmbtuText.value = (tempInt * 0.01).toFixed(2);
		wfuelChanged();				
	}
}



function editDepPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editDepPermit';
	document.forms[0].submit();
}
function editStatePermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editStatePermit';
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
