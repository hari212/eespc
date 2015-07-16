<%@ page contentType="application/vnd.ms-excel;
    charset=UTF-8" %>
<%@ page language ="java" import = "com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.sql.*,Test.TestFacility,java.text.*" %> 
<%!
public static String checkNullAndFill(String _input, String _default)
    {
        if(isNotEmpty(_input) && !"null".equalsIgnoreCase(_input.trim()))
            return _input;
        else
            return _default;
    }
    public static boolean isNotEmpty(String _input)
    {
        return _input != null && _input.trim().length() > 0;
    }
    
 %>

<%
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>

<%
String t[]={"TITLE V","STATE FACILITY","MINOR"};
    String str="";
    Connection conn= null;            
    PreparedStatement st= null;
    ResultSet rs = null;
    try
{
			
			
			conn = utilObj.getConnection();
 
			st=conn.prepareStatement("select facilitytype from facility where facilityid=?");
            st.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString(1);
            	
            	
            }
            }
            catch(Exception e)
            {
            out.println("Facility Exception :"+e);
            }finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
            out.println(str);
            
    %>
<HTML>
<HEAD>
<TITLE>EXHIBIT-19</TITLE>
</HEAD>
<BODY>

<p align="left">&nbsp;</p>

<p align="center"><font color="#01688A" size="4"><b>EXHIBIT 19:DEC_REPORTS FOR STATE FACILITY REPORT<%//out.println(t[Integer.parseInt(str)-1]);%></b></font><hr>

<table  cellSpacing="0" cellPadding="2" border="1" bgcolor="#FFFFFF" style="border-collapse: collapse; border-style: solid; border-width: 1; width:1900" bordercolor="#111111">
            <tr >
        <td align="center" style="background-color: #01688A" width="32" >
        <font face="Times New Roman" color="#FFFFFF">Status</font></td>
        <td align="center" style="background-color: #01688A" width="247" >
        <font face="Times New Roman" color="#FFFFFF">Client</font></td>
        <td align="center" style="background-color: #01688A" width="166" >
        <font face="Times New Roman" color="#FFFFFF">Permit</font></td>
        <td align="center" style="background-color: #01688A" width="31" >
        <font face="Times New Roman" color="#FFFFFF">Expire</font></td>
        <td align="center" style="background-color: #01688A" width="26" >
        <font face="Times New Roman" color="#FFFFFF">Due</font></td>
        <td align="center" style="background-color: #01688A" width="21" >
        <font face="Times New Roman" color="#FFFFFF">Jan</font></td>
        <td align="center" style="background-color: #01688A" width="28" >
        <font face="Times New Roman" color="#FFFFFF">Feb</font></td>
        <td align="center" style="background-color: #01688A" width="25" >
        <font face="Times New Roman" color="#FFFFFF">Mar</font></td>
        <td align="center" style="background-color: #01688A" width="24" >
        <font face="Times New Roman" color="#FFFFFF">Apr</font></td>
        <td align="center" style="background-color: #01688A" width="23" >
        <font face="Times New Roman" color="#FFFFFF">May</font></td>
        <td align="center" style="background-color: #01688A" width="23" >
        <font face="Times New Roman" color="#FFFFFF">Jun</font></td>
        <td align="center" style="background-color: #01688A" width="30" >
        <font face="Times New Roman" color="#FFFFFF">Jul</font></td>
        <td align="center" style="background-color: #01688A" width="26" >
        <font face="Times New Roman" color="#FFFFFF">Aug</font></td>
        <td align="center" style="background-color: #01688A" width="24" >
        <font face="Times New Roman" color="#FFFFFF">Sep</font></td>
        <td align="center" style="background-color: #01688A" width="30" >
        <font face="Times New Roman" color="#FFFFFF">Oct</font></td>
        <td align="center" style="background-color: #01688A" width="34" >
        <font face="Times New Roman" color="#FFFFFF">Nov</font></td>
        <td align="center" style="background-color: #01688A" width="23" >
        <font face="Times New Roman" color="#FFFFFF">Dec</font></td>
        <td align="center" style="background-color: #01688A" width="65" >
        <font face="Times New Roman" color="#FFFFFF">Last <br>
        Proposal</font></td>
        <td align="center" style="background-color: #01688A" width="70" >
        <font face="Times New Roman" color="#FFFFFF">Next <br>
        Proposal</font></td>
      </tr>
      <%
      String bk="white";
          try
{
			
			conn =utilObj.getConnection();
 
			st=conn.prepareStatement("select clientname,decid from facility where facilitytype=?");
            st.setString(1,"2");
            rs=st.executeQuery();
             int i=1;
            while(rs.next())
            {
            	
                        	
            
%>

      <tr>
        <td  align="center"  width="32" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="247" bgcolor="#FFFFFF" ><p align="left"><%=rs.getString(1)%></td>
        <td  align="center"  width="166" bgcolor="#FFFFFF" ><p align="left"><%=rs.getString(2)%></td>
        <td  align="center"  width="31" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="26" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="21" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="28" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="25" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="24" bgcolor="#FFFFFF" >&nbsp;</td>
        <td  align="center"  width="23" >&nbsp;</td>
        <td  align="center"  width="23" >&nbsp;</td>
        <td  align="center"  width="30" >&nbsp;</td>
        <td  align="center"  width="26" >&nbsp;</td>
        <td  align="center"  width="24" >&nbsp;</td>
        <td  align="center"  width="30" >&nbsp;</td>
        <td  align="center"  width="34" >&nbsp;</td>
        <td  align="center"  width="23" >&nbsp;</td>
        <td  align="center"  width="65" >&nbsp;</td>
        <td  align="center"  width="70" >&nbsp;</td>
      </tr>
      <%
      i=i+1;
      }
            }
           catch(Exception e)
            {
            out.println("Exception :"+e);
            }finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
%>
    </table>
    </td>
  </tr>
</table>

</div>
<b><font color="#01688A" size="2">A: Annual Contract<br>
N: New/Prospective<br>
P: Per Project </font></b>

&nbsp;<hr color="#000000">
<table width="100%" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0">
<tr><td width="42">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
 </td>
  <td width="42">
 &nbsp;</td>
  <td width="42">
 &nbsp;</td>
  <td width="42">
 &nbsp;</td>
  <td width="42">
 &nbsp;</td>
  <td width="42">
 &nbsp;</td>
  <td width="42">
 &nbsp;</td>
  <td width="42">
 &nbsp;</td>
  <td width="41">
 &nbsp;</td>
  <td width="41">
 &nbsp;</td>
  <td width="11">
 &nbsp;</td>
  <td width="10">
 &nbsp;</td>
  <td width="10">
 &nbsp;</td>
  <td width="10">
 &nbsp;</td>
  <td width="11">
 &nbsp;</td>
  <td width="10">
 &nbsp;</td>
  <td width="10">
 &nbsp;</td>
  <td width="10">
 &nbsp;</td>
 <td width="385">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">Environmental 
Engineering Solutions, P.C.</SPAN>
 </td>
 
 </tr>
</table>
</BODY>
</HTML>