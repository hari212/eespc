function ID()
{
Tip('Enter facility designated boiler id.  E.g., 1.', TITLE, 'EES HELP',WIDTH,'25' )
}

function StackExhausting()
{
Tip('<b>1</b>.Choose the stack that is connected to this boiler from the existing list.<br><b>2<b>.  If the associated stack does not exist, then a stack must first be created.<br>  The stack ID and specification may be found in the DEC permit.  E.g., EP00001.  If not, a facility designated number can be used to first create the stack and then return to this page for completing the boiler creation process.' , TITLE, 'EES HELP',WIDTH,'25')
}

function Floor()
{ 
Tip('Enter the floor in which boiler plant is located.  E.g., basement.', TITLE, 'EES HELP',WIDTH,'25' )
}

function DECSourceID()
{
Tip('<b>1</b>.Enter the ID designated by the NYSDEC for this boiler.  It can be found in the DEC-state-permit.<br><b>2</b>.E.g. ES00001.  If not, a facility designated number can be assigned.', TITLE, 'EES HELP',WIDTH,'25' )
}

function PrimaryUse()
{
Tip('Choose the primary use of the boiler from the drop-down menu.  E.g., heat, hot-water, etc.', TITLE, 'EES HELP',WIDTH,'25')
}

function YearInstalled()
{
Tip('Enter the year of installation of the boiler in YYYY format.', TITLE, 'EES HELP',WIDTH,'25')
}

function Manufacturer()
{
Tip('Enter the manufacturing company of the boiler.  E.g., Babcock & Wilcox.', TITLE, 'EES HELP',WIDTH,'25')
}

function Make()
{
Tip('Enter the series name, if any, or simply enter the manufacturer name.', TITLE, 'EES HELP',WIDTH,'25')
}

function Model()
{
Tip('Enter the model of the boiler. This should be available on the boiler name plate', TITLE, 'EES HELP',WIDTH,'25')
}

function Serial()
{
Tip('Enter the serial number of the boiler. Ask the facility or data from the boiler name plate', TITLE, 'EES HELP',WIDTH,'25')
}

function BurnerMake()
{
Tip('Enter the Burner make name of the boiler, if it is separate from the boiler make-model.  If it is a package boiler then enter as package boiler.  This  information can be obtained form the boiler name plate.', TITLE, 'EES HELP',WIDTH,'25')
}

function BurnerModel()
{
Tip('Enter the burner model.  If it is a package boiler then enter as package boiler.   Ask the facility or data from the boiler name plate', TITLE, 'EES HELP',WIDTH,'25')
}

function Capacity()
{
Tip('Enter the boiler maximum heat input capacity in million Btu per hr.  The information should be available on the boiler name plate.', TITLE, 'EES HELP',WIDTH,'25')
}

function BurnerType()
{
Tip('Choose if the burner is a single fuel firing type or dual.  Information should be available on the burner name plate', TITLE, 'EES HELP',WIDTH,'25')
}

function PrimaryFuel()
{
Tip('Choose the primary fuel-mostly used-from the drop-down menu.', TITLE, 'EES HELP',WIDTH,'25')
}

function SecondaryFuel()
{
Tip('Choose the back-up fuel, if applicable.', TITLE, 'EES HELP',WIDTH,'25')
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
Tip('<b>1</b>.Choose the tank from the existing list by clicking the search buttion that will be used to supply fuel to the boiler, if it burns oil.<br><b>2</b>.  If the tank does not exist, it must be first created under the appropriate building section and then return to this page to complete the boiler creation.', TITLE, 'EES HELP',WIDTH,'25')
}

function DOBPERMIT()
{
Tip('Enter the Department of Buildings-village-city-town construction installation id from the work permit.', TITLE, 'EES HELP',WIDTH,'25')
}

function MEA()
{
Tip('Enter the material equipment acceptance number-typically available on equipment plate.', TITLE, 'EES HELP',WIDTH,'25')
}

function DEP()
{
Tip('<b>1</b>.Enter the NYCDEP or local envionmental protection agency permit number.   It can be obtained from the work permit or from the Certificate to Operate - C.O.<br><b>2<b>.  If the facility is outside the NYC, then enter the local environmental agency permit number.  For e.g., Westchester County Department of Health.', TITLE, 'EES HELP',WIDTH,'25')
}

function ScheduleC()
{
Tip('Choose if a Schedule C for this boiler is required or not.  This is a department of buildings requirement.', TITLE, 'EES HELP',WIDTH,'25')
}

function PlanApproval()
{
Tip('Choose if a plan approval from the DOB was obtained.', TITLE, 'EES HELP',WIDTH,'25')
}

function StackTestrequiredbyStateAgency()
{
Tip('Choose if a stack emission test is required for this boiler.  Refer to the DEC permit', TITLE, 'EES HELP',WIDTH,'25')
}

