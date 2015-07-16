package com.eespc.tracking.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.EngineRunningHrsVo;
import com.eespc.tracking.bo.FuelConsumptionVo;
import com.eespc.tracking.bo.myenum.ResourcesTypeEnum;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.SqlUtil;

public class EngineRunningHrsEntity
{

    public EngineRunningHrsEntity()
    {
    }

    public static int add(EngineRunningHrsVo enginerunninghrsvo, int i)
        throws TrackingException
    {
        if(log.isDebugEnabled())
            log.debug((new StringBuilder()).append("To insert,").append(enginerunninghrsvo).toString());
        int j = -99;
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == i)
        {
            stringbuffer.append("insert into GENERATORENGINECONSUMPTION ");
            stringbuffer.append("(GNRTANNENGINECONSID,");
            stringbuffer.append("GENERATORID,");
        }
        else
        {
            throw new TrackingException("Not a valid type");
        }
        stringbuffer.append("YEAR,");
        stringbuffer.append("bLOCK,");
        stringbuffer.append("bJAN,");
        stringbuffer.append("bFEB,");
        stringbuffer.append("bMAR,");
        stringbuffer.append("bAPR,");
        stringbuffer.append("bMAY,");
        stringbuffer.append("bJUN,");
        stringbuffer.append("bJUL,");
        stringbuffer.append("bAUG,");
        stringbuffer.append("bSEP,");
        stringbuffer.append("bOCT,");
        stringbuffer.append("bNOV,");
        stringbuffer.append("bDEC,");
        stringbuffer.append("YEARTODATE,");   
        stringbuffer.append("bIndex,ebctype)");
        stringbuffer.append(" values (null,");
        stringbuffer.append(enginerunninghrsvo.getEntityId()).append(",");
        stringbuffer.append("'").append(enginerunninghrsvo.getYear()).append("',");
        if(enginerunninghrsvo.isLocked())
            stringbuffer.append("'Y',");
        else
            stringbuffer.append("'N',");
        stringbuffer.append(enginerunninghrsvo.getJan()).append(",");
        stringbuffer.append(enginerunninghrsvo.getFeb()).append(",");
        stringbuffer.append(enginerunninghrsvo.getMar()).append(",");
        stringbuffer.append(enginerunninghrsvo.getApr()).append(",");
        stringbuffer.append(enginerunninghrsvo.getMay()).append(",");
        stringbuffer.append(enginerunninghrsvo.getJun()).append(",");
        stringbuffer.append(enginerunninghrsvo.getJul()).append(",");
        stringbuffer.append(enginerunninghrsvo.getAug()).append(",");
        stringbuffer.append(enginerunninghrsvo.getSep()).append(",");
        stringbuffer.append(enginerunninghrsvo.getOct()).append(",");
        stringbuffer.append(enginerunninghrsvo.getNov()).append(",");
        stringbuffer.append(enginerunninghrsvo.getDec()).append(",");
        stringbuffer.append(enginerunninghrsvo.getYearToDate()).append(",");      
      /*  if(enginerunninghrsvo.isCompliant())
            stringbuffer.append("'Y',");
        else
            stringbuffer.append("'N',");*/
        stringbuffer.append(enginerunninghrsvo.getIndex()).append(",'");
        stringbuffer.append(enginerunninghrsvo.getEbctype()).append("'");
        stringbuffer.append(")");
        System.out.println("Query:"+stringbuffer.toString());
        System.out.println("Total Hrs of Engine Running Time"+enginerunninghrsvo.getYearToDate());  
        SqlUtil sqlutil = new SqlUtil();
        try
        {
            j = sqlutil.insert(stringbuffer.toString());
        }
        catch(Exception exception)
        {
            
            System.out.println("While Adding EngineConsumption:"+exception);
            TrackingException trackingexception = new TrackingException("While Adding EngineRunningHrs");
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return j;
    }

    public static void update(EngineRunningHrsVo enginerunninghrsvo, int i)
        throws TrackingException
    {
        if(log.isDebugEnabled())
            log.debug((new StringBuilder()).append("To update,").append(enginerunninghrsvo).toString());
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == i)
        {
            stringbuffer.append("update GENERATORENGINECONSUMPTION set ");
            commonForUpdate(stringbuffer);
            stringbuffer.append("where GNRTANNENGINECONSID=?");
        }         
         else
        {
            throw new TrackingException("Not a valid type");
        }
        SqlUtil sqlutil = new SqlUtil();
        sqlutil.addInParam(enginerunninghrsvo.getYear());
         System.out.println("Year of Engine Running Time"+enginerunninghrsvo.getYear());  
        if(enginerunninghrsvo.isLocked())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");
        sqlutil.addInParam(new Float(enginerunninghrsvo.getJan()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getFeb()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getMar()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getApr()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getMay()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getJun()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getJul()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getAug()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getSep()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getOct()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getNov()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getDec()));
        sqlutil.addInParam(new Float(enginerunninghrsvo.getYearToDate()));
        
