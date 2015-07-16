<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<html>
<head>
<title>EESPC - Login Screen</title>

<style type="text/css">
html
{
height: 100%;
}
body
{
height: 100%;
}
#nonFooter
{
position: relative;
min-height: 100%;
}

* html #nonFooter
{
height: 100%;
}

#content
{
padding-bottom: 8em;
}

#footer
{
position: relative;
margin-top: -9em;
}  


.mybtn
{
        width: 120px;
        height: 21px;
        border-style: outset;
	border-color: red;
        font-size: 12px;        
        color: #000000;
	background-color: rgb(250, 240, 230);
        border: 1px 1px 1px 1px;
        outline: 10px;
	text-align: center;
}
  </style>
<link rel="stylesheet" href="/eespc/css/bootstrap.css">
<link rel="stylesheet" href="/eespc/css/bootstrap-theme.min.css">
<script src="/eespc/js/jquery.js" ></script>
<script src="/eespc/js/bootstrap.js" ></script>
<script src="/eespc/js/CommonScript.js" ></script>
<script language="JavaScript">

function termsandcondition()
{
Tip('EESPC welcomes you to navigate the Software and track your facility\'s compliance status by choosing \'Accept\' box:<br>I understand that incorrect data will cause the program to yield incorrect results.', TITLE, 'Terms &amp; Conditions',WIDTH,'25' )
}
	function loginCheck(){
	
		if(document.userForm.loginId.value.length==0 || 
			document.userForm.passWord.value.length==0){
			alert("Please enter loginId and Password.");
			return false;
		}
		else if(document.forms[0].accept.checked==false)
		{
		alert("Please Read Terms and Conditions.If You Accept,Choose Accept Box.");
		return false;
		}
		else
		{		
		document.forms[0].methodToCall.value='verify';
		document.forms[0].submit();
		return true;
		}
		
	}
	function myreset(){

	document.userForm.loginId.value="";
	document.userForm.passWord.value="";	
		return false;
		
	}
	$(function() {
	    $("#abc").attr("placeholder", "User Name");
	    $("#pwd").attr("placeholder", "Password");
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
<body background="images/bg.png" onLoad="document.forms[0].loginId.focus();">
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<div id="nonFooter">
<div id="content">
<html:form action="/login">
  <html:hidden property="methodToCall" />
  <div class="container">
  	<div class="row">
  		<div class="col-md-4"></div>
  		<div class="col-md-5">
  			<div class="well" style="margin-top: 35%;opacity:0.8; border-radius: 15px; margin-left: -5%">
  				<div class="row">
  				<div class="col-md-1"></div>
  				<div class="col-md-10">
  					<h4 style="color: brown; font-style: unset; font-size: 20px;">Environmental Engineering Solutions P.C</h4>
  				</div>
  					
  				</div>
  				<div class="row" style="margin-top: 20px;">
  					<div class="col-md-1"></div>
  					<div class="col-md-3"><p style="font-size: 17px; color: chocolate; font-weight: bold;">User Name</p></div>
  					<div class="col-md-7">
  					
  						<html:text property="loginId" styleClass="form-control" style="width: 96%" styleId="abc"> </html:text>
  						 
  					</div>
  				</div>
  				<div class="row" style="margin-top: 20px;">
  					<div class="col-md-1"></div>
  					<div class="col-md-3"><p style="font-size: 17px; color: chocolate; font-weight: bold;">Password</p></div>
  					<div class="col-md-7">
  						
  						 <html:password property="passWord" styleClass="form-control" style="width: 96%" styleId="pwd" />
  					</div>
  				</div>
  				<div class="row" style="margin-top: 20px;">
  					<div class="col-md-1"></div>
  					<div class="col-md-10">
  						
  						<input type="checkbox"  name="accept" value="ON"><font face="Verdana" size="2"> I Accept the
                        <a href="#" onmouseover="termsandcondition();"  onmouseout="UnTip()">Terms &amp; Conditions</a>
  					</div>
  				</div>
  				<div class="row" style="margin-top: 20px;">
  					<div class="col-md-1"></div>
  					<div class="col-md-4">
  						<button class="btn btn-primary" alt="Submit" onclick="loginCheck()" style="width:94%">Submit</button>
     
     				</div>
     				<div class="col-md-4">
     				<button onclick="myreset()" class="btn btn-warning" style="width:92%; margin-left: -10%" >Cancel</button>
 
     				</div>

  					
  				</div>
  				<div class="row" style="margin-top: 20px;">
  					<div class="col-md-1"></div>
  					<div class="col-md-10">
  						<a href="jsp/forgotpassword.jsp">I forgot my password?</a>
     
     				</div>
     				

  					
  				</div>
  			</div>
  		</div>
  		<div class="col-md-3"></div>
  	</div>
  
  </div>
  
  <logic:present name="MESSAGE" scope="request">
    <script>
		alertMessage("<bean:write name='MESSAGE' scope='request' filter='false' />", 
						"<bean:write name='MESSAGE_TYPE' scope='request' filter='false' />");
	  </script>
  </logic:present>
</html:form>

</div>
</div>

 
</body>
</html>