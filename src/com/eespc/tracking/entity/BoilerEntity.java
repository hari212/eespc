package com.eespc.tracking.entity;

import com.eespc.tracking.bo.*;
import com.eespc.tracking.bo.myenum.FurnaceOilTypeEnum;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;
import java.io.PrintStream;
import java.sql.*;
import java.util.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class BoilerEntity
{
    public static class BoilerAnnualTuneUpEntity
    {

        public static BoilerAnnualTuneUpVo findByPrimaryKey(int id)
            throws TrackingException
        {
            String GET_BLR = "select * from annualtuneup where annualtuneupid=?";
            BoilerAnnualTuneUpVo vo = null;
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(id));
                List stackList = util.execQueryUsingConstructor(GET_BLR, com.eespc.tracking.bo.BoilerAnnualTuneUpVo.class);
                if(stackList != null && stackList.size() > 0)
                    vo = (BoilerAnnualTuneUpVo)stackList.get(0);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("findByPrimaryKey(" + id + ")");
                ex.initCause(e);
                throw ex;
            }
            return vo;
        }

        public static List getList(int boilerId)
            throws TrackingException
        {
            List tuneupList = new ArrayList();
            StringBuffer queryBuff = new StringBuffer();
            queryBuff.append("select * from annualtuneup where boilerid =?");
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(boilerId));
                tuneupList = util.execQueryUsingConstructor(queryBuff.toString(), com.eespc.tracking.bo.BoilerAnnualTuneUpVo.class);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("getList(" + boilerId + ")");
                ex.initCause(e);
                throw ex;
            }
            return tuneupList;
        }

        public static int add(BoilerAnnualTuneUpVo blrTuneUpVo)
            throws TrackingException
        {
            int generatedKey = -99;
            StringBuffer buff = new StringBuffer();
            buff.append("insert into annualtuneup ");
            buff.append("(annualtuneupid, boilerid, year, ");
            buff.append("dateperformed, performedby, isrecordskept) ");
            buff.append("values (null,");
            buff.append(blrTuneUpVo.getBoilerId()).append(",");
            buff.append("'").append(blrTuneUpVo.getYear()).append("',");

            if(blrTuneUpVo.getDate() !=null)
            buff.append("'").append(UtilityObject.convertToString(blrTuneUpVo.getDate(), "yyyy-MM-dd")).append("',");
            else
        	buff.append("null,");

            buff.append("'").append(blrTuneUpVo.getPerformedBy()).append("',");
            if(blrTuneUpVo.isRecordsKept())
                buff.append("'").append("Y").append("'");
            else
                buff.append("'").append("N").append("'");
            buff.append(")");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                generatedKey = utilObj.insert(buff.toString());
            }
            catch(Exception e)
            {
               System.out.println("Error in Adding Boiler tuneup:"+e);
                TrackingException ex = new TrackingException("While Adding annualtuneup Info");
                ex.initCause(e);
                throw ex;
            }
            return generatedKey;
        }

        public static void update(BoilerAnnualTuneUpVo blrTuneUpVo)
            throws TrackingException
        {
            StringBuffer buff = new StringBuffer();
            buff.append("update annualtuneup ");
            buff.append("set year=?, dateperformed=?, ");
            buff.append("    performedby=?, isrecordskept=? ");
            buff.append(" where annualtuneupid=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                utilObj.addInParam(blrTuneUpVo.getYear());

        		if(blrTuneUpVo.getDate() !=null)
                utilObj.addInParam(UtilityObject.convertToString(blrTuneUpVo.getDate(), "yyyy-MM-dd"));
                else
                utilObj.addInParam(null);

                utilObj.addInParam(blrTuneUpVo.getPerformedBy());
                if(blrTuneUpVo.isRecordsKept())
                    utilObj.addInParam("Y");
                else
                    utilObj.addInParam("N");
                utilObj.addInParam(new Integer(blrTuneUpVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                System.out.println("Error in Updating Boiler tuneup:"+e);
                TrackingException ex = new TrackingException("While updating annualtuneup Info");
                ex.initCause(e);
                throw ex;
            }
        }




        public static void delete(BoilerAnnualTuneUpVo blrTuneUpVo)
            throws TrackingException
        {

            StringBuffer buff = new StringBuffer();
            buff.append("delete from annualtuneup");
//          buff.append("set year=?, date=?, ");
//          buff.append("    performedby=?, isrecordskept=? ");
            buff.append(" where annualtuneupid=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
//                utilObj.addInParam(blrTuneUpVo.getYear());
//                utilObj.addInParam(blrTuneUpVo.getDate());
//                utilObj.addInParam(blrTuneUpVo.getPerformedBy());
//                if(blrTuneUpVo.isRecordsKept())
//                    utilObj.addInParam("Y");
//                else
//                    utilObj.addInParam("N");
                utilObj.addInParam(new Integer(blrTuneUpVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While updating annualtuneup Info");
                ex.initCause(e);
                throw ex;
            }
        }

        public BoilerAnnualTuneUpEntity()
        {
        }
    }


//**********************************Pressure Valve Test Info*******************************************//

public static class BoilerValveTestEntity
    {

        public static BoilerValveTestVo findByPrimaryKey(int id)
            throws TrackingException
        {
            String GET_BLR = "select * from valvetest where valvetestid=?";
            BoilerValveTestVo vo = null;
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(id));
                List stackList = util.execQueryUsingConstructor(GET_BLR, com.eespc.tracking.bo.BoilerValveTestVo.class);
                if(stackList != null && stackList.size() > 0)
                    vo = (BoilerValveTestVo)stackList.get(0);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("findByPrimaryKey(" + id + ")");
                ex.initCause(e);
                throw ex;
            }
            return vo;
        }

        public static List getList(int boilerId)
            throws TrackingException
        {
            List valveTestList = new ArrayList();
            StringBuffer queryBuff = new StringBuffer();
            queryBuff.append("select * from valvetest where boilerid =?");
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(boilerId));
                valveTestList = util.execQueryUsingConstructor(queryBuff.toString(), com.eespc.tracking.bo.BoilerValveTestVo.class);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("getList(" + boilerId + ")");
                ex.initCause(e);
                throw ex;
            }
            return valveTestList;
        }

        public static int add(BoilerValveTestVo blrValveTestVo)
            throws TrackingException
        {
            int generatedKey = -99;
            StringBuffer buff = new StringBuffer();
            buff.append("insert into valvetest ");
            buff.append("(valvetestid, boilerid, year, ");
            buff.append("dateperformed, performedby) ");
            buff.append("values (null,");
            
            buff.append(blrValveTestVo.getBoilerId()).append(",");
            buff.append("'").append(blrValveTestVo.getYear()).append("',");
            if(blrValveTestVo.getDate() !=null)
            buff.append("'").append(UtilityObject.convertToString(blrValveTestVo.getDate(), "yyyy-MM-dd")).append("',");
            else
        	buff.append("null,");
            buff.append("'").append(blrValveTestVo.getPerformedBy()).append("'");            
            buff.append(")");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                generatedKey = utilObj.insert(buff.toString());
            }
            catch(Exception e)
            {
               System.out.println("Error in Adding Boiler valvetest:"+e);
                TrackingException ex = new TrackingException("While Adding valvetest Info");
                ex.initCause(e);
                throw ex;
            }
            return generatedKey;
        }

        public static void update(BoilerValveTestVo blrValveTestVo)
            throws TrackingException
        {
            StringBuffer buff = new StringBuffer();
            buff.append("update valvetest ");
            buff.append("set year=?, dateperformed=?, ");
            buff.append("    performedby=? ");
            buff.append(" where valvetestid=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                utilObj.addInParam(blrValveTestVo.getYear());
        		if(blrValveTestVo.getDate() !=null)
                utilObj.addInParam(UtilityObject.convertToString(blrValveTestVo.getDate(), "yyyy-MM-dd"));
                else
                utilObj.addInParam(null);
                utilObj.addInParam(blrValveTestVo.getPerformedBy());                
                utilObj.addInParam(new Integer(blrValveTestVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                System.out.println("Error in Updating Boiler valvetest:"+e);
                TrackingException ex = new TrackingException("While updating valvetest Info");
                ex.initCause(e);
                throw ex;
            }
        }




        public static void delete(BoilerValveTestVo blrValveTestVo)
            throws TrackingException
        {

            StringBuffer buff = new StringBuffer();
            buff.append("delete from valvetest");
//          buff.append("set year=?, date=?, ");
//          buff.append("    performedby=? ");
            buff.append(" where valvetestid=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
//              utilObj.addInParam(blrValveTestVo.getYear());
//              utilObj.addInParam(blrValveTestVo.getDate());
//              utilObj.addInParam(blrValveTestVo.getPerformedBy());//                
                utilObj.addInParam(new Integer(blrValveTestVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While updating valvetest Info");
                ex.initCause(e);
                throw ex;
            }
        }

        public BoilerValveTestEntity()
        {
        }
    }
    
    
    public static class BoilerFuelConsumptionEntity
    {

        public static BoilerFuelConsumptionVo findByPrimaryKey(int id)
            throws TrackingException
        {
            String GET_BLR = "select * from boilerfuelconsumption where BLRANNFUELCONSID=?";
            BoilerFuelConsumptionVo vo = null;
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(id));
                List stackList = util.execQueryUsingConstructor(GET_BLR, com.eespc.tracking.bo.BoilerFuelConsumptionVo.class);
                if(stackList != null && stackList.size() > 0)
                    vo = (BoilerFuelConsumptionVo)stackList.get(0);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("findByPrimaryKey(" + id + ")");
                ex.initCause(e);
                throw ex;
            }
            return vo;
        }

        public static List getList(int boilerId)
            throws TrackingException
        {
            List consumptionList = new ArrayList();
            StringBuffer queryBuff = new StringBuffer();
            queryBuff.append("select * from boilerfuelconsumption where boilerid =?");
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(boilerId));
                consumptionList = util.execQueryUsingConstructor(queryBuff.toString(), com.eespc.tracking.bo.BoilerFuelConsumptionVo.class);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("getList(" + boilerId + ")");
                ex.initCause(e);
                throw ex;
            }
            return consumptionList;
        }

        public static int add(BoilerFuelConsumptionVo blrConsumptionVo)
            throws TrackingException
        {
            int generatedKey = -99;
            StringBuffer buff = new StringBuffer();
            buff.append("insert into boilerfuelconsumption ");
            buff.append("(BLRANNFUELCONSID, BOILERID, YEAR, ");
            buff.append(" ANNUALCONSUMP, DAILYCONSUMP, MONTHLYCONSUMP) ");
            buff.append(" values (null,");
            buff.append(blrConsumptionVo.getBoilerId()).append(",");
            buff.append("'").append(blrConsumptionVo.getYear()).append("',");
            buff.append("'").append(blrConsumptionVo.getAnnualConsump()).append("',");
            buff.append("'").append(blrConsumptionVo.getDailyConsump()).append("',");
            buff.append("'").append(blrConsumptionVo.getMonthlyConsump()).append("'");
            buff.append(")");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                generatedKey = utilObj.insert(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While Adding Fuel Consumption Info");
                ex.initCause(e);
                throw ex;
            }
            return generatedKey;
        }

        public static void update(BoilerFuelConsumptionVo blrConsumptionVo)
            throws TrackingException
        {
            StringBuffer buff = new StringBuffer();
            buff.append("update boilerfuelconsumption ");
            buff.append("set year=?, ANNUALCONSUMP=?, ");
            buff.append("    DAILYCONSUMP=?, MONTHLYCONSUMP=? ");
            buff.append(" where BLRANNFUELCONSID=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                utilObj.addInParam(blrConsumptionVo.getYear());
                utilObj.addInParam(blrConsumptionVo.getAnnualConsump());
                utilObj.addInParam(blrConsumptionVo.getDailyConsump());
                utilObj.addInParam(blrConsumptionVo.getMonthlyConsump());
                utilObj.addInParam(new Integer(blrConsumptionVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While updating Fuel Consumption Info");
                ex.initCause(e);
                throw ex;
            }
        }

        public BoilerFuelConsumptionEntity()
        {
        }
    }

    public static class BoilerPermitEntity
    {

        public static BoilerPermitInfoVo findByPrimaryKey(int id)
            throws TrackingException
        {
            String GET_BLR = "select * from BOILERPERMITINFO where BOILERPERMITID=?";
            BoilerPermitInfoVo vo = null;
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(id));
                List stackList = util.execQueryUsingConstructor(GET_BLR, com.eespc.tracking.bo.BoilerPermitInfoVo.class);
                if(stackList != null && stackList.size() > 0)
                    vo = (BoilerPermitInfoVo)stackList.get(0);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("findByPrimaryKey(" + id + ")");
                ex.initCause(e);
                throw ex;
            }
            return vo;
        }

        public static List getList(int boilerId)
            throws TrackingException
        {
            List permitList = new ArrayList();
            StringBuffer queryBuff = new StringBuffer();
            queryBuff.append("select * from BOILERPERMITINFO where boilerid =?");
            try
            {
                SqlUtil util = new SqlUtil();
                util.addInParam(new Integer(boilerId));
                permitList = util.execQueryUsingConstructor(queryBuff.toString(), com.eespc.tracking.bo.BoilerPermitInfoVo.class);
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("getList(" + boilerId + ")");
                ex.initCause(e);
                throw ex;
            }
            return permitList;
        }

        public static int add(BoilerPermitInfoVo blrConsumptionVo)
            throws TrackingException
        {
            int generatedKey = -99;
            StringBuffer buff = new StringBuffer();
            buff.append("insert into BOILERPERMITINFO ");
            buff.append("(BOILERPERMITID,BOILERID,YEAR,LASTINSPECTIONDATE,");
            buff.append("PERMITNUMBER,ISSUEDDATE,INSPECTIONTYPE,EXPIRATIONDATE,");
            buff.append("DEPID,INSPSUBMITTEDDATE,DEPEXPIRATIONNOTE,DOBEXPIRATIONNOTE,DEPCOMPLIANCECOMMENTS)");
            buff.append(" values (null,");
            
            buff.append(blrConsumptionVo.getBoilerId()).append(",");
            
          	buff.append("'").append(blrConsumptionVo.getYear()).append("',");
          	
            if(blrConsumptionVo.getLastinspectionDate() !=null)
            buff.append("'").append(UtilityObject.convertToString(blrConsumptionVo.getLastinspectionDate(), "yyyy-MM-dd")).append("',");
            else
        	buff.append("null,");
        	
            buff.append("'").append(blrConsumptionVo.getPermitNumber()).append("',");

            if(blrConsumptionVo.getIssueDate() !=null)
            buff.append("'").append(UtilityObject.convertToString(blrConsumptionVo.getIssueDate(), "yyyy-MM-dd")).append("',");
            else
        	buff.append("null,");
        	
        	//FOR INSPECTIONTYPE
        		
        		if(String.valueOf(blrConsumptionVo.isInspectionType()).equalsIgnoreCase("true"))
        	buff.append("'").append("TRUE").append("',");
        	else if (String.valueOf(blrConsumptionVo.isInspectionType()).equalsIgnoreCase("false"))
        	buff.append("'").append("FALSE").append("',");	
        	else
        	buff.append("null,");
        	
        	

        	if(blrConsumptionVo.getExpirationDate() != null)
            buff.append("'").append(UtilityObject.convertToString(blrConsumptionVo.getExpirationDate(), "yyyy-MM-dd")).append("',");
            else
        	buff.append("null,");

            buff.append("'").append(blrConsumptionVo.getDepId()).append("',");

            if(blrConsumptionVo.getInspecSubmittedDate() != null)
            buff.append("'").append(UtilityObject.convertToString(blrConsumptionVo.getInspecSubmittedDate(), "yyyy-MM-dd")).append("',");
            else
        	buff.append("null,");
           	
            buff.append("'").append(UtilityObject.checkNullAndFill(blrConsumptionVo.getDepExpirationNote(),"")).append("',");
            buff.append("'").append(UtilityObject.checkNullAndFill(blrConsumptionVo.getDobExpirationNote(),"")).append("',");
            buff.append("'").append(UtilityObject.checkNullAndFill(blrConsumptionVo.getDepCompliancecomments(),"")).append("'");
            buff.append(")");
            SqlUtil utilObj = new SqlUtil();
            try
            {
                generatedKey = utilObj.insert(buff.toString());
            }
            catch(Exception e)
            {
                System.out.println("Exception In Add Boiler Dep Permit:"+e);
                TrackingException ex = new TrackingException("While Adding Permit Info");
                ex.initCause(e);
                throw ex;
            }
            return generatedKey;
        }

        public static void update(BoilerPermitInfoVo blrConsumptionVo)
            throws TrackingException
        {
            StringBuffer buff = new StringBuffer();
            buff.append("update BOILERPERMITINFO ");
            buff.append("set ");
            buff.append("YEAR=?,");
            buff.append("LASTINSPECTIONDATE=?, ");
            buff.append("PERMITNUMBER=?,");            
            buff.append("ISSUEDDATE=?,");
            buff.append("INSPECTIONTYPE=?,");
            buff.append("EXPIRATIONDATE=?,");
            buff.append("DEPID=?,");
            buff.append("INSPSUBMITTEDDATE=?,");        
            buff.append("DEPEXPIRATIONNOTE=?,");
            buff.append("DOBEXPIRATIONNOTE=?,");
            buff.append("DEPCOMPLIANCECOMMENTS=?");
            buff.append(" where BOILERPERMITID=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
         		
         		
         		utilObj.addInParam(blrConsumptionVo.getYear());
                if(blrConsumptionVo.getLastinspectionDate() !=null)
                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getLastinspectionDate(), "yyyy-MM-dd"));
                else
                {
                utilObj.addInParam(null);
                }
                utilObj.addInParam(blrConsumptionVo.getPermitNumber());

                if(blrConsumptionVo.getIssueDate() !=null)
                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getIssueDate(), "yyyy-MM-dd"));
                else
                {
                utilObj.addInParam(null);
                }
                //FOR INSPECTIONTYPE
        		if(String.valueOf(blrConsumptionVo.isInspectionType()).equalsIgnoreCase("true"))
        	utilObj.addInParam("TRUE");
        	else if (String.valueOf(blrConsumptionVo.isInspectionType()).equalsIgnoreCase("false"))
        	utilObj.addInParam("FALSE");
        	else
        	utilObj.addInParam("null");
        	
                
                if(blrConsumptionVo.getExpirationDate() !=null)
                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getExpirationDate(), "yyyy-MM-dd"));
                else
                {
                utilObj.addInParam(null);
                }
                utilObj.addInParam(new Integer(blrConsumptionVo.getDepId()));
                if(blrConsumptionVo.getInspecSubmittedDate() !=null)
                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getInspecSubmittedDate(), "yyyy-MM-dd"));
                else
                {
                utilObj.addInParam(null);
                }
                utilObj.addInParam(UtilityObject.checkNullAndFill(blrConsumptionVo.getDepExpirationNote(),""));
                utilObj.addInParam(UtilityObject.checkNullAndFill(blrConsumptionVo.getDobExpirationNote(),""));
                utilObj.addInParam(UtilityObject.checkNullAndFill(blrConsumptionVo.getDepCompliancecomments(),""));
                utilObj.addInParam(new Integer(blrConsumptionVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While updating Permit Info");
                ex.initCause(e);
                throw ex;
            }
        }


        public static void delete(BoilerPermitInfoVo blrConsumptionVo)
            throws TrackingException
        {
            StringBuffer buff = new StringBuffer();
            buff.append("delete from BOILERPERMITINFO");
//            buff.append("set ");
//            buff.append("YEAR=?, ");
//            buff.append("PERMITNUMBER=?,");
//            buff.append("ISSUEDDATE=?,");
//            buff.append("EXPIRATIONDATE=?,");
//            buff.append("DEPID=?,");
//            buff.append("INSPSUBMITTEDDATE=?");
            buff.append(" where BOILERPERMITID=?");
            SqlUtil utilObj = new SqlUtil();
            try
            {
//                utilObj.addInParam(blrConsumptionVo.getYear());
//                utilObj.addInParam(blrConsumptionVo.getPermitNumber());
//                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getIssueDate(), "yyyy-MM-dd"));
//                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getExpirationDate(), "yyyy-MM-dd"));
//                utilObj.addInParam(new Integer(blrConsumptionVo.getDepId()));
//                utilObj.addInParam(UtilityObject.convertToString(blrConsumptionVo.getInspecSubmittedDate(), "yyyy-MM-dd"));
                utilObj.addInParam(new Integer(blrConsumptionVo.getId()));
                utilObj.execForDmlUsingQuery(buff.toString());
            }
            catch(Exception e)
            {
                TrackingException ex = new TrackingException("While updating Permit Info");
                ex.initCause(e);
                throw ex;
            }
        }


        public BoilerPermitEntity()
        {
        }
    }


    public BoilerEntity()
    {
    }

    public static BoilerVo findByPrimaryKey(int id)
        throws TrackingException
    {
        String GET_BLR = "select * from building bld, boiler blr where bld.buildingid = blr.buildingid and blr.boilerid =?";
        BoilerVo vo = null;
        try
        {
            SqlUtil util = new SqlUtil();
            util.addInParam(new Integer(id));
            List stackList = util.execQueryUsingConstructor(GET_BLR, com.eespc.tracking.bo.BoilerVo.class);
            if(stackList != null && stackList.size() > 0)
                vo = (BoilerVo)stackList.get(0);
        }
        catch(Exception e)
        {
            TrackingException ex = new TrackingException("findByPrimaryKey(" + id + ")");
            ex.initCause(e);
            throw ex;
        }
        return vo;
    }

    public static int add(BoilerVo blrVo)
        throws TrackingException
    {
    	String No=null;
        if(log.isDebugEnabled())
            log.debug("To insert," + blrVo);
        int generatedKey = -99;
        StringBuffer buff = new StringBuffer();
        buff.append("insert into boiler (");
        buff.append("BOILERID,BUILDINGID,FACILITYDESIGNATEDID,FLOOR,STATEID,PRIMARYUSE,YEARINSTALLED,MANUFACTURER,");
        buff.append("MAKE,MODEL,SERIAL,BURNERMAKE,BURNERMODEL,CAPACITY,BURNERTYPE,PRIMARYFUEL,SECONDARYFUEL,");
        buff.append("FUELFROM,NYCDOB,MEA,DEP,SECHDUELC,PLANAPPROVAL,STACKFROM,STACKTESTREQUIRED,OTHERBOILERSCOMBINED,");
        buff.append("PARAMETERS,TESTONFUEL,STACKTESTPROTSUBMITED,TESTCONDUCTEDBY,TESTRPTSUBMITED,PROTOCOLSUBMITDATE,");
        buff.append("BOILERTESTPASSED,RETESTPLANNED,STACKTESTDATE,NEXTSTACKTESTDATE,ISBLRCOMPLIANCE,ISBLRSUBJECTTONSPC,BLRMODIFIEDINPAST,");
        buff.append("BLRMODIFIEDON,ISMODIFYPERMITSUB,ANYEMISSION,BLRSUBJECTTOFEDERAL,FUELCAPING,ISROLLINGAVG,");
        buff.append("ISOPACITYMONINST,PRFMTESTPROTSUB,PRFMTESTPROTSUBDATE,PRFMTESTRPTSUB,PRFMTESTRPTSUBDATE,ISSULPCONANLY,");
        buff.append("SULPCONTENT,FUELLIMITSFORBLR,FUELLIMITQNTY,NITGNCONTNT,NITGNCONTENT,BCOMMENTS,MODIFIEDINPAST,DISCONNECTEDYEAR,BFOOTNOTE,OILEMMISSIONFACTOR,GASEMMISSIONFACTOR,GASFUELGAPPING,OILFUELGAPPING,PRIMARYUSEOTHER,B_OILSO2,B_GASSO2,B_SO2ALLOWABLE,B_NOXALLOWABLE,DOBSIGNOFF,FIREDEPARTMENTAPPROVAL,DEPPERMITSTATUS,DEPPERMITSTATUSEXPIRE,DOBFILING,BOILERTUNEUP)");
        buff.append(" values (null,");
        buff.append(blrVo.getBuildingId()).append(",");
        buff.append("'").append(blrVo.getFacilityDesignatedId()).append("',");
        buff.append("'").append(blrVo.getFloor()).append("',");
        buff.append("'").append(blrVo.getStateId()).append("',");
        buff.append(blrVo.getPrimaryUse()).append(",");
        buff.append("'").append(blrVo.getYearInstalled()).append("',");
        buff.append("'").append(blrVo.getManufacturer()).append("',");
        buff.append("'").append(blrVo.getMake()).append("',");
        buff.append("'").append(blrVo.getModel()).append("',");
        buff.append("'").append(blrVo.getSerial()).append("',");
        buff.append("'").append(blrVo.getBurnerMake()).append("',");
        buff.append("'").append(blrVo.getBurnerModel()).append("',");
        buff.append("'").append(blrVo.getCapacity()).append("',");
        buff.append(blrVo.getBurnerType()).append(",");
        buff.append(blrVo.getPrimaryFuel()).append(",");
        buff.append(blrVo.getSecondaryFuel()).append(",");
        buff.append(blrVo.getFuelFrom()).append(",");
        buff.append("'").append(blrVo.getNycDob()).append("',");
        buff.append("'").append(blrVo.getMea()).append("',");
        buff.append("'").append(blrVo.getDep()).append("',");
        if(blrVo.isSechduelC())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isPlanApproval())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        buff.append(blrVo.getStackFrom()).append(",");

       /* if(blrVo.isStackTestRequired())
            buff.append("'").append("Y").append("',");
        else
           buff.append("'").append("N").append("',");*/
           
        buff.append("'").append(blrVo.getStackTestRequired()).append("',");

        if(blrVo.isOtherBoilersCombined())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");


        buff.append(blrVo.getParameters()).append(",");
        buff.append(blrVo.getTestOnFuel()).append(",");


        if(blrVo.isStackTestProtSubmited())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        buff.append("'").append(blrVo.getTestConductedBy()).append("',");
       // buff.append("'").append(blrVo.getTestReportSubmited()).append("',");
        
       /* if(blrVo.isTestReportSubmited())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");*/
		if(isNotEmpty(blrVo.getTestReportSubmited()))          
            
        buff.append("'").append(blrVo.getTestReportSubmited()).append("',");
        else
        buff.append("null,");
        


        if(isNotEmpty(blrVo.getProtocolSubmitDate()))
        buff.append("'").append(blrVo.getProtocolSubmitDate()).append("',");
        else
        buff.append("null,");

        if(blrVo.isBoilerTestPassed())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isRetestPlanned())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");

        if(isNotEmpty(blrVo.getStackTestDate()))
        buff.append("'").append(blrVo.getStackTestDate()).append("',");
        else
        buff.append("null,");

        if(isNotEmpty(blrVo.getNextStackTestDate()))
        buff.append("'").append(blrVo.getNextStackTestDate()).append("',");
        else
        buff.append("null,");

        if(blrVo.isBoilerInCompliance())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isBoilerSubjectToNspc())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isBlrModifiedInPast())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");

        String dis1="";
        if(isNotEmpty(blrVo.getBlrModifiedOn()) && !"null".equalsIgnoreCase(blrVo.getBlrModifiedOn().trim()))
        {
           dis1="'"+blrVo.getBlrModifiedOn()+"',";
        }
        else
        dis1=null+",";

         buff.append(dis1);



        if(blrVo.isModifyPermitSub())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isAnyEmission())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isBlrSubjectToFederal())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        buff.append("'").append(blrVo.getFuelCaping()).append("',");
        if(blrVo.isRollingAvg())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isOpacityMonInst())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        if(blrVo.isPrfmTestProtSub())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");


       if(isNotEmpty(blrVo.getPrfmTestProtSubDate()))

        buff.append("'").append(blrVo.getPrfmTestProtSubDate()).append("',");
        else
        buff.append("null,");





        if(blrVo.isPrfmTestRptSub())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");

          if(isNotEmpty(blrVo.getPrfmTestRptSubDate()))
          {
        	buff.append("'").append(blrVo.getPrfmTestRptSubDate()).append("',");
    		}
    		else
    		{
    		 buff.append("null,");
    		}


        if(blrVo.isSulpContentAnaly())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        buff.append("'").append(blrVo.getSulphurContent()).append("',");
        if(blrVo.isFuelLimitsForBlr())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        buff.append("'").append(blrVo.getFuelLimitQnty()).append("',");
        if(blrVo.isNitrogenContent())
            buff.append("'").append("Y").append("',");
        else
            buff.append("'").append("N").append("',");
        buff.append("'").append(blrVo.getNitrogenContentQnty()).append("',");
        buff.append("'").append(blrVo.getComments()).append("',");



          buff.append(blrVo.getModifiedInPast()).append(",");

        buff.append("'").append(blrVo.getDisconnectedYear()).append("',");
        buff.append("'").append(blrVo.getFootnote()).append("',");
        buff.append("'").append(blrVo.getOilemmisionfactor()).append("',");
        buff.append("'").append(blrVo.getGasemmisionfactor()).append("',");
        buff.append("'").append(blrVo.getGasfuelgapping()).append("',");
        buff.append("'").append(blrVo.getOilfuelgapping()).append("',");
        buff.append("'").append(blrVo.getPrimaryuseother()).append("',");
        buff.append("'").append(blrVo.getB_oilso2()).append("',");
        buff.append("'").append(blrVo.getB_gasso2()).append("',");

		buff.append("'").append(blrVo.getB_so2allowable()).append("',");
		buff.append("'").append(blrVo.getB_noxallowable()).append("',");

		buff.append("'").append(blrVo.getDobsignoff()).append("',");
		buff.append("'").append(blrVo.getFiredepartmentapproval()).append("',");
		buff.append("'"+blrVo.isDepPermit()+"',");
		buff.append("'"+blrVo.isDepPermitExpire()+"',");
		buff.append("'"+blrVo.getDobFiling()+"',");
		buff.append("'").append(blrVo.getBoilerTuneup()).append("'");
        buff.append(")");

        SqlUtil utilObj = new SqlUtil();
        try
        {
            generatedKey = utilObj.insert(buff.toString());
        }
        catch(Exception e)
        {
            TrackingException ex = new TrackingException("While Adding Boiler");
            ex.initCause(e);
            throw ex;
        }
        return generatedKey;
    }



    public static void delete(BoilerVo bo)
        throws TrackingException
    {

        //if(log.isDebugEnabled())
           // log.debug("To update," + boilerVo);

        String s[]={"delete from boilerpermitinfo where BOILERID=?","delete from annualtuneup where BOILERID=?","delete from boilerfuelconsumption where BOILERID=?","delete from boiler where BOILERID=?"};
        for(int i=0;i<=3;i++)
        {
        StringBuffer updt = new StringBuffer();
        updt.append(s[i]);

        SqlUtil utilObj = new SqlUtil();
      //  utilObj.addInParam(boilerVo.getDep());

        utilObj.addInParam(new Integer(bo.getId()));
        try
        {
            utilObj.execForDmlUsingQuery(updt.toString());
        }
        catch(Exception e)
        {
            TrackingException ex = new TrackingException("While Updating Boiler");
            ex.initCause(e);
            throw ex;
        }
    	}
    }

    public static void  update(BoilerVo boilerVo)
        throws TrackingException
    {
    	String No=null;
        if(log.isDebugEnabled())
            log.debug("To update," + boilerVo);
        StringBuffer updt = new StringBuffer();
        updt.append("update boiler set ");
        updt.append("FACILITYDESIGNATEDID=?,");
        updt.append("FLOOR=?,");
        updt.append("STATEID=?,");
        updt.append("PRIMARYUSE=?,");
        updt.append("YEARINSTALLED=?,");
        updt.append("MANUFACTURER=?,");
        updt.append("MAKE=?,");
        updt.append("MODEL=?,");
        updt.append("SERIAL=?,");
        updt.append("BURNERMAKE=?,");
        updt.append("BURNERMODEL=?,");
        updt.append("CAPACITY=?,");
        updt.append("BURNERTYPE=?,");
        updt.append("PRIMARYFUEL=?,");
        updt.append("SECONDARYFUEL=?,");
        updt.append("FUELFROM=?,");
        updt.append("NYCDOB=?,");
        updt.append("MEA=?,");
        updt.append("DEP=?,");
        updt.append("SECHDUELC=?,");
        updt.append("PLANAPPROVAL=?,");
        updt.append("STACKFROM=?,");
        updt.append("STACKTESTREQUIRED=?,");
        updt.append("OTHERBOILERSCOMBINED=?,");
        updt.append("PARAMETERS=?,");
        /////////////
        updt.append("TESTONFUEL=?,");
        updt.append("STACKTESTPROTSUBMITED=?,");
        updt.append("TESTCONDUCTEDBY=?,");
        updt.append("TESTRPTSUBMITED=?,");
        updt.append("PROTOCOLSUBMITDATE=?,");
        updt.append("BOILERTESTPASSED=?,");
        updt.append("RETESTPLANNED=?,");
        updt.append("STACKTESTDATE=?,");
        updt.append("NEXTSTACKTESTDATE=?,");
        updt.append("ISBLRCOMPLIANCE=?,");

        ////////////////////
        updt.append("ISBLRSUBJECTTONSPC=?,");
        updt.append("BLRMODIFIEDINPAST=?,");
        updt.append("BLRMODIFIEDON=?,");
        updt.append("ISMODIFYPERMITSUB=?,");
        updt.append("ANYEMISSION=?,");
        updt.append("BLRSUBJECTTOFEDERAL=?,");
        updt.append("FUELCAPING=?,");
        updt.append("ISROLLINGAVG=?,");
        updt.append("ISOPACITYMONINST=?,");
        updt.append("PRFMTESTPROTSUB=?,");
        updt.append("PRFMTESTPROTSUBDATE=?,");
        updt.append("PRFMTESTRPTSUB=?,");
        updt.append("PRFMTESTRPTSUBDATE=?,");
        updt.append("ISSULPCONANLY=?,");
        updt.append("SULPCONTENT=?,");
        updt.append("FUELLIMITSFORBLR=?,");
        updt.append("FUELLIMITQNTY=?,");
        updt.append("NITGNCONTNT=?,");
        updt.append("BCOMMENTS=?,");
        updt.append("MODIFIEDINPAST=?, ");
        updt.append("DISCONNECTEDYEAR=?, ");
        updt.append("NITGNCONTENT=?, ");
        updt.append("BFOOTNOTE=?, ");
        updt.append("OILEMMISSIONFACTOR=?, ");
        updt.append("GASEMMISSIONFACTOR=?, ");
        updt.append("GASFUELGAPPING=?, ");
        updt.append("OILFUELGAPPING=?, ");
        updt.append("PRIMARYUSEOTHER=?, ");
        updt.append("B_OILSO2=?, ");
        updt.append("B_GASSO2=?, ");
        updt.append("B_SO2ALLOWABLE=?,");
		updt.append("B_NOXALLOWABLE=?, ");
		updt.append("DOBSIGNOFF=?,");
		updt.append("FIREDEPARTMENTAPPROVAL=?,");
		updt.append("DEPPERMITSTATUS=?,");
		updt.append("DEPPERMITSTATUSEXPIRE=?,");
		updt.append("DOBFILING=?,");
		updt.append("BOILERTUNEUP=?");
        updt.append("where BOILERID=?");
        SqlUtil utilObj = new SqlUtil();
        utilObj.addInParam(boilerVo.getFacilityDesignatedId());
        utilObj.addInParam(boilerVo.getFloor());
        utilObj.addInParam(boilerVo.getStateId());
        utilObj.addInParam(new Integer(boilerVo.getPrimaryUse()));
        utilObj.addInParam(boilerVo.getYearInstalled());
        utilObj.addInParam(boilerVo.getManufacturer());
        utilObj.addInParam(boilerVo.getMake());
        utilObj.addInParam(boilerVo.getModel());
        utilObj.addInParam(boilerVo.getSerial());
        utilObj.addInParam(boilerVo.getBurnerMake());
        utilObj.addInParam(boilerVo.getBurnerModel());
        utilObj.addInParam(boilerVo.getCapacity());
        utilObj.addInParam(new Integer(boilerVo.getBurnerType()));
        utilObj.addInParam(new Integer(boilerVo.getPrimaryFuel()));
        utilObj.addInParam(new Integer(boilerVo.getSecondaryFuel()));
        utilObj.addInParam(new Integer(boilerVo.getFuelFrom()));
        utilObj.addInParam(boilerVo.getNycDob());
        utilObj.addInParam(boilerVo.getMea());
        utilObj.addInParam(boilerVo.getDep());
        if(boilerVo.isSechduelC())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isPlanApproval())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(new Integer(boilerVo.getStackFrom()));
        utilObj.addInParam(boilerVo.getStackTestRequired());
        
       /* if(boilerVo.isStackTestRequired())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");*/        
            
        if(boilerVo.isOtherBoilersCombined())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(new Integer(boilerVo.getParameters()));



        utilObj.addInParam(new Integer(boilerVo.getTestOnFuel()));
        if(boilerVo.isStackTestProtSubmited())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(boilerVo.getTestConductedBy());
     //   utilObj.addInParam(boilerVo.getTestReportSubmited());

       /* if(boilerVo.isTestReportSubmited())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");*/
            
            
            if(isNotEmpty(boilerVo.getTestReportSubmited()))
            {
        	utilObj.addInParam(boilerVo.getTestReportSubmited());
        	}
    		else
    		{
    		 utilObj.addInParam(null);
    		}
    		
    		
    		

         if(isNotEmpty(boilerVo.getProtocolSubmitDate()))
          {
        utilObj.addInParam(boilerVo.getProtocolSubmitDate());
        	}
    		else
    		{
    		 utilObj.addInParam(null);
    		}

        if(boilerVo.isBoilerTestPassed())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isRetestPlanned())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");



