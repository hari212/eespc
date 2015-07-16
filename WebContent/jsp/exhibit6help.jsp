<%
String id=request.getParameter("id");
%>

<meta http-equiv="Content-Language" content="en-us">
<table border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" >
  <%
  if(id.equals("1"))
  {
  %>
  <tr>
    <td colspan="2" bgcolor="#008000" height="16"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 1:</font></b></td>
    </tr>
  <tr>
    <td width="16%" bgcolor="#00FF00" height="32"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" bgcolor="#00FF00" height="32"><font face="Verdana" size="2">
    Means that the Facade Inspection Details are not Available. Please Verify 
    and Input the Data for a Complete Assessment.</font></td>
  </tr>
  <tr>
    <td width="16%" bgcolor="#00FF00" height="16"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" bgcolor="#00FF00" height="16"><font face="Verdana" size="2">
    Facade Inspection(5yr) was not Done or Report not Submitted to the DOB.</font></td>
  </tr>
   <%
  }
  else if(id.equals("2"))
  {
  %>
  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 2:</font></b></td>
    </tr>
  <tr>
    <td colspan="2" height="16" bgcolor="#00FF00">
    <font face="Verdana" size="2">No Note</font></td>
    </tr>
   <%
  }
  else if(id.equals("3"))
  {
  %>
  
  <tr>
    <td colspan="2" height="15" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 3:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No assessment values DEP permit or expiration date available, please input 
    correct date for compliance</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DEP permit obtained or permit has already expired. If expired, renew the permit.</font></td>
  </tr>
   <%
  }
  else if(id.equals("4"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT&nbsp; 4:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the MEA, inspection date or report submitted. please input 
    these and assess compliance.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No MEA#, or inspection was not done or next inspection delayed or reports 
    were not submitted to DOB.</font></td>
  </tr>
   <%
  }
  else if(id.equals("5"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 5:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on annual Tune-up, reports submitted dates and information on 
    the stack test were not input.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No tune ups, no report submitted, DEC test not completed</font></td>
  </tr>
   <%
  }
  else if(id.equals("6"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 6:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DOB permit, permit expiration data, stack test.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit, Permit not renewed; no stack test done.</font></td>
  </tr>
   <%
  }
  else if(id.equals("7"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 7:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the tightness test, PBS registration certification, and 
    expiration dates.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No tightness test, no PBS registration, permit expired.</font></td>
  </tr>
   <%
  }
  else if(id.equals("8"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 8:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DEP permit, expiration dates, DOB permit, test dates</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DEP permit, or permit expired, or No DOB permit or Test Not done.</font></td>
  </tr>
   <%
  }
  else if(id.equals("9"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 9:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DOB DEP approvals, and expiration dates</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit or No DEP permit or Permit expired.</font></td>
  </tr>
   <%
  }
  else if(id.equals("10"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 10:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DOB permit, annual inspections</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit or inspection performed or reports submitted to DOB.</font></td>
  </tr>
   <%
  }
  else if(id.equals("11"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 11:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on DOB, DEP permits, expiration and inspection reports.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit, or no DEP permit or permit expired or inspection not done or 
    not submitted to agencies.</font></td>
  </tr>
   <%
  }
  else if(id.equals("12"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 12:</font></b></td>
    </tr>
  <tr>
    <td colspan="2" height="16" bgcolor="#00FF00">
    <font face="Verdana" size="2">No data</font></td>
    </tr>
     <%
  }
  else if(id.equals("13"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 13:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DOB and DEP permit</font></td>
  </tr>
  <tr>
    <td width="16%" height="17" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="17" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit or DEP permit or DEC test not done or permits expired</font></td>
  </tr>
   <%
  }
  else if(id.equals("14"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 14:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DEP, DEC permits or the expiration.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DEP permit or No DEC permit inclusion or permits expired.</font></td>
  </tr>
   <%
  }
  else if(id.equals("15"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 15:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DEC/DEP/DOB permits or expiration dates.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit or DEC permit or DEP permit or dates expired.</font></td>
  </tr>
   <%
  }
  else if(id.equals("16"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 16:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid date on the DOT/DOB permits, insurance information or inspections.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOT permit, no DOB permits; No inspection done.</font></td>
  </tr>
   <%
  }
  else if(id.equals("17"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 17:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DOB/DEP/DEC permits expiration dates, temp dates, waste 
    quantities allowed, stack test.</font></td>
  </tr>
  <tr>
    <td width="16%" height="32" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="32" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB permit, or No DEP permit or DEC permit or Temperature executed, or 
    waste quantity exceeded, stack test not done.</font></td>
  </tr>
   <%
  }
  else if(id.equals("18"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 18:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the secondary containment, DEC/PBS approvals or 
    inspections.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No secondary containment, or PBS registrations, no inspections.</font></td>
  </tr>
   <%
  }
  else if(id.equals("19"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 19:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DOB/FD approvals, 5 years test.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB/FD approvals, no 5Yr test Done.</font></td>
  </tr>
   <%
  }
  else if(id.equals("20"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 20:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the VOC lb/Yr, DEC/DOB/DEP permits; and expiration dates.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No DOB/DEP/DEC permits, or permit expired, or VOC limit exceed.</font></td>
  </tr>
   <%
  }
  else if(id.equals("21"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 21:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the S, CH4, Temp, Nox, CO and AES submitted date.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    S and CH4 exceded, or other parameters as permitted exceeded. No AES 
    submitted.</font></td>
  </tr>
   <%
  }
  else if(id.equals("22"))
  {
  %>

  <tr>
    <td colspan="2" height="16" bgcolor="#008000"><b>
    <font face="Verdana" size="2" color="#FFFFFF">REPORT 22:</font></b></td>
    </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">TBD</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No valid data on the DEP/DOB/DOH, DEC/FD approvals, expiration dates; and 
    stack tests.</font></td>
  </tr>
  <tr>
    <td width="16%" height="16" bgcolor="#00FF00"><b>
    <font face="Verdana" size="2">Non-Compliance</font></b></td>
    <td width="84%" height="16" bgcolor="#00FF00"><font face="Verdana" size="2">
    No permits from DEP or DOB or DOH or DEC or FD or permits expired.</font></td>
  </tr>
   <%
  }
 
  %>

</table>&nbsp;