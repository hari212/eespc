package com.eespc.tracking.actions;

import java.util.ArrayList;

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

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.bo.myenum.StateEnum;
import com.eespc.tracking.bo.myenum.UserRoleEnum;
import com.eespc.tracking.entity.AddressEntity;
import com.eespc.tracking.entity.FacilityEntity;
import com.eespc.tracking.entity.UserEntity;
import com.eespc.tracking.exceptions.UserException;
import com.eespc.tracking.util.UtilityObject;

public class UserAdministrationAction extends DispatchAction {

	public UserAdministrationAction() {
	}

	private void setDropDown(HttpServletRequest httpservletrequest) {
		com.eespc.tracking.bo.DropDown dropdown = StateEnum.getDropDownObj();
		httpservletrequest.setAttribute("STATES", dropdown);
		httpservletrequest.setAttribute("ROLES", UserRoleEnum.getDropDownObj());
		httpservletrequest.setAttribute("CLIENT_LIST",
				FacilityEntity.getDropDown());
	}

	private void setFieldDetails(HttpServletRequest httpservletrequest,
			DynaActionForm dynaactionform, UserVo uservo) {
		setDropDown(httpservletrequest);
		httpservletrequest.setAttribute("isComponentEditable", "Y");
		if (uservo == null) {

			dynaactionform.set("firstName", "");
			dynaactionform.set("lastName", "");
			dynaactionform.set("initial", "");
			dynaactionform.set("title", "");
			dynaactionform.set("address1", "");
			dynaactionform.set("address2", "");
			dynaactionform.set("address3", "");
			dynaactionform.set("city", "");
			dynaactionform.set("state", "");
			dynaactionform.set("zipCode", "");
			dynaactionform.set("phone", "");
			dynaactionform.set("alternatePhone", "");
			dynaactionform.set("email", "");
			dynaactionform.set("alternateEmail", "");
			dynaactionform.set("role", "");
			dynaactionform.set("active", "");
			dynaactionform.set("delete", "");
			dynaactionform.set("clientAccess", null);
			dynaactionform.set("loginId", "");
			dynaactionform.set("userPassword", "");

			httpservletrequest.getSession().removeAttribute("ADMIN_USER_VO");
			dynaactionform.set("methodToCall", "add");
			dynaactionform.set("btnLabel", "Save");
			log.debug("Object UserVo is null so returning..");
			return;
		}
		dynaactionform.set("methodToCall", "update");
		dynaactionform.set("btnLabel", "Update");
		dynaactionform.set("userId", new Integer(uservo.getId()));
		dynaactionform.set("firstName", uservo.getFirstName());
		dynaactionform.set("lastName", uservo.getLastName());
		dynaactionform.set("initial", uservo.getMiddleName());
		dynaactionform.set("title", uservo.getTitle());
		AddressVo addressvo = uservo.getAddress();
		if (addressvo != null) {
			dynaactionform.set("address1", addressvo.getAddress1());
			dynaactionform.set("address2", addressvo.getAddress2());
			dynaactionform.set("address3", addressvo.getAddress3());
			dynaactionform.set("city", addressvo.getCity());
			dynaactionform.set("state", addressvo.getState());
			dynaactionform.set("zipCode", addressvo.getZipCode());
		}
		dynaactionform.set("phone", uservo.getPhone());
		dynaactionform.set("alternatePhone", uservo.getAlternatePhone());
		dynaactionform.set("email", uservo.getEmail());
		dynaactionform.set("alternateEmail", uservo.getAlternateEmail());
		UserRoleEnum userroleenum = uservo.getRole();
		dynaactionform.set(
				"role",
				userroleenum != null ? ((Object) ((new StringBuffer(String
						.valueOf(userroleenum.getCode()))).toString())) : "");
		dynaactionform.set("active",
				UtilityObject.booleanToString(uservo.isActiveInd()));
		dynaactionform.set("delete", uservo.getDelete());
		dynaactionform.set("clientAccess", uservo.getClientAccess());
		dynaactionform.set("loginId", uservo.getLoginId());
		dynaactionform.set("userPassword", uservo.getPassWord());
		httpservletrequest.getSession().setAttribute("ADMIN_USER_VO", uservo);
	}

