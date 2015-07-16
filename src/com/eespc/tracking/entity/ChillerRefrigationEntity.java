package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.ChillerRefrigationVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class ChillerRefrigationEntity {

	public ChillerRefrigationEntity() {
	}

	public static ChillerRefrigationVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, ChillerRefrigation chr where bld.buildingid = chr.buildingid and chr.chillerrefrigationid =?";
		ChillerRefrigationVo chillerrefrigationvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ChillerRefrigationVo.class);
			if (list != null && list.size() > 0)
				chillerrefrigationvo = (ChillerRefrigationVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("ChillerRefrigationVo findByPrimaryKey():"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return chillerrefrigationvo;
	}

	public static int add(ChillerRefrigationVo chillerrefrigationvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(chillerrefrigationvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into CHILLERREFRIGATION ");
		stringbuffer
				.append("(CHILLERREFRIGATIONID,BUILDINGID,FACILITYDESIGNATEDID,YEARINSTALLED,MAKE,MODEL,STATUS,DISCONNECTEDYEAR,LOCATION,CAPACITYTONS,CAPACITYBTU,EQUIPMENTUSEPERMIT,DOBPERMIT,DOBISSUEDATE,DOBSIGNOFF,EPASTATUS,EPASUBMITTALDATE,EPAAPPROVALDATE,FDAPPROVAL,REFRIGERATIONRECOVERY,EPAMAINTAINED,CRCOMMENTS,SERIALNUM,AREASERVED,DOBFILING,FUELFIRING,FUELUSED,DEPSTATUS,DEPPERMIT,DEPEXPIRATIONDATE)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("'").append(chillerrefrigationvo.getBuildingId())
				.append("',");
		stringbuffer.append("'")
				.append(chillerrefrigationvo.getFacilitydesignatedId())
				.append("',");
		stringbuffer.append("'")
				.append(chillerrefrigationvo.getYearInstalled()).append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getMake())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getModel())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getStatus())
				.append("',");
		stringbuffer.append("'")
				.append(chillerrefrigationvo.getDisconnectedyear())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getLocation())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getCapacityTons())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getCapacityBtu())
				.append("',");
		stringbuffer.append("'")
				.append(chillerrefrigationvo.getEquipmentusepermit())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getDobpermit())
				.append("',");

		if (UtilityObject.isNotEmpty(chillerrefrigationvo.getDobissuedate()))
			stringbuffer.append("'")
					.append(chillerrefrigationvo.getDobissuedate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(chillerrefrigationvo.getDobsignoff())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getEpaStatus())
				.append("',");
		if (UtilityObject
				.isNotEmpty(chillerrefrigationvo.getEpaSubmittalDate()))
			stringbuffer.append("'")
					.append(chillerrefrigationvo.getEpaSubmittalDate())
					.append("',");
		else
			stringbuffer.append("null,");
		// stringbuffer.append("'").append(chillerrefrigationvo.getSubmittalDateNote()).append("',");
		if (UtilityObject.isNotEmpty(chillerrefrigationvo.getEpaApprovalDate()))
			stringbuffer.append("'")
					.append(chillerrefrigationvo.getEpaApprovalDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(chillerrefrigationvo.getFdaApproval())
				.append("',");
		/*
		 * if(UtilityObject.isNotEmpty(chillerrefrigationvo.getFdaApprovalDate())
		 * )
		 * stringbuffer.append("'").append(chillerrefrigationvo.getFdaApprovalDate
		 * ()).append("',");
		 * 
		 * else stringbuffer.append("null,");
		 */

		stringbuffer.append("'")
				.append(chillerrefrigationvo.getRefrigerationrecovery())
				.append("',");
		stringbuffer.append("'")
				.append(chillerrefrigationvo.getEpamaintained()).append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getComments())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getSerialnum())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getAreaServed())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getDobfiling())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getFuelFiring())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getFuelUsed())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getDepStatus())
				.append("',");
		stringbuffer.append("'").append(chillerrefrigationvo.getDeppermit())
				.append("',");
		if (UtilityObject.isNotEmpty(chillerrefrigationvo
				.getDepexpirationdate()))
			stringbuffer.append("'")
					.append(chillerrefrigationvo.getDepexpirationdate())
					.append("')");

		else
			stringbuffer.append("null)");

		System.out.println(stringbuffer.toString());
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding ChillerRefrigation:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding ChillerRefrigation.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(ChillerRefrigationVo chillerrefrigationvo)
			throws TrackingException {

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("delete from CHILLERREFRIGATION where chillerrefrigationid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(chillerrefrigationvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out
					.println("While Deleting ChillerRefrigation:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Deleting ChillerRefrigation:");
			trackingexception.initCause(exception);
			throw trackingexception;
		}

	}

	public static void update(ChillerRefrigationVo chillerrefrigationvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(chillerrefrigationvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update chillerrefrigation set ");
		stringbuffer.append("BUILDINGID=?,");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("YEARINSTALLED=?,");
		stringbuffer.append("MAKE=?,");
		stringbuffer.append("MODEL=?,");
		stringbuffer.append("STATUS=?,");
		stringbuffer.append("DISCONNECTEDYEAR=?,");
		stringbuffer.append("LOCATION=?,");
		stringbuffer.append("CAPACITYTONS=?,");
		stringbuffer.append("CAPACITYBTU=?,");
		stringbuffer.append("EQUIPMENTUSEPERMIT=?,");
		stringbuffer.append("DOBPERMIT=?,");
		stringbuffer.append("DOBISSUEDATE=?,");
		stringbuffer.append("DOBSIGNOFF=?,");
		stringbuffer.append("EPASTATUS=?,");
		stringbuffer.append("EPASUBMITTALDATE=?,");
		// stringbuffer.append("SUBMITTALDATENOTE=?,");
		stringbuffer.append("EPAAPPROVALDATE=?,");
		stringbuffer.append("FDAPPROVAL=?,");
		// stringbuffer.append("FDAPPROVALDATE=?,");
		stringbuffer.append("REFRIGERATIONRECOVERY=?,");
		stringbuffer.append("EPAMAINTAINED=?,");
		stringbuffer.append("CRCOMMENTS=?, ");
		stringbuffer.append("SERIALNUM=?, ");
		stringbuffer.append("AREASERVED=?, ");
		stringbuffer.append("DOBFILING=?, ");
		stringbuffer.append("FUELFIRING=?, ");
		stringbuffer.append("FUELUSED=?, ");
		stringbuffer.append("DEPSTATUS=?, ");
		stringbuffer.append("DEPPERMIT=?, ");
		stringbuffer.append("DEPEXPIRATIONDATE=? ");
		stringbuffer.append("where chillerrefrigationid=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(chillerrefrigationvo.getBuildingId());
		sqlutil.addInParam(chillerrefrigationvo.getFacilitydesignatedId());
		sqlutil.addInParam(chillerrefrigationvo.getYearInstalled());
		sqlutil.addInParam(chillerrefrigationvo.getMake());
		sqlutil.addInParam(chillerrefrigationvo.getModel());
		sqlutil.addInParam(chillerrefrigationvo.getStatus());
		sqlutil.addInParam(chillerrefrigationvo.getDisconnectedyear());
		sqlutil.addInParam(chillerrefrigationvo.getLocation());
		sqlutil.addInParam(chillerrefrigationvo.getCapacityTons());
		sqlutil.addInParam(chillerrefrigationvo.getCapacityBtu());
		sqlutil.addInParam(chillerrefrigationvo.getEquipmentusepermit());
		sqlutil.addInParam(chillerrefrigationvo.getDobpermit());

		if (UtilityObject.isNotEmpty(chillerrefrigationvo.getDobissuedate()))
			sqlutil.addInParam(chillerrefrigationvo.getDobissuedate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(chillerrefrigationvo.getDobsignoff());
		sqlutil.addInParam(chillerrefrigationvo.getEpaStatus());

		if (UtilityObject
				.isNotEmpty(chillerrefrigationvo.getEpaSubmittalDate()))
			sqlutil.addInParam(chillerrefrigationvo.getEpaSubmittalDate());
		else
			sqlutil.addInParam(null);

		if (UtilityObject.isNotEmpty(chillerrefrigationvo.getEpaApprovalDate()))
			sqlutil.addInParam(chillerrefrigationvo.getEpaApprovalDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(chillerrefrigationvo.getFdaApproval());
		sqlutil.addInParam(chillerrefrigationvo.getRefrigerationrecovery());
		sqlutil.addInParam(chillerrefrigationvo.getEpamaintained());
		sqlutil.addInParam(chillerrefrigationvo.getComments());
		sqlutil.addInParam(chillerrefrigationvo.getSerialnum());
		sqlutil.addInParam(chillerrefrigationvo.getAreaServed());
		sqlutil.addInParam(chillerrefrigationvo.getDobfiling());
		sqlutil.addInParam(chillerrefrigationvo.getFuelFiring());
		sqlutil.addInParam(chillerrefrigationvo.getFuelUsed());
		sqlutil.addInParam(chillerrefrigationvo.getDepStatus());
		sqlutil.addInParam(chillerrefrigationvo.getDeppermit());

		if (UtilityObject.isNotEmpty(chillerrefrigationvo
				.getDepexpirationdate()))
			sqlutil.addInParam(chillerrefrigationvo.getDepexpirationdate());
		else
			sqlutil.addInParam(null);

		try {
			sqlutil.addInParam(chillerrefrigationvo.getId());
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Miscellaneous:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating ChillerRefrigation.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String qu = "select chr.CHILLERREFRIGATIONID, chr.FACILITYDESIGNATEDID,bldg.BUILDINGNAME from CHILLERREFRIGATION chr, BUILDING bldg where chr.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(qu,
					com.eespc.tracking.bo.ChillerRefrigationListVo.class);
		} catch (Exception exception) {
			System.out.println("getFacilityStackList:" + exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Printing Chillerrefrigation List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.ChillerRefrigationEntity.class);

}