package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.util.UtilityObject;

public class LoginUserVo implements Serializable {

	public LoginUserVo() {
		id = -99;

		userid = null;
		username = null;
		logintime = null;

	}

	public LoginUserVo(ResultSet resultset) throws SQLException {
		id = -99;
		userid = null;
		username = null;
		logintime = null;

		id = UtilityObject.getIntFromString(resultset
				.getString("LOGINDETAILID"));
		userid = resultset.getString("USERID");
		username = resultset.getString("USERNAME");
		logintime = resultset.getString("LOGINTIME");

	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String i) {
		logintime = i;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String i) {
		username = i;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String i) {
		userid = i;
	}

	protected int id;
	protected String userid;
	protected String username;
	protected String logintime;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.LoginUserVo.class);

}