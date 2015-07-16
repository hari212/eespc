// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 3/30/2014 6:10:22 PM
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   pdfreport.java

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import sevenb.fromseventh;
import Test.TestFacility;

import com.eespc.tracking.reports.datasources.HashDataSource;

public class pdfreport extends HttpServlet {

	public pdfreport() {
		bytes = (byte[]) null;
		path = "";
		conn = null;
		fs = new fromseventh();
		ds = null;
	}

	public void service(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws IOException,
			ServletException {
		ServletContext servletcontext = getServletConfig().getServletContext();
		HttpSession httpsession = httpservletrequest.getSession();
		String s = String.valueOf(httpsession.getAttribute("expath"));
		if (s.equals("7")) {
			path = servletcontext.getRealPath("/report/exhi_7.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport7(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("8")) {
			path = servletcontext.getRealPath("/report/exhi8.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport8(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("9")) {
			path = servletcontext.getRealPath("/report/exhi9.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport9(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("10")) {
			path = servletcontext.getRealPath("/report/exhi10.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport10(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("11")) {
			path = servletcontext.getRealPath("/report/exhi11.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport11(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("12")) {
			path = servletcontext.getRealPath("/report/exhi12.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport12(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("13"))
			path = servletcontext.getRealPath("/report/exhi13.jrxml");
		else if (s.equals("14")) {
			path = servletcontext.getRealPath("/report/exhi14.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport14(TestFacility
					.getCurrentFacilityID()));
		} else if (s.equals("17")) {
			path = servletcontext.getRealPath("/report/exhi17.jrxml");
			ds = new HashDataSource(fs.getNycdobStatusReport15(TestFacility
					.getCurrentFacilityID()));
		} else {
			path = "";
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eespc", "root", "root");
			PreparedStatement preparedstatement = conn
					.prepareStatement("SELECT a.pbsnumber FROM storagetank a,building d,facility f WHERE d.buildingid=a.buildingid AND d.FACILITYID = f.FACILITYID AND f.FACILITYID=?");
			preparedstatement.setString(1,
					String.valueOf(TestFacility.getCurrentFacilityID()));
			for (ResultSet resultset = preparedstatement.executeQuery(); resultset
					.next();)
				str1 = resultset.getString(1);

		} catch (Exception exception) {
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eespc", "root", "root");
			PreparedStatement preparedstatement1 = conn
					.prepareStatement("select clientname,facilitytype from facility where facilityid=?");
			preparedstatement1.setString(1,
					String.valueOf(TestFacility.getCurrentFacilityID()));
			for (ResultSet resultset1 = preparedstatement1.executeQuery(); resultset1
					.next();) {
				str = resultset1.getString(1);
				typ = resultset1.getString(2);
			}

			String s1 = "";
			if (typ.equals("1"))
				s1 = "Y";
			else
				s1 = "N";
			String s2 = str.toUpperCase();
			HashMap hashmap = new HashMap();
			hashmap.put("Report_Title",
					String.valueOf(TestFacility.getCurrentFacilityID()));
			hashmap.put("client_name", s2);
			hashmap.put("name", str);
			hashmap.put("pbs_no", str1);
			hashmap.put("fac", s1);
			jasperDesign = JRXmlLoader.load(path);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			HashDataSource hashdatasource = ds;
			if (s.equals("13"))
				jasperPrint = JasperFillManager.fillReport(jasperReport,
						hashmap, conn);
			else
				jasperPrint = JasperFillManager.fillReport(jasperReport,
						hashmap, hashdatasource);
		} catch (SQLException sqlexception) {
			System.err.println(sqlexception.getMessage());
		} catch (ClassNotFoundException classnotfoundexception) {
			System.err.println("No such class found!");
		} catch (JRException jrexception) {
		}
		byte abyte0[] = (byte[]) null;
		abyte0 = getPdf();
		if (abyte0 != null && abyte0.length > 0) {
			httpservletresponse.setContentType("application/pdf");
			httpservletresponse.setContentLength(abyte0.length);
			ServletOutputStream servletoutputstream = httpservletresponse
					.getOutputStream();
			try {
				servletoutputstream.write(abyte0, 0, abyte0.length);
				servletoutputstream.flush();
			} catch (IOException ioexception) {
				if (servletoutputstream != null)
					try {
						servletoutputstream.close();
					} catch (IOException ioexception1) {
					}
			}
		} else {
			httpservletresponse.setContentType("text/html");
			PrintWriter printwriter = httpservletresponse.getWriter();
			printwriter.println("<html>");
			printwriter.println("<body bgcolor=\"white\">");
			printwriter.println("<span class=\"bold\">Empty response.</span>");
			printwriter.println("</body>");
			printwriter.println("</html>");
		}
	}

	public byte[] getPdf() {
		byte[] byt = (byte[]) null;
		try {
			byt = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			System.out.println(e);
		}
		return byt;

	}

	String str;
	String str1;
	String typ;
	byte bytes[];
	JasperReport jasperReport;
	JasperPrint jasperPrint;
	JasperDesign jasperDesign;
	String path;
	Connection conn;
	fromseventh fs;
	HashDataSource ds;
}