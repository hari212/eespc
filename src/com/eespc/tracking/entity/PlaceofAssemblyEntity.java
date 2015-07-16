package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.PlaceofAssemblyPermitVo;
import com.eespc.tracking.bo.PlaceofAssemblyVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class PlaceofAssemblyEntity {

	public PlaceofAssemblyEntity() {
	}

	public static PlaceofAssemblyVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, placeofassembly chr where bld.buildingid = chr.buildingid and chr.placeofassemblyid =?";
		PlaceofAssemblyVo placeofassemblyvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.PlaceofAssemblyVo.class);
			if (list != null && list.size() > 0)
				placeofassemblyvo = (PlaceofAssemblyVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("PlaceofAssemblyVo findByPrimaryKey():"
					+ exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return placeofassemblyvo;
	}

	public static int add(PlaceofAssemblyVo placeofassemblyvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(placeofassemblyvo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into PLACEOFASSEMBLY ");
		stringbuffer
				.append("(PLACEOFASSEMBLYID,BUILDINGID,FACILITYDESIGNATEDID,PATYPE,LOCATION,SEATINGCAPACITY,DOBFILING,DOBPERMIT,DOBSIGNOFF,DOBPERMITNUMBER,DOBPLAN,FDPERMITOBTAINED,OPENVIOLATION,VIOLATIONTYPE,VIOLATIONNUM,NOTE)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("'").append(placeofassemblyvo.getBuildingId())
				.append("',");
		stringbuffer.append("'")
				.append(placeofassemblyvo.getFacilitydesignatedId())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getPaType())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getLocation())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getSeatingCapacity())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getDobfiling())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getDobpermit())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getDobsignoff())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getDobPermitnumber())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getDobPlan())
				.append("',");
		stringbuffer.append("'")
				.append(placeofassemblyvo.getFdPermitObtained()).append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getOpenViolation())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getViolationType())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getViolationNum())
				.append("',");
		stringbuffer.append("'").append(placeofassemblyvo.getNote())
				.append("')");

		System.out.println(stringbuffer.toString());
		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding PlaceofAssembly:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding PlaceofAssembly.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(PlaceofAssemblyVo placeofassemblyvo)
			throws TrackingException {

		String s[] = {
				"delete from placeofassemblypermit where PLACEOFASSEMBLYID=?",
				"delete from placeofassembly where PLACEOFASSEMBLYID=?" };
		for (int i = 0; i <= 1; i++) {
			StringBuffer updt = new StringBuffer();
			updt.append(s[i]);

			SqlUtil utilObj = new SqlUtil();
			utilObj.addInParam(new Integer(placeofassemblyvo.getId()));
			try {
				utilObj.execForDmlUsingQuery(updt.toString());
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"While Deleting PlaceofAssembly");
				ex.initCause(e);
				throw ex;
			}
		}

	}

	public static void update(PlaceofAssemblyVo placeofassemblyvo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(placeofassemblyvo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update PLACEOFASSEMBLY set ");
		stringbuffer.append("BUILDINGID=?,");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("PATYPE=?,");
		stringbuffer.append("LOCATION=?,");
		stringbuffer.append("SEATINGCAPACITY=?,");
		stringbuffer.append("DOBFILING=?,");
		stringbuffer.append("DOBPERMIT=?,");
		stringbuffer.append("DOBSIGNOFF=?,");
		stringbuffer.append("DOBPERMITNUMBER=?,");
		stringbuffer.append("DOBPLAN=?,");
		stringbuffer.append("FDPERMITOBTAINED=?,");
		stringbuffer.append("OPENVIOLATION=?,");
		stringbuffer.append("VIOLATIONTYPE=?,");
		stringbuffer.append("VIOLATIONNUM=?,");
		stringbuffer.append("NOTE=? ");
		stringbuffer.append("where placeofassemblyid=? ");

		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(placeofassemblyvo.getBuildingId());
		sqlutil.addInParam(placeofassemblyvo.getFacilitydesignatedId());
		sqlutil.addInParam(placeofassemblyvo.getPaType());
		sqlutil.addInParam(placeofassemblyvo.getLocation());
		sqlutil.addInParam(placeofassemblyvo.getSeatingCapacity());
		sqlutil.addInParam(placeofassemblyvo.getDobfiling());
		sqlutil.addInParam(placeofassemblyvo.getDobpermit());
		sqlutil.addInParam(placeofassemblyvo.getDobsignoff());
		sqlutil.addInParam(placeofassemblyvo.getDobPermitnumber());
		sqlutil.addInParam(placeofassemblyvo.getDobPlan());
		sqlutil.addInParam(placeofassemblyvo.getFdPermitObtained());
		sqlutil.addInParam(placeofassemblyvo.getOpenViolation());
		sqlutil.addInParam(placeofassemblyvo.getViolationType());
		sqlutil.addInParam(placeofassemblyvo.getViolationNum());
		sqlutil.addInParam(placeofassemblyvo.getNote());

		try {
			sqlutil.addInParam(placeofassemblyvo.getId());
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating PlaceofAssembly:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating PlaceofAssembly.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static class PlaceofAssemblyPermitEntity {

		public static PlaceofAssemblyPermitVo findByPrimaryKey(int id)
				throws TrackingException {
			String GET_PA = "select * from placeofassemblypermit where PLACEOFASSEMBLYPERMITID=?";
			PlaceofAssemblyPermitVo vo = null;
			try {
				SqlUtil util = new SqlUtil();
				util.addInParam(new Integer(id));
				List stackList = util.execQueryUsingConstructor(GET_PA,
						com.eespc.tracking.bo.PlaceofAssemblyPermitVo.class);
				if (stackList != null && stackList.size() > 0)
					vo = (PlaceofAssemblyPermitVo) stackList.get(0);
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"findByPrimaryKey(" + id + ")");
				ex.initCause(e);
				throw ex;
			}
			return vo;
		}

		public static List getList(int placeofassemblyId)
				throws TrackingException {
			List permitList = new ArrayList();
			StringBuffer queryBuff = new StringBuffer();
			queryBuff
					.append("select * from placeofassemblypermit where placeofassemblyid =?");
			try {
				SqlUtil util = new SqlUtil();
				util.addInParam(new Integer(placeofassemblyId));
				permitList = util.execQueryUsingConstructor(
						queryBuff.toString(),
						com.eespc.tracking.bo.PlaceofAssemblyPermitVo.class);
			} catch (Exception e) {
				TrackingException ex = new TrackingException("getList("
						+ placeofassemblyId + ")");
				ex.initCause(e);
				throw ex;
			}
			return permitList;
		}

		public static int add(PlaceofAssemblyPermitVo placeofassemblypermitVo)
				throws TrackingException {
			int generatedKey = -99;
			StringBuffer buff = new StringBuffer();
			buff.append("insert into PLACEOFASSEMBLYPERMIT ");
			buff.append("(PLACEOFASSEMBLYPERMITID,PLACEOFASSEMBLYID,ISSUEDDATE,EXPIRATIONDATE,DOBEXPNOTE)");
			buff.append(" values (null,");

			buff.append(placeofassemblypermitVo.getPlaceofassemblyId()).append(
					",");

			if (placeofassemblypermitVo.getDobIssueDate() != null)
				buff.append("'")
						.append(UtilityObject.convertToString(
								placeofassemblypermitVo.getDobIssueDate(),
								"yyyy-MM-dd")).append("',");
			else
				buff.append("null,");

			if (placeofassemblypermitVo.getDobExpirationDate() != null)
				buff.append("'")
						.append(UtilityObject.convertToString(
								placeofassemblypermitVo.getDobExpirationDate(),
								"yyyy-MM-dd")).append("',");
			else
				buff.append("null,");

			buff.append("'")
					.append(placeofassemblypermitVo.getDobExpirationNote())
					.append("'");
			buff.append(")");
			SqlUtil utilObj = new SqlUtil();
			try {
				generatedKey = utilObj.insert(buff.toString());
			} catch (Exception e) {
				System.out.println("Exception In Add PA DOB Permit:" + e);
				TrackingException ex = new TrackingException(
						"While Adding Permit Info");
				ex.initCause(e);
				throw ex;
			}
			return generatedKey;
		}

		public static void update(
				PlaceofAssemblyPermitVo placeofassemblypermitVo)
				throws TrackingException {
			StringBuffer buff = new StringBuffer();
			buff.append("update PLACEOFASSEMBLYPERMIT ");
			buff.append("set ");
			buff.append("ISSUEDDATE=?,");
			buff.append("EXPIRATIONDATE=?, ");
			buff.append("DOBEXPNOTE=?");
			buff.append(" where PLACEOFASSEMBLYPERMITID=?");
			SqlUtil utilObj = new SqlUtil();
			try {
				System.out.println("1111111111111111111.....");
				if (placeofassemblypermitVo.getDobIssueDate() != null)
					utilObj.addInParam(UtilityObject.convertToString(
							placeofassemblypermitVo.getDobIssueDate(),
							"yyyy-MM-dd"));
				else {
					utilObj.addInParam(null);
				}

				System.out.println("222222222222222222222222222.....");
				if (placeofassemblypermitVo.getDobExpirationDate() != null)
					utilObj.addInParam(UtilityObject.convertToString(
							placeofassemblypermitVo.getDobExpirationDate(),
							"yyyy-MM-dd"));
				else {
					utilObj.addInParam(null);
				}
				System.out.println("3333333333333333333333333333.....");
				utilObj.addInParam(placeofassemblypermitVo
						.getDobExpirationNote());
				System.out.println("4444444444444444444444444.....");
				utilObj.addInParam(new Integer(placeofassemblypermitVo.getId()));
				System.out.println("5555555555555555555555555555.....");
				utilObj.execForDmlUsingQuery(buff.toString());
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"While updating Permit Info");
				ex.initCause(e);
				throw ex;
			}
		}

		public static void delete(
				PlaceofAssemblyPermitVo placeofassemblypermitVo)
				throws TrackingException {
			StringBuffer buff = new StringBuffer();
			buff.append("delete from PLACEOFASSEMBLYPERMIT");
			// buff.append("set ");
			// buff.append("YEAR=?, ");
			// buff.append("PERMITNUMBER=?,");
			// buff.append("ISSUEDDATE=?,");
			// buff.append("EXPIRATIONDATE=?,");
			// buff.append("DEPID=?,");
			// buff.append("INSPSUBMITTEDDATE=?");
			buff.append(" where PLACEOFASSEMBLYPERMITID=?");
			SqlUtil utilObj = new SqlUtil();
			try {
				// utilObj.addInParam(blrConsumptionVo.getYear());
				// utilObj.addInParam(blrConsumptionVo.getPermitNumber());
				// utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getIssueDate(),
				// "yyyy-MM-dd"));
				// utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getExpirationDate(),
				// "yyyy-MM-dd"));
				// utilObj.addInParam(new Integer(blrConsumptionVo.getDepId()));
				// utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getInspecSubmittedDate(),
				// "yyyy-MM-dd"));
				utilObj.addInParam(new Integer(placeofassemblypermitVo.getId()));
				utilObj.execForDmlUsingQuery(buff.toString());
			} catch (Exception e) {
				TrackingException ex = new TrackingException(
						"While deleting Permit Info");
				ex.initCause(e);
				throw ex;
			}
		}

		public PlaceofAssemblyPermitEntity() {
		}
	}

	public static PlaceofAssemblyPermitVo findPermit(int id)
			throws TrackingException {
		return PlaceofAssemblyPermitEntity.findByPrimaryKey(id);
	}

	public static List getPermitList(int placeofassemblyId)
			throws TrackingException {
		return PlaceofAssemblyPermitEntity.getList(placeofassemblyId);
	}

	public static int addPermit(PlaceofAssemblyPermitVo obj)
			throws TrackingException {
		return PlaceofAssemblyPermitEntity.add(obj);
	}

	public static void deletePermit(PlaceofAssemblyPermitVo obj)
			throws TrackingException {
		PlaceofAssemblyPermitEntity.delete(obj);
	}

	public static void updatePermit(PlaceofAssemblyPermitVo obj)
			throws TrackingException {
		PlaceofAssemblyPermitEntity.update(obj);
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String qu = "select chr.PLACEOFASSEMBLYID, chr.FACILITYDESIGNATEDID,bldg.BUILDINGNAME from PLACEOFASSEMBLY chr, BUILDING bldg where chr.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(qu,
					com.eespc.tracking.bo.PlaceofAssemblyListVo.class);
		} catch (Exception exception) {
			System.out.println("getFacilityStackList:" + exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Printing PlaceofAssembly List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.PlaceofAssemblyEntity.class);

}