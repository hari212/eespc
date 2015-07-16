package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.bo.HwasteVo;
import com.eespc.tracking.exceptions.BuildingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class HwasteEntity {

	public HwasteEntity() {
	}

	public static List getHwasteInFacility(int i) {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select * from hwaste where facilityid=");
		stringbuffer.append(i + " order by dateOftheManifest");
		System.out.println("Query:" + stringbuffer.toString());
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.HwasteVo.class);
		} catch (Exception exception) {
			System.out.println("getHwasteInFacility:" + exception);
		}
		System.out.println("getHwasteInFacility:" + ((List) (obj)).size());
		return ((List) (obj));
	}

	public static HwasteVo findByPrimaryKey(int i) {
		HwasteVo hwastevo = null;
		String s = "select * from hwaste where hwasteid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.HwasteVo.class);
			if (list != null && list.size() > 0)
				hwastevo = (HwasteVo) list.get(0);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return hwastevo;
	}

	public static int add(int i, HwasteVo hwastevo) throws BuildingException {

		// System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+hwastevo.getWasteName1());
		int k = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into hwaste (hwasteid, facilityid");

		stringbuffer
				.append(",manifastno,dateOftheManifest,waste,waste1,waste2,waste3,waste4,typeOfWaste,wasteName,wasteVolume,wasteVolume1,wasteVolume2,wasteVolume3,wasteVolume4,wasteDensity,wasteDensity1,wasteDensity2,wasteDensity3,wasteDensity4,wasteUnit,wasteUnit1,wasteUnit2,wasteUnit3,wasteUnit4,wasteQuantity,typeOfWaste1,wasteName1,wasteQuantity1,typeOfWaste2,wasteName2,wasteQuantity2,typeOfWaste3,wasteName3,wasteQuantity3,typeOfWaste4,wasteName4,wasteQuantity4,hazardousTotalWaste,divisioncontactname,telephonenumber,companyName,epaidWaste,epaId,hazardousWasteCategory,epaidFinaldes) values ( null,");
		stringbuffer.append(i).append(",");

		stringbuffer.append("'").append(hwastevo.getManifastno()).append("',");

		if (UtilityObject.isNotEmpty(hwastevo.getDateOftheManifest()))
			stringbuffer.append("'").append(hwastevo.getDateOftheManifest())
					.append("',");
		else
			stringbuffer.append("null,");

		stringbuffer.append("'").append(hwastevo.getWaste()).append("',");
		stringbuffer.append("'").append(hwastevo.getWaste1()).append("',");
		stringbuffer.append("'").append(hwastevo.getWaste2()).append("',");
		stringbuffer.append("'").append(hwastevo.getWaste3()).append("',");
		stringbuffer.append("'").append(hwastevo.getWaste4()).append("',");
		stringbuffer.append(hwastevo.getTypeOfWaste()).append(",");
		stringbuffer.append("'").append(hwastevo.getWasteName()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteVolume()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteVolume1())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteVolume2())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteVolume3())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteVolume4())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteDensity())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteDensity1())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteDensity2())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteDensity3())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteDensity4())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getWasteUnit()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteUnit1()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteUnit2()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteUnit3()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteUnit4()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteQuantity())
				.append("',");
		stringbuffer.append(hwastevo.getTypeOfWaste1()).append(",");
		stringbuffer.append("'").append(hwastevo.getWasteName1()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteQuantity1())
				.append("',");
		stringbuffer.append(hwastevo.getTypeOfWaste2()).append(",");
		stringbuffer.append("'").append(hwastevo.getWasteName2()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteQuantity2())
				.append("',");
		stringbuffer.append(hwastevo.getTypeOfWaste3()).append(",");
		stringbuffer.append("'").append(hwastevo.getWasteName3()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteQuantity3())
				.append("',");
		stringbuffer.append(hwastevo.getTypeOfWaste4()).append(",");
		stringbuffer.append("'").append(hwastevo.getWasteName4()).append("',");
		stringbuffer.append("'").append(hwastevo.getWasteQuantity4())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getHazardousTotalWaste())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getDivisioncontactname())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getTelephonenumber())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getCompanyName()).append("',");
		stringbuffer.append("'").append(hwastevo.getEpaidWaste()).append("',");
		stringbuffer.append("'").append(hwastevo.getEpaId()).append("',");
		stringbuffer.append("'").append(hwastevo.getHazardousWasteCategory())
				.append("',");
		stringbuffer.append("'").append(hwastevo.getEpaidFinaldes())
				.append("')");

		SqlUtil sqlutil = new SqlUtil();
		try {
			k = sqlutil.insert(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("Exception:" + exception);
			throw new BuildingException(exception);
		}

		return k;
	}

	public static void update(HwasteVo hwastevo)

	{

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("update hwaste set manifastno=?,dateOftheManifest=?,waste=?,waste1=?,waste2=?,waste3=?,waste4=?,typeOfWaste=?,wasteName=?,wasteVolume=?,wasteDensity=?,wasteUnit=?,wasteVolume1=?,wasteDensity1=?,wasteUnit1=?,wasteVolume2=?,wasteDensity2=?,wasteUnit2=?,wasteVolume3=?,wasteDensity3=?,wasteUnit3=?,wasteVolume4=?,wasteDensity4=?,wasteUnit4=?,wasteQuantity=?,typeOfWaste1=?,wasteName1=?,wasteQuantity1=?,typeOfWaste2=?,wasteName2=?,wasteQuantity2=?,typeOfWaste3=?,wasteName3=?,wasteQuantity3=?,typeOfWaste4=?,wasteName4=?,wasteQuantity4=?,hazardousTotalWaste=?,divisioncontactname=?,telephonenumber=?,companyName=?,epaidWaste=?,epaId=?,hazardousWasteCategory=?,epaidFinaldes=? ");
		stringbuffer.append(" where hwasteid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(hwastevo.getManifastno());

		if (UtilityObject.isNotEmpty(hwastevo.getDateOftheManifest()))
			sqlutil.addInParam(hwastevo.getDateOftheManifest());
		else
			sqlutil.addInParam(null);

		sqlutil.addInParam(hwastevo.getWaste());
		sqlutil.addInParam(hwastevo.getWaste1());
		sqlutil.addInParam(hwastevo.getWaste2());
		sqlutil.addInParam(hwastevo.getWaste3());
		sqlutil.addInParam(hwastevo.getWaste4());
		sqlutil.addInParam(new Integer(hwastevo.getTypeOfWaste()));
		sqlutil.addInParam(hwastevo.getWasteName());
		sqlutil.addInParam(hwastevo.getWasteVolume());
		sqlutil.addInParam(hwastevo.getWasteDensity());
		sqlutil.addInParam(hwastevo.getWasteUnit());
		sqlutil.addInParam(hwastevo.getWasteVolume1());
		sqlutil.addInParam(hwastevo.getWasteDensity1());
		sqlutil.addInParam(hwastevo.getWasteUnit1());
		sqlutil.addInParam(hwastevo.getWasteVolume2());
		sqlutil.addInParam(hwastevo.getWasteDensity2());
		sqlutil.addInParam(hwastevo.getWasteUnit2());
		sqlutil.addInParam(hwastevo.getWasteVolume3());
		sqlutil.addInParam(hwastevo.getWasteDensity3());
		sqlutil.addInParam(hwastevo.getWasteUnit3());
		sqlutil.addInParam(hwastevo.getWasteVolume4());
		sqlutil.addInParam(hwastevo.getWasteDensity4());
		sqlutil.addInParam(hwastevo.getWasteUnit4());
		sqlutil.addInParam(hwastevo.getWasteQuantity());
		sqlutil.addInParam(new Integer(hwastevo.getTypeOfWaste1()));
		sqlutil.addInParam(hwastevo.getWasteName1());
		sqlutil.addInParam(hwastevo.getWasteQuantity1());
		sqlutil.addInParam(new Integer(hwastevo.getTypeOfWaste2()));
		sqlutil.addInParam(hwastevo.getWasteName2());
		sqlutil.addInParam(hwastevo.getWasteQuantity2());
		sqlutil.addInParam(new Integer(hwastevo.getTypeOfWaste3()));
		sqlutil.addInParam(hwastevo.getWasteName3());
		sqlutil.addInParam(hwastevo.getWasteQuantity3());
		sqlutil.addInParam(new Integer(hwastevo.getTypeOfWaste4()));
		sqlutil.addInParam(hwastevo.getWasteName4());
		sqlutil.addInParam(hwastevo.getWasteQuantity4());
		sqlutil.addInParam(hwastevo.getHazardousTotalWaste());
		sqlutil.addInParam(hwastevo.getDivisioncontactname());
		sqlutil.addInParam(hwastevo.getTelephonenumber());
		sqlutil.addInParam(hwastevo.getCompanyName());
		sqlutil.addInParam(hwastevo.getEpaidWaste());
		sqlutil.addInParam(hwastevo.getEpaId());
		sqlutil.addInParam(new Integer(hwastevo.getHazardousWasteCategory()));
		sqlutil.addInParam(hwastevo.getEpaidFinaldes());

		sqlutil.addInParam(new Integer(hwastevo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("hwaste Update Exception:" + exception);
		}

	}

	public static void delete(HwasteVo hwastevo) {

		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from hwaste where hwasteid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(hwastevo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			System.out.println("While Deleting hwaste:" + exception);

		}

	}

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.HwasteEntity.class);

}