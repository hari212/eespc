<%@ page import="java.util.List,com.eespc.tracking.bo.EngineRunningHrsVo" %> 
<%@ page import="com.eespc.tracking.bo.myenum.YearEnum,java.util.Collection"%>
<%@ page import="java.util.Iterator,com.eespc.tracking.bo.DropDown,com.eespc.tracking.bo.NameValueBean"%>

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD width="100%">&#160&#160&#160 <FONT COLOR=white CLASS=section>Annual Maintenance Performed (oil change, spark plugs, filters, etc.), when?<div></div></div></FONT></TD>
  </TR>
  <TR> 
    <TD width="100%" > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="evenRowStyle"> 
                <td   class=columnhead>Year</td>
                <td colspan="12"   class=columnhead><div align="center">Annual Maintenance Performed (oil change, spark plugs, filters, etc.)</div></td>
             
                <td   class=columnhead  align="center">Lock<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="LockHelp();"  onmouseout="UnTip()" /></td>               
                <td   class=columnhead  align="center"><div align="center">Delete</div></td>
            
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnhead width="47">&nbsp;</td>
                <td   class=columnhead width="52">Jan</td>
                <td   class=columnhead width="46">Feb</td>
                <td   class=columnhead width="46">Mar</td>
                <td   class=columnhead width="46">Apr</td>
                <td   class=columnhead width="46">May</td>
                <td   class=columnhead width="46">Jun</td>
                <td   class=columnhead width="47">Jul</td>
                <td   class=columnhead width="47">Aug</td>
                <td   class=columnhead width="47">Sep</td>
                <td   class=columnhead width="47">Oct</td>
                <td   class=columnhead width="47">Nov</td>
                <td   class=columnhead width="47">Dec</td>		
 			                          
                <td   class=columnhead width="67">&nbsp;</td>              
                <td   class=columnhead width="70">&nbsp;</td>
              </tr>
<%
					List anu_pressureTestList = (List)request.getAttribute("ANU_MONTHLYPRESSURETEST");
					
					int anu_size = (( anu_pressureTestList != null )?anu_pressureTestList.size():0);
					
					boolean anu_isAllLocked = true;
					
					if (anu_size > 0 ){		
					
						for (int i=0; i < anu_size; i++)
						{
						 	EngineRunningHrsVo engineRunningHrsVo = (EngineRunningHrsVo)anu_pressureTestList.get(i);
							if (engineRunningHrsVo.isLocked()==false){
								anu_isAllLocked = false;
							}
							out.println(engineRunningHrsVo.anu_getHtml(i));
						}//for
						
					}
					
					
					
					if (anu_size==0 || anu_isAllLocked){
		%>				  
              <tr class="evenRowStyle"> 
                <td width="47" align="center"  nowrap class="fieldleft">
<%
					DropDown yearEnum = YearEnum.getDropDownObj();
					Collection col = yearEnum.getDropDownValues();
					out.println("<select name=\"anu_fcyear\" id=\"anu_fcyear\">");			
					for (Iterator it=col.iterator(); it.hasNext(); ) {
						NameValueBean element = (NameValueBean)it.next();
						String name = element.getName();
						out.println("<option value=\"" +name + "\">" + name + "</option>");
					}
					out.println("</select>");
%>			
				  </td>
                <td width="52" align="center"  nowrap class="fieldleft"><input name="anu_jan" type="text" class="normal" id="fuelConsumDaily26" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="anu_feb" type="text" class="normal" id="fuelConsumDaily27" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="anu_mar" type="text" class="normal" id="fuelConsumDaily28" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="anu_apr" type="text" class="normal" id="fuelConsumDaily29" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="anu_may" type="text" class="normal" id="fuelConsumDaily210" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="anu_jun" type="text" class="normal" id="fuelConsumDaily211" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="anu_jul" type="text" class="normal" id="fuelConsumDaily212" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="anu_aug" type="text" class="normal" id="fuelConsumDaily213" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="anu_sep" type="text" class="normal" id="fuelConsumDaily214" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="anu_oct" type="text" class="normal" id="fuelConsumDaily215" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="anu_nov" type="text" class="normal" id="fuelConsumDaily216" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="anu_dec" type="text" class="normal" id="fuelConsumDaily217" size="4" maxlength="11"><br><input name="anu_rdbIndex" type="radio" value="0"></td>
                
                <td width="67" align="center"  nowrap class="fieldleft"><input name="anu_lock" type="checkbox" id="anu_lock" value="Y" ></td>                
                <td width="70" align="center"  nowrap class="fieldleft">&nbsp;</td>
              </tr>
<%
			}//else condition			
%>			 
			<input type="hidden" name="anu_hdnConsumption" value="<%=request.getAttribute("anu_hdnConsumption")%>">			
            <tr align="right" class="evenRowStyle"> 
              <td colspan=15>
                	
					<input type="button" name="Button23" value="Add " onClick="anu_addMonthlyPressureHrs();">
			  </td>
            </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>