// JavaScript Document
var se_monthsName= new Array(11)
se_monthsName[0] = "document.forms[0].se_jan.value";
se_monthsName[1] = "document.forms[0].se_feb.value";
se_monthsName[2] = "document.forms[0].se_mar.value";
se_monthsName[3] = "document.forms[0].se_apr.value";
se_monthsName[4] = "document.forms[0].se_may.value";
se_monthsName[5] = "document.forms[0].se_jun.value";
se_monthsName[6] = "document.forms[0].se_jul.value";
se_monthsName[7] = "document.forms[0].se_aug.value";
se_monthsName[8] = "document.forms[0].se_sep.value";
se_monthsName[9] = "document.forms[0].se_oct.value";
se_monthsName[10]= "document.forms[0].se_nov.value";
se_monthsName[11]= "document.forms[0].se_dec.value";

function se_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].ser_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function se_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(se_monthsName[i]));
	}
	return sum;
}
function se_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].se_rdbIndex.length;i++){
		if (document.forms[0].se_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(se_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].se_rdbIndex.length;j++){
			var tempValue2 = eval(se_monthsName[j]);
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
function se_validateInkConsumption(){

	if (! se_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].se_rdbIndex.length;i++){
		if (document.forms[0].se_rdbIndex[i].checked){
			currentYearTotal = se_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].se_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = se_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = se_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].se_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].se_yearToDate.value = currentYearTotal;
	return true;
}

function se_preceedingConsumption(index){
	if(index == 0){
		return se_getSum(11, 12);
	}else{
		return parseFloat(eval(se_monthsName[index-1]));
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

function se_addInkConsumption()
{
	
if(se_validateInkConsumption()==false){
		return false;
	}
    	document.forms[0].bctype.value='7';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}

