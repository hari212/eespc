function goToResult(){
	if( validateCustomerInfo()==true && 
		validateForContact() ==true &&
		checkPhone(document.forms[0].phone.value) == true && 
		checkPhone(document.forms[0].fax.value) == true){
		document.forms[0].methodToCall.value="add";
		document.forms[0].submit();
	}
}

function validateCustomerInfo(){
	if( document.forms[0].clName.value.length ==0 ||
		document.forms[0].decId.value.length ==0 ||
		document.forms[0].vPresident.value.length ==0 ||
		document.forms[0].borough.value.length ==0 ||
		document.forms[0].addr1.value.length ==0 ||
		document.forms[0].city.value.length ==0 ||
		document.forms[0].zip.value.length ==0 ||
		document.forms[0].phone.value.length ==0 ||
		document.forms[0].fax.value.length ==0 ||
		document.forms[0].state.selectedIndex ==0 ||		
		document.forms[0].facilityType.selectedIndex ==0 ){
			alert("Please enter all the mandatory facility information.");
			return false;		
		}
		return true;
}

function validateForContact(){
	if(document.forms[0].pContact.selectedIndex == 0 &&
		document.forms[0].sContact.selectedIndex == 0 &&
		document.forms[0].tContact.selectedIndex == 0 ){
		alert("Please enter atleast one contact type.");
		return false;		
	}

	if( (document.forms[0].pContact.selectedIndex !=0 &&
			(document.forms[0].pFirstName.value.length ==0 || 
			document.forms[0].pLastName.value.length ==0 || 
			document.forms[0].pAddr1.value.length ==0 ||
			document.forms[0].pCity.value.length ==0 ||
			document.forms[0].pState.selectedIndex ==0 ||
			document.forms[0].pZip.value.length ==0 ||
			document.forms[0].pPhone.value.length ==0) ) ||
		(document.forms[0].sContact.selectedIndex !=0 &&
			(document.forms[0].sFirstName.value.length ==0 || 
			document.forms[0].sLastName.value.length ==0 || 
			document.forms[0].sAddr1.value.length ==0 ||
			document.forms[0].sCity.value.length ==0 ||
			document.forms[0].sState.selectedIndex ==0 ||
			document.forms[0].sZip.value.length ==0 ||
			document.forms[0].sPhone.value.length ==0) ) ||		  
		(document.forms[0].tContact.selectedIndex !=0 &&
			(document.forms[0].tFirstName.value.length ==0 || 
			document.forms[0].tLastName.value.length ==0 || 
			document.forms[0].tAddr1.value.length ==0 ||
			document.forms[0].tCity.value.length ==0 ||
			document.forms[0].tState.selectedIndex ==0 ||
			document.forms[0].tZip.value.length ==0 ||
			document.forms[0].tPhone.value.length ==0) )			
		){
			alert("Please enter the mandatory informations.");
			return false;
		}else{
			var conditionChecked = true;
			if (isDropDownSelected(document.forms[0].pContact))
			{
				if (isFieldEntered(document.forms[0].pPhone))
				{
					conditionChecked = checkPhone(document.forms[0].pPhone.value);
				}
				if (conditionChecked && isFieldEntered(document.forms[0].pMobilePhone))
				{
					conditionChecked = checkPhone(document.forms[0].pMobilePhone.value)
				}
				if (conditionChecked && isFieldEntered(document.forms[0].pFax))
				{
					conditionChecked = checkPhone(document.forms[0].pFax.value)
				}	
				if (conditionChecked && isFieldEntered(document.forms[0].pEmail))
				{
					conditionChecked = validateEmail(document.forms[0].pEmail);
				}
			}
			
			if (isDropDownSelected(document.forms[0].sContact))
			{
				if (isFieldEntered(document.forms[0].sPhone))
				{
					conditionChecked = checkPhone(document.forms[0].sPhone.value);
				}			
				if (conditionChecked && isFieldEntered(document.forms[0].sMobilePhone))
				{
					conditionChecked = checkPhone(document.forms[0].sMobilePhone.value)
				}
				if (conditionChecked && isFieldEntered(document.forms[0].sFax))
				{
					conditionChecked = checkPhone(document.forms[0].sFax.value)
				}	
				if (conditionChecked && isFieldEntered(document.forms[0].sEmail))
				{
					conditionChecked = validateEmail(document.forms[0].sEmail);
				}
							
			}
			if (isDropDownSelected(document.forms[0].tContact))
			{
				if (isFieldEntered(document.forms[0].tPhone))
				{
					conditionChecked = checkPhone(document.forms[0].tPhone.value);
				}	
				if (conditionChecked && isFieldEntered(document.forms[0].tMobilePhone))
				{
					conditionChecked = checkPhone(document.forms[0].tMobilePhone.value)
				}
				if (conditionChecked && isFieldEntered(document.forms[0].tFax))
				{
					conditionChecked = checkPhone(document.forms[0].tFax.value)
				}
				if (conditionChecked && isFieldEntered(document.forms[0].tEmail))
				{
					conditionChecked = validateEmail(document.forms[0].tEmail);
				}
			}	
			return 	conditionChecked;	
		}
		return true;
}

function search()
{
	if (document.forms[0].clName.value.length == 0 && 
		document.forms[0].clDecId.value.length == 0 &&
		document.forms[0].clFacilityType.selectedIndex == 0)
	{
			alert("Please enter atleast one search criteria.");
			return false;
	}
	document.forms[0].methodToCall.value='search';
	document.forms[0].submit();
}

function facilityDetails(id)
{
	document.forms[0].action='/eespc/AddFacility.do?methodToCall=view&id=' + id;
	document.forms[0].submit();
}
function editFacility(id)
{
	document.forms[0].action='/eespc/AddFacility.do?methodToCall=edit&id=' + id;
	document.forms[0].submit();
}

function deleteFacility(id)
{

var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      	document.forms[0].action='/eespc/AddFacility.do?methodToCall=delete&id=' + id;
	document.forms[0].submit();
}
 else
 {
 
  }



}


function update(){
	if( validateCustomerInfo()==true && validateForContact() ==true){
	document.forms[0].methodToCall.value="update";
		document.forms[0].submit();
	}
}

function addBuilding()
{
	document.forms[0].action='/eespc/BuildingInfo.do?methodToCall=initial';
	document.forms[0].submit();
}

function editBuilding(id)
{
	document.forms[0].action='/eespc/BuildingInfo.do?methodToCall=edit&bId=' + id;
	document.forms[0].submit();
}
function deleteBuilding(id)
{
var where_to= confirm("Do you want delete this??");
 if (where_to== true)
 {
      document.forms[0].action='/eespc/BuildingInfo.do?methodToCall=delete&bId=' + id;
	document.forms[0].submit();
}
 else
 {
 
  }

}
function viewBuilding(id)
{
	document.forms[0].action='/eespc/BuildingInfo.do?methodToCall=view&bId=' + id;
	document.forms[0].submit();
}