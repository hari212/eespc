package sevenb;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import net.sf.jasperreports.engine.JRException;
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

import exhibit.exhi_re;

// Referenced classes of package sevenb:
//            fromseventh, getpath

public class rep8 extends HttpServlet implements Serializable {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-M-yyyy HH:mm:ss");
	protected JasperPrint jasperPrint;
	protected Map imagesMap;
	protected int pageIndex;
	protected String reportType;
	protected String error;
	exhi_re ss;
	fromseventh fs;
	HashDataSource ds;
	String str;
	String str1;
	String strs;
	String typ;
	String path;
	Connection conn;
	JasperReport jasperReport;
	JasperDesign jasperDesign;
	String path1;
	getpath gp;

	public rep8(String s) throws JRException, IOException, ServletException,
			NullPointerException {
		ss = new exhi_re();
		fs = new fromseventh();
		ds = null;
		path = "";
		conn = null;
		path1 = "df";
		gp = new getpath();
		jasperPrint = null;
		imagesMap = null;
		pageIndex = 0;
		path = s;
		System.out.println(s);
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
		System.out.println("arundath" + gp.path());
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
			ds = new HashDataSource(fs.getNycdobStatusReport8(TestFacility
					.getCurrentFacilityID()));
			HashDataSource hashdatasource = ds;
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
					hashdatasource);
		} catch (Exception exception1) {
		}
		return jasperPrint;
	}

}
