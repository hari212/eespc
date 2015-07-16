var fuelText = new Array();
var fuelValue = new Array();
function init(){
	var formObj = document.forms[0].chPrimFuel;
	for(var i=0; i<formObj.length; i++)
	{
		fuelText[i] = new Array(formObj[i].text);	
		fuelValue[i] = new Array(formObj[i].value);
	}
}

function validateDep(){
	if (    document.forms[0].chDEPIssueDate.value.length ==0 || document.forms[0].chDEPExpirationDate.value.length ==0)
	{
		alert("Please enter all the mandatory information for DEP.");
		return false;		
	}
	        if (!isDate(document.forms[0].chDEPIssueDate.value))
	        {   
	            alert("Invalid DEP issue Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].chDEPExpirationDate.value))
	        {   
	            alert("Invalid DEP Expiration Date");
	            return false;
	        }

	dateFrom = chkdate(document.forms[0].chDEPIssueDate);
	dateTo   = chkdate(document.forms[0].chDEPExpirationDate);

        if(Date.parse(dateFrom) < Date.parse(dateTo))
	        {
	        	return true;
				
		}
		else
		{
		      alert("Expiration date must be Greater than Issue Date");
		      return false; 
		}

	return true;
}


function validateCons(){
	if (document.forms[0].chAnnualYear.value.length ==0 ||document.forms[0].chAnnualConsumption.value.length ==0 )
        {
		alert("Please enter all the mandatory Combustion information.");
		return false;		
	}
	else
	{
	     var cons = document.forms[0].chAnnualConsumption.value;
             return isNumeric(cons," Annual Consumption ");
	
	}
	
	return true;
}

function validateMe(){
	if (	document.forms[0].chFacId.value.length ==0 || document.forms[0].chFloor.value.length ==0 ||
		document.forms[0].chYearIns.value.length ==0 ||
		document.forms[0].chModel.value.length ==0 ||
		document.forms[0].chMake.value.length ==0 ||
		document.forms[0].chSerialNo.value.length ==0 ||
		document.forms[0].chCapacity.value.length ==0 ||
		document.forms[0].chBurnerType.value.length ==0 ||
		document.forms[0].chFuel.value.length ==0 ||
		document.forms[0].chStackFrom.value.length ==0 ||
		document.forms[0].chMea.value.length ==0 ||
		document.forms[0].chPrimFuel.selectedIndex ==0  || 
		document.forms[0].chSecFuel.selectedIndex ==0 ) 
		{
			alert("Please enter all the mandatory Chiller information.");
		return false;		
	}
	else
	{
	     var cap = document.forms[0].chCapacity.value;
             return isNumeric(cap," Capacity ");
	
	}
	return true;
}

function isNumeric(s , b){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9")))
        { 
           alert("The Field " + b + " Should be Numeric."); 
           return false;
        }
    }
    // All characters are numbers.
    return true;
}

function burnerTypeSetting()
{
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].chBurnerType.length;k++)
	{
		if (document.forms[0].chBurnerType[k].checked && document.forms[0].chBurnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}

	if (isDualBurner){
		emptyDropDown(document.forms[0].chPrimFuel);
		var tt =0;
		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr = fuelValue[j];
			if (tempStr =="-1" || tempStr =="1"){
				document.forms[0].chPrimFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
			document.forms[0].chSecFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr == "1") {
				tt =j;
			}
		}
		if(tt>0){
			document.forms[0].chSecFuel.options[tt] = null;
		}
	}else{
		emptyDropDown(document.forms[0].chPrimFuel);
		emptyDropDown(document.forms[0].chSecFuel);

		for (var j=0; j<fuelText.length; j++)
		{
			var tempStr2 = fuelValue[j];
			document.forms[0].chPrimFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			if (tempStr2 ==-1) {
				document.forms[0].chSecFuel.options[j] = new Option(fuelText[j],fuelValue[j]);		
			}
		}
	}
}


