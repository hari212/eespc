// JavaScript Document
var fuelText = new Array();
var fuelValue = new Array();
function init(){
	var formObj = document.forms[0].B_primaryFuel;
	for(var i=0; i<formObj.length; i++)
	{
		fuelText[i] = new Array(formObj[i].text);	
		fuelValue[i] = new Array(formObj[i].value);
	}
}

function addBoiler(updateFlag)
{
        
	if(isFieldEntered(document.forms[0].B_facilityDesignatedId)==false)
	{
       	alert("Please Enter the Fasility Designated Id:");
	 	document.forms[0].B_facilityDesignatedId.focus();
	 	return false;
	}
	else if(isFieldEntered(document.forms[0].B_stackFrom) ==false)
	{
       	alert("Please Choose Stack:");
	 	document.forms[0].B_stackFrom.focus();
	 	return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].B_yearInstalled)==false)
	{
		document.forms[0].B_yearInstalled.focus();
		return false;

	}
	else if(isFieldEntered(document.forms[0].B_capacity) ==false)
	{
       	alert("Please Enter the Capacity:");
	 	document.forms[0].B_capacity.focus();
	 	return false;
	}

	else if(isRadioChecked(document.forms[0].B_burnerType) ==false)
	{
       	alert("Please Select the Burner type:");
	 	document.forms[0].B_burnerType.focus();
		return false;
	}
	else if(isDropDownSelected(document.forms[0].B_primaryFuel) ==false)
	{
       	alert("Please Select the Primary Fuel type:");
	 	document.forms[0].B_primaryFuel.focus();
		return false;
	}
	else if(document.forms[0].B_burnerType[0].checked && (isDropDownSelected(document.forms[0].B_secondaryFuel) ==false))
	{
       	alert("Please Select the Secondary Fuel type:");
	 	document.forms[0].B_secondaryFuel.focus();
		return false;		
	}	
	else if(isdateFieldEnteredcheckdate(document.forms[0].B_protocolSubmitDate)==false)
	{		
        	document.forms[0].B_protocolSubmitDate.focus();
        	return false; 	  
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].B_StackTestDate) ==false)
	{       
        	document.forms[0].B_StackTestDate.focus();
        	return false;   		  
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].B_nextStackTestDate) ==false)
	{       
        	document.forms[0].B_nextStackTestDate.focus();
        	return false;   		 
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].B_blrModifiedOn) ==false)
	{      
        	document.forms[0].B_blrModifiedOn.focus();
        	return false;        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].B_prfmTestProtSubDate)==false)
	{       	
        	document.forms[0].B_prfmTestProtSubDate.focus();
        	return false;    		  
        	
	}
	else if(isdateFieldEnteredcheckdate(document.forms[0].B_prfmTestRptSubDate) ==false)
	{       
        	document.forms[0].B_prfmTestRptSubDate.focus();
        	return false;   		   
        	
	}
	else if(document.forms[0].ModifiedInPast.selectedIndex ==0)
	{
       	alert("Please Choose Status of the Source:");
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
	document.forms[0].methodToCall.value='update';					
	}else
	{
	document.forms[0].methodToCall.value='add';
	}
	document.forms[0].submit();	
	return true;

	
}



function boilerDateCheck(_formObj){
	if(!_formObj){
		return true;
	}
    if (isDate(_formObj.value)==false){
        _formObj.focus();
        return false;
    }        
    return true;
}
function boilerDateCheck(_radioObj, _textObj){
	if(!_radioObj){
		return true;
	}

	if(!_textObj){
		return true;
	}
	if(radioValueTrue(_radioObj))
	{
		if (isDate(_textObj.value)==false){
			_textObj.focus();
			return false;
		}else{
			return true;
		}
	}        
    return true;
}

