function ID()
{
Tip('Enter the facility designated ID  of this generator.  E.g. # 1.', TITLE, 'EES HELP',WIDTH,'25')
}


 function Floor()
{ 
Tip('Enter the floor number-name where this generator is located.  E.g., basement.', TITLE, 'EES HELP',WIDTH,'25')
}

function PermitID()
{
Tip('Enter the ID designated by the NYSDEC.  It can be found in the DEC permit.  E.g., ES00001.  If not, a facility designated number-ID  may be assigned.', TITLE, 'EES HELP',WIDTH,'25')
}

function PrimaryUse()
{
Tip('Choose the primary use of this generator-either Emergency power or Peak Load Management.', TITLE, 'EES HELP',WIDTH,'25')
}

function YearInstalledhelp()
{
Tip('Enter the year of installation of the generator in YYYY.  It may be decoded from the DEP permit ID or local agency permit id.', TITLE, 'EES HELP',WIDTH,'25')
}

function StatusOfSource()
{
Tip('Select the operational status of the generator. E.g. In-service, closed, out-of-service, etc', TITLE, 'EES HELP',WIDTH,'25')
}


function Manufacturer()
{
Tip('Enter the manufacturer of the unit.  E.g. Caterpillar, Cummins, etc.', TITLE, 'EES HELP',WIDTH,'25')
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
Tip('Enter the generator maximum energy generation capacity in kilowatt-hours-KWH.', TITLE, 'EES HELP',WIDTH,'25')
}
function Fuel()
{
Tip('Choose the fuel type, typically diesel oil, #2 oil', TITLE, 'EES HELP',WIDTH,'25')
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
Tip('<b>1</b>.Choose the tank from the existing list that will be used to supply fuel to the Generator-if it burns oil.<br><b>2</b>.  Click Search button to view the list of tanks.  If the associated tank does not exist in the list, it must be first created in the Building Section and then chosen.', TITLE, 'EES HELP',WIDTH,'25')
}

function HasDayTank()
{
Tip('<b>1</b>.Indicate whether the generator utilizes a day-tank <br><b>2</b>. If yes, select the tank in the next box using the search button.<br><b>3</b>. If the tank is not in the list, it must be first created or added-the same way a storage tank is added,  and then visit this page to add the appropriate tank.', TITLE, 'EES HELP',WIDTH,'25')
}

function DOB()
{
Tip('Enter the Department of Buildings-village-city-town construction installation ID from the work permit.', TITLE, 'EES HELP',WIDTH,'25')
}

function MEA()
{
Tip('Enter the Materials and Equipment Acceptance number-available on equipment plate.', TITLE, 'EES HELP',WIDTH,'25')
}

function DEP()
{
Tip('<b>1</b>.Enter the NYCDEP permit number.  It can be obtained from the work permit or from the registration.<br><b>2</b>.  If the facility-unit is outside the NYC, then enter the local environmental agency permit number.', TITLE, 'EES HELP',WIDTH,'25')
}


function ScheduleC()
{
Tip('Choose if a Schedule C for this generator is required or not.  It is a DoB requirement.', TITLE, 'EES HELP',WIDTH,'25')
}

function PlanApproval()
{
Tip('Choose if a plan-drawing approval from the DOB was obtained on this unit installation.', TITLE, 'EES HELP',WIDTH,'25')
}

 function StackExhausting()
{
Tip('<b>1</b>.Choose the stack from which this generator exhausts flue gases to the atmosphere.<br><b>2</b>. Click the Search button to view and select the appropriate stack.  If the stack does not exist in the list, first create it under the building section and then return to this page for making the selection.', TITLE, 'EES HELP',WIDTH,'25')
}

function Isrecordsinpermanentboundbook()
{
Tip('Select Yes or No, if the facility maintains a bound logbook where the generator operation details are logged.  The details should include date, start time, end time, number of gallons used, smoke density, weather conditon and signature of the watch engineer.', TITLE, 'EES HELP',WIDTH,'25')
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
Tip('Choose Yes or No.  If the generator is only for emergency purposes, a stack test may not be required.  However, check the permit and make a selection.', TITLE, 'EES HELP',WIDTH,'25')
}

