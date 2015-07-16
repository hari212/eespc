package com.eespc.tracking.reports.beans;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import sevenb.fromseventh;

import com.eespc.tracking.bo.FacilityVo;
import com.eespc.tracking.bo.myenum.ReportExhibitsEnum;
import com.eespc.tracking.exceptions.ReportException;
import com.eespc.tracking.reports.datasources.HashDataSource;
import com.eespc.tracking.reports.datasources.factories.Exhibit1DataSourceFactory;
import com.eespc.tracking.reports.datasources.factories.Exhibit2DataSourceFactory;
import com.eespc.tracking.reports.datasources.factories.Exhibit3DataSourceFactory;
import com.eespc.tracking.reports.datasources.factories.Exhibit4DataSourceFactory;
import com.eespc.tracking.reports.datasources.factories.Exhibit5DataSourceFactory;
import com.eespc.tracking.reports.datasources.factories.Exhibit6DataSourceFactory;
import com.eespc.tracking.util.SqlUtil;
import com.eespc.tracking.util.UtilityObject;

public class ReportBean implements Serializable {

	public ReportBean() {
		jasperPrint = null;
		imagesMap = null;
		pageIndex = 0;
	}

	public void setServletContext(ServletContext servletcontext) {
		servletContext = servletcontext;
	}

	public void setReportType(String s) {
		reportType = s;
	}