function dualBurnerCheck()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].B_burnerType.length;k++)
	{
		if (document.forms[0].B_burnerType[k].checked && document.forms[0].B_burnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
        if(isDualBurner){
            return isDropDownSelected(document.forms[0].B_secondaryFuel);
        }
        
        return true;

}

function burnerTypeSetting()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].B_burnerType.length;k++)
	{
		if (document.forms[0].B_burnerType[k].checked && document.forms[0].B_burnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}

	if (isDualBurner){
		emptyDropDown(document.forms[0].B_primaryFuel);
		var tt =0;
		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr = fuelValue[j];
			if (tempStr =="-1" || tempStr =="1"){
				document.forms[0].B_primaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
			document.forms[0].B_secondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr == "1") {
				tt =j;
			}
		}
		if(tt>0){
			document.forms[0].B_secondaryFuel.options[tt] = null;
		}
	}else{
		emptyDropDown(document.forms[0].B_primaryFuel);
		emptyDropDown(document.forms[0].B_secondaryFuel);

		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr2 = fuelValue[j];
			document.forms[0].B_primaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr2 ==-1) {
				document.forms[0].B_secondaryFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
		}
	}
}
function emptyDropDown(controlToPopulate)
{
		 // Empty the second drop down box of any choices
        for (var q=controlToPopulate.options.length;q>=0;q--) controlToPopulate.options[q]=null;

}

//@TODO - to get the calorific value dynamically 
function fuelChanged(){
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].B_burnerType.length;k++)
	{
		if (document.forms[0].B_burnerType[k].checked && document.forms[0].B_burnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
	var factor =0;
	if (isDualBurner){
		if(document.forms[0].B_primaryFuel.selectedIndex !=0 && 
			document.forms[0].B_capacity.value.length >0){
			document.forms[0].B_naturalGasFiringRate.value = (document.forms[0].B_capacity.value * 1000);
		}else{
			document.forms[0].B_naturalGasFiringRate.value = "";
		}
		if(document.forms[0].B_secondaryFuel.selectedIndex !=0 && 
			document.forms[0].B_capacity.value.length >0){
			var tmp = document.forms[0].B_secondaryFuel[document.forms[0].B_secondaryFuel.selectedIndex].value;
			if (tmp==2){
				factor = 140;
			}else if(tmp==3){
				factor = 145;
			}else if(tmp==4){
				factor = 150;
			}
			document.forms[0].B_oilFiringRate.value = parseFloat((document.forms[0].B_capacity.value * 1000)/factor).toFixed(2);
		}else{
			document.forms[0].B_oilFiringRate.value = "";		
		}
	}else{
		if(document.forms[0].B_primaryFuel.selectedIndex !=0 && 
			document.forms[0].B_capacity.value.length >0){
			var tmp = document.forms[0].B_primaryFuel[document.forms[0].B_primaryFuel.selectedIndex].value;
			if (tmp ==1){
				document.forms[0].B_naturalGasFiringRate.value = (document.forms[0].B_capacity.value * 1000);
				document.forms[0].B_oilFiringRate.value = "";			
			}else if (tmp==2){
				factor = 140;
				document.forms[0].B_naturalGasFiringRate.value = "";
			}else if(tmp==3){
				factor = 145;
				document.forms[0].B_naturalGasFiringRate.value = "";
			}else if(tmp==4){
				factor = 150;
				document.forms[0].B_naturalGasFiringRate.value = "";
			}
			
			if (tmp !=1){
				document.forms[0].B_oilFiringRate.value = parseFloat((document.forms[0].B_capacity.value * 1000)/factor).toFixed(2);			
			}
		}
	}
}
function addDep(updateFlag)
{
   if(isdateFieldEnteredcheckdate(document.forms[0].depIssueDate)==false)
	{
	document.forms[0].depIssueDate.focus();
	return false;
	}
  else if(isdateFieldEnteredcheckdate(document.forms[0].depExpDate)==false)
	{
	document.forms[0].depExpDate.focus();
	return false;
	}

else if(isdateFieldEnteredcheckdate(document.forms[0].depInspSubDate)==false)
	{
	document.forms[0].depInspSubDate.focus();
	return false;
	}

/*else if(isdateFieldEnteredcheckdate(document.forms[0].depIssueDate)==true && isdateFieldEnteredcheckdate(document.forms[0].depExpDate)==true && validatePermitDate(document.forms[0].depIssueDate, document.forms[0].depExpDate)==false)
	{
	document.forms[0].depExpDate.focus();
	alert("Issue Date should be earlier than Expire date");
	return false;
	}
*/


			


    	if (updateFlag){
    		document.forms[0].methodToCall.value='updateDepPermitInfo';
    	}else
	  {
        	document.forms[0].methodToCall.value='depPermitInfo';
        }
       document.forms[0].submit();
        return true;
}

function addDob(updateFlag)
{
	

	if(isdateFieldEnteredcheckdate(document.forms[0].dobExpDate)==false)
	{
	document.forms[0].dobExpDate.focus();
	return false;
	}
  	else if(isdateFieldEnteredcheckdate(document.forms[0].dobInspSubDate)==false)
	{
	document.forms[0].dobInspSubDate.focus();
	return false;
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate)==false)
	{
	document.forms[0].dobIssueDate.focus();
	return false;
	}
	if (updateFlag)
      {
    		document.forms[0].methodToCall.value='updateDobPermitInfo';
    	}else
	{
		document.forms[0].methodToCall.value='dobPermitInfo';
	}
	document.forms[0].submit();
            
    return true;

}

