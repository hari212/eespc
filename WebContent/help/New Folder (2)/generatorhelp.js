function ID()
{
Tip('This is a number assigned by the facility.  E.g. # 1.', TITLE, 'EES HELP',WIDTH,'25')
}


 function Floor()
{ 
Tip('Enter the floor of the generator plant.  E.g. basement.', TITLE, 'EES HELP',WIDTH,'25')
}

function PermitID()
{
Tip('<b>1</b>.This is an ID designated by the NYSDEC.  It can be found in the DEC permit.  E.g., ES00001.<br><b>2</b>.If not, a facility designated number can be assigned.', TITLE, 'EES HELP',WIDTH,'25')
}

function PrimaryUse()
{
Tip('Choose the primary use of the generator; either Emergency power or Peak Load Management.', TITLE, 'EES HELP',WIDTH,'25')
}

function YearInstalledhelp()
{
Tip('Enter the year of installation of the generator in YYYY or it can be decoded from the DEP ID.', TITLE, 'EES HELP',WIDTH,'25')
}

function StatusOfSource()
{
Tip('The operational condition of the generator. E.g. In-service, closed, out-of-service, etc…', TITLE, 'EES HELP',WIDTH,'25')
}


function Manufacturer()
{
Tip('Enter the manufacturing company of the unit.  E.g. Caterpillar, Cummins, etc.', TITLE, 'EES HELP',WIDTH,'25')
}

function Make()
{
Tip('Enter the series or the same information as above.', TITLE, 'EES HELP',WIDTH,'25')
}

function Model()
{
Tip('Enter the model number of the generator.', TITLE, 'EES HELP',WIDTH,'25')
}

function Serial()
{
Tip('Enter the serial number from the equipment name plate.', TITLE, 'EES HELP',WIDTH,'25')
}

function Capacity()
{
Tip('Enter the generator’s maximum energy generation capacity in kilowatt hours (KWH).', TITLE, 'EES HELP',WIDTH,'25')
}
function Fuel()
{
Tip('Choose the fuel type, typically diesel oil (#2 oil)', TITLE, 'EES HELP',WIDTH,'25')
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
Tip('<b>1</b>.Choose the tank from the existing list that will be used to supply fuel to the Generator (if it burns oil).<br><b>2</b>.If the tank does not exist, it must be first created in the building section and then chosen.', TITLE, 'EES HELP',WIDTH,'25')
}

function HasDayTank()
{
Tip('<b>1</b>.Indicate whether the generator utilizes a day tank (tank used for approximately 12 hours daily)<br><b>2</b>. If yes; select the tank in the next box using the search button.<br><b>3</b>. If the tank is not in the pop-up list, it must be added (the same way a storage tank is added.)', TITLE, 'EES HELP',WIDTH,'25')
}

function DOB()
{
Tip('Enter the Department of Buildings/village/city/towns construction installation ID from the work permit.', TITLE, 'EES HELP',WIDTH,'25')
}

function MEA()
{
Tip('Enter the Materials and Equipment Acceptance number (available on equipment plate.)', TITLE, 'EES HELP',WIDTH,'25')
}

function DEP()
{
Tip('<b>1</b>.Enter the NYCDEP permit number.  It can be obtained from the work permit or from the registration.<br><b>2</b> If you are outside the NYC area, then enter the local environmental agency permit number.', TITLE, 'EES HELP',WIDTH,'25')
}


function ScheduleC()
{
Tip('Choose if a Schedule C for this generator is required or not.', TITLE, 'EES HELP',WIDTH,'25')
}

function PlanApproval()
{
Tip('Choose if a plan/ drawing approval from the DOB was obtained.', TITLE, 'EES HELP',WIDTH,'25')
}

 function StackExhausting()
{
Tip('<b>1</b>.Choose which stack the generator utilizes.<br><b>2</b>.If the stack does not exist, first create it under the building section.', TITLE, 'EES HELP',WIDTH,'25')
}

function Isrecordsinpermanentboundbook()
{
Tip('Confirm from the facility if a hard copy set of the generators current operation been placed in a binder.', TITLE, 'EES HELP',WIDTH,'25')
}

 function DECpermitsobtained()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function DEPpermitsobtained()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function StacktestprotocolsubmittedtoDEC()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function OtherGeneratorscombinedwiththetest()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestConductedby()
{
Tip('Enter the name of the company that tests the generator.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestReportsubmittedtoDEC()
{
Tip('Choose Yes or No. ', TITLE, 'EES HELP',WIDTH,'25')
}

function StackTestDaterequiredbythepermit()
{
Tip('Enter date (mm/dd/yyyy).', TITLE, 'EES HELP',WIDTH,'25')
}

function UnitcomplywithNOxRACTplan()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function UnitcomplywithPMRACTplan()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function TestPassedCompliance()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function StackTestDate()
{
Tip('Enter the most recent stack test date (mm/dd/yyyy).', TITLE, 'EES HELP',WIDTH,'25')
}

function NextStackTestDate()
{
Tip('Enter the next test date planned for the stack (mm/dd/yyyy).', TITLE, 'EES HELP',WIDTH,'25')
}


function FuelCapping()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function GasFuelCapping()
{
Tip('Enter the cap value for natural gas. ', TITLE, 'EES HELP',WIDTH,'25')
}

function OilFuelCapping()
{
Tip('Enter the cap value for oil. ', TITLE, 'EES HELP',WIDTH,'25')
}

function GasNOxEmissionfactor()
{
Tip('Enter the value (lb/million cubic ft.)', TITLE, 'EES HELP',WIDTH,'25')
}


function OilNOxEmissionfactor()
{
Tip('Enter the value (lb/1000 gal).', TITLE, 'EES HELP',WIDTH,'25')
}

function IstherefuelcappingorthehoursofoperationlimitunderDECforthisunit()
{
Tip('Check DEC permit to confirm the operation limitations if any', TITLE, 'EES HELP',WIDTH,'25')
}

function Comments()
{
Tip('Please enter all applicable  comments here.', TITLE, 'EES HELP',WIDTH,'25')
}
