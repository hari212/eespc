// JavaScript Document
var on_monthsName= new Array(11)
on_monthsName[0] = "document.forms[0].on_jan.value";
on_monthsName[1] = "document.forms[0].on_feb.value";
on_monthsName[2] = "document.forms[0].on_mar.value";
on_monthsName[3] = "document.forms[0].on_apr.value";
on_monthsName[4] = "document.forms[0].on_may.value";
on_monthsName[5] = "document.forms[0].on_jun.value";
on_monthsName[6] = "document.forms[0].on_jul.value";
on_monthsName[7] = "document.forms[0].on_aug.value";
on_monthsName[8] = "document.forms[0].on_sep.value";
on_monthsName[9] = "document.forms[0].on_oct.value";
on_monthsName[10]= "document.forms[0].on_nov.value";
on_monthsName[11]= "document.forms[0].on_dec.value";

function on_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].onx_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function on_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(on_monthsName[i]));
	}
	return sum;
}
function on_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].on_rdbIndex.length;i++){
		if (document.forms[0].on_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(on_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].on_rdbIndex.length;j++){
			var tempValue2 = eval(on_monthsName[j]);
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
function on_validateInkConsumption(){

	if (! on_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].on_rdbIndex.length;i++){
		if (document.forms[0].on_rdbIndex[i].checked){
			currentYearTotal = on_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].on_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = on_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = on_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].on_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].on_yearToDate.value = currentYearTotal;
	return true;
}

function on_preceedingConsumption(index){
	if(index == 0){
		return on_getSum(11, 12);
	}else{
		return parseFloat(eval(on_monthsName[index-1]));
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

function on_addInkConsumption()
{
	
if(on_validateInkConsumption()==false){
		return false;
	}
    	document.forms[0].bctype.value='1';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}
