
package com.eespc.tracking.bo;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class GeneratorCfrPermitInfoVo
{

    
    public GeneratorCfrPermitInfoVo()
    { 
        id = -99;
        generatorId = -99;        
        initialTestDate = null;
        initialTestDateStr = null; 
        
        initialPressureOil = null;
        initialPressureGas = null;
        lastSubsequentTestDate = null;
        lastSubsequentTestDateStr = null; 
        
        subsequentPressureOil = null;
        subsequentPressureGas = null; 
    }

    public GeneratorCfrPermitInfoVo(ResultSet resultset)
        throws SQLException
    {
        id = -99;
        generatorId = -99;       
        initialTestDate = null;
        initialTestDateStr = null;
        initialPressureOil = null;
        initialPressureGas = null;
        lastSubsequentTestDate = null;
        lastSubsequentTestDateStr = null; 
        subsequentPressureOil = null;
        subsequentPressureGas = null;
        
        
        
        id = resultset.getInt("GNRTCFRPERMITID");
        generatorId = resultset.getInt("GENERATORID");             
        initialTestDate = resultset.getDate("INITIALTESTDATE");
        initialPressureOil = resultset.getString("INITIALPRESSUREOIL");
        initialPressureGas = resultset.getString("INITIALPRESSUREGAS");        
        lastSubsequentTestDate = resultset.getDate("SUBSEQUENTTESTDATE");       
        subsequentPressureOil = resultset.getString("SUBSEQUENTPRESSUREOIL");
        subsequentPressureGas = resultset.getString("SUBSEQUENTPRESSUREGAS");       
        
       
    }


    public int getId()
    {
        return id;
    }

    public void setId(int i)
    {
        id = i;
    }
    
    
    public int getGeneratorId()
    {
        return generatorId;
    }

    public void setGeneratorId(int i)
    {
        generatorId = i;
    }


    
    public Date getInitialTestDate()
    {
        return initialTestDate;
    }

    public void setInitialTestDate(Date date)
    {
        initialTestDate = date;
    }
    
    

    
    public String getInitialPressureOil()
    {
        return initialPressureOil;
    }

    public void setInitialPressureOil(String i)
    {
        initialPressureOil = i;
    }
    
    public String getInitialPressureGas()
    {
        return initialPressureGas;
    }

    public void setInitialPressureGas(String i)
    {
        initialPressureGas = i;
    }

    public Date getLastSubsequentTestDate()
    {
        return lastSubsequentTestDate;
    }

    public void setLastSubsequentTestDate(Date date)
    {
        lastSubsequentTestDate = date;
    }

   
   public String getSubsequentPressureOil()
    {
        return subsequentPressureOil;
    }

    public void setSubsequentPressureOil(String i)
    {
        subsequentPressureOil = i;
    }
    
    public String getSubsequentPressureGas()
    {
        return subsequentPressureGas;
    }

    public void setSubsequentPressureGas(String i)
    {
        subsequentPressureGas = i;
    }


    public String getInitialTestDateStr()
    {
        if(initialTestDate == null)
        {
            return "";
        } else
        {
            return UtilityObject.convertToString(initialTestDate);
        }
    }

    public void setInitialTestDateStr(String s)
    {
        initialTestDateStr = s;
    }

    public String getLastSubsequentTestDateStr()
    {
        if(lastSubsequentTestDate == null)
        {
            return "";
        } else
        {
            return UtilityObject.convertToString(lastSubsequentTestDate);
        }
    }

    public void setLastSubsequentTestDateStr(String s)
    {
    	lastSubsequentTestDateStr = s;
    }
  	public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = ", ";
        stringbuffer.append("id  =").append(id).append(s);
        stringbuffer.append("generatorId  =").append(generatorId).append(s);        
        stringbuffer.append("initialTestDate  =").append(initialTestDate).append(s);
        stringbuffer.append("initialPressureOil  =").append(initialPressureOil).append(s);
        stringbuffer.append("initialPressureGas  =").append(initialPressureGas).append(s);
        stringbuffer.append("lastSubsequentTestDate  =").append(lastSubsequentTestDate).append(s);
        stringbuffer.append("subsequentPressureOil  =").append(subsequentPressureOil).append(s);
        stringbuffer.append("subsequentPressureGas  =").append(subsequentPressureGas);
        
        return stringbuffer.toString();
    }

    protected int id;
    protected int generatorId;  
    protected Date initialTestDate;
    protected String initialPressureOil;
    protected String initialPressureGas;
    protected Date lastSubsequentTestDate;    
    protected String subsequentPressureOil;
    protected String subsequentPressureGas;
    protected String initialTestDateStr;
    protected String lastSubsequentTestDateStr;


}