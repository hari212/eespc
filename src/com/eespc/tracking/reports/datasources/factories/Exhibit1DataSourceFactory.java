package com.eespc.tracking.reports.datasources.factories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import com.eespc.tracking.entity.BuildingEntity;
import com.eespc.tracking.reports.datasources.HashDataSource;
import com.eespc.tracking.util.SqlUtil;

// Referenced classes of package com.eespc.tracking.reports.datasources.factories:
//            AbstractDataSourceFactory

public class Exhibit1DataSourceFactory extends AbstractDataSourceFactory {

	static int inn = 0;

	public Exhibit1DataSourceFactory() {
	}

	public static void main(String args[]) {
		Exhibit1DataSourceFactory exhibit1datasourcefactory = new Exhibit1DataSourceFactory();
		JRDataSource jrdatasource = exhibit1datasourcefactory.getDataSource();
		try {
			while (jrdatasource.next()) {
				System.out.println("next");
			}
		} catch (JRException jrexception) {
			jrexception.printStackTrace();
		}
	}

	public JRDataSource getDataSource() {
		List list = BuildingEntity.getBuildingsInFacility(87);
		HashDataSource hashdatasource = new HashDataSource(list);
		return hashdatasource;
	}

	public JRDataSource getDataSource(int i) {
		List list = getlist(i);
		HashDataSource hashdatasource = new HashDataSource(list);
		return hashdatasource;
	}

	public static List getlist(int i) {
		ArrayList arraylist = new ArrayList();
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		SqlUtil sqlutil = new SqlUtil();
		try {
			SqlUtil sqlutil1 = new SqlUtil();
			connection = sqlutil1.getConnection();
			preparedstatement = connection
					.prepareStatement("select a.BUILDINGNAME,a.ADDRESSID,a.DOBBINNUMBER,a.BLOCKNUMBER,a.LOTNUMBER,a.BLD"
							+ "GSIXSTORIES,a.BOROUGH,b.FACADEINSPFORDOB,b.NEXTINSPDATE,a.cofobtaining,a.cofno from buildi"
							+ "ng a left join buildinginspectioninfo b on b.BUILDINGID=a.BUILDINGID  where a.fa"
							+ "cilityid=?");
			preparedstatement.setString(1, String.valueOf(i));
			Hashtable hashtable;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); arraylist
					.add(hashtable)) {
				hashtable = new Hashtable();
				hashtable.put(
						"BUILDINGNAME",
						checkNullAndFill(resultset.getString("BUILDINGNAME"),
								"-"));
				String s = checkNullAndFill(resultset.getString("ADDRESSID"),
						"-");
				String adds = getaddr(s);
				hashtable.put("bldgAddress", checkNullAndFill(adds, "-"));
				hashtable.put(
						"DOBBINNUMBER",
						checkNullAndFill(resultset.getString("DOBBINNUMBER"),
								"-"));
				hashtable.put(
						"BLOCKNUMBER",
						checkNullAndFill(resultset.getString("BLOCKNUMBER"),
								"-"));
				hashtable
						.put("LOTNUMBER",
								checkNullAndFill(
										resultset.getString("LOTNUMBER"), "-"));

				Integer borough = resultset.getInt("BOROUGH");
				// hashtable.put("BOROUGH", checkNullAndFill(borough, "-"));

				/*
				 * String borough = resultset.getInteger(("BOROUGH"), "-");
				 * hashtable.put("BOROUGH", checkNullAndFill(BOROUGH, "-"));
				 */

				String nextinspdate = checkNullAndFill(
						resultset.getString("NEXTINSPDATE"), "-");
				String facadeinspfordob = checkNullAndFill(
						resultset.getString("FACADEINSPFORDOB"), "-");
				String cofo = checkNullAndFill(
						resultset.getString("cofobtaining"), "-");
				hashtable.put("COFO", cofo);
				String cofono = checkNullAndFill(resultset.getString("cofno"),
						"-");
				hashtable.put("COFONO", cofono);

				String s1 = "";
				s1 = resultset.getString("BLDGSIXSTORIES");
				int j = Integer.parseInt(checkNullAndFill(s1, "0"));

				if (j > 5 && borough != 1) {
					hashtable.put("FACADEINFO", "Inspection required");

					if (facadeinspfordob.equals("-") && cofo.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "TBD");
					} else if (nextinspdate.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (facadeinspfordob.equals("N")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("N")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("Y") && cofono.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "TBD");
					} else {
						hashtable.put("COMPLIANCESTATUS", "Compliance");
					}
				} else if (j > 5 && borough == 1) {
					hashtable.put("FACADEINFO", "Not required");

					if (cofo.equals("N")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("Y") && cofono.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "TBD");
					} else {
						hashtable.put("COMPLIANCESTATUS", "Compliance");
					}
				} else if (j <= 5) {
					hashtable.put("FACADEINFO", "Not Applicable");
					if (cofo.equals("N")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("Y") && cofono.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "Non Compliance");
					} else if (cofo.equals("-")) {
						hashtable.put("COMPLIANCESTATUS", "TBD");
					} else {
						hashtable.put("COMPLIANCESTATUS", "Compliance");
					}
				}
				/*
				 * else { hashtable.put("FACADEINFO", "-");
				 * if(facadeinspfordob.equals("-") && cofo.equals("-")) {
				 * hashtable.put("COMPLIANCESTATUS", "TBD"); } else
				 * if(facadeinspfordob.equals("-")) {
				 * hashtable.put("COMPLIANCESTATUS", "TBD"); }
				 * if(cofo.equals("N")) { hashtable.put("COMPLIANCESTATUS",
				 * "Non Compliance"); } else if(cofo.equals("Y") &&
				 * cofono.equals("-")) { hashtable.put("COMPLIANCESTATUS",
				 * "Non Compliance"); } else if(cofo.equals("-")) {
				 * hashtable.put("COMPLIANCESTATUS", "TBD"); } else {
				 * hashtable.put("COMPLIANCESTATUS", "Compliance"); } }
				 */
				hashtable.put("BLDGSIXSTORIES", checkNullAndFill(s1, "-"));
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("Exhibit 1 datasource factory exception")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return arraylist;
	}

	public static String getfoot(int i) {
		inn = 0;
		SqlUtil sqlutil = new SqlUtil();
		String notes = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			SqlUtil sqlutil1 = new SqlUtil();
			connection = sqlutil1.getConnection();
			preparedstatement = connection
					.prepareStatement("select * from building where facilityid=?");
			preparedstatement.setString(1, String.valueOf(i));
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s = checkNullAndFillnote(
						resultset.getString("FOOTNOTE"), "");
				notes = (new StringBuilder()).append(notes).append(s)
						.toString();
			}

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("hai")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return notes;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return s;
		} else {
			return s1;
		}
	}

	public static String checkNullAndFillnote(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			inn++;
			return (new StringBuilder()).append(inn).append(": ").append(s)
					.append("\n").toString();
		} else {
			return s1;
		}
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

	public static String getaddr(String s) {
		String s1 = "";
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			SqlUtil sqlutil = new SqlUtil();
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select address1,city,state,zipcode from address where addressid =?");
			preparedstatement.setString(1, s);
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s1 = (new StringBuilder()).append(
						checkNullAndFill(resultset.getString("address1"), ""))
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

}
