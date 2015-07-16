package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.util.UtilityObject;

public class OtherEvalutionVo implements Serializable {

	public OtherEvalutionVo() {
		id = -99;
		facilityId = -99;
		spcc_req = null;
		stormprevention_req = null;
		epaaudit_req = null;
		accfinding_req = null;
		hazwaste_req = null;
		hazr_req = null;
		tier2_req = null;
		nyccom_req = null;
		dobvio_req = null;
		fd_req = null;
		ecp_req = null;
		dep_req = null;
		anystate_req = null;
		doh_req = null;
		builtinplan_req = null;
		fuelinventory_req = null;
		opacity_req = null;
		refri_req = null;
		risk_req = null;
		other_req = null;
		spcc_avi = null;
		stormprevention_avi = null;
		epaaudit_avi = null;
		accfinding_avi = null;
		hazwaste_avi = null;
		hazr_avi = null;
		tier2_avi = null;
		nyccom_avi = null;
		dobvio_avi = null;
		fd_avi = null;
		ecp_avi = null;
		dep_avi = null;
		anystate_avi = null;
		doh_avi = null;
		builtinplan_avi = null;
		fuelinventory_avi = null;
		opacity_avi = null;
		refri_avi = null;
		risk_avi = null;
		other_avi = null;
		other_inp = null;
		laundry_req = null;
		laundry_avi = null;
		compliancereport_req = null;
		compliancereport_avi = null;
		depofenergy_req = null;
		depofenergy_avi = null;
	}

	public OtherEvalutionVo(ResultSet resultset) throws SQLException {
		id = -99;
		facilityId = -99;
		spcc_req = null;
		stormprevention_req = null;
		epaaudit_req = null;
		accfinding_req = null;
		hazwaste_req = null;
		hazr_req = null;
		tier2_req = null;
		nyccom_req = null;
		dobvio_req = null;
		fd_req = null;
		ecp_req = null;
		dep_req = null;
		anystate_req = null;
		doh_req = null;
		builtinplan_req = null;
		fuelinventory_req = null;
		opacity_req = null;
		refri_req = null;
		risk_req = null;
		other_req = null;
		spcc_avi = null;
		stormprevention_avi = null;
		epaaudit_avi = null;
		accfinding_avi = null;
		hazwaste_avi = null;
		hazr_avi = null;
		tier2_avi = null;
		nyccom_avi = null;
		dobvio_avi = null;
		fd_avi = null;
		ecp_avi = null;
		dep_avi = null;
		anystate_avi = null;
		doh_avi = null;
		builtinplan_avi = null;
		fuelinventory_avi = null;
		opacity_avi = null;
		refri_avi = null;
		risk_avi = null;
		other_avi = null;
		other_inp = null;
		laundry_req = null;
		laundry_avi = null;
		compliancereport_req = null;
		compliancereport_avi = null;
		depofenergy_req = null;
		depofenergy_avi = null;

		id = UtilityObject.getIntFromString(resultset
				.getString("OTHEREVALUTIONID"));
		facilityId = UtilityObject.getIntFromString(resultset
				.getString("FACILITYID"));
		spcc_req = resultset.getString("SPCC_REQ");
		stormprevention_req = resultset.getString("STORMPREVENTION_REQ");
		epaaudit_req = resultset.getString("EPAAUDIT_REQ");
		accfinding_req = resultset.getString("ACCFINDING_REQ");
		hazwaste_req = resultset.getString("HAZWASTE_REQ");
		hazr_req = resultset.getString("HAZR_REQ");
		tier2_req = resultset.getString("TIER2_REQ");
		nyccom_req = resultset.getString("NYCCOM_REQ");
		dobvio_req = resultset.getString("DOBVIO_REQ");
		fd_req = resultset.getString("FD_REQ");
		ecp_req = resultset.getString("ECP_REQ");
		dep_req = resultset.getString("DEP_REQ");
		anystate_req = resultset.getString("ANYSTATE_REQ");
		doh_req = resultset.getString("DOH_REQ");
		builtinplan_req = resultset.getString("BUILTINPLAN_REQ");
		fuelinventory_req = resultset.getString("FUELINVENTORY_REQ");
		opacity_req = resultset.getString("OPACITY_REQ");
		refri_req = resultset.getString("REFRI_REQ");
		risk_req = resultset.getString("RISK_REQ");
		other_req = resultset.getString("OTHER_REQ");
		spcc_avi = resultset.getString("SPCC_AVI");
		stormprevention_avi = resultset.getString("STORMPREVENTION_AVI");
		epaaudit_avi = resultset.getString("EPAAUDIT_AVI");
		accfinding_avi = resultset.getString("ACCFINDING_AVI");
		hazwaste_avi = resultset.getString("HAZWASTE_AVI");
		hazr_avi = resultset.getString("HAZR_AVI");
		tier2_avi = resultset.getString("TIER2_AVI");
		nyccom_avi = resultset.getString("NYCCOM_AVI");
		dobvio_avi = resultset.getString("DOBVIO_AVI");
		fd_avi = resultset.getString("FD_AVI");
		ecp_avi = resultset.getString("ECP_AVI");
		dep_avi = resultset.getString("DEP_AVI");
		anystate_avi = resultset.getString("ANYSTATE_AVI");
		doh_avi = resultset.getString("DOH_AVI");
		builtinplan_avi = resultset.getString("BUILTINPLAN_AVI");
		fuelinventory_avi = resultset.getString("FUELINVENTORY_AVI");
		opacity_avi = resultset.getString("OPACITY_AVI");
		refri_avi = resultset.getString("REFRI_AVI");
		risk_avi = resultset.getString("RISK_AVI");
		other_avi = resultset.getString("OTHER_AVI");
		other_inp = resultset.getString("OTHER_INP");
		laundry_req = resultset.getString("LAUNDRY_REQ");
		laundry_avi = resultset.getString("LAUNDRY_AVI");
		compliancereport_req = resultset.getString("COMPLIANCEREPORT_REQ");
		compliancereport_avi = resultset.getString("COMPLIANCEREPORT_AVI");
		depofenergy_req = resultset.getString("DEPOFENERGY_REQ");
		depofenergy_avi = resultset.getString("DEPOFENERGY_AVI");
	}

