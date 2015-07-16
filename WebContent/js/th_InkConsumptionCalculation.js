// JavaScript Document
var th_monthsName= new Array(11)
th_monthsName[0] = "document.forms[0].th_jan.value";
th_monthsName[1] = "document.forms[0].th_feb.value";
th_monthsName[2] = "document.forms[0].th_mar.value";
th_monthsName[3] = "document.forms[0].th_apr.value";
th_monthsName[4] = "document.forms[0].th_may.value";
th_monthsName[5] = "document.forms[0].th_jun.value";
th_monthsName[6] = "document.forms[0].th_jul.value";
th_monthsName[7] = "document.forms[0].th_aug.value";
th_monthsName[8] = "document.forms[0].th_sep.value";
th_monthsName[9] = "document.forms[0].th_oct.value";
th_monthsName[10]= "document.forms[0].th_nov.value";
th_monthsName[11]= "document.forms[0].th_dec.value";

function th_getSum(start, end){
	//var previousValues = "1|2|3|4|5|6|7|8|9|10|11|12";
	var previousValues = document.forms[0].thx_hdnPreviousConsumption.value;
	var previousValuesArray = previousValues.split("|");
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++) {
		sum += parseFloat(previousValuesArray[i]);
	}

	return sum;

}
function th_getCurrentConsumptionSum(start, end){
	var sum = parseFloat("0");
	var startIndex = parseFloat(start);
	var endIndex = parseFloat(end);
	for (var i=startIndex; i<endIndex;i++){
		sum += parseFloat(eval(th_monthsName[i]));
	}
	return sum;
}
function th_preInkConsumptionValidation(){
	var index = parseFloat("0");
	var isIndexSelected = false;
	for (var i=0; i<document.forms[0].th_rdbIndex.length;i++){
		if (document.forms[0].th_rdbIndex[i].checked){
			isIndexSelected = true;
			index = i;
			var tempValue = eval(th_monthsName[i]);
			if (tempValue.length==0){
				alert("Please enter valid consumption.");
				return false;
			}
		}
	}
	for (var j=(index+1); j<document.forms[0].th_rdbIndex.length;j++){
			var tempValue2 = eval(th_monthsName[j]);
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
function th_validateInkConsumption(){

	if (! th_preInkConsumptionValidation()){
		return false;
	}

	var previousToCurrentRollOverTotal = parseFloat("0");
	var currentYearTotal = parseFloat("0");
	var tempPreceedingConsumption = parseFloat("0");	
	for (var i=0; i<document.forms[0].th_rdbIndex.length;i++){
		if (document.forms[0].th_rdbIndex[i].checked){
			currentYearTotal = th_getCurrentConsumptionSum(0,i+1);
			if( i==(document.forms[0].th_rdbIndex.length-1)){
				previousToCurrentRollOverTotal = th_getSum(i,12) + currentYearTotal;
			}else{
				previousToCurrentRollOverTotal = th_getSum(i+1,12) + currentYearTotal;
			}
		}
	}
	
	document.forms[0].th_consumption.value = previousToCurrentRollOverTotal ;
	document.forms[0].th_yearToDate.value = currentYearTotal;
	return true;
}

function th_preceedingConsumption(index){
	if(index == 0){
		return th_getSum(11, 12);
	}else{
		return parseFloat(eval(th_monthsName[index-1]));
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

function th_addInkConsumption()
{
	if(th_validateInkConsumption()==false)
	{
	 return false;
	}
    	document.forms[0].bctype.value='3';
	document.forms[0].methodToCall.value='on_inkConsumptionInfo';
	document.forms[0].submit();
	return true;
}
