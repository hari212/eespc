function ID()
{
Tip('Enter facility designated id for this elevator. E.g. # 1.', TITLE, 'EES HELP',WIDTH,'25')
}
function Type()
{
Tip('Choose if this unit is an elevator or an escalator.', TITLE, 'EES HELP',WIDTH,'25')
}
function HydraulicTank()
{ 
Tip('<b>1</b>.Choose the tank from the existing list that will be used to supply hydraulic fluid for the elevator mechanics.<br><b>2</b>. If the tank does not exist, it must be first created and then chosen.<br><b>3</b>. If the device is electric, no tank will be associated.', TITLE, 'EES HELP',WIDTH,'25')
}
function StatusofSource()
{
Tip('Select the operational status of the elevator. E.g. In-service, closed, out-of-service, etc.', TITLE, 'EES HELP',WIDTH,'25')
}
function Comments()
{
Tip('Enter comments, if any.', TITLE, 'EES HELP',WIDTH,'25')
}

