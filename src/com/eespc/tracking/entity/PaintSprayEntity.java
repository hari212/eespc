package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.PaintSprayVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.myenum.SprayBoothChemicalsEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class PaintSprayEntity {

	public PaintSprayEntity() {
	}

	public static PaintSprayVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, spraybooth sBooth where bld.buildingid = sBooth.buildingid and sBooth.sprayboothid =?";
		PaintSprayVo paintsprayvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PaintSprayVo.class);
			if (list != null && list.size() > 0)
				paintsprayvo = (PaintSprayVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return paintsprayvo;
	}

	public static int add(PaintSprayVo paintsprayvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(paintsprayvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into SPRAYBOOTH ");
		stringbuffer.append("(SPRAYBOOTHID, ");
		stringbuffer.append("BUILDINGID, ");
		stringbuffer.append("STACKEXHAUSTID, ");
		stringbuffer.append("FACILITYDESIGNATEDID, ");
		stringbuffer.append("FLOOR, ");
		stringbuffer.append("YEARINSTALLED, ");
		stringbuffer.append("STATUS, ");
		stringbuffer.append("DISCONNECTEDDATE, ");
		stringbuffer.append("MAKE, ");
		stringbuffer.append("MODEL, ");
		stringbuffer.append("HROFOPERATION, ");
		stringbuffer.append("EXEMPTEDBYDEC, ");
		stringbuffer.append("INCLUDEDINDECPERMIT, ");
		stringbuffer.append("ACTIONTAKEN, ");
		stringbuffer
				.append("LOCATION,PSDEPNUMBER,SPRAYPAINT,SPRAYSOLVANT,SPRAYINK,SPRAYOTHER,SPRAYPAINTNAME,SPRAYSOLVANTNAME,SPRAYINKNAME,SPRAYOTHERNAME,SPRAYPAINTVOCPERCENT,SPRAYSOLVANTVOCPERCENT,SPRAYINKVOCPERCENT,SPRAYOTHERVOCPERCENT,SPRAYPAINTVOLUME,SPRAYSOLVANTVOLUME,SPRAYINKVOLUME,SPRAYOTHERVOLUME,SPRAYPAINTDENSITY,SPRAYSOLVANTDENSITY,SPRAYINKDENSITY,SPRAYOTHERDENSITY,SPRAYPAINTVOC,SPRAYSOLVANTVOC,SPRAYINKVOC,SPRAYOTHERVOC,SPRAYPAINTCAP,SPRAYSOLVANTCAP,SPRAYINKCAP,VOCYEAR,VOCMONTH,VOCCAP,VOCMONTHLYQUANTITY,VOCMONTHLYLIMIT,PSHOURSOFOPERATIONYEAR,DOBSIGNOFF,DOBNUMBER)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(paintsprayvo.getBuildingId()).append(",");
		stringbuffer.append(paintsprayvo.getStackId()).append(",");
		stringbuffer.append("'").append(paintsprayvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getFloor()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getYearinstalled())
				.append("',");
		stringbuffer.append("").append(paintsprayvo.getStatus()).append(",");
		if (paintsprayvo.getDisconnecteddate().equals(null))
			stringbuffer.append("").append(paintsprayvo.getDisconnecteddate())
					.append(",");
		else
			stringbuffer.append("'").append(paintsprayvo.getDisconnecteddate())
					.append("',");
		stringbuffer.append("'").append(paintsprayvo.getMake()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getModel()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getHrsOfOperation())
				.append("',");
		if (paintsprayvo.isExemptedByDec())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (paintsprayvo.isUnitIncludedInDecPermit())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		stringbuffer.append("'").append(paintsprayvo.getActionTaken())
				.append("',");
		stringbuffer.append(paintsprayvo.getLocation()).append(",");
		stringbuffer.append("'").append(paintsprayvo.getPsDEPNumber())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaint())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraysolvant())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayink())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayother())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaintname())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraysolvantname())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayinkname())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayothername())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaintvocpercent())
				.append("',");
		stringbuffer.append("'")
				.append(paintsprayvo.getSpraysolvantvocpercent()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayinkvocpercent())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayothervocpercent())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaintvolume())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraysolvantvolume())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayinkvolume())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayothervolume())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaintdensity())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraysolvantdensity())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayinkdensity())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayotherdensity())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaintvoc())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraysolvantvoc())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayinkvoc())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayothervoc())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraypaintcap())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSpraysolvantcap())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getSprayinkcap())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getVocyear()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getVocmonth())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getVoccap()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getVocmonthlyquantity())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getVocmonthlylimit())
				.append("',");
		stringbuffer.append("'")
				.append(paintsprayvo.getPsHoursOfOperationyear()).append("',");
		stringbuffer.append("'").append(paintsprayvo.getDobsignoff())
				.append("',");
		stringbuffer.append("'").append(paintsprayvo.getDobnumber())
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding Spray Booth:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Spray Booth.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(PaintSprayVo paintsprayvo)
			throws TrackingException {
		String as[] = {
				"delete from SPRAYBOOTHTOCHEMICALS where SPRAYBOOTHID=?",
				"delete from SPRAYBOOTHPERMITINFO where SPRAYBOOTHID=?",
				"delete from SPRAYBOOTH where SPRAYBOOTHID=?" };
		for (int i = 0; i <= 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(paintsprayvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While Deleting Spray Booth:" + exception);
				TrackingException trackingexception = new TrackingException(
						"While Updating Spray Booth.");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static void update(PaintSprayVo paintsprayvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(paintsprayvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update SPRAYBOOTH set ");
		stringbuffer.append("BUILDINGID=?, ");
		stringbuffer.append("STACKEXHAUSTID=?, ");
		stringbuffer.append("FACILITYDESIGNATEDID=?, ");
		stringbuffer.append("FLOOR=?, ");
		stringbuffer.append("YEARINSTALLED=?, ");
		stringbuffer.append("STATUS=?, ");
		stringbuffer.append("DISCONNECTEDDATE=?, ");
		stringbuffer.append("MAKE=?, ");
		stringbuffer.append("MODEL=?, ");
		stringbuffer.append("HROFOPERATION=?, ");
		stringbuffer.append("EXEMPTEDBYDEC=?, ");
		stringbuffer.append("INCLUDEDINDECPERMIT=?, ");
		stringbuffer.append("ACTIONTAKEN=?, ");
		stringbuffer.append("LOCATION=?, ");
		stringbuffer.append("PSDEPNUMBER=?,");
		stringbuffer.append("SPRAYPAINT=?,");
		stringbuffer.append("SPRAYSOLVANT=?,");
		stringbuffer.append("SPRAYINK=?,");
		stringbuffer.append("SPRAYOTHER=?,");
		stringbuffer.append("SPRAYPAINTNAME=?,");
		stringbuffer.append("SPRAYSOLVANTNAME=?,");
		stringbuffer.append("SPRAYINKNAME=?,");
		stringbuffer.append("SPRAYOTHERNAME=?,");
		stringbuffer.append("SPRAYPAINTVOCPERCENT=?,");
		stringbuffer.append("SPRAYSOLVANTVOCPERCENT=?,");
		stringbuffer.append("SPRAYINKVOCPERCENT=?,");
		stringbuffer.append("SPRAYOTHERVOCPERCENT=?,");
		stringbuffer.append("SPRAYPAINTVOLUME=?,");
		stringbuffer.append("SPRAYSOLVANTVOLUME=?,");
		stringbuffer.append("SPRAYINKVOLUME=?,");
		stringbuffer.append("SPRAYOTHERVOLUME=?,");
		stringbuffer.append("SPRAYPAINTDENSITY=?,");
		stringbuffer.append("SPRAYSOLVANTDENSITY=?,");
		stringbuffer.append("SPRAYINKDENSITY=?,");
		stringbuffer.append("SPRAYOTHERDENSITY=?,");
		stringbuffer.append("SPRAYPAINTVOC=?,");
		stringbuffer.append("SPRAYSOLVANTVOC=?,");
		stringbuffer.append("SPRAYINKVOC=?,");
		stringbuffer.append("SPRAYOTHERVOC=?,");
		stringbuffer.append("SPRAYPAINTCAP=?,");
		stringbuffer.append("SPRAYSOLVANTCAP=?,");
		stringbuffer.append("SPRAYINKCAP=?,");
		stringbuffer.append("VOCYEAR=?,");
		stringbuffer.append("VOCMONTH=?,");
		stringbuffer.append("VOCCAP=?,");
		stringbuffer.append("VOCMONTHLYQUANTITY=?,");
		stringbuffer.append("VOCMONTHLYLIMIT=?,");
		stringbuffer.append("PSHOURSOFOPERATIONYEAR=?, ");
		stringbuffer.append("DOBSIGNOFF=?,");
		stringbuffer.append("DOBNUMBER=? ");
		stringbuffer.append("where SPRAYBOOTHID=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(paintsprayvo.getBuildingId()));
		sqlutil.addInParam(new Integer(paintsprayvo.getStackId()));
		sqlutil.addInParam(paintsprayvo.getFacilityDesignatedId());
		sqlutil.addInParam(paintsprayvo.getFloor());
		sqlutil.addInParam(paintsprayvo.getYearinstalled());
		sqlutil.addInParam(String.valueOf(paintsprayvo.getStatus()));
		sqlutil.addInParam(paintsprayvo.getDisconnecteddate());
		sqlutil.addInParam(paintsprayvo.getMake());
		sqlutil.addInParam(paintsprayvo.getModel());
		sqlutil.addInParam(paintsprayvo.getHrsOfOperation());
		if (paintsprayvo.isExemptedByDec())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (paintsprayvo.isUnitIncludedInDecPermit())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(paintsprayvo.getActionTaken());
		sqlutil.addInParam(new Integer(paintsprayvo.getLocation()));
		sqlutil.addInParam(paintsprayvo.getPsDEPNumber());
		sqlutil.addInParam(paintsprayvo.getSpraypaint());
		sqlutil.addInParam(paintsprayvo.getSpraysolvant());
		sqlutil.addInParam(paintsprayvo.getSprayink());
		sqlutil.addInParam(paintsprayvo.getSprayother());
		sqlutil.addInParam(paintsprayvo.getSpraypaintname());
		sqlutil.addInParam(paintsprayvo.getSpraysolvantname());
		sqlutil.addInParam(paintsprayvo.getSprayinkname());
		sqlutil.addInParam(paintsprayvo.getSprayothername());
		sqlutil.addInParam(paintsprayvo.getSpraypaintvocpercent());
		sqlutil.addInParam(paintsprayvo.getSpraysolvantvocpercent());
		sqlutil.addInParam(paintsprayvo.getSprayinkvocpercent());
		sqlutil.addInParam(paintsprayvo.getSprayothervocpercent());
		sqlutil.addInParam(paintsprayvo.getSpraypaintvolume());
		sqlutil.addInParam(paintsprayvo.getSpraysolvantvolume());
		sqlutil.addInParam(paintsprayvo.getSprayinkvolume());
		sqlutil.addInParam(paintsprayvo.getSprayothervolume());
		sqlutil.addInParam(paintsprayvo.getSpraypaintdensity());
		sqlutil.addInParam(paintsprayvo.getSpraysolvantdensity());
		sqlutil.addInParam(paintsprayvo.getSprayinkdensity());
		sqlutil.addInParam(paintsprayvo.getSprayotherdensity());
		sqlutil.addInParam(paintsprayvo.getSpraypaintvoc());
		sqlutil.addInParam(paintsprayvo.getSpraysolvantvoc());
		sqlutil.addInParam(paintsprayvo.getSprayinkvoc());
		sqlutil.addInParam(paintsprayvo.getSprayothervoc());
		sqlutil.addInParam(paintsprayvo.getSpraypaintcap());
		sqlutil.addInParam(paintsprayvo.getSpraysolvantcap());
		sqlutil.addInParam(paintsprayvo.getSprayinkcap());
		sqlutil.addInParam(paintsprayvo.getVocyear());
		sqlutil.addInParam(paintsprayvo.getVocmonth());
		sqlutil.addInParam(paintsprayvo.getVoccap());
		sqlutil.addInParam(paintsprayvo.getVocmonthlyquantity());
		sqlutil.addInParam(paintsprayvo.getVocmonthlylimit());
		sqlutil.addInParam(paintsprayvo.getPsHoursOfOperationyear());
		sqlutil.addInParam(paintsprayvo.getDobsignoff());
		sqlutil.addInParam(paintsprayvo.getDobnumber());
		sqlutil.addInParam(new Integer(paintsprayvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Spray Booth:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Spray Booth.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getPermitInfo(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select SPRAYBOOTHPERMITID as permit_id, ");
		stringbuffer.append("SPRAYBOOTHID as object_id, ");
		stringbuffer
				.append("9 as object_type, PERMITNUMBER as permit_number, ");
		stringbuffer.append("ISSUEDDATE as permit_issue_date, ");
		stringbuffer.append("EXPIRATIONDATE as permit_exp_date, ");
		stringbuffer.append("NOTE as note, ");
		stringbuffer.append("DEPID as dept_id ");
		stringbuffer.append("from SPRAYBOOTHPERMITINFO ");
		stringbuffer.append("where SPRAYBOOTHID = ? ");
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
		stringbuffer.append("insert into SPRAYBOOTHPERMITINFO ");
		stringbuffer.append("(SPRAYBOOTHPERMITID, SPRAYBOOTHID, PERMITNUMBER,");
		stringbuffer.append("ISSUEDDATE, EXPIRATIONDATE, NOTE, DEPID) ");
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
			System.out.println("While Adding Spray Booth Permit Info:"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Spray Booth Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deletePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from SPRAYBOOTHPERMITINFO");
		stringbuffer.append(" where SPRAYBOOTHPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Spray Booth Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updatePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update SPRAYBOOTHPERMITINFO ");
		stringbuffer
				.append("set PERMITNUMBER=?, ISSUEDDATE=?, EXPIRATIONDATE=?, NOTE=?, ");
		stringbuffer.append("DEPID=? ");
		stringbuffer.append(" where SPRAYBOOTHPERMITID=?");
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
			sqlutil.addInParam(new Integer(permitinfovo.getDepId()));
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While updating Spray Booth Permit Info:"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					"While updating Spray Booth Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static int addChemical(int i, List list) throws TrackingException {
		int j = -99;
		for (int k = 0; k < list.size(); k++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into SPRAYBOOTHTOCHEMICALS ");
			stringbuffer.append("(SPRAYBOOTHID, CHEMICALID)");
			stringbuffer.append(" values (");
			stringbuffer.append(i).append(",");
			stringbuffer.append(list.get(k)).append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				j = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Adding Spray Booth Chemical Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		return j;
	}

	public static void updateChemical(int i, List list)
			throws TrackingException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("Delete from SPRAYBOOTHTOCHEMICALS ");
		stringbuffer.append(" where SPRAYBOOTHID=?");
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
		queryBuff.append(" select CHEMICALID from SPRAYBOOTHTOCHEMICALS ");
		queryBuff.append(" where SPRAYBOOTHID =").append(id);
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
				SprayBoothChemicalsEnum tempEnumObj = SprayBoothChemicalsEnum
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
		String s = "select stk.SPRAYBOOTHID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, bldg.BUILDINGNAME from spraybooth stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PaintListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Spray Booth List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.PaintSprayEntity.class);

}