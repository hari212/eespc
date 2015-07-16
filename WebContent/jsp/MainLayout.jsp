 <%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
 <%@ taglib uri="/WEB-INF/tlds/struts-tiles.tld" prefix="tiles" %>

<TITLE>EESPC Tracking System</TITLE>
<link rel="icon" type="image/ico" href="images/link.ico"></link> 
<link rel="stylesheet" type="text/css" href="/eespc/css/eepsc_style.css" />
 <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
   <tr>
     <td width="100%"><tiles:insert attribute="header" /></td>
   </tr>
   <tr>
     <td width="100%" bgcolor="#FCEAE9">
     <!--MARQUEE WIDTH="100%">
<IMG SRC="images/eye.png" ALT="Idocs Guide to HTML">Hi There! Welcome To Environmental Engineering Solutions.<IMG SRC="images/eye.png"  ALT="Idocs Guide to HTML">
</MARQUEE-->

     
     </td>
   </tr>
   <tr>
     <td width="100%" bgcolor="#000000" background="css/bg-bubplastic-h-gray.gif" style="border-left-width: 1; border-right-style: solid; border-right-width: 1; border-top-width: 1; border-bottom-width: 1" align="center"><tiles:insert attribute="menu" /></td>
   </tr>
   <tr>
   
     <td width="100%"><br>
     <div style="width:100% ; overflow: auto; border: 0; background-color: #FFFFFF;"> 
     <tiles:insert attribute="content" />
     </div>
     </td>
   </tr>
 </table>