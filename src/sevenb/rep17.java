package sevenb;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import Test.TestFacility;

import com.eespc.tracking.reports.datasources.HashDataSource;
import com.eespc.tracking.util.SqlUtil;

import exhibit.exhi_re;

public class rep17 implements Serializable {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-M-yyyy HH:mm:ss");
	protected ServletContext servletContext;
	protected JasperPrint jasperPrint;
	protected Map imagesMap;
	protected int pageIndex;
	protected String reportType;
	protected String error;
	exhi_re ss;
	String str;
	String str1;
	String strs;
	String typ;
	String path;
	Connection conn;
	JasperReport jasperReport;
	JasperDesign jasperDesign;

	public rep17(String s) {
		ss = new exhi_re();
		path = "";
		conn = null;
		jasperPrint = null;
		imagesMap = null;
		pageIndex = 0;
		path = s;
	}

	public Map getImagesMap() {
		return imagesMap;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getLastPage() {
		jasperPrint = data_con();
		if (jasperPrint != null && jasperPrint.getPages() != null) {
			return jasperPrint.getPages().size() - 1;
		} else {
			return -1;
		}
	}

	public void setFirstPage() {
		pageIndex = 0;
	}

	public void setPreviousPage() {
		if (pageIndex > 0) {
			pageIndex--;
		}
	}

	public void setNextPage() {
		if (pageIndex < getLastPage()) {
			pageIndex++;
		}
	}

	public void setLastPage() {
		pageIndex = getLastPage();
	}

	public String getError() {
		return error;
	}

	public String getHtml() {
		jasperPrint = data_con();
		if (jasperPrint == null) {
			return "";
		}
		StringBuffer stringbuffer = new StringBuffer();
		imagesMap = new HashMap();
		JRHtmlExporter jrhtmlexporter = new JRHtmlExporter();
		jrhtmlexporter.setParameter(JRExporterParameter.JASPER_PRINT,
				jasperPrint);
		jrhtmlexporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER,
				stringbuffer);
		jrhtmlexporter
				.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
						Boolean.FALSE);
		jrhtmlexporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP,
				imagesMap);
		jrhtmlexporter.setParameter(JRHtmlExporterParameter.IMAGES_URI,
				"image?image=");
		jrhtmlexporter
				.setParameter(
						JRHtmlExporterParameter.HTML_HEADER,
						"<table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td>");
		jrhtmlexporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML,
				"");
		jrhtmlexporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER,
				"</td></tr></table>");
		jrhtmlexporter.setParameter(JRExporterParameter.PAGE_INDEX,
				new Integer(pageIndex));
		jrhtmlexporter.setParameter(
				JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.FALSE);
		try {
			jrhtmlexporter.exportReport();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return stringbuffer.toString();
	}

	public JasperPrint data_con() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eespc", "root", "root");
			PreparedStatement preparedstatement = conn
					.prepareStatement("SELECT a.pbsnumber FROM storagepermitinfo b,storagetank a,tanktightnessinfo c,bu"
							+ "ilding d,facility f WHERE a.STORAGETANKID = b.STORAGETANKID AND a.STORAGETANKID "
							+ "= c.STORAGETANKID AND d.BUILDINGREFID = a.BUILDINGID AND d.FACILITYID = f.FACILI"
							+ "TYID AND f.FACILITYID=?");
			preparedstatement.setString(1,
					String.valueOf(TestFacility.getCurrentFacilityID()));
			for (ResultSet resultset = preparedstatement.executeQuery(); resultset
					.next();) {
				str1 = resultset.getString(1);
			}

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

			String s = "";
			if (typ.equals("1")) {
				s = "Y";
			} else {
				s = "N";
			}
			HashDataSource hashdatasource = new HashDataSource(
					getNycdobStatusReport());
			HashDataSource hashdatasource1 = hashdatasource;
			String s1 = str.toUpperCase();
			HashMap hashmap = new HashMap();
			hashmap.put("Report_Title",
					String.valueOf(TestFacility.getCurrentFacilityID()));
			hashmap.put("client_name", s1);
			hashmap.put("name", str);
			hashmap.put("pbs_no", str1);
			hashmap.put("fac", s);
			jasperDesign = JRXmlLoader.load(path);
			jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashmap,
					hashdatasource1);
		} catch (Exception exception1) {
		}
		return jasperPrint;
	}

	public static List getNycdobStatusReport() {
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
					.prepareStatement("SELECT a.chemicalname1,a.chemicalname2,a.chemicalname3,a.chemicalname4,a.chemica"
							+ "lname5,a.fumehoodid,a.buildingid,a.make,a.model,a.voc,a.INCLUDEDINDECPERMIT,a.EX"
							+ "EMPTEDBYDEC,a.dob,b.permitnumber,b.expirationdate,c.buildingname FROM (fumehoods"
							+ " a,building c) left join fumehoodpermitinfo b on b.FUMEHOODID=a.FUMEHOODID and b"
							+ ".DEPID='2' where (a.STATUS='1' or a.STATUS='5') and a.buildingid=c.buildingid an"
							+ "d c.facilityid=?");
			preparedstatement.setString(1,
					String.valueOf(TestFacility.getCurrentFacilityID()));
			Hashtable hashtable;
			for (ResultSet resultset = preparedstatement.executeQuery(); resultset
					.next(); arraylist.add(hashtable)) {
				hashtable = new Hashtable();
				hashtable.put(
						"LOCATION",
						checkNullAndFill(resultset.getString("buildingname"),
								"N/A"));
				hashtable.put("MAKE",
						checkNullAndFill(resultset.getString("make"), "N/A"));
				hashtable.put("MODEL",
						checkNullAndFill(resultset.getString("model"), "N/A"));
				hashtable.put("VOC",
						checkNullAndFill(resultset.getString("voc"), "N/A"));
				String s = resultset.getString("INCLUDEDINDECPERMIT");
				if (s.equalsIgnoreCase("Y")) {
					hashtable.put("COMPLIANCE", "Y");
				} else {
					hashtable.put("COMPLIANCE", "N");
				}
				String s1 = checkNullAndFillf(
						resultset.getString("chemicalname1"), "");
				String s2 = checkNullAndFillf(
						resultset.getString("chemicalname2"), "");
				String s3 = checkNullAndFillf(
						resultset.getString("chemicalname3"), "");
				String s4 = checkNullAndFillf(
						resultset.getString("chemicalname4"), "");
				String s5 = checkNullAndFillf(
						resultset.getString("chemicalname5"), "");
				hashtable.put("CHEMICAL", s1 + s2 + s3 + s4 + s5);
				hashtable.put(
						"DEC",
						checkNullAndFill(resultset.getString("EXEMPTEDBYDEC"),
								"N/A"));
				hashtable.put(
						"DEP",
						checkNullAndFill(resultset.getString("permitnumber"),
								"N/A"));
				hashtable.put(
						"EXPIRATIONDATE",
						checkNullAndFilldate(
								resultset.getString("expirationdate"), "N/A"));
				hashtable.put("DOB",
						checkNullAndFill(resultset.getString("dob"), "N/A"));
			}

		} catch (Exception exception) {
			System.out.println("hai" + exception);
		}
		return arraylist;
	}

	public static String getchem(String s) {
		String as[] = { "chemical1", "chemical2", "chemical3", "chemical4" };
		String s1 = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/eespc", "root", "root");
			PreparedStatement preparedstatement = connection
					.prepareStatement("select chemicalid from  fumehoodtochemicals where FUMEHOODID=?");
			preparedstatement.setString(1, s);
			for (ResultSet resultset = preparedstatement.executeQuery(); resultset
					.next();) {
				int i = Integer.parseInt(resultset.getString("chemicalid")) - 1;
				s1 = s1 + " " + as[i];
			}

		} catch (Exception exception) {
			System.out.println("" + exception);
		}
		return s1;
	}

	public static boolean isNotEmpty(String s) {
		return s != null && s.trim().length() > 0;
	}

	public static String checkNullAndFilldate(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			String as[] = s.split("-");
			String s2 = as[1] + "/" + as[2] + "/" + as[0];
			return s2;
		} else {
			return s1;
		}
	}

	public static String checkNullAndFillf(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return s + "/";
		} else {
			return s1;
		}
	}

	public static String checkNullAndFill(String s, String s1) {
		if (isNotEmpty(s) && !"null".equalsIgnoreCase(s.trim())) {
			return s;
		} else {
			return s1;
		}
	}

}