//@TODO - to get the calorific value dynamically 
function fuelChanged(){
	var isDualBurner = false;
	for (var k=0; k<document.forms[0].chBurnerType.length;k++)
	{
		if (document.forms[0].chBurnerType[k].checked && document.forms[0].chBurnerType[k].value =="Y"){
			isDualBurner = true;
			break;
		}
	}
	var factor =0;
	if (isDualBurner){
		if(document.forms[0].chPrimFuel.selectedIndex !=0 && 
			document.forms[0].chCapacity.value.length >0){
			document.forms[0].B_naturalGasFiringRate.value = (document.forms[0].chCapacity.value * 1000);
		}else{
			document.forms[0].B_naturalGasFiringRate.value = "";
		}
		if(document.forms[0].chSecFuel.selectedIndex !=0 && 
			document.forms[0].chCapacity.value.length >0){
			var tmp = document.forms[0].chSecFuel[document.forms[0].chSecFuel.selectedIndex].value;
			if (tmp==2){
				factor = 140;
			}else if(tmp==3){
				factor = 145;
			}else if(tmp==4){
				factor = 150;
			}
			document.forms[0].B_oilFiringRate.value = parseInt((document.forms[0].chCapacity.value * 1000000)/factor);
		}else{
			document.forms[0].B_oilFiringRate.value = "";		
		}
	}else{
		if(document.forms[0].chPrimFuel.selectedIndex !=0 && 
			document.forms[0].chCapacity.value.length >0){
			var tmp = document.forms[0].chPrimFuel[document.forms[0].chPrimFuel.selectedIndex].value;
			if (tmp ==1){
				document.forms[0].B_naturalGasFiringRate.value = (document.forms[0].chCapacity.value * 1000);
				document.forms[0].B_oilFiringRate.value = "";			
			}else if (tmp==2){
				factor = 140;
				document.forms[0].B_naturalGasFiringRate.value = "";
			}else if(tmp==3){
				factor = 145;
				document.forms[0].B_naturalGasFiringRate.value = "";
			}else if(tmp==4){
				factor = 150;
				document.forms[0].B_naturalGasFiringRate.value = "";
			}
			
			if (tmp !=1){
				document.forms[0].B_oilFiringRate.value = parseInt((document.forms[0].chCapacity.value * 1000000)/factor);			
			}
		}
	}
}


function chkdate(objName) {
var strDatestyle = "US"; //United States date style
//var strDatestyle = "EU";  //European date style
var strDate;
var strDateArray;
var strDay;
var strMonth;
var strYear;
var intday;
var intMonth;
var intYear;
var booFound = false;
var datefield = objName;
var strSeparatorArray = new Array("-"," ","/",".");
var intElementNr;
var err = 0;
var strMonthArray = new Array(12);
strMonthArray[0] = "Jan";
strMonthArray[1] = "Feb";
strMonthArray[2] = "Mar";
strMonthArray[3] = "Apr";
strMonthArray[4] = "May";
strMonthArray[5] = "Jun";
strMonthArray[6] = "Jul";
strMonthArray[7] = "Aug";
strMonthArray[8] = "Sep";
strMonthArray[9] = "Oct";
strMonthArray[10] = "Nov";
strMonthArray[11] = "Dec";
strDate = datefield.value;
if (strDate.length < 1) {

return err;
}
for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
strDateArray = strDate.split(strSeparatorArray[intElementNr]);
if (strDateArray.length != 3) {
err = 1;

return err;
}
else {
strDay = strDateArray[0];
strMonth = strDateArray[1];
strYear = strDateArray[2];
}
booFound = true;
   }
}
if (booFound == false) {
if (strDate.length>5) {
strDay = strDate.substr(0, 2);
strMonth = strDate.substr(2, 2);
strYear = strDate.substr(4);
   }
}
if (strYear.length == 2) {
strYear = '20' + strYear;
}
// US style
if (strDatestyle == "US") {
strTemp = strDay;
strDay = strMonth;
strMonth = strTemp;
}
intday = parseInt(strDay, 10);
if (isNaN(intday)) {
err = 2;

return err;
}
intMonth = parseInt(strMonth, 10);
if (isNaN(intMonth)) {
for (i = 0;i<12;i++) {
if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) {
intMonth = i+1;
strMonth = strMonthArray[i];
i = 12;
   }
}
if (isNaN(intMonth)) {
err = 3;

return err;
   }
}
intYear = parseInt(strYear, 10);
if (isNaN(intYear)) {
err = 4;

return err;
}
if (intMonth>12 || intMonth<1) {
err = 5;

return err;
}
if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
err = 6;

return err;
}
if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
err = 7;

return err;
}
if (intMonth == 2) {
if (intday < 1) {
err = 8;

return err;
}
if (LeapYear(intYear) == true) {
 if (intday > 29) {
 err = 9;

 return err;
 }
}
else {
  if (intday > 28) {
  err = 10;

  return err;
}
}
}
date1=0;
if (strDatestyle == "US") {
	date1 = strMonthArray[intMonth-1] + " " + intday+" " + strYear;
}
else 
{
	date1 = intday + " " + strMonthArray[intMonth-1] + " " + strYear;
}

if (err >0 )
   date1 = 0;

return date1;
}




function LeapYear(intYear) {
if (intYear % 100 == 0) {
if (intYear % 400 == 0) { return true; }
}
else {
if ((intYear % 4) == 0) { return true; }
}
return false;
}


function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=chStack&formIdName=chStackFrom";
	window.open(url, 'StackSearch', 500, 500);
}

function searchFuel(){
	var url = "/eespc/FuelTankListInfo.do?formId=chFuel&formIdName=chFuelFrom&tankType=N";
	window.open(url, 'FuelSearch', 500, 500);
}	