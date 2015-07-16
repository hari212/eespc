package com.eespc.tracking.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

import com.eespc.tracking.entity.UserEntity;

public class LoginAction extends DispatchAction {

	public LoginAction() {
	}

	public ActionForward initial(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		return actionmapping.findForward("login");
	}

	public ActionForward verify(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		String s = (String) dynaactionform.get("loginId");
		String s1 = (String) dynaactionform.get("passWord");
		log.debug("INside verfiy");
		Object obj = null;
		UserEntity userentity = new UserEntity();
		String s2 = "";
		String s3 = "";
		String s4 = "success";
		try {
			com.eespc.tracking.bo.UserVo uservo = userentity
					.validateUser(s, s1);
			if (uservo == null) {
				s2 = "User Not Provisioned";
				s3 = "ERROR";
				s4 = "login";
			} else {
				com.eespc.tracking.bo.LoginUserVo loginuservo = new com.eespc.tracking.bo.LoginUserVo();
				loginuservo.setLogintime(String.valueOf(new Date()));
				loginuservo.setUsername(uservo.getFirstName() + " "
						+ uservo.getLastName());
				loginuservo.setUserid(uservo.getLoginId());
				int logd = userentity.addlogindetail(loginuservo);
				HttpSession httpsession = httpservletrequest.getSession();
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
				log.debug((new StringBuilder())
						.append("LoginAction after getting the user object:")
						.append(uservo).toString());
			}
		} catch (Exception exception) {
			log.error(exception);
			s2 = exception.getMessage();
			s3 = "ERROR";
			s4 = "login";
		}
		if (s2 != null && s2.trim().length() > 0 && s3 != null
				&& s3.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s2);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s3);
		}
		return actionmapping.findForward(s4);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.LoginAction.class);

}