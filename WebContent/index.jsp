<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>EESPC Software Tracking</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<script>
	function renderPage(){
	//alert("Please dont access this ip Address");
		var port = window.location.port;
		if (port.length > 0){
			port = ":" + port;
		}
		var urlToGo = window.location.protocol + '//' + window.location.hostname + port + "/eespc/ControllerAction.do"

		if(navigator.userAgent.indexOf("MSIE") != -1){

	   with(top.document)
         {

           open ('text/html');

           writeln ('<HTML><HEAD><TITLE>Welcome to EFMS Local Services</TITLE></HEAD>');

           writeln ('<FRAMESET ROWS="0,*" FRAMEBORDER="NO" BORDER="0" onBlur="return true" onFocus="return true" FRAMESPACING="0">');

           writeln (' <FRAME NAME="BLANK" SCROLLING="NO" NORESIZE="" SRC="" >');

           writeln (' <FRAME NAME="main" SRC="' + urlToGo+'">');

           writeln ('</FRAMESET>');

           writeln ('</HTML>');

           close ();

         }
		}
		else if(navigator.userAgent.indexOf("MSIE") >0){

	   with(top.document)
         {

           open ('text/html');

           writeln ('<HTML><HEAD><TITLE>Welcome to EFMS Local Services</TITLE></HEAD>');

           writeln ('<FRAMESET ROWS="0,*" FRAMEBORDER="NO" BORDER="0" onBlur="return true" onFocus="return true" FRAMESPACING="0">');

           writeln (' <FRAME NAME="BLANK" SCROLLING="NO" NORESIZE="" SRC="" >');

           writeln (' <FRAME NAME="main" SRC="' + urlToGo+'">');

           writeln ('</FRAMESET>');

           writeln ('</HTML>');

           close ();

         }
		}

		else{
			document.location.href=urlToGo;		
		}



		/*if (navigator.userAgent.indexOf("MSIE") != -1)
		{
			with(top.document)
			{
				open('text/html');
				writeln('<HTML><HEAD><TITLE>Welcome to EESPC</TITLE></HEAD>');
				writeln('<FRAMESET ROWS="0,*" FRAMEBORDER="NO" BORDER="0" onBlur="return true" onFocus="return true" FRAMESPACING="0">');
				writeln (' <FRAME NAME="BLANK" SCROLLING="NO" NORESIZE="" SRC="" >');
				writeln (' <FRAME NAME="main" SRC="' + urlToGO + '">');
				writeln (' </FRAMESET>');
				writeln (' </HTML> ');
				close();
			}
		} else {
			document.location.href=urlToGo;
		}
*/
	}
</script> 
</head>

<body onLoad="renderPage();">

</body>
</html>