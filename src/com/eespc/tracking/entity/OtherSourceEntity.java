package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.OtherSourceVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class OtherSourceEntity {

	public OtherSourceEntity() {
	}

	public static OtherSourceVo findByPrimaryKey(int i)
			throws TrackingException {
		String s = "select * from building bld, others oth where bld.buildingid = oth.buildingid and oth.othersid =?";
		OtherSourceVo othersourcevo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.OtherSourceVo.class);
			if (list != null && list.size() > 0)
				othersourcevo = (OtherSourceVo) list.get(0);
		} catch (Exception exception) {
			System.out.println("OtherSourceVo findByPrimaryKey():" + exception);
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("findByPrimaryKey(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return othersourcevo;
	}

	public static int add(OtherSourceVo othersourcevo) throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To insert,")
					.append(othersourcevo).toString());
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into OTHERS ");
		stringbuffer
				.append("(othersid,Buildingid,FACILITYDESIGNATEDID,MODIFIEDINPAST,DISCONNECTEDYEAR,OCOMMENTS,MISNAME,MISMAKE,MISMODEL,MISCAPACITY,MISCAPACITYUNIT,MISPOLLUTANT1,MISPOLLUTANT2,MISPOLLUTANT3,MISPOLLUTANT1MAX,MISPOLLUTANT2MAX,MISPOLLUTANT3MAX,MISDEC,MISDEP,MISDOB,MISDOH,MISFD,MISOTHER,MISISSUEDATE,MISEXPIRATIONDATE,MISEXPIRATIONDATENOTE,MISMONTHLY,MISQUARTLY,MISSEMIANNUALY,MISANNUALY,MISANNIVERSARY,MISTESTINGREQUIRED,COMPLIANCEISSUE,MISTESTSUBMITTED,DOBSIGNOFF)");
		stringbuffer.append(" values (null,");
		stringbuffer.append("'").append(othersourcevo.getBuildingId())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getFacilitydesinatedId())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getModifiedInPast())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getDisconnectedYear())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getOcomments())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMisname())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMismake())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMismodel())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMiscapacity())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMiscapacityunit())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMispollutant1())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMispollutant2())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMispollutant3())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMispollutant1max())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMispollutant2max())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMispollutant3max())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMisdec()).append("',");
		stringbuffer.append("'").append(othersourcevo.getMisdep()).append("',");
		stringbuffer.append("'").append(othersourcevo.getMisdob()).append("',");
		stringbuffer.append("'").append(othersourcevo.getMisdoh()).append("',");
		stringbuffer.append("'").append(othersourcevo.getMisfd()).append("',");
		stringbuffer.append("'").append(othersourcevo.getMisother())
				.append("',");

		if (UtilityObject.isNotEmpty(othersourcevo.getMisissuedate()))
			stringbuffer.append("'").append(othersourcevo.getMisissuedate())
					.append("',");
		else
			stringbuffer.append("null,");
		if (UtilityObject.isNotEmpty(othersourcevo.getMisexpirationdate()))
			stringbuffer.append("'")
					.append(othersourcevo.getMisexpirationdate()).append("',");
		else
			stringbuffer.append("null,");
		stringbuffer.append("'")
				.append(othersourcevo.getMisexpirationdateNote()).append("',");
		stringbuffer.append("'").append(othersourcevo.getMismonthly())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMisquartly())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMissemiannualy())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMisannualy())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMisanniversary())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMistestingrequired())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getComplianceissue())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getMistestsubmitted())
				.append("',");
		stringbuffer.append("'").append(othersourcevo.getDobsignoff())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding Miscellaneous:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Miscellaneous.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static void delete(OtherSourceVo othersourcevo)
			throws TrackingException {

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from OTHERS where othersid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(othersourcevo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Deleting Miscellaneous:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Deleting Miscellaneous:");
			trackingexception.initCause(exception);
			throw trackingexception;
		}

	}

	public static void update(OtherSourceVo othersourcevo)
			throws TrackingException {
		if (log.isDebugEnabled())
			log.debug((new StringBuilder()).append("To update,")
					.append(othersourcevo).toString());
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update OTHERS set ");
		stringbuffer.append("BUILDINGID=?,");
		stringbuffer.append("FACILITYDESIGNATEDID=?,");
		stringbuffer.append("MODIFIEDINPAST=?,");
		stringbuffer.append("DISCONNECTEDYEAR=?,");
		stringbuffer.append("OCOMMENTS=?,");
		stringbuffer.append("MISNAME=?,");
		stringbuffer.append("MISMAKE=?,");
		stringbuffer.append("MISMODEL=?,");
		stringbuffer.append("MISCAPACITY=?,");
		stringbuffer.append("MISCAPACITYUNIT=?,");
		stringbuffer.append("MISPOLLUTANT1=?,");
		stringbuffer.append("MISPOLLUTANT2=?,");
		stringbuffer.append("MISPOLLUTANT3=?,");
		stringbuffer.append("MISPOLLUTANT1MAX=?,");
		stringbuffer.append("MISPOLLUTANT2MAX=?,");
		stringbuffer.append("MISPOLLUTANT3MAX=?,");
		stringbuffer.append("MISDEC=?,");
		stringbuffer.append("MISDEP=?,");
		stringbuffer.append("MISDOB=?,");
		stringbuffer.append("MISDOH=?,");
		stringbuffer.append("MISFD=?,");
		stringbuffer.append("MISOTHER=?,");
		stringbuffer.append("MISISSUEDATE=?,");
		stringbuffer.append("MISEXPIRATIONDATE=?,");
		stringbuffer.append("MISEXPIRATIONDATENOTE=?,");
		stringbuffer.append("MISMONTHLY=?,");
		stringbuffer.append("MISQUARTLY=?,");
		stringbuffer.append("MISSEMIANNUALY=?,");
		stringbuffer.append("MISANNUALY=?,");
		stringbuffer.append("MISANNIVERSARY=?,");
		stringbuffer.append("MISTESTINGREQUIRED=?,");
		stringbuffer.append("COMPLIANCEISSUE=?,");
		stringbuffer.append("MISTESTSUBMITTED=?,");
		stringbuffer.append("DOBSIGNOFF=? ");
		stringbuffer.append("where othersid=? ");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(othersourcevo.getBuildingId());
		sqlutil.addInParam(othersourcevo.getFacilitydesinatedId());
		sqlutil.addInParam(othersourcevo.getModifiedInPast());
		sqlutil.addInParam(othersourcevo.getDisconnectedYear());
		sqlutil.addInParam(othersourcevo.getOcomments());
		sqlutil.addInParam(othersourcevo.getMisname());
		sqlutil.addInParam(othersourcevo.getMismake());
		sqlutil.addInParam(othersourcevo.getMismodel());
		sqlutil.addInParam(othersourcevo.getMiscapacity());
		sqlutil.addInParam(othersourcevo.getMiscapacityunit());
		sqlutil.addInParam(othersourcevo.getMispollutant1());
		sqlutil.addInParam(othersourcevo.getMispollutant2());
		sqlutil.addInParam(othersourcevo.getMispollutant3());
		sqlutil.addInParam(othersourcevo.getMispollutant1max());
		sqlutil.addInParam(othersourcevo.getMispollutant2max());
		sqlutil.addInParam(othersourcevo.getMispollutant3max());
		sqlutil.addInParam(othersourcevo.getMisdec());
		sqlutil.addInParam(othersourcevo.getMisdep());
		sqlutil.addInParam(othersourcevo.getMisdob());
		sqlutil.addInParam(othersourcevo.getMisdoh());
		sqlutil.addInParam(othersourcevo.getMisfd());
		sqlutil.addInParam(othersourcevo.getMisother());
		if (UtilityObject.isNotEmpty(othersourcevo.getMisissuedate()))
			sqlutil.addInParam(othersourcevo.getMisissuedate());
		else
			sqlutil.addInParam(null);

		if (UtilityObject.isNotEmpty(othersourcevo.getMisexpirationdate()))
			sqlutil.addInParam(othersourcevo.getMisexpirationdate());
		else
			sqlutil.addInParam(null);
		sqlutil.addInParam(othersourcevo.getMisexpirationdateNote());
		sqlutil.addInParam(othersourcevo.getMismonthly());
		sqlutil.addInParam(othersourcevo.getMisquartly());
		sqlutil.addInParam(othersourcevo.getMissemiannualy());
		sqlutil.addInParam(othersourcevo.getMisannualy());
		sqlutil.addInParam(othersourcevo.getMisanniversary());
		sqlutil.addInParam(othersourcevo.getMistestingrequired());
		sqlutil.addInParam(othersourcevo.getComplianceissue());
		sqlutil.addInParam(othersourcevo.getMistestsubmitted());
		sqlutil.addInParam(othersourcevo.getDobsignoff());
		sqlutil.addInParam(othersourcevo.getId());
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Miscellaneous:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Miscellaneous.");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String qu = "select stk.OTHERSID, stk.FACILITYDESIGNATEDID,bldg.BUILDINGNAME from OTHERS stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(qu,
					com.eespc.tracking.bo.OtherListVo.class);
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

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.OtherSourceEntity.class);

}