<%@ page import="java.util.List,com.eespc.tracking.bo.FuelConsumptionVo" %> 
<%@ page import="com.eespc.tracking.bo.myenum.YearEnum,java.util.Collection"%>
<%@ page import="java.util.Iterator,com.eespc.tracking.bo.DropDown,com.eespc.tracking.bo.NameValueBean"%>

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100% style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD width="100%">&#160&#160&#160 <FONT COLOR=white CLASS=section>Fuel Oil Consumption<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="FuelOilConsumption();"  onmouseout="UnTip()" /><div align="right">(in 
      gallons)</div></FONT></TD>
  </TR>
  <TR> 
    <TD width="100%" > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead width="30">Year</td>
                <td colspan="12"   class=columnhead width="493"><div align="center">Monthly 
                    Consumption<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="MonthlyConsumption();"  onmouseout="UnTip()" /> </div></td>
                <td   class=columnhead width="51">Year to Date</td>
                <td colspan="1"   class=columnhead width="274"><div align="center">12 
                  Month 
                    Rolling Status<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="TwelveMonthRollingStatus();"  onmouseout="UnTip()" /></div></td>
                <td   class=columnhead width="67" align="center">Lock<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Lock();"  onmouseout="UnTip()" /></td>
                
                <td   class=columnhead width="55" align="center"><div align="center">
                  Delete</div></td>
              </tr>
              <tr class="oddRowStyle"> 
                <td   class=columnhead width="30">&nbsp;</td>
                <td   class=columnhead width="36">Jan</td>
                <td   class=columnhead width="36">Feb</td>
                <td   class=columnhead width="36">Mar</td>
                <td   class=columnhead width="36">Apr</td>
                <td   class=columnhead width="36">May</td>
                <td   class=columnhead width="36">Jun</td>
                <td   class=columnhead width="37">Jul</td>
                <td   class=columnhead width="37">Aug</td>
                <td   class=columnhead width="37">Sep</td>
                <td   class=columnhead width="37">Oct</td>
                <td   class=columnhead width="37">Nov</td>
                <td   class=columnhead width="37">Dec</td>
                <td   class=columnhead width="51">&nbsp;</td>
                <td   class=columnhead width="167"><div align="center">Consumption</div></td>
              
                <td   class=columnhead width="67">&nbsp;</td>
               
                <td   class=columnhead width="55">&nbsp;</td>
              </tr>
              
              <%
			List o_consumptionList = (List)request.getAttribute("O_FUEL_CONSUMPTION");
			
			int o_size = (( o_consumptionList != null )?o_consumptionList.size():0);
			
			boolean o_isAllLocked = true;
			
			if (o_size > 0 ){		
			
				for (int i=0; i < o_size; i++)
				{
				 	FuelConsumptionVo fuelConsumptionVo = (FuelConsumptionVo)o_consumptionList.get(i);
					if (fuelConsumptionVo.isLocked()==false){
						o_isAllLocked = false;
					}
					out.println(fuelConsumptionVo.o_getHtml(i));
				}//for
				
			}
			
			
			
			if (o_size==0 || o_isAllLocked){
			
%>
			  
              <tr class="evenRowStyle"> 
                <td width="30" align="center"  nowrap class="fieldleft">
<%
					DropDown o_yearEnum = YearEnum.getDropDownObj();
					Collection o_col = o_yearEnum.getDropDownValues();
					out.println("<select name=\"o_fcyear\" id=\"o_fcyear\">");			
					for (Iterator it=o_col.iterator(); it.hasNext(); ) {
						NameValueBean element = (NameValueBean)it.next();
						String name = element.getName();
						out.println("<option value=\"" +name + "\">" + name + "</option>");
					}
					out.println("</select>");
%>			
				  </td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="o_jan" type="text" class="normal" id="fuelConsumDaily26" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="o_feb" type="text" class="normal" id="fuelConsumDaily27" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="o_mar" type="text" class="normal" id="fuelConsumDaily28" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="o_apr" type="text" class="normal" id="fuelConsumDaily29" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="o_may" type="text" class="normal" id="fuelConsumDaily210" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="o_jun" type="text" class="normal" id="fuelConsumDaily211" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="37" align="center"  nowrap class="fieldleft"><input name="o_jul" type="text" class="normal" id="fuelConsumDaily212" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="37" align="center"  nowrap class="fieldleft"><input name="o_aug" type="text" class="normal" id="fuelConsumDaily213" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="37" align="center"  nowrap class="fieldleft"><input name="o_sep" type="text" class="normal" id="fuelConsumDaily214" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="37" align="center"  nowrap class="fieldleft"><input name="o_oct" type="text" class="normal" id="fuelConsumDaily215" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="37" align="center"  nowrap class="fieldleft"><input name="o_nov" type="text" class="normal" id="fuelConsumDaily216" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="37" align="center"  nowrap class="fieldleft"><input name="o_dec" type="text" class="normal" id="fuelConsumDaily217" size="4" maxlength="11"><br><input name="o_rdbIndex" type="radio" value="0"></td>
                <td width="51" align="center"  nowrap class="fieldleft"><input name="o_yearToDate" type="text" class="normal" id="fuelConsumAnnual22" size="10" maxlength="10"></td>
                <td width="167" align="center"  nowrap class="fieldleft">
                <input name="o_consumption" type="text" class="normal" id="fuelConsumAnnual2" size="20"></td>
               
                <td width="67" align="center"  nowrap class="fieldleft"><input name="o_lock" type="checkbox" id="lock" value="Y" ></td>
               
                <td width="55" align="center"  nowrap class="fieldleft">&nbsp;</td>
              </tr>
<%
			}//else condition
			
%>
			<input type="hidden" name="o_hdnConsumption" value="<%= request.getAttribute("o_hdnConsumption")%>">
			<input type="hidden" name="o_hdnPreviousConsumption" value="<%= request.getAttribute("o_hdnPreviousConsumption")%>">
			<input type="hidden" name="o_hdnCurrentMonthIndex" value="<%= request.getAttribute("o_hdnCurrentMonthIndex")%>">			
              <tr align="right" class="evenRowStyle"> 
                <td colspan=19 ><input type="button" name="Button23" value="Validate" onClick="o_fetchConsumption();">
									<input type="button" name="Button23" value="Add " onClick="o_addFuelConsumption();"> </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>