package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.ElevatorPermitVo;
import com.eespc.tracking.bo.ElevatorVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class ElevatorEntity {
	public static class ElevatorPermitEntity {

		public static ElevatorPermitVo findByPrimaryKey(int i)
				throws TrackingException {
			String s = "select * from ELEVATORPERMITINFO where ELEVATORPERMITID=?";
			ElevatorPermitVo elevatorpermitvo = null;
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				List list = sqlutil
						.execQueryUsingConstructor(
								s,
								ElevatorEntity.class$com$eespc$tracking$bo$ElevatorPermitVo == null ? (ElevatorEntity.class$com$eespc$tracking$bo$ElevatorPermitVo = ElevatorEntity
										._mthclass$("com.eespc.tracking.bo.ElevatorPermitVo"))
										: ElevatorEntity.class$com$eespc$tracking$bo$ElevatorPermitVo);
				if (list != null && list.size() > 0)
					elevatorpermitvo = (ElevatorPermitVo) list.get(0);
			} catch (Exception exception) {
				System.out.println("findByPrimaryKey():" + exception);
				TrackingException trackingexception = new TrackingException(
						(new StringBuilder()).append("findByPrimaryKey(")
								.append(i).append(")").toString());
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return elevatorpermitvo;
		}

		public static List getList(int i) throws TrackingException {
			Object obj = new ArrayList();
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer
					.append("select * from ELEVATORPERMITINFO where ELEVATORID =?");
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				obj = sqlutil
						.execQueryUsingConstructor(
								stringbuffer.toString(),
								ElevatorEntity.class$com$eespc$tracking$bo$ElevatorPermitVo == null ? (ElevatorEntity.class$com$eespc$tracking$bo$ElevatorPermitVo = ElevatorEntity
										._mthclass$("com.eespc.tracking.bo.ElevatorPermitVo"))
										: ElevatorEntity.class$com$eespc$tracking$bo$ElevatorPermitVo);
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						(new StringBuilder()).append("getList(").append(i)
								.append(")").toString());
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return ((List) (obj));
		}

		public static int add(ElevatorPermitVo elevatorpermitvo)
				throws TrackingException {
			int i = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into ELEVATORPERMITINFO ");
			stringbuffer.append("(ELEVATORPERMITID,ELEVATORID,PERMITNUMBER,");
			stringbuffer
					.append("LASTINSPECTIONDATE,NEXTINSPECTIONDATE,REPORTSUBMITTALDATE,REPORTSUBMITDATENOTE,NEXTINSPDATENOTE,");
			stringbuffer.append("FIRMINSPECTED)");
			stringbuffer.append(" values (null,");
			stringbuffer.append(elevatorpermitvo.getElevatorId()).append(",");
			stringbuffer.append("'").append(elevatorpermitvo.getPermitNumber())
					.append("',");
			if (UtilityObject.isNotEmpty(elevatorpermitvo.getLastInspDate()))
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(UtilityObject
								.convertToDate(elevatorpermitvo
										.getLastInspDate()), "yyyy-MM-dd"))
						.append("',");
			else
				stringbuffer.append("null,");

			if (UtilityObject.isNotEmpty(elevatorpermitvo.getNextInspDate()))
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(UtilityObject
								.convertToDate(elevatorpermitvo
										.getNextInspDate()), "yyyy-MM-dd"))
						.append("',");
			else
				stringbuffer.append("null,");

			if (UtilityObject
					.isNotEmpty(elevatorpermitvo.getReportSubmitDate()))
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(UtilityObject
								.convertToDate(elevatorpermitvo
										.getReportSubmitDate()), "yyyy-MM-dd"))
						.append("',");
			else
				stringbuffer.append("null,");

			stringbuffer.append("'")
					.append(elevatorpermitvo.getReportSubmitDateNote())
					.append("',");
			stringbuffer.append("'")
					.append(elevatorpermitvo.getNextInspDateNote())
					.append("',");
			stringbuffer.append("'")
					.append(elevatorpermitvo.getFirmInspected()).append("' ");
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				i = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Adding Elevator Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return i;
		}

		public static void delete(ElevatorPermitVo elevatorpermitvo)
				throws TrackingException {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("delete from ELEVATORPERMITINFO ");
			stringbuffer.append(" where ELEVATORPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			try {
				sqlutil.addInParam(new Integer(elevatorpermitvo.getPermitId()));
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While updating Elevator Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public static void update(ElevatorPermitVo elevatorpermitvo)
				throws TrackingException {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("update ELEVATORPERMITINFO ");
			stringbuffer.append("set ");
			stringbuffer.append("PERMITNUMBER=?, ");
			stringbuffer.append("LASTINSPECTIONDATE=?, ");
			stringbuffer.append("NEXTINSPECTIONDATE=?, ");
			stringbuffer.append("REPORTSUBMITTALDATE=?, ");
			stringbuffer.append("REPORTSUBMITDATENOTE=?, ");
			stringbuffer.append("NEXTINSPDATENOTE=?, ");
			stringbuffer.append("FIRMINSPECTED=? ");
			stringbuffer.append(" where ELEVATORPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			try {
				sqlutil.addInParam(elevatorpermitvo.getPermitNumber());

				if (UtilityObject
						.isNotEmpty(elevatorpermitvo.getLastInspDate())) {
					sqlutil.addInParam(UtilityObject.convertToString(
							UtilityObject.convertToDate(elevatorpermitvo
									.getLastInspDate()), "yyyy-MM-dd"));
				} else {
					sqlutil.addInParam(null);
				}

				if (UtilityObject
						.isNotEmpty(elevatorpermitvo.getNextInspDate())) {
					sqlutil.addInParam(UtilityObject.convertToString(
							UtilityObject.convertToDate(elevatorpermitvo
									.getNextInspDate()), "yyyy-MM-dd"));
				} else {
					sqlutil.addInParam(null);
				}

				if (UtilityObject.isNotEmpty(elevatorpermitvo
						.getReportSubmitDate())) {
					sqlutil.addInParam(UtilityObject.convertToString(
							UtilityObject.convertToDate(elevatorpermitvo
									.getReportSubmitDate()), "yyyy-MM-dd"));
				} else {
					sqlutil.addInParam(null);
				}
				sqlutil.addInParam(elevatorpermitvo.getReportSubmitDateNote());
				sqlutil.addInParam(elevatorpermitvo.getNextInspDateNote());
				sqlutil.addInParam(elevatorpermitvo.getFirmInspected());
				sqlutil.addInParam(new Integer(elevatorpermitvo.getPermitId()));
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While updating Elevator Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public ElevatorPermitEntity() {
		}
	}

	public ElevatorEntity() {
	}

	public static ElevatorVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, ELEVATORS elv where bld.buildingid = elv.buildingid and elv.ELEVATORID =?";
		ElevatorVo elevatorvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ElevatorVo.class);
			if (list != null && list.size() > 0)
				elevatorvo = (ElevatorVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return elevatorvo;
	}

	public static int add(ElevatorVo elevatorvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(elevatorvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into ELEVATORS (");
		stringbuffer
				.append("ELEVATORID,BUILDINGID,ECOMMENTS,YEARINSTALLED,MODIFIEDINPAST,DISCONNECTEDYEAR,ELETYPE,FACILITYDESIGNATEDID,HYDRAULICTANK) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(elevatorvo.getBuildingId()).append(",'");
		stringbuffer.append(elevatorvo.getComment()).append("',");
		stringbuffer.append("'").append(elevatorvo.getYearInstalled())
				.append("',");
		stringbuffer.append(elevatorvo.getModifiedInPast()).append(",");
		stringbuffer.append("'").append(elevatorvo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(elevatorvo.getEleType()).append("',");
		stringbuffer.append("'").append(elevatorvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append(elevatorvo.getHydraulicTankId());
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Exception in elavator entity:" + exception);

		}
		return i;
	}

	public static void update(ElevatorVo elevatorvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(elevatorvo).toString());
		boolean flag = false;
		if (elevatorvo.getHydraulicTankId() > 0)
			flag = true;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(" update ELEVATORS set ");
		stringbuffer.append(" FACILITYDESIGNATEDID=? ");
		stringbuffer.append(" ,YEARINSTALLED=? ");
		stringbuffer.append(" ,MODIFIEDINPAST=? ");
		stringbuffer.append(" ,DISCONNECTEDYEAR=? ");
		stringbuffer.append(" ,ELETYPE=? ");
		stringbuffer.append(" ,ECOMMENTS=? ");
		stringbuffer.append(" , HYDRAULICTANK=? ");
		stringbuffer.append(" where ELEVATORID=?");
		SqlUtil sqlutil = new SqlUtil();

		sqlutil.addInParam(elevatorvo.getFacilityDesignatedId());

		sqlutil.addInParam(elevatorvo.getYearInstalled());

		sqlutil.addInParam(new Integer(elevatorvo.getModifiedInPast()));

		sqlutil.addInParam(elevatorvo.getDisconnectedYear());

		sqlutil.addInParam(elevatorvo.getEleType());

		sqlutil.addInParam(elevatorvo.getComment());

		sqlutil.addInParam(String.valueOf(elevatorvo.getHydraulicTankId()));

		sqlutil.addInParam(new Integer(elevatorvo.getElevatorId()));

		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Elevator" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Elevator");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(ElevatorVo elevatorvo) throws TrackingException {
		String as[] = { "delete from elevatorpermitinfo where ELEVATORID=?",
				"delete from ELEVATORS where ELEVATORID=?" };
		for (int i = 0; i <= 1; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(elevatorvo.getElevatorId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating Elevator");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static ElevatorPermitVo findPermitByPrimaryKey(int i)
			throws TrackingException {
		return ElevatorPermitEntity.findByPrimaryKey(i);
	}

	public static int add(ElevatorPermitVo elevatorpermitvo)
			throws TrackingException {
		return ElevatorPermitEntity.add(elevatorpermitvo);
	}

	public static void update(ElevatorPermitVo elevatorpermitvo)
			throws TrackingException {
		ElevatorPermitEntity.update(elevatorpermitvo);
	}

	public static void delete(ElevatorPermitVo elevatorpermitvo)
			throws TrackingException {
		ElevatorPermitEntity.delete(elevatorpermitvo);
	}

	public static List getPermitList(int i) throws TrackingException {
		return ElevatorPermitEntity.getList(i);
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.ELEVATORID, stk.FACILITYDESIGNATEDID,stk.ELETYPE, bldg.BUILDINGNAME from elevators stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ElevatorListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Elevator List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	static Class _mthclass$(String s) throws Exception {
		return Class.forName(s);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.ElevatorEntity.class);
	static Class class$com$eespc$tracking$bo$ElevatorPermitVo;

}