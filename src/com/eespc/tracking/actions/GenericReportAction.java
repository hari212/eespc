package com.eespc.tracking.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.eespc.tracking.reports.beans.ReportBean;

public class GenericReportAction extends Action {

	public GenericReportAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = "success";
		String s1 = "";
		int i = 0;
		ActionForward actionforward = new ActionForward();
		try {
			String s2 = httpservletrequest.getParameter("reportType");
			System.out.println((new StringBuilder()).append("Report Type : ")
					.append(s2).toString());
			i = Integer.parseInt(s2.substring(8));
			ReportBean reportbean = new ReportBean();
			// System.out.println("asdfas"+s2);
			reportbean.setServletContext(getServlet().getServletContext());
			// System.out.println("asdfggggas");
			reportbean.setReportType(s2);
			// System.out.println("asdfatttttts");
			reportbean.fillReport(httpservletrequest);
			// System.out.println("asdfaaaaaas");
			httpservletrequest.getSession().setAttribute("reportBean",
					reportbean);
			// System.out.println("asdferwerwerweas");
		} catch (Exception exception) {
			httpservletrequest.setAttribute("exception", exception);
			System.out.println((new StringBuilder()).append("Rep exception")
					.append(exception).toString());
			s = "error";
		}
		System.out.println((new StringBuilder()).append("Page : ").append(i)
				.toString());
		System.out.println((new StringBuilder()).append("Show Report...")
				.append(s).toString());
		if (i > 24)
			actionforward = actionmapping.findForward((new StringBuilder())
					.append("Exhibit").append(i).toString());
		else
			actionforward = actionmapping.findForward(s);
		return actionforward;
	}
}