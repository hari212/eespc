<html>
<%@ page language ="java" import = "com.eespc.tracking.entity.*,com.eespc.tracking.reports.datasources.factories.*,sevenb.fromseventh,com.eespc.tracking.util.SqlUtil,com.eespc.tracking.util.SqlUtil,java.util.*,java.io.*, java.lang.*, java.sql.*,java.text.*,com.eespc.tracking.bo.FacilityVo" %> 
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.util.SqlUtil,com.eespc.tracking.util.UtilityObject,com.eespc.tracking.entity.*" %>

<head>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 6.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Report 29</title>
<%
String dep="";
String dob="";
FacilityVo facilityvo = (FacilityVo)session.getAttribute("REPORT_FACILITY_VO");
try
{


int borough=facilityvo.getBorough();
if(borough==1)
{
dep="DEP/DOH";
dob="DOB/TOWN/CITY";
}
else
{
dep="DEP";
dob="DOB";
}

}
catch(Exception e)
{
out.println("Eyxception:"+e);
}
%>

<script>
function building()
{
Tip('If Any Non Compliance, Please Refer to Report-1 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function boiler()
{
Tip('If Any Non Compliance, Please Refer to Report-3, 4, 5 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function generator()
{
Tip('If Any Non Compliance, Please Refer to Report-6 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function eto()
{
Tip('If Any Non Compliance, Please Refer to Report-8 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function fumehood()
{
Tip('If Any Non Compliance, Please Refer to Report-15 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function chiller()
{
Tip('If Any Non Compliance, Please Refer to Report-9 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function rpz()
{
Tip('If Any Non Compliance, Please Refer to Report-11 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function storagetank()
{
Tip('If Any Non Compliance, Please Refer to Report-7 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function bulkoxygen()
{
Tip('If Any Non Compliance, Please Refer to Report-19 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function elevator()
{
Tip('If Any Non Compliance, Please Refer to Report-10 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function bridge()
{
Tip('If Any Non Compliance, Please Refer to Report-16 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}

function ac()
{
Tip('If Any Non Compliance, Please Refer to Report-23 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function cogen()
{
Tip('If Any Non Compliance, Please Refer to Report-12, 13 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function press()
{
Tip('If Any Non Compliance, Please Refer to Report-20 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function hydralic()
{
Tip('If Any Non Compliance, Please Refer to Report-18 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function landfill()
{
Tip('If Any Non Compliance, Please Refer to Report-21 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function other()
{
Tip('If Any Non Compliance, Please Refer to Report-22 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}
function incinerator()
{
Tip('If Any Non Compliance, Please Refer to Report-17 for Details.', TITLE, 'EES HELP',WIDTH,'25')
}


</script>
</head>
<%
SqlUtil utilObj = new SqlUtil();
java.util.Date date = new java.util.Date();
SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");



 Exhibit1DataSourceFactory ex1=new Exhibit1DataSourceFactory(); 
 fromseventh fseven=new fromseventh();  
 
 //eto

    
 String etodob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport8(facilityvo.getId()),"dob",true);
 String etodep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport8(facilityvo.getId()),"dep",true);
 String etosignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport8(facilityvo.getId()),"DOBSIGNOFF",false);
 String etoexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport8(facilityvo.getId()),"EXPIRATIONDATE");
 String etocompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport8(facilityvo.getId()),"compliancestatus");
 String etooverallcompliance="";
 
 if(etodob.equals("Non Compliance") || etodep.equals("Non Compliance") || etosignoff.equals("Non Compliance") || etoexpirationdate.equals("Non Compliance") || etocompliance.equals("Non Compliance"))
 {
 etooverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(etodob.equals("TBD") || etodep.equals("TBD") || etosignoff.equals("TBD") ||  etoexpirationdate.equals("TBD") || etocompliance.equals("TBD"))
 {
 etooverallcompliance="TBD";
 }
 else
 {
 etooverallcompliance="Compliance";
 }
 
 
 
 
 //Fumehood

    
 String fumehooddob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport15(facilityvo.getId()),"DOB",true);
 //String fumehooddep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport15(facilityvo.getId()),"DEP",true);
 String fumehoodsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport15(facilityvo.getId()),"DOBSIGNOFF",false);
 String fumehoodexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport15(facilityvo.getId()),"EXPIRATIONDATE");
 String fumehoodcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport15(facilityvo.getId()),"compliance");
 String fumehoodoverallcompliance="";
 
 if(fumehooddob.equals("Non Compliance") ||  fumehoodsignoff.equals("Non Compliance") || fumehoodexpirationdate.equals("Non Compliance") || fumehoodcompliance.equals("Non Compliance"))
 {
 fumehoodoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(fumehooddob.equals("TBD") || fumehoodsignoff.equals("TBD") || fumehoodexpirationdate.equals("TBD") || fumehoodcompliance.equals("TBD"))
 {
 fumehoodoverallcompliance="TBD";
 }
 else
 {
 fumehoodoverallcompliance="Compliance";
 }


 //chiller    
 String chillerdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport9(facilityvo.getId()),"dob",true);
 String chillerdep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport9(facilityvo.getId()),"depapproval",true);
 String chillersignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport9(facilityvo.getId()),"dobsignoff",false);
 String chillerexpirationdate=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport9(facilityvo.getId()),"dep",true);
 String chillercompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport9(facilityvo.getId()),"compliancestatus");
 String chilleroverallcompliance="";
 
 if(chillerdob.equals("Non Compliance") || chillerdep.equals("Non Compliance") ||  chillersignoff.equals("Non Compliance") || chillerexpirationdate.equals("Non Compliance") || chillercompliance.equals("Non Compliance"))
 {
 chilleroverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(chillerdob.equals("TBD") || chillerdep.equals("TBD") || chillersignoff.equals("TBD") || chillerexpirationdate.equals("TBD") || chillercompliance.equals("TBD"))
 {
 chilleroverallcompliance="TBD";
 }
 else
 {
 chilleroverallcompliance="Compliance";
 }

//a.c units    
 String acdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport23(facilityvo.getId()),"DOBPERMIT",true);
 String acdep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport23(facilityvo.getId()),"DEPPERMIT",true);
 String acsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport23(facilityvo.getId()),"DOBSIGNOFF",false);
 String acexpirationdate=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport23(facilityvo.getId()),"DEPEXPIRATIONDATE",true);
 String accompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport23(facilityvo.getId()),"COMPLIANCESTATUS");
 String acoverallcompliance="";
 
 if(acdob.equals("Non Compliance") || acdep.equals("Non Compliance") ||  acsignoff.equals("Non Compliance") || acexpirationdate.equals("Non Compliance") || accompliance.equals("Non Compliance"))
 {
 acoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(acdob.equals("TBD") || acdep.equals("TBD") || acsignoff.equals("TBD") || acexpirationdate.equals("TBD") || accompliance.equals("TBD"))
 {
 acoverallcompliance="TBD";
 }
 else
 {
 acoverallcompliance="Compliance";
 }

//rpz 
   
 String rpzdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport11(facilityvo.getId()),"DOBPERMITNUMBER",true);
 String rpzdep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport11(facilityvo.getId()),"DEPPERMITNUMBER",true);
 String rpzsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport11(facilityvo.getId()),"DOBSIGNOFF",false);
 //String rpzexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport11(facilityvo.getId()),"EXPIRATIONDATE");
 String rpzcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport11(facilityvo.getId()),"compliancestatus");
 String rpzoverallcompliance="";
 
 if(rpzdob.equals("Non Compliance") || rpzdep.equals("Non Compliance") ||  rpzsignoff.equals("Non Compliance") || rpzcompliance.equals("Non Compliance"))
 {
 rpzoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(rpzdob.equals("TBD") || rpzdep.equals("TBD") || rpzsignoff.equals("TBD") || rpzcompliance.equals("TBD"))
 {
 rpzoverallcompliance="TBD";
 }
 else
 {
 rpzoverallcompliance="Compliance";
 }

//bulk 
   
 String bulkdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport19(facilityvo.getId()),"FIREDEPTAPPROVAL",false);
 String bulk5yr=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport19(facilityvo.getId()),"PRESSURETEST",false);
 String bulksignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport19(facilityvo.getId()),"DOBSIGNOFF",false);
 //String bulkexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport19(facilityvo.getId()),"EXPIRATIONDATE");
 String bulkcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport19(facilityvo.getId()),"COMPLIANCESTATUS");
 String bulkoverallcompliance="";
 
 if(bulkdob.equals("Non Compliance") || bulk5yr.equals("Non Compliance") ||  bulksignoff.equals("Non Compliance") || bulkcompliance.equals("Non Compliance"))
 {
 bulkoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(bulkdob.equals("TBD") || bulk5yr.equals("TBD") || bulksignoff.equals("TBD") || bulkcompliance.equals("TBD"))
 {
 bulkoverallcompliance="TBD";
 }
 else
 {
 bulkoverallcompliance="Compliance";
 }
 
 //elevator
   
 String elevatordob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport10(facilityvo.getId()),"PERMITNUMBER",true);
 //String elevator5yr=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport10(facilityvo.getId()),"PRESSURETEST",true);
 //String elevatorsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport10(facilityvo.getId()),"DOBSIGNOFF",false);
 //String elevatorexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport10(facilityvo.getId()),"EXPIRATIONDATE");
 String elevatorcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport10(facilityvo.getId()),"COMPLIANCESTATUS");
 String elevatoroverallcompliance="";
 
 if(elevatordob.equals("Non Compliance") || elevatorcompliance.equals("Non Compliance"))
 {
 elevatoroverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(elevatordob.equals("TBD") || elevatorcompliance.equals("TBD"))
 {
 elevatoroverallcompliance="TBD";
 }
 else
 {
 elevatoroverallcompliance="Compliance";
 }

//bridge
   
 String bridgedob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport16(facilityvo.getId()),"dobnumber",true);
 String bridgedot=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport16(facilityvo.getId()),"PERMITNUMBER",true);
 String bridgesignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport16(facilityvo.getId()),"DOBSIGNOFF",false);
 String bridgeexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport16(facilityvo.getId()),"EXPIRATIONDATE");
 String bridgecompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport16(facilityvo.getId()),"dotcompliance");
 String bridgeoverallcompliance="";
 
 if(bridgedob.equals("Non Compliance") || bridgedot.equals("Non Compliance") || bridgesignoff.equals("Non Compliance") || bridgeexpirationdate.equals("Non Compliance") || bridgecompliance.equals("Non Compliance")  )
 {
 bridgeoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(bridgedob.equals("TBD") || bridgedot.equals("TBD")  || bridgesignoff.equals("TBD") || bridgeexpirationdate.equals("TBD") || bridgecompliance.equals("TBD"))
 {
 bridgeoverallcompliance="TBD";
 }
 else
 {
 bridgeoverallcompliance="Compliance";
 }

/*//paintspray    
 String paintspraydob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport14(facilityvo.getId()),"DOBNUMBER",true);
 //String paintspraydep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport14(facilityvo.getId()),"dep",true);
 String paintspraysignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport14(facilityvo.getId()),"DOBSIGNOFF",false);
 //String paintsprayexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport14(facilityvo.getId()),"EXPIRATIONDATE");
 String paintspraycompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport14(facilityvo.getId()),"COMPLIANCESTATUS");
 String paintsprayoverallcompliance="";
 
 if(paintspraydob.equals("Non Compliance") ||  paintspraysignoff.equals("Non Compliance")  ||  paintspraycompliance.equals("Non Compliance"))
 {
 paintsprayoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(paintspraydob.equals("TBD") ||  paintspraysignoff.equals("TBD") ||  paintspraycompliance.equals("TBD"))
 {
 paintsprayoverallcompliance="TBD";
 }
 else
 {
 paintsprayoverallcompliance="Compliance";
 }
*/

//cogen    
 String cogendob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport13(facilityvo.getId()),"dobpermit",true);
 String cogendep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport13(facilityvo.getId()),"deppermit",true);
 String cogensignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport13(facilityvo.getId()),"DOBSIGNOFF",false);
 String cogenexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport13(facilityvo.getId()),"expirationdate");
 String cogencompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport13(facilityvo.getId()),"compliancestatus");
 String cogenoverallcompliance="";
 
 if(cogendob.equals("Non Compliance") ||  cogendep.equals("Non Compliance")  ||  cogensignoff.equals("Non Compliance") ||  cogenexpirationdate.equals("Non Compliance")  ||  cogencompliance.equals("Non Compliance"))
 {
 cogenoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(cogendob.equals("TBD") ||  cogendep.equals("TBD") ||  cogensignoff.equals("TBD") ||  cogenexpirationdate.equals("TBD") ||  cogencompliance.equals("TBD"))
 {
 cogenoverallcompliance="TBD";
 }
 else
 {
 cogenoverallcompliance="Compliance";
 }
 
 
 //press    
 String pressdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport36(facilityvo.getId()),"DOBPERMIT",true);
 String pressdep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport36(facilityvo.getId()),"DEPNUMBER",true);
 String presssignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport20(facilityvo.getId()),"DOBSIGNOFF",false);
 String pressexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport36(facilityvo.getId()),"DEPEXPIRATIONDATE");
 String presscompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport36(facilityvo.getId()),"COMPLIANCESTATUS");
 String pressoverallcompliance="";
 
 if(pressdob.equals("Non Compliance") ||  pressdep.equals("Non Compliance")  ||  presssignoff.equals("Non Compliance") ||  pressexpirationdate.equals("Non Compliance")  ||  presscompliance.equals("Non Compliance"))
 {
 pressoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(pressdob.equals("TBD") ||  pressdep.equals("TBD") ||  presssignoff.equals("TBD") ||  pressexpirationdate.equals("TBD") ||  presscompliance.equals("TBD"))
 {
 pressoverallcompliance="TBD";
 }
 else
 {
 pressoverallcompliance="Compliance";
 }
 
 //storagetank    
 String storagetankdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport7(facilityvo.getId()),"DOBAPPROVAL",true);
 String storagetankfd=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport7(facilityvo.getId()),"FIREDEPTCERTIFICATE",true);
 String storagetankpbs=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport7(facilityvo.getId()),"PBSNUMBER",true);
 String storagetanksignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport7(facilityvo.getId()),"DOBSIGNOFF",false);
 String storagetankexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport7(facilityvo.getId()),"EXPIRATIONDATE");
 String storagetankcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport7(facilityvo.getId()),"compliancestatus");
 String storagetankoverallcompliance="";
 
 if(storagetankpbs.equals("Non Compliance") || storagetankdob.equals("Non Compliance") ||  storagetankfd.equals("Non Compliance")  ||  storagetanksignoff.equals("Non Compliance") ||  storagetankexpirationdate.equals("Non Compliance")  ||  storagetankcompliance.equals("Non Compliance"))
 {
 storagetankoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(storagetankpbs.equals("TBD") || storagetankdob.equals("TBD") ||  storagetankfd.equals("TBD") ||  storagetanksignoff.equals("TBD") ||  storagetankexpirationdate.equals("TBD") ||  storagetankcompliance.equals("TBD"))
 {
 storagetankoverallcompliance="TBD";
 }
 else
 {
 storagetankoverallcompliance="Compliance";
 }

 

//landfill    

 String landfilldob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport21(facilityvo.getId()),"LFDOBNUMBER",true);
 String landfilldep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport21(facilityvo.getId()),"LFDEPNUMBER",true);
 String landfillsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport21(facilityvo.getId()),"DOBSIGNOFF",false);
 String landfillexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport21(facilityvo.getId()),"EXPIRATIONDATE");
 String landfillcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport21(facilityvo.getId()),"COMPLIANCESTATUS");
 String landfilloverallcompliance="";
 
 if(landfilldob.equals("Non Compliance") ||  landfilldep.equals("Non Compliance")  ||  landfillsignoff.equals("Non Compliance") ||  landfillexpirationdate.equals("Non Compliance")  ||  landfillcompliance.equals("Non Compliance"))
 {
 landfilloverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(landfilldob.equals("TBD") ||  landfilldep.equals("TBD") ||  landfillsignoff.equals("TBD") ||  landfillexpirationdate.equals("TBD") ||  landfillcompliance.equals("TBD"))
 {
 landfilloverallcompliance="TBD";
 }
 else
 {
 landfilloverallcompliance="Compliance";
 }

//incin 
 String incindob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport17(facilityvo.getId()),"DOB",true);
 String incindep=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport17(facilityvo.getId()),"DEP",true);
 String incinsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport17(facilityvo.getId()),"DOBSIGNOFF",false);
 String incinexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport17(facilityvo.getId()),"DEPEXPIRATIONDATE");
 String incincompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport17(facilityvo.getId()),"COMPLIANCESTATUS");
 String incinoverallcompliance="";
 
 if(incindob.equals("Non Compliance") ||  incindep.equals("Non Compliance")  ||  incinsignoff.equals("Non Compliance") ||  incinexpirationdate.equals("Non Compliance")  ||  incincompliance.equals("Non Compliance"))
 {
 incinoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(incindob.equals("TBD") ||  incindep.equals("TBD") ||  incinsignoff.equals("TBD") ||  incinexpirationdate.equals("TBD") ||  incincompliance.equals("TBD"))
 {
 incinoverallcompliance="TBD";
 }
 else
 {
 incinoverallcompliance="Compliance";
 }
 
 //mis 
 String misdob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport22(facilityvo.getId()),"MISDOB",true);
 String missignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport22(facilityvo.getId()),"DOBSIGNOFF",false);
 String miscompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport22(facilityvo.getId()),"COMPLIANCESTATUS");
 String misoverallcompliance="";
 
 if(misdob.equals("Non Compliance") || missignoff.equals("Non Compliance") || miscompliance.equals("Non Compliance"))
 {
 misoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(misdob.equals("TBD") || missignoff.equals("TBD") ||  miscompliance.equals("TBD"))
 {
 misoverallcompliance="TBD";
 }
 else
 {
 misoverallcompliance="Compliance";
 }

//hyd 
 String hyddob=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport18(facilityvo.getId()),"DOBNUMBER",true);
 String hydpbs= fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport18(facilityvo.getId()),"PBSNO",true);
 String hydsignoff=fseven.getNycdobStatusReport29a(fseven.getNycdobStatusReport18(facilityvo.getId()),"DOBSIGNOFF",false);
 //String incinexpirationdate=fseven.getNycdobStatusReport29b(fseven.getNycdobStatusReport18(facilityvo.getId()),"DEPEXPIRATIONDATE");
 String hydcompliance=fseven.getNycdobStatusReport29(fseven.getNycdobStatusReport18(facilityvo.getId()),"compliance");
 String hydoverallcompliance="";
 
 if(hyddob.equals("Non Compliance") ||  hydpbs.equals("Non Compliance")  ||  hydsignoff.equals("Non Compliance") ||  hydcompliance.equals("Non Compliance"))
 {
 hydoverallcompliance="<font color=\"#FF0000\">Non Compliance</font>";
 }
 else if(hyddob.equals("TBD") ||  hydpbs.equals("TBD") ||  hydsignoff.equals("TBD") ||  hydcompliance.equals("TBD"))
 {
 hydoverallcompliance="TBD";
 }
 else
 {
 hydoverallcompliance="Compliance";
 }

