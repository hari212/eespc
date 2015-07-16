package com.eespc.tracking.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.BoilerAnnualTuneUpVo;
import com.eespc.tracking.bo.BoilerFuelConsumptionVo;
import com.eespc.tracking.bo.BoilerPermitInfoVo;
import com.eespc.tracking.bo.EngineRunningHrsVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.GeneratorCfrPermitInfoVo;
import com.eespc.tracking.bo.GeneratorEngineRunningHrsVo;
import com.eespc.tracking.bo.GeneratorVo;
import com.eespc.tracking.bo.TankTightnessVo;
import com.eespc.tracking.bo.myenum.FurnaceOilTypeEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

// Referenced classes of package com.eespc.tracking.entity:
//            FuelConsumptionEntity

public class GeneratorEntity
{
    public static class GeneratorPermitEntity
    {
        public static BoilerPermitInfoVo findByPrimaryKey(int i)
            throws TrackingException
        {
            String s = "select GNRTPERMITID as BOILERPERMITID,GENERATORID as BOILERID,PERMITNUMBER,LASTINSPECTIONDATE,ISSUEDDATE, EXPIRATIONDATE ,DEPID,YEAR,null as INSPSUBMITTEDDATE,null as INSPECTIONTYPE,DEPCOMPLIANCECOMMENTS,DOBCOMPLIANCECOMMENTS,DEPEXPIRATIONNOTE,DOBEXPIRATIONNOTE from GENERATORPERMITINFO where GNRTPERMITID=?";
            BoilerPermitInfoVo boilerpermitinfovo = null;
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                List list = sqlutil.execQueryUsingConstructor(s, com.eespc.tracking.bo.BoilerPermitInfoVo.class);
                if(list != null && list.size() > 0)
                    boilerpermitinfovo = (BoilerPermitInfoVo)list.get(0);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("findByPrimaryKey(").append(i).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return boilerpermitinfovo;
        }

