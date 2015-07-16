package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

// Referenced classes of package com.eespc.tracking.bo:
//            AddressVo

public class StatepermitVo implements Serializable {

	public StatepermitVo() {
		id = -99;
		facilityId = -99;
		fstatus = null;
		fexpirationdate = null;
		fjan = null;
		ffeb = null;
		fmar = null;
		fapril = null;
		fmay = null;
		fjune = null;
		fjuly = null;
		faug = null;
		fsep = null;
		foct = null;
		fnov = null;
		fdec = null;
		flastproposal = null;
		fnextproposal = null;
		fcomments = null;

	}

	public StatepermitVo(ResultSet resultset) throws SQLException {
		id = -99;
		facilityId = -99;
		fstatus = null;
		fexpirationdate = null;
		fjan = null;
		ffeb = null;
		fmar = null;
		fapril = null;
		fmay = null;
		fjune = null;
		fjuly = null;
		faug = null;
		fsep = null;
		foct = null;
		fnov = null;
		fdec = null;
		flastproposal = null;
		fnextproposal = null;
		fcomments = null;

		id = resultset.getInt("MASTERID");
		facilityId = resultset.getInt("FACILITYID");
		fstatus = resultset.getString("FSTATUS");
		fexpirationdate = resultset.getString("FEXPIRATIONDATE");
		fjan = resultset.getString("FJAN");
		ffeb = resultset.getString("FFEB");
		fmar = resultset.getString("FMAR");
		fapril = resultset.getString("FAPRIL");
		fmay = resultset.getString("FMAY");
		fjune = resultset.getString("FJUNE");
		fjuly = resultset.getString("FJULY");
		faug = resultset.getString("FAUG");
		fsep = resultset.getString("FSEP");
		foct = resultset.getString("FOCT");
		fnov = resultset.getString("FNOV");
		fdec = resultset.getString("FDEC");
		flastproposal = resultset.getString("FLASTPROPOSAL");
		fnextproposal = resultset.getString("FNEXTPROPOSAL");
		fcomments = resultset.getString("FCOMMENTS");

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

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String s) {
		fstatus = s;
	}

	public String getFexpirationdate() {
		return fexpirationdate;
	}

	public void setFexpirationdate(String s) {
		fexpirationdate = s;
	}

	public String getFjan() {
		return fjan;
	}

	public void setFjan(String s) {
		fjan = s;
	}

	public String getFfeb() {
		return ffeb;
	}

	public void setFfeb(String s) {
		ffeb = s;
	}

	public String getFmar() {
		return fmar;
	}

	public void setFmar(String s) {
		fmar = s;
	}

	public String getFapril() {
		return fapril;
	}

	public void setFapril(String s) {
		fapril = s;
	}

	public String getFmay() {
		return fmay;
	}

	public void setFmay(String s) {
		fmay = s;
	}

	public String getFjune() {
		return fjune;
	}

	public void setFjune(String s) {
		fjune = s;
	}

	public String getFjuly() {
		return fjuly;
	}

	public void setFjuly(String s) {
		fjuly = s;
	}

	public String getFaug() {
		return faug;
	}

	public void setFaug(String s) {
		faug = s;
	}

	public String getFsep() {
		return fsep;
	}

	public void setFsep(String s) {
		fsep = s;
	}

	public String getFoct() {
		return foct;
	}

	public void setFoct(String s) {
		foct = s;
	}

	public String getFnov() {
		return fnov;
	}

	public void setFnov(String s) {
		fnov = s;
	}

	public String getFdec() {
		return fdec;
	}

	public void setFdec(String s) {
		fdec = s;
	}

	public String getFlastproposal() {
		return flastproposal;
	}

	public void setFlastproposal(String s) {
		flastproposal = s;
	}

	public String getFnextproposal() {
		return fnextproposal;
	}

	public void setFnextproposal(String s) {
		fnextproposal = s;
	}

	public String getFcomments() {
		return fcomments;
	}

	public void setFcomments(String s) {
		fcomments = s;
	}

	protected int id;
	protected int facilityId;
	protected String fstatus;
	protected String fexpirationdate;
	protected String fjan;
	protected String ffeb;
	protected String fmar;
	protected String fapril;
	protected String fmay;
	protected String fjune;
	protected String fjuly;
	protected String faug;
	protected String fsep;
	protected String foct;
	protected String fnov;
	protected String fdec;
	protected String flastproposal;
	protected String fnextproposal;
	protected String fcomments;
}