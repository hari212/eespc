// JavaScript Document
var e_monthsName= new Array(11)
e_monthsName[0] = "document.forms[0].e_jan.value";
e_monthsName[1] = "document.forms[0].e_feb.value";
e_monthsName[2] = "document.forms[0].e_mar.value";
e_monthsName[3] = "document.forms[0].e_apr.value";
e_monthsName[4] = "document.forms[0].e_may.value";
e_monthsName[5] = "document.forms[0].e_jun.value";
e_monthsName[6] = "document.forms[0].e_jul.value";
e_monthsName[7] = "document.forms[0].e_aug.value";
e_monthsName[8] = "document.forms[0].e_sep.value";
e_monthsName[9] = "document.forms[0].e_oct.value";
e_monthsName[10]= "document.forms[0].e_nov.value";
e_monthsName[11]= "document.forms[0].e_dec.value";

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
function getCurrentRunningHrsSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(e_monthsName[i]));
	}
	return sum;
}
function preEngineRunningHrsValidation(){

	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].e_rdbIndex.length;i++){
		if (document.forms[0].e_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(e_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid Engine Running Hrs.");
				return false;
			}
		}
	}

	for (var j=(index+1); j<document.forms[0].e_rdbIndex.length;j++){
			var tempValue2 = eval(e_monthsName[j]);
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
function validateEngineRunningHrs(){

	if (! preEngineRunningHrsValidation()){
		return false;
	}

	//var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	//var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].e_rdbIndex.length;i++){
		if (document.forms[0].e_rdbIndex[i].checked){
			currentYearTotal = getCurrentRunningHrsSum(0,i+1);
			/*if( i==(document.forms[0].e_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = getSum(i+1,12) + currentYearTotal;
			}*/
		}
	}
	
	//document.forms[0].consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].e_yearToDate.value = currentYearTotal;
	return true;
}


function preceedingRunningHrs(index){
	if(index == 0){
		return getSum(11, 12);
	}else{
		return parseFloat(eval(e_monthsName[index-1]));
	}
}

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



function addEngineRunningHrs()
{
	
	if(validateEngineRunningHrs()==false){
		return false;
	}
	document.forms[0].ebctype.value='1';
	document.forms[0].methodToCall.value='engineRunningHrsInfo';
	document.forms[0].submit();
	return true;
}

