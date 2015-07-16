package com.eespc.tracking.bo.myenum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eespc.tracking.bo.FacilityVo;

public class ReportExhibitsEnum implements Serializable {

	private static String dep = "";
	private static String dob = "";
	private static String dec = "";
	private final int code;
	private final String name;
	private final String reportFileName;
	private final boolean toUseConnection;
	private static final List list = new ArrayList();
	private static final boolean USE_CONNECTION = true;
	private static final boolean DONT_USE_CONNECTION = false;
	public static final ReportExhibitsEnum EXHIBIT_1 = new ReportExhibitsEnum(
			1, " REPORT 1: LIST OF BUILDINGS", "exhibit1", true, false);
	public static final ReportExhibitsEnum EXHIBIT_2 = new ReportExhibitsEnum(
			2, " REPORT 2: LIST OF EQUIPMENT ASSOCIATED WITH EACH BUILDING",
			"exhibit2", true, false);
	public static final ReportExhibitsEnum EXHIBIT_3 = new ReportExhibitsEnum(
			3, " REPORT 3: LIST OF BOILERS AND DEP/DOB PERMIT STATUS",
			"exhibit3", true, false);
	public static final ReportExhibitsEnum EXHIBIT_4 = new ReportExhibitsEnum(
			4, " REPORT 4: LIST OF BOILERS AND DOB ANNUAL INSPECTION STATUS",
			"exhibit4", true, false);
	public static final ReportExhibitsEnum EXHIBIT_5 = new ReportExhibitsEnum(
			5,
			" REPORT 5: LIST OF BOILERS, ANNUAL TUNE-UP AND STACK TEST REPORTING SUMMARY",
			"exhibit5", true, false);
	public static final ReportExhibitsEnum EXHIBIT_6 = new ReportExhibitsEnum(
			6,
			" REPORT 6: LIST OF EMERGENCY GENERATORS AND AGENCY PERMIT STATUS",
			"exhibit6", true, false);
	public static final ReportExhibitsEnum EXHIBIT_7 = new ReportExhibitsEnum(
			7,
			" REPORT 7: LIST OF PETROLEUM BULK STORAGE TANKS AND DEC REGISTRATION STATUS",
			"exhibit7", true, false);
	public static final ReportExhibitsEnum EXHIBIT_8 = new ReportExhibitsEnum(
			8,
			" REPORT 8: LIST OF ETHYLENE OXIDE STERILIZER SYSTEM AND PERMIT STATUS",
			"exhibit8", true, false);
	public static final ReportExhibitsEnum EXHIBIT_9 = new ReportExhibitsEnum(
			9,
			" REPORT 9: LIST OF CHILLERS, AGENCY APPROVALS AND INSPECTION REPORTS",
			"exhibit9", true, false);
	public static final ReportExhibitsEnum EXHIBIT_10 = new ReportExhibitsEnum(
			10, " REPORT 10: LIST OF ELEVATORS AND DOB INSPECTION STATUS",
			"exhibit10", true, false);
	public static final ReportExhibitsEnum EXHIBIT_11 = new ReportExhibitsEnum(
			11, " REPORT 11: LIST OF RPZS AND AGENCY PERMIT STATUS",
			"exhibit11", true, false);
	public static final ReportExhibitsEnum EXHIBIT_12 = new ReportExhibitsEnum(
			12,
			" REPORT 12: LIST OF COGEN SYSTEMS AND THEIR DESIGN AND OPERATING PARAMETERS",
			"exhibit12", true, false);
	public static final ReportExhibitsEnum EXHIBIT_13 = new ReportExhibitsEnum(
			13, " REPORT 13: LIST OF COGEN SYSTEMS AND AGENCY PERMIT STATUS",
			"exhibit13", true, false);
	public static final ReportExhibitsEnum EXHIBIT_14 = new ReportExhibitsEnum(
			14, " REPORT 14: LIST OF PAINT SPRAY BOOTHS OR PAINT SHOPS",
			"exhibit14", true, false);
	public static final ReportExhibitsEnum EXHIBIT_15 = new ReportExhibitsEnum(
			15, " REPORT 15: LIST OF FUMEHOODS AND THEIR COMPLIANCE STATUS",
			"exhibit15", true, false);
	public static final ReportExhibitsEnum EXHIBIT_16 = new ReportExhibitsEnum(
			16,
			" REPORT 16: LIST OF BRIDGES AND TUNNELS AND AGENCY PERMIT STATUS",
			"exhibit16", true, false);
	public static final ReportExhibitsEnum EXHIBIT_17 = new ReportExhibitsEnum(
			17,
			" REPORT 17: LIST OF INCINERATOR/CREMATORIES AND THEIR COMPLIANCE STATUS",
			"exhibit17", true, false);
	public static final ReportExhibitsEnum EXHIBIT_18 = new ReportExhibitsEnum(
			18,
			" REPORT 18: LIST OF HYDRAULIC ELEVATOR TANKS AND COMPLIANCE STATUS",
			"exhibit18", true, false);
	public static final ReportExhibitsEnum EXHIBIT_19 = new ReportExhibitsEnum(
			19,
			" REPORT 19: LIST OF BULK OXYGEN STORAGE TANKS AND COMPLIANCE STATUS",
			"exhibit19", true, false);
	public static final ReportExhibitsEnum EXHIBIT_20 = new ReportExhibitsEnum(
			20, " REPORT 20: VOC COMPLIANCE EVALUATION OF PRINTING PRESS",
			"exhibit20", true, false);
	public static final ReportExhibitsEnum EXHIBIT_21 = new ReportExhibitsEnum(
			21, " REPORT 21: LIST OF LANDFILLS AND COMPLIANCE STATUS",
			"exhibit21", true, false);
	public static final ReportExhibitsEnum EXHIBIT_22 = new ReportExhibitsEnum(
			22, " REPORT 22: COMPLIANCE EVALUATION OF 'MISCELLANEOUS' SOURCES",
			"exhibit22", true, false);
	public static final ReportExhibitsEnum EXHIBIT_23 = new ReportExhibitsEnum(
			23,
			" REPORT 23: LIST OF AIRCONDITIONING UNIT AND COMPLIANCE STATUS",
			"exhibit23", true, false);
	public static final ReportExhibitsEnum EXHIBIT_24 = new ReportExhibitsEnum(
			24, " REPORT 24: LIST OF FIRE ALARM AND COMPLIANCE STATUS",
			"exhibit24", true, false);
	public static final ReportExhibitsEnum EXHIBIT_25 = new ReportExhibitsEnum(
			25,
			" REPORT 25: SUMMARY OF 12-MONTH ROLLING CONSUMPTION HISTORY FOR BOILER",
			"exhibit25", true, false);
	public static final ReportExhibitsEnum EXHIBIT_26 = new ReportExhibitsEnum(
			26,
			" REPORT 26: SUMMARY OF 12-MONTH ROLLING CONSUMPTION HISTORY FOR GENERATOR",
			"exhibit26", true, false);
	public static final ReportExhibitsEnum EXHIBIT_27 = new ReportExhibitsEnum(
			27,
			" REPORT 27: SUMMARY OF 12-MONTH ROLLING CONSUMPTION HISTORY FOR CO GEN ENGINE",
			"exhibit27", true, false);
	public static final ReportExhibitsEnum EXHIBIT_28 = new ReportExhibitsEnum(
			28, " REPORT 28: OTHER REGULATORY COMPLIANCE EVALUATIONS  TASKS",
			"exhibit28", true, false);
	public static final ReportExhibitsEnum EXHIBIT_29 = new ReportExhibitsEnum(
			29,
			" REPORT 29: LIST OF EQUIPMENT, AGENCY REQUIREMENTS AND OVERALL COMPLIANCE REQUIREMENTS",
			"exhibit29", true, false);
	public static final ReportExhibitsEnum EXHIBIT_30 = new ReportExhibitsEnum(
			30, " REPORT 30: LIST OF DISCONNECTED SOURCES", "exhibit30", true,
			false);
	public static final ReportExhibitsEnum EXHIBIT_31 = new ReportExhibitsEnum(
			31, " REPORT 31: MASTER REPORT", "exhibit31", true, false);
	public static final ReportExhibitsEnum EXHIBIT_32 = new ReportExhibitsEnum(
			32, " REPORT 32: AGENCY INSPECTION DOCUMENTS (AID)", "exhibit32",
			true, false);
	public static final ReportExhibitsEnum EXHIBIT_33 = new ReportExhibitsEnum(
			33,
			" REPORT 33: LIST OF STAFF AND THEIR FIRE DEPARTMENT (FD) CERTIFICATE OF FITNESS (C OF F) STATUS",
			"exhibit33", true, false);
	public static final ReportExhibitsEnum EXHIBIT_34 = new ReportExhibitsEnum(
			34,
			" REPORT 34: HAZARDOUS WASTE (HW) GENERATION AND DISPOSAL SUMMARY",
			"exhibit34", true, false);
	public static final ReportExhibitsEnum EXHIBIT_35 = new ReportExhibitsEnum(
			35, " REPORT 35: LIST OF VIOLATION AND FINAL COMPLIANCE STATUS",
			"exhibit35", true, false);
	public static final ReportExhibitsEnum EXHIBIT_36 = new ReportExhibitsEnum(
			36, " REPORT 36: LIST OF PRINTING PRESS AND AGENCY PERMIT STATUS",
			"exhibit36", true, false);
	public static final ReportExhibitsEnum EXHIBIT_37 = new ReportExhibitsEnum(
			37,
			" REPORT 37: LIST OF PLACE OF PUBLIC ASSEMBLY AND AGENCY APPROVALS",
			"exhibit37", true, false);

