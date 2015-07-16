package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FireAlarmVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class FireAlarmEntity {

	public FireAlarmEntity() {
	}

	public static FireAlarmVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, firealarm chr where bld.buildingid = chr.buildingid and chr.firealarmid =?";
		FireAlarmVo firealarmvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.FireAlarmVo.class);
			if (list != null && list.size() > 0)
				firealarmvo = (FireAlarmVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("FireAlarmVo findByPrimaryKey():" + exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return firealarmvo;
	}

	public static int add(FireAlarmVo firealarmvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(firealarmvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into Firealarm ");
		stringbuffer
				.append("(firealarmid,buildingid,facilitydesignatedid,yearinstalled,statusOfSource,disconnectedYear,typeofFAInstalled,dobApproval,dobFiling,approvalDate,dobSignoff,fdApproval,fdApprovalDate,agencyApproval,agencyName,agencyApprovalDate,agencyApprovalNo,fcomments)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("").append(firealarmvo.getBuildingId()).append(",");

		stringbuffer.append("'").append(firealarmvo.getFacilitydesignatedid())
				.append("',");
		stringbuffer.append("'").append(firealarmvo.getYearinstalled())
				.append("',");
		stringbuffer.append("").append(firealarmvo.getStatusOfSource())
				.append(",");
		stringbuffer.append("'").append(firealarmvo.getDisconnectedYear())
				.append("',");

		stringbuffer.append("").append(firealarmvo.getTypeofFAInstalled())
				.append(",");
		stringbuffer.append("'").append(firealarmvo.getDobApproval())
				.append("',");
		stringbuffer.append("'").append(firealarmvo.getDobFiling())
				.append("',");

		if (UtilityObject.isNotEmpty(firealarmvo.getApprovalDate()))
			stringbuffer.append("'").append(firealarmvo.getApprovalDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(firealarmvo.getDobSignoff())
				.append("',");

		stringbuffer.append("'").append(firealarmvo.getFdApproval())
				.append("',");

		if (UtilityObject.isNotEmpty(firealarmvo.getFdApprovalDate()))
			stringbuffer.append("'").append(firealarmvo.getFdApprovalDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(firealarmvo.getAgencyApproval())
				.append("',");
		stringbuffer.append("'").append(firealarmvo.getAgencyName())
				.append("',");

		if (UtilityObject.isNotEmpty(firealarmvo.getAgencyApprovalDate()))
			stringbuffer.append("'")
					.append(firealarmvo.getAgencyApprovalDate()).append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(firealarmvo.getAgencyApprovalNo())
				.append("',");
		stringbuffer.append("'").append(firealarmvo.getComments()).append("')");

		System.out.println(stringbuffer.toString());
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding FireAlarm:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding FireAlarm.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(FireAlarmVo firealarmvo) throws TrackingException {

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from firealarm where firealarmid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(firealarmvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Deleting firealarm:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Deleting firealarm:");
			trackingexception.initCause(exception);
			throw trackingexception;
		}

	}

	public static void update(FireAlarmVo firealarmvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(firealarmvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update firealarm set ");
		stringbuffer
				.append("BUILDINGID=?,facilitydesignatedid=?,yearinstalled=?,statusOfSource=?,disconnectedYear=?,typeofFAInstalled=?,dobApproval=?,dobFiling=?,approvalDate=?,dobSignoff=?,fdApproval=?,fdApprovalDate=?,agencyApproval=?,AgencyName=?,agencyApprovalDate=?,agencyApprovalNo=?,fcomments=? ");
		stringbuffer.append("where firealarmid=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(firealarmvo.getBuildingId());
		sqlutil.addInParam(firealarmvo.getFacilitydesignatedid());
		sqlutil.addInParam(firealarmvo.getYearinstalled());
		sqlutil.addInParam(firealarmvo.getStatusOfSource());
		sqlutil.addInParam(firealarmvo.getDisconnectedYear());

		sqlutil.addInParam(firealarmvo.getTypeofFAInstalled());
		sqlutil.addInParam(firealarmvo.getDobApproval());
		sqlutil.addInParam(firealarmvo.getDobFiling());
		if (UtilityObject.isNotEmpty(firealarmvo.getApprovalDate()))
			sqlutil.addInParam(firealarmvo.getApprovalDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(firealarmvo.getDobSignoff());
		sqlutil.addInParam(firealarmvo.getFdApproval());

		if (UtilityObject.isNotEmpty(firealarmvo.getFdApprovalDate()))
			sqlutil.addInParam(firealarmvo.getFdApprovalDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(firealarmvo.getAgencyApproval());
		sqlutil.addInParam(firealarmvo.getAgencyName());

		if (UtilityObject.isNotEmpty(firealarmvo.getAgencyApprovalDate()))
			sqlutil.addInParam(firealarmvo.getAgencyApprovalDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(firealarmvo.getAgencyApprovalNo());
		sqlutil.addInParam(firealarmvo.getComments());

		try {
			sqlutil.addInParam(firealarmvo.getId());
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating firealarm:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating firealarm.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String qu = "select chr.FIREALARMID, chr.FACILITYDESIGNATEDID,bldg.BUILDINGNAME from FIREALARM chr, BUILDING bldg where chr.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(qu,
					com.eespc.tracking.bo.FireAlarmListVo.class);
		} catch (Exception exception) {
			System.out.println("getFacilityStackList:" + exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Printing FIREALARM List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.FireAlarmEntity.class);

}