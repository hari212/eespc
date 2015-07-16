function addfumehood(updateFlag)
{
      if (document.forms[0].FumehoodId.value.length ==0)
	{
		alert("Please enter the Id.");
		document.forms[0].FumehoodId.focus();
		return false;		
	}
	else if(isyearFieldEnteredyear(document.forms[0].Fumehood_yearInstalled)==false)
	{
		document.forms[0].Fumehood_yearInstalled.focus();
		return false;
	}
	else if(document.forms[0].Fumehood_status.selectedIndex==0)
	{
		alert("Please Select Status.");
		document.forms[0].Fumehood_status.focus();
		return false;
	}
	else if(isyearFieldEnteredyear(document.forms[0].Fumehood_disconnecteddate) ==false)
	{
		document.forms[0].Fumehood_disconnecteddate.focus();
		return false;	
	}

	if (updateFlag)
	{
		document.forms[0].methodToCall.value='update';					
	}
	else
	{
		document.forms[0].methodToCall.value='save';
	}
		document.forms[0].submit();	
		return true;

	
}

function searchStack(){
	var url = "/eespc/StackListInfo.do?formId=Fumehood_fuelFrom&formIdName=Fumehood_fuelFromName";
	window.open(url, 'StackSearch', 500, 500);
}


function addDep(updateFlag)
{

	if(isdateFieldEnteredcheckdate(document.forms[0].FumehoodDEPIssueDate) ==false)
	{
		document.forms[0].FumehoodDEPIssueDate.focus();
		return false;	
	}

	else if(isdateFieldEnteredcheckdate(document.forms[0].FumehoodDEPExpirationDate) ==false)
	{
		document.forms[0].FumehoodDEPExpirationDate.focus();
		return false;	
	}	

	
	if (updateFlag)
	{
    		document.forms[0].methodToCall.value='depUpdate';
    	}
	else
	{
        	document.forms[0].methodToCall.value='dep';
      }
      document.forms[0].submit();
      return true;

}


function cal()
{
	document.forms[0].Fumehood_voct1.value=parseFloat(((document.forms[0].Fumehood_volume1.value*document.forms[0].Fumehood_density1.value)/2000)*(document.forms[0].Fumehood_voc1.value/100)).toFixed(2);
}

function cal1()
{
	document.forms[0].Fumehood_voct2.value=parseFloat(((document.forms[0].Fumehood_volume2.value*document.forms[0].Fumehood_density2.value)/2000)*(document.forms[0].Fumehood_voc2.value/100)).toFixed(2);
}

function cal2()
{
	document.forms[0].Fumehood_voct3.value=parseFloat(((document.forms[0].Fumehood_volume3.value*document.forms[0].Fumehood_density3.value)/2000)*(document.forms[0].Fumehood_voc3.value/100)).toFixed(2);
}

function cal3()
{
	document.forms[0].Fumehood_voct4.value=parseFloat(((document.forms[0].Fumehood_volume4.value*document.forms[0].Fumehood_density4.value)/2000)*(document.forms[0].Fumehood_voc4.value/100)).toFixed(2);
}

function cal4()
{
	document.forms[0].Fumehood_voct5.value=parseFloat(((document.forms[0].Fumehood_volume5.value*document.forms[0].Fumehood_density5.value)/2000)*(document.forms[0].Fumehood_voc5.value/100)).toFixed(2);
}

function cal5()
{


	var a=parseFloat(document.forms[0].Fumehood_voct1.value);
	var b=parseFloat(document.forms[0].Fumehood_voct2.value);
	var c=parseFloat(document.forms[0].Fumehood_voct3.value);
	var d=parseFloat(document.forms[0].Fumehood_voct4.value);
	var e=parseFloat(document.forms[0].Fumehood_voct5.value);
	if(isNaN(a))
	{
		a=0;
	}
	if(isNaN(b))
	{
		b=0;
	}

	if(isNaN(c))
	{
		c=0;
	}

	if(isNaN(d))
	{
		d=0;
	}

	if(isNaN(e))
	{
		e=0;
	}
	document.forms[0].Fumehood_voc.value=(a+b+c+d+e).toFixed(2);
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
			else  
			{
			tempVal1= true;
			}
	}	
return tempVal1;
}

