package com.eespc.tracking.entity;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.OtherEvalutionVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;

public class OtherEvalutionEntity {

	public OtherEvalutionEntity() {
	}

	public static OtherEvalutionVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from facility fc, OTHEREVALUTION oe where fc.facilityid = oe.facilityid and oe.OTHEREVALUTIONID =?";
		OtherEvalutionVo otherevalutionvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.OtherEvalutionVo.class);
			if (list != null && list.size() > 0)
				otherevalutionvo = (OtherEvalutionVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("OtherEvalutionVo findByPrimaryKey():"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return otherevalutionvo;
	}

	public static OtherEvalutionVo facilityfindByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from facility fc, OTHEREVALUTION oe where fc.facilityid = oe.facilityid and fc.facilityid=?";
		OtherEvalutionVo otherevalutionvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.OtherEvalutionVo.class);
			if (list != null && list.size() > 0)
				otherevalutionvo = (OtherEvalutionVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("OtherEvalutionVo findByPrimaryKey():"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return otherevalutionvo;
	}

	public static int add(OtherEvalutionVo otherevalutionvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(otherevalutionvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into OTHEREVALUTION ");
		stringbuffer
				.append("(OTHEREVALUTIONID,facilityid,SPCC_REQ,STORMPREVENTION_REQ,EPAAUDIT_REQ,ACCFINDING_REQ,HAZWASTE_REQ,HAZR_REQ,TIER2_REQ,NYCCOM_REQ,DOBVIO_REQ,FD_REQ,ECP_REQ,DEP_REQ,ANYSTATE_REQ,DOH_REQ,BUILTINPLAN_REQ,FUELINVENTORY_REQ,OPACITY_REQ,REFRI_REQ,RISK_REQ,OTHER_REQ,SPCC_AVI,STORMPREVENTION_AVI,EPAAUDIT_AVI,ACCFINDING_AVI,HAZWASTE_AVI,HAZR_AVI,TIER2_AVI,NYCCOM_AVI,DOBVIO_AVI,FD_AVI,ECP_AVI,DEP_AVI,ANYSTATE_AVI,DOH_AVI,BUILTINPLAN_AVI,FUELINVENTORY_AVI,OPACITY_AVI,REFRI_AVI,RISK_AVI,OTHER_AVI,OTHER_INP,LAUNDRY_AVI,LAUNDRY_REQ,COMPLIANCEREPORT_AVI,COMPLIANCEREPORT_REQ,DEPOFENERGY_REQ,DEPOFENERGY_AVI)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("'").append(otherevalutionvo.getFacilityId())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getSpcc_req())
				.append("',");
		stringbuffer.append("'")
				.append(otherevalutionvo.getStormprevention_req()).append("',");
		stringbuffer.append("'").append(otherevalutionvo.getEpaaudit_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getAccfinding_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getHazwaste_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getHazr_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getTier2_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getNyccom_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDobvio_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getFd_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getEcp_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDep_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getAnystate_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDoh_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getBuiltinplan_req())
				.append("',");
		stringbuffer.append("'")
				.append(otherevalutionvo.getFuelinventory_req()).append("',");
		stringbuffer.append("'").append(otherevalutionvo.getOpacity_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getRefri_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getRisk_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getOther_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getSpcc_avi())
				.append("',");
		stringbuffer.append("'")
				.append(otherevalutionvo.getStormprevention_avi()).append("',");
		stringbuffer.append("'").append(otherevalutionvo.getEpaaudit_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getAccfinding_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getHazwaste_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getHazr_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getTier2_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getNyccom_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDobvio_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getFd_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getEcp_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDep_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getAnystate_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDoh_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getBuiltinplan_avi())
				.append("',");
		stringbuffer.append("'")
				.append(otherevalutionvo.getFuelinventory_avi()).append("',");
		stringbuffer.append("'").append(otherevalutionvo.getOpacity_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getRefri_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getRisk_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getOther_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getOther_inp())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getLaundry_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getLaundry_req())
				.append("',");
		stringbuffer.append("'")
				.append(otherevalutionvo.getCompliancereport_avi())
				.append("',");
		stringbuffer.append("'")
				.append(otherevalutionvo.getCompliancereport_req())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDepofenergy_avi())
				.append("',");
		stringbuffer.append("'").append(otherevalutionvo.getDepofenergy_req())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding OtherEvalution:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding OtherEvalution.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(OtherEvalutionVo otherevalutionvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(otherevalutionvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update OTHEREVALUTION set ");
		stringbuffer.append("FACILITYID=?,");
		stringbuffer.append("SPCC_REQ=?,");
		stringbuffer.append("STORMPREVENTION_REQ=?,");
		stringbuffer.append("EPAAUDIT_REQ=?,");
		stringbuffer.append("ACCFINDING_REQ=?,");
		stringbuffer.append("HAZWASTE_REQ=?,");
		stringbuffer.append("HAZR_REQ=?,");
		stringbuffer.append("TIER2_REQ=?,");
		stringbuffer.append("NYCCOM_REQ=?,");
		stringbuffer.append("DOBVIO_REQ=?,");
		stringbuffer.append("FD_REQ=?,");
		stringbuffer.append("ECP_REQ=?,");
		stringbuffer.append("DEP_REQ=?,");
		stringbuffer.append("ANYSTATE_REQ=?,");
		stringbuffer.append("DOH_REQ=?,");
		stringbuffer.append("BUILTINPLAN_REQ=?,");
		stringbuffer.append("FUELINVENTORY_REQ=?,");
		stringbuffer.append("OPACITY_REQ=?,");
		stringbuffer.append("REFRI_REQ=?,");
		stringbuffer.append("RISK_REQ=?,");
		stringbuffer.append("OTHER_REQ=?,");
		stringbuffer.append("SPCC_AVI=?,");
		stringbuffer.append("STORMPREVENTION_AVI=?,");
		stringbuffer.append("EPAAUDIT_AVI=?,");
		stringbuffer.append("ACCFINDING_AVI=?,");
		stringbuffer.append("HAZWASTE_AVI=?,");
		stringbuffer.append("HAZR_AVI=?,");
		stringbuffer.append("TIER2_AVI=?,");
		stringbuffer.append("NYCCOM_AVI=?,");
		stringbuffer.append("DOBVIO_AVI=?,");
		stringbuffer.append("FD_AVI=?,");
		stringbuffer.append("ECP_AVI=?,");
		stringbuffer.append("DEP_AVI=?,");
		stringbuffer.append("ANYSTATE_AVI=?,");
		stringbuffer.append("DOH_AVI=?,");
		stringbuffer.append("BUILTINPLAN_AVI=?,");
		stringbuffer.append("FUELINVENTORY_AVI=?,");
		stringbuffer.append("OPACITY_AVI=?,");
		stringbuffer.append("REFRI_AVI=?,");
		stringbuffer.append("RISK_AVI=?,");
		stringbuffer.append("OTHER_AVI=?,");
		stringbuffer.append("OTHER_INP=?,");
		stringbuffer.append("LAUNDRY_AVI=?,");
		stringbuffer.append("LAUNDRY_REQ=?,");
		stringbuffer.append("COMPLIANCEREPORT_AVI=?,");
		stringbuffer.append("COMPLIANCEREPORT_REQ=?, ");
		stringbuffer.append("DEPOFENERGY_AVI=?,");
		stringbuffer.append("DEPOFENERGY_REQ=? ");

		stringbuffer.append("where OTHEREVALUTIONID=? ");
		SqlUtil sqlutil = new SqlUtil();

		sqlutil.addInParam(otherevalutionvo.getFacilityId());
		sqlutil.addInParam(otherevalutionvo.getSpcc_req());
		sqlutil.addInParam(otherevalutionvo.getStormprevention_req());
		sqlutil.addInParam(otherevalutionvo.getEpaaudit_req());
		sqlutil.addInParam(otherevalutionvo.getAccfinding_req());
		sqlutil.addInParam(otherevalutionvo.getHazwaste_req());
		sqlutil.addInParam(otherevalutionvo.getHazr_req());
		sqlutil.addInParam(otherevalutionvo.getTier2_req());
		sqlutil.addInParam(otherevalutionvo.getNyccom_req());
		sqlutil.addInParam(otherevalutionvo.getDobvio_req());
		sqlutil.addInParam(otherevalutionvo.getFd_req());
		sqlutil.addInParam(otherevalutionvo.getEcp_req());
		sqlutil.addInParam(otherevalutionvo.getDep_req());
		sqlutil.addInParam(otherevalutionvo.getAnystate_req());
		sqlutil.addInParam(otherevalutionvo.getDoh_req());
		sqlutil.addInParam(otherevalutionvo.getBuiltinplan_req());
		sqlutil.addInParam(otherevalutionvo.getFuelinventory_req());
		sqlutil.addInParam(otherevalutionvo.getOpacity_req());
		sqlutil.addInParam(otherevalutionvo.getRefri_req());
		sqlutil.addInParam(otherevalutionvo.getRisk_req());
		sqlutil.addInParam(otherevalutionvo.getOther_req());
		sqlutil.addInParam(otherevalutionvo.getSpcc_avi());
		sqlutil.addInParam(otherevalutionvo.getStormprevention_avi());
		sqlutil.addInParam(otherevalutionvo.getEpaaudit_avi());
		sqlutil.addInParam(otherevalutionvo.getAccfinding_avi());
		sqlutil.addInParam(otherevalutionvo.getHazwaste_avi());
		sqlutil.addInParam(otherevalutionvo.getHazr_avi());
		sqlutil.addInParam(otherevalutionvo.getTier2_avi());
		sqlutil.addInParam(otherevalutionvo.getNyccom_avi());
		sqlutil.addInParam(otherevalutionvo.getDobvio_avi());
		sqlutil.addInParam(otherevalutionvo.getFd_avi());
		sqlutil.addInParam(otherevalutionvo.getEcp_avi());
		sqlutil.addInParam(otherevalutionvo.getDep_avi());
		sqlutil.addInParam(otherevalutionvo.getAnystate_avi());
		sqlutil.addInParam(otherevalutionvo.getDoh_avi());
		sqlutil.addInParam(otherevalutionvo.getBuiltinplan_avi());
		sqlutil.addInParam(otherevalutionvo.getFuelinventory_avi());
		sqlutil.addInParam(otherevalutionvo.getOpacity_avi());
		sqlutil.addInParam(otherevalutionvo.getRefri_avi());
		sqlutil.addInParam(otherevalutionvo.getRisk_avi());
		sqlutil.addInParam(otherevalutionvo.getOther_avi());
		sqlutil.addInParam(otherevalutionvo.getOther_inp());
		sqlutil.addInParam(otherevalutionvo.getLaundry_avi());
		sqlutil.addInParam(otherevalutionvo.getLaundry_req());
		sqlutil.addInParam(otherevalutionvo.getCompliancereport_avi());
		sqlutil.addInParam(otherevalutionvo.getCompliancereport_req());
		sqlutil.addInParam(otherevalutionvo.getDepofenergy_avi());
		sqlutil.addInParam(otherevalutionvo.getDepofenergy_req());

		sqlutil.addInParam(otherevalutionvo.getId());
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating OTHEREVALUTION:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating OTHEREVALUTION.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.OtherEvalutionEntity.class);

}