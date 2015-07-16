// JavaScript Document
var gas_monthsName= new Array(11)
gas_monthsName[0] = "document.forms[0].gas_jan.value";
gas_monthsName[1] = "document.forms[0].gas_feb.value";
gas_monthsName[2] = "document.forms[0].gas_mar.value";
gas_monthsName[3] = "document.forms[0].gas_apr.value";
gas_monthsName[4] = "document.forms[0].gas_may.value";
gas_monthsName[5] = "document.forms[0].gas_jun.value";
gas_monthsName[6] = "document.forms[0].gas_jul.value";
gas_monthsName[7] = "document.forms[0].gas_aug.value";
gas_monthsName[8] = "document.forms[0].gas_sep.value";
gas_monthsName[9] = "document.forms[0].gas_oct.value";
gas_monthsName[10]= "document.forms[0].gas_nov.value";
gas_monthsName[11]= "document.forms[0].gas_dec.value";

/*function getSum(start, end){
	
	var previousValues = document.forms[0].e_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}
	return sum;
}*/
function gas_getCurrentRunningHrsSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(gas_monthsName[i]));
	}
	return sum;
}
function gas_preEngineRunningHrsValidation(){

	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].gas_rdbIndex.length;i++){
		if (document.forms[0].gas_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(gas_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid Monthly Running Hrs Gas.");
				return false;
			}
		}
	}

	for (var j=(index+1); j<document.forms[0].gas_rdbIndex.length;j++){
			var tempValue2 = eval(gas_monthsName[j]);
			if (tempValue2.length >0){
				alert("Please check Hours selection.");
				return false;
			}		
	}
	if (! isIndexSelected){
		alert("Please select the Month to Calculate the Rollover.");
		return false;
	}
	return true;
}
function gas_validateEngineRunningHrs(){

	if (! gas_preEngineRunningHrsValidation()){
		return false;
	}

	//var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	//var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].gas_rdbIndex.length;i++){
		if (document.forms[0].gas_rdbIndex[i].checked){
			currentYearTotal = gas_getCurrentRunningHrsSum(0,i+1);
			/*if( i==(document.forms[0].e_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = getSum(i+1,12) + currentYearTotal;
			}*/
		}
	}
	
	//document.forms[0].consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].gas_yearToDate.value = currentYearTotal;
	return true;
}


/*function oil_preceedingRunningHrs(index){
	if(index == 0){
		return getSum(11, 12);
	}else{
		return parseFloat(eval(oil_monthsName[index-1]));
	}
}*/

function deleteEningeRunningHrs(id)
{var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].enginedeleteRunningHrsId.value=id;
	document.forms[0].methodToCall.value='deleteEngineRunningHrsInfo';
	document.forms[0].submit();
}
 else
 {
 
  }
}



function gas_addMonthlyPressureHrs()
{
	
if(gas_validateEngineRunningHrs()==false){
		return false;
	}

    if(document.forms[0].gas_fcyear){
        if(document.forms[0].gas_fcyear[document.forms[0].gas_fcyear.selectedIndex].value==-1
			|| document.forms[0].gas_fcyear[document.forms[0].gas_fcyear.selectedIndex].value=='Please Select'){
			printMandatoryAlert();
            return false;
        }
    }
	document.forms[0].ebctype.value='3';
	document.forms[0].methodToCall.value='engineRunningHrsInfo';
	document.forms[0].submit();
	return true;
}