%>
<body>
<script src="/eespc/tooltip/wz_tooltip.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_followscroll.js" type="text/javascript"></script>
<script src="/eespc/tooltip/tip_centerwindow.js" type="text/javascript"></script>
<p align="left"><img border="0" src="images/pdf.gif" width="16" height="16">
<a href="jsp/Exhibit-29xls.jsp" target="_blank"><img border="0" src="images/xls.gif" width="16" height="16"></a>&nbsp;&nbsp;
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
<p align="center"><font color="#01688A"><b>REPORT 29: LIST OF EQUIPMENT,&nbsp; 
AGENCY REQUIREMENTS AND OVERALL COMPLIANCE REQUIREMENTS&nbsp; FOR '<%=str.toUpperCase()%>'</b></font><hr>

<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse; font-family: Verdana; border-style: solid; border-width: 2; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" bordercolor="#111111" width="100%" id="AutoNumber1" height="803">
  <tr>
    <td width="16%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0">SOURCES</font></b></td>
    <td width="11%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0"><%=dob%> WORK PERMIT TO CONSTRUCT
    </font></b></td>
    <td width="9%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0"><%=dob%> PLAN APPROVAL INSPECTION</font></b></td>
    <td width="8%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0"><%=dob%> SIGN OFF</font></b></td>
    <td width="9%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0">FIRE DEPT APPROVAL </font></b>
    </td>
    <td width="13%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0"><%=dob%> CERTIFICATE OF OCCUPANCY&nbsp; 
    (C OF O)</font></b></td>
    <td width="13%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0"><%=dob%> CERTIFICATE OF APPROVAL&nbsp; 
    (C OF A)</font></b></td>
    <td width="9%" height="62" align="center" bgcolor="#336699"><b>
    <font size="1" face="Verdana" color="#C0C0C0"><%=dep%> REQUIREMENTS</font></b></td>
    <td width="9%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0">DEC REQUIREMENTS</font></b></td>
    <td width="9%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0">DOT REQUIREMENTS</font></b></td>
    <td width="9%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0">PERMIT RENEWAL</font></b></td>
    <td width="10%" height="62" align="center" bgcolor="#336699"><b>
    <font face="Verdana" size="1" color="#C0C0C0">OVERALL COMPLIANCE</font></b></td>
  </tr>
 
  <%
  Exhibit1DataSourceFactory exii1=new Exhibit1DataSourceFactory();  
  List exhibit1=(List)exii1.getlist(facilityvo.getId()); 
  if (exhibit1 != null && exhibit1.size()>0)
    {
%>
    <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%201" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">BUILDINGS</font> </b></td>
    <td width="11%" height="1" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="13%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="13%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="10%" height="1" rowspan="3">
    <p align="center"><font size="1">
    <input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="building();"  onmouseout="UnTip()" /><br>
    <%    
    String buildingcompliance=fseven.getNycdobStatusReport29(ex1.getlist(facilityvo.getId()),"COMPLIANCESTATUS");
    String buildingbin=fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"DOBBINNUMBER",true);
	String buildingcofo=fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"COFO",false);
	
    if(buildingbin.equals("Non Compliance") || buildingcompliance.equals("Non Compliance") || buildingcofo.equals("Non Compliance")) 
    {
    out.println("<font color=\"#FF0000\">Non Compliance</font>");
    } 
    else if(buildingbin.equals("TBD") || buildingcompliance.equals("TBD") || buildingcofo.equals("TBD"))
    {
    out.println("TBD");
    } 
    else
    {
    out.println("Compliance");
    }
    
    %></font>
