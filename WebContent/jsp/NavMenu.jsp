<%@ page language="java" import="com.eespc.tracking.bo.Constants" %>
<link rel="stylesheet" type="text/css" href="/eespc/css/csshorizontalmenu.css" /> 
<!--style type="text/css">


#menu1 a {color:white;background-color:black;text-decoration:none;text-indent:1ex;}
#menu1 a:active {color:red;text-decoration:none;}
#menu1 a:hover {color:black;background-color:#FFFF99}
#menu1 a:visited {color:black;text-decoration:none;}

#menu2 a {color:navy;background-color:white;text-decoration:none;text-indent:1ex;}
#menu2 a:active	{color:blue;text-decoration:none;}
#menu2 a:visited {color:blue;text-decoration:none;}
#menu2 a:hover {color:navy;background-color:#f0fea8}

#menu3 a { /*Menu3 Links*/
color:red;
background-color:white;
text-decoration:none;
text-indent:1ex;
}
#menu3 a:hover {
color:black;background-color:#FFFF99;
}
#menu3 a:active	{color:black;text-decoration:none;}
#menu3 a:visited	{color:black;text-decoration:none;}



</style-->
<!--script src="js/mmenu.js" type="text/javascript"></script>
<script src="js/menuItems.js" type="text/javascript">
</script-->
<script src="/eespc/js/csshorizontalmenu.js" ></script>

   <div class="horizontalcssmenu">
<ul id="cssmenu1">
<%
				try
				{
					if(session.getAttribute(Constants.KEY_MENU_ITEMS)==null){
						//out.println("<script>");
						//out.println("top.window.location.href=top.window.location;");
						//out.println("</script>");
						out.println("Your Session is lost. Please relogin.");

						return;
					}else
						out.println(session.getAttribute(Constants.KEY_MENU_ITEMS));
				}
				catch (Exception e)
				{
					out.println(e.getMessage());
				}
%>    
                </ul>
                

</div>