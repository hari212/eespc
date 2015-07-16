package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.EtoTestInfoVo;
import com.eespc.tracking.bo.EtoVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class EtoEntity {
	public static class EtoTestEntity {

		public static EtoTestInfoVo findByPrimaryKey(int i)
				throws TrackingException {
			String s = "select * from ETOTESTINFO where ETOTESTID=?";
			EtoTestInfoVo etotestinfovo = null;
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				List list = sqlutil.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.EtoTestInfoVo.class);
				if (list != null && list.size() > 0)
					etotestinfovo = (EtoTestInfoVo) list.get(0);
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						(new StringBuilder()).append("findByPrimaryKey(")
								.append(i).append(")").toString());
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return etotestinfovo;
		}

		public static List getTestList(int i) throws TrackingException {
			Object obj = new ArrayList();
			String s = "select * from ETOTESTINFO where ETOID =?";
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				obj = sqlutil.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.EtoTestInfoVo.class);
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"Getting Test list for ETO");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return ((List) (obj));
		}

		public static int addTest(EtoTestInfoVo etotestinfovo)
				throws TrackingException {
			int i = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into ETOTESTINFO ");
			stringbuffer.append("(ETOTESTID, ETOID, YEAR, ");
			stringbuffer.append(" TESTDATE, NEXTDATE, NEXTDATENOTE) ");
			stringbuffer.append(" values (null,");
			stringbuffer.append(etotestinfovo.getEtoId()).append(",");
			stringbuffer.append("'").append(etotestinfovo.getYear())
					.append("',");

			if (UtilityObject.isNotEmpty(etotestinfovo.getTestDate()))
				stringbuffer.append("'").append(etotestinfovo.getTestDate())
						.append("',");
			else
				stringbuffer.append("null,");

			if (UtilityObject.isNotEmpty(etotestinfovo.getNextDate()))
				stringbuffer.append("'").append(etotestinfovo.getNextDate())
						.append("',");
			else
				stringbuffer.append("null,");
			stringbuffer.append("'").append(etotestinfovo.getNextDateNote())
					.append("'");
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				i = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("Add Eto TestInfo Exception:" + exception);
				TrackingException trackingexception = new TrackingException(
						"While Adding ETO Test Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return i;
		}

		public static void updateTest(EtoTestInfoVo etotestinfovo)
				throws TrackingException {
			byte byte0 = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("update ETOTESTINFO ");
			stringbuffer.append("set YEAR=?, ");
			stringbuffer.append(" TESTDATE=?, NEXTDATE=?, NEXTDATENOTE=? ");
			stringbuffer.append(" where ETOTESTID=?");
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(etotestinfovo.getYear());

			if (UtilityObject.isNotEmpty(etotestinfovo.getTestDate()))
				sqlutil.addInParam(etotestinfovo.getTestDate());
			else
				sqlutil.addInParam(null);

			if (UtilityObject.isNotEmpty(etotestinfovo.getNextDate()))
				sqlutil.addInParam(etotestinfovo.getNextDate());
			else
				sqlutil.addInParam(null);
			sqlutil.addInParam(etotestinfovo.getNextDateNote());
			sqlutil.addInParam(new Integer(etotestinfovo.getEtoTestId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				System.out
						.println("Update Eto TestInfo Exception:" + exception);
				TrackingException trackingexception = new TrackingException(
						"While updating ETO Test Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public EtoTestEntity() {
		}
	}

	public static class EtoPermitEntity {

		public static PermitInfoVo findByPrimaryKey(int i)
				throws TrackingException {
			String s = "select ETOPERMITID as PERMIT_ID, ETOID as OBJECT_ID,  7 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issueddate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID, year from ETOPERMITINFO where ETOPERMITID=?";
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
			stringbuffer.append("insert into ETOPERMITINFO ");
			stringbuffer.append("(ETOPERMITID, ETOID, PERMITNUMBER, ");
			stringbuffer
					.append("ISSUEDDATE, EXPIRATIONDATE, NOTE, DEPID, YEAR) ");
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
			stringbuffer.append("'").append(permitinfovo.getNote())
					.append("',");
			stringbuffer.append(permitinfovo.getDepId()).append(",");
			stringbuffer.append("'").append(permitinfovo.getYear()).append("'");
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				i = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While Adding ETO Permit Info:" + exception);
				TrackingException trackingexception = new TrackingException(
						"While Adding ETO Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return i;
		}

		public static void updatePermit(PermitInfoVo permitinfovo)
				throws TrackingException {
			byte byte0 = -99;
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("update ETOPERMITINFO ");
			stringbuffer.append("set PERMITNUMBER=?, ");
			stringbuffer
					.append(" ISSUEDDATE=?, EXPIRATIONDATE=?,NOTE=?,YEAR=? ");
			stringbuffer.append(" where ETOPERMITID=?");
			SqlUtil sqlutil = new SqlUtil();
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
			sqlutil.addInParam(permitinfovo.getYear());
			sqlutil.addInParam(new Integer(permitinfovo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				System.out.println("While updating ETO Permit Info:"
						+ exception);
				TrackingException trackingexception = new TrackingException(
						"While updating ETO Permit Info");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		public static List getPermitList(int i) throws TrackingException {
			Object obj = new ArrayList();
			String s = "select ETOPERMITID as PERMIT_ID, ETOID as OBJECT_ID,  7 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issueddate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID, YEAR from ETOPERMITINFO where ETOID =?";
			try {
				SqlUtil sqlutil = new SqlUtil();
				sqlutil.addInParam(new Integer(i));
				obj = sqlutil.execQueryUsingConstructor(s,
						com.eespc.tracking.bo.PermitInfoVo.class);
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"Getting Permit Info list for ETO");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
			return ((List) (obj));
		}

		public EtoPermitEntity() {
		}
	}

	public static void deletePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from ETOPERMITINFO ");
		stringbuffer.append(" where ETOPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating ETO Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deleteTest(EtoTestInfoVo etotestinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from ETOTESTINFO");
		stringbuffer.append(" where ETOTESTID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(etotestinfovo.getEtoTestId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating ETO Test Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public EtoEntity() {
	}

	public static EtoVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, eto et where bld.buildingid = et.buildingid and et.ETOID =?";
		EtoVo etovo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.EtoVo.class);
			if (list != null && list.size() > 0)
				etovo = (EtoVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return etovo;
	}

	public static int add(EtoVo etovo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,").append(etovo)
					.toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into ETO ");
		stringbuffer.append("(ETOID, BUILDINGID, FACILITYDESIGNATEDID,");
		stringbuffer.append("MANUFACTURER, MODEL, SERIAL,");
		stringbuffer.append("VOLUME, MIXTURETYPE, CONTAINERWEIGHT,");
		stringbuffer
				.append("GASMIXTURE, INSTALLATIONDATE, ISRECORDSAVAILABLE,");
		stringbuffer
				.append("ETCOMMENTS, DEP, DOB,ACTIONTAKEN,STATEPERMIT, STACKFROM, MODIFIEDINPAST, DISCONNECTEDYEAR,ISABATOR,HRS,DAYS,A_DECPERMITOBTAINED,A_STACKTESTPROTSUBMITED,A_TESTCONDUCTEDBY,A_TESTREPORTSUBMITED,A_PERMITDATE,A_COMPYWITHETO,AMANUFACTURER,AMODEL,A_TESTPASSED,DOBSIGNOFF,ETODOB,STACKTESTREQUIRE) ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(etovo.getBuildingId()).append(",");
		stringbuffer.append("'").append(etovo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(etovo.getManufacturer()).append("',");
		stringbuffer.append("'").append(etovo.getModel()).append("',");
		stringbuffer.append("'").append(etovo.getSerial()).append("',");
		stringbuffer.append("'").append(etovo.getVolume()).append("',");
		stringbuffer.append(etovo.getMixtureType()).append(",");
		stringbuffer.append(etovo.getContainerWeight()).append(",");
		stringbuffer.append(etovo.getGasMixture()).append(",");
		if (UtilityObject.isNotEmpty(etovo.getInstallationDate()))
			stringbuffer.append(UtilityObject.getDateStringForDB(etovo
					.getInstallationDate()));
		else
			stringbuffer.append("null,");

		if (etovo.isRecordsAvailable())
			stringbuffer.append("'Y',");
		else
			stringbuffer.append("'N',");
		stringbuffer.append("'").append(etovo.getComments()).append("',");
		stringbuffer.append("'").append(etovo.getDep()).append("',");
		stringbuffer.append("'").append(etovo.getDob()).append("',");
		stringbuffer.append("'").append(etovo.getActiontaken()).append("',");
		stringbuffer.append("'").append(etovo.getStatePermit()).append("',");
		stringbuffer.append(etovo.getStackId()).append(",");
		stringbuffer.append(etovo.getModifiedInPast()).append(",");
		stringbuffer.append("'").append(etovo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(etovo.getIsabator()).append("',");
		stringbuffer.append("'").append(etovo.getHrs()).append("',");
		stringbuffer.append("'").append(etovo.getDays()).append("',");
		stringbuffer.append("'").append(etovo.getA_decPermitObtained())
				.append("',");
		stringbuffer.append("'").append(etovo.getA_stackTestProtSubmited())
				.append("',");
		stringbuffer.append("'").append(etovo.getA_testConductedBy())
				.append("',");
		stringbuffer.append("'").append(etovo.getA_testReportSubmited())
				.append("',");

		if (UtilityObject.isNotEmpty(etovo.getA_permitdate()))
			stringbuffer.append(UtilityObject.getDateStringForDB(etovo
					.getA_permitdate()));
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(etovo.getA_compyWithETO()).append("',");
		stringbuffer.append("'").append(etovo.getAmanufacturer()).append("',");
		stringbuffer.append("'").append(etovo.getAmodel()).append("',");
		stringbuffer.append("'").append(etovo.getA_testPassed()).append("',");
		stringbuffer.append("'").append(etovo.getDobsignoff()).append("',");
		stringbuffer.append("'").append(etovo.getEtoDob()).append("',");
		stringbuffer.append("'").append(etovo.getStackTestRequire())
				.append("'");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding ETO:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding ETO");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(EtoVo etovo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,").append(etovo)
					.toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update eto set ");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("MANUFACTURER=?,");
		stringbuffer.append("MODEL=?,");
		stringbuffer.append("SERIAL=?,");
		stringbuffer.append("VOLUME=?,");
		stringbuffer.append("MIXTURETYPE=?,");
		stringbuffer.append("CONTAINERWEIGHT=?,");
		stringbuffer.append("GASMIXTURE=?,");
		stringbuffer.append("INSTALLATIONDATE=?,");
		stringbuffer.append("ISRECORDSAVAILABLE=?,");
		stringbuffer.append("ETCOMMENTS=?,");
		stringbuffer.append("DEP=?,");
		stringbuffer.append("DOB=?,");
		stringbuffer.append("ACTIONTAKEN=?,");
		stringbuffer.append("STATEPERMIT=?, ");
		stringbuffer.append("STACKFROM=?, ");
		stringbuffer.append("MODIFIEDINPAST=?, ");
		stringbuffer.append("DISCONNECTEDYEAR=?, ");
		stringbuffer.append("ISABATOR=?,");
		stringbuffer.append("HRS=?,");
		stringbuffer.append("DAYS=?,");
		stringbuffer.append("A_DECPERMITOBTAINED=?,");
		stringbuffer.append("A_STACKTESTPROTSUBMITED=?,");
		stringbuffer.append("A_TESTCONDUCTEDBY=?,");
		stringbuffer.append("A_TESTREPORTSUBMITED=?,");
		stringbuffer.append("A_PERMITDATE=?,");
		stringbuffer.append("A_COMPYWITHETO=?,");
		stringbuffer.append("AMANUFACTURER=?,");
		stringbuffer.append("AMODEL=?, ");
		stringbuffer.append("A_TESTPASSED=?, ");
		stringbuffer.append("DOBSIGNOFF=?, ");
		stringbuffer.append("ETODOB=?, ");
		stringbuffer.append("STACKTESTREQUIRE=? ");
		stringbuffer.append("where etoid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(etovo.getFacilityDesignatedId());
		sqlutil.addInParam(etovo.getManufacturer());
		sqlutil.addInParam(etovo.getModel());
		sqlutil.addInParam(etovo.getSerial());
		sqlutil.addInParam(etovo.getVolume());
		sqlutil.addInParam(new Integer(etovo.getMixtureType()));
		sqlutil.addInParam(new Integer(etovo.getContainerWeight()));
		sqlutil.addInParam(new Integer(etovo.getGasMixture()));
		if (UtilityObject.isNotEmpty(etovo.getInstallationDate()))
			sqlutil.addInParam(UtilityObject.convertToString(
					UtilityObject.convertToDate(etovo.getInstallationDate()),
					"yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		if (etovo.isRecordsAvailable())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(etovo.getComments());
		sqlutil.addInParam(etovo.getDep());
		sqlutil.addInParam(etovo.getDob());
		sqlutil.addInParam(etovo.getActiontaken());
		sqlutil.addInParam(etovo.getStatePermit());
		sqlutil.addInParam(new Integer(etovo.getStackId()));
		sqlutil.addInParam(new Integer(etovo.getModifiedInPast()));
		sqlutil.addInParam(etovo.getDisconnectedYear());
		sqlutil.addInParam(etovo.getIsabator());
		sqlutil.addInParam(etovo.getHrs());
		sqlutil.addInParam(etovo.getDays());
		sqlutil.addInParam(etovo.getA_decPermitObtained());
		sqlutil.addInParam(etovo.getA_stackTestProtSubmited());
		sqlutil.addInParam(etovo.getA_testConductedBy());
		sqlutil.addInParam(etovo.getA_testReportSubmited());
		if (UtilityObject.isNotEmpty(etovo.getA_permitdate()))
			sqlutil.addInParam(UtilityObject.convertToString(
					UtilityObject.convertToDate(etovo.getA_permitdate()),
					"yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(etovo.getA_compyWithETO());
		sqlutil.addInParam(etovo.getAmanufacturer());
		sqlutil.addInParam(etovo.getAmodel());
		sqlutil.addInParam(etovo.getA_testPassed());
		sqlutil.addInParam(etovo.getDobsignoff());
		sqlutil.addInParam(etovo.getEtoDob());
		sqlutil.addInParam(etovo.getStackTestRequire());
		sqlutil.addInParam(new Integer(etovo.getEtoId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating ETO:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating ETO");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(EtoVo etovo) throws TrackingException {
		String as[] = { "delete from etopermitinfo where etoid=?",
				"delete from etotestinfo where etoid=?",
				"delete from eto where etoid=?" };
		for (int i = 0; i <= 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(etovo.getEtoId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating ETO");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static PermitInfoVo findPermit(int i) throws TrackingException {
		return EtoPermitEntity.findByPrimaryKey(i);
	}

	public static List getPermitList(int i) throws TrackingException {
		return EtoPermitEntity.getPermitList(i);
	}

	public static int addPermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		return EtoPermitEntity.addPermit(permitinfovo);
	}

	public static void updatePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		EtoPermitEntity.updatePermit(permitinfovo);
	}

	public static EtoTestInfoVo findTest(int i) throws TrackingException {
		return EtoTestEntity.findByPrimaryKey(i);
	}

	public static List getTestList(int i) throws TrackingException {
		return EtoTestEntity.getTestList(i);
	}

	public static int addTest(EtoTestInfoVo etotestinfovo)
			throws TrackingException {
		return EtoTestEntity.addTest(etotestinfovo);
	}

	public static void updateTest(EtoTestInfoVo etotestinfovo)
			throws TrackingException {
		EtoTestEntity.updateTest(etotestinfovo);
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.ETOID, stk.FACILITYDESIGNATEDID,stk.MODEL,stk.SERIAL, bldg.BUILDINGNAME from eto stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.EtoListVo.class);
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
			.getLog(com.eespc.tracking.entity.EtoEntity.class);

}