        public static List getList(int i)
            throws TrackingException
        {
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("select GNRTPERMITID as BOILERPERMITID,GENERATORID as BOILERID,PERMITNUMBER,LASTINSPECTIONDATE,ISSUEDDATE, EXPIRATIONDATE ,DEPID,YEAR,null as INSPSUBMITTEDDATE,null as INSPECTIONTYPE,DEPCOMPLIANCECOMMENTS,DOBCOMPLIANCECOMMENTS,DEPEXPIRATIONNOTE,DOBEXPIRATIONNOTE from GENERATORPERMITINFO where GENERATORID =?");
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.BoilerPermitInfoVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getList(").append(i).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return ((List) (obj));
        }

        public static int add(BoilerPermitInfoVo boilerpermitinfovo)
            throws TrackingException
        {
            int i = -99;
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("insert into GENERATORPERMITINFO ");
            stringbuffer.append("(GNRTPERMITID,GENERATORID,YEAR,LASTINSPECTIONDATE,");
            stringbuffer.append("PERMITNUMBER,ISSUEDDATE,EXPIRATIONDATE,DEPCOMPLIANCECOMMENTS,");
            stringbuffer.append("DEPID)");
            stringbuffer.append(" values (null,");
            stringbuffer.append(boilerpermitinfovo.getBoilerId()).append(",");
            stringbuffer.append("'").append(boilerpermitinfovo.getYear()).append("',");
            
            
            if(boilerpermitinfovo.getLastinspectionDate() !=null)
            stringbuffer.append("'").append(UtilityObject.convertToString(boilerpermitinfovo.getLastinspectionDate(), "yyyy-MM-dd")).append("',");
            else
        	stringbuffer.append("null,");
            
            stringbuffer.append("'").append(boilerpermitinfovo.getPermitNumber()).append("',");

            if(boilerpermitinfovo.getIssueDate() !=null)
            stringbuffer.append("'").append(UtilityObject.convertToString(boilerpermitinfovo.getIssueDate(), "yyyy-MM-dd")).append("',");
            else
            stringbuffer.append("null,");

            if(boilerpermitinfovo.getExpirationDate() !=null)
            stringbuffer.append("'").append(UtilityObject.convertToString(boilerpermitinfovo.getExpirationDate(), "yyyy-MM-dd")).append("',");
            else
            stringbuffer.append("null,");

			stringbuffer.append("'").append(boilerpermitinfovo.getDepCompliancecomments()).append("',");
			//stringbuffer.append("'").append(boilerpermitinfovo.getDobCompliancecomments()).append("',");

            stringbuffer.append("'").append(boilerpermitinfovo.getDepId()).append("'");
            stringbuffer.append(")");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                i = sqlutil.insert(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While Adding Generator Permit Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return i;
        }

        public static void update(BoilerPermitInfoVo boilerpermitinfovo)
            throws TrackingException
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("update GENERATORPERMITINFO ");
            stringbuffer.append("set ");
            stringbuffer.append("YEAR=?, ");
            stringbuffer.append("LASTINSPECTIONDATE=?, ");
            stringbuffer.append("PERMITNUMBER=?,");
            stringbuffer.append("ISSUEDDATE=?,");
            stringbuffer.append("EXPIRATIONDATE=?,");
            stringbuffer.append("DEPCOMPLIANCECOMMENTS=?,");
           // stringbuffer.append("DOBCOMPLIANCECOMMENTS=?,");
            stringbuffer.append("DEPID=? ");
            stringbuffer.append(" where GNRTPERMITID=?");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                sqlutil.addInParam(boilerpermitinfovo.getYear());
                if(boilerpermitinfovo.getLastinspectionDate() !=null)
                sqlutil.addInParam(UtilityObject.convertToString(boilerpermitinfovo.getLastinspectionDate(), "yyyy-MM-dd"));
                else
                sqlutil.addInParam(null);
                sqlutil.addInParam(boilerpermitinfovo.getPermitNumber());

            	if(boilerpermitinfovo.getIssueDate() !=null)
                sqlutil.addInParam(UtilityObject.convertToString(boilerpermitinfovo.getIssueDate(), "yyyy-MM-dd"));
                else
                sqlutil.addInParam(null);
                if(boilerpermitinfovo.getExpirationDate() !=null)
                sqlutil.addInParam(UtilityObject.convertToString(boilerpermitinfovo.getExpirationDate(), "yyyy-MM-dd"));
                else
                sqlutil.addInParam(null);

				sqlutil.addInParam(boilerpermitinfovo.getDepCompliancecomments());
			//	sqlutil.addInParam(boilerpermitinfovo.getDobCompliancecomments());
                sqlutil.addInParam(new Integer(boilerpermitinfovo.getDepId()));
                sqlutil.addInParam(new Integer(boilerpermitinfovo.getId()));
                sqlutil.execForDmlUsingQuery(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While updating Generator Permit Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
        }

        public static void delete(BoilerPermitInfoVo boilerpermitinfovo)
            throws TrackingException
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("delete from GENERATORPERMITINFO ");
            stringbuffer.append(" where GNRTPERMITID=?");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                sqlutil.addInParam(new Integer(boilerpermitinfovo.getId()));
                sqlutil.execForDmlUsingQuery(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While updating Generator Permit Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
        }

        public GeneratorPermitEntity()
        {
        }
    }



//****************************************CFR PERMIT STARTS*******************************************//

    public static int addCfrPermit(GeneratorCfrPermitInfoVo generatorcfrpermitinfovo)
			throws TrackingException {
    	int i = -99;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("insert into GENERATORCFRPERMITINFO ");
        /*stringbuffer.append("(GNRTCFRPERMITID,GENERATORID,INITIALTESTDATE,INITIALPRESSUREOIL,");
        stringbuffer.append("INITIALPRESSUREGAS,SUBSEQUENTTESTDATE,SUBSEQUENTPRESSUREOIL,SUBSEQUENTPRESSUREGAS)");*/      
        stringbuffer.append(" values (null,");        
        stringbuffer.append(generatorcfrpermitinfovo.getGeneratorId()).append(",");                       
        if(generatorcfrpermitinfovo.getInitialTestDate() !=null)
        stringbuffer.append("'").append(UtilityObject.convertToString(generatorcfrpermitinfovo.getInitialTestDate(), "yyyy-MM-dd")).append("',");
        else
    	stringbuffer.append("null,");            
        stringbuffer.append("'").append(generatorcfrpermitinfovo.getInitialPressureOil()).append("',");
        stringbuffer.append("'").append(generatorcfrpermitinfovo.getInitialPressureGas()).append("',");

        if(generatorcfrpermitinfovo.getLastSubsequentTestDate() !=null)
        stringbuffer.append("'").append(UtilityObject.convertToString(generatorcfrpermitinfovo.getLastSubsequentTestDate(), "yyyy-MM-dd")).append("',");
        else
        stringbuffer.append("null,");           
		stringbuffer.append("'").append(generatorcfrpermitinfovo.getSubsequentPressureOil()).append("',");
        stringbuffer.append("'").append(generatorcfrpermitinfovo.getSubsequentPressureGas()).append("')");
        
        SqlUtil sqlutil = new SqlUtil();
        try
        {
            i = sqlutil.insert(stringbuffer.toString());
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException("While Adding Generator Cfr Permit Info");
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return i;
    }
        
        
	public static void updateCfrPermit(GeneratorCfrPermitInfoVo generatorcfrpermitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("update GENERATORCFRPERMITINFO ");
        stringbuffer.append("set ");
        stringbuffer.append("INITIALTESTDATE=?, ");          
        stringbuffer.append("INITIALPRESSUREOIL=?,");
        stringbuffer.append("INITIALPRESSUREGAS=?,");
        stringbuffer.append("SUBSEQUENTTESTDATE=?,");
        stringbuffer.append("SUBSEQUENTPRESSUREOIL=?,");
        stringbuffer.append("SUBSEQUENTPRESSUREGAS=? ");           
        stringbuffer.append(" where GNRTCFRPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();

		 if(generatorcfrpermitinfovo.getInitialTestDate() !=null)
             sqlutil.addInParam(UtilityObject.convertToString(generatorcfrpermitinfovo.getInitialTestDate(), "yyyy-MM-dd"));
             else
             sqlutil.addInParam(null);
             sqlutil.addInParam(generatorcfrpermitinfovo.getInitialPressureOil());
             sqlutil.addInParam(generatorcfrpermitinfovo.getInitialPressureGas());

         	if(generatorcfrpermitinfovo.getLastSubsequentTestDate() !=null)
             sqlutil.addInParam(UtilityObject.convertToString(generatorcfrpermitinfovo.getLastSubsequentTestDate(), "yyyy-MM-dd"));
             else
             sqlutil.addInParam(null);
             sqlutil.addInParam(generatorcfrpermitinfovo.getSubsequentPressureOil());
             sqlutil.addInParam(generatorcfrpermitinfovo.getSubsequentPressureGas());
             sqlutil.addInParam(new Integer(generatorcfrpermitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
			} catch (Exception exception) {
				TrackingException trackingexception = new TrackingException("While updating Generator Cfr Permit Info");
	             trackingexception.initCause(exception);
	             throw trackingexception;
		}	  
         
	}

	public static void deleteCfrPermit(GeneratorCfrPermitInfoVo generatorcfrpermitinfovo)
			throws TrackingException {
		byte byte0 = -99;
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("delete from GENERATORCFRPERMITINFO ");
		stringbuffer.append("where GNRTCFRPERMITID=?");
		SqlUtil sqlutil = new SqlUtil();
		sqlutil.addInParam(new Integer(generatorcfrpermitinfovo.getId()));
		try {
			sqlutil.execForDmlUsingQuery(stringbuffer.toString());
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException("While delete Generator Cfr Permit Info");
            trackingexception.initCause(exception);
            throw trackingexception;
		}				
	}

	public static List getCfrPermitList(int i)
			throws TrackingException
	{
		List cfrList = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("select * from GENERATORCFRPERMITINFO where GENERATORID =?");
        try
        {
        	SqlUtil sqlutil = new SqlUtil();
        	sqlutil.addInParam(new Integer(i));
        	cfrList = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.GeneratorCfrPermitInfoVo.class);
        }
        catch(Exception e)
        {
            TrackingException ex = new TrackingException("getList(" + i + ")");
            ex.initCause(e);
            throw ex;
        }
        return cfrList;		
	}

	public static GeneratorCfrPermitInfoVo getCfrPermit(int i)
			throws TrackingException {
		
		String s = "select * from GENERATORCFRPERMITINFO where GNRTCFRPERMITID=?";
		GeneratorCfrPermitInfoVo generatorcfrpermitinfovo = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			sqlutil.addInParam(new Integer(i));
			List list = sqlutil.execQueryUsingConstructor(s,
					com.eespc.tracking.bo.GeneratorCfrPermitInfoVo.class);
			if (list != null && list.size() > 0)
				generatorcfrpermitinfovo = (GeneratorCfrPermitInfoVo) list.get(0);
		} catch (Exception exception) {
			TrackingException trackingexception = new TrackingException(
					(new StringBuilder()).append("getCfrPermit(").append(i)
							.append(")").toString());
			trackingexception.initCause(exception);
			throw trackingexception;
		}
		return generatorcfrpermitinfovo;
		
         
	}
//****************************************CFR PERMIT ENDS*******************************************//
//****************************************FUEL CONSUMPTION******************************************//
    public static class GeneratorFuelConsumptionEntity
    {

        public static BoilerFuelConsumptionVo findByPrimaryKey(int i)
            throws TrackingException
        {
            String s = "select GNRTANNFUELCONSID  as BLRANNFUELCONSID,GENERATORID as BOILERID,YEAR, ANNUALCONSUMP,DAILYCONSUMP,MONTHLYCONSUMP  from GENERATORFUELCONSUMPTION where GNRTANNFUELCONSID=?";
            BoilerFuelConsumptionVo boilerfuelconsumptionvo = null;
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                List list = sqlutil.execQueryUsingConstructor(s, com.eespc.tracking.bo.BoilerFuelConsumptionVo.class);
                if(list != null && list.size() > 0)
                    boilerfuelconsumptionvo = (BoilerFuelConsumptionVo)list.get(0);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("findByPrimaryKey(").append(i).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return boilerfuelconsumptionvo;
        }

        public static List getList(int i)
            throws TrackingException
        {
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("select GNRTANNFUELCONSID  as BLRANNFUELCONSID,GENERATORID as BOILERID,YEAR, ANNUALCONSUMP,DAILYCONSUMP,MONTHLYCONSUMP from GENERATORFUELCONSUMPTION where GENERATORID =?");
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.BoilerFuelConsumptionVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getList(").append(i).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return ((List) (obj));
        }

        public static int add(BoilerFuelConsumptionVo boilerfuelconsumptionvo)
            throws TrackingException
        {
            int i = -99;
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("insert into GENERATORFUELCONSUMPTION ");
            stringbuffer.append("(GNRTANNFUELCONSID, GENERATORID, YEAR, ");
            stringbuffer.append(" ANNUALCONSUMP, DAILYCONSUMP, MONTHLYCONSUMP) ");
            stringbuffer.append(" values (null,");
            stringbuffer.append(boilerfuelconsumptionvo.getBoilerId()).append(",");
            stringbuffer.append("'").append(boilerfuelconsumptionvo.getYear()).append("',");
            stringbuffer.append("'").append(boilerfuelconsumptionvo.getAnnualConsump()).append("',");
            stringbuffer.append("'").append(boilerfuelconsumptionvo.getDailyConsump()).append("',");
            stringbuffer.append("'").append(boilerfuelconsumptionvo.getMonthlyConsump()).append("'");
            stringbuffer.append(")");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                i = sqlutil.insert(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While Adding Generator Fuel Consumption Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return i;
        }

        public static void update(BoilerFuelConsumptionVo boilerfuelconsumptionvo)
            throws TrackingException
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("update GENERATORFUELCONSUMPTION ");
            stringbuffer.append("set year=?, ANNUALCONSUMP=?, ");
            stringbuffer.append("    DAILYCONSUMP=?, MONTHLYCONSUMP=? ");
            stringbuffer.append(" where GNRTANNFUELCONSID=?");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                sqlutil.addInParam(boilerfuelconsumptionvo.getYear());
                sqlutil.addInParam(boilerfuelconsumptionvo.getAnnualConsump());
                sqlutil.addInParam(boilerfuelconsumptionvo.getDailyConsump());
                sqlutil.addInParam(boilerfuelconsumptionvo.getMonthlyConsump());
                sqlutil.addInParam(new Integer(boilerfuelconsumptionvo.getId()));
                sqlutil.execForDmlUsingQuery(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While updating Generator Fuel Consumption Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
        }

        public GeneratorFuelConsumptionEntity()
        {
        }
    }   
    
    public GeneratorEntity()
    {
    }

    
	public static void deletefuelconsumption(int id)
            throws TrackingException
        {
        StringBuffer buff = new StringBuffer();
            buff.append("delete from generatorfuelconsumption");
            buff.append(" where GNRTANNFUELCONSID=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
            	utilObj.addInParam(id);
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While updating annualtuneup Info");
                ex.initCause(e);
                throw ex;
            }

    	}

    
//****************************************ENGINE RUNNING HRS ******************************************//
    public static class GeneratorEngineRunningHrsEntity
    {

        public static GeneratorEngineRunningHrsVo findByPrimaryKey(int i)
            throws TrackingException
        {
            String s = "select GNRTANNENGINECONSID,GENERATORID,YEAR, ANNUALCONSUMP,DAILYCONSUMP,MONTHLYCONSUMP  from GENERATORENGINECONSUMPTION where GNRTANNENGINECONSID=?";
            GeneratorEngineRunningHrsVo generatorenginerunninghrsvo = null;
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                List list = sqlutil.execQueryUsingConstructor(s, com.eespc.tracking.bo.GeneratorEngineRunningHrsVo.class);
                if(list != null && list.size() > 0)
                    generatorenginerunninghrsvo = (GeneratorEngineRunningHrsVo)list.get(0);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("findByPrimaryKey(").append(i).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return generatorenginerunninghrsvo;
        }

        public static List getList(int i)
            throws TrackingException
        {
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("select GNRTANNENGINECONSID,GENERATORID,YEAR, ANNUALCONSUMP,DAILYCONSUMP,MONTHLYCONSUMP  from GENERATORENGINECONSUMPTION where GENERATORID =?");
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.GeneratorEngineRunningHrsVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getList(").append(i).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return ((List) (obj));
        }

        public static int add(GeneratorEngineRunningHrsVo generatorenginerunninghrsvo)
            throws TrackingException
        {
            int i = -99;
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("insert into GENERATORENGINECONSUMPTION ");
            stringbuffer.append("(GNRTANNENGINECONSID, GENERATORID, YEAR, ");
            stringbuffer.append(" ANNUALCONSUMP, DAILYCONSUMP, MONTHLYCONSUMP) ");
            stringbuffer.append(" values (null,");
            stringbuffer.append(generatorenginerunninghrsvo.getGeneratorId()).append(",");
            stringbuffer.append("'").append(generatorenginerunninghrsvo.getYear()).append("',");
            stringbuffer.append("'").append(generatorenginerunninghrsvo.getAnnualConsump()).append("',");
            stringbuffer.append("'").append(generatorenginerunninghrsvo.getDailyConsump()).append("',");
            stringbuffer.append("'").append(generatorenginerunninghrsvo.getMonthlyConsump()).append("'");
            stringbuffer.append(")");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                i = sqlutil.insert(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While Adding Generator Engine Running Hrs Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            return i;
        }

        public static void update(GeneratorEngineRunningHrsVo generatorenginerunninghrsvo)
            throws TrackingException
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("update GENERATORENGINECONSUMPTION ");
            stringbuffer.append("set year=?, ANNUALCONSUMP=?, ");
            stringbuffer.append("    DAILYCONSUMP=?, MONTHLYCONSUMP=? ");
            stringbuffer.append(" where GNRTANNENGINECONSID=?");
            SqlUtil sqlutil = new SqlUtil();
            try
            {
                sqlutil.addInParam(generatorenginerunninghrsvo.getYear());
                sqlutil.addInParam(generatorenginerunninghrsvo.getAnnualConsump());
                sqlutil.addInParam(generatorenginerunninghrsvo.getDailyConsump());
                sqlutil.addInParam(generatorenginerunninghrsvo.getMonthlyConsump());
                sqlutil.addInParam(new Integer(generatorenginerunninghrsvo.getId()));
                sqlutil.execForDmlUsingQuery(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While updating Generator Engine Running Hrs Info");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
        }

        public GeneratorEngineRunningHrsEntity()
        {
        }
    }   
    
    
	public static void deleteEngineRunningHrs(int id)
            throws TrackingException
        {
        StringBuffer buff = new StringBuffer();
            buff.append("delete from generatorengineconsumption");
            buff.append(" where GNRTANNENGINECONSID=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
            	utilObj.addInParam(id);
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While deleting Engine running hrs Info");
                ex.initCause(e);
                throw ex;
            }

    	}
    	
    	
  //********************************************Ends*********************************************//
    public static GeneratorVo findByPrimaryKey(int i)
        throws TrackingException
    {
        String s = "select * from building bld, generator gnr where bld.buildingid = gnr.buildingid and gnr.GENERATORID =?";
        GeneratorVo generatorvo = null;
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            List list = sqlutil.execQueryUsingConstructor(s, com.eespc.tracking.bo.GeneratorVo.class);
            if(list != null && list.size() > 0)
                generatorvo = (GeneratorVo)list.get(0);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("findByPrimaryKey(").append(i).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return generatorvo;
    }

    public static int add(GeneratorVo generatorvo)
        throws TrackingException
    {
        if(log.isDebugEnabled())
            log.debug((new StringBuilder()).append("To insert,").append(generatorvo).toString());
        int i = -99;
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("insert into generator (");
        stringbuffer.append("GENERATORID,BUILDINGID,FACILITYDESIGNATEDID,");
        stringbuffer.append("FLOOR,STATEID,PRIMARYUSE,YEARINSTALLED,STATUS,DISCONNECTEDDATE,");
        stringbuffer.append("MANUFACTURER,MODEL,SERIAL,BURNERMAKE,");
        stringbuffer.append("BURNERMODEL,CAPACITY,BURNERTYPE,PRIMARYFUEL,");
        stringbuffer.append("SECONDARYFUEL,FUELFROM,HASDAYTANK,DAYTANKFROM,");
        stringbuffer.append("NYCDOB,MEA,DEP,SECHDUELC,PLANAPPROVAL,");
        stringbuffer.append("STACKFROM,ISRECORDSINBOUNDBOOK,DECDEPPERMITOBTAINED,ISSTACKTEST,");
        stringbuffer.append("ISSTACKPROTSUBMITTED,TESTCONDUCTEDBY,TESTRPTSUBMITED,");
        stringbuffer.append("COMPLYWITHNOXPLAN,RETESTPLANNED,NEXTSTACKTESTDATE,NEXTSTACKTESTDATENOTE,");
        stringbuffer.append("FUELCAPPINGUNDERLIMIT,DEPPERMITOBTAINED,ACTIONTAKEN,compyWithPMRactPlan,gasfuelgapping,gasemmisionfactor,oilfuelgapping,oilemmisionfactor,StackTestDate,ProtocalSubmittalDate,testPassed,fuelgapping,G_OILSO2,G_GASSO2,G_SO2ALLOWABLE,G_NOXALLOWABLE,DOBSIGNOFF,FIREDEPARTMENTAPPROVAL,PERMITSTATUS,PERMITEXPIRE,DOBFILING,CAPABLEFUEL,OILFIRING) ");
        stringbuffer.append(" values (null,");
        stringbuffer.append(generatorvo.getBuildingId()).append(",");
        stringbuffer.append("'").append(generatorvo.getFacilityDesignatedId()).append("',");
        stringbuffer.append("'").append(generatorvo.getFloor()).append("',");
        stringbuffer.append("'").append(generatorvo.getStateId()).append("',");
        stringbuffer.append(generatorvo.getPrimaryUse()).append(",");
        stringbuffer.append("'").append(generatorvo.getYearInstalled()).append("',");
        stringbuffer.append("").append(generatorvo.getStatus()).append(",");
        if(generatorvo.getDisconnecteddate().equals(null))
            stringbuffer.append("").append(generatorvo.getDisconnecteddate()).append(",");
        else
            stringbuffer.append("'").append(generatorvo.getDisconnecteddate()).append("',");
        stringbuffer.append("'").append(generatorvo.getManufacturer()).append("',");        
        stringbuffer.append("'").append(generatorvo.getModel()).append("',");
        stringbuffer.append("'").append(generatorvo.getSerial()).append("',");
        stringbuffer.append("'").append(generatorvo.getBurnerMake()).append("',");
        stringbuffer.append("'").append(generatorvo.getBurnerModel()).append("',");
        stringbuffer.append("'").append(generatorvo.getCapacity()).append("',");
        stringbuffer.append(generatorvo.getBurnerType()).append(",");
        stringbuffer.append(generatorvo.getPrimaryFuel()).append(",");
        stringbuffer.append(generatorvo.getSecondaryFuel()).append(",");
        stringbuffer.append(generatorvo.getFuelFrom()).append(",");
        if(generatorvo.isHasDayTank())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        stringbuffer.append(generatorvo.getDayTankFrom()).append(",");
        stringbuffer.append("'").append(generatorvo.getNycDob()).append("',");
        stringbuffer.append("'").append(generatorvo.getMea()).append("',");
        stringbuffer.append("'").append(generatorvo.getDep()).append("',");
        if(generatorvo.isSechduelC())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        if(generatorvo.isPlanApproval())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        stringbuffer.append(generatorvo.getStackFrom()).append(",");
        if(generatorvo.isRecordsInBoundBook())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        if(generatorvo.isDecPermitObtained())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");

        /*if(generatorvo.isStackProtSubmitted())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");*/
        stringbuffer.append("'"+generatorvo.isStackTest()+"'").append(",");
		stringbuffer.append("'"+generatorvo.isStackProtSubmitted()+"'").append(",");
        stringbuffer.append("'").append(generatorvo.getTestConductedBy()).append("',");
        if(generatorvo.isTestRptSubmited())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        if(generatorvo.isComplyWithNoxPlan())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        if(generatorvo.isRetestPlanned())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        if(isNotEmpty(generatorvo.getNextStackTestDate()))
            stringbuffer.append("'").append(generatorvo.getNextStackTestDate()).append("',");
        else
            stringbuffer.append("null,");
        stringbuffer.append("'").append(generatorvo.getNextStackTestDateNote()).append("',");
        if(generatorvo.isFuelCappingUnderLimit())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");
        if(generatorvo.isDepPermitObtained())
            stringbuffer.append("'").append("Y").append("',");
        else
            stringbuffer.append("'").append("N").append("',");

        stringbuffer.append("'").append(generatorvo.getActionTaken()).append("',");

        stringbuffer.append("'").append(generatorvo.getCompyWithPMRactPlan()).append("',");
        stringbuffer.append("'").append(generatorvo.getGasfuelgapping()).append("',");
        stringbuffer.append("'").append(generatorvo.getGasemmisionfactor()).append("',");
        stringbuffer.append("'").append(generatorvo.getOilfuelgapping()).append("',");
        stringbuffer.append("'").append(generatorvo.getOilemmisionfactor()).append("',");

        if(isNotEmpty(generatorvo.getStackTestDate()))
            stringbuffer.append("'").append(generatorvo.getStackTestDate()).append("',");
        else
            stringbuffer.append("null,");

        if(isNotEmpty(generatorvo.getProtocalSubmittalDate()))
            stringbuffer.append("'").append(generatorvo.getProtocalSubmittalDate()).append("',");
        else
            stringbuffer.append("null,");

        stringbuffer.append("'").append(generatorvo.getTestPassed()).append("',");
        stringbuffer.append("'").append(generatorvo.getFuelgapping()).append("',");
        stringbuffer.append("'").append(generatorvo.getG_oilso2()).append("',");
		stringbuffer.append("'").append(generatorvo.getG_gasso2()).append("',");
		stringbuffer.append("'").append(generatorvo.getG_so2allowable()).append("',");
		stringbuffer.append("'").append(generatorvo.getG_noxallowable()).append("',");
		stringbuffer.append("'").append(generatorvo.getDobsignoff()).append("',");
		stringbuffer.append("'").append(generatorvo.getFiredepartmentapproval()).append("',");
		stringbuffer.append("'").append(generatorvo.getPermitStatus()).append("',");
		stringbuffer.append("'").append(generatorvo.getPermitExpire()).append("',");
		stringbuffer.append("'").append(generatorvo.getDobFiling()).append("',");
		stringbuffer.append("'").append(generatorvo.getCapablefuel()).append("',");
		
		
		if(String.valueOf(generatorvo.isOilFiring()).equalsIgnoreCase("true"))
			stringbuffer.append("'").append("TRUE").append("'");
        	else if (String.valueOf(generatorvo.isOilFiring()).equalsIgnoreCase("false"))
        	stringbuffer.append("'").append("FALSE").append("'");	
        	else
        	stringbuffer.append("null");
		
		
	//	stringbuffer.append("'").append(generatorvo.getOilFiring()).append("'");
        stringbuffer.append(")");
        SqlUtil sqlutil = new SqlUtil();
        try
        {
            i = sqlutil.insert(stringbuffer.toString());
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException("While Adding Generator");
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return i;
    }

    public static void update(GeneratorVo generatorvo)
        throws TrackingException
    {
        if(log.isDebugEnabled())
            log.debug((new StringBuilder()).append("To update,").append(generatorvo).toString());
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("update generator set ");
        stringbuffer.append("FACILITYDESIGNATEDID = ?, ");
        stringbuffer.append("FLOOR = ?, ");
        stringbuffer.append("STATEID = ?, ");
        stringbuffer.append("PRIMARYUSE = ?,");
        stringbuffer.append("YEARINSTALLED = ?,");
        stringbuffer.append("STATUS=?, ");
        stringbuffer.append("DISCONNECTEDDATE=?, ");
        stringbuffer.append("MANUFACTURER = ?, ");      
        stringbuffer.append("MODEL = ?, ");
        stringbuffer.append("SERIAL = ?, ");
        stringbuffer.append("BURNERMAKE = ?, ");
        stringbuffer.append("BURNERMODEL = ?, ");
        stringbuffer.append("CAPACITY = ?, ");
        stringbuffer.append("BURNERTYPE = ?, ");
        stringbuffer.append("PRIMARYFUEL = ?, ");
        stringbuffer.append("SECONDARYFUEL = ?, ");
        stringbuffer.append("FUELFROM = ?, ");
        stringbuffer.append("HASDAYTANK = ?, ");
        stringbuffer.append("DAYTANKFROM = ?,");
        stringbuffer.append("NYCDOB = ?, ");
        stringbuffer.append("MEA = ?, ");
        stringbuffer.append("DEP = ?, ");
        stringbuffer.append("SECHDUELC = ?, ");
        stringbuffer.append("PLANAPPROVAL = ?, ");
        stringbuffer.append("STACKFROM = ?,");
        stringbuffer.append("ISRECORDSINBOUNDBOOK = ?, ");
        stringbuffer.append("DECDEPPERMITOBTAINED = ?, ");
        stringbuffer.append("ISSTACKTEST = ?, ");
        stringbuffer.append("ISSTACKPROTSUBMITTED = ?, ");
        stringbuffer.append("TESTCONDUCTEDBY = ?, ");
        stringbuffer.append("TESTRPTSUBMITED = ?, ");
        stringbuffer.append("COMPLYWITHNOXPLAN = ?, ");
        stringbuffer.append("RETESTPLANNED = ?,");
        stringbuffer.append("NEXTSTACKTESTDATE = ?,");
        stringbuffer.append("NEXTSTACKTESTDATENOTE = ?,");
        stringbuffer.append("FUELCAPPINGUNDERLIMIT = ?,");
        stringbuffer.append("DEPPERMITOBTAINED  = ?,");
        stringbuffer.append("ACTIONTAKEN  = ?,");
        stringbuffer.append("compyWithPMRactPlan  = ?,");
        stringbuffer.append("gasfuelgapping  = ?,");
        stringbuffer.append("gasemmisionfactor  = ?,");
        stringbuffer.append("oilfuelgapping  = ?,");
        stringbuffer.append("oilemmisionfactor  = ?,");
        stringbuffer.append("StackTestDate  = ?,");
        stringbuffer.append("ProtocalSubmittalDate  = ?,");
        stringbuffer.append("testPassed  = ?,");
        stringbuffer.append("fuelgapping  = ?,");
        stringbuffer.append("G_OILSO2=?,");
		stringbuffer.append("G_GASSO2=?,");
		stringbuffer.append("G_SO2ALLOWABLE=?,");
		stringbuffer.append("G_NOXALLOWABLE=?,");
		stringbuffer.append("DOBSIGNOFF=?,");
		stringbuffer.append("FIREDEPARTMENTAPPROVAL=?,");
		stringbuffer.append("PERMITSTATUS=?, ");
		stringbuffer.append("PERMITEXPIRE=?, ");
		stringbuffer.append("DOBFILING=?, ");
		stringbuffer.append("CAPABLEFUEL=?, ");
		stringbuffer.append("OILFIRING=? ");
        stringbuffer.append(" where GENERATORID=?");
        SqlUtil sqlutil = new SqlUtil();
        sqlutil.addInParam(generatorvo.getFacilityDesignatedId());
        sqlutil.addInParam(generatorvo.getFloor());
        sqlutil.addInParam(generatorvo.getStateId());
        sqlutil.addInParam(String.valueOf(generatorvo.getPrimaryUse()));
        sqlutil.addInParam(generatorvo.getYearInstalled());
        sqlutil.addInParam(String.valueOf(generatorvo.getStatus()));
        sqlutil.addInParam(generatorvo.getDisconnecteddate());
        sqlutil.addInParam(generatorvo.getManufacturer());   
        sqlutil.addInParam(generatorvo.getModel());
        sqlutil.addInParam(generatorvo.getSerial());
        sqlutil.addInParam(generatorvo.getBurnerMake());
        sqlutil.addInParam(generatorvo.getBurnerModel());
        sqlutil.addInParam(generatorvo.getCapacity());
        sqlutil.addInParam(String.valueOf(generatorvo.getBurnerType()));
        sqlutil.addInParam(String.valueOf(generatorvo.getPrimaryFuel()));
        sqlutil.addInParam(String.valueOf(generatorvo.getSecondaryFuel()));
        sqlutil.addInParam(String.valueOf(generatorvo.getFuelFrom()));
        if(generatorvo.isHasDayTank())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        sqlutil.addInParam(String.valueOf(generatorvo.getDayTankFrom()));
        sqlutil.addInParam(generatorvo.getNycDob());
        sqlutil.addInParam(generatorvo.getMea());
        sqlutil.addInParam(generatorvo.getDep());
        if(generatorvo.isSechduelC())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        if(generatorvo.isPlanApproval())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        sqlutil.addInParam(String.valueOf(generatorvo.getStackFrom()));
        if(generatorvo.isRecordsInBoundBook())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        if(generatorvo.isDecPermitObtained())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
/*        if(generatorvo.isStackProtSubmitted())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");*/
		sqlutil.addInParam(generatorvo.isStackTest());
        sqlutil.addInParam(generatorvo.isStackProtSubmitted());
        sqlutil.addInParam(generatorvo.getTestConductedBy());
        if(generatorvo.isTestRptSubmited())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        if(generatorvo.isComplyWithNoxPlan())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        if(generatorvo.isRetestPlanned())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        sqlutil.addInParam(checkNullAndFill(generatorvo.getNextStackTestDate(), null));
        sqlutil.addInParam(generatorvo.getNextStackTestDateNote());
        if(generatorvo.isFuelCappingUnderLimit())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        if(generatorvo.isDepPermitObtained())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        sqlutil.addInParam(generatorvo.getActionTaken());

        sqlutil.addInParam(generatorvo.getCompyWithPMRactPlan());
        sqlutil.addInParam(generatorvo.getGasfuelgapping());
        sqlutil.addInParam(generatorvo.getGasemmisionfactor());
        sqlutil.addInParam(generatorvo.getOilfuelgapping());
        sqlutil.addInParam(generatorvo.getOilemmisionfactor());

        sqlutil.addInParam(checkNullAndFill(generatorvo.getStackTestDate(), null));
        sqlutil.addInParam(checkNullAndFill(generatorvo.getProtocalSubmittalDate(), null));

        sqlutil.addInParam(generatorvo.getTestPassed());
        sqlutil.addInParam(generatorvo.getFuelgapping());
        sqlutil.addInParam(generatorvo.getG_oilso2());
		sqlutil.addInParam(generatorvo.getG_gasso2());
		sqlutil.addInParam(generatorvo.getG_so2allowable());
		sqlutil.addInParam(generatorvo.getG_noxallowable());
		sqlutil.addInParam(generatorvo.getDobsignoff());
		sqlutil.addInParam(generatorvo.getFiredepartmentapproval());
		sqlutil.addInParam(generatorvo.getPermitStatus());
		sqlutil.addInParam(generatorvo.getPermitExpire());
		sqlutil.addInParam(generatorvo.getDobFiling());
		sqlutil.addInParam(generatorvo.getCapablefuel());
		//sqlutil.addInParam(generatorvo.getOilFiring());	
		
	
	if(String.valueOf(generatorvo.isOilFiring()).equalsIgnoreCase("true"))
	sqlutil.addInParam("TRUE");
	else if(String.valueOf(generatorvo.isOilFiring()).equalsIgnoreCase("false"))
	sqlutil.addInParam("FALSE");
	else
	sqlutil.addInParam("null");
		
        sqlutil.addInParam(String.valueOf(generatorvo.getId()));
        try
        {
            sqlutil.execForDmlUsingQuery(stringbuffer.toString());
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException("While Updating Generator.");
            trackingexception.initCause(exception);
            throw trackingexception;
        }
    }

    public static void delete(GeneratorVo generatorvo)
        throws TrackingException
    {
        String as[] = {
            "delete from generatorpermitinfo where GENERATORID=?", "delete from generatorfuelconsumption where GENERATORID=?", "delete from generator where GENERATORID=?"
        };
        for(int i = 0; i <= 2; i++)
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append(as[i]);
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(generatorvo.getId()));
            try
            {
                sqlutil.execForDmlUsingQuery(stringbuffer.toString());
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException("While Updating Generator.");
                trackingexception.initCause(exception);
                throw trackingexception;
            }
        }

    }
//*****fuel consumption
    public static BoilerFuelConsumptionVo findFuelConsumption(int i)
        throws TrackingException
    {
        return GeneratorFuelConsumptionEntity.findByPrimaryKey(i);
    }

    public static List getFuelConsumptionList(int i)
        throws TrackingException
    {
        return FuelConsumptionEntity.getFuelConsumption(i, ResourcesTypeEnum.GNERATOR.getCode());
    }

    public static List geto_FuelConsumptionList(int i)
        throws TrackingException
    {
        return FuelConsumptionEntity.geto_FuelConsumption(i, ResourcesTypeEnum.GNERATOR.getCode());
    }


    public static int addFuelConsumption(FuelConsumptionVo fuelconsumptionvo)
        throws TrackingException
    {
        return FuelConsumptionEntity.add(fuelconsumptionvo, ResourcesTypeEnum.GNERATOR.getCode());
    }

    public static void updateFuelConsumption(FuelConsumptionVo fuelconsumptionvo)
        throws TrackingException
    {
        FuelConsumptionEntity.update(fuelconsumptionvo, ResourcesTypeEnum.GNERATOR.getCode());
    }
    
 //***************engine running hrs   
    
    
    
    
    public static GeneratorEngineRunningHrsVo findEngineRunningHrs(int i)
        throws TrackingException
    {
        return GeneratorEngineRunningHrsEntity.findByPrimaryKey(i);
    }

    public static List getEngineRunningHrsList(int i)
        throws TrackingException
    {
        return EngineRunningHrsEntity.getEngineRunningHrs(i, ResourcesTypeEnum.GNERATOR.getCode());
    }

    public static List geto_PressureTestHrsList(int i)
        throws TrackingException
    {
        return EngineRunningHrsEntity.geto_PressureTestHrs(i, ResourcesTypeEnum.GNERATOR.getCode());
    }
    public static List getg_PressureTestHrsList(int i)
            throws TrackingException
    {
            return EngineRunningHrsEntity.getg_PressureTestHrs(i, ResourcesTypeEnum.GNERATOR.getCode());
    }
    
    public static List getanu_PressureTestHrsList(int i)
            throws TrackingException
    {
            return EngineRunningHrsEntity.getanu_PressureTestHrs(i, ResourcesTypeEnum.GNERATOR.getCode());
    }

    public static int addEngineRunningHrs(EngineRunningHrsVo enginerunninghrsvo)
        throws TrackingException
    {
        return EngineRunningHrsEntity.add(enginerunninghrsvo, ResourcesTypeEnum.GNERATOR.getCode());
    }

    public static void updateEngineRunningHrs(EngineRunningHrsVo enginerunninghrsvo)
        throws TrackingException
    {
        EngineRunningHrsEntity.update(enginerunninghrsvo, ResourcesTypeEnum.GNERATOR.getCode());
    }
    
    
//**********************************permit*******//
    public static BoilerPermitInfoVo findPermit(int i)
        throws TrackingException
    {
        return GeneratorPermitEntity.findByPrimaryKey(i);
    }

    public static List getPermitList(int i)
        throws TrackingException
    {
        return GeneratorPermitEntity.getList(i);
    }

    public static int addPermit(BoilerPermitInfoVo boilerpermitinfovo)
        throws TrackingException
    {
        return GeneratorPermitEntity.add(boilerpermitinfovo);
    }

    public static void updatePermit(BoilerPermitInfoVo boilerpermitinfovo)
        throws TrackingException
    {
        GeneratorPermitEntity.update(boilerpermitinfovo);
    }

    public static void deletePermit(BoilerPermitInfoVo boilerpermitinfovo)
        throws TrackingException
    {
        GeneratorPermitEntity.delete(boilerpermitinfovo);
    }
	
  //**********************cfr**************************************************//
	/*public static int addCfrPermit(GeneratorCfrPermitInfoVo generatorcfrpermitinfovo)
        throws TrackingException
    {
        return GeneratorCfrPermitEntity.add(generatorcfrpermitinfovo);
    }

    public static void updateCfrPermit(GeneratorCfrPermitInfoVo generatorcfrpermitinfovo)
        throws TrackingException
    {
        GeneratorCfrPermitEntity.update(generatorcfrpermitinfovo);
    }

    public static void deleteCfrPermit(GeneratorCfrPermitInfoVo generatorcfrpermitinfovo)
        throws TrackingException
    {
        GeneratorCfrPermitEntity.delete(generatorcfrpermitinfovo);
    }
    
     public static GeneratorCfrPermitInfoVo findCfrPermit(int i)
        throws TrackingException
    {
        return GeneratorCfrPermitEntity.findByPrimaryKey(i);
    }

    public static List getCfrPermitList(int i)
        throws TrackingException
    {
        return GeneratorCfrPermitEntity.getList(i);
    }*/
   //****************************************************************************// 
    public static List getNycdepReport(int facilityid)
    {
        List result;
        LinkedHashMap hash4Unique;
        result = new ArrayList();
        StringBuffer query = new StringBuffer();
        query.append("select blr.DOBSIGNOFF,blr.FIREDEPARTMENTAPPROVAL,blr.FACILITYDESIGNATEDID,blr.DOBFILING,blr.ProtocalSubmittalDate,blr.generatorid, blr.manufacturer,blr.model,blr.serial,bld.buildingname,blr.yearinstalled,blr.primaryfuel,blr.capacity,blr.NYCDOB,blr.dep,bpf.expirationdate,");
        query.append("blr.PRIMARYUSE,blr.ISSTACKTEST,blr.NEXTSTACKTESTDATE,blr.StackTestDate,blr.PERMITSTATUS,blr.PERMITEXPIRE ");
        query.append("from  building bld,generator blr  left join generatorpermitinfo bpf ");
        query.append("on blr.generatorid=bpf.generatorid and bpf.depid='1' ");
        query.append("where (blr.STATUS='1' or blr.STATUS='5') and blr.buildingid=bld.buildingid ");
        query.append("and bld.facilityid=");
        query.append(facilityid);
        query.append(" order by blr.generatorid asc, blr.buildingid asc, bpf.gnrtpermitid  asc");
        SqlUtil utilObj = new SqlUtil();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        hash4Unique = new LinkedHashMap();
        long startTime = System.currentTimeMillis();
        try
        {
            conn = utilObj.getConnection();
            pstmt = conn.prepareStatement(query.toString());

            rst = pstmt.executeQuery();
            startTime = System.currentTimeMillis();
            Hashtable tempHash;
            String tempBoilerId;
            for(; rst.next(); hash4Unique.put(tempBoilerId, tempHash))
            {
                tempHash = new Hashtable();
                tempBoilerId = rst.getString("generatorid");
                tempHash.put("generatorid1", tempBoilerId);
                tempHash.put("generatorid", UtilityObject.checkNullAndFill(rst.getString("FACILITYDESIGNATEDID"), "-"));
                tempHash.put("FIREDEPARTMENTAPPROVAL", UtilityObject.checkNullAndFill(rst.getString("FIREDEPARTMENTAPPROVAL"), "-"));
                tempHash.put("manufacturer", UtilityObject.checkNullAndFill(rst.getString("manufacturer"), "-"));
                tempHash.put("model", UtilityObject.checkNullAndFill(rst.getString("model"), "-"));
                tempHash.put("serial", UtilityObject.checkNullAndFill(rst.getString("serial"), "-"));
                tempHash.put("buildingname", UtilityObject.checkNullAndFill(rst.getString("buildingname"), "-"));
                tempHash.put("yearinstalled", UtilityObject.checkNullAndFill(rst.getString("yearinstalled"), "-"));
                String tempStr = rst.getString("capacity");
                long lngCapacity = -99L;
                if(UtilityObject.isNotEmpty(tempStr))
                {
                    try
                    {
                        lngCapacity = Long.parseLong(tempStr);
                    }
                    catch(NumberFormatException numberformatexception) { }
                    if(lngCapacity > 0L)
                    {
                        tempHash.put("hp",String.valueOf(round((double)lngCapacity * 1.3400000000000001D,2)));
                        tempHash.put("kw", tempStr);
                        tempHash.put("mmbtu",String.valueOf(round((double)lngCapacity * 0.01D,2)));
                    }
                } else
                {
                    tempHash.put("hp", "");
                    tempHash.put("kw", "");
                    tempHash.put("mmbtu", "");
                }



               /*int tempInt = rst.getInt("secondaryfuel");
                if(tempInt > 0)
                {
                    FurnaceOilTypeEnum sEnum = FurnaceOilTypeEnum.get(tempInt);
                    if(sEnum != null)
                    {
                        tempHash.put("fuel", sEnum.getName());
                        int oilFRate = -99;
                        int toCheck = sEnum.getCode();
                        int factor = 0;
                        if(toCheck == 2)
                            factor = 140;
                        else
                        if(toCheck == 3)
                            factor = 145;
                        else
                        if(toCheck == 4)
                            factor = 150;
                        if(factor > 0)
                            tempHash.put("gph", (new StringBuffer(String.valueOf((lngCapacity * 0xf4240L) / (long)factor))).toString());
                    }
                }
                */

                int tempInt = rst.getInt("primaryfuel");
                if(tempInt > 0)
                {
                    FurnaceOilTypeEnum sEnum = FurnaceOilTypeEnum.get(tempInt);
                    if(sEnum != null)
                    {
                        tempHash.put("fuel", sEnum.getName());

                        if(tempInt==5)
                        {
                        double te=Double.parseDouble(String.valueOf(lngCapacity));
                        double tep=round(((te)/14),2);
                        tempHash.put("gph", (String.valueOf(tep)));
                    	}
                    	else if(tempInt==1)
                        {
                        double te=Double.parseDouble(String.valueOf(lngCapacity));
                        double tep=round(((te)*10),2);
                        tempHash.put("gph", (String.valueOf(tep)));
                        }
                        else
                        {
                        tempHash.put("gph", "-");
                        }
                    }
                 }
				
				//*******************************DOB Compliance Status***********************//
				String dobfiling = UtilityObject.checkNullAndFill(rst.getString("DOBFILING"), "-");
                String dobnumber = rst.getString("NYCDOB");                
				String dobsignoff = rst.getString("DOBSIGNOFF");				
								
                if(dobfiling.equalsIgnoreCase("Y"))
                {
                tempHash.put("NYCDOB", UtilityObject.checkNullAndFill(dobnumber, "No Filing"));
                tempHash.put("DOBSIGNOFF", UtilityObject.checkNullAndFill(dobsignoff, "-"));
                }
                else if(dobfiling.equalsIgnoreCase("N"))
                {
                tempHash.put("NYCDOB","No Filing");
                tempHash.put("DOBSIGNOFF","N/A");
                }
                else if(dobfiling.equalsIgnoreCase("N/A"))
                {
                tempHash.put("NYCDOB","N/A");
                tempHash.put("DOBSIGNOFF","N/A");	
                }else
                {
                tempHash.put("NYCDOB","N/A");
                tempHash.put("DOBSIGNOFF","N/A");
                }
                

           		String dobcompliancestatus="";
           		
           		    if(dobfiling.equalsIgnoreCase("-"))
		            {
						tempHash.put("dobcompliancestatus", "TBD");
			            dobcompliancestatus="TBD";		            	
		            }
		            else if(dobfiling.equalsIgnoreCase("N"))
		            {
			          tempHash.put("dobcompliancestatus", "Non Compliance");
			           dobcompliancestatus="Non Compliance";  	
		            } 				
		            else if(!dobnumber.equals("No Filing") && dobsignoff.equalsIgnoreCase("N"))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }		           
		            else if(!UtilityObject.isNotEmpty(dobnumber) && dobsignoff.equalsIgnoreCase("Y"))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }  
		            else if(!UtilityObject.isNotEmpty(dobnumber) && dobsignoff.equalsIgnoreCase("N"))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }
		           /* else if(!UtilityObject.isNotEmpty(dobnumber) && !UtilityObject.isNotEmpty(dobsignoff))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }*/		              	                          
		            else
					{
						tempHash.put("dobcompliancestatus", "Compliance");
						dobcompliancestatus="Compliance";
					}

                
				                
	                
				//********************Dob Filing ENDS*************************//
                

                String depPermitstatus = UtilityObject.checkNullAndFill(rst.getString("PERMITSTATUS"), "-");
				String depPermitStatusExpire = UtilityObject.checkNullAndFill(rst.getString("PERMITEXPIRE"), "-");


                String dep = rst.getString("dep");

                if(depPermitstatus.equalsIgnoreCase("Y"))
                {
                tempHash.put("dep", UtilityObject.checkNullAndFill(dep, "-"));
                }
                else if(depPermitstatus.equalsIgnoreCase("N"))
                {
                tempHash.put("dep","-");
                }
                else
                {
                tempHash.put("dep","N/A");
                }


                String exp=rst.getString("expirationdate");

                if(depPermitStatusExpire.equalsIgnoreCase("Y"))
                {
                tempHash.put("expirationdate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(exp), "-"));
                }
                else if(depPermitStatusExpire.equalsIgnoreCase("N"))
                {
                tempHash.put("expirationdate","-");
                }
                else
                {
                tempHash.put("expirationdate","N/A");
                }

			int j_1=0;

            try
            {
                 java.util.Date today=new java.util.Date();
            	 java.util.Date expi=null;
            	if(exp==null)
            	{
            	j_1=0;
            	}
            	else
            	{
            		expi=UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
            	}

		            	if(expi==null)
		            	{
		            		j_1=0;
		            	}

				            else if(expi!=null)
				            {

					            j_1 = today.compareTo(expi);
					            if(j_1 > 0)
					            {
					            	j_1=0;
					            }
					            else
					            {
					            	j_1=1;
					            }
		     }
		     else
		     {
		     j_1=0;
		     }

            }
            catch(Exception e)
            {
            System.out.println("Exhibit 6 :"+e)	;
            }
			
				
			
		    
            String compliancestatus1="";

			if(depPermitstatus.equalsIgnoreCase("Y") && dep.equals("-") && depPermitStatusExpire.equals("Y") && !UtilityObject.isNotEmpty(exp))
            {
            tempHash.put("compliancestatus1", "TBD");
            compliancestatus1="TBD";
            }
            else
            {

		            if(depPermitstatus.equals("Y") && dep.equals("-"))
		            {
		            tempHash.put("compliancestatus1", "Non Compliance");
		            compliancestatus1="Non Compliance";
		            }
		            else if(depPermitstatus.equals("N"))
		            {
		            tempHash.put("compliancestatus1", "Non Compliance");
		            compliancestatus1="Non Compliance";
		            }
		            else if(depPermitStatusExpire.equals("Y") && !UtilityObject.isNotEmpty(exp))
		            {
		            tempHash.put("compliancestatus1", "TBD");
		            compliancestatus1="TBD";
		            }
		            else if(depPermitStatusExpire.equals("N") && !UtilityObject.isNotEmpty(exp))
		            {
		            tempHash.put("compliancestatus1", "Non Compliance");
		            compliancestatus1="Non Compliance";
		            }
		            else if(depPermitStatusExpire.equals("Y") && j_1==0)
		            {
		            tempHash.put("compliancestatus1", "Non Compliance");
		            compliancestatus1="Non Compliance";
		            }
		            else
		            {
		            compliancestatus1="Compliance";
		            tempHash.put("compliancestatus1", "Compliance");
		            }

		    }

				 /* if(dep.equals("-") && !UtilityObject.isNotEmpty(exp))
            	  {
            		tempHash.put("compliancestatus1","TBD");
            		compliancestatus1="TBD";
            	  }
		          else if(UtilityObject.isNotEmpty(exp) && (new java.util.Date()).compareTo(exp_date)>0)
		          {
		            tempHash.put("compliancestatus1", "Non Compliance");
		            compliancestatus1="Non Compliance";
		          }
		          else
	              {
	                tempHash.put("compliancestatus1","Compliance");
	                compliancestatus1="Compliance";
	              }*/


               /* if(dep.equals("-") && !UtilityObject.isNotEmpty(exp))
                {
                tempHash.put("compliancestatus1","TBD");
                compliancestatus1="TBD";
                }
                else
                {
	                if(j_1==2)
	                {
	                tempHash.put("compliancestatus1","Compliance");
	                compliancestatus1="Compliance";
	                }
	                else if(j_1==0)
	                {
	                tempHash.put("compliancestatus1","TBD");
	                compliancestatus1="TBD";
	                }
	                else if(j_1==1)
	                {
	                tempHash.put("compliancestatus1","Non Compliance");
	                compliancestatus1="Non Compliance";
	                }
	                else
	                {
	                compliancestatus1="Non Compliance";
	                tempHash.put("compliancestatus1","Non Compliance");
	                }
                }*/
            int primaryuse = rst.getInt("PRIMARYUSE");

            String stackpermit="N";
     		if(primaryuse==2)
     		{
     		stackpermit="Y";
     		}
     		else
     		{
     		stackpermit="N";
     		}



			String dec=checkNullAndFill(rst.getString("ISSTACKTEST"),"N");

            String testdate=rst.getString("StackTestDate");
            String nexttestdate=rst.getString("NEXTSTACKTESTDATE");

            tempHash.put("stackpermit",stackpermit);

			if(stackpermit.equals("N"))
            {
             tempHash.put("stacktest","N/A");
             tempHash.put("lasttest","N/A");
             tempHash.put("nexttest","N/A");
             tempHash.put("compliancestatus2","Compliance");
            }
            else
            {
	             String t1=UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(testdate), "-");
	             String t2=UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(nexttestdate), "-");

	             tempHash.put("stacktest",dec);
	             tempHash.put("lasttest",t1);
	             tempHash.put("nexttest",t2);

	             if(dec.equals("N"))
	             {
	             tempHash.put("stacktest","N/A");
	             tempHash.put("lasttest","N/A");
	             tempHash.put("nexttest","N/A");
	             tempHash.put("compliancestatus2","Compliance");
	             }
	             else if(t1.equals("-") && t2.equals("-"))
	             {
	             tempHash.put("compliancestatus2","Non Compliance");
	             }
	             else
	             {
	             tempHash.put("compliancestatus2","Compliance");
	             }
          	 }

            }

        }
        catch(Exception e)
        {
            log.error("getNycdepReport(" + facilityid + ")", e);
        }
        finally
        {
            log.debug("Time taken for report query " + (System.currentTimeMillis() - startTime) + " ms.");
            if(conn != null)
            {
                SqlUtil.close(rst);
                SqlUtil.close(pstmt);
                utilObj.closeConnection();
            }
        }
        if(hash4Unique != null)
        {
            Set set = hash4Unique.keySet();
            for(Iterator iter = set.iterator(); iter.hasNext(); result.add(hash4Unique.get(iter.next())));
        }
        return result;
    }

	public static String checkNullAndFilldate(String s, String s1)
    {
        if(isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
        {
            String as[] = s.split("-");
            String s2 = (new StringBuilder()).append(as[1]).append("/").append(as[2]).append("/").append(as[0]).toString();
            return s2;
        } else
        {
            return s1;
        }
    }

    public static String checkNullAndFill(String s, String s1)
    {
        if(isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
            return s;
        else
            return s1;
    }

    public static boolean isNotEmpty(String s)
    {
        return s != null && s.trim().length() > 0;
    }

    public static double round(double d, int i)
    {
        double d1;
        for(d1 = 1.0D; i-- > 0; d1 *= 10D);
        return (double)Math.round(d * d1) / d1;
    }

    public static List getFacilityStackList(int i)
        throws TrackingException
    {
        Object obj = new ArrayList();
        String s = "select stk.GENERATORID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, stk.SERIAL, stk.CAPACITY, bldg.BUILDINGNAME from generator stk, BUILDING bldg where stk.buildingid=bldg.buildingid and bldg.facilityid=?";
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            obj = sqlutil.execQueryUsingConstructor(s, com.eespc.tracking.bo.GeneratorListVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("Getting Generator List for facility id =").append(i).toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return (List)(List)obj;
    }

    public static List getFuelConsumptionyearList(int boilerId,String year)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	FuelConsumptionEntity.getFuelConsumptionyear(boilerId, ResourcesTypeEnum.GNERATOR.getCode(),year);
        }
        catch(Exception e)
        {
        	System.out.println("Error:"+e);
        }

        return FuelConsumptionEntity.getFuelConsumptionyear(boilerId, ResourcesTypeEnum.GNERATOR.getCode(),year);
    }

    public static List geto_FuelConsumptionyearList(int boilerId,String year)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	FuelConsumptionEntity.geto_FuelConsumptionyear(boilerId, ResourcesTypeEnum.GNERATOR.getCode(),year);
        }
        catch(Exception e)
        {
        	System.out.println("Error:"+e);
        }

        return FuelConsumptionEntity.geto_FuelConsumptionyear(boilerId, ResourcesTypeEnum.GNERATOR.getCode(),year);
    }
    
    
    
       public static List getEngineRunningHrsyearList(int generatorId,String year)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	EngineRunningHrsEntity.getEngineRunningHrsyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
        }
        catch(Exception e)
        {
        	System.out.println("Error:"+e);
        }

