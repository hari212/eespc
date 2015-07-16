package sevenb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Test.TestFacility;

import com.eespc.tracking.util.SqlUtil;

public class fromseventh7 {

	public fromseventh7() {
	}

	public static List getNycdobStatusReport15() {
		ArrayList arraylist = new ArrayList();
		SqlUtil sqlutil = new SqlUtil();
		Object obj = null;
		Object obj1 = null;
		Object obj2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eespc", "root", "root");
			PreparedStatement preparedstatement = connection
					.prepareStatement("select g.facilitydesignatedid,b.ISSUEDATE,g.capacity,g.productstored,d.testdate,"
							+ "d.nexttestdate,a.buildingname,b.EXPIRATIONDATE,bo.floor,bo.boilerid,bo.testcondu"
							+ "ctedby,c.tanktype,p.tankstored,s.tankstatus from (building a,storagetank g) left"
							+ " join tanktightnessinfo d on g.STORAGETANKID=d.storagetankid left join storagepe"
							+ "rmitinfo b on b.storagetankid=g.storagetankid left join boiler bo on bo.fuelfrom"
							+ "=g.storagetankid left join tanktype c on c.id=g.tanktype left join productStored"
							+ " p on p.pid=g.productstored left join tankstatus s on g.tankstatus=s.statusid wh"
							+ "ere g.buildingid=a.buildingid and a.facilityid=?");
			preparedstatement.setString(1,
					String.valueOf(TestFacility.getCurrentFacilityID()));
			Hashtable hashtable;
			for (ResultSet resultset = preparedstatement.executeQuery(); resultset
					.next(); arraylist.add(hashtable)) {
				hashtable = new Hashtable();
				hashtable.put(
						"facilitydesignatedid",
						checkNullAndFill(
								resultset.getString("facilitydesignatedid"),
								"-"));
				hashtable
						.put("ISSUEDATE",
								checkNullAndFill(
										resultset.getString("ISSUEDATE"), "-"));
				hashtable.put("capacity",
						checkNullAndFill(resultset.getString("capacity"), "-"));
				hashtable.put(
						"productstored",
						checkNullAndFill(resultset.getString("productstored"),
								"-"));
				hashtable.put("testdate",
						checkNullAndFill(resultset.getString("testdate"), "-"));
				hashtable.put(
						"nexttestdate",
						checkNullAndFill(resultset.getString("nexttestdate"),
								"-"));
				hashtable.put(
						"buildingname",
						checkNullAndFill(resultset.getString("buildingname"),
								"-"));
				hashtable.put(
						"EXPIRATIONDATE",
						checkNullAndFill(resultset.getString("EXPIRATIONDATE"),
								"-"));
				hashtable.put("floor",
						checkNullAndFill(resultset.getString("floor"), "-"));
				hashtable.put("boilerid",
						checkNullAndFill(resultset.getString("boilerid"), "-"));
				hashtable.put(
						"testconductedby",
						checkNullAndFill(
								resultset.getString("testconductedby"), "-"));
				hashtable.put("tanktype",
						checkNullAndFill(resultset.getString("tanktype"), "-"));
				hashtable
						.put("tankstored",
								checkNullAndFill(
										resultset.getString("tankstored"), "-"));
				hashtable
						.put("tankstatus",
								checkNullAndFill(
										resultset.getString("tankstatus"), "-"));
			}

		} catch (Exception exception) {
			System.out.println("hai" + exception);
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

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}
}
