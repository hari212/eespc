

 function ID()
{
Tip('<b>1</b>.Enter facility designated ID number for this stack.<br><b>2</b>. If not available, use a numbering ID system.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Installed()
{
Tip('Enter the year in which this stack was installed.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Floor()
{ 
Tip('Enter the location of the stack, e.g. Roof, Basement.', TITLE, 'EES HELP',WIDTH,'25')
}

 function Height()
{
Tip('<b>1</b>.Enter the height of stack in feet. For boiler stacks, the height information may be available in the permit.<br><b>2</b>.  This must be verified.  If not, an estimate of the height of the  building (No of floors (12ft floor each) and add about 10 ft. to the total height of the building).<br><b>3</b>.  The generator stacks are typically shorter than boiler stacks (about 15ft from ground).', TITLE, 'EES HELP',WIDTH,'25')
}

 function Diameter()
{
Tip('Enter the diameter of the stack in inches.  Boiler stacks diameter information may  be available in the DEC permit. If not, estimate about 12-18 inches.  Generator stacks are  about 6 inches.', TITLE, 'EES HELP',WIDTH,'25')
}

 function NoofSourcesConnectedtothisStack()
{
Tip('Enter the total number of sources connected to this stack.  If it is a common stack serving many sources that may be connected to it, usually same type of sources (such as 3 boilers emitting via a common stack). Generators and EtO sterilizers are typically expected to have dedicated stacks.', TITLE, 'EES HELP',WIDTH,'25')
}

 function TotalCapacityoftheSource()
{
Tip('This is the capacity of the source such as boilers or generators. This can be obtained from the source data.', TITLE, 'EES HELP',WIDTH,'25')
}


 function TypeofFuel()
{
Tip('Enter the type of fuel that is burnt in the source(s) connected to this stack. E.g. Natural gas, No. 2 oil, etc', TITLE, 'EES HELP',WIDTH,'25')
}

 function FlowRate()
{
Tip('Automatically generated.', TITLE, 'EES HELP',WIDTH,'25')
}

 function ExhaustTemperature()
{
Tip('For boilers enter\'450F\', for generators, enter \'900F\',  for EtO sterilizers, enter \'140F\'', TITLE, 'EES HELP',WIDTH,'25')
}

 function Velocity()
{
Tip('Automatic calculation', TITLE, 'EES HELP',WIDTH,'25')
}

 function DECEmissionsPointID()
{
Tip('<b>1</b>.This can be obtained from the DEC permit.<br><b>2</b>.  This is also an input in the facility level ', TITLE, 'EES HELP',WIDTH,'25')
}
function MethodTest()
{
Tip('As per the DEC requirement, whether the annual method 9 test has been done on this stack?', TITLE, 'EES HELP',WIDTH,'25')
}