	public ActionForward initial(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("1111111");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		dynaactionform.reset(actionmapping, httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, null);
		log.debug("UserAdministrationAction - In initial");

		UserEntity entity = new UserEntity();
		java.util.List userList = entity.getAllUser();
		if (userList != null)
			httpsession.setAttribute("MANAGE_USER_LIST", userList);
		else
			httpsession.setAttribute("MANAGE_USER_LIST", new ArrayList());

		java.util.List loginList = entity.getLoginDetailUser();
		if (loginList != null)
			httpsession.setAttribute("LOGIN_USER_LIST", loginList);
		else
			httpsession.setAttribute("LOGIN_USER_LIST", new ArrayList());
		System.out.println("222222");
		return actionmapping.findForward("success");
	}

	public ActionForward delete(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();

		Integer integer = (Integer) dynaactionform.get("userId");

		UserEntity userentity = new UserEntity();
		UserVo uservo = userentity.findByPrimaryKey(integer.intValue());
		AddressVo addressvo = uservo.getAddress();
		int addressid = addressvo.getId();
		if (addressid == 1 || addressid == 3) {
			httpservletrequest.setAttribute("MESSAGE",
					"No Permission For Delete.");
			httpservletrequest.setAttribute("MESSAGE_TYPE",
					"Delete Permission:");
		} else {
			userentity.Deleteusertofacility(integer);
			userentity.Deleteuser(integer);
			userentity.Deleteperson(addressid);
			userentity.Deleteaddress(addressid);
		}
		dynaactionform.reset(actionmapping, httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, null);
		log.debug("UserAdministrationAction - In initial");
		java.util.List userList = userentity.getAllUser();
		if (userList != null)
			httpsession.setAttribute("MANAGE_USER_LIST", userList);
		else
			httpsession.setAttribute("MANAGE_USER_LIST", new ArrayList());

		java.util.List loginList = userentity.getLoginDetailUser();
		if (loginList != null)
			httpsession.setAttribute("LOGIN_USER_LIST", loginList);
		else
			httpsession.setAttribute("LOGIN_USER_LIST", new ArrayList());

		return actionmapping.findForward("success");
	}

