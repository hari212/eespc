package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.PrintingInkConsumptionVo;
import com.eespc.tracking.bo.PrintingPressVo;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class PrintingPressEntity {

	public static class PrintingInkConsumptionEntity {

		public static PrintingInkConsumptionVo findByPrimaryKey(int id)
				throws TrackingException {
			String s = "select * from printinginkconsumption where PRTINKONECONSID=?";
			PrintingInkConsumptionVo vo = null;
			try {
				SqlUtil util = new SqlUtil();
				util.addInParam(new Integer(id));
				List list = util.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.PrintingInkConsumptionVo.class);
				if (list != null && list.size() > 0)
					vo = (PrintingInkConsumptionVo) list.get(0);
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"findByPrimaryKey(" + id + ")");
				ex.initCause(e);
				throw ex;
			}
			return vo;
		}

		public static List getList(int pressId) throws TrackingException {
			List inkList = new ArrayList();
			StringBuffer queryBuff = new StringBuffer();
			queryBuff
					.append("select * from printinginkconsumption where pressid =?");
			try {
				SqlUtil util = new SqlUtil();
				util.addInParam(new Integer(pressId));
				inkList = util.execQueryUsingConstructor(queryBuff.toString(),
						com.eespc.tracking.bo.PrintingInkConsumptionVo.class);
			} catch (Exception e) {
				TrackingException ex = new TrackingException("getList("
						+ pressId + ")");
				ex.initCause(e);
				throw ex;
			}
			return inkList;
		}

		public static int add(PrintingInkConsumptionVo prtConsumptionVo)
				throws TrackingException {
			int generatedKey = -99;
			StringBuffer buff = new StringBuffer();
			buff.append("insert into printinginkconsumption ");
			buff.append("(PRTINKONECONSID, PRESSID, YEAR, ");
			buff.append(" ANNUALINKCONSUMP, DAILYINKCONSUMP, MONTHLYINKCONSUMP) ");
			buff.append(" values (null,");
			buff.append(prtConsumptionVo.getPressId()).append(",");
			buff.append("'").append(prtConsumptionVo.getYear()).append("',");
			buff.append("'").append(prtConsumptionVo.getAnnualInkConsump())
					.append("',");
			buff.append("'").append(prtConsumptionVo.getDailyInkConsump())
					.append("',");
			buff.append("'").append(prtConsumptionVo.getMonthlyInkConsump())
					.append("'");
			buff.append(")");
			SqlUtil utilObj = new SqlUtil();
			try {
				generatedKey = utilObj.insert(buff.toString());
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"While Adding Ink Consumption Info");
				ex.initCause(e);
				throw ex;
			}
			return generatedKey;
		}

		public static void update(PrintingInkConsumptionVo prtConsumptionVo)
				throws TrackingException {
			StringBuffer buff = new StringBuffer();
			buff.append("update printinginkconsumption ");
			buff.append("set year=?, ANNUALINKCONSUMP=?, ");
			buff.append("    DAILYINKCONSUMP=?, MONTHLYINKCONSUMP=? ");
			buff.append(" where PRTINKONECONSID=?");
			SqlUtil utilObj = new SqlUtil();
			try {
				utilObj.addInParam(prtConsumptionVo.getYear());
				utilObj.addInParam(prtConsumptionVo.getAnnualInkConsump());
				utilObj.addInParam(prtConsumptionVo.getDailyInkConsump());
				utilObj.addInParam(prtConsumptionVo.getMonthlyInkConsump());
				utilObj.addInParam(new Integer(prtConsumptionVo.getId()));
				utilObj.execForDmlUsingQuery(buff.toString());
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"While updating Ink Consumption Info");
				ex.initCause(e);
				throw ex;
			}
		}

		public PrintingInkConsumptionEntity() {
		}
	}

	public PrintingPressEntity() {
	}

	public static PrintingPressVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, press pres where bld.buildingid = pres.buildingid and pres.pressid =?";
		PrintingPressVo printingpressvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PrintingPressVo.class);
			if (list != null && list.size() > 0)
				printingpressvo = (PrintingPressVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("PrintingPressVo findByPrimaryKey():"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return printingpressvo;
	}

	public static int add(PrintingPressVo printingpressvo)
			throws TrackingException {

		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();

		stringbuffer.append("insert into PRESS ");
		stringbuffer
				.append("(PRESSID,Buildingid,FACILITYDESIGNATEDID,MODIFIEDINPAST,DISCONNECTEDYEAR,PCOMMENTS,DECPERMIT,STACKTESTREQUIRED,ISVOC,ISPM,ISOTHER,DEPNUMBER,DEPISSUEDATE,DEPEXPIRATIONDATE,STACKEXHASTLOCATION,HEIGHT,DIAMETER,VELOCITY,FILTERUSED,EFFIVIANCY,SOLVANTCAP,ANNUALCONSUMPTION,INK1,INK2,INK3,INK4,FOUNTAINSOLUTION,CLEANINGAGENT,INK1NAME,INK2NAME,INK3NAME,INK4NAME,FOUNTAINSOLUTIONNAME,CLEANINGAGENTNAME,INK1VOLUME,INK2VOLUME,INK3VOLUME,INK4VOLUME,FOUNTAINSOLUTIONVOLUME,CLEANINGAGENTVOLUME,INK1DENSITY,INK2DENSITY,INK3DENSITY,INK4DENSITY,FOUNTAINSOLUTIONDENSITY,CLEANINGAGENTDENSITY,INK1VOCPERCENT,INK2VOCPERCENT,INK3VOCPERCENT,INK4VOCPERCENT,FOUNTAINSOLUTIONVOCPERCENT,CLEANINGAGENTVOCPERCENT,INK1VOC,INK2VOC,INK3VOC,INK4VOC,FOUNTAINSOLUTIONVOC,CLEANINGAGENTVOC,DOBPERMIT,TOTALVOC,DOBSIGNOFF)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("'").append(printingpressvo.getBuildingId())
				.append("',");

		stringbuffer.append("'")
				.append(printingpressvo.getFacilitydesinatedId()).append("',");

		stringbuffer.append("'").append(printingpressvo.getModifiedInPast())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getPcomments())
				.append("',");

		stringbuffer.append("'").append(printingpressvo.getDecpermit())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getStacktestrequired())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getIsvoc())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getIspm()).append("',");

		stringbuffer.append("'").append(printingpressvo.getIsother())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getDepnumber())
				.append("',");

		if (UtilityObject.isNotEmpty(printingpressvo.getDepissuedate()))
			stringbuffer.append("'").append(printingpressvo.getDepissuedate())
					.append("',");
		else
			stringbuffer.append("null,");
		if (UtilityObject.isNotEmpty(printingpressvo.getDepexpirationdate()))
			stringbuffer.append("'")
					.append(printingpressvo.getDepexpirationdate())
					.append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'")
				.append(printingpressvo.getStackexhastlocation()).append("',");
		stringbuffer.append("'").append(printingpressvo.getHeight())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getDiameter())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getVelocity())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getFilterused())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getEffiviancy())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getSolvantcap())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getAnnualconsumption())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk1()).append("',");
		stringbuffer.append("'").append(printingpressvo.getInk2()).append("',");
		stringbuffer.append("'").append(printingpressvo.getInk3()).append("',");
		stringbuffer.append("'").append(printingpressvo.getInk4()).append("',");
		stringbuffer.append("'").append(printingpressvo.getFountainSolution())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getCleaningAgent())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk1name())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk2name())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk3name())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk4name())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getFountainSolutionname()).append("',");
		stringbuffer.append("'").append(printingpressvo.getCleaningAgentname())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk1volume())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk2volume())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk3volume())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk4volume())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getFountainSolutionvolume())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getCleaningAgentvolume()).append("',");
		stringbuffer.append("'").append(printingpressvo.getInk1density())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk2density())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk3density())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk4density())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getFountainSolutiondensity())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getCleaningAgentdensity()).append("',");
		stringbuffer.append("'").append(printingpressvo.getInk1vocpercent())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk2vocpercent())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk3vocpercent())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk4vocpercent())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getFountainSolutionvocpercent())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getCleaningAgentvocpercent())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk1voc())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk2voc())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk3voc())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getInk4voc())
				.append("',");
		stringbuffer.append("'")
				.append(printingpressvo.getFountainSolutionvoc()).append("',");
		stringbuffer.append("'").append(printingpressvo.getCleaningAgentvoc())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getDobpermit())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getTotalvoc())
				.append("',");
		stringbuffer.append("'").append(printingpressvo.getDobsignoff())
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding PrintingPress:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding PrintingPress.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(PrintingPressVo printingpressvo)
			throws TrackingException {

		// if(log.isDebugEnabled())
		// log.debug((new
		// StringBuilder()).append("To update,").append(printingpressvo).toString());

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update PRESS set ");
		stringbuffer.append("BUILDINGID=?,");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("MODIFIEDINPAST=?,");
		stringbuffer.append("DISCONNECTEDYEAR=?,");
		stringbuffer.append("PCOMMENTS=?,");
		stringbuffer.append("DECPERMIT=?,");
		stringbuffer.append("STACKTESTREQUIRED=?,");
		stringbuffer.append("ISVOC=?,");
		stringbuffer.append("ISPM=?,");
		stringbuffer.append("ISOTHER=?,");
		stringbuffer.append("DEPNUMBER=?,");
		stringbuffer.append("DEPISSUEDATE=?,");
		stringbuffer.append("DEPEXPIRATIONDATE=?,");
		stringbuffer.append("STACKEXHASTLOCATION=?,");
		stringbuffer.append("HEIGHT=?,");
		stringbuffer.append("DIAMETER=?,");
		stringbuffer.append("VELOCITY=?,");
		stringbuffer.append("FILTERUSED=?,");
		stringbuffer.append("EFFIVIANCY=?,");
		stringbuffer.append("SOLVANTCAP=?,");
		stringbuffer.append("ANNUALCONSUMPTION=?,");
		stringbuffer.append("INK1=?,");
		stringbuffer.append("INK2=?,");
		stringbuffer.append("INK3=?,");
		stringbuffer.append("INK4=?,");
		stringbuffer.append("FOUNTAINSOLUTION=?,");
		stringbuffer.append("CLEANINGAGENT=?,");
		stringbuffer.append("INK1NAME=?,");
		stringbuffer.append("INK2NAME=?,");
		stringbuffer.append("INK3NAME=?,");
		stringbuffer.append("INK4NAME=?,");
		stringbuffer.append("FOUNTAINSOLUTIONNAME=?,");
		stringbuffer.append("CLEANINGAGENTNAME=?,");
		stringbuffer.append("INK1VOLUME=?,");
		stringbuffer.append("INK2VOLUME=?,");
		stringbuffer.append("INK3VOLUME=?,");
		stringbuffer.append("INK4VOLUME=?,");
		stringbuffer.append("FOUNTAINSOLUTIONVOLUME=?,");
		stringbuffer.append("CLEANINGAGENTVOLUME=?,");
		stringbuffer.append("INK1DENSITY=?,");
		stringbuffer.append("INK2DENSITY=?,");
		stringbuffer.append("INK3DENSITY=?,");
		stringbuffer.append("INK4DENSITY=?,");
		stringbuffer.append("FOUNTAINSOLUTIONDENSITY=?,");
		stringbuffer.append("CLEANINGAGENTDENSITY=?,");
		stringbuffer.append("INK1VOCPERCENT=?,");
		stringbuffer.append("INK2VOCPERCENT=?,");
		stringbuffer.append("INK3VOCPERCENT=?,");
		stringbuffer.append("INK4VOCPERCENT=?,");
		stringbuffer.append("FOUNTAINSOLUTIONVOCPERCENT=?,");
		stringbuffer.append("CLEANINGAGENTVOCPERCENT=?,");
		stringbuffer.append("INK1VOC=?,");
		stringbuffer.append("INK2VOC=?,");
		stringbuffer.append("INK3VOC=?,");
		stringbuffer.append("INK4VOC=?,");
		stringbuffer.append("FOUNTAINSOLUTIONVOC=?,");
		stringbuffer.append("CLEANINGAGENTVOC=?,");
		stringbuffer.append("DOBPERMIT=?,");
		stringbuffer.append("TOTALVOC=?,");
		stringbuffer.append("DOBSIGNOFF=?");
		stringbuffer.append("where PRESSID=?");
		SqlUtil sqlutil = new SqlUtil();

		sqlutil.addInParam(printingpressvo.getBuildingId());
		sqlutil.addInParam(printingpressvo.getFacilitydesinatedId());
		sqlutil.addInParam(printingpressvo.getModifiedInPast());
		sqlutil.addInParam(printingpressvo.getDisconnectedYear());
		sqlutil.addInParam(printingpressvo.getPcomments());
		sqlutil.addInParam(printingpressvo.getDecpermit());

		sqlutil.addInParam(printingpressvo.getStacktestrequired());
		sqlutil.addInParam(printingpressvo.getIsvoc());
		sqlutil.addInParam(printingpressvo.getIspm());
		sqlutil.addInParam(printingpressvo.getIsother());
		sqlutil.addInParam(printingpressvo.getDepnumber());

		if (UtilityObject.isNotEmpty(printingpressvo.getDepissuedate()))
			sqlutil.addInParam(printingpressvo.getDepissuedate());
		else
			sqlutil.addInParam(null);

		if (UtilityObject.isNotEmpty(printingpressvo.getDepexpirationdate()))
			sqlutil.addInParam(printingpressvo.getDepexpirationdate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(printingpressvo.getStackexhastlocation());

		sqlutil.addInParam(printingpressvo.getHeight());
		sqlutil.addInParam(printingpressvo.getDiameter());
		sqlutil.addInParam(printingpressvo.getVelocity());
		sqlutil.addInParam(printingpressvo.getFilterused());
		sqlutil.addInParam(printingpressvo.getEffiviancy());
		sqlutil.addInParam(printingpressvo.getSolvantcap());
		sqlutil.addInParam(printingpressvo.getAnnualconsumption());
		sqlutil.addInParam(printingpressvo.getInk1());
		sqlutil.addInParam(printingpressvo.getInk2());
		sqlutil.addInParam(printingpressvo.getInk3());
		sqlutil.addInParam(printingpressvo.getInk4());
		sqlutil.addInParam(printingpressvo.getFountainSolution());
		sqlutil.addInParam(printingpressvo.getCleaningAgent());
		sqlutil.addInParam(printingpressvo.getInk1name());
		sqlutil.addInParam(printingpressvo.getInk2name());
		sqlutil.addInParam(printingpressvo.getInk3name());
		sqlutil.addInParam(printingpressvo.getInk4name());
		sqlutil.addInParam(printingpressvo.getFountainSolutionname());
		sqlutil.addInParam(printingpressvo.getCleaningAgentname());
		sqlutil.addInParam(printingpressvo.getInk1volume());
		sqlutil.addInParam(printingpressvo.getInk2volume());
		sqlutil.addInParam(printingpressvo.getInk3volume());
		sqlutil.addInParam(printingpressvo.getInk4volume());
		sqlutil.addInParam(printingpressvo.getFountainSolutionvolume());
		sqlutil.addInParam(printingpressvo.getCleaningAgentvolume());
		sqlutil.addInParam(printingpressvo.getInk1density());
		sqlutil.addInParam(printingpressvo.getInk2density());
		sqlutil.addInParam(printingpressvo.getInk3density());
		sqlutil.addInParam(printingpressvo.getInk4density());
		sqlutil.addInParam(printingpressvo.getFountainSolutiondensity());
		sqlutil.addInParam(printingpressvo.getCleaningAgentdensity());
		sqlutil.addInParam(printingpressvo.getInk1vocpercent());
		sqlutil.addInParam(printingpressvo.getInk2vocpercent());
		sqlutil.addInParam(printingpressvo.getInk3vocpercent());
		sqlutil.addInParam(printingpressvo.getInk4vocpercent());
		sqlutil.addInParam(printingpressvo.getFountainSolutionvocpercent());
		sqlutil.addInParam(printingpressvo.getCleaningAgentvocpercent());
		sqlutil.addInParam(printingpressvo.getInk1voc());
		sqlutil.addInParam(printingpressvo.getInk2voc());
		sqlutil.addInParam(printingpressvo.getInk3voc());
		sqlutil.addInParam(printingpressvo.getInk4voc());
		sqlutil.addInParam(printingpressvo.getFountainSolutionvoc());
		sqlutil.addInParam(printingpressvo.getCleaningAgentvoc());
		sqlutil.addInParam(printingpressvo.getDobpermit());
		sqlutil.addInParam(printingpressvo.getTotalvoc());
		sqlutil.addInParam(printingpressvo.getDobsignoff());

		sqlutil.addInParam(new Integer(printingpressvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating PRESS:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating PRESS.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(PrintingPressVo bo) throws TrackingException {

		String s[] = { "delete from printinginkconsumption where PRESSID=?",
				"delete from PRESS where PRESSID=?" };
		for (int i = 0; i <= 1; i++) {
			StringBuffer updt = new StringBuffer();
			updt.append(s[i]);

			SqlUtil sqlutil = new SqlUtil();
			// utilObj.addInParam(boilerVo.getDep());

			sqlutil.addInParam(new Integer(bo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(updt.toString());
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"While Deleting Press");
				ex.initCause(e);
				throw ex;
			}
		}

	}

	public static PrintingInkConsumptionVo findInkConsumption(int id)
			throws TrackingException {
		return PrintingInkConsumptionEntity.findByPrimaryKey(id);
	}

	public static List geton_InkConsumptionyearList(int pressId, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year);
	}

	public static List getInkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode());
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode());
	}

	public static List getto_InkConsumptionyearList(int pressId, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.geto_FuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.geto_FuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year);
	}

	public static List getto_InkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.geto_FuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode());
			// System.out.println("BEO"+xx.size());
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.geto_FuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode());
	}

	public static List getth_InkConsumptionyearList(int pressId, String year,
			int bctype) throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year, 3);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year, 3);
	}

	public static List getth_InkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode(), 3);
			// System.out.println("BEO"+xx.size());
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.getFuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode(), 3);
	}

	public static List getfr_InkConsumptionyearList(int pressId, String year,
			int bctype) throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year, 4);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year, 4);
	}

	public static List getfr_InkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode(), 4);
			// System.out.println("BEO"+xx.size());
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.getFuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode(), 4);
	}

	public static List getfi_InkConsumptionyearList(int pressId, String year,
			int bctype) throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year, 5);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year, 5);
	}

	public static List getfi_InkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode(), 5);
			// System.out.println("BEO"+xx.size());
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.getFuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode(), 5);
	}

	public static List getsi_InkConsumptionyearList(int pressId, String year,
			int bctype) throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year, 6);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year, 6);
	}

	public static List getsi_InkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode(), 6);
			// System.out.println("BEO"+xx.size());
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.getFuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode(), 6);
	}

	public static List getse_InkConsumptionyearList(int pressId, String year,
			int bctype) throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(pressId,
					ResourcesTypeEnum.PRESS.getCode(), year, 7);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(pressId,
				ResourcesTypeEnum.PRESS.getCode(), year, 7);
	}

	public static List getse_InkConsumptionList(int pressId)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(pressId,
					ResourcesTypeEnum.PRESS.getCode(), 7);
			// System.out.println("BEO"+xx.size());
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.getFuelConsumption(pressId,
				ResourcesTypeEnum.PRESS.getCode(), 7);
	}

	public static int addInkConsumption(FuelConsumptionVo vo)
			throws TrackingException {
		return FuelConsumptionEntity.add(vo, ResourcesTypeEnum.PRESS.getCode());
	}

	public static void updateInkConsumption(FuelConsumptionVo vo)
			throws TrackingException {
		FuelConsumptionEntity.update(vo, ResourcesTypeEnum.PRESS.getCode());
	}

	public static void deleteinkconsumption(int id) throws TrackingException {
		StringBuffer buff = new StringBuffer();
		buff.append("delete from printinginkconsumption");
		buff.append(" where PRTINKONECONSID=?");
		SqlUtil utilObj = new SqlUtil();
		try {
			utilObj.addInParam(id);
			utilObj.execForDmlUsingQuery(buff.toString());
		} catch (Exception e) {
			TrackingException ex = new TrackingException(
					"While updating annualconsump Info");
			ex.initCause(e);
			throw ex;
		}

	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String qu = "select stk.PRESSID, stk.FACILITYDESIGNATEDID,bldg.BUILDINGNAME from PRESS stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(qu,
					com.eespc.tracking.bo.PressListVo.class);
		} catch (Exception exception) {
			System.out.println("getFacilityStackList:" + exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Printing Press List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log;

	static {
		Log log = LogFactory
				.getLog(com.eespc.tracking.entity.PrintingPressEntity.class);
	}

}