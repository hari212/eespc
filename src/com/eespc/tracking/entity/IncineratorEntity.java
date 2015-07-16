package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.IncConsumptionVo;
import com.eespc.tracking.bo.IncineratorVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.StackTestVo;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class IncineratorEntity {

	public IncineratorEntity() {
	}

	public static List getFuelConsumptionyearList(int boilerId, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(boilerId,
					ResourcesTypeEnum.INCINERATOR.getCode(), year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(boilerId,
				ResourcesTypeEnum.INCINERATOR.getCode(), year);
	}

	public static int addFuelConsumption(FuelConsumptionVo vo)
			throws TrackingException {
		return FuelConsumptionEntity.add(vo,
				ResourcesTypeEnum.INCINERATOR.getCode());
	}

	public static void updateFuelConsumption(FuelConsumptionVo vo)
			throws TrackingException {
		FuelConsumptionEntity.update(vo,
				ResourcesTypeEnum.INCINERATOR.getCode());
	}

	public static List getFuelConsumptionList(int incid)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(incid,
					ResourcesTypeEnum.INCINERATOR.getCode());
		} catch (Exception e) {
			System.out.println("Exception in Inc Entity" + e);
		}

		return FuelConsumptionEntity.getFuelConsumption(incid,
				ResourcesTypeEnum.INCINERATOR.getCode());
	}

	public static void deletefuelconsumption(int id) throws TrackingException {
		StringBuffer buff = new StringBuffer();
		buff.append("delete from incincremannualwasteconsum");
		buff.append(" where WASTECONSUMPTIONID=?");
		SqlUtil utilObj = new SqlUtil();
		try {
			utilObj.addInParam(id);
			utilObj.execForDmlUsingQuery(buff.toString());
		} catch (Exception e) {
			TrackingException ex = new TrackingException(
					"While updating annualtuneup Info");
			ex.initCause(e);
			throw ex;
		}

	}

	public static IncineratorVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, incineratorcrematories sIncinerator where bld.buildingid = sIncinerator.buildingid and sIncinerator.INCINERATORCREMATORIESID =?";
		IncineratorVo incineratorvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.IncineratorVo.class);
			if (list != null && list.size() > 0)
				incineratorvo = (IncineratorVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return incineratorvo;
	}

	public static int add(IncineratorVo incineratorvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(incineratorvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into incineratorcrematories ");
		stringbuffer.append("(INCINERATORCREMATORIESID, ");
		stringbuffer.append("BUILDINGID, ");
		stringbuffer.append("FACILITYDESIGNATEDID, ");
		stringbuffer.append("TYPE, ");
		stringbuffer.append("FLOOR, ");
		stringbuffer.append("MAKE, ");
		stringbuffer.append("MODEL, ");
		stringbuffer.append("SERIAL, ");
		stringbuffer.append("TYPEOFWASTEBURNED, ");
		stringbuffer.append("YEARINSTALLED, ");
		stringbuffer.append("STATUS, ");
		stringbuffer.append("DISCONNECTEDDATE, ");
		stringbuffer.append("BURNERMAKE, ");
		stringbuffer.append("BURNERMODEL, ");
		stringbuffer.append("BURNERCAPACITY, ");
		stringbuffer.append("CAPACITY, ");
		stringbuffer.append("STACKFROM, ");
		stringbuffer.append("SCRUBBERINSTALLED, ");
		stringbuffer.append("DOBAPPROVAL, ");
		stringbuffer.append("MEA, ");
		stringbuffer.append("COMONINSTALLED, ");
		stringbuffer.append("OPACITYMONINSTALLED, ");
		stringbuffer.append("OXYGENMONINSTALLED, ");
		stringbuffer.append("CHARTRECORDERAVAILABLE, ");
		stringbuffer.append("QUARTERLYCGARQD, ");
		stringbuffer
				.append("LOCATION,TYPEOFUNIT,DOBNO,TEPERATUREREQUIRED,FACILITYSECONDARY,FACILITYPRIMARY,PRIMARYTEMP,SECTEMP,DEPREQUIRED,DEPNO,DECNO,INCSTACKTESTED,I_DECPERMITOBTAINED,PROTOCOLSUBMITTED,TESTCONDUCTEDBY,TESTREPORTSUBMITED,TESTREPORTSUBMITEDDATE,RETESTPLANNED,I_TESTPASSED,I_STACKTESTDATE,NEXTSTACKTEST,NEXTSTACKTESTNOTE,B_PARAMETERS1,B_PARAMETERS2,B_PARAMETERS3,B_PARAMETERS4,B_PARAMETERS5,B_PARAMETERS6,B_PARAMETERS7,B_PARAMETERS8,B_PARAMETERS9,IWASTEQUANTITY,ISOLIDWASTE,I_SOLIDISSUEDDATE,I_SOLIDEXPIRATIONDATE,SOLIDWASTEEXPIRATIONNOTE,ICOMMENTS,IWASTELIMIT,DOBSIGNOFF)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(incineratorvo.getBuildingId()).append(",");
		stringbuffer.append("'")
				.append(incineratorvo.getFacilityDesignatedId()).append("',");
		stringbuffer.append(incineratorvo.getType()).append(",");
		stringbuffer.append("'").append(incineratorvo.getFloor()).append("',");
		stringbuffer.append("'").append(incineratorvo.getMake()).append("',");
		stringbuffer.append("'").append(incineratorvo.getModel()).append("',");
		stringbuffer.append("'").append(incineratorvo.getSerialNo())
				.append("',");
		stringbuffer.append(incineratorvo.getWasteBurned()).append(",");
		stringbuffer.append("'").append(incineratorvo.getYearInstalled())
				.append("',");
		stringbuffer.append("").append(incineratorvo.getStatus()).append(",");
		if (incineratorvo.getDisconnecteddate().equals(null))
			stringbuffer.append("").append(incineratorvo.getDisconnecteddate())
					.append(",");
		else
			stringbuffer.append("'")
					.append(incineratorvo.getDisconnecteddate()).append("',");
		stringbuffer.append("'").append(incineratorvo.getBurnerMake())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getBurnerModel())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getBurnerCapacity())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getCapacity())
				.append("',");
		stringbuffer.append(incineratorvo.getStackFrom()).append(",");
		if (incineratorvo.isScrubberInst())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (incineratorvo.isDOBApproved())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		stringbuffer.append("'").append(incineratorvo.getMEA()).append("',");

		stringbuffer.append("'" + incineratorvo.isCOMonitor() + "'")
				.append(",");
		stringbuffer.append("'" + incineratorvo.isOpacityInstalled() + "'")
				.append(",");
		stringbuffer.append("'" + incineratorvo.isO2Installed() + "'").append(
				",");

		if (incineratorvo.isOpacityChartAvailable())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (incineratorvo.isCGARequired())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		stringbuffer.append(incineratorvo.getLocation()).append(",");
		stringbuffer.append("'").append(incineratorvo.getTypeofunit())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getDobno()).append("',");
		stringbuffer.append("'").append(incineratorvo.getTeperaturerequired())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getFacilitysecondary())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getFacilityprimary())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getPrimarytemp())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getSectemp())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getDeprequired())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getDepno()).append("',");
		stringbuffer.append("'").append(incineratorvo.getDecno()).append("',");
		stringbuffer.append("'").append(incineratorvo.getIncStackTested())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getI_decPermitObtained())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getProtocolSubmitted())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getTestConductedBy())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getTestReportSubmited())
				.append("',");

		if (UtilityObject.isNotEmpty(incineratorvo.getTestReportSubmitedDate()))
			stringbuffer.append("'")
					.append(incineratorvo.getTestReportSubmitedDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(incineratorvo.getRetestPlanned())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getI_testPassed())
				.append("',");

		if (UtilityObject.isNotEmpty(incineratorvo.getI_StackTestDate()))
			stringbuffer.append("'").append(incineratorvo.getI_StackTestDate())
					.append("',");
		else
			stringbuffer.append("null,");

		if (UtilityObject.isNotEmpty(incineratorvo.getNextStackTest()))
			stringbuffer.append("'").append(incineratorvo.getNextStackTest())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(incineratorvo.getNextStackTestNote())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters1())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters2())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters3())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters4())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters5())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters6())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters7())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters8())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getB_parameters9())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getIwastequantity())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getIsolidwaste())
				.append("',");

		if (UtilityObject.isNotEmpty(incineratorvo.getI_SolidIssuedDate()))
			stringbuffer.append("'")
					.append(incineratorvo.getI_SolidIssuedDate()).append("',");
		else
			stringbuffer.append("null,");

		if (UtilityObject.isNotEmpty(incineratorvo.getI_SolidExpirationDate()))
			stringbuffer.append("'")
					.append(incineratorvo.getI_SolidExpirationDate())
					.append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'")
				.append(incineratorvo.getSolidWasteExpirationNote())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getIcomments())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getIwastelimit())
				.append("',");
		stringbuffer.append("'").append(incineratorvo.getDobsignoff())
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding Incinerator:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Incinerator");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(IncineratorVo incineratorvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(incineratorvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("Update incineratorcrematories set ");
		stringbuffer.append("BUILDINGID=?, ");
		stringbuffer.append("FACILITYDESIGNATEDID=?, ");
		stringbuffer.append("TYPE=?, ");
		stringbuffer.append("FLOOR=?, ");
		stringbuffer.append("MAKE=?, ");
		stringbuffer.append("MODEL=?, ");
		stringbuffer.append("SERIAL=?, ");
		stringbuffer.append("TYPEOFWASTEBURNED=?, ");
		stringbuffer.append("YEARINSTALLED=?, ");
		stringbuffer.append("STATUS=?, ");
		stringbuffer.append("DISCONNECTEDDATE=?, ");
		stringbuffer.append("BURNERMAKE=?, ");
		stringbuffer.append("BURNERMODEL=?, ");
		stringbuffer.append("BURNERCAPACITY=?, ");
		stringbuffer.append("CAPACITY=?, ");
		stringbuffer.append("STACKFROM=?, ");
		stringbuffer.append("SCRUBBERINSTALLED=?, ");
		stringbuffer.append("DOBAPPROVAL=?, ");
		stringbuffer.append("MEA=?, ");
		stringbuffer.append("COMONINSTALLED=?, ");
		stringbuffer.append("OPACITYMONINSTALLED=?, ");
		stringbuffer.append("OXYGENMONINSTALLED=?, ");
		stringbuffer.append("CHARTRECORDERAVAILABLE=?, ");
		stringbuffer.append("QUARTERLYCGARQD=?, ");
		stringbuffer.append("LOCATION=?,");
		stringbuffer.append("TYPEOFUNIT=?,");
		stringbuffer.append("DOBNO=?,");
		stringbuffer.append("TEPERATUREREQUIRED=?,");
		stringbuffer.append("FACILITYSECONDARY=?,");
		stringbuffer.append("FACILITYPRIMARY=?,");
		stringbuffer.append("PRIMARYTEMP=?,");
		stringbuffer.append("SECTEMP=?,");
		stringbuffer.append("DEPREQUIRED=?,");
		stringbuffer.append("DEPNO=?,");
		stringbuffer.append("DECNO=?,");
		stringbuffer.append("INCSTACKTESTED=?,");
		stringbuffer.append("I_DECPERMITOBTAINED=?,");
		stringbuffer.append("PROTOCOLSUBMITTED=?,");
		stringbuffer.append("TESTCONDUCTEDBY=?,");
		stringbuffer.append("TESTREPORTSUBMITED=?,");
		stringbuffer.append("TESTREPORTSUBMITEDDATE=?,");
		stringbuffer.append("RETESTPLANNED=?,");
		stringbuffer.append("I_TESTPASSED=?,");
		stringbuffer.append("I_STACKTESTDATE=?,");
		stringbuffer.append("NEXTSTACKTEST=?,");
		stringbuffer.append("NEXTSTACKTESTNOTE=?,");
		stringbuffer.append("B_PARAMETERS1=?,");
		stringbuffer.append("B_PARAMETERS2=?,");
		stringbuffer.append("B_PARAMETERS3=?,");
		stringbuffer.append("B_PARAMETERS4=?,");
		stringbuffer.append("B_PARAMETERS5=?,");
		stringbuffer.append("B_PARAMETERS6=?,");
		stringbuffer.append("B_PARAMETERS7=?,");
		stringbuffer.append("B_PARAMETERS8=?,");
		stringbuffer.append("B_PARAMETERS9=?,");
		stringbuffer.append("IWASTEQUANTITY=?,");
		stringbuffer.append("ISOLIDWASTE=?,");
		stringbuffer.append("I_SOLIDISSUEDDATE=?,");
		stringbuffer.append("I_SOLIDEXPIRATIONDATE=?,");
		stringbuffer.append("SOLIDWASTEEXPIRATIONNOTE=?,");
		stringbuffer.append("ICOMMENTS=?,");
		stringbuffer.append("IWASTELIMIT=?, ");
		stringbuffer.append("DOBSIGNOFF=? ");
		stringbuffer.append(" where INCINERATORCREMATORIESID=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(incineratorvo.getBuildingId()));
		sqlutil.addInParam(incineratorvo.getFacilityDesignatedId());
		sqlutil.addInParam(new Integer(incineratorvo.getType()));
		sqlutil.addInParam(incineratorvo.getFloor());
		sqlutil.addInParam(incineratorvo.getMake());
		sqlutil.addInParam(incineratorvo.getModel());
		sqlutil.addInParam(incineratorvo.getSerialNo());
		sqlutil.addInParam(new Integer(incineratorvo.getWasteBurned()));
		sqlutil.addInParam(incineratorvo.getYearInstalled());
		sqlutil.addInParam(String.valueOf(incineratorvo.getStatus()));
		sqlutil.addInParam(incineratorvo.getDisconnecteddate());
		sqlutil.addInParam(incineratorvo.getBurnerMake());
		sqlutil.addInParam(incineratorvo.getBurnerModel());
		sqlutil.addInParam(incineratorvo.getBurnerCapacity());
		sqlutil.addInParam(incineratorvo.getCapacity());
		sqlutil.addInParam(new Integer(incineratorvo.getStackFrom()));
		if (incineratorvo.isScrubberInst())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (incineratorvo.isDOBApproved())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(incineratorvo.getMEA());
		sqlutil.addInParam(incineratorvo.isCOMonitor());
		sqlutil.addInParam(incineratorvo.isOpacityInstalled());
		sqlutil.addInParam(incineratorvo.isO2Installed());
		if (incineratorvo.isOpacityChartAvailable())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (incineratorvo.isCGARequired())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(new Integer(incineratorvo.getLocation()));
		sqlutil.addInParam(incineratorvo.getTypeofunit());
		sqlutil.addInParam(incineratorvo.getDobno());
		sqlutil.addInParam(incineratorvo.getTeperaturerequired());
		sqlutil.addInParam(incineratorvo.getFacilitysecondary());
		sqlutil.addInParam(incineratorvo.getFacilityprimary());
		sqlutil.addInParam(incineratorvo.getPrimarytemp());
		sqlutil.addInParam(incineratorvo.getSectemp());
		sqlutil.addInParam(incineratorvo.getDeprequired());
		sqlutil.addInParam(incineratorvo.getDepno());
		sqlutil.addInParam(incineratorvo.getDecno());
		sqlutil.addInParam(incineratorvo.getIncStackTested());
		sqlutil.addInParam(incineratorvo.getI_decPermitObtained());
		sqlutil.addInParam(incineratorvo.getProtocolSubmitted());
		sqlutil.addInParam(incineratorvo.getTestConductedBy());
		sqlutil.addInParam(incineratorvo.getTestReportSubmited());
		if (UtilityObject.isNotEmpty(incineratorvo.getTestReportSubmitedDate())) {
			sqlutil.addInParam(incineratorvo.getTestReportSubmitedDate());
		} else
			sqlutil.addInParam(null);
		sqlutil.addInParam(incineratorvo.getRetestPlanned());
		sqlutil.addInParam(incineratorvo.getI_testPassed());
		if (UtilityObject.isNotEmpty(incineratorvo.getI_StackTestDate())) {
			sqlutil.addInParam(incineratorvo.getI_StackTestDate());
		} else
			sqlutil.addInParam(null);
		if (UtilityObject.isNotEmpty(incineratorvo.getNextStackTest())) {
			sqlutil.addInParam(incineratorvo.getNextStackTest());
		} else
			sqlutil.addInParam(null);
		sqlutil.addInParam(incineratorvo.getNextStackTestNote());
		sqlutil.addInParam(incineratorvo.getB_parameters1());
		sqlutil.addInParam(incineratorvo.getB_parameters2());
		sqlutil.addInParam(incineratorvo.getB_parameters3());
		sqlutil.addInParam(incineratorvo.getB_parameters4());
		sqlutil.addInParam(incineratorvo.getB_parameters5());
		sqlutil.addInParam(incineratorvo.getB_parameters6());
		sqlutil.addInParam(incineratorvo.getB_parameters7());
		sqlutil.addInParam(incineratorvo.getB_parameters8());
		sqlutil.addInParam(incineratorvo.getB_parameters9());
		sqlutil.addInParam(incineratorvo.getIwastequantity());
		sqlutil.addInParam(incineratorvo.getIsolidwaste());
		if (UtilityObject.isNotEmpty(incineratorvo.getI_SolidIssuedDate())) {
			sqlutil.addInParam(incineratorvo.getI_SolidIssuedDate());
		} else
			sqlutil.addInParam(null);
		if (UtilityObject.isNotEmpty(incineratorvo.getI_SolidExpirationDate())) {
			sqlutil.addInParam(incineratorvo.getI_SolidExpirationDate());
		} else
			sqlutil.addInParam(null);
		sqlutil.addInParam(incineratorvo.getSolidWasteExpirationNote());
		sqlutil.addInParam(incineratorvo.getIcomments());
		sqlutil.addInParam(incineratorvo.getIwastelimit());
		sqlutil.addInParam(incineratorvo.getDobsignoff());
		sqlutil.addInParam(new Integer(incineratorvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Incinerator:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Incenirator.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(IncineratorVo incineratorvo)
			throws TrackingException {
		String as[] = {
				"delete from incineratorcrematorpermitinfo where INCINERATORCREMATORIESID=?",
				"delete from incincremannualwasteconsum where INCINERATORCREMATORIESID=?",
				"delete from stacktestinfo where INCINERATORCREMATORIESID=?",
				"delete from incineratorcrematories where INCINERATORCREMATORIESID=?" };
		for (int i = 0; i <= 3; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(incineratorvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating Incenirator.");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static List getPermitInfo(int i) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("Get Permit Info - ID = ,")
					.append(i).toString());
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select INCCREMPERMITID as permit_id, ");
		stringbuffer.append("INCINERATORCREMATORIESID as object_id, ");
		stringbuffer
				.append("9 as object_type, PERMITNUMBER as permit_number, ");
		stringbuffer.append("ISSUEDATE as permit_issue_date, ");
		stringbuffer.append("EXPIRATIONDATE as permit_exp_date, ");
		stringbuffer.append("NOTE as note, ");
		stringbuffer.append("DEPID as dept_id ");
		stringbuffer.append("from incineratorcrematorpermitinfo ");
		stringbuffer.append("where INCINERATORCREMATORIESID = ? ");
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
		stringbuffer.append("insert into incineratorcrematorpermitinfo ");
		stringbuffer
				.append("(INCCREMPERMITID, INCINERATORCREMATORIESID, PERMITNUMBER,");
		stringbuffer.append("ISSUEDATE, EXPIRATIONDATE, NOTE, DEPID) ");
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
			TrackingException trackingexception = new TrackingException(
					"While Adding Incinerator Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deletePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from incineratorcrematorpermitinfo");
		stringbuffer.append(" where INCCREMPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Incinerator Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updatePermitInfo(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update incineratorcrematorpermitinfo ");
		stringbuffer
				.append("set PERMITNUMBER=?, ISSUEDATE=?, EXPIRATIONDATE=?, NOTE=?,");
		stringbuffer.append("DEPID=? ");
		stringbuffer.append(" where INCCREMPERMITID=?");
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
			TrackingException trackingexception = new TrackingException(
					"While updating Incinerator Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getStackTestInfo(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("select STACKTESTINFO , INCINERATORCREMATORIESID, YEAR,TESTRQDBYAGENCY,");
		stringbuffer
				.append("OTHERBLRCOMBINED, PARAMETERNOX, PARAMETERSO,PARAMETERPM,");
		stringbuffer.append("TESTONNATURALGAS,TESTONFUEL,");
		stringbuffer
				.append("PROTOCOLSUBMITTED, CONDUCTEDBY, RPTSUBMITTED,PROTOCOLSUBMITDATE,");
		stringbuffer.append("TESTPASSED, RETESTPLANNED,NEXTTESTDATE");
		stringbuffer.append(" from  stacktestinfo ");
		stringbuffer.append(" where INCINERATORCREMATORIESID = ? ");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.StackTestVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getStackTestInfo(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static int addStackTestInfo(StackTestVo stacktestvo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into stacktestinfo ");
		stringbuffer
				.append("(STACKTESTINFO, INCINERATORCREMATORIESID, YEAR,TESTRQDBYAGENCY,");
		stringbuffer
				.append("OTHERBLRCOMBINED, PARAMETERNOX, PARAMETERSO,PARAMETERPM,TESTONNATURALGAS,TESTONFUEL,");
		stringbuffer
				.append("PROTOCOLSUBMITTED, CONDUCTEDBY, RPTSUBMITTED,PROTOCOLSUBMITDATE,");
		stringbuffer.append("TESTPASSED, RETESTPLANNED,NEXTTESTDATE)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(stacktestvo.getIncineratorId()).append(",");
		stringbuffer.append("'").append(stacktestvo.getYear()).append("',");
		stringbuffer.append("'").append(stacktestvo.getTestAgency())
				.append("',");
		stringbuffer.append("'").append(stacktestvo.getBoilerTest())
				.append("',");
		stringbuffer.append("'").append(stacktestvo.getParam1()).append("',");
		stringbuffer.append("'").append(stacktestvo.getParam2()).append("',");
		stringbuffer.append("'").append(stacktestvo.getParam3()).append("',");
		stringbuffer.append("'").append(stacktestvo.getTestOnNaturalGas())
				.append("',");
		stringbuffer.append(stacktestvo.getTestFuel()).append(",");
		stringbuffer.append("'").append(stacktestvo.getProtocolSubmitted())
				.append("',");
		stringbuffer.append("'").append(stacktestvo.getConductedBy())
				.append("',");
		stringbuffer.append("'").append(stacktestvo.getReportSubmitted())
				.append("',");
		stringbuffer
				.append("'")
				.append(UtilityObject.convertToString(
						stacktestvo.getProSubmissionDate(), "yyyy-MM-dd"))
				.append("',");
		stringbuffer.append("'").append(stacktestvo.getTestPassed())
				.append("',");
		stringbuffer.append("'").append(stacktestvo.getReTest()).append("',");
		stringbuffer
				.append("'")
				.append(UtilityObject.convertToString(
						stacktestvo.getNextTestDate(), "yyyy-MM-dd"))
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Stack Test Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deleteStackTestInfo(StackTestVo stacktestvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from stacktestinfo");
		stringbuffer.append(" where STACKTESTINFO=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(stacktestvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Stack Test Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updateStackTestInfo(StackTestVo stacktestvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("Update stacktestinfo ");
		stringbuffer
				.append("set  INCINERATORCREMATORIESID=?, YEAR=?,TESTRQDBYAGENCY=?,");
		stringbuffer
				.append("OTHERBLRCOMBINED=?, PARAMETERNOX=?, PARAMETERSO=?,PARAMETERPM=?,TESTONNATURALGAS=?,TESTONFUEL=?,");
		stringbuffer
				.append("PROTOCOLSUBMITTED=?, CONDUCTEDBY=?, RPTSUBMITTED=?,PROTOCOLSUBMITDATE=?,");
		stringbuffer.append("TESTPASSED=?, RETESTPLANNED=?,NEXTTESTDATE=?");
		stringbuffer.append(" where STACKTESTINFO=?");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(stacktestvo.getIncineratorId()));
			sqlutil.addInParam(stacktestvo.getYear());
			sqlutil.addInParam(stacktestvo.getTestAgency());
			sqlutil.addInParam(stacktestvo.getBoilerTest());
			sqlutil.addInParam(stacktestvo.getParam1());
			sqlutil.addInParam(stacktestvo.getParam2());
			sqlutil.addInParam(stacktestvo.getParam3());
			sqlutil.addInParam(stacktestvo.getTestOnNaturalGas());
			sqlutil.addInParam(new Integer(stacktestvo.getTestFuel()));
			sqlutil.addInParam(stacktestvo.getProtocolSubmitted());
			sqlutil.addInParam(stacktestvo.getConductedBy());
			sqlutil.addInParam(stacktestvo.getReportSubmitted());
			sqlutil.addInParam(UtilityObject.convertToString(
					stacktestvo.getProSubmissionDate(), "yyyy-MM-dd"));
			sqlutil.addInParam(stacktestvo.getTestPassed());
			sqlutil.addInParam(stacktestvo.getReTest());
			sqlutil.addInParam(UtilityObject.convertToString(
					stacktestvo.getNextTestDate(), "yyyy-MM-dd"));
			sqlutil.addInParam(new Integer(stacktestvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Stack Test Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static int addWasteConsum(IncConsumptionVo incconsumptionvo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into incincremannualwasteconsum ");
		stringbuffer
				.append("(WASTECONSUMPTIONID, INCINERATORCREMATORIESID,YEAR,CONSUMPTION)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(incconsumptionvo.getObjectId()).append(",");
		stringbuffer.append("'").append(incconsumptionvo.getYear())
				.append("',");
		stringbuffer.append("'").append(incconsumptionvo.getConsumption())
				.append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Incinerator Waste Consumption");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deleteWasteConsum(IncConsumptionVo incconsumptionvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from incincremannualwasteconsum");
		stringbuffer.append(" where WASTECONSUMPTIONID=? ");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(incconsumptionvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Incinerator WASte Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updateWasteConsum(IncConsumptionVo incconsumptionvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update incincremannualwasteconsum ");
		stringbuffer
				.append("set INCINERATORCREMATORIESID=?, YEAR=?, CONSUMPTION=? ");
		stringbuffer.append(" where WASTECONSUMPTIONID=? ");
		SqlUtil sqlutil = new SqlUtil();
		try {
			sqlutil.addInParam(new Integer(incconsumptionvo.getObjectId()));
			sqlutil.addInParam(incconsumptionvo.getYear());
			sqlutil.addInParam(incconsumptionvo.getConsumption());
			sqlutil.addInParam(new Integer(incconsumptionvo.getId()));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Incinerator WASte Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getWasteConsum(int i) throws TrackingException {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select WASTECONSUMPTIONID as CONS_ID, ");
		stringbuffer.append("INCINERATORCREMATORIESID as object_id, ");
		stringbuffer.append("YEAR as YEAR, ");
		stringbuffer.append("CONSUMPTION as CONSUMPTION ");
		stringbuffer.append("from incincremannualwasteconsum ");
		stringbuffer.append("where INCINERATORCREMATORIESID = ? ");
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.IncConsumptionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getPermitInfo(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.INCINERATORCREMATORIESID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, stk.SERIAL, stk.CAPACITY, bldg.BUILDINGNAME from incineratorcrematories stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.IncineratorListVo.class);
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
			.getLog(com.eespc.tracking.entity.IncineratorEntity.class);

}