</td>
  </tr>
    <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="4" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="1" align="center"><font size="1">&nbsp;
    <%        
    out.println(fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"DOBBINNUMBER",true));
    %> </font>
    </td>
    <td width="9%" height="1" align="center"><font size="1">&nbsp;
    <%     
    out.println(fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"DOBBINNUMBER",true));
    %> </font>
</td>
    <td width="8%" height="1">
    <p align="center"><font size="1"><%out.println(fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"DOBBINNUMBER",true));%></font></td>
    <td width="9%" height="1">
    <p align="center"><font size="1"><%out.println(fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"DOBBINNUMBER",true));%></font></td>
    <td width="13%" height="1">
    <p align="center"><font size="1"><%out.println(fseven.getNycdobStatusReport29a(ex1.getlist(facilityvo.getId()),"COFO",false));%></font></td>
    <td width="13%" height="1">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="1">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="1">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center"><font size="1">
    N/A</font></td>
  </tr>
  <%
	}
	%>
 <%
  BoilerEntity boilerentity=new BoilerEntity(); 
  List exhibit3=(List)boilerentity.getResultForReport(facilityvo.getId()); 
  if (exhibit3 != null && exhibit3.size()>0)
					{
%>

  <tr>
    <td width="16%" height="9" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%203" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">BOILERS</font></b></td>
    <td width="11%" height="1" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED (Subject to DOB SIGN OFF)</font></td>
    <td width="13%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="1" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="1" rowspan="3">
    
    <p align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="boiler();"  onmouseout="UnTip()" /><br><font size="1">
    <%
    String boilerdob=fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"NYCDOB",true);
    String boilersignoff=fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"DOBSIGNOFF",false);
    String boilderfiredepartment=fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"FIREDEPARTMENTAPPROVAL",false);
 	String compliance1=fseven.getNycdobStatusReport29(boilerentity.getResultForReport(facilityvo.getId()),"compliancestatus");
    String compliance2=fseven.getNycdobStatusReport29(boilerentity.getNycdobStatusReport(facilityvo.getId()),"compliancestatus");
    String compliance3=fseven.getNycdobStatusReport29(boilerentity.getAnnualTuneUpReport(facilityvo.getId()),"compliancestatus");
    String boilerdep=fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"dep",true);
    String boilerexpirationdate=fseven.getNycdobStatusReport29b(boilerentity.getResultForReport(facilityvo.getId()),"expirationdate");
    if(boilerdep.equals("Non Compliance") || boilerexpirationdate.equals("Non Compliance") || boilerdob.equals("Non Compliance") || boilersignoff.equals("Non Compliance") || boilderfiredepartment.equals("Non Compliance") || compliance1.equals("Non Compliance") || compliance2.equals("Non Compliance") || compliance3.equals("Non Compliance"))
    {
    out.println("<font color=\"#FF0000\">Non Compliance</font>");
    }
    else if(boilerdep.equals("TBD") || boilerexpirationdate.equals("TBD") || boilerdob.equals("TBD") || boilersignoff.equals("TBD") || boilderfiredepartment.equals("TBD") || compliance1.equals("TBD") || compliance2.equals("TBD") || compliance3.equals("TBD"))
    {
    out.println("TBD");
    }
    else
    {
    out.println("Compliance");
    }
    
    %>
   </font>
    
    </td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1">&nbsp;
    <%
    
    out.println(fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"NYCDOB",true));
    
    %></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%
    
    out.println(fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"NYCDOB",true));
    
    %></font></td>
    <td width="8%" height="20" align="center"><font size="1"><%
    
    out.println(fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"DOBSIGNOFF",false));
    
    %></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%
    
    out.println(fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"FIREDEPARTMENTAPPROVAL",false));
    
    %></font></td>
    <td width="13%" height="20" align="center">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center">&nbsp;<font size="1"><%
    
    out.println(fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"dep",true));
    
    %></font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">  
   <% 
   out.println(fseven.getNycdobStatusReport29b(boilerentity.getResultForReport(facilityvo.getId()),"expirationdate"));
  %> 
    </font></td>
  </tr>
  <%
					}
					%>
                    
  <%
  GeneratorEntity generatorentity=new GeneratorEntity();  
  List exhibit6=(List)generatorentity.getNycdepReport(facilityvo.getId()); 
  if (exhibit6 != null && exhibit6.size()>0)
					{
%>
   <tr>
    <td width="16%" height="13" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%206" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">GENERATORS</font></b></td>
    <td width="11%" height="7" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED (Subject to DOB SIGN OFF)</font></td>
    <td width="13%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="7" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="28" rowspan="3">
    <p align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="generator();"  onmouseout="UnTip()" /><br>&nbsp;<font size="1">
    <%
    String generatordob=fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"dob",true);
    String generatorsignoff=fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"DOBSIGNOFF",false);
    String generatorfiredepartment=fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"FIREDEPARTMENTAPPROVAL",false);
 	String generatorcompliance1=fseven.getNycdobStatusReport29(generatorentity.getNycdepReport(facilityvo.getId()),"compliancestatus1");
    String generatorcompliance2=fseven.getNycdobStatusReport29(generatorentity.getNycdepReport(facilityvo.getId()),"compliancestatus2");
    String generatordep=fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"dep",true);
    String generatorexpirationdate=fseven.getNycdobStatusReport29b(generatorentity.getNycdepReport(facilityvo.getId()),"expirationdate");
    if(generatordob.equals("Non Compliance") || generatorsignoff.equals("Non Compliance") || generatorfiredepartment.equals("Non Compliance") || generatorcompliance1.equals("Non Compliance") || generatorcompliance2.equals("Non Compliance") || generatordep.equals("Non Compliance") || generatorexpirationdate.equals("Non Compliance"))
    {
    out.println("<font color=\"#FF0000\">Non Compliance</font>");
    }
    else if(generatordob.equals("TBD") || generatorsignoff.equals("TBD") || generatorfiredepartment.equals("TBD") || generatorcompliance1.equals("TBD") || generatorcompliance2.equals("TBD") || generatordep.equals("TBD") || generatorexpirationdate.equals("TBD"))
    {
    out.println("TBD");
    }
    else
    {
    out.println("Compliance");
    }
    
    %>
   </font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1">&nbsp;
    <%    
    out.println(fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"dob",true));    
    %></font></td>
    <td width="9%" height="20" align="center"><font size="1">&nbsp;
    <%
    
    out.println(fseven.getNycdobStatusReport29a(boilerentity.getResultForReport(facilityvo.getId()),"NYCDOB",true));
    
    %></font></td>
    <td width="8%" height="20" align="center"><font size="1">
    <%    
    out.println(fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"DOBSIGNOFF",false));
    %></font></td>
    <td width="9%" height="20" align="center"><font size="1">
    <%
    
    out.println(fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"FIREDEPARTMENTAPPROVAL",false));
    
    %></font></td>
    <td width="13%" height="20">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">&nbsp;
    <%    
    out.println(fseven.getNycdobStatusReport29a(generatorentity.getNycdepReport(facilityvo.getId()),"dep",true));
    %></font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><% 
   out.println(fseven.getNycdobStatusReport29b(generatorentity.getNycdepReport(facilityvo.getId()),"expirationdate"));
  %> </font></td>
  </tr>
  <%
					}
					%>
  
  <%
  List exhibit8=(List)fseven.getNycdobStatusReport8(facilityvo.getId()); 
   if (exhibit8 != null && exhibit8.size()>0)
   {
%>
   <tr>
    <td width="16%" height="12" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%208" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">ETO STERILIZERS</font></b></td>
    <td width="11%" height="24" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED (Subject to DOB SIGN OFF)</font></td>
    <td width="13%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="24" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="45" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="eto();"  onmouseout="UnTip()" /><br><font size="1"><%=etooverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=etodob%></font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1"><%=etodob%></font></td>
    <td width="8%" height="20" align="center"><font size="1"><%=etosignoff%></font></td>
    <td width="9%" height="20" align="center"><font size="1">TBD</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1"><%=etodep%></font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=etoexpirationdate%></font></td>
  </tr>
  <%
   }
   %>
   
  <%
  List exhibit15=(List)fseven.getNycdobStatusReport15(facilityvo.getId()); 
   if (exhibit15 != null   && exhibit15.size()>0)
   {
%>
   <tr>
    <td width="16%" height="4" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2015" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">FUME HOODS</font></b></td>
    <td width="11%" height="15" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">REQUIRED</font></td>
    <td width="8%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">REQUIRED</font></td>
    <td width="9%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    Exempt Source in Title V</font></td>
    <td width="9%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="15" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="17" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="fumehood();"  onmouseout="UnTip()" /><br><font size="1"><%=fumehoodoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="15" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=fumehooddob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=fumehooddob%></font></td>
    <td width="8%" height="20" align="center"><font size="1"><%=fumehoodsignoff%></font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=fumehoodexpirationdate%></font></td>
  </tr>
  <%
   }
   %>
  
  <%
  List exhibit9=(List)fseven.getNycdobStatusReport9(facilityvo.getId()); 
   if (exhibit9 != null  && exhibit9.size()>0)
   {
%>
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%209" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">CHILLERS</font></b></td>
    <td width="11%" height="28" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="28" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="5" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="49" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="chiller();"  onmouseout="UnTip()" /><br><font size="1"><%=chilleroverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="5" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=chillerdob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=chillerdob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=chillersignoff%></font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=chillerdep%></font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=chillerexpirationdate%></font></td>
  </tr>
  <%
   }
   %>
  
  <%
  List exhibit11=(List)fseven.getNycdobStatusReport11(facilityvo.getId()); 
   if (exhibit11 != null  && exhibit11.size()>0)
   {
%>
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2011" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">RPZ</font></b></td>
    <td width="11%" height="23" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1" face="Verdana">REQUIRED</font></td>
    <td width="9%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="13%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    Initial test Report & Annual Inspection Report Submission</font></td>
    <td width="9%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="23" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="10%" height="44" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="rpz();"  onmouseout="UnTip()" /><br><font size="1"><%=rpzoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=rpzdob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=rpzdob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=rpzsignoff%></font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=rpzdep%></font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
  </tr>
  <%
   }
   %>
   
  <%
   
   List exhibit7=(List)fseven.getNycdobStatusReport7(facilityvo.getId()); 
  if (exhibit7 != null && exhibit7.size()>0)
					{
%>
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%207" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">STORAGE TANKS</font></b></td>
    <td width="11%" height="33" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="13%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="9%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="33" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="54" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="storagetank();"  onmouseout="UnTip()" /><br><font size="1"><%=storagetankoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=storagetankdob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=storagetankdob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=storagetanksignoff%></font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1"><%=storagetankfd%></font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center">
    <p align="center"><font size="1"><%=storagetanksignoff%></font></td>    
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=storagetankpbs%></font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=storagetankexpirationdate%></font></td>
  </tr>
  <%
					}
					%>
  
  <%
    
   List exhibit19=(List)fseven.getNycdobStatusReport19(facilityvo.getId()); 
   if (exhibit19!= null   && exhibit19.size()>0)
   {
%>
   <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2019" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">BULK OXYGEN TANK</font></b></td>
    <td width="11%" height="28" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="13%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2">&nbsp;<font size="1">N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2">&nbsp;<font size="1">N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="10%" height="34" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="bulkoxygen();"  onmouseout="UnTip()" /><br><font size="1"><%=bulkoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="5" align="center"><font size="1"><%=bulkdob%></font></td>
    <td width="9%" height="5" align="center"><font size="1"><%=bulkdob%></font></td>
    <td width="8%" height="5" align="center">
    <p align="center"><font size="1"><%=bulksignoff%></font></td>
    <td width="9%" height="5" align="center">
    <p align="center"><font size="1"><%=bulk5yr%></font></td>
    <td width="13%" height="5" align="center">&nbsp;<font size="1">N/A</font></td>
    <td width="13%" height="5" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="5" align="center">&nbsp;<font size="1">N/A</font></td>
    <td width="9%" height="5" align="center">&nbsp;<font size="1">N/A</font></td>
    <td width="9%" height="5">
    <p align="center">&nbsp;<font size="1">N/A</font></td>
    <td width="9%" height="5" align="center">&nbsp;<font size="1">N/A</font></td>
  </tr>
  <%
   }
   %>
   
   <%
  List exhibit10=(List)fseven.getNycdobStatusReport10(facilityvo.getId()); 
   if (exhibit10 != null  && exhibit10.size()>0)
   {
%>
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2010" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">ELEVATOR</font></b></td>
    <td width="11%" height="27" bgcolor="#CCCCCC" rowspan="2" align="center">
    <p align="center"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
     <td width="13%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">N/A</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">N/A</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">N/A</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">N/A</font></td>
    <td width="10%" height="48" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="elevator();"  onmouseout="UnTip()" /><br><font size="1"><%=elevatoroverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=elevatordob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=elevatordob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=elevatordob%></font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1"><%=elevatordob%></font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center">
    <font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
  </tr>
  <%
   }
   %>
   
   <%
    
   List exhibit16=(List)fseven.getNycdobStatusReport16(facilityvo.getId()); 
   if (exhibit16!= null   && exhibit16.size()>0)
   {
%>
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2016" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">BRIDGES &amp; TUNNELS</font></b></td>
    <td width="11%" height="20" bgcolor="#CCCCCC" rowspan="2" align="center">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="13%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">REQUIRED</font></td>
    <td width="9%" height="20" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">REQUIRED</font></td>
    <td width="10%" height="41" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="bridge();"  onmouseout="UnTip()" /><br><font size="1"><%=bridgeoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=bridgedob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=bridgedob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=bridgesignoff%></font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1"><%=bridgedot%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=bridgeexpirationdate%></font></td>
  </tr>
  <%
   }
   %>
   
  <%
   List exhibit23=(List)fseven.getNycdobStatusReport23(facilityvo.getId()); 
   if (exhibit23!= null   && exhibit23.size()>0)
   {
%>
     <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2023" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">A.C UNITS</font></b></td>
    <td width="11%" height="28" bgcolor="#CCCCCC" rowspan="2">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="28" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="5" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="28" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="49" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="ac();"  onmouseout="UnTip()" /><br><font size="1"><%=acoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="5" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=acdob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=acdob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=acsignoff%></font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=acdep%></font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">
    N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=acexpirationdate%></font></td>
  </tr>
  <%
   }
   %>

