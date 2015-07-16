package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.BulkOxygenPermitVo;
import com.eespc.tracking.bo.BulkOxygenTankVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class BulkOxygenTankEntity {

	public BulkOxygenTankEntity() {
	}

	public static BulkOxygenTankVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, bulkoxygentank tank where bld.buildingid = tank.buildingid and tank.bulkoxygentankid =?";
		BulkOxygenTankVo bulkoxygentankvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.BulkOxygenTankVo.class);
			if (list != null && list.size() > 0)
				bulkoxygentankvo = (BulkOxygenTankVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return bulkoxygentankvo;
	}

	public static int add(BulkOxygenTankVo bulkoxygentankvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(bulkoxygentankvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into bulkoxygentank values (null,");
		stringbuffer.append(bulkoxygentankvo.getBuildingId()).append(",");
		/*
		 * if(bulkoxygentankvo.isFireDeptApproval())
		 * stringbuffer.append("'Y'").append(","); else
		 * stringbuffer.append("'N'").append(",");
		 */
		stringbuffer.append("'" + bulkoxygentankvo.isFireDeptApproval() + "'")
				.append(",");
		// stringbuffer.append("'").append(bulkoxygentankvo.isFireDeptApproval()).append("',");
		stringbuffer.append("'")
				.append(bulkoxygentankvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(bulkoxygentankvo.getYearInstalled())
				.append("',");
		stringbuffer.append("'").append(bulkoxygentankvo.getDisconnectedYear())
				.append("',");
		stringbuffer.append(bulkoxygentankvo.getModifiedInPast()).append(",");
		stringbuffer.append("'")
				.append(bulkoxygentankvo.getFireDeptApprovalno()).append("',");
		stringbuffer.append("'").append(bulkoxygentankvo.getPressuretest())
				.append("',");

		if (UtilityObject.isNotEmpty(bulkoxygentankvo.getLasttestdate()))
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(UtilityObject
							.convertToDate(bulkoxygentankvo.getLasttestdate()),
							"yyyy-MM-dd")).append("',");
		else
			stringbuffer.append("null,");

		if (UtilityObject.isNotEmpty(bulkoxygentankvo.getNexttestdate()))
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(UtilityObject
							.convertToDate(bulkoxygentankvo.getNexttestdate()),
							"yyyy-MM-dd")).append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(bulkoxygentankvo.getNexttestdatenote())
				.append("',");
		stringbuffer.append("'").append(bulkoxygentankvo.getBcomments())
				.append("',");
		// stringbuffer.append("'").append(bulkoxygentankvo.getDobsignoff()).append("',");
		stringbuffer.append("'").append(bulkoxygentankvo.getCapacity())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding BulkOxygenTank:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding BulkOxygenTank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(BulkOxygenTankVo bulkoxygentankvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(bulkoxygentankvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update bulkoxygentank set ");
		stringbuffer
				.append("firedeptapproval=?, FACILITYDESIGNATEDID=?,YEARINSTALLED=?,MODIFIEDINPAST=?,DISCONNECTEDYEAR=?, ");
		stringbuffer.append("FIREDEPTAPPROVALNO=?,");
		stringbuffer.append("PRESSURETEST=?,");
		stringbuffer.append("LASTTESTDATE=?,");
		stringbuffer.append("NEXTTESTDATE=?,");
		stringbuffer.append("NEXTTESTDATENOTE=?,");
		stringbuffer.append("BCOMMENTS=?, ");
		// stringbuffer.append("DOBSIGNOFF=?, ");
		stringbuffer.append("CAPACITY=? ");
		stringbuffer.append("where bulkoxygentankid=?");
		SqlUtil sqlutil = new SqlUtil();
		/*
		 * if(bulkoxygentankvo.isFireDeptApproval()) sqlutil.addInParam("Y");
		 * else sqlutil.addInParam("N");
		 */
		sqlutil.addInParam(bulkoxygentankvo.isFireDeptApproval());
		sqlutil.addInParam(bulkoxygentankvo.getFacilityDesignatedId());
		sqlutil.addInParam(bulkoxygentankvo.getYearInstalled());
		sqlutil.addInParam(String.valueOf(bulkoxygentankvo.getModifiedInPast()));
		sqlutil.addInParam(bulkoxygentankvo.getDisconnectedYear());
		sqlutil.addInParam(bulkoxygentankvo.getFireDeptApprovalno());
		sqlutil.addInParam(bulkoxygentankvo.getPressuretest());
		if (UtilityObject.isNotEmpty(bulkoxygentankvo.getLasttestdate()))
			sqlutil.addInParam(UtilityObject.convertToString(UtilityObject
					.convertToDate(bulkoxygentankvo.getLasttestdate()),
					"yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		if (UtilityObject.isNotEmpty(bulkoxygentankvo.getNexttestdate()))
			sqlutil.addInParam(UtilityObject.convertToString(UtilityObject
					.convertToDate(bulkoxygentankvo.getNexttestdate()),
					"yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(bulkoxygentankvo.getNexttestdatenote());
		sqlutil.addInParam(bulkoxygentankvo.getBcomments());
		// sqlutil.addInParam(bulkoxygentankvo.getDobsignoff());
		sqlutil.addInParam(bulkoxygentankvo.getCapacity());
		sqlutil.addInParam(new Integer(bulkoxygentankvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating BulkOxygenTank:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating BulkOxygenTank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(BulkOxygenTankVo bulkoxygentankvo)
			throws TrackingException {
		String as[] = {
				"delete from bulkoxygentankpermitinfo where bulkoxygentankid=?",
				"delete from bulkoxygentank where bulkoxygentankid=?" };
		for (int i = 0; i <= 1; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(bulkoxygentankvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating BulkOxygenTank");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static List getPermitInfo(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select oxygenpermitid as permit_id, ");
		stringbuffer.append("bulkoxygentankid as object_id, ");
		stringbuffer
				.append("4 as object_type, permitnumber as permit_number, ");
		stringbuffer.append("issuedate as permit_issue_date, ");
		stringbuffer.append("2 as dept_id from bulkoxygentankpermitinfo ");
		stringbuffer.append("where bulkoxygentankid=?");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.BulkOxygenPermitVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getPermitInfo(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static int addPermit(BulkOxygenPermitVo bulkoxygenpermitvo)
			throws TrackingException {
		System.out.println("TEST 1");
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		System.out.println("TEST 2");
		stringbuffer.append("insert into bulkoxygentankpermitinfo ");
		stringbuffer.append("(oxygenpermitid, bulkoxygentankid, permitnumber,");
		stringbuffer.append("issuedate) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(bulkoxygenpermitvo.getObjectId()).append(",");
		stringbuffer.append("'").append(bulkoxygenpermitvo.getPermitNumber())
				.append("',");
		System.out.println("TEST 3");

		if (bulkoxygenpermitvo.getIssueDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							bulkoxygenpermitvo.getIssueDate(), "yyyy-MM-dd"))
					.append("')");
		else
			stringbuffer.append("null)");
		System.out.println("TEST 4");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Bulk Oxygen storage Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deletePermit(BulkOxygenPermitVo bulkoxygenpermitvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from bulkoxygentankpermitinfo");
		stringbuffer.append(" where oxygenpermitid=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(bulkoxygenpermitvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Bulk Oxygen storage Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updatePermit(BulkOxygenPermitVo bulkoxygenpermitvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update bulkoxygentankpermitinfo ");
		stringbuffer.append("set permitnumber=?, ");
		stringbuffer.append("issuedate=? ");
		stringbuffer.append(" where oxygenpermitid=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(bulkoxygenpermitvo.getPermitNumber());
			if (bulkoxygenpermitvo.getIssueDate() != null)
				sqlutil.addInParam(UtilityObject.convertToString(
						bulkoxygenpermitvo.getIssueDate(), "yyyy-MM-dd"));
			else
				sqlutil.addInParam(null);
			sqlutil.addInParam(new Integer(bulkoxygenpermitvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Bulk Oxygen storage Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.BULKOXYGENTANKID, stk.FACILITYDESIGNATEDID, bldg.BUILDINGNAME from bulkoxygentank stk, BUILDING bldg where stk.buildingid=bldg.buildingid and  bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.BulkOxygenListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting B-O-Tank List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.BulkOxygenTankEntity.class);

}