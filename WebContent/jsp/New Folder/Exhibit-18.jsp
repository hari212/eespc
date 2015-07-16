
<script>


function searchExist(){
	var url = "/eespc/help.html";
	window.open(url,"_blank");
}
</script>



<%@ page language ="java" import = "com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.sql.*,Test.TestFacility,java.text.*" %> 
<%String na="-";
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>
<%
String t[]={"TITLE V","STATE FACILITY","MINOR"};
    String str="";
    String str1="";
    String address1="";
    String city="";
    String state="";
    String zipcode="";
    
    try
{
Connection conn;

conn = utilObj.getConnection();
 
			PreparedStatement st=conn.prepareStatement("select a.clientname,a.facilitytype,b.address1,b.city,b.state,b.zipcode from (facility a,address b) where a.addressid=b.addressid and a.facilityid=?");
            st.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString("clientname");
				str1=rs.getString("facilitytype");
				address1=rs.getString("address1");
				city=rs.getString("city");
				state=rs.getString("state");
				zipcode=rs.getString("zipcode");
			}
            }
            catch(Exception e)
            {
            }
            
    %>
    
<p><a href="exhi18">
<img border="0" src="images/word.jpg" width="37" height="40"></a>
<a href="jsp/update.jsp" target="_blank"><img border="0" src="images/load.jpg" width="36" height="33"></a>
<img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"></a></p>
<hr><span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial"><%=str%></span><br>
<span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    Environmental Compliance Assessment Manual</span>
<p align="center"><font color="#01688A" face="Times New Roman">
<span style="font-variant: small-caps; font-weight: 700">Exhibit 18:<%=str%> – modified <%out.println(t[Integer.parseInt(str1)-1]);%> permit reporting dates</span></font>
<font color="#01688A" face="Times New Roman">
<span style="font-variant: small-caps; font-weight: 700">
<%=address1%>, <%=city%>, <%=state%>-<%=zipcode%>
</span></font></p>
<hr>
<p><input type="submit" value="Help" name="B1" onClick="searchExist();" ></p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<hr>
<table width="100%" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0">
<tr><td width="500">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
 </td>
 <td width="385">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">Environmental 
Engineering Solutions, P.C.</SPAN>
 </td>
 
 </tr>
</table>