<html>
<%@ page language ="java" import = "sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.math.BigDecimal, java.sql.*,java.text.*,com.eespc.tracking.bo.FacilityVo" %> 

<head>
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 25</title>
</head>
<%
DecimalFormat df1 = new DecimalFormat("0000.000");
DecimalFormat df = new DecimalFormat("0.000000");
DecimalFormat df2 = new DecimalFormat("0.0");
Calendar now = Calendar.getInstance();
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");
int month=now.get(Calendar.MONTH);
int year=now.get(Calendar.YEAR);
FacilityVo facilityvo = (FacilityVo)session.getAttribute("REPORT_FACILITY_VO");
%>
<p align="left"><img border="0" src="images/pdf.gif" width="16" height="16">
<a href="jsp/Exhibit-25xls.jsp" target="_blank"><img border="0" src="images/xls.gif" width="16" height="16"></a>&nbsp;&nbsp;
<img border="0" src="images/first_grey.gif" width="23" height="23"><img border="0" src="images/previous_grey.gif" width="23" height="23"><img border="0" src="images/next_grey.gif" width="23" height="23"><img border="0" src="images/last_grey.gif" width="23" height="23"></p>

<span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">	
<%
    String str="";
    Connection conn= null;            
    PreparedStatement st= null;
    ResultSet rs = null;
    try
{


conn = utilObj.getConnection();

 
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
            }finally
        	{
	        	SqlUtil.close(rs);
           	 	SqlUtil.close(st);
             	SqlUtil.close(conn);         
			}
            out.println(str);
    %>	
    </span> <br>
    <span style="FONT-SIZE: 9px; COLOR: #01688a; FONT-FAMILY: Arial">
    EESPC Compliance Tracking Software (EESCTS)</span>
    <br>
