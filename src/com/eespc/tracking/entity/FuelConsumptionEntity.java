package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;

public class FuelConsumptionEntity {

	public FuelConsumptionEntity() {
	}

	public static int add(FuelConsumptionVo fuelconsumptionvo, int i)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(fuelconsumptionvo).toString());
		int j = -99;
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == i) {
			stringbuffer.append("insert into BOILERFUELCONSUMPTION ");
			stringbuffer.append("(BLRANNFUELCONSID,");
			stringbuffer.append("BOILERID,");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == i) {
			stringbuffer.append("insert into GENERATORFUELCONSUMPTION ");
			stringbuffer.append("(GNRTANNFUELCONSID,");
			stringbuffer.append("GENERATORID,");
		} else if (ResourcesTypeEnum.PRESS.getCode() == i) {
			stringbuffer.append("insert into PRINTINGINKCONSUMPTION ");
			stringbuffer.append("(PRTINKONECONSID,");
			stringbuffer.append("PRESSID,");

		}

		else if (ResourcesTypeEnum.COGEN.getCode() == i
				|| ResourcesTypeEnum.TURBINES.getCode() == i) {
			stringbuffer.append("insert into COGENTURBINEFUELCONSUMPTION ");
			stringbuffer.append("(CTFUELCONSUMPTIONID,");
			stringbuffer.append("COGENTURBINEID,");
		}

		else if (ResourcesTypeEnum.INCINERATOR.getCode() == i
				|| ResourcesTypeEnum.CREMATORIES.getCode() == i) {
			stringbuffer.append("insert into INCINCREMANNUALWASTECONSUM ");
			stringbuffer.append("(WASTECONSUMPTIONID,");
			stringbuffer.append("INCINERATORCREMATORIESID,");
		}

		else if (123 == i) {
			stringbuffer.append("insert into FACILITYFUELCONSUMPTION ");
			stringbuffer.append("(FACANNFUELCONSID,");
			stringbuffer.append("FACILITYID,");
		}

		else {
			throw new TrackingException("Not a valid type");
		}
		stringbuffer.append("YEAR,");
		stringbuffer.append("bLOCK,");
		stringbuffer.append("bJAN,");
		stringbuffer.append("bFEB,");
		stringbuffer.append("bMAR,");
		stringbuffer.append("bAPR,");
		stringbuffer.append("bMAY,");
		stringbuffer.append("bJUN,");
		stringbuffer.append("bJUL,");
		stringbuffer.append("bAUG,");
		stringbuffer.append("bSEP,");
		stringbuffer.append("bOCT,");
		stringbuffer.append("bNOV,");
		stringbuffer.append("bDEC,");
		stringbuffer.append("YEARTODATE,");
		stringbuffer.append("ROLLINGCONSUMPTION,");
		stringbuffer.append("COMPLIANT, bIndex,bctype)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(fuelconsumptionvo.getEntityId()).append(",");
		stringbuffer.append("'").append(fuelconsumptionvo.getYear())
				.append("',");
		if (fuelconsumptionvo.isLocked())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		stringbuffer.append(fuelconsumptionvo.getJan()).append(",");
		stringbuffer.append(fuelconsumptionvo.getFeb()).append(",");
		stringbuffer.append(fuelconsumptionvo.getMar()).append(",");
		stringbuffer.append(fuelconsumptionvo.getApr()).append(",");
		stringbuffer.append(fuelconsumptionvo.getMay()).append(",");
		stringbuffer.append(fuelconsumptionvo.getJun()).append(",");
		stringbuffer.append(fuelconsumptionvo.getJul()).append(",");
		stringbuffer.append(fuelconsumptionvo.getAug()).append(",");
		stringbuffer.append(fuelconsumptionvo.getSep()).append(",");
		stringbuffer.append(fuelconsumptionvo.getOct()).append(",");
		stringbuffer.append(fuelconsumptionvo.getNov()).append(",");
		stringbuffer.append(fuelconsumptionvo.getDec()).append(",");
		stringbuffer.append(fuelconsumptionvo.getYearToDate()).append(",");
		stringbuffer.append(fuelconsumptionvo.getRollingConsumption()).append(
				",");
		if (fuelconsumptionvo.isCompliant())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		stringbuffer.append(fuelconsumptionvo.getIndex()).append(",'");
		stringbuffer.append(fuelconsumptionvo.getBctype()).append("'");
		stringbuffer.append(")");
		System.out.println("Query:" + stringbuffer.toString());
		SqlUtil sqlutil = new SqlUtil();
		try {
			j = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {

			System.out.println("While Adding FuelConsumption:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding FuelConsumption");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return j;
	}

	public static void update(FuelConsumptionVo fuelconsumptionvo, int i)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(fuelconsumptionvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == i) {
			stringbuffer.append("update BOILERFUELCONSUMPTION set ");
			commonForUpdate(stringbuffer);
			stringbuffer.append("where BLRANNFUELCONSID=?");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == i) {
			stringbuffer.append("update GENERATORFUELCONSUMPTION set ");
			commonForUpdate(stringbuffer);
			stringbuffer.append("where GNRTANNFUELCONSID=?");
		} else if (ResourcesTypeEnum.COGEN.getCode() == i
				|| ResourcesTypeEnum.TURBINES.getCode() == i) {
			stringbuffer.append("update COGENTURBINEFUELCONSUMPTION set ");
			commonForUpdate(stringbuffer);
			stringbuffer.append("where CTFUELCONSUMPTIONID=?");
		} else if (ResourcesTypeEnum.INCINERATOR.getCode() == i
				|| ResourcesTypeEnum.CREMATORIES.getCode() == i) {
			stringbuffer.append("update INCINCREMANNUALWASTECONSUM set ");
			commonForUpdate(stringbuffer);
			stringbuffer.append(" where WASTECONSUMPTIONID=?");

		}

		else if (123 == i) {
			stringbuffer.append("update FACILITYFUELCONSUMPTION set ");
			commonForUpdate(stringbuffer);
			stringbuffer.append(" where FACANNFUELCONSID=?");

		} else if (ResourcesTypeEnum.PRESS.getCode() == i) {
			stringbuffer.append("update PRINTINGINKCONSUMPTION set ");
			commonForUpdate(stringbuffer);
			stringbuffer.append("where PRTINKONECONSID=?");
		}

		else {
			throw new TrackingException("Not a valid type");
		}
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(fuelconsumptionvo.getYear());
		if (fuelconsumptionvo.isLocked())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(new Float(fuelconsumptionvo.getJan()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getFeb()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getMar()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getApr()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getMay()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getJun()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getJul()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getAug()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getSep()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getOct()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getNov()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getDec()));
		sqlutil.addInParam(new Float(fuelconsumptionvo.getYearToDate()));

		// System.out.println("sUSENUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU@"+fuelconsumptionvo.getYearToDate());
		sqlutil.addInParam(new Float(fuelconsumptionvo.getRollingConsumption()));
		if (fuelconsumptionvo.isCompliant())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(new Integer(fuelconsumptionvo.getIndex()));
		sqlutil.addInParam(new Integer(fuelconsumptionvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Updating Fuel Consumption.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	private static void commonForUpdate(StringBuffer stringbuffer) {
		stringbuffer.append("YEAR=?,");
		stringbuffer.append("bLOCK=?,");
		stringbuffer.append("bJAN=?,");
		stringbuffer.append("bFEB=?,");
		stringbuffer.append("bMAR=?,");
		stringbuffer.append("bAPR=?,");
		stringbuffer.append("bMAY=?,");
		stringbuffer.append("bJUN=?,");
		stringbuffer.append("bJUL=?,");
		stringbuffer.append("bAUG=?,");
		stringbuffer.append("bSEP=?,");
		stringbuffer.append("bOCT=?,");
		stringbuffer.append("bNOV=?,");
		stringbuffer.append("bDEC=?,");
		stringbuffer.append("YEARTODATE=?,");
		stringbuffer.append("ROLLINGCONSUMPTION=?,");
		stringbuffer.append("COMPLIANT=?, ");
		stringbuffer.append("bIndex=? ");
	}

	public static List getFuelConsumptionyear(int i, int j, String year)
			throws TrackingException {
		System.out.println("In Year Consumption i=" + i + " j=" + j + " Year="
				+ year);
		log.debug((new StringBuilder()).append("id=").append(i)
				.append(",type=").append(j).append(",")
				.append(ResourcesTypeEnum.BOILER.getCode())
				.append(" isEqual ?")
				.append(ResourcesTypeEnum.BOILER.getCode() == j).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer.append("where BOILERID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer
					.append("where GENERATORID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer
					.append("where COGENTURBINEID = ? and bctype=? and year=?");
		}

		else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer
					.append("where INCINERATORCREMATORIESID = ? and bctype=? and year=?");
		}

		else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer.append("where FACILITYID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer.append("where PRESSID = ? and bctype=? and year=?");
		} else {
			throw new TrackingException("Not a valid type");
		}

		try {
			System.out.println("Query:" + stringbuffer.toString());
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			sqlutil.addInParam("1");
			sqlutil.addInParam(year);
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		List ss = (List) (obj);

		System.out.println("Year List length:" + ss.size());
		return ((List) (obj));
	}

	public static List geto_FuelConsumptionyear(int i, int j, String year)
			throws TrackingException {
		System.out.println("In Year Consumption i=" + i + " j=" + j + " Year="
				+ year);
		log.debug((new StringBuilder()).append("id=").append(i)
				.append(",type=").append(j).append(",")
				.append(ResourcesTypeEnum.BOILER.getCode())
				.append(" isEqual ?")
				.append(ResourcesTypeEnum.BOILER.getCode() == j).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer.append("where BOILERID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer
					.append("where GENERATORID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer
					.append("where COGENTURBINEID = ? and bctype=? and year=?");
		}

		else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer
					.append("where INCINERATORCREMATORIESID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer.append("where PRESSID = ? and bctype=? and year=?");
		}

		else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer.append("where FACILITYID = ? and bctype=? and year=?");
		}

		else {
			throw new TrackingException("Not a valid type");
		}

		try {
			System.out.println("Query:" + stringbuffer.toString());
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			sqlutil.addInParam("2");
			sqlutil.addInParam(year);
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		List ss = (List) (obj);

		System.out.println("Year List length:" + ss.size());
		return ((List) (obj));
	}

	// --------------------INK
	public static List getFuelConsumption(int i, int j, int bctype)
			throws TrackingException {
		log.debug((new StringBuilder()).append("id=").append(i)
				.append(",type=").append(j).append(",")
				.append(ResourcesTypeEnum.BOILER.getCode())
				.append(" isEqual ?")
				.append(ResourcesTypeEnum.BOILER.getCode() == j).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer.append("where BOILERID = ? and bctype=?");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer.append("where GENERATORID = ? and bctype=?");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer.append("where COGENTURBINEID = ? and bctype=?");
		}

		else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer
					.append("where INCINERATORCREMATORIESID = ? and bctype=?");
		}

		else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer.append("where FACILITYID = ? and bctype=?");
		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer.append("where PRESSID = ? and bctype=?");
		} else {
			throw new TrackingException("Not a valid type");
		}
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			sqlutil.addInParam(bctype);
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		List ss = (List) (obj);
		System.out.println("G List length:" + ss.size());
		return ((List) (obj));
	}

	public static List getFuelConsumptionyear(int i, int j, String year,
			int bctype) throws TrackingException {
		System.out.println("In Year Consumption i=" + i + " j=" + j + " Year="
				+ year);
		log.debug((new StringBuilder()).append("id=").append(i)
				.append(",type=").append(j).append(",")
				.append(ResourcesTypeEnum.BOILER.getCode())
				.append(" isEqual ?")
				.append(ResourcesTypeEnum.BOILER.getCode() == j).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer.append("where BOILERID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer
					.append("where GENERATORID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer
					.append("where COGENTURBINEID = ? and bctype=? and year=?");
		}

		else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer
					.append("where INCINERATORCREMATORIESID = ? and bctype=? and year=?");
		}

		else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer.append("where FACILITYID = ? and bctype=? and year=?");
		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer.append("where PRESSID = ? and bctype=? and year=?");
		} else {
			throw new TrackingException("Not a valid type");
		}

		try {
			System.out.println("Query:" + stringbuffer.toString());
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			sqlutil.addInParam(bctype);
			sqlutil.addInParam(year);
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		List ss = (List) (obj);

		System.out.println("Year List length:" + ss.size());
		return ((List) (obj));
	}

	// INK End

	public static List getFuelConsumption(int i, int j)
			throws TrackingException {
		log.debug((new StringBuilder()).append("id=").append(i)
				.append(",type=").append(j).append(",")
				.append(ResourcesTypeEnum.BOILER.getCode())
				.append(" isEqual ?")
				.append(ResourcesTypeEnum.BOILER.getCode() == j).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer
					.append("where BOILERID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer
					.append("where GENERATORID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer
					.append("where COGENTURBINEID = ? and bctype=? ORDER BY year ASC");
		}

		else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer
					.append("where INCINERATORCREMATORIESID = ? and bctype=? ORDER BY year ASC");
		}

		else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer
					.append("where FACILITYID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer
					.append("where PRESSID = ? and bctype=? ORDER BY year ASC");
		} else {
			throw new TrackingException("Not a valid type");
		}
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			sqlutil.addInParam("1");
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		List ss = (List) (obj);
		System.out.println("G List length:" + ss.size());
		return ((List) (obj));
	}

	public static List geto_FuelConsumption(int i, int j)
			throws TrackingException {

		log.debug((new StringBuilder()).append("id=").append(i)
				.append(",type=").append(j).append(",")
				.append(ResourcesTypeEnum.BOILER.getCode())
				.append(" isEqual ?")
				.append(ResourcesTypeEnum.BOILER.getCode() == j).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer
					.append("where BOILERID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer
					.append("where GENERATORID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer
					.append("where COGENTURBINEID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer
					.append("where INCINERATORCREMATORIESID = ? and bctype=? ORDER BY year ASC");
		} else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer
					.append("where FACILITYID = ? and bctype=? ORDER BY year ASC");
		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonGetFuelConsumption(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer
					.append("where PRESSID = ? and bctype=? ORDER BY year ASC");
		} else {
			throw new TrackingException("Not a valid type");
		}
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			sqlutil.addInParam("2");
			System.out.println(stringbuffer.toString() + "@" + new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		List ss = (List) (obj);
		System.out.println("O List length:" + ss.size());
		return ((List) (obj));
	}

	private static void commonGetFuelConsumption(StringBuffer stringbuffer) {
		stringbuffer.append("YEAR, ");
		stringbuffer.append("bLOCK as 'LOCK', ");
		stringbuffer.append("bJAN as JAN, ");
		stringbuffer.append("bFEB as FEB, ");
		stringbuffer.append("bMAR as MAR, ");
		stringbuffer.append("bAPR as APR, ");
		stringbuffer.append("bMAY as MAY, ");
		stringbuffer.append("bJUN as JUN, ");
		stringbuffer.append("bJUL as JUL, ");
		stringbuffer.append("bAUG as AUG, ");
		stringbuffer.append("bSEP as SEP, ");
		stringbuffer.append("bOCT as OCT, ");
		stringbuffer.append("bNOV as NOV, ");
		stringbuffer.append("bDEC as 'DEC', ");
		stringbuffer.append("YEARTODATE , ");
		stringbuffer.append("ROLLINGCONSUMPTION, ");
		stringbuffer.append("COMPLIANT,  ");
		stringbuffer.append("bIndex as 'index',bctype ");
	}

	public static FuelConsumptionVo findByPrimaryKey(int i, int j)
			throws TrackingException {
		FuelConsumptionVo fuelconsumptionvo = null;
		StringBuffer stringbuffer = new StringBuffer();
		if (ResourcesTypeEnum.BOILER.getCode() == j) {
			stringbuffer.append("select BLRANNFUELCONSID as ID, ");
			stringbuffer.append("BOILERID as ENTITY_ID, ");
			commonFindByPrimaryKey(stringbuffer);
			stringbuffer.append("from BOILERFUELCONSUMPTION  ");
			stringbuffer.append("where BLRANNFUELCONSID = ? ");
		} else if (ResourcesTypeEnum.GNERATOR.getCode() == j) {
			stringbuffer.append("select GNRTANNFUELCONSID as ID, ");
			stringbuffer.append("GENERATORID as ENTITY_ID, ");
			commonFindByPrimaryKey(stringbuffer);
			stringbuffer.append("from GENERATORFUELCONSUMPTION  ");
			stringbuffer.append("where GNRTANNFUELCONSID = ? ");
		} else if (ResourcesTypeEnum.COGEN.getCode() == j
				|| ResourcesTypeEnum.TURBINES.getCode() == j) {
			stringbuffer.append("select CTFUELCONSUMPTIONID as ID, ");
			stringbuffer.append("COGENTURBINEID as ENTITY_ID, ");
			commonFindByPrimaryKey(stringbuffer);
			stringbuffer.append("from COGENTURBINEFUELCONSUMPTION  ");
			stringbuffer.append("where CTFUELCONSUMPTIONID = ? ");
		} else if (ResourcesTypeEnum.INCINERATOR.getCode() == j
				|| ResourcesTypeEnum.CREMATORIES.getCode() == j) {
			stringbuffer.append("select WASTECONSUMPTIONID as ID, ");
			stringbuffer.append("INCINERATORCREMATORIESID as ENTITY_ID, ");
			commonFindByPrimaryKey(stringbuffer);
			stringbuffer.append("from INCINCREMANNUALWASTECONSUM  ");
			stringbuffer.append("where INCINERATORCREMATORIESID = ? ");

		} else if (123 == j) {
			stringbuffer.append("select FACANNFUELCONSID as ID, ");
			stringbuffer.append("FACILITYID as ENTITY_ID, ");
			commonFindByPrimaryKey(stringbuffer);
			stringbuffer.append("from FACILITYFUELCONSUMPTION  ");
			stringbuffer.append("where FACILITYID = ? ");

		} else if (ResourcesTypeEnum.PRESS.getCode() == j) {
			stringbuffer.append("select PRTINKONECONSID as ID, ");
			stringbuffer.append("PRESSID as ENTITY_ID, ");
			commonFindByPrimaryKey(stringbuffer);
			stringbuffer.append("from PRINTINGINKCONSUMPTION  ");
			stringbuffer.append("where PRTINKONECONSID = ? ");
		}

		else {
			throw new TrackingException("Not a valid type");
		}
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(
					stringbuffer.toString(),
					com.eespc.tracking.bo.FuelConsumptionVo.class);
			if (list != null && list.size() > 0)
				fuelconsumptionvo = (FuelConsumptionVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getFuelConsumption(")
							.append(i).append(",").append(j).append(")")
							.toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return fuelconsumptionvo;
	}

	private static void commonFindByPrimaryKey(StringBuffer stringbuffer) {
		stringbuffer.append("YEAR, ");
		stringbuffer.append("bLOCK as 'LOCK', ");
		stringbuffer.append("bJAN as JAN, ");
		stringbuffer.append("bFEB as FEB, ");
		stringbuffer.append("bMAR as MAR, ");
		stringbuffer.append("bAPR as APR, ");
		stringbuffer.append("bMAY as MAY, ");
		stringbuffer.append("bJUN as JUN, ");
		stringbuffer.append("bJUL as JUL, ");
		stringbuffer.append("bAUG as AUG, ");
		stringbuffer.append("bSEP as SEP, ");
		stringbuffer.append("bOCT as OCT, ");
		stringbuffer.append("bNOV as NOV, ");
		stringbuffer.append("bDEC as 'DEC', ");
		stringbuffer.append("YEARTODATE , ");
		stringbuffer.append("ROLLINGCONSUMPTION, ");
		stringbuffer.append("COMPLIANT,  ");
		stringbuffer.append("bIndex as 'index' ");
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.FuelConsumptionEntity.class);

}