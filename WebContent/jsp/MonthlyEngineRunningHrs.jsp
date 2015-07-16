<%@ page import="java.util.List,com.eespc.tracking.bo.EngineRunningHrsVo" %> 
<%@ page import="com.eespc.tracking.bo.myenum.YearEnum,java.util.Collection"%>
<%@ page import="java.util.Iterator,com.eespc.tracking.bo.DropDown,com.eespc.tracking.bo.NameValueBean"%>

<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH=100%>
  <TR BGCOLOR=#006699> 
    <TD width="100%">&#160&#160&#160 <FONT COLOR=white CLASS=section>Monthly Engine Running Hours<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="EngineRunningHrsHelp();"  onmouseout="UnTip()" /><div></div></div></FONT></TD>
  </TR>
  <TR> 
    <TD width="100%" > <TABLE BORDER=0 CELLSPACING=1 CELLPADDING=0 WIDTH=100%>
        <TR BGCOLOR=white> 
          <TD> 
          <table border="0" width="100%" cellspacing="1" cellpadding="2" class="globalTableStyle">
              <tr class="evenRowStyle"> 
                <td   class=columnhead>Year</td>
                <td colspan="12"   class=columnhead><div align="center">Engine Running Hours<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="MonthlyEngineRunningHrs();"  onmouseout="UnTip()" /> </div></td>
                <td   class=columnhead><div align="center">Total Hours</div></td>
                <td   class=columnhead  align="center">Lock<input type="image" src="/eespc/images/help.png" onClick="return false;" onmouseover="LockHelp();"  onmouseout="UnTip()" /></td>               
                <td   class=columnhead  align="center"><div align="center">Delete</div></td>
            
              </tr>
              <tr class="evenRowStyle"> 
                <td   class=columnhead>&nbsp;</td>
                <td   class=columnhead>Jan</td>
                <td   class=columnhead>Feb</td>
                <td   class=columnhead>Mar</td>
                <td   class=columnhead>Apr</td>
                <td   class=columnhead>May</td>
                <td   class=columnhead>Jun</td>
                <td   class=columnhead>Jul</td>
                <td   class=columnhead>Aug</td>
                <td   class=columnhead>Sep</td>
                <td   class=columnhead>Oct</td>
                <td   class=columnhead>Nov</td>
                <td   class=columnhead>Dec</td>		
 				<td   class=columnhead>&nbsp;</td>                            
                <td   class=columnhead>&nbsp;</td>              
                <td   class=columnhead>&nbsp;</td>
              </tr>
<%
			List runningHrsList = (List)request.getAttribute("ENGINERUNNING_HRS");
			int size = (( runningHrsList!= null )?runningHrsList.size():0);
			boolean isAllLocked = true;
			
			if (size > 0 ){		
			
				for (int i=0; i < size; i++)
				{
				 	EngineRunningHrsVo engineRunningHrsVo= (EngineRunningHrsVo)runningHrsList.get(i);
					if (engineRunningHrsVo.isLocked()==false){
						isAllLocked = false;
					}
					out.println(engineRunningHrsVo.getHtml(i));
				}//for
				
			}		
			
			
			if (size==0 || isAllLocked){
%>				  
              <tr class="evenRowStyle"> 
                <td  align="center"  nowrap class="fieldleft">
<%
					DropDown yearEnum = YearEnum.getDropDownObj();
					Collection col = yearEnum.getDropDownValues();
					out.println("<select name=\"e_rhyear\" id=\"e_rhyear\">");			
					for (Iterator it=col.iterator(); it.hasNext(); ) {
						NameValueBean element = (NameValueBean)it.next();
						String name = element.getName();
						out.println("<option value=\"" +name + "\">" + name + "</option>");
					}
					out.println("</select>");
%>			
				  </td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_jan" type="text" class="normal" id="fuelConsumDaily26" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_feb" type="text" class="normal" id="fuelConsumDaily27" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_mar" type="text" class="normal" id="fuelConsumDaily28" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_apr" type="text" class="normal" id="fuelConsumDaily29" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_may" type="text" class="normal" id="fuelConsumDaily210" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_jun" type="text" class="normal" id="fuelConsumDaily211" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_jul" type="text" class="normal" id="fuelConsumDaily212" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_aug" type="text" class="normal" id="fuelConsumDaily213" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_sep" type="text" class="normal" id="fuelConsumDaily214" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_oct" type="text" class="normal" id="fuelConsumDaily215" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_nov" type="text" class="normal" id="fuelConsumDaily216" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>
                <td  align="center"  nowrap class="fieldleft"><input name="e_dec" type="text" class="normal" id="fuelConsumDaily217" size="4" maxlength="11"><br><input name="e_rdbIndex" type="radio" value="0"></td>               
                <td  align="center"  nowrap class="fieldleft"><input name="e_yearToDate" type="text" class="normal" id="fuelConsumAnnual22" size="10" maxlength="10"></td>            
                <td  align="center"  nowrap class="fieldleft"><input name="e_lock" type="checkbox" id="lock" value="Y" ></td>                
                <td  align="center"  nowrap class="fieldleft">&nbsp;</td>
              </tr>
<%
			}//else condition			
%>			 
			<input type="hidden" name="e_hdnConsumption" value="<%= request.getAttribute("e_hdnConsumption")%>">
					
            <tr align="right" class="evenRowStyle"> 
              <td colspan=16>
                	<input type="button" name="Button23" value="Validate" onClick="e_fetchRunningHrs();">
					<input type="button" name="Button23" value="Add " onClick="addEngineRunningHrs();">
			  </td>
            </tr>
            </table></TD>
        </TR>
      </TABLE></TD>
  </TR>
</TABLE>