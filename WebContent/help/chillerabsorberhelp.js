
 function ID()
{
Tip('Enter facility designated id number for this chiller/absorber unit.  E.g., # 1', TITLE, 'EES HELP',WIDTH,'25')
}

 function StackExhausting()
{
Tip('<b>1</b>.Choose the stack that is connected to the  Chillers-Absorbers from the existing list.<br><b>2</b>. If it is a different stack, then a stack must first be created.<br><b>3</b>. The stack ID and specifications can be found in the DEC permit.  E.g. EP00001.<br><b>4</b>. If not, a facility designated number can be used to first create the stack and then choose from the stack list.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Floor()
{ 
Tip('Enter the floor number of chiller/absorber unit. E.g., basement.', TITLE, 'EES HELP',WIDTH,'25')
}

 function MakeModel()
{
Tip('Enter the series and/or the model  of the chiller/absorber unit.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Serial()
{
Tip('Enter the serial number from the equipment name plate.', TITLE, 'EES HELP',WIDTH,'25')
}


 function YearInstalledhelp()
{
Tip('Enter the year of installation of the chiller/absorber in YYYY.  It may be decoded from the DEP permit ID.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Capacity()
{
Tip('Enter the chiller\'s absorber\'s maximum heat generation capacity in million Btu/hr.', TITLE, 'EES HELP',WIDTH,'25')
}

 function DoesthechillerAbsorberhavefuelfiringability()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

 function BurnerType()
{
Tip('Choose if the associated burner is a single fuel firing type or dual fuel firing capable.', TITLE, 'EES HELP',WIDTH,'25')
}

 function PrimaryFuel()
{
Tip('Select the type of primary fuel that will be used from the drop-down menu.', TITLE, 'EES HELP',WIDTH,'25')
}

 function SecondaryFuel()
{
Tip('Select the back-up fuel, if applicable.', TITLE, 'EES HELP',WIDTH,'25')
}

 function FiringRateOil()
{
Tip('Automatically generated.', TITLE, 'EES HELP',WIDTH,'25')
}
 function FiringRateNaturalGas()
{
Tip('Automatically generated.', TITLE, 'EES HELP',WIDTH,'25')
}
 function Fuelfromtank()
{
Tip('<b>1</b>.Choose the tank from the existing list that will be used to supply fuel to the chiller/absorber (if it burns oil).<br><b>2</b>. Click the Search button to view listing of existing tanks from the database.  If the associated tank does not exist in the listing, it must be first created and then chosen.', TITLE, 'EES HELP',WIDTH,'25')
}
 function IsitincludedintheDECTitleVpermitapplicationannualemissionsstatement()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
 function DOBApproval()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
 function DEPApproval()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
 function MEA()
{
Tip('Enter the Material and Equipment Acceptance Number (available on equipment plate or contact DOB if it is required).', TITLE, 'EES HELP',WIDTH,'25')
}
 function StatusofSource()
{
Tip('Select the operational status of the generator. E.g., In-service, closed, out-of-service, etc....', TITLE, 'EES HELP',WIDTH,'25')
}
 function ActionTaken()
{
Tip('Enter if any changes/modfication were made to this unit. If yes, also indicate if the permit was updated or reflects current unit status.  If not, enter NONE.', TITLE, 'EES HELP',WIDTH,'25')
}

function IssueDatehelp()
{
Tip('Enter the permit issue date (mm/dd/yyyy).', TITLE, 'EES HELP',WIDTH,'25')
}

function DOBsignoff()
{
Tip('Choose Yes or NO, if a job signoff from the Department of Buildings was obtained.  If DoB does not have ruling in your area on this source, choose NO. ', TITLE, 'EES HELP',WIDTH,'25' )
}
function Comments()
{
Tip('Enter comments, if any.', TITLE, 'EES HELP',WIDTH,'25')
}