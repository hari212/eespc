package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.bo.Constants;
import com.eespc.tracking.bo.ContactVo;
import com.eespc.tracking.exceptions.AddressException;
import com.eespc.tracking.exceptions.ContactException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.entity:
//            AddressEntity

public class ContactEntity {

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.ContactEntity.class);

	public ContactEntity() {
	}

	public static List getContactList(int i) {
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(
					Constants.GET_CONTACT_LIST_4_FACILITY,
					com.eespc.tracking.bo.ContactVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR)) {
				logger.error(exception);
			}
		}
		return ((List) (obj));
	}

	public static int addContact(ContactVo contactvo) throws ContactException {
		int i = -99;
		int j = -99;
		try {
			j = AddressEntity.addAddress(contactvo.getAddressForInsert());
		} catch (AddressException addressexception) {
			throw new ContactException("Error while creating contact address",
					addressexception);
		}
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into person values (null,");
		stringbuffer.append(j);
		stringbuffer.append(",").append("'");
		stringbuffer.append(contactvo.getFirstName());
		stringbuffer.append("'").append(",");
		stringbuffer.append("'").append(contactvo.getLastName());
		stringbuffer.append("'").append(",");
		stringbuffer.append("'").append(contactvo.getMiddleName());
		stringbuffer.append("'").append(",");
		stringbuffer.append("'").append(contactvo.getTitle());
		stringbuffer.append("'").append(",");
		if (contactvo.getPhone() != null
				&& contactvo.getPhone().trim().length() > 0) {
			stringbuffer.append("'").append(contactvo.getPhone());
			stringbuffer.append("'");
		} else {
			stringbuffer.append("null");
		}
		stringbuffer.append(",");
		if (contactvo.getAlternatePhone() != null
				&& contactvo.getAlternatePhone().trim().length() > 0) {
			stringbuffer.append("'").append(contactvo.getAlternatePhone());
			stringbuffer.append("'");
		} else {
			stringbuffer.append("null");
		}
		stringbuffer.append(",");
		if (contactvo.getFax() != null
				&& contactvo.getFax().trim().length() > 0) {
			stringbuffer.append("'").append(contactvo.getFax());
			stringbuffer.append("'");
		} else {
			stringbuffer.append("null");
		}
		stringbuffer.append(",");
		if (contactvo.getPager() != null
				&& contactvo.getPager().trim().length() > 0) {
			stringbuffer.append("'").append(contactvo.getPager());
			stringbuffer.append("'");
		} else {
			stringbuffer.append("null");
		}
		stringbuffer.append(",").append("'");
		stringbuffer.append(contactvo.getEmail());
		stringbuffer.append("'").append(",");
		stringbuffer.append("'");
		stringbuffer.append(contactvo.getAlternateEmail());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("null").append(",");
		stringbuffer.append("null").append(",");
		stringbuffer.append("null").append(",");
		stringbuffer.append("null");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		int k = -99;
		try {
			k = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			throw new ContactException("Error while inserting user", exception);
		}
		StringBuffer stringbuffer1 = new StringBuffer();
		stringbuffer1.append("insert into contact values(null,");
		stringbuffer1.append(k).append(",");
		stringbuffer1.append(contactvo.getContactType());
		stringbuffer1.append(",");
		stringbuffer1.append("'");
		stringbuffer1.append("Y");
		stringbuffer1.append("'");
		stringbuffer1.append(",");
		stringbuffer1.append("null").append(",");
		stringbuffer1.append("null").append(",");
		stringbuffer1.append("null").append(",");
		stringbuffer1.append("null");
		stringbuffer1.append(")");
		try {
			i = sqlutil.insert(stringbuffer1.toString());
		} catch (Exception exception1) {
			throw new ContactException("Error while inserting contact",
					exception1);
		}
		return i;
	}

	public static void insertToContactToFacility(int i, int j)
			throws ContactException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into contacttofacility ");
		stringbuffer.append(" (contactid, facilityid) values (");
		stringbuffer.append(i).append(",");
		stringbuffer.append(j).append(")");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			throw new ContactException(
					"Error while inserting contact2facility", exception);
		}
	}

	public static void updateContact(ContactVo contactvo)
			throws ContactException {
		try {
			AddressEntity.update(contactvo.getAddressForInsert());
		} catch (AddressException addressexception) {
			throw new ContactException("Error while updating contact address",
					addressexception);
		}
		String s = UtilityObject.getDateStringForDB(
				UtilityObject.convertToString(new Date()), true);
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
		stringbuffer.append("UPDATEDDATE=? ");
		stringbuffer.append("where PERSONID=?");

		SqlUtil utilObj = new SqlUtil();
		utilObj.addInParam(contactvo.getFirstName());
		utilObj.addInParam(contactvo.getLastName());

		utilObj.addInParam(contactvo.getMiddleName());
		utilObj.addInParam(contactvo.getTitle());
		if (contactvo.getPhone() != null
				&& contactvo.getPhone().trim().length() > 0) {
			utilObj.addInParam(contactvo.getPhone());
		} else {
			utilObj.addInParam(null);
		}

		if (contactvo.getAlternatePhone() != null
				&& contactvo.getAlternatePhone().trim().length() > 0) {
			utilObj.addInParam(contactvo.getAlternatePhone());
		} else {
			utilObj.addInParam(null);
		}

		if (contactvo.getFax() != null
				&& contactvo.getFax().trim().length() > 0) {
			utilObj.addInParam(contactvo.getFax());
		} else {
			utilObj.addInParam(null);
		}

		if (contactvo.getPager() != null
				&& contactvo.getPager().trim().length() > 0) {
			utilObj.addInParam(contactvo.getPager());
		} else {
			utilObj.addInParam(null);
		}

		utilObj.addInParam(contactvo.getEmail());
		utilObj.addInParam(contactvo.getAlternateEmail());
		utilObj.addInParam(s);
		utilObj.addInParam(contactvo.getId());
		try {
			utilObj.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new ContactException("Error while updating user", exception);
		}

		StringBuffer stringbuffer1 = new StringBuffer();
		stringbuffer1
				.append("update contact set CONTACTTYPE=?,UPDATEDDATE=?,PERSONID=? ,VALID_IND = ? where CONTACTID=?");
		SqlUtil sqlutil1 = new SqlUtil();
		sqlutil1.addInParam(String.valueOf(contactvo.getContactType()));
		sqlutil1.addInParam(String.valueOf(s));
		sqlutil1.addInParam(String.valueOf(contactvo.getId()));
		sqlutil1.addInParam("Y");
		sqlutil1.addInParam(String.valueOf(contactvo.getContactId()));
		try {
			sqlutil1.execForDmlUsingQuery(stringbuffer1.toString());
		} catch (Exception exception1) {
			throw new ContactException(exception1);
		}
	}

	/*
	 * public static void deleteContact(ContactVo contactvo) throws
	 * ContactException { try {
	 * AddressEntity.update(contactvo.getAddressForInsert()); }
	 * catch(AddressException addressexception) { throw new
	 * ContactException("Error while updating contact address",
	 * addressexception); } String s =
	 * UtilityObject.getDateStringForDB(UtilityObject.convertToString(new
	 * Date()), true); StringBuffer stringbuffer = new StringBuffer();
	 * stringbuffer.append("update person set ");
	 * stringbuffer.append("FIRSTNAME=?, ");
	 * stringbuffer.append("LASTNAME=?, ");
	 * stringbuffer.append("MIDDLEINITIAL=?, ");
	 * stringbuffer.append("TITLE=?, "); stringbuffer.append("PHONE=?, ");
	 * stringbuffer.append("ALTERNATEPHONE=?, ");
	 * stringbuffer.append("FAX=?, "); stringbuffer.append("PAGER=?, ");
	 * stringbuffer.append("EMAIL=?, ");
	 * stringbuffer.append("ALTERNATEEMAIL=?, ");
	 * stringbuffer.append("UPDATEDDATE=? ");
	 * stringbuffer.append("where PERSONID=?");
	 * 
	 * SqlUtil utilObj = new SqlUtil();
	 * utilObj.addInParam(contactvo.getFirstName());
	 * utilObj.addInParam(contactvo.getLastName());
	 * 
	 * utilObj.addInParam(contactvo.getMiddleName());
	 * utilObj.addInParam(contactvo.getTitle()); if(contactvo.getPhone() != null
	 * && contactvo.getPhone().trim().length() > 0) {
	 * utilObj.addInParam(contactvo.getPhone()); } else {
	 * utilObj.addInParam(null); }
	 * 
	 * if(contactvo.getAlternatePhone() != null &&
	 * contactvo.getAlternatePhone().trim().length() > 0) {
	 * utilObj.addInParam(contactvo.getAlternatePhone()); } else {
	 * utilObj.addInParam(null); }
	 * 
	 * if(contactvo.getFax() != null && contactvo.getFax().trim().length() > 0)
	 * { utilObj.addInParam(contactvo.getFax()); } else {
	 * utilObj.addInParam(null); }
	 * 
	 * if(contactvo.getPager() != null && contactvo.getPager().trim().length() >
	 * 0) { utilObj.addInParam(contactvo.getPager()); } else {
	 * utilObj.addInParam(null); }
	 * 
	 * 
	 * utilObj.addInParam(contactvo.getEmail());
	 * utilObj.addInParam(contactvo.getAlternateEmail()); utilObj.addInParam(s);
	 * utilObj.addInParam(contactvo.getId()); try {
	 * utilObj.execForDmlUsingQuery(stringbuffer.toString()); } catch(Exception
	 * exception) { throw new ContactException("Error while updating user",
	 * exception); } StringBuffer stringbuffer1 = new StringBuffer();
	 * stringbuffer1.append(
	 * "update contact set CONTACTTYPE=?,UPDATEDDATE=?,PERSONID=? where CONTACTID=?"
	 * ); SqlUtil sqlutil1 = new SqlUtil();
	 * sqlutil1.addInParam(String.valueOf(contactvo.getContactType()));
	 * sqlutil1.addInParam(String.valueOf(s));
	 * sqlutil1.addInParam(String.valueOf(contactvo.getId()));
	 * sqlutil1.addInParam(String.valueOf(contactvo.getContactId())); try {
	 * sqlutil1.execForDmlUsingQuery(stringbuffer1.toString()); }
	 * catch(Exception exception1) { throw new ContactException(exception1); } }
	 */

}
