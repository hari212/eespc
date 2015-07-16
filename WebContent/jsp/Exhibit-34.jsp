<%@ page language ="java" import = "sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*,com.eespc.tracking.bo.FacilityVo,java.sql.*,java.text.*" %> 
<html>
<head>
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
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
    Connection conn= null;            
    PreparedStatement st= null;
    ResultSet rs = null;
    try
{
			conn =utilObj.getConnection(); 
			st=conn.prepareStatement("select clientname from facility where facilityid=?");
            st.setString(1,String.valueOf(facilityvo.getId()));
            rs=st.executeQuery();
            while(rs.next())
            {
            	str=rs.getString(1);          	
            }
            }
            catch(Exception e)
            {
            out.println("Facility Exception :"+e);
            } finally
        	{
        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);          
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
    <td  align="center" bgcolor="#01688A"  width="5%" rowspan="2"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Year</font></b></td>
    <td  align="center" bgcolor="#01688A" width="11%" rowspan="2"><b>
    <font face="Verdana" size="2" color="#FFFFFF">Month</font></b></td>
    <td  align="center" bgcolor="#01688A" width="11%" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Manifest Date</font></b></td>
    <td  align="center" bgcolor="#01688A" width="11%" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Manifest No.</font></b></td>
    <td  align="center" bgcolor="#01688A" width="42%" colspan="4"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Waste details</font></b></td>
    <td  align="center" bgcolor="#01688A" width="10%" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">EPA Id.</font></b></td>
    <td  align="center" bgcolor="#01688A" width="10%" rowspan="2"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Compliance Status</font></b></td>
  </tr>
     
  <tr>
    <td  align="center" bgcolor="#01688A" width="11%"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Type Of Waste</font></b></td>
    <td  align="center" bgcolor="#01688A" width="11%"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Waste Quantity (lbs)</font></b></td>
    <td  align="center" bgcolor="#01688A" width="10%"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Total Waste/Month(lbs)</font></b></td>
    <td  align="center" bgcolor="#01688A" width="10%"><b>
        <font face="Verdana" size="2" color="#FFFFFF">Category</font></b></td>
  </tr>
     
  	<%
  	try{
  					String montharray[]={"","January","February","March","April","May","June","July","August","September","October","November","December"};
  					Map yearMap=(Map)fromseventh.getNycdobStatusReport34a(facilityvo.getId());
  					SortedMap monthMap;
  					StringBuffer buf=null; String category=null;
  					Iterator yearItr =((Set)yearMap.keySet()).iterator();
  					Iterator monItr ;	List valueList;
  					String year,month; int yr=0,mn=0;
  					while(yearItr.hasNext()){
	  					year=(String)yearItr.next(); 
	  					monthMap=(SortedMap)yearMap.get(year);
	  					monItr =monthMap.keySet().iterator();
	  					buf=new StringBuffer();
	  					//if(yr!=0) buf.append("<tr>"); yr++;
  						buf.append("<tr><td  align=\"center\" rowspan=\"{YEARSPAN}\"><font size=\"2\" face=\"Verdana\">"+year+"</font> &nbsp;</td>"); 
  						int yearcount=0,mounthcount;
  						float total=0; mn=0;
  						while(monItr.hasNext()){
	  						total=0; 
	  						if(mn!=0) buf.append("<tr>"); mn++;
		  					month=(String)monItr.next();
  							valueList=(List)monthMap.get(month);
  							mounthcount=valueList.size();
  							buf.append("<td  align=\"center\" rowspan=\""+mounthcount+"\"><font size=\"2\" face=\"Verdana\">"+montharray[Integer.parseInt(month)]+"</font> &nbsp;</td>"); 
  							for(int i=0;i<valueList.size();i++) {
  							yearcount++;
  							 String[] valueSet=valueList.get(i).toString().split("##");
  							 if(i!=0) buf.append("<tr>");
  							 buf.append("<td  align=\"center\" ><font size=\"2\" face=\"Verdana\">"+valueSet[0]+"</font> &nbsp;</td>"); 
  							 buf.append("<td  align=\"center\" ><font size=\"2\" face=\"Verdana\">"+valueSet[1]+"</font> &nbsp;</td>");
  							
  							 String[] wasteDetails=valueSet[2].split("!!");
  							 StringBuffer bufwaste=new StringBuffer();
  							 StringBuffer bufwasteqty=new StringBuffer();
  							 float itrTotal=0;
  							 for(int j=0;j<wasteDetails.length;j++){
  							 if(wasteDetails[j]!=null && !"".equalsIgnoreCase(wasteDetails[j])){
  							 
  							  String[] wasteItr=wasteDetails[j].split(":=");
								if(wasteItr[0]!=null && !"".equalsIgnoreCase(wasteItr[0])) {
									bufwaste.append(wasteItr[0]+"<br/>");
									bufwasteqty.append(wasteItr[1]+"<br/>");
									itrTotal+=Float.parseFloat(wasteItr[1]);
								}
							   }
  							 }
  							 total+=itrTotal;
  							 buf.append("<td  align=\"center\" ><font size=\"2\" face=\"Verdana\">"+bufwaste.toString()+"</font> </td>");
							 buf.append("<td  align=\"center\" ><font size=\"2\" face=\"Verdana\">"+itrTotal+"</font> </td>");

							 if(i==0) {
								 category=valueSet[3];
							   	buf.append("<td  align=\"center\" rowspan=\""+mounthcount+"\" ><font size=\"2\" face=\"Verdana\">{TOTAL}</font> </td>");
							 	buf.append("<td  align=\"center\" rowspan=\""+mounthcount+"\" ><font size=\"2\" face=\"Verdana\">"+valueSet[3]+"</font> &nbsp;</td>"); 
							 	buf.append("<td  align=\"center\" rowspan=\""+mounthcount+"\" ><font size=\"2\" face=\"Verdana\">"+valueSet[4]+"</font> &nbsp;</td>"); 
							 	buf.append("<td  align=\"center\" rowspan=\""+mounthcount+"\" ><font size=\"2\" face=\"Verdana\">{COMPLIANCE}</font> &nbsp;</td>"); 
							 }
					
							  buf.append("</tr>");
  							 }
  							 buf.replace(buf.indexOf("{TOTAL}"),buf.indexOf("{TOTAL}")+7,""+total);
  							 
  							 
  							 
  							 // Compliance status :: Calculation begin
  							 
							if("CESQG".equalsIgnoreCase(category))
							{
							 if(total<=220)
  							   buf.replace(buf.indexOf("{COMPLIANCE}"),buf.indexOf("{COMPLIANCE}")+12,"Compliance");
  							 else
  							  buf.replace(buf.indexOf("{COMPLIANCE}"),buf.indexOf("{COMPLIANCE}")+12,"Non Compliance");
							}
							
							else if("SQG".equalsIgnoreCase(category))
							{
							 if(total<=2205)
  							   buf.replace(buf.indexOf("{COMPLIANCE}"),buf.indexOf("{COMPLIANCE}")+12,"Compliance");
  							 else
  							  buf.replace(buf.indexOf("{COMPLIANCE}"),buf.indexOf("{COMPLIANCE}")+12,"Non Compliance");
							}

							else
							{
							  buf.replace(buf.indexOf("{COMPLIANCE}"),buf.indexOf("{COMPLIANCE}")+12,"Compliance");

							}
							
							/// Compliance status :: Calculation ends
							
							
							
  						}
  						out.println(buf.toString().replace("{YEARSPAN}",""+yearcount));
  					}
                    
                    
                    }catch(Exception ex){
                    ex.printStackTrace();
                    	out.println(ex);
                    }
  %>

</table>
<hr color="#111111" >
<table width="100%" style="border-collapse: collapse" cellpadding="0" cellspacing="0" height="16">
<tr>

 <td width="900" height="16">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=com.eespc.tracking.util.UtilityObject.convertToString(new java.util.Date())%></span>
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