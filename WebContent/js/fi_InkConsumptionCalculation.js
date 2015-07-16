// JavaScript Document
var fi_monthsName= new Array(11)
fi_monthsName[0] = "document.forms[0].fi_jan.value";
fi_monthsName[1] = "document.forms[0].fi_feb.value";
fi_monthsName[2] = "document.forms[0].fi_mar.value";
fi_monthsName[3] = "document.forms[0].fi_apr.value";
fi_monthsName[4] = "document.forms[0].fi_may.value";
fi_monthsName[5] = "document.forms[0].fi_jun.value";
fi_monthsName[6] = "document.forms[0].fi_jul.value";
fi_monthsName[7] = "document.forms[0].fi_aug.value";
fi_monthsName[8] = "document.forms[0].fi_sep.value";
fi_monthsName[9] = "document.forms[0].fi_oct.value";
fi_monthsName[10]= "document.forms[0].fi_nov.value";
fi_monthsName[11]= "document.forms[0].fi_dec.value";

function fi_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].fir_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function fi_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(fi_monthsName[i]));
	}
	return sum;
}
function fi_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].fi_rdbIndex.length;i++){
		if (document.forms[0].fi_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(fi_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].fi_rdbIndex.length;j++){
			var tempValue2 = eval(fi_monthsName[j]);
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
function fi_validateInkConsumption(){

	if (! fi_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].fi_rdbIndex.length;i++){
		if (document.forms[0].fi_rdbIndex[i].checked){
			currentYearTotal = fi_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].fi_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = fi_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = fi_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].fi_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].fi_yearToDate.value = currentYearTotal;
	return true;
}

function fi_preceedingConsumption(index){
	if(index == 0){
		return fi_getSum(11, 12);
	}else{
		return parseFloat(eval(fi_monthsName[index-1]));
	}
}

function deleteinkconsumption(id)
{
 var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {

	document.forms[0].inkdeleteConsumptionId.value=id;
	document.forms[0].methodToCall.value='deleteinkConsumptionInfo';
	document.forms[0].submit();
}
 else
 {
 
  }
}

function fi_addInkConsumption()
{
	
if(fi_validateInkConsumption()==false){
		return false;
	}
    	document.forms[0].bctype.value='5';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}