	public String getDepofenergy_req() {
		return depofenergy_req;
	}

	public void setDepofenergy_req(String s) {
		depofenergy_req = s;
	}

	public String getDepofenergy_avi() {
		return depofenergy_avi;
	}

	public void setDepofenergy_avi(String s) {
		depofenergy_avi = s;
	}

	public String getCompliancereport_req() {
		return compliancereport_req;
	}

	public void setCompliancereport_req(String s) {
		compliancereport_req = s;
	}

	public String getCompliancereport_avi() {
		return compliancereport_avi;
	}

	public void setCompliancereport_avi(String s) {
		compliancereport_avi = s;
	}

	public String getLaundry_req() {
		return laundry_req;
	}

	public void setLaundry_req(String s) {
		laundry_req = s;
	}

	public String getLaundry_avi() {
		return laundry_avi;
	}

	public void setLaundry_avi(String s) {
		laundry_avi = s;
	}

	public String getSpcc_req() {
		return spcc_req;
	}

	public void setSpcc_req(String s) {
		spcc_req = s;
	}

	public String getStormprevention_req() {
		return stormprevention_req;
	}

	public void setStormprevention_req(String s) {
		stormprevention_req = s;
	}

	public String getEpaaudit_req() {
		return epaaudit_req;
	}

	public void setEpaaudit_req(String s) {
		epaaudit_req = s;
	}

	public String getAccfinding_req() {
		return accfinding_req;
	}

	public void setAccfinding_req(String s) {
		accfinding_req = s;
	}

	public String getHazwaste_req() {
		return hazwaste_req;
	}

	public void setHazwaste_req(String s) {
		hazwaste_req = s;
	}

	public String getHazr_req() {
		return hazr_req;
	}

	public void setHazr_req(String s) {
		hazr_req = s;
	}

	public String getTier2_req() {
		return tier2_req;
	}

	public void setTier2_req(String s) {
		tier2_req = s;
	}

	public String getNyccom_req() {
		return nyccom_req;
	}

	public void setNyccom_req(String s) {
		nyccom_req = s;
	}

	public String getDobvio_req() {
		return dobvio_req;
	}

	public void setDobvio_req(String s) {
		dobvio_req = s;
	}

	public String getFd_req() {
		return fd_req;
	}

	public void setFd_req(String s) {
		fd_req = s;
	}

	public String getEcp_req() {
		return ecp_req;
	}

	public void setEcp_req(String s) {
		ecp_req = s;
	}

	public String getDep_req() {
		return dep_req;
	}

	public void setDep_req(String s) {
		dep_req = s;
	}

	public String getAnystate_req() {
		return anystate_req;
	}

	public void setAnystate_req(String s) {
		anystate_req = s;
	}

	public String getDoh_req() {
		return doh_req;
	}

	public void setDoh_req(String s) {
		doh_req = s;
	}

	public String getBuiltinplan_req() {
		return builtinplan_req;
	}

	public void setBuiltinplan_req(String s) {
		builtinplan_req = s;
	}

	public String getFuelinventory_req() {
		return fuelinventory_req;
	}

	public void setFuelinventory_req(String s) {
		fuelinventory_req = s;
	}

	public String getOpacity_req() {
		return opacity_req;
	}

	public void setOpacity_req(String s) {
		opacity_req = s;
	}

	public String getRefri_req() {
		return refri_req;
	}

	public void setRefri_req(String s) {
		refri_req = s;
	}

	public String getRisk_req() {
		return risk_req;
	}