<body>
<p align="center"><font color="#01688A"><b>REPORT 25: SUMMARY OF 12-MONTH 
ROLLING CONSUMPTION HISTORY FOR BOILER FOR '<%=str.toUpperCase()%>'</b></font><hr>
<%
					List printlist=fromseventh.getNycdobStatusReport25(facilityvo.getId());
                    int psize =0;
					Hashtable hashtable= new Hashtable();
					if (printlist != null)
					{
						psize = printlist.size();
						for (int i=0; i < psize; i++)
						{
						hashtable=(Hashtable)printlist.get(i);
						//out.println("BOILER ID: "+(String)hashtable.get("FACILITYDESIGNATEDID")+"<br>");
						Hashtable gminhastable=(Hashtable)hashtable.get("GASMAX");
						Hashtable ominhastable=(Hashtable)hashtable.get("OILMAX");
						Hashtable gasconsumption=(Hashtable)hashtable.get("GASCON");
						Hashtable oilconsumption=(Hashtable)hashtable.get("OILCON");
						String gmaxyear=(String)gminhastable.get("MAX");
						String gminyear=(String)gminhastable.get("MIN");
						
						String omaxyear=(String)ominhastable.get("MAX");
						String ominyear=(String)ominhastable.get("MIN");
						
						String B_SO2ALLOWABLE=(String)hashtable.get("B_SO2ALLOWABLE");
						String B_NOXALLOWABLE=(String)hashtable.get("B_NOXALLOWABLE");
						
						String GASFUELGAPPING=(String)hashtable.get("GASFUELGAPPING");
						String OILFUELGAPPING=(String)hashtable.get("OILFUELGAPPING");
						
						String OILEMMISSIONFACTOR=(String)hashtable.get("OILEMMISSIONFACTOR");						
						String GASEMMISSIONFACTOR=(String)hashtable.get("GASEMMISSIONFACTOR");
						
						String B_gasso2=(String)hashtable.get("B_gasso2");						
						String B_oilso2=(String)hashtable.get("B_oilso2");
               
						int omin=0;
						int gmin=0;
						if(!gminyear.equals("-"))
						{
							if(Integer.parseInt(gminyear)<=year);
							{
							gmin=Integer.parseInt(gminyear);
							}
						}
						
						if(!ominyear.equals("-"))
						{
							if(Integer.parseInt(ominyear)<=year);
							{
							omin=Integer.parseInt(ominyear);
							}
						}
						
						
						
						int omax=0;
						int gmax=0;
						if(!gmaxyear.equals("-"))
						{
							if(Integer.parseInt(gmaxyear)<=year);
							{
							gmax=Integer.parseInt(gmaxyear);
							}
						}
						
						if(!omaxyear.equals("-"))
						{
							if(Integer.parseInt(omaxyear)<=year);
							{
							omax=Integer.parseInt(omaxyear);
							}
						}
						//out.println("GMAX: "+(String)gminhastable.get("MAX")+"<br>");
						//out.println("GMIN: "+(String)gminhastable.get("MIN")+"<br>");
						//out.println("OMAX: "+(String)ominhastable.get("MAX")+"<br>");
						//out.println("OMIN: "+(String)ominhastable.get("MIN")+"<br>");
						
						%>
<br>
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" height="144">
  <tr>
    <td width="20%" align="center" bgcolor="#669999" colspan="2" height="24">
    <p align="left"><b><font size="2" face="Verdana" color="#0000FF">&nbsp;</font><font size="2" face="Verdana" color="#CCFFFF">BOILER&nbsp;<%=i+1%> :
    </font></b> </td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">Gas Rolling Limit (CFY)</font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24"><b>
    <font face="Verdana" size="1" color="#CCFFFF">Oil Rolling Limit (GPY)</font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">NOX Rolling Limit (Tons/Yr)</font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">SO2 Rolling Limit (Tons/Yr)</font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">NOX Emission Factor (GAS) (lb/million cu.ft.)
    </font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">NOX Emission Factor (OIL) (lb/1000 gal)</font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">SO2 Emission Factor (GAS) (lb/million cu.ft)</font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="24">
    <b><font face="Verdana" size="1" color="#CCFFFF">SO2 Emission Factor (OIL) 
    (lb/1000 gal)</font></b></td>
  </tr>
  <tr>
    <td width="20%" align="center" bgcolor="#669999" colspan="2" height="20">
    <p align="left"><b><font color="#FFFFFF" face="Verdana" size="2"><%=(String)hashtable.get("FACILITYDESIGNATEDID")%></font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font face="Verdana" color="#FFFFFF" size="1"><%=GASFUELGAPPING%></font></b>&nbsp;</td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font size="1" face="Verdana" color="#FFFFFF">&nbsp;<%=OILFUELGAPPING%></font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font face="Verdana" color="#FFFFFF" size="1"><%=B_NOXALLOWABLE%></font></b>&nbsp;</td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font size="1" face="Verdana" color="#FFFFFF">&nbsp;<%=B_SO2ALLOWABLE%></font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    &nbsp;<b><font size="1" face="Verdana" color="#FFFFFF"><%=GASEMMISSIONFACTOR%></font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font size="1" face="Verdana" color="#FFFFFF">&nbsp;<%=OILEMMISSIONFACTOR%></font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font size="1" face="Verdana" color="#FFFFFF">&nbsp;<%=B_gasso2%></font></b></td>
    <td width="10%" align="center" bgcolor="#669999" height="20">
    <b><font size="1" face="Verdana" color="#FFFFFF">&nbsp;<%=B_oilso2%></font></b></td>
  </tr>
   <tr>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Month-Year</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly Fuel Oil (gal)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly Natural gas (mmcuft.)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">12-Month Rolling Fuel Oil 
    (gal)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">12-Month Rolling Natural gas (mmcuft.)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly NOX (TPY)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">12-Month Rolling NOX&nbsp; 
    (TPY)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly SO2 (TPY)</font></b></td>
    <td width="12%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">12-Month Rolling SO2&nbsp; 
    (TPY)</font></b></td>
     <td width="12%" align="center" bgcolor="#01688A" height="64"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status&nbsp; 
    </font></b></td>
  </tr>
  <%
  //out.println(omin+"-"+gmin);
  int from=0;
  if(gmin > omin)
  {
  from=gmin;
  }
  else
  {
  from=omin;
  }
  
  
  int to=0;
  if(gmax > omax)
  {
  to=gmax;
  }
  else
  {
  to=omax;
  }
  
  // out.println(omin+"-"+gmin+"="+from);
  
float oprev[]=new float[12];
                oprev[0]=0;
                oprev[1]=0;
                oprev[2]=0;
                oprev[3]=0;
                oprev[4]=0;
                oprev[5]=0;
                oprev[6]=0;
                oprev[7]=0;
                oprev[8]=0;
                oprev[9]=0;
                oprev[10]=0;
                oprev[11]=0;
float ocurrent[]=new float[12];
                ocurrent[0]=0;
                ocurrent[1]=0;
                ocurrent[2]=0;
                ocurrent[3]=0;
                ocurrent[4]=0;
                ocurrent[5]=0;
                ocurrent[6]=0;
                ocurrent[7]=0;
                ocurrent[8]=0;
                ocurrent[9]=0;
                ocurrent[10]=0;
                ocurrent[11]=0;
                
float gprev[]=new float[12];
                gprev[0]=0;
                gprev[1]=0;
                gprev[2]=0;
                gprev[3]=0;
                gprev[4]=0;
                gprev[5]=0;
                gprev[6]=0;
                gprev[7]=0;
                gprev[8]=0;
                gprev[9]=0;
                gprev[10]=0;
                gprev[11]=0;
float gcurrent[]=new float[12];
                gcurrent[0]=0;
                gcurrent[1]=0;
                gcurrent[2]=0;
                gcurrent[3]=0;
                gcurrent[4]=0;
                gcurrent[5]=0;
                gcurrent[6]=0;
                gcurrent[7]=0;
                gcurrent[8]=0;
                gcurrent[9]=0;
                gcurrent[10]=0;
                gcurrent[11]=0;
               

  
  if(from!=0)
  {
  		for(int j=from;j<=to;j++)
  		{
  		
  				float gcons[]=new float [12];
                gcons[0]=0;
                gcons[1]=0;
                gcons[2]=0;
                gcons[3]=0;
                gcons[4]=0;
                gcons[5]=0;
                gcons[6]=0;
                gcons[7]=0;
                gcons[8]=0;
                gcons[9]=0;
                gcons[10]=0;
                gcons[11]=0;
                
                float ocons[]=new float [12];
                ocons[0]=0;
                ocons[1]=0;
                ocons[2]=0;
                ocons[3]=0;
                ocons[4]=0;
                ocons[5]=0;
                ocons[6]=0;
                ocons[7]=0;
                ocons[8]=0;
                ocons[9]=0;
                ocons[10]=0;
                ocons[11]=0;
                
                if(gasconsumption.get(""+j)!=null)
                {
                gcons=(float[])gasconsumption.get(""+j);
                }
                if(oilconsumption.get(""+j)!=null)
                {
                ocons=(float[])oilconsumption.get(""+j);
                }
	gprev=gcurrent;
	gcurrent=gcons;

	oprev=ocurrent;
	ocurrent=ocons;
  		
  		String montharray[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
  			for(int k=0;k<=11;k++)
  				{
  		float oilroll=0;
  		float gasroll=0;		
		float bgasmillioncubic=gcons[k]/1000000; 		
		
  		for(int l=0;l<=k;l++)
  		{ 
  		 gasroll=gasroll+gcurrent[l];
  		 oilroll=oilroll+ocurrent[l];		
  		}
  		
		if(k!=11)
  		{
  		
	for(int m=(k+1);m<=11;m++)
  		{ 
  		gasroll=gasroll+gprev[m];
  		oilroll=oilroll+oprev[m];
  		}
  		
  		}		
    
  %>
   <% if(ocons[k]>=0 && bgasmillioncubic >0)
 {
	 %>
   <tr>
    <td width="11%" height="16"><font face="Verdana" size="2"><%=j+"-"+montharray[k]%>&nbsp;</font></td>
    <td width="11%" align="center" height="16"><font face="Verdana" size="2"><%=ocons[k]%>&nbsp;</font></td>
    <td width="11%" align="center" height="16"><font face="Verdana" size="2"><%=bgasmillioncubic%>&nbsp;</font></td>    
    <td width="11%" align="center" height="16"><font face="Verdana" size="2">&nbsp;<%=oilroll%></font></td>
    <td width="11%" align="center" height="16"><font face="Verdana" size="2">&nbsp;<%=(gasroll/1000000)%></font></td>
    <td width="11%" align="center" height="16"><font size="2" face="Verdana">
    <%
    
    float gasnox=0;
    float oilnox=0;
    //out.println(OILEMMISSIONFACTOR+"-"+GASEMMISSIONFACTOR);
    if(!OILEMMISSIONFACTOR.equals("-"))
    { 
    oilnox=Float.parseFloat(OILEMMISSIONFACTOR); //B
    }
    if(!GASEMMISSIONFACTOR.equals("-"))
    {
    gasnox=Float.parseFloat(GASEMMISSIONFACTOR); //A  
    }

   float a=ocons[k]*oilnox;
   float b=a/1000;
   float c=gcons[k]*gasnox;
   float d=c/1000000;
   float monthlynox=(b+d)/2000; 
   
   float at=oilroll*oilnox;
   float bt=at/1000;
   float ct=gasroll*gasnox;
   float dt=ct/1000000;
   float tmonthlynox=(bt+dt)/2000; 

   
 out.println(""+df.format(monthlynox));
    %>
    &nbsp;</font></td>
    <td width="11%" align="center" height="16"><font size="2" face="Verdana"><%=df.format(tmonthlynox)%>&nbsp;</font></td>
    <td width="11%" align="center" height="16"><font size="2" face="Verdana">
    <%
    
 
    
    float gasso2=0;
    float oilso2=0;
    
    if(!B_oilso2.equals("-"))
    { 
    oilso2=Float.parseFloat(B_oilso2); //D
    }
    if(!B_gasso2.equals("-"))
    {
    gasso2=Float.parseFloat(B_gasso2); //C  
    }

   float p=ocons[k]*oilso2;
   float q=p/1000;
   float r=gcons[k]*gasso2;
   float s=r/1000000;
   float monthlyso2=(q+s)/2000; 
   
   float pt=oilroll*oilso2;
   float qt=pt/1000;
   float rt=gasroll*gasso2;
   float stObj=rt/1000000;
   float tmonthlyso2=(qt+stObj)/2000; 

   
 out.println(""+df.format(monthlyso2));
    %>
    &nbsp;</font></td>
    <td width="12%" align="center" height="16"><font size="2" face="Verdana"><%=df.format(tmonthlyso2)%>&nbsp;</font></td>
     <td width="12%" align="center" height="16"><font size="2" face="Verdana">
     <%
     int gasall=0;
     int oilall=0;
     float noxall=0;
     float so2all=0;
 if(!GASFUELGAPPING.equals("-"))
    { 
    gasall=Integer.parseInt(GASFUELGAPPING);
    }
 if(!OILFUELGAPPING.equals("-"))
    { 
    oilall=Integer.parseInt(OILFUELGAPPING);
    }
if(!B_NOXALLOWABLE.equals("-"))
    {
    noxall=Float.parseFloat(B_NOXALLOWABLE); 
    }
if(!B_SO2ALLOWABLE.equals("-"))
    {
    so2all=Float.parseFloat(B_SO2ALLOWABLE); 
    }
   
if(gasall<gasroll)
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}

else if(oilall<oilroll)
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}

else if(noxall<tmonthlynox)
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}

else if(so2all<tmonthlyso2)
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}
else
{
out.println("Compliance");
}

 }%>
     
     
  </font></td>
  </tr>
  <%
  				}
  		}
  }
  else
  {
  %>

  <tr>
    <td width="100%" colspan="10" height="16">
    <p align="center"><b><font face="Verdana" size="2">No Results</font></b></td>
  </tr>

  <%
  }
  %>
  </table>
  <%
  }
}
						