        System.out.println("Total Hrs of Engine Running Time"+enginerunninghrsvo.getYearToDate());        
        
        /*if(enginerunninghrsvo.isCompliant())
            sqlutil.addInParam("Y");
        else
            sqlutil.addInParam("N");*/
        sqlutil.addInParam(new Integer(enginerunninghrsvo.getIndex()));
        sqlutil.addInParam(new Integer(enginerunninghrsvo.getId()));
        try
        {
            sqlutil.execForDmlUsingQuery(stringbuffer.toString());
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException("While Updating EngineRunningHrs.");
            trackingexception.initCause(exception);
            throw trackingexception;
        }
    }

    private static void commonForUpdate(StringBuffer stringbuffer)
    {
        stringbuffer.append("YEAR=?,");
        stringbuffer.append("bLOCK=?,");
        stringbuffer.append("bJAN=?,");
        stringbuffer.append("bFEB=?,");
        stringbuffer.append("bMAR=?,");
        stringbuffer.append("bAPR=?,");
        stringbuffer.append("bMAY=?,");
        stringbuffer.append("bJUN=?,");
        stringbuffer.append("bJUL=?,");
        stringbuffer.append("bAUG=?,");
        stringbuffer.append("bSEP=?,");
        stringbuffer.append("bOCT=?,");
        stringbuffer.append("bNOV=?,");
        stringbuffer.append("bDEC=?,");
        stringbuffer.append("YEARTODATE=?,");      
       // stringbuffer.append("COMPLIANT=?, ");
        stringbuffer.append("bIndex=? ");
    }
    
    
    public static List getEngineRunningHrsyear(int i, int j,String year)
        throws TrackingException
    {
    	System.out.println("In Year Consumption i="+i+" j="+j+" Year="+year);
        log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
        Object obj = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonGetEngineRunningHrs(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GENERATORID = ? and ebctype=? and year=?");
        } 
         else
        {
            throw new TrackingException("Not a valid type");
        }
        
        
        try
        {
        	System.out.println("Query:"+stringbuffer.toString());
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            sqlutil.addInParam("1");
            sqlutil.addInParam(year);
            obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        List ss=(List) (obj);
        
        System.out.println("Year List length:"+ss.size());
        return ((List) (obj));
    }
 
    public static List geto_PressureTestyear(int i, int j,String year)
        throws TrackingException
    {
    	System.out.println("In Year Consumption i="+i+" j="+j+" Year="+year);
        log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
        Object obj = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
        
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonGetEngineRunningHrs(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GENERATORID = ? and ebctype=? and year=?");
        }             
         else
        {
            throw new TrackingException("Not a valid type");
        }
        
        
        try
        {
        	System.out.println("Query:"+stringbuffer.toString());
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            sqlutil.addInParam("2");
            sqlutil.addInParam(year);
            obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        List ss=(List) (obj);
        
        System.out.println("Year List length:"+ss.size());
        return ((List) (obj));
    }
    
    
    
    public static List getg_PressureTestyear(int i, int j,String year)
            throws TrackingException
        {
        	System.out.println("In Year Consumption i="+i+" j="+j+" Year="+year);
            log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
            
            if(ResourcesTypeEnum.GNERATOR.getCode() == j)
            {
                stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
                stringbuffer.append("GENERATORID as ENTITY_ID, ");
                commonGetEngineRunningHrs(stringbuffer);
                stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
                stringbuffer.append("where GENERATORID = ? and ebctype=? and year=?");
            }             
             else
            {
                throw new TrackingException("Not a valid type");
            }
            
            
            try
            {
            	System.out.println("Query:"+stringbuffer.toString());
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                sqlutil.addInParam("3");
                sqlutil.addInParam(year);
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            List ss=(List) (obj);
            
            System.out.println("Year List length:"+ss.size());
            return ((List) (obj));
        }
    
    public static List getanu_PressureTestyear(int i, int j,String year)
            throws TrackingException
        {
        	System.out.println("In Year Consumption i="+i+" j="+j+" Year="+year);
            log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
            
            if(ResourcesTypeEnum.GNERATOR.getCode() == j)
            {
                stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
                stringbuffer.append("GENERATORID as ENTITY_ID, ");
                commonGetEngineRunningHrs(stringbuffer);
                stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
                stringbuffer.append("where GENERATORID = ? and ebctype=? and year=?");
            }             
             else
            {
                throw new TrackingException("Not a valid type");
            }
            
            
            try
            {
            	System.out.println("Query:"+stringbuffer.toString());
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                sqlutil.addInParam("4");
                sqlutil.addInParam(year);
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            List ss=(List) (obj);
            
            System.out.println("Year List length:"+ss.size());
            return ((List) (obj));
        }
        
    //--------------------INK
    public static List getEngineRunningHrs(int i, int j,int ebctype)
        throws TrackingException
    {
        log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
        Object obj = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonGetEngineRunningHrs(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GENERATORID = ? and ebctype=?");
        }
         else
        {
            throw new TrackingException("Not a valid type");
        }
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            sqlutil.addInParam(ebctype);
            obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        List ss=(List) (obj);
        System.out.println("G List length:"+ss.size());
        return ((List) (obj));
    }
    
    public static List getEngineRunningHrsyear(int i, int j,String year,int ebctype)
        throws TrackingException
    {
    	System.out.println("In Year Consumption i="+i+" j="+j+" Year="+year);
        log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
        Object obj = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
        
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonGetEngineRunningHrs(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GENERATORID = ? and ebctype=? and year=?");
        } 
         else
        {
            throw new TrackingException("Not a valid type");
        }
        
        
        try
        {
        	System.out.println("Query:"+stringbuffer.toString());
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            sqlutil.addInParam(ebctype);
            sqlutil.addInParam(year);
            obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        List ss=(List) (obj);
        
        System.out.println("Year List length:"+ss.size());
        return ((List) (obj));
    }
    
    //INK End
    

    public static List getEngineRunningHrs(int i, int j)
        throws TrackingException
    {
        log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
        Object obj = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonGetEngineRunningHrs(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GENERATORID = ? and ebctype=? ORDER BY year ASC");
        } 
         else
        {
            throw new TrackingException("Not a valid type");
        }
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            sqlutil.addInParam("1");
            obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        List ss=(List) (obj);
        System.out.println("G List length:"+ss.size());
        return ((List) (obj));
    }
    
    public static List geto_PressureTestHrs(int i, int j)
        throws TrackingException
    {
        
        log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
        Object obj = new ArrayList();
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonGetEngineRunningHrs(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GENERATORID = ? and ebctype=? ORDER BY year ASC");
        } 
         else
        {
            throw new TrackingException("Not a valid type");
        }
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            sqlutil.addInParam("2");
            System.out.println(stringbuffer.toString()+"@"+new Integer(i));
            obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("geto_PressureTestHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        List ss=(List) (obj);
        System.out.println("O List length:"+ss.size());
        return ((List) (obj));
    }
    
    
    public static List getg_PressureTestHrs(int i, int j)
            throws TrackingException
        {
            
            log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
           
            if(ResourcesTypeEnum.GNERATOR.getCode() == j)
            {
                stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
                stringbuffer.append("GENERATORID as ENTITY_ID, ");
                commonGetEngineRunningHrs(stringbuffer);
                stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
                stringbuffer.append("where GENERATORID = ? and ebctype=? ORDER BY year ASC");
            } 
             else
            {
                throw new TrackingException("Not a valid type");
            }
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                sqlutil.addInParam("3");
                System.out.println(stringbuffer.toString()+"@"+new Integer(i));
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getg_PressureTestHrs(").append(i).append(",").append(j).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            List ss=(List) (obj);
            System.out.println("G List length:"+ss.size());
            return ((List) (obj));
        }
    
    
    public static List getanu_PressureTestHrs(int i, int j)
            throws TrackingException
        {
            
            log.debug((new StringBuilder()).append("id=").append(i).append(",type=").append(j).append(",").append(ResourcesTypeEnum.GNERATOR.getCode()).append(" isEqual ?").append(ResourcesTypeEnum.GNERATOR.getCode() == j).toString());
            Object obj = new ArrayList();
            StringBuffer stringbuffer = new StringBuffer();
           
            if(ResourcesTypeEnum.GNERATOR.getCode() == j)
            {
                stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
                stringbuffer.append("GENERATORID as ENTITY_ID, ");
                commonGetEngineRunningHrs(stringbuffer);
                stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
                stringbuffer.append("where GENERATORID = ? and ebctype=? ORDER BY year ASC");
            } 
             else
            {
                throw new TrackingException("Not a valid type");
            }
            try
            {
                SqlUtil sqlutil = new SqlUtil();
                sqlutil.addInParam(new Integer(i));
                sqlutil.addInParam("4");
                System.out.println(stringbuffer.toString()+"@"+new Integer(i));
                obj = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
            }
            catch(Exception exception)
            {
                TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getanu_PressureTestHrs(").append(i).append(",").append(j).append(")").toString());
                trackingexception.initCause(exception);
                throw trackingexception;
            }
            List ss=(List) (obj);
            System.out.println("Anu List length:"+ss.size());
            return ((List) (obj));
        }
    
    
    private static void commonGetEngineRunningHrs(StringBuffer stringbuffer)
    {
        stringbuffer.append("YEAR, ");
        stringbuffer.append("bLOCK as 'LOCK', ");
        stringbuffer.append("bJAN as JAN, ");
        stringbuffer.append("bFEB as FEB, ");
        stringbuffer.append("bMAR as MAR, ");
        stringbuffer.append("bAPR as APR, ");
        stringbuffer.append("bMAY as MAY, ");
        stringbuffer.append("bJUN as JUN, ");
        stringbuffer.append("bJUL as JUL, ");
        stringbuffer.append("bAUG as AUG, ");
        stringbuffer.append("bSEP as SEP, ");
        stringbuffer.append("bOCT as OCT, ");
        stringbuffer.append("bNOV as NOV, ");
        stringbuffer.append("bDEC as 'DEC', ");
        stringbuffer.append("YEARTODATE , ");  
       // stringbuffer.append("COMPLIANT,  ");
        stringbuffer.append("bIndex as 'index',ebctype ");
    }

    public static EngineRunningHrsVo findByPrimaryKey(int i, int j)
        throws TrackingException
    {
        EngineRunningHrsVo enginerunninghrsvo = null;
        StringBuffer stringbuffer = new StringBuffer();
       
        if(ResourcesTypeEnum.GNERATOR.getCode() == j)
        {
            stringbuffer.append("select GNRTANNENGINECONSID as ID, ");
            stringbuffer.append("GENERATORID as ENTITY_ID, ");
            commonFindByPrimaryKey(stringbuffer);
            stringbuffer.append("from GENERATORENGINECONSUMPTION  ");
            stringbuffer.append("where GNRTANNENGINECONSID = ? ");
        } 
        else
        {
            throw new TrackingException("Not a valid type");
        }
        
        
        try
        {
            SqlUtil sqlutil = new SqlUtil();
            sqlutil.addInParam(new Integer(i));
            List list = sqlutil.execQueryUsingConstructor(stringbuffer.toString(), com.eespc.tracking.bo.EngineRunningHrsVo.class);
            if(list != null && list.size() > 0)
                enginerunninghrsvo = (EngineRunningHrsVo)list.get(0);
        }
        catch(Exception exception)
        {
            TrackingException trackingexception = new TrackingException((new StringBuilder()).append("getEngineRunningHrs(").append(i).append(",").append(j).append(")").toString());
            trackingexception.initCause(exception);
            throw trackingexception;
        }
        return enginerunninghrsvo;
    }

    private static void commonFindByPrimaryKey(StringBuffer stringbuffer)
    {
        stringbuffer.append("YEAR, ");
        stringbuffer.append("bLOCK as 'LOCK', ");
        stringbuffer.append("bJAN as JAN, ");
        stringbuffer.append("bFEB as FEB, ");
        stringbuffer.append("bMAR as MAR, ");
        stringbuffer.append("bAPR as APR, ");
        stringbuffer.append("bMAY as MAY, ");
        stringbuffer.append("bJUN as JUN, ");
        stringbuffer.append("bJUL as JUL, ");
        stringbuffer.append("bAUG as AUG, ");
        stringbuffer.append("bSEP as SEP, ");
        stringbuffer.append("bOCT as OCT, ");
        stringbuffer.append("bNOV as NOV, ");
        stringbuffer.append("bDEC as 'DEC', ");
        stringbuffer.append("YEARTODATE , ");     
        stringbuffer.append("COMPLIANT,  ");
        stringbuffer.append("bIndex as 'index' ");
    }

    private static Log log = LogFactory.getLog(com.eespc.tracking.entity.EngineRunningHrsEntity.class);

}