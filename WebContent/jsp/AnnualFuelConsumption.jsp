<%@ page import="java.util.List,com.eespc.tracking.bo.FuelConsumptionVo" %> 
<%@ page import="com.eespc.tracking.bo.myenum.YearEnum,java.util.Collection"%>
<%@ page import="java.util.Iterator,com.eespc.tracking.bo.DropDown,com.eespc.tracking.bo.NameValueBean"%>

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD width="100%">&#160&#160&#160 <FONT COLOR=white CLASS=section>NaturalGas Consumption<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="NaturalGasConsumption();"  onmouseout="UnTip()" /><div align="right">(in 
      cubic feet)</div></FONT></TD>
  </TR>
  <TR> 
    <TD width="100%" > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="oddRowStyle"> 
                <td   class=columnhead width="47">Year</td>
                <td colspan="12"   class=columnhead ><div align="center">Monthly Consumption<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="MonthlyConsumption();"  onmouseout="UnTip()" /> </div></td>
                <td   class=columnhead width="131">Year to Date</td>
                <td colspan="1"   class=columnhead ><div align="center">12 Month Rolling Status<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="TwelveMonthRollingStatus();"  onmouseout="UnTip()" /></div></td>
                <td   class=columnhead width="67" align="center">Lock<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="Lock();"  onmouseout="UnTip()" /></td>               
                <td   class=columnhead width="70" align="center"><div align="center">Delete</div></td>
              </tr>
              <tr class="oddRowStyle"> 
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
                <td   class=columnhead ><div align="center">Consumption</div></td>             
                <td   class=columnhead width="67">&nbsp;</td>              
                <td   class=columnhead width="70">&nbsp;</td>
              </tr>
<%
			List consumptionList = (List)request.getAttribute("FUEL_CONSUMPTION");
			int size = (( consumptionList != null )?consumptionList.size():0);
			boolean isAllLocked = true;
			
			if (size > 0 ){		
			
				for (int i=0; i < size; i++)
				{
				 	FuelConsumptionVo fuelConsumptionVo = (FuelConsumptionVo)consumptionList.get(i);
					if (fuelConsumptionVo.isLocked()==false){
						isAllLocked = false;
					}
					out.println(fuelConsumptionVo.getHtml(i));
				}//for
				
			}		
			
			
			if (size==0 || isAllLocked){
%>				  
              <tr class="evenRowStyle"> 
                <td width="47" align="center"  nowrap class="fieldleft">
<%
					DropDown yearEnum = YearEnum.getDropDownObj();
					Collection col = yearEnum.getDropDownValues();
					out.println("<select name=\"fcyear\" id=\"fcyear\">");			
					for (Iterator it=col.iterator(); it.hasNext(); ) {
						NameValueBean element = (NameValueBean)it.next();
						String name = element.getName();
						out.println("<option value=\"" +name + "\">" + name + "</option>");
					}
					out.println("</select>");
%>			
				  </td>
                <td width="52" align="center"  nowrap class="fieldleft"><input name="jan" type="text" class="normal" id="fuelConsumDaily26" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="feb" type="text" class="normal" id="fuelConsumDaily27" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="mar" type="text" class="normal" id="fuelConsumDaily28" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="apr" type="text" class="normal" id="fuelConsumDaily29" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="may" type="text" class="normal" id="fuelConsumDaily210" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="46" align="center"  nowrap class="fieldleft"><input name="jun" type="text" class="normal" id="fuelConsumDaily211" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="jul" type="text" class="normal" id="fuelConsumDaily212" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="aug" type="text" class="normal" id="fuelConsumDaily213" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="sep" type="text" class="normal" id="fuelConsumDaily214" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="oct" type="text" class="normal" id="fuelConsumDaily215" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="nov" type="text" class="normal" id="fuelConsumDaily216" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="47" align="center"  nowrap class="fieldleft"><input name="dec" type="text" class="normal" id="fuelConsumDaily217" size="4" maxlength="11"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="yearToDate" type="text" class="normal" id="fuelConsumAnnual22" size="10" maxlength="10"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="consumption" type="text" class="normal" id="fuelConsumAnnual2" size="20"></td>              
                <td width="67" align="center"  nowrap class="fieldleft"><input name="lock" type="checkbox" id="lock" value="Y" ></td>                
                <td width="70" align="center"  nowrap class="fieldleft">&nbsp;</td>
              </tr>
<%
			}//else condition			
%>			 
			<input type="hidden" name="hdnConsumption" value="<%= request.getAttribute("hdnConsumption")%>">
			<input type="hidden" name="hdnPreviousConsumption" value="<%= request.getAttribute("hdnPreviousConsumption")%>">
			<input type="hidden" name="hdnCurrentMonthIndex" value="<%= request.getAttribute("hdnCurrentMonthIndex")%>">			
            <tr align="right" class="evenRowStyle"> 
              <td colspan=19>
                	<input type="button" name="Button23" value="Validate" onClick="fetchConsumption();">
					<input type="button" name="Button23" value="Add " onClick="addFuelConsumption();">
			  </td>
            </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>