	private ReportExhibitsEnum(int i, String s, String s1, boolean flag,
			boolean flag1) {
		code = i;
		name = s;
		toUseConnection = flag1;
		reportFileName = s1;
		list.add(this);
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getReportFileName() {
		return reportFileName;
	}

	public boolean isToUseConnection() {
		return toUseConnection;
	}

	public static ReportExhibitsEnum get(int i) {
		int j = size();
		for (int k = 0; k < j; k++) {
			ReportExhibitsEnum reportexhibitsenum = (ReportExhibitsEnum) list
					.get(k);
			if (reportexhibitsenum.getCode() == i) {
				return reportexhibitsenum;
			}
		}

		return null;
	}

	public static ReportExhibitsEnum parse(String s) {
		if (s == null) {
			return null;
		}
		int i = size();
		for (int j = 0; j < i; j++) {
			ReportExhibitsEnum reportexhibitsenum = (ReportExhibitsEnum) list
					.get(j);
			String s1 = reportexhibitsenum.getName();
			String as[] = s1.split(":");
			if (as[0].equals(s)) {
				return reportexhibitsenum;
			}
		}

		return null;
	}

	public static int size() {
		return list.size();
	}

	public String toString() {
		return getName();
	}

	public static String getExhibits(FacilityVo facilityvo) throws Exception {
		int i = facilityvo.getBorough();
		if (i == 1) {
			dep = "DEP/DOH";
			dob = "DOB/TOWN/CITY";
			dec = "STATE/DOH";
		} else {
			dep = "DEP";
			dob = "DOB";
			dec = "NYCDEC";
		}
		com.eespc.tracking.bo.AddressVo addressvo = facilityvo
				.getFacilityAddress();
		if (addressvo == null) {
			throw new Exception(
					(new StringBuilder())
							.append("Unable to get the address information for facilityid=")
							.append(facilityvo.getId()).toString());
		}
		StringBuffer stringbuffer = new StringBuffer("<exhibits>");
		int j = size();
		for (int k = 0; k < j; k++) {
			ReportExhibitsEnum reportexhibitsenum = (ReportExhibitsEnum) list
					.get(k);
			stringbuffer
					.append("<exhibit id=\"")
					.append(reportexhibitsenum.getCode())
					.append("\">")
					.append(reportexhibitsenum.getName().replace("DEP", dep)
							.replace("DOB", dob).replace("DEC", dec))
					.append("</exhibit>");
		}

		stringbuffer.append("</exhibits>");
		return stringbuffer.toString();
	}

}
