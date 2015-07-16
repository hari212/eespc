
package com.eespc.tracking.bo;

import com.eespc.tracking.util.UtilityObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class BoilerPermitInfoVo
{

    public BoilerPermitInfoVo()
    {
        id = -99;
        boilerId = -99;
        year = null;        
        permitNumber = null;
        issueDate = null;
        expirationDate = null;
        inspecSubmittedDate = null;
        depId = -99;
        issueDateStr = null;
        expirationDateStr = null;
        inspecSubmittedDateStr = null;
        dobLastinspectionDate = null;
        dobLastinspectionDateStr = null;
        depExpirationNote = null;
        dobExpirationNote = null;
        depCompliancecomments  =null;
        
        isInspectionType = true;
    }

    public BoilerPermitInfoVo(ResultSet resultset)
        throws SQLException
    {
        id = -99;
        boilerId = -99;
        year = null;
        permitNumber = null;
        issueDate = null;
        expirationDate = null;
        inspecSubmittedDate = null;
        depId = -99;
        issueDateStr = null;
        expirationDateStr = null;
        inspecSubmittedDateStr = null;
        dobLastinspectionDate = null;
        dobLastinspectionDateStr = null;
        depExpirationNote = null;
        dobExpirationNote = null;
        depCompliancecomments  =null;
        
        isInspectionType = true;
        
        
        
        
        year = resultset.getString("YEAR");
        id = resultset.getInt("BOILERPERMITID");
        boilerId = resultset.getInt("BOILERID");
        dobLastinspectionDate = resultset.getDate("LASTINSPECTIONDATE");
        permitNumber = resultset.getString("PERMITNUMBER");
        issueDate = resultset.getDate("ISSUEDDATE");
        expirationDate = resultset.getDate("EXPIRATIONDATE");
        depCompliancecomments=resultset.getString("DEPCOMPLIANCECOMMENTS");
        depId = resultset.getInt("DEPID");
        inspecSubmittedDate = resultset.getDate("INSPSUBMITTEDDATE");
        depExpirationNote = resultset.getString("DEPEXPIRATIONNOTE");
        dobExpirationNote = resultset.getString("DOBEXPIRATIONNOTE");
        isInspectionType = UtilityObject.convertBoolean(resultset.getString("INSPECTIONTYPE"));
        //isInspectionType = resultset.getString("INSPECTIONTYPE");
        
    }
	
	
	
	
	public boolean isInspectionType()
    {
        return isInspectionType;
    }

    public void setInspectionType(boolean flag)
    {
        isInspectionType = flag;
    }
    
    
	
	public String getYear()
    {
        return year;
    }
    public void setYear(String s)
    {
        year = s;
    }
    
    
    public int getBoilerId()
    {
        return boilerId;
    }

    public void setBoilerId(int i)
    {
        boilerId = i;
    }

    public int getDepId()
    {
        return depId;
    }

    public void setDepId(int i)
    {
        depId = i;
    }

    public Date getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(Date date)
    {
        expirationDate = date;
    }
	
	
	public void setDepCompliancecomments(String s)
    {
        depCompliancecomments = s;
    }

    public String getDepCompliancecomments()
    {
        return depCompliancecomments;
    }
    
    public int getId()
    {
        return id;
    }

    public void setId(int i)
    {
        id = i;
    }

	public void setDepExpirationNote(String s)
    {
        depExpirationNote = s;
    }
    public String getDepExpirationNote()
    {
        return depExpirationNote;
    }


	public void setDobExpirationNote(String s)
    {
        dobExpirationNote = s;
    }
    public String getDobExpirationNote()
    {
        return dobExpirationNote;
    }


    public Date getInspecSubmittedDate()
    {
        return inspecSubmittedDate;
    }

    public void setInspecSubmittedDate(Date date)
    {
        inspecSubmittedDate = date;
    }
    
   

    public Date getIssueDate()
    {
        return issueDate;
    }

    public void setIssueDate(Date date)
    {
        issueDate = date;
    }

    public String getPermitNumber()
    {
        return permitNumber;
    }

    public void setPermitNumber(String s)
    {
        permitNumber = s;
    }
    
   
     public Date getLastinspectionDate()
    {
        return dobLastinspectionDate;
    }

    public void setLastinspectionDate(Date date)
    {
        dobLastinspectionDate = date;
    }
    
    public String getLastinspectionDateStr()
    {
        if(dobLastinspectionDate == null)
            return "";
        else
            return UtilityObject.convertToString(dobLastinspectionDate);
    }

    public void setLastinspectionDateStr(String s)
    {
        dobLastinspectionDateStr = s;
    }

    public String getExpirationDateStr()
    {
        if(expirationDate == null)
            return "";
        else
            return UtilityObject.convertToString(expirationDate);
    }

    public void setExpirationDateStr(String s)
    {
        expirationDateStr = s;
    }

    public String getInspecSubmittedDateStr()
    {
        if(inspecSubmittedDate == null)
            return "";
        else
            return UtilityObject.convertToString(inspecSubmittedDate);
    }

    public void setInspecSubmittedDateStr(String s)
    {
        inspecSubmittedDateStr = s;
    }

    public String getIssueDateStr()
    {
        if(issueDate == null)
            return "";
        else
            return UtilityObject.convertToString(issueDate);
    }

    public void setIssueDateStr(String s)
    {
        issueDateStr = s;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        String s = ", ";
        stringbuffer.append("id  =").append(id).append(s);
        stringbuffer.append("boilerId  =").append(boilerId).append(s);
        stringbuffer.append("year  =").append(year).append(s);
        stringbuffer.append("dobLastinspectionDate  =").append(dobLastinspectionDate).append(s);
        stringbuffer.append("permitNumber  =").append(permitNumber).append(s);
        stringbuffer.append("issueDate  =").append(issueDate).append(s);
        stringbuffer.append("expirationDate  =").append(expirationDate).append(s);
        stringbuffer.append("depId  =").append(depId).append(s);
        stringbuffer.append("depCompliancecomments  =").append(depCompliancecomments).append(s);
        stringbuffer.append("depExpirationNote  =").append(depExpirationNote).append(s);
        stringbuffer.append("dobExpirationNote  =").append(dobExpirationNote).append(s);
        stringbuffer.append("isInspectionType  =").append(isInspectionType);
        return stringbuffer.toString();
    }

    protected int id;
    protected int boilerId; 
    protected String year;   
    protected String permitNumber;
    protected Date issueDate;
    protected Date expirationDate;
    protected Date inspecSubmittedDate;
    protected Date dobLastinspectionDate;
    protected int depId;
    protected String issueDateStr;
    protected String expirationDateStr;
    protected String inspecSubmittedDateStr;
    protected String dobLastinspectionDateStr;
    protected String depExpirationNote;
    protected String depCompliancecomments;
    protected String dobExpirationNote;
    protected boolean isInspectionType;

}