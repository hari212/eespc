<%@ page import="java.util.List,com.eespc.tracking.bo.EngineRunningHrsVo" %> 
<%@ page import="com.eespc.tracking.bo.myenum.YearEnum,java.util.Collection"%>
<%@ page import="java.util.Iterator,com.eespc.tracking.bo.DropDown,com.eespc.tracking.bo.NameValueBean"%>

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD width="100%">&#160&#160&#160 <FONT COLOR=white CLASS=section>Monthly Pressure Test Across the Catalyst (Gas Firing)<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="PressureTestGasHelp();"  onmouseout="UnTip()" /><div></div></div></FONT></TD>
  </TR>
  <TR> 
    <TD width="100%" > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="evenRowStyle"> 
                <td   class=columnhead>Year</td>
                <td colspan="12"   class=columnhead><div align="center">Monthly Pressure Test<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="MonthlyPressureTestGas();"  onmouseout="UnTip()" /> </div></td>
                <td   class=columnhead><div align="center">Total</div></td>
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
 				<td   class=columnhead >&nbsp;</td>                            
                <td   class=columnhead width="67">&nbsp;</td>              
                <td   class=columnhead width="70">&nbsp;</td>
              </tr>
<%
					List gas_pressureTestList = (List)request.getAttribute("GAS_MONTHLYPRESSURETEST");
					
					int gas_size = (( gas_pressureTestList != null )?gas_pressureTestList.size():0);
					
					boolean gas_isAllLocked = true;
					
					if (gas_size > 0 ){		
					
						for (int i=0; i < gas_size; i++)
						{
						 	EngineRunningHrsVo engineRunningHrsVo = (EngineRunningHrsVo)gas_pressureTestList.get(i);
							if (engineRunningHrsVo.isLocked()==false){
								gas_isAllLocked = false;
							}
							out.println(engineRunningHrsVo.gas_getHtml(i));
						}//for
						
					}
					
					
					
					if (gas_size==0 || gas_isAllLocked){
		%>				
              <tr class="evenRowStyle"> 
                <td width="47" align="center"  nowrap class="fieldleft">
<%
					DropDown yearEnum = YearEnum.getDropDownObj();
					Collection col = yearEnum.getDropDownValues();
					out.println("<select name=\"gas_fcyear\" id=\"gas_fcyear\">");			
					for (Iterator it=col.iterator(); it.hasNext(); ) {
						NameValueBean element = (NameValueBean)it.next();
						String name = element.getName();
						out.println("<option value=\"" +name + "\">" + name + "</option>");
					}
					out.println("</select>");
%>			
				  </td>
                <td width="52" align="center"  nowrap class="fieldleft"><input name="gas_jan" type="text" class="normal" id="fuelConsumDaily26" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="gas_feb" type="text" class="normal" id="fuelConsumDaily27" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="gas_mar" type="text" class="normal" id="fuelConsumDaily28" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="gas_apr" type="text" class="normal" id="fuelConsumDaily29" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="gas_may" type="text" class="normal" id="fuelConsumDaily210" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="gas_jun" type="text" class="normal" id="fuelConsumDaily211" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="gas_jul" type="text" class="normal" id="fuelConsumDaily212" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="gas_aug" type="text" class="normal" id="fuelConsumDaily213" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="gas_sep" type="text" class="normal" id="fuelConsumDaily214" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="gas_oct" type="text" class="normal" id="fuelConsumDaily215" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="gas_nov" type="text" class="normal" id="fuelConsumDaily216" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="gas_dec" type="text" class="normal" id="fuelConsumDaily217" size="4" maxlength="11"><br><input name="gas_rdbIndex" type="radio" value="0"></td>               
                <td  align="center"  nowrap class="fieldleft"><input name="gas_yearToDate" type="text" class="normal" id="fuelConsumAnnual22" size="10" maxlength="10"></td>            
                <td width="67" align="center"  nowrap class="fieldleft"><input name="gas_lock" type="checkbox" id="lock" value="Y" ></td>                
                <td width="70" align="center"  nowrap class="fieldleft">&nbsp;</td>
              </tr>
<%
			}//else condition			
%>			 
			<input type="hidden" name="gas_hdnConsumption" value="<%=request.getAttribute("gas_hdnConsumption")%>">			
            <tr align="right" class="evenRowStyle"> 
              <td colspan=16> 
                	<input type="button" name="Button23" value="Validate" onClick="gas_fetchConsumption();">
					<input type="button" name="Button23" value="Add " onClick="gas_addMonthlyPressureHrs();">
			  </td>
            </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>