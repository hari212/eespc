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

<%String na="-";
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
%>
<html>
<head>
<title>New Page 1</title>
</head>

<body>
<table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" height="63">

<tr>
<td>
      <table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" cellpadding="7" height="88">
    <tr>
    <td width="1033" colspan="2">
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    
    <%
    String str="";
    try
{
Connection conn;

conn = utilObj.getConnection();

 
			PreparedStatement st=conn.prepareStatement("select clientname from facility where facilityid=?");
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
            out.println(str);
    %>
    </span>
<br>
   
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    Environmental Compliance Assessment Manual</span></a></td></tr>
      


 

 <tr>
    <td width="1033" colspan="2">
<p align="center"><b><font color="#01688A">EXHIBIT 17:MASTER REPORT FOR '<%=str.toUpperCase()%>' </font></b></td></tr>

     

  <tr>
    <td width="1033" colspan="2">
    <hr>
</td>

<br>
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" >
  <tr>
    <td width="100%">
    
    
    
   <tr><td colspan="2" width="100%"> 
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" height="63">
  <tr>
    <td width="100%" bgcolor="#01688A" align="left" colspan="8" height="19"><b>
    <font color="#FFFFFF" size="2">FACADE INFORMATION</font></b></td>
  </tr>
  <tr>
    <td width="5%" height="15" bgcolor="#01688A" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">No</font></b></td>
    <td width="21%" height="15" bgcolor="#01688A" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">Building Name</font></b></td>
    <td width="22%" height="15" bgcolor="#01688A" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">Address</font></b></td>
    <td width="10%" height="15" bgcolor="#01688A" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">Bin No</font></b></td>
    <td width="10%" height="15" bgcolor="#01688A" align="center">
    <font size="1" color="#FFFFFF">
    <b>Block No.</b></font></td>
    <td width="9%" height="15" bgcolor="#01688A" align="center">
    <font size="1" color="#FFFFFF">
    <b>LOT No.</b></font></td>
    <td width="11%" height="15" bgcolor="#01688A" align="center">
    <font size="1" color="#FFFFFF">
    <b>No of Stories</b></font></td>
    <td width="12%" height="15" bgcolor="#01688A" align="center">
    <font size="1" color="#FFFFFF">
    <b>Facade Info.</b></font></td>
  </tr>
  
  <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("SELECT d.buildingname,a.address1,d.dobbinnumber,d.blocknumber,d.lotnumber,d.bldgsixstories FROM building d,facility f,address a WHERE a.addressid=d.addressid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	String s="*";	
            	%>
            	
            
            	
  <tr>
    <td width="5%" height="19"><font size="1"><%=i%></font></td>
    <td width="21%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font></td>
    <td width="22%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("address1"),"N/A")%></font></td>
    <td width="10%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("dobbinnumber"),"N/A")%></font></td>
    <td width="10%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("blocknumber"),"N/A")%></font></td>
    <td width="9%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("lotnumber"),"N/A")%></font></td>
    
        <%
    String s1=rs1.getString("bldgsixstories");
    int a=Integer.parseInt(s1);
    if(a<=5)
    {
    s="Not Required";
    }
    else
    {
    s="Required";
    }
    %>
    <td width="11%" height="19"><font size="1"><%=checkNullAndFill(s1,"N/A")%></font></td>
    
    <td width="12%" height="19"><font size="1"><%=s%></font></td>
  </tr>


<%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>


  </table>  
    
    
    
    </td>
  </tr>
  <tr>
    <td width="100%"><font size="1">1. 5th and 6th cycle FAÇADE information needs to be done.<br>
    2. ECB hearing was on 1/9/06. Minimum penalty will be issued and need to 
    submit FAÇADE inspection report for 5th and 6th cycle.<br>3. Time to submit 
    6th cycle FAÇADE inspection report. </font>
   </td>
  </tr>
</table>
</td></tr>
<p>&nbsp;</p>

<tr><td colspan="2" width="1033">
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber8">
  <tr>
    <td width="100%">
       
 <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" height="89">
  <tr>
    <td width="100%" bgcolor="#01688A" colspan="5" height="19"><b>
    <font color="#FFFFFF" size="2">GENERATORS</font></b></td>
  </tr>
  <tr>
    <td width="13%" bgcolor="#01688A" rowspan="2" height="40" align="center">
    <p align="center"><b><font face="Times New Roman" size="1" color="#FFFFFF">No</font></b></td>
    <td width="30%" bgcolor="#01688A" rowspan="2" height="40" align="center">
    <p align="center"><b><font size="1" color="#FFFFFF">Manufacturer</font></b></td>
    <td width="36%" bgcolor="#01688A" colspan="2" height="19" align="center">
    <p align="center"><b><font face="Times New Roman" size="1" color="#FFFFFF">DEP</font></b></td>
    <td width="21%" bgcolor="#01688A" rowspan="2" height="40" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">Action Taken</font></b></td>
  </tr>
  <tr>
    <td width="17%" bgcolor="#01688A" height="21" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">Permit No</font></b></td>
    <td width="19%" bgcolor="#01688A" height="21" align="center"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">Expiration Date</font></b></td>
  </tr>
  
  <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("select blr.actiontaken,blr.manufacturer,blr.dep,bpf.expirationdate from  building bld, generator blr left join generatorpermitinfo bpf on blr.generatorid=bpf.generatorid where (blr.STATUS='1' or blr.STATUS='5') and blr.buildingid=bld.buildingid and bld.facilityid=? order by blr.generatorid asc, blr.buildingid asc, bpf.gnrtpermitid  asc");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	String s="-";	
            	%>
       	

    <tr>
    <td width="13%" height="19" align="center"><font size="1"><%=i%></font></td>
    <td width="30%" height="19" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("manufacturer"),"N/A")%></font></td>
    <td width="17%" height="19" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("dep"),"N/A")%></font></td>
    <td width="19%" height="19" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("expirationdate"),"N/A")%></font></td>
   <td width="21%" height="19" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("actiontaken"),"N/A")%></font></td>
  </tr>
  
  <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>

    
    </td>
  </tr>
  <tr>
    <td width="100%">&nbsp;</td>
  </tr>
