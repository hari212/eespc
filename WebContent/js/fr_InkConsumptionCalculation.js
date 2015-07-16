// JavaScript Document
var fr_monthsName= new Array(11)
fr_monthsName[0] = "document.forms[0].fr_jan.value";
fr_monthsName[1] = "document.forms[0].fr_feb.value";
fr_monthsName[2] = "document.forms[0].fr_mar.value";
fr_monthsName[3] = "document.forms[0].fr_apr.value";
fr_monthsName[4] = "document.forms[0].fr_may.value";
fr_monthsName[5] = "document.forms[0].fr_jun.value";
fr_monthsName[6] = "document.forms[0].fr_jul.value";
fr_monthsName[7] = "document.forms[0].fr_aug.value";
fr_monthsName[8] = "document.forms[0].fr_sep.value";
fr_monthsName[9] = "document.forms[0].fr_oct.value";
fr_monthsName[10]= "document.forms[0].fr_nov.value";
fr_monthsName[11]= "document.forms[0].fr_dec.value";

function fr_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].frx_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function fr_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(fr_monthsName[i]));
	}
	return sum;
}
function fr_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].fr_rdbIndex.length;i++){
		if (document.forms[0].fr_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(fr_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].fr_rdbIndex.length;j++){
			var tempValue2 = eval(fr_monthsName[j]);
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
function fr_validateInkConsumption(){

	if (! fr_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].fr_rdbIndex.length;i++){
		if (document.forms[0].fr_rdbIndex[i].checked){
			currentYearTotal = fr_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].fr_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = fr_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = fr_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].fr_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].fr_yearToDate.value = currentYearTotal;
	return true;
}

function fr_preceedingConsumption(index){
	if(index == 0){
		return fr_getSum(11, 12);
	}else{
		return parseFloat(eval(fr_monthsName[index-1]));
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

function fr_addInkConsumption()
{
	
if(fr_validateInkConsumption()==false){
		return false;
	}
    	document.forms[0].bctype.value='4';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}