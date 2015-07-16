<%@ taglib uri="/WEB-INF/tlds/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tlds/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tlds/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<%@ page import="java.util.List,com.eespc.tracking.bo.StatepermitVo" %>
<%@ page import="com.eespc.tracking.ui.taglib.TagUtil,com.eespc.tracking.bo.FacilityVo,com.eespc.tracking.util.UtilityObject" %>

<br>
<b>State Permit -Reporting Deadlines:</b>
<br><br>
<table BORDER=0 CELLSPACING=0 CELLPADDING=2 BGCOLOR=#006699 WIDTH="100%" style="border:3px solid #006699; border-collapse: collapse" bordercolor="#111111" height="53">
  <tr>
    
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Expiration(Title V) date<br>(<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>)</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Jan</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Feb</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>March</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>April</b></td>
    <td width="5%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>May</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>June</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>July</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Aug</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Sept</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Oct</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Nov</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Dec</b></td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Last Proposal</b><br>(<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>)</td>
    <td width="6%" bgcolor="#FFFFCC" bordercolor="#006699" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Next Proposal</b><br>(<b><font color="#006699">mm</font><font color="#006699">/dd/yyyy</font></b>)</td>
    <td width="6%" bgcolor="#FFFFCC" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Comments</b></td>
    <td width="6%" bgcolor="#FFFFCC" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Edit</b></td>
	<td width="6%" bgcolor="#FFFFCC" height="30" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <b>Delete</b></td>

  </tr>
  
  <%
				List permitlist	 = (List)request.getAttribute("PERMIT_LIST");
				boolean toEditPermit = UtilityObject.convertBoolean((String)request.getAttribute("EDIT_PERMIT"));
				int size1 =0;
				if (permitlist != null){
				
				size1 = permitlist.size();
					for (int i=0; i < size1; i++)
					{
						StatepermitVo statePermitInfo = (StatepermitVo)permitlist.get(i);
						boolean isPermitEditable = false;
						int permitId = statePermitInfo.getId();
						String permitIdStr = permitId +"";
						String tempStr = String.valueOf(request.getAttribute("editid"));
						
						if (null != tempStr && tempStr.equalsIgnoreCase(permitIdStr) && toEditPermit)
						{
							isPermitEditable = true;
						} 
						
						
					out.println("<tr class=\"evenRowStyle\">");
					
					out.println("<td  nowrap class=\"fieldleft\">");
					out.print(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fstatus", UtilityObject.checkNullAndFill(statePermitInfo.getFstatus(),""),null));
					out.println("</td>");
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fexpirationdate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(statePermitInfo.getFexpirationdate()),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fjan", UtilityObject.checkNullAndFill(statePermitInfo.getFjan(),""),null));
					out.println("</td>");
					
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "ffeb", UtilityObject.checkNullAndFill(statePermitInfo.getFfeb(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fmar", UtilityObject.checkNullAndFill(statePermitInfo.getFmar(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fapril", UtilityObject.checkNullAndFill(statePermitInfo.getFapril(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fmay", UtilityObject.checkNullAndFill(statePermitInfo.getFmay(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fjune", UtilityObject.checkNullAndFill(statePermitInfo.getFjune(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fjuly", UtilityObject.checkNullAndFill(statePermitInfo.getFjuly(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "faug", UtilityObject.checkNullAndFill(statePermitInfo.getFaug(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fsep", UtilityObject.checkNullAndFill(statePermitInfo.getFsep(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "foct", UtilityObject.checkNullAndFill(statePermitInfo.getFoct(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fnov", UtilityObject.checkNullAndFill(statePermitInfo.getFnov(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fdec", UtilityObject.checkNullAndFill(statePermitInfo.getFdec(),""),null));
					out.println("</td>");
					
					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "flastproposal", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(statePermitInfo.getFlastproposal()),""),null));
					out.println("</td>");


					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fnextproposal", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(statePermitInfo.getFnextproposal()),""),"DATES"));
					out.println("</td>");

					out.println("<td  nowrap class=\"fieldleft\">");					
					out.println(TagUtil.toHtml(request, TagUtil.HTML_TEXT, isPermitEditable, "fcomments", UtilityObject.checkNullAndFill(statePermitInfo.getFcomments(),""),null));
					out.println("</td>");

					
					
					
					
						out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isPermitEditable){
							out.println("<a href=\"javascript:editstatePermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/edit.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");	
													
					out.println("<td align=\"center\"  nowrap class=\"fieldleft\">");
						if (!isPermitEditable){
						if(session.getAttribute("DELETE_PERMISSION")!=null)
				            {
							out.println("<a href=\"javascript:deletesatatePermit('");
							out.println(permitId);
							out.println("');\">");
							out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\"></a>");
							}
				else
				{
				out.println("<img src=\"/eespc/images/delete.jpg\" width=\"42\" height=\"13\" border=\"0\">");
				}
						}else{
							out.println("&nbsp;");
						}
						out.println("</td>");			
				  out.println("</tr>");
					
					
				}
					
			}
			
			
			if (!toEditPermit){
  
  
  %>
  <tr>
   
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fexpirationdate" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fjan" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="ffeb" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fmar" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fapril" size="10"></td>
    <td width="5%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fmay" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fjune" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fjuly" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="faug" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fsep" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="foct" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fnov" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fdec" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="flastproposal" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fnextproposal" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    <input type="text" name="fcomments" size="10"></td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    &nbsp;</td>
    <td width="6%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" align="center">
    &nbsp;</td>
  </tr>
  <%}%>
  <tr>
    <td width="95%" bgcolor="#FFFFFF" height="15" style="border: 1px solid #006699; padding-left: 4; padding-right: 4; padding-top: 1; padding-bottom: 1" colspan="18">
    <p align="right"><input type="button" name="Button22" value="<%= ((toEditPermit)? "Update" : "Add") %>" onClick="<%= ((toEditPermit)? "addpermit(true)" : "addpermit(false)") %>;"></td>
  </tr>
 
</table>