package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.StackVo;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;

public class StackEntity {

	public StackEntity() {
	}

	public static StackVo findByPrimaryKey(int i) throws TrackingException {
		String s = "select * from stack where stackid=?";
		StackVo stackvo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.StackVo.class);
			if (list != null && list.size() > 0)
				stackvo = (StackVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					"Getting Address");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return stackvo;
	}

	public static int add(StackVo stackvo) throws TrackingException {
		int i = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into stack values (null,");
		stringbuffer.append(stackvo.getBuildingId()).append(",");
		stringbuffer.append("'").append(stackvo.getFloor()).append("',");
		stringbuffer.append(stackvo.getHeight()).append(",");
		stringbuffer.append(stackvo.getDiameter()).append(",");
		stringbuffer.append(stackvo.getFlowRate()).append(",");
		stringbuffer.append(stackvo.getExhaustTemp()).append(",");
		stringbuffer.append(stackvo.getVelocity()).append(",");
		stringbuffer.append("'").append(stackvo.getDecEmissionPointId())
				.append("',");
		stringbuffer.append("'").append(stackvo.getYearInstalled())
				.append("',");
		stringbuffer.append("'").append(stackvo.getFacilityStackId())
				.append("',");
		stringbuffer.append("'").append(stackvo.getNoofsource()).append("',");
		stringbuffer.append("'").append(stackvo.getTotalcapacity())
				.append("',");
		stringbuffer.append("'").append(stackvo.getTypeoffuel()).append("',");
		/*
		 * stringbuffer.append(stackvo.getModifiedInPast()).append(",");
		 * stringbuffer
		 * .append("'").append(stackvo.getDisconnectedYear()).append("',");
		 */
		stringbuffer.append("'" + stackvo.isMethodNineTest() + "'").append(",");
		if (isNotEmpty(stackvo.getMethodNineLastDate()))
			stringbuffer.append("'").append(stackvo.getMethodNineLastDate())
					.append("',");
		else
			stringbuffer.append("null,");

		if (isNotEmpty(stackvo.getMethodNineNextTestDate()))
			stringbuffer.append("'")
					.append(stackvo.getMethodNineNextTestDate()).append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'" + stackvo.isOpacityLimit() + "')");
		// stringbuffer.append("'").append(stackvo.getFacilityDesignatedId()).append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			i = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Adding Stack:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Adding Stack");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return i;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static void update(StackVo stackvo) throws TrackingException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update stack set ");
		stringbuffer
				.append("FLOOR=?, HEIGHT=?, DIAMETER=?, FLOWRATE=?, EXHAUSTTEMP=?, VELOCITY=?,");
		stringbuffer
				.append("DECEMISSIONPOINTID=?,YEARINSTALLED=?,FACILITYSTACKID=?, NOOFSOURCE=?,CAPACITY=?,TYPEOFFUEL=?,");
		stringbuffer
				.append("methodNineTest=? ,methodNineLastDate=?,methodNineNextTestDate=?,");
		stringbuffer.append("opacityLimit=? where stackid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(stackvo.getFloor());
		sqlutil.addInParam(new Double(stackvo.getHeight()));
		sqlutil.addInParam(new Double(stackvo.getDiameter()));
		sqlutil.addInParam(new Double(stackvo.getFlowRate()));
		sqlutil.addInParam(new Double(stackvo.getExhaustTemp()));
		sqlutil.addInParam(new Double(stackvo.getVelocity()));
		sqlutil.addInParam(stackvo.getDecEmissionPointId());
		sqlutil.addInParam(stackvo.getYearInstalled());
		sqlutil.addInParam(stackvo.getFacilityStackId());
		sqlutil.addInParam(stackvo.getNoofsource());
		sqlutil.addInParam(stackvo.getTotalcapacity());
		sqlutil.addInParam(stackvo.getTypeoffuel());
		/*
		 * sqlutil.addInParam(String.valueOf(stackvo.getModifiedInPast()));
		 * sqlutil.addInParam(stackvo.getDisconnectedYear());
		 */
		sqlutil.addInParam(stackvo.isMethodNineTest());

		if (isNotEmpty(stackvo.getMethodNineLastDate())) {
			sqlutil.addInParam(stackvo.getMethodNineLastDate());
		} else {
			sqlutil.addInParam(null);
		}

		if (isNotEmpty(stackvo.getMethodNineNextTestDate())) {
			sqlutil.addInParam(stackvo.getMethodNineNextTestDate());
		} else {
			sqlutil.addInParam(null);
		}
		sqlutil.addInParam(stackvo.isOpacityLimit());
		// sqlutil.addInParam(stackvo.getFacilityDesignatedId());
		sqlutil.addInParam(new Integer(stackvo.getStackId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Updating Stack:" + exception);
			TrackingException trackingexception = new TrackingException(
					"While Updating Stack");
			trackingexception.initCause(exception);
			throw trackingexception;
		}
	}

	public static void delete(StackVo stackvo) throws TrackingException {
		String as[] = { "delete from stackpermitinfo where stackid=?",
				"delete from stack where stackid=?" };
		for (int i = 0; i < 2; i++) {
			StringBuffer stringbuffer = new StringBuffer();
			stringbuffer.append(as[i]);
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(stackvo.getStackId()));
			try {
				sqlutil.execForDmlUsingQuery(stringbuffer.toString());
				System.out.println((new StringBuilder()).append("hhhh22222")
						.append(new Integer(stackvo.getStackId())).toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException(
						"While Updating Stack");
				trackingexception.initCause(exception);
				throw trackingexception;
			}
		}

	}

	/*
	 * public static int addPermit(PermitInfoVo permitinfovo) throws
	 * TrackingException { int i = -99; StringBuffer stringbuffer = new
	 * StringBuffer(); stringbuffer.append("insert into stackpermitinfo ");
	 * stringbuffer.append("(stackpermitid, stackid, permitnumber, ");
	 * stringbuffer.append("issueddate, expirationdate, depid) ");
	 * stringbuffer.append(" values (null,");
	 * stringbuffer.append(permitinfovo.getObjectId()).append(",");
	 * stringbuffer.
	 * append("'").append(permitinfovo.getPermitNumber()).append("',");
	 * stringbuffer
	 * .append("'").append(UtilityObject.convertToString(permitinfovo
	 * .getIssueDate(), "yyyy-MM-dd")).append("',");
	 * stringbuffer.append("'").append
	 * (UtilityObject.convertToString(permitinfovo.getExpirationDate(),
	 * "yyyy-MM-dd")).append("',");
	 * stringbuffer.append(permitinfovo.getDepId()).append(")"); SqlUtil sqlutil
	 * = new SqlUtil(); try { i = sqlutil.insert(stringbuffer.toString()); }
	 * catch(Exception exception) { TrackingException trackingexception = new
	 * TrackingException("While Adding Stack Permit Info");
	 * trackingexception.initCause(exception); throw trackingexception; } return
	 * i; }
	 * 
	 * public static List getPermitInfo(int i) throws TrackingException { Object
	 * obj = new ArrayList(); String s =
	 * "select stackpermitid as PERMIT_ID, stackid as OBJECT_ID,  1 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issueddate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, depid as DEPT_ID from stackpermitinfo where stackid =?"
	 * ; try { SqlUtil sqlutil = new SqlUtil(); sqlutil.addInParam(new
	 * Integer(i)); obj = sqlutil.execQueryUsingConstructor(s,
	 * com.eespc.tracking.bo.PermitInfoVo.class); } catch(Exception exception) {
	 * TrackingException trackingexception = new
	 * TrackingException("Getting Permit Info list");
	 * trackingexception.initCause(exception); throw trackingexception; } return
	 * ((List) (obj)); }
	 * 
	 * public static void updatePermit(PermitInfoVo permitinfovo) throws
	 * TrackingException { byte byte0 = -99; StringBuffer stringbuffer = new
	 * StringBuffer(); stringbuffer.append("update stackpermitinfo ");
	 * stringbuffer.append("set permitnumber=?, ");
	 * stringbuffer.append("issueddate=?, expirationdate=?,depid=? ");
	 * stringbuffer.append(" where stackpermitid=?"); SqlUtil sqlutil = new
	 * SqlUtil(); try { sqlutil.addInParam(permitinfovo.getPermitNumber());
	 * sqlutil
	 * .addInParam(UtilityObject.convertToString(permitinfovo.getIssueDate(),
	 * "yyyy-MM-dd"));
	 * sqlutil.addInParam(UtilityObject.convertToString(permitinfovo
	 * .getExpirationDate(), "yyyy-MM-dd")); sqlutil.addInParam(new
	 * Integer(permitinfovo.getDepId())); sqlutil.addInParam(new
	 * Integer(permitinfovo.getId()));
	 * sqlutil.execForDmlUsingQuery(stringbuffer.toString()); } catch(Exception
	 * exception) { TrackingException trackingexception = new
	 * TrackingException("While updating stack storage Permit Info");
	 * trackingexception.initCause(exception); throw trackingexception; } }
	 * 
	 * public static void deletePermit(PermitInfoVo permitinfovo) throws
	 * TrackingException { byte byte0 = -99; StringBuffer stringbuffer = new
	 * StringBuffer(); stringbuffer.append("delete from stackpermitinfo");
	 * stringbuffer.append(" where stackpermitid=?"); SqlUtil sqlutil = new
	 * SqlUtil(); try { sqlutil.addInParam(new Integer(permitinfovo.getId()));
	 * sqlutil.execForDmlUsingQuery(stringbuffer.toString()); } catch(Exception
	 * exception) { TrackingException trackingexception = new
	 * TrackingException("While updating stack storage Permit Info");
	 * trackingexception.initCause(exception); throw trackingexception; } }
	 */

	public static List getFacilityStackList(int i) throws TrackingException {
		Object obj = new ArrayList();
		String s = "select stk.STACKID, stk.DECEMISSIONPOINTID, stk.FACILITYSTACKID, stk.HEIGHT, stk.DIAMETER, bldg.BUILDINGNAME from STACK stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.StackListVo.class);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder())
							.append("Getting Stack List for facility id =")
							.append(i).toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return ((List) (obj));
	}

	/*
	 * public static PermitInfoVo getPermit(int i) throws TrackingException {
	 * PermitInfoVo permitinfovo = null; String s =
	 * "select stackpermitid as PERMIT_ID, stackid as OBJECT_ID,  1 as OBJECT_TYPE, permitnumber as PERMIT_NUMBER, issueddate as PERMIT_ISSUE_DATE, expirationdate as PERMIT_EXP_DATE, depid as DEPT_ID from stackpermitinfo where stackpermitid =?"
	 * ; try { SqlUtil sqlutil = new SqlUtil(); sqlutil.addInParam(new
	 * Integer(i)); List list = sqlutil.execQueryUsingConstructor(s,
	 * com.eespc.tracking.bo.PermitInfoVo.class); if(list != null && list.size()
	 * > 0) permitinfovo = (PermitInfoVo)list.get(0); } catch(Exception
	 * exception) { TrackingException trackingexception = new
	 * TrackingException((new
	 * StringBuilder()).append("getPermit(").append(i).append(")").toString());
	 * trackingexception.initCause(exception); throw trackingexception; } return
	 * permitinfovo; }
	 */
}