
function validateDep(){
	if (    document.forms[0].lfDEPNumber.value.length ==0 || document.forms[0].lfDEPIssueDate.value.length ==0 || document.forms[0].lfDEPExpirationDate.value.length ==0)
	{
		alert("Please enter all the mandatory information for DEP.");
		return false;		
	}

	        if (!isNumeric(document.forms[0].lfDEPNumber.value, "DEP Number "))
	        {   
	            return false;
	        }
	      
	        if (!isDate(document.forms[0].lfDEPIssueDate.value))
	        {   
	            alert("Invalid DEP issue Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].lfDEPExpirationDate.value))
	        {   
	            alert("Invalid DEP Expiration Date");
	            return false;
	        }

	
	dateFrom = chkdate(document.forms[0].lfDEPIssueDate);
	dateTo   = chkdate(document.forms[0].lfDEPExpirationDate);

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


function validateLoc(){
	if (document.forms[0].lfLocIssueDate.value.length ==0 || document.forms[0].lfLocExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory building information.");
		return false;		
	}
	        if (!isDate(document.forms[0].lfLocIssueDate.value))
	        {   
	            alert("Invalid Location issue Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].lfLocExpirationDate.value))
	        {   
	            alert("Invalid Location Expiration Date");
	            return false;
	        }
	
	dateFrom = chkdate(document.forms[0].lfLocIssueDate);
	dateTo   = chkdate(document.forms[0].lfLocExpirationDate);

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



function validateState(){
	if (document.forms[0].lfStateIssueDate.value.length ==0 ||document.forms[0].lfStateExpirationDate.value.length ==0 )
        {
		alert("Please enter all the mandatory State information.");
		return false;		
	}
	        if (!isDate(document.forms[0].lfStateIssueDate.value))
	        {   
	            alert("Invalid State issue Date");
	            return false;
	        }
	        
	        if (!isDate(document.forms[0].lfStateExpirationDate.value))
	        {   
	            alert("Invalid State Expiration Date");
	            return false;
	        }

	dateFrom = chkdate(document.forms[0].lfStateIssueDate);
	dateTo   = chkdate(document.forms[0].lfStateExpirationDate);

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

var dtCh= "-";
var minYear=1900;
var maxYear=2100;

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strMonth=dtStr.substring(0,pos1)
	var strDay=dtStr.substring(pos1+1,pos2)
	var strYear=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		alert("The date format should be : mm-dd-yyyy")
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		alert("Please enter a valid month")
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		alert("Please enter a valid day")
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		alert("Please enter a valid date")
		return false
	}
    return true
}

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}



function validateThis(){
        date1 = document.forms[0].lfDate;

           var c = document.forms[0].lfCoTurbines.options.length;
         
	if (document.forms[0].lfId.value.length ==0 ||
	    document.forms[0].lfDate.value.length ==0 ||
	    document.forms[0].psLocation.selectedIndex == 0 )
        {
		alert("Please enter all the mandatory Landfills information.");
		return false;		
	}
	if (document.forms[0].lfDate.value.length > 0) 
	{
	     
	     return(isDate(document.forms[0].lfDate.value));
        }
	return true;
}

function isYear(s){
    var i;
    if(s.length < 4 || s.length > 4)
    {
       alert("Invalid Year."); 
       return false;
     }
    for (i = 0; i < 4; i++)
    {   
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) 
        {
           alert("Invalid Year."); 
           return false;
           }
    }
    var yr = s;
    if(s < 1900 || s > 2010) 
    {
       alert("Invalid Year."); 
       return false;
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
           alert( b + " Should be Numeric."); 
           return false;
        }
    }
    // All characters are numbers.
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

function getCoGenList(){
	var url = "/eespc/CogenListAction.do";
	window.open(url, 'CoGenTurbineList', 500, 500);
}


function depEdit(id)
{
        alert("Inside DEP Edit");
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='depEdit';
        document.forms[0].submit();
        alert("Leave DEP Edit");
}


function addDep(updateFlag)
{
    var isCheckOk = true;
    if ( validateDep()==false ){
       isCheckOk = false;
    }
    else
        return false;
    
    if(isCheckOk ){
    	if (updateFlag){
    		document.forms[0].methodToCall.value='depUpdate';
    	}else{
        	document.forms[0].methodToCall.value='dep';
        }
    }
    return true;
}
