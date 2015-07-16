// Decompiled by DJ v3.10.10.93 Copyright 2007 Atanas Neshkov  Date: 4/2/2009 2:30:22 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   StorageTankVo.java

package com.eespc.tracking.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.entity.StorageTankEntity;
import com.eespc.tracking.exceptions.TrackingException;
import com.eespc.tracking.util.UtilityObject;

public class StorageTankVo {

	public StorageTankVo() {
		id = -99;
		buildingId = -99;
		isDayTank = false;
		pbsNumber = null;
		pbsExpirationDate = null;
		pbsExpirationDateNote = null;
		fuelType = -99;
		capacity = null;
		tankType = -99;
		regionalLocation = null;
		cathodicInstallationDate = null;
		corrosionProtectionType = -99;
		spillPreventionType = -99;
		tankStatus = -99;
		pipeType = -99;
		pipeInternalProtection = -99;
		tankExternalProtection = -99;
		externalProtection = -99;
		overfillProtection = -99;
		secondaryContainment = -99;
		dobApproval = null;
		fireDeptCertificate = null;
		location = -99;
		yearInstalled = null;
		disconnecteddate = null;
		testcompany = null;
		productStored = -99;
		regWithStateAgency = false;
		permitInfo = null;
		facilityDesignatedId = null;
		trienialCathodicTestList = null;
		tightnessTestList = null;
		spillControlList = null;
		tankLocation = -99;
		scomments = null;
		fuelsuppliedto = null;
		dobsignoff = null;
		certificateofapproval = null;
		tankTightness = null;
		dispenser = -99;
		leakDetection = -99;
		pipingSecContainment = -99;
		dobfiling = null;
	}

	public StorageTankVo(ResultSet resultset) throws SQLException {
		id = -99;
		buildingId = -99;
		isDayTank = false;
		pbsNumber = null;
		pbsExpirationDate = null;
		pbsExpirationDateNote = null;
		fuelType = -99;
		capacity = null;
		tankType = -99;
		regionalLocation = null;
		cathodicInstallationDate = null;
		corrosionProtectionType = -99;
		tankStatus = -99;
		pipeType = -99;
		pipeInternalProtection = -99;
		tankExternalProtection = -99;
		spillPreventionType = -99;
		externalProtection = -99;
		overfillProtection = -99;
		secondaryContainment = -99;
		dobApproval = null;
		fireDeptCertificate = null;
		location = -99;
		yearInstalled = null;
		disconnecteddate = null;
		testcompany = null;
		productStored = -99;
		regWithStateAgency = false;
		permitInfo = null;
		facilityDesignatedId = null;
		trienialCathodicTestList = null;
		tightnessTestList = null;
		spillControlList = null;
		tankLocation = -99;
		scomments = null;
		fuelsuppliedto = null;
		dobsignoff = null;
		certificateofapproval = null;
		tankTightness = null;
		dispenser = -99;
		leakDetection = -99;
		pipingSecContainment = -99;
		dobfiling = null;

		id = resultset.getInt("storagetankid");
		buildingId = resultset.getInt("buildingid");
		String s = resultset.getString("isdaytank");
		if (UtilityObject.convertBoolean(s))
			isDayTank = true;
		else
			isDayTank = false;
		pbsNumber = resultset.getString("pbsnumber");

		pbsExpirationDate = resultset.getString("pbsexpirationdate");
		pbsExpirationDateNote = resultset.getString("pbsexpirationdatenote");
		fuelType = resultset.getInt("fueltype");
		capacity = resultset.getString("capacity");
		tankType = resultset.getInt("tanktype");
		regionalLocation = resultset.getString("regionalLocation");
		corrosionProtectionType = resultset.getInt("corrosionprotectiontype");
		cathodicInstallationDate = resultset
				.getString("cathodicinstallationdate");
		tankStatus = resultset.getInt("tankstatus");
		pipeType = resultset.getInt("pipetype");
		pipeInternalProtection = resultset.getInt("pipeinternalprot");
		tankExternalProtection = resultset.getInt("tankexternalprotection");
		externalProtection = resultset.getInt("externalprot");
		overfillProtection = resultset.getInt("overfillprot");
		secondaryContainment = resultset.getInt("secondarycontain");

		fireDeptCertificate = resultset.getString("firedeptcertificate");
		location = resultset.getInt("location");
		yearInstalled = resultset.getString("yearinstalled");
		disconnecteddate = resultset.getString("DISCONNECTEDDATE");
		testcompany = resultset.getString("testcompany");
		productStored = resultset.getInt("productstored");
		s = resultset.getString("regwithstateagency");
		if (UtilityObject.convertBoolean(s))
			regWithStateAgency = true;
		else
			regWithStateAgency = false;
		facilityDesignatedId = resultset.getString("facilitydesignatedid");
		tankLocation = resultset.getInt("tanklocation");
		scomments = resultset.getString("SCOMMENTS");
		fuelsuppliedto = resultset.getString("FUELSUPPLIEDTO");
		tankTightness = resultset.getString("TANKTIGHTNESS");
		spillPreventionType = resultset.getInt("spillpreventiontype");
		dispenser = resultset.getInt("dispenser");
		leakDetection = resultset.getInt("leakdetection");
		pipingSecContainment = resultset.getInt("pipingseccontainment");
		dobfiling = resultset.getString("DOBFILING");
		dobApproval = resultset.getString("dobapproval");
		dobsignoff = resultset.getString("DOBSIGNOFF");
		certificateofapproval = resultset.getString("CERTIFICATEOFAPPROVAL");
	}