//            if(isNotEmpty(boilerVo.getPrfmTestRptSubDate()))
//           {
//           utilObj.addInParam(boilerVo.getPrfmTestRptSubDate());
//           }
//    		else
//    		{
//    		 utilObj.addInParam(null);
//    		}


            if(isNotEmpty(boilerVo.getStackTestDate()))
            {
              utilObj.addInParam(boilerVo.getStackTestDate());
             }
    		else
    		{
    		 utilObj.addInParam(null);
    		}


            if(isNotEmpty(boilerVo.getNextStackTestDate()))
            {
              utilObj.addInParam(boilerVo.getNextStackTestDate());
             }
    		else
    		{
    		 utilObj.addInParam(null);
    		}

        if(boilerVo.isBoilerInCompliance())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isBoilerSubjectToNspc())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isBlrModifiedInPast())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");


         if(isNotEmpty(boilerVo.getBlrModifiedOn()))
            {
        utilObj.addInParam(boilerVo.getBlrModifiedOn());
                     }
    		else
    		{
    		 utilObj.addInParam(null);
    		}


        if(boilerVo.isModifyPermitSub())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isAnyEmission())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isBlrSubjectToFederal())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(boilerVo.getFuelCaping());
        if(boilerVo.isRollingAvg())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isOpacityMonInst())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        if(boilerVo.isPrfmTestProtSub())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");


        if(UtilityObject.isNotEmpty(boilerVo.getPrfmTestProtSubDate()))
        utilObj.addInParam(boilerVo.getPrfmTestProtSubDate());
        else
        utilObj.addInParam(null);

        if(boilerVo.isPrfmTestRptSub())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");

        if(UtilityObject.isNotEmpty(boilerVo.getPrfmTestRptSubDate()))
        utilObj.addInParam(boilerVo.getPrfmTestRptSubDate());
        else
        utilObj.addInParam(null);

        if(boilerVo.isSulpContentAnaly())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(boilerVo.getSulphurContent());
        if(boilerVo.isFuelLimitsForBlr())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(boilerVo.getFuelLimitQnty());
        if(boilerVo.isNitrogenContent())
            utilObj.addInParam("Y");
        else
            utilObj.addInParam("N");
        utilObj.addInParam(boilerVo.getComments());

        utilObj.addInParam(String.valueOf(boilerVo.getModifiedInPast()));
        utilObj.addInParam(boilerVo.getDisconnectedYear());
        utilObj.addInParam(boilerVo.getNitrogenContentQnty());
        utilObj.addInParam(boilerVo.getFootnote());
        utilObj.addInParam(boilerVo.getOilemmisionfactor());
        utilObj.addInParam(boilerVo.getGasemmisionfactor());
        utilObj.addInParam(boilerVo.getGasfuelgapping());
        utilObj.addInParam(boilerVo.getOilfuelgapping());
        utilObj.addInParam(boilerVo.getPrimaryuseother());
        utilObj.addInParam(boilerVo.getB_oilso2());
        utilObj.addInParam(boilerVo.getB_gasso2());
        utilObj.addInParam(boilerVo.getB_so2allowable());
		utilObj.addInParam(boilerVo.getB_noxallowable());
        utilObj.addInParam(boilerVo.getDobsignoff());
		utilObj.addInParam(boilerVo.getFiredepartmentapproval());
		utilObj.addInParam(boilerVo.isDepPermit());
		utilObj.addInParam(boilerVo.isDepPermitExpire());
		utilObj.addInParam(boilerVo.getDobFiling());
		utilObj.addInParam(boilerVo.getBoilerTuneup());
        utilObj.addInParam(new Integer(boilerVo.getId()));
        try
        {
            utilObj.execForDmlUsingQuery(updt.toString());
        }
        catch(Exception e)
        {
            System.out.println("Exhibit Error:"+e);
        }
    }

    public static BoilerAnnualTuneUpVo findAnnualTuneUp(int id)
        throws TrackingException
    {
        return BoilerAnnualTuneUpEntity.findByPrimaryKey(id);
    }

    public static List getAnnaulTuneUpList(int boilerId)
        throws TrackingException
    {
        return BoilerAnnualTuneUpEntity.getList(boilerId);
    }

    public static int addAnnualTuneup(BoilerAnnualTuneUpVo obj)
        throws TrackingException
    {
        return BoilerAnnualTuneUpEntity.add(obj);
    }

    public static void updateAnnualTuneup(BoilerAnnualTuneUpVo obj)
        throws TrackingException
    {
        BoilerAnnualTuneUpEntity.update(obj);
    }

     public static void deleteAnnualTuneup(BoilerAnnualTuneUpVo obj)
        throws TrackingException
    {
        BoilerAnnualTuneUpEntity.delete(obj);
    }
    
    
    
    public static BoilerValveTestVo findValveTest(int id)
        throws TrackingException
    {
        return BoilerValveTestEntity.findByPrimaryKey(id);
    }

    public static List getValveTestList(int boilerId)
        throws TrackingException
    {
        return BoilerValveTestEntity.getList(boilerId);
    }

    public static int addValveTest(BoilerValveTestVo obj)
        throws TrackingException
    {
        return BoilerValveTestEntity.add(obj);
    }

    public static void updateValveTest(BoilerValveTestVo obj)
        throws TrackingException
    {
        BoilerValveTestEntity.update(obj);
    }

     public static void deleteValveTest(BoilerValveTestVo obj)
        throws TrackingException
    {
        BoilerValveTestEntity.delete(obj);
    }
    

    public static BoilerFuelConsumptionVo findFuelConsumption(int id)
        throws TrackingException
    {
        return BoilerFuelConsumptionEntity.findByPrimaryKey(id);
    }

    public static List getFuelConsumptionList(int boilerId)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	FuelConsumptionEntity.getFuelConsumption(boilerId, ResourcesTypeEnum.BOILER.getCode());
        }
        catch(Exception e)
        {
        System.out.println("Error:"+e);
        }

        return FuelConsumptionEntity.getFuelConsumption(boilerId, ResourcesTypeEnum.BOILER.getCode());
    }
    public static List getFuelConsumptionyearList(int boilerId,String year)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	FuelConsumptionEntity.getFuelConsumptionyear(boilerId, ResourcesTypeEnum.BOILER.getCode(),year);
        }
        catch(Exception e)
        {
        	System.out.println("Error:"+e);
        }

        return FuelConsumptionEntity.getFuelConsumptionyear(boilerId, ResourcesTypeEnum.BOILER.getCode(),year);
    }

    public static List geto_FuelConsumptionyearList(int boilerId,String year)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	FuelConsumptionEntity.geto_FuelConsumptionyear(boilerId, ResourcesTypeEnum.BOILER.getCode(),year);
        }
        catch(Exception e)
        {
        	System.out.println("Error:"+e);
        }

        return FuelConsumptionEntity.geto_FuelConsumptionyear(boilerId, ResourcesTypeEnum.BOILER.getCode(),year);
    }

    public static List geto_FuelConsumptionList(int boilerId)
        throws TrackingException
    {

        try
        {
        List xx=null;
        xx=	FuelConsumptionEntity.geto_FuelConsumption(boilerId, ResourcesTypeEnum.BOILER.getCode());
        //System.out.println("BEO"+xx.size());
        }
        catch(Exception e)
        {
        }

        return FuelConsumptionEntity.geto_FuelConsumption(boilerId, ResourcesTypeEnum.BOILER.getCode());
    }

    public static int addFuelConsumption(FuelConsumptionVo vo)
        throws TrackingException
    {
        return FuelConsumptionEntity.add(vo, ResourcesTypeEnum.BOILER.getCode());
    }

    public static void updateFuelConsumption(FuelConsumptionVo vo)
        throws TrackingException
    {
        FuelConsumptionEntity.update(vo, ResourcesTypeEnum.BOILER.getCode());
    }

    public static BoilerPermitInfoVo findPermit(int id)
        throws TrackingException
    {
        return BoilerPermitEntity.findByPrimaryKey(id);
    }

    public static List getPermitList(int boilerId)
        throws TrackingException
    {
        return BoilerPermitEntity.getList(boilerId);
    }

    public static int addPermit(BoilerPermitInfoVo obj)
        throws TrackingException
    {
        return BoilerPermitEntity.add(obj);
    }    
	
    public static void updatePermit(BoilerPermitInfoVo obj)
        throws TrackingException
    {
        BoilerPermitEntity.update(obj);
    }    
        
    public static void deletePermit(BoilerPermitInfoVo obj)
        throws TrackingException
    {
        BoilerPermitEntity.delete(obj);
    }


    public static void deletefuelconsumption(int id)
            throws TrackingException
        {
        StringBuffer buff = new StringBuffer();
            buff.append("delete from boilerfuelconsumption");
            buff.append(" where BLRANNFUELCONSID=?");
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
   /* public static List getResultForReport(int i)throws NullPointerException
    {
        ArrayList arraylist;
        LinkedHashMap linkedhashmap= new LinkedHashMap();;
        StringBuffer stringbuffer;
        SqlUtil sqlutil;
        Connection connection=null;
        PreparedStatement preparedstatement=null;
        ResultSet resultset=null;
        long l = System.currentTimeMillis();
        arraylist = new ArrayList();
        stringbuffer = new StringBuffer();
        stringbuffer.append("select bpf.DEPCOMPLIANCECOMMENTS,blr.boilerid, blr.manufacturer, blr.yearinstalled, blr.make, ");
        stringbuffer.append("      blr.model, blr.serial, blr.burnermake,blr.burnermodel, bld.buildingname, ");
        stringbuffer.append("      blr.capacity, blr.burnertype, blr.primaryfuel, blr.secondaryfuel, blr.dep, ");
        stringbuffer.append("      bpf.expirationdate, bpf.boilerpermitid ");
        stringbuffer.append("from  building bld, boiler blr left join boilerpermitinfo bpf on blr.boilerid=bpf.boilerid ");
        stringbuffer.append("where (blr.MODIFIEDINPAST='1' or blr.MODIFIEDINPAST='5') and blr.buildingid=bld.buildingid and ");
        stringbuffer.append("      bld.facilityid=");
        stringbuffer.append(i);
        stringbuffer.append(" order by blr.boilerid asc, blr.buildingid asc, bpf.boilerpermitid asc");
        sqlutil = new SqlUtil();
        try
        {
        connection = null;
        preparedstatement = null;
        resultset = null;


        connection = sqlutil.getConnection();
        preparedstatement = connection.prepareStatement(stringbuffer.toString());
        resultset = preparedstatement.executeQuery();
        l = System.currentTimeMillis();
        Hashtable hashtable;
        String s;
        for(; resultset.next(); linkedhashmap.put(s, hashtable))
        {
            hashtable = new Hashtable();
            s = resultset.getString("boilerid");
            hashtable.put("boilerid", s);
            hashtable.put("comments", UtilityObject.checkNullAndFill(resultset.getString("DEPCOMPLIANCECOMMENTS"), "-"));
            hashtable.put("manufacturer", UtilityObject.checkNullAndFill(resultset.getString("manufacturer"), "-"));
            hashtable.put("yearinstalled", UtilityObject.checkNullAndFill(resultset.getString("yearinstalled"), "-"));
            hashtable.put("make", UtilityObject.checkNullAndFill(resultset.getString("make"), "-"));
            hashtable.put("model", UtilityObject.checkNullAndFill(resultset.getString("model"), "-"));
            hashtable.put("serial", UtilityObject.checkNullAndFill(resultset.getString("serial"), "-"));
            hashtable.put("burnermake", UtilityObject.checkNullAndFill(resultset.getString("burnermake"), "-"));
            hashtable.put("burnermodel", UtilityObject.checkNullAndFill(resultset.getString("burnermodel"), "-"));
            hashtable.put("buildingname", UtilityObject.checkNullAndFill(resultset.getString("buildingname"), "-"));
            String s1 = UtilityObject.checkNullAndFill(resultset.getString("capacity"), "0");
            hashtable.put("capacity", s1);
            hashtable.put("dep", UtilityObject.checkNullAndFill(resultset.getString("dep"), "-"));
            String exp=resultset.getString("expirationdate");
            hashtable.put("expirationdate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(exp), "-"));
            String s2 = resultset.getString("boilerpermitid");
            hashtable.put("boilerpermitid", s2 != null ? ((Object) (s2)) : "");

            String i_1=checkNullAndFill(s2,"axs");
            int j_1=0;


            java.util.Date today=new java.util.Date();
            java.util.Date expi=UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
            j_1 = today.compareTo(expi);
            if(j_1 > 0 || expi==null)
            {
            	j_1=0;
            }
            else
            {
            j_1=1;
            }

            if(i_1.equals("axs") || j_1==0 )
            {
            hashtable.put("compliancestatus", "Non Compliance");
            }
            else
            {
            hashtable.put("compliancestatus", "Compliance");
            }


            int j = UtilityObject.getIntFromString(resultset.getString("burnertype"));
            int k = UtilityObject.getIntFromString(resultset.getString("primaryfuel"));
            int i1 = UtilityObject.getIntFromString(resultset.getString("secondaryfuel"));
            if(j != 2);
            if(k > 0)
            {
                FurnaceOilTypeEnum furnaceoiltypeenum = FurnaceOilTypeEnum.get(k);
                hashtable.put("primaryFuel", furnaceoiltypeenum != null ? ((Object) (furnaceoiltypeenum.getName())) : "N/A");
            }
            double d = Double.parseDouble(s1);
            double d1 = -99D;
            if(d <= 0.0D)
                continue;
            int j1 = k;
            if(j == 1)
            {
                if(j1 == 1)
                {
                    d1 = d * 1000D;
                    hashtable.put("naturalGasFiringRate", (new StringBuilder()).append(d1).append("").toString());
                } else
                {
                    int k1 = 0;
                    if(j1 == 2)
                        k1 = 140;
                    else
                    if(j1 == 3)
                        k1 = 145;
                    else
                    if(j1 == 4)
                        k1 = 150;
                    hashtable.put("oilFiringRate", (new StringBuilder()).append(round((d * 1000D) / (double)k1, 2)).append("").toString());
                    hashtable.put("naturalGasFiringRate", "N/A");
                }
                hashtable.put("secondaryFuel", "N/A");
            }
            if(j != 2)
                continue;
            d1 = d * 1000D;
            hashtable.put("naturalGasFiringRate", (new StringBuilder()).append(d1).append("").toString());
            int l1 = i1;
            if(l1 > 0)
            {
                FurnaceOilTypeEnum furnaceoiltypeenum1 = FurnaceOilTypeEnum.get(l1);
                if(furnaceoiltypeenum1 != null)
                {
                    hashtable.put("secondaryFuel", checkNullAndFill(furnaceoiltypeenum1.getName(), "N/A"));
                    byte byte0 = -99;
                    int i2 = furnaceoiltypeenum1.getCode();
                    int j2 = 0;
                    if(i2 == 2)
                        j2 = 140;
                    else
                    if(i2 == 3)
                        j2 = 145;
                    else
                    if(i2 == 4)
                        j2 = 150;
                    if(j2 > 0)
                        hashtable.put("oilFiringRate", (new StringBuilder()).append(round((d * 1000D) / (double)j2, 2)).append("").toString());
                    else
                        hashtable.put("oilFiringRate", "--");
                } else
                {
                    hashtable.put("secondaryFuel", "N/A");
                    hashtable.put("oilFiringRate", "--");
                }
            } else
            {
                hashtable.put("secondaryFuel", "N/A");
                hashtable.put("oilFiringRate", "--");
            }
        }

        }


        catch(Exception e)
        {
            System.out.println("Error Exhibit e:"+e);
            log.error("getResultForReport(" + i + ")", e);
        }
        finally
        {
            log.debug("Time taken for report query " + (System.currentTimeMillis() - l) + " ms.");
            if(connection != null)
            {
                SqlUtil.close(resultset);
                SqlUtil.close(preparedstatement);
                sqlutil.closeConnection();
            }
        }



        if(linkedhashmap != null)
        {

            Set set = linkedhashmap.keySet();
            for(Iterator iterator = set.iterator(); iterator.hasNext(); arraylist.add(linkedhashmap.get(iterator.next())));
        }
        return arraylist;
    }
*/

public static List getResultForReport(int facilityid)
    {
        List result;
        LinkedHashMap hash4Unique;
        result = new ArrayList();
        StringBuffer query = new StringBuffer();
        query.append("select blr.DEPPERMITSTATUS,blr.DEPPERMITSTATUSEXPIRE,blr.NYCDOB,blr.DOBSIGNOFF,blr.DOBFILING,blr.FIREDEPARTMENTAPPROVAL,blr.boilerid,blr.FACILITYDESIGNATEDID, blr.manufacturer, blr.yearinstalled, blr.make, ");
        query.append("      blr.model, blr.serial, blr.burnermake,blr.burnermodel, bld.buildingname, ");
        query.append("      blr.capacity, blr.burnertype, blr.primaryfuel, blr.secondaryfuel, blr.dep, ");
        query.append("      bpf.expirationdate, bpf.DEPCOMPLIANCECOMMENTS, bpf.boilerpermitid ");
        query.append("from  building bld, boiler blr left join boilerpermitinfo bpf on blr.boilerid=bpf.boilerid and bpf.depid='1' ");
        query.append("where (blr.MODIFIEDINPAST='1' or blr.MODIFIEDINPAST='5')  and blr.buildingid=bld.buildingid and ");
        query.append("      bld.facilityid=");
        query.append(facilityid);
        query.append(" order by blr.boilerid asc, blr.buildingid asc, bpf.boilerpermitid asc");
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
                tempBoilerId = rst.getString("boilerid");
                tempHash.put("boilerid", tempBoilerId);

                String comm=UtilityObject.checkNullAndFill(rst.getString("DEPCOMPLIANCECOMMENTS"), "-");
                tempHash.put("comments", comm);                
                tempHash.put("FIREDEPARTMENTAPPROVAL", UtilityObject.checkNullAndFill(rst.getString("FIREDEPARTMENTAPPROVAL"), "-"));
                tempHash.put("facilitydesinatedid", UtilityObject.checkNullAndFill(rst.getString("FACILITYDESIGNATEDID"), "-"));
                tempHash.put("manufacturer", UtilityObject.checkNullAndFill(rst.getString("manufacturer"), "-"));
                tempHash.put("yearinstalled", UtilityObject.checkNullAndFill(rst.getString("yearinstalled"), "-"));
                tempHash.put("make", UtilityObject.checkNullAndFill(rst.getString("make"), "-"));
                tempHash.put("model", UtilityObject.checkNullAndFill(rst.getString("model"), "-"));
                tempHash.put("serial", UtilityObject.checkNullAndFill(rst.getString("serial"), "-"));
                tempHash.put("burnermake", UtilityObject.checkNullAndFill(rst.getString("burnermake"), "-"));
                tempHash.put("burnermodel", UtilityObject.checkNullAndFill(rst.getString("burnermodel"), "-"));
                tempHash.put("buildingname", UtilityObject.checkNullAndFill(rst.getString("buildingname"), "-"));
                String tempCapacity = UtilityObject.checkNullAndFill(rst.getString("capacity"), "0");
                tempHash.put("capacity", tempCapacity);	
                
                
			  //********************Dob Filing Start*************************//	                
			
                String dobfiling = UtilityObject.checkNullAndFill(rst.getString("DOBFILING"), "-");
                String nycdob = rst.getString("NYCDOB");                
				String dobsignoff = rst.getString("DOBSIGNOFF");				
								
                if(dobfiling.equalsIgnoreCase("Y"))
                {
                tempHash.put("NYCDOB", UtilityObject.checkNullAndFill(nycdob, "No Filing"));
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
		            else if(!nycdob.equals("No Filing") && dobsignoff.equalsIgnoreCase("N"))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }		           
		            else if(!UtilityObject.isNotEmpty(nycdob) && dobsignoff.equalsIgnoreCase("Y"))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }  
		            else if(!UtilityObject.isNotEmpty(nycdob) && dobsignoff.equalsIgnoreCase("N"))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }
		           /* else if(!UtilityObject.isNotEmpty(nycdob) && !UtilityObject.isNotEmpty(dobsignoff))
		            {
			            tempHash.put("dobcompliancestatus", "Non Compliance");
			            dobcompliancestatus="Non Compliance";	
		            }*/		              	                          
		            else
					{
						tempHash.put("dobcompliancestatus", "Compliance");
						dobcompliancestatus="Compliance";
					}

										
					                
               //********************Dob Filing End*************************//	
             

				//********************DEP Start*************************//	                
                String depPermitstatus = UtilityObject.checkNullAndFill(rst.getString("DEPPERMITSTATUS"), "-");
				String depPermitStatusExpire = UtilityObject.checkNullAndFill(rst.getString("DEPPERMITSTATUSEXPIRE"), "-");


                String tempddep = rst.getString("dep");

                if(depPermitstatus.equalsIgnoreCase("Y"))
                {
                tempHash.put("dep", UtilityObject.checkNullAndFill(tempddep, "-"));
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
                tempHash.put("expirationdate","-");
                }

                String tempPId = rst.getString("boilerpermitid");

                String i_1=checkNullAndFill(tempddep,"axs");
                int j_1=0;
                tempHash.put("boilerpermitid", tempPId == null ? "" : ((Object) (tempPId)));

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
            System.out.println("Exhibit 3 :"+e)	;
            }
            
            
            
            
				   
		            
		    String compliancestatus="";
            
            if(depPermitstatus.equalsIgnoreCase("Y") && tempddep.equals("-") && depPermitStatusExpire.equals("Y") && !UtilityObject.isNotEmpty(exp))
            {
            tempHash.put("compliancestatus", "TBD");
            compliancestatus="TBD";
            }
            else if(depPermitstatus.equalsIgnoreCase("N"))
            {
            tempHash.put("compliancestatus", "Non Compliance");
            compliancestatus="Non Compliance";	
            }
            else if(depPermitstatus.equalsIgnoreCase("Y") && tempddep.equals("-") && depPermitStatusExpire.equals("N") && !UtilityObject.isNotEmpty(exp))
            {
            tempHash.put("compliancestatus", "Non Compliance");
            compliancestatus="Non Compliance";	
            }
            else if(depPermitstatus.equalsIgnoreCase("Y") && !tempddep.equals("-") && depPermitStatusExpire.equals("Y") && !UtilityObject.isNotEmpty(exp) || depPermitStatusExpire.equals("Y") && j_1==0)
            {
            tempHash.put("compliancestatus", "Non Compliance");
            compliancestatus="Non Compliance";	
            }                                
            else if(depPermitstatus.equalsIgnoreCase("Y") && tempddep.equals("-") && depPermitStatusExpire.equals("Y") && exp != null)
            {
            tempHash.put("compliancestatus", "Non Compliance");
            compliancestatus="Non Compliance";	
            }       
            else
		    {
		    tempHash.put("compliancestatus", "Compliance");
		    compliancestatus="Compliance";
		    }
			//********************DEP END*************************//	
	
            int burnerType = UtilityObject.getIntFromString(rst.getString("burnertype"));
                int primaryFuel = UtilityObject.getIntFromString(rst.getString("primaryfuel"));
                int secondaryFuel = UtilityObject.getIntFromString(rst.getString("secondaryfuel"));
                if(burnerType == 2);
                if(primaryFuel > 0)
                {
                    FurnaceOilTypeEnum pEnum = FurnaceOilTypeEnum.get(primaryFuel);
                    tempHash.put("primaryFuel", pEnum == null ? "N/A" : ((Object) (pEnum.getName())));
                }

                //////////////////////////////////////////////////////////

        double tempIntCapacity = Double.parseDouble(tempCapacity);
        double tempLong = -99D;
        if(tempIntCapacity > 0.0D)
        {
        int fu=primaryFuel;

        if(burnerType == 1)
        {

        if(fu==1)
        {
        	tempLong = tempIntCapacity * 1000D;
            //formObj.set("B_naturalGasFiringRate", tempLong + "");
            tempHash.put("naturalGasFiringRate",tempLong + "");
            tempHash.put("oilFiringRate","N/A");
        }
        else
        {
        	int factor = 0;
           			if(fu==2)
        			{
        			factor = 140;
    				}
    				else if(fu==3)
    				{
        			factor = 145;
       				}

       				else if(fu==4)
    				{
        			factor = 150;
       				}


        tempHash.put("oilFiringRate",round((tempIntCapacity * 1000D) / (double)factor,2)+ "");

        tempHash.put("naturalGasFiringRate","N/A");

        }
        tempHash.put("secondaryFuel", "N/A");
    	}
        if(burnerType == 2)
        {


        tempLong = tempIntCapacity * 1000D;

        //formObj.set("B_naturalGasFiringRate", tempLong + "");
        tempHash.put("naturalGasFiringRate",tempLong + "");
        int tempInt =secondaryFuel;
        if(tempInt > 0)
        {
            FurnaceOilTypeEnum sEnum = FurnaceOilTypeEnum.get(tempInt);
            if(sEnum != null)
            {

                tempHash.put("secondaryFuel", checkNullAndFill(sEnum.getName(),"N/A"));
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
                            tempHash.put("oilFiringRate", round((tempIntCapacity * 1000D) / (double)factor,2)+"");
                        else
                            tempHash.put("oilFiringRate", "-");



            } else
            {
                   tempHash.put("secondaryFuel", "N/A");
                   tempHash.put("oilFiringRate", "-");
            }
        } else
        {
            tempHash.put("secondaryFuel", "N/A");
            tempHash.put("oilFiringRate", "-");
        }
        }
       }

            }

        }
        catch(Exception e)
        {
            log.error("getResultForReport(" + facilityid + ")", e);
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
        System.out.println("Exhibit 3 length:"+result.size());
        return result;
    }

    public static List getNycdobStatusReport(int facilityid)
    {
        List nycdobList;
        LinkedHashMap hash4Unique;
        nycdobList = new ArrayList();
        SqlUtil utilObj = new SqlUtil();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rst = null;
        StringBuffer query = new StringBuffer();
        query.append("select blr.BOILERID,blr.FACILITYDESIGNATEDID,bpf.LASTINSPECTIONDATE,bpf.INSPECTIONTYPE,bpf.expirationdate,bpf.inspsubmitteddate, blr.manufacturer,blr.mea, bld.buildingname, ");
        query.append("adr.address1, adr.address2, adr.address3, ");
        query.append("bld.blocknumber, bld.lotnumber, blr.facilitydesignatedid,blr.testconductedby,blr.stacktestprotsubmited ");
        query.append("from  building bld, address adr ,boiler blr left join boilerpermitinfo bpf on blr.boilerid=bpf.boilerid and bpf.depid='2' ");
        query.append("where (blr.MODIFIEDINPAST='1' or blr.MODIFIEDINPAST='5') and blr.buildingid=bld.buildingid and adr.addressid=bld.addressid ");
        query.append("and bld.facilityid=");
        query.append(facilityid);
        query.append(" order by blr.boilerid asc, blr.buildingid asc, bpf.boilerpermitid asc");
         hash4Unique = new LinkedHashMap();
        long startTime = System.currentTimeMillis();
        try
        {
            conn = utilObj.getConnection();
            pstmt = conn.prepareStatement(query.toString());
            //log.debug(query.toString());
            rst = pstmt.executeQuery();
            startTime = System.currentTimeMillis();
            Hashtable tempHash;
            String tempBoilerId;
            for(; rst.next(); hash4Unique.put(tempBoilerId, tempHash))
            {
                tempHash = new Hashtable();
                tempBoilerId = rst.getString("boilerid");
                tempHash.put("boilerid", tempBoilerId);
                tempHash.put("facilitydesinatedid", UtilityObject.checkNullAndFill(rst.getString("FACILITYDESIGNATEDID"), "-"));
                tempHash.put("manufacturer", UtilityObject.checkNullAndFill(rst.getString("manufacturer"), "-"));
                tempHash.put("location", UtilityObject.checkNullAndFill(rst.getString("buildingname"), "-"));
               /*StringBuffer tempAddr = new StringBuffer();
                tempAddr.append(UtilityObject.checkNullAndFill(rst.getString("address1"), " "));
                tempAddr.append(UtilityObject.checkNullAndFill(rst.getString("address2"), " "));
                tempAddr.append(UtilityObject.checkNullAndFill(rst.getString("address3"), " "));
                tempHash.put("aka", tempAddr.toString());
                tempHash.put("block", UtilityObject.checkNullAndFill(rst.getString("blocknumber"), "-"));
                tempHash.put("lot", UtilityObject.checkNullAndFill(rst.getString("lotnumber"), "-"));*/
                tempHash.put("internalId", UtilityObject.checkNullAndFill(rst.getString("mea"), "-"));
                
                /*String inspectyp = checkNullAndFill(rst.getString("INSPECTIONTYPE"), "-"));
                if(inspectyp.equalsIgnoreCase("TRUE"))
                   boilerpermitinfovo.setInspectionType(Internal);
              else
                   boilerpermitinfovo.setInspectionType(false);*/
                
              //  tempHash.put("inspectionType", UtilityObject.checkNullAndFill(rst.getString("INSPECTIONTYPE"), "-"));
                



				String inspecTyp = UtilityObject.checkNullAndFill(rst.getString("INSPECTIONTYPE"), "-");
				
				if(inspecTyp.equalsIgnoreCase("TRUE"))
				{
					tempHash.put("INSPECTIONTYPE", "Internal");
				}				
				else
				{
					tempHash.put("INSPECTIONTYPE", "External");
				}



                String last=checkNullAndFilldate(rst.getString("LASTINSPECTIONDATE"), "-");
                tempHash.put("annualLast",last);
                           

                tempHash.put("testconductedby",UtilityObject.checkNullAndFill(rst.getString("testconductedby"), "N/A"));

               /* String next=checkNullAndFilldate(rst.getString("expirationdate"), "-");
                tempHash.put("annualNext", next);*/


                String next=rst.getString("expirationdate");
				tempHash.put("annualNext", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(next), "-"));
				
				
				int j_1=0;
               

               try
            {
            java.util.Date today=new java.util.Date();
            java.util.Date expi=null;
            	if(next==null)
            	{
            	j_1=0;
            	}
            	else
            	{
            		expi=UtilityObject.convert_YyyyMmDd2MmDdYyyy(next);
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
            System.out.println("Exhibit 4 :"+e)	;
            }


               /*String due=checkNullAndFill(rst.getString("inspsubmitteddate"), "-");

                if(due.equals("-"))
                {

                tempHash.put("reportDueDate","N");

            	}
                else
                {
                expi=UtilityObject.convert_YyyyMmDd2MmDdYyyy(due);

                if(expi!=null)
                cal.setTime(expi);

                tempHash.put("reportDueDate","Y");
            	}
				*/
                tempHash.put("testingSubmitted", UtilityObject.checkNullAndFill(rst.getString("stacktestprotsubmited"), "-"));

               
                if(last.equals("-") && !UtilityObject.isNotEmpty(next))
                {
             	tempHash.put("compliancestatus", "TBD");
            	}

                else if(j_1==0)
                {                
            	tempHash.put("compliancestatus", "Non Compliance");
            	}            
            	else
            	{            	
            	tempHash.put("compliancestatus", "Compliance");
                }
            }

        }
        catch(Exception e)
        {
            System.out.println("Exception in Boiler entity: Exhibit 4:"+e);
            log.error("getNycdobStatusReport(" + facilityid + ")", e);
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
            for(Iterator iter = set.iterator(); iter.hasNext(); nycdobList.add(hash4Unique.get(iter.next())));
        }


        return nycdobList;
    }

    public static String checkNullAndFilldate(String s, String s1)
    {
        if(isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
        {
            String as[] = s.split("-");
            String s2 = as[1] + "/" + as[2] + "/" + as[0];
            return s2;
        } else
        {
            return s1;
        }
    }


	public static boolean isNotEmpty(String s)
    {
        return s != null && s.trim().length() > 0;
    }


    public static List getAnnualTuneUpReport(int facilityid)
    {
        List result;
        LinkedHashMap hash4Unique;
        result = new ArrayList();
        StringBuffer query = new StringBuffer();
        query.append("select blr.boilerid,blr.STACKTESTREQUIRED,blr.NEXTSTACKTESTDATE, blr.FACILITYDESIGNATEDID,  ");
        query.append("      bld.buildingname, ");
        query.append("      blr.testrptsubmited, blr.protocolsubmitdate, blr.stacktestdate, blr.BOILERTUNEUP,");
        query.append("      bpf.performedby, bpf.DATEPERFORMED ");
        query.append("from  building bld, boiler blr left join annualtuneup bpf on blr.boilerid=bpf.boilerid ");
        query.append(" where (blr.MODIFIEDINPAST='1' or blr.MODIFIEDINPAST='5') and blr.buildingid=bld.buildingid and ");
        query.append("  bld.facilityid=");
        query.append(facilityid);
        query.append(" order by blr.boilerid asc, blr.buildingid asc, bpf.annualtuneupid asc");
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
                tempBoilerId = rst.getString("boilerid");
                tempHash.put("boilerid", tempBoilerId);
                tempHash.put("manufacturer", UtilityObject.checkNullAndFill(rst.getString("FACILITYDESIGNATEDID"), "-"));                
                tempHash.put("location", UtilityObject.checkNullAndFill(rst.getString("buildingname"), "-"));


                String boilerTuneUp = rst.getString("BOILERTUNEUP");
				tempHash.put("BOILERTUNEUP", UtilityObject.checkNullAndFill(boilerTuneUp, "-"));

				String pyd=rst.getString("DATEPERFORMED");
				String  py=rst.getString("performedby");

                if(boilerTuneUp.equalsIgnoreCase("Y"))
                {
                tempHash.put("DATEPERFORMED", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(pyd), "-"));
                tempHash.put("performedby", UtilityObject.checkNullAndFill(py, "-"));
                }
                else if(boilerTuneUp.equalsIgnoreCase("N"))
                {
                tempHash.put("DATEPERFORMED","-");
                tempHash.put("performedby","-");
                }
                else
                {
                tempHash.put("DATEPERFORMED","N/A");
                tempHash.put("performedby","N/A");
                }

			int j_1 = 0;
            try
            {
			java.util.Date today=new java.util.Date(); 
			java.util.Date afterOneYear = new java.util.Date(); 
		
					      
            java.util.Date expi=null;            
            
            	if(pyd==null)
            	{
            	j_1=0;
            	}
            	else
            	{
            		expi=UtilityObject.convert_YyyyMmDd2MmDdYyyy(pyd);
            		Calendar cal = Calendar.getInstance();
            		cal.setTime(expi);
            		cal.add(Calendar.YEAR,1); 
					 afterOneYear = cal.getTime();
            		System.out.println(expi);
            		
            	}

		            	if(expi==null)
		            	{
		            		j_1=0;
		            	}

				            else if(expi!=null)
				            {

					            j_1 = today.compareTo(afterOneYear);
					            System.out.println(afterOneYear+"/"+expi+"="+j_1);
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
                    System.out.println((new StringBuilder()).append("Exhibit 9 :").append(e).toString());
                }
                
                
            
            String  repsub= UtilityObject.checkNullAndFill(rst.getString("STACKTESTREQUIRED"), "-");            
            tempHash.put("reportSubmitted", UtilityObject.checkNullAndFill(repsub, "-"));
                
            String stacktestdate=rst.getString("testrptsubmited");
            String expirationdate=rst.getString("NEXTSTACKTESTDATE");
                
                
                
            int k_1=0;
            try
            {
            java.util.Date today=new java.util.Date();
            java.util.Date expir=null;
            	if(expirationdate==null)
            	{
            	k_1=0;
            	}
            	else
            	{
            		expir=UtilityObject.convert_YyyyMmDd2MmDdYyyy(expirationdate);
            	}

		            	if(expir==null)
		            	{
		            		k_1=0;
		            	}

				            else if(expir!=null)
				            {

					            k_1 = today.compareTo(expir);
					            if(k_1 > 0)
					            {
					            	k_1=0;
					            }
					            else
					            {
					            	k_1=1;
					            }
		     }
		     else
		     {
		     k_1=0;
		     }

            }
            catch(Exception e)
            {
            System.out.println("Exhibit 5 :"+e)	;
            }
            
            
                if(repsub.equalsIgnoreCase("Y"))
                {                	
                	tempHash.put("stacktestdate", UtilityObject.checkNullAndFill(stacktestdate, "-"));
                	tempHash.put("expirationdate", UtilityObject.checkNullAndFill(UtilityObject.convertYyyyMmDd2MmDdYyyy(expirationdate), "-"));
            	}
                else if(repsub.equalsIgnoreCase("N"))
                {
                	tempHash.put("stacktestdate","-");
                	tempHash.put("expirationdate","-");	
                }
                else
                {
                	tempHash.put("stacktestdate","N/A");
                	tempHash.put("expirationdate","N/A");
                }
                
                
                
                
                if(boilerTuneUp.equalsIgnoreCase("Y") && !UtilityObject.isNotEmpty(pyd))
                {
                    tempHash.put("compliancestatus", "Non Compliance");
                }
                else if(boilerTuneUp.equalsIgnoreCase("Y") && j_1==0)
                {
                    tempHash.put("compliancestatus", "Non Compliance");
                }           
                else if(boilerTuneUp.equals("N"))
                {
                    tempHash.put("compliancestatus", "Non Compliance");
                }
                else if(repsub.equalsIgnoreCase("Y") && stacktestdate.equals("-") && !UtilityObject.isNotEmpty(expirationdate))
                {
                	tempHash.put("compliancestatus", "TBD");	
                }
                else if(repsub.equalsIgnoreCase("Y") && stacktestdate.equals("-"))
                {
                	tempHash.put("compliancestatus", "Non Compliance");	
                }
                else if(repsub.equalsIgnoreCase("Y") && !UtilityObject.isNotEmpty(expirationdate))
                {
                	tempHash.put("compliancestatus", "Non Compliance");	
                }
                else if(repsub.equalsIgnoreCase("Y") && k_1==0)
                {
                	tempHash.put("compliancestatus", "Non Compliance");	
                }               
                else
                {
                	tempHash.put("compliancestatus","Compliance");
                }

            }

        }
        catch(Exception e)
        {
            System.out.println("Exhibit 5 Exception in Boiler Entity:"+e);
            log.error("getAnnualTuneUpReport(" + facilityid + ")", e);
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


  public static double round(double value, int decimalPlace)
  {
    double power_of_ten = 1;
    while (decimalPlace-- > 0)
       power_of_ten *= 10.0;
    return Math.round(value * power_of_ten) / power_of_ten;
  }


  public static String checkNullAndFill(String s, String s1)
    {
        if(isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
        {
            return s;
        } else
        {
            return s1;
        }
    }


     public static List getFacilityStackList(int i)
        throws TrackingException
    {
        Object obj = new ArrayList();
        String s = "select stk.BOILERID, stk.FACILITYDESIGNATEDID, stk.MAKE, stk.MODEL, stk.FLOOR, stk.SERIAL, stk.CAPACITY," +
" bldg.BUILDINGNAME from boiler stk, BUILDING bldg where stk.buildingid=" +
"bldg.buildingid and bldg.facilityid=?";
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            obj = sqlutil.execQueryUsingConstructor(s, com.eespc.tracking.bo.BoilerListVo.class);

        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("Getting Boiler List for facility id =").append(i).toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return ((List) (obj));
    }



    private static Log log;

    static
    {
        log = LogFactory.getLog(com.eespc.tracking.entity.BoilerEntity.class);
    }
}