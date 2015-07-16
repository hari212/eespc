package com.eespc.tracking.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.eespc.tracking.entity.UserEntity;

public class AdminUserListAction extends Action {

	public AdminUserListAction() {
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("MANAGE_USER_LIST");
		DynaActionForm formObj = (DynaActionForm) form;
		UserEntity entity = new UserEntity();

		java.util.List userList = entity.getAllUser();

		if (userList != null)
			session.setAttribute("MANAGE_USER_LIST", userList);
		else
			session.setAttribute("MANAGE_USER_LIST", new ArrayList());
		java.util.List loginList = entity.getLoginDetailUser();
		if (loginList != null)
			session.setAttribute("LOGIN_USER_LIST", loginList);
		else
			session.setAttribute("LOGIN_USER_LIST", new ArrayList());

		log.debug("AdminUserListAction - In Execute");
		return mapping.findForward("success");
	}

	private static Log log;

	static {
		log = LogFactory
				.getLog(com.eespc.tracking.actions.AdminUserListAction.class);
	}
}