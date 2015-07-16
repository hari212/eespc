function ID()
{ 
Tip('This is a number assigned by the facility.  E.g., # 1 .', TITLE, 'EES HELP',WIDTH,'25')
}
function TypeOfUnit()
{
Tip('Select the type of the incinerator or crematories.', TITLE, 'EES HELP',WIDTH,'25')
}
function StackExhausting()
{
Tip('<b>1</b>.Choose the stack that is connected to this cremator/ incinerator from the existing list.<br><b>2</b>.If it is a different stack, then a stack must first be created.<br><b>3</b>The stack ID and specification can be found in the DEC permit.  E.g. EP00001.<br><b>4</b>If not, a facility-designated number can be used to first create the stack and then choose from the stack list.', TITLE, 'EES HELP',WIDTH,'25')
}
function Floor()
{
Tip('Enter the floor the cremator/ incinerator is located.  E.g. basement, 1, etc.', TITLE, 'EES HELP',WIDTH,'25')
}
function MakeModel()
{
Tip('Enter the series and/or the model number of the cremator/ incinerator.', TITLE, 'EES HELP',WIDTH,'25')
}
function Serial()
{
Tip('Enter the series or the same information as above.', TITLE, 'EES HELP',WIDTH,'25')
}
function YearInstalledhelp()
{
Tip('<b>1</b>.Enter the year of installation of the crematory/ incinerator in YYYY or it can be decoded from the DEP ID (in NYC settings).<br><b>2</b>. If outside of NYC, then check for other permit/ documentation ')
}
function BurnerCapacity()
{
Tip('Enter the maximum heat input capacity in million BTU/Hr. ', TITLE, 'EES HELP',WIDTH,'25')
}
function StatusOfSource()
{
Tip('The operational condition of the crematory/ incinerator. E.g. In-service, closed, out-of-service, etc.', TITLE, 'EES HELP',WIDTH,'25')
}
function BurnerMakeModel()
{
Tip('Enter the make and model number for the burner if different from the cremator/ incinerator.', TITLE, 'EES HELP',WIDTH,'25')
}
function TypeofWasteBurned()
{
Tip('Choose the type of waste the crematory/ incinerator is used for. E.g., medical waste, pets. etc.', TITLE, 'EES HELP',WIDTH,'25')
}
function Capacity()
{
Tip('Enter the capacity of the crematory/ incinerator (Lbs/hr).', TITLE, 'EES HELP',WIDTH,'25')
}
function IsthereaScrubberInstalled()
{
Tip('Choose Yes or No.');
}
function Location()
{
Tip('Choose the facility location.', TITLE, 'EES HELP',WIDTH,'25')
}
function DOBApprovalRequired()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function DOB()
{
Tip('Enter the Department of Buildings/village/city/town’s construction installation ID from the work permit.', TITLE, 'EES HELP',WIDTH,'25')
}
function IsCOmonitorinstalled()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function IsOpacitymonitorinstalled()
{ 
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function IsO2monitorinstalled()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function OpacityChartRecorderAvailable()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function QuarterlyCGArequired()
{
Tip('Choose Yes or No.', TITLE, 'EES HELP',WIDTH,'25')
}
function TemperatureRequired()
{
Tip('Does the permit require  temperature management of the unit choose Y or N.', TITLE, 'EES HELP',WIDTH,'25')
}
function MinimumTemperature()
{
Tip('Enter the minimum permitted operating temperature.', TITLE, 'EES HELP',WIDTH,'25')
}
function Primary()
{
Tip('Enter the permitted minimum primary temperature.', TITLE, 'EES HELP',WIDTH,'25')
}
function Secondary()
{
Tip('Enter the permitted minimum secondary temperature.', TITLE, 'EES HELP',WIDTH,'25')
}
function AverageFacilitytemperature()
{
Tip('The average operating temperature recorded at the facility.', TITLE, 'EES HELP',WIDTH,'25')
}
function Primaryx()
{
Tip('Enter the permitted average facility primary temperature. ', TITLE, 'EES HELP',WIDTH,'25')
}
function Secondaryx()
{
Tip('Enter the permitted average facility Secondary temperature.', TITLE, 'EES HELP',WIDTH,'25')
}
function DEPRequired()
{
Tip('Is a DEP permit or local agency permit required, choose Y or N.', TITLE, 'EES HELP',WIDTH,'25')
}
function DEP()
{
Tip('<b>1</b>.Enter the NYCDEP permit number.  It can be obtained from the work permit or from the Certificate to Operate (C .O).<br><b>2<b>.If you are outside the NYC area, then enter the local environmental agency permit number.  For e.g. Westchester County Department of Health', TITLE, 'EES HELP',WIDTH,'25')
}
function DEC()
{
Tip('Enter the DEC permit number.It can be obtained from the  DEC permit.', TITLE, 'EES HELP',WIDTH,'25')
}

function Stacktestrequired()
{
Tip('Check permit for stack test required.', TITLE, 'EES HELP',WIDTH,'25')
}
function DECpermitsobtained()
{
Tip('Was a state permit obtained choose Y or N. ', TITLE, 'EES HELP',WIDTH,'25')
}

function WasastacktestprotocolsubmittedtoDEC()
{
Tip('If a stack test is required then enter whether a test protocol submitted or not. ', TITLE, 'EES HELP',WIDTH,'25')
}
function Testconductedbywhatfirm()
{
Tip('If a test conducted by the firm, enter thr name of the firm.', TITLE, 'EES HELP',WIDTH,'25')
}

function Parameters()
{
Tip('Choose the pollutant to be tested(ass stated in the permit).', TITLE, 'EES HELP',WIDTH,'25')
}

function WasatestreportsubmittedtoDEC()
{
Tip('If a test was conducted, choose if the report was submitted to the agency.', TITLE, 'EES HELP',WIDTH,'25')
}

function StacktestdaterequiredbythePermit()
{
Tip('Enter the stack test deadline date.', TITLE, 'EES HELP',WIDTH,'25')
}

function Wasaretestplanned()
{
Tip('If the stack failed, was a retest planned.', TITLE, 'EES HELP',WIDTH,'25')
}

function TestPassedCompliance()
{
Tip('Choose the test passed compliance Y or N.', TITLE, 'EES HELP',WIDTH,'25')
}

function StackTestDate()
{
Tip('Enter the stack test date.', TITLE, 'EES HELP',WIDTH,'25')
}

function Whenisthenextstacktest()
{
Tip('Enter the next stack test date, usually once in 5 years.', TITLE, 'EES HELP',WIDTH,'25')
}
function IsthereaWasteQuantityCap()
{
Tip('Choose if the permit has a waste quantity cap.', TITLE, 'EES HELP',WIDTH,'25')
}
function IfYesWasteLimit()
{
Tip('Enter the limit or cap from the permit.', TITLE, 'EES HELP',WIDTH,'25')
}

function SolidWastePermitRequiredbyDEC()
{
Tip('Choose Y or N.', TITLE, 'EES HELP',WIDTH,'25')
}
function IssueDate()
{
Tip('Enter the issue or effective date of the solid waste permit.', TITLE, 'EES HELP',WIDTH,'25')
}

function ExpirationDate()
{
Tip('Enter the expiration date of the solid waste permit, once in 5 years.', TITLE, 'EES HELP',WIDTH,'25')
}
function Comments()
{
Tip('Please enter all applicable comments here.', TITLE, 'EES HELP',WIDTH,'25')
}




