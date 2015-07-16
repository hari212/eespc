package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.Constants;
import com.eespc.tracking.bo.ContactVo;
import com.eespc.tracking.bo.DropDown;
import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.NameValueBean;
import com.eespc.tracking.bo.StatepermitVo;
import com.eespc.tracking.exceptions.FacilityException;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class FacilityEntity {

	public FacilityEntity() {
	}

	public static List getFuelConsumptionList(int facilityid)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumption(facilityid, 123);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumption(facilityid, 123);
	}

	public static List getFuelConsumptionyearList(int facilityid, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.getFuelConsumptionyear(facilityid, 123,
					year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.getFuelConsumptionyear(facilityid, 123,
				year);
	}

	public static List geto_FuelConsumptionyearList(int facilityid, String year)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.geto_FuelConsumptionyear(facilityid,
					123, year);
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}

		return FuelConsumptionEntity.geto_FuelConsumptionyear(facilityid, 123,
				year);
	}

	public static List geto_FuelConsumptionList(int facilityid)
			throws TrackingException {

		try {
			List xx = null;
			xx = FuelConsumptionEntity.geto_FuelConsumption(facilityid, 123);
		} catch (Exception e) {
		}

		return FuelConsumptionEntity.geto_FuelConsumption(facilityid, 123);
	}

	public static int addFuelConsumption(FuelConsumptionVo vo)
			throws TrackingException {
		return FuelConsumptionEntity.add(vo, 123);
	}

	public static void updateFuelConsumption(FuelConsumptionVo vo)
			throws TrackingException {
		FuelConsumptionEntity.update(vo, 123);
	}

	public static void deletefuelconsumption(int id) throws TrackingException {
		StringBuffer buff = new StringBuffer();
		buff.append("delete from facilityfuelconsumption");
		buff.append(" where FACANNFUELCONSID=?");
		SqlUtil utilObj = new SqlUtil();
		try {
			utilObj.addInParam(id);
			utilObj.execForDmlUsingQuery(buff.toString());
		} catch (Exception e) {
			TrackingException ex = new TrackingException(
					"While updating annualcons Info");
			ex.initCause(e);
			throw ex;
		}

	}

	public static List getMasterReport(int ftype, int facilityid) {
		List result;
		LinkedHashMap hash4Unique;
		result = new ArrayList();
		StringBuffer query = new StringBuffer();
		query.append("select fac.facilityid,fac.DECID,fac.CLIENTNAME,per.fstatus,per.fexpirationdate,per.fjan,per.ffeb	,per.fmar,per.fapril,per.fmay,per.fjune,per.fjuly,per.faug,per.fsep,per.foct,per.fnov,per.fdec,per.flastproposal,per.fnextproposal,per.fcomments ");
		query.append(" from facility fac left join statepermit per on per.facilityid=fac.facilityid where fac.FACILITYTYPE=? and fac.facilityid in (select facilityid from usertofacility where userid=?)");
		SqlUtil utilObj = new SqlUtil();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rst = null;
		hash4Unique = new LinkedHashMap();
		long startTime = System.currentTimeMillis();
		try {
			conn = utilObj.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setInt(1, ftype);
			pstmt.setInt(2, facilityid);
			rst = pstmt.executeQuery();
			startTime = System.currentTimeMillis();
			Hashtable tempHash;
			String tempfacilityId;
			for (; rst.next(); hash4Unique.put(tempfacilityId, tempHash)) {
				tempHash = new Hashtable();
				tempfacilityId = rst.getString("facilityid");
				tempHash.put("facilityid", tempfacilityId);
				tempHash.put("decid", UtilityObject.checkNullAndFill(
						rst.getString("DECID"), ""));
				tempHash.put(
						"clientname",
						UtilityObject.checkNullAndFill(
								rst.getString("CLIENTNAME"), ""));
				tempHash.put("fstatus", UtilityObject.checkNullAndFill(
						rst.getString("fstatus"), ""));
				String due = "";
				java.util.Date expi = null;
				java.util.Date last = null;

				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				Calendar cal3 = Calendar.getInstance();

				String expirationdate = rst.getString("fexpirationdate");

				if (UtilityObject.isNotEmpty(expirationdate)) {
					expi = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(expirationdate);
					cal1.setTime(expi);
					cal3.setTime(expi);

					cal1.add(Calendar.MONTH, -6);
					cal3.add(Calendar.MONTH, -18);
					due = (cal3.get(Calendar.MONTH) + 1) + "/"
							+ (cal3.get(Calendar.DATE) + 1) + "/"
							+ cal3.get(Calendar.YEAR) + " - "
							+ (cal1.get(Calendar.MONTH) + 1) + "/"
							+ cal1.get(Calendar.DATE) + "/"
							+ cal1.get(Calendar.YEAR);

				} else {
					due = "";
				}

				tempHash.put("fdue", due);

				tempHash.put("fexpirationdate", UtilityObject.checkNullAndFill(
						UtilityObject.convertYyyyMmDd2MmDdYyyy(expirationdate),
						""));
				tempHash.put("fjan", UtilityObject.checkNullAndFill(
						rst.getString("fjan"), ""));
				tempHash.put("ffeb", UtilityObject.checkNullAndFill(
						rst.getString("ffeb"), ""));
				tempHash.put("fmar", UtilityObject.checkNullAndFill(
						rst.getString("fmar"), ""));
				tempHash.put("fapril", UtilityObject.checkNullAndFill(
						rst.getString("fapril"), ""));
				tempHash.put("fmay", UtilityObject.checkNullAndFill(
						rst.getString("fmay"), ""));
				tempHash.put("fjune", UtilityObject.checkNullAndFill(
						rst.getString("fjune"), ""));
				tempHash.put("fjuly", UtilityObject.checkNullAndFill(
						rst.getString("fjuly"), ""));
				tempHash.put("faug", UtilityObject.checkNullAndFill(
						rst.getString("faug"), ""));
				tempHash.put("fsep", UtilityObject.checkNullAndFill(
						rst.getString("fsep"), ""));
				tempHash.put("foct", UtilityObject.checkNullAndFill(
						rst.getString("foct"), ""));
				tempHash.put("fnov", UtilityObject.checkNullAndFill(
						rst.getString("fnov"), ""));
				tempHash.put("fdec", UtilityObject.checkNullAndFill(
						rst.getString("fdec"), ""));

				String lastproposal = rst.getString("flastproposal");
				String nextproposal = "";

				if (UtilityObject.isNotEmpty(lastproposal)) {
					last = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(lastproposal);
					cal2.setTime(last);
					cal2.add(Calendar.MONTH, 12);
					nextproposal = (cal2.get(Calendar.MONTH) + 1) + "/"
							+ (cal2.get(Calendar.DATE) - 1) + "/"
							+ cal2.get(Calendar.YEAR);
				} else {
					nextproposal = "";
				}

				tempHash.put("flastproposal", UtilityObject.checkNullAndFill(
						UtilityObject.convertYyyyMmDd2MmDdYyyy(lastproposal),
						""));
				tempHash.put("fnextproposal", nextproposal);
				tempHash.put(
						"fcomments",
						UtilityObject.checkNullAndFill(
								rst.getString("fcomments"), ""));

			}

		} catch (Exception e) {
			System.out.println("Master Report  Exception in Facility Entity:"
					+ e);

		} finally {

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

	public static int addStatepermit(StatepermitVo permitvo)
			throws FacilityException {
		if (permitvo == null)
			throw new IllegalArgumentException("StateperitVo cannot be NULL.");
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into statepermit ( ");
		stringbuffer.append("masterid, facilityid, fstatus, fexpirationdate, ");
		stringbuffer
				.append("fjan, ffeb, fmar, fapril, fmay, fjune,fjuly,faug,fsep,foct,fnov,fdec,flastproposal,fnextproposal,fcomments) values (null,");
		stringbuffer.append(permitvo.getFacilityId()).append(",");
		stringbuffer.append("'").append(permitvo.getFstatus()).append("',");

		if (permitvo.getFexpirationdate() != null
				&& !permitvo.getFexpirationdate().equals(""))
			stringbuffer.append("'").append(permitvo.getFexpirationdate())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(permitvo.getFjan()).append("',");
		stringbuffer.append("'").append(permitvo.getFfeb()).append("',");
		stringbuffer.append("'").append(permitvo.getFmar()).append("',");
		stringbuffer.append("'").append(permitvo.getFapril()).append("',");
		stringbuffer.append("'").append(permitvo.getFmay()).append("',");
		stringbuffer.append("'").append(permitvo.getFjune()).append("',");
		stringbuffer.append("'").append(permitvo.getFjuly()).append("',");
		stringbuffer.append("'").append(permitvo.getFaug()).append("',");
		stringbuffer.append("'").append(permitvo.getFsep()).append("',");
		stringbuffer.append("'").append(permitvo.getFoct()).append("',");
		stringbuffer.append("'").append(permitvo.getFnov()).append("',");
		stringbuffer.append("'").append(permitvo.getFdec()).append("',");

		if (permitvo.getFlastproposal() != null
				&& !permitvo.getFlastproposal().equals(""))
			stringbuffer.append("'").append(permitvo.getFlastproposal())
					.append("',");
		else
			stringbuffer.append("null,");

		if (permitvo.getFnextproposal() != null
				&& !permitvo.getFnextproposal().equals(""))
			stringbuffer.append("'").append(permitvo.getFnextproposal())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(permitvo.getFcomments()).append("'");

		stringbuffer.append(")");

		SqlUtil sqlutil = new SqlUtil();
		int j;
		System.out.println("Query:::::::::::::" + stringbuffer.toString());
		try {
			j = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			throw new FacilityException(exception);
		}
		return j;
	}

	public static void updatestateprmit(StatepermitVo permitvo)
			throws TrackingException {
		StringBuffer buff = new StringBuffer();
		buff.append("update statepermit ");
		buff.append("set ");
		buff.append("fstatus=?, fexpirationdate=?, ");
		buff.append("fjan=?, ffeb=?, fmar=?, fapril=?, fmay=?, fjune=?,fjuly=?,faug=?,fsep=?,foct=?,fnov=?,fdec=?,flastproposal=?,fnextproposal=?,fcomments=?");
		buff.append(" where masterid=?");
		SqlUtil utilObj = new SqlUtil();
		try {
			utilObj.addInParam(permitvo.getFstatus());

			if (permitvo.getFexpirationdate() != null
					&& !permitvo.getFexpirationdate().equals(""))
				utilObj.addInParam(permitvo.getFexpirationdate());
			else
				utilObj.addInParam(null);

			utilObj.addInParam(permitvo.getFjan());
			utilObj.addInParam(permitvo.getFfeb());
			utilObj.addInParam(permitvo.getFmar());
			utilObj.addInParam(permitvo.getFapril());
			utilObj.addInParam(permitvo.getFmay());
			utilObj.addInParam(permitvo.getFjune());
			utilObj.addInParam(permitvo.getFjuly());
			utilObj.addInParam(permitvo.getFaug());
			utilObj.addInParam(permitvo.getFsep());
			utilObj.addInParam(permitvo.getFoct());
			utilObj.addInParam(permitvo.getFnov());
			utilObj.addInParam(permitvo.getFdec());

			if (permitvo.getFlastproposal() != null
					&& !permitvo.getFlastproposal().equals(""))
				utilObj.addInParam(permitvo.getFlastproposal());
			else
				utilObj.addInParam(null);

			if (permitvo.getFnextproposal() != null
					&& !permitvo.getFnextproposal().equals(""))
				utilObj.addInParam(permitvo.getFnextproposal());
			else
				utilObj.addInParam(null);

			utilObj.addInParam(permitvo.getFcomments());
			utilObj.addInParam(permitvo.getId());
			utilObj.execForDmlUsingQuery(buff.toString());

		} catch (Exception e) {
			System.out.println("Error in Updating Statepermit tuneup:" + e);
			TrackingException ex = new TrackingException(
					"While updating Statepermit Info");
			ex.initCause(e);
			throw ex;
		}
	}

	public static void deletesatatepermit(int pid) throws TrackingException {

		StringBuffer buff = new StringBuffer();
		buff.append("delete from statepermit");
		buff.append(" where masterid=?");
		SqlUtil utilObj = new SqlUtil();
		try {

			utilObj.addInParam(pid);
			utilObj.execForDmlUsingQuery(buff.toString());
		} catch (Exception e) {
			TrackingException ex = new TrackingException(
					"While Deleting satepermit Info");
			ex.initCause(e);
			throw ex;
		}
	}

	public static List getstatepermitList(int facilityid)
			throws TrackingException {
		List permitList = new ArrayList();
		StringBuffer queryBuff = new StringBuffer();
		queryBuff.append("select * from statepermit where facilityid=?");
		try {
			SqlUtil util = new SqlUtil();
			util.addInParam(new Integer(facilityid));
			permitList = util.execQueryUsingConstructor(queryBuff.toString(),
					com.eespc.tracking.bo.StatepermitVo.class);
		} catch (Exception e) {
			System.out.println("Error in List State permit." + e);
			TrackingException ex = new TrackingException("getList("
					+ facilityid + ")");
			ex.initCause(e);
			throw ex;
		}
		return permitList;
	}

	public static int addNewFacility(int i, FacilityVo facilityvo)
			throws FacilityException {
		if (facilityvo == null)
			throw new IllegalArgumentException("FacilityVo cannot be NULL.");
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into facility ( ");
		stringbuffer.append("facilityid, addressid, clientname, decid, ");
		stringbuffer
				.append("facilitytype, borough, vicePresident, validind, phone, fax,county,town,FUELCAPING,OILCAP,GASCAP,numofbldg) values (null,");
		stringbuffer.append(i).append(",");
		stringbuffer.append("'").append(facilityvo.getClientName());
		stringbuffer.append("'").append(",");
		stringbuffer.append("'").append(facilityvo.getDecId());
		stringbuffer.append("'").append(",");
		stringbuffer.append(facilityvo.getFacilityType()).append(",");
		stringbuffer.append(facilityvo.getBorough());
		stringbuffer.append(",").append("'");
		stringbuffer.append(facilityvo.getVicePresident());
		stringbuffer.append("'").append(",");
		stringbuffer.append("'Y'").append(",");
		stringbuffer.append("'").append(facilityvo.getPhone()).append("'")
				.append(",");
		stringbuffer.append("'").append(facilityvo.getFax()).append("',");
		stringbuffer.append("'").append(facilityvo.getCounty()).append("',");
		stringbuffer.append("'").append(facilityvo.getTown()).append("',");
		stringbuffer.append("'").append(facilityvo.getFuelcaping())
				.append("',");
		stringbuffer.append("'").append(facilityvo.getOilcap()).append("',");
		stringbuffer.append("'").append(facilityvo.getGascap()).append("',");
		stringbuffer.append("'").append(facilityvo.getNumofbldg()).append("'");
		stringbuffer.append(")");

		SqlUtil sqlutil = new SqlUtil();
		int j;
		try {
			j = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			throw new FacilityException(exception);
		}
		return j;
	}

	public static void updateFacility(FacilityVo facilityvo)
			throws FacilityException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update facility set clientname = ?, decid = ?,");
		stringbuffer
				.append("facilityType = ?, borough =?, vicePresident=?, phone=?, fax=?,county=?,town=?,FUELCAPING=?,OILCAP=?,GASCAP=?,numofbldg=? where facilityid =?");
		SqlUtil sqlutil = new SqlUtil();
		if (facilityvo.getClientName() != null)
			sqlutil.addInParam(new String(facilityvo.getClientName()));
		else
			sqlutil.addInParam(null);
		if (facilityvo.getDecId() != null)
			sqlutil.addInParam(new String(facilityvo.getDecId()));
		else
			throw new FacilityException("DEC ID is null.");
		sqlutil.addInParam(new Integer(facilityvo.getFacilityType()));
		sqlutil.addInParam(new Integer(facilityvo.getBorough()));
		if (facilityvo.getVicePresident() != null)
			sqlutil.addInParam(new String(facilityvo.getVicePresident()));
		else
			sqlutil.addInParam(null);
		if (UtilityObject.isNotEmpty(facilityvo.getPhone()))
			sqlutil.addInParam(facilityvo.getPhone());
		else
			sqlutil.addInParam(null);
		if (UtilityObject.isNotEmpty(facilityvo.getFax()))
			sqlutil.addInParam(facilityvo.getFax());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(new String(facilityvo.getCounty()));
		sqlutil.addInParam(new String(facilityvo.getTown()));
		sqlutil.addInParam(facilityvo.getFuelcaping());
		sqlutil.addInParam(facilityvo.getOilcap());
		sqlutil.addInParam(facilityvo.getGascap());
		sqlutil.addInParam(facilityvo.getNumofbldg());
		sqlutil.addInParam(new Integer(facilityvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new FacilityException(exception);
		}
		updateNewContact(facilityvo.getId());

	}

	public static void updateNewContact(int i) throws FacilityException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("update contact set VALID_IND = ? where CONTACTID IN (select CONTACTID FROM CONTACTTOFACILITY WHERE FACILITYID = ?)");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new String("N"));
		sqlutil.addInParam(new Integer(i));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new FacilityException(exception);
		}
	}

	public static void deleteFacility(int i) throws FacilityException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("update facility set validind = ? where facilityid =?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new String("N"));
		sqlutil.addInParam(new Integer(i));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new FacilityException(exception);
		}
	}

	public static void delete(FacilityVo facilityvo) throws FacilityException {
		List list = facilityvo.getContactList();
		int i = list != null ? list.size() : -99;
		int ai[] = new int[i];
		int ai1[] = new int[i];
		for (int j = 0; j < i; j++) {
			ContactVo contactvo = (ContactVo) list.get(j);
			AddressVo addressvo = contactvo.getAddress();
			ai1[j] = contactvo.getContactId();
			ai[j] = addressvo.getId();
		}

		String as[] = {
				"delete a from (chillerabsorberpermitinfo a,chillerabsorber b,building c) where a.CHILLERABSORBERID=b.CHILLERABSORBERID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (chillerabsorberfuelconsumption a,chillerabsorber b,building c) where a.CHILLERABSORBERID=b.CHILLERABSORBERID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (chillerabsorber a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (boilerpermitinfo a,boiler b,building c) where a.BOILERID=b.BOILERID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (boilerfuelconsumption a,boiler b,building c) where a.BOILERID=b.BOILERID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (annualtuneup a,boiler b,building c) where a.BOILERID=b.BOILERID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (boiler a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (incineratorcrematorpermitinfo a,incineratorcrematories b,building c) where a.INCINERATORCREMATORIESID=b.INCINERATORCREMATORIESID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (incincremannualwasteconsum a,incineratorcrematories b,building c) where a.INCINERATORCREMATORIESID=b.INCINERATORCREMATORIESID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (stacktestinfo a,incineratorcrematories b,building c) where a.INCINERATORCREMATORIESID=b.INCINERATORCREMATORIESID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (incineratorcrematories a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (elevatorpermitinfo a,ELEVATORS b,building c) where a.ELEVATORID=b.ELEVATORID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (ELEVATORS a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (etopermitinfo a,eto b,building c) where a.etoid=b.etoid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (etotestinfo a,eto b,building c) where a.etoid=b.etoid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (eto a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (generatorpermitinfo a,generator b,building c) where a.GENERATORID=b.GENERATORID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (generatorfuelconsumption a,generator b,building c) where a.GENERATORID=b.GENERATORID and b.buildingid=c.buildingid and c.facilityid=?",
				
				"delete a from (generatorcfrpermitinfo a,generator b,building c) where a.GENERATORID=b.GENERATORID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (generatorengineconsumption a,generator b,building c) where a.GENERATORID=b.GENERATORID and b.buildingid=c.buildingid and c.facilityid=?",
				
				"delete a from (generator a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (SPRAYBOOTHTOCHEMICALS a,SPRAYBOOTH b,building c) where a.SPRAYBOOTHID=b.SPRAYBOOTHID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (SPRAYBOOTHPERMITINFO a,SPRAYBOOTH b,building c) where a.SPRAYBOOTHID=b.SPRAYBOOTHID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (SPRAYBOOTH a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (LANDFILLPERMITINFO a,LANDFILLS b,building c) where a.LANDFILLSID=b.LANDFILLSID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (LANDFILLTOCOGENTURBINE a,LANDFILLS b,building c) where a.LANDFILLSID=b.LANDFILLSID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (LANDFILLS a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (cogenturbinepermitinfo a,cogenturbine b,building c) where a.COGENTURBINEID=b.COGENTURBINEID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (cogenturbinefuelconsumption a,cogenturbine b,building c) where a.COGENTURBINEID=b.COGENTURBINEID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (cogenturbine a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (rpzpermitinfo a,rpz b,building c) where a.rpzid=b.rpzid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (rpzinspectioninfo a,rpz b,building c) where a.rpzid=b.rpzid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (rpz a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (BRIDGETUNNELPERMITINFO a,BRIDGETUNNELS b,building c) where a.BRIDGETUNNELID=b.BRIDGETUNNELID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (BRIDGETUNNELS a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (bulkoxygentankpermitinfo a,bulkoxygentank b,building c) where a.BULKOXYGENTANKID=b.BULKOXYGENTANKID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (bulkoxygentank a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (hydraulicelevatorothertank a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (FUMEHOODTOCHEMICALS a,FUMEHOODS b,building c) where a.FUMEHOODID=b.FUMEHOODID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (FUMEHOODPERMITINFO a,FUMEHOODS b,building c) where a.FUMEHOODID=b.FUMEHOODID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (FUMEHOODS a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (tanktightnessinfo a,storagetank b,building c) where a.storagetankid=b.storagetankid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (spillcompliance a,storagetank b,building c) where a.storagetankid=b.storagetankid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (storagepermitinfo a,storagetank b,building c) where a.storagetankid=b.storagetankid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (trienialcathodicinfo a,storagetank b,building c) where a.storagetankid=b.storagetankid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (storagetank a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (stackpermitinfo a,stack b,building c) where a.stackid=b.stackid and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (stack a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (others a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (printinginkconsumption a,press b,building c) where a.PRESSID=b.PRESSID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (press a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (firealarm a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (chillerrefrigation a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (airconditioningunit a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (placeofassemblypermit a,placeofassembly b,building c) where a.PLACEOFASSEMBLYID=b.PLACEOFASSEMBLYID and b.buildingid=c.buildingid and c.facilityid=?",
				"delete a from (placeofassembly a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (violation a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete a from (buildinginspectioninfo a,building b) where b.buildingid=a.buildingid and b.facilityid=?",
				"delete from building where facilityid=?",
				"delete from hwaste where facilityid=?",
				"delete from lsf where facilityid=?",
				"delete a from (lsfoperatingequipments a,lsf b) where a.lsfid=b.lsfid and b.facilityid=?",
				"delete from usertofacility where facilityid=?",
				"delete from statepermit where facilityid=?",
				"delete from contacttofacility where facilityid=?",
				"delete from facility where facilityid=?",
				"delete from address where addressid=?" };
		for (int k = 0; k <= 60; k++) {
			SqlUtil sqlutil = new SqlUtil();
			if (k == 60)
				try {
					AddressVo addressvo1 = facilityvo.getFacilityAddress();
					sqlutil.addInParam(String.valueOf(addressvo1.getId()));
				} catch (Exception exception) {
					throw new FacilityException(exception);
				}
			else
				sqlutil.addInParam(String.valueOf(facilityvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(as[k]);
			} catch (Exception exception1) {
				throw new FacilityException(exception1);
			}
		}

		String as1[] = { "delete from contact where contactid=?",
				"delete from person where addressid=?",
				"delete from address where addressid=?" };
		for (int l = 0; l < i; l++) {
			for (int i1 = 0; i1 < 3; i1++) {
				SqlUtil sqlutil1 = new SqlUtil();
				if (i1 == 0)
					sqlutil1.addInParam(String.valueOf(ai1[l]));
				else
					sqlutil1.addInParam(String.valueOf(ai[l]));
				try {
					sqlutil1.execForDmlUsingQuery(as1[i1]);
				} catch (Exception exception2) {
					throw new FacilityException(exception2);
				}
			}

		}

	}

	public static List findAll() throws FacilityException {
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(Constants.GET_ALL_FACILITY,
					com.eespc.tracking.bo.FacilityVo.class);
		} catch (Exception exception) {
			throw new FacilityException(exception);
		}
		return ((List) (obj));
	}

	public static FacilityVo findById(int id) throws FacilityException {
		try {
			SqlUtil util = new SqlUtil();
			util.addInParam(new Integer(id));
			List temp = util.execQueryUsingConstructor(
					Constants.GETTING_FACILITY,
					com.eespc.tracking.bo.FacilityVo.class);
			if (temp != null && temp.size() > 0)
				return (FacilityVo) temp.get(0);
		} catch (Exception e) {
			throw new FacilityException(e);
		}
		return null;
	}

	public static List searchBy(String s, String s1, int i, int j) {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer(Constants.GET_ALL_FACILITY);
		if (s != null && s.trim().length() > 0)
			stringbuffer.append(" and fac.clientname like '").append(s)
					.append("%'");
		if (s1 != null && s1.trim().length() > 0) {
			stringbuffer.append(" and ");
			stringbuffer.append(" fac.decid ='").append(s1).append("'");
		}
		if (i >= 0) {
			stringbuffer.append(" and ");
			stringbuffer.append(" fac.facilitytype =").append(i);
		}
		if (j >= 0) {
			stringbuffer
					.append((new StringBuilder())
							.append(" and fac.facilityid in (select facilityid from usertofacility where userid=")
							.append(j).append(")").toString());
			System.out
					.println("SDATHI:"
							+ (new StringBuilder())
									.append(" and fac.facilityid in (select facilityid from usertofacility where userid=")
									.append(j).append(")").toString());

		}
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.FacilityVo.class);
		} catch (Exception exception) {
			logger.equals(exception);
		}
		return ((List) (obj));
	}

	public static DropDown getDropDown() {
		DropDown dropdown = null;
		List list = null;
		try {
			list = findAll();
		} catch (FacilityException facilityexception) {
			logger.error(facilityexception);
		}
		ArrayList arraylist = new ArrayList();
		arraylist.add(new NameValueBean("All Clients", "100"));
		if (list != null) {
			int i = list.size();
			for (int j = 0; j < i; j++) {
				FacilityVo facilityvo = (FacilityVo) list.get(j);
				String s = "";
				String s2 = "";
				if (facilityvo != null) {
					String s1 = (new StringBuffer(String.valueOf(facilityvo
							.getId()))).toString();
					String s3 = facilityvo.getClientName();
					arraylist.add(new NameValueBean(s3, s1));
				}
			}

		}
		dropdown = new DropDown(arraylist);
		return dropdown;
	}

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.FacilityEntity.class);

}