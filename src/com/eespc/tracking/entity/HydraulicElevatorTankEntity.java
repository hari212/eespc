package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.HydraulicElevatorAndOtherTankVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class HydraulicElevatorTankEntity {

	public HydraulicElevatorTankEntity() {
	}

	public static HydraulicElevatorAndOtherTankVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, hydraulicelevatorothertank tank where bld.buildingid = tank.buildingid and tank.othertankid =?";
		HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil
					.execQueryUsingConstructor(
							s,
							com.eespc.tracking.bo.HydraulicElevatorAndOtherTankVo.class);
			if (list != null && list.size() > 0)
				hydraulicelevatorandothertankvo = (HydraulicElevatorAndOtherTankVo) list
						.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return hydraulicelevatorandothertankvo;
	}

	public static int add(
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(hydraulicelevatorandothertankvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("insert into hydraulicelevatorothertank values (null,");
		stringbuffer.append(hydraulicelevatorandothertankvo.getBldgId())
				.append(",'");
		stringbuffer.append(
				hydraulicelevatorandothertankvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append(hydraulicelevatorandothertankvo.getCapacity())
				.append(",");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getYearInstalled())
				.append("',");
		stringbuffer
				.append(hydraulicelevatorandothertankvo.getModifiedInPast())
				.append(",");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getDisconnectedYear())
				.append("',");
		if (hydraulicelevatorandothertankvo.isSecondaryContainment())
			stringbuffer.append("'Y'").append(",'");
		else
			stringbuffer.append("'N'").append(",'");
		if (UtilityObject
				.isNotEmpty(hydraulicelevatorandothertankvo.getUsage()))
			stringbuffer.append(hydraulicelevatorandothertankvo.getUsage())
					.append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getDecapproval())
				.append("',");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getMonthlyinspection())
				.append("',");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getSpillkit())
				.append("',");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getPbsno())
				.append("',");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getHcomments())
				.append("',");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getDobsignoff())
				.append("',");
		stringbuffer.append("'")
				.append(hydraulicelevatorandothertankvo.getDobnumber())
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding HTank" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding HTank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo)
			throws TrackingException {
		String as[] = { "delete from hydraulicelevatorothertank where OTHERTANKID=?" };
		for (int i = 0; i < 1; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(hydraulicelevatorandothertankvo
					.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating HydraulicElevatorAndOtherTankVo");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static void update(
			HydraulicElevatorAndOtherTankVo hydraulicelevatorandothertankvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(hydraulicelevatorandothertankvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update hydraulicelevatorothertank set ");
		stringbuffer
				.append("FACILITYDesignatedID=?, capacity=?,YEARINSTALLED=? ,MODIFIEDINPAST=?,DISCONNECTEDYEAR=? , ");
		stringbuffer.append("tankusage=?, isSecondaryContainment=?, ");
		stringbuffer.append("DECAPPROVAL=?,");
		stringbuffer.append("MONTHLYINSPECTION=?,");
		stringbuffer.append("SPILLKIT=?,");
		stringbuffer.append("PBSNO=?,");
		stringbuffer.append("HCOMMENTS=?,DOBSIGNOFF=?,DOBNUMBER=? ");
		stringbuffer.append("where OTHERTANKID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(hydraulicelevatorandothertankvo
				.getFacilityDesignatedId());
		sqlutil.addInParam(new Integer(hydraulicelevatorandothertankvo
				.getCapacity()));
		sqlutil.addInParam(hydraulicelevatorandothertankvo.getYearInstalled());
		sqlutil.addInParam(String.valueOf(hydraulicelevatorandothertankvo
				.getModifiedInPast()));
		sqlutil.addInParam(hydraulicelevatorandothertankvo
				.getDisconnectedYear());
		sqlutil.addInParam(new Integer(hydraulicelevatorandothertankvo
				.getUsage()));
		if (hydraulicelevatorandothertankvo.isSecondaryContainment())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");

		sqlutil.addInParam(hydraulicelevatorandothertankvo.getDecapproval());
		sqlutil.addInParam(hydraulicelevatorandothertankvo
				.getMonthlyinspection());
		sqlutil.addInParam(hydraulicelevatorandothertankvo.getSpillkit());
		sqlutil.addInParam(hydraulicelevatorandothertankvo.getPbsno());
		sqlutil.addInParam(hydraulicelevatorandothertankvo.getHcomments());
		sqlutil.addInParam(hydraulicelevatorandothertankvo.getDobsignoff());
		sqlutil.addInParam(hydraulicelevatorandothertankvo.getDobnumber());
		sqlutil.addInParam(new Integer(hydraulicelevatorandothertankvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating HydraulicElevatorAndOtherTankVo"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating HydraulicElevatorAndOtherTankVo");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.OTHERTANKID, stk.FACILITYDESIGNATEDID, stk.CAPACITY, bldg.BUILDINGNAME from hydraulicelevatorothertank stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();

			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.HTankListVo.class);

		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting H-Storage Tank List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.HydraulicElevatorTankEntity.class);

}