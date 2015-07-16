// JavaScript Document
function printMandatoryAlert(){
    alert("Please enter all the mandatory information.");	
}
function sort(sortKey){
	document.forms[0].TABLETAG_SORTKEY.value=sortKey;
	document.forms[0].submit();
}

function alertMessage(message, messageType)
{
	var msg = messageType.toUpperCase() + "\n\n";
	if(message != null && message !='')
	{
		message = message.replace(/\+/g, "%20");
		alert (msg + unescape(message));
	}
	return;
}

function returnToBuilding()
{
    document.forms[0].action='/eespc/BuildingInfo.do?methodToCall=view';
	document.forms[0].submit();
}
function radioChecked(formObject)
{
	if(!formObject){
		return true;
	}

	if(formObject)
	{
		for (var i=0; i<formObject.length ; i++)
		{
			if (formObject[i].checked){
				return true;
			}
		}
	}
	
	return false;
}

function trim(val){
    return val.replace(/^\s*|\s*$/g,"");
}

function isFieldEntered(formObject)
{
	if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0){
			return true;
		}else{
			return false;
		}
	}	
	
	return true;
}
function radioValueTrue(formObject)
{
	if(formObject)
	{
		for (var i=0; i<formObject.length ; i++)
		{
			if (formObject[i].checked && formObject[i].value=="Y"){
				return true;
			}
		}
	}
	return false;
}

function intCheck(val){
    val=val.replace(/[^-\d]/g,'')
    val=val.replace(/(\d)-/g,'$1')
    return val
}

function numberWithDotCheck(val)
{
    var anum=/(^-*\d+$)|(^-*\d+\.\d+$)/;

    if(!anum.test(val)){
        val=val.replace(/[^.\d]/g,'');
    }
    return val;
}

/*
<html:text property="installFee" onkeyup="this.value=intCheck(this.value)" 
    onchange="this.value=intCheck(this.value)"/>
*/                   

/*
    To check whether the radio button is checked or not. 
    return true if checked else false.
*/
function isRadioChecked(radio)
{
	if(!radio){
		return true;
	}
	
    var len = radio.length;

    if (len == null)   // one element only
    {
        if (radio.checked)
            return true;
    }
    else              // multiple elements
    {
        for (var i=0; i<len; i++)
        {
            if (radio[i].checked)
                return true;
        }
    }

    return false;
}

function isDropDownSelected(formObj)
{
    if(formObj){
        if(formObj[formObj.selectedIndex].value==-1){
            return false;
        }else{
            return true;
        } 
    }
    return false;
}

function isTextEnteredForRadio(_radioFormObj, _textFormObj)
{
    if (radioChecked(_radioFormObj)){
        //if the radio is selected Y, then text should be entered.
        if(radioValueTrue(_radioFormObj)){
            if(isFieldEntered(_textFormObj)){
                return true;
            }else{
                return false;
            }
        }else{
        //if radio is selected NO, then text value should be optional
            return true;
        }
    }else{
        printMandatoryAlert();
        return false;
    }
}


/************************ DATE VALIDATION FUNCTIONS START ****************/

// Declaring valid date character, minimum year and maximum year
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

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

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
    // February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

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
		alert("The date format should be : mm/dd/yyyy")
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
/* example 
function ValidateForm(){
	var dt=document.frmSample.txtDate
	if (isDate(dt.value)==false){
		dt.focus()
		return false
	}
    return true
 }
*/
function isDateFormatOk (_formObj){
    if (isDate(_formObj.value)==false){
        _formObj.focus();
        return false;
    }        
    return true;
}

function compareDate(date1, date2)
{
    date1 = (date1==null)?'':date1;
    date2 = (date2==null)?'':date2;
    if (date1 == '' || date2 == '')  // don't check
        return -1;

	var date1Array=date1.split('/');
	var date2Array=date2.split('/');
	var startdate =new Date(date1Array[2],date1Array[0],date1Array[1]);
	var enddate=new Date(date2Array[2],date2Array[0],date2Array[1]); 	
	if(startdate.getTime() < enddate.getTime() )
	{
		return 1;
	}else{
		return -3;
	}
	
/*    if (date1 > date2)
        return -2;
        
    if (date1 == date2)
    {
            return -1;
    }
*/	
    return -1;
}
/*
    Pass the value not the object
*/
function checkDates(issueDate, expirationDate, errorMessage)
{
    var returnVal = compareDate(issueDate, expirationDate);
//    alert("checkDates(issueDate, expirationDate, errorMessage) returnVal=" + returnVal);
    if ( returnVal < 0)
    {
        alert("ERROR\n\n" + errorMessage);
                //"The Start Date and Time must be the same or earlier than the End Date and Time.");
        return false;
    }
    return true;
}

function validatePermitDate(issueDate, expirationDate){
    var dateErrorMessage = "Enter all mandatory dates, Issue Date should be earlier than Expire date";
    if (isDate(issueDate.value)==false){
            issueDate.focus();
            return false;
    }

    if (isDate(expirationDate.value)==false){
            expirationDate.focus();
            return false;
    }
    
    if(checkDates(issueDate.value, expirationDate.value, dateErrorMessage)==false){
        return false;
    }
	return true;
}
/************************ DATE VALIDATION FUNCTIONS END ****************/
function checkPhone( phone ) {
  phoneRegex = /\d\d\d\-\d\d\d-\d\d\d\d$/;
 if( !phone.match( phoneRegex ) ) {
  alert( "Please enter a valid phone number. (FORMAT: xxx-xxx-xxxx)" );
  return false;
 }
 return true;
}
function validateEmail(_formObj)
{
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(_formObj.value)){
		return (true)
	}
	alert("Invalid E-mail Address! Please re-enter.")
	_formObj.focus();
	return (false)
}

function returnToFacility()
{
	document.forms[0].action='/eespc/BuildingInfo.do?methodToCall=view';
	document.forms[0].submit();
}

 var UNDEFINED;  // do not assign!
 var PHONEMASK = "(###) ###-####";

 // example:  formatPhoneNumber(document.formname.txtFieldName); 
 /*
	 This function will auto format the phone numbers. Will be called onBlur event
 */
 function formatPhoneNumber( fieldOrValue, displayName)  
 {
    if ( fieldOrValue==UNDEFINED ) return(fieldOrValue);
   
    s = (fieldOrValue.value==UNDEFINED) ? (""+fieldOrValue) : fieldOrValue.value;
    if ( s.length==0 ) return(s);  
    s = s.replace(/[^\d]*/gi,"");  // strip out all non-digits before imposing the mask
    s = (s.length>9) ? s.replace(/^([\d]{3})([\d]{3})([\d]{4})([\d]*)$/gi,"$1-$2-$3").replace(/[ex]$/,"") : PHONEMASK;

    if (s==PHONEMASK)
    {
		var tempDispName='';
		if (displayName.length==0)
		{
			tempDispName=fieldOrValue.name;
		}
		else
		{
			tempDispName=displayName;
		}
	 	alert("Please check the "+ tempDispName + " #"  );
	 	s=fieldOrValue.value;   
	 	fieldOrValue.focus();
    }
    return( (fieldOrValue.value==UNDEFINED) ? fieldOrValue=s : fieldOrValue.value=s );
 }
