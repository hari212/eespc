<%@ taglib uri="/WEB-INF/tlds/EespcTag.tld" prefix="eespc" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Generator" CONTENT="EditPlus">
<META NAME="Author" CONTENT="">
<META NAME="Keywords" CONTENT="">
<META NAME="Description" CONTENT="">
</HEAD>
<%
com.eespc.tracking.bo.FacilityVo fac = null;
java.util.List list = new java.util.ArrayList();
for (int i=0; i<3; i++){
	fac = new com.eespc.tracking.bo.FacilityVo();
	fac.setId(i);
	fac.setClientName("Client Name " +i);
	fac.setDecId("Dec id " +i);
	list.add(fac);
}
session.setAttribute("BUILDING_LIST", list);
%>
<BODY>

<eespc:requiredField />REQUIRED TEST

<eespc:table dataSource="BUILDING_LIST"
  formName="form1"
  noDataFoundMessage="<CENTER><SPAN CLASS=error>There is no building to this facility.</SPAN></CENTER>" >

  <eespc:tableHeaderCellDef id="h_clientName" label="Client Name" bodyCellId="b_clientName"/>
  <eespc:tableHeaderCellDef id="h_decId" label="Dec Id" bodyCellId="b_decId"/>

  <eespc:tableBodyCellDef id="b_crId" method="getId"/>
  <eespc:tableBodyCellDef id="b_clientName" method="getClientName"/>
  <eespc:tableBodyCellDef id="b_decId" method="getDecId"/>

  <eespc:tr>
      <eespc:th>SELECT</eespc:th>
      <eespc:th>$$h_clientName$$</eespc:th>
      <eespc:th>$$h_decId$$</eespc:th>
  </eespc:tr>

  <eespc:tr>
      <eespc:tb>$$b_clientName$$</eespc:tb>
      <eespc:tb>$$b_decId$$</eespc:tb>
      <eespc:tb>
          <input type="button" name="rdCr" onclick="selectBuilding(this);" value="$$b_crId$$"/>
      </eespc:tb>
  </eespc:tr>
</eespc:table>
*******************************************<br>
<%
out.println (request.getContextPath() +"<br>");
out.println (request.getPathInfo() +"<br>");
com.eespc.tracking.ui.NavMenu nm = new com.eespc.tracking.ui.NavMenu(new com.eespc.tracking.bo.UserVo(),request);
out.println(nm.getMenuItems("1"));
%>
</BODY>
</HTML>

