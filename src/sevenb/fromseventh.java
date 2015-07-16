package sevenb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.eespc.tracking.bo.HwasteVo;
import com.eespc.tracking.bo.myenum.CofF;
import com.eespc.tracking.bo.myenum.FireAlarmType;
import com.eespc.tracking.bo.myenum.FurnaceOilTypeEnum;
import com.eespc.tracking.bo.myenum.ProductStorageEnum;
import com.eespc.tracking.bo.myenum.TankLocationEnum;
import com.eespc.tracking.entity.HwasteEntity;
import com.eespc.tracking.entity.LsfEntity;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class fromseventh {

	public fromseventh() {

	}

	public static String getpbs(String s) {
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT a.pbsnumber FROM storagetank a,building d,facility f WHERE d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
			preparedstatement.setString(1, String.valueOf(s));
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = resultset.getString(1);
			}
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("EXCEPTION : ")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}

		return s1;
	}

	public static List getNycdobStatusReport7(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select g.corrosionprotectiontype,g.TANKTIGHTNESS,g.tanklocation,g.tanktype,g.FIREDEPTCERTIFICATE,g.PBSNUMBER,g.DOBAPPROVAL,g.dobsignoff,g.DOBFILING,g.CERTIFICATEOFAPPROVAL,g.STORAGETANKID,g.FACILITYDESIGNATEDID,g.yearinstalled,g.CAPACITY,g.FUELTYPE,g.productstored,g.Location,g.FUELSUPPLIEDTO,b.buildingname,t.TESTDATE,t.NEXTTESTDATE,c.LASTTESTDATE,c.NEXTTESTDUEDATE,g.PBSEXPIRATIONDATE from (storagetank g, building b) left join tanktightnessinfo t on t.STORAGETANKID=g.STORAGETANKID left join trienialcathodicinfo c on c.STORAGETANKID=g.STORAGETANKID left join storagepermitinfo p on p.STORAGETANKID=g.STORAGETANKID and p.PERMITNUMBER='dummy4location' WHERE (g.TANKSTATUS='1' or g.TANKSTATUS='5') and g.buildingid=B.buildingid and B.facilityid=? order by g.PBSNUMBER desc");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			String tempTankId;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempTankId, hashtable)) {
				hashtable = new Hashtable();
				tempTankId = resultset.getString("STORAGETANKID");
				hashtable.put("Tankid", tempTankId);
				hashtable.put(
						"facilitydesignatedid",
						checkNullAndFill(
								resultset.getString("facilitydesignatedid"),
								"-"));
				hashtable.put(
						"yearinstalled",
						checkNullAndFill(resultset.getString("yearinstalled"),
								"-"));
				hashtable.put("tankstored",
						checkNullAndFill(resultset.getString("FUELTYPE"), "-"));
				hashtable.put(
						"boilerid",
						checkNullAndFill(resultset.getString("FUELSUPPLIEDTO"),
								"-"));
				hashtable
						.put("PBSNUMBER",
								checkNullAndFill(
										resultset.getString("PBSNUMBER"), "-"));
				hashtable
						.put("FIREDEPTCERTIFICATE",
								checkNullAndFill(resultset
										.getString("FIREDEPTCERTIFICATE"), "-"));

				// ********************Dob Filing
				// Start*************************//

				String dobfiling = UtilityObject.checkNullAndFill(
						resultset.getString("DOBFILING"), "-");
				String dobnumber = resultset.getString("DOBAPPROVAL");
				String dobsignoff = resultset.getString("DOBSIGNOFF");
				String certofapproval = resultset
						.getString("CERTIFICATEOFAPPROVAL");
				String dobcompliancestatus = "";

				if (dobfiling.equalsIgnoreCase("Y")) {
					hashtable.put("DOBAPPROVAL", UtilityObject
							.checkNullAndFill(dobnumber, "No Filing"));
					hashtable.put("DOBSIGNOFF",
							UtilityObject.checkNullAndFill(dobsignoff, "-"));
					hashtable
							.put("CERTIFICATEOFAPPROVAL", UtilityObject
									.checkNullAndFill(certofapproval, "-"));
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("DOBAPPROVAL", "No Filing");
					hashtable.put("DOBSIGNOFF", "N/A");
					hashtable.put("CERTIFICATEOFAPPROVAL", "N/A");
				} else if (dobfiling.equalsIgnoreCase("N/A")) {
					hashtable.put("DOBAPPROVAL", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
					hashtable.put("CERTIFICATEOFAPPROVAL", "N/A");
				} else {
					hashtable.put("DOBAPPROVAL", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
					hashtable.put("CERTIFICATEOFAPPROVAL", "N/A");
				}

				if (dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("dobcompliancestatus", "TBD");
					dobcompliancestatus = "TBD";
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (certofapproval.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!dobnumber.equals("No Filing")
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dobnumber)
						&& dobsignoff.equalsIgnoreCase("Y")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dobnumber)
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else {
					hashtable.put("dobcompliancestatus", "Compliance");
					dobcompliancestatus = "Compliance";
				}

				// ********************Dob Filing End*************************//

				String exp = resultset.getString("PBSEXPIRATIONDATE");
				hashtable.put("EXPIRATIONDATE", checkNullAndFilldate(exp, "-"));
				int j_1 = 0;
				java.util.Date today = new java.util.Date();
				java.util.Date expi = null;
				if (exp == null) {
					j_1 = 0;
				} else {
					expi = UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
				}
				if (expi == null) {
					j_1 = 0;
				} else if (expi != null) {
					j_1 = today.compareTo(expi);
					if (j_1 > 0) {
						j_1 = 0;
					} else {
						j_1 = 1;
					}
				} else {
					j_1 = 0;
				}

				hashtable.put("capacity",
						checkNullAndFill(resultset.getString("capacity"), "-"));
				ProductStorageEnum productstorageenum = ProductStorageEnum
						.get(Integer.parseInt(checkNullAndFill(
								resultset.getString("productstored"), "-99")));
				if (productstorageenum != null) {
					hashtable.put("tankstored", productstorageenum.getName());
				} else {
					hashtable.put("tankstored", "-");
				}
				String s = resultset.getString("tanklocation");
				if (s.equals("-99") || s.equals("0") || s.equals("-1")) {
					hashtable.put("tanktype", "-");
				} else {
					TankLocationEnum tanklocationenum = TankLocationEnum
							.get(Integer.parseInt(s));
					hashtable
							.put("tanktype",
									checkNullAndFill(
											tanklocationenum.getName(), "N/A"));
				}

				String testdate = resultset.getString("testdate");
				String nextdate = resultset.getString("nexttestdate");
				int k_1 = 0;
				java.util.Date todayk = new java.util.Date();
				java.util.Date expik = null;
				if (nextdate == null) {
					k_1 = 0;
				} else {
					expik = UtilityObject.convert_YyyyMmDd2MmDdYyyy(nextdate);
				}
				if (expik == null) {
					k_1 = 0;
				} else if (expik != null) {
					k_1 = todayk.compareTo(expik);
					if (k_1 > 0) {
						k_1 = 0;
					} else {
						k_1 = 1;
					}
				} else {
					k_1 = 0;
				}

				String tankTightness = UtilityObject.checkNullAndFill(
						resultset.getString("TANKTIGHTNESS"), "-");

				if (tankTightness.equalsIgnoreCase("Y")) {
					hashtable.put("TESTDATE",
							checkNullAndFilldate(testdate, "-"));
					hashtable.put("NEXTTESTDATE",
							checkNullAndFilldate(nextdate, "-"));
				} else {
					hashtable.put("TESTDATE", "N/A");
					hashtable.put("NEXTTESTDATE", "N/A");
				}

				String lasttest = resultset.getString("lasttestdate");
				String testduedate = resultset.getString("nexttestduedate");
				int m_1 = 0;
				java.util.Date todaym = new java.util.Date();
				java.util.Date expim = null;
				if (testduedate == null) {
					m_1 = 0;
				} else {
					expim = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(testduedate);
				}
				if (expim == null) {
					m_1 = 0;
				} else if (expim != null) {
					m_1 = todaym.compareTo(expim);
					if (m_1 > 0) {
						m_1 = 0;
					} else {
						m_1 = 1;
					}
				} else {
					m_1 = 0;
				}

				String s1 = resultset.getString("corrosionprotectiontype");
				if (s1.equals("-99") || s1.equals("0") || s1.equals("-1")) {
					hashtable.put("LASTTESTDATE", "-");
					hashtable.put("NEXTTESTDUEDATE", "-");
				} else if (s1.equals("4")) {
					hashtable.put("LASTTESTDATE", "N/A");
					hashtable.put("NEXTTESTDUEDATE", "N/A");
				} else {
					hashtable.put("LASTTESTDATE",
							checkNullAndFilldate(lasttest, "-"));
					hashtable.put("NEXTTESTDUEDATE",
							checkNullAndFilldate(testduedate, "-"));
				}
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("buildingname"),
								"-"));

				if (tankTightness.equals("-")
						&& !UtilityObject.isNotEmpty(nextdate)) {
					hashtable.put("compliancestatus", "TBD");
				} else if (tankTightness.equals("Y")
						&& !UtilityObject.isNotEmpty(nextdate)) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (tankTightness.equals("Y") && k_1 == 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (!s1.equals("4")
						&& !UtilityObject.isNotEmpty(testduedate)) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (!s1.equals("4") && m_1 == 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (j_1 == 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else {
					hashtable.put("compliancestatus", "Compliance");
				}

			}

		} catch (Exception exception) {
			System.out.println(exception);
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static String getboiler(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select facilitydesignatedid from  boiler where FUELFROM=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = (new StringBuilder()).append(s1).append(" ")
						.append(resultset.getString("facilitydesignatedid"))
						.toString();
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static String getelevator(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select FACILITYDESIGNATEDID from  elevators where HYDRAULICTANK=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = (new StringBuilder()).append(s1).append(" ")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.toString();
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static String getHydraliccapacity(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select sum(a.CAPACITY) from (hydraulicelevatorothertank a,building b) where a.CA"
							+ "PACITY>54  and a.buildingid=b.buildingid and b.facilityid=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = resultset.getString(1);
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static String getPetrocapacity(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select sum(a.CAPACITY) from (storagetank a,building b) where a.CA"
							+ "PACITY>54  and a.buildingid=b.buildingid and b.facilityid=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = resultset.getString(1);
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static String getaboveGround(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select sum(a.CAPACITY) from (storagetank a,building b) where a.CAPACITY>54 and ("
							+ "a.TANKTYPE='1' OR TANKTYPE='2' or a.TANKTYPE='3' OR TANKTYPE='4') and a.building"
							+ "id=b.buildingid and b.facilityid=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = resultset.getString(1);
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static String getunderGround(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select sum(a.CAPACITY) from (storagetank a,building b) where (a.TANKTYPE='5' OR "
							+ "TANKTYPE='6') and a.buildingid=b.buildingid and b.facilityid=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = resultset.getString(1);
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static List getNycdobStatusReport15(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT a.DOBSIGNOFF,a.chemicalname1,a.chemicalname2,a.chemicalname3,a.chemicalna"
							+ "me4,a.chemicalname5,a.fumehoodid,a.facilitydesignatedid,c.buildingname,a.make,a."
							+ "model,a.voc,a.HROFOPERATION,a.FUMEHOODHOURSOFOPERATION1,a.INCLUDEDINDECPERMIT,a."
							+ "dob,a.FUMEHOODDEPNUMBER,a.DEPPERMITSTATUS,a.DOBPERMITSTATUS,a.ANNUALPERMITSTATUS,a.LASTINSPECTIONDATE,a.NEXTINSPECTIONDATE,b.expirationdate FROM (fumehoods a,building c) left join"
							+ " fumehoodpermitinfo b on b.FUMEHOODID=a.FUMEHOODID and b.DEPID='2' where (a.STAT"
							+ "US='1' or a.STATUS='5') and a.buildingid=c.buildingid and c.facilityid=?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("FUMEHOODID");
				hashtable.put("FUMEHOODID", checkNullAndFill(tempId, "-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"LOCATION",
						checkNullAndFill(resultset.getString("buildingname"),
								"-"));
				hashtable.put("MAKE",
						checkNullAndFill(resultset.getString("make"), "-"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("model"), "-"));
				hashtable.put("VOC",
						checkNullAndFill(resultset.getString("voc"), "-"));
				hashtable.put(
						"HROFOPERATION",
						checkNullAndFill(resultset.getString("HROFOPERATION"),
								"-"));
				hashtable
						.put("INCLUDEDINDECPERMIT",
								checkNullAndFill(resultset
										.getString("INCLUDEDINDECPERMIT"), "-"));
				String s1 = checkNullAndFillcoma(
						resultset.getString("chemicalname1"), "");
				String s2 = checkNullAndFillcoma(
						resultset.getString("chemicalname2"), "");
				String s3 = checkNullAndFillcoma(
						resultset.getString("chemicalname3"), "");
				String s4 = checkNullAndFillcoma(
						resultset.getString("chemicalname4"), "");
				String s5 = checkNullAndFillcoma(
						resultset.getString("chemicalname5"), "");
				String chemecal = (new StringBuilder()).append(s1).append(s2)
						.append(s3).append(s4).append(s5).toString();
				hashtable.put("CHEMICAL", checkNullAndFill(chemecal, "-"));

				String dobPermitStatus = UtilityObject.checkNullAndFill(
						resultset.getString("DOBPERMITSTATUS"), "-");

				String dob = UtilityObject.checkNullAndFill(
						resultset.getString("DOB"), "-");

				if (dobPermitStatus.equalsIgnoreCase("Y")) {
					hashtable.put("DOB",
							UtilityObject.checkNullAndFill(dob, "-"));
				} else if (dobPermitStatus.equalsIgnoreCase("N")) {
					hashtable.put("DOB", "-");
				} else {
					hashtable.put("DOB", "N/A");
				}

				String depPermitstatus = UtilityObject.checkNullAndFill(
						resultset.getString("DEPPERMITSTATUS"), "-");

				String dep = UtilityObject.checkNullAndFill(
						resultset.getString("FUMEHOODDEPNUMBER"), "-");
				String exp = resultset.getString("expirationdate");

				if (depPermitstatus.equalsIgnoreCase("Y")) {
					hashtable.put("DEP",
							UtilityObject.checkNullAndFill(dep, "-"));
					hashtable
							.put("EXPIRATIONDATE",
									UtilityObject.checkNullAndFill(
											UtilityObject
													.convertYyyyMmDd2MmDdYyyy(exp),
											"-"));
				} else if (depPermitstatus.equalsIgnoreCase("N")) {
					hashtable.put("DEP", "-");
					hashtable.put("EXPIRATIONDATE", "-");
				} else {
					hashtable.put("DEP", "N/A");
					hashtable.put("EXPIRATIONDATE", "N/A");
				}

				int j_1 = 0;
				try {

					java.util.Date today = new java.util.Date();
					java.util.Date expdate = null;
					if (exp == null) {
						j_1 = 0;
					} else {
						expdate = UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
					}

					if (expdate == null) {
						j_1 = 0;
					}

					else if (expdate != null) {

						j_1 = today.compareTo(expdate);
						if (j_1 > 0) {
							j_1 = 0;
						} else {
							j_1 = 1;
						}
					} else {
						j_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 15 :" + e);
				} /*
				 * finally { SqlUtil.close(resultset);
				 * SqlUtil.close(preparedstatement); SqlUtil.close(connection);
				 * }
				 */

				String annualPermitStatus = UtilityObject.checkNullAndFill(
						resultset.getString("ANNUALPERMITSTATUS"), "-");

				String last = resultset.getString("LASTINSPECTIONDATE");
				String next = resultset.getString("NEXTINSPECTIONDATE");

				if (annualPermitStatus.equalsIgnoreCase("Y")) {
					hashtable.put("LASTINSPECTIONDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(last), "-"));
					hashtable.put("NEXTINSPECTIONDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(next), "-"));
				} else if (annualPermitStatus.equalsIgnoreCase("N")) {
					hashtable.put("LASTINSPECTIONDATE", "-");
					hashtable.put("NEXTINSPECTIONDATE", "-");
				} else {
					hashtable.put("LASTINSPECTIONDATE", "N/A");
					hashtable.put("NEXTINSPECTIONDATE", "N/A");
				}

				int k_1 = 0;

				try {

					java.util.Date today = new java.util.Date();
					java.util.Date nextdate = null;
					if (next == null) {
						k_1 = 0;
					} else {
						nextdate = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(next);
					}

					if (nextdate == null) {
						k_1 = 0;
					}

					else if (nextdate != null) {

						k_1 = today.compareTo(nextdate);
						if (k_1 > 0) {
							k_1 = 0;
						} else {
							k_1 = 1;
						}
					} else {
						k_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 15 :" + e);
				} /*
				 * finally { SqlUtil.close(resultset);
				 * SqlUtil.close(preparedstatement); SqlUtil.close(connection);
				 * }
				 */

				int m_1 = 0;

				try {
					java.util.Date today = new java.util.Date();
					java.util.Date lastdate = null;
					if (last == null) {
						m_1 = 0;
					} else {
						lastdate = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(last);
					}

					if (lastdate == null) {
						m_1 = 0;
					}

					else if (lastdate != null) {

						m_1 = today.compareTo(lastdate);
						if (m_1 > 0) {
							m_1 = 0;
						} else {
							m_1 = 1;
						}
					} else {
						m_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 15 :" + e);
				} /*
				 * finally { SqlUtil.close(resultset);
				 * SqlUtil.close(preparedstatement); SqlUtil.close(connection);
				 * }
				 */

				if (depPermitstatus.equalsIgnoreCase("Y") && dep.equals("-")
						&& !UtilityObject.isNotEmpty(exp)) {
					hashtable.put("compliance", "Non Compliance");
				} else if (dobPermitStatus.equalsIgnoreCase("Y")
						&& dob.equals("-")) {
					hashtable.put("compliance", "TBD");
				} else if (annualPermitStatus.equalsIgnoreCase("Y")
						&& !UtilityObject.isNotEmpty(last)
						&& !UtilityObject.isNotEmpty(next)) {
					hashtable.put("compliance", "Non Compliance");
				} else {
					hashtable.put("compliance", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static String getchem(String s) {
		SqlUtil sqlutil = new SqlUtil();
		String as[] = { "chemical1", "chemical2", "chemical3", "chemical4" };
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select chemicalid from  fumehoodtochemicals where FUMEHOODID=?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				int i = Integer.parseInt(resultset.getString("chemicalid")) - 1;
				s1 = (new StringBuilder()).append(s1).append(" ").append(as[i])
						.toString();
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s1;
	}

	public static List getNycdobStatusReport8(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select e.DOBSIGNOFF,e.ISABATOR,e.ETOID,e.HRS,e.DAYS,e.A_PERMITDATE,e.FACILITYDES"
							+ "IGNATEDID,e.MODEL,e.MANUFACTURER,e.AMANUFACTURER,e.AMODEL,e.dep,e.actiontaken,e."
							+ "DOB,e.ETODOB,b.BUILDINGNAME,et.TESTDATE,ep.EXPIRATIONDATE,et.NEXTDATE,ep.ISSUEDDATE from "
							+ "(eto e,building b) left join etopermitinfo ep on ep.ETOID = e.ETOID and depid='4"
							+ "' left join etotestinfo et on et.ETOID = e.ETOID where (e.MODIFIEDINPAST='1' or "
							+ "e.MODIFIEDINPAST='5') and e.BUILDINGID = b.BUILDINGID AND b.FACILITYID=?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			String tempetoid;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempetoid, hashtable)) {
				hashtable = new Hashtable();
				tempetoid = resultset.getString("ETOID");
				String permitdate = resultset.getString("A_PERMITDATE");
				String expirationdate = resultset.getString("EXPIRATIONDATE");
				String issuedate = resultset.getString("ISSUEDDATE");
				String testdate = resultset.getString("TESTDATE");
				String dep = resultset.getString("dep");
				String isabator = resultset.getString("ISABATOR");
				String nextdate = resultset.getString("NEXTDATE");
				hashtable.put(
						"eid",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("MODEL"), "-"));
				hashtable.put(
						"MANUFACTURER",
						checkNullAndFill(resultset.getString("MANUFACTURER"),
								"-"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("MODEL"), "-"));
				hashtable.put("dep", checkNullAndFill(dep, "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				// hashtable.put("TESTDATE", checkNullAndFilldate(testdate,
				// "-"));
				hashtable.put(
						"amake",
						checkNullAndFill(resultset.getString("AMANUFACTURER"),
								"-"));
				hashtable.put("amodel",
						checkNullAndFill(resultset.getString("AMODEL"), "-"));
				hashtable.put("hrs",
						checkNullAndFill(resultset.getString("HRS"), "-"));
				hashtable.put("days",
						checkNullAndFill(resultset.getString("DAYS"), "-"));

				// ********************Dob Filing
				// Start*************************//

				String dobfiling = UtilityObject.checkNullAndFill(
						resultset.getString("ETODOB"), "-");
				String dob = resultset.getString("DOB");
				String dobsignoff = resultset.getString("DOBSIGNOFF");
				String dobcompliancestatus = "";

				if (dobfiling.equalsIgnoreCase("Y")) {
					hashtable.put("DOB",
							UtilityObject.checkNullAndFill(dob, "No Filing"));
					hashtable.put("DOBSIGNOFF",
							UtilityObject.checkNullAndFill(dobsignoff, "-"));
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("DOB", "No Filing");
					hashtable.put("DOBSIGNOFF", "N/A");
				} else if (dobfiling.equalsIgnoreCase("N/A")) {
					hashtable.put("DOB", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
				} else {
					hashtable.put("DOB", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
				}

				if (dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("dobcompliancestatus", "TBD");
					dobcompliancestatus = "TBD";
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!dob.equals("No Filing")
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dob)
						&& dobsignoff.equalsIgnoreCase("Y")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dob)
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				}
				/*
				 * else if(!UtilityObject.isNotEmpty(dobnumber) &&
				 * !UtilityObject.isNotEmpty(dobsignoff)) {
				 * hashtable.put("dobcompliancestatus", "Non Compliance");
				 * dobcompliancestatus="Non Compliance"; }
				 */
				else {
					hashtable.put("dobcompliancestatus", "Compliance");
					dobcompliancestatus = "Compliance";
				}

				// ********************Dob Filing End*************************//

				String compliancestatus = "";
				hashtable
						.put("ISSUEDATE", checkNullAndFilldate(issuedate, "-"));

				String exp = resultset.getString("EXPIRATIONDATE");
				hashtable.put("EXPIRATIONDATE", UtilityObject.checkNullAndFill(
						UtilityObject.convertYyyyMmDd2MmDdYyyy(exp), "-"));

				int j_1 = 0;

				try {

					java.util.Date today = new java.util.Date();
					java.util.Date expi = null;

					if (exp == null) {
						j_1 = 0;
					} else {
						expi = UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
					}

					if (expi == null) {
						j_1 = 0;
					} else if (expi != null) {

						j_1 = today.compareTo(expi);
						if (j_1 > 0) {
							j_1 = 0;
						} else {
							j_1 = 1;
						}
					} else {
						j_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 3 :" + e);
				}

				if (!UtilityObject.isNotEmpty(dep)
						&& !UtilityObject.isNotEmpty(issuedate)
						&& !UtilityObject.isNotEmpty(exp)) {
					hashtable.put("compliancestatus", "TBD");
				} else if (UtilityObject.isNotEmpty(exp) && j_1 == 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(dep)
						&& !UtilityObject.isNotEmpty(issuedate)
						&& !UtilityObject.isNotEmpty(exp)) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else {
					hashtable.put("compliancestatus", "Compliance");
				}

			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("Exhibit ETO 8:Exception").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport9(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select c.DOBSIGNOFF,c.DOBJOBNUMBER,c.FACILITYDESIGNATEDID,c.CAPACITYTON,b.buildingname,c.floor,c.m"
							+ "ake,c.model,c.serial,c.depapproval,c.DOBAPPROVAL,c.EUPAVAILABLE,c.expirationdate from chillerab"
							+ "sorber c, building b where (c.MODIFIEDINPAST='1' or c.MODIFIEDINPAST='5') and c."
							+ "buildingid = b.buildingid and b.facilityid=?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); arraylist
					.add(hashtable)) {
				hashtable = new Hashtable();
				hashtable.put(
						"capacity",
						checkNullAndFill(resultset.getString("CAPACITYTON"),
								"-"));
				hashtable.put(
						"facilitydesinatedid",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"buildingname",
						checkNullAndFill(resultset.getString("buildingname"),
								"-"));
				hashtable.put("floor",
						checkNullAndFill(resultset.getString("floor"), "-"));
				hashtable.put("make",
						checkNullAndFill(resultset.getString("make"), "-"));
				hashtable.put("model",
						checkNullAndFill(resultset.getString("model"), "-"));
				hashtable.put("serial",
						checkNullAndFill(resultset.getString("serial"), "-"));

				// ********************Dob Filing
				// Start*************************//

				String dobfiling = UtilityObject.checkNullAndFill(
						resultset.getString("DOBAPPROVAL"), "-");
				String dob = resultset.getString("DOBJOBNUMBER");
				String dobsignoff = resultset.getString("DOBSIGNOFF");
				String eupavailable = resultset.getString("EUPAVAILABLE");
				String dobcompliancestatus = "";

				if (dobfiling.equalsIgnoreCase("Y")) {
					hashtable.put("DOBJOBNUMBER",
							UtilityObject.checkNullAndFill(dob, "No Filing"));
					hashtable.put("DOBSIGNOFF",
							UtilityObject.checkNullAndFill(dobsignoff, "-"));
					hashtable.put("EUPAVAILABLE",
							UtilityObject.checkNullAndFill(eupavailable, "-"));
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("DOBJOBNUMBER", "No Filing");
					hashtable.put("DOBSIGNOFF", "N/A");
					hashtable.put("EUPAVAILABLE", "N/A");
				} else if (dobfiling.equalsIgnoreCase("N/A")) {
					hashtable.put("DOBJOBNUMBER", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
					hashtable.put("EUPAVAILABLE", "N/A");
				} else {
					hashtable.put("DOBJOBNUMBER", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
					hashtable.put("EUPAVAILABLE", "N/A");
				}

				if (dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("dobcompliancestatus", "TBD");
					dobcompliancestatus = "TBD";
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (eupavailable.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!dob.equals("No Filing")
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dob)
						&& dobsignoff.equalsIgnoreCase("Y")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dob)
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else {
					hashtable.put("dobcompliancestatus", "Compliance");
					dobcompliancestatus = "Compliance";
				}

				// ********************Dob Filing End*************************//

				String dep = checkNullAndFill(
						resultset.getString("depapproval"), "-");
				hashtable.put("depapproval", dep);

				if (dep.equals("-")) {
					hashtable.put("compliancestatus", "TBD");
				} else if (dep.equalsIgnoreCase("N")) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else {
					hashtable.put("compliancestatus", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return arraylist;
	}

	public static List getNycdobStatusReport10(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select c.BUILDINGNAME,d.ADDRESS1,d.ADDRESS2,d.ADDRESS3,d.CITY,d.STATE,d.ZIPCODE,"
							+ "b.ELEVATORID,b.FACILITYDESIGNATEDID,b.ECOMMENTS,a.PERMITNUMBER,a.LASTINSPECTIOND"
							+ "ATE,a.REPORTSUBMITTALDATE,a.NEXTINSPECTIONDATE,a.FIRMINSPECTED from  (elevators "
							+ "b, building c, address d) left join elevatorpermitinfo a on a.ELEVATORID = b.ELE"
							+ "VATORID where (b.MODIFIEDINPAST='1' or b.MODIFIEDINPAST='5') and b.BUILDINGID = "
							+ "c.BUILDINGID AND c.ADDRESSID = d.ADDRESSID  AND c.facilityid=?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			String tempelevatorId;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempelevatorId, hashtable)) {
				hashtable = new Hashtable();
				tempelevatorId = resultset.getString("ELEVATORID");
				hashtable
						.put("ID",
								checkNullAndFill(
										resultset.getString("ELEVATORID"), "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put("ADDRESS1",
						checkNullAndFill(resultset.getString("ADDRESS1"), "-"));
				hashtable.put("ADDRESS2",
						checkNullAndFill(resultset.getString("ADDRESS2"), "-"));
				hashtable.put("ADDRESS3",
						checkNullAndFill(resultset.getString("ADDRESS3"), "-"));
				hashtable.put("CITY",
						checkNullAndFill(resultset.getString("CITY"), "-"));
				hashtable.put("STATE",
						checkNullAndFill(resultset.getString("STATE"), "-"));
				hashtable.put("ZIPCODE",
						checkNullAndFill(resultset.getString("ZIPCODE"), "-"));
				hashtable.put(
						"ELEVATORID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				String permitnumber = checkNullAndFill(
						resultset.getString("PERMITNUMBER"), "-");
				hashtable.put("PERMITNUMBER", permitnumber);
				java.util.Date last = null;
				Calendar lastcal = Calendar.getInstance();
				java.util.Date submital = null;
				Calendar submitalcal = Calendar.getInstance();

				java.util.Date next = null;// next
				Calendar nextcal = Calendar.getInstance();// next

				Calendar today = Calendar.getInstance();
				String lastinspectiondate = resultset
						.getString("LASTINSPECTIONDATE");

				String nextInspectionDate = resultset
						.getString("NEXTINSPECTIONDATE");// Next

				String submitaldate = resultset
						.getString("REPORTSUBMITTALDATE");

				if (UtilityObject.isNotEmpty(nextInspectionDate)) {
					next = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(nextInspectionDate);
					nextcal.setTime(next);
				}// next

				if (UtilityObject.isNotEmpty(lastinspectiondate)) {
					last = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(lastinspectiondate);
					lastcal.setTime(last);
				}
				if (UtilityObject.isNotEmpty(submitaldate)) {
					submital = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(submitaldate);
					submitalcal.setTime(submital);
				}
				hashtable.put("SUBMITTALDATE",
						checkNullAndFilldate(submitaldate, "-"));
				hashtable.put("LASTINSPECTIONDATE",
						checkNullAndFilldate(lastinspectiondate, "-"));
				hashtable.put("NEXTINSPECTIONDATE",
						checkNullAndFilldate(nextInspectionDate, "-"));
				hashtable.put(
						"FIRMINSPECTED",
						checkNullAndFill(resultset.getString("FIRMINSPECTED"),
								"-"));
				if (!UtilityObject.isNotEmpty(lastinspectiondate)
						&& !UtilityObject.isNotEmpty(submitaldate)
						&& permitnumber.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (UtilityObject.isNotEmpty(nextInspectionDate)
						&& (new java.util.Date()).compareTo(next) > 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(lastinspectiondate)
						|| UtilityObject.isNotEmpty(submitaldate)
						|| !permitnumber.equals("-")) {
					if (!UtilityObject.isNotEmpty(lastinspectiondate)
							|| !UtilityObject.isNotEmpty(submitaldate)
							|| permitnumber.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (today.get(1) == lastcal.get(1)) {
						if (lastcal.get(1) == submitalcal.get(1)
								|| lastcal.get(1) - 1 == submitalcal.get(1)) {
							hashtable.put("COMPLIANCESTATUS", "Compliance");
						} else {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						}
					} else if (today.get(1) - 1 == lastcal.get(1)) {
						if (lastcal.get(1) == submitalcal.get(1)) {
							hashtable.put("COMPLIANCESTATUS", "Compliance");
						} else {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						}
					} else {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					}
				} else {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport13(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select g.DOBSIGNOFF,g.DOBFILING,b.BUILDINGNAME,g.COGENTURBINEID,g.CO_DECPERMITOBTAINED,g.FAC"
							+ "ILITYDESIGNATEDID,g.CO_STACKTESTDATE,g.NEXTSTACKTESTDATE,g.TESTREPORTSUBMITTEDDA"
							+ "TE,g.NYCDOB,g.DEPNUMBER,t.EXPIRATIONDATE from (cogenturbine g, building b) left "
							+ "join cogenturbinepermitinfo t on t.COGENTURBINEID=g.COGENTURBINEID and t.DEPID='"
							+ "4' WHERE (g.STATUS='1' or g.STATUS='5') and g.buildingid=b.buildingid and b.faci"
							+ "lityid=? order by g.COGENTURBINEID desc");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			String tempcoId;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempcoId, hashtable)) {
				hashtable = new Hashtable();
				tempcoId = resultset.getString("COGENTURBINEID");
				hashtable.put("cogenid", tempcoId);
				hashtable.put(
						"location",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				// hashtable.put("DOBSIGNOFF",
				// checkNullAndFill(resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put(
						"facilitydesinatedid",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));

				// *******************************DOB Compliance
				// Status***********************//
				String dobfiling = UtilityObject.checkNullAndFill(
						resultset.getString("DOBFILING"), "-");
				String dobnumber = resultset.getString("NYCDOB");
				String dobsignoff = resultset.getString("DOBSIGNOFF");

				if (dobfiling.equalsIgnoreCase("Y")) {
					hashtable.put("NYCDOB", UtilityObject.checkNullAndFill(
							dobnumber, "No Filing"));
					hashtable.put("DOBSIGNOFF",
							UtilityObject.checkNullAndFill(dobsignoff, "-"));
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("NYCDOB", "No Filing");
					hashtable.put("DOBSIGNOFF", "N/A");
				} else if (dobfiling.equalsIgnoreCase("N/A")) {
					hashtable.put("NYCDOB", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
				} else {
					hashtable.put("NYCDOB", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
				}

				String dobcompliancestatus = "";

				if (dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("dobcompliancestatus", "TBD");
					dobcompliancestatus = "TBD";
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!dobnumber.equals("No Filing")
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dobnumber)
						&& dobsignoff.equalsIgnoreCase("Y")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				} else if (!UtilityObject.isNotEmpty(dobnumber)
						&& dobsignoff.equalsIgnoreCase("N")) {
					hashtable.put("dobcompliancestatus", "Non Compliance");
					dobcompliancestatus = "Non Compliance";
				}
				/*
				 * else if(!UtilityObject.isNotEmpty(dobnumber) &&
				 * !UtilityObject.isNotEmpty(dobsignoff)) {
				 * hashtable.put("dobcompliancestatus", "Non Compliance");
				 * dobcompliancestatus="Non Compliance"; }
				 */
				else {
					hashtable.put("dobcompliancestatus", "Compliance");
					dobcompliancestatus = "Compliance";
				}

				// ********************Dob Filing
				// ENDS*************************//

				String dep = checkNullAndFill(resultset.getString("DEPNUMBER"),
						"-");// dep
				hashtable.put("deppermit", dep);

				String expirationdate = resultset.getString("EXPIRATIONDATE");
				hashtable.put("expirationdate",
						checkNullAndFilldate(expirationdate, "-"));// dep
																	// expiration
																	// date

				String dec = checkNullAndFill(
						resultset.getString("CO_DECPERMITOBTAINED"), "-");
				hashtable.put("stacktest", dec);// stack test

				// String decdate =
				// resultset.getString("TESTREPORTSUBMITTEDDATE");

				String lasttestdate = resultset.getString("CO_STACKTESTDATE");// last
																				// test
																				// date
				String nexttestdate = resultset.getString("NEXTSTACKTESTDATE");// next
																				// test
																				// date

				java.util.Date edate = null;
				// java.util.Date ddate = null;
				java.util.Date ldate = null;
				java.util.Date today = new java.util.Date();
				int e1 = 2;
				// int d1 = 0;
				int l1 = 2;
				try {
					if (UtilityObject.isNotEmpty(expirationdate)) {
						edate = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(expirationdate);
						if (edate != null) {
							e1 = today.compareTo(edate);
							if (e1 > 0) {
								e1 = 0;
							} else {
								e1 = 1;
							}
						}
					}
				} catch (Exception e) {
					System.out.println((new StringBuilder())
							.append("in first:").append(e).toString());
				} /*
				 * finally { SqlUtil.close(resultset);
				 * SqlUtil.close(preparedstatement); SqlUtil.close(connection);
				 * }
				 */
				/*
				 * try { if(UtilityObject.isNotEmpty(decdate)) { ddate =
				 * UtilityObject.convert_YyyyMmDd2MmDdYyyy(decdate); if(ddate !=
				 * null) { d1 = 1; } } } catch(Exception e) {
				 * System.out.println((new
				 * StringBuilder()).append("in tsecond:").append(e).toString());
				 * }
				 */
				try {
					if (UtilityObject.isNotEmpty(lasttestdate)) {
						ldate = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(lasttestdate);
						if (ldate != null) {

							l1 = today.compareTo(ldate);
							if (l1 > 0) {
								l1 = 1;
							} else {
								l1 = 0;
							}
						}
					}
				} catch (Exception e) {
					System.out.println((new StringBuilder())
							.append("in third:").append(e).toString());
				} /*
				 * finally { SqlUtil.close(resultset);
				 * SqlUtil.close(preparedstatement); SqlUtil.close(connection);
				 * }
				 */
				// System.out.println((new
				// StringBuilder()).append("d1:").append(d1).append(",,,,e1:").append(e1).append(",,,,,l1:").append(l1).toString());
				hashtable.put("lasttestdate",
						checkNullAndFilldate(lasttestdate, "-"));
				hashtable.put("nexttestdate",
						checkNullAndFilldate(nexttestdate, "-"));

				if (dep.equals("-") || dec.equals("-")
						|| dec.equalsIgnoreCase("N")) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (e1 == 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (l1 == 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (e1 == 2) {
					hashtable.put("compliancestatus", "TBD");
				} else if (l1 == 2) {
					hashtable.put("compliancestatus", "TBD");
				} else {
					hashtable.put("compliancestatus", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport13forreplace(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select * from facility");
			Hashtable hashtable;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); arraylist
					.add(hashtable)) {
				hashtable = new Hashtable();
				hashtable.put("BUILDINGNAME",
						checkNullAndFill(resultset.getString(2), "N/A"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return arraylist;
	}

	public static List getNycdobStatusReport11(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT r.PERMITSTATUS,r.DOBSIGNOFF,b.BUILDINGNAME,r.RPZID,r.FACILITYDESIGNATEDID,r.SERIALNUMBER"
							+ ",r.TYPE,r.MODEL,r.SIZE,r.DOBPERMITNUMBER,r.DEPPERMITNUMBER,ri.LASTINSPECTIONDATE"
							+ ",ri.NEXTINSPECTIONDATE,per.EXPIRATIONDATE FROM (building b,rpz r) left join rpzi"
							+ "nspectioninfo ri on r.RPZID = ri.RPZID left join rpzpermitinfo per on r.RPZID = "
							+ "per.RPZID and per.DEPID='1' WHERE  (r.MODIFIEDINPAST='1' or r.MODIFIEDINPAST='5'"
							+ ") and r.buildingid = b.buildingid  AND b.FACILITYID = ?");
			preparedstatement.setString(1, String.valueOf(facid));
			String tempId = "";
			Hashtable hashtable;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("RPZID");
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put("RPZID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"SERIALNUMBER",
						checkNullAndFill(resultset.getString("SERIALNUMBER"),
								"-"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("MODEL"), "-"));
				hashtable.put("TYPE",
						checkNullAndFill(resultset.getString("TYPE"), "-"));

				String s = resultset.getString("SIZE");
				if (s.equals("-99") || s.equals("0") || s.equals("-1")) {
					hashtable.put("SIZE", "-");
				} else {
					hashtable.put("SIZE", s);
				}

				// hashtable.put("SIZE",
				// checkNullAndFill(resultset.getString("SIZE"), "-"));

				String permitStatus = checkNullAndFill(
						resultset.getString("PERMITSTATUS"), "-");
				String dob = checkNullAndFill(
						resultset.getString("DOBPERMITNUMBER"), "-");
				hashtable.put("DOBPERMITNUMBER", dob);

				String dep = checkNullAndFill(
						resultset.getString("DEPPERMITNUMBER"), "-");

				if (!permitStatus.equals("NA")) {
					hashtable.put("DEPPERMITNUMBER", dep);
				} else {
					hashtable.put("DEPPERMITNUMBER", "N/A");
				}

				java.util.Date last_date = null;
				Calendar lastcal = Calendar.getInstance();

				java.util.Date next_date = null;
				Calendar nextcal = Calendar.getInstance();

				Calendar today = Calendar.getInstance();

				String lastinspection = resultset
						.getString("LASTINSPECTIONDATE");
				String nextInspection = resultset
						.getString("NEXTINSPECTIONDATE");

				if (UtilityObject.isNotEmpty(lastinspection)) {
					last_date = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(lastinspection);
					lastcal.setTime(last_date);
				}

				if (UtilityObject.isNotEmpty(nextInspection)) {
					next_date = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(nextInspection);
					nextcal.setTime(next_date);
				}

				hashtable.put("LASTINSPECTIONDATE",
						checkNullAndFilldate(lastinspection, "-"));
				hashtable.put("NEXTINSPECTIONDATE",
						checkNullAndFilldate(nextInspection, "-"));

				if (dob.equals("-") && dep.equals("-")
						&& !UtilityObject.isNotEmpty(lastinspection)) {
					hashtable.put("compliancestatus", "TBD");
				} else if (dob.equals("-")) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (dep.equals("-") && !permitStatus.equals("NA")) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(lastinspection)
						&& (new java.util.Date()).compareTo(last_date) < 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(nextInspection)
						&& (new java.util.Date()).compareTo(next_date) > 0) {
					hashtable.put("compliancestatus", "Non Compliance");
				} else {
					hashtable.put("compliancestatus", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("Exception In RPZ Exhibit.").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}

		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport12(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select b.BUILDINGNAME,br.YEARINSTALLED,br.FACILITYDESIGNATEDID,br.MANUFACTURER,b"
							+ "r.MAKE,br.MODEL,br.SERIALNUMBER,br.BURNERTYPE,br.CAPACITY,br.PRIMARYFUEL,br.SECO"
							+ "NDARYFUEL,br.ISTURBINE,br.TMANUFACTURER,br.TMAKE,br.TMODEL,br.TSERIAL,br.TCAPACI"
							+ "TY,br.TBURNERTYPE,br.TPRIMARYFUEL,br.TSECONDARYFUEL,br.ISWHRB,br.WMANUFACTURER,b"
							+ "r.WMAKE,br.WMODEL,br.WSERIAL,br.WCAPACITY,br.WBURNERTYPE,br.WPRIMARYFUEL,br.WSEC"
							+ "ONDARYFUEL from (building b,cogenturbine br)  where (br.STATUS='1' or br.STATUS="
							+ "'5') and br.BUILDINGID = b.BUILDINGID and b.FACILITYID=?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); arraylist
					.add(hashtable)) {
				hashtable = new Hashtable();
				hashtable.put(
						"cid",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"enginemanufacturer",
						checkNullAndFill(resultset.getString("MANUFACTURER"),
								"-"));
				hashtable.put(
						"turbinemanufacturer",
						checkNullAndFill(resultset.getString("TMANUFACTURER"),
								"-"));
				hashtable.put(
						"whrbmanufacturer",
						checkNullAndFill(resultset.getString("WMANUFACTURER"),
								"-"));
				hashtable.put("enginemodel",
						checkNullAndFill(resultset.getString("MODEL"), "-"));
				hashtable.put("turbinemodel",
						checkNullAndFill(resultset.getString("TMODEL"), "-"));
				hashtable.put("whrbmodel",
						checkNullAndFill(resultset.getString("WMODEL"), "-"));
				hashtable.put(
						"engineserial",
						checkNullAndFill(resultset.getString("SERIALNUMBER"),
								"-"));
				hashtable.put("turbineserial",
						checkNullAndFill(resultset.getString("TSERIAL"), "-"));
				hashtable.put("whrbserial",
						checkNullAndFill(resultset.getString("WSERIAL"), "-"));
				hashtable.put(
						"location",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put(
						"year",
						checkNullAndFill(resultset.getString("YEARINSTALLED"),
								"-"));
				String enginecapacity = resultset.getString("CAPACITY");
				hashtable.put("enginecapacity",
						checkNullAndFill(enginecapacity, "-"));
				int engineprimary = resultset.getInt("PRIMARYFUEL");
				if (engineprimary > 0) {
					FurnaceOilTypeEnum furnaceoiltypeenume = FurnaceOilTypeEnum
							.get(engineprimary);
					hashtable.put(
							"engineprimary",
							checkNullAndFill(furnaceoiltypeenume.getName(),
									"N/A"));
					if (UtilityObject.isNotEmpty(enginecapacity)) {
						double d = Double.parseDouble(enginecapacity);
						double d1 = -99D;
						if (d > 0.0D) {
							double d2 = round(d * 0.01D * 1000D, 2);
							if (furnaceoiltypeenume.getCode() == 1) {
								hashtable.put("enginecfh", (new StringBuffer(
										String.valueOf(d2))).toString());
								hashtable.put("enginegph", "-");
							} else {
								byte nbyte0 = -99;
								int nj1 = furnaceoiltypeenume.getCode();
								int nk1 = 0;
								if (nj1 == 2) {
									nk1 = 140;
								} else if (nj1 == 3) {
									nk1 = 145;
								} else if (nj1 == 4) {
									nk1 = 150;
								}
								if (nk1 > 0) {
									hashtable.put("enginecfh", "-");
									hashtable
											.put("enginegph",
													(new StringBuffer(
															String.valueOf(round(
																	(d * 0.01D * 1000D)
																			/ (double) nk1,
																	2))))
															.toString());
								} else {
									hashtable.put("enginecfh", "-");
									hashtable.put("enginegph", "-");
								}
							}
						}
					} else {
						hashtable.put("enginecfh", "-");
						hashtable.put("enginegph", "-");
					}
				} else {
					hashtable.put("engineprimary", "N/A");
					hashtable.put("enginecfh", "-");
					hashtable.put("enginegph", "-");
				}
				int enginesecondary = resultset.getInt("SECONDARYFUEL");
				if (enginesecondary > 0) {
					FurnaceOilTypeEnum furnaceoiltypeenum1e = FurnaceOilTypeEnum
							.get(enginesecondary);
					hashtable.put(
							"enginesecondary",
							checkNullAndFill(furnaceoiltypeenum1e.getName(),
									"N/A"));
					if (UtilityObject.isNotEmpty(enginecapacity)) {
						double d = Double.parseDouble(enginecapacity);
						int i1 = enginesecondary;
						if (i1 > 0 && furnaceoiltypeenum1e != null) {
							byte byte0 = -99;
							int j1 = furnaceoiltypeenum1e.getCode();
							int k1 = 0;
							if (j1 == 2) {
								k1 = 140;
							} else if (j1 == 3) {
								k1 = 145;
							} else if (j1 == 4) {
								k1 = 150;
							}
							if (k1 > 0) {
								hashtable.put(
										"enginegph",
										(new StringBuffer(String.valueOf(round(
												(d * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
							}
						}
					} else {
						hashtable.put("enginecfh", "-");
						hashtable.put("enginegph", "-");
					}
				} else {
					hashtable.put("enginesecondary", "N/A");
				}
				String turbinecapacity = resultset.getString("TCAPACITY");
				hashtable.put("turbinecapacity",
						checkNullAndFill(turbinecapacity, "-"));
				int turbineprimary = resultset.getInt("TPRIMARYFUEL");
				int turbinesecondary = resultset.getInt("TSECONDARYFUEL");
				if (turbineprimary > 0) {
					FurnaceOilTypeEnum furnaceoiltypeenumt = FurnaceOilTypeEnum
							.get(turbineprimary);
					hashtable.put(
							"turbineprimary",
							checkNullAndFill(furnaceoiltypeenumt.getName(),
									"N/A"));
					if (UtilityObject.isNotEmpty(turbinecapacity)) {
						double d = Double.parseDouble(turbinecapacity);
						double d1 = -99D;
						if (d > 0.0D) {
							double d2 = round(d * 0.01D * 1000D, 2);
							if (furnaceoiltypeenumt.getCode() == 1) {
								hashtable.put("turbinecfh", (new StringBuffer(
										String.valueOf(d2))).toString());
								hashtable.put("turbinegph", "-");
							} else {
								byte nbyte0 = -99;
								int nj1 = furnaceoiltypeenumt.getCode();
								int nk1 = 0;
								if (nj1 == 2) {
									nk1 = 140;
								} else if (nj1 == 3) {
									nk1 = 145;
								} else if (nj1 == 4) {
									nk1 = 150;
								}
								if (nk1 > 0) {
									hashtable.put("turbinecfh", "-");
									hashtable
											.put("turbinegph",
													(new StringBuffer(
															String.valueOf(round(
																	(d * 0.01D * 1000D)
																			/ (double) nk1,
																	2))))
															.toString());
								} else {
									hashtable.put("turbinecfh", "-");
									hashtable.put("turbinegph", "-");
								}
							}
						}
					} else {
						hashtable.put("turbinecfh", "-");
						hashtable.put("turbinegph", "-");
					}
				} else {
					hashtable.put("turbineprimary", "N/A");
					hashtable.put("turbinecfh", "-");
					hashtable.put("turbinegph", "-");
				}
				if (turbinesecondary > 0) {
					FurnaceOilTypeEnum furnaceoiltypeenum1t = FurnaceOilTypeEnum
							.get(turbinesecondary);
					hashtable.put(
							"turbinesecondary",
							checkNullAndFill(furnaceoiltypeenum1t.getName(),
									"N/A"));
					if (UtilityObject.isNotEmpty(turbinecapacity)) {
						double d = Double.parseDouble(turbinecapacity);
						int i1 = turbinesecondary;
						if (i1 > 0 && furnaceoiltypeenum1t != null) {
							byte byte0 = -99;
							int j1 = furnaceoiltypeenum1t.getCode();
							int k1 = 0;
							if (j1 == 2) {
								k1 = 140;
							} else if (j1 == 3) {
								k1 = 145;
							} else if (j1 == 4) {
								k1 = 150;
							}
							if (k1 > 0) {
								hashtable.put(
										"turbinegph",
										(new StringBuffer(String.valueOf(round(
												(d * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
							}
						}
					} else {
						hashtable.put("turbinegph", "-");
						hashtable.put("turbinecfh", "-");
					}
				} else {
					hashtable.put("turbinesecondary", "N/A");
				}
				String whrbcapacity = resultset.getString("WCAPACITY");
				hashtable.put("whrbcapacity",
						checkNullAndFill(whrbcapacity, "-"));
				int whrbprimary = resultset.getInt("WPRIMARYFUEL");
				int whrbsecondary = resultset.getInt("WSECONDARYFUEL");
				if (whrbprimary > 0) {
					FurnaceOilTypeEnum furnaceoiltypeenumw = FurnaceOilTypeEnum
							.get(whrbprimary);
					hashtable.put(
							"whrbprimary",
							checkNullAndFill(furnaceoiltypeenumw.getName(),
									"N/A"));
					if (UtilityObject.isNotEmpty(whrbcapacity)) {
						double d = Double.parseDouble(whrbcapacity);
						double d1 = -99D;
						if (d > 0.0D) {
							double d2 = round(d * 0.01D * 1000D, 2);
							if (furnaceoiltypeenumw.getCode() == 1) {
								hashtable.put("whrbcfh", (new StringBuffer(
										String.valueOf(d2))).toString());
								hashtable.put("whrbgph", "-");
							} else {
								byte nbyte0 = -99;
								int nj1 = furnaceoiltypeenumw.getCode();
								int nk1 = 0;
								if (nj1 == 2) {
									nk1 = 140;
								} else if (nj1 == 3) {
									nk1 = 145;
								} else if (nj1 == 4) {
									nk1 = 150;
								}
								if (nk1 > 0) {
									hashtable.put("whrbcfh", "-");
									hashtable
											.put("whrbgph",
													(new StringBuffer(
															String.valueOf(round(
																	(d * 0.01D * 1000D)
																			/ (double) nk1,
																	2))))
															.toString());
								} else {
									hashtable.put("whrbcfh", "-");
									hashtable.put("whrbgph", "-");
								}
							}
						}
					} else {
						hashtable.put("whrbcfh", "-");
						hashtable.put("whrbgph", "-");
					}
				} else {
					hashtable.put("whrbprimary", "N/A");
					hashtable.put("whrbgph", "-");
					hashtable.put("whrbcfh", "-");
				}
				if (whrbsecondary > 0) {
					FurnaceOilTypeEnum furnaceoiltypeenum1w = FurnaceOilTypeEnum
							.get(whrbsecondary);
					hashtable.put(
							"whrbsecondary",
							checkNullAndFill(furnaceoiltypeenum1w.getName(),
									"N/A"));
					if (UtilityObject.isNotEmpty(whrbcapacity)) {
						double d = Double.parseDouble(whrbcapacity);
						int i1 = whrbsecondary;
						if (i1 > 0 && furnaceoiltypeenum1w != null) {
							byte byte0 = -99;
							int j1 = furnaceoiltypeenum1w.getCode();
							int k1 = 0;
							if (j1 == 2) {
								k1 = 140;
							} else if (j1 == 3) {
								k1 = 145;
							} else if (j1 == 4) {
								k1 = 150;
							}
							if (k1 > 0) {
								hashtable.put(
										"whrbgph",
										(new StringBuffer(String.valueOf(round(
												(d * 0.01D * 1000D)
														/ (double) k1, 2))))
												.toString());
							}
						}
					} else {
						hashtable.put("whrbgph", "-");
						hashtable.put("whrbcfh", "-");
					}
				} else {
					hashtable.put("whrbsecondary", "N/A");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return arraylist;
	}

	public static List getNycdobStatusReport16(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select br.DOBSIGNOFF,b.BUILDINGNAME,bp.INSISSUEDATE,bp.fileno,bp.permitnumber as"
							+ " dotpermitnumber,bp.expirationdate,bp.LASTINSPDATE,bp.NEXTINSPDATE,bp.INSISSUEDA"
							+ "TE,bp.INSEXPDATE,t.type,br.PERMITNUMBER,br.FACILITYDESIGNATEDID,br.BRIDGETUNNELI"
							+ "D from (building b,bridgetunnels br,bridgetype t) left join bridgetunnelpermitin"
							+ "fo bp on  br.BRIDGETUNNELID = bp.BRIDGETUNNELID and bp.depid='9' where (br.MODIF"
							+ "IEDINPAST='1' or br.MODIFIEDINPAST='5') and br.BUILDINGID = b.BUILDINGID AND br."
							+ "isbridge=t.che AND b.FACILITYID=?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			String tempid;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempid, hashtable)) {
				hashtable = new Hashtable();
				tempid = resultset.getString("BRIDGETUNNELID");
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put("FILENO",
						checkNullAndFill(resultset.getString("fileno"), "-"));
				String st = checkNullAndFill(
						resultset.getString("dotpermitnumber"), "-");
				hashtable.put("PERMITNUMBER", st);
				String s = checkNullAndFill(
						resultset.getString("PERMITNUMBER"), "-");
				hashtable.put("dobnumber", s);
				if (s.equals("-")) {
					hashtable.put("dobcompliance", "Non Compliance");
				} else {
					hashtable.put("dobcompliance", "Compliance");
				}
				hashtable.put(
						"bid",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put("type",
						checkNullAndFill(resultset.getString("type"), "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				java.util.Date today = new java.util.Date();
				java.util.Date dotexp_date = null;
				java.util.Date insexp_date = null;
				java.util.Date last_date = null;
				java.util.Date next_date = null;
				String dotexp = resultset.getString("expirationdate");
				String inseff = resultset.getString("INSISSUEDATE");
				String insexp = resultset.getString("INSEXPDATE");
				String last = resultset.getString("LASTINSPDATE");
				String next = resultset.getString("NEXTINSPDATE");
				if (UtilityObject.isNotEmpty(dotexp)) {
					dotexp_date = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(dotexp);
				}
				if (UtilityObject.isNotEmpty(insexp)) {
					insexp_date = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(insexp);
				}
				if (UtilityObject.isNotEmpty(last)) {
					last_date = UtilityObject.convert_YyyyMmDd2MmDdYyyy(last);
				}
				if (UtilityObject.isNotEmpty(next)) {
					next_date = UtilityObject.convert_YyyyMmDd2MmDdYyyy(next);
				}
				hashtable.put("EXPIRATIONDATE",
						checkNullAndFilldate(dotexp, "-"));
				hashtable
						.put("INSISSUEDATE", checkNullAndFilldate(inseff, "-"));
				hashtable.put("INSEXPDATE", checkNullAndFilldate(insexp, "-"));
				hashtable.put("LASTINSPDATE", checkNullAndFilldate(last, "-"));
				hashtable.put("NEXTINSPDATE", checkNullAndFilldate(next, "-"));
				if (st.equals("-") && !UtilityObject.isNotEmpty(dotexp)
						&& !UtilityObject.isNotEmpty(inseff)
						&& !UtilityObject.isNotEmpty(insexp)
						&& !UtilityObject.isNotEmpty(last)
						&& !UtilityObject.isNotEmpty(next)) {
					hashtable.put("dotcompliance", "TBD");
				} else if (st.equals("-")) {
					hashtable.put("dotcompliance", "Non Compliance");
				} else if (st.equals("-")) {
					hashtable.put("dotcompliance", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(dotexp)
						&& today.compareTo(dotexp_date) > 0) {
					hashtable.put("dotcompliance", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(insexp)
						&& today.compareTo(insexp_date) > 0) {
					hashtable.put("dotcompliance", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(next)
						&& today.compareTo(next_date) > 0) {
					hashtable.put("dotcompliance", "Non Compliance");
				} else if (!UtilityObject.isNotEmpty(dotexp)
						|| !UtilityObject.isNotEmpty(insexp)
						|| !UtilityObject.isNotEmpty(next)) {
					hashtable.put("dotcompliance", "TBD");
				} else {
					hashtable.put("dotcompliance", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport17(int facid) {
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		LinkedHashMap hash4Unique = new LinkedHashMap();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select i.DOBSIGNOFF,b.BUILDINGNAME,i.INCINERATORCREMATORIESID,i.FACILITYDESIGNAT"
							+ "EDID,i.CAPACITY,i.DEPREQUIRED,i.DEPNO,i.DOBAPPROVAL,i.DOBNO,i.DECNO,i.ISOLIDWAST"
							+ "E,i.I_SOLIDEXPIRATIONDATE,i.INCSTACKTESTED,i.TESTREPORTSUBMITEDDATE,i.I_STACKTES"
							+ "TDATE,i.NEXTSTACKTEST,i.IWASTEQUANTITY,i.IWASTELIMIT,i.FACILITYSECONDARY,i.FACIL"
							+ "ITYPRIMARY,i.PRIMARYTEMP,i.SECTEMP,p.EXPIRATIONDATE,c.ROLLINGCONSUMPTION from (b"
							+ "uilding b,incineratorcrematories i) left join incincremannualwasteconsum c on c."
							+ "INCINERATORCREMATORIESID=i.INCINERATORCREMATORIESID left join incineratorcremato"
							+ "rpermitinfo p on p.INCINERATORCREMATORIESID=i.INCINERATORCREMATORIESID and p.dep"
							+ "id='2' where (i.STATUS='1' or i.STATUS='5') and i.BUILDINGID = b.BUILDINGID AND "
							+ "b.FACILITYID=?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			String tempetoid;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); hash4Unique
					.put(tempetoid, hashtable)) {
				hashtable = new Hashtable();
				tempetoid = resultset.getString("INCINERATORCREMATORIESID");
				hashtable.put(
						"IID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"LOCATION",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put("THROUGHPUT",
						checkNullAndFill(resultset.getString("CAPACITY"), "-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				String decno = checkNullAndFill(resultset.getString("DECNO"),
						"-");
				hashtable.put("DEC", decno);
				java.util.Date today = new java.util.Date();
				java.util.Date depexpi = null;
				java.util.Date decexpi = null;
				java.util.Date stackexpi = null;
				java.util.Date stacktestdat = null;
				String dob = checkNullAndFill(
						resultset.getString("DOBAPPROVAL"), "-");
				String dobno = checkNullAndFill(resultset.getString("DOBNO"),
						"-");
				if (dob.equals("Y")) {
					hashtable.put("DOB", dobno);
				} else if (dob.equals("-")) {
					hashtable.put("DOB", "TBD");
				} else {
					hashtable.put("DOB", "-");
				}
				String dep = checkNullAndFill(
						resultset.getString("DEPREQUIRED"), "-");
				String depno = checkNullAndFill(resultset.getString("DEPNO"),
						"-");
				String dec = checkNullAndFill(
						resultset.getString("ISOLIDWASTE"), "-");
				String decexp = resultset.getString("I_SOLIDEXPIRATIONDATE");
				if (dec.equals("Y")) {
					hashtable.put("DECEXPIRATIONDATE",
							checkNullAndFilldate(decexp, "-"));
					if (UtilityObject.isNotEmpty(decexp)) {
						decexpi = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(decexp);
					}
				} else if (dec.equals("Y")) {
					hashtable.put("DECEXPIRATIONDATE", "TBD");
				} else {
					hashtable.put("DECEXPIRATIONDATE", "N/A");
				}
				String depexp = resultset.getString("EXPIRATIONDATE");
				if (dep.equals("Y")) {
					hashtable.put("DEP", dobno);
					hashtable.put("DEPEXPIRATIONDATE",
							checkNullAndFilldate(depexp, "-"));
					if (UtilityObject.isNotEmpty(depexp)) {
						depexpi = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(depexp);
					}
				} else if (dep.equals("-")) {
					hashtable.put("DEP", "TBD");
					hashtable.put("DEPEXPIRATIONDATE", "TBD");
				} else {
					hashtable.put("DEP", "N/A");
					hashtable.put("DEPEXPIRATIONDATE", "N/A");
				}
				String stack = checkNullAndFill(
						resultset.getString("INCSTACKTESTED"), "-");
				String stackreqdate = resultset
						.getString("TESTREPORTSUBMITEDDATE");
				String stacktest = resultset.getString("I_STACKTESTDATE");
				String nexttest = resultset.getString("NEXTSTACKTEST");
				if (stack.equals("Y")) {
					hashtable.put("STACKTESTREQUIRED", "Y");
					hashtable.put("STACKTESTDATE",
							checkNullAndFilldate(stacktest, "-"));
					hashtable.put("NEXTSTACKTESTDATE",
							checkNullAndFilldate(nexttest, "-"));
					if (UtilityObject.isNotEmpty(stackreqdate)) {
						stackexpi = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(stackreqdate);
					}
					if (UtilityObject.isNotEmpty(stacktest)) {
						stacktestdat = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(stacktest);
					}
				} else if (stack.equals("-")) {
					hashtable.put("STACKTESTREQUIRED", "TBD");
					hashtable.put("STACKTESTDATE", "TBD");
					hashtable.put("NEXTSTACKTESTDATE", "TBD");
				} else {
					hashtable.put("STACKTESTREQUIRED", "N/A");
					hashtable.put("STACKTESTDATE", "N/A");
					hashtable.put("NEXTSTACKTESTDATE", "N/A");
				}
				String ptemp = resultset.getString("PRIMARYTEMP");
				String stemp = resultset.getString("SECTEMP");
				String fptemp = resultset.getString("FACILITYPRIMARY");
				String fstemp = resultset.getString("FACILITYSECONDARY");
				if (UtilityObject.isNotEmpty(fptemp)
						&& UtilityObject.isNotEmpty(ptemp)) {
					if (Integer.parseInt(fptemp) >= Integer.parseInt(ptemp)) {
						hashtable.put("PRIMARY", "Compliance");
					} else {
						hashtable.put("PRIMARY", "Non Compliance");
					}
				} else {
					hashtable.put("PRIMARY", "TBD");
				}
				if (UtilityObject.isNotEmpty(fstemp)
						&& UtilityObject.isNotEmpty(stemp)) {
					if (Integer.parseInt(fptemp) >= Integer.parseInt(stemp)) {
						hashtable.put("SECONDARY", "Compliance");
					} else {
						hashtable.put("SECONDARY", "Non Compliance");
					}
				} else {
					hashtable.put("SECONDARY", "TBD");
				}
				String waste = checkNullAndFill(
						resultset.getString("IWASTEQUANTITY"), "-");
				String wastelimit = resultset.getString("IWASTELIMIT");
				String consumption = resultset.getString("ROLLINGCONSUMPTION");
				if (waste.equals("Y")) {
					if (UtilityObject.isNotEmpty(wastelimit)
							&& UtilityObject.isNotEmpty(consumption)) {
						if (Integer.parseInt(wastelimit) >= Integer
								.parseInt(consumption)) {
							hashtable.put("WASTEQUANTITY", "Compliance");
						} else {
							hashtable.put("WASTEQUANTITY", "Non Compliance");
						}
					} else {
						hashtable.put("WASTEQUANTITY", "TBD");
					}
				} else if (dob.equals("-")) {
					hashtable.put("WASTEQUANTITY", "TBD");
				} else {
					hashtable.put("WASTEQUANTITY", "Compliance");
				}
				if (dob.equals("-") && dep.equals("-") && dec.equals("-")
						&& stack.equals("-") && decno.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (!dob.equals("Y") && !dep.equals("Y")
						&& !dec.equals("Y") && !stack.equals("Y")
						&& !decno.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				} else if (decno.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dob.equals("Y") && dobno.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dep.equals("Y") && depno.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dep.equals("Y") && depexpi != null
						&& today.compareTo(depexpi) > 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dec.equals("Y") && decexpi != null
						&& today.compareTo(decexpi) > 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (stack.equals("Y") && stackexpi != null
						&& stacktestdat != null
						&& stackexpi.compareTo(stacktestdat) < 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dep.equals("Y") && depexpi == null) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (dec.equals("Y") && decexpi == null) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (stack.equals("Y") && stackexpi == null) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("Exhibit ETO 8:Exception").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport14(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.DOBNUMBER,s.DOBSIGNOFF,s.FACILITYDESIGNATEDID,s.SPRAYBOOTHID,s.MAKE,s.M"
							+ "ODEL,b.BUILDINGNAME,s.HROFOPERATION,s.PSHOURSOFOPERATIONYEAR,s.PSDEPNUMBER,s.INC"
							+ "LUDEDINDECPERMIT,sp.EXPIRATIONDATE,s.VOCMONTHLYQUANTITY,s.VOCMONTHLYLIMIT,s.VOCM"
							+ "ONTH,s.SPRAYPAINTCAP,s.SPRAYSOLVANTCAP,s.SPRAYINKCAP,s.VOCCAP,s.SPRAYPAINTDENSIT"
							+ "Y,s.SPRAYSOLVANTDENSITY,s.SPRAYINKDENSITY FROM (spraybooth s, building b) left j"
							+ "oin  sprayboothpermitinfo sp on  s.SPRAYBOOTHID = sp.SPRAYBOOTHID and sp.depid='"
							+ "2' WHERE  (s.STATUS='1' or s.STATUS='5') and b.BUILDINGID=s.BUILDINGID AND b.FAC"
							+ "ILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("SPRAYBOOTHID");
				hashtable.put("SPRAYBOOTHID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put("MAKE",
						checkNullAndFill(resultset.getString("MAKE"), "-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable
						.put("DOBNUMBER",
								checkNullAndFill(
										resultset.getString("DOBNUMBER"), "-"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("MODEL"), "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put(
						"HROFOPERATION",
						checkNullAndFill(resultset.getString("HROFOPERATION"),
								"-"));
				hashtable.put(
						"PSHOURSOFOPERATIONYEAR",
						checkNullAndFill(
								resultset.getString("PSHOURSOFOPERATIONYEAR"),
								"-"));
				String dep = checkNullAndFill(
						resultset.getString("PSDEPNUMBER"), "-");
				hashtable.put("PSDEPNUMBER", dep);
				hashtable
						.put("INCLUDEDINDECPERMIT",
								checkNullAndFill(resultset
										.getString("INCLUDEDINDECPERMIT"), "-"));
				String exp = resultset.getString("EXPIRATIONDATE");
				hashtable.put("EXPIRATIONDATE", checkNullAndFilldate(exp, "-"));
				java.util.Date today = new java.util.Date();
				java.util.Date exp_date = null;
				if (UtilityObject.isNotEmpty(exp)) {
					exp_date = UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
				}
				String vocmonthlyquantity = checkNullAndFill(
						resultset.getString("vocmonthlyquantity"), "-");
				String vocmonthlylimit = checkNullAndFill(
						resultset.getString("vocmonthlylimit"), "-");
				String vocmonth = checkNullAndFill(
						resultset.getString("vocmonth"), "-");
				String voccap = checkNullAndFill(resultset.getString("voccap"),
						"-");
				String spraypaintcap = checkNullAndFill(
						resultset.getString("spraypaintcap"), "-");
				String spraysolvantcap = checkNullAndFill(
						resultset.getString("spraysolvantcap"), "-");
				String sprayinkcap = checkNullAndFill(
						resultset.getString("sprayinkcap"), "-");
				String spraypaintdensity = checkNullAndFill(
						resultset.getString("SPRAYPAINTDENSITY"), "-");
				String spraysolvantdensity = checkNullAndFill(
						resultset.getString("SPRAYSOLVANTDENSITY"), "-");
				String sprayinkdensity = checkNullAndFill(
						resultset.getString("SPRAYINKDENSITY"), "-");
				if (!UtilityObject.isNotEmpty(exp) && dep.equals("-")
						&& voccap.equals("-") && vocmonthlyquantity.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (dep.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (!UtilityObject.isNotEmpty(exp)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(exp)
						&& today.compareTo(exp_date) > 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (vocmonthlyquantity.equals("Y") && voccap.equals("Y")) {
					int i = 0;
					int j = 0;
					if (vocmonthlyquantity.equals("Y")) {
						if (vocmonth.equals("-") || vocmonthlylimit.equals("-")) {
							i = 1;
						} else if (Integer.parseInt(vocmonth) > Integer
								.parseInt(vocmonthlylimit)) {
							i = 1;
						}
					}
					if (voccap.equals("Y")) {
						if (!spraypaintcap.equals("-")) {
							if (spraypaintdensity.equals("-")) {
								j = 1;
							} else if (Integer.parseInt(spraypaintdensity) > Integer
									.parseInt(spraypaintcap)) {
								j = 1;
							}
						}
						if (!spraysolvantcap.equals("-")) {
							if (spraysolvantdensity.equals("-")) {
								j = 1;
							} else if (Integer.parseInt(spraysolvantdensity) > Integer
									.parseInt(spraysolvantcap)) {
								j = 1;
							}
						}
						if (!sprayinkcap.equals("-")) {
							if (sprayinkdensity.equals("-")) {
								j = 1;
							} else if (Integer.parseInt(sprayinkdensity) > Integer
									.parseInt(sprayinkcap)) {
								j = 1;
							}
						}
					}
					if (j == 1 || i == 1) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else {
						hashtable.put("COMPLIANCESTATUS", "Compliance");
					}
				} else if (vocmonthlyquantity.equals("Y")) {
					if (vocmonth.equals("-") || vocmonthlylimit.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (Integer.parseInt(vocmonth) > Integer
							.parseInt(vocmonthlylimit)) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					}
				} else if (voccap.equals("Y")) {
					if (!spraypaintcap.equals("-")) {
						if (spraypaintdensity.equals("-")) {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						} else if (Integer.parseInt(spraypaintdensity) > Integer
								.parseInt(spraypaintcap)) {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						}
					}
					if (!spraysolvantcap.equals("-")) {
						if (spraysolvantdensity.equals("-")) {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						} else if (Integer.parseInt(spraysolvantdensity) > Integer
								.parseInt(spraysolvantcap)) {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						}
					}
					if (!sprayinkcap.equals("-")) {
						if (sprayinkdensity.equals("-")) {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						} else if (Integer.parseInt(sprayinkdensity) > Integer
								.parseInt(sprayinkcap)) {
							hashtable.put("COMPLIANCESTATUS", "Non Compliance");
						}
					}
				} else if (voccap.equals("-") || vocmonthlyquantity.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Exception: ")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport18(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.DOBSIGNOFF,s.DOBNUMBER,s.FACILITYDESIGNATEDID,s.OTHERTANKID,s.C"
							+ "APACITY,s.ISSECONDARYCONTAINMENT,b.BUILDINGNAME,s.MONTHLYINSPECTIO"
							+ "N,s.SPILLKIT FROM (hydraulicelevatorothertank s, building b)   WHERE  (s.MODIFIE"
							+ "DINPAST='1' or s.MODIFIEDINPAST='5') and b.BUILDINGID=s.BUILDINGID AND b.FACILIT"
							+ "YID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("OTHERTANKID");
				String listofelevator = getelevator(tempId);
				hashtable.put("LISTOFELEVATOR",
						checkNullAndFill(listofelevator, "-"));
				hashtable.put("OTHERTANKID", tempId);
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put("CAPACITY",
						checkNullAndFill(resultset.getString("CAPACITY"), "-"));

				String issecondarycontainment = checkNullAndFill(
						resultset.getString("ISSECONDARYCONTAINMENT"), "-");
				hashtable.put("ISSECONDARYCONTAINMENT", issecondarycontainment);

				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable
						.put("DOBNUMBER",
								checkNullAndFill(
										resultset.getString("DOBNUMBER"), "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				// String decapproval =
				// checkNullAndFill(resultset.getString("DECAPPROVAL"), "-");
				/*
				 * String pbsno = checkNullAndFill(resultset.getString("PBSNO"),
				 * "-"); hashtable.put("PBSNO", pbsno);
				 */
				// hashtable.put("DECAPPROVAL", decapproval);
				String monthlyinspection = checkNullAndFill(
						resultset.getString("MONTHLYINSPECTION"), "-");
				hashtable.put("MONTHLYINSPECTION", monthlyinspection);

				String spillkit = checkNullAndFill(
						resultset.getString("SPILLKIT"), "-");
				hashtable.put("SPILLKIT", spillkit);
				// hashtable.put("SPILLKIT",
				// checkNullAndFill(resultset.getString("SPILLKIT"), "-"));

				if (spillkit.equals("-") && monthlyinspection.equals("-")) {
					hashtable.put("compliance", "TBD");
				} else if (monthlyinspection.equals("Y")
						&& spillkit.equals("-")) {
					hashtable.put("compliance", "Non Compliance");
				} else if (monthlyinspection.equals("N")
						&& spillkit.equals("-")) {
					hashtable.put("compliance", "Non Compliance");
				} else if (monthlyinspection.equals("-")
						&& spillkit.equals("Y")) {
					hashtable.put("compliance", "Non Compliance");
				} else if (monthlyinspection.equals("-")
						&& spillkit.equals("N")) {
					hashtable.put("compliance", "Non Compliance");
				} else if (monthlyinspection.equals("N")
						&& spillkit.equals("N")) {
					hashtable.put("compliance", "Non Compliance");
				} else if (monthlyinspection.equals("Y")
						&& spillkit.equals("N")) {
					hashtable.put("compliance", "Non Compliance");
				} else if (monthlyinspection.equals("N")
						&& spillkit.equals("Y")) {
					hashtable.put("compliance", "Non Compliance");
				} else {
					hashtable.put("compliance", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport19(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.CAPACITY,s.PRESSURETEST,s.FACILITYDESIGNATEDID,s.BULKOXYGENTANKID,s.F"
							+ "IREDEPTAPPROVAL,s.FIREDEPTAPPROVALNO,b.BUILDINGNAME,s.LASTTESTDATE,s.NEXTTESTDAT"
							+ "E FROM (bulkoxygentank s, building b)  WHERE  (s.MODIFIEDINPAST='1' or s.MODIFIE"
							+ "DINPAST='5') and b.BUILDINGID=s.BUILDINGID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("BULKOXYGENTANKID");
				hashtable
						.put("BULKOXYGENTANKID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));

				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put("CAPACITY",
						checkNullAndFill(resultset.getString("CAPACITY"), "-"));
				// hashtable.put("DOBSIGNOFF",
				// checkNullAndFill(resultset.getString("DOBSIGNOFF"), "-"));
				String firedept = checkNullAndFill(
						resultset.getString("FIREDEPTAPPROVAL"), "-");
				hashtable.put("FIREDEPTAPPROVAL", firedept);

				String firedeptno = resultset.getString("FIREDEPTAPPROVALNO");

				if (!firedept.equalsIgnoreCase("NA")) {
					hashtable.put("FIREDEPTAPPROVALNO",
							UtilityObject.checkNullAndFill(firedeptno, "-"));
				} else {
					hashtable.put("FIREDEPTAPPROVALNO", "N/A");
				}

				/*
				 * String lasttest = resultset.getString("LASTTESTDATE");
				 * 
				 * if(!pressuretest.equalsIgnoreCase("NA")){
				 * hashtable.put("LASTTESTDATE", checkNullAndFilldate(lasttest,
				 * "-")); hashtable.put("NEXTTESTDATE",
				 * checkNullAndFilldate(resultset.getString("NEXTTESTDATE"),
				 * "-")); }else{ hashtable.put("LASTTESTDATE","N/A");
				 * hashtable.put("NEXTTESTDATE","N/A"); }
				 */

				String pressuretest = checkNullAndFill(
						resultset.getString("PRESSURETEST"), "-");
				hashtable.put("PRESSURETEST", pressuretest);
				String lasttest = resultset.getString("LASTTESTDATE");
				String nexttest = resultset.getString("NEXTTESTDATE");

				if (pressuretest.equalsIgnoreCase("Y")) {
					hashtable.put("LASTTESTDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(lasttest), "-"));
					hashtable.put("NEXTTESTDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(nexttest), "-"));
				} else if (pressuretest.equalsIgnoreCase("N")) {
					hashtable.put("LASTTESTDATE", "-");
					hashtable.put("NEXTTESTDATE", "-");
				} else {
					hashtable.put("LASTTESTDATE", "N/A");
					hashtable.put("NEXTTESTDATE", "N/A");
				}

				int j_1 = 0;

				try {

					java.util.Date today = new java.util.Date();
					java.util.Date next = null;
					if (nexttest == null) {
						j_1 = 0;
					} else {
						next = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(nexttest);
					}

					if (next == null) {
						j_1 = 0;
					} else if (next != null) {

						j_1 = today.compareTo(next);
						if (j_1 > 0) {
							j_1 = 0;
						} else {
							j_1 = 1;
						}
					} else {
						j_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 19 :" + e);
				}

				java.util.Date today = new java.util.Date();
				java.util.Date last_date = null;
				if (UtilityObject.isNotEmpty(lasttest)) {
					last_date = UtilityObject
							.convert_YyyyMmDd2MmDdYyyy(lasttest);
				}
				if (firedept.equals("-") && firedeptno.equals("-")
						&& pressuretest.equals("-")
						&& !UtilityObject.isNotEmpty(lasttest)) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (firedept.equals("N") || firedeptno.equals("-")
						&& !firedept.equalsIgnoreCase("NA")) // if(firedept.equals("N")
																// ||
																// firedeptno.equals("-"))
				{
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (firedept.equals("-") || pressuretest.equals("-")
						&& !pressuretest.equalsIgnoreCase("NA"))// if(firedept.equals("-")
																// ||
																// pressuretest.equals("-"))
				{
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (pressuretest.equals("Y") && j_1 == 0) // &&
																	// !firedept.equalsIgnoreCase("NA")
				{
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (pressuretest.equals("N")) // &&
														// !firedept.equalsIgnoreCase("NA")
				{
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	/*
	 * public static List getNycdobStatusReport19(int facid) { LinkedHashMap
	 * hash4Unique = new LinkedHashMap(); SqlUtil sqlutil = new SqlUtil();
	 * ArrayList arraylist = new ArrayList(); SqlUtil sqlutil1 = new SqlUtil();
	 * Object obj = null; Object obj1 = null; Object obj2 = null; Connection
	 * connection = null; ResultSet resultset = null; PreparedStatement
	 * preparedstatement = null;
	 * 
	 * try { connection = sqlutil.getConnection(); preparedstatement =
	 * connection.prepareStatement(
	 * "SELECT s.DOBSIGNOFF,s.PRESSURETEST,s.FACILITYDESIGNATEDID,s.BULKOXYGENTANKID,s.F"
	 * +
	 * "IREDEPTAPPROVAL,s.FIREDEPTAPPROVALNO,b.BUILDINGNAME,s.LASTTESTDATE,s.NEXTTESTDAT"
	 * +
	 * "E FROM (bulkoxygentank s, building b)  WHERE  (s.MODIFIEDINPAST='1' or s.MODIFIE"
	 * + "DINPAST='5') and b.BUILDINGID=s.BUILDINGID AND b.FACILITYID =?" );
	 * preparedstatement.setString(1, String.valueOf(facid)); resultset =
	 * preparedstatement.executeQuery(); String tempId = ""; Hashtable
	 * hashtable; for(; resultset.next(); hash4Unique.put(tempId, hashtable)) {
	 * hashtable = new Hashtable(); tempId =
	 * resultset.getString("BULKOXYGENTANKID");
	 * hashtable.put("BULKOXYGENTANKID", checkNullAndFill(tempId, "-"));
	 * hashtable.put("FACILITYDESIGNATEDID",
	 * checkNullAndFill(resultset.getString("FACILITYDESIGNATEDID"), "-"));
	 * String firedept =
	 * checkNullAndFill(resultset.getString("FIREDEPTAPPROVAL"), "-"); String
	 * firedeptno = checkNullAndFill(resultset.getString("FIREDEPTAPPROVALNO"),
	 * "-"); String pressuretest =
	 * checkNullAndFill(resultset.getString("PRESSURETEST"), "-");
	 * hashtable.put("FIREDEPTAPPROVAL", firedept);
	 * hashtable.put("FIREDEPTAPPROVALNO", firedeptno);
	 * hashtable.put("BUILDINGNAME",
	 * checkNullAndFill(resultset.getString("BUILDINGNAME"), "-"));
	 * hashtable.put("PRESSURETEST", pressuretest);
	 * hashtable.put("NEXTTESTDATE",
	 * checkNullAndFilldate(resultset.getString("NEXTTESTDATE"), "-"));
	 * hashtable.put("DOBSIGNOFF",
	 * checkNullAndFill(resultset.getString("DOBSIGNOFF"), "-")); String
	 * lasttest = resultset.getString("LASTTESTDATE");
	 * hashtable.put("LASTTESTDATE", checkNullAndFilldate(lasttest, "-"));
	 * java.util.Date today = new java.util.Date(); java.util.Date last_date =
	 * null; if(UtilityObject.isNotEmpty(lasttest)) { last_date =
	 * UtilityObject.convert_YyyyMmDd2MmDdYyyy(lasttest); }
	 * if(firedept.equals("-") && firedeptno.equals("-") &&
	 * pressuretest.equals("-") && !UtilityObject.isNotEmpty(lasttest)) {
	 * hashtable.put("COMPLIANCESTATUS", "TBD"); } else if(firedept.equals("N")
	 * || firedeptno.equals("-") && !firedept.equalsIgnoreCase("NA")) //
	 * if(firedept.equals("N") || firedeptno.equals("-")) {
	 * hashtable.put("COMPLIANCESTATUS", "Non Compliance"); } else
	 * if(firedept.equals("-") || pressuretest.equals("-") &&
	 * !pressuretest.equalsIgnoreCase("NA"))//if(firedept.equals("-") ||
	 * pressuretest.equals("-")) { hashtable.put("COMPLIANCESTATUS", "TBD"); }
	 * else if(UtilityObject.isNotEmpty(lasttest) && today.compareTo(last_date)
	 * < 0 && !firedept.equalsIgnoreCase("NA")) // &&
	 * !firedept.equalsIgnoreCase("NA") { hashtable.put("COMPLIANCESTATUS",
	 * "Non Compliance"); } else { hashtable.put("COMPLIANCESTATUS",
	 * "Compliance"); } }
	 * 
	 * } catch(Exception exception) { System.out.println((new
	 * StringBuilder()).append("hai").append(exception).toString()); }
	 * if(hash4Unique != null) { Set set = hash4Unique.keySet(); for(Iterator
	 * iter = set.iterator(); iter.hasNext();
	 * arraylist.add(hash4Unique.get(iter.next()))) { } } return arraylist; }
	 */
	public static List getNycdobStatusReport20(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.DOBSIGNOFF,s.SOLVANTCAP,b.FACILITYID,s.FACILITYDESIGNATEDID,s.pressid,s"
							+ ".DECPERMIT,s.DOBPERMIT,b.BUILDINGNAME,s.DEPNUMBER,s.DEPEXPIRATIONDATE,s.ANNUALCO"
							+ "NSUMPTION,s.TOTALVOC,s.INK1NAME,s.INK2NAME,s.INK3NAME,s.INK4NAME,s.FOUNTAINSOLUT"
							+ "IONNAME,s.CLEANINGAGENTNAME,s.INK1VOLUME,s.INK2VOLUME,s.INK3VOLUME,s.INK4VOLUME,"
							+ "s.FOUNTAINSOLUTIONVOLUME,s.CLEANINGAGENTVOLUME,s.INK1DENSITY,s.INK2DENSITY,s.INK"
							+ "3DENSITY,s.INK4DENSITY,s.FOUNTAINSOLUTIONDENSITY,s.CLEANINGAGENTDENSITY,s.INK1VO"
							+ "CPERCENT,s.INK2VOCPERCENT,s.INK3VOCPERCENT,s.INK4VOCPERCENT,s.FOUNTAINSOLUTIONVO"
							+ "CPERCENT,s.CLEANINGAGENTVOCPERCENT,s.INK1VOC,s.INK2VOC,s.INK3VOC,s.INK4VOC,s.FOU"
							+ "NTAINSOLUTIONVOC,s.CLEANINGAGENTVOC FROM (press s, building b)  WHERE  (s.MODIFI"
							+ "EDINPAST='1' or s.MODIFIEDINPAST='5') and b.BUILDINGID=s.BUILDINGID AND b.FACILI"
							+ "TYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			String temp_Id = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(temp_Id, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("FACILITYID");
				temp_Id = resultset.getString("pressid");
				hashtable.put("FACILITYID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put("pressid",
						checkNullAndFill(resultset.getString("pressid"), "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				// hashtable.put("DECPERMIT",
				// checkNullAndFill(resultset.getString("DECPERMIT"), "-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put("INK1NAME",
						checkNullAndFill(resultset.getString("INK1NAME"), "-"));
				hashtable.put("INK2NAME",
						checkNullAndFill(resultset.getString("INK2NAME"), "-"));
				hashtable.put("INK3NAME",
						checkNullAndFill(resultset.getString("INK3NAME"), "-"));
				hashtable.put("INK4NAME",
						checkNullAndFill(resultset.getString("INK4NAME"), "-"));
				hashtable.put(
						"FOUNTAINSOLUTIONNAME",
						checkNullAndFill(
								resultset.getString("FOUNTAINSOLUTIONNAME"),
								"-"));
				hashtable.put(
						"CLEANINGAGENTNAME",
						checkNullAndFill(
								resultset.getString("CLEANINGAGENTNAME"), "-"));
				hashtable
						.put("INK1VOLUME",
								checkNullAndFill(
										resultset.getString("INK1VOLUME"), "-"));
				hashtable
						.put("INK2VOLUME",
								checkNullAndFill(
										resultset.getString("INK2VOLUME"), "-"));
				hashtable
						.put("INK3VOLUME",
								checkNullAndFill(
										resultset.getString("INK3VOLUME"), "-"));
				hashtable
						.put("INK4VOLUME",
								checkNullAndFill(
										resultset.getString("INK4VOLUME"), "-"));
				hashtable.put(
						"FOUNTAINSOLUTIONVOLUME",
						checkNullAndFill(
								resultset.getString("FOUNTAINSOLUTIONVOLUME"),
								"-"));
				hashtable
						.put("CLEANINGAGENTVOLUME",
								checkNullAndFill(resultset
										.getString("CLEANINGAGENTVOLUME"), "-"));
				hashtable.put(
						"INK1DENSITY",
						checkNullAndFill(resultset.getString("INK1DENSITY"),
								"-"));
				hashtable.put(
						"INK2DENSITY",
						checkNullAndFill(resultset.getString("INK2DENSITY"),
								"-"));
				hashtable.put(
						"INK3DENSITY",
						checkNullAndFill(resultset.getString("INK3DENSITY"),
								"-"));
				hashtable.put(
						"INK4DENSITY",
						checkNullAndFill(resultset.getString("INK4DENSITY"),
								"-"));
				hashtable.put(
						"FOUNTAINSOLUTIONDENSITY",
						checkNullAndFill(
								resultset.getString("FOUNTAINSOLUTIONDENSITY"),
								"-"));
				hashtable.put(
						"CLEANINGAGENTDENSITY",
						checkNullAndFill(
								resultset.getString("CLEANINGAGENTDENSITY"),
								"-"));
				hashtable.put(
						"INK1VOCPERCENT",
						checkNullAndFill(resultset.getString("INK1VOCPERCENT"),
								"-"));
				hashtable.put(
						"INK2VOCPERCENT",
						checkNullAndFill(resultset.getString("INK2VOCPERCENT"),
								"-"));
				hashtable.put(
						"INK3VOCPERCENT",
						checkNullAndFill(resultset.getString("INK3VOCPERCENT"),
								"-"));
				hashtable.put(
						"INK4VOCPERCENT",
						checkNullAndFill(resultset.getString("INK4VOCPERCENT"),
								"-"));
				hashtable.put(
						"FOUNTAINSOLUTIONVOCPERCENT",
						checkNullAndFill(resultset
								.getString("FOUNTAINSOLUTIONVOCPERCENT"), "-"));
				hashtable.put(
						"CLEANINGAGENTVOCPERCENT",
						checkNullAndFill(
								resultset.getString("CLEANINGAGENTVOCPERCENT"),
								"-"));
				hashtable.put("INK1VOC",
						checkNullAndFill(resultset.getString("INK1VOC"), "-"));
				hashtable.put("INK2VOC",
						checkNullAndFill(resultset.getString("INK2VOC"), "-"));
				hashtable.put("INK3VOC",
						checkNullAndFill(resultset.getString("INK3VOC"), "-"));
				hashtable.put("INK4VOC",
						checkNullAndFill(resultset.getString("INK4VOC"), "-"));
				hashtable
						.put("FOUNTAINSOLUTIONVOC",
								checkNullAndFill(resultset
										.getString("FOUNTAINSOLUTIONVOC"), "-"));
				hashtable.put(
						"CLEANINGAGENTVOC",
						checkNullAndFill(
								resultset.getString("CLEANINGAGENTVOC"), "-"));

				// for overall compliance report
				/*
				 * String
				 * dobpermit=checkNullAndFill(resultset.getString("DOBPERMIT"),
				 * "-"); hashtable.put("DOBPERMIT",dobpermit); String
				 * depnumber=checkNullAndFill(resultset.getString("DEPNUMBER"),
				 * "-"); hashtable.put("DEPNUMBER",depnumber);
				 */

				String annualconsumption = checkNullAndFill(
						resultset.getString("ANNUALCONSUMPTION"), "-");
				String totalvoc = checkNullAndFill(
						resultset.getString("TOTALVOC"), "-");
				String solvantcap = checkNullAndFill(
						resultset.getString("SOLVANTCAP"), "-");

				hashtable.put("ANNUALCONSUMPTION", annualconsumption);
				hashtable.put("TOTALVOC", totalvoc);
				hashtable.put("SOLVANTCAP", solvantcap);

				if (solvantcap.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				}/*
				 * else if(solvantcap.equals("Y") &&
				 * !annualconsumption.equals("-") && !totalvoc.equals("-")) {
				 * hashtable.put("COMPLIANCESTATUS", "Non Compliance"); }
				 */else if (solvantcap.equals("Y")
						&& !annualconsumption.equals("-")
						&& totalvoc.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (solvantcap.equals("-")
						|| annualconsumption.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Exception : ")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		System.out.println("OOOOOOOOOOOOOOOOOOPPPPPPPPPPPPPPPPQQQQQQQQQQQ:"
				+ arraylist.size());

		return arraylist;
	}

	public static List getNycdobStatusReport36(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.DOBSIGNOFF,b.FACILITYID,s.FACILITYDESIGNATEDID,s.pressid,s"
							+ ".DECPERMIT,s.DOBPERMIT,b.BUILDINGNAME,s.DEPNUMBER,s.DEPEXPIRATIONDATE FROM (press s, building b)  WHERE  (s.MODIFI"
							+ "EDINPAST='1' or s.MODIFIEDINPAST='5') and b.BUILDINGID=s.BUILDINGID AND b.FACILI"
							+ "TYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			String temp_Id = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(temp_Id, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("FACILITYID");
				temp_Id = resultset.getString("pressid");
				hashtable.put("FACILITYID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put("pressid",
						checkNullAndFill(resultset.getString("pressid"), "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable
						.put("DECPERMIT",
								checkNullAndFill(
										resultset.getString("DECPERMIT"), "-"));

				String dobpermit = checkNullAndFill(
						resultset.getString("DOBPERMIT"), "-");
				String depnumber = checkNullAndFill(
						resultset.getString("DEPNUMBER"), "-");

				hashtable.put("DOBPERMIT", dobpermit);
				hashtable.put("DEPNUMBER", depnumber);

				String exp = resultset.getString("DEPEXPIRATIONDATE");
				hashtable.put("DEPEXPIRATIONDATE",
						checkNullAndFilldate(exp, "-"));
				java.util.Date today = new java.util.Date();
				java.util.Date exp_date = null;
				if (UtilityObject.isNotEmpty(exp)) {
					exp_date = UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
				}

				if (!UtilityObject.isNotEmpty(exp) && depnumber.equals("-")
						&& dobpermit.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (!UtilityObject.isNotEmpty(exp)
						|| dobpermit.equals("-") || depnumber.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (UtilityObject.isNotEmpty(exp)
						&& today.compareTo(exp_date) > 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Exception : ")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		System.out.println("OOOOOOOOOOOOOOOOOOPPPPPPPPPPPPPPPPAAAAAAAAAAAA:"
				+ arraylist.size());

		return arraylist;
	}

	public static List getNycdobStatusReport37(int facid) {
		String arrr[] = { "", "Auditorium", "Cafeteria", "Conference Hall",
				"Other" };
		LinkedHashMap hash4Unique;
		hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT b.BUILDINGNAME,s.FACILITYDESIGNATEDID,s.DOBPERMIT,s.PLACEOFASSEMBLYID,s.PATYPE,s.LOCATION,s.SEATINGCAPACITY,s.DOBFILING,s.DOBPLAN,s.DOBPERMITNUMBER,s.FDPERMITOBTAINED,s.OPENVIOLATION,s.VIOLATIONNUM FROM (placeofassembly s, building b)"
							+ " WHERE   b.BUILDINGID=s.BUILDINGID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("FACILITYDESIGNATEDID");
				hashtable.put("FACILITYDESIGNATEDID",
						checkNullAndFill(tempId, "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));

				String patype = resultset.getString("PATYPE");
				hashtable.put(
						"PATYPE",
						checkNullAndFill(arrr[Integer.parseInt(patype)] + "",
								"-"));
				hashtable.put("LOCATION",
						checkNullAndFill(resultset.getString("LOCATION"), "-"));
				hashtable.put(
						"SEATINGCAPACITY",
						checkNullAndFill(
								resultset.getString("SEATINGCAPACITY"), "-"));

				String dobfiling = checkNullAndFill(
						resultset.getString("DOBFILING"), "-");
				String dobpermit = resultset.getString("DOBPERMIT");
				String dobplan = resultset.getString("DOBPLAN");
				String dobpermitnumber = resultset.getString("DOBPERMITNUMBER");

				if (dobfiling.equalsIgnoreCase("Y")) {
					hashtable
							.put("DOBPERMIT", checkNullAndFill(dobpermit, "-"));
					hashtable.put("DOBPLAN", checkNullAndFill(dobplan, "-"));
					hashtable.put("DOBPERMITNUMBER",
							checkNullAndFill(dobpermitnumber, "-"));
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("DOBPERMIT", "-");
					hashtable.put("DOBPLAN", "-");
					hashtable.put("DOBPERMITNUMBER", "-");
				} else if (dobfiling.equalsIgnoreCase("N/A")) {
					hashtable.put("DOBPERMIT", "N/A");
					hashtable.put("DOBPLAN", "N/A");
					hashtable.put("DOBPERMITNUMBER", "N/A");
				} else {
					hashtable.put("DOBPERMIT", "-");
					hashtable.put("DOBPLAN", "-");
					hashtable.put("DOBPERMITNUMBER", "-");
				}

				String fdapproval = resultset.getString("FDPERMITOBTAINED");
				hashtable.put("FDPERMITOBTAINED",
						checkNullAndFill(fdapproval, "-"));

				String openViolation = checkNullAndFill(
						resultset.getString("OPENVIOLATION"), "-");
				String violationnum = resultset.getString("VIOLATIONNUM");

				if (openViolation.equalsIgnoreCase("Y")) {
					hashtable.put("VIOLATIONNUM",
							checkNullAndFill(violationnum, "-"));
				} else if (openViolation.equalsIgnoreCase("N")) {
					hashtable.put("VIOLATIONNUM", "N/A");
				} else {
					hashtable.put("VIOLATIONNUM", "-");
				}

				String COMPLIANCESTATUS = "";

				if (dobfiling.equalsIgnoreCase("-")
						&& openViolation.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("Y")
						&& dobpermit.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("Y")
						&& dobplan.equals("N")
						|| dobfiling.equalsIgnoreCase("Y")
						&& dobplan.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("Y")
						&& dobpermitnumber.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (fdapproval.equalsIgnoreCase("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (fdapproval.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (openViolation.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (openViolation.equalsIgnoreCase("Y")
						&& violationnum.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}

			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}

		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next())))
				;
		}

		return arraylist;
	}

	/*
	 * public static List getNycdobStatusReport37(int facid) { LinkedHashMap
	 * hash4Unique = new LinkedHashMap(); SqlUtil sqlutil = new SqlUtil();
	 * ArrayList arraylist = new ArrayList(); SqlUtil sqlutil1 = new SqlUtil();
	 * Object obj = null; Object obj1 = null; Object obj2 = null; Connection
	 * connection = null; ResultSet resultset = null; PreparedStatement
	 * preparedstatement = null;
	 * 
	 * try { connection = sqlutil.getConnection(); preparedstatement =
	 * connection.prepareStatement(
	 * "SELECT s.tanktype,b.FACILITYID,s.FACILITYDESIGNATEDID,s.storagetankid,s"
	 * +
	 * ".capacity,s.yearinstalled,b.BUILDINGNAME,s.tanklocation,s.productstored,s.tankbaselocation,s.FUELSUPPLIEDTO FROM (storagetank s, building b)  WHERE  (s.MODIFI"
	 * +
	 * "EDINPAST='1' or s.MODIFIEDINPAST='5') and b.BUILDINGID=s.BUILDINGID AND b.FACILI"
	 * + "TYID =?" ); preparedstatement.setString(1, String.valueOf(facid));
	 * resultset = preparedstatement.executeQuery(); String tempId = ""; String
	 * temp_Id = ""; Hashtable hashtable; for(; resultset.next();
	 * hash4Unique.put(temp_Id, hashtable)) { hashtable = new Hashtable();
	 * tempId = resultset.getString("FACILITYID"); temp_Id =
	 * resultset.getString("storagetankid"); hashtable.put("FACILITYID",
	 * checkNullAndFill(tempId, "-")); hashtable.put("BUILDINGNAME",
	 * checkNullAndFill(resultset.getString("BUILDINGNAME"), "-"));
	 * hashtable.put("storagetankid",
	 * checkNullAndFill(resultset.getString("storagetankid"), "-"));
	 * hashtable.put("FACILITYDESIGNATEDID",
	 * checkNullAndFill(resultset.getString("FACILITYDESIGNATEDID"), "-"));
	 * hashtable.put("tanklocation",
	 * checkNullAndFill(resultset.getString("tanklocation"), "-"));
	 * hashtable.put("yearinstalled",
	 * checkNullAndFill(resultset.getString("yearinstalled"), "-"));
	 * hashtable.put("capacity",
	 * checkNullAndFill(resultset.getString("capacity"), "-"));
	 * hashtable.put("tanktype",
	 * checkNullAndFill(resultset.getString("tanktype"), "-"));
	 * hashtable.put("productstored",
	 * checkNullAndFill(resultset.getString("productstored"), "-"));
	 * hashtable.put("FUELSUPPLIEDTO",
	 * checkNullAndFill(resultset.getString("FUELSUPPLIEDTO"), "-"));
	 * hashtable.put("tankbaselocation",
	 * checkNullAndFill(resultset.getString("tankbaselocation"), "-"));
	 */

	/*
	 * String dobpermit = checkNullAndFill(resultset.getString("DOBPERMIT"),
	 * "-"); String depnumber =
	 * checkNullAndFill(resultset.getString("DEPNUMBER"), "-");
	 * 
	 * hashtable.put("DOBPERMIT", dobpermit); hashtable.put("DEPNUMBER",
	 * depnumber);
	 */

	/*
	 * String exp = resultset.getString("DEPEXPIRATIONDATE");
	 * hashtable.put("DEPEXPIRATIONDATE", checkNullAndFilldate(exp, "-"));
	 * java.util.Date today = new java.util.Date(); java.util.Date exp_date =
	 * null; if(UtilityObject.isNotEmpty(exp)) { exp_date =
	 * UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp); }
	 * 
	 * 
	 * if(!UtilityObject.isNotEmpty(exp) && depnumber.equals("-") &&
	 * dobpermit.equals("-")) { hashtable.put("COMPLIANCESTATUS", "TBD"); } else
	 * if(!UtilityObject.isNotEmpty(exp)||dobpermit.equals("-") ||
	 * depnumber.equals("-")) { hashtable.put("COMPLIANCESTATUS",
	 * "Non Compliance"); } else if(UtilityObject.isNotEmpty(exp) &&
	 * today.compareTo(exp_date) > 0) { hashtable.put("COMPLIANCESTATUS",
	 * "Non Compliance"); }else { hashtable.put("COMPLIANCESTATUS",
	 * "Compliance"); }
	 */
	/*
	 * }
	 * 
	 * } catch(Exception exception) { System.out.println((new
	 * StringBuilder()).append("Exception : ").append(exception).toString()); }
	 * finally { SqlUtil.close(resultset); SqlUtil.close(preparedstatement);
	 * SqlUtil.close(connection); } if(hash4Unique != null) { Set set =
	 * hash4Unique.keySet(); for(Iterator iter = set.iterator(); iter.hasNext();
	 * arraylist.add(hash4Unique.get(iter.next()))) { } }
	 * System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA:"
	 * +arraylist.size());
	 * 
	 * return arraylist; }
	 */

	public static List getNycdobStatusReport21(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT lp.EXPIRATIONDATE,s.LFDEPNUMBER,s.LFDOBNUMBER,s.dobsignoff,s.FACILITYDESI"
							+ "GNATEDID,s.LANDFILLSID,s.COMMENCEDDATE,s.LFGQUANTITY,b.BUILDINGNAME,s.LFGCOMBUST"
							+ "ION,s.SVALUE,s.SALLOWABLEVALUE,s.CH4VALUE,s.CH4ALLOWABLEVALUE,s.LFSCH4,s.ISTEMPE"
							+ "RATURELIMIT,s.TEMPERATUREVALUE,s.TEMPERATUREALLOWABLEVALUE,s.NOXVALUE,s.NOXALLOW"
							+ "ABLEVALUE,s.COVALUE,s.COALLOWABLEVALUE,s.OVALUE,s.OALLOWABLEVALUE,s.YEARLYOPERAT"
							+ "ING,s.YEARLYVALUE,s.YEARLYALLOWABLEVALUE,s.AESDATE FROM (landfills s, building b"
							+ ") left join landfillpermitinfo lp on lp.LANDFILLSID=s.LANDFILLSID and lp.DEPID='"
							+ "2'  WHERE  (s.STATUS='1' or s.STATUS='5') and b.BUILDINGID=s.BUILDINGID AND b.FA"
							+ "CILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("LANDFILLSID");
				hashtable.put("LANDFILLSID", tempId);
				hashtable.put(
						"LFDEPNUMBER",
						checkNullAndFill(resultset.getString("LFDEPNUMBER"),
								"-"));
				hashtable.put(
						"LFDOBNUMBER",
						checkNullAndFill(resultset.getString("LFDOBNUMBER"),
								"-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put(
						"EXPIRATIONDATE",
						checkNullAndFilldate(
								resultset.getString("EXPIRATIONDATE"), "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"COMMENCEDDATE",
						checkNullAndFilldate(
								resultset.getString("COMMENCEDDATE"), "-"));
				hashtable.put(
						"AESDATE",
						checkNullAndFilldate(resultset.getString("AESDATE"),
								"-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put(
						"LFGQUANTITY",
						checkNullAndFill(resultset.getString("LFGQUANTITY"),
								"-"));
				String lfgcombustion = checkNullAndFill(
						resultset.getString("LFGCOMBUSTION"), "-");
				if (!lfgcombustion.equals("-")) {
					hashtable.put("LFGCOMBUSTION", (new StringBuilder())
							.append(Integer.parseInt(lfgcombustion) * 8760)
							.append("").toString());
				} else {
					hashtable.put("LFGCOMBUSTION", lfgcombustion);
				}
				String svalue = checkNullAndFill(resultset.getString("SVALUE"),
						"-");
				String sallowablevalue = checkNullAndFill(
						resultset.getString("SALLOWABLEVALUE"), "-");
				String ch4value = checkNullAndFill(
						resultset.getString("CH4VALUE"), "-");
				String ch4allowablevalue = checkNullAndFill(
						resultset.getString("CH4ALLOWABLEVALUE"), "-");
				String lfsch4 = checkNullAndFill(resultset.getString("LFSCH4"),
						"-");
				String istemperaturelimit = checkNullAndFill(
						resultset.getString("ISTEMPERATURELIMIT"), "-");
				String temperaturevalue = checkNullAndFill(
						resultset.getString("TEMPERATUREVALUE"), "-");
				String temperatureallowablevalue = checkNullAndFill(
						resultset.getString("TEMPERATUREALLOWABLEVALUE"), "-");
				String noxvalue = checkNullAndFill(
						resultset.getString("NOXVALUE"), "-");
				String noxallowablevalue = checkNullAndFill(
						resultset.getString("NOXALLOWABLEVALUE"), "-");
				String covalue = checkNullAndFill(
						resultset.getString("COVALUE"), "-");
				String coallowablevalue = checkNullAndFill(
						resultset.getString("COALLOWABLEVALUE"), "-");
				String ovalue = checkNullAndFill(resultset.getString("OVALUE"),
						"-");
				String oallowablevalue = checkNullAndFill(
						resultset.getString("OALLOWABLEVALUE"), "-");
				String yearlyoperating = checkNullAndFill(
						resultset.getString("YEARLYOPERATING"), "-");
				String yearlyvalue = checkNullAndFill(
						resultset.getString("YEARLYVALUE"), "-");
				String yearlyallowablevalue = checkNullAndFill(
						resultset.getString("YEARLYALLOWABLEVALUE"), "-");
				hashtable.put("SVALUE", (new StringBuilder()).append(svalue)
						.append(" / ").append(sallowablevalue).toString());
				hashtable.put("CH4VALUE",
						(new StringBuilder()).append(ch4value).append(" / ")
								.append(ch4allowablevalue).toString());
				hashtable.put("YEARLYVALUE", yearlyvalue);
				hashtable.put("NOXVALUE", noxvalue);
				hashtable.put("COVALUE", covalue);
				hashtable.put("OVALUE", ovalue);
				hashtable.put("TEMPERATUREVALUE", ovalue);
				if (lfsch4.equals("-") && yearlyoperating.equals("-")
						&& oallowablevalue.equals("-")
						&& noxallowablevalue.equals("-")
						&& coallowablevalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (lfsch4.equals("Y")
						&& !svalue.equals("-")
						&& !sallowablevalue.equals("-")
						&& Integer.parseInt(svalue) > Integer
								.parseInt(sallowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (lfsch4.equals("Y") && !sallowablevalue.equals("-")
						&& svalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (lfsch4.equals("Y")
						&& !ch4value.equals("-")
						&& !ch4allowablevalue.equals("-")
						&& Integer.parseInt(ch4value) > Integer
								.parseInt(ch4allowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (lfsch4.equals("Y") && !ch4allowablevalue.equals("-")
						&& ch4value.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (istemperaturelimit.equals("Y")
						&& !temperaturevalue.equals("-")
						&& !temperatureallowablevalue.equals("-")
						&& Integer.parseInt(temperaturevalue) > Integer
								.parseInt(temperatureallowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (istemperaturelimit.equals("Y")
						&& !temperatureallowablevalue.equals("-")
						&& temperaturevalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (!noxvalue.equals("-")
						&& !noxallowablevalue.equals("-")
						&& Integer.parseInt(noxvalue) > Integer
								.parseInt(noxallowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (noxvalue.equals("-")
						&& !noxallowablevalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (!covalue.equals("-")
						&& !coallowablevalue.equals("-")
						&& Integer.parseInt(covalue) > Integer
								.parseInt(coallowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (covalue.equals("-") && !coallowablevalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (!ovalue.equals("-")
						&& !oallowablevalue.equals("-")
						&& Integer.parseInt(ovalue) > Integer
								.parseInt(oallowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (ovalue.equals("-") && !oallowablevalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (yearlyoperating.equals("Y")
						&& !yearlyvalue.equals("-")
						&& !yearlyallowablevalue.equals("-")
						&& Integer.parseInt(yearlyvalue) > Integer
								.parseInt(yearlyallowablevalue)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (yearlyoperating.equals("Y")
						&& !yearlyallowablevalue.equals("-")
						&& yearlyvalue.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (oallowablevalue.equals("-")
						|| coallowablevalue.equals("-")
						|| noxallowablevalue.equals("-")
						|| istemperaturelimit.equals("-") || lfsch4.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Compliance ")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport22(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.DOBSIGNOFF,s.othersid,s.FACILITYDESIGNATEDID,b.BUILDINGNAME,s.MISNAME,s"
							+ ".MISMAKE,s.MISMODEL,s.MISCAPACITY,s.MISCAPACITYUNIT,s.MISDEC,s.MISDEP,s.MISDOB,s"
							+ ".MISDOH,s.MISFD,s.MISEXPIRATIONDATE,MISTESTINGREQUIRED,s.MISTESTSUBMITTED FROM ("
							+ "others s, building b)  WHERE  (s.MODIFIEDINPAST='1' or s.MODIFIEDINPAST='5') and"
							+ " b.BUILDINGID=s.BUILDINGID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("othersid");
				hashtable.put("OTHERSID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put("MISNAME",
						checkNullAndFill(resultset.getString("MISNAME"), "-"));
				hashtable.put("MISMAKE",
						checkNullAndFill(resultset.getString("MISMAKE"), "-"));
				hashtable
						.put("DOBSIGNOFF",
								checkNullAndFill(
										resultset.getString("DOBSIGNOFF"), "-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put("MISMODEL",
						checkNullAndFill(resultset.getString("MISMODEL"), "-"));
				hashtable
						.put("MISCAPACITY",
								(new StringBuilder())
										.append(checkNullAndFill(resultset
												.getString("MISCAPACITY"), "-"))
										.append(" ")
										.append(checkNullAndFill(resultset
												.getString("MISCAPACITYUNIT"),
												"")).toString());
				hashtable.put("MISDEC",
						checkNullAndFill(resultset.getString("MISDEC"), "-"));
				hashtable.put("MISDEP",
						checkNullAndFill(resultset.getString("MISDEP"), "-"));
				hashtable.put("MISDOB",
						checkNullAndFill(resultset.getString("MISDOB"), "-"));
				hashtable.put("MISDOH",
						checkNullAndFill(resultset.getString("MISDOH"), "-"));
				hashtable.put("MISFD",
						checkNullAndFill(resultset.getString("MISFD"), "-"));
				String exp = resultset.getString("MISEXPIRATIONDATE");
				hashtable.put("MISEXPIRATIONDATE",
						checkNullAndFilldate(exp, "-"));
				java.util.Date today = new java.util.Date();
				java.util.Date exp_date = null;
				if (UtilityObject.isNotEmpty(exp)) {
					exp_date = UtilityObject.convert_YyyyMmDd2MmDdYyyy(exp);
				}
				String mistestingrequired = checkNullAndFill(
						resultset.getString("MISTESTINGREQUIRED"), "-");
				String mistestsubmitted = checkNullAndFill(
						resultset.getString("MISTESTSUBMITTED"), "-");
				hashtable.put("MISTESTSUBMITTED", mistestsubmitted);
				if (!UtilityObject.isNotEmpty(exp)
						&& mistestingrequired.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (UtilityObject.isNotEmpty(exp)
						&& today.compareTo(exp_date) > 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (mistestingrequired.equals("Y")
						&& mistestsubmitted.equals("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (mistestingrequired.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (mistestingrequired.equals("Y")
						&& mistestsubmitted.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport23(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.DOBSIGNOFF,s.AREASERVED,s.SERIALNUM,s.EQUIPMENTUSEPERMIT,s.DEPPERMIT,s.DEPEXPIRATIONDATE,s.CHILLERREFRIGATIONID,s.FACILITYDESIGNATEDID,b.BUILDINGNAME"
							+ ",s.MAKE,s.MODEL,s.CAPACITYTONS,s.CAPACITYBTU,s.DOBPERMIT,s.DOBISSUEDATE,s.DOBFILING,s.FUELFIRING,s.DEPSTATUS,s.EPASTA"
							+ "TUS,s.EPASUBMITTALDATE,s.EPAMAINTAINED,s.FDAPPROVAL FROM (chill"
							+ "errefrigation s, building b)  WHERE  (s.STATUS='1' or s.STATUS='5') and b.BUILDI"
							+ "NGID=s.BUILDINGID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("CHILLERREFRIGATIONID");
				hashtable.put("CHILLERREFRIGATIONID",
						checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable
						.put("AREASERVED",
								checkNullAndFill(
										resultset.getString("AREASERVED"), "-"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("MODEL"), "-"));
				hashtable.put("MAKE",
						checkNullAndFill(resultset.getString("MAKE"), "-"));
				hashtable
						.put("SERIALNUM",
								checkNullAndFill(
										resultset.getString("SERIALNUM"), "-"));
				hashtable.put(
						"CAPACITYTONS",
						checkNullAndFill(resultset.getString("CAPACITYTONS"),
								"-"));
				hashtable.put(
						"CAPACITYBTU",
						checkNullAndFill(resultset.getString("CAPACITYBTU"),
								"-"));

				String eupcard = checkNullAndFill(
						resultset.getString("EQUIPMENTUSEPERMIT"), "-");
				hashtable.put("EQUIPMENTUSEPERMIT", eupcard);

				/*
				 * String deppermit =
				 * checkNullAndFill(resultset.getString("DEPPERMIT"), "-");
				 * hashtable.put("DEPPERMIT", deppermit);
				 * 
				 * String depexpirationdate =
				 * checkNullAndFilldate(resultset.getString
				 * ("DEPEXPIRATIONDATE"), "-");
				 * hashtable.put("DEPEXPIRATIONDATE", depexpirationdate);
				 * 
				 * String dobpermit =
				 * checkNullAndFill(resultset.getString("DOBPERMIT"), "-");
				 * hashtable.put("DOBPERMIT", dobpermit);
				 * 
				 * String dobsignoff =
				 * checkNullAndFill(resultset.getString("DOBSIGNOFF"), "-");
				 * hashtable.put("DOBSIGNOFF", dobsignoff);
				 */

				// ******************************DOB
				// Status************************************************//
				String dobfiling = UtilityObject.checkNullAndFill(
						resultset.getString("DOBFILING"), "-");
				String dobpermit = resultset.getString("DOBPERMIT");
				String dobsignoff = resultset.getString("DOBSIGNOFF");

				if (dobfiling.equalsIgnoreCase("Y")) {
					hashtable.put("DOBPERMIT", UtilityObject.checkNullAndFill(
							dobpermit, "No Filing"));
					hashtable.put("DOBSIGNOFF",
							UtilityObject.checkNullAndFill(dobsignoff, "-"));
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("DOBPERMIT", "No Filing");
					hashtable.put("DOBSIGNOFF", "N");
				} else {
					hashtable.put("DOBPERMIT", "N/A");
					hashtable.put("DOBSIGNOFF", "N/A");
				}

				// ******************************DEP
				// Status************************************************//
				String depstatus = UtilityObject.checkNullAndFill(
						resultset.getString("DEPSTATUS"), "-");
				String fuelfiring = UtilityObject.checkNullAndFill(
						resultset.getString("FUELFIRING"), "-");
				String deppermit = resultset.getString("DEPPERMIT");
				String depexpiration = resultset.getString("DEPEXPIRATIONDATE");

				if (depstatus.equalsIgnoreCase("Y")) {
					hashtable.put("DEPPERMIT",
							UtilityObject.checkNullAndFill(deppermit, "-"));
					hashtable.put("DEPEXPIRATIONDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(depexpiration),
									"-"));
				} else if (depstatus.equalsIgnoreCase("N")) {
					hashtable.put("DEPPERMIT", "-");
					hashtable.put("DEPEXPIRATIONDATE", "N/A");
				} else if (fuelfiring.equalsIgnoreCase("N")) {
					hashtable.put("DEPPERMIT", "-");
					hashtable.put("DEPEXPIRATIONDATE", "N/A");
				} else if (depstatus.equalsIgnoreCase("N/A")) {
					hashtable.put("DEPPERMIT", "N/A");
					hashtable.put("DEPEXPIRATIONDATE", "N/A");
				} else {
					hashtable.put("DEPPERMIT", "-");
					hashtable.put("DEPEXPIRATIONDATE", "-");
				}

				int j_1 = 0;

				try {

					java.util.Date today = new java.util.Date();
					java.util.Date expi = null;
					if (depexpiration == null) {
						j_1 = 0;
					} else {
						expi = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(depexpiration);
					}

					if (expi == null) {
						j_1 = 0;
					} else if (expi != null) {

						j_1 = today.compareTo(expi);
						if (j_1 > 0) {
							j_1 = 0;
						} else {
							j_1 = 1;
						}
					} else {
						j_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 23 :" + e);
				}

				if (depstatus.equalsIgnoreCase("-")
						&& eupcard.equalsIgnoreCase("-")
						&& dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (depstatus.equalsIgnoreCase("Y")
						&& deppermit.equals("-")
						&& !UtilityObject.isNotEmpty(depexpiration)
						&& eupcard.equalsIgnoreCase("-")
						&& dobfiling.equalsIgnoreCase("Y")
						&& dobpermit.equals("-") && dobsignoff.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (eupcard.equalsIgnoreCase("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("Y")
						&& dobpermit.equals("No Filing")
						&& dobsignoff.equals("Y")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("Y")
						&& !dobpermit.equals("No Filing")
						&& dobsignoff.equals("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (depstatus.equalsIgnoreCase("Y")
						&& deppermit.equals("-")
						&& UtilityObject.isNotEmpty(depexpiration)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (depstatus.equalsIgnoreCase("Y")
						&& !deppermit.equals("-")
						&& !UtilityObject.isNotEmpty(depexpiration)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (fuelfiring.equalsIgnoreCase("N")) {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				} else if (depstatus.equalsIgnoreCase("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (eupcard.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dobfiling.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (depstatus.equalsIgnoreCase("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (depstatus.equalsIgnoreCase("Y") && j_1 == 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}

			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport24(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.FIREALARMID,s.facilitydesignatedid,s.typeofFAInstalled,b.BUILDINGNAME,s"
							+ ".approvalDate,s.dobApproval,s.DOBFILING,s.fdApproval,s.fdApprovalDate,s.agencyApproval,s.age"
							+ "ncyName,s.agencyApprovalDate,s.agencyApprovalNo FROM (firealarm s, building b)  "
							+ "WHERE  (s.statusOfSource='1' or s.statusOfSource='5') and b.BUILDINGID=s.BUILDIN"
							+ "GID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("FIREALARMID");
				hashtable.put("FIREALARMID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));

				String dappr = UtilityObject.checkNullAndFill(
						resultset.getString("DOBAPPROVAL"), "-");
				hashtable.put("DOBAPPROVAL",
						UtilityObject.checkNullAndFill(dappr, "-"));

				String dapp = resultset.getString("DOBFILING");

				if (dappr.equalsIgnoreCase("Y")) {
					hashtable.put("DOBFILING",
							UtilityObject.checkNullAndFill(dapp, "-"));
				} else if (dappr.equalsIgnoreCase("N")) {
					hashtable.put("DOBFILING", "N/A");
				} else {
					hashtable.put("DOBFILING", "N/A");
				}

				String fappr = UtilityObject.checkNullAndFill(
						resultset.getString("FDAPPROVAL"), "-");
				hashtable.put("FDAPPROVAL",
						UtilityObject.checkNullAndFill(fappr, "-"));

				String fapp = resultset.getString("FDAPPROVALDATE");

				if (fappr.equalsIgnoreCase("Y")) {
					hashtable.put("FDAPPROVALDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(fapp), "-"));
				} else if (fappr.equalsIgnoreCase("N")) {
					hashtable.put("FDAPPROVALDATE", "N/A");
				} else {
					hashtable.put("FDAPPROVALDATE", "-");
				}

				String oappravailable = UtilityObject.checkNullAndFill(
						resultset.getString("AGENCYAPPROVAL"), "-");
				// hashtable.put("AGENCYAPPROVAL",
				// UtilityObject.checkNullAndFill(oappravailable, "-"));

				String agencyapprovalno = resultset
						.getString("AGENCYAPPROVALNO");
				String oappr = resultset.getString("AGENCYNAME");
				String oapp = resultset.getString("AGENCYAPPROVALDATE");

				if (oappravailable.equalsIgnoreCase("Y")) {
					hashtable.put("AGENCYAPPROVALNO", UtilityObject
							.checkNullAndFill(agencyapprovalno, "-"));
					hashtable.put("AGENCYNAME",
							UtilityObject.checkNullAndFill(oappr, "-"));
					hashtable.put("AGENCYAPPROVALDATE", UtilityObject
							.checkNullAndFill(UtilityObject
									.convertYyyyMmDd2MmDdYyyy(oapp), "-"));
				} else if (oappravailable.equalsIgnoreCase("N")) {
					hashtable.put("AGENCYAPPROVALNO", "N/A");
					hashtable.put("AGENCYNAME", "N/A");
					hashtable.put("AGENCYAPPROVALDATE", "N/A");
				} else {
					hashtable.put("AGENCYAPPROVALNO", "-");
					hashtable.put("AGENCYNAME", "-");
					hashtable.put("AGENCYAPPROVALDATE", "-");
				}

				// hashtable.put("TYPEOFFAINSTALLED",
				// checkNullAndFill(resultset.getString("TYPEOFFAINSTALLED"),
				// "-"));
				FireAlarmType firealarmtype = FireAlarmType
						.get(Integer.parseInt(checkNullAndFill(
								resultset.getString("typeofFAInstalled"), "-99")));
				if (firealarmtype != null) {
					hashtable.put("TYPEOFFAINSTALLED", firealarmtype.getName());
				} else {
					hashtable.put("TYPEOFFAINSTALLED", "-");
				}

				if (dappr.equals("-") && fappr.equals("-")
						&& oappravailable.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (dappr.equals("Y") && dapp.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (fappr.equals("Y") && !UtilityObject.isNotEmpty(fapp)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (oappravailable.equals("Y")
						&& agencyapprovalno.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (oappravailable.equals("Y") && oappr.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (oappravailable.equals("Y")
						&& !UtilityObject.isNotEmpty(oapp)) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (dappr.equals("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else if (fappr.equals("N")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static List getNycdobStatusReport25(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.GASFUELGAPPING,s.OILFUELGAPPING,s.B_NOXALLOWABLE,s.B_SO2ALLOWABLE,s.BOI"
							+ "LERID,s.FACILITYDESIGNATEDID,s.OILEMMISSIONFACTOR,s.GASEMMISSIONFACTOR,s.B_gasso"
							+ "2,s.B_oilso2 FROM (boiler s, building b)  WHERE  (s.MODIFIEDINPAST='1' or s.MODI"
							+ "FIEDINPAST='5') and s.FUELCAPING='Y' and b.BUILDINGID=s.BUILDINGID AND b.FACILIT"
							+ "YID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("BOILERID");
				hashtable.put("BOILERID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"GASFUELGAPPING",
						checkNullAndFill(resultset.getString("GASFUELGAPPING"),
								"-"));
				hashtable.put(
						"OILFUELGAPPING",
						checkNullAndFill(resultset.getString("OILFUELGAPPING"),
								"-"));

				hashtable.put(
						"B_SO2ALLOWABLE",
						checkNullAndFill(resultset.getString("B_SO2ALLOWABLE"),
								"-"));
				hashtable.put(
						"B_NOXALLOWABLE",
						checkNullAndFill(resultset.getString("B_NOXALLOWABLE"),
								"-"));

				hashtable
						.put("OILEMMISSIONFACTOR",
								checkNullAndFill(resultset
										.getString("OILEMMISSIONFACTOR"), "-"));
				hashtable
						.put("GASEMMISSIONFACTOR",
								checkNullAndFill(resultset
										.getString("GASEMMISSIONFACTOR"), "-"));

				hashtable.put("B_gasso2",
						checkNullAndFill(resultset.getString("B_gasso2"), "-"));
				hashtable.put("B_oilso2",
						checkNullAndFill(resultset.getString("B_oilso2"), "-"));
				hashtable.put("GASCON", getgasconsumption25(tempId));
				hashtable.put("OILCON", getoilconsumption25(tempId));
				hashtable.put("GASMAX", getgasmaxmin25(tempId));
				hashtable.put("OILMAX", getoilmaxmin25(tempId));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getNycdobStatusReport25:").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static Hashtable getgasconsumption25(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from boilerfuelconsumption where bctype='1' and BOILERID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			float cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new float[12];
				cons[0] = resultset.getFloat("bJAN");
				cons[1] = resultset.getFloat("bFEB");
				cons[2] = resultset.getFloat("bMAR");
				cons[3] = resultset.getFloat("bAPR");
				cons[4] = resultset.getFloat("bMAY");
				cons[5] = resultset.getFloat("bJUN");
				cons[6] = resultset.getFloat("bJUL");
				cons[7] = resultset.getFloat("bAUG");
				cons[8] = resultset.getFloat("bSEP");
				cons[9] = resultset.getFloat("bOCT");
				cons[10] = resultset.getFloat("bNOV");
				cons[11] = resultset.getFloat("bDEC");
			}

		} catch (Exception exception) {
			System.out
					.println((new StringBuilder())
							.append("getgasconsumption25").append(exception)
							.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilconsumption25(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from boilerfuelconsumption where bctype='2' and BOILERID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			float cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new float[12];
				cons[0] = resultset.getFloat("bJAN");
				cons[1] = resultset.getFloat("bFEB");
				cons[2] = resultset.getFloat("bMAR");
				cons[3] = resultset.getFloat("bAPR");
				cons[4] = resultset.getFloat("bMAY");
				cons[5] = resultset.getFloat("bJUN");
				cons[6] = resultset.getFloat("bJUL");
				cons[7] = resultset.getFloat("bAUG");
				cons[8] = resultset.getFloat("bSEP");
				cons[9] = resultset.getFloat("bOCT");
				cons[10] = resultset.getFloat("bNOV");
				cons[11] = resultset.getFloat("bDEC");
			}

		} catch (Exception exception) {
			System.out
					.println((new StringBuilder())
							.append("getoilconsumption25").append(exception)
							.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilmaxmin25(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year) from boilerfuelconsumption where bctype='2' and BOILE"
							+ "RID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getoilmaxmin25")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getgasmaxmin25(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year)  from boilerfuelconsumption where bctype='1' and BOIL"
							+ "ERID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getgasmaxmin25")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static List getNycdobStatusReport25a(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT OILCAP,GASCAP,FACILITYID,CLIENTNAME  FROM facility  WHERE FUELCAPING='Y' "
							+ "and FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("FACILITYID");
				hashtable.put("FACILITYID", checkNullAndFill(tempId, "-"));
				hashtable
						.put("CLIENTNAME",
								checkNullAndFill(
										resultset.getString("CLIENTNAME"), "-"));
				hashtable.put("GASCON", getgasconsumption25a(tempId));
				hashtable.put("OILCON", getoilconsumption25a(tempId));
				hashtable.put("GASMAX", getgasmaxmin25a(tempId));
				hashtable.put("OILMAX", getoilmaxmin25a(tempId));
				hashtable.put("GASFUELGAPPING",
						checkNullAndFill(resultset.getString("GASCAP"), "-"));
				hashtable.put("OILFUELGAPPING",
						checkNullAndFill(resultset.getString("OILCAP"), "-"));
				System.out.println((new StringBuilder()).append("Oil cap:")
						.append(resultset.getString("OILCAP"))
						.append("- Gas cap:")
						.append(resultset.getString("GASCAP")).toString());
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getNycdobStatusReport25a:").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static Hashtable getgasconsumption25a(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from facilityfuelconsumption where bctype='1' and FACILITYID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			float cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new float[12];
				cons[0] = resultset.getFloat("bJAN");
				cons[1] = resultset.getFloat("bFEB");
				cons[2] = resultset.getFloat("bMAR");
				cons[3] = resultset.getFloat("bAPR");
				cons[4] = resultset.getFloat("bMAY");
				cons[5] = resultset.getFloat("bJUN");
				cons[6] = resultset.getFloat("bJUL");
				cons[7] = resultset.getFloat("bAUG");
				cons[8] = resultset.getFloat("bSEP");
				cons[9] = resultset.getFloat("bOCT");
				cons[10] = resultset.getFloat("bNOV");
				cons[11] = resultset.getFloat("bDEC");
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getgasconsumption25a").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilconsumption25a(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from facilityfuelconsumption where bctype='2' and FACILITYID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			float cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new float[12];
				cons[0] = resultset.getFloat("bJAN");
				cons[1] = resultset.getFloat("bFEB");
				cons[2] = resultset.getFloat("bMAR");
				cons[3] = resultset.getFloat("bAPR");
				cons[4] = resultset.getFloat("bMAY");
				cons[5] = resultset.getFloat("bJUN");
				cons[6] = resultset.getFloat("bJUL");
				cons[7] = resultset.getFloat("bAUG");
				cons[8] = resultset.getFloat("bSEP");
				cons[9] = resultset.getFloat("bOCT");
				cons[10] = resultset.getFloat("bNOV");
				cons[11] = resultset.getFloat("bDEC");
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getoilconsumption25a").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilmaxmin25a(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year) from facilityfuelconsumption where bctype='2' and FAC"
							+ "ILITYID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getoilmaxmin25a")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getgasmaxmin25a(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year)  from facilityfuelconsumption where bctype='1' and FA"
							+ "CILITYID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getgasmaxmin25a")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static List getNycdobStatusReport26(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.gasfuelgapping,s.oilfuelgapping,s.G_NOXALLOWABLE,s.G_SO2ALLOWABLE,s.GEN"
							+ "ERATORID,s.FACILITYDESIGNATEDID,s.oilemmisionfactor,s.gasemmisionfactor,s.G_GASS"
							+ "O2,s.G_OILSO2 FROM (generator s, building b)  WHERE  (s.STATUS='1' or s.STATUS='"
							+ "5') and s.fuelgapping='Y' and b.BUILDINGID=s.BUILDINGID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("GENERATORID");
				hashtable.put("GENERATORID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"OILEMMISSIONFACTOR",
						checkNullAndFill(
								resultset.getString("oilemmisionfactor"), "-"));
				hashtable.put(
						"GASEMMISSIONFACTOR",
						checkNullAndFill(
								resultset.getString("gasemmisionfactor"), "-"));
				hashtable.put("B_gasso2",
						checkNullAndFill(resultset.getString("G_GASSO2"), "-"));
				hashtable.put("B_oilso2",
						checkNullAndFill(resultset.getString("G_OILSO2"), "-"));
				hashtable.put("GASCON", getgasconsumption26(tempId));
				hashtable.put("OILCON", getoilconsumption26(tempId));
				hashtable.put("GASMAX", getgasmaxmin26(tempId));
				hashtable.put("OILMAX", getoilmaxmin26(tempId));
				hashtable.put(
						"B_SO2ALLOWABLE",
						checkNullAndFill(resultset.getString("G_SO2ALLOWABLE"),
								"-"));
				hashtable.put(
						"B_NOXALLOWABLE",
						checkNullAndFill(resultset.getString("G_NOXALLOWABLE"),
								"-"));
				hashtable.put(
						"GASFUELGAPPING",
						checkNullAndFill(resultset.getString("gasfuelgapping"),
								"-"));
				hashtable.put(
						"OILFUELGAPPING",
						checkNullAndFill(resultset.getString("oilfuelgapping"),
								"-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getNycdobStatusReport26:").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static Hashtable getgasconsumption26(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from generatorfuelconsumption where bctype='1' and GENERATORID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			int cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new int[12];
				cons[0] = resultset.getInt("bJAN");
				cons[1] = resultset.getInt("bFEB");
				cons[2] = resultset.getInt("bMAR");
				cons[3] = resultset.getInt("bAPR");
				cons[4] = resultset.getInt("bMAY");
				cons[5] = resultset.getInt("bJUN");
				cons[6] = resultset.getInt("bJUL");
				cons[7] = resultset.getInt("bAUG");
				cons[8] = resultset.getInt("bSEP");
				cons[9] = resultset.getInt("bOCT");
				cons[10] = resultset.getInt("bNOV");
				cons[11] = resultset.getInt("bDEC");
			}

		} catch (Exception exception) {
			System.out
					.println((new StringBuilder())
							.append("getgasconsumption26").append(exception)
							.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilconsumption26(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from generatorfuelconsumption where bctype='2' and GENERATORID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			int cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new int[12];
				cons[0] = resultset.getInt("bJAN");
				cons[1] = resultset.getInt("bFEB");
				cons[2] = resultset.getInt("bMAR");
				cons[3] = resultset.getInt("bAPR");
				cons[4] = resultset.getInt("bMAY");
				cons[5] = resultset.getInt("bJUN");
				cons[6] = resultset.getInt("bJUL");
				cons[7] = resultset.getInt("bAUG");
				cons[8] = resultset.getInt("bSEP");
				cons[9] = resultset.getInt("bOCT");
				cons[10] = resultset.getInt("bNOV");
				cons[11] = resultset.getInt("bDEC");
			}

		} catch (Exception exception) {
			System.out
					.println((new StringBuilder())
							.append("getoilconsumption26").append(exception)
							.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilmaxmin26(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year) from generatorfuelconsumption where bctype='2' and GE"
							+ "NERATORID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getoilmaxmin26")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getgasmaxmin26(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year)  from generatorfuelconsumption where bctype='1' and G"
							+ "ENERATORID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getgasmaxmin26")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static List getNycdobStatusReport27(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.CO_GASFUELGAPPING,s.CO_OILFUELGAPPING,s.CO_NOXALLOWABLE,s.CO_SO2ALLOWAB"
							+ "LE,s.COGENTURBINEID,s.FACILITYDESIGNATEDID,s.CO_OILEMMISIONFACTOR,s.CO_GASEMMISI"
							+ "ONFACTOR,s.CO_GASSO2,s.CO_OILSO2 FROM (cogenturbine s, building b)  WHERE  (s.ST"
							+ "ATUS='1' or s.STATUS='5') and s.CO_FUELGAPPING='Y' and b.BUILDINGID=s.BUILDINGID"
							+ " AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("COGENTURBINEID");
				hashtable.put("COGENTURBINEID", checkNullAndFill(tempId, "-"));
				hashtable.put(
						"FACILITYDESIGNATEDID",
						checkNullAndFill(
								resultset.getString("FACILITYDESIGNATEDID"),
								"-"));
				hashtable.put(
						"OILEMMISSIONFACTOR",
						checkNullAndFill(
								resultset.getString("CO_OILEMMISIONFACTOR"),
								"-"));
				hashtable.put(
						"GASEMMISSIONFACTOR",
						checkNullAndFill(
								resultset.getString("CO_GASEMMISIONFACTOR"),
								"-"));
				hashtable
						.put("B_gasso2",
								checkNullAndFill(
										resultset.getString("CO_GASSO2"), "-"));
				hashtable
						.put("B_oilso2",
								checkNullAndFill(
										resultset.getString("CO_OILSO2"), "-"));
				hashtable.put("GASCON", getgasconsumption27(tempId));
				hashtable.put("OILCON", getoilconsumption27(tempId));
				hashtable.put("GASMAX", getgasmaxmin27(tempId));
				hashtable.put("OILMAX", getoilmaxmin27(tempId));
				hashtable.put(
						"B_SO2ALLOWABLE",
						checkNullAndFill(
								resultset.getString("CO_SO2ALLOWABLE"), "-"));
				hashtable.put(
						"B_NOXALLOWABLE",
						checkNullAndFill(
								resultset.getString("CO_NOXALLOWABLE"), "-"));
				hashtable.put(
						"GASFUELGAPPING",
						checkNullAndFill(
								resultset.getString("CO_GASFUELGAPPING"), "-"));
				hashtable.put(
						"OILFUELGAPPING",
						checkNullAndFill(
								resultset.getString("CO_OILFUELGAPPING"), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getNycdobStatusReport27:").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static Hashtable getgasconsumption27(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from cogenturbinefuelconsumption where bctype='1' and COGENTURBINEID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			float cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new float[12];
				cons[0] = resultset.getFloat("bJAN");
				cons[1] = resultset.getFloat("bFEB");
				cons[2] = resultset.getFloat("bMAR");
				cons[3] = resultset.getFloat("bAPR");
				cons[4] = resultset.getFloat("bMAY");
				cons[5] = resultset.getFloat("bJUN");
				cons[6] = resultset.getFloat("bJUL");
				cons[7] = resultset.getFloat("bAUG");
				cons[8] = resultset.getFloat("bSEP");
				cons[9] = resultset.getFloat("bOCT");
				cons[10] = resultset.getFloat("bNOV");
				cons[11] = resultset.getFloat("bDEC");
			}

		} catch (Exception exception) {
			System.out
					.println((new StringBuilder())
							.append("getgasconsumption27").append(exception)
							.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilconsumption27(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from cogenturbinefuelconsumption where bctype='2' and COGENTURBINEID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			float cons[];
			for (; resultset.next(); hashtable.put(resultset.getString("YEAR"),
					cons)) {
				cons = new float[12];
				cons[0] = resultset.getFloat("bJAN");
				cons[1] = resultset.getFloat("bFEB");
				cons[2] = resultset.getFloat("bMAR");
				cons[3] = resultset.getFloat("bAPR");
				cons[4] = resultset.getFloat("bMAY");
				cons[5] = resultset.getFloat("bJUN");
				cons[6] = resultset.getFloat("bJUL");
				cons[7] = resultset.getFloat("bAUG");
				cons[8] = resultset.getFloat("bSEP");
				cons[9] = resultset.getFloat("bOCT");
				cons[10] = resultset.getFloat("bNOV");
				cons[11] = resultset.getFloat("bDEC");
			}

		} catch (Exception exception) {
			System.out
					.println((new StringBuilder())
							.append("getoilconsumption27").append(exception)
							.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getoilmaxmin27(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year) from cogenturbinefuelconsumption where bctype='2' and"
							+ " COGENTURBINEID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getoilmaxmin27")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static Hashtable getgasmaxmin27(String bid) {
		SqlUtil sqlutil = new SqlUtil();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Hashtable hashtable = new Hashtable();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT max(year),min(year)  from cogenturbinefuelconsumption where bctype='1' an"
							+ "d COGENTURBINEID=?");
			preparedstatement.setString(1, bid);
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hashtable.put("MIN",
					checkNullAndFill(resultset.getString(2), "-"))) {
				hashtable.put("MAX",
						checkNullAndFill(resultset.getString(1), "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getgasmaxmin27")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return hashtable;
	}

	public static List getNycdobStatusReport28(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT * from otherevalution where FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				System.out.println("@28 Report in");
				hashtable = new Hashtable();
				tempId = resultset.getString("FACILITYID");
				hashtable.put("FACILITYID", checkNullAndFill(tempId, "-"));
				String spcc_req = checkNullAndFill(
						resultset.getString("SPCC_REQ"), "-");
				hashtable.put("SPCC_REQ", spcc_req);
				String stormprevention_req = checkNullAndFill(
						resultset.getString("STORMPREVENTION_REQ"), "-");
				hashtable.put("STORMPREVENTION_REQ", stormprevention_req);
				String epaaudit_req = checkNullAndFill(
						resultset.getString("EPAAUDIT_REQ"), "-");
				hashtable.put("EPAAUDIT_REQ", epaaudit_req);
				String accfinding_req = checkNullAndFill(
						resultset.getString("ACCFINDING_REQ"), "-");
				hashtable.put("ACCFINDING_REQ", accfinding_req);
				String hazwaste_req = checkNullAndFill(
						resultset.getString("HAZWASTE_REQ"), "-");
				hashtable.put("HAZWASTE_REQ", hazwaste_req);
				String hazr_req = checkNullAndFill(
						resultset.getString("HAZR_REQ"), "-");
				hashtable.put("HAZR_REQ", hazr_req);
				String tier2_req = checkNullAndFill(
						resultset.getString("TIER2_REQ"), "-");
				hashtable.put("TIER2_REQ", tier2_req);
				String nyccom_req = checkNullAndFill(
						resultset.getString("NYCCOM_REQ"), "-");
				hashtable.put("NYCCOM_REQ", nyccom_req);
				String dobvio_req = checkNullAndFill(
						resultset.getString("DOBVIO_REQ"), "-");
				hashtable.put("DOBVIO_REQ", dobvio_req);
				String fd_req = checkNullAndFill(resultset.getString("FD_REQ"),
						"-");
				hashtable.put("FD_REQ", fd_req);
				String ecp_req = checkNullAndFill(
						resultset.getString("ECP_REQ"), "-");
				hashtable.put("ECP_REQ", ecp_req);
				String dep_req = checkNullAndFill(
						resultset.getString("DEP_REQ"), "-");
				hashtable.put("DEP_REQ", dep_req);
				String anystate_req = checkNullAndFill(
						resultset.getString("ANYSTATE_REQ"), "-");
				hashtable.put("ANYSTATE_REQ", anystate_req);
				String doh_req = checkNullAndFill(
						resultset.getString("DOH_REQ"), "-");
				hashtable.put("DOH_REQ", doh_req);
				String builtinplan_req = checkNullAndFill(
						resultset.getString("BUILTINPLAN_REQ"), "-");
				hashtable.put("BUILTINPLAN_REQ", builtinplan_req);
				String fuelinventory_req = checkNullAndFill(
						resultset.getString("FUELINVENTORY_REQ"), "-");
				hashtable.put("FUELINVENTORY_REQ", fuelinventory_req);
				String opacity_req = checkNullAndFill(
						resultset.getString("OPACITY_REQ"), "-");
				hashtable.put("OPACITY_REQ", opacity_req);
				String refri_req = checkNullAndFill(
						resultset.getString("REFRI_REQ"), "-");
				hashtable.put("REFRI_REQ", refri_req);
				String risk_req = checkNullAndFill(
						resultset.getString("RISK_REQ"), "-");
				hashtable.put("RISK_REQ", risk_req);
				String other_req = checkNullAndFill(
						resultset.getString("OTHER_REQ"), "-");
				hashtable.put("OTHER_REQ", other_req);
				String laundry_req = checkNullAndFill(
						resultset.getString("LAUNDRY_REQ"), "-");
				hashtable.put("LAUNDRY_REQ", laundry_req);
				String compliancereport_req = checkNullAndFill(
						resultset.getString("COMPLIANCEREPORT_REQ"), "-");
				hashtable.put("COMPLIANCEREPORT_REQ", compliancereport_req);
				String spcc_avi = checkNullAndFill(
						resultset.getString("SPCC_AVI"), "-");
				hashtable.put("SPCC_AVI", spcc_avi);
				String stormprevention_avi = checkNullAndFill(
						resultset.getString("STORMPREVENTION_AVI"), "-");
				hashtable.put("STORMPREVENTION_AVI", stormprevention_avi);
				String epaaudit_avi = checkNullAndFill(
						resultset.getString("EPAAUDIT_AVI"), "-");
				hashtable.put("EPAAUDIT_AVI", epaaudit_avi);
				String accfinding_avi = checkNullAndFill(
						resultset.getString("ACCFINDING_AVI"), "-");
				hashtable.put("ACCFINDING_AVI", accfinding_avi);
				String hazwaste_avi = checkNullAndFill(
						resultset.getString("HAZWASTE_AVI"), "-");
				hashtable.put("HAZWASTE_AVI", hazwaste_avi);
				String hazr_avi = checkNullAndFill(
						resultset.getString("HAZR_AVI"), "-");
				hashtable.put("HAZR_AVI", hazr_avi);
				String tier2_avi = checkNullAndFill(
						resultset.getString("TIER2_AVI"), "-");
				hashtable.put("TIER2_AVI", tier2_avi);
				String nyccom_avi = checkNullAndFill(
						resultset.getString("NYCCOM_AVI"), "-");
				hashtable.put("NYCCOM_AVI", nyccom_avi);
				String dobvio_avi = checkNullAndFill(
						resultset.getString("DOBVIO_AVI"), "-");
				hashtable.put("DOBVIO_AVI", dobvio_avi);
				String fd_avi = checkNullAndFill(resultset.getString("FD_AVI"),
						"-");
				hashtable.put("FD_AVI", fd_avi);
				String ecp_avi = checkNullAndFill(
						resultset.getString("ECP_AVI"), "-");
				hashtable.put("ECP_AVI", ecp_avi);
				String dep_avi = checkNullAndFill(
						resultset.getString("DEP_AVI"), "-");
				hashtable.put("DEP_AVI", dep_avi);
				String anystate_avi = checkNullAndFill(
						resultset.getString("ANYSTATE_AVI"), "-");
				hashtable.put("ANYSTATE_AVI", anystate_avi);
				String doh_avi = checkNullAndFill(
						resultset.getString("DOH_AVI"), "-");
				hashtable.put("DOH_AVI", doh_avi);
				String builtinplan_avi = checkNullAndFill(
						resultset.getString("BUILTINPLAN_AVI"), "-");
				hashtable.put("BUILTINPLAN_AVI", builtinplan_avi);
				String fuelinventory_avi = checkNullAndFill(
						resultset.getString("FUELINVENTORY_AVI"), "-");
				hashtable.put("FUELINVENTORY_AVI", fuelinventory_avi);
				String opacity_avi = checkNullAndFill(
						resultset.getString("OPACITY_AVI"), "-");
				hashtable.put("OPACITY_AVI", opacity_avi);
				String refri_avi = checkNullAndFill(
						resultset.getString("REFRI_AVI"), "-");
				hashtable.put("REFRI_AVI", refri_avi);
				String risk_avi = checkNullAndFill(
						resultset.getString("RISK_AVI"), "-");
				hashtable.put("RISK_AVI", risk_avi);
				String other_avi = checkNullAndFill(
						resultset.getString("OTHER_AVI"), "-");
				hashtable.put("OTHER_AVI", other_avi);
				String laundry_avi = checkNullAndFill(
						resultset.getString("LAUNDRY_AVI"), "-");
				hashtable.put("LAUNDRY_AVI", laundry_avi);
				String compliancereport_avi = checkNullAndFill(
						resultset.getString("COMPLIANCEREPORT_AVI"), "-");
				hashtable.put("COMPLIANCEREPORT_AVI", compliancereport_avi);

				if (spcc_req.equals("-") || spcc_req.equals("UN")) {
					hashtable.put("SPCC_REQ_COMPLIANCE", "TBD");
				} else if (spcc_req.equals("Y")
						&& (spcc_avi.equals("UN") || spcc_avi.equals("-") || spcc_avi
								.equals("N"))) {
					hashtable.put("SPCC_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("SPCC_REQ_COMPLIANCE", "Compliance");
				}
				if (stormprevention_req.equals("-")
						|| stormprevention_req.equals("UN")) {
					hashtable.put("STORMPREVENTION_REQ_COMPLIANCE", "TBD");
				} else if (stormprevention_req.equals("Y")
						&& (stormprevention_avi.equals("UN")
								|| stormprevention_avi.equals("-") || stormprevention_avi
									.equals("N"))) {
					hashtable.put("STORMPREVENTION_REQ_COMPLIANCE",
							"Non Compliance");
				} else {
					hashtable.put("STORMPREVENTION_REQ_COMPLIANCE",
							"Compliance");
				}
				if (epaaudit_req.equals("-") || epaaudit_req.equals("UN")) {
					hashtable.put("EPAAUDIT_REQ_COMPLIANCE", "TBD");
				} else if (epaaudit_req.equals("Y")
						&& (epaaudit_avi.equals("UN")
								|| epaaudit_avi.equals("-") || epaaudit_avi
									.equals("N"))) {
					hashtable.put("EPAAUDIT_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("EPAAUDIT_REQ_COMPLIANCE", "Compliance");
				}
				if (accfinding_req.equals("-") || accfinding_req.equals("UN")) {
					hashtable.put("ACCFINDING_REQ_COMPLIANCE", "TBD");
				} else if (accfinding_req.equals("Y")
						&& (accfinding_avi.equals("UN")
								|| accfinding_avi.equals("-") || accfinding_avi
									.equals("N"))) {
					hashtable
							.put("ACCFINDING_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("ACCFINDING_REQ_COMPLIANCE", "Compliance");
				}
				if (hazwaste_req.equals("-") || hazwaste_req.equals("UN")) {
					hashtable.put("HAZWASTE_REQ_COMPLIANCE", "TBD");
				} else if (hazwaste_req.equals("Y")
						&& (hazwaste_avi.equals("UN")
								|| hazwaste_avi.equals("-") || hazwaste_avi
									.equals("N"))) {
					hashtable.put("HAZWASTE_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("HAZWASTE_REQ_COMPLIANCE", "Compliance");
				}
				if (hazr_req.equals("-") || hazr_req.equals("UN")) {
					hashtable.put("HAZR_REQ_COMPLIANCE", "TBD");
				} else if (hazr_req.equals("Y")
						&& (hazr_avi.equals("UN") || hazr_avi.equals("-") || hazr_avi
								.equals("N"))) {
					hashtable.put("HAZR_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("HAZR_REQ_COMPLIANCE", "Compliance");
				}
				if (tier2_req.equals("-") || tier2_req.equals("UN")) {
					hashtable.put("TIER2_REQ_COMPLIANCE", "TBD");
				} else if (tier2_req.equals("Y")
						&& (tier2_avi.equals("UN") || tier2_avi.equals("-") || tier2_avi
								.equals("N"))) {
					hashtable.put("TIER2_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("TIER2_REQ_COMPLIANCE", "Compliance");
				}
				if (nyccom_req.equals("-") || nyccom_req.equals("UN")) {
					hashtable.put("NYCCOM_REQ_COMPLIANCE", "TBD");
				} else if (nyccom_req.equals("Y")
						&& (nyccom_avi.equals("UN") || nyccom_avi.equals("-") || nyccom_avi
								.equals("N"))) {
					hashtable.put("NYCCOM_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("NYCCOM_REQ_COMPLIANCE", "Compliance");
				}
				if (dobvio_req.equals("-") || dobvio_req.equals("UN")) {
					hashtable.put("DOBVIO_REQ_COMPLIANCE", "TBD");
				} else if (dobvio_req.equals("Y")
						&& (dobvio_avi.equals("UN") || dobvio_avi.equals("-") || dobvio_avi
								.equals("N"))) {
					hashtable.put("DOBVIO_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("DOBVIO_REQ_COMPLIANCE", "Compliance");
				}
				if (fd_req.equals("-") || fd_req.equals("UN")) {
					hashtable.put("FD_REQ_COMPLIANCE", "TBD");
				} else if (fd_req.equals("Y")
						&& (fd_avi.equals("UN") || fd_avi.equals("-") || fd_avi
								.equals("N"))) {
					hashtable.put("FD_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("FD_REQ_COMPLIANCE", "Compliance");
				}
				if (ecp_req.equals("-") || ecp_req.equals("UN")) {
					hashtable.put("ECP_REQ_COMPLIANCE", "TBD");
				} else if (ecp_req.equals("Y")
						&& (ecp_avi.equals("UN") || ecp_avi.equals("-") || ecp_avi
								.equals("N"))) {
					hashtable.put("ECP_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("ECP_REQ_COMPLIANCE", "Compliance");
				}
				if (dep_req.equals("-") || dep_req.equals("UN")) {
					hashtable.put("DEP_REQ_COMPLIANCE", "TBD");
				} else if (dep_req.equals("Y")
						&& (dep_avi.equals("UN") || dep_avi.equals("-") || dep_avi
								.equals("N"))) {
					hashtable.put("DEP_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("DEP_REQ_COMPLIANCE", "Compliance");
				}
				if (anystate_req.equals("-") || anystate_req.equals("UN")) {
					hashtable.put("ANYSTATE_REQ_COMPLIANCE", "TBD");
				} else if (anystate_req.equals("Y")
						&& (anystate_avi.equals("UN")
								|| anystate_avi.equals("-") || anystate_avi
									.equals("N"))) {
					hashtable.put("ANYSTATE_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("ANYSTATE_REQ_COMPLIANCE", "Compliance");
				}
				if (doh_req.equals("-") || doh_req.equals("UN")) {
					hashtable.put("DOH_REQ_COMPLIANCE", "TBD");
				} else if (doh_req.equals("Y")
						&& (doh_avi.equals("UN") || doh_avi.equals("-") || doh_avi
								.equals("N"))) {
					hashtable.put("DOH_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("DOH_REQ_COMPLIANCE", "Compliance");
				}
				if (builtinplan_req.equals("-") || builtinplan_req.equals("UN")) {
					hashtable.put("BUILTINPLAN_REQ_COMPLIANCE", "TBD");
				} else if (builtinplan_req.equals("Y")
						&& (builtinplan_avi.equals("UN")
								|| builtinplan_avi.equals("-") || builtinplan_avi
									.equals("N"))) {
					hashtable.put("BUILTINPLAN_REQ_COMPLIANCE",
							"Non Compliance");
				} else {
					hashtable.put("BUILTINPLAN_REQ_COMPLIANCE", "Compliance");
				}
				if (fuelinventory_req.equals("-")
						|| fuelinventory_req.equals("UN")) {
					hashtable.put("FUELINVENTORY_REQ_COMPLIANCE", "TBD");
				} else if (fuelinventory_req.equals("Y")
						&& (fuelinventory_avi.equals("UN")
								|| fuelinventory_avi.equals("-") || fuelinventory_avi
									.equals("N"))) {
					hashtable.put("FUELINVENTORY_REQ_COMPLIANCE",
							"Non Compliance");
				} else {
					hashtable.put("FUELINVENTORY_REQ_COMPLIANCE", "Compliance");
				}
				if (opacity_req.equals("-") || opacity_req.equals("UN")) {
					hashtable.put("OPACITY_REQ_COMPLIANCE", "TBD");
				} else if (opacity_req.equals("Y")
						&& (opacity_avi.equals("UN") || opacity_avi.equals("-") || opacity_avi
								.equals("N"))) {
					hashtable.put("OPACITY_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("OPACITY_REQ_COMPLIANCE", "Compliance");
				}
				if (refri_req.equals("-") || refri_req.equals("UN")) {
					hashtable.put("REFRI_REQ_COMPLIANCE", "TBD");
				} else if (refri_req.equals("Y")
						&& (refri_avi.equals("UN") || refri_avi.equals("-") || refri_avi
								.equals("N"))) {
					hashtable.put("REFRI_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("REFRI_REQ_COMPLIANCE", "Compliance");
				}
				if (risk_req.equals("-") || risk_req.equals("UN")) {
					hashtable.put("RISK_REQ_COMPLIANCE", "TBD");
				} else if (risk_req.equals("Y")
						&& (risk_avi.equals("UN") || risk_avi.equals("-") || risk_avi
								.equals("N"))) {
					hashtable.put("RISK_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("RISK_REQ_COMPLIANCE", "Compliance");
				}
				if (other_req.equals("-") || other_req.equals("UN")) {
					hashtable.put("OTHER_REQ_COMPLIANCE", "TBD");
				} else if (other_req.equals("Y")
						&& (other_avi.equals("UN") || other_avi.equals("-") || other_avi
								.equals("N"))) {
					hashtable.put("OTHER_REQ_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("OTHER_REQ_COMPLIANCE", "Compliance");
				}
				if (laundry_req.equals("-") || laundry_req.equals("UN")) {
					hashtable.put("LAUNDRY_AVI_COMPLIANCE", "TBD");
				} else if (laundry_req.equals("Y")
						&& (laundry_avi.equals("UN") || laundry_avi.equals("-") || laundry_avi
								.equals("N"))) {
					hashtable.put("LAUNDRY_AVI_COMPLIANCE", "Non Compliance");
				} else {
					hashtable.put("LAUNDRY_AVI_COMPLIANCE", "Compliance");
				}

				if (compliancereport_req.equals("-")
						|| compliancereport_req.equals("UN")) {
					hashtable.put("COMPLIANCEREPORT_AVI_COMPLIANCE", "TBD");
				} else if (compliancereport_req.equals("Y")
						&& (compliancereport_avi.equals("UN")
								|| compliancereport_avi.equals("-") || compliancereport_avi
									.equals("N"))) {
					hashtable.put("COMPLIANCEREPORT_AVI_COMPLIANCE",
							"Non Compliance");
				} else {
					hashtable.put("COMPLIANCEREPORT_AVI_COMPLIANCE",
							"Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("Exception:")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static String getNycdobStatusReport29(List datalist, String Search) {
		String returnvalue = "";
		String noncompliance = "";
		String tbd = "";
		int datalistsize = datalist.size();
		if (datalistsize == 0) {
			returnvalue = "TBD";
		} else {
			for (int i = 0; i < datalistsize; i++) {
				Hashtable hashtable = (Hashtable) datalist.get(i);
				String srch = (String) hashtable.get(Search);
				if (srch.equalsIgnoreCase("Non Compliance")) {
					noncompliance = "Non Compliance";
					continue;
				}
				if (srch.equalsIgnoreCase("TBD")) {
					tbd = "TBD";
				}
			}

		}
		if (noncompliance.equals("Non Compliance")) {
			returnvalue = "Non Compliance";
		} else if (tbd.equals("TBD")) {
			returnvalue = "TBD";
		} else {
			returnvalue = "Compliance";
		}
		return returnvalue;
	}

	public static String getNycdobStatusReport29a(List datalist, String Search,
			boolean condition) {
		String returnvalue = "";
		String noncompliance = "";
		String tbd = "";

		int datalistsize = datalist.size();

		if (datalistsize == 0) {
			returnvalue = "TBD";
		} else if (condition) {
			for (int i = 0; i < datalistsize; i++) {
				Hashtable hashtable = (Hashtable) datalist.get(i);
				String srch = (String) hashtable.get(Search);
				if (srch != null && srch.equalsIgnoreCase("-")) {
					noncompliance = "Non Compliance";
				} else {
					tbd = "Available";
				}
			}

			if (noncompliance != null && noncompliance.equals("Non Compliance")) {
				returnvalue = "Non Compliance";
			} else if (tbd.equals("Available")) {
				returnvalue = "Available";
			} else {
				returnvalue = "Available";
			}
		} else {
			for (int i = 0; i < datalistsize; i++) {
				Hashtable hashtable = (Hashtable) datalist.get(i);
				String srch = (String) hashtable.get(Search);
				if (srch != null && srch.equalsIgnoreCase("N")) {
					noncompliance = "Non Compliance";
					continue;
				}
				if (srch != null && srch.equalsIgnoreCase("-")) {
					tbd = "TBD";
				}
			}

			if (noncompliance != null && noncompliance.equals("Non Compliance")) {
				returnvalue = "Non Compliance";
			} else if (tbd.equals("TBD")) {
				returnvalue = "TBD";
			} else {
				returnvalue = "Available";
			}
		}
		return returnvalue;
	}

	public static String getNycdobStatusReport29b(List datalist, String Search) {
		String returnvalue = "";
		String noncompliance = "";
		String tbd = "";
		int datalistsize = datalist.size();
		if (datalistsize == 0) {
			returnvalue = "TBD";
		} else {
			for (int i = 0; i < datalistsize; i++) {
				Hashtable hashtable = (Hashtable) datalist.get(i);
				String srch = (String) hashtable.get(Search);
				if (srch.equalsIgnoreCase("-") || srch.equalsIgnoreCase("TBD")
						|| srch.equalsIgnoreCase("N/A")) {
					tbd = "TBD";
					continue;
				}
				String getdate = UtilityObject.convertToString(
						UtilityObject.convertToDate(srch), "yyyy-MM-dd");
				java.util.Date today = new java.util.Date();
				java.util.Date exp_date = null;
				exp_date = UtilityObject.convert_YyyyMmDd2MmDdYyyy(getdate);
				if (today.compareTo(exp_date) > 0) {
					noncompliance = "Non Compliance";
				}
			}

		}
		if (noncompliance.equals("Non Compliance")) {
			returnvalue = "Non Compliance";
		} else if (tbd.equals("TBD")) {
			returnvalue = "TBD";
		} else {
			returnvalue = "Compliance";
		}
		return returnvalue;
	}

	public static String[] getDepartmentFor33(String lsfid) {

		String arr[] = null;
		LsfEntity lsfentity = new LsfEntity();
		List arraylist = lsfentity.getcoffrequiredList(Integer.parseInt(lsfid),
				1);

		int i = arraylist == null ? 0 : arraylist.size();
		if (i > 0) {
			String as[] = new String[i];

			// System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+as);
			for (int j = 0; j < i; j++) {
				CofF coff = CofF.get(Integer.parseInt(String.valueOf(arraylist
						.get(j))));
				as[j] = coff.getName();

			}

			arr = as;
		}

		return arr;
	}

	public static List getNycdobStatusReport33(int facid) {
		LinkedHashMap hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT s.lsfid,s.certificatenumber,s.firstname,s.lastname,s.department,s.phonenumber,s.issuedate,s.expirationdate FROM lsf s where s.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			Hashtable hashtable;
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {

				hashtable = new Hashtable();
				tempId = resultset.getString("LSFID");
				hashtable.put("LSFID", checkNullAndFill(tempId, "-"));
				System.out.println("id:" + tempId);

				if (getDepartmentFor33(tempId) != null) {
					hashtable.put("OPERATINGEQUIPMENTS",
							getDepartmentFor33(tempId));
				} else if (getDepartmentFor33(tempId) == null) {
					hashtable.put("OPERATINGEQUIPMENTS",
							getDepartmentFor33("-"));
				}

				hashtable.put(
						"NAME",
						(new StringBuilder())
								.append(checkNullAndFill(
										resultset.getString("FIRSTNAME"), ""))
								.append(" ")
								.append(checkNullAndFill(
										resultset.getString("LASTNAME"), ""))
								.toString());
				// System.out.println("NAME:"+tempId);

				String certificatenumber = checkNullAndFill(
						resultset.getString("certificatenumber"), "-");
				hashtable.put("certificatenumber", certificatenumber);
				// System.out.println("certificatenumber:"+certificatenumber);

				hashtable.put(
						"PHONENUMBER",
						checkNullAndFill(resultset.getString("PHONENUMBER"),
								"-"));
				// System.out.println("id:"+tempId);

				hashtable
						.put("department",
								checkNullAndFill(
										resultset.getString("department"), "-"));
				// System.out.println("department:");

				String issuedate = checkNullAndFilldate(
						resultset.getString("ISSUEDATE"), "-");
				hashtable.put("ISSUEDATE", issuedate);
				// System.out.println("issuedate:"+issuedate);

				String expirationdate = resultset.getString("EXPIRATIONDATE");
				hashtable.put("EXPIRATIONDATE",
						checkNullAndFilldate(expirationdate, "-"));
				// System.out.println("expirationdate:"+expirationdate);

				int m_1 = 0;

				try {
					java.util.Date today = new java.util.Date();
					java.util.Date expi = null;
					if (expirationdate == null) {
						m_1 = 0;
					} else {
						expi = UtilityObject
								.convert_YyyyMmDd2MmDdYyyy(expirationdate);
					}

					if (expi == null) {
						m_1 = 0;
					}

					else if (expi != null) {

						m_1 = today.compareTo(expi);
						if (m_1 > 0) {
							m_1 = 0;
						} else {
							m_1 = 1;
						}
					} else {
						m_1 = 0;
					}

				} catch (Exception e) {
					System.out.println("Exhibit 33 :" + e);
				}

				if (certificatenumber.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (issuedate.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (expirationdate.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "TBD");
				} else if (m_1 == 0) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				} else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next()))) {
			}
		}
		return arraylist;
	}

	public static Map getNycdobStatusReport34a(int facid) {
		List arraylist = new ArrayList();
		HwasteVo hwastevo = new HwasteVo();
		HwasteEntity hwasteentity = new HwasteEntity();
		arraylist = hwasteentity.getHwasteInFacility(facid);
		String cat[] = { "CESQG", "SQG", "LQG" };
		String waste[] = { "", "F Waste", "K Waste", "D Waste", "U Waste",
				"P Waste" };
		HashMap yearMap = new HashMap();
		SortedMap monthMap = null;
		List valueList;
		for (int i = 0; i < arraylist.size(); i++) {
			hwastevo = (HwasteVo) arraylist.get(i);

			String year = UtilityObject.convertDate(
					hwastevo.getDateOftheManifest(),
					UtilityObject.DB_DATE_FORMAT, UtilityObject.ONLY_YEAR);
			monthMap = (yearMap.containsKey(year)) ? (TreeMap) yearMap
					.get(year) : new TreeMap();
			String mont = UtilityObject.convertDate(
					hwastevo.getDateOftheManifest(),
					UtilityObject.DB_DATE_FORMAT, UtilityObject.ONLY_MONTH);
			valueList = (monthMap.containsKey(mont)) ? (List) monthMap
					.get(mont) : new ArrayList();

			valueList
					.add(UtilityObject.convertDate(
							hwastevo.getDateOftheManifest(),
							UtilityObject.DB_DATE_FORMAT,
							UtilityObject.VIEW_DATE_FORMAT)
							+ "##"
							+ hwastevo.getManifastno()
							+ "##"
							+ ((hwastevo.getWaste() != null && !hwastevo
									.getWaste().equalsIgnoreCase("")) ? waste[hwastevo
									.getTypeOfWaste()]
									+ ":="
									+ checkNullAndFill(
											hwastevo.getWasteQuantity(), "0")
									: "")
							+ "!!"
							+ ((hwastevo.getWaste() != null && !hwastevo
									.getWaste1().equalsIgnoreCase("")) ? waste[hwastevo
									.getTypeOfWaste1()]
									+ ":="
									+ checkNullAndFill(
											hwastevo.getWasteQuantity1(), "0")
									: "")
							+ "!!"
							+ ((hwastevo.getWaste() != null && !hwastevo
									.getWaste2().equalsIgnoreCase("")) ? waste[hwastevo
									.getTypeOfWaste2()]
									+ ":="
									+ checkNullAndFill(
											hwastevo.getWasteQuantity2(), "0")
									: "")
							+ "!!"
							+ ((hwastevo.getWaste() != null && !hwastevo
									.getWaste3().equalsIgnoreCase("")) ? waste[hwastevo
									.getTypeOfWaste3()]
									+ ":="
									+ checkNullAndFill(
											hwastevo.getWasteQuantity3(), "0")
									: "")
							+ "!!"
							+ ((hwastevo.getWaste() != null && !hwastevo
									.getWaste4().equalsIgnoreCase("")) ? waste[hwastevo
									.getTypeOfWaste4()]
									+ ":="
									+ checkNullAndFill(
											hwastevo.getWasteQuantity4(), "0")
									: "")
							+ "##"
							+ (hwastevo.getHazardousWasteCategory() > 0 ? cat[hwastevo
									.getHazardousWasteCategory() - 1] : "")
							+ "##" + hwastevo.getEpaId());
			monthMap.put(mont, valueList);
			yearMap.put(year, monthMap);
		}
		System.out.println(yearMap);
		return yearMap;
	}

	public static List getNycdobStatusReport34(int facid) {
		String montharray[] = { "", "Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		String cat[] = { "CESQG", "SQG", "LQG" };
		List array_list = new ArrayList();
		// List datalist = new ArrayList();
		List arraylist = new ArrayList();
		HwasteVo hwastevo = new HwasteVo();
		HwasteEntity hwasteentity = new HwasteEntity();
		arraylist = hwasteentity.getHwasteInFacility(facid);
		int n = arraylist.size();
		int start = 0;
		int stop = 0;

		System.out.println("Senthil Start:" + n);
		try {
			if (n > 0) {
				hwastevo = (HwasteVo) arraylist.get(0);
				String mdate = UtilityObject.convertYyyyMmDd2MmDdYyyy(hwastevo
						.getDateOftheManifest());
				System.out.println("Senthil 1:" + mdate);
				if (UtilityObject.isNotEmpty(mdate)) {
					String mdarr[] = mdate.split("/");
					start = Integer.parseInt(mdarr[2]);
					System.out.println("Senthil 2:" + start);
				}
			}

			if (n > 0) {
				hwastevo = (HwasteVo) arraylist.get(n - 1);
				String mdate = UtilityObject.convertYyyyMmDd2MmDdYyyy(hwastevo
						.getDateOftheManifest());
				System.out.println("Senthil 3:" + mdate);
				if (UtilityObject.isNotEmpty(mdate)) {
					String mdarr[] = mdate.split("/");
					stop = Integer.parseInt(mdarr[2]);
					System.out.println("Senthil 4:" + stop);
				}
			}

			System.out.println("Senthil 2:" + start + "/" + stop);
			Hashtable hashtable;
			List datalist;
			for (int i = start; i <= stop; i++) {
				System.out.println("Year In:" + i);
				hashtable = new Hashtable();
				hashtable.put("YEAR", i);

				datalist = new ArrayList();

				// monthdata[1]="";
				for (int k = 0; k < n; k++) {
					float hwaste = 0;
					float pwaste = 0;
					String monthdata[] = { "", "", "", "", "", "", "", "", "",
							"", "", "" };
					monthdata[1] = "";
					// monthdata[9]=""+montharray[j];
					// for(int j=1;j<=12;j++)
					// {

					hwastevo = (HwasteVo) arraylist.get(k);
					String m_date = UtilityObject
							.convertYyyyMmDd2MmDdYyyy(hwastevo
									.getDateOftheManifest());
					if (UtilityObject.isNotEmpty(m_date)) {
						monthdata[8] = m_date;

						String m_darr[] = m_date.split("/");
						int getmonth = Integer.parseInt(m_darr[0]);
						if (getmonth == 1) {
							monthdata[0] = "Jan";
						} else if (getmonth == 2) {
							monthdata[0] = "Feb";
						} else if (getmonth == 3) {
							monthdata[0] = "Mar";
						} else if (getmonth == 4) {
							monthdata[0] = "Apr";
						} else if (getmonth == 5) {
							monthdata[0] = "May";
						} else if (getmonth == 6) {
							monthdata[0] = "Jun";
						} else if (getmonth == 7) {
							monthdata[0] = "Jul";
						} else if (getmonth == 8) {
							monthdata[0] = "Aug";
						} else if (getmonth == 9) {
							monthdata[0] = "Sep";
						} else if (getmonth == 10) {
							monthdata[0] = "Oct";
						} else if (getmonth == 11) {
							monthdata[0] = "Nov";
						} else if (getmonth == 12) {
							monthdata[0] = "Dec";
						}

						int getyear = Integer.parseInt(m_darr[2]);
						if (getyear == i) {

							if (hwastevo.getWaste() != null
									&& !hwastevo.getWaste()
											.equalsIgnoreCase("")) {

								if (hwastevo.getTypeOfWaste() == 5) {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste() + ",";
									pwaste = pwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity(),
													"0"));
								}
								// else if(hwastevo.getTypeOfWaste()==-1)
								// {
								// monthdata[1] = monthdata[1] + "" + ",";
								// hwaste=hwaste+Float.parseFloat(checkNullAndFill("","0"));
								// }
								else {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste() + ",";
									hwaste = hwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity(),
													"0"));
								}
							}

							if (hwastevo.getWaste1() != null
									&& !hwastevo.getWaste1().equalsIgnoreCase(
											"")) {
								// System.out.println("OOOOOOOOO:"+hwastevo.getWasteQuantity1());
								if (hwastevo.getTypeOfWaste1() == 5) {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste1() + ",";
									pwaste = pwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity1(),
													"0"));

								} else {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste1() + ",";
									hwaste = hwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity1(),
													"0"));
								}
							}

							if (hwastevo.getWaste2() != null
									&& !hwastevo.getWaste2().equalsIgnoreCase(
											"")) {
								System.out.println("PPPPPPPP:"
										+ hwastevo.getTypeOfWaste2());
								if (hwastevo.getTypeOfWaste2() == 5) {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste2() + ",";
									pwaste = pwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity2(),
													"0"));
								} else {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste2() + ",";
									hwaste = hwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity2(),
													"0"));
								}
							}

							if (hwastevo.getWaste3() != null
									&& !hwastevo.getWaste3().equalsIgnoreCase(
											"")) {
								// System.out.println("QQQQQQQQQ:");
								if (hwastevo.getTypeOfWaste3() == 5) {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste3() + ",";
									pwaste = pwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity3(),
													"0"));
								} else {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste3() + ",";
									hwaste = hwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity3(),
													"0"));
								}
							}

							if (hwastevo.getWaste4() != null
									&& !hwastevo.getWaste4().equalsIgnoreCase(
											"")) {

								if (hwastevo.getTypeOfWaste4() == 5) {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste4() + ",";
									pwaste = pwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity4(),
													"0"));
								} else {
									monthdata[1] = monthdata[1]
											+ hwastevo.getTypeOfWaste4() + ",";
									hwaste = hwaste
											+ Float.parseFloat(checkNullAndFill(
													hwastevo.getWasteQuantity4(),
													"0"));
								}
							}

							if (hwastevo.getHazardousWasteCategory() != -99
									&& hwastevo.getHazardousWasteCategory() > 0
									&& hwastevo.getHazardousWasteCategory() < 4) {

								monthdata[3] = cat[hwastevo
										.getHazardousWasteCategory() - 1];
								monthdata[4] = hwastevo.getManifastno();
								monthdata[5] = hwastevo.getEpaId();
								monthdata[9] = hwastevo
										.getHazardousTotalWaste();
							}

						}

					}
					monthdata[2] = String.valueOf(pwaste + hwaste);

					// }
					// monthdata[2]=String.valueOf(pwaste+hwaste);
					if (hwaste == 0 && pwaste == 0) {
						monthdata[6] = "-";
					} else if (hwaste == 0) {

						if (pwaste < 2.2) {
							monthdata[6] = "CESQG";
						} else if (pwaste < 2.2) {
							monthdata[6] = "SQG";
						} else if (pwaste > 0) {
							monthdata[6] = "LQG";
						}

					} else if (pwaste == 0) {
						if (hwaste <= 220) {
							monthdata[6] = "CESQG";
						} else if (hwaste <= 2205) {
							monthdata[6] = "SQG";
						} else if (hwaste > 0) {
							monthdata[6] = "LQG";
						}

					} else if (monthdata[3].equalsIgnoreCase("CESQG")
							&& hwaste <= 220 && pwaste < 2.2) {
						monthdata[6] = "CESQG";
					} else if (monthdata[3].equalsIgnoreCase("SQG")
							&& hwaste <= 2205 && pwaste < 2.2) {
						monthdata[6] = "SQG";
					}
					/*
					 * else if(monthdata[3].equalsIgnoreCase("LQG") && hwaste>0
					 * && pwaste>0) { monthdata[6]="LQG"; }
					 */
					else {
						monthdata[6] = "-";
					}

					if (monthdata[6].equalsIgnoreCase(monthdata[3])) {
						monthdata[7] = "Compliance";
					} else if (monthdata[3].equalsIgnoreCase("LQG")) {
						monthdata[7] = "Compliance";
					} else {
						monthdata[7] = "Non Compliance";
					}

					datalist.add(monthdata);
				}
				// }
				// }

				hashtable.put("DATA", datalist);
				array_list.add(hashtable);
			}

			System.out.println("Senthil END:");
		} catch (Exception e) {
			System.out.println("" + e);
		}
		return array_list;

	}

	public static List getNycdobStatusReport35(int facid) {
		String arr[] = { "", "DOB", "DEP", "ECB", "DEC", "FD", "EPA",
				"STATE/CITY", "OTHER" };
		LinkedHashMap hash4Unique;
		hash4Unique = new LinkedHashMap();
		SqlUtil sqlutil = new SqlUtil();
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil1 = new SqlUtil();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null; 

		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("SELECT b.BUILDINGNAME,s.VIOLATIONID,s.violationType,s.VIOLATIONWHICH,s.violationno,s.description,s.issueDate,s.remedialMeassures,s.contractor,s.stepsTaken,s.removalDate,s.interMediateStatus,s.finalComplianceDate,s.jDate,s.otherAgency"
							+ " FROM (violation s, building b) "
							+ " WHERE   b.BUILDINGID=s.BUILDINGID AND b.FACILITYID =?");
			preparedstatement.setString(1, String.valueOf(facid));
			Hashtable hashtable;
			resultset = preparedstatement.executeQuery();
			String tempId = "";
			for (; resultset.next(); hash4Unique.put(tempId, hashtable)) {
				hashtable = new Hashtable();
				tempId = resultset.getString("VIOLATIONID");
				hashtable.put("VIOLATIONID", checkNullAndFill(tempId, "-"));
				String violationwhich = resultset.getString("VIOLATIONWHICH");

				String otheragency = checkNullAndFill(
						resultset.getString("OTHERAGENCY"), "-");

				if (Integer.parseInt(violationwhich) == 8)
					hashtable.put("VIOLATIONWHICH", otheragency);
				else
					hashtable.put(
							"VIOLATIONWHICH",
							checkNullAndFill(
									arr[Integer.parseInt(violationwhich)] + "",
									"-"));
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				hashtable.put(
						"VIOLATIONNO",
						checkNullAndFill(resultset.getString("VIOLATIONNO"),
								"-"));
				hashtable.put(
						"VIOLATIONTYPE",
						checkNullAndFill(resultset.getString("violationType"),
								"-"));
				hashtable.put(
						"DESCRIPTION",
						checkNullAndFill(resultset.getString("DESCRIPTION"),
								"-"));
				hashtable.put(
						"ISSUEDATE",
						checkNullAndFilldate(resultset.getString("ISSUEDATE"),
								"-"));
				hashtable.put(
						"REMEDIALMEASSURES",
						checkNullAndFill(
								resultset.getString("REMEDIALMEASSURES"), "-"));
				hashtable
						.put("CONTRACTOR",
								checkNullAndFill(
										resultset.getString("CONTRACTOR"), "-"));
				hashtable
						.put("STEPSTAKEN",
								checkNullAndFill(
										resultset.getString("STEPSTAKEN"), "-"));
				hashtable
						.put("INTERMEDIATESTATUS",
								checkNullAndFill(resultset
										.getString("INTERMEDIATESTATUS"), "-"));
				hashtable.put(
						"REMOVALDATE",
						checkNullAndFilldate(
								resultset.getString("REMOVALDATE"), "-"));

				hashtable.put("OTHERAGENCY", otheragency);

				String dapp = checkNullAndFilldate(
						resultset.getString("FINALCOMPLIANCEDATE"), "-");

				hashtable.put("FINALCOMPLIANCEDATE", dapp);
				hashtable
						.put("JDATE",
								checkNullAndFilldate(
										resultset.getString("JDATE"), "-"));

				if (dapp.equals("-")) {
					hashtable.put("COMPLIANCESTATUS", "Non Compliance");
				}

				else {
					hashtable.put("COMPLIANCESTATUS", "Compliance");
				}

			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}

		if (hash4Unique != null) {
			Set set = hash4Unique.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext(); arraylist
					.add(hash4Unique.get(iter.next())))
				;
		}

		return arraylist;
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return s;
		} else {
			return s1;
		}
	}

	public static String checkNullAndFillcoma(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return s + " ";
		} else {
			return s1;
		}
	}

	public static String checkNullAndFillf(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return (new StringBuilder()).append(s).append("/").toString();
		} else {
			return s1;
		}
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static String checkNullAndFilldate(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			String as[] = s.split("-");
			String s2 = (new StringBuilder()).append(as[1]).append("/")
					.append(as[2]).append("/").append(as[0]).toString();
			return s2;
		} else {
			return s1;
		}
	}

	public static double round(double d, int i) {
		double d1;
		for (d1 = 1.0D; i-- > 0; d1 *= 10D)
			;
		return (double) Math.round(d * d1) / d1;
	}
}