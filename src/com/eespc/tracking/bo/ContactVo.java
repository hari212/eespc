package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            UserVo

public class ContactVo extends UserVo {

	protected int contactType;
	protected int contactId;

	public ContactVo() {
		contactType = -99;
		contactId = -99;
	}

	public ContactVo(ResultSet resultset) throws SQLException {
		super(resultset);
		contactType = -99;
		contactId = -99;
		if (resultset.getString("contacttype") != null) {
			contactType = resultset.getInt("contacttype");
		}
		if (resultset.getString("CONTACTID") != null) {
			contactId = resultset.getInt("CONTACTID");
		}
	}

	public int getContactType() {
		return contactType;
	}

	public void setContactType(int i) {
		contactType = i;
	}

	public int getContactId() {
		return contactId;
	}
}
