// JavaScript Document
var oil_monthsName= new Array(11)
oil_monthsName[0] = "document.forms[0].oil_jan.value";
oil_monthsName[1] = "document.forms[0].oil_feb.value";
oil_monthsName[2] = "document.forms[0].oil_mar.value";
oil_monthsName[3] = "document.forms[0].oil_apr.value";
oil_monthsName[4] = "document.forms[0].oil_may.value";
oil_monthsName[5] = "document.forms[0].oil_jun.value";
oil_monthsName[6] = "document.forms[0].oil_jul.value";
oil_monthsName[7] = "document.forms[0].oil_aug.value";
oil_monthsName[8] = "document.forms[0].oil_sep.value";
oil_monthsName[9] = "document.forms[0].oil_oct.value";
oil_monthsName[10]= "document.forms[0].oil_nov.value";
oil_monthsName[11]= "document.forms[0].oil_dec.value";

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
function oil_getCurrentRunningHrsSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(oil_monthsName[i]));
	}
	return sum;
}
function oil_preEngineRunningHrsValidation(){

	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].oil_rdbIndex.length;i++){
		if (document.forms[0].oil_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(oil_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid Monthly Running Hrs Oil.");
				return false;
			}
		}
	}

	for (var j=(index+1); j<document.forms[0].oil_rdbIndex.length;j++){
			var tempValue2 = eval(oil_monthsName[j]);
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
function oil_validateEngineRunningHrs(){

	if (! oil_preEngineRunningHrsValidation()){
		return false;
	}

	//var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	//var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].oil_rdbIndex.length;i++){
		if (document.forms[0].oil_rdbIndex[i].checked){
			currentYearTotal = oil_getCurrentRunningHrsSum(0,i+1);
			/*if( i==(document.forms[0].e_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = getSum(i+1,12) + currentYearTotal;
			}*/
		}
	}
	
	//document.forms[0].consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].oil_yearToDate.value = currentYearTotal;
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



function oil_addMonthlyPressureHrs()
{
	
if(oil_validateEngineRunningHrs()==false){
		return false;
	}

    if(document.forms[0].oil_fcyear){
        if(document.forms[0].oil_fcyear[document.forms[0].oil_fcyear.selectedIndex].value==-1
			|| document.forms[0].oil_fcyear[document.forms[0].oil_fcyear.selectedIndex].value=='Please Select'){
			printMandatoryAlert();
            return false;
        }
    }
	document.forms[0].ebctype.value='2';
	document.forms[0].methodToCall.value='engineRunningHrsInfo';
	document.forms[0].submit();
	return true;
}

