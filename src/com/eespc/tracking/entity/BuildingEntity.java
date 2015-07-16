package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import com.eespc.tracking.bo.BuildingInspectionReportVo;
import com.eespc.tracking.bo.BuildingVo;
import com.eespc.tracking.bo.myenum.CogenTurbineTypeEnum;
import com.eespc.tracking.bo.myenum.DepartmentEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.BuildingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class BuildingEntity {

	public BuildingEntity() {
	}

	public static List getBuildingsInFacility(int i) {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select * from building where facilityid=");
		stringbuffer.append(i);
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.BuildingVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return ((List) (obj));
	}

	public static BuildingVo getBuilding(int i) {
		BuildingVo buildingvo = null;
		String s = "select * from building where buildingid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.BuildingVo.class);
			if (list != null && list.size() > 0)
				buildingvo = (BuildingVo) list.get(0);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return buildingvo;
	}

	public static List getInspectionReportObject(int i) {
		Object obj = new ArrayList();
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("select * from buildinginspectioninfo where buildingid=");
		stringbuffer.append(i);
		try {
			SqlUtil sqlutil = new SqlUtil();
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.BuildingInspectionReportVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return ((List) (obj));
	}

	public static List getResources(int i) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select entityid, entitytype, ");
		stringbuffer.append("buildingid, name,' ' bname from ");
		stringbuffer.append("(select stackid as entityid, ");
		stringbuffer.append("1 as entitytype, buildingid, ");
		stringbuffer.append("FACILITYSTACKID as name from stack ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select storagetankid as entityid, ");
		stringbuffer.append("2 as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from storagetank");
		stringbuffer.append(" union all ");
		stringbuffer.append("select OTHERTANKID as entityid, ");
		stringbuffer.append("3 as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from HYDRAULICELEVATOROTHERTANK");
		stringbuffer.append(" union all ");
		stringbuffer.append("select bulkoxygentankid as entityid, ");
		stringbuffer.append("4 as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from bulkoxygentank");
		stringbuffer.append(" union all ");
		stringbuffer.append("select bridgetunnelid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.BRIDGE.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from bridgetunnels where isbridge ='Y'");
		stringbuffer.append(" union all ");
		stringbuffer.append("select bridgetunnelid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.TUNNEL.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from bridgetunnels where isbridge ='N'");
		stringbuffer.append(" union all ");
		stringbuffer.append("select rpzid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.RPZ.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from rpz");
		stringbuffer.append(" union all ");
		stringbuffer.append("select fumehoodid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.FUMEHOOD.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from fumehoods");
		stringbuffer.append(" union all ");
		stringbuffer.append("select landfillsid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.LANDFILL.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from landfills");
		stringbuffer.append(" union all ");
		stringbuffer.append("select sprayboothid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PAINT_SPRAY.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from spraybooth");
		stringbuffer.append(" union all ");
		stringbuffer.append("select boilerid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.BOILER.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from boiler");
		stringbuffer.append(" union all ");
		stringbuffer.append("select GENERATORID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.GNERATOR.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from generator ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select ETOID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.ETO.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from eto ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select ELEVATORID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.ELEVATOR.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from ELEVATORS ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select pressid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PRESS.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from PRESS ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select othersid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.OTHERS.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from OTHERS ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select CHILLERREFRIGATIONID as entityid, ");
		stringbuffer
				.append(ResourcesTypeEnum.CHILLERWITHAIRCONDITIONINGREFRIGATION
						.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from chillerrefrigation ");

		stringbuffer.append(" union all ");
		stringbuffer.append("select PLACEOFASSEMBLYID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PLACEOFASSEMBLY.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from PLACEOFASSEMBLY ");

		stringbuffer.append(" union all ");
		stringbuffer.append("select FIREALARMID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.FIREALARM.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer.append("FACILITYDESIGNATEDID as name from FIREALARM ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select INCINERATORCREMATORIESID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.INCINERATOR.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from INCINERATORCREMATORIES ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.INCINERATOR.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select INCINERATORCREMATORIESID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.CREMATORIES.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from INCINERATORCREMATORIES ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.CREMATORIES.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select CHILLERABSORBERID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.CHILLER.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from CHILLERABSORBER ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.CHILLER.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select CHILLERABSORBERID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.ABSORBER.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from CHILLERABSORBER ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.ABSORBER.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select cogenturbineid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.COGEN.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from cogenturbine  where resourcetype=");
		stringbuffer.append(CogenTurbineTypeEnum.COGEN.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select cogenturbineid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.TURBINES.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name from cogenturbine  where resourcetype=");
		stringbuffer.append(CogenTurbineTypeEnum.TURBINES.getCode())
				.append(" ");
		stringbuffer.append(") e ");
		stringbuffer.append("where e.buildingid=?");
		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.BuildingResourcesVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return ((List) (obj));
	}

	public static List getResources(int i, String resString) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("select entityid, entitytype, ");
		stringbuffer.append("buildingid, name ,type   ");
		stringbuffer
				.append(", (select Buildingname from building bd where bd.buildingid=e.buildingid) bname from ");
		stringbuffer.append("(select stackid as entityid, ");
		stringbuffer.append("1 as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYSTACKID as name,'stack' as type from stack ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select storagetankid as entityid, ");
		stringbuffer.append("2 as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name, 'storagetank' as type from storagetank");
		stringbuffer.append(" union all ");
		stringbuffer.append("select OTHERTANKID as entityid, ");
		stringbuffer.append("3 as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'HYDRAULICELEVATOROTHERTANK' as type from HYDRAULICELEVATOROTHERTANK");
		stringbuffer.append(" union all ");
		stringbuffer.append("select bulkoxygentankid as entityid, ");
		stringbuffer.append("4 as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'bulkoxygentank' as type from bulkoxygentank");
		stringbuffer.append(" union all ");
		stringbuffer.append("select bridgetunnelid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.BRIDGE.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name ,'bridgetunnels' as type from bridgetunnels where isbridge ='Y'");
		stringbuffer.append(" union all ");
		stringbuffer.append("select bridgetunnelid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.TUNNEL.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'bridgetunnels' as type from bridgetunnels where isbridge ='N'");
		stringbuffer.append(" union all ");
		stringbuffer.append("select rpzid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.RPZ.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'rpz' as type from rpz");
		stringbuffer.append(" union all ");
		stringbuffer.append("select fumehoodid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.FUMEHOOD.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'fumehoods' as type from fumehoods");
		stringbuffer.append(" union all ");
		stringbuffer.append("select landfillsid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.LANDFILL.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name ,'landfills' as type from landfills");
		stringbuffer.append(" union all ");
		stringbuffer.append("select sprayboothid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PAINT_SPRAY.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'paintspraybooth' as type from spraybooth");
		stringbuffer.append(" union all ");
		stringbuffer.append("select boilerid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.BOILER.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'boiler'as type from boiler");
		stringbuffer.append(" union all ");
		stringbuffer.append("select GENERATORID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.GNERATOR.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'generator' as type from generator ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select ETOID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.ETO.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name ,'eto' as type from eto ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select ELEVATORID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.ELEVATOR.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name, 'ELEVATORS' as type from ELEVATORS ");
		stringbuffer.append(" union all ");

		stringbuffer.append("select PLACEOFASSEMBLYID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PLACEOFASSEMBLY.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name, 'PLACEOFASSEMBLY' as type from PLACEOFASSEMBLY ");
		stringbuffer.append(" union all ");

		stringbuffer.append("select pressid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PRESS.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'PRINTINGPRESS' as type from PRESS ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select othersid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.OTHERS.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'miscellaneous' as type from OTHERS ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select CHILLERREFRIGATIONID as entityid, ");
		stringbuffer
				.append(ResourcesTypeEnum.CHILLERWITHAIRCONDITIONINGREFRIGATION
						.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'chillerrefrigation' as type from chillerrefrigation ");

		stringbuffer.append(" union all ");
		stringbuffer.append("select PLACEOFASSEMBLYID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.PLACEOFASSEMBLY.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'placeofassembly' as type from PLACEOFASSEMBLY ");

		stringbuffer.append(" union all ");
		stringbuffer.append("select FIREALARMID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.FIREALARM.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name, 'FIREALARM' as type from FIREALARM ");

		stringbuffer.append(" union all ");
		stringbuffer.append("select INCINERATORCREMATORIESID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.INCINERATOR.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'INCINERATORCREMATORIES' as type from INCINERATORCREMATORIES ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.INCINERATOR.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select INCINERATORCREMATORIESID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.CREMATORIES.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'INCINERATORCREMATORIES' as type from INCINERATORCREMATORIES ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.CREMATORIES.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select CHILLERABSORBERID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.CHILLER.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'CHILLERABSORBER' as type from CHILLERABSORBER ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.CHILLER.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select CHILLERABSORBERID as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.ABSORBER.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'CHILLERABSORBER' as type from CHILLERABSORBER ");
		stringbuffer.append(" where TYPE=")
				.append(ResourcesTypeEnum.ABSORBER.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select cogenturbineid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.COGEN.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'cogenturbine' as type from cogenturbine  where resourcetype=");
		stringbuffer.append(CogenTurbineTypeEnum.COGEN.getCode()).append(" ");
		stringbuffer.append(" union all ");
		stringbuffer.append("select cogenturbineid as entityid, ");
		stringbuffer.append(ResourcesTypeEnum.TURBINES.getCode());
		stringbuffer.append(" as entitytype, buildingid, ");
		stringbuffer
				.append("FACILITYDESIGNATEDID as name,'cogenturbine' as type from cogenturbine  where resourcetype=");
		stringbuffer.append(CogenTurbineTypeEnum.TURBINES.getCode())
				.append(" ");
		stringbuffer.append(") e ");
		// stringbuffer.append("where e.buildingid=? and( lower(type) like '%"
		// +resString.toLowerCase()+"%' or lower(name) like '%"+resString.toLowerCase()+"%')");

		stringbuffer.append("where e.buildingid=? and( lower(type) like '"
				+ resString.toLowerCase() + "%' )");
		System.out.println("============MANI::BEGIN========");
		System.out.println(stringbuffer.toString().replace("union", "\nunion")
				.replace("from", "\nfrom").replace("select", "\nselect")
				.replace("as", "\nas"));
		System.out.println("============MANI::END========");

		Object obj = new ArrayList();
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(),
					com.eespc.tracking.bo.BuildingResourcesVo.class);
		} catch (Exception exception) {
			if (logger.isEnabledFor(Priority.ERROR))
				logger.error(exception);
		}
		return ((List) (obj));
	}

	public static int getBuildingsCount(int facilityId) {
		int s1 = 0;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {

			SqlUtil sqlutil = new SqlUtil();
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT count(buildingid) FROM building WHERE FACILITYID=?");
			preparedstatement.setInt(1, facilityId);
			resultset = preparedstatement.executeQuery();
			resultset.next();
			s1 = resultset.getInt(1);
		} catch (Exception exception) {
			System.out.println("EXCEPTION : " + exception);
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;

	}

	public static int addBuilding(int i, int j, BuildingVo buildingvo)
			throws BuildingException {
		int k = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into building (buildingid, addressid,");
		stringbuffer.append("facilityid, buildingname, buildingrefid,");
		stringbuffer
				.append("dobbinnumber, blocknumber, lotnumber,BOROUGH,FOOTNOTE,COMMENTS,");
		stringbuffer
				.append("bldgsixstories,cityname,cofobtaining,cofno) values ( null,");
		stringbuffer.append(j).append(",");
		stringbuffer.append(i).append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getBuildingName());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getBuildingId());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getDobBinNumber());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getBlockNumber());
		stringbuffer.append("'");

		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getLotNumber());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getBorough());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getBldgfootnote());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getBldgcomment());
		stringbuffer.append("'");
		stringbuffer.append(",");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getbldtall());
		stringbuffer.append("',");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getCityname());
		stringbuffer.append("',");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getCofObtaining());
		stringbuffer.append("',");
		stringbuffer.append("'");
		stringbuffer.append(buildingvo.getCofNo());
		stringbuffer.append("'");
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

	public static int addBuildingInspRpt(int i,
			BuildingInspectionReportVo buildinginspectionreportvo)
			throws BuildingException {
		int j = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("insert into buildinginspectioninfo ");
		stringbuffer.append("values (null,").append(i);
		stringbuffer.append(",").append(DepartmentEnum.DOB.getCode());

		stringbuffer.append(",");
		stringbuffer.append(
				"'" + buildinginspectionreportvo.isFacadeDobInspected() + "'")
				.append(",");
		/*
		 * .append("'");
		 * 
		 * if(buildinginspectionreportvo.isFacadeDobInspected())
		 * stringbuffer.append("Y"); else stringbuffer.append("N");
		 * 
		 * stringbuffer.append("'").append(",").append("'");
		 */
		stringbuffer.append("'").append(buildinginspectionreportvo.getCycle())
				.append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(buildinginspectionreportvo.getFacadePeriod())
				.append("'");
		stringbuffer.append(",").append("'");
		stringbuffer.append(buildinginspectionreportvo.getSubmitedBy()).append(
				"'");
		stringbuffer.append(",").append("'");
		if (buildinginspectionreportvo.isFacadeRepaired())
			stringbuffer.append("Y");
		else
			stringbuffer.append("N");
		stringbuffer.append("'").append(",").append("'");
		stringbuffer
				.append(buildinginspectionreportvo.getJobNumberRepairWork());
		stringbuffer.append("'").append(",").append("'");
		stringbuffer.append(buildinginspectionreportvo.getRepairedYear())
				.append("'");
		stringbuffer.append(",").append("'");
		if (buildinginspectionreportvo.isDobPermitObtained())
			stringbuffer.append("Y");
		else
			stringbuffer.append("N");
		stringbuffer.append("'").append(",").append("'");
		stringbuffer.append(UtilityObject.convertToString(UtilityObject
				.convertToDate(buildinginspectionreportvo
						.getActualInspectionDate()), "yyyy-MM-dd"));
		stringbuffer.append("'").append(",").append("'");
		stringbuffer.append(UtilityObject.convertToString(UtilityObject
				.convertToDate(buildinginspectionreportvo
						.getNextInspectionDate()), "yyyy-MM-dd"));
		stringbuffer.append("'").append(",");
		stringbuffer.append("'")
				.append(buildinginspectionreportvo.getFilingDateNotes())
				.append("'");
		stringbuffer.append(")");

		SqlUtil sqlutil = new SqlUtil();
		try {
			j = sqlutil.insert(stringbuffer.toString());

		}

		catch (Exception exception) {
			throw new BuildingException(exception);

		}
		return j;

	}

	public static void updateBuilding(BuildingVo buildingvo)
			throws BuildingException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer
				.append("update building set buildingname=?, buildingrefid =?,");
		stringbuffer.append("dobbinnumber = ?, blocknumber =?, lotnumber=?,");
		stringbuffer
				.append("bldgsixstories =?,BOROUGH=?,FOOTNOTE=?,COMMENTS=?,cityname=?,cofobtaining=?,cofno=? where buildingid=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(buildingvo.getBuildingName());
		sqlutil.addInParam(buildingvo.getBuildingId());
		sqlutil.addInParam(buildingvo.getDobBinNumber());
		sqlutil.addInParam(buildingvo.getBlockNumber());
		sqlutil.addInParam(buildingvo.getLotNumber());
		sqlutil.addInParam(buildingvo.getbldtall());
		sqlutil.addInParam(buildingvo.getBorough());
		sqlutil.addInParam(buildingvo.getBldgfootnote());
		sqlutil.addInParam(buildingvo.getBldgcomment());
		sqlutil.addInParam(buildingvo.getCityname());
		sqlutil.addInParam(buildingvo.getCofObtaining());
		sqlutil.addInParam(buildingvo.getCofNo());

		sqlutil.addInParam(new Integer(buildingvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new BuildingException(exception);
		}
	}

	public static void deleteBuildingInspectionInfo(
			BuildingInspectionReportVo buildinginspectionreportvo)
			throws BuildingException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from buildinginspectioninfo ");
		stringbuffer.append("where bldginsprepid =?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(buildinginspectionreportvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new BuildingException(exception);
		}
	}

	public static void updateBuildingInspectionInfo(
			BuildingInspectionReportVo buildinginspectionreportvo)
			throws BuildingException {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update buildinginspectioninfo set ");
		stringbuffer.append("departmentid=?, facadeinspfordob=?, cycle=?, ");
		stringbuffer
				.append("facadeperiod=?, submitedby=?, facaderepairrqd=?, ");
		stringbuffer.append("jobnumberrepairwork=?, repairedyear=?, ");
		stringbuffer
				.append("dobpermitobtained =?, actualinspdate=?, nextinspdate=?, filingdatenotes=? ");
		stringbuffer.append("where bldginsprepid =?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(buildinginspectionreportvo
				.getDepartmentId()));

		sqlutil.addInParam(buildinginspectionreportvo.isFacadeDobInspected());

		/*
		 * if(buildinginspectionreportvo.isFacadeDobInspected())
		 * sqlutil.addInParam("Y"); else sqlutil.addInParam("N");
		 */
		sqlutil.addInParam(buildinginspectionreportvo.getCycle());
		sqlutil.addInParam(buildinginspectionreportvo.getFacadePeriod());

		sqlutil.addInParam(buildinginspectionreportvo.getSubmitedBy());
		if (buildinginspectionreportvo.isFacadeRepaired())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(buildinginspectionreportvo.getJobNumberRepairWork());
		sqlutil.addInParam(buildinginspectionreportvo.getRepairedYear());
		if (buildinginspectionreportvo.isDobPermitObtained())
			sqlutil.addInParam("Y");
		else
			sqlutil.addInParam("N");
		sqlutil.addInParam(UtilityObject.convertToString(UtilityObject
				.convertToDate(buildinginspectionreportvo
						.getActualInspectionDate()), "yyyy-MM-dd"));
		sqlutil.addInParam(UtilityObject.convertToString(UtilityObject
				.convertToDate(buildinginspectionreportvo
						.getNextInspectionDate()), "yyyy-MM-dd"));
		sqlutil.addInParam(buildinginspectionreportvo.getFilingDateNotes());
		sqlutil.addInParam(new Integer(buildinginspectionreportvo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			throw new BuildingException(exception);
		}
	}

	public static BuildingInspectionReportVo findReportByPrimaryKey(int i)
			throws BuildingException {
		BuildingInspectionReportVo buildinginspectionreportvo = null;
		String s = "select * from buildinginspectioninfo where bldginsprepid=?";
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.BuildingInspectionReportVo.class);
			if (list != null && list.size() > 0)
				buildinginspectionreportvo = (BuildingInspectionReportVo) list
						.get(0);
		} catch (Exception exception) {
			throw new BuildingException(exception);
		}
		return buildinginspectionreportvo;
	}

	static Logger logger = Logger
			.getLogger(com.eespc.tracking.entity.BuildingEntity.class);

}