package com.eespc.tracking.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.entity.UserEntity;
import com.eespc.tracking.ui.NavMenu;
import com.eespc.tracking.util.UtilityObject;

public class ControllerAction extends Action {

	public ControllerAction() {
	}

	public ActionForward execute(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		Log log = LogFactory
				.getLog((com.eespc.tracking.actions.ControllerAction.class)
						.getName());
		String s = ".home";
		HttpSession httpsession = httpservletrequest.getSession();
		UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");

		String s1 = httpservletrequest.getParameter("id");
		if (uservo == null)
			return new ForwardingActionForward("login");
		if (s1 == null)
			httpsession.removeAttribute("FACILITY_OBJECT");
		NavMenu navmenu = new NavMenu(uservo, httpservletrequest);
		setMenus(httpsession, s1, navmenu);
		s = navmenu.getActionMapping(s1);
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("mappingForward:").append(s)
					.toString());
		if (s == null) {
			UtilityObject.cleanSessionObject(httpservletrequest);
			String s2 = uservo.getLoginId();
			if (log.isDebugEnabled())
				log.debug((new StringBuilder()).append("Login Name :")
						.append(s2).toString());
			UserEntity userentity = new UserEntity();
			uservo = userentity.getByLoginName(s2);

			httpsession.setAttribute("SESSION_USER", uservo);
			System.out.println("##############################@"
					+ uservo.getDelete());
			if (uservo.getDelete().equals("Y")) {
				System.out.println("##############################@y="
						+ uservo.getDelete());
				httpsession.setAttribute("DELETE_PERMISSION", "seng");
			} else {
				System.out.println("##############################@n="
						+ uservo.getDelete());
				httpsession.removeAttribute("DELETE_PERMISSION");
			}

			s = ".home";
		}
		return new ForwardingActionForward(s);
	}

	public static void setMenus(HttpSession httpsession, String s,
			NavMenu navmenu) {
		String s1 = navmenu.getMenuItems(s);
		if (s1 != null) {
			System.out.println(s1);
			httpsession.setAttribute("MENUS_STRING", s1);
		}
	}
}