	public ActionForward add(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		log.debug("UserAdministrationAction - In Add");
		// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add0$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		AddressVo addressvo = new AddressVo();
		addressvo.setAddress1((String) dynaactionform.get("address1"));
		addressvo.setAddress2((String) dynaactionform.get("address2"));
		addressvo.setAddress3((String) dynaactionform.get("address3"));
		addressvo.setCity((String) dynaactionform.get("city"));
		addressvo.setState((String) dynaactionform.get("state"));
		addressvo.setZipCode((String) dynaactionform.get("zipCode"));
		// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add1$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		UserVo uservo = new UserVo();
		uservo.setFirstName((String) dynaactionform.get("firstName"));
		uservo.setLastName((String) dynaactionform.get("lastName"));
		uservo.setMiddleName((String) dynaactionform.get("initial"));
		uservo.setTitle((String) dynaactionform.get("title"));
		uservo.setPhone((String) dynaactionform.get("phone"));
		uservo.setAlternatePhone((String) dynaactionform.get("alternatePhone"));
		uservo.setEmail((String) dynaactionform.get("email"));
		uservo.setAlternateEmail((String) dynaactionform.get("alternateEmail"));
		uservo.setLoginId((String) dynaactionform.get("loginId"));
		uservo.setPassWord((String) dynaactionform.get("userPassword"));
		uservo.setActiveInd(UtilityObject
				.convertBoolean((String) dynaactionform.get("active")));
		// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add2$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		uservo.setDelete((String) dynaactionform.get("delete"));
		// dynaactionform.set("delete", uservo.getDelete());
		UserRoleEnum userroleenum = UserRoleEnum.get(UtilityObject
				.getIntFromString((String) dynaactionform.get("role")));
		uservo.setRole(userroleenum);
		uservo.setClientAccess((String[]) (String[]) dynaactionform
				.get("clientAccess"));
		System.out
				.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add3$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"
						+ (String[]) (String[]) dynaactionform
								.get("clientAccess"));
		try {

			UserEntity userentity = new UserEntity();
			com.eespc.tracking.bo.UserVo uservo1 = userentity
					.isexistUserid(uservo.getLoginId());
			if (uservo1 != null) {
				throw new UserException(
						"UserId already exists in the system. Please use update to change.");
			}

			if (userentity.isUserExist(uservo.getFirstName(),
					uservo.getLastName(), uservo.getMiddleName()))
				throw new UserException(
						"User already exists in the system. Please use update to change.");
			int i = AddressEntity.addAddress(addressvo);
			// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add5=="+i+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			int j = -1;
			if (i > 0) {
				j = userentity.addUser(i, uservo);
			}
			// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add6$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (j > 0) {
				uservo = userentity.findByPrimaryKey(j);
			}
			// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%In User Add7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			if (uservo != null) {
				s = "User Added.";
				s1 = "CONFIRMATION";
			}
		} catch (Exception userexception) {
			System.out.println("sez:" + userexception);
			s = userexception.getMessage();
			s1 = "ERROR";
			if (log.isErrorEnabled())
				log.error(userexception);
		}

		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		setFieldDetails(httpservletrequest, dynaactionform, uservo);
		UserEntity entity = new UserEntity();
		java.util.List userList = entity.getAllUser();
		if (userList != null)
			httpsession.setAttribute("MANAGE_USER_LIST", userList);
		else
			httpsession.setAttribute("MANAGE_USER_LIST", new ArrayList());
		java.util.List loginList = entity.getLoginDetailUser();
		if (loginList != null)
			httpsession.setAttribute("LOGIN_USER_LIST", loginList);
		else
			httpsession.setAttribute("LOGIN_USER_LIST", new ArrayList());

		return actionmapping.findForward("success");
	}

	public ActionForward view(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		log.debug("UserAdministrationAction - In view");
		HttpSession httpsession = httpservletrequest.getSession();
		Integer integer = (Integer) dynaactionform.get("userId");
		System.out.println("User ---------------------:" + integer);
		UserEntity userentity = new UserEntity();
		UserVo uservo = userentity.findByPrimaryKey(integer.intValue());
		setFieldDetails(httpservletrequest, dynaactionform, uservo);
		return actionmapping.findForward("success");
	}

	public ActionForward changepassword(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out
				.println("#######################################PasswordCHANGE");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;

		HttpSession httpsession = httpservletrequest.getSession();
		UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");
		System.out
				.println("#######################################PasswordCHANGE"
						+ uservo.getId());
		return actionmapping.findForward("changepassword");
	}

	public ActionForward passwordchange(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {

		String s2 = "";
		String s3 = "";
		String s4 = "successpasswordchange";

		System.out.println("CHANGING...1");
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		UserEntity userentity = new UserEntity();
		System.out.println("CHANGING...2");
		UserVo uservo = (UserVo) httpsession.getAttribute("SESSION_USER");
		System.out.println("CHANGING...3");
		String oldpassword = (String) dynaactionform.get("oldpassword");
		String newpassword = (String) dynaactionform.get("newpassword");
		System.out.println("CHANGING...4");
		System.out.println(uservo.getLoginId() + ":" + oldpassword);
		try {
			System.out.println("ckeckoldpass...");
			com.eespc.tracking.bo.UserVo uservo1 = userentity.validateUser(
					uservo.getLoginId(), oldpassword);

			if (uservo1 == null) {
				System.out.println("ckeckpassincorrect...");
				s2 = "Old password is Wrong.";
				s3 = "ERROR";
				s4 = "changepassword";
			} else {
				System.out.println("ckeck change pass...");
				System.out.println(uservo.getId() + ":ckeck change pass...:"
						+ newpassword);
				s2 = "Password successfully changed.";
				s3 = "Success:";
				userentity.changePassword(uservo.getId(), newpassword);
			}

		} catch (Exception exception) {
			log.error(exception);
			s2 = exception.getMessage();
			s3 = "ERROR";
			s4 = "changepassword";
		}
		if (s2 != null && s2.trim().length() > 0 && s3 != null
				&& s3.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s2);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s3);
		}

		return actionmapping.findForward(s4);
	}

	public ActionForward update(ActionMapping actionmapping,
			ActionForm actionform, HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		String s = "";
		String s1 = "";
		DynaActionForm dynaactionform = (DynaActionForm) actionform;
		HttpSession httpsession = httpservletrequest.getSession();
		log.debug("UserAdministrationAction - In update");
		UserVo uservo = (UserVo) httpsession.getAttribute("ADMIN_USER_VO");
		UserVo uservo1 = uservo;
		AddressVo addressvo = new AddressVo();
		addressvo.setId(uservo1.getAddressId());
		addressvo.setAddress1((String) dynaactionform.get("address1"));
		addressvo.setAddress2((String) dynaactionform.get("address2"));
		addressvo.setAddress3((String) dynaactionform.get("address3"));
		addressvo.setCity((String) dynaactionform.get("city"));
		addressvo.setState((String) dynaactionform.get("state"));
		addressvo.setZipCode((String) dynaactionform.get("zipCode"));
		uservo1.setAddress(addressvo);
		uservo1.setFirstName((String) dynaactionform.get("firstName"));
		uservo1.setLastName((String) dynaactionform.get("lastName"));
		uservo1.setMiddleName((String) dynaactionform.get("initial"));
		uservo1.setTitle((String) dynaactionform.get("title"));
		uservo1.setPhone((String) dynaactionform.get("phone"));
		uservo1.setAlternatePhone((String) dynaactionform.get("alternatePhone"));
		uservo1.setEmail((String) dynaactionform.get("email"));
		uservo1.setAlternateEmail((String) dynaactionform.get("alternateEmail"));
		uservo1.setLoginId((String) dynaactionform.get("loginId"));
		uservo1.setPassWord((String) dynaactionform.get("userPassword"));
		uservo1.setActiveInd(UtilityObject
				.convertBoolean((String) dynaactionform.get("active")));
		uservo1.setDelete((String) dynaactionform.get("delete"));
		UserRoleEnum userroleenum = UserRoleEnum.get(UtilityObject
				.getIntFromString((String) dynaactionform.get("role")));
		uservo1.setRole(userroleenum);
		uservo1.setClientAccess((String[]) (String[]) dynaactionform
				.get("clientAccess"));
		System.out
				.println("#######################################PasswordCHANGE"
						+ uservo.getId());
		UserEntity userentity = new UserEntity();
		try {
			userentity.update(uservo1);
			s = "Updated User Information(s).";
			s1 = "CONFIRMATION";
		} catch (Exception exception) {
			s = exception.getMessage();
			s1 = "ERROR";
			if (log.isErrorEnabled())
				log.error(exception);
		}
		if (s != null && s.trim().length() > 0 && s1 != null
				&& s1.trim().length() > 0) {
			httpservletrequest.setAttribute("MESSAGE", s);
			httpservletrequest.setAttribute("MESSAGE_TYPE", s1);
		}
		UserVo uservo2 = userentity.findByPrimaryKey(uservo.getId());
		log.debug((new StringBuilder()).append("udpatedUserVo=")
				.append(uservo2).toString());
		dynaactionform.reset(actionmapping, httpservletrequest);
		setFieldDetails(httpservletrequest, dynaactionform, uservo2);
		UserEntity entity = new UserEntity();
		java.util.List userList = entity.getAllUser();
		if (userList != null)
			httpsession.setAttribute("MANAGE_USER_LIST", userList);
		else
			httpsession.setAttribute("MANAGE_USER_LIST", new ArrayList());

		java.util.List loginList = entity.getLoginDetailUser();
		if (loginList != null)
			httpsession.setAttribute("LOGIN_USER_LIST", loginList);
		else
			httpsession.setAttribute("LOGIN_USER_LIST", new ArrayList());

		return actionmapping.findForward("success");
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.actions.UserAdministrationAction.class);

}