function addTuneUp(updateFlag)
{
    if(isdateFieldEnteredcheckdate(document.forms[0].tuneUpDate)==false)
	{
	document.forms[0].tuneUpDate.focus();
	return false;
	}



    
    
    	if (updateFlag){
    		document.forms[0].methodToCall.value='updateAnnualTuneUpInfo';
    	}else{
    		document.forms[0].methodToCall.value='annualTuneUpInfo';
	}
	document.forms[0].submit();
           
    return true;

}



function addValveTest(updateFlag)
{
    if(isdateFieldEnteredcheckdate(document.forms[0].valveTestDate)==false)
	{
	document.forms[0].valveTestDate.focus();
	return false;
	}    
    
    	if (updateFlag){
    		document.forms[0].methodToCall.value='updateValveTestInfo';
    	}else{
    		document.forms[0].methodToCall.value='valveTestInfo';
	}
	document.forms[0].submit();
           
    return true;

}
/*
function validateFuelConsumption()
{
	var currentMonthValue = 0;
	var currentTotal = parseInt(document.forms[0].yearToDate.value);
	if (document.forms[0].hdnCurrentMonthIndex.value == "0"){
		currentMonthValue = document.forms[0].jan.value;
		currentTotal = currentTotal + parseInt(document.forms[0].jan.value);
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "1"){
		currentMonthValue = document.forms[0].feb.value
		currentTotal = currentTotal + parseInt(document.forms[0].feb.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "2"){
			currentMonthValue = document.forms[0].mar.value
		currentTotal = currentTotal + parseInt(document.forms[0].mar.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "3"){
			currentMonthValue = document.forms[0].apr.value
		currentTotal = currentTotal + parseInt(document.forms[0].apr.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "4"){		
		currentMonthValue = document.forms[0].may.value
		currentTotal = currentTotal + parseInt(document.forms[0].may.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "5"){	
		currentMonthValue = document.forms[0].jun.value
		currentTotal = currentTotal + parseInt(document.forms[0].jun.value);	
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "6"){
		currentMonthValue = document.forms[0].jul.value
		currentTotal = currentTotal + parseInt(document.forms[0].jul.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "7"){
		currentMonthValue = document.forms[0].aug.value
		currentTotal = currentTotal + parseInt(document.forms[0].aug.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "8"){
		currentMonthValue = document.forms[0].sep.value
		currentTotal = currentTotal + parseInt(document.forms[0].sep.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "9"){
		currentMonthValue = document.forms[0].oct.value
		currentTotal = currentTotal + parseInt(document.forms[0].oct.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "10"){	
		currentMonthValue = document.forms[0].nov.value
		currentTotal = currentTotal + parseInt(document.forms[0].nov.value);			
	}else if (document.forms[0].hdnCurrentMonthIndex.value == "11"){	
		currentMonthValue = document.forms[0].dec.value
		currentTotal = currentTotal + parseInt(document.forms[0].dec.value);			
	}
	document.forms[0].consumption.value = ((parseInt(document.forms[0].hdnConsumption.value) + parseInt(currentMonthValue) ) - document.forms[0].hdnPreviousConsumption.value);
	document.forms[0].yearToDate.value = currentTotal;
}
*/
function displayControl()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}
function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=B_stackFrom&formIdName=B_stackFromName";
	window.open(url, 'StackSearch', 500, 500);
}
function searchFuel(){
	var url = "/eespc/FuelTankListInfo.do?formId=B_fuelFrom&formIdName=B_fuelFromName&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}

function testPrint()
{
    var CR = "\n";
    var msg = "";
/*
	msg = msg + "isFieldEntered(document.forms[0].B_facilityDesignatedId)="+
                     isFieldEntered(document.forms[0].B_facilityDesignatedId) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_floor)="+isFieldEntered(document.forms[0].B_floor) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_stateId)="+isFieldEntered(document.forms[0].B_stateId) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_yearInstalled)="+isFieldEntered(document.forms[0].B_yearInstalled) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_manufacturer)="+isFieldEntered(document.forms[0].B_manufacturer) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_make)="+isFieldEntered(document.forms[0].B_make) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_model)="+isFieldEntered(document.forms[0].B_model) + CR;                          
	msg = msg + "isFieldEntered(document.forms[0].B_serial)="+isFieldEntered(document.forms[0].B_serial) + CR;        
	msg = msg + "isFieldEntered(document.forms[0].B_burnerMake)="+isFieldEntered(document.forms[0].B_burnerMake) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_burnerModel)="+isFieldEntered(document.forms[0].B_burnerModel) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_capacity)="+isFieldEntered(document.forms[0].B_capacity) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_fuelCaping)="+isFieldEntered(document.forms[0].B_fuelCaping) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_fuelFrom)="+isFieldEntered(document.forms[0].B_fuelFrom) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_nycDob)="+isFieldEntered(document.forms[0].B_nycDob) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_mea)="+isFieldEntered(document.forms[0].B_mea) + CR;
        msg = msg + "isFieldEntered(document.forms[0].B_dep)="+isFieldEntered(document.forms[0].B_dep) + CR;
	msg = msg + "isFieldEntered(document.forms[0].B_testConductedBy)="+isFieldEntered(document.forms[0].B_testConductedBy) + CR;
        msg = msg + "isFieldEntered(document.forms[0].B_protocolSubmitDate)="+isFieldEntered(document.forms[0].B_protocolSubmitDate) + CR;
        msg = msg + "isFieldEntered(document.forms[0].B_nextStackTestDate)="+isFieldEntered(document.forms[0].B_nextStackTestDate) + CR;  
        msg = msg + "isFieldEntered(document.forms[0].B_stackFrom)="+isFieldEntered(document.forms[0].B_stackFrom) + CR;                  
        msg = msg + "isDropDownSelected(document.forms[0].B_primaryUse)="+isDropDownSelected(document.forms[0].B_primaryUse) + CR;        
        msg = msg + "isDropDownSelected(document.forms[0].B_primaryFuel)="+isDropDownSelected(document.forms[0].B_primaryFuel) + CR;
        msg = msg + "dualBurnerCheck()="+dualBurnerCheck() + CR;
*/

	msg = msg + "isRadioChecked(document.forms[0].B_burnerType)="+ isRadioChecked(document.forms[0].B_burnerType) +CR;
	msg = msg + "isRadioChecked(document.forms[0].B_sechduelC)="+ isRadioChecked(document.forms[0].B_sechduelC) +CR;        
	msg = msg + "isRadioChecked(document.forms[0].B_planApproval)="+ isRadioChecked(document.forms[0].B_planApproval) +CR;        
        msg = msg + "isRadioChecked(document.forms[0].B_stackTestRequired)="+ isRadioChecked(document.forms[0].B_stackTestRequired) +CR;
        msg = msg + "isRadioChecked(document.forms[0].B_otherBoilersCombined)="+ 
                    isRadioChecked(document.forms[0].B_otherBoilersCombined) +CR;
        msg = msg + "isRadioChecked(document.forms[0].B_stackTestProtSubmited)="+
                     isRadioChecked(document.forms[0].B_stackTestProtSubmited) +CR;                        
        msg = msg + "isRadioChecked(document.forms[0].B_testReportSubmited)="+
                     isRadioChecked(document.forms[0].B_testReportSubmited) +CR;                                
        msg = msg + "isRadioChecked(document.forms[0].B_boilerTestPassed)="+
                     isRadioChecked(document.forms[0].B_boilerTestPassed) +CR;                                
        msg = msg + "isRadioChecked(document.forms[0].B_retestPlanned)="+
                     isRadioChecked(document.forms[0].B_retestPlanned) +CR;                     
        msg = msg + "isRadioChecked(document.forms[0].B_isBoilerInCompliance)="+
                     isRadioChecked(document.forms[0].B_isBoilerInCompliance) +CR;                                                  
        msg = msg + "isRadioChecked(document.forms[0].B_isBoilerSubjectToNspc)="+
                     isRadioChecked(document.forms[0].B_isBoilerSubjectToNspc) +CR;
        msg = msg + "isRadioChecked(document.forms[0].B_isModifyPermitSub)="+
                     isRadioChecked(document.forms[0].B_isModifyPermitSub) +CR;                
        msg = msg + "isRadioChecked(document.forms[0].B_anyEmission)="+
                     isRadioChecked(document.forms[0].B_anyEmission) +CR;                                
        msg = msg + "isRadioChecked(document.forms[0].B_blrSubjectToFederal)="+
                     isRadioChecked(document.forms[0].B_blrSubjectToFederal) +CR;                                                
        msg = msg + "isRadioChecked(document.forms[0].B_isRollingAvg)="+
                     isRadioChecked(document.forms[0].B_isRollingAvg) +CR;
        msg = msg + "isRadioChecked(document.forms[0].B_isOpacityMonInst)="+
                     isRadioChecked(document.forms[0].B_isOpacityMonInst) +CR;
        msg = msg + "isTextEnteredForRadio(document.forms[0].B_blrModifiedInPast, document.forms[0].B_blrModifiedOn)="+
                     isTextEnteredForRadio(document.forms[0].B_blrModifiedInPast, document.forms[0].B_blrModifiedOn) + CR;    
        msg = msg + "isTextEnteredForRadio(document.forms[0].B_prfmTestProtSub, document.forms[0].B_prfmTestProtSubDate)="+
                     isTextEnteredForRadio(document.forms[0].B_prfmTestProtSub, document.forms[0].B_prfmTestProtSubDate) + CR;    
        msg = msg + "isTextEnteredForRadio(document.forms[0].B_prfmTestRptSub, document.forms[0].B_prfmTestRptSubDate)="+
                     isTextEnteredForRadio(document.forms[0].B_prfmTestRptSub, document.forms[0].B_prfmTestRptSubDate) + CR;    
        msg = msg + "isTextEnteredForRadio(document.forms[0].B_isSulpContentAnaly, document.forms[0].B_sulphurContent)="+
                     isTextEnteredForRadio(document.forms[0].B_isSulpContentAnaly, document.forms[0].B_sulphurContent) + CR;
        msg = msg + "isTextEnteredForRadio(document.forms[0].B_fuelLimitsForBlr, document.forms[0].B_fuelLimitQnty)="+
                     isTextEnteredForRadio(document.forms[0].B_fuelLimitsForBlr, document.forms[0].B_fuelLimitQnty) + CR;
        msg = msg + "isTextEnteredForRadio(document.forms[0].B_nitrogenContent, document.forms[0].B_nitrogenContentQnty)="+
                     isTextEnteredForRadio(document.forms[0].B_nitrogenContent, document.forms[0].B_nitrogenContentQnty) + CR;             

alert(
		isFieldEntered(document.forms[0].B_facilityDesignatedId) ==true && 
		isFieldEntered(document.forms[0].B_floor) ==true &&
		isFieldEntered(document.forms[0].B_stateId) ==true &&
		isFieldEntered(document.forms[0].B_yearInstalled) ==true &&
		isFieldEntered(document.forms[0].B_manufacturer) ==true &&
		isFieldEntered(document.forms[0].B_make) ==true &&
		isFieldEntered(document.forms[0].B_model) ==true &&
		isFieldEntered(document.forms[0].B_serial) ==true &&
		isFieldEntered(document.forms[0].B_burnerMake) ==true &&
		isFieldEntered(document.forms[0].B_burnerModel) ==true &&
		isFieldEntered(document.forms[0].B_capacity) ==true &&
		isFieldEntered(document.forms[0].B_fuelCaping) ==true &&
		isFieldEntered(document.forms[0].B_fuelFrom) ==true &&
		isFieldEntered(document.forms[0].B_nycDob) ==true &&
		isFieldEntered(document.forms[0].B_mea) ==true &&
		isFieldEntered(document.forms[0].B_dep) ==true &&
		isFieldEntered(document.forms[0].B_testConductedBy) ==true &&
		isFieldEntered(document.forms[0].B_protocolSubmitDate) ==true &&
            isFieldEntered(document.forms[0].B_StackTestDate) ==true &&
		isFieldEntered(document.forms[0].B_nextStackTestDate) ==true &&
		isFieldEntered(document.forms[0].B_stackFrom) ==true &&
		isDropDownSelected(document.forms[0].B_primaryUse)  ==true &&
                isDropDownSelected(document.forms[0].B_primaryFuel)  ==true &&
		dualBurnerCheck() ==true &&
		isRadioChecked(document.forms[0].B_burnerType) ==true &&
		isRadioChecked(document.forms[0].B_sechduelC) ==true &&
		isRadioChecked(document.forms[0].B_planApproval) ==true &&
		isRadioChecked(document.forms[0].B_stackTestRequired) ==true &&
		isRadioChecked(document.forms[0].B_otherBoilersCombined) ==true &&
		isRadioChecked(document.forms[0].B_stackTestProtSubmited) ==true &&
		isRadioChecked(document.forms[0].B_testReportSubmited) ==true &&
		isRadioChecked(document.forms[0].B_boilerTestPassed) ==true &&
		isRadioChecked(document.forms[0].B_retestPlanned) ==true &&
		isRadioChecked(document.forms[0].B_isBoilerInCompliance) ==true &&
		isRadioChecked(document.forms[0].B_isBoilerSubjectToNspc) ==true &&
		isRadioChecked(document.forms[0].B_isModifyPermitSub) ==true &&
		isRadioChecked(document.forms[0].B_anyEmission) ==true &&
		isRadioChecked(document.forms[0].B_blrSubjectToFederal) ==true && 
		isRadioChecked(document.forms[0].B_isRollingAvg) ==true &&
		isRadioChecked(document.forms[0].B_isOpacityMonInst) ==true &&
		isTextEnteredForRadio(document.forms[0].B_blrModifiedInPast, document.forms[0].B_blrModifiedOn) ==true &&
		isTextEnteredForRadio(document.forms[0].B_prfmTestProtSub, document.forms[0].B_prfmTestProtSubDate) ==true &&
		isTextEnteredForRadio(document.forms[0].B_prfmTestRptSub, document.forms[0].B_prfmTestRptSubDate) ==true &&
		isTextEnteredForRadio(document.forms[0].B_isSulpContentAnaly, document.forms[0].B_sulphurContent) ==true &&
		isTextEnteredForRadio(document.forms[0].B_fuelLimitsForBlr, document.forms[0].B_fuelLimitQnty) ==true &&     
		isTextEnteredForRadio(document.forms[0].B_nitrogenContent, document.forms[0].B_nitrogenContentQnty==true ));
        alert(msg);
}

function editAnnualTuneUp(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editAnnualTuneUp';
	document.forms[0].submit();
}

function deleteAnnualTuneUp(id)
{var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteAnnualTuneUp';
	document.forms[0].submit();
}
 else
 {
 
  }
}



function editValveTest(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editValveTest';
	document.forms[0].submit();
}

function deleteValveTest(id)
{var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteValveTest';
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
function isBoilerFieldEntered(formObject)
{
var tempVal1=false;	
if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0){
			tempVal1= true;
		}else{
			tempVal1= false;
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






