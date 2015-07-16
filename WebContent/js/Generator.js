// JavaScript Document

function addGenerator(updateFlag)
{
	
if(document.forms[0].G_facilityDesignatedId.value.length==0)
{
alert("Please Enter The Facility Designated Id.");
document.forms[0].G_facilityDesignatedId.focus();
return false;
}
else if(document.forms[0].G_primaryUse.selectedIndex==0)
{
alert("Please Select Primary Use.");
document.forms[0].G_primaryUse.focus();
return false;
}
else if(document.forms[0].G_status.selectedIndex==0)
{
alert("Please Select Tank Status.");
document.forms[0].G_status.focus();
return false;
}
else if(document.forms[0].G_capacity.value.length==0)
{
alert("Please Enter The Capacity.");
document.forms[0].G_capacity.focus();
return false;
}
else if(document.forms[0].G_primaryFuel.selectedIndex==0)
{
alert("Please Select Fuel.");
document.forms[0].G_primaryFuel.focus();
return false;
}
else if(isyearFieldEnteredyear(document.forms[0].G_yearInstalled) ==false)
	{
		document.forms[0].G_yearInstalled.focus();
		return false;	
	}
else if(isyearFieldEnteredyear(document.forms[0].G_disconnecteddate) ==false)
	{
		document.forms[0].G_disconnecteddate.focus();
		return false;	
	}


else if(isdateFieldEnteredcheckdate(document.forms[0].G_ProtocalSubmittalDate) ==false)
{
document.forms[0].G_ProtocalSubmittalDate.focus();
return false; 
}

else if(isdateFieldEnteredcheckdate(document.forms[0].G_StackTestDate) ==false)
{
document.forms[0].G_StackTestDate.focus();
return false; 
}

else if(isdateFieldEnteredcheckdate(document.forms[0].G_nextStackTestDate) ==false)
{
document.forms[0].G_nextStackTestDate.focus();
return false; 
}




			if (updateFlag)
	       	{
	       		document.forms[0].methodToCall.value='update';					
			}
			else
			{
				document.forms[0].methodToCall.value='add';
			}
				document.forms[0].submit();	
           
	return true;	
}

function GUI_CHANGE(){
	//document.forms[0].G_primaryUse.value

	if(document.forms[0].capablefuel[0]){
		if(document.forms[0].capablefuel[0].checked){
			show_hide_column(1, true);
			show_hide_column(2, true);
			show_hide_column(4, true);
			show_hide_column(5, true);
		}
		if(document.forms[0].capablefuel[1].checked){
			if(document.forms[0].oilfiring[0]){
				if(document.forms[0].oilfiring[0].checked){
					show_hide_column(1, true);
					show_hide_column(2, false);
					show_hide_column(4, true);
					show_hide_column(5, false);
				}
				if(document.forms[0].oilfiring[1].checked){
					show_hide_column(1, false);
					show_hide_column(2, true);
					show_hide_column(4, false);
					show_hide_column(5, true);
				}
			}			
		}	
			
			
		
	}
	
	alert(document.forms[0].capablefuel[0].checked);
	//alert(document.forms[0].oilfiring[0].checked);
	
}
function show_hide_column(col_no, do_show) {

    var stl;
    if (do_show) stl = 'block'
    else         stl = 'none';

    var tbl  = document.getElementById('hiddingTable');
    var rows = tbl.getElementsByTagName('tr');

    for (var row=1; row<rows.length;row++) {
      var cels = rows[row].getElementsByTagName('td');
      cels[col_no-1].style.display=stl;
    }
  }
function generatorDateCheck(_formObj){
	if(!_formObj){
		return true;
	}
    if (isDate(_formObj.value)==false){
        _formObj.focus();
        return false;
    }        
    return true;
}

//@TODO - to get the calorific value dynamically 
function fuelChanged(){
	var factor =0;
		if(document.forms[0].G_primaryFuel.selectedIndex !=0 && 
			document.forms[0].G_capacity.value.length >0){
			//Natural Gas
			if (document.forms[0].G_primaryFuel[document.forms[0].G_primaryFuel.selectedIndex].value=="1"){

				document.forms[0].G_naturalGasFiringRate.value =parseFloat(document.forms[0].G_capacity.value * 0.01* 1000).toFixed(2);
				document.forms[0].G_oilFiringRate.value = "";
			}else if (document.forms[0].G_primaryFuel[document.forms[0].G_primaryFuel.selectedIndex].value=="5"){
				document.forms[0].G_oilFiringRate.value = parseFloat((document.forms[0].G_capacity.value * 0.01*1000)/ 140).toFixed(2);
				document.forms[0].G_naturalGasFiringRate.value = "";
			}
		}
}

