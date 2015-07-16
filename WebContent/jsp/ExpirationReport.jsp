<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>

<%@ page language ="java" import = "com.eespc.tracking.bo.UserVo,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.sql.*,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.entity.ExpirationEntity,java.text.*" %> 
<%

SqlUtil utilObj = new SqlUtil();

            
Calendar cal = Calendar.getInstance();
ExpirationEntity ee=new ExpirationEntity();
UtilityObject uo=new UtilityObject();

  String from=String.valueOf(request.getAttribute("from"));
  
  String to=String.valueOf(request.getAttribute("to"));
  String getfrom="";
  String getto="";
  if(from.equals("xxx") || from ==null)
  {
  getfrom=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
  cal.add(Calendar.MONTH, 2);
  getto=cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.DAY_OF_MONTH);
  
  }
  else
  {
  getfrom=from;
  getto=to;
  }  
%> 
<html>
<head>
<script language="javascript" type="text/javascript" src="js/datetimepicker.js">
  </script>
<title>Facility</title>
</head> 
<body>
<span class="page_title">
Expiration Report </span>
<br><br>
<form method="POST" action="ExpirationReport.do">
<html:hidden property="methodToCall" value="html"/>
  <center>
<table border="0" cellpadding="0" cellspacing="0" style="border-style:solid; border-width:1; border-collapse: collapse; padding-left:4; padding-right:4; padding-top:1; padding-bottom:1" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#C0C0C0">
  <tr>
    <td width="33%"><a href="ExpirationReport.do?methodToCall=xls&from=<%=getfrom%>&to=<%=getto%>" target="_blank" Title="Excel Report"><img border="0" src="images/xls.gif" width="33" height="25"></a>&nbsp;
    <a href="ExpirationReport.do?methodToCall=mail&from=<%=getfrom%>&to=<%=getto%>" title="Send Mail"><img border="0" src="images/mail.jpg" width="34" height="25"></a></td>
    <td width="33%">
    <p align="center"><font color="#003399"><b>Expiration Report:<%out.println("("+uo.convertYyyyMmDd2MmDdYyyy(getfrom)+" To : "+uo.convertYyyyMmDd2MmDdYyyy(getto)+")");%></b></font></td>
    <td width="34%">&nbsp;</td>
  </tr>
</table>
</center>
<br>
<center>
<table border="0" cellpadding="0" cellspacing="0" style="border-style:solid; border-width:1; border-collapse: collapse; padding-left:4; padding-right:4; padding-top:1; padding-bottom:1" bordercolor="#111111" width="100%" id="AutoNumber2" bgcolor="#C0C0C0">
  <tr>
    <td width="88%"><b>From</b><input id="demo1" type="text" name="from" size="20"><a href="javascript:NewCal('demo1','mmddyyyy',false,24)" title="Select Date"><img border="0" src="images/cale.gif" width="16" height="16"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <b>To</b><input id="demo2" type="text" name="to" size="20"><a href="javascript:NewCal('demo2','mmddyyyy',false,24)" title="Select Date"><img border="0" src="images/cale.gif" width="16" height="16"></a><input type="submit" value="Submit" name="B1"></td>
    <td width="7%" align="center">
    <p align="right">&nbsp;</td>
    <td width="5%" align="center">
    <p align="right">&nbsp;</td>
  </tr>
</table>
</center>
<br>
<center>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
  <tr>
    <td  bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Facility (Client Name)</font></b></td>
    <td  bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Building Name</font></b></td>
    <td  bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Source Type</font></b></td>
    <td  bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Agency/Type</font></b></td>    
    <td bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Date</font></b></td>     
    <td  bgcolor="#006699" align="center"><b>    
    <font color="#FFFFFF" face="Verdana" size="2">Required Action</font></b></td>    
    <td  bgcolor="#006699" align="center"><b>
    <font color="#FFFFFF" face="Verdana" size="2">Contact Information</font></b></td>
    <td  bgcolor="#006699" align="center"><b>  
    <font color="#FFFFFF" face="Verdana" size="2">Notes</font></b></td>
  </tr>
  <%  
  
  		 Connection connection = null;            
         PreparedStatement preparedstatement = null;
         ResultSet rs = null;    
	
 		try
        {
            UserVo uservo = (UserVo)session.getAttribute("SESSION_USER");
            connection = utilObj.getConnection();            
            preparedstatement = connection.prepareStatement("select clientname,facilityid from facility where facilityid in (select facilityid from usertofacility where userid=?)");
            preparedstatement.setString(1,String.valueOf(uservo.getUserId()));
            rs = preparedstatement.executeQuery();
           
            while(rs.next())
            {
            String facid=(String)rs.getString("facilityid");
           //  String st=ee.getStatehtml(facid,getfrom,getto);
           //  String s=ee.gethtml(facid,getfrom,getto);            
           String st=ee.gethtml(facid, getfrom, getto);
           String  s=ee.getStatehtml(facid, getfrom, getto)+ee.getcertificateoffitnessrenewaldatehtml(facid, getfrom, getto)+ee.getcertificateoffitnesshtml(facid, getfrom, getto)+ee.getStatenextproposalhtml(facid, getfrom, getto)+st;
                       
            if(s.equals(""))
            {            
            }
            else
            { 
                      
            %>
            
  <tr> 
    <td width="237" bordercolor="#FF0000" bgcolor="#0066CC">
    <p align="left"><span style="text-transform:uppercase">
    <font size="2" color="#FFFFFF" face="Verdana"><%=(rs.getString("Clientname")).toUpperCase()%></font></span></td>
    <td width="168" bgcolor="#E4E8E3">&nbsp;</td>
    <td width="169" bgcolor="#E4E8E3">&nbsp;</td>
    <td width="78" bgcolor="#E4E8E3">&nbsp;</td>    
    <td width="125" bgcolor="#E4E8E3">&nbsp;</td>        
    <td width="157" bgcolor="#E4E8E3">&nbsp;</td>
    <td width="157" bgcolor="#E4E8E3">&nbsp;</td>
    <td width="157" bgcolor="#E4E8E3">&nbsp;</td> 
  </tr>
             
              <%
            
            }
            
          //  if(!st.equals(""))
          //  out.println(st);
            
            if(s.equals(""))
            {            
            }
            else
            {
            out.println(s);
            }
        }    
        

        }
        catch(Exception exception)
        {
        out.println(exception);  
        } finally
        {
           SqlUtil.close(rs);
           SqlUtil.close(preparedstatement);
           SqlUtil.close(connection);                            
		}
         %>
    </table>


<p align="center">&nbsp;</p>       
        <logic:present name="MESSAGE" scope="request">
    	<script>
		alert("<bean:write name='MESSAGE' scope='request' filter='false' />");
		    </script>
		</logic:present>

</form>

</body>

</html>