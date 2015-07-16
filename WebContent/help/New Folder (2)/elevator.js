function ID()
{
Tip('This is a number assigned by the facility.  E.g. # 1.', TITLE, 'EES HELP',WIDTH,'25')
/*Tip('45645645', TITLE, 'EES HELP',WIDTH,'20');*/
}
function Type()
{
Tip('Choose between elevator or escalator.', TITLE, 'EES HELP',WIDTH,'25')
}
function HydraulicTank()
{ 
Tip('<b>1</b>.Choose the tank from the existing list that will be used to supply hydraulic fluid to the elevator.<br><b>2</b>.If the tank does not exist, it must be first created and then chosen.<br><b>3</b>.If the device is electric, no tank will be associated.', TITLE, 'EES HELP',WIDTH,'25')
}
function StatusofSource()
{
Tip('The operational condition of the elevator. E.g. In-service, closed, out-of-service, etc.', TITLE, 'EES HELP',WIDTH,'25')
}
function Comments()
{
Tip('Please enter all the comments here.', TITLE, 'EES HELP',WIDTH,'25')
}

