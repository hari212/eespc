<%@ page language ="java" import = "sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*,com.eespc.tracking.bo.FacilityVo,java.sql.*,java.text.*" %> 
<html>
<head>
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 34</title>
</head>
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
    
    public static String checkNullAndFilldate(String _input, String _default)
    {
        if(isNotEmpty(_input) && !"null".equalsIgnoreCase(_input.trim()))
        {
        	
        String dd[]=_input.split("-");
    	String dat=dd[1]+"/"+dd[2]+"/"+dd[0];

            return dat;
        }
        else
            return _default;
    }
%>
 
<%
FacilityVo facilityvo= (FacilityVo)session.getAttribute("REPORT_FACILITY_VO");
SqlUtil utilObj = new SqlUtil();


%>

<body>
<%String na="N/A";

java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" cellpadding="7" height="166">
  <tr>
    <td width="100%" height="43">
    <img border="0" src="images/pdf.gif" width="18" height="16">&nbsp;
    <a href="jsp/Exhibit-34xls.jsp">
    <img border="0" src="images/xls.gif" width="19" height="16"></a>
    <img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"><hr></td>
  </tr>
  <tr>
    <td width="100%" height="31">   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    <%
    String str="";
    try
{
Connection conn;
conn =utilObj.getConnection();
 
			PreparedStatement st=conn.prepareStatement("select clientname from facility where facilityid=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            ResultSet rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString(1);          	
            }
            }
            catch(Exception e)
            {
            out.println("Facility Exception :"+e);
            }
            out.println(str);
    %></span></a><br>
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    EESPC Compliance Tracking Software (C)</span></a></td>
  </tr>
  <tr>
    <td width="100%" height="30">
    <p align="center"><font color="#01688A"><b>REPORT</b></font><b><font color="#01688A"> 
    34:HAZARDOUS WASTE (HW) GENERATION AND DISPOSAL SUMMARY FOR'<%=str.toUpperCase()%>' </font></b></td>
  </tr>
  
   <tr>
    <td width="100%" height="2">
     <hr color="#111111" ></td>
  </tr>
</table>       
     

<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3">
  <tr>
    <td  align="center" bgcolor="#01688A"  width="5%"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Year</font></b></td>
    <td  align="center" bgcolor="#01688A" width="95%"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Details</font></b></td>
  </tr>
     
  	<%
  					List wasteList=fromseventh.getNycdobStatusReport34(facilityvo.getId());
                    int psize =0;
					Hashtable hashtable= new Hashtable();
					if (wasteList!= null)
					{
						psize = wasteList.size();
						for (int i=0; i < psize; i++)
						{
						hashtable=(Hashtable)wasteList.get(i);
				
  	%>
  
  <tr>
    <td  valign="top" align="center" width="5%"><font size="2" face="Verdana"><%=hashtable.get("YEAR")%></font> &nbsp;</td>
    <td   width="95%">    
    
    <%
    	List datalist= new ArrayList();
    
     	if(hashtable.get("DATA")!=null)
    	{
			datalist=(ArrayList)hashtable.get("DATA");
    		
 
    		if(datalist!=null)
    	{
    	
    %>
			 
   	<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber4">					
    <tr>    
	  <td  bgcolor="#01688A" align="center" rowspan="2"><b>
      <font face="Verdana" size="2" color="#FFFFFF">Month</font></b></td>  
      <td  bgcolor="#01688A" align="center" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Manifest Date</font></b></td>
		<td  bgcolor="#01688A" align="center" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Manifest No.</font></b></td>
        <td  bgcolor="#01688A" align="center" colspan="4"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Waste details</font></b></td>
        
        <td  bgcolor="#01688A" align="center" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">EPA Id.</font></b></td>
        <td  bgcolor="#01688A" align="center" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Compliance Status</font></b></td>
      </tr>     
           
      
      <tr>       
        <td  bgcolor="#01688A" align="center"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Type Of Waste</font></b></td>
        <td  bgcolor="#01688A" align="center"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Waste Quantity (lbs)</font></b></td>        
        <td  bgcolor="#01688A" align="center"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Total Waste/Month(lbs)</font></b></td>        
        <td  bgcolor="#01688A" align="center"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Category</font></b></td>
      </tr> 
             			
    			<%
    				for(int z=0;z<datalist.size();z++)
    				{
    					String oparr[]=(String[])datalist.get(z);  				
    					int opesize=oparr.length;    					
    			%>        
      <tr>    					 
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[0]%></font></td>
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[8]%></font></td>
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[4]%></font></td>
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%
       
        if(oparr[1].length() > 0){
        String spArr[] = oparr[1].split(",");        
        String wasteArray[] = {"","F Waste","D Waste","K Waste","U Waste","P Waste"};
        
        for(int x = 0;x < spArr.length ; x++){
        out.println(wasteArray[Integer.parseInt(spArr[x])]+"<br>");
       }        
       }        
        %></font></td>
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[2]%></font></td>        
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[9]%></font></td>
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[3]%></font></td>      
        <td  align="center"><font size="2" face="Verdana">&nbsp;<%=oparr[5]%></font></td>
        <td  align="center"><font size="2" face="Verdana">&nbsp;        
        <% 
    if(oparr[7].equalsIgnoreCase("Non Compliance"))
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}
else
{
out.println(oparr[7]);
}
%>
</font></td>
      </tr>   
    
 <%
 
 }
 %>
  </table>
 <%
 }
 }
 %>
    </td>
  </tr>
  
  <%
  	}
	}
  %>
</table>
 <br>

<hr color="#111111" >
<table width="100%" style="border-collapse: collapse" cellpadding="0" cellspacing="0" height="16">
<tr>


 <td width="900" height="16">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
    <br>
    <span style="FONT-SIZE: 15px; FONT-FAMILY: Arial; color:Blue"><b>Foot Note:</b></span>
    <br>
    <span style="FONT-SIZE: 13px; FONT-FAMILY: Arial"><b>CESQG: If the facility generates 220 lb(100kg) or less hazardous waste and also less than 2.2 lb(1kg) of acute waste in a 
    calendar month.</b></span> 
    <br>
    <span style="FONT-SIZE: 13px; FONT-FAMILY: Arial"><b>SQG: If the facility generates 2205 lb(1000kg) or less and more than 220 lb(100kg) of hazardous waste and also less than 2.2 lb(1kg) of acute waste in a 
    calendar month.</b></span>
    <br>
    <span style="FONT-SIZE: 13px; FONT-FAMILY: Arial"><b>LQG: If the facility generates more than 2205 lb(1000kg) of hazardous waste and 2.2 lb(1kg) or more of acute waste in a 
    calendar month.</b></span>
 </td>   
    
 <td width="50">
 <p align="center"><SPAN style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff"><img border="0" src="images/ees.JPG" width="20" height="18" align="right"></SPAN>
 </td>
 
 <td width="200">
  <p align="right"><SPAN style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">Environmental Engineering Solutions, P.C.</SPAN>
 </td> 
 
 
</tr>

</table>