	public String getDobFiling() {
		return dobfiling;
	}

	public void setDobFiling(String s) {
		dobfiling = s;
	}

	public String getTankTightness() {
		return tankTightness;
	}

	public void setTankTightness(String s) {
		tankTightness = s;
	}

	public String getDobsignoff() {
		return dobsignoff;
	}

	public void setDobsignoff(String s) {
		dobsignoff = s;
	}

	public String getCertificateofapproval() {
		return certificateofapproval;
	}

	public void setCertificateofapproval(String s) {
		certificateofapproval = s;
	}

	public String getScomments() {
		return scomments;
	}

	public void setScomments(String s) {
		scomments = s;
	}

	public String getRegionalLocation() {
		return regionalLocation;
	}

	public void setRegionalLocation(String s) {
		regionalLocation = s;
	}

	public String getPbsExpirationDateNote() {
		return pbsExpirationDateNote;
	}

	public void setPbsExpirationDateNote(String s) {
		pbsExpirationDateNote = s;
	}

	public String getFuelsuppliedto() {
		return fuelsuppliedto;
	}

	public void setFuelsuppliedto(String s) {
		fuelsuppliedto = s;
	}

	public String getFacilityDesignatedId() {
		return facilityDesignatedId;
	}

	public void setFacilityDesignatedId(String s) {
		facilityDesignatedId = s;
	}

	public void setYearInstalled(String s) {
		yearInstalled = s;
	}

	public void settestcompany(String s) {
		testcompany = s;
	}

	public String gettestcompany() {
		return testcompany;
	}

	public int getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(int i) {
		buildingId = i;
	}

	public int getLeakDetection() {
		return leakDetection;
	}

	public void setLeakDetection(int i) {
		leakDetection = i;
	}

	public int getPipingSecContainment() {
		return pipingSecContainment;
	}

	public void setPipingSecContainment(int i) {
		pipingSecContainment = i;
	}

	public int getTankExternalProtection() {
		return tankExternalProtection;
	}

	public void setTankExternalProtection(int i) {
		tankExternalProtection = i;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String s) {
		capacity = s;
	}

	public String getDobApproval() {
		return dobApproval;
	}

	public void setDobApproval(String s) {
		dobApproval = s;
	}

	public int getExternalProtection() {
		return externalProtection;
	}

	public void setExternalProtection(int i) {
		externalProtection = i;
	}

	public String getFireDeptCertificate() {
		return fireDeptCertificate;
	}

	public void setFireDeptCertificate(String s) {
		fireDeptCertificate = s;
	}

	public String getCathodicInstallationDate() {
		return cathodicInstallationDate;
	}