function OtherBoilersCombinedwiththetest()
{
Tip('Choose if there are other boilers to be tested as well at the same time or with in the same permit term.', TITLE, 'EES HELP',WIDTH,'25')
}

function Parameters()
{
Tip('Choose what pollutants are to be tested during the stack test.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestOnFuel()
{
Tip('<b>1</b>.Choose on what fuels is the stack test required.<br><b>2</b>.Some times a particulate matter-PM-test may not be required on gas firing.', TITLE, 'EES HELP',WIDTH,'25')
}

function StacktestprotocolsubmittedtoStateAgency()
{
Tip('<b>1</b>.Choose if a protocol was submitted to the state agency before the test.<br><b>2</b>It may take about 3 months for the agency to approve the protocol.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestConductedby()
{
Tip('Test conducted by which company.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestReportsubmittedtoStateAgency()
{
Tip('Choose if the report was submitted to the agency within 60-days of testing.', TITLE, 'EES HELP',WIDTH,'25')
}
function StackTestDaterequiredbythepermit()
{
Tip('<b>1</b>.Enter the permit expiration date of the Title V permit.<br><b>2</b>. State facility permit does not have an expiration date. However, if there is a deadline on the stack testing, it must be entered.<b>3</b>.For example, within 180 days of the issuance of the permit', TITLE, 'EES HELP',WIDTH,'25')
}
function TestPassedCompliance()
{
Tip('Choose if the boiler passed the State pollutant limits.', TITLE, 'EES HELP',WIDTH,'25')
}
function ReTestPlanned()
{
Tip('If the boiler failed the  test or for some reason the test was postponed, choose Y or N.', TITLE, 'EES HELP',WIDTH,'25')
}
function StackTestDate()
{
Tip('Enter the date of stack test in mm/dd/yyyy.', TITLE, 'EES HELP',WIDTH,'25')
}

function NextStackTestDate()
{
Tip('Enter the next test date.  Typically, the test is required once in every 5 years.', TITLE, 'EES HELP',WIDTH,'25')
}
function IstheboilersubjecttoNSPS()
{
Tip('<b>1</b>.Choose if the EPAs New Source Performance Standards are applicable to the boiler.<br><b>2</b>. It is applicable if the boiler capacity is  greater than 10 MMBTU per HR.<br><b>3</b>  Applicable  only since 1989 installations or modifications', TITLE, 'EES HELP',WIDTH,'25')
}
function Wasboilerorburnermodifiedinthepast()
{
Tip('Choose if there were any major modifications on the boiler or burner and also enter the date.', TITLE, 'EES HELP',WIDTH,'25')
}
function PermitforconstructormodifysubmitedtoDECDEPDOB()
{
Tip('Choose if a work permit was obtained to proceed with the change or modification.', TITLE, 'EES HELP',WIDTH,'25')
}
function Wasthereanyemissioncreditrequiredaspartofthe6NYCRRPart231()
{
Tip('<b>1</b>Were there any decreases in emissions due to the upgrade or modification.<br><b>2</b>If yes, emissions credit can be claimed from the agency.', TITLE, 'EES HELP',WIDTH,'25')
}
function WastheboilersubjecttoFederalPSD40CFRPart52()
{
Tip('<b>1</b>.Choose if PSD is applicable to this boiler.<br><b>2</b>.If the incremental emissions-NOx-are in excess of 25 TPY, it is applicable.<br><b>3</b>. Also, NSR and Part 231 analysis will have to be performed.', TITLE, 'EES HELP',WIDTH,'25')
}
function FuelCapping()
{
Tip('<b>1</b>.Check from the permit or permit application the maximum amount of fuel that is allowed to burn during any 12 month period.<br><b>2</b>.Enter values for both gas and oil as applicable.', TITLE, 'EES HELP',WIDTH,'25')
}
function IfFuelCappingYesGasFuelCapping()
{
Tip('Amount of gas allowed. Enter values for Gas in CFY.', TITLE, 'EES HELP',WIDTH,'25')
}
function IfFuelCappingYesOilFuelCapping()
{
Tip('Amount of oil allowed. Enter values for oil in GPY.', TITLE, 'EES HELP',WIDTH,'25')
}
function GasNOxEmmisionfactor()
{
Tip('Enter 140 or 100 depending upon the boiler size.', TITLE, 'EES HELP',WIDTH,'25')
}
function OilNOxEmmisionfactor()
{
Tip('Enter 55 for No.6, 38 for N0.4, 20 for No.2 depending upon the boiler size.', TITLE, 'EES HELP',WIDTH,'25')
}
function SO2EmissionFactorOil()
{
Tip('Enter the SO2 emission factor in lbs/1000 gal', TITLE, 'EES HELP',WIDTH,'25')
}
function Gas()
{
Tip('Enter the SO2 emission factor in  lb/million cu.ft', TITLE, 'EES HELP',WIDTH,'25')
}
function Istherollingaverage12monthsconsecutivetotalbelowlimit()
{
Tip('Check the permit if a 12 month rolling is required, if yes, enter yes.', TITLE, 'EES HELP',WIDTH,'25')
}
function Isanopacitymonitorinstalledasper40CFRPart60()
{
Tip('A continuous opacity monitor will be required if the  capacity of the boiler is  in excess of 30 MMBtu per hr, firing oil.', TITLE, 'EES HELP',WIDTH,'25')
}
function WasperformancetestprotocolsubmittedtoDEC()
{
Tip('Choose if a protocol for the opacity monitor was submitted prior to installation.', TITLE, 'EES HELP',WIDTH,'25')
}
function WasperformancetestreportsubmittedtoDEC()
{
Tip('Choose if a performance test report was submitted for the opacity monitor.', TITLE, 'EES HELP',WIDTH,'25')
}
function IstheSulfurcontentofthefuelsampledanalyzedandrecordskeptforeachofthefueldelivery()
{
Tip('Choose if a sulfur certificate is maintained per delivery of any fuel oil and the sulfur content in percent by weight.', TITLE, 'EES HELP',WIDTH,'25')
}
function Isthereanallowablefuellimitfortheboiler()
{
Tip('Choose if the permit has fuel capping value.', TITLE, 'EES HELP',WIDTH,'25')
}
function IsthereaNitrogencontentofthefueldatarequirementforthisboiler()
{
Tip('Choose if the permit has a limit on fuel oil nitrogen content and the value in percent by weight.', TITLE, 'EES HELP',WIDTH,'25')
}
function StatusofSource()
{
Tip('Choose from the drop down menu.', TITLE, 'EES HELP',WIDTH,'25')
}
function Comments()
{
Tip('If any.', TITLE, 'EES HELP',WIDTH,'25')
}
function FootNote()
{
Tip('If any.', TITLE, 'EES HELP',WIDTH,'25')
}
function DEPPermitInformation()
{
Tip('Enter the initial issue date and expiration date through every triennial permit-renewal term.', TITLE, 'EES HELP',WIDTH,'25')
}
function RenewalSubmittalDate()
{
Tip('Enter the date of request for inspection with DEP/DOH.', TITLE, 'EES HELP',WIDTH,'25')
}
function LastInspectionYear()
{
Tip('<b>1</b>.Enter the inspection date by DEP/DOH which typically performed every 3 years.<br> <b>2</b> Similarly, enter the next anticipated inspection date and permit issue date.', TITLE, 'EES HELP',WIDTH,'25')
}
function AnnualTuneupInformation()
{
Tip('Enter the date on which the annual inspection is performed for each year.', TITLE, 'EES HELP',WIDTH,'25')
}
function NaturalGasConsumption()
{
Tip('Enter the natural gas consumption, if applicable, in cubic feet under each month box as indicated.  Upon entering the number for all applicable months, click the radio button under the latest month, then click validate button.  This will give the latest 12-month rolling figure and the consumption for the current year so far.', TITLE, 'EES HELP',WIDTH,'25' )
}

function FuelOilConsumption()
{
Tip('Enter the fuel oil consumption, if applicable, in gallons under each month box as indicated.  Upon entering the number for all applicable months, click the radio button under the latest month, then click validate button.  This will give the latest 12-month rolling figure and the consumption for the current year so far. ', TITLE, 'EES HELP',WIDTH,'25' )
} 

function doboff()
{
Tip('Choose Yes or NO, if a job signoff from the Department of Buildings was obtained. If DOB does not have ruling in your area on this Source, choose NO.', TITLE, 'EES HELP',WIDTH,'25' )
}

 function FireDeptcertificateofapproval()
{
Tip('The permit number found on the approval certificate from the village/city/town Fire Department.', TITLE, 'EES HELP',WIDTH,'25')
}
function SO2Allowable()
{
Tip('Enter the maximum So2 allowed in tons per year  ', TITLE, 'EES HELP',WIDTH,'25' )
}

function NOXAllowable()
{
Tip('Enter the NOx allowed in tons per year  ', TITLE, 'EES HELP',WIDTH,'25' )
}


function MonthlyConsumption()
{
Tip('Select the year and then enter the fuel consumed-oil or gas as indicated per month. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function TwelveMonthRollingStatus()
{
Tip(' This is automatically generated value based on monthly fuel entered. It is the latest 12-month rolling value. ', TITLE, 'EES HELP',WIDTH,'25' )
}

function Lock()
{
Tip(' When checked-locked, the monthly values entered cannot be changed. ', TITLE, 'EES HELP',WIDTH,'25' )
}
