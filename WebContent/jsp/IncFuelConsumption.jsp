<%@ page import="java.util.List,com.eespc.tracking.bo.FuelConsumptionVo" %> 
<%@ page import="com.eespc.tracking.bo.myenum.YearEnum,java.util.Collection"%>
<%@ page import="java.util.Iterator,com.eespc.tracking.bo.DropDown,com.eespc.tracking.bo.NameValueBean"%>

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100% style="border-collapse: collapse" bordercolor="#111111">
  <TR BGCOLOR=#006699> 
    <TD width="100%" >&#160&#160&#160<FONT COLOR=white CLASS=section>Annual&nbsp; 
    waste Burned:<div align="right">(in Pounds)</div></FONT></TD>
  </TR>
  <TR> 
    <TD width="953" > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="1" width="100%" cellspacing="0" cellpadding="2" class="globalTableStyle" style="border-collapse: collapse" bordercolor="#006699">
              <tr class="oddRowStyle"> 
                <td   class=columnhead width="30">Year</td>
                <td colspan="12"   class=columnhead width="487"><div align="center">Monthly 
                    Consumption </div></td>
                <td   class=columnhead width="78">Year to Date</td>
                <td   class=columnhead width="249"><div align="center">12 
                  Month 
                    Rolling Status</div></td>
                <td   class=columnhead width="47" align="center">Lock</td>
               
                <td   class=columnhead width="54" align="center"><div align="center">
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
                <td   class=columnhead width="36">Jul</td>
                <td   class=columnhead width="36">Aug</td>
                <td   class=columnhead width="36">Sep</td>
                <td   class=columnhead width="36">Oct</td>
                <td   class=columnhead width="36">Nov</td>
                <td   class=columnhead width="36">Dec</td>
                <td   class=columnhead width="78">&nbsp;</td>
                <td   class=columnhead width="148"><div align="center">Consumption</div></td>
              
               
              <td   class=columnhead width="54">&nbsp;</td>
                <td   class=columnhead width="54">&nbsp;</td>
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
                <td width="30" align="center"  nowrap class="fieldleft">
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
%> &nbsp;</td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="jan" type="text" class="normal" id="fuelConsumDaily26" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="feb" type="text" class="normal" id="fuelConsumDaily27" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="mar" type="text" class="normal" id="fuelConsumDaily28" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="apr" type="text" class="normal" id="fuelConsumDaily29" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="may" type="text" class="normal" id="fuelConsumDaily210" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="jun" type="text" class="normal" id="fuelConsumDaily211" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="jul" type="text" class="normal" id="fuelConsumDaily212" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="aug" type="text" class="normal" id="fuelConsumDaily213" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="sep" type="text" class="normal" id="fuelConsumDaily214" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="oct" type="text" class="normal" id="fuelConsumDaily215" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="nov" type="text" class="normal" id="fuelConsumDaily216" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="36" align="center"  nowrap class="fieldleft"><input name="dec" type="text" class="normal" id="fuelConsumDaily217" size="4" maxlength="4"><br><input name="rdbIndex" type="radio" value="0"></td>
                <td width="78" align="center"  nowrap class="fieldleft"><input name="yearToDate" type="text" class="normal" id="fuelConsumAnnual22" size="10" maxlength="10"></td>
                <td width="148" align="center"  nowrap class="fieldleft">
                <input name="consumption" type="text" class="normal" id="fuelConsumAnnual2" size="20"></td>
               
                <td width="47" align="center"  nowrap class="fieldleft"><input name="lock" type="checkbox" id="lock" value="Y" ></td>
                <td width="47" align="center"  nowrap class="fieldleft"></td>
               
              </tr>
<%
			}//else condition
			
%>			 
			<input type="hidden" name="hdnConsumption" value="<%= request.getAttribute("hdnConsumption")%>">
			<input type="hidden" name="hdnPreviousConsumption" value="<%= request.getAttribute("hdnPreviousConsumption")%>">
			<input type="hidden" name="hdnCurrentMonthIndex" value="<%= request.getAttribute("hdnCurrentMonthIndex")%>">			
              <tr align="right" class="evenRowStyle"> 
                <td colspan=18 width="100%" ><input type="button" name="Button23" value="Validate" onClick="fetchConsumption();">
									<input type="button" name="Button23" value="Add " onClick="addFuelConsumption();"> </td>
              </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>