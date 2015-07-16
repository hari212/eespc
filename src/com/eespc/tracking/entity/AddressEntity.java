package com.eespc.tracking.entity;

import java.util.List;

import com.eespc.tracking.bo.AddressVo;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.Constants;
import com.eespc.tracking.exceptions.AddressException;
import com.eespc.tracking.util.SqlUtil;

public class AddressEntity {

	public AddressEntity() {
	}

	public static AddressVo getAddressById(int addressId) throws Exception {
		AddressVo addressObj = null;
		try {
			SqlUtil util = new SqlUtil();
			util.addInParam(new Integer(addressId));
			List addrList = util.execQueryUsingConstructor(
					Constants.GET_ADDRESS,
					com.eespc.tracking.bo.AddressVo.class);
			if (addrList != null && addrList.size() > 0)
				addressObj = (AddressVo) addrList.get(0);
		} catch (Exception e) {
			Exception ex = new Exception("Getting Address");
			ex.initCause(e);
			throw ex;
		}
		return addressObj;
	}

	public static int addAddress(AddressVo _addressVo) throws AddressException {
		int generatedKey = -99;
		StringBuffer buff = new StringBuffer();
		buff.append("insert into address values (null,'");
		buff.append(_addressVo.getAddress1()).append("','");
		buff.append(_addressVo.getAddress2()).append("','");
		buff.append(_addressVo.getAddress3()).append("','");
		buff.append(_addressVo.getCity()).append("','");
		buff.append(_addressVo.getState()).append("','");
		buff.append(_addressVo.getZipCode()).append("')");
		SqlUtil utilObj = new SqlUtil();
		try {
			generatedKey = utilObj.insert(buff.toString());
		} catch (Exception e) {
			throw new AddressException(e);
		}
		return generatedKey;
	}

	public static void update(AddressVo _addressVo) throws AddressException {
		if (_addressVo == null)
			throw new AddressException("AddressVo cannot be NULL");
		SqlUtil utilObj = new SqlUtil();
		StringBuffer updt = new StringBuffer();
		updt.append("update address set ");
		updt.append("ADDRESS1=?, ");
		updt.append("ADDRESS2=?, ");
		updt.append("ADDRESS3=?, ");
		updt.append("CITY=?, ");
		updt.append("STATE=?, ");
		updt.append("ZIPCODE=? ");
		updt.append("where ADDRESSID=?");
		utilObj.addInParam(_addressVo.getAddress1());
		utilObj.addInParam(_addressVo.getAddress2());
		utilObj.addInParam(_addressVo.getAddress3());
		utilObj.addInParam(_addressVo.getCity());
		utilObj.addInParam(_addressVo.getState());
		utilObj.addInParam(_addressVo.getZipCode());
		utilObj.addInParam(new Integer(_addressVo.getId()));
		try {
			utilObj.execForDmlUsingQuery(updt.toString());
		} catch (Exception e) {
			AddressException ex = new AddressException(
					"While Updating Address.");
			ex.initCause(e);
			throw ex;
		}
	}

