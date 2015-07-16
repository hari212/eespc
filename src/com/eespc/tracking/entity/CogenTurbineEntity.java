package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.CogenTurbineVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.myenum.CogenTurbineTypeEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class CogenTurbineEntity {
	public static class CogenPermitEntity {

		public static PermitInfoVo findByPrimaryKey(int i)
				throws TrackingException {
			String s = "select cogenturbinepermitid as PERMIT_ID, cogenturbineid as OBJECT_ID,  12 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issueddate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID, YEAR from cogenturbinepermitinfo where COGENTURBINEPERMITID =?";
			PermitInfoVo permitinfovo = null;
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				List list = sqlutil.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.PermitInfoVo.class);
				if (list != null && list.size() > 0)
					permitinfovo = (PermitInfoVo) list.get(0);
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						(new StringBuilder()).append("findByPrimaryKey(")
								.append(i).append(")").toString());
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return permitinfovo;
		}

		public static int addPermit(PermitInfoVo permitinfovo)
				throws TrackingException {
			int i = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into cogenturbinepermitinfo ");
			stringbuffer
					.append("(COGENTURBINEPERMITID, COGENTURBINEID, PERMITNUMBER, ");
			stringbuffer
					.append("ISSUEDDATE, EXPIRATIONDATE, NOTE, DEPID, YEAR) ");
			stringbuffer.append(" values (null,");
			stringbuffer.append(permitinfovo.getObjectId()).append(",");
			stringbuffer.append("'").append(permitinfovo.getPermitNumber())
					.append("',");
			if (permitinfovo.getIssueDate() != null) {
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(
								permitinfovo.getIssueDate(), "yyyy-MM-dd"))
						.append("',");
			} else {
				stringbuffer.append("null,");
			}
			if (permitinfovo.getExpirationDate() != null) {
				stringbuffer
						.append("'")
						.append(UtilityObject.convertToString(
								permitinfovo.getExpirationDate(), "yyyy-MM-dd"))
						.append("',");
			} else {
				stringbuffer.append("null,");
			}
			stringbuffer.append("'").append(permitinfovo.getNote())
					.append("',");
			stringbuffer.append(permitinfovo.getDepId()).append(",");
			stringbuffer.append("'").append(permitinfovo.getYear()).append("'");
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				i = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Adding Cogen/Turbine Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return i;
		}

		public static void updatePermit(PermitInfoVo permitinfovo)
				throws TrackingException {
			byte byte0 = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("update cogenturbinepermitinfo ");
			stringbuffer.append("set PERMITNUMBER=?, ");
			stringbuffer
					.append(" ISSUEDDATE=?, EXPIRATIONDATE=?, NOTE=?, YEAR=? ");
			stringbuffer.append(" where COGENTURBINEPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(permitinfovo.getPermitNumber());
			if (permitinfovo.getIssueDate() != null) {
				sqlutil.addInParam(UtilityObject.convertToString(
						permitinfovo.getIssueDate(), "yyyy-MM-dd"));
			} else {
				sqlutil.addInParam(null);
			}

			if (permitinfovo.getExpirationDate() != null) {
				sqlutil.addInParam(UtilityObject.convertToString(
						permitinfovo.getExpirationDate(), "yyyy-MM-dd"));
			} else {
				sqlutil.addInParam(null);
			}
			sqlutil.addInParam(permitinfovo.getNote());
			sqlutil.addInParam(permitinfovo.getYear());
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While updating Cogen/Turbine Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public static void deletePermit(PermitInfoVo permitinfovo)
				throws TrackingException {
			byte byte0 = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("delete from cogenturbinepermitinfo");
			stringbuffer.append(" where COGENTURBINEPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While updating Cogen/Turbine Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public static List getPermitList(int i, int j) throws TrackingException {
			Object obj = new ArrayList();
			String s = (new StringBuilder())
					.append("select COGENTURBINEPERMITID as PERMIT_ID, COGENTURBINEID as OBJECT_ID,  ")
					.append(j == CogenTurbineTypeEnum.COGEN.getCode() ? "12 as OBJECT_TYPE, "
							: "13 as OBJECT_TYPE, ")
					.append("permitnumber as PERMIT_NUMBER, ")
					.append("issueddate as PERMIT_ISSUE_DATE, ")
					.append("expirationdate as PERMIT_EXP_DATE, ")
					.append("note as NOTE, ").append("depid as DEPT_ID, ")
					.append("year as YEAR ")
					.append("from cogenturbinepermitinfo ")
					.append("where COGENTURBINEID =?").toString();
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				obj = sqlutil.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.PermitInfoVo.class);
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"Getting Permit Info list for Cogen/Turbine");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return ((List) (obj));
		}

		public CogenPermitEntity() {
		}
	}

	public CogenTurbineEntity() {
	}

	public static void deletefuelconsumption(int id) throws TrackingException {
		StringBuffer buff = new StringBuffer();
		buff.append("delete from cogenturbinefuelconsumption");
		buff.append(" where CTFUELCONSUMPTIONID=?");
		SqlUtil utilObj = new SqlUtil();
		try {
			utilObj.addInParam(id);
			utilObj.execForDmlUsingQuery(buff.toString());
		} catch (Exception e) {
			TrackingException ex = new TrackingException(
					"While updating deleting Info");
			ex.initCause(e);
			throw ex;
		}

	}

	public static List getCogenTurbineList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "SELECT * FROM cogenturbine where buildingid in (select buildingid from building  where facilityid=?)";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.CogenTurbineVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Error while retrieving CogenTurbine List for the facility id");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static CogenTurbineVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, cogenturbine et where bld.buildingid = et.buildingid and et.cogenturbineid =?";
		CogenTurbineVo cogenturbinevo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.CogenTurbineVo.class);
			if (list != null && list.size() > 0)
				cogenturbinevo = (CogenTurbineVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return cogenturbinevo;
	}

	public static int add(CogenTurbineVo cogenturbinevo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(cogenturbinevo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into cogenturbine ");
		stringbuffer.append("(COGENTURBINEID,BUILDINGID,FACILITYDESIGNATEDID,");
		stringbuffer.append("DECID,RESOURCETYPE,FLOOR,");
		stringbuffer.append("PRIMARYUSE,HASCONTROLSYSTEM,CONTROLEFFICIENCY,");
		stringbuffer
				.append("YEARINSTALLED,STATUS,DISCONNECTEDDATE,MANUFACTURER,MAKE,");
		stringbuffer.append("MODEL,SERIALNUMBER,BURNERMAKE,");
		stringbuffer.append("BURNERMODEL,BURNERTYPE,CAPACITY,");
		stringbuffer.append("PRIMARYFUEL,SECONDARYFUEL,FUELFROMTANK,");
		stringbuffer.append("NYCDOB,MEA,DEPNUMBER,");
		stringbuffer.append("SCHEDULEC,PLANAPPROVAL,RECORDSMAINTAINED,");
		stringbuffer
				.append("STACTTESTPROTOCOLSUBMITTED,TESTCONDUCTBY,TESTREPORTSUBMITTED,");
		stringbuffer
				.append("TESTREPORTSUBMITTEDDATE,COMPYWITHNOX,RETESTPLANNED,");
		stringbuffer
				.append("NEXTSTACKTESTDATE,NEXTSTACKTESTNOTE,STACKEXHAUSTID,ISFUELCAPPING,ISTURBINE,TMANUFACTURER,TMAKE,TMODEL,TSERIAL,TBURNERMAKE,TBURNERMODEL,TCAPACITY,TBURNERTYPE,TPRIMARYFUEL,TSECONDARYFUEL,ISWHRB,WMANUFACTURER,WMAKE,WMODEL,WSERIAL,WBURNERMAKE,WBURNERMODEL,WCAPACITY,WBURNERTYPE,WPRIMARYFUEL,WSECONDARYFUEL,CO_DECPERMITOBTAINED,CO_DEPPERMITOBTAINED,CO_COMPYWITHSO2RACTPLAN,CO_COMPYWITHPMRACTPLAN,CO_TESTPASSED,CO_STACKTESTDATE,CO_FUELGAPPING,CO_GASFUELGAPPING,CO_GASEMMISIONFACTOR,CO_OILFUELGAPPING,CO_OILEMMISIONFACTOR,CO_COMMENTS,CO_OILSO2,CO_GASSO2,CO_SO2ALLOWABLE,CO_NOXALLOWABLE,DOBSIGNOFF,DOBFILING)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(cogenturbinevo.getBuildingId()).append(",");
		stringbuffer.append("'")
				.append(cogenturbinevo.getFacilityDesignatedId()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getDecId()).append("',");
		stringbuffer.append(cogenturbinevo.getResourceType()).append(",");
		stringbuffer.append("'").append(cogenturbinevo.getFloor()).append("',");
		stringbuffer.append(cogenturbinevo.getPrimaryUse()).append(",");
		if (cogenturbinevo.isHasControlSystem())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		stringbuffer.append(cogenturbinevo.getControlEfficiency()).append(",");
		stringbuffer.append("'").append(cogenturbinevo.getYearInstalled())
				.append("',");
		stringbuffer.append("").append(cogenturbinevo.getStatus()).append(",");
		stringbuffer.append("'").append(cogenturbinevo.getDisconnecteddate())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getManufacturer())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getMake()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getModel()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getSerialNumber())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getBurnerMake())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getBurnerModel())
				.append("',");
		stringbuffer.append(cogenturbinevo.getBurnerType()).append(",");
		stringbuffer.append("'").append(cogenturbinevo.getCapacity())
				.append("',");
		stringbuffer.append(cogenturbinevo.getPrimaryFuel()).append(",");
		stringbuffer.append(cogenturbinevo.getSecondaryFuel()).append(",");
		stringbuffer.append(cogenturbinevo.getFuelFromTank()).append(",");
		stringbuffer.append("'").append(cogenturbinevo.getNycdob())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getMea()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getDepNumber())
				.append("',");
		if (cogenturbinevo.isSechedule())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		if (cogenturbinevo.isPlanApproval())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		if (cogenturbinevo.isRecordsMaintained())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		if (cogenturbinevo.isTestProtocolSubmitted())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		stringbuffer.append("'").append(cogenturbinevo.getTestContductedBy())
				.append("',");
		if (cogenturbinevo.isTestReportSubmitted())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");

		if (isNotEmpty(cogenturbinevo.getTestReportSubmittedDate()))
			stringbuffer
					.append("")
					.append(UtilityObject.getDateStringForDB(cogenturbinevo
							.getTestReportSubmittedDate())).append("");
		else
			stringbuffer.append("null").append(",");

		if (cogenturbinevo.isComplyWithNoxPlan())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");

		if (cogenturbinevo.isRetestPlanned())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");

		if (isNotEmpty(cogenturbinevo.getNextStackTestDate()))
			stringbuffer
					.append("")
					.append(UtilityObject.getDateStringForDB(cogenturbinevo
							.getNextStackTestDate())).append("");
		else
			stringbuffer.append("null").append(",");

		stringbuffer.append("'").append(cogenturbinevo.getNextStackTestNote())
				.append("',");

		stringbuffer.append(cogenturbinevo.getStackExhaustId()).append(",");
		if (cogenturbinevo.isFuleCappingUnderLimit())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");

		stringbuffer.append("'").append(cogenturbinevo.getIsturbine())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTmanufacturer())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTmake()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTmodel())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTserial())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTburnerMake())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTburnerModel())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTcapacity())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getTburnerType())
				.append("',");
		stringbuffer.append("").append(cogenturbinevo.getTprimaryFuel())
				.append(",");
		stringbuffer.append("").append(cogenturbinevo.getTsecondaryFuel())
				.append(",");
		// stringbuffer.append("'").append(cogenturbinevo.getToilFiringRate()).append("',");
		// stringbuffer.append("'").append(cogenturbinevo.getTnaturalGasFiringRate()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getIswhrb())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWmanufacturer())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWmake()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWmodel())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWserial())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWburnerMake())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWburnerModel())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWcapacity())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getWburnerType())
				.append("',");
		stringbuffer.append("").append(cogenturbinevo.getWprimaryFuel())
				.append(",");
		stringbuffer.append("").append(cogenturbinevo.getWsecondaryFuel())
				.append(",");
		// stringbuffer.append("'").append(cogenturbinevo.getWoilFiringRate()).append("',");
		// stringbuffer.append("'").append(cogenturbinevo.getWnaturalGasFiringRate()).append("',");
		stringbuffer.append("'")
				.append(cogenturbinevo.getCo_decPermitObtained()).append("',");
		stringbuffer.append("'")
				.append(cogenturbinevo.getCo_depPermitObtained()).append("',");
		stringbuffer.append("'")
				.append(cogenturbinevo.getCo_compyWithSO2RactPlan())
				.append("',");
		stringbuffer.append("'")
				.append(cogenturbinevo.getCo_compyWithPMRactPlan())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_testPassed())
				.append("',");

		if (isNotEmpty(cogenturbinevo.getCo_StackTestDate()))
			stringbuffer
					.append("")
					.append(UtilityObject.getDateStringForDB(cogenturbinevo
							.getCo_StackTestDate())).append("");
		else
			stringbuffer.append("null").append(",");

		stringbuffer.append("'").append(cogenturbinevo.getCo_fuelgapping())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_gasfuelgapping())
				.append("',");
		stringbuffer.append("'")
				.append(cogenturbinevo.getCo_gasemmisionfactor()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_oilfuelgapping())
				.append("',");
		stringbuffer.append("'")
				.append(cogenturbinevo.getCo_oilemmisionfactor()).append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_Comments())
				.append("',");

		stringbuffer.append("'").append(cogenturbinevo.getCo_oilso2())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_gasso2())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_so2allowable())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getCo_noxallowable())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getDobsignoff())
				.append("',");
		stringbuffer.append("'").append(cogenturbinevo.getDobfiling())
				.append("'");

		stringbuffer.append(")");
		System.out.println(stringbuffer.toString());
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error While Adding Cogen/Turbine" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Cogen/Turbine");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(CogenTurbineVo cogenturbinevo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(cogenturbinevo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update cogenturbine set ");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("DECID=?,");
		stringbuffer.append("RESOURCETYPE=?,");
		stringbuffer.append("FLOOR=?,");
		stringbuffer.append("PRIMARYUSE=?,");
		stringbuffer.append("HASCONTROLSYSTEM=?,");
		stringbuffer.append("CONTROLEFFICIENCY=?,");
		stringbuffer.append("YEARINSTALLED=?,");
		stringbuffer.append("STATUS=?,");
		stringbuffer.append("DISCONNECTEDDATE=?,");
		stringbuffer.append("MANUFACTURER=?,");
		stringbuffer.append("MAKE=?,");
		stringbuffer.append("MODEL=?,");
		stringbuffer.append("SERIALNUMBER=?,");
		stringbuffer.append("BURNERMAKE=?,");
		stringbuffer.append("BURNERMODEL=?,");
		stringbuffer.append("BURNERTYPE=?,");
		stringbuffer.append("CAPACITY=?,");
		stringbuffer.append("PRIMARYFUEL=?,");
		stringbuffer.append("SECONDARYFUEL=?,");
		stringbuffer.append("FUELFROMTANK=?,");
		stringbuffer.append("NYCDOB=?,");
		stringbuffer.append("MEA=?,");
		stringbuffer.append("DEPNUMBER=?,");
		stringbuffer.append("SCHEDULEC=?,");
		stringbuffer.append("PLANAPPROVAL=?,");
		stringbuffer.append("RECORDSMAINTAINED=?,");
		stringbuffer.append("STACTTESTPROTOCOLSUBMITTED=?,");
		stringbuffer.append("TESTCONDUCTBY=?,");
		stringbuffer.append("TESTREPORTSUBMITTED=?,");
		stringbuffer.append("TESTREPORTSUBMITTEDDATE=?,");
		stringbuffer.append("COMPYWITHNOX=?,");
		stringbuffer.append("RETESTPLANNED=?,");
		stringbuffer.append("NEXTSTACKTESTDATE=?,");
		stringbuffer.append("NEXTSTACKTESTNOTE=?,");
		stringbuffer.append("STACKEXHAUSTID=?,");
		stringbuffer.append("ISFUELCAPPING=?, ");
		stringbuffer.append("ISTURBINE=?,");
		stringbuffer.append("TMANUFACTURER=?,");
		stringbuffer.append("TMAKE=?,");
		stringbuffer.append("TMODEL=?,");
		stringbuffer.append("TSERIAL=?,");
		stringbuffer.append("TBURNERMAKE=?,");
		stringbuffer.append("TBURNERMODEL=?,");
		stringbuffer.append("TCAPACITY=?,");
		stringbuffer.append("TBURNERTYPE=?,");
		stringbuffer.append("TPRIMARYFUEL=?,");
		stringbuffer.append("TSECONDARYFUEL=?,");
		stringbuffer.append("ISWHRB=?,");
		stringbuffer.append("WMANUFACTURER=?,");
		stringbuffer.append("WMAKE=?,");
		stringbuffer.append("WMODEL=?,");
		stringbuffer.append("WSERIAL=?,");
		stringbuffer.append("WBURNERMAKE=?,");
		stringbuffer.append("WBURNERMODEL=?,");
		stringbuffer.append("WCAPACITY=?,");
		stringbuffer.append("WBURNERTYPE=?,");
		stringbuffer.append("WPRIMARYFUEL=?,");
		stringbuffer.append("WSECONDARYFUEL=?,");
		stringbuffer.append("CO_DECPERMITOBTAINED=?,");
		stringbuffer.append("CO_DEPPERMITOBTAINED=?,");
		stringbuffer.append("CO_COMPYWITHSO2RACTPLAN=?,");
		stringbuffer.append("CO_COMPYWITHPMRACTPLAN=?,");
		stringbuffer.append("CO_TESTPASSED=?,");
		stringbuffer.append("CO_STACKTESTDATE=?,");
		stringbuffer.append("CO_FUELGAPPING=?,");
		stringbuffer.append("CO_GASFUELGAPPING=?,");
		stringbuffer.append("CO_GASEMMISIONFACTOR=?,");
		stringbuffer.append("CO_OILFUELGAPPING=?,");
		stringbuffer.append("CO_OILEMMISIONFACTOR=?,");
		stringbuffer.append("CO_COMMENTS=?,");
		stringbuffer.append("CO_OILSO2=?,");
		stringbuffer.append("CO_GASSO2=?,");
		stringbuffer.append("CO_SO2ALLOWABLE=?,");
		stringbuffer.append("CO_NOXALLOWABLE=?, ");
		stringbuffer.append("DOBSIGNOFF=?, ");
		stringbuffer.append("DOBFILING=? ");
		stringbuffer.append(" where COGENTURBINEID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(cogenturbinevo.getFacilityDesignatedId());
		sqlutil.addInParam(cogenturbinevo.getDecId());
		sqlutil.addInParam(new Integer(cogenturbinevo.getResourceType()));
		sqlutil.addInParam(cogenturbinevo.getFloor());
		sqlutil.addInParam(new Integer(cogenturbinevo.getPrimaryUse()));
		if (cogenturbinevo.isHasControlSystem())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(new Integer(cogenturbinevo.getControlEfficiency()));
		sqlutil.addInParam(cogenturbinevo.getYearInstalled());
		sqlutil.addInParam(String.valueOf(cogenturbinevo.getStatus()));
		sqlutil.addInParam(cogenturbinevo.getDisconnecteddate());
		sqlutil.addInParam(cogenturbinevo.getManufacturer());
		sqlutil.addInParam(cogenturbinevo.getMake());
		sqlutil.addInParam(cogenturbinevo.getModel());
		sqlutil.addInParam(cogenturbinevo.getSerialNumber());
		sqlutil.addInParam(cogenturbinevo.getBurnerMake());
		sqlutil.addInParam(cogenturbinevo.getBurnerModel());
		sqlutil.addInParam(new Integer(cogenturbinevo.getBurnerType()));
		sqlutil.addInParam(cogenturbinevo.getCapacity());
		sqlutil.addInParam(new Integer(cogenturbinevo.getPrimaryFuel()));
		sqlutil.addInParam(new Integer(cogenturbinevo.getSecondaryFuel()));
		sqlutil.addInParam(new Integer(cogenturbinevo.getFuelFromTank()));
		sqlutil.addInParam(cogenturbinevo.getNycdob());
		sqlutil.addInParam(cogenturbinevo.getMea());
		sqlutil.addInParam(cogenturbinevo.getDepNumber());
		if (cogenturbinevo.isSechedule())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (cogenturbinevo.isPlanApproval())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (cogenturbinevo.isRecordsMaintained())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (cogenturbinevo.isTestProtocolSubmitted())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(cogenturbinevo.getTestContductedBy());
		if (cogenturbinevo.isTestReportSubmitted())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");

		if (UtilityObject.isNotEmpty(cogenturbinevo
				.getTestReportSubmittedDate()))
			sqlutil.addInParam(UtilityObject.convertToString(
					UtilityObject.convertToDate(cogenturbinevo
							.getTestReportSubmittedDate()), "yyyy-MM-dd"));
		else {
			sqlutil.addInParam(null);
		}

		if (cogenturbinevo.isComplyWithNoxPlan())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (cogenturbinevo.isRetestPlanned())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");

		if (UtilityObject.isNotEmpty(cogenturbinevo.getNextStackTestDate()))
			sqlutil.addInParam(UtilityObject.convertToString(UtilityObject
					.convertToDate(cogenturbinevo.getNextStackTestDate()),
					"yyyy-MM-dd"));
		else {
			sqlutil.addInParam(null);
		}

		sqlutil.addInParam(cogenturbinevo.getNextStackTestNote());

		sqlutil.addInParam(new Integer(cogenturbinevo.getStackExhaustId()));
		if (cogenturbinevo.isFuleCappingUnderLimit())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");

		sqlutil.addInParam(cogenturbinevo.getIsturbine());
		sqlutil.addInParam(cogenturbinevo.getTmanufacturer());
		sqlutil.addInParam(cogenturbinevo.getTmake());
		sqlutil.addInParam(cogenturbinevo.getTmodel());
		sqlutil.addInParam(cogenturbinevo.getTserial());
		sqlutil.addInParam(cogenturbinevo.getTburnerMake());
		sqlutil.addInParam(cogenturbinevo.getTburnerModel());
		sqlutil.addInParam(cogenturbinevo.getTcapacity());
		sqlutil.addInParam(cogenturbinevo.getTburnerType());
		sqlutil.addInParam(cogenturbinevo.getTprimaryFuel());
		sqlutil.addInParam(cogenturbinevo.getTsecondaryFuel());
		sqlutil.addInParam(cogenturbinevo.getIswhrb());
		sqlutil.addInParam(cogenturbinevo.getWmanufacturer());
		sqlutil.addInParam(cogenturbinevo.getWmake());
		sqlutil.addInParam(cogenturbinevo.getWmodel());
		sqlutil.addInParam(cogenturbinevo.getWserial());
		sqlutil.addInParam(cogenturbinevo.getWburnerMake());
		sqlutil.addInParam(cogenturbinevo.getWburnerModel());
		sqlutil.addInParam(cogenturbinevo.getWcapacity());
		sqlutil.addInParam(cogenturbinevo.getWburnerType());
		sqlutil.addInParam(cogenturbinevo.getWprimaryFuel());
		sqlutil.addInParam(cogenturbinevo.getWsecondaryFuel());
		sqlutil.addInParam(cogenturbinevo.getCo_decPermitObtained());
		sqlutil.addInParam(cogenturbinevo.getCo_depPermitObtained());
		sqlutil.addInParam(cogenturbinevo.getCo_compyWithSO2RactPlan());
		sqlutil.addInParam(cogenturbinevo.getCo_compyWithPMRactPlan());
		sqlutil.addInParam(cogenturbinevo.getCo_testPassed());
		if (UtilityObject.isNotEmpty(cogenturbinevo.getCo_StackTestDate())) {
			sqlutil.addInParam(UtilityObject.convertToString(UtilityObject
					.convertToDate(cogenturbinevo.getCo_StackTestDate()),
					"yyyy-MM-dd"));
		} else {
			sqlutil.addInParam(null);
		}
		sqlutil.addInParam(cogenturbinevo.getCo_fuelgapping());
		sqlutil.addInParam(cogenturbinevo.getCo_gasfuelgapping());
		sqlutil.addInParam(cogenturbinevo.getCo_gasemmisionfactor());
		sqlutil.addInParam(cogenturbinevo.getCo_oilfuelgapping());
		sqlutil.addInParam(cogenturbinevo.getCo_oilemmisionfactor());
		sqlutil.addInParam(cogenturbinevo.getCo_Comments());
		sqlutil.addInParam(cogenturbinevo.getCo_oilso2());
		sqlutil.addInParam(cogenturbinevo.getCo_gasso2());
		sqlutil.addInParam(cogenturbinevo.getCo_so2allowable());
		sqlutil.addInParam(cogenturbinevo.getCo_noxallowable());
		sqlutil.addInParam(cogenturbinevo.getDobsignoff());
		sqlutil.addInParam(cogenturbinevo.getDobfiling());
		sqlutil.addInParam(new Integer(cogenturbinevo.getId()));

		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Error")
					.append(exception).toString());
			TrackingException trackingexception = new TrackingException(
					"While Updating Cogen/Turbine");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(CogenTurbineVo cogenturbinevo)
			throws TrackingException {
		String as[] = {
				"delete from cogenturbinepermitinfo where COGENTURBINEID=?",
				"delete from cogenturbinefuelconsumption where COGENTURBINEID=?",
				"delete from landfilltocogenturbine where COGENTURBINEID=?",
				"delete from cogenturbine where COGENTURBINEID=?" };
		for (int i = 0; i <= 3; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(cogenturbinevo.getId()));
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

	public static PermitInfoVo findPermitByPrimaryKey(int i)
			throws TrackingException {
		return CogenPermitEntity.findByPrimaryKey(i);
	}

	public static int addPermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		return CogenPermitEntity.addPermit(permitinfovo);
	}

	public static void deletePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		CogenPermitEntity.deletePermit(permitinfovo);
	}

	public static void updatePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		CogenPermitEntity.updatePermit(permitinfovo);
	}

	public static List getPermitList(int i, int j) throws TrackingException {
		return CogenPermitEntity.getPermitList(i, j);
	}

	public static List getFuelConsumptionList(int i,
			ResourcesTypeEnum resourcestypeenum) throws TrackingException {
		if (resourcestypeenum == null)
			throw new TrackingException("ResourceTypeEnum cannot be 'NULL'");
		else
			return FuelConsumptionEntity.getFuelConsumption(i,
					resourcestypeenum.getCode());
	}

	public static List geto_FuelConsumptionList(int i,
			ResourcesTypeEnum resourcestypeenum) throws TrackingException {
		if (resourcestypeenum == null)
			throw new TrackingException("ResourceTypeEnum cannot be 'NULL'");
		else
			return FuelConsumptionEntity.geto_FuelConsumption(i,
					resourcestypeenum.getCode());
	}

	public static int addFuelConsumption(FuelConsumptionVo fuelconsumptionvo,
			ResourcesTypeEnum resourcestypeenum) throws TrackingException {
		if (resourcestypeenum == null)
			throw new TrackingException("ResourceTypeEnum cannot be 'NULL'");
		else
			return FuelConsumptionEntity.add(fuelconsumptionvo,
					resourcestypeenum.getCode());
	}

	public static void updateFuelConsumption(
			FuelConsumptionVo fuelconsumptionvo,
			ResourcesTypeEnum resourcestypeenum) throws TrackingException {
		if (resourcestypeenum == null) {
			throw new TrackingException("ResourceTypeEnum cannot be 'NULL'");
		} else {
			FuelConsumptionEntity.update(fuelconsumptionvo,
					resourcestypeenum.getCode());
			return;
		}
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
			return s;
		else
			return s1;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static String checkNullAndFilldate(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			String as[] = s.split("-");
			String s2 = (new StringBuilder()).append(as[2]).append("-")
					.append(as[0]).append("-").append(as[1]).toString();
			return s2;
		} else {
			return s1;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.COGENTURBINEID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, stk.SERIALNUMBER, stk.CAPACITY, stk.RESOURCETYPE, bldg.BUILDINGNAME from cogenturbine stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.CogenTurbineListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting COGEN List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	public static List getFuelConsumptionyearList(int boilerId, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(boilerId,
					ResourcesTypeEnum.COGEN.getCode(), year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(boilerId,
				ResourcesTypeEnum.COGEN.getCode(), year);
	}

	public static List geto_FuelConsumptionyearList(int boilerId, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.geto_FuelConsumptionyear(boilerId,
					ResourcesTypeEnum.COGEN.getCode(), year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.geto_FuelConsumptionyear(boilerId,
				ResourcesTypeEnum.COGEN.getCode(), year);
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.CogenTurbineEntity.class);

}