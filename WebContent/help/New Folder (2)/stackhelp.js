



 function ID()
{
Tip('<b>1</b>.Enter ID as per the facility identification.<br><b>2</b>. If not available, start with numerical number (E.g. # 001).', TITLE, 'EES HELP',WIDTH,'25')
}

 function Installed()
{
Tip('Enter the year installed. This is the same as the year of source (boiler, generator, etc.)', TITLE, 'EES HELP',WIDTH,'25')
}

 function Floor()
{ 
Tip('Enter the location of the Stack E.g.Roof,Basement.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Height()
{
Tip('<b>1</b>.Enter the height in feet. For boilers, this will be available in the permit.<br><b>2</b>.If not estimate the height of the  building(No of floors (12ft floor) and add about 10ft).<br><b>3</b>.For generator stacks will be very short(15ft from ground).', TITLE, 'EES HELP',WIDTH,'25')
}

 function Diameter()
{
Tip('Enter the diameter (For boilers, it will be available in the DEC permit. If not estimate about 12 inches). For generator about 6 inches.', TITLE, 'EES HELP',WIDTH,'25')
}

 function NoofSourcesConnectedtothisStack()
{
Tip('If it is a Common Stack, many sources may be connected to this. Usually same type (boilers). All the generators and ETO sterilizers will have dedicated stack.', TITLE, 'EES HELP',WIDTH,'25')
}

 function TotalCapacityoftheSource()
{
Tip('<b>1<b>.This is the capacity of the source such as boilers or generators.<br><b>2</b>.This can be obtained from the source data.', TITLE, 'EES HELP',WIDTH,'25')
}


 function TypeofFuel()
{
Tip('The contents of the tank. E.g. Natural gas, No. 2 oil, etc', TITLE, 'EES HELP',WIDTH,'25')
}

 function FlowRate()
{
Tip('Automatic calculation', TITLE, 'EES HELP',WIDTH,'25')
}

 function ExhaustTemperature()
{
Tip('For boilers enter\'400F\', generator enter \'900F\',  For ETO enter \'140F\'', TITLE, 'EES HELP',WIDTH,'25')
}

 function Velocity()
{
Tip('Automatic calculation', TITLE, 'EES HELP',WIDTH,'25')
}

 function DECEmissionsPointID()
{
Tip('<b>1</b>.This can be obtained from the DEC permit.<br><b>2</b>. This is also an input in facility level ', TITLE, 'EES HELP',WIDTH,'25')
}


