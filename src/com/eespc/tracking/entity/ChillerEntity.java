package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.ChConsumptionVo;
import com.eespc.tracking.bo.ChillerVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class ChillerEntity {

	public ChillerEntity() {
	}

	public static ChillerVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, chillerabsorber sChiller where bld.buildingid = sChiller.buildingid and sChiller.CHILLERABSORBERID =?";
		ChillerVo chillervo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ChillerVo.class);
			if (list != null && list.size() > 0)
				chillervo = (ChillerVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return chillervo;
	}

	public static int add(ChillerVo chillervo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(chillervo).toString());

		// System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ1");
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into chillerabsorber ");
		stringbuffer.append("(CHILLERABSORBERID, ");
		stringbuffer.append("BUILDINGID, ");
		stringbuffer.append("TYPE, ");
		stringbuffer.append("FACILITYDESIGNATEDID, ");
		stringbuffer.append("FLOOR, ");
		stringbuffer.append("YEARINSTALLED, ");
		stringbuffer.append("MAKE, ");
		stringbuffer.append("MODEL, ");
		stringbuffer.append("SERIAL, ");
		stringbuffer.append("CAPACITY, ");
		stringbuffer.append("BURNERTYPE, ");
		stringbuffer.append("PRIMARYFUEL, ");
		stringbuffer.append("SECONDARYFUEL, ");
		stringbuffer.append("FUELFROM, ");
		stringbuffer.append("STACKFROM, ");
		stringbuffer.append("CHILLERHASFUELFIRINGABILITY, ");
		stringbuffer.append("TITLEVPERMITAPPLICATION, ");
		stringbuffer.append("DOBAPPROVAL, ");
		stringbuffer.append("DEPAPPROVAL, ");
		stringbuffer.append("MEA, ");
		stringbuffer.append("ACTIONTAKEN, ");
		stringbuffer.append("MODIFIEDINPAST, ");
		stringbuffer.append("CAPACITYTON, ");
		stringbuffer.append("EXPIRATIONDATE, ");
		stringbuffer.append("DOBJOBNUMBER, ");
		stringbuffer.append("CHCOMMENTS, ");
		stringbuffer.append("CHFOOTNOTE, ");
		stringbuffer.append("DISCONNECTEDYEAR, ");
		stringbuffer.append("DOBSIGNOFF, ");
		stringbuffer.append("CHILLEROPERTAEDBY, ");
		stringbuffer.append("EUPAVAILABLE)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(chillervo.getBuildingId()).append(",");
		stringbuffer.append(chillervo.getChType()).append(",");
		stringbuffer.append("'").append(chillervo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(chillervo.getFloor()).append("',");
		stringbuffer.append("'").append(chillervo.getYearInstalled())
				.append("',");
		stringbuffer.append("'").append(chillervo.getMake()).append("',");
		stringbuffer.append("'").append(chillervo.getModel()).append("',");
		stringbuffer.append("'").append(chillervo.getSerialNo()).append("',");
		stringbuffer.append("'").append(chillervo.getCapacity()).append("',");
		stringbuffer.append(chillervo.getBurnerType()).append(",");
		stringbuffer.append(chillervo.getPrimFuel()).append(",");
		stringbuffer.append(chillervo.getsecFuel()).append(",");
		stringbuffer.append(chillervo.getfuelFrom()).append(",");
		stringbuffer.append(chillervo.getStackFrom()).append(",");
		if (chillervo.isChillerFuel())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (chillervo.isTitleV())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		stringbuffer.append("'" + chillervo.isDOBApproved() + "'").append(",");
		stringbuffer.append("'" + chillervo.isDEPApproved() + "'").append(",");
		stringbuffer.append("'").append(chillervo.getMEA()).append("',");
		stringbuffer.append("'").append(chillervo.getActionTaken())
				.append("','");
		stringbuffer.append(chillervo.getModifiedInPast()).append("','");
		stringbuffer.append(chillervo.getChCapacityton()).append("',");

		if (UtilityObject.isNotEmpty(chillervo.getChDEPExpirationDate()))
			stringbuffer.append("'").append(chillervo.getChDEPExpirationDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(chillervo.getDobjobnumber())
				.append("',");
		stringbuffer.append("'").append(chillervo.getChComments()).append("',");
		stringbuffer.append("'").append(chillervo.getChFootnote()).append("',");
		stringbuffer.append("'").append(chillervo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(chillervo.getDobsignoff()).append("',");
		stringbuffer.append(chillervo.getChillerOperated()).append(",");
		stringbuffer.append("'").append(chillervo.getEupAvailable())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {

			i = sqlutil.insert(stringbuffer.toString());

		} catch (Exception exception) {
			System.out.println("Error in Chiller Add:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Chiller");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(ChillerVo chillervo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(chillervo).toString());
		String as[] = {
				"delete from chillerabsorberpermitinfo where CHILLERABSORBERID = ?",
				"delete from chillerabsorberfuelconsumption where CHILLERABSORBERID = ?",
				"delete from chillerabsorber where CHILLERABSORBERID = ?" };
		for (int i = 0; i <= 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(chillervo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating Chiller.");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static void update(ChillerVo chillervo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(chillervo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("Update chillerabsorber set ");
		stringbuffer.append("BUILDINGID=?, ");
		stringbuffer.append("TYPE=?, ");
		stringbuffer.append("FACILITYDESIGNATEDID=?, ");
		stringbuffer.append("FLOOR=?, ");
		stringbuffer.append("YEARINSTALLED=?, ");
		stringbuffer.append("MAKE=?, ");
		stringbuffer.append("MODEL=?, ");
		stringbuffer.append("SERIAL=?, ");
		stringbuffer.append("CAPACITY=?, ");
		// System.out.println("Capacity:" +chillervo.getCapacity());
		stringbuffer.append("BURNERTYPE=?, ");
		stringbuffer.append("PRIMARYFUEL=?, ");
		stringbuffer.append("SECONDARYFUEL=?, ");
		stringbuffer.append("FUELFROM=?, ");
		stringbuffer.append("STACKFROM=?, ");
		stringbuffer.append("CHILLERHASFUELFIRINGABILITY=?, ");
		stringbuffer.append("TITLEVPERMITAPPLICATION=?, ");
		stringbuffer.append("DOBAPPROVAL=?, ");
		stringbuffer.append("DEPAPPROVAL=?, ");
		stringbuffer.append("MEA=?, ");
		stringbuffer.append("ACTIONTAKEN=?, ");
		stringbuffer.append("MODIFIEDINPAST=?, ");
		stringbuffer.append("CAPACITYTON=?, ");
		stringbuffer.append("EXPIRATIONDATE=?, ");
		stringbuffer.append("DOBJOBNUMBER=?, ");
		stringbuffer.append("CHCOMMENTS=?, ");
		stringbuffer.append("CHFOOTNOTE=?, ");
		stringbuffer.append("DISCONNECTEDYEAR=?, ");
		stringbuffer.append("DOBSIGNOFF=?,  ");
		stringbuffer.append("CHILLEROPERTAEDBY=?,  ");
		stringbuffer.append("EUPAVAILABLE=? ");
		stringbuffer.append(" where CHILLERABSORBERID = ? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(chillervo.getBuildingId()));
		sqlutil.addInParam(new Integer(chillervo.getChType()));
		sqlutil.addInParam(chillervo.getFacilityDesignatedId());
		sqlutil.addInParam(chillervo.getFloor());
		sqlutil.addInParam(chillervo.getYearInstalled());
		sqlutil.addInParam(chillervo.getMake());
		sqlutil.addInParam(chillervo.getModel());
		sqlutil.addInParam(chillervo.getSerialNo());
		sqlutil.addInParam(chillervo.getCapacity());
		sqlutil.addInParam(new Integer(chillervo.getBurnerType()));
		sqlutil.addInParam(new Integer(chillervo.getPrimFuel()));
		sqlutil.addInParam(new Integer(chillervo.getsecFuel()));
		sqlutil.addInParam(new Integer(chillervo.getfuelFrom()));
		sqlutil.addInParam(new Integer(chillervo.getStackFrom()));

		if (chillervo.isChillerFuel())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (chillervo.isTitleV())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");

		sqlutil.addInParam(chillervo.isDOBApproved());
		sqlutil.addInParam(chillervo.isDEPApproved());
		sqlutil.addInParam(chillervo.getMEA());
		sqlutil.addInParam(chillervo.getActionTaken());
		sqlutil.addInParam(String.valueOf(chillervo.getModifiedInPast()));
		sqlutil.addInParam(chillervo.getChCapacityton());

		if (UtilityObject.isNotEmpty(chillervo.getChDEPExpirationDate()))
			sqlutil.addInParam(chillervo.getChDEPExpirationDate());
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(chillervo.getDobjobnumber());
		sqlutil.addInParam(chillervo.getChComments());
		sqlutil.addInParam(chillervo.getChFootnote());
		sqlutil.addInParam(chillervo.getDisconnectedYear());
		sqlutil.addInParam(chillervo.getDobsignoff());
		sqlutil.addInParam(new Integer(chillervo.getChillerOperated()));
		sqlutil.addInParam(chillervo.getEupAvailable());
		sqlutil.addInParam(new Integer(chillervo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error in update chiller:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Chiller.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}

	}

	public static List getPermitInfo(int i) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("Get Permit Info - ID = ,")
					.append(i).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select CHILLERABSORBERPERMITID as permit_id, ");
		stringbuffer.append("CHILLERABSORBERID as object_id, ");
		stringbuffer
				.append("9 as object_type, PERMITNUMBER as permit_number, ");
		stringbuffer.append("ISSUEDDATE as permit_issue_date, ");
		stringbuffer.append("EXPIRATIONDATE as permit_exp_date, ");
		stringbuffer.append("DEPID as dept_id ");
		stringbuffer.append("from chillerabsorberpermitinfo ");
		stringbuffer.append("where CHILLERABSORBERID = ? ");
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
		stringbuffer.append("insert into chillerabsorberpermitinfo ");
		stringbuffer
				.append("(CHILLERABSORBERPERMITID, CHILLERABSORBERID, PERMITNUMBER,");
		stringbuffer.append("ISSUEDDATE, EXPIRATIONDATE, DEPID) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(permitinfovo.getObjectId()).append(",");
		stringbuffer.append("'").append(permitinfovo.getPermitNumber())
				.append("',");
		stringbuffer
				.append("'")
				.append(UtilityObject.convertToString(
						permitinfovo.getIssueDate(), "yyyy-MM-dd"))
				.append("',");
		stringbuffer
				.append("'")
				.append(UtilityObject.convertToString(
						permitinfovo.getExpirationDate(), "yyyy-MM-dd"))
				.append("',");
		stringbuffer.append(permitinfovo.getDepId()).append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Chiller Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deletePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from chillerabsorberpermitinfo ");
		stringbuffer.append(" where CHILLERABSORBERPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Chiller Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updatePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update chillerabsorberpermitinfo ");
		stringbuffer
				.append("set PERMITNUMBER=?, ISSUEDDATE=?, EXPIRATIONDATE=?, ");
		stringbuffer.append("DEPID=? ");
		stringbuffer.append(" where CHILLERABSORBERPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(permitinfovo.getPermitNumber());
			sqlutil.addInParam(UtilityObject.convertToString(
					permitinfovo.getIssueDate(), "yyyy-MM-dd"));
			sqlutil.addInParam(UtilityObject.convertToString(
					permitinfovo.getExpirationDate(), "yyyy-MM-dd"));
			sqlutil.addInParam(new Integer(permitinfovo.getDepId()));
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Chiller Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static int addFuelConsumption(ChConsumptionVo chconsumptionvo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into chillerabsorberfuelconsumption ");
		stringbuffer
				.append("(ABSORBERCHILLERFUELCONSID, CHILLERABSORBERID,YEAR,CONSUMPTION,CONSUMPTIONCFY)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(chconsumptionvo.getObjectId()).append(",");
		stringbuffer.append("'").append(chconsumptionvo.getYear()).append("',");
		stringbuffer.append("'").append(chconsumptionvo.getConsumption())
				.append("',");
		stringbuffer.append("'").append(chconsumptionvo.getConsumptioncfy())
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Chiller Fuel Consumption");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static List getFuelConsumption(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select ABSORBERCHILLERFUELCONSID as CONS_ID, ");
		stringbuffer.append("CHILLERABSORBERID as object_id, ");
		stringbuffer.append("YEAR as YEAR, ");
		stringbuffer
				.append("CONSUMPTION as CONSUMPTION , CONSUMPTIONCFY as CONSUMPTIONCFY ");
		stringbuffer.append("from chillerabsorberfuelconsumption ");
		stringbuffer.append("where CHILLERABSORBERID = ? ");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.ChConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getPermitInfo(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static void updateFuelConsumption(ChConsumptionVo chconsumptionvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("Update chillerabsorberfuelconsumption ");
		stringbuffer
				.append(" set CHILLERABSORBERID=?,YEAR=?,CONSUMPTION=?,CONSUMPTIONCFY=? ");
		stringbuffer.append(" where ABSORBERCHILLERFUELCONSID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(chconsumptionvo.getObjectId()));
			sqlutil.addInParam(chconsumptionvo.getYear());
			sqlutil.addInParam(chconsumptionvo.getConsumption());
			sqlutil.addInParam(chconsumptionvo.getConsumptioncfy());
			sqlutil.addInParam(new Integer(chconsumptionvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Chiller Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deleteFuelConsumption(ChConsumptionVo chconsumptionvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from chillerabsorberfuelconsumption");
		stringbuffer.append(" where ABSORBERCHILLERFUELCONSID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(chconsumptionvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Chiller Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.CHILLERABSORBERID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, stk.SERIAL, stk.CAPACITY, bldg.BUILDINGNAME from chillerabsorber stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ChillerListVo.class);
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
			.getLog(com.eespc.tracking.entity.ChillerEntity.class);

}