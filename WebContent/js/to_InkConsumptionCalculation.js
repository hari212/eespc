// JavaScript Document
var to_monthsName= new Array(11)
to_monthsName[0] = "document.forms[0].to_jan.value";
to_monthsName[1] = "document.forms[0].to_feb.value";
to_monthsName[2] = "document.forms[0].to_mar.value";
to_monthsName[3] = "document.forms[0].to_apr.value";
to_monthsName[4] = "document.forms[0].to_may.value";
to_monthsName[5] = "document.forms[0].to_jun.value";
to_monthsName[6] = "document.forms[0].to_jul.value";
to_monthsName[7] = "document.forms[0].to_aug.value";
to_monthsName[8] = "document.forms[0].to_sep.value";
to_monthsName[9] = "document.forms[0].to_oct.value";
to_monthsName[10]= "document.forms[0].to_nov.value";
to_monthsName[11]= "document.forms[0].to_dec.value";

function to_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].tox_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function to_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(to_monthsName[i]));
	}
	return sum;
}
function to_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].to_rdbIndex.length;i++){
		if (document.forms[0].to_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(to_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].to_rdbIndex.length;j++){
			var tempValue2 = eval(to_monthsName[j]);
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
function to_validateInkConsumption(){

	if (! to_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].to_rdbIndex.length;i++){
		if (document.forms[0].to_rdbIndex[i].checked){
			currentYearTotal = to_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].to_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = to_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = to_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].to_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].to_yearToDate.value = currentYearTotal;
	return true;
}

function to_preceedingConsumption(index){
	if(index == 0){
		return to_getSum(11, 12);
	}else{
		return parseFloat(eval(to_monthsName[index-1]));
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

function to_addInkConsumption()
{
	
if(to_validateInkConsumption()==false){
		return false;
	}
    	document.forms[0].bctype.value='2';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}