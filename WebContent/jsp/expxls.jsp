<%@ page contentType="application/vnd.ms-excel;
    charset=UTF-8" %>
    
<html>
<head>

<title>Facility</title>
</head>
<body>


<center>
<center>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="1242" id="AutoNumber1">
  <tr>
    <td width="1240" bgcolor="#C0C0C0" colspan="7" align="center"><p align="center"><font color="#003399"><b>Expiration Report:<%out.println("("+(String)request.getAttribute("from")+" To : "+(String)request.getAttribute("from")+")");%></b></font></td>
  </tr>
  <tr>
    <td width="251" bgcolor="#006699"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Facility (Client 
    Name)</font></b></td>
    <td width="156" bgcolor="#006699"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Building Name</font></b></td>
    <td width="207" bgcolor="#006699"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Source Type</font></b></td>
    <td width="200" bgcolor="#006699" align="center" height="18"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Agency/Type</font></b></td>    
    <td width="118" bgcolor="#006699" align="center" height="18"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Date</font></b></td>
     <td width="159" bgcolor="#006699" align="center" height="18"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Required Action</font></b></td>    
    <td width="143" bgcolor="#006699" align="center" height="18"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Contact Information</font></b></td>
  </tr>
  <%=(String)request.getAttribute("total")%>
</table>





</body>

</html>