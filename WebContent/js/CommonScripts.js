// JavaScript Document

function showHide(str)
{ 
      loc = document.forms[0].psLocation;
      if(loc.selectedIndex > 0) 
      {
            showTable(str);
      } 
      else	
            hideTable(str);
      return true;
}

function getLocation()
{
 loc1 = document.forms[0].psLocation;
 idx1 = loc1.selectedIndex;
 loca1 = loc1.options[idx1].text;
 return loca1;
}

function hideTable (tab) { 
   a = document.getElementById(tab);
   a.style.display = "none"; 
} 

function showTable(tab)
{ 
   a = document.getElementById(tab);
   a.style.display = "inline"; 
}

function setmethod(fun)
{ 
   a = document.forms[0].methodToCall;
   a.value = fun; 
}

function setFacility(fun)
{ 
   a = document.forms[0].lfFacility;
   a.value = fun; 
}

function locRequired()
{ 
      loc = document.forms[0].psLocation;
      idx = loc.selectedIndex;
      if(loc.options[idx].text == "WCDOH" || loc.options[idx].text == "RCDOH" ) 
      {
            showTable(str);
      } 
      else	
            hideTable(str);
      return true;
}