	public Map getImagesMap() {
		return imagesMap;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public int getLastPage() {
		if (jasperPrint != null && jasperPrint.getPages() != null)
			return jasperPrint.getPages().size() - 1;
		else
			return -1;
	}

	public void setFirstPage() {
		pageIndex = 0;
	}

	public void setPreviousPage() {
		if (pageIndex > 0)
			pageIndex--;
	}

	public void setNextPage() {
		if (pageIndex < getLastPage())
			pageIndex++;
	}

	public void setLastPage() {
		pageIndex = getLastPage();
	}

	public String getError() {
		return error;
	}

	public void fillReport(HttpServletRequest httpservletrequest)
			throws ReportException {
		// System.out.println("hello1");
		SqlUtil sqlutil;
		Exception exception1;
		java.sql.Connection connection = null;
		String s = "";
		sqlutil = null;
		HashMap hashmap = new HashMap();
		Object obj = null;
		HashMap hashmap1 = new HashMap();
		error = null;
		if (!UtilityObject.isNotEmpty(reportType))
			error = "'Report Type' field is mandatory.";
		if (error != null)
			return;
		pageIndex = 0;
		try {
			FacilityVo facilityvo = (FacilityVo) httpservletrequest
					.getSession().getAttribute("REPORT_FACILITY_VO");
			if (facilityvo == null)
				throw new ReportException(
						"Unable to get the Report Facility Vo !!!");
			String s1;
			if (String.valueOf(facilityvo.getClientName()).equals("1"))
				s1 = "Y";
			else
				s1 = "N";

			String deptitle = "";
			String dobtitle = "";
			String dectitle = "";
			String deprep = "";

			int boro = facilityvo.getBorough();
			if (boro == 1) {
				deptitle = "DEP/DOH";
				dobtitle = "DOB/TOWN/CITY";
				dectitle = "NYSDEC/DOH";
				deprep = "DOH";
			} else {
				deptitle = "DEP";
				dobtitle = "DOB";
				dectitle = "NYSDEC/DOH";
				deprep = "DEP";
			}

			hashmap.put("deptitle", deptitle);
			hashmap.put("dobtitle", dobtitle);
			hashmap.put("dectitle", dectitle);
			hashmap.put("deprep", deprep);
			hashmap.put("clientName", facilityvo.getClientName());
			hashmap.put("County", facilityvo.getCounty().toUpperCase());
			hashmap.put("name", facilityvo.getClientName());
			hashmap.put("client_name", facilityvo.getClientName().toUpperCase());
			hashmap.put("fac", s1);
			String imgpath = servletContext.getRealPath("");
			System.out.println(imgpath);
			ImageIcon imageIcon = new ImageIcon(imgpath + "\\images\\ees.jpg");
			Image image = imageIcon.getImage();
			hashmap.put("FooterImage", image);
			ReportExhibitsEnum reportexhibitsenum = ReportExhibitsEnum
					.parse(reportType);
			JasperReport jasperreport = getCompiledReport(reportexhibitsenum);
			if (!reportexhibitsenum.isToUseConnection()) {
				Object obj1 = null;
				Object obj2 = null;
				if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_1
						.getCode()) {
					Exhibit1DataSourceFactory exhibit1datasourcefactory = new Exhibit1DataSourceFactory();
					obj2 = exhibit1datasourcefactory.getDataSource(facilityvo
							.getId());
					hashmap.put(
							"notes",
							"Notes :\n"
									+ exhibit1datasourcefactory
											.getfoot(facilityvo.getId()));
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_2
						.getCode()) {
					Exhibit2DataSourceFactory exhibit2datasourcefactory = new Exhibit2DataSourceFactory();
					obj2 = exhibit2datasourcefactory.getDataSource(facilityvo
							.getId());
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_3
						.getCode()) {
					Exhibit3DataSourceFactory exhibit3datasourcefactory = new Exhibit3DataSourceFactory();
					obj2 = exhibit3datasourcefactory.getDataSource(facilityvo
							.getId());
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_4
						.getCode()) {
					Exhibit4DataSourceFactory exhibit4datasourcefactory = new Exhibit4DataSourceFactory();
					obj2 = exhibit4datasourcefactory.getDataSource(facilityvo
							.getId());
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_5
						.getCode()) {
					Exhibit5DataSourceFactory exhibit5datasourcefactory = new Exhibit5DataSourceFactory();
					obj2 = exhibit5datasourcefactory.getDataSource(facilityvo
							.getId());
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_6
						.getCode()) {
					Exhibit6DataSourceFactory exhibit6datasourcefactory = new Exhibit6DataSourceFactory();
					obj2 = exhibit6datasourcefactory.getDataSource(facilityvo
							.getId());
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_7
						.getCode()) {
					// String
					// petro=UtilityObject.checkNullAndFill(fromseventh.getPetrocapacity(facilityvo.getId()+""),"0");
					String abov = UtilityObject
							.checkNullAndFill(
									fromseventh.getaboveGround(facilityvo
											.getId() + ""), "0");
					String under = UtilityObject
							.checkNullAndFill(
									fromseventh.getunderGround(facilityvo
											.getId() + ""), "0");
					// System.out.println("Hyd:"+petro+" Abov:"+abov+" Under:"+under);

					try {
						// if(((Integer.parseInt(petro)+Integer.parseInt(abov))>1320)
						// || (Integer.parseInt(under)>42000))
						if ((Integer.parseInt(abov) >= 1320)
								|| (Integer.parseInt(under) >= 42000)) {
							hashmap.put("SPCC", "Y");
						} else {
							hashmap.put("SPCC", "N");
						}
					} catch (Exception e) {
						System.out.println("Exception:" + e);
					}
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource = new HashDataSource(
							fromseventh.getNycdobStatusReport7(facilityvo
									.getId()));
					obj2 = hashdatasource;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_8
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource1 = new HashDataSource(
							fromseventh.getNycdobStatusReport8(facilityvo
									.getId()));
					obj2 = hashdatasource1;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_9
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource2 = new HashDataSource(
							fromseventh.getNycdobStatusReport9(facilityvo
									.getId()));
					obj2 = hashdatasource2;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_10
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource3 = new HashDataSource(
							fromseventh.getNycdobStatusReport10(facilityvo
									.getId()));
					obj2 = hashdatasource3;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_11
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource4 = new HashDataSource(
							fromseventh.getNycdobStatusReport11(facilityvo
									.getId()));
					obj2 = hashdatasource4;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_12
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource5 = new HashDataSource(
							fromseventh.getNycdobStatusReport12(facilityvo
									.getId()));
					obj2 = hashdatasource5;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_13
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource6 = new HashDataSource(
							fromseventh.getNycdobStatusReport13(facilityvo
									.getId()));
					obj2 = hashdatasource6;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_14
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource7 = new HashDataSource(
							fromseventh.getNycdobStatusReport14(facilityvo
									.getId()));
					obj2 = hashdatasource7;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_15
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource8 = new HashDataSource(
							fromseventh.getNycdobStatusReport15(facilityvo
									.getId()));
					obj2 = hashdatasource8;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_16
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource9 = new HashDataSource(
							fromseventh.getNycdobStatusReport16(facilityvo
									.getId()));
					obj2 = hashdatasource9;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_17
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource10 = new HashDataSource(
							fromseventh.getNycdobStatusReport17(facilityvo
									.getId()));
					obj2 = hashdatasource10;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_18
						.getCode()) {
					String hyd = UtilityObject.checkNullAndFill(
							fromseventh.getHydraliccapacity(facilityvo.getId()
									+ ""), "0");
					String abov = UtilityObject
							.checkNullAndFill(
									fromseventh.getaboveGround(facilityvo
											.getId() + ""), "0");
					String under = UtilityObject
							.checkNullAndFill(
									fromseventh.getunderGround(facilityvo
											.getId() + ""), "0");
					System.out.println("Hyd:" + hyd + " Abov:" + abov
							+ " Under:" + under);

					try {
						if (((Integer.parseInt(hyd) + Integer.parseInt(abov)) > 1320)
								|| (Integer.parseInt(under) > 42000)) {
							hashmap.put("SPCC", "Y");
						} else {
							hashmap.put("SPCC", "N");
						}
					} catch (Exception e) {
						System.out.println("Exception:" + e);
					}

					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource10 = new HashDataSource(
							fromseventh.getNycdobStatusReport18(facilityvo
									.getId()));
					obj2 = hashdatasource10;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_19
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource10 = new HashDataSource(
							fromseventh.getNycdobStatusReport19(facilityvo
									.getId()));
					obj2 = hashdatasource10;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_20
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource10 = new HashDataSource(
							fromseventh.getNycdobStatusReport20(facilityvo
									.getId()));
					obj2 = hashdatasource10;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_21
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource10 = new HashDataSource(
							fromseventh.getNycdobStatusReport21(facilityvo
									.getId()));
					obj2 = hashdatasource10;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_22
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource10 = new HashDataSource(
							fromseventh.getNycdobStatusReport22(facilityvo
									.getId()));
					obj2 = hashdatasource10;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_23
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource23 = new HashDataSource(
							fromseventh.getNycdobStatusReport23(facilityvo
									.getId()));
					obj2 = hashdatasource23;
				} else if (reportexhibitsenum.getCode() == ReportExhibitsEnum.EXHIBIT_24
						.getCode()) {
					hashmap.put(
							"pbs_no",
							fromseventh.getpbs((new StringBuilder()).append("")
									.append(facilityvo.getId()).toString()));
					HashDataSource hashdatasource24 = new HashDataSource(
							fromseventh.getNycdobStatusReport24(facilityvo
									.getId()));
					obj2 = hashdatasource24;
				} else {
					throw new ReportException(
							"Unable to get the Exhibit Data Source Concrete Factory.");
				}
				jasperPrint = JasperFillManager.fillReport(jasperreport,
						hashmap,
						((net.sf.jasperreports.engine.JRDataSource) (obj2)));
			} else {
				sqlutil = new SqlUtil();
				connection = sqlutil.getConnection();
				jasperPrint = JasperFillManager.fillReport(jasperreport,
						hashmap, connection);
			}
		} catch (Exception exception) {
			throw new ReportException(exception);
		} finally {
			if (connection != null)
				sqlutil.closeConnection();
		}
		return;
	}

