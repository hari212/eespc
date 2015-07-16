package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.RpzVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class RpzEntity {

	public RpzEntity() {
	}

	public static RpzVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, rpz rz where bld.buildingid = rz.buildingid and rz.rpzid =?";
		RpzVo rpzvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.RpzVo.class);
			if (list != null && list.size() > 0)
				rpzvo = (RpzVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return rpzvo;
	}

	public static int add(RpzVo rpzvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,").append(rpzvo)
					.toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into rpz ");
		stringbuffer
				.append("(RPZID,BUILDINGID,FACILITYDESIGNATEDID,YEARINSTALLED,");
		stringbuffer
				.append("SERIALNUMBER,MAKE,MODEL,TYPE,PERMITSTATUS,SIZE,LOCATION,RCOMMENTS,MODIFIEDINPAST,DISCONNECTEDYEAR,");
		stringbuffer.append("DOBPERMITNUMBER,DEPPERMITNUMBER,DOBSIGNOFF)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(rpzvo.getBuildingId()).append(",");
		stringbuffer.append("'").append(rpzvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(rpzvo.getYearInstalled()).append("',");
		stringbuffer.append("'").append(rpzvo.getSerialNumber()).append("',");
		stringbuffer.append("'").append(rpzvo.getMake()).append("',");
		stringbuffer.append("'").append(rpzvo.getModel()).append("',");
		stringbuffer.append("'").append(rpzvo.getType()).append("',");

		/*
		 * if(rpzvo.isPermit()) stringbuffer.append("'Y',"); else
		 * stringbuffer.append("'N',");
		 */
		stringbuffer.append("'" + rpzvo.isPermit() + "'").append(",");
		stringbuffer.append("'").append(rpzvo.getSize()).append("',");
		stringbuffer.append(rpzvo.getLocation()).append(",");
		stringbuffer.append("'").append(rpzvo.getComments()).append("',");
		stringbuffer.append(rpzvo.getModifiedInPast()).append(",");
		stringbuffer.append("'").append(rpzvo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(rpzvo.getDobPermitNumber())
				.append("',");
		stringbuffer.append("'").append(rpzvo.getDepPermitNumber())
				.append("',");
		stringbuffer.append("'").append(rpzvo.getDobsignoff()).append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out
					.println("While Adding RPZ Installation Info" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding RPZ");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(RpzVo rpzvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,").append(rpzvo)
					.toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update rpz set ");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("YEARINSTALLED=?,");
		stringbuffer.append("SERIALNUMBER=?,");
		stringbuffer.append("MAKE=?,");
		stringbuffer.append("MODEL=?,");
		stringbuffer.append("TYPE=?,");
		stringbuffer.append("PERMITSTATUS=?,");
		stringbuffer.append("SIZE=?,");
		stringbuffer.append("LOCATION=?,");
		stringbuffer.append("RCOMMENTS=?,");
		stringbuffer.append("MODIFIEDINPAST=?,");
		stringbuffer.append("DISCONNECTEDYEAR=?,");
		stringbuffer.append("DOBPERMITNUMBER=?,");
		stringbuffer.append("DEPPERMITNUMBER=?,");
		stringbuffer.append("DOBSIGNOFF=? ");
		stringbuffer.append("where rpzid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(rpzvo.getFacilityDesignatedId());
		sqlutil.addInParam(rpzvo.getYearInstalled());
		sqlutil.addInParam(rpzvo.getSerialNumber());
		sqlutil.addInParam(rpzvo.getMake());
		sqlutil.addInParam(rpzvo.getModel());
		sqlutil.addInParam(rpzvo.getType());

		/*
		 * if(rpzvo.isPermit()) sqlutil.addInParam("Y"); else
		 * sqlutil.addInParam("N");
		 */
		sqlutil.addInParam(rpzvo.isPermit());
		sqlutil.addInParam(rpzvo.getSize());
		sqlutil.addInParam(new Integer(rpzvo.getLocation()));
		sqlutil.addInParam(rpzvo.getComments());
		sqlutil.addInParam(String.valueOf(rpzvo.getModifiedInPast()));
		sqlutil.addInParam(rpzvo.getDisconnectedYear());
		sqlutil.addInParam(rpzvo.getDobPermitNumber());
		sqlutil.addInParam(rpzvo.getDepPermitNumber());
		sqlutil.addInParam(rpzvo.getDobsignoff());
		sqlutil.addInParam(new Integer(rpzvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating RPZ " + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating RPZ");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(RpzVo rpzvo) throws TrackingException {
		String as[] = { "delete from rpzpermitinfo where rpzid=?",
				"delete from rpzinspectioninfo where rpzid=?",
				"delete from rpz where rpzid=?" };
		for (int i = 0; i <= 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(rpzvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				System.out
						.println("delete(RpzVo rpzvo) Exception:" + exception);

				TrackingException trackingexception = new TrackingException(
						"While Updating RPZ");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static List getInstallationInfo(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select rpzinspectioninfo as permit_id, ");
		stringbuffer.append("rpzid as object_id, ");
		stringbuffer.append("7 as object_type, null as permit_number, ");
		stringbuffer.append("lastinspectiondate as permit_issue_date, ");
		stringbuffer.append("nextinspectiondate as permit_exp_date, ");
		stringbuffer.append("note as note, ");
		stringbuffer.append("2 as dept_id ");
		stringbuffer.append("from rpzinspectioninfo ");
		stringbuffer.append("where rpzid = ? ");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.PermitInfoVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getInstallationInfo(")
							.append(i).append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static int addInstallation(PermitInfoVo permitinfovo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into rpzinspectioninfo ");
		stringbuffer.append("(rpzinspectioninfo, rpzid, ");
		stringbuffer.append("lastinspectiondate, nextinspectiondate, note) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(permitinfovo.getObjectId()).append(",");
		// stringbuffer.append("'"+permitinfovo.isDepPermitInspected()+"'").append(",");
		if (permitinfovo.getIssueDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							permitinfovo.getIssueDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null,");

		if (permitinfovo.getExpirationDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							permitinfovo.getExpirationDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(permitinfovo.getNote()).append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out
					.println("While Adding RPZ Installation Info" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding RPZ Installation Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deleteInstallation(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from rpzinspectioninfo");
		stringbuffer.append(" where rpzinspectioninfo=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating RPZ Installation Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updateInstallation(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update rpzinspectioninfo ");
		stringbuffer
				.append("set lastinspectiondate=?, nextinspectiondate=?, note=? ");
		stringbuffer.append(" where rpzinspectioninfo=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			// sqlutil.addInParam(permitinfovo.isDepPermitInspected());
			if (permitinfovo.getIssueDate() != null)
				sqlutil.addInParam(UtilityObject.convertToString(
						permitinfovo.getIssueDate(), "yyyy-MM-dd"));
			else
				sqlutil.addInParam(null);

			if (permitinfovo.getExpirationDate() != null)
				sqlutil.addInParam(UtilityObject.convertToString(
						permitinfovo.getExpirationDate(), "yyyy-MM-dd"));
			else
				sqlutil.addInParam(null);
			sqlutil.addInParam(permitinfovo.getNote());
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating RPZ Installation Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static int addPermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into RPZPERMITINFO ");
		stringbuffer.append("(RPZPERMITID, RPZID, permitnumber, ");
		stringbuffer.append("issuedate, expirationdate, note, depid) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(permitinfovo.getObjectId()).append(",");
		stringbuffer.append("'").append(permitinfovo.getPermitNumber())
				.append("',");
		// stringbuffer.append("'"+permitinfovo.isDepPermitInspected()+"'").append(",");
		if (permitinfovo.getIssueDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							permitinfovo.getIssueDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null,");
		if (permitinfovo.getExpirationDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							permitinfovo.getExpirationDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'").append(permitinfovo.getNote()).append("',");
		stringbuffer.append(permitinfovo.getDepId()).append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding RPZ Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deletePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from RPZPERMITINFO");
		stringbuffer.append(" where RPZPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating RPZ Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updatePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update RPZPERMITINFO ");
		stringbuffer.append("set permitnumber=?,");
		stringbuffer.append(" issuedate=?, expirationdate=?, note=? ");
		stringbuffer.append(" where RPZPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(permitinfovo.getPermitNumber());
		// sqlutil.addInParam(permitinfovo.isDepPermitInspected());
		if (permitinfovo.getIssueDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					permitinfovo.getIssueDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		if (permitinfovo.getIssueDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					permitinfovo.getExpirationDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(permitinfovo.getNote());
		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating RPZ Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getPermitList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select RPZPERMITID as PERMIT_ID, RPZID as OBJECT_ID,  7 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issuedate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID from RPZPERMITINFO where RPZID =?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PermitInfoVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Getting Permit Info list for RPZ");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.RPZID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.SERIALNUMBER, bldg.BUILDINGNAME from rpz stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.RPZListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Boiler List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.RpzEntity.class);

}