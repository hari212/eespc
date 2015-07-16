package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.bo.myenum.UserRoleEnum;
import com.eespc.tracking.entity.AddressEntity;
import com.eespc.tracking.entity.UserEntity;
import com.eespc.tracking.util.UtilityObject;

public class UserVo implements Serializable {

	public UserVo() {
		id = -99;
		firstName = null;
		lastName = null;
		middleName = null;
		title = null;
		phone = null;
		alternatePhone = null;
		fax = null;
		pager = null;
		email = null;
		alternateEmail = null;
		loginId = null;
		passWord = null;
		passWordChangeInd = false;
		activeInd = false;
		delete = null;
		clientAccess = null;
		addressId = -99;
		address = null;
		clientAccessObjects = null;
		userId = -99;
		isAdminUser = false;
		role = null;
	}

	public UserVo(ResultSet resultset) throws SQLException {
		id = -99;
		firstName = null;
		lastName = null;
		middleName = null;
		title = null;
		phone = null;
		alternatePhone = null;
		fax = null;
		pager = null;
		email = null;
		alternateEmail = null;
		loginId = null;
		passWord = null;
		passWordChangeInd = false;
		activeInd = false;
		delete = null;
		clientAccess = null;
		addressId = -99;
		address = null;
		clientAccessObjects = null;
		userId = -99;
		isAdminUser = false;
		role = null;
		id = resultset.getInt("personid");
		firstName = resultset.getString("firstname");
		lastName = resultset.getString("lastname");
		middleName = resultset.getString("middleinitial");
		title = resultset.getString("title");
		phone = resultset.getString("phone");
		alternatePhone = resultset.getString("alternatephone");
		fax = resultset.getString("fax");
		email = resultset.getString("email");
		alternateEmail = resultset.getString("alternateemail");
		if (resultset.getString("addressid") != null)
			addressId = resultset.getInt("addressid");
		try {
			loginId = resultset.getString("username");
			passWord = resultset.getString("password");
			String s = resultset.getString("passworkdchangeind");
			if (s != null && s.trim().equalsIgnoreCase("Y"))
				passWordChangeInd = true;
			activeInd = UtilityObject.convertBoolean(resultset
					.getString("ACTIVEIND"));
			delete = resultset.getString("DELETEPERMISSION");
			role = UserRoleEnum.get(resultset.getInt("userrole"));
			userId = resultset.getInt("userid");
		} catch (SQLException sqlexception) {
		}
		pager = resultset.getString("PAGER");
	}

	public AddressVo getAddressForInsert() {
		return address;
	}

	public AddressVo getAddress() {
		if (addressId >= 0)
			try {
				address = AddressEntity.getAddressById(addressId);
			} catch (Exception exception) {
				if (logger.isEnabledFor(Priority.ERROR))
					logger.error(exception);
			}
		return address;
	}

	public String getAlternateEmail() {
		return alternateEmail;
	}

	public String getAlternatePhone() {
		return alternatePhone;
	}

	public String getEmail() {
		return email;
	}

	public String getFax() {
		return fax;
	}

	public String getFirstName() {
		return firstName;
	}

	public int getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getPager() {
		return pager;
	}

	public String getPassWord() {
		return passWord;
	}

	public boolean isPassWordChangeInd() {
		return passWordChangeInd;
	}

	public String getPhone() {
		return phone;
	}

	public String getTitle() {
		return title;
	}

	public String getDelete() {
		return delete;
	}

	public void setDelete(String s) {
		delete = s;
	}

	public void setAddress(AddressVo addressvo) {
		address = addressvo;
	}

	public void setAlternateEmail(String s) {
		alternateEmail = s;
	}

	public void setAlternatePhone(String s) {
		alternatePhone = s;
	}

	public void setEmail(String s) {
		email = s;
	}

	public void setFax(String s) {
		fax = s;
	}

	public void setFirstName(String s) {
		firstName = s;
	}

	public void setId(int i) {
		id = i;
	}

	public void setLastName(String s) {
		lastName = s;
	}

	public void setLoginId(String s) {
		loginId = s;
	}

	public void setMiddleName(String s) {
		middleName = s;
	}

	public void setPager(String s) {
		pager = s;
	}

