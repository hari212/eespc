// JavaScript Document
var monthsName= new Array(11)
monthsName[0] = "document.forms[0].jan.value";
monthsName[1] = "document.forms[0].feb.value";
monthsName[2] = "document.forms[0].mar.value";
monthsName[3] = "document.forms[0].apr.value";
monthsName[4] = "document.forms[0].may.value";
monthsName[5] = "document.forms[0].jun.value";
monthsName[6] = "document.forms[0].jul.value";
monthsName[7] = "document.forms[0].aug.value";
monthsName[8] = "document.forms[0].sep.value";
monthsName[9] = "document.forms[0].oct.value";
monthsName[10]= "document.forms[0].nov.value";
monthsName[11]= "document.forms[0].dec.value";

function getSum(start, end){
	
	var previousValues = document.forms[0].hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}
	return sum;
}
function getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(monthsName[i]));
	}
	return sum;
}
function preFuelConsumptionValidation(){

	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].rdbIndex.length;i++){
		if (document.forms[0].rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}

	for (var j=(index+1); j<document.forms[0].rdbIndex.length;j++){
			var tempValue2 = eval(monthsName[j]);
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
function validateFuelConsumption(){

	if (! preFuelConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].rdbIndex.length;i++){
		if (document.forms[0].rdbIndex[i].checked){
			currentYearTotal = getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].rdbIndex.length-1)){
				previousToCurrentRollOverTotal = getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].yearToDate.value = currentYearTotal;
	return true;
}

function preceedingConsumption(index){
	if(index == 0){
		return getSum(11, 12);
	}else{
		return parseFloat(eval(monthsName[index-1]));
	}
}

function deleteoilconsumption(id)
{var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].fueldeleteConsumptionId.value=id;
	document.forms[0].methodToCall.value='deletefuelConsumptionInfo';
	document.forms[0].submit();
}
 else
 {
 
  }
}

function addFuelConsumption()
{
	
	if(validateFuelConsumption()==false){
		return false;
	}
	document.forms[0].bctype.value='1';
	document.forms[0].methodToCall.value='fuelConsumptionInfo';
	document.forms[0].submit();
	return true;
}
