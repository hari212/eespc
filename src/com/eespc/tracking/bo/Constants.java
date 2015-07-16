// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Constants.java

package com.eespc.tracking.bo;

public class Constants {

	public Constants() {
	}

	public static final String SESSION_USER = "SESSION_USER";
	public static final String COOKIE_NAME = "EESPC_USERID";
	public static final String QUESTION_MARK_CHARACTER = "?";
	public static final String KEY_MENU_ITEMS = "MENUS_STRING";
	public static final String FACILITY_OBJECT = "FACILITY_OBJECT";
	public static final String FACILITY_NEW_MENU = "3";
	public static final String FACILITY_VIEW_MENU = "4";
	public static String GET_ALL_FACILITY = "select * from facility fac, address addr where fac.addressid = addr.addressid and  fac.validind='Y' ";
	public static String GETTING_FACILITY = "select * from facility fac, address addr where fac.validind ='Y' and fac.addressid = addr.addressid and fac.facilityid=?";
	public static String GET_CONTACT_LIST_4_FACILITY = "select * from person per, contact cont, contacttofacility ctf where per.personid=cont.personid and cont.contactid=ctf.contactid and ctf.facilityid=? and cont.valid_ind='Y'";
	public static String GET_BUILDING_4_FACILITY = "select * from building where facilityid =?";
	public static String GET_BUILDING = "select * from building where buildingid =?";
	public static String GET_USER_BY_USERID = "select * from person as per  left join user as usr  on per.personid = usr.personid where usr.userid=?";
	public static String GET_USER_BY_LOGIN_NAME = "select * from person as per  left join user as usr  on per.personid = usr.personid where usr.username=?";
	public static String GET_USER_BY_PERSONID = "select * from person as per  left join user as usr  on per.personid = usr.personid where per.personid=?";
	public static String GET_ADDRESS = "select * from address where addressid=?";
	public static String AUTHENTICATE_USER = "select * from person as per  left join user as usr  on per.personid = usr.personid where usr.username=? and usr.password=? and usr.ACTIVEIND='Y'";
	public static final String COMMA = ",";
	public static final String QUOTE = "'";
	public static final String CLOSE_BRACKET = ")";
	public static String GET_ALL_FACILITY_FOR_USERID = "select * from facility fac, address addr where fac.addressid = addr.addressid and fac.validind='Y'  and fac.facilityid in (select facilityid from usertofacility where userid=?)";
	public static String GET_ALL_USER = "select * from user usr, person prsn where usr.personid=prsn.personid";

}
