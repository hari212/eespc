package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.BridgeTunnelPermitVo;
import com.eespc.tracking.bo.BridgeTunnelsVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class BridgeTunnelsEntity {
	public static class BridgeTunnelPermitEntity {

		public static BridgeTunnelPermitVo findByPrimaryKey(int i)
				throws TrackingException {
			String s = "select * from BRIDGETUNNELPERMITINFO where BRIDGETUNNELPERMITID=?";
			BridgeTunnelPermitVo bridgetunnelpermitvo = null;
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				List list = sqlutil.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.BridgeTunnelPermitVo.class);
				if (list != null && list.size() > 0)
					bridgetunnelpermitvo = (BridgeTunnelPermitVo) list.get(0);
			} catch (Exception exception) {
				System.out.println("BridgeTunnelPermitVo findByPrimaryKey:"
						+ exception);
				TrackingException trackingexception = new TrackingException(
						(new StringBuilder()).append("findByPrimaryKey(")
								.append(i).append(")").toString());
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return bridgetunnelpermitvo;
		}

		public static List getList(int i) throws TrackingException {
			Object obj = new ArrayList();
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer
					.append("select * from BRIDGETUNNELPERMITINFO where BRIDGETUNNELID =?");
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				obj = sqlutil.execQueryUsingConstructor(
						stringbuffer.toString(),
						com.eespc.tracking.bo.BridgeTunnelPermitVo.class);
			} catch (Exception exception) {
				System.out.println("BridgeTunnelPermitVo getList:" + exception);
				TrackingException trackingexception = new TrackingException(
						(new StringBuilder()).append("getList(").append(i)
								.append(")").toString());
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return ((List) (obj));
		}

		public static int adddob(BridgeTunnelPermitVo bridgetunnelpermitvo)
				throws TrackingException {
			int i = -99;
			StringBuffer stringbuffer = new StringBuffer();
			Object obj = null;
			stringbuffer.append("insert into BRIDGETUNNELPERMITINFO ");
			stringbuffer
					.append("(BRIDGETUNNELPERMITID,BRIDGETUNNELID,PERMITNUMBER,");
			stringbuffer.append("ISSUEDATE,EXPIRATIONDATE,DEPID,");
			stringbuffer.append("BYWHO, YEAR)");
			stringbuffer.append(" values (null,");
			stringbuffer.append(bridgetunnelpermitvo.getBridgeTunnelId())
					.append(",");
			stringbuffer.append("'")
					.append(bridgetunnelpermitvo.getPermitNumber())
					.append("',");
			if (UtilityObject.isNotEmpty(bridgetunnelpermitvo.getIssueDate())) {
				java.util.Date date = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getIssueDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}

			if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
					.getExpirationDate())) {
				java.util.Date date1 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getExpirationDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date1,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}

			stringbuffer.append("'").append(bridgetunnelpermitvo.getDepId())
					.append("',");
			stringbuffer.append("'").append(bridgetunnelpermitvo.getByWho())
					.append("',");
			stringbuffer.append("'").append(bridgetunnelpermitvo.getYear())
					.append("'");
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				i = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While Adding Bridge Tunnel Permit Info:"
						+ exception);
				exception.printStackTrace();
				TrackingException trackingexception = new TrackingException(
						"While Adding Bridge Tunnel Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return i;
		}

		public static int add(BridgeTunnelPermitVo bridgetunnelpermitvo)
				throws TrackingException {
			int i = -99;
			StringBuffer stringbuffer = new StringBuffer();
			Object obj = null;
			stringbuffer.append("insert into BRIDGETUNNELPERMITINFO ");
			stringbuffer
					.append("(BRIDGETUNNELPERMITID,BRIDGETUNNELID,PERMITNUMBER,FILENO,");
			stringbuffer.append("ISSUEDATE,EXPIRATIONDATE,DEPID,");
			stringbuffer.append("LASTINSPDATE,NEXTINSPDATE, FILLINGDATE,");
			stringbuffer
					.append("BYWHO,INSISSUEDATE, INSEXPDATE, YEAR, DOTEXPDATENOTE, DOTNEXTINSPDATENOTE, DOTINSEXPDATENOTE)");
			stringbuffer.append(" values (null,");
			stringbuffer.append(bridgetunnelpermitvo.getBridgeTunnelId())
					.append(",");
			stringbuffer.append("'")
					.append(bridgetunnelpermitvo.getPermitNumber())
					.append("',");
			stringbuffer.append("'").append(bridgetunnelpermitvo.getFileNo())
					.append("',");
			if (UtilityObject.isNotEmpty(bridgetunnelpermitvo.getIssueDate())) {
				java.util.Date date = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getIssueDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
					.getExpirationDate())) {
				java.util.Date date1 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getExpirationDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date1,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			stringbuffer.append("'").append(bridgetunnelpermitvo.getDepId())
					.append("',");
			if (UtilityObject
					.isNotEmpty(bridgetunnelpermitvo.getLastInspDate())) {
				java.util.Date date2 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getLastInspDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date2,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			if (UtilityObject
					.isNotEmpty(bridgetunnelpermitvo.getNextInspDate())) {
				java.util.Date date3 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getNextInspDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date3,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			if (UtilityObject.isNotEmpty(bridgetunnelpermitvo.getFillingDate())) {
				java.util.Date date4 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getFillingDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date4,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			stringbuffer.append("'").append(bridgetunnelpermitvo.getByWho())
					.append("',");
			if (UtilityObject
					.isNotEmpty(bridgetunnelpermitvo.getInsIssueDate())) {
				java.util.Date date5 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getInsIssueDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date5,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			if (UtilityObject.isNotEmpty(bridgetunnelpermitvo.getInsExpDate())) {
				java.util.Date date6 = UtilityObject
						.convertToDate(bridgetunnelpermitvo.getInsExpDate());
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(date6,
								"yyyy-MM-dd")).append("',");
			} else {
				stringbuffer.append("null,");
			}
			stringbuffer.append("'").append(bridgetunnelpermitvo.getYear())
					.append("',");
			stringbuffer.append("'")
					.append(bridgetunnelpermitvo.getDotExpDateNote())
					.append("',");
			stringbuffer.append("'")
					.append(bridgetunnelpermitvo.getDotNextInspDateNote())
					.append("',");
			stringbuffer.append("'")
					.append(bridgetunnelpermitvo.getDotInsExpDateNote())
					.append("'");
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				i = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While Adding Bridge Tunnel Permit Info:"
						+ exception);
				exception.printStackTrace();
				TrackingException trackingexception = new TrackingException(
						"While Adding Bridge Tunnel Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return i;
		}

		public static void delete(BridgeTunnelPermitVo bridgetunnelpermitvo)
				throws TrackingException {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("delete from BRIDGETUNNELPERMITINFO ");
			stringbuffer.append(" where BRIDGETUNNELPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			try {
				sqlutil.addInParam(new Integer(bridgetunnelpermitvo.getId()));
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While updating Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public static void update(BridgeTunnelPermitVo bridgetunnelpermitvo)
				throws TrackingException {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("update BRIDGETUNNELPERMITINFO ");
			stringbuffer.append("set ");
			stringbuffer.append("PERMITNUMBER  =?, ");
			stringbuffer.append("FILENO  =?, ");
			stringbuffer.append("ISSUEDATE     =?, ");
			stringbuffer.append("EXPIRATIONDATE =?, ");
			stringbuffer.append("DEPID          =?, ");
			stringbuffer.append("LASTINSPDATE   =?, ");
			stringbuffer.append("NEXTINSPDATE   =?, ");
			stringbuffer.append("FILLINGDATE    =?, ");
			stringbuffer.append("BYWHO          =?, ");
			stringbuffer.append("INSISSUEDATE   =?, ");
			stringbuffer.append("INSEXPDATE     =?, ");
			stringbuffer.append("YEAR           =?, ");
			stringbuffer.append("DOTEXPDATENOTE =?, ");
			stringbuffer.append("DOTNEXTINSPDATENOTE =?, ");
			stringbuffer.append("DOTINSEXPDATENOTE =? ");
			stringbuffer.append(" where BRIDGETUNNELPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			try {
				sqlutil.addInParam(bridgetunnelpermitvo.getPermitNumber());
				sqlutil.addInParam(bridgetunnelpermitvo.getFileNo());

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getIssueDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getIssueDate(), false));
				else
					sqlutil.addInParam(null);

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getExpirationDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getExpirationDate(), false));
				else
					sqlutil.addInParam(null);

				sqlutil.addInParam(new Integer(bridgetunnelpermitvo.getDepId()));

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getLastInspDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getLastInspDate(), false));
				else
					sqlutil.addInParam(null);

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getNextInspDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getNextInspDate(), false));
				else
					sqlutil.addInParam(null);

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getFillingDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getFillingDate(), false));
				else
					sqlutil.addInParam(null);

				sqlutil.addInParam(bridgetunnelpermitvo.getByWho());

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getInsIssueDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getInsIssueDate(), false));
				else
					sqlutil.addInParam(null);

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getInsExpDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getInsExpDate(), false));
				else
					sqlutil.addInParam(null);

				sqlutil.addInParam(bridgetunnelpermitvo.getYear());
				sqlutil.addInParam(bridgetunnelpermitvo.getDotExpDateNote());
				sqlutil.addInParam(bridgetunnelpermitvo
						.getDotNextInspDateNote());
				sqlutil.addInParam(bridgetunnelpermitvo.getDotInsExpDateNote());
				sqlutil.addInParam(new Integer(bridgetunnelpermitvo.getId()));
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While updating Permit Info:" + exception);
				TrackingException trackingexception = new TrackingException(
						"While updating Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public static void updatedob(BridgeTunnelPermitVo bridgetunnelpermitvo)
				throws TrackingException {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("update BRIDGETUNNELPERMITINFO ");
			stringbuffer.append("set ");
			stringbuffer.append("PERMITNUMBER  =?, ");
			stringbuffer.append("ISSUEDATE     =?, ");
			stringbuffer.append("EXPIRATIONDATE =?, ");
			stringbuffer.append("DEPID          =?, ");
			stringbuffer.append("BYWHO          =?, ");
			stringbuffer.append("YEAR           =? ");
			stringbuffer.append(" where BRIDGETUNNELPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			try {
				sqlutil.addInParam(bridgetunnelpermitvo.getPermitNumber());

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getIssueDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getIssueDate(), false));
				else
					sqlutil.addInParam(null);

				if (UtilityObject.isNotEmpty(bridgetunnelpermitvo
						.getExpirationDate()))
					sqlutil.addInParam(UtilityObject.getDateStringForDB(
							bridgetunnelpermitvo.getExpirationDate(), false));
				else
					sqlutil.addInParam(null);

				sqlutil.addInParam(new Integer(bridgetunnelpermitvo.getDepId()));
				sqlutil.addInParam(bridgetunnelpermitvo.getByWho());
				sqlutil.addInParam(bridgetunnelpermitvo.getYear());
				sqlutil.addInParam(new Integer(bridgetunnelpermitvo.getId()));
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While updating Permit Info:" + exception);
				TrackingException trackingexception = new TrackingException(
						"While updating Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public BridgeTunnelPermitEntity() {
		}
	}

	public BridgeTunnelsEntity() {
	}

	public static BridgeTunnelsVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, BRIDGETUNNELS brdg where bld.buildingid = brdg.buildingid and brdg.BRIDGETUNNELID =?";
		BridgeTunnelsVo bridgetunnelsvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.BridgeTunnelsVo.class);
			if (list != null && list.size() > 0)
				bridgetunnelsvo = (BridgeTunnelsVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return bridgetunnelsvo;
	}

	public static int add(BridgeTunnelsVo bridgetunnelsvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(bridgetunnelsvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into BRIDGETUNNELS (");
		stringbuffer.append("BRIDGETUNNELID, BUILDINGID, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID,ACTIONTAKEN,ISBRIDGE,YEARINSTALLED,MODIFIEDINPAST,DISCONNECTEDYEAR,BCOMMENTS,ISSUEDATE,PERMITNUMBER,DOBSIGNOFF) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(bridgetunnelsvo.getBuildingId()).append(",");
		stringbuffer.append("'")
				.append(bridgetunnelsvo.getFacilityDesignatedId()).append("',");
		stringbuffer.append("'").append(bridgetunnelsvo.getActionTaken())
				.append("',");
		if (bridgetunnelsvo.isBridge())
			stringbuffer.append("'").append("Y").append("',");
		else
			stringbuffer.append("'").append("N").append("',");
		stringbuffer.append("'").append(bridgetunnelsvo.getYearInstalled())
				.append("',");
		stringbuffer.append(bridgetunnelsvo.getModifiedInPast()).append(",");
		stringbuffer.append("'").append(bridgetunnelsvo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(bridgetunnelsvo.getBcomments())
				.append("',");

		if (UtilityObject.isNotEmpty(bridgetunnelsvo.getIssueDate())) {
			java.util.Date date = UtilityObject.convertToDate(bridgetunnelsvo
					.getIssueDate());
			stringbuffer.append("'")
					.append(UtilityObject.convertToString(date, "yyyy-MM-dd"))
					.append("',");
		} else {
			stringbuffer.append("null,");
		}
		stringbuffer.append("'").append(bridgetunnelsvo.getPermitNumber())
				.append("',");
		stringbuffer.append("'").append(bridgetunnelsvo.getDobsignoff())
				.append("'");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Bridge/Tunnel.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(BridgeTunnelsVo bridgetunnelsvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(bridgetunnelsvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update BRIDGETUNNELS set ");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("ACTIONTAKEN=? ");
		stringbuffer.append(" ,YEARINSTALLED=? ");
		stringbuffer.append(" ,MODIFIEDINPAST=? ");
		stringbuffer
				.append(" ,DISCONNECTEDYEAR=? ,BCOMMENTS=?,ISSUEDATE=?,PERMITNUMBER=?,");
		stringbuffer.append("DOBSIGNOFF=? ");
		stringbuffer.append("where BRIDGETUNNELID=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(bridgetunnelsvo.getFacilityDesignatedId());
		sqlutil.addInParam(bridgetunnelsvo.getActionTaken());
		sqlutil.addInParam(bridgetunnelsvo.getYearInstalled());
		sqlutil.addInParam(String.valueOf(bridgetunnelsvo.getModifiedInPast()));
		sqlutil.addInParam(bridgetunnelsvo.getDisconnectedYear());
		sqlutil.addInParam(bridgetunnelsvo.getBcomments());
		if (UtilityObject.isNotEmpty(bridgetunnelsvo.getIssueDate())) {
			java.util.Date date = UtilityObject.convertToDate(bridgetunnelsvo
					.getIssueDate());
			sqlutil.addInParam(UtilityObject
					.convertToString(date, "yyyy-MM-dd"));
		} else {
			sqlutil.addInParam(null);
		}

		sqlutil.addInParam(bridgetunnelsvo.getPermitNumber());
		sqlutil.addInParam(bridgetunnelsvo.getDobsignoff());
		sqlutil.addInParam(new Integer(bridgetunnelsvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Updating Bridge/Tunnel.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(BridgeTunnelsVo bridgetunnelsvo)
			throws TrackingException {
		String as[] = {
				"delete from BRIDGETUNNELPERMITINFO where BRIDGETUNNELID=?",
				"delete from BRIDGETUNNELS where BRIDGETUNNELID=?" };
		for (int i = 0; i <= 1; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(bridgetunnelsvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating Bridge/Tunnel.");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static BridgeTunnelPermitVo findPermit(int i)
			throws TrackingException {
		return BridgeTunnelPermitEntity.findByPrimaryKey(i);
	}

	public static List getPermitList(int i) throws TrackingException {
		return BridgeTunnelPermitEntity.getList(i);
	}

	public static int addPermit(BridgeTunnelPermitVo bridgetunnelpermitvo)
			throws TrackingException {
		return BridgeTunnelPermitEntity.add(bridgetunnelpermitvo);
	}

	public static int addPermitdob(BridgeTunnelPermitVo bridgetunnelpermitvo)
			throws TrackingException {
		return BridgeTunnelPermitEntity.adddob(bridgetunnelpermitvo);
	}

	public static void updatePermit(BridgeTunnelPermitVo bridgetunnelpermitvo)
			throws TrackingException {
		BridgeTunnelPermitEntity.update(bridgetunnelpermitvo);
	}

	public static void updatePermitDob(BridgeTunnelPermitVo bridgetunnelpermitvo)
			throws TrackingException {
		BridgeTunnelPermitEntity.updatedob(bridgetunnelpermitvo);
	}

	public static void deletePermit(BridgeTunnelPermitVo bridgetunnelpermitvo)
			throws TrackingException {
		BridgeTunnelPermitEntity.delete(bridgetunnelpermitvo);
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.BRIDGETUNNELID, stk.FACILITYDESIGNATEDID,stk.ISBRIDGE,stk.Yearinstalled, bldg.BUILDINGNAME from bridgetunnels stk, BUILDING bldg where stk.buildingid=bldg.buildingid and  bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.BridgeListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Bridge List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.BridgeTunnelsEntity.class);

}