	public void setRisk_req(String s) {
		risk_req = s;
	}

	public String getOther_req() {
		return other_req;
	}

	public void setOther_req(String s) {
		other_req = s;
	}

	public String getSpcc_avi() {
		return spcc_avi;
	}

	public void setSpcc_avi(String s) {
		spcc_avi = s;
	}

	public String getStormprevention_avi() {
		return stormprevention_avi;
	}

	public void setStormprevention_avi(String s) {
		stormprevention_avi = s;
	}

	public String getEpaaudit_avi() {
		return epaaudit_avi;
	}

	public void setEpaaudit_avi(String s) {
		epaaudit_avi = s;
	}

	public String getAccfinding_avi() {
		return accfinding_avi;
	}

	public void setAccfinding_avi(String s) {
		accfinding_avi = s;
	}

	public String getHazwaste_avi() {
		return hazwaste_avi;
	}

	public void setHazwaste_avi(String s) {
		hazwaste_avi = s;
	}

	public String getHazr_avi() {
		return hazr_avi;
	}

	public void setHazr_avi(String s) {
		hazr_avi = s;
	}

	public String getTier2_avi() {
		return tier2_avi;
	}

	public void setTier2_avi(String s) {
		tier2_avi = s;
	}

	public String getNyccom_avi() {
		return nyccom_avi;
	}

	public void setNyccom_avi(String s) {
		nyccom_avi = s;
	}

	public String getDobvio_avi() {
		return dobvio_avi;
	}

	public void setDobvio_avi(String s) {
		dobvio_avi = s;
	}

	public String getFd_avi() {
		return fd_avi;
	}

	public void setFd_avi(String s) {
		fd_avi = s;
	}

	public String getEcp_avi() {
		return ecp_avi;
	}

	public void setEcp_avi(String s) {
		ecp_avi = s;
	}

	public String getDep_avi() {
		return dep_avi;
	}

	public void setDep_avi(String s) {
		dep_avi = s;
	}

	public String getAnystate_avi() {
		return anystate_avi;
	}

	public void setAnystate_avi(String s) {
		anystate_avi = s;
	}

	public String getDoh_avi() {
		return doh_avi;
	}

	public void setDoh_avi(String s) {
		doh_avi = s;
	}

	public String getBuiltinplan_avi() {
		return builtinplan_avi;
	}

	public void setBuiltinplan_avi(String s) {
		builtinplan_avi = s;
	}

	public String getFuelinventory_avi() {
		return fuelinventory_avi;
	}

	public void setFuelinventory_avi(String s) {
		fuelinventory_avi = s;
	}

	public String getOpacity_avi() {
		return opacity_avi;
	}

	public void setOpacity_avi(String s) {
		opacity_avi = s;
	}

	public String getRefri_avi() {
		return refri_avi;
	}

	public void setRefri_avi(String s) {
		refri_avi = s;
	}

	public String getRisk_avi() {
		return risk_avi;
	}

	public void setRisk_avi(String s) {
		risk_avi = s;
	}

	public String getOther_avi() {
		return other_avi;
	}

	public void setOther_avi(String s) {
		other_avi = s;
	}

	public String getOther_inp() {
		return other_inp;
	}

	public void setOther_inp(String s) {
		other_inp = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public int getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(int i) {
		facilityId = i;
	}

	protected int id;
	protected int facilityId;
	protected String spcc_req;
	protected String stormprevention_req;
	protected String epaaudit_req;
	protected String accfinding_req;
	protected String hazwaste_req;
	protected String hazr_req;
	protected String tier2_req;
	protected String nyccom_req;
	protected String dobvio_req;
	protected String fd_req;
	protected String ecp_req;
	protected String dep_req;
	protected String anystate_req;
	protected String doh_req;
	protected String builtinplan_req;
	protected String fuelinventory_req;
	protected String opacity_req;
	protected String refri_req;
	protected String risk_req;
	protected String other_req;
	protected String spcc_avi;
	protected String stormprevention_avi;
	protected String epaaudit_avi;
	protected String accfinding_avi;
	protected String hazwaste_avi;
	protected String hazr_avi;
	protected String tier2_avi;
	protected String nyccom_avi;
	protected String dobvio_avi;
	protected String fd_avi;
	protected String ecp_avi;
	protected String dep_avi;
	protected String anystate_avi;
	protected String doh_avi;
	protected String builtinplan_avi;
	protected String fuelinventory_avi;
	protected String opacity_avi;
	protected String refri_avi;
	protected String risk_avi;
	protected String other_avi;
	protected String other_inp;
	protected String laundry_req;
	protected String laundry_avi;
	protected String compliancereport_req;
	protected String compliancereport_avi;
	protected String depofenergy_req;
	protected String depofenergy_avi;

	private static Log log = LogFactory
			.getLog(com.eespc.tracking.bo.OtherEvalutionVo.class);

}