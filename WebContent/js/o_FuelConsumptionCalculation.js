// JavaScript Document
var o_monthsName= new Array(11)
o_monthsName[0] = "document.forms[0].o_jan.value";
o_monthsName[1] = "document.forms[0].o_feb.value";
o_monthsName[2] = "document.forms[0].o_mar.value";
o_monthsName[3] = "document.forms[0].o_apr.value";
o_monthsName[4] = "document.forms[0].o_may.value";
o_monthsName[5] = "document.forms[0].o_jun.value";
o_monthsName[6] = "document.forms[0].o_jul.value";
o_monthsName[7] = "document.forms[0].o_aug.value";
o_monthsName[8] = "document.forms[0].o_sep.value";
o_monthsName[9] = "document.forms[0].o_oct.value";
o_monthsName[10]= "document.forms[0].o_nov.value";
o_monthsName[11]= "document.forms[0].o_dec.value";

function o_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].o_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}
	return sum;
}
function o_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(o_monthsName[i]));
	}
	return sum;
}
function o_preFuelConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].o_rdbIndex.length;i++){
		if (document.forms[0].o_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(o_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].o_rdbIndex.length;j++){
			var tempValue2 = eval(o_monthsName[j]);
			if (tempValue2.length >0){
				alert("Please check consumption selection.");
				return false;
			}		
	}
	if (! isIndexSelected){
		alert("Please select the Month to Calculate the Rollover.");
		return false;
	}
	return true;
}
function o_validateFuelConsumption(){

	if (! o_preFuelConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].o_rdbIndex.length;i++){
		if (document.forms[0].o_rdbIndex[i].checked){
			currentYearTotal = o_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].o_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = o_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = o_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].o_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].o_yearToDate.value = currentYearTotal;
	return true;
}

function o_preceedingConsumption(index){
	if(index == 0){
		return o_getSum(11, 12);
	}else{
		return parseFloat(eval(o_monthsName[index-1]));
	}
}
function o_addFuelConsumption()
{
	
if(o_validateFuelConsumption()==false){
		return false;
	}

    if(document.forms[0].o_fcyear){
        if(document.forms[0].o_fcyear[document.forms[0].o_fcyear.selectedIndex].value==-1
			|| document.forms[0].o_fcyear[document.forms[0].o_fcyear.selectedIndex].value=='Please Select'){
			printMandatoryAlert();
            return false;
        }
    }
	document.forms[0].bctype.value='2';
	document.forms[0].methodToCall.value='fuelConsumptionInfo';
	document.forms[0].submit();
	return true;
}
