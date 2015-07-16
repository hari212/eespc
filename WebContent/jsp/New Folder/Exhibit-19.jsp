
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
    try
{
			Connection conn;
			
			conn = utilObj.getConnection();
 
			PreparedStatement st=conn.prepareStatement("select facilitytype from facility where facilityid=?");
            st.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString(1);
            	
            	
            }
            }
            catch(Exception e)
            {
            }
            
    %>
<HTML>
<HEAD>
<TITLE>EXHIBIT-19</TITLE>
<STYLE type=text/css>DIV.tableContainer {
	MARGIN: 0px auto; OVERFLOW: auto; WIDTH: 100%; HEIGHT: 295px
}
TABLE {
	BORDER-RIGHT: medium none; BORDER-TOP: medium none; BORDER-LEFT: medium none; WIDTH: 99%; BORDER-BOTTOM: medium none; BACKGROUND-COLOR: #f7f7f7
}
UNKNOWN {
	OVERFLOW-X: hidden;  HEIGHT: 200px
}
THEAD TR {
	POSITION: relative; ; TOP: expression(offsetParent.scrollTop)
}
THEAD TD {
	BORDER-TOP: #d8d8d8 2px solid; FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: steelblue; BACKGROUND-COLOR: white; TEXT-ALIGN: center
}
THEAD TH {
	BORDER-TOP: #d8d8d8 2px solid; FONT-WEIGHT: bold; FONT-SIZE: 14px; COLOR: steelblue; BACKGROUND-COLOR: blue; TEXT-ALIGN: center
}
TD {
	PADDING-RIGHT: 2px; FONT-SIZE: 12px; BORDER-LEFT: #d8d8d8 1px solid; COLOR: #000; BORDER-BOTTOM: #d8d8d8 1px solid; TEXT-ALIGN: right
}
TD:unknown {
	PADDING-RIGHT: 20px
}
</STYLE>
<!-- print style sheet -->
<STYLE type=text/css media=print>DIV.tableContainer {
	
}
UNKNOWN {
	
}
TD {
	HEIGHT: 14pt
}
THEAD TD {
	FONT-SIZE: 15pt
}
THEAD {
	DISPLAY: table-header-group
}
TFOOT {
	DISPLAY: table-footer-group
}
THEAD TH {
	POSITION: static
}
THEAD TD {
	POSITION: static
}
</STYLE>

</HEAD>
<script>
var preEl ;
var orgBColor;
var orgTColor;
function HighLightTR(backColor,textColor){
  if(typeof(preEl)!='undefined') {
     preEl.bgColor=orgBColor;
     try{ChangeTextColor(preEl,orgTColor);}catch(e){;}
     }
  var el = event.srcElement;
  el = el.parentElement;
  orgBColor = el.bgColor;
  orgTColor = el.style.color;
  el.bgColor=backColor;

  try{ChangeTextColor(el,textColor);}catch(e){;}
  preEl = el;
  }

function ChangeTextColor(a_obj,a_color){  ;
   for (i=0;i<a_obj.cells.length;i++)
    a_obj.cells(i).style.color=a_color;
    }
</script>
<BODY>

<p align="left"><img border="0" src="images/pdf.gif" width="16" height="16">
<a href="jsp/Exhibit-19xls.jsp"><img border="0" src="images/xls.gif" width="16" height="16"></a>&nbsp;&nbsp;
<img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"></p>

<p align="center"><font color="#01688A"><b>EXHIBIT 19:DEC_REPORTS FOR STATE FACILITY REPORT<%//out.println(t[Integer.parseInt(str)-1]);%></b></font><hr>
<div style="height:450px; width:100%; overflow:scroll">
<table  cellSpacing="0" cellPadding="2" border="1" bgcolor="#5B7AAC" style="border-collapse: collapse; border-style: solid; border-width: 1; width:1608" bordercolor="#111111">
 <tbody>
 <thead>
           <tr >
        <td align="center" style="background-color: #01688A" width="22" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Status</font></td>
        <td align="center" style="background-color: #01688A" width="257" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Client</font></td>
        <td align="center" style="background-color: #01688A" width="179" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Permit</font></td>
        <td align="center" style="background-color: #01688A" width="28" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Expire</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Due</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Jan</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#FFFFFF">Feb</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Mar</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Apr</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">May</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Jun</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Jul</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Aug</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Sep</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Oct</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Nov</font></td>
        <td align="center" style="background-color: #01688A" width="20" >
        <font size="1" face="Times New Roman" color="#111111">Dec</font></td>
        <td align="center" style="background-color: #01688A" width="70" >
        <font size="1" face="Times New Roman" color="#111111">Last <br>
        Proposal</font></td>
        <td align="center" style="background-color: #01688A" width="70" >
        <font size="1" face="Times New Roman" color="#111111">Next <br>
        Proposal</font></td>
      </tr>
      <%
      String bk="white";
          try
{
			Connection conn;
			
			conn =utilObj.getConnection();
 
			PreparedStatement st=conn.prepareStatement("select clientname,decid from facility where facilitytype=?");
            st.setString(1,"2");
            ResultSet rs=st.executeQuery();
             int i=1;
            while(rs.next())
            {
            	
            	if((i%2)==0)
			{
			bk="";
			
			}
			else
			{
			bk="#EBF0F3";
			}
            	
            
%>
<TBODY>
      <tr bgcolor="<%=bk%>" 
      onClick="HighLightTR('#c9cc99','cc3333');"
       >
        <td  align="center"  width="22" >&nbsp;</td>
        <td  align="center"  width="257" ><font size="1"><p align="left"><%=rs.getString(1)%></font>&nbsp;</td>
        <td  align="center"  width="179" ><p align="left"><%=rs.getString(2)%>&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="20" >&nbsp;</td>
        <td  align="center"  width="70" >&nbsp;</td>
        <td  align="center"  width="70" >&nbsp;</td>
      </tr>
      <%
      i=i+1;
      }
            }
            catch(Exception e)
            {
            }%>
    </table>
    </td>
  </tr>
</table>

</div>
<b><font color="#01688A" size="1">A: Annual Contract<br>
N: New/Prospective<br>
P: Per Project </font></b>
&nbsp;<hr color="#000000">
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

</BODY>
</HTML>