	public void setCathodicInstallationDate(String s) {
		cathodicInstallationDate = s;
	}

	public String getPbsExpirationDate() {
		return pbsExpirationDate;
	}

	public void setPbsExpirationDate(String s) {
		pbsExpirationDate = s;
	}

	public int getFuelType() {
		return fuelType;
	}

	public void setFuelType(int i) {
		fuelType = i;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isDayTank() {
		return isDayTank;
	}

	public void setDayTank(boolean flag) {
		isDayTank = flag;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int i) {
		location = i;
	}

	public int getOverfillProtection() {
		return overfillProtection;
	}

	public void setOverfillProtection(int i) {
		overfillProtection = i;
	}

	public String getPbsNumber() {
		return pbsNumber;
	}

	public void setPbsNumber(String s) {
		pbsNumber = s;
	}

	public List getPermitInfo() {
		if (permitInfo == null)
			try {
				permitInfo = StorageTankEntity.getPermitList(getId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return permitInfo;
	}

	public void setPermitInfo(List list) {
		permitInfo = list;
	}

	public int getPipeInternalProtection() {
		return pipeInternalProtection;
	}

	public void setPipeInternalProtection(int i) {
		pipeInternalProtection = i;
	}

	public int getPipeType() {
		return pipeType;
	}

	public void setPipeType(int i) {
		pipeType = i;
	}

	public int getDispenser() {
		return dispenser;
	}

	public void setDispenser(int i) {
		dispenser = i;
	}

	public int getProductStored() {
		System.out.println((new StringBuilder()).append("product")
				.append(productStored).toString());
		return productStored;
	}

	public void setProductStored(int i) {
		productStored = i;
	}

	public boolean isRegWithStateAgency() {
		return regWithStateAgency;
	}

	public void setRegWithStateAgency(boolean flag) {
		regWithStateAgency = flag;
	}

	public int getSecondaryContainment() {
		return secondaryContainment;
	}

	public void setSecondaryContainment(int i) {
		secondaryContainment = i;
	}

	public int getCorrosionProtectionType() {
		return corrosionProtectionType;
	}

	public void setCorrosionProtectionType(int i) {
		corrosionProtectionType = i;
	}

	public int getSpillPreventionType() {
		return spillPreventionType;
	}

	public void setSpillPreventionType(int i) {
		spillPreventionType = i;
	}

	public int getTankStatus() {
		return tankStatus;
	}

	public void setTankStatus(int i) {
		tankStatus = i;
	}

	public int getTankType() {
		return tankType;
	}

	public void setTankType(int i) {
		tankType = i;
	}

	public String getYearInstalled() {
		return yearInstalled;
	}

	public String getDisconnecteddate() {
		return disconnecteddate;
	}

	public void setDisconnecteddate(String s) {
		disconnecteddate = s;
	}

	public List getSpillControlList() {
		if (spillControlList == null)
			try {
				spillControlList = StorageTankEntity
						.getSpillControlList(getId());
			} catch (TrackingException trackingexception) {
				trackingexception.printStackTrace();
			}
		return spillControlList;
	}

	public void setSpillControlList(List list) {
		spillControlList = list;
	}

	public List getTightnessTestList() {
		if (tightnessTestList == null)
			try {
				tightnessTestList = StorageTankEntity
						.getTankTightnessList(getId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return tightnessTestList;
	}

	public void setTightnessTestList(List list) {
		tightnessTestList = list;
	}

	public List getTrienialCathodicTestList() {
		if (trienialCathodicTestList == null)
			try {
				trienialCathodicTestList = StorageTankEntity
						.getTrienialCathodicProtectionTestList(getId());
			} catch (TrackingException trackingexception) {
				log.error(trackingexception);
			}
		return trienialCathodicTestList;
	}

	public void setTrienialCathodicTestList(List list) {
		trienialCathodicTestList = list;
	}

	public int getTankLocation() {
		return tankLocation;
	}

	public void setTankLocation(int i) {
		tankLocation = i;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id).append("|");
		stringbuffer.append("isDayTank =").append(isDayTank).append("|");
		stringbuffer.append("pbsNumber =").append(pbsNumber).append("|");
		stringbuffer.append("pbsExpirationDate =").append(pbsExpirationDate)
				.append("|");
		stringbuffer.append("pbsExpirationDateNote =")
				.append(pbsExpirationDateNote).append("|");
		stringbuffer.append("fuelType =").append(fuelType).append("|");
		stringbuffer.append("capacity =").append(capacity).append("|");
		stringbuffer.append("tankType =").append(tankType).append("|");
		stringbuffer.append("regionalLocation =").append(regionalLocation)
				.append("|");
		stringbuffer.append("tankLocation =").append(tankLocation).append("|");
		stringbuffer.append("corrosionProtectionType =")
				.append(corrosionProtectionType).append("|");
		stringbuffer.append("cathodicInstallationDate =")
				.append(cathodicInstallationDate).append("|");
		stringbuffer.append("tankStatus =").append(tankStatus).append("|");
		stringbuffer.append("pipeType =").append(pipeType).append("|");
		stringbuffer.append("pipeInternalProtection =")
				.append(pipeInternalProtection).append("|");
		stringbuffer.append("tankExternalProtection =")
				.append(tankExternalProtection).append("|");
		stringbuffer.append("externalProtection =").append(externalProtection)
				.append("|");
		stringbuffer.append("overfillProtection =").append(overfillProtection)
				.append("|");
		stringbuffer.append("spillPreventionType =")
				.append(spillPreventionType).append("|");
		stringbuffer.append("dispenser =").append(dispenser).append("|");
		stringbuffer.append("leakDetection =").append(leakDetection)
				.append("|");
		stringbuffer.append("pipingSecContainment =")
				.append(pipingSecContainment).append("|");
		stringbuffer.append("secondaryContainment =")
				.append(secondaryContainment).append("|");
		stringbuffer.append("dobApproval =").append(dobApproval).append("|");
		stringbuffer.append("certificateofapproval =")
				.append(certificateofapproval).append("|");
		stringbuffer.append("fireDeptCertificate =")
				.append(fireDeptCertificate).append("|");
		stringbuffer.append("location =").append(location).append("|");
		stringbuffer.append("yearInstalled =").append(yearInstalled)
				.append("|");
		stringbuffer.append("disconnecteddate=").append(disconnecteddate)
				.append("|");
		stringbuffer.append("testcompany =").append(testcompany).append("|");
		stringbuffer.append("productStored =").append(productStored)
				.append("|");
		stringbuffer.append("tankTightness =").append(tankTightness)
				.append("|");
		stringbuffer.append("dobfiling =").append(dobfiling).append("|");
		stringbuffer.append("regWithStateAgency =").append(regWithStateAgency);
		return stringbuffer.toString();
	}

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.StorageTankVo.class);
	protected int id;
	protected int buildingId;
	protected boolean isDayTank;
	protected String pbsNumber;
	protected String tankTightness;
	protected String pbsExpirationDate;
	protected String pbsExpirationDateNote;
	protected int fuelType;
	protected String capacity;
	protected int tankType;
	protected int dispenser;
	protected String regionalLocation;
	protected int corrosionProtectionType;
	protected String cathodicInstallationDate;
	protected int tankStatus;
	protected int pipeType;
	protected int pipeInternalProtection;
	protected int externalProtection;
	protected int overfillProtection;
	protected int secondaryContainment;
	protected String dobApproval;
	protected String fireDeptCertificate;
	protected int location;
	protected String yearInstalled;
	protected String disconnecteddate;
	protected String testcompany;
	protected int productStored;
	boolean regWithStateAgency;
	protected List permitInfo;
	protected String facilityDesignatedId;
	protected List trienialCathodicTestList;
	protected List tightnessTestList;
	protected List spillControlList;
	protected int tankLocation;
	protected String fuelsuppliedto;
	protected String scomments;
	protected String dobsignoff;
	protected String certificateofapproval;
	protected int spillPreventionType;
	protected int tankExternalProtection;
	protected int leakDetection;
	protected int pipingSecContainment;
	protected String dobfiling;
}