        return EngineRunningHrsEntity.getEngineRunningHrsyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
    }
       
       
       
       public static List geto_PressureTestyearList(int generatorId,String year)
    	        throws TrackingException
    	    {

    	        try
    	        {
    	        List xx=null;
    	        xx=	EngineRunningHrsEntity.geto_PressureTestyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
    	        }
    	        catch(Exception e)
    	        {
    	        	System.out.println("Error:"+e);
    	        }

    	        return EngineRunningHrsEntity.geto_PressureTestyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
    	    }
       
       
       public static List getg_PressureTestyearList(int generatorId,String year)
   	        throws TrackingException
   	    {

   	        try
   	        {
   	        List xx=null;
   	        xx=	EngineRunningHrsEntity.getg_PressureTestyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
   	        }
   	        catch(Exception e)
   	        {
   	        	System.out.println("Error:"+e);
   	        }

   	        return EngineRunningHrsEntity.getg_PressureTestyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
   	    }
       
       public static List getanu_PressureTestyearList(int generatorId,String year)
      	        throws TrackingException
      	    {

      	        try
      	        {
      	        List xx=null;
      	        xx=	EngineRunningHrsEntity.getanu_PressureTestyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
      	        }
      	        catch(Exception e)
      	        {
      	        	System.out.println("Error:"+e);
      	        }

      	        return EngineRunningHrsEntity.getanu_PressureTestyear(generatorId, ResourcesTypeEnum.GNERATOR.getCode(),year);
      	    }


    private static Log log = LogFactory.getLog(com.eespc.tracking.entity.GeneratorEntity.class);

}