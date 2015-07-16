// JavaScript Document
var si_monthsName= new Array(11)
si_monthsName[0] = "document.forms[0].si_jan.value";
si_monthsName[1] = "document.forms[0].si_feb.value";
si_monthsName[2] = "document.forms[0].si_mar.value";
si_monthsName[3] = "document.forms[0].si_apr.value";
si_monthsName[4] = "document.forms[0].si_may.value";
si_monthsName[5] = "document.forms[0].si_jun.value";
si_monthsName[6] = "document.forms[0].si_jul.value";
si_monthsName[7] = "document.forms[0].si_aug.value";
si_monthsName[8] = "document.forms[0].si_sep.value";
si_monthsName[9] = "document.forms[0].si_oct.value";
si_monthsName[10]= "document.forms[0].si_nov.value";
si_monthsName[11]= "document.forms[0].si_dec.value";

function si_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].six_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function si_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(si_monthsName[i]));
	}
	return sum;
}
function si_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].si_rdbIndex.length;i++){
		if (document.forms[0].si_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(si_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].si_rdbIndex.length;j++){
			var tempValue2 = eval(si_monthsName[j]);
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
function si_validateInkConsumption(){

	if (! si_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].si_rdbIndex.length;i++){
		if (document.forms[0].si_rdbIndex[i].checked){
			currentYearTotal = si_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].si_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = si_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = si_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].si_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].si_yearToDate.value = currentYearTotal;
	return true;
}

function si_preceedingConsumption(index){
	if(index == 0){
		return si_getSum(11, 12);
	}else{
		return parseFloat(eval(si_monthsName[index-1]));
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

function si_addInkConsumption()
{
	
if(si_validateInkConsumption()==false){
		return false;
	}
    	document.forms[0].bctype.value='6';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}

