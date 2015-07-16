
function validateDep(){
	if (document.forms[0].rpzLastInspDate.value.length ==0 || document.forms[0].rpzNextInspDate.value.length ==0)
	{
		alert("Please enter all the mandatory information for Inspection.");
		return false;		
	}
	dateFrom = chkdate(document.forms[0].rpzLastInspDate);
	dateTo   = chkdate(document.forms[0].rpzNextInspDate);

        if(Date.parse(dateFrom) <= Date.parse(dateTo))
	        {
	        	return true;
				
		}
		else
		{
		      alert("Next date must be Greater than Last Date");
		      return false; 
		}
	
	return true;
}


function validateLoc(){
	if (document.forms[0].rpzLocNumber.value.length ==0 || document.forms[0].rpzLocIssueDate.value.length ==0 || document.forms[0].rpzLocExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory Location information.");
		return false;		
	}
	dateFrom = chkdate(document.forms[0].rpzLocIssueDate);
	dateTo   = chkdate(document.forms[0].rpzLocExpirationDate);

        if(Date.parse(dateFrom) <= Date.parse(dateTo))
	        {
	        	return true;
				
		}
		else
		{
		      alert("Location - Expiration date must be Greater than Issue Date");
		      return false; 
		}

	return true;
}


function validateMe(){
	if (	document.forms[0].rpzId.value.length ==0 || document.forms[0].rpzYear.value.length ==0 ||
		document.forms[0].rpzSerialNo.value.length ==0 ||
		document.forms[0].rpzMake.value.length ==0 ||
		document.forms[0].rpzModel.value.length ==0 ||
		document.forms[0].rpzSize.value.length ==0 ||
		document.forms[0].rpzDOB.value.length ==0 ||
		document.forms[0].rpzDOB.value.length ==0 ||
		document.forms[0].psLocation.selectedIndex ==0 )
		{
			alert("Please enter all the mandatory RPZ information.");
		return false;		
	}
	return true;
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
alert("" + err);
return err;
}
for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
strDateArray = strDate.split(strSeparatorArray[intElementNr]);
if (strDateArray.length != 3) {
err = 1;
alert("" + err);
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
alert("" + err);
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
alert("" + err);
return err;
   }
}
intYear = parseInt(strYear, 10);
if (isNaN(intYear)) {
err = 4;
alert("" + err);
return err;
}
if (intMonth>12 || intMonth<1) {
err = 5;
alert("" + err);
return err;
}
if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
err = 6;
alert("" + err);
return err;
}
if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
err = 7;
alert("" + err);
return err;
}
if (intMonth == 2) {
if (intday < 1) {
err = 8;
alert("" + err);
return err;
}
if (LeapYear(intYear) == true) {
 if (intday > 29) {
 err = 9;
 alert("" + err);
 return err;
 }
}
else {
  if (intday > 28) {
  err = 10;
  alert("" + err);
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