	public void setPassWord(String s) {
		passWord = s;
	}

	public void setPassWordChangeInd(boolean flag) {
		passWordChangeInd = flag;
	}

	public void setPhone(String s) {
		phone = s;
	}

	public void setTitle(String s) {
		title = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer("UserVo :");
		stringbuffer.append("id=").append(id).append(" ");
		stringbuffer.append("firstName=").append(firstName).append(" ");
		stringbuffer.append("lastName=").append(lastName).append(" ");
		stringbuffer.append("middleName=").append(middleName).append(" ");
		stringbuffer.append("title=").append(title).append(" ");
		stringbuffer.append("phone=").append(phone).append(" ");
		stringbuffer.append("alternatePhone=").append(alternatePhone)
				.append(" ");
		stringbuffer.append("fax=").append(fax).append(" ");
		stringbuffer.append("pager=").append(pager).append(" ");
		stringbuffer.append("email=").append(email).append(" ");
		stringbuffer.append("alternateEmail=").append(alternateEmail)
				.append(" ");
		stringbuffer.append("loginId=").append(loginId).append(" ");
		stringbuffer.append("passWord=").append(passWord).append(" ");
		stringbuffer.append("passWordChangeInd=").append(passWordChangeInd)
				.append(" ");
		stringbuffer.append("addressId=").append(addressId).append(" ");
		return stringbuffer.toString();
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int i) {
		addressId = i;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum userroleenum) {
		role = userroleenum;
	}

	public boolean isActiveInd() {
		return activeInd;
	}

	public void setActiveInd(boolean flag) {
		activeInd = flag;
	}

	private List getClientAccessObjects() {
		if (clientAccessObjects == null) {
			UserEntity userentity = new UserEntity();
			clientAccessObjects = userentity.getClientAccessList(getUserId());
		}
		return clientAccessObjects;
	}

	public String[] getClientAccess() {
		List list = getClientAccessObjects();
		int i = list == null ? 0 : list.size();
		if (i > 0) {
			String as[] = new String[i];
			for (int j = 0; j < i; j++) {
				FacilityVo facilityvo = (FacilityVo) list.get(j);
				if (facilityvo != null)
					as[j] = (new StringBuffer(
							String.valueOf(facilityvo.getId()))).toString();
			}

			clientAccess = as;
		}
		return clientAccess;
	}

	public String[] getClientAccessForInsert() {
		return clientAccess;
	}

	public void setClientAccess(String as[]) {
		clientAccess = as;
	}

	public String getClientAccessString() {
		StringBuffer stringbuffer = new StringBuffer();
		List list = getClientAccessObjects();
		int i = list == null ? 0 : list.size();
		if (i > 0) {
			for (int j = 0; j < i; j++) {
				FacilityVo facilityvo = (FacilityVo) list.get(j);
				if (facilityvo != null)
					stringbuffer.append(facilityvo.getClientName())
							.append("\n");
			}

		}
		return stringbuffer.toString();
	}

	public String getUserRoleString() {
		if (role != null)
			return role.getName();
		else
			return "";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int i) {
		userId = i;
	}

	public String getDisabledIndicator() {
		return isActiveInd() ? "Active" : "Disabled";
	}

	public boolean isAdminUser() {
		UserRoleEnum userroleenum = getRole();
		if (userroleenum != null)
			return userroleenum.getCode() == UserRoleEnum.SUPER_USER.getCode();
		else
			return isAdminUser;
	}

	public void setAdminUser(boolean flag) {
		isAdminUser = flag;
	}

	protected int id;
	protected String firstName;
	protected String lastName;
	protected String middleName;
	protected String title;
	protected String phone;
	protected String alternatePhone;
	protected String fax;
	protected String pager;
	protected String email;
	protected String alternateEmail;
	protected String loginId;
	protected String passWord;
	protected boolean passWordChangeInd;
	protected boolean activeInd;
	protected String delete;
	protected String clientAccess[];
	protected int addressId;
	protected AddressVo address;
	private List clientAccessObjects;
	protected int userId;
	private boolean isAdminUser;
	static Logger logger = Logger.getLogger(com.eespc.tracking.bo.UserVo.class);
	protected UserRoleEnum role;

}