	public byte[] getPdf() {
		try {
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public byte[] getXls() {
		ByteArrayOutputStream bytearrayoutputstream;
		JRXlsExporter jrxlsexporter;
		bytearrayoutputstream = new ByteArrayOutputStream();
		jrxlsexporter = new JRXlsExporter();
		jrxlsexporter.setParameter(JRExporterParameter.JASPER_PRINT,
				jasperPrint);
		jrxlsexporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
				bytearrayoutputstream);
		jrxlsexporter.setParameter(
				JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		jrxlsexporter.setParameter(
				JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);

		try {
			jrxlsexporter.exportReport();
			return bytearrayoutputstream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHtml() {
		if (jasperPrint == null)
			return "";
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

	private JasperReport getCompiledReport(ReportExhibitsEnum reportexhibitsenum)
			throws JRException {
		String s = reportexhibitsenum.getReportFileName();
		File file = new File(servletContext.getRealPath((new StringBuilder())
				.append("/reports/").append(s).append(".jasper").toString()));
		if (!file.exists())
			JasperCompileManager.compileReportToFile(servletContext
					.getRealPath((new StringBuilder()).append("/reports/")
							.append(s).append(".jrxml").toString()));
		JasperReport jasperreport = (JasperReport) JRLoader.loadObject(file
				.getPath());
		return jasperreport;
	}

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"dd-M-yyyy HH:mm:ss");
	protected ServletContext servletContext;
	protected JasperPrint jasperPrint;
	protected Map imagesMap;
	protected int pageIndex;
	protected String reportType;
	protected String error;

}