function OtherGeneratorscombinedwiththetest()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestConductedby()
{
Tip('Enter the name of the company that tests are perofmed to the generator.  If information is unavailable, leave blank.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestReportsubmittedtoDEC()
{
Tip('Choose Yes or No.  Select No if not applicable. ', TITLE, 'EES HELP',WIDTH,'25')
}

function StackTestDaterequiredbythepermit()
{
Tip('Enter date (mm/dd/yyyy).  Leave blank, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}

function UnitcomplywithNOxRACTplan()
{
Tip('Choose Yes or No.  Select No, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}

function UnitcomplywithPMRACTplan()
{
Tip('Choose Yes or No.  Select No, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}
function TestPassedCompliance()
{
Tip('Choose Yes or No.  Select No, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}

function StackTestDate()
{
Tip('Enter the most recent stack test date (mm/dd/yyyy).  Leave blank, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}

function NextStackTestDate()
{
Tip('Enter the next test date planned for the stack (mm/dd/yyyy).  Leave blank, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}


function FuelCapping()
{
Tip('Choose Yes or No.  Typically, there will be a capping only if the generator is participating-permitted in a peak-load shaving-demand response program.  Select No, if not applicable.', TITLE, 'EES HELP',WIDTH,'25')
}

function GasFuelCapping()
{
Tip('Enter the permit cap value for natural gas, if applicable. ', TITLE, 'EES HELP',WIDTH,'25')
}

function OilFuelCapping()
{
Tip('Enter the permit cap value for oil, if applicable. ', TITLE, 'EES HELP',WIDTH,'25')
}

function GasNOxEmissionfactor()
{
Tip('Enter the value-lb/million cubic ft.', TITLE, 'EES HELP',WIDTH,'25')
}


function OilNOxEmissionfactor()
{
Tip('Enter the value-lb/1000 gal.', TITLE, 'EES HELP',WIDTH,'25')
}

function IstherefuelcappingorthehoursofoperationlimitunderDECforthisunit()
{
Tip('Check DEC permit to confirm the operation limitations, if any.  Typically, there is a 500 maximum hours per year limitation as emergency use only.  Fuel capping is not the criteria - if that is the case, select No.', TITLE, 'EES HELP',WIDTH,'25')
}

function Comments()
{
Tip('Enter comments, if any.', TITLE, 'EES HELP',WIDTH,'25')
}

function NaturalGasConsumption()
{
Tip('Enter the natural gas consumption, if applicable, in cubic feet under each month box as indicated.  Upon entering the number for all applicable months, click the radio button under the latest month, then click validate button.  This will give the latest 12-month rolling figure and the consumption for the current year so far.', TITLE, 'EES HELP',WIDTH,'25' )
}

function FuelOilConsumption()
{
Tip('Enter the fuel oil consumption, if applicable, in gallons under each month box as indicated.  Upon entering the number for all applicable months, click the radio button under the latest month, then click validate button.  This will give the latest 12-month rolling figure and the consumption for the current year so far.  Exhibit 24 provides complete 12-month rolling history on fuel consumed.  ', TITLE, 'EES HELP',WIDTH,'25' )
}
function SO2EmissionfactorOil()
{
Tip('Enter the SO2 emission factor in lbs/1000 gal.  This could be a stack test value or EPA  AP-42 factor.  This is applicable only if the unit participates in a PLM-CDRP program.  If not, leave blank.    ', TITLE, 'EES HELP',WIDTH,'25' )
}

function SO2Allowable()
{
Tip('Enter the maximum SO2 emissions allowed in tons per year for this generator only.  This is applicable only if the unit participates in a PLM-CDRP program.  If not, leave blank.', TITLE, 'EES HELP',WIDTH,'25' )
}
function NOXAllowable()
{
Tip('Enter the maximum NOx emissions allowed in tons per year for this generator only.  This is applicable only if the unit participates in a PLM/CDRP program.  If not, leave blank.', TITLE, 'EES HELP',WIDTH,'25' )
}
function so2Emissionfactorgas()
{
Tip('Enter the NOx emisions factor in lb/million cubic feet.  This is applicable only if the unit participates in a PLM/CDRP program.  If not, leave blank.', TITLE, 'EES HELP',WIDTH,'25' )
}
function DOBsignoff()
{
Tip('Choose Yes or NO, if a job signoff from the Department of Buildings was obtained.  If DoB does not have ruling in your area on Generator, choose No. ', TITLE, 'EES HELP',WIDTH,'25' )
}
 function FireDeptcertificateofapproval()
{
Tip('The permit number found on the approval certificate from the village/city/town Fire Department.', TITLE, 'EES HELP',WIDTH,'25')
}
function MonthlyConsumption()
{
Tip('Select the year and then enter the fuel consumed, oil or gas as indicated per month. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function TwelveMonthRollingStatus()
{
Tip(' This is an automatically generated value based on monthly fuel entered. It is the latest 12-month rolling value. ', TITLE, 'EES HELP',WIDTH,'25' )
}


function MonthlyEngineRunningHrs()
{
Tip('Select the year and then enter the monthly engine running hours. ', TITLE, 'EES HELP',WIDTH,'25' )
}


function MonthlyPressureTestOil()
{
Tip('Please enter the monthly pressure test value for Oil. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function MonthlyPressureTestGas()
{
Tip('Please enter the monthly pressure test value for Gas. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function EngineRunningHrsHelp()
{
Tip('Enter the Engine Running Hours, Select the Year and enter the number of hours for all applicable months, click the radio button under the latest month, then click validate button.  This will give the total hours for the current year. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function PressureTestOilHelp()
{
Tip('Enter the Pressure Test Value for Oil, Select the Year and enter the values for all applicable months, click the radio button under the latest month, then click validate button.  This will give the total Value of the Oil for the current year. ', TITLE, 'EES HELP',WIDTH,'25' )
}


function PressureTestGasHelp()
{
Tip('Enter the Pressure Test Value for Gas, Select the Year and enter the values for all applicable months, click the radio button under the latest month, then click validate button.  This will give the total Value of the Gas for the current year. ', TITLE, 'EES HELP',WIDTH,'25' )
}


function Lock()
{
Tip(' When checked/locked, the monthly fuel consumption values entered cannot be changed. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function LockHelp()
{
Tip(' When checked/locked, the monthly values entered cannot be changed. ', TITLE, 'EES HELP',WIDTH,'25' )
}