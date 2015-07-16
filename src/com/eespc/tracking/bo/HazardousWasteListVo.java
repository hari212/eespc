package com.eespc.tracking.bo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eespc.tracking.util.UtilityObject;

public class HazardousWasteListVo implements Serializable {

	protected int id;
	protected int hwasteId;
	protected String waste;
	protected String typeOfWaste;
	protected String wasteName;
	protected String wasteUnit;
	protected String wasteVolume;
	protected String wasteDensity;
	protected String wasteQuantity;

	public HazardousWasteListVo() {
		id = -99;
		hwasteId = -99;
		waste = null;
		typeOfWaste = null;
		wasteName = null;
		wasteVolume = null;
		wasteDensity = null;
		wasteUnit = null;
		wasteQuantity = null;
	}

	public HazardousWasteListVo(ResultSet resultset) throws SQLException {
		id = -99;
		hwasteId = -99;
		waste = null;
		typeOfWaste = null;
		wasteName = null;
		wasteVolume = null;
		wasteDensity = null;
		wasteUnit = null;
		wasteQuantity = null;
		id = UtilityObject.getIntFromString(resultset
				.getString("HAZARDWASTEID"));
		hwasteId = UtilityObject.getIntFromString(resultset
				.getString("hwasteid"));
		waste = resultset.getString("waste");
		typeOfWaste = resultset.getString("typeOfWaste");
		wasteName = resultset.getString("wasteName");
		wasteVolume = resultset.getString("wasteVolume");
		wasteDensity = resultset.getString("wasteDensity");
		wasteUnit = resultset.getString("wasteUnit");
		wasteQuantity = resultset.getString("wasteQuantity");
	}

	public int getHwasteId() {
		return hwasteId;
	}

	public void setHwasteId(int i) {
		hwasteId = i;
	}

	public String getWaste() {
		return waste;
	}

	public void setWaste(String s) {
		waste = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		id = i;
	}

	public String getTypeOfWaste() {
		return typeOfWaste;
	}

	public void setTypeOfWaste(String s) {
		typeOfWaste = s;
	}

	public String getWasteName() {
		return wasteName;
	}

	public void setWasteName(String s) {
		wasteName = s;
	}

	public String getWasteVolume() {
		return wasteVolume;
	}

	public void setWasteVolume(String s) {
		wasteVolume = s;
	}

	public String getWasteDensity() {
		return wasteDensity;
	}

	public void setWasteDensity(String s) {
		wasteDensity = s;
	}

	public String getWasteUnit() {
		return wasteUnit;
	}

	public void setWasteUnit(String s) {
		wasteUnit = s;
	}

	public String getWasteQuantity() {
		return wasteQuantity;
	}

	public void setWasteQuantity(String s) {
		wasteQuantity = s;
	}

	public String toString() {
		StringBuffer stringbuffer = new StringBuffer();
		String s = ", ";
		stringbuffer.append("id =").append(id).append(s);
		stringbuffer.append("hwasteId =").append(hwasteId).append(s);
		stringbuffer.append("waste=").append(waste).append(s);
		stringbuffer.append("typeOfWaste=").append(typeOfWaste).append(s);
		stringbuffer.append("wasteName=").append(wasteName).append(s);
		stringbuffer.append("wasteUnit=").append(wasteUnit).append(s);
		stringbuffer.append("wasteVolume=").append(wasteVolume).append(s);
		stringbuffer.append("wasteDensity=").append(wasteDensity).append(s);
		stringbuffer.append("wasteQuantity=").append(wasteQuantity);
		return stringbuffer.toString();
	}
}
