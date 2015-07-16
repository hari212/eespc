package com.eespc.tracking.entity;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class ExpirationEntity {

	public ExpirationEntity() {
		sqlutil = new SqlUtil();
		uo = new UtilityObject();
	}

	public String getStatehtml(String s, String s1, String s2) throws Exception {
		String s3 = "";
		String query = "select fexpirationdate from statepermit  where facilityid='"
				+ s
				+ "' AND fexpirationdate BETWEEN '"
				+ s1
				+ "' AND '"
				+ s2
				+ "'";
		System.out.println(query);
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection.prepareStatement(query);
			resultset = preparedstatement.executeQuery();
			do {
				if (!resultset.next())
					break;

				String s5 = "";
				s5 = resultset.getString("fexpirationdate");
				System.out.println(s5);
				if (s5 != "" && !s5.equals("")) {
					s3 = s3
							+ (new StringBuilder())
									.append(s3)
									.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#C0C0C0\" bordercolor=\"#FF0000\"><font size=\"1\">")
									.append("</font></font></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\">")
									.append("State Permit")
									.append("</td><td  bgcolor=\"#E4E8E3\">")
									.append(uo.convertYyyyMmDd2MmDdYyyy(s5))
									.append("</td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td></tr>")
									.toString();

				}
			} while (true);

		} catch (Exception exception) {
			System.out.println(exception);
			System.out.println((new StringBuilder()).append("gethtml")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s3;
	}

	public String getStatenextproposalhtml(String s, String s1, String s2)
			throws Exception {

		String s3 = "";
		String query = "select fnextproposal from statepermit  where facilityid='"
				+ s
				+ "'  AND fnextproposal BETWEEN '"
				+ s1
				+ "' AND '"
				+ s2
				+ "'";
		System.out.println(query);
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection.prepareStatement(query);
			resultset = preparedstatement.executeQuery();
			do {
				if (!resultset.next())
					break;
				String s5 = "";
				s5 = resultset.getString("fnextproposal");
				System.out.println("Expiration report" + s5);
				if (s5 != "" && !s5.equals("")) {
					s3 = s3
							+ (new StringBuilder())
									.append(s3)
									.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#C0C0C0\" bordercolor=\"#FF0000\"><font size=\"1\">")
									.append("</font></font></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\">")
									.append("State Permit Next Proposal Date")
									.append("</td><td  bgcolor=\"#E4E8E3\">")
									.append(uo.convertYyyyMmDd2MmDdYyyy(s5))
									.append("</td><td  bgcolor=\"#E4E8E3\">Refer to State Pertmit Next Proposal Date and for further help Please contact EES</td><td  bgcolor=\"#E4E8E3\">Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td  bgcolor=\"#E4E8E3\"></td></tr>")
									.toString();
				}
			} while (true);

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("gethtml")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s3;
	}

	public String getcertificateoffitnesshtml(String s, String s1, String s2)
			throws Exception {

		String s3 = "";
		String query = "select expirationdate, firstname, lastname, certificatenumber from lsf  where facilityid='"
				+ s
				+ "'  AND expirationdate BETWEEN '"
				+ s1
				+ "' AND '"
				+ s2
				+ "'";
		System.out.println(query);
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection.prepareStatement(query);
			resultset = preparedstatement.executeQuery();
			do {
				if (!resultset.next())
					break;
				String s5 = "";
				s5 = resultset.getString("expirationdate");

				if (s5 != "" && !s5.equals("")) {
					// s3 =s3+ (new
					// StringBuilder()).append("<tr><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Staff:<font color=\"#FF66FF\">").append(resultset.getString("firstname")).append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Certificate Number[<font color=\"#FF66FF\">").append(resultset.getString("certificatenumber")).append("</font>]</td><td >").append("Facility Certificate of Fitness Expiration Date").append("</td><td  bgcolor=\"#E4E8E3\">").append(uo.convertYyyyMmDd2MmDdYyyy(s5)).append("</td><td  bgcolor=\"#E4E8E3\">Refer to Facility Certificate of Fitness Expiration Date and for further help Please contact EES</td><td  bgcolor=\"#E4E8E3\">Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td  bgcolor=\"#E4E8E3\"></td></tr>").toString();
					s3 = s3
							+ (new StringBuilder())
									.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td bgcolor=\"#E4E8E3\"><b>Staff: </b>")
									.append(resultset.getString("firstname"))
									.append(" ")
									.append(resultset.getString("lastname"))
									.append("</td><td  bgcolor=\"#E4E8E3\">Certificate Number[<font color=\"#FF66FF\">")
									.append(resultset
											.getString("certificatenumber"))
									.append("</font>]</td><td bgcolor=\"#E4E8E3\">")
									.append("Facility Certificate of Fitness Expiration Date")
									.append("</td><td  bgcolor=\"#E4E8E3\">")
									.append(uo.convertYyyyMmDd2MmDdYyyy(s5))
									.append("</td><td  bgcolor=\"#E4E8E3\">Refer to Facility Certificate of Fitness Expiration Date and for further help Please contact EES</td><td  bgcolor=\"#E4E8E3\">Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td  bgcolor=\"#E4E8E3\"></td></tr>")
									.toString();

				}
			} while (true);

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getcertificateoffitnesshtml").append(exception)
					.toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s3;
	}

	public String getcertificateoffitnessrenewaldatehtml(String s, String s1,
			String s2) throws Exception {

		String s3 = "";
		String query = "select renewaldate from lsf where facilityid='" + s
				+ "'  AND renewaldate BETWEEN '" + s1 + "' AND '" + s2 + "'";
		System.out.println(query);
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection.prepareStatement(query);
			resultset = preparedstatement.executeQuery();
			do {
				if (!resultset.next())
					break;
				String s5 = "";
				s5 = resultset.getString("renewaldate");

				if (s5 != "" && !s5.equals("")) {
					s3 = s3
							+ (new StringBuilder())
									.append(s3)
									.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#C0C0C0\" bordercolor=\"#FF0000\"><font size=\"1\">")
									.append("</font></font></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\">")
									.append("Facility Certificate of Fitness Renewal Date")
									.append("</td><td  bgcolor=\"#E4E8E3\">")
									.append(uo.convertYyyyMmDd2MmDdYyyy(s5))
									.append("</td><td  bgcolor=\"#E4E8E3\">Refer to Facility Certificate of Fitness Renewal Date and for further help Please contact EES</td><td  bgcolor=\"#E4E8E3\">Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td  bgcolor=\"#E4E8E3\"></td></tr>")
									.toString();
				}
			} while (true);

		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getcertificateoffitnessrenewaldatehtml")
					.append(exception).toString());
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}
		return s3;
	}

	public String gethtml(String s, String s1, String s2) throws Exception {
		System.out.println("EESCTS TEST:" + s + "/" + s1 + "/" + s2);
		FacilityVo facilityvo = (FacilityVo) FacilityEntity.findById(Integer
				.parseInt(s));
		int boro = facilityvo.getBorough();
		if (boro == 1) {
			deptitle = "DEP/DOH";
			dobtitle = "DOB/TOWN/CITY";
			fdtitle = "FD";
			dectitle = "DEC";
			dohtitle = "DOH";
		} else {
			deptitle = "DEP";
			dobtitle = "DOB";
			fdtitle = "FD";
			dectitle = "DEC";
			dohtitle = "DOH";
		}

		String s3 = "";
		Object obj = null;
		Connection connection = null;
		ResultSet resultset = null;
		PreparedStatement preparedstatement = null;
		try {
			connection = sqlutil.getConnection();
			preparedstatement = connection
					.prepareStatement("select buildingname,BUILDINGID from building where facilityid=?");
			preparedstatement.setString(1, s);
			resultset = preparedstatement.executeQuery();
			do {
				if (!resultset.next())
					break;
				String s4 = resultset.getString("BUILDINGID");
				String s5 = "";

				// System.out.println("EESCTS TESTC:"+s4);
				s5 = getreport(s4, s1, s2, connection).trim();
				if (s5 != "" && !s5.equals("")) {
					s3 = (new StringBuilder())
							.append(s3)
							.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#C0C0C0\" bordercolor=\"#FF0000\"><font size=\"1\">")
							.append(resultset.getString("buildingname"))
							.append("</font></font></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td></tr>")
							.toString();
					s3 = (new StringBuilder()).append(s3).append(s5).toString();
				}
			} while (true);
			SqlUtil _tmp = sqlutil;
			SqlUtil _tmp1 = sqlutil;

		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("gethtml")
					.append(exception).toString());
			File file = new File("c:/test.txt");
			Object obj1 = null;
			Object obj2 = null;
			PrintWriter printwriter = null;
			try {
				file.createNewFile();
				FileWriter filewriter = new FileWriter(file);
				BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
				printwriter = new PrintWriter(bufferedwriter, false);
				printwriter.println((new StringBuilder()).append("gethtml")
						.append(exception).toString());
			} catch (Throwable throwable) {
			}
			printwriter.close();
			throw exception;
		} finally {
			SqlUtil.close(resultset);
			SqlUtil.close(preparedstatement);
			SqlUtil.close(connection);
		}

		// System.out.println("EESCTS TEST:"+s3);
		return s3;
	}

	public String getreport(String s, String s1, String s2,
			Connection connection) {
		String s3 = "";
		s3 = (new StringBuilder()).append(s3)
				.append(getbuildinginspectiondate(s, s1, s2, connection))
				.toString();// **********
		s3 = (new StringBuilder())
				.append(s3)
				.append(getbuildingnotificationtoeesdate(s, s1, s2, connection))
				.toString();// **********
		s3 = (new StringBuilder()).append(s3)
				.append(getviolationchdate(s, s1, s2, connection)).toString();// **********
		s3 = (new StringBuilder()).append(s3)
				.append(getabsorber(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getboiler(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getboilerstacktest(s, s1, s2, connection)).toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getboilersubmital(s, s1, s2, connection)).toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getbridge(s, s1, s2, connection)).toString();// ********
		s3 = (new StringBuilder()).append(s3)
				.append(gettunnel(s, s1, s2, connection)).toString();// ********
		s3 = (new StringBuilder()).append(s3)
				.append(getchillerrefrigation(s, s1, s2, connection))
				.toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getcogenengine(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getcogenenginestacktest(s, s1, s2, connection))
				.toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getIncineratorcrematories(s, s1, s2, connection))
				.toString();
		s3 = (new StringBuilder())
				.append(s3)
				.append(getincineratorcrematoriessolidwastepermit(s, s1, s2,
						connection)).toString();// *********
		s3 = (new StringBuilder())
				.append(s3)
				.append(getincineratorcrematoriesstacktest(s, s1, s2,
						connection)).toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getelevatorreportsubmittaldate(s, s1, s2, connection))
				.toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getelevatorinspectiondate(s, s1, s2, connection))
				.toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(geteto(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getetostacktest(s, s1, s2, connection)).toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getfumehood(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getgenerator(s, s1, s2, connection)).toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getgeneratorstacktest(s, s1, s2, connection))
				.toString();// *********
		s3 = (new StringBuilder()).append(s3)
				.append(getlandfill(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getmis(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getspraybooth(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getpress(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getrpzstacktest(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getrpz(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getstoragetank(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getstoragetanktightness(s, s1, s2, connection))
				.toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getbulkoxygen(s, s1, s2, connection)).toString();
		// s3 = (new StringBuilder()).append(s3).append(getstack(s, s1, s2,
		// connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getstackmethodnine(s, s1, s2, connection)).toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getstoragetankpbsdate(s, s1, s2, connection))
				.toString();
		s3 = (new StringBuilder()).append(s3)
				.append(getstoragetankcathodictest(s, s1, s2, connection))
				.toString();
		s3 = (new StringBuilder())
				.append(s3)
				.append(getstoragetankcathodicinstallationtest(s, s1, s2,
						connection)).toString();
		return s3;
	}

	public String getbuildinginspectiondate(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.NEXTINSPDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();
		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select b.NEXTINSPDATE ,b.filingdatenotes from buildinginspectioninfo b where  b.buildingid=? ")
				.append(s4).toString();
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String chdep = deptitle;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Building</td><td >Next Inspection Date </td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTINSPDATE")))
						.append("</td><td>Refer to Building-Facade Next Inspection Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("filingdatenotes"))
						.append("</td></tr>").toString();
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getbuildinginspectiondate").append(exception)
					.toString());
		}
		return s5;
	}

	public String getbuildingnotificationtoeesdate(String s, String s1,
			String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.ACTUALINSPDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();
		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select b.ACTUALINSPDATE from buildinginspectioninfo b where  b.buildingid=? ")
				.append(s4).toString();
		try {

			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String chdep = deptitle;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Building</td><td >Facade Notification to EES</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("ACTUALINSPDATE")))
						.append("</td><td>Refer to Building-Facade Inspection Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append("").append("</td></tr>").toString();
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getbuildingnotificationtoeesdate")
					.append(exception).toString());
		}
		return s5;
	}

	public String getviolationchdate(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder()).append(" AND b.jDate BETWEEN '")
				.append(s1).append("' AND '").append(s2).append("'").toString();
		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select b.jDate, b.violationno, b.description from violation b where  b.buildingid=? ")
				.append(s4).toString();
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String chdep = deptitle;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Violation[<font color=\"#FF66FF\">")
						.append(resultset.getString("violationno"))
						.append("</font>]</td><td><b>Court Hearing Date</b></td><td><b>")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("jDate")))
						.append("</b></td><td>Refer to Violation Details and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("description"))
						.append("</td></tr>").toString();
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getviolationchdate").append(exception).toString());
		}
		return s5;
	}

	public String getabsorber(String s, String s1, String s2,
			Connection connection) {

		String s4 = (new StringBuilder())
				.append(" AND a.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();
		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select a.FACILITYDESIGNATEDID,a.EXPIRATIONDATE,a.CHFOOTNOTE,a.MODIFIEDINPAST from chillerabsorber a where a.MODIFIEDINPAST='1' and a.buildingid=?")
				.append(s4).toString();
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String chdep = deptitle;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Chiller/Absorber[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >" + chdep
								+ " Expiration Date</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Chiller/Absorber "
								+ deptitle
								+ " Permit  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("CHFOOTNOTE"))
						.append("</td></tr>").toString();
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getabsorber")
					.append(exception).toString());
		}
		return s5;
	}

	public String getboiler(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();
		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,b.depid,b.EXPIRATIONDATE,b.DEPEXPIRATIONNOTE,b.DOBEXPIRATIONNOTE,a.MODIFIEDINPAST from (boiler a,boilerpermitinfo b) where (b.depid='1' or b.depid='2') and a.MODIFIEDINPAST='1' and a.boilerid=b.boilerid and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String deob = resultset.getString("depid");
				String s6 = "";
				if (deob.equals("1")) {
					s6 = deptitle;
					s5 = (new StringBuilder())
							.append(s5)
							.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >BOILER[<font color=\"#FF66FF\">")
							.append(resultset.getString("FACILITYDESIGNATEDID"))
							.append("</font>]</td><td >")
							.append(s6 + " Expiration Date")
							.append("</td><td >")
							.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
									.getString("EXPIRATIONDATE")))
							.append("</td><td>Refer to Boiler "
									+ deptitle
									+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
							.append(resultset.getString("DEPEXPIRATIONNOTE"))
							.append("</td></tr>").toString();
				}

				else if (deob.equals("2")) {
					s6 = dobtitle;
					s5 = (new StringBuilder())
							.append(s5)
							.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >BOILER[<font color=\"#FF66FF\">")
							.append(resultset.getString("FACILITYDESIGNATEDID"))
							.append("</font>]</td><td >")
							.append(s6 + "/Next Inspection Date")
							.append("</td><td >")
							.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
									.getString("EXPIRATIONDATE")))
							.append("</td><td>Refer to Boiler "
									+ dobtitle
									+ " Permit Next Inspection Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
							.append(resultset.getString("DOBEXPIRATIONNOTE"))
							.append("</td></tr>").toString();
				}
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getboiler")
					.append(exception).toString());
		}
		return s5;
	}

	public String getboilerstacktest(String s, String s1, String s2,
			Connection connection) {

		String s4 = (new StringBuilder())
				.append(" AND a.NEXTSTACKTESTDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select a.FACILITYDESIGNATEDID,a.NEXTSTACKTESTDATE,a.BFOOTNOTE,a.MODIFIEDINPAST from boiler a where a.MODIFIEDINPAST='1' and a.buildingid=?")
				.append(s4).toString();
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String chdep = deptitle;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Boiler[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >Next Stack Test Date</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTSTACKTESTDATE")))
						.append("</td><td>Refer to Boiler Next Stack Test Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("BFOOTNOTE"))
						.append("</td></tr>").toString();
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getboilerstacktest").append(exception).toString());
		}
		return s5;
	}

	public String getboilersubmital(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.INSPSUBMITTEDDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.INSPSUBMITTEDDATE,b.DEPCOMPLIANCECOMMENTS from (boiler a,boilerpermitinfo b) where (b.depid='1' or b.depid='2') and a.boilerid=b.boilerid and a.MODIFIEDINPAST='1' and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String deob = resultset.getString("depid");
				String s6 = "";
				if (deob.equals("1")) {
					s6 = deptitle;
					s5 = (new StringBuilder())
							.append(s5)
							.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >BOILER[<font color=\"#FF66FF\">")
							.append(resultset.getString("FACILITYDESIGNATEDID"))
							.append("</font>]</td><td >")
							.append(s6 + "/Renewal Submittal Date")
							.append("</td><td >")
							.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
									.getString("INSPSUBMITTEDDATE")))
							.append("</td><td>Refer to Boiler "
									+ deptitle
									+ " Permit Renewal Submittal Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
							.append("").append("</td></tr>").toString();
				} else if (deob.equals("2")) {
					s6 = dobtitle;
					s5 = (new StringBuilder())
							.append(s5)
							.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >BOILER[<font color=\"#FF66FF\">")
							.append(resultset.getString("FACILITYDESIGNATEDID"))
							.append("</font>]</td><td >")
							.append(s6 + "/Submittal Date")
							.append("</td><td >")
							.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
									.getString("INSPSUBMITTEDDATE")))
							.append("</td><td>Refer to Boiler "
									+ dobtitle
									+ " Permit Submittal Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
							.append("").append("</td></tr>").toString();
				}
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getboilersubmital").append(exception).toString());
		}
		return s5;
	}

	public String getbridge(String s, String s1, String s2,
			Connection connection) {

		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.ISSUEDATE,b.EXPIRATIONDATE,b.DOTEXPDATENOTE from (bridgetunnels a,bridgetunnelpermitinfo b) where a.ISBRIDGE='Y' and a.MODIFIEDINPAST='1'and depid='9' and a.BRIDGETUNNELID=b.BRIDGETUNNELID and a.buildingid=? ")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("9"))
					s6 = "DOT";
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">BRIDGE[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Bridge DOT Permit  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DOTEXPDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getbridge1")
					.append(exception).toString());
		}

		String s4b = (new StringBuilder())
				.append(" AND b.INSEXPDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5b = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.INSEXPDATE,b.DOTINSEXPDATENOTE from (bridgetunnels a,bridgetunnelpermitinfo b) where a.MODIFIEDINPAST='1' and a.ISBRIDGE='Y' and b.depid='9' and a.BRIDGETUNNELID=b.BRIDGETUNNELID and a.buildingid=?")
							.append(s4b).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6b = "";
				if (resultset.getString("depid").equals("9"))
					s6b = "INSURENCE";
				s5b = (new StringBuilder())
						.append(s5b)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">BRIDGE[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6b + "  Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("INSEXPDATE")))
						.append("</td><td>Refer to Bridge Insurence Permit  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DOTINSEXPDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getbridge2")
					.append(exception).toString());
		}

		String s4c = (new StringBuilder())
				.append(" AND b.NEXTINSPDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5c = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.NEXTINSPDATE,b.DOTNEXTINSPDATENOTE from (bridgetunnels a,bridgetunnelpermitinfo b) where a.MODIFIEDINPAST='1' and a.ISBRIDGE='Y' and b.depid='9' and a.BRIDGETUNNELID=b.BRIDGETUNNELID and a.buildingid=?")
							.append(s4c).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6c = "";
				if (resultset.getString("depid").equals("9"))
					s6c = "Next Inspection Date";
				s5c = (new StringBuilder())
						.append(s5c)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">BRIDGE[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6c)
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTINSPDATE")))
						.append("</td><td>Refer to Bridge Next Inspection Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DOTNEXTINSPDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getbridge3")
					.append(exception).toString());
		}

		return s5 + s5b + s5c;
	}

	public String gettunnel(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.ISSUEDATE,b.EXPIRATIONDATE,b.DOTEXPDATENOTE from (bridgetunnels a,bridgetunnelpermitinfo b) where a.MODIFIEDINPAST='1' and a.ISBRIDGE='N' and depid='9' and a.BRIDGETUNNELID=b.BRIDGETUNNELID and a.buildingid=? ")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("9"))
					s6 = "DOT";
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Tunnel[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + "  Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Tunnel DOT Permit  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DOTEXPDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("gettunnel")
					.append(exception).toString());
		}

		String s4b = (new StringBuilder())
				.append(" AND b.INSEXPDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5b = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.INSEXPDATE,b.DOTINSEXPDATENOTE from (bridgetunnels a,bridgetunnelpermitinfo b) where a.MODIFIEDINPAST='1' and a.ISBRIDGE='N' and b.depid='9' and a.BRIDGETUNNELID=b.BRIDGETUNNELID and a.buildingid=?")
							.append(s4b).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6b = "";
				if (resultset.getString("depid").equals("9"))
					s6b = "INSURENCE";
				s5b = (new StringBuilder())
						.append(s5b)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">TUNNEL[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6b)
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("INSEXPDATE")))
						.append("</td><td>Refer to Tunnel Insurence Permit  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DOTINSEXPDATENOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getbridge")
					.append(exception).toString());
		}

		String s4c = (new StringBuilder())
				.append(" AND b.NEXTINSPDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5c = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.NEXTINSPDATE,b.DOTNEXTINSPDATENOTE from (bridgetunnels a,bridgetunnelpermitinfo b) where a.MODIFIEDINPAST='1' and a.ISBRIDGE='N' and b.depid='9' and a.BRIDGETUNNELID=b.BRIDGETUNNELID and a.buildingid=?")
							.append(s4c).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6c = "";
				if (resultset.getString("depid").equals("9"))
					s6c = "Next Inspection Date";
				s5c = (new StringBuilder())
						.append(s5c)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">TUNNEL[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6c)
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTINSPDATE")))
						.append("</td><td>Refer to Tunnel  Next Inspection Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DOTNEXTINSPDATENOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getbridge")
					.append(exception).toString());
		}

		return s5 + s5b + s5c;
	}

	public String getchillerrefrigation(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.EPASUBMITTALDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.EPASUBMITTALDATE,a.STATUS from chillerrefrigation a where a.STATUS='1' and a.buildingid=? ")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Chiller With Air-conditioning - Refrigeration [<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("EPA Submittal Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EPASUBMITTALDATE")))
						.append("</td><td>Refer to Chiller With Air-conditioning - Refrigeration  EPA Submittal Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append("").append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getchillerrefrigation").append(exception)
					.toString());
		}
		return s5;
	}

	public String getcogenengine(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.STATUS,b.depid,b.EXPIRATIONDATE,b.NOTE from (cogenturbine a,cogenturbinepermitinfo b) where a.COGENTURBINEID=b.COGENTURBINEID and a.STATUS='1' and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("4"))
					s6 = deptitle;
				else
					s6 = "STATE PERMIT";
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Co Gen Engines[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to COGENTURBINE "
								+ s6
								+ "  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getcogenengine")
					.append(exception).toString());
		}
		return s5;
	}

	public String getcogenenginestacktest(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.NEXTSTACKTESTDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.NEXTSTACKTESTDATE,a.NEXTSTACKTESTNOTE,a.STATUS from cogenturbine a where a.STATUS='1' and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				/*
				 * String s6 = ""; if(resultset.getString("depid").equals("4"))
				 * s6 = deptitle; else s6 = "STATE PERMIT";
				 */
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Co Gen Engines[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Next Stack Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTSTACKTESTDATE")))
						.append("</td><td>Refer to Cogenturbine Next Stack Test Date  and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTSTACKTESTNOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getcogenenginestacktest").append(exception)
					.toString());
		}
		return s5;
	}

	public String getIncineratorcrematories(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.STATUS,b.depid,b.EXPIRATIONDATE,b.NOTE from (incineratorcrematories a,incineratorcrematorpermitinfo b) where a.STATUS='1' and b.depid='2' and a.INCINERATORCREMATORIESID=b.INCINERATORCREMATORIESID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("2"))
					s6 = deptitle;
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Incinerator/Crematories[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Incinerator/Crematories "
								+ s6
								+ " Permit  Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getIncineratorcrematories").append(exception)
					.toString());
		}
		return s5;
	}

	public String getincineratorcrematoriesstacktest(String s, String s1,
			String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.NEXTSTACKTEST BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.NEXTSTACKTEST,a.NEXTSTACKTESTNOTE,a.STATUS from incineratorcrematories a where a.STATUS='1' and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Incinerator/Crematories[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(" Next Stack Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTSTACKTEST")))
						.append("</td><td>Refer to Incinerator/Crematories Next Stack Test Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTSTACKTESTNOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getincineratorcrematoriesstacktest")
					.append(exception).toString());
		}
		return s5;
	}

	public String getincineratorcrematoriessolidwastepermit(String s,
			String s1, String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.I_SOLIDEXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.I_SOLIDEXPIRATIONDATE,a.SOLIDWASTEEXPIRATIONNOTE,a.STATUS from incineratorcrematories a where a.STATUS='1' and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				/*
				 * String s6 = ""; if(resultset.getString("depid").equals("2"))
				 * s6 = deptitle;
				 */
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Incinerator/Crematories[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Solid Waste Permit Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("I_SOLIDEXPIRATIONDATE")))
						.append("</td><td>Refer to Incinerator/Crematories Solid Waste Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("SOLIDWASTEEXPIRATIONNOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getincineratorcrematoriessolidwastepermit")
					.append(exception).toString());
		}
		return s5;
	}

	public String getelevatorinspectiondate(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.NEXTINSPECTIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.NEXTINSPECTIONDATE,b.NEXTINSPDATENOTE from (elevators a,elevatorpermitinfo b) where a.MODIFIEDINPAST='1' and a.ELEVATORID=b.ELEVATORID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;

			for (resultset = preparedstatement.executeQuery(); resultset.next();) {

				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Elevator[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(dobtitle + " Next Inspection Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTINSPECTIONDATE")))
						.append("</td><td>Refer to Elevator "
								+ dobtitle
								+ " Next Inspection Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTINSPDATENOTE"))
						.append("</td></tr>").toString();

			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {

			System.out.println((new StringBuilder())
					.append("getelevatorinspectiondate").append(exception)
					.toString());

		}
		return s5;
	}

	public String getelevatorreportsubmittaldate(String s, String s1,
			String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.REPORTSUBMITTALDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.REPORTSUBMITTALDATE,b.REPORTSUBMITDATENOTE from (elevators a,elevatorpermitinfo b) where a.MODIFIEDINPAST='1' and a.ELEVATORID=b.ELEVATORID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {

				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Elevator[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(dobtitle + " Report Submittal Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("REPORTSUBMITTALDATE")))
						.append("</td><td>Refer to Elevator "
								+ dobtitle
								+ " Report Submittal Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("REPORTSUBMITDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getelevatorreportsubmittaldate").append(exception)
					.toString());
		}
		return s5;
	}

	public String geteto(String s, String s1, String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.ISSUEDDATE,b.EXPIRATIONDATE,b.NOTE from (eto a,etopermitinfo b) where a.MODIFIEDINPAST='1' and b.depid='4' and a.ETOID=b.ETOID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("4"))
					s6 = deptitle;

				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">ETO[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to ETO "
								+ s6
								+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("geteto")
					.append(exception).toString());
		}

		return s5;
	}

	public String getetostacktest(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder()).append(" AND b.NEXTDATE BETWEEN '")
				.append(s1).append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.NEXTDATE,b.NEXTDATENOTE from (eto a,etotestinfo b) where a.MODIFIEDINPAST='1' and a.ETOID=b.ETOID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">ETO[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Next Stack Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTDATE")))
						.append("</td><td>Refer to ETO Next Stack Test Date  and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getetostacktest")
					.append(exception).toString());
		}

		return s5;
	}

	public String getfumehood(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.STATUS,b.depid,b.ISSUEDDATE,b.EXPIRATIONDATE,b.NOTE from (fumehoods a,fumehoodpermitinfo b) where a.STATUS='1' and b.depid='2' and a.FUMEHOODID=b.FUMEHOODID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("2"))
					s6 = deptitle;
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">FUMEHOODS[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Fumehood "
								+ s6
								+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getfumehood")
					.append(exception).toString());
		}
		return s5;
	}

	public String getgenerator(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.STATUS,b.depid,b.ISSUEDDATE,b.EXPIRATIONDATE,b.DEPCOMPLIANCECOMMENTS from (generator a,generatorpermitinfo b) where a.STATUS='1' and a.GENERATORID=b.GENERATORID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("1"))
					s6 = deptitle;
				else
					s6 = dobtitle;
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">GENERATOR[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Generator "
								+ s6
								+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("DEPCOMPLIANCECOMMENTS"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getgenerator")
					.append(exception).toString());
		}
		return s5;
	}

	public String getgeneratorstacktest(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.NEXTSTACKTESTDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.NEXTSTACKTESTDATE,a.NEXTSTACKTESTDATENOTE,a.STATUS from generator a where a.STATUS='1' and a.buildingid=? ")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">GENERATOR[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Next Stack Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTSTACKTESTDATE")))
						.append("</td><td>Refer to Generator Next Stack Test Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTSTACKTESTDATENOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getgeneratorstacktest").append(exception)
					.toString());
		}
		return s5;
	}

	public String getlandfill(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.STATUS,b.depid,b.ISSUEDDATE,b.EXPIRATIONDATE,b.NOTE from (landfills a,landfillpermitinfo b) where a.STATUS='1' and b.depid='2' and a.LANDFILLSID=b.LANDFILLSID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String s6;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); s5 = (new StringBuilder())
					.append(s5)
					.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">LANDFILLS[<font color=\"#FF66FF\">")
					.append(resultset.getString("FACILITYDESIGNATEDID"))
					.append("</font>]</td><td >")
					.append(s6 + " Expiration Date")
					.append("</td><td >")
					.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
							.getString("EXPIRATIONDATE")))
					.append("</td><td>Refer to LandFill "
							+ s6
							+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
					.append(resultset.getString("NOTE")).append("</td></tr>")
					.toString()) {
				s6 = "";

				if (resultset.getString("depid").equals("2"))
					s6 = deptitle;

			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getlandfill")
					.append(exception).toString());
		}
		return s5;
	}

	public String getmis(String s, String s1, String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.MISEXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select a.FACILITYDESIGNATEDID,a.MISEXPIRATIONDATE,a.MISEXPIRATIONDATENOTE,a.MISDOB,a.MISFD,a.MISDEP,a.MISDEC,a.MISDOH,a.MISOTHER,a.MODIFIEDINPAST from others a where a.MODIFIEDINPAST='1' and a.buildingid=?")
				.append(s4).toString();
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			resultset = preparedstatement.executeQuery();
			resultset.next();

			/*
			 * String fdt = resultset.getString("MISFD"); String mdb =
			 * resultset.getString("MISDOB"); String mdh =
			 * resultset.getString("MISDOH"); String mdc =
			 * resultset.getString("MISDEC"); String mdp =
			 * resultset.getString("MISDEP");
			 * 
			 * String chdep;
			 * 
			 * if(fdt.equals("Y")) { chdep = fdtitle; resultset.next(); } else
			 * if(mdb.equals("Y")) { chdep = dobtitle; resultset.next(); } else
			 * if(mdh.equals("Y")) { chdep = dohtitle; } else
			 * if(mdc.equals("Y")) { chdep = dectitle; } else
			 * if(mdp.equals("Y")) { chdep = deptitle; } else chdep="";
			 */

			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String otr = resultset.getString("MISOTHER");
				String s7 = "";
				if (resultset.getString("MISFD").equals("Y"))
					s7 = fdtitle;
				else if (resultset.getString("MISDOB").equals("Y"))
					s7 = dobtitle;
				else if (resultset.getString("MISDOH").equals("Y"))
					s7 = dohtitle;
				else if (resultset.getString("MISDEC").equals("Y"))
					s7 = dectitle;
				else if (resultset.getString("MISDEP").equals("Y"))
					s7 = deptitle;
				else

					s7 = otr;

				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Miscellaneous[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >" + s7
								+ " Expiration Date</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("MISEXPIRATIONDATE")))
						.append("</td><td>Refer to Miscellaneous "
								+ deptitle
								+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("MISEXPIRATIONDATENOTE"))
						.append("</td></tr>").toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("absorber/chiller")
					.append(exception).toString());
		}
		return s5;
	}

	public String getspraybooth(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.STATUS,b.depid,b.ISSUEDDATE,b.EXPIRATIONDATE,b.NOTE from (spraybooth a,sprayboothpermitinfo b) where a.STATUS='1' and b.depid='2' and a.SPRAYBOOTHID=b.SPRAYBOOTHID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String s6;
			for (resultset = preparedstatement.executeQuery(); resultset.next(); s5 = (new StringBuilder())
					.append(s5)
					.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">SPRAYBOOTH[<font color=\"#FF66FF\">")
					.append(resultset.getString("FACILITYDESIGNATEDID"))
					.append("</font>]</td><td >")
					.append(s6 + " Expiration Date")
					.append("</td><td >")
					.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
							.getString("EXPIRATIONDATE")))
					.append("</td><td>Refer to Spraybooth "
							+ s6
							+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
					.append(resultset.getString("NOTE")).append("</td></tr>")
					.toString()) {
				s6 = "";
				if (resultset.getString("depid").equals("2"))
					s6 = deptitle;
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getspraybooth")
					.append(exception).toString());
		}
		return s5;
	}

	public String getpress(String s, String s1, String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.DEPEXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		String s6 = (new StringBuilder())
				.append("select a.FACILITYDESIGNATEDID,a.DEPEXPIRATIONDATE,a.MODIFIEDINPAST from press a where a.MODIFIEDINPAST='1' and a.buildingid=?")
				.append(s4).toString();
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement(s6);
			preparedstatement.setString(1, s);
			ResultSet resultset;
			String chdep = deptitle;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td >Printing Press[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td>" + chdep
								+ " Expiration Date</td><td>")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("DEPEXPIRATIONDATE")))
						.append("</td><td>Refer to Printing Press "
								+ deptitle
								+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append("").append("</td></tr>").toString();

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getpress")
					.append(exception).toString());
		}
		return s5;
	}

	public String getrpz(String s, String s1, String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();
		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.depid,b.ISSUEDATE,b.EXPIRATIONDATE,b.NOTE from (rpz a,rpzpermitinfo b) where a.MODIFIEDINPAST='1' and b.depid='1' and a.RPZID=b.RPZID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">RPZ[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(deptitle + "Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to RPZ "
								+ deptitle
								+ " Permit Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getrpz")
					.append(exception).toString());
		}
		return s5;
	}

	public String getrpzstacktest(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.NEXTINSPECTIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.MODIFIEDINPAST,b.NEXTINSPECTIONDATE,b.NOTE from (rpz a,rpzinspectioninfo b) where a.MODIFIEDINPAST='1' and a.RPZID=b.RPZID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">RPZ[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Next Inspection Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTINSPECTIONDATE")))
						.append("</td><td>Refer to RPZ Next Inspection Date Permit and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getrpzstacktest")
					.append(exception).toString());
		}
		return s5;
	}

	public String getstoragetank(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.EXPIRATIONDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.TANKSTATUS,b.depid,b.ISSUEDATE,b.EXPIRATIONDATE,b.NOTE from (storagetank a,storagepermitinfo b) where a.TANKSTATUS='1' and a.STORAGETANKID=b.STORAGETANKID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				String s6 = "";
				if (resultset.getString("depid").equals("1"))
					s6 = "State Agency Permit Expiration Date";
				else
					s6 = "CITY/WCDOH/RCDOH  Expiration Date";
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Storagetank[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append(s6 + " Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("EXPIRATIONDATE")))
						.append("</td><td>Refer to Storagetank "
								+ s6
								+ " Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getstoragetank")
					.append(exception).toString());
		}
		return s5;
	}

	public String getstoragetanktightness(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.NEXTTESTDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.TANKSTATUS,b.NEXTTESTDATE,b.NEXTTESTDATENOTE from (storagetank a,tanktightnessinfo b) where a.TANKSTATUS='1' and a.STORAGETANKID=b.STORAGETANKID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {

				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Storagetank[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Storagetank  Tightness Next Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTTESTDATE")))
						.append("</td><td>Refer to Storagetank Tightness Next Test Date  and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTTESTDATENOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getstoragetank")
					.append(exception).toString());
		}
		return s5;
	}

	public String getstoragetankpbsdate(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.pbsexpirationdate BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.pbsexpirationdate,a.pbsexpirationdatenote,a.TANKSTATUS from storagetank a where a.TANKSTATUS='1' and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				// String tankstatus = resultset.getString("TANKSTATUS");
				// String cm = "1";
				// if(tankstatus.equals(cm))
				{
					s5 = (new StringBuilder())
							.append(s5)
							.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Storagetank[<font color=\"#FF66FF\">")
							.append(resultset.getString("FACILITYDESIGNATEDID"))
							.append("</font>]</td><td >")
							.append("Pbs Expiration Date")
							.append("</td><td >")
							.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
									.getString("pbsexpirationdate")))
							.append("</td><td>Refer to Storagetank Pbs Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
							.append(resultset
									.getString("pbsexpirationdatenote"))
							.append("</td></tr>").toString();
				}
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getstoragetankpbsdate").append(exception)
					.toString());
		}
		return s5;
	}

	public String getstoragetankcathodictest(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND b.NEXTTESTDUEDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.TANKSTATUS,b.NEXTTESTDUEDATE,b.NOTE from (storagetank a,trienialcathodicinfo b) where a.TANKSTATUS='1' and a.STORAGETANKID=b.STORAGETANKID and a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {

				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Storagetank[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Storagetank  Cathodic Protection Next Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTTESTDUEDATE")))
						.append("</td><td>Refer to Storagetank Cathodic Protection Next Test Date  and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NOTE"))
						.append("</td></tr>").toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getstoragetankcathodictest").append(exception)
					.toString());
		}
		return s5;
	}

	public String getstoragetankcathodicinstallationtest(String s, String s1,
			String s2, Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.cathodicinstallationdate BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.cathodicinstallationdate,a.TANKSTATUS from storagetank a where a.TANKSTATUS='1' and a.buildingid=? ")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Storagetank[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Cathodic Protection Installation Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("cathodicinstallationdate")))
						.append("</td><td>Refer to Storage Tank Cathodic Installation Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td></tr>")
						.toString();
			}
			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getstoragetankcathodicinstallationtest")
					.append(exception).toString());
		}
		return s5;
	}

	public String getbulkoxygen(String s, String s1, String s2,
			Connection connection) {

		String s4 = (new StringBuilder())
				.append(" AND a.NEXTTESTDATE BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYDESIGNATEDID,a.NEXTTESTDATE,a.NEXTTESTDATENOTE,a.MODIFIEDINPAST from bulkoxygentank a where a.MODIFIEDINPAST='1' and a.buildingid=?  	")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();)
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Bulk Oxygen Storage Tank [<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYDESIGNATEDID"))
						.append("</font>]</td><td >")
						.append("Bulk Oxygen Storage Tank  5 Yrs Pressure Next Test Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("NEXTTESTDATE")))
						.append("</td><td>Refer to Bulk Oxygen Storage Tank  5 Yrs Pressure Next Test Date  and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td>")
						.append(resultset.getString("NEXTTESTDATENOTE"))
						.append("</td></tr>").toString();

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder()).append("getbulkoxygen")
					.append(exception).toString());
		}
		return s5;
	}

	public String getstackmethodnine(String s, String s1, String s2,
			Connection connection) {
		String s4 = (new StringBuilder())
				.append(" AND a.methodNineNextTestDate BETWEEN '").append(s1)
				.append("' AND '").append(s2).append("'").toString();

		String s5 = "";
		try {
			PreparedStatement preparedstatement = connection
					.prepareStatement((new StringBuilder())
							.append("select a.FACILITYSTACKID,a.methodNineNextTestDate from stack a where a.buildingid=?")
							.append(s4).toString());
			preparedstatement.setString(1, s);
			ResultSet resultset;
			for (resultset = preparedstatement.executeQuery(); resultset.next();) {
				s5 = (new StringBuilder())
						.append(s5)
						.append("<tr><td  bgcolor=\"#E4E8E3\"></td><td  bgcolor=\"#E4E8E3\"></td><td width=\"11%\">Stack[<font color=\"#FF66FF\">")
						.append(resultset.getString("FACILITYSTACKID"))
						.append("</font>]</td><td >")
						.append("Method 9 Expiration Date")
						.append("</td><td >")
						.append(uo.convertYyyyMmDd2MmDdYyyy(resultset
								.getString("methodNineNextTestDate")))
						.append("</td><td>Refer to Stack Method9 Test Expiration Date and for further help Please contact EES</td><td>Phone No:(914) 788 4165<br>E-Mail:clientdeadline@eespc.com</td><td></td></tr>")
						.toString();
			}

			SqlUtil _tmp = sqlutil;
			SqlUtil.close(resultset);
			SqlUtil _tmp1 = sqlutil;
			SqlUtil.close(preparedstatement);
		} catch (Exception exception) {
			System.out.println((new StringBuilder())
					.append("getstackmethodnine").append(exception).toString());
		}
		return s5;
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim()))
			return s;
		else
			return s1;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	String dobtitle = "";
	String deptitle = "";
	String fdtitle = "";
	String dectitle = "";
	String dohtitle = "";

	SqlUtil sqlutil;
	UtilityObject uo;
	private static Log log = LogFactory
			.getLog(com.eespc.tracking.entity.ExpirationEntity.class);

}