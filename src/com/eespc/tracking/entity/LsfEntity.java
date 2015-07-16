package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.bo.LsfVo;
import com.eespc.tracking.exceptions.BuildingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class LsfEntity {

	public LsfEntity() {
	}

	static void insertlsfToEq(LsfVo lsfvo, int i) {
		String as1[] = lsfvo.getOperatingequipments();
		String as2[] = lsfvo.getHtypeofcoffrequired();

		if (as1 != null) {
			int j = as1.length;
			for (int k = 0; k < j; k++) {
				String s = as1[k];
				if (!UtilityObject.isNotEmpty(s))
					continue;
				StringBuffer stringbuffer = new StringBuffer();
				stringbuffer
						.append("insert into lsfoperatingequipments (type, operatingequipments,lsfid) values (1,");
				stringbuffer.append(s);
				stringbuffer.append(",");
				stringbuffer.append(i);
				stringbuffer.append(")");
				SqlUtil sqlutil = new SqlUtil();
				try {
					sqlutil.insert(stringbuffer.toString());
				} catch (Exception exception) {
					logger.error(exception);
				}
			}

		}

		if (as2 != null) {
			int j = as2.length;
			for (int k = 0; k < j; k++) {
				String s = as2[k];
				if (!UtilityObject.isNotEmpty(s))
					continue;
				StringBuffer stringbuffer = new StringBuffer();
				stringbuffer
						.append("insert into lsfoperatingequipments (type, operatingequipments,lsfid) values (2,");
				stringbuffer.append(s);
				stringbuffer.append(",");
				stringbuffer.append(i);
				stringbuffer.append(")");
				SqlUtil sqlutil = new SqlUtil();
				try {
					sqlutil.insert(stringbuffer.toString());
				} catch (Exception exception) {
					logger.error(exception);
				}
			}

		}
	}

	public static List getLfsInFacility(int i) {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select * from lsf where facilityid=");
		stringbuffer.append(i);
		System.out.println("Query:" + stringbuffer.toString());

		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.LsfVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return ((List) (obj));
	}

	public static LsfVo findByPrimaryKey(int i) {
		LsfVo lsfvo = null;
		String s = "select * from lsf where lsfid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.LsfVo.class);
			if (list != null && list.size() > 0)
				lsfvo = (LsfVo) list.get(0);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return lsfvo;
	}

	public static int add(int i, LsfVo lsfvo) throws BuildingException {
		int k = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into lsf (lsfid, facilityid");

		stringbuffer
				.append(",firstname,lastname,certificatenumber,phonenumber,department,issuedate,expirationdate,equipmentoperated) values ( null,");
		stringbuffer.append(i).append(",");

		stringbuffer.append("'").append(lsfvo.getFirstname()).append("',");
		stringbuffer.append("'").append(lsfvo.getLastname()).append("',");
		stringbuffer.append("'").append(lsfvo.getCertificatenumber())
				.append("',");
		stringbuffer.append("'").append(lsfvo.getPhonenumber()).append("',");
		stringbuffer.append("'").append(lsfvo.getDepartment()).append("',");

		if (UtilityObject.isNotEmpty(lsfvo.getIssuedate()))
			stringbuffer.append("'").append(lsfvo.getIssuedate()).append("',");
		else
			stringbuffer.append("null,");

		if (UtilityObject.isNotEmpty(lsfvo.getExpirationdate()))
			stringbuffer.append("'").append(lsfvo.getExpirationdate())
					.append("',");
		else
			stringbuffer.append("null,");

		// stringbuffer.append("'").append(lsfvo.getOperatingequipments()).append("',");
		stringbuffer.append("'").append(lsfvo.getEquipmentoperated())
				.append("'");
		// stringbuffer.append("'").append(lsfvo.getHtypeofcoffrequired()).append("',");
		stringbuffer.append(")");

		SqlUtil sqlutil = new SqlUtil();
		try {
			k = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Exception:" + exception);
			throw new BuildingException(exception);
		}

		try {

			if (k > 0) {
				insertlsfToEq(lsfvo, k);
			}
		} catch (Exception exception) {
			System.out.println("" + exception);

		}

		return k;
	}

	public static void update(LsfVo lsfvo) {
		deletelsfoperatingequipments(lsfvo);
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("update lsf set firstname=?,lastname=?,certificatenumber=?,phonenumber=?,department=?,issuedate=?,expirationdate=?,equipmentoperated=? ");
		stringbuffer.append(" where Lsfid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(lsfvo.getFirstname());
		sqlutil.addInParam(lsfvo.getLastname());
		sqlutil.addInParam(lsfvo.getCertificatenumber());
		sqlutil.addInParam(lsfvo.getPhonenumber());
		sqlutil.addInParam(lsfvo.getDepartment());

		if (UtilityObject.isNotEmpty(lsfvo.getIssuedate()))
			sqlutil.addInParam(lsfvo.getIssuedate());
		else
			sqlutil.addInParam(null);

		if (UtilityObject.isNotEmpty(lsfvo.getExpirationdate()))
			sqlutil.addInParam(lsfvo.getExpirationdate());
		else
			sqlutil.addInParam(null);

		// sqlutil.addInParam(lsfvo.getOperatingequipments());
		sqlutil.addInParam(lsfvo.getEquipmentoperated());
		// sqlutil.addInParam(lsfvo.getHtypeofcoffrequired());

		sqlutil.addInParam(new Integer(lsfvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Lsf Update Exception:" + exception);
		}

		insertlsfToEq(lsfvo, lsfvo.getId());
	}

	public static void delete(LsfVo lsfvo) {
		deletelsfoperatingequipments(lsfvo);
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from Lsf where Lsfid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(lsfvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Deleting LFS:" + exception);

		}

	}

	public static void deletelsfoperatingequipments(LsfVo lsfvo) {

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from lsfoperatingequipments where Lsfid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(lsfvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Deleting LFS:" + exception);

		}

	}

	public static List getcoffrequiredList(int a, int b) {
		List result;

		StringBuffer query = new StringBuffer();
		query.append("select * from lsfoperatingequipments where lsfid=? and type=?  ");
		SqlUtil utilObj = new SqlUtil();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;

		result = new ArrayList();

		try {
			conn = utilObj.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, a);
			pstmt.setInt(2, b);
			rst = pstmt.executeQuery();

			while (rst.next()) {
				result.add(rst.getString("operatingequipments"));

			}

		} catch (Exception e) {
			System.out.println(" Exception in Entity:" + e);

		} finally {
			SqlUtil.close(rst);
			SqlUtil.close(pstmt);
			SqlUtil.close(conn);
		}

		return result;
	}

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.LsfEntity.class);

}