<%
  List exhibit12=(List)fseven.getNycdobStatusReport12(facilityvo.getId()); 
   if (exhibit12 != null   && exhibit12.size()>0)
   {
%>

  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2013" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">COGEN / TURBINE</font></b></td>
    <td width="11%" height="34" bgcolor="#CCCCCC" rowspan="2" align="center">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="13%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="9%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="34" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="55" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="cogen();"  onmouseout="UnTip()" /><br><font size="1"><%=cogenoverallcompliance%></font></td>
  </tr>

  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=cogendob%></font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=cogendob%></font></td>
    <td width="8%" height="20" align="center">
    <p align="center"><font size="1"><%=cogensignoff%></font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20">
    <p align="center"><font size="1">N/A</font></td>
    <td width="13%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=cogendep%></font></td>
    <td width="9%" height="20" align="center"><font size="1">Available</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1"><%=cogenexpirationdate%></font></td>
  </tr>
  <%
   }
   %>
   
  <%
  List exhibit36=(List)fseven.getNycdobStatusReport36(facilityvo.getId()); 
   if (exhibit36 != null   && exhibit36.size()>0)
   {
%> 
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2020" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">PRINTING PRESS</font></b></td>
    <td width="11%" height="27" bgcolor="#CCCCCC" rowspan="2" align="center">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="8%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="13%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2">
    <font size="1">N/A</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="27" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="10%" height="49" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="press();"  onmouseout="UnTip()" /><br><font size="1"><%=pressoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="7" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="21" align="center"><font size="1"><%=pressdob%></font></td>
    <td width="9%" height="21" align="center"><font size="1"><%=pressdob%></font></td>
    <td width="8%" height="21" align="center"><font size="1"><%=presssignoff%></font></td>
    <td width="9%" height="21" align="center"><font size="1">N/A</font></td>
    <td width="13%" height="21">
    <p align="center"><font size="1">N/A</font></td>
    <td width="13%" height="21" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="21" align="center">&nbsp;<font size="1"><%=pressdep%></font></td>
    <td width="9%" height="21" align="center"><font size="1">Available</font></td>
    <td width="9%" height="21">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="21" align="center"><font size="1"><%=pressexpirationdate%></font></td>
  </tr>
  <%
   }
   %>
   
   <%
    
   List exhibit18=(List)fseven.getNycdobStatusReport18(facilityvo.getId()); 
   if (exhibit18!= null   && exhibit18.size()>0)
   {
%>
  <tr>
    <td width="16%" height="18" bgcolor="#336699" align="center" bordercolor="#003300">
    <p align="left"><b><a href="/eespc/ShowReport.do?reportType=%20REPORT%2018" target="_blank">
    <font face="Verdana" size="1" color="#FFFFFF">HYDRAULIC TANKS </font></b>
    </td>
    <td width="11%" height="36" bgcolor="#CCCCCC" rowspan="2" align="center">
    <p align="center"><font size="1">REQUIRED</font></td>
    <td width="9%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="8%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    REQUIRED</font></td>
    <td width="9%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
     <td width="13%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="13%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="9%" height="36" align="center" bgcolor="#CCCCCC" rowspan="2"><font size="1">
    N/A</font></td>
    <td width="10%" height="57" rowspan="3" align="center"><input type="image" src="/eespc/images/help.png" onClick="return false;" onMouseOver="hydralic();"  onmouseout="UnTip()" /><br><font size="1"><%=hydoverallcompliance%></font></td>
  </tr>
  <tr>
    <td width="16%" height="1" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Applicability</b></font></td>
  </tr>
  <tr>
    <td width="16%" height="6" bgcolor="#336699" align="center" bordercolor="#003300">
    <font size="1" color="#00CC99"><b>Compliance</b></font></td>
    <td width="11%" height="20" align="center"><font size="1"><%=hyddob%></font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="8%" height="20" align="center"><font size="1"><%=hydsignoff%></font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>    
    <td width="13%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>    
    <td width="13%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20">
    <p align="center"><font size="1">N/A</font></td>
    <td width="9%" height="20" align="center"><font size="1">N/A</font></td>
  </tr>
  <%
   }
   %>
 

</table>
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