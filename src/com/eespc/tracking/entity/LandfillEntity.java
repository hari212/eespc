package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.LandfillVo;
import com.eespc.tracking.bo.PermitInfoVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.entity:
//            CogenTurbineEntity

public class LandfillEntity {

	public LandfillEntity() {
	}

	public static LandfillVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from building bld, landfills lfills where bld.buildingid = lfills.buildingid and lfills.landfillsid =?";
		LandfillVo landfillvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.LandfillVo.class);
			if (list != null && list.size() > 0)
				landfillvo = (LandfillVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return landfillvo;
	}

	public static int add(LandfillVo landfillvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(landfillvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into LANDFILLS ");
		stringbuffer.append("(LANDFILLSID, BUILDINGID,");
		stringbuffer
				.append("COMMENCEDDATE,YEARINSTALLED,STATUS,DISCONNECTEDDATE,ISLISTEDASTITLEV,");
		stringbuffer.append("ISLINKEDTOCOGEN,ISANNUALSTATEMENTSUBMITTED,");
		stringbuffer
				.append("LOCATION, FACILITYDESIGNATEDID,LFDEPNUMBER,LFDOBNUMBER,LFDOBISSUEDATE,LFLFGQUANTITY,LFGQUANTITY,LFLFGCOMBUSTION,LFGCOMBUSTION,LFSCH4,SVALUE,SALLOWABLEVALUE,CH4VALUE,CH4ALLOWABLEVALUE,ISTEMPERATURELIMIT,TEMPERATUREVALUE,TEMPERATUREALLOWABLEVALUE,NOXVALUE,NOXALLOWABLEVALUE,COVALUE,COALLOWABLEVALUE,OVALUE,OALLOWABLEVALUE,YEARLYOPERATING,YEARLYVALUE,YEARLYALLOWABLEVALUE,LCOMMENTS,AESDATE,DOBSIGNOFF)");
		stringbuffer.append(" values (null,");
		stringbuffer.append(landfillvo.getBuildingId()).append(",");
		if (UtilityObject.isNotEmpty(landfillvo.getCommencedDate()))
			stringbuffer.append("'").append(landfillvo.getCommencedDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(landfillvo.getYearinstalled())
				.append("',");
		stringbuffer.append("").append(landfillvo.getStatus()).append(",");
		if (UtilityObject.isNotEmpty(landfillvo.getDisconnecteddate()))
			stringbuffer.append("").append(landfillvo.getDisconnecteddate())
					.append(",");
		else
			stringbuffer.append("'',");
		if (landfillvo.isListedAsTitleV())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (landfillvo.isLinkedToCogenOrTurbine())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		if (landfillvo.isAnnualStmtSubmited())
			stringbuffer.append("'Y'").append(",");
		else
			stringbuffer.append("'N'").append(",");
		stringbuffer.append(landfillvo.getLocation()).append(",");
		stringbuffer.append("'").append(landfillvo.getFacilityDesignatedId())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLfDEPNumber())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLfDOBNumber())
				.append("',");
		if (UtilityObject.isNotEmpty(landfillvo.getLfDOBIssuedate()))
			stringbuffer.append("'").append(landfillvo.getLfDOBIssuedate())
					.append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'").append(landfillvo.getLfLFGQuantity())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLFGQuantity())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLfLFGCombustion())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLFGCombustion())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLfSCH4()).append("',");
		stringbuffer.append("'").append(landfillvo.getSvalue()).append("',");
		stringbuffer.append("'").append(landfillvo.getSallowablevalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getCh4value()).append("',");
		stringbuffer.append("'").append(landfillvo.getCh4allowablevalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getIstemperaturelimit())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getTemperaturevalue())
				.append("',");
		stringbuffer.append("'")
				.append(landfillvo.getTemperatureallowablevalue()).append("',");
		stringbuffer.append("'").append(landfillvo.getNoxvalue()).append("',");
		stringbuffer.append("'").append(landfillvo.getNoxallowablevalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getCovalue()).append("',");
		stringbuffer.append("'").append(landfillvo.getCoallowablevalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getOvalue()).append("',");
		stringbuffer.append("'").append(landfillvo.getOallowablevalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getYearlyoperating())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getYearlyvalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getYearlyallowablevalue())
				.append("',");
		stringbuffer.append("'").append(landfillvo.getLcomments()).append("',");
		if (UtilityObject.isNotEmpty(landfillvo.getAesdate()))
			stringbuffer.append("'").append(landfillvo.getAesdate())
					.append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'").append(landfillvo.getDobsignoff()).append("'");
		stringbuffer.append(")");
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding LandFills:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding LandFills");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void update(LandfillVo landfillvo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(landfillvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update LANDFILLS set ");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("COMMENCEDDATE=?,");
		stringbuffer.append("YEARINSTALLED=?, ");
		stringbuffer.append("STATUS=?, ");
		stringbuffer.append("DISCONNECTEDDATE=?, ");
		stringbuffer.append("ISLISTEDASTITLEV=?,");
		stringbuffer.append("ISLINKEDTOCOGEN=?,");
		stringbuffer.append("ISANNUALSTATEMENTSUBMITTED=?,");
		stringbuffer.append("LOCATION=?, ");
		stringbuffer.append("LFDEPNUMBER=?,");
		stringbuffer.append("LFDOBNUMBER=?,");
		stringbuffer.append("LFDOBISSUEDATE=?,");
		stringbuffer.append("LFLFGQUANTITY=?,");
		stringbuffer.append("LFGQUANTITY=?,");
		stringbuffer.append("LFLFGCOMBUSTION=?,");
		stringbuffer.append("LFGCOMBUSTION=?,");
		stringbuffer.append("LFSCH4=?,");
		stringbuffer.append("SVALUE=?,");
		stringbuffer.append("SALLOWABLEVALUE=?,");
		stringbuffer.append("CH4VALUE=?,");
		stringbuffer.append("CH4ALLOWABLEVALUE=?,");
		stringbuffer.append("ISTEMPERATURELIMIT=?,");
		stringbuffer.append("TEMPERATUREVALUE=?,");
		stringbuffer.append("TEMPERATUREALLOWABLEVALUE=?,");
		stringbuffer.append("NOXVALUE=?,");
		stringbuffer.append("NOXALLOWABLEVALUE=?,");
		stringbuffer.append("COVALUE=?,");
		stringbuffer.append("COALLOWABLEVALUE=?,");
		stringbuffer.append("OVALUE=?,");
		stringbuffer.append("OALLOWABLEVALUE=?,");
		stringbuffer.append("YEARLYOPERATING=?,");
		stringbuffer.append("YEARLYVALUE=?,");
		stringbuffer.append("YEARLYALLOWABLEVALUE=?,");
		stringbuffer.append("LCOMMENTS=?,");
		stringbuffer.append("AESDATE=?, ");
		stringbuffer.append("DOBSIGNOFF=? ");
		stringbuffer.append("where LANDFILLSID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(landfillvo.getFacilityDesignatedId());
		if (UtilityObject.isNotEmpty(landfillvo.getCommencedDate()))
			sqlutil.addInParam(landfillvo.getCommencedDate());
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(landfillvo.getYearinstalled());
		sqlutil.addInParam(String.valueOf(landfillvo.getStatus()));
		sqlutil.addInParam(landfillvo.getDisconnecteddate());
		if (landfillvo.isListedAsTitleV())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (landfillvo.isLinkedToCogenOrTurbine())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		if (landfillvo.isAnnualStmtSubmited())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(new Integer(landfillvo.getLocation()));

		sqlutil.addInParam(landfillvo.getLfDEPNumber());
		sqlutil.addInParam(landfillvo.getLfDOBNumber());
		if (UtilityObject.isNotEmpty(landfillvo.getLfDOBIssuedate()))
			sqlutil.addInParam(landfillvo.getLfDOBIssuedate());
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(landfillvo.getLfLFGQuantity());
		sqlutil.addInParam(landfillvo.getLFGQuantity());
		sqlutil.addInParam(landfillvo.getLfLFGCombustion());
		sqlutil.addInParam(landfillvo.getLFGCombustion());
		sqlutil.addInParam(landfillvo.getLfSCH4());
		sqlutil.addInParam(landfillvo.getSvalue());
		sqlutil.addInParam(landfillvo.getSallowablevalue());
		sqlutil.addInParam(landfillvo.getCh4value());
		sqlutil.addInParam(landfillvo.getCh4allowablevalue());
		sqlutil.addInParam(landfillvo.getIstemperaturelimit());
		sqlutil.addInParam(landfillvo.getTemperaturevalue());
		sqlutil.addInParam(landfillvo.getTemperatureallowablevalue());
		sqlutil.addInParam(landfillvo.getNoxvalue());
		sqlutil.addInParam(landfillvo.getNoxallowablevalue());
		sqlutil.addInParam(landfillvo.getCovalue());
		sqlutil.addInParam(landfillvo.getCoallowablevalue());
		sqlutil.addInParam(landfillvo.getOvalue());
		sqlutil.addInParam(landfillvo.getOallowablevalue());
		sqlutil.addInParam(landfillvo.getYearlyoperating());
		sqlutil.addInParam(landfillvo.getYearlyvalue());
		sqlutil.addInParam(landfillvo.getYearlyallowablevalue());
		sqlutil.addInParam(landfillvo.getLcomments());
		if (UtilityObject.isNotEmpty(landfillvo.getAesdate()))
			sqlutil.addInParam(landfillvo.getAesdate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(landfillvo.getDobsignoff());

		sqlutil.addInParam(new Integer(landfillvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
			TrackingException trackingexception = new TrackingException(
					"While Updating Land Fills.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(LandfillVo landfillvo) throws TrackingException {
		String as[] = { "delete from LANDFILLPERMITINFO where LANDFILLSID=?",
				"delete from LANDFILLTOCOGENTURBINE where LANDFILLSID=?",
				"delete from LANDFILLS where LANDFILLSID=?" };
		for (int i = 0; i <= 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(landfillvo.getId()));
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

	public static int addPermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into LANDFILLPERMITINFO ");
		stringbuffer.append("(LANDFILLPERMITID, LANDFILLSID, PERMITNUMBER, ");
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
			System.out.println("While Adding LandFills Dep:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Landfill Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void deletePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from LANDFILLPERMITINFO");
		stringbuffer.append(" where LANDFILLPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Landfill Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void updatePermit(PermitInfoVo permitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update LANDFILLPERMITINFO ");
		stringbuffer.append("set PERMITNUMBER=?, ");
		stringbuffer.append(" ISSUEDDATE=?, EXPIRATIONDATE=?, NOTE=? ");
		stringbuffer.append(" where LANDFILLPERMITID=?");
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
		sqlutil.addInParam(new Integer(permitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While updating LandFills Dep:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While updating Landfill Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getPermitList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select LANDFILLPERMITID as PERMIT_ID, LANDFILLSID as OBJECT_ID,  8 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issueddate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, note as NOTE, depid as DEPT_ID from LANDFILLPERMITINFO where LANDFILLSID =?";
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

	public static List getCogenOrTurbineList(int i) throws TrackingException {
		int j = getFacilityId(i);
		if (j < 0)
			throw new TrackingException("Unable to retrieve facility id");
		else
			return CogenTurbineEntity.getCogenTurbineList(j);
	}

	private static int getFacilityId(int buildingId) throws TrackingException {
		int facilityId;
		String query = "select facilityid from building where buildingid="
				+ buildingId;
		SqlUtil utilObj = new SqlUtil();
		Connection con = null;
		Statement stmt = null;
		ResultSet rst = null;
		facilityId = -99;
		try {
			con = utilObj.getConnection();
			stmt = con.createStatement();
			rst = stmt.executeQuery(query);
			if (rst.next())
				facilityId = rst.getString("facilityid") == null ? -99 : rst
						.getInt("facilityid");
		} catch (Exception e) {
			TrackingException ex = new TrackingException(
					"While retrieving facilityid");
			ex.initCause(e);
			throw ex;
		} finally {
			SqlUtil.close(rst);
			SqlUtil.close(stmt);
			utilObj.closeConnection();
		}
		utilObj = null;
		return facilityId;
	}

	public static int addCogenOrTurbine(int i, List list)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert, landfillId=")
					.append(i).append(", cogenId=").append(list).toString());
		int j = -99;
		for (int k = 0; k < list.size(); k++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append("insert into landfilltocogenturbine ");
			stringbuffer.append("(LANDFILLSID, COGENTURBINEID) ");
			stringbuffer.append(" values (");
			stringbuffer.append(i).append(",");
			stringbuffer.append(list.get(k));
			stringbuffer.append(")");
			SqlUtil sqlutil = new SqlUtil();
			try {
				j = sqlutil.insert(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Adding addCogenOrTurbine");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

		return j;
	}

	public static List getCogenOrTurbine(int id) throws TrackingException {
		List chemicalList;
		chemicalList = new ArrayList();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append(" select COGENTURBINEID from landfilltocogenturbine ");
		queryBuff.append(" where LANDFILLSID =").append(id);
		SqlUtil util = new SqlUtil();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		try {
			conn = util.getConnection();
			stmt = conn.createStatement();
			for (rst = stmt.executeQuery(queryBuff.toString()); rst.next();) {
				int code = rst.getString("COGENTURBINEID") == null ? -99 : rst
						.getInt("COGENTURBINEID");
				String tempEnumObj = String.valueOf(code);
				if (tempEnumObj != null)
					chemicalList.add(tempEnumObj);
			}

		} catch (Exception e) {
			TrackingException ex = new TrackingException("getCogenList(" + id
					+ ")");
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

	public static int updateCogenOrTurbine(int i, List list)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update, landfillId=")
					.append(i).append(", cogenId=").append(list).toString());
		ArrayList arraylist = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(" delete from landfilltocogenturbine ");
		stringbuffer.append(" where LANDFILLSID =?");
		SqlUtil sqlutil = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		int j = -1;
		try {
			sqlutil.addInParam(new Integer(i));
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			j = addCogenOrTurbine(i, list);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"While updating Landfill Permit Info");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return j;
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.LANDFILLSID, stk.FACILITYDESIGNATEDID, bldg.BUILDINGNAME from landfills stk, BUILDING bldg where stk.buildingid=bldg.buildingid and  bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.LandFillsListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting B-O-Tank List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.LandfillEntity.class);

}