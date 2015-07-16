package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.eespc.tracking.bo.Constants;
import com.eespc.tracking.bo.LoginUserVo;
import com.eespc.tracking.bo.UserVo;
import com.eespc.tracking.bo.myenum.UserRoleEnum;
import com.eespc.tracking.exceptions.UserException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.entity:
//            AddressEntity

public class UserEntity {

	public UserEntity() {
	}

	public int addlogindetail(LoginUserVo loginuservo) throws UserException {
		int j = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("insert into LOGINDETAIL (LOGINDETAILID,USERID,USERNAME,LOGINTIME)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("'").append(loginuservo.getUserid()).append("',");
		stringbuffer.append("'").append(loginuservo.getUsername()).append("',");
		stringbuffer.append("'").append(loginuservo.getLogintime())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();

		try {
			j = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("while inserting user:" + exception);
		}
		return j;
	}

	public UserVo getUserByPersonId(int i) throws Exception {
		UserVo uservo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(
					Constants.GET_USER_BY_PERSONID,
					com.eespc.tracking.bo.UserVo.class);
			if (list != null && list.size() > 0)
				uservo = (UserVo) list.get(0);
		} catch (Exception exception) {
			throw new Exception(exception);
		}
		return uservo;
	}

	public UserVo getByLoginName(String s) throws Exception {
		if (s == null)
			throw new IllegalArgumentException("Login Name is mandatory");
		UserVo uservo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new String(s));
			List list = sqlutil.execQueryUsingConstructor(
					Constants.GET_USER_BY_LOGIN_NAME,
					com.eespc.tracking.bo.UserVo.class);
			if (list != null && list.size() > 0)
				uservo = (UserVo) list.get(0);
		} catch (Exception exception) {
			throw new Exception(exception);
		}
		return uservo;
	}

	public int addUser(int i, UserVo uservo) throws UserException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("insert into person (PERSONID,ADDRESSID,FIRSTNAME,LASTNAME,MIDDLEINITIAL,TITLE,PHONE,ALTERNATEPHONE,FAX,PAGER,EMAIL,ALTERNATEEMAIL,ENTEREDBY,ENTEREDDATE,UPDATEDBY,UPDATEDDATE)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(i);
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getFirstName()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getLastName()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getMiddleName()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getTitle()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getPhone()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getAlternatePhone()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getFax()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getPager()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getEmail()).append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(uservo.getAlternateEmail()).append("'");
		stringbuffer.append(",");
		stringbuffer.append(0);
		stringbuffer.append(",");
		stringbuffer.append("sysdate()");
		stringbuffer.append(",");
		stringbuffer.append("'1'");
		stringbuffer.append(",");
		stringbuffer.append("sysdate()");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		int j = -99;
		byte byte0 = -99;
		try {
			int k = sqlutil.insert(stringbuffer.toString());
			if (k > 0) {
				j = insertInUserTable(uservo, k);
				if (j > 0)
					insertUserToFacility(uservo, j);
			}
		} catch (Exception exception) {
			System.out.println("" + exception);
			throw new UserException("Error while inserting user", exception);
		}
		return j;
	}

	private int insertInUserTable(UserVo uservo, int i) throws Exception {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("insert into user (USERID,PERSONID,USERNAME,PASSWORD,DELETEPERMISSION,ACTIVEIND,USERROLE,PASSWORKDCHANGEIND)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(i);
		stringbuffer.append(",");
		stringbuffer.append("'").append(uservo.getLoginId()).append("'");
		stringbuffer.append(",");
		stringbuffer.append("'").append(uservo.getPassWord()).append("'");
		stringbuffer.append(",");

		stringbuffer.append("'");
		stringbuffer.append(uservo.getDelete());
		stringbuffer.append("'").append(",");

		stringbuffer.append("'");
		if (uservo.isActiveInd())
			stringbuffer.append("Y");
		else
			stringbuffer.append("N");
		stringbuffer.append("'").append(",");
		UserRoleEnum userroleenum = uservo.getRole();
		stringbuffer.append(userroleenum != null ? userroleenum.getCode() : -1);
		stringbuffer.append(",");
		stringbuffer.append("'N'");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		int j = sqlutil.insert(stringbuffer.toString());
		return j;
	}

	private void insertUserToFacility(UserVo uservo, int i) {
		String as[] = uservo.getClientAccessForInsert();
		logger.debug((new StringBuilder())
				.append("Client Access size ")
				.append(as != null ? (new StringBuffer(String
						.valueOf(as.length))).toString() : "EMPTY").toString());
		if (as != null) {
			int j = as.length;
			for (int k = 0; k < j; k++) {
				String s = as[k];
				if (!UtilityObject.isNotEmpty(s))
					continue;
				StringBuffer stringbuffer = new StringBuffer();
				stringbuffer
						.append("insert into USERTOFACILITY (FACILITYID, USERID) values (");
				stringbuffer.append(s);
				stringbuffer.append(",");
				stringbuffer.append(i);
				stringbuffer.append(")");
				SqlUtil sqlutil = new SqlUtil();
				try {
					sqlutil.insert(stringbuffer.toString());
				} catch (Exception exception) {
					logger.error(exception);
				}
			}

		}
	}

	public UserVo validateUser(String s, String s1) throws UserException {
		if (s == null || s1 == null)
			throw new IllegalArgumentException(
					"Login Id / Password is mandatory");
		UserVo uservo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new String(s));
			sqlutil.addInParam(new String(s1));
			List list = sqlutil.execQueryUsingConstructor(
					Constants.AUTHENTICATE_USER,
					com.eespc.tracking.bo.UserVo.class);
			if (list != null && list.size() > 0)
				uservo = (UserVo) list.get(0);
			System.out.println((new StringBuilder()).append("sen is")
					.append(uservo).toString());
		} catch (Exception exception) {
			throw new UserException(exception);
		}
		return uservo;
	}

	public UserVo isexistUserid(String s) throws UserException {
		if (s == null)
			throw new IllegalArgumentException(
					"Login Id / Password is mandatory");
		UserVo uservo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new String(s));

			List list = sqlutil
					.execQueryUsingConstructor(
							"select * from person as per  left join user as usr  on per.personid = usr.personid where usr.username=?",
							com.eespc.tracking.bo.UserVo.class);
			if (list != null && list.size() > 0)
				uservo = (UserVo) list.get(0);
			System.out.println((new StringBuilder()).append("sen is")
					.append(uservo).toString());
		} catch (Exception exception) {
			throw new UserException(exception);
		}
		return uservo;
	}

	public UserVo findByPrimaryKey(int i) throws UserException {
		UserVo uservo = null;
		String s = "select * from user usr, person prsn where usr.personid=prsn.personid and usr.userid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.UserVo.class);
			if (list != null && list.size() > 0)
				uservo = (UserVo) list.get(0);
		} catch (Exception exception) {
			throw new UserException(exception);
		}
		return uservo;
	}

	public boolean isUserExist(String s, String s1, String s2)
			throws UserException {
		boolean flag = false;
		if ((s == null || s.trim().length() == 0)
				&& (s1 == null || s1.trim().length() == 0)
				&& (s2 == null || s2.trim().length() == 0))
			throw new UserException(
					"Firstname or LastName or MiddleName is required to find the user");
		List list = search(s, s1, s2);
		if (list != null && list.size() != 0)
			flag = true;
		return flag;
	}

	public List search(String s, String s1, String s2) throws UserException {
		Object obj = new ArrayList();
		if ((s == null || s.trim().length() == 0)
				&& (s1 == null || s1.trim().length() == 0)
				&& (s2 == null || s2.trim().length() == 0))
			throw new UserException(
					"Firstname or LastName or MiddleName is required to search the user");
		try {
			SqlUtil sqlutil = new SqlUtil();
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer
					.append("select * from user usr, person prsn where usr.personid=prsn.personid ");
			if (UtilityObject.isNotEmpty(s))
				stringbuffer.append("and prsn.firstname='").append(s)
						.append("'");
			if (UtilityObject.isNotEmpty(s1))
				stringbuffer.append("and prsn.lastname='").append(s1)
						.append("'");
			if (UtilityObject.isNotEmpty(s2))
				stringbuffer.append("and prsn.middleinitial='").append(s2)
						.append("'");
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.UserVo.class);
		} catch (Exception exception) {
			throw new UserException(exception);
		}
		return ((List) (obj));
	}

	public List getClientAccessList(int i) {
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(
					Constants.GET_ALL_FACILITY_FOR_USERID,
					com.eespc.tracking.bo.FacilityVo.class);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return ((List) (obj));
	}

	public List getAllUser() {
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(Constants.GET_ALL_USER,
					com.eespc.tracking.bo.UserVo.class);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return ((List) (obj));
	}

	public List getLoginDetailUser() {
		System.out
				.println("-=======================================================================================================");
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(
					"select * from logindetail",
					com.eespc.tracking.bo.LoginUserVo.class);
		} catch (Exception exception) {
			System.out.println("logindetail:" + exception);
			logger.error(exception);
		}
		return ((List) (obj));
	}

	public void changePassword(int id, String newpass) throws UserException {

		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update user set password=? where PERSONID=?");
		sqlutil.addInParam(newpass);
		sqlutil.addInParam(id);
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Changing Password: " + exception);
			UserException userexception = new UserException(
					"While Changing Password.");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	public void updatePerson(UserVo uservo) throws UserException {
		if (uservo == null)
			throw new UserException("UserVo cannot be NULL");
		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update person set ");
		stringbuffer.append("FIRSTNAME=?, ");
		stringbuffer.append("LASTNAME=?, ");
		stringbuffer.append("MIDDLEINITIAL=?, ");
		stringbuffer.append("TITLE=?, ");
		stringbuffer.append("PHONE=?, ");
		stringbuffer.append("ALTERNATEPHONE=?, ");
		stringbuffer.append("FAX=?, ");
		stringbuffer.append("PAGER=?, ");
		stringbuffer.append("EMAIL=?, ");
		stringbuffer.append("ALTERNATEEMAIL=?, ");
		stringbuffer.append("UPDATEDBY=?, ");
		stringbuffer.append("UPDATEDDATE=sysdate() ");
		stringbuffer.append("where personid=? ");
		sqlutil.addInParam(uservo.getFirstName());
		sqlutil.addInParam(uservo.getLastName());
		sqlutil.addInParam(uservo.getMiddleName());
		sqlutil.addInParam(uservo.getTitle());
		sqlutil.addInParam(uservo.getPhone());
		sqlutil.addInParam(uservo.getAlternatePhone());
		sqlutil.addInParam(uservo.getFax());
		sqlutil.addInParam(uservo.getPager());
		sqlutil.addInParam(uservo.getEmail());
		sqlutil.addInParam(uservo.getAlternateEmail());
		sqlutil.addInParam(new Integer(1));
		sqlutil.addInParam(new Integer(uservo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			UserException userexception = new UserException(
					"While Updating Address.");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	public void Deleteusertofacility(int userid) throws UserException {

		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from usertofacility where USERID=? ");
		sqlutil.addInParam(userid);
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error:While Deleting Usertofacility:"
					+ exception);
			UserException userexception = new UserException(
					"While Deleting Usertofacility");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	public void Deleteuser(int userid) throws UserException {

		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from user where USERID=? ");
		sqlutil.addInParam(userid);
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error:While Deleting User:" + exception);
			UserException userexception = new UserException(
					"While Deleting User");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	public void Deleteperson(int addressid) throws UserException {

		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from person where ADDRESSID=? ");
		sqlutil.addInParam(addressid);
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error:While Person:" + exception);
			UserException userexception = new UserException(
					"While Deleting Person");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	public void Deleteaddress(int addressid) throws UserException {

		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from address where ADDRESSID=? ");
		sqlutil.addInParam(addressid);
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error:While Address:" + exception);
			UserException userexception = new UserException(
					"While Deleting Address");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	private void updateUserTable(UserVo uservo) throws UserException {
		if (uservo == null)
			throw new UserException("UserVo cannot be NULL");
		SqlUtil sqlutil = new SqlUtil();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update user set ");
		stringbuffer.append("USERNAME=?, ");
		stringbuffer.append("PASSWORD=?, ");
		stringbuffer.append("DELETEPERMISSION=?, ");
		stringbuffer.append("ACTIVEIND=?, ");
		stringbuffer.append("PASSWORKDCHANGEIND=?, ");
		stringbuffer.append("USERROLE=? ");
		stringbuffer.append("where userid=? ");
		sqlutil.addInParam(uservo.getLoginId());
		sqlutil.addInParam(uservo.getPassWord());
		sqlutil.addInParam(uservo.getDelete());
		sqlutil.addInParam(UtilityObject.booleanToString(uservo.isActiveInd()));
		sqlutil.addInParam("Y");
		sqlutil.addInParam(new Integer(uservo.getRole() != null ? uservo
				.getRole().getCode() : -99));
		sqlutil.addInParam(new Integer(uservo.getUserId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			UserException userexception = new UserException(
					"While Updating Address.");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	private void updateUserToFacility(UserVo uservo) throws UserException {
		if (uservo == null)
			throw new UserException("UserVo cannot be NULL");
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from usertofacility where userid=").append(
				uservo.getUserId());
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			UserException userexception = new UserException(
					"While Updating User To Facility.");
			userexception.initCause(exception);
			throw userexception;
		}
		insertUserToFacility(uservo, uservo.getUserId());
	}

	public void update(UserVo uservo) throws UserException {
		if (uservo == null)
			throw new UserException("UserVo cannot be NULL");
		try {
			AddressEntity.update(uservo.getAddressForInsert());
			updatePerson(uservo);
			updateUserTable(uservo);
			updateUserToFacility(uservo);
		} catch (Exception exception) {
			UserException userexception = new UserException(
					"While Updating User.");
			userexception.initCause(exception);
			throw userexception;
		}
	}

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.UserEntity.class);
	
	
	
	public List getOneUser() {
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			String s = "select * from user usr, person prsn where usr.personid=prsn.personid and usr.username='kasi' ";
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.UserVo.class);
		} catch (Exception exception) {
			logger.error(exception);
		}
		return ((List) (obj));
	}

}