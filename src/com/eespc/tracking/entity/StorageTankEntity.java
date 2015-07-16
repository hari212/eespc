package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.bo.SpillProtectionVo;
import com.eespc.tracking.bo.StorageTankVo;
import com.eespc.tracking.bo.TankTightnessVo;
import com.eespc.tracking.bo.TrienialCathodicVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class StorageTankEntity {

	public StorageTankEntity() {
	}

	public static StorageTankVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, storagetank tank where bld.buildingid = tank.buildingid and tank.storagetankid =?";
		StorageTankVo storagetankvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.StorageTankVo.class);
			if (list != null && list.size() > 0)
				storagetankvo = (StorageTankVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return storagetankvo;
	}

	public static int add(StorageTankVo storagetankvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(storagetankvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into storagetank (");
		stringbuffer.append("storagetankid,");
		stringbuffer.append("buildingid,");
		stringbuffer.append("isdaytank,");
		stringbuffer.append("pbsnumber,");
		stringbuffer.append("pbsexpirationdate,");
		stringbuffer.append("pbsexpirationdatenote,");
		stringbuffer.append("fueltype,");
		stringbuffer.append("capacity,");
		stringbuffer.append("tanktype,");
		stringbuffer.append("regionalLocation,");
		stringbuffer.append("corrosionprotectiontype,");
		stringbuffer.append("cathodicinstallationdate,");
		stringbuffer.append("tankTightness,");
		stringbuffer.append("tankstatus,");
		stringbuffer.append("pipetype,");
		stringbuffer.append("pipeinternalprot,");
		stringbuffer.append("tankexternalprotection,");
		stringbuffer.append("externalprot,");
		stringbuffer.append("overfillprot,");
		stringbuffer.append("spillpreventiontype,");
		stringbuffer.append("dispenser,");
		stringbuffer.append("leakdetection,");
		stringbuffer.append("pipingseccontainment,");
		stringbuffer.append("secondarycontain,");
		stringbuffer.append("dobapproval,");
		stringbuffer.append("CERTIFICATEOFAPPROVAL,");
		stringbuffer.append("firedeptcertificate,");
		stringbuffer.append("location,");
		stringbuffer.append("yearinstalled,");
		stringbuffer.append("DISCONNECTEDDATE, ");
		stringbuffer.append("testcompany,");
		stringbuffer.append("productstored,");
		stringbuffer.append("regwithstateagency,");
		stringbuffer.append("facilitydesignatedid,");
		stringbuffer
				.append("tanklocation,scomments,FUELSUPPLIEDTO,dobsignoff,dobfiling");
		stringbuffer.append(")");
		stringbuffer.append(" values (null,");
		stringbuffer.append(storagetankvo.getBuildingId()).append(",");
		if (storagetankvo.isDayTank())
			stringbuffer.append("'").append("Y").append("',");
		else
			stringbuffer.append("'").append("N").append("',");

		/*
		 * if(storagetankvo.isDayTank())
		 * stringbuffer.append("'Y'").append(",'"); else
		 * stringbuffer.append("'N'").append(",'");
		 */
		stringbuffer.append("'").append(storagetankvo.getPbsNumber())
				.append("',");

		if (UtilityObject.isNotEmpty(storagetankvo.getPbsExpirationDate()))
			stringbuffer.append("'")
					.append(storagetankvo.getPbsExpirationDate()).append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'")
				.append(storagetankvo.getPbsExpirationDateNote()).append("',");
		stringbuffer.append(storagetankvo.getFuelType()).append(",'");
		stringbuffer.append(storagetankvo.getCapacity()).append("',");
		stringbuffer.append(storagetankvo.getTankType()).append(",'");
		stringbuffer.append(storagetankvo.getRegionalLocation()).append("',");
		stringbuffer.append(storagetankvo.getCorrosionProtectionType()).append(
				",");

		if (UtilityObject.isNotEmpty(storagetankvo
				.getCathodicInstallationDate()))
			stringbuffer.append("'")
					.append(storagetankvo.getCathodicInstallationDate())
					.append("',");
		else
			stringbuffer.append("null,");

		// stringbuffer.append(storagetankvo.getCathodicInstallationDate()).append("',");
		stringbuffer.append("'").append(storagetankvo.getTankTightness())
				.append("',");
		stringbuffer.append(storagetankvo.getTankStatus()).append(",");
		stringbuffer.append(storagetankvo.getPipeType()).append(",");
		stringbuffer.append(storagetankvo.getPipeInternalProtection()).append(
				",");
		stringbuffer.append(storagetankvo.getTankExternalProtection()).append(
				",");
		stringbuffer.append(storagetankvo.getExternalProtection()).append(",");
		stringbuffer.append(storagetankvo.getOverfillProtection()).append(",");
		stringbuffer.append(storagetankvo.getSpillPreventionType()).append(",");
		stringbuffer.append(storagetankvo.getDispenser()).append(",");
		stringbuffer.append(storagetankvo.getLeakDetection()).append(",");
		stringbuffer.append(storagetankvo.getPipingSecContainment())
				.append(",");
		stringbuffer.append(storagetankvo.getSecondaryContainment()).append(
				",'");
		stringbuffer.append(storagetankvo.getDobApproval()).append("','");
		stringbuffer.append(storagetankvo.getCertificateofapproval()).append(
				"','");
		stringbuffer.append(storagetankvo.getFireDeptCertificate())
				.append("',");
		stringbuffer.append(storagetankvo.getLocation()).append(",'");
		stringbuffer.append(storagetankvo.getYearInstalled()).append("',");
		stringbuffer.append("'").append(storagetankvo.getDisconnecteddate())
				.append("','");
		stringbuffer.append(storagetankvo.gettestcompany()).append("',");
		stringbuffer.append(storagetankvo.getProductStored()).append(",");
		if (storagetankvo.isRegWithStateAgency())
			stringbuffer.append("'").append("Y").append("',");
		else
			stringbuffer.append("'").append("N").append("',");

		/*
		 * if(storagetankvo.isRegWithStateAgency())
		 * stringbuffer.append("'").append("Y").append("','"); else
		 * stringbuffer.append("'N'").append(",'");
		 */
		stringbuffer.append("'")
				.append(storagetankvo.getFacilityDesignatedId()).append("','");
		stringbuffer.append(storagetankvo.getTankLocation()).append("','");
		stringbuffer.append(storagetankvo.getScomments()).append("','");
		stringbuffer.append(storagetankvo.getFuelsuppliedto()).append("',");
		stringbuffer.append("'").append(storagetankvo.getDobsignoff())
				.append("',");
		stringbuffer.append("'").append(storagetankvo.getDobFiling())
				.append("'");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error While adding the Storage tank:"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Storage Tank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(StorageTankVo storagetankvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(storagetankvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update storagetank set ");
		stringbuffer.append("isdaytank=?, ");
		stringbuffer.append("pbsnumber=?,");
		stringbuffer.append("pbsexpirationdate=?, ");
		stringbuffer.append("pbsexpirationdatenote=?, ");
		stringbuffer.append("fueltype=?,");
		stringbuffer.append("capacity=?, ");
		stringbuffer.append("tanktype=?, ");
		stringbuffer.append("regionalLocation=?, ");
		stringbuffer.append("corrosionprotectiontype=?,");
		stringbuffer.append("cathodicinstallationdate=?,");
		stringbuffer.append("tankTightness=?,");
		stringbuffer.append("tankstatus=?,");
		stringbuffer.append("pipetype=?, ");
		stringbuffer.append("pipeinternalprot=?, ");
		stringbuffer.append("tankexternalprotection=?, ");
		stringbuffer.append("externalprot=?, ");
		stringbuffer.append("overfillprot=?, ");
		stringbuffer.append("spillpreventiontype=?, ");
		stringbuffer.append("dispenser=?, ");
		stringbuffer.append("leakdetection=?, ");
		stringbuffer.append("pipingseccontainment=?, ");
		stringbuffer.append("secondarycontain=?,");
		stringbuffer.append("dobapproval=?, ");
		stringbuffer.append("CERTIFICATEOFAPPROVAL=?, ");
		stringbuffer.append("firedeptcertificate=?, ");
		stringbuffer.append("location=?,");
		stringbuffer.append("yearinstalled=?,");
		stringbuffer.append("DISCONNECTEDDATE=?, ");
		stringbuffer.append("testcompany=?,");
		stringbuffer.append("productstored=?,");
		stringbuffer.append("regwithstateagency=?, ");
		stringbuffer.append("facilitydesignatedid=?, ");
		stringbuffer.append("tanklocation=?, ");
		stringbuffer.append("scomments=?, ");
		stringbuffer.append("fuelsuppliedto=?, ");
		stringbuffer.append("dobsignoff=?, ");
		stringbuffer.append("dobfiling=? ");
		stringbuffer.append("where storagetankid=?");
		SqlUtil sqlutil = new SqlUtil();
		if (storagetankvo.isDayTank())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(storagetankvo.getPbsNumber());

		if (UtilityObject.isNotEmpty(storagetankvo.getPbsExpirationDate()))
			sqlutil.addInParam(storagetankvo.getPbsExpirationDate());
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(storagetankvo.getPbsExpirationDateNote());
		sqlutil.addInParam(new Integer(storagetankvo.getFuelType()));
		sqlutil.addInParam(storagetankvo.getCapacity());
		sqlutil.addInParam(new Integer(storagetankvo.getTankType()));
		sqlutil.addInParam(storagetankvo.getRegionalLocation());
		sqlutil.addInParam(new Integer(storagetankvo
				.getCorrosionProtectionType()));

		if (UtilityObject.isNotEmpty(storagetankvo
				.getCathodicInstallationDate()))
			sqlutil.addInParam(storagetankvo.getCathodicInstallationDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(storagetankvo.getTankTightness());
		sqlutil.addInParam(new Integer(storagetankvo.getTankStatus()));
		sqlutil.addInParam(new Integer(storagetankvo.getPipeType()));
		sqlutil.addInParam(new Integer(storagetankvo
				.getPipeInternalProtection()));
		sqlutil.addInParam(new Integer(storagetankvo
				.getTankExternalProtection()));
		sqlutil.addInParam(new Integer(storagetankvo.getExternalProtection()));
		sqlutil.addInParam(new Integer(storagetankvo.getOverfillProtection()));
		sqlutil.addInParam(new Integer(storagetankvo.getSpillPreventionType()));
		sqlutil.addInParam(new Integer(storagetankvo.getDispenser()));
		sqlutil.addInParam(new Integer(storagetankvo.getLeakDetection()));
		sqlutil.addInParam(new Integer(storagetankvo.getPipingSecContainment()));
		sqlutil.addInParam(new Integer(storagetankvo.getSecondaryContainment()));
		sqlutil.addInParam(storagetankvo.getDobApproval());
		sqlutil.addInParam(storagetankvo.getCertificateofapproval());
		sqlutil.addInParam(storagetankvo.getFireDeptCertificate());
		sqlutil.addInParam(new Integer(storagetankvo.getLocation()));
		sqlutil.addInParam(storagetankvo.getYearInstalled());
		sqlutil.addInParam(storagetankvo.getDisconnecteddate());
		sqlutil.addInParam(storagetankvo.gettestcompany());
		sqlutil.addInParam(new Integer(storagetankvo.getProductStored()));
		if (storagetankvo.isRegWithStateAgency())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(storagetankvo.getFacilityDesignatedId());
		sqlutil.addInParam(new Integer(storagetankvo.getTankLocation()));
		sqlutil.addInParam(storagetankvo.getScomments());
		sqlutil.addInParam(storagetankvo.getFuelsuppliedto());
		sqlutil.addInParam(storagetankvo.getDobsignoff());
		sqlutil.addInParam(storagetankvo.getDobFiling());
		System.out.println((new StringBuilder()).append("sigma2")
				.append(storagetankvo.getTankLocation()).toString());
		sqlutil.addInParam(new Integer(storagetankvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Error While updating the Storage tank:"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Storage Tank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(StorageTankVo storagetankvo)
			throws TrackingException {
		String as[] = { "delete from storagepermitinfo where storagetankid=?",
				"delete from spillcompliance where storagetankid=?",
				"delete from tanktightnessinfo where storagetankid=?",
				"delete from trienialcathodicinfo where storagetankid=?",
				"delete from storagetank where storagetankid=?" };
		for (int i = 0; i <= 4; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(storagetankvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating Storage Tank");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	public static int addPermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into storagepermitinfo ");
		stringbuffer.append("(storagepermitid, storagetankid, permitnumber, ");
		stringbuffer.append("issuedate, expirationdate, note, depid) ");
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
			stringbuffer.append("null").append(",");

		if (permitinfovo.getExpirationDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							permitinfovo.getExpirationDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null").append(",");

		stringbuffer.append("'").append(permitinfovo.getNote()).append("',");

		stringbuffer.append(permitinfovo.getDepId()).append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Storage Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void updatePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update storagepermitinfo ");
		stringbuffer.append("set permitnumber=?, ");
		stringbuffer.append(" issuedate=?, expirationdate=?, note=? ");
		stringbuffer.append(" where storagepermitid=?");
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

		sqlutil.addInParam(UtilityObject.checkNullAndFill(
				permitinfovo.getNote(), ""));

		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Storage Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deletePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from storagepermitinfo");
		stringbuffer.append(" where storagepermitid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Storage Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getPermitList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select storagepermitid as PERMIT_ID, storagetankid as OBJECT_ID,  2 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issuedate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID from storagepermitinfo where storagetankid =?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PermitInfoVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Getting Permit Info list for Storage Tank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static List getSpillControlList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select * from spillcompliance where storagetankid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.SpillProtectionVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Getting Spill Control list for Storage Tank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static int addSpillControl(SpillProtectionVo spillprotectionvo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into spillcompliance ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(spillprotectionvo.getTankId()).append(",");

		if (spillprotectionvo.getDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							spillprotectionvo.getDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null").append(",");

		stringbuffer
				.append("'")
				.append(UtilityObject.booleanToString(spillprotectionvo
						.isThereAnySpill())).append("',");
		stringbuffer.append("'").append(spillprotectionvo.getSpillNumber())
				.append("',");
		stringbuffer
				.append("'")
				.append(UtilityObject.booleanToString(spillprotectionvo
						.isCompliant())).append("')");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Storage Compilance Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void updateSpillControl(SpillProtectionVo spillprotectionvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update spillcompliance ");
		stringbuffer.append("set date=?, ");
		stringbuffer.append(" anyspill=?, spillnumber=?, compliance=? ");
		stringbuffer.append(" where spillcomplianceid=?");
		SqlUtil sqlutil = new SqlUtil();

		if (spillprotectionvo.getDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					spillprotectionvo.getDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(UtilityObject.booleanToString(spillprotectionvo
				.isThereAnySpill()));
		sqlutil.addInParam(spillprotectionvo.getSpillNumber());
		sqlutil.addInParam(UtilityObject.booleanToString(spillprotectionvo
				.isCompliant()));
		sqlutil.addInParam(new Integer(spillprotectionvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Storage Compilance Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deleteSpillControl(SpillProtectionVo spillprotectionvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from spillcompliance ");
		stringbuffer.append(" where spillcomplianceid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(spillprotectionvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Storage Compilance Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static SpillProtectionVo getSpillProtection(int i)
			throws TrackingException {
		SpillProtectionVo spillprotectionvo = null;
		String s = "select * from spillcompliance where spillcomplianceid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.SpillProtectionVo.class);
			if (list != null && list.size() > 0)
				spillprotectionvo = (SpillProtectionVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getSpillProtection(")
							.append(i).append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return spillprotectionvo;
	}

	// ****************Trienial*****************//

	public static int addTrienialCathodicProtection(
			TrienialCathodicVo trienialcathodicvo) throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into trienialcathodicinfo ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(trienialcathodicvo.getTankId()).append(",");

		if (trienialcathodicvo.getLastTestDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							trienialcathodicvo.getLastTestDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null").append(",");

		if (trienialcathodicvo.getNextTestDueDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							trienialcathodicvo.getNextTestDueDate(),
							"yyyy-MM-dd")).append("',");
		else
			stringbuffer.append("null").append(",");

		stringbuffer.append("'").append(trienialcathodicvo.getNote())
				.append("',");

		if (trienialcathodicvo.getActualTestDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							trienialcathodicvo.getActualTestDate(),
							"yyyy-MM-dd")).append("')");
		else
			stringbuffer.append("null").append(")");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Trienial Cathodic Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void updateTrienialCathodicProtection(
			TrienialCathodicVo trienialcathodicvo) throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update trienialcathodicinfo ");
		stringbuffer.append("set lasttestdate=?, ");
		stringbuffer.append(" nexttestduedate=?, ");
		stringbuffer.append(" note=?, ");
		stringbuffer.append(" actualtestdate=? ");
		stringbuffer.append(" where TRIENIALTESTID=?");
		SqlUtil sqlutil = new SqlUtil();

		if (trienialcathodicvo.getLastTestDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					trienialcathodicvo.getLastTestDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		if (trienialcathodicvo.getNextTestDueDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					trienialcathodicvo.getNextTestDueDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(trienialcathodicvo.getNote());

		if (trienialcathodicvo.getActualTestDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					trienialcathodicvo.getActualTestDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(new Integer(trienialcathodicvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Trienial Cathodic Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deleteTrienialCathodicProtection(
			TrienialCathodicVo trienialcathodicvo) throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from trienialcathodicinfo ");
		stringbuffer.append("where TRIENIALTESTID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(trienialcathodicvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Trienial Cathodic Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getTrienialCathodicProtectionTestList(int i)
			throws TrackingException {
		Object obj = new ArrayList();
		String s = "select * from trienialcathodicinfo where storagetankid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.TrienialCathodicVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Getting Trienial Cathodic list for Storage Tank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static TrienialCathodicVo getTrienialCathodicProtection(int i)
			throws TrackingException {
		TrienialCathodicVo trienialcathodicvo = null;
		String s = "select * from trienialcathodicinfo where TRIENIALTESTID=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.TrienialCathodicVo.class);
			if (list != null && list.size() > 0)
				trienialcathodicvo = (TrienialCathodicVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("getTrienialCathodicProtection(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return trienialcathodicvo;
	}

	/************************************************************ Trienial End **************************************************/

	public static int addTankTightness(TankTightnessVo tanktightnessvo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into tanktightnessinfo ");
		stringbuffer.append(" values (null,");
		stringbuffer.append(tanktightnessvo.getTankId()).append(",");

		if (tanktightnessvo.getTestDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							tanktightnessvo.getTestDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null").append(",");

		if (tanktightnessvo.getNextTestDate() != null)
			stringbuffer
					.append("'")
					.append(UtilityObject.convertToString(
							tanktightnessvo.getNextTestDate(), "yyyy-MM-dd"))
					.append("',");
		else
			stringbuffer.append("null").append(",");

		stringbuffer.append("'").append(tanktightnessvo.getNextTestDateNote())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While Adding Tank Tightness Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void updateTankTightness(TankTightnessVo tanktightnessvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update tanktightnessinfo ");
		stringbuffer.append("set testdate=?, ");
		stringbuffer.append(" nexttestdate=?, ");
		stringbuffer.append(" nexttestdatenote=? ");
		stringbuffer.append(" where TANKTIGHTNESSID=?");
		SqlUtil sqlutil = new SqlUtil();

		if (tanktightnessvo.getTestDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					tanktightnessvo.getTestDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		if (tanktightnessvo.getNextTestDate() != null)
			sqlutil.addInParam(UtilityObject.convertToString(
					tanktightnessvo.getNextTestDate(), "yyyy-MM-dd"));
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(tanktightnessvo.getNextTestDateNote());

		sqlutil.addInParam(new Integer(tanktightnessvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Tank Tightness Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void deleteTankTightness(TankTightnessVo tanktightnessvo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from tanktightnessinfo ");
		stringbuffer.append("where TANKTIGHTNESSID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(tanktightnessvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Tank Tightness Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getTankTightnessList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select * from tanktightnessinfo where storagetankid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.TankTightnessVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Getting Tank Tightness list for Storage Tank");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static TankTightnessVo getTankTightness(int i)
			throws TrackingException {
		TankTightnessVo tanktightnessvo = null;
		String s = "select * from tanktightnessinfo where TANKTIGHTNESSID=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.TankTightnessVo.class);
			if (list != null && list.size() > 0)
				tanktightnessvo = (TankTightnessVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getTankTightness(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return tanktightnessvo;
	}

	public static List getFacilityTankList(int i) throws TrackingException {
		Object obj = new ArrayList();
		// String s = (new
		// StringBuilder()).append("select strg.STORAGETANKID, strg.PBSNUMBER, strg.FACILITYDESIGNATEDID, strg.CAPACITY, bldg.BUILDINGNAME from STORAGETANK strg, BUILDING bldg where strg.buildingid=bldg.buildingid and bldg.facilityid=? ").toString();
		String s = (new StringBuilder())
				.append("select strg.STORAGETANKID, strg.PBSNUMBER, strg.FACILITYDESIGNATEDID, strg.CAPACITY, bldg.BUILDINGNAME from STORAGETANK strg, BUILDING bldg where strg.buildingid=bldg.buildingid and bldg.facilityid=? ")
				.toString();
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.StorageTankListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Tank List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static List getFacilityTankList(int i, boolean flag)
			throws TrackingException {
		Object obj = new ArrayList();
		// String s = (new
		// StringBuilder()).append("select strg.STORAGETANKID, strg.PBSNUMBER, strg.FACILITYDESIGNATEDID, strg.CAPACITY, bldg.BUILDINGNAME from STORAGETANK strg, BUILDING bldg where strg.buildingid=bldg.buildingid and bldg.facilityid=? ").toString();
		String s = (new StringBuilder())
				.append("select strg.STORAGETANKID, strg.PBSNUMBER, strg.FACILITYDESIGNATEDID, strg.CAPACITY, bldg.BUILDINGNAME from STORAGETANK strg, BUILDING bldg where strg.buildingid=bldg.buildingid and strg.isDayTank=")
				.append(flag ? "'Y' " : "'N' ")
				.append("and bldg.facilityid=? ").toString();
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.StorageTankListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Tank List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	public static PermitInfoVo getPermit(int i) throws TrackingException {
		PermitInfoVo permitinfovo = null;
		String s = "select storagepermitid as PERMIT_ID, storagetankid as OBJECT_ID,  2 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issuedate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID from storagepermitinfo where storagepermitid =?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PermitInfoVo.class);
			if (list != null && list.size() > 0)
				permitinfovo = (PermitInfoVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getPermit(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return permitinfovo;
	}

	public static List getNysdecReport(int facilityid) {
		List result = new ArrayList();
		StringBuffer query = new StringBuffer();
		SqlUtil utilObj = new SqlUtil();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		long startTime = System.currentTimeMillis();
		try {
			conn = utilObj.getConnection();
			pstmt = conn.prepareStatement(query.toString());

			rst = pstmt.executeQuery();
			startTime = System.currentTimeMillis();
			Hashtable tempHash;
			String tempBoilerId;
			for (; rst.next(); hash4Unique.put(tempBoilerId, tempHash)) {
				tempHash = new Hashtable();
				tempBoilerId = rst.getString("generatorid");
				tempHash.put("generatorid", tempBoilerId);
				tempHash.put("expirationdate", UtilityObject.checkNullAndFill(
						UtilityObject.convertYyyyMmDd2MmDdYyyy(rst
								.getString("expirationdate")), "-"));
			}

		} catch (Exception e) {
			log.error("getNysdecReport(" + facilityid + ")", e);
		} finally {
			log.debug("Time taken for report query "
					+ (System.currentTimeMillis() - startTime) + " ms.");
			if (conn != null) {
				SqlUtil.close(rst);
				SqlUtil.close(pstmt);
				utilObj.closeConnection();
			}
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); result
					.add(hash4Unique.get(iter.next())))
				;
		}
		return result;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.StorageTankEntity.class);

}