%>  

<hr color="#0000FF">
<%
					List fprintlist=fromseventh.getNycdobStatusReport25a(facilityvo.getId());
                    int fpsize =0;
					Hashtable fhashtable= new Hashtable();
					if (fprintlist != null)
					{
						fpsize = fprintlist.size();
						for (int i=0; i < fpsize; i++)
						{
						fhashtable=(Hashtable)fprintlist.get(i);				
						Hashtable fgminhastable=(Hashtable)fhashtable.get("GASMAX");
						Hashtable fominhastable=(Hashtable)fhashtable.get("OILMAX");
						Hashtable fgasconsumption=(Hashtable)fhashtable.get("GASCON");
						Hashtable foilconsumption=(Hashtable)fhashtable.get("OILCON");
						String fgmaxyear=(String)fgminhastable.get("MAX");
						String fgminyear=(String)fgminhastable.get("MIN");
						String fomaxyear=(String)fominhastable.get("MAX");
						String fominyear=(String)fominhastable.get("MIN");						
						String FGASFUELGAPPING=(String)fhashtable.get("GASFUELGAPPING");
						String FOILFUELGAPPING=(String)fhashtable.get("OILFUELGAPPING");
						
               
						int fomin=0;
						int fgmin=0;
						if(!fgminyear.equals("-"))
						{
							if(Integer.parseInt(fgminyear)<=year);
							{
							fgmin=Integer.parseInt(fgminyear);
							}
						}
						
						if(!fominyear.equals("-"))
						{
							if(Integer.parseInt(fominyear)<=year);
							{
							fomin=Integer.parseInt(fominyear);
							}
						}
						
						
						
						int fomax=0;
						int fgmax=0;
						if(!fgmaxyear.equals("-"))
						{
							if(Integer.parseInt(fgmaxyear)<=year);
							{
							fgmax=Integer.parseInt(fgmaxyear);
							}
						}
						
						if(!fomaxyear.equals("-"))
						{
							if(Integer.parseInt(fomaxyear)<=year);
							{
							fomax=Integer.parseInt(fomaxyear);
							}
						}


						%>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" height="142">
  <tr>
    <td width="49%" bgcolor="#669999" colspan="3" height="19">
    <p align="left">
    <b><font face="Verdana" size="2" color="#CCFFFF">Facility-Wide Compliance:</font></b></td>
    <td width="17%" bgcolor="#669999" height="19">&nbsp;</td>
    <td width="17%" bgcolor="#669999" align="center" height="19">
    <b><font face="Verdana" size="1" color="#CCFFFF">Gas Rolling Limit (CFY)</font></b></td>
    <td width="17%" bgcolor="#669999" align="center" height="19"><b>
    <font face="Verdana" size="1" color="#CCFFFF">Oil Rolling Limit (GPY)</font></b></td>
  </tr>
  <tr>
    <td width="16%" bgcolor="#669999" height="21">&nbsp;</td>
    <td width="16%" bgcolor="#669999" height="21">&nbsp;</td>
    <td width="17%" bgcolor="#669999" height="21">&nbsp;</td>
    <td width="17%" bgcolor="#669999" height="21">&nbsp;</td>
    <td width="17%" bgcolor="#669999" height="21" align="center">
    <b><font face="Verdana" size="1" color="#CCFFFF"><%=FGASFUELGAPPING%></font></b></td>
    <td width="17%" bgcolor="#669999" height="21" align="center">
    <b><font face="Verdana" size="1" color="#CCFFFF"><%=FOILFUELGAPPING%></font></b></td>
  </tr>
  <tr>
    <td width="11%" align="center" bgcolor="#01688A" height="63"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Month-Year</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="63"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly Fuel Oil (gal)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="63"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Monthly Natural gas (mmcuft.)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="63"><b>
    <font size="2" face="Verdana" color="#FFFFFF">12-Month Rolling Fuel Oil (gal)</font></b></td>
    <td width="11%" align="center" bgcolor="#01688A" height="63"><b>
    <font size="2" face="Verdana" color="#FFFFFF">12-Month Rolling Natural gas 
    (mmcuft.)</font></b></td>
    <td width="17%" bgcolor="#01688A" height="63">
    <p align="center"><b>
    <font size="2" face="Verdana" color="#FFFFFF">Compliance Status&nbsp; 
    </font></b></td>
  </tr>
  <%
						
 int ffrom=0;
  if(fgmin > fomin)
  {
  ffrom=fgmin;
  }
  else
  {
  ffrom=fomin;
  }
   
   
  int to=0;
  if(fgmax > fomax)
  {
  to=fgmax;
  }
  else
  {
  to=fomax;
  }

float foprev[]=new float [12];
                foprev[0]=0;
                foprev[1]=0;
                foprev[2]=0;
                foprev[3]=0;
                foprev[4]=0;
                foprev[5]=0;
                foprev[6]=0;
                foprev[7]=0;
                foprev[8]=0;
                foprev[9]=0;
                foprev[10]=0;
                foprev[11]=0;
float focurrent[]=new float[12];
                focurrent[0]=0;
                focurrent[1]=0;
                focurrent[2]=0;
                focurrent[3]=0;
                focurrent[4]=0;
                focurrent[5]=0;
                focurrent[6]=0;
                focurrent[7]=0;
                focurrent[8]=0;
                focurrent[9]=0;
                focurrent[10]=0;
                focurrent[11]=0;
                
 float fgprev[]=new float [12];
                fgprev[0]=0;
                fgprev[1]=0;
                fgprev[2]=0;
                fgprev[3]=0;
                fgprev[4]=0;
                fgprev[5]=0;
                fgprev[6]=0;
                fgprev[7]=0;
                fgprev[8]=0;
                fgprev[9]=0;
                fgprev[10]=0;
                fgprev[11]=0;
float fgcurrent[]=new float [12];
                fgcurrent[0]=0;
                fgcurrent[1]=0;
                fgcurrent[2]=0;
                fgcurrent[3]=0;
                fgcurrent[4]=0;
                fgcurrent[5]=0;
                fgcurrent[6]=0;
                fgcurrent[7]=0;
                fgcurrent[8]=0;
                fgcurrent[9]=0;
                fgcurrent[10]=0;
                fgcurrent[11]=0;
                 
  if(ffrom!=0)
  {
  		for(int j=ffrom;j<=to;j++)
  		{
  		
  				float fgcons[]=new float[12];
                fgcons[0]=0;
                fgcons[1]=0;
                fgcons[2]=0;
                fgcons[3]=0;
                fgcons[4]=0;
                fgcons[5]=0;
                fgcons[6]=0;
                fgcons[7]=0;
                fgcons[8]=0;
                fgcons[9]=0;
                fgcons[10]=0;
                fgcons[11]=0;
                //
                float focons[]=new float[12];
                focons[0]=0;
                focons[1]=0;
                focons[2]=0;
                focons[3]=0;
                focons[4]=0;
                focons[5]=0;
                focons[6]=0;
                focons[7]=0;
                focons[8]=0;
                focons[9]=0;
                focons[10]=0;
               	focons[11]=0;
                
                if(fgasconsumption.get(""+j)!=null)
                {
                fgcons=(float[])fgasconsumption.get(""+j);
				//String.valueOf(fgcons)
                }
                if(foilconsumption.get(""+j)!=null)
                {
                focons=(float[])foilconsumption.get(""+j);
				
                }
	fgprev=fgcurrent;
	fgcurrent=fgcons;

	foprev=focurrent;
	focurrent=focons;
  		
  		String fmontharray[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
  			for(int k=0;k<=11;k++)
  				{
  		float foilroll=0;
  		float fgasroll=0;
  		float gasmillioncubic=fgcons[k]/1000000; 		
  				
  		for(int l=0;l<=k;l++)
  		{ 
  		 fgasroll=fgasroll+fgcurrent[l];
  		 foilroll=foilroll+focurrent[l];	
  		}
  		
		if(k!=11)
  		{
  		
	for(int m=(k+1);m<=11;m++)
  		{ 
  		fgasroll=fgasroll+fgprev[m];
  		foilroll=foilroll+foprev[m];
  		}  		
  		}    
  %>
 <% if(focons[k]>=0 && gasmillioncubic >0)
 {
	 
	 
	 %>
  <tr>
    <td width="16%" height="19"><font face="Verdana" size="2"><%=j+"-"+fmontharray[k]%>&nbsp;</font></td>
    <td width="16%" height="19"><font face="Verdana" size="2"><%=df2.format(focons[k])%>&nbsp;</font></td>
    <td width="17%" height="19"><font face="Verdana" size="2"><%=gasmillioncubic%>&nbsp;</font></td>
    <td width="17%" height="19"><font face="Verdana" size="2">&nbsp;<%=df2.format(foilroll)%></font></td>
    <td width="17%" height="19"><font face="Verdana" size="2">&nbsp;<%=(fgasroll/1000000)%></font></td>
    <td width="17%" height="19">
    <p align="center"><font face="Verdana" size="2">&nbsp;<% 
    
    float fgasall=0;
    float foilall=0;
    
 if(!FGASFUELGAPPING.equals("-"))
    { 
    fgasall=Float.parseFloat(FGASFUELGAPPING);
    }
 if(!FOILFUELGAPPING.equals("-"))
    { 
    foilall=Float.parseFloat(FOILFUELGAPPING);
    }  
    
    if(fgasall<fgasroll)
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}

else if(foilall<foilroll)
{
out.println("<font color=\"#FF0000\">Non Compliance</font>");
}

else
{
out.println("Compliance");
}	
    
    %></font></td>
  </tr>
  <%}
  				}
  		}
  }
  else
  {
  %>
  <tr>
    <td width="100%" colspan="6" align="center" height="16"><b><font face="Verdana" size="2">No Results</font></b></td>
  </tr>
   <%
  }
  %>
 
</table>
 <%
  }
	}
						
%> 
<br>
<hr color="#000000">
<table width="100%" style="border-collapse: collapse" bordercolor="#111111" cellpadding="0" cellspacing="0">
<tr><td width="602">
 <p align="left">
    <span style="FONT-SIZE: 9px; FONT-FAMILY: Arial"><%=sdf.format(date)%></span>
 </td>
 <td width="93">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  <img border="0" src="images/ees.JPG" width="20" height="18"></SPAN>
 </td>
 
 <td width="197">
  <p align="right"><SPAN 
style="FONT-WEIGHT: bold; FONT-SIZE: 9px; FONT-FAMILY: Arial; BACKGROUND-COLOR: #ffffff">
  Environmental 
Engineering Solutions, P.C.</SPAN></td>
 
 </tr>
</table>
</body>

</html>