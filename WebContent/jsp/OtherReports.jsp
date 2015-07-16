<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Untitled Document</title>
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script>
  var req;
  var which;

  function retrieveURL(url) {
   document.getElementById("theTable").innerHTML = "<DIV ALIGN=CENTER>Please wait....</DIV>";
   document.getElementById("theExhibit").innerHTML = "<DIV ALIGN=CENTER>Please select the Client.</DIV>";
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;
        req.open("GET", url, true);
        req.send();
      }
    }
  }

  function processStateChange() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
        document.getElementById("theTable").innerHTML = req.responseText;
      } else {
        alert("Problem: " + req.statusText);
      }
    }
  }

function fetch(event)
{
	if (doSomething(event) ){
		goGetClientDetails();
	}
}
function goGetClientDetails(){
	if (document.dynamicSelect.clName.value.length > 0){
		var url = 'GetClientData.do?'+'&clName='+document.dynamicSelect.clName.value;
		retrieveURL(url);
	}
}
function search()
{
	goGetClientDetails();
}

function doSomething(e)
{
	var key;
	var keychar;
	
	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);
	keychar = keychar.toLowerCase();
	
	
	// control keys
	if ((key==null) || (key==0) || (key==8) || 
		(key==9) || (key==13) || (key==27) )
	   return true;
	
	// alphas and numbers
	else if ((("abcdefghijklmnopqrstuvwxyz0123456789").indexOf(keychar) > -1))
	   return true;
	else
	   return false;
}

<!-- For the EXHIBIT -->
function fetchExhibit(id)
{
	var url = 'GetExhibitData.do?id='+id;
	retrieveExhibit(url);
}

  function retrieveExhibit(url) {

   document.getElementById("theExhibit").innerHTML = "<DIV ALIGN=CENTER>Please wait....</DIV>";
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processExhibit;
      try {
        req.open("GET", url, true);
      } catch (e) {
        alert(e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processExhibit;
        req.open("GET", url, true);
        req.send();
      }
    }
  }
  function processExhibit() {
    if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response
		createTable(req.responseXML);
      } else {
        alert("Problem: " + req.statusText);
      } 
    }
  }

function createTable(xmlDoc)
{

	
	
	
	
	var x = xmlDoc.getElementsByTagName('exhibits');
	var newEl = document.createElement('TABLE');
	newEl.setAttribute('BORDER',0);	
	newEl.setAttribute('CELLPADDING',1);
	newEl.setAttribute('CELLSPACING',1);
	newEl.setAttribute('BGCOLOR','#006699');	
	newEl.setAttribute('WIDTH','100%');		
	var row = document.createElement('TR');
	var container = document.createElement('TD');
	var innerTable = document.createElement('TABLE');
	innerTable.setAttribute('BORDER',0);	
	innerTable.setAttribute('CELLPADDING',1);
	innerTable.setAttribute('CELLSPACING',1);
	innerTable.setAttribute('BGCOLOR','#006699');	
	innerTable.setAttribute('WIDTH','100%');		
	container.appendChild(innerTable);
	row.appendChild(container);	
	newEl.appendChild(row);
	var tmp = document.createElement('TBODY');
	newEl.appendChild(tmp);

	for (i=0;i<x.length;i++)
	{
		for (j=0;j<x[i].childNodes.length;j++)
		{
			var row = document.createElement('TR');		
			if (j%2==0){
				row.setAttribute('bgcolor','#EEEEEE');
				row.setAttribute('onMouseOver',"this.bgColor='#CCCCFF'");
				row.setAttribute('onMouseOut', "this.bgColor='#EEEEEE'");
			}else{
				row.setAttribute('bgcolor','#FFFFFF');
				row.setAttribute('onMouseOver',"this.bgColor='#CCCCFF'");
				row.setAttribute('onMouseOut', "this.bgColor='#FFFFFF'");
			}
			if (x[i].childNodes[j].nodeType != 1) continue;
			var container = document.createElement('TD');
			//container.setAttribute('CLASS','data1_nocolor');			
			var tempData = x[i].childNodes[j].firstChild.nodeValue;
			var toolsize=tempData.length;		
			var theData = document.createTextNode(tempData);
			var newLink = document.createElement("a");			
			var af=tempData.split(":");
			newLink.setAttribute("href","javascript:getReport('"+af[0]+"');");
			
			
			//var myLink = document.createElement('a');
			//var myhref = document.createAttribute('href');
			//myLink.setAttribute("href","javascript:getReport('"+af[0]+"');");
			//myLink.innerText ="Go here";
			//myLink.appendChild(theData);
			
			newLink.appendChild(theData);
			container.appendChild(newLink);
			container.setAttribute('WIDTH','75%');			
			row.appendChild(container);
			row.appendChild(container);
			tmp.appendChild(row);			 
		}
	}
	document.getElementById('theExhibit').innerHTML="";
	document.getElementById('theExhibit').appendChild(newEl);
}
  
function getReport(temp){
	url = '/eespc/ShowReport.do?reportType='+temp;
	window.open(url,'_blank');
}
</script>
</head>

<body onLoad="document.dynamicSelect.clName.focus();">



 <span class="page_title">General Reports</span>
 <br> <br>
 <table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#CCCCCC" width="100%" id="AutoNumber1">
<form name="dynamicSelect" >  
  <tr>
    <td width="100%"><div align="center">
Client Name :
    <input type="text" name="clName" value="" class="normal"  onKeyup="fetch(event);" size="20"><input type="button" name="Button" value="Search" onClick="goGetClientDetails();"></td>
  </tr>
</table>
</div>
<br>
<table width='100%'>
<tr>
<td valign='top'>
	<fieldset>
	<legend align='left'>Client Details(s)</legend>
	<span id="theTable"><DIV ALIGN=CENTER>Please enter the Client Name</DIV></span>
	</fieldset></td>
</tr>
<tr>
<td valign='top'>
	
	<fieldset >
	<legend align='left'>Exhibits</legend>
	<span id="theExhibit" ><DIV ALIGN=CENTER>Please select the Client.</DIV></span>
	</fieldset>
</td>
</tr>
</table>
		
</form>
</body>
</html>