function addDep(updateFlag)
{
    

  if(isdateFieldEnteredcheckdate(document.forms[0].depIssueDate)==false)
	{
	document.forms[0].depIssueDate.focus();
	return false;
	}
  else if(isdateFieldEnteredcheckdate(document.forms[0].depExpDate)==false)
	{
	document.forms[0].depExpDate.focus();
	return false;
	}


    
    	if (updateFlag){
    		document.forms[0].methodToCall.value='updateDepPermitInfo';
    	}else{
		document.forms[0].methodToCall.value='depPermitInfo';
	}
	document.forms[0].submit();
    
    return true;

}

function addDob(updateFlag)
{
    

    if(isdateFieldEnteredcheckdate(document.forms[0].dobIssueDate)==false)
	{
	document.forms[0].dobIssueDate.focus();
	return false;
	}
   else if(isdateFieldEnteredcheckdate(document.forms[0].dobExpDate)==false)
	{
	document.forms[0].dobExpDate.focus();
	return false;
	}




    	if (updateFlag){
    		document.forms[0].methodToCall.value='updateDobPermitInfo';
    	}else{
		document.forms[0].methodToCall.value='dobPermitInfo';
	}
	document.forms[0].submit();
    
	return true;

}



function addCfr(updateFlag)
{
    

  if(isdateFieldEnteredcheckdate(document.forms[0].initialTestDate)==false)
	{
	document.forms[0].initialTestDate.focus();
	return false;
	}
  else if(isdateFieldEnteredcheckdate(document.forms[0].lastSubsequentTestDate)==false)
	{
	document.forms[0].lastSubsequentTestDate.focus();
	return false;
	}

    
    	if (updateFlag){
    		document.forms[0].methodToCall.value='updateCfrPermitInfo';
    	}else{
		document.forms[0].methodToCall.value='cfrPermitInfo';
	}
	document.forms[0].submit();
    
    return true;

}



function addFuelConsumption()
{
	document.forms[0].methodToCall.value='fuelConsumptionInfo';
	document.forms[0].submit();
	return true;

}



function addEngineRunningHrs()
{
		
	document.forms[0].methodToCall.value='engineRunningHrsInfo';
	document.forms[0].submit();
	return true;
}




function calcOtherUnit(){
	if (document.forms[0].G_capacity.value.length >0){
		var tempInt =0;
		tempInt = document.forms[0].G_capacity.value;
		document.forms[0].G_kvaText.value = (tempInt * 1.25);
		document.forms[0].G_hpText.value = (tempInt * 1.34);		
		document.forms[0].G_mmbtuText.value = (tempInt * 0.01);
		fuelChanged();				
	}
}
function searchFuel(){
	var url = "/eespc/FuelTankListInfo.do?formId=G_fuelFrom&formIdName=G_fuelFromName&tankType=N";
	window.open(url, 'TankSearch', 500, 500);
}
function searchDayTankFuel(){
	var url = "/eespc/FuelTankListInfo.do?formId=G_dayTankFrom&formIdName=G_dayTankFromName&tankType=Y";
	window.open(url, 'TankSearch2', 500, 500);
}
function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=G_stackFrom&formIdName=G_stackFromName";
	window.open(url, 'StackSearch', 500, 500);
}
function useChanged()
{
	document.forms[0].methodToCall.value='displayControl';
	document.forms[0].submit();
}

function editDobPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editDobPermit';
	document.forms[0].submit();
}
function editDepPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editDepPermit';
	document.forms[0].submit();
}

function editCfrPermit(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editCfrPermit';
	document.forms[0].submit();
}

function deleteDepPermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteDepPermit';
	document.forms[0].submit();
}
 else
 {
 
  }
}


function deleteCfrPermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteCfrPermit';
	document.forms[0].submit();
}
 else
 {
 
  }
}

function deleteDobPermit(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='deleteDobPermit';
	document.forms[0].submit();
}
 else
 {
 
  }
}
function isdateFieldEnteredcheckdate(formObject)
{
var tempVal1=true;	
if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0)
			{
			tempVal1=isDate(formObject.value)
			}
		else  {
			tempVal1= true;
			}
	}	
	
	return tempVal1;
}

function isyearFieldEnteredyear(formObject)
{
var tempVal1=true;	
if(formObject)
	{
        var tempVal = trim(formObject.value);
		if (tempVal.length>0)
			{
				var year = parseInt(formObject.value);
				if(year<1900)
				{
				alert("Please Enter Year Between 1900-2100 :");
      	 			tempVal1= false;
				}
      				else if(year>2100)
				{
				alert("Please Enter Year Between 1900-2100 :");			
				tempVal1= false;
				}	
			}
			else  {
			tempVal1= true;
			}
	}	
	
	return tempVal1;
}