</table>
</td></tr>
<p>&nbsp;</p>
<tr><td colspan="2" width="100%">
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber14">
  <tr>
    <td width="100%">
    
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber13">
  <tr>
    <td width="100%" colspan="11" bgcolor="#01688A"><b>
    <font size="2" color="#FFFFFF">STERILIZERS</font></b></td>
  </tr>
  <tr>
    <td width="10%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">No</font></b></td>
    <td width="18%" colspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Sterilizer</font></b></td>
    <td width="18%" colspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Abator</font></b></td>
    <td width="18%" colspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">DEP Permit</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">DOB Permit</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">DEC Title V 
    included</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Abator Test 
    Date</font></b></td>
    <td width="9%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Action Taken</font></b></td>
  </tr>
  <tr>
    <td width="9%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Make</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Model</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Make</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Model</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Installation</font></b></td>
    <td width="9%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Expiration&nbsp; Date</font></b></td>
  </tr>
  <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("select e.actiontaken,f.facilitytype,e.MODEL,e.MANUFACTURER,e.MODEL,e.dep,e.dob,b.BUILDINGNAME,et.TESTDATE,ep.EXPIRATIONDATE from (eto e,building b,facility f) left join etopermitinfo ep on ep.ETOID = e.ETOID and depid='4' left join etotestinfo et on et.ETOID = e.ETOID where (e.MODIFIEDINPAST='1' or e.MODIFIEDINPAST='5') and e.BUILDINGID = b.BUILDINGID AND b.FACILITYID=f.facilityid and f.facilityid=?");
			st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            String typ=rs1.getString("facilitytype");
            String yes="";
            if(typ.equals("1"))
            {
            	yes="Y";
            }
            else
            {
            	yes="N";
            }
            
            	String s="-";	
            	%>

  <tr>
    <td width="10%"><font size="1"><%=i%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("MANUFACTURER"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("MODEL"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("MANUFACTURER"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("MODEL"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("dep"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFilldate(rs1.getString("EXPIRATIONDATE"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("dob"),"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFill(yes,"N/A")%></font>&nbsp;</td>
    <td width="9%"><font size="1"><%=checkNullAndFilldate(rs1.getString("TESTDATE"),"N/A")%></font>&nbsp;</td>
     <td width="9%"><font size="1"><%=checkNullAndFill(rs1.getString("ACTIONTAKEN"),"N/A")%></font>&nbsp;</td>  </tr>
  <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>

</table>

    
    </td>
  </tr>
  <tr>
    <td width="100%"><font size="1">1. Need to apply for permit from the NYCDOB</font></td>
  </tr>
</table>
</td></tr>
<p>&nbsp;</p>
<p>&nbsp;</p>
<tr><td colspan="2" width="1033">
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber9">
  <tr>
    <td width="100%">
    
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="47">
  <tr>
    <td width="100%" colspan="7" height="19" bgcolor="#01688A"><b>
    <font size="2" color="#FFFFFF">BOILERS</font></b></td>
  </tr>
  <tr>
    <td width="10%" rowspan="2" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">No</font></b></td>
    <td width="20%" rowspan="2" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">Manufacturer</font></b></td>
    <td width="12%" rowspan="2" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">Serial No.</font></b></td>
    <td width="28%" colspan="2" height="12" align="center" bgcolor="#01688A"><b>
    <font face="Times New Roman" size="1" color="#FFFFFF">DEP</font></b></td>
    <td width="15%" rowspan="2" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">Comments</font></b></td>
    <td width="15%" rowspan="2" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">Stack Test</font></b></td>
  </tr>
  <tr>
    <td width="14%" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">Permit No.</font></b></td>
    <td width="14%" height="17" align="center" bgcolor="#01688A"><b>
    <font size="1" face="Times New Roman" color="#FFFFFF">Expiration Date</font></b></td>
  </tr>
  
   <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("select blr.comments,blr.stacktestdate,blr.manufacturer,blr.serial,blr.dep,bpf.expirationdate,blr.protocolsubmitdate from  building bld, boiler blr left join boilerpermitinfo bpf on blr.boilerid=bpf.boilerid and bpf.depid='1' where (blr.MODIFIEDINPAST='1' or blr.MODIFIEDINPAST='5') and blr.buildingid=bld.buildingid and bld.facilityid=? order by blr.boilerid asc, blr.buildingid asc, bpf.boilerpermitid asc");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	String s="-";	
            	%>

  
  <tr>
    <td width="10%" height="31"><font size="1"><%=i%></font></td>
    <td width="20%" height="31"><font size="1"><%=checkNullAndFill(rs1.getString("manufacturer"),"N/A")%></font></td>  
    <td width="12%" height="31"><font size="1"><%=checkNullAndFill(rs1.getString("serial"),"N/A")%></font></td>    
    <td width="14%" height="31"><font size="1"><%=checkNullAndFill(rs1.getString("dep"),"N/A")%></font></td>
    <td width="14%" height="31"><font size="1"><%=checkNullAndFilldate(rs1.getString("expirationdate"),"N/A")%></font></td>
    <td width="15%" height="31"><font size="1"><%=checkNullAndFill(rs1.getString("comments"),"N/A")%></font></td> 
    <td width="15%" height="31"><font size="1"><%=checkNullAndFilldate(rs1.getString("stacktestdate"),"N/A")%></font></td>
 </tr>
  
  <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>


</table>
    
    
    </td>
  </tr>
  <tr>
    <td width="100%"><font size="1">DOB inspection and Boiler Annual Tune up dates have to be 
    confirmed.<br>Opacity Monitor </font> </td>
  </tr>
</table>
</td></tr>
<tr><td colspan="2" width="100%">
<br>
<br>
<br>
<br>
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber10">
  <tr>
    <td width="100%">
    
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber4" height="23">
  <tr>
    <td width="100%" colspan="4" bgcolor="#01688A" height="1"><b>
    <font size="2" color="#FFFFFF">CHILLERS</font></b></td>
  </tr>
  <tr>
    <td width="10%" align="center" bgcolor="#01688A" height="12"><b>
    <font size="1" color="#FFFFFF">No</font></b></td>
    <td width="32%" align="center" bgcolor="#01688A" height="12"><b>
    <font size="1" color="#FFFFFF">Make</font></b></td>
    <td width="29%" align="center" bgcolor="#01688A" height="12"><b>
    <font size="1" color="#FFFFFF">Serial 
    No.</font></b></td>
    <td width="29%" align="center" bgcolor="#01688A" height="12"><b>
    <font size="1" color="#FFFFFF">DOB 
    Approval</font></b></td>
  </tr>
  <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("SELECT a.make,a.serial,a.dobapproval FROM building d,facility f,chillerabsorber a WHERE a.MODIFIEDINPAST='Y' and a.buildingid=d.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            		
            	%>

  <tr>
    <td width="10%" height="19"><font size="1"><%=i%></font>&nbsp;</td>
    <td width="32%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("make"),"N/A")%></font>&nbsp;</td>
    <td width="29%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("serial"),"N/A")%></font>&nbsp;</td>
    <td width="29%" height="19"><font size="1"><%=checkNullAndFill(rs1.getString("dobapproval"),"N/A")%></font>&nbsp;</td>
  </tr>
  <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>


</table>

    
    </td>
  </tr>
  <tr>
    <td width="100%">&nbsp;</td>
  </tr>
</table>
</td></tr>

<p>&nbsp;</p>
<tr><td colspan="2" width="1033">
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber16">
  <tr>
    <td width="100%">
    
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber15">
  <tr>
    <td width="100%" colspan="7" bgcolor="#01688A"><b>
    <font size="2" color="#FFFFFF">RPZ's</font></b></td>
  </tr>
  <tr>
    <td width="10%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">No</font></b></td>
    <td width="15%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Serial No.</font></b></td>
    <td width="17%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">NYCDOB No.</font></b></td>
    <td width="16%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">NYCDEP No.</font></b></td>
    <td width="25%" colspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Inspection Date</font></b></td>
    <td width="17%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Expiration Date</font></b></td>
  </tr>
  <tr>
    <td width="13%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Last</font></b></td>
    <td width="12%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Next</font></b></td>
  </tr>
  
   <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("select a.serialnumber,a.dobpermitnumber,a.deppermitnumber,c.lastinspectiondate,c.nextinspectiondate,b.expirationdate from building x,rpz a left join rpzinspectioninfo c  on c.rpzid=a.rpzid left join rpzpermitinfo b on b.rpzid=a.rpzid where a.buildingid=x.buildingid and x.facilityid=?");
            st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            		
            	%>


  
  <tr>
    <td width="10%"><font size="1"><%=i%></font>&nbsp;</td>
    <td width="15%"><font size="1"><%=checkNullAndFill(rs1.getString("serialnumber"),"N/A")%></font>&nbsp;</td>
    <td width="17%"><font size="1"><%=checkNullAndFill(rs1.getString("dobpermitnumber"),"N/A")%></font>&nbsp;</td>
    <td width="16%"><font size="1"><%=checkNullAndFill(rs1.getString("deppermitnumber"),"N/A")%></font>&nbsp;</td>
    <td width="25%"><font size="1"><%=checkNullAndFilldate(rs1.getString("lastinspectiondate"),"N/A")%></font>&nbsp;</td>
    <td width="17%"><font size="1"><%=checkNullAndFilldate(rs1.getString("nextinspectiondate"),"N/A")%></font>&nbsp;</td>
    <td width="17%"><font size="1"><%=checkNullAndFilldate(rs1.getString("expirationdate"),"N/A")%></font>&nbsp;</td>

  </tr>
  
   <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>

  
</table>

    
    </td>
  </tr>
  <tr>
    <td width="100%"><font size="1">* Annual periodic inspection by a licensed plumber is to be 
    done and reported to DEP along with DOB/DEP approval.<br>Plumber Info: Stephen 
    Ferdinando Ph. No. 718 273 7970</font></td>
  </tr>
</table>
</td></tr>
<p>&nbsp;</p>
<tr><td colspan="2" width="1033">
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber11">
  <tr>
    <td width="100%">
    
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber5">
  <tr>
    <td width="100%" colspan="6" bgcolor="#01688A"><b>
    <font size="2" color="#FFFFFF">STORAGE TANKS</font></b></td>
  </tr>
  <tr>
    <td width="14%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Tank No.</font></b></td>
    <td width="20%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Capacity</font></b></td>
    <td width="19%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Tank Type</font></b></td>
    <td width="29%" colspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Test Date</font></b></td>
    <td width="18%" rowspan="2" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Action&nbsp; Taken</font></b></td>
  </tr>
  <tr>
    <td width="14%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Last</font></b></td>
    <td width="15%" bgcolor="#01688A" align="center"><b>
    <font size="1" color="#FFFFFF">Next</font></b></td>
  </tr>
  
    <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("select g.facilitydesignatedid,s.tankstatus,g.capacity,c.tanktype,d.testdate,d.nexttestdate from (building a,storagetank g) left join tanktype c on c.id=g.tanktype left join tanktightnessinfo d on g.STORAGETANKID=d.storagetankid left join tankstatus s on g.tankstatus=s.statusid where (g.TANKSTATUS='1' or g.TANKSTATUS='5') and g.buildingid=a.buildingid and a.facilityid=?");
			st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	String s="NA";	
            	%>
  <tr>
    <td width="14%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("facilitydesignatedid"),"N/A")%></font>&nbsp;</td>
    <td width="20%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("capacity"),"N/A")%></font>&nbsp;</td>
    <td width="19%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("tanktype"),"N/A")%></font>&nbsp;</td>
    <td width="14%" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("testdate"),"N/A")%></font>&nbsp;</td>
    <td width="15%" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("nexttestdate"),"N/A")%></font>&nbsp;</td>
    <td width="18%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("tankstatus"),"N/A")%></font>&nbsp;</td>
  </tr>
  
  
   <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>


  
</table>

    
    </td>
  </tr>
  <tr>
    <td width="100%"><font size="1">Registrations of all PSB's to be renewed by 07/07/07<br>
    Check if a copy of UST is registered with the FIRE DEPT. - David Blass
    <br>Replacement of 550 gallon tank.</font></td>
  </tr>
</table>
</td></tr>
<p>&nbsp;</p>

<p>&nbsp;</p>
<tr><td colspan="2" width="100%">
<table border="0" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber12">
  <tr>
    <td width="100%">
    
    <table border="1" cellspacing="1" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber6">
  <tr>
    <td width="100%" colspan="9" bgcolor="#01688A"><b>
    <font size="2" color="#FFFFFF">BRIDGES AND TUNNELS</font></b></td>
  </tr>
  <tr>
    <td width="10%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Type</font></b></td>
    <td width="16%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Location</font></b></td>
    <td width="17%" colspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">DOT</font></b></td>
    <td width="23%" colspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Insurance</font></b></td>
    <td width="21%" colspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Inspection Test Date</font></b></td>
    <td width="13%" rowspan="2" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Action Taken</font></b></td>
  </tr>
  <tr>
    <td width="8%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">No</font></b></td>
    <td width="9%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">File No.</font></b></td>
    <td width="12%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Effective</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">
    Expiration</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Last
    </font></b></td>
    <td width="10%" align="center" bgcolor="#01688A"><b>
    <font size="1" color="#FFFFFF">Next
    </font></b></td>
  </tr>
  
   <%
try
{
int i=1;
// get a database connection
Connection conn;

conn = utilObj.getConnection();

 
PreparedStatement st1=conn.prepareStatement("select b.permitnumber,b.FileNo,c.actiontaken,d.type,a.buildingname,b.issuedate,b.expirationdate,b.lastinspdate,b.nextinspdate from (building a,bridgetunnels c,bridgetype d) left join bridgetunnelpermitinfo b on c.bridgetunnelid=b.bridgetunnelid and b.depid='9' where (c.MODIFIEDINPAST='1' or c.MODIFIEDINPAST='5') and c.isbridge=d.che and c.buildingid=a.buildingid and a.facilityid=?");
			st1.setString(1,String.valueOf(TestFacility.getCurrentFacilityID()));
            ResultSet rs1=st1.executeQuery();
            while(rs1.next())
            {
            	String s="N/A";	
            	%>

  
  <tr>
    <td width="10%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("type"),"N/A")%></font>&nbsp;</td>
    <td width="16%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("buildingname"),"N/A")%></font>&nbsp;</td>
    <td width="8%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("permitnumber"),"N/A")%></font>&nbsp;</td>
    <td width="9%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("FileNo"),"N/A")%></font>&nbsp;</td>
    <td width="12%" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("issuedate"),"N/A")%></font>&nbsp;</td>
    <td width="11%" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("expirationdate"),"N/A")%></font>&nbsp;</td>
    <td width="11%" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("lastinspdate"),"N/A")%></font>&nbsp;</td>
    <td width="10%" align="center"><font size="1"><%=checkNullAndFilldate(rs1.getString("nextinspdate"),"N/A")%></font>&nbsp;</td>
    <td width="13%" align="center"><font size="1"><%=checkNullAndFill(rs1.getString("actiontaken"),"N/A")%></font>&nbsp;</td>
  </tr>
  
  
   <%         
i=i+1;   	
            }
            
 

}
catch(Exception e)
{
}	
%>

  
</table>
    
    </td>
  </tr>
  <tr>
    <td width="100%"><font size="1">* Bridge removed<br>** No inspection was 
    conducted as the hospital requested to inspect only 2 tunnels</font></td>
  </tr>
</table>

</td></tr>
</td>
  </tr>
 
     
</table>
</tr>
</td>
</tr>
</table>

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
</table>
</body>

</html>