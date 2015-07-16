package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.eespc.tracking.util.UtilityObject;

public class SpillProtectionVo implements Serializable {

	protected int id;
	protected int tankId;
	protected Date date;
	protected boolean isThereAnySpill;
	protected boolean isCompliant;
	protected String spillNumber;
	protected String date4Display;

	public SpillProtectionVo() {
		id = -99;
		tankId = -99;
		date = null;
		isThereAnySpill = false;
		isCompliant = false;
		spillNumber = null;
		date4Display = null;
	}

	public SpillProtectionVo(ResultSet resultset) throws SQLException {
		id = -99;
		tankId = -99;
		date = null;
		isThereAnySpill = false;
		isCompliant = false;
		spillNumber = null;
		date4Display = null;
		id = resultset.getInt("SPILLCOMPLIANCEID");
		tankId = resultset.getInt("STORAGETANKID");
		date = resultset.getDate("DATE");
		String s = resultset.getString("ANYSPILL");
		if (UtilityObject.convertBoolean(s)) {
			isThereAnySpill = true;
		}
		s = resultset.getString("COMPLIANCE");
		if (UtilityObject.convertBoolean(s)) {
			isCompliant = true;
		}
		spillNumber = resultset.getString("SPILLNUMBER");
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date1) {
		date = date1;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public boolean isCompliant() {
		return isCompliant;
	}

	public void setCompliant(boolean flag) {
		isCompliant = flag;
	}

	public boolean isThereAnySpill() {
		return isThereAnySpill;
	}

	public void setThereAnySpill(boolean flag) {
		isThereAnySpill = flag;
	}

	public String getSpillNumber() {
		return spillNumber;
	}

	public void setSpillNumber(String s) {
		spillNumber = s;
	}

	public int getTankId() {
		return tankId;
	}

	public void setTankId(int i) {
		tankId = i;
	}

	public String getDate4Display() {
		return UtilityObject.convertToString(date);
	}

	public void setDate4Display(String s) {
		date4Display = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("id =").append(id).append("|");
		stringbuffer.append("tankId =").append(tankId).append("|");
		stringbuffer.append("date =").append(date).append("|");
		stringbuffer.append("isThereAnySpill =").append(isThereAnySpill)
				.append("|");
		stringbuffer.append("isCompliant =").append(isCompliant).append("|");
		stringbuffer.append("spillNumber =").append(spillNumber);
		return stringbuffer.toString();
	}
}