	public static void delete(BuildingVo buildingvo) throws Exception {
		String as[] = {
				"delete a from (chillerabsorberpermitinfo a,chillerabsorber b) where a.CHILLERABS"
						+ "ORBERID=b.CHILLERABSORBERID and b.buildingid=?",
				"delete a from (chillerabsorberfuelconsumption a,chillerabsorber b) where a.CHILL"
						+ "ERABSORBERID=b.CHILLERABSORBERID and b.buildingid=?",
				"delete from chillerabsorber where BUILDINGID=?",
				"delete a from (boilerpermitinfo a,boiler b) where a.BOILERID=b.BOILERID and b.bu"
						+ "ildingid=?",
				"delete a from (boilerfuelconsumption a,boiler b) where a.BOILERID=b.BOILERID and"
						+ " b.buildingid=?",
				"delete a from (annualtuneup a,boiler b) where a.BOILERID=b.BOILERID and b.buildi"
						+ "ngid=?",
				"delete from boiler where BUILDINGID=?",
				"delete a from (incineratorcrematorpermitinfo a,incineratorcrematories b) where a"
						+ ".INCINERATORCREMATORIESID=b.INCINERATORCREMATORIESID and b.buildingid=?",
				"delete a from (incincremannualwasteconsum a,incineratorcrematories b) where a.IN"
						+ "CINERATORCREMATORIESID=b.INCINERATORCREMATORIESID and b.buildingid=?",
				"delete a from (stacktestinfo a,incineratorcrematories b) where a.INCINERATORCREM"
						+ "ATORIESID=b.INCINERATORCREMATORIESID and b.buildingid=?",
				"delete from incineratorcrematories where BUILDINGID=?",
				"delete a from (elevatorpermitinfo a,ELEVATORS b) where a.ELEVATORID=b.ELEVATORID"
						+ " and b.buildingid=?",
				"delete from ELEVATORS where BUILDINGID=?",
				"delete a from (etopermitinfo a,eto b) where a.etoid=b.etoid and b.buildingid=?",
				"delete a from (etotestinfo a,eto b) where a.etoid=b.etoid and b.buildingid=?",
				"delete from eto where buildingid=?",
				"delete a from (generatorpermitinfo a,generator b) where a.GENERATORID=b.GENERATO"
						+ "RID and b.buildingid=?",
				"delete a from (generatorfuelconsumption a,generator b) where a.GENERATORID=b.GEN"
						+ "ERATORID and b.buildingid=?",
				"delete from generator where buildingid=?",
				"delete a from (SPRAYBOOTHTOCHEMICALS a,SPRAYBOOTH b) where a.SPRAYBOOTHID=b.SPRA"
						+ "YBOOTHID and b.buildingid=?",
				"delete a from (SPRAYBOOTHPERMITINFO a,SPRAYBOOTH b) where a.SPRAYBOOTHID=b.SPRAY"
						+ "BOOTHID and b.buildingid=?",
				"delete from SPRAYBOOTH where buildingid=?",
				"delete a from (LANDFILLPERMITINFO a,LANDFILLS b) where a.LANDFILLSID=b.LANDFILLS"
						+ "ID and b.buildingid=?",
				"delete a from (LANDFILLTOCOGENTURBINE a,LANDFILLS b) where a.LANDFILLSID=b.LANDF"
						+ "ILLSID and b.buildingid=?",
				"delete from LANDFILLS where buildingid=?",
				"delete a from (cogenturbinepermitinfo a,cogenturbine b) where a.COGENTURBINEID=b"
						+ ".COGENTURBINEID and b.buildingid=?",
				"delete a from (cogenturbinefuelconsumption a,cogenturbine b) where a.COGENTURBIN"
						+ "EID=b.COGENTURBINEID and b.buildingid=?",
				"delete from cogenturbine where BUILDINGID=?",
				"delete a from (rpzpermitinfo a,rpz b) where a.rpzid=b.rpzid and b.buildingid=?",
				"delete a from (rpzinspectioninfo a,rpz b) where a.rpzid=b.rpzid and b.buildingid"
						+ "=?",
				"delete from rpz where buildingid=?",
				"delete a from (BRIDGETUNNELPERMITINFO a,BRIDGETUNNELS b) where a.BRIDGETUNNELID="
						+ "b.BRIDGETUNNELID and b.buildingid=?",
				"delete from BRIDGETUNNELS where buildingid=?",
				"delete a from (bulkoxygentankpermitinfo a,bulkoxygentank b) where a.BULKOXYGENTA"
						+ "NKID=b.BULKOXYGENTANKID and b.buildingid=?",
				"delete from bulkoxygentank where buildingid=?",
				"delete from hydraulicelevatorothertank where buildingid=?",
				"delete a from (FUMEHOODTOCHEMICALS a,FUMEHOODS b) where a.FUMEHOODID=b.FUMEHOODI"
						+ "D and b.buildingid=?",
				"delete a from (FUMEHOODPERMITINFO a,FUMEHOODS b) where a.FUMEHOODID=b.FUMEHOODID"
						+ " and b.buildingid=?",
				"delete from FUMEHOODS where buildingid=?",
				"delete a from (tanktightnessinfo a,storagetank b) where a.storagetankid=b.storag"
						+ "etankid and b.buildingid=?",
				"delete a from (spillcompliance a,storagetank b) where a.storagetankid=b.storaget"
						+ "ankid and b.buildingid=?",
				"delete a from (storagepermitinfo a,storagetank b) where a.storagetankid=b.storag"
						+ "etankid and b.buildingid=?",
				"delete from storagetank where buildingid=?",
				"delete a from (stackpermitinfo a,stack b) where a.stackid=b.stackid and b.buildi"
						+ "ngid=?",
				"delete from stack where buildingid=?",
				"delete from others where buildingid=?",
				"delete a from (printinginkconsumption a,press b) where a.Pressid=b.pressid and b.buildingid=?",
				"delete from press where buildingid=?",
				"delete from firealarm where buildingid=?",
				"delete from chillerrefrigation where buildingid=?",
				"delete from violation where buildingid=?",
				"delete from buildinginspectioninfo where buildingid=?",
				"delete from building where buildingid=?" };
		System.out.println((new StringBuilder()).append(buildingvo.getId())
				.append("-").append(buildingvo.getBuildingId()).toString());
		for (int i = 0; i <= 52; i++) {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(String.valueOf(buildingvo.getId()));
			try {
				sqlutil.execForDmlUsingQuery(as[i]);
			} catch (Exception exception) {
				AddressException addressexception = new AddressException(
						"While Updating Address.");
				addressexception.initCause(exception);
				throw addressexception;
			}
		}

	}

}