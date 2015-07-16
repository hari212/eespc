
function validate(updateFlag){

	if (document.forms[0].bldgName.value.length ==0)
		{
		alert("Please enter the Building Name.");
		document.forms[0].bldgName.focus();
		return false;
		}
	else if (document.forms[0].bldgId.value.length ==0)
		{
		alert("Please enter the Building Id.");
		document.forms[0].bldgId.focus();
		return false;
		}

	else if (document.forms[0].borough.selectedIndex ==0)
		{  
		alert("Please Select Borough");
		document.forms[0].borough.focus();
		return false; 
		}

	else if (document.forms[0].bldgTall.value.length ==0)
		{
		alert("Please enter the No.Of Stories.");
		document.forms[0].bldgTall.focus();
		return false;
		}		
	
	if(updateFlag){
		document.forms[0].methodToCall.value='update';
	}
	document.forms[0].submit();
	return true;
}

function radioChecked(formObject)
{
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

function validateBldgInsp(updateFlag)
{
	
	 if (radioChecked(document.forms[0].facadeInspected)==0)
          {	
		alert("Please Select Facade Inspected for DOB.");
		document.forms[0].facadeInspected.focus();
		return false;	
          }



	if (updateFlag){
		document.forms[0].methodToCall.value='updateBuildingInspection';
	}else{
		document.forms[0].methodToCall.value='addBuildingInspection';
	}
	document.forms[0].submit();
	return true;

}

function addResource()
{

    if (document.forms[0].resourceType.selectedIndex ==0 ){
			alert("Please select Source Type.");
		return false;		
	}

		if(document.forms[0].resourceType.selectedIndex==3 || document.forms[0].resourceType.selectedIndex==8 || document.forms[0].resourceType.selectedIndex==14)
		{
			var where_to= confirm("Ensure that the Stack and Fuel oil tank(if applicable) is already created. If not,Create the Stack and Tank information for this Source before proceeding with the creation of this unit. If you want to continue Click Ok.");
 			if (where_to== true)
 			{
				document.forms[0].action=document.forms[0].resourceType[document.forms[0].resourceType.selectedIndex].value;
				document.forms[0].submit();
				return true;
			}
		}
		else
		{
				document.forms[0].action=document.forms[0].resourceType[document.forms[0].resourceType.selectedIndex].value;
				document.forms[0].submit();
				return true;

		}
	
}

function editBuildingInspection(id)
{
	document.forms[0].hdnPermitId.value=id;
	document.forms[0].methodToCall.value='editBuildingInspection';
	document.forms[0].submit();
}

function deleteBuildingInspection(id)
{
 	var where_to= confirm("Do you want delete this??");
 	if (where_to== true)
 	{
		document.forms[0].hdnPermitId.value=id;
		document.forms[0].methodToCall.value='deleteBuildingInspection';
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

function viewSource(id, type)
{
	if (type ==1){
		document.forms[0].id.value=id;
		document.forms[0].methodToCall.value='view';	
		document.forms[0].action='/eespc/StackInfo.do';
	}else if (type ==2){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/StorageTankInfo.do';
	}else if (type ==3){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/HydraulicStorageTankInfo.do';
	}else if (type ==4){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/BulkOxygenStorageTankInfo.do';
	}else if (type ==5){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/BridgeTunnelInfo.do';
	}else if (type ==6){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/BridgeTunnelInfo.do';
	}else if (type ==7){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/RpzAction.do';
	}else if (type ==8){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/LandFillsAction.do';
	}else if (type ==9){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/PaintSprayAction.do';
	}else if (type ==10){
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/Boiler.do';
	}else if (type ==11){//Generator
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/Generator.do';
	}else if (type ==12){ //Co Gen Engines
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/Generator.do';
	}else if (type ==13){ //Turbines
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/Generator.do';
	}else if (type ==14){ //eto
		document.forms[0].id.value=id;	
		document.forms[0].methodToCall.value='view';
		document.forms[0].action='/eespc/Eto.do';
	}

	document.forms[0].submit();	
}
