package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.bo.ViolationVo;
import com.eespc.tracking.exceptions.BuildingException;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class ViolationEntity {

	public ViolationEntity() {
	}

	@SuppressWarnings("rawtypes")
	public static List getViolationInBuilding(int i, int j) {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select * from Violation where buildingid=");
		stringbuffer.append(i + " and violationWhich='" + j + "'");
		System.out.println("Query:" + stringbuffer.toString());
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.ViolationVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return ((List) (obj));
	}

	@SuppressWarnings("rawtypes")
	public static ViolationVo findByPrimaryKey(int i) {
		ViolationVo violationvo = null;
		String s = "select * from Violation where Violationid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ViolationVo.class);
			if (list != null && list.size() > 0)
				violationvo = (ViolationVo) list.get(0);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return violationvo;
	}

	public static int add(int i, ViolationVo violationvo)
			throws BuildingException {
		int k = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into violation (violationid, buildingid");

		stringbuffer
				.append(",violationWhich,violationno,violationType,description,issueDate,remedialMeassures,contractor,stepsTaken,removalDate,interMediateStatus,finalComplianceDate,jDate,otherAgency,feePaid) values ( null,");
		stringbuffer.append(i).append(",");

		stringbuffer.append("").append(violationvo.getViolationWhich())
				.append(",");

		stringbuffer.append("'").append(violationvo.getViolationno())
				.append("',");
		stringbuffer.append("'").append(violationvo.getViolationType())
				.append("',");
		stringbuffer.append("'").append(violationvo.getDescription())
				.append("',");

		if (UtilityObject.isNotEmpty(violationvo.getIssueDate()))
			stringbuffer.append("'").append(violationvo.getIssueDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(violationvo.getRemedialMeassures())
				.append("',");
		stringbuffer.append("'").append(violationvo.getContractor())
				.append("',");
		stringbuffer.append("'").append(violationvo.getStepsTaken())
				.append("',");

		if (UtilityObject.isNotEmpty(violationvo.getRemovalDate()))
			stringbuffer.append("'").append(violationvo.getRemovalDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(violationvo.getInterMediateStatus())
				.append("',");

		if (UtilityObject.isNotEmpty(violationvo.getFinalComplianceDate()))
			stringbuffer.append("'")
					.append(violationvo.getFinalComplianceDate()).append("',");
		else
			stringbuffer.append("null,");

		if (UtilityObject.isNotEmpty(violationvo.getJDate()))
			stringbuffer.append("'").append(violationvo.getJDate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(violationvo.getOtherAgency())
				.append("',");

		stringbuffer.append("'").append(violationvo.getFeePaid()).append("'");

		stringbuffer.append(")");

		SqlUtil sqlutil = new SqlUtil();
		try {
			k = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Exception:" + exception);
			throw new BuildingException(exception);
		}

		return k;
	}

	public static void update(ViolationVo violationvo)

	{

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("update Violation set violationWhich=?,violationno=?,violationType=?,description=?,issueDate=?,remedialMeassures=?,contractor=?,stepsTaken=?,removalDate=?,interMediateStatus=?,finalComplianceDate=?,jDate=?,otherAgency=?,feePaid=? ");
		stringbuffer.append(" where Violationid=?");
		SqlUtil sqlutil = new SqlUtil();

		sqlutil.addInParam(violationvo.getViolationWhich());
		sqlutil.addInParam(violationvo.getViolationno());
		sqlutil.addInParam(violationvo.getViolationType());
		sqlutil.addInParam(violationvo.getDescription());

		if (UtilityObject.isNotEmpty(violationvo.getIssueDate()))
			sqlutil.addInParam(violationvo.getIssueDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(violationvo.getRemedialMeassures());
		sqlutil.addInParam(violationvo.getContractor());
		sqlutil.addInParam(violationvo.getStepsTaken());

		if (UtilityObject.isNotEmpty(violationvo.getRemovalDate()))
			sqlutil.addInParam(violationvo.getRemovalDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(violationvo.getInterMediateStatus());

		if (UtilityObject.isNotEmpty(violationvo.getFinalComplianceDate()))
			sqlutil.addInParam(violationvo.getFinalComplianceDate());
		else
			sqlutil.addInParam(null);

		if (UtilityObject.isNotEmpty(violationvo.getJDate()))
			sqlutil.addInParam(violationvo.getJDate());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(violationvo.getOtherAgency());

		sqlutil.addInParam(violationvo.getFeePaid());

		sqlutil.addInParam(new Integer(violationvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Violation Update Exception:" + exception);
		}

	}

	public static void delete(ViolationVo violationvo) {

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from Violation where  violationid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(violationvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Deleting Violation:" + exception);

		}

	}

	@SuppressWarnings("rawtypes")
	public static List getFacilityViolationList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select vio.violationid, vio.violationno,vio.violationWhich,vio.violationType, bldg.BUILDINGNAME from violation vio, BUILDING bldg where vio.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.ViolationListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Violation List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return (List) (List) obj;
	}

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.ViolationEntity.class);

}