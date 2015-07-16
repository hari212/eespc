package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FumehoodVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.myenum.FumehoodChemicalsEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class FumehoodEntity {

	public FumehoodEntity() {
	}

	public static FumehoodVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, fumehoods fh where bld.buildingid = fh.buildingid and fh.FUMEHOODID =?";
		FumehoodVo fumehoodvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.FumehoodVo.class);
			if (list != null && list.size() > 0)
				fumehoodvo = (FumehoodVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return fumehoodvo;
	}

	public static int add(FumehoodVo fumehoodvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(fumehoodvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into FUMEHOODS ");
		stringbuffer.append("(FUMEHOODID, ");
		stringbuffer.append("BUILDINGID, ");
		stringbuffer.append("STACKEXHAUSTID, ");
		stringbuffer.append("FACILITYDESIGNATEDID, ");
		stringbuffer.append("FLOOR, ");
		stringbuffer.append("YEARINSTALLED, ");
		stringbuffer.append("STATUS, ");
		stringbuffer.append("DISCONNECTEDDATE, ");
		stringbuffer.append("MAKE, ");
		stringbuffer.append("MODEL, ");
		stringbuffer.append("chemical1, ");
		stringbuffer.append("chemicalname1, ");
		stringbuffer.append("volume1, ");
		stringbuffer.append("density1, ");
		stringbuffer.append("voc1, ");
		stringbuffer.append("voctot1, ");
		stringbuffer.append("chemical2, ");
		stringbuffer.append("chemicalname2, ");
		stringbuffer.append("volume2, ");
		stringbuffer.append("density2, ");
		stringbuffer.append("voc2, ");
		stringbuffer.append("voctot2, ");
		stringbuffer.append("chemical3, ");
		stringbuffer.append("chemicalname3, ");
		stringbuffer.append("volume3, ");
		stringbuffer.append("density3, ");
		stringbuffer.append("voc3, ");
		stringbuffer.append("voctot3, ");
		stringbuffer.append("chemical4, ");
		stringbuffer.append("chemicalname4, ");
		stringbuffer.append("volume4, ");
		stringbuffer.append("density4, ");
		stringbuffer.append("voc4, ");
		stringbuffer.append("voctot4, ");
		stringbuffer.append("chemical5, ");
		stringbuffer.append("chemicalname5, ");
		stringbuffer.append("volume5, ");
		stringbuffer.append("density5, ");
		stringbuffer.append("voc5, ");
		stringbuffer.append("voctot5, ");
		stringbuffer.append("VOC, ");
		stringbuffer.append("HROFOPERATION, ");
		stringbuffer.append("EXEMPTEDBYDEC, ");
		stringbuffer.append("INCLUDEDINDECPERMIT, ");
		stringbuffer.append("DOB, ");
		stringbuffer.append("LOCATION,");
		stringbuffer.append("FUMEHOODHOURSOFOPERATION1, ");
		stringbuffer.append("FUMEHOODDEPNUMBER, ");
		stringbuffer.append("FCOMMENTS, ");
		stringbuffer.append("dobsignoff, ");
		stringbuffer.append("DEPPERMITSTATUS, ");
		stringbuffer
				.append("DOBPERMITSTATUS,ANNUALPERMITSTATUS,LASTINSPECTIONDATE,NEXTINSPECTIONDATE)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(fumehoodvo.getBuildingId()).append(",");
		stringbuffer.append(fumehoodvo.getStackId()).append(",");
		stringbuffer.append("'").append(fumehoodvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getFloor()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getYearinstalled())
				.append("',");
		stringbuffer.append("").append(fumehoodvo.getStatus()).append(",");
		if (fumehoodvo.getDisconnecteddate().equals(null))
			stringbuffer.append("").append(fumehoodvo.getDisconnecteddate())
					.append(",");
		else
			stringbuffer.append("'").append(fumehoodvo.getDisconnecteddate())
					.append("',");
		stringbuffer.append("'").append(fumehoodvo.getMake()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getModel()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemical1()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemicalname1())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getVolume1()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getDensity1()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoc1()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoctot1()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemical2()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemicalname2())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getVolume2()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getDensity2()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoc2()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoctot2()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemical3()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemicalname3())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getVolume3()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getDensity3()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoc3()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoctot3()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemical4()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemicalname4())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getVolume4()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getDensity4()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoc4()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoctot4()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemical5()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getChemicalname5())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getVolume5()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getDensity5()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoc5()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getVoctot5()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getvoc()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getHrsOfOperation())
				.append("',");
		if (fumehoodvo.isExemptedByDec())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (fumehoodvo.isUnitIncludedInDecPermit())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		stringbuffer.append("'").append(fumehoodvo.getdob()).append("',");
		stringbuffer.append(fumehoodvo.getLocation()).append(",");
		stringbuffer.append("'")
				.append(fumehoodvo.getFumehoodHoursOfOperation1()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getFumehoodDEPNumber())
				.append("',");
		stringbuffer.append("'").append(fumehoodvo.getFcomments()).append("',");
		stringbuffer.append("'").append(fumehoodvo.getDobsignoff())
				.append("',");
		stringbuffer.append("'" + fumehoodvo.isDepPermitStatus() + "',");
		stringbuffer.append("'" + fumehoodvo.isDobPermitStatus() + "',");
		stringbuffer.append("'" + fumehoodvo.isAnnualPermitStatus() + "',");

		if (isNotEmpty(fumehoodvo.getLastInspectionDate()))
			stringbuffer.append("'").append(fumehoodvo.getLastInspectionDate())
					.append("',");
		else
			stringbuffer.append("null,");

		if (isNotEmpty(fumehoodvo.getNextInspectionDate()))
			stringbuffer.append("'").append(fumehoodvo.getNextInspectionDate())
					.append("'");
		else
			stringbuffer.append("null");
		stringbuffer.append(")");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding Fume hood:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Fume hood");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static void update(FumehoodVo fumehoodvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(fumehoodvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update FUMEHOODS set ");
		stringbuffer.append("BUILDINGID=?, ");
		stringbuffer.append("STACKEXHAUSTID=?, ");
		stringbuffer.append("FACILITYDESIGNATEDID=?, ");
		stringbuffer.append("FLOOR=?, ");
		stringbuffer.append("YEARINSTALLED=?, ");
		stringbuffer.append("STATUS=?, ");
		stringbuffer.append("DISCONNECTEDDATE=?, ");
		stringbuffer.append("MAKE=?, ");
		stringbuffer.append("MODEL=?, ");
		stringbuffer.append("chemical1=?, ");
		stringbuffer.append("chemicalname1=?, ");
		stringbuffer.append("volume1=?, ");
		stringbuffer.append("density1=?, ");
		stringbuffer.append("voc1=?, ");
		stringbuffer.append("voctot1=?, ");
		stringbuffer.append("chemical2=?, ");
		stringbuffer.append("chemicalname2=?, ");
		stringbuffer.append("volume2=?, ");
		stringbuffer.append("density2=?, ");
		stringbuffer.append("voc2=?, ");
		stringbuffer.append("voctot2=?, ");
		stringbuffer.append("chemical3=?, ");
		stringbuffer.append("chemicalname3=?, ");
		stringbuffer.append("volume3=?, ");
		stringbuffer.append("density3=?, ");
		stringbuffer.append("voc3=?, ");
		stringbuffer.append("voctot3=?, ");
		stringbuffer.append("chemical4=?, ");
		stringbuffer.append("chemicalname4=?, ");
		stringbuffer.append("volume4=?, ");
		stringbuffer.append("density4=?, ");
		stringbuffer.append("voc4=?, ");
		stringbuffer.append("voctot4=?, ");
		stringbuffer.append("chemical5=?, ");
		stringbuffer.append("chemicalname5=?, ");
		stringbuffer.append("volume5=?, ");
		stringbuffer.append("density5=?, ");
		stringbuffer.append("voc5=?, ");
		stringbuffer.append("voctot5=?, ");
		stringbuffer.append("VOC=?, ");
		stringbuffer.append("HROFOPERATION=?, ");
		stringbuffer.append("EXEMPTEDBYDEC=?, ");
		stringbuffer.append("INCLUDEDINDECPERMIT=?, ");
		stringbuffer.append("DOB=?, ");
		stringbuffer.append("LOCATION=?, ");
		stringbuffer.append("FUMEHOODHOURSOFOPERATION1=?,");
		stringbuffer.append("FUMEHOODDEPNUMBER=?,");
		stringbuffer.append("FCOMMENTS=?, ");
		stringbuffer.append("DOBSIGNOFF=?, ");
		stringbuffer.append("DEPPERMITSTATUS=?, ");
		stringbuffer.append("DOBPERMITSTATUS=?, ");
		stringbuffer.append("ANNUALPERMITSTATUS=?, ");
		stringbuffer.append("LASTINSPECTIONDATE=?, ");
		stringbuffer.append("NEXTINSPECTIONDATE=? ");
		stringbuffer.append("where FUMEHOODID=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(fumehoodvo.getBuildingId()));
		sqlutil.addInParam(new Integer(fumehoodvo.getStackId()));
		sqlutil.addInParam(fumehoodvo.getFacilityDesignatedId());
		sqlutil.addInParam(fumehoodvo.getFloor());
		sqlutil.addInParam(fumehoodvo.getYearinstalled());
		sqlutil.addInParam(String.valueOf(fumehoodvo.getStatus()));
		sqlutil.addInParam(fumehoodvo.getDisconnecteddate());
		sqlutil.addInParam(fumehoodvo.getMake());
		sqlutil.addInParam(fumehoodvo.getModel());
		sqlutil.addInParam(fumehoodvo.getChemical1());
		sqlutil.addInParam(fumehoodvo.getChemicalname1());
		sqlutil.addInParam(fumehoodvo.getVolume1());
		sqlutil.addInParam(fumehoodvo.getDensity1());
		sqlutil.addInParam(fumehoodvo.getVoc1());
		sqlutil.addInParam(fumehoodvo.getVoctot1());
		sqlutil.addInParam(fumehoodvo.getChemical2());
		sqlutil.addInParam(fumehoodvo.getChemicalname2());
		sqlutil.addInParam(fumehoodvo.getVolume2());
		sqlutil.addInParam(fumehoodvo.getDensity2());
		sqlutil.addInParam(fumehoodvo.getVoc2());
		sqlutil.addInParam(fumehoodvo.getVoctot2());
		sqlutil.addInParam(fumehoodvo.getChemical3());
		sqlutil.addInParam(fumehoodvo.getChemicalname3());
		sqlutil.addInParam(fumehoodvo.getVolume3());
		sqlutil.addInParam(fumehoodvo.getDensity3());
		sqlutil.addInParam(fumehoodvo.getVoc3());
		sqlutil.addInParam(fumehoodvo.getVoctot3());
		sqlutil.addInParam(fumehoodvo.getChemical4());
		sqlutil.addInParam(fumehoodvo.getChemicalname4());
		sqlutil.addInParam(fumehoodvo.getVolume4());
		sqlutil.addInParam(fumehoodvo.getDensity4());
		sqlutil.addInParam(fumehoodvo.getVoc4());
		sqlutil.addInParam(fumehoodvo.getVoctot4());
		sqlutil.addInParam(fumehoodvo.getChemical5());
		sqlutil.addInParam(fumehoodvo.getChemicalname5());
		sqlutil.addInParam(fumehoodvo.getVolume5());
		sqlutil.addInParam(fumehoodvo.getDensity5());
		sqlutil.addInParam(fumehoodvo.getVoc5());
		sqlutil.addInParam(fumehoodvo.getVoctot5());
		sqlutil.addInParam(fumehoodvo.getvoc());
		sqlutil.addInParam(fumehoodvo.getHrsOfOperation());
		if (fumehoodvo.isExemptedByDec())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (fumehoodvo.isUnitIncludedInDecPermit())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(fumehoodvo.getdob());
		sqlutil.addInParam(new Integer(fumehoodvo.getLocation()));
		sqlutil.addInParam(fumehoodvo.getFumehoodHoursOfOperation1());
		sqlutil.addInParam(fumehoodvo.getFumehoodDEPNumber());
		sqlutil.addInParam(fumehoodvo.getFcomments());
		sqlutil.addInParam(fumehoodvo.getDobsignoff());
		sqlutil.addInParam(fumehoodvo.isDepPermitStatus());
		sqlutil.addInParam(fumehoodvo.isDobPermitStatus());
		sqlutil.addInParam(fumehoodvo.isAnnualPermitStatus());

		if (isNotEmpty(fumehoodvo.getLastInspectionDate())) {
			sqlutil.addInParam(fumehoodvo.getLastInspectionDate());
		} else {
			sqlutil.addInParam(null);
		}

		if (isNotEmpty(fumehoodvo.getNextInspectionDate())) {
			sqlutil.addInParam(fumehoodvo.getNextInspectionDate());
		} else {
			sqlutil.addInParam(null);
		}

		sqlutil.addInParam(new Integer(fumehoodvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Fume hood:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Fume Hood.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(FumehoodVo fumehoodvo) throws TrackingException {
		String as[] = { "delete from FUMEHOODTOCHEMICALS where FUMEHOODID=?",
				"delete from FUMEHOODPERMITINFO where FUMEHOODID=?",
				"delete from FUMEHOODS where FUMEHOODID=?" };
		for (int i = 0; i <= 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(fumehoodvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating fume hood.");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static List getPermitInfo(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select FUMEHOODPERMITID as permit_id, ");
		stringbuffer.append("FUMEHOODID as object_id, ");
		stringbuffer
				.append("9 as object_type, PERMITNUMBER as permit_number, ");
		stringbuffer.append("ISSUEDDATE as permit_issue_date, ");
		stringbuffer.append("EXPIRATIONDATE as permit_exp_date, ");
		stringbuffer.append("NOTE as note, ");
		stringbuffer.append("DEPID as dept_id ");
		stringbuffer.append("from FUMEHOODPERMITINFO ");
		stringbuffer.append("where FUMEHOODID = ? ");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.PermitInfoVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getPermitInfo(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static int addPermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into FUMEHOODPERMITINFO ");
		stringbuffer.append("(FUMEHOODPERMITID, FUMEHOODID, PERMITNUMBER, ");
		stringbuffer.append("ISSUEDDATE, EXPIRATIONDATE, NOTE,DEPID) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(permitinfovo.getObjectId()).append(",");
		stringbuffer.append("'").append(permitinfovo.getPermitNumber())
				.append("',");
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
			System.out
					.println("While Adding Fume hood Permit Info" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Fume hood Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void updatePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		System.out.println((new StringBuilder()).append("luca")
				.append(permitinfovo.getPermitNumber()).toString());
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update FUMEHOODPERMITINFO");
		stringbuffer
				.append(" set PERMITNUMBER=?, ISSUEDDATE=?, EXPIRATIONDATE=?, NOTE=?,");
		stringbuffer.append("FUMEHOODID=?, DEPID=?");
		stringbuffer.append(" where FUMEHOODPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(permitinfovo.getPermitNumber());
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
			sqlutil.addInParam(new Integer(permitinfovo.getObjectId()));
			sqlutil.addInParam(new Integer(permitinfovo.getDepId()));
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While updating Fume hood Permit Info"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					"While updating Fume hood Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deletePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		System.out.println((new StringBuilder()).append("luca")
				.append(permitinfovo.getPermitNumber()).toString());
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from FUMEHOODPERMITINFO");
		stringbuffer.append(" where FUMEHOODPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Fume hood Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static int addChemical(int i, List list) throws TrackingException {
		int j = -99;
		for (int k = 0; k < list.size(); k++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into fumehoodtochemicals ");
			stringbuffer.append("(FUMEHOODID, CHEMICALID)");
			stringbuffer.append(" values (");
			stringbuffer.append(i).append(",");
			stringbuffer.append(list.get(k)).append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				j = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Adding Fume Hood Chemical Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		return j;
	}

	public static void updateChemical(int i, List list)
			throws TrackingException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("Delete from fumehoodtochemicals ");
		stringbuffer.append(" where FUMEHOODID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(i));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			int j = addChemical(i, list);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Spray Booth Chemical Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getChemicalList(int id) throws TrackingException {
		List chemicalList;
		chemicalList = new ArrayList();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append(" select CHEMICALID from fumehoodtochemicals ");
		queryBuff.append(" where FUMEHOODID =").append(id);
		SqlUtil util = new SqlUtil();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			for (rst = stmt.executeQuery(queryBuff.toString()); rst.next();) {
				int code = rst.getString("CHEMICALID") == null ? -99 : rst
						.getInt("CHEMICALID");
				FumehoodChemicalsEnum tempEnumObj = FumehoodChemicalsEnum
						.get(code);
				if (tempEnumObj != null)
					chemicalList.add(tempEnumObj);
			}

		} catch (Exception e) {
			TrackingException ex = new TrackingException("getChemicalList("
					+ id + ")");
			ex.initCause(e);
			throw ex;
		} finally {
			log.debug(queryBuff.toString());
			SqlUtil.close(rst);
			SqlUtil.close(stmt);
			util.closeConnection();
		}
		return chemicalList;
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.FUMEHOODID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, bldg.BUILDINGNAME from fumehoods stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.FumehoodListVo.class);
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
			.getLog(com.eespc.tracking.entity.FumehoodEntity.class);

}